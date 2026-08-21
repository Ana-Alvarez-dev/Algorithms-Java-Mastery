# Common Mistakes

## Algorithms Java Mastery

Tree-based data structures are among the most powerful tools in computer science, but they are also a frequent source of implementation errors. Many of these mistakes arise from misunderstanding the properties of different tree structures or applying the wrong data structure to a particular problem.

Some errors affect correctness, while others significantly reduce performance. Understanding these common mistakes helps developers write more reliable algorithms and make better engineering decisions.

This document presents the most frequent misconceptions and implementation errors related to Binary Trees, Binary Search Trees, Balanced Trees, Heaps, and Java's `PriorityQueue`.

---

# 1. Purpose

The purpose of this document is to:

- identify common mistakes when working with tree structures;
- explain why these mistakes occur;
- present best practices for avoiding them;
- reinforce correct engineering principles.

---

# 2. Confusing a Binary Tree with a Binary Search Tree

One of the most common mistakes is assuming that every Binary Tree is automatically a Binary Search Tree.

Incorrect assumption:

```text
Binary Tree

=

Binary Search Tree
```

A Binary Tree only limits the number of children.

A Binary Search Tree additionally enforces an ordering property:

```text
Left < Root < Right
```

Without this property, efficient searching is impossible.

---

# 3. Assuming Every Search Is O(log n)

Many beginners believe that searching in a Binary Search Tree is always logarithmic.

This is only true when the tree remains balanced.

Example of an unbalanced BST:

```text
10
 \
 20
   \
   30
     \
      40
```

Searching now requires visiting nearly every node.

Actual complexity:

```text
O(n)
```

Always consider the height of the tree.

---

# 4. Ignoring Tree Balance

Repeatedly inserting already sorted values into a Binary Search Tree creates an unbalanced structure.

Example insertion order:

```text
10

20

30

40

50
```

Result:

```text
10
 \
 20
   \
   30
     \
      40
        \
         50
```

Whenever predictable performance is required, prefer self-balancing trees.

---

# 5. Choosing a Heap for Searching

A heap is designed for priority management—not for fast searching.

Incorrect expectation:

```text
Heap

↓

Fast Search
```

Reality:

```text
Heap Search

↓

O(n)
```

Choose a Binary Search Tree when searching is the primary operation.

---

# 6. Expecting a Heap to Produce Sorted Data

A common misconception is that a heap stores all elements in sorted order.

Example Min-Heap:

```text
        10
       /  \
     20    30
    / \
   40 50
```

The root is the smallest value, but the remaining elements are **not globally sorted**.

A heap guarantees only the relationship between parents and children.

---

# 7. Misunderstanding Java PriorityQueue Iteration

Many developers expect iteration over a `PriorityQueue` to return elements in priority order.

Example:

```text
PriorityQueue<Integer> queue = new PriorityQueue<>();
```

Iterating through the queue does **not** guarantee sorted output.

To process elements according to priority, repeatedly call:

```text
poll()
```

instead of relying on the iterator.

---

# 8. Forgetting to Define Ordering for Custom Objects

When storing custom objects inside a `PriorityQueue`, developers often forget to specify how objects should be compared.

This can be solved by:

- implementing `Comparable`;
- providing a `Comparator`.

Without a valid comparison strategy, the priority queue cannot organize elements correctly.

---

# 9. Ignoring Null Values

Java's `PriorityQueue` does **not** allow `null` elements.

Attempting to insert one results in an exception.

Always validate input before insertion.

---

# 10. Choosing the Wrong Tree Structure

Another common mistake is selecting a tree simply because it is familiar.

Instead, choose the structure based on the application's requirements.

| Requirement | Recommended Structure |
|------------|-----------------------|
| Ordered searching | Binary Search Tree |
| Guaranteed logarithmic performance | AVL Tree or Red-Black Tree |
| Priority scheduling | Heap |
| Java priority processing | `PriorityQueue` |

The correct choice depends on the dominant operations performed by the application.

---

# 11. Engineering Best Practices

To avoid these mistakes:

- understand the properties of each tree structure;
- consider the expected workload before selecting a data structure;
- evaluate the effect of tree height on performance;
- use balanced trees for dynamic ordered collections;
- use heaps only for priority-based processing;
- verify the complexity of operations instead of assuming logarithmic performance.

These practices lead to more efficient and maintainable software.

---

# 12. Engineering Perspective

Most performance problems involving tree structures are not caused by implementation errors but by selecting an inappropriate data structure.

Professional software engineers analyze the operations an application performs most frequently before choosing between a Binary Search Tree, a balanced tree, or a heap. This decision often has a greater impact on performance than low-level code optimizations.

Understanding the strengths and limitations of each structure is therefore an essential engineering skill.

---

# 13. Key Takeaways

After completing this document, the learner should understand that:

- a Binary Tree and a Binary Search Tree are different data structures;
- Binary Search Trees only provide logarithmic performance when they remain balanced;
- heaps are optimized for priority access rather than searching;
- Java's `PriorityQueue` does not iterate in priority order;
- custom objects require a comparison strategy to be stored correctly in a priority queue;
- selecting the appropriate tree structure is one of the most important decisions in algorithm design and software engineering.