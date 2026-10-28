# Search Fundamentals

## Purpose

Searching is one of the most fundamental operations in Computer Science.

Exact-match search in many systems asks the following existence/location question:

> **Given a collection of data, determine whether a target element exists and, if so, identify its location.**

Although this problem appears simple, the efficiency of a searching algorithm directly affects the overall performance and scalability of software systems.

For this reason, searching algorithms occupy a central role in algorithm design, data structures, databases and software engineering.

This document introduces the fundamental concepts that underpin every searching algorithm studied throughout this repository.

---

# Learning Objectives

After completing this document, the learner should be able to:

- understand the computational search problem;
- distinguish searching from other algorithmic operations;
- recognise why efficient searching is essential in software systems;
- identify the factors that influence search performance;
- understand how data organisation affects algorithm selection.

---

# What Is Searching?

Searching is the computational process of locating information within a collection of data.

More formally:

> A searching algorithm receives a collection of elements together with a target value and determines whether the target exists within the collection.

Depending on the algorithm, the result may include:

- the position of the element;
- the element itself;
- multiple matching elements;
- confirmation that no matching element exists.

The repository also implements lower-bound, range-endpoint and nearest-value questions; they are specified separately in the [concrete contract table](02-search-problem-definition.md#concrete-repository-contracts). Exact matching is one search contract rather than a universal description of all retrieval tasks.

---

# The Search Problem

The search problem can be expressed as follows.

Given:

- a finite collection of elements;
- a search key;
- a comparison criterion;

determine whether at least one element satisfies the search condition.

Conceptually:

```text
Collection
───────────────
[A][B][C][D][E]

Search key
     │
     ▼
     D

Result
───────────────
Found at index 3
```

If no matching element exists:

```text
Collection
───────────────
[A][B][C][D][E]

Search key
     │
     ▼
     X

Result
───────────────
Not Found
```

---

# Why Searching Matters

Searching is one of the most frequently executed operations in computing.

Every modern software system performs search operations continuously.

Examples include:

- user authentication;
- product catalogues;
- file systems;
- search engines;
- routing systems;
- recommendation platforms;
- operating systems;
- database indexes;
- compiler symbol tables;
- cache lookups.

Searching can be a significant part of these systems. Its contribution relative
to I/O, updates and other work depends on the workload; this module does not
establish a universal system bottleneck.

---

# Components of a Search

Every search algorithm consists of several essential components.

## The Collection

The data structure that stores the information.

Examples:

- arrays;
- lists;
- trees;
- hash tables;
- graphs.

Different collections require different search strategies.

---

## The Search Key

The value being searched.

Examples:

- student ID;
- username;
- product code;
- email address;
- integer value.

The key identifies the desired information.

---

## Comparison Operation

Searching relies on comparing the search key with existing elements.

The comparison may determine:

- equality;
- ordering;
- matching conditions.

The efficiency of these comparisons significantly affects algorithm performance.

---

## Result

The algorithm returns one of several possible outcomes.

Examples include:

- index position;
- reference to an object;
- collection of matching elements;
- boolean result;
- null or empty result.

---

# Factors Affecting Search Performance

Not all search problems have the same complexity.

Several factors influence algorithm performance.

## Collection Size

Larger collections generally require more work.

```text
10 elements
↓

1,000 elements
↓

1,000,000 elements
```

The algorithm should scale efficiently as the collection grows.

---

## Data Organisation

Some collections are unordered.

Others are sorted.

Some are indexed.

Others use hashing.

The organisation of the data determines which searching algorithms can be applied.

For example:

```text
Unsorted Array
↓

Linear Search
```

```text
Sorted Array
↓

Binary Search
```

---

## Search Frequency

Applications that perform millions of searches per second require algorithms with excellent scalability.

Examples include:

- search engines;
- databases;
- financial systems;
- operating systems.

---

## Memory Constraints

Some search algorithms require additional memory.

Others operate in constant auxiliary space.

Engineering decisions often involve balancing execution time against memory usage.

---

# Categories of Searching Algorithms

Searching algorithms can be classified according to their underlying strategy.

Common categories include:

- sequential searching;
- divide-and-conquer searching;
- hashing-based searching;
- tree searching;
- graph searching.

Each category is appropriate under different assumptions.

These strategies will be explored progressively throughout this repository.

---

# Searching and Computational Complexity

Searching algorithms are evaluated primarily according to:

- execution time;
- memory consumption;
- scalability.

The same search problem may have multiple valid solutions.

For example:

```text
Linear Search
↓

Simple
↓

Works everywhere

O(n)
```

versus

```text
Binary Search
↓

Requires sorted data

O(log n)
```

Selecting the appropriate algorithm depends on the characteristics of the problem.

---

# Relationship with Previous Modules

Searching builds directly upon concepts introduced earlier.

```text
Foundations
        ↓
Algorithm Design

Complexity
        ↓
Efficiency Analysis

Arrays
        ↓
Sequential Traversal

Searching
```

Without understanding complexity analysis and array traversal, evaluating searching algorithms would not be possible.

---

# Key Takeaways

Searching is a fundamental computational problem that appears in virtually every software system.

Different algorithms can solve the same exact-match problem, but lower bounds,
nearest-value selection and equality endpoints have different postconditions.
Choose the required result before comparing strategies or complexity.

Understanding the search problem itself is more important than memorising individual algorithms.

The ability to analyse data organisation, computational complexity and algorithmic trade-offs enables software engineers to select the most appropriate search strategy for a given problem.

The following document formally defines the computational search problem and introduces the terminology used throughout the remainder of this module.

---

# Next Document

```

02-search-problem-definition.md

```

This document formalises the search problem, defines its inputs and outputs, and establishes the assumptions required by different searching algorithms.
