# Common Mistakes

## Algorithms Java Mastery

This document presents the most common mistakes encountered when working with
arrays in Java and when designing algorithms that operate on them.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
algorithmic reasoning developed throughout this repository.

The previous documents established:

- the abstract array model;
- indexed access;
- memory organisation;
- Java array semantics;
- multidimensional arrays;
- fundamental array operations;
- common array algorithms;
- complexity analysis;
- problem-solving methodology.

This document focuses on mistakes that commonly occur when those concepts are
translated into algorithm implementations.

The objective is not merely to identify programming errors.

Each mistake is analysed through:

```text
Mistake
        ↓
Cause
        ↓
Consequence
        ↓
Prevention
        ↓
Engineering Practice
```

The central question addressed throughout this document is:

> **What mistakes commonly occur when designing and implementing array
> algorithms, and how can they be prevented systematically?**

---

# Purpose

The purpose of this document is to identify, explain, and prevent common errors
related to array manipulation and algorithm design.

The emphasis is on reasoning rather than memorising compiler or runtime
messages.

Understanding why an error occurs is more valuable than simply memorising its
symptom.

The expected progression is:

```text
Incorrect Assumption
        ↓
Incorrect Implementation
        ↓
Unexpected Behaviour
        ↓
Root-Cause Analysis
        ↓
Prevention
```

The goal is to develop programming practices that reduce defects before they
reach the testing or debugging stages.

---

# Learning Objectives

After studying this document, the learner should be able to:

- recognise common array implementation mistakes;
- explain why those mistakes occur;
- prevent invalid index access;
- distinguish `null` references from empty arrays;
- distinguish references from copies;
- recognise mutation-related errors;
- analyse array edge cases correctly;
- select an appropriate data structure;
- identify inefficient algorithmic strategies;
- apply defensive programming practices;
- relate implementation mistakes to the Foundations and Complexity modules;
- design tests that expose common array-related defects.

---

# Mistake 1 — Off-by-One Errors

## Description

An off-by-one error occurs when a loop or index calculation accesses one position
outside the intended range.

Incorrect example:

```text
for (int i = 0; i <= numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

---

## Why It Happens

For an array of length:

```text
n
```

the valid indexes are:

```text
0 ≤ i < n
```

The last valid index is:

```text
n - 1
```

Using:

```text
i <= numbers.length
```

allows:

```text
i = numbers.length
```

which is outside the valid index range.

---

## Consequence

The Java runtime throws:

```text
ArrayIndexOutOfBoundsException
```

when the invalid access is reached.

---

## Correct Implementation

```text
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

The loop condition preserves:

```text
0 ≤ i < numbers.length
```

---

## Best Practice

Before writing an array loop, explicitly determine:

```text
First Valid Index = 0
Last Valid Index = length - 1
```

This simple step prevents many traversal errors.

---

# Mistake 2 — Confusing Length with the Last Index

An array of length:

```text
5
```

contains these valid indexes:

```text
0  1  2  3  4
```

Therefore:

```text
length = 5
```

but:

```text
last index = 4
```

The correct relationship is:

```text
Last Index = length - 1
```

---

## Common Error

```text
numbers[numbers.length]
```

is invalid.

The final valid access is:

```text
numbers[numbers.length - 1]
```

provided the array is non-empty.

---

# Mistake 3 — Accessing Invalid Positions

Invalid indexes include:

```text
numbers[-1];
```

or:

```text
numbers[100];
```

when the array contains fewer than 101 positions.

---

## Consequence

Java performs runtime bounds checking.

An invalid index results in:

```text
ArrayIndexOutOfBoundsException
```

---

## Best Practice

Ensure that an index satisfies:

```text
0 ≤ index < array.length
```

For algorithms, this condition should normally be established through the loop
or problem logic rather than by adding unnecessary checks before every array
access.

---

# Mistake 4 — Confusing `null` with an Empty Array

Declaring an array variable does not automatically create an array object.

Example:

```text
int[] numbers;
```

The local variable has no assigned array reference.

After explicit assignment:

```text
int[] numbers = null;
```

the reference explicitly contains:

```text
null
```

This is different from:

```text
int[] numbers = new int[0];
```

which creates a valid array object with zero elements.

Conceptually:

```text
null reference
        ≠
empty array
```

---

## Consequence

Attempting:

```text
numbers.length
```

or:

```text
numbers[0]
```

when `numbers == null` causes:

```text
NullPointerException
```

---

## Best Practice

Define the method contract explicitly.

For example:

```text
Precondition

Array reference must not be null.
```

and separately define whether:

```text
length = 0
```

is valid.

---

# Mistake 5 — Assuming Arrays Resize Automatically

Java arrays have a fixed length after creation.

For example:

```text
int[] numbers = new int[5];
```

always has:

```text
length = 5
```

The array cannot grow to six positions automatically.

---

## Incorrect Assumption

```text
Array Full
    ↓
Automatically Grow
```

This behaviour belongs to a dynamic data structure rather than to the fixed
array itself.

---

## Correct Model

```text
Fixed Array
        ↓
Fixed Length
```

For dynamic growth:

```text
Dynamic Structure
        ↓
Possibly Reallocate Underlying Storage
```

A common Java example is `ArrayList`, whose implementation manages dynamic
capacity internally.

---

# Mistake 6 — Comparing Arrays with `==`

Incorrect:

```text
array1 == array2
```

For arrays, `==` compares whether the two variables refer to the same array
object.

It does not compare element contents.

---

## Correct Content Comparison

Use:

```text
Arrays.equals(array1, array2);
```

with:

```text
import java.util.Arrays;
```

Conceptually:

```text
Reference Equality
        ≠
Content Equality
```

---

# Mistake 7 — Assuming Assignment Copies an Array

Consider:

```text
int[] copy = numbers;
```

This does not create a new array.

Both variables refer to the same array object:

```text
numbers ─────┐
             ↓
          [1][2][3]
             ↑
copy ────────┘
```

Changing:

```text
copy[0] = 99;
```

also changes the array observed through `numbers`.

---

## Correct Solution

Create a separate array when an independent copy is required:

```text
int[] copy = Arrays.copyOf(numbers, numbers.length);
```

This creates a new array object.

---

## Complexity

Reference assignment:

```text
Time: Θ(1)
Additional Space: Θ(1)
```

Content copying:

```text
Time: Θ(n)
Additional Space: Θ(n)
```

when `n` elements are copied into a new array.

---

# Mistake 8 — Ignoring Edge Cases

Algorithms are often tested only with typical inputs.

This can hide defects.

Common array cases include:

```text
Empty Array
Single Element
Duplicate Values
All Equal Values
Negative Values
Extreme Values
Already Sorted
Reverse Sorted
Very Large Input
```

Not every case applies to every algorithm.

The problem specification determines which cases belong to the valid domain.

---

## Best Practice

Analyse edge cases before implementation.

For example:

```text
Find Maximum
        ↓
Does empty input belong to the valid domain?
```

The answer must be defined by the algorithm's contract.

---

# Mistake 9 — Modifying an Array While Traversing It

Changing an array during traversal may invalidate assumptions about indexes or
the remaining data.

For example, repeatedly shifting elements while iterating can cause:

- elements to be skipped;
- elements to be processed more than once;
- incorrect indexes;
- unexpected results.

The problem is not that modification is always invalid.

The problem is modifying the structure without reasoning about how the change
affects subsequent iterations.

---

## Best Practice

Before modifying an array during traversal, determine:

- which elements move;
- how indexes change;
- whether the traversal direction remains valid;
- whether elements may be skipped;
- whether an auxiliary structure would simplify the algorithm.

---

# Mistake 10 — Choosing the Wrong Data Structure

Arrays provide efficient indexed access, but they are not optimal for every
problem.

For example:

```text
Frequent Order-Preserving Insertions
        ↓
Potentially Many Element Shifts
        ↓
Array May Become Expensive
```

A different structure may be more appropriate depending on the workload.

Possible alternatives include:

```text
ArrayList
Linked Structure
Deque
HashMap
TreeMap
```

The correct choice depends on:

- dominant operations;
- input size;
- ordering requirements;
- memory constraints;
- access patterns.

---

## Engineering Principle

Do not choose a data structure solely because it is familiar.

Choose it because its characteristics match the computational problem.

---

# Mistake 11 — Ignoring Computational Complexity

A program may produce the correct result while still being inefficient.

For example, an algorithm may use nested comparisons:

```text
for
    for
```

producing:

```text
Θ(n²)
```

when a different strategy could solve the problem in:

```text
Θ(n)
```

under appropriate assumptions.

---

## Example

Duplicate detection may be implemented with pairwise comparisons:

```text
Time: Θ(n²)
Space: Θ(1)
```

or with an auxiliary hash-based structure:

```text
Expected Time: Θ(n)
Auxiliary Space: Θ(n)
```

The appropriate choice depends on engineering constraints.

---

# Mistake 12 — Programming Before Understanding the Problem

Beginning implementation immediately may lead to:

- incorrect assumptions;
- unnecessary code;
- weak algorithm selection;
- difficult debugging;
- unclear contracts;
- unnecessary complexity.

The repository methodology therefore requires:

```text
Problem
        ↓
Specification
        ↓
Strategy
        ↓
Correctness
        ↓
Complexity
        ↓
Implementation
```

Programming should be the expression of an understood solution.

---

# Mistake 13 — Hard-Coding Array Dimensions

Incorrect:

```text
for (int i = 0; i < 10; i++) {
    process(numbers[i]);
}
```

This code assumes a fixed length of ten.

If the array has fewer than ten elements, the code may fail.

If it has more, elements may be ignored.

---

## Correct Approach

Use the actual array length:

```text
for (int i = 0; i < numbers.length; i++) {
    process(numbers[i]);
}
```

For a multidimensional array:

```text
for (int row = 0; row < matrix.length; row++) {
    for (int column = 0; column < matrix[row].length; column++) {
        process(matrix[row][column]);
    }
}
```

This also supports jagged arrays correctly.

---

# Mistake 14 — Assuming All Rows Have the Same Length

Java multidimensional arrays are arrays of arrays.

Therefore, this structure is valid:

```text
int[][] data = {
    {1, 2},
    {3, 4, 5},
    {6}
};
```

The rows have different lengths.

---

## Incorrect Assumption

```text
Every row has the same number of columns.
```

This is only valid when the problem explicitly guarantees a rectangular
structure.

---

## Best Practice

When traversing potentially jagged arrays:

```text
for (int row = 0; row < data.length; row++) {
    for (int column = 0; column < data[row].length; column++) {
        process(data[row][column]);
    }
}
```

---

# Mistake 15 — Confusing `null`, Empty, and Uninitialised State

These concepts should remain distinct.

An array variable may be:

```text
Not assigned
```

or explicitly:

```text
null
```

or refer to:

```text
new int[0]
```

or refer to:

```text
new int[n]
```

These represent different states.

A robust API should define which states are valid.

---

# Mistake 16 — Ignoring Mutation

Arrays are mutable.

A method receiving an array reference can modify the elements of the same
array object.

For example:

```text
static void update(int[] numbers) {
    numbers[0] = 100;
}
```

The caller's array may therefore change.

---

## Engineering Consequence

Unexpected mutation can produce:

- hidden side effects;
- difficult debugging;
- incorrect assumptions between methods;
- test interference.

---

## Best Practice

Document whether a method:

```text
Reads the array only
```

or:

```text
Modifies the input array
```

When necessary, use defensive copying.

---

# Mistake 17 — Returning Internal Mutable Arrays Without Considering Ownership

Suppose a class internally stores:

```text
private final int[] values;
```

Returning:

```text
return values;
```

exposes the same mutable array object.

External code may then modify internal state.

---

## Safer Alternative

Return a defensive copy when the API requires encapsulation:

```text
return Arrays.copyOf(values, values.length);
```

This introduces:

```text
Θ(n)
```

time and:

```text
Θ(n)
```

additional space.

The trade-off should be intentional.

---

# Mistake 18 — Ignoring Numeric Overflow

Array algorithms often process numeric values.

An algorithm may be logically correct while producing incorrect numeric results
because of the chosen data type.

For example:

```text
int total = 0;

for (int value : numbers) {
    total += value;
}
```

may overflow if the sum exceeds the representable range of `int`.

Therefore:

```text
Algorithmic Correctness
        +
Numeric Representation
```

must both be considered.

Complexity analysis alone does not guarantee numerical correctness.

---

# Mistake 19 — Using `O(...)` Without Understanding the Bound

A common error is to write:

```text
O(n)
```

without explaining what the notation represents.

For example:

```text
Linear Search
```

may have:

```text
Best Case: Θ(1)
Worst Case: Θ(n)
```

while:

```text
Maximum Search
```

has:

```text
Time: Θ(n)
```

in its standard model.

The correct bound should be attached to:

- the resource;
- the case;
- the input model;
- the implementation assumptions.

---

# Mistake 20 — Treating Benchmarks as Complexity Proof

A benchmark may show:

```text
Implementation A
        ↓
Faster on Tested Inputs
```

This does not prove:

```text
A ∈ Θ(n)
```

or:

```text
A ∈ O(log n)
```

Finite empirical measurements and asymptotic analysis answer different
questions.

The correct relationship is:

```text
Theoretical Analysis
        ↓
Complexity

Empirical Evaluation
        ↓
Benchmark
```

Benchmarking complements complexity analysis.

---

# Summary of Common Mistakes

| Mistake | Typical Consequence |
|---|---|
| Off-by-one error | Invalid index access |
| Using `length` as the last index | Runtime exception |
| Invalid index | `ArrayIndexOutOfBoundsException` |
| Null array reference | `NullPointerException` |
| Assuming automatic resizing | Incorrect data-structure model |
| Comparing with `==` | Reference comparison instead of content comparison |
| Assigning instead of copying | Unexpected shared mutations |
| Ignoring edge cases | Incomplete or fragile algorithms |
| Modifying during traversal without analysis | Logical errors |
| Choosing the wrong structure | Poor performance or unnecessary complexity |
| Ignoring complexity | Poor scalability |
| Coding before analysis | Weak algorithm design |
| Hard-coded dimensions | Incorrect or inflexible code |
| Assuming rectangular rows | Incorrect multidimensional traversal |
| Ignoring mutation | Hidden side effects |
| Exposing mutable internal arrays | Encapsulation violations |
| Ignoring numeric overflow | Incorrect results |
| Misusing asymptotic notation | Incorrect complexity claims |
| Treating benchmarks as proofs | Invalid conclusions |

---

# Defensive Programming

Defensive programming means designing software to anticipate invalid states,
unexpected inputs, and incorrect assumptions.

For array-based algorithms, appropriate practices may include:

- defining valid input domains;
- checking required preconditions;
- validating indexes when indexes originate from external or uncertain data;
- handling `null` according to the API contract;
- distinguishing empty collections from invalid references;
- documenting mutation;
- using defensive copies when encapsulation requires them;
- writing automated tests for relevant edge cases.

Defensive programming should not mean adding unnecessary checks everywhere.

The objective is to protect meaningful boundaries defined by the problem and
API contract.

---

# Mistakes and Problem Specifications

Many implementation errors originate from incomplete specifications.

For example:

```text
Problem:
Find the maximum value.
```

is incomplete if it does not state how to handle:

```text
Empty Array
```

A more precise contract might be:

```text
Precondition:
Array contains at least one element.
```

This specification immediately removes one class of ambiguity.

Therefore:

```text
Specification
        ↓
Contracts
        ↓
Implementation
        ↓
Fewer Ambiguities
```

---

# Mistakes and Correctness

Common mistakes often represent violations of correctness requirements.

For example:

```text
Off-by-One
        ↓
Invalid Access
        ↓
Incorrect Execution
```

or:

```text
Incorrect Maximum Initialisation
        ↓
Invalid Invariant
        ↓
Incorrect Result
```

Correctness reasoning should therefore be performed before optimisation.

---

# Mistakes and Complexity

Some mistakes affect not only correctness but also performance.

For example:

```text
Unnecessary Nested Traversal
        ↓
Θ(n²)
```

when:

```text
Single-Pass Strategy
        ↓
Θ(n)
```

may be possible.

Therefore, preventing mistakes also involves recognising inefficient
algorithmic patterns.

---

# Mistakes and Testing

Automated tests should target the risks identified during analysis.

For example:

```text
Off-by-One Risk
        ↓
Boundary Index Tests
```

```text
Null Risk
        ↓
Null-Input Test When Contract Defines It
```

```text
Mutation Risk
        ↓
Verify Input State After Execution
```

```text
Copying Risk
        ↓
Verify Source and Copy Are Independent
```

The test strategy should originate from the specification and identified risks.

---

# Relationship with Previous Modules

This document integrates concepts from the previous modules.

```text
Foundations
        ↓
Problem Specification
        ↓
Contracts
        ↓
Edge Cases
        ↓
Correctness
```

```text
Complexity
        ↓
Cost Analysis
        ↓
Scalability
        ↓
Engineering Trade-Offs
```

```text
Arrays
        ↓
Indexes
        ↓
Operations
        ↓
Java Representation
```

The document therefore serves as a practical error-prevention layer across the
three areas.

---

# Relationship with Future Modules

The principles presented here remain applicable to:

```text
Searching
Sorting
Stacks
Queues
Hash Tables
Trees
Heaps
Graphs
Backtracking
Dynamic Programming
```

Different data structures introduce different failure modes, but the same
engineering principles continue to apply:

```text
Specification
        ↓
Contracts
        ↓
Edge Cases
        ↓
Correctness
        ↓
Complexity
        ↓
Testing
```

---

# Best Practices

When implementing array algorithms:

- define the problem before coding;
- establish valid input assumptions;
- distinguish `null` from empty arrays;
- use valid index ranges;
- avoid hard-coded dimensions;
- distinguish references from copies;
- document mutation;
- consider defensive copies when required;
- analyse edge cases;
- consider numeric-range limitations;
- choose the data structure according to dominant operations;
- derive complexity rather than memorising it;
- test behaviour at important boundaries;
- benchmark only when empirical evidence adds value.

The objective is not simply to prevent runtime exceptions.

It is to prevent incorrect assumptions from becoming incorrect software.

---

# Common Mistake-Prevention Checklist

Before considering an array implementation complete, verify:

```text
□ Is the problem specification clear?
□ Are inputs and outputs defined?
□ Are preconditions explicit?
□ Are postconditions explicit?
□ Is null behaviour defined?
□ Is empty-input behaviour defined?
□ Are valid indexes respected?
□ Is the last valid index understood?
□ Are dimensions obtained dynamically?
□ Are jagged arrays handled correctly when relevant?
□ Is mutation intentional?
□ Are references and copies distinguished?
□ Are edge cases tested?
□ Is numeric overflow considered when relevant?
□ Is time complexity justified?
□ Is auxiliary space justified?
□ Is the selected data structure appropriate?
□ Is benchmarking necessary?
```

This checklist can be incorporated into the review process for future array
algorithms.

---

# Expected Outcome

After completing this document, the learner should be able to recognise that
many array defects originate from incorrect assumptions rather than difficult
Java syntax.

The intended reasoning process becomes:

```text
Identify Assumption
        ↓
Question the Assumption
        ↓
Define the Contract
        ↓
Analyse the Boundary
        ↓
Implement Carefully
        ↓
Test the Risk
```

The learner should be able to identify and prevent common problems involving:

- indexing;
- array length;
- references;
- mutation;
- copying;
- null values;
- edge cases;
- data-structure selection;
- complexity;
- numerical limitations.

---

# Key Takeaways

After studying this document, the learner should remember that:

- many array defects originate from incorrect reasoning rather than syntax;
- valid indexes satisfy `0 ≤ index < array.length`;
- the last valid index is `length - 1`;
- `null` and an empty array are different states;
- Java arrays have fixed length;
- assigning one array variable to another copies the reference;
- `Arrays.copyOf` creates a separate array object;
- `==` compares array references, while `Arrays.equals` compares contents;
- arrays are mutable, so mutation should be intentional and documented;
- multidimensional Java arrays may be jagged;
- edge cases should be derived from the problem specification;
- numeric overflow can affect correctness independently of algorithmic
  complexity;
- the choice of data structure should follow the dominant operations;
- complexity should be justified rather than memorised;
- benchmarking complements theoretical analysis but does not prove complexity;
- defensive programming should protect meaningful contract boundaries.

Recognising and preventing these mistakes strengthens the connection between
**problem specification**, **correctness**, **complexity**, **Java
implementation**, and **testing**.

The result is a more disciplined approach to software development that can be
applied not only to arrays but throughout the entire **Algorithms Java Mastery**
repository.

---

# Next Document

```text
11-interview-notes.md
```

The next document provides a concise technical reference for reviewing array
concepts in interview-oriented contexts.

It will consolidate:

- array fundamentals;
- indexing;
- operation complexity;
- common traversal patterns;
- frequent technical questions;
- common pitfalls;
- problem-solving considerations.

The next central question becomes:

> **Which array concepts and reasoning patterns should a software engineer be
> able to explain clearly in a technical interview?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The error-prevention methodology in this document also integrates the concepts
developed throughout:

```text
docs/01-foundations/
docs/02-complexity/
docs/03-arrays/
```

Java-specific behaviour is based on the Java language and standard library
model.

Complementary academic, mathematical, and technical references are documented
in:

```text
docs/00-project/10-references.md
```