# Complexity Analysis Examples

## Algorithms Java Mastery

This document consolidates the concepts introduced throughout the
**Complexity** module by applying them to representative algorithms and data
structures.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic analysis of algorithms throughout this repository.

Understanding complexity theory is only the first step.

The learner must also be able to apply the analytical framework to concrete
algorithms by identifying:

* the computational problem;
* the input-size parameter;
* the relevant operations;
* the time-cost function;
* auxiliary-space requirements;
* relevant case distinctions;
* amortized behaviour when applicable;
* engineering trade-offs.

The central question addressed throughout this document is:

> **How can the complexity-analysis methodology be applied systematically to
> concrete algorithms and data structures?**

---

# Purpose

The purpose of this document is to demonstrate how the concepts introduced
throughout the **Complexity** module work together in practical analysis.

Rather than attaching isolated labels such as:

```text
O(n)
```

or:

```text
O(n²)
```

to algorithms, each example derives its conclusions from the structure of the
problem and the algorithm.

The general progression is:

```text
Computational Problem
        ↓
Define Input Size
        ↓
Describe Algorithmic Strategy
        ↓
Identify Significant Operations
        ↓
Derive Cost Function
        ↓
Analyse Relevant Cases
        ↓
Establish Time Bounds
        ↓
Analyse Auxiliary Space
        ↓
Consider Amortized Behaviour When Relevant
        ↓
Engineering Interpretation
```

This methodology becomes the standard analytical model used throughout later
repository modules.

---

# Learning Objectives

After studying this document, the learner should be able to:

* apply the complexity-analysis workflow to representative algorithms;
* define the relevant input-size parameter;
* identify significant operations;
* derive simple cost functions;
* distinguish best-, average-, and worst-case analysis when relevant;
* distinguish case analysis from asymptotic notation;
* analyse auxiliary-space requirements;
* recognise when amortized analysis is appropriate;
* compare alternative algorithms under explicit assumptions;
* interpret complexity results from an engineering perspective;
* avoid reducing algorithm analysis to memorised complexity labels.

These competencies prepare the learner for the dedicated implementation modules
that follow.

---

# Standard Analysis Template

A useful analysis template is:

```text
1. Computational Problem
        ↓
2. Input Size
        ↓
3. Preconditions
        ↓
4. Algorithmic Strategy
        ↓
5. Significant Operation
        ↓
6. Cost Function
        ↓
7. Relevant Case Analysis
        ↓
8. Asymptotic Time Bound
        ↓
9. Auxiliary-Space Analysis
        ↓
10. Amortized Analysis When Relevant
        ↓
11. Engineering Interpretation
```

Not every algorithm requires every section.

For example:

* some algorithms have no meaningful distinction among all three cases;
* some operations require amortized analysis;
* some problems require multiple input-size parameters.

The methodology should remain rigorous without becoming mechanical.

---

# Example 1 — Linear Search

## Computational Problem

Determine whether a target value exists in an unsorted array.

---

## Input Size

Let:

```text
n = number of elements in the array
```

---

## Preconditions

A possible contract may require:

```text
The array is valid.
```

No ordering assumption is required.

---

## Strategy

Inspect elements sequentially until:

* the target is found; or
* the end of the array is reached.

Conceptually:

```text
Start
        ↓
Compare Current Element
        ↓
Target Found?
        │
        ├── Yes → Return
        │
        └── No → Continue
```

---

## Significant Operation

A natural significant operation is:

```text
Compare current element with target.
```

---

## Best-Case Time

If the target appears in the first position:

```text
1 comparison
```

Therefore:

```text
T_best(n) ∈ Θ(1)
```

---

## Worst-Case Time

If the target appears in the final position or is absent:

```text
n comparisons
```

Therefore:

```text
T_worst(n) ∈ Θ(n)
```

---

## Average-Case Time

Average-case analysis requires a probability model.

Assume:

* the target is present; and
* each position is equally likely to contain it.

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

Therefore:

```text
T_average(n) ∈ Θ(n)
```

under that probability model.

---

## Auxiliary Space

The algorithm requires only a fixed number of additional variables.

Therefore:

```text
S_aux(n) ∈ Θ(1)
```

---

## Engineering Interpretation

Linear search requires no ordering precondition and is simple to implement.

Its worst-case work grows linearly with input size.

It may therefore be appropriate when:

* the collection is small;
* the input is unsorted;
* preprocessing would not be worthwhile;
* only a small number of searches are required.

The complexity result should be interpreted together with the problem
constraints rather than as an isolated scalability label.

---

# Example 2 — Binary Search

## Computational Problem

Determine whether a target value exists in an ordered array.

---

## Input Size

```text
n = number of elements in the array
```

---

## Preconditions

Binary search requires:

```text
The input array is ordered according to the same comparison relation used by
the search.
```

This precondition is fundamental.

An unsorted array is not a worst-case binary-search input.

It lies outside the declared contract.

---

## Strategy

Compare the target with the middle value.

Then eliminate approximately half of the remaining search interval.

Conceptually:

```text
Search Interval of Size n
        ↓
Compare Middle
        ↓
Remaining Interval ≈ n / 2
        ↓
Repeat
```

---

## Significant Operation

```text
Comparison between target and middle element.
```

---

## Best-Case Time

If the target is found at the first middle position:

```text
T_best(n) ∈ Θ(1)
```

---

## Worst-Case Time

The active interval is repeatedly halved:

```text
n
n / 2
n / 4
n / 8
...
```

The number of reductions is logarithmic.

Therefore:

```text
T_worst(n) ∈ Θ(log n)
```

---

## Average-Case Time

A rigorous average-case statement requires a probability model.

Under common search-distribution assumptions, the expected search depth also
has logarithmic growth.

However, the probability assumptions should be documented rather than simply
reporting an unexplained “average complexity”.

---

## Auxiliary Space — Iterative Version

An iterative implementation may require only variables such as:

```text
left
right
middle
```

Therefore:

```text
S_aux(n) ∈ Θ(1)
```

---

## Auxiliary Space — Recursive Version

A recursive implementation produces a call-stack depth proportional to the
number of search-space reductions.

Therefore:

```text
S_aux(n) ∈ Θ(log n)
```

under the standard recursive implementation model.

---

## Engineering Interpretation

Binary search offers substantially slower growth than linear search for large
ordered inputs.

However, the comparison is not complete without acknowledging its precondition.

If the collection must first be sorted for a single search, the cost of sorting
may dominate the search itself.

Therefore, binary search is particularly attractive when:

* data is already ordered; or
* many searches justify maintaining ordered data.

---

# Linear Search vs Binary Search

The two algorithms should be compared under explicit assumptions.

| Property                  | Linear Search | Binary Search |
| ------------------------- | ------------- | ------------- |
| Ordering required         | No            | Yes           |
| Best-case time            | `Θ(1)`        | `Θ(1)`        |
| Worst-case time           | `Θ(n)`        | `Θ(log n)`    |
| Iterative auxiliary space | `Θ(1)`        | `Θ(1)`        |

The table does not imply that binary search is universally superior.

Its ordering requirement changes the engineering context.

---

# Example 3 — Bubble Sort

## Computational Problem

Rearrange an array into the required order.

---

## Input Size

```text
n = number of elements
```

---

## Strategy

Repeatedly compare adjacent elements and exchange them when they appear in the
wrong order.

A common optimised implementation terminates early if a complete pass performs
no swaps.

---

## Significant Operations

Relevant operations may include:

```text
Adjacent comparisons
```

and:

```text
Swaps
```

The exact analysis should identify which operation is being counted.

---

## Worst-Case Time

For an unfavourable ordering, the algorithm performs a number of comparisons
proportional to:

```text
(n - 1) + (n - 2) + ... + 1
```

which equals:

```text
n(n - 1) / 2
```

Therefore:

```text
T_worst(n) ∈ Θ(n²)
```

---

## Average-Case Time

Under common input-distribution assumptions, the number of relevant operations
also grows quadratically.

Therefore:

```text
T_average(n) ∈ Θ(n²)
```

under the assumed model.

---

## Best-Case Time

The best-case result depends on the implementation.

### Optimised Version

If the algorithm detects that no swaps occurred during a pass, an already
ordered array may require one complete traversal.

Therefore:

```text
T_best(n) ∈ Θ(n)
```

### Non-Optimised Version

A version that always performs all passes may still require:

```text
Θ(n²)
```

work even for ordered input.

Therefore:

> **Complexity belongs to a defined algorithm and implementation strategy, not
> merely to the algorithm name.**

---

## Auxiliary Space

A typical in-place implementation uses a fixed number of additional variables.

Therefore:

```text
S_aux(n) ∈ Θ(1)
```

---

## Engineering Interpretation

Bubble sort is useful for studying:

* nested-loop analysis;
* case-dependent behaviour;
* swaps;
* early termination.

Its quadratic growth generally makes it inappropriate for large collections
when more efficient sorting algorithms are available.

Its principal value in this repository is educational rather than as a default
production sorting strategy.

---

# Example 4 — Merge Sort

## Computational Problem

Sort a sequence by recursively decomposing it and merging ordered subproblems.

---

## Input Size

```text
n = number of elements
```

---

## Strategy

Conceptually:

```text
Sequence of Size n
        ↓
Divide into Two Subproblems
        ↓
Sort Each Subproblem
        ↓
Merge Ordered Results
```

---

## Time Recurrence

A typical recurrence is:

```text
T(n) = 2T(n / 2) + Θ(n)
```

where:

```text
2T(n / 2)
```

represents the two recursive sorting subproblems and:

```text
Θ(n)
```

represents merging work at the current level.

---

## Time Complexity

The recurrence yields:

```text
T(n) ∈ Θ(n log n)
```

for the standard merge-sort model.

The same asymptotic growth generally applies across the usual best, average,
and worst input arrangements because the recursive decomposition and merging
structure remain fundamentally similar.

Therefore:

```text
T_best(n)    ∈ Θ(n log n)
T_average(n) ∈ Θ(n log n)
T_worst(n)   ∈ Θ(n log n)
```

under the standard model.

---

## Auxiliary Space

A common array-based merge-sort implementation uses temporary storage
proportional to the number of elements.

Therefore:

```text
S_aux(n) ∈ Θ(n)
```

The recursion stack additionally contributes logarithmic depth, but the linear
temporary storage dominates:

```text
Θ(n) + Θ(log n)
        ↓
Θ(n)
```

---

## Engineering Interpretation

Merge sort provides predictable `Θ(n log n)` time growth.

Its principal trade-off in an array-based implementation is additional memory.

It demonstrates an important engineering relationship:

```text
Predictable Time Growth
        ↔
Additional Auxiliary Storage
```

---

# Example 5 — Dynamic Array Append

## Computational Problem

Append elements to a dynamically resizable array.

This example differs from the previous algorithms because the analytical object
is a **sequence of operations**.

---

## Parameters

Let:

```text
m = number of append operations
```

The current structure size may vary during the sequence.

---

## Strategy

When capacity remains available:

```text
Store New Element
```

When capacity is exhausted:

```text
Allocate Larger Storage
        ↓
Copy Existing Elements
        ↓
Append New Element
```

For theoretical illustration, assume a geometric growth policy such as
doubling.

---

## Individual Operation Cost

An append that does not trigger resizing may require:

```text
Θ(1)
```

work.

An append that triggers reallocation and copying may require:

```text
Θ(n)
```

work relative to the current structure size.

Therefore, it would be incorrect to claim that every append has constant actual
cost.

---

## Amortized Time

Across a sequence of `m` appends under geometric capacity growth, the total
number of copied elements remains proportional to `m`.

Thus:

```text
Total Cost of m Appends
        ∈ Θ(m)
```

and:

```text
Amortized Append Cost
        ∈ Θ(1)
```

---

## Auxiliary Space

Space analysis requires greater care than writing simply:

```text
O(1)
```

A resizable array maintains backing storage proportional to its capacity.

Relative to logical size:

```text
Allocated Capacity ∈ Θ(n)
```

under a geometric growth policy.

During resizing, both the old and new backing arrays may temporarily coexist.

The peak concrete memory may therefore involve a larger constant multiple of
`n`, while preserving linear growth.

Thus the structure's storage requirement remains:

```text
Θ(n)
```

with respect to its stored elements.

The **additional working space of an ordinary non-resizing append** may be
constant, but that should not be confused with the memory required by the
dynamic array itself.

---

## Engineering Interpretation

Dynamic arrays demonstrate why several analytical perspectives may be required
for one operation:

```text
Ordinary Append
        ↓
Low Individual Cost

Resize-Triggering Append
        ↓
Higher Individual Cost

Long Sequence
        ↓
Constant Amortized Cost
```

The favourable amortized time relies partly on maintaining additional capacity.

This creates a time-space trade-off.

---

# Java `ArrayList` and the Theoretical Model

Java's `ArrayList` is a practical example of a resizable array abstraction.

However, the complexity analysis in this document should remain independent of
version-specific Java implementation details.

Conceptually:

```text
Resizable Backing Array
        ↓
Occasional Capacity Growth
        ↓
Element Copying
        ↓
Amortized Append Behaviour
```

The exact capacity-growth policy belongs to the library implementation.

The theoretical lesson is the relationship between geometric growth and
amortized sequence cost.

---

# Example 6 — Pairwise Processing

Consider an algorithm that processes every unordered pair of elements.

## Input Size

```text
n = number of elements
```

---

## Number of Pairs

The number of unordered pairs is:

```text
n(n - 1) / 2
```

Therefore, assuming constant work per pair:

```text
T(n)
        =
cn(n - 1) / 2
```

for some constant `c`.

The dominant term is quadratic.

Thus:

```text
T(n) ∈ Θ(n²)
```

---

## Auxiliary Space

If the algorithm processes one pair at a time using only fixed temporary
variables:

```text
S_aux(n) ∈ Θ(1)
```

---

## Engineering Interpretation

This example demonstrates that quadratic growth should be derived from the
number of relevant combinations rather than inferred mechanically from the
presence of two loops.

---

# Example 7 — Graph Traversal Perspective

A graph provides an example where one input parameter is insufficient.

Let:

```text
V = number of vertices
E = number of edges
```

For traversal algorithms operating on adjacency-list representations, the work
may depend on both quantities.

Conceptually:

```text
Process Vertices
        +
Process Edges
```

leading to an expression such as:

```text
Θ(V + E)
```

under the relevant graph representation and traversal model.

This demonstrates why complexity expressions should preserve independent
problem dimensions.

The detailed analysis belongs to the graph module.

---

# Comparing the Examples

A concise comparison is:

| Example               | Relevant Time Result            | Auxiliary Space          | Important Condition           |
| --------------------- | ------------------------------- | ------------------------ | ----------------------------- |
| Linear Search         | Best `Θ(1)`, Worst `Θ(n)`       | `Θ(1)`                   | No ordering required          |
| Binary Search         | Best `Θ(1)`, Worst `Θ(log n)`   | Iterative `Θ(1)`         | Ordered input                 |
| Bubble Sort           | Best `Θ(n)`*; Avg/Worst `Θ(n²)` | `Θ(1)`                   | `*` Optimised version         |
| Merge Sort            | `Θ(n log n)`                    | `Θ(n)`                   | Standard array implementation |
| Dynamic Array Append  | Amortized `Θ(1)`                | Structure storage `Θ(n)` | Geometric capacity growth     |
| Pairwise Processing   | `Θ(n²)`                         | Often `Θ(1)`             | Constant work per pair        |
| Graph Traversal Model | `Θ(V + E)`                      | Representation-dependent | Adjacency-list model          |

The table summarises results.

It does not replace the derivations.

---

# Why Tight Bounds Are Preferred Here

Where the analysis establishes a tight asymptotic growth rate, this document
uses:

```text
Θ(...)
```

rather than reporting only:

```text
O(...)
```

For example:

```text
Linear Search Worst Case
        ↓
Θ(n)
```

is more informative than:

```text
O(n)
```

because both an asymptotic upper and lower bound are known.

This follows the notation principles established earlier in the module.

---

# Case Labels and Asymptotic Bounds

The examples reinforce the distinction:

```text
Best / Average / Worst
        ↓
Identify Cost Functions
```

while:

```text
O / Ω / Θ
        ↓
Describe Mathematical Bounds
```

Therefore:

```text
Best Case ≠ Ω
Worst Case ≠ O
```

Each relevant case may receive its own tight or non-tight asymptotic bound.

---

# Time and Space Must Be Named Explicitly

A complexity statement such as:

```text
Θ(n)
```

is incomplete unless the context establishes what resource is being analysed.

A more precise analysis identifies:

```text
Worst-case time: Θ(n)
```

or:

```text
Auxiliary space: Θ(n)
```

or:

```text
Amortized append time: Θ(1)
```

Precision becomes increasingly important as algorithms acquire multiple
relevant resource dimensions.

---

# Not Every Algorithm Needs Every Case

The standard template should not force meaningless distinctions.

For example, merge sort has the same standard asymptotic time growth across the
usual input arrangements.

In such situations, repeating three nearly identical sections may add little
educational value.

Likewise, some algorithms may require:

* expected analysis;
* amortized analysis;
* multi-parameter analysis;

instead of a conventional best/average/worst structure.

The analysis should follow the algorithm, not a rigid form.

---

# Algorithm vs Implementation Complexity

Some complexity results belong primarily to the abstract algorithm.

Others depend on implementation strategy.

Binary search demonstrates this distinction.

The time strategy may remain logarithmic for both iterative and recursive
versions.

But auxiliary space differs:

```text
Iterative
        ↓
Θ(1)

Recursive
        ↓
Θ(log n)
```

Therefore:

> **Complexity documentation should identify when a result is
> implementation-dependent.**

---

# Engineering Interpretation

Complexity analysis supports engineering decisions, but it does not make those
decisions automatically.

Relevant considerations may include:

* input size;
* input distribution;
* preconditions;
* memory limits;
* latency requirements;
* implementation complexity;
* mutation requirements;
* stability;
* expected workload.

For example:

```text
Binary Search
        ↓
Better Search Growth
```

does not automatically imply:

```text
Always Use Binary Search
```

if maintaining sorted data introduces greater overall cost.

---

# Complexity and Preconditions

Complexity claims should remain attached to the contract under which the
algorithm operates.

Examples:

```text
Binary Search
        ↓
Requires Ordered Input
```

```text
Dynamic Array Amortized Append
        ↓
Depends on Capacity-Growth Strategy
```

```text
Hash-Table Expected Cost
        ↓
Depends on Hashing Assumptions
```

Removing these assumptions from the complexity statement may produce misleading
engineering conclusions.

---

# Complexity and Correctness

Complexity analysis assumes that the algorithm being analysed solves the
specified problem correctly.

The progression remains:

```text
Specification
        ↓
Correctness
        ↓
Complexity Analysis
        ↓
Implementation
        ↓
Testing
        ↓
Benchmarking When Appropriate
```

An incorrect algorithm with excellent asymptotic growth remains an incorrect
solution.

---

# Complexity and Benchmarking

The examples in this document are theoretical analyses.

Benchmarking later evaluates concrete Java implementations.

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
Observed Performance
```

A benchmark may reveal:

* constant-factor differences;
* crossover points;
* JVM behaviour;
* implementation overhead.

It cannot establish a formal complexity class by itself.

---

# Complexity and Automated Testing

Testing and complexity analysis provide different evidence.

```text
Automated Testing
        ↓
Behavioural Evidence
```

```text
Complexity Analysis
        ↓
Resource-Growth Evidence
```

A fast algorithm can still be incorrect.

A correct implementation can still scale poorly.

Both forms of analysis remain necessary when relevant.

---

# Standard Complexity Questions

For any future algorithm, ask:

```text
What problem is being solved?
        ↓
What defines input size?
        ↓
Which assumptions apply?
        ↓
Which operation represents the work?
        ↓
How many times can it execute?
        ↓
Do costs vary by input?
        ↓
What is the time-cost function?
        ↓
What auxiliary memory is required?
        ↓
Is amortized analysis relevant?
        ↓
Which asymptotic statement is justified?
        ↓
What engineering trade-offs follow?
```

This sequence is more important than memorising a table of complexity values.

---

# Common Mistakes

## Reporting Only `O(...)`

An upper bound may be mathematically correct while less informative than a known
tight `Θ(...)` bound.

---

## Treating Best Case as Big Omega

Case analysis and asymptotic notation are separate.

---

## Treating Worst Case as Big O

Worst case identifies the maximum cost function.

Big O describes an upper asymptotic bound.

---

## Reporting an Average Case Without Assumptions

Average-case analysis requires a probability model.

---

## Assuming Bubble Sort Always Has Linear Best Case

Linear best-case behaviour requires an early-termination optimisation.

---

## Saying Binary Search Is Always Better Than Linear Search

Binary search requires ordered data.

Its precondition changes the complete engineering problem.

---

## Treating `ArrayList.add` as Always Constant

A resize-triggering operation may require linear work.

Its long-sequence append behaviour can nevertheless be amortized constant.

---

## Reporting Dynamic Array Space as `O(1)`

The backing storage grows with the number of stored elements.

The memory model being reported must be identified carefully.

---

## Ignoring Recursion Stack Space

Recursive and iterative implementations may have identical time growth but
different auxiliary-space requirements.

---

## Treating Nested Loops as Proof of Quadratic Growth

The actual iteration counts must be derived.

---

## Ignoring Multiple Input Parameters

Graph algorithms frequently require expressions involving both `V` and `E`.

---

## Treating Benchmark Results as Complexity Proof

Experimental measurements and asymptotic analysis provide different forms of
evidence.

---

# Best Practices

When analysing concrete algorithms:

* define the computational problem first;
* define all input-size parameters;
* document relevant preconditions;
* describe the algorithmic strategy;
* identify significant operations;
* derive the cost rather than memorising the label;
* analyse only cases that provide meaningful information;
* document probability assumptions for average-case analysis;
* use tight bounds when justified;
* distinguish total space from auxiliary space;
* include recursion-stack effects;
* use amortized analysis for operation sequences when appropriate;
* preserve multiple parameters when necessary;
* distinguish algorithmic results from implementation-dependent results;
* interpret complexity within the complete engineering context.

The objective is to produce an analysis that can be explained and defended.

---

# Reusable Complexity Analysis Template

Future algorithm documents may use the following structure when appropriate:

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

Sections that do not contribute meaningful information may be omitted or
adapted.

---

# Relationship with Later Modules

The methodology demonstrated here becomes part of every subsequent algorithm
module.

Conceptually:

```text
Complexity Foundations
        ↓
Arrays
        ↓
Searching
        ↓
Sorting
        ↓
Linked Structures
        ↓
Stacks and Queues
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

Later modules deepen the analysis according to their own computational
structures.

Examples include:

* operation-specific array costs;
* search-space reduction;
* sorting recurrences;
* hash-table expected and amortized behaviour;
* tree-height-dependent operations;
* graph complexity with `V` and `E`;
* recurrence relations;
* dynamic-programming state and transition counts.

---

# Module Integration

This document integrates the principal concepts studied throughout
`02-complexity`.

```text
Computational Complexity
        ↓
Algorithm Growth
        ↓
Asymptotic Analysis
        ↓
Asymptotic Notations
        ↓
Case Analysis
        ↓
Time Complexity
        ↓
Space Complexity
        ↓
Amortized Analysis
        ↓
Complexity Analysis Examples
```

The module has therefore progressed from:

```text
What does computational cost mean?
```

toward:

```text
How can I derive and justify the computational cost
of a concrete algorithm?
```

This transition represents the principal applied outcome of the module.

---

# Key Takeaways

After completing this document, the learner should understand that:

* complexity analysis should be derived from the structure of the algorithm;
* input size must be defined explicitly;
* significant operations provide the bridge to cost functions;
* time and auxiliary space are separate resource dimensions;
* best-, average-, and worst-case analysis should be used only when meaningful;
* average-case claims require probability assumptions;
* asymptotic notation and case analysis describe different concepts;
* tight `Θ` bounds are more informative than loose upper bounds when known;
* implementation strategy may change auxiliary-space complexity;
* amortized analysis is required for some operation sequences;
* multi-parameter problems should preserve independent variables;
* complexity conclusions depend on preconditions and analytical assumptions;
* engineering interpretation requires more than a single complexity label;
* benchmarking complements theoretical complexity but cannot replace its
  derivation.

Complexity analysis therefore becomes a repeatable reasoning process rather than
a collection of memorised symbols.

The learner should now be able to move from a concrete algorithm to a justified
analysis of its **computational work**, **memory requirements**, **relevant
execution cases**, and **engineering trade-offs**.

---

# Next Document

```text
10-complexity-cheat-sheet.md
```

The final document of the **Complexity** module consolidates the principal
definitions, growth families, asymptotic notations, analytical rules, and
common patterns into a concise review reference.

Its purpose is not to replace the reasoning developed throughout the module.

Instead, it provides a compact tool for recalling the analytical framework
before studying and implementing algorithms in later modules.

The next central question becomes:

> **Which complexity concepts, formulas, distinctions, and analysis patterns
> should be immediately available when analysing a new algorithm?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The algorithm-analysis methodology demonstrated throughout these examples draws
on the treatment of running time, asymptotic notation, case analysis,
recurrences, sorting, searching, and amortized analysis developed throughout
*Introduction to Algorithms*.

Complementary academic, mathematical, Java, and engineering references are
documented in:

```text
docs/00-project/10-references.md
```

Later algorithm modules may introduce additional specialised references when
their analysis requires deeper treatment of a particular data structure,
algorithmic paradigm, or computational model.

```
```
