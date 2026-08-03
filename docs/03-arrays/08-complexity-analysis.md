# Complexity Analysis of Arrays

## Algorithms Java Mastery

This document analyses the computational complexity of the fundamental operations performed on arrays.

The previous module introduced the theoretical principles of computational complexity. This document applies those principles specifically to arrays, demonstrating how their internal organisation determines the cost of each operation.

Rather than memorising complexity values, software engineers should understand the relationship between **memory layout**, **algorithm design** and **performance**.

The central question addressed throughout this document is:

> **Why does each array operation have its corresponding computational complexity?**

---

# Purpose

The purpose of this document is to analyse the computational cost of array operations using the asymptotic analysis methodology introduced in the previous module.

The learner should understand not only the complexity values but also the reasons behind them.

The expected progression is:

```text
Array Structure
        ↓
Operation
        ↓
Execution Process
        ↓
Time Complexity
        ↓
Space Complexity
        ↓
Engineering Interpretation
```

---

# Learning Objectives

After studying this document, the learner should be able to:

- analyse the computational complexity of array operations;
- explain why each operation exhibits its complexity;
- distinguish efficient and inefficient operations;
- evaluate scalability;
- justify engineering decisions using complexity analysis.

---

# Why Complexity Depends on Memory Layout

Arrays store their elements in contiguous memory locations.

Because every element occupies a predictable position, the processor can calculate its address directly.

Conceptually:

```text
Base Address
        +
(Index × Element Size)
        ↓
Memory Address
```

This simple calculation explains many of the complexity values studied throughout this document.

---

# Complexity Classification

Array operations can be divided into three categories.

```text
Constant Time
        ↓
O(1)

Linear Time
        ↓
O(n)

Logarithmic Time
        ↓
O(log n)
```

Each category corresponds to a different growth rate as the input size increases.

---

# Access by Index

## Operation

Retrieve the value stored at a given index.

Example:

```java
int value = numbers[5];
```

### Execution Process

The processor calculates the address directly.

No previous elements are inspected.

### Time Complexity

```text
O(1)
```

### Space Complexity

```text
O(1)
```

### Engineering Interpretation

Random access is one of the greatest strengths of arrays.

---

# Update

## Operation

Replace the value stored at a given position.

Example:

```text
numbers[2] = 80;
```

### Time Complexity

```text
O(1)
```

### Space Complexity

```text
O(1)
```

### Why?

The element has already been located.

Only one memory position changes.

---

# Traversal

## Operation

Visit every element.

Example:

```text
for (int value : numbers) {
    System.out.println(value);
}
```

### Execution Process

Each element is processed once.

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

### Engineering Interpretation

Traversal cost grows proportionally to the input size.

---

# Linear Search

## Operation

Search for an element in an unsorted array.

### Execution Process

Elements are examined sequentially.

### Best Case

```text
O(1)
```

The first element matches.

### Average Case

```text
O(n)
```

Approximately half the array is examined.

### Worst Case

```text
O(n)
```

Every element must be inspected.

---

# Binary Search

Binary Search requires a sorted array.

At every step the search space is divided in half.

### Best Case

```text
O(1)
```

### Average Case

```text
O(log n)
```

### Worst Case

```text
O(log n)
```

Binary Search illustrates how algorithm design can dramatically improve performance without changing the underlying data structure.

---

# Insertion

## Operation

Insert a new element.

Conceptually:

```text
Before

[10][20][40][50]

↓

Insert 30

↓

[10][20][30][40][50]
```

### Execution Process

Every element after the insertion point shifts one position.

### Worst Case

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

# Deletion

## Operation

Remove an element.

Conceptually:

```text
Before

[10][20][30][40][50]

↓

Delete 20

↓

[10][30][40][50]
```

### Execution Process

Remaining elements move to fill the gap.

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

# Copying an Array

Every element must be duplicated.

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(n)
```

The additional memory corresponds to the newly created array.

---

# Reversing an Array

Half of the elements are exchanged.

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

---

# Rotating an Array

Each element changes position.

Typical implementations require:

### Time Complexity

```text
O(n)
```

### Space Complexity

```text
O(1)
```

or

```text
O(n)
```

depending on the chosen algorithm.

---

# Comparative Complexity Table

| Operation | Best | Average | Worst | Space |
|-----------|------|---------|--------|--------|
| Access | O(1) | O(1) | O(1) | O(1) |
| Update | O(1) | O(1) | O(1) | O(1) |
| Traversal | O(n) | O(n) | O(n) | O(1) |
| Linear Search | O(1) | O(n) | O(n) | O(1) |
| Binary Search | O(1) | O(log n) | O(log n) | O(1) |
| Insert | O(1)* | O(n) | O(n) | O(1) |
| Delete | O(1)* | O(n) | O(n) | O(1) |
| Reverse | O(n) | O(n) | O(n) | O(1) |
| Copy | O(n) | O(n) | O(n) | O(n) |
| Rotate | O(n) | O(n) | O(n) | O(1) |

\*Insertion and deletion are constant time only when performed at the logical end of an array with available capacity. In standard Java arrays, whose size is fixed, these operations usually require creating a new array or shifting elements.

---

# Scalability Analysis

As the input size increases, the difference between constant, logarithmic and linear operations becomes increasingly significant.

Conceptually:

```text
Input Size

↓

10

↓

100

↓

1,000

↓

1,000,000
```

Operations with:

```text
O(1)
```

remain effectively constant.

Operations with:

```text
O(n)
```

increase proportionally.

Operations with:

```text
O(log n)
```

grow very slowly, making them highly scalable.

---

# Engineering Perspective

When designing software systems, choosing the correct operation is often more important than optimising code syntax.

Examples:

- Accessing values by index is extremely efficient.
- Repeated insertions near the beginning of an array should be avoided.
- Binary Search should be preferred over Linear Search when data is sorted.
- Arrays are excellent when random access is the dominant requirement.

Complexity analysis therefore guides engineering decisions rather than merely describing algorithms.

---

# Relationship with Future Modules

The complexity values presented here will be referenced repeatedly throughout the repository.

Examples include:

```text
Searching
        ↓
Linear Search
        ↓
Binary Search

Sorting
        ↓
Traversal
        ↓
Swap

Trees
        ↓
Comparison of Growth Rates

Graphs
        ↓
Traversal Complexity

Dynamic Programming
        ↓
Table Traversal
```

Understanding array complexity provides the analytical foundation required for these future modules.

---

# Best Practices

When analysing array algorithms:

- identify the dominant operation;
- evaluate both time and space complexity;
- distinguish best, average and worst cases;
- explain why the complexity occurs;
- relate performance to memory layout;
- justify algorithm selection based on scalability.

---

# Key Takeaways

The learner should remember the following principles:

- Complexity is determined by the work performed, not by the programming language.
- Constant-time access is possible because arrays use indexed addressing.
- Traversal, insertion and deletion require processing multiple elements.
- Binary Search demonstrates how algorithmic strategy influences scalability.
- Time and space complexity should always be analysed together.
- Engineering decisions should be supported by computational complexity analysis.

Understanding the computational complexity of array operations enables software engineers to design efficient algorithms, evaluate trade-offs and build scalable systems throughout the **Algorithms Java Mastery** repository.