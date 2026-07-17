# Invariants

## Algorithms Java Mastery

This document introduces the concept of **algorithmic invariants**, one of the most important tools for reasoning about algorithm correctness.

An invariant is a property that remains true throughout the execution of an algorithm.

Rather than describing individual instructions, invariants describe the state of the algorithm and explain why each execution step preserves correctness.

Understanding invariants allows the learner to reason formally about algorithms before analysing their implementation or efficiency.

The central question addressed throughout this document is:

> **Which property remains true during every step of an algorithm?**

---

# Purpose

The purpose of this document is to introduce invariants as a fundamental reasoning technique in algorithm design.

Many correctness proofs rely on demonstrating that a specific property remains unchanged during execution.

The expected progression is:

```text
Problem Specification
        ↓
Algorithm Design
        ↓
Invariant
        ↓
Correctness
        ↓
Implementation
```

Instead of observing isolated execution steps, the learner studies the behaviour that remains consistently true throughout the algorithm.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define an invariant;
- distinguish an invariant from an algorithm step;
- explain why invariants support correctness reasoning;
- identify invariants in iterative algorithms;
- identify invariants in recursive algorithms;
- relate invariants to problem specification and contracts.

These concepts become essential throughout the study of algorithms and data structures.

---

# What Is an Invariant?

An invariant is a logical property that remains true before and after every execution step of an algorithm.

Although variables may change, the invariant itself never becomes false.

The relationship is:

```text
Iteration
      ↓
Invariant Holds

Iteration
      ↓
Invariant Holds

Iteration
      ↓
Invariant Holds
```

If the invariant is preserved during every execution step, confidence in the correctness of the algorithm increases.

---

# Why Invariants Matter

Algorithms modify their internal state while executing.

Without invariants, it becomes difficult to reason about whether those modifications remain correct.

The invariant provides a stable reference throughout execution.

```text
Changing State
        ↓
Invariant Remains True
```

Instead of analysing every instruction independently, the learner analyses the property that is preserved.

---

# Invariants in Iterative Algorithms

Loop invariants are the most common type of invariant.

They describe a property that remains true before and after every iteration of a loop.

General structure:

```text
Before Loop
        ↓
Invariant Holds

Each Iteration
        ↓
Invariant Holds

Loop Ends
        ↓
Correct Result
```

If the invariant is preserved throughout execution, the final result follows logically.

---

# Example: Finding the Maximum Value

Problem:

> Find the largest value in an integer array.

Possible invariant:

```text
After processing the first i elements,
the variable maximum contains
the largest value found so far.
```

Example:

```text
Array

[7, 2, 15, 4, 9]
```

Execution:

```text
Step 1

Maximum = 7

↓

Step 2

Maximum = 7

↓

Step 3

Maximum = 15

↓

Step 4

Maximum = 15

↓

Step 5

Maximum = 15
```

At every step, the invariant remains true:

> The variable `maximum` stores the largest value among the elements already processed.

This property guarantees that, once every element has been examined, the algorithm returns the correct result.

---

# Invariants in Recursive Algorithms

Recursive algorithms also rely on invariants.

Instead of analysing loop iterations, the invariant is preserved across recursive calls.

General progression:

```text
Recursive Call
        ↓
Invariant Holds

Recursive Call
        ↓
Invariant Holds

Base Case
        ↓
Correct Result
```

Although the execution model differs, the reasoning principle remains the same.

---

# Invariants and Correctness

Correctness arguments frequently depend on invariants.

The progression becomes:

```text
Invariant
        ↓
Correctness Reasoning
        ↓
Confidence in the Algorithm
```

An invariant alone does not prove correctness.

However, demonstrating that it is:

- true before execution;
- preserved during execution;
- sufficient to establish the final result;

provides a strong correctness argument.

---

# Establishing an Invariant

A useful invariant normally satisfies three conditions.

### Initialization

The invariant must be true before the algorithm begins.

```text
Start
      ↓
Invariant Holds
```

---

### Maintenance

Every execution step must preserve the invariant.

```text
Iteration
      ↓
Invariant Remains True
```

---

### Termination

When the algorithm finishes, the invariant should imply the expected result.

```text
Invariant
      ↓
Algorithm Ends
      ↓
Correct Solution
```

These three stages form the basis of invariant-based reasoning.

---

# Invariants and Edge Cases

A correct invariant should remain valid even in boundary situations.

Examples include:

- single-element collections;
- duplicate values;
- negative numbers;
- already sorted inputs;
- reverse-ordered inputs.

Edge-case analysis therefore strengthens confidence in the invariant.

---

# Invariants and Testing

Although invariants are theoretical concepts, they influence testing.

Every automated test provides evidence that the invariant appears to be preserved for selected inputs.

The relationship is:

```text
Invariant
      ↓
Expected Behaviour
      ↓
Automated Test
```

Testing increases confidence but does not replace formal reasoning.

---

# Invariants Throughout This Repository

Invariants will appear repeatedly throughout the repository.

Examples include:

```text
Linear Search

↓

Maximum Search

↓

Insertion Sort

↓

Selection Sort

↓

Merge Sort

↓

Quick Sort

↓

Binary Search

↓

Heap Operations

↓

AVL Trees

↓

Graph Traversals

↓

Dynamic Programming
```

Although each algorithm differs, all of them preserve one or more logical properties during execution.

Learning to identify these properties is an essential algorithmic skill.

---

# Best Practices

Every algorithm should:

- define its invariant explicitly whenever possible;
- verify that the invariant is true before execution;
- preserve the invariant during every execution step;
- use the invariant to reason about correctness;
- relate invariants to the problem specification.

These practices encourage disciplined algorithm design.

---

# Key Takeaways

The learner should remember the following principles:

- An invariant is a property that always remains true during execution.
- Variables may change while the invariant remains valid.
- Invariants provide the foundation for many correctness arguments.
- Loop invariants support iterative algorithms.
- Recursive invariants support recursive algorithms.
- Correctness reasoning often depends on identifying and preserving invariants.
- Invariants connect problem specification, contracts, correctness, and implementation.

Algorithmic invariants therefore represent one of the most powerful reasoning tools in Computer Science.

They allow developers to explain not only **what** an algorithm does, but also **why** it is guaranteed to work.

This reasoning process becomes increasingly important throughout the study of searching algorithms, sorting algorithms, trees, graphs, and dynamic programming in the **Algorithms Java Mastery** repository.