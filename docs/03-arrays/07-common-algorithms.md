# Common Array Algorithms

## Algorithms Java Mastery

This document presents a selection of representative algorithms that operate
on arrays and applies the problem-solving methodology established throughout
**Algorithms Java Mastery**.

The previous documents introduced:

- arrays as an abstract data structure;
- Java array representation;
- memory organisation;
- multidimensional arrays;
- fundamental array operations;
- computational complexity.

This document now moves from individual operations toward complete algorithmic
solutions.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
algorithmic reasoning developed throughout this repository.

The objective is not to memorise implementations.

Instead, the learner should recognise recurring problem structures, select an
appropriate strategy, reason about correctness, and derive the computational
cost of the resulting solution.

The central question addressed throughout this document is:

> **How can common computational problems be solved systematically using
> arrays, and how can their solutions be analysed and justified?**

---

# Purpose

The purpose of this document is to provide representative array algorithms that
connect theory with implementation.

Each example follows the repository's general reasoning process:

```text
Problem
        ↓
Input and Output
        ↓
Specification
        ↓
Strategy
        ↓
Pseudocode
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Testing
```

The examples are deliberately selected to reinforce reusable algorithmic
patterns rather than isolated programming tricks.

---

# Learning Objectives

After studying this document, the learner should be able to:

- recognise common computational problems involving arrays;
- identify reusable traversal patterns;
- select an appropriate algorithmic strategy;
- define relevant preconditions and edge cases;
- describe algorithms using language-independent pseudocode;
- reason about correctness using invariants where appropriate;
- implement representative solutions in Java;
- analyse time and auxiliary-space complexity;
- identify cases where different strategies produce different trade-offs;
- recognise when an algorithm should be revisited in a later module.

---

# Analysis Method

Each algorithm should be understood through the following analytical structure:

```text
Problem
        ↓
Input
        ↓
Output
        ↓
Preconditions
        ↓
Strategy
        ↓
Invariant / Correctness
        ↓
Complexity
        ↓
Engineering Interpretation
```

The examples below summarise the reasoning.

Full production implementations and automated tests belong in the Java source
and test structures defined elsewhere in the repository.

---

# Algorithm 1 — Find the Maximum Element

## Problem

Determine the largest value stored in an integer array.

---

## Input

Example:

```text
[7, 2, 15, 4, 9]
```

---

## Output

```text
15
```

---

## Preconditions

A possible contract is:

```text
The array is valid.
The array contains at least one element.
```

Whether `null` is accepted should be explicitly defined by the implementation
contract.

---

## Strategy

Traverse the array while maintaining the largest value encountered so far.

Conceptually:

```text
Initial Maximum
        ↓
Process Next Element
        ↓
Compare with Maximum
        ↓
Update if Necessary
        ↓
Continue
```

---

## Pseudocode

```text
maximum ← first element

for each remaining element

    if current > maximum

        maximum ← current

return maximum
```

---

## Correctness Invariant

After processing the first `i` elements:

> `maximum` contains the largest value among the elements processed so far.

When the traversal ends, every valid element has been processed.

Therefore, `maximum` contains the largest value in the array.

---

## Complexity

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

where:

```text
n = number of elements
```

---

## Engineering Interpretation

The algorithm is preferable to sorting when only the maximum value is required,
because sorting would perform substantially more work than necessary.

The problem requires one full traversal, so linear time is asymptotically
appropriate.

---

# Algorithm 2 — Find the Minimum Element

## Problem

Determine the smallest value stored in an array.

---

## Strategy

The algorithm mirrors maximum search.

Maintain:

```text
minimum
```

containing the smallest value encountered so far.

---

## Pseudocode

```text
minimum ← first element

for each remaining element

    if current < minimum

        minimum ← current

return minimum
```

---

## Correctness Invariant

After processing the first `i` elements:

> `minimum` contains the smallest value among the processed elements.

At termination, all elements have been examined.

Therefore, `minimum` is the minimum value of the array.

---

## Complexity

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

---

# Algorithm 3 — Calculate the Sum

## Problem

Compute the sum of all elements in an integer array.

---

## Input

```text
[2, 4, 6, 8]
```

---

## Output

```text
20
```

---

## Preconditions

The implementation should define how an empty array is handled.

A common contract is:

```text
Empty array
        ↓
Sum = 0
```

---

## Strategy

Maintain an accumulator.

---

## Pseudocode

```text
sum ← 0

for each element

    sum ← sum + element

return sum
```

---

## Correctness Invariant

After processing the first `i` elements:

> `sum` equals the sum of all processed elements.

At termination, every element has been included.

Therefore, `sum` equals the sum of the complete array.

---

## Complexity

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

---

# Algorithm 4 — Calculate the Average

## Problem

Calculate the arithmetic mean of the values in an array.

---

## Strategy

The calculation requires:

```text
Sum
    ÷
Number of Elements
```

Therefore:

```text
Traverse Array
        ↓
Compute Sum
        ↓
Divide by n
```

---

## Preconditions

The implementation must define behaviour for:

```text
n = 0
```

because division by zero would otherwise be undefined.

A reasonable contract may require:

```text
Array contains at least one element.
```

---

## Pseudocode

```text
sum ← 0

for each element

    sum ← sum + element

return sum / number of elements
```

---

## Complexity

The summation requires a complete traversal.

The final division is constant work.

Therefore:

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

---

# Algorithm 5 — Count Occurrences

## Problem

Determine how many times a target value appears in an array.

Example:

```text
Input

[3, 7, 3, 8, 3]

Target

3
```

Output:

```text
3
```

---

## Strategy

Maintain a counter and increment it whenever the current element equals the
target.

---

## Pseudocode

```text
count ← 0

for each element

    if element = target

        count ← count + 1

return count
```

---

## Correctness Invariant

After processing the first `i` elements:

> `count` equals the number of occurrences of the target among those processed
> elements.

At termination, all elements have been examined.

---

## Complexity

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

---

# Algorithm 6 — Reverse an Array

## Problem

Reverse the order of an array in place.

Example:

```text
Before

[1][2][3][4][5]

        ↓

After

[5][4][3][2][1]
```

---

## Strategy

Use two indexes:

```text
left  → first position
right → last position
```

Swap the elements and move both indexes toward the centre.

---

## Pseudocode

```text
left ← 0
right ← n - 1

while left < right

    swap A[left] and A[right]

    left ← left + 1
    right ← right - 1
```

---

## Correctness Invariant

At every iteration:

> Positions outside the interval `[left, right]` have already been placed in
> their final reversed positions.

When:

```text
left ≥ right
```

all positions have been processed.

---

## Complexity

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

Only a constant number of temporary variables is required.

---

# Algorithm 7 — Verify Whether an Array Is Sorted

## Problem

Determine whether an array is ordered according to a specified comparison
relation.

For ascending integer order:

```text
[1][3][5][8]
```

is sorted.

While:

```text
[1][5][3][8]
```

is not.

---

## Strategy

Compare each element with the next element.

If an inversion is found:

```text
A[i] > A[i + 1]
```

the array is not sorted.

The algorithm can terminate immediately.

---

## Pseudocode

```text
for i from 0 to n - 2

    if A[i] > A[i + 1]

        return false

return true
```

---

## Complexity

Best case:

```text
Θ(1)
```

when an inversion occurs immediately.

Worst case:

```text
Θ(n)
```

when the array is sorted or the inversion occurs near the end.

Auxiliary space:

```text
Θ(1)
```

---

# Algorithm 8 — Linear Search

## Problem

Determine whether a target value exists in an unsorted array.

---

## Strategy

Inspect elements sequentially until:

- the target is found; or
- the array ends.

---

## Pseudocode

```text
for i from 0 to n - 1

    if A[i] = target

        return i

return -1
```

---

## Complexity

Best case:

```text
Θ(1)
```

Worst case:

```text
Θ(n)
```

Average case depends on the probability model.

Under the standard model where the target is present and each position is
equally likely:

```text
Θ(n)
```

---

## Auxiliary Space

```text
Θ(1)
```

for the iterative implementation.

---

## Engineering Interpretation

Linear search is appropriate when:

- the input is unsorted;
- the collection is small;
- preprocessing is not justified;
- only a limited number of searches is required.

---

# Algorithm 9 — Second Largest Element

## Problem

Determine the second largest distinct value without sorting the array.

---

## Preconditions

The implementation must define whether:

```text
[5, 5, 5]
```

contains a valid second largest value.

A common contract may require:

```text
At least two distinct values exist.
```

---

## Strategy

Maintain two values:

```text
largest
secondLargest
```

During a single traversal:

```text
Current Value
        ↓
Compare with Largest
        ↓
Update Largest / Second Largest
        ↓
Continue
```

The exact comparison logic must account for duplicate values.

---

## Complexity

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

No sorting is required.

---

## Engineering Interpretation

This example demonstrates how a carefully selected invariant can avoid an
unnecessary sorting step.

---

# Algorithm 10 — Rotate an Array

## Problem

Rotate an array to the right by one position.

Example:

```text
Before

[1][2][3][4][5]

        ↓

After

[5][1][2][3][4]
```

---

## Strategy

Several valid algorithms exist.

A simple in-place implementation can:

1. store the last element;
2. shift the remaining elements right;
3. place the stored value at the beginning.

---

## Complexity

For a simple shift-based implementation:

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

More advanced rotation strategies may also achieve:

```text
Θ(n)
```

time with:

```text
Θ(1)
```

auxiliary space.

---

# Algorithm 11 — Merge Two Sorted Arrays

## Problem

Combine two sorted arrays into a single sorted result.

Example:

```text
A = [1, 4, 7]

B = [2, 3, 8]
```

Output:

```text
[1, 2, 3, 4, 7, 8]
```

---

## Strategy

Maintain two indexes:

```text
i → current position in A
j → current position in B
```

Compare the current elements.

Copy the smaller value to the result.

Continue until one input is exhausted, then copy the remaining elements.

---

## Pseudocode

```text
i ← 0
j ← 0

while i < length(A) and j < length(B)

    if A[i] ≤ B[j]

        copy A[i]
        i ← i + 1

    else

        copy B[j]
        j ← j + 1

copy remaining elements from A
copy remaining elements from B
```

---

## Correctness Invariant

At every iteration:

> The result array contains the smallest elements from the two input prefixes
> considered so far, in sorted order.

When either input is exhausted, every remaining value can be appended directly
because that input is already sorted.

---

## Complexity

Let:

```text
n = length(A)
m = length(B)
```

Then:

```text
Time: Θ(n + m)
Auxiliary Space: Θ(n + m)
```

because a separate result array is created.

This is the fundamental merge operation used later by Merge Sort.

---

# Algorithm 12 — Duplicate Detection

## Problem

Determine whether an array contains duplicate values.

Example:

```text
[4, 7, 2, 4]
```

contains duplicates.

---

## Strategy 1 — Pairwise Comparison

Compare each element with the remaining elements.

Conceptually:

```text
A[0] ↔ A[1...]
A[1] ↔ A[2...]
...
```

The number of comparisons grows quadratically.

---

## Complexity

```text
Time: Θ(n²)
Auxiliary Space: Θ(1)
```

for the simple pairwise approach.

---

## Strategy 2 — Auxiliary Hash Structure

A hash-based approach can store values already observed.

Conceptually:

```text
Current Value
        ↓
Already Seen?
        ↓
Yes → Duplicate
No  → Store Value
```

Under appropriate hashing assumptions:

```text
Expected Time: Θ(n)
Auxiliary Space: Θ(n)
```

This alternative will be revisited in the **Hashing** module.

---

# Algorithmic Pattern Summary

The algorithms above can be grouped according to their principal strategies:

| Algorithm | Main Pattern |
|---|---|
| Maximum | Sequential Traversal |
| Minimum | Sequential Traversal |
| Sum | Accumulator |
| Average | Accumulator + Reduction |
| Count Occurrences | Sequential Traversal |
| Reverse | Two Pointers |
| Sorted Verification | Adjacent Comparison |
| Linear Search | Sequential Search |
| Second Largest | Stateful Single Pass |
| Rotation | Shifting / Two Pointers |
| Merge Sorted Arrays | Two Pointers |
| Duplicate Detection | Pairwise or Hash-Based |

Recognising these patterns is often more useful than memorising individual
implementations.

---

# Sequential Traversal

Many array algorithms are based on a single traversal.

Typical structure:

```text
for each element
        ↓
Update State
        ↓
Continue
```

Examples:

- maximum;
- minimum;
- sum;
- count;
- linear search;
- second-largest value.

If each element is processed a constant number of times:

```text
Time
        ↓
Θ(n)
```

---

# Two-Pointer Pattern

Two-pointer algorithms maintain two positions simultaneously.

Examples include:

```text
Reverse
```

and:

```text
Merge Sorted Arrays
```

Conceptually:

```text
Left Pointer
       ↓
[................]
       ↑
Right Pointer
```

The pointers may move:

- toward one another;
- through two independent arrays;
- according to a problem-specific rule.

Two-pointer techniques often allow a linear-time solution while avoiding nested
scans.

---

# Early-Termination Pattern

Some algorithms can stop before processing the entire array.

Examples include:

```text
Linear Search
```

and:

```text
Sorted Verification
```

Conceptually:

```text
Process
   ↓
Condition Found?
   │
   ├── Yes → Stop
   └── No  → Continue
```

This creates different best- and worst-case costs.

---

# Accumulator Pattern

An accumulator stores information derived from processed elements.

Examples:

```text
sum
count
maximum
minimum
```

Conceptually:

```text
Element
        ↓
Update Accumulator
        ↓
Next Element
```

This is one of the most reusable patterns for linear-time array algorithms.

---

# Stateful Single-Pass Algorithms

Some problems require maintaining several pieces of state during one traversal.

For example:

```text
largest
secondLargest
```

The objective is to preserve enough information so that a second traversal,
sorting operation, or auxiliary data structure is unnecessary.

This pattern encourages careful invariant design.

---

# Correctness Through Invariants

Many single-pass array algorithms can be justified using an invariant.

For example, maximum search:

```text
After processing i elements:

maximum = largest processed element
```

Count occurrences:

```text
After processing i elements:

count = number of target occurrences in processed prefix
```

Merge:

```text
Result prefix contains the smallest values
from the processed input prefixes in sorted order.
```

Invariant reasoning should be used whenever it meaningfully strengthens the
correctness argument.

---

# Complexity Perspective

The algorithms demonstrate several important complexity families.

```text
Θ(1)
        ↓
Individual Access

Θ(n)
        ↓
Single Traversal

Θ(n log n)
        ↓
Efficient Divide-and-Conquer Sorting

Θ(n²)
        ↓
Pairwise Comparison
```

The objective is to derive these results from the operations performed.

---

# Complexity Should Be Stated Precisely

Avoid:

```text
Maximum = O(n)
```

when a tight result is known.

Prefer:

```text
Maximum Search
        ↓
Time: Θ(n)
Auxiliary Space: Θ(1)
```

Likewise, for linear search:

```text
Best Case: Θ(1)
Worst Case: Θ(n)
```

The relevant case should be named whenever input-dependent behaviour matters.

---

# Input Parameters

Algorithms may require different input-size parameters.

For example:

```text
Merge Two Arrays

n = length(A)
m = length(B)
```

Therefore:

```text
Time: Θ(n + m)
```

should not be replaced by:

```text
Θ(n)
```

unless a relationship between `n` and `m` has been explicitly established.

This follows the multi-parameter complexity principles introduced in
`docs/02-complexity/`.

---

# Space Analysis

The examples also demonstrate different auxiliary-space patterns.

```text
Maximum Search
        ↓
Θ(1)
```

```text
In-Place Reverse
        ↓
Θ(1)
```

```text
Merge Sorted Arrays
        ↓
Θ(n + m)
```

```text
Hash-Based Duplicate Detection
        ↓
Expected Θ(n)
```

The space result depends on the actual implementation strategy.

---

# Array Algorithms and Java

These algorithms should be implemented in Java after their logic and complexity
have been established.

A representative project workflow is:

```text
Algorithm Design
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
JUnit Jupiter Tests
        ↓
AssertJ Assertions
```

Benchmarking with JMH may be introduced when empirical performance adds
meaningful information.

---

# Testing Strategy

Array algorithms should be tested against cases derived from their
specifications.

Examples include:

```text
Empty Array
Single Element
Duplicate Values
Negative Values
Already Sorted
Reverse Sorted
All Equal Values
Extreme Values
```

Not every case applies to every algorithm.

For example:

```text
Maximum Search
```

may require a non-empty array.

A duplicate-detection algorithm should define whether an empty array is:

```text
No Duplicates
```

or outside its accepted domain.

The test suite should follow the defined contract.

---

# Engineering Interpretation

The algorithms demonstrate a central engineering principle:

> **The best solution is not necessarily the one with the shortest code.**

A good algorithm should be:

- correct;
- understandable;
- sufficiently efficient;
- appropriate for the input constraints;
- maintainable;
- testable.

For example:

```text
Duplicate Detection
```

may be solved using:

```text
Θ(n²) Time + Θ(1) Space
```

or:

```text
Expected Θ(n) Time + Θ(n) Space
```

The appropriate choice depends on:

- input size;
- memory constraints;
- required performance;
- hashing assumptions;
- maintainability requirements.

---

# Relationship with Future Modules

Several algorithms introduced here are deliberately connected to later topics.

```text
Linear Search
        ↓
Searching Module
```

```text
Merge
        ↓
Merge Sort
        ↓
Sorting Module
```

```text
Duplicate Detection
        ↓
Hash Tables
```

```text
Two Pointers
        ↓
Algorithmic Patterns
```

```text
Stateful Single Pass
        ↓
Algorithmic Problem Solving
```

The purpose is to build conceptual continuity across the repository.

---

# Common Mistakes

## Sorting to Find a Maximum

Sorting solves a more general problem than necessary.

If only the maximum is required:

```text
Single Traversal
        ↓
Θ(n)
```

is sufficient.

---

## Using Nested Loops When One Traversal Is Enough

Many aggregation problems can be solved in one pass.

---

## Ignoring Duplicate Semantics

The meaning of “second largest” depends on whether duplicate values are allowed
to represent the same rank.

---

## Assuming Average Case Without a Model

Average complexity requires probability assumptions.

---

## Returning an Incorrect Result for Empty Input

The contract must define whether empty input is valid.

---

## Ignoring Integer Overflow

Algorithms such as sum and average may require careful numeric-range
consideration.

Complexity analysis alone does not guarantee numerical correctness.

---

## Modifying the Input Unexpectedly

In-place algorithms such as reverse and rotation mutate the input.

The method contract should make this clear.

---

# Best Practices

When designing an array algorithm:

- define the problem before coding;
- identify input and output;
- define preconditions and postconditions;
- analyse edge cases;
- select the simplest appropriate strategy;
- identify reusable traversal patterns;
- establish an invariant when useful;
- derive time and auxiliary-space complexity;
- implement clearly in Java;
- test against the defined contract;
- benchmark only when empirical evidence adds value;
- document important trade-offs.

The objective is to develop repeatable algorithmic reasoning.

---

# Reusable Algorithm Template

Future array algorithms can be documented using:

```text
## Problem

## Input

## Output

## Preconditions

## Strategy

## Pseudocode

## Correctness / Invariant

## Time Complexity

## Auxiliary Space

## Engineering Interpretation
```

Only include sections that contribute meaningful information.

The structure should remain consistent with the documentation methodology used
throughout the repository.

---

# Expected Outcome

After completing this document, the learner should be able to move from a
natural-language array problem to a structured algorithmic solution:

```text
Problem
        ↓
Specification
        ↓
Pattern Recognition
        ↓
Algorithmic Strategy
        ↓
Invariant
        ↓
Pseudocode
        ↓
Complexity
        ↓
Java Implementation
        ↓
Testing
```

The learner should recognise that many apparently different problems are
instances of a small number of reusable algorithmic patterns.

This recognition is a major step from writing code toward **algorithmic
problem solving**.

---

# Key Takeaways

After studying this document, the learner should remember that:

- common array problems can often be solved with simple reusable strategies;
- single-pass algorithms are powerful for aggregation and state-maintenance
  problems;
- two-pointer techniques can avoid unnecessary nested traversals;
- early termination creates different best- and worst-case costs;
- invariants provide a rigorous way to reason about correctness;
- time and auxiliary-space complexity should be derived from actual operations;
- multiple input parameters must be preserved when they are independent;
- different strategies may trade memory for execution time;
- implementation should follow, not replace, algorithmic reasoning;
- tests should be derived from the problem contract and edge cases.

Common array algorithms therefore provide the practical bridge between the
fundamental array structure and the more advanced searching, sorting, hashing,
and algorithmic-pattern modules that follow in **Algorithms Java Mastery**.

---

# Next Document

```text
08-complexity-analysis.md
```

The next document performs a dedicated complexity analysis of array operations
and algorithms.

It consolidates:

- access;
- traversal;
- search;
- insertion;
- deletion;
- copying;
- shifting;
- nested processing;
- auxiliary space;
- input-dependent cases.

The next central question becomes:

> **How can the complexity of array-based algorithms be derived systematically
> from their operations and implementation strategies?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The algorithmic reasoning, correctness methodology, and complexity analysis
follow the principles established throughout the preceding **Foundations** and
**Complexity** modules.

Java-specific implementations are based on the Java language and standard
library model.

Complementary academic and technical references are documented in:

```text
docs/00-project/10-references.md
```

Additional references may be introduced in later modules when particular
algorithms, data structures, or Java implementation details require more
specialised treatment.