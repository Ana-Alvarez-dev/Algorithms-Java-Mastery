# Arrays — Java Implementations

## Algorithms Java Mastery

This package contains the Java implementations associated with the **Arrays** module of **Algorithms Java Mastery**.

The objective of this directory is not merely to provide a collection of array exercises. Each implementation represents a specific algorithmic concept studied in the documentation and is designed to connect theoretical reasoning with executable Java code.

The implementations progress from fundamental constant-time operations to traversal, aggregation, searching, in-place transformations, structural operations, and preprocessing techniques.

The central question addressed by this package is:

> **How can fundamental array operations and algorithms be translated from formal algorithmic reasoning into clear, correct, testable, and complexity-aware Java implementations?**

---

# Purpose

The purpose of this package is to transform the theoretical concepts studied in the Arrays module into practical Java implementations.

Each implementation should preserve the general development methodology of the repository:

```text
Problem Understanding
        ↓
Formal Specification
        ↓
Preconditions / Postconditions
        ↓
Algorithmic Strategy
        ↓
Pseudocode
        ↓
Manual Trace
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Experimental Evaluation
```

The Java source code represents only one stage of this process.

Before an algorithm reaches this directory, its behaviour should already be conceptually understood and its computational characteristics identified.

---

# Package Location

```text
src/
└── main/
    └── java/
        └── org/
            └── anaalvarez/
                └── algorithms/
                    └── arrays/
                        ├── README.md
                        ├── ArrayAccess.java
                        ├── ArrayUpdate.java
                        ├── ArrayTraversal.java
                        ├── ArrayAggregation.java
                        ├── FindMinimum.java
                        ├── FindMaximum.java
                        ├── FindMinimumAndMaximum.java
                        ├── LinearSearch.java
                        ├── ArrayReverse.java
                        ├── ArrayCopy.java
                        ├── InsertAtIndex.java
                        ├── DeleteAtIndex.java
                        ├── RemoveDuplicatesSorted.java
                        └── PrefixSum.java
```

---

# Learning Objectives

After implementing and studying the algorithms contained in this package, the learner should be able to:

* perform direct indexed access to an array;
* update elements through their indexes;
* traverse arrays systematically;
* maintain state during traversal;
* aggregate information from array elements;
* identify minimum and maximum values;
* search sequentially for a target value;
* distinguish between read-only and mutating operations;
* perform in-place transformations;
* reason about auxiliary memory usage;
* copy arrays correctly;
* simulate insertion and deletion in fixed-size structures;
* understand the cost of shifting elements;
* exploit sorted input when removing duplicates;
* use preprocessing to improve repeated queries;
* identify the significant operations of an algorithm;
* derive time and auxiliary-space complexity;
* formulate loop invariants for traversal-based algorithms;
* translate preconditions and postconditions into Java contracts;
* design automated tests for normal, boundary, and invalid cases.

---

# Implementation Philosophy

The implementations in this package prioritize:

1. **Correctness**
2. **Clarity**
3. **Explicit algorithmic reasoning**
4. **Testability**
5. **Complexity awareness**
6. **Appropriate Java practices**

The objective is not to produce the shortest possible implementation.

For example, an operation should not be replaced immediately by a high-level library method if doing so would hide the algorithmic process currently being studied.

When appropriate, a later comparison with Java standard-library alternatives may be performed.

This distinction is intentional:

```text
Learning implementation
        ↓
Understand the mechanism

Library implementation
        ↓
Use an existing abstraction
```

Both are valuable, but they serve different purposes.

---

# Implementation Progression

The package follows a deliberate progression.

```text
Indexed Access
      ↓
Update
      ↓
Traversal
      ↓
State Maintenance
      ↓
Aggregation
      ↓
Searching
      ↓
In-Place Manipulation
      ↓
Copying
      ↓
Structural Operations
      ↓
Algorithmic Patterns
      ↓
Preprocessing
```

Each stage introduces a new form of reasoning while reusing concepts from the previous stages.

---

# 1. Indexed Access

## `ArrayAccess.java`

Introduces direct access to an element through its index.

Conceptually:

```text
array[index]
```

This implementation reinforces the relationship between:

* indexed organisation;
* valid index ranges;
* array length;
* boundary validation;
* direct access.

For an array of length `n`, valid indexes are:

```text
0 ... n - 1
```

Expected complexity:

```text
Time:            Θ(1)
Auxiliary space: Θ(1)
```

The operation does not depend on traversing the array.

---

# 2. Element Update

## `ArrayUpdate.java`

Introduces modification of an element at a known index.

Conceptually:

```text
array[index] = value
```

This implementation introduces an important distinction:

```text
Access
    ↓
Reads state

Update
    ↓
Changes state
```

The operation should explicitly consider index validity and mutation of the original array.

Expected complexity:

```text
Time:            Θ(1)
Auxiliary space: Θ(1)
```

---

# 3. Array Traversal

## `ArrayTraversal.java`

Introduces systematic iteration over every element of an array.

A basic traversal follows:

```text
A[0]
A[1]
A[2]
...
A[n - 1]
```

This class establishes one of the most important patterns used throughout the repository.

Many algorithms can be understood as:

```text
Traversal
    +
State
    +
Decision
```

Expected complexity for a complete traversal:

```text
Time:            Θ(n)
Auxiliary space: Θ(1)
```

Traversal will become the foundation for aggregation, searching, minimum/maximum detection, transformations, and many later algorithms.

---

# 4. Aggregation

## `ArrayAggregation.java`

Introduces algorithms that traverse an array while accumulating information.

Examples include:

```text
sum
count
average-related totals
conditional counts
```

The general pattern is:

```text
Initial state
      ↓
Visit element
      ↓
Update accumulated state
      ↓
Continue
      ↓
Return final state
```

This class provides an important introduction to **state maintenance during iteration**.

Expected complexity for a complete aggregation:

```text
Time:            Θ(n)
Auxiliary space: Θ(1)
```

---

# 5. Minimum Value

## `FindMinimum.java`

Finds the smallest element in a non-empty array.

The algorithm maintains a candidate minimum while traversing the remaining elements.

Conceptually:

```text
minimum = A[0]

for each remaining element:
    if current < minimum:
        minimum = current
```

A useful loop invariant is:

> At the beginning of each iteration, `minimum` contains the smallest value among the elements processed so far.

Expected complexity:

```text
Time:            Θ(n)
Auxiliary space: Θ(1)
```

This implementation directly connects the Arrays module with the **Correctness and Formal Reasoning** module.

---

# 6. Maximum Value

## `FindMaximum.java`

Finds the greatest element in a non-empty array.

Its structure is similar to `FindMinimum`, but the maintained state represents the greatest value encountered.

A useful loop invariant is:

> At the beginning of each iteration, `maximum` contains the greatest value among the elements processed so far.

Expected complexity:

```text
Time:            Θ(n)
Auxiliary space: Θ(1)
```

This algorithm reinforces the relationship:

```text
Traversal
    +
Candidate state
    +
Comparison
    =
Extremum search
```

---

# 7. Minimum and Maximum Together

## `FindMinimumAndMaximum.java`

Determines both the minimum and maximum values of an array.

This implementation introduces an important algorithmic question:

> Can related results be computed during the same traversal?

A straightforward solution can maintain both values simultaneously:

```text
minimum
maximum
```

during one traversal.

Expected asymptotic complexity:

```text
Time:            Θ(n)
Auxiliary space: Θ(1)
```

The class also creates an opportunity to study the difference between:

```text
Asymptotic complexity
        ↓
Growth rate

Exact operation counting
        ↓
Number of comparisons
```

Two algorithms can both be `Θ(n)` while performing different numbers of significant operations.

---

# 8. Linear Search

## `LinearSearch.java`

Searches sequentially for a target value.

Conceptually:

```text
A[0] → A[1] → A[2] → ... → A[n - 1]
```

The algorithm stops when:

* the target is found; or
* all elements have been examined.

This is an important example for distinguishing different cases.

Expected complexity:

```text
Best case:       Θ(1)
Worst case:      Θ(n)
Auxiliary space: Θ(1)
```

Depending on the assumptions about the input and target distribution, average-case analysis may also be studied separately.

This implementation prepares the transition to the dedicated **Searching** module.

---

# 9. In-Place Reversal

## `ArrayReverse.java`

Reverses the order of the elements in an array.

An in-place implementation typically uses two indexes:

```text
left  →             ← right
```

and repeatedly swaps symmetric elements.

Conceptually:

```text
[A, B, C, D, E]

 ↓

[E, D, C, B, A]
```

This introduces several important ideas:

* two-pointer traversal;
* symmetric positions;
* swapping;
* mutation;
* in-place algorithms;
* auxiliary-space analysis.

Expected complexity:

```text
Time:            Θ(n)
Auxiliary space: Θ(1)
```

This is one of the first examples where traversal does not necessarily require visiting every position independently.

---

# 10. Array Copying

## `ArrayCopy.java`

Creates a separate array containing the elements of the original array.

This implementation introduces the distinction between:

```text
Original array
      ↓
Independent copy
```

and:

```text
Two references
      ↓
Same array object
```

This is particularly important in Java because arrays are objects and variables hold references to them.

Expected complexity for copying `n` elements:

```text
Time:            Θ(n)
Auxiliary space: Θ(n)
```

Unlike many previous examples, the additional array means that auxiliary-space usage grows with the input size.

---

# 11. Insertion at an Index

## `InsertAtIndex.java`

Studies the conceptual insertion of a value at a specified position.

Because a Java array has a fixed length after creation, insertion is not equivalent to dynamically expanding the same array.

The operation generally requires either:

* an existing capacity convention; or
* creation of another array.

Elements after the insertion position may need to be shifted.

Conceptually:

```text
Before:

[A, B, C, D]

Insert X at index 2

After:

[A, B, X, C, D]
```

The important algorithmic concept is the movement of elements.

Expected worst-case complexity:

```text
Time: Θ(n)
```

Auxiliary-space complexity depends on the selected implementation strategy and must therefore be documented explicitly in the class.

---

# 12. Deletion at an Index

## `DeleteAtIndex.java`

Studies deletion from an indexed sequence represented by an array.

Removing an element creates a conceptual gap:

```text
[A, B, C, D]
       ↑

Delete C
```

which requires later elements to be shifted:

```text
[A, B, D]
```

As with insertion, Java arrays cannot physically reduce their length after creation.

Therefore, the implementation must clearly define its contract.

Expected worst-case complexity:

```text
Time: Θ(n)
```

The implementation should explicitly document whether it:

* returns a new array;
* shifts elements inside an existing capacity model; or
* uses another defined representation.

---

# 13. Removing Duplicates from a Sorted Array

## `RemoveDuplicatesSorted.java`

Introduces an important algorithmic pattern based on exploiting an input property.

The precondition is significant:

> The input array is sorted.

Because equal values occur consecutively, duplicate removal can avoid repeatedly searching the entire array.

A typical solution uses two logical positions:

```text
Read position
        ↓
Examines elements

Write position
        ↓
Tracks the next unique position
```

This introduces a form of the **two-pointer pattern**.

Expected complexity:

```text
Time:            Θ(n)
Auxiliary space: Θ(1)
```

for an in-place logical compaction strategy.

The exact return contract must define how the new logical size is represented.

This algorithm demonstrates an important principle:

> Input properties can fundamentally influence algorithm design.

---

# 14. Prefix Sum

## `PrefixSum.java`

Introduces preprocessing as an algorithmic strategy.

Given:

```text
A = [a₀, a₁, a₂, ..., aₙ₋₁]
```

a prefix-sum structure stores cumulative information:

```text
P[i] = A[0] + A[1] + ... + A[i]
```

Building the prefix array requires:

```text
Time:            Θ(n)
Auxiliary space: Θ(n)
```

The preprocessing cost can then make repeated range-sum queries significantly cheaper.

This introduces an important trade-off:

```text
Additional preprocessing
        +
Additional memory
        ↓
Faster repeated queries
```

Prefix sums provide an early example of a broader algorithmic principle that will later appear in dynamic programming, indexing, caching, and other optimisation techniques.

---

# Conceptual Classification

The implementations can be grouped according to the principal concept they introduce.

| Category                 | Implementations                                       | Main Concept              |
| ------------------------ | ----------------------------------------------------- | ------------------------- |
| Indexed access           | `ArrayAccess`                                         | Direct access             |
| Mutation                 | `ArrayUpdate`                                         | State modification        |
| Traversal                | `ArrayTraversal`                                      | Sequential processing     |
| Aggregation              | `ArrayAggregation`                                    | State accumulation        |
| Extremes                 | `FindMinimum`, `FindMaximum`, `FindMinimumAndMaximum` | Candidate maintenance     |
| Searching                | `LinearSearch`                                        | Sequential search         |
| In-place manipulation    | `ArrayReverse`                                        | Two pointers and mutation |
| Copying                  | `ArrayCopy`                                           | Auxiliary memory          |
| Structural operations    | `InsertAtIndex`, `DeleteAtIndex`                      | Element shifting          |
| Pattern-based processing | `RemoveDuplicatesSorted`                              | Two pointers              |
| Preprocessing            | `PrefixSum`                                           | Time-space trade-off      |

---

# Complexity Overview

The following table provides the expected high-level complexity characteristics.

| Implementation           |       Time |          Auxiliary Space | Main Reason                                  |
| ------------------------ | ---------: | -----------------------: | -------------------------------------------- |
| `ArrayAccess`            |       Θ(1) |                     Θ(1) | Direct indexed access                        |
| `ArrayUpdate`            |       Θ(1) |                     Θ(1) | Direct indexed update                        |
| `ArrayTraversal`         |       Θ(n) |                     Θ(1) | Visits all elements                          |
| `ArrayAggregation`       |       Θ(n) |                     Θ(1) | Single traversal                             |
| `FindMinimum`            |       Θ(n) |                     Θ(1) | All elements must be considered              |
| `FindMaximum`            |       Θ(n) |                     Θ(1) | All elements must be considered              |
| `FindMinimumAndMaximum`  |       Θ(n) |                     Θ(1) | Single traversal                             |
| `LinearSearch`           |  Θ(1)–Θ(n) |                     Θ(1) | Depends on target position                   |
| `ArrayReverse`           |       Θ(n) |                     Θ(1) | Symmetric swaps                              |
| `ArrayCopy`              |       Θ(n) |                     Θ(n) | Copies every element                         |
| `InsertAtIndex`          |       O(n) | Implementation-dependent | Elements may require shifting/copying        |
| `DeleteAtIndex`          |       O(n) | Implementation-dependent | Elements may require shifting/copying        |
| `RemoveDuplicatesSorted` |       Θ(n) |                    Θ(1)* | Single traversal with logical write position |
| `PrefixSum`              | Θ(n) build |                     Θ(n) | Precomputed cumulative structure             |

`*` Assuming an in-place logical compaction implementation.

These values should not simply be memorised.

For every implementation, the learner should be able to explain **why** the complexity follows from the operations performed.

---

# Correctness Reasoning

Several implementations in this package provide ideal first examples for formal correctness reasoning.

For traversal-based algorithms, a common structure is:

```text
Initialization
      ↓
Loop Invariant
      ↓
Maintenance
      ↓
Termination
      ↓
Postcondition
```

Examples include:

```text
FindMinimum
        ↓
minimum represents the smallest
processed element

FindMaximum
        ↓
maximum represents the greatest
processed element

LinearSearch
        ↓
the target has not occurred
in the already rejected prefix

ArrayReverse
        ↓
the outer portions are already
in their final reversed positions

PrefixSum
        ↓
each computed prefix contains
the aggregate of the required prefix
```

Correctness reasoning should be developed in conjunction with:

```text
docs/02-correctness-and-formal-reasoning/
```

The objective is not merely to produce working implementations, but to understand why their state transitions preserve the required properties.

---

# Preconditions and Contracts

Each implementation should explicitly define its input requirements.

Possible conditions include:

```text
array != null
```

```text
0 <= index < array.length
```

```text
array.length > 0
```

or, for algorithms with stronger assumptions:

```text
array is sorted
```

Preconditions must not remain implicit when they affect algorithm correctness.

For example:

```text
RemoveDuplicatesSorted
        ↓
Requires sorted input
```

The implementation should clearly document whether invalid input:

* causes an exception;
* is rejected through argument validation;
* or lies outside the method contract.

Consistency should be maintained across the package.

---

# Edge Cases

Every implementation should be analysed against relevant edge cases.

Typical array edge cases include:

```text
null reference
empty array
single-element array
two-element array
duplicate values
all values equal
negative values
already sorted input
reverse-sorted input
first valid index
last valid index
invalid negative index
index equal to array length
Integer.MIN_VALUE
Integer.MAX_VALUE
```

Not every case applies to every algorithm.

The purpose is to identify cases systematically rather than mechanically test every possible category.

---

# Testing Correspondence

Every relevant implementation should have a corresponding test class under:

```text
src/test/java/org/anaalvarez/algorithms/arrays/
```

Expected correspondence:

```text
ArrayAccess.java
        ↓
ArrayAccessTest.java

ArrayUpdate.java
        ↓
ArrayUpdateTest.java

ArrayTraversal.java
        ↓
ArrayTraversalTest.java

ArrayAggregation.java
        ↓
ArrayAggregationTest.java

FindMinimum.java
        ↓
FindMinimumTest.java

FindMaximum.java
        ↓
FindMaximumTest.java

FindMinimumAndMaximum.java
        ↓
FindMinimumAndMaximumTest.java

LinearSearch.java
        ↓
LinearSearchTest.java

ArrayReverse.java
        ↓
ArrayReverseTest.java

ArrayCopy.java
        ↓
ArrayCopyTest.java

InsertAtIndex.java
        ↓
InsertAtIndexTest.java

DeleteAtIndex.java
        ↓
DeleteAtIndexTest.java

RemoveDuplicatesSorted.java
        ↓
RemoveDuplicatesSortedTest.java

PrefixSum.java
        ↓
PrefixSumTest.java
```

Tests should use the testing technologies defined for the project:

```text
JUnit Jupiter
+
AssertJ
```

---

# Testing Strategy

Tests should be derived from the algorithm contract.

A useful progression is:

```text
Normal case
      ↓
Boundary case
      ↓
Minimum valid input
      ↓
Special structural case
      ↓
Invalid input
```

For example, `FindMaximumTest` could verify:

```text
positive values
negative values
duplicate maximum
maximum at first position
maximum at last position
single-element array
empty array
null reference
```

Testing should answer:

> Does the Java implementation behave according to the specification for the selected executions?

It complements, but does not replace, correctness reasoning.

---

# Benchmarking

Not every class in this package requires a benchmark.

Benchmarking should be introduced only when there is a meaningful performance question.

Potential experiments include:

```text
Sequential traversal
        vs
alternative traversal forms
```

```text
Manual copying
        vs
Java copying utilities
```

```text
Repeated range calculation
        vs
prefix-sum preprocessing
```

Relevant benchmarks should be located under:

```text
src/jmh/java/org/anaalvarez/algorithms/benchmarks/arrays/
```

JMH should be used for empirical performance evaluation rather than ad-hoc timing with `System.nanoTime()`.

---

# Relationship with Documentation

The conceptual foundation for these implementations belongs to the documentation modules.

```text
docs/
│
├── 01-foundations/
├── 02-correctness-and-formal-reasoning/
├── 03-complexity/
└── 04-arrays/
```

Their responsibilities are different.

```text
docs/04-arrays/
        ↓
What is the structure?
How does it behave?
Which algorithms operate on it?
What are their theoretical properties?

src/main/.../arrays/
        ↓
How are those ideas represented
as executable Java implementations?

src/test/.../arrays/
        ↓
How is implementation behaviour verified?

src/jmh/.../arrays/
        ↓
How can relevant performance hypotheses
be evaluated empirically?
```

Together they form a complete learning cycle.

---

# Relationship with Later Modules

The Arrays package establishes patterns that will reappear throughout the repository.

```text
Array Traversal
        ↓
Linear Search
        ↓
Sorting
        ↓
Linked Structures
        ↓
Trees
        ↓
Graphs
```

Examples include:

### Traversal

Introduces systematic state progression.

Later reused in:

* linked lists;
* trees;
* graphs;
* dynamic programming tables.

### Two Pointers

Introduced through operations such as array reversal and duplicate removal.

Later useful in:

* searching;
* partitioning;
* sorted-sequence problems.

### State Maintenance

Introduced through minimum, maximum, and aggregation.

Later reused in:

* greedy algorithms;
* graph algorithms;
* dynamic programming.

### Preprocessing

Introduced through prefix sums.

Later connected with:

* caching;
* dynamic programming;
* range-query techniques;
* time-space trade-offs.

The package therefore serves as an important bridge between basic Java operations and more advanced algorithmic reasoning.

---

# Recommended Implementation Order

The implementations should not be completed randomly.

A recommended order is:

```text
1. ArrayAccess
        ↓
2. ArrayUpdate
        ↓
3. ArrayTraversal
        ↓
4. ArrayAggregation
        ↓
5. FindMinimum
        ↓
6. FindMaximum
        ↓
7. FindMinimumAndMaximum
        ↓
8. LinearSearch
        ↓
9. ArrayReverse
        ↓
10. ArrayCopy
        ↓
11. InsertAtIndex
        ↓
12. DeleteAtIndex
        ↓
13. RemoveDuplicatesSorted
        ↓
14. PrefixSum
```

This sequence progressively introduces additional reasoning requirements.

---

# Study Workflow for Each Implementation

Every implementation should follow the same workflow.

```text
1. Study the corresponding documentation.
        ↓
2. Define the problem.
        ↓
3. Identify input and output.
        ↓
4. Establish preconditions and postconditions.
        ↓
5. Design the algorithm independently of Java.
        ↓
6. Write pseudocode.
        ↓
7. Perform a manual trace.
        ↓
8. Formulate the correctness argument.
        ↓
9. Analyse time and auxiliary-space complexity.
        ↓
10. Implement the algorithm in Java.
        ↓
11. Create automated tests.
        ↓
12. Execute Maven verification.
        ↓
13. Review implementation quality.
        ↓
14. Commit the completed learning unit.
```

This process is more important than the number of algorithms implemented.

---

# Definition of Done

An array implementation should not be considered complete merely because it compiles.

For each relevant algorithm, verify:

### Understanding

* [ ] The computational problem is understood.
* [ ] Input and output are clearly identified.
* [ ] Preconditions are known.
* [ ] Postconditions are known.

### Algorithm Design

* [ ] The strategy is understood before coding.
* [ ] Pseudocode can be explained.
* [ ] A manual trace has been performed.
* [ ] Relevant edge cases have been identified.

### Correctness

* [ ] The maintained state is understood.
* [ ] A loop invariant is identified when appropriate.
* [ ] Termination can be explained.
* [ ] The postcondition follows from the algorithm behaviour.

### Complexity

* [ ] Time complexity is identified.
* [ ] Auxiliary-space complexity is identified.
* [ ] The significant operation can be explained.
* [ ] The complexity is justified rather than memorised.

### Java Implementation

* [ ] Naming expresses intent.
* [ ] Input validation follows the defined contract.
* [ ] Responsibilities remain focused.
* [ ] The implementation does not unnecessarily hide the algorithm.
* [ ] Mutation behaviour is explicit.

### Testing

* [ ] Normal cases are tested.
* [ ] Boundary cases are tested.
* [ ] Invalid inputs are tested when applicable.
* [ ] Tests use JUnit Jupiter and AssertJ.
* [ ] Tests reflect the documented contract.

### Verification

* [ ] The project compiles.
* [ ] All automated tests pass.
* [ ] Maven verification succeeds.
* [ ] Documentation and implementation remain consistent.

---

# Expected Outcome

After completing this package, the learner should be able to look at an array problem and reason beyond the Java syntax.

Instead of immediately asking:

> **Which loop should I write?**

the reasoning process should become:

```text
What is the problem?
        ↓
What does the input represent?
        ↓
What result must be produced?
        ↓
What state must be maintained?
        ↓
What traversal pattern is appropriate?
        ↓
What property remains true?
        ↓
Why does the algorithm terminate?
        ↓
What is the time complexity?
        ↓
What is the auxiliary-space cost?
        ↓
How should the algorithm be implemented?
        ↓
How should its behaviour be tested?
```

The purpose of the Arrays implementation module is therefore not simply to learn how to manipulate Java arrays.

Its purpose is to use arrays as the first concrete environment in which the complete **Algorithms Java Mastery** methodology is applied systematically.

---

# Next Step

After completing the fundamental array implementations, the same reasoning process will be extended to the dedicated **Searching** module.

Concepts developed here — particularly traversal, indexed access, state maintenance, invariants, edge-case analysis, and complexity reasoning — provide the foundation for understanding and implementing more sophisticated search algorithms.

---

> **The objective is not to memorise array algorithms. The objective is to develop a repeatable process for transforming a computational problem into a correct, analysed, tested, and maintainable implementation.**
