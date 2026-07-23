# Linear Search

## Purpose

Linear Search, also known as **Sequential Search**, is the simplest searching algorithm.

It examines each element of a collection one at a time until either the target element is found or every element has been inspected.

Although Linear Search is not the most efficient searching algorithm for large collections, it has an important advantage: **it requires no assumptions about the organisation of the data**.

For this reason, Linear Search serves as the foundation upon which more sophisticated searching algorithms are studied.

This document analyses Linear Search from both an algorithmic and software engineering perspective.

---

# Learning Objectives

After completing this document, the learner should be able to:

- understand the strategy used by Linear Search;
- explain why the algorithm is correct;
- analyse its computational complexity;
- identify situations in which Linear Search is appropriate;
- implement the algorithm in Java;
- validate its behaviour through automated tests.

---

# Overview

Linear Search scans the collection sequentially from the first element to the last.

Each element is compared with the search key.

If a match is found, the algorithm immediately terminates.

If the end of the collection is reached without finding the target, the search fails.

Conceptually:

```text
Collection

[12][25][31][44][58][73]

Search Key

44

↓

Compare 12

↓

Compare 25

↓

Compare 31

↓

Compare 44

↓

Found
```

---

# Algorithm Strategy

The strategy is straightforward.

1. Start at the first element.
2. Compare the current element with the search key.
3. If they are equal, terminate successfully.
4. Otherwise, continue to the next element.
5. Repeat until the collection ends.

If every element has been inspected without finding the target, report that the search was unsuccessful.

---

# Preconditions

Linear Search requires very few assumptions.

The collection:

- may be sorted or unsorted;
- may contain duplicate values;
- may contain any comparable elements.

No preprocessing is required.

---

# Postconditions

After execution:

If the target exists,

the algorithm returns its position (or another application-specific result).

Otherwise,

the algorithm reports that the element was not found.

---

# Pseudocode

```text
LINEAR-SEARCH(A, key)

for each element in A

    if current element equals key

        return its position

return NOT FOUND
```

The algorithm terminates as soon as the desired element is located.

---

# Example

Collection:

```text
[18][11][35][29][42]
```

Search key:

```text
29
```

Execution:

```text
Compare 18

↓

No Match

↓

Compare 11

↓

No Match

↓

Compare 35

↓

No Match

↓

Compare 29

↓

Match

↓

Return index 3
```

---

# Unsuccessful Search

Collection:

```text
[18][11][35][29][42]
```

Search key:

```text
50
```

Execution:

```text
18

↓

11

↓

35

↓

29

↓

42

↓

Not Found
```

Every element must be inspected.

---

# Correctness

Linear Search is correct because it systematically compares the search key against every element in the collection.

If the target exists,

it must eventually be examined.

If every element has been inspected and no match has been found,

the target cannot exist within the collection.

Therefore, the algorithm always produces the correct result.

---

# Computational Complexity

The running time depends on the position of the target.

## Best Case

The target is the first element.

```text
O(1)
```

Only one comparison is required.

---

## Worst Case

The target is the final element,

or does not exist.

```text
O(n)
```

Every element must be inspected.

---

## Average Case

On average,

approximately half of the collection is examined.

```text
O(n)
```

Linear growth remains the dominant behaviour.

---

# Space Complexity

Linear Search uses only a few local variables.

Therefore,

its auxiliary memory consumption is:

```text
O(1)
```

---

# Advantages

Linear Search offers several benefits.

- Extremely simple.
- Easy to implement.
- Works on unsorted collections.
- Requires no preprocessing.
- Constant auxiliary memory.
- Suitable for small collections.

---

# Limitations

Despite its simplicity,

Linear Search becomes inefficient as collections grow.

Its main disadvantages include:

- poor scalability;
- linear running time;
- repeated comparisons.

For large datasets,

more efficient algorithms should be preferred.

---

# Typical Applications

Linear Search is appropriate when:

- the collection is very small;
- the data is unsorted;
- the collection changes frequently;
- preprocessing would be more expensive than searching.

Examples include:

- configuration lists;
- small lookup tables;
- command-line utilities;
- educational implementations.

---

# Java Engineering Considerations

In Java,

Linear Search is commonly implemented using:

- arrays;
- `ArrayList`;
- generic collections;
- custom object collections.

Good implementations should:

- avoid unnecessary comparisons;
- terminate immediately after finding the target;
- handle empty collections safely;
- document behaviour clearly.

---

# Relationship with Previous Modules

Linear Search combines concepts studied earlier.

```text
Foundations
        ↓
Algorithm Design

Complexity
        ↓
Big-O Analysis

Arrays
        ↓
Sequential Traversal

Searching
        ↓
Linear Search
```

Without sequential traversal,

Linear Search would not be possible.

---

# Relationship with Future Modules

Linear Search serves as the baseline against which more efficient searching algorithms are evaluated.

```text
Linear Search

↓

Binary Search

↓

Hash Tables

↓

Balanced Trees
```

Each subsequent technique improves search efficiency under additional assumptions.

---

# Key Takeaways

Linear Search is the simplest searching algorithm and requires no assumptions about the organisation of the data.

Although its running time grows linearly with the size of the collection, its simplicity, flexibility and constant memory usage make it an essential algorithm for understanding searching techniques.

Mastering Linear Search provides the conceptual foundation required for studying Binary Search and more advanced search structures.

---

# Next Document

```
04-binary-search.md
```

The next document introduces **Binary Search**, an algorithm that significantly improves search performance by repeatedly dividing a sorted collection into smaller search spaces.