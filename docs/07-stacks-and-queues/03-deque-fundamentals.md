# Deque Fundamentals

## Algorithms Java Mastery

A **deque** is a linear abstract data type that permits insertion and removal
operations at both ends of the structure.

The term **deque** is an abbreviation of:

```text
Double-Ended Queue
```

Unlike a traditional queue, which inserts elements at the rear and removes them
from the front, a deque provides controlled access to both boundaries.

Conceptually:

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  | ⇄  |  D  |
+-----+    +-----+    +-----+    +-----+
```

A deque supports:

```text
Insertion at the front
Insertion at the rear
Removal from the front
Removal from the rear
```

Because of this flexibility, a deque can reproduce both:

```text
Stack behaviour
```

and:

```text
Queue behaviour
```

However, a deque is not merely a combination of unrelated methods. It is a
well-defined abstract data type whose correctness depends on preserving the
logical order of elements while both boundaries change.

Deques appear in:

- stack and queue implementations;
- sliding-window algorithms;
- monotonic data structures;
- task scheduling;
- work-stealing systems;
- palindrome verification;
- history navigation;
- bounded buffering;
- bidirectional processing.

The objective of this document is to study the deque conceptually before
examining implementation strategies and Java's `Deque` interface.

---

## 1. Academic Foundation

A deque is defined by its behavioural contract rather than by a specific
physical representation.

The abstract data type determines:

- which operations are supported;
- at which end each operation occurs;
- how element order is preserved;
- how empty-state operations behave;
- which structural properties must remain valid.

A deque may be implemented using:

- a fixed-capacity circular array;
- a dynamically resized circular array;
- a doubly linked list;
- a specialised linked structure;
- another representation supporting efficient access to both ends.

Although these implementations differ internally, a correct deque must preserve
the same abstract behaviour.

The distinction between abstraction and implementation is essential:

```text
Deque Abstract Data Type
          ↓
Defines behaviour
          ↓
Insert and remove at both ends
```

```text
Deque Implementation
          ↓
Defines representation
          ↓
Circular Array or Linked Nodes
```

The theoretical foundation of this module is based on classical algorithms and
data structures literature. The explanations, diagrams, specifications, and
engineering interpretations developed in this repository are original and
adapted to the direction of **Algorithms Java Mastery**.

The complete bibliographic foundation is maintained in the project
documentation.

```text
docs/00-project/
```

---

## 2. Purpose

The purpose of this document is to establish the conceptual foundation required
to understand, implement, analyse, and use deques correctly.

The learner will study:

- the double-ended access model;
- the front and rear boundaries;
- fundamental deque operations;
- stack-like and queue-like behaviour;
- input-restricted and output-restricted deques;
- preconditions and postconditions;
- structural invariants;
- underflow and overflow;
- representative applications;
- expected computational complexity.

This document focuses on the abstract behaviour of a deque independently of a
particular Java implementation.

---

## 3. Learning Objectives

After completing this document, the learner should be able to achieve the
following objectives.

### 3.1 Conceptual understanding

- define a deque;
- explain why it is called a double-ended queue;
- identify the front and rear;
- distinguish a deque from a stack and a standard queue;
- explain how a deque can support both LIFO and FIFO behaviour.

### 3.2 Algorithmic reasoning

- trace sequences of operations at both ends;
- determine which element will be removed next;
- identify problems requiring double-ended access;
- recognise restricted deque variants;
- reason about empty and single-element states.

### 3.3 Correctness reasoning

- define operation preconditions and postconditions;
- identify deque invariants;
- verify that both boundaries remain valid;
- reason about underflow and overflow;
- confirm that operations preserve logical order.

### 3.4 Complexity analysis

- explain why end operations should be constant time;
- identify unsuitable representations;
- understand the role of circular storage;
- distinguish worst-case and amortised costs.

### 3.5 Engineering perspective

- evaluate when a deque is more appropriate than a stack or queue;
- select suitable operation semantics;
- identify representative tests;
- understand the importance of abstraction and encapsulation.

---

## 4. Conceptual Model

A deque can be visualised as a sequence with two accessible boundaries.

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  | ⇄  |  D  |
+-----+    +-----+    +-----+    +-----+
```

Both ends support controlled modification.

At the front, the structure may:

- insert a new first element;
- inspect the first element;
- remove the first element.

At the rear, the structure may:

- insert a new last element;
- inspect the last element;
- remove the last element.

The middle elements are not normally inserted or removed directly through the
deque abstraction.

This restriction distinguishes a deque from a general-purpose list.

---

## 5. Double-Ended Access

The defining property of a deque is not LIFO or FIFO alone.

Its defining property is:

```text
Access at both ends
```

Conceptually:

```text
Front operations              Rear operations

addFirst                      addLast
removeFirst                   removeLast
peekFirst                     peekLast
```

Given:

```text
[A, B, C]
```

An insertion at the front produces:

```text
[X, A, B, C]
```

An insertion at the rear produces:

```text
[A, B, C, X]
```

A removal from the front produces:

```text
[B, C]
```

A removal from the rear produces:

```text
[A, B]
```

The selected operation determines which ordering behaviour emerges.

---

## 6. Front and Rear Boundaries

A deque maintains two conceptually distinct ends.

### 6.1 Front

The front identifies the first logical element.

```text
Front
  ↓
+-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  |
+-----+    +-----+    +-----+
```

Operations at this end include:

```text
insertFront
removeFront
peekFront
```

### 6.2 Rear

The rear identifies the final logical element.

```text
                    Rear
                      ↓
+-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  |
+-----+    +-----+    +-----+
```

Operations at this end include:

```text
insertRear
removeRear
peekRear
```

### 6.3 Single-element state

When the deque contains one element:

```text
Front and Rear
      ↓
   +-----+
   |  A  |
   +-----+
```

The same element is both the first and the last.

Any removal operation makes the structure empty.

---

## 7. Fundamental Operations

A deque commonly provides the following operations:

| Operation | Purpose |
|---|---|
| `addFirst` | Insert an element at the front |
| `addLast` | Insert an element at the rear |
| `removeFirst` | Remove and return the front element |
| `removeLast` | Remove and return the rear element |
| `peekFirst` | Inspect the front without removing it |
| `peekLast` | Inspect the rear without removing it |
| `isEmpty` | Determine whether the deque is empty |
| `size` | Return the number of stored elements |

Bounded implementations may also provide:

| Operation | Purpose |
|---|---|
| `isFull` | Determine whether capacity has been reached |
| `capacity` | Return the maximum supported number of elements |

The exact method names may differ between APIs, but the abstract behaviour
remains equivalent.

---

## 8. Insert at the Front

The front-insertion operation adds a new element before the current first
element.

Before:

```text
Front                    Rear
  ↓                        ↓
+-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  |
+-----+    +-----+    +-----+
```

Operation:

```text
addFirst(X)
```

After:

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  X  | ⇄  |  A  | ⇄  |  B  | ⇄  |  C  |
+-----+    +-----+    +-----+    +-----+
```

### 8.1 Conceptual specification

Input:

```text
Deque D
Element x
```

Effect:

```text
Insert x before the current front
```

### 8.2 Postconditions

After a successful front insertion:

- the deque contains the new element;
- the new element becomes the front;
- the size increases by one;
- the previous elements preserve their relative order;
- the rear remains unchanged unless the deque was empty.

---

## 9. Insert at the Rear

The rear-insertion operation adds a new element after the current final
element.

Before:

```text
Front                    Rear
  ↓                        ↓
+-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  |
+-----+    +-----+    +-----+
```

Operation:

```text
addLast(X)
```

After:

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  | ⇄  |  X  |
+-----+    +-----+    +-----+    +-----+
```

### 9.1 Conceptual specification

Input:

```text
Deque D
Element x
```

Effect:

```text
Insert x after the current rear
```

### 9.2 Postconditions

After a successful rear insertion:

- the deque contains the new element;
- the new element becomes the rear;
- the size increases by one;
- previous elements preserve their relative order;
- the front remains unchanged unless the deque was empty.

---

## 10. Remove from the Front

The front-removal operation removes and returns the first logical element.

Before:

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  | ⇄  |  D  |
+-----+    +-----+    +-----+    +-----+
```

Operation:

```text
removeFirst()
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
|  B  | ⇄  |  C  | ⇄  |  D  |
+-----+    +-----+    +-----+
```

### 10.1 Preconditions

The deque must not be empty.

### 10.2 Postconditions

After a successful removal:

- the previous front is returned;
- the previous front is removed;
- the next element becomes the new front;
- the size decreases by one;
- the rear remains unchanged unless the removed element was the only element.

---

## 11. Remove from the Rear

The rear-removal operation removes and returns the last logical element.

Before:

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  | ⇄  |  D  |
+-----+    +-----+    +-----+    +-----+
```

Operation:

```text
removeLast()
```

Returned value:

```text
D
```

After:

```text
Front                    Rear
  ↓                        ↓
+-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  |
+-----+    +-----+    +-----+
```

### 11.1 Preconditions

The deque must not be empty.

### 11.2 Postconditions

After a successful removal:

- the previous rear is returned;
- the previous rear is removed;
- the predecessor becomes the new rear;
- the size decreases by one;
- the front remains unchanged unless the removed element was the only element.

---

## 12. Peek Operations

A deque supports inspection at both ends without structural modification.

### 12.1 Peek front

Given:

```text
[A, B, C]
```

Operation:

```text
peekFirst()
```

Returns:

```text
A
```

The deque remains:

```text
[A, B, C]
```

### 12.2 Peek rear

Operation:

```text
peekLast()
```

Returns:

```text
C
```

The deque remains:

```text
[A, B, C]
```

### 12.3 Postconditions

After either peek operation:

- the expected boundary element is returned;
- no element is removed;
- size remains unchanged;
- logical order remains unchanged.

---

## 13. Empty Deque

An empty deque contains no elements.

Conceptually:

```text
No valid front
No valid rear
size = 0
```

For an empty deque:

| Operation | Typical result |
|---|---|
| `isEmpty` | `true` |
| `size` | `0` |
| `addFirst` | Valid |
| `addLast` | Valid |
| `removeFirst` | Invalid without defined handling |
| `removeLast` | Invalid without defined handling |
| `peekFirst` | Invalid without defined handling |
| `peekLast` | Invalid without defined handling |

The internal representation depends on the implementation.

A linked implementation may use:

```text
front = null
rear = null
```

A circular-array implementation may use:

```text
size = 0
```

together with front and rear indices.

The abstract state remains the same.

---

## 14. Underflow

Deque underflow occurs when an operation attempts to inspect or remove an
element from an empty deque.

Examples:

```text
removeFirst()
removeLast()
peekFirst()
peekLast()
```

when:

```text
size = 0
```

Possible handling strategies include:

- throwing an exception;
- returning a special value;
- returning an optional result;
- checking the precondition before performing the operation.

The selected behaviour must be explicit in the contract.

### 14.1 Ambiguity of special values

Returning `null` may be ambiguous when `null` is accepted as a stored element.

The API should distinguish clearly between:

```text
No element exists
```

and:

```text
An element exists and stores null
```

---

## 15. Overflow

Deque overflow occurs when insertion is attempted on a bounded deque that has
reached its capacity.

Example:

```text
Capacity = 4
Deque = [A, B, C, D]
```

Operations such as:

```text
addFirst(X)
```

or:

```text
addLast(X)
```

cannot succeed without additional capacity.

Possible strategies include:

- rejecting the insertion;
- throwing an exception;
- returning a failure result;
- dynamically increasing storage;
- applying an eviction policy in specialised systems.

Overflow behaviour must be defined explicitly.

---

## 16. Bounded and Dynamically Growing Deques

### 16.1 Bounded deque

A bounded deque has a fixed maximum number of elements.

Advantages may include:

- predictable memory use;
- controlled resource consumption;
- suitability for caches and buffers;
- explicit capacity limits.

Limitations include:

- overflow;
- rejected operations;
- required capacity planning.

### 16.2 Dynamically growing deque

A dynamically growing deque can expand when necessary.

Possible representations include:

- a resizable circular array;
- a linked structure.

Advantages may include:

- flexible capacity;
- adaptation to changing workloads.

Limitations may include:

- allocation overhead;
- occasional resizing cost;
- less predictable memory usage;
- possible uncontrolled growth.

Both variants must preserve the same double-ended behavioural contract.

---

## 17. Operation Trace

Tracing mixed operations helps clarify deque behaviour.

Initial state:

```text
[]
```

Operation:

```text
addLast(A)
```

State:

```text
[A]
```

Operation:

```text
addLast(B)
```

State:

```text
[A, B]
```

Operation:

```text
addFirst(C)
```

State:

```text
[C, A, B]
```

Operation:

```text
removeLast()
```

Returned value:

```text
B
```

State:

```text
[C, A]
```

Operation:

```text
peekFirst()
```

Returned value:

```text
C
```

State remains:

```text
[C, A]
```

Operation:

```text
addFirst(D)
```

State:

```text
[D, C, A]
```

Operation:

```text
removeFirst()
```

Returned value:

```text
D
```

Final state:

```text
[C, A]
```

This trace demonstrates that both boundaries remain independently accessible.

---

## 18. Formal Behavioural Model

A deque may be represented abstractly as a finite sequence:

```text
D = ⟨a₁, a₂, ..., aₙ⟩
```

where:

```text
a₁ = front element
aₙ = rear element
n  = current size
```

### Add first

For element `x`:

```text
addFirst(D, x)
```

produces:

```text
D' = ⟨x, a₁, a₂, ..., aₙ⟩
```

### Add last

```text
addLast(D, x)
```

produces:

```text
D' = ⟨a₁, a₂, ..., aₙ, x⟩
```

### Remove first

For a non-empty deque:

```text
removeFirst(D)
```

returns:

```text
a₁
```

and produces:

```text
D' = ⟨a₂, a₃, ..., aₙ⟩
```

### Remove last

```text
removeLast(D)
```

returns:

```text
aₙ
```

and produces:

```text
D' = ⟨a₁, a₂, ..., aₙ₋₁⟩
```

### Peek operations

```text
peekFirst(D) = a₁
```

```text
peekLast(D) = aₙ
```

Neither operation modifies `D`.

---

## 19. Preconditions and Postconditions

### 19.1 Add first

Preconditions:

- the input satisfies value constraints;
- capacity exists, or the structure can grow.

Postconditions:

- the new element becomes the front;
- size increases by one;
- previous elements preserve their relative order.

### 19.2 Add last

Preconditions:

- the input satisfies value constraints;
- capacity exists, or the structure can grow.

Postconditions:

- the new element becomes the rear;
- size increases by one;
- previous elements preserve their relative order.

### 19.3 Remove first

Precondition:

- the deque is not empty.

Postconditions:

- the previous front is returned and removed;
- size decreases by one;
- the next element becomes the front.

### 19.4 Remove last

Precondition:

- the deque is not empty.

Postconditions:

- the previous rear is returned and removed;
- size decreases by one;
- the predecessor becomes the rear.

### 19.5 Peek operations

Precondition:

- the deque is not empty.

Postconditions:

- the corresponding end element is returned;
- the deque remains unchanged.

---

## 20. Structural and Behavioural Invariants

A deque implementation must preserve a set of properties before and after every
valid operation.

### 20.1 Size invariant

```text
size ≥ 0
```

For a bounded deque:

```text
0 ≤ size ≤ capacity
```

### 20.2 Front invariant

When non-empty:

```text
front identifies the first logical element
```

### 20.3 Rear invariant

When non-empty:

```text
rear identifies the last logical element
```

### 20.4 Single-element invariant

When:

```text
size = 1
```

the front and rear identify the same logical element.

### 20.5 Empty-state invariant

When:

```text
size = 0
```

no removable or inspectable element exists at either end.

### 20.6 Order invariant

Operations at one end must not arbitrarily reorder the remaining elements.

### 20.7 Reachability invariant

In a linked implementation, all stored nodes must remain reachable from either
boundary through valid references.

### 20.8 Boundary invariant

In an array implementation, front and rear positions must remain within valid
storage bounds.

### 20.9 Size consistency invariant

The recorded size must equal the number of logically stored elements.

---

## 21. Correctness Reasoning

A deque operation is correct when it:

- satisfies its specification;
- modifies only the intended boundary;
- preserves element order;
- updates size correctly;
- preserves front and rear consistency;
- handles special states according to the contract.

Consider:

```text
addFirst(X)
```

Before:

```text
[A, B, C]
```

After:

```text
[X, A, B, C]
```

The operation is correct because:

1. `X` becomes the new front;
2. `C` remains the rear;
3. the order `A, B, C` remains unchanged;
4. size increases by one.

Now consider:

```text
removeLast()
```

Before:

```text
[X, A, B, C]
```

Returned:

```text
C
```

After:

```text
[X, A, B]
```

The operation is correct because:

1. only the rear element is removed;
2. `B` becomes the new rear;
3. `X` remains the front;
4. remaining order is preserved;
5. size decreases by one.

---

## 22. Expected Complexity

The fundamental deque operations are expected to execute in constant time.

| Operation | Expected time |
|---|---:|
| `addFirst` | O(1) |
| `addLast` | O(1) |
| `removeFirst` | O(1) |
| `removeLast` | O(1) |
| `peekFirst` | O(1) |
| `peekLast` | O(1) |
| `isEmpty` | O(1) |
| `size` | O(1) |

These costs assume:

- direct access to both ends;
- explicit size tracking;
- no traversal;
- no shifting of all elements.

### 22.1 Resizing consideration

A dynamically resized array may occasionally copy all elements to a larger
array.

A single insertion may then cost:

```text
O(n)
```

Across a sequence of insertions, end insertion is generally analysed as:

```text
O(1) amortised
```

---

## 23. Suitable Representations

A deque requires efficient modification at both ends.

### 23.1 Circular array

A circular array allows the front and rear indices to wrap around the underlying
storage.

Advantages may include:

- constant-time end operations;
- efficient memory locality;
- no node-allocation overhead;
- reuse of released positions.

Challenges include:

- modular index calculations;
- capacity management;
- correct resizing;
- distinguishing full and empty states.

### 23.2 Doubly linked list

A doubly linked list maintains references in both directions.

Advantages may include:

- direct access to both ends;
- constant-time insertion and removal;
- flexible growth.

Challenges include:

- additional memory per node;
- more reference updates;
- reduced cache locality;
- greater structural complexity.

The representation should support both boundaries directly.

---

## 24. Unsuitable Representations

Some representations may produce inefficient deque operations.

### Singly linked list with head only

This representation supports:

```text
addFirst = O(1)
removeFirst = O(1)
```

but rear removal may require traversal:

```text
removeLast = O(n)
```

### Linear array with shifting

Inserting or removing at the front may require moving all elements:

```text
O(n)
```

This violates the expected constant-time behaviour.

A suitable deque representation should avoid repeated traversal and mass
shifting.

---

## 25. Deque Used as a Stack

A deque can reproduce stack behaviour by using the same end for insertion and
removal.

For example, using the front:

```text
Push     = addFirst
Pop      = removeFirst
Peek     = peekFirst
```

Operation trace:

```text
addFirst(A) → [A]
addFirst(B) → [B, A]
addFirst(C) → [C, B, A]
removeFirst() returns C
```

Removal order:

```text
C, B, A
```

This is LIFO behaviour.

Alternatively, the rear may be used consistently:

```text
Push = addLast
Pop  = removeLast
```

The important requirement is that insertion and removal occur at the same end.

---

## 26. Deque Used as a Queue

A deque can reproduce standard queue behaviour by inserting at one end and
removing from the opposite end.

For example:

```text
Enqueue = addLast
Dequeue = removeFirst
Peek    = peekFirst
```

Operation trace:

```text
addLast(A) → [A]
addLast(B) → [A, B]
addLast(C) → [A, B, C]
removeFirst() returns A
```

Removal order:

```text
A, B, C
```

This is FIFO behaviour.

The opposite orientation is also logically possible:

```text
Enqueue = addFirst
Dequeue = removeLast
```

However, an implementation and codebase should use a clear and consistent
convention.

---

## 27. Deque versus Stack

A stack permits access through one end.

A deque permits access through both ends.

| Characteristic | Stack | Deque |
|---|---|---|
| Insertion ends | One | Two |
| Removal ends | One | Two |
| Primary behaviour | LIFO | Configurable |
| Directly visible boundaries | Top | Front and rear |
| Flexibility | Restricted | Greater |

A deque can act as a stack, but a stack intentionally exposes a smaller
interface.

When only LIFO behaviour is required, the stack abstraction may communicate
intent more clearly.

---

## 28. Deque versus Queue

A standard queue uses opposite ends according to a fixed policy.

```text
Insert at rear
Remove from front
```

A deque permits operations at both ends.

| Characteristic | Queue | Deque |
|---|---|---|
| Insert at front | No | Yes |
| Insert at rear | Yes | Yes |
| Remove from front | Yes | Yes |
| Remove from rear | No | Yes |
| Primary ordering | FIFO | Depends on operation sequence |

A deque can act as a queue, but its broader interface allows additional
behaviour.

---

## 29. Deque versus General List

A general list may support arbitrary indexed access and modification.

A deque restricts direct structural operations to its ends.

| Characteristic | General list | Deque |
|---|---|---|
| Insertion positions | Potentially arbitrary | Front or rear |
| Removal positions | Potentially arbitrary | Front or rear |
| Indexed access | Often supported | Not central |
| Access policy | General | Double-ended |
| Expected end-operation cost | Implementation-dependent | O(1) |

The restriction provides a clearer contract and enables specialised
implementations.

---

## 30. Restricted Deque Variants

Some deque variants intentionally limit operations.

### 30.1 Input-restricted deque

Insertion is permitted at only one end.

Removal remains permitted at both ends.

Conceptually:

```text
Insertion:
Rear only

Removal:
Front or rear
```

This structure may support controlled arrival while allowing flexible removal.

### 30.2 Output-restricted deque

Insertion is permitted at both ends.

Removal is permitted at only one end.

Conceptually:

```text
Insertion:
Front or rear

Removal:
Front only
```

These variants appear less frequently in general-purpose APIs but are useful for
understanding how operation restrictions define abstract data types.

---

## 31. Circular Deque Concept

An array-based deque is commonly implemented as a circular structure.

Suppose the physical array is:

```text
Index:  0   1   2   3   4   5
```

The logical elements may occupy wrapped positions:

```text
Index:  0   1   2   3   4   5
Value: [D] [E] [_] [_] [B] [C]
         ↑               ↑
        Rear            Front
```

The logical order may be:

```text
B → C → D → E
```

Circular indexing permits:

- insertion before physical index `0`;
- insertion after the final physical index;
- reuse of released storage;
- constant-time boundary movement.

The logical order is independent of the simple left-to-right physical layout.

---

## 32. Representative Applications

### 32.1 Sliding-window algorithms

A deque can maintain relevant candidates for a moving interval.

For example, in a sliding-window maximum problem, obsolete elements may be
removed from the front while weaker candidates are removed from the rear.

This requires efficient access to both ends.

---

### 32.2 Monotonic deque

A monotonic deque preserves elements in increasing or decreasing order.

It supports algorithms involving:

- range maxima;
- range minima;
- next candidate selection;
- window optimisation.

Elements may be removed from one end because they expire and from the other
because they are no longer useful.

---

### 32.3 Palindrome verification

A sequence may be inspected from both ends.

Conceptually:

```text
Compare front with rear
        ↓
Remove both
        ↓
Repeat
```

If every pair matches, the sequence may be a palindrome.

---

### 32.4 Undo and redo navigation

A deque may support controlled navigation through recent states or actions.

Depending on system requirements, states may be added and removed from either
end.

In many applications, two stacks may still provide a clearer design, so the
choice should follow the required operations.

---

### 32.5 Task scheduling

A scheduler may normally add tasks at one end but occasionally place urgent work
at the opposite end.

Conceptually:

```text
Normal task → rear
Urgent task → front
```

This provides controlled priority without implementing a complete priority
queue.

---

### 32.6 Work-stealing systems

In some concurrent execution models, a worker processes tasks from one end of
its deque while another worker steals tasks from the opposite end.

This reduces direct contention between operations.

Actual concurrent implementations require specialised synchronisation and are
more complex than the sequential deque studied here.

---

### 32.7 Bounded history

A fixed-capacity deque can retain only the most recent elements.

When full:

```text
Add new element at rear
        ↓
Remove oldest element from front
```

This pattern is useful for:

- recent events;
- command history;
- logs;
- cached states;
- rolling observations.

---

### 32.8 Double-ended buffering

Some systems require data to be inserted or consumed from either direction.

A deque provides a natural model when both boundaries have semantic importance.

---

## 33. Java Perspective

Java provides the `Deque<E>` interface.

A common implementation is:

```text
Deque<String> deque = new ArrayDeque<>();
```

Typical double-ended operations include:

```text
deque.addFirst("A");
deque.addLast("B");

String first = deque.peekFirst();
String last = deque.peekLast();

String removedFirst = deque.removeFirst();
String removedLast = deque.removeLast();
```

Java also provides method pairs with different failure behaviours.

Representative insertion methods include:

```text
addFirst / offerFirst
addLast  / offerLast
```

Representative removal methods include:

```text
removeFirst / pollFirst
removeLast  / pollLast
```

Representative inspection methods include:

```text
getFirst / peekFirst
getLast  / peekLast
```

The detailed semantics and implementation choices are studied in:

```text
06-java-deque.md
```

The central principle is:

> Understand double-ended behaviour before relying on the Java API.

---

## 34. Null Elements

Whether a deque accepts `null` depends on its contract and implementation.

Allowing `null` can create ambiguity.

Suppose:

```text
pollFirst() returns null
```

This may mean:

```text
The deque is empty
```

or:

```text
The front element stores null
```

A well-designed API should define:

- whether `null` is allowed;
- how empty-state operations behave;
- whether exceptions or special values are used.

Some Java implementations prohibit `null` to preserve unambiguous operation
results.

---

## 35. Mutability and Encapsulation

A deque should protect its internal state.

Client code should not be allowed to:

- modify front or rear indices directly;
- reconnect internal nodes;
- change size independently;
- access unused storage positions;
- bypass operation rules;
- create inconsistent boundary states.

Poor encapsulation could produce states such as:

```text
size = 1
front points to A
rear points to B
```

This violates the single-element invariant.

A well-designed deque exposes operations rather than its representation.

---

## 36. Automated Testing Perspective

A correct deque implementation should be validated through automated tests.

### Empty-state tests

- a newly created deque is empty;
- size is zero;
- removal from either end follows the contract;
- inspection at either end follows the contract.

### Front-insertion tests

- inserting into an empty deque sets both boundaries;
- multiple front insertions preserve expected order;
- size increases correctly.

### Rear-insertion tests

- inserting into an empty deque sets both boundaries;
- multiple rear insertions preserve expected order;
- size increases correctly.

### Front-removal tests

- the front element is returned;
- only one element is removed;
- the next element becomes the front;
- size decreases correctly.

### Rear-removal tests

- the rear element is returned;
- only one element is removed;
- the predecessor becomes the rear;
- size decreases correctly.

### Mixed-operation tests

- insertion and removal from alternating ends;
- transition from empty to non-empty;
- transition from one element to empty;
- preservation of logical order.

### Circular-array tests

- front index wraps correctly;
- rear index wraps correctly;
- released positions are reused;
- logical order survives wrap-around;
- resizing preserves order;
- full and empty states remain distinguishable.

### Generic-type tests

The deque should behave correctly with:

- integers;
- strings;
- domain objects;
- other permitted types.

---

## 37. Property-Based Reasoning

Deque behaviour can be described through general properties.

### Add-first/remove-first property

For any valid deque `D` and element `x`:

```text
addFirst(D, x)
removeFirst(D)
```

must return:

```text
x
```

and restore the previous logical contents of `D`.

### Add-last/remove-last property

```text
addLast(D, x)
removeLast(D)
```

must return:

```text
x
```

and restore the previous logical contents.

### Queue property

If elements are added using `addLast`:

```text
x₁, x₂, ..., xₙ
```

then repeated `removeFirst` operations return:

```text
x₁, x₂, ..., xₙ
```

### Stack property

If elements are added using `addFirst`:

```text
x₁, x₂, ..., xₙ
```

then repeated `removeFirst` operations return:

```text
xₙ, xₙ₋₁, ..., x₁
```

### Peek preservation property

For a non-empty deque:

```text
size before peek = size after peek
```

### Size property

After successful insertion:

```text
new size = previous size + 1
```

After successful removal:

```text
new size = previous size - 1
```

---

## 38. Common Conceptual Mistakes

Several fundamental mistakes should be recognised early.

### Confusing physical and logical order

In a circular array, the first logical element may not occupy physical index
zero.

### Updating only one boundary

When the deque becomes empty, both ends must enter a consistent empty state.

### Mishandling the single-element case

Removing the only element affects both front and rear.

### Moving all array elements

A circular representation should move indices rather than shift every element.

### Using a singly linked representation carelessly

Efficient removal at both ends generally requires direct access to predecessors
or a different representation.

### Inconsistent stack usage

Using different ends for push and pop produces queue-like behaviour rather than
LIFO behaviour.

### Inconsistent queue usage

Removing from the same end used for insertion produces stack-like behaviour.

### Losing logical order during resizing

Circular elements must be copied according to logical sequence, not simple
physical index order.

---

## 39. Problem-Recognition Guide

A deque may be appropriate when a problem involves:

- processing from both ends;
- adding urgent elements to the front;
- removing outdated elements from the front;
- removing dominated candidates from the rear;
- maintaining a sliding window;
- comparing first and last elements;
- acting as either a stack or queue;
- bounded recent history;
- double-ended scheduling;
- work stealing.

Useful questions include:

```text
Must elements be inserted at both ends?
```

```text
Must elements be removed from both ends?
```

```text
Does the algorithm discard obsolete elements from one end and inferior
candidates from the other?
```

```text
Would a stack or queue alone expose too few operations?
```

```text
Would a general list expose more operations than required?
```

If the answer is yes, a deque may be the appropriate abstraction.

---

## 40. Engineering Considerations

Selecting a deque requires evaluating more than double-ended access.

An engineer should consider:

- expected number of elements;
- fixed or dynamic capacity;
- memory locality;
- node-allocation overhead;
- resizing cost;
- failure semantics;
- permission or prohibition of `null`;
- concurrency requirements;
- bounded-buffer policies;
- standard-library alternatives;
- readability of the selected abstraction.

A deque should not be selected merely because it is more flexible.

Greater flexibility can also permit misuse.

When a problem requires only FIFO behaviour, declaring a variable through the
`Queue` interface may communicate intent more clearly.

When LIFO behaviour is required, using explicit stack-style deque operations may
be appropriate.

When both ends are essential, the full deque abstraction becomes justified.

---

## 41. Relationship with Other Module Documents

This document extends the concepts introduced in stack and queue fundamentals.

```text
01-stack-fundamentals.md
        ↓
Introduces one-ended LIFO processing
```

```text
02-queue-fundamentals.md
        ↓
Introduces opposite-ended FIFO processing
```

```text
03-deque-fundamentals.md
        ↓
Generalises controlled access to both ends
```

It prepares the learner for:

```text
04-stack-implementations.md
05-queue-implementations.md
06-java-deque.md
07-common-algorithms.md
08-complexity-analysis.md
09-problem-solving-guide.md
10-common-mistakes.md
```

It also supports later study involving:

- sliding-window algorithms;
- monotonic structures;
- trees;
- graphs;
- scheduling;
- concurrent systems.

---

## 42. Study Questions

After studying this document, the learner should be able to answer:

1. What defines a deque as an abstract data type?
2. Why is it called a double-ended queue?
3. Which operations are permitted at the front?
4. Which operations are permitted at the rear?
5. How can a deque reproduce stack behaviour?
6. How can a deque reproduce queue behaviour?
7. What happens when the only element is removed?
8. Which invariants must a deque preserve?
9. Why should end operations normally be O(1)?
10. Why is a circular array suitable for a deque?
11. Why may a doubly linked list be suitable?
12. Why can a singly linked list be inefficient for rear removal?
13. What is an input-restricted deque?
14. What is an output-restricted deque?
15. How does a deque support sliding-window algorithms?
16. How does a deque differ from a general list?
17. What test scenarios verify correct double-ended behaviour?
18. Why must logical order be preserved during array resizing?
19. When should an engineer prefer a queue interface over a deque interface?
20. Why should the abstract behaviour be understood before using Java's
    `Deque`?

---

## 43. Completion Criteria

This document may be considered complete when the learner can:

- define a deque independently of a programming language;
- identify the front and rear;
- explain every fundamental operation;
- trace mixed operations at both ends;
- define operation preconditions and postconditions;
- identify structural and behavioural invariants;
- explain underflow and overflow;
- distinguish bounded and dynamically growing deques;
- demonstrate stack and queue behaviour using deque operations;
- compare circular-array and linked representations;
- analyse expected operation complexity;
- recognise representative applications;
- identify deque-oriented problems;
- design automated tests for normal and boundary cases.

---

## 44. Key Takeaways

After completing this document, the learner should understand that:

- a deque is a double-ended linear abstract data type;
- insertion, removal, and inspection may occur at both ends;
- the front and rear are distinct logical boundaries;
- a deque can reproduce stack behaviour;
- a deque can reproduce queue behaviour;
- correct implementations preserve order, size, and boundary invariants;
- the empty and single-element states require careful handling;
- fundamental end operations should normally execute in constant time;
- circular arrays and doubly linked lists are common deque representations;
- unsuitable representations may introduce traversal or shifting costs;
- deques support sliding windows, monotonic algorithms, bounded histories,
  scheduling, and double-ended processing;
- automated tests should verify both individual operations and general
  behavioural properties;
- the full deque abstraction should be selected only when its additional
  flexibility is genuinely required.

Understanding these fundamentals prepares the learner to study concrete stack
and queue implementations, use Java's `Deque` interface correctly, and solve
algorithms requiring efficient access to both ends of a sequence.