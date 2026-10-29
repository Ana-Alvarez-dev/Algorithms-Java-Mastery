# Dynamic Programming: Implementation and Benchmark Guide

## Ordered Documentation Review

The [2026-10-29 review](documentation-review.md), based on main
`bbab550e82a7768bdce789e646dd16621fa8d539` (PR #112 Backtracking-review merge),
checks all twelve notes, three production files, three test sources and four JMH
sources, plus the reused Recursion Fibonacci. Its
[contract/proof map](implementation-contracts.md)
derives cache/request ledgers, row versions, recurrence completeness, witness
ties and full public costs. Fresh Java 21 execution passes **710 repository
tests, including 22 here**; packaging/discovery are checked separately.
Original build, smoke/probe/doclint and author timings retain their dates.

## Scope and Design Before Implementation

Base: main `da3db882a70e3cc59ee05491ee76d8d8c11b97ae`, PR #89 integrating
[Backtracking research](../17-benchmarking/results/backtracking/README.md).
Module 15 connects all twelve study notes to state/recurrence design, base
cases, dependency order, traces, induction, work/space bounds and verification.
Production belongs to `org.anaalvarezdev.algorithms.dynamicprogramming`.

| Study notes | Executable responsibility / evidence |
|---|---|
| 01 fundamentals / 02 overlap | Per-call state reuse versus full grid preparation, complete cache keys |
| 03 substructure | Knapsack take/skip and LCS prefix replacement arguments; Fibonacci is value computation |
| 04 memo / 05 tabulation | Fresh dense sentinels, dependency order and rolling version invariants |
| 06 state / 07 recurrence | Formal prefix/remaining-capacity definitions, base cases and termination |
| 08 Fibonacci | New full table plus reused memo/rolling, exact BigInteger oracle/identities |
| 09 knapsack | Exact long optimum and immutable original-index plan; exhaustive subset/tie oracle |
| 10 LCS | UTF-16 optimum length and deterministic witness; independent subsequence oracle |
| 11 complexity / 12 mistakes | Pseudopolynomial capacity, dense setup, output work, benchmark limits |

Selected additions are full-table Fibonacci, exact 0/1 Knapsack value/plans,
and LCS length/one deterministic sequence. Existing
[Fibonacci.memoised/iterative](../../src/main/java/org/anaalvarezdev/algorithms/recursion/Fibonacci.java)
are reused rather than copied. Their earlier measurements retain their own
source and do not become measurements of the new table or new fixtures.
Rod cutting, matrix-chain order, edit distance, sparse caches, unbounded/
fractional knapsack, Hirschberg reconstruction and code-point/grapheme LCS
remain extensions. Module **16 - Testing** is next in the numbered roadmap.

## Contracts and Resource Domains

| API | Contract | Bound / policy |
|---|---|---|
| [FibonacciTabulation.value(n)](../../src/main/java/org/anaalvarezdev/algorithms/dynamicprogramming/FibonacciTabulation.java) | Exact long F(n), F(0)=0, F(1)=1, fresh full table | 0..92; F(93) exceeds long; invalid input fails before allocation |
| [Knapsack01.valueMemoised/valueTabulated/valueRolling](../../src/main/java/org/anaalvarezdev/algorithms/dynamicprogramming/Knapsack01.java)(weights,values,capacity) | Maximum total long value of a subset of positions with total weight <=capacity | Non-null equal-length int arrays, positive weights, nonnegative values; each position at most once; empty subset allowed |
| Knapsack01.select(...) | Same optimum plus total weight and immutable increasing original indices | On equal values, omit the currently highest item index; this picks the smallest numeric subset mask, not necessarily minimum weight |
| [LongestCommonSubsequence.lengthMemoised/lengthTabulated/lengthRolling](../../src/main/java/org/anaalvarezdev/algorithms/dynamicprogramming/LongestCommonSubsequence.java)(a,b) | Maximum common subsequence length over Java UTF-16 char units | Non-null Strings, each length <=512; empty side gives zero |
| LongestCommonSubsequence.sequence(a,b) | One deterministic maximum subsequence | On unequal trailing chars and equal scores, discard from the first prefix; not a lexicographically minimum promise |

Knapsack shares explicit limits across all variants: at most **256 items**,
capacity **0..10,000**, and **(items+1)*(capacity+1)<=1,000,000** table cells.
Compute that product in long before allocating. The shared grid cap applies
even to rolling storage; it defines this comparison domain rather than its
intrinsic memory requirement. Arrays are preserved, duplicates remain distinct
positions, and zero-valued items are allowed. With <=256 nonnegative int values,
the optimum is <=256*MAX_INT and fits long. A selected total weight fits capacity.

Null throws NullPointerException; invalid domains throw IllegalArgumentException.
Caps are educational work/output decisions, not fixed resource guarantees or
maximal mathematical domains. There is no persistent/static memo cache. Values
and plans are separate contracts; the benchmarks time values/lengths only.

LCS uses `String.length/charAt`, without normalisation or code-point conversion.
A supplementary character occupies two units; a returned UTF-16 subsequence may
contain a lone surrogate. This explicit sequence contract is not a grapheme-aware
text comparison. Reconstruction writes a char array backwards by index, preserving
the chosen code-unit order without surrogate-aware reversal transformations.

## Fibonacci: Same Recurrence, Different Retained State

```text
validate 0<=n<=92
table[0]=0; if n>=1: table[1]=1
for i=2..n: table[i]=table[i-1]+table[i-2]
return table[n]
```

Invariant before i: every earlier entry equals its mathematical Fibonacci
number. Base entries establish it; the recurrence preserves it; increasing i
terminates at n. The final value is exact because every computed entry is <=F(92).
For n=5 the table is [0,1,1,2,3,5]. The existing memoised implementation uses a
fresh -1-filled array, including base entries once reached; iterative keeps two
values. Both satisfy the same 0..92 result contract. Their state/initialisation
differs, so no isolated call/allocation cost follows by subtracting times.

## Knapsack: State, Choices and Reconstruction

Let K(i,c) be the optimum value using the first i items at capacity c.

```text
K(0,c)=0
if weight[i-1]>c: K(i,c)=K(i-1,c)
else K(i,c)=max(K(i-1,c), value[i-1]+K(i-1,c-weight[i-1]))
```

Each feasible subset either omits item i-1 or includes it exactly once. In the
latter case its residual subset uses only earlier items with reduced capacity.
Replacing a nonoptimal residual would improve the original, a contradiction.
The two cases cover every feasible subset and construct only feasible subsets;
induction on i proves the recurrence. Positive weights allow capacity zero and
avoid zero-weight edge cases in the selected rolling contract. Items decrease
on every recursive edge, so memo recursion terminates with O(n) logical depth.

Memoisation caches nonbase states in a fresh dense long matrix, initially -1.
Nonnegative optima make that sentinel distinct from all valid answers. Cache
keys include BOTH item count and remaining capacity, with the input arrays fixed
for that call. Lazy state expansion does not avoid dense allocation/filling.
Tabulation fills i increasing, c=0..C, using only the completed previous row.

Rolling storage uses one long[C+1] row. For each item, capacities run **downwards**
from C to its weight, so every read at c-weight still describes the previous item
prefix. Ascending order would reuse the same item and solve a different problem:
one item (weight=2,value=3), C=4 must return 3, not 6.

For weights [10,20,30], values [60,100,120], C=50, optima after successive rows
at capacity 50 are 60,160,220. Reconstruction starts at (3,50), takes item 2
(residual (2,20)), takes item 1 and omits item 0: indices [1,2], weight 50,
value 220. A density-greedy choice that takes item 0 first misses that optimum.

`select` retains the full table. At (i,c), take item i-1 only if its row value
strictly exceeds the previous row. Ties skip the largest remaining index, giving
the smallest numeric subset mask among optimal plans. It is deterministic and
does not promise minimum weight or lexicographically smallest index list.
Reverse the collected indices into increasing order and snapshot the list.
The reconstruction invariant preserves an optimal residual plus chosen items;
i decreases to zero, and total weight never exceeds original C.

## LCS: Prefix Dependencies and Rolling Rows

Let L(i,j) be the best common subsequence length of a[0..i), b[0..j).

```text
L(0,j)=L(i,0)=0
if a[i-1]==b[j-1]: L(i,j)=1+L(i-1,j-1)
else L(i,j)=max(L(i-1,j),L(i,j-1))
```

Matching trailing units can extend a best shorter-prefix subsequence; an optimum
can be chosen to end with that match. With unequal trailing units, no common
subsequence can use both as its final matched pair, so at least one trailing
unit is omitted. These cases exhaust the choices; induction on i+j gives exact
lengths. Memoised nonbase states use a fresh -1-filled dense int matrix; zero is
a valid cached optimum. Tabulation fills row-major after zero boundary entries.

Rolling storage makes the shorter String the columns and keeps one int row.
At inner position j, save old row[j] (previous row), use row[j-1] (new current
row), and preserve the old diagonal scalar before overwriting. Thus each
recurrence sees its required version; row[0] stays zero. Space is
O(min(m,n)+1), while both nonempty loop dimensions are still visited.

For a="ABC", b="AC", successive rows (including column zero) are
[0,0,0], [0,1,1], [0,1,1], [0,1,2]. The common subsequence "AC" is not
contiguous in a, distinguishing this problem from longest common substring.
For "ABCBDAB"/"BDCABA", length is four and the chosen trace yields "BCBA".
For "AB"/"BA", the documented upward-on-tie trace yields "A", not "B".

Sequence reconstruction uses the full table and walks backwards. Matches write
one unit and decrement both indices; otherwise follow a predecessor with the
same score, choosing the first-prefix discard on ties. Every step preserves the
remaining optimal length and decreases i+j; the filled char array is a maximum
subsequence of both. No lexicographic or code-point correctness is implied.

## Complexity and What Must Be Counted

Let n be item count, C numeric capacity, m/k String lengths, R expanded nonbase
memo states. Bounds include allocation/initialisation, not only recurrence work.

| Workflow | Full work | Auxiliary state / output |
|---|---|---|
| Full-table Fibonacci | O(n+1) word work | O(n+1) table |
| Reused memoised / iterative Fibonacci | O(n+1) word work | O(n+1) memo/depth / O(1) rolling |
| Knapsack dense memo | O((n+1)(C+1)+R), R<=n(C+1) | O((n+1)(C+1)) plus O(n) depth |
| Knapsack full table | O((n+1)(C+1)) | Same grid, primitive value output |
| Knapsack rolling | O(n+C+1+sum(max(0,C-weight[i]+1))) | O(C+1) row, no plan |
| Knapsack select | Table work plus O(n) backtracking | Full grid plus O(n) retained indices |
| LCS dense memo | O((m+1)(k+1)+R), R<=mk | Full grid plus O(m+k) depth |
| LCS full table | O((m+1)(k+1)) including empty boundaries/allocation | Full grid |
| LCS rolling length | O(mk+m+k+1) loose full-call bound | O(min(m,k)+1) row |
| LCS sequence | Full table plus O(m+k) trace and O(L) construction | Full grid plus O(L) retained UTF-16 output |

Knapsack's O(nC) usual bound is **pseudopolynomial**: C is a numeric value,
potentially exponential in its bit-length. A work cap does not change that fact.
Fibonacci long bounds assume primitive word arithmetic in 0..92; unbounded
BigInteger Fibonacci would need a bit-complexity analysis. LCS is itself an
optimisation problem; Fibonacci is value computation, not an optimal-choice proof.
Optimal substructure describes the selected optimisation recurrences; memoisation
also serves feasibility/counting recurrences without a maximisation objective.

Memo avoids re-expanding a nonbase state, but may look it up many times and
initialises every dense cell. Result reconstruction is not free after rolling
compression. DAG dependency order, well-founded recursion and a complete cache
key are necessary; overlapping calls alone do not make an arbitrary recurrence
correct or polynomial. Numeric states/output width and local transition work matter.

## JMH Questions and Recorded Evidence

Three templates use one thread, 3 warmup / 5 measurement iterations of 500 ms,
two forks and fresh per-invocation algorithm state. No cross-invocation cache.

| Template | Methods / fixtures | Defaults | Units |
|---|---|---:|---|
| FibonacciDpBenchmark | Existing memoised/iterative versus full table; n=20/40/90 | 9 | ns/op |
| KnapsackValueBenchmark | Dense memo/full table/rolling; n=16/64, capacity=32/128; LIGHT/TOO_HEAVY | 24 | us/op |
| LcsLengthBenchmark | Dense memo/full table/rolling; n=32/128, IDENTICAL/DISJOINT/SHIFTED/RANDOM | 24 | us/op |

There are **57 default cells**, nine methods. Preserve each row's units: combined
output mixes Fibonacci nanoseconds with other microseconds; N/A parameters are
inapplicable. No cross-class ranking or pooling is meaningful.

LIGHT uses unit weights and positive values cycling 1..7; TOO_HEAVY uses weights
C+1 and the same values. Trial shuffling seed 20261026 is outside timing. The
heavy memo follows only n nonbase states but still fills the dense cache; rolling
has no feasible item updates, while table fills all capacities. This comparison
does not isolate recursion or cache lookup. LCS IDENTICAL is all A on both sides,
DISJOINT A/B, SHIFTED alternating AB versus BA, RANDOM independent A..D from the
same seeded trial generator. Dense memo allocation remains even on one diagonal.

Expected values can be derived without elapsed times: F(20)=6,765,
F(40)=102,334,155, F(90)=2,880,067,194,370,816,120. Knapsack LIGHT gives 59
for n=16 at either capacity, and 182/253 for n=64 at C=32/128; TOO_HEAVY
gives zero. LCS IDENTICAL gives n, DISJOINT zero, SHIFTED n-1 at the even
default lengths. RANDOM lengths are independently reference-checked by the
original probe; no JMH return value or timing is inferred from these expectations.

Only exact values/lengths are timed. Plan/sequence construction is verified
separately; no output-generation throughput is claimed. Research Question,
Theoretical Expectation, Measured Work, Result and Interpretation belong in each
template. The
[first research report](../17-benchmarking/results/dynamic-programming/README.md)
now completes all three with **57 means/errors**, **570 measured** and **342
warmup averages**, completion **8 min 30 sec**. The author supplied the output
on **2026-10-26**, separately reporting source
**`d572651484f3ec6cffe03ad589bddd5a4a8fb8a3`**, PR #90's merge. Its tree matches
this reviewed implementation. Headers confirm JDK 21.0.9/JMH 1.37, the controlled
defaults and each template's units. The full command, execution timestamp,
CPU/power, generated-code inspection, jar hash and profiler data are unavailable.
Source is author checkout attribution, not embedded by JMH; previous OS/vendor/
locale setup is contextual rather than newly captured run metadata.

Eleven errors reach 25%: four Fibonacci/seven LCS, none knapsack. Table Fibonacci
n=20 reports 86.692 +/- 141.911 ns/op (163.7%); its literal `? 0` lower CI marker,
every fork/iteration and all other statistics remain intact. Twelve fully numeric
matching-method interval pairs overlap. No significance/equivalence, universal
ratio or exact n=20 table ranking follows from the point means.

Rolling has lower knapsack means throughout the selected grid, but memo/table
order varies and five pairs overlap. LCS memo means are lower than rolling on
IDENTICAL/SHIFTED at both n; large DISJOINT/RANDOM favour rolling means. The
report derives Fibonacci calls/additions, knapsack grid/expanded-state/update
counts and LCS diagonal/full-grid dependencies independently of elapsed scores.
Dense initialisation remains even when few states expand. Counts are not
profiler events, actual allocations, stack bytes or isolated elapsed-cost shares.
All timed methods omit witnesses. No fitted exponent or CPU/cache cause is
claimed. Earlier 694-test verification remains separate from this results-only
documentation update; smoke timings are excluded from research.

## Verification and Windows Protocol

Verification reported **2026-10-26**, using **OpenJDK 21+35-2513**, passes normal
Maven tests and benchmark-profile clean package: **694 repository tests**, zero
failures/errors/skips, including **22 new module tests** (prior Backtracking 672).

| Test class | Tests | Independent/reference evidence |
|---|---:|---|
| [FibonacciTabulationTest](../../src/test/java/org/anaalvarezdev/algorithms/dynamicprogramming/FibonacciTabulationTest.java) | 5 | All 93 supported values against BigInteger; exact Cassini products, reuse agreement, rejection and interleaved boundaries |
| [Knapsack01Test](../../src/test/java/org/anaalvarezdev/algorithms/dynamicprogramming/Knapsack01Test.java) | 9 | All 7,381 small arrays at nine capacities (66,429 cases) against complete subsets and numeric-mask ties; 100 seeded inputs, greedy/same-item counterexamples, long sums, exactly 1,000,000-cell boundary, item cap, immutable plans and preservation |
| [LongestCommonSubsequenceTest](../../src/test/java/org/anaalvarezdev/algorithms/dynamicprogramming/LongestCommonSubsequenceTest.java) | 8 | All 3,969 binary String pairs through length five against subsequence enumeration; 100 seeded pairs, witness legality/ties, UTF-16/surrogates, 512-unit boundary, symmetry/common-suffix identities and repeatability |

All **nine methods** are discovered. **57 short smoke cells** complete with one
50 ms warmup/measurement and one fork, preserving Fibonacci ns/op versus other
us/op units; those timings are not research scores. A separate probe checks
every default **three calls each (171 total)** against BigInteger, a sorted
unit-weight optimum and an independent matching-position LIS reference for LCS,
with repeatability and unchanged trial inputs. The latter enumerates matching
second-input positions in descending order per first-input unit; a strictly
increasing subsequence cannot reuse one first unit, and represents a common
subsequence. It is not the production prefix-grid recurrence.

The three production classes pass JavaDoc doclint excluding missing tags. Finite
tests and variant agreement complement the proof arguments, not replace them.
Local checks do not establish passing CI or fix the earlier tracked Arrays/
Searching findings. The existing CI gap remains separate from implementation.

After feature PR integration, from the Windows repository root:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd -Pbenchmark clean package
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar -l ".*dynamicprogramming.*"
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*dynamicprogramming.*" -foe true
```

The expected discovery list has **nine methods**. The original build reported
694 tests; the ordered review passes 710. Read the actual Surefire totals for
the checkout under test. Capture SHA before measurement
and before pulling later result documentation. The explicit executable selects
the actual runtime; Maven release=21 alone does not. Packaging builds/tests the
jar and does not launch the controlled run. For separate runs use patterns
`.*dynamicprogramming.FibonacciDpBenchmark.*` (9),
`.*dynamicprogramming.KnapsackValueBenchmark.*` (24), and
`.*dynamicprogramming.LcsLengthBenchmark.*` (24), keeping each source/header.
Send complete headers, fork warmup/measurement iterations, tables/errors and
completion, plus known OS/CPU metadata. Keep evidence in Markdown without new
JSON/log exports. Never silently convert or pool ns/op and us/op observations.

## Academic Sources and Review Decisions

Supplied CLRS **Third Edition (2009)**: Chapter 15 introduction, printed
pp.359-360 (value versus solution and design stages); §15.1 pp.363-369
(memoisation/tabulation, exercise 15.1-5 Fibonacci on p.370); §15.3 pp.378-389
(substructure, overlapping states and memo trade-offs); §15.4 pp.390-397
(prefix LCS recurrence, reconstruction and storage improvements). Figure 15.8
on p.395 was visually checked. §16.2 pp.425-427 gives the 0/1 versus fractional
contrast/counterexample; exercise 16.2-2 on p.427 asks for the O(nW) DP solution.
Do not misattribute knapsack to a complete Chapter 15 worked algorithm.

Contracts, caps, deterministic ties, UTF-16 interpretation, long accumulator,
one-row implementations and fixture grids are repository engineering choices.
The review corrects blanket claims that DP requires an optimisation objective,
always becomes polynomial, memo memory follows only reached states, zero is a
missing sentinel, LCS is not optimisation, or rolling storage retains a free
witness. Theory, tests, local harness checks and author timings remain distinct.
