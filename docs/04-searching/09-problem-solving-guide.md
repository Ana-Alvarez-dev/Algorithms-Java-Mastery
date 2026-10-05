# Problem Solving Guide

## Purpose

Learning individual searching algorithms is only the first step towards becoming an effective software engineer.

In practice, developers are rarely asked to implement a specific algorithm directly.

Instead, they are presented with a problem and must determine:

- what information is required;
- what assumptions can be made;
- which searching strategy is appropriate;
- whether the chosen solution is correct;
- whether the solution is efficient.

This document presents a structured methodology for analysing and solving searching problems using systematic algorithmic reasoning.

The objective is not to memorise algorithms, but to develop a repeatable problem-solving process.

---

# Learning Objectives

After completing this document, the learner should be able to:

- analyse searching problems systematically;
- identify the characteristics of a search problem;
- recognise applicable search patterns;
- select an appropriate searching algorithm;
- reason about correctness;
- evaluate computational complexity;
- design clear and maintainable Java implementations.

---

# Problem Solving Methodology

Every searching problem should be approached using the same engineering process.

```text
Understand the Problem
        ↓
Identify Inputs
        ↓
Identify Outputs
        ↓
Analyse Constraints
        ↓
Recognise the Search Pattern
        ↓
Select the Algorithm
        ↓
Design the Solution
        ↓
Reason About Correctness
        ↓
Analyse Complexity
        ↓
Implement in Java
        ↓
Validate with Tests
```

Following the same methodology consistently produces reliable and maintainable solutions.

---

# Step 1 — Understand the Problem

The first responsibility of a software engineer is to understand exactly what the problem asks.

Typical questions include:

- What must be found?
- Is only one result required?
- Can multiple matches exist?
- Is an exact match required?
- Is an approximate answer acceptable?

A poorly understood problem almost always leads to an incorrect solution.

---

# Step 2 — Identify the Inputs

Determine the information available.

Typical inputs include:

- arrays;
- lists;
- trees;
- graphs;
- databases.

Also identify:

- search key;
- comparison criterion;
- collection size.

---

# Step 3 — Identify the Expected Output

Determine exactly what the algorithm must return.

Possible outputs include:

- index;
- object reference;
- boolean value;
- collection of results;
- nearest value;
- NOT FOUND indicator.

A precise output specification prevents ambiguity during implementation.

---

# Step 4 — Analyse Constraints

Before selecting an algorithm, identify any constraints.

Important questions include:

- Is the collection sorted?
- Can duplicates exist?
- How large is the collection?
- How frequently is the data updated?
- Are memory limitations important?

Constraints often determine which algorithms are applicable.

---

# Step 5 — Recognise the Search Pattern

Classify the problem.

Examples include:

- Exact Match
- Boundary Search
- Range Search
- Predicate Search
- Nearest Value Search
- Multiple Match Search

Pattern recognition is often more valuable than memorising algorithms.

---

# Step 6 — Select the Appropriate Algorithm

Choose the simplest algorithm that satisfies the problem requirements.

Examples:

Unsorted collection

↓

Linear Search

---

Sorted collection

↓

Binary Search

---

Hierarchical structure

↓

Tree Traversal

---

Relationship network

↓

Graph Search

The chosen algorithm should satisfy both correctness and efficiency requirements.

---

# Step 7 — Design the Solution

Before writing code, describe the algorithm.

Questions to answer include:

- What variables are required?
- How will comparisons be performed?
- When does the algorithm terminate?
- How are unsuccessful searches handled?

Developing a clear algorithm before implementation reduces programming errors.

---

# Step 8 — Reason About Correctness

Every algorithm should be justified.

Typical questions include:

- Why does the algorithm always find the target?
- Why can discarded elements never contain the solution?
- Under what conditions does the algorithm terminate?

Correctness reasoning is a fundamental aspect of algorithm engineering.

---

# Step 9 — Analyse Complexity

Evaluate the algorithm.

Consider:

Time Complexity

```text
Best Case

Average Case

Worst Case
```

Space Complexity

```text
Auxiliary Memory
```

Engineering decisions require balancing performance and simplicity.

---

# Step 10 — Implement in Java

Only after completing the previous steps should implementation begin.

Good implementations should:

- use meaningful names;
- avoid unnecessary complexity;
- clearly document assumptions;
- separate concerns;
- remain easy to test.

Implementation should reflect the previously designed algorithm.

---

# Step 11 — Validate with Automated Tests

Every searching algorithm should be validated using representative test cases.

Typical tests include:

Successful search

```text
Element Exists
```

---

Unsuccessful search

```text
Element Does Not Exist
```

---

Boundary conditions

```text
Empty Collection

Single Element

First Element

Last Element
```

---

Duplicate values

```text
Repeated Elements
```

Testing confirms that the implementation satisfies its specification.

---

# Repository Implementation Traceability

The methodology in this document is now represented by concrete implementations
inside the Searching package.

```text
Problem Definition
        ↓
Contract
        ↓
Search Pattern
        ↓
Correctness Argument
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
JUnit + AssertJ
        ↓
JMH when justified
```

Representative traces include:

| Problem Type | Implementation | Test Evidence | Benchmark |
|---|---|---|---|
| Exact sequential search | `LinearSearch` | `LinearSearchTest` | `LinearSearchBenchmark` |
| Exact ordered search | `BinarySearch` | `BinarySearchTest` | `BinarySearchBenchmark` |
| Recursive ordered search | `RecursiveBinarySearch` | `RecursiveBinarySearchTest` | `BinarySearchVariantsBenchmark` |
| First boundary | `FirstOccurrence` | `FirstOccurrenceTest` | Not required |
| Last boundary | `LastOccurrence` | `LastOccurrenceTest` | Not required |
| Lower-bound insertion point | `SearchInsertPosition` | `SearchInsertPositionTest` | Not required |
| Complete duplicate range | `SearchRange` | `SearchRangeTest` | Not required |
| Nearest value | `ClosestValueSearch` | `ClosestValueSearchTest` | Not required |

The table is intended to demonstrate methodology, not to require one
implementation for every possible search problem.

---

# Worked Methodology Example — First Occurrence

A first-occurrence problem can be solved through the repository workflow.

```text
Problem
Return the smallest index containing target
        ↓
Input Property
Array is sorted
        ↓
Contract
Return first matching index or -1
        ↓
Pattern
Boundary Binary Search
        ↓
Correctness Property
A recorded match remains valid while the search continues left
        ↓
Complexity
Θ(log n) time, Θ(1) auxiliary space
        ↓
Implementation
FirstOccurrence.java
        ↓
Tests
Repeated target, absent target, boundaries, empty array, null input
```

This example shows why code should be the consequence of the analysis rather
than its starting point.

---

# Verification Boundary

Automated tests provide execution evidence for selected inputs.

They do not replace the correctness argument.

Likewise, JMH provides empirical performance evidence but does not prove
asymptotic complexity.

The repository therefore preserves:

```text
Correctness Proof      ≠ Unit Test
Complexity Analysis    ≠ Benchmark Result
Implementation         ≠ Specification
```

---

# Common Mistakes During Problem Solving

Software engineers frequently make mistakes before implementation even begins.

Examples include:

- selecting an algorithm too early;
- ignoring problem constraints;
- assuming sorted input;
- overlooking edge cases;
- analysing complexity incorrectly;
- confusing correctness with efficiency.

Following a structured methodology helps avoid these mistakes.

---

# Engineering Perspective

Professional software engineers spend far more time analysing problems than writing code.

A well-designed solution generally results from:

- understanding the requirements;
- selecting appropriate algorithms;
- reasoning carefully;
- validating assumptions.

Programming is only one stage of the engineering process.

---

# Relationship with Previous Documents

This guide integrates every concept introduced throughout the Searching module.

```text
Search Fundamentals
        ↓
Problem Definition
        ↓
Searching Algorithms
        ↓
Complexity Analysis
        ↓
Search Patterns
        ↓
Common Problems
        ↓
Problem Solving Guide
```

Algorithmic knowledge becomes practical engineering methodology.

---

# Relationship with Future Modules

The methodology presented here will be reused throughout the repository.

```text
Searching
        ↓
Sorting
        ↓
Trees
        ↓
Graphs
        ↓
Dynamic Programming
```

Although the algorithms change, the engineering process remains the same.

---

# Key Takeaways

Successful software engineers do not begin by writing code.

They first understand the problem, analyse its constraints, identify the underlying search pattern, select an appropriate algorithm, reason about correctness and evaluate computational complexity.

Only then do they implement and test the solution.

Developing this disciplined methodology is essential for solving increasingly complex algorithmic problems.

---

# Next Document

```
10-common-mistakes.md
```

The next document examines the most common implementation and reasoning mistakes encountered when designing and implementing searching algorithms, together with practical strategies for avoiding them.