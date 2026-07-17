# Preconditions and Postconditions

## Algorithms Java Mastery

This document introduces the concept of **preconditions** and **postconditions**, two fundamental components of algorithm specification.

Together, they define the contractual relationship between a computational problem and its solution.

Rather than describing how an algorithm works, they specify the conditions under which the algorithm may execute and the guarantees it must provide after execution.

The central question addressed throughout this document is:

> **Under which conditions may an algorithm execute, and what must be true after it finishes?**

---

# Purpose

The purpose of this document is to establish the concept of **algorithmic contracts**.

Every algorithm operates under certain assumptions.

If those assumptions are satisfied, the algorithm is expected to produce a well-defined result.

The expected progression is:

```text
Computational Problem
        ↓
Problem Specification
        ↓
Preconditions
        ↓
Algorithm
        ↓
Postconditions
```

Understanding these contracts allows the learner to reason formally about algorithm behaviour before implementation.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define preconditions and postconditions;
- distinguish between assumptions and guarantees;
- identify valid execution conditions;
- specify behavioural contracts for algorithms;
- explain why contracts improve software quality;
- relate contracts to correctness and testing.

These concepts will become essential throughout the repository.

---

# Algorithm Contracts

Every algorithm can be viewed as a contract.

The contract defines:

- what the algorithm expects;
- what the algorithm guarantees.

This relationship may be represented as:

```text
Preconditions
        ↓
Algorithm
        ↓
Postconditions
```

The algorithm is only responsible for producing the expected result when its preconditions are satisfied.

---

# Preconditions

A precondition is a condition that must be true before an algorithm begins execution.

If a precondition is violated, the algorithm is not required to produce a correct result.

Preconditions define the valid domain of the algorithm.

Examples include:

- the input array is not empty;
- an index belongs to the valid range;
- a graph contains at least one vertex;
- a divisor is not zero.

Preconditions should be explicitly documented rather than assumed.

---

# Characteristics of Preconditions

Well-defined preconditions should be:

- explicit;
- verifiable;
- objective;
- independent of implementation.

A precondition should never describe how the algorithm works.

Instead, it should define when the algorithm may be executed.

---

# Example of Preconditions

Problem:

> Find the largest value in an integer array.

Possible preconditions:

```text
Input array is not null.

Input array contains at least one element.
```

These conditions establish that the algorithm receives a valid input.

---

# Postconditions

A postcondition describes what must be true after the algorithm successfully completes.

It represents the guarantee provided by the algorithm.

If every precondition is satisfied, every postcondition must also be satisfied.

The relationship becomes:

```text
Valid Input
        ↓
Algorithm
        ↓
Guaranteed Result
```

---

# Characteristics of Postconditions

A postcondition should:

- describe the expected result;
- be objectively verifiable;
- be independent of implementation;
- define observable behaviour.

It should not describe internal variables or implementation details.

---

# Example of Postconditions

Problem:

> Find the largest value in an integer array.

Possible postconditions:

```text
The returned value belongs to the input array.

No element in the array is greater than the returned value.
```

These guarantees completely define the expected behaviour of the algorithm.

---

# Preconditions vs Postconditions

Although closely related, these concepts describe different responsibilities.

Preconditions define what must already be true.

Postconditions define what becomes true after execution.

The relationship is:

```text
Before Execution
        ↓
Preconditions

Algorithm

Postconditions
        ↓
After Execution
```

Understanding this distinction is essential for disciplined algorithm design.

---

# Why Contracts Matter

Algorithmic contracts improve software quality by reducing ambiguity.

Without contracts:

```text
Problem
      ↓
Implementation
```

With contracts:

```text
Problem
      ↓
Specification
      ↓
Contracts
      ↓
Implementation
```

Contracts establish a precise agreement between the problem specification and the algorithm.

---

# Relationship with Correctness

Correctness depends directly on algorithmic contracts.

If the preconditions are satisfied, a correct algorithm must satisfy every postcondition.

This relationship can be expressed as:

```text
Preconditions
        ↓
Correct Algorithm
        ↓
Postconditions
```

Correctness therefore becomes easier to reason about.

---

# Relationship with Testing

Contracts also provide the foundation for automated testing.

Every precondition suggests situations that should be validated.

Every postcondition defines behaviours that should be verified.

The progression becomes:

```text
Precondition
        ↓
Valid Test Inputs

Postcondition
        ↓
Assertions
```

For example:

```text
Precondition

Array contains at least one element.

↓

Test

Verify behaviour using arrays of different sizes.

↓

Postcondition

Returned value is the largest element.

↓

Assertion

Assert that the returned value equals the expected maximum.
```

This relationship will later be implemented using **JUnit Jupiter** and **AssertJ**.

---

# Relationship with Later Modules

Contracts influence every stage of algorithm development.

```text
Problem Specification
          ↓
Preconditions

Preconditions
          ↓
Correctness

Postconditions
          ↓
Testing

Testing
          ↓
Benchmarking
```

Every algorithm implemented throughout this repository should explicitly define its contracts before implementation.

---

# Best Practices

Every algorithm should:

- document its preconditions;
- document its postconditions;
- avoid implicit assumptions;
- define observable guarantees;
- separate contracts from implementation;
- verify contracts whenever appropriate.

Following these practices improves readability, maintainability, and correctness.

---

# Key Takeaways

The learner should remember the following principles:

- Every algorithm operates under explicit assumptions.
- Preconditions define valid execution conditions.
- Postconditions define guaranteed results.
- Contracts reduce ambiguity.
- Correctness depends on satisfying both preconditions and postconditions.
- Contracts naturally support automated testing.
- Contracts should be defined before implementation.

Preconditions and postconditions therefore establish the behavioural contract of every algorithm.

They provide the logical bridge between **problem specification**, **algorithm design**, **correctness reasoning**, and **automated testing**, becoming one of the most important foundations of disciplined software engineering throughout the **Algorithms Java Mastery** repository.