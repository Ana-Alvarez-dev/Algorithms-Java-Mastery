# Common Mistakes

## Algorithms Java Mastery

Stacks and queues are conceptually simple data structures, yet implementation errors are common, especially when managing boundaries, references, or indices.

Many of these mistakes do not change the algorithm itself but violate the structural invariants that guarantee correctness.

Recognizing these common errors helps developers design more reliable and maintainable implementations.

---

# 1. Purpose

The purpose of this document is to:

- identify common implementation mistakes;
- understand why they occur;
- learn good implementation practices.

---

# 2. Stack Mistakes

## Confusing LIFO Order

A stack must always remove the **most recently inserted element**.

Incorrect:

```text
Push: A B C

Pop → A
```

Correct:

```text
Push: A B C

Pop → C
```

---

## Incorrect Top Management

Failing to update the top position correctly may produce:

- invalid reads;
- overwritten elements;
- incorrect stack size.

Always ensure that the top and size remain consistent after every operation.

---

## Forgetting Underflow Validation

Removing an element from an empty stack should never occur silently.

Before calling:

```text
pop()
```

verify that the stack is not empty.

---

# 3. Queue Mistakes

## Using a Linear Array with Shifting

Removing the first element by shifting all remaining elements is inefficient.

```text
[A][B][C][D]

↓

[B][C][D][_]
```

This makes:

```text
dequeue = O(n)
```

A circular array avoids this problem.

---

## Incorrect Front and Rear Updates

After enqueue or dequeue operations, both indices must remain consistent.

Incorrect index management may produce:

- lost elements;
- duplicated elements;
- invalid queue states.

---

## Forgetting the Single-Element Case

When the last element is removed:

```text
Front → null

Rear → null
```

Both references (or indices) must represent the empty state correctly.

---

# 4. Deque Mistakes

Common deque errors include:

- inserting at the wrong end;
- removing from the incorrect end;
- confusing stack behaviour with queue behaviour;
- failing to update both boundaries consistently.

Remember that a deque supports operations at **both ends**.

---

# 5. Complexity Mistakes

A frequent misconception is believing that all implementations have identical performance.

Examples:

- assuming every queue implementation performs dequeue in O(1);
- ignoring resizing costs in dynamic arrays;
- forgetting that linked structures require additional memory.

Always analyse the implementation, not only the abstract data structure.

---

# 6. Java-Specific Mistakes

Some common mistakes when using Java collections include:

- using the legacy `Stack` class instead of `Deque`;
- choosing `LinkedList` when `ArrayDeque` is generally more efficient;
- inserting `null` into an `ArrayDeque`;
- programming against concrete implementations instead of interfaces.

Prefer:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

instead of:

```java
ArrayDeque<Integer> stack = new ArrayDeque<>();
```

when possible.

---

# 7. Best Practices

To avoid these mistakes:

- preserve all representation invariants;
- validate boundary conditions;
- keep insertion and removal logic simple;
- write automated tests for empty, single-element, and multiple-element cases;
- verify both correctness and complexity.

---

# 8. Key Takeaways

After completing this document, the learner should understand that:

- most implementation errors result from incorrect state management rather than complex algorithms;
- boundary conditions are responsible for many bugs;
- stacks, queues, and deques must always preserve their structural invariants;
- implementation choices influence both correctness and performance;
- careful testing is essential for validating data structure implementations.