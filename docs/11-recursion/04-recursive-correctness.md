# Recursive Correctness

## Algorithms Java Mastery

A recursive algorithm is not considered correct simply because it produces the expected result for a few test cases.

From an algorithmic perspective, correctness means proving that **every recursive call eventually terminates** and **every returned result is mathematically correct for all valid inputs**.

Recursive correctness is closely related to mathematical induction. Both techniques rely on the idea that if a statement is true for a simple case and remains true while progressing toward larger cases, then it is true for every valid input.

For this reason, recursive correctness occupies an important place in algorithm analysis. Before studying efficiency, a software engineer must first demonstrate that an algorithm always produces the intended result.

---

# 1. Purpose

The purpose of this document is to:

- define recursive correctness;
- explain the relationship between recursion and mathematical induction;
- distinguish termination from correctness;
- introduce recursive proof techniques;
- understand recursive invariants;
- establish systematic reasoning for recursive algorithms.

---

# 2. What Is Recursive Correctness?

A recursive algorithm is correct when:

- every recursive call eventually terminates;
- every returned value is mathematically correct;
- every recursive reduction preserves the problem definition.

Conceptually:

```text
Recursive Call
        ↓
Terminates
        ↓
Returns Correct Result
        ↓
Original Problem Solved Correctly
```

Correctness therefore depends on both **termination** and **valid recursive reasoning**.

---

# 3. Two Requirements

Every recursive algorithm must satisfy two independent properties.

## Termination

The algorithm eventually stops.

## Correctness

The returned solution is correct.

An algorithm may terminate while producing incorrect results.

Likewise, an algorithm may describe the correct computation but never terminate.

Both properties are required.

---

# 4. Why Correctness Matters

Algorithm design follows a logical sequence.

```text
Problem
        ↓
Specification
        ↓
Algorithm
        ↓
Correctness
        ↓
Complexity
```

Measuring performance before proving correctness has little value.

An incorrect algorithm that executes quickly is still incorrect.

---

# 5. Mathematical Induction

Recursive correctness is strongly connected to **mathematical induction**.

Induction consists of two parts.

First:

```text
Prove Smallest Case
```

Second:

```text
Assume Smaller Case Is Correct

↓

Prove Larger Case
```

This reasoning is remarkably similar to recursive execution.

---

# 6. Recursion and Induction

Conceptually:

```text
Mathematical Induction

↓

Base Case

↓

Inductive Step
```

matches:

```text
Recursion

↓

Base Case

↓

Recursive Case
```

The recursive algorithm assumes that the smaller problem is solved correctly and builds the larger solution from it.

---

# 7. Base Case Correctness

The first step in proving correctness is verifying the base case.

Example:

```text
factorial(0)

↓

1
```

If the base case is incorrect, every larger recursive solution becomes incorrect as well.

The proof therefore always begins with the simplest problem.

---

# 8. Recursive Assumption

After verifying the base case, we assume:

```text
The Smaller Recursive Call
Returns the Correct Result
```

This assumption is called the **recursive hypothesis** or **inductive hypothesis**.

The recursive algorithm depends on this assumption when constructing the larger solution.

---

# 9. Recursive Step

The next objective is proving:

```text
If

Problem(n − 1)

is correct,

then

Problem(n)

is also correct.
```

This demonstrates that correctness propagates from smaller instances toward larger ones.

---

# 10. Example Reasoning

Factorial:

```text
factorial(n)

=

n × factorial(n − 1)
```

Suppose:

```text
factorial(n − 1)

is correct.
```

Then:

```text
n × factorial(n − 1)
```

must also produce the correct value for:

```text
factorial(n)
```

This completes the recursive reasoning.

---

# 11. Recursive Invariants

Many recursive algorithms preserve certain properties throughout execution.

These properties are called **recursive invariants**.

Examples include:

- processed elements remain correct;
- tree structure is preserved;
- recursive decomposition remains valid;
- partial solutions satisfy the original specification.

Every recursive call should preserve these invariants.

---

# 12. Correct Recursive Reduction

Recursive reduction must preserve the meaning of the problem.

Correct reduction:

```text
Sort(Array)

↓

Sort(Left Half)

+

Sort(Right Half)
```

Incorrect reduction:

```text
Original Problem

↓

Different Problem
```

Changing the problem definition breaks correctness.

---

# 13. Correctness and Problem Size

Each recursive call should satisfy:

```text
Same Problem

↓

Smaller Input
```

Not:

```text
Different Problem

↓

Different Meaning
```

Recursive correctness depends on solving exactly the same problem at every level.

---

# 14. Correctness Tree

Recursive correctness may be visualized as:

```text
Original Problem
        ↓
Smaller Problem
        ↓
Smaller Problem
        ↓
Base Case
        ↓
Correct Result
        ↓
Correct Larger Result
        ↓
Correct Original Result
```

Correctness propagates upward through the recursive calls.

---

# 15. Proving Termination

Correctness also requires termination.

Termination is demonstrated by proving that:

- every recursive call simplifies the problem;
- no infinite recursive sequence exists;
- the base case is eventually reached.

Conceptually:

```text
Problem Size

↓

Strictly Smaller

↓

Strictly Smaller

↓

Base Case
```

---

# 16. Progress Measure

A useful technique is defining a **progress measure**.

Examples:

```text
n

↓

n − 1
```

```text
Array Length

↓

Length − 1
```

```text
Tree Height

↓

Smaller Height
```

Every recursive call should reduce this measure.

---

# 17. Well-Founded Ordering

Recursive proofs often rely on a **well-founded ordering**.

Examples include:

- natural numbers;
- remaining array elements;
- tree height;
- graph depth.

A well-founded ordering guarantees that infinite descending sequences cannot exist.

Eventually, the smallest element is reached.

---

# 18. Multiple Recursive Calls

Algorithms such as Merge Sort or Fibonacci perform multiple recursive calls.

Correctness reasoning must verify:

- every recursive branch is correct;
- every branch terminates;
- the combined result preserves correctness.

Each recursive branch is analysed independently.

---

# 19. Divide and Conquer Correctness

Divide and Conquer algorithms typically follow:

```text
Divide
        ↓
Solve Smaller Problems
        ↓
Combine Correctly
```

Correctness depends on:

- correct division;
- correct recursive solutions;
- correct combination.

Every stage must preserve the original problem.

---

# 20. Tree Traversal Correctness

Recursive tree traversal follows a similar argument.

Suppose:

```text
Left Subtree

Correct
```

and:

```text
Right Subtree

Correct
```

Then:

```text
Visit Root

+

Left Result

+

Right Result
```

produces the correct traversal for the complete tree.

---

# 21. Correctness versus Testing

Testing demonstrates:

```text
Some Inputs Work
```

Correctness proves:

```text
Every Valid Input Works
```

Testing is valuable but cannot replace formal reasoning.

A recursive algorithm may pass hundreds of tests while still failing on unseen inputs.

---

# 22. Preconditions

Correctness proofs assume that:

- input satisfies the specification;
- recursive assumptions remain valid;
- recursive calls receive legal arguments.

If preconditions are violated, correctness is no longer guaranteed.

---

# 23. Postconditions

A recursive algorithm should also satisfy explicit postconditions.

Examples:

```text
Sorted Array
```

```text
Correct Tree Traversal
```

```text
Correct Maximum Value
```

Correctness means the algorithm always establishes its postconditions whenever its preconditions hold.

---

# 24. Common Correctness Questions

When reviewing a recursive algorithm, ask:

- Is the base case correct?
- Is the recursive reduction valid?
- Does the recursive call simplify the problem?
- Is the base case reachable?
- Does every recursive branch terminate?
- Are partial results combined correctly?
- Does the final result satisfy the specification?

These questions reveal many design errors before implementation.

---

# 25. Java Perspective

Java does not automatically verify recursive correctness.

The compiler checks syntax only.

Developers are responsible for ensuring:

- termination;
- valid recursive reduction;
- correct returned values;
- preserved invariants.

Recursive correctness is therefore a design responsibility rather than a language feature.

---

# 26. Engineering Perspective

Professional software engineers reason about recursive algorithms before writing code.

A disciplined approach is:

```text
Understand Specification
        ↓
Define Base Case
        ↓
Define Recursive Reduction
        ↓
Prove Termination
        ↓
Prove Correctness
        ↓
Implement
        ↓
Test
```

This process minimizes defects and produces algorithms that are both mathematically sound and practically reliable.

---

# 27. Relationship with the Next Document

This document established how recursive algorithms are proven correct.

The next document studies **Recursive Complexity**, explaining how recursive execution influences time complexity, auxiliary space, recursion depth, and recurrence relations.

Correctness answers:

```text
Does it work?
```

Complexity answers:

```text
How efficiently does it work?
```

---

# 28. Key Takeaways

After completing this document, the learner should understand that:

- recursive correctness requires both termination and correct results;
- recursive proofs closely resemble mathematical induction;
- every proof begins with a correct base case;
- recursive reasoning assumes the correctness of smaller instances;
- recursive reductions must preserve the original problem definition;
- recursive invariants help maintain correctness throughout execution;
- every recursive call must make measurable progress;
- termination depends on eventually reaching the base case;
- testing supports confidence, but formal correctness reasoning provides stronger guarantees;
- proving correctness is an essential step before analysing algorithmic complexity.