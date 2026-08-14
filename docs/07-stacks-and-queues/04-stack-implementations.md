# Stack Implementations

## Algorithms Java Mastery

A stack is defined by its **Last In, First Out** behaviour, but this abstract
behaviour can be implemented through different internal representations.

The most common implementation strategies are:

```text
Array-Based Stack
```

and:

```text
Linked Stack
```

Both implementations support the same fundamental operations:

- push;
- pop;
- peek;
- is empty;
- size.

However, they differ in:

- memory organisation;
- capacity management;
- allocation behaviour;
- cache locality;
- resizing cost;
- implementation complexity.

Understanding these differences is essential because selecting a data structure
requires more than knowing its asymptotic complexity.

The same abstract operation may have similar Big-O behaviour in two
implementations while producing different practical performance, memory usage,
and engineering trade-offs.

This document studies stack implementations from first principles before
relating them to Java's standard collections.

---

## 1. Academic Foundation

A stack is an abstract data type whose behaviour is independent of its physical
representation.

Conceptually:

```text
Stack Abstract Data Type
          ↓
Defines permitted operations
          ↓
push, pop, peek, isEmpty, size
```

An implementation provides the concrete mechanism used to preserve this
behaviour.

```text
Stack Implementation
          ↓
Defines internal organisation
          ↓
Array or linked nodes
```

This distinction is fundamental.

The stack abstraction specifies:

- which element becomes accessible after insertion;
- which element is removed;
- which order must be preserved;
- what happens in empty or full states.

The implementation determines:

- where elements are stored;
- how the top is represented;
- how capacity is managed;
- which memory costs are incurred;
- how operations achieve the required complexity.

The theoretical direction of this document is based on classical data
structures and algorithm analysis. The explanations, diagrams, specifications,
and engineering interpretations are original and adapted to the academic
objectives of **Algorithms Java Mastery**.

The repository's bibliographic foundation is maintained in the project
documentation.

```text
docs/00-project/
```

---

## 2. Purpose

The purpose of this document is to compare the principal strategies used to
implement a stack.

The learner will study:

- fixed-capacity array stacks;
- dynamically resized array stacks;
- linked-node stacks;
- top representation;
- capacity management;
- resizing strategies;
- underflow and overflow;
- structural invariants;
- correctness reasoning;
- computational complexity;
- memory and performance trade-offs;
- Java implementation direction.

The objective is not merely to reproduce code.

The learner should understand why each implementation works, which invariants it
must preserve, and under which conditions it should be selected.

---

## 3. Learning Objectives

After completing this document, the learner should be able to achieve the
following objectives.

### 3.1 Conceptual understanding

- distinguish the stack abstraction from its implementations;
- explain how arrays and linked nodes represent stack elements;
- identify the role of the top in each implementation;
- compare fixed and dynamic capacity.

### 3.2 Algorithmic reasoning

- design push and pop operations;
- determine the correct order of state updates;
- reason about boundary conditions;
- preserve LIFO order independently of representation.

### 3.3 Correctness reasoning

- define implementation invariants;
- establish preconditions and postconditions;
- verify size and top consistency;
- reason about resizing and node reachability.

### 3.4 Complexity analysis

- derive the cost of each stack operation;
- explain amortised insertion;
- compare array and linked implementations;
- recognise inefficient designs.

### 3.5 Engineering perspective

- evaluate memory overhead;
- consider cache locality and allocation cost;
- select a suitable implementation;
- design representative automated tests;
- separate public behaviour from internal representation.

---

## 4. Implementation Requirements

Every correct stack implementation must preserve the same behavioural contract.

Given elements inserted in this order:

```text
A, B, C, D
```

repeated removal must return:

```text
D, C, B, A
```

The implementation must therefore guarantee:

```text
Last inserted remaining element
              ↓
Current top
              ↓
Next element removed
```

Regardless of representation, the implementation must support:

| Operation | Required behaviour |
|---|---|
| `push(x)` | Insert `x` as the new top |
| `pop()` | Remove and return the current top |
| `peek()` | Return the current top without removal |
| `isEmpty()` | Report whether no elements are stored |
| `size()` | Return the number of stored elements |

A bounded implementation may additionally support:

| Operation | Required behaviour |
|---|---|
| `isFull()` | Report whether capacity has been reached |
| `capacity()` | Return the maximum supported size |

---

## 5. Representation Alternatives

The main implementation alternatives studied in this module are:

```text
Stack
├── Fixed-Capacity Array Stack
├── Dynamically Resized Array Stack
└── Linked Stack
```

Each representation stores the same logical sequence.

Suppose the abstract stack contains:

```text
Bottom → A, B, C, D ← Top
```

An array representation may store:

```text
Index:   0   1   2   3   4   5
Value:  [A] [B] [C] [D] [_] [_]
                     ↑
                    Top
```

A linked representation may store:

```text
Top
 ↓
[D] → [C] → [B] → [A] → null
```

The physical organisation differs, but the logical LIFO order remains the same.

---

## 6. Array-Based Stack

An array-based stack stores elements in contiguous positions.

Conceptually:

```text
Index:   0   1   2   3   4
Value:  [A] [B] [C] [_] [_]
                 ↑
                Top
```

The first inserted element is usually stored at the lowest active index.

Each new element is placed in the next available position.

The implementation requires a way to identify:

- the current number of elements;
- the top element;
- the next available position;
- the total capacity.

---

## 7. Representing the Top in an Array

There are several valid conventions for representing the top.

### 7.1 Top as the current top index

For a stack containing three elements:

```text
Index:   0   1   2   3   4
Value:  [A] [B] [C] [_] [_]
                 ↑
               top = 2
```

For an empty stack:

```text
top = -1
```

With this convention:

```text
size = top + 1
```

Push generally performs:

```text
top = top + 1
elements[top] = value
```

Pop generally performs:

```text
value = elements[top]
top = top - 1
return value
```

---

### 7.2 Top as the next available position

For a stack containing three elements:

```text
Index:   0   1   2   3   4
Value:  [A] [B] [C] [_] [_]
                     ↑
                   top = 3
```

Here `top` identifies the next insertion position.

The current top element is located at:

```text
top - 1
```

For an empty stack:

```text
top = 0
```

With this convention:

```text
size = top
```

Push generally performs:

```text
elements[top] = value
top = top + 1
```

Pop generally performs:

```text
top = top - 1
value = elements[top]
return value
```

---

### 7.3 Size as the primary state variable

The implementation may store only:

```text
size
```

Then:

```text
next insertion position = size
current top position = size - 1
```

For an empty stack:

```text
size = 0
```

This convention often improves clarity because the same variable represents the
logical number of elements.

---

## 8. Choosing a Representation Convention

Any of the previous conventions may be correct.

The essential requirement is consistency.

A common source of errors is combining formulas from different conventions.

For example:

```text
top = current top index
```

cannot be treated as though it were:

```text
top = next available position
```

Otherwise, the implementation may produce:

- skipped positions;
- overwritten elements;
- incorrect empty-state detection;
- off-by-one errors;
- invalid array access.

The chosen convention should be documented explicitly.

A clear implementation should answer:

```text
What exactly does top represent?
```

---

## 9. Fixed-Capacity Array Stack

A fixed-capacity stack allocates an array of predetermined size.

Example:

```text
capacity = 5
```

Internal storage:

```text
Index:   0   1   2   3   4
Value:  [_] [_] [_] [_] [_]
```

The stack may contain at most five elements.

After inserting `A`, `B`, and `C`:

```text
Index:   0   1   2   3   4
Value:  [A] [B] [C] [_] [_]
```

The logical stack is:

```text
Bottom → A, B, C ← Top
```

---

## 10. Fixed-Capacity Push

The push operation inserts the element in the next available array position.

Suppose:

```text
size = 3
capacity = 5
```

Before:

```text
Index:   0   1   2   3   4
Value:  [A] [B] [C] [_] [_]
```

Operation:

```text
push(D)
```

After:

```text
Index:   0   1   2   3   4
Value:  [A] [B] [C] [D] [_]
```

New state:

```text
size = 4
top element = D
```

### 10.1 Preconditions

A bounded push requires:

```text
size < capacity
```

### 10.2 Postconditions

After a successful push:

- the element occupies the previous next-available position;
- the inserted element becomes the top;
- size increases by one;
- previous elements remain unchanged;
- LIFO order is preserved.

### 10.3 Conceptual pseudocode

```text
PUSH(stack, value)

    if stack.size = stack.capacity
        report overflow

    stack.elements[stack.size] = value
    stack.size = stack.size + 1
```

---

## 11. Fixed-Capacity Pop

The pop operation reads and removes the top element.

Before:

```text
Index:   0   1   2   3   4
Value:  [A] [B] [C] [D] [_]
                     ↑
                    Top
```

Operation:

```text
pop()
```

Returned:

```text
D
```

After:

```text
Index:   0   1   2   3   4
Value:  [A] [B] [C] [_] [_]
                 ↑
                Top
```

New state:

```text
size = 3
```

### 11.1 Preconditions

A pop requires:

```text
size > 0
```

### 11.2 Postconditions

After a successful pop:

- the previous top is returned;
- the previous top is no longer logically stored;
- size decreases by one;
- the previous second element becomes the top;
- remaining order is preserved.

### 11.3 Conceptual pseudocode

```text
POP(stack)

    if stack.size = 0
        report underflow

    stack.size = stack.size - 1
    value = stack.elements[stack.size]
    stack.elements[stack.size] = empty
    return value
```

The final clearing operation is not always required for abstract correctness,
but it may be important in managed-memory environments.

---

## 12. Clearing Removed Array References

In Java, an array-based stack may store object references.

Suppose the internal array contains:

```text
[A] [B] [C] [D] [_]
```

After logically removing `D`, reducing the size is enough to make `D`
inaccessible through the stack abstraction.

However, if the array still contains the reference:

```text
[A] [B] [C] [D] [_]
             ↑
       outside active range
```

the array continues to reference that object.

This may delay garbage collection.

A defensive implementation may clear the released position:

```text
elements[size] = null;
```

Conceptually:

```text
Before clear:

[A] [B] [C] [D] [_]

After clear:

[A] [B] [C] [_] [_]
```

This practice is sometimes described as avoiding obsolete references or
unintended object retention.

---

## 13. Fixed-Capacity Peek

Peek reads the element at:

```text
size - 1
```

without changing size.

Before:

```text
[A] [B] [C]
         ↑
        Top
```

Operation:

```text
peek()
```

Returns:

```text
C
```

State remains:

```text
[A] [B] [C]
```

Conceptual pseudocode:

```text
PEEK(stack)

    if stack.size = 0
        report underflow

    return stack.elements[stack.size - 1]
```

---

## 14. Fixed-Capacity Array Invariants

A correct fixed-capacity implementation should preserve several invariants.

### 14.1 Size bounds

```text
0 ≤ size ≤ capacity
```

### 14.2 Active range

Logical elements occupy:

```text
indices 0 through size - 1
```

### 14.3 Top position

When non-empty:

```text
top element = elements[size - 1]
```

### 14.4 Empty state

When:

```text
size = 0
```

no logical top exists.

### 14.5 Full state

When:

```text
size = capacity
```

no additional element may be inserted without changing capacity.

### 14.6 Ordering

The insertion order is preserved from lower to higher active indices.

The highest active index contains the most recently inserted element.

---

## 15. Advantages of a Fixed-Capacity Array Stack

A fixed-capacity array stack offers several benefits.

### Predictable memory usage

The required storage is allocated in advance.

```text
Maximum memory commitment
        ↓
Known from capacity
```

### Constant-time operations

Push, pop, and peek normally require direct index access.

### Good memory locality

Elements are stored in contiguous array positions.

This often improves cache behaviour compared with separately allocated nodes.

### Low per-element overhead

No extra node object or next reference is required for each element.

### Simple representation

The internal state may require only:

- an array;
- a size or top variable.

---

## 16. Limitations of a Fixed-Capacity Array Stack

### Fixed maximum size

The implementation cannot exceed its predetermined capacity.

### Overflow handling

An insertion may fail even when additional system memory exists.

### Capacity estimation

Selecting an unnecessarily large capacity wastes storage.

Selecting a capacity that is too small causes premature overflow.

### Generic-array complications in Java

Creating arrays of a generic type requires careful implementation because Java
does not permit direct generic-array creation in the usual form.

### Possible memory retention

Removed object references should be cleared when appropriate.

---

## 17. Dynamically Resized Array Stack

A dynamically resized stack uses an array whose capacity changes according to
the number of stored elements.

Initial state:

```text
capacity = 2
size = 0

[_] [_]
```

After pushing `A` and `B`:

```text
capacity = 2
size = 2

[A] [B]
```

A new push requires more storage.

The implementation allocates a larger array:

```text
capacity = 4

[_] [_] [_] [_]
```

Then copies the existing elements:

```text
[A] [B] [_] [_]
```

Finally, it inserts `C`:

```text
[A] [B] [C] [_]
```

---

## 18. Growth Strategy

A dynamic stack requires a rule for selecting the new capacity.

A common strategy is geometric growth.

For example:

```text
new capacity = old capacity × 2
```

Possible capacity sequence:

```text
2 → 4 → 8 → 16 → 32
```

Other growth factors may be used.

The main objective is to avoid increasing capacity by only one position for
every insertion.

---

## 19. Why Increasing Capacity by One Is Inefficient

Suppose the array grows by one position each time it becomes full.

Capacity sequence:

```text
1 → 2 → 3 → 4 → 5 → ...
```

Every growth may require copying all existing elements.

To reach size `n`, the total number of copied elements may be approximately:

```text
1 + 2 + 3 + ... + (n - 1)
```

This grows quadratically:

```text
O(n²)
```

Therefore, repeated push operations become inefficient.

Geometric growth reduces the frequency of resizing and supports amortised
constant-time insertion.

---

## 20. Dynamic Push Without Resizing

When capacity remains available, push behaves like fixed-array insertion.

Before:

```text
capacity = 8
size = 4

[A] [B] [C] [D] [_] [_] [_] [_]
```

Operation:

```text
push(E)
```

After:

```text
capacity = 8
size = 5

[A] [B] [C] [D] [E] [_] [_] [_]
```

Time complexity:

```text
O(1)
```

---

## 21. Dynamic Push with Resizing

When:

```text
size = capacity
```

the operation requires additional work.

Conceptual process:

```text
Detect full array
        ↓
Allocate larger array
        ↓
Copy existing elements
        ↓
Replace internal array reference
        ↓
Insert new element
        ↓
Increase size
```

If the stack contains `n` elements, copying costs:

```text
O(n)
```

Therefore, one resizing push may have:

```text
Worst-case time = O(n)
```

However, resizing does not occur on every insertion.

Across a long sequence of pushes:

```text
Amortised push time = O(1)
```

---

## 22. Amortised Analysis Intuition

Amortised analysis distributes the cost of occasional expensive operations
across a sequence of inexpensive operations.

Consider capacities:

```text
1 → 2 → 4 → 8 → 16
```

Resizing occurs only when the structure reaches certain sizes.

Most pushes perform only:

```text
one array assignment
one size update
```

A small number of pushes also copy existing elements.

The expensive operations are sufficiently infrequent that the average cost per
operation over the full sequence remains bounded by a constant.

This does not mean every push is O(1).

It means:

```text
Worst-case push = O(n)
Amortised push = O(1)
```

---

## 23. Shrinking Strategy

A dynamic stack may reduce its capacity when many positions are unused.

Example:

```text
capacity = 16
size = 3
```

The implementation may allocate a smaller array to reduce unused storage.

However, shrinking requires careful design.

If the array shrinks whenever:

```text
size = capacity / 2
```

alternating push and pop operations near the threshold may trigger repeated
growth and shrinkage.

This behaviour is sometimes called **thrashing**.

A safer approach uses different thresholds.

For example:

```text
Grow when:
size = capacity
```

```text
Shrink when:
size ≤ capacity / 4
```

Then the new capacity might be:

```text
capacity / 2
```

The separation between growth and shrink thresholds reduces repeated resizing.

---

## 24. Minimum Capacity

A dynamically resized stack may preserve a minimum internal capacity.

Example:

```text
minimum capacity = 8
```

Even when the stack becomes empty, the implementation may avoid shrinking below
this value.

Reasons include:

- avoiding repeated allocation;
- maintaining expected short-term capacity;
- reducing resizing frequency;
- simplifying implementation.

The exact policy is an engineering decision.

---

## 25. Dynamic Array Invariants

In addition to normal array-stack invariants, a dynamic implementation should
preserve:

### Capacity invariant

```text
capacity = length of internal array
```

### Size-capacity relationship

```text
0 ≤ size ≤ capacity
```

### Growth preservation

After resizing:

- every logical element remains present;
- element order remains unchanged;
- the top remains the same logical element;
- size remains unchanged during copying.

### Shrink preservation

Shrinking must never create:

```text
capacity < size
```

### Minimum-capacity invariant

If a minimum is defined:

```text
capacity ≥ minimum capacity
```

---

## 26. Dynamic Resizing Correctness

Suppose the original stack is:

```text
[A, B, C, D]
```

with:

```text
size = 4
capacity = 4
```

After resizing, the new internal array should represent:

```text
[A, B, C, D, _, _, _, _]
```

The logical stack must remain:

```text
Bottom → A, B, C, D ← Top
```

Resizing is correct when:

1. every active element is copied;
2. no element is duplicated or omitted;
3. relative order remains unchanged;
4. size remains four;
5. `D` remains the top;
6. the next insertion occurs after `D`.

Resizing changes physical capacity, not logical content.

---

## 27. Generic Array Stack in Java

A generic stack may be declared conceptually as:

```text
public final class ArrayStack<E> {
    private E[] elements;
    private int size;
}
```

However, Java does not allow direct creation such as:

```text
new E[capacity]
```

because generic type information is erased at runtime.

One common implementation strategy uses an `Object[]` internally:

```text
private Object[] elements;
```

Values are cast when returned.

Another strategy creates a generic-looking array through a controlled unchecked
cast.

Both approaches require:

- careful encapsulation;
- limited and justified casting;
- protection against invalid element types;
- clear documentation.

The implementation should prevent the internal array from escaping to client
code.

---

## 28. Simplified Dynamic Array Stack Design

A possible internal design is:

```text
public final class ArrayStack<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private Object[] elements;
    private int size;

    public ArrayStack() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }
}
```

Conceptually, the primary invariants are:

```text
0 ≤ size ≤ elements.length
```

and:

```text
logical elements occupy indices 0 through size - 1
```

The implementation methods should preserve these invariants.

---

## 29. Illustrative Array Push in Java

A simplified implementation may follow this structure:

```text
public void push(E element) {
    ensureCapacityForInsertion();
    elements[size] = element;
    size++;
}
```

The operation order is meaningful.

```text
Ensure capacity
      ↓
Store element
      ↓
Increase size
```

If size were increased before ensuring storage, an exception could leave the
stack in an inconsistent state.

---

## 30. Illustrative Array Pop in Java

A simplified implementation may be:

```text
@SuppressWarnings("unchecked")
public E pop() {
    ensureNotEmpty();

    int topIndex = size - 1;
    E value = (E) elements[topIndex];

    elements[topIndex] = null;
    size--;

    return value;
}
```

The method:

- verifies the precondition;
- identifies the top index;
- saves the value;
- clears the obsolete reference;
- decreases size;
- returns the removed element.

The exact order may vary, but the implementation must remain exception-safe and
preserve invariants.

---

## 31. Illustrative Array Peek in Java

A simplified peek may be:

```text
@SuppressWarnings("unchecked")
public E peek() {
    ensureNotEmpty();
    return (E) elements[size - 1];
}
```

Peek must not modify:

- the array;
- size;
- capacity;
- element order.

---

## 32. Linked Stack

A linked stack stores each element in a separate node.

Each node contains:

```text
Element
Reference to next node
```

Conceptually:

```text
+-----------+
| element   |
| next      |
+-----------+
```

The stack's top is represented by a reference to the first node.

Example:

```text
Top
 ↓
[D] → [C] → [B] → [A] → null
```

Here:

```text
D = most recently inserted element
A = earliest remaining element
```

---

## 33. Why the Top Should Be the Head

A singly linked list supports constant-time insertion and removal at its head.

Therefore, a linked stack should normally use:

```text
head = top
```

Push becomes insertion at the head.

Pop becomes removal from the head.

Conceptually:

```text
Top
 ↓
Node → Node → Node → null
```

Both operations require only a small number of reference updates.

Using the tail as the top in a singly linked list may make pop inefficient
because finding the predecessor of the tail requires traversal.

---

## 34. Linked Node Structure

A generic node may be represented conceptually as:

```text
private static final class Node<E> {

    private final E element;
    private final Node<E> next;

    private Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
    }
}
```

This immutable-link node design is suitable when nodes are created during push
and discarded during pop.

Another implementation may use mutable references.

The essential requirement is that each node preserves the link to the next
element below it in the stack.

---

## 35. Linked Push

Before:

```text
Top
 ↓
[B] → [A] → null
```

Operation:

```text
push(C)
```

A new node is created:

```text
[C]
```

Its next reference points to the previous top:

```text
[C] → [B] → [A] → null
```

The top reference is updated:

```text
Top
 ↓
[C] → [B] → [A] → null
```

### 35.1 Conceptual pseudocode

```text
PUSH(stack, value)

    newNode = Node(value)
    newNode.next = stack.top
    stack.top = newNode
    stack.size = stack.size + 1
```

### 35.2 Postconditions

After push:

- a new node exists;
- the new node stores the inserted element;
- the new node references the previous top;
- top references the new node;
- size increases by one;
- previous nodes remain reachable;
- LIFO order is preserved.

---

## 36. Linked Pop

Before:

```text
Top
 ↓
[C] → [B] → [A] → null
```

Operation:

```text
pop()
```

The current top value is saved:

```text
C
```

The top reference moves to the next node:

```text
Top
 ↓
[B] → [A] → null
```

Returned:

```text
C
```

### 36.1 Conceptual pseudocode

```text
POP(stack)

    if stack.top = null
        report underflow

    removed = stack.top
    stack.top = removed.next
    stack.size = stack.size - 1

    return removed.value
```

### 36.2 Postconditions

After pop:

- the previous top value is returned;
- the previous top node is no longer part of the stack;
- top references the next node;
- size decreases by one;
- remaining nodes preserve their order.

---

## 37. Linked Peek

Peek returns the value stored in the top node.

Conceptually:

```text
Top
 ↓
[C] → [B] → [A] → null
```

Operation:

```text
peek()
```

Returns:

```text
C
```

The structure remains unchanged.

Conceptual pseudocode:

```text
PEEK(stack)

    if stack.top = null
        report underflow

    return stack.top.value
```

---

## 38. Linked Empty State

An empty linked stack commonly uses:

```text
top = null
size = 0
```

Conceptually:

```text
Top
 ↓
null
```

After the first push:

```text
Top
 ↓
[A] → null
```

After removing the final node:

```text
Top
 ↓
null
```

The size and top reference must agree.

Invalid state:

```text
size = 0
top references a node
```

Another invalid state:

```text
size > 0
top = null
```

---

## 39. Linked Stack Invariants

### 39.1 Size invariant

```text
size ≥ 0
```

### 39.2 Empty-state equivalence

A common invariant is:

```text
size = 0 if and only if top = null
```

### 39.3 Top invariant

When non-empty:

```text
top references the most recently inserted remaining node
```

### 39.4 Reachability invariant

Starting from top and repeatedly following `next` must reach every stored node.

### 39.5 Termination invariant

For a non-circular stack:

```text
the final node references null
```

### 39.6 Node-count invariant

The number of reachable nodes equals the recorded size.

### 39.7 Ordering invariant

Following references from top produces reverse insertion order.

---

## 40. Linked Push Correctness

Suppose the original stack is:

```text
Top
 ↓
[B] → [A] → null
```

After `push(C)`:

```text
Top
 ↓
[C] → [B] → [A] → null
```

The operation is correct because:

1. the new node stores `C`;
2. it references the old top;
3. all previous nodes remain reachable;
4. the new top is `C`;
5. size increases by one;
6. the next pop returns `C`;
7. the remaining order is unchanged.

The critical reference order is:

```text
newNode.next = oldTop
        ↓
top = newNode
```

If the top were overwritten before preserving the old reference, the existing
stack could become unreachable.

---

## 41. Linked Pop Correctness

Suppose:

```text
Top
 ↓
[C] → [B] → [A] → null
```

After pop:

```text
Top
 ↓
[B] → [A] → null
```

The operation is correct because:

1. the old top value `C` is returned;
2. top moves exactly one node;
3. `B` becomes the new top;
4. `A` remains reachable;
5. size decreases by one;
6. remaining order is preserved.

When the original size is one:

```text
Top
 ↓
[A] → null
```

after pop:

```text
Top
 ↓
null
```

and:

```text
size = 0
```

The empty-state invariant remains valid.

---

## 42. Garbage Collection and Removed Nodes

After a linked pop, the removed node may become unreachable.

In Java:

```text
No remaining reference
        ↓
Node becomes eligible for garbage collection
```

The programmer does not manually deallocate the node.

However, correctness still requires removing internal references appropriately.

If another long-lived object retains the node, it will remain reachable and
cannot be collected.

Memory management is automatic, but reference management remains an engineering
responsibility.

---

## 43. Illustrative Linked Stack Design in Java

A simplified generic structure may be:

```text
public final class LinkedStack<E> {

    private Node<E> top;
    private int size;

    private static final class Node<E> {
        private final E element;
        private final Node<E> next;

        private Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
```

This representation directly models:

```text
Top → Next → Next → null
```

---

## 44. Illustrative Linked Push in Java

```text
public void push(E element) {
    top = new Node<>(element, top);
    size++;
}
```

The expression:

```text
new Node<>(element, top)
```

creates a node whose next reference points to the previous top.

Then the stack's top becomes the new node.

---

## 45. Illustrative Linked Pop in Java

```text
public E pop() {
    ensureNotEmpty();

    E value = top.element;
    top = top.next;
    size--;

    return value;
}
```

The implementation:

- validates the non-empty precondition;
- preserves the top value;
- advances the top reference;
- updates size;
- returns the removed value.

---

## 46. Illustrative Linked Peek in Java

```text
public E peek() {
    ensureNotEmpty();
    return top.element;
}
```

Peek reads the top element without changing the structure.

---

## 47. Advantages of a Linked Stack

### Dynamic growth

The stack grows one node at a time.

There is no fixed logical capacity other than available memory.

### Constant-time operations

Push, pop, and peek operate directly at the head.

### No resizing copies

Existing elements are not copied when the stack grows.

### Natural structural model

The node chain directly represents the logical order from top to bottom.

### Stable node positions

Existing nodes are not relocated when new elements are inserted.

---

## 48. Limitations of a Linked Stack

### Per-node memory overhead

Each element requires:

- a node object;
- an element reference;
- a next reference;
- object metadata determined by the runtime.

### Allocation cost

Each push generally creates a new node.

### Garbage-collection pressure

Repeated insertion and removal may create many short-lived objects.

### Reduced memory locality

Nodes may occupy unrelated memory locations.

Traversal through references may be less cache-friendly than array access.

### Additional structural risks

Incorrect reference updates can:

- lose nodes;
- create cycles;
- invalidate size;
- break reachability.

---

## 49. Complexity Comparison

| Operation | Fixed Array | Dynamic Array | Linked Stack |
|---|---:|---:|---:|
| Push without resize | O(1) | O(1) | O(1) |
| Push with resize | Not applicable | O(n) | O(1) |
| Amortised push | O(1) | O(1) | O(1) |
| Pop | O(1) | O(1) | O(1) |
| Peek | O(1) | O(1) | O(1) |
| Is empty | O(1) | O(1) | O(1) |
| Size | O(1) | O(1) | O(1) |
| Capacity growth | None | Array copy | One node allocation |

The table demonstrates that asymptotic complexity alone does not determine the
best implementation.

---

## 50. Space Complexity Comparison

For `n` stored elements:

### Fixed array

```text
O(capacity)
```

The array reserves storage for the full configured capacity, even when some
positions are unused.

### Dynamic array

```text
O(capacity)
```

Capacity is usually greater than or equal to size.

Unused positions may exist because of geometric growth.

### Linked stack

```text
O(n)
```

Each stored element requires one node.

However, the constant memory overhead per element is usually greater because
every node stores structural information.

---

## 51. Time-Space Trade-Off

An array stack may reserve unused positions to reduce future allocation and
resizing.

A linked stack allocates storage incrementally but adds structural overhead to
every element.

Conceptually:

```text
Array stack
    ↓
Unused capacity may exist
    ↓
Lower per-element overhead
```

```text
Linked stack
    ↓
No reserved array capacity
    ↓
Higher per-element overhead
```

The appropriate trade-off depends on workload characteristics.

---

## 52. Cache Locality

Array elements occupy contiguous positions.

Conceptually:

```text
[A][B][C][D]
```

This arrangement generally offers favourable memory locality.

Linked nodes may be distributed:

```text
[D] → [C] → [B] → [A]
```

Their physical locations may be unrelated.

Although push and pop use only the top, locality can still influence:

- object access;
- allocation behaviour;
- iteration;
- practical benchmark results.

This explains why two O(1) implementations may perform differently.

---

## 53. Allocation Behaviour

### Dynamic array stack

Allocation occurs primarily when capacity grows.

Most pushes reuse existing array positions.

```text
Many pushes
    ↓
Few large allocations
```

### Linked stack

Every push usually allocates one node.

```text
Every push
    ↓
One node allocation
```

The linked implementation avoids array copying but increases allocation
frequency.

This trade-off should be evaluated experimentally where performance matters.

---

## 54. Capacity Predictability

A fixed-capacity stack offers strong predictability.

The maximum number of elements is known in advance.

A dynamic array offers:

- flexible growth;
- occasional large allocations;
- unused capacity after growth.

A linked stack offers:

- incremental growth;
- no fixed structural capacity;
- one allocation per element;
- less predictable total memory overhead.

Applications with strict memory constraints may prefer bounded structures.

---

## 55. Failure Behaviour

Different implementations may fail under different conditions.

### Fixed array stack

May reject push when:

```text
size = capacity
```

### Dynamic array stack

May fail when:

- a larger array cannot be allocated;
- requested capacity exceeds implementation limits.

### Linked stack

May fail when:

- a new node cannot be allocated;
- available memory is exhausted.

All structures remain limited by finite system resources.

The term **unbounded** usually means that the implementation has no predefined
logical capacity, not that it can grow infinitely.

---

## 56. Array Stack versus Linked Stack

| Characteristic | Array Stack | Linked Stack |
|---|---|---|
| Storage | Contiguous array | Separate nodes |
| Capacity | Fixed or dynamic | Dynamic |
| Push | O(1), amortised if resized | O(1) |
| Pop | O(1) | O(1) |
| Per-element overhead | Lower | Higher |
| Unused reserved storage | Possible | Normally no |
| Resizing | Possible | Not required |
| Allocation frequency | Occasional | Usually every push |
| Cache locality | Generally better | Generally weaker |
| Structural references | Not required per element | Required |
| Implementation risks | Bounds and resizing errors | Reference errors |

---

## 57. Selection Guidelines

An array-based stack may be appropriate when:

- memory locality matters;
- per-element overhead should be low;
- the expected size is known or manageable;
- resizing is acceptable;
- high operation throughput is important;
- Java standard collections already provide a suitable implementation.

A linked stack may be appropriate when:

- strict contiguous capacity is undesirable;
- elements should grow one node at a time;
- node-based representation is educationally relevant;
- no array resizing is desired;
- the structure must integrate with existing linked nodes.

A fixed-capacity array may be appropriate when:

- maximum size is known;
- memory usage must be predictable;
- overflow is meaningful;
- the system is resource constrained.

---

## 58. Encapsulation

A stack implementation should hide its internal representation.

Client code should not access:

- the internal array;
- the top index;
- the size field;
- linked nodes;
- next references;
- capacity-management methods.

The public abstraction should expose only meaningful stack operations.

Conceptually:

```text
Client
  ↓
Stack API
  ↓
Hidden array or node structure
```

Encapsulation prevents clients from violating invariants.

For example, exposing the internal array could allow a client to overwrite an
element below the top without using stack operations.

---

## 59. Interface-Based Design

A common design separates the stack contract from its implementations.

Conceptually:

```java
public interface Stack<E> {

    void push(E element);

    E pop();

    E peek();

    boolean isEmpty();

    int size();
}
```

Possible implementations:

```text
Stack<E>
├── ArrayStack<E>
└── LinkedStack<E>
```

Benefits include:

- interchangeable implementations;
- clearer behavioural contract;
- reduced client coupling;
- easier testing;
- improved extensibility.

Client code can depend on:

```text
Stack<E>
```

rather than on one concrete representation.

---

## 60. Exception Semantics

The stack contract must define what happens when `pop` or `peek` is called on an
empty structure.

Possible strategies include:

### Throw an exception

```text
Empty operation
      ↓
NoSuchElementException
```

or a custom exception.

### Return an optional result

```text
Optional<E>
```

### Return a special value

Such as `null`, although this may be ambiguous.

The selected approach should remain consistent across implementations.

An array stack and a linked stack implementing the same interface should not
silently use incompatible failure semantics.

---

## 61. Null-Element Policy

The implementation should define whether `null` may be inserted.

Allowing `null` affects:

- empty-result semantics;
- test design;
- API clarity;
- compatibility with Java collections.

If `null` is prohibited, push should validate the element.

If `null` is permitted, pop and peek failure behaviour should remain
unambiguous.

This is a contract decision rather than a property inherent to stacks.

---

## 62. Defensive Validation

A robust implementation may validate:

- non-negative initial capacity;
- maximum capacity limits;
- prohibited null values;
- empty-state operations;
- internal growth overflow.

Example constructor requirement:

```text
initial capacity > 0
```

An invalid capacity should be rejected explicitly rather than producing an
invalid internal state.

---

## 63. Integer Overflow during Growth

A dynamic array implementation may calculate:

```text
new capacity = old capacity × 2
```

For very large arrays, this calculation may exceed the range of the integer
type.

A production-quality implementation should consider:

- maximum supported array length;
- arithmetic overflow;
- allocation failure;
- meaningful exceptions.

These concerns may not appear in introductory examples but are important in
professional implementations.

---

## 64. Correctness through Representation Invariants

Correctness can be established by proving that each operation preserves the
representation invariants.

For an array stack:

```text
0 ≤ size ≤ elements.length
```

and:

```text
active elements occupy positions 0 through size - 1
```

For a linked stack:

```text
size = number of nodes reachable from top
```

and:

```text
size = 0 ⇔ top = null
```

A correct method should satisfy:

```text
Valid state before operation
          ↓
Operation executes
          ↓
Valid state after operation
```

This reasoning is stronger than checking only returned values.

---

## 65. Push Proof Outline

To reason about push:

### Array implementation

1. Verify that capacity exists or create additional capacity.
2. Store the new element at index `size`.
3. Increase size by one.
4. The new element is now at index `size - 1`.
5. Previous elements remain in their original positions.
6. The new element is therefore the top.

### Linked implementation

1. Create a node storing the new element.
2. Connect it to the previous top.
3. Update top to the new node.
4. Increase size by one.
5. All previous nodes remain reachable.
6. The new node is therefore the top.

Both implementations satisfy the same abstract postcondition.

---

## 66. Pop Proof Outline

### Array implementation

1. Verify that size is greater than zero.
2. Identify index `size - 1`.
3. Save the element at that index.
4. Decrease size.
5. The removed position leaves the active range.
6. The previous element becomes the top.
7. Return the saved value.

### Linked implementation

1. Verify that top is not null.
2. Save the element stored by top.
3. Advance top to `top.next`.
4. Decrease size.
5. The previous top is no longer reachable through the stack.
6. The next node becomes the top.
7. Return the saved value.

Both preserve LIFO behaviour.

---

## 67. Testing Strategy

Testing should verify both the abstract contract and representation-specific
behaviour.

A common contract-test suite may be executed against:

```text
ArrayStack
LinkedStack
```

This confirms that both implementations behave equivalently.

Representation-specific tests may then verify:

- capacity growth;
- capacity limits;
- reference cleanup;
- node transitions;
- resizing order.

---

## 68. Common Contract Tests

Every stack implementation should pass tests for:

### Initial state

- new stack is empty;
- size is zero;
- peek follows the empty-state contract;
- pop follows the empty-state contract.

### Push

- one pushed element becomes the top;
- size increases;
- multiple pushes preserve LIFO order.

### Pop

- top element is returned;
- exactly one element is removed;
- size decreases;
- repeated pop returns reverse insertion order.

### Peek

- top element is returned;
- size remains unchanged;
- repeated peek does not modify the stack.

### Empty transition

- removing the final element makes the stack empty;
- a new push works after the stack has been emptied.

### Generic behaviour

- integers;
- strings;
- domain objects;
- repeated equal values.

---

## 69. Fixed-Array Tests

Representative tests include:

- construction with valid capacity;
- rejection of invalid capacity;
- push until full;
- overflow according to contract;
- pop after reaching capacity;
- reuse of a released array position;
- correct top at every boundary.

Example sequence:

```text
capacity = 3

push(A)
push(B)
push(C)
pop()      → C
push(D)
pop()      → D
```

The implementation must reuse available capacity correctly.

---

## 70. Dynamic-Array Tests

Representative scenarios include:

- growth from initial capacity;
- multiple consecutive resizes;
- preservation of LIFO order after resizing;
- correct size after resizing;
- no element loss;
- no element duplication;
- optional shrinking;
- growth after shrink;
- minimum-capacity preservation.

Example:

```text
Initial capacity = 2

push(A)
push(B)
push(C)  → triggers growth
push(D)
push(E)  → may trigger another growth
```

Repeated pop should still return:

```text
E, D, C, B, A
```

---

## 71. Linked-Stack Tests

Representative tests include:

- first push sets top;
- additional pushes preserve reachability;
- pop advances top;
- removing the final node sets top to null;
- repeated empty-to-non-empty transitions;
- size equals reachable node count;
- no unintended cycles.

Most internal node details should remain private.

Structural correctness may be verified indirectly through observable operations
or through carefully designed package-private validation during development.

---

## 72. Property-Based Tests

Useful general properties include:

### Push-pop restoration

For any stack state `S` and permitted element `x`:

```text
push(x)
pop()
```

returns `x` and restores the previous logical content.

### LIFO property

For any sequence:

```text
x₁, x₂, ..., xₙ
```

after pushing in that order, repeated pop returns:

```text
xₙ, xₙ₋₁, ..., x₁
```

### Peek preservation

For a non-empty stack:

```text
peek before = peek after
```

when no modifying operation occurs.

### Size consistency

```text
push count - successful pop count = current size
```

provided the sequence begins with an empty stack and contains no invalid pop.

---

## 73. Benchmarking Perspective

After correctness is established, implementations may be compared using JMH.

Possible benchmark scenarios include:

- repeated push;
- repeated pop;
- alternating push and pop;
- large growth sequences;
- fixed-capacity operations;
- dynamic resizing;
- linked-node allocation;
- Java `ArrayDeque`.

Benchmarks should separate different costs.

For example:

```text
Measure normal push without resizing
```

separately from:

```text
Measure push that triggers resizing
```

Otherwise, results may be difficult to interpret.

---

## 74. Benchmarking Questions

Useful questions include:

- How does array locality affect throughput?
- How much does node allocation influence linked-stack performance?
- How expensive are resize events?
- How does initial capacity affect dynamic-array performance?
- Does clearing removed references produce measurable overhead?
- How does a custom implementation compare with `ArrayDeque`?
- How does performance change as stack size grows?

Empirical results complement theoretical analysis.

They do not replace proof of correctness or complexity reasoning.

---

## 75. Common Implementation Mistakes

### Mixing top conventions

Treating `top` both as the current element index and the next free position.

### Incrementing size too early

Changing logical state before capacity validation or successful storage.

### Forgetting to decrement size

The element may be removed while the recorded size remains incorrect.

### Failing to clear obsolete array references

Removed objects remain referenced unnecessarily.

### Growing by one position

Repeated copying produces poor cumulative performance.

### Losing linked nodes during push

Updating top before connecting the new node to the previous top.

### Failing to update top during pop

The same element remains accessible.

### Ignoring the single-node transition

After removing the only linked node, top must become null.

### Using the linked-list tail as top

Pop may become O(n) in a singly linked representation.

### Exposing internal storage

Client code can violate invariants.

---

## 76. Engineering Considerations

Selecting an implementation requires evaluating the actual system context.

Important questions include:

- Is the maximum size known?
- Must memory usage be predictable?
- Are frequent allocations acceptable?
- Is low per-element overhead important?
- Is cache locality important?
- Can occasional O(n) resizing be tolerated?
- Is a custom implementation necessary?
- Does the Java standard library already satisfy the requirement?
- Must the implementation be thread-safe?
- What failure semantics should be used?
- Are null values permitted?
- Is instrumentation or educational visibility required?

The most flexible implementation is not automatically the best one.

The correct implementation is the one whose characteristics match the
requirements.

---

## 77. Java Collections Perspective

For most general-purpose Java applications, stack behaviour can be provided by:

```text
Deque<E>
```

with:

```text
ArrayDeque<E>
```

Conceptually:

```text
Deque<String> stack = new ArrayDeque<>();
```

This standard implementation is typically preferable to writing a custom stack
for production use unless specialised requirements exist.

Manual implementations remain important in this repository because they expose:

- representation choices;
- invariant preservation;
- capacity management;
- reference manipulation;
- amortised analysis;
- testing strategy.

The Java Collections Framework is studied in detail in:

```text
06-java-deque.md
```

---

## 78. Relationship with Previous Documents

This document builds directly on:

```text
01-stack-fundamentals.md
```

The fundamentals document defines:

- LIFO behaviour;
- top semantics;
- stack operations;
- abstract invariants;
- expected complexity.

This document explains how those requirements are realised physically.

The progression is:

```text
Stack Contract
      ↓
Representation Selection
      ↓
Array Implementation
      ↓
Linked Implementation
      ↓
Correctness
      ↓
Complexity
      ↓
Testing
      ↓
Engineering Evaluation
```

---

## 79. Relationship with Following Documents

This document prepares the learner for:

```text
05-queue-implementations.md
```

which applies similar reasoning to FIFO structures.

It also supports:

```text
06-java-deque.md
```

which connects stacks and queues with Java collections.

Further application occurs in:

```text
07-common-algorithms.md
08-complexity-analysis.md
09-problem-solving-guide.md
10-common-mistakes.md
```

---

## 80. Study Questions

After studying this document, the learner should be able to answer:

1. Why can the same stack abstraction have multiple implementations?
2. How is the top represented in an array stack?
3. What is the difference between a top index and a next-free-position index?
4. Which invariants define a correct array stack?
5. Why should removed object references sometimes be cleared?
6. What causes overflow in a fixed-capacity stack?
7. How does a dynamically resized stack grow?
8. Why is growth by one position inefficient?
9. What does amortised O(1) push mean?
10. Why can one push still cost O(n)?
11. Why should growth and shrink thresholds differ?
12. Why is the linked-list head a suitable stack top?
13. Which reference updates are required during linked push?
14. Which reference updates are required during linked pop?
15. What invariants define a correct linked stack?
16. Why does a linked stack use more memory per element?
17. Why may an array stack offer better cache locality?
18. When is a fixed-capacity stack appropriate?
19. How can a shared contract-test suite validate multiple implementations?
20. Why is standard `ArrayDeque` generally preferable in production Java?

---

## 81. Completion Criteria

This document may be considered complete when the learner can:

- distinguish stack behaviour from stack representation;
- design a fixed-capacity array stack;
- define the meaning of the top variable;
- implement push, pop, and peek conceptually;
- explain array-stack invariants;
- explain dynamic capacity growth;
- derive amortised push complexity;
- explain safe shrinking strategies;
- model a linked stack using nodes;
- preserve node reachability;
- prove push and pop correctness informally;
- compare time and space costs;
- identify memory-allocation trade-offs;
- define suitable exception semantics;
- design contract and implementation-specific tests;
- justify the selection of an implementation.

---

## 82. Key Takeaways

After completing this document, the learner should understand that:

- a stack's abstract LIFO behaviour is independent of its representation;
- arrays and linked nodes can both implement the same stack contract;
- fixed-capacity arrays provide predictable storage but may overflow;
- dynamically resized arrays provide flexible growth with occasional O(n)
  copies;
- geometric growth supports amortised O(1) push;
- linked stacks provide O(1) push and pop without array resizing;
- linked nodes introduce allocation and reference overhead;
- array implementations usually provide better memory locality;
- the linked-list head should normally represent the top;
- representation invariants are essential for correctness;
- size and top must remain consistent after every operation;
- removed object references may need to be cleared in Java;
- asymptotic complexity must be considered together with memory and runtime
  behaviour;
- automated tests should validate both the common stack contract and
  representation-specific properties;
- production Java code should generally prefer standard-library abstractions
  unless specialised requirements justify a custom implementation.

Understanding these implementation strategies prepares the learner to analyse
queue representations, work effectively with Java's `Deque` interface, and make
informed data-structure decisions in real software systems.