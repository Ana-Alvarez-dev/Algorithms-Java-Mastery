# Base and Recursive Cases

## Algorithms Java Mastery

Every recursive algorithm is built upon two essential components:

- the **base case**;
- the **recursive case**.

Together, these two elements define the complete behavior of the algorithm.

The base case determines **when recursion stops**, while the recursive case determines **how the problem is reduced** into progressively smaller instances.

Understanding these concepts is far more important than memorizing recursive syntax. Most recursion errors—including infinite recursion, incorrect results, and excessive stack growth—originate from an incorrectly designed base case or recursive case.

This document studies the structure, purpose, and engineering principles behind these two fundamental components.

---

# 1. Purpose

The purpose of this document is to:

- define the base case and recursive case;
- explain their relationship;
- understand how recursive algorithms terminate;
- identify proper recursive reduction;
- recognize common design patterns;
- establish good engineering practices for recursive algorithm design.

---

# 2. The Structure of Every Recursive Algorithm

Conceptually, every recursive algorithm follows the same structure.

```text
Receive Problem
        ↓
Is It a Base Case?
      /     \
    Yes      No
    ↓         ↓
Return      Reduce Problem
Result          ↓
          Recursive Call
               ↓
        Combine Result
               ↓
            Return
```

Although implementations differ, nearly every recursive algorithm follows this reasoning.

---

# 3. The Base Case

The **base case** is the simplest form of the problem.

It represents a situation whose solution is already known and therefore requires no additional recursive calls.

Conceptually:

```text
Problem Is Simple Enough

↓

Return Directly
```

The base case acts as the stopping condition of recursion.

Without it, recursive execution would never terminate.

---

# 4. Purpose of the Base Case

The base case has several responsibilities.

It:

- stops recursive execution;
- prevents infinite recursion;
- returns a directly computable result;
- allows previous recursive calls to finish;
- begins the process of returning results.

Every recursive algorithm must contain at least one valid base case.

---

# 5. Example of a Base Case

Consider factorial.

Mathematically:

```text
0! = 1
```

The recursive definition becomes:

```text
n!

↓

n × (n − 1)!
```

until:

```text
0!

↓

1
```

The value:

```text
1
```

is returned directly without another recursive call.

---

# 6. Characteristics of a Good Base Case

A well-designed base case should be:

- simple;
- directly computable;
- reachable;
- correct;
- independent of further recursion.

A base case that is never reached is equivalent to having no base case at all.

---

# 7. Multiple Base Cases

Some recursive problems require more than one stopping condition.

Example:

```text
Fibonacci
```

Mathematically:

```text
F(0) = 0

F(1) = 1
```

Both values terminate recursion.

Conceptually:

```text
Input = 0

↓

Return 0
```

```text
Input = 1

↓

Return 1
```

Multiple base cases are common in recursive algorithms.

---

# 8. The Recursive Case

The recursive case describes how a larger problem is reduced into one or more smaller problems of the same type.

Conceptually:

```text
Large Problem

↓

Smaller Problem

↓

Recursive Call
```

The recursive case should always move toward a base case.

---

# 9. Purpose of the Recursive Case

The recursive case is responsible for:

- reducing the problem;
- preserving correctness;
- making measurable progress;
- eventually reaching the base case.

Without continuous progress, recursion cannot terminate.

---

# 10. Recursive Progress

A recursive algorithm must always simplify the problem.

Good reduction:

```text
Problem(n)

↓

Problem(n − 1)
```

Possible alternative:

```text
Problem(n)

↓

Problem(n / 2)
```

Poor reduction:

```text
Problem(n)

↓

Problem(n)
```

No simplification occurs.

Recursion continues indefinitely.

---

# 11. Recursive Reduction Strategies

Problems may be reduced in different ways.

Examples include:

Decrease by one:

```text
n

↓

n − 1
```

Divide by two:

```text
n

↓

n / 2
```

Split into two subproblems:

```text
Problem

↓

Left

+

Right
```

The reduction strategy depends on the algorithm.

---

# 12. One Recursive Call

Many recursive algorithms perform one recursive call.

Example reasoning:

```text
Problem(n)

↓

Problem(n − 1)

↓

Return
```

Examples include:

- factorial;
- linear search (recursive version);
- recursive list traversal.

---

# 13. Multiple Recursive Calls

Some recursive algorithms generate multiple recursive branches.

Example:

```text
Problem

↓

Subproblem A

+

Subproblem B
```

Examples include:

- Fibonacci;
- merge sort;
- quicksort;
- binary tree traversal;
- divide and conquer algorithms.

Multiple recursive calls usually increase the complexity of the algorithm.

---

# 14. Combining Results

Recursive calls often produce intermediate results.

The current invocation combines those results into a larger solution.

Conceptually:

```text
Recursive Result

↓

Current Computation

↓

Return Larger Result
```

Example:

```text
factorial(4)

↓

4 × factorial(3)
```

The multiplication occurs after the recursive call returns.

---

# 15. Recursive Flow

The complete recursive execution consists of two phases.

### Descending Phase

```text
Problem

↓

Smaller Problem

↓

Smaller Problem

↓

Base Case
```

### Ascending Phase

```text
Base Case

↓

Return

↓

Combine Results

↓

Return

↓

Original Result
```

Understanding both phases is essential for reasoning about recursion.

---

# 16. Recursive Expansion Example

Conceptually:

```text
factorial(4)

↓

4 × factorial(3)

↓

4 × 3 × factorial(2)

↓

4 × 3 × 2 × factorial(1)

↓

4 × 3 × 2 × 1
```

The recursive calls expand downward until the base case is reached.

The results are then combined while returning upward.

---

# 17. Visualizing Recursive Calls

Recursive execution can be represented as:

```text
factorial(4)

↓

factorial(3)

↓

factorial(2)

↓

factorial(1)

↓

Return
```

Each level represents a separate method invocation with its own local variables.

The next document explains how these calls are stored inside the JVM Call Stack.

---

# 18. Designing a Recursive Algorithm

A useful design process is:

```text
Understand Problem
        ↓
Identify Simplest Case
        ↓
Define Base Case
        ↓
Determine Smaller Problem
        ↓
Write Recursive Case
        ↓
Verify Progress
        ↓
Verify Termination
```

This systematic approach reduces implementation errors.

---

# 19. Questions to Ask During Design

Before writing a recursive algorithm, ask:

- What is the simplest possible input?
- Can that input be solved immediately?
- How can the larger problem become smaller?
- Does every recursive call simplify the problem?
- Is the base case guaranteed to be reached?
- How are partial results combined?
- Can recursion continue forever?

These questions often reveal design mistakes before implementation begins.

---

# 20. Common Design Patterns

Many recursive algorithms share the following pattern.

```text
if simple case

    return solution

otherwise

    solve smaller problem

    combine results

    return
```

Although the implementation differs between algorithms, the logical structure remains remarkably consistent.

---

# 21. Relationship Between Base and Recursive Cases

Neither part is sufficient by itself.

Base case only:

```text
Stops Immediately
```

Recursive case only:

```text
Never Stops
```

Together:

```text
Recursive Reduction

↓

Base Case

↓

Correct Result
```

The correctness of recursion depends on both components working together.

---

# 22. Java Perspective

A recursive Java method typically contains:

- one or more base cases;
- one recursive case;
- optional work before recursion;
- optional work after recursion.

Every recursive invocation creates a new stack frame.

Each invocation evaluates its own base case independently.

---

# 23. Engineering Perspective

Professional recursive algorithms are designed carefully rather than written by trial and error.

Engineers first verify:

- the stopping condition;
- recursive progress;
- correctness;
- expected recursion depth;
- memory requirements.

A recursive algorithm should terminate for every valid input while preserving correctness throughout every recursive reduction.

---

# 24. Relationship with the Next Document

This document introduced the two structural components of recursion:

```text
Base Case

+

Recursive Case
```

The next document explains how recursive calls are executed internally by the Java Virtual Machine through the **Call Stack**, showing how stack frames are created, managed, and removed during recursive execution.

---

# 25. Key Takeaways

After completing this document, the learner should understand that:

- every recursive algorithm consists of base and recursive cases;
- the base case stops recursive execution;
- the recursive case reduces the problem to smaller instances;
- every recursive call must make measurable progress;
- recursive execution consists of descending and ascending phases;
- recursive algorithms may contain one or multiple recursive calls;
- recursive calls often combine partial results while returning;
- good recursive design begins by identifying the simplest possible problem;
- correct recursive algorithms require both proper termination and correct reduction;
- understanding base and recursive cases is essential before studying the JVM Call Stack.