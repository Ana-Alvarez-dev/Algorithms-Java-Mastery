# Problem Decomposition

## Algorithms Java Mastery

This document introduces **problem decomposition** as a fundamental technique
for analysing and structuring computational problems.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic study of algorithms throughout the repository.

Complex computational problems are often easier to understand when they are
divided into smaller responsibilities, subproblems, or stages of reasoning.

Problem decomposition therefore provides a way to reduce conceptual complexity
before implementation begins.

The central question addressed throughout this document is:

> **How can a complex computational problem be divided into smaller and more
> manageable parts without losing the structure of the original problem?**

---

# Purpose

The purpose of this document is to establish problem decomposition as a
systematic reasoning technique for computational problems whose complexity
benefits from being divided into smaller components.

Decomposition should not be treated as a mandatory procedure for every problem.

Some problems are already simple enough to reason about directly.

However, when a problem contains multiple responsibilities, repeated structure,
or identifiable subproblems, decomposition can make analysis significantly more
manageable.

The conceptual progression is:

```text
Complex Computational Problem
        ↓
Problem Understanding
        ↓
Identify Structure
        ↓
Decompose Responsibilities or Subproblems
        ↓
Reason About Each Part
        ↓
Combine the Results
        ↓
Complete Algorithmic Solution
```

The objective is not to create more components.

The objective is to create a clearer model of the problem.

---

# Learning Objectives

After studying this document, the learner should be able to:

* define problem decomposition;
* distinguish a complete problem from its subproblems;
* identify responsibilities within a computational problem;
* distinguish subproblems from simple execution steps;
* decompose complex problems systematically;
* explain how abstraction supports decomposition;
* distinguish top-down and bottom-up reasoning;
* recognise when decomposition improves algorithm design;
* understand how decomposition supports correctness reasoning;
* recognise how decomposition prepares the learner for recursion, divide and
  conquer, and dynamic programming;
* avoid unnecessary or artificial decomposition.

These competencies become increasingly important as algorithmic problems grow
in structural complexity.

---

# What Is Problem Decomposition?

**Problem decomposition** is the process of analysing a complex computational
problem by dividing it into smaller conceptual responsibilities or subproblems
that can be understood more independently.

Conceptually:

```text
Complex Problem
        ↓
Identify Internal Structure
        ↓
Smaller Responsibilities or Subproblems
        ↓
Reason About Each Part
        ↓
Combine Their Contributions
        ↓
Complete Solution
```

Decomposition does not necessarily mean that every part becomes a separate
algorithm.

It means that the learner identifies structure within the problem rather than
attempting to reason about the entire solution simultaneously.

---

# Why Decomposition Matters

Human reasoning becomes more difficult as the number of interacting details
increases.

A problem containing many simultaneous concerns may be difficult to understand
as a single unit.

Decomposition reduces this cognitive load.

```text
High Conceptual Complexity
        ↓
Decomposition
        ↓
Smaller Reasoning Units
        ↓
Clearer Analysis
```

This can improve:

* problem understanding;
* specification;
* algorithm design;
* correctness reasoning;
* implementation planning;
* testing strategy;
* maintainability.

However, decomposition is useful only when the resulting parts reflect
meaningful structure.

Artificial decomposition may make a simple problem more difficult.

---

# Problems, Responsibilities, and Subproblems

Not every part identified during analysis is a true computational subproblem.

It is useful to distinguish among several concepts.

## Problem

The complete computational objective.

Example:

> Determine whether a target value exists in a sorted array.

## Responsibility

A conceptual task required by the solution.

Example:

```text
Maintain the active search interval.
```

## Subproblem

A smaller computational problem whose solution contributes directly to the
larger problem.

Example:

```text
Search for the target inside a smaller portion of the original array.
```

## Execution Step

An individual operation performed by the algorithm.

Example:

```text
Compute the middle index.
```

These concepts should not be treated as equivalent.

---

# Types of Decomposition

Problem decomposition can occur in several forms.

The appropriate form depends on the computational structure of the problem.

---

## Responsibility Decomposition

A complex problem may contain several conceptual responsibilities.

For example:

```text
Search Problem
        │
        ├── Establish Search Domain
        ├── Compare Candidate
        ├── Reduce Search Space
        └── Determine Final Result
```

These responsibilities help organise reasoning without necessarily becoming
independent algorithms.

---

## Subproblem Decomposition

Some problems can be divided into smaller instances of related problems.

Conceptually:

```text
Problem of Size n
        ↓
Smaller Subproblem
        ↓
Solve Subproblem
        ↓
Combine or Extend Result
```

This form becomes particularly important in:

* recursion;
* divide and conquer;
* dynamic programming.

---

## Data Decomposition

A problem may also be analysed according to portions or structures within its
input.

Examples include:

```text
Array
        ↓
Prefix + Remaining Elements
```

or:

```text
Graph
        ↓
Vertices + Edges + Reachable Regions
```

or:

```text
Tree
        ↓
Root + Left Subtree + Right Subtree
```

The input structure itself may therefore suggest a useful decomposition.

---

# Top-Down Reasoning

**Top-down reasoning** begins with the complete problem and progressively
identifies smaller responsibilities or subproblems.

Conceptually:

```text
Complete Problem
        ↓
Major Responsibilities
        ↓
Smaller Components
        ↓
Detailed Strategy
```

This approach is particularly useful when the learner understands the desired
result but does not yet know how to construct the complete solution.

Top-down reasoning encourages the question:

> **Which smaller questions must be answered before the complete problem can be
> solved?**

---

# Bottom-Up Reasoning

**Bottom-up reasoning** begins with known operations, properties, or smaller
solutions and examines how they can be combined into a larger solution.

Conceptually:

```text
Known Components
        ↓
Relationships
        ↓
Combination
        ↓
Complete Solution
```

For example, a learner may already understand how to:

* compare two values;
* merge two ordered sequences;
* traverse a tree;
* solve a smaller instance of a recursive problem.

These known capabilities may suggest how a more complex algorithm can be
constructed.

---

# Top-Down vs Bottom-Up Reasoning

Neither approach should be treated as universally superior.

They answer different reasoning needs.

| Approach  | Starting Point     | Typical Question                                   |
| --------- | ------------------ | -------------------------------------------------- |
| Top-down  | Complete problem   | Which smaller parts must be solved?                |
| Bottom-up | Known capabilities | How can these parts produce the complete solution? |

Algorithm design may use both.

A learner may decompose a problem top-down and later construct the final
solution bottom-up from previously understood components.

---

# Abstraction and Decomposition

Problem decomposition depends strongly on **abstraction**.

Abstraction allows the learner to reason about one responsibility without
simultaneously considering every lower-level detail.

Conceptually:

```text
Complex Details
        ↓
Abstraction
        ↓
Relevant Responsibility
        ↓
Decomposition
```

For example, when analysing binary search, it may initially be sufficient to
reason about:

```text
Compare target with middle value
        ↓
Choose remaining search interval
```

without yet considering:

* Java syntax;
* exact variable declarations;
* method signatures;
* testing frameworks;
* package organisation.

Abstraction protects algorithmic reasoning from unnecessary implementation
detail.

---

# Example — Maximum Element

Consider the problem:

> Determine the largest value contained in a finite non-empty integer array.

This problem is relatively simple, so it does not require deep decomposition.

However, it can still be analysed in terms of logical responsibilities.

```text
Determine Maximum
        ↓
Establish Initial Candidate
        ↓
Process Remaining Elements
        ↓
Preserve Maximum-So-Far Property
        ↓
Establish Final Result
```

These are not necessarily separate computational subproblems.

They are conceptual responsibilities that make the reasoning easier to follow.

The associated invariant may state:

> The current maximum is the largest value among the elements processed so far.

This example demonstrates that decomposition can support reasoning even when the
problem itself remains small.

---

# Example — Binary Search

Consider:

> Determine whether a target value exists in a sorted array.

A useful conceptual decomposition is:

```text
Binary Search
        │
        ├── Maintain Current Search Interval
        ├── Identify Middle Position
        ├── Compare Target with Middle Value
        ├── Eliminate One Half of the Search Space
        └── Determine Found or Absent Result
```

The important structural idea is not merely that the algorithm executes several
steps.

It is that the remaining search problem can repeatedly be reduced.

Conceptually:

```text
Search in Interval
        ↓
Compare Middle
        ↓
Search in Smaller Interval
```

This reduction of the problem domain becomes an important form of algorithmic
reasoning.

---

# Example — Merge Sort

A stronger example of genuine subproblem decomposition appears in merge sort.

The sorting problem can be expressed conceptually as:

```text
Sort Sequence
        ↓
Divide Sequence
        ↓
Sort Left Subproblem
        ↓
Sort Right Subproblem
        ↓
Merge Ordered Results
```

Here, the original problem is decomposed into smaller instances of the same
general problem.

```text
Sort n Elements
        ↓
Sort Approximately n/2 Elements
        +
Sort Approximately n/2 Elements
        ↓
Combine
```

This form of decomposition later becomes central to the study of
**divide and conquer**.

---

# Decomposition and Correctness

Decomposition can make correctness reasoning easier when each subproblem has a
clear responsibility.

Conceptually:

```text
Complete Specification
        ↓
Subproblem Specifications
        ↓
Correct Subproblem Solutions
        ↓
Correct Composition
        ↓
Complete Postcondition
```

However, proving each part independently is not always enough.

The learner must also reason about how the parts interact.

A complete correctness argument may therefore require:

* correctness of each subproblem;
* correctness of their composition;
* preservation of shared assumptions;
* compatibility between subproblem outputs and inputs.

Decomposition improves correctness reasoning only when the relationships
between components remain explicit.

---

# Decomposition and Preconditions

Subproblems may inherit or refine conditions from the original problem.

For example:

```text
Original Problem
        ↓
Sorted Input Required
```

A recursive or reduced subproblem may require that the smaller input preserves
the same ordering condition.

Conceptually:

```text
Original Preconditions
        ↓
Subproblem Preconditions
```

The learner should verify that decomposition does not silently violate the
contract of the original problem.

---

# Decomposition and Postconditions

Subproblem outputs often contribute to the final postcondition.

For example:

```text
Subproblem A Result
        +
Subproblem B Result
        ↓
Combination
        ↓
Final Postcondition
```

This becomes especially important in algorithms where partial results must be
combined correctly.

The decomposition should therefore make the relationship between intermediate
results and the final objective explicit.

---

# Decomposition and Complexity Analysis

Problem decomposition may strongly influence computational complexity.

For example:

```text
Problem Size n
        ↓
Two Subproblems of Size n/2
        ↓
Combination Work
```

This structure later leads naturally to recurrence relations.

Similarly:

```text
Problem Size n
        ↓
Problem Size n - 1
        ↓
Repeated Reduction
```

may suggest a different growth pattern.

However, decomposition itself does not establish complexity.

Formal analysis is required.

Complexity is developed systematically in:

```text
docs/02-complexity/
```

---

# Decomposition and Implementation

Conceptual decomposition may later influence Java implementation structure.

However:

> **A conceptual subproblem does not automatically become a method, class,
> interface, or package.**

The relationship is better understood as:

```text
Conceptual Decomposition
        ↓
May Inform
        ↓
Implementation Structure
```

not:

```text
One Subproblem
        =
One Class
```

Implementation boundaries should be introduced only when they improve:

* clarity;
* cohesion;
* reuse;
* testability;
* maintainability.

The algorithmic model should therefore remain distinct from the software
architecture used to implement it.

---

# Decomposition and Modular Design

Problem decomposition can support modular implementation, but the mapping is not
mechanical.

For example, a meaningful subproblem may later become:

* a private helper method;
* a reusable algorithm;
* a separate component;
* no separate software element at all.

The choice depends on the implementation context.

This distinction prevents premature abstraction.

Conceptually:

```text
Problem Structure
        ↓
Algorithm Structure
        ↓
Implementation Decisions
```

Each level informs the next without determining it automatically.

---

# Relationship with Recursion

Recursion frequently expresses decomposition directly.

A recursive algorithm typically solves a problem by reducing it to one or more
smaller instances.

Conceptually:

```text
Problem
        ↓
Base Case?
        │
        ├── Yes → Direct Solution
        │
        └── No
             ↓
        Smaller Subproblem
             ↓
        Recursive Solution
             ↓
        Complete Result
```

Problem decomposition therefore provides an important foundation for the later
study of recursion.

---

# Relationship with Divide and Conquer

Divide and conquer makes subproblem decomposition explicit.

The general structure is:

```text
Problem
        ↓
Divide
        ↓
Subproblems
        ↓
Conquer
        ↓
Subproblem Solutions
        ↓
Combine
        ↓
Complete Solution
```

Typical examples later include:

* merge sort;
* quicksort;
* binary-search-style reduction;
* recursive problem partitioning.

The dedicated module develops this paradigm in greater depth.

---

# Relationship with Dynamic Programming

Dynamic programming also depends upon recognising problem structure.

A problem may contain smaller subproblems whose results are needed repeatedly.

Conceptually:

```text
Complex Problem
        ↓
Subproblems
        ↓
Overlapping Subproblems
        ↓
Store Reusable Results
        ↓
Complete Solution
```

Problem decomposition alone does not imply dynamic programming.

However, the ability to identify meaningful subproblems is a prerequisite for
recognising when dynamic programming may be appropriate.

---

# Relationship with Greedy Reasoning

Some computational problems can be analysed as sequences of smaller decisions.

Conceptually:

```text
Complete Problem
        ↓
Local Decision
        ↓
Remaining Problem
        ↓
Next Decision
```

When certain structural properties hold, such reasoning may later support a
greedy algorithm.

However, local decomposition does not automatically justify a greedy strategy.

Correctness must still be established independently.

---

# When Decomposition Is Not Useful

Not every computational problem benefits from extensive decomposition.

A simple problem may already have:

* one clear responsibility;
* one straightforward strategy;
* little internal complexity.

Artificially dividing such a problem may introduce unnecessary cognitive
overhead.

For example:

```text
Return whether two integers are equal.
```

does not require multiple subproblems.

The principle is:

> **Decompose when decomposition improves reasoning.**

Not:

> **Decompose every problem regardless of complexity.**

---

# Signs That Decomposition May Be Useful

Decomposition may be valuable when:

* the problem contains multiple responsibilities;
* the input has recursive or hierarchical structure;
* the same smaller problem appears repeatedly;
* a solution can be expressed through smaller instances;
* several independent conditions must be established;
* reasoning about the complete problem becomes difficult;
* intermediate results can be combined systematically.

These signals should guide analysis rather than act as rigid rules.

---

# Common Mistakes

## Treating Execution Steps as Independent Subproblems

For example:

```text
Read input.
Increment index.
Return result.
```

These are execution actions, not necessarily meaningful computational
subproblems.

---

## Decomposing Too Early

If the original problem has not been understood, decomposition may simply
divide an ambiguous problem into smaller ambiguous problems.

Problem specification should therefore come first.

---

## Decomposing Too Much

Excessive decomposition may produce:

* fragmented reasoning;
* unnecessary abstractions;
* excessive implementation structure;
* difficulty understanding the complete algorithm.

---

## Losing the Original Objective

Each subproblem should contribute clearly to the original specification.

If the relationship cannot be explained, the decomposition may not be useful.

---

## Assuming Subproblems Are Independent

Some subproblems depend on shared state or on results produced by other
subproblems.

These relationships must remain explicit.

---

## Mapping Every Subproblem to a Class

Conceptual decomposition and object-oriented design are different activities.

A subproblem should become a separate software abstraction only when the
implementation benefits from that structure.

---

## Introducing Design Patterns Prematurely

Problem decomposition does not automatically justify Strategy, Factory,
Template Method, or any other design pattern.

Patterns should be introduced later only when an actual implementation problem
requires them.

---

# Systematic Decomposition Method

A disciplined decomposition process may follow this progression:

```text
1. Understand the complete problem.
        ↓
2. Identify the required final result.
        ↓
3. Identify major responsibilities or structural components.
        ↓
4. Determine whether genuine subproblems exist.
        ↓
5. Define the responsibility of each subproblem.
        ↓
6. Identify dependencies between subproblems.
        ↓
7. Determine how their results contribute to the final solution.
        ↓
8. Verify that the decomposition preserves the original specification.
        ↓
9. Design the algorithmic strategy.
```

Useful questions include:

* What must ultimately be computed?
* Which parts of the problem can be reasoned about independently?
* Does the input structure suggest smaller instances?
* Which results are required before another step can proceed?
* Are any subproblems repeated?
* How will partial results be combined?
* Does each identified component contribute to the final postcondition?

These questions help transform decomposition into a reasoning method rather than
a mechanical checklist.

---

# Relationship with the Problem-Solving Method

Problem decomposition is one component of the broader methodology used
throughout **Algorithms Java Mastery**.

Conceptually:

```text
Problem Understanding
        ↓
Problem Specification
        ↓
Contracts
        ↓
Edge-Case Analysis
        ↓
Decomposition When Useful
        ↓
Strategy Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Implementation
```

The next document consolidates these concepts into a repeatable study method.

---

# Best Practices

When decomposing a computational problem:

* understand the original problem first;
* begin from the required output;
* distinguish responsibilities from execution steps;
* identify genuine subproblems when they exist;
* preserve the original specification;
* make dependencies explicit;
* reason about how partial solutions combine;
* use abstraction to hide irrelevant detail;
* avoid unnecessary fragmentation;
* keep conceptual decomposition separate from Java architecture;
* allow the problem structure to guide the decomposition;
* revise the decomposition when it does not simplify reasoning.

A good decomposition should make the problem easier to explain.

If it makes the problem harder to understand, it should be reconsidered.

---

# Key Takeaways

After completing this document, the learner should understand that:

* problem decomposition divides complex reasoning into manageable components;
* not every problem requires explicit decomposition;
* responsibilities, subproblems, and execution steps are different concepts;
* top-down reasoning begins from the complete problem;
* bottom-up reasoning begins from known components or smaller solutions;
* both approaches may contribute to algorithm design;
* abstraction supports decomposition by hiding irrelevant detail;
* conceptual decomposition does not map automatically to Java classes or
  methods;
* decomposition can support correctness reasoning;
* subproblem structure may influence complexity analysis;
* recursion, divide and conquer, and dynamic programming rely heavily on the
  ability to recognise smaller problem structure;
* unnecessary decomposition introduces complexity instead of reducing it.

Problem decomposition therefore provides a bridge between **problem
understanding**, **algorithmic structure**, **correctness reasoning**, and
advanced algorithm design paradigms.

Its value lies not in producing more components, but in revealing the structure
required to reason about increasingly complex computational problems.

---

# Next Document

```text
08-problem-solving-method.md
```

The next document consolidates the concepts introduced throughout the
**Foundations** module into a repeatable problem-solving methodology.

It integrates:

```text
Problem Understanding
        ↓
Problem Specification
        ↓
Preconditions and Postconditions
        ↓
Edge-Case Analysis
        ↓
Problem Decomposition
        ↓
Strategy Design
        ↓
Pseudocode
        ↓
Correctness Reasoning
        ↓
Complexity Preview
        ↓
Java Implementation
```

This methodology becomes the standard reasoning process applied throughout the
remaining modules of **Algorithms Java Mastery**.

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The systematic decomposition of computational problems into smaller instances
or responsibilities provides an important foundation for the study of
algorithmic strategies throughout the book, particularly recursion, divide and
conquer, and dynamic programming.

Additional academic and technical references supporting problem solving,
algorithm design, and structured reasoning are documented in:

```text
docs/00-project/10-references.md
```

Complementary references may be introduced in later modules when a specific
algorithmic paradigm requires additional theoretical treatment.
