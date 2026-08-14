# Time Complexity

## Algorithms Java Mastery

This document introduces **time complexity**, the branch of computational complexity that studies how the amount of computational work performed by an algorithm grows as the input size increases.

Time complexity does **not** measure the execution time shown by a clock.

Instead, it analyses the **number of elementary operations** an algorithm performs relative to the size of its input.

The central question addressed throughout this document is:

> **How much computational work does an algorithm perform as the problem becomes larger?**

---

# Purpose

The purpose of this document is to establish a rigorous methodology for analysing algorithm execution cost.

Instead of relying on empirical execution times, time complexity provides a mathematical model for estimating the amount of work required to solve a problem.

The expected progression is:

```text
Algorithm
        ↓
Elementary Operations
        ↓
Operation Count
        ↓
Growth Rate
        ↓
Time Complexity
```

This methodology allows algorithms to be compared independently of hardware and implementation details.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define time complexity;
- identify elementary operations;
- estimate the execution cost of iterative algorithms;
- analyse nested loops;
- recognise the effect of recursion on execution time;
- distinguish theoretical cost from execution time;
- prepare for asymptotic notation.

These competencies establish the analytical skills required for algorithm evaluation.

---

# What Is Time Complexity?

Time complexity describes how the computational work performed by an algorithm changes as the input size grows.

It does **not** answer:

> "How many milliseconds does this algorithm take?"

Instead, it answers:

> **"How many operations does this algorithm perform?"**

The analysis therefore focuses on computational work rather than physical time.

---

# Computational Work

Every algorithm is composed of elementary operations.

Examples include:

- comparisons;
- assignments;
- arithmetic operations;
- logical operations;
- array accesses;
- method calls.

Example:

```texte
int max = numbers[0];

for (int number : numbers) {
    if (number > max) {
        max = number;
    }
}
```

The objective is not to measure milliseconds.

The objective is to estimate how many comparisons and assignments are executed.

---

# Why Count Operations?

Execution time depends on many external factors.

Examples include:

- processor architecture;
- JVM implementation;
- compiler optimisations;
- operating system;
- memory hierarchy;
- background processes.

Operation counting eliminates these variables.

Instead of analysing hardware performance, we analyse algorithm behaviour.

---

# Input Size

The computational work depends on the size of the input.

The input size is represented by:

```text
n
```

Where **n** may represent:

- number of array elements;
- number of tree nodes;
- number of graph vertices;
- number of records;
- number of characters.

Every complexity analysis expresses computational work as a function of **n**.

---

# Constant-Time Operations

Some operations require approximately the same amount of work regardless of input size.

Example:

```java
int first = numbers[0];
```

Regardless of whether the array contains:

- 10 elements;
- 100 elements;
- 1,000,000 elements;

the operation remains essentially constant.

---

# Linear Growth

Consider the following example:

```texte
for (int number : numbers) {
    System.out.println(number);
}
```

If the array doubles in size, the loop performs approximately twice as many iterations.

The computational work therefore grows proportionally with **n**.

---

# Nested Loops

Now consider:

```text
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {

    }
}
```

Each iteration of the outer loop executes the complete inner loop.

The progression becomes:

```text
Outer Loop
        ↓
Inner Loop
        ↓
Repeated Work
```

The amount of computational work increases much faster than in a single loop.

Nested iterations are one of the principal causes of higher time complexity.

---

# Recursive Algorithms

Execution cost is not limited to iterative algorithms.

Recursive algorithms also generate computational work.

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

Each recursive invocation contributes to the total execution cost.

Analysing recursive algorithms often requires recurrence relations, which will be introduced in later modules.

---

# Best, Average and Worst Behaviour

An algorithm may not always execute the same number of operations.

Different inputs may produce different execution costs.

For example:

```text
Input A
        ↓
Few Operations

Input B
        ↓
Many Operations
```

Later in this module, these scenarios will be formalised as:

- best case;
- average case;
- worst case.

---

# Time Complexity vs Execution Time

These concepts should never be confused.

Time complexity describes:

```text
Mathematical Growth
```

Execution time measures:

```text
Real Execution
```

Execution time depends on the machine.

Time complexity depends only on the algorithm.

---

# Relationship with Benchmarking

Theoretical analysis and benchmarking complement one another.

```text
Time Complexity
        ↓
Predicts Growth

JMH Benchmark
        ↓
Measures Performance
```

Time complexity predicts scalability.

JMH validates implementation performance experimentally.

---

# Relationship with Later Modules

Every algorithm implemented in this repository will include a time complexity analysis.

Examples include:

```text
Linear Search
        ↓
Time Complexity

Merge Sort
        ↓
Time Complexity

Binary Search
        ↓
Time Complexity

AVL Trees
        ↓
Time Complexity

Dijkstra
        ↓
Time Complexity
```

Understanding execution cost becomes a permanent part of algorithm development.

---

# Best Practices

When analysing time complexity:

- identify elementary operations;
- determine how often each operation executes;
- relate execution count to the input size;
- distinguish loops from recursive calls;
- analyse the algorithm before measuring performance;
- justify every complexity conclusion.

These practices encourage disciplined and reproducible analysis.

---

# Key Takeaways

The learner should remember the following principles:

- Time complexity measures computational work rather than physical time.
- Elementary operations form the basis of algorithm analysis.
- Input size determines execution growth.
- Loop structure strongly influences execution cost.
- Recursive calls contribute to computational work.
- Time complexity is independent of hardware.
- Benchmarking complements, but does not replace, theoretical analysis.
- Every algorithm should include an explicit time complexity evaluation.

Time complexity therefore provides the mathematical foundation required to evaluate algorithm efficiency and prepares the learner for the formal study of **Big O**, **Big Θ**, and **Big Ω**, which will be introduced in the following documents.