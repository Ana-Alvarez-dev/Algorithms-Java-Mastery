# Balanced Trees

> AVL balance concerns subtree **heights**, not equal subtree populations.
> Two primitive rotations compose the left-right/right-left repairs. Deletion
> can repair several ancestors and must handle a zero-balance heavy child.
> See the [contract and proof map](implementation-contracts.md) for the recurrence,
> ordering partitions, cached-height updates and corresponding Java tests.

## Algorithms Java Mastery

The [implementation guide](implementation-and-benchmark-guide.md) defines the
selected Java scope, contracts, proof arguments, tests and benchmark protocol.
Conceptual extensions below are not automatically implemented.

The efficiency of a Binary Search Tree depends directly on its height. When the tree remains balanced, searching, insertion, and deletion require relatively few comparisons. However, if nodes are inserted in an unfavorable order, a Binary Search Tree may become highly unbalanced, causing its height to approach the number of stored elements and reducing its performance to that of a linked list.

To overcome this limitation, computer scientists developed **Balanced Trees**. These data structures automatically maintain a relatively small height as elements are inserted and removed, ensuring that the fundamental operations continue to execute efficiently regardless of insertion order.

Balanced Trees are widely used in programming language libraries, database systems, file systems, operating systems, and many backend applications where predictable performance is essential.

---

# 1. Purpose

The purpose of this document is to:

- explain why tree balancing is necessary;
- introduce the concept of balanced trees;
- understand how balance affects performance;
- present the most common self-balancing tree structures.

---

# 2. Why Is Balancing Necessary?

Consider two Binary Search Trees containing the same values.

Balanced tree:

```text
          40
        /    \
      20      60
     / \     / \
   10 30   50 70
```

Unbalanced tree:

```text
10
 \
 20
   \
   30
     \
      40
        \
         50
           \
            60
             \
              70
```

Although both trees store identical data, the number of comparisons required for searching is dramatically different.

A height-balanced search tree bounds worst-case path length. It need not have
the minimum possible height for its population, equal subtree sizes or a
logarithmic complete traversal.

---

# 3. What Is a Balanced Tree?

A **Balanced Tree** is a tree whose height remains close to the minimum possible as nodes are inserted and removed.

The objective is logarithmic height, not visual symmetry. The selected AVL
invariant requires each node's left/right subtree heights to differ by at most
one; cached heights are updated after each repair.

Conceptually:

```text
Insert

↓

Tree Becomes Unbalanced

↓

Rebalance Tree

↓

Height Remains Small
```

---

# 4. Height and Performance

The height of a tree determines the maximum number of comparisons required for searching.

| Tree Structure | Search Complexity |
|---------------|------------------:|
| Balanced Tree | O(log n) |
| Unbalanced Tree | O(n) |

Keeping the height logarithmic is the primary goal of every self-balancing tree.

---

# 5. Tree Rotations

AVL and red-black search trees use **rotations**. Multiway B-trees instead
manage occupancy with operations such as splits and merges; rotation-based
binary repair is not a universal implementation of every balanced tree.

A rotation reorganizes a small portion of the tree without changing the ordering of its elements.

Conceptually:

```text
Before Rotation

↓

Rotate

↓

Balanced Structure
```

Rotations preserve in-order key order. Appropriate local repairs restore AVL
balance; deletion can require repair at multiple ancestors and can encounter a
heavy child whose balance is zero. A complete traversal remains linear.

---

# 6. Types of Rotations

For AVL repair there are two primitive rotations (left/right) and two
compositions (left-right/right-left). This is not a universal repair scheme for
all self-balancing tree families.

### Left Rotation

Moves a right child upward.

---

### Right Rotation

Moves a left child upward.

---

### Left-Right Rotation

Performs a left rotation followed by a right rotation.

---

### Right-Left Rotation

Performs a right rotation followed by a left rotation.

These operations restore balance after insertions or deletions.

---

# 7. Common Balanced Trees

Several self-balancing tree structures have been developed.

### AVL Tree

Characteristics:

- subtree heights differ by at most one at every node; perfect symmetry is not required;
- uses balance factors;
- very fast searching;
- additional rotations may be required after updates.

---

### Red-Black Tree

Characteristics:

- uses node colors;
- slightly less strict balancing;
- different balancing and rotation trade-offs, depending on the update;
- widely used in standard libraries.

Examples include:

- Java `TreeMap`
- Java `TreeSet`

---

### B-Tree

Characteristics:

- allows multiple keys per node;
- optimized for secondary storage;
- commonly used in databases and file systems.

---

# 8. Advantages

Balanced trees provide several important benefits.

- predictable performance;
- logarithmic search time;
- efficient insertion and deletion;
- scalable for large datasets;
- suitable for dynamic collections.

These characteristics make them ideal for applications requiring ordered data.

---

# 9. Limitations

Maintaining balance introduces additional complexity.

Balanced trees require:

- balance verification;
- rotation operations;
- more sophisticated insertion algorithms;
- more complex deletion procedures.

This additional work is justified by the improved search performance.

---

# 10. Engineering Perspective

Balanced trees are preferred whenever applications require:

- ordered data;
- frequent insertions and deletions;
- predictable performance;
- efficient range queries.

Unlike hash tables, balanced trees preserve sorted order while maintaining efficient logarithmic operations.

For this reason, balanced trees are widely used in databases, memory management systems, language runtimes, and many enterprise software applications.

---

# 11. Relationship with the Next Document

Balanced trees automatically maintain efficient search performance.

The next document introduces **Heaps**, another specialized tree structure designed for a different purpose.

Unlike Binary Search Trees, heaps prioritize efficient access to the smallest or largest element rather than maintaining a fully ordered structure.

---

# 12. Key Takeaways

After completing this document, the learner should understand that:

- the efficiency of Binary Search Trees depends on maintaining a small height;
- balanced trees automatically reorganize themselves to preserve logarithmic height;
- AVL/red-black trees use rotations; multiway B-tree occupancy repair uses
  different operations such as splits and merges;
- AVL Trees, Red-Black Trees, and B-Trees are common examples of self-balancing trees;
- balanced trees provide **O(log n)** search, insertion, and deletion regardless of insertion order;
- self-balancing trees are fundamental components of modern software engineering, database systems, and backend development.