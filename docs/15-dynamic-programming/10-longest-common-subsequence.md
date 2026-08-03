# Longest Common Subsequence (LCS)

## Algorithms Java Mastery

The **Longest Common Subsequence (LCS)** Problem is one of the most influential applications of **Dynamic Programming**. Unlike optimization problems such as the Knapsack Problem, LCS focuses on comparing two sequences to determine the longest subsequence that appears in both while preserving the original order of their elements.

The problem demonstrates how Dynamic Programming can efficiently solve sequence comparison tasks that would otherwise require exponential recursive exploration.

The LCS algorithm introduces several important Dynamic Programming concepts:

- two-dimensional state representation;
- multidimensional recurrence relations;
- optimal decision making;
- table-based computation;
- sequence reconstruction.

Because of its practical importance, LCS is widely used in version control systems, bioinformatics, text comparison, plagiarism detection, DNA analysis, and file synchronization.

This case study represents one of the most important milestones in learning Dynamic Programming because it combines mathematical reasoning, state modeling, and practical software engineering applications.

---

# 1. Purpose

The purpose of this document is to:

- introduce the Longest Common Subsequence problem;
- understand sequence comparison using Dynamic Programming;
- define states and recurrence relations;
- analyze Memoization and Tabulation approaches;
- evaluate computational complexity.

---

# 2. Problem Statement

Given:

- two sequences;

Goal:

```text
Find

↓

The Longest
Subsequence

↓

Present In
Both Sequences
```

The subsequence must preserve the relative order of the elements.

---

# 3. What Is a Subsequence?

A subsequence is obtained by removing zero or more elements without changing the order of the remaining elements.

Example:

Sequence:

```text
ABCDE
```

Valid subsequences:

```text
ACE

ABD

BC

ABCDE
```

Invalid:

```text
AEC
```

because the original order has changed.

---

# 4. Example Problem

Sequence 1:

```text
ABCBDAB
```

Sequence 2:

```text
BDCABA
```

One longest common subsequence is:

```text
BCBA
```

Length:

```text
4
```

Other valid longest common subsequences may also exist.

---

# 5. Why Brute Force Is Inefficient

A brute-force solution would generate:

```text
All Subsequences
Of Sequence A
```

and compare them with:

```text
All Subsequences
Of Sequence B
```

The number of subsequences grows exponentially.

Therefore:

```text
Brute Force

↓

Impractical
```

for large sequences.

---

# 6. Why Dynamic Programming Works

The LCS problem satisfies both required properties.

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

Repeated comparisons are computed only once.

---

# 7. State Definition

The classical state is:

```text
State(i,j)
```

where:

- **i** represents the current position in the first sequence;
- **j** represents the current position in the second sequence.

Each state represents one unique comparison subproblem.

---

# 8. State Transition

Every state compares two characters.

Conceptually:

```text
Sequence A[i]

↓

Sequence B[j]
```

The comparison determines the next state.

---

# 9. Decision Process

There are two possible situations.

## Characters Match

```text
A[i]

=

B[j]
```

The subsequence grows.

---

## Characters Do Not Match

The algorithm explores two alternatives.

```text
Skip Character
From A
```

or

```text
Skip Character
From B
```

The larger result becomes the optimal value.

---

# 10. Recurrence Relation

If the characters match:

```text
LCS(i,j)

=

1

+

LCS(i−1,j−1)
```

Otherwise:

```text
LCS(i,j)

=

Maximum(

LCS(i−1,j),

LCS(i,j−1)

)
```

The recurrence compares both possibilities before selecting the optimal one.

---

# 11. Memoization

Memoization computes states recursively.

Workflow:

```text
State(i,j)

↓

Already Stored?
      /       \
    Yes        No
    ↓           ↓
Return      Compute
Result         ↓
          Store Result
```

Each comparison state is solved exactly once.

---

# 12. Tabulation

Tabulation constructs the Dynamic Programming table iteratively.

Conceptually:

```text
Initialize
Base Cases
        ↓
Fill Table
        ↓
Final Cell
```

Each table entry depends on previously computed neighbors.

---

# 13. Dynamic Programming Table

Rows represent:

```text
Sequence A
```

Columns represent:

```text
Sequence B
```

Each cell stores:

```text
Length Of
Longest Common
Subsequence
```

for the corresponding prefixes.

---

# 14. Table Dependencies

Each table entry depends on neighboring cells.

```text
Diagonal

Top

Left
```

These dependencies determine the order in which the table is filled.

---

# 15. Overlapping Subproblems

Different recursive paths often compare the same pair of prefixes.

Example:

```text
State(i,j)
```

may appear repeatedly during recursive execution.

Dynamic Programming stores the result after the first computation.

---

# 16. Optimal Substructure

The optimal subsequence for larger prefixes depends on the optimal subsequences of smaller prefixes.

Conceptually:

```text
Smaller Prefixes

↓

Optimal Solution

↓

Larger Prefixes
```

This property makes Dynamic Programming applicable.

---

# 17. Time Complexity

Let:

```text
m
```

be the length of the first sequence.

Let:

```text
n
```

be the length of the second sequence.

Time complexity:

```text
O(m × n)
```

Every state is computed exactly once.

---

# 18. Space Complexity

The Dynamic Programming table stores:

```text
m × n
```

states.

Auxiliary space:

```text
O(m × n)
```

Some implementations reduce memory usage to:

```text
O(min(m,n))
```

when only the length of the LCS is required.

---

# 19. Practical Applications

Longest Common Subsequence is used in numerous software systems.

Examples include:

- version control systems;
- source code comparison;
- Git merge algorithms;
- plagiarism detection;
- DNA sequence analysis;
- RNA sequence alignment;
- text editors;
- document synchronization.

These applications rely on efficient sequence comparison.

---

# 20. Java Perspective

Java implementations commonly use:

- two-dimensional arrays;
- nested loops;
- recursion;
- Memoization;
- Tabulation.

Typical table:

```text
dp[i][j]
```

Each entry stores the length of the longest common subsequence for one pair of prefixes.

---

# 21. Engineering Perspective

The LCS Problem demonstrates how Dynamic Programming solves sequence comparison efficiently.

```text
Compare Characters
        ↓
Define State
        ↓
Apply Recurrence
        ↓
Fill DP Table
        ↓
Optimal Length
```

Because of its combination of multidimensional states, recurrence relations, and practical applications, LCS is considered one of the most important Dynamic Programming problems studied in Computer Science.

---

# 22. Relationship with the Next Document

This document studied the **Longest Common Subsequence** problem, illustrating how Dynamic Programming efficiently compares sequences using two-dimensional states and recurrence relations.

The next document, **Complexity Analysis**, summarizes the computational characteristics of the Dynamic Programming algorithms presented throughout this module, comparing execution time, auxiliary space, state-space size, and engineering trade-offs.

---

# 23. Key Takeaways

After completing this document, the learner should understand that:

- the Longest Common Subsequence problem seeks the longest subsequence shared by two sequences while preserving order;
- the problem satisfies both **Overlapping Subproblems** and **Optimal Substructure**;
- the state is commonly defined as **(i, j)**, representing positions in the two sequences;
- the recurrence relation distinguishes between matching and non-matching characters;
- Memoization and Tabulation both solve each comparison state exactly once;
- Dynamic Programming reduces the problem from exponential complexity to **O(m × n)** time;
- two-dimensional tables naturally represent sequence comparison problems;
- LCS is a foundational algorithm with important applications in software engineering, bioinformatics, text processing, and version control systems.