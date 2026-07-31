# Complexity Analysis

## Algorithms Java Mastery

One of the principal advantages of hash tables is their ability to perform search, insertion, and deletion operations in **constant average time**. This performance makes hash tables one of the most efficient data structures for associative data access.

However, the complexity of these operations is not fixed. It depends on several factors, including the quality of the hash function, the collision resolution strategy, and the current load factor of the table.

This document summarizes the computational complexity of hash tables and explains the conditions under which their expected performance can be achieved.

---

# 1. Purpose

The purpose of this document is to:

- analyse the time complexity of hash table operations;
- compare average-case and worst-case performance;
- understand how collisions affect efficiency;
- relate complexity to implementation decisions.

---

# 2. Average-Case Complexity

When:

- the hash function distributes keys uniformly;
- collisions remain infrequent;
- the load factor is properly controlled;

the principal operations execute in constant average time.

| Operation | Average Complexity |
|-----------|-------------------:|
| Search | O(1) |
| Insert | O(1) |
| Delete | O(1) |

This expected efficiency is the primary reason hash tables are widely used in modern software systems.

---

# 3. Worst-Case Complexity

The worst case occurs when many keys are assigned to the same table position.

For example:

```text
Index 5

↓

A → B → C → D → E
```

Searching may require examining every stored element.

| Operation | Worst Complexity |
|-----------|-----------------:|
| Search | O(n) |
| Insert | O(n) |
| Delete | O(n) |

Although uncommon in well-designed hash tables, this scenario demonstrates the importance of good hashing.

---

# 4. Complexity of Collision Resolution

Different collision resolution strategies exhibit similar theoretical complexity but different practical behaviour.

| Strategy | Average | Worst |
|----------|--------:|------:|
| Separate Chaining | O(1) | O(n) |
| Open Addressing | O(1) | O(n) |

The choice of strategy mainly affects memory usage, cache locality, and implementation complexity.

---

# 5. Resizing Complexity

When the load factor exceeds the predefined threshold, the table must be resized.

This process requires:

```text
Create Larger Table

↓

Rehash Every Element

↓

Continue Operations
```

The resizing operation requires:

```text
O(n)
```

time because every stored element must be inserted into the new table.

Since resizing occurs only occasionally, insertions remain **O(1) amortized**.

---

# 6. Space Complexity

The amount of memory required depends on both the table size and the stored elements.

| Structure | Space Complexity |
|-----------|-----------------:|
| Hash Table | O(n) |

Additional memory requirements vary according to the collision resolution strategy.

- Separate Chaining requires memory for linked nodes.
- Open Addressing stores all elements directly inside the table.

---

# 7. Factors Affecting Performance

The practical efficiency of a hash table depends on several factors:

- quality of the hash function;
- number of collisions;
- load factor;
- collision resolution strategy;
- resizing policy.

Poor choices in any of these areas may significantly reduce performance.

---

# 8. Engineering Perspective

Theoretical complexity describes the expected behaviour of a hash table, but practical performance also depends on implementation details.

For example:

- uniform key distribution improves average performance;
- excessive collisions increase the number of comparisons;
- frequent resizing introduces temporary overhead;
- cache-friendly implementations often execute faster in practice.

For this reason, efficient software design requires considering both asymptotic complexity and real-world behaviour.

---

# 9. Complexity Summary

| Operation | Average Case | Worst Case |
|-----------|-------------:|-----------:|
| Search | O(1) | O(n) |
| Insert | O(1) | O(n) |
| Delete | O(1) | O(n) |
| Resize | Occasional O(n) | O(n) |

Under normal conditions, hash tables provide constant average-time performance for the operations that matter most.

---

# 10. Key Takeaways

After completing this document, the learner should understand that:

- hash tables achieve **O(1)** average complexity for search, insertion, and deletion;
- poor hash functions and excessive collisions may degrade performance to **O(n)**;
- resizing requires **O(n)** time but occurs infrequently, resulting in **O(1) amortized** insertion;
- both Separate Chaining and Open Addressing provide efficient average performance when properly implemented;
- the practical efficiency of a hash table depends on balancing algorithmic complexity, memory usage, and implementation quality.