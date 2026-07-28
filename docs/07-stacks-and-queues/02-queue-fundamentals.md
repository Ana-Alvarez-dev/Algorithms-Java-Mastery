# Queue Fundamentals

## Algorithms Java Mastery

A **queue** is a linear abstract data type that restricts insertion and removal
operations to opposite ends of the structure.

Elements are inserted at the **rear** and removed from the **front**.

Its behaviour follows the principle:

```text
FIFO — First In, First Out
```

This means that the earliest inserted element is the first element removed.

Conceptually:

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  | →  |  D  |
+-----+    +-----+    +-----+    +-----+

Next element removed = A
Most recent insertion = D
```

Queues model situations in which elements must be processed according to their
arrival order.

They appear in:

- task scheduling;
- request processing;
- message systems;
- operating systems;
- network communication;
- print management;
- event processing;
- breadth-first search;
- producer-consumer systems;
- asynchronous backend applications.

The objective of this document is to study the queue as an **abstract data
type** before examining its concrete implementations and its relationship with
Java.

---

## 1. Academic Foundation

A queue is defined primarily by its behavioural contract rather than by its
physical representation.

The abstract data type determines:

- where insertion occurs;
- where removal occurs;
- which element is processed next;
- which ordering policy must be preserved;
- how empty-state operations behave.

A queue may be implemented using:

- a fixed-capacity array;
- a circular array;
- a dynamically resized circular buffer;
- a singly linked list;
- a doubly linked list;
- another suitable sequential structure.

Although these representations differ internally, every correct queue
implementation must preserve FIFO behaviour.

The distinction between abstraction and implementation is essential:

```text
Queue Abstract Data Type
          ↓
Defines behaviour
          ↓
Enqueue, Dequeue, Peek, Size, Is Empty
```

```text
Queue Implementation
          ↓
Defines representation
          ↓
Circular Array, Dynamic Buffer, Linked Nodes
```

The theoretical foundation of this module is based on classical algorithms and
data structures literature. The explanations, diagrams, specifications, and
engineering interpretations developed in this repository are original and
adapted to the academic direction of **Algorithms Java Mastery**.

The complete bibliographic foundation is maintained in the project
documentation.

```text
docs/00-project/
```

---

## 2. Purpose

The purpose of this document is to establish the conceptual foundation required
to understand, implement, analyse, and use queues correctly.

The learner will study:

- the FIFO ordering principle;
- the front and rear positions;
- fundamental queue operations;
- queue states;
- preconditions and postconditions;
- structural invariants;
- underflow and overflow;
- bounded and dynamically growing queues;
- representative applications;
- correctness and complexity expectations.

This document focuses on the behaviour of the structure independently of a
particular Java implementation.

---

## 3. Learning Objectives

After completing this document, the learner should be able to achieve the
following objectives.

### 3.1 Conceptual understanding

- define a queue;
- explain the FIFO principle;
- identify the front and rear;
- distinguish the queue abstraction from its implementation;
- describe the effect of each fundamental operation.

### 3.2 Algorithmic reasoning

- trace a sequence of queue operations;
- predict which element will be removed next;
- identify problems that require arrival-order processing;
- distinguish queue behaviour from stack behaviour;
- reason about empty and full states.

### 3.3 Correctness reasoning

- define operation preconditions and postconditions;
- identify queue invariants;
- reason about underflow and overflow;
- verify that an implementation preserves FIFO order.

### 3.4 Complexity analysis

- explain why fundamental queue operations should be constant time;
- identify inefficient array-based designs;
- understand why circular storage is important;
- distinguish worst-case from amortised behaviour.

### 3.5 Engineering perspective

- evaluate when a queue is the appropriate abstraction;
- select suitable error-handling behaviour;
- recognise queue-oriented applications;
- identify representative automated tests;
- understand the importance of encapsulation.

---

## 4. Conceptual Model

A queue can be visualised as a horizontal sequence of elements.

Insertion occurs at the rear.

Removal occurs at the front.

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  | →  |  D  |
+-----+    +-----+    +-----+    +-----+
```

The element `A` is the earliest remaining element.

Therefore:

```text
Next element removed = A
```

If `A` is removed, `B` becomes the new front.

```text
Front                    Rear
  ↓                        ↓
+-----+    +-----+    +-----+
|  B  | →  |  C  | →  |  D  |
+-----+    +-----+    +-----+
```

If a new element `E` is inserted, it becomes the new rear.

```text
Front                                         Rear
  ↓                                             ↓
+-----+    +-----+    +-----+    +-----+
|  B  | →  |  C  | →  |  D  | →  |  E  |
+-----+    +-----+    +-----+    +-----+
```

This separation between insertion and removal gives the queue its defining
behaviour.

---

## 5. The FIFO Principle

The defining property of a queue is:

```text
First In
    ↓
First Out
```

Suppose the following elements are inserted in order:

```text
A, B, C, D
```

The removal order will be:

```text
A, B, C, D
```

Unlike a stack, the order is preserved.

```text
Insertion order

A → B → C → D
```

```text
Removal order

A → B → C → D
```

The earliest remaining element must always be processed first.

The FIFO rule must remain valid after every queue operation.

---

## 6. Front and Rear

A queue has two conceptually distinct boundaries.

### 6.1 Front

The **front** identifies the next element available for inspection or removal.

```text
Front
  ↓
+-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  |
+-----+    +-----+    +-----+
```

The front element is the earliest remaining element.

### 6.2 Rear

The **rear** identifies the end at which new elements are inserted.

```text
                    Rear
                      ↓
+-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  |
+-----+    +-----+    +-----+
```

A new element is added after the current rear.

### 6.3 Representation differences

The front and rear may be represented differently depending on the
implementation.

In an array-based queue, they may be represented by:

- integer indices;
- positions calculated through modular arithmetic;
- an index and a size;
- separate read and write boundaries.

In a linked queue, they may be represented by:

- a reference to the first node;
- a reference to the final node.

These are implementation decisions.

Conceptually:

```text
Front = next element removed
Rear  = location of most recent insertion
```

---

## 7. Fundamental Operations

The queue abstract data type commonly defines the following operations:

| Operation | Purpose |
|---|---|
| `enqueue` | Insert an element at the rear |
| `dequeue` | Remove and return the front element |
| `peek` | Return the front element without removing it |
| `isEmpty` | Determine whether the queue has no elements |
| `size` | Return the number of stored elements |

Some bounded implementations may also define:

| Operation | Purpose |
|---|---|
| `isFull` | Determine whether the queue has reached capacity |
| `capacity` | Return the maximum number of supported elements |

Java libraries may use method names such as:

- `add`;
- `offer`;
- `remove`;
- `poll`;
- `element`;
- `peek`.

The language-specific differences are studied later in:

```text
06-java-deque.md
```

---

## 8. Enqueue Operation

The `enqueue` operation inserts a new element at the rear of the queue.

Before:

```text
Front                    Rear
  ↓                        ↓
+-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  |
+-----+    +-----+    +-----+
```

Operation:

```text
enqueue(D)
```

After:

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  | →  |  D  |
+-----+    +-----+    +-----+    +-----+
```

The newly inserted element becomes the new rear.

### 8.1 Conceptual specification

Input:

```text
Queue Q
Element x
```

Effect:

```text
Insert x at the rear of Q
```

### 8.2 Postconditions

After a successful enqueue:

- the queue contains the new element;
- the new element becomes the rear;
- the size increases by one;
- existing elements preserve their relative order;
- the front remains unchanged unless the queue was previously empty.

### 8.3 Empty-queue case

Before:

```text
Queue = empty
```

Operation:

```text
enqueue(A)
```

After:

```text
Front and Rear
      ↓
   +-----+
   |  A  |
   +-----+
```

For a single-element queue, the same element is both the front and the rear.

---

## 9. Dequeue Operation

The `dequeue` operation removes and returns the front element.

Before:

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  | →  |  D  |
+-----+    +-----+    +-----+    +-----+
```

Operation:

```text
dequeue()
```

Returned value:

```text
A
```

After:

```text
Front                    Rear
  ↓                        ↓
+-----+    +-----+    +-----+
|  B  | →  |  C  | →  |  D  |
+-----+    +-----+    +-----+
```

### 9.1 Conceptual specification

Input:

```text
Non-empty queue Q
```

Output:

```text
Previous front element
```

Effect:

```text
Remove the front element from Q
```

### 9.2 Preconditions

A traditional dequeue operation requires:

```text
Q is not empty
```

Attempting to remove an element from an empty queue produces an underflow
condition.

### 9.3 Postconditions

After a successful dequeue:

- the previous front has been removed;
- the removed element is returned;
- the size decreases by one;
- the next element becomes the new front;
- the relative order of remaining elements is preserved.

### 9.4 Single-element case

Before:

```text
Front and Rear
      ↓
   +-----+
   |  A  |
   +-----+
```

After removing `A`:

```text
Queue = empty
```

The implementation must update both front and rear consistently.

---

## 10. Peek Operation

The `peek` operation returns the front element without modifying the queue.

Before:

```text
Front                    Rear
  ↓                        ↓
+-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  |
+-----+    +-----+    +-----+
```

Operation:

```text
peek()
```

Returned value:

```text
A
```

After:

```text
Front                    Rear
  ↓                        ↓
+-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  |
+-----+    +-----+    +-----+
```

The structure remains unchanged.

### 10.1 Difference between peek and dequeue

| Operation | Returns front | Removes front |
|---|:---:|:---:|
| `peek` | Yes | No |
| `dequeue` | Yes | Yes |

### 10.2 Postconditions

After a successful peek:

- the returned value is the current front;
- the size remains unchanged;
- FIFO order remains unchanged;
- no element is removed.

---

## 11. Is Empty Operation

The `isEmpty` operation determines whether the queue contains any elements.

Conceptually:

```text
size = 0
    ↓
Queue is empty
```

```text
size > 0
    ↓
Queue is not empty
```

Typical result:

```text
true  → no elements
false → one or more elements
```

This operation is commonly used before `dequeue` or `peek`.

Example reasoning:

```text
If queue is not empty
        ↓
Inspect or remove the front
```

The operation should not modify the queue.

---

## 12. Size Operation

The `size` operation returns the number of elements currently stored.

Example:

```text
Front                    Rear
  ↓                        ↓
+-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  |
+-----+    +-----+    +-----+
```

Result:

```text
size = 3
```

The size differs from capacity.

```text
Size
    ↓
Number of elements currently stored
```

```text
Capacity
    ↓
Maximum number of elements currently supported
```

A queue may have:

```text
size = 3
capacity = 8
```

This means three positions are occupied and five remain available.

---

## 13. Empty Queue

An empty queue contains no elements.

Conceptually:

```text
No valid front
No valid rear element
```

Its size is:

```text
0
```

For an empty queue:

| Operation | Typical result |
|---|---|
| `isEmpty` | `true` |
| `size` | `0` |
| `enqueue` | Valid |
| `dequeue` | Invalid without defined handling |
| `peek` | Invalid without defined handling |

The representation depends on the implementation.

An array-based queue may use:

```text
size = 0
```

A linked implementation may use:

```text
front = null
rear = null
```

A circular queue may use:

```text
front index and size
```

or another explicit empty-state convention.

The abstract meaning remains the same.

---

## 14. Queue Underflow

**Underflow** occurs when an operation attempts to inspect or remove an element
from an empty queue.

Example:

```text
Queue = empty

dequeue()
```

There is no front element to return.

Possible handling strategies include:

- throwing an exception;
- returning a special value;
- returning an optional result;
- preventing the operation through a precondition check.

The selected behaviour must be explicit in the queue contract.

### 14.1 Engineering consideration

Returning `null` may be ambiguous when `null` is permitted as a stored value.

The API should distinguish clearly between:

```text
The queue is empty
```

and:

```text
The front element exists and contains null
```

---

## 15. Queue Overflow

**Overflow** occurs when insertion is attempted on a bounded queue that has no
remaining capacity.

Example:

```text
Capacity = 3

Current queue:

[A, B, C]

enqueue(D)
```

The queue is full.

Possible strategies include:

- rejecting the insertion;
- throwing an exception;
- returning a failure result;
- increasing the underlying capacity;
- using a dynamically linked representation.

A dynamically growing implementation reduces fixed-capacity limitations but
still depends on available memory.

---

## 16. Bounded and Dynamically Growing Queues

### 16.1 Bounded queue

A bounded queue has a fixed maximum capacity.

```text
Maximum capacity = 5
```

Advantages may include:

- predictable memory use;
- explicit backpressure;
- suitability for constrained systems;
- stable capacity limits.

Limitations include:

- possible overflow;
- rejected insertions;
- required capacity planning.

Bounded queues are common in systems that must control workload growth.

### 16.2 Dynamically growing queue

A dynamically growing queue expands when required.

Possible representations include:

- a resizable circular array;
- linked nodes.

Advantages may include:

- flexibility;
- adaptation to variable workloads;
- fewer fixed-capacity constraints.

Limitations may include:

- resizing cost;
- dynamic allocation;
- less predictable memory consumption;
- potential unbounded growth.

The FIFO contract remains the same in both cases.

---

## 17. Operation Trace

Tracing operations helps verify queue behaviour.

Initial state:

```text
[]
```

Operation:

```text
enqueue(A)
```

State:

```text
[A]
```

Operation:

```text
enqueue(B)
```

State:

```text
[A, B]
```

Operation:

```text
enqueue(C)
```

State:

```text
[A, B, C]
```

Operation:

```text
dequeue()
```

Returned value:

```text
A
```

State:

```text
[B, C]
```

Operation:

```text
peek()
```

Returned value:

```text
B
```

State remains:

```text
[B, C]
```

Operation:

```text
enqueue(D)
```

State:

```text
[B, C, D]
```

Operation:

```text
dequeue()
```

Returned value:

```text
B
```

Final state:

```text
[C, D]
```

This trace demonstrates that the earliest remaining element is always removed
first.

---

## 18. Formal Behavioural Model

A queue may be represented abstractly as a finite sequence:

```text
Q = ⟨a₁, a₂, ..., aₙ⟩
```

where:

```text
a₁ = front element
aₙ = rear element
n  = current size
```

### Enqueue

For an element `x`:

```text
enqueue(Q, x)
```

produces:

```text
Q' = ⟨a₁, a₂, ..., aₙ, x⟩
```

### Dequeue

For a non-empty queue:

```text
dequeue(Q)
```

returns:

```text
a₁
```

and produces:

```text
Q' = ⟨a₂, a₃, ..., aₙ⟩
```

### Peek

For a non-empty queue:

```text
peek(Q)
```

returns:

```text
a₁
```

without changing `Q`.

This model demonstrates that insertion occurs at the end of the abstract
sequence while removal occurs at the beginning.

---

## 19. Preconditions and Postconditions

Defining preconditions and postconditions makes queue behaviour precise.

### 19.1 Enqueue

Preconditions:

- the input element satisfies the queue's value constraints;
- sufficient capacity exists, or the structure can grow.

Postconditions:

- the inserted element becomes the new rear;
- the size increases by one;
- the previous front remains unchanged unless the queue was empty;
- previous elements preserve their relative order.

### 19.2 Dequeue

Precondition:

- the queue is not empty.

Postconditions:

- the previous front is returned;
- the previous front is removed;
- the size decreases by one;
- the next element becomes the new front;
- the remaining elements preserve FIFO order.

### 19.3 Peek

Precondition:

- the queue is not empty.

Postconditions:

- the current front is returned;
- the queue remains unchanged.

### 19.4 Is empty

Preconditions:

- none beyond the existence of a valid queue.

Postconditions:

- returns `true` exactly when size is zero;
- does not modify the structure.

---

## 20. Structural and Behavioural Invariants

A queue implementation must preserve properties that remain true before and
after every valid operation.

### 20.1 Size invariant

```text
size ≥ 0
```

For a bounded queue:

```text
0 ≤ size ≤ capacity
```

### 20.2 Front invariant

When the queue is not empty:

```text
front identifies the earliest remaining element
```

When the queue is empty:

```text
no valid front element exists
```

### 20.3 Rear invariant

When the queue is not empty:

```text
rear identifies the most recently inserted remaining element
```

For a single-element queue:

```text
front and rear identify the same element
```

### 20.4 Ordering invariant

Elements leave the queue in the same relative order in which they entered.

### 20.5 Reachability invariant

In a linked implementation, every stored node must remain reachable from the
front.

### 20.6 Boundary invariant

In an array implementation, active elements must remain within valid storage
positions.

### 20.7 Size consistency invariant

The recorded size must equal the number of logically stored elements.

### 20.8 Empty-state invariant

When size is zero, the front and rear representations must be consistent with an
empty queue.

---

## 21. Correctness Reasoning

A queue operation is correct when it:

- satisfies its specification;
- preserves FIFO order;
- returns the expected value;
- updates size correctly;
- preserves all invariants;
- handles invalid states according to the contract.

Consider `enqueue(D)`.

Before:

```text
Front          Rear
  ↓              ↓
[A, B, C]
```

After:

```text
Front             Rear
  ↓                 ↓
[A, B, C, D]
```

The operation is correct because:

1. `D` becomes the new rear;
2. the front remains `A`;
3. existing elements preserve their order;
4. the size increases by one;
5. the next dequeue still returns `A`.

Now consider `dequeue()`.

Before:

```text
[A, B, C, D]
```

Returned:

```text
A
```

After:

```text
[B, C, D]
```

The operation is correct because:

1. the earliest element is returned;
2. only one element is removed;
3. `B` becomes the new front;
4. remaining order is preserved;
5. size decreases by one.

---

## 22. Expected Complexity

The fundamental queue operations are expected to be efficient.

| Operation | Expected time |
|---|---:|
| `enqueue` | O(1) |
| `dequeue` | O(1) |
| `peek` | O(1) |
| `isEmpty` | O(1) |
| `size` | O(1) |

These costs assume that:

- the front is directly accessible;
- the rear is directly accessible;
- size is stored explicitly;
- no element shifting is required;
- no traversal is required.

### 22.1 Dynamic-array consideration

A resizable circular array may occasionally require copying all elements into a
larger array.

A particular enqueue may therefore cost:

```text
O(n)
```

Across a sequence of insertions, enqueue is commonly analysed as:

```text
O(1) amortised
```

The detailed analysis is developed in:

```text
05-queue-implementations.md
```

and:

```text
08-complexity-analysis.md
```

---

## 23. Inefficient Linear Array Queue

A naive array queue may remove the front element and then shift every remaining
element one position to the left.

Before:

```text
[A, B, C, D]
```

After removing `A`:

```text
[B, C, D, _]
```

If the implementation physically moves `B`, `C`, and `D`, dequeue requires:

```text
O(n)
```

This is inefficient because a queue should normally support constant-time
removal.

The issue can be avoided by moving the front boundary instead of moving every
element.

```text
Do not move elements
        ↓
Move the front index
```

This idea leads to the circular queue.

---

## 24. Circular Queue Concept

An array-based queue can reuse positions that become available at the beginning
of the array.

Conceptually:

```text
Array positions

0   1   2   3   4
```

After several operations, active elements may wrap around:

```text
Index:   0   1   2   3   4
Value:  [D] [E] [_] [B] [C]
              ↑       ↑
             Rear    Front
```

The logical queue order may be:

```text
B → C → D → E
```

even though the physical positions are not contiguous from left to right.

Circular indexing allows the queue to:

- reuse available positions;
- avoid shifting elements;
- maintain O(1) enqueue and dequeue;
- use fixed-capacity storage efficiently.

The complete implementation is studied in:

```text
05-queue-implementations.md
```

---

## 25. Queue as an Abstract Data Type

The queue abstraction exposes behaviour while hiding representation details.

A client should reason in terms of:

```text
enqueue
dequeue
peek
isEmpty
size
```

The client should not need to know whether the implementation uses:

- a circular array;
- a linked list;
- a dynamic buffer.

Conceptually:

```text
Client code
     ↓
Queue operations
     ↓
Hidden implementation
```

This separation supports:

- encapsulation;
- interchangeable implementations;
- easier testing;
- reduced coupling;
- implementation evolution.

---

## 26. Representative Applications

### 26.1 Task scheduling

Tasks may be processed in arrival order.

```text
Task A arrives
Task B arrives
Task C arrives
```

Processing order:

```text
A → B → C
```

A queue provides a natural model when earlier tasks should be handled first.

---

### 26.2 Request processing

Backend systems frequently receive requests that cannot all be processed
immediately.

A queue can temporarily store pending work.

```text
Incoming requests
        ↓
      Queue
        ↓
Worker processes
```

This design supports:

- workload smoothing;
- asynchronous processing;
- separation between producers and consumers;
- controlled concurrency.

---

### 26.3 Print management

A print service may process documents according to their arrival order.

```text
Document A
Document B
Document C
```

Expected print order:

```text
A → B → C
```

The queue preserves fairness under a simple arrival-order policy.

---

### 26.4 Message systems

Message brokers and application queues organise pending messages until consumers
process them.

Conceptually:

```text
Producer
   ↓
Message Queue
   ↓
Consumer
```

Real messaging systems may add:

- delivery guarantees;
- acknowledgements;
- retries;
- priorities;
- persistence;
- multiple consumers.

However, the basic FIFO queue remains an important conceptual foundation.

---

### 26.5 Network buffering

Data packets may temporarily wait in a queue before transmission or processing.

Queues help manage differences between:

- arrival rate;
- processing rate;
- transmission capacity.

A bounded queue may also support explicit backpressure when capacity is reached.

---

### 26.6 Breadth-first search

Breadth-first search explores nodes by increasing distance from the starting
point.

A queue stores discovered nodes waiting to be processed.

Conceptually:

```text
Discover node
      ↓
Enqueue node

Process front node
      ↓
Discover neighbours
      ↓
Enqueue neighbours
```

FIFO order ensures that earlier-discovered nodes are explored first.

---

### 26.7 Event processing

Event-driven applications may place events into a queue before dispatching them
to handlers.

```text
Event source
     ↓
Event queue
     ↓
Event processor
```

This supports ordered and decoupled processing.

---

### 26.8 Producer-consumer systems

Producers generate work.

Consumers process work.

A shared queue mediates between them.

```text
Producers
    ↓
Work Queue
    ↓
Consumers
```

The queue can absorb temporary differences between production and consumption
rates.

---

## 27. Queue versus General List

A general list may permit:

- insertion at arbitrary positions;
- deletion at arbitrary positions;
- indexed access;
- arbitrary traversal.

A queue intentionally restricts operations.

| Characteristic | General list | Queue |
|---|---|---|
| Insertion position | Potentially arbitrary | Rear |
| Removal position | Potentially arbitrary | Front |
| Next element processed | Depends on operation | Earliest |
| Ordering policy | General | FIFO |
| Interface complexity | Broader | Restricted |

The restriction communicates a clear processing policy.

When a problem requires arrival-order processing, a queue expresses intent more
clearly than a general-purpose list.

---

## 28. Queue versus Stack

Queues and stacks are both restricted linear data structures, but they preserve
different orderings.

| Characteristic | Queue | Stack |
|---|---|---|
| Ordering principle | FIFO | LIFO |
| Insertion location | Rear | Top |
| Removal location | Front | Top |
| Next element removed | Earliest | Most recent |
| Representative use | Scheduling | Backtracking |

Given insertion order:

```text
A, B, C
```

Queue removal order:

```text
A, B, C
```

Stack removal order:

```text
C, B, A
```

The required processing policy determines the appropriate structure.

---

## 29. Queue versus Priority Queue

A standard queue processes elements according to arrival order.

A priority queue processes elements according to a priority rule.

Example arrival order:

```text
A, B, C
```

Assigned priorities:

```text
A = low
B = high
C = medium
```

Possible priority-based removal order:

```text
B, C, A
```

Therefore:

```text
Queue
    ↓
FIFO order
```

```text
Priority Queue
    ↓
Priority-defined order
```

A priority queue is related to queues conceptually, but it does not preserve
standard FIFO behaviour.

---

## 30. Java Perspective

Java provides the `Queue` interface for queue-oriented behaviour.

Conceptually:

```text
Queue<String> queue = new ArrayDeque<>();
```

Typical operations include:

```text
queue.offer("A");
queue.offer("B");

String front = queue.peek();
String removed = queue.poll();
```

Java distinguishes between method families that:

- throw exceptions;
- return special values.

Representative pairs include:

| Behaviour | Exception-based | Special-value-based |
|---|---|---|
| Insert | `add` | `offer` |
| Remove | `remove` | `poll` |
| Inspect | `element` | `peek` |

These differences are studied in detail in:

```text
06-java-deque.md
```

The central principle remains:

> Understand FIFO behaviour before selecting a Java implementation.

---

## 31. Null Elements

Whether a queue should accept `null` is an API-design decision.

Allowing `null` may create ambiguity.

Suppose:

```text
poll() returns null
```

This could mean:

```text
The queue is empty
```

or:

```text
The front element exists and contains null
```

A clear contract should define:

- whether `null` values are permitted;
- how empty-state operations behave;
- whether exceptions, optional results, or special values are used.

Some Java queue implementations prohibit `null` for this reason.

---

## 32. Mutability and Encapsulation

A queue should control access to its internal representation.

Client code should not be able to:

- change the front index directly;
- change the rear index directly;
- reconnect internal nodes;
- modify size independently;
- remove elements from arbitrary positions;
- bypass FIFO rules.

Poor encapsulation could create invalid states such as:

```text
size = 4
```

while only three elements are logically present.

A well-designed queue exposes operations rather than representation details.

This protects structural and behavioural invariants.

---

## 33. Automated Testing Perspective

A correct queue implementation should be validated through automated tests.

### Empty-state tests

- a newly created queue is empty;
- its size is zero;
- invalid peek behaviour follows the contract;
- invalid dequeue behaviour follows the contract.

### Enqueue tests

- enqueueing one element makes it both front and rear;
- multiple enqueues preserve arrival order;
- every enqueue increases size;
- the queue is no longer empty after insertion.

### Dequeue tests

- dequeue returns the earliest element;
- dequeue removes exactly one element;
- repeated dequeues preserve FIFO order;
- size decreases correctly;
- removing the final element restores the empty state.

### Peek tests

- peek returns the front;
- peek does not remove the element;
- peek does not modify size;
- repeated peeks return the same value while the queue remains unchanged.

### Circular-buffer tests

- indices wrap correctly;
- released positions are reused;
- element order remains valid after wrap-around;
- full and empty states remain distinguishable;
- resizing preserves logical order.

### Capacity tests

For bounded or resizable queues:

- insertion at capacity is handled correctly;
- overflow behaviour follows the contract;
- resizing preserves all elements;
- resizing preserves FIFO order.

### Generic-type tests

The queue should behave correctly with:

- integers;
- strings;
- domain objects;
- other permitted types.

---

## 34. Property-Based Reasoning

Queue behaviour can be expressed through general properties.

### Enqueue-dequeue property

For an empty queue:

```text
enqueue(Q, x)
dequeue(Q)
```

must return:

```text
x
```

and restore the empty logical state.

### FIFO property

If elements are enqueued in the order:

```text
x₁, x₂, ..., xₙ
```

then repeated dequeue operations return:

```text
x₁, x₂, ..., xₙ
```

### Peek preservation property

For a non-empty queue:

```text
size before peek = size after peek
```

### Size property

After a successful enqueue:

```text
new size = previous size + 1
```

After a successful dequeue:

```text
new size = previous size - 1
```

### Order preservation property

Removing the front does not change the relative order of the remaining elements.

These properties provide a strong basis for automated testing.

---

## 35. Common Conceptual Mistakes

Several fundamental errors should be recognised early.

### Confusing front and rear

Insertion must occur at the rear and removal at the front.

### Removing from the rear

Removing the most recently inserted element produces stack-like behaviour rather
than queue behaviour.

### Shifting array elements unnecessarily

A queue implementation should move boundaries rather than all stored elements.

### Ignoring empty-state transitions

Removing the only element requires both front and rear to enter a valid empty
state.

### Confusing size with capacity

The number of stored elements differs from the maximum supported number.

### Mismanaging circular indices

Incorrect wrap-around logic may overwrite active elements or skip available
positions.

### Losing FIFO order during resizing

Elements copied from a circular buffer must preserve their logical order, not
merely their physical array order.

---

## 36. Problem-Recognition Guide

A queue may be appropriate when the problem contains ideas such as:

- first come, first served;
- arrival order;
- pending requests;
- scheduled work;
- buffering;
- waiting tasks;
- level-by-level traversal;
- event dispatch;
- producer-consumer communication;
- fair processing.

Useful questions include:

```text
Must the earliest pending item be processed first?
```

```text
Does the problem represent waiting work?
```

```text
Must processing preserve arrival order?
```

```text
Does the algorithm explore elements level by level?
```

```text
Is asynchronous work temporarily stored before processing?
```

If the answer is yes, a queue may be a suitable abstraction.

---

## 37. Engineering Considerations

Selecting and designing a queue requires more than recognising FIFO behaviour.

The engineer should evaluate:

- expected workload;
- maximum capacity;
- bounded or unbounded growth;
- latency requirements;
- backpressure behaviour;
- memory constraints;
- error-handling policy;
- thread-safety requirements;
- blocking or non-blocking operations;
- standard-library alternatives;
- fairness requirements;
- observability and monitoring.

For example:

- a bounded queue may prevent uncontrolled memory growth;
- a linked queue may grow dynamically but allocate one node per element;
- a circular array may offer efficient memory locality;
- a concurrent queue may be required when multiple threads interact with the
  same structure.

The data structure should be selected according to system requirements.

---

## 38. Relationship with Other Module Documents

This document introduces the conceptual foundation for the remaining
queue-related study.

```text
02-queue-fundamentals.md
        ↓
Defines FIFO behaviour and core operations
        ↓
05-queue-implementations.md
        ↓
Compares physical representations
        ↓
06-java-deque.md
        ↓
Connects the abstraction with Java
        ↓
07-common-algorithms.md
        ↓
Applies queues to representative problems
        ↓
08-complexity-analysis.md
        ↓
Examines operation costs
        ↓
09-problem-solving-guide.md
        ↓
Develops a systematic solution method
        ↓
10-common-mistakes.md
        ↓
Identifies recurring errors
```

It also supports later modules involving:

- trees;
- graphs;
- scheduling;
- concurrent systems;
- event processing;
- backend architecture.

---

## 39. Study Questions

After studying this document, the learner should be able to answer:

1. What defines a queue as an abstract data type?
2. Why is queue behaviour described as FIFO?
3. What is the difference between the front and the rear?
4. What is the difference between `dequeue` and `peek`?
5. What happens when dequeue is applied to an empty queue?
6. What is the difference between queue size and capacity?
7. Which invariants must a queue preserve?
8. Why should enqueue and dequeue normally be O(1)?
9. Why is shifting elements in an array queue inefficient?
10. How does a circular queue reuse storage?
11. Why is a queue useful for breadth-first search?
12. How does a queue differ from a stack?
13. How does a queue differ from a priority queue?
14. What test scenarios provide evidence of FIFO correctness?
15. When should an engineer select a bounded queue?
16. Why must front and rear both be updated after removing the only element?
17. Why is understanding the abstraction necessary before using Java
    collections?

---

## 40. Completion Criteria

This document may be considered complete when the learner can:

- define a queue without referring to a specific programming language;
- explain FIFO behaviour using an operation trace;
- identify the front and rear;
- specify enqueue, dequeue, peek, is-empty, and size operations;
- define operation preconditions and postconditions;
- explain underflow and overflow;
- identify queue invariants;
- distinguish size from capacity;
- explain why circular storage avoids shifting;
- recognise representative applications;
- analyse the expected complexity of fundamental operations;
- compare queues with stacks, lists, and priority queues;
- identify queue-oriented problems;
- design representative automated tests.

---

## 41. Key Takeaways

After completing this document, the learner should understand that:

- a queue is a linear abstract data type;
- queue behaviour is defined by the FIFO principle;
- insertion occurs at the rear;
- removal occurs at the front;
- `enqueue` inserts an element;
- `dequeue` removes and returns the earliest element;
- `peek` inspects the front without modifying the structure;
- empty-queue operations require explicit handling;
- bounded queues may experience overflow;
- correct implementations preserve front, rear, size, and ordering invariants;
- enqueue and dequeue should normally execute in constant time;
- naive array shifting produces an inefficient queue;
- circular arrays and linked nodes can both implement queue behaviour;
- queues model scheduling, buffering, message processing, breadth-first search,
  and asynchronous workloads;
- automated testing should verify both results and FIFO properties;
- implementation decisions should follow the queue contract rather than define
  it.

Understanding these fundamentals prepares the learner to compare queue
implementations, study deques, use Java's queue abstractions appropriately, and
solve algorithms based on FIFO processing.