# Automated Testing

## Algorithms Java Mastery

Software engineering is fundamentally concerned with producing software that behaves correctly under a wide variety of conditions. As software systems grow in size and complexity, manually verifying every feature after each modification becomes impractical, slow, and error-prone. Every change introduces the possibility of regression defects—errors that unintentionally break previously working functionality.

To address this challenge, modern software development relies on **Automated Testing**.

Automated testing is the practice of verifying software behavior through executable test programs that run automatically whenever the application is built or modified. Rather than depending on manual verification, automated tests repeatedly validate that the software satisfies its expected behavior, providing rapid feedback whenever defects are introduced.

Within the Java ecosystem, automated testing is typically implemented using frameworks such as **JUnit Jupiter**, **AssertJ**, and **Mockito**, and is executed automatically through build tools like **Maven** and Continuous Integration platforms such as **GitHub Actions**.

For professional software engineers, automated testing is not an optional activity performed at the end of development. It is an integral part of the software development lifecycle, ensuring correctness, maintainability, confidence during refactoring, and long-term software quality.

This document explains how automated testing integrates into CI pipelines and why it is one of the most important practices in modern Java software engineering.

---

# 1. Purpose

The purpose of this document is to:

- explain automated testing within Continuous Integration;
- understand why tests execute automatically;
- study the role of JUnit and AssertJ in CI pipelines;
- explain regression prevention;
- establish best practices for automated software validation.

---

# 2. What Is Automated Testing?

**Automated Testing** is the execution of test code that verifies software behavior without manual intervention.

Conceptually:

```text
Application
        ↓
Automated Tests
        ↓
Verification
        ↓
Result
```

Tests execute automatically whenever required by the development workflow.

---

# 3. Why Automated Testing Exists

Without automated testing:

```text
Code Change

↓

Manual Verification

↓

Possible Human Error
```

With automated testing:

```text
Code Change

↓

Automatic Tests

↓

Immediate Feedback
```

Automation increases reliability while reducing repetitive manual work.

---

# 4. Automated Testing in CI

Continuous Integration automatically executes tests after compilation.

Conceptually:

```text
Commit
        ↓
Compile
        ↓
Automated Tests
        ↓
Pipeline Result
```

If any required test fails, the pipeline reports a failure.

---

# 5. The Testing Stage

A typical testing stage performs:

- unit tests;
- integration tests;
- regression tests;
- optional performance verification.

Conceptually:

```text
Compiled Application

↓

Testing

↓

Validation
```

Only validated builds continue through the pipeline.

---

# 6. JUnit Jupiter

Professional Java projects commonly use **JUnit Jupiter** as the testing framework.

JUnit provides:

- test execution;
- lifecycle management;
- assertions;
- parameterized tests;
- exception testing.

Conceptually:

```text
JUnit

↓

Execute Tests

↓

Results
```

JUnit serves as the execution engine for automated tests.

---

# 7. AssertJ

**AssertJ** complements JUnit by providing expressive assertions.

Conceptually:

```text
Expected Result

↓

AssertJ

↓

Verification
```

Readable assertions improve test maintainability and make failures easier to understand.

---

# 8. Regression Prevention

One of the primary goals of automated testing is preventing regressions.

Conceptually:

```text
Existing Feature

↓

Modify Code

↓

Run Tests

↓

Still Correct?
```

Automated tests verify that previously working functionality remains correct after modifications.

---

# 9. Immediate Feedback

Continuous Integration emphasizes rapid feedback.

Conceptually:

```text
Developer

↓

Commit

↓

Automatic Tests

↓

Feedback
```

Developers learn about failures within minutes rather than days or weeks.

---

# 10. Build Validation

The testing stage determines whether the build remains valid.

Conceptually:

```text
Tests Pass

↓

Valid Build
```

or

```text
Test Failure

↓

Invalid Build
```

The pipeline prevents invalid software from progressing.

---

# 11. Repeatability

Every commit is validated using exactly the same test suite.

Conceptually:

```text
Commit

↓

Same Tests

↓

Same Criteria
```

This consistency eliminates many forms of manual verification error.

---

# 12. Confidence During Refactoring

Refactoring modifies software structure without changing behavior.

Automated tests provide confidence during this process.

Conceptually:

```text
Refactor

↓

Run Tests

↓

Behavior Preserved
```

Developers can improve internal code quality while maintaining functional correctness.

---

# 13. Testing as Documentation

Well-written automated tests describe expected software behavior.

Conceptually:

```text
Business Rule

↓

Automated Test

↓

Executable Documentation
```

Future developers can understand system behavior by reading the test suite.

---

# 14. Testing Workflow

A typical Java testing workflow follows:

```text
Write Code
        ↓
Compile
        ↓
Execute Tests
        ↓
Analyze Results
        ↓
Commit
```

Testing becomes a natural part of everyday development.

---

# 15. Automated Testing in GitHub Actions

GitHub Actions commonly executes automated tests after compilation.

Typical pipeline:

```text
Push
        ↓
Checkout
        ↓
Install Java
        ↓
Maven Test
        ↓
Pipeline Result
```

Testing becomes fully automated without requiring developer intervention.

---

# 16. Relationship with Maven

Maven executes automated tests during the build lifecycle.

Typical phases include:

```text
Compile
        ↓
Test
        ↓
Package
```

Only successfully tested applications continue to packaging.

---

# 17. Relationship with Previous Modules

Automated testing combines several concepts studied earlier.

```text
Java
        ↓
JUnit
        ↓
AssertJ
        ↓
Git
        ↓
Maven
        ↓
GitHub Actions
        ↓
Automated Testing
```

Testing becomes an integrated component of the development pipeline.

---

# 18. Java Perspective

Professional Java projects execute automated tests for:

- domain logic;
- utility classes;
- services;
- REST controllers;
- repositories;
- business rules.

Automated testing protects software quality throughout the entire project lifecycle.

---

# 19. Engineering Perspective

Professional software engineers rarely ask:

```text
Did I
remember to
run the
tests?
```

Instead, they ask:

```text
Does every
commit
execute
the entire
test suite?
```

```text
Can any
developer
trust the
pipeline?
```

```text
Will regressions
be detected
automatically?
```

Automated testing transforms software validation into a repeatable, reliable, and objective engineering process.

---

# 20. Relationship with the Next Document

This document introduced **Automated Testing**, explaining how JUnit, AssertJ, Maven, and GitHub Actions work together to validate Java applications automatically.

The next document studies **Code Quality Checks**, examining how Continuous Integration pipelines extend beyond testing by incorporating static analysis, coding standards, code coverage, and automated quality gates to further improve software reliability.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- automated testing executes software verification without manual intervention;
- Continuous Integration automatically runs the test suite after every relevant code change;
- JUnit Jupiter provides the execution framework for Java tests, while AssertJ improves assertion readability;
- automated tests detect regression defects early and provide rapid developer feedback;
- every successful build must satisfy the same validation criteria before continuing through the pipeline;
- automated tests increase confidence during refactoring and serve as executable documentation of software behavior;
- Maven integrates automated testing into the standard Java build lifecycle, while GitHub Actions automates its execution;
- automated testing is an essential engineering practice that supports software quality, maintainability, reproducibility, and professional Java backend development.