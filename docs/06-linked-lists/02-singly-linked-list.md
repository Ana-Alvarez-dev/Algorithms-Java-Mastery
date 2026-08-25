# Singly Linked Lists

## Algorithms Java Mastery

A **singly linked list** is a dynamic linear data structure whose elements are
connected through references rather than contiguous memory locations.

Each element, commonly called a **node**, stores both application data and a
reference to its successor. Together, these nodes form a sequential structure
whose logical order is determined by the relationships between nodes instead of
their physical location in memory.

This organisation differs fundamentally from arrays.

Arrays determine their order through indices, whereas linked lists determine
their order through explicit references between nodes. This distinction changes
how algorithms traverse, modify, and analyse the structure. :contentReference[oaicite:1]{index=1}

Understanding singly linked lists establishes the conceptual basis required for
studying more advanced dynamic structures, including stacks, queues, hash
tables with chaining, trees, and graphs.

---

# Academic Foundation

The concepts developed throughout this document are primarily based on the study
of linked lists presented in **Chapter 10 — Elementary Data Structures** of
*Introduction to Algorithms* by Cormen, Leiserson, Rivest and Stein, where
linked lists are introduced as a flexible representation for dynamic sets and
their principal variants are discussed. :contentReference[oaicite:2]{index=2}

The explanations provided in this repository are intentionally original and are
supported by the academic references maintained in:

```text
docs/00-project/10-references.md
```

---

# Purpose

The purpose of this document is to study the conceptual structure of singly
linked lists before analysing their algorithms and Java implementation.

The learner will progressively understand:

- how nodes are organised;
- how references establish logical order;
- why linked lists are dynamic structures;
- how structural modifications are performed;
- which computational advantages and disadvantages arise from this
  organisation.

The emphasis remains on understanding the data structure before implementing
its operations.

---

# Learning Objectives

After completing this document, the learner should be able to:

## Conceptual understanding

- explain what a singly linked list is;
- distinguish linked lists from arrays;
- identify the components of a node;
- explain how logical order is established;
- recognise situations where linked lists are appropriate.

## Structural reasoning

- identify the head of the list;
- reason about node relationships;
- recognise the end of a list;
- distinguish logical order from physical memory order.

## Engineering perspective

- relate memory organisation to algorithm behaviour;
- explain why insertion and deletion differ from arrays;
- justify when a linked list should be selected.

---

# Conceptual Model

A singly linked list is composed of a sequence of interconnected nodes.

Each node contains:

- application data;
- a reference to the next node.

Conceptually:

```text
Head
 │
 ▼
┌──────────┐     ┌──────────┐     ┌──────────┐
│ Data │ ● ├────►│ Data │ ● ├────►│ Data │ ∅ │
└──────────┘     └──────────┘     └──────────┘
```

The final node contains no successor.

Its reference indicates that the structure has reached its logical end.

The collection therefore forms a chain of nodes connected through references.

---

# Dynamic Memory Organisation

Unlike arrays, linked lists do not require all elements to occupy consecutive
memory locations.

Each node may be allocated independently.

Conceptually, the logical organisation of the list is unrelated to the physical
layout of memory.

```text
Physical Memory

Node C

Node A

Node B

Node D


Logical Structure

A ─────► B ─────► C ─────► D
```

This separation between physical storage and logical organisation is one of the
most important characteristics of linked data structures.

Algorithms operate on references between nodes rather than on contiguous memory
positions.

---

# Node Structure

The node is the elementary building block of every singly linked list.

Conceptually, every node contains two responsibilities.

```text
Node
├── Stored Data
└── Reference to the Next Node
```

The data component represents the information managed by the application.

The reference component establishes the relationship between nodes.

Algorithms manipulating linked lists therefore modify references rather than
moving every element of the collection.

---

# Head Reference

Every singly linked list begins with a distinguished reference known as the
**head**.

The head identifies the first node of the structure.

Conceptually:

```text
Head
 │
 ▼
Node → Node → Node → null
```

If the head reference is absent, the list contains no elements.

```text
Head
 │
 ▼
null
```

The head therefore determines whether the structure is empty and provides the
starting point for every traversal algorithm.

---

# Logical Order

The order of the elements is determined exclusively by the references stored in
each node.

Unlike arrays, positions are not inferred from indices.

Instead, the successor of a node is explicitly identified.

For example:

```text
A → B → C → D
```

The sequence is defined by the references:

- A points to B;
- B points to C;
- C points to D;
- D points to null.

Changing one reference may therefore change the logical structure of the entire
collection.

---

# Sequential Access

One consequence of node-based organisation is that linked lists provide
**sequential access**.

To reach an arbitrary element, every preceding node must be visited.

For example, locating the fourth node requires traversing:

```text
Head
 │
 ▼
Node₁
  │
  ▼
Node₂
  │
  ▼
Node₃
  │
  ▼
Node₄
```

The structure provides no direct mechanism equivalent to array indexing.

This characteristic strongly influences the computational complexity of search
and traversal algorithms.

---

# Fundamental Characteristics

A singly linked list possesses several defining properties.

- Dynamic size.
- Sequential traversal.
- Independent node allocation.
- Explicit structural relationships.
- Efficient insertion at the beginning.
- Efficient deletion after a known predecessor.
- No constant-time indexed access.
- Flexible structural growth.

Together, these properties distinguish linked lists from contiguous
representations such as arrays.

---

# Structural Integrity

A linked list is considered structurally correct when every node satisfies the
relationships defined by the data structure.

In general:

- every node is reachable from the head;
- each node references at most one successor;
- the final node terminates the sequence correctly;
- no references are unintentionally lost;
- the logical order remains consistent after structural modifications.

These structural properties become the basis for correctness reasoning in the
algorithms studied later in this module.

---

# Traversal

Traversal is the fundamental operation performed on a singly linked list.

Since nodes are connected sequentially, every traversal begins at the head of
the list and repeatedly follows the `next` reference until the end of the
structure is reached.

Conceptually:

```text
Head
 │
 ▼
Node₁ → Node₂ → Node₃ → Node₄ → null
```

Each iteration processes exactly one node before moving to its successor.

Unlike arrays, a linked list does not provide direct access to an arbitrary
position. Every intermediate node must be visited before reaching a later node.

Traversal therefore becomes the basis for:

- searching;
- counting elements;
- updating values;
- locating insertion points;
- locating deletion points.

Many algorithms built upon linked lists differ only in the work performed during
each traversal step.

---

# Search

Searching consists of locating the first node whose stored value satisfies a
given condition.

The operation begins at the head and examines each node sequentially.

Conceptually:

```text
Head
 │
 ▼
Node → Node → Node → Node → null
```

The search terminates when one of the following conditions occurs:

- the target element is found;
- the end of the list is reached.

Because every preceding node must be examined before reaching a later node, the
worst-case running time grows linearly with the number of elements.

Searching therefore illustrates the principal limitation of singly linked lists
compared with indexed structures.

---

# Insertion

Insertion adds a new node to the logical structure.

The operation itself consists primarily of updating references rather than
relocating existing elements.

Depending on the desired position, insertion may occur:

- at the beginning of the list;
- after an existing node;
- at the end of the list.

Conceptually, inserting a node between two existing nodes changes only the
relationships surrounding the insertion point.

Before:

```text
A ─────► C
```

After:

```text
A ─────► B ─────► C
```

The correctness of insertion depends on preserving the connectivity of the
entire structure.

Every node that was reachable before the insertion must remain reachable after
the operation.

---

# Deletion

Deletion removes a node from the logical structure.

Unlike arrays, deletion does not require shifting every remaining element.

Instead, the surrounding references are modified so that the predecessor points
directly to the successor of the removed node.

Before:

```text
A ─────► B ─────► C
```

After removing **B**

```text
A ───────────────► C
```

The removed node no longer belongs to the logical structure.

Correct deletion preserves the integrity of the remaining list while ensuring
that no references become inconsistent.

---

# Complexity Analysis

The computational cost of singly linked list operations depends primarily on the
amount of traversal required.

| Operation | Worst-Case Complexity |
|-----------|----------------------:|
| Access by position | O(n) |
| Search | O(n) |
| Traversal | O(n) |
| Insert at head | O(1) |
| Insert after a known node | O(1) |
| Insert at tail* | O(n) |
| Delete head | O(1) |
| Delete after a known predecessor | O(1) |

\*Assuming that only a head reference is maintained.

Theoretical complexity should be interpreted together with the structural
characteristics of the data structure.

Operations that require locating a position first inherit the cost of the
preceding traversal.

---

# Comparison with Arrays

Arrays and singly linked lists solve the problem of storing ordered collections
using different organisational models.

| Characteristic | Array | Singly Linked List |
|---------------|-------|--------------------|
| Memory organisation | Contiguous | Dynamic |
| Indexed access | O(1) | O(n) |
| Sequential traversal | O(n) | O(n) |
| Insert at beginning | O(n) | O(1) |
| Delete at beginning | O(n) | O(1) |
| Dynamic growth | Limited | Natural |

These differences demonstrate that no data structure is universally superior.

The choice depends on the dominant operations required by the application.

---

# Structural Invariants

Every singly linked list implementation should preserve a set of structural
properties throughout its execution.

Typical invariants include:

- the head references the first node or `null`;
- every node references at most one successor;
- the final node references `null`;
- every reachable node belongs to exactly one traversal path from the head;
- no node is unintentionally disconnected during structural modification.

Reasoning about these invariants provides confidence that insertion and deletion
operations preserve the integrity of the structure.

---

# Java Perspective

Within this repository, singly linked lists are implemented manually before
studying the Java Collections Framework.

The objective is to understand:

- node construction;
- reference manipulation;
- structural modification;
- algorithm behaviour;
- implementation trade-offs.

Only after these concepts are understood should the learner study
`java.util.LinkedList` as a production-oriented implementation.

Academic implementations prioritise visibility and understanding rather than
feature completeness.

---

# Relationship with Automated Testing

Every linked list implementation should be accompanied by automated tests that
verify both functional behaviour and structural integrity.

Representative scenarios include:

- empty list;
- single-node list;
- multiple-node list;
- insertion at the head;
- insertion after an existing node;
- deletion of the head;
- deletion of an intermediate node;
- unsuccessful search;
- traversal of the complete list.

Testing demonstrates that the implementation behaves according to its
specification for representative cases.

However, automated tests complement rather than replace correctness reasoning.

---

# Engineering Considerations

Choosing a singly linked list is an engineering decision rather than a purely
algorithmic one.

A linked list is generally appropriate when the application performs frequent
structural modifications and direct indexed access is not the dominant
operation.

Conversely, when random access by position is required repeatedly, contiguous
structures such as arrays are often more appropriate.

The decision should therefore be guided by:

- expected access patterns;
- insertion frequency;
- deletion frequency;
- memory constraints;
- implementation complexity;
- maintainability.

Selecting the appropriate data structure requires understanding both its
advantages and its limitations.

---

# Key Takeaways

After studying this document, the learner should understand that:

- a singly linked list organises data through references rather than contiguous
  memory;
- traversal is inherently sequential;
- insertion and deletion modify references instead of relocating elements;
- structural invariants preserve the integrity of the list;
- computational complexity depends on traversal requirements;
- linked lists and arrays represent different engineering solutions to storing
  ordered collections.

These concepts establish the theoretical foundation required for studying more
advanced linked-list variants and their applications throughout the remainder of
the repository.