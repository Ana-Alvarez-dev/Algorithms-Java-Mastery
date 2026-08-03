# Common Mistakes

## Algorithms Java Mastery

Greedy algorithms are often considered simple because they repeatedly make the **best local decision**. However, their apparent simplicity can be misleading.

Unlike exhaustive search or Dynamic Programming, Greedy algorithms commit to decisions immediately and never reconsider them. If the selection criterion is incorrect or the problem does not satisfy the required mathematical properties, the algorithm may produce a solution that is efficient but **not optimal**.

For this reason, designing a Greedy algorithm requires more than implementing a selection rule. It requires verifying that every decision is mathematically justified, that the problem satisfies the **Greedy Choice Property** and **Optimal Substructure**, and that the supporting data structures are used correctly.

Understanding the common mistakes presented in this document helps software engineers avoid incorrect optimization strategies and design reliable Greedy solutions.

---

# 1. Purpose

The purpose of this document is to:

- identify the most common mistakes in Greedy algorithm design;
- explain why these mistakes occur;
- reinforce the mathematical foundations of the Greedy paradigm;
- improve implementation quality;
- establish good engineering practices.

---

# 2. Assuming Every Optimization Problem Has a Greedy Solution

One of the most common misconceptions is believing that every optimization problem can be solved greedily.

Conceptually:

```text
Optimization Problem

↓

Greedy Algorithm

↓

Optimal Solution
```

This reasoning is incorrect.

Many optimization problems require Dynamic Programming, Branch and Bound, or exhaustive search.

---

# 3. Ignoring the Greedy Choice Property

A Greedy algorithm is only correct if every local decision is a **safe choice**.

Ignoring this property may produce:

```text
Local Optimum

↓

Poor Global Solution
```

The Greedy Choice Property must always be verified before designing the algorithm.

---

# 4. Ignoring Optimal Substructure

Even if the first decision is safe, the remaining problem must still be solvable optimally.

Conceptually:

```text
Safe Decision

↓

Remaining Problem

↓

Optimal Solution
```

Without Optimal Substructure, Greedy algorithms cannot guarantee correctness.

---

# 5. Confusing Local and Global Optimality

A local optimum is not necessarily a global optimum.

Incorrect reasoning:

```text
Best Decision Now

↓

Best Final Solution
```

This conclusion is valid only when the mathematical properties of the problem have been proven.

---

# 6. Choosing the Wrong Selection Criterion

Every Greedy algorithm depends on a carefully selected decision rule.

Examples of incorrect criteria include:

- selecting the earliest starting activity instead of the earliest finishing activity;
- choosing the largest value when the smallest cost is required;
- selecting arbitrary candidates.

An incorrect selection criterion usually produces suboptimal solutions.

---

# 7. Assuming Testing Proves Correctness

A Greedy algorithm may succeed on many examples.

```text
Example 1

✓
```

```text
Example 2

✓
```

```text
Example 3

✓
```

However:

```text
Many Tests

≠

Mathematical Proof
```

Correctness requires formal reasoning.

---

# 8. Forgetting Correctness Proofs

Greedy algorithms should always be justified through formal proofs.

Typical techniques include:

- Exchange Argument;
- proof by contradiction;
- mathematical induction.

Implementation alone does not establish correctness.

---

# 9. Ignoring Input Preconditions

Many Greedy algorithms assume:

- sorted input;
- valid intervals;
- non-negative weights;
- properly initialized data structures.

Violating these assumptions often produces incorrect results.

---

# 10. Forgetting to Sort the Input

Many Greedy scheduling algorithms require sorting before selection.

Incorrect process:

```text
Unsorted Activities

↓

Greedy Selection
```

Correct process:

```text
Sort

↓

Greedy Selection
```

Skipping the sorting phase generally invalidates the algorithm.

---

# 11. Using the Wrong Data Structure

The efficiency of a Greedy algorithm often depends on its supporting data structures.

Examples:

- using a list instead of a Priority Queue for Huffman Coding;
- repeatedly searching unsorted collections.

Poor data structure choices increase execution time unnecessarily.

---

# 12. Misusing Priority Queues

Algorithms such as Huffman Coding repeatedly require the smallest available element.

Using an inappropriate structure leads to repeated linear searches.

Conceptually:

```text
Priority Queue

↓

Efficient Selection
```

instead of:

```text
Repeated Linear Search
```

---

# 13. Forgetting Irreversible Decisions

Greedy decisions are permanent.

Incorrect reasoning:

```text
Choose

↓

Undo Later
```

Greedy algorithms do not backtrack.

Once a decision is made, execution continues with the remaining problem.

---

# 14. Applying Greedy When Dynamic Programming Is Required

Some optimization problems contain overlapping subproblems.

Conceptually:

```text
Repeated
Subproblems
```

These problems often require Dynamic Programming rather than a Greedy strategy.

Choosing Greedy in these situations usually produces incorrect solutions.

---

# 15. Ignoring Complexity Analysis

Developers sometimes focus only on correctness.

Algorithm analysis should also consider:

- execution time;
- auxiliary memory;
- scalability;
- preprocessing costs.

Efficient algorithms balance correctness with computational performance.

---

# 16. Assuming Big-O Is Everything

Two Greedy algorithms with identical asymptotic complexity may perform differently.

Factors include:

- sorting implementation;
- cache locality;
- constant factors;
- memory allocation;
- data structures.

Engineering decisions require more than asymptotic notation.

---

# 17. Poor Boundary Handling

Scheduling algorithms frequently compare:

- start times;
- finish times;
- interval compatibility.

Incorrect comparisons may produce:

- overlapping schedules;
- skipped activities;
- invalid interval selection.

Careful boundary management is essential.

---

# 18. Insufficient Testing

A complete testing strategy should include:

- empty input;
- one element;
- duplicate values;
- already sorted data;
- reverse order;
- large datasets;
- edge cases.

These scenarios frequently reveal hidden implementation errors.

---

# 19. Better Design Process

Professional Greedy algorithm design follows a systematic workflow.

```text
Understand Problem
        ↓
Verify Greedy Choice Property
        ↓
Verify Optimal Substructure
        ↓
Choose Selection Rule
        ↓
Prove Correctness
        ↓
Analyze Complexity
        ↓
Implement
        ↓
Test
```

Following this sequence significantly reduces design mistakes.

---

# 20. Engineering Perspective

Professional software engineers never adopt a Greedy solution simply because it appears simple or efficient.

Instead, they evaluate:

```text
Problem Analysis
        ↓
Mathematical Properties
        ↓
Correctness
        ↓
Complexity
        ↓
Implementation
        ↓
Testing
```

Successful Greedy algorithms are the result of rigorous mathematical reasoning combined with efficient implementation.

---

# 21. Relationship with the Next Module

This document concludes the study of the **Greedy Algorithms** module.

The next module introduces **Dynamic Programming**, another major optimization paradigm.

Unlike Greedy algorithms, Dynamic Programming solves problems with **overlapping subproblems** by storing intermediate solutions instead of making irreversible local decisions.

Understanding the strengths and limitations of Greedy algorithms provides the foundation for appreciating why Dynamic Programming is necessary for more complex optimization problems.

---

# 22. Key Takeaways

After completing this document, the learner should understand that:

- not every optimization problem admits a Greedy solution;
- the Greedy Choice Property and Optimal Substructure must both be verified;
- local optimality does not automatically imply global optimality;
- correctness requires formal mathematical proof;
- preprocessing and appropriate data structures are essential for efficient implementations;
- Priority Queues are fundamental for algorithms such as Huffman Coding;
- algorithm analysis should consider both complexity and implementation quality;
- disciplined design and systematic testing greatly improve the reliability of Greedy algorithms;
- professional software engineers combine mathematical reasoning with practical engineering considerations when designing Greedy solutions.