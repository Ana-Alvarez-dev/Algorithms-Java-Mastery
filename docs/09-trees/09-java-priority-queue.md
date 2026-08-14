# Java PriorityQueue

## Algorithms Java Mastery

Java provides a built-in implementation of a **priority queue** through the `PriorityQueue` class, which is part of the **Java Collections Framework**. Unlike traditional queues that process elements according to their insertion order, a priority queue processes elements according to their priority.

Internally, Java's `PriorityQueue` is implemented using a **binary heap**, allowing efficient insertion and removal while ensuring that the highest-priority element is always accessible. By default, it behaves as a **Min-Heap**, meaning the smallest element has the highest priority.

This document introduces Java's `PriorityQueue`, explains its internal behavior, presents its most important operations, and discusses the situations in which it is the appropriate data structure.

---

# 1. Purpose

The purpose of this document is to:

- introduce Java's `PriorityQueue`;
- explain its heap-based implementation;
- understand its fundamental operations;
- analyze its computational complexity;
- identify practical applications and best practices.

---

# 2. What Is a PriorityQueue?

A `PriorityQueue` is a collection that stores elements according to their **priority** instead of their insertion order.

By default:

- the smallest element has the highest priority;
- elements are automatically organized using a binary heap;
- the root of the heap is always the next element to be removed.

Conceptually:

```text
Insert Elements

↓

Heap Organization

↓

Smallest Element at Root

↓

Remove Highest Priority
```

Unlike a standard queue, elements are **not** retrieved in FIFO order.

---

# 3. Internal Implementation

Java implements `PriorityQueue` using a **binary heap** stored internally as an array.

This implementation provides:

- efficient insertion;
- efficient removal of the root element;
- automatic maintenance of the heap property.

Developers do not need to manually rebalance the structure, as all heap operations are handled internally.

---

# 4. Creating a PriorityQueue

A priority queue can be created using its default constructor.

```text
PriorityQueue<Integer> queue = new PriorityQueue<>();
```

This creates a **Min-Heap**, where the smallest value has the highest priority.

Example:

```text
queue.add(40);
queue.add(10);
queue.add(30);
queue.add(20);
```

Although the insertion order is:

```text
40, 10, 30, 20
```

the first element removed will be:

```text
10
```

because it has the highest priority.

---

# 5. Common Operations

The most frequently used operations are:

| Method | Description |
|--------|-------------|
| `add()` | Inserts an element |
| `offer()` | Inserts an element (returns `false` instead of throwing an exception when insertion fails) |
| `peek()` | Returns the highest-priority element without removing it |
| `poll()` | Removes and returns the highest-priority element |
| `remove()` | Removes a specific element or the root depending on usage |
| `contains()` | Checks whether an element exists |
| `size()` | Returns the number of elements |
| `isEmpty()` | Checks whether the queue is empty |

---

# 6. Ordering Elements

By default, Java uses the **natural ordering** of elements.

Example:

```text
10

↓

20

↓

30

↓

40
```

For custom objects, ordering can be defined by:

- implementing the `Comparable` interface;
- supplying a `Comparator` when creating the queue.

This allows developers to prioritize objects according to application-specific rules.

---

# 7. Creating a Max-Heap

Java's `PriorityQueue` is a Min-Heap by default.

A Max-Heap can be created by supplying a reverse comparator.

Example:

```java
PriorityQueue<Integer> queue =
    new PriorityQueue<>(Comparator.reverseOrder());
```

Now the largest value has the highest priority.

Example removal order:

```text
90

↓

70

↓

50

↓

20
```

---

# 8. Complexity Analysis

For a priority queue containing **n** elements:

| Operation | Complexity |
|-----------|-----------:|
| `peek()` | O(1) |
| `add()` | O(log n) |
| `offer()` | O(log n) |
| `poll()` | O(log n) |
| `remove(Object)` | O(n) |
| `contains()` | O(n) |

The logarithmic complexity is achieved because the internal heap maintains a height proportional to **log n**.

---

# 9. Common Applications

Java's `PriorityQueue` is commonly used in:

- task scheduling;
- event processing;
- shortest-path algorithms;
- graph algorithms;
- job scheduling;
- simulation systems;
- network routing;
- resource allocation.

Any application that repeatedly processes the highest-priority element can benefit from this data structure.

---

# 10. Best Practices

When using `PriorityQueue`, consider the following recommendations:

- use it only when priority ordering is required;
- use `peek()` to inspect the next element without removing it;
- implement `Comparable` or provide a `Comparator` for custom objects;
- remember that iteration does **not** return elements in priority order;
- use repeated `poll()` operations when elements must be processed according to priority.

Following these practices improves both correctness and readability.

---

# 11. Engineering Perspective

The `PriorityQueue` demonstrates how theoretical data structures are transformed into practical software engineering tools.

Although developers interact with a simple collection interface, the class internally relies on a binary heap to guarantee efficient insertion and removal. This abstraction allows programmers to focus on solving application problems rather than implementing complex balancing algorithms.

Understanding the relationship between heaps and `PriorityQueue` also helps explain why operations such as insertion and removal are logarithmic while retrieving the next element is performed in constant time.

---

# 12. Relationship with the Next Document

This document presented Java's heap-based implementation of priority queues.

The next document analyzes the **computational complexity of tree data structures**, comparing Binary Trees, Binary Search Trees, Balanced Trees, Heaps, and Java's `PriorityQueue` to understand the performance trade-offs among these structures.

---

# 13. Key Takeaways

After completing this document, the learner should understand that:

- Java's `PriorityQueue` is implemented internally using a binary heap;
- by default, it behaves as a Min-Heap;
- custom ordering can be achieved through `Comparable` or `Comparator`;
- insertion and removal require **O(log n)** time, while accessing the next element requires **O(1)** time;
- `PriorityQueue` is the preferred Java collection for implementing priority-based processing;
- understanding heaps provides the theoretical foundation for effectively using `PriorityQueue` in real-world software systems.