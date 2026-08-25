# Java Deque

## Algorithms Java Mastery

The **Java Collections Framework (JCF)** provides the `Deque<E>` interface as the recommended abstraction for implementing both **stacks** and **queues** in modern Java.

Unlike legacy classes such as `Stack` or `LinkedList` used directly as queues, `Deque` offers a unified, efficient, and flexible API for operations at both ends of a sequence.

This document introduces the role of `Deque` within the Java Collections Framework and explains why it is the preferred abstraction for implementing LIFO and FIFO behaviours.

---

# 1. Purpose

The purpose of this document is to:

- understand the `Deque<E>` interface;
- identify its principal implementations;
- distinguish stack and queue operations;
- recognise when `ArrayDeque` or `LinkedList` should be used.

---

# 2. The Deque Interface

`Deque` stands for:

```text
Double-Ended Queue
```

It allows operations at both the front and the rear of the structure.

Conceptually:

```text
Front                      Rear

[A] ⇄ [B] ⇄ [C] ⇄ [D]
```

The interface belongs to the Java Collections Framework.

```text
Collection
      │
      └── Queue
             │
             └── Deque
```

---

# 3. Using Deque as a Stack

A deque can behave exactly like a stack.

| Stack Operation | Deque Method |
|-----------------|--------------|
| Push | `push()` |
| Pop | `pop()` |
| Peek | `peek()` |

Example:

```text
Deque<String> stack = new ArrayDeque<>();

stack.push("A");
stack.push("B");
stack.push("C");

System.out.println(stack.pop()); // C
```

The behaviour follows the **LIFO** principle.

---

# 4. Using Deque as a Queue

A deque can also behave as a traditional queue.

| Queue Operation | Deque Method |
|-----------------|--------------|
| Enqueue | `offerLast()` |
| Dequeue | `pollFirst()` |
| Peek | `peekFirst()` |

Example:

```text
Deque<String> queue = new ArrayDeque<>();

queue.offerLast("A");
queue.offerLast("B");
queue.offerLast("C");

System.out.println(queue.pollFirst()); // A
```

The behaviour follows the **FIFO** principle.

---

# 5. Principal Implementations

The most common implementations are:

## ArrayDeque

```text
Deque<Integer> deque = new ArrayDeque<>();
```

Characteristics:

- dynamic circular array;
- very fast insertions and removals;
- excellent cache locality;
- recommended for most applications.

---

## LinkedList

```java
Deque<Integer> deque = new LinkedList<>();
```

Characteristics:

- doubly linked list;
- dynamic growth;
- higher memory overhead;
- generally slower than `ArrayDeque` for stack and queue operations.

---

# 6. ArrayDeque vs LinkedList

| Characteristic | ArrayDeque | LinkedList |
|---------------|------------|------------|
| Internal structure | Dynamic circular array | Doubly linked list |
| Memory overhead | Lower | Higher |
| Cache locality | Better | Weaker |
| Recommended for stacks | ✔ | Possible |
| Recommended for queues | ✔ | Possible |

For most applications, **ArrayDeque** is the preferred choice.

---

# 7. Important Notes

- `Deque` replaces the legacy `Stack` class in modern Java.
- `ArrayDeque` does **not** allow `null` elements.
- All fundamental stack and queue operations execute in **O(1)** under normal conditions.
- The implementation details are hidden behind the `Deque` interface.

---

# 8. Best Practices

Prefer programming against the interface rather than the implementation.

```java
Deque<String> stack = new ArrayDeque<>();
```

instead of

```java
ArrayDeque<String> stack = new ArrayDeque<>();
```

This approach improves:

- flexibility;
- maintainability;
- readability;
- interchangeability of implementations.

---

# 9. Key Takeaways

After completing this document, the learner should understand that:

- `Deque<E>` is the recommended Java abstraction for stacks and queues;
- `ArrayDeque` is generally the preferred implementation;
- the same interface supports both LIFO and FIFO behaviour;
- `LinkedList` also implements `Deque`, but usually with higher memory overhead;
- developers should program against the `Deque` interface rather than concrete implementations whenever possible.