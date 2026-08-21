# Candidate Generation

## Algorithms Java Mastery

**Candidate Generation** is one of the fundamental phases of every Backtracking algorithm. During this phase, the algorithm systematically produces the possible decisions that may extend the current partial solution.

Unlike Greedy algorithms, which select a single candidate and never reconsider it, Backtracking explores **every feasible candidate** one at a time. Each candidate is temporarily added to the current solution, validated against the problem constraints, and either accepted for further exploration or discarded through backtracking.

The efficiency of a Backtracking algorithm depends heavily on how candidates are generated. Well-designed candidate generation reduces unnecessary recursive calls, simplifies constraint validation, and enables effective pruning.

Understanding this phase is essential because every Backtracking algorithm follows the same general pattern:

- generate a candidate;
- validate the candidate;
- recursively explore the new state;
- undo the decision;
- generate the next candidate.

---

# 1. Purpose

The purpose of this document is to:

- define candidate generation;
- explain its role in Backtracking;
- understand how recursive exploration expands the search space;
- analyze candidate generation strategies;
- prepare for constraint validation and pruning.

---

# 2. What Is a Candidate?

A **candidate** is a possible decision that extends the current partial solution.

Conceptually:

```text
Current Solution
        ↓
Possible Candidate
        ↓
New Partial Solution
```

Each candidate represents one possible continuation of the search.

---

# 3. Candidate Generation

Candidate generation is the process of producing every possible decision available from the current state.

Conceptually:

```text
Current State
        ↓
Candidate A

Candidate B

Candidate C
```

Each candidate becomes the starting point for a new recursive branch.

---

# 4. Incremental Construction

Backtracking constructs solutions incrementally.

```text
Empty Solution
        ↓
Add Candidate
        ↓
Partial Solution
        ↓
Add Candidate
        ↓
Complete Solution
```

Every recursive call extends the current solution by exactly one decision.

---

# 5. Recursive Expansion

Each recursive level generates a new set of candidates.

Conceptually:

```text
Level 1

↓

Generate Candidates

↓

Level 2

↓

Generate Candidates

↓

Level 3
```

The search continues until a complete solution or an invalid state is reached.

---

# 6. Candidate Selection Loop

Most Backtracking algorithms contain the same conceptual loop.

```text
For Each Candidate
        ↓
Add Candidate
        ↓
Validate
        ↓
Explore
        ↓
Remove Candidate
```

This loop systematically explores the search space.

---

# 7. Candidate Ordering

Candidates may be generated in different orders.

Examples include:

- ascending order;
- descending order;
- lexicographical order;
- heuristic order;
- problem-specific order.

The order affects exploration but not necessarily correctness.

---

# 8. Candidate Representation

Candidates depend on the problem being solved.

Examples:

| Problem | Candidate |
|----------|-----------|
| Permutations | One unused element |
| N-Queens | One column in the current row |
| Sudoku | One possible digit |
| Graph Coloring | One available color |

Each candidate represents a legal decision that may extend the current solution.

---

# 9. Example: Permutations

Suppose the elements are:

```text
A

B

C
```

Initially, the candidates are:

```text
A

B

C
```

If:

```text
A
```

is selected, the remaining candidates become:

```text
B

C
```

The search continues recursively.

---

# 10. Example: N-Queens

At each recursive level:

```text
Current Row
        ↓
Column 1

Column 2

Column 3

...
```

Each column represents a candidate position for the queen.

Constraint validation determines whether recursion continues.

---

# 11. State Transition

Every accepted candidate transforms one state into another.

Conceptually:

```text
Current State
        ↓
Choose Candidate
        ↓
New State
```

The recursive search explores this new state before returning.

---

# 12. Candidate Independence

Each candidate represents an independent branch of the search tree.

```text
Current State

↓

Candidate A

Candidate B

Candidate C
```

Exploring one branch does not affect the exploration of the others after backtracking restores the previous state.

---

# 13. Avoiding Duplicate Candidates

Some problems require preventing repeated candidates.

Example:

```text
Current Solution

↓

Already Used?

↓

Yes

↓

Skip Candidate
```

Avoiding duplicate candidates reduces unnecessary exploration.

---

# 14. Relationship with Constraint Validation

Candidate generation alone is insufficient.

Every generated candidate must be validated.

Conceptually:

```text
Generate Candidate
        ↓
Valid?
      /      \
    No        Yes
    ↓          ↓
Discard    Continue
```

Constraint validation filters invalid candidates before deeper recursion.

---

# 15. Relationship with Pruning

Pruning begins immediately after candidate generation.

```text
Generate Candidate
        ↓
Impossible?
      /      \
    Yes       No
    ↓          ↓
Prune      Continue
```

The earlier invalid candidates are rejected, the smaller the explored search space becomes.

---

# 16. Complexity Considerations

The number of generated candidates strongly influences the algorithm's complexity.

Conceptually:

```text
More Candidates
        ↓
More Branches
        ↓
Larger Search Tree
```

Reducing unnecessary candidate generation improves efficiency.

---

# 17. Java Perspective

Java implementations typically generate candidates using:

- `for` loops;
- arrays;
- `ArrayList`;
- boolean arrays;
- recursive helper methods.

Each recursive call usually iterates through every available candidate before selecting the next one.

---

# 18. Engineering Perspective

Professional software engineers carefully design candidate generation because it directly determines the size of the search tree.

Typical questions include:

```text
Can invalid
candidates
be avoided?
```

```text
Can candidates
be generated
more efficiently?
```

```text
Does ordering
improve pruning?
```

Efficient candidate generation often produces greater performance improvements than low-level code optimizations.

---

# 19. Relationship with the Next Document

This document introduced **Candidate Generation**, the mechanism responsible for expanding the search space.

The next document studies **Constraint Validation**, explaining how each generated candidate is verified before recursive exploration continues.

Together, candidate generation and constraint validation form the core of every Backtracking algorithm.

---

# 20. Key Takeaways

After completing this document, the learner should understand that:

- candidate generation produces the possible decisions available at each recursive step;
- every candidate extends the current partial solution;
- Backtracking systematically explores one candidate at a time;
- recursive expansion creates the branches of the state-space tree;
- candidate generation must be followed by constraint validation;
- avoiding duplicate or unnecessary candidates reduces search space size;
- the efficiency of candidate generation significantly influences Backtracking performance;
- well-designed candidate generation simplifies pruning and improves algorithm scalability.