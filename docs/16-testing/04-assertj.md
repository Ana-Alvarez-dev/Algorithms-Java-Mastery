# AssertJ

## Algorithms Java Mastery

While **JUnit Jupiter** provides the framework for discovering and executing automated tests, **AssertJ** is responsible for one of the most important aspects of testing: **expressing assertions clearly and readably**.

Assertions determine whether the observed behavior of a program matches the expected behavior. Traditional assertion methods often become difficult to read as test complexity increases. AssertJ addresses this problem by providing a **fluent API** that allows assertions to be written in a natural, expressive style.

Rather than simply verifying values, AssertJ encourages developers to write tests that clearly communicate their intent. Readable assertions improve code reviews, simplify maintenance, and make failing tests easier to understand.

Because of its expressive syntax and extensive support for Java types, AssertJ has become one of the most widely adopted assertion libraries in modern Java development, especially in projects using JUnit Jupiter and Spring Boot.

---

# 1. Purpose

The purpose of this document is to:

- introduce AssertJ;
- explain fluent assertions;
- distinguish AssertJ from JUnit assertions;
- study common assertion categories;
- establish best practices for expressive Java tests.

---

# 2. What Is AssertJ?

AssertJ is a **fluent assertion library** for Java.

It provides:

- readable assertions;
- extensive type support;
- descriptive failure messages;
- expressive test code.

Conceptually:

```text
JUnit Jupiter

↓

Execute Test

↓

AssertJ

↓

Verify Behaviour
```

AssertJ complements JUnit rather than replacing it.

---

# 3. Why AssertJ?

Traditional assertions often become difficult to read.

Conceptually:

```text
Assertion

↓

Correct?

↓

Hard To Read
```

AssertJ improves readability.

```text
Assertion

↓

Readable

↓

Self-Documenting
```

The test communicates both **what** is being verified and **why**.

---

# 4. Fluent API

AssertJ uses a **fluent interface**.

Conceptually:

```text
Actual Value

↓

Assertion

↓

Expected Behaviour
```

Each assertion reads almost like natural language.

This improves both readability and maintainability.

---

# 5. Assertion Structure

Every assertion consists of two components.

```text
Actual Result

↓

Expected Result
```

If both values satisfy the assertion, the test passes.

Otherwise, the test fails with a descriptive message.

---

# 6. Equality Assertions

Equality assertions verify that two values are identical.

Typical use cases include:

- numbers;
- strings;
- objects;
- collections.

Conceptually:

```text
Actual

↓

Equals?

↓

Expected
```

---

# 7. Boolean Assertions

Boolean assertions verify logical conditions.

Examples include:

- true;
- false;
- conditions.

Conceptually:

```text
Boolean Value

↓

Expected State
```

These assertions simplify logical verification.

---

# 8. Null Assertions

Software frequently needs to verify the presence or absence of objects.

Conceptually:

```text
Object

↓

Null?

↓

Expected
```

AssertJ provides expressive assertions for both null and non-null values.

---

# 9. Collection Assertions

Collections often require more than simple equality.

Typical properties include:

- size;
- emptiness;
- contents;
- ordering;
- uniqueness.

Conceptually:

```text
Collection

↓

Properties

↓

Verification
```

AssertJ provides specialized assertions for Java collections.

---

# 10. String Assertions

Strings may be verified using properties such as:

- equality;
- containment;
- prefixes;
- suffixes;
- length.

Conceptually:

```text
String

↓

Expected Property
```

These assertions make text verification concise and readable.

---

# 11. Numeric Assertions

Numeric assertions verify mathematical relationships.

Examples include:

- greater than;
- less than;
- positive;
- negative;
- zero.

Conceptually:

```text
Number

↓

Comparison

↓

Expected Result
```

---

# 12. Exception Assertions

AssertJ provides expressive assertions for exceptions.

Conceptually:

```text
Execute Code

↓

Exception?

↓

Verify Type

↓

Verify Message
```

This makes failure scenarios easier to test and understand.

---

# 13. Object Assertions

Complex objects can be verified through:

- equality;
- field values;
- recursive comparison;
- property verification.

Readable object assertions reduce test complexity.

---

# 14. Failure Messages

One advantage of AssertJ is its informative failure output.

Instead of reporting only:

```text
Failed
```

it explains:

```text
Expected

↓

Actual

↓

Difference
```

This simplifies debugging.

---

# 15. Readability

Professional tests prioritize readability.

Example reasoning:

```text
Read Test

↓

Understand Behaviour

↓

Understand Failure
```

Readable assertions serve as executable documentation.

---

# 16. Relationship with JUnit Jupiter

JUnit Jupiter:

```text
Run Tests
```

AssertJ:

```text
Verify Results
```

Together they provide:

```text
Execution

+

Assertions

↓

Complete Unit Test
```

---

# 17. Best Practices

Professional AssertJ usage follows several principles.

- Verify one behavior per assertion group.
- Prefer expressive assertions over complex boolean expressions.
- Write assertions that explain intent.
- Keep tests short and focused.
- Avoid unnecessary duplication.

Readable assertions improve long-term maintainability.

---

# 18. Java Perspective

AssertJ integrates naturally with:

- JUnit Jupiter;
- Maven;
- IntelliJ IDEA;
- Spring Boot.

Typical workflow:

```text
Execute Test

↓

Assert Behaviour

↓

Pass

or

Fail
```

The library supports virtually every standard Java type.

---

# 19. Engineering Perspective

Professional software engineers value AssertJ because tests are read far more often than they are written.

Typical questions include:

```text
Can another
developer
understand
this assertion?
```

```text
Will a failing
test explain
the problem?
```

```text
Does the test
describe
expected
behaviour?
```

Expressive assertions reduce maintenance costs and accelerate debugging.

---

# 20. Relationship with the Next Document

This document introduced **AssertJ**, the fluent assertion library used to express expected behavior in modern Java unit tests.

The next document studies **Parameterized Tests**, demonstrating how a single test can be executed repeatedly with multiple sets of input data, improving test coverage while reducing duplication.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- AssertJ is a fluent assertion library that complements JUnit Jupiter;
- assertions verify that actual behavior matches expected behavior;
- fluent assertions improve readability and maintainability;
- AssertJ provides specialized assertions for numbers, strings, collections, objects, and exceptions;
- descriptive failure messages simplify debugging;
- expressive assertions make tests function as executable documentation;
- AssertJ integrates seamlessly with JUnit Jupiter, Maven, IntelliJ IDEA, and Spring Boot;
- professional software engineers use AssertJ to create clear, reliable, and maintainable automated tests.