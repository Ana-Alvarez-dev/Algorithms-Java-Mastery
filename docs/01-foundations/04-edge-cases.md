# Edge Cases

## Algorithms Java Mastery

This document introduces the concept of **edge cases**, also known as **boundary cases**, in algorithm design.

Edge-case analysis is a fundamental step in disciplined software engineering because algorithms frequently fail at the limits of their expected input rather than during ordinary execution.

Understanding these situations before implementation improves correctness, testing, and software reliability.

The central question addressed throughout this document is:

> **Which inputs are most likely to expose incorrect algorithm behaviour?**

---

# Purpose

The purpose of this document is to establish the importance of analysing exceptional and boundary situations before implementing an algorithm.

Many software defects are not caused by the main algorithmic idea but by situations that were never considered during design.

The expected progression is:

```text
Problem Specification
        ↓
Contracts
        ↓
Edge-Case Analysis
        ↓
Algorithm Design
        ↓
Implementation
        ↓
Testing
```

Edge cases should therefore be identified before writing code.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define an edge case;
- distinguish ordinary inputs from boundary situations;
- identify common categories of edge cases;
- analyse algorithm behaviour under exceptional conditions;
- relate edge cases to correctness;
- transform edge cases into automated test cases.

These competencies become essential for designing reliable software.

---

# What Is an Edge Case?

An edge case is an input scenario that occurs at the limits of the valid problem domain.

Although these situations may be less frequent than ordinary inputs, they often reveal defects that remain hidden during normal execution.

Examples include:

- empty collections;
- single-element inputs;
- duplicated values;
- minimum values;
- maximum values;
- null references;
- extremely large inputs.

Edge cases are not exceptional because they are rare.

They are exceptional because they exercise the boundaries of the specification.

---

# Why Edge Cases Matter

Algorithms are usually designed using typical examples.

For example:

```text
Input

[7, 2, 15, 4, 9]
```

This input is useful for understanding the algorithm.

However, it does not answer questions such as:

- What happens if the array is empty?
- What happens if there is only one element?
- What happens if every value is identical?
- What happens if all values are negative?

Ignoring these situations frequently produces incorrect software.

---

# Ordinary Cases vs Edge Cases

A computational problem normally contains two categories of inputs.

```text
Valid Inputs
│
├── Ordinary Cases
│
└── Edge Cases
```

Both categories belong to the specification and therefore require analysis.

A correct algorithm must behave correctly for every valid input.

---

# Common Categories of Edge Cases

## Empty Input

Many algorithms receive collections.

An empty collection is often the first situation that should be analysed.

Example:

```text
[]
```

Questions include:

- Is the input valid?
- Should an exception be thrown?
- Should a default value be returned?

---

## Single Element

Collections containing one element frequently simplify the problem.

Example:

```text
[8]
```

The learner should determine whether the algorithm still behaves correctly.

---

## Duplicate Values

Algorithms should be analysed when identical values appear.

Example:

```text
[5, 5, 5, 5]
```

Questions include:

- Does the algorithm still terminate?
- Is the result deterministic?
- Does the presence of duplicates affect correctness?

---

## Minimum and Maximum Values

Numerical algorithms should consider extreme values.

Examples include:

```text
Integer.MIN_VALUE

Integer.MAX_VALUE
```

These situations may expose overflow or comparison errors.

---

## Negative Values

Many beginner implementations assume positive numbers.

Algorithms should instead be analysed using values from the complete valid domain.

Example:

```text
[-9, -2, -15]
```

---

## Large Inputs

Algorithms should also be analysed using large datasets.

Although correctness remains the primary objective, large inputs later become important when studying complexity and benchmarking.

---

# Edge Cases and Problem Specification

Edge-case analysis begins during problem specification.

The progression becomes:

```text
Problem
      ↓
Specification
      ↓
Edge Cases
      ↓
Algorithm
```

Boundary situations should never be discovered accidentally during implementation.

They should be anticipated during analysis.

---

# Edge Cases and Preconditions

Some edge cases violate algorithm preconditions.

Example:

Problem:

> Find the largest value in an array.

Precondition:

```text
The array contains at least one element.
```

Edge case:

```text
[]
```

The learner should recognise that this input violates the contract.

The algorithm is therefore not required to produce a valid result.

---

# Edge Cases and Correctness

Correctness requires analysing every valid input.

Edge-case analysis strengthens correctness reasoning by examining situations where incorrect assumptions frequently occur.

The relationship becomes:

```text
Contracts
        ↓
Edge Cases
        ↓
Correctness
```

---

# Edge Cases and Testing

Every identified edge case should later become an automated test.

The progression is:

```text
Edge Case
        ↓
Expected Behaviour
        ↓
Automated Test
```

For example:

```text
Edge Case

Single-element array

↓

Expected Behaviour

Return the only element.

↓

JUnit Test

Verify that the returned value equals the single value stored in the array.
```

This methodology creates a direct connection between algorithm analysis and software testing.

---

# Relationship with Later Modules

Edge-case analysis supports multiple areas of the repository.

```text
Edge Cases
        ↓
Correctness

Edge Cases
        ↓
Testing

Edge Cases
        ↓
Benchmarking

Edge Cases
        ↓
Algorithm Design
```

Every future implementation should explicitly identify its boundary situations before coding begins.

---

# Best Practices

Every algorithm should:

- identify boundary situations;
- analyse exceptional inputs;
- distinguish valid from invalid inputs;
- document expected behaviour;
- transform edge cases into automated tests;
- avoid making implicit assumptions.

Following these practices significantly improves software quality.

---

# Key Takeaways

The learner should remember the following principles:

- Edge cases are part of the problem specification.
- Boundary situations should be analysed before implementation.
- Every edge case represents an opportunity to validate algorithm correctness.
- Edge cases naturally become automated test cases.
- Correct software behaves correctly for both ordinary and boundary inputs.
- Reliable algorithms are designed with edge cases in mind from the beginning.

Edge-case analysis therefore becomes an essential component of disciplined algorithm design.

It connects **problem specification**, **algorithm contracts**, **correctness reasoning**, and **automated testing**, providing a systematic approach to building robust and reliable software throughout the **Algorithms Java Mastery** repository.