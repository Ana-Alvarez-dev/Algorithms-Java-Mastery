# Java Arrays

## Algorithms Java Mastery

This document explains how the Java programming language implements arrays.

While the previous documents introduced arrays as an abstract data structure and explained their organisation in memory, this document focuses on their practical implementation using Java.

Arrays are one of the most fundamental language features in Java and provide the basis for many data structures, algorithms and standard library classes.

The central question addressed throughout this document is:

> **How does Java implement arrays, and how should software engineers use them correctly?**

---

# Purpose

The purpose of this document is to study arrays as implemented by the Java platform.

The learner should understand both the syntax and the behaviour of Java arrays while maintaining the algorithmic reasoning developed throughout the repository.

The expected progression is:

```text
Array Abstraction
        ↓
Java Syntax
        ↓
Array Creation
        ↓
Array Manipulation
        ↓
Engineering Best Practices
```

---

# Learning Objectives

After studying this document, the learner should be able to:

- declare arrays correctly;
- create arrays using different techniques;
- initialise arrays;
- access and modify elements;
- understand array references;
- distinguish primitive arrays from object arrays;
- use the Arrays utility class;
- apply Java best practices when working with arrays.

These competencies establish the practical foundation required for implementing algorithms in Java.

---

# Arrays in Java

In Java, an array is an object that stores a fixed number of elements of the same type.

Every array has:

- a type;
- a fixed length;
- indexed access;
- contiguous logical organisation.

Example:

```java
int[] numbers;
```

---

# Declaring an Array

Declaring an array defines the variable that will reference the array object.

Example:

```java
int[] numbers;
```

or

```java
String[] names;
```

At this point, no memory for the elements has been allocated.

---

# Creating an Array

Memory is allocated using the `new` keyword.

Example:

```java
int[] numbers = new int[5];
```

This creates an array capable of storing five integers.

Conceptually:

```text
Index

0   1   2   3   4

↓

[ ][ ][ ][ ][ ]
```

---

# Declaration and Creation Together

The declaration and allocation are commonly performed in a single statement.

Example:

```java
double[] values = new double[10];
```

---

# Initialising an Array

Arrays can also be initialised directly.

Example:

```java
int[] numbers = {10, 20, 30, 40, 50};
```

Conceptually:

```text
Index

0   1   2   3   4

↓

[10][20][30][40][50]
```

---

# Accessing Elements

Elements are accessed using their index.

Example:

```text
System.out.println(numbers[2]);
```

Output:

```text
30
```

Array access executes in constant time:

```text
O(1)
```

---

# Modifying Elements

Individual elements can be updated directly.

Example:

```text
numbers[1] = 99;
```

Result:

```text
[10][99][30][40][50]
```

Updating an existing element also has:

```text
O(1)
```

complexity.

---

# Array Length

Every Java array exposes the `length` field.

Example:

```text
System.out.println(numbers.length);
```

Output:

```text
5
```

The `length` field is not a method.

Correct:

```text
numbers.length
```

Incorrect:

```text
numbers.length()
```

---

# Primitive Arrays

Primitive arrays store primitive values directly.

Examples:

```text
int[]
```

```text
double[]
```

```text
char[]
```

```text
boolean[]
```

These arrays provide efficient memory usage.

---

# Object Arrays

Arrays may also store object references.

Example:

```text
Student[] students;
```

Conceptually:

```text
Reference

↓

Reference

↓

Reference
```

Each position stores a reference to an object rather than the object itself.

---

# Default Values

When an array is created, Java automatically assigns default values.

| Type | Default Value |
|------|---------------|
| int | 0 |
| double | 0.0 |
| boolean | false |
| char | '\u0000' |
| Object | null |

Understanding default values helps prevent unexpected behaviour.

---

# Iterating Through an Array

Traditional loop:

```text
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

Enhanced for loop:

```text
for (int number : numbers) {
    System.out.println(number);
}
```

Use the enhanced loop when the index is not required.

---

# Copying Arrays

Java provides several ways to copy arrays.

Example:

```text
int[] copy = Arrays.copyOf(numbers, numbers.length);
```

This creates a new independent array.

---

# Filling Arrays

The `Arrays.fill()` method assigns the same value to every position.

Example:

```text
Arrays.fill(numbers, 0);
```

Result:

```text
[0][0][0][0][0]
```

---

# Sorting Arrays

The Java Standard Library provides:

```text
Arrays.sort(numbers);
```

This sorts the array efficiently using highly optimised algorithms.

---

# Binary Search

The `Arrays` utility class also includes binary search.

Example:

```text

Arrays.binarySearch(numbers, 40);
```

The array must already be sorted.

---

# Comparing Arrays

Use:

```text
Arrays.equals(array1, array2);
```

instead of:

```text
array1 == array2
```

The first compares contents.

The second compares references.

---

# Arrays and References

Arrays are objects.

Therefore:

```text
int[] a = numbers;
```

does not create a copy.

Instead:

```text
numbers

↓

Array

↑

a
```

Both variables reference the same array.

---

# Common Mistakes

Avoid the following errors.

---

## Accessing Invalid Indexes

Example:

```text
numbers[10];
```

Result:

```text
ArrayIndexOutOfBoundsException
```

---

## Forgetting Array Length

Incorrect:

```text
i <= numbers.length
```

Correct:

```text
i < numbers.length
```

---

## Comparing Arrays Incorrectly

Incorrect:

```text
array1 == array2
```

Correct:

```text
Arrays.equals(array1, array2)
```

---

## Assuming Arrays Resize Automatically

Arrays never change size.

Dynamic resizing is provided by classes such as `ArrayList`.

---

# Relationship with the Collections Framework

Arrays provide fixed-size storage.

The Java Collections Framework builds more flexible data structures on top of similar concepts.

Example:

```text
Array
        ↓
ArrayList
```

The next repository modules will explore these abstractions in detail.

---

# Best Practices

When working with Java arrays:

- choose arrays only when the size is known;
- prefer enhanced for loops when modification is unnecessary;
- validate indexes carefully;
- use the `Arrays` utility class instead of implementing common operations manually;
- avoid exposing mutable arrays directly from APIs;
- document complexity when implementing algorithms.

---

# Key Takeaways

The learner should remember the following principles:

- Arrays in Java are objects.
- Every array has a fixed size.
- Elements are accessed through zero-based indexes.
- Random access executes in O(1).
- Primitive arrays store values directly.
- Object arrays store references.
- The `Arrays` utility class provides common operations.
- Arrays do not resize automatically.
- Arrays form the foundation of many classes in the Java Collections Framework.

Understanding Java arrays provides the practical knowledge required to implement efficient algorithms while maintaining the theoretical foundations established throughout the **Algorithms Java Mastery** repository.