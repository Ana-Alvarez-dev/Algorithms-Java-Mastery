# Separate Chaining

## Algorithms Java Mastery

Since collisions are unavoidable in hash tables, every implementation must provide a mechanism for storing multiple elements that map to the same table index. One of the oldest and most widely used solutions is **Separate Chaining**.

Instead of forcing each table position to contain only one element, Separate Chaining allows each position to store a collection of elements. When two or more keys produce the same index, they are placed in the same collection, commonly implemented as a linked list.

This approach keeps the hash table simple while maintaining efficient average-case performance when collisions remain relatively infrequent.

---

# 1. Purpose

The purpose of this document is to:

- explain the Separate Chaining collision resolution strategy;
- understand how collisions are managed using linked structures;
- analyse its advantages and disadvantages;
- prepare the learner for comparing alternative collision resolution techniques.

---

# 2. The Basic Idea

In Separate Chaining, each position of the hash table stores a collection of elements rather than a single value.

Conceptually:

```text
Table

0

1

2 ──► A → B → C

3

4
```

All elements stored in the same chain share the same hash index.

---

# 3. How Insertion Works

The insertion process consists of three simple steps:

```text
Key

↓

Hash Function

↓

Index

↓

Insert into the corresponding chain
```

If the chain is empty, the new element becomes its first node.

If the chain already contains elements, the new element is added to the collection.

---

# 4. Searching for an Element

Searching follows two stages.

### Step 1

Compute the hash value.

```text
Key

↓

Hash Function

↓

Index
```

### Step 2

Search only inside the corresponding chain.

```text
Index 7

↓

A → B → C
```

Instead of examining the entire table, only the elements stored in one chain are inspected.

---

# 5. Deletion

Deletion follows the same strategy.

```text
Key

↓

Hash Function

↓

Locate Chain

↓

Remove Matching Element
```

Only the target chain must be traversed.

---

# 6. Why Linked Lists?

Linked lists are traditionally used because they provide:

- dynamic growth;
- efficient insertion;
- no need for contiguous memory.

Conceptually:

```text
Index 5

↓

Node → Node → Node
```

Although linked lists are the most common implementation, other collections may also be used depending on the application.

---

# 7. Advantages

Separate Chaining offers several important benefits:

- simple implementation;
- handles collisions naturally;
- table never becomes completely full;
- deletion is straightforward;
- supports dynamic growth of individual chains.

These characteristics make it a practical solution for many hash table implementations.

---

# 8. Limitations

Separate Chaining also has some disadvantages.

- additional memory is required for the linked nodes;
- long chains increase search time;
- poor hash functions produce uneven chain lengths;
- cache locality is generally weaker than array-based approaches.

Performance depends heavily on keeping chains relatively short.

---

# 9. Complexity Analysis

Assuming a good hash function and a reasonable load factor:

| Operation | Average Case | Worst Case |
|-----------|-------------:|-----------:|
| Search | O(1) | O(n) |
| Insert | O(1) | O(n) |
| Delete | O(1) | O(n) |

The worst case occurs when many elements are stored in the same chain.

---

# 10. Relationship with the Load Factor

As more elements are inserted, chains tend to grow.

```text
More Elements

↓

Longer Chains

↓

More Comparisons

↓

Lower Performance
```

Maintaining an appropriate **load factor** helps preserve the expected constant-time average performance.

---

# 11. Comparison with Open Addressing

Separate Chaining differs fundamentally from Open Addressing.

| Separate Chaining | Open Addressing |
|-------------------|-----------------|
| Stores colliding elements in external chains | Stores all elements inside the table |
| Additional memory required | No external storage |
| Simple deletion | More complex deletion |
| Table can exceed the number of buckets | Table capacity is limited |

The next document introduces the Open Addressing strategy.

---

# 12. Engineering Perspective

Separate Chaining is particularly useful when:

- collisions are expected;
- the number of stored elements changes frequently;
- simple insertion and deletion are priorities;
- additional memory overhead is acceptable.

Modern software systems choose between Separate Chaining and Open Addressing based on performance requirements, memory constraints, and expected workloads.

---

# 13. Key Takeaways

After completing this document, the learner should understand that:

- Separate Chaining stores colliding elements in collections associated with each table index;
- linked lists are the traditional implementation for these chains;
- search, insertion, and deletion operate only within the corresponding chain;
- average-case performance remains **O(1)** when chains remain short;
- maintaining a good hash function and an appropriate load factor is essential for preserving efficiency.