# Study Methodology

## Purpose

This document defines the academic and engineering methodology adopted
throughout **Algorithms Java Mastery**.

Rather than describing individual algorithms, it establishes the repeatable
study process applied to every topic in the repository.

The methodology transforms algorithm study into a structured engineering
workflow, beginning with academic research and concluding with documented
engineering conclusions supported by implementation, automated testing, and
experimental evaluation.

Every module follows the same methodological framework, ensuring consistency
throughout the repository.

---

# Methodological Philosophy

The repository is guided by one fundamental principle:

> **Understanding precedes implementation.**

Algorithms are not studied by memorising source code.

Instead, every implementation is the result of a progressive learning process
that begins with recognised Computer Science literature, continues through
analysis and reasoning, and only then reaches executable Java code.

The objective is to develop transferable algorithmic thinking rather than
language-specific programming skills.

---

# Academic Learning Workflow

Every topic follows the same academic progression.

```text
Academic Reference
        ↓
Research
        ↓
Conceptual Understanding
        ↓
Problem Analysis
        ↓
Formal Specification
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Technical Documentation
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Benchmarking
        ↓
Engineering Conclusions
```

Each stage builds upon the previous one.

Implementation is therefore considered the consequence of understanding rather
than the beginning of learning.

---

# Methodological Principles

The methodology is based on six complementary principles.

---

## 1. Study Before Implementing

Every topic begins with academic study.

The repository is primarily based on recognised Computer Science literature,
supplemented with official technical documentation when appropriate.

The objective is to understand the underlying concepts before designing a
solution.

---

## 2. Understand Before Coding

Programming should never replace reasoning.

Before writing Java code, the learner should understand:

- the computational problem;
- the required inputs and outputs;
- assumptions and constraints;
- possible solution strategies.

Implementation decisions should naturally emerge from conceptual analysis.

---

## 3. Separate Theory from Implementation

Algorithms exist independently of programming languages.

The repository therefore distinguishes clearly between:

```text
Algorithm
        ↓
Abstract computational procedure

Java
        ↓
Concrete implementation
```

This separation encourages transferable knowledge beyond Java itself.

---

## 4. Justify Every Engineering Decision

Every important decision should be supported by explicit reasoning.

Learners should progressively explain:

- why a strategy was selected;
- why an algorithm is correct;
- why a complexity result is valid;
- why an implementation choice was appropriate.

Engineering conclusions should always be evidence-based.

---

## 5. Validate Through Multiple Perspectives

Confidence in an implementation is obtained through complementary activities.

These include:

- correctness reasoning;
- automated testing;
- benchmark analysis when appropriate.

Each activity contributes different evidence.

None replaces the others.

---

## 6. Document the Learning Process

Documentation is considered an integral component of learning.

Each module preserves:

- conceptual understanding;
- reasoning;
- implementation decisions;
- analytical conclusions;
- lessons learned.

Documentation explains not only what was implemented, but also why.

---

# Study Stages

The methodology is organised into twelve complementary stages.

---

## Stage 1 — Academic Study

Study recognised Computer Science references before attempting any
implementation.

Typical activities include:

- reading academic material;
- identifying key concepts;
- recognising algorithmic patterns;
- taking personal notes.

Purpose:

> Understand the theoretical foundations.

---

## Stage 2 — Research

Complement the academic reference using official documentation and reliable
technical resources when necessary.

Examples include:

- Oracle Java Documentation;
- Java Language Specification;
- JUnit User Guide;
- AssertJ Documentation.

Purpose:

> Connect theory with professional engineering practice.

---

## Stage 3 — Problem Analysis

Identify precisely which computational problem must be solved.

Questions include:

- What is the objective?
- What information is available?
- What assumptions exist?
- Which constraints must be respected?

Purpose:

> Understand the problem before designing the solution.

---

## Stage 4 — Formal Specification

Define the computational contract.

Specify:

- inputs;
- outputs;
- preconditions;
- postconditions;
- expected behaviour.

Purpose:

> Define what a correct solution must satisfy.

---

## Stage 5 — Algorithm Design

Design a solution independently of Java syntax.

The focus is on computational reasoning rather than programming language
features.

Purpose:

> Develop the algorithm itself.

---

## Stage 6 — Correctness Reasoning

Explain why the algorithm satisfies its specification.

Depending on the topic, reasoning may involve:

- loop invariants;
- induction;
- state reasoning;
- recursive arguments.

Purpose:

> Demonstrate why the algorithm works.

---

## Stage 7 — Complexity Analysis

Study how computational resources grow as input size increases.

This stage analyses:

- significant operations;
- time complexity;
- auxiliary space;
- asymptotic behaviour.

Purpose:

> Predict theoretical performance.

---

## Stage 8 — Technical Documentation

Record the reasoning developed during the previous stages.

Documentation should preserve:

- conceptual explanations;
- strategy selection;
- correctness arguments;
- complexity analysis.

Purpose:

> Preserve engineering knowledge.

---

## Stage 9 — Java Implementation

Translate the validated algorithm into maintainable Java code.

The implementation should faithfully preserve the algorithmic strategy.

Purpose:

> Convert theory into executable software.

---

## Stage 10 — Automated Testing

Validate behaviour using automated tests.

Tests are derived from the specification rather than from implementation
details.

Purpose:

> Verify expected behaviour.

---

## Stage 11 — Benchmarking

When appropriate, analyse implementation behaviour experimentally.

Benchmark results should always be interpreted together with theoretical
complexity.

Purpose:

> Compare theory with observed behaviour.

---

## Stage 12 — Engineering Conclusions

Summarise the knowledge obtained throughout the study.

Typical conclusions include:

- algorithm suitability;
- limitations;
- trade-offs;
- comparison with Java Collections Framework;
- lessons learned.

Purpose:

> Transform implementation into engineering understanding.

---

# Repository Integration

Each methodological stage is supported by a specific repository module.

| Methodological Stage | Repository Module |
|----------------------|-------------------|
| Academic Study | `research/` |
| Problem Analysis | `docs/01-foundations/` |
| Specification | `docs/01-foundations/` |
| Correctness | `docs/01-foundations/` |
| Complexity | `docs/02-complexity/` |
| Implementations | `src/main/java/` |
| Testing | `docs/16-testing/` |
| Benchmarking | `docs/17-benchmarking/` |

This organisation separates research, documentation, implementation, and
validation while maintaining a coherent learning workflow.

---

# Applying the Methodology

When approaching a new algorithm, the learner should avoid asking:

> *"How do I implement this?"*

Instead, the recommended sequence is:

```text
Which academic concept is being studied?

↓

What computational problem exists?

↓

Which assumptions apply?

↓

Which strategy is appropriate?

↓

Why should it work?

↓

How does complexity grow?

↓

How should it be documented?

↓

How should it be implemented?

↓

How should it be tested?

↓

What engineering conclusions can be drawn?
```

Following this progression encourages disciplined engineering reasoning instead
of implementation by trial and error.

---

# Methodology Checklist

Before considering a topic complete, the learner should confidently answer:

- Which academic concept was studied?
- What computational problem is being solved?
- What assumptions define valid execution?
- Which strategy was selected?
- Why is the algorithm correct?
- How does computational complexity grow?
- Does the Java implementation preserve the strategy?
- Have representative behaviours been tested?
- Is benchmarking necessary?
- What engineering conclusions were obtained?

If any of these questions cannot be answered, further study is recommended.

---

# Conclusion

The methodology adopted by **Algorithms Java Mastery** transforms algorithm
study into a structured academic and engineering process.

Beginning with recognised Computer Science literature and progressing through
research, reasoning, implementation, validation, and documentation, the
repository promotes a disciplined approach to algorithmic learning.

The objective is not merely to produce working Java programs, but to develop
software engineers capable of understanding, explaining, implementing,
validating, and defending algorithmic solutions using evidence-based reasoning.

---

# Related Documents

| Order | Document | Purpose |
|------:|----------|---------|
| **01** | `01-project-overview.md` | Defines the project's academic vision and scope. |
| **02** | `02-learning-objectives.md` | Defines the educational competencies developed throughout the repository. |
| **04** | `04-repository-architecture.md` | Explains the organisation of the repository. |
| **05** | `05-technology-stack.md` | Presents the technologies supporting the project. |
| **06** | `references.md` | Lists the academic and technical references supporting the repository. |

---

# Next Document

```text
04-repository-architecture.md
```

The next document describes the repository architecture, explaining how
research notes, documentation, source code, tests, benchmarks, and supporting
resources are organised to implement the methodology defined in this document.