# Multidimensional Arrays

## Algorithms Java Mastery

This document introduces **multidimensional arrays** in Java and explains how they extend the concept of one-dimensional arrays to represent more complex data structures.

Many computational problems involve data organised in rows and columns rather than in a simple sequence. Examples include matrices, game boards, spreadsheets, images and graph representations.

Understanding multidimensional arrays is essential before studying graph algorithms, dynamic programming and numerical computing.

The central question addressed throughout this document is:

> **How are multidimensional arrays represented in Java, and when should they be used?**

---

# Purpose

The purpose of this document is to explain the conceptual and practical aspects of multidimensional arrays.

The learner should understand that multidimensional arrays provide a structured way to represent data organised in multiple dimensions while preserving the principles established for one-dimensional arrays.

The expected progression is:

```text
One-Dimensional Array
        ↓
Rows and Columns
        ↓
Two-Dimensional Array
        ↓
Higher Dimensions
        ↓
Practical Applications
```

---

# Learning Objectives

After studying this document, the learner should be able to:

- explain what a multidimensional array is;
- distinguish one-dimensional and multidimensional arrays;
- declare and initialise multidimensional arrays in Java;
- traverse multidimensional arrays correctly;
- analyse their computational complexity;
- recognise common applications in software engineering.

These competencies prepare the learner for more advanced algorithmic topics.

---

# What Is a Multidimensional Array?

A multidimensional array is an array whose elements are themselves arrays.

Conceptually:

```text
Array
        ↓
Array
        ↓
Array
```

The most common case is the **two-dimensional array**, which represents data organised into rows and columns.

---

# Two-Dimensional Arrays

A two-dimensional array can be visualised as a table.

Example:

```text
        Column

        0   1   2

Row 0  [1][2][3]

Row 1  [4][5][6]

Row 2  [7][8][9]
```

Each element is identified by two indexes:

- row;
- column.

---

# Declaring a Two-Dimensional Array

Example:

```java
int[][] matrix;
```

At this point, the array has been declared but no memory has been allocated.

---

# Creating a Two-Dimensional Array

Memory is allocated using the `new` keyword.

Example:

```java
int[][] matrix = new int[3][3];
```

This creates a matrix containing:

- 3 rows;
- 3 columns.

Conceptually:

```text
3 × 3 Matrix

[ ][ ][ ]
[ ][ ][ ]
[ ][ ][ ]
```

---

# Initialising a Two-Dimensional Array

Arrays can also be initialised directly.

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

---

# Accessing Elements

Each element is accessed using two indexes.

Example:

```text
matrix[1][2];
```

Result:

```text
6
```

The first index selects the row.

The second index selects the column.

---

# Modifying Elements

Example:

```text
matrix[0][1] = 50;
```

Result:

```text
[1][50][3]
```

Updating an existing element has:

```text
O(1)
```

time complexity.

---

# Traversing a Matrix

Matrices are typically traversed using nested loops.

Example:

```text
for (int row = 0; row < matrix.length; row++) {
    for (int column = 0; column < matrix[row].length; column++) {
        System.out.println(matrix[row][column]);
    }
}
```

The outer loop iterates over rows.

The inner loop iterates over columns.

---

# Computational Complexity

For a matrix containing:

```text
R rows

C columns
```

Traversing the entire matrix requires:

```text
O(R × C)
```

time complexity.

Space complexity remains proportional to the number of stored elements.

---

# Jagged Arrays

Unlike many programming languages, Java allows rows to have different lengths.

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

This structure is called a **jagged array** (or **ragged array**).

---

# Why Jagged Arrays Exist

Jagged arrays improve memory efficiency when rows naturally contain different numbers of elements.

Typical applications include:

- irregular datasets;
- graph adjacency lists;
- hierarchical structures.

---

# Three-Dimensional Arrays

Java also supports higher dimensions.

Example:

```text
int[][][] cube = new int[4][5][6];
```

Conceptually:

```text
Layer

↓

Matrix

↓

Row

↓

Column
```

Although valid, higher-dimensional arrays are less common in everyday software development.

---

# Typical Applications

Multidimensional arrays are frequently used in:

- mathematical matrices;
- image processing;
- spreadsheets;
- game boards;
- chess engines;
- Sudoku solvers;
- graph adjacency matrices;
- scientific computing;
- simulations.

---

# Relationship with Algorithms

Many classical algorithms operate on multidimensional arrays.

Examples include:

- matrix multiplication;
- matrix rotation;
- flood fill;
- pathfinding;
- dynamic programming tables.

Understanding multidimensional arrays therefore prepares the learner for more advanced algorithmic techniques.

---

# Relationship with Memory Layout

Although conceptually represented as tables, Java implements multidimensional arrays as arrays of arrays.

Conceptually:

```text
Matrix

↓

Row Reference

↓

Row Reference

↓

Row Reference
```

Each row is itself an independent array object.

This differs from lower-level languages that may allocate matrices as one contiguous memory block.

---

# Common Mistakes

Avoid the following errors.

---

## Confusing Rows and Columns

Incorrect index ordering frequently produces logical errors.

Always remember:

```text
matrix[row][column]
```

---

## Assuming Every Row Has the Same Length

Jagged arrays allow different row sizes.

Always use:

```text
matrix[row].length
```

instead of assuming all rows contain the same number of columns.

---

## Forgetting Nested Loops

A single loop cannot traverse every element of a two-dimensional array.

Nested iteration is required.

---

# Best Practices

When working with multidimensional arrays:

- choose meaningful variable names such as `row` and `column`;
- use enhanced loops when indexes are unnecessary;
- validate indexes carefully;
- avoid hard-coded dimensions;
- document traversal strategies for complex algorithms.

---

# Key Takeaways

The learner should remember the following principles:

- A multidimensional array is an array whose elements are arrays.
- Two-dimensional arrays organise data into rows and columns.
- Elements are accessed using multiple indexes.
- Matrix traversal generally requires nested loops.
- Traversing a matrix has O(R × C) time complexity.
- Java implements multidimensional arrays as arrays of arrays.
- Jagged arrays allow rows of different lengths.
- Multidimensional arrays support numerous algorithms in Computer Science.

Understanding multidimensional arrays extends the learner's ability to model more complex computational problems and prepares the foundation for future modules involving graphs, matrices, dynamic programming and scientific computing throughout the **Algorithms Java Mastery** repository.