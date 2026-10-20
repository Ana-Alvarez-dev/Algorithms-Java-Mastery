# Graph Implementations

This Java 21 package implements the selected scope of
[module 10](../../../../../../../docs/10-graphs/README.md).
The [implementation guide](../../../../../../../docs/10-graphs/implementation-and-benchmark-guide.md)
connects contracts, pseudocode, traces, invariants, complexity, tests and JMH.

| Type | Responsibility | Verification |
|---|---|---|
| IntGraph | Sealed immutable fixed-int-vertex contract | Eight shared representation contract tests |
| AdjacencyListGraph / AdjacencyMatrixGraph | Sorted primitive rows / boolean matrix, loops and unweighted deduplication | Eight inherited tests each, direction, ownership and iterator bounds |
| GraphInputs | Validation, canonical rows, private path reconstruction | Constructor/query/path contract cases |
| BreadthFirstSearch | FIFO source reachability, shortest edge distances and paths | Five tests, all 512 small directed and 64 undirected graphs |
| DepthFirstSearch | Iterative recursive-order traversal | Four tests, small recursive oracle and 100,000-vertex path |
| ConnectedComponents | Undirected partition including isolated vertices | Three tests, all small undirected reachability relations |
| TopologicalSort | FIFO Kahn order or explicit cycle absence | Four tests, independent cycle/precedence oracle |
| WeightedGraph | Immutable sorted weighted lists, rejected parallel pairs | Four tests, zero/negative weights, loops and defensive ownership |
| Dijkstra | Nonnegative long distances, lazy queue/stale skip | Five tests, 2,400 seeded source cases and overflow/zero-cycle boundaries |
| DisjointSet | Union by size, iterative path halving | Three tests, 1,000 seeded unions against naive labels |
| Kruskal | Deterministic minimum spanning forest | Four tests, 192 exhaustive small weighted cases and long totals |

Verification on 2026-10-20 UTC with OpenJDK 21 passed **572 repository tests**,
including **48 Graphs tests**. Six JMH methods built, 16 short smoke cells
completed and a separate probe checked all 32 default combinations with
independent weighted reference algorithms. JavaDoc passes doclint excluding
missing tags. These checks do not provide author research timings.

Unweighted duplicates collapse, weighted duplicate pairs are rejected. Source
searches need a valid vertex, whereas empty components/empty DAG/empty forest
are defined. Ascending neighbours stabilise traversal order; Kahn is FIFO, not
lexicographically minimal. Dijkstra rejects negative edges globally; Kruskal
accepts them and returns a forest on disconnected input. Both weighted result
costs use long. Public snapshots/lists cannot mutate graph or result state.

The guide distinguishes list versus matrix scans, V initialisation, temporary
normalisation, output allocation, lazy priority entries and conservative
union-by-size bounds. No graph mutation, generic multigraph or full alternative
algorithm suite is claimed. Earlier modules remain unchanged.

Result and Interpretation in both JMH templates await measured author output.
Follow the guide's separate 24-cell traversal and 8-cell weighted commands,
record SHA before launch, and preserve microsecond units and all errors in
Markdown without JSON/log artifacts.
