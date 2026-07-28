# Complexity Analysis

## Algorithms Java Mastery

One of the main advantages of stacks and queues is that their fundamental operations are designed to execute in **constant time**. This efficiency makes them essential building blocks for many algorithms and software systems.

However, the actual performance of an implementation depends not only on the abstract data structure, but also on its internal representation.

This document summarizes the time and space complexity of the most common stack and queue operations.

---

# 1. Purpose

The purpose of this document is to:

- review the complexity of stack and queue operations;
- compare different implementations;
- understand how implementation choices affect performance.

---

# 2. Stack Complexity

## Array-Based Stack

| Operation | Complexity |
|-----------|-----------:|
| Push | O(1) |
| Pop | O(1) |
| Peek | O(1) |
| isEmpty | O(1) |
| Size | O(1) |

If the array grows dynamically:

| Operation | Complexity |
|-----------|-----------:|
| Push (resize) | O(n) |
| Push (amortized) | O(1) |

---

## Linked Stack

| Operation | Complexity |
|-----------|-----------:|
| Push | O(1) |
| Pop | O(1) |
| Peek | O(1) |
| isEmpty | O(1) |
| Size | O(1) |

No resizing is required because nodes are allocated individually.

---

# 3. Queue Complexity

## Linear Array Queue

| Operation | Complexity |
|-----------|-----------:|
| Enqueue | O(1) |
| Dequeue (with shifting) | O(n) |
| Peek | O(1) |

This implementation is generally avoided because removing an element may require shifting all remaining elements.

---

## Circular Array Queue

| Operation | Complexity |
|-----------|-----------:|
| Enqueue | O(1) |
| Dequeue | O(1) |
| Peek | O(1) |
| Resize | O(n) |
| Enqueue (amortized) | O(1) |

Circular arrays eliminate unnecessary element movement and are the preferred array implementation.

---

## Linked Queue

| Operation | Complexity |
|-----------|-----------:|
| Enqueue | O(1) |
| Dequeue | O(1) |
| Peek | O(1) |
| isEmpty | O(1) |
| Size | O(1) |

Maintaining both **front** and **rear** references allows constant-time insertion and removal.

---

# 4. Space Complexity

| Structure | Space Complexity |
|-----------|-----------------:|
| Fixed Array Stack | O(capacity) |
| Dynamic Array Stack | O(capacity) |
| Linked Stack | O(n) |
| Circular Array Queue | O(capacity) |
| Linked Queue | O(n) |

Although linked structures use only the required number of elements, each node introduces additional memory overhead due to references.

---

# 5. Complexity Comparison

| Operation | Array Stack | Circular Queue | Linked Stack | Linked Queue |
|-----------|------------:|---------------:|-------------:|-------------:|
| Insert | O(1) | O(1) | O(1) | O(1) |
| Remove | O(1) | O(1) | O(1) | O(1) |
| Peek | O(1) | O(1) | O(1) | O(1) |
| Resize | O(n) | O(n) | — | — |

---

# 6. Engineering Perspective

When choosing an implementation, complexity is only one factor to consider.

Other important aspects include:

- memory overhead;
- cache locality;
- allocation cost;
- resizing frequency;
- expected data size;
- implementation simplicity.

For most Java applications:

- **ArrayDeque** provides excellent performance for both stacks and queues.
- Linked implementations are useful when node-based structures or incremental growth are required.

---

# 7. Key Takeaways

After completing this document, the learner should understand that:

- most stack and queue operations execute in **O(1)** time;
- dynamic arrays occasionally require **O(n)** resizing, but insertion remains **O(1) amortized**;
- circular arrays avoid the inefficiency of linear array queues;
- linked implementations eliminate resizing but require additional memory per element;
- selecting the appropriate implementation depends on both algorithmic complexity and practical engineering considerations.