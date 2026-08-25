# Search Patterns

## Purpose

Searching algorithms are rarely used in isolation.

Real-world software systems solve a wide variety of problems that require locating, identifying or selecting information according to different criteria.

Although these problems may appear unrelated, many of them share common algorithmic strategies.

These recurring strategies are known as **search patterns**.

Recognising these patterns enables software engineers to choose appropriate algorithms, simplify problem solving and design efficient solutions.

This document introduces the most common searching patterns encountered in software engineering and technical interviews.

---

# Learning Objectives

After completing this document, the learner should be able to:

- identify common searching patterns;
- distinguish between different search objectives;
- select an appropriate searching strategy for a problem;
- recognise when Binary Search is applicable;
- understand how searching patterns appear in practical software systems.

---

# What Is a Search Pattern?

A search pattern is a general strategy used to solve a family of search-related problems.

Instead of memorising individual algorithms, software engineers learn to recognise the structure of a problem.

Once the pattern is identified, selecting the appropriate algorithm becomes much easier.

Conceptually:

```text
Problem

↓

Recognise Pattern

↓

Choose Algorithm

↓

Implement Solution
```

---

# Exact Match Search

The objective is to determine whether a specific element exists.

Examples:

- Find a user by username.
- Search for a product ID.
- Locate a student number.
- Verify whether a value exists.

Typical algorithms:

- Linear Search
- Binary Search
- Hash Tables

---

# Position Search

Sometimes the goal is not merely to determine existence but to locate the position of an element.

Examples:

- Return the index of a value.
- Find the position of a character.
- Locate an element within an array.

Typical algorithms:

- Linear Search
- Binary Search

---

# Range Search

Some applications search for values belonging to an interval.

Examples:

- Students with grades between 70 and 90.
- Products priced between $100 and $200.
- Employees hired during a specific year.

These problems frequently combine:

- Binary Search;
- sorting;
- sequential traversal.

---

# Boundary Search

Many problems require identifying the first or last element satisfying a condition.

Examples:

- First occurrence of a duplicate.
- Last occurrence of a duplicate.
- First available appointment.
- First failed test.

These problems often use modified Binary Search algorithms.

---

# Nearest Value Search

Sometimes an exact match does not exist.

Instead,

the objective is to find the closest value.

Examples:

- Closest timestamp.
- Nearest geographic coordinate.
- Closest temperature measurement.

These searches commonly appear in numerical computing and data analysis.

---

# Condition Search

Instead of searching for equality,

the algorithm searches for elements satisfying a condition.

Examples:

```text
Age > 18
```

```text
Salary >= 50000
```

```text
StartsWith("Ana")
```

Condition-based searches are common in databases and business applications.

---

# Multiple Match Search

Some problems require locating every matching element.

Examples:

- All students enrolled in a course.
- Every transaction from a customer.
- Every occurrence of a word.

Unlike Binary Search,

the algorithm usually continues searching after finding a match.

---

# Search by Key

Software systems frequently search using unique identifiers.

Examples:

- Customer ID.
- ISBN.
- Employee Number.
- Vehicle Registration.

Unique keys allow efficient retrieval and simplify data management.

---

# Search by Attribute

Objects often contain multiple searchable properties.

Example:

```text
Employee

↓

ID

↓

Name

↓

Department

↓

Salary
```

Different attributes may require different search strategies.

---

# Search in Ordered Collections

When the collection is sorted,

additional information becomes available.

This enables algorithms such as:

- Binary Search;
- interpolation search;
- exponential search.

Ordering dramatically improves efficiency.

---

# Search in Unordered Collections

When no ordering exists,

algorithms generally rely on sequential inspection.

Typical examples include:

- Linear Search;
- hash-based lookup (if available).

---

# Pattern Selection

Choosing an appropriate search strategy depends on several questions.

```text
Is the collection sorted?

↓

Yes

↓

Binary Search
```

```text
No

↓

Linear Search
```

---

```text
Need exact match?

↓

Yes

↓

Equality Search
```

---

```text
Need every match?

↓

Multiple Match Search
```

---

```text
Need first occurrence?

↓

Boundary Search
```

---

# Practical Examples

| Problem | Pattern |
|----------|---------|
| Login system | Exact Match |
| Dictionary lookup | Binary Search |
| Student record by ID | Search by Key |
| Product catalogue | Ordered Search |
| Find nearest value | Nearest Value Search |
| Employees by department | Multiple Match Search |
| First duplicate | Boundary Search |

---

# Engineering Perspective

Experienced software engineers rarely begin by selecting an algorithm.

Instead,

they first analyse the characteristics of the problem.

Questions typically include:

- Is the collection ordered?
- Can duplicates exist?
- Is only one match required?
- How frequently is the data updated?
- Is fast searching more important than insertion?

Answering these questions leads naturally to an appropriate search strategy.

---

# Relationship with Previous Modules

Search patterns build upon previously studied algorithms.

```text
Linear Search
        ↓
Binary Search
        ↓
Complexity Analysis
        ↓
Search Patterns
```

The emphasis shifts from learning algorithms to recognising problems.

---

# Relationship with Future Modules

Many future algorithms implement specialised search patterns.

```text
Search Patterns
        ↓
Sorting
        ↓
Hash Tables
        ↓
Trees
        ↓
Graphs
        ↓
Dynamic Programming
```

Understanding these patterns simplifies the study of more advanced data structures.

---

# Key Takeaways

Search patterns provide a higher level of abstraction than individual algorithms.

Rather than memorising solutions, software engineers learn to recognise recurring problem structures.

This ability improves algorithm selection, software design and technical interview performance.

Mastering search patterns is an important step toward becoming an effective problem solver.

---

# Next Document

```
08-common-search-problems.md
```

The next document applies the searching techniques introduced in this module to representative computational problems, demonstrating how these patterns appear in practical software engineering scenarios.