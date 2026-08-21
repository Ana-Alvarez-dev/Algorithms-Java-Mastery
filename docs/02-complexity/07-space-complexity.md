# Space Complexity

## Algorithms Java Mastery

This document introduces **space complexity** as the analysis of how the memory
required by an algorithm grows as the size of the input increases.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic analysis of algorithms throughout this repository.

The previous document examined **time complexity**, which models computational
work.

This document studies a second fundamental computational resource:

> **Memory.**

The central question addressed throughout this document is:

> **How does the memory required by an algorithm grow as the problem size
> increases?**

---

# Purpose

The purpose of this document is to establish a systematic methodology for
analysing algorithmic memory requirements.

Space analysis does not primarily ask:

> How many megabytes did this execution use?

Instead, it asks:

> **How does the required memory grow relative to the size of the problem?**

The general progression is:

```text
Algorithm
        ↓
Define Input Size
        ↓
Identify Memory Components
        ↓
Model Memory Requirements
        ↓
Analyse Growth
        ↓
Establish Asymptotic Space Bound
```

The objective is to understand memory behaviour at the algorithmic level before
considering implementation-specific measurements.

---

# Learning Objectives

After studying this document, the learner should be able to:

* define space complexity;
* distinguish total space from auxiliary space;
* distinguish input storage from additional algorithmic memory;
* identify temporary data structures created during execution;
* analyse stack usage in recursive algorithms;
* understand how recursion depth affects memory consumption;
* interpret in-place algorithms carefully;
* construct simple space-cost functions;
* apply asymptotic notation to memory growth;
* recognise time-space trade-offs;
* distinguish theoretical space complexity from concrete memory measurement.

These competencies provide the spatial counterpart to the time-complexity
analysis developed previously.

---

# What Is Space Complexity?

**Space complexity** describes how the memory required by an algorithm changes
relative to the size of its input.

Conceptually:

```text
Input Size
        ↓
Algorithm
        ↓
Memory Requirement
        ↓
Space-Cost Function
        ↓
Asymptotic Growth
```

The analysis focuses on growth rather than exact byte counts.

---

# Memory as a Computational Resource

Algorithms consume memory while solving computational problems.

Conceptually:

```text
Algorithmic Resources
        │
        ├── Computational Work
        │       ↓
        │   Time Complexity
        │
        └── Memory
                ↓
            Space Complexity
```

Time and space answer different analytical questions.

An algorithm may perform relatively little computation while requiring large
amounts of memory.

Another may minimise memory at the cost of additional computation.

---

# Space-Cost Functions

Memory requirements may be represented by a function such as:

```text
S(n)
```

where:

```text
n = input size
S(n) = memory requirement associated with input size n
```

Examples include:

```text
S(n) = c
```

```text
S(n) = n
```

```text
S(n) = n²
```

The exact form depends on what memory is being counted.

Therefore, every space analysis should make its memory model explicit.

---

# Total Space and Auxiliary Space

Two related but distinct concepts are useful.

## Total Space

Total space may include:

```text
Input Storage
        +
Additional Memory Required by the Algorithm
```

Conceptually:

```text
Total Space
        =
Input Space
        +
Auxiliary Space
```

## Auxiliary Space

**Auxiliary space** refers to additional memory used by the algorithm beyond the
storage required for the original input.

This distinction is especially useful when comparing algorithms operating on
the same input representation.

---

# Input Storage

The input itself occupies memory.

For example:

```java
int[] numbers = new int[n];
```

requires storage proportional to the number of elements.

Conceptually:

```text
Input Size n
        ↓
Input Storage
        ↓
Proportional to n
```

If the analytical objective is **total space**, this storage may be included.

If the objective is **auxiliary space**, the original input storage is normally
excluded.

Therefore:

> **Whether input memory is counted depends on which space model is being
> reported.**

---

# Auxiliary Memory

Auxiliary memory includes additional storage required by the algorithm during
execution.

Examples may include:

* temporary arrays;
* buffers;
* stacks;
* queues;
* hash tables;
* auxiliary collections;
* copied data;
* recursion stack frames.

For example:

```java
int[] temporary = new int[n];
```

introduces additional memory proportional to `n`.

A simplified auxiliary-space function may therefore be:

```text
S_aux(n) = n
```

with tight asymptotic growth:

```text
Θ(n)
```

---

# Fixed Auxiliary Memory

Some algorithms require only a fixed number of additional variables.

For example:

```java
int maximum = numbers[0];
int index = 0;
```

If the number of such variables does not depend on input size, the auxiliary
memory is constant.

Conceptually:

```text
n increases
        ↓
Number of additional variables remains fixed
```

Therefore:

```text
S_aux(n) ∈ Θ(1)
```

under the adopted memory model.

---

# Temporary Data Structures

Additional data structures often dominate auxiliary-space complexity.

Examples include:

```text
Temporary Array of Size n
        ↓
Θ(n) Auxiliary Space
```

```text
Matrix of Size n × n
        ↓
Θ(n²) Auxiliary Space
```

```text
Queue Containing Up to n Elements
        ↓
Θ(n) Auxiliary Space
```

The analysis should identify the **maximum simultaneously live memory**, not
simply every allocation that occurs over the entire execution.

---

# Peak Memory vs Cumulative Allocation

Space complexity generally concerns how much memory is required at the same time.

This is different from the total amount of memory allocated over the lifetime
of the computation.

For example:

```text
Allocate Temporary Object
        ↓
Discard It
        ↓
Allocate Another Temporary Object
```

may perform many allocations while maintaining only a small amount of live
memory at once.

Therefore:

```text
Cumulative Allocations
        ≠
Peak Space Requirement
```

For algorithmic space analysis, peak simultaneous memory is usually the more
relevant model.

---

# The Call Stack

Recursive algorithms consume additional memory through the runtime call stack.

Each active recursive invocation normally requires a stack frame containing
information such as:

* parameters;
* local variables;
* return information;
* execution state.

Conceptually:

```text
Call 1
        ↓
Call 2
        ↓
Call 3
        ↓
...
```

The important quantity is the **maximum number of active calls at the same
time**.

---

# Recursion Depth

Suppose a recursive algorithm has maximum recursion depth:

```text
d(n)
```

and each active call requires constant additional stack space.

Then the stack contribution may be modelled as:

```text
S_stack(n) ∝ d(n)
```

For example, if:

```text
d(n) = n
```

then:

```text
S_stack(n) ∈ Θ(n)
```

If:

```text
d(n) = log n
```

then:

```text
S_stack(n) ∈ Θ(log n)
```

The number of total recursive calls and the maximum recursion depth are not
necessarily the same.

---

# Total Recursive Calls vs Active Stack Frames

Consider a divide-and-conquer algorithm that generates many recursive calls.

The total number of calls may be large.

However, calls in separate branches may not remain active simultaneously.

Conceptually:

```text
Total Recursive Calls
        ≠
Maximum Recursion Depth
```

Space analysis therefore requires reasoning about the maximum active recursion
path.

This is different from time analysis, which may count all recursive work.

---

# Example — Linear Recursive Depth

Consider a recursive algorithm reducing the problem by one:

```text
solve(n)
    ↓
solve(n - 1)
    ↓
solve(n - 2)
    ↓
...
```

Maximum active depth:

```text
n
```

If each frame requires constant additional space:

```text
S_aux(n) ∈ Θ(n)
```

even if no explicit array or collection is allocated.

---

# Example — Logarithmic Recursive Depth

Consider a recursive problem that halves its size:

```text
n
        ↓
n / 2
        ↓
n / 4
        ↓
n / 8
        ↓
...
```

The maximum recursion depth grows logarithmically.

Assuming constant stack space per call:

```text
S_aux(n) ∈ Θ(log n)
```

---

# Iterative Algorithms

Iterative algorithms often use fixed auxiliary memory, but this is not
automatic.

For example:

```java
for (int i = 0; i < n; i++) {
    process(numbers[i]);
}
```

may require only a fixed number of variables.

Then:

```text
S_aux(n) ∈ Θ(1)
```

However, an iterative algorithm may still allocate:

* arrays;
* queues;
* maps;
* buffers.

Therefore:

> **Iteration does not automatically imply constant space.**

---

# In-Place Algorithms

An algorithm is commonly described as **in-place** when it transforms its input
using only a small amount of additional memory beyond the input representation.

For example:

```text
Original Array
        ↓
Elements Rearranged Directly
        ↓
No Auxiliary Array Proportional to n
```

Many in-place algorithms use:

```text
Θ(1)
```

auxiliary space.

However, the exact definition of “in-place” can vary by context, especially for
recursive algorithms.

Therefore, the repository should state the actual auxiliary-space bound rather
than relying only on the label.

---

# Examples of In-Place Processing

Algorithms commonly implemented in place include:

* selection sort;
* insertion sort;
* heap sort;
* some partitioning procedures.

For example, if a sorting algorithm only uses:

```text
index variables
temporary swap variable
```

independently of `n`, then its auxiliary space may be:

```text
Θ(1)
```

even though the input array itself occupies:

```text
Θ(n)
```

memory.

---

# Input Space vs Auxiliary Space Example

Suppose an algorithm receives:

```text
int[] numbers
```

containing `n` integers and uses only:

```text
int current;
int maximum;
```

Then:

```text
Input Space
        ↓
Θ(n)
```

while:

```text
Auxiliary Space
        ↓
Θ(1)
```

If total space is reported:

```text
Total Space
        ↓
Θ(n)
```

This example demonstrates why the reported metric must be named explicitly.

---

# Algorithms Requiring Additional Memory

Some algorithms deliberately allocate memory proportional to the problem size.

For example, a merge-based sorting implementation may require temporary
storage for elements being merged.

Conceptually:

```text
Input Array
        +
Temporary Storage
        ↓
Additional Memory
```

If temporary storage grows proportionally to `n`:

```text
S_aux(n) ∈ Θ(n)
```

under the selected implementation model.

---

# Example — Merge Sort

A typical merge-sort implementation may allocate auxiliary storage proportional
to the input size.

Conceptually:

```text
Input
        ↓
Recursive Decomposition
        ↓
Temporary Merge Storage
```

The analysis should consider both:

* temporary storage;
* recursion stack depth.

Depending on the implementation, the dominant auxiliary-space component may
still be linear.

The exact result should be derived from the implementation strategy being
analysed.

---

# Example — Binary Search

Binary search illustrates how implementation strategy may affect auxiliary
space.

## Iterative Version

An iterative implementation may use only a fixed number of variables:

```text
left
right
middle
```

Therefore:

```text
S_aux(n) ∈ Θ(1)
```

## Recursive Version

A recursive implementation may create one active call per search-space
reduction.

Maximum depth:

```text
Θ(log n)
```

Therefore:

```text
S_aux(n) ∈ Θ(log n)
```

The underlying search strategy is similar, but the implementation structure
changes the auxiliary-space requirements.

---

# Example — Breadth-First Search

A breadth-first graph traversal typically maintains a queue.

The queue may contain multiple vertices simultaneously.

Depending on the graph structure, auxiliary memory may grow with the number of
vertices.

Conceptually:

```text
Graph
        ↓
Traversal
        ↓
Queue + Visited State
        ↓
Auxiliary Memory
```

The exact bound depends on the representation and algorithmic assumptions and
will be analysed in the graph module.

---

# Example — Dynamic Programming

Dynamic programming often illustrates an explicit time-space trade-off.

A naïve recursive strategy may repeatedly recompute the same subproblems.

Dynamic programming may instead store previously computed results.

Conceptually:

```text
More Stored Results
        ↓
Less Repeated Computation
```

The additional memory may substantially reduce computational work.

This relationship becomes central in the dedicated dynamic-programming module.

---

# Time-Space Trade-Offs

Algorithms frequently exchange one resource for another.

Conceptually:

```text
Additional Memory
        ↓
Store Intermediate Results
        ↓
Reduce Repeated Computation
```

or:

```text
Minimal Additional Memory
        ↓
Recompute Information
        ↓
Increase Computational Work
```

This relationship is known as a **time-space trade-off**.

Neither choice is automatically superior.

---

# Evaluating Time-Space Trade-Offs

Relevant considerations may include:

* input size;
* available memory;
* latency requirements;
* implementation complexity;
* expected workload;
* data reuse;
* system constraints.

An engineering decision should therefore consider both:

```text
T(n)
```

and:

```text
S(n)
```

when both resources are significant.

---

# Time and Space Are Independent Dimensions

An algorithm may have:

```text
Excellent Time Growth
```

but:

```text
Large Auxiliary Space
```

or:

```text
Minimal Auxiliary Space
```

but:

```text
Greater Time Cost
```

Therefore:

```text
Time Complexity
        ≠
Space Complexity
```

Both describe different aspects of computational behaviour.

---

# Space Complexity and Asymptotic Notation

Asymptotic notation applies to space functions just as it applies to time
functions.

For example:

```text
S_aux(n) ∈ Θ(1)
```

```text
S_aux(n) ∈ Θ(log n)
```

```text
S_aux(n) ∈ Θ(n)
```

```text
S_aux(n) ∈ Θ(n²)
```

The notation describes the growth of the selected memory function.

It does not inherently mean time.

---

# Multi-Parameter Space Complexity

Some algorithms require memory depending on multiple input dimensions.

For example:

```text
S(r, c)
```

for a matrix algorithm.

A temporary matrix containing:

```text
r × c
```

elements may require:

```text
Θ(rc)
```

space.

Likewise, graph algorithms may express space in terms of:

```text
V
```

and:

```text
E
```

depending on the representation and auxiliary structures used.

---

# Case-Dependent Space Complexity

Memory usage may sometimes vary among valid inputs of the same size.

For example, recursion depth may depend on structural properties of the input.

One may therefore define:

```text
S_best(n)
S_average(n)
S_worst(n)
```

when those distinctions are meaningful.

Case analysis is not restricted to time.

However, separate cases should only be introduced when memory requirements
actually vary in a meaningful way.

---

# Space Complexity and Scalability

Memory requirements can limit practical input size.

Conceptually:

```text
Input Size Increases
        ↓
Memory Requirement Grows
        ↓
Available Memory Reached
        ↓
Practical Limit
```

An algorithm whose auxiliary memory grows rapidly may become infeasible even
when its computational work remains acceptable.

However, practical memory scalability also depends on:

* object representation;
* runtime overhead;
* garbage collection;
* available heap;
* native memory;
* data layout.

Space complexity provides the theoretical growth model.

---

# Theoretical Space vs Measured Memory

Space complexity should not be confused with exact memory consumption.

For example:

```text
S_aux(n) ∈ Θ(n)
```

does not specify:

```text
128 MB
```

or:

```text
500 MB
```

Those values depend on the implementation and runtime environment.

Conceptually:

```text
Space Complexity
        ↓
Growth Model
```

versus:

```text
Measured Memory
        ↓
Concrete Runtime Observation
```

---

# Java-Specific Memory Considerations

A Java implementation introduces practical memory effects such as:

* object headers;
* reference sizes;
* primitive representation;
* array metadata;
* alignment;
* garbage collection;
* JVM implementation details.

These details may affect empirical memory consumption.

They do not change the conceptual role of asymptotic space analysis.

For example, two Java implementations may both require:

```text
Θ(n)
```

auxiliary space while using different numbers of actual bytes.

---

# Space Complexity and Benchmarking

JMH is primarily designed for microbenchmarking execution performance.

It should not be treated as the primary tool for deriving space complexity.

Conceptually:

```text
Algorithm
        ↓
Space-Cost Function
        ↓
Theoretical Space Analysis
```

and separately:

```text
Java Implementation
        ↓
Runtime Memory Observation
```

Memory profiling, allocation analysis, or other specialised tools may later be
used when empirical memory behaviour is relevant.

However:

> **Empirical memory measurements do not establish asymptotic space complexity
> by themselves.**

---

# Space Complexity and Automated Testing

Automated tests do not derive space complexity.

Testing verifies behavioural properties such as:

* expected output;
* contract enforcement;
* mutation behaviour;
* edge-case behaviour.

Space analysis studies memory growth.

Therefore:

```text
Automated Testing
        ≠
Space-Complexity Analysis
```

Both remain important but serve different purposes.

---

# Space Complexity and Immutability

Some implementation choices may allocate additional objects intentionally.

For example, an immutable algorithmic transformation may create a new structure
rather than modifying the original input.

Conceptually:

```text
Original Structure
        +
New Structure
        ↓
Additional Memory
```

Such choices may improve:

* clarity;
* safety;
* functional reasoning.

while increasing auxiliary-space requirements.

Space analysis helps make the trade-off visible.

---

# Space Complexity and Data Structures

Data-structure choice strongly influences memory requirements.

Examples include:

* arrays;
* linked lists;
* hash tables;
* trees;
* graphs;
* heaps.

Two structures storing the same logical information may have different memory
overheads because of:

* references;
* metadata;
* unused capacity;
* auxiliary indexing structures.

Theoretical analysis usually abstracts exact byte-level differences while
preserving growth relationships.

---

# Systematic Space-Complexity Analysis

A useful analysis process is:

```text
1. Define the computational problem.
        ↓
2. Define input size.
        ↓
3. Decide whether total or auxiliary space is being analysed.
        ↓
4. Identify fixed additional variables.
        ↓
5. Identify temporary data structures.
        ↓
6. Analyse recursion depth when applicable.
        ↓
7. Determine maximum simultaneously live memory.
        ↓
8. Construct the space-cost function.
        ↓
9. Analyse its asymptotic growth.
        ↓
10. Document relevant time-space trade-offs.
```

This process makes the memory model explicit and reproducible.

---

# Space Analysis Checklist

Before stating a space bound, ask:

* What does the input-size parameter represent?
* Am I reporting total space or auxiliary space?
* Is the input itself included?
* Which additional structures are allocated?
* How large can each structure become?
* Which structures exist simultaneously?
* Is recursion involved?
* What is the maximum recursion depth?
* Does memory usage depend on the input structure?
* Are multiple input parameters required?
* Is the algorithm truly in-place under the stated definition?
* What assumptions support the final bound?

These questions help prevent ambiguous complexity claims.

---

# Common Mistakes

## Saying Input Memory Is Never Counted

Input storage is excluded from **auxiliary-space** analysis but may be included
when reporting total space.

---

## Treating Every Local Variable as Linear Space

A fixed number of scalar variables normally contributes constant auxiliary
space.

---

## Ignoring the Recursion Stack

Recursive calls consume memory even when no explicit collection is allocated.

---

## Counting Total Recursive Calls as Space

Space depends on maximum simultaneous active calls, not necessarily the total
number of calls executed.

---

## Assuming Iterative Algorithms Use Constant Space

An iterative algorithm may still allocate structures whose size grows with the
input.

---

## Assuming Recursive Algorithms Always Use Linear Space

Recursion depth depends on how the problem size changes.

Repeated halving may require only logarithmic stack depth.

---

## Treating Every In-Place Algorithm as Literally Zero Extra Space

In-place algorithms still require some working memory.

The relevant statement is usually about bounded or sublinear auxiliary memory.

---

## Counting Cumulative Allocations Instead of Peak Memory

Repeated allocation and release do not necessarily imply that all allocated
memory exists simultaneously.

---

## Ignoring Multiple Input Parameters

Matrices, graphs, and algorithms processing multiple collections may require
more than one size parameter.

---

## Confusing Measured Memory with Space Complexity

Actual byte usage depends on the language, runtime, representation, and
environment.

---

# Best Practices

When analysing space complexity:

* define the input-size parameters explicitly;
* specify whether total or auxiliary space is being reported;
* identify additional memory created by the algorithm;
* analyse maximum simultaneously live storage;
* account for recursion depth;
* distinguish total calls from active stack frames;
* preserve multiple parameters when necessary;
* state in-place claims precisely;
* analyse time-space trade-offs explicitly;
* derive the space-cost function before applying asymptotic notation;
* distinguish theoretical memory growth from empirical memory measurements;
* document assumptions and implementation-dependent considerations.

The objective is not merely to write:

```text
O(1)
```

or:

```text
O(n)
```

The objective is to explain **which memory is being counted and why it grows in
that way**.

---

# Key Takeaways

After completing this document, the learner should understand that:

* space complexity studies memory growth relative to problem size;
* total space and auxiliary space are related but distinct concepts;
* input storage may be included or excluded depending on the reported metric;
* fixed additional variables generally contribute constant auxiliary space;
* temporary structures may dominate memory requirements;
* recursion consumes stack memory according to maximum active depth;
* total recursive calls and recursion depth are different quantities;
* iterative algorithms do not automatically use constant space;
* recursive algorithms do not automatically use linear space;
* in-place algorithms minimise additional memory but do not necessarily use
  literally zero extra memory;
* space complexity normally considers peak simultaneously required memory;
* time and space represent independent computational dimensions;
* time-space trade-offs are common in algorithm design;
* asymptotic notation can describe both time and memory functions;
* empirical memory measurements do not prove theoretical space complexity.

Space complexity therefore provides the second principal resource dimension of
algorithm analysis.

Together with **time complexity**, it allows the learner to reason about the
computational cost of algorithms in terms of both **work performed** and
**memory required**.

---

# Next Document

```text
08-amortized-analysis.md
```

The next document introduces **amortized analysis**, a technique used to study
sequences of operations whose individual costs may vary significantly.

It develops the relationship:

```text
Occasionally Expensive Operation
        +
Many Inexpensive Operations
        ↓
Total Cost of a Sequence
        ↓
Amortized Cost per Operation
```

The next central question becomes:

> **How can a sequence of operations remain efficient even when some individual
> operations are expensive?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The analysis of algorithmic space requirements, auxiliary storage, recursive
execution, and time-space trade-offs throughout *Introduction to Algorithms*
provides the principal academic foundation for this document.

Complementary academic and technical references are documented in:

```text
docs/00-project/10-references.md
```

Additional Java-specific references may be introduced later when empirical
memory behaviour, runtime representation, or profiling requires implementation-
level analysis.

```
```
