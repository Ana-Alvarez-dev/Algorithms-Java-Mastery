# Common Mistakes

## Algorithms Java Mastery

Recursion is one of the most elegant algorithmic techniques in Computer Science, but it is also one of the easiest to implement incorrectly.

Unlike iterative algorithms, recursive algorithms depend on several conditions being satisfied simultaneously:

- a correct base case;
- a correct recursive case;
- guaranteed progress toward termination;
- valid recursive reasoning;
- controlled recursion depth.

If any of these conditions is violated, the algorithm may produce incorrect results, fail to terminate, or consume excessive computational resources.

Understanding these mistakes is essential because recursive defects are often more difficult to identify than iterative ones. A recursive algorithm may appear correct for small inputs while failing for larger or unexpected cases.

This document presents the most common conceptual, algorithmic, and implementation mistakes encountered when designing recursive algorithms.

---

# 1. Purpose

The purpose of this document is to:

- identify common recursion mistakes;
- explain why these mistakes occur;
- establish good recursive design practices;
- improve recursive debugging skills;
- reinforce correct algorithmic reasoning.

---

# 2. Missing Base Case

The most common recursion error is omitting the base case entirely.

Conceptually:

```text
Problem

↓

Smaller Problem

↓

Smaller Problem

↓

...
```

Since no stopping condition exists, recursion never terminates.

Eventually:

```text
StackOverflowError
```

Every recursive algorithm must define at least one reachable base case.

---

# 3. Incorrect Base Case

A base case may exist but still be incorrect.

Example:

```text
Base Case

↓

Returns Wrong Value
```

Even though recursion terminates, every larger recursive solution becomes incorrect.

Since recursive algorithms build larger solutions from smaller ones, an incorrect base case propagates errors throughout the entire computation.

---

# 4. Unreachable Base Case

Sometimes the base case exists but can never be reached.

Example:

```text
Problem(n)

↓

Problem(n + 1)

↓

Problem(n + 2)

↓

...
```

The recursive calls move away from the stopping condition instead of toward it.

This produces infinite recursion despite having a defined base case.

---

# 5. No Progress Toward the Base Case

Every recursive call must simplify the problem.

Correct reduction:

```text
n

↓

n − 1
```

Incorrect reduction:

```text
n

↓

n
```

or

```text
n

↓

n + 1
```

Without measurable progress, recursion cannot terminate.

---

# 6. Changing the Problem

Recursive calls should solve **the same problem** on a smaller input.

Incorrect reasoning:

```text
Original Problem

↓

Different Problem
```

Changing the meaning of the problem breaks recursive correctness.

Every recursive invocation must preserve the original specification.

---

# 7. Forgetting the Recursive Case

Some implementations correctly handle the base case but never continue solving larger inputs.

Conceptually:

```text
Base Case

↓

Return
```

No recursive reduction exists.

The algorithm simply stops instead of solving the complete problem.

Both the base case and recursive case are essential.

---

# 8. Incorrect Recursive Reduction

A recursive algorithm may reduce the problem incorrectly.

Example:

Correct:

```text
Search Remaining Elements
```

Incorrect:

```text
Skip Elements
```

The recursive call executes successfully but solves the wrong subproblem.

The reduction itself must preserve correctness.

---

# 9. Incorrect Combination of Results

Many recursive algorithms perform work after recursive calls return.

Example:

```text
Recursive Result

↓

Combine

↓

Return
```

If the combination step is incorrect, the entire algorithm becomes incorrect even when the recursive calls themselves are correct.

Examples include:

- Merge Sort;
- tree traversals;
- divide and conquer algorithms.

---

# 10. Infinite Recursion

Infinite recursion occurs when recursive execution never reaches a stopping condition.

Conceptually:

```text
Problem

↓

Recursive Call

↓

Recursive Call

↓

Recursive Call

↓

...
```

Eventually the Call Stack is exhausted.

Infinite recursion is one of the most frequent implementation errors.

---

# 11. Excessive Recursion Depth

Some recursive algorithms terminate correctly but still exceed available stack memory.

Example:

```text
Very Large Input

↓

Very Deep Recursion

↓

StackOverflowError
```

Correctness alone does not guarantee practical execution.

Recursion depth should always be considered.

---

# 12. Ignoring Stack Memory

Every recursive call creates a new stack frame.

Conceptually:

```text
Recursive Call

↓

New Stack Frame
```

Deep recursion consumes additional stack memory.

Ignoring stack usage may cause runtime failures even for correct algorithms.

---

# 13. Assuming Tail Recursion Is Optimized

Some developers assume:

```text
Tail Recursion

↓

Automatic Optimization
```

This assumption is incorrect in Java.

The JVM does **not** guarantee Tail Call Optimization.

Tail-recursive methods may still produce:

```text
StackOverflowError
```

for sufficiently deep recursion.

---

# 14. Duplicating Work

Some recursive algorithms repeatedly solve identical subproblems.

Example:

```text
Problem

↓

Subproblem A

↓

Repeated Again
```

Naïve Fibonacci is the classic example.

Repeated computation produces exponential execution time.

Dynamic Programming solves this problem through memoization or tabulation.

---

# 15. Ignoring Overlapping Subproblems

When recursive calls repeatedly solve the same subproblem, unnecessary computation occurs.

Conceptually:

```text
Problem

↓

Repeated Recursive Calls

↓

Repeated Work
```

Recognizing overlapping subproblems is essential before applying Dynamic Programming.

---

# 16. Confusing Recursion with Iteration

Recursion is not simply a loop written differently.

Recursion:

```text
Problem Reduction
```

Iteration:

```text
Repeated Execution
```

Although equivalent for many problems, they represent different reasoning models.

---

# 17. Choosing Recursion for Every Problem

Not every problem benefits from recursion.

Simple sequential tasks often become unnecessarily complex when implemented recursively.

Examples include:

- counting;
- simple array traversal;
- repetitive numeric calculations.

Recursion should simplify the problem, not complicate it.

---

# 18. Avoiding Recursion Completely

The opposite mistake is refusing to use recursion even when it naturally models the problem.

Examples:

- trees;
- recursive file systems;
- expression parsing;
- backtracking.

Replacing naturally recursive algorithms with complicated iterative logic may reduce readability.

---

# 19. Ignoring Input Size

A recursive solution that works for:

```text
100 Elements
```

may fail for:

```text
10,000,000 Elements
```

Algorithm selection should consider expected recursion depth.

Production software often requires iterative alternatives for very large inputs.

---

# 20. Forgetting Preconditions

Recursive algorithms frequently assume:

- valid input;
- legal arguments;
- decreasing problem size.

If these assumptions are violated, recursion may fail unexpectedly.

Every recursive algorithm should define and respect its preconditions.

---

# 21. Ignoring Edge Cases

Common edge cases include:

- empty input;
- single-element input;
- null references;
- zero values;
- negative values;
- already solved problems.

These situations often correspond to base cases.

Failing to test them frequently produces incorrect recursive behavior.

---

# 22. Misunderstanding Time Complexity

A common misconception is:

```text
Recursive

↓

Slow
```

Incorrect.

Recursive complexity depends on:

- recursive reduction;
- branching factor;
- work performed per call.

Binary Search remains:

```text
O(log n)
```

whether implemented recursively or iteratively.

---

# 23. Misunderstanding Space Complexity

Some developers analyse only execution time.

Recursive algorithms also require:

```text
Stack Memory
```

Additional space depends primarily on recursion depth.

Ignoring auxiliary space leads to incomplete complexity analysis.

---

# 24. Forgetting the Call Stack

Recursive execution is controlled by the JVM Call Stack.

Ignoring this execution model makes debugging much more difficult.

Instead of thinking:

```text
Method Calls Itself
```

think:

```text
New Stack Frame

↓

Later Returns
```

This mental model simplifies recursive reasoning.

---

# 25. Testing Only Small Inputs

Recursive algorithms often appear correct for small examples.

Example:

```text
factorial(5)
```

works correctly.

Later:

```text
factorial(50000)
```

may fail because of stack limitations.

Testing should include:

- small inputs;
- edge cases;
- large inputs;
- invalid inputs.

---

# 26. Debugging by Guessing

Recursive debugging should follow a systematic process.

```text
Verify Base Case
        ↓
Verify Recursive Reduction
        ↓
Verify Progress
        ↓
Trace Recursive Calls
        ↓
Verify Returned Values
```

Random code modifications rarely solve recursive problems.

---

# 27. Better Recursive Design

A disciplined recursive design process is:

```text
Understand Problem
        ↓
Identify Base Case
        ↓
Identify Recursive Case
        ↓
Verify Progress
        ↓
Prove Correctness
        ↓
Analyse Complexity
        ↓
Implement
        ↓
Test
```

Following this sequence significantly reduces recursion errors.

---

# 28. Engineering Perspective

Professional software engineers evaluate recursive algorithms from multiple perspectives.

They verify:

- correctness;
- termination;
- recursion depth;
- stack usage;
- readability;
- maintainability;
- algorithmic complexity.

The objective is not simply to make recursion work, but to produce algorithms that remain correct, efficient, and maintainable in real-world software systems.

---

# 29. Relationship with the Next Document

This document reviewed the conceptual and implementation mistakes commonly encountered when designing recursive algorithms.

The next document, **Interview Notes**, summarizes the complete module by reviewing:

- core recursion concepts;
- recursive reasoning;
- complexity analysis;
- recursion versus iteration;
- common interview questions;
- practical engineering considerations.

It serves as a concise review for technical interviews and revision.

---

# 30. Key Takeaways

After completing this document, the learner should understand that:

- every recursive algorithm requires a correct and reachable base case;
- recursive calls must always make measurable progress toward termination;
- recursive reduction must preserve the original problem;
- recursion consumes stack memory proportional to recursion depth;
- Java does not guarantee Tail Call Optimization;
- duplicated recursive work may produce exponential complexity;
- recursive algorithms should be analysed for both time and space complexity;
- recursion should be selected only when it naturally models the problem;
- systematic reasoning is more effective than trial-and-error debugging;
- disciplined recursive design produces algorithms that are both mathematically correct and suitable for professional software engineering.