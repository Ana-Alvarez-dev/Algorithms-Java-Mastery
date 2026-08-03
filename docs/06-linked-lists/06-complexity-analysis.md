# Complexity Analysis

## Algorithms Java Mastery

The study of data structures is incomplete without analysing the computational
cost of the operations they support.

A linked list may provide excellent performance for some operations while being
less efficient for others. These differences arise not from the programming
language or implementation details, but from the structural organisation of the
data itself.

Understanding computational complexity allows software engineers to predict how
algorithms behave as the amount of data grows and to select the most
appropriate data structure for a given problem.

Rather than memorising complexity values, this document focuses on explaining
*why* each operation exhibits its corresponding computational behaviour.

---

# Academic Foundation

The concepts presented throughout this document are based on the principles of
algorithm analysis introduced in *Introduction to Algorithms* by Cormen,
Leiserson, Rivest and Stein and on the complexity analysis methodology studied
throughout this repository.

Complexity analysis evaluates the asymptotic behaviour of algorithms,
independently of hardware characteristics, compiler optimisations or
programming languages.

Additional references are maintained in:

```text
docs/00-project/10-references.md
```

---

# Purpose

The purpose of this document is to analyse the computational complexity of the
fundamental operations performed on linked lists.

The learner will understand:

- why each operation has its computational cost;
- how traversal influences performance;
- differences between singly, doubly and circular linked lists;
- engineering trade-offs when selecting a linked representation.

---

# Learning Objectives

After completing this document, the learner should be able to:

## Conceptual understanding

- explain the complexity of common linked-list operations;
- distinguish constant-time and linear-time operations;
- relate complexity to structural organisation.

## Analytical reasoning

- identify the dominant operation of an algorithm;
- predict algorithm growth;
- compare linked lists with arrays.

## Engineering perspective

- evaluate performance trade-offs;
- justify data-structure selection;
- estimate scalability.

---

# Why Complexity Matters

Two implementations may produce exactly the same result while exhibiting
completely different execution times.

The difference is determined by the number of elementary operations required to
solve the problem.

For linked lists, the dominant factor is usually **node traversal**.

Operations that require visiting multiple nodes become more expensive as the
list grows.

---

# Sources of Computational Cost

The running time of linked-list algorithms depends primarily on three factors.

## Traversal

Moving from one node to another requires following references.

The greater the distance to the desired node, the greater the computational
cost.

---

## Reference Updates

Insertion and deletion generally modify only a small number of references.

Because elements are not shifted in memory, these operations are often
performed in constant time once the required position is known.

---

## Searching

Finding a particular node usually requires sequential traversal.

The search operation therefore dominates the overall complexity of many
algorithms.

---

# Complexity of Fundamental Operations

The following table summarises the theoretical worst-case complexity of the most
common operations.

| Operation | Complexity |
|-----------|-----------:|
| Create an empty list | O(1) |
| Check whether the list is empty | O(1) |
| Access the head | O(1) |
| Traverse the list | O(n) |
| Search for a value | O(n) |
| Access by position | O(n) |
| Insert at the head | O(1) |
| Insert after a known node | O(1) |
| Insert at the tail* | O(n) |
| Delete the head | O(1) |
| Delete after a known predecessor | O(1) |
| Reverse the list | O(n) |
| Count elements | O(n) |

\*Assuming only a head reference is maintained.

---

# Why Traversal Costs O(n)

Traversal visits every node exactly once.

Conceptually:

```text
Head

A → B → C → D → E
```

To reach the fifth node, the algorithm must first visit:

- node A;
- node B;
- node C;
- node D.

Only then can it process node E.

The number of visited nodes grows proportionally with the size of the list.

---

# Why Search Costs O(n)

Linked lists do not provide direct indexing.

Searching therefore proceeds sequentially.

Worst case:

```text
Target

A → B → C → D → E
                ▲
```

If the desired element is located at the end—or is not present—the algorithm
must examine every node.

---

# Why Insertion at the Head Costs O(1)

Insertion at the beginning modifies only two structural relationships.

Conceptually:

Before

```text
Head

A → B
```

After

```text
Head

X → A → B
```

Only the new node and the head reference are updated.

No traversal is required.

---

# Why Deletion at the Head Costs O(1)

Deleting the first node simply redirects the head to the second node.

Before

```text
Head

A → B → C
```

After

```text
Head

B → C
```

Again, only a constant number of references changes.

---

# Why Access by Position Costs O(n)

Unlike arrays, linked lists do not associate positions with memory addresses.

Accessing the eighth element requires traversing the preceding seven nodes.

For this reason, linked lists are unsuitable for applications dominated by
random indexed access.

---

# Complexity of Reversal

Reversing a linked list requires updating every relationship between
neighbouring nodes.

Every node participates in the transformation.

Consequently, reversal performs work proportional to the number of elements.

---

# Best, Average and Worst Cases

Complexity analysis often distinguishes three scenarios.

## Best Case

The operation completes with minimal work.

Example:

- searching for the first element.

Complexity:

```text
O(1)
```

---

## Average Case

The desired element is expected to appear somewhere within the collection.

Traversal typically visits approximately half of the nodes.

Although fewer nodes are examined than in the worst case, the asymptotic
complexity remains:

```text
O(n)
```

---

## Worst Case

The desired element appears at the end of the list or does not exist.

Every node must be examined.

Complexity:

```text
O(n)
```

---

# Comparison of Linked List Variants

| Operation | Singly | Doubly | Circular |
|-----------|--------|---------|----------|
| Forward traversal | O(n) | O(n) | O(n) |
| Backward traversal | — | O(n) | Depends on implementation |
| Insert after known node | O(1) | O(1) | O(1) |
| Delete known node | O(1)* | O(1) | O(1)* |
| Search | O(n) | O(n) | O(n) |

\*Provided the required predecessor is already known.

Although their internal organisation differs, the asymptotic complexity of the
principal operations remains largely unchanged.

The primary differences concern implementation simplicity and navigation
capabilities rather than asymptotic growth.

---

# Comparison with Arrays

| Operation | Array | Linked List |
|-----------|------:|------------:|
| Indexed access | O(1) | O(n) |
| Search | O(n) | O(n) |
| Insert at beginning | O(n) | O(1) |
| Delete at beginning | O(n) | O(1) |
| Dynamic growth | Limited | Natural |

Arrays optimise direct access.

Linked lists optimise structural modification.

Selecting one representation over the other depends on the dominant operations
performed by the application.

---

# Time-Space Trade-Off

Improving one aspect of a data structure often requires sacrificing another.

Linked lists illustrate this principle clearly.

Advantages include:

- efficient structural modification;
- dynamic growth;
- flexible memory allocation.

Disadvantages include:

- additional memory for references;
- poor indexed access;
- increased traversal cost.

Engineering decisions require balancing these competing factors.

---

# Relationship with Previous Modules

This document applies concepts introduced in:

- Computational Complexity
- Asymptotic Analysis
- Big-O Notation
- Best, Average and Worst Cases

The linked-list operations studied previously now serve as practical examples
of those theoretical concepts.

---

# Relationship with Java

The theoretical complexity analysed here is independent of Java.

However, understanding these results allows developers to predict the behaviour
of Java implementations before analysing their source code or using the Java
Collections Framework.

Complexity analysis therefore precedes implementation.

---

# Relationship with Automated Testing

Automated tests verify that an implementation behaves correctly.

Complexity analysis evaluates how efficiently that implementation scales.

Both activities are complementary.

A correct implementation may still be unsuitable if its performance does not
meet application requirements.

---

# Engineering Considerations

Software engineers should avoid selecting data structures solely because they
are familiar.

Instead, the choice should be guided by the dominant operations performed by
the application.

Linked lists are excellent when structural modifications occur frequently.

Arrays are preferable when rapid indexed access dominates.

Understanding computational complexity enables informed engineering decisions
based on measurable characteristics rather than intuition.

---

# Key Takeaways

After studying this document, the learner should understand that:

- computational complexity predicts algorithm scalability;
- traversal is the dominant cost of most linked-list algorithms;
- insertion and deletion are efficient once the required position is known;
- linked lists prioritise structural flexibility over indexed access;
- asymptotic analysis explains *why* operations perform differently;
- selecting an appropriate data structure is fundamentally an engineering
  decision supported by complexity analysis.

These concepts complete the theoretical study of linked lists and prepare the
learner to implement and evaluate linked-list algorithms in Java using
correctness reasoning, automated testing and experimental benchmarking.