# Repository Architecture

## Algorithms Java Mastery

This document defines and justifies the repository architecture of **Algorithms Java Mastery**.

The repository is designed as a structured academic and engineering environment for the systematic study of algorithms, data structures, computational complexity, automated testing, performance evaluation, Linux-based development, and continuous integration practices using Java.

The architecture follows a central principle:

> **Repository structure should reflect the learning process and preserve clear boundaries between theoretical study, algorithm implementation, verification, experimentation, environment validation, and engineering automation.**

This repository is not organised as a conventional business application.

It does not adopt architectural layers such as `controller`, `service`, and `repository` as its primary organisational model because its principal domain is algorithmic study.

Instead, the repository is organised according to:

- academic progression;
- algorithmic domains;
- implementation responsibility;
- automated verification;
- experimental evaluation;
- development environment validation;
- continuous integration practices.

---

# 1. Architectural Objectives

The repository architecture supports seven principal objectives.

```text
Academic Organisation
        ↓
Structure theoretical study progressively.

Algorithmic Organisation
        ↓
Group implementations by computational domain.

Implementation
        ↓
Translate analysed algorithms into Java.

Verification
        ↓
Validate behaviour through automated testing.

Experimentation
        ↓
Evaluate implementation performance.

Environment Validation
        ↓
Execute and validate the project using Linux.

Engineering Automation
        ↓
Automate build and test validation through CI/CD.
```

The repository should remain understandable to a technical reader who has not participated in its development.

A reader should be able to identify:

- where project decisions are documented;
- where theoretical material is located;
- where algorithms are implemented;
- where automated tests are defined;
- where exercises are preserved;
- where performance experiments are performed;
- how Linux is integrated into the learning process;
- how continuous integration validates the repository.

---

# 2. General Repository Structure

The repository is organised as follows:

```text
Algorithms-Java-Mastery/
│
├── .github/
│   └── workflows/
│       └── maven-ci.yml
│
├── docs/
│   ├── 00-project/
│   ├── 01-foundations/
│   ├── 02-complexity/
│   ├── 03-arrays/
│   ├── 04-searching/
│   ├── 05-sorting/
│   ├── 06-algorithmic-patterns/
│   ├── 07-recursion/
│   ├── 08-divide-and-conquer/
│   ├── 09-hashing/
│   ├── 10-stacks-and-queues/
│   ├── 11-trees/
│   ├── 12-heaps/
│   ├── 13-graphs/
│   ├── 14-backtracking/
│   ├── 15-dynamic-programming/
│   ├── 16-testing/
│   ├── 17-benchmarking/
│   ├── 18-linux-environment/
│   ├── 19-ci-cd/
│   └── 20-conclusions/
│
├── diagrams/
│
├── exercises/
│   ├── basic/
│   ├── intermediate/
│   ├── advanced/
│   └── interview/
│
├── notes/
│
├── references/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── io/
│   │           └── github/
│   │               └── algorithms/
│   │                   ├── application/
│   │                   ├── arrays/
│   │                   ├── searching/
│   │                   ├── sorting/
│   │                   ├── patterns/
│   │                   ├── recursion/
│   │                   ├── hashing/
│   │                   ├── stack/
│   │                   ├── queue/
│   │                   ├── tree/
│   │                   ├── heap/
│   │                   ├── graph/
│   │                   ├── backtracking/
│   │                   ├── dynamicprogramming/
│   │                   └── shared/
│   │
│   └── test/
│       └── java/
│           └── io/
│               └── github/
│                   └── algorithms/
│
├── benchmark/
│
├── README.md
├── CONTRIBUTING.md
├── CHANGELOG.md
├── LICENSE
└── pom.xml
```

The architecture separates learning artefacts according to their responsibility.

```text
Documentation
      ↓
docs/

Visual Models
      ↓
diagrams/

Practice
      ↓
exercises/

Working Notes
      ↓
notes/

Sources
      ↓
references/

Implementation
      ↓
src/main/

Verification
      ↓
src/test/

Performance Evaluation
      ↓
benchmark/

Automation
      ↓
.github/
```

---

# 3. Architectural Principle: Separation of Responsibilities

The repository applies separation of responsibilities at the directory level.

An algorithm may generate several related artefacts.

For example:

```text
Binary Search
      │
      ├── Academic explanation
      │       ↓
      │   docs/04-searching/
      │
      ├── Java implementation
      │       ↓
      │   src/main/java/
      │
      ├── Automated tests
      │       ↓
      │   src/test/java/
      │
      └── Performance experiment
              ↓
          benchmark/
```

These artefacts study the same algorithm but answer different questions.

| Artefact | Question |
|---|---|
| Documentation | Why and how does the algorithm work? |
| Implementation | How is the algorithm expressed in Java? |
| Test | Does the implementation satisfy expected behaviour? |
| Benchmark | What performance behaviour is experimentally observed? |

The repository should avoid unnecessary duplication between these responsibilities.

---

# 4. The `docs/` Directory

The `docs/` directory contains the principal academic and technical documentation of the project.

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

The numerical prefixes communicate the intended academic and engineering progression.

They do not define Java modules.

They define documentation domains.

The progression is:

```text
00
 ↓
Project definition and methodology

01–02
 ↓
Algorithmic foundations and complexity

03–13
 ↓
Core data structures and algorithms

14–15
 ↓
Advanced problem-solving techniques

16
 ↓
Automated verification

17
 ↓
Experimental performance analysis

18
 ↓
Linux development environment

19
 ↓
Continuous integration and delivery

20
 ↓
Technical synthesis and conclusions
```

The sequence represents the transformation of algorithmic knowledge into reproducible engineering practice.

---

# 5. `docs/00-project/`

The `00-project/` directory defines the repository itself.

Its responsibility is project-level documentation.

The current structure is:

```text
00-project/
├── README.md
├── learning-objectives.md
├── project-overview.md
├── repository-architecture.md
├── study-methodology.md
└── technology-stack.md
```

These documents describe decisions that apply to the complete project.

---

## 5.1 `README.md`

The local `README.md` acts as the entry point to the `00-project` documentation domain.

It should provide navigation and briefly explain the purpose of each project-level document.

It does not replace the root repository `README.md`.

```text
Repository README
        ↓
Introduces the complete repository.

00-project/README.md
        ↓
Introduces project-level documentation.
```

---

## 5.2 `project-overview.md`

Defines the nature and direction of **Algorithms Java Mastery**.

It answers:

> **What is this project and why does it exist?**

Its responsibility includes:

- project purpose;
- academic context;
- engineering direction;
- professional relevance;
- project scope.

---

## 5.3 `learning-objectives.md`

Defines the competencies expected to be developed.

It answers:

> **What should the learner be capable of doing after completing the project?**

The objectives include algorithmic reasoning, complexity analysis, Java implementation, testing, benchmarking, Linux usage, and continuous integration practices.

---

## 5.4 `study-methodology.md`

Defines the systematic study procedure applied throughout the repository.

It establishes the progression:

```text
Problem Understanding
        ↓
Formal Specification
        ↓
Edge-Case Analysis
        ↓
Strategy Design
        ↓
Pseudocode
        ↓
Manual Tracing
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
        ↓
Technical Conclusions
```

It answers:

> **How should an algorithm be studied?**

---

## 5.5 `repository-architecture.md`

Defines and justifies the organisation of the repository.

It answers:

> **Why is the repository structured in this way?**

This document establishes directory responsibilities, source organisation, documentation progression, and the relationship between algorithmic study and engineering practices.

---

## 5.6 `technology-stack.md`

Defines and justifies the technologies used throughout the project.

Its scope includes technologies such as:

- Java;
- JDK;
- Maven;
- JUnit Jupiter;
- AssertJ;
- JMH;
- Git;
- GitHub;
- Linux;
- GitHub Actions.

The document should explain why each technology is relevant to the project.

It should not become a list of tool names without technical justification.

---

# 6. docs/01-foundations/

The `01-foundations/` directory establishes the theoretical and methodological foundations required before studying specific algorithms and data structures.

Its responsibility is to develop disciplined algorithmic reasoning independently of any programming language.

The current structure is:

```text
01-foundations/
├── README.md
├── algorithm-definition.md
├── problem-specification.md
├── preconditions-and-postconditions.md
├── edge-cases.md
├── correctness.md
├── invariants.md
├── problem-decomposition.md
└── problem-solving-method.md
```

These documents introduce the fundamental concepts that will be reused throughout the repository.

They define how computational problems should be analysed before implementation.

The primary objective of this module is to encourage the learner to think algorithmically rather than syntactically.

The expected progression is:

Computational Problem
↓
Formal Specification
↓
Algorithmic Reasoning
↓
Strategy Design
↓
Pseudocode
↓
Java Implementation

This module intentionally postpones programming.

The learner should progressively stop beginning with Java syntax and instead begin with the computational problem.

---

## 6.1 README.md

The local `README.md` acts as the entry point to the Foundations documentation.

It introduces the purpose of the module, explains the relationship between the documents, and provides navigation through the theoretical foundations.

Its responsibilities include:

- introducing the Foundations module;
- presenting the learning sequence;
- describing the purpose of each document;
- explaining how this module connects to later algorithm implementations.

It does not replace the repository README or the `00-project` documentation.

Repository README
↓
Introduces the complete repository.

00-project/README.md
↓
Introduces the project documentation.

01-foundations/README.md
↓
Introduces the theoretical foundations of algorithmic reasoning.

---

## 6.2 algorithm-definition.md

Defines the concept of an algorithm from a computational perspective.

It answers:

> What is an algorithm?

Its responsibilities include:

- formal definition;
- algorithm characteristics;
- finite execution;
- determinism;
- correctness objectives;
- relationship between problems and algorithms;
- distinction between algorithms and programs.

The learner should understand that an algorithm exists independently of its implementation language.

---

## 6.3 problem-specification.md

Defines how computational problems should be specified before implementation.

It answers:

> What problem are we trying to solve?

Its responsibilities include:

- problem definition;
- input;
- output;
- domain assumptions;
- constraints;
- formal specification;
- examples of valid and invalid inputs.

The document establishes the habit of describing the problem before designing the solution.

---

## 6.4 preconditions-and-postconditions.md

Introduces the concept of software contracts.

It answers:

> Under which conditions may an algorithm execute, and what must be true after it finishes?

Its responsibilities include:

- preconditions;
- postconditions;
- valid execution assumptions;
- result guarantees;
- behavioural contracts.

The learner should understand that every algorithm operates under explicit assumptions that should be documented.

---

## 6.5 edge-cases.md

Defines the importance of analysing exceptional and boundary situations.

It answers:

> Which inputs are most likely to expose incorrect algorithm behaviour?

Its responsibilities include:

- boundary values;
- empty inputs;
- single-element cases;
- duplicated values;
- invalid inputs;
- exceptional situations.

The document establishes the connection between edge-case analysis and automated testing.

Edge Case
↓
Expected Behaviour
↓
Test Case

---

## 6.6 algorithm-correctness.md

Introduces reasoning about algorithm correctness.

It answers:

> Why does the algorithm always produce the correct result?

Its responsibilities include:

- correctness reasoning;
- partial correctness;
- total correctness;
- termination;
- logical argumentation.

The learner should understand that successful execution alone does not constitute proof of correctness.

Correctness requires formal reasoning in addition to testing.

---

## 6.7 invariants.md

Introduces the concept of algorithmic invariants.

It answers:

> Which property remains true during every iteration or recursive step?

Its responsibilities include:

- loop invariants;
- recursive invariants;
- state preservation;
- correctness support;
- invariant-based reasoning.

Invariants will become increasingly important when analysing searching algorithms, sorting algorithms, graph traversal, and dynamic programming.

---

## 6.8 problem-decomposition.md

Defines systematic problem decomposition.

It answers:

> How can a complex computational problem be divided into manageable subproblems?

Its responsibilities include:

- decomposition strategies;
- abstraction;
- subproblem identification;
- sequential reasoning;
- divide-before-implement philosophy.

The learner should understand that software complexity is reduced by solving smaller problems with clearly defined responsibilities.

---

## 6.9 problem-solving-method.md

Defines the standard methodology used throughout the repository for studying algorithms.

It answers:

> How should an algorithm be analysed before implementation?

It establishes the progression:

Problem Understanding
↓
Input and Output Identification
↓
Formal Specification
↓
Preconditions
↓
Postconditions
↓
Edge-Case Analysis
↓
Strategy Design
↓
Pseudocode
↓
Manual Execution
↓
Correctness Reasoning
↓
Complexity Preview
↓
Java Implementation

This document becomes the methodological reference for every algorithm implemented in the repository.

All future modules should apply this study process consistently.

The objective is to develop disciplined algorithmic thinking rather than immediate coding habits.

---

# 7. docs/02-complexity/

The `02-complexity/` directory introduces the mathematical and conceptual foundations required to analyse algorithm efficiency.

Unlike the Foundations module, which focuses on reasoning about algorithm correctness, this module focuses on measuring computational cost and understanding how algorithms scale as input size increases.

The objective is to develop the ability to analyse an algorithm before comparing alternative implementations or selecting appropriate data structures.

The current structure is:

```text
02-complexity/
├── README.md
├── computational-complexity.md
├── input-size.md
├── operation-counting.md
├── growth-functions.md
├── asymptotic-notation.md
├── time-complexity.md
├── space-complexity.md
├── time-space-tradeoffs.md
└── complexity-analysis-method.md
```

These documents progressively introduce the principles of computational complexity analysis, moving from concrete operation counting to formal asymptotic reasoning.

The learner should understand that complexity analysis is a mathematical model used to predict algorithm scalability rather than measure execution time on a particular computer.

The expected progression is:

Computational Problem
↓
Algorithm
↓
Input Size Identification
↓
Operation Counting
↓
Growth Function
↓
Asymptotic Analysis
↓
Time Complexity
↓
Space Complexity
↓
Algorithm Comparison

Complexity analysis should always be justified mathematically and conceptually.

The repository intentionally avoids unsupported statements such as:

> Complexity: O(n)

Every complexity claim should explain:

- which operation is analysed;
- how often it executes;
- how the number of executions depends on input size;
- why the resulting growth corresponds to the stated asymptotic notation.

---

## 7.1 README.md

The local `README.md` acts as the entry point to the Computational Complexity module.

It introduces the purpose of complexity analysis, explains the relationship between the documents, and provides navigation through the study sequence.

Its responsibilities include:

- introducing computational complexity;
- presenting the learning progression;
- describing the purpose of each document;
- explaining how complexity analysis supports algorithm comparison throughout the repository.

---

## 7.2 computational-complexity.md

Defines the concept of computational complexity.

It answers:

> What does algorithm complexity measure?

Its responsibilities include:

- definition of computational complexity;
- computational resources;
- time versus space;
- scalability;
- theoretical versus practical performance;
- why complexity analysis matters.

The learner should understand that complexity analysis predicts behaviour as problem size increases rather than measuring elapsed execution time.

---

## 7.3 input-size.md

Defines the concept of input size.

It answers:

> What does **n** represent?

Its responsibilities include:

- definition of input size;
- choosing the appropriate input parameter;
- multiple input variables;
- examples for arrays, strings, trees and graphs;
- influence of input size on scalability.

The document establishes that every complexity analysis begins by identifying the variable that characterises problem size.

---

## 7.4 operation-counting.md

Introduces systematic operation counting.

It answers:

> Which operations determine algorithm cost?

Its responsibilities include:

- significant operations;
- primitive operations;
- counting loop iterations;
- nested loops;
- recursive calls;
- deriving operation counts.

The learner should understand that complexity is derived from analysing dominant operations rather than counting every instruction equally.

---

## 7.5 growth-functions.md

Introduces growth functions.

It answers:

> How does computational cost evolve as input size increases?

Its responsibilities include:

- mathematical growth;
- constant, logarithmic, linear, quadratic and exponential functions;
- comparing growth rates;
- dominant terms;
- ignoring constants.

The document prepares the learner for asymptotic notation.

---

## 7.6 asymptotic-notation.md

Defines asymptotic notation.

It answers:

> How can algorithm growth be expressed mathematically?

Its responsibilities include:

- Big O;
- Big Omega;
- Big Theta;
- upper and lower bounds;
- asymptotic reasoning;
- formal interpretation.

The learner should understand the meaning of each notation instead of treating them as interchangeable labels.

---

## 7.7 time-complexity.md

Focuses on time complexity analysis.

It answers:

> How much computational work does the algorithm perform?

Its responsibilities include:

- best case;
- average case;
- worst case;
- deriving time complexity;
- practical examples;
- comparing algorithms by execution cost.

The document emphasises analytical reasoning instead of memorising complexity values.

---

## 7.8 space-complexity.md

Introduces memory complexity.

It answers:

> How much additional memory does the algorithm require?

Its responsibilities include:

- auxiliary space;
- input space versus extra space;
- recursive stack usage;
- temporary variables;
- memory growth.

The learner should understand that efficient algorithms optimise both execution time and memory usage.

---

## 7.9 time-space-tradeoffs.md

Introduces trade-offs between computational resources.

It answers:

> When is it beneficial to exchange memory for execution time?

Its responsibilities include:

- memory versus performance;
- caching;
- lookup tables;
- preprocessing;
- algorithm design decisions;
- practical examples.

The document establishes that optimisation usually requires balancing multiple computational resources rather than minimising only one.

---

## 7.10 complexity-analysis-method.md

Defines the standard methodology used throughout the repository for analysing algorithm complexity.

It answers:

> How should algorithm complexity be analysed systematically?

It establishes the progression:

Problem Identification
↓
Input Size Definition
↓
Significant Operation Identification
↓
Operation Counting
↓
Growth Function Derivation
↓
Asymptotic Classification
↓
Time Complexity
↓
Space Complexity
↓
Trade-off Evaluation
↓
Algorithm Comparison

This document becomes the methodological reference for every complexity analysis included in the repository.

Every algorithm implemented in later modules should justify its complexity by following this methodology instead of simply stating an asymptotic notation.

---

# 8. Algorithm and Data Structure Documentation

The following directories contain the principal algorithmic study domains:

```text
03-arrays/
04-searching/
05-sorting/
06-algorithmic-patterns/
07-recursion/
08-divide-and-conquer/
09-hashing/
10-stacks-and-queues/
11-trees/
12-heaps/
13-graphs/
14-backtracking/
15-dynamic-programming/
```

Documentation should follow `study-methodology.md` when applicable.

A major algorithm document may contain:

```text
Problem Definition
Input
Input Size
Preconditions
Output
Postconditions
Edge Cases
Algorithmic Strategy
Pseudocode
Manual Execution Trace
Correctness Reasoning
Complexity Analysis
Java Implementation Considerations
Automated Test Strategy
Experimental Analysis
Java Standard Library Comparison
Technical Conclusions
```

Sections should only be included when analytically relevant.

The template is methodological rather than mechanically rigid.

---

# 9. `docs/03-arrays/`

This directory contains the study of arrays as foundational sequential data structures.

Topics may include:

- array representation;
- indexing;
- traversal;
- sequential access;
- candidate tracking;
- maximum and minimum search;
- in-place operations;
- array mutation.

Arrays provide the initial environment for studying state evolution and linear processing.

---

# 10. `docs/04-searching/`

This directory contains search algorithms.

Initial topics include:

- linear search;
- binary search.

The module studies:

- search spaces;
- candidate regions;
- elimination strategies;
- ordering preconditions;
- correctness;
- linear and logarithmic growth.

Search algorithms provide an important transition from complete traversal to systematic search-space reduction.

---

# 11. `docs/05-sorting/`

This directory contains sorting algorithms.

Possible algorithms include:

- bubble sort;
- selection sort;
- insertion sort;
- merge sort;
- quicksort.

The module studies:

- comparisons;
- swaps;
- data movement;
- ordering invariants;
- stability;
- in-place behaviour;
- auxiliary memory;
- divide-and-conquer strategies.

Sorting algorithms should be studied comparatively.

---

# 12. `docs/06-algorithmic-patterns/`

This directory contains recurring algorithmic problem-solving patterns.

Examples include:

- two pointers;
- sliding window;
- prefix sums;
- fast and slow pointers.

These are algorithmic patterns.

They should not be confused with object-oriented design patterns.

The purpose of the module is to identify reusable reasoning structures across computational problems.

---

# 13. `docs/07-recursion/`

This directory contains recursive problem-solving principles.

Topics include:

- base cases;
- recursive cases;
- problem reduction;
- recursive termination;
- call stack behaviour;
- recursive correctness reasoning.

The learner should be capable of explaining why a recursive process terminates and how each call contributes to the final result.

---

# 14. `docs/08-divide-and-conquer/`

This directory studies divide-and-conquer algorithms.

The central structure is:

```text
Divide
   ↓
Conquer
   ↓
Combine
```

Topics may include:

- problem decomposition;
- recursive subproblems;
- combination strategies;
- recurrence relations.

Merge sort provides a significant application of this paradigm.

---

# 15. `docs/09-hashing/`

This directory contains hashing theory and hash-based structures.

Topics include:

- keys;
- hash functions;
- buckets;
- collisions;
- collision resolution;
- load factor;
- expected operation cost.

Custom educational implementations may be compared with Java hash-based collections.

The purpose is to understand the internal computational model rather than replace standard library implementations in production code.

---

# 16. `docs/10-stacks-and-queues/`

This directory contains stack and queue abstractions.

The fundamental processing disciplines are:

```text
Stack
  ↓
LIFO

Queue
  ↓
FIFO
```

The module studies:

- abstract data types;
- insertion;
- removal;
- state evolution;
- operation complexity;
- algorithmic applications.

Java Collections Framework abstractions should be analysed when relevant.

---

# 17. `docs/11-trees/`

This directory contains tree structures and algorithms.

Topics may include:

- tree terminology;
- binary trees;
- binary search trees;
- nodes;
- roots;
- leaves;
- height;
- depth;
- traversal;
- search;
- insertion.

The documentation should preserve conceptual distinctions between tree structures.

```text
Binary Tree
      ≠
Binary Search Tree
      ≠
Binary Heap
```

---

# 18. `docs/12-heaps/`

This directory contains heap structures.

Topics include:

- heap property;
- complete binary trees;
- array representation;
- parent-child index relationships;
- insertion;
- extraction;
- heap restoration;
- priority access.

Educational implementations may be compared with Java priority queue abstractions.

---

# 19. `docs/13-graphs/`

This directory contains graph theory and graph algorithms relevant to the project.

Topics may include:

- vertices;
- edges;
- directed graphs;
- undirected graphs;
- adjacency;
- adjacency lists;
- adjacency matrices;
- breadth-first search;
- depth-first search.

The module places particular emphasis on:

```text
Visited State
        ↓
Exploration Frontier
        ↓
Traversal Progress
```

Representation decisions should be analysed alongside algorithmic complexity.

---

# 20. `docs/14-backtracking/`

This directory contains backtracking techniques.

The central process is:

```text
Choose
   ↓
Explore
   ↓
Validate
   ↓
Undo
   ↓
Continue
```

Topics include:

- decision states;
- partial solutions;
- constraints;
- candidate generation;
- pruning;
- state restoration.

The learner should understand the decision tree represented by the algorithm.

---

# 21. `docs/15-dynamic-programming/`

This directory contains dynamic programming.

Topics include:

- overlapping subproblems;
- optimal substructure;
- state definition;
- recurrence relations;
- memoisation;
- tabulation;
- dependency order.

Dynamic programming documentation must explicitly define the computational state.

Code alone is not considered sufficient evidence of understanding.

A central question is:

> **What does each dynamic programming state represent?**

---

# 22. `docs/16-testing/`

The `16-testing/` directory contains the theoretical and practical study of automated testing.

The project uses:

```text
JUnit Jupiter
        ↓
Test structure, lifecycle, and execution

AssertJ
        ↓
Readable and expressive assertions
```

Topics may include:

- automated testing fundamentals;
- test cases;
- edge-case testing;
- parameterised tests;
- assertion design;
- exception testing;
- test naming;
- test independence.

Testing is derived from algorithm specification.

```text
Preconditions
Postconditions
Edge Cases
Expected Behaviour
        ↓
Test Design
```

Tests provide implementation evidence.

They do not replace correctness reasoning.

---

# 23. `docs/17-benchmarking/`

The `17-benchmarking/` directory contains performance experimentation methodology.

The project uses Java Microbenchmark Harness when controlled Java microbenchmarking is appropriate.

Topics include:

- theoretical analysis versus empirical measurement;
- benchmark hypotheses;
- warmup;
- measurement iterations;
- forks;
- benchmark state;
- JVM optimisation;
- dead-code elimination;
- input generation;
- result interpretation.

Benchmarking should begin with a defined experimental question.

For example:

> How does observed execution behaviour change as input size increases?

The project avoids benchmarking algorithms solely to determine which implementation appears faster in one isolated execution.

---

# 24. `docs/18-linux-environment/`

The `18-linux-environment/` directory integrates Linux into the project as an engineering learning domain.

Linux is not treated as an algorithmic package.

The project does not create:

```text
src/main/java/.../linux/
```

Instead, Linux is studied as a development and validation environment.

The purpose is to develop Linux knowledge relevant to Java backend engineering.

Topics may include:

```text
18-linux-environment/
├── linux-role-in-backend-development.md
├── linux-filesystem-basics.md
├── command-line-fundamentals.md
├── java-environment-setup.md
├── maven-wrapper-on-linux.md
├── git-workflow-on-linux.md
├── file-permissions.md
├── paths-and-case-sensitivity.md
├── cross-platform-validation.md
└── windows-linux-differences.md
```

The module should remain connected to the repository.

It is not intended to become a general Linux administration course.

The central question is:

> **How does a Java backend developer use Linux to work with, execute, and validate a software project?**

---

# 25. Linux as an Engineering Environment

The initial project development environment may be Windows.

The expected progression is:

```text
Windows Development
        ↓
Java and Maven Project
        ↓
Git Repository
        ↓
Linux Environment
        ↓
Cross-Platform Validation
```

The same repository should be used in both environments.

The project should not be duplicated into:

```text
windows-project/
linux-project/
```

Algorithms should remain operating-system independent unless an explicit study objective requires platform-specific behaviour.

For example:

```text
BinarySearch
MergeSort
BreadthFirstSearch
```

should preserve their logical behaviour regardless of whether the JVM executes on Windows or Linux.

---

# 26. Linux Command-Line Study

Linux commands should be studied in the context of project development.

Relevant commands may include:

```text
pwd
ls
cd
mkdir
touch
cp
mv
rm
chmod
cat
less
grep
find
ps
java
git
```

Commands should not be memorised as isolated vocabulary.

The documentation should explain:

- what problem the command solves;
- when it is relevant;
- which project task requires it;
- what risks or side effects exist.

For example:

```text
chmod +x mvnw
```

should be studied in relation to executable permissions and Maven Wrapper execution.

---

# 27. Linux and Maven Wrapper

The Maven Wrapper contributes to reproducible project execution.

The project should use the wrapper when available.

Windows:

```text
mvnw.cmd clean test
```

Linux:

```text
./mvnw clean test
```

The conceptual relationship is:

```text
Repository
    ↓
Maven Wrapper
    ↓
Maven Execution
    ↓
Compilation and Tests
```

The wrapper reduces dependence on a manually configured global Maven installation.

A compatible Java environment is still required.

The expected JDK version should therefore be documented.

---

# 28. Linux Filesystem and Paths

Cross-platform development requires awareness of filesystem differences.

A Windows path may resemble:

```text
C:\Users\User\Projects\Algorithms-Java-Mastery
```

A Linux path may resemble:

```text
/home/user/projects/Algorithms-Java-Mastery
```

The project should avoid unnecessary developer-specific hard-coded paths.

Avoid:

```text
C:\Users\Ana\Desktop\project\data.txt
```

Such paths create environment dependencies.

When file access is studied, path handling should be designed intentionally and validated across environments when relevant.

---

# 29. Linux and Case Sensitivity

Linux environments may expose filename and package inconsistencies.

For example:

```text
BinarySearch.java
```

and:

```text
binarysearch.java
```

may be interpreted as different filenames.

Java class names and source filenames should remain consistent.

```text
public class BinarySearch
```

should correspond to:

```text
BinarySearch.java
```

Cross-platform validation contributes to repository discipline.

---

# 30. Linux and File Permissions

Linux introduces filesystem permission concepts relevant to development workflows.

A script may require executable permission.

For example:

```text
chmod +x mvnw
```

Relevant concepts include:

- read permission;
- write permission;
- execute permission;
- file ownership;
- executable scripts.

The project should study these concepts when they directly affect repository execution.

---

# 31. Linux and Git

The same Git repository is used from Windows and Linux.

```text
              GitHub
              Repository
              ↑      ↑
              │      │
          Windows   Linux
```

Relevant Git operations include:

```text
git status
git branch
git switch
git pull
git add
git commit
git push
```

Linux integration provides an opportunity to develop command-line Git fluency.

It does not create a separate version-control workflow.

---

# 32. Cross-Platform Validation

The project should progressively validate the repository in both Windows and Linux environments.

The validation objective is:

```text
Same Repository
      ↓
Windows Execution
      ↓
Linux Execution
      ↓
Equivalent Build and Test Behaviour
```

Equivalent behaviour means:

- compilation succeeds;
- automated tests execute;
- expected algorithmic behaviour remains valid.

Terminal output formatting does not need to be identical.

The objective is project portability and reproducibility.

---

# 33. `docs/19-ci-cd/`

The `19-ci-cd/` directory contains continuous integration and continuous delivery concepts relevant to the repository.

Possible documents include:

```text
19-ci-cd/
├── continuous-integration.md
├── continuous-delivery.md
├── github-actions-fundamentals.md
├── maven-ci-workflow.md
├── linux-ci-environment.md
└── pipeline-analysis.md
```

The module connects local development with automated repository validation.

The principal workflow is:

```text
Developer
    ↓
Git Commit
    ↓
Git Push
    ↓
GitHub Repository
    ↓
GitHub Actions
    ↓
Linux Runner
    ↓
Maven Build
    ↓
JUnit Tests
    ↓
Workflow Result
```

The learner should understand each stage rather than treating CI as a configuration file copied from another repository.

---

# 34. Continuous Integration

Continuous integration provides automated evidence that the committed repository can be built and tested in a controlled environment.

The project uses CI to detect problems such as:

- missing committed files;
- local machine dependencies;
- incorrect build configuration;
- failing automated tests;
- environment-specific assumptions.

The validation model is:

```text
Local Development
        ↓
Local Validation
        ↓
Repository Commit
        ↓
Automated CI Validation
```

A successful local build and a successful CI build provide different evidence.

Local validation answers:

> Does the project work in the developer's current environment?

CI validation answers:

> Can the committed repository be built and tested automatically in the configured CI environment?

---

# 35. Continuous Delivery

Continuous delivery should initially be studied conceptually.

Algorithms Java Mastery is not primarily a deployable backend service.

Therefore, the project should not artificially create a deployment pipeline solely to claim CI/CD experience.

The repository should clearly distinguish:

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

The practical emphasis of this repository is continuous integration.

Continuous delivery concepts prepare the learner for future backend projects where deployment artefacts and environments are present.

---

# 36. The `.github/` Directory

The `.github/` directory contains GitHub-specific repository configuration.

The current architecture includes:

```text
.github/
└── workflows/
    └── maven-ci.yml
```

This directory is not part of the Java source architecture.

Its responsibility is repository automation.

---

# 37. `.github/workflows/`

The `workflows/` directory contains GitHub Actions workflow definitions.

Workflow files use YAML.

A workflow defines:

- triggering events;
- execution environment;
- jobs;
- steps;
- commands.

Conceptually:

```text
Event
  ↓
Workflow
  ↓
Job
  ↓
Runner
  ↓
Steps
  ↓
Result
```

The learner should understand this hierarchy before modifying the CI workflow.

---

# 38. `maven-ci.yml`

The `maven-ci.yml` file defines Maven-based continuous integration.

Its expected responsibility is:

```text
Checkout Repository
        ↓
Configure Java
        ↓
Prepare Build Environment
        ↓
Execute Maven
        ↓
Run Automated Tests
        ↓
Report Result
```

The workflow should validate the committed project state.

It should not depend on IntelliJ IDEA configuration or local developer files.

The workflow provides an important engineering question:

> **Can this repository validate itself from its committed configuration?**

---

# 39. Relationship Between Linux and CI

Linux and CI are separate documentation modules because they represent different competencies.

```text
Linux
  ↓
Development and operating environment knowledge.

CI
  ↓
Automated software validation process.
```

They are strongly related.

A GitHub Actions workflow may execute using a Linux runner.

The learner should therefore understand the relationship:

```text
Linux Knowledge
       ↓
Understand Runner Environment
       ↓
Understand Shell Commands
       ↓
Understand Maven Execution
       ↓
Understand CI Workflow Behaviour
```

However:

> **Using a Linux CI runner is not equivalent to having practical Linux development experience.**

The project studies both areas explicitly.

---

# 40. `docs/20-conclusions/`

The `20-conclusions/` directory contains the final technical synthesis of the project.

Possible topics include:

- algorithmic learning conclusions;
- complexity analysis conclusions;
- Java implementation lessons;
- testing conclusions;
- benchmarking observations;
- Linux environment lessons;
- CI/CD conclusions;
- future study directions.

The module should not simply repeat previous documentation.

Its purpose is synthesis.

A final conclusion should answer:

```text
What knowledge was developed?

How did the learner's problem-solving process change?

Which algorithmic principles became reusable?

How did Java influence implementation decisions?

What did automated testing contribute?

What did benchmarking demonstrate?

What was learned through Linux validation?

What did CI reveal about reproducibility?

Which limitations remain?

What should be studied next?
```

---

# 41. The `diagrams/` Directory

The `diagrams/` directory contains visual representations that improve algorithmic understanding.

Possible visual artefacts include:

- array state diagrams;
- binary search interval diagrams;
- recursion trees;
- sorting transitions;
- binary trees;
- heaps;
- graphs;
- backtracking decision trees;
- dynamic programming dependency diagrams.

A diagram should exist because it communicates information more clearly than text alone.

The repository avoids decorative diagrams without analytical value.

The central question is:

> **Does this diagram make a computational relationship, structure, or state transition easier to understand?**

---

# 42. The `exercises/` Directory

The `exercises/` directory preserves algorithmic problem-solving practice.

```text
exercises/
├── basic/
├── intermediate/
├── advanced/
└── interview/
```

Exercises are separated from principal implementations because their responsibility is deliberate practice.

---

## 42.1 `basic/`

Contains foundational exercises.

Examples include:

- array traversal;
- maximum search;
- minimum search;
- counters;
- accumulators;
- elementary string processing.

The objective is systematic reasoning.

---

## 42.2 `intermediate/`

Contains problems requiring combinations of concepts.

Examples may involve:

- multiple state variables;
- hashing;
- stacks;
- queues;
- recursive reasoning;
- tree traversal.

The learner should begin identifying reusable patterns.

---

## 42.3 `advanced/`

Contains problems with greater algorithmic reasoning complexity.

Examples may involve:

- graphs;
- backtracking;
- dynamic programming;
- non-trivial complexity analysis.

Difficulty should result from computational reasoning rather than unnecessarily complicated syntax.

---

## 42.4 `interview/`

Contains problems representative of technical interview environments.

The study progression remains:

```text
Problem Constraints
        ↓
Initial Strategy
        ↓
Complexity Analysis
        ↓
Possible Improvement
        ↓
Trade-Off Analysis
        ↓
Implementation
        ↓
Technical Explanation
```

The objective is to develop the ability to communicate algorithmic decisions.

---

# 43. The `notes/` Directory

The `notes/` directory contains complementary working knowledge.

Examples include:

- preliminary observations;
- study questions;
- terminology comparisons;
- alternative explanations;
- unresolved concepts.

The distinction is:

```text
notes/
    Working knowledge.

docs/
    Structured and reviewed knowledge.
```

A note may evolve into formal documentation.

```text
Question
   ↓
Study Note
   ↓
Research
   ↓
Analysis
   ↓
Reviewed Documentation
```

The directory should not become miscellaneous storage.

---

# 44. The `references/` Directory

The `references/` directory supports source traceability.

Possible files include:

```text
references/
├── bibliography.md
├── academic-sources.md
└── official-documentation.md
```

Academic references support:

- algorithm theory;
- correctness reasoning;
- asymptotic analysis;
- data structures;
- algorithm design techniques.

Official technical documentation supports:

- Java;
- Java Collections Framework;
- Maven;
- JUnit Jupiter;
- AssertJ;
- JMH;
- Git;
- GitHub Actions;
- Linux tooling.

The principal theoretical reference is:

**Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; and Stein, Clifford. _Introduction to Algorithms_.**

The repository should not contain unauthorised copies of copyrighted books.

References should preserve bibliographic and source information.

---

# 45. The `src/` Directory

The `src/` directory follows the standard Maven source layout.

```text
src/
├── main/
│   └── java/
└── test/
    └── java/
```

The separation is:

```text
src/main/java/
        ↓
Algorithm implementation.

src/test/java/
        ↓
Automated implementation verification.
```

Maven and standard Java tooling recognise this structure.

---

# 46. `src/main/java/`

The `src/main/java/` directory contains the principal Java implementations.

The source hierarchy follows the Java package namespace.

The initial conceptual structure is:

```text
src/main/java/
└── io/
    └── github/
        └── algorithms/
```

Subdirectories represent algorithmic domains.

```text
algorithms/
├── application/
├── arrays/
├── searching/
├── sorting/
├── patterns/
├── recursion/
├── hashing/
├── stack/
├── queue/
├── tree/
├── heap/
├── graph/
├── backtracking/
├── dynamicprogramming/
└── shared/
```

The source architecture is organised by algorithmic domain.

---

# 47. Java Base Package Decision

The repository belongs to the GitHub identity:

```text
Ana-Alvarez-dev
```

Java package names:

- use lowercase characters;
- cannot use hyphens as package separators;
- should remain stable;
- should represent the project namespace consistently.

A suitable package namespace should therefore be defined before the implementation structure becomes extensive.

A possible namespace is:

```text
io.github.anaalvarezdev.algorithms
```

This would produce:

```text
src/main/java/
└── io/
    └── github/
        └── anaalvarezdev/
            └── algorithms/
```

and:

```java
package io.github.anaalvarezdev.algorithms;
```

The same base package should be mirrored in:

```text
src/main/java/
src/test/java/
```

and used consistently by benchmark code.

Package naming is considered an architectural decision because later modification may affect a large number of Java files.

---

# 48. Algorithm Domain Packages

The project organises implementation by computational domain.

This differs from a backend business application architecture.

The project does not primarily use:

```text
controller/
service/
repository/
domain/
```

because the repository does not model a business system.

Instead:

```text
arrays/
searching/
sorting/
graph/
dynamicprogramming/
```

represent the concepts being studied.

The architecture should reflect the actual project domain.

For **Algorithms Java Mastery**, that domain is algorithmic reasoning and implementation.

---

# 49. `application/`

The `application/` package may contain lightweight executable entry points.

Its responsibilities may include:

- manually executing an algorithm;
- displaying demonstration results;
- supporting educational examples.

It should not contain the algorithm implementation itself.

For example:

```text
application/
└── AlgorithmDemo.java

searching/
└── BinarySearch.java
```

The relationship is:

```text
AlgorithmDemo
      ↓
BinarySearch
```

The dependency direction should not be reversed.

---

# 50. `arrays/`

Contains array-focused algorithms and operations.

Possible implementations include:

- maximum search;
- minimum search;
- reversal;
- duplicate analysis;
- array transformations.

An algorithm belongs here when array processing is its principal study objective.

---

# 51. `searching/`

Contains search algorithms.

Initial implementations may include:

```text
LinearSearch
BinarySearch
```

The package supports the study of search-space processing and elimination strategies.

---

# 52. `sorting/`

Contains sorting algorithms.

Possible implementations include:

```text
BubbleSort
SelectionSort
InsertionSort
MergeSort
QuickSort
```

Grouping these implementations supports direct technical comparison.

---

# 53. `patterns/`

Contains recurring algorithmic patterns.

Examples include:

- two pointers;
- sliding window;
- prefix sums;
- fast and slow pointers.

These are problem-solving patterns rather than object-oriented design patterns.

---

# 54. `recursion/`

Contains implementations whose principal objective is recursive reasoning.

The package supports:

- base-case analysis;
- recursive state reduction;
- termination;
- call-stack understanding.

---

# 55. `hashing/`

Contains hash-related algorithms and educational structures.

Topics implemented may involve:

- hash functions;
- buckets;
- collision handling.

Custom implementations exist for educational analysis.

Production Java development should evaluate standard Java collections before implementing custom hash structures.

---

# 56. `stack/` and `queue/`

These packages represent different abstract data types.

```text
stack/
    LIFO structures and algorithms.

queue/
    FIFO structures and algorithms.
```

They remain separate because their operational contracts and algorithmic applications differ.

---

# 57. `tree/`

Contains tree structures and tree algorithms.

Possible implementations include:

- binary tree nodes;
- binary search trees;
- tree traversal;
- search;
- insertion.

Tree abstractions should preserve their structural invariants.

---

# 58. `heap/`

Contains heap structures and operations.

Possible implementations include:

- min heap;
- max heap;
- insertion;
- extraction;
- heap restoration.

The package is separated from `tree/` because heap properties and operational objectives differ from general tree structures.

---

# 59. `graph/`

Contains graph representations and algorithms.

Possible implementations include:

- adjacency lists;
- adjacency matrices;
- breadth-first search;
- depth-first search.

Graph representation decisions should be explicit because they influence operation complexity.

---

# 60. `backtracking/`

Contains candidate exploration algorithms based on state modification and restoration.

The implementation should make the following process observable:

```text
Choose
Explore
Undo
```

State restoration should be clearly identifiable.

---

# 61. `dynamicprogramming/`

Contains dynamic programming implementations.

The package uses:

```text
dynamicprogramming
```

because Java package names should use lowercase identifiers without hyphens.

Implementations should correspond to documented state definitions and recurrence relations.

---

# 62. `shared/`

Contains genuinely reusable technical abstractions.

Possible responsibilities include:

- shared validation;
- common result representations;
- reusable comparison utilities.

The package should be used conservatively.

A class should not be placed in `shared/` because its ownership is unclear.

The decision question is:

> **Is this abstraction genuinely reused across multiple algorithmic domains?**

If not, the class should remain within its owning domain.

---

# 63. `src/test/java/`

The `src/test/java/` directory contains automated tests.

The package structure should mirror `src/main/java/`.

Example:

```text
src/main/java/
└── io/github/anaalvarezdev/algorithms/searching/
    └── BinarySearch.java
```

Corresponding test:

```text
src/test/java/
└── io/github/anaalvarezdev/algorithms/searching/
    └── BinarySearchTest.java
```

This creates direct discoverability between implementation and verification.

```text
BinarySearch
      ↓
BinarySearchTest
```

Tests should exercise algorithm implementations directly.

They should not depend unnecessarily on demonstration classes from `application/`.

---

# 64. Test Architecture

Tests are derived from algorithm specifications.

```text
Specification
      ↓
Preconditions
Postconditions
Edge Cases
Expected Behaviour
      ↓
Automated Tests
```

The project uses:

```text
JUnit Jupiter
        ↓
Test execution and lifecycle.

AssertJ
        ↓
Readable assertions.
```

Test names should communicate behaviour.

Prefer:

```text
shouldReturnMaximumWhenLargestValueIsAtLastPosition
```

Avoid names that only identify implementation steps.

Testing should verify externally observable behaviour.

---

# 65. The `benchmark/` Directory

The `benchmark/` directory contains performance experiments.

Its responsibility differs from automated testing.

```text
src/test/
    Is the implementation behaviour correct?

benchmark/
    How does the implementation behave experimentally?
```

Benchmark code may use algorithms from `src/main/java/`.

```text
benchmark/
      ↓
depends on
      ↓
src/main/java/
```

The reverse dependency is not permitted conceptually.

```text
src/main/java/
      ✗
must not depend on benchmark code
```

Benchmark infrastructure observes algorithm implementations.

It should not influence their core design unnecessarily.

---

# 66. Benchmark Architecture

Java Microbenchmark Harness is used when controlled microbenchmarking is appropriate.

A benchmark should begin with:

```text
Research Question
        ↓
Hypothesis
        ↓
Experimental Design
        ↓
JMH Benchmark
        ↓
Measurement
        ↓
Result Analysis
        ↓
Technical Conclusion
```

For example:

> As input size increases, linear search execution cost should exhibit approximately linear growth.

Benchmarking should complement theoretical complexity analysis.

```text
Complexity Analysis
        ↓
Predicts growth.

Benchmarking
        ↓
Observes implementation behaviour.
```

Experimental results do not redefine asymptotic complexity.

---

# 67. Root `README.md`

The root `README.md` is the principal repository entry point.

Its responsibility is orientation.

It should communicate:

- project identity;
- project purpose;
- academic direction;
- technologies;
- study methodology;
- repository architecture;
- build requirements;
- basic validation commands;
- documentation navigation.

The README should not reproduce all project documentation.

```text
README
   ↓
Introduces.

docs/
   ↓
Explains in depth.
```

---

# 68. `CONTRIBUTING.md`

The `CONTRIBUTING.md` file defines repository contribution rules.

It may establish:

- branch naming;
- commit conventions;
- pull request expectations;
- documentation requirements;
- testing requirements;
- package organisation;
- build validation.

Even in an individually developed academic project, contribution rules establish engineering discipline.

The document answers:

> **How should repository consistency be preserved when changes are introduced?**

---

# 69. `CHANGELOG.md`

The `CHANGELOG.md` file records meaningful repository evolution.

It should document significant changes such as:

- new study modules;
- new algorithm implementations;
- important architecture modifications;
- testing infrastructure changes;
- benchmark integration;
- Linux environment integration;
- CI workflow introduction.

The changelog should not reproduce the Git commit history.

Git records every committed change.

The changelog communicates meaningful project evolution.

---

# 70. `LICENSE`

The `LICENSE` file defines the legal conditions under which repository content may be used, modified, and distributed.

The selected licence should be consistent with the intended public and educational nature of the repository.

The presence of a licence is a legal decision rather than a decorative repository convention.

---

# 71. `pom.xml`

The `pom.xml` file is the Maven Project Object Model.

It defines the Java project build configuration.

Its responsibilities may include:

- project coordinates;
- Java version;
- dependencies;
- test dependencies;
- plugins;
- build configuration.

Conceptually:

```text
pom.xml
   ↓
Defines Project Build Model
   ↓
Maven
   ↓
Compile
Test
Package
```

Dependencies such as JUnit Jupiter and AssertJ should be declared through Maven.

JMH integration should also be configured intentionally according to the selected benchmark structure.

The `pom.xml` file is part of the reproducibility model of the repository.

---

# 72. Architectural Dependency Direction

The repository should maintain clear conceptual dependency directions.

```text
application
     ↓
algorithm packages

tests
     ↓
algorithm packages

benchmarks
     ↓
algorithm packages
```

Algorithm packages should not depend on:

```text
tests
benchmarks
GitHub Actions
Linux documentation
```

Conceptually:

```text
                    application
                        ↓
                 algorithm code
                   ↑         ↑
                   │         │
                tests    benchmarks
```

Documentation explains all layers but does not participate in Java runtime dependencies.

---

# 73. Relationship Between Academic and Engineering Modules

The complete project progression is:

```text
Foundations
     ↓
Complexity
     ↓
Data Structures
     ↓
Algorithms
     ↓
Algorithmic Patterns
     ↓
Advanced Problem Solving
     ↓
Testing
     ↓
Benchmarking
     ↓
Linux Environment
     ↓
Continuous Integration
     ↓
Technical Conclusions
```

This progression is intentional.

The project begins with the question:

> **How does an algorithm solve a computational problem?**

It later introduces:

> **How can the implementation be verified?**

Then:

> **How can performance behaviour be measured?**

Then:

> **Can the project be executed and validated in another operating environment?**

Finally:

> **Can repository validation be automated?**

This sequence connects computer science foundations with software engineering practice.

---

# 74. Architectural Evolution Principle

The repository architecture may evolve as project knowledge increases.

However, structural changes should be justified.

A new directory or package should not be introduced solely because another repository uses it.

Before modifying the architecture, ask:

```text
What responsibility does this element represent?

Does an existing directory already own that responsibility?

Will the new structure improve discoverability?

Will it reduce conceptual ambiguity?

Is the structure appropriate for this project domain?

Can the decision be explained technically?
```

Architecture should follow project needs.

The project should not follow architecture mechanically.

---

# 75. Final Architectural Principle

The architecture of **Algorithms Java Mastery** represents a progression from algorithmic understanding to reproducible engineering practice.

```text
Study the Problem
        ↓
Document the Theory
        ↓
Design the Algorithm
        ↓
Implement in Java
        ↓
Verify with Tests
        ↓
Evaluate with Benchmarks
        ↓
Validate in Linux
        ↓
Automate with CI
        ↓
Document Technical Conclusions
```

Each repository area has a defined responsibility.

```text
docs/
    Knowledge and reasoning.

diagrams/
    Visual understanding.

exercises/
    Deliberate practice.

notes/
    Working knowledge.

references/
    Source traceability.

src/main/
    Java implementation.

src/test/
    Automated verification.

benchmark/
    Experimental evaluation.

.github/
    Repository automation.
```

The architecture can be summarised through the following principle:

> **Structure should make the learning process visible, the implementation traceable, the verification reproducible, and the engineering decisions explainable.**