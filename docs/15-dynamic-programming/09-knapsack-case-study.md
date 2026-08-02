# 0/1 Knapsack Case Study

## Algorithms Java Mastery

The **0/1 Knapsack Problem** is one of the most important optimization problems in Computer Science and one of the classical applications of **Dynamic Programming**.

Unlike the Fibonacci problem, which primarily demonstrates the elimination of repeated computation, the Knapsack Problem introduces the central idea of **decision-based optimization**. At each step, the algorithm must decide whether to **include** or **exclude** an item while respecting a limited capacity.

The objective is not to enumerate every possible solution, but to determine the subset of items that produces the **maximum total value** without exceeding the capacity of the knapsack.

Although the problem can be solved using exhaustive search, the number of possible subsets grows exponentially. Dynamic Programming exploits the problem's **Optimal Substructure** and **Overlapping Subproblems** to transform this exponential search into a polynomial-time algorithm.

The Knapsack Problem serves as one of the most influential examples of state modeling, recurrence design, and multidimensional Dynamic Programming.

---

# 1. Purpose

The purpose of this document is to:

- introduce the 0/1 Knapsack Problem;
- understand optimization through Dynamic Programming;
- define states and recurrence relations;
- analyze Memoization and Tabulation;
- evaluate computational complexity.

---

# 2. Problem Statement

Given:

- **N** items;
- each item has:
    - a weight;
    - a value;
- a knapsack with limited capacity.

Goal:

```text
Select Items

↓

Maximum Total Value

↓

Capacity Not Exceeded
```

Every item may be selected:

```text
0 Times

or

1 Time
```

Hence the name **0/1 Knapsack**.

---

# 3. Input Representation

Each item is represented as:

```text
(weight, value)
```

Example:

| Item | Weight | Value |
|------|-------:|------:|
| A | 2 | 3 |
| B | 3 | 4 |
| C | 4 | 5 |
| D | 5 | 8 |

Knapsack capacity:

```text
5
```

---

# 4. Why Brute Force Is Inefficient

For every item, there are two possibilities.

```text
Take

or

Skip
```

Therefore:

```text
N Items

↓

2ⁿ
Possible
Subsets
```

As the number of items increases, exhaustive search quickly becomes impractical.

---

# 5. Why Dynamic Programming Works

The problem satisfies both required properties.

```text
Overlapping
Subproblems
        +
Optimal
Substructure
        ↓
Dynamic
Programming
```

Repeated subproblems are solved only once and reused.

---

# 6. State Definition

A classical state is:

```text
State(item, capacity)
```

Meaning:

- current item being considered;
- remaining knapsack capacity.

Each state represents one unique optimization subproblem.

---

# 7. Decision Process

At every state, the algorithm evaluates two possibilities.

```text
Current Item

↓

Take

Skip
```

The better of the two decisions becomes the optimal value for that state.

---

# 8. Recurrence Relation

If the item is too heavy:

```text
Skip Item
```

Otherwise:

```text
Maximum(

Take Item,

Skip Item

)
```

The recurrence compares both alternatives and stores the larger value.

---

# 9. State Transition

Conceptually:

```text
(item, capacity)

↓

(item+1, capacity)
```

or

```text
(item+1,
capacity-weight)
```

depending on the chosen decision.

---

# 10. Memoization

Memoization computes states recursively.

Workflow:

```text
State

↓

Already Stored?
      /       \
    Yes        No
    ↓           ↓
Return      Compute
Result         ↓
          Store Result
```

Each state is computed exactly once.

---

# 11. Tabulation

Tabulation constructs the solution iteratively.

Conceptually:

```text
First Item

↓

Second Item

↓

Third Item

↓

...

↓

Optimal Value
```

The Dynamic Programming table is filled row by row.

---

# 12. Dynamic Programming Table

The table typically represents:

Rows:

```text
Items
```

Columns:

```text
Capacity
```

Each cell stores:

```text
Maximum Value
```

for that specific state.

---

# 13. Example

Suppose:

Capacity:

```text
5
```

Items:

| Item | Weight | Value |
|------|-------:|------:|
| A | 2 | 3 |
| B | 3 | 4 |

Possible decisions:

```text
Take A

↓

Remaining Capacity
```

or

```text
Skip A
```

Dynamic Programming evaluates both possibilities before selecting the better one.

---

# 14. Overlapping Subproblems

Different recursive paths often reach identical states.

Example:

```text
(item 3,
capacity 4)
```

may be reached through multiple decision sequences.

Memoization stores its solution after the first computation.

---

# 15. Optimal Substructure

Suppose the optimal decision is:

```text
Take Item
```

The remaining problem becomes:

```text
Smaller Capacity

↓

Remaining Items
```

If this smaller problem is solved optimally, its solution contributes directly to the global optimum.

---

# 16. Time Complexity

Let:

```text
N
```

be the number of items.

Let:

```text
W
```

be the knapsack capacity.

Time complexity:

```text
O(N × W)
```

Each state is computed exactly once.

---

# 17. Space Complexity

The DP table stores:

```text
N × W
```

states.

Auxiliary space:

```text
O(N × W)
```

Some implementations optimize memory usage to:

```text
O(W)
```

by storing only the previous row.

---

# 18. Memoization versus Tabulation

| Memoization | Tabulation |
|--------------|------------|
| Top-Down | Bottom-Up |
| Recursive | Iterative |
| Computes only visited states | Computes every table entry |
| Uses recursion stack | No recursion stack |

Both implementations compute the same optimal value.

---

# 19. Practical Applications

The Knapsack Problem appears in many real-world optimization problems.

Examples include:

- cargo loading;
- resource allocation;
- investment planning;
- budget optimization;
- cloud resource scheduling;
- portfolio selection;
- memory allocation.

Many engineering optimization problems share the same mathematical structure.

---

# 20. Java Perspective

Java implementations commonly use:

- two-dimensional arrays;
- recursion;
- nested loops;
- `int[][] dp`.

Typical table:

```text
dp[item][capacity]
```

Each cell stores the optimal value for one state.

---

# 21. Engineering Perspective

The Knapsack Problem introduces nearly every major concept of Dynamic Programming.

```text
Optimization Problem
        ↓
State Definition
        ↓
Decision Making
        ↓
Recurrence Relation
        ↓
Memoization
or
Tabulation
        ↓
Optimal Solution
```

For this reason, it is considered one of the most important educational examples in algorithm design and frequently appears in technical interviews and university courses.

---

# 22. Relationship with the Next Document

This document studied the **0/1 Knapsack Problem**, demonstrating how Dynamic Programming solves optimization problems through multidimensional states and decision-based recurrence relations.

The next document introduces the **Longest Common Subsequence (LCS)** problem, illustrating how Dynamic Programming can also solve sequence comparison problems by combining state transitions, recurrence relations, and two-dimensional tables.

---

# 23. Key Takeaways

After completing this document, the learner should understand that:

- the 0/1 Knapsack Problem is a classical Dynamic Programming optimization problem;
- each item may be either selected or skipped;
- the state is commonly defined as **(item, capacity)**;
- every state evaluates two decisions: take or skip the current item;
- the recurrence relation selects the maximum value between the available alternatives;
- the algorithm satisfies both **Overlapping Subproblems** and **Optimal Substructure**;
- Dynamic Programming reduces the complexity from exponential search to **O(N × W)**;
- the Knapsack Problem demonstrates the importance of state modeling, recurrence design, and multidimensional Dynamic Programming in modern software engineering.