# Testing Sorting Algorithms

## Purpose

The purpose of this document is to study how sorting algorithms are verified through automated testing.

Implementing a sorting algorithm is only one step of software development. Equally important is demonstrating that the implementation behaves correctly for every valid input.

Automated testing provides confidence that an algorithm produces a correctly ordered result, preserves the expected data, and behaves consistently under different scenarios.

This document introduces the testing principles applicable to sorting algorithms using modern Java testing practices.

---

# Learning Objectives

After completing this document, the learner should be able to:

- understand why sorting algorithms require systematic testing;
- identify the essential properties that every sorting algorithm must satisfy;
- design meaningful test cases;
- distinguish normal, boundary and exceptional test scenarios;
- verify sorting correctness using JUnit Jupiter and AssertJ;
- understand the role of automated testing in software engineering.

---

# Why Test Sorting Algorithms?

An algorithm may compile successfully and still produce incorrect results.

Testing verifies that the implementation satisfies its specification under different conditions.

For sorting algorithms, testing answers questions such as:

- Is the output ordered correctly?
- Were any elements lost?
- Were duplicate values preserved?
- Does the algorithm work for empty collections?
- Does it behave correctly with a single element?

Automated tests allow these questions to be answered repeatedly and consistently.

---

# Fundamental Properties of a Sorting Algorithm

Every sorting algorithm should satisfy two fundamental properties.

## Ordered Output

The resulting sequence must be ordered according to the chosen comparison relation.

For ascending order:

```text
A[i] ≤ A[i + 1]
```

for every valid index.

---

## Permutation Preservation

Sorting changes the order of elements but not the elements themselves.

Therefore:

- no element may disappear;
- no new element may appear;
- duplicate occurrences must be preserved.

The output must be a permutation of the input.

---

# Types of Test Cases

A complete test suite should include different categories of input.

## Empty Collection

```text
[]
```

Expected result:

```text
[]
```

---

## Single Element

```text
[5]
```

Expected result:

```text
[5]
```

---

## Already Sorted

```text
[1][2][3][4][5]
```

The algorithm should preserve the ordering.

---

## Reverse Sorted

```text
[5][4][3][2][1]
```

This is commonly used to evaluate worst-case behaviour.

---

## Random Input

```text
[8][2][5][1][9]
```

Represents typical input.

---

## Duplicate Values

```text
[4][2][4][1][2]
```

Verifies correct handling of repeated values.

---

## All Equal Values

```text
[7][7][7][7]
```

The algorithm should terminate correctly without unnecessary modifications.

---

## Negative Values

```text
[-5][-1][4][2]
```

Confirms that comparisons work for the complete numeric range.

---

# Boundary Testing

Boundary values frequently reveal implementation errors.

Examples include:

- empty arrays;
- arrays of size one;
- arrays of size two;
- very large arrays.

Boundary testing is particularly useful for detecting indexing mistakes.

---

# Idempotence

Sorting an already sorted collection should produce the same ordered result.

Formally:

```text
sort(sort(A)) = sort(A)
```

This property is known as **idempotence**.

---

# Stability Testing

Stable algorithms should preserve the relative order of equal elements.

Example:

```text
(A, 2)
(B, 1)
(C, 2)
```

Expected stable result:

```text
(B, 1)
(A, 2)
(C, 2)
```

Algorithms documented as stable should be tested accordingly.

---

# Automated Testing with JUnit Jupiter

JUnit Jupiter is the standard testing framework used in modern Java projects.

A simple test structure is:

```java
@Test
void shouldSortAscendingOrder() {

    int[] values = {5, 2, 8, 1};

    sorter.sort(values);

    assertThat(values)
            .containsExactly(1, 2, 5, 8);
}
```

Each test verifies one clearly defined behaviour.

---

# Assertions with AssertJ

AssertJ provides expressive assertions.

Example:

```text
assertThat(values)
        .isSorted();
```

Other useful assertions include:

```text
assertThat(values)
        .hasSize(5);
```

```text
assertThat(values)
        .containsExactly(...);
```

Readable assertions improve test maintainability.

---

# Test Independence

Every test should execute independently.

Each test should:

- create its own input;
- execute the algorithm;
- verify the result.

Tests should never depend on previous executions.

---

# Good Testing Practices

A high-quality test suite should be:

- deterministic;
- repeatable;
- readable;
- independent;
- maintainable.

Each test should verify one behaviour.

---

# Engineering Perspective

Testing is not an optional activity performed after implementation.

It is an essential component of software engineering.

Well-designed tests:

- detect regressions;
- document expected behaviour;
- increase confidence during refactoring;
- improve long-term maintainability.

For algorithms, automated testing complements mathematical correctness reasoning by validating real implementations.

---

# Relationship with Previous Documents

Previous documents studied sorting algorithms and compared their properties.

This document demonstrates how those implementations can be validated through automated testing.

---

# Relationship with Future Documents

The next document studies benchmarking techniques for measuring the practical performance of sorting algorithms.

Testing verifies correctness.

Benchmarking evaluates efficiency.

Both are necessary for professional software development.

---

# Key Takeaways

- Every sorting algorithm should be tested automatically.
- Correctness requires ordered output and permutation preservation.
- Boundary cases are essential.
- Stable algorithms should preserve the order of equal elements.
- JUnit Jupiter and AssertJ provide modern tools for automated testing.
- Testing complements theoretical algorithm analysis.

---

# Next Document

```text
13-benchmarking-sorting-algorithms.md
```