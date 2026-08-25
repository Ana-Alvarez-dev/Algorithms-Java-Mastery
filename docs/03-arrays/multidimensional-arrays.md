# Multidimensional Arrays

## Algorithms Java Mastery

This document introduces **multidimensional arrays in Java** and explains how
arrays can be combined to represent data organised across multiple dimensions.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
algorithmic reasoning developed throughout this repository.

The previous documents established:

- the array as an abstract data structure;
- indexed access;
- fixed-size storage;
- the classical memory-layout model;
- Java's concrete array representation.

This document extends those concepts to structures involving multiple indexes.

The central question addressed throughout this document is:

> **How does Java represent multidimensional arrays, and how does their
> structure affect algorithm design, memory usage, and computational
> complexity?**

---

# Purpose

The purpose of this document is to establish a rigorous understanding of
multidimensional arrays before applying them to matrix algorithms, graph
representations, dynamic programming, simulations, and other computational
problems.

The conceptual progression is:

```text
One-Dimensional Array
        ↓
Array of Arrays
        ↓
Rows and Columns
        ↓
Multidimensional Structure
        ↓
Traversal
        ↓
Complexity Analysis
        ↓
Algorithmic Applications
```

The objective is not simply to learn the syntax:

```text
int[][]
```

but to understand the underlying data structure and its consequences.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define a multidimensional array;
- explain the relationship between one-dimensional and multidimensional arrays;
- explain why Java multidimensional arrays are arrays of arrays;
- declare and initialise two-dimensional arrays;
- access and modify elements using multiple indexes;
- distinguish rectangular and jagged arrays;
- traverse multidimensional arrays correctly;
- define appropriate input-size parameters;
- derive the time complexity of multidimensional traversal;
- analyse auxiliary-space requirements;
- recognise common applications of multidimensional arrays;
- identify common implementation and indexing errors.

These competencies prepare the learner for later modules involving matrices,
graphs, dynamic programming, and other multidimensional computational
structures.

---

# What Is a Multidimensional Array?

A multidimensional array is an array structure in which elements are organised
across more than one logical dimension.

In Java, a multidimensional array is represented as an **array whose elements
are themselves arrays**.

Conceptually:

```text
Array
        ↓
Row Array
        ↓
Element
```

For a two-dimensional structure:

```text
Matrix
        │
        ├── Row 0
        ├── Row 1
        └── Row 2
```

This representation allows data to be accessed using multiple indexes.

---

# Two-Dimensional Arrays

A two-dimensional array is commonly used to represent data organised into rows
and columns.

For example:

```text
        Column
         0   1   2

Row 0   [1][2][3]

Row 1   [4][5][6]

Row 2   [7][8][9]
```

Each element is identified by:

```text
matrix[row][column]
```

Therefore:

```text
First Index
        ↓
Row

Second Index
        ↓
Column
```

---

# Declaring a Two-Dimensional Array

A two-dimensional array variable can be declared as:

```text
int[][] matrix;
```

This statement declares a variable capable of referring to an array whose
elements are arrays of `int`.

It does not by itself create the row or column storage.

---

# Creating a Rectangular Array

A rectangular two-dimensional structure can be created with:

```text
int[][] matrix = new int[3][4];
```

This creates:

```text
3 rows
4 columns per row
```

Conceptually:

```text
[ ][ ][ ][ ]
[ ][ ][ ][ ]
[ ][ ][ ][ ]
```

The total number of element positions is:

```text
3 × 4 = 12
```

---

# Initialising a Two-Dimensional Array

Java also supports array initialisers.

Example:

```text
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

Conceptually:

```text
[1][2][3]
[4][5][6]
[7][8][9]
```

The outer array contains three row arrays.

Each row contains three integers.

---

# Accessing an Element

An element is accessed using two indexes.

Example:

```text
int value = matrix[1][2];
```

For:

```text
[1][2][3]
[4][5][6]
[7][8][9]
```

the result is:

```text
6
```

because:

```text
matrix[1]
        ↓
[4][5][6]

matrix[1][2]
        ↓
6
```

---

# Modifying an Element

Individual elements can be updated directly.

Example:

```text
matrix[0][1] = 50;
```

The matrix becomes:

```text
[1][50][3]
[4][5][6]
[7][8][9]
```

Accessing a known row and column is constant-time under the standard array
model, assuming the required indexes are already known and valid.

Therefore:

```text
Element Access
        ↓
Θ(1)
```

---

# Array Lengths

For:

```text
int[][] matrix = new int[3][4];
```

the outer array length is:

```text
matrix.length
```

which gives:

```text
3
```

The length of a specific row is:

```text
matrix[row].length
```

which gives:

```text
4
```

This distinction is essential because Java multidimensional arrays are arrays
of arrays.

---

# Rows and Columns

For a rectangular matrix:

```text
R = number of rows
C = number of columns per row
```

The total number of stored elements is:

```text
R × C
```

This means that complexity analysis should not automatically force a
two-dimensional problem into a single parameter `n`.

A more precise model is:

```text
T(R, C)
```

---

# Multidimensional Arrays Are Arrays of Arrays

The Java representation is conceptually:

```text
matrix
   ↓
┌─────────┬─────────┬─────────┐
│ row ref │ row ref │ row ref │
└────┬────┴────┬────┴────┬────┘
     ↓         ↓         ↓
   Row 0     Row 1     Row 2
```

Each row is itself an array object.

Therefore, a Java two-dimensional array is not necessarily one single flat
memory block containing every element.

This distinction is important when comparing Java with lower-level
representations that may store matrix elements in one contiguous region.

---

# Rectangular Arrays

A rectangular array has rows with equal lengths.

Example:

```text
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

The structure is:

```text
3 × 3
```

Every row contains the same number of elements.

This representation is useful for:

- matrices;
- game boards;
- tables;
- grid algorithms;
- image-like data;
- dynamic-programming tables.

---

# Jagged Arrays

Java also allows rows to have different lengths.

Example:

```text
int[][] data = {
    {1, 2},
    {3, 4, 5},
    {6}
};
```

Conceptually:

```text
[1][2]
[3][4][5]
[6]
```

This is commonly called a **jagged** or **ragged** array.

The outer array still contains row arrays, but those row arrays do not need to
have identical lengths.

---

# Why Jagged Arrays Matter

Jagged arrays are useful when the number of elements naturally differs between
rows.

Examples may include:

- irregular tabular data;
- adjacency-list-like representations;
- grouped data;
- hierarchical structures.

They can avoid allocating unused positions that would exist in a rectangular
representation.

The appropriate representation depends on the computational problem.

---

# Traversing a Rectangular Array

A common approach is to use nested loops:

```text
for (int row = 0; row < matrix.length; row++) {
    for (int column = 0; column < matrix[row].length; column++) {
        process(matrix[row][column]);
    }
}
```

The outer loop processes rows.

The inner loop processes the elements within each row.

Conceptually:

```text
Row 0
    ↓
Columns

Row 1
    ↓
Columns

Row 2
    ↓
Columns
```

---

# Traversing with Enhanced `for` Loops

When indexes are not required, enhanced loops may improve readability:

```text
for (int[] row : matrix) {
    for (int value : row) {
        process(value);
    }
}
```

This approach is particularly useful when the algorithm only needs the values.

Indexed loops remain appropriate when:

- row positions matter;
- column positions matter;
- neighbouring positions are accessed;
- modifications depend on coordinates.

---

# Traversal Complexity

For a rectangular matrix with:

```text
R rows
C columns
```

and constant work per element, a complete traversal processes:

```text
R × C
```

elements.

Therefore:

```text
T(R, C) ∈ Θ(RC)
```

This is more precise than writing simply:

```text
Θ(n²)
```

because the matrix may have different row and column dimensions.

If the problem specifically defines:

```text
R = C = n
```

then:

```text
Θ(RC)
        ↓
Θ(n²)
```

The substitution should only be made when the relationship is justified.

---

# Traversal of a Jagged Array

For a jagged array, let:

```text
R = number of rows
```

and let:

```text
Lᵢ = length of row i
```

Then the total number of elements is:

```text
L₀ + L₁ + ... + Lᵣ₋₁
```

A complete traversal therefore has time complexity proportional to the total
number of stored elements:

```text
T ∈ Θ(Σ Lᵢ)
```

This is more precise than assuming every row has the same length.

---

# Space Complexity of Multidimensional Arrays

A rectangular array containing:

```text
R × C
```

elements requires storage proportional to the number of stored positions.

Conceptually:

```text
R rows
×
C elements per row
        ↓
Θ(RC)
```

for the array's element storage.

For Java, the complete concrete memory footprint also includes object and
runtime representation overhead.

Therefore:

```text
Theoretical Element Storage
        ≠
Exact JVM Memory Consumption
```

---

# Auxiliary Space During Traversal

A simple traversal such as:

```text
for (int row = 0; row < matrix.length; row++) {
    for (int column = 0; column < matrix[row].length; column++) {
        process(matrix[row][column]);
    }
}
```

may require only a fixed number of additional variables.

Therefore, excluding the input matrix itself:

```text
S_aux ∈ Θ(1)
```

under the standard model.

This distinction is important:

```text
Input Storage
        ↓
Θ(RC)

Auxiliary Traversal Space
        ↓
Θ(1)
```

---

# Matrix Operations

Multidimensional arrays provide the representation required by many matrix
algorithms.

Examples include:

- matrix traversal;
- matrix addition;
- matrix transposition;
- matrix rotation;
- matrix multiplication;
- row and column aggregation.

The complexity of each operation depends on the exact algorithm and matrix
dimensions.

The representation alone does not determine the final complexity.

---

# Example — Matrix Addition

Suppose two matrices have dimensions:

```text
R × C
```

and the algorithm computes:

```text
result[row][column]
    =
a[row][column] + b[row][column]
```

for every position.

Every stored position must be visited.

Therefore:

```text
T(R, C) ∈ Θ(RC)
```

assuming constant work per position.

---

# Example — Matrix Transposition

For a matrix with:

```text
R rows
C columns
```

a complete transpose processes each matrix position.

A straightforward implementation may therefore require:

```text
Θ(RC)
```

time.

The auxiliary-space complexity depends on whether the transpose is performed:

- in place;
- using a new matrix;
- under square or rectangular dimensions.

The implementation strategy must therefore be specified before assigning a
space bound.

---

# Example — Matrix Multiplication

For matrices where the standard multiplication requires three nested
dimensions:

```text
A: R × K
B: K × C
```

the conventional algorithm performs work proportional to:

```text
R × K × C
```

Therefore:

```text
T(R, K, C) ∈ Θ(RKC)
```

For square matrices where:

```text
R = K = C = n
```

this becomes:

```text
Θ(n³)
```

This example demonstrates why multiple input-size parameters are important.

---

# Multidimensional Arrays and Algorithms

Multidimensional arrays appear throughout algorithm design.

Examples include:

```text
Matrix
        ↓
Numerical Algorithms
```

```text
Grid
        ↓
Pathfinding
```

```text
Table
        ↓
Dynamic Programming
```

```text
Matrix
        ↓
Graph Adjacency Representation
```

```text
3D Structure
        ↓
Simulation
```

The data structure provides the storage model.

The algorithm determines how that storage is processed.

---

# Multidimensional Arrays and Dynamic Programming

Dynamic programming frequently stores results in one-dimensional or
multidimensional tables.

Conceptually:

```text
Subproblem State
        ↓
Index or Coordinate
        ↓
Stored Result
```

For example:

```text
dp[i][j]
```

may represent a state depending on two parameters.

The dimensions should correspond to the actual state variables of the
algorithm.

This makes multidimensional arrays an important bridge toward the dynamic
programming module.

---

# Multidimensional Arrays and Graphs

A graph may be represented using an adjacency matrix:

```text
matrix[u][v]
```

where the position represents the relationship between two vertices.

For a graph with:

```text
V vertices
```

an adjacency matrix contains:

```text
V × V
```

positions.

Therefore:

```text
Storage
        ↓
Θ(V²)
```

This may be appropriate when dense graph representation or constant-time
adjacency lookup is important.

The graph module will examine the trade-offs against adjacency-list
representations.

---

# Multidimensional Arrays and Images

Images can conceptually be represented as grids.

For example:

```text
Pixel[row][column]
```

Each coordinate identifies a spatial location.

For colour images, an additional dimension may represent channels:

```text
Pixel[row][column][channel]
```

The appropriate representation depends on the application and data model.

This example illustrates why multidimensional structures are useful beyond
purely mathematical matrices.

---

# Three-Dimensional Arrays

Java supports arrays with more than two dimensions.

Example:

```text
int[][][] cube = new int[4][5][6];
```

Conceptually:

```text
Dimension 1
        ↓
Layers

Dimension 2
        ↓
Rows

Dimension 3
        ↓
Columns
```

The total number of positions is:

```text
4 × 5 × 6
```

or generally:

```text
D₁ × D₂ × D₃
```

when all dimensions are explicitly defined.

---

# Higher-Dimensional Arrays

Java can represent arrays with additional dimensions.

For example:

```text
int[][][][] data;
```

However, increasing dimensionality increases:

- cognitive complexity;
- indexing complexity;
- memory requirements;
- traversal complexity.

Therefore, higher-dimensional arrays should be used only when they represent
the computational problem clearly.

---

# Multidimensional Arrays and Memory Layout

Unlike a conceptual flat matrix model, Java's multidimensional arrays are
arrays of arrays.

For:

```text
int[][] matrix = new int[3][4];
```

the outer array contains references to row arrays.

Conceptually:

```text
matrix
  ↓
[row 0 reference]
[row 1 reference]
[row 2 reference]

row 0 → [ ][ ][ ][ ]
row 1 → [ ][ ][ ][ ]
row 2 → [ ][ ][ ][ ]
```

Therefore, the entire matrix should not automatically be described as one
single contiguous block.

This distinction is especially important when discussing:

- cache locality;
- jagged arrays;
- row traversal;
- Java runtime representation.

---

# Row-Major Intuition vs Java Representation

In lower-level discussions, programmers sometimes describe matrix storage using
row-major or column-major layouts.

For Java's `int[][]` representation:

```text
array of row arrays
```

is the more accurate language-level model.

A nested traversal such as:

```text
for (int row = 0; row < matrix.length; row++) {
    for (int column = 0; column < matrix[row].length; column++) {
        process(matrix[row][column]);
    }
}
```

processes each row sequentially.

This is useful for practical locality considerations, but the exact runtime
behaviour depends on the JVM and hardware.

---

# Memory Locality

Sequential traversal of a row can benefit from spatial locality because nearby
elements in that row are typically accessed consecutively.

Conceptually:

```text
Row
 ↓
[0][1][2][3][4]
```

may have favourable local access behaviour.

However:

```text
Cache Locality
        ↓
Practical Performance
```

must remain distinct from:

```text
Θ(RC)
        ↓
Asymptotic Complexity
```

The asymptotic traversal cost does not change because of cache behaviour.

---

# Rectangular vs Jagged Arrays

| Property | Rectangular | Jagged |
|---|---|---|
| Row lengths | Equal | May differ |
| Representation | Array of equal-length rows | Array of variable-length rows |
| Typical use | Matrices, grids | Irregular data |
| Traversal model | `R × C` | Sum of row lengths |
| Memory usage | Predictable from dimensions | Depends on each row |

Choosing between them should depend on the structure of the data.

---

# Common Mistakes

## Confusing `matrix.length` with Column Count

In:

```text
int[][] matrix = new int[3][4];
```

```text
matrix.length
```

is:

```text
3
```

while:

```text
matrix[0].length
```

is:

```text
4
```

The first identifies the number of rows.

The second identifies the length of a particular row.

---

## Assuming All Rows Have Equal Length

Jagged arrays allow:

```text
int[][] data = {
    {1, 2},
    {3, 4, 5},
    {6}
};
```

Therefore, use:

```text
matrix[row].length
```

when iterating over each row.

---

## Reversing Row and Column Indexes

The conventional representation is:

```text
matrix[row][column]
```

Confusing the two coordinates can produce incorrect results or invalid index
access.

---

## Using Hard-Coded Dimensions

Avoid:

```text
for (int i = 0; i < 3; i++) {
    ...
}
```

when the dimension is dynamic.

Prefer:

```text
for (int i = 0; i < matrix.length; i++) {
    ...
}
```

and:

```text
for (int j = 0; j < matrix[i].length; j++) {
    ...
}
```

---

## Assuming a Java Matrix Is One Flat Block

A Java `int[][]` is an array of row arrays.

Do not automatically apply assumptions from a flat native matrix representation.

---

## Assuming Nested Loops Automatically Mean `Θ(n²)`

For:

```text
R rows
C columns
```

the general traversal result is:

```text
Θ(RC)
```

Only when:

```text
R = C = n
```

does this become:

```text
Θ(n²)
```

---

# Best Practices

When working with multidimensional arrays:

- define every dimension explicitly;
- use `row` and `column` names when appropriate;
- use `matrix.length` for the number of rows;
- use `matrix[row].length` for the current row length;
- support jagged structures when the problem requires them;
- avoid hard-coded dimensions;
- derive complexity from the actual dimensions;
- preserve multiple parameters when they are independent;
- distinguish Java's array-of-arrays representation from flat matrix models;
- use nested loops carefully;
- document the coordinate conventions used by algorithms.

The objective is to make the representation and algorithmic assumptions
explicit.

---

# Systematic Multidimensional-Array Analysis

When analysing a multidimensional-array problem, ask:

```text
1. What does each dimension represent?
        ↓
2. Is the structure rectangular or jagged?
        ↓
3. What are the size parameters?
        ↓
4. How are elements indexed?
        ↓
5. Which dimensions are traversed?
        ↓
6. Is every stored element processed?
        ↓
7. Are there nested computations?
        ↓
8. What is the time-cost function?
        ↓
9. What auxiliary memory is required?
        ↓
10. Does the Java representation affect practical performance?
```

This follows the methodology established throughout the **Foundations** and
**Complexity** modules.

---

# Relationship with Foundations

Multidimensional array algorithms continue to follow the problem-solving
methodology:

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
Strategy
        ↓
Correctness
        ↓
Complexity
        ↓
Java Implementation
        ↓
Testing
```

Examples of relevant contracts include:

```text
Matrix dimensions are compatible.
```

or:

```text
Row index is valid.
Column index is valid.
```

The exact contract depends on the algorithm.

---

# Relationship with Complexity

Multidimensional arrays reinforce the importance of multiple input parameters.

Examples:

```text
Matrix Traversal
        ↓
Θ(RC)
```

```text
Matrix Multiplication
        ↓
Θ(RKC)
```

```text
Square Matrix
R = C = n
        ↓
Θ(n²) for full traversal
```

The correct expression depends on the problem's dimensions and assumptions.

---

# Relationship with Java Arrays

This document extends the previous Java-array model:

```text
Java Array
        ↓
Array of Arrays
        ↓
Two-Dimensional Structure
```

The next array documents can use these concepts when studying:

- array operations;
- traversal patterns;
- matrix algorithms;
- complexity analysis;
- problem-solving strategies.

---

# Relationship with Later Repository Modules

Multidimensional arrays prepare the learner for:

```text
Graphs
        ↓
Adjacency Matrices
```

```text
Dynamic Programming
        ↓
Two-Dimensional State Tables
```

```text
Numerical Algorithms
        ↓
Matrix Computation
```

```text
Searching and Traversal
        ↓
Grid-Based Problems
```

```text
Simulation
        ↓
Multidimensional State Representation
```

The concepts introduced here therefore extend well beyond the Arrays module.

---

# Expected Outcome

After completing this document, the learner should be able to move between
three levels of reasoning:

```text
Abstract Structure
        ↓
Multidimensional Indexed Data
```

```text
Java Representation
        ↓
Array of Arrays
```

```text
Algorithmic Analysis
        ↓
Multiple Input Parameters
        ↓
Time and Space Complexity
```

The learner should understand that:

- a two-dimensional Java array is an array of arrays;
- rows and columns are represented through multiple indexes;
- rectangular and jagged structures have different properties;
- complete traversal depends on the total number of stored elements;
- complexity should preserve independent dimensions;
- Java's multidimensional representation should not automatically be treated
  as one flat contiguous matrix.

---

# Key Takeaways

After studying this document, the learner should remember that:

- multidimensional arrays extend the indexed-array concept across multiple
  dimensions;
- Java represents multidimensional arrays as arrays of arrays;
- `matrix[row][column]` identifies an element through two indexes;
- `matrix.length` identifies the number of rows;
- `matrix[row].length` identifies the length of a particular row;
- rectangular arrays have equal row lengths;
- jagged arrays may have different row lengths;
- full traversal of an `R × C` rectangular matrix is `Θ(RC)`;
- for a square matrix where `R = C = n`, full traversal is `Θ(n²)`;
- matrix multiplication may require three independent dimensions, such as
  `Θ(RKC)`;
- input storage and auxiliary processing memory should be analysed separately;
- multidimensional Java arrays should not automatically be treated as one flat
  contiguous block;
- multidimensional arrays are useful for matrices, grids, graph
  representations, dynamic programming, simulations, and scientific
  computation.

Multidimensional arrays therefore extend the fundamental array model from
**one-dimensional indexed storage** to **structured multidimensional data**.

They provide an important foundation for understanding matrices, graph
representations, dynamic-programming tables, and other algorithms that operate
on multiple dimensions throughout the **Algorithms Java Mastery** repository.

---

# Next Document

```text
05-array-operations.md
```

The next document analyses the fundamental operations that can be performed on
arrays.

It develops:

- indexed access;
- updates;
- traversal;
- searching;
- insertion;
- deletion;
- copying;
- shifting;
- operation-specific complexity.

The next central question becomes:

> **How do the fundamental operations performed on arrays affect computational
> cost and algorithm design?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The conceptual treatment of arrays, matrices, indexed structures, and
algorithmic complexity is complemented by official Java documentation for
multidimensional array semantics and language-specific behaviour.

Additional academic, mathematical, and technical references are documented in:

```text
docs/00-project/10-references.md
```

Additional references may be introduced in later modules when matrix
algorithms, graph representations, dynamic programming, or multidimensional
memory models require greater theoretical depth.