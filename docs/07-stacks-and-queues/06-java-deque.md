# Java Deque

> These JCF interfaces are broader than the module's `Stack`, `FifoQueue` and
> `DoubleEndedQueue`. Their shared operation names do not make the educational
> types interchangeable with JCF collections. See the [contract map](implementation-contracts.md).
> Primary API references: [Java 21 Deque](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Deque.html),
> [Queue](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Queue.html)
> and [ArrayDeque](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayDeque.html).

> Executable scope, API contracts, pseudocode, traces, tests and JMH protocol:
> [Implementation and benchmark guide](implementation-and-benchmark-guide.md).

## Algorithms Java Mastery

The **Java Collections Framework (JCF)** provides the `Deque<E>` interface as the recommended abstraction for implementing both **stacks** and **queues** in modern Java.

`Deque` is preferred to the legacy `java.util.Stack` abstraction for LIFO use.
`LinkedList` is a current implementation of `Deque`, not a competing legacy
interface. The interface defines behaviour; the implementation determines costs.

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
- non-null elements;
- amortised constant-time endpoint insertion and removal;
- searches, occurrence removal and bulk operations need separate cost analysis;
- occasional linear growth; individual latency is not guaranteed constant.

---

## LinkedList

```java
Deque<Integer> deque = new LinkedList<>();
```

Characteristics:

- doubly linked list;
- dynamic growth;
- endpoint insertion/removal using constant pointer work;
- node allocation per insertion; exact memory and timing require measurement.

---

# 6. ArrayDeque vs LinkedList

| Characteristic | ArrayDeque | LinkedList |
|---------------|------------|------------|
| Internal structure | Dynamic circular array | Doubly linked list |
| Storage | Backing array with retained capacity | Separate nodes and links |
| Growth | Occasional array allocation/copy | Node allocation per insertion |
| Recommended for stacks | ✔ | Possible |
| Recommended for queues | ✔ | Possible |

The representation suggests different allocation and locality hypotheses.
The [historical timing subset](../17-benchmarking/results/stacks-and-queues/README.md)
records 20 paired-operation configurations on Oracle JDK 21.0.9. It does not
measure heap footprint or isolate allocation/cache causes. Reserved capacity
excludes array growth; queue rotation averages periodic two-stack transfers.
The guide specifies the workload and uncertainty limits.

---

# 7. Important Notes

- `Deque` replaces the legacy `Stack` class in modern Java.
- `ArrayDeque` does **not** allow `null` elements.
- ArrayDeque endpoint operations are **O(1) amortised**, with occasional growth.
- In Java 21, Deque also extends `SequencedCollection`; the diagram above shows
  the Queue relationship rather than the complete interface hierarchy.
- The implementation details are hidden behind the `Deque` interface.

| Intent | Strict operation | Special-value operation |
|---|---|---|
| Insert at an end | `addFirst/addLast`: capacity failure throws | `offerFirst/offerLast`: false on capacity failure |
| Remove at an end | `removeFirst/removeLast`: empty throws | `pollFirst/pollLast`: null when empty |
| Inspect an end | `getFirst/getLast`: empty throws | `peekFirst/peekLast`: null when empty |

Some implementations permit null, including LinkedList. The selected manual
containers reject it so absence sentinels remain unambiguous.

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
- `LinkedList` also implements `Deque`; node/reference fields differ from an
  array representation, while exact heap footprint needs measurement;
- developers should program against the `Deque` interface rather than concrete implementations whenever possible.
