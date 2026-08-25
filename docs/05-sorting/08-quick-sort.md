# Quick Sort

## Purpose

Quick Sort is an efficient comparison-based sorting algorithm founded on the **divide-and-conquer** paradigm.

Unlike Merge Sort, which divides the collection according to position and later combines sorted halves, Quick Sort reorganises the collection around a selected element called the **pivot**.

The partitioning process places elements into regions according to their relationship with the pivot.

For ascending order:

```text
Elements Smaller Than or Equal to Pivot
                |
              Pivot
                |
Elements Greater Than Pivot
```

After partitioning, the pivot occupies a position consistent with the final sorted order.

The algorithm then recursively sorts the regions located to the left and right of the pivot.

Its general strategy is:

```text
Select Pivot
        ↓
Partition Collection
        ↓
Place Pivot in Ordered Position
        ↓
Recursively Sort Left Region
        ↓
Recursively Sort Right Region
```

Quick Sort commonly achieves:

```text
O(n log n)
```

average-case performance.

However, poor pivot selection may produce highly unbalanced partitions and cause:

```text
O(n²)
```

worst-case behaviour.

The standard implementation is generally:

- in-place with respect to array storage;
- unstable;
- comparison-based;
- recursive;
- efficient in practice for arrays;
- sensitive to pivot and partition strategies.

This document studies Quick Sort from algorithmic, mathematical, recursive and software engineering perspectives.

---

## Learning Objectives

After completing this document, the learner should be able to:

- explain the divide-and-conquer strategy used by Quick Sort;
- define the role of the pivot;
- explain partitioning;
- distinguish partitioning from merging;
- trace Quick Sort manually;
- write Quick Sort pseudocode;
- write partition pseudocode;
- distinguish Lomuto and Hoare partitioning;
- define the algorithm's preconditions and postconditions;
- formulate partition invariants;
- reason about recursive correctness;
- analyse best, average and worst-case time complexity;
- explain why partition balance matters;
- analyse recursion depth and auxiliary-space usage;
- explain why the standard implementation is unstable;
- understand the effect of duplicate values;
- compare pivot-selection strategies;
- identify implementation risks and boundary errors;
- design an appropriate Java implementation and testing strategy;
- recognise situations in which Quick Sort is an appropriate engineering choice.

---

# Algorithm Overview

Quick Sort selects an element from the current region and uses it as a pivot.

The remaining elements are rearranged so that:

```text
Elements Smaller Than or Equal to Pivot
```

appear on one side, while:

```text
Elements Greater Than Pivot
```

appear on the other side.

Consider:

```text
[9][4][7][3][10][5]
```

Suppose the pivot is:

```text
5
```

After partitioning, one possible arrangement is:

```text
[4][3] | [5] | [9][10][7]
```

The important property is not the exact order inside each region.

The important property is:

```text
Every value on the left ≤ 5
```

and:

```text
Every value on the right > 5
```

The pivot is now in its final sorted position.

Quick Sort then recursively sorts:

```text
[4][3]
```

and:

```text
[9][10][7]
```

---

# Core Strategy

Quick Sort follows three conceptual phases:

```text
Select
Partition
Recurse
```

More formally:

```text
Choose Pivot
        ↓
Partition Current Region
        ↓
Recursively Sort Left Partition
        ↓
Recursively Sort Right Partition
```

Unlike Merge Sort, Quick Sort performs most of its structural work before the recursive calls.

Conceptually:

```text
Quick Sort

Partition First
        ↓
Recurse Later
```

```text
Merge Sort

Recurse First
        ↓
Merge Later
```

---

# Divide-and-Conquer Structure

Quick Sort applies the divide-and-conquer paradigm.

---

## Divide

The partition operation reorganises the input region around a pivot.

The collection is divided according to value rather than position.

Conceptually:

```text
Original Region

↓

Values ≤ Pivot | Pivot | Values > Pivot
```

---

## Conquer

The left and right regions are sorted recursively.

```text
QUICK-SORT(left region)
```

```text
QUICK-SORT(right region)
```

---

## Combine

Quick Sort does not require a separate merge phase.

Once the partitions are sorted, the complete region is sorted because:

- every element in the left region is not greater than the pivot;
- the pivot separates the regions;
- every element in the right region is not smaller than the pivot;
- both regions are recursively sorted.

Therefore:

```text
Sorted Left
        +
Pivot
        +
Sorted Right
        =
Sorted Complete Region
```

---

# Pivot

The pivot is the element used to divide the current region.

Possible pivot choices include:

- first element;
- last element;
- middle element;
- random element;
- median of selected candidates;
- median-of-three approximation.

The pivot-selection strategy influences:

- partition balance;
- recursion depth;
- practical execution time;
- vulnerability to unfavourable input;
- predictability.

---

# Pivot Is a Value and a Position

Before partitioning, the pivot is selected from some position.

Example:

```text
[9][4][7][3][10][5]
```

Pivot value:

```text
5
```

Initial pivot position:

```text
index 5
```

After partitioning, the pivot may move to another index.

Example:

```text
[4][3][5][9][10][7]
```

Final pivot index:

```text
2
```

At that point, the pivot occupies a position compatible with the sorted result.

---

# Partitioning

Partitioning rearranges a region according to the pivot.

For ascending order, the desired relationship is:

```text
A[left ... pivotIndex - 1] ≤ pivot
```

and:

```text
A[pivotIndex + 1 ... right] > pivot
```

depending on the chosen partition scheme.

The elements within each region are not necessarily sorted after partitioning.

Example:

```text
[4][1][3] | [5] | [9][8][7]
```

The left region satisfies:

```text
all values ≤ 5
```

but:

```text
[4][1][3]
```

is not yet sorted.

Similarly:

```text
[9][8][7]
```

is not sorted.

The recursive calls complete the process.

---

# Partitioning Is Not Sorting

Partitioning establishes a relational structure around the pivot.

It does not fully order either region.

Conceptually:

```text
Partitioning

↓

Correct Side of Pivot
```

not:

```text
Complete Sorted Order
```

This distinction is essential.

---

# Initial State

Consider:

```text
[9][4][7][3][10][5]
```

Complete range:

```text
left = 0
right = 5
```

Suppose the last element is selected as pivot:

```text
pivot = 5
```

Before partitioning:

```text
Unclassified Elements | Pivot
[9][4][7][3][10]      | [5]
```

---

# Intermediate Partition State

During partitioning, the region may be represented as:

```text
Values ≤ Pivot | Values > Pivot | Unclassified | Pivot
```

Example:

```text
[4][3] | [7][9] | [10] | [5]
```

The algorithm progressively moves elements from the unclassified region into the correct partition.

---

# Final Partition State

After partitioning:

```text
[4][3] | [5] | [10][9][7]
```

The pivot occupies its partition index.

The left and right regions remain recursive subproblems.

---

# Base Case

Quick Sort stops when the current region contains zero or one element.

For inclusive boundaries:

```text
left >= right
```

Such a region is already sorted.

Conceptually:

```text
Region Size > 1?

├── Yes → Partition and Recurse
└── No  → Already Sorted
```

---

# Recursive Structure

A general recursive structure is:

```text
QUICK-SORT(A, left, right)

    if left < right

        pivotIndex ← PARTITION(A, left, right)

        QUICK-SORT(A, left, pivotIndex - 1)

        QUICK-SORT(A, pivotIndex + 1, right)
```

The partition operation determines the pivot's final index for the current recursive call.

---

# Formal Problem

Given a collection:

```text
A = [a₀, a₁, a₂, ..., aₙ₋₁]
```

Quick Sort must produce a permutation:

```text
A' = [a'₀, a'₁, a'₂, ..., a'ₙ₋₁]
```

such that, for ascending order:

```text
a'₀ ≤ a'₁ ≤ a'₂ ≤ ... ≤ a'ₙ₋₁
```

The output must contain exactly the same elements as the input.

---

# Preconditions

A standard Quick Sort implementation assumes:

- the input collection exists;
- the collection contains a finite number of elements;
- every pair of elements can be compared consistently;
- the selected index range is valid;
- the collection supports indexed access;
- the collection allows element replacement;
- recursive call depth remains within available stack limits.

For a primitive implementation:

```text
Input: int[]
```

For a generic implementation:

```text
Input: Array of elements and Comparator
```

---

# Postconditions

After the algorithm terminates:

- the complete collection satisfies the ordering criterion;
- every original element remains present;
- no element has been introduced;
- no element has been removed;
- duplicate values remain present;
- the original array contains the sorted result;
- the standard implementation does not guarantee preservation of equivalent-element order.

For ascending order:

```text
A[i] ≤ A[i + 1]
```

for every:

```text
0 ≤ i < n - 1
```

---

# Quick Sort Pseudocode

```text
QUICK-SORT(A, left, right)

    if left >= right

        return

    pivotIndex ← PARTITION(A, left, right)

    QUICK-SORT(A, left, pivotIndex - 1)

    QUICK-SORT(A, pivotIndex + 1, right)
```

This version assumes that partitioning places the pivot in its final index.

---

# Lomuto Partition Scheme

The Lomuto partition scheme commonly selects the last element as the pivot.

Its structure is straightforward and educational.

```text
LOMUTO-PARTITION(A, left, right)

    pivot ← A[right]

    boundary ← left - 1

    for scan ← left to right - 1

        if A[scan] ≤ pivot

            boundary ← boundary + 1

            exchange A[boundary] with A[scan]

    exchange A[boundary + 1] with A[right]

    return boundary + 1
```

The returned index is the final pivot position.

---

# Lomuto Partition Regions

During execution, the current region can be represented as:

```text
A[left ... boundary]          ≤ pivot
A[boundary + 1 ... scan - 1] > pivot
A[scan ... right - 1]         unclassified
A[right]                      pivot
```

Diagram:

```text
≤ Pivot | > Pivot | Unclassified | Pivot
```

As `scan` advances, one unclassified element is processed during every iteration.

---

# Meaning of the Boundary

The variable:

```text
boundary
```

marks the final index of the region containing elements less than or equal to the pivot.

Initially:

```text
boundary = left - 1
```

This means the region:

```text
A[left ... boundary]
```

is empty.

When an element satisfies:

```text
A[scan] ≤ pivot
```

the boundary advances and the element is moved into the left partition.

---

# Lomuto Partition Manual Trace

Consider:

```text
[9][4][7][3][10][5]
```

Pivot:

```text
5
```

Initial state:

```text
boundary = -1
scan = 0
```

Logical regions:

```text
≤ Pivot | > Pivot | Unclassified       | Pivot
          [9][4][7][3][10]             | [5]
```

---

## Examine 9

Compare:

```text
9 ≤ 5
```

False.

`9` belongs to the greater-than-pivot region.

State:

```text
≤ Pivot | > Pivot | Unclassified    | Pivot
          [9]       [4][7][3][10]    | [5]
```

---

## Examine 4

Compare:

```text
4 ≤ 5
```

True.

Advance boundary:

```text
boundary = 0
```

Exchange `A[0]` and `A[1]`.

Result:

```text
[4][9][7][3][10][5]
```

Regions:

```text
≤ Pivot | > Pivot | Unclassified | Pivot
[4]     | [9]     | [7][3][10]   | [5]
```

---

## Examine 7

Compare:

```text
7 ≤ 5
```

False.

Regions:

```text
≤ Pivot | > Pivot | Unclassified | Pivot
[4]     | [9][7]  | [3][10]      | [5]
```

---

## Examine 3

Compare:

```text
3 ≤ 5
```

True.

Advance boundary:

```text
boundary = 1
```

Exchange `A[1]` and `A[3]`.

Result:

```text
[4][3][7][9][10][5]
```

Regions:

```text
≤ Pivot | > Pivot | Unclassified | Pivot
[4][3]  | [7][9]  | [10]         | [5]
```

---

## Examine 10

Compare:

```text
10 ≤ 5
```

False.

Regions:

```text
≤ Pivot | > Pivot      | Pivot
[4][3]  | [7][9][10]   | [5]
```

---

## Place Pivot

Advance conceptual pivot position:

```text
boundary + 1 = 2
```

Exchange:

```text
A[2] ↔ A[5]
```

Result:

```text
[4][3][5][9][10][7]
```

Final partition:

```text
[4][3] | [5] | [9][10][7]
```

Returned pivot index:

```text
2
```

---

# Partition Trace Table

| Scan Index | Value | Comparison with Pivot `5` | Boundary | Array |
|---:|---:|---|---:|---|
| 0 | `9` | `9 ≤ 5` — false | `-1` | `[9, 4, 7, 3, 10, 5]` |
| 1 | `4` | `4 ≤ 5` — true | `0` | `[4, 9, 7, 3, 10, 5]` |
| 2 | `7` | `7 ≤ 5` — false | `0` | `[4, 9, 7, 3, 10, 5]` |
| 3 | `3` | `3 ≤ 5` — true | `1` | `[4, 3, 7, 9, 10, 5]` |
| 4 | `10` | `10 ≤ 5` — false | `1` | `[4, 3, 7, 9, 10, 5]` |
| Final | Pivot `5` | Place at `2` | `2` | `[4, 3, 5, 9, 10, 7]` |

---

# Complete Manual Trace

Consider:

```text
[9][4][7][3][10][5]
```

First partition around `5`:

```text
[4][3] | [5] | [9][10][7]
```

Recursively sort the left region:

```text
[4][3]
```

Suppose pivot:

```text
3
```

Partition:

```text
[] | [3] | [4]
```

Sorted left result:

```text
[3][4]
```

Recursively sort the right region:

```text
[9][10][7]
```

Suppose pivot:

```text
7
```

Partition:

```text
[] | [7] | [10][9]
```

Sort:

```text
[10][9]
```

Pivot:

```text
9
```

Partition:

```text
[] | [9] | [10]
```

Sorted right result:

```text
[7][9][10]
```

Complete result:

```text
[3][4][5][7][9][10]
```

---

# Recursive Call Tree

For the previous input, a simplified recursion tree is:

```text
[9, 4, 7, 3, 10, 5]
             |
          pivot 5
         /       \
    [4, 3]      [9, 10, 7]
       |              |
    pivot 3        pivot 7
    /    \          /    \
  []     [4]      []    [10, 9]
                             |
                          pivot 9
                          /    \
                        []     [10]
```

The tree shape depends on the pivot choices and partition outcomes.

---

# Partition Invariant

For Lomuto partitioning, a suitable loop invariant is:

> Before each iteration with scan index `j`, every element in `A[left ... boundary]` is less than or equal to the pivot, every element in `A[boundary + 1 ... j - 1]` is greater than the pivot, and every element in `A[j ... right - 1]` has not yet been classified.

The pivot remains at:

```text
A[right]
```

until the final exchange.

---

# Partition Correctness

The correctness argument can be divided into:

1. initialisation;
2. maintenance;
3. termination.

---

## Partition Initialisation

Before the first iteration:

```text
boundary = left - 1
scan = left
```

The region:

```text
A[left ... boundary]
```

is empty.

The region:

```text
A[boundary + 1 ... scan - 1]
```

is also empty.

Both required properties hold trivially.

The complete region before the pivot remains unclassified.

---

## Partition Maintenance

Assume the invariant holds before processing:

```text
A[scan]
```

Two cases exist.

### Case 1: Current Element Is Less Than or Equal to Pivot

If:

```text
A[scan] ≤ pivot
```

then:

1. the boundary advances;
2. `A[scan]` is exchanged with `A[boundary]`;
3. the current element joins the less-than-or-equal region;
4. the previous boundary element moves into the greater-than region.

The invariant remains valid.

### Case 2: Current Element Is Greater Than Pivot

If:

```text
A[scan] > pivot
```

no exchange is required.

The element becomes part of the greater-than-pivot region.

The invariant remains valid.

---

## Partition Termination

When the scan finishes:

```text
A[left ... boundary] ≤ pivot
```

and:

```text
A[boundary + 1 ... right - 1] > pivot
```

The pivot is then exchanged with:

```text
A[boundary + 1]
```

After the exchange:

```text
A[left ... boundary] ≤ pivot
```

```text
A[boundary + 1] = pivot
```

```text
A[boundary + 2 ... right] > pivot
```

Therefore, the pivot is correctly positioned relative to the current region.

---

# Recursive Correctness

Quick Sort correctness can be proved through induction on the size of the current region.

---

## Base Case

For a region of size zero or one:

```text
left >= right
```

the region is already sorted.

The algorithm returns.

Therefore, the base case is correct.

---

## Inductive Hypothesis

Assume Quick Sort correctly sorts every region smaller than size:

```text
n
```

---

## Inductive Step

Consider a region of size:

```text
n
```

Partitioning places a pivot into a correct relational position.

After partitioning:

```text
Every left element ≤ pivot
```

and:

```text
Every right element > pivot
```

The left and right regions are smaller than the original region.

By the inductive hypothesis:

- the left recursive call sorts the left region;
- the right recursive call sorts the right region.

Because the pivot separates the two sorted regions according to the ordering relation, the complete region is sorted.

Therefore, Quick Sort is correct for every finite input size.

---

# Termination Argument

Quick Sort terminates if every recursive call processes a strictly smaller region.

After partitioning around pivot index `p`, the recursive ranges are:

```text
left ... p - 1
```

and:

```text
p + 1 ... right
```

The pivot is excluded from both recursive calls.

Therefore, neither subproblem includes the complete original range.

Eventually, each region reaches size zero or one.

---

# Incorrect Recursive Boundaries

Suppose the recursive calls are incorrectly written as:

```text
QUICK-SORT(A, left, pivotIndex)
```

and:

```text
QUICK-SORT(A, pivotIndex, right)
```

If the pivot remains at one of the boundaries, a recursive call may receive the same range repeatedly.

This can cause:

- infinite recursion;
- stack overflow;
- non-termination.

For a scheme that returns the pivot's final index, the pivot must be excluded:

```text
left ... pivotIndex - 1
```

```text
pivotIndex + 1 ... right
```

---

# Permutation Preservation

Quick Sort rearranges elements through exchanges.

An exchange:

```text
A[i] ↔ A[j]
```

changes positions but does not:

- create elements;
- remove elements;
- duplicate elements permanently;
- modify their values.

Every partition preserves the multiset of elements.

Recursive calls also preserve the same property.

Therefore, the complete algorithm produces a permutation of the input.

---

# Time Complexity

Quick Sort's running time depends on partition balance.

The partition operation itself processes the current region in linear time.

For a region of size `n`:

```text
Partition Cost = Θ(n)
```

The recursive cost depends on the sizes of the resulting partitions.

---

# General Recurrence

If the pivot produces partitions of sizes:

```text
k
```

and:

```text
n - k - 1
```

then:

```text
T(n) = T(k) + T(n - k - 1) + Θ(n)
```

The value of `k` determines the recursion-tree shape.

---

# Best-Case Complexity

The best case occurs when the pivot divides the collection into approximately equal regions during every recursive call.

Conceptually:

```text
n
     / \
   n/2 n/2
```

The recurrence is:

```text
T(n) = 2T(n / 2) + Θ(n)
```

This solves to:

```text
T(n) = Θ(n log n)
```

Therefore:

```text
Best Case = O(n log n)
```

---

# Balanced Recursion Tree

For balanced partitions:

```text
Level 0:              n
                    /   \
Level 1:          n/2   n/2
                 / \     / \
Level 2:       n/4 n/4 n/4 n/4
```

At every level, the total partition work is:

```text
Θ(n)
```

The number of levels is:

```text
Θ(log n)
```

Therefore:

```text
Θ(n) × Θ(log n)
        =
Θ(n log n)
```

---

# Average-Case Complexity

For appropriately distributed pivot positions, the partitions are sufficiently balanced on average.

The expected recurrence leads to:

```text
Average Case = O(n log n)
```

Quick Sort's strong practical performance is largely associated with this average-case behaviour and favourable memory-access patterns.

---

# Worst-Case Complexity

The worst case occurs when partitioning repeatedly produces:

```text
One Empty Region
```

and:

```text
One Region of Size n - 1
```

Conceptually:

```text
n
 \
 n - 1
    \
    n - 2
       \
       ...
```

The recurrence becomes:

```text
T(n) = T(n - 1) + Θ(n)
```

Expanding:

```text
T(n) = n + (n - 1) + (n - 2) + ... + 1
```

Therefore:

```text
T(n) = Θ(n²)
```

---

# Worst-Case Example

Suppose the last element is always selected as pivot.

For already sorted input:

```text
[1][2][3][4][5]
```

the pivot is:

```text
5
```

Partition result:

```text
[1][2][3][4] | [5] | []
```

The next pivot is:

```text
4
```

Result:

```text
[1][2][3] | [4] | []
```

This continues until the complete recursion degenerates into a chain.

---

# Reverse-Sorted Worst Case

With the same last-element pivot strategy:

```text
[5][4][3][2][1]
```

the pivot is always the smallest value.

Partition result:

```text
[] | [1] | [5][4][3][2]
```

Again, one subproblem has size:

```text
n - 1
```

This produces quadratic behaviour.

---

# Complexity Summary

| Case | Time Complexity |
|---|---:|
| Best | `O(n log n)` |
| Average | `O(n log n)` |
| Worst | `O(n²)` |

Unlike Merge Sort, Quick Sort does not guarantee `O(n log n)` worst-case execution in its basic form.

---

# Partition Comparison Count

A Lomuto partition over a region of size `n` compares each non-pivot element with the pivot once.

Therefore:

```text
Partition Comparisons = n - 1
```

The total comparison count depends on the recursive partition sizes.

Balanced partitions produce approximately:

```text
O(n log n)
```

comparisons.

Degenerate partitions produce approximately:

```text
O(n²)
```

comparisons.

---

# Space Complexity

Quick Sort does not usually require an auxiliary array proportional to input size.

Partitioning uses a fixed number of variables:

```text
pivot
boundary
scan
temporary
```

The partition operation itself therefore requires:

```text
O(1)
```

auxiliary storage.

However, recursive calls consume stack space.

---

# Recursion-Stack Space

For balanced partitions, recursion depth is:

```text
O(log n)
```

Therefore:

```text
Auxiliary Space = O(log n)
```

in the typical balanced case.

For highly unbalanced partitions, recursion depth may become:

```text
O(n)
```

Therefore:

```text
Worst-Case Auxiliary Space = O(n)
```

---

# In-Place Property

Quick Sort is commonly described as in-place because partitioning rearranges elements within the original array and does not require an auxiliary array of size `n`.

However, recursion-stack memory must still be considered.

A precise statement is:

```text
In-Place Array Partitioning
        +
Recursive Stack Usage
```

Thus, the standard recursive implementation is not constant-space overall.

---

# Limiting Recursion Depth

An implementation may reduce worst-case stack usage by recursively processing the smaller partition first.

Conceptually:

```text
Partition
        ↓
Recurse on Smaller Region
        ↓
Iteratively Continue with Larger Region
```

This technique can limit stack depth to:

```text
O(log n)
```

even when partitions are unbalanced.

It is an example of converting one recursive branch into iteration.

---

# Stability

The standard Quick Sort implementation is unstable.

Partitioning exchanges elements across potentially distant positions.

These exchanges may change the relative order of elements with equivalent keys.

---

# Stability Example

Consider:

```text
[A₁, key 4]
[B,  key 2]
[A₂, key 4]
[C,  key 1]
```

A partition operation may move:

```text
A₂
```

before:

```text
A₁
```

even though both have the same key.

A possible sorted result is:

```text
[C,  key 1]
[B,  key 2]
[A₂, key 4]
[A₁, key 4]
```

The key order is correct.

However, the original relative order of equivalent elements has changed.

Therefore, the algorithm is unstable.

---

# Stable Quick Sort Variations

A stable variation is possible by placing elements into separate auxiliary collections.

Conceptually:

```text
Less Than Pivot
Equal to Pivot
Greater Than Pivot
```

Then:

```text
Stable Quick Sort Less Region
        +
Equal Region
        +
Stable Quick Sort Greater Region
```

This approach may preserve order but requires additional memory.

The usual in-place advantage is therefore reduced.

Algorithm properties depend on the exact implementation.

---

# Adaptability

Standard Quick Sort is not usually classified as adaptive.

Already sorted input does not necessarily reduce the required work.

In fact, with a poor pivot strategy, already sorted input may trigger the worst case.

Example:

```text
Sorted Input
        +
Last-Element Pivot
        =
Highly Unbalanced Partitions
```

Therefore, pre-existing order may harm rather than improve performance.

---

# Pivot-Selection Strategies

Pivot selection is central to Quick Sort performance.

Common strategies include:

- first element;
- last element;
- middle element;
- random element;
- median of three;
- more advanced sampling techniques.

---

# First-Element Pivot

The first element is easy to select.

```text
pivot = A[left]
```

Advantages:

- simple implementation;
- no additional selection work.

Disadvantages:

- vulnerable to already sorted input;
- vulnerable to reverse-sorted input;
- may repeatedly produce unbalanced partitions.

---

# Last-Element Pivot

The last element is frequently used with Lomuto partitioning.

```text
pivot = A[right]
```

Advantages:

- simple Lomuto implementation;
- clear educational invariant.

Disadvantages:

- vulnerable to already sorted data;
- vulnerable to reverse-sorted data;
- may perform many unnecessary exchanges with duplicates.

---

# Middle-Element Pivot

The middle index may be selected:

```text
middle = left + (right - left) / 2
```

This avoids choosing an extreme position in already sorted arrays.

However, the middle-position value is not necessarily close to the median value.

Therefore, balanced partitions are not guaranteed.

---

# Random Pivot

A random index is selected from the current region.

Conceptually:

```text
pivotIndex = random(left, right)
```

The selected pivot may be exchanged into the position expected by the partition scheme.

Random selection reduces the probability that a predictable input arrangement consistently produces worst-case partitions.

It does not eliminate the theoretical worst case.

However, it improves expected robustness.

---

# Median-of-Three Pivot

Median-of-three selection examines:

```text
first element
middle element
last element
```

and chooses the median of these three values.

Conceptually:

```text
Candidates

A[left]
A[middle]
A[right]

↓

Choose Middle Value
```

This strategy often avoids selecting an extreme value from ordered or partially ordered inputs.

It adds comparison overhead but may improve partition balance.

---

# True Median Pivot

If the exact median value were selected during every partition, the partitions would be optimally balanced.

However, finding the exact median introduces additional work.

Therefore, practical implementations usually use approximations rather than computing the exact median during every recursive call.

---

# Pivot Selection Trade-Off

Pivot selection balances:

```text
Selection Cost
        ↔
Partition Quality
```

A more sophisticated strategy may:

- require additional comparisons;
- reduce recursion imbalance;
- improve worst practical cases;
- complicate implementation.

A simpler strategy may:

- reduce local overhead;
- produce poor partitions on structured inputs.

---

# Duplicate Values

Duplicate values require careful consideration.

Suppose:

```text
[5][5][5][5][5]
```

With Lomuto partitioning and condition:

```text
A[scan] ≤ pivot
```

every element joins the left partition.

The pivot may finish at the final position.

The recursive problem sizes become:

```text
n - 1
```

and:

```text
0
```

This may produce quadratic behaviour.

---

# Two-Way Partitioning with Duplicates

Traditional two-way partitioning separates values into:

```text
≤ Pivot
```

and:

```text
> Pivot
```

or:

```text
< Pivot
```

and:

```text
≥ Pivot
```

When many elements equal the pivot, those equivalent elements may accumulate on one side.

The resulting partitions may be unbalanced.

---

# Three-Way Partitioning

Three-way partitioning divides the collection into:

```text
Values < Pivot
Values = Pivot
Values > Pivot
```

Conceptually:

```text
< Pivot | = Pivot | > Pivot
```

The equal region does not require further recursive sorting.

This can substantially improve performance for duplicate-heavy data.

---

# Three-Way Quick Sort Structure

```text
THREE-WAY-QUICK-SORT(A, left, right)

    if left >= right

        return

    pivot ← A[left]

    lower ← left

    scan ← left + 1

    upper ← right

    while scan ≤ upper

        if A[scan] < pivot

            exchange A[lower] with A[scan]

            lower ← lower + 1

            scan ← scan + 1

        else if A[scan] > pivot

            exchange A[scan] with A[upper]

            upper ← upper - 1

        else

            scan ← scan + 1

    THREE-WAY-QUICK-SORT(A, left, lower - 1)

    THREE-WAY-QUICK-SORT(A, upper + 1, right)
```

After partitioning:

```text
A[left ... lower - 1] < pivot
```

```text
A[lower ... upper] = pivot
```

```text
A[upper + 1 ... right] > pivot
```

---

# Three-Way Partition Benefit

Consider:

```text
[4][2][4][4][1][4][3]
```

A three-way partition around `4` may produce:

```text
[2][1][3] | [4][4][4][4] | []
```

The middle region is already complete.

Only:

```text
[2][1][3]
```

requires recursive sorting.

This reduces unnecessary recursive work.

---

# Hoare Partition Scheme

The Hoare partition scheme uses two indices moving toward each other.

It commonly chooses the first or middle element as pivot.

Conceptually:

```text
Left Pointer  →      ← Right Pointer
```

The left pointer searches for an element that belongs on the right.

The right pointer searches for an element that belongs on the left.

The misplaced elements are exchanged.

---

# Hoare Partition Pseudocode

A common form is:

```text
HOARE-PARTITION(A, left, right)

    pivot ← A[left + (right - left) / 2]

    i ← left - 1

    j ← right + 1

    while true

        repeat

            i ← i + 1

        until A[i] ≥ pivot

        repeat

            j ← j - 1

        until A[j] ≤ pivot

        if i ≥ j

            return j

        exchange A[i] with A[j]
```

The returned index is a partition boundary.

It is not necessarily the final index of a specific pivot element.

---

# Recursive Calls with Hoare Partition

When Hoare partitioning returns:

```text
boundary
```

the recursive calls are typically:

```text
QUICK-SORT(A, left, boundary)
```

and:

```text
QUICK-SORT(A, boundary + 1, right)
```

This differs from Lomuto partitioning.

For Lomuto:

```text
pivotIndex - 1
pivotIndex + 1
```

For Hoare:

```text
boundary
boundary + 1
```

The two schemes must not be mixed.

---

# Lomuto versus Hoare Partitioning

| Property | Lomuto | Hoare |
|---|---|---|
| Typical pivot | Last element | First or middle element |
| Returned value | Final pivot index | Partition boundary |
| Pointer structure | One scan and one boundary | Two inward-moving pointers |
| Exchanges | Often more | Often fewer |
| Conceptual simplicity | High | Moderate |
| Duplicate handling | May be weak | Often better, but still requires care |
| Recursive boundaries | Exclude pivot index | Split at returned boundary |

Both schemes can be correct when their invariants and recursive boundaries are implemented consistently.

---

# Partition Scheme Consistency

A major implementation principle is:

> The recursive boundaries must correspond to the selected partition scheme.

Incorrect combination:

```text
Hoare Partition Return Value
        +
Lomuto Recursive Boundaries
```

may cause:

- missing elements;
- repeated ranges;
- infinite recursion;
- incomplete sorting.

The implementation should clearly document which partition scheme it uses.

---

# Descending Quick Sort

To sort in descending order, the partition comparisons are reversed.

Ascending Lomuto condition:

```text
A[scan] ≤ pivot
```

Descending condition:

```text
A[scan] ≥ pivot
```

The recursive structure remains unchanged.

Only the ordering relation changes.

---

# Generic Comparison Policy

A reusable implementation should separate:

```text
Quick Sort Strategy
```

from:

```text
Ordering Criterion
```

Conceptually:

```text
Quick Sort
        +
Comparator
        =
Reusable Sorting Operation
```

Possible criteria include:

- ascending numerical order;
- descending numerical order;
- alphabetical order;
- transaction amount;
- event timestamp;
- employee priority;
- product price and identifier.

---

# Comparison-Based Property

Quick Sort is comparison-based.

Partitioning relies on comparisons between elements and the pivot.

It does not require:

- bounded integer ranges;
- digit extraction;
- direct key indexing;
- hashing.

Therefore, Quick Sort can operate on any data type with a consistent ordering relation.

---

# Internal Sorting

Standard Quick Sort is an internal sorting algorithm.

It assumes that the complete collection is available in main memory.

It is particularly associated with arrays because:

- indexed access is efficient;
- exchanges are direct;
- partitioning has good locality;
- no auxiliary merge array is required.

Quick Sort is not naturally suited to external sorting because its access and partition patterns do not provide the same sequential advantages as external Merge Sort.

---

# Recursive Structure

Quick Sort is naturally recursive because each partition creates smaller instances of the same problem.

```text
Sort Current Region
        ↓
Partition
        ↓
Sort Left Region
        ↓
Sort Right Region
```

However, iterative implementations are also possible through an explicit stack.

---

# Iterative Quick Sort

An iterative implementation stores pending ranges explicitly.

Conceptually:

```text
Push Initial Range

↓

While Stack Not Empty

    Pop Range

    Partition Range

    Push Unsorted Subranges
```

This replaces the language call stack with a manually managed data structure.

The asymptotic space requirement still depends on partition balance and push order.

---

# Tail-Recursion Optimisation

One recursive call can be replaced by iteration.

Example strategy:

```text
while left < right

    pivotIndex ← partition

    if left partition is smaller

        recurse on left partition

        left ← pivotIndex + 1

    else

        recurse on right partition

        right ← pivotIndex - 1
```

This ensures that recursion is applied only to the smaller subproblem.

The larger subproblem is handled by the loop.

This can reduce stack risk.

---

# Java Implementation Considerations

A basic integer-array design may follow this structure:

```java
public final class QuickSort {

    private QuickSort() {
        throw new AssertionError("Utility class");
    }

    public static void sort(int[] values) {
        // Public entry point.
    }

    private static void sort(
            int[] values,
            int left,
            int right
    ) {
        // Recursive Quick Sort.
    }

    private static int partition(
            int[] values,
            int left,
            int right
    ) {
        // Partition implementation.
    }
}
```

The public method hides recursive index management from the caller.

---

# Suggested Method Contract

```java
/**
 * Sorts the supplied array in ascending numerical order
 * using Quick Sort.
 *
 * <p>The operation modifies the input array in place.
 * The implementation uses recursive stack space whose depth
 * depends on partition balance.</p>
 *
 * @param values array to be sorted
 * @throws NullPointerException if {@code values} is {@code null}
 */
public static void sort(int[] values) {
    // ...
}
```

The contract should make mutation and recursion behaviour clear.

---

# Public Entry Point

The public method may:

1. validate the array;
2. return immediately for trivial input;
3. invoke the internal recursive method.

Example:

```java
public static void sort(int[] values) {
    Objects.requireNonNull(values, "values must not be null");

    if (values.length < 2) {
        return;
    }

    sort(values, 0, values.length - 1);
}
```

---

# Example Lomuto Structure

```java
private static void sort(
        int[] values,
        int left,
        int right
) {
    if (left >= right) {
        return;
    }

    int pivotIndex = partition(values, left, right);

    sort(values, left, pivotIndex - 1);
    sort(values, pivotIndex + 1, right);
}
```

Partition:

```java
private static int partition(
        int[] values,
        int left,
        int right
) {
    int pivot = values[right];
    int boundary = left - 1;

    for (int scan = left; scan < right; scan++) {
        if (values[scan] <= pivot) {
            boundary++;
            swap(values, boundary, scan);
        }
    }

    int pivotIndex = boundary + 1;
    swap(values, pivotIndex, right);

    return pivotIndex;
}
```

---

# Exchange Helper

```java
private static void swap(
        int[] values,
        int first,
        int second
) {
    if (first == second) {
        return;
    }

    int temporary = values[first];
    values[first] = values[second];
    values[second] = temporary;
}
```

Avoiding self-exchanges may reduce assignments.

However, the branch itself also has a cost.

The practical effect should be measured rather than assumed.

---

# Generic Java Design

A generic implementation may accept:

```text
Comparator<? super T>
```

Example signature:

```text
public static <T> void sort(
        T[] values,
        Comparator<? super T> comparator
) {
    // ...
}
```

Partition comparison:

```text
if (comparator.compare(values[scan], pivot) <= 0) {
    // Move to left partition.
}
```

Because Quick Sort is normally unstable, strict versus non-strict comparison affects partition distribution but does not automatically provide stability.

---

# Pivot Object Reference

In a generic implementation, the pivot value should be stored safely before exchanges begin.

Example:

```text
T pivot = values[right];
```

Even if elements move during partitioning, the pivot reference remains available.

For mutable objects, the comparison criterion should remain consistent throughout the sort.

Objects should not change comparison-relevant state while sorting is in progress.

---

# Comparator Consistency

A comparator should provide a coherent ordering.

Important properties include:

### Directional Consistency

If:

```text
compare(A, B) < 0
```

then:

```text
compare(B, A) > 0
```

### Transitivity

If:

```text
A < B
```

and:

```text
B < C
```

then:

```text
A < C
```

### Stable Comparison State

The comparison result should not change unexpectedly during execution.

An inconsistent comparator may cause:

- incorrect partition relationships;
- unpredictable output;
- difficult-to-reproduce defects;
- violation of sorting postconditions.

---

# Safe Midpoint Calculation

When selecting the middle position as a pivot candidate, calculate:

```text
int middle = left + (right - left) / 2;
```

rather than:

```text
int middle = (left + right) / 2;
```

The first expression avoids potential integer overflow.

---

# Random Pivot Implementation

A random pivot may be selected and moved to the final position expected by Lomuto partitioning.

Conceptually:

```text
int pivotIndex = randomIndexBetween(left, right);
swap(values, pivotIndex, right);
return partitionUsingLastElement(values, left, right);
```

Random generation should not be recreated unnecessarily during every recursive call.

A reusable random source may be passed or stored according to project design.

---

# Deterministic Testing of Randomised Quick Sort

Random pivot selection complicates deterministic testing.

Possible approaches include:

- inject a seeded random generator;
- separate pivot-selection policy from partitioning;
- test partition correctness independently;
- use a deterministic strategy in unit tests;
- use repeated randomised property tests.

The sorting result should remain deterministic for a total ordering, even though the internal pivot sequence may vary.

However, equivalent-element order may vary because the algorithm is unstable.

---

# Strategy Pattern Opportunity

Pivot selection can be represented as a strategy.

Conceptually:

```text
Quick Sort
        +
Pivot Selection Strategy
```

Possible strategies:

```text
First Element
Last Element
Middle Element
Random Element
Median of Three
```

A Java design might define:

```text
@FunctionalInterface
public interface PivotSelector {

    int selectPivotIndex(
            int[] values,
            int left,
            int right
    );
}
```

This separates:

```text
Sorting Algorithm
```

from:

```text
Pivot Policy
```

Such abstraction is useful for experimentation, but should not obscure the fundamental algorithm in the initial implementation.

---

# Partition Strategy Abstraction

Partition schemes may also be separated conceptually.

```text
Quick Sort
        +
Partition Strategy
```

Possible choices:

```text
Lomuto
Hoare
Three-Way
```

However, each partition strategy may require different recursive boundaries.

Therefore, a generic abstraction must express its result clearly.

A simple pivot index is not sufficient for every scheme.

Three-way partitioning, for example, returns an equal-value range rather than one pivot position.

---

# Common Implementation Errors

Typical Quick Sort defects include:

- missing or incorrect base case;
- including the pivot in recursive calls;
- using boundaries that do not shrink;
- confusing Lomuto and Hoare return values;
- selecting a pivot but failing to move it as expected;
- comparing the pivot position after it has changed;
- using incorrect scan boundaries;
- accessing beyond the array;
- failing to place the pivot after partitioning;
- using inconsistent comparison conditions;
- mishandling duplicate values;
- causing excessive recursion depth;
- forgetting to validate `null`;
- mixing inclusive and exclusive index conventions.

---

# Scan Boundary Error

For Lomuto partitioning with the last element as pivot, the scan must stop before the pivot:

```text
for (int scan = left; scan < right; scan++)
```

Using:

```text
scan <= right
```

would compare the pivot with itself and may disrupt the intended boundary logic.

---

# Boundary Initialisation Error

For Lomuto partitioning:

```text
boundary = left - 1
```

represents an initially empty less-than-or-equal region.

Initialising:

```text
boundary = left
```

without changing the rest of the algorithm may produce incorrect exchanges and pivot placement.

---

# Pivot Placement Error

At the end of Lomuto partitioning, the pivot belongs at:

```text
boundary + 1
```

The final exchange should be:

```text
A[boundary + 1] ↔ A[right]
```

Returning:

```text
boundary
```

instead of:

```text
boundary + 1
```

produces incorrect recursive ranges.

---

# Mixing Range Conventions

An implementation may use inclusive boundaries:

```text
[left, right]
```

or half-open boundaries:

```text
[from, to)
```

Both are valid.

However, the base case, pivot selection, partition scan and recursive calls must follow the same convention.

Mixing conventions may cause:

- skipped elements;
- invalid access;
- repeated ranges;
- incomplete sorting.

---

# Stack Overflow Risk

Poor partition balance may produce recursion depth:

```text
O(n)
```

For sufficiently large arrays, this can cause:

```text
StackOverflowError
```

Possible mitigations include:

- random pivot selection;
- median-of-three selection;
- three-way partitioning;
- recursion on the smaller partition first;
- iterative handling of the larger partition;
- introspective fallback strategies.

---

# Introspective Sorting

An introspective sorting strategy begins with Quick Sort but monitors recursion depth.

Conceptually:

```text
Start with Quick Sort

↓

Recursion Depth Too Large?

├── No  → Continue Quick Sort
└── Yes → Switch to Guaranteed Algorithm
```

A fallback such as Heap Sort can provide stronger worst-case guarantees.

This family of approaches combines:

- Quick Sort's practical average performance;
- protection against quadratic degeneration.

A complete introspective sort is beyond the scope of the elementary implementation, but the concept illustrates advanced algorithm engineering.

---

# Small-Partition Optimisation

Quick Sort may switch to Insertion Sort when partitions become small.

Conceptually:

```text
Partition Size > Threshold?

├── Yes → Continue Quick Sort
└── No  → Use Insertion Sort
```

Reasons include:

- lower overhead;
- simple loop structure;
- effective small-input behaviour;
- good memory locality.

The threshold should be evaluated experimentally rather than selected without evidence.

---

# Deferred Small-Partition Sorting

Another hybrid strategy allows Quick Sort to stop partitioning below a threshold.

After the recursive process finishes, one complete Insertion Sort pass sorts the nearly ordered array.

Conceptually:

```text
Quick Sort Large Regions
        ↓
Leave Small Regions Unsorted
        ↓
Insertion Sort Complete Array
```

Because the array is already partially structured, Insertion Sort may complete efficiently.

---

# Testing Strategy

Quick Sort should be tested using representative input categories.

---

## Empty Array

```text
Input

[]

Expected

[]
```

The public method should terminate immediately.

---

## Single Element

```text
Input

[7]

Expected

[7]
```

No partition is required.

---

## Two Elements in Order

```text
Input

[2][5]

Expected

[2][5]
```

---

## Two Elements Reversed

```text
Input

[5][2]

Expected

[2][5]
```

This verifies the smallest non-trivial partition.

---

## Already Sorted Array

```text
Input

[1][2][3][4][5]

Expected

[1][2][3][4][5]
```

This case is especially important for detecting poor pivot behaviour and recursion-depth risk.

---

## Reverse-Sorted Array

```text
Input

[5][4][3][2][1]

Expected

[1][2][3][4][5]
```

---

## Random Order

```text
Input

[9][4][7][3][10][5]

Expected

[3][4][5][7][9][10]
```

---

## Duplicate Values

```text
Input

[4][2][4][1][2]

Expected

[1][2][2][4][4]
```

Every duplicate must remain present.

---

## All Equal Values

```text
Input

[6][6][6][6]

Expected

[6][6][6][6]
```

This case is important for analysing duplicate handling and partition balance.

---

## Negative Values

```text
Input

[-3][7][-8][2][0]

Expected

[-8][-3][0][2][7]
```

---

## Mixed Extremes

```text
Input

[Integer.MAX_VALUE][0][Integer.MIN_VALUE]
```

Expected:

```text
[Integer.MIN_VALUE][0][Integer.MAX_VALUE]
```

The implementation should compare values safely without subtraction-based overflow.

---

## Odd-Length Array

```text
Input

[8][3][6][2][7]

Expected

[2][3][6][7][8]
```

---

## Even-Length Array

```text
Input

[8][3][6][2]

Expected

[2][3][6][8]
```

---

# Avoiding Subtraction Comparisons

Generic numerical comparison should not be implemented as:

```text
return first - second;
```

This may overflow for extreme values.

Safer comparison methods include:

```text
Integer.compare(first, second);
```

For primitive Quick Sort conditions:

```text
values[scan] <= pivot
```

does not introduce subtraction overflow.

---

# Testing Partition Independently

Partitioning can be tested separately from complete sorting.

Given the returned pivot index `p`, verify:

```text
A[i] ≤ A[p]
```

for every:

```text
left ≤ i < p
```

and:

```text
A[i] > A[p]
```

for every:

```text
p < i ≤ right
```

according to the exact partition contract.

Also verify:

- all original elements remain present;
- the pivot value appears at the returned index;
- no index outside the selected range is modified.

---

# Partition Contract Test Example

Input:

```text
[9][4][7][3][10][5]
```

After Lomuto partition:

```text
[4][3][5][9][10][7]
```

Pivot index:

```text
2
```

Verify:

```text
[4][3] ≤ 5
```

and:

```text
[9][10][7] > 5
```

The left and right regions do not need to be internally sorted.

---

# Testing Correctness Properties

Tests should verify:

## Ordering Property

```text
A[i] ≤ A[i + 1]
```

for every valid index.

## Permutation Property

The output contains the same values and frequencies as the input.

## Mutation Property

The original array contains the sorted result.

## Partition Property

Every partition call satisfies its documented relational contract.

## Boundary Correctness

Recursive calls eventually reach valid base cases.

## Duplicate Preservation

All equal and repeated values remain present.

---

# Testing Instability

Instability should not normally be asserted as an exact expected output because different valid implementations may reorder equivalent elements differently.

Instead, the documentation should state:

```text
Equivalent-element order is not guaranteed.
```

Tests should verify ordering by key and element preservation, not a stable relative order.

If a particular implementation accidentally preserves order for some input, that does not make the algorithm generally stable.

---

# Example JUnit Structure

```java
class QuickSortTest {

    @Test
    void shouldSortValuesInAscendingOrder() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldHandleEmptyArray() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldHandleSingleElement() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldSortTwoReversedElements() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldPreserveDuplicateValues() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldHandleAllEqualValues() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldHandleAlreadySortedArray() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldHandleReverseSortedArray() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldHandleNegativeValues() {
        // Arrange
        // Act
        // Assert
    }
}
```

---

# Randomised Testing

Quick Sort can be tested against Java's standard sorting operation.

Conceptually:

```text
Generate Random Array

↓

Create Two Copies

↓

Sort First Copy with Quick Sort

↓

Sort Second Copy with Arrays.sort

↓

Compare Results
```

This helps validate many:

- pivot positions;
- partition arrangements;
- duplicate distributions;
- input sizes.

Randomised testing should complement deterministic edge cases.

---

# Property-Based Testing Perspective

Useful properties include:

```text
Result Is Ordered
```

```text
Result Has Same Length
```

```text
Result Contains Same Frequencies
```

```text
Sorting Twice Produces Same Result
```

Formally:

```text
sort(sort(A)) = sort(A)
```

This is the idempotence property.

Another useful property is:

```text
Minimum Result Element = Minimum Input Element
```

and:

```text
Maximum Result Element = Maximum Input Element
```

for non-empty arrays.

---

# Testing Recursion Depth

An educational implementation may expose or internally measure maximum recursion depth.

Relevant inputs include:

```text
Already Sorted
Reverse Sorted
Random
All Equal
```

With last-element pivot selection, sorted and reverse-sorted inputs may reveal linear recursion depth.

With random or median-based selection, observed depth should generally improve.

---

# Measuring Operations

An educational implementation may count:

```text
Comparisons
Exchanges
Partition Calls
Recursive Calls
Maximum Recursion Depth
Partition Sizes
```

These measurements help connect theoretical analysis with actual behaviour.

Possible observations:

```text
Balanced Input

Depth ≈ log₂ n
```

```text
Degenerate Input

Depth ≈ n
```

---

# Measuring Partition Balance

For each partition, record:

```text
Left Size
Right Size
```

A balance metric may compare the smaller partition with the complete region.

Conceptually:

```text
Balance Ratio =
min(leftSize, rightSize) / currentSize
```

Values near:

```text
0.5
```

indicate balanced partitions.

Values near:

```text
0
```

indicate highly unbalanced partitions.

This provides an experimental way to study pivot quality.

---

# Benchmarking Considerations

Quick Sort may be benchmarked using:

```text
Random Data
Already Sorted Data
Reverse-Sorted Data
Nearly Sorted Data
Duplicate-Heavy Data
All Equal Data
Small Arrays
Large Arrays
```

Different pivot and partition strategies should be evaluated separately.

---

# Benchmark Questions

Useful experimental questions include:

- How does pivot selection affect execution time?
- How does partition balance affect recursion depth?
- How does Lomuto compare with Hoare partitioning?
- How do duplicates affect two-way partitioning?
- When does three-way partitioning become beneficial?
- What small-partition threshold works well?
- How does Quick Sort compare with Merge Sort?
- How much stack space is used?
- How many exchanges occur?

---

# Benchmarking Input Restoration

Sorting mutates the input array.

Therefore, every benchmark invocation must receive an array with the intended initial distribution.

Incorrect benchmark:

```text
Sort Same Array Repeatedly
```

After the first invocation, the array is already sorted.

Correct structure:

```text
Original Dataset

↓

Copy Before Each Invocation

↓

Sort Copy
```

JMH state setup should be designed carefully to avoid measuring array construction when the intended measurement is sorting alone.

---

# Benchmarking Comparison with Merge Sort

Quick Sort and Merge Sort both commonly provide:

```text
O(n log n)
```

performance.

However, practical results may differ because of:

- auxiliary-array allocation;
- recursion behaviour;
- memory locality;
- copying;
- branch behaviour;
- pivot quality;
- data distribution.

Quick Sort often benefits from in-place partitioning and cache-friendly array access.

Merge Sort provides more predictable worst-case time.

---

# Benchmarking Comparison with Insertion Sort

For small arrays, Insertion Sort may outperform Quick Sort because it avoids:

- recursive calls;
- partition setup;
- pivot selection;
- multiple small subproblems.

This motivates hybrid implementations.

Expected pattern:

```text
Small n

Insertion Sort may be faster
```

```text
Large n

Quick Sort's n log n growth becomes advantageous
```

---

# Benchmarking Warning

Reliable Java microbenchmarking should use JMH.

Manual timing may be distorted by:

- JVM warm-up;
- just-in-time compilation;
- dead-code elimination;
- garbage collection;
- branch prediction;
- random-number generation;
- stack behaviour;
- input reuse;
- measurement noise.

Benchmarking should complement, not replace, theoretical analysis.

---

# Advantages

Quick Sort offers several important advantages.

## Strong Average Performance

Its average-case running time is:

```text
O(n log n)
```

---

## In-Place Partitioning

The standard array implementation does not require an auxiliary array proportional to input size.

---

## Good Array Locality

Partitioning processes nearby array elements and often interacts favourably with memory hierarchies.

---

## Low Additional Allocation

A basic implementation mainly uses recursive stack frames and a fixed number of local variables.

---

## Flexible Pivot Strategies

Pivot selection can be adapted to expected input characteristics.

---

## Hybrid Potential

Quick Sort can be combined with:

- Insertion Sort;
- three-way partitioning;
- introspective fallback;
- randomised selection;
- median-of-three selection.

---

## Parallelisation Potential

After partitioning, the left and right regions are independent.

Conceptually:

```text
Partition
        ↓
-------------------
↓                 ↓
Sort Left     Sort Right
```

These regions may potentially be processed concurrently.

---

# Limitations

Quick Sort also has important limitations.

## Quadratic Worst Case

Poor partition balance may produce:

```text
O(n²)
```

running time.

---

## Recursion-Depth Risk

Degenerate partitions may require:

```text
O(n)
```

stack depth.

---

## Instability

The standard in-place implementation does not preserve equivalent-element order.

---

## Pivot Sensitivity

Performance depends strongly on pivot quality.

---

## Duplicate-Heavy Input

Two-way partitioning may perform poorly when many values equal the pivot.

---

## Implementation Complexity

Correct behaviour requires consistency between:

- partition scheme;
- pivot policy;
- range convention;
- recursive boundaries.

---

## Difficult Predictability

Unlike Merge Sort, basic Quick Sort does not provide a fixed worst-case `O(n log n)` guarantee.

---

# When Quick Sort May Be Appropriate

Quick Sort may be appropriate when:

- arrays are being sorted in memory;
- strong average performance is required;
- auxiliary array allocation should be avoided;
- stability is unnecessary;
- input distributions are not adversarial;
- randomised or robust pivot selection is available;
- memory locality matters;
- hybrid optimisation is acceptable;
- average throughput is more important than strict worst-case guarantees.

---

# When Quick Sort Should Not Be Preferred

It may be less suitable when:

- stable sorting is required;
- guaranteed `O(n log n)` worst-case time is mandatory;
- recursion-depth risk cannot be tolerated;
- the dataset contains many duplicate keys and only two-way partitioning is available;
- the collection is externally stored;
- a standard-library sorting operation already provides the required behaviour;
- untrusted input could intentionally trigger poor partitions.

---

# Comparison with Selection Sort

| Property | Quick Sort | Selection Sort |
|---|---|---|
| Main strategy | Partition around pivot | Repeated minimum selection |
| Best-case time | `O(n log n)` | `O(n²)` |
| Average time | `O(n log n)` | `O(n²)` |
| Worst-case time | `O(n²)` | `O(n²)` |
| Stable | Usually no | No |
| In place | Yes, excluding stack | Yes |
| Adaptive | No | No |
| Recursion | Yes | No |
| Large inputs | Usually suitable | Unsuitable |
| Exchanges | Depends on partitioning | At most `O(n)` |

---

# Comparison with Bubble Sort

| Property | Quick Sort | Bubble Sort — optimised |
|---|---|---|
| Best-case time | `O(n log n)` in standard analysis | `O(n)` |
| Average time | `O(n log n)` | `O(n²)` |
| Worst-case time | `O(n²)` | `O(n²)` |
| Stable | Usually no | Yes |
| In place | Yes, excluding stack | Yes |
| Adaptive | Generally no | Yes |
| Main movement | Partition exchanges | Adjacent exchanges |
| Large inputs | Usually suitable | Unsuitable |

Bubble Sort has a linear best case for already sorted input, but Quick Sort offers far stronger general scalability.

---

# Comparison with Insertion Sort

| Property | Quick Sort | Insertion Sort |
|---|---|---|
| Best-case time | `O(n log n)` standard | `O(n)` |
| Average time | `O(n log n)` | `O(n²)` |
| Worst-case time | `O(n²)` | `O(n²)` |
| Stable | Usually no | Yes |
| In place | Yes, excluding stack | Yes |
| Adaptive | No | Yes |
| Small inputs | More overhead | Often effective |
| Large random inputs | Strong | Poor scalability |
| Hybrid relationship | Uses Insertion Sort for small regions | Supports Quick Sort |

---

# Comparison with Merge Sort

| Property | Quick Sort | Merge Sort |
|---|---|---|
| Main strategy | Partition around pivot | Divide and merge |
| Best-case time | `O(n log n)` | `O(n log n)` |
| Average time | `O(n log n)` | `O(n log n)` |
| Worst-case time | `O(n²)` | `O(n log n)` |
| Stable | Usually no | Yes |
| Auxiliary array | Not required | `O(n)` for standard arrays |
| Stack depth | `O(log n)` average, `O(n)` worst | `O(log n)` |
| Adaptability | Generally no | Generally no |
| Array locality | Often strong | Sequential merge access |
| External sorting | Less suitable | Highly suitable |
| Predictability | Pivot-dependent | Strong |

---

# Quick Sort versus Merge Sort Strategy

Merge Sort:

```text
Divide by Position
        ↓
Recursively Sort
        ↓
Merge Ordered Results
```

Quick Sort:

```text
Select Pivot
        ↓
Partition by Value
        ↓
Recursively Sort
```

Merge Sort performs its combine work after recursion.

Quick Sort performs its division work before recursion.

---

# Engineering Perspective

Quick Sort demonstrates that divide-and-conquer algorithms depend not only on problem size, but also on subproblem balance.

Conceptually:

```text
Same Input Size
        +
Different Pivot Choices
        =
Different Recursion Trees
```

A balanced recursion tree produces:

```text
O(n log n)
```

work.

A degenerate recursion tree produces:

```text
O(n²)
```

work.

Therefore, algorithm analysis must consider how the input is divided, not merely whether division occurs.

---

# Time-Space Trade-Off

Quick Sort commonly exchanges worst-case predictability for reduced auxiliary-array usage.

Conceptually:

```text
Quick Sort

Lower Array Memory
        ↔
Possible O(n²) Time
```

```text
Merge Sort

Predictable O(n log n) Time
        ↔
O(n) Auxiliary Array
```

Neither strategy is universally superior.

The engineering decision depends on:

- memory limits;
- stability requirements;
- latency guarantees;
- data structure;
- input distribution;
- security considerations;
- implementation quality.

---

# Security and Adversarial Input

When input may be controlled by external users, a deterministic poor pivot strategy may be risky.

An adversarial sequence could intentionally trigger highly unbalanced partitions.

Consequences may include:

- excessive CPU usage;
- deep recursion;
- stack overflow;
- degraded service availability.

Possible mitigations include:

- randomised pivot selection;
- introspective fallback;
- median-based sampling;
- recursion-depth limits;
- standard-library implementations.

This demonstrates that algorithmic worst cases can become operational reliability concerns.

---

# Backend Engineering Perspective

Backend systems may sort:

- API response collections;
- transaction records;
- event batches;
- report data;
- cached entities;
- in-memory search results.

Before applying an in-memory algorithm, engineers should consider whether ordering belongs in:

```text
Database Query
```

```text
Application Memory
```

```text
Distributed Processing Layer
```

For database-backed pagination, ordering should generally be delegated to the database with deterministic keys.

Handwritten Quick Sort is rarely appropriate for ordinary production application code.

Its implementation remains valuable for understanding:

- partitioning;
- recursive complexity;
- worst-case risk;
- memory trade-offs;
- hybrid algorithm design.

---

# Deterministic Pagination

Quick Sort's instability is relevant when equivalent keys exist.

Suppose records are ordered only by:

```text
createdAt
```

Several records may share the same timestamp.

Their relative order may vary.

For deterministic output, a secondary key should be defined:

```text
createdAt
        ↓
id
```

Conceptually:

```text
Sort by Creation Time
Then by Unique Identifier
```

A total ordering is more important than the internal sorting algorithm when results must remain consistent across pages or requests.

---

# Parallel Quick Sort

After partitioning, the left and right regions can be sorted independently.

```text
                Partition
                   ↓
          -------------------
          ↓                 ↓
     Sort Left         Sort Right
```

This creates parallelisation opportunities.

However, practical parallel implementations must consider:

- task creation cost;
- partition size thresholds;
- thread scheduling;
- load imbalance;
- memory bandwidth;
- shared execution pools;
- recursion depth.

Poorly balanced partitions also create poorly balanced parallel workloads.

---

# Relationship with Previous Documents

This document builds upon:

```text
01-sorting-fundamentals.md

↓

Ordering Criteria
Sorting Keys
Correct Ordering
```

```text
02-sorting-problem-definition.md

↓

Input
Output
Preconditions
Postconditions
Permutation Property
```

```text
03-sorting-properties.md

↓

Stability
In-Place Behaviour
Comparison-Based Sorting
Recursive Structure
```

```text
04-selection-sort.md

↓

Selection Strategy
Partition-Like Region Reasoning
Quadratic Complexity
```

```text
05-bubble-sort.md

↓

Exchanges
Inversions
Adaptive Behaviour
```

```text
06-insertion-sort.md

↓

Small-Input Efficiency
Hybrid Algorithm Potential
```

```text
07-merge-sort.md

↓

Divide and Conquer
Recursive Correctness
O(n log n) Analysis
```

Quick Sort introduces partition-based divide and conquer and demonstrates how subproblem balance affects performance.

---

# Relationship with Future Documents

The following documents may consolidate and extend the module through topics such as:

```text
Sorting Algorithm Comparison
```

```text
Java Sorting APIs
```

```text
Sorting Correctness Testing
```

```text
Sorting Benchmarking
```

```text
Algorithm Selection Guide
```

Quick Sort provides the final major sorting strategy required for comparing:

- quadratic elementary algorithms;
- divide-and-conquer algorithms;
- stable and unstable behaviour;
- in-place and out-of-place approaches;
- predictable and pivot-dependent complexity.

---

# Key Takeaways

Quick Sort is a divide-and-conquer sorting algorithm based on partitioning.

Its central steps are:

```text
Select Pivot
        ↓
Partition Current Region
        ↓
Place or Separate Pivot Region
        ↓
Recursively Sort Subregions
```

For a standard two-way implementation, its complexity is:

```text
Best Case        O(n log n)
Average Case     O(n log n)
Worst Case       O(n²)
```

Its auxiliary-space behaviour is:

```text
Partition Variables      O(1)
Average Recursion Stack  O(log n)
Worst Recursion Stack    O(n)
```

The standard implementation:

- is comparison-based;
- uses in-place array partitioning;
- is recursive;
- is generally unstable;
- is generally non-adaptive;
- is sensitive to pivot selection;
- may perform poorly with unbalanced partitions;
- may require specialised handling for duplicate-heavy data.

Lomuto partitioning provides a simple educational structure.

Hoare partitioning often performs fewer exchanges but requires different recursive boundaries.

Three-way partitioning is especially valuable when many elements equal the pivot.

Quick Sort is widely important because it combines strong average performance, limited array-memory overhead and flexible engineering optimisations.

Its main trade-off is the lack of a guaranteed `O(n log n)` worst case in the basic implementation.

---

# Next Document

```text
09-sorting-algorithm-comparison.md
```

The next document compares Selection Sort, Bubble Sort, Insertion Sort, Merge Sort and Quick Sort according to:

- strategy;
- best, average and worst-case complexity;
- auxiliary-space usage;
- stability;
- adaptability;
- data movement;
- recursion;
- practical suitability;
- engineering trade-offs.