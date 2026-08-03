# Load Factor and Resizing

## Algorithms Java Mastery

The performance of a hash table depends not only on the quality of its hash function or collision resolution strategy, but also on **how full the table becomes**. As more elements are inserted, collisions become increasingly frequent, causing search, insertion, and deletion operations to require additional work.

To maintain efficient performance, hash tables monitor a metric known as the **load factor**. When this value exceeds a predefined threshold, the table is expanded and its elements are redistributed. This process is called **resizing** or **rehashing**.

This document explains how the load factor influences performance and why dynamic resizing is essential for preserving the average-case efficiency of hash tables.

---

# 1. Purpose

The purpose of this document is to:

- explain the concept of the load factor;
- understand its effect on hash table performance;
- introduce dynamic resizing;
- explain the rehashing process.

---

# 2. What Is the Load Factor?

The **load factor** measures how full a hash table is.

It is defined as:

```text
Load Factor = Number of Stored Elements
              --------------------------
                 Number of Buckets
```

For example:

```text
Elements = 60

Buckets = 100

↓

Load Factor = 0.60
```

A higher load factor generally increases the probability of collisions.

---

# 3. Why Is the Load Factor Important?

As the table becomes more populated:

```text
More Elements

↓

Higher Load Factor

↓

More Collisions

↓

Lower Performance
```

A low load factor allows keys to be distributed more evenly, reducing the number of comparisons required during search operations.

---

# 4. Typical Thresholds

Most hash table implementations define a maximum load factor.

When this threshold is exceeded, the table is resized.

A commonly used threshold is:

```text
0.75
```

This value provides a practical balance between:

- memory usage;
- collision frequency;
- overall performance.

---

# 5. What Is Resizing?

**Resizing** is the process of creating a larger hash table when the current one becomes too full.

Conceptually:

```text
Old Table

↓

Create Larger Table

↓

Reinsert Elements

↓

Continue Operations
```

Increasing the table size reduces the average number of collisions.

---

# 6. Rehashing

Simply copying elements into a larger table is **not sufficient**.

Because the table size changes, the computed indices also change.

Each element must therefore be processed again.

```text
Old Key

↓

Hash Function

↓

New Table Size

↓

New Index
```

This process is called **rehashing**.

---

# 7. Complexity of Resizing

Resizing requires every stored element to be reinserted into the new table.

| Operation | Complexity |
|-----------|-----------:|
| Resize / Rehash | O(n) |

Although resizing is expensive, it occurs only occasionally.

Therefore, insertion remains:

```text
Average: O(1)
```

This behaviour is known as **amortized constant time**.

---

# 8. Benefits of Resizing

Dynamic resizing offers several advantages:

- maintains a low collision rate;
- preserves efficient average performance;
- improves key distribution;
- supports growing collections automatically.

Without resizing, performance would gradually degrade as the table fills.

---

# 9. Engineering Perspective

Choosing an appropriate load factor involves balancing two competing goals.

### Low Load Factor

Advantages:

- fewer collisions;
- faster operations.

Disadvantage:

- higher memory consumption.

---

### High Load Factor

Advantages:

- lower memory usage.

Disadvantages:

- more collisions;
- slower search and insertion;
- reduced overall performance.

For this reason, modern hash table implementations select thresholds that balance both concerns.

---

# 10. Java HashMap

The Java `HashMap` implementation automatically monitors its load factor.

When the number of stored elements exceeds the configured threshold:

- a larger internal table is created;
- all entries are rehashed;
- normal operations resume.

This process is transparent to the developer and helps maintain average **O(1)** performance.

---

# 11. Relationship with the Next Document

The next document examines **Java HashMap** in greater detail.

It explains how the theoretical concepts introduced throughout this module—hash functions, collisions, load factor, resizing, and rehashing—are applied in one of the most widely used data structures of the Java Collections Framework.

---

# 12. Key Takeaways

After completing this document, the learner should understand that:

- the load factor measures how full a hash table is;
- higher load factors generally increase the number of collisions;
- resizing expands the table to preserve efficient performance;
- rehashing recomputes the storage position of every element after resizing;
- although resizing requires **O(n)** time, insertions remain **O(1)** on average because resizing occurs only occasionally.