# Hash Functions

## Algorithms Java Mastery

A hash table is only as effective as the **hash function** it uses. Regardless of the size of the table or the quality of its implementation, poor hashing leads to uneven data distribution, frequent collisions, and degraded performance.

The purpose of a hash function is not to eliminate collisions completely—that is generally impossible—but to distribute keys as uniformly as possible across the available positions in the table. A well-designed hash function allows hash tables to achieve their expected average-case performance.

This document introduces the role of hash functions, their desired properties, and the basic techniques used to transform keys into table indices.

---

# 1. Purpose

The purpose of this document is to:

- understand the role of hash functions;
- explain how keys are transformed into table indices;
- identify the characteristics of a good hash function;
- introduce common hashing techniques.

---

# 2. What Is a Hash Function?

A **hash function** is an algorithm that converts a key into an integer value, which is then used to determine the storage location within a hash table.

Conceptually:

```text
Key

↓

Hash Function

↓

Hash Value

↓

Array Index
```

The computed index identifies where the associated element should be stored or searched.

---

# 3. Why Is a Hash Function Necessary?

A hash table stores its elements inside an array.

However, keys are often not suitable as array indices.

Examples:

```text
Employee ID = 845932

Email = alice@example.com

Username = developer01
```

A hash function transforms these keys into valid positions inside the table.

Example:

```text
Employee ID

845932

↓

Hash Function

↓

Index 18
```

---

# 4. Desired Characteristics

A good hash function should satisfy several important properties.

## Uniform Distribution

Keys should be spread evenly throughout the table.

Good distribution:

```text
0 1 2 3 4 5 6 7

X   X X   X   X
```

Poor distribution:

```text
0 1 2 3 4 5 6 7

XXXXX
```

Uniform distribution reduces the probability of collisions.

---

## Deterministic

The same key must always produce the same hash value.

```text
Hash("Alice")

↓

25
```

Every execution must return the same result.

---

## Efficient

The computation of the hash value should be fast.

If computing the hash function becomes expensive, the advantages of using a hash table diminish.

---

## Dependent on the Entire Key

Small changes in the key should generally produce different hash values.

Example:

```text
Alice

↓

42
```

```text
Alicf

↓

11
```

This reduces clustering and improves distribution.

---

# 5. From Hash Value to Table Index

The hash value itself may be much larger than the table size.

Therefore, it is converted into a valid array index.

A common conceptual approach is:

```text
Index = Hash Value mod Table Size
```

Example:

```text
Hash Value = 245

Table Size = 16

↓

245 mod 16 = 5
```

The element is stored at index **5**.

---

# 6. Good vs Poor Hash Functions

### Good Hash Function

- distributes keys evenly;
- minimizes collisions;
- provides predictable performance.

### Poor Hash Function

- clusters many keys together;
- produces frequent collisions;
- degrades average-case performance.

The quality of the hash function directly affects the efficiency of the hash table.

---

# 7. Can Collisions Be Avoided?

In practice, **no**.

Different keys may produce the same table index.

Example:

```text
Key A

↓

Index 8
```

```text
Key B

↓

Index 8
```

This situation is called a **collision**.

Handling collisions efficiently is one of the fundamental responsibilities of a hash table and is explored in the following documents.

---

# 8. Hash Functions in Java

Every Java object inherits the method:

```text
hashCode()
```

This method returns an integer hash value representing the object's state.

Collections such as `HashMap` and `HashSet` use this value internally to determine where elements should be stored.

For custom classes, developers can override `hashCode()` to provide a hash function that reflects the object's contents.

---

# 9. Best Practices

When designing or using hash functions:

- aim for a uniform distribution of keys;
- avoid simple formulas that produce excessive clustering;
- ensure the function is deterministic;
- keep the computation efficient;
- remember that collisions are expected and must be handled appropriately.

---

# 10. Relationship with the Next Document

A well-designed hash function reduces the number of collisions, but it cannot eliminate them completely.

The next document explains:

- why collisions occur;
- how they affect performance;
- why every practical hash table must include a collision resolution strategy.

---

# 11. Key Takeaways

After completing this document, the learner should understand that:

- a hash function transforms keys into array indices;
- good hash functions distribute keys uniformly across the table;
- deterministic and efficient computation is essential;
- collisions are unavoidable in practical hash tables;
- the quality of the hash function has a direct impact on the overall performance of a hash table.