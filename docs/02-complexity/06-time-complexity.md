# Time Complexity

## Algorithms Java Mastery

This document introduces **time complexity** as the analysis of how the
computational work required by an algorithm grows as the size of the input
increases.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic analysis of algorithms throughout this repository.

The previous documents established the concepts of computational cost,
algorithm growth, asymptotic analysis, asymptotic notation, and case analysis.

This document applies those concepts specifically to the computational work
performed by an algorithm.

The central question addressed throughout this document is:

> **How can the amount of computational work performed by an algorithm be
> modelled and analysed as the input size increases?**

---

# Purpose

The purpose of this document is to establish a systematic methodology for
analysing time-related computational cost.

Time complexity does not attempt to predict exact wall-clock execution time.

Instead, it models the amount of algorithmic work performed as a function of
problem size.

The general progression is:

```text
Algorithm
        ↓
Define Input Size
        ↓
Identify Significant Operations
        ↓
Count or Model Their Execution
        ↓
Derive Cost Function
        ↓
Analyse Growth
        ↓
Establish Asymptotic Time Bound
```

The objective is to understand **why** an algorithm has a particular time
complexity rather than attaching a memorised complexity label to its code.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define time complexity;
- distinguish computational work from wall-clock execution time;
- define the relevant input-size parameter;
- identify significant operations;
- construct simple cost functions;
- analyse sequential statements;
- analyse loops systematically;
- analyse nested loops from their actual bounds;
- analyse conditional branches;
- recognise input-dependent execution cost;
- relate best-, average-, and worst-case functions to time complexity;
- understand how recursive structure produces recurrence relations;
- derive simple asymptotic time bounds;
- distinguish theoretical time complexity from benchmarking.

These competencies provide the practical analytical tools required for later
algorithm modules.

---

# What Is Time Complexity?

**Time complexity** describes how the amount of computational work required by
an algorithm grows relative to the size of the problem.

Conceptually:

```text
Input Size
        ↓
Algorithm
        ↓
Computational Work
        ↓
Time-Cost Function
        ↓
Asymptotic Growth
```

Time complexity does not directly answer:

> How many milliseconds will this program require?

Instead, it asks:

> **How does the amount of computational work grow as the problem size
> increases?**

---

# Computational Work

An algorithm performs operations while transforming input into output.

Examples may include:

- comparisons;
- assignments;
- arithmetic operations;
- logical operations;
- array accesses;
- pointer or reference accesses;
- recursive calls;
- data-structure operations.

However, theoretical analysis does not necessarily count every low-level
machine instruction.

Instead, the learner selects operations that meaningfully represent the work
performed by the algorithm.

---

# Significant Operations

A **significant operation** is an operation chosen as a useful representative of
algorithmic work.

For a search algorithm, this may be:

```text
Compare current value with target.
```

For a sorting algorithm, it may be:

```text
Compare two elements.
```

or:

```text
Move an element.
```

For a graph algorithm, it may be:

```text
Process an edge.
```

The appropriate operation depends on the algorithm and the analytical question.

Conceptually:

```text
Algorithm
        ↓
Identify Relevant Work
        ↓
Count as Function of Input Size
        ↓
Time-Cost Function
```

---

# Why Use a Cost Model?

Physical execution time depends on many external factors.

Examples include:

- processor architecture;
- cache behaviour;
- JVM implementation;
- JIT compilation;
- operating-system scheduling;
- compiler optimisation;
- garbage collection;
- memory hierarchy.

Therefore:

```text
Same Algorithm
        ↓
Different Execution Environment
        ↓
Different Wall-Clock Time
```

A theoretical cost model abstracts many of these effects.

The objective is to analyse the underlying algorithmic structure.

---

# Input Size

Time complexity must always be expressed relative to a defined problem size.

The symbol:

```text
n
```

is frequently used, but its meaning depends on the problem.

Examples include:

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

A meaningful time-complexity statement therefore requires an explicit
definition of its input-size parameters.

---

# Time-Cost Functions

A time-cost function represents the amount of computational work associated
with an input size.

For example:

```text
T(n) = 3n + 2
```

or:

```text
T(n) = n² + n
```

or:

```text
T(n) = log n + 5
```

The exact expression may result from operation counting or a simplified
analytical model.

The next step is to analyse how this function grows.

---

# Exact Operation Count vs Asymptotic Time Complexity

These two concepts should remain distinct.

Consider:

```text
T(n) = 4n + 7
```

This expression describes a more detailed cost model.

Its long-term growth is linear.

Therefore, an appropriate tight asymptotic statement may be:

```text
T(n) ∈ Θ(n)
```

The progression is:

```text
Operation Count
        ↓
Cost Function
        ↓
Growth Analysis
        ↓
Asymptotic Time Bound
```

The asymptotic result does not preserve every constant and lower-order term.

---

# Constant-Time Work

A fixed amount of modelled computational work does not grow with input size.

For example:

```text
int first = numbers[0];
```

Assuming valid indexed access, this operation does not require scanning the
array.

Conceptually:

```text
n = 10
        ↓
Fixed Work

n = 1,000,000
        ↓
Fixed Work
```

A constant cost function may therefore be represented as:

```text
T(n) = c
```

and tightly bounded by:

```text
Θ(1)
```

under the adopted computational model.

---

# Sequential Statements

Consider several constant-time operations executed sequentially:

```text
Operation A
Operation B
Operation C
```

If their costs are:

```text
c₁
c₂
c₃
```

then:

```text
T(n) = c₁ + c₂ + c₃
```

which remains constant with respect to `n`.

If one sequential component grows faster than the others, the total growth is
eventually dominated by the fastest-growing term.

For example:

```text
T(n) = n + n²
```

has quadratic long-term growth.

---

# Single Loops

Consider:

```text
for (int i = 0; i < n; i++) {
    process(i);
}
```

Assume:

```text
process(i)
```

requires constant work.

Then the loop body executes approximately `n` times.

Conceptually:

```text
n iterations
        ×
constant work per iteration
        ↓
linear growth
```

A simplified cost model may therefore be:

```text
T(n) = cn + d
```

with tight asymptotic growth:

```text
Θ(n)
```

---

# Loop Analysis Requires Bounds

The existence of a loop does not automatically imply linear complexity.

Consider:

```text
// n is an int with 0 <= n <= Integer.MAX_VALUE.
for (long i = 1; i < n; i *= 2) {
    process(i); // assumed constant work on this model word
}
```

The values of `i` evolve approximately as:

```text
1
2
4
8
16
...
```

The number of iterations is proportional to:

```text
log n
```

Therefore, assuming constant work per iteration:

```text
T(n) ∈ Θ(log n)
```

For integer n >= 1 the body executes ceil(log2(n)) times (zero at n=1).
The long counter is safe for this declared int range: its first value reaching
or exceeding n is at most 2^31. This is not a promise of safety for arbitrary
long limits.

The previous int-counter version was not a valid logarithmic-time example for
all positive int bounds. At n=1_073_741_825 it reaches 2^30, then multiplication
wraps to -2^31 and later to zero. Zero remains below n and doubling it makes no
progress. The derivation follows the finite-width rules in
[Java SE 21 JLS sections 4.2.1-4.2.2](https://docs.oracle.com/javase/specs/jls/se21/html/jls-4.html#jls-4.2.2)
and [15.17.1](https://docs.oracle.com/javase/specs/jls/se21/html/jls-15.html#jls-15.17.1).

The loop bound and arithmetic model, not merely the presence of a loop,
determine termination and growth.

---

# Nested Loops

Consider:

```text
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        process(i, j);
    }
}
```

If:

- the outer loop executes `n` times;
- the inner loop executes `n` times for each outer iteration;
- the body performs constant work;

then:

```text
n × n = n²
```

and:

```text
T(n) ∈ Θ(n²)
```

However:

> **Nested loops do not automatically imply quadratic complexity.**

Their iteration bounds must be analysed.

---

# Dependent Nested Loops

Consider:

```text
// n is a non-negative int; long counters also avoid <= bound wraparound.
for (long i = 1; i <= n; i++) {
    for (long j = 1; j <= i; j++) {
        process(i, j);
    }
}
```

The inner loop performs:

```text
1 + 2 + 3 + ... + n
```

iterations.

Using:

```text
1 + 2 + ... + n
        =
n(n + 1) / 2
```

the cost grows quadratically.

Therefore:

```text
T(n) ∈ Θ(n²)
```

The result is still quadratic, but the derivation is different from simply
multiplying `n × n`.

---

# Geometrically Changing Loops

Consider:

```text
for (int i = n; i > 1; i /= 2) {
    process(i);
}
```

The remaining value is repeatedly divided by two.

Conceptually:

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

For integer n >= 1, truncating non-negative integer division gives exactly
floor(log2(n)) body executions. Thus it differs by one from doubling for many
non-power-of-two limits. For n=0 the body is not executed.

Therefore:

```text
T(n) ∈ Θ(log n)
```

assuming constant work per iteration.

---

# Sequential Loops

Consider:

```text
Loop A → Θ(n)

Loop B → Θ(n²)
```

executed sequentially.

The total cost may be represented as:

```text
T(n) = an + bn²
```

The dominant growth is quadratic.

Therefore:

```text
T(n) ∈ Θ(n²)
```

Sequential complexities are combined through addition before asymptotic
simplification.

---

# Conditional Statements

Consider:

```text
if (condition) {
    operationA();
} else {
    operationB();
}
```

The time cost depends on which branch executes.

If:

```text
operationA → T_A(n)
```

and:

```text
operationB → T_B(n)
```

then case analysis may be required.

Include the work needed to evaluate condition as well as the selected branch.
For a concrete input x, C(x)=C_condition(x)+C_selected_branch(x).
The maximum of branch upper bounds gives an upper bound, but does not prove a
tight worst case unless the expensive branch/cost is attainable by valid inputs.
Expected cost requires the corresponding branch probabilities and costs.
For example, scanning an array to decide a condition already costs Θ(n) even
if both branches perform only constant work.

Conditionals therefore connect time complexity with the case-analysis framework
introduced previously.

---

# Early Termination

Some algorithms terminate before processing the complete input.

Linear search is a common example.

Conceptually:

```text
Target Found Early
        ↓
Few Comparisons
```

versus:

```text
Target Found Late or Absent
        ↓
Many Comparisons
```

Thus, the same algorithm may produce:

```text
T_best(n)
T_average(n)
T_worst(n)
```

with different costs.

---

# Time Complexity and Case Analysis

The previous document introduced separate case functions.

Time complexity may therefore be written as:

```text
T_best(n)
```

```text
T_average(n)
```

```text
T_worst(n)
```

Each function can then receive its own asymptotic analysis.

For example, linear search may have:

```text
Best-case time:
Θ(1)
```

and:

```text
Worst-case time:
Θ(n)
```

while average-case analysis depends on the probability model.

---

# Big O Is Not the Time Complexity

A common shortcut is:

```text
Time complexity = O(n)
```

This may be incomplete.

Big O provides an asymptotic upper bound.

If a tight bound is known, a more precise statement may be:

```text
T_worst(n) ∈ Θ(n)
```

A rigorous analysis should identify:

- the case being analysed;
- the resource;
- the cost function;
- the type of asymptotic bound.

---

# Example — Maximum Element

Consider a maximum-value algorithm over a non-empty array.

A significant operation may be:

```text
Compare current value with current maximum.
```

For an array of size:

```text
n
```

the algorithm compares the remaining elements after initialisation.

Number of comparisons:

```text
n - 1
```

Therefore:

```text
T(n) = n - 1
```

for that selected significant operation.

The long-term growth is linear:

```text
T(n) ∈ Θ(n)
```

The result follows from the operation count rather than from the presence of a
loop alone.

---

# Example — Linear Search

Consider linear search over `n` elements.

Significant operation:

```text
Compare current element with target.
```

## Best Case

```text
1 comparison
```

Therefore:

```text
T_best(n) ∈ Θ(1)
```

## Worst Case

```text
n comparisons
```

Therefore:

```text
T_worst(n) ∈ Θ(n)
```

## Average Case

Requires a probability model.

For n >= 1, under a model with exactly one target whose position is uniform
over the n positions:

```text
Expected comparisons
        =
(n + 1) / 2
```

and therefore:

```text
T_average(n) ∈ Θ(n)
```

under those assumptions.

---

# Example — Binary Search

Binary search repeatedly reduces the active search interval.

Conceptually:

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

Assume a non-null sorted array with constant-time indexing and comparisons.
For the inclusive interval [left,right] with middle=left+(right-left)/2, a
midpoint probe is one iteration, not one individual Java comparison expression.
For n >= 1, at most floor(log2(n))+1 probes are needed. A last-element target
in a strictly increasing array attains this maximum: the remaining right half
has size floor(n/2) after each unsuccessful probe. At n=1024 that is eleven
probes, although ten halvings reduce 1024 to one. For empty input there are no
probes and constant control work.

Therefore, in the worst case:

```text
T_worst(n) ∈ Θ(log n)
```

while the best case may be:

```text
T_best(n) ∈ Θ(1)
```

when the target is found immediately.

---

# Example — Pairwise Processing

Suppose an algorithm examines every pair of positions:

```text
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        process(i, j);
    }
}
```

The number of pairs is:

```text
n(n - 1) / 2
```

which expands to:

```text
(n² - n) / 2
```

The dominant term is quadratic.

Therefore:

```text
T(n) ∈ Θ(n²)
```

Again, the conclusion follows from counting the actual work.

---

# Recursive Algorithms

Recursive algorithms generate computational work through recursive calls.

For example:

```text
Problem of Size n
        ↓
Recursive Call on Smaller Problem
        ↓
Additional Work
```

The cost may be represented through a recurrence such as:

```text
T(n) = T(n - 1) + c
```

or:

```text
T(n) = T(n / 2) + c
```

or:

```text
T(n) = 2T(n / 2) + n
```

The recurrence describes how the cost of a larger problem depends on the cost
of smaller subproblems.

---

# Recursive Calls Are Not Automatically Exponential

The presence of recursion does not determine complexity.

For example:

```text
T(n) = T(n / 2) + c
```

may produce logarithmic growth.

Whereas:

```text
T(n) = 2T(n - 1) + c
```

may produce exponential growth.

The recursion structure must be analysed.

Therefore:

> **Recursion is an implementation or algorithmic structure, not a complexity
> class.**

---

# Recurrence Relations

A **recurrence relation** expresses the cost of a recursive computation in terms
of smaller instances.

Conceptually:

```text
T(n)
        ↓
Subproblem Costs
        +
Non-Recursive Work
```

For example:

```text
T(n) = 2T(n / 2) + n
```

contains:

```text
2T(n / 2)
```

for recursive subproblems and:

```text
n
```

for additional work performed at the current level.

Formal recurrence-solving techniques are introduced later where required by
recursive and divide-and-conquer algorithms.

---

# Recurrences Need a Domain and Base Cost

For example, on sizes n=2^k with T(1)=1:

```text
T(n)=T(n/2)+1       gives T(2^k)=k+1.
T(n)=2T(n/2)+n      gives T(2^k)=n*(k+1).
```

The second formula includes the n leaf costs and k levels of n work.
For arbitrary integer sizes, state the actual rounded subproblem sizes and a
base case for n <= 1. Merge sort can use
T(n)=T(floor(n/2))+T(ceil(n/2))+Θ(n) for n >= 2. The halving expression is not
a recursive definition down to fractional sizes without a stopping rule.
Copying a subarray costs Θ(n) at that level, so a recursive search that copies
halves has T(n)=T(floor(n/2))+Θ(n)=Θ(n) worst-case work, rather than the
constant-extra-work logarithmic recurrence.

---

# Multi-Parameter Time Complexity

Some algorithms depend on several independent input dimensions.

For a graph algorithm:

```text
T(V, E)
```

may be required.

For two collections:

```text
T(n, m)
```

may be appropriate.

For example:

```text
for each element in A:
    for each element in B:
        process pair
```

may produce:

```text
T(n, m) ∈ Θ(nm)
```

when:

```text
n = |A|
m = |B|
```

This Θ(nm) total-work shorthand assumes n,m >= 1. With zero-sized inputs,
outer-loop checks still matter: a direct nested implementation with constant
setup has Θ(1+n+nm) work, even when m=0 and no pair is processed. Counting
only pair-body executions gives exactly nm instead. State which cost is meant.

The parameters should remain separate unless the problem defines a relationship
between them.

---

# Time Complexity and Auxiliary Work

Time complexity should also distinguish the algorithmic work being modelled
from unrelated implementation overhead.

For example, inserting:

```text
System.out.println(...)
```

inside a loop can dominate observed execution time.

However, if output is not part of the computational problem being analysed,
using I/O operations as the principal complexity model may obscure the intended
algorithmic behaviour.

The cost model should reflect the problem and analytical objective. An analysis
of the core scan may explicitly exclude diagnostic I/O, but a bound claimed for
the whole implementation must include the actual I/O, allocation, copying,
validation and helper-call work. Work does not become constant or disappear
merely because it is described as overhead.

---

# Theoretical Time Complexity vs Execution Time

The distinction can be summarised as:

```text
Time Complexity
        ↓
Mathematical Model of Computational Work
```

versus:

```text
Execution Time
        ↓
Observed Duration on a Concrete System
```

Two implementations may have:

```text
Same Θ(n) Growth
```

while exhibiting very different wall-clock performance.

Likewise, an implementation with worse asymptotic growth may perform faster for
small inputs due to constant factors.

---

# Time Complexity and Benchmarking

Benchmarking evaluates concrete implementation performance.

Time complexity analyses the theoretical cost model.

Conceptually:

```text
Algorithm
        ↓
Time-Cost Function
        ↓
Asymptotic Time Complexity
```

and separately:

```text
Java Implementation
        ↓
JMH
        ↓
Measured Runtime Behaviour
```

JMH may provide useful evidence about:

- constant factors;
- implementation differences;
- JVM effects;
- crossover points;
- real execution trends.

However:

> **Benchmarking does not derive or prove time complexity.**

---

# Benchmarking Is Not Required for Every Algorithm

Time-complexity analysis is a fundamental part of algorithm study.

Benchmarking is used only when empirical performance evaluation provides
meaningful additional evidence.

Therefore:

```text
Time Complexity
        ↓
Required Theoretical Analysis
```

while:

```text
Benchmarking
        ↓
Optional Experimental Evaluation
```

This distinction maintains consistency with the project methodology.

---

# Time Complexity and Java Implementation

Time complexity belongs primarily to the algorithmic model.

Java implementation follows later.

Conceptually:

```text
Algorithm
        ↓
Theoretical Time Complexity
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Benchmarking When Appropriate
```

Implementation should preserve the algorithm whose complexity was analysed.

A significantly different implementation strategy may require a new analysis.

---

# Time Complexity and Automated Testing

Automated tests do not establish time complexity.

Testing answers:

> Does the implementation behave correctly for selected inputs?

Time-complexity analysis asks:

> How does computational work grow?

Therefore:

```text
Testing
        ≠
Time-Complexity Analysis
```

Testing remains essential for behavioural validation but serves a different
purpose.

---

# Time Complexity and Space Complexity

Time and space are distinct computational resources.

Conceptually:

```text
Algorithm
        │
        ├── Time Cost T(n)
        │
        └── Space Cost S(n)
```

An algorithm may reduce time by consuming additional memory, or reduce memory by
performing additional computation.

The next document studies this second dimension in greater depth.

---

# Systematic Time-Complexity Analysis

A useful analysis process is:

```text
1. Define the computational problem.
        ↓
2. Define input size.
        ↓
3. Identify the case being analysed when relevant.
        ↓
4. Select significant operations.
        ↓
5. Determine how often those operations execute.
        ↓
6. Construct a cost function.
        ↓
7. Simplify the growth model carefully.
        ↓
8. Apply asymptotic analysis.
        ↓
9. State the appropriate time bound.
        ↓
10. Document assumptions.
```

This progression makes the reasoning traceable.

---

# Analysis Checklist

Before concluding that an algorithm has a particular time complexity, ask:

- What does `n` represent?
- Are there multiple independent parameters?
- Which resource am I analysing?
- Which operations represent the principal work?
- How many times can those operations execute?
- Do loop bounds depend on each other?
- Does a condition change the number of operations?
- Is early termination possible?
- Which case am I analysing?
- Does recursion generate multiple subproblems?
- Is the bound upper, lower, or tight?
- Which assumptions support the conclusion?

If these questions cannot be answered, the complexity statement may be
insufficiently justified.

---

# Common Mistakes

## Counting Every Machine Instruction

Theoretical analysis normally uses a simplified computational model.

The goal is not to simulate processor execution.

---

## Treating One Operation Type as Universally Significant

The important operation depends on the algorithm.

---

## Assuming Every Loop Is Linear

Loop variables may grow or shrink geometrically.

---

## Assuming Nested Loops Are Always Quadratic

Their actual bounds must be analysed.

---

## Multiplying Sequential Loops

Sequential costs are generally added.

Nested costs may multiply depending on their bounds.

---

## Assuming Recursion Means Exponential Time

Recursive structure must be represented and analysed through its recurrence.

---

## Ignoring Case Analysis

Algorithms with early termination or input-dependent branching may have
different cost functions.

---

## Writing Only Big O When a Tight Bound Is Known

For example:

```text
T(n) ∈ O(n)
```

may be true but less informative than:

```text
T(n) ∈ Θ(n)
```

when linear growth is known tightly.

---

## Confusing Runtime with Time Complexity

Milliseconds describe empirical execution.

Time complexity describes theoretical growth.

---

## Inferring Complexity from Benchmark Curves

Finite measurements cannot prove an asymptotic time bound.

---

# Best Practices

When analysing time complexity:

- define all input-size parameters explicitly;
- identify significant operations;
- derive operation counts from algorithm structure;
- analyse actual loop bounds;
- distinguish sequential and nested work;
- identify early termination;
- specify the relevant case;
- use recurrence relations for recursive structure;
- preserve multiple parameters when necessary;
- derive the cost function before applying asymptotic notation;
- use tight bounds when justified;
- document assumptions;
- separate theoretical cost from empirical performance.

The objective is not simply to write:

```text
O(...)
```

The objective is to explain **why the algorithm requires that amount of
computational work**.

---

# Key Takeaways

After completing this document, the learner should understand that:

- time complexity models computational work rather than physical execution time;
- input size must be defined before analysing cost;
- significant operations provide a useful analytical abstraction;
- operation counting leads to cost functions;
- asymptotic time complexity describes the growth of those functions;
- constant operations, loops, nested loops, conditionals, and recursion require
  different analytical reasoning;
- loop syntax alone does not determine complexity;
- nested loops do not automatically imply quadratic growth;
- recursion does not automatically imply exponential growth;
- best-, average-, and worst-case analysis may produce different time-cost
  functions;
- Big O, Big Omega, and Big Theta describe bounds on those functions rather
  than cases themselves;
- multi-parameter problems should preserve independent size variables;
- benchmarking measures concrete implementation performance but does not prove
  theoretical time complexity.

Time complexity therefore provides the systematic framework required to model,
derive, and justify the computational work performed by algorithms.

It connects **operation counting**, **cost functions**, **case analysis**,
**asymptotic notation**, and later **experimental performance evaluation**
throughout **Algorithms Java Mastery**.

---

# Next Document

```text
07-space-complexity.md
```

The next document introduces **space complexity** and examines how the memory
required by an algorithm grows with problem size.

It develops the distinction among:

```text
Input Storage
        ↓
Auxiliary Space
        ↓
Temporary Structures
        ↓
Recursion Stack
        ↓
Asymptotic Space Growth
```

The next central question becomes:

> **How much additional memory does an algorithm require as the problem size
> increases?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Project bibliography: Fourth Edition (2022), MIT Press.

The analysis of running time, operation counts, case-dependent cost, recurrence
relations, and asymptotic bounds throughout *Introduction to Algorithms*
provides the principal academic foundation for this document.

Complementary academic and mathematical references are documented in:

```text
docs/00-project/10-references.md
```

Additional references may be introduced later when recurrence solving,
probabilistic time analysis, or specialised computational models require
greater theoretical depth.
For this review the supplied **Third Edition (2009)** was consulted directly.
The [documentation review](documentation-review.md) records verified sections,
printed/PDF page locators, official Java SE 21 sources and attribution limits.
Fourth-edition page equivalence is not assumed.
