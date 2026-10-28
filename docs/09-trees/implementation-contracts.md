# Trees: Java Contracts and Proof Map

Reviewed on **2026-10-28** against main
`7182a9990e06dbd4e7d9dd82302ca32932b52d5a` (PR #106 merge).
The [review report](documentation-review.md) records corrections and actual
Java verification; the [implementation guide](implementation-and-benchmark-guide.md)
retains the original pseudocode, traces and historical experiments.

## Three representations and their APIs

| Representation | Observable contract | Source |
|---|---|---|
| Ordered int set | Unique keys, every int valid; boolean add/remove report membership change; contains, OptionalInt extrema/inclusive neighbours, cached size, height and independent ascending int[] snapshot | [OrderedIntSet](../../src/main/java/org/anaalvarezdev/algorithms/trees/OrderedIntSet.java), [shared queries](../../src/main/java/org/anaalvarezdev/algorithms/trees/AbstractIntSearchTree.java), [BST](../../src/main/java/org/anaalvarezdev/algorithms/trees/IntBinarySearchTree.java), [AVL](../../src/main/java/org/anaalvarezdev/algorithms/trees/IntAvlTree.java) |
| Standalone binary tree | Immutable value/child fields; repeated values allowed; null root/child means empty. Inputs must be proper trees without shared child identity; this is a precondition, not automatic validation | [BinaryTreeNode](../../src/main/java/org/anaalvarezdev/algorithms/trees/BinaryTreeNode.java), [traversals](../../src/main/java/org/anaalvarezdev/algorithms/trees/TreeTraversals.java), [metrics](../../src/main/java/org/anaalvarezdev/algorithms/trees/TreeAnalysis.java) |
| Dynamic int min-heap | All ints and duplicates, void offer, OptionalInt peek/poll; poll removes one minimum; independent heap-order snapshot; no arbitrary-key removal or membership API | [IntMinHeap](../../src/main/java/org/anaalvarezdev/algorithms/trees/IntMinHeap.java), [capacity arithmetic](../../src/main/java/org/anaalvarezdev/algorithms/trees/IntArrayCapacity.java) |

The mutable containers expose neither internal nodes nor iterators, and provide
no thread-safety or full JCF Set/Queue contract. Primitive keys have no null
policy. Duplicate set add and absent remove return false without changing
logical shape/contents/count. Empty extrema/neighbours use OptionalInt.empty(),
not a reserved int. For keys {10,20,30}, floor(20)=ceiling(20)=20,
floor(25)=20 and ceiling(25)=30; floor(9) and ceiling(31) are empty. Strict
predecessor/successor are different conceptual operations.

Array inputs to heap construction, HeapSort and KSmallest reject null. Heap
construction copies input; snapshots and KSmallest results have independent
array storage. HeapSort deliberately mutates its supplied array. clear drops
the set root/size, whereas heap clear retains capacity and merely resets size.
Inactive primitive slots need not be zeroed. Neither action promises immediate
garbage collection.

Standalone node immutability does not prevent a caller from passing the same
child twice. Two root-to-node paths sharing an identity violate the proper-tree
contract even without a cycle. Repeated **values** in distinct nodes are valid.
Traversals/analysis do not add a visited set or promise DAG semantics. These
nodes are separate from the search containers' non-public mutable nodes.

## Ordered-set preservation and termination

The strict BST invariant is global: every left-subtree value is smaller and
every right-subtree value larger; checking only immediate children is insufficient.
Exactly size distinct nodes are reachable, with no repeated node identity.
Comparisons use relational int operators, so extremes need no subtraction or
query+1/query-1 arithmetic.

**Search and insertion.** At each step, if the key is present it remains in the
current subtree. A comparison rules out the other subtree, not necessarily half
the node population. Descending strictly reduces the remaining path. Equality
returns without insertion; null ends absence search or identifies the new leaf
position. BST insertion tracks only the current node; it is iterative.

**Keyed deletion.** Search first locates target and parent. With zero/one child,
reconnect its parent (or root) to the surviving child/null. With two children,
choose the minimum in the right subtree, copy its key into the target and
physically remove that successor. It has no left child; its possible right
child must be reattached. Every other right-subtree key exceeds the successor,
and all old left-subtree keys remain smaller. Hence ordering/uniqueness survive
and size decreases exactly once. Public node identity is not part of this API.

Concrete Java test trace: insert [40,20,80,60,90,50,70,55], then remove 40.
Successor 50 is copied into the target; 60.left becomes 55. Sorted output is
[20,50,55,60,70,80,90]. Immediate-right successor, one-child root, leaf and
singleton cases are covered separately. The textbook's node-transplant API
and parent fields are not copied wholesale into this private-node/key API.

**Floor/ceiling.** Floor retains the best visited key <= query. A valid candidate
allows searching right for an improvement; a key above the query excludes its
right subtree. Ceiling is the dual. At null, the stored candidate is the result
or absence. These inclusive queries also work when the query key is absent.

**AVL.** Each node caches edge-height; null=-1, leaf=0, and every node satisfies
abs(leftHeight-rightHeight)<=1. Recursive update first returns valid child
subtrees, then recomputes the parent height and repairs a skew beyond one.
For a right rotation, in-order partitions A < x < B < y < C keep that order
when x replaces y as subtree root; left rotation is symmetric. Left-right and
right-left are compositions. Update the lower node's height before the new
root's height because the latter depends on the former.

Insertion examples [3,2,1], [1,2,3], [3,1,2], [1,3,2] cover the four repair
cases. Deleting 5 from [4,2,5,1,3] needs a right rotation with a balanced heavy
child; a strict insertion-only sign test would miss this deletion case. Deletion
can require repair at several ancestors. Successor removal decrements size at
the physical zero/one-child case, not twice at the original two-child target.

Minimum AVL population at edge-height h obeys N(-1)=0, N(0)=1 and
N(h)=1+N(h-1)+N(h-2): the shortest valid child heights are h-1 and h-2.
This grows exponentially with h, so h=O(log(n+1)). It does not require equal
subtree populations or exact halving at each comparison. Updates visit a bounded
height path with constant local repair work per ancestor; deletion need not
have constant total rotations. No-op updates preserve valid cached heights.

## Traversal and metric invariants

All current traversals are iterative and return fresh mutable List<Integer>
results, including an empty list for null root. They leave the nodes unchanged.

| Method | State argument |
|---|---|
| preOrder | Pending stack is the remaining root-left-right work; pushing right before left makes left execute next |
| inOrder | Stack holds ancestors awaiting output after their left subtree; pop/output then descend right. Values are sorted only when the input satisfies BST order |
| postOrder | Stack is the active ancestor path; previous is the last completed subtree root. After the left subtree, visit an unfinished right child before emitting/popping its parent |
| levelOrder | Queue holds unprocessed nodes in level/left-to-right order; append children after existing queued nodes |

Each node enters/leaves its frontier a bounded number of times, giving linear
total work. The identity-based postorder completion test relies on proper-tree
ownership; it is not a promise of shared-subtree handling. A list of preorder
values alone does not uniquely encode arbitrary binary-tree shape; serialization
needs an additional shape convention such as null markers.

TreeAnalysis uses explicit frames with three phases: schedule left, schedule
right, then combine completed child summaries. Null children begin with
Summary(0,-1,0,true). Once both children complete:

```text
size = 1 + left.size + right.size
height = 1 + max(left.height,right.height)
diameter = max(left.diameter,right.diameter,left.height+right.height+2)
balanced = left.balanced && right.balanced && abs(left.height-right.height)<=1
```

A longest node-to-node path lies wholly in one child or crosses the current
node, which exhausts the diameter cases. Balance must hold at every node,
including inside a subtree whose root heights happen to match. Completed child
summaries establish the parent result by structural induction. Every frame
advances through finite phases and is popped once; only active root-to-node
frames and constant child summaries per frame remain live. No all-node summary
map or repeated subtree-height scan is used.

For root 4 with left 2(1,3) and right 6(null,7), results are preorder
[4,2,1,3,6,7], inorder [1,2,3,4,6,7], postorder [1,3,2,7,6,4], level order
[4,2,6,1,3,7] and Summary(6,2,4,true). Empty summary is (0,-1,0,true);
a leaf gives (1,0,0,true). Height and diameter count edges, not nodes.

## Heap preservation, build and applications

Live slots [0,n) form a complete binary tree, with parent (i-1)/2 for i>0 and
children 2i+1,2i+2. Parent<=child and live multiplicity define min-heap order;
siblings and unrelated subtrees need not be sorted. The sift-down loop checks
i<size/2 before child arithmetic, keeping the left child in range; it checks
right-child existence before reading it. Supported capacities avoid overflow.

Offer reserves capacity first, then moves larger ancestors down into the hole
and writes the offered value once. The only possible violation moves upward.
Poll saves the root, decreases live size, moves the final value to the root and
sifts downward by the smaller child. Child subheaps remain valid; choosing the
smaller child repairs order above the moving hole. The hole follows a finite
tree path. Duplicate values and Integer.MIN_VALUE/MAX_VALUE are valid.

The capacity helper accepts requested capacity 0..2^30, reserving at least one;
default is sixteen. Growth doubles/caps at 2^30 and throws IllegalStateException
at the maximum before the offer changes live size. Physical allocation may fail
earlier. There is no automatic shrink after poll/clear.

Bottom-up construction first copies the input, then sifts parents from n/2-1
down to zero. Initially all leaves are heaps; at each parent both child subtrees
are already heaps. Sifting establishes that parent's subtree invariant, and
decreasing parent ends with a complete heap. Height-weighted work sums as
O(n * sum(t/2^(t+1)))=O(n), and copying supplies Omega(n): Theta(n+1) including
empty input. It is not n independent worst-height sifts.

[HeapSort](../../src/main/java/org/anaalvarezdev/algorithms/trees/HeapSort.java)
uses the max-heap dual in the original array. A max-heap prefix contains the
remaining small values; its sorted suffix contains the largest values in final
order. Swapping the prefix maximum to its last slot and repairing the shorter
prefix preserves that partition. The prefix shrinks to one; multiplicity and
ascending order follow. Worst time is Theta(n log n) for growing n, extra space
O(1); it is unstable. Equal-value inputs can stop sifting early, so that worst
bound is not a per-input lower bound.

[KSmallest](../../src/main/java/org/anaalvarezdev/algorithms/trees/KSmallest.java)
requires 0<=k<=input.length. Null/invalid k rejects before modifying input.
k=0 returns immediately. Otherwise it copies/builds the **entire** min-heap and
extracts k minima. The result prefix is the sorted sequence of minima removed
so far, with multiplicities; k bounded iterations terminate. For k>0, build
Theta(n) plus O(k log(n+1)) extraction uses O(n) heap and Theta(k) result space.
This is not an O(k)-storage streaming top-k method, even when k=1.

## Operation-specific cost ledger

Let n be live population, h edge-height, w maximum width and c retained heap
capacity. Bounds count primitive/reference/array work, not JVM allocation/GC
latency. Empty cases cost constant work. O(h+2) and O(1+log(n+1)) below include
h=-1 and n=0 explicitly; familiar O(h+1)/O(log n) shorthand describes nonempty
asymptotic paths.

| Operation | Time | Working/output storage |
|---|---|---|
| BST keyed update/query/extrema/neighbours | O(h+2), worst O(n+1) | O(1), iterative |
| AVL keyed update/query/extrema/neighbours | O(1+log(n+1)) worst | Updates O(1+log(n+1)) call frames; reads O(1) |
| height | BST Theta(n+1); AVL Theta(1) cached | BST O(w+1) queue |
| Ordered-set snapshot | Theta(n+1) | Theta(n) result plus O(h+2) stack |
| Set size/isEmpty/clear | Theta(1) | Owner work; deferred GC separate |
| Full traversal | Theta(n+1) | Theta(n) result plus O(h+2) DFS or O(w+1) BFS frontier |
| TreeAnalysis | Theta(n+1) | O(h+2) explicit frames; constant final result |
| Heap constructor with requested capacity c | Theta(max(1,c)) | Retained primitive array |
| Heap peek/size/isEmpty/capacity/clear | Theta(1) | Clear retains c |
| Heap offer without growth / poll | O(1+log(n+1)) | O(1) local state |
| Growing heap offer | Theta(n) copying plus sift | Old/new arrays coexist |
| Heap from array / snapshot | Theta(n+1) | Theta(n+1) constructed heap / Theta(n) snapshot |

For m offers starting empty at c0, doubled capacities give O(c0+m) array
initialisation/copying. Add sift work separately; total insertion is logarithmic
amortised per offer in the usual nonempty bound, not constant and not worst-case
logarithmic when one call grows. A preloaded suffix must account for its starting
state. After draining, current n does not bound retained capacity c.

Diagnostic hasValidStructure checks count, global ordering/identity and, for
AVL, cached heights/local balance; heap diagnostics scan live child relations.
They are test helpers, not constant-cost production calls or timed JMH work.
No source-level reference/slot count establishes a portable byte footprint.

## Java execution and benchmark evidence

The [test directory](../../src/test/java/org/anaalvarezdev/algorithms/trees)
contains shared OrderedIntSet contracts inherited twice, BST successor/root
repair, four AVL insertion repairs and deletion cases, 5000-step TreeSet and
PriorityQueue models, 576 insertion/removal order pairs, 626 binary-tree shapes,
1093 small arrays, 7108 array/k selections, integer extremes and 100000-node
iterative traversal/analysis spines. Test totals and actual execution appear in
the review report; these finite checks do not prove every complexity statement.

[TreeOperationsBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/trees/TreeOperationsBenchmark.java)
has six hit/miss methods, three sizes and two insertion orders. It times lookup,
not tree construction, updates or rotations. SHUFFLED is one fixed-seed order;
SORTED gives a right-chain BST. Hits cycle preboxed keys with cursor work timed;
miss repeats n above the maximum. Over a complete sorted-BST hit cycle, visits
average (n+1)/2; upper miss visits n. This is a source path count, not a fitted
timing exponent or proof that all random BSTs have short height.

[HeapOperationsBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/trees/HeapOperationsBenchmark.java)
has six methods over three sizes and two distributions. Reserved capacity
excludes growth. Peek, repeated-minimum poll/restore pairs and new-heap builds
answer different questions. Pairs retain size/multiset, not necessarily physical
layout; JDK pairs reinsert the same boxed value. Builds include new container/
array allocation and copying or repeated offers from the trial input. Descending
distinct min-heap offers move every ancestor, totalling sum(floor(log2(i))) parent
moves for i=1..n. No copy baseline is subtracted and no allocation rate is inferred.

There are 72 default configurations. The separate [lookup record](../17-benchmarking/results/trees/README.md)
and [heap record](../17-benchmarking/results/trees/heap-first-run.md) retain 24
cells each, Oracle JDK 21.0.9, source identities c576cb7/ce621c6 and all errors.
The 48 recorded cells do not include size 10000. Primitive versus boxed APIs,
OptionalInt/null, query selection and JIT behaviour limit algorithm-only
rankings. Fresh Java build/discovery adds no timing or profiler evidence.
