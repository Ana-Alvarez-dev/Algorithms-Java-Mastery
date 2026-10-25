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

Both JMH templates now contain Result and Interpretation from the author's
[24 traversal cells](../../../../../../../docs/17-benchmarking/results/graphs/README.md)
and [8 weighted cells](../../../../../../../docs/17-benchmarking/results/graphs/weighted-first-run.md).
Source `00fc9de90757eab77fbf6f824132d9bbea3f37e2` was supplied with the outputs;
actual runtime is JDK 21.0.9/JMH 1.37. All means/errors, 320 measurement and
192 warmup averages remain in separate Markdown reports without JSON/log.
Five cells have error at least 25% of the mean; these and workload limitations
remain visible. All current default cells are measured, not all possible graphs.
