# Algorithm Growth

## Algorithms Java Mastery

This document introduces the concept of **algorithm growth**, one of the fundamental ideas in computational complexity.

While asymptotic notation provides the mathematical language used to describe algorithms, algorithm growth explains **how the amount of computational work evolves as the input size increases**.

Understanding growth behaviour allows software engineers to predict scalability long before an application is deployed.

The central question addressed throughout this document is:

> **How does an algorithm behave as the amount of data becomes increasingly large?**

---

# Purpose

The purpose of this document is to develop an intuitive and mathematical understanding of algorithm growth.

Instead of focusing on execution time measured on a particular machine, algorithm growth studies how computational work changes relative to the size of the input.

The expected progression is:

```text
Problem
        ↓
Algorithm
        ↓
Growth Behaviour
        ↓
Scalability
        ↓
Algorithm Selection
```

Algorithm growth therefore becomes one of the principal criteria for selecting appropriate algorithms in Computer Science.

---

# Learning Objectives

After studying this document, the learner should be able to:

- explain what algorithm growth represents;
- compare different growth rates;
- recognise the scalability of an algorithm;
- identify common complexity classes;
- understand why small differences become significant for large datasets;
- relate algorithm growth to software engineering decisions.

These competencies establish the intuition required before analysing concrete algorithms.

---

# What Is Algorithm Growth?

Algorithm growth describes **how the amount of computational work increases as the size of the input grows**.

The focus is not on measuring seconds or milliseconds.

Instead, the objective is to understand the relationship between:

```text
Input Size
        ↓
Computational Work
```

This relationship determines the scalability of the algorithm.

---

# Why Growth Matters

Consider two algorithms solving exactly the same problem.

```text
Algorithm A
        ↓
Correct Result

Algorithm B
        ↓
Correct Result
```

Both are correct.

However, one algorithm may require significantly fewer operations as the amount of data increases.

The difference may be almost invisible for small inputs.

For very large datasets, however, the difference may become enormous.

---

# Growth and Scalability

Scalability describes the ability of an algorithm to remain efficient as the problem size increases.

The relationship becomes:

```text
Small Input
        ↓
Minor Differences

Large Input
        ↓
Major Differences
```

Choosing scalable algorithms is essential for modern software systems.

---

# Constant Growth

Constant growth represents algorithms whose computational work remains essentially unchanged regardless of input size.

Example:

```text
Input

10

↓

1 Operation

Input

1,000,000

↓

1 Operation
```

Characteristics:

- extremely efficient;
- highly scalable;
- independent of input size.

Examples:

- accessing an array by index;
- reading a variable.

---

# Logarithmic Growth

Logarithmic growth increases very slowly.

Each step reduces the remaining search space.

Example:

```text
1024

↓

512

↓

256

↓

128

↓

64
```

Characteristics:

- excellent scalability;
- common in divide-and-conquer algorithms;
- typical of balanced search structures.

Examples:

- Binary Search;
- AVL Trees;
- Red-Black Trees.

---

# Linear Growth

Linear growth means computational work increases proportionally with the input size.

Example:

```text
10 Elements

↓

10 Operations

100 Elements

↓

100 Operations
```

Characteristics:

- predictable;
- acceptable for many applications;
- common when every element must be inspected.

Examples:

- Linear Search;
- Array Traversal;
- Counting Elements.

---

# Linearithmic Growth

Linearithmic growth combines linear and logarithmic behaviour.

Example:

```text
n

↓

log n

↓

n log n
```

Characteristics:

- highly efficient;
- excellent scalability;
- widely used in efficient sorting algorithms.

Examples:

- Merge Sort;
- Heap Sort;
- Efficient Divide-and-Conquer Algorithms.

---

# Quadratic Growth

Quadratic growth usually appears when every element interacts with every other element.

Example:

```text
Outer Loop
        ↓
Inner Loop
```

Characteristics:

- acceptable for small datasets;
- poor scalability;
- rapidly increasing computational work.

Examples:

- Bubble Sort;
- Selection Sort;
- Simple comparison algorithms.

---

# Cubic Growth

Cubic growth often results from three nested iterations.

Example:

```text
Loop

↓

Loop

↓

Loop
```

Characteristics:

- very limited scalability;
- practical only for relatively small datasets.

Examples:

- Certain matrix algorithms;
- Brute-force graph algorithms.

---

# Exponential Growth

Exponential algorithms grow extremely quickly.

Each increase in input size dramatically increases computational work.

Example:

```text
2

↓

4

↓

8

↓

16

↓

32
```

Characteristics:

- extremely expensive;
- impractical for large inputs;
- common in exhaustive search.

Examples:

- naïve recursive Fibonacci;
- brute-force combinatorial search.

---

# Factorial Growth

Factorial growth is among the fastest growth rates encountered in Computer Science.

Example:

```text
3!

↓

6

4!

↓

24

5!

↓

120

10!

↓

3,628,800
```

Characteristics:

- explosive growth;
- extremely poor scalability;
- rarely practical for large problems.

Examples:

- permutation generation;
- travelling salesperson brute-force solutions.

---

# Comparing Growth Rates

The common complexity classes can be ordered from most scalable to least scalable.

```text
Constant
        ↓
Logarithmic
        ↓
Linear
        ↓
Linearithmic
        ↓
Quadratic
        ↓
Cubic
        ↓
Exponential
        ↓
Factorial
```

Each level represents a faster increase in computational work.

---

# Visual Interpretation

As input size increases:

```text
Input Size
        ↓
Algorithm Growth
        ↓
Execution Cost
```

Algorithms with slower growth remain efficient even for very large datasets.

Algorithms with faster growth quickly become impractical.

---

# Growth and Software Engineering

Understanding algorithm growth directly influences software engineering decisions.

Examples include:

- selecting data structures;
- designing scalable APIs;
- reducing infrastructure costs;
- improving backend performance;
- supporting larger user bases.

Algorithm growth therefore affects both code quality and system architecture.

---

# Relationship with Asymptotic Notation

Algorithm growth describes behaviour.

Asymptotic notation expresses that behaviour mathematically.

```text
Growth Behaviour
        ↓
Mathematical Representation
        ↓
Asymptotic Notation
```

The two concepts should always be studied together.

---

# Relationship with Benchmarking

Algorithm growth predicts long-term scalability.

Benchmarking validates implementation performance.

```text
Algorithm Growth
        ↓
Theory

JMH Benchmark
        ↓
Experiment
```

Theoretical growth explains why benchmark results behave as they do.

---

# Relationship with Later Modules

Every algorithm implemented in this repository will be classified according to its growth behaviour.

Examples include:

```text
Arrays
        ↓
Growth Analysis

Searching
        ↓
Growth Analysis

Sorting
        ↓
Growth Analysis

Trees
        ↓
Growth Analysis

Graphs
        ↓
Growth Analysis

Dynamic Programming
        ↓
Growth Analysis
```

Understanding growth becomes a permanent component of algorithm evaluation.

---

# Best Practices

When analysing algorithm growth:

- identify how computational work changes as input increases;
- compare growth rather than execution time;
- prioritise scalability over short-term performance;
- recognise the practical limitations of expensive growth rates;
- justify algorithm selection using growth behaviour.

These practices encourage objective and engineering-oriented decision making.

---

# Key Takeaways

The learner should remember the following principles:

- Algorithm growth describes how computational work evolves as input size increases.
- Scalability depends on growth behaviour rather than execution time.
- Constant and logarithmic algorithms are highly scalable.
- Linear algorithms are efficient for many practical applications.
- Linearithmic algorithms are ideal for many sorting problems.
- Quadratic and cubic algorithms should be used carefully for large datasets.
- Exponential and factorial algorithms quickly become impractical.
- Understanding growth is essential before implementing, optimising or benchmarking algorithms.

Algorithm growth therefore provides the intuitive foundation for understanding why some algorithms remain efficient as problems become larger while others rapidly become computationally infeasible.

This knowledge forms one of the central pillars of algorithm design and software engineering, supporting every subsequent module in the **Algorithms Java Mastery** repository.