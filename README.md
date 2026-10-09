# Algorithms Java Mastery

<p align="center">
  Academic study of algorithms and data structures through rigorous reasoning,
  Java 21 implementation, automated testing, empirical benchmarking, Linux,
  Maven, and professional software-engineering practices.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-informational" alt="Java 21">
  <img src="https://img.shields.io/badge/Maven-Wrapper-informational" alt="Maven Wrapper">
  <img src="https://img.shields.io/badge/JUnit-Jupiter-informational" alt="JUnit Jupiter">
  <img src="https://img.shields.io/badge/AssertJ-Testing-informational" alt="AssertJ">
  <img src="https://img.shields.io/badge/JMH-Benchmarking-informational" alt="JMH">
</p>

---

## Overview

**Algorithms Java Mastery** is an academic and engineering repository for the
systematic study of algorithms, data structures, correctness, asymptotic
complexity, testing, benchmarking, and reproducible Java development.

The project is inspired primarily by *Introduction to Algorithms* by
Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.

The repository does not attempt to reproduce the book. Instead, it transforms
academic concepts into original explanations, formal specifications, correctness
arguments, Java implementations, automated tests, empirical experiments, and
engineering documentation.

Java is the implementation language. The real subject of the repository is
**algorithmic reasoning**.

---

## Core Principle

The project follows a simple rule:

> **Understanding precedes implementation.**

Every relevant algorithm is studied through a traceable workflow:

```text
Academic Source
        ↓
Problem Understanding
        ↓
Formal Specification
        ↓
Preconditions / Postconditions
        ↓
Edge Cases
        ↓
Algorithmic Strategy
        ↓
Pseudocode
        ↓
Manual Trace
        ↓
Correctness Reasoning
        ↓
Termination
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
JUnit + AssertJ
        ↓
JMH when justified
        ↓
Engineering Documentation
```

This separation is intentional:

```text
Correctness          ≠ Testing
Complexity Analysis  ≠ Benchmarking
Algorithm Design     ≠ Java Syntax
Academic Source      ≠ Repository Documentation
Implementation       ≠ Experimental Evidence
```

---

## Project Goals

The repository is designed to develop the ability to:

- define computational problems precisely;
- reason about preconditions, postconditions, invariants, and termination;
- analyse time and space complexity using asymptotic notation;
- select appropriate algorithms and data structures;
- implement algorithms clearly in Java;
- derive tests from contracts rather than from implementation details;
- use JMH only when a meaningful empirical question exists;
- work through Maven from the command line;
- use Linux as a professional engineering environment;
- connect local verification with future Continuous Integration;
- communicate technical decisions through structured documentation.

The objective is not to accumulate code. The objective is to build transferable
Computer Science and software-engineering judgement.

---

## Academic and Engineering Traceability

The repository architecture connects theory with executable evidence.

```text
CLRS / Academic References
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
src/jmh/ when relevant
        ↓
Maven Verification
        ↓
Windows / Linux
        ↓
Continuous Integration when configured
```

Each layer has a distinct responsibility.

---

## Repository Structure

```text
Algorithms-Java-Mastery/
│
├── docs/
│   ├── 00-project/
│   ├── 01-foundations/
│   ├── 02-complexity/
│   ├── 03-arrays/
│   ├── 04-searching/
│   ├── 05-sorting/
│   ├── 06-linked-lists/
│   ├── 07-stacks-and-queues/
│   ├── 08-hash-tables/
│   ├── 09-trees/
│   ├── 10-graphs/
│   ├── 11-recursion/
│   ├── 12-divide-and-conquer/
│   ├── 13-greedy-algorithms/
│   ├── 14-backtracking/
│   ├── 15-dynamic-programming/
│   ├── 16-testing/
│   ├── 17-benchmarking/
│   ├── 18-linux-environment/
│   └── 19-ci-cd/
│
├── src/
│   ├── main/java/
│   ├── test/java/
│   └── jmh/java/
│
├── pom.xml
├── mvnw
└── mvnw.cmd
```

### Source responsibilities

```text
src/main/java
        ↓
Algorithm and data-structure implementations

src/test/java
        ↓
JUnit + AssertJ behavioural verification

src/jmh/java
        ↓
Controlled empirical performance experiments
```

---

## Documentation Modules

| Module | Focus |
|---|---|
| `00-project` | Project vision, methodology, architecture, standards, workflow, references |
| `01-foundations` | Problem specification, preconditions, postconditions, correctness, invariants |
| `02-complexity` | O, Ω, Θ, time, space, best/average/worst-case reasoning |
| `03-arrays` | Array fundamentals, traversal, operations, common algorithms |
| `04-searching` | Linear Search, Binary Search, recursive search, boundary patterns |
| `05-sorting` | Sorting algorithms and ordering strategies |
| `06-linked-lists` | Linked structures and list operations |
| `07-stacks-and-queues` | LIFO/FIFO structures and applications |
| `08-hash-tables` | Hashing, lookup, collisions, table behaviour |
| `09-trees` | Hierarchical structures and tree operations |
| `10-graphs` | Graph representations and graph algorithms |
| `11-recursion` | Recursive reasoning and recursive correctness |
| `12-divide-and-conquer` | Divide, solve, combine, recurrences |
| `13-greedy-algorithms` | Greedy choice and optimal substructure |
| `14-backtracking` | Search-space exploration and pruning |
| `15-dynamic-programming` | State, recurrence, memoization, tabulation |
| `16-testing` | Automated testing methodology |
| `17-benchmarking` | JMH and empirical performance analysis |
| `18-linux-environment` | Linux terminal, permissions, processes, Java, Maven, Git |
| `19-ci-cd` | Continuous Integration and automation concepts |

Detailed project documentation begins at
[`docs/00-project/README.md`](docs/00-project/README.md).

---

## Current Java Implementation

The current implementation layer focuses on **Arrays** and **Searching**.

### Arrays

The Arrays package includes implementations for topics such as:

- indexed access and update;
- traversal and aggregation;
- minimum and maximum search;
- array reversal and copying;
- insertion and deletion;
- duplicate removal in sorted arrays;
- prefix sums.

Package:

```text
src/main/java/org/anaalvarezdev/algorithms/arrays/
```

### Searching

The Searching package currently includes:

- `LinearSearch`;
- `BinarySearch`;
- `RecursiveBinarySearch`;
- `FirstOccurrence`;
- `LastOccurrence`;
- `SearchInsertPosition`;
- `SearchRange`;
- `ClosestValueSearch`.

Package:

```text
src/main/java/org/anaalvarezdev/algorithms/searching/
```

Each approved implementation is expected to remain traceable to its
documentation and automated tests.

---

## Example: Searching Traceability

```text
docs/04-searching/04-binary-search.md
        ↓
BinarySearch.java
        ↓
BinarySearchTest.java
        ↓
BinarySearchBenchmark.java
```

For recursive search:

```text
docs/04-searching/05-recursive-binary-search.md
        ↓
RecursiveBinarySearch.java
        ↓
RecursiveBinarySearchTest.java
        ↓
BinarySearchVariantsBenchmark.java
```

Boundary-search variants are tested but are not benchmarked individually when
no separate empirical question exists.

This follows the project rule:

> **Benchmark when justified, not because a class exists.**

---

## Technology Stack

| Technology | Responsibility |
|---|---|
| Java 21 | Implementation language |
| Maven | Build and dependency management |
| Maven Wrapper | Reproducible project execution |
| JUnit Jupiter | Automated testing |
| AssertJ | Fluent assertions |
| JMH | Microbenchmarking |
| Git | Version control |
| GitHub | Repository collaboration and review |
| Linux | Command-line and execution-environment practice |
| GitHub Actions | Intended CI platform |

GitHub Actions belongs to the engineering roadmap, but the repository should
only claim CI implementation after an executable workflow exists under
`.github/workflows/` and has run successfully.

---

## Requirements

To work with the project locally:

- JDK 21;
- Git;
- a terminal or shell;
- no global Maven installation is required when using the Maven Wrapper.

---

## Build and Verify

### Windows

```powershell
.\mvnw.cmd clean verify
```

### Linux / macOS

```bash
./mvnw clean verify
```

The operating system changes the execution environment and command syntax. It
does not change algorithmic correctness or asymptotic complexity.

---

## Run Tests

### Windows

```powershell
.\mvnw.cmd test
```

### Linux / macOS

```bash
./mvnw test
```

Tests are written with **JUnit Jupiter** and **AssertJ**.

Passing tests provide evidence for selected executions. They do not constitute
a formal proof of algorithm correctness.

---

## Build JMH Benchmarks

The benchmark profile is intentionally separate from the normal build.

### Windows

```powershell
.\mvnw.cmd -Pbenchmark clean package
```

### Linux / macOS

```bash
./mvnw -Pbenchmark clean package
```

The Maven profile creates an executable JMH artefact:

```text
target/benchmarks.jar
```

It can then be executed with:

```bash
java -jar target/benchmarks.jar
```

Benchmark results are environment-dependent empirical measurements.

They support interpretation of implementation behaviour but do not prove
asymptotic complexity.

---

## Testing Philosophy

Tests should be derived from the documented contract.

Representative test dimensions include:

```text
Normal Case
Boundary Case
Empty Input
Single Element
Absent Result
Duplicate Values
Invalid Input
Mutation / Non-Mutation Guarantee
```

The repository deliberately distinguishes:

```text
Formal Correctness
        ↓
General reasoning about all valid inputs

Automated Testing
        ↓
Evidence from selected concrete executions
```

---

## Benchmarking Philosophy

A benchmark must begin with a research question.

Examples:

```text
How does target position affect Linear Search execution time?

How does Binary Search behave as n grows?

What empirical differences appear between iterative and recursive Binary Search?
```

A benchmark is not added merely to preserve file symmetry.

When results are recorded, interpretation should include:

- input size;
- benchmark mode;
- time unit;
- JDK;
- operating system;
- relevant JMH configuration;
- observed trend;
- theoretical expectation;
- environmental limitations.

---

## Cross-Platform Execution

The repository is designed to be usable from both Windows and Linux.

Equivalent verification entry points are:

```text
Windows
.\mvnw.cmd clean verify

Linux
./mvnw clean verify
```

Linux has a dedicated module because command-line execution, permissions,
processes, environment variables, Java tooling, Maven, and Git are important
professional backend-engineering skills.

---

## Development Workflow

Contributions and study changes should follow the repository methodology rather
than starting directly from code.

```text
Study / Research
        ↓
Problem Specification
        ↓
Correctness
        ↓
Complexity
        ↓
Implementation
        ↓
Tests
        ↓
Benchmark when justified
        ↓
Local Maven Verification
        ↓
Git Branch
        ↓
Commit
        ↓
Pull Request
```

Conventional Commit-style messages are preferred, for example:

```text
feat(searching): add iterative binary search
test(searching): cover boundary search contracts
docs(searching): align implementation traceability
benchmark(searching): compare binary search variants
```

---

## Engineering Standards

The repository values:

- explicit contracts;
- meaningful names;
- small focused classes;
- predictable package organisation;
- separation of theory from implementation;
- separation of tests from proofs;
- selective benchmarking;
- reproducible Maven commands;
- incremental branches and pull requests;
- documentation that remains traceable to executable artefacts.

The complete engineering standards are documented in
[`docs/00-project/08-engineering-standards.md`](docs/00-project/08-engineering-standards.md).

---

## Academic References

The principal academic reference is:

**Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; Stein, Clifford.  
*Introduction to Algorithms*. MIT Press.**

The repository also uses recognised Computer Science literature and official
documentation for Java, Maven, JUnit, AssertJ, JMH, Git, Linux, and GitHub.

The central bibliography is maintained in
[`docs/00-project/10-references.md`](docs/00-project/10-references.md).

---

## Project Status

This is an actively evolving academic repository.

The documentation architecture currently spans project foundations, complexity,
core data structures and algorithms, testing, benchmarking, Linux, and CI/CD.

Executable implementation is being developed progressively so that code follows
the academic sequence instead of getting ahead of it.

Current implementation work has established complete documentation-to-code
traceability for Arrays and Searching. Sorting is the next algorithmic module in
the progression.

---

## Author

**Ana María Alvarez**

Systems Engineering and Software Development student focused on backend
engineering with Java.

GitHub: [@Ana-Alvarez-dev](https://github.com/Ana-Alvarez-dev)

---

## Final Principle

> **An algorithm should be understood, specified, reasoned about, analysed,
> implemented, tested, and experimentally evaluated before its engineering
> conclusions are treated as established.**
