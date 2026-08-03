# Sorting Properties

## Purpose

Sorting algorithms do not differ only in the sequence of operations they perform.

They also possess properties that influence:

- execution time;
- memory usage;
- correctness guarantees;
- behaviour with duplicate values;
- suitability for partially ordered data;
- scalability;
- practical use in software systems.

Two algorithms may produce the same ordered result while behaving very differently.

For example:

- one may preserve the relative order of equivalent elements;
- another may reorder them;
- one may require additional memory;
- another may modify the collection in place;
- one may benefit from nearly sorted input;
- another may perform the same amount of work regardless of the initial order.

This document introduces the main properties used to classify and compare sorting algorithms.

These properties will later support the analysis of Selection Sort, Bubble Sort, Insertion Sort, Merge Sort and Quick Sort.

---

## Learning Objectives

After completing this document, the learner should be able to:

- explain what sorting stability means;
- distinguish stable and unstable sorting algorithms;
- explain in-place sorting;
- distinguish in-place and out-of-place strategies;
- describe adaptive sorting behaviour;
- distinguish internal and external sorting;
- explain comparison-based sorting;
- understand natural and custom ordering;
- identify the role of auxiliary space;
- compare algorithms using properties beyond time complexity;
- recognise which properties matter in software engineering scenarios.

---

## Why Sorting Properties Matter

A sorting algorithm cannot be selected correctly by looking only at its asymptotic execution time.

Consider two algorithms with the same time complexity.

One may:

- preserve equivalent elements;
- use constant auxiliary space;
- perform few data movements;
- behave well on nearly sorted input.

The other may:

- reorder equivalent elements;
- require additional memory;
- perform many exchanges;
- ignore existing order.

Therefore:

```text
Time Complexity
        +
Space Complexity
        +
Stability
        +
Adaptability
        +
Data Characteristics
        =
Informed Algorithm Selection
```

Sorting properties provide a more complete engineering perspective.

---

# Stability

A sorting algorithm is **stable** when it preserves the original relative order of elements that are equivalent according to the selected comparison key.

Consider the following records:

```text
Ana     — Grade 90
Laura   — Grade 75
María   — Grade 90
```

The records for Ana and María have equivalent sorting keys because both grades are `90`.

A stable sort by grade produces:

```text
Laura   — Grade 75
Ana     — Grade 90
María   — Grade 90
```

Ana remains before María because she appeared first in the original collection.

---

## Stable Sorting Example

Initial collection:

```text
[Ana, 90]
[Laura, 75]
[María, 90]
[Clara, 80]
```

Stable ascending sort by grade:

```text
[Laura, 75]
[Clara, 80]
[Ana, 90]
[María, 90]
```

The relative order of Ana and María is preserved.

---

## Unstable Sorting Example

An unstable algorithm may produce:

```text
[Laura, 75]
[Clara, 80]
[María, 90]
[Ana, 90]
```

The output is still correctly ordered by grade.

However, the original relative order of equivalent elements has changed.

---

## Stability Is Key-Dependent

Stability is evaluated according to the selected sorting key.

Consider:

```text
Employee A
Department: Engineering
Surname: Alvarez
```

```text
Employee B
Department: Engineering
Surname: Gómez
```

If the collection is sorted only by department, both employees are equivalent according to the criterion.

A stable algorithm preserves their original order.

If the collection is sorted by department and surname, they are no longer equivalent because the secondary key distinguishes them.

Therefore:

```text
Equivalent Elements
```

means:

```text
Elements considered equal by the comparison criterion
```

not necessarily:

```text
Identical objects
```

---

## Why Stability Matters

Stability is especially important when sorting is performed in multiple stages.

Suppose a collection of employees is first sorted by surname:

```text
Alvarez
Gómez
López
Romero
```

Then the same collection is sorted by department using a stable algorithm.

Employees belonging to the same department preserve their previous surname order.

Conceptually:

```text
Sort by Secondary Key

↓

Stable Sort by Primary Key

↓

Correct Multi-Level Order
```

This technique depends on stability.

---

## Stability in Business Systems

Stability may be required in:

- reports;
- ranking systems;
- transaction histories;
- user interfaces;
- audit records;
- grouped datasets;
- multi-criteria ordering.

Example:

```text
Orders sorted by creation time

↓

Stable sort by status
```

Orders with the same status retain their chronological order.

Without stability, the result may be valid according to the main key but confusing to users.

---

## Stability of the Algorithms in This Module

The usual implementations studied in this module have the following properties:

| Algorithm | Stable |
|---|---|
| Selection Sort | No |
| Bubble Sort | Yes |
| Insertion Sort | Yes |
| Merge Sort | Yes |
| Quick Sort | Usually no |

Stability may depend on the exact implementation.

An algorithm that is normally unstable may sometimes be modified to preserve order, although this may increase complexity or memory usage.

---

# In-Place Sorting

An algorithm is commonly described as **in-place** when it rearranges elements within the original collection while using only a limited amount of auxiliary memory.

Example:

```text
Original Array

[8][3][6][1]

↓

In-Place Sorting

[1][3][6][8]
```

The same array is modified.

No second array of comparable size is required.

---

## Auxiliary Memory

Auxiliary memory is additional storage used by the algorithm beyond the input collection.

Examples include:

- temporary variables;
- auxiliary arrays;
- recursion call frames;
- temporary buffers;
- copied collections.

An in-place iterative algorithm may use variables such as:

```text
current
index
temporary
pivot
```

while still requiring only:

```text
O(1)
```

auxiliary space.

---

## In-Place Does Not Mean Zero Memory

Every algorithm uses some memory.

The term in-place does not mean:

```text
No additional memory
```

It generally means:

```text
No additional collection proportional to input size
```

For example, an algorithm may use a fixed number of variables and still be considered in-place.

---

## Out-of-Place Sorting

An out-of-place algorithm requires additional storage proportional to the input size.

Conceptually:

```text
Original Collection
        +
Auxiliary Collection
        ↓
Sorted Result
```

Merge Sort on arrays commonly requires an auxiliary array.

Example:

```text
Input Array     O(n)
Auxiliary Array O(n)
```

This allows efficient merging but increases memory usage.

---

## In-Place Algorithms in This Module

Typical implementations have the following auxiliary-space behaviour:

| Algorithm | Typical Auxiliary Space |
|---|---:|
| Selection Sort | `O(1)` |
| Bubble Sort | `O(1)` |
| Insertion Sort | `O(1)` |
| Merge Sort | `O(n)` |
| Quick Sort | Depends on recursion depth |

Quick Sort rearranges elements within the original array, but recursive calls use stack space.

Its auxiliary-space complexity must therefore consider recursion.

---

## Mutability and In-Place Sorting

An in-place algorithm modifies the original collection.

This creates an important API consideration.

Before:

```text
[7][2][9][4]
```

After:

```text
[2][4][7][9]
```

Any other component holding a reference to the same mutable collection will observe the new order.

Therefore, documentation should clarify whether a method:

- modifies the input;
- returns a new collection;
- preserves the original order.

---

## In-Place Sorting and Defensive Copies

A method may use an in-place algorithm while still preserving the caller's input by creating a copy first.

Conceptually:

```text
Original Collection

↓

Create Copy

↓

Sort Copy In Place

↓

Return Sorted Copy
```

The algorithm itself is in-place on the copy.

However, the complete operation requires:

```text
O(n)
```

additional space because of the copied collection.

This distinction is important in engineering analysis.

---

# Adaptability

A sorting algorithm is **adaptive** when it takes advantage of existing order in the input.

Consider:

```text
[1][2][3][5][4][6][7]
```

The collection is almost sorted.

An adaptive algorithm may perform substantially less work than it would on a random or reverse-sorted collection.

---

## Nearly Sorted Data

Nearly sorted data appears frequently in practical systems.

Examples include:

- newly added records inserted into an ordered list;
- event streams arriving mostly in chronological order;
- reports updated incrementally;
- user interfaces where only a few values change;
- partially processed datasets.

An adaptive algorithm can exploit this structure.

---

## Adaptive Algorithm Example

Insertion Sort performs well when elements are already close to their final positions.

For an already sorted array:

```text
[1][2][3][4][5]
```

Insertion Sort performs a small number of comparisons and no significant shifts.

Its best-case complexity is:

```text
O(n)
```

This is a consequence of adaptive behaviour.

---

## Optimised Bubble Sort

Bubble Sort may also behave adaptively when implemented with an exchange flag.

Conceptually:

```text
Perform Pass

↓

Were Any Exchanges Made?

├── Yes → Continue
└── No  → Stop
```

If the collection is already sorted, the algorithm terminates after one pass.

This produces a best-case complexity of:

```text
O(n)
```

Without this optimisation, Bubble Sort continues performing unnecessary passes.

---

## Non-Adaptive Behaviour

Selection Sort usually performs the same number of comparisons regardless of the initial order.

For:

```text
[1][2][3][4][5]
```

and:

```text
[5][4][3][2][1]
```

Selection Sort still searches the entire unsorted region during each iteration.

Therefore, its comparison count remains:

```text
O(n²)
```

even when the input is already sorted.

---

## Adaptive Behaviour Summary

| Algorithm | Adaptive |
|---|---|
| Selection Sort | No |
| Bubble Sort — optimised | Yes |
| Insertion Sort | Yes |
| Merge Sort — standard | Generally no |
| Quick Sort | Depends on implementation and pivot strategy |

Adaptability is not an absolute property in every implementation.

Optimisations and input-detection mechanisms can affect behaviour.

---

# Comparison-Based Sorting

A sorting algorithm is **comparison-based** when it determines the order of elements by comparing them.

Typical questions include:

```text
Is A less than B?
```

```text
Should A appear before B?
```

```text
Are A and B equivalent?
```

The algorithm uses these results to:

- exchange elements;
- shift elements;
- select values;
- merge sequences;
- partition collections.

---

## Comparison Model

Under the comparison model, the algorithm learns about the order only through comparisons.

Conceptually:

```text
Compare Elements

↓

Receive Ordering Result

↓

Rearrange Elements

↓

Repeat
```

The algorithms studied in this module are comparison-based:

- Selection Sort;
- Bubble Sort;
- Insertion Sort;
- Merge Sort;
- Quick Sort.

---

## Lower Bound for Comparison Sorting

For general comparison-based sorting, the theoretical lower bound is:

```text
Ω(n log n)
```

in the worst case.

This means that no comparison-based algorithm can guarantee asymptotically better than `n log n` comparisons for arbitrary input.

Merge Sort achieves:

```text
O(n log n)
```

in the worst case.

Quick Sort achieves:

```text
O(n log n)
```

on average, but:

```text
O(n²)
```

in the worst case.

The quadratic elementary algorithms do not reach the optimal comparison-based bound.

---

## Why the Lower Bound Matters

The lower bound explains why algorithms such as Merge Sort and Heap Sort are considered asymptotically efficient for general comparison-based sorting.

It also explains why improving a general comparison-based algorithm below:

```text
O(n log n)
```

requires changing the assumptions or computational model.

---

# Non-Comparison Sorting

Non-comparison algorithms use structural properties of the keys instead of relying only on pairwise comparisons.

Examples include:

- Counting Sort;
- Radix Sort;
- Bucket Sort.

These algorithms may achieve:

```text
O(n)
```

or related bounds under specific assumptions.

However, they usually require restrictions such as:

- integer keys;
- limited key ranges;
- known digit representations;
- suitable value distributions.

Therefore, their performance cannot be compared directly with general comparison sorting without considering those assumptions.

---

# Internal Sorting

Internal sorting occurs when the entire dataset fits in main memory.

Conceptually:

```text
Dataset Fits in RAM

↓

Load Collection

↓

Sort In Memory

↓

Use Result
```

The algorithms studied in this module primarily address internal sorting.

Examples include:

- sorting an array;
- sorting a Java `List`;
- ordering objects already loaded by an application;
- arranging small or medium datasets in memory.

---

# External Sorting

External sorting is required when the complete dataset cannot fit in main memory.

The algorithm must use external storage such as:

- files;
- disks;
- distributed storage;
- database pages.

Conceptually:

```text
Large Dataset

↓

Divide into Memory-Sized Blocks

↓

Sort Each Block

↓

Store Sorted Blocks

↓

Merge Blocks
```

External Merge Sort is a common strategy because merging ordered sequences can be performed efficiently in stages.

---

## Internal versus External Sorting

| Property | Internal Sorting | External Sorting |
|---|---|---|
| Primary storage | Main memory | Secondary storage |
| Dataset size | Fits in memory | Exceeds available memory |
| Main performance concern | CPU and memory operations | Input/output operations |
| Typical examples | Quick Sort, Insertion Sort | External Merge Sort |
| Main environment | Application memory | Files, databases, large systems |

External sorting introduces concerns beyond the scope of elementary in-memory algorithms.

These include:

- disk access;
- buffering;
- block size;
- sequential reads;
- distributed processing.

---

# Natural Ordering

Natural ordering is the default order associated with a type.

Examples:

```text
Integer

1 < 2 < 3
```

```text
LocalDate

Earlier date < Later date
```

```text
String

Compared using its defined character order
```

In Java, natural ordering is typically expressed through `Comparable`.

Example:

```java
public final class Student implements Comparable<Student> {

    private final int registrationNumber;

    @Override
    public int compareTo(Student other) {
        return Integer.compare(
                this.registrationNumber,
                other.registrationNumber
        );
    }
}
```

---

# Custom Ordering

Custom ordering defines an alternative comparison policy.

In Java, it is commonly expressed through `Comparator`.

Example:

```java
Comparator<Product> byPrice =
        Comparator.comparing(Product::price);
```

Descending order:

```java
Comparator<Product> byPriceDescending =
        Comparator.comparing(Product::price)
                  .reversed();
```

Multiple keys:

```java
Comparator<Employee> byDepartmentAndSurname =
        Comparator.comparing(Employee::department)
                  .thenComparing(Employee::surname);
```

Custom ordering allows the same algorithm to support different business requirements.

---

# Total and Partial Ordering

A **total order** allows every pair of elements to be compared consistently.

For any two elements `A` and `B`, the relation determines whether:

```text
A < B
```

```text
A = B
```

or:

```text
A > B
```

Comparison-based sorting generally assumes a total ordering criterion.

---

## Partial Ordering

A partial order does not necessarily define a direct order for every pair of elements.

For example, task dependencies may indicate that one task must precede another, while unrelated tasks remain incomparable.

This type of problem is not solved by ordinary comparison sorting.

Instead, it may require algorithms such as:

```text
Topological Sort
```

Topological sorting belongs to graph algorithms and should not be confused with the array-sorting algorithms studied in this module.

---

# Deterministic Ordering

A deterministic sorting result is reproducible.

Given the same input and comparison criterion, the result should be predictable.

Duplicate keys can create ambiguity unless:

- the algorithm is stable;
- a secondary key is defined;
- another deterministic tie-breaking rule is used.

Example:

```text
Sort by Grade

↓

If Grades Are Equal

↓

Sort by Registration Number
```

This produces a complete and predictable order.

---

# Online and Offline Sorting

An **offline** sorting algorithm receives the complete collection before producing the final ordered result.

Most algorithms studied in this module follow this model.

```text
Complete Input Available

↓

Sort

↓

Return Result
```

An **online** process handles elements as they arrive.

Conceptually:

```text
Receive New Element

↓

Insert into Ordered Position

↓

Maintain Order
```

Insertion-based strategies can support this idea, although specialised data structures may be more appropriate for continuous data streams.

---

# Recursive and Iterative Sorting

Sorting algorithms may also be classified by control structure.

### Iterative Algorithms

Common iterative algorithms include:

- Selection Sort;
- Bubble Sort;
- Insertion Sort.

They primarily use loops.

### Recursive Algorithms

Common recursive algorithms include:

- Merge Sort;
- Quick Sort.

They solve smaller subproblems through recursive calls.

The distinction affects:

- reasoning style;
- stack usage;
- base cases;
- implementation complexity;
- auxiliary-space analysis.

---

# Data Movement

Sorting cost is not determined only by comparisons.

Algorithms may also perform:

- exchanges;
- assignments;
- shifts;
- copies;
- merges.

For large objects or expensive storage operations, data movement may become a significant cost.

---

## Exchange-Based Algorithms

Bubble Sort commonly performs many adjacent exchanges.

Conceptually:

```text
Compare

↓

Swap

↓

Compare

↓

Swap
```

Each swap may require several assignments.

---

## Shift-Based Algorithms

Insertion Sort often shifts elements instead of repeatedly exchanging them.

Conceptually:

```text
Save Current Element

↓

Shift Larger Elements

↓

Insert Saved Element
```

This may reduce the number of assignments compared with repeated swaps.

---

## Selection Sort and Write Cost

Selection Sort performs many comparisons but relatively few exchanges.

During each outer iteration, it usually performs at most one swap.

This property may be useful when:

- comparisons are inexpensive;
- writing is expensive;
- input size is small.

Therefore, operation type matters in practical evaluation.

---

# Stability and Data Movement

An implementation may lose stability because of how elements are moved.

Example:

```text
[A₁][B][A₂]
```

where `A₁` and `A₂` have equivalent keys.

Exchanging distant elements may change their relative order.

Stable algorithms must move equivalent elements carefully.

For example, Insertion Sort preserves stability when it shifts only elements that are strictly greater than the current value.

Conceptually:

```text
Shift while previous > current
```

not:

```text
Shift while previous ≥ current
```

The comparison detail directly affects stability.

---

# Sorting Property Comparison

The algorithms in this module can be summarised initially as follows.

| Algorithm | Stable | In Place | Adaptive | Comparison-Based |
|---|---|---|---|---|
| Selection Sort | No | Yes | No | Yes |
| Bubble Sort — optimised | Yes | Yes | Yes | Yes |
| Insertion Sort | Yes | Yes | Yes | Yes |
| Merge Sort | Yes | No for typical arrays | No in standard form | Yes |
| Quick Sort | Usually no | Mostly, excluding stack | Depends | Yes |

This table reflects common implementations.

Alternative implementations may exhibit different properties.

---

# Algorithm Selection by Property

Different requirements favour different algorithms.

## Stability Required

Possible choice:

```text
Merge Sort
```

or:

```text
Insertion Sort
```

depending on input size and other constraints.

---

## Very Small Collection

Possible choice:

```text
Insertion Sort
```

because its implementation is simple and its constant factors are low.

---

## Nearly Sorted Collection

Possible choice:

```text
Insertion Sort
```

or optimised:

```text
Bubble Sort
```

although Insertion Sort is generally more practical.

---

## Predictable Worst-Case Performance

Possible choice:

```text
Merge Sort
```

because its worst-case time is:

```text
O(n log n)
```

---

## Limited Auxiliary Array Memory

Possible choices include:

```text
Selection Sort
Insertion Sort
Quick Sort
```

with careful consideration of recursion stack usage.

---

## Few Writes Desired

Possible choice:

```text
Selection Sort
```

because it generally performs fewer swaps than Bubble Sort.

---

# Property Trade-Offs

Improving one property may worsen another.

Example:

```text
Merge Sort
```

provides:

- stability;
- predictable `O(n log n)` time.

However, array implementations generally require:

```text
O(n)
```

auxiliary space.

Quick Sort often provides:

- good average performance;
- locality of reference;
- in-place partitioning.

However, it usually lacks:

- stability;
- guaranteed `O(n log n)` worst-case behaviour.

Algorithm selection is therefore based on trade-offs.

---

# Java Engineering Perspective

Professional Java code normally uses standard sorting APIs rather than handwritten algorithms.

Examples:

```java
Arrays.sort(numbers);
```

```java
employees.sort(
        Comparator.comparing(Employee::department)
                  .thenComparing(Employee::surname)
);
```

Even when using library implementations, engineers must still understand sorting properties.

Important questions include:

- Is the operation stable?
- Does it modify the original collection?
- Is the comparator consistent?
- How much memory may be required?
- What happens with duplicate keys?
- Is the ordering deterministic?
- Should sorting occur in the database instead?

Understanding algorithmic properties supports correct API usage.

---

# Comparator Requirements

A comparison rule should be consistent and predictable.

Conceptually, it should satisfy properties such as:

### Antisymmetric Direction

If:

```text
A < B
```

then:

```text
B > A
```

### Transitivity

If:

```text
A < B
```

and:

```text
B < C
```

then:

```text
A < C
```

### Consistent Equivalence

If two elements compare as equivalent, that relationship should remain consistent while sorting is performed.

An invalid comparator can cause:

- unpredictable results;
- incorrect ordering;
- runtime failures in library algorithms;
- difficult-to-reproduce defects.

---

# Sorting in Backend Applications

Backend systems must often decide where and how sorting should occur.

Possible locations include:

```text
Database
```

```text
Application Memory
```

```text
Client Interface
```

Each option has consequences.

Sorting in the database may:

- reduce transferred data;
- use indexes;
- support pagination;
- avoid loading the entire collection.

Sorting in application memory may be appropriate when:

- the dataset is already loaded;
- custom business logic is required;
- the collection is small;
- multiple local transformations are necessary.

The decision extends beyond the sorting algorithm itself.

---

# Sorting and Pagination

Sorting must be deterministic when combined with pagination.

Consider ordering records only by creation date.

If several records share the same timestamp, their relative order may vary between requests.

A secondary key can provide deterministic ordering.

Example:

```text
Order by Creation Date

↓

Then by Unique Identifier
```

Conceptually:

```sql
ORDER BY created_at DESC, id DESC
```

This is an example of sorting properties influencing system correctness.

---

# Sorting and Immutability

An API may choose between:

```text
Sort Original Collection
```

and:

```text
Return New Sorted Collection
```

Modifying the original collection may be efficient but creates side effects.

Returning a new collection may improve predictability but requires additional memory.

The appropriate design depends on:

- ownership of the data;
- API expectations;
- concurrency concerns;
- performance constraints;
- project conventions.

---

# Testing Sorting Properties

Tests should verify more than whether values are ordered.

Different properties require specific test strategies.

---

## Testing Correct Order

Example:

```text
Input

[5][1][4][2]

Expected

[1][2][4][5]
```

---

## Testing the Permutation Property

Verify that:

- collection size is preserved;
- duplicate frequencies remain unchanged;
- no elements are introduced;
- no elements disappear.

---

## Testing Stability

Use objects with:

- equal sorting keys;
- different identities;
- known initial order.

Example:

```text
A — Key 10
B — Key 5
C — Key 10
```

Stable result:

```text
B — Key 5
A — Key 10
C — Key 10
```

---

## Testing In-Place Behaviour

When required, verify whether:

- the original array is modified;
- a new collection is returned;
- object identity is preserved.

---

## Testing Adaptability

Compare behaviour for:

```text
Already Sorted Input
```

```text
Nearly Sorted Input
```

```text
Reverse-Sorted Input
```

Operation counters or benchmarks may reveal whether the implementation benefits from existing order.

---

# Benchmarking Sorting Properties

Benchmarking can evaluate practical effects that complexity tables do not fully express.

Possible measurements include:

- execution time;
- comparison count;
- exchange count;
- shift count;
- allocation rate;
- memory usage;
- recursion depth.

Example scenarios:

```text
Random Data
Already Sorted Data
Nearly Sorted Data
Reverse-Sorted Data
Duplicate-Heavy Data
```

Theoretical properties should guide experiment design.

Experimental results should then be interpreted using the theoretical model.

---

# Relationship with Previous Documents

This document builds upon:

```text
README.md

↓

Module Scope and Methodology
```

```text
01-sorting-fundamentals.md

↓

General Sorting Concepts
Ordering Keys
Natural and Custom Order
```

```text
02-sorting-problem-definition.md

↓

Input
Output
Preconditions
Postconditions
Permutation Property
```

The current document extends the formal problem definition by introducing the properties used to classify possible solutions.

---

# Relationship with Future Documents

The properties introduced here will guide the study of each algorithm.

```text
Sorting Properties
        ↓
Selection Sort
        ↓
Bubble Sort
        ↓
Insertion Sort
        ↓
Merge Sort
        ↓
Quick Sort
```

For every algorithm, the analysis should consider:

- Is it stable?
- Is it in-place?
- Is it adaptive?
- Is it comparison-based?
- What auxiliary space does it require?
- What kind of data movement does it perform?
- How do these properties affect practical use?

---

# Engineering Perspective

Sorting algorithms should not be evaluated using a single measurement.

A professional decision requires understanding the interaction between:

- correctness;
- stability;
- time complexity;
- space complexity;
- adaptability;
- mutation;
- predictability;
- data movement;
- application requirements.

For example:

```text
Fast Average Time
```

does not necessarily imply:

```text
Best Engineering Choice
```

A slightly slower algorithm may be preferable because it offers:

- stable output;
- deterministic behaviour;
- predictable worst-case performance;
- simpler maintenance;
- lower operational risk.

Algorithm properties provide the vocabulary required to explain and justify these decisions.

---

# Key Takeaways

Sorting algorithms differ in more than execution time.

Important properties include:

- stability;
- in-place behaviour;
- auxiliary-space usage;
- adaptability;
- comparison-based operation;
- internal or external execution;
- recursive or iterative structure;
- data-movement cost;
- deterministic ordering.

A stable algorithm preserves the relative order of equivalent elements.

An in-place algorithm rearranges data using limited auxiliary storage, although recursion and defensive copies must still be considered.

An adaptive algorithm benefits from existing order in the input.

Comparison-based sorting has a general lower bound of:

```text
Ω(n log n)
```

in the worst case.

No property should be evaluated in isolation.

Algorithm selection requires balancing theoretical behaviour with software requirements.

---

# Next Document

```text
04-selection-sort.md
```

The next document introduces Selection Sort, an elementary comparison-based algorithm that repeatedly selects the smallest element from the unsorted region and places it in its final position.