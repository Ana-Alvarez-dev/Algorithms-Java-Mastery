# Fibonacci Case Study

## Algorithms Java Mastery

The **Fibonacci sequence** is the classical introductory example for **Dynamic Programming**. Although the mathematical definition is simple, the recursive implementation illustrates one of the most important problems in algorithm design: **repeated computation of identical subproblems**.

The naive recursive algorithm repeatedly computes the same Fibonacci numbers, producing an exponential number of recursive calls. Dynamic Programming eliminates this inefficiency by storing intermediate results and reusing them whenever the same subproblem appears again.

For this reason, the Fibonacci problem is not important because of the sequence itself, but because it clearly demonstrates the transformation from an inefficient recursive algorithm into an efficient Dynamic Programming solution using **Memoization** and **Tabulation**.

This case study serves as the foundation for understanding more advanced Dynamic Programming problems such as Knapsack, Longest Common Subsequence, Matrix Chain Multiplication, and Edit Distance.

---

# 1. Purpose

The purpose of this document is to:

- introduce Fibonacci as a Dynamic Programming problem;
- analyze naive recursion;
- identify overlapping subproblems;
- compare Memoization and Tabulation;
- evaluate computational complexity.

---

# 2. Problem Definition

The Fibonacci sequence is defined recursively.

Base cases:

```text
F(0) = 0

F(1) = 1
```

Recursive rule:

```text
F(n)

=

F(n−1)

+

F(n−2)
```

Each number is obtained by summing the two previous numbers.

---

# 3. First Values

The beginning of the sequence is:

```text
n : 0 1 2 3 4 5 6 7

F : 0 1 1 2 3 5 8 13
```

These values satisfy the recursive definition.

---

# 4. Naive Recursive Solution

A direct recursive implementation follows the mathematical definition.

Conceptually:

```text
F(n)

↓

F(n−1)

+

F(n−2)
```

This implementation is elegant but highly inefficient.

---

# 5. Recursion Tree

Consider:

```text
F(5)
```

The recursion expands as:

```text
              F(5)
             /    \
         F(4)     F(3)
        /   \     /   \
     F(3) F(2) F(2) F(1)
     / \
  F(2) F(1)
```

Notice that:

```text
F(3)
```

and

```text
F(2)
```

are computed multiple times.

---

# 6. Overlapping Subproblems

The recursion tree reveals repeated work.

Example:

```text
F(2)

↓

Computed

↓

Computed Again

↓

Computed Again
```

This is the defining characteristic of **Overlapping Subproblems**.

---

# 7. Why Recursion Is Inefficient

Without storing intermediate results:

```text
Same Problem

↓

Solved Repeatedly
```

The number of recursive calls grows exponentially.

Most of the execution time is spent recomputing previously solved values.

---

# 8. Memoization Solution

Memoization stores every computed Fibonacci number.

Workflow:

```text
Recursive Call
        ↓
Already Stored?
      /        \
    Yes         No
    ↓            ↓
Return       Compute
Stored Value     ↓
             Store Result
```

Every Fibonacci number is computed exactly once.

---

# 9. Memoization Example

Suppose:

```text
F(3)
```

has already been computed.

Later:

```text
Need F(3)

↓

Return Cached Value
```

No additional recursive calls are required.

---

# 10. Tabulation Solution

Tabulation computes Fibonacci numbers iteratively.

Conceptually:

```text
F(0)

↓

F(1)

↓

F(2)

↓

F(3)

↓

...

↓

F(n)
```

Every value depends only on the two previous values.

---

# 11. Dynamic Programming Table

A typical DP table appears as:

| n | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|
| F(n) | 0 | 1 | 1 | 2 | 3 | 5 | 8 |

Each table entry is computed exactly once.

---

# 12. State Definition

The state is simply:

```text
State(n)
```

Each value of **n** represents one unique subproblem.

Stored result:

```text
dp[n]
```

---

# 13. Recurrence Relation

The recurrence is:

```text
F(n)

=

F(n−1)

+

F(n−2)
```

This recurrence drives both Memoization and Tabulation.

Only the implementation strategy changes.

---

# 14. Time Complexity Comparison

| Approach | Time Complexity |
|-----------|----------------:|
| Naive Recursion | O(2ⁿ) |
| Memoization | O(n) |
| Tabulation | O(n) |

Dynamic Programming transforms an exponential algorithm into a linear one.

---

# 15. Space Complexity Comparison

| Approach | Auxiliary Space |
|-----------|----------------:|
| Naive Recursion | O(n) (call stack) |
| Memoization | O(n) |
| Tabulation | O(n) |

Both Dynamic Programming approaches require additional memory for stored results.

---

# 16. Space Optimization

The Fibonacci recurrence depends only on:

```text
F(n−1)

and

F(n−2)
```

Therefore, the complete table is unnecessary.

Only two previous values must be stored.

Optimized auxiliary space:

```text
O(1)
```

This is a classical Dynamic Programming optimization.

---

# 17. Memoization versus Tabulation

| Memoization | Tabulation |
|--------------|------------|
| Top-Down | Bottom-Up |
| Recursive | Iterative |
| Computes only required states | Computes every intermediate state |
| Uses recursion stack | No recursion stack |

Both produce the same final result.

---

# 18. Java Perspective

Java implementations commonly use:

Memoization:

- recursion;
- `int[]`;
- `HashMap`.

Tabulation:

- arrays;
- loops;
- iterative state transitions.

For optimized implementations, only two integer variables are required instead of an entire array.

---

# 19. Engineering Perspective

Although Fibonacci itself is rarely used in production systems, it provides an ideal educational example because it demonstrates every fundamental concept of Dynamic Programming.

```text
Recursive Definition
        ↓
Repeated Computation
        ↓
Overlapping Subproblems
        ↓
Memoization
        ↓
Tabulation
        ↓
Linear-Time Algorithm
```

The same reasoning applies to many real-world optimization problems encountered in software engineering.

---

# 20. Relationship with the Next Document

This document demonstrated how Dynamic Programming transforms the recursive Fibonacci algorithm into an efficient solution using Memoization and Tabulation.

The next document studies the **0/1 Knapsack Problem**, one of the most important optimization problems in Computer Science, introducing multidimensional states, decision-based recurrence relations, and value optimization.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- the Fibonacci sequence is the classical introductory example of Dynamic Programming;
- naive recursion repeatedly solves identical subproblems, resulting in exponential time complexity;
- Memoization eliminates redundant recursive calls by caching computed values;
- Tabulation computes Fibonacci numbers iteratively in dependency order;
- both Dynamic Programming approaches reduce the running time from **O(2ⁿ)** to **O(n)**;
- the Fibonacci problem requires only a one-dimensional state representation;
- the recurrence relation is identical for Memoization and Tabulation—the implementation strategy differs;
- Fibonacci illustrates the core ideas of Dynamic Programming and provides the conceptual foundation for solving more advanced optimization problems.