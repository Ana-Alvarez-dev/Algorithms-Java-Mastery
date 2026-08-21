# Common Search Problems

## Purpose

Searching algorithms are not studied merely to locate values within a collection.

Their true importance lies in the wide variety of computational problems they solve.

Many software engineering tasks can be formulated as search problems, even when they do not explicitly appear to involve searching.

Recognising these problems is an essential skill for designing efficient algorithms and selecting appropriate data structures.

This document introduces the most common search problems encountered in software engineering and demonstrates how different searching strategies are applied in practice.

---

# Learning Objectives

After completing this document, the learner should be able to:

- recognise common computational search problems;
- classify search problems according to their characteristics;
- identify the most appropriate searching strategy;
- understand the relationship between searching problems and data structures;
- prepare for practical software engineering scenarios and technical interviews.

---

# Understanding Search Problems

Every search problem begins with a question.

Examples include:

- Does this element exist?
- Where is this element located?
- Which element satisfies a given condition?
- Which value is closest to the target?
- Which element should be selected?

Although these questions differ, they all involve locating information within a collection.

---

# Exact Match Problems

The objective is to determine whether a specific element exists.

Typical examples include:

- User authentication.
- Product lookup by identifier.
- Student registration number.
- ISBN verification.
- Vehicle registration lookup.

Typical algorithms:

- Linear Search
- Binary Search
- Hash Table Lookup

---

# Index Location Problems

Sometimes the location of the element is required rather than the element itself.

Examples include:

- Position of a word in a document.
- Index of an array element.
- Cursor position within a text editor.
- First occurrence of a character.

Typical algorithms:

- Linear Search
- Binary Search

---

# Duplicate Detection Problems

Many applications need to determine whether duplicate values exist.

Examples:

- Duplicate usernames.
- Repeated email addresses.
- Duplicate transaction identifiers.
- Repeated product codes.

Possible solutions include:

- Linear Search
- Sorting followed by searching
- Hash-based searching

---

# Minimum and Maximum Search

Searching is not always performed for equality.

Sometimes the objective is to locate the smallest or largest element.

Examples:

- Highest examination score.
- Lowest product price.
- Maximum temperature.
- Minimum response time.

Typical strategy:

Sequential traversal.

---

# Boundary Problems

The objective is to locate the first or last element satisfying a condition.

Examples include:

- First failed transaction.
- Last completed order.
- First duplicate value.
- Last available reservation.

These problems frequently use modified Binary Search algorithms.

---

# Range Problems

Many software systems retrieve every element belonging to an interval.

Examples:

- Products between two prices.
- Students within a grade range.
- Employees hired between two dates.
- Orders created during a specific month.

These problems often combine:

- sorting;
- Binary Search;
- sequential traversal.

---

# Nearest Value Problems

Sometimes no exact match exists.

Instead, the closest value must be identified.

Examples include:

- Closest timestamp.
- Nearest GPS location.
- Closest measurement.
- Approximate numerical solution.

These problems appear frequently in scientific computing and data analysis.

---

# Predicate-Based Problems

The search criterion is defined by a logical condition.

Examples:

```text
Age >= 18
```

```text
Salary > 75000
```

```text
StartsWith("Mar")
```

```text
Status == ACTIVE
```

Searching based on predicates is widely used in enterprise software.

---

# Multiple Result Problems

Some searches return every matching element rather than stopping after the first match.

Examples:

- All students enrolled in a course.
- Every invoice belonging to a customer.
- All products of a category.
- Every occurrence of a word.

These problems require complete traversal unless specialised indexing structures are available.

---

# Hierarchical Search Problems

Some collections are organised hierarchically.

Examples include:

- File systems.
- Organisation charts.
- XML documents.
- JSON trees.
- Directory structures.

These searches are commonly performed using tree traversal algorithms.

---

# Graph Search Problems

Certain problems involve relationships rather than linear collections.

Examples:

- Route planning.
- Social networks.
- Computer networks.
- Dependency analysis.

Typical algorithms include:

- Breadth-First Search (BFS)
- Depth-First Search (DFS)

These algorithms are studied in the Graphs module.

---

# Choosing the Appropriate Strategy

Selecting the correct algorithm depends on the characteristics of the problem.

Questions to consider include:

- Is the collection sorted?
- Are duplicates possible?
- Is only one result required?
- Are all matching elements required?
- Does the collection change frequently?
- Is memory limited?
- Is search performance critical?

Answering these questions guides algorithm selection.

---

# Practical Software Engineering Examples

| Problem | Suggested Strategy |
|----------|-------------------|
| Login validation | Exact Match |
| Product catalogue | Binary Search |
| Configuration lookup | Linear Search |
| Student records | Search by Key |
| Employee directory | Predicate Search |
| Sales report | Range Search |
| GPS navigation | Nearest Value Search |
| File explorer | Tree Search |
| Route planning | Graph Search |

---

# Relationship with Previous Documents

This document applies the concepts introduced earlier.

```text
Search Fundamentals
        ↓
Problem Definition
        ↓
Linear Search
        ↓
Binary Search
        ↓
Complexity
        ↓
Search Patterns
        ↓
Common Search Problems
```

The emphasis moves from learning algorithms to recognising practical applications.

---

# Relationship with Future Modules

Many future topics are specialised solutions to these search problems.

```text
Common Search Problems
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

Understanding the problem is always more important than memorising the algorithm.

---

# Key Takeaways

Searching is far more than locating a value within an array.

Modern software systems solve a wide variety of search problems involving exact matches, ranges, predicates, nearest values, hierarchical structures and graph relationships.

Recognising these problem categories enables software engineers to select appropriate algorithms and data structures while improving software performance and maintainability.

---

# Next Document

```
09-problem-solving-guide.md
```

The next document presents a structured methodology for analysing and solving searching problems, integrating algorithmic reasoning, correctness analysis, complexity evaluation and software engineering best practices.