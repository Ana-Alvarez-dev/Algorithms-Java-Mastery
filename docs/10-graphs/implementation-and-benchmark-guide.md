# Graphs: Implementation and Benchmark Guide

## Selected Scope and Traceability

Package `org.anaalvarezdev.algorithms.graphs` mirrors main, test and JMH roots.
The contribution starts from main **`aca933f9c15db0e977f64a6ea48ce5bb9b537b90`**,
which integrated Trees' separate lookup/heap evidence through PR #79. Earlier
production/test packages and the POM are unchanged. This is a focused review
of selected contracts and evidence, not a formal review of every conceptual
example or future graph algorithm.

| Documents | Executable responsibility | Verification |
|---|---|---|
| 01 fundamentals / 02 terminology / 03 representations | Immutable `IntGraph`, `AdjacencyListGraph`, `AdjacencyMatrixGraph`, validation helper | Eight shared tests per representation, direction/loops/duplicates/ownership |
| 04 BFS | `BreadthFirstSearch` and private-state Result | Distances, paths, deterministic order, exhaustive small graphs |
| 05 DFS | `DepthFirstSearch`, `ConnectedComponents` | Recursive-order oracle, deep path, undirected reachability partitions |
| 06 topological sorting | `TopologicalSort`, FIFO Kahn algorithm | Empty DAG versus cycle, isolated vertices, exhaustive cycle/order oracle |
| 07 shortest paths | `WeightedGraph`, lazy-queue `Dijkstra` | Negative-weight rejection, long distances, independent relaxation oracle |
| 08 spanning trees | `Kruskal`, internal `DisjointSet` | Minimum spanning forest, ties/loops/disconnection, exhaustive subset oracle |
| 09 complexity / 10 applications / 11 mistakes / 12 interview notes | Contract-specific ledger and limits | Mathematical arguments kept distinct from tests and timing |

There are **12 production Java files**, **48 executed module tests**, and
**6 JMH methods in two benchmark classes**. Bellman-Ford, Floyd-Warshall, Prim,
strongly connected components, undirected cycle witnesses, DFS timestamps/edge
classification, generic vertex labels, mutable graphs, multigraphs, graph
serialization and domain services remain extensions. Small independent test
oracles do not count as production implementations of those algorithms.

## Contracts and Representation Decisions

### Unweighted graphs

`IntGraph` is a sealed immutable base with two final representations. Construction
fixes vertex IDs **0..V-1**, including isolated vertices. V must be nonnegative;
null edge arrays/rows are rejected, each row must have exactly two endpoints,
and invalid endpoints raise IndexOutOfBoundsException. Empty graphs are valid.
Memory allocation can fail for inputs too large for the machine.

Edges may be directed or undirected. Self-loops are allowed. Repeated unweighted
input pairs collapse; in an undirected graph, reverse duplicates collapse too.
`edgeCount` is long and counts each logical edge once, including each loop once.
Undirected nonloop edges have two adjacency entries. There is no insertion,
removal or automatic vertex creation API after construction.

Adjacency-list rows are sorted primitive arrays. The matrix stores booleans;
false means absent. `hasEdge` validates both endpoints, then uses binary search
in a list row or constant matrix access. `neighbours(v)` returns an independent
ascending int array, including a fresh empty array. Input arrays are not retained.
Package-private iterators let algorithms scan neighbours without allocating a
public snapshot per frame. They expose no removal and no internal array access.
The sealed base prevents unrelated external implementations from weakening
these selected immutability/order contracts.

### Weighted graphs

`WeightedGraph` is immutable and list-based. `Edge(from,to,weight)` uses int
endpoints/weights, including zero and negative weights. Zero is a present edge,
not an absence marker. Null input/list entries and invalid IDs are rejected.
Repeated endpoint pairs are **rejected**, even if weights match; reversed input
pairs are duplicates in an undirected graph. This explicit policy differs from
the unweighted constructor's deduplication.

Self-loops are allowed. Outgoing rows sort by destination and are immutable lists
of immutable edge records. Canonical undirected `edges()` uses from <= to,
including a loop once; outgoing nonloop rows include both orientations. The
constructor copies input relationships. Neither the input list nor a returned
list can mutate the graph. Directed mode preserves orientation.

### Search, components and ordering

BFS and DFS require a valid source, so a source search on an empty graph throws.
They follow outgoing edges only and visit **only vertices reachable from source**.
Ascending neighbour order makes results deterministic. BFS marks before enqueue,
returns edge-count distance (-1 unreachable), independent visit-order snapshots,
and independent paths. Source path is `[source]`; unreachable path is empty.
Invalid result-query IDs are rejected. DFS uses explicit iterator frames to
match ascending recursive preorder without recursion, returning an independent
int array. It is not a shortest-path or full-forest API.

`ConnectedComponents.find` accepts only undirected graphs. It includes isolated
vertices and loops; component labels follow ascending first roots. Count and
labels represent a partition, not strongly connected components. The result owns
its labels and returns copies. Empty undirected graph has zero components.

`TopologicalSort.sort` accepts only directed graphs. Edge u->v means u precedes
v. Kahn's algorithm returns a present Optional with a fresh complete order for a
DAG, including a **present empty array for an empty DAG**. Empty Optional means a
cycle anywhere in the graph, including an isolated self-loop/component. The FIFO
queue starts with ascending zero-indegree IDs and processes ascending neighbours.
This is deterministic but not promised to be the lexicographically smallest
valid ordering. Input indegrees are local state and no graph is modified.

### Weighted results

Dijkstra requires a valid source and rejects **every negative edge**, even if
unreachable. Distances use long and OptionalLong.empty for absence. Source
distance is zero; paths are independent arrays with the same empty/source
conventions as BFS. Queue entries compare distance then vertex ID; only strict
improvements change parents. Equal-distance paths are deterministic for this
implementation, with no lexicographically smallest path guarantee. Zero cycles
are supported and do not produce parent cycles.

With nonnegative int weights, a shortest simple path uses at most V-1 edges.
A relaxed candidate adds one more int weight, so V*Integer.MAX_VALUE fits in long
for every supported int vertex count. Long.MAX_VALUE remains an unambiguous
unreachable marker. No overflowing int addition/subtraction is used for cost or
priority comparisons.

`Kruskal.minimumSpanningForest` accepts only undirected graphs, allows negative
weights, ignores loops and includes every vertex. A disconnected input returns
a minimum spanning **forest**, with component count and V-components selected
edges. Empty input gives zero components/edges/weight. Sorting by weight, from,
then to gives deterministic tie choices, not uniqueness of mathematical MSTs.
The result's edge list is immutable; total weight uses long. At most V-1 int
weights are selected, so the signed sum fits in long. MST cost and shortest-path
cost solve different problems and are never substituted for one another.

## Strategies, Pseudocode, Traces and Correctness

### BFS and DFS

```text
BFS: distance[source]=0; enqueue source
  dequeue u; output u
  for ascending outgoing neighbour v:
    if unseen: mark distance[v]=distance[u]+1; set parent[v]=u; enqueue v
DFS: mark/output source; push its neighbour iterator
  if top iterator exhausted: pop
  else read next v; if unseen: mark/output v; push v's iterator
```

BFS queue vertices are processed in nondecreasing edge distance. When v is first
enqueued from u, any shorter path would have exposed v from an earlier layer,
contradicting unseen status. Marking at discovery ensures one enqueue per vertex;
parents provide real edges and strictly decreasing BFS distances when followed
backwards. DFS frames preserve the unfinished neighbour suffix of each active
vertex. Each new frame represents one discovery; completing its iterator matches
returning from recursive DFS. Both terminate because each vertex is discovered
once and each relevant adjacency entry is consumed once (or each matrix row is
scanned once). Cycles and loops do not create new discoveries.

For edges `0->1,0->2,1->2,1->3,2->4`, BFS visits `[0,1,2,3,4]` and gives path
`[0,1,3]` to 3. DFS visits `[0,1,2,4,3]`. Merely pushing all neighbours and marking
them immediately can produce a different DFS order; iterator frames implement
the selected recursive-order contract. In a graph with another isolated vertex
5, source search does not pretend to visit it.

Components repeat FIFO reachability from the smallest unlabelled vertex. All
vertices labelled in one search have paths to that root; in an undirected graph
reachability is an equivalence relation. A later root cannot share an edge/path
with an earlier component, or it would already have been labelled. Every vertex
gets exactly one label and a finite number of scans.

### Kahn topological order and cycle detection

```text
compute local indegrees; enqueue all zero-indegree IDs ascending
while queue nonempty:
  dequeue/output u; decrement outgoing neighbours' indegrees
  enqueue a neighbour exactly when its indegree becomes zero
return complete order if output count equals V; otherwise report cycle
```

Each current indegree counts edges from vertices not yet output. A zero-indegree
vertex has no unmet predecessor. Removing its outgoing contributions preserves
the invariant, and every output edge points forward. Every finite nonempty DAG
has a zero-indegree vertex, so an incomplete result implies a cycle in the
unprocessed subgraph. A cycle cannot release its last incoming dependencies.
The queue emits each vertex at most once, establishing termination.

For `0->2,1->2,2->3` plus isolated 4, initial queue is `[0,1,4]`; result is
`[0,1,4,2,3]`. It is valid even though `[0,1,2,3,4]` is another valid order.
Adding `3->2` leaves a cycle and returns empty Optional, with no partial order
mislabelled as a valid topological result.

### Dijkstra with lazy entries

```text
reject negative edges; set source distance zero and others infinity
push (0,source)
while queue nonempty:
  pop smallest (distance,u); skip if stale against current distance[u]
  for each outgoing edge u->v:
    if distance[u]+weight improves v: update distance/parent; push new entry
```

Nonnegative edges ensure a minimum nonstale extracted distance cannot later be
improved through an unprocessed longer route. Relaxation preserves valid path
upper bounds; eventual processing settles every reachable distance. Old queue
entries remain and must be skipped; Java PriorityQueue is not an indexed heap
with decrease-key. Strict improvements and deterministic extraction prevent
zero-cycle parent updates. Finite edges/relaxations bound queue insertions; path
reconstruction terminates at source. No negative-edge exception is hidden by
reachability filtering.

For `0->1(10),0->2(1),2->1(1),1->3(2),2->3(20)`, distance to 1 improves 10->2,
leaving a stale entry. Distance to 3 improves to 4; path is `[0,2,1,3]`. A separate
edge of weight -1 in an unreachable component still violates this API's global
precondition. A two-edge path with both weights Integer.MAX_VALUE has distance
4,294,967,294, which is supported rather than wrapping an int.

### Kruskal forest and disjoint sets

```text
sort a copy of canonical edges by (weight,from,to)
create singleton sets for all V vertices
for each edge u-v:
  if union finds different roots: select edge; add its weight
return selected immutable list, total and remaining set count
```

The selected edges form a forest; union accepts exactly edges between different
components, so adding one cannot form a cycle. At the moment an edge is accepted,
it is a light edge across an appropriate cut of the current forest. The cut
exchange argument preserves extendability to a minimum spanning forest. Rejected
edges connect an already connected pair, including loops. Processing all edges
spans each original component. Each successful union decreases component count
by one, yielding the V-components edge count and termination.

DisjointSet uses union by component size and iterative path halving. Attaching
only the smaller root limits depth to O(log(V+1)): each depth increase requires
at least doubling the component size. Halving only shortens paths. The ledger
below uses that direct worst-case height bound; stronger amortised disjoint-set
analysis is not needed to justify the selected implementation's stated bound.

For vertices 0..4, edges `0-1(-4),1-2(2),0-2(8),3-3(1)` select the first two,
ignore the loop/cycle edge and return total -2 with three components, including
isolated 4. Negative MST edges are valid even though Dijkstra rejects them.

## Complexity Ledger

Let M be input edge records (including duplicates), E distinct logical edges,
A stored adjacency entries (E for directed, at most 2E for undirected), and R the
source-reachable vertices. Bounds include local state/output, not input storage.

| Work | Time | Storage / qualification |
|---|---|---|
| List construction | O(V + M log(V+1)) | O(V+E) retained; temporary ordered sets |
| Matrix construction | O(V² + M log(V+1)) | Theta(V²) retained plus temporary normalised lists |
| List hasEdge / neighbours(v) | O(log(degree(v)+1)) / Theta(degree(v)+1) | Neighbour output is an independent array |
| Matrix hasEdge / neighbours(v) | O(1) / O(V) | Boolean matrix; no weighted absence sentinel |
| Source BFS/DFS on list | O(V + reachable adjacency entries) | O(V) working/output; iterators do not copy rows |
| Source BFS/DFS on matrix | O(V + R*V) | O(V) working/output; full connected scan is O(V²) |
| Components / Kahn on list | O(V+E) | O(V) additional, including local labels/indegrees |
| Components / Kahn on matrix | O(V²) | O(V) additional |
| Weighted graph construction | O(V + E log(V+1)) for valid unique pairs | O(V+E) retained/copy state |
| Lazy Dijkstra | O(V+E+E log(E+1)) | O(V+E) queue/state, including stale entries |
| Kruskal | O(V + E log(E+1) + E log(V+1)) conservative worst bound | O(V+E) sort copy, disjoint sets and result |
| BFS/Dijkstra path query | O(path length) | Independent path array; empty unreachable path |

For selected simple graphs, E is at most quadratic in V, permitting familiar
log(V+1) forms for lazy queue/sort terms. Isolated vertices still require O(V)
initialisation; omitting V is invalid for sparse/disconnected forests. A general
indexed-heap Dijkstra's O(V) queue storage cannot be assigned to the lazy queue.
Boolean matrices here are not bit-packed and no exact byte footprint is claimed.
List storage has ordering/constructor costs distinct from an append-only mutable
textbook list. Public defensive snapshots and algorithm internal iterators have
different allocation work.

## Automated Verification

On **2026-10-20 UTC**, verification-host **OpenJDK 21+35-2513** passed normal
Maven tests and benchmark-profile clean package: **572 tests**, zero failures,
errors or skips, compared with the previously verified 524-test Trees base.

| Concrete class | Executed tests |
|---|---:|
| AdjacencyListGraphTest / AdjacencyMatrixGraphTest | 8 each, inherited shared contract |
| BreadthFirstSearchTest | 5 |
| DepthFirstSearchTest | 4 |
| ConnectedComponentsTest | 3 |
| TopologicalSortTest | 4 |
| WeightedGraphTest | 4 |
| DijkstraTest | 5 |
| DisjointSetTest | 3 |
| KruskalTest | 4 |

Representation contracts include 100 seeded input sets per directedness mode and
implementation (400 cases), loops/duplicates, sorted independent snapshots,
invalid IDs/rows and iterator termination. BFS checks all **512 three-vertex
directed graphs with loops** at every source in both representations, using an
independent all-pairs distance oracle. DFS uses a small recursive order oracle;
Kahn is checked against transitive-closure cycle detection and edge precedence.
All **64 four-vertex simple undirected graphs** check BFS/component partitions.
A **100,000-vertex path** checks iterative DFS and BFS without call-stack recursion.

Dijkstra is checked on **400 seeded six-vertex graphs**, both directedness modes,
all sources (2,400 source cases), against synchronous repeated relaxation. Paths
are checked for valid edges, cost, endpoints and uniqueness. Zero cycles, stale
entries, unreachable negative edges and long-distance boundaries are explicit.
Kruskal checks all **64 four-vertex graphs x 3 weight assignments = 192 cases**
against exhaustive edge-subset connectivity/weight enumeration, plus loops,
negative weights, disconnected/empty cases, ties and long totals. DisjointSet
runs 1,000 seeded unions against naive component labels.

The benchmark jar discovers **six methods**. All **16 short smoke cells** at
size 100 complete across the respective fixture shapes. A separate probe checks
all **32 default method/size/shape combinations**, three calls each, representation
agreement, complete traversal/path constraints, and independent scan-based
shortest-path/Prim references for weighted fixtures. JavaDoc passes
`-Xdoclint:all,-missing`. These are functional/harness checks, not author research
measurements or a claim that tests prove correctness over every possible graph.

## JMH Design and Windows Evidence Protocol

`GraphTraversalBenchmark` has list/matrix BFS/DFS, sizes **100/1,000**, shapes
**PATH/SPARSE/DENSE**: **24 configurations**. All fixtures are undirected and
connected by a path backbone. SPARSE adds up to 3V seeded pair attempts, discarding
loops/duplicates; DENSE includes every unordered nonloop pair. Generator seed is
**20261020**. Both representations receive identical canonical edges.
Construction/normalisation is excluded; each timed call allocates fresh algorithm
state/frontiers/output. BFS includes path/distance state while DFS returns only
visit order: elapsed difference is not an isolated queue-versus-stack cost.
Both reach all V vertices; disconnected-case performance is not measured here.

`WeightedGraphBenchmark` has Dijkstra and Kruskal, sizes **100/1,000**, shapes
**SPARSE/DENSE**: **8 configurations**. The same generator produces an undirected
connected graph with weights 1..1,000. Trial graph construction is excluded.
Dijkstra includes global validation, arrays, lazy queue and output; Kruskal includes
copied sorting, sets and forest creation. They solve different problems and are
not ranked as substitutes. No update/growth, negative-edge run or allocation/cache
profiler is included. RANDOM seed reproducibility is not broad workload sampling.

Both classes use **average microseconds/op (`us/op`)**, one thread, three
500 ms warmups, five 500 ms measurements, two forks. A controlled cell has
`Cnt=10` iteration averages, not ten individual invocations. There are **32 default
configurations**, split 24/8. The author supplied both complete executions on
**2026-10-24**, with source **`00fc9de90757eab77fbf6f824132d9bbea3f37e2`**,
PR #80's merge. Actual runtime is JMH 1.37/JDK 21.0.9, matching the controlled
protocol. This author-reported SHA is separate from JMH headers; both initial
pasted command lines are truncated. Short smoke values stay outside research.

The [traversal report](../17-benchmarking/results/graphs/README.md) preserves all
24 cells/errors, 240 measurement and 144 warmup averages, completed in 3 min
36 sec. PATH/SPARSE means favour list scanning in this subset; on DENSE input,
E itself grows quadratically, and size-100 BFS intervals overlap. Three errors
reach 25% of their means, largest about 41.7%. Source adjacency/row counts
explain algorithmic work without establishing exact CPU/cache causes.

The [weighted report](../17-benchmarking/results/graphs/weighted-first-run.md)
preserves eight cells/errors, 80 measurement and 48 warmup averages, completed
in 1 min 14 sec. Both sparse Dijkstra errors exceed 25%. Dense Kruskal at 1,000
reports 163948.138 +/- 19262.711 us/op, roughly 164 ms against a 500 ms iteration
target; actual invocation counts are not supplied. The source sorts/scans all
499,500 canonical dense edges and does not stop once V-1 are selected. Those
source facts do not assign a fraction of elapsed time to sorting or union/find.

Both Result/Interpretation fields now use all **32 current default cells**, with
separate report statistics, complete printed observations and explicit limits.
No sample pooling, new production optimisation, formal significance test,
asymptotic fit or generalisation to all graph inputs is claimed. No new
performance run or executable Java change is made to document these outputs.

After the feature PR is merged, from the Windows local repository root:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd -Pbenchmark clean package
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar -l ".*graphs.*"
```

Expect 572 tests and BUILD SUCCESS, then six listed methods. Package builds tests
and jar; it does not measure performance. The explicit Java path is the author's
verified JDK 21 installation. Check actual Maven/JMH runtimes separately;
`release=21` does not choose the runtime. Linux uses `bash mvnw` with JDK 21.

Optional harness smoke, separate from controlled evidence:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*graphs.*" -p size=100 -wi 1 -w 50ms -i 1 -r 50ms -f 1 -t 1 -foe true
```

Capture the source **before each run and before pulling later results changes**:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*graphs.GraphTraversalBenchmark.*" -foe true
```

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*graphs.WeightedGraphBenchmark.*" -foe true
```

Send each source SHA with actual JDK/JMH headers, full final table/errors,
completion marker and printed fork iterations when available. Include CPU/OS
metadata when known. Preserve the separate 24-cell traversal and 8-cell weighted
runs as console text/captures; resulting repo evidence stays Markdown without
JSON/log exports. Keep time units explicit: these scores use microseconds, unlike
the previous Trees module's nanoseconds. Record any source/runtime/fixture change
and do not infer missing metrics, causes or asymptotic exponents from means.

## Sources and Review Decisions

The supplied CLRS **Third Edition (2009)** was consulted: §§22.1–22.4, printed
pp. 589–614 (representations, BFS, DFS and ordering); §§23.1–23.2, pp. 625–633
(cut arguments and Kruskal); and §24.3, pp. 658–663 (nonnegative Dijkstra and
priority-queue dependence). Those are third-edition locations, not fourth-edition
pagination. FIFO Kahn, immutable APIs, sorted primitive rows, lazy duplicate
queue entries, global negative-weight validation, deterministic ties and long
result bounds are selected implementation decisions, not a literal transcription
of the book's data structures or topological DFS pseudocode.

All twelve numbered documents link to this authoritative scope/contract guide.
Focused corrections distinguish sorted immutable rows from generic mutable lists,
source reachability from a full forest, empty DAG from cycle, local indegrees,
lazy versus indexed priority queues, disconnected MSF versus connected MST, and
V initialisation from edge sorting. Strongly connected components, alternative
weighted algorithms and advanced services remain conceptual extensions.
Earlier Arrays/Searching findings remain tracked; no fresh CI status is inferred
from these local builds.
