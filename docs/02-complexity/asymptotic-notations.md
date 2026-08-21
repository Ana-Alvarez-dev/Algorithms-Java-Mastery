# Asymptotic Notations

## Algorithms Java Mastery

This document introduces **asymptotic notations**, the mathematical language
used to express and compare the long-term growth of algorithmic cost functions.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic analysis of algorithms throughout this repository.

The previous documents established the progression from computational cost to
growth behaviour and then to asymptotic analysis.

This document introduces the formal notation used to express those asymptotic
relationships precisely.

The central question addressed throughout this document is:

> **How can asymptotic relationships between algorithmic cost functions be
> expressed mathematically?**

---

# Purpose

The purpose of this document is to introduce the principal asymptotic notations
used in algorithm analysis.

These notations describe how one function relates to another for sufficiently
large input sizes.

The progression is:

```text
Cost Function
        ↓
Growth Behaviour
        ↓
Asymptotic Analysis
        ↓
Mathematical Relationship
        ↓
Asymptotic Notation
```

The principal notations studied are:

```text
O
Ω
Θ
o
ω
```

Each notation expresses a different type of asymptotic relationship.

The objective is not merely to recognise these symbols.

The learner should understand **what mathematical claim each notation makes**.

---

# Learning Objectives

After studying this document, the learner should be able to:

* explain why asymptotic notation is used;
* distinguish exact cost functions from asymptotic bounds;
* interpret asymptotic statements mathematically;
* distinguish upper, lower, and tight bounds;
* define Big O notation conceptually;
* define Big Omega notation conceptually;
* define Big Theta notation conceptually;
* understand the purpose of little o and little omega;
* recognise the role of positive constants and thresholds;
* distinguish asymptotic notation from best-, average-, and worst-case analysis;
* apply asymptotic notation to time and auxiliary-space functions;
* avoid using Big O as a universal synonym for complexity.

These competencies establish the mathematical vocabulary used throughout later
algorithm analysis.

---

# From Asymptotic Analysis to Asymptotic Notation

The previous document introduced asymptotic analysis as the comparison of
long-term function behaviour.

For example:

```text
f(n) = 3n² + 7n + 4
```

and:

```text
g(n) = n²
```

have different exact values.

However, they exhibit the same dominant quadratic growth.

Asymptotic notation provides a precise way to express relationships such as
this.

Conceptually:

```text
Exact Functions
        ↓
Compare Long-Term Growth
        ↓
Establish Mathematical Bound
        ↓
Express with Asymptotic Notation
```

---

# Asymptotic Notation Describes Functions

Asymptotic notation applies to mathematical functions.

For algorithm analysis, these functions may represent:

```text
T(n)
```

for time-related computational work, or:

```text
S(n)
```

for auxiliary-space requirements.

The notation does not describe source code directly.

Instead:

```text
Algorithm
        ↓
Cost Function
        ↓
Asymptotic Analysis
        ↓
Asymptotic Bound
```

This distinction is essential.

---

# Bounds Rather Than Exact Equality

Consider:

```text
T(n) = 4n + 10
```

A statement such as:

```text
T(n) ∈ Θ(n)
```

does not mean:

```text
4n + 10 = n
```

The functions are not equal.

The statement means that their long-term growth is bounded in a particular
mathematical relationship.

Therefore:

```text
Exact Equality
        ≠
Asymptotic Relationship
```

---

# Constants and Thresholds

Formal asymptotic definitions rely on two important ideas:

```text
Positive Constant
```

and:

```text
Threshold n₀
```

The constant scales a comparison function.

The threshold identifies a point after which the required relationship must
remain true.

Conceptually:

```text
n < n₀
        ↓
Relationship May Not Hold

n ≥ n₀
        ↓
Required Relationship Holds
```

Asymptotic notation therefore describes **eventual behaviour**.

---

# Big O — Asymptotic Upper Bound

Big O describes an asymptotic upper bound.

We write:

```text
f(n) ∈ O(g(n))
```

when there exist positive constants:

```text
c
```

and:

```text
n₀
```

such that:

```text
0 ≤ f(n) ≤ c · g(n)
```

for every:

```text
n ≥ n₀
```

Conceptually:

```text
f(n)
        ↓
Eventually Does Not Grow Faster Than
        ↓
Constant Multiple of g(n)
```

Big O therefore expresses an **upper asymptotic bound**.

---

# Interpreting Big O

The statement:

```text
f(n) ∈ O(g(n))
```

means that beyond some sufficiently large input size, `f(n)` is bounded above
by a constant multiple of `g(n)`.

It does not mean that:

* `f(n)` equals `g(n)`;
* `g(n)` is necessarily the tightest possible bound;
* the algorithm always takes exactly `g(n)` operations;
* the statement refers automatically to the worst case.

These distinctions are fundamental.

---

# Example — Big O

Consider:

```text
f(n) = 3n + 5
```

We can compare it with:

```text
g(n) = n
```

For sufficiently large positive `n`, there exists a constant `c` such that:

```text
3n + 5 ≤ c · n
```

For example, for:

```text
n ≥ 1
```

we have:

```text
3n + 5 ≤ 8n
```

Therefore:

```text
f(n) ∈ O(n)
```

The exact constants are not the final objective.

Their existence demonstrates the asymptotic bound.

---

# Big O Is Not Necessarily Tight

Suppose:

```text
f(n) = n
```

Then it is true that:

```text
f(n) ∈ O(n)
```

but it is also true that:

```text
f(n) ∈ O(n²)
```

because for sufficiently large `n`:

```text
n ≤ n²
```

Therefore, Big O alone does not necessarily describe the most precise growth
classification.

This is why the distinction between:

```text
Upper Bound
```

and:

```text
Tight Bound
```

matters.

---

# Big Omega — Asymptotic Lower Bound

Big Omega describes an asymptotic lower bound.

We write:

```text
f(n) ∈ Ω(g(n))
```

when there exist positive constants:

```text
c
```

and:

```text
n₀
```

such that:

```text
0 ≤ c · g(n) ≤ f(n)
```

for every:

```text
n ≥ n₀
```

Conceptually:

```text
f(n)
        ↓
Eventually Grows At Least As Fast As
        ↓
Constant Multiple of g(n)
```

Big Omega therefore establishes a lower asymptotic boundary.

---

# Example — Big Omega

Consider:

```text
f(n) = 3n + 5
```

For every positive `n`:

```text
3n ≤ 3n + 5
```

Therefore:

```text
f(n) ∈ Ω(n)
```

This establishes that the function grows at least linearly asymptotically.

---

# Big Theta — Asymptotically Tight Bound

Big Theta describes an asymptotically tight bound.

We write:

```text
f(n) ∈ Θ(g(n))
```

when there exist positive constants:

```text
c₁
c₂
n₀
```

such that:

```text
0 ≤ c₁ · g(n) ≤ f(n) ≤ c₂ · g(n)
```

for every:

```text
n ≥ n₀
```

Conceptually:

```text
Constant Multiple of g(n)
        ≤
f(n)
        ≤
Another Constant Multiple of g(n)
```

Therefore, `f(n)` and `g(n)` grow at the same asymptotic rate within constant
factors.

---

# Big Theta as Upper and Lower Bound

A useful interpretation is:

```text
f(n) ∈ Θ(g(n))
```

when both:

```text
f(n) ∈ O(g(n))
```

and:

```text
f(n) ∈ Ω(g(n))
```

hold.

Conceptually:

```text
Upper Bound
        +
Lower Bound
        ↓
Tight Asymptotic Bound
```

This is why Big Theta provides a more precise growth description than Big O
alone when a tight bound is known.

---

# Example — Big Theta

Consider:

```text
f(n) = 3n + 5
```

We have already established:

```text
f(n) ∈ O(n)
```

and:

```text
f(n) ∈ Ω(n)
```

Therefore:

```text
f(n) ∈ Θ(n)
```

This expresses the tight linear asymptotic growth of the function.

---

# Big O, Big Omega, and Big Theta Together

The relationship can be summarised as:

```text
O(g(n))
        ↓
Asymptotic Upper Bound

Ω(g(n))
        ↓
Asymptotic Lower Bound

Θ(g(n))
        ↓
Asymptotically Tight Bound
```

These notations describe different mathematical relationships.

They should not be treated as interchangeable.

---

# little o — Strict Asymptotic Upper Bound

little o represents a strict asymptotic upper relationship.

We write:

```text
f(n) ∈ o(g(n))
```

when `f(n)` grows strictly slower than `g(n)` asymptotically.

One useful interpretation is:

```text
lim     f(n) / g(n) = 0
n→∞
```

when the limit formulation is applicable.

Conceptually:

```text
f(n)
        ↓
Becomes Negligible Relative To
        ↓
g(n)
```

---

# Example — little o

Consider:

```text
f(n) = n
```

and:

```text
g(n) = n²
```

Then:

```text
n / n² = 1 / n
```

and:

```text
1 / n → 0
```

as `n` grows.

Therefore:

```text
n ∈ o(n²)
```

Linear growth is strictly slower than quadratic growth.

---

# little omega — Strict Asymptotic Lower Bound

little omega represents a strict asymptotic lower relationship.

We write:

```text
f(n) ∈ ω(g(n))
```

when `f(n)` grows strictly faster than `g(n)` asymptotically.

A useful limit interpretation is:

```text
lim     f(n) / g(n) = ∞
n→∞
```

when the formulation is applicable.

Conceptually:

```text
f(n)
        ↓
Eventually Dominates
        ↓
g(n)
```

---

# Example — little omega

Consider:

```text
f(n) = n²
```

and:

```text
g(n) = n
```

Then:

```text
n² / n = n
```

and:

```text
n → ∞
```

Therefore:

```text
n² ∈ ω(n)
```

Quadratic growth is strictly faster than linear growth.

---

# Big O vs little o

Big O allows the two functions to have the same asymptotic order.

For example:

```text
n ∈ O(n)
```

is true.

However:

```text
n ∈ o(n)
```

is false.

Why?

Because:

```text
n / n = 1
```

does not approach zero.

Thus:

```text
little o
```

expresses a stricter relationship than:

```text
Big O
```

---

# Big Omega vs little omega

Similarly:

```text
n ∈ Ω(n)
```

is true.

But:

```text
n ∈ ω(n)
```

is false.

little omega requires strictly faster asymptotic growth.

---

# Summary of the Five Notations

| Notation  | Meaning                   | Informal Interpretation                                                |
| --------- | ------------------------- | ---------------------------------------------------------------------- |
| `O(g(n))` | Upper bound               | Does not grow asymptotically faster than a constant multiple of `g(n)` |
| `Ω(g(n))` | Lower bound               | Grows at least as fast as a constant multiple of `g(n)`                |
| `Θ(g(n))` | Tight bound               | Grows at the same asymptotic rate within constant factors              |
| `o(g(n))` | Strict upper relationship | Grows strictly slower than `g(n)`                                      |
| `ω(g(n))` | Strict lower relationship | Grows strictly faster than `g(n)`                                      |

The informal descriptions help build intuition.

The formal definitions determine the actual mathematical meaning.

---

# Set-Theoretic Interpretation

Strictly speaking, asymptotic notation describes sets of functions.

For example:

```text
O(n²)
```

represents the set of functions asymptotically bounded above by a constant
multiple of `n²`.

Therefore, the mathematically precise expression is:

```text
f(n) ∈ O(n²)
```

rather than:

```text
f(n) = O(n²)
```

However, the equality-style notation is widely used in algorithm literature as
a conventional shorthand.

Within this repository, both forms may appear, but the learner should understand
the underlying set relationship.

---

# Why Big O Is Commonly Misunderstood

In informal software discussions, Big O is frequently used as shorthand for:

```text
the complexity of the algorithm
```

This can hide important distinctions.

For example:

```text
T(n) = n
```

satisfies:

```text
T(n) ∈ O(n)
```

but also:

```text
T(n) ∈ O(n²)
```

and:

```text
T(n) ∈ O(n³)
```

The most informative tight classification is:

```text
T(n) ∈ Θ(n)
```

Therefore:

> **Big O should not automatically be interpreted as the exact asymptotic
> growth of an algorithm.**

---

# Big O Is Not Automatically Worst Case

Another common mistake is:

```text
Big O = Worst Case
```

This is incorrect.

Big O is a mathematical upper-bound notation.

Worst-case analysis identifies a particular cost function.

For example:

```text
T_worst(n)
```

may describe the worst-case cost.

Then we may state:

```text
T_worst(n) ∈ O(g(n))
```

or perhaps more precisely:

```text
T_worst(n) ∈ Θ(g(n))
```

Conceptually:

```text
Case Analysis
        ↓
Select Cost Function
        ↓
Asymptotic Analysis
        ↓
Apply O / Ω / Θ
```

These are separate analytical dimensions.

---

# Best Case and Big Omega Are Not Synonyms

Similarly:

```text
Big Omega = Best Case
```

is incorrect.

Big Omega describes an asymptotic lower bound on a function.

It does not automatically mean that the function represents a best-case cost.

A worst-case cost function may also have a Big Omega bound.

A best-case function may also have Big O and Big Theta bounds.

---

# Big Theta Is Not “Exact Running Time”

Big Theta describes a tight **asymptotic** bound.

It does not describe exact computational cost.

For example:

```text
T(n) = 3n + 5
```

may satisfy:

```text
T(n) ∈ Θ(n)
```

But:

```text
T(n) ≠ n
```

The constants and lower-order terms remain part of the exact function.

Theta abstracts them when describing long-term growth.

---

# Example — Quadratic Function

Consider:

```text
f(n) = 3n² + 7n + 4
```

For sufficiently large `n`, the quadratic term dominates.

We can establish both:

```text
f(n) ∈ O(n²)
```

and:

```text
f(n) ∈ Ω(n²)
```

Therefore:

```text
f(n) ∈ Θ(n²)
```

This is a tight asymptotic description.

---

# Example — Linearithmic Function

Consider:

```text
f(n) = 4n log n + 10n
```

The dominant growth term is:

```text
n log n
```

Therefore, with an appropriate mathematical argument:

```text
f(n) ∈ Θ(n log n)
```

Again, this does not mean:

```text
4n log n + 10n = n log n
```

It means the functions share the same asymptotic order within constant factors.

---

# Example — Logarithm Bases

Consider:

```text
log₂ n
```

and:

```text
log₁₀ n
```

Using the change-of-base relationship:

```text
logₐ n = log_b n / log_b a
```

different logarithm bases differ only by a constant factor.

Therefore, asymptotically:

```text
log₂ n ∈ Θ(log₁₀ n)
```

This is why the logarithm base is often omitted in asymptotic complexity
expressions when the base is a fixed constant greater than one.

---

# Transitivity Intuition

Asymptotic relationships often allow comparisons to be chained.

If:

```text
f(n) ∈ O(g(n))
```

and:

```text
g(n) ∈ O(h(n))
```

then:

```text
f(n) ∈ O(h(n))
```

Similar reasoning applies to other asymptotic relationships under their
corresponding definitions.

These properties make asymptotic notation useful for systematic algorithm
comparison.

---

# Relationship with Dominant Terms

Dominant-term reasoning often provides the first intuition for an asymptotic
classification.

For example:

```text
f(n) = n³ + 100n² + 10,000
```

has dominant term:

```text
n³
```

suggesting:

```text
f(n) ∈ Θ(n³)
```

However, the notation should ultimately be justified through the asymptotic
definitions rather than by intuition alone.

---

# Relationship with Constant Factors

Consider:

```text
f(n) = 1000n
```

and:

```text
g(n) = n
```

They satisfy:

```text
f(n) ∈ Θ(g(n))
```

because constant multiplicative differences do not change their asymptotic
growth order.

This does not imply identical practical performance.

It only describes the same long-term growth family.

---

# Asymptotic Notation and Time Complexity

Asymptotic notation can describe a time-cost function.

For example:

```text
T(n) ∈ Θ(n)
```

may describe linear time growth.

Or:

```text
T(n) ∈ Θ(n log n)
```

may describe linearithmic time growth.

The notation itself does not mean “time”.

It describes the function supplied to it.

---

# Asymptotic Notation and Space Complexity

The same notation can describe auxiliary-space growth.

For example:

```text
S(n) ∈ Θ(1)
```

or:

```text
S(n) ∈ Θ(n)
```

Therefore:

```text
O
Ω
Θ
o
ω
```

are independent of the computational resource being studied.

---

# Multi-Parameter Asymptotic Notation

Some algorithms depend on multiple input-size parameters.

For example:

```text
T(V, E)
```

for a graph algorithm.

A complexity statement might involve both:

```text
Θ(V + E)
```

This expression should preserve both parameters when they vary independently.

Likewise:

```text
T(n, m) ∈ Θ(nm)
```

may describe an algorithm operating on two independent inputs.

Asymptotic notation does not require every problem to be reduced to one
variable.

---

# Asymptotic Notation and Benchmarking

Asymptotic notation describes mathematical growth relationships.

Benchmarking measures a concrete implementation.

Conceptually:

```text
Cost Function
        ↓
Asymptotic Analysis
        ↓
O / Ω / Θ / o / ω
```

while:

```text
Java Implementation
        ↓
JMH
        ↓
Observed Measurements
```

A benchmark cannot prove:

```text
T(n) ∈ Θ(n log n)
```

from a finite set of measurements.

That statement requires analytical justification.

---

# Why Benchmark Curves May Look Different

A theoretically linear algorithm may not produce a perfectly straight
benchmark graph.

Observed performance may be affected by:

* JVM warm-up;
* JIT compilation;
* cache effects;
* garbage collection;
* branch prediction;
* allocation;
* operating-system scheduling;
* measurement noise.

Therefore:

```text
Mathematical Growth
        ≠
Perfect Experimental Curve
```

The two forms of evidence should be interpreted carefully.

---

# Relationship with Later Modules

Asymptotic notation becomes a standard part of algorithm documentation
throughout the repository.

Examples include:

```text
Searching
        ↓
Time and Space Bounds

Sorting
        ↓
Case-Specific Bounds

Hash Tables
        ↓
Expected and Worst-Case Bounds

Trees
        ↓
Operation Bounds

Graphs
        ↓
Multi-Parameter Bounds

Dynamic Programming
        ↓
Time and Space Bounds
```

The appropriate notation should always correspond to the actual mathematical
claim being made.

---

# Systematic Use of Asymptotic Notation

A disciplined process is:

```text
1. Define the computational problem.
        ↓
2. Define input size.
        ↓
3. Identify the resource being analysed.
        ↓
4. Derive the relevant cost function.
        ↓
5. Determine the case being studied when relevant.
        ↓
6. Analyse long-term growth.
        ↓
7. Determine an upper, lower, or tight relationship.
        ↓
8. Select the appropriate notation.
        ↓
9. Justify the asymptotic claim.
```

Notation should be the conclusion of the analysis.

It should not replace the analysis.

---

# Common Mistakes

## Using Big O as a Universal Complexity Label

Big O represents an upper bound.

A tight bound may require Big Theta.

---

## Treating Big O as Worst Case

Worst case identifies a cost function.

Big O bounds a function asymptotically.

---

## Treating Big Omega as Best Case

Big Omega represents a lower asymptotic bound.

It is independent of whether the analysed function represents best, average, or
worst case.

---

## Treating Big Theta as Exact Cost

Theta describes tight asymptotic growth.

It does not preserve exact constants or lower-order terms.

---

## Ignoring the Input Parameter

An expression such as:

```text
O(n)
```

is meaningless unless `n` has been defined.

---

## Ignoring the Resource

A statement should make clear whether the bound applies to:

* computational work;
* auxiliary space;
* another defined resource.

---

## Using Equality Without Understanding the Convention

Expressions such as:

```text
T(n) = O(n)
```

are commonly used in algorithm literature.

The learner should nevertheless understand that `O(n)` represents a set of
functions.

---

## Assuming Every Valid Upper Bound Is Equally Informative

If:

```text
T(n) ∈ Θ(n)
```

then:

```text
T(n) ∈ O(n²)
```

may also be mathematically true.

However, it is less informative.

Prefer the tightest useful bound when it is known and relevant.

---

## Deriving Bounds from Benchmark Data Alone

Finite empirical measurements cannot establish formal asymptotic bounds.

---

# Best Practices

When using asymptotic notation:

* define the cost function first;
* define all input-size parameters;
* identify the computational resource;
* specify the relevant case when necessary;
* distinguish upper, lower, and tight bounds;
* prefer mathematically justified claims;
* use Big Theta when a tight bound is known and appropriate;
* avoid equating Big O with worst-case complexity;
* preserve multiple parameters when required;
* distinguish exact cost from asymptotic growth;
* interpret constants and thresholds correctly;
* treat little o and little omega as strict asymptotic relationships;
* use benchmarking only as complementary implementation evidence.

The objective is not to attach notation mechanically.

The objective is to express a precise mathematical relationship.

---

# Key Takeaways

After completing this document, the learner should understand that:

* asymptotic notation describes relationships between mathematical functions;
* Big O represents an asymptotic upper bound;
* Big Omega represents an asymptotic lower bound;
* Big Theta represents an asymptotically tight bound;
* little o represents strictly slower asymptotic growth;
* little omega represents strictly faster asymptotic growth;
* asymptotic notation relies on positive constants and sufficiently large input
  thresholds;
* a valid Big O bound is not necessarily the tightest bound;
* Big O is not synonymous with worst case;
* Big Omega is not synonymous with best case;
* Big Theta does not represent exact execution cost;
* case analysis and asymptotic notation describe different dimensions of
  algorithm analysis;
* asymptotic notation applies to both time and auxiliary-space functions;
* multi-parameter complexity expressions should preserve independent input
  dimensions;
* benchmarking cannot prove formal asymptotic relationships.

Asymptotic notation therefore provides the formal mathematical vocabulary used
to express the relationships developed through **computational complexity**,
**algorithm growth**, and **asymptotic analysis**.

It becomes the standard language used to describe algorithmic resource growth
throughout the remaining modules of **Algorithms Java Mastery**.

---

# Next Document

```text
05-best-average-worst-case.md
```

The next document introduces **case analysis** and explains why algorithms may
require different amounts of computational work for different inputs having the
same size.

It develops the distinction among:

```text
Best Case
Average Case
Worst Case
```

while preserving the important principle that each resulting cost function may
then be analysed using:

```text
O
Ω
Θ
```

The next central question becomes:

> **How can the computational cost of an algorithm vary among different inputs
> of the same size?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The formal treatment of asymptotic notation, function bounds, growth rates, and
algorithm analysis throughout *Introduction to Algorithms* provides the
principal academic foundation for this document.

Complementary academic and mathematical references are documented in:

```text
docs/00-project/10-references.md
```

Additional mathematical references may be introduced when formal proofs or more
advanced asymptotic relationships require greater theoretical depth.
