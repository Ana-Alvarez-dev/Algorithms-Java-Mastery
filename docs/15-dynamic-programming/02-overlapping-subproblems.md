# Overlapping Subproblems

## Algorithms Java Mastery

**Overlapping Subproblems** is one of the two fundamental mathematical properties required for applying the **Dynamic Programming** paradigm. It describes situations in which a recursive algorithm repeatedly solves the **same subproblems** during its execution.

Many recursive algorithms naturally divide a problem into smaller parts. However, unlike **Divide and Conquer**, where subproblems are generally independent, some recursive algorithms generate identical subproblems multiple times. These repeated computations are redundant because the result of a subproblem never changes.

Dynamic Programming eliminates this inefficiency by storing the solution the first time a subproblem is solved. Whenever the same subproblem appears again, the previously computed result is reused instead of performing another recursive computation.

Understanding this property is essential because it explains **why Dynamic Programming can dramatically improve the performance of recursive algorithms**.

---

# 1. Purpose

The purpose of this document is to:

- define Overlapping Subproblems;
- explain why repeated computation occurs;
- distinguish Dynamic Programming from Divide and Conquer;
- understand recursion trees;
- motivate Memoization and Tabulation.

---

# 2. Definition

A problem exhibits **Overlapping Subproblems** when identical subproblems are solved multiple times during recursive execution.

Conceptually:

```text
Problem
        ↓
Subproblem A
        ↓
Subproblem B
        ↓
Subproblem A
```

The second computation of **Subproblem A** is unnecessary because its result has already been determined.

---

# 3. Why Repeated Computation Occurs

Recursive algorithms often expand into multiple recursive branches.

Conceptually:

```text
Problem
      /     \
     /       \
Sub A       Sub B
     \       /
      \     /
      Sub C
```

Notice that:

```text
Sub C
```

is reached from multiple paths.

Without Dynamic Programming, **Subproblem C** is solved repeatedly.

---

# 4. Redundant Computation

Repeated evaluation of identical subproblems is called **redundant computation**.

Without caching:

```text
Solve A

↓

Solve A Again

↓

Solve A Again
```

The algorithm wastes execution time performing identical work.

---

# 5. Eliminating Redundancy

Dynamic Programming replaces repeated computation with stored results.

Conceptually:

```text
Solve Once
        ↓
Store Result
        ↓
Need Again?
      /       \
    No         Yes
    ↓           ↓
 Continue    Retrieve
```

Each distinct subproblem is computed only once.

---

# 6. Recursive Trees

Repeated computation becomes easier to understand using a recursion tree.

Example:

```text
             F(5)
            /    \
         F(4)   F(3)
         /  \    / \
      F(3) F(2) F(2) F(1)
```

Notice:

```text
F(3)
```

appears multiple times.

Likewise:

```text
F(2)
```

is also recomputed.

---

# 7. Fibonacci Example

The recursive Fibonacci algorithm illustrates overlapping subproblems perfectly.

Recursive definition:

```text
F(n)

↓

F(n−1)

+

F(n−2)
```

Each recursive call generates additional repeated calls.

Example:

```text
F(5)

↓

F(4)

↓

F(3)

↓

F(2)
```

Later:

```text
F(3)

↓

F(2)
```

The same value is computed repeatedly.

---

# 8. Why Dynamic Programming Helps

Instead of computing:

```text
F(2)

↓

Again

↓

Again
```

Dynamic Programming performs:

```text
Compute F(2)

↓

Store

↓

Reuse
```

This eliminates unnecessary recursive calls.

---

# 9. Memoization

Memoization stores recursive results as they are computed.

Conceptually:

```text
Recursive Call
        ↓
Already Stored?
      /       \
    Yes        No
    ↓           ↓
 Return     Compute
 Result        ↓
           Store
```

This prevents duplicate recursive work.

---

# 10. Tabulation

Tabulation eliminates repeated computation by solving subproblems iteratively.

Conceptually:

```text
Smallest States
        ↓
Next States
        ↓
Larger States
        ↓
Final Answer
```

Each state is computed exactly once.

---

# 11. Relationship with Divide and Conquer

Divide and Conquer:

```text
Problem
      /   \
Independent
Subproblems
```

Dynamic Programming:

```text
Problem
      /   \
Shared
Subproblems
```

The presence of repeated states distinguishes Dynamic Programming from Divide and Conquer.

---

# 12. Relationship with Optimal Substructure

Overlapping Subproblems alone are not sufficient.

Dynamic Programming also requires:

```text
Optimal
Substructure
```

Both properties together justify storing intermediate results.

---

# 13. Identifying Overlapping Subproblems

When analyzing a recursive algorithm, ask:

```text
Does the same
recursive state
appear again?
```

If the answer is yes:

```text
Dynamic Programming
May Apply
```

---

# 14. Typical Problems

Many classical Dynamic Programming problems exhibit overlapping subproblems.

Examples include:

- Fibonacci Numbers;
- Knapsack Problem;
- Longest Common Subsequence;
- Matrix Chain Multiplication;
- Coin Change;
- Rod Cutting;
- Edit Distance.

Each repeatedly computes identical recursive states.

---

# 15. Complexity Impact

Without Dynamic Programming:

```text
Repeated Calls

↓

Exponential Time
```

With Dynamic Programming:

```text
Compute Once

↓

Polynomial Time
```

Many exponential recursive algorithms become practical after eliminating redundant computation.

---

# 16. Memory Trade-Off

Dynamic Programming exchanges memory for execution speed.

Conceptually:

```text
Extra Memory

↓

Stored Results

↓

Faster Execution
```

This is one of the most important engineering trade-offs in algorithm design.

---

# 17. Java Perspective

Java implementations commonly store intermediate results using:

- arrays;
- multidimensional arrays;
- `HashMap`;
- `Map<Integer, Integer>`;
- lookup tables.

The choice depends on how the problem state is represented.

---

# 18. Engineering Perspective

Professional software engineers recognize overlapping subproblems during algorithm design.

Typical questions include:

```text
Is this state
being solved
multiple times?
```

```text
Can the result
be reused?
```

```text
Is caching
worth the
additional memory?
```

Answering these questions determines whether Dynamic Programming is an appropriate solution.

---

# 19. Relationship with the Next Document

This document introduced **Overlapping Subproblems**, the first mathematical property required for Dynamic Programming.

The next document studies **Optimal Substructure**, explaining why optimal solutions can be constructed from optimal solutions to smaller subproblems and why this property is essential for Dynamic Programming algorithms.

---

# 20. Key Takeaways

After completing this document, the learner should understand that:

- Overlapping Subproblems occur when identical recursive states are solved multiple times;
- repeated computation is the primary source of inefficiency in many recursive algorithms;
- Dynamic Programming eliminates redundant computation by storing intermediate results;
- Memoization and Tabulation both exploit the presence of overlapping subproblems;
- recursion trees provide a visual representation of repeated recursive states;
- Divide and Conquer generally solves independent subproblems, whereas Dynamic Programming solves shared subproblems;
- additional memory is exchanged for significant improvements in execution time;
- recognizing overlapping subproblems is the first step in identifying whether Dynamic Programming can be applied.