# Java Linked Lists

## Algorithms Java Mastery

Linked lists can be implemented manually or through the **Java Collections
Framework**. While production software typically relies on the standard library,
a solid understanding of the underlying data structure is essential before using
its abstractions.

Throughout this repository, linked lists are **implemented manually first** to
develop algorithmic reasoning, understand reference manipulation, analyse
computational complexity and verify structural correctness.

Only after these concepts have been mastered does the learner study Java's
built-in implementation.

This approach follows an important engineering principle:

> **A software engineer should understand how a data structure works before
using the abstraction provided by a programming language.**

---

# Academic Foundation

The concepts presented throughout this document combine the theoretical study of
linked lists developed in previous modules with the implementation facilities
provided by the Java Collections Framework.

This document focuses on relating algorithmic concepts to Java rather than
teaching the Collections Framework in isolation.

Additional academic references are maintained in:

```text
docs/00-project/10-references.md
```

---

# Purpose

The purpose of this document is to explain how linked lists are represented in
Java and how their implementation relates to the theoretical concepts studied
throughout this repository.

The learner will understand:

- manual implementations;
- node-based object modelling;
- Java's `LinkedList` class;
- advantages and limitations of the Collections Framework;
- engineering considerations when selecting a linked structure.

---

# Learning Objectives

After completing this document, the learner should be able to:

## Conceptual understanding

- relate linked-list theory to Java objects;
- explain how nodes are represented;
- distinguish manual implementations from library implementations.

## Programming perspective

- implement a linked list manually;
- manipulate object references correctly;
- understand the role of generic types.

## Engineering perspective

- identify appropriate use cases for `LinkedList`;
- compare `LinkedList` with `ArrayList`;
- justify implementation choices.

---

# Object-Oriented Representation

In Java, every node is naturally represented as an object.

Conceptually:

```text
Node

+-------------------+
| data              |
| next              |
+-------------------+
```

For doubly linked lists:

```text
Node

+-------------------+
| previous          |
| data              |
| next              |
+-------------------+
```

Each node stores application data together with the references required to
maintain the structure.

---

# Manual Implementation

Before studying Java's standard library, this repository develops linked lists
from first principles.

A simplified node can be represented as:

```java
class Node<T> {

    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
    }

}
```

This implementation makes every structural modification explicit, allowing the
learner to understand how insertion, deletion and traversal manipulate object
references.

---

# Generic Types

Java generics allow linked lists to store elements of different types while
preserving compile-time type safety.

Conceptually:

```text
Node<Integer>

Node<String>

Node<Employee>
```

Rather than creating a different implementation for every data type, a generic
implementation can be reused safely.

---

# Head and Tail References

Most implementations maintain a reference to the first node.

```text
Head

↓

Node → Node → Node
```

Some implementations also maintain a reference to the last node.

```text
Head                     Tail

↓                          ↓

Node → Node → Node → Node
```

Maintaining a tail reference can reduce the cost of inserting elements at the
end of the list.

---

# Java Collections Framework

Java provides a ready-to-use linked-list implementation through the
Collections Framework.

```java
LinkedList<String> cities = new LinkedList<>();
```

The class provides methods for:

- insertion;
- deletion;
- traversal;
- searching;
- queue operations;
- deque operations;
- stack-like operations.

The internal implementation details are hidden from the programmer, allowing
applications to focus on higher-level behaviour.

---

# Position within the Collections Framework

Conceptually:

```text
Collection
      │
      ▼
     List
      │
      ▼
 LinkedList
```

`LinkedList` is one implementation of the `List` interface.

Because it implements interfaces rather than defining new behaviour, it can be
used interchangeably with other `List` implementations when appropriate.

---

# LinkedList versus ArrayList

Java provides two commonly used implementations of the `List` interface.

| Characteristic | ArrayList | LinkedList |
|---------------|----------:|-----------:|
| Internal organisation | Dynamic array | Linked structure |
| Indexed access | Fast | Sequential |
| Insert at beginning | Expensive | Efficient |
| Delete at beginning | Expensive | Efficient |
| Memory overhead | Lower | Higher |

Both classes implement the same interface while providing different internal
representations.

Selecting one over the other depends on the dominant operations performed by
the application.

---

# Iteration

Java supports several techniques for traversing a linked list.

Conceptually:

```text
for (String city : cities) {

    System.out.println(city);

}
```

The enhanced `for` loop improves readability by abstracting the traversal
mechanism.

Alternatively, explicit iterators can be used when structural modification
during traversal is required.

---

# Iterators

An iterator provides sequential access without exposing the internal structure.

Conceptually:

```java
Iterator<String> iterator = cities.iterator();
```

Iterators improve abstraction and reduce coupling between algorithms and data
structures.

They also support safe element removal during traversal through the iterator
interface.

---

# Choosing the Appropriate Implementation

Engineers should avoid selecting `LinkedList` simply because it is available.

Instead, the decision should be guided by application requirements.

`LinkedList` is generally appropriate when:

- frequent insertions occur;
- frequent deletions occur;
- sequential traversal dominates;
- queue or deque behaviour is required.

`ArrayList` is generally preferable when:

- indexed access dominates;
- memory efficiency is important;
- cache locality improves performance.

---

# Relationship with Previous Modules

This document builds upon:

- Linked List Fundamentals;
- Singly Linked Lists;
- Doubly Linked Lists;
- Circular Linked Lists;
- List Operations;
- Complexity Analysis.

The theoretical concepts introduced in those documents now become concrete Java
implementations.

---

# Relationship with Automated Testing

Every manual implementation should be verified using automated unit tests.

Representative scenarios include:

- empty list creation;
- insertion;
- deletion;
- traversal;
- search;
- invalid operations;
- generic type behaviour;
- edge cases.

Testing confirms that the implementation satisfies its specification while
preserving structural correctness.

---

# Relationship with Benchmarking

Theoretical complexity predicts asymptotic behaviour.

Benchmarking measures actual execution performance.

Within this repository, Java implementations are evaluated experimentally using
JMH (Java Microbenchmark Harness) to compare linked-list operations with other
data structures under controlled conditions.

Experimental results complement, but never replace, theoretical complexity
analysis.

---

# Engineering Considerations

Using a standard library implementation improves reliability and reduces
maintenance costs.

However, software engineers should understand the underlying algorithms before
relying on library abstractions.

A manual implementation provides valuable insight into:

- reference manipulation;
- object relationships;
- structural invariants;
- algorithm correctness;
- computational complexity.

This understanding enables developers to make informed engineering decisions
when selecting data structures for real-world systems.

---

# Key Takeaways

After studying this document, the learner should understand that:

- Java represents linked lists through interconnected objects;
- manual implementations reveal the underlying algorithmic principles;
- `LinkedList` is one implementation of the `List` interface;
- generics enable reusable and type-safe implementations;
- `LinkedList` and `ArrayList` solve similar problems using different internal
  organisations;
- engineering decisions should be based on application requirements rather than
  familiarity with a particular class.

Understanding both the theoretical model and the Java implementation prepares
the learner to design, implement, test and evaluate linked data structures
using professional software engineering practices.