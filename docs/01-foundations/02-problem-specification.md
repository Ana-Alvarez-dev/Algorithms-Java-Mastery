# Problem Specification

## Algorithms Java Mastery

This document introduces the formal specification of computational problems
within the **Foundations** module.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic study of algorithms throughout the repository.

Before designing an algorithm, it is necessary to understand precisely **what
problem must be solved**.

A poorly specified problem may lead to an incorrect algorithm even when the
implementation itself is syntactically valid and technically well written.

Problem specification therefore separates the computational problem from the
algorithmic strategy that will later solve it.

The central question addressed throughout this document is:

> **How should a computational problem be specified before designing an
> algorithm?**

---

# Purpose

The purpose of this document is to establish a disciplined method for describing
computational problems before proposing algorithmic solutions.

Many implementation errors originate from incomplete or ambiguous problem
understanding rather than from programming syntax.

For this reason, specification becomes one of the first formal activities in
algorithmic problem solving.

The progression is:

```text
Computational Problem
        ↓
Problem Understanding
        ↓
Problem Specification
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Implementation
```

A correct solution begins with a precise understanding of the problem that the
solution is expected to solve.

---

# Learning Objectives

After studying this document, the learner should be able to:

- distinguish between a computational problem and an algorithmic solution;
- describe a computational problem precisely;
- identify inputs and expected outputs;
- distinguish assumptions from constraints;
- identify valid and invalid problem instances;
- define the objective independently of implementation details;
- use examples to clarify the specification;
- recognise ambiguity in incomplete problem descriptions;
- understand how specifications support later correctness reasoning, testing,
  and implementation.

These competencies will be reused throughout every subsequent module of the
repository.

---

# Computational Problems

A **computational problem** describes a general relationship between valid
inputs and the outputs that should be produced.

The problem defines **what must be achieved**.

It does not prescribe the procedure used to achieve it.

For example:

> Determine the largest value contained in a finite array of integers.

This problem describes the required result.

It does not specify:

- which programming language should be used;
- whether iteration or recursion should be used;
- which variables should exist;
- how values should be compared;
- how the implementation should be organised.

Those decisions belong to the design and implementation of an algorithm.

Conceptually:

```text
Computational Problem
        ↓
Defines Required Behaviour
        ↓
Algorithm
        ↓
Provides a Solution
```

---

# Problem Instances

A computational problem represents a general class of related cases.

A concrete set of input values represents a **problem instance**.

For example, consider the problem:

> Determine the largest value contained in a finite array of integers.

Possible instances include:

```text
[7, 2, 15, 4, 9]

[5]

[-8, -3, -12]

[4, 4, 4, 4]
```

Each instance belongs to the same general computational problem.

This distinction becomes important because an algorithm is expected to solve
every valid instance covered by the problem specification, not merely a few
examples.

---

# Why Specification Matters

Algorithm design requires a precise understanding of the required behaviour.

Without a specification, the development process may become:

```text
Problem Description
        ↓
Assumptions Made Implicitly
        ↓
Guessing
        ↓
Implementation
```

This creates several risks:

- different interpretations of the same problem;
- incorrect assumptions;
- incomplete handling of valid inputs;
- missing edge cases;
- inconsistent tests;
- incorrect correctness arguments.

A disciplined process instead follows:

```text
Problem Description
        ↓
Analysis
        ↓
Specification
        ↓
Algorithm Design
        ↓
Verification
```

Specification reduces ambiguity by making the relevant properties of the
problem explicit.

---

# Elements of a Problem Specification

A computational problem specification should identify the information necessary
to understand the problem independently of any implementation.

Typical elements include:

- problem statement;
- input;
- output;
- constraints;
- assumptions;
- valid problem instances;
- invalid situations when relevant;
- representative examples.

These elements collectively define the boundaries of the problem.

---

# Problem Statement

The **problem statement** describes the objective clearly and concisely.

Example:

> Determine the largest value contained in a non-empty array of integers.

The problem statement should describe the required result without prescribing a
solution strategy.

A good problem statement answers:

> **What must be computed?**

It should not answer:

> **How should it be computed?**

---

# Input

The specification must define the information provided to the problem.

Examples of algorithmic inputs include:

- arrays;
- numerical values;
- strings;
- matrices;
- linked structures;
- trees;
- graphs.

For the maximum-value problem:

```text
Input

A finite array of integers containing at least one element.
```

A useful input definition should make the valid input domain understandable.

---

# Output

The specification must define the result that should be produced.

For the same problem:

```text
Output

The largest integer contained in the input array.
```

The output describes the required relationship between the problem instance and
its solution.

It should remain independent of implementation details.

---

# Constraints

**Constraints** define limitations inherent to the computational problem.

They may restrict:

- input size;
- allowed value ranges;
- ordering;
- uniqueness;
- available resources;
- structural properties.

Examples include:

```text
The array contains at least one element.
```

```text
The input values are integers.
```

```text
The array contains at most n elements.
```

Constraints may affect which algorithmic strategies are valid or appropriate.

They should therefore be identified before algorithm selection.

---

# Assumptions

**Assumptions** describe conditions treated as true within the problem model or
execution context.

Examples include:

```text
The supplied array exists.
```

```text
All elements can be represented by the selected numeric type.
```

```text
The graph representation satisfies the documented structural contract.
```

Assumptions should be explicit rather than silently inferred.

This prevents later disagreement between documentation, implementation, and
tests.

---

# Constraints vs Assumptions

Constraints and assumptions are related but should not be treated as identical.

A **constraint** restricts the valid problem space.

An **assumption** states a condition accepted as true for the analysis.

Conceptually:

```text
Constraint
    ↓
Defines the Limits of Valid Instances
```

```text
Assumption
    ↓
Defines a Condition Accepted During Reasoning
```

For example:

```text
Constraint:
The array contains at least one element.
```

```text
Assumption:
Each array element is a valid integer value.
```

Making this distinction explicit improves later specification and contract
design.

---

# Valid and Invalid Problem Instances

A specification should make it possible to determine whether a given input
belongs to the valid problem domain.

For example, if the problem states:

```text
Input

A non-empty array of integers.
```

then:

```text
[3, 8, 1]
```

is a valid problem instance.

However:

```text
[]
```

does not satisfy the specification.

Whether invalid instances should be rejected, transformed, or handled in a
specific way belongs to the behavioural contract developed in the following
document.

This distinction prepares the learner for the study of:

```text
Preconditions
        ↓
Postconditions
        ↓
Error Behaviour
```

---

# Representative Examples

Examples help clarify a specification by showing concrete problem instances and
their expected results.

For example:

```text
Input

[7, 2, 15, 4, 9]

Output

15
```

Additional examples may reveal important characteristics of the problem.

```text
Input

[-8, -3, -12]

Output

-3
```

```text
Input

[5]

Output

5
```

Examples support understanding, but they do not replace the formal
specification.

A finite set of examples cannot define every possible valid instance.

---

# Edge-Oriented Examples

When appropriate, examples should include situations that expose the boundaries
of the specification.

Examples may include:

- minimum valid input;
- repeated values;
- negative values;
- already ordered data;
- maximum permitted size;
- structurally unusual but valid inputs.

These examples later contribute to systematic edge-case analysis.

Edge cases are studied in greater detail in:

```text
04-edge-cases.md
```

---

# Separating the Problem from the Solution

A common mistake is to describe an algorithm while attempting to specify the
problem.

Consider the following statement:

> Traverse the array using a loop and compare every element while maintaining a
> current maximum.

This is not a problem specification.

It already describes a strategy.

A correct problem statement is:

> Determine the largest value contained in the array.

The difference is fundamental.

```text
Problem Specification
        ↓
Defines What Must Be Solved
```

```text
Algorithm Design
        ↓
Defines How It Will Be Solved
```

The repository therefore preserves the progression:

```text
Problem
        ↓
Specification
        ↓
Algorithm Design
```

rather than:

```text
Problem
        ↓
Java Code
```

---

# Example of a Complete Problem Specification

Consider the following computational problem.

## Problem Statement

Determine the largest value contained in a finite non-empty array of integers.

## Input

```text
A finite array of integers containing at least one element.
```

## Output

```text
The largest integer contained in the input array.
```

## Constraints

```text
The array contains at least one element.

Every element belongs to the integer domain supported by the problem model.
```

## Assumptions

```text
The input satisfies the declared problem constraints.
```

## Example 1

```text
Input:
[7, 2, 15, 4, 9]

Output:
15
```

## Example 2

```text
Input:
[-10, -4, -20]

Output:
-4
```

## Example 3

```text
Input:
[6]

Output:
6
```

No loop, variable, class, method, or Java-specific mechanism appears in this
specification.

The implementation remains intentionally undefined.

---

# Specification Before Algorithm Design

Once the problem has been specified, algorithmic design may begin.

Conceptually:

```text
Computational Problem
        ↓
Problem Specification
        ↓
Algorithmic Strategy
        ↓
Algorithm
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Implementation
```

The specification answers:

> **What behaviour is required?**

The algorithm answers:

> **How can that behaviour be produced computationally?**

These responsibilities should remain separate.

---

# Relationship with Preconditions and Postconditions

Problem specification provides the information required to formulate explicit
behavioural contracts.

The specification identifies:

```text
Input Domain
        ↓
Preconditions

Required Result
        ↓
Postconditions
```

For example:

```text
Problem:
Find the maximum element.
```

may later lead to:

```text
Precondition:
The input contains at least one valid element.
```

```text
Postcondition:
The returned value is greater than or equal to every element in the input.
```

These concepts are developed systematically in:

```text
03-preconditions-and-postconditions.md
```

---

# Relationship with Correctness

Correctness can only be reasoned about relative to a specification.

An algorithm cannot meaningfully be described as correct unless the expected
behaviour has first been defined.

Conceptually:

```text
Problem Specification
        ↓
Defines Required Behaviour
        ↓
Algorithm
        ↓
Correctness Reasoning
```

Correctness asks whether the algorithm satisfies the documented specification
for every valid input covered by its assumptions.

This topic is developed further in:

```text
05-correctness.md
```

---

# Relationship with Automated Testing

Problem specifications later provide the foundation for automated test design.

For example:

```text
Specification
        ↓
Input Categories
        ↓
Expected Behaviour
        ↓
Test Cases
```

Tests can therefore be derived from:

- valid input classes;
- boundaries;
- invalid situations;
- documented outputs;
- contractual guarantees.

Automated testing is developed formally in:

```text
docs/16-testing/
```

Testing does not replace the specification.

It provides executable evidence that selected problem instances behave
according to it.

---

# Relationship with Complexity Analysis

Constraints identified during specification may influence later complexity
analysis and algorithm selection.

For example:

```text
Small Input Domain
        ↓
One Strategy May Be Sufficient
```

```text
Very Large Input Domain
        ↓
Algorithmic Efficiency Becomes More Significant
```

However, the specification itself should not select an algorithm merely because
of anticipated performance.

Formal complexity analysis is developed in:

```text
docs/02-complexity/
```

---

# Best Practices

A disciplined problem specification should:

- state the computational objective clearly;
- distinguish the problem from the algorithm;
- identify the input domain;
- define the expected output;
- make important constraints explicit;
- document relevant assumptions;
- distinguish valid from invalid instances;
- include representative examples;
- include boundary-oriented examples when useful;
- remain independent of Java or any other implementation language;
- provide enough information to support later correctness reasoning and
  testing.

The specification should be precise without introducing unnecessary
implementation detail.

---

# Common Specification Mistakes

Several mistakes frequently weaken algorithmic problem descriptions.

## Describing the Algorithm Instead of the Problem

Incorrect:

> Iterate through every element and update the current maximum.

Correct:

> Determine the largest value contained in the input.

---

## Leaving the Input Domain Undefined

Weak:

> Find a value.

Better:

> Find a target integer within a finite array of integers.

---

## Leaving Invalid Situations Ambiguous

If empty input is not valid, this should be stated explicitly rather than left
to the implementation to decide accidentally.

---

## Using Examples as the Entire Specification

Examples illustrate behaviour but do not define the complete problem domain.

---

## Introducing Java Details Too Early

A specification should not depend on:

```text
for loops
ArrayList
methods
exceptions
classes
interfaces
```

unless those elements are themselves part of the problem being specified.

In this repository, algorithmic problems should generally remain
language-independent at the specification stage.

---

# Key Takeaways

After completing this document, the learner should understand that:

- every algorithm begins with a computational problem;
- a computational problem describes required behaviour;
- a specification defines that behaviour precisely;
- the specification describes **what**, not **how**;
- problem instances represent concrete cases of the general problem;
- inputs and outputs define the fundamental problem relationship;
- constraints define the limits of valid instances;
- assumptions make the reasoning context explicit;
- examples clarify a specification but do not replace it;
- correctness depends upon a previously defined specification;
- tests should later derive from the documented behaviour;
- implementation details should remain outside the problem specification.

Problem specification therefore represents one of the first formal stages of
disciplined algorithmic reasoning.

It establishes the foundation upon which algorithm design, correctness
reasoning, complexity analysis, Java implementation, and automated validation
will later be developed.

---

# Next Document

```text
03-preconditions-and-postconditions.md
```

The next document introduces explicit behavioural contracts.

It examines how **preconditions** define the conditions required before an
algorithm executes and how **postconditions** describe the guarantees that must
hold after successful execution.

These concepts transform the problem specification into a more precise basis
for correctness reasoning, implementation, and automated testing.

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The book provides the principal academic foundation for the systematic
description and analysis of computational problems throughout **Algorithms Java
Mastery**.

Additional academic and technical references supporting the repository are
documented in:

```text
docs/00-project/10-references.md
```

Complementary references may be introduced when a topic requires additional
formal treatment of specifications, contracts, or program correctness.