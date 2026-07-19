# Best, Average and Worst Case Analysis

## Algorithms Java Mastery

This document introduces **case analysis**, a fundamental technique in algorithm analysis used to evaluate how an algorithm behaves under different input conditions.

The execution cost of an algorithm is not always the same.

Different inputs may produce different numbers of operations, even when the algorithm itself remains unchanged.

Case analysis allows software engineers to understand the complete performance profile of an algorithm rather than relying on a single complexity value.

The central question addressed throughout this document is:

> **How does the behaviour of an algorithm change depending on its input?**

---

# Purpose

The purpose of this document is to explain how algorithm efficiency varies according to different execution scenarios.

Rather than assigning a single complexity value to an algorithm, case analysis evaluates multiple possible situations.

The expected progression is:

```text
Algorithm
        ↓
Different Inputs
        ↓
Different Execution Costs
        ↓
Case Analysis
```

Understanding these scenarios leads to more accurate complexity analysis and better engineering decisions.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define best-case analysis;
- define average-case analysis;
- define worst-case analysis;
- explain why different inputs affect performance;
- distinguish theoretical guarantees from expected behaviour;
- interpret complexity values correctly;
- analyse algorithms under multiple execution scenarios.

These competencies strengthen the ability to evaluate algorithms rigorously.

---

# Why Case Analysis Is Necessary

Consider an algorithm that searches for a value inside an array.

If the desired value is located in the first position:

```text
Target

↓

First Element

↓

Search Stops
```

If the value is located in the last position:

```text
Target

↓

Last Element

↓

Entire Array Examined
```

The same algorithm performs a different amount of work.

This illustrates why multiple execution scenarios must be analysed.

---

# Best Case

The **best case** represents the most favourable input for the algorithm.

It answers:

> **What is the minimum amount of work the algorithm may perform?**

Characteristics:

- minimum execution cost;
- ideal input conditions;
- optimistic scenario.

Example:

Linear Search:

```text
Target

↓

First Element

↓

1 Comparison
```

The algorithm terminates immediately.

---

# Average Case

The **average case** represents the expected execution cost across a large number of typical inputs.

It answers:

> **How does the algorithm usually behave?**

Characteristics:

- expected behaviour;
- depends on assumptions about the input;
- often more difficult to analyse mathematically.

Average-case analysis frequently relies on probability theory and statistical assumptions.

---

# Worst Case

The **worst case** represents the maximum amount of work an algorithm may perform.

It answers:

> **What is the greatest computational cost the algorithm can require?**

Characteristics:

- maximum execution cost;
- guaranteed upper limit;
- commonly used in software engineering.

Example:

Linear Search:

```text
Target

↓

Last Element

↓

Entire Array Traversed
```

The algorithm must inspect every element before terminating.

---

# Why the Worst Case Is Widely Used

Software engineers often prefer worst-case analysis because it provides a performance guarantee.

Knowing the maximum possible execution cost helps design systems that remain reliable under heavy workloads.

Examples include:

- backend services;
- financial systems;
- healthcare platforms;
- real-time applications.

Predictable performance is often more valuable than optimistic performance.

---

# Case Analysis Example

Consider a Linear Search.

Input:

```text
[7, 12, 18, 25, 30]
```

Searching for:

```text
7
```

Best case:

```text
1 Comparison
```

Searching for:

```text
18
```

Average behaviour:

```text
Approximately Half the Array
```

Searching for:

```text
30
```

Worst case:

```text
Entire Array
```

Although the algorithm remains the same, the computational work changes.

---

# Different Algorithms, Different Cases

Not every algorithm has significantly different cases.

For example:

Binary Search

```text
Best
↓

Target in Middle

Worst
↓

Repeated Halving
```

Both cases remain efficient.

In contrast:

Quick Sort

```text
Balanced Partitions
↓

Excellent Performance

Unbalanced Partitions
↓

Poor Performance
```

Understanding these differences is essential when selecting algorithms.

---

# Relationship with Asymptotic Notation

Case analysis and asymptotic notation complement each other.

Example:

```text
Best Case
        ↓
Big Ω

Average Case
        ↓
Often Big Θ

Worst Case
        ↓
Big O
```

These relationships will be applied throughout the repository.

---

# Relationship with Scalability

Scalability depends not only on growth rate but also on which execution scenario is analysed.

A highly efficient average case may still hide an unacceptable worst case.

Engineering decisions should therefore consider all relevant scenarios.

---

# Relationship with Benchmarking

Benchmarking should evaluate multiple datasets rather than a single execution.

Example:

```text
Random Input

↓

Benchmark

Sorted Input

↓

Benchmark

Reverse Input

↓

Benchmark
```

Testing different scenarios provides a more realistic understanding of algorithm behaviour.

---

# Relationship with Later Modules

Every major algorithm studied in this repository will include explicit case analysis.

Examples include:

```text
Linear Search

↓

Best / Average / Worst

Binary Search

↓

Best / Average / Worst

Bubble Sort

↓

Best / Average / Worst

Quick Sort

↓

Best / Average / Worst

Hash Tables

↓

Average / Worst

Graphs

↓

Case Analysis
```

Case analysis becomes a standard component of every algorithm evaluation.

---

# Common Misconceptions

Several misconceptions frequently appear in introductory algorithm courses.

Misconception:

> "Big O is the complexity of the algorithm."

Correction:

Big O usually describes the upper asymptotic bound.

The algorithm may also have:

- a lower bound;
- a tight bound;
- different execution scenarios.

---

Misconception:

> "Worst case always happens."

Correction:

Worst-case analysis describes the maximum possible cost.

Many executions perform significantly better.

---

Misconception:

> "Average case is simply halfway between best and worst."

Correction:

Average-case analysis depends on the probability distribution of the inputs.

It is not obtained by taking a numerical average.

---

# Best Practices

When analysing an algorithm:

- identify the best-case scenario;
- identify the average-case scenario;
- identify the worst-case scenario;
- explain why each scenario occurs;
- avoid reporting only one complexity value without context;
- justify engineering decisions using the most appropriate scenario.

These practices produce more complete and rigorous algorithm analyses.

---

# Key Takeaways

The learner should remember the following principles:

- The same algorithm may exhibit different execution costs.
- Best case represents the minimum computational work.
- Average case represents the expected behaviour.
- Worst case represents the maximum computational work.
- Worst-case analysis provides important performance guarantees.
- Average-case analysis often requires probabilistic reasoning.
- Case analysis complements asymptotic notation.
- Every algorithm should be analysed under the scenarios that are relevant to its practical use.

Case analysis therefore provides a more complete understanding of algorithm behaviour, allowing software engineers to evaluate performance under realistic conditions and make informed architectural decisions throughout the **Algorithms Java Mastery** repository.