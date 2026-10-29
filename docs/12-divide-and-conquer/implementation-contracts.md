# Divide and Conquer: Applied Contracts and Proof Map

Reviewed on **2026-10-29 (America/Buenos_Aires)** from main
`5fd81c44cd5df511bb6c553dcdad45b17c38a18d`, the PR #109 Recursion-review merge.
The [ordered review](documentation-review.md) records findings and fresh Java 21
execution. The [guide](implementation-and-benchmark-guide.md) retains original
implementation, smoke/probe and research provenance. No new algorithm is added.

## Public contracts and source ownership

| API | Normal result | Input, ownership and failures |
|---|---|---|
| [MaximumSubarray.divideAndConquer / linear](../../src/main/java/org/anaalvarezdev/algorithms/divideandconquer/MaximumSubarray.java) | Optional<Subarray> for a **nonempty** contiguous maximum; empty input gives Optional.empty() | Non-null int[], unchanged; null throws NullPointerException; result has original half-open indices and exact long sum |
| [InversionCount.mergeBased / quadratic](../../src/main/java/org/anaalvarezdev/algorithms/divideandconquer/InversionCount.java) | Exact long number of pairs i<j with values[i]>values[j]; empty/singleton=0 | Non-null int[], unchanged; null throws NullPointerException; merge sorts a private clone using one scratch array, only for length>=2 |
| [QuickSelect.select](../../src/main/java/org/anaalvarezdev/algorithms/divideandconquer/QuickSelect.java) | Value at zero-based ascending rank, with duplicate multiplicities retained | Mutates/permutates int[]; null fails first, invalid rank throws IndexOutOfBoundsException before writes, including every rank on empty input |

MaximumSubarray compares answers by highest sum, then **smallest start**, then
**smallest exclusive end**. The immutable scalar Subarray record carries that
result; its public constructor does not validate arbitrary caller-created
bounds/sums and stores no owned array or slice. A negative nonempty answer is
valid: all-negative input does not select an empty zero sum.

On successful selection, values[rank] equals the result; all earlier values
are <= it and all later values >= it. Length and the full multiset are
preserved. Neither side need be internally sorted. No public slice/range
selection overload or new generic sorting API is exposed. All three utilities
have private constructors and no shared mutable global state. Read-only
operations require a stable input while reading; they do not snapshot against
concurrent caller mutation.

For any int-array length n<=2^31-1, each element magnitude is <=2^31.
Every subset/partial sum has magnitude <2^62, so both maximum-subarray variants
and crossing additions fit long. Counts are bounded by n(n-1)/2 <2^61.
Formula calculations must widen before multiplying; source comparisons use
relational operators, not potentially overflowing int subtraction.

## Maximum subarray: decomposition, ties and linear invariant

For the private divide(values,lo,hi), assume a nonempty valid half-open range.
Its postcondition is the canonical best nonempty interval wholly inside that
range. A singleton has only itself as candidate. At length>=2, mid yields two
strictly smaller nonempty halves. Every candidate is entirely left, entirely
right, or crosses mid; induction supplies canonical answers for the first two.

A crossing interval consists of a nonempty suffix ending at mid and a nonempty
prefix starting there. Maximising the two independent sums maximises their
sum. The suffix scan runs right-to-left and updates on **>=**, retaining an
earlier start on equal sums. The prefix runs left-to-right and updates only
on **>**, retaining the earlier end on equal sums. Finally better compares all
three categories by the same sum/start/end order. This proves the canonical
answer, not merely the maximum numeric sum. Strictly smaller ranges and finite
cursor scans establish termination in the algorithmic model.

For [-2,1,-3,4,-1,2,1,-5,4], the root splits [0,9) at 4. The best crossing
suffix is [3,4), sum 4, and prefix [4,7), sum 2. Their answer is [3,7), sum 6.
For [0,1,0,-1,1], [0,2) wins at sum 1: start 0 precedes later starts, and
end 2 precedes other equal-sum endings from that start. For [0,0,0], the
canonical answer is [0,1), not the full array; for [-5,-2,-2], it is [1,2).

The loop keeps the canonical maximum nonempty range **ending at the previous
index**, and the canonical best range anywhere in the processed prefix.
At the next index, every candidate either starts there or extends a previous
ending range. If endingSum<0, restarting strictly improves the sum. Otherwise
extend: on zero, extension preserves the earlier starting index. The global
better comparison retains the correct end tie as well. Initialising from the
first actual element, rather than implicit empty zero, handles all-negative
input. Increasing the index to length establishes termination; empty input
returns before initialising the state.

Both implementations read the same mathematical problem. The linear method
is a different strategy with an ending-range invariant, rather than a faster
syntax-only rewrite of the recursion.

## Inversions: sorted prefix and cross-pair accounting

For count(copy,scratch,lo,hi), the combined postcondition is:

1. return the strict inversion count of the original values in [lo,hi);
2. sort exactly that range ascending while preserving its multiset;
3. leave values outside it unchanged.

A range shorter than two has zero pairs. Every original pair is internal to
one half or crosses the split; these categories are disjoint and exhaustive.
Induction counts internal pairs and supplies sorted halves. Copying the full
active range to scratch preserves unread values while overwriting copy.

During merging, the written prefix is sorted and contains exactly the consumed
items. If the left head is <= the right head, emit it; equality contributes no
strict inversion. If the right head is smaller, every remaining left item is
strictly larger, so add **mid-leftCursor**. Each cross inversion is charged
once, when its right item is emitted. Exhausting a half requires no extra
cross count. Cursor progress and smaller recursive ranges prove termination.

For [2,3,8,6,1], the left half [2,3] has zero inversions and the right half
[8,6,1] has three. Merging its sorted [1,6,8] with [2,3], the emitted 1 adds
two cross inversions, giving five. [2,2,1] gives two; [1,1,1] gives zero.
For descending distinct length 100000, the count is 4999950000, beyond int.

The direct-pair baseline visits every i<j exactly once, incrementing only for
the strict predicate; it reads the original input with constant local state.
For duplicates, the reversal identity is count(A)+count(reverse(A)) = number
of **unequal** pairs, not automatically n(n-1)/2.

## QuickSelect: partition, global rank and progress

The iterative active interval is half-open [from,to); rank is always the
**original global index**, with from<=rank<to. The pivot is the median value
of the first, middle and last active positions. It need not be the median of
all active elements. At length two the middle/last position coincides; the
algorithm does not require three distinct candidates.

The inner partition maintains:

- [from,less) contains values < pivot;
- [less,scan) contains values == pivot;
- [scan,greater) is not yet classified;
- [greater,to) contains values > pivot.

A smaller item swaps into the first band and advances less/scan. A larger
item swaps with --greater; scan stays because the incoming value is unknown.
An equal item advances scan. Each step decreases greater-scan by one, and
swaps preserve the multiset. The pivot came from the active values, so its
final equal band is nonempty.

The outer invariant additionally bounds discarded regions: every prefix item
before from is <= every active item, and every suffix item from to is >= every
active item. If rank is left of the equal band, retain [from,less); if right,
retain [greater,to); otherwise return values[rank]. A retained strict side
still contains rank and is nonempty, and omits at least the equal band. Thus
its size strictly decreases. Previously discarded bounds plus the new
partition establish the outer invariant across the whole array. On returning
from an equal band or singleton, the global earlier/later inequalities follow,
not just inequalities inside the last active range. The rank is never rebased.

Trace [4,1,4,2,3], rank 2: first pivot 4 produces [1,2,3,4,4] with equal band
[3,5). Retain [0,3). Pivot 2 produces bands with the singleton answer rank
2 on the right; retain [2,3) and return 3. Exact layouts are source trace
illustrations; the public contract specifies rank, permutation and inequalities.

One partition of active length s performs s classification iterations. If it
continues, the new length m satisfies 1<=m<s. Summing decreasing lengths gives
an **O(n^2)** worst-case upper bound; no fixed-fraction guarantee is supplied.
All-equal input of length>=2 returns after one Theta(n) partition. The actual
implementation is iterative with O(1) auxiliary state. Neither a randomised
expected-linear proof nor a median-of-medians worst-linear proof applies to
this deterministic fixed-position pivot rule.

## Reused cases: contracts and interval conventions

| Existing source | Applied proof / convention | Scope boundary |
|---|---|---|
| [BinarySearch](../../src/main/java/org/anaalvarezdev/algorithms/searching/BinarySearch.java), [RecursiveBinarySearch](../../src/main/java/org/anaalvarezdev/algorithms/searching/RecursiveBinarySearch.java) | Inclusive [low,high]; preserve all possible target positions, exclude middle on a miss; overflow-safe low+(high-low)/2 while nonempty | Stable nondecreasing int[] required, any duplicate match/-1; no sortedness scan/copy; iterative constant locals, recursive logarithmic worst depth |
| [MergeSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/MergeSort.java) | Inclusive [left,right]; one auxiliary array; sorted destination prefix/multiset, left on equality | Ascending in supplied int[], accepts empty/singleton/null policy; full copy/merge even when already sorted; no descending/generic/external-file implementation |
| [QuickSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/QuickSort.java) | Inclusive Hoare boundary j divides [left,j] and [j+1,right]; swaps preserve multiset; cross-region order plus sorted children establishes result | j is not a final pivot rank or three-way equal band; middle-value pivot; smaller-side recursive call/larger-side loop; no stability guarantee |

The smaller recursive QuickSort region has at most half the active elements,
so nested calls have O(1+log(n+1)) logical depth even if the full partition
problem tree is tall and total work quadratic. This is explicit source control,
not JVM tail-call elimination. Hoare equal values can appear on both sides;
all-equal Quicksort still sorts both regions, whereas selection may return
from a single equal band.

Binary search's initial-middle hit is constant; its worst-path bound is
logarithmic for n>=2. Expected search cost needs a query distribution. The
existing Quicksort expected bound uses uniformly random permutations of
distinct keys; balanced halves alone are not an average-case proof. No new
Searching/Sorting measurements or algorithms are introduced by reuse here.

## Work ledger, theorem boundary and live storage

Let L(0)=L(1)=0 and, for n>=2,
L(n)=L(floor(n/2))+L(ceil(n/2))+n. This counts every full active-range scan
across internal nodes. For n>=1, with h=ceil(log2(n)), the binary split tree
has leaves at depths h-1 or h; their external path length gives
**L(n)=n*h-2^h+n**. In particular L(100)=672, L(1000)=9976 and L(10000)=133616.
These are mathematical source counts; neither instruction counts nor profiled
events are inferred.

MaximumSubarray's crossing loops together visit n items at each internal node,
so their total is L(n). Its full tree has n singleton leaves and n-1 internal
nodes: **2n-1 source Subarray constructions**, plus one public Optional for
nonempty input. The loop processes n items and constructs one result record.
Sequential recursion retains only a bounded number of child/candidate values
per active level, hence logarithmic **logical reachable live state**. Total
constructions over the call are linear; actual heap occupancy/GC retention,
frame layout and optimisation are not measured by this argument.

InversionCount performs L(n) merge destination writes and copies L(n) active
items to scratch, plus its one initial clone. It allocates two length-n arrays
at the public boundary for n>=2, not at every node. There is no ordered-halves
shortcut; sorted input still performs full merging. Quadratic visits exactly
n(n-1)/2 pairs independently of how many qualify.

Under constant-cost primitive comparisons/additions, both balanced helpers
have tight local Theta(n) work and constant bases, yielding Theta(n log n) for
n>=2 by basic Master case 2 (a=2,b=2). Public linear clone/allocation work does
not change that bound. Empty/singleton entry cases are constant.
The theorem assumes the specified recurrence; it proves neither canonical
results nor mutation policies. Polynomial gaps and case-three regularity
remain as stated in the [theorem note](04-master-theorem.md). Variable
Quicksort/QuickSelect retained sizes do not automatically match that form.

| Operation | Word work | Auxiliary state excluding input |
|---|---|---|
| MaximumSubarray divide | Theta(n log n), n>=2 | O(1+log(n+1)) logical live state; constant returned record |
| MaximumSubarray linear | Theta(n+1) | O(1) local/result state |
| InversionCount mergeBased | Theta(n log n), n>=2 | O(n) private/scratch arrays plus logarithmic depth |
| InversionCount quadratic | Theta(n^2), n>=2 | O(1) local state |
| QuickSelect.select | O(n^2) worst upper bound; Theta(n) all-equal n>=2 | O(1), in place |
| Copy-plus-select benchmark | Clone plus selection, input-dependent work | O(n) clone plus O(1) selector state |

These are bounded Java int-array families, not promises of unbounded resources.
Logical depth is not a frame-byte measurement. Reused MergeSort has O(n)
auxiliary storage; reused QuickSort logarithmic worst stack; preprocessing and
access costs belong in comparisons with other data representations/workflows.

## Tests and empirical traceability

Fresh [verification](documentation-review.md) runs the actual Java classes with
Maven/JUnit/AssertJ: **710 tests**, zero failures/errors/skips, including **18**
module invocations. Cases inside loops are not separate JUnit invocations.

| Existing tests | Independent evidence |
|---|---|
| [MaximumSubarrayTest](../../src/test/java/org/anaalvarezdev/algorithms/divideandconquer/MaximumSubarrayTest.java), 6 | Interval enumeration in canonical tie order on all 3280 small arrays and 500 seeded arrays; explicit negatives/zeros/crossing/ties; 200000-element exact long result; input preservation/null/empty |
| [InversionCountTest](../../src/test/java/org/anaalvarezdev/algorithms/divideandconquer/InversionCountTest.java), 6 | Direct pair oracle on all 3280 arrays and 500 seeded cases; 200 duplicate-aware reversal identities; 100000-element descending count beyond int; preservation/null/bases |
| [QuickSelectTest](../../src/test/java/org/anaalvarezdev/algorithms/divideandconquer/QuickSelectTest.java), 6 | Sorted-copy value, rank position, both full-array inequalities and permutation; 21324 small rank cases, all ranks in 200 seeded arrays, structured/extreme/200000 equal inputs; null-first/rejection before mutation |

[SmallArrays](../../src/test/java/org/anaalvarezdev/algorithms/divideandconquer/SmallArrays.java)
enumerates lengths 0..7 over {-1,0,1}. Reused
[BinarySearchTest](../../src/test/java/org/anaalvarezdev/algorithms/searching/BinarySearchTest.java)
and [RecursiveBinarySearchTest](../../src/test/java/org/anaalvarezdev/algorithms/searching/RecursiveBinarySearchTest.java)
execute 13 each; [MergeSortTest](../../src/test/java/org/anaalvarezdev/algorithms/sorting/MergeSortTest.java)
and [QuickSortTest](../../src/test/java/org/anaalvarezdev/algorithms/sorting/QuickSortTest.java)
execute 14 and 16, including inherited
[contracts](../../src/test/java/org/anaalvarezdev/algorithms/sorting/SortingContractTest.java).
Tests/proofs do not demonstrate unlimited resources, identity-stability from
primitive keys, a worst-case benchmark fixture or specific CPU causes.

The three [benchmark classes](implementation-and-benchmark-guide.md) register
six methods and 48 default cells (16 per class), confirmed by fresh discovery:

- MaximumSubarray: sizes 100/10000, MIXED/ALL_NEGATIVE/ALL_POSITIVE/ALL_ZERO;
  full validated Optional/record result over unchanged input, no timed clone.
- InversionCount: sizes 100/1000, RANDOM/SORTED/REVERSE_SORTED/DUPLICATE_HEAVY;
  full count, merge includes clone/scratch work while quadratic does not.
- QuickSelect: sizes 100/10000 and the same four ordered distributions;
  **both** timed methods clone, then select upper-middle rank size/2 or fully
  Arrays.sort and read it. This is not isolated partition time or repeated
  queries on a retained sorted array.

[TrialInputs](../../src/jmh/java/org/anaalvarezdev/algorithms/divideandconquer/TrialInputs.java)
uses seed 20261025. Ordered cases reorder one full-int population; duplicate
heavy changes values to -4..4. Sum distributions also change sign/tie work.
Setup is outside timing; unchanged trial inputs prevent progressively sorted
selection data. All use avgt **us/op**, one thread, 3/5 x 500 ms warmup/measurement,
two forks. Discovery verifies registration, not fresh timing or profiler data.

The [historical report](../17-benchmarking/results/divide-and-conquer/README.md)
retains all 48 means/errors, 480 measured and 288 warmup iteration averages,
reported 2026-10-26, source author-attributed
`507b2537d3dedd3beb57900d3ee72d7858571c89` (PR #84), Oracle JDK 21.0.9/JMH 1.37.
Its 7 min 4 sec run is separate from fresh Corretto functional verification.
Six errors reach 25%; no results or templates are replaced. There is no
recovered jar hash/full command/timestamp, isolated allocation cost, universal
crossover, significance conclusion or fitted asymptotic exponent in this pass.
