# Linked Lists: Current Contracts and Proofs

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`20e7f2823fc0e9e620029177fceb111961e80811`. The
[review report](documentation-review.md) identifies sources, corrections and
actual Java 21 verification. This map describes the existing implementations,
not a new List/Deque API or a separate mathematical implementation.

## API and representation boundary

[LinkedSequence](../../src/main/java/org/anaalvarezdev/algorithms/linkedlists/LinkedSequence.java)
extends Iterable, not java.util.List. Its three implementations are
[SinglyLinkedList](../../src/main/java/org/anaalvarezdev/algorithms/linkedlists/SinglyLinkedList.java),
[DoublyLinkedList](../../src/main/java/org/anaalvarezdev/algorithms/linkedlists/DoublyLinkedList.java)
and [CircularLinkedList](../../src/main/java/org/anaalvarezdev/algorithms/linkedlists/CircularLinkedList.java).
They have private mutable nodes, cached head/tail/size and structural counters.
Public callers pass values or indices, not internal node handles. The circular
implementation is a singly linked ring without a sentinel node.

[LinkedNode](../../src/main/java/org/anaalvarezdev/algorithms/linkedlists/LinkedNode.java)
is a separate public chain node for
[LinkedListAlgorithms](../../src/main/java/org/anaalvarezdev/algorithms/linkedlists/LinkedListAlgorithms.java).
Its value reference is final, not necessarily its referenced object's state;
its next link is mutable and can deliberately form a cycle. These nodes cannot
be passed into a private container or change that container's cached metadata.

Assume valid representation, no concurrent/reentrant link changes, sufficient
resources and representable resulting sizes. Snapshots A0 below denote entry
values mathematically, not hidden copies allocated by every method. Cost bounds
use fixed-word reference/index work. Add actual Objects.equals/comparator work
when application comparisons are nonconstant. Costs refer to the provided
implementations, not an arbitrary third-party LinkedSequence override.

| Operation family | Current result / mutation | Domain and failure |
|---|---|---|
| Construction, size, isEmpty | New empty container; cached size read | No traversal to compute size |
| getFirst/getLast | Return endpoint value without removing it | Empty throws NoSuchElementException; null may be a stored value |
| get/set/remove(i) | Read, replace/return old value, or remove/return selected value | 0<=i<n; invalid index throws IndexOutOfBoundsException before mutation |
| addFirst/addLast/add(i,value) | Insert one new owned node; preserve existing relative order | Null/duplicate values allowed; indexed insertion allows 0<=i<=n |
| removeFirst/removeLast | Return removed value and repair endpoints/size | Empty throws NoSuchElementException |
| indexOf/contains | First Objects.equals(current,value) match or -1 / boolean | Null is a valid search value; equality is not node identity |
| reverse | Same nodes in opposite logical order | n<2 is a structural no-op; n>=2 invalidates existing iterators |
| clear | Reset this owner's endpoints and size | Empty clear does not increment the structural counter; no immediate-GC guarantee |
| toList | Independent mutable list of value references, in entry order | Shallow snapshot: changing its slots is independent; mutable values are shared |
| appendAll(other) | Append the captured source values into fresh destination nodes | Null source throws NPE before append. A distinct source is unchanged; self-append doubles the original contents once |
| splitOff(i) | Receiver retains A0[0:i); new same-topology container owns original nodes for A0[i:n) | 0<=i<=n. Invalid index rejected before mutation; i=n returns a new empty result without changing the receiver |
| iterator / descendingIterator | Finite forward pass; doubly also supports backward pass | Stable exhausted next throws NoSuchElementException; remove is unsupported; structural invalidation is checked by hasNext/next |

The [shared contract tests](../../src/test/java/org/anaalvarezdev/algorithms/linkedlists/LinkedSequenceContractTest.java)
exercise nulls, duplicates, bounds, endpoints, shallow snapshots, self/cross-type
append, split ownership, reversal, iteration and seeded operation sequences.
[Doubly tests](../../src/test/java/org/anaalvarezdev/algorithms/linkedlists/DoublyLinkedListTest.java)
add backward iteration; [circular tests](../../src/test/java/org/anaalvarezdev/algorithms/linkedlists/CircularLinkedListTest.java)
add small-ring endpoint transitions. The dated report records their execution,
not merely their existence.

## Container invariants and boundary repairs

For n=0, every container has head=tail=null. For n>0:

- Singly: exactly n distinct nodes lead from head to tail, then null.
- Doubly: that forward chain also has reciprocal previous/next links,
  head.previous=null and tail.next=null.
- Circular: exactly n distinct nodes form one ring, whose last logical node
  is tail and whose tail.next=head. For n=1, head=tail and head.next=head.

These statements concern owned nodes, not every allocated node in the JVM.
Private topology prevents clients from attaching unrelated chains. Diagnostics
hasValidStructure traverse with explicit bounds and inspect counts/endpoints;
the JUnit contract checks call them after state changes. Public endpoint calls
do not perform that diagnostic scan automatically.

Insertion saves the original successor, connects one fresh node, then updates
head/tail/size. Doubly linkBefore also repairs both neighboring links. Removal
reconnects the remaining neighbors before detaching the removed node; removing
the only element resets both endpoints. Circular head/tail changes additionally
preserve tail.next=head, except when the final element is removed and the owner
becomes empty. These local cases preserve node count, order and reachability.

Singly reversal maintains an already reversed prefix plus the still accessible
unprocessed suffix; saving next before redirecting current.next is essential.
Doubly reversal swaps each node's next/previous while advancing by the saved
old next. Circular reversal redirects exactly n links with previous initially
tail, then swaps endpoints. Its remaining-node count decreases to zero rather
than waiting for null. Empty/singleton methods return without structural change.

An interior split severs the prefix/suffix boundary. Singly sets the prefix
tail.next to null; doubly additionally clears the suffix head's previous link.
Circular repairs both tails to close two independent rings. At i=0, ownership
of the whole chain/ring transfers; at i=n no receiver topology changes. The
result and receiver have disjoint nodes, but stored object references can still
alias. Reusing nodes does not mean cloning application objects.

## Position and ownership costs

Let n be receiver size, m source size and i a valid position. Define
d(i)=min(i,n-1-i) for element indices in a doubly linked container.

| Operation | Singly / circular singly | Doubly |
|---|---|---|
| size/isEmpty, endpoint reads | Theta(1) | Theta(1) |
| addFirst/addLast/removeFirst | Theta(1) | Theta(1) |
| removeLast on non-empty input | Theta(n), except the constant singleton path | Theta(1) |
| get/set(i) | Exactly i next hops; Theta(1+i) | d(i) hops from the nearer endpoint; Theta(1+d(i)) |
| remove(i) | Head constant; otherwise locate i-1, Theta(i) | Theta(1+d(i)) including unlink |
| add(i,value) | Endpoints constant; interior Theta(i) locating predecessor | Append constant; otherwise Theta(1+d(i)) to locate successor and splice |
| splitOff(i) | i=0/n constant; interior Theta(i) locating predecessor | i=n constant; otherwise Theta(1+d(i)); i=0 is constant |
| Complete iteration, reverse, snapshot | Theta(1+n) own work | Theta(1+n) own work |
| appendAll(source) | Theta(1+m) with provided source/receiver | Same |
| clear | Theta(1) ownership reset | Same |

The singly get(n-1) implementation walks from head despite its cached tail;
getLast reads that tail directly. A tail cannot reveal the previous node needed
by singly tail removal. Doubly middle access shortens a walk but remains linear.
Summing get(i) across all indices is quadratic for each provided topology,
whereas a single iterator pass is linear. Value comparisons or user actions
must be accounted for separately from these reference-walk costs.

appendAll first completes other.toList() before any addLast. This makes the
self case finite: exactly the original m=n values are appended once. The
snapshot uses Theta(1+m) temporary value-reference storage and the destination
allocates m new nodes. For distinct owners, changing a source node value or
link later does not change destination topology/slots; mutating a shared
application object can still be observed through both. This is copying append,
not constant-time tail-to-head splicing.

splitOff allocates only the result container and reuses existing nodes; boundary
lookup is its variable work. reverse uses constant auxiliary references. Container
retained node storage is Theta(n). clear drops this owner's references, but an
old invalidated iterator can still hold nodes. Ownership reset does not measure
heap reclamation or establish an immediate memory reduction. JVM object layout,
alignment and reference encoding also prevent portable byte-overhead claims.

## Iterator observations and limits

Iterators capture a current node and expected structural counter; the ring
also captures remaining=size. A successful next advances one node (and consumes
one remaining count for the ring). Without invalidating changes, it therefore
returns exactly the logical sequence once. It reads current node values rather
than snapshot values: set of an unvisited node may affect the later result.

Add/remove, nontrivial reverse, non-empty clear and a split that transfers nodes
invalidate receiver iterators. Singleton/empty reverse, empty clear and split
at size do not. Appending zero values performs no structural changes. A fresh
empty split result is still a separate allocated container. Doubly descending
iteration follows the same policy in the other direction. Iterator.remove uses
the default unsupported operation; it is not a private-node deletion handle.

Checks detect ordinary structural misuse; they provide no synchronization,
thread-safe snapshot or concurrency correctness guarantee. The
[Java SE 21 Iterator](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Iterator.html)
contract makes removal optional. The standard
[LinkedList API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/LinkedList.html)
has additional List/Deque methods and best-effort fail-fast limits. Its peek/poll
empty-null policy is distinct from this interface's throwing endpoint methods.

## Standalone chain algorithms

All costs below include validation. Comparator costs are assumed constant;
normal reasoning also requires consistent order and no link-changing callback
effects. Null head represents an empty chain, unlike a non-empty node containing
null. The explicit node type is not a private container node.

| Existing method | Result / mutation / validation | Work / auxiliary state |
|---|---|---|
| hasCycle | Read-only Floyd identity detection; null gives false; accepts cycles | Theta(1+n) for n distinct reachable nodes; constant state |
| middle | Reject cycle, then return original node at floor(n/2), second middle for even n; null gives null; no writes | Theta(1+n), constant state; validation plus middle phase |
| reverse | Reject cycle before link writes; reuse original nodes and return new head; null gives null | Theta(1+n), constant state |
| mergeSorted(left,right,comparator) | Non-null comparator, reject cycles/order violations and shared nodes; stable destructive merge reuses element nodes | Theta(1+n+m) for separate lengths, constant state including one temporary sentinel |
| removeDuplicatesSorted | Non-null comparator, reject cycle/unsorted input; retain first node of each comparator-equal run and detach discarded links | Theta(1+n), constant state; null head returns null with a valid comparator |

For Floyd, after k iterations slow advances k edges and fast 2k. In an acyclic
chain the fast null guard terminates. With a tail prefix of length mu and cycle
length lambda, both enter the cycle and their relative offset increases by
one modulo lambda, forcing a meeting after at most mu+lambda iterations.
Equality of stored values is irrelevant; only node identity proves a meeting.
An intentional circular container is not passed to this acyclic-chain API.

For middle, slow has moved k and fast 2k while the two-step guard permits
progress. At null/last fast exit, slow is at floor(n/2). The public method has
already performed Floyd validation; the middle phase's one pass is not the
entire public-call traversal count. Reverse maintains disjoint processed and
unprocessed logical node sets, saves next, and transfers one node per iteration.
The original head becomes the final tail; callers must retain the returned head.

Sorted validation walks each chain after cycle rejection and returns its tail.
Two non-empty acyclic singly linked chains intersect iff they have the same
tail identity: once a node is shared, all following successors/tail are shared;
a shared tail itself is already an intersection. Thus overlap is rejected
without an extra set. Null tails do not make two empty chains overlapping.
Even an empty/non-empty merge validates the whole non-empty input, so attaching
one remaining chain in constant time does not make the public call constant.

During merge, the logical output prefix ending at tail contains exactly the
chosen nodes in stable sorted order. The remaining input heads represent the
unconsumed values; choose the smaller head and left on equality. The prefix's
tail.next need not be null during construction, so it is not an independent
finished chain at each step. After one input empties, attaching the other
finishes a sorted permutation of both disjoint input node sets. The temporary
sentinel is not an element node in the returned result. Old heads must no
longer be treated as independently owned chains.

Deduplication maintains a retained prefix with one first representative per
completed equivalence run. On equality it bypasses and detaches one next node;
otherwise it advances current. Each iteration eliminates or passes one node,
so it terminates with first-node identity preserved per sorted run. Sortedness
and comparator consistency make equivalent values contiguous. Comparator-zero
need not mean object equals. Invalid cycle/order/overlap rejection occurs
before algorithm link mutation; arbitrary comparator exceptions/side effects
later in a destructive phase are not a transactional rollback guarantee.

The [algorithm tests](../../src/test/java/org/anaalvarezdev/algorithms/linkedlists/LinkedListAlgorithmsTest.java)
execute real object-identity checks, stable tagged-record merging, null-aware
comparisons, invalid/shared chains and a 100,000-node iterative case. These are
Java tests, separate from the reasoning above; they are not Python translations.

## Execution and empirical boundary

The [dated review](documentation-review.md) records 710 Maven/JUnit tests on
Java 21, including 81 Linked Lists tests, with no failures/errors/skips. The
module's earlier 338-test build and historical benchmark run retain their own
dates; the new functional verification does not replace their provenance.

[LinkedListOperationsBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/linkedlists/LinkedListOperationsBenchmark.java)
has 15 methods over four sizes (60 defaults). It measures five representations
under traversal/checksum, fixed middle get and restored prepend/remove pairs.
Setup constructs shared boxed values across representations, outside timing;
ArrayList has capacity n+1 to exclude growth. Traversal includes iterator work
and unboxing. A prepend/remove score measures the pair, with node-allocation
sites inside the linked payload, subject to JVM optimisation. It is not an
isolated insertion/removal cost or an allocation profiler.

The [historical 30-cell record](../17-benchmarking/results/linked-lists/README.md)
preserves all reported means/errors, Oracle JDK 21.0.9 and author-reported
execution source a16a431. Only five configurations have supplied detailed
headers/iteration data; the remaining iteration values are not reconstructed.
The current Java 21 functional run and harness discovery add no timing score,
new historic artifact identity or causal cache/JIT/GC conclusion. No current
JMH workload measures standalone chain algorithms, deep-copy behaviour,
thread safety, split/append ownership or retained heap footprint.
