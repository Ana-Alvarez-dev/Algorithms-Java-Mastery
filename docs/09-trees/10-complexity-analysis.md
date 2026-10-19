# Tree Complexity Analysis

## Algorithms Java Mastery

The [implementation guide](implementation-and-benchmark-guide.md) defines the
selected executable contracts, proof arguments, tests and benchmark protocol.
A bound belongs to an operation and input model, not simply to the word "tree".

## Height, Population and Width

Let n be live nodes, h edge-height (-1 for empty), w maximum width and c retained
heap capacity. Comparisons of primitive int keys take constant time. Allocation,
output storage and deferred garbage collection must be distinguished.

A BST search follows one path: O(h+1) time, O(1) extra storage for the iterative
implementation. Sorted distinct insertion into the unbalanced BST gives h=n-1,
so a miss above the maximum traverses all n nodes. A shuffled order does not
guarantee logarithmic height for every possible permutation. Expected random-BST
claims need a specified probability model, not merely a fixed benchmark seed.

AVL balance bounds the heights of a node's subtrees to differ by at most one.
Minimum population obeys N(h)=1+N(h-1)+N(h-2), with N(-1)=0 and N(0)=1. Its
exponential growth in h yields logarithmic height. Searches and updates take
O(log(n+1)) worst time; cached heights make height retrieval constant. Recursion
in the AVL update is bounded by this height; deletion can repair many ancestors.
Red-black/B-tree variants are conceptual extensions, not additional implementations.

## Search Trees: Operation-Specific Costs

| Selected operation | Unbalanced BST | AVL | Auxiliary/output storage |
|---|---|---|---|
| contains/add/remove | O(h+1), worst O(n) | O(log(n+1)) worst | BST O(1); AVL recursive updates O(log(n+1)) |
| first/last/floor/ceiling | O(h+1) | O(log(n+1)) | O(1) working storage |
| height | Theta(n) scan | Theta(1) cached | BST O(w) queue |
| sorted snapshot | Theta(n) | Theta(n) | Theta(n) result plus O(h+1) stack |
| size/isEmpty/clear | Theta(1) | Theta(1) | Clear drops root; future GC is separate |

The selected API locates removal by **key**. Constant-time unlinking given a
known node or an already known parent is a different contract. Inclusive floor
and ceiling are not strict successor/predecessor. Retained node storage is
Theta(n). A general binary tree has no universal insertion/deletion cost without
a position, search, ownership and subtree-removal specification.

## Complete Traversal and Metrics

Every complete traversal must output every node, even if the tree is balanced.
Its time and output size are Theta(n); balancing does not reduce this to O(log n).
The explicit DFS frontier for preorder/inorder/postorder uses O(h+1) working
space. Level-order uses O(w) queue space. List output is an additional Theta(n),
not hidden inside those frontier bounds.

`TreeAnalysis` computes size, height, edge-diameter and local-height balance in
Theta(n) time with O(h+1) explicit frames and a constant-size final summary.
It does not repeatedly rescan subtree heights or store a summary map for all
nodes. Proper-tree inputs are required; shared-subtree DAG work is not described
by the same distinct-node count. Explicit frames avoid call-stack overflow but
still consume memory proportional to the active path.

## Heap Sifting, Construction and Resizing

A complete binary heap has logarithmic height. Heap order constrains parent and
child, not arbitrary key order. Peek is constant; arbitrary membership search
remains O(n) in the worst case. Selected `IntMinHeap` exposes no membership or
remove(Object) API.

| Heap operation | Time | Qualification |
|---|---|---|
| peek/size/isEmpty/capacity | Theta(1) | Empty result uses OptionalInt |
| offer without growth | O(log(n+1)) | Sift up one path |
| offer with growth | Theta(n) copy plus sift | One operation can be linear |
| poll root | O(log(n+1)) | No shrink; sift down one path |
| build from array | Theta(n) | Copy plus bottom-up sifting |
| repeated-offer build | O(n log(n+1)) worst | Descending distinct input makes min-heap insertion adverse |
| toArray | Theta(n) | Independent heap-order output, not sorted |
| clear | Theta(1) | Capacity retained, live size reset |

Geometric growth makes total copying linear across a growing sequence, adding
O(1) amortised copying per offer. Combined with sifting, the amortised upper
bound is O(log(n+1)), while one growing call can be linear. Retained storage is
Theta(c); old/new arrays coexist during resize. No byte-level footprint claim
follows from the asymptotic bound.

Bottom-up heap building is not n independent worst-height sifts. At height t,
there are at most order n/2^(t+1) nodes; summing their O(t) sift work gives O(n).
The required copy gives Omega(n), hence Theta(n). "Heapify" must distinguish
one subtree sift, O(log n) worst, from complete bottom-up construction.

## Selected Applications and JDK References

`HeapSort` builds an internal max-heap and grows a sorted suffix. It has
O(n log(n+1)) time, Theta(n log n) worst-case time and O(1) auxiliary storage.
It is in-place and unstable. Inputs with many equal values may cause early sift
termination; the worst bound is not an assertion of the same work on every input.

`KSmallest`, for k>0, spends Theta(n) building a copied min-heap and at most
O(k log(n+1)) on extraction, with O(n) heap plus Theta(k) output storage. k=0
returns immediately. A bounded O(k)-space streaming selection is a different
algorithm and is not claimed here.

Java 21 TreeSet documents logarithmic add/remove/contains. PriorityQueue
specifies constant peek/size and logarithmic enqueue/dequeue, while contains and
remove(Object) are linear. Removing the root with poll and removing an arbitrary
matching object are not interchangeable. The consulted OpenJDK 21 source grows
its backing array by copying; account for that individual growth event when
analysing actual allocation work. PriorityQueue iteration is not sorted, and
null is forbidden. Custom comparators and boxed values add their own costs.

## Experimental Interpretation

The guide's JMH fixtures distinguish insertion order for lookup and descending
versus seeded random heap input. Trials reserve heap capacity, so peek/pairs
exclude growth. Lookup excludes construction/updates. Repeated-minimum restore
pairs and build methods answer different questions. A fixed seed is reproducible
input, not a distributional guarantee or an asymptotic proof.

Results remain pending author measurements. Record actual source/runtime,
settings, all means/errors and the exact fixture. Smoke execution verifies the
harness only; passing tests, mathematical arguments and timings remain separate
evidence. See the guide for the full verification and evidence chain.
