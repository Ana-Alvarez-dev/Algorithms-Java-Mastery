# Queue Implementations

## Algorithms Java Mastery

A queue is defined by its **First In, First Out (FIFO)** behaviour rather than
by the way its elements are stored in memory.

The same abstract queue can therefore be implemented using different physical
representations while preserving the same behavioural contract.

The most common implementation strategies are:

```text
Linear Array Queue
```

```text
Circular Array Queue
```

```text
Linked Queue
```

Each implementation supports the same conceptual operations:

- enqueue;
- dequeue;
- peek;
- isEmpty;
- size.

However, they differ significantly in:

- memory organisation;
- capacity management;
- boundary representation;
- allocation behaviour;
- cache locality;
- implementation complexity;
- practical performance.

Understanding these differences is essential because selecting a queue
implementation is an engineering decision rather than simply a programming
exercise.

This document studies the principal queue implementations before introducing
their corresponding Java abstractions.

---

# 1. Academic Foundation

A queue is an abstract data type.

Its behaviour is defined by the FIFO principle:

```text
First In
    ↓
First Out
```

The abstract data type specifies:

- where insertion occurs;
- where removal occurs;
- which element becomes accessible next;
- the ordering policy that must always be preserved.

The implementation specifies:

- how elements are stored;
- how the front and rear are represented;
- how capacity is managed;
- how memory is allocated;
- how operations achieve their required complexity.

Conceptually:

```text
Queue Abstract Data Type
          ↓
Defines behaviour
          ↓
enqueue
dequeue
peek
isEmpty
size
```

```text
Queue Implementation
          ↓
Defines representation
          ↓
Linear Array
Circular Array
Linked Nodes
```

Every correct implementation must preserve FIFO behaviour regardless of its
internal organisation.

The explanations developed in this repository are original and based on the
academic direction of **Algorithms Java Mastery**.

---

# 2. Purpose

The purpose of this document is to analyse the principal implementation
strategies for queues.

The learner will study:

- linear arrays;
- circular arrays;
- linked queues;
- front and rear management;
- capacity growth;
- resizing;
- structural invariants;
- correctness reasoning;
- computational complexity;
- engineering trade-offs.

The objective is to understand **why** each implementation works and under
which circumstances it should be selected.

---

# 3. Learning Objectives

After completing this document, the learner should be able to:

### Conceptual understanding

- distinguish queue behaviour from queue representation;
- explain why circular queues exist;
- identify front and rear in each implementation;
- compare array and linked representations.

### Algorithmic reasoning

- implement enqueue and dequeue conceptually;
- update front and rear correctly;
- preserve FIFO order;
- reason about wrap-around.

### Correctness reasoning

- define implementation invariants;
- preserve front and rear consistency;
- reason about resizing;
- analyse boundary conditions.

### Complexity analysis

- derive operation complexity;
- explain amortised enqueue;
- compare implementations;
- recognise inefficient designs.

### Engineering perspective

- evaluate memory usage;
- compare allocation strategies;
- select an implementation according to requirements;
- design representative automated tests.

---

# 4. Implementation Requirements

Every queue implementation must preserve the same behavioural contract.

Given the insertion sequence:

```text
A, B, C, D
```

repeated removal must produce:

```text
A, B, C, D
```

The implementation must guarantee:

```text
Front
 ↓
Next element removed

Rear
 ↓
Most recently inserted element
```

Regardless of representation, every implementation supports:

| Operation | Required Behaviour |
|-----------|--------------------|
| enqueue(x) | Insert x at the rear |
| dequeue() | Remove the front |
| peek() | Read the front |
| isEmpty() | Determine whether no elements exist |
| size() | Return the number of elements |

---

# 5. Representation Alternatives

The principal implementations are:

```text
Queue
├── Linear Array Queue
├── Circular Array Queue
└── Linked Queue
```

All three represent the same logical sequence.

Logical queue:

```text
Front                     Rear

A → B → C → D
```

Linear array:

```text
Index

0   1   2   3   4   5

[A] [B] [C] [D] [_] [_]
```

Circular array:

```text
0   1   2   3   4   5

[D] [E] [_] [_] [B] [C]
```

Linked queue:

```text
Front
 ↓
[A] → [B] → [C] → [D] → null
                              ↑
                            Rear
```

---

# 6. Linear Array Queue

A linear array stores elements contiguously.

Conceptually:

```text
Front                     Rear

[A] [B] [C] [_] [_]
```

Insertion occurs at the rear.

Removal occurs at the front.

Initially this appears similar to an array stack.

However, a fundamental problem soon appears.

---

# 7. The Shifting Problem

Suppose:

```text
[A] [B] [C] [D]
```

Removing A produces:

```text
[B] [C] [D]
```

A naïve implementation shifts every remaining element.

```text
Before

[A] [B] [C] [D]
```

```text
After

[B] [C] [D] [_]
```

This requires moving:

- B
- C
- D

Therefore:

```text
dequeue = O(n)
```

The queue abstraction expects constant-time removal.

Therefore this implementation is inefficient.

---

# 8. Front Index Without Shifting

Instead of moving elements, the implementation may move only the front index.

Example:

```text
Index

0 1 2 3 4 5

[A][B][C][D][_][_]
 ↑
Front
```

After removing A:

```text
Index

0 1 2 3 4 5

[A][B][C][D][_][_]
    ↑
 Front
```

The logical queue becomes:

```text
B → C → D
```

No element moved.

Only the front boundary changed.

---

# 9. Remaining Problem

Although shifting is eliminated, released positions cannot be reused.

Example:

```text
Capacity = 6
```

After several operations:

```text
Index

0 1 2 3 4 5

[_][_][C][D][E][F]
          ↑
       Front
             ↑
            Rear
```

After inserting another element:

```text
Rear = 6
```

No array position exists.

Even though indices 0 and 1 are empty.

The queue appears full.

This wasted storage motivates the circular queue.

---

# 10. Circular Array Queue

A circular queue treats the array as though its ends were connected.

Conceptually:

```text
0 → 1 → 2 → 3 → 4 → 5
↑                     ↓
└─────────────────────┘
```

After reaching the final position:

```text
Rear
```

returns to:

```text
Index 0
```

provided that position is no longer occupied.

Released storage becomes reusable.

---

# 11. Wrap-Around

Suppose:

```text
Capacity = 6
```

Current state:

```text
Index

0 1 2 3 4 5

[_][_][C][D][E][F]
        ↑       ↑
     Front    Rear
```

Inserting G wraps around:

```text
Index

0 1 2 3 4 5

[G][_][C][D][E][F]
 ↑
Rear
```

Logical order becomes:

```text
C → D → E → F → G
```

Logical order differs from physical order.

---

# 12. Modular Arithmetic

Circular queues commonly use modular arithmetic.

Conceptually:

```text
nextIndex

(current + 1) mod capacity
```

Example:

```text
capacity = 6
```

```text
5 + 1 = 6

6 mod 6 = 0
```

Therefore:

```text
After index 5 comes index 0.
```

The learner should understand the idea rather than memorise formulas.

---

# 13. Front and Rear in a Circular Queue

The queue maintains:

```text
front
```

```text
rear
```

Conceptually:

```text
Index

0 1 2 3 4 5

[D][E][_][_][B][C]
 ↑             ↑
Rear         Front
```

Logical order:

```text
B → C → D → E
```

The physical order no longer determines the queue order.

---

# 14. Empty and Full States

A circular queue must distinguish:

```text
Empty
```

from:

```text
Full
```

Several strategies exist.

Examples include:

- explicit size;
- reserved position;
- separate state variable.

Within this repository, the preferred conceptual model uses:

```text
size
```

because it simplifies reasoning.

Invariant:

```text
0 ≤ size ≤ capacity
```

---

# 15. Circular Queue Invariants

Typical invariants include:

### Size

```text
0 ≤ size ≤ capacity
```

### Front

When non-empty:

```text
front identifies the earliest element.
```

### Rear

When non-empty:

```text
rear identifies the latest inserted element.
```

### Order

Logical FIFO order is preserved independently of physical indices.

### Wrap-around

Both indices always remain inside the valid array.

---

# 16. Dynamic Circular Queue

A circular queue may grow dynamically.

When:

```text
size = capacity
```

the implementation:

```text
Allocate larger array
        ↓
Copy logical order
        ↓
Reset front
        ↓
Reset rear
```

The new array stores elements contiguously.

Example:

Before:

```text
[D][E][_][_][B][C]
```

After resizing:

```text
[B][C][D][E][_][_][_][_]
```

Logical order remains unchanged.

---

# 17. Resizing Correctness

Resizing is correct when:

- every logical element is copied;
- FIFO order remains unchanged;
- size remains constant;
- front identifies the first copied element;
- rear identifies the final copied element.

Resizing changes only storage capacity.

It never changes queue behaviour.

---

# 18. Linked Queue

A linked queue stores each element in a separate node.

Conceptually:

```text
Front
 ↓
[A] → [B] → [C] → null
                     ↑
                   Rear
```

Each node contains:

```text
Element
Reference to next node
```

Unlike a stack, the queue requires:

- front reference;
- rear reference.

Both are necessary for efficient insertion and removal.

---

# 19. Why Both References Are Required

Suppose only the front reference exists.

To insert:

```text
enqueue(D)
```

the implementation must locate:

```text
Current rear
```

Traversal becomes necessary.

```text
O(n)
```

Maintaining a direct rear reference allows:

```text
enqueue = O(1)
```

---

# 20. Linked Enqueue

Before:

```text
Front
 ↓
[A] → [B] → [C]
               ↑
             Rear
```

Create node:

```text
[D]
```

Connect:

```text
[C] → [D]
```

Update rear:

```text
Rear
 ↓
[D]
```

Final queue:

```text
Front
 ↓
[A] → [B] → [C] → [D]
                     ↑
                   Rear
```

---

# 21. Linked Dequeue

Before:

```text
Front
 ↓
[A] → [B] → [C]
               ↑
             Rear
```

Remove:

```text
A
```

Advance front:

```text
Front
 ↓
[B] → [C]
         ↑
       Rear
```

Only the front reference changes.

---

# 22. Single-Element Queue

Suppose:

```text
Front
 ↓
[A]
 ↑
Rear
```

Removing A produces:

```text
Front → null

Rear → null
```

Both references must be updated.

Otherwise the implementation enters an inconsistent state.

---

# 23. Linked Queue Invariants

Typical invariants include:

### Empty queue

```text
size = 0

⇔

front = null
rear = null
```

### Non-empty queue

```text
front references earliest element

rear references latest element
```

### Reachability

Every node must be reachable from the front.

### Termination

The final node references:

```text
null
```

### Size consistency

The number of reachable nodes equals:

```text
size
```

---

# 24. Complexity Comparison

| Operation | Linear Array | Circular Array | Linked Queue |
|-----------|--------------:|---------------:|-------------:|
| enqueue | O(1) | O(1) | O(1) |
| dequeue | O(n) if shifting | O(1) | O(1) |
| peek | O(1) | O(1) | O(1) |
| resize | — | O(n) | Not required |

The circular array eliminates the principal weakness of the naïve array queue.

---

# 25. Space Comparison

### Linear array

```text
O(capacity)
```

### Circular array

```text
O(capacity)
```

### Linked queue

```text
O(n)
```

However, linked nodes require additional references and object overhead.

---

# 26. Engineering Trade-Offs

### Circular Array

Advantages:

- excellent cache locality;
- no per-node overhead;
- efficient memory usage;
- constant-time operations.

Limitations:

- resizing complexity;
- modular arithmetic;
- contiguous allocation.

---

### Linked Queue

Advantages:

- dynamic growth;
- no resizing;
- constant-time insertion and removal.

Limitations:

- node allocation;
- additional references;
- weaker cache locality;
- greater memory overhead.

---

# 27. Choosing an Implementation

A circular array is generally appropriate when:

- high throughput is important;
- predictable memory locality matters;
- resizing is acceptable.

A linked queue may be appropriate when:

- growth is highly unpredictable;
- frequent resizing should be avoided;
- node-based structures are already used.

The correct implementation depends on system requirements rather than theoretical
complexity alone.

---

# 28. Java Perspective

Modern Java commonly represents queue behaviour through:

```text
Queue<E>
```

or

```text
Deque<E>
```

Typical implementations include:

```text
ArrayDeque<E>
```

and

```text
LinkedList<E>
```

The Java Collections Framework hides the internal implementation while exposing
the abstract queue behaviour.

The learner should first understand the underlying implementation principles
before relying on library classes.

The Java API is studied in detail in:

```text
06-java-deque.md
```

---

# 29. Testing Strategy

Every implementation should satisfy the same queue contract.

Representative tests include:

- empty queue;
- enqueue one element;
- enqueue multiple elements;
- dequeue preserves FIFO order;
- peek preserves state;
- transition between empty and non-empty;
- size consistency.

Implementation-specific tests include:

### Circular Array

- wrap-around;
- resizing;
- front and rear movement;
- logical order after resizing.

### Linked Queue

- front transition;
- rear transition;
- single-element removal;
- node reachability.

---

# 30. Key Takeaways

After completing this document, the learner should understand that:

- a queue's FIFO behaviour is independent of its representation;
- a naïve linear-array queue becomes inefficient because dequeue requires
  shifting elements;
- circular arrays eliminate unnecessary movement by reusing released positions;
- modular arithmetic allows the front and rear indices to wrap around the array;
- linked queues require both front and rear references to achieve constant-time
  operations;
- dynamic circular queues occasionally resize while preserving logical order;
- circular arrays usually provide better memory locality, whereas linked queues
  offer incremental growth without resizing;
- implementation correctness depends on preserving structural invariants rather
  than on the chosen representation;
- different implementations may have similar asymptotic complexity but different
  practical performance and memory characteristics;
- selecting a queue implementation is an engineering decision based on workload,
  memory behaviour, and system requirements rather than on Big-O notation
  alone.