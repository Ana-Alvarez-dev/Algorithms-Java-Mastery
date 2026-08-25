# Recursive Complexity

## Algorithms Java Mastery

Correctness determines whether a recursive algorithm produces the expected result.

Complexity analysis determines **how efficiently** that algorithm uses computational resources.

Recursive algorithms introduce an additional challenge compared with iterative algorithms because their execution is distributed across multiple method invocations. Consequently, analysing recursive complexity requires understanding:

- the work performed by each recursive call;
- the number of recursive calls;
- the recursion depth;
- additional stack memory;
- recurrence relations.

Recursive complexity does not depend on the presence of recursion itself. Instead, it depends on how recursion reduces the problem and how many recursive calls are generated during execution.

This document introduces the fundamental techniques used to analyse recursive algorithms before studying more advanced topics such as Divide and Conquer and Dynamic Programming.

---

# 1. Purpose

The purpose of this document is to:

- explain recursive complexity analysis;
- distinguish time and space complexity in recursive algorithms;
- introduce recursion depth;
- explain recurrence relations;
- analyse common recursive patterns;
- establish systematic reasoning for recursive performance.

---

# 2. Time Complexity

Time complexity measures how the number of elementary operations grows as the input size increases.

For recursive algorithms, the total execution time depends on:

- the work performed inside each invocation;
- the number of recursive calls.

Conceptually:

```text
Total Work

=

Work per Call

×

Number of Calls
```

---

# 3. Space Complexity

Recursive algorithms require additional memory because each method invocation creates a new stack frame.

Additional space depends primarily on:

```text
Maximum Recursion Depth
```

rather than the total number of recursive calls.

This distinction is extremely important.

---

# 4. Work Performed by One Call

Each recursive invocation usually performs three activities.

```text
Current Work
        ↓
Recursive Call(s)
        ↓
Combine Results
```

The complexity of one invocation depends on the amount of work performed before and after recursion.

---

# 5. Recursion Depth

The **recursion depth** is the maximum number of active recursive calls at any moment.

Example:

```text
factorial(5)

↓

factorial(4)

↓

factorial(3)

↓

factorial(2)

↓

factorial(1)
```

Maximum depth:

```text
5
```

The recursion depth determines stack-space usage.

---

# 6. Linear Recursion

A recursive algorithm that reduces the problem by one element each time produces linear recursion.

Example:

```text
n

↓

n − 1
```

Typical complexity:

```text
Time

O(n)
```

```text
Additional Space

O(n)
```

Examples include:

- factorial;
- recursive linear search;
- recursive list traversal.

---

# 7. Logarithmic Recursion

Some recursive algorithms reduce the problem by half.

Example:

```text
n

↓

n / 2
```

Maximum recursion depth:

```text
O(log n)
```

Examples include:

- recursive binary search;
- balanced tree operations.

The smaller recursion depth significantly reduces stack usage.

---

# 8. Multiple Recursive Calls

Some algorithms create more than one recursive branch.

Example:

```text
Problem

↓

Left

+

Right
```

Examples include:

- merge sort;
- quicksort;
- binary tree traversal;
- Fibonacci.

The complexity depends on both:

- the number of recursive branches;
- the size of each branch.

---

# 9. Constant Work Per Level

Consider factorial.

Each invocation performs only constant work.

```text
Multiply

↓

Recursive Call
```

The total complexity becomes proportional to the number of recursive calls.

Therefore:

```text
Time

O(n)
```

---

# 10. Recurrence Relations

Recursive algorithms are commonly analysed using **recurrence relations**.

A recurrence expresses the running time of a problem in terms of smaller problems.

General form:

```text
T(n)

=

Work

+

Recursive Calls
```

Instead of immediately producing Big-O notation, the recurrence describes how the algorithm behaves recursively.

---

# 11. Example Recurrence

Factorial:

```text
T(n)

=

T(n − 1)

+

O(1)
```

Meaning:

- one recursive call;
- constant additional work.

This recurrence produces:

```text
O(n)
```

time complexity.

---

# 12. Binary Search Recurrence

Recursive binary search reduces the problem by half.

Recurrence:

```text
T(n)

=

T(n / 2)

+

O(1)
```

Solution:

```text
O(log n)
```

The recursion depth also becomes logarithmic.

---

# 13. Merge Sort Recurrence

Merge Sort divides the array into two halves.

Recurrence:

```text
T(n)

=

2T(n / 2)

+

O(n)
```

The two recursive calls solve half-sized problems.

The merge operation processes all elements.

Overall complexity:

```text
O(n log n)
```

---

# 14. Fibonacci Recurrence

Naïve recursive Fibonacci performs two recursive calls.

Recurrence:

```text
T(n)

=

T(n − 1)

+

T(n − 2)

+

O(1)
```

The recursion tree grows rapidly.

Overall time complexity becomes exponential.

Conceptually:

```text
O(2ⁿ)
```

This illustrates that recursion alone is not necessarily efficient.

---

# 15. Recursion Trees

A **recursion tree** visualizes recursive execution.

Example:

```text
Problem

↓

Subproblem

↓

Subproblem

↓

...
```

or:

```text
Problem

↓

Left      Right

↓

Smaller Problems
```

Recursion trees help estimate:

- recursion depth;
- number of calls;
- work performed at each level.

---

# 16. Stack Space

Each recursive call creates one stack frame.

If maximum recursion depth is:

```text
n
```

additional stack space becomes:

```text
O(n)
```

If recursion depth is:

```text
log n
```

stack usage becomes:

```text
O(log n)
```

---

# 17. Total Calls versus Stack Usage

These concepts are different.

Example:

Naïve Fibonacci:

```text
Many Recursive Calls
```

but:

```text
Maximum Depth

O(n)
```

The total number of calls determines execution time.

The maximum simultaneous calls determine stack memory.

---

# 18. Tail Recursion

Tail-recursive algorithms perform no additional work after the recursive call returns.

Conceptually:

```text
Current Work

↓

Recursive Call

↓

Return
```

Tail recursion often simplifies complexity analysis because each level performs constant work.

Its optimization is discussed in the next document.

---

# 19. Balanced and Unbalanced Recursion

Balanced recursion:

```text
Problem

↓

Half

+

Half
```

Unbalanced recursion:

```text
Problem

↓

Almost Entire Problem
```

Balanced recursion generally produces smaller recursion depth and better performance.

---

# 20. Best, Average, and Worst Cases

Recursive algorithms may have different complexities depending on the input.

Example:

Recursive binary search:

```text
Best

O(1)
```

```text
Worst

O(log n)
```

Recursive quicksort:

```text
Best

O(n log n)
```

```text
Worst

O(n²)
```

Complexity analysis should specify the relevant case.

---

# 21. Auxiliary Space

Auxiliary space refers to additional memory required beyond the input itself.

Recursive algorithms usually require auxiliary space because of:

- stack frames;
- local variables;
- temporary structures.

Example:

Recursive factorial:

```text
Auxiliary Space

O(n)
```

---

# 22. Common Complexity Patterns

| Recursive Pattern | Time | Additional Space |
|-------------------|------|------------------|
| One recursive call reducing by one | O(n) | O(n) |
| One recursive call reducing by half | O(log n) | O(log n) |
| Two half-sized recursive calls | O(n log n) | O(log n)* |
| Two overlapping recursive calls | Exponential | O(n) |

\* Ignoring temporary auxiliary arrays.

---

# 23. Complexity Misconceptions

## "Recursion is always slower."

Incorrect.

Binary search remains:

```text
O(log n)
```

whether implemented recursively or iteratively.

---

## "Recursive algorithms always require exponential time."

False.

Many recursive algorithms are linear or logarithmic.

---

## "Recursive complexity depends only on recursion depth."

Incorrect.

Execution time depends on:

- recursion depth;
- branching factor;
- work performed at each call.

---

# 24. Engineering Perspective

Professional engineers analyse recursive algorithms before implementation.

Typical reasoning:

```text
How Much Work
Per Call?

↓

How Many Calls?

↓

Maximum Depth?

↓

Stack Usage?

↓

Overall Complexity
```

This process provides a complete picture of algorithm efficiency.

---

# 25. Relationship with the Next Document

This document introduced the principles used to analyse recursive performance.

The next document studies **Tail Recursion**, explaining how recursive algorithms may be structured to minimize post-recursion work and discussing why Java does not guarantee Tail Call Optimization.

---

# 26. Key Takeaways

After completing this document, the learner should understand that:

- recursive complexity depends on both the work per call and the number of recursive calls;
- recursion depth determines additional stack-space usage;
- recurrence relations describe recursive running time mathematically;
- recursive algorithms may exhibit linear, logarithmic, polynomial, or exponential complexity;
- balanced recursion generally performs better than unbalanced recursion;
- total recursive calls and maximum recursion depth are different concepts;
- auxiliary space in recursive algorithms is primarily determined by stack frames;
- recursion trees help visualize recursive execution and complexity;
- recursive complexity should always be analysed before implementation;
- understanding recursive complexity is essential before studying advanced recursive paradigms such as Divide and Conquer and Dynamic Programming.