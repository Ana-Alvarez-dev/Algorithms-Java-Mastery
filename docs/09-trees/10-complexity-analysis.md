# Complexity Analysis

## Algorithms Java Mastery

Selecting an appropriate tree data structure requires understanding not only its functionality but also its computational cost. Different tree structures are designed to optimize different operations. Some prioritize fast searching, others provide efficient access to the highest-priority element, while some guarantee predictable performance regardless of insertion order.

Complexity analysis allows software engineers to compare these structures objectively and determine which one best fits a particular problem. Rather than asking which tree is "better," the correct question is **which tree is most appropriate for the required operations**.

This document compares the computational complexity of the principal tree structures studied in this module and explains the trade-offs involved in their practical use.

---

# 1. Purpose

The purpose of this document is to:

- compare the computational complexity of tree-based data structures;
- analyze the cost of common tree operations;
- understand how tree height affects performance;
- identify the strengths and weaknesses of each structure.

---

# 2. Why Complexity Matters

The performance of a tree depends primarily on its height.

As the height increases, operations require more comparisons.

Conceptually:

```text
Greater Height

↓

More Comparisons

↓

Slower Operations
```

Balanced structures minimize tree height, leading to more efficient algorithms.

---

# 3. Binary Tree

A general Binary Tree does not enforce any ordering among its elements.

Searching requires visiting nodes until the desired value is found.

| Operation | Complexity |
|-----------|-----------:|
| Search | O(n) |
| Insert | O(n)* |
| Delete | O(n) |

\*Insertion complexity depends on the specific implementation because Binary Trees do not define a unique insertion strategy.

---

# 4. Binary Search Tree

A Binary Search Tree (BST) maintains an ordering property that enables efficient searching.

### Balanced BST

| Operation | Complexity |
|-----------|-----------:|
| Search | O(log n) |
| Insert | O(log n) |
| Delete | O(log n) |

### Unbalanced BST

| Operation | Complexity |
|-----------|-----------:|
| Search | O(n) |
| Insert | O(n) |
| Delete | O(n) |

The performance of a BST depends entirely on its height.

---

# 5. Balanced Trees

Self-balancing trees automatically maintain logarithmic height.

Examples include:

- AVL Trees;
- Red-Black Trees;
- B-Trees.

| Operation | Complexity |
|-----------|-----------:|
| Search | O(log n) |
| Insert | O(log n) |
| Delete | O(log n) |

These guarantees remain valid regardless of insertion order.

---

# 6. Heaps

Heaps are optimized for priority-based processing rather than searching.

| Operation | Complexity |
|-----------|-----------:|
| Peek Root | O(1) |
| Insert | O(log n) |
| Extract Root | O(log n) |
| Search | O(n) |

Searching is inefficient because heaps do not maintain global ordering.

---

# 7. Java PriorityQueue

Java's `PriorityQueue` is implemented internally as a binary heap.

| Operation | Complexity |
|-----------|-----------:|
| `peek()` | O(1) |
| `add()` | O(log n) |
| `offer()` | O(log n) |
| `poll()` | O(log n) |
| `contains()` | O(n) |
| `remove(Object)` | O(n) |

Its complexity characteristics are therefore identical to those of a binary heap.

---

# 8. Overall Comparison

| Data Structure | Search | Insert | Delete / Remove | Root Access |
|---------------|-------:|-------:|----------------:|------------:|
| Binary Tree | O(n) | O(n)* | O(n) | O(1) |
| Binary Search Tree (Balanced) | O(log n) | O(log n) | O(log n) | O(1) |
| Binary Search Tree (Worst Case) | O(n) | O(n) | O(n) | O(1) |
| Balanced Tree | O(log n) | O(log n) | O(log n) | O(1) |
| Heap | O(n) | O(log n) | O(log n) | O(1) |
| Java `PriorityQueue` | O(n) | O(log n) | O(log n) | O(1) |

This comparison illustrates that no single structure is optimal for every operation.

---

# 9. Choosing the Appropriate Structure

Different problems require different tree structures.

Use a **Binary Search Tree** when:

- ordered searching is required;
- elements must remain sorted.

Use a **Balanced Tree** when:

- predictable logarithmic performance is essential;
- insertions and deletions occur frequently.

Use a **Heap** or **PriorityQueue** when:

- the next highest- or lowest-priority element must always be retrieved efficiently;
- priority scheduling is more important than searching.

Selecting the appropriate structure improves both performance and maintainability.

---

# 10. Engineering Perspective

Complexity analysis demonstrates that the efficiency of an algorithm depends not only on the algorithm itself but also on the underlying data structure.

Professional software engineers evaluate expected workloads before choosing a collection. For example, an application that performs millions of search operations benefits from balanced search trees, while a scheduling system naturally relies on heaps or priority queues.

Understanding these trade-offs enables developers to build systems that remain efficient as data volumes increase.

---

# 11. Relationship with the Next Document

This document concludes the study of the principal tree structures and their computational characteristics.

The next document presents **common algorithms based on trees**, demonstrating how these data structures are applied to practical problems such as searching, scheduling, graph processing, expression evaluation, and hierarchical data management.

---

# 12. Key Takeaways

After completing this document, the learner should understand that:

- the height of a tree largely determines the efficiency of its operations;
- Binary Search Trees provide efficient searching only when they remain balanced;
- self-balancing trees guarantee **O(log n)** search, insertion, and deletion;
- heaps optimize priority-based operations rather than searching;
- Java's `PriorityQueue` inherits the complexity characteristics of binary heaps;
- choosing the correct data structure depends on the operations an application performs most frequently rather than on a single notion of overall performance.