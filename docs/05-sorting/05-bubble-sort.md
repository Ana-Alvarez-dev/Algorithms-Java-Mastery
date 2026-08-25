# Bubble Sort

## Purpose

Bubble Sort is an elementary comparison-based sorting algorithm that repeatedly compares adjacent elements and exchanges them when they appear in the wrong order.

During each pass through the collection, larger elements gradually move toward the end of the array.

This movement resembles bubbles rising toward the surface, which gives the algorithm its name.

Conceptually:

```text
Compare Adjacent Elements
        ↓
Exchange if Out of Order
        ↓
Largest Remaining Element Moves Right
        ↓
Repeat
```

Bubble Sort is rarely used for large production workloads because its average and worst-case running times are quadratic.

However, it remains academically valuable because it introduces important ideas such as:

- adjacent comparison;
- local exchange;
- progressive boundary reduction;
- early termination;
- stability;
- adaptive behaviour;
- loop invariants;
- operation counting;
- correctness reasoning.

This document studies Bubble Sort from algorithmic, mathematical and software engineering perspectives.

---

## Learning Objectives

After completing this document, the learner should be able to:

- explain the strategy used by Bubble Sort;
- identify how adjacent comparisons transform the collection;
- describe how the largest unsorted element moves toward its final position;
- distinguish standard and optimised Bubble Sort;
- trace the algorithm manually;
- write Bubble Sort pseudocode;
- state its preconditions and postconditions;
- formulate a loop invariant;
- explain why the algorithm is correct;
- analyse best, average and worst-case time complexity;
- calculate comparison and exchange behaviour;
- explain why the usual implementation is stable;
- explain why the optimised version is adaptive;
- analyse its auxiliary-space usage;
- recognise its advantages and limitations;
- prepare a clear Java implementation and testing strategy.

---

# Algorithm Overview

Bubble Sort repeatedly scans the unsorted region of the collection.

For each pair of adjacent elements:

```text
A[j] and A[j + 1]
```

the algorithm checks whether they appear in the wrong order.

For ascending sorting:

```text
if A[j] > A[j + 1]
```

the elements are exchanged.

Consider:

```text
[5][1][4][2][8]
```

During the first pass:

```text
[5][1] → exchange
```

Result:

```text
[1][5][4][2][8]
```

Next:

```text
[5][4] → exchange
```

Result:

```text
[1][4][5][2][8]
```

Next:

```text
[5][2] → exchange
```

Result:

```text
[1][4][2][5][8]
```

Finally:

```text
[5][8] → no exchange
```

At the end of the pass, the largest element in the unsorted region is in its final position.

---

# Core Strategy

Bubble Sort maintains two logical regions:

```text
Unsorted Region | Sorted Region
```

At the beginning, the complete collection belongs to the unsorted region.

After each pass, the largest remaining element reaches the end of the unsorted region.

The sorted region therefore grows from right to left.

```text
A[0 ... n - i - 1] | A[n - i ... n - 1]
    Unsorted Region | Sorted Region
```

At each outer-loop iteration:

1. compare adjacent elements in the unsorted region;
2. exchange elements that are in the wrong order;
3. place the largest remaining value at the right boundary;
4. reduce the unsorted region by one position.

---

# Adjacent Comparison

Bubble Sort compares only neighbouring elements.

Example:

```text
[7][3][5][1]
```

The algorithm examines:

```text
[7][3]
```

then:

```text
[7][5]
```

then:

```text
[7][1]
```

after exchanges update their positions.

This local comparison strategy gradually moves misplaced values through the collection.

---

# Why Elements Move Like Bubbles

Suppose the largest value is near the beginning:

```text
[9][2][5][1][4]
```

During one pass:

```text
[9][2] → swap
[9][5] → swap
[9][1] → swap
[9][4] → swap
```

Result:

```text
[2][5][1][4][9]
```

The value `9` moves one position to the right after each exchange until it reaches its final position.

Conceptually:

```text
Large Element
        ↓
Repeated Adjacent Exchanges
        ↓
Moves Toward the Right Boundary
```

---

# Initial State

At the beginning:

```text
Unsorted Region              Sorted Region
---------------------------  -------------
[5][1][4][2][8]
```

No element has yet been guaranteed to occupy its final position.

---

# Intermediate State

After one complete pass:

```text
[1][4][2][5] | [8]
```

The largest element is in its final position.

After the second pass:

```text
[1][2][4] | [5][8]
```

The two largest elements are now correctly positioned.

---

# Final State

The algorithm finishes when:

- every required pass has been completed; or
- an entire pass produces no exchanges.

Final result:

```text
[1][2][4][5][8]
```

---

# Formal Problem

Given a collection:

```text
A = [a₀, a₁, a₂, ..., aₙ₋₁]
```

Bubble Sort must produce a permutation:

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

A standard Bubble Sort implementation assumes:

- the input collection exists;
- the collection contains a finite number of elements;
- every adjacent pair can be compared;
- the comparison criterion is consistent;
- the collection supports indexed access and element replacement.

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

- the collection is ordered according to the selected criterion;
- every input element remains present;
- no new element has been introduced;
- no element has been removed;
- no element has been duplicated;
- equivalent elements preserve their relative order in the standard implementation.

For ascending order:

```text
A[i] ≤ A[i + 1]
```

for every valid index:

```text
0 ≤ i < n - 1
```

---

# Standard Bubble Sort Pseudocode

A basic ascending-order implementation may be expressed as follows:

```text
BUBBLE-SORT(A)

    n ← length(A)

    for i ← 0 to n - 2

        for j ← 0 to n - 2 - i

            if A[j] > A[j + 1]

                exchange A[j] with A[j + 1]
```

The outer loop controls the number of passes.

The inner loop compares adjacent elements in the remaining unsorted region.

---

# Why the Inner Boundary Shrinks

After each complete pass, the largest unsorted element reaches its final position.

Therefore, the final positions do not need to be examined again.

First pass:

```text
j = 0 to n - 2
```

Second pass:

```text
j = 0 to n - 3
```

Third pass:

```text
j = 0 to n - 4
```

Conceptually:

```text
Unsorted Region Becomes Smaller
        ↓
Sorted Right Region Becomes Larger
```

---

# Optimised Bubble Sort

The standard version performs all passes even when the collection becomes sorted early.

An optimised implementation uses a Boolean flag.

```text
BUBBLE-SORT-OPTIMISED(A)

    n ← length(A)

    for i ← 0 to n - 2

        exchanged ← false

        for j ← 0 to n - 2 - i

            if A[j] > A[j + 1]

                exchange A[j] with A[j + 1]

                exchanged ← true

        if exchanged = false

            stop
```

If a complete pass produces no exchange, then every adjacent pair is already correctly ordered.

The collection is sorted and the algorithm may terminate.

---

# Early-Termination Principle

Suppose a complete pass satisfies:

```text
A[j] ≤ A[j + 1]
```

for every adjacent pair in the unsorted region.

Then:

```text
A[0] ≤ A[1] ≤ A[2] ≤ ... ≤ A[n - 1]
```

Therefore, no further work is required.

Conceptually:

```text
No Exchanges During a Complete Pass
        ↓
No Adjacent Inversion Exists
        ↓
Collection Is Sorted
```

---

# Manual Trace

Consider:

```text
[5][1][4][2][8]
```

The objective is ascending order.

---

## Pass 1

Initial state:

```text
[5][1][4][2][8]
```

Compare:

```text
5 > 1
```

Exchange:

```text
[1][5][4][2][8]
```

Compare:

```text
5 > 4
```

Exchange:

```text
[1][4][5][2][8]
```

Compare:

```text
5 > 2
```

Exchange:

```text
[1][4][2][5][8]
```

Compare:

```text
5 > 8
```

False.

End of pass:

```text
[1][4][2][5] | [8]
```

The value `8` is in its final position.

---

## Pass 2

Current unsorted region:

```text
[1][4][2][5]
```

Compare:

```text
1 > 4
```

False.

Compare:

```text
4 > 2
```

Exchange:

```text
[1][2][4][5][8]
```

Compare:

```text
4 > 5
```

False.

End of pass:

```text
[1][2][4] | [5][8]
```

---

## Pass 3

Current unsorted region:

```text
[1][2][4]
```

Compare:

```text
1 > 2
```

False.

Compare:

```text
2 > 4
```

False.

No exchanges occur.

The optimised algorithm terminates.

Final result:

```text
[1][2][4][5][8]
```

---

# Trace Table

| Pass | Comparisons | Exchanges | Result |
|---:|---:|---:|---|
| 1 | 4 | 3 | `[1, 4, 2, 5, 8]` |
| 2 | 3 | 1 | `[1, 2, 4, 5, 8]` |
| 3 | 2 | 0 | `[1, 2, 4, 5, 8]` |

The third pass confirms that the collection is already sorted.

---

# Visual Model

Initial state:

```text
| 5 | 1 | 4 | 2 | 8 |
  -------------------
      Unsorted
```

After pass 1:

```text
| 1 | 4 | 2 | 5 | 8 |
  --------------- ----
    Unsorted       Sorted
```

After pass 2:

```text
| 1 | 2 | 4 | 5 | 8 |
  ----------- --------
   Unsorted     Sorted
```

After pass 3:

```text
| 1 | 2 | 4 | 5 | 8 |
  -------------------
          Sorted
```

---

# Inversions

An inversion is a pair of positions:

```text
i < j
```

such that:

```text
A[i] > A[j]
```

for ascending order.

Example:

```text
[3][1][2]
```

The inversions are:

```text
(3, 1)
(3, 2)
```

Bubble Sort removes inversions through adjacent exchanges.

Each valid adjacent exchange decreases the total inversion count by exactly one.

This provides another way to reason about progress and termination.

---

# Example of Inversion Reduction

Initial collection:

```text
[3][1][2]
```

Inversion count:

```text
2
```

Exchange `3` and `1`:

```text
[1][3][2]
```

Inversion count:

```text
1
```

Exchange `3` and `2`:

```text
[1][2][3]
```

Inversion count:

```text
0
```

A collection is sorted when it contains no inversions.

---

# Loop Invariant

A suitable outer-loop invariant is:

> At the end of each pass `i`, the final `i + 1` positions contain the `i + 1` largest elements of the collection in sorted order.

Equivalently, at the beginning of pass `i`:

> The subarray `A[n - i ... n - 1]` contains the `i` largest elements in their final sorted positions.

---

# Correctness Reasoning

The correctness argument can be divided into:

1. initialisation;
2. maintenance;
3. termination.

---

## Initialisation

Before the first pass:

```text
i = 0
```

The sorted right region is empty.

An empty region trivially contains the zero largest elements in sorted order.

Therefore, the loop invariant holds.

---

## Maintenance

Assume the invariant holds before pass `i`.

The inner loop compares every adjacent pair in:

```text
A[0 ... n - i - 1]
```

Whenever a larger element appears before a smaller element, they are exchanged.

As the pass continues, the largest element in the unsorted region moves rightward until it reaches:

```text
A[n - i - 1]
```

That element is therefore in its final position.

The already sorted right region remains unchanged.

Thus, after the pass:

```text
A[n - i - 1 ... n - 1]
```

contains the `i + 1` largest elements in sorted order.

The invariant is maintained.

---

## Termination

The standard outer loop terminates after:

```text
n - 1
```

passes.

At that point, the last `n - 1` positions contain the `n - 1` largest elements in sorted order.

The remaining first element must be the smallest.

Therefore, the complete collection is sorted.

For the optimised version, termination may occur earlier when a pass produces no exchanges.

In that case, every adjacent pair is ordered, so the complete collection is sorted.

---

# Termination Argument

Bubble Sort always terminates because:

- the outer-loop index increases after every pass;
- the outer loop has a finite bound;
- the inner-loop index increases during every comparison;
- the unsorted region becomes smaller;
- in the optimised version, early termination can only reduce work.

From the inversion perspective:

- every exchange decreases the number of inversions;
- the inversion count is finite and non-negative;
- once no inversions remain, the collection is sorted.

---

# Permutation Preservation

Bubble Sort changes the collection only through exchanges.

An adjacent exchange:

```text
A[j] ↔ A[j + 1]
```

changes positions but does not:

- create elements;
- remove elements;
- duplicate elements;
- modify element values.

Therefore, every step preserves the permutation property.

The final result contains exactly the same elements as the input.

---

# Time Complexity

Bubble Sort performs:

- comparisons;
- exchanges;
- passes.

Its running time depends on both the implementation and the initial order of the input.

---

# Standard Comparison Count

Without early termination, the number of comparisons is:

```text
(n - 1) + (n - 2) + ... + 2 + 1
```

Therefore:

```text
C(n) = n(n - 1) / 2
```

This produces:

```text
O(n²)
```

time in every case for the non-optimised version.

---

# Best-Case Complexity

Consider an already sorted collection:

```text
[1][2][3][4][5]
```

With the exchange flag, Bubble Sort performs one complete pass.

Number of comparisons:

```text
n - 1
```

Number of exchanges:

```text
0
```

Therefore:

```text
Best Case = O(n)
```

This best case applies to the optimised version.

Without early termination:

```text
Best Case = O(n²)
```

---

# Average-Case Complexity

For randomly ordered input, many adjacent comparisons and exchanges are required.

The number of operations grows quadratically.

Therefore:

```text
Average Case = O(n²)
```

---

# Worst-Case Complexity

A reverse-sorted collection represents a worst-case arrangement.

Example:

```text
[5][4][3][2][1]
```

Every adjacent comparison results in an exchange.

The number of comparisons is:

```text
n(n - 1) / 2
```

The number of exchanges is also:

```text
n(n - 1) / 2
```

Therefore:

```text
Worst Case = O(n²)
```

---

# Complexity Summary

For optimised Bubble Sort:

| Case | Time Complexity |
|---|---:|
| Best | `O(n)` |
| Average | `O(n²)` |
| Worst | `O(n²)` |

For the unoptimised implementation:

| Case | Time Complexity |
|---|---:|
| Best | `O(n²)` |
| Average | `O(n²)` |
| Worst | `O(n²)` |

---

# Exact Comparison Count

Without early termination:

```text
C(n) = n(n - 1) / 2
```

Examples:

| Input Size `n` | Comparisons |
|---:|---:|
| 1 | 0 |
| 2 | 1 |
| 3 | 3 |
| 4 | 6 |
| 5 | 10 |
| 10 | 45 |
| 100 | 4,950 |
| 1,000 | 499,500 |

The optimised version may perform fewer comparisons when the collection becomes sorted early.

---

# Exchange Count

The exchange count depends on the number of inversions in the input.

For Bubble Sort:

```text
Number of Exchanges = Number of Initial Inversions
```

This is an important property.

Already sorted input:

```text
0 exchanges
```

Reverse-sorted input:

```text
n(n - 1) / 2 exchanges
```

---

# Worst-Case Exchange Count

For:

```text
[n][n - 1][n - 2] ... [2][1]
```

every pair is inverted.

Therefore, the inversion count is:

```text
n(n - 1) / 2
```

Bubble Sort performs that many adjacent exchanges.

This can make it expensive when element movement has a high cost.

---

# Space Complexity

Bubble Sort usually uses only a fixed number of variables:

```text
i
j
temporary
exchanged
```

The amount of additional storage does not depend on input size.

Therefore:

```text
Auxiliary Space = O(1)
```

---

# In-Place Property

Bubble Sort modifies the original collection directly.

Example:

```text
Before

[5][1][4][2]
```

```text
After

[1][2][4][5]
```

No auxiliary array proportional to input size is required.

Therefore, Bubble Sort is in-place.

---

# Stability

The standard Bubble Sort implementation is stable.

It exchanges adjacent elements only when:

```text
A[j] > A[j + 1]
```

Equivalent elements are not exchanged.

Therefore, their original relative order is preserved.

---

# Stability Example

Consider:

```text
[A₁, key 4]
[B,  key 2]
[A₂, key 4]
[C,  key 1]
```

When the algorithm compares:

```text
A₁ and A₂
```

their keys are equal.

Because the exchange condition is strictly:

```text
left > right
```

they are not exchanged.

After sorting:

```text
[C,  key 1]
[B,  key 2]
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

If the comparison condition is written as:

```text
A[j] ≥ A[j + 1]
```

equivalent elements may be exchanged.

This would destroy stability.

Therefore:

```text
Exchange only when left > right
```

is essential for a stable ascending-order implementation.

---

# Adaptability

Optimised Bubble Sort is adaptive because it can benefit from existing order.

For an already sorted collection:

```text
[1][2][3][4][5]
```

one pass is sufficient.

For a nearly sorted collection:

```text
[1][2][3][5][4]
```

the algorithm may require only a small number of exchanges and passes.

The early-termination flag allows the algorithm to stop once no inversions remain.

---

# Degree of Adaptability

Although Bubble Sort is adaptive in its optimised form, it is not generally the most effective algorithm for nearly sorted data.

Insertion Sort usually provides better practical behaviour because it moves misplaced elements directly through shifts rather than repeatedly exchanging adjacent pairs.

Therefore:

```text
Adaptive
```

does not necessarily mean:

```text
Best Practical Choice
```

---

# Comparison-Based Property

Bubble Sort is comparison-based.

It determines element order through adjacent comparisons:

```text
A[j] > A[j + 1]
```

It does not depend on:

- bounded integer ranges;
- digit positions;
- hashing;
- direct key indexing.

Therefore, it can support any type with a consistent ordering rule.

---

# Internal Sorting

The standard algorithm is intended for collections stored entirely in main memory.

Typical structures include:

- arrays;
- array-backed lists;
- small educational datasets.

Bubble Sort is not suitable for external sorting or very large datasets.

---

# Iterative Structure

Bubble Sort is usually implemented iteratively.

It contains:

- an outer loop for passes;
- an inner loop for adjacent comparisons.

No recursion is required.

This contributes to its:

```text
O(1)
```

auxiliary-space complexity.

---

# Descending Bubble Sort

To sort in descending order, the comparison condition is reversed.

Ascending:

```text
if A[j] > A[j + 1]
```

Descending:

```text
if A[j] < A[j + 1]
```

The algorithmic structure remains unchanged.

Only the ordering policy changes.

---

# Generic Comparison Policy

A reusable implementation should separate the sorting strategy from the ordering criterion.

Conceptually:

```text
Bubble Sort
        +
Comparator
        =
Reusable Sorting Operation
```

Possible comparison policies include:

- ascending numerical order;
- descending numerical order;
- alphabetical order;
- product price;
- transaction date;
- employee department and surname.

---

# Java Implementation Considerations

A basic integer-array implementation may follow this structure:

```java
public final class BubbleSort {

    private BubbleSort() {
        throw new AssertionError("Utility class");
    }

    public static void sort(int[] values) {
        // Implementation developed after algorithmic analysis.
    }
}
```

The method contract should state that the input array is modified in place.

---

# Suggested Method Contract

```java
/**
 * Sorts the supplied array in ascending numerical order
 * using an optimised Bubble Sort implementation.
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

The project should use a consistent policy for invalid `null` inputs.

---

# Generic Java Design

A generic implementation may accept a comparator:

```text
public static <T> void sort(
        T[] values,
        Comparator<? super T> comparator
) {
    // ...
}
```

Example:

```text
BubbleSort.sort(
        employees,
        Comparator.comparing(Employee::surname)
);
```

Descending price:

```text
BubbleSort.sort(
        products,
        Comparator.comparing(Product::price).reversed()
);
```

---

# Safe Exchange Method

The exchange operation may be isolated:

```text
private static void swap(int[] values, int first, int second) {
    int temporary = values[first];
    values[first] = values[second];
    values[second] = temporary;
}
```

Because Bubble Sort may perform many exchanges, the helper method should remain simple and efficient.

---

# Boolean Exchange Flag

The optimised implementation requires a flag:

```text
boolean exchanged;
```

At the beginning of each pass:

```text
exchanged = false;
```

After every swap:

```text
exchanged = true;
```

At the end of the pass:

```text
if (!exchanged) {
    break;
}
```

This logic must be reset during every outer iteration.

---

# Incorrect Flag Placement

A common mistake is to initialise the flag only once before the outer loop.

Incorrect concept:

```text
exchanged ← false

for every pass
    ...
```

Once the flag becomes true, it remains true permanently.

Early termination then becomes impossible.

Correct concept:

```text
for every pass

    exchanged ← false

    compare and exchange

    if no exchange occurred
        stop
```

---

# Index Boundaries

For an array of length `n`, the outer loop generally runs while:

```text
i < n - 1
```

The inner loop runs while:

```text
j < n - 1 - i
```

This ensures that:

```text
j + 1
```

remains a valid index.

Example:

```text
for (int i = 0; i < values.length - 1; i++) {
    for (int j = 0; j < values.length - 1 - i; j++) {
        // Compare values[j] and values[j + 1]
    }
}
```

---

# Common Boundary Errors

Typical errors include:

```text
Using j < n - i
```

This may cause access to:

```text
A[n]
```

through `A[j + 1]`.

```text
Failing to reduce the inner boundary
```

This repeats comparisons in the sorted region.

```text
Stopping the outer loop too early
```

This may leave the first region unsorted.

```text
Comparing non-adjacent elements
```

This changes the algorithm's intended behaviour.

---

# Testing Strategy

Bubble Sort should be tested using representative input categories.

---

## Empty Array

```text
Input

[]

Expected

[]
```

The algorithm should terminate immediately.

---

## Single Element

```text
Input

[7]

Expected

[7]
```

No comparisons or exchanges are required.

---

## Already Sorted Array

```text
Input

[1][2][3][4][5]

Expected

[1][2][3][4][5]
```

For the optimised version, only one pass should be required.

---

## Reverse-Sorted Array

```text
Input

[5][4][3][2][1]

Expected

[1][2][3][4][5]
```

This exercises the maximum number of exchanges.

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

A stable implementation should not exchange equal elements.

The optimised version should terminate after one pass.

---

## Negative Values

```text
Input

[-3][7][-8][2][0]

Expected

[-8][-3][0][2][7]
```

---

## Nearly Sorted Array

```text
Input

[1][2][3][5][4]

Expected

[1][2][3][4][5]
```

This case helps validate adaptive behaviour.

---

# Testing Stability

Use distinct objects with equivalent keys.

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

The test must verify object order, not only key order.

---

# Testing Early Termination

An educational implementation may count passes.

For already sorted input:

```text
[1][2][3][4][5]
```

Expected:

```text
Passes = 1
Exchanges = 0
```

This confirms that the optimisation is active.

---

# Testing Correctness Properties

Tests must verify:

## Ordering Property

For every valid index:

```text
A[i] ≤ A[i + 1]
```

## Permutation Property

The result contains the same elements and frequencies as the input.

## Stability Property

Equivalent keys preserve their original relative order.

## In-Place Behaviour

The original array is modified rather than replaced, when that is the documented contract.

---

# Example JUnit Structure

```java
class BubbleSortTest {

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
Passes
Comparisons
Exchanges
Assignments
```

Expected observations:

```text
Already Sorted Input

Comparisons ≈ n - 1
Exchanges = 0
```

```text
Reverse-Sorted Input

Comparisons = n(n - 1) / 2
Exchanges = n(n - 1) / 2
```

This connects theoretical analysis with actual execution.

---

# Benchmarking Considerations

Bubble Sort may be benchmarked using:

```text
Random Data
Already Sorted Data
Nearly Sorted Data
Reverse-Sorted Data
Duplicate-Heavy Data
```

The results should demonstrate:

- linear best-case behaviour for the optimised version;
- quadratic average growth;
- quadratic worst-case growth;
- high exchange cost for reverse-sorted data.

---

# Benchmark Interpretation

Even when two inputs have the same size, practical execution time may differ because of:

- exchange count;
- branch prediction;
- element type;
- comparator cost;
- memory locality;
- JVM optimisation.

However, large-input growth will still be dominated by:

```text
O(n²)
```

behaviour.

---

# Benchmarking Warning

Reliable Java microbenchmarking should use JMH.

Simple manual timing may be distorted by:

- JVM warm-up;
- just-in-time compilation;
- dead-code elimination;
- garbage collection;
- measurement noise.

Benchmarking must complement, not replace, complexity analysis.

---

# Advantages

Bubble Sort offers several educational and structural advantages.

## Simplicity

The algorithm is easy to describe:

```text
Compare Neighbours
        ↓
Exchange if Necessary
        ↓
Repeat
```

---

## Stability

The standard strict-comparison implementation preserves the relative order of equivalent elements.

---

## Constant Auxiliary Space

Bubble Sort requires:

```text
O(1)
```

additional memory.

---

## Adaptive Best Case

The optimised version detects already sorted input in:

```text
O(n)
```

time.

---

## Useful for Visualisation

Adjacent movement makes the algorithm easy to animate and trace manually.

This supports introductory study of:

- comparisons;
- exchanges;
- inversions;
- loop behaviour.

---

# Limitations

Bubble Sort has significant practical limitations.

## Quadratic Average and Worst-Case Time

Its running time grows as:

```text
O(n²)
```

for typical and unfavourable inputs.

---

## High Exchange Count

Reverse-sorted input requires:

```text
n(n - 1) / 2
```

exchanges.

This can be expensive when elements are large or writes are costly.

---

## Limited Scalability

The algorithm becomes impractical for large collections.

---

## Weak Practical Use

Although the optimised version is adaptive, Insertion Sort is generally more useful for small or nearly sorted collections.

---

# When Bubble Sort May Be Appropriate

Possible uses include:

- educational demonstrations;
- algorithm visualisation;
- teaching inversion removal;
- very small collections;
- controlled exercises involving stability;
- comparing exchange-based strategies;
- verifying operation-counting methods.

---

# When Bubble Sort Should Not Be Preferred

It is generally unsuitable when:

- input size may grow;
- performance is important;
- element exchanges are expensive;
- a standard-library implementation is available;
- scalable worst-case behaviour is required;
- the collection contains complex or large objects.

---

# Comparison with Selection Sort

| Property | Bubble Sort — optimised | Selection Sort |
|---|---|---|
| Main strategy | Adjacent exchange | Repeated selection |
| Best-case time | `O(n)` | `O(n²)` |
| Average time | `O(n²)` | `O(n²)` |
| Worst-case time | `O(n²)` | `O(n²)` |
| Stable | Yes | No |
| Adaptive | Yes | No |
| Maximum exchanges | `O(n²)` | `O(n)` |
| Auxiliary space | `O(1)` | `O(1)` |

Selection Sort performs fewer exchanges.

Bubble Sort provides stability and early termination.

---

# Comparison with Insertion Sort

| Property | Bubble Sort — optimised | Insertion Sort |
|---|---|---|
| Best-case time | `O(n)` | `O(n)` |
| Average time | `O(n²)` | `O(n²)` |
| Worst-case time | `O(n²)` | `O(n²)` |
| Stable | Yes | Yes |
| Adaptive | Yes | Yes |
| Main movement | Adjacent swaps | Shifts and insertion |
| Nearly sorted input | Can improve | Usually performs better |

Insertion Sort generally moves elements more efficiently.

Bubble Sort remains useful for understanding adjacent inversion removal.

---

# Engineering Perspective

Bubble Sort demonstrates how a small implementation detail can change an algorithm's practical properties.

Without an exchange flag:

```text
Best Case = O(n²)
```

With an exchange flag:

```text
Best Case = O(n)
```

The asymptotic average and worst cases remain unchanged, but the algorithm becomes adaptive.

This illustrates an important engineering principle:

> Optimisations must be evaluated according to the input scenarios they improve, without overstating their effect on overall scalability.

Bubble Sort also demonstrates that movement cost matters.

Its repeated adjacent exchanges may be expensive when sorting:

- large objects;
- records stored indirectly;
- structures with costly assignment operations;
- data written to slow storage.

In such cases, comparison count alone does not describe the complete cost.

---

# Relationship with Previous Documents

This document applies the concepts introduced in:

```text
01-sorting-fundamentals.md

↓

Ordering Criteria
Duplicate Values
Natural and Custom Order
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

Sorted and Unsorted Regions
Loop Invariants
Quadratic Complexity
```

Bubble Sort provides a contrasting elementary strategy based on local exchanges rather than repeated minimum selection.

---

# Relationship with Future Documents

The next algorithm introduces incremental insertion.

```text
Bubble Sort
        ↓
Adjacent Exchanges
        ↓
Progressive Movement
```

```text
Insertion Sort
        ↓
Sorted Left Region
        ↓
Shifting and Insertion
```

Later documents will introduce more efficient asymptotic strategies:

```text
Merge Sort
        ↓
Divide and Conquer
```

```text
Quick Sort
        ↓
Partitioning
```

Bubble Sort will serve as an important comparison point for stability, adaptability and movement cost.

---

# Key Takeaways

Bubble Sort repeatedly compares adjacent elements and exchanges them when they appear in the wrong order.

After each pass:

```text
The largest remaining element reaches its final position.
```

The optimised version uses an exchange flag to stop when a complete pass makes no changes.

Its complexity is:

```text
Best Case       O(n)
Average Case    O(n²)
Worst Case      O(n²)
Auxiliary Space O(1)
```

The standard strict-comparison implementation:

- is stable;
- is in-place;
- is adaptive when early termination is used;
- is comparison-based;
- is iterative;
- may perform a quadratic number of exchanges.

Bubble Sort is valuable for studying adjacent comparisons, inversions, stability, loop invariants and adaptive behaviour, although it is rarely appropriate for large production workloads.

---

# Next Document

```text
06-insertion-sort.md
```

The next document introduces Insertion Sort, a stable and adaptive algorithm that incrementally builds a sorted region by shifting larger elements and inserting each new value into its correct position.