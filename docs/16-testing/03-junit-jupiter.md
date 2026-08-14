# JUnit Jupiter

## Algorithms Java Mastery

**JUnit Jupiter** is the programming and extension model of **JUnit 5**, the modern standard framework for automated testing in the Java ecosystem.

It provides the annotations, assertions, lifecycle management, and execution engine required to create, organize, and execute unit tests efficiently. Together with **AssertJ**, JUnit Jupiter forms the foundation of modern Java testing in applications ranging from small algorithmic projects to large-scale enterprise systems built with Spring Boot.

JUnit Jupiter encourages developers to write tests that are readable, deterministic, independent, and maintainable. Its annotation-based programming model allows test methods to be clearly identified, grouped, configured, and executed automatically during development or within Continuous Integration (CI) pipelines.

In this repository, JUnit Jupiter is the primary framework used to verify the correctness of every algorithm implemented in Java.

---

# 1. Purpose

The purpose of this document is to:

- introduce JUnit Jupiter;
- explain its role in automated testing;
- understand its execution model;
- study the most important annotations;
- prepare for writing professional Java unit tests.

---

# 2. What Is JUnit Jupiter?

JUnit Jupiter is the testing framework included in **JUnit 5**.

It provides:

- test execution;
- lifecycle management;
- assertions;
- parameterized tests;
- nested tests;
- extensions.

Conceptually:

```text
Java Code
        ↓
JUnit Jupiter
        ↓
Automated Tests
        ↓
Test Report
```

---

# 3. JUnit 5 Architecture

JUnit 5 consists of three major components.

```text
JUnit Platform
        ↓
JUnit Jupiter
        ↓
JUnit Vintage
```

### JUnit Platform

Responsible for discovering and executing tests.

---

### JUnit Jupiter

Provides the API used to write modern tests.

---

### JUnit Vintage

Allows legacy JUnit 3 and JUnit 4 tests to execute on the JUnit 5 platform.

---

# 4. Why JUnit Jupiter?

Before JUnit Jupiter, developers often relied on manual testing.

```text
Write Code

↓

Run Program

↓

Check Output
```

JUnit Jupiter automates this process.

```text
Write Test

↓

Run Automatically

↓

Verify Behaviour
```

This significantly improves productivity and software quality.

---

# 5. Test Lifecycle

Each unit test follows the same lifecycle.

```text
Prepare

↓

Execute

↓

Verify

↓

Finish
```

JUnit Jupiter manages this lifecycle automatically.

---

# 6. The @Test Annotation

The most fundamental annotation is:

```java
@Test
```

It identifies a method as an executable test.

Conceptually:

```text
Method

↓

@Test

↓

Executable Test
```

Only methods marked with `@Test` are executed as unit tests.

---

# 7. Lifecycle Annotations

JUnit Jupiter provides several lifecycle annotations.

### @BeforeEach

Executed before every test.

```text
Setup

↓

Test
```

---

### @AfterEach

Executed after every test.

```text
Test

↓

Cleanup
```

---

### @BeforeAll

Executed once before all tests.

---

### @AfterAll

Executed once after all tests finish.

These annotations simplify test preparation and cleanup.

---

# 8. Assertions

JUnit Jupiter provides basic assertions to verify expected behavior.

Examples include:

- equality;
- truth values;
- null references;
- object identity;
- exception verification.

Conceptually:

```text
Actual Result

↓

Assertion

↓

Pass

or

Fail
```

Assertions determine whether a test succeeds.

---

# 9. Test Independence

Professional unit tests should never depend on one another.

Correct:

```text
Test A

Test B

Test C
```

Independent execution.

Incorrect:

```text
Test A

↓

Test B
```

Every test should be executable in isolation.

---

# 10. Test Naming

A descriptive test name documents behavior.

Examples:

```text
shouldSortArrayAscending()
```

```text
shouldReturnEmptyList()
```

```text
shouldThrowExceptionForNullInput()
```

Readable names improve maintainability.

---

# 11. Test Execution

JUnit automatically discovers test methods.

Conceptually:

```text
Find Tests

↓

Execute

↓

Report Results
```

Developers do not manually invoke test methods.

---

# 12. Test Reports

After execution, JUnit produces a report.

Example:

```text
15 Tests

↓

15 Passed
```

or

```text
15 Tests

↓

14 Passed

1 Failed
```

Reports help identify failing behaviors quickly.

---

# 13. Organizing Tests

Professional projects organize tests according to the production code.

Example:

```text
src/main/java
        ↓
Algorithm.java

↓

src/test/java
        ↓
AlgorithmTest.java
```

This one-to-one structure improves navigation and maintenance.

---

# 14. Integration with Maven

JUnit Jupiter integrates naturally with Maven.

Typical workflow:

```text
Compile

↓

Run Tests

↓

Generate Report
```

Tests execute automatically during the Maven build lifecycle.

---

# 15. Integration with IntelliJ IDEA

Modern IDEs execute JUnit tests directly.

Developers can:

- run individual tests;
- execute complete test classes;
- debug failing tests;
- view execution reports.

This accelerates development and debugging.

---

# 16. Relationship with AssertJ

JUnit Jupiter executes tests.

AssertJ improves assertions.

Conceptually:

```text
JUnit Jupiter

↓

Runs Tests

↓

AssertJ

↓

Verifies Results
```

The two libraries complement each other rather than compete.

---

# 17. Best Practices

Professional JUnit tests should be:

- short;
- independent;
- deterministic;
- readable;
- fast;
- focused on one behavior.

Each test should verify a single responsibility.

---

# 18. Java Perspective

Typical JUnit Jupiter projects use:

- `@Test`
- `@BeforeEach`
- `@AfterEach`
- `@BeforeAll`
- `@AfterAll`

along with Maven and IntelliJ IDEA.

This combination represents the standard Java testing environment used throughout the industry.

---

# 19. Engineering Perspective

Professional software engineers use JUnit Jupiter to verify software continuously during development.

Typical workflow:

```text
Implement Feature
        ↓
Write Unit Tests
        ↓
Run JUnit
        ↓
Fix Failures
        ↓
Commit Code
```

Because tests execute automatically during Continuous Integration, JUnit Jupiter helps prevent regressions and ensures software reliability.

---

# 20. Relationship with the Next Document

This document introduced **JUnit Jupiter**, the execution framework for modern Java unit testing.

The next document studies **AssertJ**, the fluent assertion library that complements JUnit Jupiter by providing expressive, readable, and maintainable assertions for verifying software behavior.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- JUnit Jupiter is the modern testing framework of JUnit 5;
- it provides annotations, lifecycle management, and automated test execution;
- the `@Test` annotation identifies executable unit tests;
- lifecycle annotations simplify test setup and cleanup;
- JUnit automatically discovers, executes, and reports test results;
- professional unit tests should be independent, deterministic, readable, and focused on one behavior;
- JUnit Jupiter integrates seamlessly with Maven, IntelliJ IDEA, and Continuous Integration pipelines;
- together with AssertJ, JUnit Jupiter forms the foundation of modern automated testing in Java applications.