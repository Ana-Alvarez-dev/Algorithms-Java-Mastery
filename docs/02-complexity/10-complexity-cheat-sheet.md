# Complexity Cheat Sheet

## Algorithms Java Mastery

This document provides a concise reference for the principal concepts,
notations, growth patterns, analytical distinctions, and engineering questions
introduced throughout the **Complexity** module.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic study of algorithm analysis throughout this repository.

Unlike the previous documents, this file does not introduce new theory.

Its purpose is to support review by condensing the analytical framework into a
single reference that can be consulted when:

* analysing algorithms;
* reviewing later modules;
* preparing for technical interviews;
* documenting implementation decisions;
* comparing algorithmic strategies.

The central question is:

> **Which complexity concepts should be immediately available when analysing a
> new algorithm?**

---

# Purpose

The purpose of this document is to consolidate the **Complexity** module into a
practical review reference.

The intended relationship is:

```text
Detailed Theory
        ↓
Understanding
        ↓
Complexity Cheat Sheet
        ↓
Fast Recall
        ↓
Applied Analysis
```

This document should therefore:

> **Complement the theoretical documents, not replace them.**

Every complexity result should still be derived and justified when analysing a
concrete algorithm.

---

# Complexity Module at a Glance

The module develops the following progression:

```text
Computational Complexity
        ↓
Algorithm Growth
        ↓
Asymptotic Analysis
        ↓
Asymptotic Notations
        ↓
Best / Average / Worst Case Analysis
        ↓
Time Complexity
        ↓
Space Complexity
        ↓
Amortized Analysis
        ↓
Complexity Analysis Examples
        ↓
Complexity Cheat Sheet
```

The progression moves from:

```text
What does computational cost mean?
```

toward:

```text
How can I derive and justify the cost
of a concrete algorithm?
```

---

# Computational Complexity

Computational complexity studies how computational resource requirements change
as problem size increases.

The principal dimensions studied in this repository are:

```text
Computational Complexity
        │
        ├── Time
        │       ↓
        │   Computational Work
        │
        └── Space
                ↓
            Memory Requirement
```

The objective is to model **growth**, not to report exact milliseconds or
megabytes.

---

# Core Analysis Workflow

A recommended complexity-analysis sequence is:

```text
Computational Problem
        ↓
Define Input Size
        ↓
Identify Preconditions
        ↓
Describe Algorithmic Strategy
        ↓
Identify Significant Operations
        ↓
Derive Cost Function
        ↓
Analyse Relevant Cases
        ↓
Analyse Growth
        ↓
Apply Asymptotic Notation
        ↓
Analyse Auxiliary Space
        ↓
Apply Amortized Analysis When Relevant
        ↓
Engineering Interpretation
```

Java implementation and empirical evaluation follow later.

---

# Input Size

Every complexity statement requires a defined problem-size model.

Common examples include:

```text
Array

n = number of elements
```

```text
String

n = number of characters
```

```text
Tree

n = number of nodes
```

```text
Graph

V = number of vertices
E = number of edges
```

```text
Two Independent Collections

n = size of first collection
m = size of second collection
```

Do not automatically reduce every problem to one variable.

---

# Significant Operations

A significant operation is an operation selected to represent relevant
algorithmic work.

Examples include:

| Algorithmic Context | Possible Significant Operation  |
| ------------------- | ------------------------------- |
| Searching           | Comparison with target          |
| Sorting             | Element comparison              |
| Sorting             | Element movement or swap        |
| Graph traversal     | Vertex processing               |
| Graph traversal     | Edge processing                 |
| Hashing             | Lookup or insertion operation   |
| Recursive algorithm | Recursive subproblem evaluation |

The correct operation depends on the analytical objective.

---

# Cost Functions

A cost function may represent computational work:

```text
T(n)
```

or memory:

```text
S(n)
```

Examples include:

```text
T(n) = 4n + 3
```

```text
T(n) = n² + 2n
```

```text
T(n) = n log n
```

```text
S(n) = n
```

The general progression is:

```text
Algorithm Structure
        ↓
Operation Count
        ↓
Cost Function
        ↓
Growth
        ↓
Asymptotic Bound
```

---

# Common Growth Functions

For sufficiently large input sizes, commonly studied functions may be ordered
by increasing growth:

```text
1
        ↓
log n
        ↓
n
        ↓
n log n
        ↓
n²
        ↓
n³
        ↓
2ⁿ
        ↓
n!
```

This ordering describes asymptotic growth.

It does **not** guarantee exact runtime ordering for every practical input size.

---

# Growth Reference

| Growth Family | Representative Function | Typical Structural Pattern          |
| ------------- | ----------------------- | ----------------------------------- |
| Constant      | `1`                     | Fixed amount of work                |
| Logarithmic   | `log n`                 | Repeated constant-factor reduction  |
| Linear        | `n`                     | Process each element                |
| Linearithmic  | `n log n`               | Logarithmic levels with linear work |
| Quadratic     | `n²`                    | Pairwise / repeated linear work     |
| Cubic         | `n³`                    | Triple-dimensional processing       |
| Exponential   | `2ⁿ`                    | Explore rapidly multiplying choices |
| Factorial     | `n!`                    | Explore permutations                |

These patterns provide intuition.

They are not proofs.

---

# Dominant Terms

For asymptotic growth, the fastest-growing term eventually dominates.

Examples:

```text
5n + 20
        ↓
dominant growth: n
```

```text
3n² + 7n + 4
        ↓
dominant growth: n²
```

```text
n log n + 6n
        ↓
dominant growth: n log n
```

Important:

```text
3n² + 7n + 4
```

is not equal to:

```text
n²
```

They simply share the same tight asymptotic growth.

---

# Constants

Consider:

```text
f(n) = 5n
```

and:

```text
g(n) = 1000n
```

Both grow linearly.

Therefore they may belong to the same asymptotic growth class.

However:

```text
Same Asymptotic Growth
        ≠
Same Exact Cost
```

Constant factors may still matter in practical execution.

---

# Asymptotic Notations

| Notation  | Meaning               | Core Interpretation                                      |
| --------- | --------------------- | -------------------------------------------------------- |
| `O(g(n))` | Upper bound           | Eventually no greater than a constant multiple of `g(n)` |
| `Ω(g(n))` | Lower bound           | Eventually no smaller than a constant multiple of `g(n)` |
| `Θ(g(n))` | Tight bound           | Bounded above and below by constant multiples of `g(n)`  |
| `o(g(n))` | Strict upper relation | Grows strictly slower than `g(n)`                        |
| `ω(g(n))` | Strict lower relation | Grows strictly faster than `g(n)`                        |

---

# Big O

Formal idea:

```text
f(n) ∈ O(g(n))
```

when positive constants `c` and `n₀` exist such that:

```text
0 ≤ f(n) ≤ c · g(n)
```

for all:

```text
n ≥ n₀
```

Big O is an **upper asymptotic bound**.

It is not automatically:

* worst case;
* exact growth;
* exact runtime.

---

# Big Omega

Formal idea:

```text
f(n) ∈ Ω(g(n))
```

when positive constants `c` and `n₀` exist such that:

```text
0 ≤ c · g(n) ≤ f(n)
```

for all:

```text
n ≥ n₀
```

Big Omega is a **lower asymptotic bound**.

It is not automatically best case.

---

# Big Theta

Formal idea:

```text
f(n) ∈ Θ(g(n))
```

when positive constants `c₁`, `c₂`, and `n₀` exist such that:

```text
0 ≤ c₁g(n) ≤ f(n) ≤ c₂g(n)
```

for all:

```text
n ≥ n₀
```

Big Theta expresses a **tight asymptotic bound**.

When a tight bound is known, it is generally more informative than a loose Big
O statement.

---

# little o and little omega

```text
f(n) ∈ o(g(n))
```

means `f(n)` grows strictly slower than `g(n)`.

When the limit formulation applies:

```text
lim f(n) / g(n) = 0
n→∞
```

---

```text
f(n) ∈ ω(g(n))
```

means `f(n)` grows strictly faster than `g(n)`.

When applicable:

```text
lim f(n) / g(n) = ∞
n→∞
```

---

# Case Analysis

Best, average, and worst case describe **different cost functions**.

They are not asymptotic notations.

```text
Inputs of Size n
        │
        ├── Minimum Cost
        │       ↓
        │   T_best(n)
        │
        ├── Expected Cost
        │       ↓
        │   T_average(n)
        │
        └── Maximum Cost
                ↓
            T_worst(n)
```

---

# Best Case

The best-case function describes the minimum cost among valid inputs of size
`n`.

```text
T_best(n)
```

Example:

```text
Linear Search

Target at first position
        ↓
1 comparison
        ↓
T_best(n) ∈ Θ(1)
```

---

# Worst Case

The worst-case function describes the maximum cost among valid inputs of size
`n`.

Example:

```text
Linear Search

Target last or absent
        ↓
n comparisons
        ↓
T_worst(n) ∈ Θ(n)
```

---

# Average Case

Average-case analysis requires a probability model.

Conceptually:

```text
Possible Inputs
        ↓
Probability Distribution
        ↓
Cost of Each Input
        ↓
Expected Cost
```

Average case is not:

```text
(best + worst) / 2
```

and random benchmark data should not automatically be called the average case.

---

# Essential Case / Notation Distinction

Incorrect:

```text
Best Case  = Ω
Average    = Θ
Worst Case = O
```

Correct:

```text
Case Analysis
        ↓
Select Cost Function
        ↓
Asymptotic Analysis
        ↓
Apply O / Ω / Θ
```

For example:

```text
T_best(n)  ∈ Θ(1)
T_worst(n) ∈ Θ(n)
```

---

# Time Complexity

Time complexity models computational work.

The analysis sequence is:

```text
Input Size
        ↓
Significant Operation
        ↓
Operation Count
        ↓
T(n)
        ↓
Growth Analysis
        ↓
Asymptotic Time Bound
```

It does not measure exact wall-clock duration.

---

# Common Loop Patterns

## Fixed Work

```text
Fixed number of operations
        ↓
Θ(1)
```

---

## Linear Traversal

```text
n iterations
×
constant work
        ↓
Θ(n)
```

---

## Geometric Reduction

```text
n
n / 2
n / 4
n / 8
...
        ↓
Θ(log n)
```

---

## Full Nested Iteration

```text
n outer iterations
×
n inner iterations
        ↓
Θ(n²)
```

when the inner loop truly performs `n` iterations for every outer iteration.

---

## Dependent Nested Iteration

```text
1 + 2 + ... + n
        ↓
n(n + 1) / 2
        ↓
Θ(n²)
```

The result must be derived from actual bounds.

---

# Important Loop Rule

Do not use:

```text
1 loop  = O(n)
2 loops = O(n²)
3 loops = O(n³)
```

as a mechanical rule.

Analyse the iteration bounds.

Example:

```text
i = 1
i *= 2
```

produces logarithmic iteration count.

---

# Sequential vs Nested Work

Sequential components:

```text
Θ(n)
+
Θ(n²)
        ↓
Θ(n²)
```

Nested components may multiply **when their bounds justify multiplication**.

---

# Recursion

Recursion does not determine complexity by itself.

Cost may be expressed using recurrence relations such as:

```text
T(n) = T(n - 1) + c
```

```text
T(n) = T(n / 2) + c
```

```text
T(n) = 2T(n / 2) + n
```

The recurrence structure determines the growth.

---

# Space Complexity

Space complexity models memory growth.

Two useful concepts are:

```text
Total Space
        =
Input Space
        +
Auxiliary Space
```

Auxiliary space refers to additional memory required beyond the original input
storage.

---

# Auxiliary Space Reference

| Memory Pattern                   | Typical Auxiliary Growth |
| -------------------------------- | ------------------------ |
| Fixed number of scalar variables | `Θ(1)`                   |
| Temporary array of size `n`      | `Θ(n)`                   |
| Matrix of size `n × n`           | `Θ(n²)`                  |
| Recursion depth `log n`          | `Θ(log n)`               |
| Recursion depth `n`              | `Θ(n)`                   |

The actual result depends on the implementation.

---

# Peak Space

Space complexity generally focuses on maximum simultaneously required memory.

Therefore:

```text
Cumulative Allocation
        ≠
Peak Space Requirement
```

Repeated allocations that are released do not necessarily accumulate.

---

# Recursion Stack

For recursive algorithms:

```text
Auxiliary Stack Space
        ≈
Maximum Active Recursion Depth
```

Important:

```text
Total Recursive Calls
        ≠
Maximum Active Calls
```

Time may depend on total recursive work.

Stack space depends on maximum active depth.

---

# In-Place Algorithms

An in-place algorithm generally uses only a small amount of additional storage
beyond the original input.

Typical tight auxiliary bound:

```text
Θ(1)
```

for many in-place array algorithms.

However:

> **In-place does not mean zero memory.**

State the actual auxiliary-space bound explicitly.

---

# Time-Space Trade-Off

A common relationship is:

```text
Additional Memory
        ↓
Store Information
        ↓
Avoid Repeated Computation
        ↓
Lower Time Cost
```

or:

```text
Less Additional Memory
        ↓
Recompute Information
        ↓
Higher Time Cost
```

Neither alternative is automatically superior.

---

# Amortized Analysis

Amortized analysis studies the total cost of a sequence of operations.

It does not require probability.

Conceptually:

```text
m Operations
        ↓
Total Cost T(m)
        ↓
Distribute Across m Operations
        ↓
Amortized Cost
```

---

# Amortized vs Average Case

| Property          | Average Case         | Amortized Analysis            |
| ----------------- | -------------------- | ----------------------------- |
| Uses probability? | Yes                  | No                            |
| Main object       | Possible inputs      | Operation sequence            |
| Result            | Expected cost        | Sequence-level cost guarantee |
| Example           | Expected search cost | Dynamic-array append          |

---

# Classical Amortized Example

For a geometrically growing dynamic array:

```text
Most Appends
        ↓
Θ(1) actual work
```

Occasionally:

```text
Resize
        ↓
Θ(n) actual work
```

Across `m` appends:

```text
Total Cost
        ↓
Θ(m)
```

Therefore:

```text
Amortized Append Cost
        ↓
Θ(1)
```

This does not mean every append is constant actual time.

---

# Amortized Analysis Methods

Three classical methods are:

```text
Aggregate Method
        ↓
Bound total sequence cost
```

```text
Accounting Method
        ↓
Assign charges and conceptual credits
```

```text
Potential Method
        ↓
Track stored analytical potential
```

---

# Common Algorithm Reference

These values assume the standard models described in their dedicated modules.

| Algorithm                | Relevant Time Result           | Auxiliary Space           |
| ------------------------ | ------------------------------ | ------------------------- |
| Array indexed access     | `Θ(1)`                         | `Θ(1)`                    |
| Linear Search            | Best `Θ(1)`, Worst `Θ(n)`      | `Θ(1)`                    |
| Binary Search, iterative | Best `Θ(1)`, Worst `Θ(log n)`  | `Θ(1)`                    |
| Binary Search, recursive | Worst `Θ(log n)`               | `Θ(log n)`                |
| Selection Sort           | `Θ(n²)`                        | `Θ(1)`                    |
| Bubble Sort, optimised   | Best `Θ(n)`, Avg/Worst `Θ(n²)` | `Θ(1)`                    |
| Insertion Sort           | Best `Θ(n)`, Avg/Worst `Θ(n²)` | `Θ(1)`                    |
| Merge Sort               | `Θ(n log n)`                   | `Θ(n)`                    |
| Heap Sort                | `Θ(n log n)`                   | commonly `Θ(1)` auxiliary |
| Dynamic-array append     | Amortized `Θ(1)`               | structure storage `Θ(n)`  |

These values are review aids.

The dedicated algorithm modules should still derive and justify them.

---

# Quick Search Comparison

| Property                  | Linear Search | Binary Search |
| ------------------------- | ------------- | ------------- |
| Ordering required         | No            | Yes           |
| Best-case time            | `Θ(1)`        | `Θ(1)`        |
| Worst-case time           | `Θ(n)`        | `Θ(log n)`    |
| Iterative auxiliary space | `Θ(1)`        | `Θ(1)`        |

Binary search has better search growth but requires ordered data.

Therefore:

```text
Better Asymptotic Search
        ≠
Always Better Engineering Choice
```

---

# Quick Sorting Comparison

| Algorithm      |    Best Time | Average Time |   Worst Time | Auxiliary Space |
| -------------- | -----------: | -----------: | -----------: | --------------: |
| Selection Sort |      `Θ(n²)` |      `Θ(n²)` |      `Θ(n²)` |          `Θ(1)` |
| Bubble Sort*   |       `Θ(n)` |      `Θ(n²)` |      `Θ(n²)` |          `Θ(1)` |
| Insertion Sort |       `Θ(n)` |      `Θ(n²)` |      `Θ(n²)` |          `Θ(1)` |
| Merge Sort     | `Θ(n log n)` | `Θ(n log n)` | `Θ(n log n)` |          `Θ(n)` |
| Heap Sort      | `Θ(n log n)` | `Θ(n log n)` | `Θ(n log n)` | commonly `Θ(1)` |

`*` The linear best case assumes early-termination detection.

These are standard analytical models; implementation details may affect some
specific resource results.

---

# Multi-Parameter Complexity

Do not collapse independent dimensions without justification.

Examples:

```text
Graph Traversal

Θ(V + E)
```

```text
Process Every Pair Across Two Collections

Θ(nm)
```

```text
Matrix Storage

Θ(rc)
```

Only substitute:

```text
m = n
```

when the problem explicitly establishes that relationship.

---

# Java Collections Framework

Complexity claims about Java collections depend on their specification and
implementation model.

They should therefore be checked against official Java documentation when used
as concrete technical claims.

For repository study, the important conceptual associations include:

```text
Array-Based Dynamic Structure
        ↓
Direct Indexed Access
        +
Occasional Resizing
```

```text
Hash-Based Structure
        ↓
Hashing Assumptions
        +
Collision Behaviour
```

```text
Tree-Based Ordered Structure
        ↓
Tree Height
        ↓
Operation Cost
```

Do not reduce library behaviour to memorised complexity tables without
understanding the underlying structure and assumptions.

---

# Complexity and Java

Algorithmic complexity belongs primarily to the computational strategy.

Java introduces implementation-level effects such as:

* object allocation;
* boxing;
* arrays and references;
* library implementation;
* JVM optimisation;
* garbage collection.

Therefore:

```text
Algorithmic Complexity
        ↓
Theoretical Growth
```

is distinct from:

```text
Java Runtime Behaviour
        ↓
Concrete Performance
```

---

# Complexity and Testing

Automated testing answers:

> Does the implementation behave correctly for selected inputs?

Complexity analysis answers:

> How do resource requirements grow?

Therefore:

```text
Testing
        ≠
Complexity Analysis
```

JUnit Jupiter and AssertJ provide behavioural evidence.

They do not establish asymptotic bounds.

---

# Complexity and Benchmarking

Benchmarking answers:

> How did this implementation perform under these experimental conditions?

Complexity analysis answers:

> How does the theoretical resource function grow?

Conceptually:

```text
Algorithm
        ↓
Cost Function
        ↓
Asymptotic Complexity
```

and separately:

```text
Java Implementation
        ↓
JMH
        ↓
Measured Behaviour
```

Benchmarking is **conditional**, not mandatory for every algorithm.

---

# Benchmarking Reminder

Use benchmarking when it adds meaningful evidence, such as:

* comparing implementations;
* studying crossover points;
* observing JVM effects;
* examining input-size behaviour.

Do not use benchmark measurements to prove:

```text
Θ(...)
```

or:

```text
O(...)
```

A finite experimental sample cannot establish a formal asymptotic bound.

---

# Complexity Analysis Checklist

Before finalising an algorithm analysis, verify:

* What computational problem is being solved?
* What does each input-size parameter represent?
* Which preconditions apply?
* Which resource is being analysed?
* Which operations represent significant work?
* How many times can those operations execute?
* Does cost vary among inputs of the same size?
* Which case is being analysed?
* Is a probability model required?
* What cost function can be derived?
* Which term dominates long-term growth?
* Which asymptotic bound is justified?
* Is the bound upper, lower, or tight?
* What auxiliary memory is required?
* What is the maximum recursion depth?
* Does amortized analysis apply?
* Are multiple parameters required?
* Which trade-offs affect the engineering interpretation?

If these questions cannot be answered, the complexity statement may be
insufficiently justified.

---

# Fast Complexity Questions

When solving a technical exercise, ask:

```text
What is n?
```

then:

```text
What operation am I counting?
```

then:

```text
How many times can it happen?
```

then:

```text
What function describes that count?
```

then:

```text
How does the function grow?
```

then:

```text
Which bound can I justify?
```

This reasoning process is more important than memorising tables.

---

# Common Mistakes

Avoid the following errors.

## Big O = Worst Case

Incorrect.

Big O is an upper-bound notation.

---

## Big Omega = Best Case

Incorrect.

Big Omega is a lower-bound notation.

---

## Big Theta = Exact Runtime

Incorrect.

Theta describes tight asymptotic growth.

---

## `n` Is Always the Number of Elements

Incorrect.

Input size depends on the problem.

---

## Two Loops = `n²`

Incorrect.

Analyse the actual iteration bounds.

---

## Recursion = Exponential

Incorrect.

Derive the recurrence.

---

## Input Memory Is Never Counted

Incorrect.

It is normally excluded from **auxiliary-space** analysis but may be included
in total-space analysis.

---

## Average Case = Halfway Between Best and Worst

Incorrect.

Average-case analysis requires a probability model.

---

## Amortized = Average Case

Incorrect.

Amortized analysis uses sequence costs without requiring probability.

---

## Benchmark Average = Amortized Complexity

Incorrect.

One is empirical; the other is mathematical.

---

## Slower Growth = Always Better Algorithm

Incorrect.

Engineering decisions also depend on constraints, preconditions, memory,
implementation complexity, and workload.

---

# Engineering Interpretation Guide

Use complexity to support decisions, not to replace them.

Consider:

| Question                                       | Why It Matters                                 |
| ---------------------------------------------- | ---------------------------------------------- |
| Is the input already ordered?                  | May affect search strategy                     |
| How large can the input become?                | Determines whether growth differences matter   |
| Is memory limited?                             | Influences time-space trade-offs               |
| Are individual latency spikes acceptable?      | Important for amortized operations             |
| Are many repeated queries expected?            | May justify preprocessing                      |
| Is exact optimality required?                  | May affect feasibility of expensive algorithms |
| Are multiple dimensions growing independently? | Requires multi-parameter analysis              |

---

# Scalable Does Not Mean Universal

Avoid absolute labels such as:

```text
O(log n) = always excellent
```

or:

```text
O(n²) = always unusable
```

Practical suitability depends on:

* actual input size;
* constants;
* frequency of execution;
* resource constraints;
* preconditions;
* implementation context.

Complexity describes growth.

Engineering decides whether that growth is acceptable.

---

# Documentation Template

Future algorithm documents may use:

```text
## Computational Problem

## Input Size

## Preconditions

## Algorithmic Strategy

## Significant Operations

## Time Complexity

### Best Case
### Average Case
### Worst Case

## Auxiliary Space

## Amortized Analysis
(when relevant)

## Complexity Summary

## Engineering Interpretation
```

Only include sections that add meaningful analytical value.

---

# Final Module Principles

The principal complexity principles established throughout this module are:

* define the problem before analysing cost;
* define input size explicitly;
* identify the resource being analysed;
* derive cost from algorithm structure;
* distinguish exact counts from asymptotic growth;
* distinguish case analysis from asymptotic notation;
* use tight bounds when justified;
* preserve multiple input parameters when needed;
* analyse auxiliary space explicitly;
* distinguish recursion depth from total recursive calls;
* use amortized analysis for suitable operation sequences;
* separate theoretical analysis from empirical measurement;
* interpret complexity within the complete engineering context.

---

# Module Completion

With this document, the **Complexity** module establishes the complete analytical
foundation required by later algorithm and data-structure modules.

The module structure is:

```text
02-complexity/
├── README.md
├── 01-computational-complexity.md
├── 02-algorithm-growth.md
├── 03-asymptotic-analysis.md
├── 04-asymptotic-notations.md
├── 05-best-average-worst-case.md
├── 06-time-complexity.md
├── 07-space-complexity.md
├── 08-amortized-analysis.md
├── 09-complexity-examples.md
└── 10-complexity-cheat-sheet.md
```

The conceptual progression is now:

```text
Understand Computational Cost
        ↓
Model Resource Usage
        ↓
Understand Growth
        ↓
Analyse Asymptotically
        ↓
Express Bounds Precisely
        ↓
Distinguish Input-Dependent Cases
        ↓
Analyse Time
        ↓
Analyse Space
        ↓
Analyse Operation Sequences
        ↓
Apply the Complete Methodology
```

---

# Expected Outcome

After completing the **Complexity** module, the learner should no longer approach
an algorithm by asking only:

```text
What is its Big O?
```

Instead, the analytical process should become:

```text
What is the problem?
        ↓
What defines input size?
        ↓
What computational resource am I analysing?
        ↓
Which operations represent the work?
        ↓
What cost function follows from the algorithm?
        ↓
Does cost vary by input?
        ↓
How does the function grow?
        ↓
Which asymptotic relationship can be justified?
        ↓
What auxiliary space is required?
        ↓
Does amortized analysis apply?
        ↓
What engineering conclusions follow?
```

That transition represents the principal educational outcome of the module.

---

# Next Module

```text
docs/03-arrays/
```

The next module applies the theoretical foundations established in
**Foundations** and **Complexity** to the first concrete data structure studied
systematically in the repository:

```text
Arrays
```

The progression becomes:

```text
Foundations
        ↓
How to reason about computational problems

Complexity
        ↓
How to analyse computational cost

Arrays
        ↓
Apply reasoning and analysis to a concrete data structure
```

---

# Academic Foundation

The principal academic reference for this module is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The concepts summarised in this cheat sheet—including algorithmic cost,
asymptotic growth, asymptotic notation, case analysis, time complexity, space
complexity, and amortized analysis—are grounded primarily in the analytical
framework developed throughout this reference.

Complementary academic, mathematical, Java, and engineering references are
documented in:

```text
docs/00-project/10-references.md
```

This cheat sheet should be used as a review resource while the detailed
documents remain the authoritative study material for the **Complexity** module.
cation to New Algorithms