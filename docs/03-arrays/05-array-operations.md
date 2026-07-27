# Array Operations

## Algorithms Java Mastery

This document studies the fundamental operations that can be performed on arrays.

Every algorithm that manipulates arrays is ultimately composed of one or more of these basic operations. Understanding their behaviour, implementation and computational complexity is therefore essential before studying searching algorithms, sorting algorithms and more advanced data structures.

The central question addressed throughout this document is:

> **What operations can be performed on an array, and what are their computational costs?**

---

# Purpose

The purpose of this document is to introduce the core operations supported by arrays and analyse their behaviour from an algorithmic perspective.

Rather than memorising complexity values, the learner should understand **why** each operation has its corresponding computational cost.

The expected progression is:

```text
Array
        ↓
Basic Operations
        ↓
Implementation
        ↓
Complexity Analysis
        ↓
Engineering Decisions
```

Understanding these operations establishes the foundation for all later algorithmic modules.

---

# Learning Objectives

After studying this document, the learner should be able to:

- identify the fundamental operations performed on arrays;
- implement these operations correctly in Java;
- analyse their computational complexity;
- distinguish efficient from inefficient operations;
- justify the use of arrays according to the problem requirements.

---

# Fundamental Operations

The most common operations performed on arrays are:

```text
Access
        ↓
Traversal
        ↓
Search
        ↓
Update
        ↓
Insertion
        ↓
Deletion
        ↓
Copy
        ↓
Reverse
        ↓
Rotation
```

Every algorithm studied later in this repository combines one or more of these operations.

---

# Access

## Definition

Access retrieves the value stored at a specific index.

Example:

```java
int value = numbers[3];
```

Conceptually:

```text
Index

0 1 2 3 4

↓

[8][4][7][9][2]

         ↑
      Access
```

---

## Complexity

Time Complexity

```text
O(1)
```

Space Complexity

```text
O(1)
```

---

## Why?

The processor calculates the memory address directly from the index.

No traversal is required.

---

# Traversal

## Definition

Traversal visits every element of the array.

Example:

```text
for (int value : numbers) {
    System.out.println(value);
}
```

Conceptually:

```text
↓

↓

↓

↓

↓

[8][4][7][9][2]
```

---

## Complexity

Time Complexity

```text
O(n)
```

Space Complexity

```text
O(1)
```

---

## Why?

Every element must be processed exactly once.

---

# Search

## Definition

Searching determines whether an element exists in the array.

Example:

```java
int target = 7;
```

Conceptually:

```text
[8][4][7][9][2]

        ↑
```

---

## Complexity

Linear Search

```text
O(n)
```

Binary Search (sorted arrays)

```text
O(log n)
```

---

## Engineering Observation

The algorithm chosen depends on whether the array is already sorted.

---

# Update

## Definition

Updating replaces the value stored at a specific index.

Example:

```text
numbers[2] = 50;
```

Conceptually:

```text
Before

[8][4][7][9][2]

↓

After

[8][4][50][9][2]
```

---

## Complexity

Time Complexity

```text
O(1)
```

Only one element is modified.

---

# Insertion

## Definition

Insertion adds a new element into the array.

Example:

```text
Before

[10][20][40][50]

↓

Insert 30

↓

[10][20][30][40][50]
```

---

## What Happens?

Elements located after the insertion point must be shifted.

Conceptually:

```text
↓

↓

↓

[40]

↓

[50]
```

---

## Complexity

Worst Case

```text
O(n)
```

---

## Engineering Interpretation

Insertion near the beginning of the array is expensive because many elements may need to be moved.

---

# Deletion

## Definition

Deletion removes an element from the array.

Example:

```text
Before

[10][20][30][40][50]

↓

Delete 20

↓

[10][30][40][50]
```

---

## What Happens?

Remaining elements must shift to fill the gap.

---

## Complexity

Worst Case

```text
O(n)
```

---

## Engineering Interpretation

Deletion is expensive for the same reason as insertion.

---

# Copy

## Definition

Copying creates another array containing the same elements.

Example:

```java
int[] copy = Arrays.copyOf(numbers, numbers.length);
```

---

## Complexity

Time Complexity

```text
O(n)
```

Every element must be copied.

---

# Reverse

## Definition

Reverse changes the order of the elements.

Example:

```text
Before

[1][2][3][4][5]

↓

After

[5][4][3][2][1]
```

---

## Complexity

Time Complexity

```text
O(n)
```

Only half of the elements need to be swapped.

---

# Rotation

## Definition

Rotation moves every element by a fixed number of positions.

Example:

```text
Before

[1][2][3][4][5]

↓

Rotate Right

↓

[5][1][2][3][4]
```

---

## Complexity

Typical implementation

```text
O(n)
```

---

# Summary of Operations

| Operation | Time | Space |
|-----------|------|--------|
| Access | O(1) | O(1) |
| Update | O(1) | O(1) |
| Traversal | O(n) | O(1) |
| Search (Linear) | O(n) | O(1) |
| Search (Binary) | O(log n) | O(1) |
| Copy | O(n) | O(n) |
| Reverse | O(n) | O(1) |
| Rotate | O(n) | O(1) |
| Insert | O(n) | O(1) |
| Delete | O(n) | O(1) |

---

# Relationship with Computational Complexity

The complexity values above are direct consequences of the array memory layout studied previously.

For example:

- Access is O(1) because the memory address is calculated directly.
- Traversal is O(n) because every element is visited.
- Insertion and deletion are O(n) because elements must be shifted.

Understanding these relationships is more important than memorising the complexity table.

---

# Relationship with Future Modules

The operations introduced here will appear repeatedly throughout the repository.

Examples include:

```text
Searching
        ↓
Search

Sorting
        ↓
Swap
        ↓
Traversal

Hash Tables
        ↓
Access

Heaps
        ↓
Update
        ↓
Swap

Dynamic Programming
        ↓
Traversal
```

These operations form the building blocks of more sophisticated algorithms.

---

# Common Mistakes

Avoid the following misconceptions.

---

## Assuming Every Operation Has Constant Time

Only direct access and updates execute in constant time.

Traversal, insertion and deletion require processing multiple elements.

---

## Ignoring Element Shifting

Insertion and deletion often require moving many elements.

This movement determines their computational complexity.

---

## Choosing Arrays for Every Problem

Arrays are efficient for indexed access but less efficient when frequent insertions and deletions are required.

Selecting the appropriate data structure is an engineering decision.

---

# Best Practices

When designing algorithms based on arrays:

- exploit constant-time indexed access;
- minimise unnecessary traversals;
- avoid repeated insertions near the beginning of the array;
- analyse both time and space complexity;
- justify the selected operations according to the computational requirements.

---

# Key Takeaways

The learner should remember the following principles:

- Every algorithm manipulates data through a small set of fundamental operations.
- Access and update execute in O(1).
- Traversal requires O(n).
- Linear search requires O(n).
- Binary search requires O(log n) on sorted arrays.
- Insertion and deletion generally require O(n).
- Copying and reversing require O(n).
- Understanding the cost of operations is essential for selecting appropriate algorithms and data structures.

Mastering these operations provides the practical foundation required for studying searching algorithms, sorting algorithms and more advanced data structures throughout the **Algorithms Java Mastery** repository.