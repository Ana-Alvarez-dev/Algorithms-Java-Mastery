# Java Arrays

## Algorithms Java Mastery

This document introduces the concrete representation and use of **arrays in
Java**.

The previous documents established arrays as an abstract computational
data structure and explained the classical memory model associated with
indexed storage.

This document now connects that abstraction with Java's language and platform
features.

**Algorithms Java Mastery** is inspired primarily by *Introduction to
Algorithms* (Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and
Clifford Stein), which provides the principal academic foundation for the
algorithmic reasoning developed throughout this repository.

Java arrays are fundamental because they provide:

- fixed-size storage;
- zero-based indexed access;
- predictable traversal;
- direct element updates;
- a concrete representation over which classical algorithms can be
  implemented.

The central question addressed throughout this document is:

> **How does Java represent arrays, and how should they be used correctly
> while preserving the algorithmic principles established throughout this
> repository?**

---

# Purpose

The purpose of this document is to study arrays as implemented by the Java
language and runtime.

The learner should understand both:

```text
Array Abstraction
        ↓
Java Array Model
        ↓
Java Syntax
        ↓
Array Operations
        ↓
Algorithmic Use
```

The objective is not simply to memorise Java syntax.

It is to understand how Java's array features correspond to the abstract array
model introduced in the previous documents.

---

# Learning Objectives

After studying this document, the learner should be able to:

- declare arrays correctly;
- create arrays using the `new` operator;
- initialise arrays with literals;
- access and modify elements;
- use the `length` field correctly;
- understand zero-based indexing;
- understand Java array references;
- distinguish primitive arrays from reference arrays;
- recognise Java's default array-element values;
- iterate through arrays using appropriate loop constructs;
- copy arrays correctly;
- use common methods from `java.util.Arrays`;
- compare array contents correctly;
- recognise invalid index access;
- distinguish fixed-size arrays from dynamically resizable collections;
- apply Java arrays correctly when implementing algorithms.

These competencies provide the practical Java foundation required by the
remaining documents of the **Arrays** module.

---

# Java Arrays as Objects

In Java, every array is an **object**.

An array object has:

- a component type;
- a fixed length;
- indexed elements;
- runtime type information;
- array-specific behaviour defined by the Java platform.

For example:

```text
int[] numbers;
```

declares a variable capable of referring to an array object whose component type
is `int`.

At this point, no array object has yet been created by the declaration alone.

---

# Declaring an Array

An array variable can be declared using the following syntax:

```text
int[] numbers;
```

Another valid form is:

```text
int numbers[];
```

The first form is generally preferred because it makes the array type clearer:

```text
int[]
```

The declaration specifies the variable type.

It does not by itself create the array object.

---

# Creating an Array

An array object is created using the `new` operator.

Example:

```text
int[] numbers = new int[5];
```

This creates an array with:

```text
Length = 5
```

and valid indexes:

```text
0
1
2
3
4
```

Conceptually:

```text
Index

0   1   2   3   4
↓   ↓   ↓   ↓   ↓

[ ][ ][ ][ ][ ]
```

The array length is fixed after creation.

---

# Declaration and Creation Separately

Declaration and creation may also be written separately:

```text
int[] numbers;

numbers = new int[5];
```

The variable first receives its declared type and later refers to the newly
created array object.

The resulting array still has:

```text
length = 5
```

---

# Array Initialisation with a Literal

Java allows an array to be created and populated using an array initializer.

Example:

```text
int[] numbers = {10, 20, 30, 40, 50};
```

The compiler determines the array length from the number of initializer
elements.

Conceptually:

```text
Index

0    1    2    3    4
↓    ↓    ↓    ↓    ↓

[10][20][30][40][50]
```

Therefore:

```text
numbers.length
```

evaluates to:

```text
5
```

---

# Empty Array

An array may also be created with zero length:

```text
int[] numbers = new int[0];
```

This array contains no elements and therefore has:

```text
length = 0
```

There are no valid element indexes.

Whether an empty array is a valid input depends on the contract of the
algorithm being implemented.

For example:

```text
Find Maximum
        ↓
May require n ≥ 1
```

while:

```text
Count Elements
        ↓
Can naturally accept n = 0
```

---

# Zero-Based Indexing

Java arrays use zero-based indexing.

For an array of length `n`:

```text
Valid Index Range

0 ≤ i < n
```

Therefore:

```text
First Index
        ↓
0
```

and:

```text
Last Index
        ↓
n - 1
```

Example:

```text
int[] numbers = {10, 20, 30};
```

has:

```text
numbers[0] → 10
numbers[1] → 20
numbers[2] → 30
```

The expression:

```text
numbers[3]
```

is invalid.

---

# Accessing an Element

An array element is accessed using its index.

Example:

```text
int value = numbers[2];
```

For:

```text
int[] numbers = {10, 20, 30, 40};
```

the resulting value is:

```text
30
```

Under the standard computational model:

```text
Known Index
        ↓
Indexed Access
        ↓
Θ(1)
```

This reflects the array abstraction established in the previous documents.

---

# Updating an Element

An existing element can be replaced directly:

```text
numbers[1] = 99;
```

For:

```text
[10][20][30][40]
```

the resulting array becomes:

```text
[10][99][30][40]
```

The operation changes the value associated with one existing position.

Under the standard model:

```text
Update Known Index
        ↓
Θ(1)
```

---

# The `length` Field

Every Java array provides the `length` field.

Example:

```text
int size = numbers.length;
```

For an array with five positions:

```text
numbers.length
        ↓
5
```

The array length is a field, not a method.

Correct:

```text
numbers.length
```

Incorrect:

```text
numbers.length()
```

---

# `length` and Valid Indexes

For:

```text
int[] numbers = new int[5];
```

the relationship is:

```text
length = 5
```

and:

```text
valid indexes = 0 through 4
```

The final valid index is therefore:

```text
numbers.length - 1
```

This relationship is fundamental to correct traversal.

---

# Default Values

When a Java array is created, its elements are automatically initialised to
their type's default value.

Common examples include:

| Component Type | Default Value |
|---|---|
| `byte` | `0` |
| `short` | `0` |
| `int` | `0` |
| `long` | `0L` |
| `float` | `0.0f` |
| `double` | `0.0d` |
| `char` | `'\u0000'` |
| `boolean` | `false` |
| Reference type | `null` |

Example:

```text
int[] numbers = new int[3];
```

produces conceptually:

```text
[0][0][0]
```

The language performs this initialisation automatically.

---

# Primitive Arrays

Java supports arrays whose component type is a primitive type.

Examples include:

```text
int[] numbers;
double[] values;
char[] letters;
boolean[] flags;
```

The array positions contain primitive values of the declared component type.

For example:

```text
int[] numbers = {10, 20, 30};
```

contains integer values.

---

# Reference Arrays

Java arrays can also contain references to objects.

Example:

```text
String[] names = new String[3];
```

Conceptually:

```text
Array

[ reference ][ reference ][ reference ]
```

Each position may contain a reference to a `String` object or `null`.

For example:

```text
names[0] = "Ana";
```

The array position refers to the corresponding object.

This distinction becomes important when analysing:

- object identity;
- mutation;
- copying;
- `null` values;
- reference assignment.

---

# Arrays of User-Defined Objects

Java arrays may contain references to application classes.

Example:

```text
Student[] students = new Student[10];
```

This creates:

```text
10 reference positions
```

It does **not** automatically create ten `Student` objects.

Conceptually:

```text
Student[] students
        ↓
[null][null][null]...[null]
```

Individual objects must be created separately:

```text
students[0] = new Student();
```

This distinction is fundamental when working with reference arrays.

---

# Array References

An array variable stores a reference to an array object.

Consider:

```text
int[] numbers = {10, 20, 30};
```

Then:

```text
int[] other = numbers;
```

does not create a second array.

Instead:

```text
numbers ─────┐
             ↓
        [10][20][30]
             ↑
other ───────┘
```

Both variables refer to the same array object.

---

# Reference Assignment Is Not Copying

Consider:

```text
int[] a = {1, 2, 3};
int[] b = a;
```

Changing:

```text
b[0] = 99;
```

also changes what is observed through `a`:

```text
a → [99][2][3]
b → [99][2][3]
```

because both references identify the same array object.

Therefore:

```text
Reference Assignment
        ≠
Array Copy
```

---

# Copying an Array

To create a separate array object, the contents must be copied.

For example:

```text
int[] copy = Arrays.copyOf(numbers, numbers.length);
```

with:

```text
import java.util.Arrays;
```

Conceptually:

```text
Original
        ↓
[10][20][30]

Copy
        ↓
[10][20][30]
```

The two arrays are distinct objects.

Changing one array does not change the other array merely because the arrays were
copied.

---

# Shallow Copy Considerations

For primitive arrays, copying produces independent primitive values.

For reference arrays, copying duplicates the references, not necessarily the
objects referenced by those positions.

Example:

```text
Student[] original = ...;
Student[] copy = Arrays.copyOf(original, original.length);
```

The two arrays are different array objects, but corresponding positions may
refer to the same `Student` objects.

Conceptually:

```text
Array A              Array B

[ref] ─────────────→ [same object]
[ref] ─────────────→ [same object]
```

This is an important distinction when working with mutable objects.

---

# Iterating with a Traditional `for` Loop

A traditional indexed loop provides access to the position.

Example:

```text
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

This approach is appropriate when the algorithm needs:

- the index;
- positional calculations;
- updates based on position;
- comparisons with neighbouring positions.

---

# Iterating with the Enhanced `for` Loop

Java provides the enhanced `for` loop:

```text
for (int number : numbers) {
    System.out.println(number);
}
```

This is useful when the algorithm only needs each element and does not require
the index explicitly.

Conceptually:

```text
Array
        ↓
Element
        ↓
Next Element
        ↓
...
```

The underlying traversal still processes elements sequentially.

---

# When to Prefer Indexed Iteration

Use an indexed loop when the algorithm depends on positions.

Examples:

```text
numbers[i]
numbers[i + 1]
numbers[i - 1]
```

or when the algorithm needs:

```text
Current Index
+
Current Value
```

This becomes especially important in:

- searching;
- sorting;
- two-pointer algorithms;
- sliding-window algorithms;
- array transformations.

---

# When to Prefer Enhanced Iteration

Use the enhanced `for` loop when:

- the index is not required;
- elements are processed sequentially;
- readability is improved by avoiding explicit index management.

Example:

```text
int sum = 0;

for (int number : numbers) {
    sum += number;
}
```

The approach is concise and reduces opportunities for index-related errors.

---

# Searching an Array

The Java language does not automatically search by value.

An algorithm must define how the search is performed.

For an unsorted array, a linear search may inspect elements sequentially.

Conceptually:

```text
A[0]
 ↓
A[1]
 ↓
A[2]
 ↓
...
```

Worst-case time:

```text
Θ(n)
```

This remains consistent with the complexity methodology established earlier.

---

# Sorting an Array

The `java.util.Arrays` utility class provides sorting methods.

Example:

```text
Arrays.sort(numbers);
```

The exact algorithm used depends on the overload, component type, and Java API
implementation.

Therefore, this repository should avoid assuming a single sorting algorithm
for every `Arrays.sort` call.

The important conceptual point is:

```text
Array
        ↓
Standard Library Sorting Operation
```

with the implementation details defined by the Java platform.

---

# Binary Search with `Arrays`

Java also provides:

```text
Arrays.binarySearch(numbers, 40);
```

Binary search assumes that the searched range is sorted according to the
appropriate ordering.

For an appropriate sorted array:

```text
Search Space
        ↓
Repeated Reduction
        ↓
Logarithmic Growth
```

The relevant complexity depends on the overload and search range, but the
standard binary-search model is logarithmic.

---

# Filling an Array

The `Arrays.fill()` utility method can assign the same value to array
positions.

Example:

```text
Arrays.fill(numbers, 0);
```

For:

```text
[5][8][2][9]
```

the result becomes:

```text
[0][0][0][0]
```

Filling all `n` positions requires work proportional to the number of affected
positions:

```text
Fill n Positions
        ↓
Θ(n)
```

---

# Comparing Array Contents

The `==` operator compares references when applied to array variables.

For example:

```text
array1 == array2
```

checks whether the two variables refer to the same array object.

It does not compare the contents element by element.

To compare array contents, use:

```text
Arrays.equals(array1, array2);
```

Conceptually:

```text
Reference Equality
        ≠
Content Equality
```

---

# Copying with `Arrays.copyOf`

Java provides convenient array-copying methods through `java.util.Arrays`.

Example:

```text
int[] copy = Arrays.copyOf(numbers, numbers.length);
```

The resulting array is a different array object.

Copying `n` elements requires work proportional to the copied length:

```text
Θ(n)
```

The method therefore does not provide constant-time duplication of an entire
array.

---

# Other Useful `Arrays` Utilities

The `java.util.Arrays` class also provides operations such as:

```text
Arrays.copyOf(...)
Arrays.copyOfRange(...)
Arrays.fill(...)
Arrays.equals(...)
Arrays.sort(...)
Arrays.binarySearch(...)
```

It also provides additional functionality for:

- deep comparison;
- string conversion;
- parallel processing;
- range-based operations.

The exact API should be consulted when the operation is used in production code.

---

# Arrays and Exceptions

Java performs runtime bounds checking when accessing array elements.

For example:

```text
int[] numbers = new int[5];
```

the expression:

```text
numbers[5]
```

is invalid because the largest valid index is:

```text
4
```

An invalid access results in:

```text
ArrayIndexOutOfBoundsException
```

This runtime checking supports memory safety at the language level.

---

# Bounds and Traversal

The standard Java traversal pattern is:

```text
for (int i = 0; i < numbers.length; i++) {
    process(numbers[i]);
}
```

The condition:

```text
i < numbers.length
```

ensures that valid indexes satisfy:

```text
0 ≤ i < numbers.length
```

Using:

```text
i <= numbers.length
```

would eventually attempt:

```text
numbers[numbers.length]
```

which is invalid.

---

# Null Array Reference

An array variable may contain `null` when it does not refer to an array object.

For example:

```text
int[] numbers = null;
```

Attempting:

```text
numbers.length
```

or:

```text
numbers[0]
```

results in:

```text
NullPointerException
```

The distinction is:

```text
null reference
        ≠
empty array
```

An empty array is an actual array object with:

```text
length = 0
```

---

# Empty Array vs Null Reference

Compare:

```text
int[] a = null;
```

with:

```text
int[] b = new int[0];
```

The first represents:

```text
No Array Object
```

while the second represents:

```text
Existing Array Object
Length = 0
```

These states have different semantics and should not be treated as equivalent
inputs.

---

# Arrays and Method Parameters

Arrays can be passed to methods.

Example:

```text
static int sum(int[] numbers) {
    int total = 0;

    for (int number : numbers) {
        total += number;
    }

    return total;
}
```

The method receives a reference to the array object.

It does not automatically create a copy of the entire array.

Therefore, a method may modify the array's elements if the code is written to do
so.

---

# Mutation Through Methods

Consider:

```text
static void updateFirst(int[] numbers) {
    numbers[0] = 100;
}
```

Calling:

```text
int[] values = {1, 2, 3};
updateFirst(values);
```

changes the same array object:

```text
[100][2][3]
```

This is important when defining method contracts and API behaviour.

---

# Arrays and Method Return Values

Methods can return arrays:

```text
static int[] createValues() {
    return new int[] {1, 2, 3};
}
```

The caller receives a reference to the returned array object.

When exposing mutable arrays from APIs, developers should consider whether
external code should be allowed to modify the underlying structure.

---

# Defensive Copying

If an API must protect internal mutable array state, it may return a copy
instead of the original array.

Conceptually:

```text
Internal Array
        ↓
Defensive Copy
        ↓
External Caller
```

For example:

```text
return Arrays.copyOf(values, values.length);
```

This creates an additional array.

Therefore, defensive copying involves a time and space cost proportional to the
number of copied elements.

---

# Arrays and Immutability

Arrays themselves are mutable objects.

Declaring an array reference as:

```text
final int[] numbers;
```

does not make the array contents immutable.

It means the reference cannot be reassigned after initialisation.

The elements may still be changed:

```text
numbers[0] = 100;
```

provided the array itself remains accessible.

This distinction is important:

```text
final Reference
        ≠
Immutable Array Contents
```

---

# Array Copying and Algorithm Analysis

Different copying strategies have different costs.

For example:

```text
Reference Assignment
        ↓
Θ(1)
```

when only the array reference is assigned:

```text
int[] b = a;
```

while:

```text
Content Copy
        ↓
Θ(n)
```

when all `n` elements are duplicated.

This distinction is critical when analysing algorithm complexity.

---

# Array Utility Methods and Complexity

Common operations can be interpreted through the complexity methodology.

| Operation | Typical Time Behaviour |
|---|---:|
| Indexed access | `Θ(1)` |
| Indexed update | `Θ(1)` |
| `Arrays.fill` over `n` positions | `Θ(n)` |
| Copy `n` elements | `Θ(n)` |
| Content comparison of equal-length arrays | `Θ(n)` |
| Sequential traversal | `Θ(n)` |
| Binary search on sorted range | `Θ(log n)` |
| Sorting | Depends on the API overload and implementation |

The exact complexity claim should always correspond to the specific operation
and input conditions.

---

# Relationship with Complexity

The Java-specific implementation remains connected to the theoretical framework:

```text
Java Operation
        ↓
Algorithmic Work
        ↓
Cost Function
        ↓
Asymptotic Analysis
```

For example:

```text
numbers[index]
```

corresponds conceptually to:

```text
Known Index
        ↓
Direct Access
        ↓
Θ(1)
```

while:

```text
Arrays.copyOf(numbers, numbers.length)
```

requires processing the copied elements:

```text
n Elements
        ↓
Θ(n)
```

---

# Relationship with Memory Layout

The previous document described the classical memory model associated with
arrays.

Java preserves the relevant abstraction:

```text
Indexed Array
        ↓
Predictable Positional Access
        ↓
Efficient Indexed Operations
```

However, the JVM manages the concrete memory representation.

The programmer therefore reasons primarily in terms of:

- indexes;
- references;
- array length;
- component type;
- algorithmic operations.

---

# Relationship with Foundations

The Java implementation must still follow the methodology established in
**Foundations**.

For an array-based algorithm:

```text
Problem
        ↓
Specification
        ↓
Preconditions
        ↓
Postconditions
        ↓
Edge Cases
        ↓
Algorithm
        ↓
Java Implementation
```

For example, a method that computes the maximum value may require:

```text
Precondition
        ↓
Array is not null
        ↓
Array contains at least one element
```

and:

```text
Postcondition
        ↓
Returned value is the maximum element
```

---

# Relationship with Testing

Java arrays provide many useful cases for automated testing.

Examples include:

```text
Empty Array
Single Element
Duplicate Values
Negative Values
Already Ordered
Reverse Ordered
Extreme Numeric Values
```

when these belong to the method's valid input domain.

Testing should derive from:

- specification;
- contracts;
- edge cases;
- expected behaviour.

Testing does not establish asymptotic complexity.

---

# Relationship with Benchmarking

Concrete Java implementations may later be benchmarked when empirical
measurement provides useful information.

For example:

```text
Array Traversal
        ↓
JMH
        ↓
Observed Throughput
```

The benchmark may reveal practical effects such as:

- cache behaviour;
- JVM optimisation;
- allocation overhead;
- implementation differences.

It does not replace theoretical analysis.

---

# Common Misconceptions

## “Declaration Creates the Array”

Incorrect.

```text
int[] numbers;
```

declares a variable.

The array object is created separately:

```text
numbers = new int[5];
```

unless declaration and creation are combined.

---

## “`length` Is a Method”

Incorrect.

Correct:

```text
numbers.length
```

---

## “Assigning One Array to Another Copies It”

Incorrect.

```text
int[] b = a;
```

copies the reference.

It does not duplicate the array contents.

---

## “`==` Compares Array Contents”

Incorrect.

It compares references.

Use:

```text
Arrays.equals(a, b)
```

for content equality.

---

## “`final` Makes the Array Immutable”

Incorrect.

It prevents reassignment of the reference, not modification of elements.

---

## “Arrays Resize Automatically”

Incorrect.

Java arrays have fixed length.

Dynamic growth requires another object or a dynamic collection such as
`ArrayList`.

---

## “Object Arrays Store Complete Objects Directly”

Not in the general Java reference-array model.

The array positions contain references to objects.

---

## “`Arrays.binarySearch` Works on Any Array”

The searched range must satisfy the ordering assumptions required by the method.

An unsorted range does not provide the required binary-search semantics.

---

## “All `Arrays.sort` Calls Use the Same Algorithm”

Incorrect.

The implementation depends on the overload, component type, and Java platform
implementation.

---

# Best Practices

When working with Java arrays:

- distinguish declaration from object creation;
- use zero-based indexes correctly;
- use `length` as a field;
- validate contracts before indexing;
- use enhanced `for` loops when the index is unnecessary;
- use indexed loops when positional reasoning is required;
- distinguish reference assignment from copying;
- use `Arrays.equals` for content comparison;
- use `Arrays.copyOf` or related methods for explicit copying;
- use standard library utilities rather than rewriting common operations
  unnecessarily;
- document mutability and ownership expectations in APIs;
- consider defensive copies when exposing mutable internal state;
- analyse the cost of copying and sorting;
- preserve the distinction between algorithmic complexity and runtime
  behaviour.

The objective is to use Java arrays correctly while retaining the theoretical
reasoning established throughout the repository.

---

# Systematic Java Array Analysis

When implementing an array-based solution in Java, ask:

```text
1. What is the abstract data structure?
        ↓
2. What does the array length represent?
        ↓
3. Which indexes are valid?
        ↓
4. Is the array guaranteed to be non-null?
        ↓
5. Is an empty array valid?
        ↓
6. Is the algorithm reading or modifying the array?
        ↓
7. Is copying required?
        ↓
8. Which traversal strategy is appropriate?
        ↓
9. What is the time complexity?
        ↓
10. What is the auxiliary-space complexity?
        ↓
11. Which Java utility methods can simplify the implementation?
        ↓
12. What tests verify the defined contract?
```

This keeps the Java implementation aligned with the repository's analytical
methodology.

---

# Relationship with Later Documents

This document establishes the practical Java foundation for the remaining
**Arrays** module:

```text
03-java-arrays.md
        ↓
04-multidimensional-arrays.md
        ↓
05-array-operations.md
        ↓
06-traversal-patterns.md
        ↓
07-common-algorithms.md
        ↓
08-complexity-analysis.md
```

The next document focuses specifically on multidimensional structures and
explains how Java represents arrays of arrays.

---

# Expected Outcome

After completing this document, the learner should be able to move between the
abstract and concrete levels of array reasoning:

```text
Abstract Array
        ↓
Indexed Fixed-Size Structure
        ↓
Java Array Object
        ↓
Java Syntax and Semantics
        ↓
Algorithmic Operations
        ↓
Complexity Analysis
```

The learner should understand not only how to write:

```text
int[] numbers = new int[10];
```

but also why:

```text
numbers[index]
```

has constant asymptotic access cost, why:

```text
Arrays.copyOf(...)
```

requires work proportional to the copied data, and why reference assignment:

```java
int[] b = numbers;
```

does not create an independent array.

---

# Key Takeaways

After studying this document, the learner should remember that:

- Java arrays are objects;
- array variables hold references to array objects;
- arrays have a fixed length after creation;
- indexes are zero-based;
- valid indexes satisfy `0 ≤ i < array.length`;
- `length` is a field, not a method;
- array elements are automatically initialised to type-specific default values;
- primitive arrays contain primitive values;
- reference arrays contain references;
- assigning one array variable to another copies the reference, not the contents;
- `Arrays.copyOf` creates a distinct array object;
- `Arrays.equals` compares array contents;
- `Arrays.fill` can initialise or reset positions efficiently;
- `Arrays.sort` provides standard-library sorting operations;
- `Arrays.binarySearch` requires an appropriately ordered range;
- array bounds are checked at runtime;
- `final` does not make an array immutable;
- copying, traversal, filling, and content comparison generally grow with the
  amount of data processed;
- Java abstracts low-level memory management while preserving the array
  abstraction;
- Java implementation details should remain distinct from the abstract
  algorithmic model.

Java arrays therefore provide the concrete implementation environment through
which the abstract array concepts introduced earlier become executable
algorithms.

---

# Next Document

```text
04-multidimensional-arrays.md
```

The next document studies multidimensional arrays in Java.

It introduces:

- arrays of arrays;
- two-dimensional structures;
- rows and columns;
- jagged arrays;
- matrix-like representations;
- nested traversal;
- multiple input-size parameters;
- time and space analysis.

The next central question becomes:

> **How can arrays be combined to represent multidimensional computational
> structures in Java, and how does that representation affect algorithmic
> complexity?**

---

# Academic Foundation

The principal academic reference for this document is:

> **Introduction to Algorithms**
>
> Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein  
> Fourth Edition  
> MIT Press

Java-specific behaviour is based on the Java language and platform model,
including the semantics of array objects, indexing, references, and standard
array utilities.

Complementary academic, mathematical, and technical references are documented
in:

```text
docs/00-project/10-references.md
```

Official Java documentation should be consulted whenever this repository makes
claims about specific library methods or language-level behaviour that may
depend on the Java platform version.