# Edge Cases

## Algorithms Java Mastery

This document introduces the analysis of **edge cases** and **boundary cases**
within the **Foundations** module.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic study of algorithms throughout the repository.

After a computational problem has been specified and its contractual conditions
have been identified, the next step is to analyse the situations most likely to
expose incomplete assumptions, incorrect reasoning, or fragile algorithmic
behaviour.

Edge-case analysis therefore examines the boundaries of the problem domain
before implementation begins.

The central question addressed throughout this document is:

> **Which problem instances are most likely to expose incorrect or incomplete
> algorithmic behaviour?**

---

# Purpose

The purpose of this document is to establish a disciplined method for analysing
boundary, unusual, and contract-sensitive problem instances before designing or
implementing an algorithm.

Many defects do not originate from the central algorithmic idea itself.

They appear because the problem analysis failed to consider:

- minimum valid inputs;
- maximum relevant values;
- unusual but valid structures;
- repeated values;
- degenerate cases;
- inputs near contractual boundaries;
- invalid inputs outside the declared domain.

The progression is:

```text
Problem Specification
        ↓
Preconditions and Postconditions
        ↓
Input-Domain Analysis
        ↓
Edge Cases and Boundary Cases
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Implementation
        ↓
Automated Testing
```

Edge cases should therefore be identified during analysis rather than discovered
accidentally after implementation.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define edge cases and boundary cases;
- distinguish ordinary valid inputs from boundary valid inputs;
- distinguish edge cases from invalid inputs;
- identify common categories of algorithmic boundary conditions;
- analyse behaviour near the limits of a problem specification;
- recognise assumptions that may fail at boundaries;
- connect edge-case analysis with correctness reasoning;
- derive meaningful test scenarios from identified boundaries;
- determine when an input lies outside the algorithmic contract.

These competencies will be reused throughout every algorithm and data structure
studied later in the repository.

---

# What Is an Edge Case?

An **edge case** is a valid problem instance located at or near an important
boundary of the specified input domain.

Such cases are often more likely to reveal incorrect assumptions than ordinary
inputs.

Examples may include:

- the smallest valid collection;
- the largest relevant value;
- duplicated values;
- all values being equal;
- already ordered input;
- reverse-ordered input;
- highly unbalanced structures;
- values near numeric limits.

Edge cases are not important because they are necessarily rare.

They are important because they exercise the **boundaries of the specification
or algorithmic reasoning**.

---

# Edge Cases vs Invalid Inputs

Edge cases and invalid inputs should not be treated as identical.

An **edge case** is normally still inside the valid problem domain.

An **invalid input** violates the specification or one of the algorithm's
preconditions.

Conceptually:

```text
Input Domain
        │
        ├── Ordinary Valid Inputs
        │
        └── Edge Valid Inputs

Outside Contract
        ↓
Invalid Inputs
```

For example, consider the problem:

> Determine the largest value contained in a non-empty integer array.

Then:

```text
[5]
```

is a valid edge case because it is the smallest permitted array.

However:

```text
[]
```

is not an edge case under that specification.

It violates the non-empty precondition and therefore lies outside the valid
problem domain.

This distinction is essential for correct contract reasoning.

---

# Why Edge Cases Matter

Algorithms are often first understood using ordinary examples.

For example:

```text
[7, 2, 15, 4, 9]
```

This input may help demonstrate the central strategy.

However, it does not reveal whether the reasoning also holds for situations
such as:

```text
[8]
```

```text
[5, 5, 5, 5]
```

```text
[-9, -2, -15]
```

```text
[Integer.MIN_VALUE, Integer.MAX_VALUE]
```

Boundary-oriented inputs may expose:

- incorrect initialisation;
- off-by-one errors;
- invalid stopping conditions;
- hidden assumptions;
- incorrect comparisons;
- failure to preserve invariants;
- overflow-related behaviour;
- contract inconsistencies.

Edge-case analysis therefore strengthens both specification and algorithm
design.

---

# Ordinary Cases and Boundary Cases

Valid problem instances may be classified conceptually as:

```text
Valid Problem Instances
        │
        ├── Ordinary Cases
        │
        └── Boundary or Edge Cases
```

Ordinary cases demonstrate expected central behaviour.

Boundary cases test whether the same reasoning remains valid near important
limits of the problem domain.

A correct algorithm must satisfy its specification for both.

---

# Common Categories of Edge Cases

Different computational problems expose different boundaries.

The following categories are common but should not be treated as a universal
checklist.

---

## Minimum Valid Input

Many problems define a smallest valid instance.

For example:

```text
[8]
```

for a problem requiring a non-empty array.

Questions include:

- Does the algorithm terminate correctly?
- Is initialisation valid?
- Does the result satisfy the postcondition?
- Are unnecessary operations performed?

Minimum valid inputs frequently expose incorrect assumptions about iteration or
indexing.

---

## Empty Input

An empty structure may be either:

- a valid edge case; or
- an invalid input.

The distinction depends entirely on the specification.

For example:

```text
[]
```

may be valid for:

> Sort an array.

but invalid for:

> Return the maximum value in a non-empty array.

Therefore, the correct question is not:

> Is empty input an edge case?

but:

> **Does the specification include empty input in the valid domain?**

---

## Duplicate Values

Repeated values often expose hidden assumptions.

Example:

```text
[5, 5, 5, 5]
```

Questions may include:

- Does the algorithm remain correct?
- Does it terminate?
- Does it preserve required multiplicity?
- Does duplicate handling affect which result is valid?

For searching algorithms, duplicates may also affect which matching index is
permitted by the specification.

---

## Single Distinct Value

A collection may contain many elements while representing only one distinct
value.

Example:

```text
[4, 4, 4, 4, 4]
```

This may challenge assumptions about ordering, comparison, or progress.

---

## Minimum and Maximum Numeric Values

Numerical algorithms should consider the limits of the declared numeric domain.

In Java-oriented implementations, examples may later include:

```text
Integer.MIN_VALUE
Integer.MAX_VALUE
```

These values may expose:

- overflow;
- underflow-related assumptions;
- incorrect arithmetic;
- comparison errors;
- sentinel-value conflicts.

The conceptual problem should first be understood independently of Java-specific
numeric limits, while the implementation stage later examines how the selected
type represents that domain.

---

## Negative Values

If negative values belong to the valid input domain, they should be considered
explicitly.

Example:

```text
[-9, -2, -15]
```

Such cases frequently expose incorrect initialisation.

For example, initialising a maximum value to zero would fail for an input
containing only negative values.

---

## Already Ordered Input

For algorithms involving ordering, an already ordered sequence may represent an
important boundary-oriented scenario.

Example:

```text
[1, 2, 3, 4, 5]
```

Questions may include:

- Does the algorithm preserve correctness?
- Does the control flow still terminate?
- Does the algorithm perform unnecessary work?

The performance consequences belong to later complexity or benchmarking
analysis.

---

## Reverse-Ordered Input

Reverse ordering may expose different algorithmic behaviour.

Example:

```text
[5, 4, 3, 2, 1]
```

This becomes particularly relevant when later studying sorting algorithms.

---

## Structural Boundaries

Data structures may have structural edge cases.

Examples include:

```text
Tree with one node
```

```text
Graph with one vertex
```

```text
Graph with disconnected components
```

```text
Highly unbalanced tree
```

The relevant boundaries depend upon the structure's formal specification.

---

## Large Valid Inputs

Large inputs may also represent an important boundary when the specification
defines a maximum practical or theoretical size.

However, two different questions should remain separated:

```text
Does the algorithm remain correct?
```

and:

```text
How does the algorithm perform as input size grows?
```

The first belongs to correctness reasoning.

The second belongs to complexity analysis and later empirical evaluation.

---

# Edge Cases and Problem Specification

Edge-case analysis begins from the problem specification.

The specification defines the valid domain.

Edge-case analysis examines its boundaries.

Conceptually:

```text
Problem Specification
        ↓
Valid Input Domain
        ↓
Boundary Analysis
        ↓
Edge Cases
```

A boundary cannot be identified correctly without first knowing what counts as a
valid problem instance.

For this reason, edge-case analysis follows:

```text
02-problem-specification.md
```

and:

```text
03-preconditions-and-postconditions.md
```

within the Foundations module.

---

# Edge Cases and Preconditions

Preconditions help determine whether a boundary input belongs inside or outside
the algorithmic contract.

Consider:

```text
Precondition

The input array contains at least one element.
```

Then:

```text
[7]
```

is a valid minimum edge case.

But:

```text
[]
```

violates the precondition.

Conceptually:

```text
Precondition
        ↓
Defines Valid Domain
        ↓
Edge-Case Analysis
```

This distinction prevents invalid inputs from being incorrectly classified as
cases the algorithm must solve.

---

# Edge Cases and Postconditions

Edge cases must still satisfy the same postconditions as ordinary valid inputs.

For example, suppose the postcondition for a maximum-value algorithm states:

```text
The returned value belongs to the input array.

No input element is greater than the returned value.
```

For:

```text
[8]
```

the result must still satisfy both properties.

Edge cases therefore provide useful situations for examining whether the
postconditions have been specified strongly enough.

---

# Edge Cases and Correctness

Correctness requires the algorithm to satisfy its specification for every valid
problem instance.

That includes valid edge cases.

Conceptually:

```text
Valid Domain
        ↓
Ordinary Cases + Edge Cases
        ↓
Correctness Reasoning
```

Edge-case analysis therefore helps reveal where a correctness argument may rely
on assumptions that are not universally valid.

For example:

```text
"there is always another element"
```

may be false for a single-element input.

Likewise:

```text
"the initial maximum can be zero"
```

may be false when all valid values are negative.

Correctness is developed systematically in:

```text
05-correctness.md
```

---

# Edge Cases and Invariants

Boundary cases may also help evaluate whether an invariant has been formulated
correctly.

If an invariant only appears to hold for ordinary inputs, it may be too weak,
incorrect, or based on an implicit assumption.

For example:

```text
Initial State
        ↓
Invariant
        ↓
Boundary Input
        ↓
Does the invariant still hold?
```

Invariant reasoning is developed in:

```text
06-invariants.md
```

---

# Edge Cases and Automated Testing

Edge-case analysis later becomes an important input to automated test design.

The relationship is:

```text
Specification
        ↓
Boundary Analysis
        ↓
Selected Edge Cases
        ↓
Expected Behaviour
        ↓
Automated Tests
```

For example:

```text
Edge Case

Single-element array

        ↓

Expected Behaviour

Return the only value.

        ↓

Automated Test

Verify that the algorithm returns that value.
```

However, not every theoretically imaginable boundary requires a separate test.

Tests should be selected according to:

- the contract;
- known failure risks;
- equivalence classes;
- boundary conditions;
- implementation behaviour worth verifying.

Automated testing is developed formally in:

```text
docs/16-testing/
```

---

# Edge Cases and Invalid-Input Testing

Invalid inputs may also require tests, but they belong to a different category.

Conceptually:

```text
Valid Edge Case
        ↓
Verify Correct Result
```

versus:

```text
Invalid Input
        ↓
Verify Defined Failure Behaviour
```

For example:

```text
[5]
```

may test the minimum valid input.

Whereas:

```text
[]
```

may test contract enforcement if the public API rejects empty arrays.

Keeping these categories distinct improves test design and documentation.

---

# Relationship with Complexity Analysis

Edge cases and complexity analysis may intersect, but they answer different
questions.

Edge-case analysis asks:

> **Does the algorithm behave correctly near the boundaries of the valid
> problem domain?**

Complexity analysis asks:

> **How do required computational resources grow with input size?**

Certain inputs may later represent:

- best-case behaviour;
- worst-case behaviour;
- particular structural configurations.

However, those classifications should be justified by formal complexity
analysis rather than by edge-case terminology alone.

Complexity is developed systematically in:

```text
docs/02-complexity/
```

---

# Relationship with Benchmarking

Edge cases may occasionally become useful benchmark scenarios, but benchmarking
is not a primary purpose of edge-case analysis.

The correct progression is:

```text
Specification
        ↓
Edge-Case Analysis
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Benchmarking When Appropriate
```

Benchmarking should evaluate implementations whose contracts and correctness
have already been established.

Performance evaluation is developed in:

```text
docs/17-benchmarking/
```

---

# Systematic Edge-Case Analysis

A useful analysis process is:

```text
1. Define the valid input domain.
        ↓
2. Identify contractual boundaries.
        ↓
3. Determine minimum and maximum valid instances.
        ↓
4. Identify unusual but valid structures or values.
        ↓
5. Separate valid edge cases from invalid inputs.
        ↓
6. Determine expected behaviour.
        ↓
7. Use the analysis during correctness reasoning.
        ↓
8. Select relevant cases for automated testing.
```

This process should remain proportional to the computational problem.

Simple algorithms may require only a few cases.

Complex data structures may require significantly deeper boundary analysis.

---

# Example — Maximum Element

Consider:

> Determine the largest value contained in a finite non-empty integer array.

## Contract Boundary

```text
Minimum valid array size = 1
```

## Representative Ordinary Case

```text
[7, 2, 15, 4, 9]
```

Expected result:

```text
15
```

## Minimum Valid Edge Case

```text
[8]
```

Expected result:

```text
8
```

## Duplicate-Value Case

```text
[5, 5, 5]
```

Expected result:

```text
5
```

## Negative-Value Case

```text
[-9, -2, -15]
```

Expected result:

```text
-2
```

## Extreme Numeric Case

At the Java implementation stage, a relevant case may include:

```text
[Integer.MIN_VALUE, Integer.MAX_VALUE]
```

Expected result:

```text
Integer.MAX_VALUE
```

## Invalid Input

Under the declared non-empty contract:

```text
[]
```

does not belong to the valid domain.

Its runtime treatment depends upon the Java API contract selected later.

This analysis distinguishes valid boundaries from contract violations.

---

# Best Practices

When analysing edge cases:

- begin from the formal problem specification;
- identify the valid input domain first;
- distinguish valid edge cases from invalid inputs;
- analyse minimum valid instances;
- examine repeated or structurally unusual values when relevant;
- consider numeric boundaries for numeric problems;
- identify implicit assumptions exposed by boundary cases;
- define expected behaviour before implementation;
- use edge cases during correctness reasoning;
- derive relevant tests from the analysis;
- avoid treating every unusual input as an edge case;
- avoid mixing correctness questions with performance conclusions.

Edge-case analysis should improve precision rather than become a mechanical
checklist.

---

# Common Mistakes

## Treating Every Invalid Input as an Edge Case

An input outside the contract is not automatically an edge case.

First determine whether it belongs to the valid problem domain.

---

## Testing Only Ordinary Inputs

A successful ordinary example does not demonstrate behaviour at the boundaries
of the specification.

---

## Assuming Empty Input Is Always Valid

Whether an empty collection is valid depends entirely on the problem
specification.

---

## Assuming Edge Cases Are Only Small Inputs

Large values, duplicate values, structural degeneracy, or unusual ordering may
also represent important boundaries.

---

## Using Edge Cases as Performance Conclusions

A boundary input does not automatically represent a worst-case complexity
scenario.

Formal complexity analysis is required.

---

## Discovering Boundaries Only During Coding

Edge cases should be identified during analysis whenever possible.

Implementation may reveal additional cases, but it should not be the first time
the problem boundaries are considered.

---

# Key Takeaways

After completing this document, the learner should understand that:

- edge cases represent important boundaries of the valid problem domain;
- invalid inputs and edge cases are not the same concept;
- the specification determines whether a boundary instance is valid;
- preconditions define important contractual boundaries;
- valid edge cases must still satisfy the required postconditions;
- edge-case analysis strengthens correctness reasoning;
- boundary analysis may reveal hidden assumptions;
- selected edge cases later become valuable automated test scenarios;
- invalid-input tests verify contract enforcement rather than ordinary
  algorithmic behaviour;
- complexity analysis and benchmarking answer different questions from
  edge-case analysis.

Edge-case analysis therefore provides a systematic bridge between **problem
specification**, **algorithmic contracts**, **correctness reasoning**, and
**automated testing**.

It helps ensure that algorithms are designed for the complete valid problem
domain rather than only for convenient examples.

---

# Next Document

```text
05-correctness.md
```

The next document introduces systematic reasoning about whether an algorithm
satisfies its specification.

It examines the relationship between:

- preconditions;
- postconditions;
- termination;
- partial correctness;
- total correctness;
- logical reasoning about algorithm behaviour.

Correctness provides the formal basis for answering:

> **Why should the algorithm produce the required result for every valid
> problem instance?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The systematic analysis of valid inputs, boundary conditions, correctness, and
algorithm behaviour provides the principal academic context for this document.

Additional academic and technical references supporting the Foundations module
are documented in:

```text
docs/00-project/10-references.md
```

Complementary references may be introduced when a topic requires additional
formal treatment of software contracts, boundary analysis, or testing theory.