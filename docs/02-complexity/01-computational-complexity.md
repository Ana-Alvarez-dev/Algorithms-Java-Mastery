# Computational Complexity

## Algorithms Java Mastery

This document introduces **computational complexity** as the analytical study of
the computational resources required by algorithms.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic analysis of algorithms developed throughout this repository.

Algorithms are not evaluated solely by whether they produce the required
result.

Once correctness has been established, an additional question becomes
important:

> **How do the computational resources required by the algorithm grow as the
> input size increases?**

Computational complexity provides the mathematical framework used to answer that
question.

Rather than focusing on exact execution time on a particular machine,
complexity analysis studies resource growth independently of a specific
processor, operating system, programming language, or benchmark execution.

This distinction makes it possible to compare algorithms according to their
underlying computational behaviour.

---

# Purpose

The purpose of this document is to establish the conceptual foundations
required to analyse computational cost.

The learner should move from thinking about performance as:

```text
How many milliseconds did the program require?
```

toward thinking about:

```text
How does the required computation grow
as the problem size increases?
```

The general progression is:

```text
Algorithm
        ↓
Define Input Size
        ↓
Identify Computational Resources
        ↓
Model Resource Usage
        ↓
Analyse Growth
        ↓
Compare Algorithms
```

Computational complexity therefore provides a theoretical model of algorithmic
resource consumption rather than a direct measurement of wall-clock execution
time.

---

# Learning Objectives

After studying this document, the learner should be able to:

* define computational complexity;
* explain the relationship between correctness and efficiency;
* identify relevant computational resources;
* distinguish theoretical complexity from empirical performance;
* define an appropriate input-size parameter;
* recognise that some problems require multiple size parameters;
* explain why operation growth matters more than exact execution time in
  asymptotic analysis;
* identify significant operations in simple algorithms;
* relate resource growth to scalability;
* explain why complexity analysis precedes benchmarking;
* understand why algorithms solving the same problem may have different
  computational costs.

These competencies provide the analytical foundation for the remaining
documents in the **Complexity** module.

---

# What Is Computational Complexity?

**Computational complexity** studies the computational resources required to
solve problems as a function of problem size.

At the algorithmic level, the most commonly analysed resources are:

```text
Computational Resources
        │
        ├── Time
        │
        └── Space
```

Time analysis models the amount of computational work performed.

Space analysis models the amount of memory required.

The purpose is not necessarily to determine exact values.

Instead, the learner develops mathematical models describing how these resource
requirements change as the input grows.

Conceptually:

```text
Input Size
        ↓
Algorithm
        ↓
Resource Usage
        ↓
Function of Input Size
```

---

# Correctness and Complexity

Correctness and complexity describe different properties of an algorithm.

Correctness asks:

> **Does the algorithm satisfy the specification?**

Complexity asks:

> **How do the computational resources required by the algorithm grow?**

The repository therefore follows the progression:

```text
Problem Specification
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
```

An algorithm must first solve the intended computational problem.

Only then does it become meaningful to compare its resource requirements with
those of alternative correct algorithms.

---

# Correctness Does Not Imply Efficiency

Two algorithms may both satisfy the same specification while requiring very
different amounts of computation.

For example:

```text
Same Computational Problem
        │
        ├── Algorithm A
        │       ↓
        │   Correct Result
        │
        └── Algorithm B
                ↓
            Correct Result
```

Correctness alone does not tell us which algorithm scales more effectively.

Complexity analysis introduces the additional information required to compare
their growth behaviour.

---

# Efficiency Does Not Replace Correctness

The reverse is also true.

An implementation may appear fast while producing the wrong result.

Therefore:

```text
Fast
        ≠
Correct
```

and:

```text
Correct
        ≠
Efficient
```

Correctness and efficiency must be evaluated separately.

A complete engineering analysis requires both when efficiency is relevant.

---

# Computational Resources

Algorithms may consume several forms of computational resources.

The primary resources studied in this repository are:

* computational work;
* auxiliary memory.

These are commonly modelled through **time complexity** and **space
complexity**.

---

# Time as Computational Work

In theoretical algorithm analysis, time does not initially mean seconds or
milliseconds.

Instead, time is typically represented through a model of computational work.

For example, an analysis may count:

* comparisons;
* array accesses;
* assignments;
* arithmetic operations;
* recursive calls;
* data-structure operations.

Conceptually:

```text
Algorithm
        ↓
Relevant Operations
        ↓
Count as Function of Input Size
        ↓
Growth Behaviour
```

This abstraction allows the learner to reason about algorithmic cost without
depending on a particular machine.

---

# Space as Computational Memory

Algorithms may also require additional memory while executing.

Examples include:

* local variables;
* temporary arrays;
* stacks;
* queues;
* hash structures;
* recursion call stacks.

Conceptually:

```text
Algorithm
        ↓
Auxiliary Structures
        ↓
Memory Requirement
        ↓
Growth with Input Size
```

Space complexity is developed in greater detail later in this module.

---

# Input Size

Complexity analysis requires a definition of **problem size**.

The symbol:

```text
n
```

is frequently used, but its meaning depends on the computational problem.

For an array:

```text
n = number of elements
```

For a string:

```text
n = number of characters
```

For a graph, multiple parameters may be required:

```text
V = number of vertices
E = number of edges
```

For a matrix:

```text
r = number of rows
c = number of columns
```

Therefore:

> **The input-size parameter must be defined before a meaningful complexity
> statement can be made.**

---

# Input Size Is Problem-Dependent

A common beginner mistake is to treat `n` automatically as the size of every
problem.

That is not always appropriate.

Consider:

```text
Graph Algorithm
```

Its computational behaviour may depend on both:

```text
V
```

and:

```text
E
```

Likewise, an algorithm involving two independent collections may depend on:

```text
n = size of first collection
m = size of second collection
```

Complexity analysis should preserve these distinctions when they materially
affect the algorithm.

---

# Significant Operations

Not every low-level machine operation must be counted individually.

Instead, algorithm analysis often identifies one or more **significant
operations** that represent the principal work performed.

For example, in a searching algorithm, the significant operation may be:

```text
comparison with the target
```

In a sorting algorithm:

```text
element comparison
```

or:

```text
element movement
```

In a graph traversal:

```text
vertex or edge processing
```

The appropriate operation depends on the algorithm and the question being
analysed.

---

# Example — Linear Search

Consider a linear search through an array containing `n` elements.

A natural significant operation is:

```text
Compare current element with target
```

In a favourable input, the target may be found immediately.

In an unfavourable input, the algorithm may inspect every element.

Conceptually:

```text
Input Size = n
        ↓
At Most n Relevant Comparisons
        ↓
Linear Growth
```

This example illustrates how algorithm structure can be translated into a
resource-growth model.

Formal asymptotic classification is developed later in the module.

---

# Growth Rather Than Exact Time

Suppose an algorithm performs approximately:

```text
n
```

relevant operations.

Another performs approximately:

```text
n²
```

operations.

For small values of `n`, both may appear practical.

As `n` increases, however, their growth becomes increasingly different.

For example:

```text
n = 10

n   = 10
n²  = 100
```

```text
n = 1,000

n   = 1,000
n²  = 1,000,000
```

The important insight is not the exact number of nanoseconds required.

It is the **difference in growth behaviour**.

---

# Algorithm Growth

The cost of an algorithm can often be represented by a function:

```text
T(n)
```

where:

```text
n = input size
T(n) = computational work associated with input size n
```

For example:

```text
T(n) = n
```

or:

```text
T(n) = n² + n
```

or:

```text
T(n) = log n
```

Complexity analysis examines how such functions behave as `n` becomes large.

The next documents develop this reasoning in greater mathematical detail.

---

# Hardware Independence

A major advantage of theoretical complexity analysis is that it abstracts away
many machine-specific details.

Consider two computers:

```text
Computer A
Fast processor

Computer B
Slower processor
```

The same implementation may require different wall-clock times on each machine.

However, if the underlying algorithm requires work that grows proportionally to
`n`, that growth pattern remains conceptually the same.

Complexity analysis therefore supports comparisons at the **algorithmic level**
rather than the machine-specific execution level.

---

# Hardware Independence Is an Abstraction

The phrase **hardware-independent** should not be interpreted as meaning that
hardware never matters.

Real execution performance is influenced by:

* processor architecture;
* caches;
* memory latency;
* compiler behaviour;
* JVM optimisation;
* garbage collection;
* operating-system scheduling;
* data representation.

Complexity analysis deliberately abstracts many of these factors so that the
underlying growth of the algorithm can be studied.

Thus:

```text
Theoretical Complexity
        ↓
Abstracts Machine Details
```

while:

```text
Observed Performance
        ↓
Includes Machine and Runtime Effects
```

Both perspectives are useful for different purposes.

---

# Scalability

**Scalability** in algorithm analysis refers to how resource requirements change
as problem size increases.

Conceptually:

```text
Increasing Input Size
        ↓
Resource Growth
        ↓
Practical Consequences
```

An algorithm with slower growth in required resources may remain practical for
larger inputs than an alternative with faster growth.

However, scalability should not be reduced to a single complexity label.

Practical scalability may also depend on:

* constant factors;
* available memory;
* data distribution;
* implementation overhead;
* hardware;
* concurrency;
* external systems.

Complexity provides an essential analytical foundation, but real software
performance requires broader engineering interpretation.

---

# Complexity as a Function

A useful conceptual model is:

```text
Resource Requirement = f(Input Size)
```

For time:

```text
T(n)
```

For auxiliary space:

```text
S(n)
```

The goal is to understand the behaviour of these functions.

For example:

```text
T(n) = 3n + 2
```

describes different growth from:

```text
T(n) = n² + 4n + 1
```

Later asymptotic analysis focuses on the dominant behaviour of these functions.

---

# Exact Cost vs Growth Model

Complexity analysis may begin with a more detailed operation count.

For example:

```text
T(n) = 2n + 3
```

The exact constants may help explain where the cost originates.

Later, when studying asymptotic growth, the analysis may focus on the dominant
term.

Conceptually:

```text
Detailed Cost Model
        ↓
Growth Analysis
        ↓
Asymptotic Description
```

The repository therefore avoids skipping directly to a memorised Big O label
without first understanding the underlying cost structure.

---

# Complexity and Case Analysis

The cost of an algorithm may vary among different inputs having the same size.

Therefore, complexity analysis may consider:

* best case;
* average case;
* worst case.

For example, a search in an array of size `n` may require:

```text
1 comparison
```

for one input and:

```text
n comparisons
```

for another.

The input size is the same.

The input arrangement differs.

This distinction is developed formally in:

```text
05-best-average-worst-case.md
```

---

# Complexity and Asymptotic Analysis

As input size becomes large, complexity analysis increasingly focuses on the
growth of cost functions.

This leads to **asymptotic analysis**.

Conceptually:

```text
Cost Function
        ↓
Growth as Input Increases
        ↓
Asymptotic Behaviour
```

Asymptotic analysis provides the foundation for notation such as:

```text
O
Ω
Θ
o
ω
```

These concepts are developed in later documents.

---

# Complexity Is Not Big O

A common mistake is to use the terms:

```text
Complexity
```

and:

```text
Big O
```

as if they were equivalent.

They are not.

Computational complexity is the broader study of resource requirements.

Big O is one form of asymptotic notation used to describe an upper bound on
growth.

Conceptually:

```text
Computational Complexity
        ↓
Asymptotic Analysis
        ↓
Asymptotic Notations
        │
        ├── O
        ├── Ω
        ├── Θ
        ├── o
        └── ω
```

This distinction is essential for precise algorithm analysis.

---

# Time and Space Trade-Offs

Algorithms may improve one resource by consuming more of another.

For example:

```text
Additional Memory
        ↓
Avoid Repeated Computation
        ↓
Potentially Lower Time Cost
```

or:

```text
Minimal Auxiliary Memory
        ↓
Additional Recomputation
        ↓
Potentially Higher Time Cost
```

Therefore, algorithm efficiency should not always be reduced to a single
dimension.

The appropriate trade-off depends on the computational problem and its
constraints.

---

# Computational Complexity and Algorithm Comparison

Once cost models have been established, alternative algorithms can be compared
under equivalent assumptions.

Conceptually:

```text
Same Problem
        ↓
Algorithm A
        ↓
Cost Function A

Same Problem
        ↓
Algorithm B
        ↓
Cost Function B
```

The comparison should consider:

* the same problem specification;
* compatible input-size definitions;
* relevant resource dimensions;
* equivalent case assumptions.

Only then is the comparison meaningful.

---

# Example — Linear Search and Binary Search

Consider the problem:

> Determine whether a target exists in a collection.

A linear-search strategy may examine elements sequentially.

A binary-search strategy may repeatedly reduce the search interval.

However, binary search requires an important precondition:

```text
The input is ordered.
```

Therefore, a meaningful comparison must preserve the different assumptions.

Conceptually:

```text
Linear Search
        ↓
Sequential Reduction

Binary Search
        ↓
Halving Search Space
```

The resulting growth behaviour differs.

Later searching modules derive these complexities formally.

---

# Complexity and Preconditions

Complexity claims depend on the same contracts used in correctness reasoning.

For example:

```text
Binary Search
```

assumes ordered input.

Its complexity should therefore be interpreted under that condition.

Likewise, data-structure operations may have complexity claims that depend on:

* structural invariants;
* load factors;
* balance conditions;
* representation choices.

Complexity should never be detached from the assumptions under which the
algorithm operates.

---

# Complexity and Algorithm Design

Complexity analysis can influence algorithm design decisions.

The progression may be:

```text
Correct Candidate Algorithms
        ↓
Complexity Analysis
        ↓
Compare Resource Growth
        ↓
Select Appropriate Strategy
```

However, complexity is not the only criterion.

Engineering decisions may also consider:

* implementation simplicity;
* maintainability;
* memory constraints;
* input characteristics;
* stability requirements;
* readability;
* expected workload.

Complexity supports engineering judgement rather than replacing it.

---

# Theoretical Complexity and Java Implementation

The theoretical analysis belongs primarily to the algorithm.

Java provides an implementation of that algorithm.

Conceptually:

```text
Algorithm
        ↓
Theoretical Complexity
        ↓
Java Implementation
        ↓
Observed Runtime Behaviour
```

The Java implementation may introduce additional practical effects.

Examples include:

* object allocation;
* boxing;
* collection implementation;
* JVM warm-up;
* JIT compilation;
* garbage collection.

These factors matter when evaluating real implementation performance but should
not be confused with asymptotic algorithmic complexity.

---

# Complexity and Automated Testing

Automated testing answers behavioural questions.

For example:

> Does this implementation return the correct result for the selected input?

Complexity analysis answers analytical questions.

For example:

> How does the required computational work grow as input size increases?

Therefore:

```text
Testing
        ≠
Complexity Analysis
```

JUnit Jupiter and AssertJ validate implementation behaviour.

They do not establish asymptotic complexity.

---

# Complexity and Benchmarking

Complexity analysis and benchmarking provide different forms of evidence.

## Complexity Analysis

Examines:

```text
Algorithmic Structure
        ↓
Mathematical Resource Model
        ↓
Growth Behaviour
```

## Benchmarking

Examines:

```text
Concrete Implementation
        ↓
Defined Execution Environment
        ↓
Observed Measurements
```

The repository uses **JMH** when Java microbenchmarking is appropriate.

Benchmarking may help investigate:

* implementation constants;
* JVM behaviour;
* input distributions;
* practical crossover points;
* differences between implementation strategies.

However:

> **Observed timing results do not establish asymptotic complexity.**

---

# Benchmarking Is Not Always Required

Not every algorithm requires experimental benchmarking.

Benchmarking should be used when it provides meaningful educational or
engineering evidence.

For example, benchmarking may be valuable when:

* comparing multiple implementations;
* studying JVM behaviour;
* examining input-size effects;
* investigating implementation trade-offs.

It may be unnecessary when:

* the topic is primarily conceptual;
* no meaningful implementation comparison exists;
* theoretical analysis already answers the learning question.

Therefore, complexity analysis is fundamental.

Benchmarking is conditional.

---

# Computational Complexity Throughout the Repository

Complexity analysis becomes a recurring activity in later modules.

```text
Computational Complexity
        ↓
Arrays
        ↓
Searching
        ↓
Sorting
        ↓
Linked Structures
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

Each module may require different analytical models.

Examples include:

* linear analysis;
* nested-loop analysis;
* logarithmic reduction;
* recurrence relations;
* graph parameters;
* amortized analysis;
* auxiliary-space analysis.

The concepts introduced here provide the common foundation for those later
analyses.

---

# Standard Complexity Questions

When analysing an algorithm, the learner should progressively ask:

```text
What is the computational problem?
        ↓
What defines input size?
        ↓
Which resource is being analysed?
        ↓
Which operations represent the work?
        ↓
How many times can those operations occur?
        ↓
What function describes the cost?
        ↓
How does that function grow?
        ↓
Which asymptotic statement can be justified?
```

This sequence is more valuable than memorising complexity labels.

---

# Best Practices

When beginning a complexity analysis:

* establish correctness first;
* define the input-size parameter explicitly;
* identify the resource being analysed;
* identify meaningful operations;
* derive the cost from algorithm structure;
* preserve multiple parameters when required;
* state relevant assumptions;
* distinguish exact cost models from asymptotic simplification;
* distinguish case analysis from asymptotic notation;
* analyse time and auxiliary space separately;
* avoid interpreting theoretical complexity as measured runtime;
* compare algorithms under equivalent assumptions;
* use benchmarking only as complementary empirical evidence.

These practices encourage rigorous and reproducible algorithm analysis.

---

# Common Mistakes

## Treating Execution Time as Complexity

Incorrect:

```text
The algorithm took 20 ms,
therefore its complexity is 20 ms.
```

Execution time is an empirical measurement.

Complexity describes resource growth.

---

## Assuming `n` Always Means Number of Elements

The meaning of input size depends on the problem.

It must be defined explicitly.

---

## Ignoring Multiple Input Parameters

A graph may depend on both `V` and `E`.

Reducing every problem to one variable may hide important structure.

---

## Counting Everything Mechanically

The purpose of analysis is to model relevant computational work.

Not every machine-level operation needs to be counted individually.

---

## Jumping Directly to Big O

A complexity label without reasoning provides little evidence of understanding.

The learner should first identify the algorithmic structure and derive the
growth model.

---

## Confusing Big O with Exact Complexity

Big O is an asymptotic upper-bound notation.

It is not a synonym for every complexity statement.

---

## Confusing Worst Case with Big O

Worst-case analysis and Big O describe different concepts.

Worst case identifies a cost function over a class of inputs.

Big O describes an asymptotic upper bound on a function.

---

## Ignoring Space

Time efficiency may be improved by additional memory use.

Relevant analyses should consider both resources.

---

## Treating Benchmarking as Proof

Benchmark observations cannot prove an asymptotic complexity class.

---

# Key Takeaways

After completing this document, the learner should understand that:

* computational complexity studies the resources required by algorithms;
* correctness and efficiency are separate properties;
* complexity analysis begins only after the computational problem and algorithm
  are sufficiently understood;
* input size must be defined explicitly;
* not every problem uses a single parameter `n`;
* significant operations provide a useful model of computational work;
* complexity analyses growth rather than exact wall-clock execution time;
* time and auxiliary space are distinct computational resources;
* complexity provides an abstract model that intentionally ignores many
  machine-specific effects;
* scalability is related to resource growth but also requires engineering
  interpretation;
* computational complexity is broader than Big O notation;
* theoretical analysis and experimental benchmarking provide different forms
  of evidence;
* benchmarking complements complexity analysis but does not replace it.

Computational complexity therefore establishes the mathematical foundation for
reasoning about algorithm efficiency.

It provides the conceptual bridge between **correctness**, **algorithm growth**,
**asymptotic analysis**, **resource trade-offs**, and later empirical evaluation
throughout **Algorithms Java Mastery**.

---

# Next Document

```text
02-algorithm-growth.md
```

The next document examines how different computational cost functions grow as
input size increases.

It introduces common growth behaviours and develops the intuition required to
compare functions such as:

```text
1
log n
n
n log n
n²
n³
2ⁿ
n!
```

The next central question becomes:

> **How quickly do different resource functions grow as the problem size
> increases?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The analysis of computational resources, input size, running-time models, and
algorithm efficiency throughout *Introduction to Algorithms* provides the
principal academic foundation for this document.

Complementary academic and technical references are documented in:

```text
docs/00-project/10-references.md
```

Additional specialised references may be introduced later when asymptotic
analysis, amortized analysis, recurrence relations, or experimental performance
evaluation require further treatment.
