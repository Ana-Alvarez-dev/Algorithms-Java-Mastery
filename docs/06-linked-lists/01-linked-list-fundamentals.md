# Singly Linked Lists

## Algorithms Java Mastery

A **singly linked list** is a dynamic linear data structure composed of individual
nodes connected through references.

Unlike arrays, whose elements occupy contiguous memory locations, a singly
linked list stores each node independently. Every node maintains a reference to
its successor, forming a unidirectional chain that represents the collection.

This organisation provides flexibility for structural modifications while
introducing different computational characteristics from those of contiguous
storage.

Singly linked lists illustrate one of the fundamental principles of Computer
Science:

> **The organisation of data determines the efficiency of the algorithms that
operate on it.**

Understanding this structure is essential before studying more advanced dynamic
data structures such as stacks, queues, hash tables, trees and graphs.

---

# Academic Foundation

The concepts presented in this document are primarily based on the study of
elementary data structures presented in *Introduction to Algorithms* by
Cormen, Leiserson, Rivest and Stein, where linked lists are introduced as a
dynamic representation for ordered collections within the broader study of data
structures. :contentReference[oaicite:0]{index=0}

The explanations contained in this repository are intentionally original and are
supported by additional references documented in:

```text
docs/00-project/10-references.md
```

---

# Purpose

The purpose of this document is to understand the structure, behaviour and
engineering characteristics of singly linked lists before studying their
algorithms and Java implementation.

The learner will study:

- node organisation;
- structural relationships;
- logical ordering;
- traversal;
- insertion;
- deletion;
- computational complexity;
- engineering trade-offs.

---

# Conceptual Model

A singly linked list consists of a sequence of nodes.

Each node contains two conceptual components:

- stored data;
- a reference to the next node.

Conceptually:

```text
Head
 ↓
┌─────┐    ┌─────┐    ┌─────┐
│Data │──► │Data │──► │Data │──► null
└─────┘    └─────┘    └─────┘
```

The final node references **null**, indicating the end of the structure.

The order of the elements is determined by these references rather than by
physical memory addresses.

---

# Fundamental Characteristics

A singly linked list possesses several defining properties.

- Dynamic size.
- Sequential access.
- Independent node allocation.
- Explicit structural relationships.
- Efficient structural modification.
- No direct indexed access.

These characteristics distinguish linked lists from contiguous structures such
as arrays.

---

# Memory Organisation

Unlike arrays, linked lists do not require contiguous memory allocation.

Each node may occupy a different memory location.

The logical order of the list is maintained exclusively through references.

Conceptually:

```text
Physical Memory

Node A      Node C      Node B

Logical Structure

A ─────► B ─────► C
```

The physical position of each node is irrelevant to the logical organisation of
the data structure.

---

# Node Structure

From a conceptual perspective, every node contains:

```text
Node
├── Data
└── Next Reference
```

The node is the fundamental building block of every linked list.

Complex algorithms manipulate references between nodes rather than moving
entire collections of elements.

---

# Traversal

Because nodes are connected sequentially, every traversal begins at the head of
the list.

The algorithm repeatedly follows the next reference until the end of the list is
reached.

```text
Head
 ↓
Node → Node → Node → null
```

Unlike arrays, arbitrary positions cannot be accessed directly.

Every intermediate node must be visited.

---

# Insertion

Insertion consists of creating a new node and updating the surrounding
references.

Depending on the position, insertion may occur:

- at the beginning;
- in the middle;
- at the end.

The correctness of insertion depends on preserving the structural integrity of
the list.

Every node must remain reachable after the operation.

---

# Deletion

Deletion removes a node from the logical structure.

Rather than relocating every remaining element, deletion updates the references
surrounding the removed node.

Conceptually:

Before

```text
A → B → C
```

After removing **B**

```text
A ─────────► C
```

The operation modifies the relationships between nodes while preserving the
remaining structure.

---

# Complexity Analysis

The computational complexity of the principal operations depends on the amount
of traversal required.

| Operation | Complexity |
|-----------|-----------:|
| Access by index | O(n) |
| Search | O(n) |
| Insert at head | O(1) |
| Insert at end* | O(n) |
| Delete at head | O(1) |
| Delete after known node | O(1) |

\*Assuming no tail reference is maintained.

These complexities illustrate the trade-offs between linked lists and arrays.

---

# Comparison with Arrays

| Characteristic | Array | Singly Linked List |
|---------------|-------|--------------------|
| Memory | Contiguous | Dynamic |
| Indexed access | O(1) | O(n) |
| Insert at beginning | O(n) | O(1) |
| Delete at beginning | O(n) | O(1) |
| Dynamic growth | Limited | Natural |

Neither structure is universally superior.

The appropriate choice depends on the computational requirements of the
problem.

---

# Java Perspective

Within this repository, singly linked lists are implemented manually before
studying the Java Collections Framework.

The objective is to understand:

- node creation;
- reference manipulation;
- structural correctness;
- algorithm behaviour.

Only after understanding the underlying data structure should the learner study
`java.util.LinkedList`.

---

# Automated Testing

Every implementation should be verified through automated tests.

Representative scenarios include:

- empty list;
- single node;
- multiple nodes;
- insertion at the head;
- insertion at the end;
- deletion;
- traversal;
- search;
- invalid operations.

Testing demonstrates that structural modifications preserve the expected
behaviour.

---

# Engineering Considerations

Singly linked lists illustrate an important engineering principle.

Data structures should be selected according to the operations that dominate the
application rather than personal preference.

When applications require frequent structural modifications, linked lists may
provide advantages over arrays.

Conversely, applications requiring frequent indexed access generally favour
contiguous storage.

Selecting the appropriate representation is therefore an engineering decision
based on computational requirements.

---

# Key Takeaways

After studying this document, the learner should understand that:

- singly linked lists are dynamic linear data structures;
- node relationships define logical order;
- memory organisation influences algorithm behaviour;
- insertion and deletion are reference-based operations;
- traversal is inherently sequential;
- complexity depends on structural organisation rather than programming
  language.

These concepts provide the theoretical foundation required for implementing
linked lists correctly and for understanding more advanced dynamic data
structures throughout the remainder of the repository.