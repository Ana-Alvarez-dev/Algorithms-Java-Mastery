# Interview Notes

## Purpose

Technical interviews rarely evaluate a candidate's ability to memorise algorithms.

Instead, interviewers assess the candidate's capacity to analyse problems, justify design decisions, evaluate algorithmic complexity and communicate their reasoning clearly.

Searching algorithms are among the most frequently discussed topics because they combine fundamental Computer Science concepts with practical software engineering skills.

This document summarises the essential knowledge required to discuss searching algorithms confidently during technical interviews.

---

# Learning Objectives

After reviewing this document, the learner should be able to:

- explain the purpose of searching algorithms;
- compare Linear Search and Binary Search;
- analyse algorithmic complexity;
- justify algorithm selection;
- recognise common interview problems;
- communicate algorithmic reasoning clearly.

---

# Fundamental Concepts

Every software engineer should understand the following concepts.

Searching is the process of locating information within a collection.

The objective may be:

- determine whether an element exists;
- locate an element;
- retrieve one or multiple matching elements;
- identify the first or last occurrence;
- find the closest value satisfying a condition.

Searching is one of the most fundamental operations in Computer Science.

---

# Linear Search

### When should it be used?

Linear Search is appropriate when:

- the collection is small;
- the collection is unsorted;
- insertions and deletions occur frequently;
- implementation simplicity is preferred.

---

### Complexity

| Case | Complexity |
|------|------------|
| Best | O(1) |
| Average | O(n) |
| Worst | O(n) |
| Auxiliary Space | O(1) |

---

### Advantages

- Simple implementation.
- No ordering required.
- Works with every sequential collection.

---

### Limitations

- Poor scalability.
- Inefficient for large datasets.

---

# Binary Search

### When should it be used?

Binary Search is appropriate when:

- the collection is sorted;
- random access is available;
- many searches are performed;
- update operations are relatively infrequent.

---

### Complexity

| Case | Complexity |
|------|------------|
| Best | O(1) |
| Average | O(log n) |
| Worst | O(log n) |
| Auxiliary Space (Iterative) | O(1) |
| Auxiliary Space (Recursive) | O(log n) |

---

### Advantages

- Excellent scalability.
- Very efficient for large datasets.
- Logarithmic running time.

---

### Limitations

- Requires sorted data.
- Maintaining order may be expensive.

---

# Most Important Comparison

| Feature | Linear Search | Binary Search |
|---------|---------------|---------------|
| Sorted Collection Required | No | Yes |
| Time Complexity | O(n) | O(log n) |
| Auxiliary Space | O(1) | O(1) Iterative / O(log n) Recursive |
| Implementation | Very Simple | Moderate |
| Scalability | Low | High |

This comparison is one of the most common topics in technical interviews.

---

# Frequently Asked Interview Questions

## Why is Binary Search faster?

Because every comparison discards approximately half of the remaining search space.

---

## Why must the collection be sorted?

Without ordering, comparisons provide no information about which half may safely be discarded.

---

## Can Binary Search be applied to a linked list?

Not efficiently.

Binary Search depends on efficient random access.

Linked lists require sequential traversal to reach the middle element, eliminating the algorithm's logarithmic advantage.

---

## Which algorithm would you choose for an unsorted collection?

Linear Search.

Sorting the collection solely to perform one search is generally not worthwhile.

---

## Why is Binary Search considered a Divide and Conquer algorithm?

Because each step divides the problem into two parts and recursively or iteratively continues searching only one of them.

---

## Why is the iterative implementation usually preferred?

Because it avoids recursive call overhead and uses constant auxiliary memory.

---

## Why calculate the midpoint as:

```java
int middle = low + (high - low) / 2;
```

This formulation prevents integer overflow while producing the correct midpoint.

---

# Technical Reasoning Questions

These questions remain deliberately bounded by concepts developed in this
module.

## Linear Search Correctness

**Question**

What loop invariant could be used to justify Linear Search?

**Expected direction**

Explain what is known about the already examined prefix before each iteration,
then connect initialization, maintenance, and termination to the search
postcondition.

---

## Binary Search Preconditions

**Question**

Why is sorted input a correctness requirement rather than merely a performance
optimization?

**Expected direction**

Explain that the comparison with the midpoint is only sufficient to eliminate
one half when ordering guarantees that all values in that half are on the wrong
side of the target.

---

## Binary Search Invariant

**Question**

What property should remain true about the current interval during Binary
Search?

**Expected direction**

If the target exists, it must remain inside the active interval.

---

## Termination

**Question**

Why does Binary Search terminate?

**Expected direction**

The active interval becomes strictly smaller whenever the target is not found.

---

## Complexity

**Question**

Why is Binary Search worst-case Θ(log n)?

**Expected direction**

Repeated halving means the number of reductions needed to reach a constant-size
interval grows logarithmically with `n`.

---

## Iterative vs Recursive

**Question**

Why do iterative and recursive Binary Search have the same asymptotic running
time but different auxiliary-space costs?

**Expected direction**

They perform the same search-space reductions, but the recursive version keeps
a stack frame for each active call.

---

## Average-Case Precision

**Question**

Why should an engineer be careful when stating the average-case complexity of a
search algorithm?

**Expected direction**

Average-case analysis depends on assumptions about successful searches, target
distribution, and input probabilities.

---

## Sorting Before Searching

**Question**

Is it always worthwhile to sort an unsorted collection before using Binary
Search?

**Expected direction**

No. Compare the preprocessing cost with the expected number of future searches
and updates.

---

# Interview Reasoning Process

Candidates are expected to explain their reasoning rather than immediately writing code.

A recommended approach is:

```text
Understand the Problem
        ↓
Clarify Requirements
        ↓
Identify Constraints
        ↓
Recognise the Search Pattern
        ↓
Select the Algorithm
        ↓
Explain Correctness
        ↓
Analyse Complexity
        ↓
Implement
        ↓
Test
```

Interviewers usually evaluate this reasoning process more than the final implementation.

---

# Common Interview Mistakes

Avoid the following mistakes:

- choosing Binary Search for unsorted data;
- forgetting to analyse complexity;
- ignoring edge cases;
- failing to justify algorithm selection;
- assuming recursive and iterative implementations have identical memory usage;
- writing code before understanding the problem.

These mistakes often indicate weak algorithmic reasoning.

---

# Practical Interview Tips

During a technical interview:

- think aloud while solving the problem;
- clarify assumptions before coding;
- explain why an algorithm is appropriate;
- discuss alternative solutions;
- analyse time and space complexity;
- mention important edge cases;
- verify the solution with a small example.

Clear communication is an essential engineering skill.

---

# Key Concepts to Remember

Review the following topics before an interview:

- Linear Search;
- Binary Search;
- Recursive Binary Search;
- Big-O notation;
- Best, Average and Worst Case;
- Auxiliary Space;
- Sorted versus Unsorted Collections;
- Divide and Conquer;
- Search Patterns;
- Edge Cases;
- Correctness Reasoning.

These concepts form the foundation of most introductory interview questions on searching algorithms.

---

# Engineering Perspective

Professional software engineers are expected to justify their technical decisions rather than simply produce working code.

A strong candidate demonstrates:

- analytical thinking;
- structured problem solving;
- understanding of algorithmic trade-offs;
- awareness of computational complexity;
- ability to communicate technical ideas clearly.

These qualities are often more valuable than memorising implementations.

---

# Module Review

The Searching module has developed the following competencies:

```text
Search Fundamentals
        ↓
Problem Definition
        ↓
Linear Search
        ↓
Binary Search
        ↓
Recursive Binary Search
        ↓
Complexity Analysis
        ↓
Search Patterns
        ↓
Common Search Problems
        ↓
Problem Solving Methodology
        ↓
Common Mistakes
        ↓
Interview Preparation
```

Together, these topics provide a solid foundation for studying more advanced algorithms and data structures.

---

# Preparing for the Next Module

Searching and sorting are closely connected.

Many efficient searching techniques depend on ordered collections, while many sorting algorithms are designed to improve the efficiency of future search operations.

Understanding searching algorithms therefore provides the conceptual foundation for studying sorting algorithms.

---

# Module Completion

This document completes the review layer of the Searching module.

The detailed academic references for the module are maintained in:

```text
docs/04-searching/README.md
```

and the repository-wide bibliography is maintained in:

```text
docs/00-project/10-references.md
```

The next repository module is:

```text
docs/05-sorting/
```