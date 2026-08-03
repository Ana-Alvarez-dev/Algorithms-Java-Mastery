# Recurrence Design

## Algorithms Java Mastery

A **recurrence relation** is the mathematical foundation of every Dynamic Programming algorithm. Once a problem has been decomposed into states, the recurrence relation specifies **how the solution of one state can be obtained from the solutions of previously solved states**.

The recurrence relation transforms an optimization problem into a sequence of state transitions. Instead of solving the original problem directly, Dynamic Programming repeatedly applies the recurrence until the desired state is computed.

Designing an appropriate recurrence relation is often the most intellectually demanding part of Dynamic Programming. It requires understanding the structure of the problem, identifying the decisions available at each state, and expressing those decisions as mathematical equations.

A well-designed recurrence relation leads naturally to both **Memoization (Top-Down)** and **Tabulation (Bottom-Up)** implementations.

---

# 1. Purpose

The purpose of this document is to:

- define recurrence relations;
- explain their role in Dynamic Programming;
- understand state transitions;
- learn how to design recurrence equations;
- prepare for classical Dynamic Programming case studies.

---

# 2. Definition

A **recurrence relation** is a mathematical equation that expresses the solution of one state in terms of previously solved states.

Conceptually:

```text
Current State

↓

Previous States

↓

Current Solution
```

The recurrence defines how information flows between states.

---

# 3. Why Recurrence Relations Are Necessary

After defining the states, Dynamic Programming must determine:

```text
How Do We
Compute
Each State?
```

The answer is provided by the recurrence relation.

Without a recurrence, the algorithm has no rule for computing new states.

---

# 4. Fundamental Idea

Every Dynamic Programming algorithm follows the same reasoning.

```text
Current Problem
        ↓
Smaller Problems
        ↓
Combine Results
        ↓
Current Solution
```

The recurrence formalizes this reasoning mathematically.

---

# 5. State Transition

A recurrence describes how one state transitions into another.

Conceptually:

```text
State(i)

↓

Transition Rule

↓

State(i+1)
```

or

```text
State(i,j)

↓

Transition Rule

↓

Next State
```

These transitions define the computation order.

---

# 6. General Structure

Most recurrence relations follow this pattern.

```text
Current State

=

Function(
Previous States
)
```

The function may represent:

- addition;
- minimum;
- maximum;
- comparison;
- combination of values.

---

# 7. Base Cases

Every recurrence requires one or more base cases.

Conceptually:

```text
Known State

↓

Known Value
```

Example:

```text
F(0)=0

F(1)=1
```

Base cases stop recursion and initialize Tabulation tables.

---

# 8. Example: Fibonacci

Recursive definition:

```text
F(n)

=

F(n−1)

+

F(n−2)
```

Base cases:

```text
F(0)=0

F(1)=1
```

Every new state depends on two previous states.

---

# 9. Example: 0/1 Knapsack

State:

```text
(item, capacity)
```

Decision:

```text
Take Item

or

Skip Item
```

Recurrence:

```text
Best Value

=

Maximum(
Take,
Skip
)
```

The recurrence compares both possible decisions.

---

# 10. Example: Longest Common Subsequence

State:

```text
(i,j)
```

Decision:

If characters match:

```text
Diagonal
+
1
```

Otherwise:

```text
Maximum(
Top,
Left
)
```

The recurrence chooses the better subproblem.

---

# 11. Decision Making

Most recurrence relations evaluate multiple possibilities.

Conceptually:

```text
Decision A

Decision B

Decision C
```

↓

```text
Choose
Best
```

Dynamic Programming systematically evaluates every alternative before selecting the optimal result.

---

# 12. Recursive Interpretation

The recurrence naturally defines recursion.

Conceptually:

```text
Current State

↓

Smaller State

↓

Smaller State

↓

Base Case
```

Memoization follows this recursive interpretation directly.

---

# 13. Iterative Interpretation

The same recurrence can also be evaluated iteratively.

Conceptually:

```text
Base Cases

↓

Fill Table

↓

Final State
```

Tabulation applies the recurrence in dependency order.

---

# 14. Dependency Graph

Every recurrence creates dependencies between states.

Example:

```text
State 5

↓

State 4

↓

State 3
```

A state cannot be computed until every required dependency has already been solved.

---

# 15. Designing a Recurrence

Professional software engineers typically follow these steps.

```text
Understand Problem
        ↓
Define State
        ↓
Identify Decisions
        ↓
Express Transition
        ↓
Define Base Cases
        ↓
Validate Formula
```

This systematic approach simplifies algorithm design.

---

# 16. Common Patterns

Many Dynamic Programming recurrences use similar operations.

Examples include:

```text
Minimum(...)
```

```text
Maximum(...)
```

```text
Addition
```

```text
Comparison
```

```text
Count
```

Recognizing these patterns helps identify Dynamic Programming problems.

---

# 17. Relationship with Complexity

The recurrence determines:

- how many states exist;
- how many transitions each state performs.

General complexity:

```text
Number of States

×

Transition Cost
```

Efficient recurrence design directly improves algorithm performance.

---

# 18. Java Perspective

Java implementations typically express recurrences using:

- recursive methods (Memoization);
- loops (Tabulation);
- arrays;
- multidimensional arrays;
- helper methods.

The recurrence itself remains mathematically identical regardless of the implementation strategy.

---

# 19. Engineering Perspective

Professional software engineers rarely begin coding immediately.

Instead, they first design the recurrence.

Typical questions include:

```text
What decisions
exist?
```

```text
What previous
states are needed?
```

```text
Can every state
be computed
from earlier ones?
```

Once the recurrence is correct, implementing Memoization or Tabulation becomes considerably simpler.

---

# 20. Relationship with the Next Document

This document introduced **Recurrence Design**, the mathematical process of connecting Dynamic Programming states.

The next document studies the **Fibonacci Case Study**, demonstrating how a simple recurrence can be transformed from an exponential recursive algorithm into efficient Memoization and Tabulation implementations.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- a recurrence relation defines how Dynamic Programming states are computed;
- every recurrence expresses the current state in terms of previously solved states;
- base cases provide the starting point for recursive and iterative solutions;
- recurrence relations describe state transitions;
- many optimization problems evaluate multiple decisions before selecting the best one;
- the same recurrence can be implemented using either Memoization or Tabulation;
- the complexity of a Dynamic Programming algorithm depends on the number of states and the transition cost;
- designing a correct recurrence relation is one of the most important and challenging aspects of Dynamic Programming.