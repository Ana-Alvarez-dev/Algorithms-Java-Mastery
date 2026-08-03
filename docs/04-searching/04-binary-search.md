# Binary Search

## Purpose

Binary Search is one of the most efficient searching algorithms for ordered collections.

Unlike Linear Search, which examines each element sequentially, Binary Search repeatedly eliminates half of the remaining search space after each comparison.

This reduction allows the algorithm to locate an element using logarithmic time complexity, making it one of the fundamental algorithms in Computer Science.

However, this performance improvement comes at a cost.

Binary Search requires the input collection to satisfy a critical assumption:

> **The collection must be sorted according to the comparison criterion.**

Understanding both the power and the limitations of Binary Search is essential before studying more advanced data structures such as balanced trees, B-trees and hash tables.

---

# Learning Objectives

After completing this document, the learner should be able to:

- understand the Divide and Conquer strategy used by Binary Search;
- explain why Binary Search requires sorted data;
- analyse the reduction of the search space;
- reason about the correctness of the algorithm;
- analyse its computational complexity;
- implement Binary Search in Java.

---

# Overview

Binary Search locates an element by repeatedly dividing the search interval into two equal halves.

Instead of examining every element, the algorithm compares the search key with the middle element.

Depending on the comparison result, half of the remaining collection is immediately discarded.

Conceptually:

```text
Entire Collection

↓

Middle Element

↓

Target < Middle
        or
Target > Middle

↓

Discard Half

↓

Repeat
```

This process continues until the target is found or the search interval becomes empty.

---

# Fundamental Assumption

Binary Search is only correct if the collection is sorted.

Example:

```text
[3][8][14][19][27][35][42]
```

Because the elements are ordered, comparing the middle element immediately reveals which half cannot contain the target.

Without ordering, this reasoning becomes impossible.

---

# Divide and Conquer Strategy

Binary Search follows the Divide and Conquer paradigm.

The algorithm repeatedly performs three steps.

## Divide

Select the middle element.

```text
[3][8][14][19][27][35][42]

          ↑
        Middle
```

---

## Decide

Compare the search key with the middle element.

Possible outcomes:

```text
Key == Middle
```

Target found.

---

```text
Key < Middle
```

Continue searching the left half.

---

```text
Key > Middle
```

Continue searching the right half.

---

## Repeat

The same process is applied to the remaining half.

Each iteration reduces the search space by approximately 50%.

---

# Search Space Reduction

One of the most important characteristics of Binary Search is the rapid reduction of the search interval.

Example:

```text
1024 Elements

↓

512

↓

256

↓

128

↓

64

↓

32

↓

16

↓

8

↓

4

↓

2

↓

1
```

Only ten iterations are required to search more than one thousand elements.

---

# Pseudocode

```text
BINARY-SEARCH(A, key)

low ← first index

high ← last index

while low ≤ high

    middle ← (low + high) / 2

    if A[middle] == key

        return middle

    if key < A[middle]

        high ← middle − 1

    else

        low ← middle + 1

return NOT FOUND
```

---

# Example

Collection:

```text
[5][11][19][26][34][48][53][67][81]
```

Search key:

```text
48
```

Step 1

```text
Middle = 34
```

48 is greater.

Discard left half.

---

Remaining:

```text
[48][53][67][81]
```

Middle:

```text
53
```

48 is smaller.

Discard right half.

---

Remaining:

```text
48
```

Target found.

---

# Correctness

Binary Search is correct because every comparison preserves all possible locations where the target may exist.

If the search key is smaller than the middle element,

every element on the right side is necessarily larger,

therefore none of them can contain the target.

The same reasoning applies symmetrically to the left side.

The search interval becomes progressively smaller until:

- the target is found; or
- no possible positions remain.

---

# Computational Complexity

## Best Case

The middle element is the target.

```text
O(1)
```

Only one comparison.

---

## Worst Case

The search interval is repeatedly halved.

```text
O(log n)
```

---

## Average Case

Binary Search also exhibits logarithmic behaviour.

```text
O(log n)
```

---

# Space Complexity

The iterative implementation requires only a few variables.

```text
O(1)
```

The recursive version will be analysed separately.

---

# Advantages

Binary Search provides significant benefits.

- Very efficient.
- Excellent scalability.
- Few comparisons.
- Constant auxiliary memory (iterative version).
- Suitable for large collections.

---

# Limitations

Binary Search also has important limitations.

- Requires sorted data.
- Random access is necessary.
- Less suitable for linked lists.
- Maintaining sorted collections may be expensive.

---

# Typical Applications

Binary Search is widely used in:

- database indexes;
- operating systems;
- search libraries;
- compiler optimisation;
- version control systems;
- standard libraries;
- numerical methods.

---

# Binary Search versus Linear Search

| Property | Linear Search | Binary Search |
|-----------|--------------|---------------|
| Sorted Data Required | No | Yes |
| Best Case | O(1) | O(1) |
| Average Case | O(n) | O(log n) |
| Worst Case | O(n) | O(log n) |
| Space Complexity | O(1) | O(1) |
| Suitable for Unsorted Data | Yes | No |

---

# Java Engineering Considerations

When implementing Binary Search in Java, developers should:

- avoid integer overflow when computing the middle index;
- validate empty collections;
- clearly document preconditions;
- stop immediately when the target is found;
- consider using generic implementations where appropriate.

A safer midpoint calculation is:

```java
int middle = low + (high - low) / 2;
```

This expression prevents integer overflow when working with very large arrays.

---

# Relationship with Previous Modules

Binary Search integrates concepts from several previous modules.

```text
Foundations
        ↓
Problem Solving

Complexity
        ↓
Logarithmic Growth

Arrays
        ↓
Random Access

Searching
        ↓
Binary Search
```

---

# Relationship with Future Modules

Binary Search introduces ideas that appear repeatedly in Computer Science.

```text
Binary Search
        ↓
Divide and Conquer

↓

Merge Sort

↓

Balanced Trees

↓

B-Trees

↓

Database Indexes
```

Understanding Binary Search makes many advanced algorithms significantly easier to learn.

---

# Key Takeaways

Binary Search demonstrates how exploiting the structure of the data can dramatically improve algorithmic efficiency.

By repeatedly halving the search space, the algorithm achieves logarithmic performance while requiring only constant auxiliary memory in its iterative implementation.

Its effectiveness depends entirely on maintaining the ordering of the collection, making it an excellent example of the trade-off between preprocessing and query performance.

---

# Next Document

```
05-recursive-binary-search.md
```

The next document studies the recursive implementation of Binary Search and explores how recursion naturally expresses the Divide and Conquer strategy while introducing additional considerations regarding stack usage and recursive correctness.