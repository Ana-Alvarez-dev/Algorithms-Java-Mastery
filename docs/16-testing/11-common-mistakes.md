# Common Mistakes

## Algorithms Java Mastery

Automated testing is an essential practice in professional software engineering, but simply writing tests does not guarantee software quality. Poorly designed tests can create a **false sense of confidence**, increase maintenance costs, and fail to detect critical defects.

Many mistakes made in testing are not related to programming syntax, but rather to **testing strategy**, **test design**, and **software engineering practices**. Professional developers recognize that the quality of a test suite depends on the relevance of its test cases, the clarity of its assertions, and its ability to detect regressions—not merely on the number of tests or the reported coverage percentage.

Understanding these common mistakes helps developers create tests that are reliable, maintainable, expressive, and capable of supporting long-term software evolution.

---

# 1. Purpose

The purpose of this document is to:

- identify the most common mistakes in automated testing;
- explain why these mistakes reduce test quality;
- establish professional testing practices;
- improve maintainability and reliability;
- reinforce the principles studied throughout this module.

---

# 2. Confusing Testing with Debugging

One of the most common misconceptions is believing that testing and debugging are the same activity.

Testing:

```text
Execute

↓

Detect Failure
```

Debugging:

```text
Find Cause

↓

Fix Defect
```

Testing discovers problems.

Debugging solves them.

---

# 3. Testing Only Happy Paths

Many beginners verify only successful scenarios.

Example:

```text
Valid Input

↓

Expected Result
```

Missing tests include:

- invalid input;
- boundary values;
- exceptions;
- edge cases.

Professional test suites verify both successful and failing behavior.

---

# 4. Weak Assertions

A test without meaningful assertions provides little value.

Incorrect:

```text
Execute Method

↓

No Verification
```

Correct:

```text
Execute Method

↓

Verify Behaviour
```

Assertions should validate observable behavior, not merely execute code.

---

# 5. Testing Implementation Instead of Behaviour

Tests should verify:

```text
Public Behaviour
```

not

```text
Private Methods

↓

Internal Variables
```

Behavior-focused tests remain stable even after internal refactoring.

---

# 6. Duplicate Tests

Writing multiple tests that verify the same behavior increases maintenance effort.

Incorrect:

```text
Test A

↓

Same Behaviour

↓

Test B
```

Instead:

```text
One Behaviour

↓

One Clear Test
```

Parameterized Tests often eliminate unnecessary duplication.

---

# 7. Ignoring Edge Cases

Many defects appear only under unusual conditions.

Examples include:

- empty arrays;
- null values;
- duplicate elements;
- maximum values;
- minimum values.

Every algorithm should include dedicated edge-case tests.

---

# 8. Ignoring Exception Testing

Some developers verify only successful execution.

Missing tests:

```text
Invalid Input

↓

Expected Exception
```

Robust software should also verify failure scenarios.

---

# 9. Overusing Mocks

Mock objects are valuable but should be used carefully.

Incorrect:

```text
Everything

↓

Mocked
```

Excessive mocking may hide integration problems and produce unrealistic tests.

Only external dependencies should typically be mocked.

---

# 10. Poor Test Names

Unclear names make failures difficult to understand.

Incorrect:

```text
test1()
```

Correct:

```text
shouldReturnSortedArray()
```

A good test name documents the expected behavior.

---

# 11. Long and Complex Tests

Large tests often verify multiple behaviors simultaneously.

Incorrect:

```text
Many Behaviours

↓

One Test
```

Correct:

```text
One Behaviour

↓

One Test
```

Short tests are easier to understand and maintain.

---

# 12. Shared Test State

Tests should never depend on one another.

Incorrect:

```text
Test A

↓

Test B
```

Correct:

```text
Test A

Test B

Independent
```

Independent tests can execute in any order.

---

# 13. Ignoring Test Readability

Tests are executable documentation.

If another developer cannot quickly understand a test:

```text
Poor Readability

↓

Poor Maintainability
```

Readable tests reduce maintenance costs.

---

# 14. Chasing 100% Coverage

One of the most common misconceptions is:

```text
100% Coverage

↓

Perfect Software
```

Coverage measures executed code—not software correctness.

High-quality assertions are more valuable than inflated coverage percentages.

---

# 15. Not Maintaining Tests

Software evolves continuously.

If production code changes:

```text
Update Code

↓

Update Tests
```

Outdated tests become unreliable and eventually lose their value.

---

# 16. Writing Tests Too Late

Professional software engineers design tests alongside implementation.

Preferred workflow:

```text
Understand Problem
        ↓
Design Tests
        ↓
Implement Code
        ↓
Run Tests
```

Thinking about testing early often improves software design.

---

# 17. Ignoring Performance

Although unit tests should verify correctness, they should also remain efficient.

Tests that are unnecessarily slow:

- reduce developer productivity;
- delay Continuous Integration;
- discourage frequent execution.

Fast tests encourage continuous feedback.

---

# 18. Forgetting Maintainability

Good tests are software.

Therefore they should be:

- readable;
- modular;
- reusable;
- deterministic;
- easy to update.

Maintainable tests remain valuable throughout the lifetime of the project.

---

# 19. Engineering Perspective

Professional software engineers evaluate test quality by asking:

```text
Does this
test verify
real behaviour?
```

```text
Will this
test detect
future
regressions?
```

```text
Can another
developer
understand it?
```

```text
Will this
test still
be useful
next year?
```

These questions emphasize that testing is an engineering discipline rather than a simple programming task.

---

# 20. Relationship with Future Modules

This document concludes the **Testing** module.

The concepts learned throughout this module provide the foundation for all future software engineering activities in this repository.

```text
Problem Understanding
        ↓
Algorithm Design
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Performance Measurement
```

The next major module, **Benchmarking**, shifts the focus from verifying **correctness** to measuring **performance**, using tools such as **JMH (Java Microbenchmark Harness)** to analyze execution time, memory usage, scalability, and algorithmic efficiency.

Together, Testing and Benchmarking enable developers to answer two fundamental engineering questions:

- **Is the software correct?**
- **Is the software efficient?**

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- automated tests should verify observable behavior rather than implementation details;
- testing and debugging are complementary but distinct activities;
- effective test suites include positive cases, negative cases, edge cases, and exception scenarios;
- meaningful assertions are more important than simply executing code;
- duplicated, slow, or overly complex tests reduce maintainability;
- high code coverage does not guarantee software correctness;
- professional tests are readable, independent, deterministic, and maintainable;
- software testing is a continuous engineering practice that supports correctness, refactoring, regression prevention, and long-term software quality.