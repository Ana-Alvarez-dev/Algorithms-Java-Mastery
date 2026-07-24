# Sorting Problem Definition

## Purpose

Before studying individual sorting algorithms, it is essential to define precisely what the sorting problem is.

In Computer Science, every algorithm is designed to solve a well-defined computational problem. A clear problem specification establishes the algorithm's responsibilities, the properties that its output must satisfy and the assumptions under which it operates.

For sorting algorithms, this specification goes beyond simply arranging elements in a particular order. A correct solution must preserve every element from the original collection while producing a sequence that satisfies a defined ordering relation.

This document formally defines the sorting problem, introducing its inputs, outputs, ordering relation, preconditions, postconditions and correctness requirements. These concepts provide the foundation for analysing, implementing and verifying every sorting algorithm presented throughout this module.

---

# Learning Objectives

After completing this document, the learner should be able to:

- formally define the computational sorting problem;
- identify the inputs and outputs of a sorting algorithm;
- distinguish between preconditions and postconditions;
- understand the ordering relation required by the problem;
- explain the permutation property;
- recognise the characteristics of a correctly sorted collection;
- reason about algorithm correctness before implementation.

---

# The Computational Sorting Problem

The sorting problem can be stated as follows.

> Given a finite collection of elements and an ordering criterion, produce a permutation of the collection in which the elements satisfy the required ordering relation.

Unlike searching, where the objective is to locate information, sorting reorganises every element in the collection.

Conceptually:

```text
Input Collection

↓

Sorting Algorithm

↓

Ordered Collection
```

The algorithm changes only the arrangement of the elements.

It must never change the elements themselves.

---

# Input

The input consists of:

- a finite collection of elements;
- an ordering criterion.

The collection may be represented by:

- an array;
- a list;
- another sequential data structure.

Example:

```text
[18][4][27][9][13]
```

The ordering criterion determines how two elements are compared.

Examples include:

- ascending numerical order;
- descending numerical order;
- alphabetical order;
- chronological order;
- custom business rules.

---

# Output

The output is another arrangement of exactly the same elements.

For example:

Input:

```text
[18][4][27][9][13]
```

Output:

```text
[4][9][13][18][27]
```

The output must satisfy two conditions simultaneously:

- the elements are correctly ordered;
- no element has been lost, duplicated or modified.

---

# Ordering Relation

Sorting requires a comparison rule that determines the relative position of two elements.

For ascending order:

```text
A[i] ≤ A[i + 1]
```

For descending order:

```text
A[i] ≥ A[i + 1]
```

The ordering relation depends entirely on the selected comparison criterion.

For objects, the relation may involve one or more attributes.

Example:

```text
Employee

↓

Department

↓

Surname
```

---

# Preconditions

A precondition describes what must be true before the algorithm begins.

Typical preconditions include:

- the input collection exists;
- every element can be compared according to the selected ordering criterion;
- the comparison operation is well-defined and consistent.

Some algorithms introduce additional assumptions.

For example, certain specialised algorithms require:

- numerical keys;
- bounded value ranges;
- specific data representations.

The comparison-based algorithms studied in this module require only a valid comparison operation.

---

# Postconditions

A postcondition describes what must be true after the algorithm finishes.

Every correct sorting algorithm must satisfy the following conditions.

---

## Ordered Sequence

The resulting collection satisfies the required ordering relation.

Ascending example:

```text
A[0] ≤ A[1] ≤ A[2] ≤ ... ≤ A[n − 1]
```

Descending example:

```text
A[0] ≥ A[1] ≥ A[2] ≥ ... ≥ A[n − 1]
```

---

## Permutation Property

The output contains exactly the same elements as the input.

No element may be:

- removed;
- duplicated;
- modified;
- replaced.

Only the positions of the elements may change.

Conceptually:

```text
Input Elements

=

Output Elements
```

---

# Permutation

The result of a sorting algorithm is a **permutation** of the input collection.

Example:

Input:

```text
[7][3][5][1]
```

Possible permutation:

```text
[3][1][7][5]
```

Sorted permutation:

```text
[1][3][5][7]
```

Every sorting result is a permutation.

However, not every permutation is sorted.

---

# Correctly Sorted Collection

A collection is correctly sorted only when both correctness conditions hold simultaneously.

```text
Correct Ordering

+

Same Elements

=

Correct Sorting
```

If either condition fails, the algorithm is incorrect.

---

# Example of a Correct Result

Input:

```text
[6][2][8][4]
```

Output:

```text
[2][4][6][8]
```

Verification:

✔ Elements preserved

✔ Ascending order satisfied

The result is correct.

---

# Example of an Incorrect Result

Input:

```text
[6][2][8][4]
```

Output:

```text
[2][4][8]
```

Although the sequence is ordered,

one element has disappeared.

The permutation property is violated.

---

# Another Incorrect Result

Input:

```text
[6][2][8][4]
```

Output:

```text
[2][6][4][8]
```

All elements are present,

but:

```text
6 > 4
```

The ordering relation is violated.

Therefore,

the collection is not correctly sorted.

---

# Empty Collection

The empty collection is already sorted.

Example:

```text
[]
```

No comparisons are necessary.

Every correct sorting algorithm should handle this case.

---

# Single Element

A collection containing one element is also considered sorted.

Example:

```text
[15]
```

No rearrangement is required.

---

# Duplicate Values

Duplicate values are permitted.

Example:

```text
[7][3][7][2][3]
```

Correct ascending order:

```text
[2][3][3][7][7]
```

Every duplicate must remain in the result.

---

# Ordering Objects

Sorting frequently involves objects rather than primitive values.

Example:

```text
Student

↓

Name

↓

Grade

↓

Registration Number
```

The ordering criterion determines which attribute controls the final arrangement.

Example:

Sort by:

- grade;
- surname;
- registration number.

The algorithm itself remains unchanged.

Only the comparison operation changes.

---

# Stability Considerations

Some problems require equivalent elements to preserve their original relative order.

Example:

Input:

```text
Ana     Grade 90

Laura   Grade 75

Maria   Grade 90
```

Stable result:

```text
Laura   Grade 75

Ana     Grade 90

Maria   Grade 90
```

The definition of the sorting problem itself does not require stability.

Stability is an additional property of particular algorithms.

It will be studied in detail in the next document.

---

# Correctness Requirements

Every sorting algorithm studied in this repository must satisfy the following requirements.

```text
Input Accepted

↓

Algorithm Terminates

↓

Output Contains Same Elements

↓

Output Satisfies Ordering Relation

↓

Correct Solution
```

These properties provide the basis for correctness proofs developed later in the module.

---

# Engineering Perspective

In professional software engineering, defining the problem precisely is often more important than selecting the algorithm.

Before implementing a solution, engineers must understand:

- what is being ordered;
- which comparison rule applies;
- whether duplicate keys are expected;
- whether stability is required;
- whether the original collection may be modified;
- how correctness will be verified.

A clear specification reduces ambiguity and simplifies implementation, testing and maintenance.

---

# Relationship with Previous Documents

This document builds directly upon the concepts introduced in:

```text
Sorting

↓

Module Overview

↓

Sorting Fundamentals

↓

Sorting Problem Definition
```

The previous document introduced the general concept of sorting.

This document formalises that concept as a computational problem.

---

# Relationship with Future Documents

The formal specification developed here supports every algorithm studied later.

```text
Sorting Problem Definition

↓

Sorting Properties

↓

Selection Sort

↓

Bubble Sort

↓

Insertion Sort

↓

Merge Sort

↓

Quick Sort
```

Understanding the problem specification makes it possible to analyse whether each algorithm satisfies its required properties.

---

# Key Takeaways

Sorting is a formally defined computational problem rather than simply arranging elements visually.

A correct sorting algorithm must:

- accept a valid collection and comparison rule;
- terminate successfully;
- produce a correctly ordered sequence;
- preserve every input element.

These requirements establish the foundation for analysing correctness, complexity and implementation throughout the remainder of the Sorting module.

---

# Next Document

```text
03-sorting-properties.md
```

The next document introduces the fundamental properties used to classify sorting algorithms, including stability, in-place behaviour, adaptability, comparison-based sorting, internal and external sorting, and other characteristics that influence algorithm selection in software engineering.