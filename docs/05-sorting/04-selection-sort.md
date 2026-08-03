# Selection Sort

## Purpose

Selection Sort is an elementary comparison-based sorting algorithm that repeatedly selects the smallest element from the unsorted region of a collection and places it in its correct position.

The algorithm divides the collection into two logical regions:

```text
Sorted Region | Unsorted Region
```

At the beginning, the sorted region is empty and the unsorted region contains every element.

During each iteration, Selection Sort:

1. scans the unsorted region;
2. identifies the smallest element;
3. exchanges it with the first element of that region;
4. expands the sorted region by one position.

Selection Sort is not generally selected for large production workloads because its running time grows quadratically. However, it is valuable academically because it introduces several fundamental ideas:

- repeated selection;
- loop invariants;
- sorted and unsorted regions;
- comparison counting;
- exchange analysis;
- in-place transformation;
- correctness reasoning.

This document studies Selection Sort from algorithmic, mathematical and software engineering perspectives.

---

## Learning Objectives

After completing this document, the learner should be able to:

- explain the strategy used by Selection Sort;
- identify the sorted and unsorted regions of the collection;
- trace the algorithm manually;
- write Selection Sort pseudocode;
- state its preconditions and postconditions;
- formulate a suitable loop invariant;
- explain why the algorithm is correct;
- analyse its best, average and worst-case time complexity;
- calculate its comparison count;
- analyse its exchange count and auxiliary-space usage;
- explain why the usual implementation is not stable;
- recognise its advantages and limitations;
- identify situations in which the algorithm may still be useful;
- prepare a clear Java implementation and testing strategy.

---

# Algorithm Overview

Selection Sort repeatedly searches for the minimum element in the portion of the collection that has not yet been sorted.

Conceptually:

```text
Find Minimum in Unsorted Region
                ↓
Move Minimum to First Unsorted Position
                ↓
Expand Sorted Region
                ↓
Repeat
```

Consider the collection:

```text
[29][10][14][37][13]
```

Initially:

```text
Sorted Region   Unsorted Region
--------------  --------------------
                [29][10][14][37][13]
```

After selecting the smallest element, `10`, and exchanging it with `29`:

```text
Sorted Region   Unsorted Region
--------------  ----------------
[10]            [29][14][37][13]
```

The algorithm continues until every element belongs to the sorted region.

---

# Core Strategy

The algorithm maintains a boundary between the sorted and unsorted regions.

```text
A[0 ... i - 1] | A[i ... n - 1]
  Sorted Region | Unsorted Region
```

At each outer-loop iteration:

1. assume the first element of the unsorted region is the minimum;
2. scan the remainder of the unsorted region;
3. record the index of any smaller element found;
4. exchange the minimum element with the first element of the unsorted region.

After the exchange:

```text
A[i]
```

contains the smallest value from:

```text
A[i ... n - 1]
```

Therefore, that value is in its final sorted position.

---

# Initial State

At the beginning:

```text
i = 0
```

The sorted region is empty:

```text
A[0 ... -1]
```

The complete collection is unsorted:

```text
A[0 ... n - 1]
```

Diagram:

```text
Sorted Region | Unsorted Region
--------------|-------------------------
              | [29][10][14][37][13]
```

---

# Intermediate State

After several iterations, the collection has the form:

```text
[Sorted Elements] | [Unsorted Elements]
```

Example:

```text
[10][13] | [14][37][29]
```

The elements on the left are already in their final positions.

The elements on the right still require processing.

---

# Final State

When the unsorted region contains only one element, that element must already be in the correct position.

```text
[10][13][14][29] | [37]
```

No additional comparison is required for the final element.

The complete collection is then sorted:

```text
[10][13][14][29][37]
```

---

# Formal Problem

Given a collection:

```text
A = [a₀, a₁, a₂, ..., aₙ₋₁]
```

Selection Sort must produce a permutation:

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

A standard Selection Sort implementation assumes:

- the input collection exists;
- the collection contains a finite number of elements;
- every pair of elements can be compared;
- the comparison criterion is consistent;
- the collection allows indexed access and element replacement.

For a primitive integer array:

```text
Input: int[]
```

For a generic implementation:

```text
Input: Collection of comparable elements
```

or:

```text
Input: Collection and Comparator
```

---

# Postconditions

After the algorithm terminates:

- the collection is ordered according to the selected criterion;
- every input element remains present;
- no new element has been introduced;
- no element has been duplicated or lost;
- every position contains its final sorted value.

For ascending order:

```text
A[i] ≤ A[i + 1]
```

for every valid index:

```text
0 ≤ i < n - 1
```

---

# Selection Sort Pseudocode

A standard ascending-order version may be expressed as follows:

```text
SELECTION-SORT(A)

    n ← length(A)

    for i ← 0 to n - 2

        minimumIndex ← i

        for j ← i + 1 to n - 1

            if A[j] < A[minimumIndex]

                minimumIndex ← j

        if minimumIndex ≠ i

            exchange A[i] with A[minimumIndex]
```

The outer loop determines the next position to fill.

The inner loop identifies the smallest element in the remaining unsorted region.

---

# Pseudocode with Explicit Exchange

```text
SELECTION-SORT(A)

    n ← length(A)

    for i ← 0 to n - 2

        minimumIndex ← i

        for j ← i + 1 to n - 1

            if A[j] < A[minimumIndex]

                minimumIndex ← j

        temporary ← A[i]
        A[i] ← A[minimumIndex]
        A[minimumIndex] ← temporary
```

The conditional exchange can be included to avoid unnecessary assignments when the minimum element is already in position.

---

# Manual Trace

Consider:

```text
[29][10][14][37][13]
```

The objective is ascending order.

---

## Iteration 1

Current state:

```text
Sorted | Unsorted
-------|--------------------
       | [29][10][14][37][13]
```

Set:

```text
minimumIndex = 0
minimumValue = 29
```

Compare:

```text
10 < 29
```

Update:

```text
minimumIndex = 1
minimumValue = 10
```

Compare:

```text
14 < 10
```

False.

Compare:

```text
37 < 10
```

False.

Compare:

```text
13 < 10
```

False.

Exchange `29` and `10`:

```text
[10][29][14][37][13]
```

Result:

```text
Sorted | Unsorted
-------|----------------
[10]   | [29][14][37][13]
```

---

## Iteration 2

Current unsorted region:

```text
[29][14][37][13]
```

Assume:

```text
minimumValue = 29
```

Compare:

```text
14 < 29
```

Update minimum to `14`.

Compare:

```text
37 < 14
```

False.

Compare:

```text
13 < 14
```

Update minimum to `13`.

Exchange `29` and `13`:

```text
[10][13][14][37][29]
```

Result:

```text
Sorted      | Unsorted
------------|--------------
[10][13]    | [14][37][29]
```

---

## Iteration 3

Current unsorted region:

```text
[14][37][29]
```

Assume:

```text
minimumValue = 14
```

Compare:

```text
37 < 14
```

False.

Compare:

```text
29 < 14
```

False.

The minimum is already in the correct position.

No exchange is necessary.

State:

```text
[10][13][14][37][29]
```

Result:

```text
Sorted          | Unsorted
----------------|----------
[10][13][14]    | [37][29]
```

---

## Iteration 4

Current unsorted region:

```text
[37][29]
```

Assume:

```text
minimumValue = 37
```

Compare:

```text
29 < 37
```

Update minimum to `29`.

Exchange:

```text
[10][13][14][29][37]
```

Final state:

```text
[10][13][14][29][37]
```

---

# Trace Table

| Outer Iteration | Unsorted Region | Selected Minimum | Result |
|---:|---|---:|---|
| 0 | `[29, 10, 14, 37, 13]` | `10` | `[10, 29, 14, 37, 13]` |
| 1 | `[29, 14, 37, 13]` | `13` | `[10, 13, 14, 37, 29]` |
| 2 | `[14, 37, 29]` | `14` | `[10, 13, 14, 37, 29]` |
| 3 | `[37, 29]` | `29` | `[10, 13, 14, 29, 37]` |

---

# Visual Model

Selection Sort can be visualised as a progressive expansion of the sorted region.

```text
Initial

| 29 | 10 | 14 | 37 | 13 |
  -------------------------
       Unsorted Region
```

```text
After Iteration 1

| 10 | 29 | 14 | 37 | 13 |
  ---- --------------------
 Sorted      Unsorted
```

```text
After Iteration 2

| 10 | 13 | 14 | 37 | 29 |
  --------- ---------------
   Sorted       Unsorted
```

```text
After Iteration 3

| 10 | 13 | 14 | 37 | 29 |
  -------------- ----------
      Sorted       Unsorted
```

```text
After Iteration 4

| 10 | 13 | 14 | 29 | 37 |
  -------------------------
          Sorted
```

---

# Loop Invariant

A loop invariant is a property that remains true before and after each iteration of a loop.

For Selection Sort, a suitable invariant is:

> At the beginning of each outer-loop iteration with index `i`, the subarray `A[0 ... i - 1]` contains the `i` smallest elements of the original collection in sorted order.

This invariant describes the relationship between the completed work and the remaining work.

---

# Correctness Reasoning

The correctness argument can be organised into three parts:

1. initialisation;
2. maintenance;
3. termination.

---

## Initialisation

Before the first iteration:

```text
i = 0
```

The sorted region:

```text
A[0 ... -1]
```

is empty.

An empty region is trivially sorted and contains the zero smallest elements.

Therefore, the loop invariant holds before the first iteration.

---

## Maintenance

Assume the invariant holds at the beginning of iteration `i`.

Then:

```text
A[0 ... i - 1]
```

contains the `i` smallest elements in sorted order.

The inner loop examines:

```text
A[i ... n - 1]
```

and finds the smallest element in that unsorted region.

The algorithm exchanges that element with:

```text
A[i]
```

After the exchange:

- `A[i]` contains the smallest remaining element;
- `A[0 ... i]` contains the `i + 1` smallest elements;
- those elements are in sorted order.

Therefore, the invariant holds at the beginning of the next iteration.

---

## Termination

The outer loop terminates after:

```text
n - 1
```

iterations.

At that point:

```text
A[0 ... n - 2]
```

contains the `n - 1` smallest elements in sorted order.

The only remaining element at:

```text
A[n - 1]
```

must be the largest element.

Therefore, the complete collection is sorted.

---

# Termination Argument

Selection Sort always terminates because:

- the outer-loop index increases by one during every iteration;
- the outer loop has a finite upper bound;
- the inner-loop index also increases during every iteration;
- the inner loop scans a finite region.

Conceptually:

```text
Finite Collection
        +
Monotonically Increasing Indices
        =
Guaranteed Termination
```

---

# Permutation Preservation

Selection Sort modifies the collection only through exchanges.

An exchange:

```text
A[i] ↔ A[j]
```

changes element positions but does not:

- create elements;
- remove elements;
- duplicate elements;
- modify their values.

Therefore, every step preserves the permutation property.

Since the initial collection is a permutation of itself, the final collection contains exactly the same elements.

---

# Time Complexity

Selection Sort performs two principal operations:

- comparisons;
- exchanges.

The comparison count dominates the asymptotic running time.

---

## Outer Loop

The outer loop executes:

```text
n - 1
```

times.

It does not need to process the final position because the remaining element is already determined.

---

## Inner Loop

During each outer iteration, the inner loop scans the unsorted region.

Comparison counts:

```text
First iteration:      n - 1
Second iteration:     n - 2
Third iteration:      n - 3
...
Final iteration:      1
```

Total comparisons:

```text
(n - 1) + (n - 2) + ... + 2 + 1
```

This arithmetic series equals:

```text
n(n - 1) / 2
```

Expanding:

```text
(n² - n) / 2
```

Ignoring constant factors and lower-order terms:

```text
O(n²)
```

---

# Best-Case Complexity

For an already sorted collection:

```text
[1][2][3][4][5]
```

Selection Sort still scans the complete unsorted region during every iteration.

It must verify that the current element is the minimum.

Therefore:

```text
Best Case = O(n²)
```

The initial order does not reduce the number of comparisons.

---

# Average-Case Complexity

For randomly ordered input, the algorithm still performs the same number of comparisons:

```text
n(n - 1) / 2
```

Therefore:

```text
Average Case = O(n²)
```

The positions of the minimum values may affect exchanges, but not the asymptotic comparison count.

---

# Worst-Case Complexity

For reverse-sorted input:

```text
[5][4][3][2][1]
```

Selection Sort again performs:

```text
n(n - 1) / 2
```

comparisons.

Therefore:

```text
Worst Case = O(n²)
```

---

# Complexity Summary

| Case | Time Complexity |
|---|---:|
| Best | `O(n²)` |
| Average | `O(n²)` |
| Worst | `O(n²)` |

Selection Sort is non-adaptive because its comparison count does not improve when the input is already or nearly sorted.

---

# Exact Comparison Count

The exact number of key comparisons is:

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

This growth illustrates why Selection Sort becomes inefficient as input size increases.

---

# Exchange Count

Selection Sort typically performs at most one exchange per outer iteration.

Therefore, the maximum exchange count is:

```text
n - 1
```

If the implementation avoids exchanging an element with itself:

```text
if minimumIndex ≠ i
```

then the actual number of exchanges may be smaller.

---

## Comparison with Bubble Sort

Selection Sort may perform fewer exchanges than Bubble Sort.

Conceptually:

```text
Selection Sort

Many comparisons
Few exchanges
```

```text
Bubble Sort

Many comparisons
Potentially many exchanges
```

This distinction may matter when data movement is expensive.

---

# Assignment Count per Exchange

A conventional exchange uses three assignments:

```text
temporary ← A[i]
A[i] ← A[minimumIndex]
A[minimumIndex] ← temporary
```

With at most:

```text
n - 1
```

exchanges, the exchange-related assignment count remains linear.

However, the algorithm still performs a quadratic number of comparisons.

---

# Space Complexity

Selection Sort sorts the collection in place.

It normally uses only a small number of variables:

```text
i
j
minimumIndex
temporary
```

The number of variables does not depend on input size.

Therefore:

```text
Auxiliary Space = O(1)
```

---

# In-Place Property

The standard implementation modifies the original collection directly.

Before:

```text
[29][10][14][37][13]
```

After:

```text
[10][13][14][29][37]
```

No second array proportional to the input size is required.

Therefore, Selection Sort is considered in-place.

---

# Stability

The standard exchange-based implementation of Selection Sort is not stable.

A distant minimum element may be exchanged with the first unsorted element, changing the relative order of equivalent keys.

---

## Stability Example

Consider the records:

```text
[A₁, key 4]
[B,  key 2]
[A₂, key 4]
[C,  key 1]
```

Initial order of equivalent elements:

```text
A₁ before A₂
```

During the first iteration, `C` is selected and exchanged with `A₁`.

Result:

```text
[C,  key 1]
[B,  key 2]
[A₂, key 4]
[A₁, key 4]
```

The relative order of `A₁` and `A₂` has changed.

Therefore, the algorithm is unstable.

---

# Can Selection Sort Be Made Stable?

A stable variation is possible.

Instead of exchanging the minimum element directly, the algorithm may:

1. save the minimum element;
2. shift every preceding element one position to the right;
3. insert the minimum at the first unsorted position.

Conceptually:

```text
Find Minimum

↓

Remove Minimum

↓

Shift Intermediate Elements Right

↓

Insert Minimum
```

This preserves the relative order of equivalent elements.

However, it increases data movement and changes the practical characteristics of the standard algorithm.

The implementation studied in this repository should explicitly document whether it is stable or unstable.

---

# Adaptability

Standard Selection Sort is not adaptive.

It performs the same number of comparisons for:

```text
Already Sorted Input
```

```text
Random Input
```

```text
Reverse-Sorted Input
```

Example:

```text
[1][2][3][4][5]
```

Even though the collection is already sorted, the algorithm still searches for the minimum in every remaining region.

Therefore, existing order does not improve its asymptotic execution time.

---

# Comparison-Based Property

Selection Sort is comparison-based.

It determines the minimum element through repeated comparisons:

```text
A[j] < A[minimumIndex]
```

The algorithm does not rely on:

- numerical ranges;
- digit representations;
- hashing;
- direct key indexing.

Therefore, it can work with any type that provides a valid ordering relation.

---

# Internal Sorting

The standard Selection Sort implementation is an internal sorting algorithm.

It assumes that the complete collection is available in main memory.

Typical input structures include:

- arrays;
- array-backed lists;
- small in-memory collections.

It is not designed for large datasets stored across files or external storage.

---

# Iterative Structure

Selection Sort is usually implemented iteratively.

It uses:

- one outer loop;
- one inner loop.

Conceptually:

```text
Outer Loop
    ↓
Choose Position

Inner Loop
    ↓
Find Minimum
```

No recursion is required.

Therefore, it does not consume recursion-stack space.

---

# Descending Selection Sort

Selection Sort can also produce descending order.

Instead of selecting the minimum element, the algorithm selects the maximum element.

Pseudocode condition:

```text
if A[j] > A[maximumIndex]
```

Strategy:

```text
Find Maximum in Unsorted Region
                ↓
Place Maximum at First Unsorted Position
                ↓
Repeat
```

The overall complexity remains unchanged.

---

# Generic Comparison Policy

A reusable implementation should separate:

```text
Sorting Strategy
```

from:

```text
Ordering Criterion
```

Conceptually:

```text
Selection Sort
        +
Comparator
        =
Reusable Sorting Operation
```

The algorithm can then support:

- ascending numerical order;
- descending numerical order;
- alphabetical order;
- product price;
- employee surname;
- multiple business keys.

---

# Java Implementation Considerations

A basic integer-array implementation may follow this structure:

```text
public final class SelectionSort {

    private SelectionSort() {
        throw new AssertionError("Utility class");
    }

    public static void sort(int[] values) {
        // Implementation developed after the algorithmic analysis.
    }
}
```

The implementation should make its mutation behaviour clear:

```text
The input array is modified in place.
```

---

## Suggested Method Contract

```text
/**
 * Sorts the supplied array in ascending numerical order.
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

The exact null-handling policy should be defined consistently across the project.

Possible policies include:

- reject `null` with `NullPointerException`;
- reject `null` with `IllegalArgumentException`;
- treat `null` as invalid according to the documented precondition.

Silent acceptance should be avoided unless explicitly justified.

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

This design separates the algorithm from the domain-specific ordering rule.

Example usage:

```text
SelectionSort.sort(
        products,
        Comparator.comparing(Product::price)
);
```

Descending order:

```text
SelectionSort.sort(
        products,
        Comparator.comparing(Product::price).reversed()
);
```

---

# Safe Exchange Method

An exchange operation may be isolated in a helper method:

```text
private static void swap(int[] values, int first, int second) {
    int temporary = values[first];
    values[first] = values[second];
    values[second] = temporary;
}
```

Benefits include:

- improved readability;
- reduced duplication;
- clearer intent;
- easier debugging.

However, excessive abstraction should not obscure the core algorithm while it is being studied.

---

# Avoiding Unnecessary Self-Exchange

If:

```text
minimumIndex = i
```

then the minimum element is already in the correct position.

The implementation may avoid the exchange:

```text
if (minimumIndex != i) {
    swap(values, i, minimumIndex);
}
```

This does not change asymptotic complexity.

It only reduces unnecessary assignments.

---

# Index Boundaries

For a collection of length `n`, the outer loop should stop at:

```text
n - 2
```

because the last element does not need explicit processing.

Conceptually:

```text
for (int i = 0; i < values.length - 1; i++) {
    // ...
}
```

The inner loop begins at:

```text
i + 1
```

because:

```text
A[i]
```

is already the initial minimum candidate.

Conceptually:

```text
for (int j = i + 1; j < values.length; j++) {
    // ...
}
```

---

# Common Boundary Errors

Typical mistakes include:

```text
Starting j at 0 during every iteration
```

This causes the algorithm to rescan the sorted region.

```text
Stopping the inner loop before the final element
```

This may ignore the true minimum.

```text
Running the outer loop to n
```

This may create unnecessary work or invalid index access.

```text
Using minimumValue without tracking minimumIndex
```

This makes it difficult to exchange the selected element correctly.

---

# Testing Strategy

Selection Sort should be tested using representative input categories.

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

No exchange is required.

---

## Already Sorted Array

```text
Input

[1][2][3][4][5]

Expected

[1][2][3][4][5]
```

The result remains unchanged.

---

## Reverse-Sorted Array

```text
Input

[5][4][3][2][1]

Expected

[1][2][3][4][5]
```

This verifies repeated minimum selection.

---

## Duplicate Values

```text
Input

[4][2][4][1][2]

Expected

[1][2][2][4][4]
```

Every duplicate must be preserved.

---

## All Equal Values

```text
Input

[6][6][6][6]

Expected

[6][6][6][6]
```

The algorithm should not lose or duplicate values.

---

## Negative Values

```text
Input

[-3][7][-8][2][0]

Expected

[-8][-3][0][2][7]
```

The comparison logic must support the complete integer range.

---

## Mixed Values

```text
Input

[0][-1][5][-10][3]

Expected

[-10][-1][0][3][5]
```

---

# Testing Correctness Properties

Tests should verify both fundamental requirements.

## Ordering Property

For every valid index:

```text
A[i] ≤ A[i + 1]
```

---

## Permutation Property

The output must contain the same element frequencies as the input.

For duplicate-heavy collections, verifying only ascending order is not sufficient.

A defect could produce:

```text
[1][2][4]
```

from:

```text
[4][2][4][1]
```

The output would appear sorted but would violate permutation preservation.

---

# Example JUnit Structure

A future test class may follow this structure:

```java
class SelectionSortTest {

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
}
```

The exact implementation belongs in the source and testing modules rather than this conceptual document.

---

# Measuring Comparisons and Exchanges

An educational implementation may include operation counters.

Example measurements:

```text
Comparisons
Exchanges
Assignments
```

For input size `n`, the expected comparison count is:

```text
n(n - 1) / 2
```

This creates an opportunity to compare theoretical analysis with observed execution.

---

# Benchmarking Considerations

Selection Sort may be benchmarked using JMH with inputs such as:

```text
Random Data
Already Sorted Data
Reverse-Sorted Data
Nearly Sorted Data
Duplicate-Heavy Data
```

Because Selection Sort is non-adaptive, its comparison count should remain approximately consistent for equal input sizes.

Execution time may still vary because of:

- branch behaviour;
- cache effects;
- exchange count;
- element type;
- comparator cost;
- JVM optimisation.

---

# Benchmarking Warning

Microbenchmarks should not be implemented with simple manual timing such as:

```java
long start = System.nanoTime();
```

without appropriate controls.

Reliable Java microbenchmarking should use JMH because it accounts for concerns such as:

- JVM warm-up;
- dead-code elimination;
- just-in-time compilation;
- repeated measurement;
- statistical variation.

The benchmarking module will address these topics separately.

---

# Advantages

Selection Sort offers several advantages.

## Simplicity

The algorithm is conceptually straightforward.

```text
Find Minimum
        ↓
Place Minimum
        ↓
Repeat
```

This makes it useful for teaching algorithmic reasoning.

---

## Constant Auxiliary Space

The standard implementation requires:

```text
O(1)
```

additional space.

---

## Limited Number of Exchanges

Selection Sort performs at most:

```text
n - 1
```

exchanges.

This may be useful when writes are significantly more expensive than comparisons.

---

## Predictable Operation Count

The comparison count is independent of the initial arrangement.

This makes its behaviour easy to analyse.

---

# Limitations

Selection Sort also has important limitations.

## Quadratic Time Complexity

For large inputs:

```text
O(n²)
```

growth becomes inefficient.

---

## Non-Adaptive Behaviour

Already sorted and nearly sorted inputs do not significantly reduce the work.

---

## Instability

The standard exchange-based implementation does not preserve the relative order of equivalent elements.

---

## Limited Production Use

General-purpose Java applications should normally prefer standard-library sorting implementations.

Selection Sort is primarily useful for:

- education;
- experimentation;
- small collections;
- environments where writes are costly;
- cases where implementation simplicity is more important than scalability.

---

# When Selection Sort May Be Appropriate

Possible scenarios include:

- very small collections;
- educational demonstrations;
- algorithm visualisations;
- systems where writes are expensive;
- simple embedded environments;
- controlled cases where stability is unnecessary;
- comparison with more advanced algorithms.

Even in these scenarios, the decision should be justified by measured or documented constraints.

---

# When Selection Sort Should Not Be Preferred

It is generally unsuitable when:

- the input may grow significantly;
- low response time is required;
- stability is mandatory;
- nearly sorted data should be exploited;
- a reliable library implementation is available;
- predictable scalable performance is required.

---

# Comparison with Insertion Sort

Both algorithms are in-place and have quadratic worst-case time.

However:

| Property | Selection Sort | Insertion Sort |
|---|---|---|
| Best-case time | `O(n²)` | `O(n)` |
| Stable | No | Yes |
| Adaptive | No | Yes |
| Exchanges or movements | Few swaps | Potentially many shifts |
| Nearly sorted data | Does not benefit significantly | Often performs well |

Insertion Sort is generally more effective for nearly sorted data.

Selection Sort may perform fewer writes.

---

# Comparison with Bubble Sort

| Property | Selection Sort | Bubble Sort — optimised |
|---|---|---|
| Best-case time | `O(n²)` | `O(n)` |
| Average time | `O(n²)` | `O(n²)` |
| Stable | No | Yes |
| Adaptive | No | Yes |
| Data movement | Few swaps | Potentially many swaps |
| Main strategy | Repeated selection | Adjacent exchange |

Both are primarily educational algorithms, but they illustrate different design patterns.

---

# Engineering Perspective

Selection Sort demonstrates that asymptotic complexity is not the only measurable property of an algorithm.

Its main trade-off is:

```text
Many Comparisons
        +
Few Exchanges
```

This may be relevant when:

- writing to storage is expensive;
- elements are large;
- swap operations involve significant cost.

However, modern software engineering should consider the complete operation.

If comparisons are also expensive, the fixed quadratic comparison count may dominate performance.

For object collections, comparator execution may involve:

- string comparison;
- date conversion;
- multiple field access;
- locale-specific logic;
- business-rule evaluation.

In those situations, repeatedly scanning the unsorted region may be particularly costly.

---

# Relationship with Previous Documents

This document applies the concepts introduced in:

```text
01-sorting-fundamentals.md

↓

Ordering Criteria
Sorting Keys
Ascending and Descending Order
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

Selection Sort provides the first complete example in which these properties can be analysed together.

---

# Relationship with Future Documents

The next elementary algorithms will introduce different strategies.

```text
Selection Sort
        ↓
Repeated Minimum Selection
```

```text
Bubble Sort
        ↓
Adjacent Comparison and Exchange
```

```text
Insertion Sort
        ↓
Incremental Construction and Shifting
```

Later algorithms will improve asymptotic efficiency through:

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

Selection Sort provides an important baseline for comparing these approaches.

---

# Key Takeaways

Selection Sort divides the collection into:

```text
Sorted Region | Unsorted Region
```

During each iteration, it:

1. finds the smallest element in the unsorted region;
2. places that element at the beginning of the region;
3. expands the sorted region.

Its complexity is:

```text
Best Case      O(n²)
Average Case   O(n²)
Worst Case     O(n²)
Auxiliary Space O(1)
```

The exact comparison count is:

```text
n(n - 1) / 2
```

The standard implementation:

- is comparison-based;
- is in-place;
- is iterative;
- is non-adaptive;
- is not stable;
- performs relatively few exchanges.

Selection Sort is valuable for studying correctness, loop invariants, operation counting and algorithm trade-offs, although it is rarely appropriate for large production workloads.

---

# Next Document

```text
05-bubble-sort.md
```

The next document introduces Bubble Sort, an elementary stable sorting algorithm that repeatedly compares adjacent elements and exchanges them when they appear in the wrong order.