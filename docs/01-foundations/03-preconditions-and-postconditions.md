# Preconditions and Postconditions

## Algorithms Java Mastery

This document introduces **preconditions** and **postconditions** as fundamental
components of precise algorithm specification.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic study of algorithms throughout the repository.

After defining a computational problem, the next step is to establish the
conditions under which an algorithm is expected to operate and the guarantees
that must hold when it completes correctly.

Preconditions and postconditions provide this behavioural contract.

They do not describe how an algorithm performs its computation.

Instead, they define:

- what must be true before execution;
- what the algorithm may assume about valid input;
- what must be true after successful execution.

The central question addressed throughout this document is:

> **Under which conditions may an algorithm operate, and what must it guarantee
> when it finishes?**

---

# Purpose

The purpose of this document is to establish a precise understanding of
**algorithmic contracts**.

A computational problem defines required behaviour.

A contract refines that specification by identifying the conditions that must
hold before and after execution.

Conceptually:

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

Preconditions define the valid conditions under which the algorithm is expected
to satisfy its specification.

Postconditions define the result that a correct algorithm must guarantee under
those conditions.

Understanding these relationships provides the foundation for later reasoning
about correctness, edge cases, implementation behaviour, and automated testing.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define preconditions and postconditions;
- distinguish assumptions, constraints, preconditions, and guarantees;
- identify valid execution conditions;
- describe algorithm behaviour through explicit contracts;
- formulate implementation-independent postconditions;
- explain the relationship between contracts and correctness;
- derive relevant test scenarios from behavioural contracts;
- recognise when a contract should be documented, validated, or enforced;
- maintain consistency between specification, implementation, and tests.

These competencies will be reused throughout every algorithm and data structure
studied later in the repository.

---

# Algorithmic Contracts

An algorithm can be studied through the relationship between what it requires
and what it guarantees.

Conceptually:

```text
Preconditions
        ↓
Algorithm
        ↓
Postconditions
```

A contract separates two responsibilities.

## Required Conditions

These describe what must already hold before the algorithm is expected to
satisfy its specification.

## Guaranteed Conditions

These describe what must hold after successful execution of a correct
algorithm.

This relationship provides a precise basis for reasoning about behaviour.

---

# Preconditions

A **precondition** is a condition that must hold before an algorithm is expected
to satisfy its specified postconditions.

Preconditions define part of the valid domain of operation.

Examples include:

```text
The input array contains at least one element.
```

```text
The search input is sorted according to the required ordering.
```

```text
The supplied index belongs to the valid range.
```

```text
The divisor is different from zero.
```

```text
The graph satisfies the representation invariants required by the algorithm.
```

A precondition should be established before algorithm design whenever the
solution depends upon it.

---

# Characteristics of Preconditions

A useful precondition should be:

- explicit;
- relevant to the algorithm;
- objectively interpretable;
- consistent with the problem specification;
- independent of implementation details whenever possible.

A precondition describes **when the contract applies**.

It should not describe the internal steps of the algorithm.

For example:

Incorrect:

```text
The algorithm must use a loop starting at index zero.
```

This describes implementation.

Better:

```text
The supplied array is non-empty.
```

This describes a condition required by the contract.

---

# Preconditions and the Valid Input Domain

Preconditions help distinguish valid problem instances from inputs outside the
declared contract.

For example:

```text
Problem

Determine the maximum element of a non-empty integer array.
```

Possible preconditions:

```text
The input reference is valid according to the public contract.

The array contains at least one element.
```

Then:

```text
[4, 8, 2]
```

belongs to the valid domain.

An empty array:

```text
[]
```

does not satisfy the non-empty precondition.

What the Java implementation does when such a condition is violated is a
separate engineering decision that must be documented consistently.

Possible policies may include:

- explicit rejection;
- an exception;
- a different API contract;
- redesigning the problem to accept the case.

The precondition itself describes the required condition; implementation policy
defines how violations are handled.

---

# Assumptions vs Preconditions

Assumptions and preconditions are closely related but should not be treated as
identical concepts.

An **assumption** is a condition accepted as part of the problem model or
analysis.

A **precondition** is a condition that forms part of the behavioural contract
required before an algorithm is expected to satisfy its guarantees.

Conceptually:

```text
Problem Model
        ↓
Assumptions
```

```text
Algorithm Contract
        ↓
Preconditions
```

For example:

```text
Assumption:
Integer values behave according to the numeric model adopted by the problem.
```

```text
Precondition:
The input array contains at least one element.
```

Some assumptions may eventually become explicit preconditions when they affect
the public behaviour of an implementation.

Making the distinction visible reduces hidden reasoning.

---

# Example of Preconditions

Consider the problem:

> Determine the largest value contained in a finite integer array.

A possible contract may require:

```text
Preconditions

1. The input is a valid array according to the defined interface.
2. The array contains at least one element.
```

These conditions define the domain for which the algorithm must establish its
postconditions.

No implementation strategy has yet been selected.

---

# Postconditions

A **postcondition** describes what must be true after an algorithm successfully
completes, assuming its required preconditions were satisfied.

The postcondition expresses the guarantee provided by the algorithm.

Conceptually:

```text
Satisfied Preconditions
        ↓
Correct Algorithm
        ↓
Guaranteed Postconditions
```

A postcondition should describe observable or mathematically meaningful
behaviour rather than internal implementation state.

---

# Characteristics of Postconditions

A well-defined postcondition should:

- describe the required result;
- be objectively verifiable or logically analysable;
- remain independent of implementation strategy;
- correspond directly to the problem specification;
- provide enough precision to support correctness reasoning.

It should explain **what must hold**, not **how the algorithm produced it**.

---

# Example of Postconditions

Consider again:

> Determine the largest value contained in a finite non-empty integer array.

Possible postconditions are:

```text
The returned value belongs to the input array.
```

and:

```text
No element in the input array is greater than the returned value.
```

Together, these statements define the required result without specifying:

- loops;
- variables;
- iteration order;
- recursion;
- Java methods.

Different algorithms may satisfy the same postconditions using different
strategies.

---

# Preconditions vs Postconditions

Preconditions and postconditions describe opposite sides of the same contract.

```text
Before Execution
        ↓
Preconditions
        ↓
Algorithm
        ↓
Postconditions
        ↓
After Successful Execution
```

The distinction may be summarised as:

| Concept | Question |
|---|---|
| Precondition | What must already be true? |
| Postcondition | What must the algorithm guarantee? |

Preconditions constrain valid execution conditions.

Postconditions define required results.

---

# Example of a Complete Algorithmic Contract

Consider the problem:

> Determine the largest value in a finite non-empty integer array.

A compact contract may be written as follows.

```text
Problem

Determine the largest value contained in the input array.

Preconditions

- The input satisfies the declared array contract.
- The array contains at least one element.

Postconditions

- The returned value belongs to the input array.
- Every element in the input array is less than or equal to the returned value.
```

Notice that this contract says nothing about the algorithmic strategy.

Possible implementations may:

- iterate from left to right;
- use divide and conquer;
- use another valid strategy.

If they satisfy the same contract, they solve the same specified problem.

---

# Contracts and Algorithm Design

Contracts should generally be established before implementation.

The progression is:

```text
Computational Problem
        ↓
Problem Specification
        ↓
Preconditions and Postconditions
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Implementation
```

The contract provides a stable target against which candidate algorithms can be
evaluated.

Without it, implementation decisions may silently redefine the original
problem.

---

# Relationship with Correctness

Correctness is defined relative to a specification and its contract.

For a valid input satisfying the preconditions, a correct algorithm must
establish the required postconditions.

Conceptually:

```text
Preconditions
        ↓
Algorithm Execution
        ↓
Postconditions
```

This relationship provides the basis for correctness reasoning.

A useful logical interpretation is:

```text
If the preconditions hold
        ↓
and the algorithm executes correctly
        ↓
then the postconditions must hold
```

Later correctness analysis examines why this relationship is guaranteed for all
valid inputs covered by the specification.

Correctness is developed systematically in:

```text
05-correctness.md
```

---

# Partial and Total Correctness

Contracts also prepare the learner for an important distinction developed later.

## Partial Correctness

If the algorithm terminates and the preconditions were satisfied, the required
postconditions hold.

## Total Correctness

The algorithm satisfies the required postconditions **and** terminates for every
valid input covered by the specification.

Conceptually:

```text
Partial Correctness
        +
Termination
        ↓
Total Correctness
```

These ideas are developed in greater depth in:

```text
05-correctness.md
```

---

# Relationship with Invariants

Preconditions and postconditions describe conditions before and after algorithm
execution.

Invariants describe properties that remain true during relevant stages of the
execution.

Conceptually:

```text
Precondition
        ↓
Initial State
        ↓
Invariant Preserved During Execution
        ↓
Termination
        ↓
Postcondition
```

This relationship becomes especially important when proving the correctness of
iterative algorithms.

Invariants are studied in:

```text
06-invariants.md
```

---

# Relationship with Edge Cases

Contracts help identify important boundaries in the problem domain.

For example, if a precondition states:

```text
The array contains at least one element.
```

then the smallest valid instance is:

```text
[array containing one element]
```

and an empty array lies outside that specific contract.

This naturally leads to questions such as:

- What is the minimum valid input?
- Which inputs violate the contract?
- Which unusual but valid situations must still be handled correctly?
- Which cases challenge the postconditions?

These questions are developed systematically in:

```text
04-edge-cases.md
```

---

# Relationship with Automated Testing

Contracts provide an important foundation for test design.

Preconditions help identify input categories.

Postconditions define observable expectations.

Conceptually:

```text
Specification
        ↓
Contract
        ↓
Representative Inputs
        ↓
Execution
        ↓
Assertions About Postconditions
```

For example:

```text
Precondition

Array contains at least one element.

        ↓

Representative Inputs

[5]
[4, 7, 2]
[-8, -3, -10]

        ↓

Postcondition

Returned value is greater than or equal to every input element.
```

Later, JUnit Jupiter and AssertJ can provide executable verification of selected
instances.

Automated testing is studied formally in:

```text
docs/16-testing/
```

Testing provides evidence about concrete executions.

It does not replace general correctness reasoning.

---

# Contract Validation vs Algorithm Testing

Contract validation and behavioural testing should not be confused.

## Contract Validation

May verify whether an input satisfies conditions required by the public API.

For example:

```text
Reject a null reference.
```

or:

```text
Reject an empty array when the API requires non-empty input.
```

## Behavioural Testing

Verifies that valid inputs produce results satisfying the algorithm's
postconditions.

For example:

```text
Given [4, 9, 2],
the maximum algorithm returns 9.
```

Both forms of verification may be useful, but they answer different questions.

---

# Are Preconditions Always Checked at Runtime?

Not necessarily.

A precondition is first a **logical condition of the contract**.

Whether it should also be checked explicitly at runtime depends on the API
design and engineering context.

Possible approaches include:

```text
Documented Precondition
        ↓
Caller Responsible for Satisfying It
```

or:

```text
Documented Precondition
        ↓
Implementation Validates It
        ↓
Defined Failure Behaviour
```

For public Java APIs in this repository, important invalid-input behaviour
should be explicit and consistent with the documented contract.

However, the conceptual definition of a precondition should remain distinct
from the mechanism used to enforce it.

---

# Relationship with Complexity Analysis

Preconditions may affect complexity analysis because they define properties of
the valid input domain.

For example, binary search commonly requires:

```text
Input is sorted.
```

That precondition enables a strategy whose search space can be repeatedly
reduced.

However, complexity is a separate analytical property.

Contracts define required behaviour and valid conditions.

Complexity analysis examines resource growth.

Formal complexity analysis is developed in:

```text
docs/02-complexity/
```

---

# Relationship with Benchmarking

Contracts do not directly establish performance.

Their role is to ensure that implementations being compared solve the intended
problem under compatible conditions.

The proper progression is:

```text
Specification
        ↓
Contract
        ↓
Correctness Reasoning
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Verified Behaviour
        ↓
Benchmarking When Appropriate
```

A benchmark should never be used to compensate for an unclear or violated
contract.

Performance evaluation is developed in:

```text
docs/17-benchmarking/
```

---

# Contract Consistency

A contract should remain consistent across all representations of the same
algorithm.

Conceptually:

```text
Documentation
        ↓
Defines Contract

Java Implementation
        ↓
Implements Contract

Automated Tests
        ↓
Verify Contract

Benchmarks
        ↓
Measure Implementations Operating Under the Contract
```

A contradiction between these artefacts indicates a project inconsistency that
should be resolved.

The specification remains the conceptual authority for expected behaviour.

---

# Best Practices

When defining algorithmic contracts:

- state important preconditions explicitly;
- define postconditions in terms of required behaviour;
- distinguish assumptions from contractual requirements;
- avoid implementation-specific wording;
- make invalid-input policy explicit when relevant;
- keep contracts consistent with the problem specification;
- derive correctness reasoning from the contract;
- derive test expectations from observable guarantees;
- document mutation behaviour when relevant;
- avoid introducing conditions that are not required by the problem.

Contracts should improve precision without creating unnecessary complexity.

---

# Common Mistakes

## Leaving Preconditions Implicit

Weak:

```text
Binary search receives an array.
```

Better:

```text
Binary search receives an array ordered according to the required comparison
relation.
```

The ordering requirement affects algorithm correctness and must not remain
implicit.

---

## Describing Implementation as a Postcondition

Incorrect:

```text
The variable maximum contains the final value.
```

This describes internal state.

Better:

```text
The returned value is greater than or equal to every value in the input.
```

This describes observable behaviour.

---

## Treating Examples as Contracts

A few successful examples do not define the general postcondition.

Contracts should describe properties that hold across the valid problem domain.

---

## Confusing Preconditions with Runtime Validation

A precondition defines a condition of the contract.

Runtime validation is one possible implementation policy for handling that
condition.

The two concepts should remain distinguishable.

---

## Modifying Tests Instead of Resolving Contract Inconsistency

If documentation, implementation, and tests disagree, the inconsistency should
be analysed.

Tests should not simply be changed to accept incorrect behaviour.

The authoritative specification and intended contract must first be clarified.

---

# Key Takeaways

After completing this document, the learner should understand that:

- preconditions describe conditions required before execution;
- postconditions describe guarantees required after successful execution;
- assumptions and preconditions are related but distinct;
- contracts remain independent of implementation strategy;
- a correct algorithm must satisfy its postconditions for valid inputs;
- contracts provide the logical foundation for correctness reasoning;
- edge cases emerge naturally from examining contract boundaries;
- tests can be derived from preconditions and postconditions;
- runtime validation is an engineering decision separate from the conceptual
  definition of a precondition;
- benchmarks require compatible and correctly implemented contracts before
  performance comparisons are meaningful.

Preconditions and postconditions therefore provide the behavioural bridge
between **problem specification**, **algorithm design**, **correctness
reasoning**, **Java implementation**, and **automated validation**.

---

# Next Document

```text
04-edge-cases.md
```

The next document examines boundary, exceptional, and unusual problem instances
that frequently expose incomplete specifications or incorrect algorithms.

It develops the ability to identify cases that deserve explicit analysis before
implementation and later become important sources of automated test scenarios.

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The systematic use of specifications and correctness conditions throughout the
study of algorithms provides the principal academic context for this document.

Additional treatment of formal program reasoning, preconditions,
postconditions, and invariants may be supported by the complementary references
listed in:

```text
docs/00-project/10-references.md
```

Individual documents may introduce specialised references when greater
formalisation is required.