# Algorithm Definition

## Algorithms Java Mastery

This document introduces the formal concept of an algorithm from a computational perspective.

It establishes the theoretical foundation required before studying algorithm design, implementation, analysis, and optimisation.

An algorithm is treated as an abstract computational solution that exists independently of any programming language.

The central question addressed throughout this document is:

> **What is an algorithm?**

---

# Purpose

The purpose of this document is to develop a precise understanding of what an algorithm is and what characteristics distinguish it from a computer program.

Many beginners associate algorithms directly with source code.

This module intentionally separates these concepts.

The expected progression is:

```text
Computational Problem
        ↓
Algorithm
        ↓
Implementation
        ↓
Executable Program
```

An algorithm represents the solution.

Programming languages provide one possible implementation of that solution.

---

# Learning Objectives

After studying this document, the learner should be able to:

- formally define an algorithm;
- distinguish between algorithms and programs;
- identify the fundamental characteristics of algorithms;
- explain why algorithms are independent of implementation languages;
- recognise algorithms as abstract computational procedures;
- understand the relationship between problems, algorithms, and implementations.

---

# Definition of an Algorithm

An algorithm is a **finite, ordered, and unambiguous sequence of instructions** designed to solve a computational problem.

Every algorithm receives one or more inputs, performs a sequence of well-defined operations, and produces one or more outputs.

The relationship may be represented as:

```text
Input
   ↓
Algorithm
   ↓
Output
```

The algorithm itself is independent of Java, Python, C++, or any other programming language.

Programming languages simply describe the algorithm in a form that computers can execute.

---

# Algorithm vs Program

Although these terms are often used interchangeably, they describe different concepts.

An algorithm is an abstract solution to a problem.

A program is a concrete implementation of one or more algorithms using a programming language.

The relationship is:

```text
Problem
     ↓
Algorithm
     ↓
Program
```

For example:

Problem:

> Find the largest value in a collection.

Algorithm:

> Compare each element while maintaining the current maximum.

Program:

> Java implementation using arrays, loops, variables, and methods.

The algorithm remains the same regardless of the implementation language.

---

# Fundamental Characteristics of Algorithms

Every algorithm should satisfy a number of essential properties.

## Finiteness

An algorithm must always terminate after a finite number of steps.

Infinite execution does not constitute a valid algorithm.

---

## Definiteness

Each instruction must be clearly defined.

Every execution step should be unambiguous.

Different people implementing the same algorithm should reach equivalent behaviour.

---

## Input

An algorithm may receive zero or more inputs.

Inputs provide the information required to solve the problem.

Examples include:

- arrays;
- numbers;
- strings;
- graphs;
- trees.

---

## Output

Every algorithm should produce at least one result.

The output represents the solution to the computational problem.

---

## Effectiveness

Each operation should be executable using elementary computational steps.

Instructions should be practical rather than purely theoretical.

---

# Algorithms and Computational Problems

Algorithms exist because computational problems exist.

The relationship is:

```text
Computational Problem
          ↓
Algorithm Design
          ↓
Implementation
          ↓
Verification
```

A problem may have multiple correct algorithms.

For example:

```text
Searching
│
├── Linear Search
│
└── Binary Search
```

Both algorithms solve the same problem but use different strategies.

This observation becomes the foundation of the **Strategy Pattern**, which will later be applied within this repository.

---

# Deterministic and Non-Deterministic Behaviour

Most algorithms studied throughout this repository are deterministic.

Given the same input, they always produce the same output.

```text
Input A
     ↓
Algorithm
     ↓
Output B
```

Some advanced computational models introduce non-deterministic behaviour.

These models are beyond the scope of the current module but become relevant in theoretical computer science.

---

# Correctness

An algorithm is not considered correct simply because it executes without errors.

Correctness means that the algorithm always produces the expected result for every valid input satisfying its preconditions.

Correctness therefore depends on logical reasoning rather than successful execution alone.

This topic is developed further in the **correctness.md** document.

---

# Efficiency

Different algorithms may solve the same problem while requiring different computational resources.

For example:

```text
Problem
      ↓
Algorithm A → Fast

Algorithm B → Slow
```

Efficiency is evaluated by analysing:

- execution time;
- memory consumption;
- asymptotic growth.

These concepts are introduced later in the Complexity module.

---

# Algorithms Throughout This Repository

Every algorithm studied in this repository will follow the same progression:

```text
Problem
     ↓
Specification
     ↓
Algorithm Design
     ↓
Pseudocode
     ↓
Java Implementation
     ↓
Testing
     ↓
Complexity Analysis
     ↓
Benchmarking
```

This progression encourages disciplined reasoning before implementation.

---

# Relationship with Design Patterns

Although this repository focuses on algorithms, software engineering principles are incorporated whenever they improve architecture.

Different algorithms solving the same computational problem will later be organised using the **Strategy Pattern**.

For example:

```text
Search Strategy
        ▲
        │
 ┌──────┴────────┐
 │               │
Linear      Binary
```

Each algorithm becomes an interchangeable strategy while preserving a common interface.

This architectural approach demonstrates how algorithmic reasoning and software engineering complement one another.

---

# Key Takeaways

The learner should remember the following principles:

- An algorithm is not a programming language.
- An algorithm is not source code.
- An algorithm is an abstract computational procedure.
- Programs implement algorithms.
- Multiple algorithms may solve the same problem.
- Algorithms should be analysed before they are implemented.
- Correctness precedes optimisation.
- Understanding the problem is more important than writing code.

The study of algorithms therefore begins with reasoning rather than implementation.

This principle forms the intellectual foundation of the entire **Algorithms Java Mastery** repository.