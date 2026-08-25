# Correctness Proofs

## Algorithms Java Mastery

Designing a Greedy algorithm is only the first step in solving an optimization problem. The most important question is whether the algorithm **always** produces an optimal solution.

Unlike testing, which can only verify a limited number of examples, a **correctness proof** demonstrates that an algorithm works for **every valid input**.

For Greedy algorithms, correctness depends on proving that every local decision is safe and that the remaining subproblem can still be solved optimally. These ideas are formally captured by the **Greedy Choice Property** and **Optimal Substructure**.

Correctness proofs therefore play a central role in algorithm design. They transform an intuitive strategy into a mathematically justified solution.

---

# 1. Purpose

The purpose of this document is to:

- explain why correctness proofs are necessary;
- introduce the principal proof techniques for Greedy algorithms;
- understand how correctness is established mathematically;
- distinguish testing from formal proof;
- prepare for the analysis of classical Greedy algorithms.

---

# 2. Why Correctness Matters

An algorithm may appear correct because it works for several examples.

However:

```text
Works for Examples

≠

Works for Every Input
```

Only a mathematical proof can establish that a Greedy algorithm always produces an optimal solution.

---

# 3. Testing versus Proof

Testing examines specific cases.

```text
Input A

✓
```

```text
Input B

✓
```

```text
Input C

✓
```

Although useful, testing cannot guarantee correctness.

A proof demonstrates correctness for **all possible inputs**.

---

# 4. Foundation of Greedy Correctness

Correctness depends on two mathematical properties.

```text
Greedy Choice Property
        +
Optimal Substructure
        ↓
Correct Greedy Algorithm
```

Both properties must be established before claiming optimality.

---

# 5. General Proof Strategy

Most Greedy correctness proofs follow a common structure.

```text
Identify Greedy Choice
        ↓
Show Choice Is Safe
        ↓
Reduce Problem
        ↓
Show Remaining Problem
Is Still Optimal
        ↓
Conclude Overall Optimality
```

This reasoning appears repeatedly throughout Greedy algorithm analysis.

---

# 6. Safe Choice

A Greedy decision is considered **safe** if making it immediately cannot prevent an optimal final solution.

Conceptually:

```text
Choose Candidate
        ↓
Optimal Solution
Still Exists
```

If this condition holds, the decision may be accepted permanently.

---

# 7. Exchange Argument

The **Exchange Argument** is the most common proof technique for Greedy algorithms.

The idea is to compare:

- an arbitrary optimal solution;
- the Greedy solution.

Conceptually:

```text
Optimal Solution
        ↓
Replace One Decision
        ↓
Still Optimal
```

If replacing a decision with the Greedy choice preserves optimality, then the Greedy decision is safe.

---

# 8. Why the Exchange Argument Works

Suppose an optimal solution begins with a different decision.

If replacing that decision with the Greedy choice does not reduce the quality of the solution:

```text
Original Optimal
        ↓
Exchange
        ↓
New Optimal
```

The Greedy decision must also belong to an optimal solution.

---

# 9. Proof by Contradiction

Another common technique is **proof by contradiction**.

The reasoning is:

```text
Assume
Greedy Is Wrong
        ↓
Reach Contradiction
        ↓
Greedy Must Be Correct
```

Contradictions demonstrate that the opposite assumption is impossible.

---

# 10. Inductive Reasoning

Many Greedy correctness proofs also use mathematical induction.

General structure:

```text
Smallest Problem
        ↓
Assume Smaller
Problems Correct
        ↓
Prove Larger Problem
```

This method is especially useful when algorithms repeatedly reduce the problem size.

---

# 11. Recursive Reasoning

Greedy algorithms often reduce the remaining problem after each decision.

Conceptually:

```text
Choose Safe Decision
        ↓
Smaller Problem
        ↓
Repeat
```

Optimal Substructure guarantees that solving the remaining problem optimally preserves the overall solution.

---

# 12. Example: Activity Selection

Greedy rule:

```text
Choose the Activity
That Finishes Earliest
```

Proof outline:

```text
Greedy Choice
        ↓
Safe Exchange
        ↓
Remaining Activities
        ↓
Optimal Schedule
```

This example is one of the classical correctness proofs in Computer Science.

---

# 13. Example: Huffman Coding

Greedy rule:

```text
Merge Lowest
Frequencies
```

Proof outline:

```text
Lowest Frequencies
        ↓
Safe Merge
        ↓
Smaller Coding Problem
        ↓
Optimal Prefix Tree
```

The correctness proof demonstrates that the Greedy merge never increases the total encoding cost.

---

# 14. Common Structure of Greedy Proofs

Most proofs follow this logical sequence.

```text
Optimization Problem
        ↓
Greedy Decision
        ↓
Show Decision Is Safe
        ↓
Reduce Problem
        ↓
Apply Optimal Substructure
        ↓
Optimal Solution
```

Although the details differ, the reasoning pattern remains remarkably consistent.

---

# 15. Common Mistakes

Students frequently:

- assume the Greedy choice is correct without proof;
- rely only on intuition;
- confuse testing with mathematical proof;
- ignore Optimal Substructure;
- forget to justify why the Greedy choice is safe.

Correctness must always be demonstrated formally.

---

# 16. Relationship with Complexity

Correctness and complexity answer different questions.

Correctness asks:

```text
Does the algorithm
always produce
the right answer?
```

Complexity asks:

```text
How efficiently
does it produce
that answer?
```

Both analyses are necessary for evaluating an algorithm.

---

# 17. Engineering Perspective

Professional software engineers do not accept optimization algorithms solely because they perform well experimentally.

Instead, they verify:

```text
Correctness
        ↓
Complexity
        ↓
Implementation
        ↓
Testing
```

Formal reasoning ensures that software remains reliable under every valid input.

---

# 18. Relationship with the Next Document

This document introduced the mathematical techniques used to prove the correctness of Greedy algorithms.

The following documents apply these proof methods to classical optimization problems, beginning with the **Activity Selection Problem**, where the Greedy Choice Property and Optimal Substructure can be demonstrated formally.

---

# 19. Key Takeaways

After completing this document, the learner should understand that:

- correctness proofs establish that a Greedy algorithm always produces an optimal solution;
- testing alone cannot guarantee correctness;
- the Greedy Choice Property and Optimal Substructure form the theoretical basis of Greedy correctness;
- the Exchange Argument is the most common proof technique for Greedy algorithms;
- proof by contradiction and mathematical induction are also widely used;
- correctness and computational complexity answer different questions;
- formal reasoning is an essential part of professional algorithm design;
- understanding correctness proofs prepares the learner for analyzing classical Greedy optimization algorithms.