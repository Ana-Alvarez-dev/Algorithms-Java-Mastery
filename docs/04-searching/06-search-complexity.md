# Search Complexity

## Purpose

Selecting an appropriate searching algorithm requires more than understanding its implementation.

Software engineers must evaluate how efficiently an algorithm performs as the size of the input grows.

Two algorithms may solve exactly the same computational problem while exhibiting dramatically different performance characteristics.

For this reason, computational complexity is one of the primary criteria used when selecting a searching algorithm.

This document analyses the time and space complexity of the searching algorithms introduced in this module and explains the engineering trade-offs involved in choosing between them.

---

# Learning Objectives

After completing this document, the learner should be able to:

- analyse the computational complexity of searching algorithms;
- distinguish best, average and worst-case behaviour;
- compare linear and logarithmic growth;
- evaluate the trade-offs between different searching strategies;
- understand how data organisation affects search performance;
- select an appropriate searching algorithm for different scenarios.

---

# Why Complexity Matters

Searching is one of the most common operations performed by software systems.

Although two algorithms may produce identical results, the time required to obtain those results can differ significantly.

Consider the following example.

Searching one million elements:

```text
Linear Search

↓

May inspect nearly every element.
```

versus

```text
Binary Search

↓

Approximately twenty comparisons.
```

The difference becomes increasingly important as collections continue to grow.

---

# Time Complexity

Time complexity describes how the number of operations performed by an algorithm grows as the input size increases.

Searching algorithms are commonly analysed using **Big-O notation**.

Rather than measuring execution time in seconds, complexity measures the relationship between input size and algorithmic growth.

---

# Best Case

The best case represents the minimum amount of work required.

For searching algorithms, this occurs when the desired element is located immediately.

Example:

```text
Collection

[25][41][53][67]

Search Key

25
```

Only one comparison is necessary.

Complexity:

```text
O(1)
```

---

# Average Case

The average case represents the expected behaviour across many searches.

For Linear Search, approximately half of the collection is examined.

Complexity:

```text
O(n)
```

For Binary Search, the search interval is repeatedly divided in half.

Complexity:

```text
O(log n)
```

---

# Worst Case

The worst case represents the maximum amount of work required.

For Linear Search:

- the element is the final element; or
- the element does not exist.

Complexity:

```text
O(n)
```

For Binary Search:

the search interval is reduced until only one candidate remains.

Complexity:

```text
O(log n)
```

---

# Comparing Growth

The difference between linear and logarithmic growth becomes more significant as the collection increases.

| Elements | Linear Search | Binary Search |
|----------:|--------------:|--------------:|
| 10 | up to 10 comparisons | up to 4 comparisons |
| 100 | up to 100 comparisons | up to 7 comparisons |
| 1,000 | up to 1,000 comparisons | up to 10 comparisons |
| 1,000,000 | up to 1,000,000 comparisons | up to 20 comparisons |

Although both algorithms solve the same problem, Binary Search scales much more efficiently.

---

# Space Complexity

Searching algorithms also consume memory.

## Linear Search

Requires only a small number of variables.

```text
Auxiliary Space

O(1)
```

---

## Iterative Binary Search

Also stores only a few variables.

```text
Auxiliary Space

O(1)
```

---

## Recursive Binary Search

Requires one stack frame for each recursive call.

```text
Auxiliary Space

O(log n)
```

Although this additional memory is relatively small, it should still be considered during algorithm selection.

---

# Complexity Summary

| Algorithm | Best | Average | Worst | Auxiliary Space |
|-----------|------|----------|--------|-----------------|
| Linear Search | O(1) | O(n) | O(n) | O(1) |
| Binary Search (Iterative) | O(1) | O(log n) | O(log n) | O(1) |
| Binary Search (Recursive) | O(1) | O(log n) | O(log n) | O(log n) |

---

# Engineering Trade-offs

Choosing a searching algorithm involves balancing several factors.

## Linear Search

Advantages:

- works on unsorted collections;
- extremely simple;
- minimal implementation effort.

Disadvantages:

- poor scalability;
- inefficient for large datasets.

---

## Binary Search

Advantages:

- excellent scalability;
- logarithmic running time;
- very efficient for repeated searches.

Disadvantages:

- requires sorted collections;
- maintaining sorted data may introduce additional computational cost.

---

# Algorithm Selection

The most efficient algorithm depends on the characteristics of the problem.

## Small Collections

Recommended:

```text
Linear Search
```

The simplicity of the algorithm often outweighs the performance difference.

---

## Large Sorted Collections

Recommended:

```text
Binary Search
```

The logarithmic complexity provides substantial performance improvements.

---

## Frequently Updated Collections

Linear Search may be preferable.

Maintaining sorted order after every insertion or deletion may cost more than the savings obtained during searching.

---

## Frequently Queried Collections

Binary Search becomes highly advantageous when many searches are performed over relatively stable data.

---

# Practical Examples

| Scenario | Recommended Algorithm |
|----------|-----------------------|
| Small configuration file | Linear Search |
| Student attendance list | Linear Search |
| Sorted dictionary | Binary Search |
| Product catalogue sorted by ID | Binary Search |
| Static reference tables | Binary Search |
| Temporary unsorted data | Linear Search |

---

# Relationship with Previous Modules

This document applies the theoretical concepts introduced in the Complexity module.

```text
Complexity

↓

Big-O Analysis

↓

Searching

↓

Algorithm Comparison
```

Rather than introducing new complexity concepts, this document demonstrates how they guide engineering decisions.

---

# Relationship with Future Modules

Complexity analysis will continue to play a central role throughout the repository.

```text
Searching

↓

Sorting

↓

Trees

↓

Hash Tables

↓

Graphs

↓

Dynamic Programming
```

Every future algorithm will be evaluated using the same analytical methodology.

---

# Key Takeaways

Computational complexity provides an objective way to compare searching algorithms.

Although Linear Search and Binary Search solve the same problem, their scalability differs significantly.

Choosing the appropriate algorithm depends not only on asymptotic complexity but also on data organisation, update frequency, memory constraints and application requirements.

Understanding these trade-offs is an essential skill for software engineers.

---

# Next Document

```
07-search-patterns.md
```

The next document introduces common searching patterns and demonstrates how searching techniques can be adapted to solve a wide variety of computational problems beyond simple value lookups.