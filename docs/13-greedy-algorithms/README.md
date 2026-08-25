# Greedy Algorithms

## Algorithms Java Mastery

**Greedy Algorithms** represent one of the fundamental algorithm design paradigms in Computer Science. Unlike Divide and Conquer, which recursively solves independent subproblems, Greedy algorithms construct a solution **incrementally**, making the best available decision at each step without reconsidering previous choices.

The central idea behind the Greedy paradigm is deceptively simple:

> **Make the locally optimal choice at every step in the hope of obtaining a globally optimal solution.**

However, this strategy does **not** work for every optimization problem. Before a Greedy algorithm can be considered correct, the problem must satisfy specific mathematical properties, most notably the **Greedy Choice Property** and **Optimal Substructure**.

For this reason, studying Greedy algorithms is not simply learning a collection of optimization techniques. It is learning how to recognize problems where locally optimal decisions can be proven to produce globally optimal solutions.

This module introduces the theoretical foundations of the Greedy paradigm, studies its mathematical correctness, analyzes classical optimization problems, and evaluates its computational complexity from an engineering perspective using Java.

---

# Learning Objectives

After completing this module, the learner should be able to:

- explain the Greedy algorithm design paradigm;
- distinguish Greedy algorithms from Divide and Conquer and Dynamic Programming;
- understand the Greedy Choice Property;
- understand Optimal Substructure;
- explain why some optimization problems admit Greedy solutions while others do not;
- prove the correctness of Greedy algorithms using formal reasoning;
- analyze classical Greedy problems;
- evaluate computational complexity and practical performance;
- recognize situations where the Greedy paradigm is inappropriate.

---

# Module Structure

```text
13-greedy-algorithms/
│── README.md
│── 01-greedy-strategy.md
│── 02-greedy-choice-property.md
│── 03-optimal-substructure.md
│── 04-correctness-proofs.md
│── 05-activity-selection.md
│── 06-interval-scheduling.md
│── 07-huffman-coding.md
│── 08-complexity-analysis.md
└── 09-common-mistakes.md
```

---

# Module Contents

## 01. Greedy Strategy

Introduces the Greedy paradigm as an algorithm design strategy.

Topics include:

- local optimization;
- incremental solution construction;
- optimization problems;
- decision making;
- comparison with other paradigms;
- engineering intuition.

---

## 02. Greedy Choice Property

Explains the fundamental property that allows Greedy algorithms to make locally optimal decisions while preserving global optimality.

Topics include:

- local optimum;
- global optimum;
- safe choices;
- decision independence;
- proof intuition;
- applicability conditions.

---

## 03. Optimal Substructure

Studies the second essential property required by Greedy algorithms.

Topics include:

- optimal subproblems;
- recursive structure;
- optimal solutions;
- decomposition;
- relationship with Divide and Conquer;
- relationship with Dynamic Programming.

---

## 04. Correctness Proofs

Introduces formal reasoning techniques used to prove that Greedy algorithms always produce optimal solutions.

Topics include:

- exchange argument;
- contradiction proofs;
- inductive reasoning;
- proof strategies;
- correctness verification.

---

## 05. Activity Selection

Studies the classical Activity Selection problem.

Topics include:

- interval scheduling;
- earliest finishing time;
- greedy decisions;
- proof of correctness;
- algorithm implementation;
- complexity analysis.

---

## 06. Interval Scheduling

Expands the Activity Selection problem to more general scheduling scenarios.

Topics include:

- interval optimization;
- compatible intervals;
- scheduling strategies;
- greedy ordering;
- practical applications.

---

## 07. Huffman Coding

Studies Huffman Coding as one of the most important applications of the Greedy paradigm.

Topics include:

- prefix codes;
- binary trees;
- frequency analysis;
- optimal encoding;
- data compression;
- tree construction.

---

## 08. Complexity Analysis

Summarizes the computational complexity of the Greedy algorithms studied in this module.

Topics include:

- time complexity;
- auxiliary space;
- sorting costs;
- priority queues;
- scalability;
- engineering trade-offs.

---

## 09. Common Mistakes

Reviews the conceptual and implementation mistakes frequently encountered when designing Greedy algorithms.

Topics include:

- invalid greedy choices;
- incorrect ordering criteria;
- missing proof of correctness;
- confusing local and global optimality;
- inappropriate problem selection.

---

# Learning Progression

This module follows the natural progression used when designing Greedy algorithms.

```text
Optimization Problem
        ↓
Identify Candidate Choices
        ↓
Greedy Choice
        ↓
Verify Greedy Choice Property
        ↓
Verify Optimal Substructure
        ↓
Prove Correctness
        ↓
Analyze Complexity
        ↓
Engineering Evaluation
```

Each document develops one stage of this reasoning process before introducing increasingly sophisticated case studies.

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
Dynamic Programming
```

Unlike Divide and Conquer, Greedy algorithms **do not solve every recursive subproblem** before making a decision.

Instead:

```text
Current Problem
        ↓
Best Immediate Decision
        ↓
Reduce Remaining Problem
        ↓
Repeat
```

The next module, Dynamic Programming, will study problems where Greedy decisions are insufficient because subproblems overlap.

---

# Java Perspective

Greedy algorithms frequently rely on efficient data structures available in Java.

Common Java components include:

- arrays;
- `ArrayList`;
- `PriorityQueue`;
- sorting utilities;
- comparators;
- binary trees;
- hash-based collections.

Many Greedy algorithms begin by sorting the input or repeatedly selecting the smallest or largest available element, making Java's Collections Framework an important implementation tool.

---

# Engineering Perspective

Professional software engineers choose Greedy algorithms when:

- optimal local decisions can be formally justified;
- simplicity improves maintainability;
- high performance is required;
- optimization must be performed efficiently.

Before selecting a Greedy solution, engineers verify:

- Does the problem satisfy the Greedy Choice Property?
- Does it exhibit Optimal Substructure?
- Can correctness be formally proven?
- Does the Greedy strategy outperform alternative approaches?

Greedy algorithms should therefore be viewed as a mathematical design paradigm rather than simply a programming technique.

---

# Key Takeaways

After completing this module, the learner should understand that:

- Greedy algorithms construct solutions incrementally through locally optimal decisions;
- the Greedy Choice Property determines whether local decisions lead to a globally optimal solution;
- Optimal Substructure is a necessary condition for many Greedy algorithms;
- correctness must be formally proven rather than assumed;
- Activity Selection and Huffman Coding are classical Greedy algorithms;
- computational complexity depends on the supporting data structures and preprocessing steps;
- Greedy algorithms often provide elegant and highly efficient solutions but are not universally applicable;
- understanding the mathematical foundations of the Greedy paradigm prepares the learner for studying Dynamic Programming and more advanced optimization techniques.