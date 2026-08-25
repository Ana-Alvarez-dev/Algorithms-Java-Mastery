# Algorithm Growth

## Algorithms Java Mastery

This document introduces **algorithm growth** as the study of how a
computational cost function changes as the size of the input increases.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic analysis of algorithms throughout this repository.

After introducing computational complexity, the next step is to understand how
different resource functions grow.

The central question addressed throughout this document is:

> **How does the computational cost of an algorithm change as the problem size
> increases?**

Algorithm growth develops the intuition required before formal asymptotic
analysis.

---

# Purpose

The purpose of this document is to develop an intuitive and mathematical
understanding of common growth behaviours.

Rather than focusing on execution time measured on a specific machine, growth
analysis studies the relationship between:

```text
Input Size
        ↓
Computational Cost
        ↓
Growth Function
```

The progression is:

```text
Algorithm
        ↓
Cost Function
        ↓
Growth Behaviour
        ↓
Asymptotic Analysis
        ↓
Engineering Interpretation
```

The objective is not to memorise complexity labels.

The objective is to understand **why different functions respond differently
when input size increases**.

---

# Learning Objectives

After studying this document, the learner should be able to:

* define algorithm growth;
* relate computational cost to input size;
* interpret basic cost functions;
* compare common growth families;
* explain why growth differences become more significant for large inputs;
* identify dominant terms intuitively;
* distinguish growth behaviour from exact execution time;
* recognise constant, logarithmic, linear, linearithmic, polynomial,
  exponential, and factorial growth;
* explain why loop nesting alone does not prove a growth class;
* recognise that recursive algorithms may require recurrence-based analysis;
* relate growth behaviour to scalability;
* prepare growth functions for formal asymptotic analysis.

These competencies provide the conceptual foundation required for the next
documents in the **Complexity** module.

---

# What Is Algorithm Growth?

Algorithm growth describes how the computational resources required by an
algorithm change relative to problem size.

A time-cost function may be represented as:

```text
T(n)
```

where:

```text
n = input size
T(n) = computational work associated with input size n
```

Conceptually:

```text
Input Size n
        ↓
Algorithm
        ↓
Cost T(n)
        ↓
Observe Growth as n Increases
```

Growth analysis focuses on the behaviour of the function rather than one
individual measurement.

---

# Cost Functions

Different algorithms may generate different cost functions.

Examples include:

```text
T(n) = 1
```

```text
T(n) = log n
```

```text
T(n) = n
```

```text
T(n) = n log n
```

```text
T(n) = n²
```

```text
T(n) = 2ⁿ
```

```text
T(n) = n!
```

These functions do not grow at the same rate.

As input size increases, those differences become increasingly important.

---

# Why Growth Matters

Consider two correct algorithms solving the same computational problem.

```text
Algorithm A
        ↓
Cost Function A

Algorithm B
        ↓
Cost Function B
```

For a small input, both algorithms may appear similar.

For larger inputs, their resource requirements may diverge substantially.

For example:

```text
n = 10

n   = 10
n²  = 100
```

while:

```text
n = 1,000

n   = 1,000
n²  = 1,000,000
```

The purpose of growth analysis is to understand this long-term behaviour.

---

# Growth and Scalability

Growth rate is one important component of algorithmic scalability.

Conceptually:

```text
Input Size Increases
        ↓
Cost Function Grows
        ↓
Resource Requirements Increase
        ↓
Scalability Consequences
```

An algorithm whose resource function grows slowly may remain practical for
larger inputs than one whose resource requirements grow much faster.

However, practical scalability also depends on factors such as:

* constant factors;
* available memory;
* input distribution;
* implementation details;
* hardware;
* system constraints.

Growth analysis supports scalability reasoning without replacing complete
engineering evaluation.

---

# Common Growth Families

Several growth families occur repeatedly throughout algorithm analysis.

They provide a useful vocabulary for comparing resource functions.

---

# Constant Growth

A constant cost function does not grow with input size.

A general model is:

```text
T(n) = c
```

where `c` is constant.

Conceptually:

```text
n = 10
T(n) = c

n = 1,000
T(n) = c

n = 1,000,000
T(n) = c
```

Typical examples may include:

* reading a stored variable;
* accessing an array element by a known valid index;
* returning a previously computed value.

The important property is:

> **The modelled amount of computational work does not depend on `n`.**

---

# Logarithmic Growth

Logarithmic growth occurs when the number of required steps increases very
slowly relative to input size.

A common model is:

```text
T(n) = log n
```

This behaviour often appears when each step reduces the remaining problem size
by a constant factor.

For example:

```text
1024
        ↓
512
        ↓
256
        ↓
128
        ↓
64
        ↓
...
```

Repeated halving produces logarithmic growth.

Typical contexts include:

* binary search;
* operations on balanced search trees;
* repeated problem-size reduction.

The base of the logarithm usually changes only a constant factor and therefore
does not affect the asymptotic growth class.

---

# Linear Growth

Linear growth occurs when computational work increases proportionally with
input size.

A typical model is:

```text
T(n) = n
```

Conceptually:

```text
n = 10
T(n) ≈ 10

n = 100
T(n) ≈ 100

n = 1,000
T(n) ≈ 1,000
```

Linear growth commonly appears when each input element is processed a constant
number of times.

Examples include:

* full array traversal;
* counting values satisfying a condition;
* worst-case linear search.

---

# Linearithmic Growth

Linearithmic growth combines linear and logarithmic factors.

A common model is:

```text
T(n) = n log n
```

This pattern often appears when:

```text
Problem
        ↓
Repeated Decomposition
        ↓
Several Levels
        ↓
Linear Work per Level
```

Typical examples include:

* merge sort;
* heap sort;
* several efficient comparison-based sorting algorithms.

Linearithmic growth is asymptotically larger than linear growth but much smaller
than quadratic growth for sufficiently large inputs.

---

# Quadratic Growth

Quadratic growth is commonly represented as:

```text
T(n) = n²
```

It may occur when the algorithm performs work across pairs of elements or when
one amount of linear work is repeated approximately `n` times.

Conceptually:

```text
n elements
        ↓
Approximately n units of work
for each of approximately n stages
        ↓
n² growth
```

Examples may include:

* selection sort;
* some forms of bubble sort;
* pairwise comparison problems.

However:

> **Two nested loops do not automatically imply quadratic growth.**

Their bounds must be analysed.

---

# Cubic Growth

Cubic growth is commonly represented as:

```text
T(n) = n³
```

This may occur when work is performed across combinations of three dimensions
or when three independent loops each scale with `n`.

Examples may include:

* some matrix algorithms;
* processing all triples of elements;
* selected dynamic-programming formulations.

Again, three nested loops alone do not prove cubic complexity.

The actual iteration bounds determine the growth function.

---

# Polynomial Growth

Polynomial growth is described by functions of the form:

```text
T(n) = aₖnᵏ + aₖ₋₁nᵏ⁻¹ + ... + a₁n + a₀
```

Examples include:

```text
n
n²
n³
n⁵
```

Linear, quadratic, and cubic functions are therefore all polynomial.

The degree of the polynomial strongly influences scalability.

For this reason, describing an algorithm only as “polynomial” may sometimes be
too broad.

---

# Exponential Growth

Exponential growth is represented by functions such as:

```text
T(n) = 2ⁿ
```

or:

```text
T(n) = cⁿ
```

for a constant `c > 1`.

For example:

```text
2⁵  = 32
2¹⁰ = 1,024
2²⁰ = 1,048,576
```

The cost increases extremely rapidly as `n` grows.

Exponential behaviour often appears when an algorithm explores a large number
of combinations.

Examples may include:

* naïve recursive Fibonacci;
* exhaustive subset search;
* brute-force combinatorial exploration.

---

# Factorial Growth

Factorial growth is represented by:

```text
T(n) = n!
```

For example:

```text
3!  = 6
4!  = 24
5!  = 120
10! = 3,628,800
```

Factorial growth increases even more rapidly than many common exponential
functions.

It often appears when an algorithm explores every permutation of `n` elements.

Examples include brute-force permutation-based optimisation strategies.

---

# Comparing Growth Rates

For sufficiently large `n`, the commonly studied functions can be ordered by
increasing growth:

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

This ordering describes asymptotic behaviour.

It should not be interpreted as a guarantee about exact execution time for
small inputs.

---

# Numerical Comparison

A numerical example illustrates how differences expand.

| `n` | `log₂ n` | `n` | `n log₂ n` | `n²` |   `2ⁿ` |
| --: | -------: | --: | ---------: | ---: | -----: |
|   1 |        0 |   1 |          0 |    1 |      2 |
|   2 |        1 |   2 |          2 |    4 |      4 |
|   4 |        2 |   4 |          8 |   16 |     16 |
|   8 |        3 |   8 |         24 |   64 |    256 |
|  16 |        4 |  16 |         64 |  256 | 65,536 |

These values represent mathematical functions.

They do not represent measured execution time.

---

# Constant Factors

Growth behaviour should be distinguished from constant factors.

Consider:

```text
T₁(n) = n
```

and:

```text
T₂(n) = 1000n
```

Both functions grow linearly.

However, for practical input sizes, their numerical values may differ
substantially.

Likewise:

```text
T₃(n) = n²
```

may initially be smaller than `1000n`.

Eventually, quadratic growth overtakes linear growth.

This distinction prepares the learner to understand why asymptotic analysis
focuses on long-term growth while practical engineering may still care about
constants.

---

# Lower-Order Terms

Consider:

```text
T(n) = n² + 10n + 100
```

For small `n`, all terms may affect the cost substantially.

As `n` increases, the quadratic term becomes increasingly dominant.

Conceptually:

```text
n² + 10n + 100
        ↓
Large n
        ↓
Dominant Behaviour
        ↓
n²
```

This observation prepares the learner for formal asymptotic simplification.

---

# Dominant Terms

The dominant term is the part of a cost function that eventually contributes
most strongly to its growth.

For example:

```text
T(n) = 5n + 20
```

has dominant behaviour associated with:

```text
n
```

while:

```text
T(n) = 3n² + 7n + 4
```

has dominant behaviour associated with:

```text
n²
```

and:

```text
T(n) = n log n + 4n
```

has dominant behaviour associated with:

```text
n log n
```

At this stage, these observations provide intuition.

Formal justification belongs to asymptotic analysis.

---

# Growth and Input Parameters

Not every growth function depends on a single parameter.

A graph algorithm may use:

```text
T(V, E)
```

where:

```text
V = number of vertices
E = number of edges
```

An algorithm processing two independent collections may use:

```text
T(n, m)
```

Therefore, the familiar sequence:

```text
1
log n
n
n log n
n²
...
```

provides useful intuition without implying that every computational problem can
be reduced meaningfully to one variable.

---

# Growth and Case Analysis

An algorithm may exhibit different cost functions for inputs of the same size.

For example, linear search may require:

```text
1 comparison
```

for a favourable input and:

```text
n comparisons
```

for an unfavourable input.

Thus:

```text
Same Input Size
        ↓
Different Input Characteristics
        ↓
Different Cost
```

This distinction leads to:

* best-case analysis;
* average-case analysis;
* worst-case analysis.

These concepts are developed formally in:

```text
05-best-average-worst-case.md
```

---

# Growth and Recursive Algorithms

Recursive algorithms may produce cost functions expressed through recurrence
relations.

Examples include:

```text
T(n) = T(n / 2) + c
```

and:

```text
T(n) = 2T(n / 2) + n
```

In these cases, growth may not be immediately visible from loop structure.

Instead, the learner must analyse how recursive subproblems contribute to the
total cost.

This becomes especially important in:

* recursion;
* divide and conquer;
* dynamic programming.

---

# Growth and Loop Structure

Loops often provide visible clues about growth, but their structure must be
analysed carefully.

For example:

```text
for each of n elements
```

suggests linear work if the body performs constant work.

However:

```text
for i = 1 to n
    for j = 1 to i
```

does not execute exactly `n × n` iterations.

Its work is approximately:

```text
1 + 2 + 3 + ... + n
```

which still grows quadratically, but the reasoning matters.

Likewise:

```text
for i = n; i > 1; i = i / 2
```

is a loop, but its growth is logarithmic.

Therefore:

> **Growth should be derived from iteration behaviour, not from the visual
> number of loops alone.**

---

# Growth and Asymptotic Analysis

Algorithm growth provides intuition.

Asymptotic analysis provides the mathematical framework required to formalise
that intuition.

Conceptually:

```text
Cost Function
        ↓
Growth Behaviour
        ↓
Dominant Behaviour
        ↓
Asymptotic Analysis
```

The next document introduces this formal transition.

---

# Growth and Asymptotic Notation

Growth families should not be confused with asymptotic notation.

For example:

```text
Linear Growth
```

describes a family of functions whose growth is proportional to `n`.

A statement such as:

```text
Θ(n)
```

makes a mathematical statement about an asymptotically tight bound.

Likewise:

```text
O(n)
```

states an asymptotic upper bound.

Therefore:

```text
Growth Behaviour
        ↓
Formal Mathematical Claim
        ↓
Asymptotic Notation
```

The formal meaning of these symbols is developed in:

```text
04-asymptotic-notations.md
```

---

# Growth Is Not Big O

A common simplification is:

```text
Algorithm Growth = Big O
```

This is incorrect.

Growth describes the behaviour of resource functions.

Big O is one mathematical notation used to express an asymptotic upper bound.

The broader relationship is:

```text
Cost Function
        ↓
Growth Analysis
        ↓
Asymptotic Analysis
        ↓
O / Ω / Θ / o / ω
```

Maintaining this distinction prevents imprecise complexity reasoning.

---

# Growth and Algorithm Selection

When several correct algorithms solve the same problem, growth analysis can
support comparison.

Conceptually:

```text
Correct Candidate Algorithms
        ↓
Compare Resource Growth
        ↓
Consider Problem Constraints
        ↓
Consider Engineering Trade-Offs
        ↓
Select Appropriate Algorithm
```

Growth rate should not be the only criterion.

The choice may also depend on:

* preconditions;
* auxiliary memory;
* input characteristics;
* implementation complexity;
* stability;
* maintainability;
* expected workload.

---

# Growth and Practical Feasibility

Functions with faster growth typically become difficult to use for large inputs
more quickly than slower-growing functions.

However, statements such as:

```text
Polynomial = practical
Exponential = impossible
```

are too absolute.

Practical feasibility depends on:

* input size;
* hardware;
* constant factors;
* implementation;
* required response time;
* whether an exact solution is necessary;
* available alternatives.

Growth analysis provides evidence for feasibility decisions rather than
absolute rules.

---

# Growth and Java Implementation

Growth belongs primarily to the algorithmic model.

Java implementation introduces concrete runtime effects.

Conceptually:

```text
Algorithmic Cost Function
        ↓
Theoretical Growth

Java Implementation
        ↓
Practical Execution Characteristics
```

Two Java implementations with the same asymptotic growth may still behave
differently because of:

* allocation;
* cache behaviour;
* boxing;
* data representation;
* library overhead;
* JVM optimisation.

These implementation effects do not change the theoretical growth model.

---

# Growth and Benchmarking

Growth analysis and benchmarking answer different questions.

Growth analysis asks:

> **How does the cost function behave as input size increases?**

Benchmarking asks:

> **How did this implementation perform under these defined conditions?**

The relationship is:

```text
Theoretical Cost Function
        ↓
Growth Analysis

Concrete Java Implementation
        ↓
JMH Benchmark
        ↓
Observed Performance
```

A benchmark may produce results consistent with theoretical expectations.

However:

> **A finite set of benchmark measurements cannot establish an asymptotic
> growth class.**

Benchmarking is therefore complementary evidence.

---

# Practical Crossover Points

Algorithms with different growth functions may behave differently for small
inputs than asymptotic intuition alone suggests.

Consider:

```text
Algorithm A
Lower Growth Rate
Higher Constant Cost
```

and:

```text
Algorithm B
Higher Growth Rate
Lower Constant Cost
```

For sufficiently small inputs, Algorithm B may execute faster.

At larger input sizes, Algorithm A may eventually become preferable.

The point where practical behaviour changes is often described as a
**crossover point**.

This is an empirical implementation concern.

It does not change the underlying mathematical growth functions.

---

# Growth and Software Engineering

Understanding algorithm growth can support engineering decisions involving:

* algorithm selection;
* data-structure selection;
* processing large datasets;
* backend workloads;
* batch processing;
* capacity planning;
* memory constraints.

However, algorithmic growth represents only one part of complete system
performance.

Real systems may also depend on:

* network latency;
* databases;
* disk access;
* distributed communication;
* concurrency;
* external services.

Algorithm growth should therefore be interpreted as one analytical layer within
broader software engineering.

---

# Relationship with Later Modules

Growth reasoning becomes a permanent part of later algorithm analysis.

```text
Algorithm Growth
        ↓
Arrays
        ↓
Searching
        ↓
Sorting
        ↓
Hash Tables
        ↓
Trees
        ↓
Graphs
        ↓
Recursion
        ↓
Divide and Conquer
        ↓
Greedy Algorithms
        ↓
Dynamic Programming
```

Each module derives its own relevant cost functions rather than merely attaching
predefined labels.

---

# Common Growth Patterns

The following patterns provide useful intuition.

| Growth       | Typical Structural Pattern                         |
| ------------ | -------------------------------------------------- |
| Constant     | Fixed amount of work                               |
| Logarithmic  | Repeated constant-factor reduction                 |
| Linear       | Process each input element                         |
| Linearithmic | Logarithmic levels with linear work per level      |
| Quadratic    | Pairwise or repeated linear processing             |
| Cubic        | Three-dimensional or triple-combination processing |
| Exponential  | Explore combinations that multiply with input size |
| Factorial    | Explore permutations                               |

These are patterns, not proofs.

The actual cost must still be derived.

---

# Systematic Growth Analysis

A useful growth-analysis process is:

```text
1. Define the input-size parameter.
        ↓
2. Identify the computational resource.
        ↓
3. Derive or estimate the cost function.
        ↓
4. Examine how the function changes as input grows.
        ↓
5. Identify dominant behaviour.
        ↓
6. Compare with familiar growth families.
        ↓
7. Preserve multiple parameters when required.
        ↓
8. Formalise the result through asymptotic analysis.
```

This process encourages reasoning rather than memorisation.

---

# Common Mistakes

## Confusing Growth with Execution Time

Growth describes mathematical resource behaviour.

Execution time is an empirical measurement.

---

## Assuming Nested Loops Always Mean Quadratic Growth

Iteration bounds must be analysed.

A loop may reduce its variable geometrically and therefore produce logarithmic
rather than linear behaviour.

---

## Assuming Three Loops Always Mean Cubic Growth

The same principle applies.

The number of syntactic loops alone does not determine the cost function.

---

## Treating Constant Growth as “Instant”

Constant growth means the modelled work does not grow with `n`.

The constant itself may still be large.

---

## Calling Every Search Logarithmic

A search is logarithmic only when its structure supports repeated reduction of
the remaining search domain.

---

## Treating `n log n` as Linear

`n log n` and `n` are distinct growth functions.

Their difference increases as `n` grows.

---

## Assuming Polynomial Growth Is Always Practical

Polynomial algorithms with high degree may still be unusable for realistic
input sizes.

---

## Assuming Exponential Algorithms Are Never Useful

They may be appropriate for small inputs or when no better exact solution is
available.

---

## Ignoring Multiple Input Parameters

Reducing every cost function to `n` can hide important problem structure.

---

## Treating Growth Labels as Proof

A classification such as “linear” or “quadratic” should follow from a derived
cost model.

---

# Best Practices

When analysing algorithm growth:

* define input size explicitly;
* identify the resource being modelled;
* derive a cost function before assigning a growth label;
* reason from algorithm structure;
* compare growth rather than isolated timings;
* preserve multiple parameters when needed;
* distinguish constants and lower-order terms from dominant growth;
* analyse loop bounds rather than counting loops visually;
* recognise recurrence-based costs in recursive algorithms;
* distinguish growth families from asymptotic notation;
* use benchmarking only as complementary empirical evidence;
* interpret growth within the complete engineering context.

The objective is to understand **why** an algorithm exhibits a particular
growth behaviour.

---

# Key Takeaways

After completing this document, the learner should understand that:

* algorithm growth describes how computational cost changes as input size
  increases;
* cost functions provide the mathematical basis for growth analysis;
* growth behaviour differs from measured execution time;
* constant factors may affect practical performance without changing the
  long-term growth family;
* lower-order terms become less significant relative to dominant terms as input
  size grows;
* common growth families include constant, logarithmic, linear, linearithmic,
  polynomial, exponential, and factorial growth;
* loop nesting alone does not establish complexity;
* recursive algorithms may require recurrence-based analysis;
* problems may require multiple input-size parameters;
* slower asymptotic growth does not automatically determine the best
  engineering choice;
* benchmarking can illustrate implementation behaviour but cannot prove
  asymptotic growth;
* growth analysis provides the intuition required for formal asymptotic
  analysis.

Algorithm growth therefore provides the conceptual bridge between
**computational cost functions** and **asymptotic analysis**.

It explains why algorithms that appear similar for small inputs may behave very
differently as the problem size increases.

---

# Next Document

```text
03-asymptotic-analysis.md
```

The next document formalises the growth intuition developed here.

It examines how mathematical cost functions can be compared while abstracting
away implementation-specific details, constant factors, and lower-order terms.

The next central question becomes:

> **How can the long-term growth of algorithmic cost functions be analysed
> mathematically?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The study of orders of growth and the comparison of algorithmic cost functions
throughout *Introduction to Algorithms* provides the principal academic
foundation for this document.

Complementary academic and mathematical references are documented in:

```text
docs/00-project/10-references.md
```

Additional references may be introduced in later documents when formal
asymptotic analysis requires greater mathematical depth.
