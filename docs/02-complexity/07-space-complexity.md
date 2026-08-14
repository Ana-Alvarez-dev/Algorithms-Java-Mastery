# Space Complexity

## Algorithms Java Mastery

This document introduces **space complexity**, the branch of computational complexity that studies the amount of memory required by an algorithm during its execution.

Just as time complexity evaluates computational work, space complexity evaluates **memory consumption**.

An algorithm is not considered efficient solely because it executes quickly.

It must also use memory responsibly, especially when processing large datasets.

The central question addressed throughout this document is:

> **How much memory does an algorithm require as the input size increases?**

---

# Purpose

The purpose of this document is to establish a rigorous methodology for analysing the memory requirements of algorithms.

Rather than measuring memory usage on a specific computer, space complexity studies how memory consumption grows as the problem size increases.

The expected progression is:

```text
Algorithm
        ↓
Memory Allocation
        ↓
Memory Growth
        ↓
Space Complexity
```

This methodology allows memory efficiency to be analysed independently of hardware.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define space complexity;
- distinguish input memory from auxiliary memory;
- identify memory allocation performed by an algorithm;
- analyse iterative and recursive memory usage;
- recognise memory–time trade-offs;
- understand in-place algorithms;
- evaluate algorithm scalability from a memory perspective.

These competencies are fundamental for designing efficient software systems.

---

# What Is Space Complexity?

Space complexity describes how much memory an algorithm requires while solving a problem.

It analyses the relationship between:

```text
Input Size
        ↓
Memory Consumption
```

As the input grows, memory requirements may also increase.

Understanding this relationship is essential for building scalable applications.

---

# Memory as a Computational Resource

Memory is one of the two primary computational resources studied in Computer Science.

```text
Algorithm
│
├── Time
│
└── Memory
```

Both resources influence algorithm efficiency.

Improving one often affects the other.

---

# Types of Memory Used by Algorithms

Algorithms may consume different categories of memory.

The most common are:

```text
Memory
│
├── Input Memory
│
├── Auxiliary Memory
│
└── Call Stack
```

Each category contributes differently to the total memory requirements.

---

# Input Memory

Input memory corresponds to the data provided to the algorithm.

Example:

```java
int[] numbers = new int[1000];
```

The array already exists before the algorithm begins.

Normally, when analysing auxiliary space complexity, the input itself is **not** counted.

---

# Auxiliary Memory

Auxiliary memory is the additional memory created during execution.

Examples include:

- temporary arrays;
- auxiliary collections;
- buffers;
- local data structures.

Example:

```java
int[] temp = new int[n];
```

Unlike the input, auxiliary memory is allocated by the algorithm itself.

This memory directly affects space complexity.

---

# The Call Stack

Recursive algorithms consume memory through the call stack.

Example:

```text
Function
        ↓
Recursive Call
        ↓
Recursive Call
        ↓
Recursive Call
```

Each recursive invocation creates a new stack frame.

Deep recursion therefore increases memory consumption.

---

# Memory Growth

Just as execution time grows with the input size, memory consumption may also increase.

Example:

```text
Small Input
        ↓
Small Memory Usage

Large Input
        ↓
Large Memory Usage
```

The objective is to determine **how memory grows**, not how many megabytes are used on a specific machine.

---

# In-Place Algorithms

Some algorithms modify the original data without allocating significant additional memory.

These are called **in-place algorithms**.

Example:

```text
Original Array
        ↓
Modified Directly
```

In-place algorithms are often preferred when memory is limited.

Examples include:

- Selection Sort;
- Insertion Sort;
- Heap Sort.

---

# Algorithms Requiring Additional Memory

Other algorithms intentionally allocate extra memory to improve performance.

Example:

```text
Original Array
        ↓
Temporary Array
        ↓
Merge Process
```

Merge Sort is a classic example.

Although it is faster than simpler algorithms, it requires additional auxiliary memory.

This illustrates one of the most common trade-offs in algorithm design.

---

# Time–Space Trade-Off

Many algorithms exchange one computational resource for another.

Example:

```text
More Memory
        ↓
Less Execution Time
```

or

```text
Less Memory
        ↓
More Execution Time
```

Choosing between these alternatives depends on the characteristics of the system.

Backend applications, embedded systems and high-performance computing may require different decisions.

---

# Space Complexity and Scalability

As datasets become larger, memory efficiency becomes increasingly important.

Example:

```text
Small Dataset
        ↓
Memory Usage Acceptable

Large Dataset
        ↓
Memory May Become a Limitation
```

Poor memory management reduces scalability even when execution time remains acceptable.

---

# Relationship with Time Complexity

Time and space complexity should always be analysed together.

```text
Algorithm
│
├── Time Complexity
│
└── Space Complexity
```

Neither metric alone completely describes algorithm efficiency.

Both contribute to engineering decisions.

---

# Relationship with Benchmarking

Benchmarking tools such as JMH primarily measure execution performance.

However, understanding memory consumption remains essential when interpreting benchmark results.

The relationship becomes:

```text
Theory
│
├── Time Complexity
│
└── Space Complexity

↓

Implementation

↓

Benchmark
```

Benchmarking validates execution performance.

Complexity analysis explains why that performance occurs.

---

# Relationship with Later Modules

Every major algorithm studied in this repository will include both time and space analysis.

Examples include:

```text
Searching
        ↓
Time + Space

Sorting
        ↓
Time + Space

Trees
        ↓
Time + Space

Graphs
        ↓
Time + Space

Dynamic Programming
        ↓
Time + Space
```

Understanding memory behaviour is therefore a permanent component of algorithm evaluation.

---

# Best Practices

When analysing space complexity:

- distinguish input memory from auxiliary memory;
- identify additional data structures created by the algorithm;
- analyse recursive stack usage;
- recognise opportunities for in-place processing;
- evaluate memory–time trade-offs;
- justify every conclusion regarding memory efficiency.

These practices encourage disciplined memory analysis and better software design.

---

# Key Takeaways

The learner should remember the following principles:

- Space complexity measures memory growth rather than memory size.
- Input memory and auxiliary memory are different concepts.
- Recursive algorithms consume stack memory.
- In-place algorithms minimise auxiliary memory usage.
- Some algorithms deliberately use additional memory to improve execution time.
- Memory efficiency is essential for scalability.
- Time and space complexity should always be analysed together.
- Every algorithm should include both temporal and spatial complexity analysis.

Space complexity therefore complements time complexity by providing the second fundamental dimension of algorithm analysis.

Together, they establish the analytical framework required before introducing **asymptotic notation**, where both execution time and memory consumption will be formally expressed using mathematical models.