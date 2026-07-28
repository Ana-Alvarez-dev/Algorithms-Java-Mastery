# Problem Solving Guide

## Algorithms Java Mastery

Selecting the appropriate data structure is one of the first decisions in algorithm design. Many problems that initially appear complex become significantly simpler once the correct structure has been identified.

Stacks, queues, and deques are not chosen randomly; each one is designed to solve a specific class of problems efficiently.

This guide provides practical criteria for recognizing when each structure should be used.

---

# 1. Purpose

The purpose of this document is to:

- recognize common problem patterns;
- choose between a stack, queue, or deque;
- develop algorithmic reasoning before implementation.

---

# 2. Problem-Solving Process

Before writing code, ask the following questions:

```text
Understand the problem
        ↓
Identify how data must be processed
        ↓
Choose the appropriate data structure
        ↓
Design the algorithm
        ↓
Implement and test
```

Choosing the correct data structure often determines the simplicity and efficiency of the final solution.

---

# 3. When to Use a Stack

A **stack** is appropriate when the **most recently processed element** must be accessed first.

Ask yourself:

- Do I need to reverse the processing order?
- Should the latest element be removed first?
- Do I need to backtrack?
- Do I need to remember previous states?

Typical problems:

- balanced parentheses;
- undo/redo functionality;
- expression evaluation;
- recursive algorithms;
- Depth-First Search (DFS).

---

# 4. When to Use a Queue

A **queue** is appropriate when elements must be processed **in the same order they arrive**.

Ask yourself:

- Should the oldest element be processed first?
- Is arrival order important?
- Am I processing requests sequentially?

Typical problems:

- Breadth-First Search (BFS);
- task scheduling;
- printer queues;
- message processing;
- producer-consumer systems.

---

# 5. When to Use a Deque

A **deque** is useful when efficient operations are required at **both ends**.

Ask yourself:

- Do I need to insert or remove from both ends?
- Does the algorithm maintain a sliding window?
- Do I compare elements from the beginning and the end?

Typical problems:

- sliding window maximum;
- palindrome verification;
- monotonic queues;
- bounded history.

---

# 6. Decision Guide

| If the problem requires... | Recommended Structure |
|----------------------------|-----------------------|
| Last element processed first | Stack |
| First element processed first | Queue |
| Access to both ends | Deque |
| Backtracking | Stack |
| Level-order traversal | Queue |
| Sliding window | Deque |
| Undo operations | Stack |
| Scheduling tasks | Queue |

---

# 7. Common Mistakes

Avoid selecting a data structure simply because it is familiar.

Typical mistakes include:

- using a stack when FIFO behaviour is required;
- implementing queues with inefficient element shifting;
- using a deque when only one end is ever accessed;
- ignoring the algorithm's access pattern.

Always let the problem determine the data structure.

---

# 8. Best Practices

Before implementing an algorithm:

- identify the required processing order;
- define the insertion and removal operations;
- analyze the expected complexity;
- choose the simplest structure that satisfies the requirements.

A simpler data structure usually leads to simpler, more maintainable code.

---

# 9. Key Takeaways

After completing this document, the learner should understand that:

- problem solving begins by identifying how data must be processed;
- stacks are appropriate for **LIFO** behaviour;
- queues are appropriate for **FIFO** behaviour;
- deques are useful when both ends of the sequence are involved;
- recognizing problem patterns is an essential skill in algorithm design and technical interviews;
- selecting the correct data structure simplifies implementation and improves performance.