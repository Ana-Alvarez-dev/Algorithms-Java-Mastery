# Sorting Complexity

## Purpose

The purpose of this document is to consolidate the computational complexity concepts introduced throughout the sorting module.

Rather than analysing a specific sorting algorithm, this document studies the theoretical principles that explain **why different sorting algorithms exhibit different performance characteristics**.

Understanding sorting complexity enables software engineers to evaluate algorithmic efficiency, predict scalability, and select appropriate algorithms according to problem requirements.

---

# Learning Objectives

After completing this document, the learner should be able to:

- analyse the computational cost of sorting algorithms;
- distinguish best, average and worst-case complexity;
- identify significant operations during sorting;
- compare quadratic and linearithmic algorithms;
- understand the lower bound of comparison sorting;
- analyse time-space trade-offs;
- relate theoretical complexity to practical performance.

---

# Measuring Sorting Algorithms

The efficiency of a sorting algorithm is determined by the amount of computational resources required to transform an unordered collection into an ordered one.

The two primary resources are:

- execution time;
- memory consumption.

Therefore, complexity analysis focuses on:

```text
Time Complexity
```

and

```text
Space Complexity
```

---

# Input Size

Sorting complexity is expressed as a function of the number of elements:

```text
n = number of elements
```

As the input grows, the algorithm performs an increasing number of operations.

The objective of asymptotic analysis is to understand this growth independently of hardware or programming language.

---

# Significant Operations

Depending on the algorithm, different operations contribute to the execution cost.

Typical operations include:

- comparisons;
- exchanges (swaps);
- shifts;
- assignments;
- recursive calls.

For comparison-based algorithms, comparisons usually dominate the running time.

---

# Time Complexity

Time complexity describes how the number of executed operations grows as the input size increases.

For sorting algorithms, three cases are commonly analysed.

## Best Case

The most favourable input.

## Average Case

Typical expected behaviour.

## Worst Case

The maximum amount of work required.

These three perspectives provide a more complete understanding of algorithmic performance than a single complexity value.

---

# Space Complexity

Space complexity measures the additional memory required beyond the input itself.

Examples:

| Algorithm | Auxiliary Space |
|-----------|----------------:|
| Selection Sort | O(1) |
| Bubble Sort | O(1) |
| Insertion Sort | O(1) |
| Merge Sort | O(n) |
| Quick Sort | O(log n) average recursion stack |

Algorithms requiring additional memory may simplify implementation or improve time complexity.

---

# Complexity Classes

The sorting algorithms studied in this module belong to two principal complexity classes.

## Quadratic Algorithms

```text
O(n²)
```

Includes:

- Selection Sort
- Bubble Sort
- Insertion Sort (average and worst case)

Quadratic algorithms become inefficient as the input size increases.

---

## Linearithmic Algorithms

```text
O(n log n)
```

Includes:

- Merge Sort
- Quick Sort (average case)

These algorithms scale significantly better for large collections.

---

# Complexity Comparison

| Algorithm | Best | Average | Worst |
|-----------|-------|----------|--------|
| Selection Sort | O(n²) | O(n²) | O(n²) |
| Bubble Sort | O(n)* | O(n²) | O(n²) |
| Insertion Sort | O(n) | O(n²) | O(n²) |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) |
| Quick Sort | O(n log n) | O(n log n) | O(n²) |

\* Optimised implementation.

---

# Growth Comparison

Asymptotic growth can be visualised conceptually.

```text
Operations

│
│                          O(n²)
│                       /
│                    /
│                 /
│              /
│           /
│        /
│     /
│  /
│ /______________________________
│        O(n log n)
│
└───────────────────────────────────► n
```

Although both curves increase, the quadratic function grows much faster.

---

# Lower Bound of Comparison Sorting

Comparison-based sorting algorithms rely exclusively on element comparisons.

A fundamental result in computer science states that:

> Every comparison-based sorting algorithm requires at least Ω(n log n) comparisons in the worst case.

Consequently:

- Merge Sort reaches this lower bound.
- Quick Sort reaches it on average.
- Quadratic algorithms cannot achieve it.

This theorem explains why no comparison-based algorithm can guarantee better asymptotic performance than **O(n log n)**.

---

# Stability and Complexity

Complexity and stability are independent properties.

For example:

| Algorithm | Stable | Time Complexity |
|-----------|---------|----------------|
| Merge Sort | Yes | O(n log n) |
| Quick Sort | No | O(n log n) average |
| Bubble Sort | Yes | O(n²) |

A faster algorithm is not necessarily stable.

---

# Adaptability and Complexity

Some algorithms benefit from partially sorted input.

Examples:

| Algorithm | Adaptive |
|-----------|-----------|
| Bubble Sort (optimised) | Yes |
| Insertion Sort | Yes |
| Selection Sort | No |
| Merge Sort | No |
| Quick Sort | Generally No |

Adaptability affects practical performance but does not necessarily change asymptotic complexity.

---

# Time-Space Trade-Off

Algorithm design often involves balancing execution time against memory usage.

Example:

```text
Merge Sort

Faster Worst Case
        ↓
Requires O(n) Memory
```

```text
Quick Sort

Lower Memory Usage
        ↓
Possible O(n²) Worst Case
```

There is rarely a universally optimal algorithm.

The appropriate choice depends on project requirements.

---

# Theoretical versus Practical Performance

Big-O notation describes asymptotic growth rather than actual execution time.

Practical performance is also influenced by:

- processor architecture;
- cache locality;
- compiler optimisation;
- memory allocation;
- implementation quality;
- input distribution.

Therefore, theoretical analysis should be complemented by benchmarking.

---

# Engineering Perspective

Software engineers rarely choose algorithms solely according to asymptotic complexity.

Other important considerations include:

- stability requirements;
- available memory;
- implementation complexity;
- expected input size;
- probability of worst-case behaviour;
- maintainability.

Selecting an algorithm is therefore an engineering decision rather than a purely mathematical one.

---

# Relationship with Previous Documents

This document consolidates the complexity analysis presented in:

- Selection Sort
- Bubble Sort
- Insertion Sort
- Merge Sort
- Quick Sort

Instead of analysing each algorithm individually, it highlights the common theoretical principles governing computational complexity.

---

# Relationship with Future Documents

The next document compares the studied sorting algorithms from a broader engineering perspective, including strategy, stability, adaptability, implementation characteristics and practical use cases.

---

# Key Takeaways

- Sorting complexity measures algorithm scalability.
- Time and space complexity are distinct concepts.
- Quadratic algorithms are suitable primarily for small datasets.
- Merge Sort guarantees O(n log n) performance.
- Quick Sort offers excellent average performance but may degrade to O(n²).
- Comparison-based sorting algorithms cannot asymptotically outperform Ω(n log n).
- Algorithm selection depends on both theoretical complexity and practical engineering requirements.

---

# Next Document

```text
10-sorting-algorithm-comparison.md
```

The next document compares all sorting algorithms studied throughout this module, providing a unified engineering perspective for algorithm selection.