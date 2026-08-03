# Property-Based Thinking

## Algorithms Java Mastery

Traditional unit testing verifies software using **specific examples**. A developer selects an input, predicts the expected output, and writes a test that compares the observed result with that expectation. While this approach is essential, it can never cover every possible input.

**Property-Based Thinking** introduces a different perspective. Instead of asking whether an algorithm works for a handful of examples, it asks whether the algorithm satisfies **general properties** that must always be true, regardless of the specific input.

These properties are often mathematical in nature and describe the fundamental behavior of an algorithm. By verifying these invariants across many automatically generated inputs, developers gain much stronger confidence in software correctness.

Although full **Property-Based Testing** requires specialized libraries (such as jqwik or QuickTheories in Java), every professional software engineer should first learn **Property-Based Thinking**. It is a reasoning technique that improves algorithm design, test quality, and software reliability, even when using only JUnit Jupiter.

---

# 1. Purpose

The purpose of this document is to:

- introduce Property-Based Thinking;
- distinguish properties from example-based tests;
- explain invariants;
- demonstrate how properties improve software testing;
- prepare for advanced testing techniques.

---

# 2. Example-Based Testing

Traditional unit tests verify individual examples.

Conceptually:

```text
Input

↓

Expected Output

↓

Assertion
```

Example:

```text
sort([3,1,2])

↓

[1,2,3]
```

The test verifies one particular case.

---

# 3. Limitations of Example-Based Testing

Even hundreds of unit tests cannot cover every possible input.

Conceptually:

```text
Millions of Inputs

↓

Few Examples
```

A program may pass every example while still failing for untested data.

---

# 4. What Is a Property?

A **property** is a rule that should always remain true for every valid input.

Conceptually:

```text
Any Valid Input

↓

Property Holds
```

Unlike examples, properties describe **general behavior**.

---

# 5. Invariants

A property that never changes during execution is called an **invariant**.

Conceptually:

```text
Algorithm

↓

Execution

↓

Invariant Always True
```

Invariants play a central role in both algorithm correctness and testing.

---

# 6. Examples of Properties

Typical algorithmic properties include:

### Sorting

```text
Output
Is Sorted
```

---

### Searching

```text
Returned Index

↓

Contains Target
```

---

### Collections

```text
Size Never Negative
```

---

### Graph Algorithms

```text
Traversal Visits

Every Reachable Vertex

Once
```

Properties describe behavior independently of individual examples.

---

# 7. Sorting Example

Suppose an array is sorted.

Instead of checking:

```text
[3,1,2]

↓

[1,2,3]
```

Property-Based Thinking verifies:

```text
Every Adjacent Pair

↓

Ascending Order
```

If this property always holds, the result is correctly sorted.

---

# 8. Permutation Property

Sorting algorithms should not lose or duplicate elements.

Property:

```text
Input Elements

=

Output Elements
```

Only the order changes.

This property complements the "sorted order" property.

---

# 9. Search Algorithm Property

Binary Search provides another example.

Property:

```text
Returned Index

↓

Array[Index]

=

Target
```

If the target is absent:

```text
Result

↓

Not Found
```

The property must always hold.

---

# 10. Mathematical Properties

Many algorithms satisfy mathematical identities.

Examples include:

Addition:

```text
a+b

=

b+a
```

Maximum:

```text
max(a,b)

≥

a

and

b
```

These identities naturally become test properties.

---

# 11. Determinism

Many algorithms should be deterministic.

Property:

```text
Same Input

↓

Same Output
```

Repeated executions should produce identical results.

---

# 12. Idempotence

Some operations should not change after repeated execution.

Example:

Sorting:

```text
Sort

↓

Sort Again

↓

Same Result
```

This is known as **idempotence**.

---

# 13. Preservation Properties

Some algorithms preserve important information.

Examples include:

```text
Array Length

↓

Unchanged
```

```text
Tree Size

↓

Unchanged
```

```text
Graph Vertices

↓

Unchanged
```

Only the intended property should change.

---

# 14. Relationship with Correctness

Correctness proofs and Property-Based Thinking complement one another.

Correctness proof:

```text
Mathematical Proof
```

Property-Based Thinking:

```text
Executable Verification
```

Both strengthen confidence in the implementation.

---

# 15. Relationship with Edge Cases

Properties should remain valid even under unusual inputs.

Examples:

- empty arrays;
- one element;
- duplicate values;
- maximum input size;
- minimum input size.

Properties should hold for every valid case.

---

# 16. Property-Based Testing Libraries

Modern Java provides libraries that automatically generate inputs.

Examples include:

- jqwik;
- QuickTheories;
- junit-quickcheck.

These libraries repeatedly execute the same property using randomly generated data.

---

# 17. Benefits

Property-Based Thinking offers several advantages.

It:

- increases test coverage;
- reveals hidden defects;
- reduces dependence on manually selected examples;
- improves algorithmic reasoning;
- encourages mathematical thinking.

---

# 18. Java Perspective

Although JUnit Jupiter primarily supports example-based testing, developers can still apply Property-Based Thinking.

Typical workflow:

```text
Identify Property

↓

Generate Test Cases

↓

Verify Property
```

Later, specialized libraries can automate input generation.

---

# 19. Engineering Perspective

Professional software engineers often ask:

```text
What must

always

be true?
```

instead of:

```text
Does this

one example

work?
```

This shift in perspective leads to more comprehensive tests, stronger algorithm validation, and higher software quality.

Property-Based Thinking is especially valuable when developing reusable libraries, algorithms, frameworks, and enterprise software where correctness must hold across an enormous range of inputs.

---

# 20. Relationship with the Next Document

This document introduced **Property-Based Thinking**, demonstrating how general behavioral properties can strengthen automated testing beyond individual examples.

The next document studies **Test Data Builders**, a design technique that simplifies the creation of readable, reusable, and maintainable test data for complex Java applications.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- Property-Based Thinking focuses on general properties rather than individual examples;
- properties describe behavior that must always remain true for valid inputs;
- invariants provide a mathematical foundation for many software tests;
- algorithmic properties often include ordering, preservation, determinism, and idempotence;
- Property-Based Thinking complements traditional unit testing and correctness proofs;
- specialized Java libraries such as jqwik and QuickTheories automate property-based testing;
- thinking in terms of properties leads to stronger, more scalable, and more reliable test suites;
- professional software engineers use Property-Based Thinking to reason about correctness across an unlimited range of possible inputs rather than relying solely on manually selected examples.