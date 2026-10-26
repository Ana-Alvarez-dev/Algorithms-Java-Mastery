# Quicksort Case Study

## Algorithms Java Mastery

The [implementation guide](implementation-and-benchmark-guide.md) traces this
case study to the existing `sorting.QuickSort`, its tests and Sorting research.
There is no duplicate sort in the new package. This review distinguishes its
**Hoare boundary** from a pivot-final-position partition and contrasts it with
the new three-way `divideandconquer.QuickSelect`.

## Existing Contract and Partition

QuickSort.sort accepts a non-null int array, sorts it ascending in place and
preserves length/multiplicities. Empty/singleton arrays are accepted; null
throws NullPointerException. Exchange-based sorting does not guarantee stability.

The pivot is the **value at the active middle index**. This deterministic
choice is not the median of the full range and not a random pivot. Scans start
outside the inclusive range; the left scan stops at a value >= pivot and the
right scan at a value <= pivot. If they have not crossed, swapping restores
the cross-region order and both scans advance on their next steps, including
equal values. The pivot's presence bounds the scans within the active region.

On crossing, the returned boundary j splits [left,j] and [j+1,right]. Every
value in the first region is <= every value in the second. **j is not a final
pivot rank**; the pivot value may remain in either region and equal values may
occur on both sides. Sorting those two regions therefore orders the whole.
For length>=2, both regions are strictly smaller, giving progress.

```text
SORT([left,right]):
  while length > 1:
    boundary = HOARE_PARTITION(active)
    recursively sort the smaller region
    continue the loop on the larger region
```

Swaps preserve the multiset. Induction sorts each region and partition order
establishes the combined postcondition without an explicit merge.

## Time and Input Model

For region lengths k and n-k, local partition work is Theta(n), giving
T(n)=T(k)+T(n-k)+Theta(n). Consistently balanced partitions give Theta(n log n).
Repeated extreme imbalance can give Theta(n^2) worst-case time. Applying the
Master theorem to 2T(n/2)+Theta(n) describes the balanced case; it is **not an
average-case proof**.

The source's expected Theta(n log n) statement assumes uniformly random
permutations of distinct keys. That model is different from a random pivot on
an arbitrary input, and different again from duplicate-heavy fixtures. Pivot
heuristics do not replace an explicit distribution assumption. On all-equal
data this Hoare form continues sorting both balanced regions; it is not the
same work as a three-way selector returning from one equal band.

## Stack Depth Depends on the Implementation

A naive two-call Quicksort can have linear depth under bad partitions.
This repository recursively processes **only the smaller side**, of size at
most n/2, and iterates over the larger. Nested recursive sizes therefore at
least halve, so worst-case **logical stack depth is O(log n)** even when
total work is quadratic. Partition local state is O(1). This is an explicit
source transformation, not an assumption about Java tail-call elimination.

This distinction corrects the generic O(n)-worst-stack statement for the
actual reviewed source. It does not claim constant stack bytes or remove the
quadratic-time risk. Stable output order and hardware locality are separate
properties, not consequences of a low stack bound.

## Three-Way Selection Is a Different Contract

QuickSelect.select partitions into strict-less/equal/strict-greater bands and
keeps only the side containing the requested global zero-based rank. It
returns as soon as rank falls in the equal band. Duplicates count as separate
ranks, and the returned value sits at values[rank]; the whole array need not
be sorted. It uses an iterative active range and O(1) auxiliary state.

Its pivot is the median value of first/middle/last positions. Worst-case work
has an O(n^2) bound; all-equal input takes one Theta(n) pass. No randomised
expected-linear or median-of-medians worst-linear guarantee is claimed.
Selection does not complete a full Quicksort. Its benchmark compares fresh
copy-plus-selection with fresh copy-plus-Arrays.sort at rank=size/2, not
isolated partition work or repeated queries on a retained sorted array.

## Verification and Empirical Limits

Existing sorting correctness/performance evidence remains in its original
module and runtime. New selection tests compare sorted-copy ranks, complete
multisets and both global partition inequalities, including exhaustive small
duplicates, extreme ints and structured/large inputs. Tests are finite evidence;
the invariants above justify progress and correctness generally.

The [first report](../17-benchmarking/results/divide-and-conquer/README.md)
now completes selection Result/Interpretation with all 16 workflow cells, using
author-reported source `507b253` and JDK 21.0.9/JMH 1.37. Selection means are
lower on RANDOM; copy-plus-sort means are lower on the two ordered fixtures.
Duplicate-heavy size-10,000 intervals overlap. Four selection/sort errors reach
25% of their means. On sorted input the first pivot's equal band contains rank
n/2, so selection uses one full partition; that source fact does not guarantee
it beats a library call. The exact library path was not inspected or profiled.
The report preserves every error/iteration and both timed clones, without
general speed rankings, cache causes, allocation rates or stronger worst-case
guarantees. Existing Sorting evidence retains its separate source/runtime.
