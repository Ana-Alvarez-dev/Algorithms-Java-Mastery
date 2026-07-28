# Stack Fundamentals

## Algorithms Java Mastery

A **stack** is a linear abstract data type that restricts insertion and removal
operations to a single accessible end known as the **top**.

Its behaviour follows the principle:

```text
LIFO — Last In, First Out
```

This means that the most recently inserted element is the first element removed.

Conceptually:

```text
Push A
Push B
Push C

        Top
         ↓
      +-----+
      |  C  | ← First element removed
      +-----+
      |  B  |
      +-----+
      |  A  | ← First element inserted
      +-----+
```

Stacks are fundamental because they model situations in which the most recent
state, task, operation, or decision must be processed before earlier ones.

They appear in:

- method execution;
- recursion;
- expression evaluation;
- syntax analysis;
- undo mechanisms;
- backtracking;
- depth-first search;
- browser navigation;
- transaction processing.

The objective of this document is to study the stack as an **abstract data
type** before examining its concrete implementation in Java.

---

## 1. Academic Foundation

A stack is defined primarily by its behavioural contract rather than by its
physical representation.

The abstract data type determines:

- which operations are permitted;
- where insertion occurs;
- where removal occurs;
- which element is visible;
- what ordering policy is preserved.

A stack may be implemented using:

- a fixed-capacity array;
- a dynamically resized array;
- a singly linked list;
- another suitable sequential structure.

Although these implementations differ internally, each one must preserve the
same LIFO behaviour.

The distinction between abstraction and implementation is essential:

```text
Stack Abstract Data Type
          ↓
Defines behaviour
          ↓
Push, Pop, Peek, Size, Is Empty
```

```text
Stack Implementation
          ↓
Defines representation
          ↓
Array, Dynamic Array, Linked Nodes
```

The theoretical foundation of this module is based on classical algorithms and
data structures literature. The explanations and examples presented in this
repository are original and adapted to the academic and engineering direction
of **Algorithms Java Mastery**.

The complete bibliographic foundation is maintained in the project
documentation.

```text
docs/00-project/
```

---

## 2. Purpose

The purpose of this document is to establish the conceptual foundation required
to understand, implement, analyse, and use stacks correctly.

The learner will study:

- the LIFO ordering principle;
- the top of the stack;
- fundamental stack operations;
- stack states;
- preconditions and postconditions;
- structural invariants;
- overflow and underflow;
- representative applications;
- correctness and complexity expectations.

This document focuses on the behaviour of the structure independently of a
particular Java implementation.

---

## 3. Learning Objectives

After completing this document, the learner should be able to achieve the
following objectives.

### 3.1 Conceptual understanding

- define a stack;
- explain the LIFO principle;
- identify the top of the structure;
- distinguish the stack abstraction from its implementation;
- describe the effect of each fundamental operation.

### 3.2 Algorithmic reasoning

- trace a sequence of stack operations;
- predict which element will be removed next;
- identify problems that require LIFO processing;
- reason about empty and full states;
- preserve stack order during an algorithm.

### 3.3 Correctness reasoning

- define operation preconditions and postconditions;
- identify stack invariants;
- reason about underflow and overflow;
- verify that an implementation preserves LIFO behaviour.

### 3.4 Complexity analysis

- explain why fundamental stack operations should be constant time;
- distinguish worst-case from amortised behaviour;
- recognise inefficient implementations.

### 3.5 Engineering perspective

- evaluate when a stack is an appropriate abstraction;
- select suitable error-handling behaviour;
- identify representative test scenarios;
- understand the importance of encapsulation.

---

## 4. Conceptual Model

A stack can be visualised as a vertical collection of elements.

Only the element located at the top is directly accessible.

```text
        Top
         ↓
      +-----+
      |  D  |
      +-----+
      |  C  |
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

The element `D` is the most recently inserted element.

Therefore:

```text
Next element removed = D
```

After removing `D`, the element `C` becomes the new top.

```text
        Top
         ↓
      +-----+
      |  C  |
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

Elements below the top cannot normally be removed directly.

To reach `A`, the elements above it must first be removed.

This access restriction is what gives the stack its characteristic behaviour.

---

## 5. The LIFO Principle

The defining property of a stack is:

```text
Last In
   ↓
First Out
```

Suppose the following elements are inserted in order:

```text
A, B, C, D
```

The removal order will be:

```text
D, C, B, A
```

The insertion order and removal order are therefore reversed.

```text
Insertion order

A → B → C → D
```

```text
Removal order

D → C → B → A
```

This behaviour differs from a queue, where the earliest inserted element is
removed first.

The LIFO rule must remain valid after every stack operation.

---

## 6. The Top of the Stack

The **top** identifies the element currently available for inspection or
removal.

Conceptually:

```text
        Top
         ↓
      +-----+
      |  C  |
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

The top may be represented differently depending on the implementation.

In an array-based stack, it may be represented by:

- the index of the current top element;
- the index of the next available position;
- the current number of elements.

In a linked implementation, it may be represented by:

- a reference to the first node;
- a reference to the most recently inserted node.

These are implementation decisions.

Conceptually, the role remains the same:

> The top identifies the next element that will be removed.

---

## 7. Fundamental Operations

The stack abstract data type commonly defines the following operations:

| Operation | Purpose |
|---|---|
| `push` | Insert an element at the top |
| `pop` | Remove and return the top element |
| `peek` | Return the top element without removing it |
| `isEmpty` | Determine whether the stack has no elements |
| `size` | Return the number of stored elements |

Some bounded implementations may also provide:

| Operation | Purpose |
|---|---|
| `isFull` | Determine whether the stack has reached its capacity |
| `capacity` | Return the maximum number of elements |

The exact method names may differ between implementations, but the underlying
behaviour remains equivalent.

---

## 8. Push Operation

The `push` operation inserts a new element at the top of the stack.

Before:

```text
        Top
         ↓
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

Operation:

```text
push(C)
```

After:

```text
        Top
         ↓
      +-----+
      |  C  |
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

The newly inserted element becomes the new top.

### 8.1 Conceptual specification

Input:

```text
Stack S
Element x
```

Effect:

```text
Insert x at the top of S
```

### 8.2 Postconditions

After a successful push:

- the stack contains the new element;
- the new element is the top;
- the size increases by one;
- the previous elements preserve their relative order.

If the original stack was:

```text
[A, B]
```

and `C` is pushed, the resulting stack is:

```text
[A, B, C]
```

where `C` is the top.

### 8.3 Ordering guarantee

Push does not reorder existing elements.

It only adds a new element above the previous top.

---

## 9. Pop Operation

The `pop` operation removes and returns the top element.

Before:

```text
        Top
         ↓
      +-----+
      |  C  |
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

Operation:

```text
pop()
```

Returned value:

```text
C
```

After:

```text
        Top
         ↓
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

### 9.1 Conceptual specification

Input:

```text
Non-empty stack S
```

Output:

```text
Previous top element
```

Effect:

```text
Remove the top element from S
```

### 9.2 Preconditions

A traditional pop operation requires:

```text
S is not empty
```

Attempting to remove an element from an empty stack produces an underflow
condition.

### 9.3 Postconditions

After a successful pop:

- the previous top element has been removed;
- the removed element is returned;
- the size decreases by one;
- the next element becomes the new top;
- the relative order of the remaining elements is preserved.

---

## 10. Peek Operation

The `peek` operation returns the top element without modifying the stack.

Before:

```text
        Top
         ↓
      +-----+
      |  C  |
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

Operation:

```text
peek()
```

Returned value:

```text
C
```

After:

```text
        Top
         ↓
      +-----+
      |  C  |
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

The structure remains unchanged.

### 10.1 Difference between peek and pop

| Operation | Returns top | Removes top |
|---|:---:|:---:|
| `peek` | Yes | No |
| `pop` | Yes | Yes |

### 10.2 Postconditions

After a successful peek:

- the returned value is the current top;
- the size remains unchanged;
- the stack order remains unchanged;
- no element is removed.

---

## 11. Is Empty Operation

The `isEmpty` operation determines whether the stack contains any elements.

Conceptually:

```text
size = 0
    ↓
Stack is empty
```

```text
size > 0
    ↓
Stack is not empty
```

Typical result:

```text
true  → no elements
false → one or more elements
```

This operation is frequently used before `pop` or `peek` to prevent invalid
access.

Example reasoning:

```text
If stack is not empty
        ↓
Read or remove the top
```

The operation should not modify the stack.

---

## 12. Size Operation

The `size` operation returns the number of elements currently stored.

Example:

```text
        Top
         ↓
      +-----+
      |  C  |
      +-----+
      |  B  |
      +-----+
      |  A  |
      +-----+
```

Result:

```text
size = 3
```

The size is not necessarily the same as capacity.

```text
Size
    ↓
Number of elements currently stored
```

```text
Capacity
    ↓
Maximum number of elements currently supported
```

A stack may have:

```text
size = 3
capacity = 10
```

This means that three positions are occupied and seven remain available.

---

## 13. Empty Stack

An empty stack contains no elements.

Conceptually:

```text
Top
 ↓
No valid element
```

Its size is:

```text
0
```

For an empty stack:

| Operation | Typical result |
|---|---|
| `isEmpty` | `true` |
| `size` | `0` |
| `push` | Valid |
| `pop` | Invalid without defined handling |
| `peek` | Invalid without defined handling |

The representation of an empty stack depends on the implementation.

An array-based structure may use:

```text
top = -1
```

or:

```text
size = 0
```

A linked implementation may use:

```text
top = null
```

These representations differ, but they express the same abstract state.

---

## 14. Stack Underflow

**Underflow** occurs when an operation attempts to inspect or remove an element
from an empty stack.

Example:

```text
Stack = empty

pop()
```

There is no top element to return.

Underflow may be handled by:

- throwing an exception;
- returning a special value;
- returning an optional result;
- preventing the operation through a precondition check.

The selected behaviour should be explicit in the stack contract.

### 14.1 Engineering consideration

Returning `null` may be ambiguous when `null` is also permitted as a stored
value.

Therefore, error handling should be designed carefully.

The implementation must clearly distinguish:

```text
The stack is empty
```

from:

```text
The top element exists and its stored value is null
```

---

## 15. Stack Overflow

**Overflow** occurs when an insertion is attempted on a stack that cannot store
additional elements.

This condition mainly affects bounded implementations.

Example:

```text
Capacity = 3

Current stack:

[C, B, A]

push(D)
```

The stack is already full.

Possible strategies include:

- rejecting the insertion;
- throwing an exception;
- increasing the underlying capacity;
- using a dynamically linked representation.

A dynamically resized stack may avoid immediate overflow by allocating more
space, although it is still limited by available system memory.

---

## 16. Bounded and Unbounded Stacks

### 16.1 Bounded stack

A bounded stack has a fixed maximum capacity.

```text
Maximum capacity = 5
```

Advantages may include:

- predictable memory usage;
- simple storage management;
- suitability for constrained systems.

Limitations include:

- possible overflow;
- fixed maximum size;
- capacity planning requirements.

### 16.2 Dynamically growing stack

A dynamically growing stack increases its storage when required.

Possible representations include:

- a resizable array;
- linked nodes.

Advantages may include:

- flexible capacity;
- easier adaptation to variable workloads.

Limitations may include:

- allocation costs;
- resizing costs;
- less predictable memory consumption.

The abstract LIFO behaviour remains the same in both cases.

---

## 17. Operation Trace

Tracing operations helps verify stack behaviour.

Initial state:

```text
[]
```

Operation:

```text
push(A)
```

State:

```text
[A]
```

Operation:

```text
push(B)
```

State:

```text
[A, B]
```

Operation:

```text
push(C)
```

State:

```text
[A, B, C]
```

Operation:

```text
pop()
```

Returned value:

```text
C
```

State:

```text
[A, B]
```

Operation:

```text
peek()
```

Returned value:

```text
B
```

State remains:

```text
[A, B]
```

Operation:

```text
pop()
```

Returned value:

```text
B
```

Final state:

```text
[A]
```

This trace demonstrates that the most recently inserted element is always
removed first.

---

## 18. Formal Behavioural Model

A stack may be represented abstractly as a finite sequence:

```text
S = ⟨a₁, a₂, ..., aₙ⟩
```

where:

```text
a₁ = bottom element
aₙ = top element
n  = current size
```

### Push

For an element `x`:

```text
push(S, x)
```

produces:

```text
S' = ⟨a₁, a₂, ..., aₙ, x⟩
```

### Pop

For a non-empty stack:

```text
pop(S)
```

returns:

```text
aₙ
```

and produces:

```text
S' = ⟨a₁, a₂, ..., aₙ₋₁⟩
```

### Peek

For a non-empty stack:

```text
peek(S)
```

returns:

```text
aₙ
```

without changing `S`.

This model demonstrates that stack operations act only on the final element of
the abstract sequence.

---

## 19. Preconditions and Postconditions

Defining preconditions and postconditions makes stack behaviour precise.

### 19.1 Push

Preconditions:

- the input element satisfies the stack's value constraints;
- sufficient capacity exists, or the structure can grow.

Postconditions:

- the inserted element becomes the new top;
- the size increases by one;
- previous elements remain in their original order.

### 19.2 Pop

Precondition:

- the stack is not empty.

Postconditions:

- the previous top is returned;
- the previous top is removed;
- the size decreases by one;
- the remaining elements preserve their order.

### 19.3 Peek

Precondition:

- the stack is not empty.

Postconditions:

- the current top is returned;
- the stack remains unchanged.

### 19.4 Is empty

Preconditions:

- none beyond the existence of a valid stack.

Postconditions:

- returns `true` exactly when the size is zero;
- does not modify the structure.

---

## 20. Structural and Behavioural Invariants

A stack implementation must preserve properties that remain true before and
after every valid operation.

Typical invariants include:

### 20.1 Size invariant

```text
size ≥ 0
```

For a bounded stack:

```text
0 ≤ size ≤ capacity
```

### 20.2 Top invariant

When the stack is not empty:

```text
top identifies the most recently inserted remaining element
```

When the stack is empty:

```text
no valid top element exists
```

### 20.3 Ordering invariant

Elements are removed in the reverse order of their insertion.

### 20.4 Reachability invariant

In a linked implementation, every stored node must remain reachable from the top
reference.

### 20.5 Storage invariant

In an array implementation, every active element must remain within the valid
stack range.

### 20.6 Size consistency invariant

The recorded size must equal the number of elements logically stored.

These invariants provide a basis for correctness reasoning and automated
testing.

---

## 21. Correctness Reasoning

A stack operation is correct when it:

- satisfies its specification;
- preserves LIFO order;
- returns the expected value;
- updates the size correctly;
- preserves all invariants;
- handles invalid states according to the contract.

For example, consider `push(x)`.

Before the operation:

```text
Top = B

B
A
```

After the operation:

```text
Top = x

x
B
A
```

The operation is correct because:

1. `x` becomes the top;
2. the previous top remains directly below it;
3. previous elements preserve their order;
4. the size increases by one;
5. the next pop returns `x`.

Correctness is therefore not limited to inserting a value. It also requires
preserving the behavioural contract of the structure.

---

## 22. Expected Complexity

The fundamental stack operations are expected to be efficient.

| Operation | Expected time |
|---|---:|
| `push` | O(1) |
| `pop` | O(1) |
| `peek` | O(1) |
| `isEmpty` | O(1) |
| `size` | O(1) |

These costs assume that:

- the top is directly accessible;
- the size is stored explicitly;
- no traversal is required.

### 22.1 Dynamic-array consideration

A resizable array may occasionally require copying all stored elements into a
larger array.

A particular push may therefore cost:

```text
O(n)
```

However, across a sequence of insertions, push is commonly analysed as:

```text
O(1) amortised
```

The exact complexity is studied in detail in:

```text
04-stack-implementations.md
```

and:

```text
08-complexity-analysis.md
```

---

## 23. Inefficient Stack Designs

A stack implementation should not require traversal to reach the top.

For example, suppose a singly linked list stores the top at the tail but does
not maintain direct access to the predecessor.

Removing the top could require:

```text
Traverse from the head to the final node
```

Resulting complexity:

```text
O(n)
```

This violates the expected constant-time behaviour of pop.

A more appropriate linked-stack design places the top at the head:

```text
Top
 ↓
Node → Node → Node → null
```

Then insertion and removal occur directly at the first node.

The representation should support the intended operations efficiently.

---

## 24. Stack as an Abstract Data Type

The stack abstraction exposes behaviour while hiding representation details.

A user of the structure should reason in terms of:

```text
push
pop
peek
isEmpty
size
```

The user should not need to know whether the internal representation uses:

- an array;
- a linked list;
- a dynamically resized buffer.

This separation supports:

- encapsulation;
- interchangeable implementations;
- easier testing;
- reduced coupling;
- implementation evolution.

Conceptually:

```text
Client code
     ↓
Stack operations
     ↓
Hidden implementation
```

---

## 25. Representative Applications

### 25.1 Method-call management

Programming-language runtimes commonly use a call stack to manage active method
executions.

Each call may store:

- local variables;
- parameters;
- return location;
- execution state.

The most recently called method completes first.

```text
Call method A
    ↓
Call method B
    ↓
Call method C
    ↓
Return from C
    ↓
Return from B
    ↓
Return from A
```

This behaviour is naturally LIFO.

---

### 25.2 Recursion

Recursive calls depend on the runtime stack.

Example:

```text
function(3)
    ↓
function(2)
    ↓
function(1)
    ↓
base case
```

The calls return in reverse order.

```text
return from function(1)
return from function(2)
return from function(3)
```

Understanding stacks therefore helps explain:

- recursive execution;
- stack frames;
- stack depth;
- stack-overflow errors.

---

### 25.3 Delimiter validation

Stacks can verify whether opening and closing symbols are correctly nested.

Example:

```text
{ [ ( ) ] }
```

Opening delimiters are pushed.

Closing delimiters must match the current top.

Conceptually:

```text
Read opening symbol
        ↓
Push it

Read closing symbol
        ↓
Compare with top
        ↓
Pop matching opening symbol
```

Correct nesting depends on LIFO order.

---

### 25.4 Expression evaluation

Stacks support:

- postfix evaluation;
- infix-to-postfix conversion;
- operator processing;
- syntax parsing.

Recent operands or operators frequently need to be processed before earlier
ones.

---

### 25.5 Undo operations

An application may store previous actions in a stack.

```text
Action A
Action B
Action C
```

The first action undone is:

```text
Action C
```

Each undo operation removes the most recent recorded action.

A second stack may be used to support redo behaviour.

---

### 25.6 Backtracking

Backtracking algorithms explore a decision and return to the most recent
unresolved state when necessary.

Examples include:

- maze exploration;
- puzzle solving;
- path finding;
- search-space navigation.

The most recent decision is reconsidered first, which corresponds to LIFO
processing.

---

### 25.7 Depth-first search

Depth-first search explores one path deeply before returning to earlier
alternatives.

It may use:

- the runtime stack through recursion;
- an explicit stack data structure.

The stack stores nodes that remain available for later exploration.

---

### 25.8 Browser navigation

A simplified navigation system may use stacks to record:

- previously visited pages;
- pages available for forward navigation.

When the user selects **Back**, the most recently visited page is recovered
first.

---

## 26. Stack versus General List

A general list may permit:

- insertion at arbitrary positions;
- deletion at arbitrary positions;
- indexed access;
- traversal of all elements.

A stack intentionally restricts these possibilities.

| Characteristic | General list | Stack |
|---|---|---|
| Insertion position | Potentially arbitrary | Top only |
| Removal position | Potentially arbitrary | Top only |
| Direct visible element | Multiple possibilities | Top |
| Ordering policy | Depends on operations | LIFO |
| Interface complexity | Broader | Restricted |

The restriction is beneficial because it expresses a stronger behavioural
contract.

When a problem requires only LIFO behaviour, a stack communicates intent more
clearly than a general-purpose list.

---

## 27. Stack versus Queue

Stacks and queues are both restricted linear data structures, but they preserve
different processing orders.

| Characteristic | Stack | Queue |
|---|---|---|
| Ordering principle | LIFO | FIFO |
| Insertion location | Top | Rear |
| Removal location | Top | Front |
| Next element removed | Most recent | Earliest |
| Representative use | Backtracking | Scheduling |

Example insertion order:

```text
A, B, C
```

Stack removal order:

```text
C, B, A
```

Queue removal order:

```text
A, B, C
```

Choosing between them depends on the required processing policy.

---

## 28. Java Perspective

Java provides several ways to represent stack behaviour.

Historically, Java includes the class:

```text
Stack<E>
```

However, modern Java code commonly uses the `Deque` interface with an
implementation such as:

```text
ArrayDeque<E>
```

Conceptually:

```text
Deque<String> stack = new ArrayDeque<>();
```

Typical stack-style operations include:

```text
stack.push("A");
stack.push("B");

String top = stack.peek();
String removed = stack.pop();
```

The detailed comparison between Java abstractions is developed in:

```text
06-java-deque.md
```

This fundamentals document remains independent of those library-specific
details.

The central principle is:

> Understand the stack contract before selecting a Java implementation.

---

## 29. Null Elements

Whether a stack should accept `null` is an implementation and API-design
decision.

Allowing `null` may create ambiguity.

Suppose:

```text
peek() returns null
```

This could mean:

```text
The stack is empty
```

or:

```text
The top element is null
```

A clear contract should define:

- whether `null` values are permitted;
- how empty-state operations behave;
- whether exceptions or optional results are used.

Standard-library implementations may impose their own restrictions.

---

## 30. Mutability and Encapsulation

A stack should control access to its internal representation.

Client code should not be able to:

- modify the top index directly;
- reconnect internal nodes;
- change the stored size independently;
- bypass push and pop rules.

Poor encapsulation could allow invalid states such as:

```text
size = 4
```

while only three elements are actually stored.

A well-designed stack exposes operations rather than internal state.

This protects structural invariants.

---

## 31. Automated Testing Perspective

A correct stack implementation should be validated through automated tests.

Representative scenarios include:

### Empty-state tests

- a newly created stack is empty;
- its size is zero;
- invalid peek behaviour follows the contract;
- invalid pop behaviour follows the contract.

### Push tests

- pushing one element makes it the top;
- pushing multiple elements preserves LIFO order;
- every push increases the size;
- the stack is no longer empty after insertion.

### Pop tests

- pop returns the current top;
- pop removes exactly one element;
- repeated pops follow reverse insertion order;
- the size decreases correctly;
- removing the final element restores the empty state.

### Peek tests

- peek returns the top;
- peek does not remove the element;
- peek does not modify the size;
- repeated peeks return the same result while the stack remains unchanged.

### Capacity tests

For bounded or resizable stacks:

- insertion at capacity is handled correctly;
- overflow behaviour follows the contract;
- resizing preserves all elements;
- resizing preserves their order.

### Generic-type tests

The implementation should behave correctly with:

- integers;
- strings;
- domain objects;
- other permitted types.

---

## 32. Property-Based Reasoning

Beyond individual examples, stack behaviour can be described through general
properties.

### Push-pop property

For a valid stack `S` and element `x`:

```text
push(S, x)
pop(S)
```

must return:

```text
x
```

and restore the previous logical content of `S`.

### Peek preservation property

For a non-empty stack:

```text
size before peek = size after peek
```

### Size property

After a successful push:

```text
new size = previous size + 1
```

After a successful pop:

```text
new size = previous size - 1
```

### LIFO property

If elements are pushed in the order:

```text
x₁, x₂, ..., xₙ
```

then repeated pop operations return:

```text
xₙ, xₙ₋₁, ..., x₁
```

These properties provide a strong foundation for automated testing.

---

## 33. Common Conceptual Mistakes

Although detailed mistakes are studied later in the module, several fundamental
errors should be recognised early.

### Confusing top with bottom

The top is the next accessible element, not necessarily the first element ever
inserted.

### Removing from the wrong end

A stack must remove from the same end used for insertion.

### Accessing arbitrary elements

Providing unrestricted indexed removal weakens the stack abstraction.

### Ignoring empty-state behaviour

Pop and peek require explicit handling when no elements exist.

### Mismanaging size

The recorded size must be updated exactly once for every successful insertion or
removal.

### Violating LIFO order

Any operation that removes an earlier element while a newer element remains
above it breaks the stack contract.

---

## 34. Problem-Recognition Guide

A stack may be appropriate when the problem contains ideas such as:

- most recent;
- reverse order;
- nested structures;
- return to previous state;
- undo;
- backtrack;
- depth-first;
- matching delimiters;
- pending operations;
- deferred evaluation.

Useful questions include:

```text
Must the most recent item be processed first?
```

```text
Does the problem involve nested structures?
```

```text
Must execution return to the latest unfinished state?
```

```text
Would reversing the processing order solve the problem?
```

If the answer is yes, a stack may be a suitable abstraction.

---

## 35. Engineering Considerations

Selecting and designing a stack requires more than recognising LIFO behaviour.

The engineer should also evaluate:

- expected maximum size;
- memory constraints;
- need for dynamic growth;
- error-handling policy;
- permission or prohibition of `null`;
- thread-safety requirements;
- performance expectations;
- standard-library alternatives;
- observability and debugging needs.

For example, a bounded stack may be appropriate in a system requiring predictable
memory consumption.

A dynamically growing stack may be preferable when workload size is unknown.

An `ArrayDeque` may be suitable for general in-memory Java use, while a custom
implementation may be justified for study, specialised constraints, or
instrumentation.

---

## 36. Relationship with Other Module Documents

This document introduces the conceptual foundation for the remaining
stack-related study.

```text
01-stack-fundamentals.md
        ↓
Defines LIFO behaviour and core operations
        ↓
04-stack-implementations.md
        ↓
Compares physical representations
        ↓
06-java-deque.md
        ↓
Connects the abstraction with Java
        ↓
07-common-algorithms.md
        ↓
Applies stacks to representative problems
        ↓
08-complexity-analysis.md
        ↓
Examines operation costs
        ↓
09-problem-solving-guide.md
        ↓
Develops a systematic solution method
        ↓
10-common-mistakes.md
        ↓
Identifies recurring errors
```

It also supports later modules involving:

- trees;
- graphs;
- recursion;
- backtracking;
- expression processing.

---

## 37. Study Questions

After studying this document, the learner should be able to answer:

1. What defines a stack as an abstract data type?
2. Why is stack behaviour described as LIFO?
3. What is the top of a stack?
4. What is the difference between `pop` and `peek`?
5. What happens when `pop` is applied to an empty stack?
6. What is the difference between stack size and capacity?
7. Which invariants must a stack preserve?
8. Why should push and pop generally be O(1)?
9. How does a stack support recursion?
10. Why is a stack useful for delimiter validation?
11. How does a stack differ from a queue?
12. Why should representation details remain encapsulated?
13. What test scenarios provide evidence of LIFO correctness?
14. When should an engineer select a bounded stack?
15. Why is understanding the abstraction necessary before using Java collections?

---

## 38. Completion Criteria

This document may be considered complete when the learner can:

- define a stack without referring to a specific programming language;
- explain LIFO behaviour using an operation trace;
- identify the top element;
- specify push, pop, peek, is-empty, and size operations;
- define operation preconditions and postconditions;
- explain underflow and overflow;
- identify stack invariants;
- distinguish size from capacity;
- recognise representative applications;
- analyse the expected complexity of fundamental operations;
- compare stacks with general lists and queues;
- identify stack-oriented problems;
- design representative automated tests.

---

## 39. Key Takeaways

After completing this document, the learner should understand that:

- a stack is a linear abstract data type;
- stack behaviour is defined by the LIFO principle;
- insertion and removal occur at the top;
- `push` inserts an element;
- `pop` removes and returns the top element;
- `peek` inspects the top without modifying the structure;
- empty-stack operations require explicit handling;
- bounded stacks may experience overflow;
- correct implementations preserve ordering, size, and top invariants;
- fundamental stack operations should normally execute in constant time;
- arrays and linked nodes can both implement the same stack abstraction;
- stacks model recursion, backtracking, expression processing, undo behaviour,
  and depth-first exploration;
- automated testing should verify both results and behavioural properties;
- implementation decisions should follow the stack contract rather than define
  it.

Understanding these fundamentals prepares the learner to compare stack
implementations, use Java's `Deque` abstraction appropriately, and solve
algorithms based on LIFO processing.