# Complexity Analysis

## Algorithms Java Mastery

The primary objective of algorithm analysis is not only to determine whether a Greedy algorithm produces an optimal solution, but also to understand **how efficiently** it does so as the input size grows.

Unlike Divide and Conquer algorithms, whose complexity is often described using **recurrence relations**, Greedy algorithms are generally iterative. Their computational complexity is determined by operations such as:

- sorting;
- selecting the next candidate;
- maintaining priority queues;
- scanning collections.

For this reason, the efficiency of a Greedy algorithm depends not only on its decision strategy but also on the data structures used during execution.

The algorithms studied in this module illustrate this principle. Activity Selection and Interval Scheduling achieve their efficiency after sorting the input, while Huffman Coding relies on a **Priority Queue (Min Heap)** to repeatedly obtain the smallest frequencies efficiently.

Understanding these computational characteristics allows software engineers to select the most appropriate Greedy solution for a particular engineering problem.

---

# 1. Purpose

The purpose of this document is to:

- analyze the computational complexity of Greedy algorithms;
- compare the algorithms studied in this module;
- evaluate time and auxiliary space complexity;
- understand the influence of data structures;
- discuss engineering trade-offs.

---

# 2. Complexity in Greedy Algorithms

Most Greedy algorithms follow the same high-level process.

```text
Prepare Input
        ↓
Select Best Candidate
        ↓
Update Solution
        ↓
Repeat
```

Their running time is determined by:

- preprocessing;
- candidate selection;
- data structure operations.

---

# 3. Sorting Cost

Many Greedy algorithms begin by sorting the input.

Typical complexity:

```text
O(n log n)
```

After sorting:

```text
Linear Scan

↓

O(n)
```

Consequently, sorting usually dominates the total execution time.

---

# 4. Activity Selection

Execution process:

```text
Sort Activities
        ↓
Select Compatible Activities
```

Time complexity:

| Operation | Complexity |
|-----------|-----------:|
| Sorting | O(n log n) |
| Selection | O(n) |

Overall:

```text
O(n log n)
```

Auxiliary space:

```text
O(1)
```

excluding the input collection.

---

# 5. Interval Scheduling

Execution process:

```text
Sort Intervals
        ↓
Select Compatible Intervals
```

Time complexity:

| Operation | Complexity |
|-----------|-----------:|
| Sorting | O(n log n) |
| Selection | O(n) |

Overall:

```text
O(n log n)
```

Auxiliary space:

```text
O(1)
```

excluding the input.

---

# 6. Huffman Coding

Execution process:

```text
Frequency Analysis
        ↓
Priority Queue
        ↓
Repeated Merges
```

Time complexity:

| Operation | Complexity |
|-----------|-----------:|
| Build Priority Queue | O(n) |
| Merge Operations | O(n log n) |

Overall:

```text
O(n log n)
```

Auxiliary space:

```text
O(n)
```

because the algorithm stores the Huffman tree and the Priority Queue.

---

# 7. Time Complexity Comparison

| Algorithm | Time Complexity |
|------------|----------------:|
| Activity Selection | O(n log n) |
| Interval Scheduling | O(n log n) |
| Huffman Coding | O(n log n) |

Although all three algorithms share the same asymptotic complexity, they achieve it through different operations.

---

# 8. Auxiliary Space Comparison

| Algorithm | Auxiliary Space |
|------------|----------------:|
| Activity Selection | O(1) |
| Interval Scheduling | O(1) |
| Huffman Coding | O(n) |

Scheduling algorithms require very little additional memory, while Huffman Coding must maintain an explicit binary tree.

---

# 9. Role of Data Structures

The efficiency of Greedy algorithms depends heavily on selecting appropriate data structures.

Examples include:

| Problem | Primary Data Structure |
|----------|------------------------|
| Activity Selection | Sorted Array/List |
| Interval Scheduling | Sorted Array/List |
| Huffman Coding | Priority Queue (Min Heap) |

The Greedy strategy alone is insufficient without efficient supporting structures.

---

# 10. Preprocessing versus Decision Cost

Many Greedy algorithms spend most of their execution time preparing the input.

Conceptually:

```text
Sorting

↓

Greedy Decisions

↓

Fast Execution
```

After preprocessing, each Greedy decision is typically inexpensive.

---

# 11. Scalability

As input size increases:

- sorting scales as **O(n log n)**;
- linear scanning scales as **O(n)**;
- heap operations scale as **O(log n)**.

These growth rates allow Greedy algorithms to remain efficient for large datasets.

---

# 12. Practical Performance

Although asymptotic complexity is important, practical performance also depends on:

- implementation quality;
- cache locality;
- constant factors;
- memory allocation;
- data structure efficiency.

Two algorithms with the same Big-O complexity may perform differently in practice.

---

# 13. Engineering Trade-Offs

| Criterion | Scheduling Algorithms | Huffman Coding |
|-----------|----------------------|---------------|
| Main Cost | Sorting | Priority Queue |
| Auxiliary Memory | Very Low | Moderate |
| Scalability | Excellent | Excellent |
| Implementation | Simple | More Complex |

Selecting an algorithm depends on the characteristics of the problem rather than complexity alone.

---

# 14. Comparison with Previous Paradigms

| Paradigm | Typical Complexity Driver |
|-----------|--------------------------|
| Divide and Conquer | Recursive decomposition |
| Greedy | Sorting and local decisions |
| Dynamic Programming | State exploration and memoization |

Each paradigm achieves efficiency through a different strategy.

---

# 15. Common Complexity Misconceptions

## "Greedy algorithms are always O(n)."

Incorrect.

Many Greedy algorithms require an initial sorting step.

Overall complexity:

```text
O(n log n)
```

---

## "The Greedy decision is the expensive part."

Usually false.

The decision itself is often constant time.

Sorting or maintaining a Priority Queue generally dominates execution time.

---

## "Algorithms with the same Big-O always perform equally."

Incorrect.

Memory usage, implementation details, and constant factors influence real-world performance.

---

# 16. Java Perspective

Java provides several efficient data structures commonly used by Greedy algorithms.

Examples include:

- `Arrays.sort()`;
- `Collections.sort()`;
- `PriorityQueue`;
- `Comparator`;
- `ArrayList`.

Using the appropriate Java collection often determines the practical performance of the implementation.

---

# 17. Engineering Perspective

Professional software engineers evaluate Greedy algorithms according to multiple criteria.

```text
Correctness
        ↓
Time Complexity
        ↓
Memory Usage
        ↓
Data Structures
        ↓
Scalability
        ↓
Maintainability
```

Big-O notation provides valuable insight, but engineering decisions also consider implementation simplicity, available memory, and expected input characteristics.

---

# 18. Relationship with the Next Document

This document summarized the computational characteristics of the Greedy algorithms studied throughout the module.

The next document, **Common Mistakes**, examines the conceptual and implementation errors frequently encountered when designing Greedy algorithms, including incorrect selection criteria, invalid assumptions about optimality, and misuse of supporting data structures.

---

# 19. Key Takeaways

After completing this document, the learner should understand that:

- Greedy algorithm complexity is usually determined by preprocessing and efficient candidate selection;
- sorting often dominates the running time of scheduling algorithms;
- Huffman Coding relies on a Priority Queue to achieve efficient execution;
- Activity Selection and Interval Scheduling require only constant auxiliary space after sorting;
- Huffman Coding requires linear auxiliary space because of the Huffman tree and Priority Queue;
- algorithms with identical asymptotic complexity may have different practical performance;
- selecting appropriate data structures is essential for efficient Greedy implementations;
- professional software engineers evaluate Greedy algorithms according to correctness, complexity, memory usage, scalability, and implementation quality.