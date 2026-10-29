# Merge Sort Case Study

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md)
for the reviewed Java contracts, traces, correctness, tests and evidence limits.

## Algorithms Java Mastery

## Concrete sort and inversion extension

The reused Sorting.MergeSort sorts ascending in the same int[]; no descending
option, linked-list sort, external-file pipeline or generic record sort is
implemented here. Such applications below describe other adaptations. The
[map](implementation-contracts.md) states the sorted-prefix/multiset invariant
and explains InversionCount's private copy and strict cross-pair ledger.
Choosing left on equality supports the stable-merge proof; equal primitive
values alone cannot expose an identity-stability test.

Merge Sort is one of the classical applications of the **Divide and Conquer** paradigm. Unlike simpler recursive algorithms, Merge Sort repeatedly divides a collection into smaller independent subproblems, recursively sorts each subproblem, and finally combines the partial solutions into one completely ordered sequence.

Unlike Quick Sort, whose efficiency depends on pivot selection, Merge Sort always divides the input into two nearly equal halves. This balanced decomposition guarantees a predictable running time of **Θ(n log n)** regardless of the initial order of the data.

Merge Sort is also a **stable sorting algorithm**, meaning that equal elements preserve their original relative order after sorting. For this reason, it is widely used in applications where data stability is important.

This case study demonstrates how Divide and Conquer, recurrence relations, and the Master Theorem work together to explain the correctness and efficiency of a real algorithm.

---

# 1. Purpose

The purpose of this document is to:

- study Merge Sort as a Divide and Conquer algorithm;
- understand its recursive decomposition;
- analyze the merge operation;
- explain its correctness;
- derive its recurrence relation;
- evaluate its computational complexity.

---

# 2. Problem Statement

Given:

- an unsorted collection of elements.

Produce:

- the same int elements arranged in ascending order in the supplied array
  for the reused Java implementation; descending order is a separate variant.

Conceptually:

```text
Unsorted Array

↓

Sorted Array
```

Sorting is one of the most fundamental computational problems because many algorithms become more efficient once the data is ordered.

---

# 3. Fundamental Idea

Merge Sort repeatedly divides the collection until each subproblem contains only one element.

Conceptually:

```text
Array

↓

Left Half

Right Half

↓

Smaller Halves

↓

Single Elements
```

Single-element arrays are already sorted.

The algorithm then merges these sorted pieces to rebuild the complete solution.

---

# 4. Divide and Conquer Strategy

Merge Sort follows the three classical stages.

## Divide

Split the array into two approximately equal halves.

---

## Conquer

Recursively sort each half.

---

## Combine

Merge the two sorted halves into one sorted array.

Conceptually:

```text
Divide

↓

Sort Left

Sort Right

↓

Merge

↓

Sorted Array
```

---

# 5. Recursive Decomposition

Each recursive call divides the problem into two smaller subproblems.

Example:

```text
8 Elements

↓

4 + 4

↓

2 + 2 + 2 + 2

↓

1 + 1 + 1 + 1 + ...
```

The recursive decomposition continues until every subproblem contains exactly one element.

---

# 6. Base Case

The existing implementation returns for lengths below two (including empty input).
The recursive nonempty base stops when:

```text
Subarray Size = 1
```

A single element is already sorted.

Therefore:

```text
Return
```

No additional recursive calls are required.

---

# 7. The Merge Phase

After the recursive calls return, Merge Sort combines the sorted halves.

Conceptually:

```text
Sorted Left Half

+

Sorted Right Half

↓

Merged Sorted Array
```

Unlike Binary Search, Merge Sort performs significant work during the Combine phase.

---

# 8. Why Merging Works

The merge operation assumes:

- the left half is already sorted;
- the right half is already sorted.

The algorithm repeatedly compares the smallest remaining elements from both halves and copies the smaller one into the result.

Conceptually:

```text
Left

↓

Compare

↓

Right

↓

Smaller Element

↓

Output
```

This process continues until all elements have been copied.

---

# 9. Correctness

Induction supplies two sorted halves with their original multiplicities.
During merging, the destination prefix is sorted and contains exactly the
consumed items; every remaining item is at least its last value. Emitting the
smaller available head preserves this invariant, and copying the remainder
when one half is exhausted preserves coverage. At completion, the range is
sorted and its full multiset is preserved. Cursor progress and strictly smaller
recursive ranges prove termination. The auxiliary copy keeps unread heads
available while the input range is overwritten.

---

# 10. Recurrence Relation

Merge Sort generates the recurrence:

```text
T(n)

=

2T(n / 2)

+

Theta(n)
```

where:

- two recursive calls are performed;
- each recursive call handles half of the input;
- merging requires linear work.

---

# 11. Applying the Master Theorem

Parameters:

```text
a = 2

b = 2

f(n) = Theta(n)
```

Applying the Master Theorem:

```text
T(n)

=

Θ(n log n)
```

For n>=2 this tight bound follows because the reviewed Sorting.MergeSort
performs a full merge on every level, without an ordered-halves shortcut. It
uses one auxiliary array per public call. Choosing the left item on equality
supports stability by source reasoning; equal primitive ints do not expose
identities for an observable stability test. No generic record sort is added.

---

# 12. Time Complexity

| Case | Complexity |
|------|-----------:|
| Best | O(n log n) |
| Average | O(n log n) |
| Worst | O(n log n) |

Unlike Quick Sort, Merge Sort maintains the same asymptotic complexity regardless of the input order.

---

# 13. Space Complexity

Merge Sort requires additional memory for the merge operation.

Additional auxiliary space:

```text
O(n)
```

Recursive stack space:

```text
O(log n)
```

The auxiliary array dominates the total additional memory requirement.

---

# 14. Stability

Merge Sort is a **stable sorting algorithm**.

If two elements have equal keys:

```text
5A

5B
```

Their relative order remains unchanged after sorting.

This property is important when sorting records by multiple fields.

---

# 15. Advantages

Merge Sort offers several advantages.

It provides:

- predictable performance;
- guaranteed Θ(n log n) running time;
- stable sorting;
- balanced recursive decomposition;
- suitability for linked lists;
- efficient external sorting.

These characteristics make it valuable in many practical systems.

---

# 16. Limitations

Merge Sort also has disadvantages.

It requires:

- additional auxiliary memory;
- repeated copying during merging;
- recursive method calls.

For small arrays, simpler algorithms such as Insertion Sort may perform better because of their lower constant factors.

---

# 17. Comparison with Quick Sort

| Characteristic | Merge Sort | Quick Sort |
|----------------|-----------|-----------|
| Divide | Equal halves | Pivot partition |
| Combine | Merge required | No explicit merge |
| Stable | Yes | No (standard implementation) |
| Worst Case | O(n log n) | O(n²) |
| Auxiliary Space | O(n) | O(log n) worst for the repository's smaller-side-recursive variant |

Both algorithms apply Divide and Conquer but make different engineering trade-offs.

---

# 18. Practical Applications

Merge Sort is commonly used in:

- external sorting;
- database systems;
- large file processing;
- linked-list sorting;
- stable sorting libraries;
- distributed data processing.

Its predictable performance makes it suitable for applications where worst-case guarantees are important.

---

# 19. Common Mistakes

Typical implementation mistakes include:

- incorrect midpoint calculation;
- copying elements incorrectly during merging;
- forgetting remaining elements after one half is exhausted;
- allocating unnecessary temporary arrays repeatedly;
- incorrect recursive boundaries.

Most Merge Sort bugs occur during the merge phase rather than the recursive decomposition.

---

# 20. Java Perspective

Java implementations of Merge Sort commonly use:

- arrays;
- recursive helper methods;
- temporary auxiliary arrays;
- index manipulation.

Because recursion depth is logarithmic, stack usage remains relatively small.

However, the auxiliary array contributes an additional **O(n)** memory requirement.

---

# 21. Engineering Perspective

Merge Sort illustrates an important engineering principle.

```text
Balanced Division
        ↓
Independent Recursive Solutions
        ↓
Efficient Combination
        ↓
Predictable Performance
```

Although Merge Sort requires additional memory, it guarantees stable and efficient sorting for every input.

This predictable behavior explains why it remains one of the most influential sorting algorithms in Computer Science.

---

# 22. Relationship with the Next Document

Merge Sort demonstrates a Divide and Conquer algorithm with an explicit and relatively expensive Combine phase.

The next case study examines **Quick Sort**, another Divide and Conquer sorting algorithm that follows a different strategy. Instead of merging sorted halves, Quick Sort partitions the array around a pivot and recursively sorts the resulting partitions.

Comparing these two algorithms highlights the engineering trade-offs between stability, memory usage, and average-case performance.

---

# 23. Key Takeaways

After completing this document, the learner should understand that:

- Merge Sort is a classical Divide and Conquer sorting algorithm;
- the algorithm recursively divides the input into two balanced halves;
- the merge phase combines two sorted subarrays into a larger sorted array;
- the recurrence relation is `T(n) = 2T(n/2) + Theta(n)`;
- the Master Theorem yields a running time of `Θ(n log n)`;
- Merge Sort guarantees the same asymptotic complexity for best, average, and worst cases;
- the algorithm is stable but requires `O(n)` auxiliary memory;
- Merge Sort demonstrates how balanced recursive decomposition leads to predictable algorithmic performance.