# Permutations and Combinations

## Algorithms Java Mastery

**Permutations** and **Combinations** are among the most classical applications of the **Backtracking** algorithm design paradigm. Both problems require systematically exploring a large search space while constructing solutions incrementally.

Although they appear similar, they differ in one fundamental aspect:

- **Permutations** consider the **order** of the selected elements.
- **Combinations** ignore the order and consider only the selected elements themselves.

Backtracking provides a natural solution because it recursively generates partial solutions, verifies whether they are complete, and backtracks to explore alternative possibilities.

These problems are fundamental in Computer Science because they introduce many concepts that appear in more advanced Backtracking applications, including state-space trees, candidate generation, recursive exploration, pruning, and combinatorial complexity.

---

# 1. Purpose

The purpose of this document is to:

- distinguish permutations from combinations;
- explain how Backtracking generates both structures;
- analyze recursive search;
- understand candidate generation;
- evaluate computational complexity.

---

# 2. Combinatorial Generation

Both problems belong to **combinatorial generation**, whose objective is to systematically enumerate every valid solution.

Conceptually:

```text
Input Elements
        ↓
Generate Candidates
        ↓
Explore Search Tree
        ↓
Produce All Solutions
```

Backtracking guarantees that every valid solution is eventually explored.

---

# 3. What Is a Permutation?

A **permutation** is an arrangement where **order matters**.

Example:

Elements:

```text
A B C
```

Valid permutations:

```text
ABC

ACB

BAC

BCA

CAB

CBA
```

Each different ordering represents a different solution.

---

# 4. What Is a Combination?

A **combination** is a selection where **order does not matter**.

Example:

Elements:

```text
A B C
```

Choosing two elements:

```text
AB

AC

BC
```

Notice:

```text
AB

=

BA
```

Only one of these represents a valid combination.

---

# 5. Fundamental Difference

| Permutations | Combinations |
|--------------|--------------|
| Order matters | Order does not matter |
| Every ordering is unique | Different orderings are identical |
| Larger search space | Smaller search space |

This distinction determines how candidates are generated.

---

# 6. Backtracking Strategy

Both problems follow the same recursive process.

```text
Current Solution
        ↓
Generate Candidate
        ↓
Validate
        ↓
Continue
        ↓
Backtrack
```

The difference lies in how candidates are selected.

---

# 7. Candidate Generation for Permutations

At each recursive level:

```text
Choose Any
Unused Element
```

Example:

```text
Current

↓

A

↓

Choose

B

or

C
```

Previously selected elements cannot be chosen again.

---

# 8. Candidate Generation for Combinations

For combinations:

```text
Choose Only
Remaining Elements
```

Conceptually:

```text
A

↓

B

↓

C
```

The search never returns to earlier elements.

This naturally avoids duplicate combinations.

---

# 9. State-Space Tree

Both problems can be represented using a state-space tree.

Permutations:

```text
        {}
      /  |  \
     A   B   C
```

Combinations:

```text
        {}
      /  |  \
     A   B   C
```

The branching process differs after the first level because combinations avoid revisiting previous elements.

---

# 10. Recursive Exploration

The recursive search follows the same structure.

```text
Partial Solution
        ↓
Choose Candidate
        ↓
Recursive Call
        ↓
Undo Choice
```

Every recursive call explores one branch of the search tree.

---

# 11. Base Case

The recursion terminates when the solution reaches the required size.

Conceptually:

```text
Solution Complete
        ↓
Store Solution
        ↓
Return
```

The algorithm then backtracks to generate alternative solutions.

---

# 12. Backtracking Process

After returning from recursion:

```text
Remove
Last Candidate
        ↓
Try Next Candidate
```

This restoration step ensures that the previous state is recovered before exploring another branch.

---

# 13. Avoiding Duplicate Solutions

Duplicate generation must be prevented.

Permutations:

```text
Already Used?

↓

Skip
```

Combinations:

```text
Already
Processed?

↓

Do Not Return
```

These rules guarantee that each solution is generated exactly once.

---

# 14. Complexity Analysis

Suppose there are:

```text
n
```

elements.

Permutations:

```text
n!
```

possible solutions.

Combinations:

```text
n choose k
```

possible solutions.

Because every solution must be generated, the running time is proportional to the number of generated solutions.

---

# 15. Space Complexity

Recursive depth depends on the solution size.

Permutations:

```text
O(n)
```

Combinations:

```text
O(k)
```

Additional memory is required for the current partial solution.

---

# 16. Practical Applications

Permutations and combinations appear in many practical systems.

Examples include:

- password generation;
- scheduling;
- resource allocation;
- test-case generation;
- game solving;
- feature selection;
- recommendation systems;
- combinatorial optimization.

These problems frequently require systematic search.

---

# 17. Java Perspective

Java implementations commonly use:

- recursion;
- `ArrayList`;
- boolean arrays;
- recursive helper methods;
- index variables.

Boolean arrays are especially useful for permutation problems because they efficiently track previously selected elements.

---

# 18. Engineering Perspective

Professional software engineers often use these problems to evaluate recursive reasoning during technical interviews.

Typical design questions include:

```text
How are
candidates
generated?
```

```text
How are
duplicates
avoided?
```

```text
When should
recursion stop?
```

These problems develop the fundamental skills required for more advanced Backtracking algorithms.

---

# 19. Relationship with the Next Document

This document introduced the two classical combinatorial generation problems solved using Backtracking.

The next document studies the **N-Queens Problem**, a significantly more sophisticated application that combines candidate generation, constraint validation, pruning, and recursive search to solve a constraint satisfaction problem.

---

# 20. Key Takeaways

After completing this document, the learner should understand that:

- permutations generate ordered arrangements of elements;
- combinations generate unordered selections of elements;
- Backtracking systematically generates every valid solution;
- candidate generation differs depending on whether order matters;
- recursion terminates when a complete solution has been constructed;
- backtracking restores the previous state before exploring alternative branches;
- duplicate solutions must be avoided through appropriate candidate selection rules;
- permutations and combinations provide the conceptual foundation for more advanced Backtracking algorithms such as the N-Queens Problem.