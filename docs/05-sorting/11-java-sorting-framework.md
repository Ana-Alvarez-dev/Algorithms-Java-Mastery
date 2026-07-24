# Java Sorting Framework

## Purpose

The purpose of this document is to study how the Java Standard Library implements sorting operations.

After studying the theoretical foundations of sorting algorithms, it is essential to understand how professional Java applications perform sorting in practice. Rather than implementing elementary algorithms directly, Java provides highly optimized sorting mechanisms that have been extensively tested and refined.

This document introduces the classes, interfaces and algorithms used by the Java Collections Framework for sorting arrays and collections.

---

# Learning Objectives

After completing this document, the learner should be able to:

- understand how Java performs sorting;
- distinguish sorting of arrays from sorting of collections;
- explain the roles of `Comparable` and `Comparator`;
- identify the algorithms used internally by Java;
- understand why production software relies on library implementations instead of handwritten sorting algorithms;
- select the appropriate sorting mechanism for different situations.

---

# Sorting in Java

The Java Standard Library provides built-in methods for sorting both arrays and collections.

The two primary entry points are:

```text
Arrays.sort(...)
```

and

```text
Collections.sort(...)
```

Modern Java applications rarely implement sorting algorithms manually unless the objective is educational, experimental or highly specialized.

---

# Sorting Arrays

Arrays are sorted using the `Arrays` utility class.

Examples:

```text
int[] values = {8, 3, 5, 1};

Arrays.sort(values);
```

```text
String[] names = {"Lucas", "Ana", "Pedro"};

Arrays.sort(names);
```

The array is modified in place.

---

# Sorting Collections

Collections are sorted using the `Collections` utility class or the `List.sort()` method.

Example:

```text
List<String> names = new ArrayList<>();

Collections.sort(names);
```

Equivalent modern syntax:

```text
names.sort(null);
```

Both approaches sort the existing list.

---

# Natural Ordering

Objects may define their own default ordering by implementing the `Comparable` interface.

Example:

```java
public class Student implements Comparable<Student> {

    @Override
    public int compareTo(Student other) {
        return this.id.compareTo(other.id);
    }

}
```

The `compareTo()` method defines the natural ordering of the object.

---

# The Comparable Interface

The interface contains a single method:

```text
int compareTo(T other)
```

Its return value follows three rules:

| Result | Meaning |
|---------|---------|
| Negative | Current object comes first |
| Zero | Objects are considered equal |
| Positive | Current object comes after |

This contract allows Java to compare objects consistently.

---

# Custom Ordering with Comparator

Sometimes an object can be ordered in multiple ways.

Instead of modifying the class, Java allows external comparison through the `Comparator` interface.

Example:

```java
Comparator<Employee> bySalary =
        Comparator.comparing(Employee::salary);
```

Now employees are ordered according to salary instead of their natural ordering.

---

# Comparable versus Comparator

| Comparable | Comparator |
|------------|------------|
| Natural ordering | Custom ordering |
| Implemented inside the class | Implemented externally |
| One default order | Multiple possible orders |
| `compareTo()` | `compare()` |

As a general guideline:

- use **Comparable** when the class has a single natural ordering;
- use **Comparator** when different orderings are required.

---

# Chained Comparators

Java allows multiple comparison criteria.

Example:

```java
Comparator<Employee> comparator =
        Comparator.comparing(Employee::department)
                  .thenComparing(Employee::lastName)
                  .thenComparing(Employee::id);
```

This produces a deterministic ordering.

---

# Arrays.sort()

The implementation depends on the element type.

## Primitive Arrays

Examples:

```text
int[]
long[]
double[]
char[]
```

Java uses an optimized **Dual-Pivot Quick Sort** implementation.

Characteristics:

- in-place;
- excellent average performance;
- optimized for primitive values.

---

## Object Arrays

Examples:

```text
String[]
Employee[]
Transaction[]
```

Java uses **TimSort**.

Characteristics:

- stable;
- adaptive;
- optimized for partially ordered data.

---

# Collections.sort()

Collections are sorted using **TimSort** because collections store objects rather than primitive values.

Advantages include:

- stability;
- adaptiveness;
- excellent practical performance;
- efficient handling of partially sorted collections.

---

# TimSort

TimSort is a hybrid sorting algorithm developed for real-world applications.

It combines ideas from:

- Merge Sort;
- Insertion Sort.

General strategy:

```text
Detect Existing Runs
        ↓
Extend Small Runs
        ↓
Merge Runs
```

TimSort takes advantage of existing order already present in many real datasets.

---

# Dual-Pivot Quick Sort

Primitive arrays use Dual-Pivot Quick Sort.

Instead of partitioning around one pivot:

```text
Pivot
```

it partitions around two pivots:

```text
Left Pivot
        ↓
Small Values

Middle Region

Large Values
        ↑
Right Pivot
```

The algorithm reduces comparisons in many practical cases and performs efficiently for primitive arrays.

---

# Why Java Uses Different Algorithms

Different data types have different requirements.

| Data Type | Algorithm |
|-----------|-----------|
| Primitive arrays | Dual-Pivot Quick Sort |
| Object arrays | TimSort |
| Lists | TimSort |

The library selects the algorithm that provides the best balance between:

- speed;
- memory usage;
- stability;
- real-world performance.

---

# Best Practices

Professional Java developers should generally:

- use `Arrays.sort()` for arrays;
- use `List.sort()` or `Collections.sort()` for collections;
- implement `Comparable` only for natural ordering;
- prefer `Comparator` for custom ordering;
- avoid implementing sorting algorithms manually unless necessary.

---

# Engineering Perspective

Understanding sorting algorithms remains important even though Java already provides optimized implementations.

Engineers must know:

- algorithmic complexity;
- stability;
- comparator design;
- ordering semantics;
- performance trade-offs.

This knowledge allows developers to choose the correct library feature and diagnose performance problems when sorting large datasets.

---

# Relationship with Previous Documents

Previous documents studied sorting algorithms individually and compared their theoretical properties.

This document demonstrates how those concepts are applied in professional Java software through the Java Standard Library.

---

# Relationship with Future Documents

The next document focuses on testing sorting algorithms using JUnit and AssertJ.

The objective is to verify correctness, ordering properties and edge cases through automated testing.

---

# Key Takeaways

- Java provides highly optimized sorting implementations.
- `Arrays.sort()` and `Collections.sort()` are the primary sorting APIs.
- Primitive arrays use Dual-Pivot Quick Sort.
- Object arrays and collections use TimSort.
- `Comparable` defines natural ordering.
- `Comparator` defines custom ordering.
- Production software should generally rely on the Java Standard Library instead of handwritten sorting algorithms.

---

# Next Document

```text
12-testing-sorting-algorithms.md
```