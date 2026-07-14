# Project Documentation

## Algorithms Java Mastery

This directory contains the foundational project documentation for **Algorithms Java Mastery**, an academic and practical study repository focused on algorithmic reasoning, data structures, computational complexity, correctness, automated testing, and experimental performance analysis using Java.

The purpose of this documentation is to define the project's academic direction, learning objectives, study methodology, repository organisation, and technical environment before addressing individual algorithms and data structures.

The project is based on a fundamental principle:

> **An algorithm should be understood, specified, reasoned about, analysed, implemented, tested, and experimentally evaluated.**

Therefore, this repository is not intended to be a collection of isolated algorithm implementations or programming exercises. It documents a structured learning process designed to develop rigorous algorithmic reasoning and professional Java engineering practices.

---

## 1. Project Direction

**Algorithms Java Mastery** is designed as a progressive study project that connects computer science foundations with modern Java development practices.

The project follows the general progression:

```text
Problem Understanding
        ↓
Formal Specification
        ↓
Algorithmic Reasoning
        ↓
Strategy Design
        ↓
Pseudocode
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
```

Each stage addresses a different engineering question.

| Stage                   | Central Question                                                 |
| ----------------------- | ---------------------------------------------------------------- |
| Problem Understanding   | What problem must be solved?                                     |
| Formal Specification    | What are the input, output, and conditions?                      |
| Algorithmic Reasoning   | What information is relevant to the solution?                    |
| Strategy Design         | How can the problem be solved systematically?                    |
| Pseudocode              | How can the solution be expressed independently of Java syntax?  |
| Correctness Reasoning   | Why does the algorithm produce the expected result?              |
| Complexity Analysis     | How does resource usage grow with the input size?                |
| Java Implementation     | How is the algorithm expressed clearly in Java?                  |
| Automated Testing       | Does the implementation satisfy the expected behaviour?          |
| Experimental Evaluation | How does the implementation behave under controlled measurement? |

This progression defines the academic and technical direction of the repository.

---

## 2. Academic Purpose

The central purpose of the project is to develop the ability to **reason about algorithms before implementing them**.

Writing syntactically correct Java code is not sufficient to demonstrate algorithmic understanding.

A developer studying algorithms should be able to explain:

* what computational problem is being solved;
* what constitutes a valid input;
* what output must be produced;
* which preconditions must hold;
* which postconditions must be guaranteed;
* which edge cases affect the problem;
* why the selected strategy works;
* which properties remain invariant during execution;
* how execution cost grows with the input size;
* how much auxiliary memory is required;
* how theoretical analysis relates to experimental observations.

For this reason, implementations in this repository are treated as the final expression of a prior reasoning process.

```text
Understand
    ↓
Specify
    ↓
Reason
    ↓
Design
    ↓
Justify
    ↓
Analyse
    ↓
Implement
    ↓
Test
    ↓
Measure
```

The objective is not to memorise algorithm implementations.

The objective is to develop the capacity to **derive, analyse, explain, and evaluate algorithmic solutions**.

---

## 3. Learning Objectives

By progressing through this project, the following competencies are expected to be developed.

### Algorithmic reasoning

* Decompose computational problems into explicit components.
* Identify inputs, outputs, constraints, and assumptions.
* Design systematic solution strategies.
* Express algorithms using precise pseudocode.
* Perform manual execution traces.
* Identify and analyse edge cases.

### Correctness reasoning

* Distinguish an example that works from a generally correct algorithm.
* Define preconditions and postconditions.
* Identify loop invariants.
* Reason through initialisation, maintenance, and termination.
* Explain why an algorithm satisfies its specification.

### Complexity analysis

* Identify the relevant input size.
* Count significant operations.
* Analyse temporal and spatial cost.
* Distinguish best, average, and worst-case behaviour when applicable.
* Use asymptotic notation appropriately.
* Compare algorithms according to their order of growth.

### Data structures and algorithms

The project progressively studies:

* arrays;
* searching algorithms;
* sorting algorithms;
* algorithmic problem-solving patterns;
* recursion;
* divide and conquer;
* hashing;
* stacks;
* queues;
* trees;
* heaps;
* graphs;
* backtracking;
* dynamic programming.

The purpose is not only to implement these structures and algorithms, but to understand the design decisions and computational trade-offs associated with them.

### Java engineering practices

* Organise code using coherent Java packages.
* Maintain separation of responsibilities.
* Use meaningful names and explicit APIs.
* Compare academic implementations with relevant Java standard library abstractions.
* Use Maven as the project build system.
* Maintain a reproducible project environment.

### Software testing

* Design tests from algorithm specifications.
* Translate edge cases into automated test cases.
* Structure tests with JUnit Jupiter.
* Express expectations using AssertJ.
* Distinguish correctness testing from performance measurement.

### Performance analysis

* Compare theoretical complexity with observed behaviour.
* Understand the limitations of naive timing techniques.
* Design controlled microbenchmarks.
* Use Java Microbenchmark Harness when appropriate.
* Interpret benchmark results without replacing theoretical analysis with empirical measurements.

---

## 4. Study Methodology

Every algorithm studied in this repository follows a common analytical process.

### Step 1 — Define the problem

State precisely what computational problem must be solved.

The initial question is not:

> What Java code should be written?

The initial question is:

> What must the algorithm guarantee?

### Step 2 — Identify the input

Document:

* the input data;
* its representation;
* its domain;
* the relevant input size.

For an array algorithm, the input size may be represented as:

```text
n = number of elements
```

The definition of input size is essential for later complexity analysis.

### Step 3 — Define the output

Specify the expected result precisely.

The output definition should be sufficiently clear to determine whether a proposed result is correct or incorrect.

### Step 4 — Establish preconditions

Identify the conditions that must hold before the algorithm executes.

Examples include:

```text
The array must not be empty.

The input must be sorted.

The graph must contain valid vertices.

The search range must be valid.
```

Preconditions define the valid domain of the algorithm.

### Step 5 — Establish postconditions

Describe the property guaranteed after successful termination.

The relationship can be represented as:

```text
Precondition
      ↓
 Algorithm
      ↓
Postcondition
```

Correctness reasoning connects these elements.

### Step 6 — Analyse edge cases

Identify minimal, extreme, repeated, empty, ordered, reversed, or otherwise structurally significant inputs.

Edge cases are analysed before implementation because they may reveal incorrect assumptions in the proposed strategy.

### Step 7 — Design the strategy

Explain the central solution idea using natural language.

At this stage, Java syntax should not determine the reasoning process.

### Step 8 — Express the algorithm in pseudocode

Translate the strategy into a precise sequence of computational steps.

Pseudocode acts as a bridge between reasoning and implementation:

```text
Strategy
    ↓
Pseudocode
    ↓
Java
```

### Step 9 — Reason about correctness

Determine why the algorithm satisfies its specification.

When iterative algorithms are studied, loop invariants may be analysed through:

```text
Initialisation
      ↓
Maintenance
      ↓
Termination
```

The purpose is to explain why the desired property remains valid throughout execution and implies the postcondition when the algorithm terminates.

### Step 10 — Analyse computational cost

Study:

```text
Time Complexity
Space Complexity
```

The analysis considers how resource usage grows as the input size increases.

The project distinguishes theoretical complexity analysis from direct execution-time measurements.

### Step 11 — Implement in Java

Only after the algorithm has been understood and analysed is it translated into Java.

The implementation should preserve the clarity of the previously documented strategy.

### Step 12 — Test the implementation

Automated tests verify whether the Java implementation satisfies the expected behaviour.

Tests should be derived from:

* the specification;
* preconditions;
* postconditions;
* representative cases;
* edge cases.

### Step 13 — Experiment and measure

When performance analysis is relevant, controlled experiments may be performed.

Experimental observations complement theoretical analysis.

They do not replace it.

---

## 5. Repository Documentation Structure

The `docs` directory is organised as a progressive academic path.

```text
docs/
├── 00-project/
├── 01-foundations/
├── 02-complexity/
├── 03-arrays/
├── 04-searching/
├── 05-sorting/
├── 06-algorithmic-patterns/
├── 07-recursion/
├── 08-divide-and-conquer/
├── 09-hashing/
├── 10-stacks-and-queues/
├── 11-trees/
├── 12-heaps/
├── 13-graphs/
├── 14-backtracking/
├── 15-dynamic-programming/
├── 16-testing/
├── 17-benchmarking/
├── 18-linux-environment/
├── 19-ci-cd/
└── 20-conclusions/
```

The numerical prefixes represent the intended learning progression.

They are organisational elements of the documentation and do not represent Maven modules or Java packages.

### Documentation stages

The documentation can be understood in five broad stages.

| Stage                | Documentation Areas                     | Purpose                                      |
| -------------------- | --------------------------------------- | -------------------------------------------- |
| Project Definition   | `00-project`                            | Define project direction and methodology     |
| Foundations          | `01-foundations` to `02-complexity`     | Develop algorithmic reasoning and analysis   |
| Core Algorithms      | `03-arrays` to `15-dynamic-programming` | Study structures, algorithms, and strategies |
| Engineering Practice | `16-testing` to `19-ci-cd`              | Validate, measure, and automate the project  |
| Synthesis            | `20-conclusions`                        | Consolidate findings and learning outcomes   |

The repository therefore progresses from theoretical reasoning to implementation and engineering practice.

---

## 6. Repository Architecture

The project separates academic documentation, Java source code, tests, exercises, references, and automation.

```text
Algorithms-Java-Mastery/
│
├── .github/
├── .mvn/
├── docs/
├── diagrams/
├── exercises/
├── notes/
├── references/
├── src/
├── benchmarks/
├── README.md
├── CONTRIBUTING.md
├── CHANGELOG.md
├── LICENSE
├── mvnw
├── mvnw.cmd
└── pom.xml
```

Each area has a specific responsibility.

| Directory        | Responsibility                                             |
| ---------------- | ---------------------------------------------------------- |
| `.github/`       | Repository automation and continuous integration workflows |
| `.mvn/`          | Maven Wrapper configuration                                |
| `docs/`          | Structured academic documentation                          |
| `diagrams/`      | Visual representations and technical diagrams              |
| `exercises/`     | Progressive algorithmic practice                           |
| `notes/`         | Complementary study notes                                  |
| `references/`    | Academic and technical reference organisation              |
| `src/main/java/` | Java algorithm and data structure implementations          |
| `src/test/java/` | Automated tests                                            |
| `benchmarks/`    | Performance measurement resources and JMH benchmarks       |

The separation of these directories reflects the distinction between:

```text
Study
Implementation
Verification
Experimentation
Automation
```

---

## 7. Java Package Organisation

The Java source code is organised under the base package:

```text
io.github.anaalvarezdev.algorithms
```

The package structure reflects the algorithmic domain.

```text
io.github.anaalvarezdev.algorithms
├── application
├── arrays
├── searching
├── sorting
├── patterns
├── recursion
├── hashing
├── stack
├── queue
├── tree
├── heap
├── graph
├── backtracking
├── dynamicprogramming
└── shared
```

The test source tree should reflect the relevant production package structure.

Example:

```text
src/main/java/
└── io/github/anaalvarezdev/algorithms/searching/
    └── BinarySearch.java

src/test/java/
└── io/github/anaalvarezdev/algorithms/searching/
    └── BinarySearchTest.java
```

This organisation makes the relationship between an implementation and its associated tests explicit.

---

## 8. Technology Stack

The project uses technologies selected according to their role in the learning and engineering process.

| Technology     | Role                                                |
| -------------- | --------------------------------------------------- |
| Java 21        | Primary implementation language                     |
| Maven          | Build and dependency management                     |
| Maven Wrapper  | Reproducible Maven execution                        |
| JUnit Jupiter  | Test structure and execution                        |
| AssertJ        | Fluent and expressive assertions                    |
| JMH            | Java microbenchmarking                              |
| Git            | Distributed version control                         |
| GitHub         | Repository hosting and collaboration workflow       |
| GitHub Actions | Continuous integration automation                   |
| Ubuntu Linux   | Linux validation environment                        |
| WSL 2          | Local Linux environment while developing on Windows |

The project intentionally does not use Spring Boot.

The objective is to study algorithms and data structures without introducing an application framework that could obscure the computational concepts being analysed.

---

## 9. Academic and Technical References

The project distinguishes between academic references and platform-specific technical documentation.

### Algorithmic foundations

The primary theoretical reference is:

**Cormen, T. H., Leiserson, C. E., Rivest, R. L., and Stein, C. — Introduction to Algorithms.**

The reference is used to support the study of:

* algorithmic reasoning;
* correctness;
* loop invariants;
* asymptotic analysis;
* fundamental algorithms;
* data structures;
* algorithm design techniques.

The book is treated as a theoretical foundation rather than as a source of Java implementations.

### Java implementation context

Java-specific decisions are contrasted with official Java documentation and the Java Collections Framework when relevant.

The objective is to distinguish:

```text
Algorithmic concept
        ↓
Academic implementation
        ↓
Java standard abstractions
        ↓
Engineering trade-offs
```

A custom implementation is therefore not assumed to replace a standard Java collection in production software.

Its purpose is to expose the underlying structure and algorithmic behaviour for study.

### Tool documentation

Official project documentation should be prioritised for:

* Java;
* Maven;
* JUnit;
* AssertJ;
* JMH;
* Git;
* GitHub Actions;
* Linux and WSL.

This distinction between academic literature and official technical documentation is maintained throughout the repository.

---

## 10. Project Scope

The project includes:

* algorithmic problem-solving methodology;
* complexity analysis;
* fundamental data structures;
* searching and sorting algorithms;
* algorithm design patterns;
* recursion;
* divide and conquer;
* hashing;
* trees and heaps;
* graph algorithms;
* backtracking;
* dynamic programming;
* automated testing;
* microbenchmarking;
* Linux-based project validation;
* continuous integration.

The project is not intended to be:

* a Spring Boot application;
* a web application;
* a REST API;
* a microservices system;
* a production replacement for the Java Collections Framework;
* a collection of copied interview solutions.

These topics may belong to other portfolio projects with different architectural objectives.

The scope of **Algorithms Java Mastery** is deliberately focused on algorithmic foundations and the engineering practices required to implement, validate, analyse, and explain them.

---

## 11. Expected Project Outcome

At the conclusion of the project, the expected result is not simply a repository containing Java classes.

The repository should demonstrate a documented progression from problem analysis to engineering validation.

For each major topic, the project should provide evidence of the ability to:

```text
Understand the problem
        ↓
Specify expected behaviour
        ↓
Design an algorithmic strategy
        ↓
Express the strategy in pseudocode
        ↓
Reason about correctness
        ↓
Analyse computational complexity
        ↓
Implement the solution in Java
        ↓
Validate behaviour with automated tests
        ↓
Evaluate performance when relevant
        ↓
Document conclusions
```

The final repository is intended to function simultaneously as:

* an academic study record;
* an algorithmic reasoning laboratory;
* a Java implementation project;
* a testing and performance analysis environment;
* a documented portfolio artefact.

---

## Documentation Index

The detailed definition of the project is divided into the following documents:

| Document                     | Purpose                                                   |
| ---------------------------- | --------------------------------------------------------- |
| `project-overview.md`        | Defines the project, context, scope, and central purpose  |
| `learning-objectives.md`     | Defines the competencies developed throughout the project |
| `study-methodology.md`       | Defines the analytical process applied to each algorithm  |
| `repository-architecture.md` | Documents and justifies the repository organisation       |
| `technology-stack.md`        | Defines the technologies and their responsibilities       |

The next stage of the documentation begins in:

```text
docs/01-foundations/
```

where the project moves from **defining the study framework** to **developing algorithmic problem-solving foundations**.

---

## Project Principle

> **Understand before implementing. Justify before optimising. Test before trusting. Measure before concluding.**
