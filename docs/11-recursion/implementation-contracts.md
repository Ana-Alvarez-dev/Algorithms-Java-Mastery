# Recursion: Applied Contracts, Proofs and Cost Models

This map was reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`6e8f87c1dcaae00d0d545d5ebb4ec539c48b33f7`, the PR #108 Graphs-review merge.
Read the [review findings and fresh Java evidence](documentation-review.md)
and the [implementation/research protocol](implementation-and-benchmark-guide.md)
with the ten conceptual notes. The seven classes are utility classes with
private constructors; no recursive framework or shared global cache is exposed.

## Public contracts and ownership

| API / source | Accepted input and normal result | Validation / ownership |
|---|---|---|
| [Factorial](../../src/main/java/org/anaalvarezdev/algorithms/recursion/Factorial.java): recursive, tailRecursive, iterative | n=0..20; exact long n!; 0!=1 | Other ints throw IllegalArgumentException before helper work |
| [Fibonacci](../../src/main/java/org/anaalvarezdev/algorithms/recursion/Fibonacci.java): naive | n=0..30; F(0)=0, F(1)=1 | 30 is a work cap, not the long overflow boundary; invalid input throws IllegalArgumentException |
| Fibonacci: memoised, iterative | n=0..92; exact long F(n) | Invalid input throws IllegalArgumentException; memo array is fresh per call and never returned |
| [Power](../../src/main/java/org/anaalvarezdev/algorithms/recursion/Power.java): recursive, iterative | Non-null BigInteger, nonnegative int exponent; exact power when completed, including 0^0=1 | Null throws NullPointerException first; negative exponent throws IllegalArgumentException; no promise of feasible huge output |
| [ArraySum](../../src/main/java/org/anaalvarezdev/algorithms/recursion/ArraySum.java): recursive, iterative, full/slice overloads | Non-null int[], valid half-open [from,to); exact long sum; empty=0 | Null throws NullPointerException; require 0<=from<=to<=length, otherwise IndexOutOfBoundsException; no mutation or slice copies |
| [Palindrome](../../src/main/java/org/anaalvarezdev/algorithms/recursion/Palindrome.java): recursive, iterative | Non-null int[]; exact mirrored-value symmetry, empty/singleton true | Null throws NullPointerException; no mutation; no text, Unicode or normalisation policy |
| [GreatestCommonDivisor](../../src/main/java/org/anaalvarezdev/algorithms/recursion/GreatestCommonDivisor.java): recursive, iterative | Every signed int pair; nonnegative long gcd; gcd(0,0)=0 | Magnitudes normalised after widening; MIN_VALUE magnitude 2147483648 is supported |
| [Hanoi](../../src/main/java/org/anaalvarezdev/algorithms/recursion/Hanoi.java): moves | disks=0..16; optimal three-peg List<Move>, source 0, target 2, spare 1; smallest disk 1 | Invalid int throws IllegalArgumentException; immutable list of scalar records, zero disks empty |

Hanoi.Move is a public scalar record. Its constructor itself does not validate
peg/disk legality; the **moves factory's output** has the plan guarantees. No
caller-supplied plan is accepted or validated by this API. Unmodifiable output
and per-call local state do not imply general thread safety for concurrently
mutated caller arrays: ArraySum and Palindrome require stable input while read.

The numeric limits are deliberate: 20!=2432902008176640000 and
F(92)=7540113804746346429 fit long; 21! and F(93) do not. The Fibonacci loop
never computes the unused overflowing next value after F(92). Naive n=31 is
rejected despite its mathematical answer fitting long. Hanoi's 16 cap bounds
materialised output, independently of the int type's range.

For any int[] or slice, k<=2^31-1 and each element has magnitude at most 2^31.
Any subset/partial sum has magnitude at most (2^31-1)*2^31 < 2^62, hence fits
long. This covers both balanced combinations and the loop's partial totals.
It does not change the older int-returning Arrays aggregation contract or its
historical finding.

## Helper specifications and correctness

Mathematical termination uses a well-founded measure and finite local work.
Normal-result correctness remains distinct from having enough heap/stack or
BigInteger supported range on an actual JVM. The following arguments describe
the source algorithms; they do not prove the Java runtime cannot fail.

### Factorial: ordinary and accumulator forms

For product(n), precondition 0<=n<=20 and postcondition result=n! suffice.
At n=0 return 1. For n>0, induction gives product(n-1)=(n-1)! and multiplying
by n gives n!. The measure n decreases to zero. All partial products lie
within the validated exact long range.

The stronger accumulator contract is **accumulate(n,a)=a*n!**, for the reachable
states whose products fit long. Relative to the public input N, the invariant
is a*n!=N!, initially a=1,n=N. Updating (n,a) to (n-1,a*n) preserves it; at
zero return a=N!. Tail position changes where multiplication occurs, not the
portable stack guarantee. The loop invariant after processing factor f is
result=f! (initial result=1=1!, before factor 2).

For N=3, product visits **3 -> 2 -> 1 -> 0**, then returns
**1 -> 1 -> 2 -> 6**. Accumulate visits (3,1),(2,3),(1,6),(0,6).
Each helper has N+1 calls/depth and N products, excluding the validation wrapper.
The loop has max(N-1,0) products. A diagram ending at 1 describes a different
base convention; it is not an exact trace of these helpers.

### Fibonacci: repeated subproblems and a private per-call cache

Naive branch(n) returns n for 0/1, otherwise branch(n-1)+branch(n-2). Strong
induction on n proves the defining recurrence and termination. Its **total**
helper count satisfies C(0)=C(1)=1 and C(n)=C(n-1)+C(n-2)+1, so
C(n)=2F(n+1)-1. At n=4, F(4)=3 and C(4)=9. At n=10/20/30, counts are
177/21891/2692537; they are source counts, not profiling observations.

For cached(k,memo), every nonnegative entry equals its exact Fibonacci value;
-1 denotes unknown. A hit returns a correct value. A miss computes only smaller
indices, so induction proves the stored answer correct. All legitimate results
are nonnegative, including zero, so the sentinel does not collide. A new
n+1-element array is allocated and filled on **every public call**, including
base inputs. Descendants share it; independent public calls do not.

For n>=2 all n+1 indices are eventually stored, with each state expanded once
and repeated requests returning hits. There are 2n-1 cached helper invocations
including hits; n=0/1 each has one. Maximum active helper depth for both
recursive variants is n for n>=1, one for n=0: exponential total naive calls
do not mean exponential live frames. The loop maintains previous=F(i-2),
current=F(i-1) before update i and returns F(n), with a special n=0 result.

### Power: one half-result and a bit-processing invariant

For exponent e=0 return ONE, for e=1 return base. Otherwise q=floor(e/2)<e.
Induction gives half=base^q; square once and multiply by base exactly when e
is odd. This yields base^(2q) or base^(2q+1). There is **one recursive call**,
not two independent calls for the same half. Trace 3^5: exponents 5->2->1,
values 3 -> 9 -> 81*3=243.

The loop invariant is result * factor^remaining = originalBase^originalExponent.
An odd-bit multiply followed by halving remaining and squaring factor preserves
it. On the last iteration remaining becomes zero; no further factor is needed,
so the source deliberately skips the unused square. It returns the invariant's
result. For 0^0 the initial ONE is returned by the explicit convention.

For e>0, recursive source multiplication count is floor(log2(e))+popcount(e)-1;
the loop has floor(log2(e))+popcount(e), including multiplication of the
accumulator initially ONE. For e=0 both have zero. Thus equal mathematical
results need not mean identical source operations. These counts do not measure
library special cases or emitted machine instructions.

For |base|>=2 and e>0, output magnitude needs Theta(e*log2(|base|)) bits.
O(1+log(e+1)) arithmetic operations/reference frames do not imply logarithmic
bit-time or constant numeric storage. Count growing operands, retained result
and library workspace separately; no multiplication implementation or exact
heap bound is assumed. MAX_VALUE exponent tests use bases -1,0,1 with bounded
results, rather than establishing feasibility for base 2 at that exponent.

### ArraySum and Palindrome: different meanings for half ranges

ArraySum.divide(values,lo,hi) returns the sum of exactly [lo,hi). An empty
range returns zero and a singleton its widened value. Otherwise mid splits
into disjoint covering halves; induction and exact long addition establish
the result. The measure hi-lo decreases; no array is copied. Example
[MAX_INT,MAX_INT] returns 4294967294L.

For a nonempty range k, a full binary tree has k singleton leaves and k-1
internal nodes: **2k-1 helper calls**. Maximum active helper depth is
ceil(log2(k))+1; k=0 uses one. For k=4 the helper ranges are [0,4),[0,2),[0,1),
[1,2),[2,4),[2,3),[3,4), with seven calls and three active levels.

Palindrome.pairs(values,lo,hi) instead checks the conjunction of comparisons
values[i]==values[length-1-i] for **pair indices** i in [lo,hi). The public
range is [0,floor(length/2)); the odd centre needs no comparison. Empty=true;
a singleton performs its one pair comparison; larger ranges split that set.
Conjunction is correct even when false skips the right range. It does not test
whether the left and right array halves are separately palindromes.

For [1,2,2,1], pair indices 0 and 1 compare (0,3) and (1,2). First-pair
mismatch recursively descends the balanced pair tree before returning false:
Theta(1+log(p+1)) work for p=floor(length/2). The loop detects it in constant
work. A true palindrome, or last-pair mismatch, explores all p comparisons
and (for p>0) 2p-1 helper calls. Empty/singleton inputs use one helper call.

### Euclidean GCD: normalisation and decreasing remainder

First cast each int to long, then take abs. Reversing those steps would lose
the magnitude of Integer.MIN_VALUE in int. Nonnegative a,b have the same common
divisors as b,a%b. At b=0 return a, including (0,0)->0 by contract. Otherwise
0<=a%b<b establishes a decreasing second argument. The loop makes the same
state transition with constant scalar state. For (-54,24), normalise to
(54,24)->(24,6)->(6,0), result 6.

After at most an initial swap-like remainder step, in every two steps a
positive magnitude at least halves. For M=max magnitudes this gives
O(1+log(M+1)) word work/depth. The actual int domain bounds M by 2^31; this is
not a bit-complexity result for arbitrary-precision Euclid.

### Hanoi: legal transfers, optimal output and storage

The helper transfer(n,from,to,spare,output) appends a legal transfer of the n
smallest relevant disks between distinct pegs, using the spare, with larger
disks acting as legal bases. At n=0 append nothing. Inductively move n-1 to the
spare, append the largest remaining disk move, then move n-1 onto it. The
measure n decreases on both calls, and peg roles change according to the
helper contract. The output prefix is preserved, not overwritten.

Moving the largest disk from source to target requires clearing the smaller
stack from both pegs; transferring that stack before/after needs at least the
corresponding smaller three-peg solution lengths. The standard lower bound
L(n)>=2L(n-1)+1, L(0)=0, is met by this construction: **2^n-1 moves**, optimal
for this three-peg problem. At n=2: disk1 0->1, disk2 0->2, disk1 1->2.

The source performs **2^(n+1)-1 helper invocations**, including zero-disk leaves,
and has n+1 active helper depth. At n=16 it retains 65535 Move records; the
immutable List.copyOf result adds a linear reference copy. Linear stack depth
is not linear total space; a streaming alternative is not implemented here.

## Cost model summary

Word bounds below describe algorithmic families subject to the validated
finite domains. They do not extend primitive arithmetic or workload caps.
k=slice length, p=mirrored pair count, e=exponent, M=max GCD magnitude.
For zero-size inputs retain the constant base/validation work.

| Operation | Source work | Source depth / additional state |
|---|---|---|
| Factorial ordinary / tail | Theta(n+1) word work | n+1 helper depth; bounded scalar frames |
| Factorial loop | Theta(n+1) word work | O(1) scalar state |
| Fibonacci naive | Theta(phi^n), looser O(2^n) | O(n+1) depth; no memo |
| Fibonacci memoised | Theta(n+1), including allocation/fill/hits | O(n+1) memo plus O(n+1) depth |
| Fibonacci iterative | Theta(n+1) word work | O(1) scalar state |
| Power recursive / loop | O(1+log(e+1)) multiplications, variable bit cost | O(1+log(e+1)) helper frames / O(1) loop references, plus numeric objects/workspace |
| ArraySum recursive | Theta(k+1) word work | O(1+log(k+1)) helper depth, no slice copying |
| ArraySum loop | Theta(k+1) word work | O(1) scalar state |
| Palindrome recursive | Theta(p+1) worst; Theta(1+log(p+1)) first mismatch | O(1+log(p+1)) depth, no array copies |
| Palindrome loop | Theta(p+1) worst; Theta(1) first mismatch | O(1) scalar state |
| GCD recursive / loop | O(1+log(M+1)) word work | Same-order recursive depth / O(1) scalar state |
| Hanoi.moves | Theta(2^n) construction/copy work | O(n+1) depth plus Theta(2^n) moves/references |

These frame counts exclude wrappers/validation and are logical source models,
not physical JVM frame counts, bytes or a safe recursion limit. Shared references
do not duplicate their arrays per recursive call. Power's constant number of
loop reference variables is not constant bit-space; Hanoi's output is retained.
A recursion tree count yields time only after its local work is specified.

## Java test evidence

The [fresh review](documentation-review.md) records **710 repository tests**,
zero failures/errors/skips; **28** belong to this module. Seven concrete test
classes plus [SmallArrays](../../src/test/java/org/anaalvarezdev/algorithms/recursion/SmallArrays.java)
make eight test sources. Cases inside loops are not separate JUnit invocations.

| Tests | Existing independent checks |
|---|---|
| [FactorialTest](../../src/test/java/org/anaalvarezdev/algorithms/recursion/FactorialTest.java), 3 | All 21 accepted inputs against BigInteger products, zero/one/20!, invalid boundaries |
| [FibonacciTest](../../src/test/java/org/anaalvarezdev/algorithms/recursion/FibonacciTest.java), 4 | BigInteger sequence over 0..92 and all naive 0..30, separate work/overflow caps |
| [PowerTest](../../src/test/java/org/anaalvarezdev/algorithms/recursion/PowerTest.java), 4 | 17 bases times 65 exponents = 1105 cases against BigInteger.pow; large exact results; MAX exponent only for bounded-result bases; null/negative |
| [ArraySumTest](../../src/test/java/org/anaalvarezdev/algorithms/recursion/ArraySumTest.java), 5 | 1093 small arrays, every valid slice (27064), long-stream oracle, 128 seeded slices, extreme values, 200000-element sum, ownership/validation |
| [PalindromeTest](../../src/test/java/org/anaalvarezdev/algorithms/recursion/PalindromeTest.java), 4 | Reversed-copy equality on 1093 arrays, empty/odd/even/extremes, million elements and first/last mismatch, no mutation/null |
| [GreatestCommonDivisorTest](../../src/test/java/org/anaalvarezdev/algorithms/recursion/GreatestCommonDivisorTest.java), 3 | BigInteger.gcd on 6561 signed small pairs and 5000 seeded full-int pairs; zeros/signs/MIN_VALUE |
| [HanoiTest](../../src/test/java/org/anaalvarezdev/algorithms/recursion/HanoiTest.java), 5 | Legal peg simulation for n=0..12 and 16; exact counts/final peg, small plans, invalid inputs and immutable independent results |

No new tests, deliberate stack overflow, frame-byte measurement or coverage
percentage is introduced. Mathematical proofs and independent bounded tests
support different parts of the evidence chain.

## JMH: registration, timed work and historical evidence

| Benchmark source | Registered methods / parameters | Timed work and boundary |
|---|---|---|
| [FactorialBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/recursion/FactorialBenchmark.java) | recursive/tailRecursive/iterative; n=5,10,20; 9 cells | Full validated result; helper/loop product counts differ |
| [FibonacciBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/recursion/FibonacciBenchmark.java) | naive/memoised/iterative; n=10,20,30; 9 cells | Full validated value; NEW memo allocation/fill on each call, not a warm retained lookup |
| [ArraySumBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/recursion/ArraySumBenchmark.java) | recursive/iterative; size=100,10000,1000000; 6 cells | Full long sum/validation over read-only trial array, no timed clone; construction excluded |

Fresh Java 21 discovery lists all eight methods and each three-value grid.
This is **registration/parameter verification**, not a new 24-cell timing run.
The fixtures use one thread, avgt, ns/op, three 500 ms warmups, five 500 ms
measurements, two forks; ArraySum uses full-range ints and seed 20261025.
The other four utility classes have no selected JMH question here.

The [first author report](../17-benchmarking/results/recursion/README.md)
retains all 24 means/errors, 240 measurement and 144 warmup iteration averages.
Source attribution is author-reported `fe370a1953c089a55f8851399acc42a59e3d37b7`
(PR #82 merge), Oracle JDK 21.0.9/JMH 1.37, compiler blackholes, 3 min 35 sec.
No supplied jar hash/full launch command/timestamp is recovered by this review.

Lower loop means on these fixtures do not isolate call cost, and overlapping
ordinary/tail intervals do not establish equivalence or tail-call elimination.
Five historical errors reach 25% of their means; all remain intact. Work counts,
depth, allocated state and timing uncertainty stay separate. This pass adds no
profiling, CPU/cache explanation, significance claim or asymptotic fit. The
fresh Corretto functional run is separate from the author's Oracle experiment.
