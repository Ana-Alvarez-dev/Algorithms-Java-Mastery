# Common Linked List Algorithms

## Algorithms Java Mastery

Linked lists become truly valuable when they serve as the foundation for
algorithms that manipulate dynamic collections efficiently.

Although the underlying structure is relatively simple, many classical
algorithms rely on linked lists because they support structural modifications
without relocating existing elements.

The objective of this document is not to study every possible linked-list
algorithm, but rather to understand the most representative algorithms that
appear in Computer Science, software engineering, technical interviews and
production systems.

Each algorithm demonstrates a particular way of reasoning about node
relationships, reference manipulation and computational complexity.

---

# Academic Foundation

The algorithms presented throughout this document are inspired by the study of
linked data structures developed in *Introduction to Algorithms* by Cormen,
Leiserson, Rivest and Stein, together with classical algorithmic techniques
widely adopted in Computer Science.

The implementations and explanations contained in this repository are
intentionally original and focus on developing algorithmic reasoning rather
than memorising solutions.

Additional references are maintained in:

```text
docs/00-project/10-references.md
```

---

# Purpose

The purpose of this document is to introduce the most important algorithms that
operate on linked lists.

The learner will understand:

- traversal algorithms;
- searching algorithms;
- insertion strategies;
- deletion strategies;
- list reversal;
- cycle detection;
- list merging;
- middle-node discovery;
- duplicate removal;
- partitioning algorithms.

Rather than focusing on syntax, the emphasis remains on understanding the
algorithmic ideas behind each solution.

---

# Learning Objectives

After completing this document, the learner should be able to:

## Conceptual understanding

- recognise common linked-list algorithms;
- understand the objective of each algorithm;
- explain the reasoning behind each solution.

## Algorithmic reasoning

- identify appropriate traversal strategies;
- analyse reference manipulation;
- predict computational complexity.

## Engineering perspective

- select suitable algorithms for practical problems;
- compare multiple solution strategies;
- evaluate algorithmic trade-offs.

---

# Linear Traversal

Traversal is the foundation of almost every linked-list algorithm.

Conceptually:

```text
Head

A → B → C → D → E → null
```

Every node is visited exactly once.

Traversal serves as the basis for:

- searching;
- counting;
- printing;
- aggregation;
- validation.

Typical complexity:

```text
Time  : O(n)

Space : O(1)
```

---

# Searching

Searching attempts to locate a node satisfying a particular condition.

The search terminates when:

- the element is found;
- the end of the list is reached.

Conceptually:

```text
Head

A → B → C → D → E
```

Typical complexity:

```text
Time  : O(n)

Space : O(1)
```

---

# Finding the Middle Node

One classical algorithm uses two pointers.

```text
Slow

↓

A → B → C → D → E
↑
Fast
```

The slow pointer advances one node at a time.

The fast pointer advances two nodes at a time.

When the fast pointer reaches the end of the list, the slow pointer points to
the middle element.

Advantages:

- single traversal;
- no element counting required.

Typical complexity:

```text
Time  : O(n)

Space : O(1)
```

---

# Reversing a Linked List

Reversal changes the logical direction of every reference.

Before:

```text
A → B → C → D
```

After:

```text
D → C → B → A
```

The algorithm repeatedly redirects references while preserving access to the
remaining nodes.

Typical complexity:

```text
Time  : O(n)

Space : O(1)
```

---

# Cycle Detection

A linked list may accidentally become circular.

Conceptually:

```text
A → B → C → D
     ↑       │
     └───────┘
```

One of the best-known solutions is the **Fast and Slow Pointer Algorithm**
(often referred to as Floyd's Cycle Detection Algorithm).

If both pointers eventually meet, a cycle exists.

Otherwise, the list is acyclic.

Typical complexity:

```text
Time  : O(n)

Space : O(1)
```

---

# Merging Two Sorted Lists

Two independently sorted lists may be combined into a new ordered list.

Example:

```text
List A

1 → 3 → 7

List B

2 → 5 → 8
```

Merged result:

```text
1 → 2 → 3 → 5 → 7 → 8
```

This algorithm appears frequently in divide-and-conquer techniques,
particularly Merge Sort.

Typical complexity:

```text
Time  : O(n)

Space : O(1)
```

assuming the existing nodes are reused.

---

# Removing Duplicates

Duplicate elements may be eliminated while preserving the remaining structure.

Example:

Before

```text
1 → 2 → 2 → 3 → 3 → 5
```

After

```text
1 → 2 → 3 → 5
```

The implementation strategy depends on whether the list is already sorted.

Typical complexity:

Sorted list

```text
Time  : O(n)

Space : O(1)
```

---

# Partitioning a List

Partitioning divides the list according to a condition.

Example:

```text
Pivot = 5

3 → 8 → 2 → 7 → 1
```

Possible result:

```text
3 → 2 → 1 → 8 → 7
```

Partitioning algorithms are widely used in sorting and selection algorithms.

---

# Concatenating Lists

Concatenation joins two independent linked lists.

Before:

```text
A → B

C → D
```

After:

```text
A → B → C → D
```

The algorithm reconnects the tail of the first list to the head of the second.

---

# Splitting a List

Splitting divides a linked list into two independent structures.

Before:

```text
A → B → C → D → E
```

After:

```text
List 1

A → B → C

List 2

D → E
```

Splitting is frequently used in recursive algorithms.

---

# Recursive Traversal

Although iterative traversal is generally preferred, recursion provides an
alternative formulation.

Conceptually:

```text
Visit Node

↓

Visit Next Node

↓

Visit Next Node
```

Recursive solutions are elegant but consume additional stack memory.

Typical complexity:

```text
Time  : O(n)

Space : O(n)
```

because of recursive function calls.

---

# Algorithm Comparison

| Algorithm | Time | Space |
|-----------|-----:|------:|
| Traversal | O(n) | O(1) |
| Search | O(n) | O(1) |
| Middle Node | O(n) | O(1) |
| Reverse | O(n) | O(1) |
| Cycle Detection | O(n) | O(1) |
| Merge Sorted Lists | O(n) | O(1)* |
| Remove Duplicates | O(n) | O(1) |
| Partition | O(n) | O(1) |
| Recursive Traversal | O(n) | O(n) |

\*Assuming existing nodes are reused.

---

# Relationship with Previous Modules

The algorithms presented in this document apply concepts introduced throughout
this module:

- Singly Linked Lists;
- Doubly Linked Lists;
- Circular Linked Lists;
- List Operations;
- Complexity Analysis;
- Java Linked Lists.

Each algorithm combines structural reasoning with computational complexity.

---

# Relationship with Java

Within this repository, every algorithm is implemented manually before being
compared with equivalent functionality provided by the Java Collections
Framework.

This approach allows the learner to understand:

- pointer manipulation;
- structural correctness;
- algorithm design;
- computational efficiency.

Understanding the algorithm is prioritised over memorising library methods.

---

# Relationship with Automated Testing

Every algorithm should be validated through automated tests.

Representative scenarios include:

- empty list;
- single-node list;
- multiple-node list;
- sorted input;
- unsorted input;
- duplicate elements;
- cyclic lists;
- edge cases.

Testing confirms both functional correctness and preservation of structural
integrity.

---

# Relationship with Benchmarking

Although many linked-list algorithms share the same asymptotic complexity,
their practical performance may differ.

Benchmarking allows engineers to compare:

- iterative versus recursive traversal;
- different reversal strategies;
- merge implementations;
- duplicate-removal algorithms.

Within this repository, these comparisons are performed using JMH to complement
theoretical complexity analysis with empirical measurements.

---

# Engineering Considerations

The algorithms presented in this document demonstrate that solving linked-list
problems is fundamentally an exercise in reference manipulation.

Most implementation errors arise not from misunderstanding the algorithm itself
but from incorrectly updating node references.

For this reason, software engineers should prioritise:

- preserving structural invariants;
- analysing edge cases;
- reasoning about complexity;
- validating behaviour through automated testing.

Mastering these algorithms develops algorithmic thinking that extends far
beyond linked lists and applies to many dynamic data structures.

---

# Key Takeaways

After studying this document, the learner should understand that:

- traversal forms the basis of nearly every linked-list algorithm;
- many classical problems can be solved in linear time;
- efficient algorithms minimise unnecessary traversals;
- reference manipulation is central to linked-list programming;
- algorithm correctness depends on preserving structural integrity;
- theoretical analysis, testing and benchmarking together provide a complete
  engineering evaluation of linked-list algorithms.

These algorithms complete the practical study of linked lists and establish the
foundation required for solving more advanced problems involving trees, graphs,
hash tables and other dynamic data structures.