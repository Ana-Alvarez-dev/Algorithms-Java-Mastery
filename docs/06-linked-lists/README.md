# Linked Lists

## Algorithms Java Mastery

The **Linked Lists** module introduces one of the fundamental dynamic data
structures studied throughout **Algorithms Java Mastery**.

Unlike arrays, linked lists do not store elements in contiguous memory
locations. Instead, each element maintains explicit references that connect it
to one or more neighbouring elements, allowing the structure to grow and shrink
dynamically during execution.

This different memory organisation leads to distinct computational properties,
trade-offs, and engineering decisions.

The objective of this module is not merely to implement linked lists in Java,
but to understand:

- the computational problems they solve;
- their memory organisation;
- their fundamental operations;
- their correctness;
- their computational complexity;
- the engineering trade-offs between linked lists and arrays.

Understanding linked lists establishes the conceptual foundation required for
studying stacks, queues, hash tables, trees, graphs, and numerous advanced
dynamic data structures.

---

# Academic Foundation

The concepts developed throughout this module are primarily based on
*Introduction to Algorithms* by Cormen, Leiserson, Rivest, and Stein.

They are complemented by recognised Computer Science literature, Java
documentation, and the official references listed in:

```text
docs/00-project/10-references.md
```

The repository does not reproduce textbook explanations or implementations.

Academic concepts are transformed into:

- original explanations;
- formal specifications;
- correctness reasoning;
- complexity analysis;
- independent Java implementations;
- automated tests;
- engineering conclusions.

---

# Purpose

The purpose of this module is to develop a rigorous understanding of linked
lists from both academic and engineering perspectives.

Throughout this module, the learner will study:

- the conceptual foundations of linked lists;
- singly linked lists;
- doubly linked lists;
- circular linked lists;
- node organisation;
- insertion and removal operations;
- traversal techniques;
- computational complexity;
- Java implementations;
- classical linked-list algorithms;
- engineering trade-offs.

Each structure is analysed through the same methodology adopted throughout the
repository.

```text
Linked List Problem
        ↓
Formal Specification
        ↓
Memory Organisation
        ↓
Algorithmic Strategy
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Engineering Conclusions
```

The emphasis remains on understanding the behaviour of the data structure
before implementation.

---

# Core Principles

The Linked Lists module is organised around the following principles.

- Understand linked lists as an abstract data structure before studying Java.
- Analyse memory organisation before analysing operations.
- Distinguish structural correctness from implementation details.
- Compare linked lists with arrays using computational reasoning.
- Study operations through correctness and complexity.
- Verify implementations through automated testing.
- Select data structures according to engineering requirements rather than
  familiarity.

These principles guide every document contained in this module.

---

# Learning Objectives

After completing this module, the learner should be able to perform the
following activities.

## Conceptual understanding

- explain what a linked list is;
- distinguish static and dynamic data structures;
- understand node-based memory organisation;
- compare linked lists with arrays;
- identify situations where linked lists are appropriate.

## Algorithmic reasoning

- analyse insertion strategies;
- analyse removal strategies;
- analyse traversal techniques;
- reason about node manipulation;
- understand pointer relationships conceptually.

## Correctness reasoning

- define structural invariants;
- reason about list integrity;
- verify insertion correctness;
- verify deletion correctness;
- reason about boundary conditions.

## Complexity analysis

- analyse insertion complexity;
- analyse deletion complexity;
- analyse traversal complexity;
- compare linked lists with arrays;
- understand memory trade-offs.

## Java implementation

- implement linked lists in Java;
- design reusable node structures;
- use generic types appropriately;
- compare academic implementations with
  `java.util.LinkedList`;
- maintain clear implementation contracts.

## Automated testing

- verify empty lists;
- verify single-node lists;
- verify insertion;
- verify removal;
- verify traversal;
- verify head and tail operations;
- verify circular references where applicable.

---

# Why Linked Lists Matter

Linked lists demonstrate that the organisation of memory directly influences
algorithm behaviour.

Arrays optimise indexed access through contiguous memory.

Linked lists optimise structural modification by storing explicit links between
elements.

```text
Contiguous Memory
        ↓
Arrays
        ↓
Fast Indexed Access

Node References
        ↓
Linked Lists
        ↓
Efficient Structural Modification
```

Neither organisation is universally superior.

The appropriate choice depends on the computational problem being solved.

---

# Data Structures Studied

The module studies three classical linked-list structures.

| Structure | Characteristics |
|-----------|-----------------|
| Singly Linked List | One directional reference |
| Doubly Linked List | Previous and next references |
| Circular Linked List | Circular node connections |

Each structure introduces different capabilities, implementation costs, and
engineering trade-offs.

---

# Module Progression

The module follows this conceptual progression.

```text
Linked List Fundamentals
        ↓
Singly Linked Lists
        ↓
Doubly Linked Lists
        ↓
Circular Linked Lists
        ↓
Fundamental Operations
        ↓
Complexity Analysis
        ↓
Java Representation
        ↓
Common Algorithms
        ↓
Problem Solving
        ↓
Common Mistakes
        ↓
Interview Review
```

Each document introduces concepts required to understand the following
documents.

---

# Module Structure

```text
06-linked-lists/
├── README.md
├── 01-linked-list-fundamentals.md
├── 02-singly-linked-list.md
├── 03-doubly-linked-list.md
├── 04-circular-linked-list.md
├── 05-list-operations.md
├── 06-complexity-analysis.md
├── 07-java-linked-list.md
├── 08-common-algorithms.md
├── 09-problem-solving-guide.md
├── 10-common-mistakes.md
└── 11-interview-notes.md
```

The sequence progresses from conceptual understanding to implementation,
analysis, engineering practices, and technical review.

---

# Document Overview

## `01-linked-list-fundamentals.md`

Introduces linked lists as a dynamic data structure.

Topics include:

- node concept;
- links between nodes;
- dynamic memory organisation;
- comparison with arrays;
- advantages and disadvantages.

---

## `02-singly-linked-list.md`

Studies singly linked lists.

The document analyses:

- node structure;
- head reference;
- traversal;
- insertion;
- deletion;
- correctness;
- Java implementation.

---

## `03-doubly-linked-list.md`

Introduces doubly linked lists.

Topics include:

- previous references;
- next references;
- bidirectional traversal;
- insertion and deletion;
- engineering trade-offs.

---

## `04-circular-linked-list.md`

Studies circular linked lists.

The document examines:

- circular connections;
- traversal behaviour;
- termination conditions;
- practical applications.

---

## `05-list-operations.md`

Analyses the fundamental operations performed on linked lists.

Operations include:

- insertion;
- removal;
- search;
- traversal;
- update.

Each operation is studied through correctness reasoning and complexity analysis.

---

## `06-complexity-analysis.md`

Compares the computational complexity of linked-list operations.

Topics include:

- insertion complexity;
- deletion complexity;
- traversal complexity;
- auxiliary memory;
- comparison with arrays.

---

## `07-java-linked-list.md`

Studies the representation of linked lists in Java.

Topics include:

- node classes;
- generic implementations;
- academic implementation;
- comparison with `java.util.LinkedList`;
- engineering considerations.

---

## `08-common-algorithms.md`

Applies linked lists to classical algorithms.

Possible examples include:

- reversing a list;
- detecting cycles;
- finding the middle node;
- merging lists;
- removing duplicates.

---

## `09-problem-solving-guide.md`

Defines the methodology used to solve linked-list problems.

The process follows:

```text
Understand the Structure
        ↓
Define Node Relationships
        ↓
Identify Structural Constraints
        ↓
Design the Algorithm
        ↓
Reason about Correctness
        ↓
Analyse Complexity
        ↓
Implement in Java
        ↓
Verify with Automated Tests
```

---

## `10-common-mistakes.md`

Reviews common conceptual and implementation mistakes.

Examples include:

- losing node references;
- incorrect head updates;
- broken links;
- null-reference errors;
- incorrect traversal conditions.

---

## `11-interview-notes.md`

Provides a structured review of linked-list concepts frequently discussed in
technical interviews.

Topics may include:

- reversing a linked list;
- detecting cycles;
- finding the middle node;
- complexity comparison;
- insertion and deletion analysis;
- common interview patterns.

---

# Relationship with Previous Modules

Linked lists extend the concepts introduced throughout the repository.

```text
Project Documentation
        ↓
Foundations
        ↓
Complexity
        ↓
Arrays
        ↓
Searching
        ↓
Sorting
        ↓
Linked Lists
```

Previous modules provide:

- problem specification;
- correctness reasoning;
- computational complexity;
- memory organisation;
- algorithm implementation.

Linked lists introduce dynamic memory structures built upon these concepts.

---

# Relationship with Later Modules

The concepts introduced here support several later modules.

```text
Linked Lists
        ↓
Stacks

Linked Lists
        ↓
Queues

Linked Lists
        ↓
Hash Tables

Linked Lists
        ↓
Trees

Linked Lists
        ↓
Graphs
```

Many dynamic data structures reuse the node-based organisation introduced in
this module.

---

# Relationship with Java

Linked lists are first studied as an abstract data structure before examining
their representation in Java.

The module distinguishes between:

```text
Conceptual Data Structure
        ↓
Node-Based Organisation

Academic Java Implementation
        ↓
Educational Representation

Java Standard Library
        ↓
java.util.LinkedList
```

Understanding the underlying structure remains the primary objective.

---

# Study Methodology

Each topic in this module follows the repository methodology.

```text
Academic Study
        ↓
Conceptual Understanding
        ↓
Structural Specification
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Engineering Conclusions
```

---

# Academic References

The principal academic reference for this module is:

- Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; Stein,
  Clifford.
  *Introduction to Algorithms*. Fourth Edition. MIT Press, 2022.

Additional supporting references include:

- Weiss, Mark Allen.
  *Data Structures and Algorithm Analysis in Java.*

- Sedgewick, Robert; Wayne, Kevin.
  *Algorithms.*

- Oracle.
  *Java Platform API Specification.*

The complete project bibliography is maintained in:

```text
docs/00-project/10-references.md
```

---

# Expected Outcome

After completing this module, the learner should be able to:

```text
Understand Linked Lists
        ↓
Analyse Node Relationships
        ↓
Reason about Correctness
        ↓
Analyse Complexity
        ↓
Implement in Java
        ↓
Validate Behaviour
        ↓
Justify Engineering Decisions
```

The learner should understand that linked lists are not simply an alternative
to arrays, but a different computational model with distinct structural
properties, performance characteristics, and engineering applications.

---

# Next Document

```text
01-linked-list-fundamentals.md
```

The next document introduces the conceptual foundations of linked lists,
including node organisation, dynamic memory, structural relationships, and the
comparison between linked lists and arrays.