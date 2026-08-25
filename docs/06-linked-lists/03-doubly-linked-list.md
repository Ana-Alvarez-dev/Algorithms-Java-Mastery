# Doubly Linked Lists

## Algorithms Java Mastery

A **doubly linked list** is a dynamic linear data structure in which each node
maintains references to both its predecessor and its successor.

Unlike a singly linked list, whose navigation is restricted to one direction, a
doubly linked list supports traversal in both forward and backward directions.
This additional flexibility simplifies many structural operations while
requiring each node to maintain an additional reference.

The introduction of predecessor references changes the computational behaviour
of several algorithms and reduces the amount of information required to perform
certain modifications.

Understanding doubly linked lists extends the concepts introduced in singly
linked lists and provides the conceptual foundation for many practical data
structures used throughout software engineering.

---

# Academic Foundation

The concepts presented throughout this document are primarily based on the study
of linked lists introduced in *Introduction to Algorithms* by Cormen,
Leiserson, Rivest and Stein, where doubly linked lists are presented as a
dynamic representation that simplifies insertion and deletion through
bidirectional node relationships.

The explanations provided throughout this repository are intentionally original
and are supported by the academic references maintained in:

```text
docs/00-project/10-references.md
```

---

# Purpose

The purpose of this document is to study the organisation, behaviour and
engineering characteristics of doubly linked lists.

The learner will understand:

- bidirectional node relationships;
- forward and backward traversal;
- insertion and deletion;
- structural invariants;
- computational complexity;
- engineering trade-offs.

The emphasis remains on understanding the structure before implementing its
algorithms.

---

# Learning Objectives

After completing this document, the learner should be able to:

## Conceptual understanding

- explain the structure of a doubly linked list;
- distinguish singly and doubly linked lists;
- understand predecessor and successor relationships;
- explain bidirectional traversal.

## Structural reasoning

- analyse structural modifications;
- reason about node connectivity;
- preserve structural integrity during updates.

## Engineering perspective

- compare memory requirements;
- evaluate insertion and deletion costs;
- determine when doubly linked lists are preferable.

---

# Conceptual Model

Each node stores three conceptual components.

- application data;
- reference to the previous node;
- reference to the next node.

Conceptually:

```text
null
  │
  ▼
┌──────────┐
│ ● │Data│ ● │
└──────────┘
   ▲     │
   │     ▼
┌──────────┐
│ ● │Data│ ● │
└──────────┘
   ▲     │
   │     ▼
┌──────────┐
│ ● │Data│ ∅ │
└──────────┘
```

The first node has no predecessor.

The last node has no successor.

Every intermediate node maintains references in both directions.

---

# Bidirectional Relationships

The defining characteristic of a doubly linked list is that every connection is
represented in two directions.

Conceptually:

```text
A ◄────► B ◄────► C ◄────► D
```

Each node is aware of both neighbouring nodes.

This organisation simplifies several structural operations because the
predecessor of a node can be accessed directly.

---

# Memory Organisation

As with singly linked lists, nodes are allocated independently.

The logical order is determined exclusively by references.

Unlike singly linked lists, however, every node participates in two structural
relationships.

```text
Previous
     ▲
     │
Node
     │
     ▼
 Next
```

This additional reference increases memory consumption while improving
navigation flexibility.

---

# Forward Traversal

Traversal may begin at the head and repeatedly follow the next reference.

```text
Head

A → B → C → D
```

This process is equivalent to traversal in a singly linked list.

---

# Backward Traversal

Because predecessor references are available, traversal may also begin at the
tail.

```text
Tail

D → C → B → A
```

Bidirectional navigation is one of the principal advantages of this structure.

---

# Insertion

Insertion consists of creating a new node and updating the surrounding
references.

Unlike singly linked lists, insertion generally requires updating four
references.

Before:

```text
A ◄────► C
```

After:

```text
A ◄────► B ◄────► C
```

Correct insertion preserves the connectivity of every neighbouring node.

---

# Deletion

Deletion removes a node while reconnecting its predecessor and successor.

Before:

```text
A ◄────► B ◄────► C
```

After removing **B**

```text
A ◄────────────► C
```

The removed node becomes disconnected from the logical structure.

Both neighbouring nodes must be updated consistently.

---

# Complexity Analysis

The computational complexity of the principal operations is shown below.

| Operation | Worst-Case Complexity |
|-----------|----------------------:|
| Access by position | O(n) |
| Search | O(n) |
| Traversal | O(n) |
| Insert at head | O(1) |
| Insert before known node | O(1) |
| Insert after known node | O(1) |
| Delete known node | O(1) |

The additional predecessor reference does not reduce traversal complexity.

Its principal benefit is simplifying structural modification.

---

# Comparison with Singly Linked Lists

| Characteristic | Singly | Doubly |
|---------------|--------|---------|
| References per node | 1 | 2 |
| Forward traversal | Yes | Yes |
| Backward traversal | No | Yes |
| Delete known node | Requires predecessor | Direct |
| Memory usage | Lower | Higher |

Neither representation is universally preferable.

The selection depends on application requirements.

---

# Structural Invariants

Every doubly linked list should preserve the following properties.

- the head has no predecessor;
- the tail has no successor;
- every predecessor reference is consistent;
- every successor reference is consistent;
- every node is reachable;
- every connection is reciprocal.

Maintaining these invariants guarantees structural correctness.

---

# Java Perspective

Within this repository, doubly linked lists are implemented manually before
studying Java library implementations.

The objective is to understand:

- bidirectional references;
- structural modification;
- reference consistency;
- implementation trade-offs.

Only after understanding these concepts should the learner analyse
`java.util.LinkedList`.

---

# Relationship with Automated Testing

Representative test scenarios include:

- empty list;
- single-node list;
- forward traversal;
- backward traversal;
- insertion;
- deletion;
- head modification;
- tail modification;
- consistency of predecessor references;
- consistency of successor references.

Testing should verify both functional behaviour and structural integrity.

---

# Engineering Considerations

Doubly linked lists provide greater flexibility than singly linked lists at the
cost of increased memory consumption.

Applications requiring:

- bidirectional traversal;
- efficient deletion of known nodes;
- navigation history;
- deque implementations;

often benefit from this representation.

The choice should always be guided by computational requirements rather than by
implementation convenience.

---

# Key Takeaways

After studying this document, the learner should understand that:

- doubly linked lists extend singly linked lists through predecessor
  references;
- bidirectional navigation simplifies several algorithms;
- structural correctness depends on maintaining reciprocal references;
- additional flexibility requires additional memory;
- engineering decisions involve balancing structural simplicity against memory
  overhead.

These concepts provide the theoretical foundation required for studying
circular linked lists and more advanced dynamic data structures throughout the
remainder of this repository.