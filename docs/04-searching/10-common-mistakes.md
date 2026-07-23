# Common Mistakes

## Purpose

Searching algorithms are conceptually simple, yet they are responsible for a significant number of implementation errors in software systems.

Many of these mistakes are not caused by misunderstanding the algorithm itself, but by overlooking assumptions, ignoring edge cases or applying an inappropriate search strategy.

Recognising these common mistakes helps software engineers produce more reliable, maintainable and efficient software.

This document presents the most frequent errors encountered when analysing, implementing and testing searching algorithms, together with practical recommendations for avoiding them.

---

# Learning Objectives

After completing this document, the learner should be able to:

- recognise common implementation mistakes;
- identify incorrect assumptions before coding;
- understand why certain search strategies fail;
- avoid logical errors in searching algorithms;
- improve algorithm correctness and robustness;
- develop better software engineering practices.

---

# Mistake 1 — Choosing the Wrong Algorithm

One of the most common mistakes is selecting a searching algorithm before analysing the problem.

Example:

Using Binary Search on an unsorted collection.

```text
Unsorted Data

↓

Binary Search

↓

Incorrect Result
```

Before selecting an algorithm, always verify the characteristics of the data.

---

# Mistake 2 — Forgetting the Preconditions

Every algorithm has assumptions that must be satisfied.

Binary Search assumes:

- sorted data;
- valid search interval;
- consistent comparison operation.

Ignoring these assumptions invalidates the algorithm.

---

# Mistake 3 — Ignoring Edge Cases

Many implementations work correctly for ordinary inputs but fail for exceptional situations.

Typical edge cases include:

- empty collection;
- collection with one element;
- first element;
- last element;
- missing element;
- duplicate values.

Every implementation should explicitly consider these situations.

---

# Mistake 4 — Incorrect Boundary Conditions

Boundary errors are among the most common programming mistakes.

Typical examples include:

```text
low > high
```

versus

```text
low >= high
```

or

incorrect loop termination conditions.

Small boundary mistakes frequently produce incorrect results or infinite loops.

---

# Mistake 5 — Incorrect Midpoint Calculation

A common Binary Search implementation computes:

```java
int middle = (low + high) / 2;
```

Although correct for small collections, this calculation may overflow for very large indices.

The preferred implementation is:

```java
int middle = low + (high - low) / 2;
```

This formulation avoids integer overflow while producing the same result.

---

# Mistake 6 — Infinite Loops

Improperly updating search boundaries may prevent the algorithm from making progress.

Incorrect example:

```text
low remains unchanged

↓

middle remains unchanged

↓

Infinite Loop
```

Every iteration or recursive call must reduce the search interval.

---

# Mistake 7 — Infinite Recursion

Recursive Binary Search requires a valid base case.

Without one, recursive calls continue indefinitely until a stack overflow occurs.

Every recursive implementation should clearly define:

- base case;
- recursive case;
- termination condition.

---

# Mistake 8 — Confusing Correctness with Efficiency

An algorithm may produce the correct answer while still being inefficient.

Example:

Searching every element in a sorted collection using Linear Search.

The result is correct.

The algorithm selection is poor.

Software engineering requires balancing correctness and performance.

---

# Mistake 9 — Ignoring Complexity

Developers sometimes focus exclusively on implementation details.

However, algorithm selection should also consider:

- execution time;
- memory consumption;
- scalability.

Complexity analysis is an essential part of software engineering.

---

# Mistake 10 — Not Handling Duplicate Values

Binary Search returns one matching element.

It does not necessarily return:

- the first occurrence;
- the last occurrence.

Problems involving duplicates often require modified algorithms.

Understanding the problem requirements is essential.

---

# Mistake 11 — Assuming Data Never Changes

Some algorithms perform well only when collections remain relatively stable.

If insertions and deletions occur frequently,

maintaining sorted order may become more expensive than using Linear Search.

Algorithm selection should consider update frequency.

---

# Mistake 12 — Neglecting Testing

Many searching implementations appear correct until tested under unusual conditions.

Recommended test cases include:

```text
Empty Collection
```

```text
Single Element
```

```text
Target Exists
```

```text
Target Does Not Exist
```

```text
First Position
```

```text
Last Position
```

```text
Duplicate Elements
```

Automated testing significantly improves implementation reliability.

---

# Best Practices

Professional software engineers typically follow these recommendations:

- understand the problem before coding;
- verify algorithm assumptions;
- analyse edge cases;
- implement clear termination conditions;
- evaluate computational complexity;
- write automated tests;
- favour readability over unnecessary optimisation;
- document important assumptions.

These practices reduce defects and improve maintainability.

---

# Engineering Perspective

Experienced engineers understand that implementing an algorithm correctly is only part of the task.

Reliable software also requires:

- validating assumptions;
- anticipating exceptional situations;
- reasoning about correctness;
- analysing performance;
- testing thoroughly.

Preventing mistakes is generally less expensive than correcting them later.

---

# Relationship with Previous Documents

This document consolidates the knowledge developed throughout the Searching module.

```text
Search Fundamentals
        ↓
Searching Algorithms
        ↓
Complexity Analysis
        ↓
Search Patterns
        ↓
Problem Solving
        ↓
Common Mistakes
```

Recognising mistakes reinforces a deeper understanding of algorithm design.

---

# Relationship with Future Modules

The same categories of mistakes appear throughout Computer Science.

```text
Searching
        ↓
Sorting
        ↓
Trees
        ↓
Graphs
        ↓
Dynamic Programming
```

Developing the habit of identifying common mistakes improves performance across every algorithmic domain.

---

# Key Takeaways

Most errors in searching algorithms originate from incorrect assumptions, poor problem analysis or inadequate handling of edge cases rather than from the algorithms themselves.

By understanding these common mistakes and adopting disciplined engineering practices, software engineers can develop solutions that are not only correct, but also efficient, maintainable and robust.

Learning to recognise mistakes is an essential step towards mastering algorithm design.

---

# Next Document

```
11-interview-notes.md
```

The next document summarises the most important concepts of this module from the perspective of technical interviews, highlighting frequently asked questions, reasoning strategies and practical advice for solving search-related problems under interview conditions.