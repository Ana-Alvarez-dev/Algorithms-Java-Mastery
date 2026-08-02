# Call Stack

## Algorithms Java Mastery

Every recursive algorithm is executed through a mechanism known as the **Call Stack**.

Although recursion appears as a sequence of self-invocations in source code, the Java Virtual Machine (JVM) executes every recursive call exactly like any other method invocation. Each call creates a new execution context that is stored on the program stack until the method finishes.

Understanding the Call Stack is essential because it explains:

- how recursive methods are executed;
- why recursive calls eventually return;
- how local variables are isolated;
- why deep recursion consumes memory;
- why excessive recursion may produce a `StackOverflowError`.

For this reason, the Call Stack represents the bridge between the theoretical concept of recursion and its practical execution inside the JVM.

---

# 1. Purpose

The purpose of this document is to:

- explain the Call Stack concept;
- understand how recursive methods are executed;
- describe stack frames;
- analyze the lifecycle of recursive calls;
- explain stack overflow;
- relate recursion to Java runtime behavior.

---

# 2. What Is the Call Stack?

The **Call Stack** is a Last-In, First-Out (LIFO) data structure maintained by the JVM to manage method execution.

Whenever a method is invoked:

```text
Method Call

↓

Stack Frame Created
```

Whenever the method finishes:

```text
Stack Frame Removed
```

The stack always contains the active execution path of the program.

---

# 3. Why Is It Called a Stack?

The Call Stack behaves like an ordinary stack.

Only the top element can be accessed.

Operations follow:

```text
Push

↓

New Method Call
```

```text
Pop

↓

Method Returns
```

The last method called is always the first method that finishes.

This behavior is known as:

```text
LIFO

Last In
First Out
```

---

# 4. Stack Frames

Every method invocation creates a **stack frame**.

A stack frame stores the execution state of one method.

Conceptually:

```text
Stack Frame

↓

Parameters

Local Variables

Temporary Values

Return Address
```

Each recursive call receives its own independent stack frame.

---

# 5. Independent Execution Context

Consider:

```text
factorial(4)
```

During execution:

```text
factorial(4)

↓

factorial(3)

↓

factorial(2)

↓

factorial(1)
```

Although every call executes the same method, each invocation owns:

- its own parameters;
- its own local variables;
- its own execution state.

Recursive calls never share local variables.

---

# 6. Stack Growth

As recursion progresses, additional stack frames are created.

Conceptually:

```text
Top

factorial(1)

factorial(2)

factorial(3)

factorial(4)

Bottom
```

The newest recursive invocation is always placed on top of the stack.

---

# 7. Descending Phase

During recursive expansion:

```text
factorial(4)

↓

factorial(3)

↓

factorial(2)

↓

factorial(1)
```

No result has yet been produced.

The JVM continues creating stack frames until the base case is reached.

This stage is often called the **descending phase**.

---

# 8. Reaching the Base Case

Eventually:

```text
factorial(1)
```

recognizes the base case.

Instead of creating another recursive call:

```text
Return Value
```

The recursive expansion stops.

The execution now begins moving in the opposite direction.

---

# 9. Ascending Phase

After the base case returns:

```text
factorial(1)

↓

factorial(2)

↓

factorial(3)

↓

factorial(4)
```

Each method resumes execution exactly where it paused before making its recursive call.

This stage is known as the **ascending phase**.

---

# 10. Returning Through the Stack

Each returning method removes its own stack frame.

Conceptually:

```text
Top

factorial(1)

↓

Removed

factorial(2)

↓

Removed

factorial(3)

↓

Removed

factorial(4)

↓

Removed
```

Eventually, the Call Stack returns to its previous state.

---

# 11. Visual Example

Suppose:

```text
factorial(3)
```

Expansion:

```text
factorial(3)

↓

factorial(2)

↓

factorial(1)
```

Stack:

```text
Top

factorial(1)

factorial(2)

factorial(3)

Bottom
```

After returning:

```text
Top

factorial(2)

factorial(3)

Bottom
```

Then:

```text
Top

factorial(3)

Bottom
```

Finally:

```text
Empty
```

---

# 12. Work Before and After Recursion

A recursive method may perform computation:

Before recursion:

```text
Current Work

↓

Recursive Call
```

After recursion:

```text
Recursive Call

↓

Current Work
```

Many algorithms combine results after the recursive call returns.

Understanding this distinction is important for tracing recursive execution.

---

# 13. Nested Method Calls

The Call Stack is not exclusive to recursion.

Example:

```text
main()

↓

calculate()

↓

print()
```

Every ordinary method call also creates a stack frame.

Recursion simply creates multiple frames belonging to the same method.

---

# 14. Recursive Trace

Conceptually:

```text
factorial(4)

↓

factorial(3)

↓

factorial(2)

↓

factorial(1)

↓

Return

↓

factorial(2)

↓

factorial(3)

↓

factorial(4)
```

The stack naturally reverses the order of execution.

---

# 15. Why Local Variables Do Not Interfere

Consider:

```text
factorial(4)
```

and:

```text
factorial(3)
```

Each invocation contains its own:

```text
n
```

Although the variable has the same name, each copy belongs to a different stack frame.

Therefore:

```text
factorial(4)

n = 4
```

is completely independent from:

```text
factorial(3)

n = 3
```

---

# 16. The Return Address

Every stack frame stores the point where execution must continue after the called method finishes.

Conceptually:

```text
Current Method

↓

Recursive Call

↓

(Return Here)
```

Without this information, the JVM would not know where execution should resume.

---

# 17. Memory Consumption

Each stack frame occupies memory.

As recursion depth increases:

```text
More Recursive Calls

↓

More Stack Frames

↓

More Stack Memory
```

Memory consumption therefore grows with recursion depth.

---

# 18. Recursion Depth

The **recursion depth** is the maximum number of active recursive calls.

Example:

```text
factorial(5)
```

Maximum depth:

```text
5
```

A binary-tree traversal may have depth proportional to:

```text
Tree Height
```

Different recursive algorithms produce different maximum depths.

---

# 19. StackOverflowError

If recursion continues creating stack frames beyond the available stack memory, the JVM throws:

```text
StackOverflowError
```

This occurs when:

- the base case is missing;
- recursive progress never reaches the base case;
- recursion depth exceeds available stack space.

---

# 20. Infinite Recursion

Example reasoning:

```text
Problem(n)

↓

Problem(n)

↓

Problem(n)

↓

...
```

The problem never becomes smaller.

The Call Stack grows indefinitely until:

```text
StackOverflowError
```

This is one of the most common recursion mistakes.

---

# 21. Stack Size

Every Java thread owns its own Call Stack.

The available stack size is limited.

Consequently, recursion depth is also limited.

The exact limit depends on:

- JVM configuration;
- operating system;
- stack frame size;
- local variables;
- method parameters.

Algorithms should therefore avoid unnecessarily deep recursion.

---

# 22. Recursion Versus Heap Memory

It is important to distinguish:

```text
Call Stack
```

from:

```text
Heap
```

The stack stores:

- method calls;
- parameters;
- local variables;
- return information.

The heap stores:

- objects;
- arrays;
- dynamically allocated data.

Recursive execution primarily increases stack usage rather than heap usage.

---

# 23. Call Stack and Complexity

Recursive algorithms require additional stack space.

If recursion depth is:

```text
n
```

additional space may become:

```text
O(n)
```

Balanced recursive algorithms often require:

```text
O(log n)
```

stack space.

Space complexity therefore depends largely on recursion depth.

---

# 24. Tracing Recursive Execution

A useful debugging technique is to visualize recursive calls as a stack.

Rather than imagining:

```text
Method Calls Itself
```

think:

```text
Method

↓

Creates New Stack Frame

↓

Pauses

↓

Later Resumes
```

This mental model simplifies recursive reasoning considerably.

---

# 25. Java Perspective

Every recursive Java method behaves exactly like any ordinary method invocation.

The JVM does not distinguish between:

```text
calculate()
```

and:

```text
factorial()
```

The only difference is that recursive methods repeatedly invoke themselves, creating multiple stack frames belonging to the same method.

---

# 26. Engineering Perspective

Professional developers understand recursion by understanding the Call Stack.

When designing recursive algorithms, engineers evaluate:

- recursion depth;
- stack memory usage;
- termination guarantees;
- readability;
- iterative alternatives.

A recursive algorithm is appropriate only when its conceptual clarity justifies its additional stack usage.

---

# 27. Relationship with the Next Document

This document explained how recursive execution is managed internally through the JVM Call Stack.

The next document studies **Recursive Correctness**, introducing the principles used to demonstrate that a recursive algorithm both terminates and produces the correct result for every valid input.

---

# 28. Key Takeaways

After completing this document, the learner should understand that:

- every recursive call creates a new stack frame;
- the Call Stack follows the Last-In, First-Out principle;
- recursive calls execute independently with separate local variables;
- recursion consists of descending and ascending execution phases;
- the base case begins the process of returning through the stack;
- every returning method removes its own stack frame;
- recursion consumes additional stack memory proportional to recursion depth;
- excessive recursion may produce a `StackOverflowError`;
- the Call Stack stores execution state, while objects are stored in the heap;
- understanding the Call Stack is essential for debugging, analyzing, and designing recursive algorithms.