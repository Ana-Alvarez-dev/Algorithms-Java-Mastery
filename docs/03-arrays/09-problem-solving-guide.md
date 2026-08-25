# Problem-Solving Guide

## Algorithms Java Mastery

This document defines the standard methodology used throughout **Algorithms
Java Mastery** for analysing and solving algorithmic problems involving arrays.

**Algorithms Java Mastery** is inspired primarily by *Introduction to Algorithms*
(Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein),
which provides the principal academic foundation for the algorithmic reasoning
developed throughout this repository.

The previous Arrays documents established:

- the abstract array model;
- memory organisation;
- Java array semantics;
- multidimensional arrays;
- fundamental array operations;
- common array algorithms;
- complexity analysis.

This document integrates those concepts into a repeatable problem-solving
process.

The central principle is:

> **The solution should be designed and reasoned about before it is
> implemented in Java.**

The central question addressed throughout this document is:

> **How should an array problem be analysed, designed, validated, and
> implemented before writing Java code?**

---

# Purpose

The purpose of this document is to establish a systematic and repeatable
methodology for solving array-based computational problems.

The process is:

```text
Problem
        ↓
Understand Requirements
        ↓
Identify Inputs and Outputs
        ↓
Define Preconditions and Postconditions
        ↓
Analyse Edge Cases
        ↓
Decompose the Problem
        ↓
Recognise Algorithmic Pattern
        ↓
Design Strategy
        ↓
Write Pseudocode
        ↓
Perform Manual Trace
        ↓
Reason About Correctness
        ↓
Analyse Complexity
        ↓
Implement in Java
        ↓
Automated Testing
        ↓
Engineering Evaluation
```

Each stage has a specific responsibility.

The purpose of the methodology is not to make every problem artificially
formal, but to ensure that important reasoning steps are not skipped.

---

# Learning Objectives

After studying this document, the learner should be able to:

- analyse array problems systematically;
- distinguish a problem from its implementation;
- identify inputs and outputs precisely;
- define preconditions and postconditions;
- identify relevant edge cases;
- decompose problems when necessary;
- recognise reusable algorithmic patterns;
- design strategies before writing Java code;
- express algorithms using pseudocode;
- manually trace algorithm execution;
- reason about correctness;
- derive time and auxiliary-space complexity;
- implement solutions clearly in Java;
- validate implementations through automated tests;
- evaluate algorithmic trade-offs from an engineering perspective.

---

# The Complete Problem-Solving Process

Every array problem should be approached using the following sequence:

```text
Problem
        ↓
Specification
        ↓
Contracts
        ↓
Edge Cases
        ↓
Decomposition
        ↓
Pattern Recognition
        ↓
Strategy
        ↓
Pseudocode
        ↓
Manual Trace
        ↓
Correctness
        ↓
Complexity
        ↓
Java Implementation
        ↓
Testing
        ↓
Engineering Conclusions
```

The sequence is a methodological guide rather than a rigid requirement that
every problem contain the same amount of documentation.

Simple problems may require less formal decomposition.

Complex problems require deeper reasoning.

---

# Step 1 — Understand the Problem

The first responsibility is to understand exactly what must be solved.

Before considering code, ask:

- What is the computational objective?
- What information is provided?
- What result must be produced?
- What constraints exist?
- What assumptions are valid?
- Is the problem asking for a value, position, transformation, decision, or
  collection of results?

The problem should be expressible independently of Java syntax.

For example:

```text
Determine the largest value contained in an integer array.
```

This describes the problem.

It does not yet specify:

```text
for
if
while
```

or any other implementation mechanism.

---

# Step 2 — Identify the Input

The input defines the information available to the algorithm.

Example:

```text
Input

[7, 2, 15, 4, 9]
```

The input may consist of:

- one array;
- multiple arrays;
- a multidimensional array;
- numerical parameters;
- strings;
- other supporting values.

For arrays, explicitly identify the relevant size parameters.

For example:

```text
n = number of elements
```

or:

```text
R = number of rows
C = number of columns
```

or:

```text
n = length(A)
m = length(B)
```

Do not unnecessarily collapse independent dimensions into a single variable.

---

# Step 3 — Identify the Output

Define precisely what the algorithm must produce.

Example:

```text
Output

15
```

For other problems, the output may be:

```text
true / false
```

```text
index
```

```text
modified array
```

```text
new array
```

```text
count
```

or another explicitly defined result.

The output should describe observable behaviour rather than implementation
details.

---

# Step 4 — Define Preconditions

Preconditions specify what must be true before execution.

Examples:

```text
Array is not null.
```

```text
Array contains at least one element.
```

```text
Array is sorted.
```

```text
Indexes are within the valid range.
```

The precondition depends on the problem.

For example:

```text
Find Maximum
```

may require:

```text
n ≥ 1
```

while:

```text
Count Occurrences
```

may naturally support:

```text
n = 0
```

The contract should make the distinction explicit.

---

# Step 5 — Define Postconditions

Postconditions describe what must be true after successful execution.

For example, for maximum search:

```text
Returned value belongs to the input array.

No element in the array is greater than the returned value.
```

For an in-place reverse:

```text
The array contains the same elements as before execution.

Their order is reversed.
```

For a sortedness check:

```text
true  → all adjacent elements satisfy the required ordering relation.
false → at least one required ordering relation is violated.
```

Postconditions provide the target against which correctness can be reasoned
about and tested.

---

# Step 6 — Identify Edge Cases

Edge cases should be analysed before implementation.

Common array situations include:

```text
Empty Array
Single Element
All Elements Equal
Duplicate Values
Negative Values
Extreme Values
Already Sorted
Reverse Sorted
Very Large Input
```

However:

> **An edge case is only relevant if it belongs to the problem's defined
> input domain.**

For example:

```text
Find Maximum
```

may reject an empty array through its precondition.

In contrast:

```text
Calculate Sum
```

may define an empty array as:

```text
sum = 0
```

The specification determines the expected behaviour.

---

# Step 7 — Decompose the Problem When Necessary

Some problems can be solved directly.

Others benefit from decomposition.

For example:

```text
Complex Problem
        ↓
Identify Smaller Responsibilities
        ↓
Solve Each Responsibility
        ↓
Combine Results
```

For an array-processing problem:

```text
Find Second Largest
        │
        ├── Validate Input
        ├── Track Largest
        ├── Track Second Largest
        └── Return Result
```

Decomposition should simplify reasoning rather than introduce unnecessary
abstraction.

---

# Step 8 — Recognise the Algorithmic Pattern

Before inventing a new solution, determine whether the problem resembles a
known algorithmic pattern.

Common array patterns include:

```text
Sequential Traversal
```

```text
Two Pointers
```

```text
Sliding Window
```

```text
Prefix Processing
```

```text
Accumulator
```

```text
Stateful Single Pass
```

```text
Binary Search
```

```text
Divide and Conquer
```

Pattern recognition reduces unnecessary complexity.

The objective is not to memorise code.

It is to recognise the underlying computational structure.

---

# Step 9 — Design the Strategy

Once the problem and pattern are understood, determine the algorithmic
strategy.

Ask:

- Can the problem be solved in one traversal?
- Is direct indexing useful?
- Can the search space be reduced?
- Can two pointers avoid nested traversal?
- Is additional memory justified?
- Does the input ordering provide useful information?
- Is preprocessing beneficial?
- Is mutation acceptable?

The strategy should be described independently of Java syntax.

Example:

```text
Maintain the largest and second-largest distinct values during one traversal.
```

This is a strategy.

---

# Step 10 — Write Pseudocode

Pseudocode describes the logical procedure without committing to a programming
language.

Example:

```text
maximum ← first element

for each remaining element

    if current > maximum

        maximum ← current

return maximum
```

Pseudocode is useful because it separates:

```text
Algorithmic Logic
```

from:

```text
Programming Syntax
```

This separation makes reasoning and review easier.

---

# Step 11 — Perform a Manual Trace

Execute the algorithm using a small representative input.

Example:

```text
Input

[7, 2, 15, 4, 9]
```

For maximum search:

```text
Initial maximum = 7

Compare 2
maximum = 7

Compare 15
maximum = 15

Compare 4
maximum = 15

Compare 9
maximum = 15
```

Manual tracing can reveal:

- incorrect initialization;
- incorrect conditions;
- off-by-one errors;
- missing updates;
- incorrect termination;
- incorrect assumptions.

A small trace is often an efficient way to validate the basic reasoning before
implementation.

---

# Step 12 — Reason About Correctness

The next question is:

> **Why does this algorithm always produce the required result for valid
> inputs?**

Correctness reasoning should connect the algorithm to the specification.

For iterative array algorithms, invariants are often useful.

Example:

```text
After processing the first i elements,
maximum contains the largest value among those elements.
```

At termination:

```text
All valid elements have been processed.
```

Therefore:

```text
maximum
        ↓
Largest value in the complete array
```

The exact correctness argument depends on the algorithm.

---

# Step 13 — Analyse Time Complexity

After the algorithm is logically established, analyse computational cost.

Ask:

- What is the input size?
- Which operation represents significant work?
- How many times can it execute?
- Can execution terminate early?
- Do different input cases have different costs?
- Are multiple input-size parameters required?

For example:

```text
One traversal of n elements
        ↓
T(n) = cn + d
        ↓
Θ(n)
```

Use the tight bound when it is justified.

---

# Step 14 — Analyse Auxiliary Space

Determine how much additional memory the algorithm requires.

Ask:

- Does the algorithm allocate another array?
- Does it create auxiliary collections?
- Does recursion create stack frames?
- Does it use only a fixed number of variables?
- Is the input itself being counted or excluded?

Examples:

```text
Maximum Search
        ↓
Auxiliary Space = Θ(1)
```

```text
Merge Two Sorted Arrays
        ↓
Auxiliary Space = Θ(n + m)
```

The implementation model should be stated when it affects the result.

---

# Step 15 — Implement in Java

Only after the logical design has been validated should implementation begin.

The Java implementation should prioritise:

- correctness;
- readability;
- simplicity;
- maintainability;
- appropriate use of language features.

The implementation should express the algorithm already designed.

It should not become the place where the algorithm is invented accidentally.

---

# Step 16 — Validate the Java Implementation

After implementation, compare the Java program against the original
specification.

Verify:

```text
Input
        ↓
Expected Behaviour
        ↓
Actual Behaviour
```

Check that:

- preconditions are enforced or documented;
- postconditions are satisfied;
- edge cases behave as specified;
- the implementation matches the intended algorithm;
- complexity assumptions still apply to the implementation.

---

# Step 17 — Write Automated Tests

Automated tests should derive from the specification and contracts.

Typical array tests may include:

```text
Normal Input
Single Element
Empty Input When Valid
Duplicate Values
Negative Values
Extreme Values
Already Sorted
Reverse Sorted
```

Only include cases that belong to the algorithm's valid domain or explicitly
test its invalid-input behaviour.

The relationship is:

```text
Specification
        ↓
Expected Behaviour
        ↓
Test Case
        ↓
Assertion
```

JUnit Jupiter and AssertJ are the principal testing tools adopted by this
repository.

---

# Step 18 — Evaluate the Solution

After implementation and testing, evaluate the complete solution.

Questions include:

- Is it correct?
- Is the complexity appropriate?
- Is the auxiliary memory acceptable?
- Is the code readable?
- Is the chosen data structure appropriate?
- Is there a simpler strategy?
- Is there a more scalable strategy?
- Does the implementation mutate input unnecessarily?
- Are the documented assumptions accurate?

This stage transforms implementation into engineering evaluation.

---

# Benchmarking When Appropriate

Benchmarking is not mandatory for every array algorithm.

It should be used when empirical information provides meaningful additional
evidence.

A typical workflow is:

```text
Theoretical Analysis
        ↓
Complexity Classification
        ↓
Java Implementation
        ↓
JMH Benchmark
        ↓
Empirical Observation
        ↓
Engineering Interpretation
```

Benchmarking may help investigate:

- constant-factor differences;
- implementation alternatives;
- input-size crossover points;
- JVM behaviour;
- practical effects of memory locality.

It does not replace theoretical complexity analysis.

---

# Complete Array Problem-Solving Template

Future array problems can use the following template:

```text
## Problem

## Input

## Output

## Preconditions

## Postconditions

## Edge Cases

## Decomposition
(when useful)

## Algorithmic Pattern

## Strategy

## Pseudocode

## Manual Trace

## Correctness / Invariant

## Time Complexity

## Auxiliary Space

## Java Implementation

## Automated Tests

## Engineering Interpretation
```

Not every section must be equally detailed for every exercise.

The structure should adapt to problem complexity.

---

# Example — Maximum Element

The complete process can be summarised as:

```text
Problem
        ↓
Find Maximum
        ↓
Input
        ↓
Integer Array
        ↓
Precondition
        ↓
At Least One Element
        ↓
Pattern
        ↓
Sequential Traversal
        ↓
Strategy
        ↓
Maintain Maximum-So-Far
        ↓
Invariant
        ↓
Maximum = Largest Processed Value
        ↓
Complexity
        ↓
Time Θ(n), Auxiliary Space Θ(1)
        ↓
Java Implementation
        ↓
JUnit / AssertJ Tests
```

This example demonstrates how the different repository concepts connect.

---

# Example — Binary Search

A more structured example:

```text
Problem
        ↓
Find Target in Sorted Array
        ↓
Precondition
        ↓
Array Is Ordered
        ↓
Pattern
        ↓
Search-Space Reduction
        ↓
Strategy
        ↓
Inspect Middle and Discard Half
        ↓
Correctness
        ↓
Target Remains in Valid Search Interval
        ↓
Complexity
        ↓
Worst-Case Time Θ(log n)
```

Implementation details follow only after the reasoning is established.

---

# Example — Duplicate Detection

The problem may admit multiple valid strategies.

## Strategy A — Pairwise Comparison

```text
Time
        ↓
Θ(n²)

Auxiliary Space
        ↓
Θ(1)
```

## Strategy B — Auxiliary Hash Structure

Under suitable hashing assumptions:

```text
Expected Time
        ↓
Θ(n)

Auxiliary Space
        ↓
Θ(n)
```

The engineering decision therefore becomes:

```text
Less Memory
        ↔
More Computation
```

versus:

```text
More Memory
        ↔
Less Expected Computation
```

The best strategy depends on:

- input size;
- memory constraints;
- performance requirements;
- hashing assumptions.

---

# Relationship with Foundations

This guide directly applies the concepts from:

```text
docs/01-foundations/
```

The correspondence is:

```text
Problem Specification
        ↓
Problem Definition

Preconditions / Postconditions
        ↓
Contracts

Edge Cases
        ↓
Boundary Analysis

Decomposition
        ↓
Problem Simplification

Correctness
        ↓
Invariant-Based Reasoning

Problem-Solving Method
        ↓
Complete Workflow
```

The Arrays module therefore serves as the first substantial application of the
reasoning framework introduced earlier.

---

# Relationship with Complexity

This guide also integrates:

```text
docs/02-complexity/
```

The relationship is:

```text
Define Input Size
        ↓
Identify Significant Operations
        ↓
Construct Cost Function
        ↓
Analyse Growth
        ↓
Apply Asymptotic Notation
        ↓
Analyse Auxiliary Space
```

This ensures that complexity is derived rather than memorised.

---

# Relationship with Java

Java represents the implementation stage:

```text
Abstract Algorithm
        ↓
Java Implementation
```

Java should not determine the algorithmic strategy prematurely.

The developer should first know:

```text
What the algorithm does
        ↓
Why it is correct
        ↓
How efficiently it behaves
        ↓
How Java should express it
```

---

# Relationship with Testing

Testing is derived from the specification:

```text
Specification
        ↓
Expected Behaviour
        ↓
Test Case
        ↓
Assertion
```

Testing provides evidence about implementation behaviour.

It does not prove algorithmic correctness for every possible input.

Formal reasoning and automated testing complement one another.

---

# Relationship with Future Modules

The same methodology will be applied to:

```text
Searching
        ↓
Search-Space Reasoning

Sorting
        ↓
Comparison and Partitioning

Recursion
        ↓
Base Cases + Recurrence

Trees
        ↓
Structural Invariants

Graphs
        ↓
Traversal + State Tracking

Dynamic Programming
        ↓
Subproblem State + Recurrence

Backtracking
        ↓
State Exploration + Constraint Checking
```

The algorithmic strategy changes.

The engineering methodology remains consistent.

---

# Common Mistakes

## Writing Code Immediately

Starting with Java syntax can hide misunderstandings in the problem
specification.

---

## Skipping the Contract

Without explicit preconditions and postconditions, expected behaviour may remain
ambiguous.

---

## Ignoring Edge Cases

Typical inputs do not define the complete behaviour of an algorithm.

---

## Skipping Manual Tracing

A small example can reveal logical errors before implementation.

---

## Using a Familiar Algorithm Automatically

A known algorithm is useful only when its assumptions match the current problem.

---

## Optimising Before Establishing Correctness

The correct progression is:

```text
Correctness
        ↓
Complexity
        ↓
Optimisation
```

---

## Reporting Complexity Without Derivation

Writing:

```text
O(n)
```

is less useful than explaining:

```text
n elements
        ↓
One constant-work operation per element
        ↓
T(n) = cn + d
        ↓
Θ(n)
```

---

## Ignoring Auxiliary Space

A solution may be fast but unnecessarily memory-intensive.

---

## Assuming All Array Problems Need One Traversal

Some problems require:

- multiple pointers;
- binary search;
- auxiliary structures;
- preprocessing;
- recursive decomposition.

Pattern recognition should follow problem requirements.

---

## Treating Benchmark Results as Complexity Proof

Empirical measurements cannot establish formal asymptotic guarantees.

---

# Best Practices

When solving array problems:

- understand the specification completely;
- identify all relevant inputs;
- define valid and invalid cases;
- state preconditions and postconditions;
- analyse edge cases before coding;
- recognise reusable patterns;
- choose the simplest appropriate strategy;
- write pseudocode before Java;
- trace the algorithm manually;
- reason about correctness;
- derive time and auxiliary-space complexity;
- implement only after the algorithm is understood;
- test against the defined contract;
- benchmark only when useful;
- document the final engineering decision.

The objective is to develop a repeatable process that scales from simple array
exercises to complex algorithmic problems.

---

# Final Problem-Solving Principles

The complete methodology can be summarised as:

```text
Understand
        ↓
Specify
        ↓
Constrain
        ↓
Analyse
        ↓
Decompose
        ↓
Recognise
        ↓
Design
        ↓
Trace
        ↓
Prove / Reason
        ↓
Measure Theoretically
        ↓
Implement
        ↓
Test
        ↓
Evaluate
```

The sequence should become habitual.

---

# Expected Outcome

After completing this document, the learner should be able to approach an array
problem without immediately writing Java code.

The reasoning process should instead begin with:

```text
What is the problem?
```

then:

```text
What are the valid inputs?
```

then:

```text
What result is required?
```

then:

```text
What strategy fits the problem?
```

then:

```text
Why does the strategy work?
```

then:

```text
What is its computational cost?
```

and only then:

```text
How should it be implemented and tested in Java?
```

This transition—from **code-first thinking** to **problem-first reasoning**—is
one of the principal educational objectives of **Algorithms Java Mastery**.

---

# Key Takeaways

After studying this document, the learner should remember that:

- problem solving begins before implementation;
- a computational problem should be understood and specified first;
- inputs, outputs, assumptions, and contracts must be explicit;
- edge cases belong to the analysis stage;
- decomposition should be used when it simplifies reasoning;
- reusable algorithmic patterns can accelerate solution design;
- pseudocode separates algorithmic reasoning from programming syntax;
- manual tracing provides an early validation step;
- correctness should be established before optimisation;
- time and auxiliary-space complexity should be derived from the algorithm;
- Java should express an already-understood solution;
- automated tests should be derived from the specification;
- benchmarking is optional and complements theoretical analysis;
- engineering evaluation should consider correctness, scalability, memory,
  maintainability, and problem constraints.

The **Problem-Solving Guide** therefore represents the practical application of
the intellectual foundations established throughout **Foundations** and
**Complexity**.

It provides a repeatable methodology for progressing from a natural-language
computational problem to a **correct, analysed, implemented, tested, and
engineering-justified solution**.

---

# Next Document

```text
10-common-mistakes.md
```

The next document analyses common mistakes made when working with arrays.

It focuses on:

- off-by-one errors;
- invalid indexes;
- incorrect loop boundaries;
- null versus empty arrays;
- incorrect mutation assumptions;
- reference versus content copying;
- unnecessary complexity;
- incorrect complexity claims.

The next central question becomes:

> **Which mistakes commonly cause incorrect or inefficient array algorithms,
> and how can they be prevented systematically?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The problem-solving methodology integrates the principles established throughout
the **Foundations**, **Complexity**, and **Arrays** modules.

Java implementation practices are based on the Java language and standard
library model.

Complementary academic, mathematical, and technical references are documented
in:

```text
docs/00-project/10-references.md
```

Additional references may be introduced when later algorithmic modules require
more specialised literature.