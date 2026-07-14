# Technology Stack

## Algorithms Java Mastery

This document defines and justifies the technology stack adopted by **Algorithms Java Mastery**.

The technologies selected for this repository support the systematic study of algorithms, data structures, computational complexity, automated testing, performance experimentation, Linux-based development, version control, and continuous integration.

The technology stack follows a central principle:

> **A technology should be introduced because it supports a defined learning or engineering objective, not because it is currently popular or commonly listed in software projects.**

The repository therefore avoids unnecessary frameworks and infrastructure that do not contribute directly to its academic and technical objectives.

---

# 1. Technology Selection Principles

Technology decisions are based on the following criteria:

- academic relevance;
- technical clarity;
- reproducibility;
- maintainability;
- industry relevance;
- compatibility with Java development;
- support for automated verification;
- support for controlled experimentation;
- cross-platform development;
- integration with modern software engineering workflows.

The project distinguishes between:

```text
Core Language
      ↓
Java

Build and Dependency Management
      ↓
Maven

Automated Testing
      ↓
JUnit Jupiter + AssertJ

Performance Experimentation
      ↓
JMH

Development Environment
      ↓
Windows + Linux

Version Control
      ↓
Git

Repository Platform
      ↓
GitHub

Continuous Integration
      ↓
GitHub Actions
```

Each technology has a defined responsibility.

---

# 2. Java

Java is the principal programming language of the project.

The repository uses Java to translate algorithmic reasoning into executable implementations.

The role of Java is:

```text
Algorithmic Strategy
        ↓
Pseudocode
        ↓
Java Implementation
        ↓
Executable Behaviour
```

Java is not the starting point of the study methodology.

The project first analyses the computational problem and designs an algorithm independently of programming syntax.

Only after the strategy has been defined is the algorithm implemented in Java.

This distinction is fundamental.

> **Programming language knowledge does not replace algorithmic reasoning.**

---

# 3. Why Java Was Selected

Java was selected because it provides a suitable environment for studying algorithms while simultaneously developing professional backend engineering competencies.

Relevant characteristics include:

- static typing;
- explicit data types;
- object-oriented programming support;
- generic types;
- standard data structures;
- mature development tooling;
- automated testing ecosystems;
- JVM-based execution;
- cross-platform compatibility;
- mature performance analysis tools.

Java also provides direct access to the Java Collections Framework.

This allows educational implementations to be compared with standard library abstractions.

For example:

```text
Educational Structure
        ↓
Custom Stack

Java Standard Abstraction
        ↓
Deque
```

or:

```text
Educational Structure
        ↓
Custom Hash Table

Java Standard Abstraction
        ↓
HashMap
```

The objective is not to replace Java standard library implementations.

The objective is to understand the computational principles that motivate these structures.

---

# 4. Java Development Kit

The project requires a Java Development Kit.

The JDK provides the tools necessary to compile and execute Java code.

Conceptually:

```text
.java Source Code
        ↓
Java Compiler
        ↓
.class Bytecode
        ↓
Java Virtual Machine
        ↓
Program Execution
```

The project should explicitly document the selected JDK version.

A consistent JDK version contributes to reproducibility across:

- local Windows development;
- Linux development;
- continuous integration environments.

The same major Java version should be used whenever practical.

---

# 5. Java 21

The project adopts **Java 21** as its principal Java version.

Java 21 is a Long-Term Support release and provides a stable foundation for the repository.

The project does not adopt Java 21 merely to use modern language features.

The principal objective remains algorithmic understanding.

Modern Java features should be used when they improve:

- clarity;
- correctness;
- type safety;
- maintainability.

They should not obscure the algorithm being studied.

For example, an algorithm should not be compressed into a highly abstract functional expression if doing so makes its computational state difficult to analyse.

Prefer code whose algorithmic behaviour can be traced.

```java
for (int index = 1; index < values.length; index++) {
    if (values[index] > maximum) {
        maximum = values[index];
    }
}
```

This implementation exposes:

- traversal;
- comparison;
- candidate state;
- state update.

For algorithmic study, observability of the process is important.

---

# 6. Java Virtual Machine

Java applications execute through the Java Virtual Machine.

The JVM contributes to platform independence.

```text
Java Source
     ↓
Bytecode
     ↓
JVM
   ↙   ↘
Windows Linux
```

The same compiled Java model can execute in different operating environments when compatible JVM implementations are available.

This characteristic supports the cross-platform objectives of the repository.

However, JVM execution introduces important considerations for performance experimentation.

These include:

- Just-In-Time compilation;
- runtime optimisation;
- garbage collection;
- class loading;
- warmup behaviour.

These characteristics are particularly relevant to the benchmarking module.

---

# 7. Java Collections Framework

The Java Collections Framework is an important technical reference for the project.

It provides standard interfaces and implementations for common data structures.

Relevant abstractions include:

```text
Collection
├── List
├── Set
└── Queue

Map
```

Relevant implementations may include:

```text
ArrayList
LinkedList
HashSet
HashMap
ArrayDeque
PriorityQueue
TreeMap
TreeSet
```

The project uses the Collections Framework in two ways.

First, as a professional Java development tool.

Second, as a technical comparison point for educational data structure implementations.

For example:

```text
Study Hashing Theory
        ↓
Implement Educational Hash Structure
        ↓
Analyse Collision Strategy
        ↓
Study HashMap Abstraction
        ↓
Compare Design Responsibilities
```

The project should clearly distinguish:

> **Implementing a data structure to understand it is different from implementing a data structure because production software requires a custom replacement.**

In professional Java development, standard library abstractions should generally be evaluated before designing custom alternatives.

---

# 8. Maven

Apache Maven is the build and dependency management tool adopted by the project.

Maven provides a standard project model for Java applications.

Its role includes:

- project configuration;
- dependency management;
- source compilation;
- test execution;
- plugin configuration;
- build lifecycle management.

The central configuration file is:

```text
pom.xml
```

Conceptually:

```text
pom.xml
   ↓
Maven Project Model
   ↓
Dependencies + Plugins + Build Configuration
   ↓
Build Lifecycle
```

Maven contributes to repository reproducibility.

A developer should not need to manually download JUnit, AssertJ, or other Java libraries and configure local classpaths individually.

Dependencies are declared through the project model.

---

# 9. Why Maven Was Selected

Maven was selected because it provides:

- conventional Java project structure;
- declarative dependency management;
- standard build lifecycle;
- mature plugin support;
- integration with testing tools;
- integration with CI environments;
- strong relevance to Java backend development.

The standard source layout is:

```text
src/
├── main/
│   └── java/
└── test/
    └── java/
```

This structure is recognised by Maven and common Java development tools.

The repository intentionally follows conventions where they improve interoperability.

> **Convention reduces unnecessary project-specific configuration.**

---

# 10. Maven Build Lifecycle

Maven defines a build lifecycle composed of phases.

Relevant phases include:

```text
validate
   ↓
compile
   ↓
test
   ↓
package
   ↓
verify
   ↓
install
```

These phases represent different stages of project validation.

For example:

```text
mvn test
```

compiles the required source code and executes automated tests.

A command such as:

```text
mvn clean test
```

removes previous build artefacts and executes the test lifecycle from a clean project state.

The project should study Maven commands through lifecycle concepts rather than memorising isolated terminal instructions.

---

# 11. Maven Wrapper

The Maven Wrapper is adopted to improve build reproducibility.

Relevant files may include:

```text
.mvn/
mvnw
mvnw.cmd
```

The wrapper allows the project to invoke the configured Maven distribution.

Windows:

```text
mvnw.cmd clean test
```

Linux:

```text
./mvnw clean test
```

Conceptually:

```text
Repository
    ↓
Maven Wrapper
    ↓
Configured Maven Environment
    ↓
Project Build
```

The Maven Wrapper reduces dependence on a manually configured global Maven installation.

A compatible JDK remains required.

---

# 12. JUnit Jupiter

JUnit Jupiter is the primary testing framework used by the repository.

JUnit Jupiter provides the programming and extension model associated with modern JUnit 5 testing.

Its role is to define and execute automated tests.

Conceptually:

```text
Algorithm Specification
        ↓
Expected Behaviour
        ↓
JUnit Test
        ↓
Automated Execution
        ↓
Test Result
```

JUnit Jupiter supports features such as:

- test methods;
- lifecycle annotations;
- parameterised tests;
- exception testing;
- test organisation.

A basic test may follow this structure:

```java
@Test
void shouldReturnMaximumValue() {
    int[] values = {7, 2, 15, 4, 9};

    int result = ArrayMaximum.find(values);

    assertThat(result).isEqualTo(15);
}
```

JUnit defines the test execution structure.

Assertions may be expressed through AssertJ.

---

# 13. Why JUnit Jupiter Was Selected

JUnit Jupiter was selected because it provides a mature automated testing model for modern Java development.

The framework allows the project to transform algorithm specifications into executable verification cases.

For example:

```text
Postcondition
    ↓
The returned value is the maximum array element.

Edge Case
    ↓
The maximum value is located at the final position.

Test
    ↓
Verify the returned result.
```

Testing is integrated into the study methodology.

The project does not treat tests as an activity added after implementation is considered complete.

Instead:

```text
Specification
      ↓
Test Strategy
      ↓
Implementation
      ↓
Automated Verification
```

Tests provide evidence about implementation behaviour.

They do not constitute a mathematical proof of algorithmic correctness.

---

# 14. AssertJ

AssertJ is the assertion library adopted by the project.

Its role is to express expected test outcomes using readable assertion APIs.

For example:

```java
assertThat(result).isEqualTo(15);
```

or:

```java
assertThat(values)
        .containsExactly(2, 4, 7, 9, 15);
```

AssertJ complements JUnit Jupiter.

The responsibilities are:

```text
JUnit Jupiter
        ↓
Defines and executes tests.

AssertJ
        ↓
Expresses assertions.
```

The technologies should not be conceptually confused.

JUnit answers:

> **What is a test and how is it executed?**

AssertJ answers:

> **How can the expected result be expressed clearly?**

---

# 15. Why AssertJ Was Selected

AssertJ was selected because expressive assertions improve test readability.

Compare:

```java
assertEquals(15, result);
```

with:

```java
assertThat(result).isEqualTo(15);
```

Both can verify the same condition.

The second form provides a fluent assertion structure.

For collections and arrays, expressive assertions may become particularly useful.

For example:

```java
assertThat(result)
        .isNotNull()
        .hasSize(5)
        .containsExactly(2, 4, 7, 9, 15);
```

The project uses assertion readability as part of test communication.

A test is executable verification, but it is also technical documentation of expected behaviour.

---

# 16. Java Microbenchmark Harness

Java Microbenchmark Harness, commonly referred to as JMH, is the performance experimentation framework selected for the repository.

JMH is used when controlled microbenchmarking of Java code is required.

Its purpose is not to determine algorithmic complexity automatically.

The relationship is:

```text
Theoretical Complexity Analysis
        ↓
Predicts growth behaviour.

JMH Experiment
        ↓
Measures runtime observations.
```

For example:

```text
Linear Search
      ↓
Theoretical Analysis
      ↓
O(n) worst-case time

JMH
      ↓
Observe behaviour for increasing input sizes
```

The benchmark does not prove that the algorithm is `O(n)`.

The complexity analysis provides that theoretical classification.

The benchmark provides empirical observations about the implementation.

---

# 17. Why JMH Was Selected

Naive Java timing techniques can produce misleading results.

For example:

```java
long start = System.nanoTime();

algorithm.execute();

long end = System.nanoTime();
```

This code can measure elapsed time.

However, JVM execution introduces factors such as:

- JIT compilation;
- warmup;
- runtime optimisation;
- dead-code elimination;
- garbage collection;
- measurement noise.

JMH provides infrastructure specifically designed for Java microbenchmarking.

Relevant concepts include:

```text
Warmup
Measurement
Forks
Benchmark State
Blackhole
Benchmark Modes
```

The project uses JMH to develop experimental discipline.

The objective is not to generate attractive performance numbers.

The objective is to design interpretable experiments.

---

# 18. Git

Git is the distributed version control system used by the project.

Its role is to preserve and communicate repository evolution.

Git tracks changes to:

- source code;
- tests;
- documentation;
- benchmark infrastructure;
- build configuration;
- CI workflows.

The conceptual workflow is:

```text
Working Directory
        ↓
Staging Area
        ↓
Commit
        ↓
Repository History
```

Relevant commands include:

```text
git status
git add
git commit
git branch
git switch
git pull
git push
```

Commands should be understood through the Git state model.

The project avoids memorising Git commands without understanding which repository state they modify.

---

# 19. Git Branching

Branches support isolated repository changes.

A branch may represent:

- a new algorithm;
- documentation development;
- test integration;
- benchmark infrastructure;
- CI configuration.

Possible branch names include:

```text
feature/binary-search
docs/complexity-analysis
test/binary-search
benchmark/linear-search
ci/maven-workflow
```

Branch names should communicate the purpose of the change.

The project uses branching as an engineering discipline rather than as unnecessary process overhead.

---

# 20. GitHub

GitHub is the remote repository platform used by the project.

Its responsibilities include:

- remote Git repository hosting;
- project visibility;
- source history access;
- pull request workflows;
- repository documentation;
- GitHub Actions integration.

The relationship is:

```text
Git
 ↓
Version Control System

GitHub
 ↓
Repository Hosting and Collaboration Platform
```

Git and GitHub are related but not equivalent technologies.

The repository should preserve this conceptual distinction.

---

# 21. Pull Requests

Pull requests are used to review repository changes before integration into the principal branch.

Even when the repository is developed primarily by one person, pull requests can support self-review.

The workflow may be:

```text
Create Branch
      ↓
Develop Change
      ↓
Run Tests
      ↓
Commit
      ↓
Push Branch
      ↓
Open Pull Request
      ↓
Review Diff
      ↓
Validate CI
      ↓
Merge
```

A pull request creates a review boundary.

It provides an opportunity to ask:

```text
Does the implementation match the documented algorithm?

Are edge cases tested?

Is complexity documented correctly?

Does the project build?

Does CI pass?

Is the repository architecture preserved?
```

---

# 22. Windows

Windows is the initial local development environment of the project.

The repository may be developed using Windows while the learner progressively develops Linux competencies.

Windows supports:

- IntelliJ IDEA;
- JDK execution;
- Maven;
- Git;
- local testing;
- repository management.

The use of Windows does not conflict with the Linux learning objective.

The progression is intentionally cross-platform.

```text
Windows Development
        ↓
Repository Implementation
        ↓
Linux Validation
```

The same repository is preserved.

---

# 23. Linux

Linux is integrated as a professional development and validation environment.

The project studies Linux in the context of Java backend engineering.

Relevant areas include:

- filesystem navigation;
- terminal usage;
- file permissions;
- environment configuration;
- Java execution;
- Maven Wrapper execution;
- Git workflows;
- process observation;
- cross-platform validation.

Linux is not used as an algorithm implementation technology.

The algorithms remain Java algorithms.

Linux provides an execution and engineering environment.

```text
Java Algorithm
       ↓
JVM
   ↙       ↘
Windows   Linux
```

The repository should progressively demonstrate that its build and automated tests can execute in Linux.

---

# 24. Why Linux Was Integrated

Linux was integrated because modern backend development frequently interacts with Linux-based environments.

Relevant professional contexts include:

- development servers;
- cloud environments;
- containers;
- CI runners;
- virtual machines;
- production systems.

The project does not attempt to transform the learner into a Linux system administrator.

The objective is:

> **Develop practical Linux fluency appropriate for a Java backend developer.**

This includes understanding the environment in which Java applications and engineering automation frequently execute.

---

# 25. IntelliJ IDEA

IntelliJ IDEA is the principal integrated development environment used during local development.

Its role includes:

- Java source editing;
- code navigation;
- refactoring support;
- Maven integration;
- test execution;
- Git integration;
- debugging.

The IDE improves development productivity.

However, the repository should not depend on IntelliJ IDEA to function.

This distinction is important.

```text
IntelliJ IDEA
      ↓
Development Tool

Repository
      ↓
Independent Build Model
```

The project should remain executable through Maven outside the IDE.

For example:

```text
./mvnw clean test
```

The ability to build the project outside IntelliJ contributes to reproducibility.

---

# 26. GitHub Actions

GitHub Actions is the automation platform used for continuous integration.

The repository defines workflow configuration under:

```text
.github/workflows/
```

The principal workflow is:

```text
maven-ci.yml
```

The expected CI process is:

```text
Git Push or Pull Request
        ↓
Workflow Trigger
        ↓
Runner Provisioning
        ↓
Repository Checkout
        ↓
Java Configuration
        ↓
Maven Execution
        ↓
JUnit Test Execution
        ↓
Workflow Result
```

GitHub Actions provides automated repository validation.

---

# 27. Why GitHub Actions Was Selected

GitHub Actions was selected because the repository is hosted on GitHub and workflow automation can be maintained alongside the project.

The workflow definition is version controlled.

```text
Application Changes
Documentation Changes
Build Changes
CI Changes
        ↓
Git Repository History
```

This allows CI configuration to evolve transparently.

GitHub Actions also exposes the project to Linux-based automated execution environments.

This creates a relationship between:

```text
Linux
Maven
JUnit
GitHub
Continuous Integration
```

The learner should understand this relationship rather than treating the workflow as copied YAML configuration.

---

# 28. Continuous Integration

Continuous integration is a software engineering practice rather than an individual technology.

In this repository, CI is implemented through GitHub Actions.

The initial CI objective is:

```text
Retrieve Repository
        ↓
Configure Java
        ↓
Execute Maven Build
        ↓
Run Automated Tests
        ↓
Report Validation Result
```

The workflow provides evidence that the committed project state can be validated outside the local development environment.

CI helps identify:

- missing files;
- incorrect dependency declarations;
- environment assumptions;
- failing tests;
- build configuration problems.

---

# 29. Continuous Delivery and Deployment

The project studies continuous delivery conceptually.

However, **Algorithms Java Mastery** is not primarily a deployable backend service.

Therefore, the repository does not introduce artificial deployment infrastructure solely to simulate a production system.

The distinction is:

```text
Continuous Integration
        ↓
Automatically build and test changes.

Continuous Delivery
        ↓
Maintain software in a releasable state.

Continuous Deployment
        ↓
Automatically deploy validated changes.
```

The principal practical emphasis of this repository is continuous integration.

Deployment practices will be more extensively applied in backend application projects.

---

# 30. Technology Responsibility Matrix

The technology stack can be summarised as follows:

| Technology | Principal Responsibility |
|---|---|
| Java | Algorithm implementation |
| JDK 21 | Java development and execution environment |
| JVM | Bytecode execution |
| Java Collections Framework | Standard data structure abstractions |
| Maven | Build and dependency management |
| Maven Wrapper | Reproducible Maven invocation |
| JUnit Jupiter | Automated test structure and execution |
| AssertJ | Expressive test assertions |
| JMH | Controlled Java microbenchmarking |
| Git | Distributed version control |
| GitHub | Remote repository platform |
| Windows | Initial local development environment |
| Linux | Backend-oriented development and validation environment |
| IntelliJ IDEA | Local Java development environment |
| GitHub Actions | Workflow automation |
| CI | Automated repository validation |

Each technology has a defined responsibility.

The project avoids introducing technologies with overlapping responsibilities without technical justification.

---

# 31. Technologies Intentionally Excluded

The repository intentionally excludes several technologies commonly associated with Java backend development.

Examples include:

```text
Spring Boot
Spring Framework
Hibernate
JPA
Docker
Kubernetes
Kafka
Relational Databases
NoSQL Databases
REST APIs
Microservices Frameworks
```

Their exclusion is intentional.

The project domain is:

> **Algorithms, data structures, complexity analysis, testing, benchmarking, and foundational engineering practices.**

Introducing Spring Boot would add concerns such as:

- dependency injection;
- web infrastructure;
- application contexts;
- HTTP;
- framework configuration.

These concerns do not improve the study of binary search, merge sort, graphs, or dynamic programming.

The project applies the principle:

> **Use the minimum technical infrastructure necessary to study the target problem rigorously.**

Backend technologies will be applied in repositories whose domain requires backend architecture.

---

# 32. Why Docker Is Not Initially Required

Docker is not required during the initial stages of the project.

The project already introduces:

```text
Java
Maven
Testing
JMH
Git
Linux
CI
```

Adding containerisation before a defined containerisation problem exists would increase infrastructure complexity.

The project should first establish:

```text
Local Build
      ↓
Automated Tests
      ↓
Linux Validation
      ↓
CI Validation
```

Docker may be evaluated later if the repository develops a concrete reproducibility or environment-isolation requirement.

Technology adoption should follow technical necessity.

---

# 33. Technology Integration Model

The complete technology relationship is:

```text
Algorithmic Theory
        ↓
Java
        ↓
Maven
        ↓
JUnit Jupiter + AssertJ
        ↓
JMH
        ↓
Git
        ↓
GitHub
        ↓
Windows and Linux Validation
        ↓
GitHub Actions
        ↓
Continuous Integration
```

The technologies are not studied as disconnected tools.

Each technology is introduced when the project develops a question that requires it.

```text
How do I implement the algorithm?
        ↓
Java

How do I build the project reproducibly?
        ↓
Maven

How do I verify implementation behaviour?
        ↓
JUnit Jupiter + AssertJ

How do I observe performance experimentally?
        ↓
JMH

How do I preserve project evolution?
        ↓
Git

How do I validate the project in another environment?
        ↓
Linux

How do I automate repository validation?
        ↓
GitHub Actions
```

---

# 34. Technology Learning Principle

The repository does not consider a technology learned because a command was executed successfully.

For each technology, the learner should progressively answer:

```text
What problem does this technology solve?

What responsibility does it own?

How does it interact with the repository?

What configuration does it require?

What happens when it fails?

How can its behaviour be verified?

What alternatives exist?

Why is it appropriate for this project?
```

For example:

Knowing:

```text
mvn test
```

is not equivalent to understanding Maven.

The learner should understand:

```text
Command
   ↓
Lifecycle Phase
   ↓
Plugin Execution
   ↓
Test Discovery
   ↓
JUnit Execution
   ↓
Build Result
```

The same principle applies to Git, JUnit, JMH, Linux, and GitHub Actions.

---

# 35. Technology Evolution Principle

The technology stack may evolve as the project develops.

However, a new technology should be introduced only after evaluating:

```text
What problem does it solve?

Does the repository currently have this problem?

Does an existing technology already solve it?

What complexity does the new technology introduce?

What learning objective does it support?

Can the decision be technically justified?
```

The project avoids technology accumulation.

A larger technology stack does not automatically represent greater engineering maturity.

> **Engineering maturity is demonstrated by justified technical decisions and clear responsibility boundaries.**

---

# 36. Final Technology Principle

The technology stack of **Algorithms Java Mastery** supports the transition from algorithmic theory to reproducible software engineering practice.

```text
Understand
    ↓
Design
    ↓
Implement
    ↓
Build
    ↓
Verify
    ↓
Measure
    ↓
Version
    ↓
Validate
    ↓
Automate
```

The technologies correspond to this progression:

```text
Algorithmic Theory
        ↓
Java

Project Build
        ↓
Maven

Automated Verification
        ↓
JUnit Jupiter + AssertJ

Performance Experimentation
        ↓
JMH

Version Control
        ↓
Git

Repository Platform
        ↓
GitHub

Development Environments
        ↓
Windows + Linux

Automation
        ↓
GitHub Actions
```

The stack can be summarised through the following principle:

> **Technology should support understanding, verification, reproducibility, and engineering discipline without obscuring the computational problem being studied.**