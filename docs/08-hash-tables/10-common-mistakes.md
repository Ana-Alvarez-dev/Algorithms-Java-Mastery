# Common Mistakes

## Algorithms Java Mastery

Hash tables are among the most efficient data structures for associative data access, but they are also one of the most frequently misunderstood. Many performance problems and software defects arise not from the data structure itself, but from incorrect assumptions about how hashing, collisions, and key comparison work.

Understanding these common mistakes helps developers design more reliable, maintainable, and efficient software systems.

---

# 1. Purpose

The purpose of this document is to:

- identify common mistakes when working with hash tables;
- understand why these mistakes occur;
- learn practical strategies for avoiding them;
- reinforce best engineering practices.

---

# 2. Assuming Hash Tables Never Have Collisions

A common misconception is that a good hash function completely eliminates collisions.

In reality:

```text
Different Keys

↓

Same Hash Bucket

↓

Collision
```

Collisions are **unavoidable** because a finite number of buckets must store a potentially unlimited number of keys.

Good hash functions reduce collisions—they do not eliminate them.

---

# 3. Ignoring the Load Factor

As more elements are inserted into a hash table, collisions become more frequent.

Ignoring the load factor may lead to:

- slower searches;
- longer collision chains;
- more probing operations;
- reduced overall performance.

Modern implementations automatically resize the table to keep the load factor within an acceptable range.

---

# 4. Using Poor Hash Functions

A poor hash function distributes keys unevenly.

Example:

```text
100 Keys

↓

Most Stored in Bucket 5
```

This creates unnecessary collisions and degrades performance toward linear time.

An effective hash function should distribute keys as uniformly as possible.

---

# 5. Forgetting equals() and hashCode()

When custom objects are used as keys in Java, both `equals()` and `hashCode()` must be implemented consistently.

Incorrect implementations may cause:

- duplicate keys;
- failed searches;
- unexpected retrieval results;
- incorrect map behaviour.

This is one of the most common programming errors involving `HashMap`.

---

# 6. Modifying Keys After Insertion

Keys should remain unchanged after being inserted into a hash table.

Incorrect approach:

```text
Insert Key

↓

Modify Key

↓

Search Fails
```

If the key's state changes, its hash value may also change, making the entry difficult or impossible to locate.

Immutable objects are therefore preferred as hash table keys.

---

# 7. Expecting Ordered Iteration

`HashMap` does **not** guarantee the order in which elements are stored or returned.

Incorrect assumption:

```text
Insert

A
B
C

↓

Iterate

A
B
C
```

The iteration order may differ and should never be relied upon.

When a predictable order is required, other implementations such as `LinkedHashMap` or `TreeMap` should be considered.

---

# 8. Assuming Constant Time in Every Situation

Although hash tables provide average **O(1)** performance, this guarantee does not apply to every case.

Performance depends on:

- the quality of the hash function;
- the number of collisions;
- the load factor;
- the implementation.

In the worst case, operations may require **O(n)** time.

---

# 9. Choosing a Hash Table for Every Problem

Hash tables are powerful, but they are not always the most appropriate data structure.

They are less suitable when:

- ordered traversal is required;
- range queries are needed;
- sorted data must be maintained;
- sequential processing is more important than fast lookup.

Selecting the appropriate data structure is an important part of algorithm design.

---

# 10. Engineering Best Practices

When using hash tables:

- choose high-quality key types;
- prefer immutable keys;
- implement `equals()` and `hashCode()` correctly;
- avoid depending on iteration order;
- understand the effect of the load factor;
- select the data structure that best matches the problem.

Following these practices improves both correctness and performance.

---

# 11. Key Takeaways

After completing this document, the learner should understand that:

- collisions are unavoidable and must be managed correctly;
- poor hash functions significantly reduce performance;
- the load factor has a direct impact on efficiency;
- immutable keys and consistent implementations of `equals()` and `hashCode()` are essential in Java;
- `HashMap` does not preserve insertion order;
- effective software engineering involves understanding both the strengths and the limitations of hash tables.