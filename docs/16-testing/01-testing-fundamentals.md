# Testing Fundamentals

## Algorithms Java Mastery

Software development is not complete when the code compiles or produces the expected output for a few examples. Professional software engineering requires demonstrating, with objective evidence, that an implementation behaves correctly under a wide variety of conditions. This is the role of **software testing**.

Testing is the systematic process of evaluating software to determine whether it satisfies its specified requirements and behaves as expected. Rather than proving that software is free of defects—which is generally impossible—testing increases confidence that the implementation is correct, reliable, maintainable, and robust.

In modern software engineering, testing is integrated throughout the development lifecycle rather than being performed only after implementation. Automated testing supports continuous integration, safe refactoring, regression prevention, and long-term maintainability.

For algorithm development, testing complements correctness proofs and complexity analysis. While mathematical reasoning demonstrates *why* an algorithm is correct, testing verifies that the actual Java implementation faithfully realizes that algorithm.

---

# 1. Purpose

The purpose of this document is to:

- define software testing;
- explain its role in software engineering;
- distinguish testing from debugging;
- introduce automated testing;
- establish the foundation for professional Java testing practices.

---

# 2. What Is Software Testing?

Software testing is the process of executing software with the objective of verifying that it behaves according to its specification.

Conceptually:

```text
Specification
        ↓
Implementation
        ↓
Execution
        ↓
Observed Behaviour
        ↓
Verification
```

Testing compares expected behavior with actual behavior.

---

# 3. Why Testing Is Necessary

Software systems are inherently complex.

Even small programs may contain:

- logical errors;
- incorrect assumptions;
- boundary condition failures;
- implementation mistakes;
- unexpected behavior.

Testing helps detect these problems before software reaches users.

---

# 4. Verification vs Validation

Software engineering distinguishes two related concepts.

## Verification

Verification answers:

```text
Are We
Building
The Product
Correctly?
```

It checks whether the implementation satisfies its specification.

---

## Validation

Validation answers:

```text
Are We
Building
The Correct
Product?
```

It evaluates whether the software solves the intended problem.

---

# 5. Testing Is Not Debugging

These concepts are often confused.

Testing:

```text
Execute

↓

Detect Failure
```

Debugging:

```text
Locate Cause

↓

Correct Defect
```

Testing discovers that a problem exists.

Debugging identifies and fixes the underlying cause.

---

# 6. Manual Testing

Manual testing is performed by a person interacting directly with the software.

Example:

```text
Run Program

↓

Observe Output

↓

Compare Results
```

Although useful, manual testing is time-consuming and difficult to repeat consistently.

---

# 7. Automated Testing

Automated testing executes predefined test cases using software tools.

Conceptually:

```text
Write Test
        ↓
Execute Automatically
        ↓
Verify Result
```

Automated tests can be executed repeatedly with identical conditions.

---

# 8. Benefits of Automated Testing

Automated testing provides numerous advantages.

It enables:

- repeatability;
- consistency;
- rapid feedback;
- regression detection;
- safer refactoring;
- continuous integration.

These benefits make automated testing an essential engineering practice.

---

# 9. Unit Testing

A **unit test** verifies the behavior of one small software component in isolation.

Typically, a unit represents:

- a method;
- a class;
- a small module.

Conceptually:

```text
Single Unit

↓

Known Input

↓

Expected Output
```

Unit testing is the primary focus of this module.

---

# 10. Test Case

A **test case** specifies one situation to be verified.

Every test case defines:

- input;
- expected behavior;
- expected result.

Conceptually:

```text
Input
        ↓
Execution
        ↓
Expected Result
```

A well-designed test case is clear, reproducible, and independent.

---

# 11. Test Suite

A **test suite** is a collection of related test cases.

Conceptually:

```text
Test 1

Test 2

Test 3

↓

Test Suite
```

Running the complete suite verifies multiple aspects of the software simultaneously.

---

# 12. Regression Testing

Regression testing ensures that previously working functionality continues to work after modifications.

Conceptually:

```text
Modify Code
        ↓
Run Existing Tests
        ↓
Detect Regression
```

Regression tests protect software during maintenance and refactoring.

---

# 13. Characteristics of Good Tests

Professional tests should be:

- deterministic;
- independent;
- readable;
- maintainable;
- repeatable;
- fast.

Reliable tests increase developer confidence and simplify long-term maintenance.

---

# 14. Test Pyramid

Modern software engineering often organizes tests using the Test Pyramid.

```text
        UI Tests
            ▲
    Integration Tests
            ▲
       Unit Tests
```

Most tests should be unit tests because they are fast, inexpensive, and easy to automate.

---

# 15. Relationship with Algorithm Correctness

Testing and correctness proofs complement one another.

Correctness proof:

```text
Mathematical
Reasoning
```

Testing:

```text
Practical
Verification
```

Correct algorithms should also be verified through comprehensive automated tests.

---

# 16. Continuous Integration

Automated tests are commonly executed as part of a Continuous Integration (CI) pipeline.

Conceptually:

```text
Commit Code
        ↓
Build Project
        ↓
Run Tests
        ↓
Report Results
```

A failing test immediately alerts developers to potential problems.

---

# 17. Java Perspective

Modern Java applications commonly use:

- JUnit Jupiter;
- AssertJ;
- Maven Surefire Plugin;
- GitHub Actions.

JUnit Jupiter executes tests, while AssertJ provides expressive assertions that improve readability.

---

# 18. Engineering Perspective

Professional software engineers view tests as part of the software itself.

Before implementing a feature, they ask:

```text
How can this
behavior
be verified?
```

```text
What inputs
should be
tested?
```

```text
Which edge
cases may fail?
```

Writing tests encourages modular design, improves maintainability, and increases confidence during future changes.

---

# 19. Relationship with the Next Document

This document introduced the fundamental concepts of software testing and established its role within professional software engineering.

The next document studies **Test Design**, explaining how effective test cases are constructed, organized, and selected to maximize confidence while minimizing redundancy.

---

# 20. Key Takeaways

After completing this document, the learner should understand that:

- software testing systematically verifies that implementations behave according to their specifications;
- testing increases confidence in software correctness but cannot prove the absence of defects;
- verification and validation address different aspects of software quality;
- testing detects failures, while debugging identifies and fixes their causes;
- automated testing provides repeatability, speed, and reliable regression detection;
- unit tests verify individual software components in isolation;
- well-designed tests are deterministic, independent, readable, and maintainable;
- testing is a fundamental software engineering practice that complements algorithm correctness proofs and supports continuous integration.