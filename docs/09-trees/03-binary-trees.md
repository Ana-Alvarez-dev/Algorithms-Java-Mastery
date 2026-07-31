# Binary Trees

## Algorithms Java Mastery

A **Binary Tree** is one of the most fundamental tree structures in computer science. It extends the general concept of a tree by restricting each node to at most two children. This seemingly simple constraint leads to efficient algorithms for searching, traversing, representing expressions, managing hierarchical data, and implementing numerous advanced data structures.

Binary Trees form the foundation for Binary Search Trees (BSTs), AVL Trees, Red-Black Trees, Heaps, Expression Trees, Huffman Trees, and many other structures used throughout software engineering.

This document introduces Binary Trees, explains their structural properties, presents their common classifications, and establishes the theoretical foundation required for studying Binary Search Trees and balanced trees.

---

# 1. Purpose

The purpose of this document is to:

- introduce Binary Trees;
- explain their structural properties;
- distinguish different types of Binary Trees;
- understand why Binary Trees are widely used in algorithm design;
- prepare the learner for Binary Search Trees.

---

# 2. What Is a Binary Tree?

A **Binary Tree** is a tree in which every node has **at most two children**.

These children are traditionally called:

- left child;
- right child.

Conceptually:

```text
        A
       / \
      B   C
```

Unlike general trees, where a node may have any number of children, Binary Trees impose a maximum of two.

---

# 3. Binary Tree Properties

Every Binary Tree satisfies several important properties.

- one unique root;
- each node has zero, one, or two children;
- every child is designated as either left or right;
- no cycles exist;
- every node is reachable from the root through exactly one path.

These properties simplify many recursive algorithms.

---

# 4. Why Binary Trees?

Restricting each node to two children makes many algorithms easier to design and analyse.

Binary Trees support efficient:

- recursive processing;
- hierarchical organization;
- expression evaluation;
- searching;
- sorting;
- priority management.

Because of these advantages, Binary Trees are among the most widely studied data structures.

---

# 5. Types of Binary Trees

Binary Trees can be classified according to their structure.

### Full Binary Tree

Every internal node has exactly two children.

```text
        A
       / \
      B   C
     / \ / \
    D  E F  G
```

No node has only one child.

---

### Perfect Binary Tree

A Perfect Binary Tree satisfies two conditions:

- every internal node has exactly two children;
- all leaves are located at the same level.

```text
        A
       / \
      B   C
     / \ / \
    D  E F  G
```

Every level is completely filled.

---

### Complete Binary Tree

A Complete Binary Tree fills levels from left to right.

The last level may be incomplete, but all nodes appear as far left as possible.

```text
        A
       / \
      B   C
     / \ /
    D  E F
```

Complete Binary Trees are commonly used to implement heaps.

---

### Degenerate Binary Tree

A Degenerate Tree resembles a linked list.

```text
A
 \
  B
   \
    C
     \
      D
```

Searching in this structure becomes inefficient.

---

# 6. Binary Tree Height

The height of a Binary Tree is determined by the longest path from the root to any leaf.

Example:

```text
        A
       / \
      B   C
         /
        D
```

Longest path:

```text
A → C → D
```

Height:

```text
2
```

Tree height directly influences the efficiency of many algorithms.

---

# 7. Binary Tree Representation

Binary Trees are commonly represented using linked nodes.

Conceptually:

```text
Node

Value

Left Child

Right Child
```

Each node stores:

- data;
- reference to the left child;
- reference to the right child.

This representation allows trees to grow dynamically.

---

# 8. Common Applications

Binary Trees appear in numerous computing systems.

Typical applications include:

- Binary Search Trees;
- expression trees;
- compiler syntax trees;
- Huffman coding;
- decision trees;
- game trees;
- heaps;
- XML and HTML parsers.

Many advanced data structures are specialized forms of Binary Trees.

---

# 9. Advantages

Binary Trees provide several important advantages.

- hierarchical organization;
- natural recursive structure;
- efficient traversal algorithms;
- foundation for balanced search structures;
- flexible insertion and deletion.

These characteristics make Binary Trees one of the most versatile data structures in computer science.

---

# 10. Limitations

Binary Trees also have limitations.

Without balancing:

- height may become large;
- searching may degrade to linear time;
- insertion order strongly affects performance.

These limitations motivate the study of Binary Search Trees and self-balancing trees.

---

# 11. Relationship with the Next Document

Binary Trees define only the structural organization of nodes.

They do **not** impose any ordering on stored values.

The next document introduces **Binary Search Trees (BSTs)**, which extend Binary Trees by maintaining an ordering property that enables efficient searching, insertion, and deletion.

---

# 12. Engineering Perspective

Binary Trees represent one of the most influential abstractions in software engineering. Their recursive nature enables elegant algorithm design, while their hierarchical organization naturally models many real-world systems.

Although developers rarely implement simple Binary Trees directly in production systems, understanding them is essential because they serve as the foundation for numerous advanced data structures, including search trees, balanced trees, heaps, syntax trees, and database indexes.

Mastering Binary Trees therefore provides the conceptual framework required to understand much of modern algorithm design.

---

# 13. Key Takeaways

After completing this document, the learner should understand that:

- a Binary Tree is a tree in which each node has at most two children;
- children are distinguished as left and right;
- Binary Trees may be full, perfect, complete, or degenerate;
- tree height has a direct impact on algorithm performance;
- Binary Trees are the foundation of many advanced data structures;
- understanding Binary Trees is essential before studying Binary Search Trees and balanced trees.