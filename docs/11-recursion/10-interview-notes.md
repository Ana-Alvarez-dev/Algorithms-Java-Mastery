# Interview Notes — Recursion

## Algorithms Java Mastery

This document consolidates technical-interview questions and reasoning prompts
related to recursion.

Its purpose is not to encourage memorised answers. The questions are designed
to verify whether the learner can explain recursive behaviour, correctness,
termination, and complexity using the same reasoning developed throughout the
module.

The module remains academically grounded in the concepts studied through
**Introduction to Algorithms (CLRS)** and complementary Java engineering
practice.

---

# 1. What Is Recursion?

**Question**

What is recursion in algorithm design?

**Expected reasoning**

Recursion is a problem-solving technique in which a problem is expressed in
terms of smaller instances of the same problem.

A valid recursive algorithm normally requires:

- one or more base cases;
- a recursive case;
- progress toward a base case;
- a way to combine or propagate recursive results.

A recursive implementation is therefore not defined merely by a method calling
itself.

---

# 2. Why Is a Base Case Necessary?

**Question**

What happens if a recursive algorithm does not define a reachable base case?

**Expected reasoning**

The recursive calls may continue indefinitely until the runtime exhausts the
available call-stack space.

In Java, this typically results in:

```text
StackOverflowError
```

A base case is therefore part of both the functional definition and the
termination argument.

---

# 3. Base Case vs Recursive Case

**Question**

What is the difference between the base case and the recursive case?

**Expected reasoning**

The base case solves a sufficiently small instance directly.

The recursive case reduces the current problem to one or more smaller
subproblems and uses their results to construct the current result.

---

# 4. How Do You Prove That Recursion Terminates?

**Question**

A recursive method has a base case. Is that sufficient to guarantee
termination?

**Expected reasoning**

No.

The recursive calls must also make measurable progress toward that base case.

A useful termination argument identifies a decreasing measure, such as:

```text
n
n - 1
n - 2
...
0
```

or:

```text
n
n / 2
n / 4
...
1
```

The measure must move toward a well-founded terminal condition.

---

# 5. Recursion and Mathematical Induction

**Question**

Why are recursive correctness arguments often related to mathematical
induction?

**Expected reasoning**

Both techniques reason from smaller instances toward larger instances.

A common recursive correctness argument contains:

```text
Base Case
        ↓
Inductive Hypothesis
        ↓
Recursive Step
        ↓
Correct Result
```

The inductive hypothesis assumes correctness for smaller subproblems, and the
inductive step explains why the current problem is therefore solved correctly.

---

# 6. Recursion vs Recurrence Relation

**Question**

Is a recursive algorithm the same thing as a recurrence relation?

**Expected reasoning**

No.

A recursive algorithm describes how a solution is computed.

A recurrence relation commonly describes how a quantity such as running time
depends on smaller input sizes.

For example:

```text
T(n) = 2T(n / 2) + Θ(n)
```

may model the running time of Merge Sort.

It does not by itself prove that Merge Sort produces a correctly sorted
sequence.

---

# 7. What Is Stored on the Java Call Stack?

**Question**

What information is conceptually associated with each recursive call?

**Expected reasoning**

Each invocation requires an execution frame containing information needed to
resume that invocation, such as:

- method parameters;
- local variables;
- return information;
- intermediate execution state.

Recursive depth therefore contributes to space complexity.

---

# 8. Recursive Time and Space Complexity

**Question**

Why can a recursive algorithm have good time complexity but significant space
cost?

**Expected reasoning**

Even if the number of operations grows efficiently, active recursive calls may
remain on the call stack.

For example, a logarithmic recursive search may use:

```text
O(log n)
```

stack space even when no explicit auxiliary collection is created.

---

# 9. Recursive Binary Search

**Question**

What must be true before recursive binary search can be applied correctly?

**Expected reasoning**

The searched sequence must satisfy the ordering precondition required by binary
search.

Each recursive call must also reduce the search interval.

The correctness argument therefore depends on both:

- the sorted-input precondition;
- preservation of the valid search interval.

---

# 10. Recursion vs Iteration

**Question**

When should an iterative solution be preferred over a recursive one?

**Expected reasoning**

The decision depends on the problem structure, readability, stack depth,
language/runtime behaviour, and resource requirements.

Iteration may be preferable when:

- recursion depth can become large;
- the iterative state is simple;
- avoiding call-stack growth is important.

Recursion is often natural when the problem or data structure is inherently
recursive, such as tree traversal or divide-and-conquer algorithms.

---

# 11. Tail Recursion in Java

**Question**

Does Java guarantee tail-call optimisation?

**Expected reasoning**

No.

A tail-recursive Java method should not be assumed to execute in constant stack
space.

This is a language/runtime consideration that may influence the choice between
recursive and iterative implementations.

---

# 12. Common Recursive Failure Modes

A technical discussion should recognise mistakes such as:

- missing base case;
- unreachable base case;
- no progress toward termination;
- incorrect reduction of the problem;
- losing or incorrectly combining recursive results;
- excessive recursion depth;
- exponential recomputation;
- incorrect complexity assumptions.

These problems are developed in:

```text
09-common-mistakes.md
```

---

# 13. Reasoning Exercise — Factorial

**Prompt**

Given:

```text
factorial(n) = n × factorial(n - 1)
factorial(0) = 1
```

Explain:

1. the base case;
2. the recursive case;
3. why execution terminates for non-negative integers;
4. the time complexity;
5. the call-stack space complexity.

**Expected direction**

A complete answer should connect the contract, decreasing input size,
correctness reasoning, and computational cost rather than only reproducing the
formula.

---

# 14. Reasoning Exercise — Fibonacci

**Prompt**

Why is the direct recursive Fibonacci implementation often inefficient?

**Expected direction**

The recursion tree contains repeated subproblems.

The learner should distinguish:

```text
Correct recursive definition
        ≠
Efficient implementation
```

This observation prepares the transition toward memoisation and dynamic
programming.

---

# 15. Reasoning Exercise — Tree Traversal

**Prompt**

Why is recursion a natural technique for traversing a binary tree?

**Expected direction**

A binary tree is recursively structured:

```text
Node
├── Left Subtree
└── Right Subtree
```

A recursive traversal mirrors the structure of the data.

The answer should still consider base cases, correctness, and recursion depth.

---

# 16. Technical Interview Checklist

Before considering the recursion module complete, the learner should be able to
explain without reading notes:

- what recursion is;
- what makes a recursive definition valid;
- the role of the base case;
- why progress is required for termination;
- how the Java call stack relates to recursive execution;
- how to analyse recursive time complexity;
- how to analyse recursive auxiliary space;
- how induction supports correctness reasoning;
- why a recurrence relation is not a correctness proof;
- when iteration may be preferable;
- why Java tail recursion should not be assumed to use constant stack space;
- how recursion prepares the learner for divide and conquer, trees,
  backtracking, and dynamic programming.

---

# 17. Interview Strategy

A strong technical answer should usually follow this sequence:

```text
Define the concept
        ↓
State the preconditions
        ↓
Explain the recursive reduction
        ↓
Identify the base case
        ↓
Explain termination
        ↓
Discuss correctness
        ↓
Analyse time
        ↓
Analyse auxiliary space
        ↓
Mention relevant Java considerations
```

The objective is to demonstrate reasoning, not vocabulary.

---

# References

The recursion module is primarily inspired by the recursive reasoning and
algorithm-analysis framework developed in:

- Cormen, T. H., Leiserson, C. E., Rivest, R. L., and Stein, C.
  *Introduction to Algorithms*. MIT Press.

Additional repository-wide references are maintained in:

```text
docs/00-project/10-references.md
```
