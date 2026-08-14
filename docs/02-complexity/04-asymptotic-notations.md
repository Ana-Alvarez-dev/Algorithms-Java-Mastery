# Asymptotic Notation

## Algorithms Java Mastery

This document introduces **asymptotic notation**, the mathematical language used throughout Computer Science to describe the growth behaviour of algorithms.

Previous documents established that algorithm efficiency is analysed according to the growth of computational work rather than execution time measured on a specific machine.

Asymptotic notation provides the formal mathematical tools required to express that growth precisely.

The central question addressed throughout this document is:

> **How can the growth of an algorithm be described mathematically?**

---

# Purpose

The purpose of this document is to introduce the standard asymptotic notations used in algorithm analysis.

These mathematical notations allow algorithms to be compared objectively according to their long-term behaviour as the input size increases.

The expected progression is:

```text
Algorithm
        ↓
Growth Behaviour
        ↓
Mathematical Description
        ↓
Asymptotic Notation
        ↓
Algorithm Comparison
```

Rather than measuring execution time, asymptotic notation classifies the rate at which computational work increases.

---

# Learning Objectives

After studying this document, the learner should be able to:

- explain why asymptotic notation exists;
- distinguish growth from execution time;
- understand the purpose of mathematical abstraction;
- differentiate upper, lower and tight bounds;
- recognise the meaning of Big O, Big Ω and Big Θ;
- understand the role of little o and little ω;
- correctly interpret asymptotic expressions.

These competencies establish the mathematical vocabulary used throughout algorithm analysis.

---

# Why Mathematical Notation Is Necessary

Suppose two developers implement different algorithms.

One executes in:

```text
125 ms
```

The other executes in:

```text
80 ms
```

Can we conclude that the second algorithm is better?

No.

Those measurements depend on:

- processor speed;
- memory;
- operating system;
- compiler;
- JVM optimisations.

Algorithm analysis therefore requires a representation independent of technology.

That representation is asymptotic notation.

---

# What Is Asymptotic Notation?

Asymptotic notation is a mathematical language that describes how an algorithm grows as the input size approaches infinity.

It focuses on:

- growth behaviour;
- scalability;
- dominant terms.

It ignores:

- hardware;
- implementation details;
- processor speed;
- constant execution times.

---

# The Principle of Dominant Growth

Consider the following expression:

```text
n² + 8n + 20
```

For very small values of **n**, every term contributes.

As **n** becomes larger, the quadratic term dominates.

```text
Small Input
        ↓
All Terms Matter

Large Input
        ↓
Dominant Term Determines Growth
```

This principle forms the basis of asymptotic notation.

---

# Upper Bound

Sometimes we need to describe the maximum growth rate of an algorithm.

This is called an **upper bound**.

The notation used is:

```text
Big O
```

Big O answers:

> **How fast can this algorithm grow at most?**

---

# Lower Bound

Sometimes we need to describe the minimum growth rate.

This is called a **lower bound**.

The notation used is:

```text
Big Ω
```

Big Omega answers:

> **How slowly can this algorithm grow at least?**

---

# Tight Bound

When both upper and lower bounds coincide, we obtain the exact asymptotic growth.

The notation used is:

```text
Big Θ
```

Big Theta answers:

> **What is the actual asymptotic growth?**

---

# Strict Upper Bound

Some analyses require a bound that is strictly larger.

The notation used is:

```text
little o
```

This notation is less common in introductory algorithm analysis but is important in theoretical Computer Science.

---

# Strict Lower Bound

The strict lower counterpart is:

```text
little ω
```

Like little o, it appears mainly in advanced mathematical analysis.

---

# Overview of the Asymptotic Notations

```text
Big O
        ↓
Upper Bound

Big Ω
        ↓
Lower Bound

Big Θ
        ↓
Tight Bound

little o
        ↓
Strict Upper Bound

little ω
        ↓
Strict Lower Bound
```

Each notation answers a different mathematical question.

None of them replaces the others.

---

# Why Big O Is the Most Popular

Although several asymptotic notations exist, Big O is the most widely used in software engineering.

This is because developers are often interested in the maximum growth an algorithm may exhibit.

However, Big O does **not** always describe the exact complexity.

That role belongs to Big Θ.

Understanding this distinction is essential for rigorous algorithm analysis.

---

# Relationship with Time and Space Complexity

Asymptotic notation is applied to both computational resources.

```text
Time Complexity
        ↓
Big O
Big Ω
Big Θ

Space Complexity
        ↓
Big O
Big Ω
Big Θ
```

The notation itself is independent of the resource being analysed.

---

# Relationship with Benchmarking

Asymptotic notation predicts growth.

Benchmarking measures implementation performance.

```text
Asymptotic Notation
        ↓
Theory

JMH
        ↓
Experiment
```

Both perspectives complement one another.

---

# Relationship with Later Modules

Every algorithm studied throughout this repository will include asymptotic notation.

Examples include:

```text
Searching
        ↓
Asymptotic Analysis

Sorting
        ↓
Asymptotic Analysis

Trees
        ↓
Asymptotic Analysis

Graphs
        ↓
Asymptotic Analysis

Dynamic Programming
        ↓
Asymptotic Analysis
```

The notation introduced here becomes the common language used across every algorithmic domain.

---

# Best Practices

When using asymptotic notation:

- analyse growth rather than execution time;
- identify the dominant term;
- ignore constant factors appropriately;
- distinguish upper, lower and tight bounds;
- avoid assuming that Big O always represents exact complexity;
- justify every asymptotic conclusion mathematically.

These practices promote precise and rigorous algorithm analysis.

---

# Key Takeaways

The learner should remember the following principles:

- Asymptotic notation is a mathematical language.
- It describes growth rather than execution time.
- Big O represents an upper bound.
- Big Ω represents a lower bound.
- Big Θ represents a tight bound.
- little o and little ω represent strict bounds.
- The notation is independent of programming languages and hardware.
- Every complexity analysis should use the notation that best describes the algorithm.

Asymptotic notation therefore provides the formal mathematical vocabulary required for analysing algorithm efficiency and serves as the foundation for all complexity analyses performed throughout the **Algorithms Java Mastery** repository.