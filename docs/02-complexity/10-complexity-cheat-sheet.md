# Complexity Cheat Sheet

## Algorithms Java Mastery

This document serves as a quick reference guide for the computational complexity concepts introduced throughout the **Complexity** module.

Unlike the previous documents, which progressively develop the theoretical foundations of algorithm analysis, this guide summarizes the most important concepts, complexity classes and engineering recommendations in a single place.

Its purpose is to provide a concise reference that can be consulted during algorithm implementation, technical interviews and software design.

---

# Purpose

The purpose of this document is to consolidate the fundamental concepts of computational complexity into a practical engineering reference.

The expected progression is:

```text
Theory
        ↓
Reference Guide
        ↓
Engineering Decisions
```

This document should complement—not replace—the theoretical documents studied previously.

---

# Computational Complexity Overview

Computational complexity evaluates the computational resources required by an algorithm.

The two primary resources are:

```text
Computational Complexity
│
├── Time Complexity
│
└── Space Complexity
```

Every algorithm implemented in this repository should analyse both.

---

# Complexity Analysis Workflow

The recommended analysis procedure is:

```text
Understand the Problem
        ↓
Design the Strategy
        ↓
Estimate Time Complexity
        ↓
Estimate Space Complexity
        ↓
Identify Best / Average / Worst Cases
        ↓
Determine Asymptotic Growth
        ↓
Implement in Java
        ↓
Write Automated Tests
        ↓
Benchmark with JMH
```

---

# Asymptotic Notations

| Notation | Meaning | Purpose |
|----------|---------|---------|
| **O(...)** | Upper Bound | Maximum asymptotic growth |
| **Θ(...)** | Tight Bound | Exact asymptotic growth |
| **Ω(...)** | Lower Bound | Minimum asymptotic growth |
| **o(...)** | Strict Upper Bound | Mathematical analysis |
| **ω(...)** | Strict Lower Bound | Mathematical analysis |

---

# Complexity Classes

| Growth | Typical Notation | Scalability |
|---------|-----------------|-------------|
| Constant | O(1) | Excellent |
| Logarithmic | O(log n) | Excellent |
| Linear | O(n) | Good |
| Linearithmic | O(n log n) | Very Good |
| Quadratic | O(n²) | Moderate |
| Cubic | O(n³) | Poor |
| Exponential | O(2ⁿ) | Very Poor |
| Factorial | O(n!) | Impractical |

---

# Relative Growth

```text
Fastest
│
├── O(1)
├── O(log n)
├── O(n)
├── O(n log n)
├── O(n²)
├── O(n³)
├── O(2ⁿ)
└── O(n!)
│
Slowest
```

As input size increases, differences between these growth rates become increasingly significant.

---

# Typical Algorithms

| Algorithm | Time Complexity | Space Complexity |
|-----------|-----------------|------------------|
| Array Access | O(1) | O(1) |
| Linear Search | O(n) | O(1) |
| Binary Search | O(log n) | O(1) |
| Bubble Sort | O(n²) | O(1) |
| Selection Sort | O(n²) | O(1) |
| Insertion Sort | O(n²) | O(1) |
| Merge Sort | O(n log n) | O(n) |
| Quick Sort (Average) | O(n log n) | O(log n) |
| Quick Sort (Worst) | O(n²) | O(n) |
| Heap Sort | O(n log n) | O(1) |

---

# Java Collections (Typical Complexity)

| Collection | Operation | Complexity |
|------------|-----------|------------|
| ArrayList | get(index) | O(1) |
| ArrayList | add(end) | Amortized O(1) |
| ArrayList | add(index) | O(n) |
| ArrayList | remove(index) | O(n) |
| LinkedList | addFirst() | O(1) |
| LinkedList | addLast() | O(1) |
| LinkedList | get(index) | O(n) |
| HashMap | get(key) | Average O(1) |
| HashMap | put(key,value) | Average O(1) |
| HashMap | remove(key) | Average O(1) |
| TreeMap | get() | O(log n) |
| TreeMap | put() | O(log n) |
| PriorityQueue | offer() | O(log n) |
| PriorityQueue | poll() | O(log n) |

> **Note:** HashMap operations may degrade to **O(n)** in pathological cases, although modern Java implementations significantly reduce this possibility.

---

# Time vs Space

```text
Algorithm
│
├── Time Complexity
│       ↓
│   Computational Work
│
└── Space Complexity
        ↓
   Memory Consumption
```

Efficient software considers both dimensions.

---

# Best / Average / Worst Case

| Case | Meaning |
|------|---------|
| Best Case | Minimum computational work |
| Average Case | Expected computational work |
| Worst Case | Maximum computational work |

Worst-case analysis is generally preferred in software engineering because it provides predictable performance guarantees.

---

# Amortized Analysis

Used when isolated expensive operations are compensated by many inexpensive operations.

Typical examples:

- ArrayList
- Dynamic Arrays
- Hash Tables
- Dynamic Buffers

Amortized analysis does **not** require probability.

---

# Engineering Decision Guide

| If... | Prefer... |
|--------|-----------|
| Constant-time access is required | Arrays |
| Frequent insertions at the end | ArrayList |
| Frequent insertions/removals at both ends | LinkedList / Deque |
| Fast key lookup | HashMap |
| Sorted keys | TreeMap |
| Priority processing | PriorityQueue |
| Massive datasets | O(log n) or O(n log n) algorithms |

---

# Complexity Analysis Checklist

Before implementing any algorithm, verify that you can answer:

- What is the computational problem?
- What is the algorithmic strategy?
- What is the input size?
- What operations dominate execution?
- What is the time complexity?
- What is the space complexity?
- What are the best, average and worst cases?
- Does amortized analysis apply?
- Is the algorithm scalable?
- Why was this algorithm selected?

---

# Common Mistakes

Avoid the following misconceptions:

- Measuring milliseconds instead of analysing growth.
- Confusing Big O with exact complexity.
- Ignoring space complexity.
- Ignoring best and worst cases.
- Forgetting amortized behaviour.
- Choosing algorithms based only on implementation simplicity.

---

# Complexity Module Summary

The complete learning progression is:

```text
Computational Complexity
        ↓
Asymptotic Analysis
        ↓
Time Complexity
        ↓
Space Complexity
        ↓
Asymptotic Notation
        ↓
Algorithm Growth
        ↓
Best / Average / Worst Case
        ↓
Amortized Analysis
        ↓
Complexity Analysis Examples
        ↓
Complexity Cheat Sheet
```

---

# Final Engineering Principles

Throughout this repository, every algorithm should be analysed according to the following principles:

- Understand the problem before writing code.
- Analyse computational complexity before implementation.
- Evaluate both time and memory consumption.
- Justify every algorithmic decision.
- Prefer scalable solutions whenever possible.
- Validate theoretical analysis with automated testing and benchmarking.
- Document complexity explicitly.
- Think like a software engineer, not merely a programmer.

The objective of the **Algorithms Java Mastery** repository is not only to implement algorithms correctly, but to understand, analyse, justify and communicate their behaviour using the rigorous methodology employed in Computer Science and professional software engineering.