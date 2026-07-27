# Problem Specification

## Algorithms Java Mastery

This document introduces the formal specification of computational problems.

Before designing an algorithm, it is necessary to understand precisely **what problem must be solved**.

A poorly specified problem cannot produce a correct algorithm, regardless of the programming language or implementation quality.

The central question addressed throughout this document is:

> **How should a computational problem be formally specified before designing an algorithm?**

---

# Purpose

The purpose of this document is to establish a disciplined methodology for analysing computational problems before proposing solutions.

Many implementation errors originate from an incomplete understanding of the problem rather than incorrect programming.

For this reason, problem specification becomes the first technical activity in algorithm design.

The expected progression is:

```text
Computational Problem
        ↓
Problem Specification
        ↓
Algorithm Design
        ↓
Pseudocode
        ↓
Implementation
```

A correct solution always begins with a correct understanding of the problem.

---

# Learning Objectives

After studying this document, the learner should be able to:

- distinguish between a problem and its solution;
- formally specify computational problems;
- identify inputs and outputs;
- recognise assumptions and constraints;
- define the objective of an algorithm independently of its implementation;
- analyse examples before designing a solution.

These competencies will be applied throughout every module of the repository.

---

# What Is a Computational Problem?

A computational problem is a task that can be solved through a finite sequence of computational operations.

The problem describes **what must be achieved**, not **how it should be achieved**.

For example:

Problem:

> Find the largest value contained in an array.

The problem does not specify:

- programming language;
- data structures;
- loops;
- variables;
- implementation strategy.

Those decisions belong to the algorithm.

The relationship is:

```text
Problem
      ↓
Algorithm
      ↓
Implementation
```

---

# Why Specification Matters

Before designing an algorithm, the problem must be completely understood.

A formal specification reduces ambiguity and establishes a common understanding between the problem and its solution.

Without specification:

```text
Problem
      ↓
Guessing
      ↓
Implementation
```

With specification:

```text
Problem
      ↓
Analysis
      ↓
Specification
      ↓
Algorithm
      ↓
Implementation
```

Specification therefore becomes the foundation of algorithmic reasoning.

---

# Elements of a Problem Specification

Every computational problem should define several essential elements.

---

## Problem Statement

The first step is to describe the objective clearly and concisely.

Example:

> Determine the largest value contained in an integer array.

The statement should describe the goal without suggesting an implementation.

---

## Input

The specification must identify the information received by the algorithm.

Examples include:

- integer arrays;
- strings;
- graphs;
- trees;
- matrices;
- numerical values.

Example:

```text
Input

An array of integers.
```

---

## Output

The specification must describe the expected result.

Example:

```text
Output

The largest integer contained in the array.
```

The output should represent the solution to the problem.

---

## Constraints

Constraints define the valid limits of the problem.

Examples include:

- maximum array size;
- allowed value ranges;
- memory restrictions;
- execution time requirements.

Example:

```text
The array contains at least one element.
```

Constraints influence the selection of appropriate algorithms.

---

## Assumptions

Assumptions describe conditions considered true during execution.

For example:

```text
The input array has already been created.

The elements are valid integers.
```

Assumptions simplify problem analysis by explicitly defining the expected environment.

---

## Examples

Examples improve understanding by illustrating valid scenarios.

Example:

```text
Input

[7, 2, 15, 4, 9]

Output

15
```

Additional examples should include boundary situations.

---

# Separating the Problem from the Solution

One of the most common mistakes is describing the solution instead of the problem.

Incorrect specification:

> Traverse the array using a loop and compare every element.

This already describes an algorithm.

Correct specification:

> Find the largest value contained in the array.

The implementation remains intentionally undefined.

The relationship should always be:

```text
Problem
      ↓
Specification
      ↓
Algorithm Design
```

Never:

```text
Problem
      ↓
Java Code
```

---

# Example of a Complete Specification

Problem:

> Find the largest value in an array.

Specification:

```text
Problem

Determine the largest value contained in an integer array.

Input

An array of integers.

Output

The largest integer stored in the array.

Constraints

The array must contain at least one element.

Assumptions

The input is valid.

Examples

Input:
[7, 2, 15, 4, 9]

Output:
15
```

Notice that no implementation details have been introduced.

---

# Relationship with Algorithm Design

Problem specification naturally leads to algorithm design.

```text
Problem
      ↓
Specification
      ↓
Strategy
      ↓
Algorithm
      ↓
Implementation
```

The algorithm answers:

> **How should the problem be solved?**

The specification answers:

> **What problem must be solved?**

Both responsibilities are different and should never be confused.

---

# Relationship with Later Modules

Problem specification supports every later activity in the repository.

```text
Problem Specification
          ↓
Preconditions

Problem Specification
          ↓
Postconditions

Problem Specification
          ↓
Edge Cases

Problem Specification
          ↓
Correctness

Problem Specification
          ↓
Testing
```

A poorly specified problem produces incorrect tests, incorrect algorithms, and unreliable conclusions.

---

# Best Practices

Every problem specification should:

- define the objective clearly;
- identify inputs;
- identify outputs;
- document assumptions;
- document constraints;
- include representative examples;
- avoid implementation details;
- remain independent of any programming language.

These principles improve both algorithm design and software quality.

---

# Key Takeaways

The learner should remember the following principles:

- Every algorithm begins with a computational problem.
- Problems should be specified before they are solved.
- Specification describes **what**, not **how**.
- Inputs and outputs define the behaviour of the problem.
- Constraints influence algorithm selection.
- Examples improve understanding.
- Implementation should never appear inside the specification.
- A precise specification reduces ambiguity and improves software quality.

Problem specification therefore represents the first formal step of disciplined algorithm development.

It provides the foundation upon which every algorithm in the **Algorithms Java Mastery** repository will be designed, implemented, tested, and analysed.