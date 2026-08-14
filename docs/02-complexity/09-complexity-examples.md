# Complexity Analysis Examples

## Algorithms Java Mastery

This document consolidates the concepts introduced throughout the **Complexity** module by applying them to classical algorithms.

Understanding complexity theory is only the first step.

Software engineers must also be capable of analysing real algorithms systematically, identifying their computational cost, explaining their scalability and justifying their implementation decisions.

The central question addressed throughout this document is:

> **How should the computational complexity of a real algorithm be analysed?**

---

# Purpose

The purpose of this document is to demonstrate how theoretical complexity analysis is applied in practice.

Each example follows the same analytical methodology used throughout this repository.

The expected progression is:

```text
Problem
        ↓
Algorithm
        ↓
Time Complexity
        ↓
Space Complexity
        ↓
Best / Average / Worst Case
        ↓
Engineering Interpretation
```

This methodology establishes a repeatable process for analysing any algorithm.

---

# Learning Objectives

After studying this document, the learner should be able to:

- analyse the computational complexity of classical algorithms;
- distinguish time and space complexity;
- identify different execution scenarios;
- explain why an algorithm exhibits a particular growth rate;
- justify algorithm selection from an engineering perspective;
- apply a structured complexity analysis methodology.

These competencies prepare the learner for the algorithm implementation modules that follow.

---

# Standard Analysis Template

Every algorithm studied in this repository should be analysed using the following structure:

```text
Problem
        ↓
Algorithm Description
        ↓
Time Complexity
        ↓
Space Complexity
        ↓
Best Case
        ↓
Average Case
        ↓
Worst Case
        ↓
Engineering Interpretation
```

Maintaining a consistent methodology improves both understanding and documentation quality.

---

# Example 1 — Linear Search

## Problem

Find a value inside an unsorted array.

---

## Strategy

Inspect each element sequentially until the desired value is found.

---

## Time Complexity

Best Case

```text
O(1)
```

The value is located in the first position.

Average Case

```text
O(n)
```

Approximately half of the elements are inspected.

Worst Case

```text
O(n)
```

The entire array must be traversed.

---

## Space Complexity

```text
O(1)
```

Only a small number of variables are required.

No additional memory proportional to the input is allocated.

---

## Engineering Interpretation

Linear Search is simple and effective for small datasets.

Its scalability decreases as the input grows because every element may need to be inspected.

---

# Example 2 — Binary Search

## Problem

Find a value inside a sorted array.

---

## Strategy

Repeatedly divide the search space into two equal parts.

---

## Time Complexity

Best Case

```text
O(1)
```

The target is located immediately.

Average Case

```text
O(log n)
```

Worst Case

```text
O(log n)
```

The search space is halved at every iteration.

---

## Space Complexity

Iterative implementation

```text
O(1)
```

Recursive implementation

```text
O(log n)
```

due to the recursive call stack.

---

## Engineering Interpretation

Binary Search is significantly more scalable than Linear Search but requires sorted data.

---

# Example 3 — Bubble Sort

## Problem

Sort an array.

---

## Strategy

Repeatedly compare adjacent elements and swap them when necessary.

---

## Time Complexity

Best Case

```text
O(n)
```

when optimisation detects an already sorted array.

Average Case

```text
O(n²)
```

Worst Case

```text
O(n²)
```

---

## Space Complexity

```text
O(1)
```

Bubble Sort performs sorting in place.

---

## Engineering Interpretation

Bubble Sort is valuable for educational purposes but is unsuitable for large datasets.

---

# Example 4 — Merge Sort

## Problem

Sort an array efficiently.

---

## Strategy

Divide the array recursively, sort each half and merge the results.

---

## Time Complexity

Best Case

```text
O(n log n)
```

Average Case

```text
O(n log n)
```

Worst Case

```text
O(n log n)
```

---

## Space Complexity

```text
O(n)
```

Temporary arrays are required during the merge process.

---

## Engineering Interpretation

Merge Sort provides predictable performance and excellent scalability.

Its primary disadvantage is additional memory consumption.

---

# Example 5 — ArrayList Insertion

## Problem

Insert an element into a dynamic array.

---

## Strategy

Append the element to the internal array.

Resize the internal storage only when necessary.

---

## Time Complexity

Typical insertion

```text
O(1)
```

Resize operation

```text
O(n)
```

Amortized insertion

```text
O(1)
```

---

## Space Complexity

Normally

```text
O(1)
```

During resizing

Additional memory is temporarily allocated.

---

## Engineering Interpretation

This example illustrates why amortized analysis is necessary.

Occasional expensive operations do not reduce overall scalability.

---

# Comparative Summary

| Algorithm | Time Complexity | Space Complexity | Scalability |
|-----------|-----------------|------------------|-------------|
| Linear Search | O(n) | O(1) | Moderate |
| Binary Search | O(log n) | O(1) / O(log n) | Excellent |
| Bubble Sort | O(n²) | O(1) | Poor |
| Merge Sort | O(n log n) | O(n) | Excellent |
| ArrayList Insert | Amortized O(1) | O(1) | Excellent |

---

# Common Mistakes

When analysing algorithms, avoid the following mistakes:

- confusing execution time with time complexity;
- ignoring space complexity;
- reporting only Big O without considering other cases;
- forgetting amortized analysis for dynamic data structures;
- analysing implementation details instead of algorithmic behaviour.

---

# Best Practices

When analysing any algorithm:

- define the computational problem;
- describe the strategy before analysing complexity;
- evaluate both time and space;
- analyse best, average and worst cases;
- explain the engineering implications;
- justify algorithm selection using scalability.

These practices should become part of every implementation developed throughout this repository.

---

# Relationship with Later Modules

Every algorithm implemented in the remaining repository modules will include a complete complexity analysis following the methodology presented here.

Examples include:

```text
Arrays
        ↓
Complexity Analysis

Searching
        ↓
Complexity Analysis

Sorting
        ↓
Complexity Analysis

Trees
        ↓
Complexity Analysis

Graphs
        ↓
Complexity Analysis

Dynamic Programming
        ↓
Complexity Analysis
```

The analysis process introduced here becomes a permanent component of the engineering workflow adopted throughout the **Algorithms Java Mastery** project.

---

# Key Takeaways

The learner should remember the following principles:

- Complexity analysis follows a systematic methodology.
- Time and space complexity should always be evaluated together.
- Best, average and worst cases provide complementary information.
- Amortized analysis is essential for dynamic data structures.
- Engineering decisions should be justified using scalability.
- Every algorithm in this repository will include a complete complexity analysis before implementation.

This document concludes the theoretical study of computational complexity and establishes the analytical framework that will be used throughout the remainder of the **Algorithms Java Mastery** repository.