# Complexity Analysis of Arrays

## Algorithms Java Mastery

This document applies the analytical principles developed throughout the
**Complexity** module specifically to operations and algorithms involving
arrays.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
algorithmic analysis developed throughout this repository.

The previous Arrays documents established:

- arrays as indexed data structures;
- fixed-size storage;
- the classical contiguous-storage model;
- Java array semantics;
- multidimensional arrays;
- fundamental array operations;
- common array algorithms.

This document connects those concepts with formal complexity analysis.

The objective is not to memorise:

```text
O(1)
O(n)
O(log n)
```

Instead, the learner should derive each result from the amount of work required
by the operation or algorithm.

The central question addressed throughout this document is:

> **Why does a particular array operation or algorithm have its corresponding
> time and space complexity?**

---

# Purpose

The purpose of this document is to analyse the computational cost of array
operations using the methodology established in:

```text
docs/02-complexity/
```

The analysis follows the progression:

```text
Array Structure
        ↓
Operation
        ↓
Significant Work
        ↓
Cost Function
        ↓
Case Analysis
        ↓
Time Complexity
        ↓
Auxiliary Space
        ↓
Engineering Interpretation
```

The emphasis is on explaining the reasoning behind each result.

---

# Learning Objectives

After studying this document, the learner should be able to:

- derive the time complexity of fundamental array operations;
- derive auxiliary-space requirements;
- distinguish exact operation counts from asymptotic bounds;
- distinguish best-, average-, and worst-case cost;
- identify when an average-case probability model is required;
- analyse the effect of input position on array operations;
- distinguish fixed arrays from array-backed dynamic structures;
- evaluate the scalability of array-based algorithms;
- explain complexity results using memory layout and algorithm structure;
- justify engineering decisions using computational complexity.

---

# Analytical Method

Every array-complexity analysis should follow a systematic process:

```text
1. Define the operation or algorithm.
        ↓
2. Define the input-size parameter.
        ↓
3. Identify the significant operation.
        ↓
4. Determine how often it can execute.
        ↓
5. Construct the cost function when useful.
        ↓
6. Analyse relevant cases.
        ↓
7. Derive the asymptotic time bound.
        ↓
8. Analyse auxiliary space.
        ↓
9. Document implementation assumptions.
```

The objective is to explain **why** the final bound is valid.

---

# Input Size

For a one-dimensional array:

```text
n = number of elements
```

For a two-dimensional rectangular array:

```text
R = number of rows
C = number of columns
```

For two independent arrays:

```text
n = length of first array
m = length of second array
```

The input-size parameters should remain independent unless the problem explicitly
defines a relationship between them.

For example:

```text
Θ(RC)
```

should only be simplified to:

```text
Θ(n²)
```

when:

```text
R = C = n
```

has been established.

---

# Exact Cost vs Asymptotic Complexity

Consider an operation with the cost function:

```text
T(n) = n - 1
```

The expression gives a more precise operation count.

Its asymptotic growth is:

```text
T(n) ∈ Θ(n)
```

Therefore:

```text
Exact Cost
        ↓
T(n) = n - 1

Asymptotic Growth
        ↓
Θ(n)
```

The asymptotic result intentionally abstracts away constants and lower-order
terms.

---

# Access by Index

## Operation

Retrieve the value stored at a known valid index.

Example:

```text
int value = numbers[5];
```

---

## Execution Process

Under the standard array model, the position can be determined directly from
the array's base position and the requested index.

Conceptually:

```text
Base
  +
Index Offset
  ↓
Target Position
```

No traversal of previous elements is required.

---

## Time Complexity

```text
Θ(1)
```

---

## Auxiliary Space

```text
Θ(1)
```

---

## Engineering Interpretation

Constant-time indexed access is one of the primary reasons arrays are useful
when positional access is important.

---

# Update

## Operation

Replace the value stored at a known valid index.

Example:

```text
numbers[2] = 80;
```

---

## Execution Process

The element has already been located.

Only one position is modified.

---

## Time Complexity

```text
Θ(1)
```

---

## Auxiliary Space

```text
Θ(1)
```

---

# Traversal

## Operation

Visit all elements of an array.

Example:

```text
for (int value : numbers) {
    process(value);
}
```

---

## Execution Process

For:

```text
n = number of elements
```

the loop processes:

```text
n
```

elements.

If each iteration performs constant work:

```text
T(n) = cn + d
```

for constants `c` and `d`.

Therefore:

```text
T(n) ∈ Θ(n)
```

---

## Auxiliary Space

A simple traversal that uses only a fixed number of additional variables
requires:

```text
Θ(1)
```

auxiliary space.

The input array itself is not counted as auxiliary space.

---

# Linear Search

## Problem

Determine whether a target value exists in an unsorted array.

---

## Significant Operation

A natural operation to count is:

```text
Compare current element with target
```

---

## Best Case

The first element matches:

```text
1 comparison
```

Therefore:

```text
T_best(n) ∈ Θ(1)
```

---

## Worst Case

The target appears in the final relevant position or is absent.

The algorithm may inspect all `n` elements:

```text
T_worst(n) ∈ Θ(n)
```

---

## Average Case

Average-case analysis requires a probability model.

For example, if:

- the target is guaranteed to be present; and
- every position is equally likely to contain it;

then the expected number of comparisons is:

```text
(n + 1) / 2
```

Therefore:

```text
T_average(n) ∈ Θ(n)
```

under that model.

The average case should not be stated without its assumptions.

---

## Auxiliary Space

For an iterative implementation:

```text
S_aux(n) ∈ Θ(1)
```

---

# Binary Search

## Preconditions

Binary search requires the searched range to be ordered according to the
comparison relation used by the algorithm.

---

## Execution Process

Each iteration removes approximately half of the remaining search interval.

Conceptually:

```text
n
 ↓
n / 2
 ↓
n / 4
 ↓
n / 8
 ↓
...
```

---

## Best Case

The target is found at the first middle position:

```text
T_best(n) ∈ Θ(1)
```

---

## Worst Case

The number of interval reductions grows logarithmically:

```text
T_worst(n) ∈ Θ(log n)
```

---

## Average Case

A rigorous average-case result requires a probability model over the possible
targets or search outcomes.

Under suitable models, expected search cost has logarithmic growth.

The exact result should be stated together with its assumptions.

---

## Auxiliary Space

### Iterative Implementation

```text
S_aux(n) ∈ Θ(1)
```

### Recursive Implementation

Maximum recursion depth is logarithmic:

```text
S_aux(n) ∈ Θ(log n)
```

The difference is caused by the implementation strategy rather than the search
problem itself.

---

# Insertion

## Important Model Distinction

A standard Java array has fixed length.

Therefore, it cannot physically grow by inserting a new position.

When discussing insertion, this document distinguishes between:

```text
Logical Insertion with Existing Capacity
```

and:

```text
Insertion Requiring New Storage
```

---

## Order-Preserving Logical Insertion

Consider:

```text
[10][20][40][50][ ][ ]
```

Insert:

```text
30
```

The required arrangement becomes:

```text
[10][20][30][40][50][ ]
```

Elements after the insertion point must move.

---

## Best Case

If insertion occurs at the logical end and unused capacity is available:

```text
Θ(1)
```

work may be sufficient.

---

## Worst Case

Insertion near the beginning may require moving a linear number of elements:

```text
T_worst(n) ∈ Θ(n)
```

---

## Auxiliary Space

If an existing array has sufficient capacity:

```text
S_aux(n) ∈ Θ(1)
```

may be achieved.

If insertion requires creation of a new array of size proportional to `n`:

```text
S_aux(n) ∈ Θ(n)
```

for that operation.

---

# Deletion

## Operation

Remove an element while preserving logical order.

Example:

```text
[10][20][30][40][50]
```

Delete:

```text
20
```

Result:

```text
[10][30][40][50][ ]
```

---

## Execution Process

Elements following the removed position may need to shift.

---

## Best Case

If deleting the logical last element:

```text
Θ(1)
```

may be sufficient.

---

## Worst Case

Deleting near the beginning may require moving a linear number of elements:

```text
T_worst(n) ∈ Θ(n)
```

---

## Auxiliary Space

With in-place shifting:

```text
S_aux(n) ∈ Θ(1)
```

If a new smaller array is created:

```text
S_aux(n) ∈ Θ(n)
```

The implementation must therefore be identified.

---

# Copying an Array

## Operation

Create an independent array containing the same elements.

Example:

```java
int[] copy = Arrays.copyOf(numbers, numbers.length);
```

---

## Time Complexity

Every copied element must be processed:

```text
T(n) ∈ Θ(n)
```

---

## Auxiliary Space

A second array containing `n` elements is created:

```text
S_aux(n) ∈ Θ(n)
```

---

# Reference Assignment vs Copying

These operations must not be confused.

Reference assignment:

```java
int[] b = a;
```

requires:

```text
Time: Θ(1)
Additional Space: Θ(1)
```

but does not create another array.

Content copying:

```java
int[] b = Arrays.copyOf(a, a.length);
```

requires:

```text
Time: Θ(n)
Additional Space: Θ(n)
```

This distinction is particularly important when analysing Java algorithms.

---

# Reverse

## Operation

Reverse the elements of an array in place.

Example:

```text
[1][2][3][4][5]
        ↓
[5][4][3][2][1]
```

---

## Execution Process

Symmetric positions are exchanged:

```text
A[0] ↔ A[n - 1]
A[1] ↔ A[n - 2]
...
```

Approximately half of the positions participate in swaps.

Since the number of swaps is proportional to `n`:

```text
T(n) ∈ Θ(n)
```

---

## Auxiliary Space

A standard in-place implementation requires only a fixed temporary variable:

```text
S_aux(n) ∈ Θ(1)
```

---

# Rotation

## Operation

Rotate the elements by a specified number of positions.

Example:

```text
[1][2][3][4][5]
        ↓
Right Rotation by 1
        ↓
[5][1][2][3][4]
```

---

## Time Complexity

Efficient rotation methods process the array proportionally to its size:

```text
T(n) ∈ Θ(n)
```

---

## Auxiliary Space

An in-place implementation can achieve:

```text
S_aux(n) ∈ Θ(1)
```

An implementation using a temporary array may require:

```text
S_aux(n) ∈ Θ(n)
```

Therefore, the space result depends on the chosen strategy.

---

# Swap

Swapping two known positions is a constant-time operation.

Example:

```text
int temp = numbers[i];
numbers[i] = numbers[j];
numbers[j] = temp;
```

Therefore:

```text
Time: Θ(1)
Auxiliary Space: Θ(1)
```

Swap is a fundamental operation in:

- reversal;
- sorting;
- partitioning;
- rotation.

---

# Shifting

Shifting moves a sequence of elements to new positions.

Example:

```text
[ ][A][B][C][D]
```

can result from shifting:

```text
[A][B][C][D][ ]
```

one position to the right.

If up to `n` elements must move:

```text
T(n) ∈ Θ(n)
```

Shifting therefore explains much of the cost associated with order-preserving
insertion and deletion.

---

# Multidimensional Array Traversal

For a rectangular Java array:

```text
R = number of rows
C = number of columns
```

a full traversal processes:

```text
R × C
```

elements.

Therefore:

```text
T(R, C) ∈ Θ(RC)
```

If:

```text
R = C = n
```

then:

```text
T(n) ∈ Θ(n²)
```

The two-dimensional result should not automatically be represented as `Θ(n²)`
when the dimensions are independent.

---

# Jagged Array Traversal

For a jagged array with row lengths:

```text
L₀, L₁, ..., Lᵣ₋₁
```

the total number of elements is:

```text
Σ Lᵢ
```

A complete traversal therefore has:

```text
T ∈ Θ(Σ Lᵢ)
```

This is the appropriate model when rows have different lengths.

---

# Nested Array Processing

Consider:

```text
for (int row = 0; row < matrix.length; row++) {
    for (int column = 0; column < matrix[row].length; column++) {
        process(matrix[row][column]);
    }
}
```

The complexity is not determined merely by the existence of two loops.

It depends on the number of iterations.

For a rectangular matrix:

```text
R × C
```

the result is:

```text
Θ(RC)
```

This reinforces the general complexity principle:

> **Loop nesting alone does not determine asymptotic complexity.**

---

# Time and Space Summary

Under the stated analytical models:

| Operation | Best Case | Average Case | Worst Case | Auxiliary Space |
|---|---:|---:|---:|---:|
| Access known index | `Θ(1)` | `Θ(1)` | `Θ(1)` | `Θ(1)` |
| Update | `Θ(1)` | `Θ(1)` | `Θ(1)` | `Θ(1)` |
| Full Traversal | `Θ(n)` | `Θ(n)` | `Θ(n)` | `Θ(1)` |
| Linear Search | `Θ(1)` | `Θ(n)`* | `Θ(n)` | `Θ(1)` |
| Binary Search | `Θ(1)` | model-dependent | `Θ(log n)` | `Θ(1)` iterative |
| Copy | `Θ(n)` | `Θ(n)` | `Θ(n)` | `Θ(n)` |
| Reverse | `Θ(n)` | `Θ(n)` | `Θ(n)` | `Θ(1)` in place |
| Rotate | `Θ(n)` | `Θ(n)` | `Θ(n)` | `Θ(1)` in-place possible |
| Insert | `Θ(1)`* | position-dependent | `Θ(n)` | `Θ(1)` or `Θ(n)` |
| Delete | `Θ(1)`* | position-dependent | `Θ(n)` | `Θ(1)` or `Θ(n)` |

`*` The average-case result requires a defined input or position model.

For example, linear search has expected `Θ(n)` under a common model where a
present target is equally likely to occur at every position.

Insertion and deletion require an explicit logical-capacity model because
standard Java arrays have fixed length.

---

# Why the Complexity Values Occur

The principal relationships can be summarised as:

```text
Known Position
        ↓
Direct Access
        ↓
Θ(1)
```

```text
Process Every Element
        ↓
n Operations
        ↓
Θ(n)
```

```text
Reduce Search Space by a Constant Factor
        ↓
Θ(log n)
```

```text
Move or Copy Up to n Elements
        ↓
Θ(n)
```

These relationships are more important than memorising isolated values.

---

# Complexity and Memory Layout

Array complexity is closely related to the properties established in
`02-memory-layout.md`.

The classical model provides:

```text
Array
        ↓
Predictable Indexed Positions
        ↓
Direct Positional Access
        ↓
Θ(1)
```

When an operation requires:

```text
Many Positions
        ↓
Traversal / Copy / Shift
        ↓
Θ(n)
```

the computational work grows with the number of affected elements.

---

# Complexity and Foundations

Array complexity analysis follows the reasoning methodology introduced in
`docs/01-foundations/`.

The complete workflow is:

```text
Problem
        ↓
Specification
        ↓
Preconditions
        ↓
Postconditions
        ↓
Edge Cases
        ↓
Algorithmic Strategy
        ↓
Correctness
        ↓
Complexity
```

For example, a maximum-search problem may define:

```text
Precondition
        ↓
Array contains at least one element
```

and:

```text
Invariant
        ↓
Current maximum is the largest processed value
```

before deriving:

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

---

# Complexity and Case Analysis

Case analysis should be used when input-dependent behaviour is meaningful.

For example:

```text
Linear Search

Best
    ↓
Θ(1)

Worst
    ↓
Θ(n)
```

For a full traversal:

```text
Best = Average = Worst
        ↓
Θ(n)
```

because every element must be processed.

Not every operation therefore requires three separate case sections.

---

# Complexity and Java Implementation

The theoretical result may depend on implementation details.

Examples:

```text
Binary Search
        │
        ├── Iterative
        │      ↓
        │   Auxiliary Θ(1)
        │
        └── Recursive
               ↓
            Auxiliary Θ(log n)
```

Similarly:

```text
Array Copy
        │
        ├── Reference Assignment
        │      ↓
        │   Θ(1)
        │
        └── Content Copy
               ↓
            Θ(n)
```

The complexity statement should always identify the implementation model when
it affects the result.

---

# Complexity and Correctness

An asymptotically efficient algorithm is not useful if it produces incorrect
results.

The correct progression remains:

```text
Correctness
        ↓
Complexity
        ↓
Implementation
        ↓
Testing
```

or more precisely:

```text
Problem
        ↓
Correct Algorithm
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Verification
```

Correctness and efficiency are complementary properties.

---

# Complexity and Testing

Automated tests answer:

> Does the implementation satisfy its specification for selected inputs?

Complexity analysis answers:

> How does computational resource consumption grow?

Therefore:

```text
Testing
        ≠
Complexity Analysis
```

JUnit Jupiter and AssertJ can validate the implementation's behaviour but do not
establish asymptotic bounds.

---

# Complexity and Benchmarking

Benchmarking provides empirical evidence about a concrete implementation.

For example:

```text
Java Implementation
        ↓
JMH
        ↓
Measured Runtime
```

Complexity analysis provides the theoretical model:

```text
Algorithm
        ↓
Cost Function
        ↓
Asymptotic Bound
```

A benchmark cannot prove:

```text
Θ(n)
```

or:

```text
Θ(log n)
```

by itself.

---

# Benchmarking Array Algorithms

Benchmarking may be useful for:

- comparing implementations;
- evaluating constant factors;
- observing cache-sensitive behaviour;
- studying crossover points;
- examining JVM optimisation.

Benchmarking is optional.

It should be performed only when empirical evidence adds meaningful information
to the theoretical analysis.

---

# Engineering Interpretation

Complexity analysis supports data-structure and algorithm selection.

For example:

```text
Frequent Indexed Access
        ↓
Array is attractive
```

while:

```text
Frequent Order-Preserving Insertions
        ↓
Array may become expensive
```

and:

```text
Search in Sorted Data
        ↓
Binary Search may provide logarithmic search growth
```

However, complexity is not the only engineering criterion.

Additional considerations include:

- input size;
- memory constraints;
- mutation requirements;
- preprocessing costs;
- access patterns;
- implementation complexity;
- maintainability.

---

# Scalability

Suppose:

```text
n = input size
```

Then:

```text
Θ(1)
```

does not grow with `n`.

```text
Θ(log n)
```

grows slowly.

```text
Θ(n)
```

grows proportionally.

Therefore:

```text
Increasing Input Size
        ↓
Different Growth Rates
        ↓
Increasing Performance Differences
```

Understanding these differences is essential when array algorithms operate on
large datasets.

---

# Common Mistakes

## “Arrays Are O(1)”

Incorrect.

Arrays are data structures.

Complexity belongs to a particular operation or algorithm.

---

## “All Access Is O(1)”

Only direct access through a known valid index has constant asymptotic cost.

Searching for a value may require `Θ(n)` time.

---

## “Two Loops Always Mean O(n²)”

Incorrect.

The actual iteration bounds must be analysed.

---

## “Binary Search Has O(log n) for Every Input”

The worst-case bound is logarithmic.

The best case may be constant.

Average-case analysis requires a model.

---

## “Insertion Is Always O(n)”

Not necessarily.

Insertion at the logical end with available capacity may require constant
work.

For standard Java arrays, however, physical length cannot increase, so growing
the structure requires another representation.

---

## “Deletion Is Always O(n)”

The cost depends on where the element is deleted and whether order must be
preserved.

---

## “Copying a Reference Copies the Array”

Incorrect.

```java
int[] b = a;
```

copies only the reference.

---

## “Cache Locality Changes the Asymptotic Complexity”

Incorrect.

Cache behaviour may affect observed runtime without changing the asymptotic
growth.

---

## “Big O Is the Exact Complexity”

Incorrect.

When a tight bound is known:

```text
Θ(...)
```

is generally more informative.

---

# Best Practices

When analysing an array algorithm:

- define the input-size parameter;
- identify the exact operation;
- select significant operations;
- derive operation counts when useful;
- distinguish best, average, and worst cases;
- state probability assumptions for average-case analysis;
- use tight bounds when justified;
- analyse auxiliary space separately;
- preserve independent parameters;
- identify implementation-dependent results;
- distinguish fixed arrays from dynamic-array abstractions;
- connect complexity results with the problem's engineering requirements.

The objective is not merely to produce:

```text
O(...)
```

but to explain:

> **Which work is performed, how often it occurs, and why that work grows in
> that way.**

---

# Systematic Complexity Checklist

Before finalising the complexity analysis of an array algorithm, ask:

```text
1. What is the computational problem?
        ↓
2. What does n represent?
        ↓
3. Are there multiple independent parameters?
        ↓
4. What is the significant operation?
        ↓
5. How many elements can be processed?
        ↓
6. Can the algorithm terminate early?
        ↓
7. Do cases differ?
        ↓
8. Is an average-case probability model required?
        ↓
9. What is the cost function?
        ↓
10. What is the tight asymptotic bound?
        ↓
11. What auxiliary memory is required?
        ↓
12. Does the implementation strategy change the result?
        ↓
13. What engineering trade-off follows?
```

This checklist should become part of the standard array-analysis workflow.

---

# Relationship with Later Modules

The complexity principles established here are reused throughout the repository.

```text
Arrays
        ↓
Operation Complexity

Searching
        ↓
Search-Space Complexity

Sorting
        ↓
Comparison and Movement Complexity

Hashing
        ↓
Expected and Worst-Case Complexity

Trees
        ↓
Height-Dependent Complexity

Graphs
        ↓
V / E Complexity

Dynamic Programming
        ↓
State-Space Complexity
```

Arrays therefore provide the first concrete environment in which the complete
complexity methodology is repeatedly applied.

---

# Expected Outcome

After completing this document, the learner should be able to analyse an array
operation from first principles.

For example:

```text
Known Index
        ↓
One Position Accessed
        ↓
Θ(1)
```

or:

```text
All n Elements Processed
        ↓
Θ(n)
```

or:

```text
Search Space Halved Repeatedly
        ↓
Θ(log n)
```

or:

```text
Up to n Elements Shifted
        ↓
Θ(n)
```

The learner should also be able to identify when the result changes because of:

- the input position;
- the implementation strategy;
- the use of additional memory;
- the data structure representation;
- the number of independent input dimensions.

---

# Key Takeaways

After studying this document, the learner should remember that:

- array complexity is derived from the work performed by a specific operation or
  algorithm;
- indexed access and direct update are `Θ(1)` under the standard array model;
- complete traversal is `Θ(n)`;
- linear search has worst-case `Θ(n)`;
- binary search has worst-case `Θ(log n)` under its ordering precondition;
- insertion and deletion can require `Θ(n)` element movement;
- copying `n` elements requires `Θ(n)` time and generally `Θ(n)` additional
  space when a new array is created;
- in-place reversal is `Θ(n)` time and `Θ(1)` auxiliary space;
- rotation can maintain `Θ(n)` time while using either `Θ(1)` or `Θ(n)`
  auxiliary space depending on the implementation;
- multidimensional traversal should preserve independent dimensions such as
  `R` and `C`;
- average-case complexity requires explicit probability assumptions;
- implementation strategy can affect auxiliary-space complexity;
- memory layout explains constant-time indexed access but does not fully
  determine concrete runtime performance;
- theoretical complexity and benchmarking provide different forms of evidence;
- complexity analysis should support engineering decisions rather than replace
  them.

---

# Relationship with the Arrays Module

The conceptual progression of the module is:

```text
01-array-fundamentals.md
        ↓
02-memory-layout.md
        ↓
03-java-arrays.md
        ↓
04-multidimensional-arrays.md
        ↓
05-array-operations.md
        ↓
06-traversal-patterns.md
        ↓
07-common-algorithms.md
        ↓
08-complexity-analysis.md
```

The learner has now moved from:

```text
What is an array?
```

through:

```text
How is an array represented?
```

and:

```text
What operations can be performed?
```

to:

```text
Why do those operations have their computational costs?
```

This progression mirrors the general learning methodology of
**Algorithms Java Mastery**:

```text
Concept
        ↓
Structure
        ↓
Implementation
        ↓
Algorithm
        ↓
Complexity
        ↓
Verification
        ↓
Engineering Interpretation
```

---

# Next Document

```text
09-problem-solving-guide.md
```

The next document applies the complete repository problem-solving methodology
to array-based problems.

It integrates:

```text
Problem Understanding
        ↓
Specification
        ↓
Contracts
        ↓
Edge-Case Analysis
        ↓
Pattern Recognition
        ↓
Algorithmic Strategy
        ↓
Pseudocode
        ↓
Correctness
        ↓
Complexity
        ↓
Java Implementation
        ↓
Testing
```

The next central question becomes:

> **How can the complete problem-solving methodology be applied systematically
> to array-based computational problems?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The complexity analysis in this document applies the principles established
throughout the **Complexity** module, including:

- computational cost;
- algorithm growth;
- asymptotic analysis;
- asymptotic notation;
- case analysis;
- time complexity;
- space complexity;
- amortized analysis.

Java-specific implementation considerations are based on the Java language and
standard library model.

Complementary academic and technical references are documented in:

```text
docs/00-project/10-references.md
```

Additional references may be introduced when a specific array algorithm,
implementation technique, or Java library operation requires specialised
analysis.