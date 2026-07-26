# References

## Algorithms Java Mastery

This document identifies the principal academic, technical, and official
references used throughout **Algorithms Java Mastery**.

The repository is grounded in recognised Computer Science literature, Java
language specifications, official tool documentation, and reproducible
engineering practices.

References support conceptual study, algorithmic reasoning, Java
implementation, automated testing, benchmarking, version control, and
continuous integration.

They provide an academic and technical foundation for the repository but do not
replace original analysis, independent reasoning, implementation, or
experimental validation.

---

# Purpose

This document establishes the reference framework of the project.

It defines:

- the principal academic sources;
- the official Java references;
- the documentation for project technologies;
- source-selection criteria;
- citation and attribution principles;
- the relationship between general and module-specific references;
- source-maintenance practices.

The objective is to ensure that important concepts, technical decisions, and
engineering practices are supported by identifiable and reliable sources.

---

# Reference Philosophy

**Algorithms Java Mastery** uses references as foundations for understanding,
not as substitutes for learning.

The project follows this progression:

```text
Reliable Source
        ↓
Critical Reading
        ↓
Conceptual Understanding
        ↓
Independent Explanation
        ↓
Algorithmic Reasoning
        ↓
Java Implementation
        ↓
Verification and Evaluation
```

A source may define a concept, present a theorem, describe an algorithm, or
document a technology.

The repository must then transform that information into:

- original explanations;
- independent reasoning;
- project-specific specifications;
- Java implementations;
- automated tests;
- controlled benchmarks;
- engineering conclusions.

The guiding principle is:

> **References support understanding, while the repository must demonstrate
> that understanding through original reasoning and verifiable work.**

---

# Scope of This Document

This file contains the principal references used across the entire repository.

It includes sources related to:

- algorithm design;
- data structures;
- computational complexity;
- correctness reasoning;
- Java programming;
- the Java Collections Framework;
- Maven;
- JUnit Jupiter;
- AssertJ;
- JMH;
- Git;
- GitHub;
- continuous integration;
- Linux development environments;
- software engineering practices.

More specialised sources may be included in the `references.md` file of an
individual documentation module.

---

# Reference Levels

References are maintained at two levels.

## Project-Level References

```text
docs/00-project/10-references.md
```

Project-level references support concepts and technologies used throughout the
repository.

Examples include:

- general algorithms textbooks;
- Java specifications;
- Maven documentation;
- testing documentation;
- benchmarking documentation;
- Git and GitHub references.

---

## Module-Level References

```text
docs/<module>/references.md
```

Module-level references support a particular area of study.

Examples include:

```text
docs/02-complexity/references.md
docs/05-sorting/references.md
docs/11-graphs/references.md
docs/17-benchmarking/references.md
```

These files may include:

- specialised books;
- research papers;
- academic lecture material;
- official documentation relevant to the module;
- additional sources used for a specific implementation or experiment.

Module-level references should not replace the principal project bibliography.

---

# Source Selection Criteria

Sources should be selected according to their authority, relevance, accuracy,
and educational value.

Preferred sources include:

1. recognised Computer Science textbooks;
2. peer-reviewed academic publications;
3. language and platform specifications;
4. official project documentation;
5. recognised technical standards;
6. official educational material from established institutions.

Sources should be evaluated by asking:

```text
Who produced the source?

Is the author or organisation authoritative?

Is the information technically verifiable?

Is the source relevant to the subject?

Is the version appropriate for the project?

Can the source be identified and accessed reliably?

Does another authoritative source support the same concept?
```

Popularity alone does not establish technical reliability.

---

# Source Priority

When several sources explain the same topic, the repository uses the following
general priority:

```text
Specification or Standard
        ↓
Official Documentation
        ↓
Recognised Academic Literature
        ↓
Peer-Reviewed Publication
        ↓
Established Educational Material
        ↓
Secondary Technical Explanation
```

The priority may vary according to the subject.

For example:

- Java syntax and behaviour should be checked against the Java Language
  Specification;
- JUnit configuration should be checked against the official JUnit
  documentation;
- asymptotic analysis should be supported by recognised algorithms literature;
- JMH usage should be based on official OpenJDK material.

Secondary articles may complement study but should not become the sole basis for
important technical claims when authoritative sources are available.

---

# Principal Academic References

## Introduction to Algorithms

Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; Stein, Clifford.

*Introduction to Algorithms*. Fourth Edition. MIT Press, 2022.

This book is one of the principal academic references of the repository.

It supports the study of:

- algorithmic reasoning;
- correctness;
- asymptotic analysis;
- recurrence relations;
- sorting;
- searching;
- data structures;
- hashing;
- trees;
- graphs;
- greedy algorithms;
- dynamic programming.

The repository does not reproduce the book's complete explanations,
illustrations, exercises, or pseudocode.

Its concepts are studied and expressed through original project documentation
and independent Java implementations.

---

## The Art of Computer Programming

Knuth, Donald E.

*The Art of Computer Programming*. Addison-Wesley.

Relevant volumes include:

- Volume 1: *Fundamental Algorithms*;
- Volume 3: *Sorting and Searching*.

This work provides historical, mathematical, and algorithmic depth.

It may support advanced study of:

- algorithm analysis;
- fundamental data structures;
- sorting;
- searching;
- mathematical reasoning;
- implementation trade-offs.

Due to its depth, it is used selectively according to the requirements of each
module.

---

## Algorithms

Sedgewick, Robert; Wayne, Kevin.

*Algorithms*. Fourth Edition. Addison-Wesley Professional, 2011.

This source supports practical and conceptual study of:

- sorting;
- searching;
- stacks and queues;
- symbol tables;
- trees;
- graphs;
- Java-oriented algorithm implementations;
- experimental algorithm analysis.

Its implementation-oriented perspective complements more formal algorithmic
literature.

---

## Algorithm Design

Kleinberg, Jon; Tardos, Éva.

*Algorithm Design*. Pearson, 2005.

This book supports the study of:

- problem modelling;
- algorithm design strategies;
- greedy algorithms;
- divide and conquer;
- dynamic programming;
- network flow;
- computational complexity;
- correctness arguments.

It is especially valuable for connecting problem understanding with strategy
selection and proof.

---

## Data Structures and Algorithm Analysis in Java

Weiss, Mark Allen.

*Data Structures and Algorithm Analysis in Java*. Pearson.

This source supports the study of:

- Java-based data structures;
- algorithm analysis;
- lists;
- stacks;
- queues;
- trees;
- hashing;
- priority queues;
- sorting;
- graph algorithms.

It complements language-independent theory with Java-oriented implementation
considerations.

---

## Algorithms in Java

Sedgewick, Robert.

*Algorithms in Java*. Addison-Wesley.

Relevant parts may be consulted for:

- Java representations of algorithms;
- sorting and searching;
- data structures;
- graph algorithms;
- implementation comparison.

This reference should be interpreted in relation to the Java version and
engineering standards currently adopted by the repository.

---

# Mathematics and Discrete Foundations

## Concrete Mathematics

Graham, Ronald L.; Knuth, Donald E.; Patashnik, Oren.

*Concrete Mathematics: A Foundation for Computer Science*. Second Edition.
Addison-Wesley, 1994.

This source may support:

- summations;
- recurrences;
- mathematical induction;
- discrete structures;
- combinatorial reasoning;
- asymptotic analysis.

It is particularly relevant when an algorithm requires deeper mathematical
justification.

---

## Discrete Mathematics and Its Applications

Rosen, Kenneth H.

*Discrete Mathematics and Its Applications*. McGraw-Hill.

This source supports foundational topics such as:

- logic;
- sets;
- functions;
- relations;
- proofs;
- induction;
- counting;
- recurrence relations;
- graphs;
- trees.

These topics provide mathematical support for correctness and complexity
analysis.

---

# Correctness and Program Reasoning

## A Discipline of Programming

Dijkstra, Edsger W.

*A Discipline of Programming*. Prentice Hall, 1976.

This work supports the study of:

- systematic program construction;
- correctness reasoning;
- invariants;
- formal development;
- disciplined problem solving.

Its principles contribute to the repository's emphasis on reasoning before
implementation.

---

## The Science of Programming

Gries, David.

*The Science of Programming*. Springer-Verlag, 1981.

This reference supports:

- formal program derivation;
- assertions;
- preconditions;
- postconditions;
- loop invariants;
- correctness reasoning.

It may be used in modules that require more formal treatment of algorithm
correctness.

---

## Programming Pearls

Bentley, Jon.

*Programming Pearls*. Addison-Wesley.

This source supports:

- problem decomposition;
- algorithmic insight;
- correctness;
- performance reasoning;
- implementation trade-offs;
- systematic problem solving.

Its case-study approach complements the repository's workflow from problem
understanding to engineering conclusions.

---

# Software Engineering References

## Clean Code

Martin, Robert C.

*Clean Code: A Handbook of Agile Software Craftsmanship*. Prentice Hall, 2008.

This source may support discussions about:

- naming;
- method responsibility;
- readability;
- duplication;
- code organisation;
- maintainability.

Its recommendations are evaluated critically and applied only when they improve
the clarity of algorithmic implementations.

The repository does not treat any software-engineering book as an unquestionable
set of universal rules.

---

## Refactoring

Fowler, Martin.

*Refactoring: Improving the Design of Existing Code*. Second Edition.
Addison-Wesley Professional, 2018.

This work supports:

- behaviour-preserving structural improvement;
- identification of code smells;
- incremental refactoring;
- maintainability;
- disciplined design evolution.

Refactoring practices are applied proportionally to the educational and
structural needs of the repository.

---

## Design Patterns

Gamma, Erich; Helm, Richard; Johnson, Ralph; Vlissides, John.

*Design Patterns: Elements of Reusable Object-Oriented Software*.
Addison-Wesley, 1994.

This book is the principal academic reference for classical object-oriented
design patterns.

Patterns relevant to the repository may include:

- Strategy;
- Factory Method;
- Template Method;
- Builder;
- Decorator;
- Observer.

Patterns are introduced only when they solve a defined design problem or
support a clear learning objective.

---

## Effective Java

Bloch, Joshua.

*Effective Java*. Third Edition. Addison-Wesley Professional, 2018.

This source supports Java engineering decisions related to:

- object creation;
- generics;
- methods;
- classes and interfaces;
- exceptions;
- immutability;
- API design;
- defensive programming;
- concurrency considerations.

Recommendations are applied according to the requirements of the algorithm or
data structure being implemented.

---

# Java Platform References

## Java Language Specification

Oracle.

*The Java Language Specification*.

The Java Language Specification is the authoritative reference for:

- Java syntax;
- type-system rules;
- classes and interfaces;
- generics;
- expressions;
- statements;
- exceptions;
- memory-model concepts;
- language semantics.

When language behaviour is uncertain, the specification takes precedence over
informal explanations.

Official reference:

```text
https://docs.oracle.com/javase/specs/
```

---

## Java Virtual Machine Specification

Oracle.

*The Java Virtual Machine Specification*.

This specification may support advanced study of:

- bytecode;
- class loading;
- runtime data areas;
- method invocation;
- execution behaviour;
- Java memory concepts.

It is not required for every algorithm module but may support performance and
runtime analysis.

Official reference:

```text
https://docs.oracle.com/javase/specs/
```

---

## Java Platform API Documentation

Oracle.

*Java Platform, Standard Edition API Specification*.

The API documentation is the principal reference for:

- core Java classes;
- arrays and utilities;
- collections;
- comparators;
- exceptions;
- mathematical operations;
- streams when relevant;
- standard-library contracts.

Official reference:

```text
https://docs.oracle.com/en/java/javase/
```

The documentation version consulted should correspond to the Java version
configured in `pom.xml`.

---

## Java Tutorials and Learning Material

Oracle.

*The Java Tutorials*.

These materials may support introductory review of:

- language fundamentals;
- classes and objects;
- generics;
- collections;
- exceptions;
- annotations;
- basic concurrency concepts.

Official reference:

```text
https://docs.oracle.com/javase/tutorial/
```

The tutorials may describe older Java versions, so important technical decisions
should be verified against the current API documentation or specification.

---

# Java Collections Framework References

The Java Collections Framework is studied using the Java Platform API
documentation.

Principal interfaces include:

```text
Collection
List
Set
Queue
Deque
Map
SortedSet
NavigableSet
SortedMap
NavigableMap
```

Relevant implementations include:

```text
ArrayList
LinkedList
HashSet
LinkedHashSet
TreeSet
PriorityQueue
ArrayDeque
HashMap
LinkedHashMap
TreeMap
```

The official API contracts should be consulted for:

- permitted elements;
- ordering;
- duplicate behaviour;
- null handling;
- iteration;
- modification;
- complexity-related implementation notes;
- thread-safety characteristics.

Official reference:

```text
https://docs.oracle.com/en/java/javase/
```

Implementation details should not be assumed when they are not guaranteed by
the public contract.

---

# Maven References

## Apache Maven Documentation

Apache Software Foundation.

*Apache Maven Documentation*.

Maven documentation supports:

- project structure;
- build lifecycles;
- dependency management;
- plugins;
- test execution;
- profiles;
- project configuration;
- reproducible command-line builds.

Official reference:

```text
https://maven.apache.org/guides/
```

---

## Maven Project Object Model

Apache Software Foundation.

*POM Reference*.

The POM reference is the authoritative source for the structure and meaning of
`pom.xml`.

Official reference:

```text
https://maven.apache.org/pom.html
```

The repository's `pom.xml` remains the authoritative local source for:

- Java version;
- dependency versions;
- plugins;
- source-set configuration;
- test configuration;
- benchmark configuration.

---

## Maven Build Lifecycle

Apache Software Foundation.

*Introduction to the Build Lifecycle*.

This reference supports understanding of phases such as:

```text
validate
compile
test
package
verify
install
deploy
```

Official reference:

```text
https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
```

The project commonly uses:

```bash
mvn test
mvn clean verify
```

---

# Testing References

## JUnit Jupiter

JUnit Team.

*JUnit 5 User Guide*.

The JUnit documentation is the principal reference for:

- test lifecycle;
- test annotations;
- assertions;
- parameterised tests;
- nested tests;
- repeated tests;
- extensions;
- test discovery;
- Maven integration.

Official reference:

```text
https://junit.org/junit5/docs/current/user-guide/
```

JUnit Jupiter is used as the main testing framework of the repository.

---

## AssertJ

AssertJ Project.

*AssertJ Core Documentation*.

AssertJ documentation supports:

- fluent assertions;
- collection assertions;
- array assertions;
- exception assertions;
- optional assertions;
- custom assertions;
- readable verification.

Official reference:

```text
https://assertj.github.io/doc/
```

AssertJ complements JUnit Jupiter but does not replace the testing framework.

---

## Mockito

Mockito Project.

*Mockito Documentation*.

Official reference:

```text
https://site.mockito.org/
```

Mockito may be studied as part of broader testing education.

However, most algorithm and data-structure implementations should remain
independent and deterministic enough to be tested without mocks.

Mockito should only be introduced when a genuine dependency-isolation problem
exists.

---

# Benchmarking References

## Java Microbenchmark Harness

OpenJDK.

*Java Microbenchmark Harness — JMH*.

JMH is the official benchmarking framework adopted by the repository.

It supports:

- warm-up;
- measurement iterations;
- forks;
- benchmark modes;
- state configuration;
- parameterised input;
- result consumption;
- controlled JVM execution.

Official reference:

```text
https://openjdk.org/projects/code-tools/jmh/
```

The official source repository may also be consulted for examples and
configuration guidance:

```text
https://github.com/openjdk/jmh
```

---

## Performance Evaluation Principles

Performance observations should also be informed by recognised work on Java
performance and experimental methodology.

Relevant considerations include:

- JVM warm-up;
- just-in-time compilation;
- dead-code elimination;
- constant folding;
- garbage collection;
- input generation;
- state scope;
- environmental variability;
- statistical interpretation.

Informal timing should not replace JMH when the objective is to produce
reliable Java microbenchmarks.

---

# Git References

## Git Documentation

The Git Project.

*Git Reference Documentation*.

Official reference:

```text
https://git-scm.com/docs
```

Git documentation supports:

- repositories;
- commits;
- branches;
- merges;
- rebasing;
- remotes;
- tags;
- configuration;
- history inspection;
- conflict resolution.

---

## Pro Git

Chacon, Scott; Straub, Ben.

*Pro Git*. Second Edition. Apress.

Official online edition:

```text
https://git-scm.com/book/en/v2
```

This book supports conceptual understanding of:

- distributed version control;
- Git internals;
- branching;
- collaboration;
- remote repositories;
- workflows;
- project history.

---

# GitHub References

## GitHub Documentation

GitHub.

*GitHub Docs*.

Official reference:

```text
https://docs.github.com/
```

GitHub documentation supports:

- repository management;
- branches;
- pull requests;
- reviews;
- issues;
- repository security;
- Markdown;
- GitHub Actions;
- collaboration workflows.

---

## GitHub Flavoured Markdown

GitHub.

*Basic Writing and Formatting Syntax*.

Official reference:

```text
https://docs.github.com/en/get-started/writing-on-github
```

This documentation supports:

- headings;
- lists;
- tables;
- links;
- code blocks;
- task lists;
- document formatting.

Project documentation should remain compatible with GitHub Markdown rendering.

---

# Continuous Integration References

## GitHub Actions Documentation

GitHub.

*GitHub Actions Documentation*.

Official reference:

```text
https://docs.github.com/en/actions
```

The documentation supports:

- workflow syntax;
- events;
- jobs;
- steps;
- runners;
- environment variables;
- dependency caching;
- Java configuration;
- Maven build automation.

The repository's workflow files are located under:

```text
.github/workflows/
```

Official documentation should be consulted when the workflow syntax or
available actions change.

---

# Linux References

## The Linux Command Line

Shotts, William.

*The Linux Command Line*.

Official reference:

```text
https://linuxcommand.org/tlcl.php
```

This source supports:

- shell navigation;
- files and directories;
- permissions;
- processes;
- environment variables;
- package management concepts;
- shell scripting;
- command-line development.

---

## GNU Documentation

Free Software Foundation.

*GNU Manuals Online*.

Official reference:

```text
https://www.gnu.org/manual/manual.html
```

GNU manuals may support the study of:

- Bash;
- Coreutils;
- Make concepts;
- text-processing tools;
- shell utilities.

---

## Linux Manual Pages

Linux manual pages provide local technical documentation for commands,
interfaces, and system behaviour.

Examples:

```bash
man git
man java
man bash
man chmod
man find
```

Manual pages should be interpreted according to the installed system and command
version.

---

# IntelliJ IDEA References

## IntelliJ IDEA Documentation

JetBrains.

*IntelliJ IDEA Documentation*.

Official reference:

```text
https://www.jetbrains.com/help/idea/
```

This documentation supports:

- project configuration;
- Maven integration;
- Java development;
- test execution;
- debugging;
- Git integration;
- inspections;
- code navigation.

IntelliJ IDEA supports the development process but does not define the
authoritative project build.

The project must remain buildable through Maven independently of the IDE.

---

# Documentation and Technical Writing References

## The Chicago Manual of Style

*The Chicago Manual of Style*. University of Chicago Press.

This reference may support general questions of:

- citation;
- bibliography;
- titles;
- publication information;
- formal written presentation.

The repository does not require complete adherence to a single academic citation
style, but reference entries should remain clear and consistent.

---

## Google Developer Documentation Style Guide

Google.

*Google Developer Documentation Style Guide*.

Official reference:

```text
https://developers.google.com/style
```

This guide may support:

- clarity;
- terminology;
- technical explanations;
- headings;
- procedures;
- code-related writing.

Project-specific conventions defined in
`06-documentation-architecture.md` take precedence where differences exist.

---

# Reference Entry Convention

Book references should include, whenever available:

```text
Author or authors.
Title.
Edition.
Publisher.
Year.
```

Example:

```text
Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; Stein,
Clifford. Introduction to Algorithms. Fourth Edition. MIT Press, 2022.
```

Academic articles should include:

```text
Author or authors.
Article title.
Journal or conference.
Volume and issue when applicable.
Year.
DOI or stable identifier when available.
```

Official documentation should include:

```text
Organisation or project.
Document or documentation title.
Official location.
Version or access context when relevant.
```

---

# In-Text Reference Convention

Documentation may refer to a source by author, organisation, or title.

Examples:

```text
According to Cormen et al., asymptotic notation describes the growth of
functions used to characterise algorithmic efficiency.
```

```text
The Java Language Specification defines the compile-time and runtime behaviour
of Java language constructs.
```

```text
The JUnit User Guide defines the lifecycle and extension model used by JUnit
Jupiter.
```

Direct quotations should be used sparingly.

Paraphrasing with correct attribution is preferred.

---

# Direct Quotations

Direct quotations may be used when the exact wording has academic or technical
importance.

A quotation should:

- remain brief;
- preserve the original wording;
- identify the source;
- be integrated into an original explanation;
- comply with copyright restrictions.

The repository should not be constructed through extensive quotation.

Understanding should be demonstrated through independent explanation.

---

# Pseudocode and Algorithm Attribution

Many recognised algorithms have a long academic and historical development.

When pseudocode or an implementation is influenced by a specific source, the
relationship should be identified.

However:

- textbook pseudocode should not be copied extensively;
- variable names and structure should not be reproduced mechanically;
- implementations should be derived from conceptual understanding;
- source-specific choices should be distinguished from general algorithmic
  knowledge.

An algorithm's common name does not imply ownership of every independent
implementation.

The repository should still identify the sources used to study it.

---

# Diagrams and Images

Original diagrams are preferred.

When an external diagram or image is used, the repository should verify:

- its licence;
- attribution requirements;
- modification permissions;
- distribution permissions;
- educational relevance.

A link to an external source does not automatically permit copying its visual
content.

When licence conditions are unclear, the concept should be represented through
an original diagram.

---

# Source-Derived Code

Code from external sources should not be copied into the repository unless its
licence permits reuse and the attribution requirements are satisfied.

The preferred process is:

```text
Study the concept
        ↓
Close the source
        ↓
Design an independent solution
        ↓
Implement using project conventions
        ↓
Verify through tests
```

If external code is intentionally adapted:

- the source must be identified;
- the licence must be compatible;
- modifications should be documented;
- the repository licence must be respected.

---

# Online Articles and Community Sources

Online articles, forums, videos, and community discussions may support
exploration or clarify a specific problem.

They should be used carefully.

Such sources may be useful for:

- discovering terminology;
- identifying common errors;
- comparing explanations;
- locating official documentation;
- understanding practical development experiences.

They should not be the sole authority for:

- language semantics;
- security-sensitive claims;
- algorithmic complexity;
- formal correctness;
- framework configuration;
- benchmark methodology.

Important conclusions should be verified through academic or official sources.

---

# Artificial Intelligence Assistance

Artificial intelligence tools may support:

- brainstorming;
- drafting;
- language revision;
- comparison of explanations;
- generation of practice questions;
- identification of topics requiring verification.

AI-generated content is not considered an authoritative source.

Any AI-assisted contribution should be reviewed for:

- technical accuracy;
- consistency;
- originality;
- unsupported claims;
- incorrect references;
- accidental duplication;
- alignment with project standards.

References must point to the academic, official, or technical source supporting
the concept—not to the AI tool that assisted the writing process.

The repository owner remains responsible for every integrated contribution.

---

# Reference Verification

Before a source is added, verify:

- the author's or organisation's identity;
- the document title;
- the edition or version;
- the publisher or official host;
- the publication year when available;
- the stability of the location;
- the relevance of the source.

References should not be added solely because they appear authoritative.

The source should have actually contributed to the project or have a defined
role in future study.

---

# Version Awareness

Technical documentation evolves.

When using official documentation, consider:

- the Java version;
- the Maven version;
- the testing-library version;
- the benchmark-library version;
- the operating system;
- the GitHub Actions syntax;
- the date of the documentation.

The project configuration remains the authoritative source for the versions
currently adopted by the repository.

References explain the technology, while `pom.xml` and workflow files define the
active project environment.

---

# Broken and Obsolete References

References should be reviewed periodically.

When a source becomes unavailable:

1. determine whether an official replacement exists;
2. update the location;
3. preserve complete bibliographic information;
4. avoid replacing an authoritative source with an unreliable summary;
5. update affected documents if the source content has changed.

Historical books and papers do not become invalid merely because they are old.

Their continued relevance should be evaluated according to the subject.

---

# Reference Maintenance

This document should evolve when:

- a new foundational source is adopted;
- an official technology changes substantially;
- the Java version changes;
- a tool is added or removed;
- a reference becomes obsolete;
- a specialised source becomes important across several modules;
- bibliographic information is corrected.

Module-specific sources should remain inside their respective modules unless
their relevance becomes project-wide.

---

# Relationship Between References and Evidence

Different sources of evidence serve different purposes.

```text
Academic Literature
        ↓
Supports theory and algorithmic reasoning

Official Documentation
        ↓
Defines language and tool behaviour

Java Implementation
        ↓
Produces executable behaviour

Automated Tests
        ↓
Verify documented expectations

JMH Benchmarks
        ↓
Produce controlled performance observations

Git History
        ↓
Preserves project evolution
```

A reference supports a claim, but project-specific behaviour should also be
verified through the appropriate executable evidence.

---

# Principal Reference Summary

| Area | Principal References |
|------|----------------------|
| Algorithms | Cormen et al.; Sedgewick and Wayne; Kleinberg and Tardos |
| Data Structures | Weiss; Sedgewick; Java API documentation |
| Mathematical Foundations | Graham, Knuth, and Patashnik; Rosen |
| Correctness | Dijkstra; Gries; Cormen et al. |
| Java Language | Java Language Specification |
| Java Runtime | Java Virtual Machine Specification |
| Java APIs | Java Platform API Specification |
| Java Engineering | Bloch; Fowler; Gamma et al. |
| Build System | Apache Maven Documentation |
| Testing | JUnit 5 User Guide; AssertJ Documentation |
| Benchmarking | OpenJDK JMH Documentation |
| Version Control | Git Documentation; Pro Git |
| Repository Platform | GitHub Documentation |
| Continuous Integration | GitHub Actions Documentation |
| Linux | The Linux Command Line; GNU manuals; manual pages |
| Development Environment | IntelliJ IDEA Documentation |

---

# Relationship with Project Documentation

This document forms part of the foundational documentation of the repository.

| Order | Document | Purpose |
|------:|----------|---------|
| **01** | `01-project-overview.md` | Introduces the project's vision, academic foundation, scope, and objectives. |
| **02** | `02-learning-objectives.md` | Defines the knowledge, competencies, and expected learning outcomes. |
| **03** | `03-study-methodology.md` | Describes how subjects are studied, analysed, practised, and reviewed. |
| **04** | `04-repository-architecture.md` | Explains the architectural organisation of the repository. |
| **05** | `05-technology-stack.md` | Defines the technologies used throughout the project. |
| **06** | `06-documentation-architecture.md` | Establishes documentation organisation, source practices, and quality standards. |
| **07** | `07-java-project-structure.md` | Defines the organisation of production code, tests, and benchmarks. |
| **08** | `08-engineering-standards.md` | Defines the engineering and quality standards applied throughout the repository. |
| **09** | `09-development-workflow.md` | Defines the complete contribution lifecycle. |
| **10** | `10-references.md` | Identifies the principal academic, technical, and official sources. |

Together, these documents define the academic, technical, organisational, and
engineering foundations of **Algorithms Java Mastery**.

---

# Conclusion

The reference framework of **Algorithms Java Mastery** provides a reliable
foundation for academic study and Java engineering.

Recognised textbooks support algorithmic theory, mathematical reasoning, data
structures, correctness, and design strategies.

Specifications and official documentation define Java behaviour and the tools
used to build, test, benchmark, version, and automate the repository.

References are not treated as content to reproduce mechanically.

They are studied critically and transformed into original explanations,
documented specifications, independent implementations, automated tests,
controlled experiments, and engineering conclusions.

By maintaining clear source-selection, attribution, verification, and
maintenance practices, the repository preserves academic integrity,
traceability, and long-term technical reliability.

---

# Related Documentation

| Order | Document | Purpose |
|------:|----------|---------|
| **01** | `01-project-overview.md` | Defines the project's academic foundation and scope. |
| **02** | `02-learning-objectives.md` | Identifies the knowledge supported by the reference framework. |
| **03** | `03-study-methodology.md` | Defines how sources are transformed into structured learning. |
| **05** | `05-technology-stack.md` | Identifies the technologies whose official documentation is referenced. |
| **06** | `06-documentation-architecture.md` | Defines reference organisation, writing conventions, and attribution practices. |
| **08** | `08-engineering-standards.md` | Establishes academic integrity and evidence requirements. |
| **09** | `09-development-workflow.md` | Integrates research and reference verification into the contribution lifecycle. |

---

# Next Module

```text
docs/01-foundations/
```

The foundational project documentation is now complete.

The next module begins the formal study of:

- algorithmic thinking;
- computational problems;
- problem understanding;
- formal specifications;
- preconditions and postconditions;
- correctness;
- invariants;
- edge cases;
- problem decomposition;
- systematic problem solving.

These foundations establish the reasoning skills required before studying
computational complexity, data structures, and individual algorithms.