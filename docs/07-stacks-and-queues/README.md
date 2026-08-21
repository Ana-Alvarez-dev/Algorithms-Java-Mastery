# Stacks and Queues

## Algorithms Java Mastery

This module studies **stacks**, **queues**, and **double-ended queues** as
fundamental linear data structures used to organise, process, and control the
order in which elements are accessed.

Unlike arrays and linked lists, which primarily describe how data is stored,
stacks and queues introduce **restricted access policies**. These policies
determine which element may be removed or processed next.

A stack follows the **Last In, First Out** principle:

```text
LIFO — Last In, First Out
```

A queue follows the **First In, First Out** principle:

```text
FIFO — First In, First Out
```

A deque allows insertion and removal from both ends:

```text
Front ⇄ Elements ⇄ Rear
```

These structures appear throughout algorithm design, operating systems,
compilers, networking, concurrent systems, web applications, and backend
development.

The module follows the central principle of this repository:

> **A data structure should be understood conceptually, specified through its
operations, analysed mathematically, implemented carefully, tested
systematically, and evaluated from an engineering perspective.**

---

## 1. Module Purpose

The purpose of this module is to develop a rigorous understanding of stacks and
queues before relying on the abstractions provided by the Java Collections
Framework.

The study begins with the behavioural rules of each structure and progresses
toward:

- implementation strategies;
- Java abstractions;
- classical algorithms;
- complexity analysis;
- systematic problem solving;
- common implementation errors.

The objective is not merely to learn methods such as `push`, `pop`, `offer`, or
`poll`.

The learner should understand:

- why these operations exist;
- what ordering policy they preserve;
- how their implementation affects performance;
- which structure is appropriate for a particular problem;
- how structural correctness can be verified.

---

## 2. Academic Foundation

Stacks and queues are abstract data types whose behaviour is defined primarily
by the operations they permit and the order in which elements are processed.

Their implementations may rely on different underlying representations,
including:

- arrays;
- dynamically resized arrays;
- singly linked lists;
- doubly linked lists;
- circular buffers.

Although these representations differ internally, each implementation must
preserve the behavioural contract of the corresponding abstract data type.

The academic treatment of this module is based on classical concepts from
algorithms and data structures literature. The explanations, examples,
diagrams, and engineering interpretations developed in this repository are
original and adapted to the learning objectives of **Algorithms Java Mastery**.

The complete bibliographic foundation of the repository is maintained in the
project documentation.

```text
docs/00-project/
```

---

## 3. Learning Objectives

After completing this module, the learner should be able to achieve the
following objectives.

### 3.1 Conceptual understanding

- define stacks, queues, and deques;
- explain LIFO and FIFO processing;
- distinguish an abstract data type from its implementation;
- describe the role of the front, rear, and top positions;
- recognise the behavioural differences between the structures.

### 3.2 Algorithmic reasoning

- determine when a problem requires a stack or a queue;
- trace the state of a structure during an algorithm;
- identify ordering requirements;
- use one or more auxiliary structures to solve a problem;
- reason about underflow, overflow, and boundary conditions.

### 3.3 Complexity analysis

- analyse insertion and removal operations;
- distinguish worst-case from amortised complexity;
- compare array-based and linked implementations;
- evaluate time-space trade-offs;
- identify operations that violate the intended constant-time behaviour.

### 3.4 Java implementation

- implement stacks and queues manually;
- use generic types safely;
- apply Java interfaces such as `Queue` and `Deque`;
- distinguish between exception-based and special-value operations;
- select an appropriate Java collection for each use case.

### 3.5 Software engineering

- preserve structural invariants;
- handle invalid operations explicitly;
- design automated tests;
- document behavioural contracts;
- justify data-structure decisions according to system requirements.

---

## 4. Module Structure

```text
07-stacks-and-queues/
│
├── README.md
│
├── 01-stack-fundamentals.md
├── 02-queue-fundamentals.md
├── 03-deque-fundamentals.md
│
├── 04-stack-implementations.md
├── 05-queue-implementations.md
├── 06-java-deque.md
│
├── 07-common-algorithms.md
├── 08-complexity-analysis.md
├── 09-problem-solving-guide.md
└── 10-common-mistakes.md
```

The documents are organised progressively.

```text
Conceptual Foundations
          ↓
Behavioural Rules
          ↓
Implementation Strategies
          ↓
Java Abstractions
          ↓
Common Algorithms
          ↓
Complexity Analysis
          ↓
Problem-Solving Method
          ↓
Common Mistakes
```

---

## 5. Document Overview

### `01-stack-fundamentals.md`

Introduces the stack abstract data type and the **Last In, First Out** ordering
policy.

The document studies:

- the top of the stack;
- push and pop operations;
- inspection through peek;
- empty-stack behaviour;
- structural invariants;
- representative applications.

The purpose is to understand stack behaviour independently of any particular
implementation language.

---

### `02-queue-fundamentals.md`

Introduces the queue abstract data type and the **First In, First Out** ordering
policy.

The document examines:

- the front of the queue;
- the rear of the queue;
- enqueue and dequeue operations;
- queue inspection;
- empty-queue behaviour;
- common queue applications.

It establishes the conceptual foundation required to analyse scheduling,
buffering, and ordered request processing.

---

### `03-deque-fundamentals.md`

Studies the double-ended queue, commonly known as a **deque**.

A deque permits insertion and removal from both ends:

```text
Front ⇄ Deque ⇄ Rear
```

This document explains:

- front insertion;
- rear insertion;
- front removal;
- rear removal;
- stack-like use;
- queue-like use;
- restricted deque variants.

The deque is studied as a flexible abstract data type rather than merely as a
Java class.

---

### `04-stack-implementations.md`

Examines the principal strategies for implementing a stack.

The document compares:

- fixed-capacity arrays;
- dynamically resized arrays;
- linked nodes;
- capacity management;
- overflow and underflow;
- memory and performance trade-offs.

The objective is to demonstrate that the same abstract behaviour may be
implemented through different physical representations.

---

### `05-queue-implementations.md`

Examines common strategies for implementing queues.

The document studies:

- linear array representations;
- circular queues;
- dynamically resized buffers;
- linked queues;
- front and rear reference management;
- capacity and memory considerations.

Special attention is given to circular indexing because it prevents unnecessary
element movement in array-based queues.

---

### `06-java-deque.md`

Connects the theoretical study of stacks, queues, and deques with the Java
Collections Framework.

The document examines:

- `Queue`;
- `Deque`;
- `ArrayDeque`;
- `LinkedList`;
- method pairs such as `add` and `offer`;
- method pairs such as `remove` and `poll`;
- method pairs such as `element` and `peek`;
- recommended stack and queue practices in modern Java.

The focus remains on selecting an abstraction according to behaviour and
engineering requirements rather than memorising collection methods.

---

### `07-common-algorithms.md`

Presents representative algorithms based on stacks and queues.

Possible topics include:

- delimiter validation;
- expression processing;
- postfix evaluation;
- infix-to-postfix conversion;
- reversing elements;
- monotonic stacks;
- next greater element;
- queue reversal;
- breadth-first processing;
- sliding-window techniques;
- stack construction using queues;
- queue construction using stacks.

Each algorithm is studied through:

```text
Problem
   ↓
Strategy
   ↓
Operations
   ↓
Correctness
   ↓
Complexity
```

---

### `08-complexity-analysis.md`

Analyses the computational cost of stack, queue, and deque operations.

The document compares:

- array-based implementations;
- linked implementations;
- fixed-capacity structures;
- dynamic resizing;
- circular-buffer operations;
- worst-case and amortised behaviour;
- auxiliary-space requirements.

The purpose is to explain not only the resulting complexity, but also the reason
behind each cost.

---

### `09-problem-solving-guide.md`

Provides a systematic methodology for solving problems involving stacks and
queues.

The document helps the learner determine:

- whether processing order is LIFO or FIFO;
- which operations must be supported;
- whether a deque offers a more suitable abstraction;
- how to trace state changes;
- how to preserve invariants;
- how to identify edge cases;
- how to evaluate complexity;
- how to verify the solution through tests.

This document emphasises reusable problem-solving strategies rather than
memorised solutions.

---

### `10-common-mistakes.md`

Examines frequent conceptual and implementation errors.

Representative mistakes include:

- confusing LIFO with FIFO behaviour;
- removing from the wrong end;
- ignoring empty-structure operations;
- mishandling front, rear, or top references;
- implementing an inefficient array queue;
- introducing off-by-one errors in circular buffers;
- confusing capacity with current size;
- selecting an unsuitable Java collection;
- modifying a structure incorrectly during traversal;
- testing only typical cases.

The objective is to understand why these errors occur and how disciplined
engineering practices can prevent them.

---

## 6. Core Concepts

### 6.1 Stack

A stack processes the most recently inserted element first.

```text
        Top
         ↓
      +-----+
      |  D  | ← Most recently inserted
      +-----+
      |  C  |
      +-----+
      |  B  |
      +-----+
      |  A  | ← First inserted
      +-----+
```

Typical operations are:

| Operation | Purpose |
|---|---|
| `push` | Insert an element at the top |
| `pop` | Remove and return the top element |
| `peek` | Inspect the top element without removing it |
| `isEmpty` | Determine whether the stack contains elements |
| `size` | Return the number of stored elements |

Conceptual processing rule:

```text
Last inserted
      ↓
First removed
```

---

### 6.2 Queue

A queue processes the earliest inserted element first.

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | →  |  B  | →  |  C  | →  |  D  |
+-----+    +-----+    +-----+    +-----+
```

Typical operations are:

| Operation | Purpose |
|---|---|
| `enqueue` | Insert an element at the rear |
| `dequeue` | Remove and return the front element |
| `peek` | Inspect the front element without removing it |
| `isEmpty` | Determine whether the queue contains elements |
| `size` | Return the number of stored elements |

Conceptual processing rule:

```text
First inserted
      ↓
First removed
```

---

### 6.3 Deque

A deque supports operations at both ends.

```text
Front                              Rear
  ↓                                  ↓
+-----+    +-----+    +-----+    +-----+
|  A  | ⇄  |  B  | ⇄  |  C  | ⇄  |  D  |
+-----+    +-----+    +-----+    +-----+
```

Typical operations include:

| Operation | Purpose |
|---|---|
| `addFirst` | Insert at the front |
| `addLast` | Insert at the rear |
| `removeFirst` | Remove from the front |
| `removeLast` | Remove from the rear |
| `peekFirst` | Inspect the front element |
| `peekLast` | Inspect the rear element |

A deque can support both stack and queue behaviour.

```text
Deque used as a stack
        ↓
Insert and remove from the same end
```

```text
Deque used as a queue
        ↓
Insert at one end and remove from the other
```

---

## 7. Abstract Data Type and Implementation

A stack or queue is not defined by whether it uses an array or a linked list.

It is defined by its behavioural contract.

For example, a stack may be implemented using:

```text
Stack
 ├── Fixed array
 ├── Dynamic array
 └── Linked nodes
```

A queue may be implemented using:

```text
Queue
 ├── Circular array
 ├── Dynamic circular buffer
 └── Linked nodes
```

The abstraction defines **what operations mean**.

The implementation defines **how those operations are performed**.

Maintaining this distinction is essential for understanding data structures and
designing reusable software.

---

## 8. Structural Invariants

Correct implementations must preserve a set of properties before and after
every operation.

### Stack invariants

Typical stack invariants include:

- the top identifies the next element to be removed;
- the size matches the number of stored elements;
- no element outside the active range is considered part of the stack;
- an empty stack has no valid top element.

### Queue invariants

Typical queue invariants include:

- the front identifies the next element to be removed;
- the rear identifies the insertion boundary;
- elements preserve FIFO order;
- the size matches the number of stored elements;
- empty and full states are distinguishable in bounded queues.

### Deque invariants

Typical deque invariants include:

- both ends remain valid;
- insertion and removal preserve element order;
- front and rear operations update size consistently;
- an empty deque has no removable element.

An implementation is not correct merely because it produces the expected value
for one example. It must preserve its invariants for every valid operation.

---

## 9. Representative Applications

### Stack applications

Stacks are commonly used in:

- method-call management;
- recursion;
- expression evaluation;
- syntax validation;
- undo operations;
- backtracking;
- depth-first search;
- browser navigation history.

### Queue applications

Queues are commonly used in:

- task scheduling;
- request processing;
- print systems;
- message processing;
- network buffering;
- breadth-first search;
- producer-consumer systems;
- event-driven architectures.

### Deque applications

Deques are commonly used in:

- sliding-window algorithms;
- monotonic queue techniques;
- work-stealing systems;
- double-ended scheduling;
- history navigation;
- palindrome analysis;
- bounded buffers.

These examples demonstrate that stacks and queues are not isolated academic
structures. They model recurring processing policies found in real software
systems.

---

## 10. Java Perspective

Java provides several interfaces and implementations related to this module.

```text
Collection
    │
    └── Queue
          │
          └── Deque
```

Common implementations include:

```text
ArrayDeque
LinkedList
PriorityQueue
```

However, these classes do not all provide the same ordering guarantees.

For example, a priority queue does not behave as a standard FIFO queue because
element priority determines removal order.

Within this module, manual implementations are studied before standard-library
implementations so that abstractions do not hide the underlying reasoning.

The general progression is:

```text
Understand the ADT
        ↓
Implement it manually
        ↓
Verify its invariants
        ↓
Analyse its complexity
        ↓
Study the Java abstraction
        ↓
Select the appropriate implementation
```

---

## 11. Complexity Perspective

The principal stack and queue operations are generally expected to run in
constant time.

| Structure | Operation | Expected time |
|---|---|---:|
| Stack | Push | O(1) |
| Stack | Pop | O(1) |
| Stack | Peek | O(1) |
| Queue | Enqueue | O(1) |
| Queue | Dequeue | O(1) |
| Queue | Peek | O(1) |
| Deque | Insert at either end | O(1) |
| Deque | Remove from either end | O(1) |

These costs depend on the implementation.

For example:

- resizing a dynamic array may produce an occasional O(n) operation;
- shifting every array element during a dequeue operation creates an
  inefficient O(n) implementation;
- traversing a linked structure to find the rear makes enqueue O(n) unless a
  rear reference is maintained.

Therefore, complexity must be derived from the implementation rather than
assumed from the abstract data type.

---

## 12. Relationship with Previous Modules

This module builds directly upon concepts studied in earlier sections of the
repository.

### Foundations

The learner applies:

- problem specification;
- preconditions and postconditions;
- correctness reasoning;
- structural invariants;
- edge-case analysis.

### Complexity

The learner applies:

- input-size analysis;
- significant-operation counting;
- asymptotic notation;
- best, average, and worst cases;
- auxiliary-space analysis;
- amortised reasoning.

### Arrays

Arrays provide one of the principal physical representations for stacks and
queues.

Relevant concepts include:

- contiguous storage;
- fixed capacity;
- dynamic resizing;
- indexed access;
- circular indexing.

### Linked lists

Linked structures provide another implementation strategy.

Relevant concepts include:

- node references;
- head and tail management;
- constant-time insertion and deletion;
- dynamic memory organisation.

The conceptual dependency can be represented as:

```text
Foundations
     ↓
Complexity
     ↓
Arrays ──────────┐
                 ├──→ Stacks and Queues
Linked Lists ────┘
```

---

## 13. Relationship with Future Modules

Stacks and queues also prepare the learner for more advanced topics.

### Trees

Stacks support:

- iterative depth-first traversal;
- expression-tree evaluation;
- path reconstruction.

Queues support:

- level-order traversal;
- breadth-first processing.

### Graphs

Stacks are central to:

- depth-first search;
- cycle-detection strategies;
- topological-processing techniques.

Queues are central to:

- breadth-first search;
- shortest paths in unweighted graphs;
- traversal by distance levels.

### Recursion and backtracking

The runtime call stack explains how recursive algorithms preserve execution
state.

Explicit stacks can replace recursion when greater control over memory and
execution is required.

### Dynamic programming and parsing

Stacks and queues support:

- expression parsing;
- state exploration;
- dependency processing;
- staged computation.

---

## 14. Study Methodology

Each topic should be studied through the complete workflow defined by the
repository.

```text
Problem Understanding
        ↓
Formal Specification
        ↓
Conceptual Modelling
        ↓
Operation Definition
        ↓
Representation Selection
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Experimental Evaluation
```

The learner should avoid beginning with Java syntax.

Before implementation, it should be possible to explain:

- the structure's ordering policy;
- the permitted operations;
- the expected result of each operation;
- the required invariants;
- the expected computational cost.

---

## 15. Recommended Study Sequence

The module should be studied in the following order:

```text
1. Read the stack fundamentals.
2. Read the queue fundamentals.
3. Study deque behaviour.
4. Compare stack implementations.
5. Compare queue implementations.
6. Relate the concepts to Java Deque.
7. Analyse common algorithms.
8. Derive operation complexity.
9. Apply the problem-solving methodology.
10. Review common mistakes.
11. Implement the structures manually.
12. Create automated tests.
13. Compare implementations experimentally.
```

Each stage depends on the conceptual understanding developed in the previous
one.

---

## 16. Implementation Direction

The Java source code associated with this module should separate the abstract
behaviour from implementation details.

A possible package organisation is:

```text
src/main/java/org/anaalvarez/algorithms/
└── stacksandqueues/
    ├── stack/
    │   ├── Stack.java
    │   ├── ArrayStack.java
    │   └── LinkedStack.java
    │
    ├── queue/
    │   ├── Queue.java
    │   ├── CircularArrayQueue.java
    │   └── LinkedQueue.java
    │
    ├── deque/
    │   └── CustomDeque.java
    │
    └── algorithms/
        ├── DelimiterValidator.java
        ├── PostfixEvaluator.java
        ├── NextGreaterElement.java
        └── QueueReverser.java
```

The final package structure may evolve as the implementation grows, but it
should preserve:

- clear responsibilities;
- meaningful names;
- separation between structures and algorithms;
- generic and reusable components;
- testability.

---

## 17. Testing Direction

Automated tests should verify both observable behaviour and structural
correctness.

A possible test organisation is:

```text
src/test/java/org/anaalvarez/algorithms/
└── stacksandqueues/
    ├── stack/
    ├── queue/
    ├── deque/
    └── algorithms/
```

Representative test scenarios include:

### Stack tests

- creation of an empty stack;
- insertion of one element;
- insertion of multiple elements;
- removal in LIFO order;
- inspection without removal;
- removal from an empty stack;
- capacity growth;
- size consistency.

### Queue tests

- creation of an empty queue;
- insertion of one element;
- insertion of multiple elements;
- removal in FIFO order;
- inspection without removal;
- removal from an empty queue;
- circular wrap-around;
- size consistency.

### Deque tests

- insertion at both ends;
- removal from both ends;
- mixed operations;
- transition to and from an empty state;
- single-element boundary behaviour.

### Algorithm tests

- empty inputs;
- one-element inputs;
- typical cases;
- malformed expressions;
- repeated elements;
- boundary values;
- invalid operations.

Testing should provide evidence that the implementation satisfies its contract
rather than merely demonstrating that the code executes.

---

## 18. Benchmarking Direction

Benchmarking may be used after correctness has been established.

Possible comparisons include:

- array-based stack versus linked stack;
- circular-array queue versus linked queue;
- `ArrayDeque` versus `LinkedList`;
- fixed-capacity versus dynamically resized structures;
- recursive processing versus explicit-stack processing.

JMH benchmarks should be designed carefully to avoid measuring unrelated costs.

Theoretical complexity remains the primary analytical model.

Empirical results are used to investigate:

- constant-factor differences;
- memory allocation;
- cache locality;
- resizing behaviour;
- effects of input size.

---

## 19. Engineering Questions

During the module, the learner should be able to answer questions such as:

- Does the problem require LIFO or FIFO processing?
- Is insertion or removal required at one end or both ends?
- Is the maximum capacity known?
- Should the structure grow dynamically?
- Is predictable memory usage important?
- Is random access required?
- Would an array or linked representation be more appropriate?
- Is thread safety required?
- Should invalid operations throw exceptions or return special values?
- Does the Java standard library already provide the required abstraction?

These questions transform data-structure selection into an engineering decision.

---

## 20. Completion Criteria

The module may be considered complete when the learner can:

- explain stack, queue, and deque behaviour without relying on Java syntax;
- differentiate LIFO and FIFO ordering;
- implement a stack using an array and linked nodes;
- implement a queue using a circular array and linked nodes;
- preserve structural invariants;
- analyse operation complexity;
- use `Deque` appropriately in Java;
- solve representative algorithms;
- create automated tests for normal and exceptional conditions;
- justify implementation decisions;
- identify and correct common structural mistakes.

Completion is demonstrated through understanding, implementation, testing, and
analysis rather than through document reading alone.

---

## 21. Key Takeaways

After completing this module, the learner should understand that:

- stacks and queues are abstract data types defined by ordering policies;
- stacks process elements according to LIFO behaviour;
- queues process elements according to FIFO behaviour;
- deques support controlled access from both ends;
- the same abstract data type can have multiple implementations;
- representation choices affect complexity, memory usage, and performance;
- array and linked implementations involve different trade-offs;
- Java's `Deque` interface can represent both stack and queue behaviour;
- correctness depends on preserving behavioural and structural invariants;
- automated testing verifies the contract of each operation;
- benchmarking complements but does not replace theoretical analysis;
- selecting a data structure is an engineering decision based on the dominant
  operations and system requirements.

This module establishes the foundation required to understand traversal,
scheduling, parsing, recursion, breadth-first search, depth-first search, and
many other algorithms studied later in **Algorithms Java Mastery**.