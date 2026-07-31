# Tree Terminology

## Algorithms Java Mastery

Before studying Binary Trees, Binary Search Trees, balanced trees, and heaps, it is essential to establish a common vocabulary. Tree algorithms are described using a precise set of terms that define the relationships between nodes and the structural properties of a tree.

Understanding this terminology is fundamental because the same concepts appear throughout computer science literature, technical interviews, academic textbooks, and software engineering documentation. A clear understanding of these definitions allows developers to reason about tree algorithms more accurately and communicate effectively when discussing hierarchical data structures.

This document introduces the principal terms used throughout the remainder of this module.

---

# 1. Purpose

The purpose of this document is to:

- define the fundamental terminology of tree data structures;
- understand the relationships between nodes;
- explain the structural properties of trees;
- establish a common vocabulary for the remaining documents in this module.

---

# 2. Node

A **node** is the fundamental unit of a tree.

Each node stores:

- a value or data;
- references to one or more child nodes.

Conceptually:

```text
Node

┌─────────┐
│  Value  │
└─────────┘
```

Every tree is composed of interconnected nodes.

---

# 3. Root

The **root** is the first node of a tree.

It has no parent and serves as the starting point for every traversal.

Example:

```text
        A
       / \
      B   C
```

Node **A** is the root.

Every tree has exactly one root.

---

# 4. Parent and Child

A node directly connected to another node below it is called its **parent**.

The connected node is called its **child**.

Example:

```text
        A
       / \
      B   C
```

Relationships:

- A is the parent of B.
- A is the parent of C.
- B and C are children of A.

---

# 5. Siblings

Nodes that share the same parent are called **siblings**.

Example:

```text
        A
      / | \
     B  C  D
```

Here:

- B, C, and D are siblings.

Sibling nodes are located at the same level of the tree.

---

# 6. Leaf

A **leaf** (or **leaf node**) is a node with no children.

Example:

```text
        A
       / \
      B   C
         / \
        D   E
```

Leaf nodes:

- B
- D
- E

Leaves represent the endpoints of a tree.

---

# 7. Internal Node

An **internal node** is any node that has at least one child.

Example:

```text
        A
       / \
      B   C
         / \
        D   E
```

Internal nodes:

- A
- C

Leaf nodes are not considered internal nodes.

---

# 8. Path

A **path** is a sequence of connected nodes.

Example:

```text
A → C → E
```

The number of edges along the path is called the **path length**.

Trees contain exactly one unique path between the root and every node.

---

# 9. Depth

The **depth** of a node is the number of edges between the root and that node.

Example:

```text
        A
       / \
      B   C
         /
        D
```

| Node | Depth |
|------|------:|
| A | 0 |
| B | 1 |
| C | 1 |
| D | 2 |

The root always has depth zero.

---

# 10. Height

The **height** of a node is the number of edges on the longest path from that node to any leaf.

Example:

```text
        A
       / \
      B   C
         /
        D
```

| Node | Height |
|------|-------:|
| D | 0 |
| C | 1 |
| A | 2 |

The height of the root is commonly referred to as the **height of the tree**.

---

# 11. Level

The **level** of a node indicates its position within the tree.

A common convention is:

```text
Level = Depth + 1
```

Example:

```text
        A
       / \
      B   C
         /
        D
```

| Node | Level |
|------|------:|
| A | 1 |
| B | 2 |
| C | 2 |
| D | 3 |

Some textbooks use **depth** instead of **level**, so understanding both conventions is important.

---

# 12. Subtree

A **subtree** is a tree formed by a node and all of its descendants.

Example:

```text
        A
       / \
      B   C
         / \
        D   E
```

The subtree rooted at **C** is:

```text
      C
     / \
    D   E
```

Every subtree satisfies the definition of a tree.

---

# 13. Ancestors and Descendants

The nodes above a given node are its **ancestors**.

The nodes below it are its **descendants**.

Example:

```text
        A
       /
      B
     /
    C
```

For node **C**:

Ancestors:

- B
- A

Descendants:

- none

---

# 14. Degree

The **degree** of a node is the number of children it has.

Example:

```text
        A
      / | \
     B  C  D
```

| Node | Degree |
|------|-------:|
| A | 3 |
| B | 0 |
| C | 0 |
| D | 0 |

The degree of a tree is the maximum degree of any node in that tree.

---

# 15. Engineering Perspective

A precise understanding of tree terminology is essential because every tree algorithm is described using these concepts.

Operations such as insertion, deletion, balancing, traversal, and searching all depend on understanding relationships like parent, child, ancestor, descendant, depth, and height. These terms appear consistently in academic literature, technical documentation, and software engineering interviews.

Developers who master this vocabulary can read algorithm descriptions more easily, reason about recursive solutions, and communicate effectively with other engineers.

---

# 16. Key Takeaways

After completing this document, the learner should understand that:

- a tree is composed of interconnected nodes;
- the root is the unique starting point of the tree;
- nodes are related through parent-child relationships;
- leaves have no children, while internal nodes have at least one;
- depth, height, and level describe the position of nodes within a tree;
- subtrees are complete trees rooted at any node;
- ancestors and descendants define hierarchical relationships;
- mastering tree terminology is essential for understanding tree algorithms and advanced data structures.