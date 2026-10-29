# Recursion: Implementation and Benchmark Guide

## Ordered Documentation Review

The [2026-10-28 review](documentation-review.md), based on main
`6e8f87c1dcaae00d0d545d5ebb4ec539c48b33f7` (PR #108 Graphs review merge),
checks selected passages against all seven production Java files, eight test
sources and three JMH classes. Its [applied map](implementation-contracts.md)
adds exact helper invariants/counts, ownership and cost-model boundaries.
Fresh Java 21 verification passes 710 tests overall, including the same 28
module tests; benchmark packaging and registration are checked separately.
The original implementation, smoke/probe/doclint and author measurement
claims below retain their original dates and provenance.

## Selected Scope and Traceability

Package `org.anaalvarezdev.algorithms.recursion` mirrors main, test and JMH roots.
This contribution starts from main **`7ab257e7041d9e70207a54f876178c256ebe34d8`**,
which integrated Graphs research results through PR #81. Earlier executable
packages and the POM are unchanged. This is a focused scope/contract/evidence
review; conceptual examples and future paradigms are not automatically counted
as implemented.

| Documents | Executable responsibility | Evidence |
|---|---|---|
| 01 fundamentals / 02 cases / 03 stack | Factorial ordinary/tail/loop forms; bounded linear-depth examples | Exact domain, base cases, invalid inputs and logical frame model |
| 04 correctness / 05 complexity | All seven specifications, variants, invariants and recurrences below | Independent numeric, array and simulation oracles |
| 06 tail recursion / 07 iteration | Factorial accumulator and Euclidean GCD versus loops | Same mathematical result; no Java tail-call elimination assumption |
| 08 patterns | Fibonacci branching/memoisation, squaring, balanced pair/range recursion and Hanoi output | Calls versus depth; disjoint versus repeated subproblems; output cost |
| 09 mistakes / 10 interview notes | Limits, long sums, BigInteger bit cost, per-call cache and bounded output | Boundary, ownership, short-circuit and exhaustive small-input checks |

There are **7 production files**, **28 executed module tests** and **8 JMH
methods in three classes**. Mutual recursion, parsers, string/Unicode processing,
generic recursion frameworks, streaming Hanoi, arbitrary-precision factorial/
Fibonacci, trampolines and full divide-and-conquer/backtracking/dynamic-programming
suites remain extensions. Existing recursive searching/tree code stays in its
own module; this contribution does not duplicate or silently fix it.

## Contracts

| Type and operations | Input / result | Policy and ownership |
|---|---|---|
| Factorial.recursive / tailRecursive / iterative | int n in 0..20; exact long n! | 0!=1; outside range throws IllegalArgumentException before recursion |
| Fibonacci.naive | int n in 0..30; exact F(n) | 0/1 bases; 30 is a deliberate exponential-work cap |
| Fibonacci.memoised / iterative | int n in 0..92; exact long F(n) | Fresh memo array per public memoised call; no shared cache; 93 exceeds long |
| Power.recursive / iterative | Non-null BigInteger base, nonnegative int exponent; exact integer power when completed | 0^0=1; negative exponent throws IllegalArgumentException; BigInteger resource/range failures are not converted to primitive overflow |
| ArraySum.recursive / iterative | Non-null int[] or half-open slice [from,to); exact long sum | 0 <= from <= to <= length; invalid range throws IndexOutOfBoundsException; empty sum zero; input preserved |
| Palindrome.recursive / iterative | Non-null int[]; boolean symmetry | Empty/singleton true; compares exact int values, not text; input preserved |
| GreatestCommonDivisor.recursive / iterative | Any two ints; nonnegative long gcd | gcd(0,0)=0; sign ignored; MIN_VALUE magnitude 2,147,483,648 is supported |
| Hanoi.moves | int disks in 0..16; immutable List of Move(disk,from,to) | Source 0, target 2, spare 1; smallest disk 1; zero disks empty; 16 is an explicit materialised-output cap |

Null object/array arguments throw NullPointerException. The scalar APIs have no
null inputs. No API writes input arrays or mutable global state; Hanoi returns
an immutable plan of immutable records. The seven utility classes expose no
instances or user-facing memo arrays.

20! is 2,432,902,008,176,640,000; 21! does not fit long. F(92) is
7,540,113,804,746,346,429; F(93) does not fit long. Iterative Fibonacci avoids
computing the unused next value after F(92). Numeric limits differ from the
naive Fibonacci/Hanoi workload caps, which deliberately bound time/output.

Every possible mathematical sum of an int[] fits long: array length is at most
Integer.MAX_VALUE and each magnitude is at most 2^31, so any partial sum has
magnitude below 2^62. This new long-returning ArraySum contract is separate from
the older int-returning Arrays aggregation precondition; historical findings
and timings there are not relabelled or fixed by this module.

## Strategies, Traces and Correctness

### Factorial: returning versus accumulator state

```text
PRODUCT(n): if n=0 return 1; else return n*PRODUCT(n-1)
ACCUMULATE(n,a): if n=0 return a; else return ACCUMULATE(n-1,a*n)
LOOP: result=1; for factor=2..n: result*=factor
```

Termination uses decreasing nonnegative n or increasing bounded factor. Induction
assumes PRODUCT(n-1)=(n-1)!, then n times that value is n!. The tail variant
maintains `accumulator * remaining! = original!`; its update preserves the
identity and the n=0 result is exactly the accumulator. Loop state before a
factor is processed equals (factor-1)!. The accepted numeric domain ensures
every intermediate product fits long.

Ordinary PRODUCT(3) descends 3/2/1/0 and returns 1/1/2/6. Accumulation follows
`(3,1) -> (2,3) -> (1,6) -> (0,6)`. Both helpers have n+1 logical invocations;
this count excludes the public validation wrapper and is not a measured JVM
frame/instruction count.

### Fibonacci: repeated work versus stored subproblems

```text
NAIVE(n): if n<2 return n; else NAIVE(n-1)+NAIVE(n-2)
MEMO(n): allocate n+1 entries filled with -1
  cached(k): return stored value if present
             otherwise compute base or cached(k-1)+cached(k-2); store; return
ITERATIVE: handle zero; keep previous/current; update only through index n
```

Each recursive argument decreases, proving termination. Strong induction gives
F(n) from correct F(n-1)/F(n-2). Nonnegative valid results make -1 an unambiguous
uncomputed marker. Each cached state is computed once; cache lookups can repeat.
A fresh array is allocated/initialised for every public call, so measuring a
memoised call is not an O(1) lookup of a result retained from an earlier call.
The iterative invariant is previous=F(i-2), current=F(i-1) before update i.

For F(4), naive expansion requests F(3)+F(2), and F(3) again requests F(2)+F(1).
Memoisation stores the first F(2) and reuses it. Naive helper call count has
C(0)=C(1)=1 and C(n)=C(n-1)+C(n-2)+1, giving **C(n)=2F(n+1)-1**:

| Benchmark n | F(n) | Naive helper calls | Maximum distinct memo slots |
|---|---:|---:|---:|
| 10 | 55 | 177 | 11 |
| 20 | 6,765 | 21,891 | 21 |
| 30 | 832,040 | 2,692,537 | 31 |

Here phi denotes the golden ratio, (1+sqrt(5))/2. These are source/recurrence
counts, not timings. Exponential total calls coexist
with linear maximum active depth; the recursion tree's total nodes are not all
simultaneously live frames. Memoisation changes work and storage, not merely
function-call syntax.

### Power: reuse one half result

```text
POWER(base,e): handle e=0 and e=1
  half=POWER(base,floor(e/2))
  squared=half*half
  return squared if e even, else squared*base
```

The exponent strictly decreases above one. Assuming the half result is correct,
parity gives `base^(2q)=(base^q)^2` or `base^(2q+1)=(base^q)^2*base`. The half
result is computed once, not by two recursive calls. For 3^5: 5->2->1 gives
3, then 9, then 81*3=243. The iterative invariant is
`result * factor^remaining = originalBase^originalExponent`; processing a bit
and squaring the factor preserves it. The final unused square is skipped.

Both forms use O(log(e+1)) multiplications. **That is not overall logarithmic
bit-time** for arbitrary-precision operands. For |base|>=2 and e>0, output bits
B grow as Theta(e*log2(|base|)); multiplication operates on growing operands.
Output storage and BigInteger multiplication workspace are additional to
O(log(e+1)) recursive reference frames. BigInteger is immutable but resource
and supported-range constraints still apply. Tests of exponent MAX_VALUE use
bases -1/0/1 with bounded results, not an impossible promise of cheap huge output.

### Balanced array sum and mirrored-pair recursion

```text
SUM([lo,hi)): empty -> 0; singleton -> value
  mid=lo+floor((hi-lo)/2); return SUM([lo,mid))+SUM([mid,hi))
PALINDROME PAIRS([lo,hi)): empty -> true
  singleton pair i -> values[i] == values[length-1-i]
  otherwise check left half AND, if true, right half
```

Both decrease range length and partition without overlap or omission. Sum
induction combines exact sums of disjoint halves; all intermediate values fit
long by the earlier bound. For `[MAX_INT,MAX_INT]`, result is 4,294,967,294.
For a nonempty slice of length k, the full sum helper has k singleton leaves
and k-1 internal nodes, or 2k-1 calls, with logarithmic maximum depth.

Palindrome divides the **pair-index** interval 0..floor(length/2), not the two
array halves independently. Every pair compares exactly mirrored elements;
logical conjunction is equivalent to whole-array symmetry. `[1,2,2,1]` checks
pairs (0,3) and (1,2); the centre of an odd array needs no comparison. Short
circuiting preserves correctness. A first-pair mismatch still descends the
balanced tree to that pair, so recursive best work can be logarithmic, whereas
the iterative first-pair mismatch is constant.

### Euclidean GCD

```text
normalise both int magnitudes in long
GCD(a,b): if b=0 return a; else return GCD(b,a mod b)
```

Casting before abs supports Integer.MIN_VALUE. Common divisors of (a,b) equal
those of (b,a mod b); termination follows because nonzero second arguments
strictly decrease. In at most two remainder steps the relevant positive value
at least halves, giving O(log(M+1)) word work for M=max magnitudes. For (54,24),
steps are (54,24)->(24,6)->(6,0), returning 6. Tail position does not make a
Java constant-stack claim; the iterative form stores only the current pair.

### Hanoi: output itself is exponential

```text
TRANSFER(n,from,to,spare): if n=0 stop
  transfer n-1 from->spare using to
  output move of disk n from->to
  transfer n-1 spare->to using from
```

Induction moves the smaller stack legally out of the way, moves the largest
disk, then legally places the smaller stack on it. Before moving the largest,
all n-1 smaller disks must leave its source, and afterwards must reach its
target: every legal three-peg solution needs at least 2M(n-1)+1 moves. The
algorithm meets that bound, with M(0)=0, so M(n)=2^n-1 is optimal. The decreasing
disk count proves termination. At n=2 the plan is disk 1:0->1, disk 2:0->2,
disk 1:1->2. Maximum active recursion is linear; retaining the whole output is
exponential, including the immutable list's reference copy. No O(n)-total-space
claim is borrowed from an unmaterialised or streaming variant.

## Complexity and Java Execution Model

The following word-work recurrences describe the algorithmic families within
their validated educational domains; they do not extend long arithmetic beyond
its documented range. Let k be slice length, p=floor(array length/2), e exponent,
M maximum GCD magnitude and B the power result's bit length.

| Operation | Work / recurrence | Additional storage |
|---|---|---|
| Factorial ordinary/tail | T(n)=T(n-1)+Theta(1), Theta(n+1) | O(n+1) logical recursive depth |
| Factorial loop | Theta(n+1) word operations | O(1) |
| Naive Fibonacci | Theta(phi^n), also a looser O(2^n) bound | O(n+1) maximum depth, not exponential live stack |
| Memoised Fibonacci | O(n+1) word work, including array fill | O(n+1) memo plus recursive depth |
| Iterative Fibonacci | O(n+1) | O(1) |
| Power by squaring | O(log(e+1)) multiplications, with variable bit cost | Recursive O(log(e+1)) frames or iterative O(1) references, plus numeric values/library workspace |
| Balanced ArraySum | T(k)=T(floor(k/2))+T(ceil(k/2))+Theta(1), Theta(k+1) | O(log(k+1)) depth; no slice copies |
| ArraySum loop | Theta(k+1) | O(1) |
| Balanced Palindrome | O(p+1) worst, O(log(p+1)) first-mismatch path | O(log(p+1)) depth; scalar result |
| Palindrome loop | O(p+1) worst, O(1) first mismatch | O(1) |
| Euclidean GCD | O(log(M+1)) word work | Recursive same-order depth; iterative O(1) |
| Materialised Hanoi | Theta(2^n) work/output | O(n+1) call depth plus Theta(2^n) retained moves/references |

Two half-sized calls do not determine a time bound without combination work.
A constant combination yields linear full-tree work; linear combination can
produce n log n. Likewise, two overlapping calls can repeat work exponentially,
while memoisation changes how many distinct subproblems are solved.

The Java 21 JVM specification describes per-invocation frames and leaves internal
optimisation choices to implementations. The conservative source stack model
therefore counts recursive depth; Java tail position is not a contract for
constant stack space. JIT inlining or specialisation can affect generated code
and timings. No fixed maximum safe recursion depth or deliberate StackOverflowError
test is used. Balanced array examples avoid linear depth, but available memory
is not proved unbounded by a passing large-input test. Iteration can also require
an explicit stack/cache in other algorithms; only the selected scalar loops
here have constant local state.

## Automated Verification

Verification reported **2026-10-24**, using host **OpenJDK 21+35-2513**, passed
normal Maven tests and benchmark-profile clean package: **600 repository tests**,
zero failures/errors/skips, including **28 module tests** (prior Graphs base 572).

| Concrete test class | Executed tests | Independent evidence |
|---|---:|---|
| FactorialTest | 3 | BigInteger products over all 21 accepted inputs; rejected boundaries |
| FibonacciTest | 4 | BigInteger sequence through 92, all 31 naive inputs and separate work/overflow caps |
| PowerTest | 4 | 1,105 base/exponent cases against BigInteger.pow, large exact results and bounded-result MAX exponent |
| ArraySumTest | 5 | 1,093 small arrays / 27,064 valid slices; library long-sum oracle, full-int seeded data, 200,000-value large sum |
| PalindromeTest | 4 | Reversed-copy equality oracle, all 1,093 small arrays; million-value symmetry and first/last pair mismatch |
| GreatestCommonDivisorTest | 3 | BigInteger.gcd for 6,561 signed small pairs plus 5,000 seeded full-int pairs, zeros and extremes |
| HanoiTest | 5 | Legal peg simulation for 0..12 and 16 disks, counts/final positions, deterministic small plans and immutable output |

The seeded full-int array checks use 128 slices. Numeric/oracle tests establish
agreement on those domains, not an inference from comparing two potentially
similar variants alone. No fixed frame-byte measurement is claimed. No earlier
algorithm package/test or Maven dependency is changed; Arrays/Searching findings
remain open in the project review.

JMH discovers **8 methods**, all **24 short smoke configurations** complete,
and a separate probe checks all **24 default method/parameter combinations**,
three calls each, against independent exact numeric/long-sum references and
array nonmutation. JavaDoc passes `-Xdoclint:all,-missing`. These are functional/
harness checks, not author research results or a proof over untested inputs.

## JMH Design and Windows Evidence Protocol

All three classes use **average nanoseconds/op**, one thread, three 500 ms
warmups, five 500 ms measurements and two forks. A controlled cell has `Cnt=10`
iteration averages, not ten invocations. Source/actual runtime and errors must
remain with the results; do not reuse Graphs' microsecond label here.

| Class | Methods / parameters | Default cells | Included timed work |
|---|---|---:|---|
| FactorialBenchmark | recursive/tailRecursive/iterative; n=5/10/20 | 9 | Validated full factorial from mutable trial-state n; return long |
| FibonacciBenchmark | naive/memoised/iterative; n=10/20/30 | 9 | Full validated value; fresh memo allocation/fill per call, not a retained cache lookup |
| ArraySumBenchmark | recursive/iterative; sizes 100/10,000/1,000,000 | 6 | Full read-only long sum and validation; trial construction excluded |

Total is **24 current default configurations**. Array setup generates full-range
ints with SplittableRandom seed **20261025**; every mathematical sum remains in
long. No timed clone or conversion is performed. Small bounded factorial inputs,
repeated warm state, parameter profiling and JIT optimisation limit generalisation.
The Fibonacci variants intentionally have different work/allocation, so their
difference is not isolated recursion-call overhead. No benchmark is added merely
because a class exists: power/GCD/palindrome/Hanoi have mathematical/test evidence
without a distinct timing question selected here.

The [first research report](../17-benchmarking/results/recursion/README.md)
now completes all three Result/Interpretation fields with **24 means/errors**,
**240 measured** and **144 warmup averages**, completed in **3 min 35 sec**.
The author separately supplied source
**`fe370a1953c089a55f8851399acc42a59e3d37b7`**, PR #82's merge. Headers confirm
JDK 21.0.9/JMH 1.37, one thread, 3/5 x 500 ms and two forks. The SHA is author
checkout attribution, not embedded in JMH or verified by a supplied jar hash;
the attachment begins with a header and contains no full command or timestamp.

Loop means are lower on the selected fixtures. Ordinary/tail factorial intervals
overlap, without demonstrating elimination or equivalence; their helpers also
perform one more multiplication than the loop at these n values. Naive Fibonacci
has 177/21,891/2,692,537 logical helper calls at n=10/20/30, whereas memoisation
computes distinct states with a fresh per-call array. Balanced ArraySum still
does linear total work (2k-1 helper calls for nonempty k) despite logarithmic
depth. Those source counts are not profiling events or measured JVM frames.

Five cells have error at least 25% of their means: ArraySum loop size 1,000,000
(76.3%), recursive size 100 (35.9%), Factorial loop n=5/10 (43.8%/26.7%) and
Fibonacci memoised n=30 (75.5%). All values remain intact. Source work, depth,
allocation and elapsed means remain distinct; no exact ratio, memory footprint,
allocation rate, tail-call elimination, cache/CPU cause, significance or
asymptotic fit follows from this run. No new executable code, timed experiment
or CI status is introduced by recording it. Smoke stays separate.

The commands below retain the reproducible Windows protocol. After integrating
source changes, run from the local repository root:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd -Pbenchmark clean package
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar -l ".*recursion.*"
```

Expect BUILD SUCCESS and eight listed methods. The original build recorded above
reported 600 tests; the ordered review reports 710 in the later repository.
Use the actual Surefire totals for the revision under test. The explicit path is
the author's verified Java 21 installation; adapt elsewhere. Maven release=21
selects bytecode compatibility, not the Java executable launching JMH. Package
builds tests/jar; it does not execute the performance measurement. Linux uses
`bash mvnw` with a selected JDK 21 and the same jar/options.

Optional harness smoke, separate from measurement:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*recursion.*" -wi 1 -w 50ms -i 1 -r 50ms -f 1 -t 1 -foe true
```

For the complete initial 24-cell run, capture SHA **before launch and before
pulling later results changes**, then use the defaults:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*recursion.*" -foe true
```

Alternatively run each benchmark class separately (9/9/6 cells), retaining its
own SHA/header/completion marker. Send actual JDK/JMH settings, complete final
table/errors and printed fork iterations when available, plus known CPU/OS
metadata. Keep group parameter names (n/size), nanosecond units and source
identities clear; do not pool different algorithms or repeat runs as independent
samples without a stated design. Preserve evidence in Markdown without JSON/log
exports. Do not mark unknown source identity or unavailable profiler data known.

## Sources and Review Decisions

The supplied CLRS **Third Edition (2009)** was consulted: §§4.3–4.4, printed
pp. 83–92 (substitution and recursion-tree reasoning), and §15.1, pp. 360–369
(repeated subproblems, memoisation and iterative construction). These are
third-edition locations, not fourth-edition pagination. The seven APIs, numeric/
work/output caps, exact traces and bounded examples are independent repository
choices. A memoised Fibonacci demonstration does not mark the future Dynamic
Programming module complete.

- [Java 21 JVM specification, frames](https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-2.html#jvms-2.6): abstract invocation model; implementation optimisation is distinct from API guarantees.
- [Java 21 BigInteger](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/math/BigInteger.html): immutable exact-integer operations, supported-range and arithmetic policies; independent numeric test oracles.

All ten numbered documents link to this authoritative guide. Focused corrections
supply combination-work assumptions for recurrence tables, distinguish depth
from total calls, qualify iteration's space, and separate primitive overflow,
BigInteger bit cost and materialised output. Tail recursion remains a reasoning
technique with no promised Java constant-stack transformation. Existing earlier
benchmark findings/CI gaps stay tracked; local verification is not labelled CI.
