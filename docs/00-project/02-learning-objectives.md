# Learning Objectives

## Algorithms Java Mastery

This document defines the learning objectives and expected competencies developed throughout **Algorithms Java Mastery**.

The project adopts a competency-based approach to algorithmic study. Progress is not measured exclusively by the number of algorithms implemented or exercises completed.

Instead, learning is evaluated through the ability to:

* understand computational problems;
* formulate precise specifications;
* design algorithmic strategies;
* reason about correctness;
* analyse computational cost;
* implement solutions clearly in Java;
* verify behaviour through automated testing;
* evaluate performance experimentally when appropriate;
* communicate technical reasoning through structured documentation.

The central learning objective is:

> **Develop the ability to analyse, design, justify, implement, test, and evaluate algorithmic solutions using rigorous computer science foundations and professional Java engineering practices.**

---

# 1. Learning Philosophy

Algorithmic competence is not equivalent to implementation memorisation.

A developer may reproduce an existing sorting algorithm without understanding:

* why the algorithm produces an ordered sequence;
* which property remains valid during execution;
* how many operations are performed;
* how additional memory is used;
* which inputs represent relevant edge cases;
* why another algorithm may be preferable under different conditions.

For this reason, the project distinguishes between three levels of learning.

```text
KNOWLEDGE
    ↓
Understand concepts and terminology.

REASONING
    ↓
Analyse problems and justify solutions.

APPLICATION
    ↓
Implement, test, measure, and document solutions.
```

The expected progression is:

```text
Know
  ↓
Understand
  ↓
Apply
  ↓
Analyse
  ↓
Justify
  ↓
Evaluate
```

The repository should provide evidence of this progression.

---

# 2. General Learning Objectives

Upon completing the project, the learner should be able to:

1. Analyse computational problems before implementation.
2. Define algorithm inputs, outputs, preconditions, and postconditions.
3. Identify assumptions, constraints, and edge cases.
4. Design systematic algorithmic strategies.
5. Express algorithms through precise pseudocode.
6. Perform manual execution traces.
7. Reason about algorithm correctness.
8. Identify and explain loop invariants when applicable.
9. Analyse time and auxiliary space complexity.
10. Apply asymptotic notation appropriately.
11. Implement fundamental algorithms and data structures in Java.
12. Compare alternative algorithmic solutions.
13. Recognise recurring algorithmic problem-solving patterns.
14. Design automated tests from algorithm specifications.
15. Use JUnit Jupiter and AssertJ for implementation verification.
16. Distinguish theoretical complexity from empirical performance.
17. Design and interpret Java microbenchmarks using JMH when appropriate.
18. Compare educational implementations with Java standard abstractions.
19. Validate the project in Windows and Linux environments.
20. Document algorithmic reasoning and engineering conclusions clearly.

These objectives define the expected global competencies of the project.

---

# 3. Computational Problem Analysis

## Objective

Develop the ability to transform an informal problem statement into a precise computational problem.

The learner should be able to identify:

* the problem being solved;
* the input domain;
* the expected output;
* the relevant input size;
* valid and invalid inputs;
* assumptions;
* constraints;
* edge cases.

For a problem such as:

> Find the maximum value in an array.

The learner should be capable of producing a specification similar to:

```text
Input:
An array A containing n integer values.

Input size:
n = number of elements in A.

Precondition:
n ≥ 1.

Output:
A value m belonging to A such that
every element x in A satisfies x ≤ m.
```

The expected competency is not the ability to write:

```java
int max = array[0];
```

The expected competency is the ability to explain **why the problem permits that initialisation and which precondition makes it valid**.

## Evidence of learning

Evidence may include:

* written problem specifications;
* input and output definitions;
* precondition analysis;
* postcondition definitions;
* edge-case tables;
* manual problem decomposition.

---

# 4. Algorithmic Strategy Design

## Objective

Develop the ability to design a computational strategy before translating the solution into Java.

The learner should be able to answer:

```text
What information must be maintained?

Which data must be processed?

In what order should it be processed?

Can previous information be summarised?

Can the search space be reduced?

Can the problem be divided?

Are subproblems repeated?

Is backtracking necessary?

Can previous results be stored?
```

The project progressively introduces different forms of algorithmic reasoning.

```text
Sequential processing
        ↓
Search-space reduction
        ↓
State maintenance
        ↓
Divide and conquer
        ↓
Recursive decomposition
        ↓
Backtracking
        ↓
Dynamic programming
```

The learner should recognise that different problem structures suggest different strategies.

## Expected competency

Given a computational problem, the learner should be able to propose a strategy using natural language before writing source code.

The strategy should explain:

* the central idea;
* the information maintained;
* the processing sequence;
* the termination condition;
* the expected result.

## Evidence of learning

Evidence may include:

* strategy descriptions;
* solution diagrams;
* pseudocode;
* comparisons between alternative strategies;
* written justification of the selected approach.

---

# 5. Pseudocode and Algorithm Representation

## Objective

Develop the ability to represent algorithms independently of Java syntax.

Pseudocode is used as an intermediate representation between conceptual reasoning and implementation.

```text
Problem
   ↓
Strategy
   ↓
Pseudocode
   ↓
Java
```

The learner should be able to express:

* assignments;
* conditions;
* iteration;
* recursion;
* procedure calls;
* return values;
* abstract data structure operations.

Example:

```text
MAXIMUM(A)

    max ← A[0]

    for i ← 1 to A.length - 1

        if A[i] > max
            max ← A[i]

    return max
```

The learner should understand that pseudocode describes an algorithmic process rather than a compilable program.

## Expected competency

The learner should be able to translate:

```text
Informal strategy → Pseudocode
```

and later:

```text
Pseudocode → Java implementation
```

without allowing language syntax to replace algorithmic reasoning.

## Evidence of learning

Evidence may include:

* documented pseudocode;
* pseudocode execution traces;
* correspondence analysis between pseudocode and Java implementations.

---

# 6. Manual Algorithm Tracing

## Objective

Develop the ability to execute an algorithm manually and observe changes in its state.

A manual trace records relevant algorithm state after each significant operation.

For example:

```text
Input:
[7, 2, 15, 4, 9]
```

A trace may record:

|    Step | Current value | Current maximum | Comparison | Result     |
| ------: | ------------: | --------------: | ---------- | ---------- |
| Initial |             7 |               7 | —          | Initialise |
|       1 |             2 |               7 | 2 > 7      | False      |
|       2 |            15 |               7 | 15 > 7     | True       |
|       3 |             4 |              15 | 4 > 15     | False      |
|       4 |             9 |              15 | 9 > 15     | False      |

Manual tracing is used to develop understanding of:

* algorithm state;
* variable evolution;
* iteration behaviour;
* decision points;
* defects in reasoning.

## Expected competency

The learner should be able to trace an algorithm without executing Java code.

This ability is particularly relevant when studying:

* loops;
* recursion;
* sorting;
* tree traversal;
* graph traversal;
* backtracking;
* dynamic programming.

## Evidence of learning

Evidence may include:

* trace tables;
* recursion trees;
* state diagrams;
* manually recorded comparisons;
* dynamic programming tables.

---

# 7. Correctness Reasoning

## Objective

Develop the ability to explain why an algorithm satisfies its specification.

The project distinguishes:

```text
The algorithm works for this example.
                ≠
The algorithm is correct for every valid input.
```

Correctness reasoning connects:

```text
Preconditions
      ↓
Algorithm
      ↓
Postconditions
```

The learner should progressively develop the ability to reason about:

* valid input domains;
* preserved properties;
* algorithm termination;
* satisfaction of the expected result.

## Loop invariants

For iterative algorithms, the learner should understand the role of loop invariants.

The reasoning process follows:

```text
INITIALISATION
Is the invariant true before the first iteration?

MAINTENANCE
If it is true before an iteration,
does the iteration preserve it?

TERMINATION
When the loop terminates,
does the invariant imply the postcondition?
```

## Expected competency

The learner should be able to explain correctness at a level appropriate to the algorithm being studied.

For a simple traversal, a concise invariant may be sufficient.

For more advanced algorithms, the reasoning may require:

* induction;
* recursive correctness arguments;
* state definitions;
* recurrence relationships;
* optimal substructure reasoning.

## Evidence of learning

Evidence may include:

* invariant definitions;
* initialisation analysis;
* maintenance arguments;
* termination arguments;
* recursive correctness explanations;
* postcondition justification.

---

# 8. Computational Complexity Analysis

## Objective

Develop the ability to analyse how algorithm resource usage grows as the input size increases.

The learner should distinguish between:

```text
Execution time
        ↓
Measured duration on a concrete system.

Time complexity
        ↓
Growth of computational work.

Memory consumption
        ↓
Concrete memory usage.

Space complexity
        ↓
Growth of required memory resources.
```

The project studies:

* input size;
* significant operations;
* operation counting;
* orders of growth;
* asymptotic notation;
* best-case behaviour;
* average-case behaviour;
* worst-case behaviour;
* auxiliary space.

## Expected competency

For an algorithm, the learner should be able to identify:

```text
Input size:
What does n represent?

Significant operation:
What operation reflects the algorithm's work?

Time complexity:
How does work grow with n?

Space complexity:
How does auxiliary memory grow with n?
```

The learner should progressively use:

```text
O
Ω
Θ
```

with appropriate meaning and justification.

The objective is not to attach a complexity label to memorised algorithms.

The objective is to **derive or justify the complexity from the algorithmic process**.

## Evidence of learning

Evidence may include:

* operation counts;
* mathematical expressions;
* recurrence relations;
* asymptotic analysis;
* comparison tables;
* complexity justification.

---

# 9. Fundamental Data Structures

## Objective

Develop a conceptual and practical understanding of fundamental data structures.

The project studies:

* arrays;
* stacks;
* queues;
* hash-based structures;
* trees;
* heaps;
* graphs.

For each structure, the learner should analyse:

```text
What problem does the structure address?

How is data organised?

Which operations are fundamental?

What information must be maintained?

What are the expected operation costs?

What trade-offs exist?

Which Java abstractions are related?
```

## Expected competency

The learner should be able to explain the relationship between data organisation and algorithmic behaviour.

For example:

```text
Array
    ↓
Indexed access

Stack
    ↓
LIFO processing

Queue
    ↓
FIFO processing

Hash structure
    ↓
Key-based access

Tree
    ↓
Hierarchical relationships

Heap
    ↓
Priority access

Graph
    ↓
General relationships
```

The learner should understand that selecting a data structure is an algorithmic and engineering decision.

## Evidence of learning

Evidence may include:

* custom implementations;
* operation analysis;
* structural diagrams;
* complexity tables;
* Java Collections Framework comparisons.

---

# 10. Searching Algorithms

## Objective

Develop the ability to analyse strategies for locating information.

The learner should study:

* linear search;
* binary search;
* search-space reduction.

The central learning progression is:

```text
Examine elements sequentially
            ↓
Identify structural assumptions
            ↓
Exploit ordering
            ↓
Reduce the search space
```

## Expected competency

The learner should be able to explain:

* why linear search does not require ordered input;
* why binary search requires an ordering assumption;
* how binary search reduces the search interval;
* which invariant describes the candidate search region;
* why binary search terminates;
* how their complexities differ.

## Evidence of learning

Evidence may include:

* manual search traces;
* search interval diagrams;
* correctness arguments;
* complexity comparisons;
* Java implementations;
* automated tests.

---

# 11. Sorting Algorithms

## Objective

Develop the ability to compare different strategies for ordering data.

The project studies elementary and efficient sorting algorithms.

The learner should analyse characteristics such as:

* comparisons;
* swaps or movements;
* auxiliary memory;
* stability;
* in-place behaviour;
* best-case behaviour;
* worst-case behaviour;
* divide-and-conquer strategies.

## Expected competency

The learner should not evaluate a sorting algorithm using only the question:

> Which algorithm is faster?

The learner should consider:

```text
Input characteristics
Algorithmic complexity
Memory requirements
Stability
Implementation strategy
Observed performance
```

The expected competency is the ability to compare algorithms through explicit trade-offs.

## Evidence of learning

Evidence may include:

* sorting traces;
* comparison counts;
* swap counts;
* complexity analyses;
* algorithm comparison documents;
* JMH experiments when appropriate.

---

# 12. Recursion and Divide and Conquer

## Objective

Develop the ability to reason about problems defined through smaller instances of themselves.

The learner should understand:

* base cases;
* recursive cases;
* recursive state;
* call-stack behaviour;
* termination;
* recurrence relations.

For divide-and-conquer algorithms, the learner should identify:

```text
DIVIDE
Split the problem.

CONQUER
Solve subproblems.

COMBINE
Construct the final result.
```

## Expected competency

The learner should be able to determine:

* why recursion terminates;
* what changes between recursive calls;
* which base case stops the process;
* how recursive results contribute to the final solution.

## Evidence of learning

Evidence may include:

* recursion trees;
* call traces;
* recurrence definitions;
* recursive implementations;
* iterative and recursive comparisons.

---

# 13. Hashing

## Objective

Develop an understanding of key-based data access and hash-based structures.

The learner should study:

* keys;
* hash functions;
* buckets;
* collisions;
* collision-resolution strategies;
* load factor;
* expected operation cost.

## Expected competency

The learner should understand why hash-based access may provide efficient expected behaviour while still requiring analysis of collisions and distribution.

The learner should distinguish between:

```text
Expected behaviour
        ↓
Typical operation cost under assumptions.

Worst-case behaviour
        ↓
Maximum degradation under unfavourable conditions.
```

## Evidence of learning

Evidence may include:

* custom hash structure implementations;
* collision examples;
* bucket diagrams;
* load-factor analysis;
* comparison with Java hash-based collections.

---

# 14. Trees and Heaps

## Objective

Develop the ability to reason about hierarchical and priority-based structures.

For trees, the learner should study:

* nodes;
* roots;
* children;
* leaves;
* depth;
* height;
* traversal;
* search relationships.

For heaps, the learner should study:

* heap properties;
* complete binary tree representation;
* priority access;
* insertion;
* extraction;
* heap restoration.

## Expected competency

The learner should be able to connect structural properties with algorithmic guarantees.

For example:

```text
Heap property
      ↓
Efficient access to a priority element
```

The learner should also understand the distinction between:

```text
Binary tree
Binary search tree
Binary heap
```

These terms should not be treated as interchangeable.

## Evidence of learning

Evidence may include:

* tree diagrams;
* traversal traces;
* heap arrays;
* heapify traces;
* operation complexity analysis;
* Java implementations.

---

# 15. Graph Algorithms

## Objective

Develop the ability to model and process general relationships between entities.

The learner should study:

* vertices;
* edges;
* directed graphs;
* undirected graphs;
* weighted relationships when applicable;
* adjacency representations;
* traversal.

The initial algorithmic focus includes:

* breadth-first search;
* depth-first search.

## Expected competency

The learner should be able to explain:

* how a graph models relationships;
* how adjacency lists and matrices differ;
* why visited-state tracking is necessary;
* how BFS and DFS explore a graph differently;
* how representation affects space and operation cost.

## Evidence of learning

Evidence may include:

* graph diagrams;
* adjacency representations;
* traversal traces;
* visited-state tables;
* BFS and DFS implementations;
* complexity analysis.

---

# 16. Backtracking

## Objective

Develop the ability to analyse problems that require systematic exploration of candidate solutions.

The learner should understand the general process:

```text
Choose
   ↓
Explore
   ↓
Validate
   ↓
Undo
   ↓
Try another alternative
```

The learner should study:

* decision spaces;
* partial solutions;
* validity conditions;
* pruning;
* state restoration.

## Expected competency

The learner should recognise when a problem requires exploring multiple possible decisions and understand how invalid branches can be abandoned.

## Evidence of learning

Evidence may include:

* decision trees;
* state traces;
* pruning explanations;
* recursive implementations;
* complexity discussions.

---

# 17. Dynamic Programming

## Objective

Develop the ability to identify problems containing repeated subproblems and reusable computational results.

The learner should study:

* overlapping subproblems;
* optimal substructure;
* state definition;
* recurrence relations;
* memoisation;
* tabulation.

The progression should be:

```text
Recursive solution
        ↓
Identify repeated computation
        ↓
Define state
        ↓
Store results
        ↓
Avoid recomputation
```

## Expected competency

The learner should be able to explain:

* what the state represents;
* how the recurrence is defined;
* which base cases are required;
* why stored results reduce repeated work;
* the difference between memoisation and tabulation.

## Evidence of learning

Evidence may include:

* recurrence definitions;
* recursive trees;
* repeated-subproblem identification;
* memoisation tables;
* tabulation tables;
* complexity comparisons.

---

# 18. Java Implementation Competency

## Objective

Develop the ability to translate algorithmic reasoning into clear Java implementations.

The learner should demonstrate:

* meaningful naming;
* coherent package organisation;
* appropriate method signatures;
* explicit input assumptions;
* controlled mutation;
* appropriate use of static methods when relevant;
* generic types when conceptually justified;
* separation between implementation and test code.

The Java implementation should reflect the documented algorithm.

```text
Documentation
      ↓
Defines the strategy.

Implementation
      ↓
Expresses the strategy in Java.

Tests
      ↓
Verify the expected behaviour.
```

## Expected competency

A learner should be able to explain the correspondence between the pseudocode and the Java implementation.

The code should not contain unnecessary framework abstractions that obscure the algorithm being studied.

## Evidence of learning

Evidence may include:

* Java source files;
* package organisation;
* implementation documentation;
* pseudocode-to-code comparisons;
* refactoring records.

---

# 19. Automated Testing Competency

## Objective

Develop the ability to derive automated tests from algorithm specifications.

The learner should use:

* JUnit Jupiter;
* AssertJ.

The test design process follows:

```text
Specification
      ↓
Expected Behaviour
      ↓
Relevant Cases
      ↓
Automated Tests
```

The learner should design tests for:

* representative cases;
* minimal valid inputs;
* edge cases;
* repeated values;
* ordered inputs;
* reverse-ordered inputs;
* negative values when valid;
* invalid inputs when the API defines validation behaviour.

## Expected competency

The learner should be able to explain why a test exists.

A test should answer:

> Which behaviour, property, or boundary condition is being verified?

The project should avoid tests created only to increase numerical coverage percentages.

## Evidence of learning

Evidence may include:

* JUnit test classes;
* AssertJ assertions;
* parameterised tests;
* documented edge-case selection;
* failure analysis.

---

# 20. Experimental Performance Analysis

## Objective

Develop the ability to evaluate implementation performance using controlled experiments.

The learner should understand that:

```text
System.nanoTime()
        ≠
Automatically reliable microbenchmark
```

The project introduces JMH when microbenchmarking becomes relevant.

The learner should study:

* warmup;
* iterations;
* forks;
* benchmark state;
* JVM optimisation effects;
* dead-code elimination concerns;
* benchmark input design.

## Expected competency

The learner should be able to distinguish between:

```text
Theoretical claim:
Binary search has logarithmic growth.

Experimental observation:
A measured implementation produced specific results
under defined benchmark conditions.
```

The learner should avoid generalising benchmark results beyond the tested conditions.

## Evidence of learning

Evidence may include:

* JMH benchmark classes;
* benchmark configuration;
* recorded results;
* theoretical-versus-observed analysis;
* documented limitations.

---

# 21. Java Collections Framework Comparison

## Objective

Develop the ability to connect academic data structure study with professional Java development.

For relevant topics, the learner should compare custom implementations with standard Java abstractions.

Questions should include:

```text
Which Java interface represents this abstraction?

Which standard implementations are available?

What guarantees does the API expose?

What implementation details differ?

Would a custom implementation be justified in production?
```

The purpose is not to reproduce the Java Collections Framework.

The purpose is to understand the computational foundations behind commonly used abstractions.

## Expected competency

The learner should be able to state:

> I understand how this structure works internally and I also understand which standard Java abstraction I would normally evaluate for production development.

## Evidence of learning

Evidence may include:

* comparison documents;
* interface analysis;
* complexity comparisons;
* engineering conclusions.

---

# 22. Linux and Environment Validation

## Objective

Develop the ability to build and test the project independently of the primary Windows development environment.

The learner should become familiar with:

* Linux filesystem navigation;
* shell commands;
* Java execution;
* Maven Wrapper execution;
* Git command-line operations;
* environment differences.

The project should be validated using:

```text
Windows
   ↓
mvnw.cmd test
```

and:

```text
Linux
   ↓
./mvnw test
```

## Expected competency

The learner should understand that Linux is not a separate implementation target for the algorithms.

It is an engineering environment in which project portability and reproducibility can be validated.

## Evidence of learning

Evidence may include:

* successful Linux builds;
* environment documentation;
* resolved permission issues;
* cross-platform workflow validation.

---

# 23. Continuous Integration Competency

## Objective

Develop an understanding of automated project validation.

The learner should understand the workflow:

```text
Code Change
     ↓
Git Commit
     ↓
Git Push
     ↓
GitHub Actions
     ↓
Maven Build
     ↓
Automated Tests
     ↓
Validation Result
```

The continuous integration workflow should validate the repository in a controlled environment.

## Expected competency

The learner should be able to explain:

* why CI is used;
* what the workflow validates;
* why a local successful build is not the only relevant validation;
* how failed automated tests affect the workflow.

## Evidence of learning

Evidence may include:

* GitHub Actions workflows;
* successful CI executions;
* failure analysis;
* documented workflow changes.

---

# 24. Technical Documentation Competency

## Objective

Develop the ability to communicate algorithmic and engineering reasoning through structured technical documentation.

Documentation should answer:

```text
What was studied?

What problem was analysed?

Which strategy was selected?

Why should the algorithm work?

What is its computational cost?

How was the implementation tested?

What was observed?

What conclusions were reached?
```

The learner should distinguish between:

```text
Code comments
      ↓
Clarify local implementation details.

Technical documentation
      ↓
Preserves broader reasoning and conclusions.
```

## Expected competency

The learner should produce documentation that allows another technical reader to understand the reasoning behind the implementation without requiring a line-by-line reconstruction of the source code.

## Evidence of learning

Evidence may include:

* academic study documents;
* algorithm analysis documents;
* architecture documentation;
* benchmark reports;
* technical conclusions.

---

# 25. Expected Competency Matrix

| Competency Area             | Understand | Apply | Analyse | Justify | Evaluate |
| --------------------------- | :--------: | :---: | :-----: | :-----: | :------: |
| Problem specification       |      ✓     |   ✓   |    ✓    |    ✓    |          |
| Algorithm design            |      ✓     |   ✓   |    ✓    |    ✓    |     ✓    |
| Pseudocode                  |      ✓     |   ✓   |    ✓    |         |          |
| Manual tracing              |      ✓     |   ✓   |    ✓    |         |          |
| Correctness                 |      ✓     |   ✓   |    ✓    |    ✓    |          |
| Complexity                  |      ✓     |   ✓   |    ✓    |    ✓    |     ✓    |
| Data structures             |      ✓     |   ✓   |    ✓    |    ✓    |     ✓    |
| Java implementation         |      ✓     |   ✓   |    ✓    |         |     ✓    |
| Automated testing           |      ✓     |   ✓   |    ✓    |    ✓    |     ✓    |
| Benchmarking                |      ✓     |   ✓   |    ✓    |    ✓    |     ✓    |
| Java Collections comparison |      ✓     |   ✓   |    ✓    |    ✓    |     ✓    |
| Linux validation            |      ✓     |   ✓   |    ✓    |         |          |
| Continuous integration      |      ✓     |   ✓   |    ✓    |    ✓    |          |
| Technical documentation     |      ✓     |   ✓   |    ✓    |    ✓    |     ✓    |

The matrix represents the intended depth of study rather than a numerical grading system.

---

# 26. Completion Criteria

The project should not be considered complete solely because every planned directory contains source code.

Completion requires evidence that the learning objectives have been addressed.

The learner should be able to select a studied algorithm and explain:

1. the computational problem;
2. the input and output;
3. the preconditions and postconditions;
4. the relevant edge cases;
5. the algorithmic strategy;
6. the pseudocode;
7. the execution process;
8. the correctness reasoning;
9. the time complexity;
10. the auxiliary space complexity;
11. the Java implementation decisions;
12. the automated test strategy;
13. the relationship with Java standard abstractions when applicable;
14. the experimental results when benchmarking is relevant;
15. the principal technical conclusions.

The expected final capability can be summarised as:

```text
I can implement the algorithm.
            ↓
I can explain the algorithm.
            ↓
I can justify the algorithm.
            ↓
I can analyse the algorithm.
            ↓
I can test the implementation.
            ↓
I can evaluate its behaviour.
            ↓
I can communicate my conclusions.
```

---

# 27. Final Learning Outcome

At the conclusion of **Algorithms Java Mastery**, the learner should demonstrate the ability to approach algorithmic problems through a disciplined and repeatable analytical process.

The expected professional behaviour is:

```text
Do not begin with code.

Understand the problem.

Define the contract.

Identify the constraints.

Analyse the edge cases.

Design the strategy.

Express the algorithm.

Reason about correctness.

Analyse computational cost.

Implement clearly in Java.

Test expected behaviour.

Measure performance when relevant.

Interpret the evidence.

Document the conclusions.
```

The final objective is not algorithm memorisation.

> **The final objective is the development of algorithmic reasoning as a transferable software engineering competency.**
