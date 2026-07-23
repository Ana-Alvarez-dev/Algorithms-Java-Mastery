# Recursive Binary Search

## Purpose

Recursive Binary Search is an alternative implementation of the Binary Search algorithm that expresses the Divide and Conquer strategy through recursive function calls.

Instead of using an explicit loop, the algorithm repeatedly invokes itself on progressively smaller search intervals until the target element is found or the search space becomes empty.

Although the recursive implementation performs the same sequence of comparisons as the iterative version, it introduces additional concepts such as recursive reasoning, base cases, recursive calls and stack frames.

Understanding Recursive Binary Search provides an excellent introduction to recursive algorithm design and prepares the learner for more advanced recursive algorithms studied later in this repository.

---

# Learning Objectives

After completing this document, the learner should be able to:

- understand how Binary Search can be implemented recursively;
- identify the base case and recursive case;
- explain why recursion correctly reduces the search space;
- analyse recursive correctness;
- compare recursive and iterative implementations;
- understand the impact of recursion on memory consumption.

---

# Overview

Recursive Binary Search follows exactly the same algorithmic strategy as the iterative implementation.

The only difference is how the repetition is expressed.

Instead of repeatedly updating two indices inside a loop, each recursive call processes a smaller portion of the collection.

Conceptually:

```text
Entire Collection

↓

Middle Element

↓

Recursive Call

↓

Smaller Search Interval

↓

Recursive Call

↓

Target Found

or

Search Interval Empty
```

---

# Fundamental Idea

Every recursive algorithm consists of two essential components.

## Base Case

The recursion stops.

For Binary Search, this occurs when:

- the target is found; or
- the search interval becomes empty.

Without a base case, recursion would never terminate.

---

## Recursive Case

If the target has not yet been found,

the algorithm determines which half may still contain the search key and recursively searches only that portion.

Each recursive call reduces the size of the problem.

---

# Divide and Conquer

Recursive Binary Search naturally expresses the Divide and Conquer paradigm.

```text
Search Interval

↓

Divide

↓

Select Middle Element

↓

Compare

↓

Discard Half

↓

Recursive Search
```

Each recursive call solves a smaller instance of exactly the same problem.

---

# Pseudocode

```text
RECURSIVE-BINARY-SEARCH(A, low, high, key)

if low > high

    return NOT FOUND

middle ← (low + high) / 2

if A[middle] == key

    return middle

if key < A[middle]

    return RECURSIVE-BINARY-SEARCH(A, low, middle − 1, key)

return RECURSIVE-BINARY-SEARCH(A, middle + 1, high, key)
```

---

# Example

Collection

```text
[3][8][14][19][27][35][42]
```

Search key

```text
35
```

Call 1

```text
Middle = 19

35 > 19

↓

Search Right Half
```

Call 2

```text
[27][35][42]

Middle = 35

↓

Found
```

The recursion terminates immediately.

---

# Base Case

Every recursive algorithm must eventually stop.

Binary Search terminates under two conditions.

## Target Found

```text
A[middle] == key

↓

Return Position
```

---

## Empty Search Interval

```text
low > high

↓

Return NOT FOUND
```

This condition guarantees termination.

---

# Recursive Reduction

Each recursive call eliminates approximately half of the remaining search interval.

Example:

```text
64 Elements

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

This reduction produces logarithmic running time.

---

# Correctness

Recursive Binary Search is correct because every recursive call preserves all possible locations where the target may exist.

At each step:

- one half is eliminated;
- the remaining half still contains every possible valid solution.

Eventually,

either:

- the target is found; or
- the search interval becomes empty.

Therefore,

the algorithm always produces the correct result.

---

# Computational Complexity

## Best Case

The middle element is the target.

```text
O(1)
```

---

## Average Case

```text
O(log n)
```

---

## Worst Case

```text
O(log n)
```

The same number of comparisons as the iterative implementation.

---

# Space Complexity

Unlike the iterative implementation,

recursive Binary Search stores one stack frame for each recursive call.

Therefore,

its auxiliary memory complexity is:

```text
O(log n)
```

This additional memory is consumed by the call stack.

---

# Recursive Call Stack

Example:

```text
Call 1

↓

Call 2

↓

Call 3

↓

Target Found

↓

Return

↓

Return

↓

Return
```

Each recursive invocation remains on the stack until the deepest call completes.

---

# Recursive versus Iterative Binary Search

| Property | Iterative | Recursive |
|-----------|-----------|-----------|
| Running Time | O(log n) | O(log n) |
| Extra Memory | O(1) | O(log n) |
| Readability | High | Very High |
| Stack Usage | None | Yes |
| Risk of Stack Overflow | No | Very Small |

---

# Advantages

Recursive Binary Search:

- closely follows the Divide and Conquer strategy;
- is often easier to reason about;
- naturally expresses recursive problem decomposition;
- is useful for learning recursive algorithms.

---

# Limitations

Compared with the iterative implementation:

- consumes additional stack memory;
- introduces recursive function call overhead;
- may be slightly slower in practice.

For production software,

the iterative implementation is often preferred.

---

# Java Engineering Considerations

When implementing Recursive Binary Search in Java:

- define clear base cases;
- avoid infinite recursion;
- compute the midpoint safely;
- document the recursive behaviour;
- validate empty collections.

A safe midpoint calculation remains:

```java
int middle = low + (high - low) / 2;
```

---

# Relationship with Previous Modules

Recursive Binary Search combines concepts from multiple modules.

```text
Foundations
        ↓
Problem Solving

Complexity
        ↓
Logarithmic Analysis

Arrays
        ↓
Random Access

Searching
        ↓
Binary Search

↓

Recursive Binary Search
```

---

# Relationship with Future Modules

The recursive reasoning introduced here will be reused extensively.

```text
Recursive Binary Search

↓

Recursion

↓

Merge Sort

↓

Quick Sort

↓

Backtracking

↓

Dynamic Programming

↓

Tree Traversals
```

Understanding recursive Binary Search makes later recursive algorithms much easier to understand.

---

# Key Takeaways

Recursive Binary Search demonstrates how recursion naturally represents the Divide and Conquer paradigm.

Although its running time is identical to the iterative implementation, recursion introduces additional stack usage while often improving conceptual clarity.

Mastering this algorithm prepares the learner for more sophisticated recursive techniques used throughout Computer Science.

---

# Next Document

```
06-search-complexity.md
```

The next document analyses the computational complexity of searching algorithms, compares linear and logarithmic growth, and examines the engineering trade-offs involved in selecting an appropriate search strategy.