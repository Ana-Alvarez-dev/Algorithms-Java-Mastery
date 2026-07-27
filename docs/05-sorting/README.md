# Sorting

## Algorithms Java Mastery

The **Sorting** module introduces the theoretical principles, algorithmic
strategies, correctness arguments, complexity analysis, and Java
implementations used to arrange data according to a defined ordering relation.

Sorting is one of the fundamental problems of Computer Science.

Many algorithms and data structures depend on ordered information or benefit
from it. Sorting may support:

- binary search;
- duplicate detection;
- interval processing;
- ranking;
- reporting;
- data analysis;
- efficient merging;
- ordered traversal;
- selection and aggregation operations.

The objective of this module is not merely to reproduce sorting algorithms in
Java.

Each algorithm is studied as a complete computational solution involving:

- a formally defined problem;
- explicit input and output contracts;
- an ordering relation;
- an algorithmic strategy;
- correctness reasoning;
- time and auxiliary-space analysis;
- Java implementation;
- automated verification;
- controlled performance evaluation when appropriate.

The module also examines the engineering trade-offs that determine why one
sorting algorithm may be appropriate in one context and unsuitable in another.

---

# Academic Foundation

The concepts developed throughout this module are primarily based on
*Introduction to Algorithms* by Cormen, Leiserson, Rivest, and Stein.

They are complemented by recognised Computer Science literature, Java
documentation, and the official references listed in:

```text
docs/00-project/10-references.md
```

The repository does not reproduce textbook descriptions, pseudocode, diagrams,
or implementations mechanically.

Academic concepts are transformed into:

- original explanations;
- formal specifications;
- independent correctness reasoning;
- complexity analysis;
- Java implementations;
- automated tests;
- controlled benchmarks;
- engineering conclusions.

---

# Purpose

The purpose of this module is to develop a rigorous understanding of sorting
from both academic and engineering perspectives.

The learner will study:

- the formal sorting problem;
- ordering relations and sorting keys;
- natural and custom ordering;
- elementary sorting strategies;
- divide-and-conquer sorting;
- partition-based sorting;
- correctness arguments;
- loop invariants;
- recursive reasoning;
- time complexity;
- auxiliary-space complexity;
- stability;
- adaptiveness;
- in-place behaviour;
- Java sorting facilities;
- automated testing;
- experimental benchmarking;
- algorithm-selection criteria.

The objective is to understand not only how each algorithm operates, but also:

```text
Why does it work?

Under which conditions is it correct?

What computational resources does it require?

Which properties does it preserve?

When should it be selected?

Which trade-offs does it introduce?
```

---

# Core Principles

The Sorting module is organised around the following principles:

- define the ordering problem before implementing an algorithm;
- make the ordering relation explicit;
- distinguish algorithm correctness from output appearance;
- analyse stability, mutation, and auxiliary space;
- compare algorithms under clearly defined input conditions;
- verify implementations through automated tests;
- interpret benchmarks alongside theoretical complexity;
- avoid claiming that one sorting algorithm is universally optimal.

These principles guide every document and implementation in the module.

---

# Learning Objectives

After completing this module, the learner should be able to perform the
following activities.

## Problem definition

- define the sorting problem formally;
- identify the collection being ordered;
- define the sorting key;
- distinguish ascending and descending order;
- distinguish natural ordering from comparator-defined ordering;
- define whether the original input may be modified;
- identify the expected output permutation.

## Algorithmic reasoning

- explain the strategies used by different sorting algorithms;
- distinguish selection, exchange, insertion, merging, and partitioning;
- analyse iterative and recursive structures;
- identify algorithm invariants;
- perform manual traces;
- reason about termination;
- compare alternative implementations.

## Correctness reasoning

- explain why the resulting sequence is ordered;
- justify that no input element is lost or duplicated;
- reason about loop invariants;
- reason about recursive subproblems;
- distinguish partial correctness from termination;
- validate permutation preservation.

## Complexity analysis

- analyse best, average, and worst-case behaviour;
- identify significant operations;
- compare quadratic and linearithmic growth;
- analyse auxiliary-space requirements;
- evaluate recursion depth;
- distinguish in-place and out-of-place algorithms;
- relate theoretical complexity to input characteristics.

## Sorting properties

- explain stability;
- identify stable and unstable algorithms;
- explain adaptiveness;
- determine whether an algorithm is in-place;
- analyse the effect of duplicate keys;
- identify the influence of input order;
- understand the importance of comparator consistency.

## Java implementation

- implement sorting algorithms clearly in Java;
- use generic types when appropriate;
- work with `Comparable` and `Comparator`;
- maintain explicit mutation contracts;
- compare academic implementations with Java library facilities;
- avoid unnecessary abstractions;
- design reusable algorithm contracts when they provide value.

## Automated testing

- verify empty and single-element inputs;
- test ordered and reverse-ordered data;
- test duplicated values;
- test negative and repeated values;
- verify permutation preservation;
- verify stability when required;
- test custom comparators;
- compare results with a trusted reference implementation.

## Experimental evaluation

- design meaningful sorting benchmarks;
- distinguish setup from measured execution;
- compare input sizes and distributions;
- interpret JMH results carefully;
- relate observed behaviour to theoretical complexity;
- avoid presenting environment-specific measurements as universal facts.

---

# Why Sorting Matters

Sorting is often not the final objective of a system.

It frequently acts as a transformation that enables subsequent operations.

```text
Unordered Data
        ↓
Sorting
        ↓
Ordered Data
        ↓
Search, Analysis, Grouping, Ranking, or Reporting
```

Ordered data may support:

- binary search;
- merge operations;
- duplicate detection;
- range queries;
- median and percentile analysis;
- ranked output;
- interval algorithms;
- efficient comparison between collections;
- deterministic presentation.

However, sorting also has a computational cost.

The complete engineering decision may involve comparing:

```text
Repeated Linear Searches

versus

One Sorting Operation
        +
Repeated Binary Searches
```

Sorting is useful when its cost is justified by the operations that follow it.

---

# Formal Sorting Problem

A sorting algorithm receives a sequence of elements:

```text
a₁, a₂, a₃, ..., aₙ
```

and produces a permutation:

```text
a'₁, a'₂, a'₃, ..., a'ₙ
```

such that:

```text
a'₁ ≤ a'₂ ≤ a'₃ ≤ ... ≤ a'ₙ
```

for ascending order under the selected ordering relation.

A correct sorting algorithm must satisfy two fundamental properties.

## Ordering property

The resulting sequence must satisfy the defined ordering relation.

```text
a'ᵢ ≤ a'ᵢ₊₁
```

for every valid adjacent position.

## Permutation property

The output must contain exactly the same elements as the input, including the
same multiplicities.

Sorting must not:

- lose elements;
- introduce new elements;
- change duplicate counts;
- replace values accidentally.

Both properties are required.

An ordered result that does not preserve the original elements is not a correct
sorting result.

---

# Ordering Relations

Sorting depends on a defined ordering relation.

The relation may be based on:

- numeric value;
- alphabetical order;
- date;
- length;
- identifier;
- priority;
- one or more object fields;
- a custom business-independent comparison rule.

In Java, ordering may be represented through:

```text
Comparable
        ↓
Natural ordering

Comparator
        ↓
External or custom ordering
```

The comparator must define behaviour consistent enough for the sorting
algorithm and library contract being used.

A sorting algorithm cannot be analysed completely without identifying the
relation that determines whether one element precedes another.

---

# Sorting Properties

Sorting algorithms differ in more than time complexity.

Important properties include the following.

## Stability

A stable algorithm preserves the relative order of elements with equal keys.

Example:

```text
Input:

(Ana, 8)
(Laura, 5)
(María, 8)

Sorted by score:

(Laura, 5)
(Ana, 8)
(María, 8)
```

The relative order of Ana and María is preserved.

Stability is important when:

- records have several fields;
- sorting is performed in multiple stages;
- equal keys contain meaningful original order;
- predictable output is required.

---

## In-place behaviour

An in-place algorithm uses limited additional memory relative to the input.

The exact definition may depend on whether recursion-stack space is included.

In-place behaviour affects:

- auxiliary-space complexity;
- memory requirements;
- mutability;
- API design;
- benchmark interpretation.

---

## Adaptiveness

An adaptive algorithm benefits from existing order in the input.

For example, an optimised insertion sort may perform efficiently on nearly
sorted data.

Adaptiveness makes input distribution relevant to both theoretical and
experimental analysis.

---

## Comparison-based sorting

The algorithms studied in this module are primarily comparison-based.

Their decisions are based on comparisons such as:

```text
less than

greater than

equal to
```

Comparison-based sorting has theoretical lower bounds that become relevant when
analysing scalable algorithms.

Non-comparison sorting methods may be studied in future extensions when their
input assumptions justify them.

---

# Algorithms Studied

The module studies five classical comparison-based sorting algorithms.

| Algorithm | Principal Strategy | Best Case | Average Case | Worst Case | Typical Auxiliary Space |
|-----------|--------------------|-----------|--------------|------------|-------------------------|
| Selection Sort | Repeated selection | Θ(n²) | Θ(n²) | Θ(n²) | Θ(1) |
| Bubble Sort | Adjacent exchange | Θ(n) with early-exit optimisation | Θ(n²) | Θ(n²) | Θ(1) |
| Insertion Sort | Incremental insertion | Θ(n) | Θ(n²) | Θ(n²) | Θ(1) |
| Merge Sort | Divide and conquer | Θ(n log n) | Θ(n log n) | Θ(n log n) | Usually Θ(n) for arrays |
| Quick Sort | Partitioning | Θ(n log n) | Expected Θ(n log n) | Θ(n²) | Depends on recursion and partitioning |

These values depend on the implementation and assumptions documented in each
algorithm file.

The table provides a summary, not a substitute for complete analysis.

---

# Algorithm Classification

The algorithms may be classified by their principal strategy.

```text
Comparison-Based Sorting
│
├── Selection Strategy
│      └── Selection Sort
│
├── Exchange Strategy
│      └── Bubble Sort
│
├── Incremental Construction
│      └── Insertion Sort
│
├── Divide and Conquer
│      └── Merge Sort
│
└── Partitioning
       └── Quick Sort
```

They may also be grouped pedagogically.

```text
Introductory Quadratic Algorithms
│
├── Selection Sort
├── Bubble Sort
└── Insertion Sort

Scalable Divide-and-Conquer Algorithms
│
├── Merge Sort
└── Quick Sort
```

This classification does not imply that one group is universally useful and
the other universally unsuitable.

For example:

- insertion sort may perform well on small or nearly ordered inputs;
- merge sort offers predictable growth but usually requires auxiliary memory;
- quick sort often performs well in practice but depends strongly on
  partitioning and pivot selection;
- selection sort minimises the number of swaps in some implementations;
- bubble sort is educationally useful for studying invariants and adjacent
  exchange.

Algorithm selection always depends on context.

---

# Module Progression

The module follows this conceptual progression:

```text
Sorting Fundamentals
        ↓
Formal Sorting Problem
        ↓
Sorting Properties
        ↓
Selection Sort
        ↓
Bubble Sort
        ↓
Insertion Sort
        ↓
Merge Sort
        ↓
Quick Sort
        ↓
Complexity Analysis
        ↓
Algorithm Comparison
        ↓
Java Sorting Facilities
        ↓
Automated Testing
        ↓
Experimental Benchmarking
        ↓
Interview Review
```

Each stage introduces concepts required to analyse the following documents.

---

# Module Structure

```text
05-sorting/
├── README.md
├── 01-sorting-fundamentals.md
├── 02-sorting-problem-definition.md
├── 03-sorting-properties.md
├── 04-selection-sort.md
├── 05-bubble-sort.md
├── 06-insertion-sort.md
├── 07-merge-sort.md
├── 08-quick-sort.md
├── 09-sorting-complexity.md
├── 10-algorithm-comparison.md
├── 11-java-sorting-framework.md
├── 12-testing-sorting-algorithms.md
├── 13-benchmarking-sorting-algorithms.md
└── 14-common-sorting-interview-problems.md
```

The sequence progresses from foundational definitions to individual algorithms,
comparative analysis, Java facilities, verification, experimental evaluation,
and structured review.

---

# Document Overview

## `01-sorting-fundamentals.md`

Introduces sorting as a computational problem.

The document establishes:

- sorting terminology;
- ordered and unordered sequences;
- ascending and descending order;
- sorting keys;
- natural and custom order;
- comparison-based sorting;
- the role of sorting in algorithm design.

---

## `02-sorting-problem-definition.md`

Defines the sorting problem formally.

The document identifies:

- inputs;
- outputs;
- ordering relations;
- permutation requirements;
- preconditions;
- postconditions;
- mutation contracts;
- invalid inputs;
- duplicate handling;
- comparator expectations.

The specification becomes the reference for implementation and testing.

---

## `03-sorting-properties.md`

Studies properties used to compare sorting algorithms.

Topics include:

- stability;
- adaptiveness;
- in-place behaviour;
- online and offline processing;
- comparison count;
- swap count;
- auxiliary memory;
- recursive depth;
- input sensitivity.

These properties complement asymptotic time complexity.

---

## `04-selection-sort.md`

Studies Selection Sort.

The document analyses:

- repeated minimum or maximum selection;
- sorted and unsorted partitions;
- loop invariants;
- correctness;
- fixed quadratic comparison growth;
- swap behaviour;
- in-place implementation;
- stability considerations;
- Java implementation;
- automated tests.

---

## `05-bubble-sort.md`

Studies Bubble Sort.

The document analyses:

- adjacent comparisons;
- adjacent exchanges;
- pass-based reasoning;
- loop invariants;
- early-exit optimisation;
- adaptive behaviour;
- stability;
- quadratic growth;
- Java implementation;
- automated tests.

---

## `06-insertion-sort.md`

Studies Insertion Sort.

The document analyses:

- incremental construction;
- insertion into an ordered prefix;
- loop invariants;
- shifting operations;
- adaptiveness;
- stability;
- nearly sorted inputs;
- quadratic worst-case behaviour;
- Java implementation;
- automated tests.

---

## `07-merge-sort.md`

Studies Merge Sort.

The document introduces:

- divide and conquer;
- recursive decomposition;
- base cases;
- merging ordered subsequences;
- correctness reasoning;
- recurrence relations;
- Θ(n log n) time complexity;
- auxiliary-space requirements;
- stability;
- Java implementation;
- automated tests.

---

## `08-quick-sort.md`

Studies Quick Sort.

The document introduces:

- partitioning;
- pivot selection;
- recursive subproblems;
- partition invariants;
- expected performance;
- worst-case behaviour;
- recursion depth;
- in-place considerations;
- Java implementation;
- automated tests.

Different partitioning strategies should be identified explicitly when they
affect correctness or performance.

---

## `09-sorting-complexity.md`

Develops a comparative analysis of sorting complexity.

The document examines:

- comparison counts;
- swap and shift counts;
- best, average, and worst cases;
- quadratic growth;
- linearithmic growth;
- recurrence relations;
- auxiliary space;
- recursion depth;
- input sensitivity;
- theoretical lower bounds for comparison sorting.

---

## `10-algorithm-comparison.md`

Compares the algorithms using multiple criteria.

Possible criteria include:

| Criterion | Question |
|-----------|----------|
| Time complexity | How does execution grow with input size? |
| Auxiliary space | How much additional memory is required? |
| Stability | Are equal keys kept in relative order? |
| Adaptiveness | Does existing order improve behaviour? |
| In-place operation | Is the original collection modified with limited extra memory? |
| Input sensitivity | Do specific distributions affect performance? |
| Implementation complexity | How difficult is the algorithm to implement and verify? |
| Practical context | Under which conditions is the algorithm appropriate? |

The document should avoid declaring one universal winner.

---

## `11-java-sorting-framework.md`

Studies sorting facilities provided by Java.

Relevant topics may include:

- `Arrays.sort`;
- `Arrays.parallelSort`;
- `Collections.sort`;
- `List.sort`;
- `Comparable`;
- `Comparator`;
- primitive arrays;
- object arrays;
- stable and unstable implementation guarantees;
- natural and custom ordering;
- library contracts.

The objective is to compare academic implementations with standard-library
facilities without replacing understanding of the underlying algorithms.

---

## `12-testing-sorting-algorithms.md`

Defines the verification strategy for sorting implementations.

Tests should examine:

- empty input;
- single-element input;
- already ordered input;
- reverse-ordered input;
- duplicate values;
- repeated equal values;
- negative values;
- custom comparators;
- mutation behaviour;
- ordering correctness;
- permutation preservation;
- stability when required;
- agreement with a trusted reference.

Testing should verify observable behaviour rather than private implementation
details.

---

## `13-benchmarking-sorting-algorithms.md`

Defines controlled performance experiments using JMH.

Possible comparisons include:

- increasing input sizes;
- ordered input;
- reverse-ordered input;
- random input;
- nearly ordered input;
- duplicate-heavy input;
- algorithm variants;
- academic implementations and Java library facilities.

The benchmark design should document:

- input generation;
- state scope;
- setup;
- warm-up;
- measurement;
- forks;
- Java version;
- execution environment;
- interpretation limitations.

Benchmarking is used only when it answers a defined experimental question.

---

## `14-common-sorting-interview-problems.md`

Provides a structured review of sorting concepts commonly examined in technical
interviews.

Possible topics include:

- identifying appropriate sorting strategies;
- explaining stability;
- comparing Merge Sort and Quick Sort;
- sorting custom objects;
- analysing nearly ordered input;
- tracing partitioning;
- identifying quadratic behaviour;
- solving interval or ranking problems;
- determining whether sorting is required at all.

This document is a review resource and does not replace the academic study
contained in the previous documents.

---

# Relationship with Previous Modules

Sorting builds upon concepts established earlier in the repository.

```text
Project Documentation
        ↓
Foundations
        ↓
Complexity
        ↓
Arrays
        ↓
Searching
        ↓
Sorting
```

The previous modules provide:

- formal problem specification;
- preconditions and postconditions;
- correctness reasoning;
- invariants;
- asymptotic analysis;
- array operations;
- traversal patterns;
- binary search;
- input-ordering assumptions.

Sorting applies and extends these concepts through algorithms that transform the
organisation of data.

---

# Relationship with Searching

Sorting and searching are closely related.

Binary Search requires ordered input.

Therefore, a complete decision may compare:

```text
Unordered Data
        ↓
Linear Search
```

with:

```text
Unordered Data
        ↓
Sorting
        ↓
Binary Search
```

Sorting before one search may be more expensive than performing Linear Search.

Sorting may become advantageous when:

- many searches will be performed;
- ordered output is already required;
- range queries are needed;
- duplicates must be grouped;
- merging operations will follow;
- the sorting cost can be reused.

The cost of preprocessing must be included in the engineering analysis.

---

# Relationship with Later Modules

Sorting prepares the learner for several later subjects.

```text
Sorting
        ↓
Heaps

Sorting
        ↓
Trees

Sorting
        ↓
Greedy Algorithms

Sorting
        ↓
Divide and Conquer

Sorting
        ↓
Dynamic Programming
```

Examples include:

- Heap Sort and priority queues;
- ordered tree traversal;
- edge sorting in graph algorithms;
- interval scheduling;
- event processing;
- median and selection problems;
- divide-and-conquer recurrences.

Sorting strategies therefore reappear throughout advanced algorithm design.

---

# Relationship with Java

Sorting is studied first as a language-independent computational problem and
then represented in Java.

The module distinguishes between:

```text
Sorting Concept
        ↓
Algorithmic strategy and mathematical properties

Academic Java Implementation
        ↓
Executable representation developed for study

Java Standard Library
        ↓
Production-oriented sorting facilities
```

Relevant Java concepts include:

- arrays;
- generic types;
- `Comparable`;
- `Comparator`;
- mutation;
- primitive and reference types;
- method references;
- collection APIs.

Academic implementations provide visibility into the algorithm.

Standard-library facilities provide documented, reusable implementations for
software development.

Both should be studied through their different responsibilities.

---

# Relationship with Automated Testing

A sorting test must verify more than whether the output appears ordered.

Correctness requires both:

```text
Ordering Property
        +
Permutation Property
```

Representative test categories include:

- empty input;
- one element;
- two elements;
- ordered input;
- reverse-ordered input;
- duplicates;
- all-equal values;
- negative values;
- custom objects;
- ascending and descending comparators;
- stability;
- mutation behaviour.

A useful reference strategy may compare the result with a trusted Java sorting
operation.

However, tests do not replace general correctness reasoning.

---

# Relationship with Benchmarking

Sorting provides a suitable domain for studying the relationship between
asymptotic theory and observed performance.

Possible benchmark questions include:

```text
How does input size affect each algorithm?

How does existing order affect adaptive algorithms?

How does pivot selection affect Quick Sort?

When does Merge Sort's auxiliary memory become relevant?

How do academic implementations compare with Java library methods?
```

Benchmarking should be introduced only after:

- the implementation is correct;
- tests pass;
- the complexity has been analysed;
- the experimental question is defined.

JMH benchmarks belong under:

```text
src/jmh/java/org/anaalvarez/algorithms/sorting/
```

Results apply only to the documented inputs, environment, Java version, and JMH
configuration.

---

# Study Methodology

Each sorting algorithm should be studied through the repository methodology.

```text
Academic Study
        ↓
Sorting Problem Definition
        ↓
Ordering Contract
        ↓
Strategy Design
        ↓
Manual Trace
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Experimental Evaluation
        when appropriate
        ↓
Engineering Conclusions
```

This process ensures that implementation follows understanding and that
performance conclusions remain supported by theoretical and experimental
evidence.

---

# Engineering Perspective

Sorting decisions in software systems involve more than asymptotic notation.

The appropriate choice may depend on:

- data size;
- initial order;
- duplicate distribution;
- memory availability;
- mutation restrictions;
- stability requirements;
- comparator cost;
- number of repeated operations;
- parallel-processing opportunities;
- library guarantees;
- maintainability requirements.

Production software normally relies on tested library implementations unless a
specialised requirement justifies a custom algorithm.

The educational implementations in this repository exist to make the
strategies, invariants, trade-offs, and complexity visible.

---

# Academic References

The principal academic reference for this module is:

- Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; Stein,
  Clifford. *Introduction to Algorithms*. Fourth Edition. MIT Press, 2022.

Additional supporting references include:

- Knuth, Donald E. *The Art of Computer Programming, Volume 3: Sorting and
  Searching*. Addison-Wesley.

- Sedgewick, Robert; Wayne, Kevin. *Algorithms*. Fourth Edition.
  Addison-Wesley Professional, 2011.

- Weiss, Mark Allen. *Data Structures and Algorithm Analysis in Java*.
  Pearson.

- Oracle. *Java Platform API Specification*.

- OpenJDK. *Java Microbenchmark Harness — JMH*.

The complete project bibliography is maintained in:

```text
docs/00-project/10-references.md
```

Official Java documentation should be consulted according to the Java version
configured in `pom.xml`.

---

# Expected Outcome

After completing this module, the learner should be able to:

```text
Define the sorting problem
        ↓
Establish the ordering contract
        ↓
Identify relevant sorting properties
        ↓
Select an algorithmic strategy
        ↓
Reason about correctness
        ↓
Analyse time and auxiliary space
        ↓
Implement the algorithm in Java
        ↓
Verify ordering and permutation preservation
        ↓
Evaluate performance when appropriate
        ↓
Justify the engineering decision
```

The learner should recognise that no sorting algorithm is universally optimal.

Algorithm selection depends on:

- input characteristics;
- required ordering;
- stability;
- memory constraints;
- mutation policy;
- expected workload;
- implementation environment;
- performance requirements.

The objective is not to memorise isolated algorithms, but to develop the ability
to analyse, compare, implement, verify, and select sorting strategies
systematically.

---

# Key Takeaways

- Sorting transforms data according to a defined ordering relation.
- Correctness requires both ordering and permutation preservation.
- Sorting algorithms use different strategies and have different properties.
- Stability, adaptiveness, and auxiliary space matter alongside time
  complexity.
- Input organisation can substantially affect practical behaviour.
- Java library methods should be understood through their documented contracts.
- Automated testing verifies representative behaviours.
- Benchmarking complements but does not replace theoretical analysis.
- Algorithm selection must be based on context rather than memorisation.

---

# Next Document

```text
01-sorting-fundamentals.md
```

The next document introduces sorting as a computational problem and establishes
the terminology required to study ordering relations, sorting keys, natural
ordering, custom comparators, algorithm properties, and sorting strategies.