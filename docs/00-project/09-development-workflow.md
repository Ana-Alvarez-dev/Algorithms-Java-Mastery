# Development Workflow

## Algorithms Java Mastery

This document defines the development workflow adopted by
**Algorithms Java Mastery**.

Algorithms Java Mastery is inspired primarily by
Introduction to Algorithms
(Thomas H. Cormen, Charles E. Leiserson,
Ronald L. Rivest and Clifford Stein).

This document defines the development workflow through which the concepts
studied throughout the book are progressively transformed into documented,
implemented, verified and professionally engineered Java solutions.

---

# Purpose

This document defines the contribution lifecycle followed throughout the
repository.
The workflow provides a repeatable engineering process for transforming
Computer Science knowledge into professional Java implementations supported by
objective evidence.

It establishes:

- the sequence of development activities;
- the expected result of each stage;
- the relationship between documentation and executable artefacts;
- verification and performance-evaluation practices;
- version-control and review activities;
- repository integration requirements;
- completion criteria;
- continuous-improvement principles.

The workflow provides a repeatable process for developing algorithms, data
structures, tests, benchmarks, and their associated documentation.

---

# Workflow Scope

The workflow applies to contributions involving:

- academic documentation;
- algorithm implementations;
- data structure implementations;
- automated tests;
- JMH benchmarks;
- architectural improvements;
- build configuration;
- continuous integration;
- module maintenance.

Not every contribution requires every stage with the same level of detail.

For example:

- a conceptual documentation correction may not require Java implementation;
- a new executable algorithm normally requires specification and testing;
- a benchmark is introduced only when experimental evaluation provides
  meaningful educational value;
- a repository configuration change may require build validation rather than
  algorithmic complexity analysis.

Stages should be applied according to the responsibility and risk of the
contribution.

---

# Relationship with the Study Methodology

The development workflow and the study methodology are related but serve
different purposes.

```text
Study Methodology
        ↓
Defines how knowledge is acquired and understood

Development Workflow
        ↓
Defines how that knowledge becomes a repository contribution
```

`03-study-methodology.md` explains how a subject should be researched, analysed,
practised, and reviewed.

This document explains how the resulting knowledge is documented, implemented,
verified, evaluated, reviewed, and integrated into the repository.

Together, they connect academic learning with disciplined software engineering.

---

# Workflow Philosophy

**Algorithms Java Mastery** is based on the principle that implementation is the
result of understanding rather than its substitute.

Programming begins only after the problem, assumptions, expected behaviour, and
algorithmic strategy have been examined.

The guiding principle is:

Introduction to Algorithms (CLRS)

↓

Computer Science Foundations

↓

Problem Understanding

↓

Specification

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

↓

Professional Engineering Knowledge

The workflow therefore connects several forms of project knowledge:

```text
Academic Knowledge
        ↓
Problem Understanding
        ↓
Formal Specification
        ↓
Algorithmic Reasoning
        ↓
Correctness Evidence
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Verification
        ↓
Experimental Evidence
        ↓
Review and Integration
```

Documentation accompanies and records the complete process.

It is not limited to a final retrospective stage.

---

# Workflow Characteristics

The development workflow is:

- **progressive** — each stage builds upon previously established knowledge;
- **traceable** — documentation, code, tests, and benchmarks remain connected;
- **evidence-based** — conclusions require reasoning, verification, or
  experimental support;
- **iterative** — later findings may require revisiting earlier decisions;
- **reproducible** — another developer should be able to build and verify the
  contribution;
- **proportional** — the process should match the complexity and responsibility
  of the change.

The workflow provides structure without preventing justified adaptation.

---

# Complete Development Lifecycle

A complete algorithm contribution normally follows this lifecycle:

```text
1. Introduction to Algorithms (CLRS)
        ↓
2. Computer Science Foundations
        ↓
3. Contribution Selection
        ↓
4. Academic Research
        ↓
5. Problem Understanding
        ↓
6. Formal Specification
        ↓
7. Strategy Design
        ↓
8. Correctness Reasoning
        ↓
9. Complexity Analysis
        ↓
10. Java Implementation
        ↓
11. Automated Testing
        ↓
12. Performance Evaluation
        ↓
13. Documentation Consolidation
        ↓
14. Local Validation
        ↓
15. Version-Control Preparation
        ↓
16. Pull Request and Review
        ↓
17. Continuous Integration
        ↓
18. Repository Integration
        ↓
19. Reflection and Improvement
```

Performance evaluation is included only when benchmarking is relevant to the
learning objective.

---

# Iterative Feedback

Although the workflow has a recommended sequence, it is not strictly linear.

Later evidence may reveal problems in earlier stages.

```text
Failed Test
        ↓
Revisit Specification or Implementation

Unexpected Benchmark Result
        ↓
Revisit Experimental Design or Complexity Assumptions

Review Observation
        ↓
Revisit Documentation, Design, or Test Coverage

Build Failure
        ↓
Revisit Configuration or Dependency Decisions
```

Returning to an earlier stage is not a workflow failure.

It is part of evidence-based engineering.

A contribution progresses when its current evidence supports the decisions made
in previous stages.

---

# Stage 1 — Contribution Selection

The workflow begins by selecting a clearly defined contribution.

The contribution may involve:

- a new algorithm;
- a data structure;
- a conceptual document;
- an implementation variant;
- additional test coverage;
- a benchmark;
- a correction;
- a refactoring;
- a repository improvement.

Before beginning, determine:

- what problem or knowledge gap is being addressed;
- why the contribution belongs in the repository;
- which module owns the responsibility;
- which existing documents or classes are related;
- what evidence will demonstrate completion.

The contribution should remain small enough to be studied, reviewed, and
integrated coherently.

## Expected Result

A defined task with a clear purpose and repository location.

---

# Stage 2 — Academic Research

The subject is studied using recognised and verifiable sources.
Research begins with the systematic study of
Introduction to Algorithms,
which provides the principal academic reference for the repository.

Additional sources complement, clarify or extend the concepts introduced
throughout the book.

Research may include:

- established Computer Science textbooks;
- academic publications;
- language specifications;
- official Java documentation;
- official Maven, JUnit, AssertJ, or JMH documentation;
- existing project documentation.

Research should identify:

- accepted terminology;
- theoretical foundations;
- known algorithmic strategies;
- assumptions and limitations;
- relevant complexity properties;
- potential implementation considerations.

Research notes may initially be maintained under:

```text
notes/
```

Reviewed and structured knowledge belongs under:

```text
docs/
```

## Expected Result

A reliable conceptual basis expressed through original understanding rather than
source transcription.

---

# Stage 3 — Problem Understanding

The computational problem must be understood independently of Java syntax.

The analysis should identify:

- the problem being solved;
- the input;
- the expected output;
- constraints;
- assumptions;
- relevant edge cases;
- invalid situations;
- possible solution families.

Questions to consider include:

```text
What information is provided?

What result is required?

Which conditions must always hold?

What input characteristics affect the solution?

What would make a solution incorrect?

Which cases are easy to overlook?
```

Implementation should not begin while the problem remains ambiguous.

## Expected Result

A precise description of the computational problem and its boundaries.

---

# Stage 4 — Formal Specification

The problem understanding is converted into an explicit behavioural contract.

The specification should define, when applicable:

- preconditions;
- postconditions;
- invariants;
- accepted input;
- rejected input;
- mutation behaviour;
- ordering requirements;
- duplicate handling;
- absence representation;
- exceptional behaviour;
- expected output.

For example, a binary-search specification should clarify:

- whether the input must already be sorted;
- whether duplicate values are accepted;
- which duplicate index may be returned;
- how an absent target is represented;
- whether `null` input is valid.

The specification becomes the shared reference for:

- algorithm design;
- Java implementation;
- automated tests;
- documentation examples;
- benchmark input preparation.

## Expected Result

A documented and testable behavioural contract.

---

# Stage 5 — Strategy Design

A solution is designed independently of implementation details.

Activities may include:

- decomposing the problem;
- comparing possible strategies;
- selecting data structures;
- defining significant operations;
- writing pseudocode;
- identifying loop or structural invariants;
- analysing termination conditions;
- identifying algorithm variants.

The selected strategy should be justified according to the problem rather than
chosen only because it is familiar.

When several approaches are educationally relevant, each variant may be
documented separately.

Examples include:

```text
Iterative Binary Search
Recursive Binary Search

Top-Down Dynamic Programming
Bottom-Up Dynamic Programming

In-Place Merge Variant
Auxiliary-Array Merge Variant
```

## Expected Result

A clear algorithmic strategy that can be reasoned about before being translated
into Java.

---

# Stage 6 — Correctness Reasoning

Before relying on testing, the solution should be examined conceptually for
correctness.

Correctness reasoning may include:

- establishing an invariant;
- demonstrating initialisation;
- showing preservation;
- proving termination;
- applying mathematical induction;
- reasoning by contradiction;
- analysing recursive base and inductive cases;
- validating data structure invariants;
- relating postconditions to the completed execution.

The level of formality should match the complexity and educational purpose of
the algorithm.

Correctness reasoning answers:

> **Why should the algorithm produce the required result for every valid
> input covered by its specification?**

Automated tests examine selected executions.

Correctness reasoning examines the general strategy.

## Expected Result

A documented argument explaining why the algorithm satisfies its specification.

---

# Stage 7 — Complexity Analysis

The algorithm is analysed theoretically before performance measurements are
interpreted.

The analysis should consider, when applicable:

- input-size definition;
- significant operations;
- best-case behaviour;
- average-case behaviour;
- worst-case behaviour;
- time complexity;
- auxiliary-space complexity;
- recursion depth;
- in-place or out-of-place behaviour;
- relevant time-space trade-offs.

Complexity should be derived from the algorithm rather than inferred from a
single execution.

For example:

```text
Input Size
        ↓
Significant Operation
        ↓
Operation Count
        ↓
Growth Function
        ↓
Asymptotic Classification
```

Complexity conclusions should identify the assumptions under which they apply.

## Expected Result

A justified theoretical analysis that can later be compared with experimental
observations.

---

# Stage 8 — Java Implementation

The validated strategy is translated into Java.

Production code belongs under:

```text
src/main/java/org/anaalvarez/algorithms/
```

The implementation should:

- follow the documented specification;
- preserve the algorithmic strategy;
- maintain readable control flow;
- use meaningful names;
- make mutation explicit;
- handle invalid input according to the contract;
- avoid unrelated infrastructure;
- follow the Java project structure;
- remain independently testable;
- avoid premature optimisation.

Java-specific decisions should not change the algorithm silently.

When implementation constraints require a variation from the documented
strategy, that difference should be explained.

## Expected Result

Compilable production code that implements the documented behaviour.

---

# Stage 9 — Automated Testing

Executable behaviour is verified through automated tests.

Tests belong under:

```text
src/test/java/org/anaalvarez/algorithms/
```

JUnit Jupiter provides the test framework, while AssertJ provides expressive
assertions.

Tests should be derived from:

- the formal specification;
- identified equivalence classes;
- boundary conditions;
- edge cases;
- invalid-input policies;
- structural invariants;
- known algorithmic failure risks.

A suitable test set may include:

- typical input;
- empty input;
- single-element input;
- ordered input;
- reverse-ordered input;
- repeated values;
- absent values;
- minimum and maximum meaningful cases;
- invalid input;
- mutation verification.

Not every category applies to every algorithm.

Tests should remain:

- deterministic;
- independent;
- readable;
- reproducible;
- focused on observable behaviour.

## Expected Result

Executable evidence that the implementation satisfies representative parts of
its documented contract.

---

# Stage 10 — Performance Evaluation

Performance evaluation is introduced only when it supports a defined learning
objective.

JMH benchmarks belong under:

```text
src/jmh/java/org/anaalvarez/algorithms/
```

A benchmark may be appropriate when studying:

- growth across input sizes;
- alternative implementations;
- input distributions;
- recursive and iterative strategies;
- data structure trade-offs;
- theoretical and observed behaviour;
- effects of implementation decisions.

A benchmark should define:

- the implementation being measured;
- the input size;
- the input distribution;
- setup responsibilities;
- warm-up configuration;
- measurement configuration;
- relevant execution environment;
- limitations of the results.

Benchmark preparation must not be confused with the measured operation.

## Expected Result

Controlled experimental evidence whose interpretation remains limited to the
documented conditions.

---

# When Benchmarking Is Not Required

Not every implementation requires a benchmark.

Benchmarking may be omitted when:

- the topic is primarily conceptual;
- performance comparison is not part of the learning objective;
- the implementation is too incomplete for meaningful evaluation;
- correctness has not yet been established;
- the expected measurement would provide little additional knowledge;
- environmental variability would make conclusions unreliable.

The absence of a benchmark does not make an implementation incomplete when
experimental evaluation is not relevant.

The reason for introducing a benchmark should be educational or analytical, not
merely procedural.

---

# Stage 11 — Documentation Consolidation

Documentation is reviewed and consolidated after implementation and
verification, but it has already been developed throughout earlier stages.

The final documentation should connect:

- academic sources;
- problem definition;
- formal specification;
- selected strategy;
- correctness reasoning;
- complexity analysis;
- Java implementation;
- testing decisions;
- benchmark methodology and observations, when applicable;
- limitations;
- engineering conclusions.

The documentation should identify corresponding source files when useful.

For example:

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

Engineering Evidence
```

Documentation must remain consistent with the final implemented behaviour.

## Expected Result

Reviewed project knowledge that connects theory, implementation, and evidence.

---

# Stage 12 — Local Validation

Before preparing repository integration, the contribution is validated locally.

At minimum, local validation should confirm:

- the project compiles;
- relevant tests pass;
- new files are located correctly;
- package declarations match directory structure;
- documentation links are valid;
- no unintended generated files are included;
- the Maven build remains successful.

The primary validation command is:

```bash
mvn clean verify
```

Additional commands may be used according to the contribution.

For example:

```bash
mvn test
```

JMH benchmarks should be executed through the Maven configuration defined by the
project rather than through informal timing code.

## Expected Result

A clean local build and verified repository state.

---

# Stage 13 — Version-Control Preparation

The contribution is prepared for integration using Git.

A focused branch should be created for the task.

Examples:

```text
docs/09-development-workflow
feat/binary-search
test/merge-sort-edge-cases
benchmark/sorting-comparison
fix/quicksort-partition
refactor/sorting-contract
```

Before committing:

- inspect changed files;
- remove accidental changes;
- separate unrelated work;
- confirm generated files are excluded;
- review the diff;
- validate commit scope.

Commits should be small, logical, and meaningful.

Examples:

```text
docs: define development workflow
feat: implement iterative binary search
test: add binary search boundary cases
benchmark: compare sorting input distributions
fix: correct quicksort partition boundary
```

## Expected Result

A traceable version-control history representing coherent project changes.

---

# Stage 14 — Pull Request Preparation

A pull request should represent one coherent contribution.

Its description should communicate:

- what was changed;
- why the change was necessary;
- which modules or files are affected;
- how the contribution was verified;
- which tests were added or executed;
- whether benchmarking was performed;
- known limitations or future work.

A pull request should not require the reviewer to reconstruct the purpose from
the file changes alone.

## Expected Result

A reviewable contribution with clear technical context and verification
evidence.

---

# Stage 15 — Review

The contribution is reviewed before integration.

The review should examine several dimensions.

## Academic Review

- Is the problem explained correctly?
- Are terms used consistently?
- Are assumptions explicit?
- Is the strategy justified?
- Is correctness reasoning appropriate?
- Is the complexity analysis supported?
- Are sources used responsibly?

## Implementation Review

- Does the code match the specification?
- Is the algorithm visible and understandable?
- Are responsibilities well separated?
- Are names clear?
- Is unnecessary abstraction avoided?
- Are contracts and mutations explicit?

## Verification Review

- Do tests cover important behaviours?
- Are tests deterministic and independent?
- Are edge cases represented appropriately?
- Are assertions expressive?
- Are benchmarks separated from correctness tests?

## Repository Review

- Are files located correctly?
- Are documentation links valid?
- Does the contribution follow naming conventions?
- Does the Maven build succeed?
- Is the change consistent with repository architecture?

Review feedback should identify the engineering concern and, where possible, the
evidence supporting it.

## Expected Result

A contribution that has been examined for academic, implementation, and
repository consistency.

---

# Stage 16 — Continuous Integration

GitHub Actions validates the contribution in a clean environment.

Continuous integration should verify at least:

```text
Repository Checkout
        ↓
Java Environment Configuration
        ↓
Maven Dependency Resolution
        ↓
Compilation
        ↓
Automated Testing
        ↓
Build Completion
```

A successful local build is required before pushing, but CI provides additional
evidence that the contribution does not depend on the developer's local
environment.

Standard CI should not execute long or unstable benchmarks unless benchmark
automation is explicitly designed for that purpose.

## Expected Result

A successful automated build and test execution in the configured CI
environment.

---

# Stage 17 — Repository Integration

A contribution may be integrated into `main` after:

- review requirements are satisfied;
- requested corrections are addressed;
- automated tests pass;
- continuous integration succeeds;
- repository consistency is preserved.

Integration should include all artefacts required to understand and verify the
change:

- documentation;
- production code;
- automated tests;
- build configuration;
- benchmark code, when applicable;
- meaningful commit history.

Temporary files, local configuration, and experimental output should not be
integrated.

## Expected Result

A complete and validated contribution available from the primary repository
branch.

---

# Stage 18 — Reflection and Improvement

Integration does not end the learning process.

After completing a contribution, consider:

- What was learned?
- Which assumptions changed?
- Which errors were discovered?
- Which tests provided the most value?
- Did the implementation match the original design?
- Did experimental results support theoretical expectations?
- Which limitations remain?
- What should be studied next?

Relevant conclusions may be recorded in:

- the module documentation;
- implementation notes;
- benchmark analysis;
- the project changelog;
- a future issue or contribution plan.

## Expected Result

Explicit learning conclusions and identified opportunities for future
improvement.

---

# Stage Responsibility Matrix

| Stage | Primary Responsibility | Principal Evidence |
|------:|------------------------|--------------------|
| Contribution Selection | Define the scope of work | Clear task |
| Academic Research | Establish theoretical foundations | Sources and study notes |
| Problem Understanding | Define the computational problem | Problem statement |
| Formal Specification | Establish expected behaviour | Preconditions and postconditions |
| Strategy Design | Develop the solution | Reasoning and pseudocode |
| Correctness Reasoning | Justify the solution | Invariant or correctness argument |
| Complexity Analysis | Predict computational growth | Time and space analysis |
| Java Implementation | Produce executable behaviour | Production source code |
| Automated Testing | Verify behaviour | JUnit Jupiter tests |
| Performance Evaluation | Observe practical behaviour | JMH benchmarks |
| Documentation Consolidation | Preserve connected knowledge | Reviewed module documentation |
| Local Validation | Confirm local integrity | Successful Maven build |
| Version Control | Preserve traceable evolution | Branches and commits |
| Pull Request | Present the contribution | PR description |
| Review | Evaluate quality and consistency | Review feedback |
| Continuous Integration | Validate clean execution | Successful workflow |
| Integration | Incorporate validated work | Change merged into `main` |
| Reflection | Capture learning and limitations | Conclusions and future work |

---

# Workflow Artefacts

A complete algorithm contribution may produce the following artefacts:

```text
Introduction to Algorithms

↓

Documentation

↓

Java Implementation

↓

Automated Tests

↓

Benchmarks

↓

Engineering Knowledge
```

Not every contribution produces every artefact.

Each artefact should exist only when it has a defined responsibility.

---

# Traceability Across the Workflow

The workflow should preserve relationships between its outputs.

For example:

```text
Problem Specification
        ↓
Defines the implementation contract
        ↓
Guides automated tests
        ↓
Supports review
```

```text
Complexity Analysis
        ↓
Defines theoretical expectations
        ↓
Guides benchmark design
        ↓
Supports experimental interpretation
```

```text
Documentation
        ↓
References implementation
        ↓
Implementation is verified by tests
        ↓
Performance observations are produced by benchmarks
```

Traceability allows a reader to understand how project conclusions were reached
and how they can be reproduced.

---

# Handling Failed Verification

A failed test or build should not be bypassed merely to complete integration.

When verification fails:

1. identify the violated expectation;
2. determine whether the problem belongs to the specification, implementation,
   test, or configuration;
3. correct the authoritative source of the inconsistency;
4. rerun the relevant validation;
5. update documentation when behaviour or assumptions change.

A test should not be modified solely to accept incorrect behaviour.

The specification and implementation must remain consistent.

---

# Handling Unexpected Benchmark Results

Unexpected benchmark results require investigation rather than immediate
conclusions.

Review:

- benchmark setup;
- state scope;
- input generation;
- warm-up;
- measurement duration;
- JVM optimisation;
- result consumption;
- hardware and operating-system conditions;
- input distribution;
- implementation correctness;
- theoretical assumptions.

An unexpected result may reveal:

- a benchmark-design error;
- implementation overhead;
- JVM behaviour;
- an incorrect complexity assumption;
- an input-specific effect;
- environmental noise.

Performance conclusions should be revised only after the evidence has been
examined.

---

# Change Types and Workflow Depth

Different changes require different workflow depth.

| Change Type | Minimum Expected Activities |
|-------------|-----------------------------|
| Documentation correction | Review, link validation, local inspection, version control |
| New conceptual document | Research, structured writing, source review, documentation review |
| New algorithm | Specification, design, correctness reasoning, complexity analysis, implementation, testing |
| New data structure | Contract definition, invariant reasoning, implementation, behavioural testing |
| Algorithm variant | Design comparison, implementation, shared verification, optional benchmarking |
| Bug fix | Reproduction, failing test, correction, regression validation |
| Refactoring | Existing tests, structural change, behavioural preservation |
| Benchmark | Verified implementation, experimental design, JMH execution, limited conclusions |
| Build or CI change | Configuration review, clean build, automated validation |

The workflow should remain rigorous without adding activities that provide no
value to the specific contribution.

---

# Definition of Completion

A contribution is complete when the applicable requirements have been
satisfied.

## Academic Completion

- the problem is clearly defined;
- relevant terminology is consistent;
- assumptions and constraints are explicit;
- the strategy is explained;
- correctness has been considered;
- complexity has been analysed when applicable;
- sources are identified responsibly;
- explanations use original wording.

## Implementation Completion

- code follows the documented contract;
- the project compiles;
- package and naming conventions are followed;
- mutation and error behaviour are explicit;
- unnecessary abstraction is avoided;
- the implementation remains readable and testable.

## Verification Completion

- representative behaviour is tested;
- important edge cases are covered;
- tests are deterministic and independent;
- assertions communicate intent;
- regression cases are added for corrected defects;
- all relevant tests pass.

## Benchmark Completion

When benchmarking is included:

- correctness has already been verified;
- benchmark conditions are documented;
- setup is separated from measurement;
- input sizes and distributions are defined;
- conclusions remain limited to observed evidence.

## Repository Completion

- related documentation is updated;
- local validation succeeds;
- commits are coherent;
- the pull request explains the change;
- review observations are addressed;
- continuous integration succeeds;
- no unrelated or generated files are included.

Completion represents compliance with current project standards.

It does not mean that the topic can never be extended or improved.

---

# Continuous Improvement

Repository development continues after integration.

Existing contributions may be revised through:

- clearer explanations;
- stronger correctness arguments;
- additional edge-case tests;
- improved naming;
- reduced duplication;
- better benchmark methodology;
- updated official references;
- structural refactoring;
- corrected assumptions;
- improved CI validation.

Improvements should preserve verified behaviour unless a documented contract
change is intentional.

Large redesigns should be avoided when a focused incremental change can solve
the identified problem.

---

# Workflow Quality Principles

The development workflow follows these principles:

- understanding precedes implementation;
- specifications guide code and tests;
- correctness reasoning complements automated verification;
- complexity analysis precedes performance conclusions;
- testing and benchmarking remain separate;
- documentation accompanies the complete process;
- repository integration requires reviewable evidence;
- CI complements but does not replace local validation;
- failed evidence leads to revision rather than concealment;
- workflow depth remains proportional to the contribution;
- learning continues after integration.

These principles preserve the academic and engineering direction of the
repository.

---

# Relationship with Project Documentation

This document forms part of the foundational documentation of the repository.

| Order | Document | Purpose |
|------:|----------|---------|
| **01** | `01-project-overview.md` | Introduces the project's vision, academic foundation, scope, and objectives. |
| **02** | `02-learning-objectives.md` | Defines the knowledge, competencies, and expected learning outcomes. |
| **03** | `03-study-methodology.md` | Describes how subjects are studied, analysed, practised, and reviewed. |
| **04** | `04-repository-architecture.md` | Explains the architectural organisation of the repository. |
| **05** | `05-technology-stack.md` | Defines the technologies supporting the workflow. |
| **06** | `06-documentation-architecture.md` | Establishes the organisation and standards of project knowledge. |
| **07** | `07-java-project-structure.md` | Defines the organisation of production code, tests, and benchmarks. |
| **08** | `08-engineering-standards.md` | Defines the engineering and quality expectations applied during the workflow. |
| **09** | `09-development-workflow.md` | Defines the complete contribution lifecycle. |
| **10** | `10-references.md` | Lists the principal academic, technical, and official sources. |

Together, these documents define the academic, technical, organisational, and
engineering foundations of **Algorithms Java Mastery**.

---

# Conclusion

The development workflow of **Algorithms Java Mastery** transforms academic
study into documented and verifiable engineering contributions.

Each algorithm or data structure progresses from research and problem
understanding to formal specification, strategy design, correctness reasoning,
complexity analysis, Java implementation, automated testing, and, when
appropriate, controlled performance evaluation.

Local validation, version control, pull requests, review, and continuous
integration ensure that contributions are integrated without compromising
repository quality.

The workflow is progressive but iterative.

New evidence may require earlier decisions to be reviewed, corrected, and
improved.

This approach ensures that the repository records not only executable solutions
but also the reasoning, evidence, and engineering discipline required to
understand and maintain them.

The workflow provides the engineering process through which the concepts
studied in Introduction to Algorithms become documented, executable,
experimentally evaluated and professionally maintainable Java software.

---

# Related Documentation

| Order | Document | Purpose |
|------:|----------|---------|
| **01** | `01-project-overview.md` | Defines the project's identity, scope, and academic direction. |
| **02** | `02-learning-objectives.md` | Establishes the competencies developed through the workflow. |
| **03** | `03-study-methodology.md` | Defines the academic process that precedes repository contribution. |
| **04** | `04-repository-architecture.md` | Defines where workflow artefacts belong. |
| **05** | `05-technology-stack.md` | Presents the technologies used during implementation and validation. |
| **06** | `06-documentation-architecture.md` | Defines how workflow knowledge is recorded and connected. |
| **07** | `07-java-project-structure.md` | Defines where production code, tests, and benchmarks are located. |
| **08** | `08-engineering-standards.md` | Establishes the quality requirements applied at every stage. |
| **10** | `10-references.md` | Centralises the principal sources supporting project decisions. |

---

# Next Document

```text
10-references.md
```

The next document identifies the principal academic literature, Java
specifications, official tool documentation, and technical sources that support
the foundational decisions of **Algorithms Java Mastery**.

After completing the foundational project documentation, the learning process
continues with:

```text
docs/01-foundations/
```

This module begins the formal study of algorithmic thinking, computational
problems, specifications, correctness, invariants, edge cases, and systematic
problem decomposition.