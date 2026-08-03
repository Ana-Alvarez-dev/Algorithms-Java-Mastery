# Recurrence Relations

## Algorithms Java Mastery

Recursive algorithms repeatedly solve smaller instances of the same problem. Because of this recursive execution, their running time cannot usually be described by a simple mathematical expression.

Instead, algorithm designers use **recurrence relations** to model how the running time of a recursive algorithm depends on the running time of its smaller recursive calls.

A recurrence relation expresses the cost of solving a problem of size **n** in terms of the cost of solving one or more smaller subproblems, together with the additional work performed during the current recursive call.

Recurrence relations are one of the most important mathematical tools in algorithm analysis. They provide the foundation for the **Master Theorem**, recursion trees, and the complexity analysis of Divide and Conquer algorithms.

Understanding recurrence relations allows software engineers to predict algorithmic performance before implementation and to compare alternative recursive strategies objectively.

---

# 1. Purpose

The purpose of this document is to:

- define recurrence relations;
- explain why they are necessary for recursive algorithms;
- understand the structure of recursive cost equations;
- analyze common recurrence patterns;
- prepare the learner for the Master Theorem.

---

# 2. Why Recurrence Relations Are Needed

Iterative algorithms are often analyzed by counting operations performed inside loops.

Recursive algorithms are different.

Each recursive call generates additional work that must also be analyzed.

Conceptually:

```text
Current Problem

↓

Recursive Calls

↓

Additional Work
```

A recurrence relation captures this recursive behavior mathematically.

---

# 3. Definition

A **recurrence relation** is an equation that defines the running time of a recursive algorithm in terms of the running time of smaller instances of the same problem.

General idea:

```text
Running Time

↓

Smaller Running Times

+

Current Work
```

Instead of giving the final complexity directly, a recurrence describes how the algorithm grows recursively.

---

# 4. General Form

Most Divide and Conquer algorithms can be expressed using the following recurrence.

```text
T(n)

=

aT(n / b)

+

f(n)
```

where:

- **T(n)** represents the running time for an input of size **n**;
- **a** is the number of recursive subproblems;
- **b** is the factor by which the input size is reduced;
- **f(n)** is the work performed outside the recursive calls.

This form will later be analyzed using the Master Theorem.

---

# 5. Components of a Recurrence

Every recurrence contains three essential components.

## Recursive Calls

The number of smaller problems generated.

---

## Problem Reduction

How much smaller each recursive problem becomes.

---

## Non-Recursive Work

The work performed during the current invocation.

Conceptually:

```text
Divide

+

Recursive Calls

+

Combine
```

Together these determine the overall running time.

---

# 6. Base Case

Every recurrence requires a stopping condition.

Example:

```text
T(1)

=

O(1)
```

A problem of size one is solved directly without further recursion.

Without a base case, the recurrence cannot be evaluated.

---

# 7. Linear Recurrence

Some recursive algorithms generate only one recursive call.

Example:

```text
T(n)

=

T(n − 1)

+

O(1)
```

Each recursive call performs constant additional work.

Typical examples include:

- factorial;
- recursive linear search;
- recursive list traversal.

Overall complexity:

```text
O(n)
```

---

# 8. Logarithmic Recurrence

Some algorithms reduce the problem by half.

Example:

```text
T(n)

=

T(n / 2)

+

O(1)
```

Each recursive call solves only half of the remaining problem.

Typical example:

```text
Binary Search
```

Overall complexity:

```text
O(log n)
```

---

# 9. Divide and Conquer Recurrence

Many Divide and Conquer algorithms create multiple recursive calls.

Example:

```text
T(n)

=

2T(n / 2)

+

O(n)
```

Meaning:

- two recursive subproblems;
- each half the original size;
- linear work to combine solutions.

Typical example:

```text
Merge Sort
```

---

# 10. Recursive Growth

As recursion progresses, the recurrence repeatedly expands.

Example:

```text
T(n)

↓

2T(n / 2)

↓

4T(n / 4)

↓

8T(n / 8)

↓

...
```

Eventually the recursive calls reach the base case.

This expansion illustrates how recursive execution unfolds.

---

# 11. Recursion Trees

A recurrence can be visualized using a recursion tree.

Example:

```text
Problem

↓

Half

Half

↓

Quarter

Quarter

Quarter

Quarter
```

Each level represents one stage of recursive decomposition.

Recursion trees provide intuition about:

- recursion depth;
- total work;
- number of recursive calls.

---

# 12. Work Per Level

Many Divide and Conquer algorithms perform work at every level of the recursion tree.

Example:

```text
Level 0

O(n)
```

```text
Level 1

O(n)
```

```text
Level 2

O(n)
```

The total complexity depends on:

- work per level;
- number of levels.

---

# 13. Number of Levels

Balanced recursive decomposition usually reduces the input size by half.

Conceptually:

```text
n

↓

n / 2

↓

n / 4

↓

n / 8

↓

...
```

The recursion depth becomes:

```text
O(log n)
```

This explains why many Divide and Conquer algorithms contain logarithmic factors.

---

# 14. Examples of Common Recurrences

| Recurrence | Typical Algorithm | Complexity |
|------------|-------------------|-----------:|
| `T(n)=T(n−1)+O(1)` | Factorial | O(n) |
| `T(n)=T(n/2)+O(1)` | Binary Search | O(log n) |
| `T(n)=2T(n/2)+O(n)` | Merge Sort | O(n log n) |
| `T(n)=2T(n/2)+O(1)` | Simple divide process | O(n) |

Recognizing these patterns simplifies algorithm analysis.

---

# 15. Why the Combine Phase Matters

The function:

```text
f(n)
```

represents the work performed outside recursive calls.

Examples include:

- partitioning;
- merging;
- copying elements;
- comparisons.

The complexity of this work strongly influences the overall recurrence.

---

# 16. Balanced versus Unbalanced Recurrences

Balanced recurrence:

```text
T(n)

=

2T(n / 2)

+

O(n)
```

Unbalanced recurrence:

```text
T(n)

=

T(n − 1)

+

O(n)
```

Balanced recurrences generally produce shallower recursion trees and better performance.

---

# 17. Recurrence Does Not Equal Complexity

A recurrence is **not** the final complexity.

Instead:

```text
Recurrence

↓

Mathematical Analysis

↓

Asymptotic Complexity
```

The recurrence describes the recursive behavior.

It must still be solved.

---

# 18. Solving Recurrences

Several techniques exist for solving recurrences.

Common methods include:

- repeated substitution;
- recursion trees;
- Master Theorem;
- mathematical induction.

This module focuses primarily on the Master Theorem because it efficiently solves many Divide and Conquer recurrences.

---

# 19. Common Mistakes

Students frequently:

- confuse the recurrence with the final complexity;
- forget the base case;
- omit the combine cost;
- use the wrong number of recursive calls;
- ignore problem-size reduction.

A correct recurrence must accurately model the recursive algorithm.

---

# 20. Java Perspective

Java implementations do not explicitly contain recurrence relations.

The recurrence is a mathematical abstraction created during algorithm analysis.

For example, a recursive Java method performing two recursive calls on half-sized arrays naturally corresponds to:

```text
T(n)

=

2T(n / 2)

+

f(n)
```

Understanding this relationship helps developers analyze algorithms independently of programming language syntax.

---

# 21. Engineering Perspective

Professional software engineers use recurrence relations to predict scalability before implementation.

Instead of relying on experimentation alone, they ask:

```text
How many recursive calls
are created?
```

```text
How much smaller
is each problem?
```

```text
How much work
is performed outside
the recursive calls?
```

Answering these questions produces the recurrence relation, which becomes the foundation for rigorous complexity analysis.

---

# 22. Relationship with the Next Document

This document introduced recurrence relations as mathematical models for recursive running time.

The next document studies the **Master Theorem**, a powerful analytical technique used to solve many of the recurrence relations generated by Divide and Conquer algorithms.

The Master Theorem transforms recursive cost equations into asymptotic complexity, allowing algorithm designers to determine performance systematically.

---

# 23. Key Takeaways

After completing this document, the learner should understand that:

- recurrence relations model the running time of recursive algorithms;
- a recurrence expresses the cost of solving a problem in terms of smaller recursive problems;
- the general Divide and Conquer recurrence is commonly written as `T(n) = aT(n/b) + f(n)`;
- every recurrence requires a base case;
- recursion trees help visualize recursive execution and workload distribution;
- the combine phase contributes to the non-recursive work represented by `f(n)`;
- balanced recursive decomposition usually produces logarithmic recursion depth;
- a recurrence relation is a mathematical model, not the final complexity;
- solving recurrence relations is an essential step in analyzing Divide and Conquer algorithms;
- recurrence analysis provides the foundation for the Master Theorem and advanced algorithmic complexity analysis.