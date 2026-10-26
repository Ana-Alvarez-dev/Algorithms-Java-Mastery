# Divide and Conquer: Implementation and Benchmark Guide

## Selected Scope and Evidence Chain

This contribution starts from main
**`be8c6cdf4cbbab3f760cbc22cbc5136151674b1f`**, the merge of Recursion research
through PR #83. That earlier module now retains all 24 initial means/errors and
printed iterations in its [research report](../17-benchmarking/results/recursion/README.md).
Its evidence is not reused as a measurement of this module.

The package `org.anaalvarezdev.algorithms.divideandconquer` mirrors main, test
and JMH roots. It adds **three production files**, **18 executed tests**, and
**six benchmark methods in three classes**, with **48 default configurations**.
Existing Searching, Sorting and Recursion source/tests, the POM and previous
research results are unchanged. Earlier open findings remain tracked.

| Conceptual documents | Selected executable responsibility | Evidence |
|---|---|---|
| 01 fundamentals / 02 process | Maximum subarray and inversion count solve both halves; QuickSelect retains one side | Contracts, decomposition, traces and invariants below |
| 03 recurrences / 04 Master theorem | Balanced scans versus variable partition sizes; tight local-work assumptions | Polynomial gaps, regularity, rounding/base cases and limits |
| 05 binary search | Reuse existing Searching.BinarySearch / RecursiveBinarySearch | Existing contracts/tests/research; sorted random-access precondition |
| 06 merge sort | Reuse existing Sorting.MergeSort; extend merging to cross-inversion counting | Existing sort evidence plus independent inversion oracles |
| 07 quicksort | Reuse existing Sorting.QuickSort; contrast Hoare boundaries with three-way selection | Smaller-side recursion, duplicate handling, permutation and rank tests |
| 08 analysis / 09 mistakes | Work, depth, allocation/ownership, tie policies and full workflow measurements | Independent tests/probe, smoke and controlled author protocol |

Binary search/selection can be classified more specifically as
**decrease-and-conquer**: only one reduced problem survives. This module uses
the broader textbook family while stating which branches actually execute.
No duplicate binary-search or sorting implementations are introduced.
Matrix/Strassen multiplication, closest pair, generic stable sorting,
randomised selection, median-of-medians, parallel execution and automatic
recurrence solvers remain extensions, not completed code.

## Contracts and Ownership

| API | Input and output | Policy |
|---|---|---|
| [MaximumSubarray.divideAndConquer / linear](../../src/main/java/org/anaalvarezdev/algorithms/divideandconquer/MaximumSubarray.java) | Non-null int[]; Optional of immutable Subarray(fromInclusive,toExclusive,sum) | Best **nonempty** contiguous range; half-open original bounds; exact long sum; empty input gives Optional.empty() |
| [InversionCount.mergeBased / quadratic](../../src/main/java/org/anaalvarezdev/algorithms/divideandconquer/InversionCount.java) | Non-null int[]; exact long count of i<j with values[i]>values[j] | Strict comparison: equal keys do not count; empty/singleton gives zero |
| [QuickSelect.select](../../src/main/java/org/anaalvarezdev/algorithms/divideandconquer/QuickSelect.java) | Non-null int[], zero-based rank in [0,length); returns the sorted-rank value | Permutes input; duplicates occupy separate ranks; input need not be sorted |

All APIs throw NullPointerException on null before doing work. Invalid selection
ranks throw IndexOutOfBoundsException **before mutation**, including every rank
on an empty array. No sortedness precondition is added to the three new types.
MaximumSubarray ties prefer the **smallest starting index**, then the **smallest
exclusive ending index**. All-negative input therefore returns a negative
singleton; zero is not an implicit empty-range answer.

MaximumSubarray and both inversion variants preserve the supplied array.
Merge-based counting sorts only a private clone with one auxiliary array.
QuickSelect preserves length and multiplicities, and on return:

- `values[rank]` equals the returned value;
- each earlier item is <= that value and each later item is >= it;
- the rest of the array need not be sorted.

The Subarray record is an immutable value carrying the algorithm's returned
bounds, not an owned slice or an input validator. It contains no mutable array.
There is no shared mutable global cache or per-call state between operations.

Every possible int-array mathematical sum fits long: n <= Integer.MAX_VALUE,
each magnitude <= 2^31, so any partial sum has magnitude <2^62. The maximum
possible inversion count n(n-1)/2 is also below 2^61. Conversion to long occurs
before multiplication in test/formula calculations. No comparison subtracts
two int values, avoiding extreme-value comparator overflow.

## Maximum Subarray: Strategy, Trace and Correctness

```text
MAXIMUM([lo,hi)):
  singleton -> that item, its original bounds and long value
  mid = lo + floor((hi-lo)/2)
  L = MAXIMUM([lo,mid)); R = MAXIMUM([mid,hi))
  C = best nonempty suffix of left + best nonempty prefix of right
  return canonical best among L, R, C
```

Every nonempty candidate lies entirely left, entirely right, or crosses the
split. Induction supplies canonical maxima for the first two categories. A
crossing candidate is a left suffix plus a right prefix. Their sums are
independent, so choosing each maximum gives the maximum crossing sum.
On equal suffix sums the right-to-left scan updates to the earlier start;
on equal prefix sums the left-to-right scan keeps the earlier end. Comparing
the three categories by the same sum/start/end order gives the canonical
global answer. Strictly smaller nonempty intervals prove termination.

For `[-2,1,-3,4,-1,2,1,-5,4]`, split [0,9) at 4. The left maximum is [3,4)
with sum 4; the right maximum is [8,9) with sum 4. The best crossing suffix
starts at 3 and the best right prefix ends at 7, yielding **[3,7), sum 6**.
`[0,1,0,-1,1]` returns **[0,2), sum 1**, establishing both tie rules.
`[-5,-2,-2]` returns **[1,2), sum -2**, rather than an empty zero sum.

The linear reference maintains the canonical best nonempty range **ending at
the current index**, plus the best range seen so far. A negative ending sum is
discarded before the next item; a zero ending sum is extended to retain the
earlier start. Any best range ending at the next position either starts there
or extends the previous ending optimum. This proves the update; the global
comparison uses the same tie order. The index strictly increases to length.
This reference is a selected comparison, not completion of the future Dynamic
Programming module.

With singleton bases and full crossing scans, recurrence is
T(n)=T(floor(n/2))+T(ceil(n/2))+Theta(n), giving **Theta(n log n)** for n>=2
and O(log(n+1)) logical depth/live auxiliary state. There are exactly **2n-1
source Subarray constructions** for nonempty n: n leaves and n-1 crossing
candidates, plus one public Optional result. The linear form constructs one
Subarray and one Optional, with **Theta(n+1)** work and O(1) local state.
Source constructions are not measured allocation rates, actual surviving
heap objects or bytes: JIT/GC effects were not profiled. Sequential live state
differs from total allocations over the entire call.

## Inversions: Merge Invariant and Count Ledger

```text
COUNT([lo,hi)) on a private copy:
  length < 2 -> 0
  leftCount = COUNT([lo,mid)); rightCount = COUNT([mid,hi))
  merge sorted halves
  whenever rightHead < leftHead:
    crossCount += mid - leftCursor
  return leftCount + rightCount + crossCount
```

Every pair belongs uniquely to the left half, right half or crosses the split.
The recursive induction counts the first two categories and sorts each half.
During merging, the destination prefix is sorted and contains exactly the
consumed items. When the right head is strictly smaller than the left head,
it is smaller than **every remaining left item**, adding exactly mid-leftCursor
cross pairs. Otherwise the left head is emitted, including equality, so equal
keys never enter the strict inversion count. Every cross pair is counted once
when its right item is emitted. Cursor progress proves merge termination;
strictly shorter recursive ranges prove overall termination.

For `[2,3,8,6,1]`, split at 2. Left count is 0, right count is 3; its sorted
right half is `[1,6,8]`. Emitting 1 before remaining `[2,3]` adds 2 cross pairs,
giving **5**. `[2,2,1]` gives **2**, while `[1,1,1]` gives **0**.

Full merging/copying gives the same balanced **Theta(n log n)** recurrence
for n>=2, including already sorted arrays: there is no ordered-halves shortcut.
The private clone and reusable auxiliary array take O(n) storage; logical
depth is O(log(n+1)). Both allocations and their copy work are part of the
nonmutating public operation. The quadratic reference visits exactly n(n-1)/2
pairs, with O(1) local state, regardless of how many qualify. Descending
100,000 distinct values give **4,999,950,000**, exceeding int range.

## Selection: Partition Invariant and Progress

```text
SELECT(values,rank): validate before mutation; active = [0,length)
  choose median value of active first/middle/last items
  partition into < pivot | == pivot | > pivot
  retain the strict side containing the global rank, or return from equal band
```

At each partition step `[from,less)` is < pivot, `[less,scan)` equals pivot,
`[scan,greater)` is unknown and `[greater,to)` is > pivot. A smaller item swaps
into the first band and advances both cursors; a larger item swaps with the
shrinking right boundary without advancing scan; an equal item advances scan.
The number of unknown items decreases by one each iteration. Swaps preserve
the permutation. The pivot is chosen from active values, so its equal band is
nonempty. Retaining a strict side therefore strictly reduces active length.

Previously discarded bands bound all active values. Partitioning and keeping
the rank side preserve that property. If rank lies in the equal band, its value
has precisely the required order-statistic position, with the documented
earlier/later inequalities across the entire array. A singleton active range
has the same conclusion. Duplicate multiplicities are retained, not deduplicated.

For `[4,1,4,2,3]`, rank=2, median(first=4,middle=4,last=3) is 4. The equal
band has ranks [3,5), so retain [0,3), containing {1,2,3}. The next pivot is 2;
rank 2 retains the singleton value **3**. Exact swap layouts are implementation
details; the inequalities, multiset and rank are the observable contract.

This deterministic, iterative variant has **O(1)** auxiliary state and **O(n^2)**
worst-case work; all-equal input needs one Theta(n) partition. A variable retained
size m gives T(n)=T(m)+Theta(n), not automatically a fixed-fraction Master
recurrence. A randomised algorithm's expected-linear proof is not borrowed for
median-of-three on arbitrary inputs. No worst-case-linear median-of-medians
or adversarial-input protection is implemented.

## Reused Case Studies and Review Corrections

| Existing type | Source facts used by this module | Scope boundary |
|---|---|---|
| [Searching.BinarySearch](../../src/main/java/org/anaalvarezdev/algorithms/searching/BinarySearch.java) / [RecursiveBinarySearch](../../src/main/java/org/anaalvarezdev/algorithms/searching/RecursiveBinarySearch.java) | Sorted int random-access array; any matching duplicate index; worst logarithmic work, best constant | No sorting validation inside search; average claims require a query model |
| [Sorting.MergeSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/MergeSort.java) | One reusable auxiliary array, left on equality, full merge every level | Theta(n log n) for n>=2; primitive values cannot by themselves test identity stability |
| [Sorting.QuickSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/QuickSort.java) | Deterministic middle-value pivot, Hoare boundary, recurse on smaller side and loop on larger | Boundary is not a pivot's final rank; worst time remains quadratic, but stack stays O(log n) |

The [Master theorem document](04-master-theorem.md) now states the polynomial
gaps and case-three regularity condition. An upper-bound O(n) local term alone
does not justify a tight Theta(n log n) conclusion. Balanced Quicksort is a
conditional case, not a derivation of average performance by assuming halves;
its existing expected bound specifies uniformly random permutations of distinct
keys. Three-way QuickSelect and Hoare Quicksort have different boundaries.
Hardware/cache claims require measurement or profiling, not algorithm names.
Independent mathematical subproblems do not imply safe shared-buffer parallelism.
Module **13 is Greedy**, followed by Backtracking and Dynamic Programming.

## Automated Verification

Verification reported **2026-10-25 (UTC)**, using **OpenJDK 21+35-2513**, passed
normal Maven tests and benchmark-profile clean package: **618 repository tests**,
zero failures/errors/skips, including **18 module tests** (prior Recursion 600).

| Test class | Executed tests | Independent evidence |
|---|---:|---|
| [MaximumSubarrayTest](../../src/test/java/org/anaalvarezdev/algorithms/divideandconquer/MaximumSubarrayTest.java) | 6 | Quadratic interval enumeration in tie order for all 3,280 small arrays; 500 seeded full-int cases; negative/zero/crossing/tie examples; 200,000-value exact long sum and nonmutation |
| [InversionCountTest](../../src/test/java/org/anaalvarezdev/algorithms/divideandconquer/InversionCountTest.java) | 6 | Direct pair oracle on all 3,280 small arrays and 500 seeded cases; 200 reversal identities with duplicates; descending 100,000 count beyond int and nonmutation |
| [QuickSelectTest](../../src/test/java/org/anaalvarezdev/algorithms/divideandconquer/QuickSelectTest.java) | 6 | Sorted-copy oracle, rank inequalities and permutation for all 21,324 small array/rank combinations; every rank in 200 seeded cases; extreme/structured inputs and 200,000 equal values; rejection before mutation |

SmallArrays enumerates lengths 0..7 over {-1,0,1}: sum(3^k)=3,280 arrays;
sum(k*3^k)=21,324 valid rank combinations. Examples and large cases complement
this finite exhaustive subset; passing tests are not a proof for every possible
array. The test generator is not a production API.

All six JMH methods are discovered. **48 short smoke configurations** complete
with one 50 ms warmup, one 50 ms measurement and one fork; their timings are
not research results. A separate probe checks **all 48 default combinations,
three calls each**, against prefix-minimum, direct-pair and sorted-copy oracles,
including return values, repeatability and unchanged trial inputs. Package
JavaDoc passes doclint excluding missing tags. These are local checks, not an
assertion of CI workflow status. Production code in earlier modules is unchanged.

## JMH Design and Windows Evidence Protocol

All three classes use average **microseconds/op (`us/op`)**, one thread,
**3 warmup / 5 measurement iterations at 500 ms**, and **two forks**.
Trial construction uses seed **20261025**. Returned results are consumed by JMH;
this does not substitute for inspecting generated code when investigating JIT.

| Template | Default sizes / distributions | Cells | Work timed |
|---|---|---:|---|
| [MaximumSubarrayBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/divideandconquer/MaximumSubarrayBenchmark.java) | 100/10,000; MIXED, ALL_NEGATIVE, ALL_POSITIVE, ALL_ZERO | 16 | Full validated calculation plus Optional/Subarray result; unchanged trial array, no timed clone |
| [InversionCountBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/divideandconquer/InversionCountBenchmark.java) | 100/1,000; RANDOM, SORTED, REVERSE_SORTED, DUPLICATE_HEAVY | 16 | Full long count; merge includes its private clone/auxiliary arrays, direct pairs has no such allocation |
| [QuickSelectBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/divideandconquer/QuickSelectBenchmark.java) | 100/10,000; RANDOM, SORTED, REVERSE_SORTED, DUPLICATE_HEAVY | 16 | Both methods clone inside timing; select rank=size/2 or sort fully then read that rank |

MaximumSubarray MIXED uses ints -1,000..1,000; negatives -1,000..-1;
positives 1..1,000; zeros are all zero. Inversion/selection RANDOM uses full
int values; SORTED and REVERSE_SORTED reorder the same seeded population;
DUPLICATE_HEAVY uses -4..4. These last nine-key fixtures are a different value
population, not an isolated order perturbation. Sign/tie distributions also
alter branch and candidate-update work. None varies multiple random seeds.

Selection measures **copy-plus-select versus copy-plus-sort**, for the upper
middle rank at even sizes. The production selector's O(1) auxiliary-state
claim does not make the benchmark workflow constant-space: its clone is O(n).
It does not compare selecting every rank, repeated queries on a retained sorted
copy or an isolated partition cost. Inversion's default size cap bounds direct
pair work; overriding it to huge sizes can make that reference expensive.

Each template retains Research Question, Theoretical Expectation, Measured
Work, Result and Interpretation. The
[first research report](../17-benchmarking/results/divide-and-conquer/README.md)
now completes all three from **48 means/errors**, **480 measured** and **288
warmup averages**, with completion **7 min 4 sec**. The author supplied the
output on **2026-10-26**, separately reporting source
**`507b2537d3dedd3beb57900d3ee72d7858571c89`**, the PR #84 merge. Headers confirm
JDK 21.0.9/JMH 1.37 and the defaults above. The SHA is author checkout
attribution, not embedded in JMH or verified by a supplied jar hash. The
attachment starts with a header; full command, execution timestamp, CPU/power
and profiler data are not supplied.

Linear maximum-subarray means are lower on all selected fixtures. At size 100,
direct inversion counts have lower means on sorted/reverse inputs; at 1,000,
merge means are lower in all four fixtures. Selection has lower means on RANDOM,
while copy-plus-sort has lower means on ordered fixtures. Duplicate-heavy
size-10,000 selection/sort intervals overlap. These are bounded observations,
not a general crossover threshold, significance test or universal ranking.

Six errors reach 25% of their means: two direct inversion and four selection/
sort workflow cells, maximum about 74.5%. No observation is dropped. The report
derives pair visits, full-merge/crossing scan counts and result-construction
counts from source, without assigning CPU/cache/GC causes or elapsed-cost
shares. Sorted selection returns after one partition because rank=size/2 lies
in the first pivot's equal band; that still does not guarantee it beats the
library workflow. Actual library paths were not inspected or profiled here.
No allocation rate, stack bytes, formal equivalence, fitted exponent or new
performance experiment is claimed. Earlier 618-test verification is separate.

The following commands retain the reproducible Windows protocol. After source
integration, run from the local repository root:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd -Pbenchmark clean package
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar -l ".*divideandconquer.*"
```

Expect **618 tests**, BUILD SUCCESS and **six listed methods**. The explicit
Java path is the author's verified JDK 21 installation; adapt it elsewhere.
Maven release=21 controls bytecode compatibility, not the executable launching
JMH. Packaging builds/tests the jar; it does not perform the measurements.

For one complete **48-cell** default run:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*divideandconquer.*" -foe true
```

Alternatively run the three classes separately, preserving each run's own
source/header/completion marker (16 cells each):

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*divideandconquer.MaximumSubarrayBenchmark.*" -foe true
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*divideandconquer.InversionCountBenchmark.*" -foe true
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*divideandconquer.QuickSelectBenchmark.*" -foe true
```

Capture SHA **before measurement and before pulling later result changes**.
Send actual JDK/JMH/settings, complete final tables with every error, printed
fork iterations and completion markers, plus known CPU/OS metadata. Preserve
microsecond units, two sizes per class and all four distributions. No JSON/log
exports are needed: received console evidence will be transcribed to Markdown.
Do not pool separate runs as independent samples without a stated design or
silently replace wide-error values. Linux uses a selected JDK 21, `bash mvnw`
and the same jar/options. Short smoke overrides are separate harness evidence.

## Sources and Review Decisions

The supplied **CLRS Third Edition (2009)** was consulted: §4.1, printed pp. 68-74
(maximum subarray and linear-method exercise); §4.5, pp. 93-96, especially
Theorem 4.1 on p. 94 (Master conditions); Problem 2-4, pp. 41-42 (inversions);
and §§9.2-9.3, pp. 215-222 (randomised versus worst-case-linear selection).
These are third-edition locations, not fourth-edition pagination. The Master
formula page was visually checked against the supplied PDF.

The repository's zero-based half-open bounds, Optional empty policy, canonical
tie order, duplicate-aware inversion domain, long arithmetic and deterministic
median-of-three three-way selector are explicit Java design choices. They are
not verbatim book code. Randomised selection proofs do not establish expected
linear time for this deterministic variant. The three new types and six timing
questions complete only this selected scope; later Greedy/Backtracking/DP,
parallel variants and stronger selection guarantees remain distinct work.
