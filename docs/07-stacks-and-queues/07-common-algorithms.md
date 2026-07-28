# Common Algorithms Using Stacks and Queues

## Algorithms Java Mastery

Stacks and queues are **fundamental data structures** used to solve a wide variety of algorithmic problems. Their importance lies not in the complexity of their operations, but in the way they organize the processing of information.

A stack naturally supports **Last In, First Out (LIFO)** processing, while a queue supports **First In, First Out (FIFO)** processing. Choosing the appropriate structure often simplifies an algorithm and improves both readability and performance.

This document introduces the most representative algorithms and problem categories that rely on stacks and queues. Detailed implementations will be developed in later modules.

---

# 1. Purpose

The purpose of this document is to:

- identify common algorithmic applications of stacks and queues;
- understand why a particular data structure is appropriate;
- recognize recurring problem patterns;
- prepare the learner for practical implementations.

---

# 2. Algorithms Based on Stacks

Stacks are appropriate whenever the **most recently processed element** must be accessed first.

Typical applications include:

- expression evaluation;
- balanced parentheses validation;
- function call management;
- undo operations;
- depth-first search (DFS);
- backtracking algorithms.

---

## 2.1 Balanced Parentheses

One of the classic stack problems is verifying whether brackets are correctly matched.

Example:

```text
Input

({[]})
```

Processing:

```text
Push opening symbols

(

{

[
```

```text
Pop when matching closing symbols appear
```

If every opening symbol matches the corresponding closing symbol, the expression is balanced.

Complexity:

```text
Time : O(n)

Space : O(n)
```

---

## 2.2 Expression Evaluation

Stacks are widely used to evaluate mathematical expressions.

Examples include:

- postfix notation;
- prefix notation;
- infix conversion.

Example:

```text
3 4 + 2 *
```

Processing:

```text
Push operands

↓

Apply operators

↓

Push intermediate results
```

---

## 2.3 Function Call Stack

Programming languages internally use a stack to manage function calls.

Conceptually:

```text
main()

↓

calculate()

↓

sort()

↓

search()
```

When a function finishes, execution returns to the previous call.

This is a natural LIFO process.

---

## 2.4 Depth-First Search (DFS)

Depth-First Search explores one branch completely before visiting another.

Conceptually:

```text
Visit node

↓

Choose one neighbour

↓

Continue deeper

↓

Backtrack
```

DFS may be implemented using:

- an explicit stack;
- the program's recursive call stack.

---

## 2.5 Undo Operations

Applications such as text editors maintain previous states in a stack.

```text
Action 1

↓

Action 2

↓

Action 3
```

Undo removes the most recent action first.

---

# 3. Algorithms Based on Queues

Queues are appropriate whenever elements must be processed in the same order in which they arrive.

Typical applications include:

- breadth-first search (BFS);
- task scheduling;
- printer queues;
- message processing;
- producer-consumer systems.

---

## 3.1 Breadth-First Search (BFS)

BFS explores a graph level by level.

Conceptually:

```text
Visit source

↓

Visit all neighbours

↓

Visit neighbours of neighbours
```

A queue preserves the correct visiting order.

Complexity:

```text
Time : O(V + E)
```

where:

- V = vertices
- E = edges

---

## 3.2 Task Scheduling

Many operating systems and applications schedule work using queues.

Conceptually:

```text
Task A

↓

Task B

↓

Task C
```

Tasks are processed in arrival order.

---

## 3.3 Print Queue

A printer receives print jobs in chronological order.

```text
Document A

↓

Document B

↓

Document C
```

The first submitted document is printed first.

---

## 3.4 Producer-Consumer Systems

One component generates data while another consumes it.

```text
Producer

↓

Queue

↓

Consumer
```

The queue decouples production from consumption.

---

# 4. Algorithms Based on Deques

A deque provides efficient access to both ends of the sequence.

Typical applications include:

- sliding window algorithms;
- monotonic queues;
- palindrome checking;
- bounded history.

---

## 4.1 Sliding Window Maximum

The sliding window maximum problem maintains the largest element within a moving interval.

Example:

```text
Window size = 3

1 3 -1 -3 5 3 6 7
```

A deque stores only useful candidates.

This allows:

```text
Time : O(n)
```

instead of repeatedly scanning every window.

---

## 4.2 Palindrome Verification

Characters are compared from both ends.

Example:

```text
RADAR
```

Processing:

```text
Compare first and last

↓

Remove both

↓

Repeat
```

A deque naturally supports this process.

---

# 5. Choosing the Correct Data Structure

| Problem Pattern | Recommended Structure |
|-----------------|----------------------|
| Reverse order | Stack |
| Function calls | Stack |
| Expression evaluation | Stack |
| DFS | Stack |
| Undo | Stack |
| Arrival order | Queue |
| BFS | Queue |
| Scheduling | Queue |
| Producer-consumer | Queue |
| Sliding window | Deque |
| Double-ended processing | Deque |
| Palindrome checking | Deque |

---

# 6. Problem Recognition Guide

Useful questions include:

```text
Should the newest element be processed first?
```

Use:

```text
Stack
```

---

```text
Should the oldest element be processed first?
```

Use:

```text
Queue
```

---

```text
Must both ends be accessed efficiently?
```

Use:

```text
Deque
```

Recognizing these patterns is often more important than memorizing specific algorithms.

---

# 7. Key Takeaways

After completing this document, the learner should understand that:

- stacks naturally support LIFO algorithms such as DFS, expression evaluation, and undo operations;
- queues naturally support FIFO algorithms such as BFS, scheduling, and message processing;
- deques are appropriate when efficient access to both ends is required;
- selecting the appropriate data structure often simplifies the algorithm and improves performance;
- recognizing common problem patterns is an essential skill in algorithm design and technical interviews.