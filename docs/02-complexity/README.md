# Complexity

## Algorithms Java Mastery

The **Complexity** module introduces the mathematical and analytical principles used to evaluate the efficiency of algorithms.

After learning how to analyse and specify computational problems in the **Foundations** module, the next step is to understand **how algorithms consume computational resources** and how their performance changes as the input size increases.

This module establishes the theoretical basis required to compare algorithms objectively before implementing performance experiments.

Rather than asking:

> *Does the algorithm work?*

this module asks:

> **How efficiently does the algorithm solve the problem?**

---

# Purpose

The purpose of this module is to develop the ability to analyse algorithm efficiency using formal computational models.

Throughout this module, the learner will study:

- computational complexity;
- asymptotic analysis;
- time complexity;
- space complexity;
- asymptotic notations;
- algorithm growth;
- best, average, and worst-case analysis;
- amortized analysis;
- practical complexity examples.

These concepts provide the analytical foundation that will later be applied to every algorithm implemented in this repository.

---

# Learning Objectives

After completing this module, the learner should be able to:

- explain why computational complexity matters;
- compare algorithms independently of hardware;
- analyse execution time and memory usage;
- understand asymptotic behaviour;
- distinguish between theoretical analysis and experimental measurement;
- select appropriate algorithms based on efficiency;
- interpret complexity notations correctly.

These competencies are essential for designing scalable and maintainable software systems.

---

# Module Structure

The current structure of this module is:

```text
02-complexity/
├── README.md
├── computational-complexity.md
├── asymptotic-analysis.md
├── time-complexity.md
├── space-complexity.md
├── asymptotic-notations.md
├── algorithm-growth.md
├── best-average-worst-case.md
├── amortized-analysis.md
├── complexity-examples.md
└── complexity-cheat-sheet.md
```

Each document explores a specific aspect of computational complexity while progressively building a complete understanding of algorithm analysis.

---

# Document Overview

## computational-complexity.md

Introduces the concept of computational complexity and explains why algorithm efficiency is one of the central concerns of Computer Science.

---

## asymptotic-analysis.md

Explains how algorithm behaviour is analysed as the input size approaches infinity, allowing objective comparisons independently of hardware.

---

## time-complexity.md

Studies the growth of execution time according to input size and introduces methods for counting algorithmic operations.

---

## space-complexity.md

Examines how algorithms use memory and explains the trade-offs between execution time and memory consumption.

---

## asymptotic-notations.md

Introduces the mathematical notation used to describe algorithm growth, including:

- Big O;
- Big Ω (Omega);
- Big Θ (Theta);
- little o;
- little ω.

---

## algorithm-growth.md

Compares the most common growth rates observed in algorithms, from constant time to factorial complexity.

Visual comparisons and growth curves are introduced to improve intuition.

---

## best-average-worst-case.md

Explains how algorithm performance varies depending on the characteristics of the input.

The document distinguishes between:

- best case;
- average case;
- worst case.

---

## amortized-analysis.md

Introduces amortized analysis and explains why certain expensive operations remain efficient when evaluated over long execution sequences.

Examples include dynamic arrays and hash tables.

---

## complexity-examples.md

Applies theoretical concepts to real algorithms.

Examples include:

- Linear Search;
- Binary Search;
- Bubble Sort;
- Merge Sort;
- Quick Sort;
- Tree traversals;
- Hash Table operations.

The objective is to connect mathematical analysis with practical algorithm behaviour.

---

## complexity-cheat-sheet.md

Provides a concise summary of the entire module.

It serves as a quick-reference guide for reviewing complexity concepts before implementing or analysing algorithms.

---

# Relationship with Previous Modules

This module extends the concepts introduced in **Foundations**.

The learning progression becomes:

```text
Project
        ↓
Foundations
        ↓
Complexity
```

Where:

- **Foundations** explains how to understand and design algorithms.
- **Complexity** explains how to evaluate their efficiency.

Both modules together establish the theoretical basis required before implementing searching algorithms, sorting algorithms, trees, graphs, and dynamic programming.

---

# Relationship with Later Modules

Every subsequent module will apply the concepts introduced here.

```text
Complexity
        ↓
Arrays
        ↓
Searching
        ↓
Sorting
        ↓
Recursion
        ↓
Trees
        ↓
Graphs
        ↓
Dynamic Programming
```

Each implemented algorithm will include:

- correctness reasoning;
- complexity analysis;
- Java implementation;
- automated testing;
- benchmarking.

---

# Relationship with the Technology Stack

The concepts studied in this module are directly connected with the technologies adopted throughout the repository.

- **Java** — implementation of algorithms.
- **JUnit Jupiter** — verification that implementations remain correct while analysing complexity.
- **AssertJ** — expressive assertions for validating expected behaviour.
- **JMH (Java Microbenchmark Harness)** — experimental performance measurement used to compare theoretical complexity with real execution times.
- **Git & GitHub** — version control for source code and documentation.
- **Linux** — execution environment widely used for software development and performance analysis.
- **GitHub Actions** — automated build and testing pipeline ensuring that algorithm implementations remain stable as the project evolves.

---

# Study Methodology

Every document in this module should be studied using the methodology established in the **Foundations** module.

The recommended progression is:

```text
Concept
        ↓
Mathematical Definition
        ↓
Visual Interpretation
        ↓
Worked Examples
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Benchmarking (JMH)
        ↓
Technical Conclusions
```

This methodology ensures that theoretical knowledge is progressively transformed into practical engineering skills.

---

# Key Takeaways

The learner should remember the following principles:

- Efficiency is a fundamental characteristic of every algorithm.
- Complexity analysis is independent of programming languages and hardware.
- Big O describes asymptotic growth rather than execution time.
- Time and space complexity often involve trade-offs.
- Theoretical analysis should be complemented by experimental benchmarking.
- Understanding complexity is essential before studying advanced algorithms.

The **Complexity** module therefore provides the analytical framework that supports every algorithm explored throughout the **Algorithms Java Mastery** repository, connecting mathematical reasoning, software engineering, and empirical performance evaluation.