# Exception Testing

## Algorithms Java Mastery

Robust software must not only produce correct results for valid input, but also **respond correctly when invalid, unexpected, or illegal input is provided**. Verifying this behavior is the purpose of **Exception Testing**.

In Java, exceptions provide a structured mechanism for reporting errors and abnormal conditions. Rather than allowing incorrect data to produce undefined behavior, well-designed software detects invalid situations, throws meaningful exceptions, and communicates the cause of the failure.

Professional software engineers therefore test both **successful execution paths** and **failure scenarios**. Exception testing ensures that software fails predictably, reports appropriate error information, and preserves system integrity.

JUnit Jupiter provides built-in support for exception verification, allowing developers to confirm that methods throw the expected exception types under specific conditions. Combined with AssertJ, exception testing becomes expressive, readable, and maintainable.

For algorithm implementations, exception testing is especially important because algorithms often assume valid preconditions. Automated tests verify that violations of these preconditions are handled safely and consistently.

---

# 1. Purpose

The purpose of this document is to:

- explain exception testing;
- understand why failure scenarios must be tested;
- study common exception testing techniques;
- introduce JUnit Jupiter support for exceptions;
- establish best practices for robust Java testing.

---

# 2. What Is Exception Testing?

**Exception Testing** verifies that software throws the correct exception when invalid or unexpected situations occur.

Conceptually:

```text
Invalid Input
        ↓
Method Execution
        ↓
Expected Exception
        ↓
Test Passes
```

The goal is to confirm that failures occur **intentionally** and **predictably**.

---

# 3. Why Test Exceptions?

Incorrect input is inevitable in real-world software.

Examples include:

- null references;
- invalid indexes;
- negative values;
- malformed data;
- division by zero;
- illegal object states.

Software should detect these situations instead of producing incorrect results.

---

# 4. Fail Fast Principle

Professional software often follows the **Fail Fast** principle.

Conceptually:

```text
Invalid Input
        ↓
Detect Immediately
        ↓
Throw Exception
```

Failing early prevents hidden defects from propagating through the system.

---

# 5. Checked and Unchecked Exceptions

Java defines two major exception categories.

## Checked Exceptions

Must be handled or declared.

Examples include:

- file access;
- network communication;
- database operations.

---

## Unchecked Exceptions

Occur during program execution.

Examples include:

- `NullPointerException`;
- `IllegalArgumentException`;
- `IllegalStateException`;
- `IndexOutOfBoundsException`.

Unit tests frequently verify unchecked exceptions because they represent programming errors or invalid input.

---

# 6. Expected Exceptions

A successful exception test verifies:

```text
Correct Exception

↓

Correct Situation
```

The test succeeds only if the expected exception is thrown.

---

# 7. Unexpected Exceptions

If a different exception occurs:

```text
Expected

↓

IllegalArgumentException
```

but the method throws:

```text
NullPointerException
```

the test fails.

Testing verifies both **whether** an exception occurs and **which** exception is thrown.

---

# 8. No Exception When One Is Expected

Sometimes software silently accepts invalid input.

Conceptually:

```text
Invalid Input

↓

No Exception

↓

Test Fails
```

This often indicates missing input validation.

---

# 9. JUnit Jupiter Exception Testing

JUnit Jupiter provides built-in support for exception verification.

The primary method is:

```text
assertThrows(...)
```

Conceptually:

```text
Execute Method
        ↓
Expected Exception?
      /          \
    Yes           No
    ↓             ↓
Pass           Fail
```

---

# 10. Verifying Exception Messages

Sometimes the exception type alone is insufficient.

Tests may also verify:

- error messages;
- error codes;
- additional exception properties.

Conceptually:

```text
Exception
        ↓
Type
        ↓
Message
```

Meaningful messages improve debugging and diagnostics.

---

# 11. Positive vs Negative Testing

A complete test suite includes both successful and failing scenarios.

```text
Valid Input
        ↓
Expected Result
```

and

```text
Invalid Input
        ↓
Expected Exception
```

Both behaviors are equally important.

---

# 12. Common Exception Scenarios

Algorithms frequently validate:

- null input;
- empty collections;
- invalid array indexes;
- negative capacities;
- unsupported operations;
- invalid graph structures.

Each scenario should be verified independently.

---

# 13. Testing Preconditions

Many algorithms define **preconditions**.

Example:

```text
Input

↓

Precondition

↓

Algorithm
```

If a precondition is violated:

```text
Throw Exception
```

Automated tests verify that these constraints are enforced.

---

# 14. Exception Testing and Defensive Programming

Defensive programming anticipates incorrect usage.

Conceptually:

```text
Unexpected Situation
        ↓
Validation
        ↓
Controlled Failure
```

Exception testing confirms that defensive checks operate correctly.

---

# 15. Relationship with Edge Cases

Many edge cases naturally produce exceptions.

Examples include:

- empty stack;
- dequeue from an empty queue;
- invalid tree node;
- array index outside bounds.

Testing these situations increases software robustness.

---

# 16. Common Mistakes

Frequent mistakes include:

- testing only successful execution;
- accepting generic exceptions;
- ignoring exception messages;
- catching exceptions inside the test unnecessarily;
- verifying implementation details instead of observable behavior.

Professional tests focus on the expected public behavior.

---

# 17. Java Perspective

Modern Java applications commonly test exceptions using:

- JUnit Jupiter;
- AssertJ;
- custom exception classes;
- defensive input validation.

Typical workflow:

```text
Prepare Invalid Input
        ↓
Execute Method
        ↓
Verify Exception
```

---

# 18. Engineering Perspective

Professional software engineers recognize that incorrect input is unavoidable.

Typical questions include:

```text
What happens
if the input
is null?
```

```text
How does the
algorithm respond
to invalid data?
```

```text
Does the exception
clearly explain
the problem?
```

Testing failure scenarios is just as important as testing successful execution.

---

# 19. Relationship with the Next Document

This document introduced **Exception Testing**, explaining how automated tests verify that software correctly detects invalid situations and reports failures through appropriate exceptions.

The next document studies **Edge Case Testing**, focusing on unusual, boundary, and extreme input values that frequently expose hidden defects in algorithms and software implementations.

---

# 20. Key Takeaways

After completing this document, the learner should understand that:

- Exception Testing verifies that software fails correctly under invalid conditions;
- professional software must be tested for both successful and failing execution paths;
- the Fail Fast principle encourages early detection of invalid input;
- JUnit Jupiter provides `assertThrows()` for exception verification;
- exception tests should verify both the exception type and, when appropriate, its message;
- preconditions and defensive programming should be validated through automated tests;
- testing failure scenarios improves software reliability, robustness, and maintainability;
- comprehensive Java test suites always include exception testing alongside functional and boundary-value testing.