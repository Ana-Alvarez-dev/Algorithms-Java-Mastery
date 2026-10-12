# Linked Lists: Implementation and Benchmark Guide

## Current Scope and Traceability

This module extends the array-based algorithms with mutable generic structures.
It implements three topologies and five representative algorithms over explicit
node chains. The educational API exposes the computational contracts without
implementing the entire Java Collections Framework.

| Documentation | Executable implementation | Test evidence | Empirical question |
|---|---|---|---|
| [Fundamentals](01-linked-list-fundamentals.md) | `LinkedSequence<E>` shared contract | Shared contract tests | Conceptual prerequisite |
| [Singly linked](02-singly-linked-list.md) | `SinglyLinkedList<E>` | `SinglyLinkedListTest` | Traversal, middle access, prepend/remove pair |
| [Doubly linked](03-doubly-linked-list.md) | `DoublyLinkedList<E>` | `DoublyLinkedListTest`, including backward traversal | Same operations |
| [Circular](04-circular-linked-list.md) | `CircularLinkedList<E>`, singly linked ring | `CircularLinkedListTest`, including singleton/two-node rings | Same operations, one finite traversal lap |
| [Operations](05-list-operations.md) | Endpoint/indexed operations, reversal, copying append, transferring split | Shared contract and seeded model tests | Explicit operation costs |
| [Complexity](06-complexity-analysis.md) | Cached head/tail/size; explicit bounded traversals | Invariant diagnostics after mutations | Analysis remains separate from timing |
| [Java representation](07-java-linked-list.md) | Generic private nodes and iterators | Null, duplicates, snapshots, exceptions, iterator policy | `ArrayList` and JDK `LinkedList` references |
| [Common algorithms](08-common-algorithms.md) | `LinkedNode<E>` and `LinkedListAlgorithms` | `LinkedListAlgorithmsTest` | No additional benchmark without a distinct question |
| [Problem solving](09-problem-solving-guide.md) | Contracts, validation and ownership decisions | Operation sequences and edge cases | Correctness before timing |
| [Common mistakes](10-common-mistakes.md) | Endpoint repair, reciprocal links, cycle rejection | Structural invariants, invalid-operation tests | No inference from speed to correctness |
| [Interview review](11-interview-notes.md) | Assessment tied to the selected implementations | Worked contract questions | Workload-based engineering reasoning |

The [source package](../../src/main/java/org/anaalvarezdev/algorithms/linkedlists/README.md)
uses `org.anaalvarezdev.algorithms.linkedlists`. The same package path is used
under `src/main/java`, `src/test/java`, and `src/jmh/java`.

## Shared Sequence Contract

`LinkedSequence<E>` is a small common contract implemented independently by
the three concrete structures. It extends `Iterable<E>`, not `java.util.List`.

- A new instance is empty. Values may be null, duplicated, or application objects.
- Indices are zero-based. `get`, `set`, and `remove` require `[0, size)`;
  `add` and `splitOff` require `[0, size]`.
- Invalid indices throw `IndexOutOfBoundsException` before mutation.
- `getFirst`, `getLast`, `removeFirst`, and `removeLast` throw
  `NoSuchElementException` on an empty sequence, avoiding ambiguous null sentinels.
- `set` and removal return the previous/removed value. `indexOf` returns the
  first `Objects.equals` match or `-1`; null is a supported search value.
- `toList` returns an independent mutable value snapshot. Element references
  are shallow copies; internal nodes are never exposed.
- `appendAll` copies the source's value snapshot into new destination nodes.
  It leaves the source unchanged, rejects a null source, and supports self-append
  by duplicating the original contents once. It does not splice shared nodes.
- `splitOff(index)` transfers existing suffix nodes into a new sequence of the
  same topology. The original retains the prefix; neither container shares nodes.
- `reverse` changes logical order in place and preserves element multiplicities.
- Forward iterators make one finite pass. Circular iterators stop after their
  captured size, not at a null reference. Exhausted `next` throws
  `NoSuchElementException`; iterator `remove` is unsupported.
- Structural changes, including reversal and a nonempty split, invalidate
  existing iterators with `ConcurrentModificationException`. Value replacement
  is nonstructural. Empty clear/reverse and splitting at size are no-ops.
- These structures are not synchronized. Iterator checks detect misuse; they
  are not a concurrency-control mechanism.

Example:

```java
SinglyLinkedList<String> cities = new SinglyLinkedList<>();
cities.addLast("Tucuman");
cities.addFirst("Salta");
cities.add(1, "Cordoba");
// [Salta, Cordoba, Tucuman]
SinglyLinkedList<String> suffix = cities.splitOff(1);
// cities: [Salta], suffix: [Cordoba, Tucuman]
suffix.reverse();
// suffix: [Tucuman, Cordoba]
```

## Structural Correctness

All three containers maintain head, tail, size and a structural-change counter.
Their nodes are private; public callers cannot attach unrelated chains or alter
the topology outside the contracted operations.

| Topology | Invariant |
|---|---|
| Singly | Empty endpoints are null; otherwise exactly size nodes lead from head to tail and then null |
| Doubly | The singly endpoint/count condition plus reciprocal next/previous links; head.previous is null |
| Circular | Empty endpoints are null; otherwise exactly size distinct nodes form a ring, tail.next is head, and a singleton points to itself |

Insertion saves the successor before linking the new node. Removal reconnects
neighbors before detaching the removed node, then repairs endpoints and size.
Reversal saves the unprocessed successor before redirecting links. Doubly linked
reversal swaps both links; circular reversal updates exactly size nodes.

Splitting repairs both ownership boundaries. In a doubly linked split, the new
suffix head loses its predecessor. In a circular split, both resulting tails
close their own ring. This makes later mutations independent rather than leaving
two container objects attached to the same chain.

Package-visible `hasValidStructure` diagnostics traverse with explicit bounds
and check endpoints, counts and topology. Tests call them after state changes.
They are not automatically invoked by every public operation: scanning the
structure on each mutation would change the stated constant-time endpoint costs.
These checks are execution evidence; class Javadocs explain the preservation
and termination arguments.

## Exact Complexity of the Selected API

Let `n` be the receiver size and `m` the appended source size. Value equality and
comparison are assumed constant-time; more expensive application comparisons
must be added to the corresponding bounds.

| Operation | Singly | Doubly | Circular singly |
|---|---:|---:|---:|
| `size`, `isEmpty`, endpoint reads | Θ(1) | Θ(1) | Θ(1) |
| `addFirst`, `addLast`, `removeFirst` | Θ(1) | Θ(1) | Θ(1) |
| `removeLast` | O(n) | Θ(1) | O(n) |
| Indexed read/update/insert/remove | O(n) worst case | O(n), traversing from nearer endpoint | O(n) worst case |
| First-match search | Θ(1) best; Θ(n) worst | Same | Same |
| One complete traversal or value snapshot | Θ(n) | Θ(n) | Θ(n) |
| `reverse` | Θ(n) | Θ(n) | Θ(n) |
| `splitOff` | O(n) locating predecessor | O(n) locating boundary | O(n) locating predecessor |
| `appendAll` | Θ(m) | Θ(m) | Θ(m) |
| `clear` | Θ(1) ownership reset | Θ(1) ownership reset | Θ(1) ownership reset |

All containers occupy Θ(n) node storage. Snapshot creation and copying append
also need Θ(m) temporary value references and Θ(m) new destination nodes for
append. Split reuses nodes and allocates only the returned container; reversal
uses Θ(1) auxiliary state.

Maintaining a tail makes append constant-time, but does not reveal the previous
node needed for singly linked tail removal. The head-only assumptions in the
introductory documents describe a different representation. Likewise, a cached
size is constant-time even though counting nodes by traversal is linear.

`clear` drops this owner's references instead of visiting and nulling every
node. An old invalidated iterator can keep its previously reachable nodes alive
until that iterator is released. Garbage collection determines reclamation;
clearing a container does not establish an immediate heap-memory reduction.

## Standalone Node Algorithms

`LinkedNode<E>` deliberately exposes successor-link changes for algorithm
exercises. It is independent of container-owned private nodes: linking a cycle
cannot corrupt a container's cached size or endpoints.

| Method | Contract and ownership | Time | Auxiliary space |
|---|---|---:|---:|
| `hasCycle` | Floyd slow/fast pointers; identity comparison; accepts cyclic input | O(n) over distinct reachable nodes | Θ(1) |
| `middle` | Returns node at floor(n/2), choosing the second middle when even; null for empty; nonmutating | Θ(n) | Θ(1) |
| `reverse` | Returns the new head, reusing and relinking original nodes | Θ(n) | Θ(1) |
| `mergeSorted` | Destructive stable merge of disjoint sorted chains; equal heads choose left first | Θ(n + m) | Θ(1) |
| `removeDuplicatesSorted` | Retains the first node of each comparator-equal run | Θ(n) | Θ(1) |

Except for `hasCycle`, these methods reject cycles before mutation. Sorted
methods require a non-null consistent comparator and validate order first.
Merging rejects shared nodes: two intersecting acyclic chains share their tail,
so tail identity detects overlap without a hash set. Null stored values are
supported when the supplied comparator orders them.

Cycle/order/overlap validation adds linear work, included in the stated bounds.
The two-pointer middle phase uses one pass; the public method also performs the
cycle-validation pass. Merge uses one temporary sentinel but reuses every
returned element node. After a destructive operation, callers retain the
returned head and no longer treat the original heads as independent owners.

Recursive traversal, partitioning, alternative cycle algorithms, sentinel-backed
containers and linked-list sorting remain later extensions. They are conceptual
topics, not implementation evidence claimed by this selected scope.

## Automated Verification

The three concrete structure tests inherit **21 shared contract tests** from
`LinkedSequenceContractTest`. These include:

- empty/singleton/endpoint operations, invalid indices and unchanged state;
- nulls, duplicates, generic types, equality-based search and snapshots;
- reversal across boundary lengths and splitting at every boundary of small lists;
- independent copy append, self-append and node-ownership separation;
- finite/exhausted iterators, structural invalidation and nonstructural replacement;
- **2,000 seeded operations per topology**, compared with `java.util.LinkedList`,
  with structural checks after each resulting state.

Doubly linked tests add backward traversal and iterator invalidation. Circular
tests add singleton/two-node ring transitions. The algorithm test class adds
15 tests, including genuine cycles versus repeated values, stable object ordering,
identity reuse, null-aware comparators, invalid/shared chains, and a 100,000-node
iterative case.

On **2026-10-12**, verification with OpenJDK 21 passed **338 repository tests**,
including **81 Linked Lists tests**, and built the executable JMH jar. JMH
discovered all fifteen methods. A separate probe checked all 60 method/size
combinations with repeated calls, validating outputs and restored contents.
A short forked smoke run checked the generated harness on size 100; its scores
are not research results.

## JMH Experiment

`LinkedListOperationsBenchmark` compares three manual structures with
`ArrayList<Integer>` and `java.util.LinkedList<Integer>` for three distinct
operations. This produces **15 methods x 4 sizes = 60 default configurations**.

| Workload | Timed operation | Analytical expectation |
|---|---|---|
| `*Traverse` | Enhanced-for iteration and a returned long checksum | Θ(n) for all five representations |
| `*MiddleGet` | `get(size / 2)` returning the fixed middle value | Θ(1) array indexing; Θ(n) linked traversal |
| `*PrependRemove` | Add -1 at the head, then remove and return it | Θ(n) ArrayList shifts; Θ(1) linked endpoint operations |

Trial setup populates each representation with the same boxed integers
`0..n-1`. Boxing and construction are excluded. ArrayList is pre-sized to `n+1`
so the mutation pair measures shifts without capacity growth. The pair restores
size and contents on every call, avoiding a list that grows indefinitely across
measurement invocations. No per-invocation dataset reconstruction is needed.

The checksum uses `long`: for 100,000 values the expected sum is 4,999,950,000,
which cannot fit in an `int`. Returned results are consumed by JMH. Traversal
includes iterator creation, checks, unboxing and dispatch; the mutation workload
includes the add/remove allocation sites, subject to JVM optimization. It is a
pair measurement, not an isolated insertion or removal measurement. Memory and
allocation claims would require a separate profiler.

Defaults are sizes **100, 1,000, 10,000, 100,000**, one thread, three 500 ms warmup
iterations, five 500 ms measurement iterations, and two forks. Units are `ns/op`.
The fixed middle target and repeatedly accessed structures are deliberate
workloads; they do not represent arbitrary access distributions or cold-memory
behavior. Setup holds all five representations and uses interleaved node
construction, which is also part of the documented memory context.

`Result` and `Interpretation` remain pending a controlled author-supplied run.
Do not invent timing values or infer asymptotic bounds from smoke checks.

## Run from Windows PowerShell

After merging the PR, the module can be built and executed from the author's
local `main`:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd test
.\mvnw.cmd -Pbenchmark clean package
java -jar target\benchmarks.jar -l ".*linkedlists.*"
```

First check the harness briefly:

```powershell
java -jar target\benchmarks.jar ".*linkedlists.*" -p size=100 -wi 1 -w 50ms -i 1 -r 50ms -f 1 -t 1 -foe true
```

Then capture a controlled 30-configuration subset while keeping terminal output:

```powershell
git rev-parse HEAD
java -version
java -jar target\benchmarks.jar ".*linkedlists.*" -p size=100,1000 -foe true -rf json -rff linked-lists-first-run.json | Tee-Object -FilePath linked-lists-first-run.log
```

Omit the size override to run the full 60-configuration matrix. Keep each run's
JSON, complete log, source commit, command, effective JMH settings, CPU and OS
version. Preserve actual runtime version: compiling for Java 21 does not force
the Java command to run under that JDK. Store later experiments separately.

On Linux, use `bash mvnw -Pbenchmark clean package` and
`target/benchmarks.jar` in the Java commands. When results arrive, populate the
benchmark's Result/Interpretation sections and a linked evidence record under
`docs/17-benchmarking/results/linked-lists/`, preserving raw data and errors.

## Academic and API References

The supplied *Introduction to Algorithms*, **Third Edition, 2009**, Section 10.2,
printed pp. 236–239, was consulted for linked relationships, variants, search,
insertion/removal and sentinels. The module's fourth-edition bibliography is a
separate reference; these page locators belong to the supplied third edition.
The container API and its ownership policies are independent repository choices.
Floyd's algorithm and the representative chain exercises are not attributed to
those CLRS pages as though every exercise were presented there.

- [Oracle Java 21 LinkedList specification](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/LinkedList.html): standard doubly linked reference, null support, nearest-end indexing and iterator policy.
- [Oracle Java 21 Iterator specification](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Iterator.html): exhaustion and optional removal contract.

The educational containers support a selected API. They do not replace the
standard library's full List/Deque implementation.
