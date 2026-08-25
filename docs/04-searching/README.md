# Searching

## Algorithms Java Mastery

The **Searching** module introduces the fundamental concepts, strategies, and
algorithms used to locate information within a collection of data.

Searching is one of the central computational problems studied in Computer
Science.

Algorithms, data structures, databases, file systems, indexes, and information
retrieval mechanisms depend on the ability to determine whether an element
exists and, when appropriate, identify its position.

The module studies searching from both theoretical and practical perspectives.

The objective is not merely to implement search algorithms in Java, but to
understand:

- the computational problem being solved;
- the assumptions required by each strategy;
- the relationship between data organisation and search efficiency;
- the correctness of the selected algorithm;
- its time and space complexity;
- the engineering trade-offs between alternative approaches.

Searching algorithms are strongly influenced by the organisation of their
input.

Some algorithms operate on arbitrary collections, while others require
additional conditions, such as sorted input or direct access by index.

Understanding these requirements is essential for selecting and applying the
appropriate search strategy.

---

# Academic Foundation

The concepts developed throughout this module are primarily based on
*Introduction to Algorithms* by Cormen, Leiserson, Rivest, and Stein.

They are complemented by recognised Computer Science literature, Java
documentation, and the official references listed in:

```text
docs/00-project/10-references.md
```

The module does not reproduce textbook explanations or pseudocode mechanically.

Academic concepts are transformed into:

- original explanations;
- formal problem specifications;
- correctness reasoning;
- complexity analysis;
- independent Java implementations;
- automated tests;
- controlled experimental evaluation when appropriate.

---

# Purpose

The purpose of this module is to develop a rigorous understanding of searching
algorithms before and during implementation.

Throughout the module, the learner will study:

- the general search problem;
- successful and unsuccessful search results;
- sequential search strategies;
- iterative binary search;
- recursive binary search;
- preconditions required by different algorithms;
- loop and recursive invariants;
- search complexity;
- reusable search patterns;
- common engineering mistakes;
- practical problem-solving techniques.

Each search algorithm is studied through the methodology established throughout
the repository.

```text
Search Problem
        ↓
Formal Specification
        ↓
Input Assumptions
        ↓
Strategy Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Experimental Evaluation
        when appropriate
```

Searching is therefore treated as a complete academic and engineering subject
rather than as an isolated programming exercise.

---

# Core Principles

The Searching module is organised around the following principles:

- define the search problem before selecting an algorithm;
- make input assumptions explicit;
- distinguish correctness from efficiency;
- analyse data organisation before comparing strategies;
- reason about successful and unsuccessful searches;
- verify behaviour through automated tests;
- use benchmarking only when it answers a defined experimental question.

These principles guide every document and implementation contained in the
module.

---

# Learning Objectives

After completing this module, the learner should be able to perform the
following activities.

## Algorithmic reasoning

- define the computational search problem;
- distinguish between search strategies;
- identify the assumptions required by each algorithm;
- determine whether input ordering is required;
- select an appropriate strategy according to the data and problem constraints;
- distinguish search algorithms from indexing and hashing mechanisms.

## Specification and correctness

- define valid inputs and expected outputs;
- describe successful and unsuccessful search behaviour;
- establish relevant preconditions and postconditions;
- reason about loop invariants;
- reason about recursive base and reduction cases;
- explain why a search algorithm terminates;
- justify why the result satisfies the specification.

## Complexity analysis

- analyse search algorithms using asymptotic notation;
- identify significant operations;
- compare linear and logarithmic growth;
- analyse best, average, and worst-case behaviour;
- understand how input organisation affects efficiency;
- distinguish theoretical complexity from observed execution time.

## Java implementation

- implement search algorithms clearly in Java;
- preserve the documented algorithmic strategy;
- define consistent search-result contracts;
- implement iterative and recursive variants;
- compare academic implementations with Java standard-library facilities;
- avoid unnecessary abstractions and hidden assumptions.

## Automated testing

- verify successful searches;
- verify unsuccessful searches;
- test empty input;
- test single-element input;
- test boundary positions;
- test duplicate values;
- test invalid input when applicable;
- verify precondition enforcement;
- test iterative and recursive implementations consistently.

---

# Module Progression

The module follows this conceptual progression:

```text
Search Fundamentals
        ↓
Formal Search Problem
        ↓
Linear Search
        ↓
Binary Search
        ↓
Recursive Binary Search
        ↓
Complexity Comparison
        ↓
Search Patterns
        ↓
Applied Problems
        ↓
Problem-Solving Method
        ↓
Common Mistakes
        ↓
Interview Review
```

Each document introduces knowledge required to understand the documents that
follow it.

---

# Module Structure

```text
04-searching/
├── README.md
├── 01-search-fundamentals.md
├── 02-search-problem-definition.md
├── 03-linear-search.md
├── 04-binary-search.md
├── 05-recursive-binary-search.md
├── 06-search-complexity.md
├── 07-search-patterns.md
├── 08-common-search-problems.md
├── 09-problem-solving-guide.md
├── 10-common-mistakes.md
└── 11-interview-notes.md
```

The structure progresses from general search concepts to individual algorithms,
comparative analysis, problem solving, and technical review.

---

# Document Overview

## `01-search-fundamentals.md`

Introduces searching as a computational problem.

The document studies:

- the purpose of search operations;
- search spaces;
- targets and keys;
- successful and unsuccessful searches;
- positions and results;
- sequential and structured search strategies.

It establishes the terminology used throughout the remainder of the module.

---

## `02-search-problem-definition.md`

Defines the search problem formally.

The document identifies:

- inputs;
- target values;
- search domains;
- expected outputs;
- preconditions;
- postconditions;
- duplicate-value policies;
- invalid-input behaviour;
- mutation expectations.

The specification becomes the reference for implementation and testing.

---

## `03-linear-search.md`

Studies linear search as a sequential strategy.

The document examines:

- complete traversal;
- successful and unsuccessful search;
- early termination;
- loop invariants;
- correctness;
- best, average, and worst cases;
- Java implementation;
- automated testing.

Linear search provides the baseline against which more specialised strategies
can be compared.

---

## `04-binary-search.md`

Studies iterative binary search.

The document explains:

- the sorted-input precondition;
- interval reduction;
- midpoint calculation;
- boundary management;
- loop invariants;
- termination;
- logarithmic growth;
- common implementation errors.

Binary search demonstrates how data organisation can substantially improve
search efficiency.

---

## `05-recursive-binary-search.md`

Introduces the recursive formulation of binary search.

The document studies:

- base cases;
- recursive reduction;
- correctness reasoning;
- termination;
- recursion depth;
- auxiliary-space complexity;
- comparison with the iterative version.

The purpose is not to declare one version universally superior, but to analyse
their structural and computational differences.

---

## `06-search-complexity.md`

Compares the computational behaviour of the search algorithms studied in the
module.

The analysis includes:

- significant operations;
- input-size definition;
- best-case behaviour;
- average-case behaviour;
- worst-case behaviour;
- time complexity;
- auxiliary-space complexity;
- linear and logarithmic growth;
- theoretical and practical trade-offs.

The document also distinguishes asymptotic analysis from empirical timing.

---

## `07-search-patterns.md`

Presents reusable search patterns that appear across different problems.

Possible patterns include:

- locating the first occurrence;
- locating the last occurrence;
- searching within an interval;
- finding insertion positions;
- searching for a boundary condition;
- testing existence;
- counting occurrences;
- locating a minimum valid solution.

These patterns extend searching beyond the basic task of locating one exact
value.

---

## `08-common-search-problems.md`

Applies search strategies to representative computational problems.

Examples may include:

- locating a target in an array;
- finding the first or last duplicate;
- determining an insertion point;
- searching a rotated ordered array;
- finding a value satisfying a monotonic condition;
- searching within multidimensional data;
- comparing sequential and binary-search solutions.

Each problem should be analysed before implementation.

---

## `09-problem-solving-guide.md`

Defines the standard method for solving search-related problems.

The process is:

```text
Understand the Search Requirement
        ↓
Define the Search Space
        ↓
Identify Input Properties
        ↓
Specify the Expected Result
        ↓
Select a Search Strategy
        ↓
Establish Correctness Conditions
        ↓
Analyse Complexity
        ↓
Implement in Java
        ↓
Verify with Automated Tests
```

The guide integrates the knowledge developed throughout the module.

---

## `10-common-mistakes.md`

Reviews frequent conceptual and implementation errors.

Examples include:

- applying binary search to unsorted input;
- using incorrect interval boundaries;
- causing infinite loops;
- calculating the midpoint incorrectly;
- confusing index and value;
- failing to define duplicate behaviour;
- ignoring empty input;
- returning inconsistent absence values;
- modifying input unexpectedly;
- assuming tests prove general correctness.

The document explains why each error occurs and how it can be prevented.

---

## `11-interview-notes.md`

Provides a structured review of search concepts commonly examined in technical
interviews.

It may include:

- definitions;
- complexity comparisons;
- implementation variants;
- common boundary errors;
- duplicate-handling strategies;
- binary-search patterns;
- reasoning questions;
- implementation checkpoints.

This document is a review resource and does not replace the academic study
contained in the previous documents.

---

# Academic Perspective

Searching illustrates an important principle of algorithm design:

> **The organisation of data determines which operations can be performed
> efficiently.**

Linear search requires few assumptions but may inspect every element.

Binary search performs fewer comparisons, but it requires ordered data and
access to a reducible search interval.

This relationship demonstrates that algorithm selection cannot be separated
from:

- input properties;
- data representation;
- preprocessing cost;
- expected operation frequency;
- mutation requirements;
- memory constraints.

The fastest algorithm in one context may be inappropriate in another.

Search efficiency must therefore be evaluated as part of the complete
computational problem.

---

# Search Result Contracts

Search algorithms may represent their result in different ways.

Examples include:

```text
Index of the matching element

Boolean existence result

Optional value

Insertion position

First or last matching position

Defined sentinel value
```

The selected representation should be explicit and consistent.

For example, an index-based implementation may use:

```text
-1
```

to represent an absent target.

However, this decision must remain consistent across:

- documentation;
- Java implementation;
- tests;
- examples;
- comparisons between algorithms.

A search algorithm is not completely specified until its unsuccessful result is
defined.

---

# Duplicate Values

Duplicate values require an explicit contract.

A search algorithm may return:

- any matching position;
- the first matching position;
- the last matching position;
- every matching position;
- the number of occurrences.

A conventional binary search does not necessarily return the first duplicate.

When a specific duplicate policy is required, the strategy and tests must be
adapted accordingly.

---

# Relationship with Previous Modules

Searching builds upon the knowledge established in the previous modules.

```text
Project Documentation
        ↓
Foundations
        ↓
Complexity
        ↓
Arrays
        ↓
Searching
```

The previous modules provide:

- problem understanding;
- formal specification;
- preconditions and postconditions;
- correctness reasoning;
- invariants;
- asymptotic analysis;
- array representation;
- traversal patterns;
- boundary management.

Searching applies these concepts to the problem of locating information.

---

# Relationship with Later Modules

The concepts introduced here support several later modules.

```text
Searching
        ↓
Sorting

Searching
        ↓
Hashing

Searching
        ↓
Trees

Searching
        ↓
Graphs

Searching
        ↓
Dynamic Programming
```

Sorting can create the ordering required by binary search.

Hash tables and search trees introduce data structures designed to support
efficient retrieval.

Graph traversal generalises systematic exploration to vertices and edges.

Dynamic programming may require locating, storing, and reusing previously
computed results.

Searching is therefore not an isolated topic but a recurring computational
operation throughout the repository.

---

# Relationship with Java

Searching is studied first as a language-independent computational problem and
then implemented in Java.

The module distinguishes between:

```text
Algorithmic Concept
        ↓
General search strategy

Java Implementation
        ↓
Executable representation of that strategy

Java Standard Library
        ↓
Existing search and collection facilities
```

Relevant Java facilities may include:

- arrays;
- generic methods;
- `Comparator`;
- `Comparable`;
- `Arrays.binarySearch`;
- collection search operations;
- optional result representations.

Standard-library methods should be studied through their documented contracts.

They should not replace understanding of the underlying algorithms.

---

# Relationship with Testing

Search algorithms require systematic verification because small boundary errors
can produce incorrect results or non-terminating execution.

Tests should be derived from the search contract.

Representative cases include:

```text
Empty Input

Single-Element Input

Target at the Beginning

Target in the Middle

Target at the End

Absent Target

Duplicate Values

Invalid Input

Ordered and Unordered Input
```

Binary-search tests should pay particular attention to:

- interval boundaries;
- midpoint selection;
- two-element intervals;
- unsuccessful termination;
- duplicate policies.

Automated tests provide evidence for selected cases but do not replace
correctness reasoning.

---

# Relationship with Benchmarking

Experimental evaluation may be introduced when it supports a defined question.

Examples include:

- how linear and binary search behave across increasing input sizes;
- how unsuccessful searches affect execution behaviour;
- how input distribution influences observed measurements;
- how iterative and recursive variants compare under defined conditions;
- when sorting plus repeated binary search becomes useful compared with repeated
  linear search.

Benchmarking is not required for every search implementation.

When used, JMH benchmarks belong under:

```text
src/jmh/java/org/anaalvarez/algorithms/searching/
```

Benchmark conclusions must remain limited to the documented environment and
input conditions.

---

# Study Methodology

Each topic in this module should be studied using the repository methodology.

```text
Academic Study
        ↓
Search Problem Definition
        ↓
Input and Output Specification
        ↓
Precondition Analysis
        ↓
Strategy Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Experimental Evaluation
        when appropriate
        ↓
Engineering Conclusions
```

This process ensures that implementation follows understanding and that
conclusions remain supported by evidence.

---

# Engineering Perspective

Searching is a frequent operation in software systems.

Examples include:

- locating records;
- retrieving indexed information;
- finding files;
- navigating sorted collections;
- resolving identifiers;
- querying cached data;
- exploring graph structures;
- locating configuration entries;
- determining insertion positions.

Production systems may use indexing, hashing, trees, databases, or distributed
retrieval mechanisms.

However, these technologies depend on the same foundational questions studied
in this module:

```text
What is being searched?

How is the data organised?

Which assumptions hold?

What result is required?

What computational cost is acceptable?
```

The module provides the reasoning needed to understand those more advanced
mechanisms.

---

# Academic References

The principal academic reference for this module is:

- Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; Stein,
  Clifford. *Introduction to Algorithms*. Fourth Edition. MIT Press, 2022.

Additional supporting references include:

- Sedgewick, Robert; Wayne, Kevin. *Algorithms*. Fourth Edition.
  Addison-Wesley Professional, 2011.

- Weiss, Mark Allen. *Data Structures and Algorithm Analysis in Java*.
  Pearson.

- Knuth, Donald E. *The Art of Computer Programming, Volume 3: Sorting and
  Searching*. Addison-Wesley.

- Oracle. *Java Platform API Specification*.

The complete project bibliography is maintained in:

```text
docs/00-project/10-references.md
```

Official Java documentation should be consulted according to the Java version
configured in `pom.xml`.

---

# Expected Outcome

After completing this module, the learner should be able to:

```text
Define the search problem
        ↓
Identify input assumptions
        ↓
Select an appropriate strategy
        ↓
Specify successful and unsuccessful behaviour
        ↓
Reason about correctness
        ↓
Analyse time and space complexity
        ↓
Implement the strategy in Java
        ↓
Verify behaviour through automated tests
        ↓
Evaluate performance when appropriate
        ↓
Justify the engineering decision
```

The objective is not merely to reproduce linear or binary search.

The learner should understand why each strategy works, under which conditions it
is valid, how efficiently it uses computational resources, and when it should
be selected.

This knowledge provides the foundation required for studying sorting, hashing,
trees, graph traversal, and other data structures designed to organise and
retrieve information efficiently.

---

# Next Document

```text
01-search-fundamentals.md
```

The next document introduces searching as a computational problem and defines
the terminology required before studying individual search algorithms.