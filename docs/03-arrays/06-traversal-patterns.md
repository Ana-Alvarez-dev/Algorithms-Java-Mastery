# Traversal Patterns

## Algorithms Java Mastery

This document introduces the principal **array traversal patterns** used in
algorithm design throughout **Algorithms Java Mastery**.

Traversal is the systematic process of visiting elements or positions according
to a defined strategy.

Although a simple sequential loop is often the first approach considered,
many algorithmic problems become easier to solve when the traversal strategy is
chosen deliberately.

The same array can therefore be traversed in different ways depending on the
problem:

```text
Array
        ↓
Traversal Strategy
        ↓
Algorithmic Pattern
        ↓
Solution
```

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
algorithmic reasoning developed throughout this repository.

The central question addressed throughout this document is:

> **How should an array be traversed to solve a computational problem
> efficiently and systematically?**

---

# Purpose

The purpose of this document is to develop a practical understanding of
reusable traversal patterns.

The learner should not memorise isolated implementations.

Instead, the objective is to recognise:

```text
Problem
        ↓
Required Information
        ↓
Traversal Pattern
        ↓
Algorithm
```

A suitable traversal pattern can reduce:

- unnecessary iterations;
- repeated computation;
- auxiliary memory;
- implementation complexity.

The patterns introduced here become reusable tools throughout the subsequent
searching, sorting, hashing, dynamic programming, and algorithmic-pattern
modules.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define array traversal;
- identify common traversal patterns;
- distinguish one-pointer and multi-pointer strategies;
- select an appropriate traversal pattern for a given problem;
- explain the conditions under which a traversal is linear;
- recognise when previously computed information can be reused;
- analyse traversal complexity;
- apply traversal patterns in Java;
- identify common mistakes when selecting or implementing a traversal strategy.

---

# What Is Traversal?

Traversal is the process of visiting array elements or positions according to a
defined sequence.

For a one-dimensional array:

```text
A[0]
 ↓
A[1]
 ↓
A[2]
 ↓
...
 ↓
A[n - 1]
```

The traversal strategy determines:

- which positions are visited;
- in what order;
- how many times they may be visited;
- what state is maintained during execution.

Traversal is therefore an algorithmic decision, not simply a loop syntax choice.

---

# Why Traversal Patterns Matter

Many beginners attempt to solve every array problem using the same sequential
loop.

A more disciplined approach is to identify the structure of the problem first.

For example:

```text
Need every element
        ↓
Sequential Traversal
```

```text
Need both ends
        ↓
Two-Pointer Pattern
```

```text
Need a contiguous region
        ↓
Sliding Window
```

```text
Need repeated prefix information
        ↓
Prefix Processing
```

The selected pattern influences both the implementation and the resulting
complexity.

---

# Traversal and Problem Solving

Traversal patterns are part of the methodology established in the
**Problem-Solving Guide**.

The progression is:

```text
Problem
        ↓
Specification
        ↓
Recognise Required Information
        ↓
Select Traversal Pattern
        ↓
Design Algorithm
        ↓
Analyse Complexity
        ↓
Implement in Java
```

The traversal pattern should therefore follow the problem rather than habit.

---

# Sequential Traversal

## Definition

Sequential traversal processes elements from the beginning toward the end,
normally visiting each element once.

Conceptually:

```text
[10][20][30][40][50]
 ↓   ↓   ↓   ↓   ↓
```

Typical Java implementation:

```text
for (int value : numbers) {
    process(value);
}
```

or:

```text
for (int i = 0; i < numbers.length; i++) {
    process(numbers[i]);
}
```

---

## Typical Applications

Sequential traversal is appropriate for problems such as:

- calculating a sum;
- finding a maximum;
- finding a minimum;
- counting occurrences;
- validating every element;
- transforming every element;
- computing an aggregate.

---

## Complexity

If every element is processed once and each iteration performs constant work:

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

The exact space complexity depends on what additional structures are used.

---

# Reverse Traversal

## Definition

Reverse traversal processes elements from the last valid index toward the
beginning.

Conceptually:

```text
[10][20][30][40][50]
 ↑   ↑   ↑   ↑   ↑
```

Typical implementation:

```text
for (int i = numbers.length - 1; i >= 0; i--) {
    process(numbers[i]);
}
```

---

## Typical Applications

Reverse traversal is useful for:

- suffix computations;
- right-to-left processing;
- reverse-order transformations;
- algorithms where later elements must be considered before earlier ones.

It is also commonly used when an in-place algorithm must avoid overwriting
unprocessed data.

---

## Complexity

A complete reverse traversal processes all `n` elements:

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

assuming constant additional state.

---

# Indexed Traversal

## Definition

Indexed traversal explicitly tracks the position of each element.

Example:

```text
for (int i = 0; i < numbers.length; i++) {
    System.out.println(i + ": " + numbers[i]);
}
```

This pattern is useful when the algorithm depends on:

- the current index;
- neighbouring indexes;
- relative positions;
- writing to another position;
- modifying the array.

---

## Example

Comparing adjacent elements:

```text
for (int i = 0; i < numbers.length - 1; i++) {
    if (numbers[i] > numbers[i + 1]) {
        return false;
    }
}
```

The index is necessary because the algorithm uses:

```text
i
i + 1
```

---

# Forward and Reverse Traversal Comparison

| Pattern | Direction | Typical Use |
|---|---|---|
| Forward | `0 → n - 1` | Aggregation, search, transformation |
| Reverse | `n - 1 → 0` | Suffix processing, reverse-order algorithms |
| Indexed | Explicit index | Position-dependent algorithms |

All three may have:

```text
Time: Θ(n)
```

when every element is processed once.

The important difference is the information required by the algorithm.

---

# Two-Pointer Pattern

## Definition

The two-pointer pattern maintains two positions simultaneously.

A common configuration uses one pointer near the beginning and another near
the end:

```text
left
 ↓
[10][20][30][40][50]
                 ↑
                right
```

The pointers move according to the problem's rules.

---

## Typical Applications

Two pointers are useful for:

- reversing arrays;
- palindrome checks;
- pair-sum problems on sorted arrays;
- partitioning;
- merging sorted arrays;
- comparing values from both ends.

---

## Example — In-Place Reverse

```text
int left = 0;
int right = numbers.length - 1;

while (left < right) {
    int temp = numbers[left];
    numbers[left] = numbers[right];
    numbers[right] = temp;

    left++;
    right--;
}
```

The pointers move toward each other.

---

## Complexity

When each pointer moves monotonically through the array:

```text
Time: Θ(n)
Auxiliary Space: Θ(1)
```

A two-pointer approach does not automatically guarantee `Θ(n)`.

The complexity depends on how the pointers move and how much work is performed
during each movement.

---

# Two Independent Moving Pointers

The two-pointer idea can also be applied to two different arrays.

For example, when merging sorted arrays:

```text
A: [1][4][7]
     ↑

B: [2][3][8]
     ↑
```

Each pointer advances through its corresponding array.

If each element is processed once:

```text
Time: Θ(n + m)
```

where:

```text
n = length(A)
m = length(B)
```

This is more precise than reducing the problem automatically to a single
parameter.

---

# Sliding Window Pattern

## Definition

A sliding window maintains a contiguous range of elements while the range moves
through the array.

Conceptually:

```text
[10][20][30][40][50][60]

[------]
    ↓

    [------]
        ↓

        [------]
```

The window may be:

- fixed-size;
- variable-size.

The algorithm maintains some state about the current window and updates that
state as the window moves.

---

# Fixed-Size Sliding Window

Suppose the problem asks for the sum of every window containing `k` elements.

A naïve approach may recompute each sum from scratch.

A sliding-window approach reuses the previous result:

```text
Remove outgoing element
        ↓
Add incoming element
        ↓
Update window state
```

This avoids repeated work.

---

## Complexity

After the initial window is constructed, each subsequent step updates the state
in constant time.

For an array of length `n`:

```text
Time: Θ(n)
```

with:

```text
Auxiliary Space: Θ(1)
```

when only a fixed number of variables are maintained.

---

# Variable-Size Sliding Window

Some problems allow the window boundaries to expand and contract.

Conceptually:

```text
left
 ↓
[...............]
           ↑
          right
```

The window changes according to a condition.

Examples include problems involving:

- contiguous subarrays;
- sums under a threshold;
- bounded counts;
- distinct-element conditions.

The linear-time result depends on the fact that the left and right boundaries
move monotonically rather than repeatedly restarting the scan.

---

# Important Distinction

Not every subarray problem can be solved by a sliding window.

The pattern is appropriate when the problem's constraints allow the current
window state to be updated efficiently as boundaries move.

Therefore:

```text
Contiguous Subarray
        ≠
Automatically Sliding Window
```

The problem structure must justify the technique.

---

# Prefix Sum Pattern

## Definition

Prefix processing stores cumulative information from the beginning of the
array.

For an input:

```text
[2][4][3][5]
```

the prefix sums are:

```text
[2][6][9][14]
```

Conceptually:

```text
prefix[i]
    =
sum of elements from index 0 through i
```

---

# Prefix Sum Construction

A prefix-sum array can be constructed with one traversal:

```text
prefix[0] = numbers[0];

for (int i = 1; i < numbers.length; i++) {
    prefix[i] = prefix[i - 1] + numbers[i];
}
```

For `n` elements:

```text
Preprocessing Time: Θ(n)
Auxiliary Space: Θ(n)
```

when a separate prefix array is created.

---

# Range Queries with Prefix Sums

Once prefix sums have been constructed, a range sum can be computed without
traversing the complete range again.

For an inclusive range `[l, r]`:

```text
sum(l, r) =
prefix[r] - prefix[l - 1]
```

with a separate boundary case when:

```text
l = 0
```

After preprocessing:

```text
Range Query: Θ(1)
```

under the static-array model.

This assumes that the underlying values are not modified without updating the
prefix structure.

---

# Prefix Sum Trade-Off

Prefix sums illustrate a classic time-space trade-off:

```text
Additional Memory
        ↓
Θ(n)
```

provides:

```text
Fast Repeated Range Queries
        ↓
Θ(1) per Query
```

after preprocessing.

Therefore, preprocessing is worthwhile only when the problem requires enough
queries to justify the additional memory and construction cost.

---

# Suffix Traversal

## Definition

Suffix traversal processes elements from the end toward the beginning while
maintaining cumulative information about the suffix.

For example:

```text
Input

[2][4][3][5]
```

A suffix-sum representation is:

```text
[14][12][8][5]
```

because each position represents the sum from that position through the end.

---

## Complexity

Construction requires processing each element once:

```text
Time: Θ(n)
```

A separate suffix array requires:

```text
Auxiliary / Additional Space: Θ(n)
```

An in-place version can reduce additional storage when modifying the original
array is acceptable.

---

# Typical Uses of Suffix Processing

Suffix-based traversal is useful for:

- suffix sums;
- right-to-left state calculations;
- future-state information;
- prefix/suffix decomposition;
- dynamic programming preprocessing.

---

# Partition Pattern

## Definition

Partition traversal divides an array into logical regions according to a
condition.

Conceptually:

```text
[ Processed ][ Unprocessed ]
```

or:

```text
[ Smaller ][ Unknown ][ Larger ]
```

The exact regions depend on the algorithm.

---

# Example — Partitioning Around a Pivot

A partition algorithm may reorganise elements so that:

```text
values < pivot
        ↓
values ≥ pivot
```

The elements are not necessarily fully sorted.

Partitioning is an important component of:

- Quick Sort;
- selection algorithms;
- Dutch National Flag problems.

---

## Complexity

A standard single-pass partition may process each element a constant number of
times:

```text
Time: Θ(n)
```

with:

```text
Auxiliary Space: Θ(1)
```

for an in-place implementation.

---

# Prefix / Suffix Decomposition

Sometimes a problem can be solved by combining information from both
directions.

Conceptually:

```text
Prefix Information
        +
Suffix Information
        ↓
Complete Solution
```

For example:

```text
Prefix Maximum
        +
Suffix Maximum
```

may be used in certain array problems where information from both sides is
required.

This demonstrates how traversal patterns can be combined rather than used in
isolation.

---

# Nested Traversal

Multidimensional arrays naturally require nested traversal.

For a rectangular matrix:

```text
[ ][ ][ ]
[ ][ ][ ]
[ ][ ][ ]
```

a typical traversal is:

```text
for (int row = 0; row < matrix.length; row++) {
    for (int column = 0; column < matrix[row].length; column++) {
        process(matrix[row][column]);
    }
}
```

---

## Complexity

For:

```text
R = rows
C = columns
```

a complete traversal requires:

```text
Θ(RC)
```

time.

If:

```text
R = C = n
```

then:

```text
Θ(n²)
```

is the resulting bound.

The dimensions should remain separate when they are independent.

---

# Traversal with Early Termination

Some traversal patterns do not necessarily process the complete array.

Example:

```text
Search for Target
        ↓
Target Found
        ↓
Stop
```

This produces:

```text
Best Case: Θ(1)
Worst Case: Θ(n)
```

Linear search is a typical example.

Early termination therefore affects case analysis but does not automatically
change the worst-case bound.

---

# Traversal with State

Many array algorithms maintain state while traversing.

Examples include:

```text
Current Maximum
Current Minimum
Running Sum
Occurrence Count
Two Pointers
Window State
Prefix Value
```

Conceptually:

```text
Current Element
        ↓
Update State
        ↓
Next Element
        ↓
Update State
```

This is the basis of many single-pass algorithms.

---

# Traversal and Invariants

Traversal patterns become especially powerful when combined with invariants.

For example, in maximum search:

> After processing the first `i` elements, the maintained maximum is the
> largest value among those elements.

In a sliding window:

> The maintained state correctly represents the current window.

In partitioning:

> Elements already placed in each processed region satisfy the partition
> condition.

Invariant-based reasoning connects traversal directly with correctness.

---

# Traversal and Complexity

The number of times positions are processed is a central determinant of
complexity.

Examples:

```text
One Full Pass
        ↓
Θ(n)
```

```text
Two Monotonic Pointers
        ↓
Often Θ(n)
```

```text
Nested Full Traversal
        ↓
Θ(n²)
```

```text
Matrix Traversal
        ↓
Θ(RC)
```

However, these are patterns rather than automatic rules.

The actual bounds depend on:

- loop conditions;
- pointer movement;
- repeated work;
- early termination;
- nested dimensions;
- auxiliary operations.

---

# Traversal Patterns and Reuse of Computation

A major purpose of traversal patterns is to avoid recomputing information.

Consider repeated range sums.

A naïve approach may repeatedly traverse ranges:

```text
Query 1
        ↓
Traverse

Query 2
        ↓
Traverse

Query 3
        ↓
Traverse
```

Prefix sums change the pattern:

```text
Preprocess Once
        ↓
Prefix Array
        ↓
Fast Range Queries
```

Similarly, a sliding window avoids recomputing an entire contiguous range after
every shift.

This principle is central to algorithmic optimisation:

> **Reuse information whenever the problem structure allows it.**

---

# Pattern Selection Guide

Before choosing a traversal pattern, ask:

```text
Do I need to process every element?
        ↓
Sequential Traversal
```

```text
Do I need information from both ends?
        ↓
Two Pointers
```

```text
Is the problem about a contiguous region?
        ↓
Consider Sliding Window
```

```text
Do I need repeated cumulative information?
        ↓
Prefix / Suffix Processing
```

```text
Does the problem divide elements into logical regions?
        ↓
Partition
```

```text
Is the structure multidimensional?
        ↓
Nested Traversal
```

These questions help identify the most appropriate algorithmic pattern.

---

# Pattern Selection Is Not Automatic

A problem should not be forced into a known pattern simply because the pattern
is familiar.

For example:

```text
Subarray Problem
        ≠
Automatically Sliding Window
```

and:

```text
Nested Loops
        ≠
Automatically Θ(n²)
```

The strategy must be justified by the problem's properties.

---

# Comparative Summary

| Pattern | Typical Time | Additional Space | Typical Applications |
|---|---:|---:|---|
| Sequential Traversal | `Θ(n)` | `Θ(1)` | Aggregation, validation |
| Reverse Traversal | `Θ(n)` | `Θ(1)` | Suffix processing |
| Indexed Traversal | `Θ(n)` when full | `Θ(1)` | Position-dependent algorithms |
| Two Pointers | Often `Θ(n)` | Often `Θ(1)` | Pairs, reverse, merge |
| Fixed-Size Sliding Window | `Θ(n)` | Often `Θ(1)` | Contiguous windows |
| Variable-Size Sliding Window | Often `Θ(n)` | Often `Θ(1)` | Constraint-based windows |
| Prefix Sum | `Θ(n)` preprocessing | `Θ(n)` if separate | Range queries |
| Suffix Processing | `Θ(n)` | `Θ(n)` if separate | Right-to-left state |
| Partition | `Θ(n)` | `Θ(1)` in place | Quick Sort |
| Matrix Traversal | `Θ(RC)` | `Θ(1)` auxiliary | Grids, matrices |

These are typical results under the corresponding implementation assumptions.

The pattern itself does not determine the complexity without analysing the
actual algorithm.

---

# Choosing the Correct Pattern

Before implementing an array solution, ask:

- What information must be maintained?
- Must every element be visited?
- Can execution terminate early?
- Are two positions sufficient to represent the relevant state?
- Is the problem based on a contiguous window?
- Can previous computations be reused?
- Does the array need to be divided into logical regions?
- Are multiple dimensions involved?
- Does preprocessing reduce repeated work?
- Is additional memory acceptable?
- Can pointer movement remain monotonic?

These questions guide pattern selection more reliably than memorising templates.

---

# Relationship with Common Algorithms

Traversal patterns support many algorithms already introduced in this module.

| Algorithm | Main Traversal Pattern |
|---|---|
| Maximum Search | Sequential Traversal |
| Minimum Search | Sequential Traversal |
| Sum | Sequential Traversal |
| Count Occurrences | Sequential Traversal |
| Linear Search | Sequential + Early Termination |
| Reverse | Two Pointers |
| Merge Sorted Arrays | Two Pointers |
| Duplicate Detection | Pairwise Traversal or Auxiliary Hashing |
| Quick Sort Partition | Partition |
| Range Sum Queries | Prefix Sum |
| Matrix Processing | Nested Traversal |

This table demonstrates that algorithm design often involves selecting and
combining a small number of reusable ideas.

---

# Relationship with Foundations

Traversal patterns apply the reasoning principles introduced in:

```text
docs/01-foundations/
```

The process is:

```text
Problem
        ↓
Specification
        ↓
Required Information
        ↓
Traversal Pattern
        ↓
Algorithm
        ↓
Correctness
```

For example, before selecting two pointers, the developer should determine
whether the problem actually depends on two moving positions.

---

# Relationship with Complexity

Traversal patterns also apply the analysis methodology established in:

```text
docs/02-complexity/
```

The analysis asks:

```text
How many times can each position be processed?
        ↓
How many operations occur per processing step?
        ↓
What is the resulting growth?
```

For example:

```text
Single Pass
        ↓
n positions
        ↓
Θ(n)
```

while:

```text
Two Nested Full Traversals
        ↓
n × n position pairs
        ↓
Θ(n²)
```

and:

```text
Matrix
R × C
        ↓
Θ(RC)
```

---

# Traversal and Java

Java provides several syntactic mechanisms for implementing traversal.

For values only:

```text
for (int value : numbers) {
    process(value);
}
```

When indexes are required:

```text
for (int i = 0; i < numbers.length; i++) {
    process(i, numbers[i]);
}
```

For matrices:

```text
for (int row = 0; row < matrix.length; row++) {
    for (int column = 0; column < matrix[row].length; column++) {
        process(matrix[row][column]);
    }
}
```

The syntax changes the implementation.

It does not change the underlying algorithmic pattern.

---

# Traversal and Testing

Traversal algorithms should be tested against the cases defined by their
contracts.

Examples include:

```text
Empty Array
Single Element
Multiple Elements
Duplicate Values
Already Sorted
Reverse Sorted
Negative Values
```

For pointer-based algorithms, also test boundary conditions such as:

```text
left = right
left > right
```

For window-based algorithms, test:

```text
Empty Window
Window Size = 1
Window Size = n
```

when these states belong to the valid input domain.

---

# Common Mistakes

## Using Sequential Traversal for Every Problem

A simple `for` loop is useful, but it is not always the most appropriate
strategy.

---

## Assuming Two Pointers Always Produce Θ(n)

Two pointers often produce linear algorithms when both pointers move
monotonically and each position is processed a bounded number of times.

The actual algorithm must still be analysed.

---

## Treating Every Subarray Problem as Sliding Window

Sliding-window methods require a structure that allows the window state to be
updated efficiently as boundaries move.

Not every subarray problem satisfies this condition.

---

## Recomputing Values Unnecessarily

Repeatedly calculating the same range sum or window aggregate can introduce
unnecessary work.

Consider whether:

```text
Prefix Sum
```

or:

```text
Sliding Window
```

can reuse previous computation.

---

## Assuming Prefix Sums Always Give O(1) Range Queries

The `Θ(1)` query result assumes the prefix structure is already built and the
underlying values have not been changed without updating the prefix data.

The preprocessing itself requires:

```text
Θ(n)
```

time and, for a separate prefix array:

```text
Θ(n)
```

additional space.

---

## Assuming Nested Loops Automatically Mean Θ(n²)

The bounds of the loops must be analysed.

For multidimensional arrays:

```text
R × C
```

may be more appropriate than:

```text
n²
```

---

## Confusing Traversal with the Complete Algorithm

Traversal describes how elements are visited.

The complete algorithm also includes:

- state;
- conditions;
- invariants;
- data structures;
- output construction;
- complexity.

---

# Best Practices

When designing array algorithms:

- identify the traversal requirement before writing code;
- choose the pattern according to the problem structure;
- minimise repeated work;
- maintain useful state during traversal;
- exploit monotonic pointer movement when applicable;
- reuse previous calculations when possible;
- preserve independent input dimensions;
- derive complexity from actual processing;
- document important invariants;
- test boundary conditions;
- avoid forcing a familiar pattern onto an unsuitable problem.

The goal is to make traversal a deliberate algorithmic decision.

---

# Systematic Traversal Analysis

Before implementing a traversal-based solution, ask:

```text
1. What elements must be visited?
        ↓
2. In what order?
        ↓
3. Must every element be visited?
        ↓
4. Can the algorithm terminate early?
        ↓
5. How many pointers or indexes are required?
        ↓
6. Is the problem based on a contiguous window?
        ↓
7. Can previous calculations be reused?
        ↓
8. Can the state be maintained incrementally?
        ↓
9. What is the cost of each step?
        ↓
10. What is the total asymptotic complexity?
```

This checklist should become part of the standard problem-solving workflow.

---

# Relationship with Future Modules

Traversal patterns will reappear throughout the repository.

```text
Searching
        ↓
Sequential Traversal
        ↓
Binary Search
```

```text
Sorting
        ↓
Partition
        ↓
Two Pointers
        ↓
Merge
```

```text
Hashing
        ↓
Sequential Insertion / Lookup Processing
```

```text
Trees
        ↓
Tree Traversals
```

```text
Graphs
        ↓
Traversal and State Tracking
```

```text
Dynamic Programming
        ↓
Prefix / State-Ordered Processing
```

```text
Algorithmic Patterns
        ↓
Two Pointers
        ↓
Sliding Window
        ↓
Prefix Techniques
```

The patterns introduced here therefore form reusable concepts rather than
isolated array techniques.

---

# Expected Outcome

After completing this document, the learner should be able to recognise a
traversal problem and ask:

```text
What information must the algorithm maintain?
```

Then select a suitable pattern:

```text
Every Element
        ↓
Sequential Traversal
```

```text
Both Ends
        ↓
Two Pointers
```

```text
Contiguous Region
        ↓
Sliding Window
```

```text
Repeated Cumulative Information
        ↓
Prefix / Suffix Processing
```

```text
Logical Regions
        ↓
Partition
```

```text
Multiple Dimensions
        ↓
Nested Traversal
```

The learner should then derive the complexity from the actual pointer and index
movement rather than from the pattern name alone.

---

# Key Takeaways

After studying this document, the learner should remember that:

- traversal is the systematic process of visiting array elements or positions;
- sequential traversal is one pattern among many;
- reverse traversal is useful for right-to-left processing;
- indexed traversal is appropriate when position matters;
- two pointers often reduce unnecessary repeated scanning;
- sliding windows reuse information across adjacent contiguous ranges;
- prefix sums support fast repeated range queries after preprocessing;
- suffix processing supports right-to-left cumulative state;
- partitioning divides an array into logical regions;
- matrix traversal naturally depends on multiple dimensions;
- early termination affects best and worst cases;
- pointer movement must be analysed rather than assumed to be linear;
- pattern selection should follow the problem structure;
- previously computed information should be reused when the problem permits it;
- traversal is a component of an algorithm, not the complete algorithm itself.

Traversal patterns therefore provide one of the most reusable algorithmic tools
introduced in the **Arrays** module.

They establish the bridge between simple array iteration and the more advanced
algorithmic patterns that will appear throughout **Searching**, **Sorting**,
**Dynamic Programming**, and subsequent modules of **Algorithms Java Mastery**.

---

# Next Document

```text
07-common-algorithms.md
```

The next document applies the traversal patterns introduced here to
representative array algorithms.

It connects:

```text
Traversal Pattern
        ↓
Algorithmic Strategy
        ↓
Correctness
        ↓
Complexity
        ↓
Java Implementation
```

The next central question becomes:

> **How can reusable traversal patterns be transformed into complete,
> correct, and efficient array algorithms?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The traversal methodology also builds directly upon:

```text
docs/01-foundations/
docs/02-complexity/
03-arrays/
```

Java-specific examples follow the Java language and standard library model.

Additional academic and technical references are documented in:

```text
docs/00-project/10-references.md
```