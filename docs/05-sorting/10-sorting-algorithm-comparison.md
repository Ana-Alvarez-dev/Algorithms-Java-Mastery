# Sorting Algorithm Comparison

## Part I — Foundations and Core Comparison Criteria

## Purpose

The purpose of this document is to compare the principal sorting algorithms studied in this module:

- Selection Sort;
- Bubble Sort;
- Insertion Sort;
- Merge Sort;
- Quick Sort.

Each previous document analysed one algorithm independently. This document integrates those analyses into a unified framework that supports algorithm selection, technical evaluation and engineering decision-making.

The comparison focuses on the following dimensions:

```text
Algorithmic Strategy
        ↓
Time Complexity
        ↓
Auxiliary Space
        ↓
Stability
        ↓
Adaptability
        ↓
Data Movement
        ↓
Recursive Structure
        ↓
Practical Suitability
```

The objective is not to identify one universally superior algorithm.

Instead, the objective is to determine:

```text
Which algorithm is appropriate
        +
Under which conditions
        +
According to which requirements
```

Algorithm selection depends on multiple factors, including:

- input size;
- input distribution;
- available memory;
- stability requirements;
- data structure;
- worst-case guarantees;
- implementation complexity;
- expected execution environment.

---

# Learning Objectives

After completing this document, the learner should be able to:

- compare the strategies used by different sorting algorithms;
- distinguish quadratic and linearithmic algorithms;
- compare best, average and worst-case performance;
- analyse auxiliary-space requirements;
- explain the importance of stability;
- identify adaptive sorting algorithms;
- distinguish recursive and iterative approaches;
- compare exchanges, shifts and copying operations;
- evaluate algorithms according to input characteristics;
- recognise the trade-offs involved in sorting-algorithm selection;
- justify an algorithm choice from an engineering perspective.

---

# Why Comparing Sorting Algorithms Matters

Knowing how an algorithm works is not sufficient for professional software development.

An engineer must also understand:

- how the algorithm behaves as input size increases;
- how it reacts to ordered, reversed or duplicate-heavy input;
- whether it requires additional memory;
- whether it preserves the order of equivalent elements;
- whether its worst-case behaviour is acceptable;
- whether another algorithm better matches the problem constraints.

Consider two algorithms with the same average complexity:

```text
Merge Sort       O(n log n)
Quick Sort       O(n log n) average
```

Their asymptotic performance appears similar.

However, their properties differ substantially:

```text
Merge Sort

Stable
Predictable worst case
Requires auxiliary storage
```

```text
Quick Sort

Usually unstable
Possible quadratic worst case
Uses in-place partitioning
```

Therefore, complexity alone does not determine the correct choice.

---

# Algorithm Selection as a Multi-Criteria Decision

Sorting-algorithm selection can be modelled as a multi-criteria decision.

```text
Input Characteristics
        +
Performance Requirements
        +
Memory Constraints
        +
Ordering Semantics
        +
Implementation Context
        =
Algorithm Choice
```

For example:

```text
Small and Nearly Sorted Array
        ↓
Insertion Sort
```

```text
Large Array with Stable Ordering Required
        ↓
Merge-Based Strategy
```

```text
Large In-Memory Primitive Array
        +
Stability Not Required
        ↓
Quick-Sort-Based Strategy
```

```text
Very Small Educational Example
        ↓
Selection Sort or Bubble Sort
```

The appropriate algorithm depends on the complete problem context.

---

# Algorithms Included in the Comparison

This document compares five algorithms.

## Selection Sort

Selection Sort repeatedly finds the smallest element in the unsorted region and moves it into its final position.

```text
Find Minimum
        ↓
Move Minimum to Boundary
        ↓
Expand Sorted Region
```

---

## Bubble Sort

Bubble Sort repeatedly compares adjacent elements and exchanges them when they are in the wrong order.

```text
Compare Adjacent Elements
        ↓
Exchange Inversions
        ↓
Move Large Values Right
```

---

## Insertion Sort

Insertion Sort builds a sorted prefix by inserting each new element into its correct position.

```text
Take Next Element
        ↓
Shift Larger Elements
        ↓
Insert Element
```

---

## Merge Sort

Merge Sort recursively divides the collection, sorts the resulting subproblems and merges them.

```text
Divide
        ↓
Recursively Sort
        ↓
Merge
```

---

## Quick Sort

Quick Sort selects a pivot, partitions the collection around it and recursively sorts the resulting regions.

```text
Select Pivot
        ↓
Partition
        ↓
Recursively Sort Partitions
```

---

# High-Level Strategy Comparison

| Algorithm | Core Strategy | Main Structural Operation |
|---|---|---|
| Selection Sort | Repeated selection | Minimum selection |
| Bubble Sort | Repeated local correction | Adjacent exchange |
| Insertion Sort | Incremental construction | Shifting and insertion |
| Merge Sort | Divide and conquer | Merging sorted regions |
| Quick Sort | Divide and conquer | Pivot partitioning |

Although all five algorithms produce an ordered permutation, they reach that result through fundamentally different transformations.

---

# Sorting Region Models

Each algorithm maintains a different logical structure during execution.

---

## Selection Sort Region Model

```text
Sorted Region | Unsorted Region
```

The sorted region grows from left to right.

Example:

```text
[1][3][5] | [9][7][6]
```

At every iteration, the minimum value from the unsorted region is selected.

---

## Bubble Sort Region Model

```text
Unresolved Region | Final Ordered Suffix
```

After each complete pass, one of the largest remaining values reaches its final position.

Example:

```text
[4][2][3] | [5][7][9]
```

The ordered suffix grows from right to left.

---

## Insertion Sort Region Model

```text
Sorted Prefix | Unprocessed Region
```

Example:

```text
[1][3][5][8] | [4][7]
```

The next unprocessed value is inserted into the sorted prefix.

---

## Merge Sort Region Model

```text
Independent Subarrays
        ↓
Sorted Subarrays
        ↓
Merged Ordered Region
```

Example:

```text
[1][5][8] + [2][4][7]
```

becomes:

```text
[1][2][4][5][7][8]
```

---

## Quick Sort Region Model

```text
Values ≤ Pivot | Pivot | Values > Pivot
```

Example:

```text
[2][1][4] | [5] | [8][9][7]
```

The left and right partitions are recursively sorted.

---

# Comparison of Algorithmic Paradigms

The studied algorithms belong to two broad groups.

```text
Elementary Iterative Algorithms
```

and:

```text
Divide-and-Conquer Algorithms
```

---

## Elementary Iterative Algorithms

This group includes:

- Selection Sort;
- Bubble Sort;
- Insertion Sort.

These algorithms generally process the collection through nested loops.

Their typical average and worst-case complexity is:

```text
O(n²)
```

Their advantages include:

- simple control flow;
- limited auxiliary storage;
- educational clarity;
- effectiveness for small inputs in some cases.

---

## Divide-and-Conquer Algorithms

This group includes:

- Merge Sort;
- Quick Sort.

These algorithms decompose a large sorting problem into smaller sorting problems.

Their typical performance is:

```text
O(n log n)
```

They scale more effectively for large inputs.

However, they introduce:

- recursion;
- more complex invariants;
- additional memory or stack requirements;
- more complex implementation decisions.

---

# Fundamental Complexity Comparison

| Algorithm | Best Case | Average Case | Worst Case |
|---|---:|---:|---:|
| Selection Sort | `O(n²)` | `O(n²)` | `O(n²)` |
| Bubble Sort — optimised | `O(n)` | `O(n²)` | `O(n²)` |
| Insertion Sort | `O(n)` | `O(n²)` | `O(n²)` |
| Merge Sort | `O(n log n)` | `O(n log n)` | `O(n log n)` |
| Quick Sort | `O(n log n)` | `O(n log n)` | `O(n²)` |

This table shows that algorithms with similar purposes can have significantly different scalability.

---

# Quadratic versus Linearithmic Growth

The primary theoretical distinction is between:

```text
O(n²)
```

and:

```text
O(n log n)
```

For small values of `n`, the practical difference may be limited.

For large values of `n`, the difference becomes substantial.

Consider the approximate growth:

| `n` | `n²` | `n log₂ n` |
|---:|---:|---:|
| `10` | `100` | `≈ 33` |
| `100` | `10,000` | `≈ 664` |
| `1,000` | `1,000,000` | `≈ 9,966` |
| `10,000` | `100,000,000` | `≈ 132,877` |
| `100,000` | `10,000,000,000` | `≈ 1,660,964` |

The exact number of operations depends on implementation details.

However, the growth trend is clear:

```text
n² grows much faster than n log n
```

---

# Best-Case Comparison

Best-case complexity describes the most favourable input arrangement for an algorithm.

---

## Selection Sort Best Case

Selection Sort still scans the complete unsorted region during every iteration.

Even if the input is already sorted:

```text
[1][2][3][4][5]
```

the algorithm continues searching for the minimum.

Therefore:

```text
Best Case = O(n²)
```

Selection Sort does not substantially benefit from existing order.

---

## Bubble Sort Best Case

An optimised Bubble Sort uses a flag to detect whether any exchange occurred.

For already sorted input:

```text
[1][2][3][4][5]
```

the first pass performs comparisons but no exchanges.

The algorithm then terminates.

Therefore:

```text
Best Case = O(n)
```

This result depends on the early-termination optimisation.

---

## Insertion Sort Best Case

For already sorted input, every new element is already in the correct position.

Example:

```text
[1][2][3][4][5]
```

Each iteration requires only a small number of comparisons.

No substantial shifting is required.

Therefore:

```text
Best Case = O(n)
```

---

## Merge Sort Best Case

Standard Merge Sort divides and merges the input regardless of whether it is already ordered.

Therefore:

```text
Best Case = O(n log n)
```

Some implementations may skip individual merge operations when adjacent regions are already ordered.

However, the standard asymptotic classification remains:

```text
O(n log n)
```

---

## Quick Sort Best Case

Quick Sort achieves its best case when pivots repeatedly produce balanced partitions.

```text
n
   / \
 n/2 n/2
```

Therefore:

```text
Best Case = O(n log n)
```

The best case depends on partition balance rather than existing order alone.

---

# Average-Case Comparison

Average-case complexity estimates expected behaviour under assumptions about the input distribution.

| Algorithm | Average Case |
|---|---:|
| Selection Sort | `O(n²)` |
| Bubble Sort | `O(n²)` |
| Insertion Sort | `O(n²)` |
| Merge Sort | `O(n log n)` |
| Quick Sort | `O(n log n)` |

For large, randomly ordered arrays, Merge Sort and Quick Sort usually scale more effectively than elementary quadratic algorithms.

Average-case analysis should be interpreted carefully because actual performance depends on:

- distribution of values;
- duplicate frequency;
- input order;
- pivot policy;
- implementation details.

---

# Worst-Case Comparison

Worst-case complexity describes the maximum asymptotic cost permitted by the algorithm.

---

## Selection Sort Worst Case

Selection Sort performs nearly the same comparisons for every input arrangement.

Therefore:

```text
Worst Case = O(n²)
```

Its cost is predictable but inefficient.

---

## Bubble Sort Worst Case

Reverse-sorted input causes many adjacent inversions.

Example:

```text
[5][4][3][2][1]
```

Many comparisons and exchanges are required.

Therefore:

```text
Worst Case = O(n²)
```

---

## Insertion Sort Worst Case

Reverse-sorted input requires every new element to move across the complete sorted prefix.

Therefore:

```text
Worst Case = O(n²)
```

---

## Merge Sort Worst Case

Merge Sort preserves its balanced decomposition independently of initial order.

Therefore:

```text
Worst Case = O(n log n)
```

This predictable bound is one of its principal advantages.

---

## Quick Sort Worst Case

Quick Sort reaches its worst case when every pivot creates highly unbalanced partitions.

Example:

```text
0 elements
        +
pivot
        +
n - 1 elements
```

Repeated recursively, this produces:

```text
Worst Case = O(n²)
```

The worst case is strongly influenced by pivot selection and input structure.

---

# Complexity Predictability

The algorithms differ in how sensitive their cost is to input arrangement.

| Algorithm | Sensitivity to Input Order |
|---|---|
| Selection Sort | Low |
| Bubble Sort | High with early termination |
| Insertion Sort | High |
| Merge Sort | Low |
| Quick Sort | High |

Selection Sort and Merge Sort have relatively predictable comparison structures.

Insertion Sort and Bubble Sort may improve significantly on nearly sorted inputs.

Quick Sort may either perform efficiently or degrade severely depending on partition quality.

---

# Auxiliary-Space Comparison

Auxiliary space refers to memory required beyond the input collection.

| Algorithm | Auxiliary Space |
|---|---:|
| Selection Sort | `O(1)` |
| Bubble Sort | `O(1)` |
| Insertion Sort | `O(1)` |
| Merge Sort | `O(n)` for standard arrays |
| Quick Sort | `O(log n)` average stack, `O(n)` worst stack |

---

# Constant-Space Algorithms

Selection Sort, Bubble Sort and Insertion Sort use a fixed number of variables.

Typical local values include:

```text
index
temporary
current value
boundary
```

Their auxiliary-space complexity is:

```text
O(1)
```

They modify the original array directly.

---

# Merge Sort Memory Requirement

Standard array-based Merge Sort requires temporary storage during merging.

For input size `n`:

```text
Auxiliary Array = O(n)
```

The recursion stack additionally requires:

```text
O(log n)
```

Therefore, the dominant auxiliary-space cost is:

```text
O(n)
```

---

# Quick Sort Memory Requirement

Quick Sort normally partitions the original array without an auxiliary array.

However, recursive calls consume stack space.

Balanced partitions produce:

```text
O(log n)
```

stack depth.

Degenerate partitions may produce:

```text
O(n)
```

stack depth.

Thus, Quick Sort's space usage depends on partition balance.

---

# In-Place Comparison

| Algorithm | Commonly In Place |
|---|---|
| Selection Sort | Yes |
| Bubble Sort | Yes |
| Insertion Sort | Yes |
| Merge Sort | No for standard array implementation |
| Quick Sort | Yes with respect to array storage |

The phrase **in place** should be used carefully.

Quick Sort modifies the original array, but recursive stack memory still exists.

Merge Sort may be implemented in place in specialised forms, but standard implementations use auxiliary storage.

---

# Stability Comparison

A stable sorting algorithm preserves the original relative order of elements with equal keys.

Consider:

```text
[A₁, priority 2]
[B,  priority 1]
[A₂, priority 2]
```

A stable result is:

```text
[B,  priority 1]
[A₁, priority 2]
[A₂, priority 2]
```

`A₁` remains before `A₂`.

---

# Stability Summary

| Algorithm | Stable in Standard Form |
|---|---|
| Selection Sort | No |
| Bubble Sort | Yes |
| Insertion Sort | Yes |
| Merge Sort | Yes |
| Quick Sort | No |

---

# Why Selection Sort Is Unstable

Selection Sort may exchange a selected minimum with an element located much earlier in the array.

A distant exchange may reverse the order of equivalent elements.

Example:

```text
[4₁][4₂][2]
```

Selecting `2` and exchanging it with `4₁` produces:

```text
[2][4₂][4₁]
```

The relative order of the two values with key `4` changed.

---

# Why Bubble Sort Is Stable

Bubble Sort compares adjacent elements.

When the exchange condition is:

```text
left > right
```

equal elements are not exchanged.

Therefore, their original relative order is preserved.

Using:

```text
left >= right
```

would unnecessarily exchange equal elements and break stability.

---

# Why Insertion Sort Is Stable

Insertion Sort shifts elements only when they are strictly greater than the current value.

Equivalent elements remain before the newly inserted element.

Therefore, original order among equal keys is preserved.

---

# Why Merge Sort Is Stable

During merging, stability is preserved when the left element is selected first in case of equality.

```text
if leftValue ≤ rightValue
    take leftValue
```

Selecting the right value first when keys are equal may break stability.

---

# Why Quick Sort Is Unstable

Quick Sort performs exchanges across potentially distant positions during partitioning.

Equivalent elements may cross each other.

Standard in-place partitioning therefore does not guarantee stable ordering.

---

# Stability as an Engineering Requirement

Stability matters when sorting is performed successively by multiple keys.

Consider records initially ordered by:

```text
employee name
```

They are then sorted by:

```text
department
```

A stable second sort preserves name order within each department.

Unstable sorting may destroy that secondary ordering.

Stability is therefore important for:

- reports;
- user interfaces;
- multi-key ordering;
- transaction lists;
- audit records;
- deterministic output.

---

# Adaptability Comparison

An adaptive sorting algorithm benefits from existing order in the input.

| Algorithm | Adaptive |
|---|---|
| Selection Sort | No |
| Bubble Sort — optimised | Yes |
| Insertion Sort | Yes |
| Merge Sort — standard | No |
| Quick Sort — standard | Generally no |

---

# Selection Sort Adaptability

Selection Sort performs the same sequence of minimum searches regardless of input order.

Therefore, it is non-adaptive.

---

# Bubble Sort Adaptability

Optimised Bubble Sort can terminate when a complete pass performs no exchanges.

Therefore, it recognises fully sorted input.

It may also complete in fewer passes for some nearly sorted arrangements.

---

# Insertion Sort Adaptability

Insertion Sort's cost depends closely on the number of inversions.

A nearly sorted array contains relatively few inversions.

Therefore, fewer shifts are required.

This makes Insertion Sort particularly effective for:

- small arrays;
- nearly sorted data;
- small partitions in hybrid algorithms.

---

# Merge Sort Adaptability

Standard Merge Sort performs recursive division and merging regardless of existing order.

It is generally classified as non-adaptive.

An optimisation may detect:

```text
maximum of left region ≤ minimum of right region
```

and skip a merge.

This may reduce practical work but does not fundamentally change the standard classification.

---

# Quick Sort Adaptability

Quick Sort does not inherently benefit from sorted input.

With an unsuitable deterministic pivot strategy, sorted input may cause the worst case.

Thus:

```text
Existing Order
```

may improve, have no effect or harm performance depending on pivot selection.

---

# Recursive versus Iterative Structure

| Algorithm | Standard Structure |
|---|---|
| Selection Sort | Iterative |
| Bubble Sort | Iterative |
| Insertion Sort | Iterative |
| Merge Sort | Recursive or iterative |
| Quick Sort | Recursive or iterative |

---

# Iterative Algorithms

Selection Sort, Bubble Sort and Insertion Sort are naturally expressed using loops.

Typical structure:

```text
Outer Loop
        ↓
Inner Processing Loop
```

Advantages include:

- simple control flow;
- no recursion-stack growth;
- straightforward debugging;
- predictable memory use.

---

# Recursive Algorithms

Merge Sort and Quick Sort naturally express divide-and-conquer decomposition.

Advantages include:

- direct correspondence with mathematical recurrence;
- clear subproblem structure;
- modular correctness reasoning.

Risks include:

- stack overhead;
- boundary errors;
- excessive recursion depth;
- stack overflow in degenerate Quick Sort cases.

---

# Merge Sort Recursion

Merge Sort's recursion depth is predictably:

```text
O(log n)
```

because the input is divided approximately in half at every level.

---

# Quick Sort Recursion

Quick Sort's recursion depth depends on partition balance.

```text
Balanced Partitions

O(log n)
```

```text
Degenerate Partitions

O(n)
```

This makes Quick Sort less predictable than Merge Sort.

---

# Iterative Merge Sort

Bottom-up Merge Sort avoids recursive calls.

It merges runs of increasing sizes:

```text
1
2
4
8
16
...
```

This preserves:

```text
O(n log n)
```

time complexity while replacing recursive control flow with iteration.

---

# Iterative Quick Sort

Quick Sort may be implemented with an explicit stack of index ranges.

Conceptually:

```text
Push Complete Range
        ↓
Pop Range
        ↓
Partition
        ↓
Push Remaining Subranges
```

This avoids direct recursion but does not eliminate the need to manage pending subproblems.

---

# Comparison of Data Movement

Sorting cost includes not only comparisons but also element movement.

Different algorithms move data in different ways.

| Algorithm | Principal Movement |
|---|---|
| Selection Sort | Distant exchanges |
| Bubble Sort | Adjacent exchanges |
| Insertion Sort | Shifts and insertion |
| Merge Sort | Copying during merge |
| Quick Sort | Partition exchanges |

---

# Selection Sort Data Movement

Selection Sort performs relatively few exchanges.

At most, it performs approximately:

```text
n - 1 exchanges
```

However, each exchange may move an element over a long distance.

This makes Selection Sort relevant when:

```text
comparison cost is low
```

but:

```text
write operations are expensive
```

Its comparison count remains quadratic.

---

# Bubble Sort Data Movement

Bubble Sort may perform one exchange for every inversion.

In reverse-sorted input, the number of inversions is:

```text
n(n - 1) / 2
```

Therefore, Bubble Sort may perform:

```text
O(n²)
```

exchanges.

Because every exchange usually requires three assignments, data movement can be substantial.

---

# Insertion Sort Data Movement

Insertion Sort shifts larger elements to create an insertion position.

Example:

```text
[1][3][5][7] | [4]
```

Shift:

```text
7 → right
5 → right
```

Insert:

```text
4
```

Result:

```text
[1][3][4][5][7]
```

Shifting is often more efficient than repeated exchange because it reduces temporary assignments.

---

# Merge Sort Data Movement

Merge Sort copies elements into temporary storage and then back into the target region.

Each merge level processes all elements.

The resulting data movement is:

```text
O(n log n)
```

across the complete algorithm.

Its access pattern is mostly sequential, which can be favourable for memory locality.

---

# Quick Sort Data Movement

Quick Sort rearranges elements through partition exchanges.

The number of exchanges depends on:

- partition scheme;
- pivot choice;
- input distribution;
- duplicate frequency.

Lomuto partitioning may perform more exchanges than Hoare partitioning.

Quick Sort avoids full-array copying, but its exchange count is less predictable than Selection Sort's.

---

# Comparisons versus Data Movement

Two algorithms with similar comparison complexity may differ in assignment cost.

Example:

```text
Selection Sort

Many Comparisons
Few Exchanges
```

```text
Bubble Sort

Many Comparisons
Many Exchanges
```

Similarly:

```text
Merge Sort

Predictable Comparisons
Substantial Copying
```

```text
Quick Sort

Pivot Comparisons
In-Place Exchanges
```

Algorithm evaluation should therefore consider both:

```text
Comparison Cost
```

and:

```text
Movement Cost
```

---

# Comparison Count Summary

| Algorithm | Typical Comparison Behaviour |
|---|---|
| Selection Sort | Approximately `n(n - 1) / 2` in all cases |
| Bubble Sort | Up to `n(n - 1) / 2`; fewer with early termination |
| Insertion Sort | Near linear when ordered; quadratic when reversed |
| Merge Sort | `O(n log n)` predictably |
| Quick Sort | `O(n log n)` average; `O(n²)` worst |

---

# Movement Summary

| Algorithm | Exchanges, Shifts or Copies |
|---|---|
| Selection Sort | Few exchanges |
| Bubble Sort | Potentially many adjacent exchanges |
| Insertion Sort | Shifts proportional to inversions |
| Merge Sort | Linear copying per recursion level |
| Quick Sort | Partition-dependent exchanges |

---

# Part I Summary

The algorithms studied in this module differ in strategy, complexity and structural behaviour.

```text
Selection Sort
```

provides predictable quadratic comparison cost and relatively few exchanges.

```text
Bubble Sort
```

uses adjacent exchanges and can detect already sorted input, but performs poorly on large disordered collections.

```text
Insertion Sort
```

is adaptive, stable and effective for small or nearly sorted inputs.

```text
Merge Sort
```

provides stable and predictable `O(n log n)` performance at the cost of auxiliary memory.

```text
Quick Sort
```

provides strong average performance and in-place partitioning, but its worst case and recursion depth depend on partition quality.

The most important conclusions from this first comparison are:

- no sorting algorithm is universally optimal;
- complexity must be analysed together with memory usage;
- stability and adaptability are independent properties;
- data movement may be as important as comparison count;
- input order affects some algorithms much more than others;
- divide-and-conquer algorithms generally scale better than quadratic algorithms;
- engineering constraints determine which trade-offs are acceptable.

---

# Continuation

Part II will extend the comparison through:

```text
Behaviour on Different Input Distributions
```

```text
Memory Locality and Cache Behaviour
```

```text
Small versus Large Collections
```

```text
Duplicate-Heavy Input
```

```text
Internal and External Sorting
```

```text
Algorithm-Selection Decision Framework
```

```text
Engineering and Backend Perspectives
```
# Part II — Input Behaviour, Memory Characteristics and Algorithm Selection

# Behaviour on Different Input Distributions

Sorting algorithms do not react equally to every input arrangement.

The same collection size may produce different execution costs depending on whether the data is:

- already sorted;
- reverse sorted;
- nearly sorted;
- randomly ordered;
- duplicate-heavy;
- composed of a small number of distinct values.

Therefore, input size alone does not completely describe the problem.

A more complete model is:

```text
Sorting Cost
        =
Input Size
        +
Input Distribution
        +
Algorithm Strategy
        +
Implementation Decisions
```

---

# Already Sorted Input

Consider:

```text
[1][2][3][4][5][6]
```

This input contains no inversions.

An inversion is a pair of indices:

```text
i < j
```

such that:

```text
A[i] > A[j]
```

For an already sorted collection:

```text
Number of Inversions = 0
```

---

## Selection Sort on Sorted Input

Selection Sort continues scanning the complete unsorted region to verify the minimum during every iteration.

Therefore:

```text
Time Complexity = O(n²)
```

Existing order does not significantly reduce its comparison count.

---

## Bubble Sort on Sorted Input

An optimised Bubble Sort performs one complete comparison pass.

Because no exchanges occur, the early-termination condition stops the algorithm.

Therefore:

```text
Time Complexity = O(n)
```

Without this optimisation, Bubble Sort remains quadratic.

---

## Insertion Sort on Sorted Input

Every element is already greater than or equal to the previous element.

No shifts are required.

Therefore:

```text
Time Complexity = O(n)
```

This is one of the strongest practical cases for Insertion Sort.

---

## Merge Sort on Sorted Input

Standard Merge Sort continues dividing and merging the array.

Its general complexity remains:

```text
O(n log n)
```

A merge-skip optimisation may reduce copying when two adjacent regions are already ordered.

For example:

```text
maximum(left region) ≤ minimum(right region)
```

If this condition holds, the merge can be skipped.

---

## Quick Sort on Sorted Input

Quick Sort behaviour depends on the pivot strategy.

With the final element as pivot:

```text
[1][2][3][4][5]
```

the pivot is always the greatest value.

This produces partitions of sizes:

```text
n - 1
```

and:

```text
0
```

The resulting complexity is:

```text
O(n²)
```

With random or median-based pivot selection, performance may remain near:

```text
O(n log n)
```

---

# Sorted Input Comparison

| Algorithm | Behaviour on Sorted Input |
|---|---|
| Selection Sort | Quadratic |
| Bubble Sort — optimised | Linear |
| Insertion Sort | Linear |
| Merge Sort | Linearithmic |
| Quick Sort | Depends strongly on pivot selection |

Sorted input demonstrates that existing order may:

- substantially help an adaptive algorithm;
- have little effect on a non-adaptive algorithm;
- harm an algorithm with an unsuitable partition policy.

---

# Reverse-Sorted Input

Consider:

```text
[6][5][4][3][2][1]
```

This arrangement contains the maximum possible number of inversions:

```text
n(n - 1) / 2
```

It is a significant test case for algorithms based on local movement.

---

## Selection Sort on Reverse-Sorted Input

Selection Sort still performs:

```text
O(n²)
```

comparisons.

The number of exchanges remains at most:

```text
n - 1
```

Its behaviour is not greatly affected by inversion count.

---

## Bubble Sort on Reverse-Sorted Input

Every adjacent comparison initially identifies an inversion.

Elements must repeatedly move through adjacent exchanges.

Therefore:

```text
Comparisons = O(n²)
```

and:

```text
Exchanges = O(n²)
```

This is a costly case because of extensive data movement.

---

## Insertion Sort on Reverse-Sorted Input

Each new element must move across the entire sorted prefix.

Example:

```text
[6][5][4][3][2][1]
```

When inserting `1`, all preceding elements must shift.

Therefore:

```text
Time Complexity = O(n²)
```

and the number of shifts is proportional to the number of inversions.

---

## Merge Sort on Reverse-Sorted Input

Merge Sort preserves:

```text
O(n log n)
```

time complexity.

Its decomposition does not depend on the original ordering.

---

## Quick Sort on Reverse-Sorted Input

With a first-element or last-element pivot, reverse-sorted input may repeatedly produce unbalanced partitions.

Therefore:

```text
Worst Case = O(n²)
```

A more robust pivot strategy reduces this risk.

---

# Reverse-Sorted Input Comparison

| Algorithm | Behaviour on Reverse-Sorted Input |
|---|---|
| Selection Sort | Quadratic, few exchanges |
| Bubble Sort | Quadratic, many exchanges |
| Insertion Sort | Quadratic, many shifts |
| Merge Sort | Linearithmic |
| Quick Sort | May become quadratic |

---

# Nearly Sorted Input

A nearly sorted collection contains relatively few inversions.

Example:

```text
[1][2][4][3][5][6]
```

Only the pair:

```text
4, 3
```

is inverted.

---

## Selection Sort on Nearly Sorted Input

Selection Sort does not exploit the low inversion count.

It continues searching every remaining region.

Therefore:

```text
O(n²)
```

---

## Bubble Sort on Nearly Sorted Input

Bubble Sort may perform well when misplaced values are close to their correct positions.

However, a value located far from its destination may still require several passes.

Example:

```text
[6][1][2][3][4][5]
```

The value `6` moves rapidly to the right during one pass.

In contrast:

```text
[2][3][4][5][6][1]
```

the value `1` moves left only one position per pass in the standard algorithm.

Thus, Bubble Sort's response to nearly sorted input depends on the direction and location of disorder.

---

## Insertion Sort on Nearly Sorted Input

Insertion Sort is particularly effective when the inversion count is low.

Its running time may be expressed more precisely as:

```text
O(n + I)
```

where:

```text
I = number of inversions
```

When:

```text
I ≪ n²
```

Insertion Sort may approach linear performance.

---

## Merge Sort on Nearly Sorted Input

Standard Merge Sort does not strongly exploit partial order.

It still performs:

```text
O(n log n)
```

work.

Optimised merge checks may improve constant factors.

---

## Quick Sort on Nearly Sorted Input

Quick Sort does not inherently exploit near order.

Its behaviour remains dependent on pivot quality.

A poor deterministic pivot policy may still produce unbalanced partitions.

---

# Nearly Sorted Input Comparison

| Algorithm | Suitability for Nearly Sorted Data |
|---|---|
| Selection Sort | Poor |
| Bubble Sort — optimised | Moderate |
| Insertion Sort | Strong |
| Merge Sort | Predictable but not adaptive |
| Quick Sort | Pivot-dependent |

Insertion Sort is usually the strongest elementary choice for nearly sorted arrays.

---

# Randomly Ordered Input

Randomly ordered input is commonly used to evaluate average behaviour.

Example:

```text
[8][3][6][1][9][4][2][7][5]
```

For large random collections:

- Selection Sort remains quadratic;
- Bubble Sort remains quadratic;
- Insertion Sort remains quadratic on average;
- Merge Sort remains linearithmic;
- Quick Sort is linearithmic on average.

---

# Random Input Comparison

| Algorithm | Typical Behaviour |
|---|---|
| Selection Sort | `O(n²)` |
| Bubble Sort | `O(n²)` |
| Insertion Sort | `O(n²)` |
| Merge Sort | `O(n log n)` |
| Quick Sort | `O(n log n)` average |

For sufficiently large random arrays, Merge Sort and Quick Sort normally provide substantially better scalability.

---

# Duplicate-Heavy Input

Consider:

```text
[4][2][4][4][1][2][4][3]
```

This collection contains many repeated keys.

Duplicate values affect algorithms in different ways.

---

## Selection Sort with Duplicates

Selection Sort remains quadratic.

Its distant exchanges may reorder equivalent values.

Therefore, standard Selection Sort is unstable.

---

## Bubble Sort with Duplicates

Bubble Sort handles duplicates naturally when equal values are not exchanged.

Condition:

```text
leftValue > rightValue
```

preserves stability.

Condition:

```text
leftValue >= rightValue
```

may break stability and cause unnecessary movement.

---

## Insertion Sort with Duplicates

Insertion Sort remains stable when elements are shifted only if:

```text
previousValue > currentValue
```

Equal values are not moved across each other.

---

## Merge Sort with Duplicates

Merge Sort handles duplicate-heavy input effectively.

Stability is preserved when the left element is selected first during equality:

```text
leftValue ≤ rightValue
```

---

## Quick Sort with Duplicates

Two-way partitioning may perform poorly when many elements equal the pivot.

Consider:

```text
[5][5][5][5][5]
```

With Lomuto partitioning and the condition:

```text
value ≤ pivot
```

the pivot may repeatedly move to one extreme.

This produces subproblems of size:

```text
n - 1
```

and:

```text
0
```

Three-way partitioning is more appropriate:

```text
Values < Pivot
Values = Pivot
Values > Pivot
```

The equal region does not require further sorting.

---

# Duplicate-Heavy Input Comparison

| Algorithm | Behaviour with Many Duplicates |
|---|---|
| Selection Sort | Correct but unstable |
| Bubble Sort | Stable with strict exchange condition |
| Insertion Sort | Stable and generally effective for small input |
| Merge Sort | Stable and predictable |
| Quick Sort | Requires careful partition strategy |

---

# Small Number of Distinct Keys

Some datasets contain many elements but only a few possible keys.

Example:

```text
[2][1][3][2][1][2][3][1][2]
```

A three-way Quick Sort may perform effectively because large equal-key regions are excluded from recursion.

However, non-comparison algorithms may be more suitable when keys are small bounded integers.

Examples include:

- Counting Sort;
- Radix Sort;
- Bucket Sort.

These algorithms are outside the current comparison because the studied algorithms are comparison-based.

---

# Small Collections

For small values of `n`, asymptotic superiority does not always determine practical speed.

A theoretically faster algorithm may have additional overhead from:

- recursion;
- temporary arrays;
- pivot selection;
- partition setup;
- method calls.

Insertion Sort often performs well on small arrays because it has:

- simple loops;
- low constant overhead;
- no recursion;
- no auxiliary array;
- favourable locality.

---

# Small-Input Comparison

| Algorithm | Small-Input Suitability |
|---|---|
| Selection Sort | Acceptable when writes should be limited |
| Bubble Sort | Mainly educational |
| Insertion Sort | Strong |
| Merge Sort | May have unnecessary overhead |
| Quick Sort | May have partition and recursion overhead |

This is why production sorting implementations often switch to Insertion Sort for small partitions.

---

# Large Collections

As the input size grows, asymptotic complexity becomes increasingly important.

For large general-purpose collections:

```text
O(n log n)
```

algorithms are usually preferred over:

```text
O(n²)
```

algorithms.

Therefore:

- Merge Sort is suitable when stability and predictability matter;
- Quick Sort is suitable when average performance and in-place partitioning matter;
- elementary quadratic algorithms are normally restricted to small or specialised cases.

---

# Collection Size Is Not the Only Criterion

A large nearly sorted collection may still make an adaptive algorithm competitive.

A small collection of large objects may make data movement more significant than comparison count.

A large collection stored externally may require sequential-access behaviour.

Therefore:

```text
Input Size
```

must be considered together with:

```text
Input Structure
Object Size
Storage Medium
Ordering Requirements
Memory Limits
```

---

# Memory-Access Behaviour

Practical execution time depends partly on how an algorithm accesses memory.

Modern processors use memory hierarchies including:

```text
Registers
    ↓
CPU Cache
    ↓
Main Memory
    ↓
Secondary Storage
```

Accessing nearby memory locations is generally more efficient than accessing unrelated locations.

This property is called locality.

---

# Spatial Locality

Spatial locality refers to accessing memory locations close to recently accessed locations.

Arrays provide strong spatial locality because their elements occupy contiguous memory locations conceptually.

Algorithms that scan arrays sequentially often interact favourably with processor caches.

---

# Temporal Locality

Temporal locality refers to repeatedly accessing the same data within a short period.

Insertion Sort repeatedly examines the sorted prefix.

Quick Sort repeatedly partitions subarrays.

Merge Sort repeatedly reads and writes regions during merging.

The practical effect depends on subproblem size and memory hierarchy.

---

# Selection Sort Locality

Selection Sort repeatedly scans the unsorted suffix.

Its access pattern is largely sequential during minimum searches.

However, every pass rereads much of the remaining array.

Therefore, it has reasonable local access but performs too many total comparisons.

---

# Bubble Sort Locality

Bubble Sort accesses adjacent elements sequentially.

Its locality is strong.

However, repeated passes over the same data and a high number of exchanges produce substantial work.

Good locality does not compensate for quadratic growth on large inputs.

---

# Insertion Sort Locality

Insertion Sort usually works within a compact prefix of the array.

It accesses nearby elements and shifts contiguous positions.

This produces favourable locality, particularly for small collections.

Its efficient cache behaviour contributes to its use in hybrid algorithms.

---

# Merge Sort Locality

Merge Sort reads sorted regions sequentially during merging.

This access pattern is favourable for:

- arrays;
- sequential memory;
- external files;
- large datasets stored in blocks.

However, copying between the input and auxiliary array increases memory traffic.

---

# Quick Sort Locality

Quick Sort partitions subarrays in place.

As recursion progresses, it works on increasingly smaller contiguous regions.

This often produces strong cache behaviour.

For array sorting, this is one reason Quick Sort may outperform Merge Sort in practice despite weaker worst-case guarantees.

---

# Locality Comparison

| Algorithm | General Locality Characteristics |
|---|---|
| Selection Sort | Sequential scans, repeated rereading |
| Bubble Sort | Strong adjacent access, excessive passes |
| Insertion Sort | Strong locality on compact regions |
| Merge Sort | Sequential reads and writes |
| Quick Sort | Strong in-place subarray locality |

---

# Memory Traffic

Memory traffic refers to the amount of data transferred between memory locations.

An algorithm may perform:

- reads;
- writes;
- exchanges;
- shifts;
- copying.

The cost becomes particularly relevant when elements are large objects or when the dataset exceeds cache capacity.

---

# Selection Sort and Write Minimisation

Selection Sort performs many comparisons but relatively few exchanges.

At most:

```text
n - 1
```

exchanges are required.

This characteristic may be relevant in environments where writes are substantially more expensive than reads.

However, the algorithm remains quadratic in comparisons.

---

# Bubble Sort and Write Amplification

Every exchange typically requires three assignments:

```text
temporary = A[i]
A[i] = A[i + 1]
A[i + 1] = temporary
```

Because Bubble Sort may perform:

```text
O(n²)
```

exchanges, its write cost can be high.

---

# Insertion Sort and Shifting

Insertion Sort generally stores the current value once and shifts larger elements.

Conceptually:

```text
Store Current Value
        ↓
Shift Larger Elements
        ↓
Write Current Value Once
```

This is usually more efficient than repeatedly exchanging the current element with every preceding element.

---

# Merge Sort and Copying

Standard Merge Sort copies every element during merging.

At each recursion level, the complete array participates in merge processing.

Thus, even with:

```text
O(n log n)
```

time complexity, memory traffic may be substantial.

---

# Quick Sort and Exchanges

Quick Sort avoids a full auxiliary array.

However, partitioning may perform many exchanges.

The number depends on the partition scheme.

Hoare partitioning often performs fewer exchanges than Lomuto partitioning.

---

# Primitive Values versus Object References

The cost of moving an element depends on its representation.

For primitive arrays:

```text
int[]
```

an exchange copies primitive values.

For object arrays:

```text
Transaction[]
```

an exchange usually moves references rather than copying complete objects.

Therefore, the conceptual size of an object does not necessarily equal the cost of rearranging its position in an array.

However, comparator calls may access fields inside those objects, affecting cache behaviour and execution cost.

---

# Linked Structures

The studied algorithms are commonly presented for arrays, but data structure matters.

---

## Selection Sort on Linked Lists

Selection Sort can traverse a linked list to find the minimum.

However, indexed access is unavailable, and repeated traversal remains costly.

It is generally not a preferred linked-list sorting strategy.

---

## Bubble Sort on Linked Lists

Bubble Sort may exchange node values or links.

Its quadratic complexity remains a major limitation.

---

## Insertion Sort on Linked Lists

Insertion Sort may be effective for small linked lists because insertion can be performed by changing links rather than shifting array elements.

However, locating the insertion point still requires traversal.

---

## Merge Sort on Linked Lists

Merge Sort is particularly suitable for linked lists.

Advantages include:

- efficient splitting with pointer techniques;
- merging without an auxiliary array of element values;
- sequential access;
- stable ordering;
- guaranteed `O(n log n)` time.

---

## Quick Sort on Linked Lists

Quick Sort is less naturally suited to linked lists because partitioning benefits from indexed or bidirectional access.

It can be implemented, but Merge Sort is often a more natural choice.

---

# Data Structure Comparison

| Data Structure | Commonly Suitable Strategy |
|---|---|
| Small array | Insertion Sort |
| Large array | Quick Sort or Merge Sort |
| Linked list | Merge Sort |
| External sequential data | Merge Sort |
| Duplicate-heavy array | Three-way Quick Sort or stable Merge Sort |

---

# Internal Sorting

Internal sorting assumes that all data fits in main memory.

The algorithms studied so far are primarily internal sorting algorithms.

Typical use cases include:

- arrays;
- lists;
- in-memory report data;
- application collections;
- cached records.

Selection Sort, Bubble Sort, Insertion Sort and Quick Sort are mainly associated with internal sorting.

Merge Sort can be used both internally and externally.

---

# External Sorting

External sorting is required when the dataset does not fit into main memory.

Example:

```text
Dataset Size > Available Memory
```

The data must be processed in blocks stored on disk or another external medium.

---

# Why Merge Sort Is Suitable for External Sorting

Merge Sort divides the problem into manageable runs.

Conceptually:

```text
Read Memory-Sized Block
        ↓
Sort Block
        ↓
Write Sorted Run to Disk
        ↓
Merge Sorted Runs Sequentially
```

Its merge phase uses mostly sequential access.

Sequential disk access is substantially more efficient than irregular random access.

---

# Why Quick Sort Is Less Suitable Externally

Quick Sort's partitioning pattern may require less predictable data access and repeated reorganisation of large regions.

Its in-memory advantages do not translate as naturally to external storage.

Therefore, external sorting systems usually favour merge-based strategies.

---

# Internal versus External Comparison

| Property | Internal Sorting | External Sorting |
|---|---|---|
| Dataset | Fits in memory | Exceeds memory |
| Main cost | CPU and memory | Input/output operations |
| Access pattern | Flexible | Sequential preferred |
| Common strategy | Quick Sort, Merge Sort | External Merge Sort |

---

# Stability in Multi-Key Sorting

Suppose employee records contain:

```text
Department
Last Name
Identifier
```

A system may first sort by last name and then by department.

With a stable second sort:

```text
Department
        ↓
Original Last-Name Order Preserved
```

With an unstable second sort, the last-name order may be destroyed.

---

# Alternative Multi-Key Strategy

Instead of relying on successive stable sorts, a comparator can define a complete ordering directly.

Conceptually:

```text
Compare Department
        ↓
If Equal, Compare Last Name
        ↓
If Equal, Compare Identifier
```

Java example:

```java
Comparator<Employee> ordering =
        Comparator.comparing(Employee::department)
                .thenComparing(Employee::lastName)
                .thenComparing(Employee::id);
```

A unique final key produces deterministic output.

---

# Deterministic Ordering

Sorting by a non-unique key may produce ambiguous order among equivalent elements.

Example:

```text
Sort Transactions by Timestamp
```

Several transactions may share the same timestamp.

A deterministic ordering should add a tie-breaker:

```text
Timestamp
        ↓
Transaction Identifier
```

Formally:

```text
Primary Key
        +
Secondary Unique Key
        =
Total Ordering
```

This is especially important for:

- pagination;
- distributed processing;
- report reproducibility;
- testing;
- caching;
- event replay.

---

# Worst-Case Guarantees

Some systems require a predictable upper bound on execution time.

Examples include:

- real-time systems;
- latency-sensitive services;
- security-sensitive processing;
- infrastructure components;
- systems processing untrusted input.

In such contexts, the distinction between:

```text
Expected O(n log n)
```

and:

```text
Guaranteed O(n log n)
```

may be important.

---

# Merge Sort Predictability

Merge Sort guarantees:

```text
O(n log n)
```

for best, average and worst cases.

This provides consistent scalability.

Its cost is additional memory.

---

# Quick Sort Predictability

Basic Quick Sort provides:

```text
O(n log n)
```

average performance but:

```text
O(n²)
```

worst-case performance.

Randomisation reduces the probability of repeated poor partitions but does not eliminate the theoretical worst case.

An introspective strategy can switch algorithms when recursion becomes excessive.

---

# Adversarial Input

A deterministic Quick Sort may be vulnerable when external users can influence input order.

An attacker may construct data that repeatedly produces poor pivot positions.

Possible consequences include:

- excessive CPU consumption;
- deep recursion;
- stack exhaustion;
- increased response latency;
- denial-of-service conditions.

This converts a theoretical worst case into a security and reliability concern.

---

# Parallelisation Potential

Sorting algorithms differ in how naturally they expose independent work.

---

## Selection Sort Parallelisation

Finding the minimum could be divided among multiple workers.

However, every iteration depends on the previous selected boundary.

The quadratic comparison structure limits its usefulness.

---

## Bubble Sort Parallelisation

Some independent adjacent pairs can be processed in phases.

Odd-even transposition sorting is one related model.

However, standard Bubble Sort is not an attractive general-purpose parallel algorithm.

---

## Insertion Sort Parallelisation

Insertion Sort has strong sequential dependencies.

Each insertion depends on the previously constructed sorted prefix.

It is not naturally parallel.

---

## Merge Sort Parallelisation

The two recursive halves can be sorted independently.

```text
Sort Left Half  ─┐
                 ├─► Merge
Sort Right Half ─┘
```

The merge operation may also be parallelised in advanced implementations.

Merge Sort therefore has strong parallel potential.

---

## Quick Sort Parallelisation

After partitioning, the left and right partitions are independent.

```text
Partition
        ↓
-------------------
↓                 ↓
Sort Left     Sort Right
```

However, unbalanced partitions may produce uneven workloads.

---

# Parallel Suitability Comparison

| Algorithm | Parallel Potential |
|---|---|
| Selection Sort | Low |
| Bubble Sort | Low |
| Insertion Sort | Low |
| Merge Sort | High |
| Quick Sort | High when partitions are balanced |

---

# Load Balance

Parallel performance depends not only on independence but also on workload balance.

Merge Sort naturally creates similarly sized subproblems.

Quick Sort may create:

```text
50% / 50%
```

or:

```text
1% / 99%
```

partitions.

Balanced partitions distribute work more effectively across processors.

---

# Algorithm Selection Framework

No single comparison table can fully determine the correct algorithm.

A structured decision process should examine the problem requirements.

---

# Step 1 — Determine Input Size

```text
Is the collection small?
```

For small collections, Insertion Sort may be appropriate.

For large collections, prefer an algorithm with:

```text
O(n log n)
```

expected or guaranteed scalability.

---

# Step 2 — Determine Existing Order

```text
Is the collection already or nearly sorted?
```

If yes, Insertion Sort may perform efficiently.

An optimised Bubble Sort can detect fully sorted input, but Insertion Sort is generally more practical.

---

# Step 3 — Determine Stability Requirement

```text
Must equivalent elements preserve their original order?
```

If yes, suitable standard choices include:

- Bubble Sort;
- Insertion Sort;
- Merge Sort.

For large inputs, Merge Sort is usually the stronger option among the studied algorithms.

---

# Step 4 — Determine Memory Constraints

```text
Is O(n) auxiliary memory acceptable?
```

If yes, Merge Sort may provide stability and predictable performance.

If no, Quick Sort or an in-place elementary algorithm may be considered.

---

# Step 5 — Determine Worst-Case Requirement

```text
Must the algorithm guarantee O(n log n)?
```

If yes, standard Merge Sort is appropriate among the studied algorithms.

Basic Quick Sort does not provide this guarantee.

---

# Step 6 — Determine Input Distribution

```text
Does the collection contain many duplicate keys?
```

If yes:

- stable Merge Sort handles duplicates predictably;
- three-way Quick Sort may perform efficiently;
- two-way Quick Sort requires caution.

---

# Step 7 — Determine Data Structure

```text
Array or linked structure?
```

For arrays:

- Quick Sort;
- Merge Sort;
- Insertion Sort for small ranges.

For linked lists:

- Merge Sort is usually a natural choice.

---

# Step 8 — Determine Storage Model

```text
Does the complete dataset fit in memory?
```

If no, use an external merge-based strategy.

---

# Simplified Decision Tree

```text
Start
  |
  v
Is the dataset very small?
  |
  ├── Yes ──► Insertion Sort
  |
  └── No
        |
        v
Is stable ordering required?
        |
        ├── Yes
        |     |
        |     v
        | Is O(n) auxiliary memory acceptable?
        |     |
        |     ├── Yes ──► Merge Sort
        |     |
        |     └── No ──► Specialised stable in-place strategy
        |
        └── No
              |
              v
Is guaranteed O(n log n) required?
              |
              ├── Yes ──► Merge Sort or another guaranteed algorithm
              |
              └── No
                    |
                    v
Is average in-memory array performance the priority?
                    |
                    ├── Yes ──► Quick Sort or hybrid strategy
                    |
                    └── No ──► Evaluate domain-specific constraints
```

---

# Selection Matrix

| Requirement | Suitable Algorithm |
|---|---|
| Small collection | Insertion Sort |
| Nearly sorted collection | Insertion Sort |
| Stable large-array sorting | Merge Sort |
| Predictable `O(n log n)` | Merge Sort |
| Low auxiliary array usage | Quick Sort |
| Few write operations | Selection Sort |
| Educational adjacent-exchange demonstration | Bubble Sort |
| Duplicate-heavy data | Merge Sort or Three-Way Quick Sort |
| Linked-list sorting | Merge Sort |
| External sorting | Merge Sort |
| Strong average array performance | Quick Sort |

This matrix is a conceptual guide, not a universal rule.

---

# Selection Sort Decision Profile

Selection Sort may be considered when:

- the input is very small;
- the implementation must be simple;
- the number of writes should remain low;
- quadratic comparisons are acceptable;
- stability is unnecessary.

It should generally not be chosen for large collections.

---

# Bubble Sort Decision Profile

Bubble Sort may be considered when:

- teaching adjacent comparisons and inversions;
- demonstrating algorithmic invariants;
- detecting whether a tiny collection is already sorted;
- implementation simplicity is more important than performance.

It is rarely appropriate for production-scale general sorting.

---

# Insertion Sort Decision Profile

Insertion Sort may be considered when:

- the input is small;
- the input is nearly sorted;
- stability is required;
- memory usage must remain constant;
- it is used as the small-partition phase of a hybrid algorithm.

Its quadratic worst case makes it unsuitable as the primary strategy for large unordered collections.

---

# Merge Sort Decision Profile

Merge Sort may be considered when:

- stable ordering is required;
- predictable `O(n log n)` performance is important;
- additional memory is acceptable;
- linked structures are being sorted;
- external sorting is required;
- parallel decomposition is beneficial.

---

# Quick Sort Decision Profile

Quick Sort may be considered when:

- sorting large arrays in memory;
- stability is unnecessary;
- strong average performance is required;
- auxiliary array allocation should be avoided;
- pivot and partition strategies are robust;
- worst-case risk is acceptable or mitigated.

---

# Hybrid Sorting

Practical sorting implementations often combine algorithms.

A hybrid algorithm chooses different strategies for different subproblem characteristics.

Example:

```text
Large Partition
        ↓
Quick Sort
        ↓
Small Partition
        ↓
Insertion Sort
```

---

# Why Hybrid Algorithms Are Effective

Quick Sort and Merge Sort provide strong asymptotic scalability.

Insertion Sort provides low overhead for small or nearly sorted regions.

Combining them allows each algorithm to operate where it is strongest.

---

# Threshold Selection

A hybrid implementation may switch when:

```text
partition size ≤ threshold
```

The threshold should not be selected solely by intuition.

It may depend on:

- JVM behaviour;
- element type;
- comparator cost;
- hardware;
- memory hierarchy;
- partition implementation.

Benchmarking is required to determine an appropriate value.

---

# Introspective Strategy

An introspective sorting algorithm begins with Quick Sort and monitors recursion depth.

```text
Use Quick Sort
        ↓
Depth Limit Exceeded?
        |
        ├── No  ──► Continue
        |
        └── Yes ──► Switch to Heap Sort
```

This combines:

- Quick Sort's practical efficiency;
- a guaranteed worst-case bound;
- Insertion Sort for small partitions in many implementations.

Heap Sort has not yet been studied in this module, but the hybrid principle is important.

---

# TimSort Perspective

TimSort is a hybrid stable sorting algorithm based primarily on:

- Merge Sort;
- Insertion Sort;
- detection of existing ordered runs.

Conceptually:

```text
Detect Existing Runs
        ↓
Extend Small Runs
        ↓
Merge Runs Strategically
```

Its adaptiveness makes it effective for real-world data that often contains partially ordered regions.

A detailed analysis belongs in the Java sorting framework document.

---

# Engineering Trade-Off Summary

| Trade-Off | Option A | Option B |
|---|---|---|
| Time predictability | Merge Sort | Quick Sort average efficiency |
| Memory | Quick Sort | Merge Sort auxiliary array |
| Stability | Merge Sort | Quick Sort in-place behaviour |
| Small-input overhead | Insertion Sort | Recursive algorithms |
| Comparison count | Linearithmic algorithms | Elementary quadratic algorithms |
| Write minimisation | Selection Sort | Bubble Sort |
| Adaptability | Insertion Sort | Selection Sort |
| Simplicity | Elementary algorithms | Hybrid production algorithms |

---

# Backend Engineering Perspective

Backend systems commonly sort:

- API response data;
- database query results;
- report records;
- events;
- transactions;
- in-memory caches;
- search results;
- scheduled tasks.

However, the application layer is not always the correct place to perform sorting.

---

# Database Sorting

When data is stored in a relational database, ordering is usually expressed through:

```sql
ORDER BY
```

The database management system may use:

- indexes;
- in-memory sorting;
- external merge strategies;
- parallel execution;
- query-planner statistics.

Application code should not normally retrieve a large unordered dataset only to sort it manually.

---

# Why Database Ordering Is Often Preferable

Database ordering may:

- reduce data transferred to the application;
- use indexes;
- combine ordering with filtering;
- support pagination;
- avoid unnecessary application memory usage;
- execute close to the stored data.

Example:

```sql
SELECT id, created_at, amount
FROM transaction
ORDER BY created_at DESC, id DESC;
```

The secondary key ensures deterministic ordering.

---

# Application-Layer Sorting

Application sorting may be appropriate when:

- the dataset is already in memory;
- values are calculated after retrieval;
- the comparator uses application-specific logic;
- data comes from multiple sources;
- the collection is small;
- database sorting is unavailable or inappropriate.

In such cases, standard library sorting should normally be preferred over handwritten algorithms.

---

# Pagination

Pagination requires a deterministic order.

Incorrect conceptual ordering:

```text
ORDER BY created_at
```

when many rows share the same timestamp.

Better ordering:

```text
ORDER BY created_at, id
```

Without a complete ordering, records may:

- appear on multiple pages;
- disappear between pages;
- change position unpredictably;
- produce inconsistent tests.

---

# Distributed Systems

Sorting across distributed nodes introduces additional concerns:

- data partitioning;
- network transfer;
- global ordering;
- merge phases;
- memory limits;
- fault tolerance.

A typical distributed pattern is:

```text
Sort Local Partitions
        ↓
Merge Ordered Streams
```

This resembles Merge Sort at a larger architectural scale.

---

# Streaming Data

An infinite stream cannot be completely sorted because future values are unknown.

Systems instead sort:

- bounded windows;
- event-time intervals;
- finite batches;
- priority queues;
- buffered segments.

Therefore, the definition of the sorting problem changes in streaming systems.

---

# Top-K Problems

Many backend problems do not require fully sorting all values.

Example:

```text
Find the 10 highest transactions
```

Sorting the entire collection may cost:

```text
O(n log n)
```

A heap-based approach may cost:

```text
O(n log k)
```

where:

```text
k ≪ n
```

This illustrates an important engineering principle:

> Before selecting a sorting algorithm, determine whether complete sorting is actually required.

---

# Domain Semantics

The comparator must reflect business meaning.

Example transaction ordering:

```text
Status
        ↓
Due Date
        ↓
Priority
        ↓
Identifier
```

A comparator that is algorithmically valid but semantically incorrect still produces an incorrect business result.

---

# Mutable Comparison Keys

Objects should not change the fields used for comparison while sorting is in progress.

Example:

```text
Sort by Priority
```

If another thread modifies priority during execution, comparison results may become inconsistent.

Possible consequences include:

- non-deterministic ordering;
- violated comparator contracts;
- difficult concurrency defects.

---

# Null Handling

A system must define how `null` values are ordered.

Possible policies include:

```text
Nulls First
```

or:

```text
Nulls Last
```

Java comparators may express these policies explicitly:

```text
Comparator.nullsFirst(comparator)
```

```text
Comparator.nullsLast(comparator)
```

Undefined null behaviour may produce runtime exceptions.

---

# Standard Library Preference

In production code, manually implemented sorting algorithms should normally not replace standard-library operations.

Standard implementations provide:

- extensive testing;
- optimised behaviour;
- edge-case handling;
- maintainability;
- predictable contracts;
- platform-specific improvements.

Handwritten algorithms remain valuable for:

- academic study;
- technical interviews;
- experimentation;
- benchmarking;
- understanding algorithm design.

---

# Testing Perspective

Comparative testing should evaluate properties shared by every sorting algorithm.

---

## Ordered Result

For every valid adjacent index:

```text
A[i] ≤ A[i + 1]
```

---

## Permutation Preservation

The output must contain the same values and frequencies as the input.

---

## Length Preservation

```text
output.length = input.length
```

---

## Idempotence

Sorting an already sorted result should not change it:

```text
sort(sort(A)) = sort(A)
```

---

## Stability

For algorithms documented as stable, equivalent elements must preserve their original relative order.

---

## Boundary Cases

Every algorithm should be tested with:

- empty input;
- one element;
- two elements;
- duplicates;
- negative values;
- sorted input;
- reverse-sorted input;
- all-equal values;
- large input.

---

# Comparative Test Design

The same parameterised test cases may be applied to multiple sorting implementations.

Conceptually:

```text
Sorting Strategy
        +
Shared Input Cases
        =
Comparable Correctness Validation
```

A common interface may support this design:

```text
@FunctionalInterface
public interface IntSorter {

    void sort(int[] values);
}
```

Each implementation can then be supplied to the same test suite.

---

# Differential Testing

A custom algorithm may be compared with:

```text
Arrays.sort()
```

Process:

```text
Create Input
        ↓
Copy Input Twice
        ↓
Sort One Copy with Custom Algorithm
        ↓
Sort Other Copy with Standard Library
        ↓
Compare Results
```

This is especially valuable for randomised tests.

---

# Benchmarking Perspective

Benchmarking should compare algorithms across multiple input distributions.

A meaningful experiment should include:

```text
Small Random Arrays
Large Random Arrays
Sorted Arrays
Reverse-Sorted Arrays
Nearly Sorted Arrays
Duplicate-Heavy Arrays
All-Equal Arrays
```

---

# Benchmark Variables

Relevant variables include:

- input size;
- input distribution;
- pivot strategy;
- partition strategy;
- comparator complexity;
- element type;
- memory allocation;
- recursion depth;
- number of comparisons;
- number of writes.

---

# Fair Benchmarking

Every algorithm must receive equivalent input.

Because sorting mutates arrays, a fresh copy is required before every measured invocation.

Incorrect:

```text
Sort the Same Array Repeatedly
```

Correct:

```text
Original Dataset
        ↓
Copy Dataset
        ↓
Sort Copy
```

---

# Avoiding Misleading Conclusions

A result such as:

```text
Algorithm A was faster than Algorithm B
```

is incomplete without specifying:

- input size;
- input distribution;
- element type;
- implementation;
- runtime environment;
- warm-up procedure;
- measurement method.

Performance conclusions must remain tied to their experimental conditions.

---

# Practical Selection Principles

The following principles summarise the engineering comparison.

## Principle 1

Do not use asymptotic complexity as the only selection criterion.

## Principle 2

For large general-purpose inputs, prefer linearithmic algorithms.

## Principle 3

Use Insertion Sort for small or nearly sorted ranges.

## Principle 4

Use stable sorting when equivalent-element order carries meaning.

## Principle 5

Consider worst-case guarantees for untrusted or latency-sensitive input.

## Principle 6

Consider auxiliary memory and memory traffic.

## Principle 7

Match the algorithm to the data structure and storage medium.

## Principle 8

Prefer established standard-library implementations in production systems.

## Principle 9

Avoid complete sorting when the problem only requires selection, Top-K or grouping.

## Principle 10

Verify theoretical expectations through controlled benchmarking.

---

# Part II Summary

Sorting-algorithm behaviour depends on more than input size.

The input distribution influences:

- inversion count;
- number of shifts;
- number of exchanges;
- partition balance;
- recursion depth;
- practical execution time.

The principal observations are:

```text
Already Sorted or Nearly Sorted
        ↓
Insertion Sort performs strongly
```

```text
Large Stable Sorting
        ↓
Merge Sort is a strong choice
```

```text
Large In-Memory Array
        +
Stability Not Required
        ↓
Quick Sort may provide strong average performance
```

```text
Duplicate-Heavy Input
        ↓
Three-Way Partitioning or Stable Merge Strategy
```

```text
External Data
        ↓
Merge-Based Sorting
```

Memory locality, write count and data movement also affect practical performance.

Selection Sort performs relatively few exchanges but too many comparisons.

Bubble Sort provides adjacent access but excessive quadratic movement.

Insertion Sort combines strong locality with adaptability.

Merge Sort uses predictable sequential merging but requires auxiliary memory.

Quick Sort provides efficient in-place partitioning but depends on pivot quality.

Algorithm selection should therefore consider:

```text
Input Size
Input Distribution
Stability
Memory
Worst-Case Guarantees
Data Structure
Storage Medium
Business Semantics
```

---

# Continuation

Part III will complete the document through:

```text
Unified Comparison Tables
```

```text
Common Misconceptions
```

```text
Technical Interview Perspective
```

```text
Final Engineering Decision Guide
```

```text
Relationship with Previous Documents
```

```text
Relationship with Future Documents
```

```text
Key Takeaways
```

```text
Next Document
```
# Part III — Engineering Perspective, Misconceptions and Final Decision Guide

# Unified Comparison Table

The following table summarizes the principal characteristics of the sorting algorithms studied throughout this module.

| Property | Selection | Bubble | Insertion | Merge | Quick |
|-----------|-----------|---------|-----------|--------|--------|
| Strategy | Selection | Exchange | Insertion | Divide & Conquer | Divide & Conquer |
| Best Case | O(n²) | O(n)* | O(n) | O(n log n) | O(n log n) |
| Average Case | O(n²) | O(n²) | O(n²) | O(n log n) | O(n log n) |
| Worst Case | O(n²) | O(n²) | O(n²) | O(n log n) | O(n²) |
| Auxiliary Space | O(1) | O(1) | O(1) | O(n) | O(log n) average |
| Stable | No | Yes | Yes | Yes | No |
| Adaptive | No | Yes* | Yes | No | Generally No |
| In Place | Yes | Yes | Yes | No | Yes |
| Recursive | No | No | No | Yes | Yes |
| Large Data | Poor | Poor | Moderate | Excellent | Excellent |

*Optimised implementation.

---

# Visual Comparison

The algorithms can be positioned according to their primary strengths.

```text
                   Performance

                        ▲
                        │
          Merge Sort    │    Quick Sort
                        │
                        │
                        │
Insertion Sort          │
                        │
                        │
Selection Sort          │
Bubble Sort             │
────────────────────────────────────────►
                 Simplicity
```

Simple algorithms are easier to understand but generally do not scale well.

More sophisticated algorithms require additional reasoning but provide significantly better performance.

---

# Choosing the Appropriate Algorithm

Algorithm selection should follow a structured reasoning process.

Rather than asking:

```text
Which algorithm is the fastest?
```

the engineer should ask:

```text
What characteristics does my problem have?
```

Typical questions include:

- Is the input small?
- Is the input almost sorted?
- Is stability required?
- Is additional memory acceptable?
- Can the worst case be tolerated?
- Is predictable execution important?

The answers determine the appropriate algorithm.

---

# Practical Recommendations

## Educational Purposes

For learning fundamental algorithmic concepts:

- Selection Sort;
- Bubble Sort;
- Insertion Sort.

These algorithms clearly demonstrate:

- iteration;
- invariants;
- correctness;
- complexity growth.

---

## Small Collections

For relatively small arrays:

```text
Insertion Sort
```

is generally the preferred choice.

Reasons include:

- low overhead;
- simple implementation;
- excellent cache locality;
- adaptive behaviour.

---

## Large Collections

For large datasets:

```text
Merge Sort
```

or

```text
Quick Sort
```

should generally be preferred.

Quadratic algorithms become increasingly inefficient as input size grows.

---

## Stable Ordering

When equal elements must preserve their relative order:

```text
Merge Sort
```

is the strongest standard choice among the studied algorithms.

---

## Memory-Constrained Systems

When additional memory is limited:

```text
Quick Sort
```

is usually preferable because it performs in-place partitioning.

---

## Predictable Performance

Applications requiring guaranteed upper bounds should avoid algorithms whose worst case is quadratic.

Merge Sort provides predictable:

```text
O(n log n)
```

performance.

---

# Common Misconceptions

## Misconception 1

> Quick Sort is always the fastest algorithm.

Reality:

Quick Sort is often among the fastest practical algorithms for arrays, but poor pivot selection may produce quadratic behaviour.

---

## Misconception 2

> Merge Sort is always slower because it copies data.

Reality:

Although Merge Sort requires additional memory, its predictable behaviour frequently makes it highly competitive.

---

## Misconception 3

> Bubble Sort is useful for production software.

Reality:

Bubble Sort is primarily valuable for educational purposes.

Modern software rarely uses Bubble Sort as a general-purpose sorting algorithm.

---

## Misconception 4

> O(n log n) algorithms always outperform O(n²) algorithms.

Reality:

For very small collections, a well-implemented Insertion Sort may outperform more sophisticated algorithms because of lower constant factors.

---

## Misconception 5

> Stable algorithms are always better.

Reality:

Stability is only important when preserving the relative order of equivalent elements has semantic value.

If stability is unnecessary, an unstable algorithm may provide better practical performance.

---

## Misconception 6

> Big-O completely describes performance.

Reality:

Big-O describes asymptotic growth.

Actual execution time also depends on:

- implementation quality;
- hardware;
- cache behaviour;
- compiler optimisation;
- input distribution.

---

# Technical Interview Perspective

Sorting algorithms are among the most frequently discussed topics during software engineering interviews.

Candidates are commonly expected to:

- explain algorithm behaviour;
- analyse complexity;
- justify algorithm selection;
- identify edge cases;
- compare alternative approaches.

Interview questions often focus less on implementation and more on reasoning.

Typical questions include:

- Why is Merge Sort stable?
- Why can Quick Sort degrade to O(n²)?
- Why is Insertion Sort efficient for nearly sorted data?
- When should Merge Sort be preferred over Quick Sort?
- What is the lower bound for comparison sorting?

A strong engineer should be able to answer these questions conceptually before writing code.

---

# Engineering Decision Framework

Algorithm selection can be summarised through the following decision process.

```text
Small Input
      │
      ▼
Insertion Sort

────────────────────────────

Large Input
      │
      ▼

Need Stability?

      │
 ┌────┴─────┐
 │          │
Yes        No
 │          │
 ▼          ▼

Merge     Quick

────────────────────────────

Need Guaranteed Worst Case?

      │
 ┌────┴────┐
 │         │
Yes       No
 │         │
 ▼         ▼

Merge    Quick
```

This framework provides a practical starting point rather than an absolute rule.

---

# Relationship with Previous Documents

This document integrates the knowledge developed throughout the Sorting module.

Previous documents introduced:

- sorting fundamentals;
- sorting properties;
- Selection Sort;
- Bubble Sort;
- Insertion Sort;
- Merge Sort;
- Quick Sort;
- computational complexity.

This chapter compares those concepts from a unified engineering perspective.

---

# Relationship with Future Documents

The next document introduces how Java applies sorting algorithms in real software systems.

Topics include:

- `Arrays.sort()`;
- `Collections.sort()`;
- `Comparable`;
- `Comparator`;
- TimSort;
- Dual-Pivot Quick Sort;
- hybrid sorting strategies used by the Java Standard Library.

This transition connects theoretical algorithm analysis with industrial software development.

---

# Key Takeaways

- There is no universally optimal sorting algorithm.
- Algorithm selection depends on problem requirements.
- Quadratic algorithms are primarily educational or suitable for very small datasets.
- Merge Sort provides stable and predictable performance.
- Quick Sort provides excellent average performance with low auxiliary memory.
- Insertion Sort performs exceptionally well on small or nearly sorted collections.
- Stability, adaptability and memory usage are as important as asymptotic complexity.
- Engineering decisions require balancing multiple trade-offs rather than optimising a single metric.
- Modern software development generally relies on highly optimised library implementations rather than handwritten sorting algorithms.

---

# Conclusion

Sorting algorithms illustrate one of the central principles of computer science:

> **Different algorithmic strategies solve the same problem with different computational costs and engineering trade-offs.**

Understanding these trade-offs enables software engineers to make informed decisions based on correctness, efficiency, maintainability and system requirements.

Mastering sorting algorithms is therefore not simply about learning how to order data—it is about developing the analytical reasoning required to evaluate algorithms in any domain of software engineering.

---

# Next Document

```text
11-java-sorting-framework.md
```

The next document studies how the Java Standard Library implements sorting algorithms, explaining why production systems rely on hybrid algorithms such as **TimSort** and **Dual-Pivot Quick Sort** rather than the elementary algorithms studied individually.