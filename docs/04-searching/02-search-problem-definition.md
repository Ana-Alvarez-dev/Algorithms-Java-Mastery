# The Search Problem Definition

## Purpose

Before studying specific searching algorithms, it is essential to understand the computational problem they are designed to solve.

Exact-match searches share an existence/location question; insertion positions, ordered boundaries and nearest-value selection have distinct outputs and contracts.

This document defines the search problem, identifies its components, establishes the terminology used throughout this module and explains the properties that influence algorithm selection.

A clear problem definition allows software engineers to reason about correctness, computational complexity and implementation independently of any particular algorithm.

---

# Learning Objectives

After completing this document, the learner should be able to:

- formally define the computational search problem;
- identify the inputs and outputs of a searching algorithm;
- distinguish successful and unsuccessful searches;
- understand the assumptions required by different search strategies;
- recognise how problem characteristics influence algorithm selection.

---

# Defining the Search Problem

A searching algorithm receives a collection of elements together with a target value, commonly referred to as the **search key**.

The objective is to determine whether the target exists within the collection and, if it does, identify its location.

Formally:

```text
Input
──────────────
Collection of elements
+
Search key

↓

Processing

↓

Output
──────────────
Position of the element
or
Element itself
or
Boolean result
or
Not Found
```

This describes exact-match search. The implemented lower-bound and nearest-value operations can return a useful result even when no value equals the target.

---

# Concrete Repository Contracts

All eight public int[] operations reject null with NullPointerException and
leave the input unchanged. Nondecreasing order is a caller obligation for the
ordered operations; the implementations do not add a linear sortedness scan.
Results for unsorted input to an ordered operation are outside its contract.

| Operation | Required order | Result | Empty input | Duplicate policy |
|---|---|---|---|---|
| LinearSearch | None | Matching index or -1 | -1 | First occurrence |
| BinarySearch | Nondecreasing | Matching index or -1 | -1 | Any match |
| RecursiveBinarySearch | Nondecreasing | Matching index or -1 | -1 | Any match |
| FirstOccurrence | Nondecreasing | First matching index or -1 | -1 | Lowest matching index |
| LastOccurrence | Nondecreasing | Last matching index or -1 | -1 | Highest matching index |
| SearchInsertPosition | Nondecreasing | Lower bound in [0,n] | 0 | Before existing equals |
| SearchRange | Nondecreasing | Inclusive equality endpoints [first,last] | {-1,-1} | All equal-target endpoints; no enumeration |
| ClosestValueSearch | Nondecreasing | Closest-value index, -1 only when empty | -1 | Minimise absolute long distance, then index |

These contracts map to the eight existing classes, not hypothetical API choices.
The [applied contract and proof map](implementation-contracts.md) states each
result mathematically, including the sentinel boundary n, actual validation,
mutation policy and distinctions from the Java standard library.
See the [module matrix](README.md#module-traceability-matrix),
[Testing regressions](../16-testing/verification-report.md) and
[closing review](../00-project/12-closing-traceability-review.md). General result
representations discussed below do not replace the concrete guarantees above.

---

# Input

A searching problem always begins with two fundamental inputs.

## Collection

A finite collection stored in a data structure. In this module an int[] is an indexed sequence: order and duplicate positions matter, unlike a mathematical set.

Examples include:

- arrays;
- linked lists;
- trees;
- hash tables;
- graphs.

The organisation of this collection strongly influences which searching algorithms can be applied.

---

## Search Key

The value that the algorithm attempts to locate.

Examples include:

- integer values;
- usernames;
- product identifiers;
- student IDs;
- email addresses.

The search key is compared against elements stored in the collection.

---

# Output

The result depends on the requirements of the application.

Common outputs include:

## Index Position

```text
Collection

[15][24][31][48][72]

Search key

31

↓

Output

Index = 2
```

---

## Element Reference

Instead of returning a position, some algorithms return the actual object.

Example:

```text
Employee Object

↓

Returned
```

---

## Boolean Result

Sometimes only existence matters.

```text
true

or

false
```

This approach is common in validation and authentication systems.

---

## Not Found

If the target does not exist, the algorithm reports failure.

Examples include:

- -1
- null
- Optional.empty()
- false

The representation depends on the programming language and application design.

---

# Successful Search

A search is successful when at least one element satisfies the search condition.

```text
Collection

[10][20][30][40][50]

Search key

30

↓

Found
```

The algorithm terminates after identifying the desired element.

---

# Unsuccessful Search

A search is unsuccessful when no matching element exists.

```text
Collection

[10][20][30][40][50]

Search key

25

↓

Not Found
```

Correct handling of unsuccessful searches is an important aspect of algorithm correctness.

---

# Assumptions

Different searching algorithms require different assumptions.

Some algorithms operate on any collection.

Others require additional properties.

Examples:

```text
Linear Search

↓

Works on any collection.
```

```text
Binary Search

↓

Requires sorted data.
```

Understanding these assumptions is essential before selecting an algorithm.

---

# Search Space

The search space is the portion of the collection that may still contain the desired element.

Initially:

```text
Entire Collection

↓

Possible locations
```

During execution, many algorithms progressively reduce the search space.

For example:

```text
Entire Array

↓

Left Half

↓

Quarter

↓

Single Element
```

Reducing the search space efficiently is one of the key ideas behind advanced searching algorithms.

---

# Search Criteria

Searching is not always based on equality.

Different applications use different criteria.

Examples include:

Equality

```text
Product ID == 1503
```

Ordering

```text
Age > 18
```

Pattern Matching

```text
Name starts with "Ann"
```

Multiple Conditions

```text
Department == Sales

AND

Salary > 50000
```

Real-world systems frequently combine multiple search conditions.

---

# Characteristics of a Search Problem

When analysing a search problem, software engineers should identify the following properties.

## Data Size

How many elements are stored?

---

## Data Organisation

Is the collection:

- unordered?
- sorted?
- indexed?
- hierarchical?

---

## Frequency of Searches

Will searching occur occasionally or millions of times per second?

---

## Frequency of Updates

Does the collection change frequently?

Some search techniques perform well only when the collection remains relatively stable.

---

## Memory Constraints

Can additional memory be allocated?

Some searching algorithms trade memory for speed.

---

# Engineering Perspective

Selecting a searching algorithm involves much more than comparing asymptotic complexity.

Software engineers must consider:

- data organisation;
- update frequency;
- memory usage;
- implementation complexity;
- scalability;
- maintainability.

The most efficient algorithm is not always the most appropriate solution.

Engineering decisions require balancing these factors.

---

# Relationship with Previous Modules

This document builds upon concepts introduced in earlier modules.

```text
Foundations
        ↓
Problem Definition

Complexity
        ↓
Algorithm Analysis

Arrays
        ↓
Data Organisation

Searching
        ↓
Problem Definition
```

The search problem combines algorithmic reasoning with computational complexity and data organisation.

---

# Key Takeaways

The concrete result distinguishes an exact match from an insertion boundary,
nearest value or pair of endpoints. These are different computational contracts,
even when they share an interval-reduction strategy.

A precise problem definition allows software engineers to analyse correctness, evaluate complexity and select the most appropriate algorithm for a given application.

Understanding the search problem is therefore the foundation upon which all searching algorithms are built.

---

# Next Document

```
03-linear-search.md
```

The next document introduces **Linear Search**, the simplest searching algorithm, and demonstrates how a sequential traversal can solve the search problem without requiring any assumptions about the organisation of the data.
