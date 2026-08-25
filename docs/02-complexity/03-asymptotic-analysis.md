# Asymptotic Analysis

## Algorithms Java Mastery

This document introduces **asymptotic analysis** as the mathematical study of
the long-term growth behaviour of algorithmic cost functions.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic analysis of algorithms throughout this repository.

After studying computational complexity and algorithm growth, the next step is
to formalise how cost functions are compared when the input size becomes
sufficiently large.

The central question addressed throughout this document is:

> **How can the long-term growth of algorithmic cost functions be analysed
> mathematically?**

Asymptotic analysis provides the conceptual framework required to answer this
question while abstracting away details that do not determine long-term growth.

---

# Purpose

The purpose of this document is to establish the principles used to compare
algorithmic cost functions according to their behaviour for sufficiently large
input sizes.

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
Asymptotic Bounds
```

Rather than asking how many milliseconds a program requires on one particular
machine, asymptotic analysis studies the mathematical behaviour of resource
functions.

This allows algorithms to be compared at the theoretical level before
implementation-specific measurements are considered.

---

# Learning Objectives

After studying this document, the learner should be able to:

* define asymptotic analysis;
* explain why asymptotic analysis focuses on sufficiently large inputs;
* distinguish exact cost from asymptotic growth;
* identify dominant terms in simple cost functions;
* explain why constant factors may be abstracted in asymptotic reasoning;
* explain why lower-order terms become less significant in long-term growth;
* compare cost functions qualitatively;
* understand the role of thresholds such as `n₀`;
* distinguish asymptotic analysis from benchmarking;
* recognise that time and space functions can both be analysed asymptotically;
* prepare cost functions for formal asymptotic notation.

These competencies provide the mathematical foundation required for the next
document in the **Complexity** module.

---

# From Algorithm Growth to Asymptotic Analysis

Algorithm growth develops intuition about how cost functions change.

Asymptotic analysis formalises that intuition.

The progression is:

```text
Computational Complexity
        ↓
Cost Function
        ↓
Algorithm Growth
        ↓
Asymptotic Analysis
        ↓
Asymptotic Notation
```

For example, suppose:

```text
T(n) = 3n² + 10n + 20
```

Growth analysis suggests that the quadratic term eventually dominates.

Asymptotic analysis provides the formal reasoning required to describe that
long-term behaviour mathematically.

---

# What Is Asymptotic Analysis?

**Asymptotic analysis** studies how a mathematical function behaves as its input
becomes sufficiently large.

In algorithm analysis, the function commonly represents a computational
resource.

For example:

```text
T(n)
```

may model time-related work.

```text
S(n)
```

may model auxiliary-space requirements.

The purpose is not to determine the exact value of these functions for every
possible input.

Instead, asymptotic analysis focuses on their **rate of growth**.

Conceptually:

```text
Cost Function
        ↓
Increase Input Size
        ↓
Observe Long-Term Behaviour
        ↓
Asymptotic Growth
```

---

# Cost Functions

Algorithm analysis often begins with a cost function derived from the
algorithmic structure.

Examples include:

```text
T(n) = 5
```

```text
T(n) = 3n + 7
```

```text
T(n) = n² + 4n + 1
```

```text
T(n) = n log n + 2n
```

These expressions may describe different computational behaviours.

Asymptotic analysis examines which parts of the function determine its growth
for large values of the input-size parameter.

---

# Exact Cost vs Asymptotic Behaviour

Exact cost and asymptotic growth answer different questions.

Consider:

```text
T(n) = 3n + 5
```

The exact expression contains:

* a multiplicative constant;
* a linear term;
* an additive constant.

An asymptotic analysis focuses primarily on the fact that the function grows
linearly.

Conceptually:

```text
Exact Cost Function
        ↓
3n + 5
        ↓
Long-Term Growth
        ↓
Linear
```

The asymptotic description does not claim that:

```text
3n + 5 = n
```

They are not mathematically equal.

They simply exhibit the same long-term order of growth.

---

# Behaviour for Sufficiently Large Inputs

Asymptotic analysis is concerned with what happens after the input becomes
sufficiently large.

This does not mean that `n` is literally replaced by infinity.

Instead, the analysis asks whether a relationship eventually becomes and
remains true beyond some threshold.

Conceptually:

```text
Small Inputs
        ↓
Constants and Lower-Order Terms May Matter Strongly

Sufficiently Large Inputs
        ↓
Dominant Growth Becomes Clear
```

This threshold is often represented conceptually by:

```text
n₀
```

where `n₀` marks a point beyond which an asymptotic relationship holds.

---

# Threshold Behaviour

Suppose two functions are being compared:

```text
f(n)
```

and:

```text
g(n)
```

An asymptotic statement may require that a particular mathematical relationship
hold for every:

```text
n ≥ n₀
```

for some constant `n₀`.

Conceptually:

```text
n < n₀
        ↓
Relationship May Not Hold

n ≥ n₀
        ↓
Relationship Holds Consistently
```

The existence of such a threshold is central to formal asymptotic notation.

The exact definition is introduced in:

```text
04-asymptotic-notations.md
```

---

# Why Exact Execution Time Is Not the Objective

Exact execution time depends on many factors outside the abstract algorithm.

Examples include:

* processor architecture;
* clock frequency;
* memory hierarchy;
* JVM behaviour;
* JIT compilation;
* garbage collection;
* operating-system scheduling;
* implementation details.

Therefore:

```text
Same Algorithm
        ↓
Different Environment
        ↓
Different Wall-Clock Time
```

Asymptotic analysis deliberately abstracts away these machine-dependent effects.

The objective is to compare the underlying growth of the algorithmic cost model.

---

# Hardware Independence as an Abstraction

Asymptotic analysis is often described as hardware-independent.

This should be understood carefully.

It does not mean hardware is irrelevant to real performance.

Instead:

```text
Asymptotic Analysis
        ↓
Abstracts Many Machine-Level Details
        ↓
Focuses on Growth Structure
```

while:

```text
Empirical Performance
        ↓
Includes Machine and Runtime Effects
```

The two perspectives answer different questions.

---

# Constant Factors

Consider:

```text
f(n) = 5n
```

and:

```text
g(n) = 20n
```

The two functions have different exact costs.

For every positive `n`:

```text
20n > 5n
```

However, both grow linearly.

Conceptually:

```text
5n
20n
        ↓
Different Exact Costs
        ↓
Same Long-Term Growth Family
```

Asymptotic analysis may therefore abstract away multiplicative constants when
the objective is to describe long-term growth.

This abstraction does not imply that constants are irrelevant to practical
runtime.

---

# Lower-Order Terms

Consider:

```text
T(n) = n² + 8n + 15
```

For relatively small values of `n`, all three terms may contribute noticeably.

As `n` increases, however:

```text
n²
```

grows more rapidly than:

```text
8n
```

and:

```text
15
```

Conceptually:

```text
n² + 8n + 15
        ↓
Large n
        ↓
Quadratic Term Dominates Growth
```

Asymptotic analysis therefore focuses on the long-term dominant behaviour.

---

# Dominant Terms

The **dominant term** is the term that eventually determines the overall growth
of a function.

Examples:

```text
T(n) = 6n + 100
```

dominant term:

```text
n
```

```text
T(n) = 4n² + 7n + 3
```

dominant term:

```text
n²
```

```text
T(n) = 2n log n + 30n
```

dominant term:

```text
n log n
```

The goal is not to erase the other terms from the exact function.

The goal is to identify which term determines long-term growth.

---

# Why Dominant Terms Matter

Consider:

```text
T(n) = n² + n
```

For:

```text
n = 10
```

the terms are:

```text
n² = 100
n  = 10
```

For:

```text
n = 1,000
```

the terms become:

```text
n² = 1,000,000
n  = 1,000
```

The relative influence of the lower-order term decreases as `n` increases.

This behaviour motivates asymptotic simplification.

---

# Comparing Growth Functions

Asymptotic analysis allows cost functions to be compared according to how
quickly they grow.

For sufficiently large `n`, common functions satisfy the general ordering:

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

This ordering is about long-term growth.

It does not guarantee that a slower-growing function has the smallest numerical
value for every possible input.

---

# Small Inputs and Crossover Behaviour

Consider:

```text
f(n) = 1000n
```

and:

```text
g(n) = n²
```

For some small values of `n`:

```text
n² < 1000n
```

Even though quadratic growth eventually exceeds linear growth.

At larger values:

```text
n² > 1000n
```

This illustrates an important principle:

> **Asymptotic superiority describes eventual growth, not necessarily immediate
> practical superiority.**

The exact crossover point depends on the functions being compared.

---

# Asymptotic Equivalence Is Not Exact Equality

Suppose:

```text
f(n) = 3n + 10
```

and:

```text
g(n) = n
```

These functions are not equal.

However, they belong to the same linear growth family.

Therefore:

```text
f(n) ≠ g(n)
```

while their asymptotic growth may still be described as equivalent in an
appropriate sense.

This distinction is essential when using notation such as `Θ`.

---

# Asymptotic Analysis and Scalability

Asymptotic analysis provides one theoretical perspective on scalability.

Conceptually:

```text
Input Size
        ↓
Cost Function
        ↓
Asymptotic Growth
        ↓
Scalability Interpretation
```

Functions with slower growth generally require resources more slowly as problem
size increases.

However, scalability in real software may also depend on:

* constants;
* memory capacity;
* workload characteristics;
* concurrency;
* external systems;
* implementation details.

Asymptotic analysis provides an important abstraction, not a complete system
performance model.

---

# Multiple Input Parameters

Not every computational cost can be represented meaningfully using one variable.

For example, graph algorithms may depend on:

```text
V = number of vertices
E = number of edges
```

A cost function may therefore be written as:

```text
T(V, E)
```

Similarly, an algorithm processing two independent collections may use:

```text
T(n, m)
```

Asymptotic reasoning should preserve multiple parameters when combining them
into a single variable would hide important problem structure.

---

# Multi-Parameter Growth

Consider:

```text
T(n, m) = nm
```

The growth depends independently on both parameters.

If:

```text
m = n
```

then the function becomes:

```text
T(n) = n²
```

But that simplification is only valid under the additional relationship:

```text
m = n
```

Without such an assumption, the two-parameter form should be preserved.

This is especially important in:

* graph algorithms;
* matrix algorithms;
* string comparison;
* algorithms operating on multiple collections.

---

# Recursive Cost Functions

Recursive algorithms may produce cost functions expressed as recurrences.

For example:

```text
T(n) = T(n / 2) + c
```

or:

```text
T(n) = 2T(n / 2) + n
```

The same asymptotic goal remains:

> Determine how the total resource requirement grows with input size.

However, recursive functions may require specialised analytical techniques.

These techniques are introduced later when recursion and divide-and-conquer
algorithms require them.

---

# Asymptotic Analysis and Case Analysis

An algorithm may have different cost functions for different inputs of the same
size.

For example:

```text
Best-Case Cost
T_best(n)
```

```text
Average-Case Cost
T_average(n)
```

```text
Worst-Case Cost
T_worst(n)
```

Each of these functions can be analysed asymptotically.

Therefore:

```text
Case Analysis
        ≠
Asymptotic Notation
```

Case analysis identifies **which cost function is being studied**.

Asymptotic analysis describes **how that function grows**.

This distinction is developed further in:

```text
05-best-average-worst-case.md
```

---

# Asymptotic Analysis and Time Complexity

Time complexity commonly studies a function such as:

```text
T(n)
```

representing computational work.

That function may then be analysed asymptotically.

Conceptually:

```text
Algorithm
        ↓
Time-Cost Function
        ↓
Asymptotic Analysis
        ↓
Asymptotic Time Bound
```

Time complexity is developed in greater depth in:

```text
06-time-complexity.md
```

---

# Asymptotic Analysis and Space Complexity

The same reasoning can be applied to auxiliary memory.

Suppose:

```text
S(n)
```

represents additional memory required by an algorithm.

Then:

```text
Algorithm
        ↓
Space-Cost Function
        ↓
Asymptotic Analysis
        ↓
Asymptotic Space Bound
```

Therefore, asymptotic analysis is not limited to time.

It can describe the growth of different computational resources.

Space complexity is developed in:

```text
07-space-complexity.md
```

---

# Relationship with Computational Complexity

Computational complexity provides the broader analytical context.

Asymptotic analysis provides one of the principal mathematical tools used within
that context.

The relationship is:

```text
Computational Complexity
        ↓
Resource Cost Function
        ↓
Growth Analysis
        ↓
Asymptotic Analysis
```

Parallel resource dimensions may include:

```text
Computational Resources
        │
        ├── Time
        └── Space
```

Each resource may produce its own cost function and asymptotic description.

---

# Relationship with Algorithm Growth

The previous document develops intuition about growth families.

This document formalises the transition from intuitive growth to mathematical
comparison.

Conceptually:

```text
Algorithm Growth
        ↓
Recognise Long-Term Behaviour
        ↓
Asymptotic Analysis
        ↓
Formal Bounds
```

Growth intuition answers:

> Which function appears to dominate?

Asymptotic analysis asks:

> How can that relationship be justified mathematically?

---

# Relationship with Asymptotic Notation

Asymptotic analysis provides the reasoning.

Asymptotic notation provides the language used to express the resulting
relationships.

Conceptually:

```text
Cost Functions
        ↓
Asymptotic Analysis
        ↓
Mathematical Relationship
        ↓
Asymptotic Notation
```

The next document introduces:

```text
O
Ω
Θ
o
ω
```

and explains the mathematical meaning of each notation.

---

# Relationship with Benchmarking

Asymptotic analysis and benchmarking provide different forms of evidence.

## Asymptotic Analysis

Studies:

```text
Cost Function
        ↓
Mathematical Growth
        ↓
Theoretical Evidence
```

## Benchmarking

Studies:

```text
Concrete Java Implementation
        ↓
Defined Environment
        ↓
Measured Behaviour
        ↓
Empirical Evidence
```

The repository may use **JMH** when empirical performance measurement is
appropriate.

Benchmarking may reveal:

* practical constant factors;
* JVM effects;
* implementation overhead;
* crossover points;
* observed performance trends.

However:

> **Benchmark measurements do not prove asymptotic bounds.**

---

# Benchmarking Is Conditional

Asymptotic analysis is fundamental to this module.

Benchmarking is used only when it provides meaningful educational or
engineering evidence.

Therefore:

```text
Theoretical Complexity Analysis
        ↓
Required Analytical Foundation
```

while:

```text
Benchmarking
        ↓
Optional Experimental Extension
```

Not every algorithm requires experimental measurement.

---

# Why Asymptotic Analysis Cannot Predict Exact Runtime

Suppose two implementations both have linear growth.

```text
Implementation A
        ↓
T₁(n) ≈ 5n
```

```text
Implementation B
        ↓
T₂(n) ≈ 500n
```

They may share the same asymptotic growth family while having significantly
different practical execution times.

Asymptotic analysis intentionally ignores such exact constant differences when
describing long-term growth.

Therefore:

```text
Same Asymptotic Class
        ≠
Same Runtime
```

---

# Why Faster Growth Can Still Win for Small Inputs

Suppose:

```text
Algorithm A
T_A(n) = 1000n
```

and:

```text
Algorithm B
T_B(n) = n²
```

For sufficiently small inputs:

```text
n² < 1000n
```

Algorithm B may therefore perform less computational work.

Eventually:

```text
n² > 1000n
```

and Algorithm A becomes preferable from a growth perspective.

This illustrates why asymptotic analysis should not be confused with exact
performance prediction.

---

# Mathematical Perspective

Asymptotic analysis studies relationships between functions.

The important objects are therefore expressions such as:

```text
f(n)
```

and:

```text
g(n)
```

The analysis asks whether one function can eventually be bounded above, below,
or both by a constant multiple of another function.

Conceptually:

```text
f(n)
        ↓
Compare with
        ↓
g(n)
        ↓
For sufficiently large n
```

This framework leads directly to formal asymptotic notation.

---

# Upper and Lower Behaviour

A growth function may be studied from different perspectives.

One may ask:

```text
How large can the function eventually become
relative to another function?
```

or:

```text
How small can the function remain
relative to another function?
```

or:

```text
Do both functions grow at the same asymptotic rate?
```

These questions lead respectively toward:

```text
Upper Bounds
Lower Bounds
Tight Bounds
```

The formal notation is introduced in the next document.

---

# Asymptotic Analysis Is Not Approximation by Guessing

An informal statement such as:

```text
This looks quadratic.
```

is not sufficient.

A rigorous analysis should derive the cost function or enough of its structure
to justify the long-term growth claim.

For example:

```text
1 + 2 + 3 + ... + n
```

can be rewritten as:

```text
n(n + 1) / 2
```

which expands to:

```text
(n² + n) / 2
```

The dominant behaviour can then be analysed mathematically.

This is stronger than classifying the algorithm merely because two loops are
visible.

---

# Systematic Asymptotic Analysis

A useful analysis process is:

```text
1. Define the input-size parameter.
        ↓
2. Identify the resource being analysed.
        ↓
3. Derive or estimate the cost function.
        ↓
4. Identify constant factors.
        ↓
5. Identify lower-order terms.
        ↓
6. Identify the dominant growth.
        ↓
7. Compare with a reference growth function.
        ↓
8. Determine the relationship for sufficiently large inputs.
        ↓
9. Express the relationship using appropriate asymptotic notation.
```

This process provides the bridge between operation counting and formal
complexity classification.

---

# Example — Linear Cost Function

Consider:

```text
T(n) = 4n + 10
```

The terms are:

```text
4n
```

and:

```text
10
```

As `n` becomes large:

```text
4n
```

dominates the constant term.

The long-term growth is therefore linear.

The formal asymptotic statement will later be expressed using appropriate
notation.

---

# Example — Quadratic Cost Function

Consider:

```text
T(n) = 3n² + 7n + 2
```

As input grows:

```text
n²
```

dominates:

```text
n
```

and:

```text
1
```

Therefore, the function exhibits quadratic long-term growth.

Again, the exact mathematical bound is introduced in the next document.

---

# Example — Linearithmic Cost Function

Consider:

```text
T(n) = 2n log n + 5n + 20
```

For sufficiently large `n`:

```text
n log n
```

grows faster than:

```text
n
```

and:

```text
1
```

The dominant growth is therefore linearithmic.

---

# Example — Multiple Parameters

Consider:

```text
T(n, m) = nm + n
```

If `n` and `m` vary independently, the function should remain expressed using
both parameters.

One should not automatically rewrite it as:

```text
n²
```

unless the problem establishes a relationship such as:

```text
m = n
```

This preserves the actual structure of the computational problem.

---

# Relationship with Later Modules

Asymptotic analysis becomes part of every later algorithmic study.

```text
Asymptotic Analysis
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

Later algorithms may require:

* simple growth analysis;
* nested summations;
* multiple parameters;
* recurrence relations;
* amortized analysis;
* auxiliary-space analysis.

The same conceptual framework remains applicable.

---

# Common Mistakes

## Treating `n → ∞` as Literal Execution

Asymptotic analysis does not mean executing an algorithm with an infinite
input.

It studies mathematical behaviour for sufficiently large input sizes.

---

## Saying Constants Do Not Matter

Constants may matter greatly in practical performance.

They are abstracted only when the analytical objective is long-term growth.

---

## Dropping Terms Without Understanding Them

Lower-order terms should not be removed mechanically before the underlying cost
function has been understood.

---

## Treating Asymptotic Equivalence as Exact Equality

Functions may have the same asymptotic growth without being numerically equal.

---

## Assuming Slower Growth Always Means Faster Execution

A slower-growing algorithm may have larger constant factors or additional
implementation overhead for relevant input sizes.

---

## Confusing Case Analysis with Asymptotic Analysis

Best, average, and worst cases identify different cost functions.

Each function can then be analysed asymptotically.

---

## Reducing Every Problem to One Parameter

Multiple independent input dimensions should remain explicit when relevant.

---

## Using Benchmarks to Derive Asymptotic Bounds

Measurements may suggest trends but do not prove mathematical growth bounds.

---

## Jumping Directly to Big O

Big O is one asymptotic notation.

A complete analysis should first identify the cost function and understand its
growth.

---

# Best Practices

When performing asymptotic analysis:

* define the input-size parameter explicitly;
* identify which computational resource is being modelled;
* derive the cost function before simplifying it;
* preserve relevant assumptions;
* identify constants and lower-order terms;
* determine the dominant long-term behaviour;
* distinguish exact cost from asymptotic growth;
* consider sufficiently large inputs rather than only examples;
* preserve multiple parameters when necessary;
* distinguish case analysis from growth analysis;
* use formal asymptotic notation only after understanding the relationship;
* treat benchmarking as separate empirical evidence.

The objective is not merely to produce a symbol such as `O(n)`.

The objective is to justify the mathematical relationship behind that symbol.

---

# Key Takeaways

After completing this document, the learner should understand that:

* asymptotic analysis studies the long-term behaviour of computational cost
  functions;
* it focuses on sufficiently large inputs rather than exact wall-clock time;
* exact cost and asymptotic growth are different concepts;
* constant factors may be abstracted in long-term growth analysis without
  becoming irrelevant to practical execution;
* lower-order terms become relatively less significant as input size grows;
* dominant terms determine long-term growth;
* asymptotic equivalence does not mean mathematical equality;
* thresholds such as `n₀` express the idea that a relationship holds beyond a
  sufficiently large input size;
* both time and auxiliary-space functions can be analysed asymptotically;
* multiple input parameters should be preserved when the problem requires them;
* case analysis and asymptotic notation describe different dimensions of
  algorithm analysis;
* benchmarking provides empirical evidence but cannot establish mathematical
  asymptotic bounds;
* asymptotic analysis provides the reasoning required before formal notation is
  introduced.

Asymptotic analysis therefore provides the mathematical bridge between
**algorithm growth** and **asymptotic notation**.

It transforms intuitive observations about growth into relationships that can be
expressed precisely and used consistently throughout the remaining modules of
**Algorithms Java Mastery**.

---

# Next Document

```text
04-asymptotic-notations.md
```

The next document introduces the mathematical notation used to express
asymptotic relationships formally.

It develops:

```text
O(g(n))
Ω(g(n))
Θ(g(n))
o(g(n))
ω(g(n))
```

and explains the difference between:

```text
Upper Bounds
Lower Bounds
Tight Bounds
Strict Upper Bounds
Strict Lower Bounds
```

The next central question becomes:

> **How can asymptotic growth relationships be expressed precisely using
> mathematical notation?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The treatment of asymptotic growth, dominant terms, long-term function
comparison, and asymptotic bounds throughout *Introduction to Algorithms*
provides the principal academic foundation for this document.

Complementary academic and mathematical references are documented in:

```text
docs/00-project/10-references.md
```

Additional mathematical references may be introduced when the formal
definitions and proofs associated with asymptotic notation require greater
depth.
