# Problem Solving Guide

## Algorithms Java Mastery

This document defines the standard methodology used throughout the repository to solve algorithmic problems involving arrays.

Writing code is only the final stage of algorithm development.

Professional software engineers first analyse the problem, identify constraints, design a strategy, reason about correctness and evaluate computational complexity before implementing a solution.

This systematic process improves solution quality, reduces errors and produces more maintainable software.

The central question addressed throughout this document is:

> **How should an array problem be analysed and solved before writing Java code?**

---

# Purpose

The purpose of this document is to establish a repeatable and disciplined problem-solving methodology.

Every exercise in this repository should follow the same sequence of reasoning.

The expected progression is:

```text
Problem
        ↓
Understand Requirements
        ↓
Identify Inputs and Outputs
        ↓
Define Preconditions
        ↓
Identify Edge Cases
        ↓
Design Strategy
        ↓
Write Pseudocode
        ↓
Trace Manually
        ↓
Reason About Correctness
        ↓
Analyse Complexity
        ↓
Implement in Java
        ↓
Write Automated Tests
        ↓
Evaluate Results
```

Following this methodology encourages analytical thinking instead of trial-and-error programming.

---

# Learning Objectives

After studying this document, the learner should be able to:

- analyse algorithmic problems systematically;
- identify the information required before implementation;
- design algorithmic strategies;
- validate correctness before coding;
- estimate computational complexity;
- implement robust Java solutions.

---

# Step 1 — Understand the Problem

The first responsibility is to understand the problem completely.

Questions to ask include:

- What is the objective?
- What information is provided?
- What result is expected?
- Are there any restrictions?

Never begin coding before fully understanding the problem.

---

# Step 2 — Identify the Input

Determine precisely what information the algorithm receives.

Example:

```text
Input

[7, 2, 15, 4, 9]
```

The input may consist of:

- arrays;
- matrices;
- numbers;
- strings;
- multiple data structures.

---

# Step 3 — Identify the Output

Define the expected result.

Example:

```text
Output

15
```

A clearly defined output simplifies algorithm design.

---

# Step 4 — Define Preconditions

Preconditions describe what must be true before the algorithm begins.

Examples:

- the array is not null;
- the array contains at least one element;
- the array is sorted;
- values are unique.

Algorithms rely on these assumptions.

---

# Step 5 — Identify Edge Cases

Edge cases frequently reveal hidden defects.

Typical examples include:

```text
Empty array

Single element

Duplicate values

Negative numbers

Very large arrays

Already sorted arrays

Reverse sorted arrays
```

Professional engineers analyse edge cases before implementation.

---

# Step 6 — Design the Strategy

Before writing code, determine the overall approach.

Ask questions such as:

- Is traversal sufficient?
- Is searching required?
- Should two pointers be used?
- Would a sliding window improve efficiency?
- Is an auxiliary data structure necessary?

The strategy should solve the problem conceptually.

---

# Step 7 — Write Pseudocode

Pseudocode describes the algorithm independently of any programming language.

Example:

```text
maximum ← first element

for each remaining element

    if current > maximum

        maximum ← current

return maximum
```

Pseudocode allows the algorithm to be reviewed before implementation.

---

# Step 8 — Perform a Manual Trace

Execute the algorithm manually using a small example.

Example:

```text
Input

[7, 2, 15, 4, 9]
```

Execution:

```text
Maximum = 7

Compare 2

Maximum = 7

Compare 15

Maximum = 15

Compare 4

Maximum = 15

Compare 9

Maximum = 15
```

Manual tracing frequently reveals logical errors.

---

# Step 9 — Reason About Correctness

Ask whether the algorithm always produces the expected result.

Questions include:

- Does it terminate?
- Does it work for every valid input?
- Does it satisfy the specification?
- Does it handle edge cases?

Correctness should be established before optimisation.

---

# Step 10 — Analyse Computational Complexity

Evaluate both:

- time complexity;
- space complexity.

Example:

```text
Traversal

↓

O(n)
```

Complexity analysis predicts scalability.

---

# Step 11 — Implement in Java

Only after completing the previous steps should implementation begin.

The implementation should emphasise:

- readability;
- simplicity;
- correctness;
- maintainability.

Programming is the execution of a previously designed solution.

---

# Step 12 — Write Automated Tests

Every algorithm should be validated through automated tests.

Typical test cases include:

- normal input;
- empty array;
- single element;
- duplicate values;
- negative values;
- large inputs.

Testing verifies implementation correctness.

---

# Step 13 — Evaluate the Solution

Finally, evaluate the completed algorithm.

Questions include:

- Is the solution correct?
- Can complexity be improved?
- Is memory usage acceptable?
- Is the implementation readable?
- Would another algorithm perform better?

Engineering always involves evaluation and refinement.

---

# Standard Problem-Solving Template

Every algorithm developed throughout this repository should follow this template.

```text
Problem

↓

Input

↓

Output

↓

Preconditions

↓

Edge Cases

↓

Strategy

↓

Pseudocode

↓

Manual Trace

↓

Correctness

↓

Complexity Analysis

↓

Java Implementation

↓

Automated Tests

↓

Technical Conclusions
```

This methodology provides consistency across the entire repository.

---

# Relationship with Previous Modules

This guide integrates concepts introduced earlier.

```text
Foundations
        ↓
Problem Analysis

Complexity
        ↓
Complexity Analysis

Arrays
        ↓
Traversal Strategies

Java
        ↓
Implementation

Testing
        ↓
Validation
```

Each module contributes one stage of the complete engineering process.

---

# Relationship with Future Modules

The same methodology will be reused throughout the repository.

Examples include:

```text
Searching

Sorting

Recursion

Trees

Graphs

Dynamic Programming

Backtracking
```

Only the algorithm changes.

The engineering methodology remains constant.

---

# Common Mistakes

Avoid the following practices.

---

## Writing Code Immediately

Programming without understanding the problem usually leads to unnecessary complexity.

---

## Ignoring Edge Cases

Algorithms that work only for typical inputs are incomplete.

---

## Skipping Pseudocode

Pseudocode exposes logical errors before implementation.

---

## Optimising Too Early

Correctness is always more important than optimisation.

---

## Ignoring Complexity

An algorithm should always be evaluated in terms of scalability.

---

# Best Practices

When solving algorithmic problems:

- understand the specification completely;
- identify assumptions explicitly;
- analyse edge cases first;
- design the strategy before implementation;
- validate correctness systematically;
- evaluate computational complexity;
- write automated tests for every solution.

---

# Key Takeaways

The learner should remember the following principles:

- Problem solving begins before programming.
- Every algorithm should follow a systematic methodology.
- Inputs, outputs and preconditions must be clearly identified.
- Edge cases should be analysed before implementation.
- Pseudocode separates algorithmic reasoning from programming syntax.
- Correctness precedes optimisation.
- Complexity analysis supports engineering decisions.
- Automated testing validates implementation quality.

Following this methodology transforms programming into a disciplined engineering process and provides a consistent framework for solving increasingly complex algorithmic problems throughout the **Algorithms Java Mastery** repository.