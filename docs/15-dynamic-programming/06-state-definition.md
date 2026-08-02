# State Definition

## Algorithms Java Mastery

One of the most important—and often the most difficult—steps in designing a **Dynamic Programming** algorithm is defining the **state**. Regardless of whether the solution is implemented using **Memoization** or **Tabulation**, the algorithm can only be successful if every subproblem is represented correctly.

A **state** describes the minimum amount of information required to uniquely identify a subproblem. Once a state is defined, its solution can be stored and reused whenever the same state appears again.

Poor state definitions often produce inefficient algorithms, excessive memory consumption, or even incorrect solutions. Conversely, a well-designed state simplifies the recurrence relation, reduces the number of stored values, and improves both readability and performance.

For this reason, experienced software engineers frequently spend more time designing the state than writing the implementation itself. In Dynamic Programming, the quality of the state definition largely determines the quality of the entire algorithm.

---

# 1. Purpose

The purpose of this document is to:

- define the concept of a state;
- explain why state definition is fundamental to Dynamic Programming;
- understand how states represent subproblems;
- analyze different types of state representations;
- prepare for recurrence relation design.

---

# 2. What Is a State?

A **state** is a complete description of a subproblem.

Conceptually:

```text
Problem

↓

Subproblem

↓

State
```

Each state uniquely identifies one recursive computation.

---

# 3. Why States Are Necessary

Dynamic Programming stores solutions to subproblems.

Without a state:

```text
Subproblem

↓

Cannot Be Stored
```

With a state:

```text
Subproblem

↓

State

↓

Stored Result
```

States allow previously computed solutions to be reused.

---

# 4. Fundamental Idea

A good state answers the question:

> **"What information is required to completely describe the current subproblem?"**

The state should contain **only** the information needed to solve that subproblem.

---

# 5. State Representation

A state is usually represented by one or more variables.

Examples:

```text
State(i)
```

```text
State(i,j)
```

```text
State(i,j,k)
```

The number of variables depends on the complexity of the problem.

---

# 6. Single-Dimensional States

Some problems require only one variable.

Example:

```text
Fibonacci(n)
```

State:

```text
n
```

Each value of **n** represents one unique subproblem.

---

# 7. Two-Dimensional States

Many optimization problems require two variables.

Example:

```text
State(i,j)
```

Possible interpretation:

- current position;
- remaining capacity;
- substring boundaries.

Each pair uniquely identifies a subproblem.

---

# 8. Multi-Dimensional States

More complex problems may require several variables.

Example:

```text
State(i,j,k)
```

Additional variables increase:

- expressive power;
- memory usage;
- implementation complexity.

The objective is to keep the state as small as possible.

---

# 9. Example: Fibonacci

Problem:

```text
F(n)
```

State:

```text
n
```

Stored result:

```text
dp[n]
```

Every value of **n** corresponds to exactly one stored solution.

---

# 10. Example: Knapsack Problem

The classical 0/1 Knapsack Problem is commonly represented as:

```text
State(item, capacity)
```

Meaning:

- current item;
- remaining capacity.

Stored value:

```text
dp[item][capacity]
```

Each table entry represents the optimal solution for that specific subproblem.

---

# 11. Example: Longest Common Subsequence

State:

```text
State(i,j)
```

Meaning:

- current position in the first sequence;
- current position in the second sequence.

Stored value:

```text
dp[i][j]
```

Each state represents the optimal subsequence for two prefixes.

---

# 12. Choosing State Variables

When defining a state, ask:

```text
What information
changes
between
subproblems?
```

Only changing information should become part of the state.

---

# 13. Minimal State Principle

A state should contain:

- enough information to solve the subproblem;
- no unnecessary information.

Incorrect:

```text
Too Many Variables
```

Correct:

```text
Only Necessary
Variables
```

Smaller states generally produce faster algorithms.

---

# 14. State Transition

States are connected through **transitions**.

Conceptually:

```text
Current State

↓

Next State
```

The recurrence relation describes these transitions.

---

# 15. State Space

The collection of every possible state forms the **state space**.

Conceptually:

```text
All States

↓

Dynamic
Programming
Table
```

The size of the state space strongly influences both execution time and memory usage.

---

# 16. Relationship with Memoization

Memoization stores results using the state as the cache key.

Conceptually:

```text
State

↓

Lookup

↓

Stored Result
```

If the state has already been solved, its value is immediately returned.

---

# 17. Relationship with Tabulation

Tabulation fills a table whose entries correspond to states.

Conceptually:

```text
State

↓

Table Cell

↓

Computed Value
```

Each table position stores the solution for one unique state.

---

# 18. Common Design Guidelines

Professional software engineers follow several principles when defining states.

- Keep the state minimal.
- Ensure every state is unique.
- Include only changing information.
- Avoid redundant variables.
- Make state transitions simple.

These principles improve both correctness and efficiency.

---

# 19. Java Perspective

Java implementations commonly represent states using:

- one-dimensional arrays;
- two-dimensional arrays;
- multidimensional arrays;
- `HashMap`;
- custom objects (for complex states).

Examples:

```text
int[] dp
```

```text
int[][] dp
```

```text
Map<State, Integer>
```

The chosen structure depends on the nature of the state.

---

# 20. Engineering Perspective

Professional software engineers often say:

> **The hardest part of Dynamic Programming is not writing the code—it is defining the state correctly.**

Typical design questions include:

```text
What exactly
defines a
subproblem?
```

```text
Can two different
subproblems
share the same
state?
```

```text
Is every state
uniquely identified?
```

A clear state definition naturally leads to an efficient recurrence relation and a correct implementation.

---

# 21. Relationship with the Next Document

This document introduced **State Definition**, the process of modeling subproblems for Dynamic Programming.

The next document studies **Recurrence Design**, explaining how states are mathematically connected through transition equations and how these recurrence relations drive both Memoization and Tabulation algorithms.

---

# 22. Key Takeaways

After completing this document, the learner should understand that:

- a state uniquely represents a Dynamic Programming subproblem;
- every stored result corresponds to exactly one state;
- state definition is the foundation of every Dynamic Programming algorithm;
- states should contain only the information necessary to describe a subproblem;
- one-dimensional, two-dimensional, and multidimensional states are common representations;
- the size of the state space directly influences time and space complexity;
- Memoization uses states as cache keys, while Tabulation maps states to table entries;
- designing a minimal, unique, and expressive state is one of the most important skills in Dynamic Programming.