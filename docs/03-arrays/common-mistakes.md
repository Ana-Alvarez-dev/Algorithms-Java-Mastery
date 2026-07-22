# Common Mistakes

## Algorithms Java Mastery

This document presents the most common mistakes encountered when working with arrays.

Many programming errors do not originate from misunderstanding Java syntax but from incorrect reasoning about indexes, memory organisation, traversal strategies and algorithm design.

Recognising these mistakes early helps software engineers write safer, more efficient and more maintainable code.

The central question addressed throughout this document is:

> **What are the most common mistakes when working with arrays, and how can they be avoided?**

---

# Purpose

The purpose of this document is to identify, explain and prevent the most frequent mistakes related to array manipulation.

Rather than simply listing errors, each section explains:

- why the mistake occurs;
- its consequences;
- how to avoid it;
- the recommended engineering practice.

The expected progression is:

```text
Mistake
        ↓
Cause
        ↓
Consequence
        ↓
Solution
        ↓
Best Practice
```

Learning from common mistakes reduces debugging time and improves software quality.

---

# Learning Objectives

After studying this document, the learner should be able to:

- recognise common programming mistakes involving arrays;
- understand why they occur;
- prevent runtime errors;
- write safer Java implementations;
- apply defensive programming practices.

---

# Mistake 1 — Off-by-One Errors

## Description

The most common array error is accessing one position beyond the valid range.

Incorrect example:

```text
for (int i = 0; i <= numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

---

## Why It Happens

The last valid index is:

```text
length - 1
```

Using:

```text
<=
```

causes the loop to execute one extra iteration.

---

## Consequence

```text
ArrayIndexOutOfBoundsException
```

---

## Correct Implementation

```text
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

---

# Mistake 2 — Confusing Length with the Last Index

An array of length:

```text
5
```

contains the following indexes:

```text
0 1 2 3 4
```

The last valid index is **not** the length.

Correct formula:

```text
length - 1
```

---

# Mistake 3 — Accessing Invalid Positions

Incorrect example:

```text
numbers[-1];
```

or

```text
numbers[100];
```

when the array contains fewer elements.

---

## Consequence

```text
ArrayIndexOutOfBoundsException
```

---

## Best Practice

Always validate indexes before accessing an element.

---

# Mistake 4 — Forgetting Null References

Declaring an array does not create it.

Example:

```text
int[] numbers;
```

At this point:

```text
numbers == null
```

Attempting to access an element causes:

```text
NullPointerException
```

---

## Correct Implementation

```java
int[] numbers = new int[10];
```

---

# Mistake 5 — Assuming Arrays Resize Automatically

Java arrays have a fixed size.

Incorrect assumption:

```text
Insert another element
↓

Array grows automatically
```

This behaviour belongs to dynamic data structures such as `ArrayList`, not arrays.

---

# Mistake 6 — Comparing Arrays with ==

Incorrect:

```text
array1 == array2
```

This compares references.

Correct:

```text
Arrays.equals(array1, array2);
```

This compares the contents.

---

# Mistake 7 — Forgetting That Arrays Are Objects

Assignment does not copy an array.

Example:

```text
int[] copy = numbers;
```

Conceptually:

```text
numbers
      ↓
    Array
      ↑
copy
```

Both variables reference the same array.

---

## Correct Solution

Create a real copy.

Example:

```text
Arrays.copyOf(numbers, numbers.length);
```

---

# Mistake 8 — Ignoring Edge Cases

Many algorithms are tested only with typical inputs.

Commonly forgotten cases include:

- empty array;
- one element;
- duplicate values;
- negative values;
- already sorted arrays;
- reverse sorted arrays.

Robust algorithms consider every valid input.

---

# Mistake 9 — Modifying an Array While Traversing It

Changing elements during traversal may invalidate assumptions made earlier in the algorithm.

This frequently produces unexpected behaviour.

Whenever modification is necessary, carefully analyse how it affects subsequent iterations.

---

# Mistake 10 — Choosing the Wrong Data Structure

Arrays are not appropriate for every problem.

Poor choice:

```text
Repeated insertions
↓

Array
```

Better choice:

```text
Repeated insertions
↓

Linked List
```

or

```text
ArrayList
```

The selected data structure should match the dominant operations.

---

# Mistake 11 — Ignoring Computational Complexity

An implementation may be correct while remaining inefficient.

Example:

Nested traversal:

```text
for
    for
```

Time Complexity:

```text
O(n²)
```

Sometimes the same problem can be solved in:

```text
O(n)
```

using a different strategy.

---

# Mistake 12 — Programming Before Understanding the Problem

Beginning implementation immediately often produces:

- unnecessary code;
- incorrect algorithms;
- poor readability;
- additional debugging.

The methodology defined in **Problem Solving Guide** should always be followed first.

---

# Summary of Common Mistakes

| Mistake | Typical Consequence |
|----------|---------------------|
| Off-by-One | Invalid index access |
| Using `length` as the last index | Runtime exception |
| Invalid indexes | Runtime exception |
| Null arrays | NullPointerException |
| Assuming automatic resizing | Logical errors |
| Comparing with `==` | Incorrect results |
| Reference copying | Unexpected modifications |
| Ignoring edge cases | Incomplete solutions |
| Modifying during traversal | Logical errors |
| Wrong data structure | Poor performance |
| Ignoring complexity | Inefficient algorithms |
| Programming too early | Weak algorithm design |

---

# Defensive Programming

Professional software engineers write code that anticipates incorrect situations.

Examples include:

- validating indexes;
- checking for `null`;
- validating input;
- documenting assumptions;
- writing automated tests.

Defensive programming improves software reliability.

---

# Relationship with Previous Modules

These mistakes are directly related to concepts introduced earlier.

```text
Foundations
        ↓
Problem Analysis

Complexity
        ↓
Performance

Arrays
        ↓
Traversal

Java
        ↓
Implementation

Testing
        ↓
Validation
```

Understanding the theory reduces the likelihood of implementation errors.

---

# Relationship with Future Modules

The lessons presented here apply to every subsequent data structure.

Examples include:

```text
Searching

Sorting

Stacks

Queues

Trees

Graphs

Dynamic Programming
```

Although the algorithms change, disciplined engineering practices remain the same.

---

# Best Practices

When implementing algorithms involving arrays:

- validate indexes before access;
- distinguish references from copies;
- analyse edge cases before coding;
- choose the appropriate data structure;
- evaluate computational complexity;
- write automated tests for every implementation.

---

# Key Takeaways

The learner should remember the following principles:

- Most array errors originate from incorrect reasoning rather than incorrect syntax.
- Understanding indexes prevents many runtime exceptions.
- Arrays have a fixed size and are reference types.
- Edge cases should always be analysed before implementation.
- Correct data structure selection is an engineering decision.
- Complexity analysis is as important as correctness.
- Defensive programming significantly improves software quality.

Recognising and preventing these common mistakes enables software engineers to develop more reliable, maintainable and scalable software throughout the **Algorithms Java Mastery** repository.