# Insertion Sort

## Purpose

Insertion Sort is an elementary comparison-based sorting algorithm that builds an ordered region incrementally.

The algorithm processes the collection from left to right.

During each iteration, it removes one element from the unsorted region, searches for its correct position inside the sorted region and inserts it there.

Conceptually:

```text
Select Next Element
        ↓
Compare with Sorted Region
        ↓
Shift Larger Elements
        ↓
Insert Element
        ↓
Expand Sorted Region
```

Insertion Sort resembles the process of arranging playing cards in a hand.

Each new card is compared with the cards already ordered and inserted into the appropriate position.

Although its average and worst-case time complexity are quadratic, Insertion Sort has important practical properties:

- it is simple;
- it is stable;
- it is in-place;
- it is adaptive;
- it performs well on small collections;
- it performs efficiently on nearly sorted data;
- it is commonly used as part of hybrid sorting algorithms.

This document studies Insertion Sort from algorithmic, mathematical and software engineering perspectives.

---

## Learning Objectives

After completing this document, the learner should be able to:

- explain the strategy used by Insertion Sort;
- identify the sorted and unsorted regions;
- describe the role of the current element or key;
- explain how shifting differs from swapping;
- trace the algorithm manually;
- write Insertion Sort pseudocode;
- define its preconditions and postconditions;
- formulate a loop invariant;
- explain why the algorithm is correct;
- analyse its best, average and worst-case complexity;
- explain why the algorithm is stable;
- explain why it is adaptive;
- analyse its auxiliary-space usage;
- understand its relationship with inversions;
- recognise situations in which it is practically useful;
- prepare an appropriate Java implementation and testing strategy.

---

# Algorithm Overview

Insertion Sort divides the collection into two logical regions:

```text
Sorted Region | Unsorted Region
```

Initially, the first element is considered sorted.

The remaining elements belong to the unsorted region.

Example:

```text
[7] | [4][5][2][9]
```

The algorithm selects the first element of the unsorted region:

```text
4
```

It then compares this value with the elements in the sorted region.

Because:

```text
4 < 7
```

the value `7` is shifted one position to the right, and `4` is inserted before it.

Result:

```text
[4][7] | [5][2][9]
```

The sorted region grows by one position during every iteration.

---

# Core Strategy

At each iteration, Insertion Sort:

1. selects the first element of the unsorted region;
2. stores it in a temporary variable;
3. compares it with elements in the sorted region from right to left;
4. shifts larger elements one position to the right;
5. inserts the selected value into the available position.

Conceptually:

```text
Sorted Region | Current Element | Remaining Elements
```

The current element is often called:

```text
key
```

or:

```text
currentValue
```

---

# Playing Card Analogy

Insertion Sort is frequently explained through the arrangement of playing cards.

Suppose the cards currently held are:

```text
[3][6][9]
```

A new card is received:

```text
5
```

The card is compared from right to left:

```text
5 < 9
```

Move `9` to the right.

```text
5 < 6
```

Move `6` to the right.

```text
5 > 3
```

Stop.

Insert `5` after `3`.

Result:

```text
[3][5][6][9]
```

The previously arranged cards remain sorted throughout the process.

---

# Initial State

For a non-empty collection, the first element is considered a sorted region of size one.

Example:

```text
[7] | [4][5][2][9]
```

A single element is trivially sorted.

Therefore, the algorithm begins processing at index:

```text
1
```

---

# Intermediate State

After several iterations, the collection has the following structure:

```text
A[0 ... i - 1] | A[i ... n - 1]
   Sorted Region | Unsorted Region
```

Example:

```text
[2][4][5][7] | [9]
```

The elements in the sorted region are ordered, but they are not necessarily in their final positions until the algorithm finishes.

A future element may still be inserted before them.

---

# Final State

When every element has been inserted into the sorted region:

```text
[2][4][5][7][9]
```

the complete collection is sorted.

---

# Formal Problem

Given a collection:

```text
A = [a₀, a₁, a₂, ..., aₙ₋₁]
```

Insertion Sort must produce a permutation:

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

A standard Insertion Sort implementation assumes:

- the input collection exists;
- the collection contains a finite number of elements;
- the elements can be compared;
- the comparison criterion is consistent;
- the collection supports indexed access;
- the collection allows element replacement.

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

- the collection satisfies the required ordering criterion;
- every original element remains present;
- no element has been added;
- no element has been removed;
- duplicate values remain present;
- equivalent elements preserve their relative order in the standard implementation;
- the original collection has been modified in place.

For ascending order:

```text
A[i] ≤ A[i + 1]
```

for every:

```text
0 ≤ i < n - 1
```

---

# Standard Insertion Sort Pseudocode

```text
INSERTION-SORT(A)

    n ← length(A)

    for i ← 1 to n - 1

        key ← A[i]

        j ← i - 1

        while j ≥ 0 and A[j] > key

            A[j + 1] ← A[j]

            j ← j - 1

        A[j + 1] ← key
```

The outer loop selects each element that must be inserted.

The inner loop shifts larger elements to the right.

---

# Meaning of the Key

The variable:

```text
key
```

temporarily stores the element being inserted.

This is necessary because its original position may be overwritten while larger elements are shifted.

Example:

```text
[3][7][9][5]
```

Before shifting:

```text
key = 5
```

Shift `9`:

```text
[3][7][9][9]
```

Shift `7`:

```text
[3][7][7][9]
```

Insert the stored key:

```text
[3][5][7][9]
```

Without the temporary variable, the original value `5` would be lost.

---

# Shifting versus Swapping

Insertion Sort usually moves elements through shifts rather than repeated swaps.

A shift copies one element into the next position.

```text
A[j + 1] ← A[j]
```

Example:

```text
[2][6][8][4]
```

Shift `8`:

```text
[2][6][8][8]
```

Shift `6`:

```text
[2][6][6][8]
```

Insert `4`:

```text
[2][4][6][8]
```

---

## Swap-Based Alternative

A variation of Insertion Sort may repeatedly exchange the current element with its left neighbour.

Conceptually:

```text
while current < previous

    swap current and previous
```

Although this version can be correct, it often performs more assignments.

A traditional shift-based implementation is generally preferable because it:

- saves the current value once;
- shifts larger elements;
- performs one final insertion.

---

# Manual Trace

Consider:

```text
[7][4][5][2][9]
```

The objective is ascending order.

---

## Initial State

```text
Sorted | Unsorted
-------|----------------
[7]    | [4][5][2][9]
```

---

## Iteration 1

Current index:

```text
i = 1
```

Key:

```text
key = 4
```

Sorted region:

```text
[7]
```

Compare:

```text
7 > 4
```

Shift `7` right:

```text
[7][7][5][2][9]
```

Insert `4` at index `0`:

```text
[4][7][5][2][9]
```

Result:

```text
Sorted   | Unsorted
---------|-------------
[4][7]   | [5][2][9]
```

---

## Iteration 2

Current index:

```text
i = 2
```

Key:

```text
key = 5
```

Sorted region:

```text
[4][7]
```

Compare:

```text
7 > 5
```

Shift `7`:

```text
[4][7][7][2][9]
```

Compare:

```text
4 > 5
```

False.

Insert `5`:

```text
[4][5][7][2][9]
```

Result:

```text
Sorted      | Unsorted
------------|----------
[4][5][7]   | [2][9]
```

---

## Iteration 3

Current index:

```text
i = 3
```

Key:

```text
key = 2
```

Sorted region:

```text
[4][5][7]
```

Compare:

```text
7 > 2
```

Shift `7`:

```text
[4][5][7][7][9]
```

Compare:

```text
5 > 2
```

Shift `5`:

```text
[4][5][5][7][9]
```

Compare:

```text
4 > 2
```

Shift `4`:

```text
[4][4][5][7][9]
```

The left boundary has been reached.

Insert `2`:

```text
[2][4][5][7][9]
```

Result:

```text
Sorted          | Unsorted
----------------|---------
[2][4][5][7]    | [9]
```

---

## Iteration 4

Current index:

```text
i = 4
```

Key:

```text
key = 9
```

Compare:

```text
7 > 9
```

False.

No shifts are required.

The key remains in its original position.

Final result:

```text
[2][4][5][7][9]
```

---

# Trace Table

| Iteration | Key | Elements Shifted | Result |
|---:|---:|---|---|
| 1 | `4` | `7` | `[4, 7, 5, 2, 9]` |
| 2 | `5` | `7` | `[4, 5, 7, 2, 9]` |
| 3 | `2` | `7, 5, 4` | `[2, 4, 5, 7, 9]` |
| 4 | `9` | None | `[2, 4, 5, 7, 9]` |

---

# Visual Model

Initial state:

```text
| 7 | 4 | 5 | 2 | 9 |
  --- ----------------
 Sorted    Unsorted
```

After inserting `4`:

```text
| 4 | 7 | 5 | 2 | 9 |
  ------- ------------
   Sorted    Unsorted
```

After inserting `5`:

```text
| 4 | 5 | 7 | 2 | 9 |
  ----------- --------
     Sorted    Unsorted
```

After inserting `2`:

```text
| 2 | 4 | 5 | 7 | 9 |
  --------------- ----
       Sorted      Unsorted
```

After processing `9`:

```text
| 2 | 4 | 5 | 7 | 9 |
  -------------------
          Sorted
```

---

# Loop Invariant

A suitable loop invariant for Insertion Sort is:

> At the beginning of each outer-loop iteration with index `i`, the subarray `A[0 ... i - 1]` contains the same elements that originally occupied those positions, but arranged in sorted order.

More precisely:

> Before iteration `i`, the prefix `A[0 ... i - 1]` is a sorted permutation of the original prefix `A[0 ... i - 1]`.

The invariant describes the progressive construction of the sorted region.

---

# Correctness Reasoning

The proof of correctness can be organised into:

1. initialisation;
2. maintenance;
3. termination.

---

## Initialisation

Before the first iteration:

```text
i = 1
```

The prefix:

```text
A[0 ... 0]
```

contains one element.

A collection containing one element is trivially sorted.

It is also a permutation of itself.

Therefore, the invariant holds before the first iteration.

---

## Maintenance

Assume that before iteration `i`:

```text
A[0 ... i - 1]
```

is sorted.

The algorithm stores:

```text
key = A[i]
```

It then shifts every element greater than the key one position to the right.

The loop stops when:

- the beginning of the collection is reached; or
- an element less than or equal to the key is found.

The key is inserted into:

```text
A[j + 1]
```

After insertion:

```text
A[0 ... i]
```

is sorted.

The region contains exactly the same elements as before the operation.

Therefore, the invariant is maintained.

---

## Termination

The outer loop terminates when:

```text
i = n
```

At that point, the invariant states that:

```text
A[0 ... n - 1]
```

is sorted.

This region is the complete collection.

Therefore, Insertion Sort is correct.

---

# Inner-Loop Invariant

A useful invariant for the inner loop is:

> Before each inner-loop iteration, the elements in `A[j + 1 ... i]` are the elements originally greater than the key, shifted one position to the right.

The key remains safely stored in a temporary variable.

When the loop terminates, index:

```text
j + 1
```

is the correct insertion position.

---

# Termination Argument

Insertion Sort always terminates because:

- the outer-loop index increases by one during every iteration;
- the outer loop has a finite upper bound;
- the inner-loop index decreases by one during every iteration;
- the inner loop cannot move beyond index `-1`;
- the collection is finite.

Conceptually:

```text
Outer Index Moves Right
        +
Inner Index Moves Left
        +
Finite Boundaries
        =
Guaranteed Termination
```

---

# Permutation Preservation

Insertion Sort preserves the elements through:

- temporary storage;
- shifts;
- reinsertion.

No element is created or removed.

During shifting, values may appear duplicated temporarily.

Example:

```text
[4][7][5]
```

After storing `5` and shifting `7`:

```text
[4][7][7]
```

This intermediate state contains a temporary duplicate.

However, the original key remains stored separately and is reinserted:

```text
[4][5][7]
```

The complete algorithm preserves the permutation property.

---

# Time Complexity

The principal operations are:

- comparisons;
- shifts;
- assignments;
- insertions.

The number of operations depends strongly on the initial order of the input.

---

# Best-Case Complexity

The best case occurs when the collection is already sorted.

Example:

```text
[1][2][3][4][5]
```

For every key, the first comparison verifies:

```text
A[j] ≤ key
```

No shifts are performed.

The outer loop executes:

```text
n - 1
```

times.

The total work grows linearly.

Therefore:

```text
Best Case = O(n)
```

---

# Average-Case Complexity

For randomly ordered input, each key moves through approximately half of the sorted region on average.

The total number of comparisons and shifts grows quadratically.

Therefore:

```text
Average Case = O(n²)
```

---

# Worst-Case Complexity

The worst case occurs when the collection is reverse sorted.

Example:

```text
[5][4][3][2][1]
```

Every new key must move to the beginning of the sorted region.

Shift counts:

```text
First insertion:   1
Second insertion:  2
Third insertion:   3
...
Final insertion:   n - 1
```

Total:

```text
1 + 2 + 3 + ... + (n - 1)
```

This equals:

```text
n(n - 1) / 2
```

Therefore:

```text
Worst Case = O(n²)
```

---

# Complexity Summary

| Case | Time Complexity |
|---|---:|
| Best | `O(n)` |
| Average | `O(n²)` |
| Worst | `O(n²)` |

Insertion Sort is adaptive because its running time improves when the input is already or nearly sorted.

---

# Exact Worst-Case Shift Count

For reverse-sorted input:

```text
S(n) = 1 + 2 + 3 + ... + (n - 1)
```

Therefore:

```text
S(n) = n(n - 1) / 2
```

Examples:

| Input Size `n` | Maximum Shifts |
|---:|---:|
| 1 | 0 |
| 2 | 1 |
| 3 | 3 |
| 4 | 6 |
| 5 | 10 |
| 10 | 45 |
| 100 | 4,950 |
| 1,000 | 499,500 |

---

# Inversions and Insertion Sort

The number of shifts performed by Insertion Sort is directly related to the number of inversions in the input.

An inversion is a pair:

```text
i < j
```

such that:

```text
A[i] > A[j]
```

Each shift moves the key past one larger element and removes one inversion.

Therefore:

```text
Number of Shifts = Number of Initial Inversions
```

for the standard implementation.

---

## Inversion Example

Consider:

```text
[3][1][2]
```

Inversions:

```text
(3, 1)
(3, 2)
```

Total inversions:

```text
2
```

Insertion Sort performs:

- one shift when inserting `1`;
- one shift when inserting `2`.

Total shifts:

```text
2
```

This connection explains why the algorithm performs well on nearly sorted collections.

Nearly sorted data contains relatively few inversions.

---

# Space Complexity

Insertion Sort uses a fixed number of variables:

```text
i
j
key
```

The amount of auxiliary storage does not depend on the input size.

Therefore:

```text
Auxiliary Space = O(1)
```

---

# In-Place Property

Insertion Sort modifies the original collection directly.

Before:

```text
[7][4][5][2]
```

After:

```text
[2][4][5][7]
```

No secondary array proportional to the input size is required.

Therefore, the algorithm is in-place.

---

# Stability

The standard implementation of Insertion Sort is stable.

The inner loop shifts an element only when:

```text
A[j] > key
```

Elements that are equivalent to the key are not shifted.

The new key is inserted after existing equivalent elements.

This preserves their original relative order.

---

# Stability Example

Initial records:

```text
[A₁, key 4]
[B,  key 2]
[A₂, key 4]
[C,  key 3]
```

Stable sorted result:

```text
[B,  key 2]
[C,  key 3]
[A₁, key 4]
[A₂, key 4]
```

The original order:

```text
A₁ before A₂
```

is preserved.

---

# How Stability Can Be Broken

If the inner-loop condition uses:

```text
A[j] ≥ key
```

then equivalent elements are shifted.

The new key may be inserted before elements with the same key.

This changes their relative order.

Therefore, a stable ascending implementation should use:

```text
A[j] > key
```

not:

```text
A[j] ≥ key
```

---

# Adaptability

Insertion Sort is adaptive because the amount of work depends on how far each element is from its correct position.

Already sorted input:

```text
Few comparisons
No shifts
```

Nearly sorted input:

```text
Few inversions
Few shifts
```

Reverse-sorted input:

```text
Maximum inversions
Maximum shifts
```

This makes Insertion Sort useful when data is expected to be partially ordered.

---

# Nearly Sorted Example

Consider:

```text
[1][2][3][5][4][6][7]
```

Only one inversion exists:

```text
(5, 4)
```

Insertion Sort shifts `5` once and inserts `4`.

The remaining elements require no movement.

The algorithm performs close to linear work.

---

# Online Behaviour

Insertion Sort can process elements incrementally.

Suppose values arrive one at a time:

```text
Receive Element
        ↓
Insert into Sorted Prefix
        ↓
Maintain Ordered State
```

This gives Insertion Sort an online characteristic.

The algorithm does not require all future elements to be available before it begins ordering the existing values.

However, for continuously changing datasets, specialised data structures such as balanced trees or priority queues may be more appropriate.

---

# Comparison-Based Property

Insertion Sort is comparison-based.

Its decisions depend on comparisons such as:

```text
A[j] > key
```

It does not require:

- bounded integer ranges;
- digit representations;
- direct key indexing;
- hashing.

Therefore, it can sort any type for which a consistent ordering criterion exists.

---

# Internal Sorting

The standard implementation operates on a collection stored entirely in main memory.

It is therefore an internal sorting algorithm.

Typical input structures include:

- arrays;
- array-backed lists;
- small object collections;
- partitions created by larger sorting algorithms.

---

# Iterative Structure

Insertion Sort is usually implemented iteratively.

It uses:

- an outer loop to select the next key;
- an inner loop to locate the insertion position through shifting.

No recursion is required.

Therefore, no recursion-stack space is consumed.

---

# Descending Insertion Sort

To sort in descending order, the comparison condition is reversed.

Ascending:

```text
while A[j] > key
```

Descending:

```text
while A[j] < key
```

The structural behaviour remains unchanged.

Only the ordering rule changes.

---

# Generic Comparison Policy

A reusable implementation should separate:

```text
Insertion Sort Strategy
```

from:

```text
Ordering Criterion
```

Conceptually:

```text
Insertion Sort
        +
Comparator
        =
Reusable Sorting Operation
```

Example ordering policies include:

- ascending numerical order;
- descending numerical order;
- alphabetical order;
- product price;
- employee surname;
- transaction date;
- priority and deadline.

---

# Binary Insertion Sort

The sorted region allows the insertion position to be found using Binary Search.

Conceptually:

```text
Sorted Prefix
        ↓
Binary Search for Position
        ↓
Shift Elements
        ↓
Insert Key
```

This variation is called:

```text
Binary Insertion Sort
```

Binary Search can reduce the number of comparisons required to locate the insertion position.

However, the algorithm may still need to shift:

```text
O(n)
```

elements during each insertion.

Therefore, its overall worst-case time complexity remains:

```text
O(n²)
```

Binary search reduces comparison cost but does not eliminate movement cost.

---

# Java Implementation Considerations

A basic implementation for integer arrays may follow this structure:

```java
public final class InsertionSort {

    private InsertionSort() {
        throw new AssertionError("Utility class");
    }

    public static void sort(int[] values) {
        // Implementation developed after the algorithmic analysis.
    }
}
```

The method should document that the input array is modified in place.

---

# Suggested Method Contract

```java
/**
 * Sorts the supplied array in ascending numerical order
 * using Insertion Sort.
 *
 * <p>The operation modifies the input array in place.</p>
 *
 * @param values array to be sorted
 * @throws NullPointerException if {@code values} is {@code null}
 */
public static void sort(int[] values) {
    // ...
}
```

The project should define a consistent null-handling policy across every sorting implementation.

---

# Generic Java Design

A generic implementation may accept a comparator:

```java
public static <T> void sort(
        T[] values,
        Comparator<? super T> comparator
) {
    // ...
}
```

Example:

```text
InsertionSort.sort(
        products,
        Comparator.comparing(Product::price)
);
```

Multiple criteria:

```text
InsertionSort.sort(
        employees,
        Comparator.comparing(Employee::department)
                  .thenComparing(Employee::surname)
);
```

---

# Comparison Condition

The central comparison should represent the intended ordering policy.

For ascending primitive values:

```text
values[j] > key
```

For generic values:

```text
comparator.compare(values[j], key) > 0
```

Using strict comparison preserves stability.

---

# Example Implementation Structure

```text
public static void sort(int[] values) {
    Objects.requireNonNull(values, "values must not be null");

    for (int i = 1; i < values.length; i++) {
        int key = values[i];
        int j = i - 1;

        while (j >= 0 && values[j] > key) {
            values[j + 1] = values[j];
            j--;
        }

        values[j + 1] = key;
    }
}
```

This structure reflects the pseudocode directly.

The implementation should only be added after the theoretical behaviour and contract are understood.

---

# Order of Conditions

The inner-loop condition is commonly written as:

```text
while (j >= 0 && values[j] > key)
```

The boundary check must appear first.

Java uses short-circuit evaluation.

If:

```text
j = -1
```

then:

```text
j >= 0
```

is false, and:

```text
values[j]
```

is not evaluated.

Reversing the conditions may cause:

```text
ArrayIndexOutOfBoundsException
```

---

# Common Implementation Errors

Typical mistakes include:

- starting the outer loop at index `0`;
- failing to save the key before shifting;
- shifting in the wrong direction;
- inserting the key at `j` instead of `j + 1`;
- using an incorrect comparison operator;
- checking the array before validating the index;
- overwriting the current element;
- confusing shifts with swaps;
- using `>=` and unintentionally breaking stability.

---

# Starting Index Error

The outer loop should begin at:

```text
i = 1
```

The first element already forms a sorted region of size one.

Starting at index `0` is unnecessary and may complicate boundary handling.

---

# Incorrect Insertion Position

After the inner loop terminates, the key belongs at:

```text
j + 1
```

not:

```text
j
```

The index `j` represents:

- the element immediately before the insertion point; or
- `-1` when the key belongs at the beginning.

Therefore:

```text
Insertion Position = j + 1
```

---

# Testing Strategy

Insertion Sort should be tested using representative input categories.

---

## Empty Array

```text
Input

[]

Expected

[]
```

The algorithm should terminate without error.

---

## Single Element

```text
Input

[7]

Expected

[7]
```

No comparisons or shifts are required.

---

## Already Sorted Array

```text
Input

[1][2][3][4][5]

Expected

[1][2][3][4][5]
```

No shifting should occur.

---

## Reverse-Sorted Array

```text
Input

[5][4][3][2][1]

Expected

[1][2][3][4][5]
```

This exercises the maximum number of shifts.

---

## Nearly Sorted Array

```text
Input

[1][2][3][5][4][6]

Expected

[1][2][3][4][5][6]
```

This case verifies adaptive behaviour.

---

## Duplicate Values

```text
Input

[4][2][4][1][2]

Expected

[1][2][2][4][4]
```

All duplicate occurrences must be preserved.

---

## All Equal Values

```text
Input

[6][6][6][6]

Expected

[6][6][6][6]
```

No shifts should be required when strict comparison is used.

---

## Negative Values

```text
Input

[-3][7][-8][2][0]

Expected

[-8][-3][0][2][7]
```

---

## Key Belongs at Beginning

```text
Input

[2][3][4][1]

Expected

[1][2][3][4]
```

This verifies the:

```text
j = -1
```

boundary.

---

## Key Remains in Position

```text
Input

[1][2][3][4]

Expected

[1][2][3][4]
```

This verifies that unnecessary movement is avoided.

---

# Testing Stability

Distinct objects should be created with equivalent comparison keys.

Initial order:

```text
A — Key 10
B — Key 5
C — Key 10
```

Expected stable result:

```text
B — Key 5
A — Key 10
C — Key 10
```

The test must verify that:

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

The sorted result contains the same values and frequencies as the input.

## Stability Property

Equivalent elements preserve their original relative order.

## In-Place Property

The original array is modified according to the method contract.

---

# Example JUnit Structure

```java
class InsertionSortTest {

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
    void shouldPreserveDuplicateValues() {
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
    void shouldHandleNearlySortedArray() {
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

# Measuring Operations

An educational implementation may count:

```text
Comparisons
Shifts
Insertions
Assignments
```

Expected observations:

```text
Already Sorted Input

Comparisons ≈ n - 1
Shifts = 0
```

```text
Reverse-Sorted Input

Shifts = n(n - 1) / 2
```

```text
Nearly Sorted Input

Shifts ≈ Number of Inversions
```

These measurements connect theoretical analysis with observed behaviour.

---

# Benchmarking Considerations

Insertion Sort may be benchmarked using:

```text
Random Data
Already Sorted Data
Nearly Sorted Data
Reverse-Sorted Data
Duplicate-Heavy Data
Small Arrays
```

The experiments should demonstrate:

- linear behaviour for already sorted input;
- strong performance on nearly sorted input;
- quadratic behaviour for random data;
- quadratic worst-case growth for reverse-sorted input.

---

# Small Input Benchmarking

Insertion Sort often performs well for small arrays despite its quadratic asymptotic complexity.

Reasons may include:

- simple control flow;
- low constant factors;
- good memory locality;
- no recursive calls;
- no auxiliary array allocation;
- limited overhead.

Therefore, hybrid sorting algorithms may use Insertion Sort for small partitions.

---

# Hybrid Sorting Algorithms

Advanced sorting implementations often combine algorithms.

Conceptually:

```text
Large Input

↓

Divide into Smaller Partitions

↓

Partition Size Below Threshold?

├── No  → Continue Advanced Algorithm
└── Yes → Use Insertion Sort
```

Insertion Sort may be used for small partitions created by:

- Merge Sort;
- Quick Sort;
- hybrid library algorithms.

This demonstrates an important engineering principle:

> An algorithm with inferior asymptotic complexity may still be valuable within a carefully selected input range.

---

# Benchmark Interpretation

Practical execution time may depend on:

- inversion count;
- comparator cost;
- element size;
- branch prediction;
- memory locality;
- JVM optimisation;
- input size;
- data distribution.

Benchmark results must be interpreted together with theoretical complexity.

A small benchmark does not invalidate asymptotic analysis.

A complexity model does not replace practical measurement.

---

# Benchmarking Warning

Reliable Java microbenchmarking should use JMH.

Simple measurements based only on:

```text
System.nanoTime()
```

may be affected by:

- JVM warm-up;
- just-in-time compilation;
- dead-code elimination;
- garbage collection;
- measurement noise.

The benchmarking module will define a controlled experimental methodology.

---

# Advantages

Insertion Sort offers several advantages.

## Simplicity

The central strategy is straightforward:

```text
Select Key
        ↓
Shift Larger Elements
        ↓
Insert Key
```

---

## Linear Best Case

Already sorted input requires:

```text
O(n)
```

time.

---

## Adaptability

Performance improves when the collection contains few inversions.

---

## Stability

The strict-comparison implementation preserves equivalent-element order.

---

## Constant Auxiliary Space

The standard implementation uses:

```text
O(1)
```

additional space.

---

## Effective for Small Inputs

Low overhead can make the algorithm practical for small arrays or partitions.

---

## Online Processing

Elements can be inserted incrementally as they become available.

---

# Limitations

Insertion Sort also has important limitations.

## Quadratic Average Time

Random input generally requires:

```text
O(n²)
```

operations.

---

## Quadratic Worst-Case Time

Reverse-sorted input causes the maximum number of comparisons and shifts.

---

## Expensive Long-Distance Movement

An element far from its correct position may require many shifts.

---

## Limited Scalability

The algorithm is not appropriate as a general solution for large, randomly ordered collections.

---

# When Insertion Sort May Be Appropriate

Insertion Sort may be appropriate for:

- small collections;
- nearly sorted data;
- online insertion scenarios;
- small partitions in hybrid algorithms;
- educational demonstrations;
- stable in-place sorting requirements;
- collections with few inversions;
- situations where implementation simplicity matters.

---

# When Insertion Sort Should Not Be Preferred

It is generally unsuitable when:

- large random datasets are expected;
- worst-case response time must be scalable;
- many elements are far from their final positions;
- a reliable standard-library implementation is available;
- the collection is stored externally;
- guaranteed `O(n log n)` behaviour is required.

---

# Comparison with Selection Sort

| Property | Insertion Sort | Selection Sort |
|---|---|---|
| Main strategy | Insert into sorted prefix | Select minimum |
| Best-case time | `O(n)` | `O(n²)` |
| Average time | `O(n²)` | `O(n²)` |
| Worst-case time | `O(n²)` | `O(n²)` |
| Stable | Yes | No |
| Adaptive | Yes | No |
| In place | Yes | Yes |
| Data movement | Shifts | Few swaps |
| Nearly sorted data | Performs well | Does not benefit significantly |

Selection Sort may perform fewer writes.

Insertion Sort generally performs better when existing order is present.

---

# Comparison with Bubble Sort

| Property | Insertion Sort | Bubble Sort — optimised |
|---|---|---|
| Best-case time | `O(n)` | `O(n)` |
| Average time | `O(n²)` | `O(n²)` |
| Worst-case time | `O(n²)` | `O(n²)` |
| Stable | Yes | Yes |
| Adaptive | Yes | Yes |
| Main movement | Shifts | Adjacent swaps |
| Nearly sorted data | Usually effective | Can improve |
| Practical usefulness | Moderate for small inputs | Limited |

Insertion Sort generally performs fewer assignments than repeated adjacent swapping.

---

# Comparison with Merge Sort

| Property | Insertion Sort | Merge Sort |
|---|---|---|
| Best-case time | `O(n)` | Usually `O(n log n)` |
| Average time | `O(n²)` | `O(n log n)` |
| Worst-case time | `O(n²)` | `O(n log n)` |
| Stable | Yes | Yes |
| In place | Yes | No for standard array implementation |
| Small inputs | Often efficient | Recursive overhead may be higher |
| Large inputs | Poor scalability | Strong scalability |

Insertion Sort may be preferable for small partitions.

Merge Sort is more suitable for large collections requiring predictable performance.

---

# Engineering Perspective

Insertion Sort demonstrates that asymptotic complexity must be interpreted together with input characteristics.

A quadratic algorithm may perform efficiently when:

- the collection is small;
- the data is nearly sorted;
- the inversion count is low;
- allocation should be avoided;
- simple in-place behaviour is required.

Its running time is more accurately related to the disorder of the data than to input size alone.

Conceptually:

```text
Input Size
        +
Inversion Count
        =
Practical Insertion Sort Cost
```

This property distinguishes Insertion Sort from Selection Sort, whose comparison count remains quadratic regardless of initial order.

---

# Engineering Use in Hybrid Algorithms

Insertion Sort is especially important because it illustrates composition between algorithms.

A production sorting implementation does not always use a single strategy for every input size.

Instead:

```text
Large Partitions
        ↓
Asymptotically Efficient Algorithm
```

```text
Small Partitions
        ↓
Insertion Sort
```

This approach balances:

- asymptotic scalability;
- constant factors;
- memory locality;
- implementation overhead.

Understanding this trade-off prepares the learner for advanced algorithm engineering.

---

# Relationship with Previous Documents

This document applies concepts introduced in:

```text
01-sorting-fundamentals.md

↓

Ordering Criteria
Sorting Keys
Duplicate Values
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
Adaptability
Comparison-Based Sorting
```

```text
04-selection-sort.md

↓

Repeated Selection
Few Exchanges
Non-Adaptive Behaviour
```

```text
05-bubble-sort.md

↓

Adjacent Exchanges
Inversions
Adaptive Early Termination
```

Insertion Sort introduces a third elementary strategy based on incremental insertion and shifting.

---

# Relationship with Future Documents

The next algorithms improve asymptotic scalability.

```text
Insertion Sort
        ↓
Incremental Sorted Prefix
        ↓
Quadratic General Performance
```

```text
Merge Sort
        ↓
Divide and Conquer
        ↓
Linear Merging
        ↓
O(n log n)
```

```text
Quick Sort
        ↓
Partitioning
        ↓
Recursive Subproblems
```

Insertion Sort will remain relevant when comparing stability, memory usage, adaptability and small-input performance.

---

# Key Takeaways

Insertion Sort builds a sorted region incrementally.

During each iteration, it:

1. selects the next unsorted element;
2. stores it as the key;
3. shifts larger sorted elements to the right;
4. inserts the key into its correct position.

Its complexity is:

```text
Best Case       O(n)
Average Case    O(n²)
Worst Case      O(n²)
Auxiliary Space O(1)
```

The standard implementation:

- is stable;
- is in-place;
- is adaptive;
- is iterative;
- is comparison-based;
- performs especially well on small or nearly sorted collections.

Its number of shifts is directly related to the number of inversions in the input.

Insertion Sort is academically important and also has practical value as a component of hybrid sorting algorithms.

---

# Next Document

```text
07-merge-sort.md
```

The next document introduces Merge Sort, an efficient divide-and-conquer algorithm that recursively divides the collection into smaller subproblems and combines ordered subsequences through a linear merge operation.