# Amortized Analysis

## Algorithms Java Mastery

This document introduces **amortized analysis** as a technique for analysing the
cost of a sequence of operations when some individual operations may be much
more expensive than others.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic analysis of algorithms throughout this repository.

The previous documents introduced computational complexity, growth,
asymptotic analysis, asymptotic notation, case analysis, time complexity, and
space complexity.

Amortized analysis adds another important perspective:

> **A sequence of operations may have a low overall cost even when some
> individual operations are expensive.**

The central question addressed throughout this document is:

> **How can a sequence of operations remain efficient when some individual
> operations require substantially more work than others?**

---

# Purpose

The purpose of this document is to establish amortized analysis as a method for
reasoning about the total cost of a sequence of operations.

Instead of analysing one operation in isolation, amortized analysis considers a
sequence such as:

```text
Operation 1
        ↓
Operation 2
        ↓
Operation 3
        ↓
...
        ↓
Operation m
```

and asks how the total cost is distributed across that sequence.

The conceptual progression is:

```text
Sequence of Operations
        ↓
Individual Costs
        ↓
Total Sequence Cost
        ↓
Distribute Cost Across Operations
        ↓
Amortized Cost
```

The result is a deterministic guarantee about the cost of the sequence under
the adopted analytical model.

---

# Learning Objectives

After studying this document, the learner should be able to:

* define amortized analysis;
* distinguish amortized cost from the cost of an individual operation;
* distinguish amortized analysis from average-case analysis;
* explain why amortized analysis does not require a probability distribution;
* identify sequences containing occasional expensive operations;
* derive simple amortized costs from total sequence cost;
* understand dynamic-array resizing as a classical example;
* recognise the aggregate, accounting, and potential methods conceptually;
* distinguish amortized guarantees from empirical averages;
* apply asymptotic notation to amortized cost;
* recognise where amortized reasoning appears in later data structures.

These competencies extend algorithm analysis from isolated operations to
operation sequences.

---

# What Is Amortized Analysis?

**Amortized analysis** studies the total cost of a sequence of operations and
distributes that cost across the sequence.

Suppose:

```text
m operations
```

have total cost:

```text
T(m)
```

A basic amortized-cost perspective is:

```text
Amortized Cost per Operation
        ≈
T(m) / m
```

when the aggregate cost can be bounded appropriately.

The purpose is not to claim that every individual operation has this cost.

Instead, the result describes the cost that can be guaranteed when considering
the complete sequence.

---

# Individual Cost vs Amortized Cost

These concepts should remain distinct.

An individual operation may cost:

```text
Θ(1)
```

while another operation in the same sequence may cost:

```text
Θ(n)
```

Yet the amortized cost across a sufficiently structured sequence may still be:

```text
Θ(1)
```

per operation.

Conceptually:

```text
Cheap
Cheap
Cheap
Expensive
Cheap
Cheap
        ↓
Total Cost Remains Controlled
        ↓
Low Amortized Cost
```

Therefore:

> **Amortized cost does not mean that every operation is equally inexpensive.**

---

# Why Amortized Analysis Is Necessary

Some data structures occasionally perform structural work that is much more
expensive than their ordinary operations.

Examples may include:

* resizing an array;
* rebuilding a table;
* moving many elements;
* reorganising internal state.

If only the most expensive individual operation is observed, the resulting
description may hide the behaviour of long sequences.

Amortized analysis asks instead:

> **How frequently can expensive operations occur, and how large can the total
> cost become across the complete sequence?**

---

# A Deterministic Sequence Guarantee

One of the most important characteristics of amortized analysis is that it does
not require randomness.

The analysis may establish that **for every valid sequence** of a particular
form:

```text
Total Cost ≤ Bound
```

and therefore:

```text
Amortized Cost ≤ Bound / Number of Operations
```

This distinguishes amortized analysis from probabilistic expected-cost
analysis.

---

# Amortized Analysis vs Average-Case Analysis

Amortized analysis and average-case analysis are fundamentally different.

## Average-Case Analysis

Average-case analysis requires a probability model.

Conceptually:

```text
Possible Inputs
        ↓
Probability Distribution
        ↓
Expected Cost
```

The result depends on assumptions about how inputs occur.

---

## Amortized Analysis

Amortized analysis studies a sequence directly.

Conceptually:

```text
Operation Sequence
        ↓
Actual or Bounded Costs
        ↓
Total Cost
        ↓
Amortized Cost
```

No input probability distribution is required.

Therefore:

```text
Average Case
        ≠
Amortized Analysis
```

---

# Amortized Cost Is Not an Empirical Average

The word **amortized** can be misunderstood as meaning:

```text
measure many executions
        ↓
calculate arithmetic mean
```

That is not the theoretical technique being studied.

Amortized analysis is mathematical.

It derives a bound on sequence cost from the structure of the operations.

Therefore:

```text
Amortized Analysis
        ≠
Benchmark Average
```

---

# Example — Dynamic Array Growth

A dynamic array provides a classical example.

Suppose the structure has some internal capacity.

Ordinary insertion may require only:

```text
Store new element
```

which is constant work.

However, when capacity is exhausted, the structure may need to:

```text
Allocate Larger Storage
        ↓
Copy Existing Elements
        ↓
Insert New Element
```

The resize operation is much more expensive.

---

# Simplified Dynamic Array Model

Consider a simplified dynamic array whose capacity doubles whenever it becomes
full.

Conceptually:

```text
Capacity 1
        ↓
Capacity 2
        ↓
Capacity 4
        ↓
Capacity 8
        ↓
Capacity 16
        ↓
...
```

Most insertions do not resize the structure.

Only insertions occurring at capacity boundaries trigger copying.

---

# Cost of Individual Insertions

Suppose inserting into available capacity requires:

```text
1 unit of work
```

A resize from capacity `k` may require approximately:

```text
k copy operations
```

plus the insertion itself.

Therefore, some operations may have costs such as:

```text
1
1
2
1
4
1
1
1
8
...
```

The expensive operations become larger, but they also become less frequent.

---

# Total Cost of Dynamic Array Insertions

Suppose `n` elements are appended while capacity doubles.

The total number of copied elements across resizing operations is approximately:

```text
1 + 2 + 4 + 8 + ... + previous capacity
```

This is a geometric series.

If the final capacity is proportional to `n`, then:

```text
1 + 2 + 4 + ... < 2n
```

up to constant factors.

The `n` ordinary insertions also contribute linear work.

Therefore:

```text
Total Cost of n Appends
        ∈ Θ(n)
```

under this simplified model.

Dividing the total cost across `n` operations gives:

```text
Amortized Cost per Append
        ∈ Θ(1)
```

---

# What the Result Means

The statement:

```text
Append has Θ(1) amortized cost
```

does **not** mean:

```text
Every append requires Θ(1) actual work.
```

A resize may still require:

```text
Θ(n)
```

work for that particular operation.

The amortized statement means that across a sequence of appends:

```text
Total cost grows linearly with number of appends
```

and therefore the cost per operation is constant on average **in the amortized,
non-probabilistic sense**.

---

# Amortized Bound vs Worst-Case Individual Cost

Dynamic-array insertion illustrates that an operation may have:

```text
Worst-Case Individual Cost
        ↓
Θ(n)
```

while simultaneously having:

```text
Amortized Cost
        ↓
Θ(1)
```

These statements are not contradictory.

They describe different analytical perspectives.

---

# Aggregate Analysis

The simplest amortized technique is **aggregate analysis**.

The method is:

```text
1. Consider a sequence of m operations.
        ↓
2. Bound the total cost T(m).
        ↓
3. Divide the bound across m operations.
```

Conceptually:

```text
T(m)
        ↓
Total Sequence Cost
        ↓
T(m) / m
        ↓
Amortized Cost
```

The dynamic-array example above can be analysed using this method.

---

# Aggregate Analysis Example

Suppose:

```text
m operations
```

have total cost bounded by:

```text
T(m) ≤ cm
```

for some constant `c`.

Then:

```text
T(m) / m ≤ c
```

Therefore, the amortized cost per operation is bounded by a constant.

Conceptually:

```text
Total Cost ∈ O(m)
        ↓
m Operations
        ↓
Amortized Cost ∈ O(1)
```

When a tight bound is established, a corresponding `Θ` statement may be used.

---

# Accounting Method

The **accounting method** assigns an artificial amortized charge to each
operation.

Some inexpensive operations may be charged slightly more than their actual
cost.

The extra amount is stored conceptually as **credit**.

Later expensive operations consume that credit.

Conceptually:

```text
Cheap Operation
        ↓
Actual Cost + Extra Charge
        ↓
Store Credit

Later Expensive Operation
        ↓
Use Stored Credit
```

The objective is to choose charges so that accumulated credit never becomes
negative.

---

# Why the Accounting Method Works

Suppose an ordinary operation costs:

```text
1
```

but we assign it an amortized charge of:

```text
3
```

The extra:

```text
2
```

may conceptually pay for future structural work.

If the credit system is designed correctly, the total amortized charges provide
an upper bound on the total actual cost.

The credits are an analytical device.

They are not actual variables that must exist in the implementation.

---

# Potential Method

The **potential method** generalises the accounting idea.

Instead of associating credit with individual objects or operations, it defines
a global potential function:

```text
Φ(state)
```

representing stored analytical potential in the current data-structure state.

The amortized cost of operation `i` is expressed conceptually as:

```text
Amortized Cost
        =
Actual Cost
        +
Change in Potential
```

or:

```text
ĉᵢ = cᵢ + Φ(Dᵢ) - Φ(Dᵢ₋₁)
```

where:

```text
cᵢ
```

is the actual cost and:

```text
Dᵢ
```

is the state after operation `i`.

---

# Intuition Behind the Potential Method

When an inexpensive operation prepares the structure for future expensive work,
the potential may increase.

```text
Cheap Operation
        ↓
Potential Increases
```

Later:

```text
Expensive Operation
        ↓
Potential Decreases
        ↓
Stored Potential Helps Account for Cost
```

The potential function is selected so that the sum of amortized costs bounds
the sum of actual costs.

---

# Three Classical Methods

The principal amortized-analysis techniques can therefore be summarised as:

```text
Aggregate Method
        ↓
Bound total sequence cost directly

Accounting Method
        ↓
Assign charges and conceptual credits

Potential Method
        ↓
Track global stored potential
```

They provide different ways of proving amortized bounds.

---

# These Methods Should Produce Compatible Conclusions

When applied correctly to the same operation sequence, the three techniques
should describe the same underlying cost behaviour.

They differ primarily in the style of reasoning.

For introductory problems, aggregate analysis is often the most intuitive.

Accounting and potential methods become useful when the cost structure is more
complex.

---

# Amortized Analysis and Asymptotic Notation

Amortized cost may itself be expressed asymptotically.

For example:

```text
Amortized Append Cost ∈ Θ(1)
```

or:

```text
Amortized Operation Cost ∈ O(log n)
```

depending on the structure.

Therefore:

```text
Sequence Analysis
        ↓
Amortized Cost Function
        ↓
Asymptotic Analysis
        ↓
O / Ω / Θ
```

Asymptotic notation describes the growth of the amortized-cost function just as
it describes other resource functions.

---

# Amortized Analysis and Worst-Case Guarantees

Amortized analysis can provide guarantees over sequences even when individual
operations have expensive worst cases.

For example:

```text
Individual Operation
        ↓
May Cost Θ(n)
```

but:

```text
Any Sequence of n Operations
        ↓
Total Cost Θ(n)
```

may imply:

```text
Amortized Cost Θ(1)
```

The guarantee is about the **sequence**, not each operation separately.

---

# Amortized Analysis and Time Complexity

Amortized analysis is commonly applied to time-related computational work.

Conceptually:

```text
Operation Sequence
        ↓
Individual Time Costs
        ↓
Total Time-Cost Function
        ↓
Amortized Time Cost
```

Therefore, amortized analysis does not replace time complexity.

It provides a way to derive a meaningful time-cost bound for operation
sequences.

---

# Amortized Analysis and Space Complexity

Amortized analysis is most commonly introduced through time cost, but similar
reasoning may sometimes help explain resource behaviour involving storage or
restructuring.

However, amortized analysis should not be treated as a substitute for explicit
space-complexity analysis.

Memory growth still requires its own:

```text
S(n)
```

model.

---

# Amortized Analysis and Dynamic Capacity

Dynamic structures often reserve unused capacity.

Conceptually:

```text
Logical Size
        <
Allocated Capacity
```

This may consume additional memory but reduce the frequency of expensive
resizing.

The design therefore creates a time-space trade-off:

```text
Extra Capacity
        ↓
More Memory
        ↓
Fewer Resizes
        ↓
Lower Amortized Update Cost
```

This illustrates the relationship between amortized time analysis and space
engineering.

---

# Java and Dynamic Arrays

In Java, `ArrayList` provides an important practical example of a resizable
array abstraction.

At the conceptual level, appending may involve:

```text
Available Capacity
        ↓
Store Element
```

or occasionally:

```text
Insufficient Capacity
        ↓
Increase Backing Storage
        ↓
Copy Elements
        ↓
Store Element
```

This structure motivates amortized reasoning.

However, implementation details such as exact growth policy belong to the Java
library implementation and may change between versions.

The theoretical concept should therefore remain independent of a specific
capacity multiplier.

---

# Interpreting `ArrayList.add`

A statement such as:

```text
ArrayList append is amortized constant time
```

should be interpreted carefully.

It means that for a suitable sequence of append operations, the total
resizing-and-insertion work grows linearly with the number of operations under
the relevant implementation model.

It does not mean that:

```text
every call performs exactly constant work
```

or that:

```text
resizing never occurs
```

---

# Hash Tables and Amortized Behaviour

Hash-table implementations may also require occasional restructuring.

For example:

```text
Load Factor Reaches Threshold
        ↓
Allocate Larger Table
        ↓
Reinsert / Reorganise Entries
```

A rebuild may be expensive.

If resizing is sufficiently infrequent, its cost may be distributed across many
ordinary operations.

However, hash-table complexity may also depend on:

* collision behaviour;
* hashing assumptions;
* load factor;
* expected-case analysis.

Therefore, expected analysis and amortized analysis should not be conflated.

---

# Stack Operations

Some stack implementations use dynamically resizable storage.

Ordinary:

```text
push
```

operations may be inexpensive.

Occasionally, capacity expansion may require copying many elements.

The sequence can therefore exhibit amortized behaviour similar to dynamic
arrays.

---

# Other Classical Examples

Amortized analysis is used in many algorithmic contexts.

Classical examples include:

* dynamic arrays;
* binary counters;
* stack operations in particular algorithms;
* table expansion;
* some self-adjusting data structures.

The exact technique depends on the operation sequence being analysed.

---

# Example — Binary Counter

Consider a binary counter.

Incrementing the counter may flip several bits.

For example:

```text
0000
↓
0001
```

flips one bit.

But:

```text
0111
↓
1000
```

flips several bits.

A single increment may therefore be relatively expensive.

However, over many increments, low-order bits flip frequently while high-order
bits flip progressively less often.

This structure can be analysed amortized.

---

# Why Binary Counter Is Useful

The binary-counter example illustrates an important principle:

```text
Expensive Individual Operation
```

does not imply:

```text
High Cost for Every Operation
```

The total number of bit changes across a long sequence can remain bounded
linearly in the number of increments.

Therefore, the amortized number of bit changes per increment can remain
constant.

---

# Amortized Analysis Is Not About “Rare” Events Alone

A common intuitive explanation is:

> Expensive operations are rare.

That may be true in many examples, but it is not the complete definition.

The real requirement is that the **total cost of the sequence can be bounded
appropriately**.

An expensive event could occur according to a more complicated pattern and
still admit an amortized bound.

The mathematical sequence bound is the essential concept.

---

# Sequence Length

Amortized analysis should define the number of operations being studied.

For example:

```text
m = number of operations
```

Then one may derive:

```text
T(m)
```

for the total sequence cost.

This avoids confusing:

```text
n = data-structure size
```

with:

```text
m = number of operations
```

although the two may sometimes be related.

---

# Multiple Parameters

Some amortized analyses may depend on several quantities.

For example:

```text
m = number of operations
n = maximum structure size
```

A cost expression may therefore involve both.

The same multi-parameter principles introduced earlier in the Complexity module
continue to apply.

---

# Amortized Analysis and Preconditions

The operation sequence must respect the data structure's valid contract.

For example, an analysis of stack operations may assume:

```text
pop is only performed when the stack is non-empty
```

unless failure behaviour is explicitly included in the cost model.

Amortized analysis therefore still depends on:

* valid operations;
* declared structure invariants;
* defined sequence assumptions.

---

# Amortized Analysis and Structural Invariants

Dynamic structures often preserve invariants while resizing or reorganising.

For example:

```text
Dynamic Array
        ↓
Logical Sequence Preserved
        ↓
Capacity Changes
```

The expensive structural operation must preserve the data structure's required
behaviour.

Therefore:

```text
Correctness
        ↓
before
Amortized Performance Analysis
```

still applies.

---

# Amortized Analysis and Benchmarking

Benchmarking may reveal latency spikes caused by expensive operations.

For example:

```text
Append
Append
Append
Resize
Append
...
```

may produce uneven individual measurements.

However, benchmark observations do not establish amortized complexity.

Conceptually:

```text
Operation Sequence
        ↓
Mathematical Cost Analysis
        ↓
Amortized Bound
```

and separately:

```text
Concrete Implementation
        ↓
Benchmark
        ↓
Observed Latency Distribution
```

These are different forms of evidence.

---

# Benchmarking Spikes

A benchmark may show that one operation is much slower than neighbouring
operations.

This may correspond to:

* resize;
* allocation;
* garbage collection;
* cache effects;
* JVM behaviour;
* unrelated system effects.

Therefore, an isolated timing spike should not automatically be interpreted as
evidence of a specific amortized event.

The implementation and benchmark design must be understood first.

---

# Amortized Analysis Does Not Guarantee Smooth Latency

An operation sequence may have excellent amortized complexity while still
containing individual expensive operations.

Therefore:

```text
Good Amortized Cost
        ≠
Uniform Per-Operation Latency
```

This distinction becomes important in systems with strict latency requirements.

A data structure with excellent amortized throughput may still be inappropriate
when individual operations must satisfy hard latency limits.

---

# Engineering Interpretation

Amortized analysis is especially useful when throughput across a long sequence
matters.

However, engineering decisions may also require:

* worst-case latency;
* memory overhead;
* predictability;
* real-time constraints;
* concurrency behaviour.

Therefore, amortized complexity is one analytical dimension rather than a
universal performance guarantee.

---

# Amortized Analysis and Real-Time Systems

Consider a system where every operation must complete within a strict deadline.

A structure with:

```text
Θ(1) amortized update
```

but:

```text
Θ(n) worst-case individual update
```

may still be unsuitable.

The amortized guarantee does not eliminate the expensive individual operation.

This illustrates the difference between:

```text
Throughput-Oriented Analysis
```

and:

```text
Worst-Case Latency Requirements
```

---

# Amortized Analysis vs Expected Analysis

The distinction can be summarised as:

| Analysis     | Probability Required? | Main Object                             |
| ------------ | :-------------------: | --------------------------------------- |
| Worst-case   |           No          | Maximum cost among valid inputs         |
| Average-case |          Yes          | Expected cost under a distribution      |
| Amortized    |           No          | Total cost across an operation sequence |
| Benchmarking |      Experimental     | Observed implementation behaviour       |

These techniques answer different questions.

---

# Amortized Analysis and Asymptotic Bounds

Suppose `m` operations have total cost:

```text
T(m) ∈ Θ(m)
```

Then the amortized cost per operation is:

```text
Θ(m) / m
```

which gives:

```text
Θ(1)
```

This is an example of a tight amortized bound.

Alternatively, if only an upper sequence bound is established:

```text
T(m) ∈ O(m log m)
```

then the amortized upper bound may be:

```text
O(log m)
```

per operation.

---

# Systematic Amortized Analysis

A useful analysis process is:

```text
1. Define the operation sequence.
        ↓
2. Identify the cost of individual operation types.
        ↓
3. Identify expensive structural operations.
        ↓
4. Determine how frequently expensive operations can occur.
        ↓
5. Bound the total cost of m operations.
        ↓
6. Distribute the total cost across the sequence.
        ↓
7. Express the amortized cost asymptotically.
        ↓
8. State the assumptions and sequence constraints.
```

For more complex structures, the accounting or potential method may replace
direct aggregate analysis.

---

# Choosing an Amortized Method

A practical guideline is:

```text
Can total sequence cost be bounded directly?
        ↓
Yes
        ↓
Aggregate Method
```

If direct aggregation becomes awkward:

```text
Can conceptual credits explain future cost?
        ↓
Accounting Method
```

For more global structural reasoning:

```text
Can a potential function capture stored work?
        ↓
Potential Method
```

The method should simplify the proof rather than add unnecessary complexity.

---

# Common Mistakes

## Confusing Amortized with Average Case

Amortized analysis requires no probability distribution.

---

## Calling an Empirical Mean “Amortized Complexity”

Benchmark averages are measurements.

Amortized complexity is derived mathematically.

---

## Saying Every Operation Has the Amortized Cost

Individual operations may be substantially more expensive.

---

## Ignoring Worst-Case Individual Cost

An amortized guarantee does not eliminate worst-case latency.

---

## Looking Only at the Expensive Operation

The sequence is the analytical object.

---

## Saying Expensive Operations Are “Negligible”

Their cost is not ignored.

It is explicitly included in the total sequence cost.

---

## Assuming Dynamic Arrays Always Double

The exact capacity-growth strategy is implementation-specific.

The theoretical example may use doubling because it simplifies analysis.

---

## Treating `ArrayList.add()` as Always Constant Time

Individual resize-triggering operations may require substantially more work.

---

## Assuming Hash-Table Expected Cost Is Amortized Cost

Expected collision behaviour and amortized resizing are different analyses.

---

## Ignoring Space Costs of Reserved Capacity

Low amortized update time may rely on additional allocated capacity.

---

## Using Benchmarks to Prove Amortized Bounds

Finite timing observations cannot establish a mathematical guarantee over
operation sequences.

---

# Best Practices

When performing amortized analysis:

* define the operation sequence explicitly;
* distinguish operation count from data-structure size;
* identify expensive operations;
* include expensive operations in the total cost;
* derive a sequence-level bound;
* distinguish amortized cost from individual cost;
* distinguish amortized analysis from average-case analysis;
* use aggregate analysis when it is sufficient;
* use accounting or potential methods when they simplify reasoning;
* state assumptions about growth policies or operation contracts;
* consider worst-case individual latency separately when engineering constraints
  require it;
* relate amortized performance to space trade-offs when dynamic capacity is
  involved;
* treat benchmarking as separate empirical evidence.

The objective is to explain **why the complete operation sequence remains
efficient despite uneven individual costs**.

---

# Key Takeaways

After completing this document, the learner should understand that:

* amortized analysis studies the total cost of a sequence of operations;
* amortized cost is not the same as the actual cost of every operation;
* amortized analysis does not require a probability distribution;
* average-case and amortized analyses answer different questions;
* amortized complexity is a mathematical guarantee rather than an empirical
  average;
* occasional expensive operations are included rather than ignored;
* dynamic arrays provide a classical example of amortized constant-time append;
* an operation may have linear worst-case individual cost and constant
  amortized cost;
* aggregate, accounting, and potential methods provide alternative amortized
  reasoning techniques;
* dynamic capacity may exchange additional space for favourable amortized time;
* excellent amortized cost does not guarantee uniform latency;
* empirical benchmarks cannot prove amortized complexity.

Amortized analysis therefore extends computational complexity from individual
operations to **sequences of operations**.

It provides a rigorous framework for understanding why dynamic data structures
can remain efficient even when occasional operations perform substantial
structural work.

---

# Next Document

```text
09-complexity-examples.md
```

The next document applies the concepts developed throughout the **Complexity**
module to representative algorithms and data structures.

The analysis will integrate:

```text
Input Size
        ↓
Significant Operations
        ↓
Cost Functions
        ↓
Best / Average / Worst Cases When Relevant
        ↓
Time Complexity
        ↓
Auxiliary Space
        ↓
Amortized Analysis When Relevant
        ↓
Engineering Interpretation
```

The next central question becomes:

> **How can the complete complexity-analysis methodology be applied to concrete
> algorithms?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The treatment of amortized analysis through aggregate analysis, the accounting
method, and the potential method provides the principal academic foundation for
this document.

Complementary references supporting data-structure behaviour and Java
implementation are documented in:

```text
docs/00-project/10-references.md
```

Official Java documentation should be consulted when later discussing the
behaviour of concrete Java Collections Framework implementations, while keeping
the theoretical amortized model distinct from version-specific implementation
details.
