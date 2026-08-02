# Activity Selection

## Algorithms Java Mastery

The **Activity Selection Problem** is one of the most classical applications of the **Greedy algorithm design paradigm**. It demonstrates that, under specific mathematical conditions, making the best local decision at every step leads to an optimal global solution.

The objective is to select the largest possible number of mutually compatible activities from a set of activities, where each activity has a starting time and a finishing time.

Although several valid schedules may exist, the challenge is to maximize the number of non-overlapping activities.

This problem is historically significant because it clearly illustrates both the **Greedy Choice Property** and **Optimal Substructure**, making it one of the first optimization problems studied in algorithm design courses.

---

# 1. Purpose

The purpose of this document is to:

- introduce the Activity Selection Problem;
- explain why it can be solved using a Greedy algorithm;
- analyze the Greedy strategy;
- demonstrate the correctness intuition;
- evaluate its computational complexity.

---

# 2. Problem Statement

Given:

- a collection of activities;
- each activity has:
    - a start time;
    - a finish time.

Goal:

```text
Select the Maximum
Number of Compatible
Activities
```

Two activities are compatible if they do not overlap in time.

---

# 3. Example

Suppose the following activities.

| Activity | Start | Finish |
|----------|------:|-------:|
| A | 1 | 4 |
| B | 3 | 5 |
| C | 0 | 6 |
| D | 5 | 7 |
| E | 8 | 9 |
| F | 5 | 9 |

The objective is to build the largest compatible schedule.

---

# 4. Why Brute Force Is Inefficient

One possible approach is:

```text
Generate Every
Possible Schedule

↓

Choose Largest
Compatible Schedule
```

Although correct, this strategy becomes computationally infeasible as the number of activities grows.

A more efficient approach is needed.

---

# 5. Greedy Strategy

The Greedy solution is remarkably simple.

Always choose:

```text
The Activity
That Finishes Earliest
```

Once selected:

- remove every incompatible activity;
- repeat the same process with the remaining activities.

This local rule leads to an optimal solution.

---

# 6. Why the Earliest Finish Time?

Selecting the activity that finishes first leaves as much remaining time as possible for future activities.

Conceptually:

```text
Earlier Finish
        ↓
More Remaining Time
        ↓
More Possible Activities
```

This decision maximizes future opportunities.

---

# 7. Greedy Choice Property

The Activity Selection Problem satisfies the **Greedy Choice Property**.

The earliest finishing activity is always a **safe choice**.

Choosing it never reduces the maximum number of compatible activities that can be selected later.

Therefore:

```text
Choose Earliest Finish
        ↓
Optimal Solution
Still Possible
```

---

# 8. Optimal Substructure

After selecting one activity, the remaining problem becomes:

```text
Find the Best
Schedule Among
Remaining Compatible
Activities
```

This is simply a smaller instance of the original problem.

Therefore, the problem also satisfies **Optimal Substructure**.

---

# 9. General Algorithm

The Greedy algorithm follows these steps.

```text
Sort Activities
By Finish Time
        ↓
Choose First Activity
        ↓
Discard Incompatible Activities
        ↓
Choose Next Compatible Activity
        ↓
Repeat
```

The schedule is constructed incrementally.

---

# 10. Visual Example

```text
Activities

↓

Sort by Finish Time

↓

Select Earliest Finish

↓

Remove Overlapping Activities

↓

Repeat

↓

Maximum Compatible Schedule
```

Each iteration reduces the remaining search space.

---

# 11. Correctness Intuition

Suppose an optimal schedule begins with a different activity.

If that activity is replaced by the earliest finishing activity:

```text
Optimal Schedule
        ↓
Exchange First Activity
        ↓
Still Optimal
```

The replacement does not reduce the number of compatible activities.

This is the foundation of the **Exchange Argument**.

---

# 12. Complexity Analysis

If the activities are already sorted:

Selection requires:

```text
O(n)
```

If sorting is necessary:

Sorting:

```text
O(n log n)
```

Selection:

```text
O(n)
```

Overall complexity:

```text
O(n log n)
```

Sorting dominates the execution time.

---

# 13. Space Complexity

The Greedy algorithm requires only a few additional variables while scanning the activities.

Auxiliary space:

```text
O(1)
```

excluding the input collection.

This makes the algorithm highly memory-efficient.

---

# 14. Practical Applications

The Activity Selection strategy appears in many scheduling problems.

Examples include:

- meeting scheduling;
- classroom allocation;
- machine scheduling;
- reservation systems;
- conference planning;
- task scheduling.

Many real-world scheduling systems rely on similar Greedy principles.

---

# 15. Advantages

The Greedy solution offers several benefits.

It provides:

- optimal solutions;
- simple implementation;
- linear selection after sorting;
- low memory consumption;
- excellent scalability.

These characteristics make it attractive for production systems.

---

# 16. Limitations

The strategy works because this problem satisfies the required mathematical properties.

Other scheduling problems may include:

- priorities;
- profits;
- deadlines;
- resource constraints.

These additional requirements may invalidate the Greedy Choice Property.

In such cases, Dynamic Programming or other optimization techniques may be required.

---

# 17. Java Perspective

A Java implementation commonly involves:

- storing activities as objects;
- sorting them using `Arrays.sort()` or `Collections.sort()`;
- comparing finish times with a custom comparator;
- scanning the sorted collection once to construct the final schedule.

The algorithm is simple, efficient, and easy to maintain.

---

# 18. Engineering Perspective

Professional software engineers view the Activity Selection Problem as an excellent demonstration of algorithmic reasoning.

The algorithm succeeds because it follows a mathematically justified decision rule.

```text
Optimization Problem
        ↓
Earliest Finish
        ↓
Safe Choice
        ↓
Smaller Problem
        ↓
Optimal Schedule
```

This example illustrates that efficient algorithms depend not only on implementation, but also on proving that each decision preserves optimality.

---

# 19. Relationship with the Next Document

This document presented the Activity Selection Problem as the classical example of the Greedy paradigm.

The next document studies **Interval Scheduling**, a broader family of scheduling problems that extends these ideas to more general interval optimization scenarios.

It demonstrates how Greedy reasoning can be adapted to solve practical scheduling problems encountered in software engineering.

---

# 20. Key Takeaways

After completing this document, the learner should understand that:

- the Activity Selection Problem seeks the maximum number of compatible activities;
- choosing the earliest finishing activity is the optimal Greedy strategy;
- the problem satisfies both the Greedy Choice Property and Optimal Substructure;
- the Exchange Argument provides the intuition for the correctness proof;
- the algorithm runs in `O(n log n)` time when sorting is required;
- the selection phase itself is linear;
- the algorithm requires only constant auxiliary space after sorting;
- Activity Selection is one of the classical examples that demonstrates the power of the Greedy algorithm design paradigm.