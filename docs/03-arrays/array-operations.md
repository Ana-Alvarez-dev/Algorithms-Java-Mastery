# Array Operations

## Algorithms Java Mastery

This document studies the fundamental operations performed on arrays and
explains their algorithmic cost.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
algorithmic reasoning developed throughout this repository.

The previous documents established arrays as:

- indexed data structures;
- fixed-size structures;
- structures with efficient positional access;
- structures whose operations are influenced by their memory organisation.

This document now focuses on the operations performed over arrays and derives
their computational costs from the amount of work required.

The central question addressed throughout this document is:

> **What fundamental operations can be performed on arrays, and how does each
> operation affect computational complexity?**

---

# Purpose

The purpose of this document is to establish a systematic understanding of
array operations before studying more advanced algorithms.

The objective is not to memorise a complexity table.

Instead, the learner should understand the relationship:

```text
Array Operation
        ↓
Required Work
        ↓
Cost Function
        ↓
Asymptotic Complexity
        ↓
Engineering Interpretation
```

This approach follows the complexity-analysis methodology established in
`docs/02-complexity/`.

---

# Learning Objectives

After studying this document, the learner should be able to:

- identify the fundamental operations performed on arrays;
- distinguish direct access from traversal and search;
- implement common operations correctly in Java;
- analyse the computational cost of each operation;
- explain why insertion and deletion may require shifting;
- distinguish logical insertion from physical array resizing;
- analyse copying and transformation operations;
- identify the auxiliary space required by different implementations;
- recognise when an operation is dependent on input position or structure;
- justify the use of arrays according to computational requirements.

These competencies provide the practical foundation for later searching,
sorting, hashing, heaps, and dynamic-data-structure modules.

---

# Fundamental Array Operations

Common array operations include:

```text
Access
        ↓
Update
        ↓
Traversal
        ↓
Search
        ↓
Copy
        ↓
Insert
        ↓
Delete
        ↓
Reverse
        ↓
Rotate
```

The exact complexity depends on:

- the operation;
- the input size;
- the location involved;
- the implementation strategy;
- the auxiliary memory model.

Therefore, complexity should always be stated with its relevant assumptions.

---

# Access

## Definition

Access retrieves an element from a known valid index.

Example:

```text
int value = numbers[3];
```

Conceptually:

```text
Index

0   1   2   3   4

[8][4][7][9][2]
         ↑
       Access
```

---

## Time Complexity

Under the standard array model:

```text
Θ(1)
```

The index is known, so the element can be accessed directly.

---

## Auxiliary Space

The operation requires only a fixed amount of additional working memory:

```text
Θ(1)
```

---

# Update

## Definition

Update replaces the value stored at a known valid index.

Example:

```text
numbers[2] = 50;
```

Before:

```text
[8][4][7][9][2]
```

After:

```text
[8][4][50][9][2]
```

---

## Time Complexity

Only one position is modified:

```text
Θ(1)
```

---

## Auxiliary Space

The operation uses constant additional working memory:

```text
Θ(1)
```

---

# Traversal

## Definition

Traversal processes the elements of an array systematically.

Example:

```text
for (int value : numbers) {
    process(value);
}
```

Conceptually:

```text
[8][4][7][9][2]
 ↓  ↓  ↓  ↓  ↓
```

---

## Time Complexity

If constant work is performed for each of the `n` elements:

```text
n elements
        ×
constant work
        ↓
Θ(n)
```

---

## Auxiliary Space

A simple traversal using a fixed number of variables requires:

```text
Θ(1)
```

auxiliary space.

The array itself is input storage and is not included in the auxiliary-space
bound.

---

# Search

## Definition

Search determines whether a value exists in the array and, depending on the
algorithm, may also return its position.

Searching is different from indexed access:

```text
Known Index
        ↓
Access
```

versus:

```text
Known Value
        ↓
Search
```

---

# Linear Search

Linear search examines elements sequentially.

Example:

```text
[8][4][7][9][2]
      ↓
      7
```

A significant operation is:

```text
Compare Current Element with Target
```

---

## Linear Search Complexity

Best case:

```text
Θ(1)
```

when the target is found immediately.

Worst case:

```text
Θ(n)
```

when the target is at the last relevant position or is absent.

Average-case complexity depends on the probability model.

Under the common model where the target is present and each position is equally
likely:

```text
Θ(n)
```

---

## Linear Search Auxiliary Space

A standard iterative implementation requires:

```text
Θ(1)
```

auxiliary space.

---

# Binary Search

Binary search is appropriate only when the searched range satisfies the
required ordering assumption.

Conceptually:

```text
Search Range
        ↓
Middle Element
        ↓
Discard Half
        ↓
Repeat
```

---

## Binary Search Complexity

Best case:

```text
Θ(1)
```

Worst case:

```text
Θ(log n)
```

Average-case complexity depends on the adopted probability model.

For an iterative implementation:

```text
S_aux(n) ∈ Θ(1)
```

For a recursive implementation:

```text
S_aux(n) ∈ Θ(log n)
```

because of the call stack.

---

# Copy

## Definition

Copying creates another array containing the copied elements.

Example:

```java
int[] copy = Arrays.copyOf(numbers, numbers.length);
```

Conceptually:

```text
Original

[1][2][3][4]

        ↓

Copy

[1][2][3][4]
```

The two array objects are distinct.

---

## Time Complexity

Copying `n` elements requires processing those elements:

```text
Θ(n)
```

---

## Auxiliary Space

Creating a second array of `n` elements requires:

```text
Θ(n)
```

additional storage.

This should not be confused with:

```java
int[] copy = numbers;
```

which only copies the reference and therefore requires:

```text
Θ(1)
```

additional space.

---

# Insert

## Definition

A fixed Java array cannot physically increase its length after creation.

Therefore, “insertion” into an array must be interpreted carefully.

Two common models exist:

### Logical Insertion into Existing Capacity

A portion of the array is considered logically active and unused positions
remain available.

### Insertion into a New Larger Array

A larger array is created and elements are copied or shifted into the new
structure.

For an order-preserving insertion:

```text
Before

[A][B][C][ ][ ]

Insert X at Position 1

        ↓

Shift B and C

        ↓

[A][X][B][C][ ]
```

---

## Time Complexity

If up to `n` elements must be shifted:

```text
Worst Case
        ↓
Θ(n)
```

Insertion near the end may require less work.

Therefore, the position of insertion matters.

---

## Auxiliary Space

If insertion occurs within an existing array with available capacity and only
a fixed number of temporary variables are required:

```text
Θ(1)
```

If a new larger array must be created, the additional storage may be:

```text
Θ(n)
```

Thus, the space complexity depends on the insertion model.

---

# Delete

## Definition

Deleting an element from a fixed array requires defining what happens to the
remaining logical sequence.

If order must be preserved, later elements may need to shift toward the
removed position.

Example:

```text
Before

[10][20][30][40][50]

Delete 20

        ↓

Shift Remaining Elements

        ↓

[10][30][40][50][ ]
```

The physical array length remains unchanged.

The logical number of active elements may decrease.

---

## Time Complexity

If up to `n` elements must be shifted:

```text
Worst Case
        ↓
Θ(n)
```

Deleting near the end may require less shifting.

---

## Auxiliary Space

With an existing array and a fixed number of temporary variables:

```text
Θ(1)
```

If deletion is implemented by constructing a new smaller array, the additional
space may become:

```text
Θ(n)
```

The implementation model must therefore be stated explicitly.

---

# Copy vs Reference Assignment

These operations are fundamentally different.

## Reference Assignment

```java
int[] b = numbers;
```

Conceptually:

```text
numbers ───┐
           ↓
        [1][2][3]
           ↑
b ─────────┘
```

Time:

```text
Θ(1)
```

Space:

```text
Θ(1)
```

No second array is created.

---

## Content Copy

```java
int[] b = Arrays.copyOf(numbers, numbers.length);
```

Conceptually:

```text
numbers → [1][2][3]

b       → [1][2][3]
```

Time:

```text
Θ(n)
```

Auxiliary space:

```text
Θ(n)
```

This distinction is essential in complexity analysis.

---

# Reverse

## Definition

Reversal changes the order of all elements.

Example:

```text
Before

[1][2][3][4][5]

        ↓

After

[5][4][3][2][1]
```

An in-place reversal commonly swaps symmetric positions:

```text
First ↔ Last
Second ↔ Penultimate
...
```

---

## Time Complexity

Although only approximately half of the positions need to be swapped, the
number of operations still grows proportionally to `n`.

Therefore:

```text
Θ(n)
```

---

## Auxiliary Space

An in-place implementation requires only a fixed temporary variable for each
swap:

```text
Θ(1)
```

---

# Rotation

## Definition

Rotation shifts the positions of elements by a specified number of places.

Example:

```text
Before

[1][2][3][4][5]

Rotate Right by 1

        ↓

[5][1][2][3][4]
```

Rotation algorithms may use different strategies.

Examples include:

- repeated single-position shifts;
- temporary arrays;
- reversal-based rotation;
- cyclic replacement.

---

## Time Complexity

An efficient rotation implementation can process the array in:

```text
Θ(n)
```

time.

---

## Auxiliary Space

An in-place rotation can achieve:

```text
Θ(1)
```

auxiliary space.

An implementation that creates an additional array may require:

```text
Θ(n)
```

additional space.

Therefore, the implementation strategy must be documented.

---

# Swap

Although not always listed as an independent high-level array operation,
swapping is a fundamental building block of many array algorithms.

Example:

```text
int temp = numbers[i];
numbers[i] = numbers[j];
numbers[j] = temp;
```

Conceptually:

```text
[A][B]

        ↓

[B][A]
```

For two known valid positions:

```text
Time
        ↓
Θ(1)
```

and:

```text
Auxiliary Space
        ↓
Θ(1)
```

Swapping is heavily used in:

- sorting;
- partitioning;
- reversing;
- rotation.

---

# Shifting

Shifting moves a sequence of elements to new positions.

Example:

```text
[A][B][C][D][ ]
```

shift right:

```text
[ ][A][B][C][D]
```

If `n` elements may need to move:

```text
Θ(n)
```

work may be required.

Shifting is one of the main reasons order-preserving insertion and deletion
can be linear-time operations.

---

# Summary of Array Operations

Under the standard fixed-array model:

| Operation | Typical Time | Typical Auxiliary Space | Notes |
|---|---:|---:|---|
| Access known index | `Θ(1)` | `Θ(1)` | Valid index required |
| Update known index | `Θ(1)` | `Θ(1)` | One position modified |
| Swap | `Θ(1)` | `Θ(1)` | Two known positions |
| Full traversal | `Θ(n)` | `Θ(1)` | Constant work per element |
| Linear search | Best `Θ(1)`, Worst `Θ(n)` | `Θ(1)` | Average depends on model |
| Binary search | Best `Θ(1)`, Worst `Θ(log n)` | `Θ(1)` iterative | Ordered input required |
| Copy `n` elements | `Θ(n)` | `Θ(n)` | Creates another array |
| Reverse | `Θ(n)` | `Θ(1)` in place | Symmetric swaps |
| Rotate | `Θ(n)` | `Θ(1)` in-place possible | Depends on implementation |
| Insert | Worst `Θ(n)` | `Θ(1)` or `Θ(n)` | Depends on capacity model |
| Delete | Worst `Θ(n)` | `Θ(1)` or `Θ(n)` | Depends on implementation |

These values are analytical reference points.

The implementation assumptions should always accompany the final complexity
statement.

---

# Why Complexity Differs Between Operations

The complexity of an array operation follows from the amount of data that must
be processed.

For example:

```text
Known Position
        ↓
Direct Access
        ↓
Θ(1)
```

while:

```text
Every Element
        ↓
Traversal
        ↓
Θ(n)
```

and:

```text
Many Elements Must Move
        ↓
Shifting
        ↓
Θ(n)
```

The objective is to identify the required work before assigning the asymptotic
bound.

---

# Relationship with Memory Layout

The previous document established that arrays use a classical contiguous-storage
model.

This helps explain:

```text
Known Index
        ↓
Direct Position Calculation
        ↓
Θ(1) Access
```

It also helps explain why shifting can become expensive:

```text
Insert or Delete
        ↓
Preserve Order
        ↓
Move Multiple Elements
        ↓
Θ(n)
```

Memory organisation and algorithmic cost are therefore directly related.

---

# Relationship with Complexity

The complete reasoning process is:

```text
Array Operation
        ↓
Identify Significant Work
        ↓
Construct Cost Function
        ↓
Determine Growth
        ↓
Apply Asymptotic Notation
```

For example:

```text
Traversal
        ↓
n element visits
        ↓
T(n) = cn + d
        ↓
Θ(n)
```

This is more rigorous than simply memorising:

```text
Traversal = O(n)
```

---

# Relationship with Foundations

Array operations should still respect the problem-solving methodology introduced
in **Foundations**.

A complete operation analysis may begin with:

```text
Problem
        ↓
Specification
        ↓
Preconditions
        ↓
Postconditions
        ↓
Edge Cases
        ↓
Operation
        ↓
Correctness
        ↓
Complexity
```

For example, a deletion operation may need to specify:

```text
Precondition
        ↓
Valid index
```

and:

```text
Postcondition
        ↓
Remaining logical sequence preserves required order
```

---

# Edge Cases

Array operations often have important boundary conditions.

Examples include:

```text
Empty Array
```

```text
Single Element
```

```text
Insert at First Position
```

```text
Insert at Last Available Position
```

```text
Delete First Element
```

```text
Delete Last Logical Element
```

```text
Reverse Empty or Single-Element Array
```

```text
Rotate by Zero
```

```text
Rotate by Array Length
```

Whether each case is valid depends on the operation's specification.

---

# Complexity and Input Position

Some array operations depend on where the operation occurs.

For example, order-preserving insertion may involve:

```text
Insert at End
        ↓
Few or No Shifts
```

versus:

```text
Insert at Beginning
        ↓
Many Elements Shifted
```

Therefore, it may be useful to distinguish:

```text
Best Case
```

from:

```text
Worst Case
```

when analysing an operation.

---

# Example — Order-Preserving Insertion

Suppose:

```text
[10][20][30][40][ ]
```

and we insert:

```text
25
```

between `20` and `30`.

The following values must shift:

```text
30 → right
40 → right
```

Therefore:

```text
Number of shifted elements
        ↓
Depends on insertion position
```

The worst case occurs near the beginning:

```text
Θ(n)
```

This derives directly from the number of elements moved.

---

# Example — Order-Preserving Deletion

Suppose:

```text
[10][20][30][40][50]
```

and we remove:

```text
20
```

The elements:

```text
30
40
50
```

may need to shift left.

Again:

```text
Number of shifted elements
        ↓
Depends on deletion position
```

The worst-case cost is:

```text
Θ(n)
```

---

# Example — Full Array Copy

For:

```text
n elements
```

a complete copy requires processing:

```text
A[0]
A[1]
...
A[n - 1]
```

Therefore:

```text
Time
        ↓
Θ(n)
```

and if a separate array is created:

```text
Additional Space
        ↓
Θ(n)
```

---

# Example — In-Place Reverse

Consider:

```text
[1][2][3][4][5][6]
```

An in-place reversal performs:

```text
1 ↔ 6
2 ↔ 5
3 ↔ 4
```

Approximately half of the positions participate in swaps.

The number of swaps is proportional to `n`:

```text
Θ(n)
```

The temporary storage remains constant:

```text
Θ(1)
```

This is an example where processing fewer than `n` swaps still produces linear
growth because the number of operations scales proportionally with input size.

---

# Example — Rotation with Additional Storage

One rotation strategy may create a second array:

```text
Original
        ↓
Temporary Array
        ↓
Rotated Result
```

For `n` elements:

```text
Time
        ↓
Θ(n)

Auxiliary Space
        ↓
Θ(n)
```

An in-place rotation can reduce auxiliary space to:

```text
Θ(1)
```

while maintaining:

```text
Θ(n)
```

time.

This is a direct example of a time-space trade-off.

---

# Arrays and Standard Java Operations

Java provides standard utilities for many array operations.

Examples include:

```text
Arrays.copyOf(...)
Arrays.fill(...)
Arrays.equals(...)
Arrays.sort(...)
Arrays.binarySearch(...)
```

The implementation details of these methods should be distinguished from the
abstract array operation being studied.

The relevant analytical question is:

```text
What amount of data must the operation process?
```

---

# Common Mistakes

## Assuming All Operations Are O(1)

Only operations that work on a fixed amount of data may have constant
complexity.

---

## Treating Search as Access

Knowing an index and locating a value are different problems.

---

## Ignoring Shifting

Order-preserving insertion and deletion may require moving many elements.

---

## Treating Insertion as Array Resizing

A fixed Java array cannot change its length.

Growth requires another array or a dynamic collection.

---

## Ignoring Auxiliary Space

An operation implemented using a temporary array may be `Θ(n)` in space even when
an in-place implementation could use `Θ(1)`.

---

## Assuming Reverse Is O(1)

Only a single swap is constant.

Reversing an entire array requires work proportional to its size:

```text
Θ(n)
```

---

## Assuming Every Rotation Uses the Same Space

Some implementations use an additional array.

Others perform rotation in place.

The complexity depends on the chosen implementation.

---

## Using `O` Without Considering Tight Bounds

When a tight bound is justified, prefer:

```text
Θ(...)
```

over a less informative upper bound.

---

# Best Practices

When analysing array operations:

- identify the exact operation;
- define the input-size parameter;
- specify relevant preconditions;
- identify the number of elements processed;
- count shifts and copies explicitly;
- distinguish logical size from physical capacity;
- distinguish reference assignment from content copying;
- analyse auxiliary space separately;
- consider input position when relevant;
- use tight asymptotic bounds when justified;
- document implementation assumptions.

The objective is to explain **why the operation has its computational cost**.

---

# Systematic Operation Analysis

For each array operation, ask:

```text
1. What is the operation?
        ↓
2. What input size is relevant?
        ↓
3. Is the target position known?
        ↓
4. How many elements must be inspected?
        ↓
5. How many elements must be moved?
        ↓
6. Is copying required?
        ↓
7. Is additional storage required?
        ↓
8. Does the cost vary by position?
        ↓
9. What is the cost function?
        ↓
10. What asymptotic bound is justified?
```

This analysis process should become automatic before implementing more complex
array algorithms.

---

# Relationship with Later Modules

The operations introduced here become the building blocks of later algorithms.

```text
Searching
        ↓
Access + Comparison + Traversal
```

```text
Sorting
        ↓
Traversal + Comparison + Swap + Shift
```

```text
Hashing
        ↓
Access + Update
```

```text
Heaps
        ↓
Access + Update + Swap
```

```text
Dynamic Programming
        ↓
Access + Update + Traversal
```

Understanding these primitive operations therefore makes later algorithms
easier to analyse and implement.

---

# Expected Outcome

After completing this document, the learner should be able to move from an
array operation to a justified complexity statement.

For example:

```text
Known Index
        ↓
One Position Accessed
        ↓
Θ(1)
```

```text
Process Every Element
        ↓
n Positions
        ↓
Θ(n)
```

```text
Preserve Order During Insertion
        ↓
Shift Up to n Elements
        ↓
Θ(n)
```

```text
Copy Entire Array
        ↓
Process n Elements
        +
Allocate n-Element Result
        ↓
Time Θ(n)
Space Θ(n)
```

This reasoning process is more important than memorising complexity values.

---

# Key Takeaways

After studying this document, the learner should remember that:

- array operations must be analysed individually;
- access by a known index is `Θ(1)`;
- updating a known index is `Θ(1)`;
- swapping two known positions is `Θ(1)`;
- traversing all `n` elements is `Θ(n)`;
- linear search has worst-case `Θ(n)`;
- binary search has worst-case `Θ(log n)` under its ordering precondition;
- copying `n` elements requires `Θ(n)` time and usually `Θ(n)` additional
  space when a new array is created;
- order-preserving insertion may require `Θ(n)` shifting;
- order-preserving deletion may require `Θ(n)` shifting;
- in-place reversal takes `Θ(n)` time and can use `Θ(1)` auxiliary space;
- rotation can be `Θ(n)` time with either `Θ(1)` or `Θ(n)` auxiliary space
  depending on the implementation;
- fixed Java arrays do not resize automatically;
- complexity values depend on the operation and its implementation model;
- tight bounds should be preferred when they are justified.

Array operations therefore provide the practical connection between the
**abstract array structure**, **memory organisation**, **algorithmic complexity**,
and the more sophisticated algorithms studied later in **Algorithms Java
Mastery**.

---

# Next Document

```text
06-traversal-patterns.md
```

The next document studies reusable **array traversal patterns**.

It introduces:

- forward traversal;
- reverse traversal;
- indexed traversal;
- two-pointer techniques;
- nested traversal;
- prefix-oriented processing;
- window-based traversal where appropriate.

The next central question becomes:

> **How can common traversal patterns be used to design and analyse array
> algorithms systematically?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The analysis of array operations in this document applies the complexity and
problem-solving methodologies established throughout the preceding
**Foundations** and **Complexity** modules.

Java-specific implementation examples are based on the Java language and
standard library model.

Complementary academic and technical references are documented in:

```text
docs/00-project/10-references.md
```

Additional references may be introduced when a particular operation requires
more specialised algorithmic or implementation-level analysis.