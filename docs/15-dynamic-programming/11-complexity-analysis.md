# Complexity Analysis

## Algorithms Java Mastery

The efficiency of a **Dynamic Programming (DP)** algorithm depends primarily on the number of **distinct states** that must be computed and the cost of computing each state.

Unlike naive recursive algorithms, which may solve the same subproblem many times, Dynamic Programming guarantees that **each state is evaluated at most once**. This transformation often reduces exponential-time algorithms to polynomial-time algorithms, making many optimization problems computationally feasible.

However, Dynamic Programming does not eliminate computational complexity entirely. The performance of a DP algorithm depends on several design decisions, including:

- the definition of the state;
- the recurrence relation;
- the implementation strategy (Memoization or Tabulation);
- the number of state transitions;
- memory usage.

Understanding these factors is essential for analyzing the scalability and practical performance of Dynamic Programming algorithms.

---

# 1. Purpose

The purpose of this document is to:

- analyze the computational complexity of Dynamic Programming algorithms;
- understand the relationship between states and performance;
- compare Memoization and Tabulation;
- evaluate time and space complexity;
- summarize the complexity of the case studies presented in this module.

---

# 2. Fundamental Principle

The complexity of a Dynamic Programming algorithm is determined by two quantities.

```text
Number of States

×

Transition Cost

↓

Total Complexity
```

This principle forms the basis of most DP complexity analyses.

---

# 3. Number of States

Every unique state is computed once.

Examples:

```text
State(n)
```

or

```text
State(i,j)
```

The total number of possible states determines the size of the Dynamic Programming problem.

---

# 4. Transition Cost

Each state requires a computation based on previously solved states.

Conceptually:

```text
Current State

↓

Evaluate
Dependencies

↓

Store Result
```

The transition cost is the amount of work required to compute one state.

---

# 5. General Time Complexity

The overall running time is:

```text
Number of States

×

Transition Cost
```

In mathematical notation:

```text
Time Complexity

=

States × Transition Cost
```

This formula applies to both Memoization and Tabulation.

---

# 6. General Space Complexity

Dynamic Programming stores intermediate results.

Memory is typically used for:

- state tables;
- cached values;
- recursion stack (Memoization).

General auxiliary space:

```text
Number of Stored States
```

---

# 7. Memoization Complexity

Memoization computes only the states reached during recursion.

Conceptually:

```text
Recursive Call

↓

Compute Once

↓

Store

↓

Reuse
```

Time complexity:

```text
O(Number of Visited States)
```

Space complexity includes:

- cache;
- recursion stack.

---

# 8. Tabulation Complexity

Tabulation iteratively computes every state.

Conceptually:

```text
Initialize

↓

Fill Table

↓

Answer
```

Time complexity:

```text
O(Number of States)
```

Space complexity depends on the size of the Dynamic Programming table.

---

# 9. Memoization versus Tabulation

| Characteristic | Memoization | Tabulation |
|----------------|-------------|------------|
| Strategy | Top-Down | Bottom-Up |
| Execution | Recursive | Iterative |
| Computes | Only visited states | All table states |
| Recursion Stack | Yes | No |
| Stack Overflow Risk | Possible | None |

Both approaches generally have the same asymptotic complexity, although their practical performance may differ.

---

# 10. Fibonacci Complexity

Naive recursion:

```text
O(2ⁿ)
```

Memoization:

```text
O(n)
```

Tabulation:

```text
O(n)
```

Space:

```text
O(n)
```

Optimized Tabulation:

```text
O(1)
```

by storing only the two previous values.

---

# 11. Knapsack Complexity

State:

```text
(item,
capacity)
```

Number of states:

```text
N × W
```

Time complexity:

```text
O(N × W)
```

Space complexity:

```text
O(N × W)
```

Memory can often be reduced to:

```text
O(W)
```

using a one-dimensional table.

---

# 12. Longest Common Subsequence Complexity

State:

```text
(i,j)
```

Number of states:

```text
m × n
```

Time complexity:

```text
O(m × n)
```

Space complexity:

```text
O(m × n)
```

Some implementations optimize auxiliary space to:

```text
O(min(m,n))
```

when only the LCS length is required.

---

# 13. Complexity Comparison

| Problem | Time | Auxiliary Space |
|---------|------|----------------:|
| Fibonacci (Naive) | O(2ⁿ) | O(n) |
| Fibonacci (DP) | O(n) | O(n) |
| 0/1 Knapsack | O(N × W) | O(N × W) |
| Longest Common Subsequence | O(m × n) | O(m × n) |

Dynamic Programming often converts exponential algorithms into polynomial-time solutions.

---

# 14. State Space

The collection of all possible states is called the **state space**.

Conceptually:

```text
All Possible
States

↓

Dynamic
Programming
Table
```

A larger state space generally increases both execution time and memory usage.

---

# 15. Transition Efficiency

Efficient recurrence relations reduce the work performed at each state.

Conceptually:

```text
Simple Transition

↓

Fast State
Computation
```

Poorly designed transitions increase the total running time.

---

# 16. Memory Optimization

Many Dynamic Programming algorithms do not require the complete table.

Instead:

```text
Current State

↓

Previous States
Only
```

Discarding unnecessary states reduces auxiliary memory without changing the running time.

---

# 17. Scalability

As the number of states increases:

```text
More States

↓

More Memory

↓

Longer Execution
```

Scalability therefore depends primarily on the growth of the state space.

---

# 18. Engineering Trade-Offs

Dynamic Programming exchanges memory for speed.

Conceptually:

```text
More Memory

↓

Less Computation

↓

Faster Execution
```

This trade-off is one of the defining characteristics of the paradigm.

---

# 19. Java Perspective

Java implementations commonly use:

- one-dimensional arrays;
- two-dimensional arrays;
- multidimensional arrays;
- `HashMap`;
- nested loops;
- recursion (Memoization).

Performance depends on:

- efficient state representation;
- cache access;
- memory allocation;
- transition implementation.

---

# 20. Engineering Perspective

Professional software engineers analyze Dynamic Programming algorithms by asking:

```text
How many
states
exist?
```

```text
How expensive
is each
transition?
```

```text
Can memory
be reduced?
```

```text
Should
Memoization
or
Tabulation
be used?
```

These questions help engineers select the most efficient implementation for a given problem.

---

# 21. Relationship with the Next Document

This document summarized the computational complexity of the Dynamic Programming techniques and case studies explored throughout this module.

The next document, **Common Mistakes**, examines the conceptual and implementation errors that frequently occur when designing Dynamic Programming algorithms, including incorrect state definitions, invalid recurrence relations, inefficient memory usage, and improper transition design.

---

# 22. Key Takeaways

After completing this document, the learner should understand that:

- the complexity of a Dynamic Programming algorithm is determined by the **number of states** and the **cost of each state transition**;
- Memoization and Tabulation generally have the same asymptotic complexity but differ in execution strategy;
- Dynamic Programming frequently transforms exponential recursive algorithms into polynomial-time solutions;
- the size of the state space directly influences both execution time and memory usage;
- memory optimization techniques can significantly reduce auxiliary space while preserving correctness;
- Fibonacci, Knapsack, and Longest Common Subsequence illustrate different state representations and complexity characteristics;
- efficient state definitions and recurrence relations are essential for scalable Dynamic Programming solutions;
- professional software engineers evaluate Dynamic Programming algorithms by balancing execution time, memory consumption, implementation simplicity, and maintainability.