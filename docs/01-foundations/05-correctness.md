# Correctness

## Algorithms Java Mastery

This document introduces the concept of **algorithm correctness**, one of the
fundamental principles of Computer Science and a central component of the
**Foundations** module.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic study of algorithms throughout the repository.

Designing an algorithm is not merely a matter of producing an implementation
that compiles or executes successfully.

The essential question is whether the algorithm satisfies the specification of
the computational problem for every valid problem instance covered by its
contract.

Correctness therefore concerns the logical relationship between:

- the problem specification;
- the algorithmic strategy;
- the declared preconditions;
- the required postconditions;
- algorithm termination.

The central question addressed throughout this document is:

> **Why should an algorithm produce the required result for every valid problem
> instance?**

---

# Purpose

The purpose of this document is to establish the foundations required to reason
systematically about algorithm correctness.

Before analysing efficiency, optimising an implementation, or measuring
performance, it is necessary to determine whether the algorithm actually solves
the problem it was designed to solve.

The conceptual progression is:

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
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
```

Correctness reasoning belongs to the algorithmic level.

It should therefore be considered before implementation-specific optimisation or
experimental performance evaluation.

An efficient implementation of an incorrect algorithm does not solve the
intended computational problem.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define algorithm correctness;
- explain why correctness depends upon a specification;
- distinguish successful execution from correct behaviour;
- relate preconditions and postconditions to correctness;
- distinguish partial correctness from total correctness;
- explain the role of termination;
- recognise the role of invariants in correctness reasoning;
- distinguish correctness reasoning from automated testing;
- understand why correctness should be established before performance
  conclusions;
- identify the forms of evidence required to justify algorithm behaviour.

These concepts provide the theoretical foundation for every algorithm studied
throughout the repository.

---

# What Is Algorithm Correctness?

An algorithm is **correct with respect to a specification** when it satisfies the
required behaviour for every valid input covered by that specification.

Correctness is therefore not an isolated property of the algorithm.

It is always evaluated relative to the problem being solved.

Conceptually:

```text
Problem Specification
        ↓
Defines Required Behaviour
        ↓
Algorithm
        ↓
Correctness Reasoning
        ↓
Required Result
```

The specification establishes what the algorithm must accomplish.

Correctness reasoning establishes why the algorithm actually accomplishes it.

---

# Correctness Is Relative to a Specification

A statement such as:

> This algorithm is correct.

is incomplete unless the required behaviour has already been defined.

The same implementation may be correct for one specification and incorrect for
another.

For example, consider a searching procedure.

If the specification requires:

```text
Return any index containing the target value.
```

then returning any valid occurrence may be correct.

If the specification instead requires:

```text
Return the first index containing the target value.
```

then the same behaviour may no longer satisfy the contract.

Correctness must therefore always be interpreted relative to:

```text
Specification
        ↓
Preconditions
        ↓
Postconditions
```

---

# Correctness Is Not Successful Execution

A program may compile, execute, and terminate without satisfying the
specification.

For example:

```text
Program Compiles
        ↓
Program Executes
        ↓
Program Terminates
        ↓
Incorrect Result
```

Successful execution proves only that the program completed under particular
conditions.

It does not establish that the algorithm is correct.

Correctness depends upon the relationship between the produced result and the
required postconditions.

---

# Correctness and the Valid Problem Domain

Correctness is evaluated for inputs satisfying the documented preconditions.

Conceptually:

```text
Input
   ↓
Satisfies Preconditions?
   │
   ├── Yes
   │     ↓
   │   Algorithm Must Satisfy Postconditions
   │
   └── No
         ↓
      Outside Declared Contract
```

If an input lies outside the declared problem domain, the algorithm is not
required to satisfy the same postconditions unless the public contract
explicitly defines behaviour for that situation.

This distinction prevents invalid inputs from being incorrectly included in a
correctness claim.

---

# Preconditions and Correctness

Preconditions define the conditions under which the correctness claim applies.

For example:

> Determine the largest value contained in a finite non-empty integer array.

A possible precondition is:

```text
The input array contains at least one element.
```

The correctness argument may therefore assume that the array is non-empty.

An empty input:

```text
[]
```

does not satisfy this particular contract.

Its treatment belongs to the invalid-input policy of the implementation rather
than to the correctness proof for the declared valid domain.

---

# Postconditions and Correctness

Postconditions define the guarantees that a correct algorithm must establish.

For the maximum-value problem, possible postconditions include:

```text
The returned value belongs to the input array.
```

and:

```text
Every element in the input array is less than or equal to the returned value.
```

A correctness argument must justify why both properties hold after execution.

Conceptually:

```text
Preconditions
        ↓
Algorithm
        ↓
Reasoning
        ↓
Postconditions Established
```

Correctness therefore connects the initial contract with the final guaranteed
state.

---

# Partial Correctness

**Partial correctness** addresses the result produced by an algorithm under the
assumption that the algorithm terminates.

It answers:

> **If execution terminates and the preconditions were satisfied, are the
> required postconditions guaranteed to hold?**

Conceptually:

```text
Preconditions Hold
        ↓
Algorithm Executes
        ↓
Assume Termination
        ↓
Postconditions Hold
```

Partial correctness does not establish that termination actually occurs.

It establishes that if the computation finishes, the result is correct
according to the specification.

---

# Termination

Termination asks whether the algorithm eventually completes for every valid
input covered by its specification.

A termination argument must establish that execution cannot continue
indefinitely.

Conceptually:

```text
Valid Input
        ↓
Algorithm Execution
        ↓
Finite Progress
        ↓
Termination
```

Different algorithms require different forms of termination reasoning.

Examples include:

- a loop variable moves toward a fixed boundary;
- the remaining search interval becomes smaller;
- recursive calls operate on strictly smaller subproblems;
- a finite data structure is progressively consumed.

Termination is a separate logical responsibility from partial correctness.

---

# Total Correctness

**Total correctness** combines two properties:

```text
Partial Correctness
        +
Termination
        ↓
Total Correctness
```

An algorithm is totally correct with respect to its specification when:

1. it terminates for every valid input covered by the contract; and
2. when it terminates, the required postconditions hold.

Conceptually:

```text
Preconditions
        ↓
Algorithm
        ↓
Terminates
        +
Postconditions Hold
        ↓
Total Correctness
```

This distinction provides a more precise way to reason about algorithmic
behaviour.

---

# Example — Maximum Element

Consider the problem:

> Determine the largest value contained in a finite non-empty integer array.

## Preconditions

```text
The input array contains at least one element.
```

## Postconditions

```text
The returned value belongs to the input array.

Every input value is less than or equal to the returned value.
```

A possible strategy is:

1. treat the first element as the current maximum;
2. examine each remaining element;
3. replace the current maximum whenever a larger value is encountered;
4. return the final maximum.

A correctness argument must establish that after all elements have been
processed:

```text
The current maximum is the largest value among all processed elements.
```

When the final element has been processed, the processed region contains the
entire array.

Therefore:

```text
Current Maximum
        ↓
Largest Value in Entire Array
```

This reasoning provides the basis for establishing the required
postconditions.

---

# Correctness and Invariants

Many iterative correctness arguments rely on **invariants**.

An invariant is a property that remains true at defined stages throughout
algorithm execution.

Conceptually:

```text
Precondition
        ↓
Initialisation
        ↓
Invariant Holds
        ↓
Iteration
        ↓
Invariant Preserved
        ↓
Termination
        ↓
Postcondition
```

For the maximum-value example, a possible invariant is:

> At the beginning of each iteration, `maximum` contains the largest value among
> the elements already processed.

To use an invariant in correctness reasoning, three questions are commonly
examined:

1. **Initialisation** — Why is the invariant true before the first relevant
   iteration?
2. **Maintenance** — Why does each iteration preserve the invariant?
3. **Termination** — How does the invariant imply the required postcondition
   when execution finishes?

Invariant reasoning is developed systematically in:

```text
06-invariants.md
```

---

# Correctness and Edge Cases

Correctness applies to every valid problem instance, including valid edge cases.

Conceptually:

```text
Valid Input Domain
        │
        ├── Ordinary Cases
        │
        └── Edge Cases
                ↓
        Correctness Must Hold
```

For example, if a maximum-value algorithm accepts every non-empty integer array,
then correctness must also hold for:

```text
[8]
```

```text
[-9, -2, -15]
```

```text
[5, 5, 5]
```

Edge cases are useful because they often reveal assumptions that were not
included explicitly in the correctness reasoning.

Edge-case analysis is developed in:

```text
04-edge-cases.md
```

---

# Correctness and Problem Decomposition

Complex correctness arguments may become easier when the problem is decomposed
into smaller responsibilities.

For example:

```text
Complex Problem
        ↓
Subproblem A
Subproblem B
Subproblem C
```

If each component has a clear specification and the relationships between them
are correct, the complete solution can often be reasoned about more
systematically.

Problem decomposition is studied in:

```text
07-problem-decomposition.md
```

---

# Correctness and Testing

Correctness reasoning and automated testing provide different forms of
evidence.

## Correctness Reasoning

Attempts to justify behaviour across the complete valid problem domain.

```text
Specification
        ↓
Logical Reasoning
        ↓
General Correctness Argument
```

## Automated Testing

Executes the implementation for selected concrete problem instances.

```text
Selected Input
        ↓
Implementation
        ↓
Observed Result
        ↓
Assertion
```

Testing can reveal defects.

Testing can increase confidence.

Testing can verify important representative and boundary cases.

But a finite collection of tests generally cannot establish correctness for
every possible valid input.

Therefore:

> **Testing provides executable evidence; correctness reasoning provides a
> general argument.**

Both are important, but they are not interchangeable.

Automated testing is developed systematically in:

```text
docs/16-testing/
```

---

# Correctness and Implementation

Correctness reasoning applies primarily to the algorithmic strategy.

The Java implementation must then faithfully preserve that strategy and its
contract.

Conceptually:

```text
Correct Algorithmic Strategy
        ↓
Java Translation
        ↓
Implementation Behaviour
```

An algorithm may be correct while its implementation contains a programming
error.

For example:

- an incorrect array boundary;
- an incorrect comparison operator;
- an accidental mutation;
- an incorrect return value.

Therefore, two questions remain distinct:

```text
Is the algorithm correct?
```

and:

```text
Does the implementation correctly represent the algorithm?
```

Automated testing later helps provide evidence for the second question.

---

# Correctness Before Complexity

Correctness and complexity answer different questions.

Correctness asks:

> **Does the algorithm solve the specified problem?**

Complexity asks:

> **How do the computational resources required by the algorithm grow as input
> size increases?**

The proper conceptual order is:

```text
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
```

An algorithm whose correctness has not been established should not be preferred
merely because it appears computationally efficient.

Formal complexity analysis is developed in:

```text
docs/02-complexity/
```

---

# Correctness Before Benchmarking

Benchmarking evaluates observed performance.

It does not establish correctness.

The progression throughout the repository is therefore:

```text
Specification
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Benchmarking When Appropriate
```

A fast incorrect implementation remains incorrect.

Performance evaluation is therefore meaningful only after the intended
behaviour has been established and the implementation has been validated.

Benchmarking is developed in:

```text
docs/17-benchmarking/
```

---

# Forms of Correctness Reasoning

Different algorithms may require different reasoning techniques.

Examples include:

- direct logical reasoning;
- loop invariants;
- mathematical induction;
- structural induction;
- recursive reasoning;
- contradiction;
- decomposition into verified subproblems.

The appropriate technique depends on the algorithm.

The repository does not require the same degree of formal proof for every
implementation.

However, the learner should always be able to explain **why the strategy should
satisfy its specification**.

---

# Correctness Reasoning Process

A systematic correctness analysis may follow this progression:

```text
1. Identify the problem specification.
        ↓
2. Identify the preconditions.
        ↓
3. Identify the required postconditions.
        ↓
4. Describe the algorithmic strategy.
        ↓
5. Identify useful invariants or structural properties.
        ↓
6. Explain why those properties are established.
        ↓
7. Explain why they remain valid during execution.
        ↓
8. Establish termination.
        ↓
9. Connect the final state to the postconditions.
```

Not every algorithm requires each step in the same form.

The reasoning should remain proportional to the complexity and educational
purpose of the problem.

---

# Common Correctness Mistakes

## Successful Examples as Proof

Incorrect reasoning:

> It worked for five inputs, therefore the algorithm is correct.

Examples provide evidence but do not establish behaviour over the complete
valid domain.

---

## Ignoring Preconditions

A correctness argument that silently assumes properties not contained in the
contract is incomplete.

---

## Forgetting Termination

Showing that the result would be correct **if execution finishes** establishes
partial correctness, not necessarily total correctness.

---

## Using Performance as Evidence of Correctness

A fast implementation is not necessarily correct.

Correctness and performance must be evaluated separately.

---

## Using Tests as a Complete Proof

Tests can detect defects and validate representative cases but generally do not
cover every possible valid input.

---

## Proving the Implementation Instead of the Algorithmic Idea

Implementation details may distract from the underlying reasoning.

The correctness argument should first explain why the algorithmic strategy
works.

Implementation-specific behaviour can then be verified separately.

---

# Best Practices

When reasoning about algorithm correctness:

- begin from a precise specification;
- identify preconditions explicitly;
- state the required postconditions;
- reason about every valid input covered by the contract;
- separate partial correctness from termination;
- identify useful invariants;
- analyse valid edge cases;
- avoid relying exclusively on examples;
- keep correctness reasoning independent of programming language details when
  possible;
- use automated testing as complementary implementation evidence;
- establish correctness before interpreting performance.

Correctness reasoning should be precise enough to explain the algorithm without
introducing unnecessary formalism.

---

# Correctness Throughout This Repository

Every algorithm studied throughout **Algorithms Java Mastery** follows a
structured progression.

```text
Problem Understanding
        ↓
Formal Specification
        ↓
Preconditions and Postconditions
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Experimental Benchmarking
        ↓
Engineering Conclusions
```

The exact level of correctness formalisation may vary according to the topic.

However, every implementation should be supported by enough reasoning to
explain why the selected algorithm solves the documented problem.

---

# Key Takeaways

After completing this document, the learner should understand that:

- correctness is always relative to a specification;
- successful execution does not establish correctness;
- preconditions define the domain in which the correctness claim applies;
- postconditions define the guarantees a correct algorithm must establish;
- partial correctness assumes termination and examines the resulting behaviour;
- termination must be established separately;
- total correctness combines partial correctness and termination;
- valid edge cases belong to the correctness domain;
- invariants provide an important technique for reasoning about iterative
  algorithms;
- correctness reasoning and testing provide different forms of evidence;
- algorithm correctness and implementation correctness are related but distinct;
- correctness should be established before complexity or performance
  conclusions.

Algorithm correctness therefore provides the logical bridge between **problem
specification**, **algorithmic contracts**, **invariants**, **implementation**,
and **automated validation**.

It establishes the reasoning required to explain not merely that an algorithm
appears to work, but **why it should work for every valid problem instance
covered by its specification**.

---

# Next Document

```text
06-invariants.md
```

The next document introduces algorithmic invariants as a systematic technique
for reasoning about properties that remain true throughout execution.

It develops the relationship between:

```text
Preconditions
        ↓
Initialisation
        ↓
Invariant
        ↓
Maintenance
        ↓
Termination
        ↓
Postconditions
```

Invariant reasoning provides one of the principal tools used throughout the
repository for analysing and explaining algorithm correctness.

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The book provides the principal academic foundation for reasoning about
algorithm correctness, loop invariants, termination, and the relationship
between algorithms and their specifications throughout **Algorithms Java
Mastery**.

Complementary references concerning formal program correctness, preconditions,
postconditions, and invariants are listed in:

```text
docs/00-project/10-references.md
```

In particular, more formal treatment may be supported by works such as
Dijkstra's *A Discipline of Programming* and Gries's *The Science of
Programming* when additional depth is appropriate.