# Sorting Fundamentals

## Purpose

Sorting is the computational process of arranging the elements of a collection according to a defined ordering criterion.

Although sorting is often introduced through simple numerical examples, it is a fundamental operation in many software systems.

Applications frequently need to organise:

- products by price;
- employees by surname;
- transactions by date;
- students by academic performance;
- files by size;
- messages by creation time.

Sorting transforms an unordered collection into a structured representation that is easier to search, analyse, display and process.

This document introduces the fundamental concepts required to understand sorting algorithms before studying their formal specification, implementation and computational complexity.

---

## Learning Objectives

After completing this document, the learner should be able to:

- explain what sorting means;
- distinguish ascending and descending order;
- identify the sorting key of a collection;
- understand the role of comparison operations;
- distinguish natural ordering from custom ordering;
- explain how duplicate values affect sorting;
- recognise common applications of sorting;
- understand the relationship between sorting and searching;
- identify the main decisions involved in a sorting problem.

---

## What Is Sorting?

Sorting is the process of rearranging the elements of a collection so that they follow a defined order.

Consider the following collection:

```text
[24][7][18][3][15]
```

The values are not arranged according to a recognised numerical order.

After sorting in ascending order:

```text
[3][7][15][18][24]
```

After sorting in descending order:

```text
[24][18][15][7][3]
```

The elements remain the same.

Only their positions change.

---

## Sorting as a Transformation

Sorting may be represented as a transformation from an unordered sequence to an ordered sequence.

```text
Unordered Collection

[24][7][18][3][15]

        ↓

Sorting Algorithm

        ↓

Ordered Collection

[3][7][15][18][24]
```

The algorithm must preserve the elements while modifying their arrangement.

This leads to two fundamental requirements:

```text
Correct Order
        +
Same Elements
        =
Valid Sorting Result
```

---

## Ascending Order

Ascending order arranges elements from the smallest value to the largest value.

Example:

```text
Input

[9][2][7][1][5]

↓

Ascending Order

[1][2][5][7][9]
```

For a numerical collection, the ordering condition is:

```text
A[0] ≤ A[1] ≤ A[2] ≤ ... ≤ A[n − 1]
```

Each element must be less than or equal to the element that follows it.

---

## Descending Order

Descending order arranges elements from the largest value to the smallest value.

Example:

```text
Input

[9][2][7][1][5]

↓

Descending Order

[9][7][5][2][1]
```

For a numerical collection, the ordering condition is:

```text
A[0] ≥ A[1] ≥ A[2] ≥ ... ≥ A[n − 1]
```

Each element must be greater than or equal to the element that follows it.

---

## Ordering Criterion

A sorting algorithm requires a rule that determines whether one element should appear before another.

This rule is called the **ordering criterion**.

For numbers, the criterion may be:

```text
Smaller values appear first.
```

For dates:

```text
Earlier dates appear first.
```

For products:

```text
Lower prices appear first.
```

For employees:

```text
Surnames are compared alphabetically.
```

The algorithm does not define the business meaning of the order.

It applies the criterion provided by the problem.

---

## Sorting Keys

A **sorting key** is the value or attribute used to determine the position of an element.

For primitive values, the element itself may be the key.

Example:

```text
Collection

[18][4][27][9]

Sorting Key

Numerical value
```

For objects, one property is commonly selected as the key.

Example:

```text
Product
├── id
├── name
├── category
├── price
└── stock
```

The same product collection may be sorted by:

- identifier;
- name;
- category;
- price;
- available stock.

---

## Example: Sorting Objects

Consider the following products:

```text
Product A
Name: Keyboard
Price: 80

Product B
Name: Monitor
Price: 250

Product C
Name: Mouse
Price: 35
```

Sorting by price produces:

```text
Mouse       35
Keyboard    80
Monitor    250
```

Sorting by name produces a different arrangement:

```text
Keyboard    80
Monitor    250
Mouse       35
```

The collection is the same.

The ordering key changes.

---

## Primary and Secondary Keys

Some sorting problems require more than one criterion.

Consider a collection of employees.

The primary key may be:

```text
Department
```

The secondary key may be:

```text
Surname
```

Example:

```text
Engineering — Alvarez
Engineering — Gómez
Finance     — López
Finance     — Romero
```

Conceptually:

```text
Compare Department

↓

If Departments Are Equal

↓

Compare Surname
```

This form of ordering is common in reports, databases and enterprise applications.

---

## Natural Ordering

Natural ordering is the default order associated with a type.

Examples include:

```text
Integer

1 < 2 < 3 < 4
```

```text
LocalDate

Earlier Date < Later Date
```

```text
String

Compared according to its defined character ordering
```

In Java, a type may define its natural ordering by implementing the `Comparable` interface.

Conceptually:

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

In this example, the natural order of students is determined by their registration number.

---

## Custom Ordering

Natural ordering is not always sufficient.

An application may need to order the same objects in several different ways.

Custom ordering allows the comparison rule to be defined independently from the class.

In Java, this is commonly represented through `Comparator`.

Example:

```java
Comparator<Product> byPrice =
        Comparator.comparing(Product::price);
```

Descending price:

```java
Comparator<Product> byPriceDescending =
        Comparator.comparing(Product::price).reversed();
```

Multiple criteria:

```java
Comparator<Employee> byDepartmentAndSurname =
        Comparator.comparing(Employee::department)
                  .thenComparing(Employee::surname);
```

Custom comparators allow sorting behaviour to reflect different application requirements.

---

## Comparison Operations

Comparison-based sorting algorithms determine the order of elements by comparing pairs of values.

A comparison answers a question such as:

```text
Is A smaller than B?
```

```text
Should A appear before B?
```

```text
Are A and B equivalent according to the sorting key?
```

A comparison generally produces one of three logical outcomes:

```text
A < B
A = B
A > B
```

These results guide the algorithm when moving, exchanging or partitioning elements.

---

## Comparison Consistency

The comparison rule must behave consistently.

A valid ordering relation should support predictable reasoning.

For example, if:

```text
A appears before B
```

and:

```text
B appears before C
```

then the comparison should also imply:

```text
A appears before C
```

An inconsistent comparator may produce incorrect or unpredictable sorting behaviour.

Example of a problematic criterion:

```text
A is preferred to B based on a changing random value.
```

The comparison result must not change unpredictably while the sorting algorithm is executing.

---

## Duplicate Values

Collections may contain duplicate values or objects with equivalent sorting keys.

Example:

```text
[7][3][7][2][3]
```

Ascending order:

```text
[2][3][3][7][7]
```

Duplicate elements are valid and must be preserved.

A correct sorting algorithm must not remove repeated values.

For object collections, different elements may have the same key.

Example:

```text
Ana   — Grade 90
Laura — Grade 75
María — Grade 90
```

Sorting by grade does not make Ana and María the same object.

It only means that both are equivalent according to the selected key.

---

## Equality and Ordering Equivalence

Two elements may be different objects but equivalent for sorting purposes.

Consider:

```text
Employee A
ID: 101
Department: Engineering
```

```text
Employee B
ID: 204
Department: Engineering
```

When sorting by department:

```text
Employee A = Employee B
```

according to the ordering criterion.

However, they are not necessarily equal according to object identity or `equals`.

Therefore:

```text
Object Equality
```

and:

```text
Ordering Equivalence
```

are related but distinct concepts.

---

## Stable Ordering

When multiple elements have equivalent keys, their relative order may be important.

Initial collection:

```text
Ana   — Grade 90
Laura — Grade 75
María — Grade 90
```

Stable sorting by grade:

```text
Laura — Grade 75
Ana   — Grade 90
María — Grade 90
```

Ana remains before María.

An unstable algorithm may produce:

```text
Laura — Grade 75
María — Grade 90
Ana   — Grade 90
```

Both results are correctly ordered by grade.

Only the first preserves the original relative order of equivalent elements.

Stability will be studied in detail in:

```text
03-sorting-properties.md
```

---

## Complete Sorting

Complete sorting arranges every element in the collection.

Example:

```text
Input

[8][2][6][1][9]

↓

Complete Sorting

[1][2][6][8][9]
```

This is the standard sorting problem studied in this module.

---

## Partial Sorting

Some problems do not require a fully sorted collection.

Examples include:

- finding the five highest scores;
- identifying the ten cheapest products;
- locating the median;
- placing the smallest element first;
- separating values around a pivot.

Conceptually:

```text
Complete Sorting

All elements are ordered.
```

```text
Partial Sorting

Only the required region or elements are organised.
```

Partial sorting may avoid unnecessary work when the complete order is not required.

---

## Sorting and Selection

Sorting is closely related to selection problems.

Examples include:

- smallest element;
- largest element;
- second-largest element;
- median value;
- k-th smallest element;
- top-k values.

A complete sorting algorithm can solve these problems.

However, specialised selection algorithms may solve them more efficiently without ordering the entire collection.

This distinction becomes important in algorithm design.

---

## Sorting and Searching

Sorting often improves future search operations.

Consider an unsorted collection with `n` elements.

A single Linear Search may require:

```text
O(n)
```

If the collection is sorted, Binary Search may require:

```text
O(log n)
```

However, sorting itself has a computational cost.

Conceptually:

```text
Unsorted Data
        ↓
Sorting Cost
        ↓
Ordered Data
        ↓
Faster Repeated Searches
```

The engineering decision depends on how the data is used.

---

## One Search versus Repeated Searches

Suppose a collection is unsorted.

For a single search:

```text
Linear Search
```

may be preferable because sorting first could introduce unnecessary work.

For many repeated searches:

```text
Sort Once
        ↓
Apply Binary Search Repeatedly
```

may become more efficient.

The decision depends on:

- collection size;
- number of searches;
- frequency of modifications;
- required response time;
- cost of maintaining order.

---

## Sorting and Data Presentation

Sorting is frequently used to improve the way information is presented to users.

Examples include:

- newest messages first;
- products from lowest to highest price;
- employees alphabetically;
- tasks ordered by priority;
- transactions ordered chronologically;
- search results ordered by relevance.

In these cases, sorting is not only an algorithmic requirement.

It is also part of the user experience.

---

## Sorting and Reporting

Reports frequently require predictable ordering.

Examples:

```text
Monthly Sales Report

↓

Order by Date
```

```text
Employee Report

↓

Order by Department and Surname
```

```text
Inventory Report

↓

Order by Category and Product Name
```

Ordered reports are easier to review, compare and validate.

---

## Sorting and Databases

Database queries often include ordering operations.

Conceptually:

```sql
SELECT *
FROM products
ORDER BY price ASC;
```

or:

```sql
SELECT *
FROM employees
ORDER BY department ASC, surname ASC;
```

Although database systems manage their own internal algorithms and optimisation strategies, the conceptual problem remains the same:

```text
Define the Key

↓

Define the Direction

↓

Arrange the Results
```

---

## Sorting in Backend Systems

Backend applications frequently sort data before returning it to clients.

Examples include:

- API responses ordered by creation date;
- paginated product catalogues;
- ranking systems;
- transaction histories;
- administrative reports;
- audit records;
- notification feeds.

The backend must determine:

- which field controls the order;
- whether the direction is ascending or descending;
- how duplicate keys are handled;
- whether sorting occurs in memory or in the database;
- whether the complete collection must be loaded.

Sorting decisions can therefore affect performance, memory usage and system architecture.

---

## Sorting in Memory

When all data fits in main memory, sorting may be performed directly on arrays or collections.

Example:

```text
Application Memory

↓

Load Collection

↓

Sort Collection

↓

Process Results
```

This is known broadly as **internal sorting**.

The algorithms studied in this module primarily belong to this category.

---

## Sorting Large Datasets

When a dataset is too large to fit entirely in memory, external storage may be required.

Conceptually:

```text
Large Dataset

↓

Divide into Manageable Parts

↓

Sort Individual Parts

↓

Store Intermediate Results

↓

Merge Results
```

This is known as **external sorting**.

External sorting is outside the main implementation scope of this module, but Merge Sort provides important conceptual foundations for understanding it.

---

## In-Place and Out-of-Place Sorting

Some algorithms modify the original collection directly.

```text
Original Collection

↓

Rearranged in the Same Structure
```

Other approaches create an additional collection.

```text
Original Collection
        +
Auxiliary Collection
        ↓
Sorted Result
```

These strategies influence:

- memory usage;
- API design;
- mutability;
- performance;
- maintainability.

The distinction will be analysed further in the Sorting Properties document.

---

## Sorting Mutable Collections

An in-place algorithm changes the positions of elements in the original collection.

Before:

```text
[9][3][7][1]
```

After:

```text
[1][3][7][9]
```

The same collection instance has been modified.

This behaviour should be clearly documented because callers may depend on the original order.

---

## Sorting Immutable Data

Some software designs avoid changing the original collection.

Instead, they create a new ordered result.

Conceptually:

```text
Original

[9][3][7][1]

↓

Copy and Sort

↓

Result

[1][3][7][9]
```

The original remains unchanged.

This approach may improve predictability, but it generally requires additional memory.

---

## Comparison-Based Sorting

The principal algorithms studied in this module are comparison-based.

They determine order through operations such as:

```text
Compare A and B
```

```text
Move A before B
```

```text
Exchange A and B
```

```text
Partition values around a pivot
```

The algorithms include:

- Selection Sort;
- Bubble Sort;
- Insertion Sort;
- Merge Sort;
- Quick Sort.

---

## Non-Comparison Sorting

Some sorting algorithms use properties of the keys rather than comparing elements directly.

Examples include:

- Counting Sort;
- Radix Sort;
- Bucket Sort.

These algorithms may achieve different complexity bounds under specific assumptions.

They are not the primary focus of this module because they require additional constraints on the input representation.

They may be studied later as specialised sorting techniques.

---

## Sorting Direction

The same algorithm can often support both ascending and descending order.

The difference lies in the comparison criterion.

Ascending:

```text
Place smaller values first.
```

Descending:

```text
Place larger values first.
```

Conceptually:

```java
Comparator<Integer> ascending =
        Integer::compare;
```

```java
Comparator<Integer> descending =
        Comparator.reverseOrder();
```

Separating the sorting algorithm from the comparison rule improves reuse.

---

## Algorithm versus Ordering Policy

A useful software design distinction is:

```text
Sorting Algorithm
```

determines how elements are rearranged.

```text
Ordering Policy
```

determines which element should appear first.

For example:

```text
Merge Sort
        +
Order Products by Price
```

or:

```text
Merge Sort
        +
Order Products by Name
```

The strategy remains the same.

The comparison policy changes.

This distinction supports flexible and reusable software design.

---

## Fundamental Sorting Decisions

Before selecting or implementing an algorithm, several questions must be answered.

```text
What must be ordered?
```

```text
What key determines the order?
```

```text
Ascending or descending?
```

```text
Can duplicate keys exist?
```

```text
Must equivalent elements preserve their relative order?
```

```text
Can the original collection be modified?
```

```text
How large is the input?
```

```text
How much auxiliary memory is available?
```

```text
Is predictable worst-case performance required?
```

```text
Is the collection already partially ordered?
```

These questions define the sorting problem more precisely.

---

## Common Sorting Scenarios

| Scenario | Ordering Criterion |
|---|---|
| Products in an online catalogue | Price |
| Employees in a report | Department and surname |
| Transactions in an account | Date and time |
| Students in a ranking | Grade |
| Tasks in a project system | Priority and deadline |
| Files in a directory | Name, size or modification date |
| Notifications in an application | Creation time |
| Search results | Relevance score |

The algorithm should be selected only after the requirements of the scenario are understood.

---

## Sorting and Correctness

A visually ordered result is not sufficient to prove correctness.

A valid result must satisfy two essential properties.

### Ordering Property

Every adjacent pair must follow the required comparison rule.

For ascending numerical order:

```text
A[i] ≤ A[i + 1]
```

for every valid index:

```text
0 ≤ i < n − 1
```

### Permutation Property

The result must contain exactly the same elements as the input.

Conceptually:

```text
Input Elements
        =
Output Elements
```

Only the arrangement may change.

---

## Example of an Incorrect Result

Input:

```text
[4][2][7][2]
```

Incorrect output:

```text
[2][4][7]
```

The sequence is ordered, but one duplicate value was lost.

Therefore, the result is not a valid sorting of the original collection.

---

## Example of Another Incorrect Result

Input:

```text
[4][2][7]
```

Incorrect output:

```text
[2][4][4][7]
```

The sequence is ordered, but an additional element was introduced.

Again, the permutation property is violated.

---

## Edge Cases

Sorting algorithms must handle special inputs correctly.

### Empty Collection

```text
[]
```

The empty collection is already sorted.

---

### Single Element

```text
[7]
```

A collection containing one element is already sorted.

---

### Already Sorted Collection

```text
[1][3][5][7]
```

Some algorithms can detect or benefit from existing order.

---

### Reverse-Sorted Collection

```text
[7][5][3][1]
```

This input may represent the worst case for certain algorithms.

---

### Duplicate Values

```text
[4][2][4][1][2]
```

Every occurrence must be preserved.

---

### All Equal Values

```text
[6][6][6][6]
```

The collection is already ordered according to value, although an implementation may still perform comparisons or movements.

---

### Negative Values

```text
[-3][7][-8][2]
```

The comparison logic must handle the complete value domain.

---

## Sorting Is Context-Dependent

No sorting algorithm is universally optimal.

An algorithm may be appropriate in one scenario and unsuitable in another.

Examples:

```text
Small and Nearly Sorted Input

↓

Insertion Sort may be effective.
```

```text
Stable O(n log n) Requirement

↓

Merge Sort may be appropriate.
```

```text
Limited Auxiliary Array Memory

↓

An in-place strategy may be preferred.
```

```text
General In-Memory Data with Good Average Behaviour

↓

Quick Sort may be considered.
```

Algorithm selection requires balancing several properties rather than focusing on a single complexity value.

---

## Sorting in the Java Standard Library

Java provides standard sorting mechanisms for arrays and collections.

Examples include:

```text
Arrays.sort(numbers);
```

```text
list.sort(Comparator.naturalOrder());
```

```text
list.sort(Comparator.comparing(Product::price));
```

```text
list.sort(
        Comparator.comparing(Employee::department)
                  .thenComparing(Employee::surname)
);
```

Professional software should normally use reliable library implementations unless there is a justified need for custom behaviour.

Manual implementations in this repository are educational.

Their purpose is to develop:

- algorithmic reasoning;
- correctness analysis;
- complexity analysis;
- implementation discipline;
- testing skills;
- performance awareness.

---

## Engineering Questions

Before sorting data in a real application, an engineer should ask:

- Should the database perform the sorting?
- Should the application sort the data in memory?
- Is the complete dataset required?
- Can the query be paginated?
- Is the order stable and deterministic?
- What happens when two keys are equal?
- Is the original collection allowed to change?
- Is the comparator consistent?
- How frequently will the sorting operation execute?
- Can previously sorted data be reused?

These questions extend the sorting problem beyond the algorithm itself.

---

## Relationship with Previous Modules

Sorting builds directly on concepts already introduced.

```text
Foundations
        ↓
Problem Definition
Correctness
Preconditions
Postconditions
Loop Invariants
```

```text
Complexity
        ↓
Operation Counting
Growth Rates
Time Complexity
Space Complexity
```

```text
Arrays
        ↓
Indexed Access
Traversal
Element Movement
```

```text
Searching
        ↓
Ordered Data
Binary Search
Repeated Queries
```

```text
Sorting Fundamentals
        ↓
Current Document
```

---

## Relationship with Future Documents

The concepts introduced here will be formalised and expanded throughout the module.

```text
Sorting Fundamentals
        ↓
Sorting Problem Definition
        ↓
Sorting Properties
        ↓
Elementary Sorting Algorithms
        ↓
Efficient Sorting Algorithms
        ↓
Complexity Analysis
        ↓
Algorithm Comparison
```

The next document will define the sorting problem more precisely using input, output, preconditions, postconditions and correctness requirements.

---

## Key Takeaways

Sorting arranges the elements of a collection according to a defined ordering criterion.

The result must:

- satisfy the required order;
- preserve every input element;
- introduce no additional elements;
- remove no existing elements.

Sorting may use:

- natural ordering;
- custom ordering;
- one key;
- multiple keys;
- ascending order;
- descending order.

The selected algorithm depends on the characteristics of the problem, including:

- input size;
- available memory;
- data distribution;
- stability requirements;
- mutability requirements;
- existing order;
- performance expectations.

Sorting is closely connected with searching, reporting, ranking, data presentation and backend processing.

Understanding these fundamentals is necessary before analysing any individual sorting algorithm.

---

## Next Document

```text
02-sorting-problem-definition.md
```

The next document formalises the computational sorting problem by defining its input, output, ordering relation, preconditions, postconditions, permutation requirement and correctness conditions.