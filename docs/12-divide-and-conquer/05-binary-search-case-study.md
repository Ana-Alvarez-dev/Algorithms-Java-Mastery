# Binary Search Case Study

## Algorithms Java Mastery

Binary Search is one of the simplest and most elegant applications of the **Divide and Conquer** paradigm.

Rather than examining every element sequentially, Binary Search repeatedly divides the search space into two halves. At each step, one half is immediately discarded because it cannot contain the desired value.

This strategy reduces the size of the problem exponentially, allowing Binary Search to locate an element in logarithmic time.

Although the algorithm itself is relatively simple, it illustrates many of the fundamental concepts introduced throughout this repository:

- recursive decomposition;
- base cases;
- recurrence relations;
- the Master Theorem;
- logarithmic complexity;
- algorithmic correctness.

For this reason, Binary Search is commonly one of the first Divide and Conquer algorithms studied in Computer Science.

---

# 1. Purpose

The purpose of this document is to:

- study Binary Search as a Divide and Conquer algorithm;
- understand its recursive decomposition;
- analyze its recurrence relation;
- explain its correctness;
- evaluate its computational complexity;
- identify common implementation mistakes.

---

# 2. Problem Statement

Given:

- a sorted collection of elements;
- a target value.

Determine whether the target exists and, if it does, locate its position.

Conceptually:

```text
Sorted Array

↓

Locate Target
```

Unlike Linear Search, Binary Search does not inspect every element.

---

# 3. Prerequisite

Binary Search requires one essential condition.

The data must already be:

```text
Sorted
```

If the collection is not ordered, Binary Search cannot safely discard half of the search space.

Sorting is therefore a precondition of the algorithm.

---

# 4. Fundamental Idea

Instead of examining every element, Binary Search inspects only the middle element.

Conceptually:

```text
Left Half

Middle

Right Half
```

After comparing the target with the middle element:

- one half is discarded;
- the other half becomes the new search space.

---

# 5. Divide and Conquer Strategy

Binary Search follows the Divide and Conquer paradigm.

## Divide

Select the middle element.

---

## Conquer

Continue searching only the half that may contain the target.

---

## Combine

No explicit combination is required.

The recursive call directly returns the result.

This distinguishes Binary Search from algorithms such as Merge Sort.

---

# 6. Recursive Decomposition

Each recursive call reduces the problem by half.

Conceptually:

```text
n

↓

n / 2

↓

n / 4

↓

n / 8

↓

...
```

The search interval becomes progressively smaller until the target is found or no elements remain.

---

# 7. Base Cases

Binary Search has two principal base cases.

## Target Found

```text
Middle = Target

↓

Return Position
```

---

## Empty Search Space

```text
Left Index

>

Right Index

↓

Target Not Found
```

These base cases guarantee termination.

---

# 8. Recursive Case

If the middle element is not the target:

```text
Target < Middle

↓

Search Left Half
```

or

```text
Target > Middle

↓

Search Right Half
```

Exactly one recursive call is performed.

---

# 9. Why It Works

Binary Search relies on the ordering of the data.

Because the collection is sorted:

```text
Target < Middle
```

implies:

```text
Right Half

Cannot Contain Target
```

Similarly:

```text
Target > Middle
```

implies:

```text
Left Half

Cannot Contain Target
```

The algorithm safely eliminates half of the remaining search space after every comparison.

---

# 10. Correctness

Binary Search is correct because:

- the search interval always remains sorted;
- every recursive call preserves the original problem;
- the search space becomes smaller;
- the base cases are eventually reached.

Correctness follows directly from recursive reduction.

---

# 11. Recurrence Relation

Binary Search generates the recurrence:

```text
T(n)

=

T(n / 2)

+

O(1)
```

where:

- one recursive call is made;
- the problem size is divided by two;
- only constant work is performed outside recursion.

---

# 12. Applying the Master Theorem

Parameters:

```text
a = 1

b = 2

f(n) = O(1)
```

Applying the Master Theorem:

```text
T(n)

=

Θ(log n)
```

This confirms Binary Search's logarithmic running time.

---

# 13. Time Complexity

| Case | Complexity |
|------|-----------:|
| Best | O(1) |
| Average | O(log n) |
| Worst | O(log n) |

Best case:

The middle element is immediately the target.

Worst case:

The search interval is repeatedly divided until it becomes empty or the target is found.

---

# 14. Space Complexity

### Recursive implementation

Additional stack space:

```text
O(log n)
```

because recursion depth is logarithmic.

---

### Iterative implementation

Additional space:

```text
O(1)
```

No recursive calls are required.

---

# 15. Comparison with Linear Search

| Characteristic | Linear Search | Binary Search |
|----------------|--------------:|--------------:|
| Data must be sorted | No | Yes |
| Search strategy | Sequential | Divide and Conquer |
| Worst-case time | O(n) | O(log n) |
| Additional space (iterative) | O(1) | O(1) |

Binary Search is significantly more efficient for large sorted datasets.

---

# 16. Practical Applications

Binary Search is used in many systems, including:

- database indexes;
- dictionary lookups;
- search engines;
- compiler symbol tables;
- version control systems;
- scheduling algorithms;
- operating systems.

The algorithm is valuable whenever ordered data must be searched efficiently.

---

# 17. Common Mistakes

Typical implementation mistakes include:

- applying Binary Search to unsorted data;
- incorrect middle-index calculation;
- incorrect boundary updates;
- infinite loops caused by invalid conditions;
- forgetting the empty search-space base case.

Careful handling of indices is essential.

---

# 18. Java Perspective

Java implementations commonly operate on:

- arrays;
- lists supporting efficient indexed access.

Binary Search is usually implemented using:

- recursion;
- iteration.

In production software, the iterative version is often preferred because it avoids recursive stack usage while preserving the same asymptotic time complexity.

---

# 19. Engineering Perspective

Binary Search demonstrates an important engineering principle:

```text
Reduce the Problem

↓

Discard Impossible Solutions

↓

Continue with a Smaller Problem
```

Rather than performing additional work, the algorithm gains efficiency by eliminating unnecessary work.

This principle appears repeatedly in modern algorithm design.

---

# 20. Relationship with the Next Document

Binary Search represents the simplest Divide and Conquer algorithm because it requires no explicit combine phase.

The next case study examines **Merge Sort**, where recursive subproblems must be merged to produce the final solution.

This introduces a more complex Divide and Conquer algorithm with a different recurrence relation and computational complexity.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- Binary Search is a classical Divide and Conquer algorithm;
- it requires the input data to be sorted;
- each recursive call reduces the search space by half;
- Binary Search performs one recursive call per level;
- the recurrence relation is `T(n) = T(n/2) + O(1)`;
- the Master Theorem yields a time complexity of `Θ(log n)`;
- recursive Binary Search requires `O(log n)` auxiliary stack space;
- iterative Binary Search requires `O(1)` additional space;
- Binary Search demonstrates how recursive decomposition can dramatically improve algorithmic efficiency compared with sequential search.