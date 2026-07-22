# Common Array Algorithms

## Algorithms Java Mastery

This document presents the most common algorithms implemented using arrays.

Unlike previous documents, which introduced the theoretical foundations of arrays, this module focuses on solving practical computational problems.

Each algorithm illustrates a different problem-solving strategy while reinforcing the concepts of correctness, computational complexity and clean Java implementation.

The objective is not simply to memorise solutions but to understand the reasoning behind each algorithm.

The central question addressed throughout this document is:

> **How can common computational problems be solved efficiently using arrays?**

---

# Purpose

The purpose of this document is to study representative algorithms built upon arrays.

Each algorithm will be analysed using the same systematic methodology adopted throughout this repository.

The expected progression is:

```text
Problem
        ↓
Analysis
        ↓
Algorithm Design
        ↓
Java Implementation
        ↓
Complexity Analysis
        ↓
Engineering Discussion
```

This methodology encourages structured problem solving rather than trial-and-error programming.

---

# Learning Objectives

After studying this document, the learner should be able to:

- recognise common array problems;
- select an appropriate algorithmic strategy;
- implement array algorithms in Java;
- analyse correctness;
- evaluate computational complexity;
- explain engineering trade-offs.

---

# Algorithm Analysis Method

Every algorithm presented in this document follows the same structure.

```text
Problem
        ↓
Input
        ↓
Output
        ↓
Idea
        ↓
Pseudocode
        ↓
Java Implementation
        ↓
Complexity Analysis
        ↓
Applications
```

Using a consistent methodology facilitates comparison between algorithms.

---

# Algorithm 1 — Find the Maximum Element

## Problem

Determine the largest value stored in an array.

---

## Input

```text
[7, 2, 15, 4, 9]
```

---

## Output

```text
15
```

---

## Strategy

Traverse the array while maintaining the largest value encountered so far.

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

## Complexity

| Time | Space |
|------|--------|
| O(n) | O(1) |

---

## Applications

- statistical analysis;
- optimisation problems;
- data processing.

---

# Algorithm 2 — Find the Minimum Element

The approach is identical to the previous algorithm.

Only the comparison changes.

---

## Complexity

| Time | Space |
|------|--------|
| O(n) | O(1) |

---

# Algorithm 3 — Calculate the Sum

## Problem

Compute the sum of all array elements.

---

## Strategy

Maintain an accumulator.

---

## Pseudocode

```text
sum ← 0

for each element

    sum ← sum + element
```

---

## Complexity

| Time | Space |
|------|--------|
| O(n) | O(1) |

---

# Algorithm 4 — Calculate the Average

The average requires two operations:

- compute the sum;
- divide by the number of elements.

---

## Complexity

| Time | Space |
|------|--------|
| O(n) | O(1) |

---

# Algorithm 5 — Count Occurrences

## Problem

Determine how many times a value appears.

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

Traverse the array while maintaining a counter.

---

## Complexity

| Time | Space |
|------|--------|
| O(n) | O(1) |

---

# Algorithm 6 — Reverse an Array

## Problem

Reverse the order of the elements.

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

Use the Two-Pointer traversal pattern.

One pointer begins at the start.

The other begins at the end.

Exchange both values while moving towards the centre.

---

## Complexity

| Time | Space |
|------|--------|
| O(n) | O(1) |

---

# Algorithm 7 — Verify Whether an Array Is Sorted

## Problem

Determine whether the elements are already ordered.

---

## Strategy

Compare each element with its successor.

The algorithm terminates immediately when an inversion is detected.

---

## Complexity

| Time | Space |
|------|--------|
| O(n) | O(1) |

---

# Algorithm 8 — Linear Search

## Problem

Determine whether a value exists.

---

## Strategy

Visit every element until the value is found.

---

## Complexity

| Case | Complexity |
|------|------------|
| Best | O(1) |
| Average | O(n) |
| Worst | O(n) |

---

# Algorithm 9 — Remove Duplicate Values

## Problem

Identify duplicate elements.

---

## Discussion

Several solutions exist.

Using only arrays generally requires nested comparisons.

Using additional data structures such as hash tables greatly improves efficiency.

This algorithm will be revisited in the Hashing module.

---

# Algorithm 10 — Rotate an Array

Example:

```text
Before

[1][2][3][4][5]

↓

After

[5][1][2][3][4]
```

---

## Complexity

Typical implementations execute in:

| Time | Space |
|------|--------|
| O(n) | O(1) |

---

# Algorithm 11 — Find the Second Largest Element

## Problem

Determine the second largest value without sorting the array.

---

## Strategy

Maintain:

- largest value;
- second largest value.

Both values are updated during a single traversal.

---

## Complexity

| Time | Space |
|------|--------|
| O(n) | O(1) |

---

# Algorithm 12 — Merge Two Sorted Arrays

## Problem

Combine two sorted arrays into a single sorted array.

---

## Strategy

Use two independent indexes.

Always copy the smallest available element.

This strategy anticipates the Merge phase of Merge Sort.

---

## Complexity

| Time | Space |
|------|--------|
| O(n + m) | O(n + m) |

---

# Relationship Between Algorithms and Traversal Patterns

Every algorithm studied above relies on one or more traversal strategies.

| Algorithm | Main Pattern |
|-----------|--------------|
| Maximum | Sequential Traversal |
| Minimum | Sequential Traversal |
| Sum | Sequential Traversal |
| Average | Sequential Traversal |
| Count Occurrences | Sequential Traversal |
| Reverse | Two Pointers |
| Sorted Verification | Sequential Traversal |
| Linear Search | Sequential Traversal |
| Rotate | Sequential Traversal |
| Merge Arrays | Two Pointers |

Recognising these patterns simplifies algorithm design.

---

# Engineering Perspective

Professional software engineers rarely invent completely new algorithms.

Instead, they identify:

- the problem;
- the underlying traversal pattern;
- the appropriate data structure;
- the computational trade-offs.

The quality of a solution depends more on algorithmic reasoning than on programming syntax.

---

# Relationship with Future Modules

Several algorithms introduced here will be revisited in greater depth.

```text
Searching
        ↓
Linear Search
        ↓
Binary Search

Sorting
        ↓
Merge
        ↓
Partition

Hashing
        ↓
Duplicate Detection

Dynamic Programming
        ↓
Prefix Computation

Algorithmic Patterns
        ↓
Two Pointers
        ↓
Sliding Window
```

This module therefore serves as the practical bridge between array fundamentals and more advanced algorithmic techniques.

---

# Best Practices

When implementing array algorithms:

- understand the problem before writing code;
- identify the traversal pattern;
- analyse edge cases;
- evaluate computational complexity;
- write clear and readable Java code;
- validate the implementation with automated tests.

---

# Key Takeaways

The learner should remember the following principles:

- Many array problems can be solved using a small number of algorithmic ideas.
- Traversal patterns are reusable across different problems.
- Efficient algorithms minimise unnecessary work.
- Correctness must always be established before optimisation.
- Complexity analysis should accompany every implementation.
- Understanding algorithms is more valuable than memorising code.

Mastering these common algorithms provides the practical experience necessary to solve increasingly complex problems throughout the **Algorithms Java Mastery** repository.