# Engineering Standards

## Algorithms Java Mastery

This document defines the engineering standards adopted by
**Algorithms Java Mastery**.

Algorithms Java Mastery is inspired primarily by
Introduction to Algorithms
(Cormen, Leiserson, Rivest and Stein).

This document defines the engineering standards adopted throughout the
repository to transform the systematic study of the concepts presented in the
book into reliable, maintainable and professionally validated Java software.

---

# Purpose

This document establishes the quality standards applied throughout the
repository.
These standards support the transformation of theoretical Computer Science
knowledge into professional engineering practice.

It defines expectations for:

- repository organisation;
- documentation quality;
- Java source code;
- naming and formatting;
- algorithm implementation;
- input contracts and error handling;
- automated testing;
- performance benchmarking;
- version control;
- code review;
- continuous integration;
- maintainability and structural evolution.

Every document, implementation, test, benchmark, and repository change should
follow these standards when applicable.

---

# Engineering Philosophy

**Algorithms Java Mastery** follows a quality-driven and evidence-based
engineering approach.

Engineering quality is not determined by project size, number of technologies,
or architectural complexity.

It is demonstrated through:

- clear reasoning;
- correct implementations;
- explicit contracts;
- readable code;
- automated verification;
- controlled experimentation;
- traceable decisions;
- maintainable documentation.

The guiding principle is:

Introduction to Algorithms (CLRS)

↓

Computer Science Foundations

↓

Algorithmic Reasoning

↓

Java Implementation

↓

Automated Testing

↓

Benchmarking

↓

Engineering Evidence

Every technical decision should improve one or more of the following:

- clarity;
- correctness;
- cohesion;
- consistency;
- traceability;
- reproducibility;
- maintainability;
- educational value.

---

# Standards and Guidelines

This document distinguishes between two levels of engineering direction.

## Standards

Standards define expected project behaviour.

They should be followed unless a documented technical reason justifies an
exception.

Examples include:

- production code must not depend on test code;
- tests must be deterministic;
- benchmark code must remain separate from correctness tests;
- public behaviour must be documented and verified;
- source files must follow Java naming conventions.

## Guidelines

Guidelines describe preferred practices that depend on context.

Examples include:

- an algorithm may use static methods when it is stateless;
- an interface may be introduced when several implementations share a useful
  contract;
- a package may contain subpackages when its size justifies additional
  organisation.

Exceptions should be deliberate, technically justified, and documented when
they affect project consistency.

---

# Core Engineering Principles

The repository follows these principles:

- **Correctness before optimisation**
- **Theory before implementation**
- **Clarity before cleverness**
- **Simplicity before unnecessary abstraction**
- **Explicit contracts before assumptions**
- **Verification before performance conclusions**
- **Evidence before claims**
- **Consistency before individual preference**
- **Incremental improvement before large redesign**
- **Educational value before technological accumulation**

These principles guide architectural, implementation, testing, and
documentation decisions.

---

# Quality Throughout the Learning Process

Quality is not a final stage added after implementation.

It is applied throughout the complete study process.

```text
Introduction to Algorithms (CLRS)

↓

Computer Science Foundations

↓

Problem Understanding

↓

Specification

↓

Algorithm Design

↓

Java Implementation

↓

Testing

↓

Benchmarking

↓

Engineering Conclusions
```

Documentation records and connects the evidence produced during every stage.

A high-quality implementation must remain consistent with:

- the documented problem;
- its preconditions and postconditions;
- the selected algorithmic strategy;
- the correctness argument;
- the complexity analysis;
- the automated tests;
- any reported benchmark conclusions.

---

# Repository Standards

The repository structure should remain predictable and purpose-driven.

The project follows these standards:

- each top-level directory has one defined responsibility;
- documentation modules represent specific academic domains;
- Java packages represent computational domains;
- production code, tests, and benchmarks remain separated;
- directory and file naming remains consistent;
- empty structures are not added without a current purpose;
- obsolete structures are removed rather than preserved indefinitely;
- architectural changes are justified by actual project needs.

Repository organisation should improve navigation and traceability rather than
simulate the architecture of an unrelated business application.

---

# Documentation Standards

Documentation is treated as a first-class academic and engineering artefact.

Documents should:

- define their purpose clearly;
- use precise and consistent terminology;
- distinguish theory from Java-specific implementation details;
- explain reasoning rather than presenting conclusions alone;
- identify assumptions, constraints, and limitations;
- maintain traceability to related code, tests, or benchmarks;
- use original wording;
- cite academic or official sources where appropriate;
- avoid unnecessary repetition;
- preserve the modular structure defined by the documentation architecture.

Documentation should communicate knowledge, decisions, and evidence rather than
merely describe existing code.

---

# Academic Integrity

The repository must preserve academic integrity.

Documentation should:

- explain concepts using original language;
- distinguish original analysis from source-derived knowledge;
- identify external references clearly;
- avoid reproducing substantial protected content;
- avoid copying textbook pseudocode, diagrams, or explanations without
  permission;
- cite recognised sources when technical claims depend on them.

Academic sources support the learning process but do not replace independent
reasoning.

The repository should demonstrate understanding rather than transcription.

---

# Documentation Traceability

Important claims should be connected to supporting evidence.

Examples include:

```text
Introduction to Algorithms

↓

Documentation

↓

Implementation

↓

Testing

↓

Benchmarking

↓

Engineering Decision
```

Traceability allows the reader to understand where a conclusion originated and
how it can be verified.

---

# Java Source-Code Standards

Java source code should prioritise correctness, readability, and correspondence
with the documented algorithm.

Implementations should:

- solve one clearly defined problem;
- expose the studied algorithmic strategy;
- use descriptive names;
- keep control flow understandable;
- minimise duplicated logic;
- avoid hidden side effects;
- define input and output behaviour;
- remain independently testable;
- use abstractions only when they provide clear value;
- avoid coupling unrelated computational modules.

Compact code is not automatically better code.

An implementation should be easy to inspect, reason about, test, and compare
with its theoretical description.

---

# Java Language Version

The repository uses the Java version defined in `pom.xml`.

All source code should:

- compile with the configured Java version;
- avoid APIs unavailable in that version;
- use modern Java features only when they improve clarity or safety;
- avoid introducing syntax solely for novelty;
- remain compatible with the Maven build and continuous integration
  environment.

The build configuration is the authoritative source for the Java version.

IDE-specific settings must not override the project build requirements.

---

# Class Design

Each class should represent one primary responsibility.

Appropriate responsibilities include:

- one algorithm;
- one data structure;
- one algorithmic strategy;
- one validation component;
- one reusable support abstraction.

Classes should not combine:

- unrelated algorithms;
- console interaction and algorithm logic;
- production behaviour and test utilities;
- benchmark orchestration and algorithm implementation;
- miscellaneous helper methods without conceptual cohesion.

Generic container classes such as the following should be avoided:

```text
Algorithms
Helpers
Utilities
Miscellaneous
CommonFunctions
```

A broad name usually indicates an unclear responsibility.

---

# Method Design

Methods should:

- perform one coherent operation;
- use names that communicate intent;
- define observable behaviour clearly;
- avoid unnecessary parameters;
- return meaningful results;
- remain short enough to understand as a complete unit;
- separate validation from core logic when that improves clarity.

Method extraction should improve reasoning and reuse, not fragment a simple
algorithm into unnecessary layers.

Private helper methods may be used when they make the algorithm easier to
follow.

---

# Abstraction Standards

Interfaces, abstract classes, and design patterns should be introduced only when
they solve a defined problem.

An abstraction may be justified when it supports:

- several interchangeable implementations;
- reusable behavioural tests;
- consistent benchmark execution;
- separation between a contract and its implementations;
- comparison of algorithmic strategies;
- controlled extension of the repository.

An abstraction should not be introduced merely because a design pattern exists.

The preferred rule is:

> **Abstract after identifying a meaningful shared responsibility, not before.**

---

# Design Pattern Use

Design patterns may be applied when they improve the structure or educational
value of the repository.

Relevant patterns may include:

- Strategy;
- Factory;
- Template Method;
- Builder;
- Decorator;
- Observer.

Their use should remain proportional to the problem.

For example, Strategy may support comparison between several sorting
implementations sharing the same contract.

Patterns should not obscure the algorithm, increase coupling, or introduce
application-oriented architecture without necessity.

When a pattern is used, its purpose should be documented.

---

# Static Utility Design

Stateless algorithms may use static methods when this provides the clearest
representation.

A utility class should:

- be `final`;
- prevent instantiation;
- contain conceptually related operations;
- avoid mutable global state.

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

Instantiable classes are preferred when an implementation:

- maintains state;
- accepts configuration;
- depends on interchangeable strategies;
- represents a data structure;
- participates in a meaningful object-oriented contract.

---

# Encapsulation

Implementation details should remain private unless external access is required
by a documented contract.

Visibility should be restricted to the minimum necessary level.

- `public` exposes a project-level contract.
- package-private supports collaboration within a module.
- `protected` is used only for intentional inheritance.
- `private` protects internal state and helper behaviour.

Fields should generally remain private.

Mutable internal structures should not be exposed directly when doing so allows
external code to violate class invariants.

---

# Immutability and Mutability

Mutability decisions should be explicit.

An implementation should document whether it:

- modifies the original input;
- returns a new structure;
- preserves input order;
- maintains internal mutable state;
- exposes mutable results.

For sorting algorithms, the distinction between in-place and out-of-place
behaviour must remain clear because it affects:

- auxiliary-space analysis;
- test expectations;
- API design;
- benchmark interpretation.

Hidden mutation should be avoided.

---

# Input Contracts

Every public algorithm or data structure should define its input contract.

The contract should clarify:

- whether `null` is accepted;
- whether empty input is valid;
- whether duplicate values are supported;
- whether the input must be ordered;
- whether the input is modified;
- how absence is represented;
- which exceptions may be thrown;
- what result is expected.

Contracts should remain consistent across:

- documentation;
- implementation;
- automated tests;
- examples;
- benchmarks.

Comparable implementations should follow compatible contracts unless their
difference is a deliberate subject of study.

---

# Null Handling

Null behaviour should never be accidental.

An implementation should adopt and document one of the following approaches:

- reject `null` using an appropriate exception;
- support `null` values explicitly;
- treat `null` as an absent optional value;
- avoid exposing nullable results through a stronger return contract.

The selected behaviour should be tested.

`NullPointerException` should not be used as an undocumented side effect of an
unrelated operation.

---

# Error Handling

Errors should be handled according to the public contract.

Appropriate approaches may include:

- `IllegalArgumentException` for invalid argument values;
- `NullPointerException` through explicit validation when a required reference
  is `null`;
- `IllegalStateException` when an operation is invalid for the current state;
- a defined sentinel result when appropriate;
- an optional result when absence is expected behaviour.

Exceptions should communicate the cause of failure clearly.

Exceptions should not be used for ordinary control flow.

---

# Naming Conventions

Names should communicate responsibility and intent.

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

Names should avoid ambiguity.

Examples to avoid include:

```text
data
value2
tempObject
algorithmManager
doProcess
handleStuff
```

Short mathematical names such as `i`, `j`, `n`, `m`, or `k` are acceptable in
small and clearly understood algorithmic contexts.

---

# Boolean Naming

Boolean variables and methods should express a condition.

Examples:

```text
isEmpty
isSorted
containsTarget
hasNext
canInsert
```

Names that require the reader to infer whether `true` represents success,
failure, presence, or validity should be avoided.

---

# Formatting Standards

Java formatting should remain consistent across the repository.

Source code should:

- use consistent indentation;
- use braces consistently;
- avoid multiple statements on one line;
- separate logical sections with whitespace;
- keep line length readable;
- remove trailing whitespace;
- avoid excessive vertical fragmentation;
- preserve a consistent import order.

Formatting should be supported by the IDE but must not depend on proprietary
project files.

Formatting-only changes should not be mixed unnecessarily with behavioural
changes.

---

# Comments

Comments should explain information that the code cannot communicate clearly by
itself.

Useful comments may describe:

- a non-obvious invariant;
- an algorithmic trade-off;
- a subtle boundary condition;
- a deliberate implementation decision;
- a benchmark limitation;
- correspondence with documented pseudocode.

Comments should not repeat the code.

Poor example:

```text
index++; // Increment index
```

More useful example:

```text
// The right boundary remains exclusive throughout the search.
right = middle;
```

Outdated comments are worse than missing comments and should be updated with the
code.

---

# JavaDoc

Javadoc should be used for public APIs whose contract is not immediately
obvious.

JavaDoc may document:

- the purpose of the class or method;
- parameters;
- return values;
- mutation behaviour;
- preconditions;
- exceptional conditions;
- complexity when relevant;
- stability or ordering guarantees.

JavaDoc should complement module documentation rather than reproduce entire
academic explanations.

Internal methods do not require JavaDoc when their names and behaviour are
self-explanatory.

---

# Duplication

Duplicated logic should be reviewed carefully.

Duplication may indicate:

- a missing shared abstraction;
- repeated validation;
- unclear responsibility boundaries;
- implementations that should share a contract.

However, small intentional duplication may be preferable to introducing a
complex abstraction that obscures two independent algorithms.

Reuse should improve cohesion and understanding.

---

# Testing Standards

Automated testing is required whenever executable behaviour can be verified
meaningfully.

Tests are located under:

```text
src/test/java/
```

Tests should:

- verify documented behaviour;
- include representative cases;
- cover boundary and edge conditions;
- remain deterministic;
- remain independent;
- communicate intent clearly;
- avoid dependence on execution order;
- avoid unnecessary knowledge of private implementation details;
- fail for one understandable reason.

Testing provides executable evidence of correctness.

It does not replace formal reasoning, invariants, or complexity analysis.

---

# Test Design

Tests should follow a clear structure.

Conceptually:

```text
Arrange
        ↓
Act
        ↓
Assert
```

A test should establish:

- the initial input;
- the operation performed;
- the expected observable result.

The structure may remain implicit when the test is already clear.

Tests should not include unnecessary control flow or reproduce the algorithm
being tested.

---

# Test Naming

Test names should describe behaviour rather than implementation mechanics.

Preferred examples:

```text
returnsExpectedIndexWhenTargetExists()
returnsNotFoundWhenTargetIsAbsent()
sortsReverseOrderedInput()
preservesDuplicateValues()
rejectsNullInput()
handlesEmptyArray()
```

Avoid names such as:

```text
test1
testMethod
worksCorrectly
binarySearchTest
```

A failed test name should help identify the violated expectation.

---

# Test Coverage Categories

When applicable, tests should consider:

- typical input;
- empty input;
- single-element input;
- minimum valid input;
- maximum relevant input;
- ordered input;
- reverse-ordered input;
- duplicate values;
- absent targets;
- invalid input;
- structural invariants;
- mutation behaviour;
- repeated operations.

Not every algorithm requires every category.

Test selection should be based on the documented contract and known failure
risks.

---

# Parameterised Tests

JUnit Jupiter parameterised tests may be used when the same behaviour must be
verified across several inputs.

They are useful for:

- multiple edge cases;
- several algorithm implementations;
- input partitions;
- repeated contract verification.

Parameterised tests should improve readability rather than compress unrelated
scenarios into one method.

---

# AssertJ Standards

AssertJ assertions should communicate the expected behaviour directly.

Preferred assertions include:

```text
assertThat(result).isEqualTo(expected);
assertThat(values).containsExactly(expectedValues);
assertThat(result).isEmpty();
assertThatThrownBy(() -> operation())
        .isInstanceOf(IllegalArgumentException.class);
```

Assertions should avoid manual boolean comparisons when a more expressive
AssertJ operation exists.

Poor example:

```text
assertThat(result == expected).isTrue();
```

Preferred:

```text
assertThat(result).isEqualTo(expected);
```

---

# Test Independence

Each test must be executable independently.

Tests should not:

- depend on another test having executed first;
- mutate shared global state;
- depend on unpredictable external resources;
- rely on system-specific paths;
- assume a particular execution order;
- use timing as proof of functional correctness.

Shared fixtures should be reset for every test when mutation is involved.

---

# Determinism

Correctness tests must produce the same result under the same conditions.

When random data is required:

- use a fixed seed;
- record the seed;
- generate input outside the measured operation when benchmarking;
- ensure failures can be reproduced.

Non-deterministic failures reduce the evidential value of the test suite.

---

# Benchmarking Standards

Performance benchmarks are located under:

```text
src/jmh/java/
```

Benchmarks must use JMH rather than informal timing with
`System.currentTimeMillis()` or `System.nanoTime()` inside ordinary application
code.

Benchmarks should:

- measure one defined operation;
- document the implementation being evaluated;
- control input generation;
- specify input sizes;
- identify data distributions;
- avoid measuring setup work unintentionally;
- use appropriate JMH state;
- separate warm-up from measurement;
- consume results when required;
- record relevant environment information;
- remain reproducible.

Benchmarking evaluates observed behaviour.

It does not prove correctness or asymptotic complexity.

---

# Benchmark and Test Separation

Correctness tests and benchmarks have different responsibilities.

```text
JUnit Jupiter
        ↓
Does the implementation behave correctly?

JMH
        ↓
How does the implementation perform under defined conditions?
```

A benchmark must not replace an automated test.

A correctness test must not depend on execution time as evidence of success.

Production implementations must remain authoritative and should not be
duplicated inside benchmark classes.

---

# Performance Claims

Performance conclusions should be expressed carefully.

A benchmark result applies only to its defined conditions, including:

- Java version;
- JVM configuration;
- hardware;
- operating system;
- input size;
- input distribution;
- algorithm implementation;
- JMH configuration.

Avoid unsupported conclusions such as:

> Algorithm A is always faster than Algorithm B.

Prefer:

> Under the documented benchmark configuration and input distribution,
> implementation A produced a lower measured execution time than implementation
> B.

Experimental evidence should be interpreted alongside theoretical complexity.

---

# Maven Standards

Maven is the authoritative build system.

The repository should be buildable through the command line without requiring
IntelliJ IDEA.

The `pom.xml` should:

- define the Java version;
- manage dependencies centrally;
- separate test and benchmark dependencies appropriately;
- configure plugins explicitly when required;
- avoid unnecessary dependencies;
- preserve reproducible builds.

Dependencies should be introduced only when they solve a documented project
need.

Version changes should be deliberate and reviewed.

---

# Dependency Standards

Dependencies increase maintenance and compatibility responsibilities.

Before adding a dependency, consider:

```text
What project problem does it solve?

Can the Java standard library already solve it?

Is it required for a defined learning objective?

Is the dependency actively maintained?

Does it introduce unnecessary transitive dependencies?

Can the repository remain reproducible after its introduction?
```

Application frameworks should not be added to an algorithmic study repository
unless the project scope is formally changed.

---

# Version Control Standards

Git history should communicate project evolution clearly.

Contributions should use:

- focused branches;
- small logical commits;
- meaningful commit messages;
- reviewed pull requests;
- deliberate integration into `main`.

Commits should represent coherent changes.

Unrelated documentation, formatting, refactoring, and behavioural changes
should be separated when practical.

---

# Branch Standards

Branches should be created for a defined task or module.

Examples:

```text
docs/08-engineering-standards
docs/02-complexity
feat/binary-search
feat/merge-sort
test/binary-search-edge-cases
refactor/sorting-contract
benchmark/sorting-comparison
fix/binary-search-boundary
```

Branch names should:

- use lowercase;
- use hyphens;
- communicate the purpose;
- avoid spaces;
- remain short but identifiable.

A branch should not become a permanent alternative to `main`.

---

# Commit Message Standards

Commit messages should describe the change clearly.

The project may use prefixes such as:

```text
docs:
feat:
test:
fix:
refactor:
benchmark:
build:
ci:
chore:
```

Examples:

```text
docs: define engineering standards
feat: implement iterative binary search
test: add binary search boundary cases
fix: correct quicksort partition boundary
refactor: extract shared sorting contract
benchmark: compare merge sort input distributions
build: configure JMH source set
ci: execute Maven tests on pull requests
```

The message should describe what changed.

Avoid vague messages such as:

```text
changes
update
fix stuff
work
final version
```

---

# Pull Request Standards

A pull request should represent one coherent contribution.

Its description should identify:

- the purpose of the change;
- the principal files or modules affected;
- the reasoning behind important decisions;
- how the change was verified;
- any known limitations;
- related documentation or issues.

Before integration, the pull request should satisfy the applicable quality
checks.

---

# Code Review Standards

Review is an engineering activity.

A review should evaluate:

## Academic consistency

- Is the algorithm consistent with its documented description?
- Are assumptions and constraints explicit?
- Is the complexity analysis justified?
- Are external sources used appropriately?

## Implementation quality

- Is the code correct and understandable?
- Does each class have a clear responsibility?
- Are dependencies necessary?
- Are contracts and mutations explicit?
- Is unnecessary abstraction avoided?

## Verification

- Do tests cover important behaviours and edge cases?
- Are tests deterministic and independent?
- Do assertions communicate intent?
- Are benchmarks separated from tests?

## Repository consistency

- Does the change respect the documented architecture?
- Are naming and file-placement conventions followed?
- Are related documents and references updated?
- Does the Maven build remain successful?

Review comments should explain the engineering concern rather than express only
personal preference.

---

# Continuous Integration Standards

GitHub Actions validates the repository in a clean environment.

Continuous integration should verify at least:

- source-code checkout;
- configured Java version;
- Maven dependency resolution;
- project compilation;
- automated test execution;
- successful build completion.

CI configuration should remain understandable and proportional to the project.

A successful CI run does not eliminate the need for local verification, code
review, or academic reasoning.

Benchmark execution should not be added to every standard CI run unless its
duration and variability are controlled.

---

# Build Integrity

The repository should remain buildable from a clean checkout.

A contribution should not depend on:

- local IDE configuration;
- absolute file paths;
- untracked files;
- locally installed libraries outside Maven;
- operating-system-specific assumptions without documentation;
- generated output committed unnecessarily.

Build failures should be treated as project-quality failures.

---

# Generated Files

Generated files should not be committed unless they provide a documented project
benefit.

Typical excluded files include:

- compiled `.class` files;
- Maven `target/` directories;
- IDE caches;
- temporary benchmark output;
- local logs;
- operating-system metadata.

The `.gitignore` file should reflect the actual project environment without
hiding source or configuration files required for reproducibility.

---

# Security and Repository Hygiene

Although the repository does not process production data, basic security
practices still apply.

The repository must not contain:

- passwords;
- API keys;
- access tokens;
- private credentials;
- confidential personal data;
- machine-specific secrets.

Configuration examples should use placeholders.

Secrets accidentally committed must be removed and rotated when applicable.

---

# Maintainability

Long-term maintainability is a central quality objective.

Changes should:

- improve readability;
- preserve or strengthen cohesion;
- reduce ambiguity;
- minimise unnecessary duplication;
- maintain traceability;
- simplify future modification;
- avoid hidden dependencies;
- preserve the conceptual focus of each module.

The repository should become easier to understand as it evolves.

A larger repository is not automatically a more mature repository.

---

# Scalability

The repository is expected to grow through new algorithms, data structures,
tests, benchmarks, and documentation modules.

Scalability should result from:

- clear responsibilities;
- stable conventions;
- modular packages;
- consistent source-set structure;
- limited coupling;
- reusable contracts where justified;
- predictable documentation organisation.

Scalability does not require anticipating every possible future module.

New structures should be added when actual content and responsibilities require
them.

---

# Refactoring Standards

Refactoring should improve internal structure without changing documented
behaviour.

Before refactoring:

- relevant tests should exist;
- current behaviour should be understood;
- the structural problem should be identified.

After refactoring:

- tests should continue to pass;
- public contracts should remain stable unless intentionally changed;
- documentation should be updated when responsibilities move;
- unrelated behavioural changes should be avoided.

Refactoring should solve a recognised design problem rather than merely change
the code style.

---

# Technical Debt

Technical debt should be recorded when a known limitation cannot be addressed
immediately.

Examples include:

- incomplete edge-case coverage;
- temporary duplication;
- missing benchmark validation;
- outdated documentation;
- an abstraction that requires future review.

Technical debt should not be hidden behind vague comments.

A recorded limitation should explain:

- what the problem is;
- why it remains;
- what impact it has;
- what future action may resolve it.

---

# Continuous Improvement

Engineering standards may evolve as the repository matures.

A standard may be revised when the change:

- improves correctness;
- strengthens consistency;
- reduces ambiguity;
- supports new validated project needs;
- improves reproducibility;
- increases educational value;
- aligns the project with updated official practices.

Standards should evolve deliberately through documented changes.

They should not change solely because a new tool or trend appears.

---

# Definition of Completion

A contribution may be considered complete when the applicable conditions have
been satisfied.

## Documentation contribution

- the purpose is clear;
- terminology is consistent;
- sources are identified where required;
- related documents are updated;
- links and paths are valid;
- Markdown formatting is correct;
- wording is original and technically accurate.

## Algorithm or data structure contribution

- the problem and contract are documented;
- the implementation compiles;
- correctness has been reasoned about;
- complexity has been analysed;
- representative and edge cases are tested;
- naming and package conventions are followed;
- public behaviour is consistent with documentation.

## Benchmark contribution

- the production implementation already exists;
- correctness is verified separately;
- benchmark inputs and conditions are documented;
- setup and measurement are separated;
- conclusions remain limited to observed evidence;
- results are not represented as universal facts.

## Repository contribution

- the Maven build succeeds;
- automated tests pass;
- CI configuration remains valid;
- no unrelated generated files are committed;
- the commit and pull request communicate the change clearly.

Completion does not mean that a subject can never be improved.

It means the contribution meets the current documented quality expectations.

---

# Engineering Quality Model

The repository defines engineering quality through the interaction of several
forms of evidence.

```text
Introduction to Algorithms (CLRS)

↓

Computer Science Foundations

↓

Conceptual Understanding

↓

Problem Analysis

↓

Algorithm Design

↓

Java Implementation

↓

Automated Testing

↓

Benchmarking

↓

Engineering Evidence

↓

Professional Knowledge
```

No single activity establishes quality by itself.

Quality results from consistency between reasoning, code, verification, evidence,
and documentation.

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
| **08** | `08-engineering-standards.md` | Defines the engineering and quality standards applied throughout the repository. |
| **09** | `09-development-workflow.md` | Describes the workflow used to study, implement, verify, and integrate changes. |
| **10** | `10-references.md` | Lists the principal academic, technical, and official sources. |

Together, these documents define the academic, technical, and organisational
foundations of **Algorithms Java Mastery**.

---

# Conclusion

The engineering standards of **Algorithms Java Mastery** establish a consistent
quality model for academic study and Java implementation.

They connect documentation, algorithmic reasoning, source code, automated
testing, benchmarking, version control, review, and continuous integration.

The repository prioritises correctness, clarity, traceability, reproducibility,
and maintainability over unnecessary abstraction or technological complexity.

By applying these standards consistently, the project can evolve through new
study modules and implementations without losing its academic direction,
structural coherence, or engineering quality.

These engineering standards provide the framework through which the concepts
studied in Introduction to Algorithms are transformed into reliable,
maintainable, verifiable and professionally engineered Java implementations.

---

# Related Documentation

| Order | Document | Purpose |
|------:|----------|---------|
| **01** | `01-project-overview.md` | Defines the identity, scope, and academic direction of the project. |
| **03** | `03-study-methodology.md` | Establishes the study and validation process. |
| **04** | `04-repository-architecture.md` | Defines repository responsibilities and structural boundaries. |
| **05** | `05-technology-stack.md` | Presents the technologies governed by these standards. |
| **06** | `06-documentation-architecture.md` | Defines documentation quality and organisation. |
| **07** | `07-java-project-structure.md` | Defines Java package, source-set, test, and benchmark organisation. |
| **09** | `09-development-workflow.md` | Applies these standards throughout the contribution lifecycle. |
| **10** | `10-references.md` | Centralises the academic, official, and technical sources. |

---

# Next Document

```text
09-development-workflow.md
```

The next document defines the complete workflow followed throughout
**Algorithms Java Mastery**, from selecting and studying a computational problem
to specifying, implementing, testing, benchmarking, reviewing, and integrating
the resulting contribution.