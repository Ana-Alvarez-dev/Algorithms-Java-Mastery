# Stacks and Queues: Java Contracts and Proof Map

Reviewed on **2026-10-28**, against main
`6db7b24a9660ac9fd44ce42f941f8307bc2c2d22` (PR #104 merge).
This map connects the current Java source to its invariants, costs and tests.
The [review report](documentation-review.md) records changes and fresh execution;
the [implementation guide](implementation-and-benchmark-guide.md) retains the
original pseudocode, traces and historical evidence.

## API boundary and failures

The three educational interfaces are not java.util collections and do not
extend Iterable. Containers accept duplicate, non-null references, cache size
and provide no concurrency or blocking guarantee. Representation nodes/arrays
are private. No indexed container access or container iterator is provided.

| Interface | Insertion | Empty removal | Empty inspection |
|---|---|---|---|
| Stack | push | pop throws NoSuchElementException; poll returns null | element throws NoSuchElementException; peek returns null |
| FifoQueue | enqueue at rear | dequeue throws NoSuchElementException; poll returns null | element throws NoSuchElementException; peek returns null |
| DoubleEndedQueue | offerFirst/Last returns false when full; addFirst/Last throws IllegalStateException | removeFirst/Last throws NoSuchElementException; pollFirst/Last returns null | getFirst/Last throws NoSuchElementException; peekFirst/Last returns null |

Null insertion throws NullPointerException **before** growth, a full-capacity
test or mutation. Thus even a full bounded deque rejects null by exception,
not false. Ordinary invalid-capacity/null/empty/full rejections preserve logical
contents. This is not a general transaction guarantee on allocation failure;
TwoStackQueue transfer pops incoming before pushing outgoing.

toList returns a new mutable, shallow List in **removal order**: top-first for
stacks and front-first for queues/deques. Editing the snapshot's membership
does not change the container; both still reference the same element objects.

| Current types | Source | Direct Java test evidence |
|---|---|---|
| Stack, ArrayStack, LinkedStack | [Stack](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/Stack.java), [array](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/ArrayStack.java), [linked](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/LinkedStack.java) | StackContractTest inherited by both; ArrayStackTest growth/null/capacity cases |
| FifoQueue, CircularArrayQueue, LinkedQueue, TwoStackQueue | [FIFO](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/FifoQueue.java), [ring](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/CircularArrayQueue.java), [linked](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/LinkedQueue.java), [two stacks](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/TwoStackQueue.java) | QueueContractTest inherited by all three; wrapped growth and delayed-transfer tests |
| DoubleEndedQueue, BoundedArrayDeque | [interface](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/DoubleEndedQueue.java), [bounded ring](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/BoundedArrayDeque.java) | BoundedArrayDequeTest: all slots, capacity one, null even when full, both-end wrap |
| ArrayCapacity | [capacity helper](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/ArrayCapacity.java) | ArrayStackTest validates arithmetic at the implementation maximum without allocating a huge array |

All test sources are in the [module test directory](../../src/test/java/org/anaalvarezdev/algorithms/stacksandqueues).
Fixed-array stacks, growing deques and a stack built from queues remain design
alternatives. Discussion of them does not imply a corresponding Java class.

## Representations and preservation arguments

Let n be live size and c the positive array capacity. The cost model counts
reference/index/pointer work and array-slot initialisation, with fixed-width
Java arithmetic. JVM allocation/GC latency and element-object heap layout are
separate engineering questions.

**ArrayStack.** Slots [0,n) are non-null live references in bottom-to-top order;
[n,c) are null. Push writes the next free slot, growing first if needed. Pop
reads n-1, decreases size and nulls that vacated slot. Snapshot iteration reverses
the active prefix. These steps preserve top-first removal and the unused-null
invariant. clear nulls the live prefix and retains the array.

**LinkedStack.** Exactly n private non-null nodes are reachable from head, ending
in null; head is null iff n=0. Prepending a fresh node makes its value the next
removed; removing head exposes its successor. clear drops this owner's head
and resets size. It does not synchronously reclaim all nodes or shared values.

**CircularArrayQueue and BoundedArrayDeque.** Logical offset j maps to
(head+j) mod c. The Java helper uses `untilWrap = c-head`, then either head+j
when j<untilWrap or j-untilWrap otherwise. For 0<=j<c the selected branch
stays within [0,c), without overflowing an intermediate head+j. Calls during
insertion use that domain because full storage is grown or rejected first.

Live logical offsets [0,n) are non-null; all other slots are null. Empty means
n=0 and canonical head=0. Full means n=c: no slot is reserved. Capacity one
therefore supports one value. Dequeue/poll clears its slot, advances head and
repairs the empty state. Deque rear removal clears offset n-1; front insertion
decrements/wraps head before writing. Explicit size distinguishes full from
empty even when head equals the next insertion position.

For queue growth, after copying i elements, destination slots [0,i) equal the
first i values of the **old logical order**. Advancing i preserves that prefix;
at i=n every value is copied once. Publishing the new array and setting head=0
preserve FIFO order and size. Copying old physical indices in their natural
order would fail for a wrapped queue. BoundedArrayDeque never grows.

**LinkedQueue.** Exactly n nodes form the head-to-tail null-terminated chain.
Empty has both endpoints null; otherwise tail is the last reachable node and
tail.next is null. Enqueue attaches a fresh node at tail (also head if empty).
Dequeue removes head and resets tail when the last node leaves. Cached size
and tail avoid traversal. clear drops both endpoints in constant owner work.

**TwoStackQueue.** In top-first stack notation, its logical queue is
`outgoing ++ reverse(incoming)`. New values go on incoming; outgoing holds
the older prefix. Transfer reverses incoming **only when outgoing is empty**,
making its oldest value the new top. Transferring earlier would put newer
values ahead of older ones. Example: enqueue 1,2,3; peek transfers and observes 1;
dequeue removes 1; enqueue 4; outgoing still supplies 2,3 before incoming's 4.
peek preserves logical contents but can mutate the physical representation.
toList concatenates those two logical components without transferring.

## Individual and amortised work

| Structure / operation | Individual bound | Sequence/storage qualification |
|---|---|---|
| ArrayStack push; CircularArrayQueue enqueue | Theta(1) without growth; Theta(n) when full and growing | Amortised O(1) from an empty constructed state; retain capacity |
| ArrayStack pop/peek; circular dequeue/peek | Theta(1) | No shrinking |
| Linked endpoints | Theta(1) pointer work | Each insertion has a node allocation site |
| Bounded deque endpoints | Theta(1) | Full rejection; fixed c slots |
| TwoStackQueue enqueue | O(n+1) worst case from incoming growth | Amortised O(1) |
| TwoStackQueue dequeue/peek | O(n+1), including a possible transfer | Amortised O(1); repeated peek after transfer does not transfer again |
| Cached size/isEmpty and bounded capacity/isFull | Theta(1) | No scan |
| Array-backed clear, including two stacks | Theta(n+1) | Clear live references, retain backing capacity |
| Linked clear | Theta(1) | Owner work; GC excluded |
| Snapshot | Theta(n+1) time and extra references | Shallow; independent list storage |

ArrayCapacity requires 1<=c<=Integer.MAX_VALUE-8, doubles using long arithmetic
and caps at that maximum. Asking to grow at the maximum throws OutOfMemoryError;
validating a capacity does not guarantee that the JVM can allocate it.

For m successful pushes/enqueues starting empty, doubled old capacities form a
geometric sum O(m). Initialising the new arrays has the same bound. Including
arbitrary initial capacity c0 gives O(c0+m) total work. Pops/clear do not shrink,
so this is a sequence argument, not an average over random inputs or a bound
on a single operation immediately following a preloaded state.

For a two-stack queue, each enqueued item is pushed incoming once, moved to
outgoing at most once and removed from outgoing at most once. Thus a sequence
of m queue endpoint calls starting empty performs O(m) abstract stack calls,
including transfers caused by peek. Abstract unit-cost stacks also admit the
potential `Phi=2*incoming.size`: enqueue adds two credits; moving r items uses
2r push/pop steps and releases 2r credits. **This potential alone does not pay
for backing-array resizing.** Apply the geometric aggregate bound separately
to each real ArrayStack's push sequence. With initial capacities included,
total reference work is O(c_in0+c_out0+m). A preloaded state needs initial
credit/work; a suffix beginning with a large transfer has no standalone O(m)
bound that ignores those elements.

Retained storage is Theta(c) for one array, Theta(c_in+c_out) for TwoStackQueue
and Theta(n+1) for linked structures. Construction of each reference array costs
Theta(c). After fill/drain or clear, arrays can remain large when current n=0.
hasValidStructure diagnostics scan nodes/capacity and are not constant-time
production endpoints or calls inside the timed JMH methods.

## Applied algorithms: contracts, invariants and costs

All five implementations are iterative and reject null input. The source/test
links below refer to actual Java implementations, not translated models.

| Algorithm | Observable result and boundary | Tests |
|---|---|---|
| [DelimiterValidator](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/DelimiterValidator.java) | Match (), [] and {}; ignore other characters; empty true. Quotes/comments do not shield delimiters: this is not a source-language parser. | [DelimiterValidatorTest](../../src/test/java/org/anaalvarezdev/algorithms/stacksandqueues/DelimiterValidatorTest.java) |
| [PostfixEvaluator](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/PostfixEvaluator.java) | Java-whitespace-separated Integer.parseInt operands and binary +,-,*,/. One final int; bad token/arity/final size throws IllegalArgumentException (including NumberFormatException); arithmetic overflow/division by zero throws ArithmeticException. | [PostfixEvaluatorTest](../../src/test/java/org/anaalvarezdev/algorithms/stacksandqueues/PostfixEvaluatorTest.java) |
| [NextGreaterElement.indices](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/NextGreaterElement.java) | New nearest strictly greater right-hand **index** array, -1 if absent; empty valid, input preserved. [2,2,3,1] yields [2,2,-1,-1]. | [NextGreaterElementTest](../../src/test/java/org/anaalvarezdev/algorithms/stacksandqueues/NextGreaterElementTest.java) |
| [SlidingWindowMaximum.maxima](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/SlidingWindowMaximum.java) | New n-k+1 maxima, input preserved. Requires 1<=k<=n; empty has no valid window. [1,3,3,-1,5], k=3 yields [3,3,5]. | [SlidingWindowMaximumTest](../../src/test/java/org/anaalvarezdev/algorithms/stacksandqueues/SlidingWindowMaximumTest.java) |
| [QueueReverser.reverse](../../src/main/java/org/anaalvarezdev/algorithms/stacksandqueues/QueueReverser.java) | Destructively reverse the supplied mutable FIFO, retaining element identities; empty valid. | [QueueReverserTest](../../src/test/java/org/anaalvarezdev/algorithms/stacksandqueues/QueueReverserTest.java) |

**Delimiter scanning:** before index i, the stack holds exactly the unmatched
openers in the processed prefix, latest first. A closing delimiter must match
the top; missing/mismatched top rejects immediately. At exhaustion, an empty
stack is equivalent to all delimiters being matched. For L UTF-16 code units,
worst-case time is Theta(L+1); early rejection can be constant. Auxiliary storage
is O(d+1) for maximum pending nesting depth d, at worst O(L+1).

**Postfix:** after each valid token, the stack contains the completed, unused
subexpression results. An operator needs two; popping right before left preserves
subtraction/division order. Checked add/subtract/multiply and explicit MIN_VALUE/-1
division rejection avoid silent overflow. Division truncates toward zero. Exactly
one final value establishes a complete expression. Tokenisation uses strip and
`\p{javaWhitespace}+`, not every possible Unicode separator; parsing delegates
to Integer.parseInt. O(L+1) time/storage includes input tokenisation, rather
than describing only the arithmetic stack. Invalid inputs may stop early.

**Next greater:** pending indices increase bottom-to-top and their values are
non-increasing. When current value exceeds the top, current index is the first
greater value encountered to its right; set that result and pop. Equal values
stay pending because the relation is strict. Every index is pushed once and
popped at most once, so nested loops take Theta(n+1) total work, not quadratic.
The pending stack uses O(n+1) auxiliary storage plus the n-index result.

**Sliding maximum:** indices increase front-to-rear; candidate values strictly
decrease after removing rear values <= the new value. A newer equal value is
as useful and expires later. Expire the old front before inserting, keeping
at most k indices in the bounded deque; advancing one input index can expire
at most one previous candidate. Removing dominated rear entries preserves the
window maximum. The front therefore supplies each complete window's maximum.
Each index enters/leaves at most once: Theta(n) total time, O(k) auxiliary
storage and Theta(n-k+1) output storage. One step can remove Theta(k) candidates;
the total bound does not make every input step worst-case constant.

**Reversal:** after draining t values, the auxiliary stack is the reverse of
that removed prefix and the queue holds the unremoved suffix. Once drained,
popping and enqueuing all values establishes the reverse order. There are
Theta(n+1) endpoint/empty checks and O(n+1) auxiliary references. Time is
Theta(n+1) for this module's amortised-constant endpoint implementations;
the FifoQueue interface alone imposes no such complexity on arbitrary clients.

## Benchmark evidence boundary

[StackOperationsBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/stacksandqueues/StackOperationsBenchmark.java)
has four push/pop pairs that restore contents. [QueueOperationsBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/stacksandqueues/QueueOperationsBenchmark.java)
has six remove/append pairs: size and identities/multiset are retained, but
logical order **rotates**. Scores are per pair. Setup constructs/prepopulates
structures; reserved array capacity excludes growth. Linked-node allocation
sites and necessary two-stack transfers are inside timing, subject to JIT.
The full bounded deque removes before appending, so rejection is not measured.

Four default sizes produce 40 configurations; the author's
[historical subset](../17-benchmarking/results/stacks-and-queues/README.md)
contains 20, at sizes 100/1000 on Oracle JDK 21.0.9. Two sizes and average-time
pairs do not prove amortised bounds or isolate transfer latency. No profiler,
allocation-rate or heap-footprint conclusion is supplied. Fresh Java 21 build
and discovery in the review report preserve that experiment's provenance.
