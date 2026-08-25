# Circular Linked Lists

## Algorithms Java Mastery

A **circular linked list** is a dynamic linear data structure in which the last
node is connected back to the first node, forming a continuous cycle instead of
terminating with a `null` reference.

Unlike traditional linked lists, a circular linked list has no natural
beginning or end from the perspective of traversal. Every node belongs to a
closed sequence, allowing algorithms to continue navigating indefinitely until
an explicit stopping condition is satisfied.

Circular linked lists preserve the flexibility of linked structures while
introducing a different organisational model that is particularly useful for
cyclic processing, scheduling algorithms, buffering systems and queue
implementations.

Understanding circular linked lists extends the study of linked data
structures and illustrates how modifying a single structural relationship can
significantly change algorithmic behaviour.

---

# Academic Foundation

The concepts presented throughout this document are inspired by the study of
linked lists and their variants described in *Introduction to Algorithms* by
Cormen, Leiserson, Rivest and Stein, where circular lists are introduced as one
possible organisation of dynamic linked structures.

The explanations contained in this repository are intentionally original and
supported by the academic references documented in:

```text
docs/00-project/10-references.md
```

---

# Purpose

The purpose of this document is to understand how circular linked lists differ
from linear linked lists and how their structural organisation influences
algorithm design.

The learner will study:

- circular node relationships;
- cyclic traversal;
- insertion and deletion;
- structural invariants;
- computational complexity;
- engineering applications.

---

# Learning Objectives

After completing this document, the learner should be able to:

## Conceptual understanding

- explain what a circular linked list is;
- distinguish circular and linear linked lists;
- understand cyclic navigation;
- identify situations where circular structures are appropriate.

## Structural reasoning

- analyse cyclic node relationships;
- preserve structural integrity;
- reason about traversal termination.

## Engineering perspective

- compare circular and linear structures;
- evaluate their computational characteristics;
- select appropriate use cases.

---

# Conceptual Model

A circular linked list connects its final node back to the first node.

Conceptually:

```text
        ┌─────────────────────┐
        │                     │
        ▼                     │
Head → A ───► B ───► C ───► D
        ▲                     │
        └─────────────────────┘
```

Unlike linear linked lists, there is no `null` reference marking the end of the
structure.

Instead, traversal eventually returns to the starting node.

---

# Circular Structure

The defining characteristic of this data structure is the cyclic relationship
between nodes.

Each node references exactly one successor.

The successor of the final node is the first node.

Conceptually:

```text
A → B → C → D
↑           │
└───────────┘
```

This organisation creates a continuous logical sequence.

---

# Memory Organisation

As with other linked structures, nodes are allocated independently in memory.

The logical organisation depends entirely on references.

Physical memory may appear as:

```text
Node C

Node A

Node D

Node B
```

while the logical organisation remains:

```text
A → B → C → D → A
```

The physical location of nodes does not determine their logical order.

---

# Traversal

Traversal begins at an arbitrary node, commonly the head.

Unlike linear linked lists, traversal cannot terminate by encountering a
`null` reference.

Instead, algorithms must explicitly detect when the traversal has returned to
its starting point.

Conceptually:

```text
Head

A → B → C → D
↑           │
└───────────┘
```

Traversal therefore requires a stopping condition such as:

- returning to the head;
- processing a known number of nodes;
- satisfying an application-specific condition.

---

# Insertion

Insertion creates a new node while preserving the circular structure.

For example, inserting node **X** between **B** and **C**:

Before:

```text
A → B → C → D
↑           │
└───────────┘
```

After:

```text
A → B → X → C → D
↑               │
└───────────────┘
```

The circular connection must remain intact after the modification.

---

# Deletion

Deletion removes a node while preserving the cycle.

Before:

```text
A → B → C → D
↑           │
└───────────┘
```

After removing **C**

```text
A → B → D
↑       │
└───────┘
```

Every remaining node must continue to participate in the circular sequence.

---

# Complexity Analysis

The principal operations exhibit the following theoretical complexity.

| Operation | Worst-Case Complexity |
|-----------|----------------------:|
| Traversal | O(n) |
| Search | O(n) |
| Access by position | O(n) |
| Insert after known node | O(1) |
| Delete after known predecessor | O(1) |

As with other linked lists, locating a position generally dominates the overall
cost of structural modifications.

---

# Comparison with Linear Linked Lists

| Characteristic | Linear List | Circular List |
|---------------|-------------|---------------|
| Final node | References `null` | References first node |
| Traversal | Ends naturally | Requires explicit stopping condition |
| Continuous navigation | No | Yes |
| Cyclic processing | Limited | Natural |

The primary distinction lies in the organisation of the final node.

---

# Structural Invariants

Every circular linked list should preserve the following properties.

- every node has exactly one successor;
- every node remains reachable;
- the final node references the first node;
- no node becomes disconnected unintentionally;
- traversal from any node eventually returns to the starting node.

These invariants guarantee that the circular structure remains valid.

---

# Java Perspective

Within this repository, circular linked lists are implemented manually to
illustrate cyclic reference management.

The learner should understand:

- circular node relationships;
- traversal termination conditions;
- insertion and deletion algorithms;
- structural correctness.

The objective is to understand the underlying algorithm rather than relying on
predefined library implementations.

---

# Relationship with Automated Testing

Representative test scenarios include:

- empty list;
- single-node circular list;
- multiple-node circular list;
- insertion into an empty list;
- insertion into a populated list;
- deletion of the first node;
- deletion of the last node;
- deletion of the only node;
- complete circular traversal;
- verification of cyclic references.

Testing should verify both functional behaviour and preservation of the
circular structure.

---

# Engineering Applications

Circular linked lists are particularly useful when applications repeatedly
process elements in cycles.

Typical examples include:

- round-robin scheduling;
- operating system process scheduling;
- multimedia playlists;
- circular buffers;
- token-ring communication models;
- turn-based simulations;
- queue implementations with cyclic processing.

The cyclic organisation eliminates the need to restart traversal after reaching
the last element.

---

# Engineering Considerations

Circular linked lists provide an elegant representation for cyclic processes.

Their principal advantage is continuous traversal without structural restart.

However, algorithms must carefully define their stopping conditions to avoid
infinite loops.

Choosing this structure depends on whether the application naturally exhibits
repetitive or cyclic behaviour.

---

# Key Takeaways

After studying this document, the learner should understand that:

- a circular linked list connects the final node back to the first node;
- traversal requires explicit termination conditions;
- insertion and deletion preserve the cyclic structure;
- computational complexity is comparable to other linked lists;
- circular linked lists are especially appropriate for cyclic algorithms and
  continuous processing systems.

These concepts complete the study of the principal linked-list
representations and prepare the learner for analysing list operations and
their implementation in Java.