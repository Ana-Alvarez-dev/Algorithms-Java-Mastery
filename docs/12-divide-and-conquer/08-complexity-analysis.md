# Complexity Analysis

## Algorithms Java Mastery

## Source work ledger and observable results

The [contract/proof map](implementation-contracts.md) derives the shared
full-scan ledger L(0)=L(1)=0 and L(n)=L(floor(n/2))+L(ceil(n/2))+n. It counts
crossing item visits, or one merge write pass, without assigning time shares.
MaximumSubarray's canonical ties, InversionCount's strict duplicate policy and
QuickSelect's in-place rank partition are different observable contracts.
Total source constructions, reachable logical live state and measured heap
occupancy remain distinct. A clone in a benchmark belongs to that workflow
even when the production selector uses constant auxiliary state.

The [implementation guide](implementation-and-benchmark-guide.md) supplies
the contracts, traces, invariants, tests and timing protocol behind this table.
Let n be input length. Primitive int/long arithmetic is word work; bounds
describe the selected families within Java array/numeric domains, rather than
promising unbounded hardware resources. Empty/constant-size bases are constant.

## Reviewed Implementations

| Operation | Work / case | Auxiliary state excluding supplied input |
|---|---|---|
| Existing BinarySearch | Best Theta(1); worst Theta(log n), n>=2 | Iterative O(1) |
| Existing RecursiveBinarySearch | Same best/worst; average needs a query model | O(log(n+1)) worst logical depth |
| Existing MergeSort | Theta(n log n), n>=2, full merge even on sorted input | O(n) reusable array plus O(log(n+1)) depth |
| Existing Hoare QuickSort | Balanced Theta(n log n); worst Theta(n^2); expected bound has a random-permutation distinct-key model | O(log(n+1)) worst depth by smaller-side recursion; O(1) partition locals |
| MaximumSubarray.divideAndConquer | Theta(n log n), n>=2, full crossing scans | O(log(n+1)) live logical state; Theta(n) source result constructions over the call |
| MaximumSubarray.linear | Theta(n+1) | O(1) locals and constant returned value state |
| InversionCount.mergeBased | Theta(n log n), n>=2 | O(n) private copy/scratch plus O(log(n+1)) depth |
| InversionCount.quadratic | Theta(n^2), n>=2, exactly n(n-1)/2 pair visits | O(1) |
| QuickSelect.select | O(n^2) worst bound; Theta(n) all-equal; no arbitrary-input expected-linear guarantee | O(1), iterative in place |

Smaller-side Quicksort recursion is an explicit property of the existing code;
a generic naive implementation may have O(n) worst depth. New QuickSelect
returns one rank, so it is not a substitute when a fully sorted array is needed.
Nonmutating wrappers and benchmark clones change storage/work contracts.

## Work Is Not Depth or Allocation

Two balanced children plus constant combine yield Theta(n) total work; linear
combine yields Theta(n log n). Both have logarithmic active depth. Naive
Fibonacci's overlapping calls, discussed in Recursion, instead repeat work
exponentially; its recursion tree is not a divide-and-conquer partition.

MaximumSubarray recursion constructs 2n-1 Subarray records for nonempty n, but
does not keep every completed subtree live. Its sequential auxiliary-state
model is logarithmic, with no slice copy. This source construction count is
not measured heap occupancy, allocation rate, GC retention or byte size.
Merge inversion counting keeps two length-n arrays, so its array state dominates
logical stack depth. All possible int-array sums and pair counts fit long.

## Case and Distribution Assumptions

Binary search requires sorted data with constant-time indexed access; its
worst logarithmic path does not describe the first-middle-hit best case.
An average claim must define successful/unsuccessful queries and probabilities.
Quicksort's expected bound needs the stated input model, not an assumed
balanced recurrence. Three fixed-position pivot candidates in QuickSelect
provide no general rank-balance guarantee.

Full merges have linear local work on every level in the reviewed source.
O(n) alone would give an upper bound; a tight Theta conclusion needs a lower
bound as well. The [Master theorem](04-master-theorem.md) states polynomial
gaps and regularity. Data-dependent partitions may require different analysis.

## Stability, Ownership and Hardware

Existing MergeSort chooses the left item on equality, supporting the stable
merge argument. Primitive int tests cannot observe identities of equal keys;
no new generic stable-sort API is introduced. QuickSort is exchange-based and
does not guarantee stability. Inversion equality is excluded by definition;
selection preserves duplicate multiplicities rather than their identities.

There is no blanket practical-speed winner. Layout, runtime optimisations,
allocation and input distribution can affect elapsed time, but source
complexity does not identify a CPU/cache cause. None is asserted without a
matching experiment or profiler evidence. The JMH protocol times complete
operations and preserves trial inputs. The
[first research report](../17-benchmarking/results/divide-and-conquer/README.md)
now supplies all 48 means/errors and printed iterations on JDK 21.0.9/JMH 1.37,
with author-reported source `507b253` and six wide-error cells. Source scan/
construction counts explain differing work, not timing shares or machine events.
Small direct inversion counts and ordered copy-plus-sort can have lower means
than their compared variants; those bounded observations do not determine a
universal ranking. Ratios from two sizes do not prove an exponent.
