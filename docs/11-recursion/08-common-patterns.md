# Common Patterns

## Algorithms Java Mastery

Although recursive algorithms may appear very different from one another, most of them follow a relatively small number of recurring design patterns.

Recognizing these patterns is more valuable than memorizing individual algorithms because it allows software engineers to identify the underlying structure of new problems and apply an appropriate solution strategy.

Many of the most important algorithms in Computer Science—including Merge Sort, Quick Sort, tree traversals, graph traversals, backtracking algorithms, and dynamic programming—are based on one or more recursive patterns.

This document introduces the most common recursive patterns that appear throughout algorithm design and prepares the learner for the advanced modules that follow in this repository.

---

# 1. Purpose

The purpose of this document is to:

- identify the principal recursive design patterns;
- explain when each pattern should be used;
- relate recursive patterns to practical algorithm design;
- distinguish different recursive strategies;
- prepare the learner for advanced algorithmic paradigms.

---

# 2. Why Patterns Matter

Instead of memorizing isolated recursive algorithms, it is more useful to recognize recurring structures.

For example:

```text
Tree Traversal

↓

Graph Traversal

↓

Divide and Conquer

↓

Backtracking
```

Although these algorithms solve different problems, they share similar recursive reasoning.

Learning the pattern makes learning future algorithms significantly easier.

---

# 3. Linear Recursion

Linear recursion performs **one recursive call** during each invocation.

Conceptually:

```text
Problem

↓

Smaller Problem

↓

Smaller Problem

↓

Base Case
```

Characteristics:

- one recursive branch;
- simple recursive reduction;
- predictable recursion depth.

Typical examples:

- factorial;
- recursive linear search;
- recursive list traversal;
- countdown algorithms.

---

# 4. Binary Recursion

Binary recursion performs **two recursive calls** for each invocation.

Conceptually:

```text
Problem

↓

Left

+

Right
```

Typical examples:

- Fibonacci;
- binary tree traversal;
- Merge Sort;
- Quick Sort.

Binary recursion usually generates larger recursion trees than linear recursion.

---

# 5. Multiple Recursion

Some algorithms generate more than two recursive calls.

Conceptually:

```text
Problem

↓

Subproblem A

Subproblem B

Subproblem C

...
```

Examples include:

- game-tree search;
- combinatorial generation;
- recursive search algorithms;
- optimization problems.

The branching factor significantly influences the algorithm's complexity.

---

# 6. Divide and Conquer

Divide and Conquer is one of the most important recursive design paradigms.

General process:

```text
Divide
        ↓
Solve Smaller Problems
        ↓
Combine Results
```

Characteristics:

- independent subproblems;
- recursive decomposition;
- combination phase.

Typical algorithms:

- Merge Sort;
- Quick Sort;
- Binary Search;
- Strassen Matrix Multiplication.

This paradigm will be studied in detail in a later module.

---

# 7. Tree Traversal

Trees possess a naturally recursive structure.

Every subtree is itself a tree.

Conceptually:

```text
Node

↓

Left Subtree

Right Subtree
```

Recursive traversal processes:

- current node;
- left subtree;
- right subtree.

Common traversals include:

- preorder;
- inorder;
- postorder.

Recursion produces elegant tree-processing algorithms because the algorithm mirrors the recursive structure of the data.

---

# 8. Graph Traversal

Graph traversal may also use recursion.

Depth-First Search naturally follows recursive reasoning.

Conceptually:

```text
Visit Vertex

↓

Visit Neighbour

↓

Visit Next Neighbour
```

Recursive DFS requires:

- visited-state tracking;
- recursive exploration;
- termination conditions.

Unlike trees, graphs may contain cycles.

Therefore recursion alone is insufficient without a visited structure.

---

# 9. Recursive Search

Many search algorithms recursively reduce the search space.

Example:

```text
Search

↓

Smaller Search Space

↓

Smaller Search Space
```

Typical examples:

- binary search;
- recursive tree search;
- recursive directory traversal.

The recursive reduction determines the algorithm's efficiency.

---

# 10. Backtracking

Backtracking is a recursive strategy that explores possible solutions and abandons partial solutions that cannot lead to success.

Conceptually:

```text
Choose

↓

Explore

↓

Success?

↓

No

↓

Undo Choice

↓

Try Another Option
```

Characteristics:

- recursive exploration;
- decision tree;
- state restoration.

Typical applications:

- Sudoku;
- N-Queens;
- maze solving;
- permutations;
- combinations.

---

# 11. Recursive Enumeration

Some recursive algorithms generate every possible solution.

Conceptually:

```text
Partial Solution

↓

Extend

↓

Extend Again

↓

Complete Solution
```

Applications include:

- permutations;
- subsets;
- combinations;
- exhaustive search.

Enumeration algorithms frequently rely on backtracking.

---

# 12. Recursive Decomposition

Many problems become easier after decomposition.

Conceptually:

```text
Large Problem

↓

Smaller Independent Problems

↓

Combine
```

This principle appears in:

- Merge Sort;
- matrix algorithms;
- expression evaluation;
- tree processing.

Recursive decomposition is one of the defining characteristics of recursive algorithm design.

---

# 13. Recursive Accumulation

Some recursive algorithms accumulate partial results while progressing toward the base case.

Conceptually:

```text
Current Result

↓

Update

↓

Recursive Call
```

This pattern frequently appears in:

- tail recursion;
- recursive summation;
- recursive multiplication;
- numeric algorithms.

Accumulator variables often simplify recursive reasoning.

---

# 14. Recursive Reduction

Every recursive pattern depends on reducing the problem.

Common reduction strategies include:

Decrease by one:

```text
n

↓

n − 1
```

Divide by two:

```text
n

↓

n / 2
```

Split into subproblems:

```text
Problem

↓

Left

+

Right
```

The reduction strategy largely determines algorithm complexity.

---

# 15. Recursive Decision Trees

Many recursive algorithms implicitly generate decision trees.

Conceptually:

```text
Decision

↓

Option A

Option B

↓

Further Decisions
```

Examples include:

- backtracking;
- game search;
- optimization problems;
- combinatorial generation.

The branching factor determines the size of the decision tree.

---

# 16. Recursive Mathematical Definitions

Many mathematical functions are naturally recursive.

Examples include:

```text
Factorial
```

```text
Fibonacci
```

```text
Greatest Common Divisor
```

Recursive implementations closely resemble their mathematical definitions.

This correspondence often simplifies correctness reasoning.

---

# 17. Recursive Hierarchies

Hierarchical structures frequently suggest recursive solutions.

Examples:

```text
Organization

↓

Departments

↓

Teams
```

```text
Directory

↓

Subdirectory

↓

Files
```

Recursive traversal naturally processes hierarchical systems.

---

# 18. Recursive Parsing

Programming language parsers frequently use recursion.

Conceptually:

```text
Expression

↓

Subexpression

↓

Smaller Expression
```

Applications include:

- compiler construction;
- interpreters;
- expression evaluators;
- syntax analysis.

Recursive descent parsing is one of the most common parser implementations.

---

# 19. Recursive Dynamic Programming

Some recursive algorithms repeatedly solve identical subproblems.

Conceptually:

```text
Problem

↓

Repeated Subproblem

↓

Repeated Again
```

Instead of recomputing results, Dynamic Programming stores previous solutions.

This optimization transforms many exponential recursive algorithms into polynomial ones.

The Dynamic Programming module explores this idea in detail.

---

# 20. Choosing the Appropriate Pattern

When approaching a recursive problem, ask:

- Does the problem naturally divide into independent parts?
- Does every level process a hierarchical structure?
- Must every possibility be explored?
- Is only one smaller problem required?
- Are multiple recursive branches needed?
- Do identical subproblems appear repeatedly?

The answers often identify the appropriate recursive pattern.

---

# 21. Pattern Comparison

| Pattern | Main Idea | Typical Applications |
|----------|-----------|----------------------|
| Linear Recursion | One recursive call | Factorial, list traversal |
| Binary Recursion | Two recursive calls | Fibonacci, tree traversal |
| Divide and Conquer | Divide → Solve → Combine | Merge Sort, Quick Sort |
| Recursive Search | Reduce search space | Binary Search |
| Graph Traversal | Explore connected vertices | DFS |
| Tree Traversal | Visit recursive subtrees | Binary Trees |
| Backtracking | Explore and undo choices | Sudoku, N-Queens |
| Enumeration | Generate every possibility | Permutations, combinations |
| Recursive Parsing | Recursive grammar | Compilers |
| Dynamic Programming | Store repeated solutions | Optimization problems |

---

# 22. Common Misconceptions

## "Every recursive algorithm uses the same pattern."

Incorrect.

Different recursive problems require different decomposition strategies.

---

## "Backtracking and Divide and Conquer are identical."

False.

Divide and Conquer solves independent subproblems.

Backtracking explores possible solutions and abandons unsuccessful paths.

---

## "Every recursive algorithm is Divide and Conquer."

Incorrect.

Many recursive algorithms perform only one recursive call and never divide the problem into independent parts.

---

# 23. Java Perspective

Java supports all recursive patterns through ordinary method calls.

Regardless of the pattern used:

- every recursive invocation creates a stack frame;
- recursion depth affects stack memory;
- correctness depends on valid recursive reduction;
- performance depends on the selected algorithmic strategy rather than recursion itself.

The implementation mechanism remains the same across all recursive patterns.

---

# 24. Engineering Perspective

Professional software engineers rarely invent recursive algorithms from scratch.

Instead, they recognize familiar patterns.

A typical reasoning process is:

```text
Understand Problem
        ↓
Identify Recursive Structure
        ↓
Recognize Pattern
        ↓
Select Appropriate Strategy
        ↓
Implement
        ↓
Verify Correctness
        ↓
Analyse Complexity
```

Recognizing patterns accelerates algorithm design and improves solution quality.

---

# 25. Relationship with the Next Document

This document introduced the principal recursive design patterns that appear throughout Computer Science.

The next document, **Common Mistakes**, examines the conceptual and implementation errors frequently encountered when designing and implementing recursive algorithms, including:

- incorrect base cases;
- infinite recursion;
- excessive recursion depth;
- duplicated computation;
- incorrect recursive reduction;
- complexity misconceptions.

Understanding these mistakes is essential before applying recursion to more advanced algorithms.

---

# 26. Key Takeaways

After completing this document, the learner should understand that:

- most recursive algorithms belong to a relatively small number of recurring patterns;
- linear recursion performs one recursive call per invocation;
- binary recursion generates two recursive branches;
- Divide and Conquer decomposes problems into independent subproblems;
- tree and graph traversals naturally exploit recursive structures;
- backtracking explores decision trees while undoing unsuccessful choices;
- recursive search progressively reduces the search space;
- Dynamic Programming optimizes recursive algorithms with overlapping subproblems;
- recognizing recursive patterns is more valuable than memorizing isolated implementations;
- mastering these patterns prepares the learner for advanced algorithmic paradigms studied later in the repository.