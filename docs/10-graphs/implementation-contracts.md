# Graphs: Java Contracts and Proof Map

Reviewed on **2026-10-28** against main
`809deace4c37ba91bf45621265446644dd3392bf` (PR #107 merge).
The [review report](documentation-review.md) records corrections and actual Java
execution; the [implementation guide](implementation-and-benchmark-guide.md)
retains pseudocode, traces and historical experiment provenance.

## Representations, validation and ownership

| Current source | Contract |
|---|---|
| [IntGraph](../../src/main/java/org/anaalvarezdev/algorithms/graphs/IntGraph.java) | Sealed immutable unweighted base; fixed int vertices 0..V-1; directedness, long logical edgeCount, validated hasEdge and independent ascending neighbours arrays |
| [AdjacencyListGraph](../../src/main/java/org/anaalvarezdev/algorithms/graphs/AdjacencyListGraph.java) | Sorted primitive rows, binary-search membership and package-private row iterators |
| [AdjacencyMatrixGraph](../../src/main/java/org/anaalvarezdev/algorithms/graphs/AdjacencyMatrixGraph.java) | Boolean presence matrix; constant membership access, row-scanning internal iterators |
| [GraphInputs](../../src/main/java/org/anaalvarezdev/algorithms/graphs/GraphInputs.java) | Count/endpoint validation, ordered-set normalisation and private parent-path reconstruction |
| [WeightedGraph](../../src/main/java/org/anaalvarezdev/algorithms/graphs/WeightedGraph.java) | Immutable Edge(int,int,int) records and destination-sorted outgoing lists; canonical undirected edges use from<=to |

Negative V throws IllegalArgumentException; null input/edge rows or records throw
NullPointerException; invalid vertex IDs throw IndexOutOfBoundsException.
Unweighted rows must have exactly two endpoints. Construction fixes all vertices,
including isolates; there is no later insertion/removal or implicit vertex creation.
Input arrays/list membership are not retained as mutable graph state.

Unweighted repeated endpoint pairs collapse, including reversed undirected pairs.
Weighted repeated pairs instead throw IllegalArgumentException, even when weights
agree. Directed reverse pairs are distinct. All representations allow loops.
WeightedGraph accepts any int weight, including zero/negative; algorithm
requirements are separate. Zero is a present record, not an absence marker.

Let E count logical edges, L loops and A stored adjacency entries. Directed A=E;
undirected A=2E-L. A loop appears once in a row and once in logical edgeCount,
while mathematical undirected degree counts it twice. Thus neighbours(v).length
is not the loop-aware undirected degree. No degree or multigraph API is provided.

Public neighbours scans its iterator twice, first to count and then to fill a
fresh array. Algorithms use internal iterators without those row snapshots.
Weighted outgoing/edges lists are unmodifiable and contain immutable scalar
records, so they cannot expose mutable edge fields. Hiding a mutable collection
behind a List reference alone would not establish this ownership contract.

## Algorithm results and boundary cases

| API / source | Accepted input and result |
|---|---|
| [BreadthFirstSearch.search](../../src/main/java/org/anaalvarezdev/algorithms/graphs/BreadthFirstSearch.java) | Valid source in either unweighted representation; outgoing reachability only. distanceTo is edge count or -1; order/pathTo give independent arrays. Source path is [source], unreachable path empty |
| [DepthFirstSearch.traverse](../../src/main/java/org/anaalvarezdev/algorithms/graphs/DepthFirstSearch.java) | Valid source; fresh ascending-neighbour recursive-preorder array for source reachability, using explicit frames |
| [ConnectedComponents.find](../../src/main/java/org/anaalvarezdev/algorithms/graphs/ConnectedComponents.java) | Undirected only; includes isolates/loops. Cached count, validated componentOf, copied labels; labels follow ascending first roots |
| [TopologicalSort.sort](../../src/main/java/org/anaalvarezdev/algorithms/graphs/TopologicalSort.java) | Directed only; Optional containing complete independent order for a DAG; empty Optional for any cycle. Empty DAG gives present empty array |
| [Dijkstra.shortestPaths](../../src/main/java/org/anaalvarezdev/algorithms/graphs/Dijkstra.java) | Valid source, globally nonnegative weights; OptionalLong distances and independent paths; source distance 0, unreachable distance empty |
| [Kruskal.minimumSpanningForest](../../src/main/java/org/anaalvarezdev/algorithms/graphs/Kruskal.java) | Undirected only; negative weights valid, loops ignored. Immutable selected edges, long totalWeight and component count; disconnected input returns a minimum forest |
| [DisjointSet](../../src/main/java/org/anaalvarezdev/algorithms/graphs/DisjointSet.java) | Package-private singleton construction, validated find, union by size/path halving and cached component count |

All algorithm entry points reject null graphs. An empty graph has no valid BFS,
DFS or Dijkstra source. Empty undirected components/forest are valid with count
zero; undirected topological input is rejected even when empty. Result vertex
queries validate IDs. Component labels are an undirected partition, not SCCs.
DFS exposes neither finish times nor a cycle witness. Prim, Bellman-Ford,
Floyd-Warshall and SCC remain conceptual extensions; test oracles do not count
as their production implementations.

## Correctness and termination

**BFS.** Initially only source has finite distance and is queued. Mark before
enqueue permits one enqueue per vertex. Queue order is nondecreasing edge
distance; first discovery from u sets distance[u]+1. Any shorter path would
have exposed that vertex from an earlier layer, contradicting unseen state.
Parents are real edges with strictly decreasing distances backwards, so path
reconstruction reaches source. Every reachable vertex is eventually discovered;
loops/cycles add no second discovery. Directed reachability is not necessarily
an SCC or an undirected component.

**DFS.** Each frame retains one active vertex's unfinished neighbour suffix.
Discovering an unseen child suspends that frame; exhausting an iterator pops
it, matching recursive return. Seen marks prevent repeated discovery. Every
iterator advances finitely and each vertex creates one frame. The illustrative
push-all/mark-on-pop variant can keep duplicate pending vertices and O(E) stack
entries; it does not justify the selected O(V) active-frame bound. A seen flag
alone cannot distinguish active directed ancestors from completed vertices.

For 0->1,0->2,1->2,1->3,2->4, BFS order is [0,1,2,3,4] and its path to 3 is
[0,1,3]; DFS order is [0,1,2,4,3]. An isolate 5 is absent from both source orders.
Ascending rows establish reproducibility, not a universal neighbour-independent
order or full-graph traversal guarantee.

**Components.** Start FIFO reachability from each smallest unlabelled vertex.
All vertices reached share an undirected path to that root. If a later root
shared a path with an earlier component, it would already be labelled. Thus
each vertex, including isolates, receives exactly one label. Finite outer and
adjacency loops terminate. Directed reachability is not this equivalence relation.

**Kahn.** Local indegree counts incoming edges from vertices not yet output.
Zero means all predecessors are satisfied. Outputting a vertex and decrementing
its outgoing contributions preserves this invariant and gives forward edges
in a complete order. A finite nonempty DAG has a zero-indegree vertex; an empty
ready queue with vertices remaining therefore implies a cycle. A cycle cannot
release all its own dependencies. Comparing output count with V detects cycles
in disconnected components and self-loops, without modifying the graph.

FIFO roots/neighbours are deterministic, but not guaranteed lexicographically
minimal. For 0->2,1->2,2->3 plus isolate 4, [0,1,4,2,3] is valid while
[0,1,2,3,4] is smaller. A smallest-ready priority policy would change the contract.

**Lazy Dijkstra.** Validate source and scan all canonical edges for negatives.
Begin with (0,source); strict improvement creates a new immutable queue entry.
Pop by distance then vertex ID, skipping a distance different from the current
array value. Old entries do not change in place: this is not indexed decrease-key.

Finite tentative distances describe discovered paths. Nonnegative weights make
a minimum nonstale extraction final: a shorter unsettled route would cross
from a settled prefix that had already relaxed a no-larger candidate. Each such
settled vertex's outgoing row is scanned once. Only strict improvements change
parents; a chosen parent settles before its child. Following parents therefore
goes backward in settlement order even across zero-weight edges, precluding a
parent cycle. Deterministic ties do not promise lexicographically smallest paths.

For 0->1(10),0->2(1),2->1(1),1->3(2),2->3(20), distance to 1 improves 10->2
and leaves a stale entry; distance to 3 is 4 via [0,2,1,3]. An unreachable
negative edge still causes rejection. A shortest simple path uses at most V-1
nonnegative int weights; one relaxed candidate is at most V*Integer.MAX_VALUE,
below Long.MAX_VALUE for int V. Infinite markers are never queued/relaxed.

**Kruskal.** Copy/sort canonical edges by weight,from,to and create V singleton
sets. Accepted edges join different components, preserving a forest. For a light
edge crossing a current component's cut, an optimum completion lacking it has
another crossing edge on the connecting path. The cut respects prior selection;
exchange that edge for the light edge without increasing cost. Selection thus
remains extendable to an optimum within each original component, including ties.

Union rejects loops/cycle-closing edges. After all edges, each original component
is spanned, while different original components stay separate. With returned
count C, selected size is V-C because each successful union lowers count once.
Empty returns edges=[], total=0, C=0. At most max(0,V-1) signed int weights are
selected, so their long sum is safe. Tie order selects one deterministic optimum,
not a proof of uniqueness.

The Java loop scans **every** canonical edge, without a V-1 early stop. Union by
size attaches the smaller root beneath the larger; each depth increase at least
doubles component size. Find depth is O(log(V+1)); path halving only shortens it.
This direct bound suffices without importing a stronger inverse-Ackermann
amortised theorem as a constant per-call guarantee.

## Cost ledger

M counts input records, E logical edges, A stored entries, R reached vertices
and A_R their outgoing entries. Bounds count arrays/scans/comparisons and local
output, not input graph storage or portable JVM bytes. Public snapshots differ
from internal iterators; +1 accounts for constant empty handling.

| Current work | Time | Storage / qualification |
|---|---|---|
| Unweighted list construction | O(V+1+M log(V+1)) | O(V+E+1), temporary ordered sets |
| Matrix construction | O(V²+1+M log(V+1)) | O(V²+1) retained plus temporary normalised rows |
| List hasEdge / neighbours(v), d=row length | O(1+log(d+1)) / Theta(d+1) | Fresh d-element output |
| Matrix hasEdge / neighbours(v) | O(1) / Theta(V+1) | Fresh output; full row scans |
| List BFS/DFS | O(V+A_R+1) | O(V+1) state/output/frontiers |
| Matrix BFS/DFS | O(V+R*V+1) | O(V+1) |
| List components/Kahn | O(V+A+1) | O(V+1) |
| Matrix components/Kahn | O(V²+1) | O(V+1) |
| Weighted construction, valid unique pairs | O(V+1+E log(V+1)) | O(V+E+1) |
| Lazy Dijkstra | O(V+E+1+E log(E+1)) | O(V+E+1), including stale entries |
| Kruskal | O(V+1+E log(E+1)+E log(V+1)), conservative | O(V+E+1), sort copy/sets/result |
| BFS/Dijkstra path query | O(path length+1) | Fresh array; two backwards walks when reachable |

Array initialisation contributes V even for a small reached set; Dijkstra also
validates all E edges. Each scanned outgoing edge can produce at most one strict
improvement, giving O(A+1)=O(E+1) possible lazy entries rather than an indexed
O(V) queue. E is at most quadratic in V here, allowing conventional logarithmic
simplifications, but isolates prevent dropping V from a general forest bound.
Kruskal with E=0 still constructs V singleton sets.

## Java tests and empirical boundary

The [test directory](../../src/test/java/org/anaalvarezdev/algorithms/graphs)
contains inherited representation contracts, independent reachability/cycle
oracles, deterministic result cases, zero/stale/negative/long boundaries and
exhaustive forest subsets. Actual execution totals are in the review report;
finite cases are not universal correctness/complexity proofs.

[GraphTraversalBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/graphs/GraphTraversalBenchmark.java)
has four methods, two sizes and PATH/SPARSE/DENSE: 24 cells.
[WeightedGraphBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/graphs/WeightedGraphBenchmark.java)
has two methods, two sizes and SPARSE/DENSE: eight cells.
[GraphBenchmarkData](../../src/jmh/java/org/anaalvarezdev/algorithms/graphs/GraphBenchmarkData.java)
uses a path backbone plus seeded additions/all unordered nonloop pairs. SPARSE
has up to 3V attempts, not exactly 3V distinct edges. Fixtures are connected and
undirected; weighted edges are positive and source is zero.

Construction is excluded; fresh algorithm state/output is timed. BFS returns
distance/parent/order while DFS returns order only, so their difference does not
isolate queue versus stack. Dijkstra and Kruskal solve different objectives.
Dense E=V(V-1)/2 gives 4950/499500 canonical edges at 100/1000; Kruskal makes
that many union attempts and accepts 99/999. These source counts are not profiler
cost fractions. Dense list traversal is quadratic because E is quadratic too.

Separate [traversal](../17-benchmarking/results/graphs/README.md) and
[weighted](../17-benchmarking/results/graphs/weighted-first-run.md) reports retain
32 default cells, author source 00fc9de and Oracle JDK 21.0.9. Units are **us/op**,
not Trees' ns/op. Fixture coverage does not cover every graph or query distribution.
Fresh build/discovery supplies no timing, significance, allocation-rate,
heap-footprint or cache-cause evidence.
