# Benchmarking Sorting Algorithms

## Purpose

Theoretical complexity analysis provides a mathematical model for estimating algorithm growth. However, algorithms with the same asymptotic complexity may exhibit significantly different execution times in practice due to implementation details, hardware characteristics, compiler optimizations, memory hierarchy, and input distributions.

The purpose of this document is to introduce **benchmarking** as the experimental methodology used to evaluate the practical performance of sorting algorithms.

Rather than replacing complexity analysis, benchmarking complements it by answering an important engineering question:

> **How does an algorithm actually perform on a real computer?**

This document establishes the principles of reliable benchmarking and prepares the reader for experimental evaluation using Java.

---

# Learning Objectives

After studying this document, the reader should be able to:

- understand the purpose of algorithm benchmarking;
- distinguish theoretical analysis from experimental analysis;
- identify factors that influence execution time;
- design fair benchmark experiments;
- understand benchmarking pitfalls;
- compare sorting algorithms using reproducible methodologies;
- recognize the role of Java Microbenchmark Harness (JMH);
- interpret benchmark results from an engineering perspective.

---

# Why Benchmark Algorithms?

Complexity analysis predicts how an algorithm grows as the input size increases.

For example:

| Algorithm | Complexity |
|-----------|------------|
| Merge Sort | O(n log n) |
| Quick Sort | O(n log n) |

Although both algorithms belong to the same asymptotic class, they may produce different execution times under identical conditions.

Benchmarking measures these differences experimentally.

---

# Theoretical Analysis versus Experimental Analysis

Theoretical analysis and benchmarking answer different questions.

| Theoretical Analysis | Benchmarking |
|----------------------|--------------|
| Mathematical | Experimental |
| Independent of hardware | Hardware dependent |
| Describes growth | Measures execution time |
| Uses asymptotic notation | Uses real measurements |
| Ignores implementation constants | Includes implementation details |

Both approaches are complementary.

---

# The Goal of Benchmarking

Benchmarking aims to measure performance under controlled conditions.

The objective is not simply to identify the fastest algorithm, but to understand:

- how performance changes with input size;
- how input distribution affects execution;
- how memory usage influences runtime;
- how implementation choices impact efficiency;
- whether theoretical expectations match observed behaviour.

---

# Factors Affecting Performance

Execution time depends on many variables.

## Algorithm

Different algorithmic strategies perform different numbers of operations.

---

## Input Size

Larger datasets generally require more computation.

Example:

```text
100 elements
↓

1,000 elements
↓

10,000 elements
↓

100,000 elements
```

---

## Input Distribution

Performance varies depending on whether the input is:

- sorted;
- reverse sorted;
- random;
- nearly sorted;
- duplicate-heavy.

---

## Hardware

Execution time depends on:

- processor architecture;
- clock frequency;
- cache size;
- memory bandwidth.

Different machines may produce different results.

---

## JVM Optimizations

Java programs execute inside the Java Virtual Machine.

The JVM performs:

- Just-In-Time (JIT) compilation;
- method inlining;
- dead-code elimination;
- escape analysis;
- other runtime optimizations.

These optimizations significantly influence benchmark results.

---

# Why Simple Timing Is Misleading

A common beginner approach is:

```text
long start = System.nanoTime();

sort(array);

long end = System.nanoTime();
```

Although this appears reasonable, it does not produce reliable benchmarks.

Possible problems include:

- JVM warm-up;
- garbage collection;
- operating system scheduling;
- compiler optimizations;
- measurement overhead.

Professional benchmarking requires more sophisticated methodologies.

---

# Benchmarking Principles

A good benchmark should be:

- reproducible;
- isolated;
- repeatable;
- statistically meaningful;
- fair.

These principles reduce measurement noise and improve result reliability.

---

# Fair Comparisons

Every algorithm must receive identical input.

Incorrect experiment:

```text
Selection Sort

↓

Bubble Sort on the already sorted output
```

Correct experiment:

```text
Original Dataset

↓

Copy Dataset

↓

Run Algorithm
```

Each algorithm must sort an independent copy of the same dataset.

---

# Multiple Executions

Single executions are unreliable.

Instead, algorithms should be executed repeatedly.

```text
Run 1

Run 2

Run 3

...

Run N
```

The collected measurements allow computation of:

- average time;
- minimum time;
- maximum time;
- standard deviation.

---

# Warm-Up

Java applications become faster after the JVM optimizes frequently executed code.

Therefore, benchmark execution is divided into two phases:

```text
Warm-Up

↓

Measurement
```

Warm-up executions are discarded.

Only measurement iterations contribute to the reported results.

---

# Input Generation

Reliable benchmarking requires well-defined datasets.

Common input categories include:

## Random Arrays

Representative of general-purpose workloads.

---

## Sorted Arrays

Useful for adaptive algorithms.

---

## Reverse-Sorted Arrays

Often represent worst-case scenarios.

---

## Nearly Sorted Arrays

Useful for evaluating insertion-based algorithms.

---

## Duplicate-Heavy Arrays

Important for studying partition behaviour.

---

# Choosing Input Sizes

Input sizes should increase progressively.

Example:

| Dataset | Elements |
|----------|---------:|
| Small | 100 |
| Medium | 1,000 |
| Large | 10,000 |
| Very Large | 100,000 |
| Massive | 1,000,000 |

This progression illustrates scalability.

---

# Measuring Time

Benchmarking typically reports:

- nanoseconds;
- microseconds;
- milliseconds;
- seconds.

The appropriate unit depends on the experiment.

---

# Beyond Execution Time

Benchmarking may also measure:

- memory consumption;
- allocation rate;
- garbage collection activity;
- throughput;
- latency.

Execution time is only one aspect of performance.

---

# Java Microbenchmark Harness (JMH)

Java Microbenchmark Harness (JMH) is the standard benchmarking framework for Java.

It was developed by the OpenJDK project specifically for writing reliable microbenchmarks.

JMH automatically manages:

- warm-up iterations;
- measurement iterations;
- JVM optimizations;
- statistical analysis;
- benchmark isolation.

Using JMH is strongly recommended over manual timing.

---

# Benchmark Workflow

A typical benchmark follows these steps:

```text
Generate Dataset

↓

Copy Dataset

↓

Warm-Up

↓

Execute Benchmark

↓

Collect Measurements

↓

Repeat

↓

Compute Statistics

↓

Interpret Results
```

---

# Example Benchmark Design

Suppose we want to compare:

- Selection Sort;
- Insertion Sort;
- Merge Sort;
- Quick Sort.

Each algorithm receives:

- identical random arrays;
- identical sorted arrays;
- identical reverse-sorted arrays.

Input sizes:

- 100;
- 1,000;
- 10,000;
- 100,000.

Measurements are repeated multiple times before statistical analysis.

---

# Interpreting Results

Benchmark results should not be interpreted solely by the fastest execution.

Instead, engineers should consider:

- consistency;
- scalability;
- variability;
- memory usage;
- implementation complexity.

Performance is multidimensional.

---

# Common Benchmarking Mistakes

Common errors include:

- benchmarking only once;
- using different datasets;
- ignoring JVM warm-up;
- measuring object creation instead of the algorithm;
- comparing algorithms with different implementations;
- drawing conclusions from insufficient data.

These mistakes may invalidate the experiment.

---

# Engineering Perspective

Professional software engineers rarely choose algorithms based solely on theoretical complexity or isolated benchmark numbers.

Instead, they combine:

- mathematical analysis;
- controlled experiments;
- domain knowledge;
- production requirements.

Benchmarking transforms theoretical understanding into evidence-based engineering decisions.

---

# Relationship with Previous Documents

Previous documents introduced:

- sorting algorithms;
- computational complexity;
- algorithm comparison;
- Java sorting framework;
- testing strategies.

This chapter complements those topics through experimental evaluation.

---

# Relationship with Future Documents

Future modules will apply benchmarking to additional algorithms and data structures.

The methodology introduced here will also be reused when evaluating searching algorithms, graph algorithms, and dynamic programming implementations.

---

# Key Takeaways

- Complexity analysis predicts algorithm growth.
- Benchmarking measures real execution.
- Fair benchmarking requires identical experimental conditions.
- JVM optimizations influence performance.
- JMH is the recommended framework for Java benchmarking.
- Multiple executions produce more reliable measurements.
- Experimental analysis complements theoretical analysis.
- Engineering decisions should combine mathematics and experimentation.

---

# Next Document

```text
14-common-sorting-interview-problems.md
```

The next document explores common sorting problems encountered in technical interviews, focusing on algorithm selection, reasoning, optimization, and practical problem-solving strategies.