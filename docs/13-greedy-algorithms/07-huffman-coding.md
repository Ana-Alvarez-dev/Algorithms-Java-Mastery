# Huffman Coding

## Algorithms Java Mastery

**Huffman Coding** is one of the most important applications of the **Greedy algorithm design paradigm**. Developed by David A. Huffman in 1952, the algorithm constructs an **optimal prefix code** for lossless data compression.

The fundamental idea is simple: symbols that appear more frequently should receive shorter binary codes, while less frequent symbols receive longer codes. This minimizes the average number of bits required to represent the data.

Unlike the scheduling problems studied previously, Huffman Coding demonstrates that Greedy algorithms can also optimize **tree structures**. By repeatedly combining the two least frequent symbols, the algorithm constructs an optimal binary tree that minimizes the total encoding cost.

Huffman Coding remains a fundamental algorithm in Computer Science and forms the basis of numerous compression techniques used in modern software systems.

---

# 1. Purpose

The purpose of this document is to:

- introduce Huffman Coding;
- explain its Greedy strategy;
- understand prefix codes;
- analyze the Huffman tree construction process;
- evaluate computational complexity;
- explore practical applications.

---

# 2. Problem Statement

Given:

- a collection of symbols;
- the frequency of each symbol.

Goal:

```text
Construct
An Optimal
Binary Encoding
```

The objective is to minimize the total number of bits required to encode the complete dataset.

---

# 3. Motivation

Suppose a text contains:

```text
A → 45%

B → 30%

C → 15%

D → 10%
```

Using fixed-length binary codes:

```text
Every Symbol

↓

Same Number of Bits
```

Using Huffman Coding:

```text
Frequent Symbols

↓

Short Codes
```

```text
Rare Symbols

↓

Long Codes
```

This significantly reduces the average encoding length.

---

# 4. Prefix Codes

Huffman Coding produces a **prefix code**.

A prefix code satisfies:

```text
No Code
Is A Prefix
Of Another
```

Example:

Valid:

```text
A → 0

B → 10

C → 110

D → 111
```

Invalid:

```text
A → 0

B → 01
```

because the code for **A** is a prefix of the code for **B**.

Prefix codes allow unique decoding without ambiguity.

---

# 5. Greedy Strategy

The Huffman algorithm repeatedly selects:

```text
The Two
Lowest
Frequencies
```

These two symbols become children of a new internal node.

Their frequencies are added together, and the new node is inserted back into the collection.

This process continues until only one tree remains.

---

# 6. Building the Huffman Tree

Conceptually:

```text
Leaf Nodes

↓

Choose Two
Smallest

↓

Merge

↓

Insert New Node

↓

Repeat

↓

Single Root
```

The final binary tree defines the encoding.

---

# 7. Why the Greedy Strategy Works

The two least frequent symbols contribute the least to the total encoding cost.

Placing them deeper in the tree minimizes the weighted path length.

Conceptually:

```text
Lowest Frequencies

↓

Deepest Leaves

↓

Minimal Total Cost
```

This decision can be proven to be optimal.

---

# 8. Greedy Choice Property

Huffman Coding satisfies the **Greedy Choice Property**.

Choosing the two least frequent symbols is always a **safe choice**.

Once merged:

```text
Smallest Frequencies

↓

Safe Merge

↓

Optimal Tree
```

The decision never prevents the construction of an optimal solution.

---

# 9. Optimal Substructure

After merging two symbols, the remaining problem is simply a smaller Huffman Coding problem.

Conceptually:

```text
Original Problem

↓

Merge Two Nodes

↓

Smaller Problem

↓

Optimal Tree
```

Therefore, Huffman Coding also exhibits **Optimal Substructure**.

---

# 10. Data Structure

The algorithm repeatedly removes the two smallest elements.

The most suitable data structure is:

```text
Priority Queue
```

or

```text
Min Heap
```

These structures efficiently maintain the smallest frequencies.

---

# 11. Algorithm Overview

The Huffman algorithm follows these steps.

```text
Create Leaf Nodes
        ↓
Insert Into
Priority Queue
        ↓
Remove Two
Smallest Nodes
        ↓
Merge Nodes
        ↓
Insert New Node
        ↓
Repeat
        ↓
Construct Huffman Tree
```

---

# 12. Correctness Intuition

The correctness proof is based on the **Exchange Argument**.

Conceptually:

```text
Optimal Tree

↓

Exchange
Lowest
Frequencies

↓

Still Optimal
```

This demonstrates that the Greedy decision preserves optimality.

---

# 13. Complexity Analysis

Let **n** be the number of distinct symbols.

Building the Priority Queue:

```text
O(n)
```

Each merge operation:

```text
O(log n)
```

Total merges:

```text
n − 1
```

Overall complexity:

```text
O(n log n)
```

---

# 14. Space Complexity

The algorithm stores:

- leaf nodes;
- internal nodes;
- priority queue.

Auxiliary space:

```text
O(n)
```

---

# 15. Advantages

Huffman Coding provides:

- optimal prefix codes;
- efficient compression;
- lossless encoding;
- relatively simple implementation;
- excellent practical performance.

It remains one of the most influential compression algorithms.

---

# 16. Limitations

Huffman Coding also has limitations.

It requires:

- frequency analysis before encoding;
- additional memory for the Huffman tree;
- tree reconstruction during decoding.

Furthermore, it performs best when symbol frequencies are uneven.

---

# 17. Practical Applications

Huffman Coding is widely used in:

- ZIP compression;
- GZIP;
- PNG image compression;
- JPEG (entropy coding stage);
- PDF compression;
- file archiving;
- communication protocols.

Many modern compression systems incorporate Huffman Coding as one component of a larger compression pipeline.

---

# 18. Java Perspective

A Java implementation commonly uses:

- `PriorityQueue`;
- tree node classes;
- binary trees;
- recursive traversal to generate binary codes;
- maps for storing symbol frequencies.

The `PriorityQueue` automatically provides efficient access to the smallest frequencies throughout the algorithm.

---

# 19. Engineering Perspective

Huffman Coding demonstrates that the Greedy paradigm extends far beyond scheduling problems.

Instead of selecting activities or intervals, the algorithm incrementally constructs an optimal binary tree.

```text
Count Frequencies
        ↓
Build Priority Queue
        ↓
Repeatedly Merge
Smallest Nodes
        ↓
Construct Tree
        ↓
Generate Prefix Codes
```

This example highlights how mathematical optimization and efficient data structures combine to solve practical engineering problems.

---

# 20. Relationship with the Next Document

This document completed the study of the principal Greedy algorithm case studies.

The next document, **Complexity Analysis**, compares the computational characteristics of the algorithms studied throughout this module, examining their time complexity, auxiliary space, scalability, and engineering trade-offs.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- Huffman Coding is a classical Greedy algorithm for lossless data compression;
- the algorithm constructs an optimal prefix code;
- the Greedy strategy repeatedly merges the two least frequent symbols;
- Huffman Coding satisfies both the Greedy Choice Property and Optimal Substructure;
- a Priority Queue (Min Heap) enables efficient implementation;
- the algorithm runs in **O(n log n)** time;
- Huffman Coding remains one of the most important applications of Greedy algorithms in modern software engineering;
- this case study demonstrates that Greedy optimization applies not only to scheduling problems but also to binary tree construction and information encoding.