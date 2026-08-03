# Parameterized Tests

## Algorithms Java Mastery

Many software components must be verified using **multiple input values**. A sorting algorithm, for example, should be tested with empty arrays, single-element arrays, already sorted arrays, reverse-ordered arrays, duplicate values, and random data. Writing one independent test method for every input quickly leads to duplicated code and difficult maintenance.

**Parameterized Tests** solve this problem by allowing a single test method to be executed repeatedly with different sets of input data.

Instead of duplicating the test logic, developers define the test once and provide multiple arguments that are automatically supplied by the testing framework. This approach improves readability, reduces duplication, increases coverage, and makes automated tests easier to maintain.

JUnit Jupiter provides first-class support for parameterized testing through a collection of annotations that supply arguments from various sources, including literals, CSV files, methods, and external data providers.

---

# 1. Purpose

The purpose of this document is to:

- introduce Parameterized Tests;
- explain their advantages;
- study common parameter sources;
- compare parameterized and traditional tests;
- establish best practices for reusable unit tests.

---

# 2. What Are Parameterized Tests?

A **Parameterized Test** is a test method that executes multiple times using different input values.

Conceptually:

```text
One Test Method
        ↓
Input Set 1
        ↓
Input Set 2
        ↓
Input Set 3
        ↓
...
```

Each execution is treated as an independent test.

---

# 3. Why Parameterized Tests?

Without parameterization:

```text
Test A

Test B

Test C

Test D
```

The test logic is duplicated.

With parameterization:

```text
One Test

↓

Multiple Inputs
```

The logic is written once and reused.

---

# 4. Execution Model

JUnit Jupiter executes the same test repeatedly.

Conceptually:

```text
Test Method
        ↓
Input 1
        ↓
Pass / Fail

↓

Input 2
        ↓
Pass / Fail

↓

Input 3
        ↓
Pass / Fail
```

Each input produces an independent execution result.

---

# 5. The @ParameterizedTest Annotation

JUnit Jupiter identifies parameterized tests using:

```text
@ParameterizedTest
```

Conceptually:

```text
Method

↓

@ParameterizedTest

↓

Multiple Executions
```

The annotation replaces the standard `@Test` annotation.

---

# 6. Value Sources

The simplest parameter source supplies a list of values.

Example annotation:

```text
@ValueSource
```

Conceptually:

```text
Value 1

Value 2

Value 3

↓

One Test
```

This is useful for primitive values and strings.

---

# 7. CSV Sources

Multiple arguments may be supplied using comma-separated values.

Example annotation:

```text
@CsvSource
```

Conceptually:

```text
Input A, Expected

↓

Input B, Expected

↓

Input C, Expected
```

CSV sources simplify testing multiple input-output pairs.

---

# 8. Method Sources

Complex test data can be generated programmatically.

Example annotation:

```text
@MethodSource
```

Conceptually:

```text
Method

↓

Arguments

↓

Parameterized Test
```

Method sources are useful when test data cannot be expressed as simple literals.

---

# 9. Null and Empty Sources

JUnit Jupiter also supports special parameter sources for unusual inputs.

Examples include:

- null values;
- empty strings;
- empty collections.

These help verify edge-case behavior.

---

# 10. Independent Executions

Each parameter set represents an independent test.

Conceptually:

```text
Input A

↓

Independent Test
```

```text
Input B

↓

Independent Test
```

A failure in one execution does not prevent the remaining parameter sets from running.

---

# 11. Reducing Code Duplication

Parameterized tests eliminate repeated test methods.

Instead of:

```text
Test One

Test Two

Test Three
```

Developers write:

```text
One Test

↓

Many Inputs
```

This improves maintainability.

---

# 12. Improving Test Coverage

Testing multiple inputs becomes practical.

Examples include:

- positive values;
- negative values;
- zero;
- boundary values;
- invalid input.

More input combinations generally increase confidence in correctness.

---

# 13. Relationship with Boundary Testing

Parameterized tests are especially useful for boundary analysis.

Example:

```text
Minimum

↓

Typical

↓

Maximum
```

Each value becomes one parameterized execution.

---

# 14. Relationship with Edge Cases

Edge cases can also be supplied as parameters.

Examples include:

- empty arrays;
- duplicate values;
- null references;
- extremely large values.

This keeps tests concise while covering unusual scenarios.

---

# 15. Readability

Professional parameterized tests should remain easy to understand.

Good tests clearly communicate:

- what varies;
- what remains constant;
- what behavior is expected.

Readable tests simplify maintenance and debugging.

---

# 16. Limitations

Parameterized tests are not appropriate for every situation.

Avoid them when:

- every execution requires completely different setup;
- the tested behaviors are unrelated;
- the test becomes difficult to understand.

Sometimes separate test methods communicate intent more clearly.

---

# 17. Java Perspective

JUnit Jupiter commonly provides parameter sources such as:

- `@ValueSource`
- `@CsvSource`
- `@MethodSource`
- `@NullSource`
- `@EmptySource`
- `@NullAndEmptySource`

These annotations support a wide variety of testing scenarios without duplicating code.

---

# 18. Engineering Perspective

Professional software engineers frequently ask:

```text
Can this test
be reused
with different
inputs?
```

```text
Can duplicated
tests become
one parameterized
test?
```

Parameterized tests reduce maintenance effort while improving consistency and coverage.

They are particularly valuable for algorithms that must behave correctly across many different input values.

---

# 19. Relationship with the Next Document

This document introduced **Parameterized Tests**, demonstrating how one unit test can validate multiple input scenarios efficiently.

The next document studies **Exception Testing**, explaining how automated tests verify that software correctly detects invalid situations and throws the expected exceptions.

---

# 20. Key Takeaways

After completing this document, the learner should understand that:

- Parameterized Tests execute the same test logic using multiple sets of input data;
- JUnit Jupiter provides the `@ParameterizedTest` annotation for reusable test execution;
- common parameter sources include `@ValueSource`, `@CsvSource`, and `@MethodSource`;
- parameterized tests reduce duplicated code while increasing test coverage;
- boundary values and edge cases are particularly well suited to parameterized testing;
- each parameter set is executed independently and reported separately;
- parameterized tests improve readability, maintainability, and consistency when verifying multiple input scenarios;
- professional Java developers use parameterized tests extensively to validate algorithms against diverse datasets with minimal code duplication.