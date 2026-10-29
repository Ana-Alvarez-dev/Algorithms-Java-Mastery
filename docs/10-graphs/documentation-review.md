# Graphs Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`809deace4c37ba91bf45621265446644dd3392bf`, the PR #107 merge of the
[Trees review](../09-trees/documentation-review.md).

## Scope and conclusion

This scoped technical pass checks the twelve numbered notes through selected
contract, terminology, representation, algorithm, complexity, pseudocode and
test-guidance passages. It reads the guide, module/package READMEs, all twelve
production Java files, twelve test sources and three JMH sources (two benchmark
classes plus their fixture helper). The conceptual notes exceed 15000 lines;
this is not an exhaustive paragraph-by-paragraph editorial review of every
example or domain application.

Twelve findings clarify actual behaviour and its evidence chain. Concrete gaps
included mutable/direct-reference costs beside immutable defensive queries,
connected-MST early-stop pseudocode beside full-scan forest code, edge-only
Kruskal bounds omitting isolate initialisation, and Windows instructions still
expecting the original 572-test repository total. The new
[contract/proof map](implementation-contracts.md) connects APIs, invariants,
source costs, Java tests and research limits.

Only **eighteen Markdown files** change. Executable Java, tests, JMH fixtures,
POM, scripts, CI and historical-result files remain unchanged. Verification runs
the actual classes on Java 21 through Maven/JUnit/AssertJ; no translated
algorithm supplies Java-test evidence.

## Primary sources inspected

The supplied PDF is **CLRS, Introduction to Algorithms, Third Edition (2009)**.
Fourth Edition (2022) remains the preferred bibliography but was not directly
inspected; pagination is not presented as equivalent across editions.

| Selected passages | Printed pages | One-based PDF pages | Review use |
|---|---|---|---|
| Section 22.1 introduction | 589-590 | 610-611 | Logical graph versus list/matrix representation |
| Section 22.2 BFS procedure/invariant | 594-595 | 615-616 | Source reachability, discovery before enqueue, edge distances |
| Section 22.3 DFS procedure | 604-605 | 625-626 | Suspended exploration versus textbook full forest/timestamps |
| Section 22.4 topological definition/DFS procedure | 612-613 | 633-634 | Edge precedence and complete DAG order; current Kahn proof is separate |
| Section 23.1 safe-edge theorem/exchange | 626-627 | 647-648 | Cut respecting selection and tied light crossing edges |
| Section 23.2 Kruskal procedure | 631-632 | 652-653 | Singleton components, sorting and cycle-rejecting union |
| Section 24.3 Dijkstra procedure/invariant passages | 658-660 | 679-681 | Nonnegative settlement and priority-queue model boundary |

The textbook's vertex-keyed queue is not the lazy duplicate-entry queue. Its
topological procedure uses DFS finishing order, whereas this implementation
uses FIFO Kahn with a local-indegree argument. Sorted immutable rows, duplicate
policies, global negative validation, deterministic ties, Optional/array
results, integer/long bounds and disconnected forest output are repository
choices. Source inspection supplies full-edge-scan behaviour; a timing mean
does not establish it.

Official Java SE 21 references checked:

- [PriorityQueue](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/PriorityQueue.html): head operations, ordering, queue costs and arbitrary-object removal versus extraction.
- [List](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html): copyOf/unmodifiable membership versus mutability of contained objects.

WeightedGraph contains immutable scalar records. Neither reference establishes
a measured graph footprint or hardware/cache explanation.

## Findings and applied corrections

| ID | Gap / source evidence | Applied correction |
|---|---|---|
| GR01 | Modelling prose implied relationships cannot be encoded with linear structures. | Fundamentals/map distinguish graph abstraction from array/list encoding, fixed IDs, immutability and different algorithm results. |
| GR02 | Loop degree treatment was unspecified although rows/counts store a loop once. | Terminology/map distinguish logical E, stored A=2E-L for undirected graphs, and two mathematical degree incidences per loop. No degree API is invented. |
| GR03 | Generic mutable/direct-reference tables could be mistaken for current immutable costs. | Representations/Complexity label alternatives and specify binary-search membership, copied neighbours, internal iterators, ordered-set construction and boolean versus conceptual weighted matrices. |
| GR04 | BFS “reachable component” could imply SCC/partition semantics on directed input. | BFS/map use outgoing source reachability, mark-before-enqueue, exact absence/source paths, valid source and independent validated result queries. |
| GR05 | Push-all/mark-on-pop illustration could inherit the selected frame implementation's O(V) frontier claim. | DFS/map separate duplicate pending entries/O(E) alternative stack from suspended iterators. Discovery order is not finish times, cycle witness or full forest. |
| GR06 | FIFO Kahn was described as non-minimal rather than lacking a minimum-order guarantee. | Topological/package/map qualify the guarantee and give a valid nonminimum example; local indegrees, global cycle coverage and empty DAG remain explicit. |
| GR07 | Conventional Dijkstra bounds needed mapping to lazy entries/global validation and path safety. | Shortest Paths/Mistakes/map derive O(V+E+E log(E+1)) time, O(V+E) state, stale skipping, strict parents, zero-cycle safety and long arithmetic. No indexed O(V) queue bound is borrowed. |
| GR08 | Kruskal pseudocode stops at V-1 while Java scans all edges; edge-only cost omits V. | MST/Complexity/Interview/map distinguish variants, include singleton initialisation and a conservative union-by-size/path-halving bound, and derive V-components forest edges with negative/tied weights. |
| GR09 | Ownership/return conventions lacked one applied source/result map. | Map separates independent mutable arrays from unmodifiable weighted records/lists, source traversals from partitions/orders/forests and invalid inputs from valid empty results. |
| GR10 | MST test guidance required global connectivity/V-1 edges for the disconnected forest API. | Guidance checks each original component including isolates, acyclicity, V-components edges and minimum cost for that partition. Alternative algorithms/oracles remain outside production scope. |
| GR11 | Research needed an applied connection to exact fixture/method bodies. | Map records connected path-backed fixtures, sparse attempts versus distinct edges, different BFS/DFS outputs, positive weights, full union attempts, microsecond units and separate reports. No score or causal attribution is added. |
| GR12 | Current instructions expected 572 tests without a historical date. | Guide/README label the original build and link fresh execution; package README/project audit connect this ordered review while preserving timing provenance. |

GR identifiers are local to this review. No optimisation, mutable API, additional
weighted algorithm or benchmark fixture is introduced.

## Fresh verification in Java 21

JAVA_HOME selected temporary **Amazon Corretto 21.0.13.0.11**, OpenJDK
**21.0.13+11-LTS**, vendor Amazon.com Inc. Maven Wrapper uses **Maven 3.9.11**,
compiling with release 21. The author's Oracle JDK 21.0.9 experiment and local
installation remain separate; functional verification is not a JVM comparison.

An initial worktree passed the suite and benchmark package. Before publication,
the session reset removed that temporary worktree. The Markdown corrections
were reconstructed from the review on the same unchanged main revision and
the actual Java suite/package/discovery were repeated. The successful closing
builds used temporary session-proxy Maven settings and the environment Java
trust store to resolve dependencies. These options/files stay outside the repo.

Core commands, with temporary JAVA_HOME/settings/trust options supplied separately:

```bash
bash mvnw --batch-mode --no-transfer-progress -DfailIfNoTests=true test
bash mvnw --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.graphs\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.graphs\.(GraphTraversalBenchmark\.listBfs|WeightedGraphBenchmark\.dijkstra)$'
```

Discovery uses the explicit Java 21 executable.

| Execution/check | Observed result |
|---|---|
| Normal Maven/JUnit suite | **710 tests**, zero failures/errors/skips; BUILD SUCCESS |
| AdjacencyListGraphTest / AdjacencyMatrixGraphTest | **8 / 8** passed, inherited contracts |
| BreadthFirstSearchTest / DepthFirstSearchTest | **5 / 4** passed |
| ConnectedComponentsTest / TopologicalSortTest | **3 / 4** passed |
| WeightedGraphTest / DijkstraTest | **4 / 5** passed |
| DisjointSetTest / KruskalTest | **3 / 4** passed |
| Graphs total | **48** actual JUnit invocations |
| Benchmark profile package | BUILD SUCCESS; executable benchmarks.jar generated |
| Module discovery | All **6** methods listed |
| listBfs parameters | size={100,1000}; shape={PATH,SPARSE,DENSE} |
| dijkstra parameters | size={100,1000}; shape={SPARSE,DENSE} |

Surefire XML confirms totals. Twelve test sources declare 40 @Test methods;
eight shared representation contracts inherited twice yield 48 executions.
SmallGraphs is an oracle helper, not another test class or production Floyd-Warshall.

The existing Java tests cover 400 seeded representation cases; all 512
three-vertex directed graphs with loops, all sources/both representations for
BFS/DFS; independent cycle/precedence checks for Kahn; all 64 four-vertex
undirected graphs for reachability/components; and a 100000-vertex DFS/BFS path.
Dijkstra checks 400 seeded weighted graphs/all six sources (2400 source cases)
against synchronous relaxation, plus stale/zero/negative/long boundaries.
Kruskal checks 192 graph/weight assignments against exhaustive forest subsets;
DisjointSet checks 1000 unions against naive labels. Inner cases are not thousands
of separately declared JUnit methods, nor proofs for all inputs/resource failures.

Packaging deliberately skips further tests after the successful normal suite.
Additional profile fixtures compile but are not claimed as newly executed.
Discovery checks registration/parameters, not timing. No fresh coverage percentage,
smoke/probe run, score, profiler or allocation/heap measurement is produced.

## Final audit and preserved evidence

The final audit checks **301 Markdown files / 1284 relative file
targets**, with zero missing destinations, balanced fences and passing
`git diff --check`. No new local Markdown heading-fragment links are introduced;
external URLs and inherited fragments are not exhaustively validated. Eighteen
changed paths are Markdown in module docs, its package README and project audit.

Inventory remains **100 production Java files / 115 JMH methods**. Graphs has
**12 production Java files, 12 test sources, 40 declared @Test methods, three
JMH sources (two benchmark classes/one helper) and 6 methods**. Source, tests,
fixtures, build/CI and historical-result paths are outside the change.

The original **572-test** build on 2026-10-20 UTC remains historical. The author's
**2026-10-24** [traversal report](../17-benchmarking/results/graphs/README.md)
keeps 24 cells/errors, 240 measurement and 144 warmup averages; the separate
[weighted report](../17-benchmarking/results/graphs/weighted-first-run.md)
keeps eight cells/errors, 80 measurement and 48 warmup averages. Both retain
source `00fc9de90757eab77fbf6f824132d9bbea3f37e2`, Oracle JDK 21.0.9/JMH 1.37
and us/op units. Author-reported SHA is separate from JMH headers.

All 32 default fixture cells were reported; that is not every graph/source/
direction/weight/density. Three traversal and two weighted errors reach 25% of
their means. Dense Kruskal's roughly 164 ms mean against a 500 ms iteration target
does not supply invocation counts or a sorting/union cost split. Samples stay
separate; missing timings/uncertainty are not reconstructed. No new JSON/log/CSV
is versioned; generated target files remain build output.

## Next ordered review

Continue with **11 - Recursion**, preserving its implementation/test/research
chain. Later modules are not declared re-reviewed by this Graphs pass.
