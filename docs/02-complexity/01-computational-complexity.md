# Computational Complexity

## Algorithms Java Mastery

This document introduces the concept of **computational complexity**, one of the fundamental disciplines of Computer Science.

Algorithms are not evaluated solely by whether they produce the correct result.

They are also evaluated by **how efficiently they use computational resources**.

Computational complexity provides a mathematical framework for analysing algorithm efficiency independently of hardware, operating systems, or programming languages.

The central question addressed throughout this document is:

> **How can the efficiency of an algorithm be objectively measured?**

---

# Purpose

The purpose of this document is to establish the theoretical foundations of computational complexity.

Instead of comparing execution times on a particular computer, computational complexity studies how algorithms behave as the size of the input increases.

The expected progression is:

```text
Algorithm
        ↓
Resource Consumption
        ↓
Complexity Analysis
        ↓
Algorithm Comparison
```

Complexity therefore becomes a scientific method for evaluating algorithms rather than a measurement of execution speed.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define computational complexity;
- identify the computational resources consumed by algorithms;
- explain why efficiency matters;
- distinguish correctness from efficiency;
- understand why complexity analysis is hardware independent;
- recognise the relationship between complexity and scalability;
- explain why theoretical analysis precedes benchmarking.

These competencies provide the analytical foundation required for every subsequent algorithm studied in this repository.

---

# What Is Computational Complexity?

Computational complexity is the study of **how many computational resources an algorithm requires to solve a problem**.

Rather than measuring seconds or milliseconds, complexity measures how resource consumption changes as the input size grows.

The two principal resources are:

- execution time;
- memory consumption.

The relationship can be represented as:

```text
Algorithm
      ↓
Execution Time

Algorithm
      ↓
Memory Usage
```

These two dimensions define the efficiency of an algorithm.

---

# Why Computational Complexity Matters

Different algorithms can solve the same problem while consuming very different amounts of computational resources.

Consider two algorithms that both locate an element in a collection.

```text
Algorithm A
        ↓
Correct Result

Algorithm B
        ↓
Correct Result
```

Although both algorithms are correct, one may require significantly fewer operations.

Complexity analysis allows these algorithms to be compared objectively.

---

# Correctness vs Efficiency

Correctness and efficiency are different properties.

An algorithm should first be correct.

Only then should its efficiency be analysed.

The relationship becomes:

```text
Problem
      ↓
Correctness
      ↓
Efficiency
```

An incorrect algorithm has no practical value regardless of how fast it executes.

---

# Computational Resources

Algorithms consume different computational resources during execution.

The most important are:

```text
Algorithm
│
├── Time
│
└── Memory
```

These resources are analysed independently because improving one may increase the consumption of the other.

---

# Hardware Independence

A fundamental objective of computational complexity is to compare algorithms independently of the computer used.

For example:

```text
Computer A

Fast Processor

↓

Execution Time

Computer B

Slow Processor

↓

Execution Time
```

Directly comparing execution times would produce misleading conclusions.

Complexity analysis instead studies how the number of operations grows as the input size increases.

This makes comparisons objective and reproducible.

---

# Input Size

Complexity analysis studies how algorithms behave when the input becomes larger.

The input size is commonly represented by:

```text
n
```

Where:

```text
n

↓

Number of elements

↓

Problem Size
```

The value of **n** becomes the primary variable throughout algorithm analysis.

---

# Growth Rather Than Time

Complexity does not measure execution time directly.

Instead, it studies the growth of computational work.

For example:

```text
Small Input

↓

Few Operations

↓

Large Input

↓

Many Operations
```

The important question is not:

> "How long does the algorithm take today?"

Instead, it is:

> **"How does the algorithm behave as the problem becomes larger?"**

---

# Scalability

Scalability describes the ability of an algorithm to remain efficient as the input size increases.

The relationship becomes:

```text
Input Size
        ↓
Algorithm Growth
        ↓
Scalability
```

Highly scalable algorithms remain practical even for very large datasets.

Poorly scalable algorithms become increasingly inefficient.

---

# Complexity Throughout This Repository

Every algorithm implemented in this repository will be analysed from two complementary perspectives.

```text
Correctness
        ↓
Complexity
        ↓
Implementation
        ↓
Testing
        ↓
Benchmarking
```

Complexity analysis therefore becomes a permanent component of algorithm development.

---

# Complexity and Benchmarking

Complexity analysis and benchmarking answer different questions.

Complexity asks:

```text
How does the algorithm grow?
```

Benchmarking asks:

```text
How fast does the implementation execute on this machine?
```

The relationship is:

```text
Theoretical Analysis
        ↓
Complexity

Experimental Analysis
        ↓
JMH Benchmark
```

Neither replaces the other.

Both are necessary.

---

# Complexity and Software Engineering

Computational complexity influences many software engineering decisions.

Examples include:

- selecting appropriate data structures;
- choosing search algorithms;
- designing scalable systems;
- optimising backend services;
- reducing infrastructure costs.

Understanding complexity therefore extends beyond algorithm design.

It directly affects software architecture and system performance.

---

# Relationship with Later Modules

The concepts introduced here will be applied throughout the repository.

```text
Computational Complexity
        ↓
Searching

Computational Complexity
        ↓
Sorting

Computational Complexity
        ↓
Trees

Computational Complexity
        ↓
Graphs

Computational Complexity
        ↓
Dynamic Programming
```

Every future algorithm will include a formal complexity analysis before experimental evaluation.

---

# Best Practices

When analysing an algorithm:

- verify correctness before analysing efficiency;
- identify the computational resources consumed;
- analyse growth rather than execution time;
- compare algorithms using objective criteria;
- distinguish theoretical analysis from benchmarking;
- consider scalability during algorithm selection.

Following these principles encourages disciplined and scientifically grounded algorithm analysis.

---

# Key Takeaways

The learner should remember the following principles:

- Computational complexity studies algorithm efficiency.
- Efficiency is measured through resource consumption.
- Time and memory are the primary computational resources.
- Correctness always precedes efficiency.
- Complexity analysis is independent of hardware.
- Scalability is determined by algorithm growth.
- Complexity analysis complements experimental benchmarking.
- Every algorithm should be evaluated both theoretically and experimentally.

Computational complexity therefore provides the mathematical language used to compare algorithms objectively.

It establishes the analytical foundation for asymptotic analysis, time complexity, space complexity, algorithm growth, and benchmarking, becoming one of the central pillars of Computer Science and the **Algorithms Java Mastery** repository.