# Heaps

## Algorithms Java Mastery

A **Heap** is a specialized tree-based data structure designed to efficiently manage priorities rather than maintain a fully ordered collection of elements. Unlike a Binary Search Tree (BST), which organizes data to support efficient searching, a heap guarantees that the element with the highest or lowest priority is always located at the root.

Heaps are fundamental to many algorithms and systems. They are widely used to implement priority queues, scheduling algorithms, graph algorithms such as Dijkstra's and Prim's, event simulation systems, memory management, and numerous backend applications where processing elements according to priority is essential.

This document introduces the principles of heap data structures, explains their properties, and demonstrates why they are among the most important specialized tree structures in computer science.

---

# 1. Purpose

The purpose of this document is to:

- introduce heap data structures;
- explain the heap property;
- distinguish Min-Heaps and Max-Heaps;
- understand how heaps differ from Binary Search Trees;
- prepare the learner for Java's `PriorityQueue`.

---

# 2. What Is a Heap?

A **Heap** is a **Complete Binary Tree** that satisfies a specific ordering rule known as the **Heap Property**.

Unlike a Binary Search Tree, a heap does **not** maintain a complete ordering among all elements.

Instead, it guarantees that the root always contains either the smallest or the largest value.

Conceptually:

```text
          Root
         /    \
      Child  Child
```

Only the relationship between each parent and its children is guaranteed.

---

# 3. The Heap Property

Every heap satisfies one of two possible ordering rules.

### Min-Heap

Every parent is less than or equal to its children.

Example:

```text
        10
       /  \
     20    30
    / \
   40 50
```

The smallest value is always located at the root.

---

### Max-Heap

Every parent is greater than or equal to its children.

Example:

```text
        90
       /  \
     70    60
    / \
   50 40
```

The largest value is always located at the root.

---

# 4. Complete Binary Tree Requirement

A heap must always be a **Complete Binary Tree**.

This means:

- every level is completely filled except possibly the last;
- the last level is filled from left to right.

Example:

```text
        A
       / \
      B   C
     / \ /
    D  E F
```

This property allows heaps to be stored efficiently in arrays.

---

# 5. Heap Representation

Unlike most Binary Trees, heaps are typically stored in an **array** rather than as linked nodes.

Example:

```text
Tree

        10
       /  \
     20    30
    / \
   40 50
```

Array representation:

```text
[10, 20, 30, 40, 50]
```

This representation eliminates the need for explicit node references and improves memory efficiency.

---

# 6. Fundamental Operations

The principal heap operations include:

| Operation | Description |
|-----------|-------------|
| Insert | Adds a new element while preserving the heap property |
| Peek | Returns the root element without removing it |
| Extract | Removes the root and restores the heap property |
| Heapify | Restores the heap after modifications |

These operations ensure that the heap property is maintained after every update.

---

# 7. Heapify

After inserting or removing an element, the heap may temporarily violate the heap property.

The **heapify** operation reorganizes the affected portion of the tree.

Conceptually:

```text
Insert

↓

Heap Property Violated

↓

Heapify

↓

Heap Property Restored
```

Heapify is the fundamental operation that maintains heap correctness.

---

# 8. Complexity Analysis

For a heap containing **n** elements:

| Operation | Complexity |
|-----------|-----------:|
| Peek | O(1) |
| Insert | O(log n) |
| Extract Root | O(log n) |
| Heapify | O(log n) |
| Build Heap | O(n) |

The logarithmic complexity results from the height of the complete binary tree.

---

# 9. Heap vs Binary Search Tree

Although both are tree-based structures, they serve different purposes.

| Heap | Binary Search Tree |
|------|--------------------|
| Guarantees priority at the root | Guarantees ordered searching |
| Root contains minimum or maximum | Every subtree satisfies BST ordering |
| Efficient priority access | Efficient searching |
| Usually stored in an array | Usually implemented with linked nodes |

Choosing between them depends on the problem requirements.

---

# 10. Common Applications

Heaps are widely used in:

- priority queues;
- operating system schedulers;
- task scheduling;
- event simulation;
- Dijkstra's shortest-path algorithm;
- Prim's minimum spanning tree algorithm;
- Heap Sort;
- memory management systems.

Whenever elements must be processed according to priority, heaps are often the preferred data structure.

---

# 11. Engineering Perspective

Heaps illustrate an important principle of data structure design:

> **Different problems require different forms of organization.**

A Binary Search Tree organizes data for efficient searching, while a heap organizes data so that the highest- or lowest-priority element is always immediately accessible.

Because of their predictable logarithmic performance and efficient array representation, heaps are widely used in modern software systems, particularly in scheduling, graph processing, resource management, and backend services.

---

# 12. Relationship with the Next Document

This document introduced the theoretical foundations of heap data structures.

The next document examines **Java's `PriorityQueue`**, which implements a priority queue using a heap internally. Understanding heap behavior makes it easier to reason about the performance and implementation of one of the most important collections in the Java Collections Framework.

---

# 13. Key Takeaways

After completing this document, the learner should understand that:

- a heap is a Complete Binary Tree that satisfies the heap property;
- Min-Heaps and Max-Heaps differ in whether the smallest or largest element is stored at the root;
- heaps are typically represented using arrays rather than linked nodes;
- heap operations rely on the heapify procedure to preserve correctness;
- heaps provide **O(1)** access to the highest-priority element and **O(log n)** insertion and removal;
- heaps are fundamental to priority queues, scheduling algorithms, graph algorithms, and many high-performance software systems.