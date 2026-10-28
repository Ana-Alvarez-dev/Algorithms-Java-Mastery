# Stacks and Queues: Implementation and Benchmark Guide

> **2026-10-28 documentation follow-up:** see the
> [review report](documentation-review.md) and [contract/proof map](implementation-contracts.md).
> Fresh Java 21 verification is separate from the 2026-10-19 build and author's
> timing subset below. Current benchmark discovery checks registration and
> parameters; it supplies no new performance score or coverage percentage.

## Scope and Traceability

The package is `org.anaalvarezdev.algorithms.stacksandqueues` in the main,
test and JMH source roots. The small educational contracts do not implement
the full Java Collections Framework. The following scope is selected before
implementation; conceptual topics outside it remain extensions.

| Conceptual document | Executable responsibility | Verification |
|---|---|---|
| [Stack fundamentals](01-stack-fundamentals.md), [representations](04-stack-implementations.md) | `Stack`, `ArrayStack`, `LinkedStack` | Shared LIFO contract; growth and node invariants |
| [Queue fundamentals](02-queue-fundamentals.md), [representations](05-queue-implementations.md) | `FifoQueue`, `CircularArrayQueue`, `LinkedQueue`, `TwoStackQueue` | Shared FIFO contract; wraparound, wrapped growth and transfers |
| [Deque fundamentals](03-deque-fundamentals.md) | `DoubleEndedQueue`, `BoundedArrayDeque` | Both ends, capacity one, full/empty distinction and atomic rejection |
| [Java Deque](06-java-deque.md) | Explicit null/exception/sentinel policies; JDK references in JMH | Comparison with `ArrayDeque` under the same non-null workloads |
| [Common algorithms](07-common-algorithms.md) | `DelimiterValidator`, `PostfixEvaluator`, `NextGreaterElement`, `SlidingWindowMaximum`, `QueueReverser` | Malformed inputs, numeric boundaries, independent reference models |
| [Complexity](08-complexity-analysis.md) | Amortised growth, transfer accounting and monotonic structures | Reasoning below; tests are separate evidence |
| [Problem solving](09-problem-solving-guide.md), [mistakes](10-common-mistakes.md) | Contracts, pseudocode, traces and representation invariants | Seeded operation sequences and structural diagnostics |

Fixed-array stacks, dynamically growing deques, infix conversion, stacks built
from queues, and tree/graph traversal are future extensions. A bounded deque
provides the selected explicit overflow exercise. They are not claimed as
implemented merely because the conceptual documents discuss them.

## Observable Contracts

All containers reject null insertions with `NullPointerException` before
mutation, permit duplicates and generic application objects, cache size, and
are not thread-safe. This differs deliberately from the previous Linked Lists
module, which accepts null values. Null here unambiguously represents absence.

| API | Mutation | Empty behaviour |
|---|---|---|
| `Stack.push`, `pop` | Insert/remove at top; LIFO | `pop` throws `NoSuchElementException` |
| `FifoQueue.enqueue`, `dequeue` | Append at rear/remove at front; FIFO | `dequeue` throws `NoSuchElementException` |
| `peek`, `poll` on either contract | Inspect/remove next value | Return null when empty |
| `element` on either contract | Inspect next value | Throws `NoSuchElementException` |
| Deque `offerFirst/Last`, `addFirst/Last` | Insert at selected end | Full: offer returns false; add throws `IllegalStateException` |
| Deque `pollFirst/Last`, `removeFirst/Last` | Remove at selected end | Empty: poll returns null; remove throws `NoSuchElementException` |
| Deque `peekFirst/Last`, `getFirst/Last` | Inspect selected end | Empty: peek returns null; get throws `NoSuchElementException` |

Initial array capacities must be positive and at most `Integer.MAX_VALUE - 8`.
The bounded deque can use every allocated slot; a cached size distinguishes
full and empty. Null insertion is rejected even when it is already full.
Allocation failure remains possible. Growth uses a long calculation, caps at
the supported maximum and fails before integer overflow.

`toList` is a shallow, independent, mutable snapshot in **removal order**:
top-to-bottom for stacks and front-to-rear for queues/deques. It exposes values,
not backing arrays or nodes. `clear` empties the owner and permits reuse.
Arrays retain capacity, clear occupied references and never shrink automatically.
Linked containers drop their private endpoint references. No iterator API or
fail-fast policy is claimed for these selected contracts.

## Strategies, Pseudocode and Traces

### Array stack

Maintain live non-null values in `elements[0..size)`, with the top at `size-1`.

```text
PUSH(value): reject null; grow if full; elements[size] = value; size++
POP(): reject empty; size--; save elements[size]; clear slot; return saved
```

Starting at capacity 2: push A, push B gives top-first `[B,A]`; push C grows
capacity to 4 and gives `[C,B,A]`; pop returns C and restores `[B,A]`.
Resizing copies the same prefix into a separate array before publishing it.

### Circular queue and bounded deque

Logical item i occupies `(head+i) mod capacity`. The Java index calculation
uses subtraction at the wrap boundary to avoid overflowing `head+i`.

```text
ENQUEUE(value): reject null; grow if full; write logical[size]; size++
DEQUEUE(): reject empty; save head; clear head; advance head; size--; return saved
GROW(): copy logical items 0..size-1 to new array 0..size-1; set head = 0
```

Capacity 3 trace: enqueue A/B/C, dequeue A/B, enqueue D/E gives logical
`[C,D,E]` across the physical boundary. Enqueue F grows the dynamic queue and
preserves `[C,D,E,F]`. The bounded deque instead rejects a full insertion without
changing contents. Its front insertion decrements head modulo capacity; its
rear removal clears logical index `size-1`. Empty removal canonicalises head
to zero. Capacity one must support the same transitions.

### Linked stack and queue

The stack changes its head only. A queue keeps both head and tail: append links
the old tail to the new node; removing the final head also clears tail. Every
operation preserves a finite chain of exactly size nodes, with tail.next null
for the queue. These implementations own private nodes independently of the
previous module's generic lists, exposing the ADT's restricted operations.

### Queue from two stacks

```text
ENQUEUE(value): incoming.push(value)
DEQUEUE/PEEK(): if outgoing empty, transfer all incoming items to outgoing
               then remove/inspect outgoing's top
```

Enqueue A/B gives incoming top-first `[B,A]`. A dequeue transfers to outgoing
`[A,B]` and returns A. Enqueue C does not disturb outgoing `[B]`. Subsequent
dequeues return B and then C. Transferring only when outgoing is empty preserves
the older prefix before the newer incoming suffix.

## Correctness and Complexity

Array invariants require non-null live slots and null unused slots. Ring
invariants require `0 <= size <= capacity`, a valid head, logical FIFO order,
and zero head when empty. Node diagnostics check bounded reachability and
endpoint consistency. `hasValidStructure` is package-private test support;
it is not called on timed operations, where scanning would change their cost.

Inserting extends the appropriate logical sequence by one value. Removing
returns its required boundary, removes exactly that slot/node, and decreases
size once. Growth copies each logical position to the same logical position.
The copy-loop invariant is that the first i new positions equal the first i
old logical positions; increasing i terminates at size. These facts establish
initialisation, preservation and termination rather than relying on timing.

Let n be current size and c retained capacity; value/reference operations are
constant-time and allocation costs are accounted for by representation.

| Operation | Worst case | Amortised / storage |
|---|---|---|
| Array stack push | Θ(n) when growing | O(1); Θ(c) retained storage |
| Array stack pop/peek | Θ(1) | No shrink |
| Circular queue enqueue | Θ(n) when growing | O(1); Θ(c) storage |
| Circular queue dequeue/peek | Θ(1) | No shifts |
| Linked stack/queue endpoints | Θ(1) in the pointer-operation model | Θ(n) node storage; allocation/GC latency not bounded |
| Bounded deque endpoints | Θ(1) | Θ(c) storage, no growth |
| Two-stack queue dequeue/peek | Θ(n) when transferring | O(1) over a sequence; backing stacks also grow geometrically |
| Two-stack queue enqueue | Θ(n) if incoming grows | O(1) |
| Snapshot | Θ(n) | Θ(n) temporary references |
| Array clear | Θ(n) | Clears live slots, retains capacity |
| Linked clear | Θ(1) ownership reset | Reclamation is determined by GC |
| Two-stack clear | Θ(n) | Clears both array stacks |

Doubling copies a geometric series of prior capacities, bounded by a constant
multiple of inserted elements. Each queued item is pushed to incoming, moved
at most once to outgoing, and removed at most once. These aggregate arguments
explain amortised bounds; they do not promise constant latency for every call.
The two-stack queue retains both backing capacities even when empty, so its
array storage is proportional to their sum, not necessarily the current size.
Its peek may transfer values between arrays while preserving logical order.

## Selected Algorithms

| Algorithm | Contract and strategy | Time / auxiliary space |
|---|---|---|
| Delimiters | Match `()[]{}` with a stack; ignore other characters; not a parser for quoted strings/comments; empty is balanced; null rejected | O(L) / O(L) |
| Postfix | Whitespace-separated signed decimal int operands and binary `+ - * /`; preserve left/right order; malformed/empty expressions rejected; arithmetic overflow and division by zero throw `ArithmeticException`; null rejected | O(L) / O(L), including tokenisation |
| Next greater | Return the nearest strictly greater right-hand **index**, or -1; equal values are not greater; preserve input | Θ(n) / O(n), plus output |
| Sliding maximum | One maximum per complete window; require non-null input and `1 <= k <= n`; empty array therefore has no valid k; preserve input | Θ(n) / O(k), plus output |
| Queue reversal | Drain a non-null FIFO into a stack, then re-enqueue popped values; destructive; empty is valid | Θ(n) / Θ(n) |

Delimiter invariant: after each processed character, the stack contains exactly
the unmatched opening delimiters, with the most recent on top. It starts empty;
an opening extends it, a matching closing removes its top, and a mismatched or
unexpected closing proves invalidity. At the end, an empty stack is equivalent
to complete matching. The character index increases to the input length. On
`([x])`, push `(`, push `[`, ignore x, pop `[`, pop `(`, and finish empty.

Postfix invariant: the stack holds values of completed subexpressions after
each token. An operator consumes its right operand first, then its left, and
pushes the checked result. Example `3 4 + 2 *` produces 14. Exactly one value
must remain. `Integer.MIN_VALUE / -1` is explicitly treated as overflow.

For next-greater indices, pending indices increase while their values are
non-increasing. A newly greater item resolves exactly the pending suffix for
which it is the first greater right value. For `[2,2,3,1]`, return `[2,2,-1,-1]`.
Each index is pushed and popped at most once.

For sliding maxima, expire out-of-window indices, remove dominated rear
candidates, append the new index and read the head maximum. The retained indices
increase and their values strictly decrease; the head is the maximum candidate
in the current window. Equal older values may be discarded because the newer
one expires later. Each index enters and leaves once. On `[1,3,3,-1,5]`, k=3,
return `[3,3,5]`. Queue reversal's processed prefix is stored in reverse order;
draining and refilling both terminate as their source size decreases.

## Automated Verification

On **2026-10-19**, OpenJDK **21+35-2513** verification passed **418 repository
tests**, including the following **80** module tests:

| Concrete test class | Executed tests |
|---|---:|
| ArrayStackTest / LinkedStackTest | 11 / 8 |
| CircularArrayQueueTest / LinkedQueueTest / TwoStackQueueTest | 11 / 9 / 11 |
| BoundedArrayDequeTest | 8 |
| DelimiterValidatorTest / PostfixEvaluatorTest | 5 / 6 |
| NextGreaterElementTest / SlidingWindowMaximumTest / QueueReverserTest | 4 / 4 / 3 |

Shared contracts include 3,000 seeded model operations per implementation.
Bounded-deque tests use capacities 1, 2, 3 and 8 with 3,000 steps each. Tests
inspect invariants before snapshots, including unused-reference clearance.
The next-greater oracle checks all 1,093 arrays of length 0–6 over `{-1,0,1}`;
the sliding-window oracle checks all 6,015 nonempty array/window combinations.
Arithmetic tests cover overflow and operand order; deep delimiter input checks
iterative processing without asserting a timing threshold.

Normal Maven tests and the benchmark-profile clean package both passed. JMH
listed all ten methods and completed a short forked smoke on size 100. A
separate probe checked all 40 method/size combinations, with 2n+3 rotations per
queue case to cross transfer boundaries, verifying reference identity, order,
size and invariants. JavaDoc passed doclint with missing-tag checks excluded.
These checks are verification evidence, not research timing results.

## JMH Protocol

`StackOperationsBenchmark` compares four prepopulated structures: manual array
and linked stacks, JDK ArrayDeque and JDK LinkedList. One invocation pushes -1
then pops it, returning -1 and restoring the original contents. Arrays are
pre-sized for the extra value; this workload excludes resizing.

`QueueOperationsBenchmark` compares six representations: circular array, linked,
two-stack queue, bounded deque, JDK ArrayDeque and JDK LinkedList. One invocation
removes the head, appends that same boxed value and returns it. Size and the
value multiset are preserved, while logical order rotates. Two-stack transfers
are included when required; both internal stacks are pre-sized. Construction
and initial boxing of `0..n-1` are excluded; linked-node allocation sites remain
timed subject to JIT optimisation. The bounded deque starts full and removes
before appending, so the pair does not test overflow failure.

Defaults: sizes 100/1,000/10,000/100,000, one thread, JMH average time in ns/op,
three 500 ms warmups, five 500 ms measurements and two forks. Ten methods give
**40 default configurations**; the initial two-size subset has **20**. Every
score measures a pair, not one isolated insertion or removal. No profiler,
memory-footprint, latency-percentile or worst-case transfer result is inferred.

The fixed data, repeated warm state, allocator, GC, JIT and physical memory
layout constrain generalisation. Growth and isolated transfer latency require
separate justified experiments. The
[first reported measurement subset](../17-benchmarking/results/stacks-and-queues/README.md)
now completes both templates' Result and Interpretation sections. Supplied on
2026-10-19, it records 20 configurations, 200 measurement and 120 warmup iteration
averages on JDK 21.0.9/JMH 1.37, with author-reported source
`804f69f32b0da715ed41f390034523890c24bca5`. Eleven errors reach at least 25% of
their means. Interpretation remains preliminary; smoke checks remain separate.

## Windows Execution and Evidence

From the repository root after merging the PR:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd -Pbenchmark clean package
& "C:\Program Files\Java\jdk-21\bin\java.exe" -version
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar -l ".*stacksandqueues.*"
```

The path is the author's verified JDK 21 installation; adapt it for another
machine. `release=21` does not select the executable runtime. First smoke-check:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*stacksandqueues.*" -p size=100 -wi 1 -w 50ms -i 1 -r 50ms -f 1 -t 1 -foe true
```

Then measure the first subset:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*stacksandqueues.*" -p size=100,1000 -foe true
```

Preserve the header, final table, completion marker and source SHA as pasted
text or legible captures. Record actual Java/JMH versions, settings, command,
CPU/OS and all means/errors in Markdown. No JSON/log artifacts are added to the
repository. Missing fields and unavailable iteration values stay explicit.
On Linux use `bash mvnw` and `target/benchmarks.jar` with the selected JDK 21.

## Sources

The supplied CLRS **Third Edition (2009)**, Section 10.1, printed pp. 232–236,
was consulted for array stacks, circular queues, underflow/overflow and exercises
10.1-5 (deque) and 10.1-6 (queue from stacks). CLRS uses a spare ring slot;
this implementation uses cached size and every slot. These page numbers do not
refer to the fourth edition named in the project bibliography. Checked arithmetic,
null policies and selected application algorithms are independent repository
choices, not claims that each appears in those pages.

- [Java 21 Deque](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Deque.html): endpoint method pairs and LIFO/FIFO mapping.
- [Java 21 ArrayDeque](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayDeque.html): null prohibition and amortised operation model.
- [Java 21 Queue](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Queue.html): exception and special-value operations; the interface itself does not require FIFO for every implementation.
