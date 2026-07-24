# Merge Sort

## Purpose

Merge Sort is an efficient comparison-based sorting algorithm founded on the **divide-and-conquer** paradigm.

Instead of sorting the complete collection directly, the algorithm divides the original problem into smaller subproblems, solves those subproblems recursively and combines their results.

Its general strategy is:

```text
Divide
    ↓
Sort Smaller Subproblems
    ↓
Merge Ordered Results
```

Given a collection, Merge Sort repeatedly divides it into two smaller halves until every subproblem contains at most one element.

A collection with zero or one element is already sorted.

The algorithm then combines the small ordered collections through a merge operation that repeatedly selects the smallest available element from the front of each half.

Conceptually:

```text
Unsorted Collection
        ↓
Divide into Halves
        ↓
Recursively Sort Each Half
        ↓
Merge Sorted Halves
        ↓
Sorted Collection
```

Merge Sort is significant because it provides predictable:

```text
O(n log n)
```

time complexity in the best, average and worst cases.

Its standard array implementation is stable but requires auxiliary memory proportional to the input size.

This document studies Merge Sort from algorithmic, mathematical, recursive and software engineering perspectives.

---

## Learning Objectives

After completing this document, the learner should be able to:

- explain the divide-and-conquer strategy used by Merge Sort;
- identify the divide, conquer and combine phases;
- explain the recursive decomposition of the input;
- define the base case;
- describe how two sorted collections are merged;
- trace Merge Sort manually;
- write pseudocode for the recursive algorithm;
- write pseudocode for the merge operation;
- define its preconditions and postconditions;
- formulate correctness arguments for the recursive and merge phases;
- analyse its recurrence relation;
- explain why its time complexity is `O(n log n)`;
- analyse its auxiliary-space requirements;
- explain why the standard implementation is stable;
- understand why the standard array implementation is not in-place;
- distinguish top-down and bottom-up Merge Sort;
- identify implementation risks and boundary errors;
- design appropriate Java implementations and tests;
- recognise situations in which Merge Sort is an appropriate engineering choice.

---

# Algorithm Overview

Merge Sort divides a collection into smaller parts until no further meaningful division is required.

Example:

```text
[38][27][43][3][9][82][10]
```

First division:

```text
[38][27][43] | [3][9][82][10]
```

Further division:

```text
[38] | [27][43]
```

```text
[3][9] | [82][10]
```

Eventually:

```text
[38] [27] [43] [3] [9] [82] [10]
```

Every single-element collection is already sorted.

The algorithm then merges these small collections.

Example:

```text
[27] + [43]
        ↓
[27][43]
```

```text
[38] + [27][43]
        ↓
[27][38][43]
```

The merging process continues until the complete ordered result is produced.

---

# Divide-and-Conquer Paradigm

Merge Sort follows three conceptual phases:

```text
Divide
Conquer
Combine
```

---

## Divide

The collection is divided into two smaller subcollections.

For a range:

```text
left ... right
```

the midpoint may be calculated as:

```text
middle = left + (right - left) / 2
```

The resulting regions are:

```text
left ... middle
```

and:

```text
middle + 1 ... right
```

---

## Conquer

Each half is sorted recursively.

Conceptually:

```text
Sort Left Half
Sort Right Half
```

Each recursive call applies the same divide-and-conquer process.

---

## Combine

The two sorted halves are merged into one sorted region.

```text
Sorted Left Half
        +
Sorted Right Half
        ↓
Merged Sorted Region
```

The combine phase is the defining operation of Merge Sort.

---

# Base Case

Every recursive algorithm requires a base case.

For Merge Sort, recursion stops when the current region contains at most one element.

Formally:

```text
left >= right
```

A region of size zero or one is already sorted.

Therefore:

```text
No further division is required.
```

Conceptually:

```text
Region Size > 1?

├── Yes → Divide and Recurse
└── No  → Already Sorted
```

---

# Recursive Structure

The recursive structure can be represented as:

```text
MERGE-SORT(A, left, right)

    if left < right

        middle ← calculate midpoint

        MERGE-SORT(A, left, middle)

        MERGE-SORT(A, middle + 1, right)

        MERGE(A, left, middle, right)
```

The recursive calls sort the two halves.

The final merge combines them into a sorted region.

---

# Initial State

Consider:

```text
[38][27][43][3][9][82][10]
```

Initially, the complete range is unsorted:

```text
left = 0
right = 6
```

The midpoint is:

```text
middle = 3
```

The collection is divided into:

```text
[38][27][43][3]
```

and:

```text
[9][82][10]
```

---

# Decomposition Tree

The recursive decomposition may be visualised as a tree.

```text
[38, 27, 43, 3, 9, 82, 10]
                 ↓
        ---------------------
        ↓                   ↓
[38, 27, 43, 3]       [9, 82, 10]
        ↓                   ↓
   -----------           ---------
   ↓         ↓           ↓       ↓
[38, 27]   [43, 3]    [9, 82]   [10]
   ↓         ↓           ↓
 -----     -----        -----
 ↓   ↓     ↓   ↓        ↓   ↓
[38][27]  [43][3]      [9][82]
```

At the leaves of the tree, every collection contains one element.

---

# Merge Phase

After decomposition, the algorithm combines sorted subcollections.

For the previous example:

```text
[38] + [27]
        ↓
[27, 38]
```

```text
[43] + [3]
        ↓
[3, 43]
```

Then:

```text
[27, 38] + [3, 43]
            ↓
[3, 27, 38, 43]
```

On the right:

```text
[9] + [82]
        ↓
[9, 82]
```

Then:

```text
[9, 82] + [10]
          ↓
[9, 10, 82]
```

Final merge:

```text
[3, 27, 38, 43] + [9, 10, 82]
                  ↓
[3, 9, 10, 27, 38, 43, 82]
```

---

# The Merge Operation

The merge operation receives two adjacent sorted regions.

Conceptually:

```text
Left Sorted Region | Right Sorted Region
```

Example:

```text
[3][27][38][43] | [9][10][82]
```

The algorithm maintains two indices:

```text
i → current element in left region
j → current element in right region
```

It repeatedly compares:

```text
left[i]
```

and:

```text
right[j]
```

The smaller element is copied to the next output position.

---

# Merge Example

Merge:

```text
Left  = [3][27][38][43]
Right = [9][10][82]
```

Compare:

```text
3 and 9
```

Select `3`.

Output:

```text
[3]
```

Compare:

```text
27 and 9
```

Select `9`.

Output:

```text
[3][9]
```

Compare:

```text
27 and 10
```

Select `10`.

Output:

```text
[3][9][10]
```

Compare:

```text
27 and 82
```

Select `27`.

Output:

```text
[3][9][10][27]
```

Compare:

```text
38 and 82
```

Select `38`.

Output:

```text
[3][9][10][27][38]
```

Compare:

```text
43 and 82
```

Select `43`.

Output:

```text
[3][9][10][27][38][43]
```

The left region is exhausted.

Copy the remaining right element:

```text
82
```

Final result:

```text
[3][9][10][27][38][43][82]
```

---

# Merge Invariant

A suitable invariant for the merge loop is:

> Before each iteration, the output region contains the smallest elements already examined from both input regions, arranged in sorted order.

At each step:

- the smallest unprocessed front element is selected;
- it is appended to the output;
- the output remains sorted;
- no selected element is lost or duplicated.

When one region is exhausted, the remaining elements of the other region are already sorted and may be copied directly.

---

# Formal Problem

Given a collection:

```text
A = [a₀, a₁, a₂, ..., aₙ₋₁]
```

Merge Sort must produce a permutation:

```text
A' = [a'₀, a'₁, a'₂, ..., a'ₙ₋₁]
```

such that, for ascending order:

```text
a'₀ ≤ a'₁ ≤ a'₂ ≤ ... ≤ a'ₙ₋₁
```

The result must contain exactly the same elements as the input.

---

# Preconditions

A standard Merge Sort implementation assumes:

- the input collection exists;
- the collection contains a finite number of elements;
- every pair of elements can be compared consistently;
- the selected range is valid;
- the collection supports indexed access;
- sufficient auxiliary memory is available for merging.

For an integer implementation:

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
- every input element remains present;
- no element has been added;
- no element has been removed;
- duplicate values remain present;
- equivalent elements preserve their original relative order in the standard stable implementation;
- the original array contains the sorted result.

For ascending order:

```text
A[i] ≤ A[i + 1]
```

for every:

```text
0 ≤ i < n - 1
```

---

# Merge Sort Pseudocode

```text
MERGE-SORT(A, left, right)

    if left >= right

        return

    middle ← left + (right - left) / 2

    MERGE-SORT(A, left, middle)

    MERGE-SORT(A, middle + 1, right)

    MERGE(A, left, middle, right)
```

This pseudocode represents the top-down recursive version.

---

# Merge Pseudocode

```text
MERGE(A, left, middle, right)

    leftSize ← middle - left + 1

    rightSize ← right - middle

    create LEFT[0 ... leftSize - 1]

    create RIGHT[0 ... rightSize - 1]

    copy A[left ... middle] into LEFT

    copy A[middle + 1 ... right] into RIGHT

    i ← 0

    j ← 0

    k ← left

    while i < leftSize and j < rightSize

        if LEFT[i] ≤ RIGHT[j]

            A[k] ← LEFT[i]

            i ← i + 1

        else

            A[k] ← RIGHT[j]

            j ← j + 1

        k ← k + 1

    while i < leftSize

        A[k] ← LEFT[i]

        i ← i + 1

        k ← k + 1

    while j < rightSize

        A[k] ← RIGHT[j]

        j ← j + 1

        k ← k + 1
```

The comparison:

```text
LEFT[i] ≤ RIGHT[j]
```

is essential for stability.

---

# Alternative Auxiliary-Array Design

Instead of allocating two new arrays during every merge, an implementation may create one auxiliary array and reuse it throughout the complete sorting operation.

Conceptually:

```text
Create Auxiliary Array Once

↓

Pass It Through Recursive Calls

↓

Reuse During Every Merge
```

This approach reduces repeated allocation overhead.

Possible structure:

```text
sort(values)

    auxiliary ← new array of same length

    mergeSort(values, auxiliary, 0, length - 1)
```

The algorithm still requires:

```text
O(n)
```

auxiliary space.

However, the memory is allocated once rather than repeatedly.

---

# Manual Trace

Consider:

```text
[8][3][6][2]
```

---

## Division Phase

Initial collection:

```text
[8][3][6][2]
```

Divide:

```text
[8][3] | [6][2]
```

Divide left:

```text
[8] | [3]
```

Divide right:

```text
[6] | [2]
```

Every subcollection now contains one element.

---

## Merge Left Half

Merge:

```text
[8] + [3]
```

Compare:

```text
8 and 3
```

Select `3`.

Copy remaining `8`.

Result:

```text
[3][8]
```

---

## Merge Right Half

Merge:

```text
[6] + [2]
```

Compare:

```text
6 and 2
```

Select `2`.

Copy remaining `6`.

Result:

```text
[2][6]
```

---

## Final Merge

Merge:

```text
[3][8] + [2][6]
```

Compare:

```text
3 and 2
```

Select `2`.

Compare:

```text
3 and 6
```

Select `3`.

Compare:

```text
8 and 6
```

Select `6`.

Copy remaining `8`.

Final result:

```text
[2][3][6][8]
```

---

# Trace Table

| Stage | Left Region | Right Region | Merged Result |
|---|---|---|---|
| 1 | `[8]` | `[3]` | `[3, 8]` |
| 2 | `[6]` | `[2]` | `[2, 6]` |
| 3 | `[3, 8]` | `[2, 6]` | `[2, 3, 6, 8]` |

---

# Recursive Call Tree

For:

```text
[8][3][6][2]
```

the recursive structure is:

```text
MERGE-SORT [8, 3, 6, 2]
             /          \
            /            \
MERGE-SORT [8, 3]    MERGE-SORT [6, 2]
       /     \              /     \
     [8]     [3]          [6]     [2]
       \     /              \     /
      [3, 8]               [2, 6]
            \              /
             \            /
             [2, 3, 6, 8]
```

The recursion tree demonstrates:

- division from top to bottom;
- merging from bottom to top.

---

# Correctness Reasoning

Merge Sort correctness depends on two arguments:

1. recursive subproblems are correctly sorted;
2. the merge operation correctly combines two sorted regions.

---

# Correctness of the Merge Operation

Assume:

```text
LEFT
```

and:

```text
RIGHT
```

are already sorted.

The merge algorithm compares their first unprocessed elements.

Because each region is sorted, the smaller front element is also the smallest element remaining across both regions.

The algorithm copies that element to the output.

Repeating this process produces the complete sorted combination.

---

## Merge Initialisation

Before the merge loop begins:

```text
i = 0
j = 0
k = left
```

No elements have been copied to the output region.

The empty output prefix is trivially sorted.

The merge invariant holds.

---

## Merge Maintenance

Assume the output prefix is sorted and contains the smallest elements already processed.

The algorithm compares:

```text
LEFT[i]
```

and:

```text
RIGHT[j]
```

The smaller value is the smallest unprocessed value across both input regions.

Copying it to the output preserves sorted order.

The corresponding index advances.

Therefore, the invariant is maintained.

---

## Merge Termination

The main merge loop terminates when one input region is exhausted.

The remaining elements in the other region are already sorted and are all greater than or equal to the last copied element.

Copying them directly preserves sorted order.

The output then contains every element from both regions exactly once.

Therefore, the merge operation is correct.

---

# Recursive Correctness

Merge Sort correctness may be proved through induction on the input size.

---

## Base Case

For:

```text
n ≤ 1
```

the collection is already sorted.

The algorithm returns without modification.

Therefore, the result is correct.

---

## Inductive Hypothesis

Assume Merge Sort correctly sorts every collection smaller than size:

```text
n
```

---

## Inductive Step

Consider a collection of size:

```text
n
```

The algorithm divides it into two smaller collections.

By the inductive hypothesis:

- the left recursive call sorts the left half correctly;
- the right recursive call sorts the right half correctly.

The merge operation then correctly combines the two sorted halves.

Therefore, the complete collection of size `n` is sorted.

By mathematical induction, Merge Sort is correct for every finite input size.

---

# Termination Argument

Merge Sort terminates because every recursive call processes a smaller range.

Conceptually:

```text
Problem Size n
        ↓
Approximately n / 2
        ↓
Approximately n / 4
        ↓
...
        ↓
Problem Size 1
```

The recursion reaches the base case after a finite number of divisions.

The recursion depth is approximately:

```text
log₂ n
```

Every merge loop also processes finite input regions and advances at least one index during every iteration.

Therefore, the complete algorithm terminates.

---

# Permutation Preservation

The divide phase does not modify element values.

The merge phase copies every element from the two input regions into the output region exactly once.

During a correct merge:

- no element is omitted;
- no element is created;
- no element is duplicated permanently;
- only positions change.

Therefore, the permutation property is preserved.

---

# Time Complexity

Merge Sort performs two major types of work:

- recursive division;
- linear merging.

Its running time can be described through a recurrence relation.

---

# Recurrence Relation

For an input of size `n`:

```text
T(n) = 2T(n / 2) + Θ(n)
```

The terms represent:

```text
2T(n / 2)
```

for sorting two halves, and:

```text
Θ(n)
```

for merging them.

The recurrence solves to:

```text
T(n) = Θ(n log n)
```

---

# Recursion Tree Analysis

At the first level:

```text
n
```

elements are processed during merging.

At the second level:

```text
n / 2 + n / 2 = n
```

elements are processed.

At the third level:

```text
n / 4 + n / 4 + n / 4 + n / 4 = n
```

elements are processed.

Therefore, each level performs:

```text
Θ(n)
```

merge work.

The number of levels is:

```text
Θ(log n)
```

Thus:

```text
Θ(n) × Θ(log n)
        =
Θ(n log n)
```

---

# Recursion Tree Illustration

```text
Level 0:          n                → Θ(n)

Level 1:      n/2 + n/2            → Θ(n)

Level 2:   n/4 + n/4 + n/4 + n/4  → Θ(n)

...

Final Level:   n subproblems of 1  → Θ(n)
```

Number of levels:

```text
log₂ n
```

Total:

```text
Θ(n log n)
```

---

# Best-Case Complexity

Even when the collection is already sorted:

```text
[1][2][3][4][5]
```

standard Merge Sort still:

- divides the collection;
- performs recursive calls;
- merges the subcollections.

Therefore:

```text
Best Case = O(n log n)
```

Some implementations can detect when adjacent halves are already ordered and skip a merge.

However, the standard algorithm remains:

```text
Θ(n log n)
```

in its general analysis.

---

# Average-Case Complexity

For randomly ordered input, the algorithm follows the same recursive structure and performs linear merging at every level.

Therefore:

```text
Average Case = O(n log n)
```

---

# Worst-Case Complexity

The algorithm still divides the input evenly and merges every element at every level.

Therefore:

```text
Worst Case = O(n log n)
```

This predictable worst-case behaviour is one of Merge Sort's main advantages.

---

# Complexity Summary

| Case | Time Complexity |
|---|---:|
| Best | `O(n log n)` |
| Average | `O(n log n)` |
| Worst | `O(n log n)` |

Merge Sort offers consistent asymptotic performance regardless of the initial ordering of the data.

---

# Merge Comparison Count

When merging two sorted collections with total length `n`, the maximum number of comparisons is:

```text
n - 1
```

Example:

```text
Left Size  = p
Right Size = q
```

with:

```text
p + q = n
```

The maximum comparison count is:

```text
p + q - 1
```

After one region is exhausted, the remaining elements are copied without further key comparisons.

---

# Space Complexity

The standard array implementation requires auxiliary storage for merging.

The additional array size is proportional to the input size.

Therefore:

```text
Auxiliary Array Space = O(n)
```

The recursive call stack also requires:

```text
O(log n)
```

space.

The total auxiliary-space complexity is dominated by:

```text
O(n)
```

---

# Why the Standard Array Version Is Not In-Place

An in-place algorithm generally avoids additional storage proportional to input size.

Standard Merge Sort on arrays requires temporary storage to combine the two sorted halves.

Conceptually:

```text
Original Array
        +
Auxiliary Array
        =
Merge Operation
```

Therefore, the standard array implementation is considered out-of-place with respect to auxiliary storage.

---

# Merge Sort on Linked Structures

Merge Sort is particularly suitable for linked lists.

A linked list can be divided and merged by modifying references rather than copying elements into an auxiliary array.

Conceptually:

```text
Left List
        +
Right List
        ↓
Relink Nodes in Sorted Order
```

The merge operation may require only a limited number of pointer variables.

However, recursive call-stack space must still be considered.

This illustrates that an algorithm's space properties may depend on the underlying data structure.

---

# Stability

Standard Merge Sort is stable when the merge operation selects the left element first when both comparison keys are equivalent.

The condition should be:

```text
LEFT[i] ≤ RIGHT[j]
```

rather than:

```text
LEFT[i] < RIGHT[j]
```

When values are equivalent, selecting from the left region preserves their original order.

---

# Stability Example

Initial records:

```text
[A₁, key 4]
[B,  key 2]
[A₂, key 4]
[C,  key 3]
```

During decomposition, `A₁` remains in a region that precedes the region containing `A₂`.

During merging, when both keys are equal:

```text
4 = 4
```

the algorithm selects `A₁` first.

Final result:

```text
[B,  key 2]
[C,  key 3]
[A₁, key 4]
[A₂, key 4]
```

The original relative order is preserved.

---

# How Stability Can Be Broken

Suppose the merge condition is:

```text
if LEFT[i] < RIGHT[j]
```

and the algorithm selects the right element when keys are equal.

Then equivalent elements from the right region may appear before equivalent elements originally located in the left region.

This breaks stability.

Therefore, stable merging requires a deliberate tie-handling policy.

---

# Adaptability

Standard Merge Sort is generally considered non-adaptive.

It performs the recursive decomposition and merge process even when the collection is already sorted.

Example:

```text
[1][2][3][4][5][6]
```

The algorithm still divides and merges the collection.

---

# Merge-Skip Optimisation

An implementation may inspect the boundary between the two sorted halves before merging.

If:

```text
A[middle] ≤ A[middle + 1]
```

then the complete region is already ordered.

Conceptually:

```text
Left Half Sorted
        +
Right Half Sorted
        +
Last Left ≤ First Right
        ↓
Combined Region Already Sorted
```

The merge may be skipped.

This can improve practical behaviour on partially ordered data.

However, the core theoretical model remains based on:

```text
O(n log n)
```

time.

---

# Comparison-Based Property

Merge Sort is comparison-based.

During the merge operation, ordering decisions are made through comparisons between elements from the left and right regions.

It does not require:

- integer keys;
- bounded value ranges;
- digit extraction;
- direct key indexing.

Therefore, it can sort any elements for which a consistent comparison rule exists.

---

# Internal and External Sorting

Merge Sort is useful for both internal and external sorting.

---

## Internal Merge Sort

When the complete dataset fits in memory:

```text
Load Collection into Memory

↓

Apply Merge Sort

↓

Use Ordered Result
```

This is the form primarily studied in this document.

---

## External Merge Sort

When the dataset exceeds available memory, Merge Sort can be adapted.

Conceptually:

```text
Large Dataset
        ↓
Divide into Memory-Sized Blocks
        ↓
Sort Each Block
        ↓
Write Sorted Runs to Storage
        ↓
Merge Sorted Runs
```

The efficient sequential-access pattern of merging makes Merge Sort particularly suitable for:

- files;
- databases;
- external storage;
- distributed processing.

---

# Top-Down Merge Sort

Top-down Merge Sort is the recursive version studied so far.

Its structure is:

```text
Start with Complete Collection
        ↓
Recursively Divide
        ↓
Merge from Smallest Regions Upward
```

Advantages include:

- direct correspondence with divide-and-conquer reasoning;
- clear recursive structure;
- natural correctness proof.

---

# Bottom-Up Merge Sort

Bottom-up Merge Sort is an iterative alternative.

Instead of recursively dividing the collection, it begins with regions of size one and repeatedly merges adjacent runs.

Conceptually:

```text
Run Size 1
        ↓
Merge into Run Size 2
        ↓
Merge into Run Size 4
        ↓
Merge into Run Size 8
        ↓
Continue
```

Example:

```text
[8][3][6][2][7]
```

Initial runs:

```text
[8] [3] [6] [2] [7]
```

Merge runs of size one:

```text
[3, 8] [2, 6] [7]
```

Merge runs of size two:

```text
[2, 3, 6, 8] [7]
```

Final merge:

```text
[2, 3, 6, 7, 8]
```

---

# Bottom-Up Pseudocode

```text
BOTTOM-UP-MERGE-SORT(A)

    n ← length(A)

    width ← 1

    while width < n

        left ← 0

        while left < n

            middle ← minimum(left + width - 1, n - 1)

            right ← minimum(left + 2 × width - 1, n - 1)

            if middle < right

                MERGE(A, left, middle, right)

            left ← left + 2 × width

        width ← 2 × width
```

Bottom-up Merge Sort avoids recursion but still requires auxiliary storage for standard array merging.

---

# Top-Down versus Bottom-Up Merge Sort

| Property | Top-Down | Bottom-Up |
|---|---|---|
| Control structure | Recursive | Iterative |
| Division | Explicit recursive splitting | Implicit fixed-size runs |
| Call-stack usage | `O(log n)` | `O(1)` |
| Auxiliary merge array | `O(n)` | `O(n)` |
| Conceptual model | Divide and conquer | Progressive run merging |
| Implementation style | Often easier to reason about | Avoids recursion overhead |

Both versions provide:

```text
O(n log n)
```

time complexity.

---

# Descending Merge Sort

To sort in descending order, the merge comparison is reversed.

Ascending:

```text
LEFT[i] ≤ RIGHT[j]
```

Descending:

```text
LEFT[i] ≥ RIGHT[j]
```

The divide-and-conquer structure remains unchanged.

Only the ordering policy differs.

---

# Generic Comparison Policy

A reusable implementation should separate:

```text
Merge Sort Strategy
```

from:

```text
Ordering Criterion
```

Conceptually:

```text
Merge Sort
        +
Comparator
        =
Reusable Sorting Operation
```

Possible ordering criteria include:

- ascending numerical order;
- descending numerical order;
- alphabetical order;
- chronological order;
- product price;
- employee department and surname;
- transaction priority and timestamp.

---

# Java Implementation Considerations

A basic integer-array design may follow this structure:

```java
public final class MergeSort {

    private MergeSort() {
        throw new AssertionError("Utility class");
    }

    public static void sort(int[] values) {
        // Public entry point.
    }

    private static void sort(
            int[] values,
            int[] auxiliary,
            int left,
            int right
    ) {
        // Recursive implementation.
    }

    private static void merge(
            int[] values,
            int[] auxiliary,
            int left,
            int middle,
            int right
    ) {
        // Merge implementation.
    }
}
```

The public method hides internal recursion and index management from the caller.

---

# Suggested Method Contract

```java
/**
 * Sorts the supplied array in ascending numerical order
 * using Merge Sort.
 *
 * <p>The input array is modified to contain the sorted result.
 * The implementation uses auxiliary storage proportional to
 * the array length.</p>
 *
 * @param values array to be sorted
 * @throws NullPointerException if {@code values} is {@code null}
 */
public static void sort(int[] values) {
    // ...
}
```

The contract should make both mutation and auxiliary-space behaviour clear.

---

# Public Entry Point

The public method may:

1. validate the input;
2. return immediately for trivial arrays;
3. create the auxiliary array;
4. invoke the recursive implementation.

Example structure:

```java
public static void sort(int[] values) {
    Objects.requireNonNull(values, "values must not be null");

    if (values.length < 2) {
        return;
    }

    int[] auxiliary = new int[values.length];

    sort(values, auxiliary, 0, values.length - 1);
}
```

---

# Safe Midpoint Calculation

The midpoint should be calculated as:

```java
int middle = left + (right - left) / 2;
```

rather than:

```text
int middle = (left + right) / 2;
```

The first form avoids potential integer overflow when `left` and `right` are large.

Although array indices in ordinary applications may not approach the maximum integer value, the safer expression reflects good algorithmic practice.

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

Comparison during merging:

```text
if (comparator.compare(leftValue, rightValue) <= 0) {
    // Select left value first.
}
```

The condition:

```text
<= 0
```

preserves stability.

---

# Copying into the Auxiliary Array

One implementation strategy copies the current region into the auxiliary array before merging.

Conceptually:

```text
Copy A[left ... right] into auxiliary[left ... right]
```

Then merge back into the original array.

This avoids allocating separate left and right arrays for every merge call.

Possible structure:

```text
System.arraycopy(
        values,
        left,
        auxiliary,
        left,
        right - left + 1
);
```

The merge then reads from:

```text
auxiliary
```

and writes into:

```text
values
```

---

# Example Merge Structure

```text
private static void merge(
        int[] values,
        int[] auxiliary,
        int left,
        int middle,
        int right
) {
    System.arraycopy(
            values,
            left,
            auxiliary,
            left,
            right - left + 1
    );

    int leftIndex = left;
    int rightIndex = middle + 1;
    int destination = left;

    while (leftIndex <= middle && rightIndex <= right) {
        if (auxiliary[leftIndex] <= auxiliary[rightIndex]) {
            values[destination++] = auxiliary[leftIndex++];
        } else {
            values[destination++] = auxiliary[rightIndex++];
        }
    }

    while (leftIndex <= middle) {
        values[destination++] = auxiliary[leftIndex++];
    }
}
```

Copying the remaining right elements may be unnecessary in this design because they are already in the correct positions.

However, that optimisation depends on the exact copy and write strategy and should be documented carefully.

---

# Common Implementation Errors

Typical Merge Sort defects include:

- missing or incorrect base case;
- calculating the midpoint incorrectly;
- using overlapping recursive ranges;
- excluding the midpoint from both halves;
- including the midpoint in both halves;
- incorrect auxiliary-array sizes;
- copying the wrong range;
- failing to copy remaining left elements;
- failing to copy remaining right elements;
- writing results to the wrong starting index;
- using `<` instead of `<=` and breaking stability;
- allocating excessive temporary arrays;
- mixing inclusive and exclusive boundaries;
- causing infinite recursion.

---

# Inclusive Range Convention

One common convention uses inclusive indices:

```text
left ... right
```

The two halves are:

```text
left ... middle
```

and:

```text
middle + 1 ... right
```

The base case is:

```text
left >= right
```

This convention should be used consistently.

---

# Exclusive Range Convention

Another valid convention uses:

```text
from inclusive
to exclusive
```

The complete range is:

```text
[from, to)
```

The halves are:

```text
[from, middle)
```

and:

```text
[middle, to)
```

The base case is:

```text
to - from <= 1
```

Both conventions are correct.

Mixing them creates boundary defects.

---

# Infinite Recursion Error

Suppose the ranges are incorrectly defined as:

```text
left ... middle
```

and:

```text
middle ... right
```

For a two-element region:

```text
left = 0
right = 1
middle = 0
```

The second recursive call receives:

```text
0 ... 1
```

again.

The problem size does not decrease.

This creates infinite recursion.

The correct second range is:

```text
middle + 1 ... right
```

for inclusive boundaries.

---

# Testing Strategy

Merge Sort should be tested using representative input categories.

---

## Empty Array

```text
Input

[]

Expected

[]
```

The algorithm should terminate without creating invalid ranges.

---

## Single Element

```text
Input

[7]

Expected

[7]
```

The base case should return immediately.

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

This is the smallest non-trivial recursive case.

---

## Already Sorted Array

```text
Input

[1][2][3][4][5]

Expected

[1][2][3][4][5]
```

---

## Reverse-Sorted Array

```text
Input

[5][4][3][2][1]

Expected

[1][2][3][4][5]
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

This case is useful for verifying stability and boundary handling.

---

## Negative Values

```text
Input

[-3][7][-8][2][0]

Expected

[-8][-3][0][2][7]
```

---

## Odd Number of Elements

```text
Input

[8][3][6][2][7]

Expected

[2][3][6][7][8]
```

This verifies uneven division.

---

## Even Number of Elements

```text
Input

[8][3][6][2]

Expected

[2][3][6][8]
```

---

## Large Duplicate Groups

```text
Input

[3][1][3][2][3][1][2]

Expected

[1][1][2][2][3][3][3]
```

---

# Testing Stability

Use distinct objects with equivalent keys.

Initial order:

```text
A — Key 10
B — Key 5
C — Key 10
D — Key 5
```

Expected stable result:

```text
B — Key 5
D — Key 5
A — Key 10
C — Key 10
```

The test must verify:

```text
B remains before D
```

and:

```text
A remains before C
```

---

# Testing Correctness Properties

Tests should verify:

## Ordering Property

```text
A[i] ≤ A[i + 1]
```

for every valid index.

## Permutation Property

The output contains the same elements and frequencies as the input.

## Stability Property

Equivalent elements preserve their original relative order.

## Mutation Contract

The original array contains the sorted result.

## Boundary Correctness

Odd and even input sizes are divided and merged correctly.

---

# Example JUnit Structure

```java
class MergeSortTest {

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
    void shouldHandleOddLengthArray() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldHandleEvenLengthArray() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void shouldPreserveRelativeOrderOfEquivalentElements() {
        // Arrange
        // Act
        // Assert
    }
}
```

---

# Randomised Testing

Merge Sort can be tested against the Java standard library.

Conceptually:

```text
Generate Random Array

↓

Copy Original Array

↓

Sort One Copy with Merge Sort

↓

Sort Other Copy with Arrays.sort

↓

Compare Results
```

This can help detect defects across many input combinations.

Randomised testing should complement, not replace, carefully designed deterministic test cases.

---

# Property-Based Testing Perspective

Useful sorting properties include:

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

The last property is idempotence:

```text
sort(sort(A)) = sort(A)
```

These properties can reveal defects not covered by a small set of fixed examples.

---

# Measuring Operations

An educational implementation may count:

```text
Comparisons
Copies
Merge Calls
Recursive Calls
Maximum Recursion Depth
```

Expected observations include:

```text
Recursion Depth ≈ log₂ n
```

```text
Merge Work per Level ≈ n
```

```text
Total Work ≈ n log₂ n
```

---

# Benchmarking Considerations

Merge Sort may be benchmarked using:

```text
Random Data
Already Sorted Data
Nearly Sorted Data
Reverse-Sorted Data
Duplicate-Heavy Data
Small Arrays
Large Arrays
```

Expected observations include:

- consistent `O(n log n)` growth;
- similar asymptotic behaviour across initial orderings;
- allocation or copying overhead;
- stronger scalability than quadratic algorithms;
- possible disadvantage for very small arrays.

---

# Benchmark Comparison with Elementary Algorithms

For small values of `n`, algorithms such as Insertion Sort may perform competitively because of:

- simpler control flow;
- no recursive calls;
- no auxiliary array;
- lower setup cost.

As input size grows, Merge Sort's:

```text
O(n log n)
```

behaviour becomes increasingly advantageous compared with:

```text
O(n²)
```

algorithms.

Conceptually:

```text
Small Input
        ↓
Constant Factors May Dominate
```

```text
Large Input
        ↓
Growth Rate Dominates
```

---

# Benchmarking Memory

Merge Sort evaluation should include allocation behaviour.

Possible measurements include:

- bytes allocated per operation;
- auxiliary-array size;
- garbage-collection pressure;
- execution time;
- recursive call depth.

A faster execution time may still involve higher memory cost.

Therefore, performance analysis should consider both:

```text
Time
```

and:

```text
Space
```

---

# Benchmarking Warning

Reliable Java microbenchmarking should use JMH.

Manual timing may be distorted by:

- JVM warm-up;
- just-in-time compilation;
- garbage collection;
- allocation effects;
- dead-code elimination;
- measurement noise.

Benchmark input arrays must also be recreated or restored between invocations because sorting mutates the data.

Benchmarking an already sorted array repeatedly would not measure the intended initial distribution.

---

# Advantages

Merge Sort offers several important advantages.

## Predictable Time Complexity

Its best, average and worst-case running times are:

```text
O(n log n)
```

---

## Stability

The standard merge operation can preserve the relative order of equivalent elements.

---

## Effective for Large Datasets

Its growth rate is substantially better than quadratic elementary algorithms.

---

## Suitable for Linked Lists

Merging can be performed efficiently through reference manipulation.

---

## Suitable for External Sorting

Its sequential merge pattern works well with files and external storage.

---

## Parallelisation Potential

The left and right recursive subproblems are independent.

Conceptually:

```text
Sort Left Half  ──┐
                  ├── Merge
Sort Right Half ──┘
```

This structure creates opportunities for parallel execution.

---

## Predictable Behaviour

The algorithm is not vulnerable to a quadratic worst case caused by an unfavourable input arrangement.

---

# Limitations

Merge Sort also has important limitations.

## Auxiliary Memory Requirement

Standard array implementations require:

```text
O(n)
```

additional storage.

---

## Allocation and Copying Cost

The merge process involves copying elements between arrays.

---

## Recursive Overhead

Top-down implementations create recursive calls and use stack space.

---

## Limited Adaptability

Standard Merge Sort does not substantially benefit from already sorted input.

---

## Small-Input Overhead

For very small collections, simpler algorithms may perform better.

---

## More Complex Implementation

Correct range handling and merging require more care than elementary sorting algorithms.

---

# When Merge Sort May Be Appropriate

Merge Sort may be appropriate when:

- predictable `O(n log n)` performance is required;
- stability is important;
- the collection is large;
- worst-case guarantees matter;
- data is stored in a linked structure;
- external sorting is required;
- sequential access is efficient;
- parallel decomposition is beneficial;
- additional memory is acceptable.

---

# When Merge Sort Should Not Be Preferred

It may be less suitable when:

- auxiliary memory is severely constrained;
- the input is extremely small;
- in-place array sorting is a strict requirement;
- allocation and copying are expensive;
- a specialised standard-library implementation already provides the required behaviour.

---

# Comparison with Selection Sort

| Property | Merge Sort | Selection Sort |
|---|---|---|
| Main strategy | Divide and merge | Repeated minimum selection |
| Best-case time | `O(n log n)` | `O(n²)` |
| Average time | `O(n log n)` | `O(n²)` |
| Worst-case time | `O(n log n)` | `O(n²)` |
| Stable | Yes | No |
| In place | No for standard arrays | Yes |
| Auxiliary space | `O(n)` | `O(1)` |
| Adaptive | Generally no | No |
| Large inputs | Suitable | Unsuitable |

---

# Comparison with Bubble Sort

| Property | Merge Sort | Bubble Sort — optimised |
|---|---|---|
| Best-case time | `O(n log n)` | `O(n)` |
| Average time | `O(n log n)` | `O(n²)` |
| Worst-case time | `O(n log n)` | `O(n²)` |
| Stable | Yes | Yes |
| In place | No for arrays | Yes |
| Auxiliary space | `O(n)` | `O(1)` |
| Large inputs | Suitable | Unsuitable |
| Main movement | Merge and copy | Adjacent exchanges |

Bubble Sort has a better asymptotic best case for already sorted input, but Merge Sort provides far stronger general scalability.

---

# Comparison with Insertion Sort

| Property | Merge Sort | Insertion Sort |
|---|---|---|
| Best-case time | `O(n log n)` | `O(n)` |
| Average time | `O(n log n)` | `O(n²)` |
| Worst-case time | `O(n log n)` | `O(n²)` |
| Stable | Yes | Yes |
| In place | No for standard arrays | Yes |
| Adaptive | Generally no | Yes |
| Small inputs | More overhead | Often effective |
| Large inputs | Scalable | Poor scalability |

The two algorithms may be combined in a hybrid implementation.

Merge Sort handles large regions, while Insertion Sort handles small subproblems.

---

# Comparison with Quick Sort

| Property | Merge Sort | Quick Sort |
|---|---|---|
| Main strategy | Divide, sort and merge | Partition around pivot |
| Average time | `O(n log n)` | `O(n log n)` |
| Worst-case time | `O(n log n)` | `O(n²)` |
| Stable | Yes | Usually no |
| Auxiliary array | `O(n)` for standard arrays | Usually no |
| Recursion stack | `O(log n)` | Depends on partition balance |
| Array locality | Good sequential merging | Often very good |
| Predictability | Strong | Depends on pivot strategy |

Quick Sort will be studied in the next document.

---

# Engineering Perspective

Merge Sort demonstrates the practical importance of divide and conquer.

Rather than solving one large problem directly, the algorithm:

```text
Divides the Problem
        ↓
Solves Smaller Independent Problems
        ↓
Combines Their Results
```

This structure provides:

- clear recursive reasoning;
- predictable asymptotic behaviour;
- opportunities for parallelism;
- reusable merging techniques.

However, algorithm selection still requires trade-off analysis.

Merge Sort exchanges additional memory for stronger time guarantees.

Conceptually:

```text
Predictable O(n log n) Time
        ↔
O(n) Auxiliary Array Space
```

This trade-off may be acceptable in one system and unacceptable in another.

---

# Backend Engineering Perspective

In backend systems, ordering may occur:

```text
In the Database
```

```text
In Application Memory
```

```text
During File Processing
```

```text
Across Distributed Data Partitions
```

Merge-based strategies are particularly relevant when:

- sorted database or file runs must be combined;
- data is processed in batches;
- datasets exceed memory;
- stable ordering is required;
- parallel workers produce ordered partial results.

However, application code should normally use established library or database operations rather than handwritten Merge Sort for production sorting.

The manual implementation remains valuable for understanding:

- complexity;
- recursion;
- stable merging;
- memory trade-offs;
- external data processing.

---

# Parallel Merge Sort

The two recursive calls are independent until the merge phase.

Conceptually:

```text
                Input
                  ↓
          ----------------
          ↓              ↓
     Sort Left       Sort Right
          ↓              ↓
          ------ Merge ------
                  ↓
                Result
```

The left and right halves may potentially be processed concurrently.

However, parallel implementations introduce concerns such as:

- task creation overhead;
- thread scheduling;
- memory bandwidth;
- merge coordination;
- threshold selection;
- shared resource usage.

Parallel execution is beneficial only when the input is sufficiently large to justify the overhead.

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
Auxiliary Space
Comparison-Based Sorting
Recursive Algorithms
```

```text
04-selection-sort.md

↓

Repeated Selection
O(n²) Complexity
```

```text
05-bubble-sort.md

↓

Adjacent Exchanges
Stability
Adaptive Optimisation
```

```text
06-insertion-sort.md

↓

Incremental Sorted Prefix
Adaptability
Small-Input Efficiency
```

Merge Sort introduces the first asymptotically efficient sorting algorithm in the module.

---

# Relationship with Future Documents

The next document introduces another divide-and-conquer algorithm.

```text
Merge Sort
        ↓
Divide by Position
        ↓
Sort Both Halves
        ↓
Merge Results
```

```text
Quick Sort
        ↓
Select Pivot
        ↓
Partition by Value
        ↓
Sort Partitions
```

Both algorithms commonly achieve:

```text
O(n log n)
```

time.

However, they differ significantly in:

- worst-case guarantees;
- stability;
- auxiliary memory;
- data movement;
- partitioning and merging strategies.

---

# Key Takeaways

Merge Sort is an efficient divide-and-conquer sorting algorithm.

Its central phases are:

```text
Divide
Conquer
Combine
```

The algorithm:

1. divides the collection into smaller halves;
2. recursively sorts each half;
3. merges the ordered halves.

Its complexity is:

```text
Best Case       O(n log n)
Average Case    O(n log n)
Worst Case      O(n log n)
Auxiliary Space O(n)
Recursion Stack O(log n)
```

The standard array implementation:

- is stable;
- is comparison-based;
- is recursive in its top-down form;
- is generally non-adaptive;
- is not in-place;
- provides predictable worst-case performance.

Merge Sort is especially suitable for:

- large collections;
- stable sorting;
- linked lists;
- external sorting;
- systems requiring predictable performance.

Its primary trade-off is the auxiliary memory required for merging.

---

# Next Document

```text
08-quick-sort.md
```

The next document introduces Quick Sort, a divide-and-conquer sorting algorithm that selects a pivot, partitions the collection around that pivot and recursively sorts the resulting regions.