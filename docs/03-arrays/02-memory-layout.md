# Memory Layout

## Algorithms Java Mastery

This document introduces the conceptual memory organisation associated with arrays
and explains how that organisation contributes to their characteristic
performance.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the study
of algorithms and data structures throughout this repository.

The previous document established the array as a fixed, indexed data structure.
This document develops the next level of reasoning by connecting:

```text
Array Structure
        ↓
Memory Organisation
        ↓
Indexed Address Calculation
        ↓
Access Behaviour
        ↓
Algorithmic Performance
```

The classical array model assumes that elements occupy consecutive storage
locations. This model explains why a known index can be translated directly
into an element position without traversing preceding elements.

The central question addressed throughout this document is:

> **How does the memory organisation of an array support efficient indexed
> access, and what practical consequences does this organisation have for
> algorithm performance?**

---

# Purpose

The purpose of this document is to establish the relationship between the
classical array memory model and the computational behaviour of array
operations.

The learner should understand that the efficiency of indexed access is not a
consequence of Java syntax itself.

Instead, it follows from the underlying data-structure model:

```text
Memory Organisation
        ↓
Predictable Element Positions
        ↓
Direct Address Calculation
        ↓
Efficient Indexed Access
```

The document also introduces the relationship between memory locality and
practical processor performance while maintaining the distinction between:

```text
Theoretical Complexity
```

and:

```text
Concrete Runtime Behaviour
```

---

# Learning Objectives

After studying this document, the learner should be able to:

- explain the concept of a memory address;
- describe contiguous storage conceptually;
- explain the relationship between an array's base position and its index;
- understand the classical address-calculation model for arrays;
- explain why indexed access has constant asymptotic cost;
- distinguish direct access from sequential traversal;
- explain the concept of spatial locality;
- understand why contiguous data can benefit practical cache behaviour;
- distinguish asymptotic complexity from hardware-specific performance;
- compare array and linked-structure memory models conceptually;
- recognise how memory organisation influences algorithm design.

These competencies extend the abstract array model introduced in
`01-array-fundamentals.md`.

---

# Computer Memory

At a conceptual level, computer memory can be modelled as a collection of
addressable storage locations.

Simplified:

```text
Memory

Address
  ↓

[ ][ ][ ][ ][ ][ ][ ][ ][ ]
```

Each address identifies a particular location in the memory model.

The amount of information stored at each location depends on the underlying
memory representation and computational model.

For algorithm analysis, the important idea is that memory locations can be
addressed.

---

# Memory Addresses

A memory address identifies a location within the memory address space.

Conceptually:

```text
1000
1001
1002
1003
1004
```

These values represent different addresses in a simplified model.

An array can take advantage of predictable relationships between consecutive
element positions.

Conceptually:

```text
Address
   ↓

1000   1004   1008   1012
  ↓      ↓      ↓      ↓

[A]    [B]    [C]    [D]
```

The exact numeric spacing depends on the representation and element size.

---

# Contiguous Storage

The classical array model stores elements in **contiguous storage**.

Conceptually:

```text
Memory

┌───────┬───────┬───────┬───────┬───────┐
│ A[0]  │ A[1]  │ A[2]  │ A[3]  │ A[4]  │
└───────┴───────┴───────┴───────┴───────┘
```

Consecutive elements occupy consecutive positions in the underlying storage
model.

This predictable organisation is fundamental to efficient indexed access.

---

# Why Contiguous Storage Matters

Suppose the location of the first element is known.

If every element occupies the same amount of storage, the position of another
element can be calculated from its index.

Conceptually:

```text
Base Position
        +
Index × Element Size
        ↓
Target Position
```

Therefore, the algorithm does not need to examine:

```text
A[0]
A[1]
A[2]
...
```

before reaching:

```text
A[i]
```

The required position can be calculated directly.

---

# Address Calculation

A simplified address-calculation model is:

```text
Address(A[i])
    =
Base Address
    +
i × Element Size
```

For example, if:

```text
Base Address = B
Element Size = s
Index = i
```

then conceptually:

```text
Address = B + i × s
```

The exact implementation details depend on the language and runtime, but this
model explains the fundamental property of classical arrays.

---

# Why Indexed Access Is Θ(1)

If the index is already known and valid, the address calculation requires a
fixed amount of computational work under the standard array model.

Therefore:

```text
Known Index
        ↓
Address Calculation
        ↓
Element Access
        ↓
Θ(1)
```

This is a theoretical statement about asymptotic growth.

It does not mean that every physical memory access takes exactly the same number
of processor cycles.

---

# Constant Asymptotic Cost vs Physical Latency

Consider:

```text
A[0]
```

and:

```text
A[n - 1]
```

Both have constant asymptotic access cost:

```text
Θ(1)
```

However, their actual physical access latency may differ because of factors
such as:

- cache state;
- memory hierarchy;
- processor architecture;
- runtime behaviour.

Therefore:

```text
Same Asymptotic Complexity
        ≠
Identical Physical Latency
```

This distinction is fundamental to the separation between algorithm analysis and
benchmarking established in the `02-complexity` module.

---

# Random Access

Arrays are commonly described as supporting **random access**.

In this context, “random” means:

> **An arbitrary valid position can be accessed directly.**

It does not imply randomness in the algorithm.

Conceptually:

```text
A[0]
A[5]
A[100]
A[n - 1]
```

may all be accessed directly when their indexes are known.

This property distinguishes arrays from data structures that require sequential
traversal to reach a position.

---

# Sequential Traversal

Direct access to one element is constant-time under the standard model.

Processing every element is different.

For example:

```text
A[0]
 ↓
A[1]
 ↓
A[2]
 ↓
...
 ↓
A[n - 1]
```

If constant work is performed for each element:

```text
n elements
        ×
constant work
        ↓
Θ(n)
```

Therefore:

```text
Indexed Access
        ↓
Θ(1)
```

while:

```text
Complete Traversal
        ↓
Θ(n)
```

The operation being analysed must always be identified.

---

# Memory Locality

Modern processors use multiple levels of storage with different performance
characteristics.

A simplified hierarchy is:

```text
Processor
    ↓
CPU Cache
    ↓
Main Memory
    ↓
Long-Term Storage
```

Array layout can interact favourably with this hierarchy because nearby elements
are often stored near one another.

This leads to the concept of **spatial locality**.

---

# Spatial Locality

Spatial locality refers to the tendency for programs that access one memory
location to also access nearby locations.

Sequential array traversal provides a common example:

```text
A[0]
 ↓
A[1]
 ↓
A[2]
 ↓
A[3]
 ↓
A[4]
```

Because these elements are stored close together in the classical array model,
the processor's memory hierarchy may make sequential access more efficient in
practice.

---

# Spatial Locality vs Complexity

Spatial locality affects practical execution performance.

It does not change the asymptotic classification of a traversal.

For example:

```text
Sequential Traversal
        ↓
Θ(n)
```

remains:

```text
Θ(n)
```

whether one implementation benefits from better cache behaviour than another.

Therefore:

```text
Cache Locality
        ↓
Practical Performance Factor
```

while:

```text
Θ(n)
        ↓
Asymptotic Growth Property
```

These concepts should not be conflated.

---

# Arrays and Cache-Friendly Access

Array traversal is often considered cache-friendly because neighbouring elements
are commonly accessed sequentially.

For example:

```text
A[0] → A[1] → A[2] → A[3]
```

may exhibit stronger locality than an access pattern that repeatedly jumps
through unrelated memory locations.

This can influence:

- practical execution time;
- processor cache utilisation;
- memory bandwidth usage.

These are implementation and hardware-level considerations rather than
asymptotic complexity classifications.

---

# Contiguous Storage and Fragmentation

The classical array model requires a contiguous region of storage large enough
for its elements.

This differs conceptually from linked structures, where different nodes may
occupy independent memory locations.

Therefore:

```text
Array
        ↓
Contiguous Region
```

versus:

```text
Linked Structure
        ↓
Separate Nodes
        ↓
References Connect Them
```

The memory-organisation difference contributes to different access and locality
characteristics.

---

# Comparison with Linked Structures

Consider a simplified array:

```text
[A][B][C][D]
```

The elements have predictable positional relationships.

A linked structure may instead look conceptually like:

```text
[A] → [C] → [B] → [D]
```

where the nodes may reside at unrelated memory locations.

To reach a later node, traversal through references may be required.

This contributes to the difference between:

```text
Array Indexed Access
        ↓
Θ(1)
```

and a typical linked sequential access model:

```text
Position-Based Traversal
        ↓
Θ(n)
```

for reaching an arbitrary position.

The exact complexity depends on the operation and data-structure implementation.

---

# Memory Layout and Array Operations

The array memory model influences several operations.

| Operation | Typical Time Behaviour | Reason |
|---|---:|---|
| Access known index | `Θ(1)` | Position calculated directly |
| Update known index | `Θ(1)` | Position already known |
| Complete traversal | `Θ(n)` | All elements processed |
| Unordered search | Worst `Θ(n)` | Multiple positions may need inspection |
| Copy all elements | `Θ(n)` | Every element must be processed |
| Shift elements | Up to `Θ(n)` | Multiple positions may need movement |

These results follow from the operation being analysed, not from “arrays” as a
single complexity category.

---

# Insertion and Memory Layout

Insertion into a fixed array requires careful definition.

Suppose logical order must be preserved:

```text
Before

[A][B][C][ ][ ]
```

Insert `X` at position `1`:

```text
Shift elements
        ↓

[A][X][B][C][ ]
```

The elements after the insertion point may need to move.

In the worst case, a linear number of elements may be shifted.

Therefore:

```text
Order-Preserving Insertion
        ↓
Worst-Case Θ(n)
```

The fixed physical length of the array also means that growing beyond available
capacity requires another representation or a larger storage region.

---

# Deletion and Memory Layout

Deletion may require a similar shift.

Before:

```text
[A][B][C][D]
```

Delete `B`:

```text
[A][C][D][ ]
```

If order must be preserved, elements following the removed position may need to
move.

Therefore:

```text
Order-Preserving Deletion
        ↓
Worst-Case Θ(n)
```

Again, the exact cost depends on which deletion operation is being defined.

---

# Copying Arrays

Copying an array requires processing its elements.

For an array containing `n` elements:

```text
A
 ↓
B
```

the algorithm must account for all elements that are copied.

Therefore:

```text
Copy n Elements
        ↓
Θ(n)
```

This is important when analysing:

- dynamic arrays;
- resizing;
- defensive copies;
- transformations;
- temporary structures.

---

# Memory Layout and Dynamic Arrays

A dynamic array abstraction typically uses an underlying fixed-size array.

When capacity is exhausted:

```text
Current Array Full
        ↓
Allocate Larger Storage
        ↓
Copy Existing Elements
        ↓
Use New Storage
```

This operation can require:

```text
Θ(n)
```

work for the copied elements.

However, as established in `08-amortized-analysis.md`, a suitable geometric
growth policy can produce:

```text
Amortized Append Cost
        ↓
Θ(1)
```

over a long sequence of append operations.

Thus memory layout connects directly with amortized analysis.

---

# Java and Array Memory

Java abstracts low-level memory management from the programmer.

A Java array provides:

- fixed length;
- indexed access;
- runtime bounds checking;
- a defined component type;
- automatic memory management.

The JVM manages allocation and reclamation.

Therefore, the low-level physical address is not normally manipulated directly
in Java source code.

Nevertheless, the classical array model remains useful for understanding the
algorithmic behaviour of indexed access and traversal.

---

# Important Java Distinction

It is more precise to say:

> **Java preserves the array abstraction and its expected indexed-access
> behaviour while the JVM manages the underlying memory representation.**

The programmer should not assume that Java exposes physical addresses or that
all runtime representation details are identical to a simplified hardware
model.

The computational model and the concrete JVM implementation are related but
not identical.

---

# Java Primitive Arrays

Consider:

```java
int[] numbers = new int[5];
```

The array contains five positions.

Conceptually:

```text
Index

0   1   2   3   4
↓   ↓   ↓   ↓   ↓

[ ][ ][ ][ ][ ]
```

The exact physical representation is managed by the JVM.

The relevant algorithmic abstraction remains:

```text
Known Index
        ↓
Direct Access
        ↓
Θ(1)
```

---

# Java Reference Arrays

Java arrays may also contain object references:

```java
String[] names = new String[5];
```

Conceptually:

```text
Array
        ↓
References
        ↓
Objects
```

This differs from the simplified idea that every array position necessarily
contains the complete object itself.

The memory behaviour depends on the component type.

Nevertheless, indexed access to an array position remains a constant-time
operation under the standard model.

---

# Memory Layout and Multidimensional Arrays

Multidimensional arrays require additional care.

In Java, a declaration such as:

```java
int[][] matrix;
```

represents an array whose elements are themselves array references.

Conceptually:

```text
matrix
  ↓
┌─────┬─────┬─────┐
│ row │ row │ row │
└──┬──┴──┬──┴──┬──┘
   ↓     ↓     ↓
 [ ][ ] [ ][ ] [ ][ ]
```

This means Java's multidimensional arrays should not automatically be treated
as one single flat contiguous matrix.

The Java-specific representation is studied in:

```text
04-multidimensional-arrays.md
```

---

# Memory Layout and Complexity

Memory layout helps explain why array operations exhibit their characteristic
complexity.

The relationship can be summarised as:

```text
Array Organisation
        ↓
Predictable Positions
        ↓
Direct Address Calculation
        ↓
Indexed Access
        ↓
Θ(1)
```

while:

```text
Sequential Processing
        ↓
Visit n Positions
        ↓
Θ(n)
```

and:

```text
Order-Preserving Shifts
        ↓
Move Multiple Elements
        ↓
Up to Θ(n)
```

This is an important example of how data representation influences algorithmic
performance.

---

# Memory Layout and Correctness

Memory organisation also affects the way array algorithms are reasoned about.

For example, a traversal must respect:

```text
0 ≤ i < n
```

because:

```text
n - 1
```

is the last valid zero-based index.

An incorrect boundary condition can therefore produce:

- invalid access;
- incorrect results;
- runtime exceptions in Java.

Memory layout and indexing are consequently connected not only to performance
but also to correctness.

---

# Off-by-One Errors

For an array of length `n`:

```text
Valid indexes:

0, 1, 2, ..., n - 1
```

The following is invalid:

```text
A[n]
```

because it refers to a position outside the valid index domain.

A standard traversal is:

```text
for (int i = 0; i < numbers.length; i++) {
    process(numbers[i]);
}
```

The condition:

```text
i < numbers.length
```

preserves the valid index range.

---

# Memory Layout and Algorithm Design

Understanding memory organisation may influence algorithm selection.

For example, sequential access over an array can be attractive when:

- large numbers of elements must be processed;
- predictable traversal is required;
- positional access is important;
- practical locality matters.

However, memory layout should not be considered independently from:

- problem requirements;
- time complexity;
- space complexity;
- mutation requirements;
- input size;
- alternative data structures.

---

# Arrays vs Linked Structures

A simplified comparison is:

| Property | Array | Linked Structure |
|---|---|---|
| Positional access | Direct | Sequential traversal typically required |
| Storage model | Classical contiguous | Separate nodes connected by references |
| Sequential locality | Often favourable | Usually less predictable |
| Fixed length | Yes | Structure-dependent |
| Ordered insertion | May require shifting | May require reference updates |
| Random positional access | Efficient | Generally slower |

This table provides conceptual intuition.

The exact behaviour depends on the operation and implementation.

---

# Memory Layout and Software Engineering

Memory organisation matters beyond academic algorithm exercises.

It can influence:

- application performance;
- cache behaviour;
- memory footprint;
- data representation;
- high-throughput processing;
- numerical computation;
- systems programming;
- database internals;
- backend workloads.

A software engineer does not need to manipulate physical addresses directly to
benefit from understanding memory layout.

Understanding the abstraction helps explain why certain data structures and
access patterns perform differently.

---

# Memory Layout and Benchmarking

Benchmarking measures concrete implementation behaviour.

Memory layout provides one theoretical explanation for some observed performance
differences.

For example:

```text
Sequential Array Traversal
        ↓
Favourable Locality
        ↓
Potentially Better Practical Throughput
```

However, benchmark results are also affected by:

- cache hierarchy;
- branch prediction;
- JVM optimisation;
- memory allocation;
- garbage collection;
- operating-system activity;
- processor architecture.

Therefore:

```text
Memory Layout
        ↓
One Contributor to Performance
```

not:

```text
Memory Layout
        ↓
Complete Runtime Prediction
```

---

# Memory Layout and Asymptotic Complexity

Memory layout should not be used to redefine asymptotic complexity.

For example:

```text
Array Traversal
        ↓
Θ(n)
```

remains `Θ(n)` even if:

- one machine has a larger cache;
- another machine has slower memory;
- the JVM uses a different optimisation strategy.

These differences affect constants and practical execution characteristics.

---

# Common Misconceptions

## “Arrays Are Fast Because Java Is Fast”

Incorrect.

Java is only one implementation environment.

The array's indexed-access behaviour follows from its data-structure model and
the underlying runtime representation.

---

## “The Last Element Takes Longer to Access”

Incorrect under the standard model.

```text
A[0]
```

and:

```text
A[n - 1]
```

both have:

```text
Θ(1)
```

indexed-access complexity.

Actual hardware latency may vary.

---

## “Contiguous Memory Means No Runtime Abstraction Exists”

Incorrect.

Java and the JVM manage the memory representation.

The classical contiguous model is an analytical abstraction.

---

## “Cache Locality Changes O(n) to O(1)”

Incorrect.

Cache effects may improve practical constants or observed runtime.

They do not change the asymptotic classification of a full traversal.

---

## “Every Java Multidimensional Array Is One Contiguous Matrix”

Incorrect.

Java multidimensional arrays are arrays whose elements can themselves be array
references.

---

## “Memory Layout Is Only Relevant to Systems Programming”

Incorrect.

Memory organisation influences the performance characteristics of many
software systems.

---

# Best Practices

When reasoning about memory layout:

- distinguish the computational model from physical implementation details;
- understand the relationship between base position and index;
- use contiguous storage as the classical array model;
- distinguish indexed access from traversal;
- consider spatial locality when discussing practical performance;
- do not confuse cache effects with asymptotic complexity;
- distinguish Java's managed memory model from direct-address machine models;
- analyse multidimensional Java arrays according to their actual representation;
- avoid unnecessary copying;
- consider data layout when performance is genuinely relevant.

The objective is to understand **why arrays behave efficiently for certain
access patterns and what trade-offs their organisation introduces**.

---

# Systematic Analysis

When studying an array operation, ask:

```text
1. What is the input size?
        ↓
2. Is the target position already known?
        ↓
3. What memory operation is required?
        ↓
4. Does the algorithm access one position or many?
        ↓
5. Are elements shifted or copied?
        ↓
6. Is additional memory allocated?
        ↓
7. Does sequential locality affect practical performance?
        ↓
8. What is the theoretical complexity?
        ↓
9. What implementation-specific factors may affect runtime?
```

This sequence connects memory reasoning with the methodology established in
`docs/02-complexity/`.

---

# Relationship with Later Array Documents

The memory model introduced here supports the following documents:

```text
Memory Layout
        ↓
Java Arrays
        ↓
Multidimensional Arrays
        ↓
Array Operations
        ↓
Traversal Patterns
        ↓
Common Algorithms
        ↓
Complexity Analysis
```

The next document translates the abstract and memory models into concrete Java
language behaviour.

---

# Relationship with Later Repository Modules

Memory-layout concepts will reappear when studying:

```text
Searching
        ↓
Sequential and Indexed Access
```

```text
Sorting
        ↓
Element Movement and Locality
```

```text
Heaps
        ↓
Array-Based Structural Representation
```

```text
Hash Tables
        ↓
Indexed Storage
```

```text
Graphs
        ↓
Adjacency Matrices and Array-Based Structures
```

```text
Dynamic Programming
        ↓
Array-Based State Storage
```

Understanding memory layout therefore contributes to the broader study of
algorithm and data-structure performance.

---

# Expected Outcome

After completing this document, the learner should be able to connect the
abstract array structure with its classical memory model:

```text
Array
        ↓
Contiguous Positions
        ↓
Predictable Address Relationship
        ↓
Direct Indexed Access
        ↓
Θ(1)
```

and:

```text
Sequential Traversal
        ↓
Process n Elements
        ↓
Θ(n)
```

The learner should also understand that practical performance depends on more
than asymptotic complexity:

```text
Algorithmic Model
        +
Memory Organisation
        +
Runtime
        +
Hardware
        ↓
Observed Performance
```

This distinction is essential for connecting Computer Science theory with
professional software engineering.

---

# Key Takeaways

After studying this document, the learner should remember that:

- memory can be modelled as a collection of addressable locations;
- the classical array model stores elements in contiguous positions;
- an element's position can be derived from a base position, index, and element
  size in the simplified address model;
- this organisation explains efficient indexed access;
- indexed access is `Θ(1)` under the standard computational model;
- traversing all `n` elements is `Θ(n)`;
- copying or shifting a linear number of elements is typically `Θ(n)`;
- spatial locality can improve practical sequential-access performance;
- cache behaviour affects runtime constants rather than changing asymptotic
  growth;
- Java abstracts low-level memory management through the JVM;
- Java multidimensional arrays require separate consideration because their
  representation differs from a single flat matrix;
- memory layout is one contributor to practical performance, not a complete
  runtime model;
- theoretical complexity and empirical hardware behaviour must remain distinct.

Memory layout therefore provides the bridge between the abstract definition of
an array and its computational behaviour.

It connects **data structures**, **computer memory**, **algorithmic complexity**,
and **practical performance**, establishing the foundation for the Java-specific
array concepts developed in the next document.

---

# Next Document

```text
03-java-arrays.md
```

The next document introduces the concrete Java representation of arrays.

It covers:

- declaration;
- creation;
- initialisation;
- default values;
- `length`;
- primitive arrays;
- reference arrays;
- bounds checking;
- `ArrayIndexOutOfBoundsException`;
- array references;
- copying and assignment behaviour.

The next central question becomes:

> **How does Java represent and manage arrays while preserving the computational
> properties introduced by the abstract array model?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

The conceptual treatment of array organisation, memory access, and algorithmic
performance is complemented by official Java documentation for Java-specific
array behaviour.

Additional academic, technical, and implementation references are documented
in:

```text
docs/00-project/10-references.md
```

The distinction between the classical array memory model and concrete JVM
implementation details should be preserved throughout the remainder of the
module.