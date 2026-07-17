# Foundations

## Algorithms Java Mastery

The **Foundations** module establishes the theoretical and methodological principles required before studying specific algorithms and data structures.

It defines the concepts that support every algorithm implemented throughout this repository.

Unlike later modules, the objective is not to implement solutions in Java, but to understand **how computational problems should be analysed before implementation**.

The central principle of this module is:

> **An algorithm should be understood before it is implemented.**

Programming is therefore considered the consequence of algorithmic reasoning rather than its starting point.

---

# Purpose

This module develops the conceptual foundations required for disciplined algorithm design.

Its objective is to replace syntax-first learning with problem-first reasoning.

The learner should progressively develop the habit of analysing a computational problem before writing code.

The expected progression is:

```text
Computational Problem
        ↓
Formal Specification
        ↓
Algorithmic Reasoning
        ↓
Strategy Design
        ↓
Pseudocode
        ↓
Java Implementation
```

The concepts introduced here will be reused throughout every subsequent module of the repository.

---

# Learning Objectives

After completing this module, the learner should be able to:

- define an algorithm from a computational perspective;
- distinguish between a problem and its implementation;
- formally specify computational problems;
- identify inputs and outputs;
- define preconditions and postconditions;
- analyse boundary and exceptional cases;
- reason about algorithm correctness;
- identify algorithmic invariants;
- decompose complex problems into smaller subproblems;
- apply a disciplined problem-solving methodology before implementation.

These competencies form the basis for every algorithm studied later in the repository.

---

# Module Structure

The current structure is:

```text
01-foundations/
├── README.md
├── algorithm-definition.md
├── problem-specification.md
├── preconditions-and-postconditions.md
├── edge-cases.md
├── correctness.md
├── invariants.md
├── problem-decomposition.md
└── problem-solving-method.md
```

Each document addresses one essential concept of algorithmic reasoning.

---

# Document Overview

## algorithm-definition.md

Introduces the formal definition of an algorithm and its fundamental characteristics.

The learner studies what an algorithm is, how it differs from a program, and why algorithmic reasoning is independent of any programming language.

---

## problem-specification.md

Explains how computational problems should be formally specified.

The document introduces:

- problem definition;
- inputs;
- outputs;
- constraints;
- assumptions;
- examples.

A correct specification is the foundation of every algorithm.

---

## preconditions-and-postconditions.md

Introduces software contracts.

The learner studies:

- execution assumptions;
- behavioural guarantees;
- valid inputs;
- expected outputs.

Every algorithm should clearly state the conditions under which it operates.

---

## edge-cases.md

Explains the importance of analysing boundary conditions before implementation.

Typical situations include:

- empty collections;
- single-element inputs;
- duplicated values;
- invalid inputs;
- extreme values.

Edge-case analysis becomes the foundation of later automated testing.

---

## correctness.md

Introduces formal reasoning about algorithm correctness.

The learner studies why an algorithm is considered correct and why testing alone cannot prove correctness.

The document also introduces the concepts of:

- partial correctness;
- total correctness;
- termination.

---

## invariants.md

Introduces algorithmic invariants.

The learner studies properties that remain true throughout iterative or recursive execution.

Invariant reasoning will later support the study of:

- searching algorithms;
- sorting algorithms;
- graph algorithms;
- dynamic programming.

---

## problem-decomposition.md

Explains how complex computational problems can be divided into simpler subproblems.

Topics include:

- abstraction;
- decomposition;
- modular reasoning;
- sequential problem solving.

The learner develops the ability to simplify complexity before implementation.

---

## problem-solving-method.md

Defines the standard methodology used throughout this repository.

The study process is:

```text
Problem Understanding
        ↓
Input and Output Identification
        ↓
Formal Specification
        ↓
Preconditions
        ↓
Postconditions
        ↓
Edge-Case Analysis
        ↓
Strategy Design
        ↓
Pseudocode
        ↓
Manual Execution
        ↓
Correctness Reasoning
        ↓
Complexity Preview
        ↓
Java Implementation
```

Every future algorithm should follow this methodology.

---

# Relationship with Later Modules

The Foundations module supports every subsequent section of the repository.

Examples include:

```text
Foundations
      ↓
Complexity Analysis

Foundations
      ↓
Arrays

Foundations
      ↓
Searching Algorithms

Foundations
      ↓
Sorting Algorithms

Foundations
      ↓
Trees

Foundations
      ↓
Graphs

Foundations
      ↓
Dynamic Programming
```

The concepts introduced here should not be viewed as isolated theory.

They become practical tools used throughout the study of algorithms.

---

# Expected Outcome

At the conclusion of this module, the learner should no longer approach algorithmic problems by immediately writing Java code.

Instead, each problem should first be analysed, specified, and reasoned about before implementation.

The objective is to establish disciplined algorithmic thinking that remains applicable regardless of the programming language or technology used.

This methodology becomes the intellectual foundation of the entire **Algorithms Java Mastery** repository.