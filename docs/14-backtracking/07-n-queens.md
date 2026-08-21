# N-Queens

## Algorithms Java Mastery

The **N-Queens Problem** is one of the most famous applications of the **Backtracking** algorithm design paradigm. It illustrates how recursive search, candidate generation, constraint validation, and pruning work together to solve a complex constraint satisfaction problem.

The objective is simple:

> **Place N queens on an N × N chessboard so that no two queens attack each other.**

Although the problem statement is easy to understand, the search space grows extremely rapidly as the board size increases. A brute-force solution would explore an enormous number of possible board configurations, making it computationally impractical.

Backtracking overcomes this difficulty by constructing the board one queen at a time, immediately rejecting any partial arrangement that violates the problem constraints. This early elimination of invalid states makes the algorithm dramatically more efficient than exhaustive search.

The N-Queens Problem has become a classical example for teaching recursive algorithms, state-space trees, pruning strategies, and combinatorial search.

---

# 1. Purpose

The purpose of this document is to:

- introduce the N-Queens Problem;
- explain why Backtracking is an appropriate solution strategy;
- understand candidate generation and constraint validation;
- analyze pruning;
- evaluate computational complexity.

---

# 2. Problem Statement

Given:

- an **N × N** chessboard;
- **N** queens.

Goal:

```text
Place Every Queen

↓

No Two Queens
Attack Each Other
```

Every queen must satisfy the problem constraints.

---

# 3. Chess Rules

A queen attacks every square located on the same:

- row;
- column;
- diagonal.

Conceptually:

```text
Queen

↓

Row

↓

Column

↓

Diagonal
```

Therefore, two queens may never share any of these positions.

---

# 4. Constraints

A valid solution must satisfy three constraints.

## One Queen per Row

```text
One Row

↓

One Queen
```

---

## One Queen per Column

```text
One Column

↓

One Queen
```

---

## No Shared Diagonals

```text
Diagonal

↓

No Other Queen
```

Every partial solution must satisfy all three conditions.

---

# 5. Why Brute Force Is Inefficient

A brute-force algorithm would attempt:

```text
Every Possible
Board Configuration

↓

Check Validity
```

The number of possible arrangements grows explosively as **N** increases.

Most of these configurations are invalid.

---

# 6. Why Backtracking Works

Backtracking constructs the board incrementally.

```text
Place Queen

↓

Validate

↓

Continue

↓

Backtrack
```

Invalid configurations are rejected immediately.

---

# 7. Candidate Generation

The algorithm places queens one row at a time.

Example:

```text
Row 1

↓

Choose Column
```

Then:

```text
Row 2

↓

Choose Column
```

Each available column represents a candidate.

---

# 8. Constraint Validation

After placing a queen, the algorithm verifies:

```text
Same Column?

↓

Same Diagonal?
```

If either condition is true:

```text
Invalid

↓

Backtrack
```

Only valid board configurations continue recursively.

---

# 9. State-Space Tree

The search process forms a state-space tree.

Example:

```text
Empty Board

↓

Row 1

↓

Possible Columns

↓

Row 2

↓

Possible Columns

↓

...
```

Each level represents one row of the chessboard.

Each branch represents one column choice.

---

# 10. Recursive Process

The recursive algorithm follows these steps.

```text
Place Queen
        ↓
Validate Position
        ↓
Recursive Call
        ↓
Remove Queen
```

Removing the queen restores the board before trying another position.

---

# 11. Backtracking

Whenever a conflict is detected:

```text
Conflict

↓

Remove Queen

↓

Previous Row

↓

Try Next Column
```

This process continues until every possible arrangement has been explored.

---

# 12. Pruning

Pruning dramatically reduces the search space.

Without pruning:

```text
Explore
Entire Tree
```

With pruning:

```text
Conflict

↓

Stop
```

Entire branches are eliminated before additional recursive calls occur.

---

# 13. Example (4-Queens)

One valid solution is:

```text
. Q . .

. . . Q

Q . . .

. . Q .
```

Each row contains one queen.

No two queens share:

- a row;
- a column;
- a diagonal.

---

# 14. Search Tree Example

Conceptually:

```text
Row 1

↓

Column 2

↓

Row 2

↓

Column 4

↓

Row 3

↓

Column 1

↓

Row 4

↓

Column 3
```

This path represents one complete solution.

---

# 15. Complexity Analysis

The search space grows extremely quickly.

Worst-case complexity is commonly expressed as:

```text
O(N!)
```

because each recursive level has fewer available columns.

Pruning greatly reduces the number of explored states in practice, although the theoretical worst case remains exponential.

---

# 16. Space Complexity

Recursive depth:

```text
O(N)
```

Additional memory is required for:

- current board configuration;
- recursion stack;
- constraint tracking structures.

---

# 17. Improving Performance

Several techniques improve performance.

Examples include:

- pruning;
- efficient column tracking;
- diagonal lookup tables;
- boolean arrays;
- bit manipulation.

These optimizations reduce validation cost.

---

# 18. Practical Applications

Although the N-Queens Problem is primarily educational, similar Backtracking techniques appear in:

- scheduling;
- resource allocation;
- puzzle solving;
- graph coloring;
- circuit design;
- artificial intelligence;
- combinatorial optimization.

Many real-world constraint satisfaction problems share the same search principles.

---

# 19. Java Perspective

Java implementations commonly use:

- recursion;
- boolean arrays for occupied columns;
- boolean arrays for diagonals;
- two-dimensional arrays or lists for the board.

Instead of scanning the board repeatedly, boolean arrays allow constraint validation in constant time.

---

# 20. Engineering Perspective

The N-Queens Problem demonstrates every major component of the Backtracking paradigm.

```text
Generate Candidate
        ↓
Validate
        ↓
Prune
        ↓
Recursive Search
        ↓
Backtrack
```

It illustrates how careful constraint validation and pruning transform an otherwise impractical exhaustive search into an efficient recursive algorithm.

For this reason, N-Queens is considered one of the canonical examples for learning Backtracking.

---

# 21. Relationship with the Next Document

This document completed the study of the principal Backtracking case studies.

The next document, **Complexity Analysis**, compares the computational characteristics of the Backtracking algorithms studied throughout this module, examining search-tree growth, recursion depth, pruning efficiency, auxiliary space, and engineering trade-offs.

---

# 22. Key Takeaways

After completing this document, the learner should understand that:

- the N-Queens Problem is a classical Backtracking and constraint satisfaction problem;
- queens must not share rows, columns, or diagonals;
- the algorithm places queens incrementally, one row at a time;
- constraint validation determines whether recursive exploration continues;
- pruning eliminates invalid branches before deeper recursion occurs;
- the worst-case search complexity is exponential, commonly expressed as **O(N!)**;
- efficient data structures significantly reduce validation costs;
- the N-Queens Problem integrates every major concept studied in the Backtracking module, including state-space trees, candidate generation, constraint validation, recursive search, and pruning.