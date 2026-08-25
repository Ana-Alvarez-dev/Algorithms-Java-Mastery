# Array Fundamentals

## Algorithms Java Mastery

This document introduces the **array** as one of the fundamental data structures
of Computer Science and as the first concrete structure studied systematically
throughout **Algorithms Java Mastery**.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the study
of algorithms and data structures developed throughout this repository.

After establishing the reasoning methodology introduced in **Foundations** and
the analytical framework developed in **Complexity**, arrays provide the first
environment in which those concepts can be applied to a concrete data
representation.

Arrays are important because they combine several fundamental ideas:

- indexed organisation;
- fixed-size storage;
- positional access;
- sequential traversal;
- predictable structural behaviour;
- direct application of complexity analysis.

The central question addressed throughout this document is:

> **What is an array as a computational data structure, and which properties
> determine its algorithmic behaviour?**

---

# Purpose

The purpose of this document is to establish a rigorous conceptual
understanding of arrays before studying their concrete implementation in Java.

The learner should first understand the array as an abstract computational
structure rather than as syntax such as:

```java
int[] numbers;
```

The conceptual progression is:

```text
Computational Data
        ↓
Data Organisation
        ↓
Indexed Sequence
        ↓
Array
        ↓
Algorithmic Operations
```

This distinction reinforces a central principle of the repository:

> **Understand the computational structure before studying its
> language-specific implementation.**

---

# Learning Objectives

After studying this document, the learner should be able to:

- define an array as a computational data structure;
- identify the relationship between indexes and elements;
- explain why arrays are considered linear structures;
- explain the meaning of fixed length;
- distinguish logical size from valid index range;
- understand the classical contiguous-storage model;
- explain why indexed access can be performed efficiently;
- distinguish direct access from searching;
- identify fundamental array operations;
- recognise the principal advantages and limitations of arrays;
- distinguish arrays from dynamic collections;
- relate array properties to algorithm design;
- connect arrays with the complexity concepts introduced previously.

These competencies provide the conceptual foundation for the remainder of the
**Arrays** module.

---

# What Is an Array?

An **array** is an indexed linear data structure containing a fixed number of
positions.

Each position stores an element and is identified through an index.

Conceptually:

```text
Index
   0       1       2       3       4
   ↓       ↓       ↓       ↓       ↓

[ 10 ]  [ 25 ]  [ 18 ]  [ 40 ]  [ 12 ]
```

The fundamental relationship is:

```text
Index
        ↓
Position
        ↓
Element
```

When the index is known, the corresponding position can be accessed directly
under the standard array model.

---

# Logical Array Model

An array containing `n` elements may be represented conceptually as:

```text
A[0]
A[1]
A[2]
...
A[n - 1]
```

where:

```text
n = number of positions in the array
```

For a zero-based indexing model such as Java:

```text
First Valid Index
        ↓
0
```

and:

```text
Last Valid Index
        ↓
n - 1
```

Therefore:

```text
Length
        ≠
Last Index
```

This distinction becomes important when implementing traversal algorithms.

---

# Arrays Are Linear Data Structures

Arrays are classified as **linear data structures** because their elements are
logically arranged in a sequence.

Conceptually:

```text
A[0]
 ↓
A[1]
 ↓
A[2]
 ↓
A[3]
 ↓
...
```

Each position has a defined relationship with the positions before and after it.

This linear organisation naturally supports sequential traversal.

---

# Indexed Organisation

Indexes provide a positional coordinate within the array.

For example:

```text
Array

[ A ][ B ][ C ][ D ]

Index

  0    1    2    3
```

Therefore:

```text
A[0] → A
A[1] → B
A[2] → C
A[3] → D
```

The index identifies a **position**, not a value.

This distinction is fundamental.

---

# Position vs Value

Consider:

```text
[ 8 ][ 3 ][ 15 ][ 9 ]
```

The value:

```text
15
```

is located at:

```text
Index 2
```

If the position is already known:

```text
A[2]
```

the value can be accessed directly.

If only the value is known:

```text
Find 15
```

a search may be required.

Therefore:

```text
Known Index
        ↓
Direct Access
```

is different from:

```text
Known Value
        ↓
Search Problem
```

This distinction becomes essential when analysing complexity.

---

# Fixed Length

A classical array contains a fixed number of positions after creation.

Conceptually:

```text
Create Array
        ↓
Allocate n Positions
        ↓
Length = n
        ↓
Length Remains Fixed
```

The values stored inside those positions may change.

The number of positions does not.

For example:

```text
Initial State

[ 4 ][ 7 ][ 2 ]
```

may become:

```text
Updated State

[ 4 ][ 9 ][ 2 ]
```

while the number of positions remains:

```text
3
```

---

# Length vs Stored Values

The fixed length of an array does not mean that the stored data cannot change.

Two separate properties exist:

```text
Array Length
        ↓
Number of Positions
```

and:

```text
Element Values
        ↓
Data Stored in Those Positions
```

An algorithm may update values without changing array length.

---

# Homogeneous Element Type

In conventional typed array models, the positions of an array are intended to
contain elements belonging to a common type.

Examples include:

```text
Integer Array
```

```text
Character Array
```

```text
Reference Array
```

The exact type rules depend on the programming language.

In Java, arrays have a defined component type.

The language-specific details are studied later in:

```text
03-java-arrays.md
```

---

# Classical Memory Model

In the classical computational model, array positions are stored consecutively
within a contiguous memory region.

Conceptually:

```text
Memory

┌──────┬──────┬──────┬──────┬──────┐
│ A[0] │ A[1] │ A[2] │ A[3] │ A[4] │
└──────┴──────┴──────┴──────┴──────┘
```

This model provides the intuition behind efficient indexed access.

A position can conceptually be determined from:

```text
Base Address
        +
Index × Element Size
```

The exact physical representation depends on the language, runtime, and
machine.

The classical model remains valuable for understanding why arrays behave
differently from pointer-based structures.

---

# Contiguous Storage as an Abstraction

The statement that arrays use contiguous storage should be understood at the
appropriate level of abstraction.

At the algorithmic level:

```text
Array
        ↓
Indexed Consecutive Positions
```

provides a useful computational model.

At the implementation level, actual memory representation may involve:

- runtime metadata;
- object headers;
- references;
- virtual memory;
- language-specific representation.

Therefore:

> **The conceptual contiguous-storage model explains array behaviour without
> requiring every machine-level implementation detail to be exposed.**

These details are developed further in:

```text
02-memory-layout.md
```

---

# Direct Indexed Access

One of the defining properties of arrays is efficient positional access.

If:

```text
index
```

is already known, accessing:

```text
A[index]
```

does not require traversal from the beginning of the structure.

Under the standard computational model:

```text
Indexed Access
        ↓
Θ(1)
```

This is a major structural advantage of arrays.

---

# Why Indexed Access Is Constant

Conceptually, the position of an element can be calculated from:

```text
Base
        +
Offset
```

where:

```text
Offset
        =
Index × Element Size
```

The number of array elements does not determine how many earlier positions must
be visited.

Therefore:

```text
Array of 10 Elements
        ↓
Direct Indexed Access
```

and:

```text
Array of 1,000,000 Elements
        ↓
Direct Indexed Access
```

require the same asymptotic amount of positional work under the standard model.

---

# Random Access

Arrays are commonly described as supporting **random access**.

This does not mean randomised behaviour.

It means that any valid position can be accessed directly without traversing
all preceding elements.

Conceptually:

```text
A[0]
A[500]
A[n - 1]
```

are all accessed through their indexes.

The term therefore refers to **arbitrary positional access**.

---

# Sequential Access

Arrays also support sequential processing naturally.

For example:

```text
A[0]
 ↓
A[1]
 ↓
A[2]
 ↓
...
 ↓
A[n - 1]
```

Processing every element produces a traversal.

If constant work is performed for every element:

```text
n elements
        ×
constant work
        ↓
Θ(n)
```

Thus arrays support both:

- direct positional access;
- sequential traversal.

---

# Ordered Does Not Mean Sorted

Arrays preserve positional order.

However:

```text
Ordered
```

should not be confused with:

```text
Sorted
```

Consider:

```text
[ 8 ][ 3 ][ 15 ][ 9 ]
```

The elements have a definite order of positions.

The values are not sorted numerically.

Therefore:

```text
Positional Order
        ≠
Sorted Order
```

Sorting requires an additional ordering property defined over the values.

---

# Why Arrays Exist

Suppose a program needs to represent many related values.

Using independent variables:

```text
value1
value2
value3
value4
value5
```

does not provide a convenient general structure for algorithmic processing.

An array instead provides:

```text
values
        ↓
[ value ][ value ][ value ][ value ][ value ]
```

with a uniform index mechanism.

This makes it possible to write algorithms that operate over an arbitrary number
of positions.

For example:

```text
For every index i
        ↓
Process A[i]
```

This ability to treat many related values through one indexed structure is one
of the main reasons arrays are fundamental.

---

# Fundamental Array Properties

The principal conceptual properties of arrays are:

```text
Array
        │
        ├── Linear
        ├── Indexed
        ├── Fixed Length
        ├── Positional
        ├── Typed
        └── Directly Accessible by Index
```

Each property influences the operations that can be performed efficiently.

---

# Fundamental Array Operations

Arrays support several common operations.

```text
Array
        │
        ├── Access
        ├── Update
        ├── Traverse
        ├── Search
        ├── Copy
        ├── Insert
        └── Delete
```

These operations do not all have the same computational cost.

The cost depends on the exact operation and representation.

---

# Access

Access retrieves the element stored at a known valid position.

Conceptually:

```text
Known Index
        ↓
A[index]
        ↓
Element
```

Under the standard array model:

```text
Time
        ↓
Θ(1)
```

---

# Update

An update replaces the value stored at a known position.

Conceptually:

```text
A[index]
        ↓
Replace Existing Value
```

Under the standard model:

```text
Time
        ↓
Θ(1)
```

provided the index is already known and valid.

---

# Traversal

Traversal processes positions systematically.

For example:

```text
for every i from 0 to n - 1
        ↓
process A[i]
```

When each iteration performs constant work:

```text
Time
        ↓
Θ(n)
```

Traversal becomes one of the most important patterns used throughout later
algorithm modules.

---

# Search

Searching asks for the position or existence of a value.

For an unsorted array, a simple sequential search may need to inspect every
position.

Conceptually:

```text
A[0]
 ↓
A[1]
 ↓
A[2]
 ↓
...
```

Worst-case work:

```text
Θ(n)
```

This again demonstrates:

```text
Access
        ≠
Search
```

---

# Insertion

Insertion into an array requires careful definition.

If the array has a fixed number of positions, adding an additional position is
not possible without creating another structure.

However, an algorithm may simulate insertion into an available position while
preserving a logical sequence.

This may require shifting elements.

Conceptually:

```text
Before

[A][B][C][ ][ ]

Insert X at Position 1
        ↓

Shift B and C
        ↓

[A][X][B][C][ ]
```

The number of shifts may grow with `n`.

The exact cost is analysed later in:

```text
05-array-operations.md
```

---

# Deletion

Deletion also requires a clear representation model.

Removing a logical element while preserving order may require shifting later
elements.

Conceptually:

```text
Before

[A][B][C][D]
```

Remove:

```text
B
```

Then:

```text
Shift C and D
        ↓
[A][C][D][ ]
```

The physical array length remains unchanged.

What changes is the logical organisation of stored values.

This distinction becomes important when comparing fixed arrays with dynamic
collections.

---

# Physical Length vs Logical Size

Some algorithms use a fixed-capacity array while only part of it contains
logically active data.

For example:

```text
Physical Array

[A][B][C][ ][ ][ ]
```

may have:

```text
Capacity = 6
```

while:

```text
Logical Size = 3
```

Therefore:

```text
Physical Length
        ≠
Logical Number of Active Elements
```

This distinction becomes important when implementing dynamic structures on top
of arrays.

---

# Advantages of Arrays

Arrays provide several important structural advantages.

## Direct Indexed Access

Known positions can be accessed efficiently.

---

## Predictable Structure

The number and position of array slots are explicitly defined.

---

## Simple Traversal

Sequential processing maps naturally to index iteration.

---

## Low Structural Complexity

Arrays do not require per-element linkage structures such as explicit next or
previous references.

---

## Foundation for Algorithms

Many classical algorithms are naturally expressed over indexed sequences.

---

## Useful Memory Locality

Sequential array access may benefit practical cache behaviour because nearby
positions are commonly stored near one another in the classical memory model.

This is a practical performance characteristic rather than an asymptotic
complexity guarantee.

---

# Limitations of Arrays

Arrays also introduce important constraints.

## Fixed Length

The number of positions cannot normally be changed after creation.

---

## Cost of Ordered Insertion

Preserving logical order may require shifting elements.

---

## Cost of Ordered Deletion

Deleting a logical element may also require shifting.

---

## Search Without Additional Structure

If the desired value's position is unknown and no ordering or indexing
structure is available, multiple elements may need to be inspected.

---

## Capacity Decisions

When arrays are used as backing storage for dynamic structures, capacity
management becomes an additional engineering concern.

---

# Arrays vs Dynamic Arrays

A fixed array and a dynamic array are related but different abstractions.

```text
Fixed Array
        ↓
Length Established at Creation
```

while:

```text
Dynamic Array
        ↓
Logical Size Can Grow
        ↓
Underlying Storage May Be Reallocated
```

A dynamic array is often implemented using fixed arrays internally.

For example, conceptually:

```text
Current Array Full
        ↓
Create Larger Array
        ↓
Copy Elements
        ↓
Continue
```

This behaviour motivates the amortized analysis studied previously.

---

# Arrays vs Linked Structures

Arrays and linked structures organise data differently.

Conceptually:

```text
Array
        ↓
Indexed Positional Storage
```

versus:

```text
Linked Structure
        ↓
Elements Connected Through References
```

This difference affects:

- access;
- traversal;
- insertion;
- deletion;
- memory overhead;
- locality.

Neither structure is universally superior.

The appropriate choice depends on the problem.

---

# Arrays and Memory Locality

Sequential array traversal commonly accesses neighbouring positions.

Conceptually:

```text
A[0] → A[1] → A[2] → A[3]
```

This access pattern may interact favourably with modern processor caches.

However:

```text
Cache Locality
```

should not be used as a substitute for:

```text
Complexity Analysis
```

An algorithm may remain:

```text
Θ(n)
```

regardless of whether one implementation benefits more from cache locality than
another.

---

# Arrays as the Basis for Other Structures

Arrays frequently appear as components of more advanced structures.

Examples include:

```text
Dynamic Array
        ↓
Array-Based List
```

```text
Array
        ↓
Binary Heap Representation
```

```text
Array
        ↓
Hash Table Storage
```

```text
Two-Dimensional Array
        ↓
Matrix Representation
```

```text
Array / Matrix
        ↓
Dynamic Programming Table
```

Understanding arrays therefore supports later data-structure study.

---

# Arrays and Heaps

A complete binary heap can be represented efficiently using an array.

Conceptually:

```text
Tree Position
        ↓
Array Index
```

Relationships between parent and child positions can be calculated from
indexes.

The exact formulas are introduced later in the heap-related study.

This is an example of how array indexing can encode structural relationships.

---

# Arrays and Hash Tables

Hash-table implementations often rely on an array-like structure of storage
positions or buckets.

Conceptually:

```text
Key
        ↓
Hash Function
        ↓
Index
        ↓
Storage Position
```

Understanding array indexing therefore helps prepare for hashing.

---

# Arrays and Graphs

Graphs may also be represented using array-based structures.

For example:

```text
Adjacency Matrix
```

uses a two-dimensional indexed representation.

Other graph representations may use arrays or dynamic collections internally.

Arrays therefore remain relevant even when the logical data structure is no
longer linear.

---

# Arrays and Dynamic Programming

Dynamic-programming algorithms frequently store intermediate results in arrays.

Conceptually:

```text
Subproblem
        ↓
Index
        ↓
Stored Result
```

One-dimensional or multidimensional arrays may represent the state space.

This allows previously computed results to be reused.

---

# Relationship with Computational Complexity

Arrays provide a concrete environment in which the concepts from
`docs/02-complexity/` can be applied.

Consider:

```text
Indexed Access
        ↓
Fixed Amount of Positional Work
        ↓
Θ(1)
```

versus:

```text
Full Traversal
        ↓
Process n Elements
        ↓
Θ(n)
```

versus:

```text
Nested Traversal of n × n Positions
        ↓
Θ(n²)
```

The operation must always be identified before assigning a complexity result.

---

# Simplified Operation Reference

Under the standard fixed-array model:

| Operation | Typical Time Behaviour | Important Condition |
|---|---:|---|
| Access known index | `Θ(1)` | Valid index |
| Update known index | `Θ(1)` | Valid index |
| Full traversal | `Θ(n)` | Constant work per element |
| Unsorted search | Worst `Θ(n)` | Sequential search model |
| Copy `n` elements | `Θ(n)` | Process every copied element |
| Shift up to `n` elements | `Θ(n)` | Order-preserving operation |

This table is a review aid.

The costs are derived formally in later documents.

---

# Arrays and Correctness

Array algorithms frequently rely on invariants.

For example, while finding a maximum:

```text
Processed Prefix
        ↓
Current Maximum
```

A useful invariant is:

> **After processing the first `i` elements, the current maximum is the largest
> value among those processed elements.**

Arrays make invariants especially visible because the processed and unprocessed
regions can often be described using indexes.

---

# Processed and Unprocessed Regions

Many array algorithms divide the structure logically into regions.

Conceptually:

```text
[ Processed ][ Unprocessed ]
```

or:

```text
[ Sorted ][ Unsorted ]
```

or:

```text
[ Examined ][ Remaining ]
```

This perspective becomes important in:

- searching;
- sorting;
- partitioning;
- two-pointer algorithms;
- prefix algorithms.

---

# Preconditions

Array algorithms may require explicit preconditions.

Examples include:

```text
Array is not null.
```

```text
Array contains at least one element.
```

```text
Index is within the valid range.
```

```text
Array is ordered.
```

The required preconditions depend on the algorithm.

For example:

```text
Find Maximum
```

may require:

```text
n ≥ 1
```

while:

```text
Compute Sum
```

may naturally define a result for an empty array.

---

# Edge Cases

Important array edge cases may include:

- zero elements when valid;
- one element;
- duplicate values;
- all equal values;
- negative values;
- extreme numeric values;
- already ordered values;
- reverse-ordered values.

The classification depends on the specification.

An input should not be labelled an edge case if it violates the declared
preconditions.

---

# Off-by-One Reasoning

Arrays frequently expose **off-by-one errors** because valid indexes are bounded.

For an array of length:

```text
n
```

the valid zero-based index interval is:

```text
0 ≤ i < n
```

not:

```text
0 ≤ i ≤ n
```

Therefore:

```text
Last Valid Index
        =
n - 1
```

This simple relationship is fundamental to correct array traversal.

---

# Java Perspective

Java arrays will be studied in detail later.

At this stage, the important conceptual mapping is:

```text
Abstract Array
        ↓
Fixed Indexed Structure
        ↓
Java Array Representation
```

Java introduces concrete rules such as:

- zero-based indexing;
- fixed `length`;
- component types;
- runtime bounds checking;
- default initialisation;
- reference semantics.

These language-specific details do not define the abstract concept itself.

---

# Common Misconceptions

## “An Array Is a Java Feature”

Incorrect.

Arrays are a general Computer Science abstraction.

Java provides one concrete implementation.

---

## “Ordered Means Sorted”

Incorrect.

Arrays preserve positional order, but stored values may be in any arrangement.

---

## “Access and Search Are the Same”

Incorrect.

Known-index access is different from locating an unknown value.

---

## “Arrays Can Grow Automatically”

A fixed array does not change its length.

Dynamic array abstractions create new storage when growth is required.

---

## “Insertion Changes Array Length”

Insertion into a fixed-capacity representation usually changes logical contents,
not the physical array length.

Growing beyond capacity requires another array or another data structure.

---

## “All Array Operations Are Constant Time”

Incorrect.

Known-index access may be constant.

Traversal, searching, copying, and shifting may require work proportional to
the number of elements.

---

## “Contiguous Memory Means Exact Physical Hardware Layout Is Always Visible”

Incorrect.

Contiguous storage is a useful computational and language-level model.

Runtime and hardware abstractions may introduce additional representation
details.

---

## “Arrays Are Obsolete Because Collections Exist”

Incorrect.

Arrays remain fundamental both directly and as implementation components of
higher-level structures.

---

# Best Practices

When reasoning about arrays:

- define what `n` represents;
- distinguish physical length from logical size;
- distinguish index from value;
- distinguish access from search;
- state valid index ranges explicitly;
- define whether empty input is valid;
- identify mutation requirements;
- analyse shifting and copying when relevant;
- derive operation cost from actual work;
- consider auxiliary memory explicitly;
- preserve multiple dimensions for multidimensional structures;
- use arrays only when their structural properties fit the problem.

The objective is to choose arrays because their characteristics solve the
computational problem effectively.

---

# Array Selection Questions

Before choosing an array, ask:

```text
Is the number of positions naturally fixed?
```

```text
Is indexed access important?
```

```text
Will most processing be sequential?
```

```text
Are frequent middle insertions required?
```

```text
Are frequent deletions required?
```

```text
Is key-based lookup more important than positional access?
```

```text
Does the problem require dynamic growth?
```

These questions connect abstract structure with engineering decisions.

---

# Relationship with Later Array Documents

This document establishes the conceptual foundation.

The progression continues with:

```text
Array Fundamentals
        ↓
Memory Layout
        ↓
Java Arrays
        ↓
Multidimensional Arrays
        ↓
Array Operations
        ↓
Traversal Patterns
        ↓
Common Algorithms
        ↓
Complexity Analysis
        ↓
Problem Solving
```

Each document introduces additional depth while preserving the same
computational model.

---

# Relationship with Later Repository Modules

Arrays prepare the learner for:

```text
Searching
```

because arrays provide indexed search spaces.

```text
Sorting
```

because array positions support comparison, movement, and partitioning.

```text
Hash Tables
```

because indexed storage supports bucket-based representations.

```text
Heaps and Trees
```

because positional relationships may encode structural information.

```text
Graphs
```

because matrices and array-based adjacency representations may be used.

```text
Dynamic Programming
```

because arrays frequently store computed subproblem results.

---

# Expected Outcome

After completing this document, the learner should no longer define an array
only as:

> “A variable that stores several values.”

Instead, the learner should understand the complete conceptual model:

```text
Array
        ↓
Linear Indexed Structure
        ↓
Fixed Number of Positions
        ↓
Positional Access
        ↓
Classical Contiguous Storage Model
        ↓
Direct Indexed Operations
        ↓
Sequential Traversal
        ↓
Operation-Specific Complexity
```

The learner should be able to explain:

- what an array represents;
- how indexes identify positions;
- why array length is fixed;
- why indexed access is efficient;
- why searching differs from access;
- why insertion and deletion may require movement;
- how arrays support later algorithms and data structures.

---

# Key Takeaways

After studying this document, the learner should remember that:

- an array is a linear indexed data structure;
- an array contains a fixed number of positions;
- indexes identify positions rather than values;
- zero-based arrays of length `n` use indexes from `0` through `n - 1`;
- positional order does not imply sorted order;
- the classical array model uses contiguous storage;
- direct indexed access has constant asymptotic cost under the standard model;
- search, traversal, copying, insertion, and deletion may require different
  amounts of work;
- physical array length and logical number of active elements may differ in
  array-backed structures;
- arrays form the conceptual basis for many algorithms and advanced data
  structures;
- Java arrays are one concrete implementation of the broader computational
  abstraction.

Arrays therefore provide the first concrete structure through which the
reasoning principles from **Foundations** and the analytical methods from
**Complexity** can be applied systematically.

---

# Next Document

```text
02-memory-layout.md
```

The next document examines the memory model associated with arrays.

It introduces:

- contiguous storage;
- base addresses;
- offsets;
- element size;
- indexed-address calculation;
- locality;
- cache-related intuition;
- abstraction boundaries between algorithmic and physical memory models.

The next central question becomes:

> **How does the memory organisation of an array support direct indexed access
> and influence practical algorithm behaviour?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The conceptual treatment of arrays, indexed structures, algorithmic operations,
and complexity developed throughout this module is complemented by official
Java documentation for language-specific behaviour.

Additional academic and technical references are documented in:

```text
docs/00-project/10-references.md
```

The next documents progressively connect this abstract array model with memory
organisation, Java semantics, algorithm implementation, correctness, and
complexity analysis.