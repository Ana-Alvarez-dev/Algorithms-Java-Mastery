# Memoization

## Algorithms Java Mastery

**Memoization** is one of the two principal implementation techniques of **Dynamic Programming**. It follows a **Top-Down** approach, combining recursion with caching to eliminate redundant computation.

The idea behind Memoization is straightforward. Whenever a recursive algorithm computes the solution to a subproblem for the first time, that result is stored in a cache. If the same subproblem appears again, the algorithm immediately returns the stored value instead of performing another recursive computation.

Memoization preserves the simplicity of recursive algorithms while dramatically improving their efficiency. Instead of repeatedly exploring identical recursive branches, each distinct subproblem is solved exactly once.

Because many optimization problems naturally admit recursive formulations, Memoization is often the first Dynamic Programming technique introduced. It provides an intuitive transition from naive recursion to efficient algorithm design.

---

# 1. Purpose

The purpose of this document is to:

- define Memoization;
- explain the Top-Down Dynamic Programming approach;
- understand recursive caching;
- analyze the Memoization workflow;
- evaluate computational complexity.

---

# 2. Definition

**Memoization** is a Dynamic Programming technique that stores the result of each computed subproblem.

Conceptually:

```text
Recursive Call
        ↓
Already Computed?
      /        \
    Yes         No
    ↓            ↓
Return       Compute
Stored Value     ↓
             Store Result
```

Each distinct subproblem is computed only once.

---

# 3. Why Memoization Is Needed

Consider a recursive algorithm.

```text
Problem
        ↓
Subproblem A
        ↓
Subproblem B
        ↓
Subproblem A
```

Without Memoization:

```text
Solve A

↓

Solve A Again
```

The second computation is unnecessary.

---

# 4. Basic Idea

Memoization follows one simple rule:

> **If a subproblem has already been solved, reuse its stored solution instead of solving it again.**

This avoids redundant recursive work.

---

# 5. Top-Down Strategy

Memoization uses a **Top-Down** approach.

Conceptually:

```text
Original Problem
        ↓
Recursive Calls
        ↓
Smaller Problems
        ↓
Base Cases
```

Only the subproblems required by the recursion are computed.

---

# 6. Memoization Workflow

The algorithm follows the same sequence for every recursive call.

```text
Recursive Call
        ↓
Lookup Cache
        ↓
Stored?
      /      \
    Yes       No
    ↓          ↓
Return     Compute
Stored       ↓
Result    Store Result
```

The cache prevents duplicate computations.

---

# 7. Cache

The cache stores previously computed solutions.

Conceptually:

```text
Subproblem

↓

Computed Result

↓

Cache
```

Future recursive calls retrieve the stored value immediately.

---

# 8. Recursive Example

Consider the Fibonacci recurrence.

```text
F(n)

↓

F(n−1)

+

F(n−2)
```

Without Memoization:

```text
F(3)

↓

Computed

↓

Computed Again
```

With Memoization:

```text
F(3)

↓

Computed Once

↓

Stored

↓

Reused
```

---

# 9. Execution Flow

Memoization combines recursion and caching.

```text
Recursive Call
        ↓
Check Cache
        ↓
Need Computation?
      /         \
    No           Yes
    ↓             ↓
Return       Recursive Calls
Cached           ↓
Result      Store Answer
```

This process repeats until the original problem is solved.

---

# 10. Lazy Evaluation

Memoization computes only the states that are actually needed.

Conceptually:

```text
Required State

↓

Compute

↓

Store
```

Unused states are never evaluated.

This property distinguishes Memoization from Tabulation.

---

# 11. Base Cases

Every Memoization algorithm requires base cases.

Example:

```text
F(0)=0

F(1)=1
```

When recursion reaches a base case:

```text
Return Value
```

No additional recursive calls occur.

---

# 12. Time Complexity

Suppose there are:

```text
n
```

distinct subproblems.

Each one is computed only once.

General complexity:

```text
O(n)
```

plus the cost of each state transition.

This is often a dramatic improvement over exponential recursion.

---

# 13. Space Complexity

Memoization stores:

- cached results;
- recursion stack.

General space complexity:

```text
O(n)
```

although it depends on the number of stored states.

---

# 14. Advantages

Memoization provides several important benefits.

It offers:

- simple recursive implementation;
- elimination of repeated computation;
- efficient execution;
- automatic computation of only necessary states;
- clear correspondence with mathematical recurrences.

---

# 15. Limitations

Memoization also has limitations.

Potential drawbacks include:

- recursive call overhead;
- additional memory usage;
- possible stack overflow for deep recursion.

Very deep recursive problems may require an iterative solution.

---

# 16. Memoization versus Tabulation

| Memoization | Tabulation |
|--------------|------------|
| Top-Down | Bottom-Up |
| Recursive | Iterative |
| Computes only required states | Computes every state |
| Uses recursion stack | No recursion required |

Both techniques implement Dynamic Programming but follow different execution strategies.

---

# 17. Typical Applications

Memoization is commonly applied to:

- Fibonacci Numbers;
- Knapsack Problem;
- Longest Common Subsequence;
- Coin Change;
- Matrix Chain Multiplication;
- Edit Distance;
- Rod Cutting.

These problems naturally lend themselves to recursive solutions.

---

# 18. Java Perspective

Java implementations commonly use:

- recursive methods;
- arrays;
- multidimensional arrays;
- `HashMap`;
- `Map<K,V>`.

Typical workflow:

```text
Recursive Method

↓

Lookup Array
or Map

↓

Reuse or Compute
```

Arrays are efficient for fixed state spaces, while maps are useful for sparse or dynamic states.

---

# 19. Engineering Perspective

Professional software engineers often begin with a recursive solution before introducing Memoization.

Typical design questions include:

```text
Is this
subproblem
repeated?
```

```text
Can its result
be stored?
```

```text
Is recursion
still practical?
```

Memoization provides an elegant bridge between mathematical recursion and efficient software implementation.

---

# 20. Relationship with the Next Document

This document introduced **Memoization**, the Top-Down implementation strategy for Dynamic Programming.

The next document studies **Tabulation**, the Bottom-Up approach, where solutions are computed iteratively rather than recursively by filling a table in dependency order.

Together, Memoization and Tabulation form the two principal implementation techniques of Dynamic Programming.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- Memoization is the Top-Down implementation technique of Dynamic Programming;
- recursive results are stored and reused whenever identical subproblems appear;
- each distinct subproblem is computed only once;
- Memoization combines recursion with caching;
- only the states required by the recursion are evaluated;
- Memoization often reduces exponential recursive algorithms to polynomial-time solutions;
- arrays and hash maps are the most common caching structures in Java;
- Memoization offers an intuitive transition from naive recursion to efficient Dynamic Programming.