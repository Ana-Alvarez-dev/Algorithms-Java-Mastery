# Common Mistakes

## Algorithms Java Mastery

Linked lists are conceptually simple data structures, yet they are responsible
for many of the programming errors encountered by students and even experienced
software engineers.

Unlike arrays, where most operations manipulate indexed positions, linked lists
require developers to reason about **object references**, **structural
relationships**, and **dynamic memory organisation**. A single incorrect
reference update can disconnect part of the structure, create unintended
cycles, or make data permanently unreachable.

The purpose of this document is not merely to list common programming errors,
but to explain **why they occur**, **how they affect the structure**, and
**which engineering practices help prevent them**.

Learning to recognise these mistakes is an important step toward writing
correct, maintainable and reliable implementations.

---

# Academic Foundation

The observations presented throughout this document are derived from the
algorithmic principles studied in this repository together with the theoretical
foundations of linked data structures introduced in classical Computer Science
literature.

Rather than presenting language-specific mistakes, this document focuses on
errors that arise from misunderstanding the structure itself.

Additional academic references are maintained in:

```text
docs/00-project/10-references.md
```

---

# Purpose

The purpose of this document is to identify the most frequent mistakes made when
designing, implementing and analysing linked-list algorithms.

The learner will understand:

- why structural errors occur;
- how incorrect reference manipulation affects the list;
- common algorithmic misconceptions;
- techniques for preventing implementation errors.

---

# Learning Objectives

After completing this document, the learner should be able to:

## Conceptual understanding

- recognise common implementation mistakes;
- distinguish logical errors from syntactic errors;
- explain why structural integrity is essential.

## Algorithmic reasoning

- identify incorrect reference updates;
- analyse faulty algorithms;
- detect edge cases.

## Engineering perspective

- improve implementation quality;
- reduce debugging time;
- write safer linked-list algorithms.

---

# Mistake 1 — Forgetting to Handle an Empty List

Many algorithms assume that at least one node exists.

Incorrect assumption:

```text
Head

↓

Node
```

Possible reality:

```text
Head

↓

null
```

Attempting to access the first node without verifying that the list is not
empty often produces runtime exceptions.

### Recommendation

Always verify whether the head reference is `null` before accessing the first
node.

---

# Mistake 2 — Losing a Reference

One of the most serious linked-list errors is overwriting a reference before
saving the next node.

Incorrect sequence:

```text
A → B → C
```

If the reference to **B** is lost before storing its successor, node **C**
becomes unreachable.

### Recommendation

Whenever a reference will be modified, first preserve access to every node that
must remain reachable.

---

# Mistake 3 — Updating References in the Wrong Order

Structural modifications often require several reference updates.

Changing them in the wrong order may disconnect the list.

Example:

Instead of:

```text
Save next node

↓

Update references
```

The programmer performs:

```text
Update references

↓

Attempt to recover the next node
```

The original connection may already be lost.

### Recommendation

Plan the sequence of reference updates before writing code.

---

# Mistake 4 — Ignoring Edge Cases

Algorithms frequently work for typical inputs but fail for special situations.

Examples include:

- empty list;
- single-node list;
- insertion into an empty list;
- deletion of the head;
- deletion of the last node;
- duplicate values.

Ignoring these situations usually produces incorrect behaviour.

### Recommendation

List every edge case before implementing the algorithm.

---

# Mistake 5 — Assuming Indexed Access

Developers accustomed to arrays sometimes expect linked lists to provide direct
access by position.

Incorrect reasoning:

```text
Access element 100 immediately.
```

Reality:

Every preceding node must first be traversed.

### Recommendation

Remember that linked lists provide **sequential access**, not **random access**.

---

# Mistake 6 — Creating Accidental Cycles

Incorrect reference manipulation may connect a node to a previous node.

Example:

```text
A → B → C
     ↑   │
     └───┘
```

Traversal never reaches the end of the structure.

The algorithm enters an infinite loop.

### Recommendation

After structural modifications, verify that traversal terminates as expected.

---

# Mistake 7 — Forgetting to Update the Head

When inserting or deleting the first node, the head reference often changes.

Example:

Before:

```text
Head

↓

A → B → C
```

After deleting **A**

```text
Head

↓

B → C
```

If the head is not updated correctly, the structure becomes inconsistent.

### Recommendation

Treat modifications involving the first node as a separate case.

---

# Mistake 8 — Forgetting to Update the Tail

Implementations maintaining a tail reference must preserve its correctness.

Example:

Deleting the final node without updating the tail leaves the structure pointing
to a node that no longer belongs to the list.

### Recommendation

Whenever the final node changes, verify the tail reference.

---

# Mistake 9 — Confusing Node References with Stored Values

Two nodes may contain identical values while representing different objects.

Incorrect assumption:

```text
Node A == Node B
```

because

```text
Value(A) == Value(B)
```

These concepts are fundamentally different.

### Recommendation

Distinguish object identity from stored data.

---

# Mistake 10 — Ignoring Structural Invariants

Every linked-list algorithm should preserve the structural properties of the
data structure.

Typical invariants include:

- every node remains reachable;
- the head references the first node;
- the tail references the last node (if maintained);
- references remain consistent;
- no unintended cycles are introduced.

Violating these invariants compromises the correctness of every subsequent
operation.

---

# Mistake 11 — Analysing Only Correctness

Some implementations produce the correct result while exhibiting unnecessary
computational cost.

Example:

Performing multiple traversals when one would suffice.

### Recommendation

Always analyse both:

- correctness;
- computational complexity.

Correct algorithms should also be efficient.

---

# Mistake 12 — Insufficient Testing

Many implementations are tested using only one example.

This approach rarely exposes structural errors.

Representative tests should include:

- empty lists;
- single-node lists;
- multiple-node lists;
- boundary operations;
- invalid operations;
- stress cases.

Testing should verify both functionality and structural integrity.

---

# Debugging Strategies

When a linked-list algorithm fails, systematic debugging is often more effective
than repeatedly modifying the code.

Recommended approach:

```text
Understand the Problem
        ↓
Draw the Structure
        ↓
Trace Every Reference
        ↓
Verify Structural Invariants
        ↓
Identify the Fault
        ↓
Correct the Algorithm
        ↓
Retest
```

Visualising the list frequently reveals structural errors immediately.

---

# Preventive Engineering Practices

The following practices significantly reduce implementation errors.

- Draw the list before coding.
- Update references carefully.
- Preserve structural invariants.
- Handle edge cases explicitly.
- Analyse complexity.
- Write automated tests.
- Review algorithms before optimisation.

Engineering discipline prevents many errors before they occur.

---

# Relationship with Previous Modules

This document consolidates concepts introduced throughout the linked-list
module.

The mistakes described here directly relate to:

- Linked List Fundamentals;
- Singly Linked Lists;
- Doubly Linked Lists;
- Circular Linked Lists;
- List Operations;
- Complexity Analysis;
- Java Linked Lists;
- Common Algorithms;
- Problem Solving Guide.

Understanding these errors strengthens the learner's ability to implement every
previous concept correctly.

---

# Relationship with Automated Testing

Automated testing plays a central role in detecting structural mistakes.

Well-designed tests should verify:

- correctness;
- edge cases;
- reference consistency;
- structural invariants;
- exceptional conditions.

Testing reduces the likelihood that implementation errors reach production
systems.

---

# Relationship with Benchmarking

Benchmarking cannot compensate for incorrect implementations.

Before measuring performance, developers must ensure that:

- the algorithm is correct;
- the structure remains valid;
- every operation preserves the intended behaviour.

Performance evaluation should always follow correctness verification.

---

# Engineering Considerations

Most linked-list bugs do not result from complex algorithms.

Instead, they arise from small mistakes in reference manipulation.

Professional software engineers minimise these risks by:

- reasoning before coding;
- designing incrementally;
- validating assumptions;
- testing systematically;
- reviewing structural modifications carefully.

Developing these habits leads to more reliable implementations and higher
software quality.

---

# Key Takeaways

After studying this document, the learner should understand that:

- linked-list implementations are highly sensitive to reference manipulation;
- preserving structural invariants is essential for correctness;
- edge cases must be considered from the beginning of the design process;
- correctness, complexity and testing are equally important;
- systematic debugging is more effective than trial-and-error programming;
- disciplined engineering practices significantly reduce implementation errors.

Recognising these common mistakes prepares the learner to design robust linked
data structures and to apply the same engineering principles to more advanced
structures such as trees, hash tables and graphs.