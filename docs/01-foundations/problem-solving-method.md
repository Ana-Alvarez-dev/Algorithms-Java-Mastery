# Problem-Solving Method

## Algorithms Java Mastery

This document defines the standard problem-solving methodology adopted
throughout **Algorithms Java Mastery** for studying, reasoning about,
implementing, validating, and evaluating algorithms.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic study of algorithms throughout the repository.

Rather than beginning with programming, the methodology establishes a
disciplined reasoning process that begins with understanding the computational
problem and progressively develops toward specification, algorithm design,
correctness reasoning, complexity analysis, Java implementation, automated
validation, and experimental evaluation when appropriate.

The methodology consolidates the concepts introduced throughout the
**Foundations** module into a repeatable study process.

The central question addressed throughout this document is:

> **How should an algorithmic problem be studied before, during, and after
> implementation?**

---

# Purpose

The purpose of this document is to establish a systematic and repeatable
methodology for algorithmic problem solving.

Instead of treating each algorithm as an isolated implementation exercise, the
learner follows a structured process that connects:

* problem understanding;
* formal specification;
* behavioural contracts;
* edge-case analysis;
* problem decomposition;
* strategy design;
* pseudocode;
* manual reasoning;
* correctness;
* computational complexity;
* Java implementation;
* automated testing;
* empirical evaluation;
* technical conclusions.

The conceptual progression is:

```text
Computational Problem
        ↓
Problem Understanding
        ↓
Formal Specification
        ↓
Algorithmic Reasoning
        ↓
Correctness
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Validation
        ↓
Experimental Evaluation When Appropriate
        ↓
Engineering Conclusions
```

This methodology transforms algorithm study from a syntax-oriented activity
into a disciplined Computer Science and engineering process.

---

# Learning Objectives

After studying this document, the learner should be able to:

* analyse computational problems systematically;
* identify inputs, outputs, assumptions, and constraints;
* formulate precise problem specifications;
* define preconditions and postconditions;
* identify valid edge cases and invalid inputs;
* decompose complex problems when decomposition improves reasoning;
* design language-independent algorithmic strategies;
* express solutions through pseudocode;
* manually trace algorithm execution;
* formulate and use invariants when appropriate;
* reason about partial and total correctness;
* analyse theoretical time and auxiliary-space complexity;
* implement algorithms faithfully in Java;
* verify implementation behaviour through automated testing;
* evaluate performance experimentally when benchmarking provides meaningful
  evidence;
* interpret results without confusing empirical measurements with theoretical
  complexity;
* document technical conclusions and limitations.

These competencies define the complete learning cycle applied throughout the
repository.

---

# Methodology Overview

A complete algorithm study generally follows this progression:

```text
1. Problem Understanding
        ↓
2. Input and Output Identification
        ↓
3. Formal Specification
        ↓
4. Preconditions and Postconditions
        ↓
5. Edge-Case Analysis
        ↓
6. Problem Decomposition When Useful
        ↓
7. Strategy Design
        ↓
8. Pseudocode
        ↓
9. Manual Execution
        ↓
10. Correctness Reasoning
        ↓
11. Complexity Analysis
        ↓
12. Java Implementation
        ↓
13. Automated Testing
        ↓
14. Benchmarking When Appropriate
        ↓
15. Technical Conclusions
```

The sequence is intentionally progressive.

Later stages may reveal errors or assumptions that require returning to earlier
ones.

The process should therefore be understood as **structured but iterative**.

---

# Stage 1 — Problem Understanding

The first stage consists of understanding the computational problem without
considering Java syntax or implementation details.

Questions include:

* What must be solved?
* What information is provided?
* What result is required?
* What conditions define valid input?
* Which constraints exist?
* Which assumptions are already part of the problem?
* Which aspects remain ambiguous?

The objective is to understand the problem before attempting to solve it.

Implementation should not begin while the computational objective remains
unclear.

## Expected Result

A precise conceptual understanding of the problem and its boundaries.

---

# Stage 2 — Input and Output Identification

The learner identifies the observable relationship between the information
received and the result required.

Conceptually:

```text
Input
    ↓
Computational Problem
    ↓
Output
```

Inputs may include:

* arrays;
* values;
* strings;
* matrices;
* trees;
* graphs;
* structured collections.

Outputs may include:

* a value;
* an index;
* a boolean result;
* an ordered structure;
* a transformed structure;
* a path;
* an optimal solution.

The objective is to define what enters the computational problem and what must
be produced.

## Expected Result

Explicitly identified inputs and outputs.

---

# Stage 3 — Formal Specification

Problem understanding is transformed into a precise specification.

The specification should identify, when applicable:

* the problem statement;
* input domain;
* expected output;
* constraints;
* assumptions;
* valid problem instances;
* representative examples.

The specification answers:

> **What behaviour is required?**

It should remain independent of Java and algorithmic strategy.

Conceptually:

```text
Problem Understanding
        ↓
Input and Output Identification
        ↓
Formal Specification
        ↓
<<<<<<< Updated upstream:docs/01-foundations/problem-solving-method.md
Preconditions
        ↓
Postconditions
        ↓
Edge-Case Analysis
        ↓
Problem Decomposition
        ↓
Strategy Design
        ↓
Pseudocode
        ↓
Manual Execution
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Benchmarking
        ↓
Technical Conclusions
=======
Required Behaviour
>>>>>>> Stashed changes:docs/01-foundations/08-problem-solving-method.md
```

## Expected Result

A precise, implementation-independent description of the computational problem.

---

# Stage 4 — Preconditions and Postconditions

The learner refines the specification through explicit behavioural contracts.

## Preconditions

Preconditions define conditions that must hold before the algorithm is expected
to satisfy its guarantees.

Examples include:

```text
The input array contains at least one element.
```

```text
The search input is ordered.
```

```text
The supplied index belongs to the valid range.
```

## Postconditions

Postconditions define what must hold after successful execution.

Examples include:

```text
The returned value belongs to the input.
```

```text
Every element in the output satisfies the required ordering.
```

Conceptually:

```text
Preconditions
        ↓
Algorithm
        ↓
Postconditions
```

## Expected Result

A clear behavioural contract that later guides correctness reasoning,
implementation, and testing.

---

# Stage 5 — Edge-Case Analysis

The learner identifies important boundaries of the valid problem domain.

Examples may include:

* minimum valid input;
* single-element structures;
* duplicated values;
* negative values;
* numeric extremes;
* already ordered input;
* reverse-ordered input;
* structurally degenerate cases.

The learner should also distinguish:

```text
Valid Edge Cases
```

from:

```text
Invalid Inputs
```

An empty collection, for example, may be valid for one problem and invalid for
another.

Edge-case analysis should therefore begin from the specification and contract.

Conceptually:

```text
Specification
        ↓
Valid Input Domain
        ↓
Boundary Analysis
        ↓
Edge Cases
```

## Expected Result

A documented set of boundary conditions and expected behaviours relevant to the
problem.

---

# Stage 6 — Problem Decomposition

When the problem is sufficiently complex, it may be decomposed into smaller
responsibilities or subproblems.

Conceptually:

```text
Complex Problem
        ↓
Identify Structure
        ↓
Responsibilities or Subproblems
        ↓
Reason About Each Part
        ↓
Complete Solution
```

Decomposition should be used when it improves understanding.

Not every problem requires explicit decomposition.

The learner should distinguish:

* conceptual responsibilities;
* genuine computational subproblems;
* execution steps.

Problem decomposition should not automatically determine Java classes,
interfaces, methods, or packages.

## Expected Result

A clearer representation of the internal structure of the computational
problem.

---

# Stage 7 — Strategy Design

Only after the problem has been understood and specified should the learner
select or design an algorithmic strategy.

Possible strategy families may include:

* sequential processing;
* iterative reduction;
* recursion;
* divide and conquer;
* greedy reasoning;
* dynamic programming;
* hashing;
* graph traversal;
* exhaustive search;
* backtracking.

The strategy should be selected because it fits the computational structure of
the problem.

It should remain independent of Java syntax.

Conceptually:

```text
Specification
        ↓
Problem Structure
        ↓
Candidate Strategies
        ↓
Selected Algorithmic Strategy
```

## Expected Result

A justified, language-independent solution strategy.

---

# Stage 8 — Pseudocode

The selected strategy is expressed in structured pseudocode.

Pseudocode should make the algorithmic logic visible without introducing
unnecessary programming-language syntax.

For example:

```text
Set maximum to the first element.

For each remaining element:

    If the current element is greater than maximum:

        Update maximum.

Return maximum.
```

Pseudocode should:

* preserve the strategy;
* make control flow understandable;
* use meaningful conceptual operations;
* avoid Java-specific constructs unless necessary for explanation.

## Expected Result

A clear representation of the algorithm before implementation.

---

# Stage 9 — Manual Execution

The learner manually traces the algorithm using representative inputs.

For example:

```text
Input

[7, 2, 15, 4, 9]
```

A manual trace may examine:

```text
Initial maximum = 7

Process 2
maximum = 7

Process 15
maximum = 15

Process 4
maximum = 15

Process 9
maximum = 15

Return 15
```

Manual tracing helps reveal:

* incorrect assumptions;
* missing conditions;
* incorrect boundaries;
* flawed strategy transitions;
* candidate invariants.

However, manual execution does not prove general correctness.

It is a reasoning aid.

## Expected Result

Evidence that the proposed strategy behaves as expected for selected examples.

---

# Stage 10 — Correctness Reasoning

The learner explains why the algorithm satisfies its specification for every
valid problem instance covered by its contract.

Correctness reasoning may involve:

* preconditions;
* postconditions;
* loop invariants;
* structural invariants;
* termination arguments;
* mathematical induction;
* recursive reasoning;
* decomposition-based arguments.

Conceptually:

```text
Specification
        ↓
Algorithmic Strategy
        ↓
Correctness Argument
        ↓
Required Postconditions
```

The level of formalism should remain proportional to the algorithm and the
learning objective.

Correctness reasoning should answer:

> **Why should this algorithm work?**

## Expected Result

A documented argument supporting partial or total correctness as appropriate.

---

# Stage 11 — Complexity Analysis

Once the algorithmic strategy has been established, its theoretical
computational cost is analysed.

The learner should consider, when applicable:

* input size;
* significant operations;
* operation counting;
* growth functions;
* best-case behaviour;
* average-case behaviour;
* worst-case behaviour;
* time complexity;
* auxiliary-space complexity;
* recursion depth;
* time-space trade-offs.

Conceptually:

```text
Algorithm
        ↓
Input Size
        ↓
Significant Operations
        ↓
Growth Analysis
        ↓
Asymptotic Complexity
```

Complexity analysis belongs to the algorithmic level.

It should not be inferred from a single benchmark execution.

Formal complexity analysis is developed in:

```text
docs/02-complexity/
```

## Expected Result

A justified theoretical analysis of computational resource growth.

---

# Stage 12 — Java Implementation

Only after the algorithm has been understood, specified, designed, and analysed
is it translated into Java.

Production code belongs under:

```text
src/main/java/org/anaalvarez/algorithms/
```

The implementation should:

* preserve the algorithmic strategy;
* respect the documented contract;
* use meaningful names;
* make mutation explicit;
* remain readable;
* avoid unnecessary abstraction;
* remain independently testable;
* follow the Java project structure and engineering standards.

Java is therefore the **implementation language**, not the reasoning language.

Conceptually:

```text
Algorithmic Knowledge
        ↓
Java Translation
        ↓
Executable Implementation
```

## Expected Result

A compilable Java implementation faithful to the documented algorithm.

---

# Stage 13 — Automated Testing

The Java implementation is verified through automated tests.

Tests should derive primarily from:

* the specification;
* preconditions;
* postconditions;
* equivalence classes;
* important edge cases;
* invalid-input policy;
* structural invariants when observable.

Testing may include:

* ordinary cases;
* selected edge cases;
* invalid inputs when behaviour is defined;
* repeated values;
* mutation behaviour;
* regression scenarios.

Not every identified edge case necessarily requires a unique test.

Test selection should remain proportional to the contract and known failure
risks.

The repository uses:

```text
JUnit Jupiter
        +
AssertJ
```

Automated testing provides executable evidence that the Java implementation
behaves consistently with its specification for selected problem instances.

It does not replace correctness reasoning.

Formal testing practices are developed in:

```text
docs/16-testing/
```

## Expected Result

A deterministic and readable automated test suite validating relevant
implementation behaviour.

---

# Stage 14 — Benchmarking When Appropriate

Benchmarking is introduced only when empirical performance evaluation provides
meaningful educational or engineering value.

The repository uses:

```text
Java Microbenchmark Harness (JMH)
```

Benchmarking may be useful when comparing:

* algorithm variants;
* implementation strategies;
* input sizes;
* input distributions;
* iterative and recursive solutions;
* theoretical expectations and observed behaviour.

A benchmark should define:

* the implementation being measured;
* input size;
* data distribution;
* benchmark state;
* setup;
* warm-up;
* measurement;
* environmental limitations.

Benchmarking answers:

> **How did this implementation perform under these defined experimental
> conditions?**

It does not answer:

> **What is the algorithm's asymptotic complexity?**

That question belongs to theoretical analysis.

Benchmarking is developed formally in:

```text
docs/17-benchmarking/
```

## Expected Result

Controlled empirical evidence when performance measurement is relevant.

---

# When Benchmarking Is Not Required

Not every algorithm needs a benchmark.

Benchmarking may be omitted when:

* the topic is conceptual;
* performance comparison is not part of the learning objective;
* correctness has not yet been established;
* the implementation is too simple for meaningful comparison;
* environmental variation would make the result uninformative;
* theoretical analysis already answers the relevant educational question.

Benchmarking exists to provide evidence, not to satisfy a procedural checklist.

---

# Stage 15 — Technical Conclusions

The final stage consolidates the knowledge developed throughout the study
process.

Relevant questions may include:

* What computational problem was solved?
* Which assumptions were required?
* Why is the algorithm correct?
* What is its computational complexity?
* Which edge cases were important?
* Which trade-offs exist?
* Did the implementation preserve the documented strategy?
* Did automated tests reveal important defects?
* Did benchmark observations align with theoretical expectations?
* Which limitations remain?
* In which situations is the algorithm appropriate?
* Which alternative strategies could also solve the problem?

Technical conclusions should distinguish:

```text
Theoretical Evidence
```

from:

```text
Implementation Evidence
```

and:

```text
Experimental Evidence
```

A design pattern should only be discussed when an actual implementation or
structural problem justifies its use.

It should not be treated as a mandatory conclusion for every algorithm.

## Expected Result

A concise technical evaluation connecting theory, implementation, validation,
and evidence.

---

# The Methodology Is Iterative

Although the methodology defines a recommended progression, algorithm study is
not strictly linear.

Later stages may reveal problems in earlier reasoning.

Examples include:

```text
Failed Test
        ↓
Revisit Specification or Implementation
```

```text
Incorrect Invariant
        ↓
Revisit Correctness Reasoning
```

```text
Unexpected Complexity
        ↓
Revisit Algorithmic Strategy
```

```text
Unexpected Benchmark Result
        ↓
Revisit Experimental Design or Assumptions
```

Returning to an earlier stage is part of disciplined problem solving.

It does not represent failure.

---

# Evidence Produced by the Methodology

Each stage produces a different form of knowledge or evidence.

| Stage                 | Principal Evidence                                    |
| --------------------- | ----------------------------------------------------- |
| Problem Understanding | Clear computational objective                         |
| Specification         | Defined inputs, outputs, constraints, and assumptions |
| Contracts             | Preconditions and postconditions                      |
| Edge-Case Analysis    | Boundary understanding                                |
| Decomposition         | Problem structure                                     |
| Strategy Design       | Algorithmic reasoning                                 |
| Pseudocode            | Language-independent procedure                        |
| Manual Execution      | Example-based reasoning                               |
| Correctness           | Logical justification                                 |
| Complexity            | Theoretical resource analysis                         |
| Java Implementation   | Executable solution                                   |
| Automated Testing     | Behavioural evidence                                  |
| Benchmarking          | Experimental evidence                                 |
| Technical Conclusions | Consolidated engineering knowledge                    |

No single form of evidence replaces the others.

---

# Applying the Methodology Throughout the Repository

The methodology remains consistent across later modules, although its emphasis
changes according to the topic.

For example:

```text
Arrays
        ↓
Understand Representation
        ↓
Specify Operation
        ↓
Design Algorithm
        ↓
Analyse
        ↓
Implement and Test
```

```text
Searching
        ↓
Define Search Contract
        ↓
Design Strategy
        ↓
Reason About Correctness
        ↓
Analyse Complexity
        ↓
Implement and Test
```

```text
Sorting
        ↓
Define Ordering Requirements
        ↓
Identify Invariants
        ↓
Analyse Complexity
        ↓
Implement Variants
        ↓
Compare When Appropriate
```

```text
Trees and Graphs
        ↓
Define Structural Invariants
        ↓
Design Traversal or Modification
        ↓
Reason About Correctness
        ↓
Analyse Complexity
        ↓
Implement and Validate
```

```text
Dynamic Programming
        ↓
Identify Subproblem Structure
        ↓
Define Recurrence
        ↓
Reason About Correctness
        ↓
Analyse Complexity
        ↓
Implement and Compare Variants
```

The reasoning framework remains stable while individual techniques evolve.

---

# Relationship with the Study Methodology

This document defines the practical algorithm-level method used throughout the
repository.

It operates within the broader methodology defined in:

```text
docs/00-project/03-study-methodology.md
```

Conceptually:

```text
Project Study Methodology
        ↓
Defines the Overall Learning Process
        ↓
Foundations Problem-Solving Method
        ↓
Defines the Algorithm-Level Reasoning Process
```

The two documents are complementary rather than redundant.

---

# Relationship with Software Engineering

The problem-solving method begins in Computer Science reasoning and later
connects naturally with professional engineering practices.

Conceptually:

```text
Problem Understanding
        ↓
Algorithmic Reasoning
        ↓
Correctness
        ↓
Complexity
        ↓
Java Implementation
        ↓
Testing
        ↓
Benchmarking
        ↓
Versioned and Reproducible Engineering
```

Later repository modules introduce:

* Java project organisation;
* Maven;
* Git;
* Linux development environments;
* automated testing;
* benchmarking;
* Continuous Integration.

These technologies support the methodology.

They do not define it.

---

# Relationship with Design Patterns

Design patterns are not part of the foundational algorithmic method itself.

They belong to implementation and software-design decisions.

A pattern may later become useful when several Java implementations expose a
genuine shared responsibility.

For example:

```text
Several Interchangeable Algorithms
        ↓
Meaningful Shared Contract
        ↓
Possible Strategy Pattern
```

However:

```text
Multiple Algorithms
```

does not automatically imply:

```text
Strategy Pattern
```

Patterns should therefore be introduced only when they improve the actual Java
design or provide clear educational value.

---

# Best Practices

When applying the problem-solving method:

* begin with the computational problem;
* keep problem reasoning independent of Java initially;
* make assumptions and constraints explicit;
* define preconditions and postconditions;
* distinguish valid edge cases from invalid inputs;
* decompose only when decomposition improves reasoning;
* justify strategy selection;
* use pseudocode before implementation when useful;
* manually trace representative cases;
* reason about correctness explicitly;
* analyse theoretical complexity before interpreting performance measurements;
* implement only after the algorithm is sufficiently understood;
* derive tests from the specification and failure risks;
* benchmark only when experimental evaluation provides meaningful evidence;
* separate theoretical, implementation, and empirical conclusions;
* revisit earlier stages when new evidence reveals an inconsistency.

The methodology should create discipline without becoming a mechanical
checklist.

---

# Common Mistakes

## Starting with Java

Writing code before understanding the problem often hides incorrect assumptions.

---

## Treating Examples as a Specification

Examples clarify behaviour but do not define the complete valid problem domain.

---

## Skipping Contracts

Without explicit preconditions and postconditions, correctness and test
expectations may become ambiguous.

---

## Treating Every Unusual Input as an Edge Case

Some inputs lie outside the declared contract.

Valid edge cases and invalid inputs should remain distinct.

---

## Decomposing Every Problem

Simple problems may not benefit from additional decomposition.

---

## Using Manual Execution as Proof

Manual traces provide useful examples but do not establish correctness across
the complete valid domain.

---

## Testing Before Clarifying Behaviour

Tests should derive from a sufficiently precise specification rather than define
the specification accidentally.

---

## Measuring Before Analysing

Benchmark results do not replace theoretical complexity analysis.

---

## Benchmarking Incorrect Implementations

Correctness must be established before performance comparisons become
meaningful.

---

## Introducing Design Patterns Mechanically

A pattern should solve a recognised design problem rather than be added because
it appears in a checklist.

---

# Key Takeaways

After completing this document, the learner should understand that:

* algorithms should be understood before they are implemented;
* problem understanding precedes specification;
* specifications define required behaviour;
* preconditions and postconditions define contractual boundaries;
* edge cases expose important boundaries of the valid domain;
* decomposition is used when it improves reasoning;
* algorithmic strategies should remain independent of Java initially;
* pseudocode and manual tracing support understanding;
* correctness reasoning explains why an algorithm should work;
* complexity analysis examines theoretical computational growth;
* Java implementation translates the algorithm into executable software;
* testing provides implementation evidence but does not replace reasoning;
* benchmarking provides empirical evidence but does not replace complexity
  analysis;
* technical conclusions should distinguish theoretical, implementation, and
  experimental evidence;
* the methodology is iterative and may require revisiting earlier stages.

The **Problem-Solving Method** therefore becomes the algorithm-level
methodological foundation of **Algorithms Java Mastery**.

It consolidates the concepts introduced throughout the **Foundations** module
and establishes the reasoning process that will be applied progressively to
arrays, searching, sorting, recursion, trees, graphs, algorithm design
paradigms, and later Java implementations.

---

# Module Completion

With this document, the **Foundations** module establishes the principal
conceptual tools required before beginning formal complexity analysis.

The module progression is now:

```text
01-algorithm-definition.md
        ↓
02-problem-specification.md
        ↓
03-preconditions-and-postconditions.md
        ↓
04-edge-cases.md
        ↓
05-correctness.md
        ↓
06-invariants.md
        ↓
07-problem-decomposition.md
        ↓
08-problem-solving-method.md
```

Together, these documents establish a transition from:

```text
"What code should I write?"
```

toward:

```text
"What problem am I solving,
which conditions define it,
why should the strategy work,
and how should I analyse it before implementation?"
```

That transition represents the principal educational outcome of the
**Foundations** module.

---

# Next Module

```text
docs/02-complexity/
```

The next module introduces the formal analysis of computational cost.

It develops the concepts required to study:

* input size;
* significant operations;
* operation counting;
* algorithm growth;
* asymptotic notation;
* Big O;
* Big Omega;
* Big Theta;
* best, average, and worst cases;
* auxiliary space;
* time-space trade-offs.

The next major question becomes:

> **Once an algorithm is understood and its correctness can be reasoned about,
> how do the computational resources required by that algorithm grow as the
> problem size increases?**

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein
> Fourth Edition
> MIT Press

The systematic progression from problem understanding to algorithm design,
correctness reasoning, and complexity analysis provides the principal academic
foundation for the methodology adopted throughout **Algorithms Java Mastery**.

Complementary academic and technical references supporting problem solving,
formal reasoning, Java implementation, testing, and engineering practices are
documented in:

```text
docs/00-project/10-references.md
```

Later modules introduce specialised references when individual topics require
additional theoretical or technical depth.
