# Complexity Analysis

> The [contract and proof map](implementation-contracts.md) supplies the
> sequence arguments and algorithm invariants. Here n is the live element count
> before an operation, c is retained capacity, and construction of a reference
> array counts its c initialised slots.

> Executable scope, API contracts, pseudocode, traces, tests and JMH protocol:
> [Implementation and benchmark guide](implementation-and-benchmark-guide.md).

## Algorithms Java Mastery

Stacks and queues can support constant endpoint work, but the ADT alone does
not guarantee it. The selected representations distinguish individual worst-case
work, amortised sequence bounds and retained capacity. These are algorithmic
cost models, not bounds on JVM allocation, GC or wall-clock latency.

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
| Push without resizing | O(1) |
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
| Enqueue without resizing | O(1) |
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
| Insert | O(1) amortised | O(1) amortised | O(1) | O(1) |
| Remove | O(1) | O(1) | O(1) | O(1) |
| Peek | O(1) | O(1) | O(1) | O(1) |
| Resize | O(n) | O(n) | — | — |

---

The selected `TwoStackQueue` has O(n) worst-case dequeue/peek when transferring,
and O(1) amortised endpoint operations over sequences starting empty. Enqueue
can also grow an incoming array. Transfer accounting and geometric array-growth
accounting are both needed; a constant-cost abstract stack proof alone omits
backing-array copies. A preloaded starting state needs its initial work/credit.

| Additional selected operation | Cost under the reference/pointer model |
|---|---|
| ArrayStack/CircularArrayQueue/BoundedArrayDeque construction | Theta(c) |
| LinkedStack/LinkedQueue empty construction | Theta(1) |
| TwoStackQueue construction | Theta(c_in + c_out) |
| BoundedArrayDeque endpoints | Theta(1), including rejection/sentinels |
| Array-backed clear, including both stacks in TwoStackQueue | Theta(n + 1); retain arrays |
| LinkedStack/LinkedQueue clear | Theta(1) owner work; eventual GC separate |
| Any selected container toList | Theta(n + 1) time and new references |
| Linked container storage | Theta(n + 1) nodes/owner state |
| TwoStackQueue retained storage | Theta(c_in + c_out), including when empty |

For an initially empty growing array with initial capacity c0, m insertions
have O(c0 + m) construction-plus-insertion work. With no automatic shrinking,
current n alone does not bound retained capacity after a large fill and drain.
Diagnostic structure scans are outside ordinary endpoint costs and JMH bodies.

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

- **ArrayDeque** is a useful standard reference with amortised endpoint operations;
  actual performance comparisons require matched workloads and a recorded run.
- Linked implementations are useful when node-based structures or incremental growth are required.

---

# 7. Key Takeaways

After completing this document, the learner should understand that:

- most stack and queue operations execute in **O(1)** time;
- dynamic arrays occasionally require **O(n)** resizing, but insertion remains **O(1) amortized**;
- circular arrays avoid the inefficiency of linear array queues;
- linked implementations eliminate resizing but require additional memory per element;
- selecting the appropriate implementation depends on both algorithmic complexity and practical engineering considerations.
