# Tree Operations

## Algorithms Java Mastery

Regardless of their specific type, all tree-based data structures support a common set of fundamental operations. These operations allow developers to search for elements, insert new values, remove existing nodes, and obtain structural information about the tree.

Understanding these operations is essential because they form the basis of more advanced structures such as Binary Search Trees (BSTs), AVL Trees, Red-Black Trees, B-Trees, and many database indexing structures. While the implementation details may vary, the underlying concepts remain largely the same.

This document introduces the principal operations performed on tree data structures and explains their algorithmic behavior.

---

# 1. Purpose

The purpose of this document is to:

- introduce the fundamental operations performed on trees;
- explain how these operations modify or inspect a tree;
- understand their relationship with Binary Search Trees;
- prepare the learner for balanced tree algorithms.

---

# 2. Search

Searching determines whether a value exists in a tree.

In a Binary Search Tree, the search follows the ordering property.

```text
Start at Root

↓

Compare Values

↓

Smaller?

↓

Go Left

↓

Larger?

↓

Go Right

↓

Value Found
```

Each comparison eliminates part of the search space.

---

# 3. Insertion

Insertion adds a new node while preserving the tree's structural properties.

For a Binary Search Tree:

```text
Start at Root

↓

Compare Values

↓

Navigate Left or Right

↓

Insert at Empty Position
```

Example:

Before:

```text
      50
     /  \
   30    70
```

Insert **60**:

```text
      50
     /  \
   30    70
         /
       60
```

The ordering property remains unchanged.

---

# 4. Deletion

Deletion removes a node while preserving the structure of the tree.

Three situations must be considered.

### Leaf Node

The node has no children.

```text
Delete

↓

Remove Node
```

---

### One Child

The node is replaced by its child.

```text
Parent

↓

Child
```

---

### Two Children

The node is replaced by either:

- its in-order successor;
- its in-order predecessor.

This preserves the Binary Search Tree property.

Deletion is generally the most complex basic tree operation.

---

# 5. Finding the Minimum Value

The smallest value in a Binary Search Tree is always located at the **leftmost node**.

Example:

```text
          50
         /
       30
      /
    20
```

Minimum value:

```text
20
```

Algorithm:

```text
Go Left

↓

Go Left

↓

Repeat Until Null
```

---

# 6. Finding the Maximum Value

The largest value is always located at the **rightmost node**.

Example:

```text
      50
        \
         70
           \
            90
```

Maximum value:

```text
90
```

Algorithm:

```text
Go Right

↓

Go Right

↓

Repeat Until Null
```

---

# 7. Finding the Successor

The **in-order successor** is the next larger value in sorted order.

Example:

```text
        50
       /  \
     30    70
          /
        60
```

Successor of **50**:

```text
60
```

Successors are frequently used during node deletion.

---

# 8. Finding the Predecessor

The **in-order predecessor** is the next smaller value.

Example:

```text
        50
       /  \
     30    70
       \
        40
```

Predecessor of **50**:

```text
40
```

Like successors, predecessors help preserve the BST ordering during deletion.

---

# 9. Calculating Tree Height

Tree height measures the longest path from the root to any leaf.

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

Height directly affects the efficiency of search, insertion, and deletion.

---

# 10. Checking Whether the Tree Is Empty

Before performing many operations, algorithms first verify whether the tree contains any nodes.

Conceptually:

```text
Root == null

↓

Tree Is Empty
```

This simple validation avoids unnecessary processing and prevents runtime errors.

---

# 11. Complexity Summary

For a **balanced Binary Search Tree**:

| Operation | Complexity |
|-----------|-----------:|
| Search | O(log n) |
| Insert | O(log n) |
| Delete | O(log n) |
| Find Minimum | O(log n) |
| Find Maximum | O(log n) |
| Find Successor | O(log n) |
| Find Predecessor | O(log n) |

For an **unbalanced tree**, these operations may degrade to:

```text
O(n)
```

because the tree may resemble a linked list.

---

# 12. Engineering Perspective

These operations form the core functionality of nearly every tree-based data structure.

Whether working with Binary Search Trees, AVL Trees, Red-Black Trees, B-Trees, or database indexes, the same fundamental operations are performed repeatedly. Advanced tree structures differ primarily in **how they maintain balance and optimize these operations**, rather than in the operations themselves.

A solid understanding of these algorithms makes it much easier to study self-balancing trees and more sophisticated indexing structures.

---

# 13. Relationship with the Next Document

The operations presented in this document focus on modifying or querying a tree.

The next document introduces **Tree Traversals**, which answer a different question:

> **How can every node of a tree be visited systematically?**

Traversal algorithms are fundamental for searching, printing, evaluating expressions, serialization, and many recursive tree algorithms.

---

# 14. Key Takeaways

After completing this document, the learner should understand that:

- search, insertion, and deletion are the fundamental operations of tree data structures;
- finding minimum and maximum values relies on the BST ordering property;
- successors and predecessors are essential for maintaining tree structure during deletion;
- tree height directly determines the efficiency of most operations;
- balanced trees achieve **O(log n)** performance, while unbalanced trees may degrade to **O(n)**;
- these operations provide the foundation for understanding AVL Trees, Red-Black Trees, B-Trees, and many other advanced tree structures.