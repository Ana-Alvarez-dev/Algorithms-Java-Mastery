# Study Methodology

## Algorithms Java Mastery

This document defines the systematic study methodology applied throughout **Algorithms Java Mastery**.

The methodology establishes a repeatable analytical process for studying algorithms and data structures from problem definition to documented technical conclusions.

The project follows a reasoning-first principle:

> **An algorithm should be understood and specified before it is implemented.**

Source code is treated as the concrete representation of a previously analysed computational process.

The general study sequence is:

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

Not every algorithm requires the same analytical depth.

However, the order of reasoning should remain consistent.

---

# 1. Purpose of the Methodology

The purpose of this methodology is to prevent algorithm study from becoming a process of code memorisation.

A superficial workflow may follow:

```text
Find implementation
        ↓
Copy implementation
        ↓
Execute code
        ↓
Observe expected result
```

This process may demonstrate that a specific implementation executes successfully.

It does not necessarily demonstrate algorithmic understanding.

The methodology used in this project instead follows:

```text
Understand
    ↓
Specify
    ↓
Analyse
    ↓
Design
    ↓
Justify
    ↓
Implement
    ↓
Verify
    ↓
Evaluate
```

The learner should progressively become capable of approaching an unfamiliar problem without beginning with Java syntax.

The methodology therefore develops a repeatable problem-solving discipline.

---

# 2. Core Study Principle

Every algorithm studied in the project should answer four fundamental questions.

```text
WHAT?
What computational problem is being solved?

WHY?
Why does the proposed strategy work?

HOW MUCH?
What computational resources are required?

HOW DO WE KNOW?
How is the implementation verified and evaluated?
```

These questions correspond to four analytical dimensions.

| Dimension     | Central Concern                  |
| ------------- | -------------------------------- |
| Specification | Definition of expected behaviour |
| Correctness   | Justification of the algorithm   |
| Complexity    | Analysis of computational cost   |
| Verification  | Evaluation of the implementation |

Java implementation connects these dimensions but does not replace them.

---

# 3. Study Workflow

The methodology is organised into twelve analytical stages.

```text
01. Understand the problem
02. Define the specification
03. Analyse edge cases
04. Design the strategy
05. Write pseudocode
06. Perform a manual trace
07. Reason about correctness
08. Analyse complexity
09. Implement in Java
10. Design automated tests
11. Perform experimental analysis
12. Document conclusions
```

Each stage produces information required by subsequent stages.

The learner should avoid skipping directly from the problem statement to Java implementation.

---

# 4. Stage 1 — Understand the Problem

## Objective

Determine precisely what computational problem must be solved.

The first task is not to select a loop, collection, class, or Java API.

The first task is to understand the required transformation.

A computational problem can be considered as a relationship between valid inputs and expected outputs.

```text
INPUT
   ↓
Computational Process
   ↓
OUTPUT
```

For example:

> Find the largest value in an array.

Before designing an algorithm, the learner should ask:

* What data is provided?
* What result is expected?
* Is the result a value, index, collection, or boolean condition?
* Can the input be empty?
* Can values be repeated?
* Can values be negative?
* Is the input ordered?
* Does the original data need to remain unchanged?

## Study questions

The analysis should answer:

```text
What is given?

What must be obtained?

What information is relevant?

What assumptions are explicit?

What assumptions am I introducing?

What is not part of the problem?
```

## Expected output

A concise problem definition.

Example:

```text
Problem:

Given a non-empty array of integers,
determine the greatest value contained in the array.
```

The problem definition should describe the computational objective without explaining the implementation.

---

# 5. Stage 2 — Define the Formal Specification

## Objective

Define the algorithm contract.

The specification establishes the relationship between input conditions and expected results.

The basic structure is:

```text
Input
Input Size
Preconditions
Output
Postconditions
```

---

## 5.1 Input

The input identifies the data received by the algorithm.

Example:

```text
Input:

An array A containing integer values.
```

The input definition should identify the relevant data type conceptually.

At this stage, Java-specific declarations are not required.

Avoid:

```text
int[] array
```

Prefer:

```text
An array A of integer values.
```

Java types are introduced during implementation.

---

## 5.2 Input Size

The relevant input size must be identified before complexity analysis.

Example:

```text
n = number of elements in A
```

The learner should explicitly ask:

> What quantity grows when the problem becomes larger?

Depending on the problem, input size may represent:

```text
Array
n = number of elements

Matrix
r = number of rows
c = number of columns

Graph
V = number of vertices
E = number of edges

String
n = number of characters
```

The symbol `n` should not be used automatically.

Its meaning must be defined.

---

## 5.3 Preconditions

A precondition is a property that must hold before algorithm execution.

Example:

```text
A is not null.

A contains at least one element.
```

If the algorithm is binary search:

```text
A is sorted according to the selected ordering.
```

Preconditions define the valid input domain.

The learner should distinguish between:

```text
Algorithm precondition
        ↓
Condition required by the algorithm.

Implementation validation
        ↓
Code responsible for detecting invalid input.
```

An algorithm may define a precondition without necessarily specifying how an application should handle its violation.

That decision belongs to the Java API design stage.

---

## 5.4 Output

The output identifies the result produced by the algorithm.

Example:

```text
Output:

The greatest value contained in A.
```

The output should be unambiguous.

Avoid:

```text
The maximum.
```

Prefer:

```text
An integer value representing the greatest element contained in A.
```

---

## 5.5 Postconditions

A postcondition defines what must be true after successful algorithm termination.

For the maximum-element problem:

```text
The returned value m belongs to A.

For every element x in A:

x ≤ m
```

The relationship between specification elements is:

```text
Preconditions
      ↓
Algorithm Execution
      ↓
Postconditions
```

The correctness analysis will later explain why the algorithm establishes the postcondition.

---

# 6. Stage 3 — Analyse Edge Cases

## Objective

Identify inputs that expose assumptions, boundaries, or structurally relevant behaviour.

An edge case is not simply a random unusual value.

It is an input that tests an important boundary or property of the problem.

For array problems, relevant cases may include:

```text
Single element

Two elements

Repeated values

All values equal

Negative values

Already ordered input

Reverse-ordered input

Result at the first position

Result at the last position
```

Invalid inputs may also be analysed when relevant:

```text
Null reference

Empty array
```

## Edge-case table

The project should use tables when they improve analysis.

Example:

| Case             | Input           | Relevance                              |
| ---------------- | --------------- | -------------------------------------- |
| Single element   | `[7]`           | Minimum valid input                    |
| Maximum first    | `[15, 4, 2]`    | Candidate never changes                |
| Maximum last     | `[2, 4, 15]`    | Candidate changes at final comparison  |
| Repeated maximum | `[15, 2, 15]`   | Maximum is not unique                  |
| Negative values  | `[-7, -2, -15]` | Prevents incorrect zero initialisation |
| All equal        | `[5, 5, 5]`     | Equal comparisons                      |
| Empty            | `[]`            | Violates non-empty precondition        |

## Study question

For every edge case, ask:

> Which assumption or algorithmic behaviour does this case examine?

If this question cannot be answered, the case may not be analytically relevant.

---

# 7. Stage 4 — Design the Algorithmic Strategy

## Objective

Describe the central solution idea without Java syntax.

The strategy should explain how information is processed and maintained.

For the maximum-element problem:

```text
Use the first element as the initial maximum candidate.

Process each remaining element from left to right.

Compare the current element with the current maximum candidate.

If the current element is greater,
replace the candidate.

After every element has been processed,
return the current candidate.
```

The strategy should answer:

```text
What information is maintained?

How is it initialised?

What data is processed?

What decision is repeated?

How does the state change?

When does processing terminate?

Why should the final state contain the answer?
```

## State identification

Many algorithms can be understood by identifying the information that represents their current progress.

Examples:

```text
Maximum search
    ↓
Current maximum candidate

Binary search
    ↓
Current candidate interval

Insertion sort
    ↓
Sorted prefix

Breadth-first search
    ↓
Discovered vertices and pending queue

Dynamic programming
    ↓
Previously computed states
```

A central study question is:

> **What must the algorithm remember after processing part of the input?**

This question frequently reveals the essential algorithmic state.

---

# 8. Stage 5 — Write Pseudocode

## Objective

Translate the strategy into a precise algorithmic procedure.

Pseudocode should describe:

* initialisation;
* control flow;
* significant operations;
* state updates;
* termination;
* returned result.

Example:

```text
MAXIMUM(A)

    max ← A[0]

    for i ← 1 to A.length - 1

        if A[i] > max
            max ← A[i]

    return max
```

## Pseudocode principles

Pseudocode should be:

```text
Precise
Readable
Language-independent
Consistent
Minimal
```

Pseudocode should not contain unnecessary Java details.

Avoid:

```text
public static int findMaximum(int[] array)
```

Prefer:

```text
MAXIMUM(A)
```

Avoid implementation-specific exception syntax unless exception behaviour is the concept being studied.

## Pseudocode review

Before implementation, ask:

```text
Can I execute this algorithm manually?

Is every variable conceptually defined?

Is the initial state valid?

Is the repeated operation clear?

Is the termination condition clear?

Is the returned result explicit?
```

If the pseudocode cannot be traced manually, it should be clarified before implementation.

---

# 9. Stage 6 — Perform a Manual Execution Trace

## Objective

Execute the algorithm manually and record the evolution of its relevant state.

Consider:

```text
A = [7, 2, 15, 4, 9]
```

Initial state:

```text
max = 7
```

Execution trace:

|    Step | Index | Current Element | Current Maximum | Comparison | New Maximum |
| ------: | ----: | --------------: | --------------: | ---------- | ----------: |
| Initial |     0 |               7 |               — | —          |           7 |
|       1 |     1 |               2 |               7 | `2 > 7`    |           7 |
|       2 |     2 |              15 |               7 | `15 > 7`   |          15 |
|       3 |     3 |               4 |              15 | `4 > 15`   |          15 |
|       4 |     4 |               9 |              15 | `9 > 15`   |          15 |

Final result:

```text
15
```

## Purpose of manual tracing

Manual tracing helps identify:

* incorrect initialisation;
* incorrect loop boundaries;
* missing state updates;
* invalid assumptions;
* incorrect termination conditions.

It also prepares the correctness analysis.

The trace shows what occurs for a concrete input.

It does not prove general correctness.

```text
Manual trace
      ↓
Concrete execution evidence

Correctness reasoning
      ↓
General algorithmic justification
```

This distinction must be maintained throughout the project.

---

# 10. Stage 7 — Reason About Correctness

## Objective

Explain why the algorithm satisfies its specification for every valid input.

The project uses correctness reasoning at a level appropriate to the algorithm.

For iterative algorithms, loop invariants are a central analytical tool.

---

## 10.1 Identify the invariant

For the maximum-element algorithm, a suitable invariant is:

> At the beginning of each iteration, `max` contains the greatest value among the elements already processed.

Conceptually:

```text
Processed region
        ↓
The maximum of this region is known.
```

Example:

```text
[7, 2, 15 | 4, 9]
 └────────┘
   processed

max = 15
```

The unprocessed elements do not need to be reconsidered when determining the maximum of the processed region.

Their relevant information has been summarised in `max`.

---

## 10.2 Initialisation

Ask:

> Is the invariant true before the first iteration?

Initially:

```text
max ← A[0]
```

The processed region contains only the first element.

The maximum of a one-element region is that element itself.

Therefore, the invariant is initially true.

---

## 10.3 Maintenance

Ask:

> If the invariant is true before an iteration, does the iteration preserve it?

Suppose `max` is the maximum of all previously processed elements.

The next element is compared with `max`.

Two cases exist.

```text
A[i] > max
```

The current element becomes the new maximum.

Or:

```text
A[i] ≤ max
```

The previous maximum remains valid.

After the comparison, `max` represents the greatest value among all elements processed so far.

The invariant is preserved.

---

## 10.4 Termination

Ask:

> When the loop terminates, what does the invariant imply?

At termination, every element in the array has been processed.

The invariant states that `max` contains the greatest value among all processed elements.

Since all elements have been processed:

```text
max = maximum value in A
```

The postcondition is satisfied.

---

## Correctness template

For applicable iterative algorithms, use:

```text
Invariant:
What property remains true?

Initialisation:
Why is it true before iteration begins?

Maintenance:
Why does one iteration preserve it?

Termination:
Why does the invariant imply the expected result?
```

Not every algorithm requires a loop invariant.

Recursive algorithms may require inductive reasoning.

Dynamic programming may require reasoning about state definitions and recurrence relations.

The reasoning method should correspond to the algorithm structure.

---

# 11. Stage 8 — Analyse Computational Complexity

## Objective

Determine how computational resource usage grows with input size.

The analysis begins with the input-size definition established during specification.

Example:

```text
n = number of elements in A
```

---

## 11.1 Identify the significant operation

For the maximum-element algorithm, the significant repeated operation is:

```text
A[i] > max
```

The algorithm compares every remaining element with the current candidate.

For an array containing `n` elements:

```text
Number of comparisons = n - 1
```

The operation count can be represented as:

```text
T(n) = n - 1
```

Asymptotically:

```text
T(n) ∈ Θ(n)
```

---

## 11.2 Time complexity

The learner should analyse whether the amount of work changes according to input arrangement.

For maximum search:

```text
Best case     Θ(n)
Average case  Θ(n)
Worst case    Θ(n)
```

Even if the first element is already the maximum, the remaining elements must still be examined.

The algorithm cannot conclude that the first value is globally maximal without processing the remaining input.

---

## 11.3 Auxiliary space complexity

The algorithm maintains a constant amount of additional state.

Conceptually:

```text
max
i
```

The number of auxiliary variables does not grow with `n`.

Therefore:

```text
Auxiliary space = Θ(1)
```

---

## Complexity analysis template

Document:

```text
Input size:

Significant operation:

Operation count:

Best case:

Average case:

Worst case:

Auxiliary space:

Justification:
```

Complexity labels without justification are insufficient for project documentation.

Avoid:

```text
Complexity: O(n)
```

Prefer:

```text
The algorithm performs one comparison for each element
after the initial candidate.

For n elements, it performs n - 1 comparisons.

Therefore, the running time grows linearly with n:

Θ(n).
```

---

# 12. Stage 9 — Implement the Algorithm in Java

## Objective

Translate the previously analysed algorithm into clear Java code.

Implementation occurs only after:

```text
Problem specification
        ✓

Strategy
        ✓

Pseudocode
        ✓

Manual trace
        ✓

Correctness reasoning
        ✓

Complexity analysis
        ✓
```

The Java implementation should preserve the algorithmic structure.

Example conceptual correspondence:

| Pseudocode     | Java Responsibility      |
| -------------- | ------------------------ |
| `MAXIMUM(A)`   | Method definition        |
| `max ← A[0]`   | Candidate initialisation |
| `for i ← 1...` | Array traversal          |
| `A[i] > max`   | Comparison               |
| `max ← A[i]`   | State update             |
| `return max`   | Result                   |

## Implementation questions

Before considering the implementation complete, ask:

```text
Does the method signature represent the algorithm clearly?

Are preconditions explicit?

Does the implementation preserve the pseudocode strategy?

Are variable names meaningful?

Is unnecessary abstraction hiding the algorithm?

Is mutation required?

Are Java-specific decisions documented when relevant?
```

## Implementation discipline

The project prioritises clarity over artificial abstraction.

A simple algorithm does not require:

```text
Service
Controller
Repository
Factory
Strategy Bean
Dependency Injection
```

unless the studied concept explicitly requires such abstractions.

The implementation structure should serve the algorithmic learning objective.

---

# 13. Stage 10 — Design Automated Tests

## Objective

Verify that the Java implementation satisfies expected behaviour.

Tests should be derived from the specification and edge-case analysis.

The process is:

```text
Specification
      ↓
Expected Properties
      ↓
Relevant Cases
      ↓
JUnit Tests
```

For the maximum-element algorithm, tests may represent:

```text
Single-element array

Maximum at first position

Maximum at middle position

Maximum at last position

Repeated maximum

All equal values

Negative values
```

If the Java API defines behaviour for invalid input:

```text
Empty array

Null reference
```

should also be tested.

## Test design questions

For each test, ask:

```text
What behaviour is being verified?

Which specification property supports this test?

Is this case structurally different from existing tests?

Would an incorrect implementation potentially fail this test?
```

## Naming tests

Test names should communicate behaviour.

Prefer names such as:

```text
shouldReturnMaximumWhenLargestValueIsAtLastPosition

shouldReturnMaximumWhenAllValuesAreNegative

shouldReturnSingleValueWhenArrayContainsOneElement
```

Avoid names that reveal only internal implementation details.

## Testing tools

The project uses:

```text
JUnit Jupiter
        ↓
Test structure and execution

AssertJ
        ↓
Readable assertions
```

Automated tests verify concrete implementation behaviour.

They do not replace correctness reasoning.

---

# 14. Stage 11 — Perform Experimental Evaluation

## Objective

Observe implementation behaviour under controlled execution when performance analysis is relevant.

Not every algorithm requires a benchmark.

Benchmarking should be introduced only when there is a meaningful experimental question.

Examples:

```text
How does linear search behaviour change as input size grows?

How do two sorting implementations compare across data sizes?

How does input ordering affect an algorithm?

Does the observed growth correspond with theoretical expectations?
```

## Experimental hypothesis

Before creating a benchmark, define a question or hypothesis.

Example:

```text
Hypothesis:

As array size increases,
the execution cost of maximum search should exhibit
approximately linear growth.
```

Avoid:

```text
I will benchmark this algorithm to see if it is fast.
```

The second statement does not define a measurable analytical objective.

## JMH

Java Microbenchmark Harness is used when controlled Java microbenchmarking is appropriate.

The learner should consider:

* warmup;
* measurement iterations;
* forks;
* benchmark state;
* input generation;
* JVM optimisation;
* dead-code elimination;
* result interpretation.

## Relationship with complexity

Benchmark results do not redefine asymptotic complexity.

```text
Theoretical analysis
        ↓
Predicts growth behaviour.

Benchmark
        ↓
Observes a concrete implementation
under defined conditions.
```

An experimental result should be interpreted in relation to the theoretical analysis.

---

# 15. Stage 12 — Document Technical Conclusions

## Objective

Consolidate the principal findings obtained during the study.

A conclusion should not simply state:

```text
The algorithm works correctly.
```

The conclusion should summarise what was established.

Example:

```text
The maximum-element algorithm maintains the greatest value
observed in the processed prefix of the array.

The loop invariant explains why previously processed elements
do not need to be examined again: their relevant information
is represented by the current maximum candidate.

The algorithm performs n - 1 comparisons for an input of size n,
resulting in Θ(n) time complexity and Θ(1) auxiliary space.

Automated tests verify representative and boundary behaviours
of the Java implementation.
```

## Conclusion questions

Document:

```text
What was learned?

Why does the algorithm work?

What is its computational cost?

Which implementation decisions were relevant?

Which edge cases were significant?

What did testing verify?

What did experimentation show?

What limitations remain?

How does the algorithm relate to Java development?
```

A technical conclusion should demonstrate synthesis rather than repetition.

---

# 16. Standard Algorithm Study Template

Every major algorithm document should use the following structure when applicable.

```text
# Algorithm Name

## 1. Problem Definition

## 2. Input

## 3. Input Size

## 4. Preconditions

## 5. Output

## 6. Postconditions

## 7. Edge Cases

## 8. Algorithmic Strategy

## 9. Pseudocode

## 10. Manual Execution Trace

## 11. Correctness Reasoning

### 11.1 Invariant
### 11.2 Initialisation
### 11.3 Maintenance
### 11.4 Termination

## 12. Complexity Analysis

### 12.1 Significant Operation
### 12.2 Operation Count
### 12.3 Time Complexity
### 12.4 Auxiliary Space Complexity

## 13. Java Implementation Considerations

## 14. Automated Test Strategy

## 15. Experimental Analysis

## 16. Java Standard Library Comparison

## 17. Technical Conclusions
```

Sections that do not apply should not be artificially completed.

For example, a simple algorithm may not require experimental analysis.

A custom data structure may require a deeper Java Collections Framework comparison.

A recursive algorithm may replace loop-invariant analysis with an inductive correctness argument.

The template is a methodological guide rather than a rigid form.

---

# 17. Methodology Adaptation by Algorithm Type

The analytical emphasis changes according to the algorithm structure.

| Algorithm Type      | Primary Analytical Focus                |
| ------------------- | --------------------------------------- |
| Iterative           | Loop invariant and state evolution      |
| Recursive           | Base case, recursive case, termination  |
| Divide and conquer  | Divide, conquer, combine, recurrence    |
| Searching           | Candidate search space                  |
| Sorting             | Ordering invariant and data movement    |
| Hashing             | Distribution and collision behaviour    |
| Tree algorithms     | Structural relationships and traversal  |
| Graph algorithms    | Visited state and exploration frontier  |
| Backtracking        | Decision state and state restoration    |
| Dynamic programming | State, recurrence, and dependency order |

The general methodology remains stable.

The correctness and analysis tools adapt to the computational structure.

---

# 18. Study Notebook and Repository Evidence

The repository should preserve relevant evidence of the analytical process.

Not every informal thought must be committed.

The project should preserve information that contributes to technical understanding.

## `docs/`

Contains structured and reviewed academic documentation.

Examples:

```text
problem-solving-method.md

binary-search-analysis.md

merge-sort-correctness.md

dynamic-programming-state-design.md
```

## `notes/`

Contains complementary study notes.

Examples:

```text
Questions generated during study

Concept comparisons

Terminology clarification

Preliminary reasoning
```

## `exercises/`

Contains practical problem-solving activities.

Exercises are organised by difficulty:

```text
basic/
intermediate/
advanced/
interview/
```

## `src/main/java/`

Contains Java implementations.

## `src/test/java/`

Contains automated tests.

## `benchmarks/`

Contains performance experiments when applicable.

The same study process may therefore produce several related artefacts.

```text
Study Topic
    │
    ├── Academic documentation
    ├── Exercise
    ├── Java implementation
    ├── Automated tests
    └── Benchmark
```

These artefacts have different responsibilities and should not duplicate one another unnecessarily.

---

# 19. Source and Reference Methodology

The project distinguishes between theoretical sources and technical documentation.

## Academic references

Academic sources are used for:

* algorithm definitions;
* correctness reasoning;
* asymptotic analysis;
* algorithm design techniques;
* data structure theory.

The principal theoretical reference is:

**Cormen, T. H., Leiserson, C. E., Rivest, R. L., and Stein, C. — *Introduction to Algorithms*.**

Additional recognised academic references may be incorporated when they improve explanation or provide an alternative perspective.

## Official technical documentation

Official documentation is prioritised for:

* Java language and APIs;
* Java Collections Framework;
* Maven;
* JUnit;
* AssertJ;
* JMH;
* GitHub Actions;
* Linux tooling.

The project should avoid treating informal tutorials as primary authority when an academic or official technical source directly addresses the topic.

## Source integration

Documentation should not reproduce reference material mechanically.

The expected process is:

```text
Study source
      ↓
Understand concept
      ↓
Analyse the problem
      ↓
Develop original explanation
      ↓
Apply concept
      ↓
Document conclusions
```

References support reasoning.

They do not replace it.

---

# 20. Review Questions Before Completing a Topic

Before marking an algorithmic topic as studied, the learner should be able to answer:

1. What computational problem does the algorithm solve?
2. What is the input?
3. What represents the input size?
4. What is the expected output?
5. Which preconditions must hold?
6. What postcondition is guaranteed?
7. Which edge cases are relevant?
8. What is the central algorithmic strategy?
9. What information does the algorithm maintain?
10. Can I express the solution in pseudocode?
11. Can I execute the algorithm manually?
12. Why does the algorithm work?
13. Which correctness argument is appropriate?
14. What is the significant operation?
15. How does the operation count grow?
16. What is the time complexity?
17. What is the auxiliary space complexity?
18. Does the Java implementation preserve the algorithmic strategy?
19. Which behaviours must be tested?
20. Is benchmarking relevant?
21. Is there a related Java standard abstraction?
22. What technical conclusions can I explain without reading the source code?

If the learner can only explain the Java implementation line by line, the algorithmic study is incomplete.

---

# 21. Definition of Study Completion

An algorithm is not considered studied because:

```text
The code compiles.

The program executes.

The expected result appears.

The tests are green.
```

These are important implementation milestones.

They are not sufficient learning criteria.

Within **Algorithms Java Mastery**, an algorithm is considered adequately studied when the learner can:

```text
Define the problem
        ↓
Specify the contract
        ↓
Identify relevant edge cases
        ↓
Explain the strategy
        ↓
Write the pseudocode
        ↓
Trace the execution
        ↓
Justify correctness
        ↓
Analyse computational cost
        ↓
Implement in Java
        ↓
Design meaningful tests
        ↓
Evaluate performance when relevant
        ↓
Explain technical conclusions
```

The final criterion is explanatory independence.

> **The learner should be capable of explaining the algorithm without depending on the source code as a script.**

---

# 22. Final Methodological Principle

The study methodology of **Algorithms Java Mastery** is based on a disciplined progression from computational problem to technical evidence.

```text
Do not begin with syntax.

Begin with the problem.

Define what must be true.

Identify what information matters.

Design the strategy.

Express the algorithm.

Observe its state.

Explain why it works.

Determine its computational cost.

Implement it clearly.

Test its behaviour.

Measure only with a defined question.

Interpret the evidence.

Document what was learned.
```

The methodology can be summarised through the following principle:

> **Understand before implementing. Specify before testing. Justify before trusting. Analyse before optimising. Measure before concluding.**
