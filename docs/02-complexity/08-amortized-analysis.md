# Amortized Analysis

## Algorithms Java Mastery

This document introduces **amortized analysis**, a mathematical technique used to evaluate the average cost of a sequence of operations performed by an algorithm.

Unlike average-case analysis, amortized analysis does **not** rely on probability.

Instead, it studies how expensive operations are distributed across many inexpensive ones.

Many data structures used in modern software engineering, including Java collections, achieve excellent practical performance because of amortized behaviour.

The central question addressed throughout this document is:

> **How can an algorithm remain efficient even when some individual operations are expensive?**

---

# Purpose

The purpose of this document is to establish amortized analysis as a complementary technique to asymptotic analysis.

Rather than analysing operations independently, amortized analysis evaluates the total computational cost of an entire sequence of operations.

The expected progression is:

```text
Sequence of Operations
        ↓
Total Computational Cost
        ↓
Average Cost per Operation
        ↓
Amortized Complexity
```

This methodology provides a more realistic evaluation of many dynamic algorithms and data structures.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define amortized analysis;
- distinguish amortized analysis from average-case analysis;
- explain why occasional expensive operations do not necessarily reduce efficiency;
- recognise algorithms that exhibit amortized behaviour;
- understand the role of dynamic resizing in modern data structures;
- interpret amortized complexity correctly.

These competencies extend the learner's understanding of algorithm analysis beyond individual operations.

---

# What Is Amortized Analysis?

Amortized analysis studies the cost of an entire sequence of operations rather than evaluating each operation independently.

The objective is to determine the average computational cost per operation over time.

It answers the following question:

> **How expensive is each operation when the total cost is distributed across the complete sequence?**

---

# Why Is It Necessary?

Some algorithms occasionally perform very expensive operations.

However, these operations occur so infrequently that their overall impact becomes negligible.

Example:

```text
Operation

↓

Cheap

↓

Cheap

↓

Cheap

↓

Expensive

↓

Cheap

↓

Cheap

↓

Cheap
```

Looking only at the expensive operation may lead to an incorrect conclusion about the algorithm's efficiency.

Amortized analysis evaluates the complete execution.

---

# Amortized Analysis vs Average-Case Analysis

These concepts are often confused, but they answer different questions.

Average-case analysis studies the expected behaviour under probabilistic assumptions.

Amortized analysis studies the cost of a sequence of operations without using probability.

The relationship is:

```text
Average Case
        ↓
Probability Required

Amortized Analysis
        ↓
No Probability Required
```

This distinction is fundamental in algorithm analysis.

---

# Example: Dynamic Array

Consider a dynamic array such as Java's `ArrayList`.

Initially:

```text
Capacity

↓

4 Elements
```

Insertions occur normally.

```text
Insert

↓

Insert

↓

Insert

↓

Insert
```

When the array becomes full:

```text
Capacity Reached

↓

Allocate Larger Array

↓

Copy Existing Elements

↓

Continue
```

The resizing operation is expensive.

However, it occurs only occasionally.

Most insertions require constant computational work.

---

# Total Cost Perspective

Suppose one hundred insertions are performed.

```text
100 Insertions
```

Only a few require resizing.

The total computational cost is distributed across all operations.

Instead of analysing the expensive operations individually, amortized analysis evaluates:

```text
Total Cost

↓

100 Operations

↓

Average Cost per Operation
```

This provides a more accurate description of the algorithm's long-term behaviour.

---

# Why Expensive Operations Are Acceptable

Occasional expensive operations do not necessarily reduce scalability.

If the expensive operation is sufficiently rare, the overall cost remains low.

The algorithm therefore continues to perform efficiently despite isolated peaks in computational work.

---

# Typical Examples

Many classical data structures exhibit amortized behaviour.

Examples include:

- Java ArrayList
- Dynamic arrays
- Stack implementations using dynamic arrays
- Hash tables during resizing
- Dynamic buffers

These structures are widely used because their amortized performance is excellent.

---

# Amortized Complexity in Java

One of the most important examples for Java developers is:

```java
ArrayList.add(element);
```

Most insertions execute in constant time.

Occasionally, a resize operation occurs.

Therefore:

```text
Single Operation

↓

Sometimes Expensive

Long Sequence

↓

Amortized Constant Time
```

This explains why `ArrayList` performs efficiently in practice despite occasional reallocations.

---

# Relationship with Time Complexity

Time complexity analyses the computational work performed by algorithms.

Amortized analysis complements this perspective by studying sequences of operations.

```text
Time Complexity

↓

Individual Operations

Amortized Analysis

↓

Operation Sequences
```

Both techniques contribute to a complete understanding of algorithm performance.

---

# Relationship with Algorithm Growth

Amortized analysis does not replace growth analysis.

Instead, it explains why certain algorithms maintain favourable growth despite occasional expensive operations.

```text
Growth Behaviour

↓

Occasional Peaks

↓

Stable Long-Term Performance
```

---

# Relationship with Benchmarking

Benchmarking often reveals isolated execution spikes.

Amortized analysis explains why these spikes usually do not affect overall scalability.

The relationship becomes:

```text
Benchmark

↓

Performance Peaks

↓

Amortized Analysis

↓

Mathematical Explanation
```

---

# Relationship with Later Modules

Amortized analysis will reappear in several repository modules.

Examples include:

```text
Arrays
        ↓
Dynamic Arrays

Hashing
        ↓
Hash Table Resizing

Stacks
        ↓
Dynamic Capacity

Collections Framework
        ↓
ArrayList
```

Understanding amortized behaviour will improve the learner's ability to analyse real-world software libraries.

---

# Common Misconceptions

Misconception:

> "Every expensive operation means the algorithm is inefficient."

Correction:

A costly operation may occur so infrequently that the overall algorithm remains highly efficient.

---

Misconception:

> "Amortized analysis is the same as average-case analysis."

Correction:

Average-case analysis depends on probability.

Amortized analysis depends on the total cost of a sequence of operations.

---

Misconception:

> "ArrayList.add() always runs in constant time."

Correction:

Most insertions are constant.

Resize operations require additional computational work.

The amortized cost remains constant over long sequences.

---

# Best Practices

When analysing algorithms using amortized analysis:

- evaluate sequences rather than isolated operations;
- identify operations that trigger structural changes;
- distinguish occasional costs from recurring costs;
- avoid confusing amortized and average-case analysis;
- justify conclusions using the total computational cost.

These practices encourage rigorous analysis of dynamic data structures.

---

# Key Takeaways

The learner should remember the following principles:

- Amortized analysis studies sequences of operations.
- It does not require probabilistic assumptions.
- Occasional expensive operations do not necessarily reduce efficiency.
- Dynamic arrays and hash tables are classical examples of amortized behaviour.
- Java's `ArrayList` relies on amortized analysis to achieve efficient insertion performance.
- Amortized analysis complements time complexity and asymptotic notation.
- Long-term behaviour is often more important than isolated execution costs.

Amortized analysis therefore provides a realistic and mathematically rigorous framework for understanding the performance of dynamic algorithms and data structures.

It is an essential concept for software engineers working with modern programming languages, including Java, and forms a bridge between theoretical algorithm analysis and practical software engineering.