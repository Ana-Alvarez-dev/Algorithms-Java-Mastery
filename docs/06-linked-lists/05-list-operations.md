# List Operations

## Algorithms Java Mastery

A linked list is valuable not only because of its structure but also because of
the operations that can be performed efficiently on that structure.

Regardless of whether the implementation is **singly linked**, **doubly
linked**, or **circular**, every linked list supports a common set of
fundamental operations used to create, inspect, modify and traverse the
collection.

The efficiency of these operations depends on the organisation of the list, the
available node references, and the information maintained by the data
structure.

Understanding these operations is essential before implementing linked lists in
Java or analysing more advanced dynamic data structures.

---

# Academic Foundation

The concepts presented throughout this document are inspired by the study of
linked lists and dynamic sets developed in *Introduction to Algorithms* by
Cormen, Leiserson, Rivest and Stein. The explanations in this repository are
intentionally original and focus on the engineering principles that govern the
behaviour of linked-list operations.

Additional academic references are maintained in:

```text
docs/00-project/10-references.md
```

---

# Purpose

The purpose of this document is to study the fundamental operations supported
by linked lists independently of any particular implementation.

The learner will understand:

- list creation;
- traversal;
- searching;
- insertion;
- deletion;
- updating;
- reversal;
- concatenation;
- splitting;
- computational complexity.

Rather than studying language-specific implementations, this document focuses
on the algorithmic behaviour of these operations.

---

# Learning Objectives

After completing this document, the learner should be able to:

## Conceptual understanding

- identify the fundamental operations of linked lists;
- explain how each operation modifies the structure;
- understand the relationship between traversal and structural modification.

## Structural reasoning

- preserve node connectivity;
- maintain structural invariants;
- analyse algorithm behaviour.

## Engineering perspective

- compare operation costs;
- identify appropriate implementation strategies;
- recognise trade-offs between different list variants.

---

# Overview of Operations

The principal operations supported by linked lists include:

| Operation | Purpose |
|-----------|----------|
| Create | Construct an empty list |
| Traverse | Visit every node |
| Search | Locate an element |
| Insert | Add a new node |
| Delete | Remove an existing node |
| Update | Modify stored data |
| Reverse | Change traversal direction logically |
| Concatenate | Join two lists |
| Split | Divide one list into multiple lists |

These operations appear repeatedly throughout algorithm design and software
engineering.

---

# Creating a List

Every linked list begins in an empty state.

Conceptually:

```text
Head
 │
 ▼
null
```

No nodes exist.

Insertion operations progressively construct the structure by allocating new
nodes and establishing the appropriate references.

---

# Traversal

Traversal consists of visiting every reachable node exactly once.

Conceptually:

```text
Head

A → B → C → D → null
```

Traversal serves as the foundation for numerous algorithms, including:

- searching;
- counting;
- printing;
- aggregation;
- validation;
- copying.

Most linked-list algorithms begin with a traversal.

---

# Searching

Searching attempts to locate a node containing a desired value.

The algorithm examines each node sequentially until:

- the value is found;
- the end of the list is reached.

Because linked lists provide sequential rather than indexed access, searching
generally requires examining multiple nodes.

---

# Insertion

Insertion adds a new node while preserving the logical structure of the list.

Common insertion positions include:

- beginning;
- end;
- before a known node;
- after a known node;
- arbitrary position.

Conceptually:

Before

```text
A → C
```

After

```text
A → B → C
```

Correct insertion maintains connectivity throughout the structure.

---

# Deletion

Deletion removes a node from the collection.

Rather than shifting every remaining element, linked lists reconnect the
surrounding nodes.

Before

```text
A → B → C
```

After

```text
A → C
```

Deletion should never disconnect unrelated portions of the list.

---

# Updating

Updating changes the information stored within an existing node.

The structural organisation remains unchanged.

Conceptually:

Before

```text
A(15)
```

After

```text
A(30)
```

Only the stored value changes.

References remain identical.

---

# Reversing a List

Reversal changes the logical order of the nodes.

Before

```text
A → B → C → D
```

After

```text
D → C → B → A
```

For singly linked lists, reversal requires modifying every reference.

For doubly linked lists, both predecessor and successor relationships must be
updated consistently.

---

# Concatenation

Concatenation joins two independent linked lists into a single structure.

Before

```text
List 1

A → B

List 2

C → D
```

After

```text
A → B → C → D
```

Correct concatenation preserves the ordering of both lists.

---

# Splitting

Splitting divides one linked list into two independent structures.

Before

```text
A → B → C → D
```

After

```text
List 1

A → B

List 2

C → D
```

Splitting is commonly used in divide-and-conquer algorithms such as Merge Sort.

---

# Complexity Analysis

The computational complexity of linked-list operations depends on whether the
required position is already known.

| Operation | Typical Complexity |
|-----------|-------------------:|
| Create | O(1) |
| Traverse | O(n) |
| Search | O(n) |
| Insert at head | O(1) |
| Insert after known node | O(1) |
| Delete after known predecessor | O(1) |
| Update known node | O(1) |
| Reverse | O(n) |
| Concatenate* | O(n) |
| Split | O(n) |

\*Assuming only a head reference is maintained.

Operations that require locating a node inherit the cost of the traversal used
to find that position.

---

# Relationship Between Operations

Many linked-list operations are composed of simpler operations.

For example:

```text
Search
    ↓
Locate Node
    ↓
Insert
```

or

```text
Search
    ↓
Locate Node
    ↓
Delete
```

Understanding these relationships helps decompose complex algorithms into
smaller, reusable components.

---

# Structural Invariants

Regardless of the operation performed, a valid linked list should preserve the
following properties:

- every reachable node remains connected;
- no references are unintentionally lost;
- traversal reaches every node exactly once (except in circular lists);
- head and tail references remain consistent;
- structural modifications preserve logical ordering.

Maintaining these invariants is fundamental to algorithm correctness.

---

# Java Perspective

Within this repository, every linked-list operation is implemented manually
before introducing the Java Collections Framework.

This approach allows the learner to understand:

- node manipulation;
- reference updates;
- structural correctness;
- algorithm design;
- computational complexity.

The implementation focuses on educational value rather than production-ready
features.

---

# Relationship with Automated Testing

Each operation should be verified through automated tests.

Representative scenarios include:

- creation of an empty list;
- traversal of populated lists;
- successful and unsuccessful searches;
- insertion in every valid position;
- deletion of first, intermediate and last nodes;
- update of stored values;
- reversal of empty and populated lists;
- concatenation of independent lists;
- splitting into valid sublists.

Testing verifies both functional correctness and preservation of structural
integrity.

---

# Engineering Considerations

Although linked-list operations appear simple, their implementation requires
careful management of references.

Many implementation errors arise not from incorrect algorithms but from losing
references during structural modification.

For this reason, software engineers should prioritise:

- correctness;
- maintainability;
- readability;
- explicit handling of edge cases;
- automated verification.

Reliable implementations result from preserving structural invariants
throughout every operation.

---

# Key Takeaways

After studying this document, the learner should understand that:

- linked lists support a common set of fundamental operations;
- structural modifications are performed by updating references;
- traversal is the basis of most algorithms;
- many operations are combinations of simpler procedures;
- preserving structural integrity is more important than the individual
  algorithm itself;
- automated testing is essential for verifying correct behaviour.

Mastering these operations provides the practical foundation required for
implementing linked lists correctly and for understanding more advanced dynamic
data structures throughout the remainder of this repository.