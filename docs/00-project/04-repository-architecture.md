# Repository Architecture

## Algorithms Java Mastery

This document defines the repository architecture adopted throughout
**Algorithms Java Mastery**.

The project is inspired primarily by **Introduction to Algorithms (CLRS)** and
uses a layered learning architecture that connects academic study with modern
Java engineering practice.

The repository is intentionally organised so that theoretical understanding
precedes implementation and every important implementation can be traced back
to its conceptual foundations.

The central architectural relationship is:

```text
Academic Source
      ↓
Conceptual Documentation
      ↓
Problem Specification
      ↓
Correctness Reasoning
      ↓
Complexity Analysis
      ↓
Java Implementation
      ↓
Automated Testing
      ↓
Empirical Benchmarking
      ↓
Engineering Conclusions
```

The architecture therefore represents a learning system rather than a simple
collection of source files.

---

# 1. Purpose

The purpose of this document is to define:

- the responsibilities of the major repository directories;
- the relationship between documentation and source code;
- the organisation of production code, tests, and benchmarks;
- package naming conventions;
- module traceability rules;
- repository growth principles;
- the architectural boundaries that preserve the educational purpose of the
  project.

This document describes **where project artefacts belong and how they relate to
one another**.

Documentation-specific writing and organisation rules are defined separately in:

```text
docs/00-project/06-documentation-architecture.md
```

---

# 2. Architectural Principles

The repository follows several principles.

## Theory Before Implementation

Algorithms should be understood before being translated into Java.

```text
Study
  ↓
Reason
  ↓
Analyse
  ↓
Implement
```

## Traceability

Important implementations should be traceable to:

- the module that introduces the concept;
- the problem specification;
- the correctness argument;
- the complexity analysis;
- the corresponding tests;
- relevant benchmarks.

## Separation of Responsibilities

The repository separates:

- academic documentation;
- production implementations;
- automated tests;
- empirical benchmarks;
- build configuration.

Each layer answers a different engineering question.

## Progressive Learning

Modules are ordered according to conceptual dependency rather than according to
implementation convenience.

## Minimal Abstraction

Abstractions and design patterns should be introduced only when they clarify a
real variation or responsibility.

The implementation of an algorithm should remain easy to inspect and reason
about.

---

# 3. Top-Level Structure

The current repository structure is:

```text
Algorithms-Java-Mastery/
│
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
│
├── docs/
│
├── src/
│   ├── main/
│   ├── test/
│   └── jmh/
│
├── .gitignore
├── CHANGELOG.md
├── CONTRIBUTING.md
├── README.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```

The top-level architecture separates project configuration from learning
artefacts.

---

# 4. Documentation Layer

The `docs/` directory contains the academic and engineering knowledge of the
project.

The current module sequence is:

```text
docs/
├── 00-project/
├── 01-foundations/
├── 02-complexity/
├── 03-arrays/
├── 04-searching/
├── 05-sorting/
├── 06-linked-lists/
├── 07-stacks-and-queues/
├── 08-hash-tables/
├── 09-trees/
├── 10-graphs/
├── 11-recursion/
├── 12-divide-and-conquer/
├── 13-greedy-algorithms/
├── 14-backtracking/
├── 15-dynamic-programming/
├── 16-testing/
├── 17-benchmarking/
├── 18-linux-environment/
└── 19-ci-cd/
```

The numbering expresses the intended educational progression.

It should not be interpreted as a requirement to finish all documentation
before applying earlier modules in code.

---

# 5. Project Documentation

`docs/00-project/` defines the repository-wide architecture and engineering
rules.

Its responsibilities include:

- project vision;
- learning objectives;
- study methodology;
- repository architecture;
- technology stack;
- documentation architecture;
- Java project structure;
- engineering standards;
- development workflow;
- central academic references.

These documents apply across all later modules.

---

# 6. Study Modules

Modules from `01-foundations/` through `15-dynamic-programming/` develop
Computer Science knowledge progressively.

A study module should normally connect:

```text
Definition
    ↓
Properties
    ↓
Problem Model
    ↓
Algorithmic Strategy
    ↓
Correctness
    ↓
Complexity
    ↓
Implementation
    ↓
Testing
```

Not every theoretical document requires its own Java class.

Implementation should be created only where executable behaviour adds learning
value.

---

# 7. Engineering Modules

The final modules provide cross-cutting engineering knowledge.

```text
16-testing
17-benchmarking
18-linux-environment
19-ci-cd
```

These modules are studied explicitly later in the documentation sequence, but
their practices may be introduced earlier whenever the project benefits from
them.

For example:

- JUnit and AssertJ are already used while studying arrays;
- JMH is introduced for meaningful empirical comparisons;
- Maven supports every module;
- CI can validate the repository continuously.

---

# 8. Java Source Architecture

Production implementations are located under:

```text
src/main/java/
```

The project namespace is:

```text
org.anaalvarezdev.algorithms
```

Algorithm-specific packages extend this namespace.

For example:

```text
src/main/java/org/anaalvarezdev/algorithms/
└── arrays/
```

As the project progresses, additional packages may be introduced for searching,
sorting, linked structures, trees, graphs, and algorithmic paradigms.

Packages should follow the computational domain rather than design-pattern
names.

Prefer:

```text
algorithms/sorting/
algorithms/searching/
algorithms/graphs/
```

over a global package such as:

```text
algorithms/patterns/
```

unless a genuine cross-cutting abstraction justifies it.

---

# 9. Test Architecture

Automated tests mirror the production package structure.

```text
src/test/java/org/anaalvarezdev/algorithms/
```

For example:

```text
src/main/java/org/anaalvarezdev/algorithms/arrays/FindMaximum.java
                              ↓
src/test/java/org/anaalvarezdev/algorithms/arrays/FindMaximumTest.java
```

Tests provide executable evidence that a Java implementation behaves according
to its contract for selected cases.

They complement correctness reasoning; they do not replace it.

---

# 10. Benchmark Architecture

JMH benchmarks are located under:

```text
src/jmh/java/org/anaalvarezdev/algorithms/
```

Benchmarks should be created only when they answer a meaningful empirical
question.

For example:

```text
src/main/java/org/anaalvarezdev/algorithms/arrays/PrefixSum.java
                              ↓
src/jmh/java/org/anaalvarezdev/algorithms/arrays/PrefixSumBenchmark.java
```

A benchmark is not required merely because an implementation exists.

Theoretical complexity analysis remains conceptually distinct from empirical
measurement.

---

# 11. Module Traceability

The preferred traceability chain is:

```text
CLRS / Academic Source
        ↓
docs/<module>/
        ↓
Problem Specification
        ↓
Correctness Argument
        ↓
Complexity Analysis
        ↓
src/main/
        ↓
src/test/
        ↓
src/jmh/   when relevant
```

For a concrete example:

```text
docs/03-arrays/
        ↓
Find maximum problem
        ↓
Loop invariant
        ↓
Θ(n) time / Θ(1) auxiliary space
        ↓
src/main/java/org/anaalvarezdev/algorithms/arrays/FindMaximum.java
        ↓
src/test/java/org/anaalvarezdev/algorithms/arrays/FindMaximumTest.java
        ↓
src/jmh/java/org/anaalvarezdev/algorithms/arrays/FindMaximumBenchmark.java
```

This chain is central to the repository's educational identity.

---

# 12. Relationship with CLRS

**Introduction to Algorithms** provides the principal academic inspiration for
the repository.

The repository does not attempt to reproduce the book.

Instead, it uses the book as a foundation for studying concepts, reasoning
about algorithms, and translating that understanding into:

- original technical explanations;
- Java 21 implementations;
- JUnit and AssertJ tests;
- JMH experiments;
- engineering documentation.

Where CLRS introduces a proof technique, data structure, algorithm, or
complexity model relevant to a module, that relationship should be visible in
the module documentation and references.

Complementary sources may be used to clarify Java-specific or engineering
concerns.

---

# 13. Arrays as the First Complete Implementation Module

Arrays provide the first substantial implementation environment in the
repository.

The current package demonstrates the complete architectural relationship:

```text
docs/01-foundations/
        +
docs/02-complexity/
        +
docs/03-arrays/
        ↓
src/main/java/org/anaalvarezdev/algorithms/arrays/
        ↓
src/test/java/org/anaalvarezdev/algorithms/arrays/
        ↓
src/jmh/java/org/anaalvarezdev/algorithms/arrays/
```

This model should guide later implementation modules.

---

# 14. Design Patterns

Design patterns may be introduced selectively.

For this repository, patterns are secondary to algorithmic clarity.

Appropriate examples may include:

- Strategy for interchangeable algorithm families;
- Factory when construction genuinely varies;
- Builder for complex test or graph data;
- Decorator for educational instrumentation;
- Observer for optional execution tracing.

Patterns should not obscure the algorithm or create artificial hierarchies.

---

# 15. Repository Growth Rules

When adding a new implementation:

1. identify the corresponding study module;
2. confirm that the problem and contract are understood;
3. document or reference the correctness reasoning;
4. determine time and auxiliary-space complexity;
5. implement the algorithm in the corresponding production package;
6. add focused automated tests;
7. add a benchmark only when an empirical question exists;
8. preserve package symmetry between production and test code;
9. update module navigation or traceability when necessary.

New directories should not be created solely for organisational aesthetics.

Every architectural element should have a clear responsibility.

---

# 16. Architectural Boundaries

The following separations should remain explicit:

```text
Correctness          ≠ Testing
Complexity Analysis  ≠ Benchmarking
Algorithm Design     ≠ Java Syntax
Academic Source      ≠ Repository Documentation
Implementation       ≠ Experimental Evidence
```

These distinctions are essential to the learning methodology.

---

# 17. Expected Outcome

The repository architecture should allow a learner or reviewer to answer:

- Where is this concept explained?
- Which source influenced the explanation?
- Where is the algorithm implemented?
- Where is its behaviour tested?
- Where is its complexity discussed?
- Is there a benchmark, and if so, what question does it answer?
- How does this implementation connect to the larger learning path?

If these relationships remain visible, the repository preserves both academic
rigour and engineering traceability.

---

# References

Repository-wide academic and technical references are maintained in:

```text
docs/00-project/10-references.md
```

The primary academic inspiration is:

- Cormen, T. H., Leiserson, C. E., Rivest, R. L., and Stein, C.
  *Introduction to Algorithms*. MIT Press.

Module-specific references should be maintained in the corresponding module
`README.md` according to the documentation architecture.
