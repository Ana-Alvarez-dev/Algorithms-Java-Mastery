# Collisions

## Algorithms Java Mastery

An ideal hash table would assign every key to a unique position in the underlying array. In practice, however, this is impossible for most applications. Since the number of possible keys is usually much larger than the number of available array positions, different keys may be mapped to the same index.

This situation is known as a **collision**. Collisions are a normal characteristic of hash tables, not an implementation error. The efficiency of a hash table therefore depends not on preventing collisions entirely, but on resolving them efficiently.

This document introduces the concept of collisions, explains why they occur, and prepares the foundation for the collision resolution techniques studied in the following documents.

---

# 1. Purpose

The purpose of this document is to:

- define collisions in hash tables;
- explain why collisions are unavoidable;
- understand their impact on performance;
- introduce the need for collision resolution strategies.

---

# 2. What Is a Collision?

A **collision** occurs when two or more different keys are assigned to the same table index.

Conceptually:

```text
Key A
        \
         \
          ↓
        Index 8
          ↑
         /
        /
Key B
```

Although the keys are different, they compete for the same storage location.

---

# 3. Why Do Collisions Occur?

Collisions occur because a hash table has a **finite number of positions**, while the set of possible keys is often extremely large.

For example:

```text
Possible Keys

Millions of values

↓

Hash Function

↓

Table with 100 positions
```

Since many more keys exist than available positions, some keys must inevitably share the same index.

---

# 4. An Example

Consider a table with ten positions.

```text
Table Size = 10
```

Suppose the following keys produce these indices:

```text
Key 25

↓

Index 5
```

```text
Key 35

↓

Index 5
```

Both keys map to the same position.

This is a collision.

---

# 5. Are Collisions a Problem?

Collisions are expected in every practical hash table.

A collision does **not** indicate that the hash function is incorrect.

Instead, it simply means that two different keys share the same computed location.

The objective of a good hash table is to manage collisions while maintaining efficient average performance.

---

# 6. The Impact of Collisions

When collisions become frequent:

- search operations become slower;
- insertion requires additional work;
- deletion becomes more complex;
- average performance gradually decreases.

In the worst case, excessive collisions may cause operations to approach linear time.

---

# 7. The Role of the Hash Function

Although collisions cannot be eliminated, a good hash function can **reduce their frequency**.

A well-designed function:

- distributes keys uniformly;
- avoids clustering;
- minimizes the number of shared indices.

A poor hash function concentrates many keys in only a few locations, increasing collision rates.

---

# 8. Collision Resolution

Since collisions are unavoidable, every hash table must include a strategy for resolving them.

The two principal approaches are:

## Separate Chaining

Each table position stores a collection of elements that share the same index.

```text
Index 5

↓

A → B → C
```

---

## Open Addressing

If the computed position is occupied, the algorithm searches for another available position inside the table.

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

Both techniques are examined in detail in the next documents.

---

# 9. Good Design Principles

Efficient collision management depends on several factors:

- choosing an effective hash function;
- maintaining an appropriate load factor;
- selecting a suitable collision resolution strategy;
- resizing the table when necessary.

Together, these decisions determine the practical performance of a hash table.

---

# 10. Relationship with the Next Documents

Understanding collisions provides the foundation for the remainder of this module.

The following documents study the two most widely used collision resolution techniques:

- **Separate Chaining**, which stores colliding elements together;
- **Open Addressing**, which searches for alternative positions within the table.

Both approaches seek to preserve efficient average-case performance despite collisions.

---

# 11. Key Takeaways

After completing this document, the learner should understand that:

- collisions occur when different keys map to the same table index;
- collisions are an expected property of hash tables, not an error;
- they arise because the number of possible keys exceeds the number of available storage positions;
- a good hash function reduces, but cannot eliminate, collisions;
- efficient collision resolution is essential for maintaining the performance of a hash table.