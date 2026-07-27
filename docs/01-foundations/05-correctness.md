# Correctness

## Algorithms Java Mastery

This document introduces the concept of **algorithm correctness**, one of the fundamental principles of Computer Science.

Designing an algorithm is not only about producing an implementation that executes successfully.

The primary objective is to demonstrate that the algorithm always produces the correct result for every valid input.

Correctness therefore represents the logical foundation of every algorithm studied throughout this repository.

The central question addressed throughout this document is:

> **Why is an algorithm considered correct?**

---

# Purpose

The purpose of this document is to establish the principles used to reason about algorithm correctness.

Correctness is independent of programming languages, software frameworks, and implementation details.

Before analysing efficiency or optimisation, it is necessary to determine whether an algorithm actually solves the problem it was designed to solve.

The expected progression is:

```text
Computational Problem
        ↓
Problem Specification
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Implementation
```

An efficient algorithm that produces incorrect results has no practical value.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define algorithm correctness;
- distinguish correctness from successful execution;
- explain why correctness precedes optimisation;
- understand the relationship between contracts and correctness;
- distinguish partial correctness from total correctness;
- recognise the importance of algorithm termination;
- relate correctness to testing and formal reasoning.

These concepts provide the theoretical foundation for every algorithm implemented in this repository.

---

# What Is Correctness?

An algorithm is considered **correct** if it always produces the expected result for every valid input defined by the problem specification.

Correctness is determined by comparing the behaviour of the algorithm with its formal specification.

The relationship is:

```text
Problem Specification
        ↓
Correct Algorithm
        ↓
Expected Result
```

The implementation language does not affect correctness.

Whether implemented in Java, Python, or C++, the algorithm must satisfy the same specification.

---

# Correctness Is Not Successful Execution

A common misconception is that an algorithm is correct because it executes without errors.

Execution alone does not prove correctness.

For example:

```text
Program executes successfully.

↓

Returned result is incorrect.
```

The program runs.

The algorithm is still incorrect.

Correctness depends on the validity of the result rather than successful execution.

---

# Correctness Depends on the Specification

Every correctness argument begins with the problem specification.

The specification defines:

- the problem;
- the inputs;
- the outputs;
- the contracts.

The algorithm is correct only if it satisfies the specification.

```text
Specification
        ↓
Correctness
        ↓
Implementation
```

Without a specification, correctness cannot be evaluated objectively.

---

# Partial Correctness

Partial correctness answers the following question:

> **If the algorithm terminates, does it produce the correct result?**

Termination is not considered.

Only the produced result is analysed.

The relationship is:

```text
Algorithm Terminates
        ↓
Correct Result
```

If this condition always holds, the algorithm is partially correct.

---

# Total Correctness

Total correctness extends partial correctness.

It answers two questions:

- Does the algorithm terminate?
- Does it produce the correct result?

The relationship becomes:

```text
Algorithm
      ↓
Terminates
      ↓
Produces Correct Result
```

An algorithm is totally correct only if both conditions are satisfied.

---

# Termination

Termination is an essential property of every algorithm.

A non-terminating procedure cannot be considered a correct algorithm.

For example:

```text
Start
   ↓
Infinite Loop
   ↓
Never Ends
```

Since no result is produced, the computational problem is never solved.

Every algorithm studied throughout this repository must terminate after a finite number of steps.

---

# Correctness and Preconditions

Correctness assumes that every precondition is satisfied.

Example:

Problem:

> Find the largest value in an array.

Precondition:

```text
The array contains at least one element.
```

If the input violates this condition:

```text
[]
```

The algorithm is not required to satisfy its postconditions.

Correctness is therefore evaluated only within the valid problem domain.

---

# Correctness and Postconditions

Postconditions define the guarantees that a correct algorithm must satisfy.

Example:

```text
Returned value belongs to the input array.

No element is greater than the returned value.
```

A correct algorithm must satisfy every documented postcondition.

---

# Correctness and Edge Cases

Correctness includes ordinary situations as well as boundary conditions.

The relationship becomes:

```text
Specification
        ↓
Ordinary Cases

Specification
        ↓
Edge Cases

↓

Correctness
```

Ignoring edge cases weakens confidence in algorithm correctness.

---

# Correctness and Testing

Testing provides evidence that an implementation behaves correctly for selected inputs.

However, testing does not prove correctness.

The relationship is:

```text
Correctness
        ↓
Formal Reasoning

Testing
        ↓
Experimental Verification
```

Testing and correctness complement one another.

Neither replaces the other.

---

# Correctness Throughout This Repository

Every algorithm implemented in this repository should follow the same progression:

```text
Problem
      ↓
Specification
      ↓
Contracts
      ↓
Correctness Reasoning
      ↓
Implementation
      ↓
Testing
      ↓
Complexity Analysis
```

Correctness always precedes optimisation.

---

# Relationship with Invariants

Many correctness arguments rely on invariants.

An invariant describes a property that remains true throughout algorithm execution.

The progression becomes:

```text
Invariant
      ↓
Correctness
      ↓
Confidence in the Algorithm
```

This topic is developed in the next document of the Foundations module.

---

# Best Practices

Every algorithm should:

- begin with a formal specification;
- define explicit contracts;
- analyse boundary situations;
- reason about correctness before implementation;
- terminate after a finite number of steps;
- verify behaviour through automated tests.

Following these practices significantly improves software quality and algorithm reliability.

---

# Key Takeaways

The learner should remember the following principles:

- Correctness is independent of programming languages.
- A program that executes successfully is not necessarily correct.
- Correctness depends on the problem specification.
- Preconditions define the valid execution domain.
- Postconditions define the expected guarantees.
- Partial correctness analyses the produced result.
- Total correctness requires both termination and correctness.
- Testing increases confidence but does not prove correctness.
- Correctness always precedes optimisation.

Algorithm correctness therefore represents one of the most important foundations of Computer Science.

It connects **problem specification**, **algorithm contracts**, **edge-case analysis**, **invariants**, **testing**, and **complexity analysis**, providing the logical basis upon which every algorithm in the **Algorithms Java Mastery** repository will be designed, implemented, analysed, and verified.