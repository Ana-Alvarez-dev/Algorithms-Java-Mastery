# Java HashMap

> The manual `HashTable` API is intentionally smaller than Map: it exposes
> capacity and shallow snapshots but no iterator, entry views or null mappings.
> A HashMap null get result needs containsKey to distinguish absence from a null
> mapping. The [contract map](implementation-contracts.md) compares only the
> common non-null workload and keeps implementation details separate.

> Selected implementations, contracts, pseudocode, traces, correctness, tests
> and JMH protocol: [Implementation guide](implementation-and-benchmark-guide.md).

## Algorithms Java Mastery

The concepts introduced throughout this module—hash functions, collisions, collision resolution, load factor, and resizing—are applied in practice through the **HashMap** class, one of the most widely used data structures in the Java Collections Framework.

`HashMap` provides an efficient implementation of the **Map** interface, allowing values to be stored and retrieved using unique keys. It is designed to offer average constant-time performance for search, insertion, and deletion operations, making it suitable for a broad range of applications.

This document explains how `HashMap` relates to the theoretical concepts studied in this module and introduces its fundamental characteristics from an engineering perspective.

---

# 1. Purpose

The purpose of this document is to:

- introduce the Java `HashMap` class;
- understand its relationship with hash tables;
- explain its basic operations;
- identify best practices for its use.

---

# 2. The Map Interface

Unlike lists or queues, a map stores information as **key-value pairs**.

Conceptually:

```text
Key

↓

Value
```

Example:

```text
Username

↓

User Object
```

Each key uniquely identifies its associated value.

---

# 3. What Is HashMap?

`HashMap` is Java's standard implementation of the `Map` interface based on a **hash table**.

Declaration:

```java
Map<String, Integer> scores = new HashMap<>();
```

This implementation automatically performs:

- hashing;
- collision management;
- resizing;
- rehashing.

Developers use the collection without managing these details explicitly.

---

# 4. Basic Operations

The most common operations include:

| Operation | Description |
|-----------|-------------|
| `put(key, value)` | Inserts or updates an entry |
| `get(key)` | Retrieves a value |
| `remove(key)` | Removes an entry |
| `containsKey(key)` | Checks whether a key exists |
| `size()` | Returns the number of stored entries |
| `isEmpty()` | Determines whether the map is empty |

Example:

```text
Map<String, Integer> scores = new HashMap<>();

scores.put("Alice", 90);
scores.put("Bob", 85);

System.out.println(scores.get("Alice"));
```

---

# 5. Keys Must Be Unique

Each key may appear only once.

Example:

```text
scores.put("Alice", 90);
scores.put("Alice", 95);
```

The second insertion replaces the previous value.

The resulting map contains:

```text
Alice → 95
```

---

# 6. Internal Behaviour

Conceptually, a `HashMap` performs the following steps:

```text
Key

↓

hashCode()

↓

Bucket

↓

Locate Entry

↓

Retrieve or Insert Value
```

If multiple keys map to the same bucket, collision resolution mechanisms are applied automatically.

---

# 7. Complexity

When key hashes disperse properly among buckets:

| Operation | Average Complexity |
|-----------|-------------------:|
| `put()` | O(1) amortised; an individual growth is more expensive |
| `get()` | O(1) |
| `remove()` | O(1) |

In the worst case:

| Operation | Worst Complexity |
|-----------|-----------------:|
| Search | O(n) |
| Insert | O(n) |
| Remove | O(n) |

These simplified collision bounds assume constant key work and do not specify
every JDK internal path. Comparable keys may assist collision handling; no
universal logarithmic worst-case promise follows from this table. Iterating
HashMap views depends on capacity plus size, not size alone, and does not apply
as an iterator guarantee to the manual types, which expose none.

---

The JDK reference permits a null key and null values and does not promise
iteration order or thread safety. The selected manual tables reject both nulls
so a null result unambiguously means absence. Their small API is not the full
Map interface. JDK collision handling may use key comparison order; do not
present it as the same singly linked academic representation. See the official
[Java 21 HashMap specification](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html).

# 8. Best Practices

When using `HashMap`:

- use immutable objects as keys whenever possible;
- ensure that custom classes correctly implement `equals()` and `hashCode()`;
- choose meaningful key types;
- avoid unnecessary modifications to key objects after insertion.

These practices help preserve correctness and performance.

---

# 9. Common Applications

`HashMap` is widely used for:

- caches;
- lookup tables;
- dictionaries;
- configuration data;
- counting frequencies;
- indexing records;
- storing object relationships.

It is one of the most frequently used collections in backend development.

---

# 10. Relationship with Previous Concepts

`HashMap` combines all the concepts introduced in this module:

```text
Hash Function
        ↓
Bucket Selection
        ↓
Collision Resolution
        ↓
Load Factor Monitoring
        ↓
Automatic Resizing
        ↓
Average O(1) Operations
```

Understanding these principles makes it easier to reason about the performance and behaviour of real Java applications.

---

# 11. Key Takeaways

After completing this document, the learner should understand that:

- `HashMap` is Java's standard hash table implementation of the `Map` interface;
- data is stored as unique key-value pairs;
- hashing, collision resolution, and resizing are handled automatically;
- most operations execute in **O(1)** average time;
- correctly implementing `equals()` and `hashCode()` is essential when custom objects are used as keys;
- `HashMap` is one of the most important collections in the Java Collections Framework and a fundamental tool in modern software development.
