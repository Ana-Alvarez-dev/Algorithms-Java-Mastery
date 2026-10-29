# Optimal Substructure

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md)
for selected contracts, pseudocode, traces, proofs, Java, tests and evidence limits.

## Algorithms Java Mastery

## Residual state is part of the claim

The [contract/proof map](implementation-contracts.md) makes the chosen action,
feasibility and residual state explicit. Optimal substructure is a replacement
argument within a specified decomposition: improving a compatible residual
solution would improve the corresponding whole. It does not make an arbitrary
first choice safe.

**Optimal Substructure** is one of the two fundamental mathematical properties required by many optimization problems that can be solved efficiently using Greedy algorithms or Dynamic Programming.

A problem exhibits Optimal Substructure when an optimal solution to the original problem can be constructed from optimal solutions to its smaller subproblems.

This property allows complex optimization problems to be decomposed into simpler ones without losing correctness. Once the problem has been reduced, solving each subproblem optimally contributes directly to the construction of the overall optimal solution.

Although Optimal Substructure is shared by both Greedy algorithms and Dynamic Programming, the two paradigms exploit it differently. Greedy algorithms make a single irreversible decision at each step, whereas Dynamic Programming explores multiple possibilities and stores intermediate solutions.

Understanding Optimal Substructure is therefore essential for recognizing which algorithmic paradigm is appropriate for a given optimization problem.

---

# 1. Purpose

The purpose of this document is to:

- define Optimal Substructure;
- explain why it is important in optimization problems;
- distinguish it from the Greedy Choice Property;
- understand its role in Greedy algorithms;
- prepare for formal correctness proofs.

---

# 2. Definition

A problem has **Optimal Substructure** if an optimal solution can be obtained by combining optimal solutions to its subproblems.

Conceptually:

```text
Optimal Problem
        ↓
Optimal Subproblems
        ↓
Optimal Solution
```

Each subproblem contributes correctly to the final solution.

---

# 3. Fundamental Idea

The key principle is:

> If the overall solution is optimal, then every subproblem used to construct it must also be solved optimally.

Otherwise:

```text
Suboptimal Subproblem
        ↓
Suboptimal Final Solution
```

The replacement must preserve feasibility, boundary conditions and the already
fixed decisions. Without those conditions, individually optimal pieces may
conflict or omit a better first choice. The property is not permission to
combine arbitrary optimal answers to independently invented subproblems.

---

# 4. Recursive Perspective

Optimal Substructure naturally supports recursive reasoning.

Conceptually:

```text
Large Problem
        ↓
Smaller Problem
        ↓
Smaller Problem
        ↓
Base Case
```

Each recursive step preserves the optimization objective.

---

# 5. Relationship with Optimization

Optimization problems attempt to:

- minimize cost;
- maximize profit;
- minimize execution time;
- maximize efficiency;
- minimize resource consumption.

Optimal Substructure ensures that these objectives remain valid after decomposition.

---

# 6. Relationship with the Greedy Choice Property

The two concepts answer different questions.

Greedy Choice Property:

```text
Can I safely make
this decision now?
```

Optimal Substructure:

```text
Can the remaining
problem still be
solved optimally?
```

Both properties are required for many Greedy algorithms.

---

# 7. Why Optimal Substructure Matters

Suppose a problem is divided into two parts.

```text
Problem
        ↓
Subproblem A

Subproblem B
```

For a fixed compatible decomposition, a suboptimal residual would allow an
improvement by replacement. However, optimally completing a bad first choice
can remain globally suboptimal. With coins {1,3,4} and amount 6, fixing coin 4
and optimally paying the residual 2 gives three coins; choosing 3+3 uses two.
Thus an optimal residual is not itself a proof that the fixed first choice
belongs to a global optimum.

---

# 8. Classical Example

The **Activity Selection Problem** exhibits Optimal Substructure.

After selecting a **proved safe earliest-finishing** activity:

```text
Choose Activity
        ↓
Remaining Compatible Activities
        ↓
Optimal Remaining Schedule
```

The remaining scheduling problem is simply a smaller version of the original problem.

---

# 9. Huffman Coding

Huffman Coding also exhibits Optimal Substructure.

Conceptually:

```text
Merge Lowest Frequencies
        ↓
Smaller Coding Problem
        ↓
Optimal Prefix Tree
```

Each recursive reduction preserves the optimization objective.

---

# 10. Divide and Conquer

Divide and Conquer uses correct subproblem specifications and combination.
That is related reasoning, but a sorting postcondition is not by itself an
optimisation objective with an optimal-substructure theorem. For example:

```text
Merge Sort

↓

Sort Left Half

Sort Right Half

↓

Merge
```

Sorting both halves and merging proves order and multiset preservation; it
does not establish that this is an optimal sorting algorithm by any cost
measure. Some divide-and-conquer optimisation problems do have optimal
substructure, but it must be proved for the chosen objective/state. One-branch
decrease-and-conquer cases need not execute every hypothetical child.

---

# 11. Dynamic Programming

Dynamic Programming also depends on Optimal Substructure.

Unlike Greedy algorithms, it additionally exploits:

```text
Overlapping
Subproblems
```

Instead of solving the same subproblem repeatedly, Dynamic Programming stores previous solutions.

---

# 12. Problems Without Optimal Substructure

Not every optimization problem possesses this property.

Conceptually:

```text
Optimal Whole

↓

Suboptimal Part
```

In such situations, the problem cannot be solved simply by combining independently optimized subproblems.

Alternative strategies become necessary.

---

# 13. Relationship with Correctness

Correctness proofs for Greedy algorithms usually establish two facts.

```text
Greedy Choice Property
        +
Optimal Substructure
        ↓
Optimal Algorithm
```

Neither property alone is sufficient.

---

# 14. Recognizing Optimal Substructure

Useful questions include:

```text
Can the problem
be divided?
```

```text
Does replacing a residual
by a better feasible solution
improve the corresponding whole
under the fixed boundary state?
```

Make that replacement implication precise for the chosen objective/state.
A separate safe-choice or search argument still determines which first
action or decomposition yields a global optimum.

---

# 15. Engineering Perspective

Professional software engineers recognize Optimal Substructure before selecting an algorithmic paradigm.

Typical reasoning:

```text
Optimization Problem
        ↓
Optimal Substructure?
        ↓
Yes
        ↓
Consider
Greedy or
Dynamic Programming
```

Recognizing this property avoids choosing inefficient solution strategies.

---

# 16. Common Misconceptions

## "Every optimization problem has Optimal Substructure."

Incorrect.

Many optimization problems cannot be decomposed into independent optimal subproblems.

---

## "Optimal Substructure guarantees a Greedy solution."

False.

Optimal Substructure alone is insufficient.

The Greedy Choice Property must also hold.

---

## "Optimal Substructure is exclusive to Greedy algorithms."

Incorrect.

It is also fundamental to:

- Divide and Conquer;
- Dynamic Programming;
- many recursive optimization algorithms.

---

# 17. Comparison of Algorithmic Paradigms

| Paradigm | Optimal Substructure | Greedy Choice Property |
|----------|:--------------------:|:----------------------:|
| Greedy | Yes | Yes |
| Dynamic Programming | Yes | Not Required |
| Divide and Conquer | Depends on whether an optimisation objective is specified | Not required |

These rows describe exact optimal algorithms under their proved models, not
every heuristic using a greedy-looking rule. CoinChange on arbitrary systems
retains its separate non-optimal/incomplete contract.

---

# 18. Relationship with the Next Document

This document introduced **Optimal Substructure**, the second mathematical foundation of the Greedy paradigm.

The next document studies **Correctness Proofs**, explaining how the Greedy Choice Property and Optimal Substructure are combined to formally prove that a Greedy algorithm always produces an optimal solution.

---

# 19. Key Takeaways

After completing this document, the learner should understand that:

- Optimal Substructure allows optimal solutions to be constructed from optimal solutions to smaller subproblems;
- it is a mathematical property of optimization problems;
- Greedy algorithms and Dynamic Programming both rely on Optimal Substructure;
- the Greedy Choice Property and Optimal Substructure serve different purposes;
- Optimal Substructure alone does not guarantee that a Greedy algorithm is correct;
- recognizing this property helps determine the most appropriate algorithmic paradigm;
- understanding Optimal Substructure prepares the learner for formal correctness proofs and advanced optimization techniques.
