# Hash Tables

## Algorithms Java Mastery

Hash tables are one of the most important data structures in modern computer science. They provide efficient access to data by transforming keys into array indices through a hash function, allowing search, insertion, and deletion operations to execute in constant average time.

Unlike linear structures such as arrays, stacks, or queues, hash tables are designed for **fast associative access**. Their performance depends on the quality of the hash function, the strategy used to resolve collisions, and the management of the table's load factor.

Hash tables are fundamental to software engineering and appear throughout programming languages, databases, operating systems, compilers, distributed systems, caching mechanisms, and high-performance applications. In Java, they form the foundation of some of the most frequently used collections, including `HashMap` and `HashSet`.

This module studies hash tables from an academic and engineering perspective. It introduces the theoretical principles of hashing, explains how collisions occur, compares the principal collision resolution strategies, analyses computational complexity, and relates these concepts to their implementation in the Java Collections Framework.

Following the philosophy of **Algorithms Java Mastery**, the objective is not only to learn how hash tables work, but also to understand why they achieve their performance characteristics and when they should be selected over other data structures.

---

# Module Learning Path

The recommended study order is:

```text
Hashing Fundamentals
        ↓
Hash Functions
        ↓
Collisions
        ↓
Separate Chaining
        ↓
Open Addressing
        ↓
Load Factor and Resizing
        ↓
Java HashMap
        ↓
Complexity Analysis
        ↓
Common Algorithms
        ↓
Common Mistakes
```

Each document develops one fundamental concept before introducing the next, allowing the learner to progressively understand the complete design of hash tables.

---

# Learning Objectives

After completing this module, the learner should be able to:

- explain the purpose of hash tables and associative data structures;
- understand how hash functions map keys to table indices;
- distinguish between good and poor hash functions;
- explain why collisions occur and how they affect performance;
- compare separate chaining and open addressing;
- understand the role of the load factor and dynamic resizing;
- analyse the average and worst-case complexity of hash table operations;
- understand how `HashMap` is implemented and used in Java;
- identify common algorithmic problems solved using hash tables;
- recognise common implementation mistakes and performance pitfalls.

---

# Module Contents

| Document | Description |
|----------|-------------|
| **01-hashing-fundamentals.md** | Introduces the concept of hashing, associative access, and the motivation behind hash tables. |
| **02-hash-functions.md** | Explains how hash functions distribute keys and the characteristics of effective hashing algorithms. |
| **03-collisions.md** | Describes why collisions occur and why collision resolution strategies are necessary. |
| **04-separate-chaining.md** | Presents collision resolution through linked structures and analyses its advantages and limitations. |
| **05-open-addressing.md** | Explains collision resolution by probing within the table, including the most common probing techniques. |
| **06-load-factor-and-resizing.md** | Examines how load factor influences performance and how dynamic resizing preserves efficiency. |
| **07-java-hashmap.md** | Introduces the Java `HashMap` implementation and its relationship with the theoretical concepts studied in this module. |
| **08-complexity-analysis.md** | Analyses the time and space complexity of hash table operations under different scenarios. |
| **09-common-algorithms.md** | Presents representative algorithms and problem patterns that rely on hash tables. |
| **10-common-mistakes.md** | Reviews common implementation errors, incorrect assumptions, and best practices when working with hash tables. |

---

# Prerequisites

Before studying this module, the learner should be familiar with:

- Arrays
- Computational Complexity
- Linked Lists
- Stacks and Queues

These topics provide the foundation necessary to understand hashing mechanisms and collision resolution strategies.

---

# Expected Outcomes

Upon completing this module, the learner will understand:

- why hash tables provide efficient associative access;
- how the choice of a hash function influences performance;
- how different collision resolution strategies operate;
- why the load factor determines the efficiency of a hash table;
- how Java's `HashMap` applies these concepts in practice;
- how to determine whether a hash table is the appropriate data structure for a given problem.

This knowledge establishes the foundation for studying more advanced data structures and designing high-performance software systems that require efficient data retrieval.