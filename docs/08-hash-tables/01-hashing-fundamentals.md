# Hashing Fundamentals

## Algorithms Java Mastery

Hash tables are designed to solve one of the most common problems in computer science: **finding information efficiently**. While linear data structures such as arrays and linked lists often require sequential traversal to locate an element, hash tables use a different strategy. Instead of searching through the stored data, they compute where the data should be located.

This idea, known as **hashing**, transforms a key into an index within an array, allowing data to be accessed directly in most situations. When the hashing process distributes keys uniformly, search, insertion, and deletion operations can be performed in constant average time.

Understanding hashing begins with distinguishing between the **abstract concept of associative access** and the concrete implementation of a hash table.

---

# 1. Purpose

The purpose of this document is to:

- introduce the concept of hashing;
- explain why associative access is useful;
- understand the relationship between keys, hash functions, and tables;
- establish the theoretical foundation for hash tables.

---

# 2. What Is Hashing?

**Hashing** is a technique that transforms a piece of information, called a **key**, into an integer value that identifies where the associated data should be stored.

Conceptually:

```text
Key
 ↓
Hash Function
 ↓
Array Index
 ↓
Stored Value
```

Instead of searching for an element sequentially, the system computes the location where the element is expected to be found.

---

# 3. Why Is Hashing Needed?

Consider searching for a student's record.

Using a linear structure:

```text
Student A

↓

Student B

↓

Student C

↓

Student D
```

The desired record may only be found after examining many elements.

With hashing:

```text
Student ID

↓

Hash Function

↓

Index 42

↓

Student Record
```

The location is computed directly, eliminating the need for sequential traversal in the average case.

---

# 4. Associative Access

Unlike arrays, which are accessed by numerical positions, hash tables allow data to be accessed through **keys**.

For example:

```text
"alice"

↓

Hash Function

↓

Index 15
```

or

```text
Employee ID

↓

Hash Function

↓

Stored Employee
```

The key identifies the data rather than its physical position.

---

# 5. Fundamental Components

Every hash table is composed of three essential elements.

## Key

The value used to identify an element.

Examples:

- username;
- product code;
- employee ID;
- email address.

---

## Hash Function

The algorithm that transforms the key into an array index.

Conceptually:

```text
Key

↓

Hash Function

↓

Index
```

A good hash function distributes keys as uniformly as possible.

---

## Table

The internal array where elements are stored.

Example:

```text
Index

0

1

2

3

4

5
```

Each computed index represents a possible storage location.

---

# 6. Conceptual Model

The complete process can be represented as:

```text
Key

↓

Hash Function

↓

Array Index

↓

Stored Element
```

Retrieving an element follows the same sequence.

---

# 7. Advantages of Hashing

Hashing offers several important advantages:

- fast average search;
- efficient insertion;
- efficient deletion;
- direct access through keys;
- scalability for large collections.

These characteristics explain why hash tables are widely used in modern software systems.

---

# 8. Limitations

Hashing is not perfect.

Several factors influence its performance:

- collisions;
- poor hash functions;
- high load factors;
- inefficient collision resolution strategies.

These topics are explored in the following documents of this module.

---

# 9. Applications

Hash tables appear in many areas of software engineering, including:

- dictionaries and maps;
- database indexing;
- caches;
- symbol tables in compilers;
- authentication systems;
- routing tables;
- distributed systems.

In Java, the most common implementation is **HashMap**, which is studied later in this module.

---

# 10. Relationship with Previous Modules

Hash tables build upon concepts introduced earlier in the repository.

- **Arrays** provide the underlying storage.
- **Linked Lists** are commonly used for separate chaining.
- **Complexity Analysis** explains why average-case performance is typically **O(1)**.
- **Stacks and Queues** demonstrate linear access patterns, whereas hash tables provide associative access.

Together, these concepts illustrate different strategies for organizing and retrieving data efficiently.

---

# 11. Key Takeaways

After completing this document, the learner should understand that:

- hashing transforms keys into array indices;
- hash tables provide associative rather than sequential access;
- a hash table consists of keys, a hash function, and an underlying array;
- efficient hashing depends on distributing keys uniformly;
- hashing enables average constant-time search, insertion, and deletion;
- understanding hashing fundamentals is essential before studying hash functions and collision resolution techniques.