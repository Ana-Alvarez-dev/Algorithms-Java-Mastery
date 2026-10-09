<h1 align="center">Algorithms Java Mastery</h1>

<p align="center">
  <strong>Algorithms, Data Structures, Correctness, Complexity and Java Engineering</strong>
</p>

<p align="center">
  Academic study repository inspired primarily by <em>Introduction to Algorithms (CLRS)</em>,
  developed through rigorous reasoning, Java 21, automated testing, JMH,
  Linux, Maven and reproducible engineering practices.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-informational" alt="Java 21">
  <img src="https://img.shields.io/badge/Maven-Wrapper-informational" alt="Maven Wrapper">
  <img src="https://img.shields.io/badge/JUnit-Jupiter-informational" alt="JUnit Jupiter">
  <img src="https://img.shields.io/badge/AssertJ-Testing-informational" alt="AssertJ">
  <img src="https://img.shields.io/badge/JMH-Benchmarking-informational" alt="JMH">
  <img src="https://img.shields.io/badge/Linux-Engineering-informational" alt="Linux">
  <img src="https://img.shields.io/badge/AI-Assisted%20Learning-transparent-informational" alt="AI Assisted Learning">
</p>

<p align="center">
  <a href="docs/00-project/README.md">Project Documentation</a>
  ·
  <a href="docs/01-foundations/README.md">Foundations</a>
  ·
  <a href="docs/02-complexity/README.md">Complexity</a>
  ·
  <a href="docs/03-arrays/README.md">Arrays</a>
  ·
  <a href="docs/04-searching/README.md">Searching</a>
</p>

---

## Overview

**Algorithms Java Mastery** is an academic and engineering repository for the
systematic study of algorithms, data structures, correctness, asymptotic
complexity, testing, benchmarking, and reproducible Java development.

The project is inspired primarily by *Introduction to Algorithms* by
Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.

The repository does not reproduce the book. Instead, it transforms academic
concepts into original explanations, formal specifications, correctness
arguments, Java implementations, automated tests, empirical experiments, and
engineering documentation.

> **Java is the implementation language. Algorithmic reasoning is the subject.**

---

## Project Status

| Area | Status |
|---|---|
| Project architecture | Established |
| Foundations | Documented |
| Complexity | Documented |
| Arrays | Documented + implemented + tested + selected JMH |
| Searching | Documented + implemented + tested + selected JMH |
| Sorting | Next algorithmic module |
| Linux environment | Documented |
| CI/CD concepts | Documented |
| GitHub Actions workflow | Planned / not yet implemented |

The README is intentionally maintained as a **living project summary**. It is
updated only when repository evidence changes.

---

## Core Principle

> **Understanding precedes implementation.**

Every relevant algorithm follows a traceable learning and engineering workflow:

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

The repository deliberately separates concepts that are often confused:

```text
Correctness          ≠ Testing
Complexity Analysis  ≠ Benchmarking
Algorithm Design     ≠ Java Syntax
Academic Source      ≠ Repository Documentation
Implementation       ≠ Experimental Evidence
```

---

## Academic and Engineering Traceability

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

Each layer has a distinct responsibility and should provide evidence for the
next one.

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

| Path | Responsibility |
|---|---|
| `src/main/java` | Algorithm and data-structure implementations |
| `src/test/java` | JUnit + AssertJ behavioural verification |
| `src/jmh/java` | Controlled empirical performance experiments |

---

## Documentation Roadmap

| Module | Focus |
|---|---|
| `00-project` | Project vision, methodology, architecture, standards, workflow, references |
| `01-foundations` | Problem specification, correctness, invariants, edge cases |
| `02-complexity` | O, Ω, Θ, time, space, best/average/worst-case reasoning |
| `03-arrays` | Array fundamentals, operations, traversal, common algorithms |
| `04-searching` | Linear, binary, recursive and boundary-search strategies |
| `05-sorting` | Sorting algorithms and ordering strategies |
| `06-linked-lists` | Linked structures and list operations |
| `07-stacks-and-queues` | LIFO/FIFO structures and applications |
| `08-hash-tables` | Hashing, collisions and efficient lookup |
| `09-trees` | Hierarchical structures and tree operations |
| `10-graphs` | Graph representations and graph algorithms |
| `11-recursion` | Recursive reasoning and correctness |
| `12-divide-and-conquer` | Divide, solve, combine and recurrences |
| `13-greedy-algorithms` | Greedy choice and optimal substructure |
| `14-backtracking` | Search-space exploration and pruning |
| `15-dynamic-programming` | State, recurrence, memoization and tabulation |
| `16-testing` | Automated testing methodology |
| `17-benchmarking` | JMH and empirical performance analysis |
| `18-linux-environment` | Terminal, permissions, processes, Java, Maven and Git |
| `19-ci-cd` | Continuous Integration and automation concepts |

Detailed project documentation begins at
[`docs/00-project/README.md`](docs/00-project/README.md).

---

## Current Java Implementation

### Arrays

The Arrays package currently includes implementations for:

- indexed access and update;
- traversal and aggregation;
- minimum and maximum;
- array reversal and copying;
- insertion and deletion;
- duplicate removal in sorted arrays;
- prefix sums.

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

```text
src/main/java/org/anaalvarezdev/algorithms/searching/
```

Example trace:

```text
docs/04-searching/04-binary-search.md
        ↓
BinarySearch.java
        ↓
BinarySearchTest.java
        ↓
BinarySearchBenchmark.java
```

The project rule is:

> **Benchmark when justified, not because a class exists.**

---

## Technology Stack

| Technology | Responsibility |
|---|---|
| Java 21 | Implementation language |
| Maven | Build and dependency management |
| Maven Wrapper | Reproducible execution |
| JUnit Jupiter | Automated tests |
| AssertJ | Fluent assertions |
| JMH | Microbenchmarking |
| Git | Version control |
| GitHub | Collaboration, branches and pull requests |
| Linux | Professional command-line engineering environment |
| GitHub Actions | Intended CI platform |
| Generative AI | Learning, review and engineering assistance under human validation |

---

## AI-Assisted Academic Engineering

Generative AI is used in this repository as a **learning and engineering
assistant**, not as an academic authority, source of truth, or autonomous
author.

Its position in the workflow is:

```text
Academic Sources / Official Documentation
                ↓
Human Study and Reasoning
                ↓
AI-Assisted Explanation / Review / Alternatives
                ↓
Human Validation
                ↓
Implementation
                ↓
Tests / Benchmarks / Build Evidence
                ↓
Documented Engineering Conclusion
```

### How AI is used

AI assistance may support:

- explaining difficult concepts at different levels of depth;
- comparing alternative algorithmic strategies;
- questioning assumptions and identifying missing edge cases;
- reviewing preconditions, postconditions and invariants;
- suggesting additional test scenarios from an existing contract;
- reviewing complexity arguments for internal consistency;
- identifying documentation or traceability gaps;
- improving technical writing and terminology;
- supporting Java code review and maintainability analysis;
- proposing benchmark questions before experiments are implemented;
- helping maintain consistency across documentation, code, tests and JMH;
- supporting bilingual technical learning when appropriate.

### What AI does not replace

AI output is not treated as evidence by itself.

```text
AI Explanation       ≠ Academic Reference
AI-Generated Code    ≠ Verified Implementation
AI Suggestion        ≠ Engineering Decision
AI Complexity Claim  ≠ Mathematical Analysis
AI Test Proposal     ≠ Correctness Proof
AI Benchmark Comment ≠ Experimental Result
```

The learner remains responsible for understanding and validating every accepted
change.

### Validation policy

AI-assisted contributions should be validated against the strongest applicable
source:

| Claim | Required validation |
|---|---|
| Algorithmic concept | Academic reference |
| Java behaviour | Java / library documentation when relevant |
| Correctness | Contract + formal reasoning |
| Complexity | Asymptotic analysis |
| Implementation | Code review + tests |
| Performance | JMH measurement |
| Build behaviour | Maven execution |
| Repository state | Actual Git / GitHub evidence |

### Academic integrity

AI assistance is disclosed transparently because it forms part of the learning
and engineering workflow.

The repository follows these principles:

- academic sources remain primary;
- generated references are never trusted without verification;
- code should not be accepted unless it can be explained by the learner;
- AI-generated or AI-assisted reasoning must be independently reviewed;
- tests and benchmarks must be executed rather than assumed;
- institutional or course-specific AI policies take precedence for graded work;
- responsibility for the final content remains human.

This makes AI a **scaffolding and review layer**, not a substitute for
understanding.

### Why use AI here?

Used carefully, AI can increase the quality of the learning loop by making
review more frequent and interactive.

Its main advantages in this repository are:

```text
Faster Feedback
        +
More Alternative Explanations
        +
Earlier Detection of Gaps
        +
Broader Test-Idea Generation
        +
Documentation Consistency
        +
Technical Communication Practice
        ↓
More Iterations of Human Reasoning
```

The intended benefit is not to remove intellectual work. It is to increase the
number and quality of opportunities to reason, verify, compare and improve.

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

No global Maven installation is required when the Maven Wrapper is used.

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

Passing tests provide evidence for selected executions.

They do not constitute a formal proof of algorithm correctness.

---

## Build JMH Benchmarks

### Windows

```powershell
.\mvnw.cmd -Pbenchmark clean package
```

### Linux / macOS

```bash
./mvnw -Pbenchmark clean package
```

The benchmark profile creates:

```text
target/benchmarks.jar
```

Run it with:

```bash
java -jar target/benchmarks.jar
```

Benchmark results are environment-dependent empirical measurements and must not
be presented as mathematical proof of asymptotic complexity.

---

## Testing Philosophy

Tests are derived from the documented contract.

Typical dimensions include:

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

```text
Formal Correctness
        ≠
Automated Testing
```

Both are required, but they answer different questions.

---

## Benchmarking Philosophy

A benchmark begins with a research question.

Examples:

```text
How does target position affect Linear Search execution time?

How does Binary Search behave as n grows?

What empirical differences appear between iterative and recursive Binary Search?
```

A benchmark is not added merely for file symmetry.

---

## Cross-Platform Execution

Equivalent verification entry points are:

```text
Windows
.\mvnw.cmd clean verify

Linux
./mvnw clean verify
```

Linux has a dedicated module because terminal usage, permissions, processes,
environment variables, Java tooling, Maven and Git are relevant backend
engineering skills.

---

## Development Workflow

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

Conventional Commit-style messages are preferred:

```text
feat(searching): add iterative binary search
test(searching): cover boundary search contracts
docs(searching): align implementation traceability
benchmark(searching): compare binary search variants
```

---

## Engineering Standards

The repository values explicit contracts, meaningful names, focused classes,
predictable package organisation, reproducible builds, selective benchmarking,
incremental branches, pull requests, and traceability between theory and
executable artefacts.

See
[`docs/00-project/08-engineering-standards.md`](docs/00-project/08-engineering-standards.md).

---

## Academic References

The principal academic reference is:

**Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; Stein, Clifford.  
*Introduction to Algorithms*. MIT Press.**

The project also uses recognised Computer Science literature and official
technical documentation.

The central bibliography is maintained in
[`docs/00-project/10-references.md`](docs/00-project/10-references.md).

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
