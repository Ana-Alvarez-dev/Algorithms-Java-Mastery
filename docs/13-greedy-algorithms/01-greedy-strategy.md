# Greedy Strategy

## Algorithms Java Mastery

The **Greedy Strategy** is one of the fundamental algorithm design paradigms in Computer Science. It constructs a solution incrementally by repeatedly making the **best possible decision at the current step**, without reconsidering previous choices.

Unlike exhaustive search algorithms, which evaluate many possible solutions, or Dynamic Programming algorithms, which solve and reuse overlapping subproblems, Greedy algorithms focus exclusively on the information available at each decision point.

The underlying assumption is that a sequence of locally optimal decisions will produce a globally optimal solution. However, this assumption is only valid for specific classes of optimization problems that satisfy well-defined mathematical properties.

For this reason, understanding the Greedy Strategy requires more than learning implementation techniques. It requires understanding **when the strategy is applicable, why it works, and how its correctness can be formally justified**.

---

# 1. Purpose

The purpose of this document is to:

- define the Greedy Strategy;
- explain its decision-making process;
- distinguish Greedy algorithms from other paradigms;
- identify problems suitable for Greedy solutions;
- establish the conceptual foundation for the remainder of this module.

---

# 2. What Is a Greedy Strategy?

A Greedy Strategy constructs a solution one decision at a time.

At each step, it selects the **locally optimal choice** according to a predefined criterion.

Conceptually:

```text
Problem
        ↓
Choose Best Local Option
        ↓
Reduce Remaining Problem
        ↓
Repeat
        ↓
Final Solution
```

Once a decision has been made, it is never changed.

---

# 3. Local versus Global Optimality

The central idea of the Greedy paradigm is the relationship between **local** and **global** optimality.

A local optimum is the best decision available at the current step.

A global optimum is the best possible complete solution.

Conceptually:

```text
Local Decisions
        ↓
Global Solution
```

The challenge is determining whether local decisions are sufficient to guarantee global optimality.

---

# 4. Incremental Construction

Unlike algorithms that solve the entire problem recursively before producing a result, Greedy algorithms build the solution progressively.

Conceptually:

```text
Empty Solution
        ↓
First Choice
        ↓
Second Choice
        ↓
Third Choice
        ↓
Complete Solution
```

Each new decision extends the existing partial solution.

---

# 5. Irreversible Decisions

A defining characteristic of Greedy algorithms is that decisions are **irreversible**.

Once an element has been selected:

```text
Choose
        ↓
Commit
        ↓
Continue
```

The algorithm never revisits earlier choices.

This property contributes to the efficiency of Greedy algorithms but also limits their applicability.

---

# 6. Optimization Problems

Greedy algorithms are primarily designed for **optimization problems**.

Typical objectives include:

- minimizing cost;
- maximizing profit;
- minimizing execution time;
- minimizing resource usage;
- maximizing efficiency.

The algorithm attempts to optimize a measurable objective function.

---

# 7. Decision Criteria

Every Greedy algorithm relies on a selection rule.

Examples include:

- smallest value;
- largest value;
- earliest finishing time;
- highest priority;
- lowest weight;
- highest frequency.

The quality of the algorithm depends on whether this criterion always produces safe decisions.

---

# 8. General Greedy Process

Most Greedy algorithms follow a similar structure.

```text
Initialize Solution
        ↓
Select Best Candidate
        ↓
Verify Candidate
        ↓
Add to Solution
        ↓
Repeat Until Complete
```

No backtracking or reconsideration occurs.

---

# 9. Characteristics of Greedy Algorithms

Greedy algorithms typically exhibit the following characteristics:

- incremental construction;
- local decision making;
- irreversible choices;
- relatively simple implementation;
- efficient execution.

Many practical optimization algorithms share these properties.

---

# 10. Advantages

Greedy algorithms provide several important benefits.

They often offer:

- simple implementations;
- efficient execution;
- low memory consumption;
- fast decision making;
- excellent practical performance.

These advantages explain their widespread use in real-world software systems.

---

# 11. Limitations

The Greedy Strategy is **not universally applicable**.

Potential limitations include:

- local decisions may prevent the optimal global solution;
- correctness is not guaranteed for every optimization problem;
- mathematical proof is required;
- some problems require considering future decisions.

Choosing a Greedy strategy without verification may produce incorrect solutions.

---

# 12. Comparison with Divide and Conquer

Although both paradigms simplify problems, they do so differently.

| Divide and Conquer | Greedy Strategy |
|--------------------|-----------------|
| Solves recursive subproblems | Makes immediate decisions |
| Combines partial solutions | Builds one solution incrementally |
| Recursive decomposition | Sequential decision making |
| Multiple recursive branches | One decision at each step |

The two paradigms solve optimization problems using fundamentally different approaches.

---

# 13. Comparison with Dynamic Programming

Greedy algorithms differ significantly from Dynamic Programming.

| Greedy | Dynamic Programming |
|---------|---------------------|
| Makes one local decision | Evaluates multiple possibilities |
| Never revisits choices | Stores intermediate solutions |
| Usually lower memory usage | Higher memory usage |
| Simpler implementation | More complex implementation |

Dynamic Programming is often used when Greedy decisions cannot guarantee optimality.

---

# 14. Typical Applications

Greedy algorithms appear in many areas of Computer Science.

Examples include:

- Activity Selection;
- Interval Scheduling;
- Huffman Coding;
- Minimum Spanning Trees;
- Dijkstra's Algorithm (under appropriate conditions);
- data compression;
- network optimization.

Each application relies on a carefully justified selection rule.

---

# 15. Why Greedy Algorithms Can Be Fast

Greedy algorithms avoid exploring unnecessary alternatives.

Instead of evaluating every possible solution:

```text
Many Possibilities
        ↓
Choose Best Candidate
        ↓
Continue
```

This significantly reduces computational work.

---

# 16. Engineering Perspective

Professional software engineers choose Greedy algorithms when:

- decisions can be made efficiently;
- correctness can be formally proven;
- scalability is important;
- computational resources are limited.

The decision to use a Greedy algorithm is based on mathematical properties rather than implementation convenience.

---

# 17. Common Misconceptions

## "Greedy algorithms always produce the optimal solution."

Incorrect.

Only problems satisfying specific mathematical properties can be solved optimally using a Greedy approach.

---

## "Greedy algorithms are always faster."

Not necessarily.

Although Greedy algorithms are often efficient, their performance depends on preprocessing, sorting, and supporting data structures.

---

## "Greedy algorithms never use recursion."

Incorrect.

While many Greedy algorithms are iterative, recursion may still be used when appropriate.

The defining characteristic is the decision-making strategy, not the implementation technique.

---

# 18. Relationship with the Next Document

This document introduced the Greedy Strategy as a general algorithm design paradigm.

The next document studies the **Greedy Choice Property**, the mathematical condition that determines whether locally optimal decisions can always produce a globally optimal solution.

Understanding this property is essential before designing or proving the correctness of any Greedy algorithm.

---

# 19. Key Takeaways

After completing this document, the learner should understand that:

- the Greedy Strategy constructs a solution through a sequence of locally optimal decisions;
- Greedy algorithms build solutions incrementally without reconsidering previous choices;
- local optimality does not automatically imply global optimality;
- Greedy algorithms are primarily used for optimization problems;
- every Greedy algorithm depends on a well-defined decision criterion;
- Greedy algorithms differ fundamentally from Divide and Conquer and Dynamic Programming;
- mathematical justification is required before applying a Greedy strategy;
- understanding the Greedy paradigm provides the foundation for studying its correctness properties and classical optimization algorithms.