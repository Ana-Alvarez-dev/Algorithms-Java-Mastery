# Binary Search Trees

## Algorithms Java Mastery

A **Binary Search Tree (BST)** is one of the most important data structures in computer science. It extends the concept of a Binary Tree by introducing an **ordering property** that enables efficient searching, insertion, and deletion operations.

Unlike a general Binary Tree, where values may be arranged arbitrarily, a Binary Search Tree organizes its elements according to a strict ordering rule. This organization allows many operations to be performed without examining every node, making BSTs the foundation of numerous search structures, database indexes, symbol tables, and associative containers.

This document introduces the Binary Search Tree, explains its ordering property, describes its fundamental operations, and prepares the learner for balanced search trees.

---

# 1. Purpose

The purpose of this document is to:

- introduce Binary Search Trees;
- explain the BST ordering property;
- understand search, insertion, and deletion operations;
- analyse the influence of tree height on performance;
- prepare the learner for self-balancing trees.

---

# 2. What Is a Binary Search Tree?

A **Binary Search Tree (BST)** is a Binary Tree that satisfies a specific ordering rule.

For every node:

- every key in the left subtree is **smaller** than the node;
- every key in the right subtree is **greater** than the node.

Conceptually:

```text
          50
        /    \
      30      70
     / \     / \
   20  40  60  80
```

This property is known as the **Binary Search Tree Property**.

---

# 3. The BST Property

The ordering rule applies recursively to every subtree.

For example:

```text
          50
        /    \
      30      70
     / \     / \
   20  40  60  80
```

Notice that:

- every value in the left subtree of **50** is less than **50**;
- every value in the right subtree of **50** is greater than **50**;
- the same rule also applies to nodes **30** and **70**.

Because every subtree is itself a Binary Search Tree, recursive algorithms naturally arise.

---

# 4. Searching

Searching begins at the root.

At each node:

- compare the target value with the current node;
- move left if the value is smaller;
- move right if the value is larger;
- stop when the value is found or a null reference is reached.

Example:

Search for **60**.

```text
          50
            \
             70
            /
          60
```

Path:

```text
50

↓

70

↓

60
```

Instead of examining every node, only one path is explored.

---

# 5. Insertion

Insertion follows the same comparisons used during searching.

Algorithm:

```text
Start at Root

↓

Compare Values

↓

Go Left or Right

↓

Insert at First Empty Position
```

Example:

Insert **65**.

```text
          50
        /    \
      30      70
             /
           60
             \
              65
```

The Binary Search Tree property remains preserved after insertion.

---

# 6. Deletion

Deletion is the most complex BST operation because several situations must be considered.

### Case 1 — Leaf Node

The node has no children.

```text
Delete

↓

Remove Node
```

---

### Case 2 — One Child

The node is replaced by its child.

```text
Parent

↓

Child
```

---

### Case 3 — Two Children

The node is replaced by either:

- its **in-order successor**, or
- its **in-order predecessor**.

This preserves the Binary Search Tree property.

---

# 7. Tree Height and Performance

The efficiency of BST operations depends directly on the height of the tree.

Balanced tree:

```text
          50
        /    \
      30      70
```

Search requires only a few comparisons.

Unbalanced tree:

```text
10
 \
 20
   \
   30
     \
     40
```

Searching now resembles traversing a linked list.

The closer a BST remains to being balanced, the better its performance.

---

# 8. Complexity Analysis

### Balanced BST

| Operation | Complexity |
|-----------|-----------:|
| Search | O(log n) |
| Insert | O(log n) |
| Delete | O(log n) |

---

### Unbalanced BST

| Operation | Complexity |
|-----------|-----------:|
| Search | O(n) |
| Insert | O(n) |
| Delete | O(n) |

This limitation motivates the development of self-balancing trees.

---

# 9. Advantages

Binary Search Trees provide several important benefits.

- efficient searching;
- ordered data storage;
- dynamic insertion and deletion;
- recursive implementation;
- support for sorted traversals.

These characteristics make BSTs suitable for many search-related applications.

---

# 10. Limitations

Binary Search Trees are sensitive to insertion order.

If values are inserted in sorted order:

```text
10

↓

20

↓

30

↓

40
```

the tree becomes highly unbalanced.

Consequences include:

- increased height;
- slower operations;
- performance approaching linear time.

Balanced trees solve this problem automatically.

---

# 11. Common Applications

Binary Search Trees are used in many computing systems.

Typical applications include:

- symbol tables;
- dictionaries;
- ordered collections;
- database indexing concepts;
- compiler data structures;
- search-based applications.

Many balanced search trees are improvements upon the Binary Search Tree concept.

---

# 12. Relationship with the Next Document

Although Binary Search Trees provide efficient searching, their performance depends on maintaining a relatively small height.

The next document introduces **Tree Traversals**, which explain how every node of a tree can be visited systematically using different traversal strategies.

Later, the module examines **Balanced Trees**, which automatically maintain logarithmic height to preserve efficient performance.

---

# 13. Engineering Perspective

Binary Search Trees illustrate one of the central ideas of algorithm design: **organizing data to reduce the amount of work required for future operations**.

Rather than searching every element sequentially, the BST ordering property eliminates approximately half of the remaining search space after each comparison when the tree is balanced. This principle is closely related to Binary Search on sorted arrays and demonstrates how structural organization can dramatically improve algorithmic efficiency.

Although production systems often rely on self-balancing variants such as AVL Trees or Red-Black Trees, understanding the Binary Search Tree is essential because these advanced structures extend the same fundamental concepts introduced here.

---

# 14. Key Takeaways

After completing this document, the learner should understand that:

- a Binary Search Tree is a Binary Tree with an ordering property;
- every left subtree contains smaller values, while every right subtree contains larger values;
- search, insertion, and deletion follow the same comparison-based navigation process;
- the height of the tree directly determines operational efficiency;
- balanced BSTs achieve **O(log n)** operations, while unbalanced trees may degrade to **O(n)**;
- Binary Search Trees provide the conceptual foundation for self-balancing search trees and many advanced data structures.