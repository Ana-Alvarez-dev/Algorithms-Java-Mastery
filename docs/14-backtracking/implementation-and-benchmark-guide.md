# Backtracking: Implementation and Benchmark Guide

## Selected Scope and Evidence Chain

This contribution starts from main
**`605c299f11944e0234c1651c21d4191f07b3432f`**, the merge of Greedy research
through PR #87. Its [40 recorded configurations](../17-benchmarking/results/greedy/README.md)
keep their own source/runtime and are not reused as measurements here.

Package `org.anaalvarezdev.algorithms.backtracking` mirrors main/test/JMH roots.
There are **four production files**, **27 executed module tests**, and **eight
JMH methods in four classes**, with **46 default configurations**. Earlier
production/tests/POM and empirical reports are unchanged.

| Documents | Selected executable responsibility | Evidence |
|---|---|---|
| 01 fundamentals / 02 trees | Choose/explore/restore or value-parameter branching; decision versus full traversal | Contracts, traces and node/output ledgers below |
| 03 candidates / 04 constraints | Duplicate-aware positions/frequencies; increasing combinations; queen conflicts | Completeness, uniqueness, invariants and restoration checks |
| 05 pruning | Remaining-cardinality and signed suffix feasibility bounds | Soundness arguments, targeted controls and reachable-set oracle |
| 06 permutations/combinations | Immutable materialisation plus separate exact counts | Index-permutation/bitmask oracles and multinomial/binomial references |
| 07 N-Queens | Ordinary placements/counts, flags versus masks | Full column-permutation oracle, published counts and symmetry checks |
| 08 analysis / 09 mistakes | Node work, constraint cost, output copies, depth and bounded domains | Independent tests/probe, smoke and author measurement protocol |

Sudoku, graph colouring, maze solvers, branch-and-bound optimisers, streaming/
visitor APIs, symmetry-reduced queens, arbitrary-size masks and production
memoisation/tabulation remain extensions. The next module is **15 - Dynamic
Programming**; test-only reference algorithms do not mark it implemented.

## Contracts, Limits and Ownership

| API | Accepted domain / result | Policy |
|---|---|---|
| [Permutations.unique](../../src/main/java/org/anaalvarezdev/algorithms/backtracking/Permutations.java) | Non-null int[], length 0..9 -> List of distinct value permutations | Equal values indistinguishable; ascending lexicographic rows; input preserved |
| Permutations.countUnique / countUsingFrequencies | Length 0..10 -> exact long count of the same unique value permutations | Full search, not a factorial formula or memo cache; no rows materialised |
| [Combinations.choose](../../src/main/java/org/anaalvarezdev/algorithms/backtracking/Combinations.java) | 0<=k<=n<=18 -> size-k subsets of **0..n-1** | Increasing rows and lexicographic outer order; universe has distinct values |
| Combinations.countWithPruning / countWithoutPruning | Same n/k domain -> exact long C(n,k) | Same result, different treatment of infeasible remaining-cardinality branches |
| [NQueens.solutions](../../src/main/java/org/anaalvarezdev/algorithms/backtracking/NQueens.java) | n=0..12 -> ordinary placements as zero-based column-per-row lists | Lexicographic; rotations/reflections counted separately, no symmetry quotient |
| NQueens.countWithFlags / countWithBitMasks | Same n domain -> exact long number of ordinary placements | No boards materialised; masks validated before shifts |
| [SubsetSum.exists / existsWithoutBounds](../../src/main/java/org/anaalvarezdev/algorithms/backtracking/SubsetSum.java) | Non-null signed int[], length 0..24, any long target -> boolean exact feasibility | Each position used at most once; duplicates remain separate positions; empty subset allowed; include-first short-circuit |

Null arrays throw NullPointerException. Inputs exceeding stated caps or invalid
n/k arguments throw IllegalArgumentException **before search/materialisation**.
These are explicit educational work/output limits, not maximal mathematical
domains or a promise of a fixed elapsed-time/memory budget. Enumeration and
counting limits differ for permutations because retained output can dominate.

Empty permutations/choose(0,0)/NQueens(0) each have **one empty solution**;
their count is one. choose(n,0) also has one empty row. Queens n=2/3 has no
solutions. Empty subset sum is true exactly at target zero; zero is feasible
for every array by excluding all positions.

All materialised outer lists and rows are immutable snapshots. Arrays supplied
by callers are preserved. No mutable static search state/cache is retained.
Primitive counters fit long throughout accepted domains: at most 10! permutation
leaves, C(18,9)=48,620 combinations, and no more than 12! column permutations
before diagonal rejection. These are counting bounds, not claims that every
candidate satisfies constraints.

Subset sums and suffix extrema are exact in long: with at most 24 ints, absolute
magnitude is below 2^36. Prefix sum plus a disjoint suffix bound has the same
bound. Comparing target with those bounds avoids overflowing `target-sum` for
Long.MIN_VALUE/MAX_VALUE. This new bounded signed API does not repair or relabel
the old Arrays int-aggregation benchmark finding.

## Permutations: Completeness Without Duplicate Values

```text
sort a private input copy
POSITIONS(depth):
  complete -> count one; optionally snapshot the whole path
  for each sorted position i:
    skip used i
    skip equal predecessor if that predecessor is not currently used
    mark used; write path[depth]; recurse; unmark
FREQUENCIES(remaining):
  for each distinct value with positive remaining multiplicity:
    decrement; recurse; increment
```

The used-position/path-prefix invariant preserves the input multiset. Requiring
equal copies to be used left-to-right chooses one representative index sequence
for every value sequence; any remaining value still has its first unused copy
available. Thus no distinct value permutation is lost, and equivalent index
histories cannot produce duplicates. Sorted choices establish lexicographic
order. In the frequency variant each branch chooses one value, not one of its
indistinguishable copies; remaining multiplicities describe exactly the same
value prefixes. Both restore mutable constraints before the next sibling.

For input [2,1,1], sorted copies are [1a,1b,2]. Choosing 1a yields [1,1,2] and
[1,2,1]; root choice 1b is skipped while 1a is unused. Choosing 2 yields
[2,1,1]. The three value rows match multiplicities (2,1): **3!/(2!*1!)=3**.
The empty prefix at n=0 is already complete and contributes one empty row.

Only path indices below current depth form the logical state. Deeper slots may
contain stale values and are overwritten before use; clearing every int slot
is unnecessary. Used flags/multiplicities **must** be restored. Leaves snapshot
the complete path before later choices overwrite it. Finite depth increases to n.

## Combinations: Increasing Choices and Remaining Capacity

```text
CHOOSE(from,remaining):
  remaining=0 -> one complete row/count
  for candidate from from through n-remaining:
    append candidate; recurse(candidate+1,remaining-1)
```

Strictly increasing indices give each k-subset one path, removing permutations
of the same selection. At a state, a candidate larger than n-remaining leaves
too few later values to finish; skipping it is sound. Every feasible increasing
tuple has its next value inside the retained bound, proving completeness by
induction. The unpruned count instead loops through n-1, retaining dead branches
until their candidate range is empty. Both return the same count.

For n=4,k=2 the ordered rows are [0,1],[0,2],[0,3],[1,2],[1,3],[2,3]. Root
candidate 3 cannot supply two values and is safely omitted. k=0 is complete
without selecting an item. An integer `from` and decreasing remaining length
give termination; path slots beyond depth are not live state.

Logical call counts can be derived independently of wall time:

- Unpruned: sum(C(n,d), d=0..k), including infeasible prefixes.
- Pruned: sum(C(n-k+d,d), d=0..k)=C(n+1,k), including root and leaves.
- k=n: **n+1 versus 2^n calls**, despite only one complete result.

| Default n / k | Result C(n,k) | Pruned calls | Unpruned calls |
|---|---:|---:|---:|
| 12 / 2 | 66 | 78 | 79 |
| 12 / 6 | 924 | 1,716 | 2,510 |
| 12 / 12 | 1 | 13 | 4,096 |
| 18 / 2 | 153 | 171 | 172 |
| 18 / 9 | 48,620 | 92,378 | 155,382 |
| 18 / 18 | 1 | 19 | 262,144 |

These count source helper invocations, not JVM frames/instructions or profiler
events. Loop and branch implementation effects remain part of future timing.

## N-Queens: Conflict State and Available Bits

```text
FLAGS(row):
  for each column:
    reject used column or diagonals (row-column+n-1, row+column)
    mark constraints; recurse to next row; unmark
MASKS(columns,left,right):
  available = full & ~(columns | left | right)
  extract each lowest available bit
  recurse with new column and diagonal attacks shifted to next row
```

At row r, the prefix has one queen in every earlier row, with distinct columns
and diagonals. Flag tests reject exactly attacks that cannot be repaired by
adding later queens. Every legal next column remains available; marking,
recursion and restoration therefore enumerate all legal completions once.
Rows increase to n, proving termination. On the 4-board, prefix columns 1,3,0
can be completed by 2, yielding [1,3,0,2]; its reflected placement [2,0,3,1]
is counted separately. No early first-solution exit or symmetry reduction occurs.

The mask state expresses occupied columns and attacks in the current row;
left/right shifts update attacks for the next row. Masking with full discards
outside-board bits. Available bits are precisely legal children. Primitive
arguments carry child state by value, so the parent does not require flag undo.
Lowest-bit extraction gives the same ascending column order. n<=12 is validated
before `1L<<n`; no arbitrary-n or unbounded mask-width claim is made.

Flags loop over n candidates at each nonterminal valid prefix, including those
rejected immediately. Masks generate only available children. Both traverse the
same valid-prefix tree but perform different candidate work and allocations.
Flags also allocate/write a private path even in count-only calls. Neither
timing isolates a single bit operation. Both have O(n) **logical depth**, so
fixed-size masks do not make total recursive auxiliary space constant.

Published ordinary counts for n=0..12 are
**1,1,0,0,2,10,4,40,92,352,724,2680,14200**; see OEIS in Sources below.
The tests additionally derive all placements through n=9 by checking complete
column permutations without partial diagonal pruning. This is counting all
placements, not the separate task of finding one board or completing preplaced
queens.

## Signed Subset Sum: A Safe Feasibility Bound

```text
minimum[i] = sum of negative values in suffix i
maximum[i] = sum of positive values in suffix i
SEARCH(i,sum):
  sum=target -> true (exclude all remaining items)
  if target outside [sum+minimum[i],sum+maximum[i]] -> false
  no items left -> false
  return SEARCH(i+1,sum+value[i]) OR SEARCH(i+1,sum)
```

Any suffix subset adds at least the sum of all its negative items and at most
the sum of all positive items. The interval therefore contains every possible
completion; rejecting an outside target is sound. Being inside is not sufficient
for reachability: parity/gaps can remain. Include/exclude partitions all subsets
of positions, proving completeness by induction, with strictly increasing index.
An early exact prefix sum is a witness completed by excluding every suffix item.

For [8,-9], target -1, the root completion interval is [-9,8]. Include 8, whose
suffix interval becomes [-1,8], then include -9 to succeed. The generic rule
`sum>target -> reject` would be unsound, even at the root. The CLRS positive-
input optimisation must not be transplanted unchanged into this signed domain.
sum/index are primitive values, while suffix arrays are read-only, so early
success needs no shared-state rollback. No witness rows or memo table are built.

For default even n, all-2 fixtures have these **logical source call counts**:

| Scenario | Target / expected decision | Bounded calls | Without-bound calls |
|---|---|---:|---:|
| RANGE_MISS | 2n+1 / false | 1 | 2^(n+1)-1 |
| PARITY_MISS | 2n-1 / false | 2n+1 | 2^(n+1)-1 |
| PRESENT | n / true after first n/2 items | n/2+1 | n/2+1 |

In PARITY_MISS, every excluded 2 makes the upper bound too small; the include-
all chain overshoots. SIGNED_MISS instead alternates -2,+2 with target 1; all
sums are even, but no parity shortcut is implemented. Bounds are generic
intervals and may retain much of that tree. Bounded preparation still builds
two suffix arrays before even an early hit; the call-count row is not its full
work or elapsed time. Targeted easy cases do not solve general subset sum cheaply.

## Complexity: Work, Output and Depth

Let P be unique value permutations, I the nonterminal unique-prefix nodes,
u distinct input values, C=C(n,k), Q ordinary queen solutions, and V visited
states under the chosen constraints. Bounds use primitive word work within
the validated domains, not an extension of 64-bit masks to arbitrary n.

| Operation | Work model | Additional state/output |
|---|---|---|
| Position permutation count | Clone/sort plus O(n*I+P+1); loose O(n*n!) worst search bound | O(n) private arrays/logical depth |
| Frequency permutation count | Clone/sort/compression plus O(u*I+P+1) | O(n) arrays and depth, not a memo table |
| Unique permutation materialisation | Position search plus Omega((n+1)*P) row/container work | O(n) working state plus O((n+1)*P) output |
| Pruned/unpruned combination count | O(visited prefix nodes); exact call ledgers above, at most O(2^n) | O(k+1) logical depth/local state |
| Combination materialisation | Search plus Omega((k+1)*C) output copying | O(k+1) working path/depth plus O((k+1)*C) output |
| Queen flags count | O(n*V) candidate checks; loose O(n*n!) bound | O(n) arrays and logical depth |
| Queen mask count | O(V) word/candidate work; loose O(n!) bound when mask fits | O(n) logical depth with constant-size primitive state per frame |
| Queen materialisation | Flag search plus Omega((n+1)*Q) output | O(n) working state plus O((n+1)*Q) output |
| Signed subset decision | O(n) bounds preparation plus O(visited nodes); O(2^n) worst search | O(n) depth and, for bounded, suffix arrays; boolean output |

For distinct permutations P=n!, but duplicates give P=n!/product(multiplicity!).
Output counts alone omit candidate scans: all-equal positions visit a single
chain but still inspect n positions at every depth, while the frequency loop
has u=1. Materialisation copies whole rows; plain O(n!) or O(C(n,k)) labels
omit that work. Peak DFS state is not the sum of all visited nodes; retained
outputs and explicit memo sets would add storage that count-only methods omit.

A uniform tree with branching bound b has at most sum(b^i,i=0..d) nodes:
O(b^d) for fixed b>1, O(d+1) for b=1, a root for b=0. Multiply by actual local
work and add output cost; neither b nor d alone determines complete elapsed time.
Candidate reordering can change first-hit decision work, but does not by itself
remove valid prefixes from a complete count with unchanged constraints/variable
order. Pruning must be proved sound and need not help every input equally.

## Automated Verification

Verification reported **2026-10-26**, with **OpenJDK 21+35-2513**, passed normal
Maven tests and benchmark-profile clean package: **672 repository tests**, zero
failures/errors/skips, including **27 module tests** (prior Greedy 645).

| Test class | Tests | Independent evidence |
|---|---:|---|
| [PermutationsTest](../../src/test/java/org/anaalvarezdev/algorithms/backtracking/PermutationsTest.java) | 7 | All 120 multisets of length 0..7 over {-1,0,1}, against complete index permutations then value deduplication; multinomial BigInteger counts; 100 seeded arrays, extremes, caps, 10!, immutable rows/nonmutation/restoration |
| [CombinationsTest](../../src/test/java/org/anaalvarezdev/algorithms/backtracking/CombinationsTest.java) | 6 | All n<=12/k against bitmask subsets; BigInteger binomials at n=18, largest 48,620-row output, Pascal identity, domain/order/ownership checks |
| [NQueensTest](../../src/test/java/org/anaalvarezdev/algorithms/backtracking/NQueensTest.java) | 7 | All boards through 9 from full column permutations; published counts through 12; 14,200-row boundary, reflection inclusion, deep ownership, caps and repeatability |
| [SubsetSumTest](../../src/test/java/org/anaalvarezdev/algorithms/backtracking/SubsetSumTest.java) | 7 | All 1,093 signed arrays length 0..6 and targets -8..8 against iterative reachable sums; 100 seeded full-int arrays, extreme long targets, signed/overflow examples, reversal/negation and no mutation |

Finite tests and cross-variant agreement complement the arguments above; they
are not proofs for every array. No deliberate stack overflow or fixed safe
Java-frame budget is used. All caps are validated explicitly before recursion.

All **eight methods** are discovered, and **46 short smoke cells** complete
with one 50 ms warmup/measurement and one fork. Their timings are not research.
A separate probe checks **all 46 defaults, three calls each**, against
multinomial/binomial counts, OEIS values and independent reachable sets, with
repeatability and unchanged trial arrays. Package JavaDoc passes doclint
excluding missing tags. These are local checks, not a new CI status; earlier
open Arrays/Searching findings remain unchanged.

## JMH Design and Windows Evidence Protocol

All templates use average **microseconds/op (`us/op`)**, one thread,
**3 warmup / 5 measurement iterations at 500 ms**, and **two forks**. Returned
counts/decisions are consumed by JMH; this does not replace generated-code or
profiler inspection when investigating runtime effects.

| Template | Default parameters | Cells | Complete work timed |
|---|---|---:|---|
| [PermutationCountBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/PermutationCountBenchmark.java) | n=6/8; DISTINCT, DUPLICATE_HEAVY, ALL_EQUAL | 12 | Sorted-position versus frequency counts, including clone/sort/private state; no permutation rows |
| [CombinationCountBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/CombinationCountBenchmark.java) | n=12/18; TWO, HALF, ALL cardinality | 12 | Full validated count with/without remaining-cardinality pruning; no rows/cache |
| [NQueensCountBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/NQueensCountBenchmark.java) | n=8/10/12 | 6 | Full flag/array versus primitive mask count, no materialised boards/symmetry reduction |
| [SubsetSumBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/SubsetSumBenchmark.java) | n=12/18; RANGE_MISS, PARITY_MISS, PRESENT, SIGNED_MISS | 16 | Full exact decision; bounded includes suffix preparation/arrays, reference does not |

Permutation DISTINCT uses 0..n-1; DUPLICATE_HEAVY uses i%3; ALL_EQUAL uses ones.
Trial shuffling has seed **20261026**, excluded from timing. Expected counts at
n=6/8 are 720/40,320, 90/560 and 1/1 respectively. Combination k is 2, n/2 or n.
Queen defaults count 92/724/14,200. Subset fixtures are deterministic all-2 or
alternating -2,+2 arrays with targets defined in the earlier table; PRESENT is
the only true default. These deliberately controlled patterns are not random
population samples. Input order and early success are part of decision cost.

There are **46 combined defaults**, split 12/12/6/16 by these classes. All
questions compare matching mathematical outputs. Counting does not measure
enumeration/allocation throughput; a boolean decision does not produce a witness
or count subsets. No memoisation, dynamic programming or symmetry optimisation
is hidden in the baselines. Return types, local work/state allocation and caps
remain explicit. Two sizes (three for queens) do not establish an exponent.

Each template retains Research Question, Theoretical Expectation, Measured Work,
Result and Interpretation. The
[first research report](../17-benchmarking/results/backtracking/README.md) now
completes all four with **46 means/errors**, **460 measured** and **276 warmup
averages**, completion **6 min 42 sec**. Supplied on **2026-10-26**, it uses
separately author-reported source **`b62df1b84810dfe5dea023d5d00bac2eb05c0cd0`**,
the merge of PR #88, whose tree matches this reviewed contribution. Headers
confirm JDK 21.0.9/JMH 1.37 and the defaults above; Oracle/Windows 11 amd64,
es_AR/UTF-8 were separately supplied. Source is author checkout attribution,
not embedded by JMH or established by a jar hash. Complete command, execution
timestamp, CPU/power, generated-code inspection and profiler data are unavailable.

Two cells have error at least 25%: unpruned combinations n=12/HALF (38.3%)
and queens flags n=8 (29.5%). The 12/HALF combination intervals overlap; their
point means do not establish a precise ordering or equivalence. Every printed
observation and CI remains intact.
Pruned combination means are lower at HALF/ALL but higher at TWO; a single
avoided call does not guarantee lower elapsed time. Masks and frequency counts
have lower means across their matching fixtures; for DISTINCT, both permutation
candidate-check counts are equal, so reduced loop width alone cannot explain
the whole grid. Bounded subset misses have lower means, but PRESENT favours the
unbounded reference; suffix preparation remains timed in the bounded API.

The report derives exact prefix/candidate ledgers, including signed misses,
independently of timing. These are not observed CPU events, actual allocations,
stack bytes or elapsed-cost attribution. Counts omit enumeration; no universal
speedup, significance/equivalence or exponential/factorial fit is claimed.
Earlier 672-test verification stays distinct from this results-only update.

The following commands retain the Windows protocol for later runs from the
local repository root after source integration:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd -Pbenchmark clean package
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar -l ".*backtracking.*"
```

Expect **672 tests**, BUILD SUCCESS and **eight listed methods**. Use the
author-verified explicit JDK 21 path or adapt it to the installation. Maven
release=21 selects bytecode compatibility, not the executable launching JMH;
packaging builds/tests the jar but does not execute controlled measurement.

For the complete initial **46-cell** run:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*backtracking.*" -foe true
```

Alternatively run the classes separately (12/12/6/16 cells), retaining each
run's source/header/completion marker:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*backtracking.PermutationCountBenchmark.*" -foe true
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*backtracking.CombinationCountBenchmark.*" -foe true
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*backtracking.NQueensCountBenchmark.*" -foe true
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*backtracking.SubsetSumBenchmark.*" -foe true
```

Capture SHA **before measurement and before later results-documentation pulls**.
Send actual JDK/JMH/settings, complete final tables with all errors, printed
fork iterations and completion markers, plus known CPU/OS metadata. Preserve
class-specific n and distribution/selection/scenario columns: N/A in mixed
tables is inapplicable, not zero. Evidence remains Markdown without JSON/log
exports; console text is sufficient. Linux uses a selected JDK 21, `bash mvnw`
and the same jar/options. Short smoke overrides remain separate harness checks.

## Sources and Review Decisions

The supplied **CLRS Third Edition (2009)** was consulted for §22.3, printed
pp. 603-605 (DFS exploration/return structure), §34.5.5, pp. 1097-1100 (positive
integer subset-sum decision context), and §35.5, pp. 1128-1129 (positive-input
exact/optimisation model). Page 1128 was visually checked. Its sum>target
discard is justified by positive remaining values; it is not a safe rule for
the signed API here. No claim is made that CLRS has a dedicated N-Queens or
permutation-generator chapter, or that this implementation copies its subset
sum algorithm. NP-completeness context for a general problem is not a timing
claim about a fixed-cap educational implementation.

- [NIST Dictionary of Algorithms and Data Structures: backtracking](https://xlinux.nist.gov/dads/HTML/backtrack.html), consulted 2026-10-26: search/choice-point terminology and conceptual depth-first model.
- [OEIS A000170](https://oeis.org/A000170), consulted 2026-10-26: ordinary N-Queens counts, offset zero, used as an external numerical cross-check rather than a correctness proof.

Input caps, value-unique permutations, half-open prefix semantics, immutable
rows, separate count/materialisation APIs, signed suffix bounds and exact
source ledgers are repository design choices. Completeness needs exhaustive
candidates and sound pruning; a first-hit decision deliberately stops early.
The review corrects blanket node/output-space, O(n!)/combination labels and
candidate-order claims. Sudoku, optimisation-specific branch-and-bound and
future Dynamic Programming remain distinct work.
