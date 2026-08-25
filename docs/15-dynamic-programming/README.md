# Dynamic Programming

## Algorithms Java Mastery

**Dynamic Programming (DP)** is one of the most powerful algorithm design paradigms in Computer Science. It is designed to solve optimization and counting problems that exhibit two fundamental mathematical properties:

- **Overlapping Subproblems**
- **Optimal Substructure**

Unlike Divide and Conquer, which repeatedly solves independent subproblems, Dynamic Programming avoids redundant computation by **storing previously computed solutions** and reusing them whenever the same subproblem appears again.

The central idea of Dynamic Programming is simple:

> **Never solve the same subproblem more than once.**

This seemingly simple principle transforms many exponential-time recursive algorithms into polynomial-time solutions.

Dynamic Programming is widely used in software engineering, artificial intelligence, bioinformatics, operations research, networking, compiler optimization, robotics, and financial systems. It is also one of the most important topics in technical interviews because it combines recursive reasoning, mathematical modeling, optimization, and computational complexity.

This module introduces the theoretical foundations of Dynamic Programming before studying memoization, tabulation, state modeling, recurrence design, and several classical case studies.

---

# Learning Objectives

After completing this module, the learner should be able to:

- explain the Dynamic Programming paradigm;
- identify problems with overlapping subproblems;
- recognize Optimal Substructure;
- distinguish Dynamic Programming from Divide and Conquer, Greedy algorithms, and Backtracking;
- design states and recurrence relations;
- implement both Memoization and Tabulation approaches;
- analyze computational complexity;
- solve classical Dynamic Programming problems using Java.

---

# Module Structure

```text
15-dynamic-programming/
│── README.md
│── 01-dynamic-programming-fundamentals.md
│── 02-overlapping-subproblems.md
│── 03-optimal-substructure.md
│── 04-memoization.md
│── 05-tabulation.md
│── 06-state-definition.md
│── 07-recurrence-design.md
│── 08-fibonacci-case-study.md
│── 09-knapsack-case-study.md
│── 10-longest-common-subsequence.md
│── 11-complexity-analysis.md
└── 12-common-mistakes.md
```

---

# Module Contents

## 01. Dynamic Programming Fundamentals

Introduces Dynamic Programming as an algorithm design paradigm.

Topics include:

- optimization problems;
- recursive reasoning;
- repeated computation;
- dynamic programming intuition;
- algorithm design principles.

---

## 02. Overlapping Subproblems

Explains the first mathematical property required for Dynamic Programming.

Topics include:

- repeated recursive calls;
- redundant computation;
- subproblem reuse;
- recursion trees;
- computational savings.

---

## 03. Optimal Substructure

Studies the second mathematical property required for Dynamic Programming.

Topics include:

- optimal solutions;
- recursive decomposition;
- optimization problems;
- relationship with Greedy algorithms;
- relationship with Divide and Conquer.

---

## 04. Memoization

Introduces the **Top-Down** implementation strategy.

Topics include:

- recursive caching;
- lookup tables;
- lazy computation;
- recursive optimization;
- cache reuse.

---

## 05. Tabulation

Introduces the **Bottom-Up** implementation strategy.

Topics include:

- iterative computation;
- table construction;
- dependency ordering;
- state transitions;
- iterative optimization.

---

## 06. State Definition

Explains how to model Dynamic Programming problems.

Topics include:

- state variables;
- state representation;
- decision variables;
- transition states;
- problem modeling.

---

## 07. Recurrence Design

Studies how mathematical recurrence relations describe state transitions.

Topics include:

- recursive equations;
- transition functions;
- base cases;
- dependency graphs;
- optimization reasoning.

---

## 08. Fibonacci Case Study

Introduces the classical Dynamic Programming example.

Topics include:

- exponential recursion;
- memoization;
- tabulation;
- performance comparison;
- recursive optimization.

---

## 09. Knapsack Case Study

Studies one of the most important optimization problems.

Topics include:

- capacity constraints;
- value optimization;
- state transitions;
- dynamic programming tables;
- optimization strategies.

---

## 10. Longest Common Subsequence

Studies one of the most influential sequence comparison algorithms.

Topics include:

- sequence alignment;
- character comparison;
- dynamic programming matrix;
- recursive relations;
- practical applications.

---

## 11. Complexity Analysis

Summarizes the computational characteristics of Dynamic Programming algorithms.

Topics include:

- time complexity;
- auxiliary space;
- state count;
- transition cost;
- scalability;
- engineering trade-offs.

---

## 12. Common Mistakes

Reviews the conceptual and implementation mistakes frequently encountered when designing Dynamic Programming algorithms.

Topics include:

- incorrect state definitions;
- invalid recurrence relations;
- missing base cases;
- inefficient memoization;
- unnecessary memory usage.

---

# Learning Progression

This module follows the reasoning process used when designing Dynamic Programming algorithms.

```text
Problem Definition
        ↓
Recursive Solution
        ↓
Repeated Computation
        ↓
Identify Overlapping Subproblems
        ↓
Verify Optimal Substructure
        ↓
Define States
        ↓
Design Recurrence
        ↓
Choose Memoization or Tabulation
        ↓
Analyze Complexity
        ↓
Engineering Evaluation
```

Each document develops one stage of this process before introducing progressively more sophisticated case studies.

---

# Relationship with Previous Modules

This module builds upon concepts introduced throughout the repository.

```text
Recursion
        ↓
Divide and Conquer
        ↓
Greedy Algorithms
        ↓
Backtracking
        ↓
Dynamic Programming
```

Unlike Divide and Conquer:

```text
Solve Subproblem

↓

Discard Result
```

Dynamic Programming performs:

```text
Solve Subproblem
        ↓
Store Result
        ↓
Reuse Result
```

Unlike Greedy algorithms, Dynamic Programming evaluates multiple possibilities instead of making irreversible local decisions.

Unlike Backtracking, it avoids exploring identical subproblems repeatedly by storing intermediate solutions.

---

# Java Perspective

Dynamic Programming algorithms are commonly implemented in Java using:

- arrays;
- multidimensional arrays;
- `HashMap`;
- recursion;
- iterative loops;
- `ArrayList`.

Memoization often uses recursive methods combined with arrays or maps, while Tabulation typically relies on iterative loops that progressively fill a table.

---

# Engineering Perspective

Professional software engineers apply Dynamic Programming when:

- recursive solutions perform repeated computations;
- optimization problems contain overlapping subproblems;
- state transitions can be formally defined;
- improved computational efficiency is required.

Before selecting Dynamic Programming, engineers typically ask:

- Does the problem contain overlapping subproblems?
- Does it exhibit Optimal Substructure?
- What defines the state?
- How should the recurrence relation be constructed?
- Is a Top-Down or Bottom-Up implementation more appropriate?

Answering these questions ensures that Dynamic Programming is applied correctly and efficiently.

---

# Key Takeaways

After completing this module, the learner should understand that:

- Dynamic Programming eliminates redundant computation by storing intermediate results;
- the paradigm requires both Overlapping Subproblems and Optimal Substructure;
- Memoization and Tabulation are the two principal implementation strategies;
- correct state definition and recurrence design are the foundation of every Dynamic Programming solution;
- classical problems such as Fibonacci, Knapsack, and Longest Common Subsequence illustrate the practical application of the paradigm;
- Dynamic Programming often transforms exponential recursive algorithms into polynomial-time solutions;
- efficient Dynamic Programming solutions balance execution time, memory usage, and implementation simplicity;
- mastering Dynamic Programming represents a major milestone in algorithmic reasoning and prepares the learner for advanced optimization problems encountered in professional software engineering.