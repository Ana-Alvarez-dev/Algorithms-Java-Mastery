# Invariants

## Algorithms Java Mastery

This document introduces the concept of **algorithmic invariants**, one of the
most important tools used to reason about algorithm correctness.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
systematic study of algorithms throughout the repository.

An invariant is a property that remains true at specific, well-defined points
during the execution of an algorithm or throughout the valid state of a data
structure.

Unlike individual instructions, an invariant describes a relationship that is
preserved while the algorithm changes its internal state.

Invariants therefore provide a stable logical reference for explaining why an
algorithm continues to satisfy important properties throughout execution.

The central question addressed throughout this document is:

> **Which property remains true while the algorithm progresses toward its
> result?**

---

# Purpose

The purpose of this document is to introduce invariants as a systematic
reasoning technique for analysing algorithm behaviour and supporting correctness
arguments.

Algorithms frequently modify variables, collections, indices, subproblems, or
data-structure state during execution.

To reason about those changes, the learner needs properties that remain stable
throughout the relevant stages of the computation.

The conceptual progression is:

```text
Problem Specification
        ↓
Preconditions and Postconditions
        ↓
Algorithm Design
        ↓
Invariant Identification
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Implementation
```

An invariant does not describe every execution step.

Instead, it describes a property that remains true while those steps transform
the algorithm state.

---

# Learning Objectives

After studying this document, the learner should be able to:

- define an algorithmic invariant;
- explain why invariants support correctness reasoning;
- distinguish an invariant from an individual algorithmic step;
- identify loop invariants in iterative algorithms;
- recognise structural invariants in data structures;
- understand how recursive reasoning may preserve equivalent logical
  properties across subproblems;
- explain the roles of initialisation, maintenance, and termination;
- relate invariants to preconditions and postconditions;
- use invariants to strengthen correctness arguments;
- understand how invariants influence later automated testing.

These concepts become essential throughout the study of algorithms and data
structures.

---

# What Is an Invariant?

An **invariant** is a logical property that remains true at specified points
throughout an algorithmic process or throughout the valid lifetime of a
structure.

Although internal state may change, the relationship described by the invariant
continues to hold.

Conceptually:

```text
State Changes
        ↓
Invariant Preserved
        ↓
State Changes
        ↓
Invariant Preserved
```

The exact points at which the invariant must hold depend on the algorithm.

For a loop, the invariant is commonly formulated as a property that holds before
or after every iteration.

For a data structure, an invariant may describe a structural property that must
remain true after every valid operation.

---

# Why Invariants Matter

Algorithms often evolve through a sequence of changing intermediate states.

Without a stable logical property, correctness reasoning may become a collection
of unrelated observations about individual instructions.

An invariant provides continuity.

```text
Changing State
        ↓
Preserved Property
        ↓
Changing State
        ↓
Preserved Property
```

This allows the learner to reason about the algorithm at a higher level.

Instead of asking only:

> What does this instruction do?

the learner can ask:

> **Which important property is still guaranteed after this step?**

That question is central to many correctness arguments.

---

# Invariants and Algorithm State

An invariant does not require variables to remain unchanged.

Instead, it expresses a relationship among values or structures that remains
valid while those values evolve.

For example:

```text
maximum
```

may change repeatedly.

But the property:

> `maximum` contains the largest value among all elements processed so far.

may remain true throughout execution.

Conceptually:

```text
Variable Changes
        ↓
Logical Relationship Remains True
```

This distinction is fundamental.

The invariant concerns the **meaning of the state**, not the immutability of the
state itself.

---

# Loop Invariants

Loop invariants are among the most common invariants used in algorithm analysis.

A **loop invariant** is a property that holds at a specified point before or
after each iteration of a loop.

A typical correctness argument follows:

```text
Before First Iteration
        ↓
Invariant Holds
        ↓
Iteration Executes
        ↓
Invariant Preserved
        ↓
...
        ↓
Loop Terminates
        ↓
Invariant Helps Establish Postcondition
```

Loop invariants provide a systematic way to explain why repeated operations
preserve correctness.

---

# The Three-Part Invariant Method

A loop invariant is commonly used through three complementary stages:

1. **Initialisation**
2. **Maintenance**
3. **Termination**

These stages connect the invariant to a complete correctness argument.

---

## Initialisation

Initialisation establishes that the invariant is true before the first relevant
iteration.

Conceptually:

```text
Precondition
        ↓
Initial State
        ↓
Invariant Holds
```

If the invariant is false before the loop begins, it cannot support the
subsequent reasoning.

---

## Maintenance

Maintenance establishes that one iteration preserves the invariant.

The reasoning takes the form:

```text
Assume Invariant Holds
        ↓
Execute One Iteration
        ↓
Invariant Still Holds
```

This step demonstrates that algorithmic progress does not destroy the property
required for correctness.

---

## Termination

Termination connects the invariant with the final required result.

Conceptually:

```text
Invariant Holds
        +
Loop Termination Condition
        ↓
Postcondition
```

The invariant alone is usually not enough.

The correctness argument uses both:

- the invariant;
- the condition under which the loop terminates.

Together, they establish the required postcondition.

---

# Example — Finding the Maximum Value

Consider the problem:

> Determine the largest value contained in a finite non-empty integer array.

A possible algorithmic strategy is:

1. initialise the current maximum with the first element;
2. examine each remaining element;
3. replace the current maximum when a larger element is encountered;
4. return the current maximum after all elements have been processed.

A useful invariant is:

> **At the beginning of each iteration, `maximum` contains the largest value
> among the elements already processed.**

Consider:

```text
[7, 2, 15, 4, 9]
```

The state evolves as follows:

```text
Processed: [7]
maximum = 7
Invariant holds.

Processed: [7, 2]
maximum = 7
Invariant holds.

Processed: [7, 2, 15]
maximum = 15
Invariant holds.

Processed: [7, 2, 15, 4]
maximum = 15
Invariant holds.

Processed: [7, 2, 15, 4, 9]
maximum = 15
Invariant holds.
```

The variable changes.

The invariant does not.

---

# Initialisation in the Maximum Example

Before processing the remaining elements:

```text
maximum = first array element
```

The processed portion contains exactly one element.

Therefore:

```text
maximum
```

is necessarily the largest value among the elements processed so far.

The invariant is established.

---

# Maintenance in the Maximum Example

Assume the invariant holds before processing the next element.

There are two possibilities.

## Next Element Is Not Greater

Then:

```text
maximum
```

already remains the largest value among the processed elements.

The invariant continues to hold.

## Next Element Is Greater

The algorithm updates:

```text
maximum = next element
```

The new value is now the largest among all elements processed so far.

The invariant again continues to hold.

Therefore, one iteration preserves the invariant.

---

# Termination in the Maximum Example

The loop terminates after every element has been processed.

At that point, the invariant states:

> `maximum` is the largest value among all processed elements.

Since all elements have now been processed:

```text
Processed Elements
        =
Entire Input Array
```

Therefore:

> `maximum` is the largest value contained in the entire array.

The invariant and termination condition together establish the required
postcondition.

---

# Invariants and Preconditions

Preconditions establish the state from which invariant reasoning begins.

Conceptually:

```text
Precondition
        ↓
Initial State
        ↓
Invariant Initialisation
```

For the maximum-value example:

```text
Precondition:
The input array contains at least one element.
```

This condition allows the algorithm to initialise the current maximum using the
first element.

Without this precondition, the initialisation strategy would not be valid.

---

# Invariants and Postconditions

Invariants support the transition from intermediate algorithm state to final
correctness.

Conceptually:

```text
Invariant
        +
Termination Condition
        ↓
Postcondition
```

The invariant describes what remains true while the algorithm executes.

The postcondition describes what must be true once the algorithm has finished.

They therefore serve different but complementary purposes.

---

# Invariants and Correctness

Invariants are one of the principal tools used in correctness reasoning.

However:

> **An invariant by itself does not prove correctness.**

A complete invariant-based argument must explain:

```text
Initialisation
        ↓
Why the invariant is initially true

Maintenance
        ↓
Why every relevant step preserves it

Termination
        ↓
Why the invariant and stopping condition imply the postcondition
```

This structure provides a systematic argument for partial correctness.

Termination itself must also be established when reasoning about total
correctness.

Correctness is developed in:

```text
05-correctness.md
```

---

# Invariants and Termination

Invariant preservation and termination are separate responsibilities.

An invariant may remain true forever inside an infinite loop.

Therefore:

```text
Invariant Preserved
```

does not imply:

```text
Algorithm Terminates
```

For total correctness, the learner must establish both:

```text
Correctness Property
        +
Progress Toward Termination
        ↓
Total Correctness
```

This distinction prevents invariant reasoning from being mistaken for a complete
termination argument.

---

# Structural Invariants

Invariants are not limited to loops.

Data structures frequently maintain **structural invariants**.

A structural invariant describes a property that must remain true after every
valid operation on the structure.

Examples may later include:

```text
Binary Search Tree
        ↓
Values in left subtree satisfy ordering relation
Values in right subtree satisfy ordering relation
```

or:

```text
Heap
        ↓
Parent-child heap property remains satisfied
```

or:

```text
Balanced Tree
        ↓
Required balance conditions remain valid
```

Operations such as insertion or deletion may change the structure while still
preserving its invariant.

---

# Invariants in Recursive Reasoning

Recursive algorithms are often reasoned about differently from loops.

Instead of requiring a loop invariant, recursive correctness arguments commonly
use:

- base cases;
- recursive hypotheses;
- smaller subproblems;
- induction.

Conceptually:

```text
Problem Instance
        ↓
Smaller Instance
        ↓
Recursive Assumption
        ↓
Combine Result
        ↓
Required Postcondition
```

A preserved logical property may still exist across recursive calls, but the
reasoning is often more naturally expressed through induction rather than by
forcing the concept into a traditional loop-invariant structure.

This distinction becomes important in later recursion and divide-and-conquer
modules.

---

# Loop Invariants vs Structural Invariants

The term **invariant** can therefore refer to different forms of preserved
properties.

| Type | Typical Context | Meaning |
|---|---|---|
| Loop invariant | Iterative algorithms | Property preserved across iterations |
| Structural invariant | Data structures | Property preserved after valid operations |
| Algorithmic invariant | General reasoning | Property preserved during relevant states |
| Recursive property | Recursive algorithms | Property maintained through inductive reasoning |

The exact reasoning technique should match the computational problem.

---

# Invariants and Edge Cases

An invariant intended to support correctness must remain valid across every
valid problem instance covered by the specification.

This includes valid edge cases.

For example, consider the maximum-value algorithm with:

```text
[8]
```

The array contains only one element.

The invariant is already established during initialisation.

No remaining iteration is required.

At termination:

```text
maximum = 8
```

and the required postcondition holds.

Boundary cases therefore provide useful situations for examining whether the
invariant has been formulated correctly.

Edge-case analysis is developed in:

```text
04-edge-cases.md
```

---

# Invariants and Automated Testing

Invariants belong primarily to correctness reasoning.

Automated tests provide a different form of evidence.

Tests may indirectly verify observable consequences of an invariant for selected
inputs.

For example:

```text
Invariant

Processed prefix remains correctly ordered.

        ↓

Observable Consequence

Final output is ordered.

        ↓

Automated Test
```

However, ordinary black-box tests do not generally prove that an invariant
remained true internally throughout every step.

Therefore:

> **Invariant reasoning explains why a property should remain true; testing
> provides executable evidence for selected behaviours.**

Testing is developed formally in:

```text
docs/16-testing/
```

---

# Invariants and Implementation

Invariant reasoning should ideally be developed before implementation details
obscure the underlying algorithm.

The progression is:

```text
Problem Specification
        ↓
Algorithmic Strategy
        ↓
Invariant
        ↓
Correctness Reasoning
        ↓
Java Implementation
```

The Java implementation should then preserve the logical structure upon which
the invariant argument depends.

For example, changing loop boundaries incorrectly may cause an implementation to
violate an otherwise valid algorithmic invariant.

This illustrates the distinction between:

```text
Correct Algorithm
```

and:

```text
Correct Implementation of the Algorithm
```

---

# Invariants and Complexity Analysis

Invariants explain correctness, not computational cost.

The questions are different.

Invariant reasoning asks:

> **Which property remains true while the algorithm executes?**

Complexity analysis asks:

> **How many computational resources are required as the input grows?**

Both may analyse the same loop, but for different purposes.

For example:

```text
Loop Invariant
        ↓
Supports Correctness

Iteration Count
        ↓
Supports Complexity Analysis
```

Complexity is developed systematically in:

```text
docs/02-complexity/
```

---

# Finding a Useful Invariant

Identifying a good invariant is often one of the most difficult parts of
correctness reasoning.

A useful process is to ask:

1. What has already been established after processing part of the input?
2. Which relationship must remain true for the algorithm to make progress?
3. Which property would help prove the final postcondition?
4. Is the property true before the first relevant iteration?
5. Can one execution step preserve it?
6. Does it become strong enough at termination to imply the required result?

Conceptually:

```text
Postcondition
        ↓
Work Backwards
        ↓
What Must Be True Before Termination?
        ↓
Candidate Invariant
```

In many cases, the desired postcondition provides clues for discovering the
appropriate invariant.

---

# Example — Linear Search

Consider a search algorithm that examines elements sequentially.

A possible invariant is:

> Before examining position `i`, the target does not appear among the elements
> already inspected.

Conceptually:

```text
Processed Prefix
        ↓
Target Not Found There
        ↓
Inspect Next Element
```

If the target is encountered, the algorithm can return its position.

If the algorithm reaches the end while preserving the invariant, then the
target does not occur anywhere in the input.

This demonstrates how an invariant can connect partial progress to a final
search conclusion.

---

# Example — Insertion Sort

A classical invariant for insertion sort can be expressed as:

> Before each iteration, the portion of the array already processed is sorted.

Conceptually:

```text
Sorted Prefix
        ↓
Insert Next Element Correctly
        ↓
Larger Sorted Prefix
```

The invariant grows with each iteration.

At termination, the processed portion is the entire array.

Therefore, the complete array is sorted.

This type of reasoning will be developed in greater depth in the sorting module.

---

# Invariants Throughout the Repository

Invariant reasoning will appear repeatedly throughout **Algorithms Java
Mastery**.

Relevant contexts include:

- searching algorithms;
- sorting algorithms;
- heap operations;
- tree structures;
- graph traversal;
- recursive algorithms;
- dynamic programming;
- data-structure modification.

However, the exact form of the invariant depends on the algorithm.

The repository should not force every algorithm into the same invariant
template.

The objective is to identify meaningful properties when they provide real
correctness value.

---

# Best Practices

When using invariants:

- begin from the problem specification;
- identify the relevant preconditions and postconditions;
- formulate the invariant precisely;
- define when the invariant is expected to hold;
- establish initialisation;
- establish maintenance;
- connect termination with the postcondition;
- distinguish invariant preservation from termination;
- analyse valid edge cases;
- avoid implementation-specific wording when possible;
- use structural invariants for data structures when appropriate;
- use recursive or inductive reasoning when it fits better than a loop
  invariant;
- avoid inventing invariants merely to satisfy a template.

An invariant should clarify the correctness argument rather than make it more
complicated.

---

# Common Mistakes

## Describing an Algorithm Step Instead of an Invariant

Incorrect:

```text
Compare the next element with maximum.
```

This describes an operation.

Better:

```text
maximum is the largest value among all elements processed so far.
```

This describes a preserved property.

---

## Choosing an Invariant That Is Too Weak

An invariant may remain true but still provide no useful connection to the
postcondition.

A useful invariant should contribute directly to the correctness argument.

---

## Choosing an Invariant That Is Too Strong

A property that is false during intermediate execution cannot serve as an
invariant.

For example:

```text
The entire array is sorted.
```

is not a valid insertion-sort loop invariant before sorting has completed.

---

## Forgetting Initialisation

Maintenance is irrelevant if the invariant was never established initially.

---

## Forgetting Termination

Invariant preservation alone does not demonstrate that execution eventually
ends.

---

## Assuming Testing Proves an Invariant

Tests may provide evidence about observable behaviour for selected executions.

They do not generally establish invariant preservation for every possible valid
execution.

---

## Forcing Loop-Invariant Terminology onto Recursive Algorithms

Recursive algorithms are frequently easier to justify using induction and
recursive contracts.

The reasoning technique should match the algorithm.

---

# Systematic Invariant Analysis

A useful invariant-analysis process is:

```text
1. Identify the specification.
        ↓
2. Identify preconditions and postconditions.
        ↓
3. Examine the algorithmic strategy.
        ↓
4. Identify a candidate preserved property.
        ↓
5. Define exactly when the property must hold.
        ↓
6. Establish initialisation.
        ↓
7. Establish maintenance.
        ↓
8. Establish termination separately.
        ↓
9. Connect invariant + termination to the postcondition.
```

This process provides a disciplined foundation for correctness reasoning.

---

# Key Takeaways

After completing this document, the learner should understand that:

- an invariant is a logical property preserved at defined stages of execution;
- variables and data structures may change while an invariant remains true;
- loop invariants provide an important technique for reasoning about iterative
  algorithms;
- structural invariants describe properties maintained by data structures;
- recursive algorithms may require inductive reasoning rather than traditional
  loop invariants;
- initialisation establishes the invariant;
- maintenance preserves it;
- termination connects the preserved property with the required postcondition;
- invariant preservation does not by itself establish termination;
- invariants support correctness reasoning but do not replace the complete
  correctness argument;
- testing and invariants provide different forms of evidence;
- useful invariants should be discovered because they clarify reasoning, not
  because every algorithm must artificially contain one.

Algorithmic invariants therefore provide a powerful bridge between **problem
specification**, **algorithmic contracts**, **correctness reasoning**, and
**implementation**.

They allow the learner to explain not only how an algorithm changes its state,
but why those changes preserve the properties required for the final result.

---

# Next Document

```text
07-problem-decomposition.md
```

The next document introduces problem decomposition as a systematic technique for
reducing complex computational problems into smaller and more manageable
subproblems.

It develops the relationship between:

```text
Complex Problem
        ↓
Subproblem Identification
        ↓
Independent Reasoning
        ↓
Composition
        ↓
Complete Solution
```

Problem decomposition provides an important foundation for later algorithm
design paradigms including recursion, divide and conquer, and dynamic
programming.

---

# Academic Foundation

This document is inspired primarily by:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

Loop invariants and correctness reasoning form an important part of the
systematic analysis of algorithms throughout the book and provide the principal
academic context for this document.

Complementary references concerning program correctness, invariants, formal
reasoning, and disciplined program construction are listed in:

```text
docs/00-project/10-references.md
```

More formal treatment may be supported by works such as Dijkstra's
*A Discipline of Programming* and Gries's *The Science of Programming* when
additional depth is appropriate.