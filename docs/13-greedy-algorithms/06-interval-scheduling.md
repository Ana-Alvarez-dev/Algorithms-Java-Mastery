# Interval Scheduling

## Algorithms Java Mastery

The **Interval Scheduling Problem** is a classical optimization problem that extends the ideas introduced in the Activity Selection Problem. Given a collection of intervals, the objective is to select the largest possible subset of **non-overlapping intervals**.

Although the terminology differs, the mathematical problem is essentially the same: every interval occupies a period of time, and two intervals are considered compatible if they do not overlap.

This problem demonstrates one of the greatest strengths of the Greedy paradigm. A simple local decision—always selecting the interval that finishes earliest—can be formally proven to produce a globally optimal solution.

Interval Scheduling appears in numerous real-world systems, including operating systems, reservation platforms, cloud computing, network resource allocation, and project management software.

---

# 1. Purpose

The purpose of this document is to:

- introduce the Interval Scheduling Problem;
- explain why it is a Greedy optimization problem;
- analyze the interval selection strategy;
- understand its correctness;
- evaluate its computational complexity;
- explore practical software engineering applications.

---

# 2. Problem Statement

Given:

- a collection of time intervals;

Goal:

```text
Select the Maximum
Number of
Non-Overlapping
Intervals
```

Each selected interval must be compatible with every other selected interval.

---

# 3. Interval Representation

Each interval consists of:

```text
(Start Time,
 End Time)
```

Example:

```text
(1,4)

(3,5)

(5,7)

(8,10)
```

The objective is to build the largest compatible subset.

---

# 4. Compatibility

Two intervals are compatible when they do not overlap.

Example:

```text
(1,4)

↓

(5,8)
```

Compatible.

Example:

```text
(1,4)

↓

(3,6)
```

Not compatible.

Compatibility is determined by comparing the finishing time of one interval with the starting time of the next.

---

# 5. Why Brute Force Is Inefficient

One possible solution is:

```text
Generate Every
Possible Subset

↓

Verify Compatibility

↓

Choose Largest
Compatible Set
```

Although correct, this approach becomes computationally impractical for large inputs.

---

# 6. Greedy Strategy

The Greedy algorithm always selects:

```text
The Interval
That Finishes
Earliest
```

Once selected:

- every overlapping interval is discarded;
- the process repeats with the remaining compatible intervals.

---

# 7. Why Earliest Finish Time?

Choosing the interval that finishes first leaves the greatest amount of time available for future intervals.

Conceptually:

```text
Earlier Finish
        ↓
More Available Time
        ↓
More Possible Choices
```

This strategy maximizes future scheduling opportunities.

---

# 8. Algorithm Overview

The algorithm follows these steps.

```text
Sort Intervals
By Finish Time
        ↓
Choose First Interval
        ↓
Discard Overlapping Intervals
        ↓
Choose Next Compatible Interval
        ↓
Repeat
```

Each decision is permanent.

---

# 9. Greedy Choice Property

The Interval Scheduling Problem satisfies the **Greedy Choice Property**.

Selecting the earliest finishing interval never decreases the size of the optimal solution.

Conceptually:

```text
Earliest Finish
        ↓
Safe Choice
        ↓
Optimal Schedule
```

This allows the algorithm to make irreversible decisions confidently.

---

# 10. Optimal Substructure

After selecting one interval:

```text
Remaining Compatible
Intervals
```

form a smaller scheduling problem identical to the original.

Therefore:

```text
Optimal Problem
        ↓
Optimal Remaining
Problem
```

The problem satisfies **Optimal Substructure**.

---

# 11. Correctness Intuition

Suppose an optimal schedule begins with a different interval.

If that interval is exchanged with the earliest finishing interval:

```text
Optimal Schedule
        ↓
Exchange
        ↓
Still Optimal
```

The number of selected intervals does not decrease.

This reasoning forms the basis of the correctness proof.

---

# 12. Complexity Analysis

If the intervals are already sorted:

Selection:

```text
O(n)
```

If sorting is required:

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

The algorithm scans the sorted intervals while maintaining only a small amount of additional information.

Auxiliary space:

```text
O(1)
```

excluding the input collection.

---

# 14. Comparison with Activity Selection

| Activity Selection | Interval Scheduling |
|--------------------|--------------------|
| Activities | Time intervals |
| Earliest finish rule | Earliest finish rule |
| Maximize compatible activities | Maximize compatible intervals |
| Greedy solution | Greedy solution |

The two problems are mathematically equivalent and differ primarily in terminology and application context.

---

# 15. Practical Applications

Interval Scheduling appears in numerous software systems.

Examples include:

- meeting schedulers;
- operating system process scheduling;
- classroom reservations;
- cloud resource allocation;
- CPU scheduling;
- calendar applications;
- booking systems.

Efficient interval selection improves resource utilization.

---

# 16. Advantages

The Greedy solution offers several benefits.

It provides:

- optimal scheduling;
- efficient execution;
- low memory usage;
- simple implementation;
- excellent scalability.

These characteristics make it highly practical.

---

# 17. Limitations

The strategy works only for the classical Interval Scheduling Problem.

Additional constraints such as:

- interval weights;
- priorities;
- profits;
- multiple resources;
- deadlines;

may invalidate the Greedy approach.

Such problems often require Dynamic Programming or other optimization techniques.

---

# 18. Java Perspective

A Java implementation commonly includes:

- storing intervals as objects or records;
- sorting by finish time using `Comparator`;
- scanning the sorted list once;
- selecting compatible intervals incrementally.

The implementation is concise and efficient.

---

# 19. Engineering Perspective

Interval Scheduling illustrates an important engineering principle.

```text
Sort by Finish Time
        ↓
Choose Safe Interval
        ↓
Reduce Remaining Problem
        ↓
Repeat
```

The algorithm achieves optimal scheduling through mathematically justified local decisions rather than exhaustive search.

Its simplicity, efficiency, and scalability explain why similar scheduling strategies are widely used in modern software systems.

---

# 20. Relationship with the Next Document

This document studied the Interval Scheduling Problem as another classical application of the Greedy paradigm.

The next document introduces **Huffman Coding**, demonstrating how Greedy algorithms can also solve optimization problems involving binary trees and data compression rather than scheduling.

This broadens the learner's understanding of how the Greedy paradigm applies across different domains of Computer Science.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- Interval Scheduling seeks the maximum number of compatible time intervals;
- selecting the earliest finishing interval is the optimal Greedy strategy;
- the problem satisfies both the Greedy Choice Property and Optimal Substructure;
- sorting by finish time enables efficient interval selection;
- the algorithm runs in **O(n log n)** time when sorting is required;
- the selection phase is linear;
- the algorithm requires only constant auxiliary space after sorting;
- Interval Scheduling demonstrates how simple, locally optimal decisions can produce globally optimal scheduling solutions.