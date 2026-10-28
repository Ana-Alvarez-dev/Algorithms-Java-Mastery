# Open Addressing

> Current Java uses **linear probing** and parallel key/value/hash/state
> arrays. EMPTY ends absence search; DELETED does not. Put first searches for an
> existing equal key, then reuses a nonoccupied slot. Quadratic/double probing
> remain alternatives. See the [contract and proof map](implementation-contracts.md).

> Selected implementations, contracts, pseudocode, traces, correctness, tests
> and JMH protocol: [Implementation guide](implementation-and-benchmark-guide.md).

## Algorithms Java Mastery

**Open Addressing** is a collision resolution strategy in which every element is stored directly inside the hash table itself. Unlike Separate Chaining, no external linked lists or auxiliary collections are used. When a collision occurs, the algorithm searches for another available position according to a predefined probing strategy.

The selected representation stores key/value references, cached hashes and
markers in arrays; key and value objects can live elsewhere in the heap. It
avoids per-entry chain nodes, but locality, byte footprint and timing require
their own measurements. Probe cost depends on clustering and used-slot load,
including tombstones, rather than live occupancy alone.

This document introduces the principles of Open Addressing and the most common probing techniques used in hash tables.

---

# 1. Purpose

The purpose of this document is to:

- explain the Open Addressing collision resolution strategy;
- understand how probing locates alternative storage positions;
- introduce the principal probing methods;
- compare Open Addressing with Separate Chaining.

---

# 2. The Basic Idea

In Open Addressing, every element is stored inside the hash table.

If the computed position is already occupied, the algorithm searches for another available position.

Conceptually:

```text
Key

↓

Hash Function

↓

Index 5

↓

Occupied

↓

Search Next Position
```

The search continues until an empty location is found.

---

# 3. Probing

The process of searching for another position is called **probing**.

Conceptually:

```text
Index 5

Occupied

↓

Index 6

Occupied

↓

Index 7

Available
```

Once an available position is found, the new element is inserted.

---

# 4. Linear Probing

The simplest probing strategy examines the next position sequentially.

Conceptually:

```text
5

↓

6

↓

7

↓

8
```

The algorithm continues until an empty position is found.

### Advantages

- simple implementation;
- good cache locality;
- fast in lightly loaded tables.

### Limitation

Linear probing tends to create **primary clustering**, where long groups of occupied positions develop and increase search times.

---

# 5. Quadratic Probing

Quadratic probing increases the search distance progressively.

Conceptually:

```text
5

↓

6

↓

9

↓

14

...
```

Instead of examining consecutive positions, the distance grows quadratically.

This changes the clustering pattern compared with linear probing. Coverage of
all slots depends on the capacity and coefficients; an arbitrary quadratic
sequence is not guaranteed to visit every slot. The executable scope here uses
linear probing, not this variant.

---

# 6. Double Hashing

Double hashing uses a second hash function to determine the probing interval.

Conceptually:

```text
Primary Hash

↓

Initial Position

↓

Secondary Hash

↓

Probe Distance
```

Different keys can follow different probing sequences. The step must be
nonzero and relatively prime to the capacity to visit every slot; for a
power-of-two table, an odd step supplies that property.

Among the common probing strategies, double hashing generally provides the most uniform distribution.

---

# 7. Searching and Deletion

Searching follows exactly the same probing sequence used during insertion.

```text
Key

↓

Hash Function

↓

Probe Sequence

↓

Element Found
```

Deletion is more complex than in Separate Chaining because simply removing an element may interrupt the probing sequence.

The selected table clears the removed key/value/hash and retains a DELETED
state marker. Replacing it by EMPTY could hide a later key in the same probe
path. When inserting, stopping at the first tombstone before ruling out an
existing key farther along would create duplicate mappings. Both searches are
bounded by capacity probes; step-one wrap visits every slot.

---

# 8. Advantages

Open Addressing offers several benefits:

- entry references and metadata remain in the table arrays;
- no additional linked structures are required;
- adjacent-slot probing offers a locality hypothesis to measure;
- no per-entry chain node is required, while total footprint depends on layout;
- efficient average performance when the table is lightly loaded.

---

# 9. Limitations

Open Addressing also presents challenges:

- performance decreases as the table fills;
- deletion is more complicated;
- clustering may occur;
- resizing requires reinserting all elements.

Maintaining a low load factor is essential for preserving efficiency.

---

# 10. Complexity Analysis

Assuming a good hash function and a reasonable load factor:

| Operation | Average Case | Worst Case |
|-----------|-------------:|-----------:|
| Search | O(1) | O(n) |
| Insert | O(1) | O(n) |
| Delete | O(1) | O(n) |

These ordinary-operation bounds assume capacity proportional to live size and
exclude rebuilding. With deletion markers and retained capacity c, search may
scan O(c) slots even when current n is small. Used-slot occupancy, not only
live load, matters. The selected implementation guide records its cleanup policy
and O(c+n²) collision-heavy rebuild bound.

---

# 11. Comparison with Separate Chaining

| Open Addressing | Separate Chaining |
|-----------------|-------------------|
| Stores all elements inside the table | Uses external chains |
| Parallel arrays; no per-entry chain node | Bucket array plus nodes |
| Adjacent slot scans | Dependent node traversal |
| More complex deletion | Simpler deletion |
| Sensitive to load factor | Less sensitive to load factor |

Both strategies provide efficient average performance when properly implemented.

---

# 12. Engineering Perspective

Open Addressing is often preferred when:

- memory efficiency is important;
- the table size is controlled;
- fast cache-friendly access is desired;
- the expected load factor remains moderate.

Many high-performance hash table implementations employ variations of Open Addressing because modern processors benefit from contiguous memory access.

---

# 13. Relationship with the Next Document

Both Separate Chaining and Open Addressing are strongly influenced by the **load factor**.

As more elements are inserted, collisions become more frequent and performance begins to decline.

The next document explains how the load factor affects efficiency and why hash tables are resized dynamically.

---

# 14. Key Takeaways

After completing this document, the learner should understand that:

- Open Addressing resolves collisions by searching for another position within the table;
- probing determines how alternative positions are explored;
- linear probing, quadratic probing, and double hashing are the most common probing strategies;
- deletion requires special handling to preserve probing sequences;
- maintaining an appropriate load factor is essential for achieving average **O(1)** performance.
