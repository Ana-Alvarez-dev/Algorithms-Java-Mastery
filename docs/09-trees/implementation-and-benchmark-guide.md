# Trees: Implementation and Benchmark Guide

## Selected Scope and Traceability

Package `org.anaalvarezdev.algorithms.trees` mirrors main, test and JMH roots.
This contribution starts from main **`3f1e5dfb6a0dc5144fa426585a0a00ba5eeeec60`**,
which integrated the author-confirmed Hash Tables benchmark provenance in PR #75.
Contracts and analysis precede the selected implementation and experiments.

| Module documents | Executable responsibility | Evidence |
|---|---|---|
| 01 fundamentals / 02 terminology / 03 binary trees | Immutable standalone `BinaryTreeNode`; edge-height convention | Empty/leaf examples, proper-tree precondition and deep-spine tests |
| 04 BST / 05 operations | `OrderedIntSet`, iterative `IntBinarySearchTree`, shared `AbstractIntSearchTree` queries | Shared contract, keyed deletion cases, snapshots, neighbours and shape |
| 06 traversals | `TreeTraversals`: preorder, inorder, postorder, level order | Four explicit orders; all 626 small shapes; 100,000-node spines |
| 07 balanced trees | `IntAvlTree`: cached heights, four rotation cases, deletion rebalance | Ordered-set oracle, height/balance diagnostics, sorted growth/deletion |
| 08 heaps / 09 Java PriorityQueue | `IntMinHeap`, checked `IntArrayCapacity`; JDK reference in tests/JMH | Heap-order/multiplicity invariants, growth, heapify, model sequences |
| 10 complexity | Cost ledger and assumptions below | Worst-case, amortised, auxiliary/output-space distinctions |
| 11 applications | `TreeAnalysis`, in-place `HeapSort`, `KSmallest` | Distance/level oracles, sort oracle and sorted-prefix oracle |
| 12 mistakes / 13 interview notes | Boundaries of each selected contract | Duplicates, extremes, empty state, tree shape, growth and unsorted heap snapshots |

There are **11 production Java files**, including the interface and two helpers,
**59 executed module tests**, and **12 JMH methods in two classes**. Red-black
and B-trees, tries, arbitrary-key heap removal, a generic comparator API, strict
successor/predecessor methods, iterators, LCA, expression evaluation, graph
algorithms and scheduling services remain extensions. They are not counted as
implemented because conceptual documents mention them. There is no new external
dependency and no change to earlier production/test packages.

## Contracts

### Ordered int sets

`IntBinarySearchTree` and `IntAvlTree` implement the selected `OrderedIntSet`
contract, not the full `java.util.Set` interface:

- Every int key is valid, including MIN_VALUE and MAX_VALUE. Comparisons use
  relational operators, not potentially overflowing subtraction.
- Keys are unique. `add` returns true exactly when an absent key is inserted;
  `remove` returns true exactly when a present key is removed. Duplicate/absent
  updates leave observable contents, count and shape unchanged.
- `contains` queries membership. `first`/`last` use `OptionalInt.empty()` when
  empty. `floor(x)` means greatest key <= x; `ceiling(x)` means least key >= x.
  Both are inclusive, accept absent query keys and return empty if no candidate.
  They are not the strictly smaller/larger predecessor/successor of a node.
- Height counts **edges**: empty -1, leaf 0. A sorted-insertion BST with n>0
  has height n-1. AVL stores height per node; ordinary BST computes it on demand.
- `toArray` is an independent ascending snapshot; caller mutation cannot alter
  the container. Internal nodes/root are not exposed. `clear` drops the root
  and resets size; eventual garbage-collection work is not timed as immediate
  node-by-node deletion.
- Containers are mutable and not thread-safe. There is no iterator, generic
  null policy or Java Set equality/hash contract: keys are primitives.

### Standalone binary trees

`BinaryTreeNode` stores a final int and final left/right references. Null child
and null root mean an empty subtree/tree. Values may repeat, and there is no BST
ordering requirement. Identity equality is intentional; no recursive equality,
hashing or string rendering is generated. These nodes are independent of the
private nodes in the mutable search-tree containers.

Traversal and analysis inputs must form a **proper tree**, without a node shared
by two root-to-node paths. The immutable constructor allows callers to reuse a
child, so this shape is an explicit precondition, not a claim of automatic
validation. DAG/cycle processing belongs to a graph API. Ordinary traversal does
not add a visited set or validate the whole graph on every call.

Each traversal returns a fresh mutable `List<Integer>`, including for null root.
`TreeAnalysis.analyse` returns an immutable `Summary(size,height,diameter,balanced)`.
Diameter is the longest node-to-node path in **edges**, which may lie inside one
subtree; empty diameter is defined as 0. Balanced means both subtrees satisfy the
same condition and their heights differ by at most one at every node. The empty
summary is `(0,-1,0,true)` and a leaf gives `(1,0,0,true)`.

### Min-heap and selected applications

`IntMinHeap` accepts duplicates and all int values. `peek` and `poll` return
empty OptionalInt for absence; poll otherwise removes one minimum. Offer has no
priority tie-order promise. `toArray` returns an independent **heap-order**
snapshot, not sorted iteration. `IntMinHeap(int[])` rejects null, copies input
and builds bottom-up, preserving the input. A zero requested capacity reserves
one slot; negative or above `2^30` is rejected. Default capacity is sixteen.
Growth doubles, capped at `2^30`; an offer beyond that limit throws
IllegalStateException before logical mutation. Real allocation may fail earlier.
`clear` retains capacity and sets live size to zero; unused primitive slots are
not observable members and need not be zeroed.

`HeapSort.sort(int[])` rejects null before mutation, sorts ascending in place,
preserves multiplicity and uses an internal max-heap. It is not stable and does
not allocate a second heap proportional to input length. It belongs here as the
selected heap application; earlier Sorting implementations remain unchanged.

`KSmallest.select(values,k)` rejects null and requires `0 <= k <= values.length`.
It preserves input and returns an independent ascending array of k values,
including duplicates. k=0 returns an empty array immediately. For k>0 it copies
and heapifies the complete input then extracts k minima. This is not a bounded
O(k)-storage streaming top-k algorithm.

## Strategies, Pseudocode and Traces

### BST insertion and deletion

```text
ADD(x): follow comparisons from root
  if equal: return unchanged
  if selected child is absent: attach leaf; increment size once
REMOVE(x): locate node and its parent
  if absent: return unchanged
  if two children: copy minimum key from right subtree;
                   select that successor and its parent for physical removal
  reconnect parent (or root) to selected node's sole child or null
  decrement size once
```

Search maintains that any matching key lies in the current subtree. One branch
can be discarded by strict BST ordering. The remaining path shrinks until a
match or null. Deletion with two children uses the right subtree's minimum,
which has no left child; its possible right child must be reconnected. Copying
its key and unlinking that one node preserves uniqueness and strict ordering.
Only one physical node/count is removed. Updating an existing parent link or the
root preserves all unaffected subtrees. These operations use no recursion.

For insertion `[40,20,80,60,90,50,70,55]`, deleting 40 copies successor 50.
The old 50 is the left child of 60, with right child 55: reconnect 60.left to 55.
The result snapshot is `[20,50,55,60,70,80,90]`. Leaf, singleton, one-child root
and immediate-right-child successor cases use the same final reconnection.

For floor, a visited key <= x is a candidate and search continues right to
improve it; a larger key excludes its right subtree. The dual invariant gives
ceiling. A null endpoint returns the best candidate or absence. Neither query
computes x+1/x-1, avoiding integer-boundary overflow.

### AVL updates and rotations

```text
UPDATE(node,x): apply BST insertion/deletion recursively
  recompute height = 1 + max(left height,right height)
  if left-heavy by two:
    if left child is right-heavy: rotate that child left
    rotate node right
  if right-heavy by two: apply the symmetric case
  return the possibly new subtree root
```

In-order partitions A < left-root < B < old-root < C remain in that order
through a right rotation. Left rotation is symmetric; double rotations compose
two ordering-preserving steps. Update the lower node's height before the new
subtree root's height. Children returned from recursion are already valid AVL
subtrees, so repair acts on a locally changed height. The recursive path is
finite; insertion/removal changes size once, even when successor deletion is
used. Deletion may propagate rebalancing through multiple ancestors and must
handle a heavy child whose balance is zero, not only insertion-shaped cases.

Insertion `[3,2,1]`, `[1,2,3]`, `[3,1,2]` and `[1,3,2]` exercises right, left,
left-right and right-left repair; every result has root 2 and height 1. For
`[4,2,5,1,3]`, deleting 5 requires right rotation with a balanced heavy child.
The reflected case and long deletion sequences are tested separately.

Let N(h) be the minimum AVL node count at edge-height h. N(-1)=0, N(0)=1 and
N(h)=1+N(h-1)+N(h-2). This Fibonacci-like growth gives height O(log(n+1)).
Constant work/rotations per visited ancestor therefore gives worst logarithmic
path operations; deletion need not have a constant total rotation count.

### Traversals and bottom-up metrics

```text
PREORDER: stack root; pop/output; push right then left
INORDER: push left spine; pop/output; continue from right child
POSTORDER: keep path and previous completed node; descend left,
           then an unfinished right subtree, then output/pop parent
LEVEL ORDER: dequeue/output; enqueue left then right
ANALYSE: explicit frames first analyse left, then right, then combine summaries
```

DFS stack entries encode unfinished subtrees in visiting order. Postorder emits
a node only after both child subtrees are complete; identity comparison with the
previously completed right child prevents revisiting it. The proper-tree
precondition matters here. BFS queue order preserves increasing depth and
left-to-right order. Every node is output once, so complete traversal stays
Theta(n) even in a balanced tree. An explicit frontier replaces recursive call
frames and supports deep input without overflowing the call stack.

Analysis uses three frame phases and two child summaries. The combination is:

```text
size = 1 + left.size + right.size
height = 1 + max(left.height,right.height)
diameter = max(left.diameter,right.diameter,left.height+right.height+2)
balanced = left.balanced and right.balanced and abs(left.height-right.height)<=1
```

A longest path either lies inside a child or crosses this node. These exhaustive
cases justify the diameter formula. By induction, completed child summaries are
correct, giving the parent summary. Each frame advances through finite phases
and each node is pushed/popped once. Only the active root-to-node frames and
constant summaries per frame are retained.

For root 4, left subtree `2(1,3)` and right subtree `6(null,7)`, orders are:
preorder `[4,2,1,3,6,7]`, inorder `[1,2,3,4,6,7]`, postorder `[1,3,2,7,6,4]`
and level order `[4,2,6,1,3,7]`. Summary is `(6,2,4,true)`.

### Heap operations, heapify and applications

```text
OFFER(x): reserve space if full; move larger ancestors down; place x in the hole
POLL: save root; move final live value to root; move smaller child up until ordered
BUILD: copy input; sift down parents from floor(n/2)-1 back to zero
HEAP SORT: build max-heap; swap maximum into final unsorted slot;
           shorten heap prefix and sift its root; repeat
K SMALLEST: build copied min-heap; poll k times into ascending result
```

At zero-based slot i, children are 2i+1 and 2i+2; parent is floor((i-1)/2).
The sift-down loop enters only while i < size/2, so child-index arithmetic is
bounded by the live range and cannot overflow for supported capacities. A sift
hole has valid child subheaps; choosing the smaller child restores order above
that child while moving the only possible violation downward. Offer moves its
single violation upward. Both move strictly along a finite tree path.

Bottom-up build processes already-heapified child subtrees. The aggregate work
is bounded by n times the convergent sum of h/2^(h+1), hence O(n), with input
copying giving Omega(n). It is not n independent worst-height sifts. With input
`[5,1,4,2,3]`, build produces `[1,2,4,5,3]`; poll returns 1 and restores a valid
heap. Snapshots need not be sorted.

Heap Sort maintains a max-heap prefix and a sorted suffix whose values are at
least every prefix value. Extracting the maximum extends the suffix by one;
the shrinking prefix proves termination and ascending output. KSmallest removes
one smallest remaining value each iteration, preserving multiplicity and the
ascending selected prefix. Its loop terminates after k iterations.

## Complexity Ledger

Let n be live nodes/values, h edge-height, w maximum width and c retained array
capacity. Primitive comparisons are constant; array/list allocation and output
storage are counted explicitly. Bounds include O(1) empty handling.

| Work | Time | Additional storage / condition |
|---|---|---|
| BST contains/add/remove/extrema/floor/ceiling | O(h+1), worst O(n) | O(1); no balancing guarantee |
| AVL path operations | O(log(n+1)) worst | Updates use O(log(n+1)) call frames; read queries O(1) auxiliary |
| Height | BST Theta(n); AVL Theta(1) | BST O(w) queue; AVL cached |
| Ordered snapshot | Theta(n) | Theta(n) result plus O(h+1) stack |
| Set size/isEmpty/clear | Theta(1) | Clear releases root; deferred GC is separate |
| DFS traversal | Theta(n) | Theta(n) output plus O(h+1) frontier |
| Level-order traversal | Theta(n) | Theta(n) output plus O(w) queue |
| TreeAnalysis | Theta(n) | O(h+1) explicit frames; constant-size result |
| Heap peek/size/isEmpty/capacity/clear | Theta(1) | Retains capacity on clear |
| Heap offer without growth / poll | O(log(n+1)) | O(1) local storage |
| Growing heap offer | Theta(n) copying plus sift | Old/new arrays coexist during resize |
| Sequence of heap offers | O(log(n+1)) amortised upper bound per offer | Geometric growth adds O(1) amortised copying; not worst-case constant insertion |
| Heap constructor from array | Theta(n) | Theta(n) retained array; input preserved |
| Heap-order snapshot | Theta(n) | Theta(n) independent result |
| Heap Sort | O(n log(n+1)), worst Theta(n log n) | O(1) auxiliary, in-place, unstable |
| KSmallest, k>0 | Theta(n) build + O(k log(n+1)) | O(n) heap plus Theta(k) result |
| KSmallest, k=0 | Theta(1) | Empty result |

Tree retained storage is Theta(n); heap retained storage is Theta(c), even after
deletions. General binary-tree insertion/deletion has no universal complexity
without a position/ownership contract. Keyed BST removal must locate a node;
constant-time unlink given a known node is a different operation. Balance keeps
paths short but does not avoid a linear complete traversal.

JDK TreeSet documents logarithmic add/remove/contains. PriorityQueue documents
constant peek/size and logarithmic enqueue/dequeue, with linear contains and
remove(Object); these last operations are not root poll. Array resizing is a
separate single-operation copying cost: the consulted OpenJDK 21 source's
PriorityQueue `grow` uses `Arrays.copyOf`. The benchmark reserves capacity so no
growth is included. JDK Optional/null/boxing/storage mechanics differ from this
selected primitive API, and exact allocation or byte footprint is not inferred.

## Automated Verification

On **2026-10-19**, using verification-host **OpenJDK 21+35-2513**:

| Concrete test class | Executed tests |
|---|---:|
| IntBinarySearchTreeTest | 13, including 10 shared contract tests |
| IntAvlTreeTest | 14, including the same 10 shared tests |
| IntMinHeapTest | 11 |
| IntArrayCapacityTest | 2 |
| TreeTraversalsTest | 6 |
| TreeAnalysisTest | 5 |
| HeapSortTest | 4 |
| KSmallestTest | 4 |

The **465-test baseline** built successfully. Normal Maven tests and benchmark
profile clean package then passed **524 tests**, zero failures/errors/skips.
The shared set contract checks **5,000 seeded steps per representation** against
TreeSet, all **24 x 24 = 576** insertion/removal-order pairs over four keys,
integer extremes, duplicate/absence policy, neighbours and independent snapshots.
AVL-specific cases cover four insertion rotations, deletion with zero-balance
heavy children, sorted 10,000-key growth/drain and alternating extrema removal.
Diagnostics verify strict ordering, identity uniqueness, count and AVL cached
heights/balance; they are not called by production operations or JMH.

The heap has a **5,000-step** PriorityQueue oracle, capacity boundary arithmetic
without huge allocations, growth, odd/even child selection, duplicates and
reference-independent array snapshots. All **1,093 arrays** of lengths 0–6 over
`{-1,0,1}` are heapified/drained and sorted against independent oracles.
KSmallest covers all **7,108 array/k combinations** from that domain.

All **626 binary-tree shapes** through seven nodes are materialised without
shared node identity. Traversals use simple small recursive/level oracles;
analysis diameter uses all-pairs undirected distances, independent of the
production bottom-up formula. Left/right 100,000-node traversal spines and a
100,000-node analysis spine check explicit-stack behaviour. Deep tests do not
claim unbounded memory or a complete proof over every possible input.

JMH lists all **12 methods**. All **24 short smoke configurations** at size 100
completed across the two insertion orders and two heap distributions. A separate
probe checks all **72 default method/size/fixture combinations**, nine calls
each, including cursor wrap, shape/height, hit/miss results, heap minima,
restored multiset/capacity, independent builds and invariants. JavaDoc passes
`-Xdoclint:all,-missing`. These are functional/harness checks, not research timing.

## JMH Design and Evidence Protocol

### Tree lookup

`TreeOperationsBenchmark` has six methods: BST/AVL/JDK TreeSet hit and miss.
Sizes 100/1,000/10,000 and SHUFFLED/SORTED insertion give **36 configurations**.
Trial setup creates identical key sets `0..n-1`. A Fisher-Yates shuffle uses
seed **20261019**; SORTED produces an unbalanced right spine. No updates are
timed. Hits cycle preboxed query keys, with cursor/index selection timed; manual
APIs unbox these values. JDK stores those same boxed references, including the
usual small-integer cache context. Miss repeats fixed absent key n above the
maximum. Hit and miss differ in key-selection work and path distribution, so
their difference is not an isolated membership-outcome penalty.

One shuffle per size is not a sample of all insertion permutations. Seeded
shape is reproducible, not a proof of the expected random-BST bound. Comparing
representations includes primitive versus boxed API/storage work. Reserved
trial state is warm and shared across calls within a trial. No update, rotation,
allocation-rate or cache-cause conclusion follows from lookup means.

### Heap operations and construction

`HeapOperationsBenchmark` has manual/JDK peek, manual/JDK poll/restore and two
manual builds (bottom-up versus repeated offer), also **36 configurations**.
Sizes are 100/1,000/10,000; distributions RANDOM/REVERSE_SORTED. RANDOM uses
SplittableRandom seed **20261019**, unrestricted int values; descending uses
n..1. Both initial queues use repeated offers with reserved n capacity. Initial
construction and boxing are outside peek/pair timing; JDK restoration reinserts
the same removed boxed object. Manual OptionalInt access and any associated
optimisation remain part of the benchmark mechanics.

Each pair removes the current minimum and immediately offers it again, restoring
count and multiset but not promising identical physical layout. Repeated minimum
restoration is not representative of arbitrary priority churn. No growth is
timed. Build methods include new container/backing-array allocation and reading
the same trial int array: bottom-up copies then heapifies; repeated-offer reserves
n then inserts every value. Both return the built heap; no copy baseline is
subtracted. A descending offer sequence is an intentional adverse build fixture,
not a random sample. Individual pair and build scores answer different questions.

### Windows execution

Both classes use average ns/op, **one thread, three 500 ms warmups, five 500 ms
measurements and two forks**. Full defaults total **72 configurations**.
The initial measured subset below uses two sizes: **24 tree + 24 heap cells**.
A controlled cell has `Cnt=10` measurement-iteration averages, not ten invocations.
Result and Interpretation remain pending author measurements. Smoke scores must
not be promoted to empirical research evidence.

After creating/merging the feature PR, from the local repository root:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd -Pbenchmark clean package
& "C:\Program Files\Java\jdk-21\bin\java.exe" -version
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar -l ".*trees.*"
```

The explicit path is the author's verified JDK 21 installation, not a portable
requirement. Maven's runtime and JMH's executable must both be checked;
`release=21` alone does not select either runtime. Package builds tests/jar but
does not execute a performance measurement. Linux uses `bash mvnw` with a
selected JDK 21 and the same jar/options.

Optional short harness smoke, distinct from measurement:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*trees.*" -p size=100 -wi 1 -w 50ms -i 1 -r 50ms -f 1 -t 1 -foe true
```

Measure the initial subsets separately so each pasted result is identifiable:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*trees.TreeOperationsBenchmark.*" -p size=100,1000 -foe true
```

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*trees.HeapOperationsBenchmark.*" -p size=100,1000 -foe true
```

Capture SHA, actual JDK/JMH, headers, settings, full final tables, errors and
completion markers as console text/captures. Include printed per-fork iterations
when available, and CPU/OS details when known. Evidence will remain Markdown,
without JSON/log exports. Keep both runs separate and identify any later
changes of source/runtime/size/seed. Time alone does not establish correctness,
scaling exponents, causes, memory usage or statistical significance.

## Sources and Review Decisions

The supplied CLRS **Third Edition (2009)** was consulted: Chapter 6, printed
pp. 151–166 (heap operations/build/sort/priority queues); Chapter 12, pp. 286–307
(BST order, search, keyed updates and random-build assumptions); §13.2,
pp. 312–314 (rotations); and **Problem 13-3, p. 333**, which defines AVL balance,
cached height and its logarithmic-height argument. These are third-edition
locations, not fourth-edition pagination. AVL deletion here is an independent
implementation justified by local height repair, not copied insertion pseudocode
presented as a textbook deletion procedure.

- [Java 21 TreeSet](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/TreeSet.html): ordered collection reference and documented basic-operation bounds.
- [Java 21 PriorityQueue](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/PriorityQueue.html): null/ordering/iteration policies and documented operations.
- The official OpenJDK 21+35 verification distribution's `lib/src.zip`, entry
  `java.base/java/util/PriorityQueue.java`, was consulted for the `grow` array copy.

All thirteen conceptual documents link to this guide. The README's misnumbered
contents are corrected. The review distinguishes inclusive neighbours from
strict successor/predecessor, edge-height conventions, proper-tree ownership,
linear full traversal, insertion growth, root extraction versus keyed deletion,
and output space versus working frontier. Red-black/B-tree techniques and
advanced applications remain conceptual extensions. Prior Arrays/Searching
findings remain tracked in the project review and are not silently fixed here.
