# Problem-Solving Method

## Algorithms Java Mastery

This document defines the standard methodology used throughout the **Algorithms Java Mastery** repository for studying, designing, implementing, and analysing algorithms.

Rather than beginning with programming, the methodology establishes a disciplined reasoning process that starts with understanding the computational problem and concludes with experimental evaluation.

Every algorithm implemented in this repository should follow the same study procedure.

The central question addressed throughout this document is:

> **How should an algorithm be studied before it is implemented?**

---

# Purpose

The purpose of this document is to establish a systematic and repeatable methodology for algorithmic study.

Instead of treating each algorithm as an isolated implementation, the learner follows a structured process that develops analytical thinking, software engineering practices, and professional problem-solving skills.

The expected progression is:

```text
Computational Problem
        ↓
Formal Specification
        ↓
Algorithm Design
        ↓
Implementation
        ↓
Validation
        ↓
Experimental Evaluation
```

This methodology transforms algorithm study into an engineering process rather than a programming exercise.

---

# Learning Objectives

After studying this document, the learner should be able to:

- analyse computational problems systematically;
- define formal specifications;
- identify contracts and boundary conditions;
- design algorithmic strategies;
- write language-independent pseudocode;
- validate solutions through manual execution;
- reason about correctness;
- analyse computational complexity;
- implement algorithms in Java;
- verify implementations through automated testing;
- evaluate performance using benchmarking.

These competencies define the complete learning cycle adopted throughout this repository.

---

# The Algorithmic Study Process

Every algorithm should be studied using the following sequence.

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
Problem Decomposition
        ↓
Strategy Design
        ↓
Pseudocode
        ↓
Manual Execution
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Benchmarking
        ↓
Technical Conclusions
```

Each stage has a specific responsibility and should not be skipped.

---

# Stage 1 — Problem Understanding

The first step consists of understanding the computational problem.

Questions include:

- What must be solved?
- What information is available?
- What result is expected?
- Which constraints exist?

Implementation must not begin during this stage.

The objective is complete comprehension of the problem.

---

# Stage 2 — Input and Output Identification

Every computational problem receives information and produces a result.

The learner should explicitly identify:

```text
Input
    ↓
Algorithm
    ↓
Output
```

This simple model defines the observable behaviour of the algorithm.

---

# Stage 3 — Formal Specification

The computational problem is transformed into a precise specification.

The specification should define:

- problem statement;
- inputs;
- outputs;
- assumptions;
- constraints;
- examples.

A correct specification eliminates ambiguity before implementation begins.

---

# Stage 4 — Preconditions

Execution assumptions are documented.

Examples include:

```text
Array is not null.

Array contains at least one element.

Index belongs to the valid range.
```

The algorithm is only expected to behave correctly when these conditions are satisfied.

---

# Stage 5 — Postconditions

Expected guarantees are documented.

Examples include:

```text
Returned value belongs to the array.

No value is greater than the returned value.
```

These guarantees define the expected behaviour after execution.

---

# Stage 6 — Edge-Case Analysis

Boundary situations are identified before implementation.

Typical examples include:

- empty collections;
- single-element inputs;
- duplicated values;
- negative values;
- maximum and minimum values.

Every identified edge case should later become an automated test.

---

# Stage 7 — Problem Decomposition

The computational problem is divided into smaller responsibilities.

General progression:

```text
Complex Problem
        ↓
Subproblems
        ↓
Algorithm Design
```

Each subproblem becomes easier to understand and solve.

---

# Stage 8 — Strategy Design

Only after understanding the problem should the learner design a solution strategy.

Possible strategies include:

- sequential processing;
- divide and conquer;
- recursion;
- dynamic programming;
- greedy algorithms;
- hashing.

The strategy should remain independent of Java.

---

# Stage 9 — Pseudocode

The algorithm is described using language-independent notation.

Example:

```text
Set maximum to the first element.

For every remaining element:

    If current element is greater:

        Update maximum.

Return maximum.
```

Pseudocode focuses on logic rather than syntax.

---

# Stage 10 — Manual Execution

The learner executes the algorithm manually.

Example:

```text
Input

[7, 2, 15, 4, 9]
```

Execution:

```text
Maximum = 7

↓

Compare 2

↓

Compare 15

↓

Compare 4

↓

Compare 9

↓

Return 15
```

Manual tracing validates the reasoning before programming.

---

# Stage 11 — Correctness Reasoning

The learner explains why the algorithm always produces the correct result.

Topics include:

- invariants;
- contracts;
- logical arguments;
- termination.

Correctness precedes optimisation.

---

# Stage 12 — Complexity Analysis

The computational cost is analysed.

Topics include:

- time complexity;
- space complexity;
- asymptotic growth;
- Big O notation.

This stage evaluates efficiency independently of hardware.

---

# Stage 13 — Java Implementation

Only now is the algorithm implemented.

Implementation should respect:

- clean code;
- object-oriented principles;
- package organisation;
- project architecture.

Java becomes the implementation language rather than the design language.

---

# Stage 14 — Automated Testing

Every implementation should be validated using automated tests.

Testing should include:

- ordinary cases;
- edge cases;
- invalid inputs;
- contract verification.

Testing will be implemented using:

- JUnit Jupiter;
- AssertJ.

Automated tests provide confidence that the implementation satisfies its specification.

---

# Stage 15 — Benchmarking

Performance is evaluated experimentally.

Topics include:

- execution time;
- scalability;
- comparison between algorithms.

Benchmarking will be performed using **Java Microbenchmark Harness (JMH)**.

Experimental evaluation complements theoretical complexity analysis.

---

# Stage 16 — Technical Conclusions

Every algorithm should conclude with a technical evaluation.

Possible questions include:

- Is the algorithm correct?
- Is it efficient?
- Can it be improved?
- Which situations favour its use?
- Which design pattern best represents its behaviour?

The objective is to encourage critical thinking rather than simple implementation.

---

# Applying the Methodology Throughout the Repository

Every future module should apply the same methodology.

For example:

```text
Arrays
        ↓
Problem Understanding
        ↓
Specification
        ↓
Implementation

Searching
        ↓
Problem Understanding
        ↓
Specification
        ↓
Implementation

Sorting
        ↓
Problem Understanding
        ↓
Specification
        ↓
Implementation

Trees
        ↓
Problem Understanding
        ↓
Specification
        ↓
Implementation

Graphs
        ↓
Problem Understanding
        ↓
Specification
        ↓
Implementation
```

The methodology remains constant regardless of the algorithm being studied.

---

# Relationship with Software Engineering

The study process aligns algorithmic reasoning with professional software development practices.

It integrates:

- problem analysis;
- software contracts;
- design patterns;
- testing;
- benchmarking;
- continuous integration.

This approach prepares the learner not only to implement algorithms but also to justify architectural and technical decisions in professional environments.

---

# Best Practices

Every algorithm should:

- begin with the problem;
- avoid immediate implementation;
- document assumptions;
- analyse edge cases;
- justify correctness;
- evaluate complexity;
- implement only after reasoning;
- verify behaviour through automated tests;
- compare performance experimentally;
- document technical conclusions.

Following this methodology promotes disciplined, reproducible, and professional algorithm development.

---

# Key Takeaways

The learner should remember the following principles:

- Algorithms should be understood before they are implemented.
- Specification precedes programming.
- Correctness precedes optimisation.
- Complexity analysis precedes benchmarking.
- Testing validates implementations but does not replace formal reasoning.
- Benchmarking complements theoretical analysis.
- Every algorithm should follow the same systematic methodology.

The **Problem-Solving Method** therefore becomes the methodological foundation of the entire **Algorithms Java Mastery** repository.

It unifies the concepts introduced throughout the **Foundations** module and establishes the standard workflow that will be applied consistently in every subsequent module, from Arrays and Searching to Graphs and Dynamic Programming.