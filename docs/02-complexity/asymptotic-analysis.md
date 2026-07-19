# Asymptotic Analysis

## Algorithms Java Mastery

This document introduces **asymptotic analysis**, the mathematical technique used to study how algorithm performance evolves as the size of the input grows.

Computational complexity is not concerned with measuring the execution time of a program on a specific computer.

Instead, it seeks to understand the **growth behaviour** of algorithms independently of hardware, operating systems, programming languages, or compiler optimisations.

Asymptotic analysis provides the theoretical framework that makes this objective comparison possible.

The central question addressed throughout this document is:

> **How does an algorithm behave as the input size becomes very large?**

---

# Purpose

The purpose of this document is to establish asymptotic analysis as the standard methodology for evaluating algorithm scalability.

Rather than focusing on execution time measured in seconds, asymptotic analysis studies how the amount of computational work increases as the input size grows.

The expected progression is:

```text
Algorithm
        ↓
Input Size
        ↓
Growth Behaviour
        ↓
Asymptotic Analysis
        ↓
Algorithm Comparison
```

This approach allows algorithms to be analysed objectively and independently of implementation details.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define asymptotic analysis;
- explain why input growth is more important than execution time;
- distinguish asymptotic analysis from benchmarking;
- understand why constants become less relevant as input size increases;
- explain the concept of scalability;
- compare algorithms based on growth behaviour;
- prepare for the study of asymptotic notation.

These competencies establish the mathematical mindset required for rigorous algorithm analysis.

---

# Why Execution Time Is Not Enough

Suppose two developers implement the same algorithm.

One executes it on:

- a high-end workstation;

the other executes it on:

- a five-year-old laptop.

The measured execution times will differ.

```text
Same Algorithm
        ↓
Different Hardware
        ↓
Different Execution Times
```

Does this mean the algorithms are different?

No.

The algorithm is identical.

Only the execution environment has changed.

Therefore, execution time alone cannot be used as an objective measure of algorithm efficiency.

---

# The Need for a Mathematical Model

Computer Science requires a way to compare algorithms independently of technology.

Instead of asking:

> "How many milliseconds does this algorithm require?"

Asymptotic analysis asks:

> **"How does the computational work grow when the problem becomes larger?"**

The comparison therefore becomes independent of:

- processor speed;
- available memory;
- operating system;
- JVM optimisations;
- compiler version.

---

# Input Size

The behaviour of an algorithm is analysed as a function of its input size.

The input size is traditionally represented by:

```text
n
```

Where:

```text
n
        ↓
Amount of Input Data
```

Examples include:

- number of array elements;
- number of graph vertices;
- number of tree nodes;
- number of records in a database;
- number of characters in a text.

The variable **n** becomes the reference point for analysing algorithm growth.

---

# Growth Behaviour

The primary concern of asymptotic analysis is not the current execution time.

It is the rate at which computational work increases.

Example:

```text
Input

10 elements

↓

100 operations

↓

Input

100 elements

↓

10,000 operations
```

The important observation is not the absolute number of operations.

The important observation is **how quickly those operations increase**.

---

# Why Large Inputs Matter

For small datasets, almost every algorithm appears to perform well.

The true differences emerge as the input size increases.

Example:

```text
Small Input
        ↓
Algorithms behave similarly.

Large Input
        ↓
Performance differences become significant.
```

Asymptotic analysis therefore studies algorithm behaviour in the limit of large inputs.

---

# Ignoring Constants

One of the most important ideas in asymptotic analysis is that constant factors become progressively less significant as the input grows.

Example:

Algorithm A

```text
5n
```

Algorithm B

```text
20n
```

Although Algorithm A performs fewer operations, both algorithms exhibit the same growth behaviour.

Asymptotic analysis therefore focuses on the dominant growth rather than constant multipliers.

This principle will later justify the definition of **Big O notation**.

---

# Ignoring Lower-Order Terms

Expressions often contain multiple terms.

Example:

```text
n² + 8n + 15
```

For small values of **n**, every term contributes.

For very large values of **n**, the quadratic term dominates.

The progression becomes:

```text
Small Input
        ↓
All Terms Matter

Large Input
        ↓
Dominant Term Determines Growth
```

This simplification allows algorithms to be classified according to their long-term behaviour.

---

# Scalability

Scalability describes how efficiently an algorithm continues to operate as the problem size increases.

The relationship becomes:

```text
Input Size
        ↓
Growth Rate
        ↓
Scalability
```

Algorithms with slower growth rates generally scale better.

Scalability is one of the most important criteria in modern software engineering.

---

# Theoretical Analysis vs Experimental Measurement

Asymptotic analysis and benchmarking answer different questions.

Asymptotic analysis asks:

```text
How does the algorithm grow?
```

Benchmarking asks:

```text
How fast is this implementation on this machine?
```

The relationship is:

```text
Theory
        ↓
Asymptotic Analysis

Practice
        ↓
JMH Benchmark
```

Both perspectives are necessary for a complete understanding of algorithm performance.

---

# Relationship with Computational Complexity

Computational complexity defines **what** should be measured.

Asymptotic analysis explains **how** it should be measured.

The progression becomes:

```text
Computational Complexity
        ↓
Asymptotic Analysis
        ↓
Time Complexity
        ↓
Space Complexity
```

This document therefore serves as the bridge between general complexity theory and specific complexity metrics.

---

# Relationship with Later Modules

The concepts introduced here will be applied throughout every algorithm studied in the repository.

```text
Asymptotic Analysis
        ↓
Searching

Asymptotic Analysis
        ↓
Sorting

Asymptotic Analysis
        ↓
Trees

Asymptotic Analysis
        ↓
Graphs

Asymptotic Analysis
        ↓
Dynamic Programming
```

Every future algorithm will be analysed according to its asymptotic behaviour before implementation details are considered.

---

# Best Practices

When analysing an algorithm:

- focus on growth rather than execution time;
- compare algorithms independently of hardware;
- identify the dominant growth behaviour;
- avoid drawing conclusions from small datasets alone;
- distinguish theoretical analysis from empirical measurement;
- consider scalability as a primary quality attribute.

These principles encourage disciplined and objective algorithm evaluation.

---

# Key Takeaways

The learner should remember the following principles:

- Asymptotic analysis studies algorithm growth rather than execution time.
- Input size is the central variable in algorithm analysis.
- Large inputs reveal the true differences between algorithms.
- Constant factors become less relevant as input size increases.
- Lower-order terms do not determine long-term growth.
- Scalability depends on asymptotic behaviour.
- Asymptotic analysis complements benchmarking rather than replacing it.
- Objective algorithm comparison requires mathematical reasoning.

Asymptotic analysis therefore provides the conceptual bridge between **computational complexity** and the formal notation used throughout Computer Science.

It prepares the learner for the study of **time complexity**, **space complexity**, and the asymptotic notations that will become the standard language for analysing algorithms throughout the **Algorithms Java Mastery** repository.