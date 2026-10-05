# Arrays

## Algorithms Java Mastery

The **03-arrays** module introduces the first fundamental data structure studied throughout the **Algorithms Java Mastery** repository.

Arrays provide the foundation upon which many classical algorithms and more advanced data structures are built. Understanding their properties is essential before studying searching algorithms, sorting algorithms, trees, graphs and dynamic programming.

Unlike later dynamic data structures, arrays have a fixed size and store elements in contiguous memory locations. This organisation enables constant-time random access while introducing trade-offs for insertion and deletion operations.

---

# Academic Foundation

The concepts developed throughout this module are primarily based on
*Introduction to Algorithms* (Cormen, Leiserson, Rivest and Stein) and are
complemented by recognised Computer Science literature and the official
references listed in:

```text
docs/00-project/10-references.md
```

Rather than reproducing existing material, this repository transforms these
academic foundations into original explanations, Java implementations,
automated tests, and engineering-oriented documentation.

---

# Purpose

The purpose of this module is to develop a rigorous understanding of arrays from both theoretical and practical perspectives.

The learner will study:

- conceptual foundations;
- memory organisation;
- Java implementation;
- computational complexity;
- traversal techniques;
- classical algorithms;
- engineering best practices.

The expected progression is:

```text
Array Fundamentals
        ↓
Memory Organisation
        ↓
Java Representation
        ↓
Fundamental Operations
        ↓
Complexity Analysis
        ↓
Traversal Strategies
        ↓
Algorithmic Applications
        ↓
Problem Solving
        ↓
Engineering Practices
```

---

# Learning Objectives

After completing this module, the learner should be able to:

- explain the structure of arrays;
- understand contiguous memory allocation;
- implement arrays correctly in Java;
- analyse array operations using asymptotic notation;
- solve algorithmic problems involving arrays;
- recognise common implementation mistakes;
- justify engineering decisions involving arrays.
- identify the dominant operations performed on arrays;
- justify the choice of arrays over alternative data structures;
- recognise the relationship between memory layout and algorithm efficiency.
---
# Academic Perspective

Arrays represent one of the fundamental abstractions of Computer Science.

Their importance extends beyond language syntax because many classical
algorithms, data structures, and memory-management strategies assume contiguous
storage and constant-time indexed access.

Understanding arrays provides the conceptual basis required for analysing
algorithm efficiency, selecting appropriate data structures, and implementing
efficient solutions throughout the remainder of this repository.

The concepts introduced in this module therefore become reusable engineering
knowledge rather than language-specific programming techniques.
---
# Core Principles

The Arrays module is organised around five complementary principles.

- Understand arrays as an abstract data structure before studying Java syntax.
- Relate contiguous memory organisation to algorithm behaviour.
- Analyse every operation before implementing optimisation strategies.
- Connect theoretical complexity with practical implementation.
- Develop reusable reasoning applicable to more advanced data structures.

These principles guide every document contained in this module.

---
# Module Structure

```text
03-arrays/
├── README.md
├── 01-array-fundamentals.md
├── 02-memory-layout.md
├── 03-java-arrays.md
├── 04-multidimensional-arrays.md
├── 05-array-operations.md
├── 06-traversal-patterns.md
├── 07-common-algorithms.md
├── 08-complexity-analysis.md
├── 09-problem-solving-guide.md
├── 10-common-mistakes.md
└── 11-interview-notes.md
```

---

# Relationship with Java

Although arrays are introduced as an abstract data structure, this module also
studies their representation in Java.

The objective is to distinguish between:

- the computational concept of an array;
- the Java language implementation;
- the engineering implications of using arrays in real software systems.

This distinction reinforces the repository's principle that algorithms should
be understood independently of any programming language before being
implemented.

---

# Position Within the Repository

This module builds upon:

- Project Documentation
- Algorithmic Foundations
- Computational Complexity

It also establishes the knowledge required for subsequent modules, including:

- Searching
- Sorting
- Hashing
- Trees
- Graphs
- Dynamic Programming

Arrays therefore represent the first practical application of the algorithmic study methodology adopted throughout this repository.

---

---

# Traceability Architecture

Arrays is the first module that demonstrates the complete repository
traceability chain with executable evidence.

```text
CLRS / Academic Source
        ↓
01-foundations
        ↓
02-complexity
        ↓
03-arrays
        ↓
src/main/java/org/anaalvarezdev/algorithms/arrays/
        ↓
src/test/java/org/anaalvarezdev/algorithms/arrays/
        ↓
src/jmh/java/org/anaalvarezdev/algorithms/arrays/
```

Not every document requires a Java class.

Not every Java class requires a benchmark.

Traceability means that each artefact has a justified conceptual source and a
clear engineering responsibility.

---

# Document-to-Evidence Traceability

| Arrays Document | Main Responsibility | Primary Evidence |
|---|---|---|
| `01-array-fundamentals.md` | Indexed structure, fixed length, basic properties | Access and update implementations |
| `02-memory-layout.md` | Classical contiguous-memory model and locality | Conceptual support for indexed access |
| `03-java-arrays.md` | Java-specific semantics, references, bounds | Java source and validation behaviour |
| `04-multidimensional-arrays.md` | Arrays of arrays and multidimensional traversal | Future matrix/grid exercises |
| `05-array-operations.md` | Access, update, copy, insertion, deletion | Core operation classes and tests |
| `06-traversal-patterns.md` | Sequential, reverse, two-pointer, stateful traversal | Traversal, reverse, duplicate-removal classes |
| `07-common-algorithms.md` | Representative array algorithms | Minimum, maximum, search, prefix sum |
| `08-complexity-analysis.md` | Time and auxiliary-space analysis | Complexity statements and JMH interpretation |
| `09-problem-solving-guide.md` | Apply the repository methodology | Design workflow before code |
| `10-common-mistakes.md` | Prevent conceptual and implementation errors | Edge-case and invalid-input tests |
| `11-interview-notes.md` | Technical-review and communication practice | Technical-assessment preparation |

The interview document is a review layer and must remain bounded by concepts
actually developed in the module.

---

# Implementation Traceability

The current implementation package is:

```text
src/main/java/org/anaalvarezdev/algorithms/arrays/
├── AccessArrays.java
├── ArrayUpdate.java
├── ArrayTraversal.java
├── ArrayAggregation.java
├── FindMinimum.java
├── FindMaximum.java
├── FindMinimumAndMaximum.java
├── ArrayReverse.java
├── ArrayCopy.java
├── InsertAtIndex.java
├── DeleteAtIndex.java
├── RemoveDuplicatesSorted.java
└── PrefixSum.java
```

The current evidence mapping is:

| Implementation | Main Documentation Source | Test Evidence | Benchmark Evidence |
|---|---|---|---|
| `AccessArrays.java` | `01-array-fundamentals.md`, `05-array-operations.md` | `AccessArraysTest.java` | Not required |
| `ArrayUpdate.java` | `03-java-arrays.md`, `05-array-operations.md` | `ArrayUpdateTest.java` | Not required |
| `ArrayTraversal.java` | `06-traversal-patterns.md` | `ArrayTraversalTest.java` | `ArrayTraversalBenchmark.java` |
| `ArrayAggregation.java` | `06-traversal-patterns.md`, `07-common-algorithms.md` | `ArrayAggregationTest.java` | `ArrayAggregationBenchmark.java` |
| `FindMinimum.java` | `07-common-algorithms.md` | `FindMinimumTest.java` | `FindMinimumBenchmark.java` |
| `FindMaximum.java` | `07-common-algorithms.md` | `FindMaximumTest.java` | `FindMaximumBenchmark.java` |
| `FindMinimumAndMaximum.java` | `07-common-algorithms.md` | `FindMinimumAndMaximumTest.java` | `FindMinimumAndMaximumBenchmark.java` |
| `ArrayReverse.java` | `06-traversal-patterns.md`, `07-common-algorithms.md` | `ArrayReverseTest.java` | `ArrayReverseBenchmark.java` |
| `ArrayCopy.java` | `05-array-operations.md` | `ArrayCopyTest.java` | `ArrayCopyBenchmark.java` |
| `InsertAtIndex.java` | `05-array-operations.md` | `InsertAtIndexTest.java` | `InsertAtIndexBenchmark.java` |
| `DeleteAtIndex.java` | `05-array-operations.md` | `DeleteAtIndexTest.java` | `DeleteAtIndexBenchmark.java` |
| `RemoveDuplicatesSorted.java` | `06-traversal-patterns.md`, `07-common-algorithms.md` | `RemoveDuplicatesSortedTest.java` | `RemoveDuplicatesSortedBenchmark.java` |
| `PrefixSum.java` | `07-common-algorithms.md`, `08-complexity-analysis.md` | `PrefixSumTest.java` | `PrefixSumBenchmark.java` |

This table records the current state of the repository and should be updated
when the implementation package evolves.

---

## Searching Ownership

Linear Search was introduced during the Arrays learning progression because it
builds directly on sequential traversal.

Its canonical implementation now belongs to the dedicated Searching module:

```text
docs/03-arrays/
        ↓
Traversal foundation
        ↓
docs/04-searching/
        ↓
src/main/java/org/anaalvarezdev/algorithms/searching/LinearSearch.java
```

This preserves the conceptual relationship without duplicating the Java
implementation.

---

# Correctness Traceability

Correctness concepts are sourced from Foundations rather than duplicated.

```text
docs/01-foundations/05-correctness.md
        +
docs/01-foundations/06-invariants.md
        ↓
Array Algorithm
        ↓
Invariant / Contract
        ↓
Java Implementation
        ↓
JUnit Evidence
```

Example:

```text
FindMaximum
        ↓
Invariant:
maximum is the greatest value
among the processed elements
        ↓
Termination:
all elements have been processed
        ↓
Postcondition:
the returned value is the maximum
```

---

# Complexity Traceability

Complexity concepts are sourced from `docs/02-complexity/` and applied in
`08-complexity-analysis.md`.

Examples include:

| Operation / Algorithm | Expected Analysis |
|---|---:|
| Indexed access | Θ(1) |
| Indexed update | Θ(1) |
| Complete traversal | Θ(n) |
| Minimum / maximum scan | Θ(n) |
| Linear search | Θ(1) best, Θ(n) worst |
| Reverse in place | Θ(n) time, Θ(1) auxiliary space |
| Copy | Θ(n) time and storage for the new array |
| Insert/delete with shifting or copying | O(n) worst case |
| Prefix-sum construction | Θ(n) preprocessing |

The learner should derive these results from the operations performed rather
than memorise the table.

---

# Testing and Benchmarking Traceability

Tests are derived from the contract:

```text
Specification
        ↓
Preconditions / Postconditions
        ↓
Edge Cases
        ↓
Implementation
        ↓
JUnit Jupiter + AssertJ
```

Benchmarks are derived from an empirical question:

```text
Theoretical Expectation
        ↓
Java Implementation
        ↓
JMH Experiment
        ↓
Interpretation
```

A benchmark is not required simply because a class exists.

---

# Technical Review Boundary

The module already contains:

```text
11-interview-notes.md
```

It includes technical questions and reasoning prompts specifically related to
arrays.

The purpose is to verify understanding and communication, not to introduce
unrelated interview material.

Future module reviews should follow the same rule:

> **Technical-review questions must remain traceable to the academic concepts
> and engineering artefacts actually studied in that module.**

---

# Module Completion Criteria

The Arrays module can be considered complete when the learner can:

- explain the abstract array model;
- distinguish the abstract structure from Java-specific semantics;
- justify constant-time indexed access;
- explain linear traversal;
- reason about insertion and deletion costs;
- implement and test the current array algorithms;
- formulate relevant loop invariants;
- derive time and auxiliary-space complexity;
- distinguish copying from aliasing;
- explain the sorted-input precondition used by duplicate compaction;
- explain prefix-sum preprocessing and its trade-off;
- run the relevant automated tests;
- interpret JMH evidence without confusing it with asymptotic analysis;
- answer the questions in `11-interview-notes.md` using reasoning rather than
  memorised responses.

---

# Navigation

**Previous:** `docs/02-complexity/`

**Next:** `docs/04-searching/`


# Expected Outcome

Upon completing this module, the learner will possess a solid understanding of arrays as both a programming construct and a fundamental computational abstraction.

This knowledge will serve as the basis for implementing efficient algorithms, analysing computational complexity and designing scalable software systems throughout the remainder of the **Algorithms Java Mastery** project.