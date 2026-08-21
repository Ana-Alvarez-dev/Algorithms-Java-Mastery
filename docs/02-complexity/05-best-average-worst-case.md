# Best, Average and Worst Case Analysis

## Algorithms Java Mastery

This document introduces **case analysis** as the study of how the computational
cost of an algorithm may vary among different valid inputs having the same
problem size.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic analysis of algorithms throughout this repository.

The previous documents introduced computational cost functions, algorithm
growth, asymptotic analysis, and asymptotic notation.

Case analysis adds another important dimension:

> **Different problem instances of the same size may require different amounts
> of computational work.**

The central question addressed throughout this document is:

> **How can the computational cost of an algorithm vary among inputs of the
> same size?**

---

# Purpose

The purpose of this document is to establish the distinction among
**best-case**, **average-case**, and **worst-case** cost functions.

Instead of assuming that an algorithm has one universal computational cost for a
given input size, case analysis examines the range of possible behaviour.

Conceptually:

```text
Inputs of Size n
        ↓
Different Problem Instances
        ↓
Different Computational Costs
        ↓
Case Analysis
```

The resulting cost functions may then be analysed asymptotically using the
notation introduced previously.

---

# Learning Objectives

After studying this document, the learner should be able to:

* define best-case analysis;
* define average-case analysis;
* define worst-case analysis;
* explain why inputs of the same size may produce different costs;
* distinguish case analysis from asymptotic notation;
* define best-, average-, and worst-case cost functions conceptually;
* explain why average-case analysis requires a probability model;
* distinguish average case from informal “typical behaviour”;
* understand why worst-case analysis may provide useful guarantees;
* recognise when case distinctions are meaningful and when they are not;
* apply asymptotic notation independently to each relevant case.

These competencies provide a more complete framework for algorithm analysis.

---

# Why Case Analysis Is Necessary

Consider an algorithm that searches sequentially through an array.

Suppose:

```text
n = number of elements
```

The algorithm may receive several different arrays of the same size.

For example:

```text
Target at first position
```

may require very little work.

```text
Target near the middle
```

may require more work.

```text
Target at last position
```

or:

```text
Target absent
```

may require examination of the complete input.

The input size remains:

```text
n
```

but the computational cost changes.

Conceptually:

```text
Same Input Size
        ↓
Different Input Arrangement
        ↓
Different Number of Operations
```

This variation motivates case analysis.

---

# Cost as a Function of the Problem Instance

A more precise view of algorithmic cost considers both:

```text
Input Size
```

and:

```text
Specific Problem Instance
```

Conceptually:

```text
Problem Instance x
        ↓
Size |x| = n
        ↓
Algorithm
        ↓
Cost C(x)
```

Different instances `x` having the same size `n` may produce different values
of `C(x)`.

Case analysis summarises that variation.

---

# Best-Case Cost

The **best-case cost** represents the minimum computational cost among valid
inputs of size `n`.

Conceptually:

```text
T_best(n)
        =
Minimum Cost Among Inputs of Size n
```

It answers:

> **What is the least amount of computational work the algorithm may require for
> an input of size `n`?**

The best case describes the most favourable valid instance according to the
resource being analysed.

---

# Example — Linear Search Best Case

Consider linear search over an array of size `n`.

If the target appears in the first position:

```text
[Target, ..., ..., ...]
```

the algorithm may require:

```text
1 comparison
```

Therefore, the best-case cost may be represented conceptually as:

```text
T_best(n) = constant work
```

The formal asymptotic classification can then be expressed with the appropriate
notation.

---

# Worst-Case Cost

The **worst-case cost** represents the maximum computational cost among valid
inputs of size `n`.

Conceptually:

```text
T_worst(n)
        =
Maximum Cost Among Inputs of Size n
```

It answers:

> **What is the greatest amount of computational work the algorithm may require
> for an input of size `n`?**

Worst-case analysis therefore establishes an upper envelope over the possible
costs for each input size.

---

# Example — Linear Search Worst Case

For linear search, the maximum number of comparisons occurs when:

* the target is in the final position; or
* the target does not appear and the complete array must be examined.

Conceptually:

```text
n elements
        ↓
Inspect every element
        ↓
n relevant comparisons
```

Therefore, the worst-case cost grows linearly with `n`.

---

# Average-Case Cost

The **average-case cost** describes the expected computational cost under a
specified probability distribution over valid inputs of size `n`.

Conceptually:

```text
Possible Inputs of Size n
        ↓
Probability Distribution
        ↓
Cost of Each Input
        ↓
Expected Cost
```

Average-case analysis therefore depends on two components:

* the cost associated with each possible input;
* the probability assigned to those inputs.

It cannot be defined rigorously without assumptions about input distribution.

---

# Average Case Is an Expected Value

Suppose the possible valid instances are:

```text
x₁, x₂, ..., xₖ
```

with corresponding probabilities:

```text
P(x₁), P(x₂), ..., P(xₖ)
```

and costs:

```text
C(x₁), C(x₂), ..., C(xₖ)
```

Then the average cost is conceptually:

```text
Expected Cost
        =
Σ P(xᵢ) · C(xᵢ)
```

The exact formulation depends on the problem and probability model.

This is why average-case analysis is often more mathematically demanding than
best- or worst-case analysis.

---

# Average Case Is Not “Halfway”

A common mistake is to calculate:

```text
(Best Cost + Worst Cost) / 2
```

and call that the average case.

This is generally incorrect.

The average case depends on the probability distribution of inputs.

For example, if some inputs occur much more frequently than others, their costs
contribute more strongly to the expectation.

Therefore:

```text
Average Case
        ≠
Numerical Midpoint Between Best and Worst
```

---

# Average Case Is Not Automatically “Typical Behaviour”

Another common simplification is:

> The average case describes what usually happens.

This may be misleading.

Average-case analysis describes expected cost under a **specific probabilistic
model**.

If the assumed distribution does not represent the real workload, the average
case may not describe practical behaviour accurately.

Therefore:

```text
Average-Case Model
        ↓
Depends on Assumptions
        ↓
Engineering Interpretation
```

The probability model should always be documented when average-case reasoning
is used.

---

# Best, Average, and Worst as Separate Functions

Case analysis produces separate cost functions.

Conceptually:

```text
T_best(n)

T_average(n)

T_worst(n)
```

Each function answers a different question.

The relationship is:

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

These functions can have different growth behaviour.

---

# Fundamental Ordering

When all three functions are defined over the same valid input domain, we
generally expect:

```text
T_best(n)
        ≤
T_average(n)
        ≤
T_worst(n)
```

for the same input size, under the relevant model.

This relationship provides useful intuition.

However, the exact values and asymptotic growth may differ significantly.

---

# Case Analysis and Asymptotic Notation

Case analysis and asymptotic notation describe **different dimensions** of
algorithm analysis.

Case analysis asks:

> **Which cost function are we studying?**

Asymptotic notation asks:

> **How does that function grow?**

The correct progression is:

```text
Choose Case
        ↓
Obtain Cost Function
        ↓
Analyse Growth
        ↓
Apply O / Ω / Θ / o / ω
```

Therefore, the following shortcut is incorrect:

```text
Best Case  = Ω
Average    = Θ
Worst Case = O
```

---

# Big O Is Not Worst Case

Big O describes an asymptotic upper bound on **any function**.

For example, the best-case cost function may satisfy:

```text
T_best(n) ∈ O(1)
```

or:

```text
T_best(n) ∈ O(n)
```

depending on the function and the bound being stated.

Likewise, a worst-case cost function may satisfy:

```text
T_worst(n) ∈ Θ(n)
```

which is often more informative than writing only:

```text
T_worst(n) ∈ O(n)
```

Worst case identifies the function.

Big O describes a mathematical bound on that function.

---

# Big Omega Is Not Best Case

Big Omega describes an asymptotic lower bound.

It does not mean “best case”.

For example:

```text
T_worst(n) ∈ Ω(n)
```

may be a perfectly valid statement.

Likewise:

```text
T_best(n) ∈ Θ(1)
```

may describe a best-case function tightly.

Therefore:

```text
Case
        ≠
Asymptotic Notation
```

---

# Big Theta and Case Analysis

Big Theta may be applied whenever a tight asymptotic bound is known.

For example:

```text
T_best(n) ∈ Θ(1)
```

```text
T_worst(n) ∈ Θ(n)
```

and, under an appropriate probability model:

```text
T_average(n) ∈ Θ(n)
```

may all describe different cost functions of the same algorithm.

This demonstrates why the case should always be stated explicitly.

---

# Example — Linear Search

Consider a linear search through:

```text
[7, 12, 18, 25, 30]
```

with:

```text
n = number of elements
```

---

## Best Case

Search for:

```text
7
```

The target is found immediately.

Cost:

```text
1 comparison
```

Conceptually:

```text
T_best(n) ∈ Θ(1)
```

---

## Worst Case

Search for:

```text
30
```

or a value that is absent.

The algorithm may inspect all `n` elements.

Conceptually:

```text
T_worst(n) ∈ Θ(n)
```

---

## Average Case

Suppose we adopt a model in which:

* the target is guaranteed to be present; and
* every position is equally likely to contain the target.

Then the expected number of comparisons is:

```text
(1 + 2 + ... + n) / n
```

Using:

```text
1 + 2 + ... + n
        =
n(n + 1) / 2
```

we obtain:

```text
(n + 1) / 2
```

The expected number of comparisons therefore grows linearly.

Conceptually:

```text
T_average(n) ∈ Θ(n)
```

under that probability model.

The assumption is essential.

---

# Changing the Average-Case Model

Suppose instead that the target may be absent with substantial probability.

The expected cost changes.

Likewise, if some positions are more likely than others, the expected number of
comparisons changes again.

Therefore:

```text
Same Algorithm
        ↓
Different Probability Model
        ↓
Different Expected Cost
```

The average case must never be presented without its assumptions.

---

# Example — Binary Search

Consider binary search over a sorted collection.

## Best Case

The target is at the middle position examined first.

The search terminates immediately.

Conceptually:

```text
T_best(n) ∈ Θ(1)
```

## Worst Case

The algorithm repeatedly halves the active search interval until the target is
found or the interval becomes empty.

The number of reductions grows logarithmically.

Conceptually:

```text
T_worst(n) ∈ Θ(log n)
```

The average case also requires a probability model and should not simply be
assumed from intuition.

---

# Example — Insertion Sort

Case distinctions can become especially important for sorting algorithms.

For insertion sort:

```text
Already Ordered Input
```

may require relatively little movement.

```text
Reverse-Ordered Input
```

may require substantially more work.

The exact complexity also depends on which operation is being counted.

For example:

* comparisons;
* shifts;
* assignments.

Therefore, case analysis should always identify the resource and relevant
operation.

---

# Example — Quick Sort

Quicksort demonstrates how structural properties of the input and pivot
selection may affect cost.

Conceptually:

```text
Balanced Partitions
        ↓
Smaller Recursion Depth
        ↓
Favourable Growth
```

while:

```text
Highly Unbalanced Partitions
        ↓
Large Recursive Chain
        ↓
Unfavourable Growth
```

The detailed case analysis depends on the partitioning strategy and probability
model.

This topic is developed later in the sorting module.

---

# Example — Hash Tables

Hash tables provide another important example.

Operation cost may depend on:

* hash-function behaviour;
* load factor;
* collision resolution;
* distribution of keys.

Under suitable assumptions, expected operation cost may be very small.

However, unfavourable collision patterns may produce much larger costs.

Therefore:

```text
Expected Behaviour
```

and:

```text
Worst-Case Behaviour
```

may differ substantially.

Average or expected complexity claims must state their assumptions.

---

# Why Worst-Case Analysis Is Useful

Worst-case analysis provides a bound on the maximum cost over valid inputs of a
given size.

This can be valuable when:

* response-time guarantees matter;
* resource limits are strict;
* unpredictability is undesirable;
* safety margins are required;
* adversarial inputs are possible.

However, worst-case analysis should not be treated as universally superior.

Its usefulness depends on the engineering problem.

---

# Worst Case Is Not Always the Most Relevant Case

Some systems may care more about:

* expected behaviour;
* percentile latency;
* common workload distributions;
* amortized behaviour;
* adversarial behaviour.

For example, an algorithm with an uncommon expensive worst case may still be
appropriate if its expected performance is excellent and the worst case can be
tolerated.

The relevant case should therefore be chosen according to the actual analytical
question.

---

# Case Analysis and Guarantees

Different cases provide different kinds of information.

```text
Best Case
        ↓
Optimistic Lower Scenario

Average Case
        ↓
Expected Behaviour Under Assumptions

Worst Case
        ↓
Maximum Cost Guarantee
```

The engineering value of each depends on the problem.

---

# Case Analysis and Scalability

Scalability conclusions should identify the case being analysed.

For example:

```text
Worst-Case Linear Growth
```

and:

```text
Average-Case Constant Growth
```

describe very different performance profiles.

Therefore, statements such as:

```text
This operation is O(1).
```

may be incomplete if the operation has materially different worst-case
behaviour.

A precise analysis should identify:

* the case;
* the resource;
* the assumptions;
* the asymptotic bound.

---

# Case Analysis and Preconditions

Case analysis should consider only valid inputs satisfying the algorithmic
contract.

For example, binary search assumes:

```text
Input is ordered.
```

An unsorted array is not a “worst-case binary-search input”.

It violates the precondition.

Conceptually:

```text
Valid Domain
        ↓
Case Analysis
```

rather than:

```text
All Possible Inputs
        ↓
Case Analysis
```

This distinction preserves consistency with the **Foundations** module.

---

# Case Analysis and Edge Cases

Edge cases and complexity cases are not the same concept.

An edge case refers to a boundary or unusual problem instance.

A best or worst complexity case refers to computational cost.

An edge case may happen to produce:

* best-case cost;
* worst-case cost;
* neither.

Therefore:

```text
Edge Case
        ≠
Best / Average / Worst Case
```

The two concepts should be analysed independently.

---

# Case Analysis and Benchmarking

Benchmarking may use multiple input distributions to investigate implementation
behaviour.

Examples may include:

```text
Ordered Input
```

```text
Reverse-Ordered Input
```

```text
Random Input
```

```text
Duplicate-Heavy Input
```

However, benchmark scenarios do not automatically correspond to formal
best-case, average-case, or worst-case definitions.

A formal average case requires a probability model.

A formal worst case requires reasoning over the complete valid input domain of
the specified size.

Therefore:

> **Benchmark datasets illustrate implementation behaviour; they do not define
> theoretical cases by themselves.**

---

# Average Case and Random Benchmark Data

Randomly generated benchmark input should not automatically be labelled:

```text
Average Case
```

unless the random-generation process corresponds to the probability distribution
assumed by the theoretical average-case analysis.

Otherwise, it is more precise to describe it as:

```text
Random Input Distribution
```

or:

```text
Experimental Workload
```

This distinction improves experimental rigor.

---

# Case Analysis and Amortized Analysis

Average-case analysis and amortized analysis are different concepts.

## Average-Case Analysis

Uses a probability distribution over inputs.

```text
Possible Inputs
        ↓
Probabilities
        ↓
Expected Cost
```

## Amortized Analysis

Studies the total cost of a sequence of operations without requiring a
probability distribution.

```text
Sequence of Operations
        ↓
Total Cost
        ↓
Cost Distributed Across Operations
```

Therefore:

```text
Average Case
        ≠
Amortized Analysis
```

Amortized analysis is studied later in:

```text
08-amortized-analysis.md
```

---

# Case Analysis and Time Complexity

Best, average, and worst cases are often discussed in relation to time
complexity.

For example:

```text
T_best(n)
T_average(n)
T_worst(n)
```

Each represents a different time-cost function.

Each may then receive its own asymptotic classification.

---

# Case Analysis and Space Complexity

Case analysis may also apply to memory requirements.

For example, an algorithm may allocate different amounts of auxiliary storage
depending on:

* recursion depth;
* input structure;
* branch behaviour.

Therefore:

```text
S_best(n)
S_average(n)
S_worst(n)
```

may also be meaningful when memory usage varies by input.

Case analysis is not inherently restricted to time.

---

# Not Every Algorithm Requires Three Distinct Cases

Some algorithms perform essentially the same amount of work for every input of
the same size.

In such cases:

```text
T_best(n)
≈
T_average(n)
≈
T_worst(n)
```

or the distinctions may not provide meaningful additional insight.

Therefore, the repository should not mechanically force every algorithm to
include three case sections.

Case analysis should be used when input-dependent variation is relevant.

---

# When Average-Case Analysis Is Appropriate

Average-case analysis is appropriate when:

* a meaningful probability distribution can be defined;
* expected behaviour matters to the learning objective;
* the assumptions can be justified;
* the mathematics remains educationally useful.

It should be avoided when “average” would merely be an informal guess.

---

# Expected Case vs Average Case

The phrase:

```text
Expected Complexity
```

often refers to an expectation derived from randomness.

That randomness may come from:

* the input distribution;
* the algorithm itself.

For example, a randomised algorithm may have expected cost even for a fixed
input.

This distinction becomes relevant in more advanced algorithm analysis.

For the current module, the key principle is:

> **Expected cost must identify the source of randomness and the corresponding
> probability model.**

---

# Relationship with Later Modules

Case analysis becomes useful throughout later modules.

Examples include:

```text
Linear Search
        ↓
Best / Average / Worst Cost
```

```text
Binary Search
        ↓
Best / Worst Cost
```

```text
Insertion Sort
        ↓
Input-Dependent Cost
```

```text
Quicksort
        ↓
Partition-Dependent Cost
```

```text
Hash Tables
        ↓
Expected / Worst-Case Operations
```

```text
Trees
        ↓
Structure-Dependent Operation Cost
```

Not every algorithm will require identical case analysis.

The relevant scenarios depend on the algorithm and the question being studied.

---

# Systematic Case Analysis

A useful process is:

```text
1. Define the computational problem.
        ↓
2. Define input size.
        ↓
3. Define the resource or operation being analysed.
        ↓
4. Define the valid input domain.
        ↓
5. Identify the minimum possible cost.
        ↓
6. Identify the maximum possible cost.
        ↓
7. Determine whether an average-case model is meaningful.
        ↓
8. Define the probability distribution when required.
        ↓
9. Derive the corresponding cost functions.
        ↓
10. Analyse each relevant function asymptotically.
```

This process separates case identification from asymptotic classification.

---

# Common Mistakes

## Writing Best Case = Big Omega

Big Omega is a lower-bound notation.

Best case is a cost function.

They are different concepts.

---

## Writing Worst Case = Big O

Big O is an upper-bound notation.

Worst case identifies the maximum cost function over inputs of size `n`.

---

## Assuming Average Case Is Halfway Between Best and Worst

Average-case analysis requires a probability distribution.

---

## Calling Random Input the Average Case

Random benchmark input represents the average case only if its distribution
matches the theoretical probability model.

---

## Ignoring Preconditions

Inputs violating the algorithmic contract do not belong to best-, average-, or
worst-case analysis for that contract.

---

## Treating Edge Cases as Complexity Cases

Boundary inputs and cost-extreme inputs are different concepts.

---

## Reporting One Complexity Without Naming the Case

A statement such as:

```text
Quicksort is O(n log n).
```

may be incomplete or misleading without identifying the analysed scenario and
type of bound.

---

## Assuming Worst Case Is Always the Most Important

The relevant case depends on engineering requirements.

---

## Forcing Three Cases onto Every Algorithm

If an algorithm performs the same work across all inputs of a given size,
separate case analysis may provide no additional value.

---

# Best Practices

When performing case analysis:

* define input size first;
* identify the resource being analysed;
* restrict analysis to valid inputs;
* derive best and worst costs from the input domain;
* define probability assumptions explicitly for average-case analysis;
* distinguish case analysis from asymptotic notation;
* use tight bounds when known;
* distinguish edge cases from complexity cases;
* avoid labelling random datasets as average case without justification;
* consider whether the distinction among cases is actually meaningful;
* document the assumptions behind every expected-cost claim;
* interpret the relevant case according to the engineering context.

The objective is to describe the algorithm's performance profile precisely.

---

# Key Takeaways

After completing this document, the learner should understand that:

* inputs of the same size may produce different computational costs;
* best-case cost is the minimum cost among valid inputs of size `n`;
* worst-case cost is the maximum cost among valid inputs of size `n`;
* average-case cost is an expected value defined under a probability model;
* average case is not the midpoint between best and worst;
* average case should not be interpreted as “typical” without justified
  assumptions;
* best, average, and worst cases are cost functions;
* Big O, Big Omega, and Big Theta are asymptotic notations applied to those
  functions;
* Big O is not synonymous with worst case;
* Big Omega is not synonymous with best case;
* a tight bound may be expressed with Big Theta for any relevant case;
* case analysis may apply to both time and space;
* average-case and amortized analyses are different techniques;
* not every algorithm requires three meaningful case distinctions.

Case analysis therefore adds an important dimension to computational complexity.

It explains how the same algorithm may require different resources for
different valid inputs and provides the foundation for precise statements such
as:

```text
Best-case time:     Θ(...)
Average-case time:  Θ(...) under defined assumptions
Worst-case time:    Θ(...)
```

when those distinctions are meaningful.

---

# Next Document

```text
06-time-complexity.md
```

The next document focuses specifically on **time complexity** and develops the
methods used to model computational work.

It introduces the systematic analysis of:

```text
Significant Operations
        ↓
Operation Counting
        ↓
Cost Functions
        ↓
Loops and Conditionals
        ↓
Recursive Structure
        ↓
Asymptotic Time Bounds
```

The next central question becomes:

> **How can the amount of computational work performed by an algorithm be
> modelled and analysed systematically?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The treatment of best-case, expected, and worst-case running times throughout
*Introduction to Algorithms* provides the principal academic foundation for
this document.

The distinction between case analysis and asymptotic notation follows the same
mathematical framework established in the preceding complexity documents.

Complementary academic and mathematical references are documented in:

```text
docs/00-project/10-references.md
```

Additional probability references may be introduced when average-case or
expected-cost analysis requires greater mathematical depth.
