# Java Project Structure

## Algorithms Java Mastery

This document defines the Java project structure adopted by
**Algorithms Java Mastery**.

The repository organises source code according to computational domains and
academic study modules.

Java implementations represent the executable result of algorithmic analysis,
formal specification, correctness reasoning, and complexity evaluation.

The project structure therefore connects theoretical knowledge with
implementation, automated verification, and experimental performance analysis
while remaining clear, maintainable, and scalable.

---

# Purpose

This document establishes:

- the organisation of Java source code;
- the package hierarchy;
- package and class responsibilities;
- the location of tests and benchmarks;
- dependency directions;
- naming conventions;
- scalability principles;
- the relationship between documentation and executable code.

The objective is to provide a consistent structural model for every algorithm,
data structure, test, and benchmark developed in the repository.

---

# Structural Philosophy

The Java project follows a domain-oriented and responsibility-based structure.

Source code is organised around computational concepts rather than business
features or application layers.

```text
Academic Module
        ↓
Computational Domain
        ↓
Java Implementation
        ↓
Automated Verification
        ↓
Experimental Evaluation
```

Each package represents a defined area of algorithmic study.

The structure should make it possible to locate the theory, implementation,
tests, and benchmarks associated with a topic without unnecessary complexity.

---

# Maven Project Layout

The project adopts Maven conventions for production code and automated tests.

```text
src/
├── main/
│   └── java/
├── test/
│   └── java/
└── jmh/
    └── java/
```

The responsibilities of these directories are:

| Directory | Responsibility |
|-----------|----------------|
| `src/main/java/` | Algorithm and data structure implementations |
| `src/test/java/` | Automated correctness and behaviour tests |
| `src/jmh/java/` | JMH performance benchmarks |

Using established conventions improves compatibility with Maven, IntelliJ IDEA,
testing tools, benchmark plugins, and continuous integration environments.

The project build must remain executable independently of the IDE.

---

# Base Package

All Java code belongs to the following base package:

```text
org.anaalvarez.algorithms
```

This package identifies the project namespace and contains all computational
modules.

Production code, tests, and benchmarks should use the same base-package
hierarchy.

```text
src/main/java/org/anaalvarez/algorithms/
src/test/java/org/anaalvarez/algorithms/
src/jmh/java/org/anaalvarez/algorithms/
```

Maintaining the same hierarchy across source sets improves traceability and
navigation.

---

# Production Source Organisation

Production code is located under:

```text
src/main/java/org/anaalvarez/algorithms/
```

Packages are organised according to the principal study modules.

```text
org/anaalvarez/algorithms/

├── foundations/
├── complexity/
├── arrays/
├── searching/
├── sorting/
├── linkedstructures/
├── stacksqueues/
├── hashing/
├── trees/
├── heaps/
├── graphs/
├── recursion/
├── divideandconquer/
├── greedy/
├── dynamicprogramming/
└── common/
```

Not every documentation module requires executable Java code.

For example, conceptual subjects such as asymptotic notation may remain
primarily documented unless an implementation or experiment provides clear
educational value.

---

# Package Responsibilities

Each package represents one computational domain.

| Package | Responsibility |
|---------|----------------|
| `foundations` | Fundamental algorithmic concepts and introductory examples |
| `complexity` | Operation-counting examples and complexity experiments |
| `arrays` | Array operations, traversal patterns, and array algorithms |
| `searching` | Linear, binary, and related searching algorithms |
| `sorting` | Comparison-based and non-comparison sorting algorithms |
| `linkedstructures` | Linked data structures and their operations |
| `stacksqueues` | Stack, queue, and deque implementations and applications |
| `hashing` | Hash tables, hashing strategies, and collision handling |
| `trees` | Tree structures, traversal, searching, and modification |
| `heaps` | Heap structures and priority-based algorithms |
| `graphs` | Graph representations, traversal, and graph algorithms |
| `recursion` | Recursive algorithms and recurrence-oriented examples |
| `divideandconquer` | Algorithms based on problem decomposition |
| `greedy` | Greedy strategies and their applicability |
| `dynamicprogramming` | Memoisation, tabulation, and optimal-substructure problems |
| `common` | Small components with verified cross-module reuse |

Packages should not combine unrelated computational concepts.

---

# Relationship with Documentation Modules

The Java package hierarchy should remain traceable to the academic documentation.

For example:

```text
docs/05-sorting/
        ↓
src/main/java/org/anaalvarez/algorithms/sorting/
        ↓
src/test/java/org/anaalvarez/algorithms/sorting/
        ↓
src/jmh/java/org/anaalvarez/algorithms/sorting/
```

The relationship does not require identical file names.

However, the documentation should clearly identify the corresponding classes,
tests, and benchmarks whenever they exist.

This traceability allows the reader to move from theory to executable evidence.

---

# Internal Package Organisation

A computational package may contain subpackages when the number of
implementations or responsibilities justifies them.

For example:

```text
sorting/
├── comparison/
│   ├── BubbleSort.java
│   ├── InsertionSort.java
│   ├── MergeSort.java
│   └── QuickSort.java
│
├── noncomparison/
│   ├── CountingSort.java
│   └── RadixSort.java
│
└── api/
    └── SortingAlgorithm.java
```

Subpackages should be introduced only when they improve organisation.

Small modules should remain simple rather than adopting unnecessary structural
layers.

---

# Algorithm Abstractions

Interfaces may be introduced when multiple implementations share a meaningful
contract.

For example:

```java
public interface SortingAlgorithm<T extends Comparable<? super T>> {

    void sort(T[] values);
}
```

An abstraction should provide a clear educational or engineering benefit, such
as:

- comparing multiple algorithms consistently;
- enabling reusable tests;
- supporting benchmark parametrisation;
- applying the Strategy pattern;
- separating a contract from its implementations.

Interfaces should not be created merely to increase abstraction.

A single independent algorithm may remain a concrete class or utility method
when no shared contract is necessary.

---

# Class Responsibilities

Each class should represent one primary concept.

Typical class responsibilities include:

- one algorithm;
- one data structure;
- one reusable algorithmic strategy;
- one input validator;
- one clearly defined support component.

Examples:

```text
LinearSearch
BinarySearch
MergeSort
QuickSort
BinarySearchTree
AdjacencyListGraph
```

Classes should not combine unrelated algorithms or become general-purpose
containers for miscellaneous methods.

---

# Implementation Style

Algorithm implementations should make the studied strategy visible.

Code should prioritise:

- correctness;
- readability;
- correspondence with the documented algorithm;
- explicit handling of assumptions;
- appropriate generic types;
- predictable behaviour;
- testability.

Optimisation should not obscure the central algorithmic idea unless the
optimisation itself is the subject of study.

When multiple variants are relevant, they should be represented separately.

For example:

```text
RecursiveBinarySearch
IterativeBinarySearch
```

This makes comparison and evaluation explicit.

---

# Static Methods and Instantiable Classes

Stateless algorithms may be implemented using static methods when this provides
the clearest design.

Example:

```java
public final class LinearSearch {

    private LinearSearch() {
        throw new AssertionError("Utility class");
    }

    public static <T> int search(T[] values, T target) {
        // Implementation
    }
}
```

Instantiable classes are appropriate when an algorithm:

- maintains configuration;
- depends on a strategy;
- records state;
- participates in a shared abstraction;
- requires interchangeable behaviour.

The selected form should reflect the responsibility of the implementation
rather than a universal rule.

---

# Data Structure Organisation

A data structure should encapsulate its internal representation and operations.

For example:

```text
trees/
└── binarysearchtree/
    ├── BinarySearchTree.java
    └── TreeNode.java
```

Internal nodes and implementation details should not be exposed unless doing so
supports a defined educational objective.

Public operations should represent the documented contract of the data
structure.

---

# The `common` Package

The `common` package contains components that are genuinely reused by multiple
computational modules.

Possible examples include:

- shared precondition validators;
- general comparison utilities;
- reusable result types;
- common mathematical helpers.

A component should be placed in `common` only after actual reuse has been
identified.

Module-specific helpers should remain within their own packages.

The package must not become a miscellaneous collection of unrelated classes.

---

# Test Source Organisation

Automated tests are located under:

```text
src/test/java/org/anaalvarez/algorithms/
```

The test hierarchy mirrors the production hierarchy.

```text
src/main/java/org/anaalvarez/algorithms/sorting/MergeSort.java

src/test/java/org/anaalvarez/algorithms/sorting/MergeSortTest.java
```

This symmetry improves discoverability and maintains a clear relationship
between implementation and verification.

---

# Test Class Convention

A test class should normally correspond to one production class or one defined
behavioural contract.

```text
BinarySearch.java
        ↓
BinarySearchTest.java
```

Test names should communicate the behaviour being verified.

Examples:

```java
returnsExpectedIndexWhenTargetExists()
returnsNotFoundWhenTargetIsAbsent()
handlesEmptyInput()
rejectsNullInput()
sortsValuesContainingDuplicates()
```

Tests should verify observable behaviour rather than internal implementation
details.

---

# Reusable Test Contracts

When several implementations satisfy the same interface, reusable test
contracts may be used.

For example, every sorting algorithm may be validated against the same
behavioural expectations:

- sorts an empty array;
- sorts a single-element array;
- sorts an already ordered array;
- sorts reverse-ordered input;
- preserves duplicate values;
- produces the same ordering as a trusted reference.

Reusable tests should reduce duplication without hiding the behaviour being
verified.

---

# Benchmark Source Organisation

JMH benchmarks are located under:

```text
src/jmh/java/org/anaalvarez/algorithms/
```

The benchmark hierarchy should mirror the production package whenever practical.

Example:

```text
src/main/java/org/anaalvarez/algorithms/sorting/MergeSort.java

src/jmh/java/org/anaalvarez/algorithms/sorting/MergeSortBenchmark.java
```

Benchmark classes are experimental clients of production implementations.

They must not contain the authoritative implementation of an algorithm.

---

# Benchmark Responsibilities

A benchmark should define:

- the implementation being measured;
- the input characteristics;
- the input size;
- the data distribution;
- the benchmark state;
- the measured operation;
- the interpretation limits of the results.

Benchmarks may compare:

- algorithm variants;
- input sizes;
- data distributions;
- recursive and iterative solutions;
- theoretical expectations and observed behaviour.

Benchmark code must remain separate from correctness tests.

A faster execution result does not prove correctness.

---

# Dependency Direction

The project maintains a simple dependency direction.

```text
Documentation
        ↓
Explains production implementations

Production Code
        ↑                 ↑
        │                 │
Automated Tests      JMH Benchmarks
```

The dependency rules are:

- production code must not depend on test code;
- production code must not depend on benchmark code;
- tests depend on production code;
- benchmarks depend on production code;
- tests and benchmarks should remain independent of one another;
- documentation does not participate in runtime dependencies.

Dependencies between computational packages should remain minimal and
justifiable.

---

# Inter-Package Dependencies

Algorithms should remain independent whenever possible.

For example, a sorting algorithm should not depend on a graph package.

Dependencies between packages are acceptable when they represent a genuine
computational relationship.

Examples include:

- graph algorithms depending on a graph representation;
- heap sort depending on a heap abstraction;
- reusable tests depending on an algorithm interface;
- benchmark classes depending on implementations under evaluation.

Cross-package reuse should not create hidden coupling between unrelated study
modules.

---

# Visibility

Java visibility should be restricted to the minimum level required.

- `public` exposes a documented project contract.
- package-private supports collaboration within a computational module.
- `protected` is used only when inheritance is intentional.
- `private` protects implementation details.

Not every class or method should be public.

Internal components should remain encapsulated unless external access provides a
clear educational or engineering benefit.

---

# Naming Conventions

Java names should follow established language conventions.

| Element | Convention | Example |
|---------|------------|---------|
| Package | Lowercase | `dynamicprogramming` |
| Class | PascalCase | `BinarySearch` |
| Interface | PascalCase | `SortingAlgorithm` |
| Method | camelCase | `findMinimum()` |
| Variable | camelCase | `targetIndex` |
| Constant | Upper snake case | `DEFAULT_CAPACITY` |
| Test class | Production name plus `Test` | `MergeSortTest` |
| Benchmark class | Production name plus `Benchmark` | `MergeSortBenchmark` |

Names should describe intent rather than implementation mechanics.

Avoid unclear abbreviations such as:

```text
arr
tmp
obj
alg
util2
```

Short conventional names such as `i`, `j`, or `n` may be used in small,
well-defined algorithmic contexts.

---

# File Naming

Each top-level Java type should normally reside in a file with the same name.

```text
BinarySearch.java
BinarySearchTest.java
BinarySearchBenchmark.java
```

File names should remain stable unless the represented responsibility changes.

Generic names such as the following should be avoided:

```text
Algorithms.java
Helpers.java
Utils.java
Miscellaneous.java
```

Such names usually indicate an unclear responsibility.

---

# Generic Types

Generic types should be used when an implementation is conceptually independent
of a specific data type.

For example:

```java
public static <T extends Comparable<? super T>> void sort(T[] values)
```

Primitive-specific implementations may also be created when:

- the educational topic requires them;
- performance comparison is relevant;
- avoiding boxing is part of the analysis;
- the algorithm depends on numeric properties.

The choice between generic and primitive-specific implementations should be
documented when it affects behaviour or performance.

---

# Input Contracts

Public implementations should define their input contracts clearly.

The contract should establish:

- whether `null` values are accepted;
- whether input is modified;
- whether ordering is required;
- how duplicate values are handled;
- how absence is represented;
- which exceptions may be produced;
- the expected output.

These decisions should remain consistent between documentation, code, and tests.

---

# Mutability

Algorithms that modify their input should make this behaviour explicit.

For example:

```text
In-place sorting
        ↓
Modifies the original structure

Out-of-place sorting
        ↓
Produces a separate result
```

Mutability decisions affect:

- correctness;
- auxiliary-space complexity;
- API design;
- test expectations;
- benchmark interpretation.

The implementation should not introduce hidden mutation.

---

# Error Handling

Error handling should reflect the documented contract.

Possible approaches include:

- rejecting invalid input with an appropriate exception;
- returning a defined sentinel value;
- returning an optional result;
- accepting an empty structure as a valid input.

Different algorithms may require different policies.

However, comparable implementations should behave consistently unless the
difference is itself part of the study.

---

# Scalability

The project structure supports progressive growth.

New computational domains may be added under the base package when they
represent a distinct subject.

For example:

```text
algorithms/
├── backtracking/
├── strings/
├── geometry/
├── numbertheory/
└── randomised/
```

A new package should be introduced only when:

- it represents a defined area of study;
- it contains or is expected to contain meaningful content;
- it does not duplicate an existing package responsibility;
- its addition aligns with the documented project scope.

Empty package structures should not be created merely to anticipate every
possible future topic.

---

# Structural Evolution

The source structure may evolve as understanding and project requirements
develop.

Changes should improve at least one of the following:

- clarity;
- cohesion;
- traceability;
- testability;
- maintainability;
- scalability;
- educational value.

Structural changes should be based on actual project needs rather than on
enterprise architecture patterns that do not apply to the repository.

Git history preserves earlier structures, so obsolete packages should not remain
solely for historical purposes.

---

# Structural Principles

The Java project follows these principles:

- organise code by computational domain;
- preserve one primary responsibility per package and class;
- maintain traceability between documentation, code, tests, and benchmarks;
- keep production code independent of verification and experimentation code;
- introduce abstractions only when they provide clear value;
- prefer established Maven and Java conventions;
- keep dependencies explicit and minimal;
- support progressive growth without premature complexity;
- expose only the minimum required public API;
- preserve readable and testable implementations.

These principles guide future structural decisions.

---

# Relationship with the Repository

The Java project structure is one part of the complete repository architecture.

```text
Documentation
        ↓
Explains theory, contracts, and decisions

Production Source
        ↓
Implements algorithms and data structures

Test Source
        ↓
Verifies documented behaviour

Benchmark Source
        ↓
Evaluates practical performance

Continuous Integration
        ↓
Validates the build and automated tests
```

Each area has a distinct responsibility while contributing to the same learning
process.

---

# Relationship with Project Documentation

This document forms part of the foundational documentation of the repository.

| Order | Document | Purpose |
|------:|----------|---------|
| **01** | `01-project-overview.md` | Introduces the project's vision, academic foundation, scope, and objectives. |
| **02** | `02-learning-objectives.md` | Defines the knowledge, competencies, and expected learning outcomes. |
| **03** | `03-study-methodology.md` | Describes the structured methodology followed throughout the repository. |
| **04** | `04-repository-architecture.md` | Explains the architectural organisation of the repository. |
| **05** | `05-technology-stack.md` | Defines the technologies used throughout the project. |
| **06** | `06-documentation-architecture.md` | Establishes the organisation and standards of the documentation. |
| **07** | `07-java-project-structure.md` | Defines the organisation of production code, tests, and benchmarks. |
| **08** | `08-engineering-standards.md` | Defines engineering principles, quality guidelines, and coding conventions. |
| **09** | `09-development-workflow.md` | Describes the workflow used to study and implement algorithms. |
| **10** | `10-references.md` | Lists the principal academic, technical, and official sources. |

Together, these documents define the academic, technical, and organisational
foundations of **Algorithms Java Mastery**.

---

# Conclusion

The Java project structure of **Algorithms Java Mastery** connects academic
study with executable and verifiable Java code.

Production implementations are organised by computational domain, while tests
and benchmarks mirror the same package hierarchy to preserve traceability.

The structure follows Maven and Java conventions without introducing
application-oriented layers or unnecessary abstractions.

This approach allows the repository to grow progressively while maintaining
clarity, cohesion, testability, and a direct relationship between theory,
implementation, verification, and experimental evaluation.

---

# Related Documentation

| Order | Document | Purpose |
|------:|----------|---------|
| **01** | `01-project-overview.md` | Introduces the project's identity, scope, and academic direction. |
| **03** | `03-study-methodology.md` | Defines the process that precedes and validates implementation. |
| **04** | `04-repository-architecture.md` | Explains the responsibilities of the repository areas. |
| **05** | `05-technology-stack.md` | Presents Java, Maven, testing, and benchmarking technologies. |
| **06** | `06-documentation-architecture.md` | Defines how source code remains connected to documented knowledge. |
| **08** | `08-engineering-standards.md` | Establishes implementation and quality conventions. |
| **09** | `09-development-workflow.md` | Describes how changes progress from study to integration. |
| **10** | `10-references.md` | Centralises the principal sources supporting the project. |

---

# Next Document

```text
08-engineering-standards.md
```

The next document defines the engineering principles, coding conventions,
quality requirements, testing expectations, and repository rules applied
throughout **Algorithms Java Mastery**.