# Tree Traversals

## Algorithms Java Mastery

One of the most important operations performed on a tree is **visiting every node in a systematic order**. Unlike linear data structures, where elements are naturally arranged in sequence, trees have a hierarchical organization with multiple possible paths. As a result, there is no single "correct" way to visit all nodes.

A **tree traversal** defines the order in which every node of a tree is processed exactly once. Different traversal strategies are designed for different purposes, such as searching, printing data, evaluating expressions, copying trees, or serializing hierarchical structures.

This document introduces the principal traversal algorithms used with binary trees and explains when each traversal is most appropriate.

---

# 1. Purpose

The purpose of this document is to:

- introduce tree traversal algorithms;
- distinguish Depth-First Search (DFS) and Breadth-First Search (BFS);
- explain preorder, inorder, postorder, and level-order traversals;
- understand the practical applications of each traversal strategy.

---

# 2. What Is a Tree Traversal?

A **tree traversal** is an algorithm that visits every node of a tree exactly once according to a predefined order.

Conceptually:

```text
Tree

↓

Visit Every Node

↓

Specific Order
```

The chosen order determines how the tree is processed.

---

# 3. Traversal Categories

Tree traversals are generally divided into two categories.

### Depth-First Search (DFS)

Visits nodes by exploring each branch as deeply as possible before backtracking.

Includes:

- Preorder
- Inorder
- Postorder

---

### Breadth-First Search (BFS)

Visits nodes level by level from top to bottom.

Also known as:

- Level-Order Traversal

---

# 4. Preorder Traversal

The preorder traversal processes nodes in the following order:

```text
Root

↓

Left Subtree

↓

Right Subtree
```

Example:

```text
        A
       / \
      B   C
     / \
    D   E
```

Traversal:

```text
A → B → D → E → C
```

### Common Applications

- copying trees;
- serialization;
- expression tree generation;
- directory structures.

---

# 5. Inorder Traversal

The inorder traversal follows:

```text
Left Subtree

↓

Root

↓

Right Subtree
```

Example:

```text
        A
       / \
      B   C
     / \
    D   E
```

Traversal:

```text
D → B → E → A → C
```

For a **Binary Search Tree**, inorder traversal produces values in **ascending sorted order**.

This property makes inorder traversal one of the most important BST algorithms.

---

# 6. Postorder Traversal

The postorder traversal processes:

```text
Left Subtree

↓

Right Subtree

↓

Root
```

Example:

```text
        A
       / \
      B   C
     / \
    D   E
```

Traversal:

```text
D → E → B → C → A
```

### Common Applications

- deleting trees;
- releasing memory;
- expression evaluation;
- bottom-up computations.

---

# 7. Level-Order Traversal

Level-order traversal visits nodes one level at a time.

```text
Level 1

↓

Level 2

↓

Level 3
```

Example:

```text
        A
       / \
      B   C
     / \
    D   E
```

Traversal:

```text
A → B → C → D → E
```

Unlike the previous traversals, level-order traversal uses a **queue** instead of recursion.

---

# 8. Traversal Comparison

| Traversal | Visiting Order | Typical Applications |
|-----------|----------------|----------------------|
| Preorder | Root → Left → Right | Tree copying, serialization |
| Inorder | Left → Root → Right | Sorted traversal of BSTs |
| Postorder | Left → Right → Root | Tree deletion, expression evaluation |
| Level-Order | Level by Level | BFS, shortest paths, hierarchical processing |

Each traversal serves a different purpose depending on the problem being solved.

---

# 9. Complexity Analysis

Every traversal visits each node exactly once.

| Operation | Complexity |
|-----------|-----------:|
| Preorder | O(n) |
| Inorder | O(n) |
| Postorder | O(n) |
| Level-Order | O(n) |

The space complexity depends on the tree height or the maximum number of nodes stored simultaneously.

---

# 10. Recursive Nature

Depth-first traversals naturally use recursion.

Conceptually:

```text
Visit Node

↓

Traverse Left

↓

Traverse Right
```

Since every subtree is itself a tree, recursion provides an elegant and concise implementation.

Level-order traversal, however, is typically implemented iteratively using a queue.

---

# 11. Engineering Perspective

Tree traversals are fundamental algorithms in software engineering because they define systematic ways of processing hierarchical data.

They are used in:

- compiler syntax trees;
- file system navigation;
- XML and HTML processing;
- database query optimization;
- artificial intelligence;
- game trees;
- expression evaluation;
- serialization frameworks.

Choosing the appropriate traversal depends on the desired processing order rather than on the structure of the tree itself.

---

# 12. Relationship with the Next Document

Tree traversals describe **how nodes are visited**.

The next document introduces **Balanced Trees**, which focus on **maintaining an efficient tree structure**. By keeping the tree height close to logarithmic, balanced trees ensure that traversal, search, insertion, and deletion remain efficient even as the tree grows.

---

# 13. Key Takeaways

After completing this document, the learner should understand that:

- a tree traversal visits every node according to a specific order;
- Depth-First Search includes preorder, inorder, and postorder traversals;
- Breadth-First Search performs level-order traversal using a queue;
- inorder traversal produces sorted output in Binary Search Trees;
- every traversal requires **O(n)** time because each node is visited exactly once;
- selecting the appropriate traversal depends on the requirements of the algorithm and the desired processing order.