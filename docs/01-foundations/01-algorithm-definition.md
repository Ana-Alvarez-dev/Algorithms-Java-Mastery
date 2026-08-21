# Algorithm Definition

## Algorithms Java Mastery

This document introduces the concept of an algorithm from a Computer Science
perspective.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic study of algorithms throughout the repository.

The objective of this document is to establish a precise conceptual distinction
between computational problems, algorithms, implementations, and executable
programs before studying more advanced topics such as correctness,
computational complexity, data structures, and algorithm design techniques.

An algorithm is treated as an abstract computational solution whose existence
is independent of any particular programming language.

The central question addressed throughout this document is:

> **What is an algorithm?**

---

# Purpose

The purpose of this document is to develop a precise understanding of what an
algorithm is and how it relates to computational problems and executable
programs.

Beginning learners frequently associate algorithms directly with source code.

This document deliberately separates those concepts.

The fundamental progression is:

```text
Computational Problem
        ↓
Algorithm
        ↓
Implementation
        ↓
Executable Program
```

A computational problem defines what must be solved.

An algorithm defines a systematic procedure for solving that problem.

An implementation expresses the algorithm using a particular programming
language.

The resulting program provides an executable representation of that
implementation.

This distinction establishes one of the most important principles followed
throughout **Algorithms Java Mastery**:

> **Understanding the algorithm precedes implementing the algorithm.**

---

# Learning Objectives

After studying this document, the learner should be able to:

- define an algorithm from a computational perspective;
- distinguish between computational problems, algorithms, implementations, and
  programs;
- identify the fundamental characteristics associated with algorithms;
- explain why algorithms are independent of programming languages;
- recognise algorithms as abstract computational procedures;
- identify the relationship between input, processing, and output;
- understand that multiple algorithms may solve the same computational problem;
- recognise correctness and efficiency as distinct properties of algorithmic
  solutions;
- explain why algorithmic reasoning must precede Java implementation.

---

# Computational Problems and Algorithms

Algorithms exist because computational problems exist.

A **computational problem** describes a general relationship between valid inputs
and the outputs that should be produced.

Conceptually:

```text
Computational Problem
        ↓
Defines
        ↓
Required Relationship
Between Input and Output
```

An algorithm provides a systematic procedure for producing the required output
for an instance of that problem.

Therefore:

```text
Computational Problem
        ↓
Algorithmic Solution
        ↓
Required Output
```

The problem defines **what must be solved**.

The algorithm defines **how the problem can be solved computationally**.

This distinction is fundamental throughout the repository.

---

# Definition of an Algorithm

An **algorithm** is a well-defined computational procedure that receives input
values when required, performs a finite sequence of precisely specified
operations, and produces one or more outputs that solve a specified
computational problem.

Conceptually:

```text
Input
   ↓
Algorithm
   ↓
Output
```

The algorithm itself does not depend upon Java, Python, C++, or any other
programming language.

Programming languages provide mechanisms for expressing algorithms in a form
that computers can execute.

For example, the same binary-search algorithm may be implemented in multiple
languages while preserving the same underlying computational strategy.

---

# Fundamental Characteristics of Algorithms

Algorithms are generally associated with several fundamental characteristics.

These properties help distinguish algorithmic procedures from ambiguous or
unbounded descriptions of computation.

---

## Finiteness

An algorithm must terminate after a finite number of computational steps.

A procedure that continues indefinitely without reaching a defined result does
not satisfy the usual requirement of algorithmic termination.

Termination becomes particularly important when reasoning formally about
correctness.

---

## Definiteness

Each computational step must be sufficiently precise and unambiguous.

The operations performed by the algorithm should have clearly defined
behaviour.

Ambiguous instructions prevent reliable implementation and correctness
reasoning.

---

## Input

An algorithm may conceptually receive zero or more external inputs.

However, the computational problems studied throughout this repository are
generally expressed through **problem instances** that provide one or more input
values.

Examples include:

- arrays;
- numbers;
- strings;
- trees;
- graphs;
- collections of values.

The input represents the information upon which the algorithm operates.

---

## Output

An algorithm produces one or more results corresponding to the computational
problem being solved.

The output must satisfy the requirements established by the problem
specification.

For example:

```text
Input
    ↓
Array + Target Value
    ↓
Search Algorithm
    ↓
Target Position or Absence Result
```

---

## Effectiveness

The operations performed by an algorithm must be sufficiently basic and
well-defined to be carried out computationally.

An algorithm should therefore describe executable computational steps rather
than undefined or purely intuitive actions.

---

# Algorithm vs Program

An algorithm and a program are related but distinct concepts.

An algorithm is an abstract computational solution.

A program is a concrete executable implementation written using one or more
programming languages.

The relationship may be represented as:

```text
Problem
   ↓
Algorithm
   ↓
Implementation
   ↓
Program
```

Consider the following example.

## Problem

> Find the largest value contained in a finite collection.

## Algorithm

1. Treat the first value as the current maximum.
2. Compare each remaining value with the current maximum.
3. Replace the current maximum whenever a larger value is encountered.
4. Return the final maximum.

## Possible Java Implementation

```java
public static int findMaximum(int[] values) {
    int maximum = values[0];

    for (int i = 1; i < values.length; i++) {
        if (values[i] > maximum) {
            maximum = values[i];
        }
    }

    return maximum;
}
```

The Java code is not the algorithm itself.

It is one implementation of the algorithm.

The same algorithm could be implemented using another programming language
without changing its underlying reasoning.

---

# Problem Instances

A computational problem usually describes a general class of related inputs.

Each concrete input represents a **problem instance**.

For example:

```text
Problem
    ↓
Find a value in a sorted array
```

Possible instances include:

```text
[1, 3, 5, 7, 9]      target = 7

[2, 4, 8, 16, 32]    target = 5

[]                    target = 10
```

An algorithm designed for the general problem should behave correctly for every
valid instance covered by its specification.

This distinction becomes important when studying:

- correctness;
- edge cases;
- complexity;
- automated testing.

---

# Multiple Algorithms for the Same Problem

A single computational problem may have multiple valid algorithmic solutions.

For example, the problem of searching for a value may be approached using
different strategies.

```text
Searching Problem
        │
        ├── Linear Search
        │
        └── Binary Search
```

Both may solve a searching problem, but they operate differently and may require
different assumptions.

Linear search can inspect elements sequentially.

Binary search requires ordered input and repeatedly reduces the remaining search
space.

This illustrates an important principle:

> **Correct algorithms may differ in strategy, assumptions, complexity, and
> engineering trade-offs.**

The existence of multiple solutions later motivates systematic comparison
through correctness reasoning and computational complexity analysis.

---

# Deterministic Behaviour

Most algorithms studied throughout the initial modules of this repository are
deterministic.

For a deterministic algorithm, the same valid input under the same defined
conditions produces the same expected result.

Conceptually:

```text
Input
   ↓
Deterministic Algorithm
   ↓
Expected Output
```

Later areas of Computer Science may introduce randomised algorithms or other
computational models.

Those topics are outside the immediate scope of this foundational document.

---

# Correctness

An algorithm is not considered correct merely because it executes successfully
or produces the expected result for several examples.

Correctness requires demonstrating that the algorithm satisfies its
specification for every valid input covered by its assumptions.

Conceptually:

```text
Problem Specification
        ↓
Algorithm
        ↓
Correctness Reasoning
        ↓
Required Result
```

Correctness therefore depends upon logical reasoning.

Automated testing can provide important execution evidence, but a finite number
of tests cannot generally establish correctness for every possible valid input.

This topic is developed systematically in:

```text
05-correctness.md
```

---

# Efficiency

Different correct algorithms may require different amounts of computational
resources.

Examples include differences in:

- number of operations;
- execution time;
- auxiliary memory;
- recursion depth;
- input-dependent behaviour.

For example:

```text
Same Computational Problem

        ↓

Algorithm A
        ↓
Different Computational Cost

Algorithm B
        ↓
Different Computational Cost
```

The systematic study of these differences belongs to the Complexity module.

```text
docs/02-complexity/
```

There the learner will study concepts including:

- input size;
- significant operations;
- operation counting;
- growth functions;
- asymptotic notation;
- time complexity;
- auxiliary-space complexity.

Correctness answers:

> **Does the algorithm solve the problem?**

Complexity analysis asks:

> **How do the resources required by the algorithm grow as the problem size
> increases?**

Both questions are essential, but they represent different dimensions of
algorithm analysis.

---

# Algorithms Throughout This Repository

Every algorithm studied throughout **Algorithms Java Mastery** follows a
structured progression.

```text
Problem Understanding
        ↓
Formal Specification
        ↓
Algorithm Design
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
Experimental Benchmarking
        ↓
Engineering Conclusions
```

Not every topic requires every stage with the same degree of depth.

However, the central methodological principle remains constant:

> **Reason before implementing, verify before concluding, and measure only
> after correctness has been established.**

This progression connects the theoretical study of algorithms with the
engineering practices introduced throughout later modules.

---

# Key Takeaways

After completing this document, the learner should understand that:

- a computational problem defines what must be solved;
- an algorithm defines a systematic procedure for solving that problem;
- an implementation expresses an algorithm using a programming language;
- a program provides executable behaviour;
- algorithms exist independently of Java or any other implementation language;
- algorithms operate on problem instances;
- multiple algorithms may solve the same computational problem;
- correctness and efficiency are different properties;
- successful execution alone does not establish correctness;
- algorithmic reasoning should precede implementation.

The study of algorithms therefore begins with understanding computational
problems and reasoning about solutions rather than writing source code.

This principle establishes the conceptual foundation for the remainder of the
**Foundations** module.

---

# Next Document

```text
02-problem-specification.md
```

The next document examines how a computational problem should be described
precisely before an algorithmic strategy is designed.

It introduces the systematic identification of:

- inputs;
- outputs;
- assumptions;
- constraints;
- valid problem instances;
- expected behaviour.

Problem specification provides the formal connection between a computational
problem and the algorithm expected to solve it.

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The book provides the principal academic foundation for the definition and
systematic study of algorithms throughout **Algorithms Java Mastery**.

Additional academic and technical references supporting the project are listed
in:

```text
docs/00-project/10-references.md
```

Complementary references may be introduced in later documents when a topic
requires additional theoretical depth.