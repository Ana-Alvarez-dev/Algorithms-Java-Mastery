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