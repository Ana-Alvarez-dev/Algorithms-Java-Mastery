# Trees Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`7182a9990e06dbd4e7d9dd82302ca32932b52d5a`, the PR #106 merge of the
[Hash Tables review](../08-hash-tables/documentation-review.md).

## Scope and conclusion

This technical pass checks the thirteen numbered notes, module/package READMEs,
implementation guide, eleven production Java files, eleven test sources
(including the inherited contract and two enumeration helpers), and both JMH
class bodies. It concentrates on representation, observable contracts,
preservation/termination arguments, operation-specific costs and research limits.
It is not an exhaustive review of every industrial application recommendation
or a new implementation of the conceptual extensions.

Twelve scoped findings are corrected below. Concrete defects included the
claim that balanced BST comparisons halve the remaining node population,
PriorityQueue.offer described as returning false on insertion failure, a
same-values balancing example missing key 70, and general binary-tree update
costs stated without an operation contract. The new
[contract/proof map](implementation-contracts.md) connects current Java behaviour
to the reasoning and evidence.

Only **nineteen Markdown files** change. Executable Java, test sources, JMH
fixtures, POM, scripts, CI and historical result files remain unchanged.
Verification executes the actual repository Java classes through Maven/JUnit/
AssertJ on Java 21; no translated algorithm supplies Java-test evidence.

## Consulted primary sources

The supplied PDF is **CLRS, Introduction to Algorithms, Third Edition (2009)**.
Fourth Edition (2022) remains the preferred bibliography but was not directly
inspected; these page references are not presented as fourth-edition pagination.

| Passages inspected | Printed pages | One-based PDF pages | Review use |
|---|---|---|---|
| Sections 6.1-6.2, heap representation and sift precondition | 151-155 | 172-176 | Live heap size versus array length; heap order; child-subheap repair |
| Section 6.3, bottom-up invariant and height-weighted argument | 156-159 | 177-180 | Leaves first, descending parents and linear aggregate build |
| Section 6.4, sort procedure/invariant | 159-160 | 180-181 | Max-heap prefix and sorted suffix |
| Sections 12.1-12.2, selected order/walk/search/extrema/successor passages | 286-292 | 307-313 | Whole-subtree BST ordering, full traversal versus a single search path |
| Section 12.3, insertion/deletion cases | 294-298 | 315-319 | Key search versus given-node input; successor's possible right child |
| Section 13.2 | 312-314 | 333-335 | Constant local rotations preserving in-order partitions |
| Problem 13-3 | 333 | 354 | AVL per-node height balance, cached height and Fibonacci-style height argument |

The textbook allows a different duplicate policy and exposes parent/node
handles in its BST procedures. Current sets are strict unique-int APIs with
private nodes, parent tracking/recursive returns and key-copy deletion. AVL
deletion here is justified by the actual local height repair, not attributed
to the textbook problem's insertion pseudocode. Proper-tree ownership,
OptionalInt/snapshot policies, explicit metric frames and KSmallest are
repository choices and applied arguments.

Versioned official API references were checked:

- [Java SE 21 TreeSet](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/TreeSet.html): ordered collection reference and basic-operation guarantees.
- [Java SE 21 PriorityQueue](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/PriorityQueue.html): head/ties, insertion/empty results, iteration, null/thread policy and documented operation costs.

The selected Corretto 21.0.13 installation's lib/src.zip entry
java.base/java/util/PriorityQueue.java was also inspected: grow uses
Arrays.copyOf. This fresh implementation observation is separate from the
guide's older OpenJDK 21+35 source inspection; the API does not specify the
growth policy. No portable allocation-byte count follows from either.

## Findings and corrections

| ID | Evidence / gap | Applied correction |
|---|---|---|
| TR01 | Fundamentals rejected array/list encoding of hierarchy despite the module's own array heap; root statements omitted empty trees. | Fundamentals/Terminology/map distinguish logical hierarchy from physical encoding and nonempty root from null empty input. Recursive shape does not mandate recursive Java execution. |
| TR02 | General binary-tree shape and balancing advice could imply ordered lookup or automatic proper-tree enforcement. | Binary Trees/map separate value order from shape, immutable fields from unique child identity, and shared-child DAGs from accepted proper trees. Repeated values remain valid. |
| TR03 | BST and Applications claimed comparisons eliminate half the remaining node population. | Both now discard the excluded subtree without a halving claim. Map derives AVL logarithmic height from minimum population; fixed-seed/random-build assumptions remain separate. The next-document link also follows Operations before Traversals. |
| TR04 | Generic update descriptions hid actual mutable/private-node APIs and deletion identity choices. | Operations/map state unique-int return policies, OptionalInt/inclusive neighbours, successor key copying, right-child reconnection and exactly one size decrement. No public node-handle or strict-neighbour API is invented. |
| TR05 | Traversal prose contrasted BFS iteration with recursion despite four iterative methods; serialization/deletion examples lacked shape/GC boundaries. | Traversals/map specify explicit frontiers, mutable independent value lists, proper-tree-dependent postorder completion, extra shape information for serialization and Java reclamation as separate from visiting nodes. |
| TR06 | The balanced and spine examples had different keys; rotations were overgeneralised across tree families and balance suggested equal/minimal shape. | Balanced Trees adds missing 70, distinguishes two primitives/two composite AVL repairs, height versus population, cached-height update order and deletion with zero-balance heavy child. B-tree split/merge remains distinct. |
| TR07 | The heap definition was stated for all heap families; array representation could imply portable memory benefits without capacity accounting. | Heaps/map scope to the selected binary heap and document primitive storage, duplicates, heap-order snapshots, retained capacity, inactive slots and construction/growth work. |
| TR08 | PriorityQueue.offer was described as false-on-failure; remove overloads and empty/tie behaviour needed precision. | Java note splits remove() from remove(Object), gives null versus throwing empty access, and states successful offer returns true while invalid elements may throw. No stable tie arrival order is promised. |
| TR09 | Applied algorithm contracts/proofs were spread through the guide without central source/test navigation. | Map derives phased metrics including subtree-contained diameter/local balance, max-heap sort partition and copied-full-heap KSmallest selection. It distinguishes mutation, output/frontier storage, k=0 and k=1 behaviour. Applications qualifies Dijkstra's nonnegative-weight requirement. |
| TR10 | Interview table assigned general binary-tree insertion/deletion O(n) despite an ownership caveat; logarithmic search advice overgeneralised individual calls. | Interview/Mistakes use contract-dependent generic updates and distinguish a worst-path guarantee from a quick root match. Complexity/map include capacity construction, initial-state amortisation, full traversal and empty-case shorthand. |
| TR11 | Benchmark interpretation needed a single applied scope alongside the contract/proof chain. | Map reads both actual bodies: fixed-seed lookup excludes updates; hit and miss include different query work; heap pairs restore minima/multisets; builds include allocation/copying or offers. All historical cells/errors/source identities remain separate. |
| TR12 | Ordered review required dated current Java evidence and links without relabelling older results. | READMEs, guide and project audit link this report/map; normal Java test, profile package and discovery outcomes below remain distinct from the original 524-test build and Oracle 21.0.9 timing records. |

TR identifiers are local to this pass. No new balance strategy, traversal API,
heap operation, graph implementation or performance optimisation is introduced.

## Fresh Java 21 verification

JAVA_HOME selected the temporary **Amazon Corretto 21.0.13.0.11** installation;
runtime is OpenJDK **21.0.13+11-LTS**, vendor Amazon.com Inc. Maven Wrapper uses
**Maven 3.9.11**, compiling with release 21. Cached dependencies permitted offline
builds. The author's Oracle installation and repository build settings were not
changed, and no vendor/patch timing comparison is claimed.

Commands executed from the fresh worktree with temporary JAVA_HOME supplied:

```bash
bash mvnw --offline --batch-mode --no-transfer-progress -DfailIfNoTests=true test
bash mvnw --offline --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.trees\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.trees\.(TreeOperationsBenchmark\.bstHit|HeapOperationsBenchmark\.bottomUpBuild)$'
```

Both discovery commands used the explicit Java 21 executable.

| Execution/check | Observed result |
|---|---|
| Normal Maven/JUnit suite | **710 tests**, zero failures/errors/skips; BUILD SUCCESS |
| IntBinarySearchTreeTest / IntAvlTreeTest | **13 / 14** passed, each including ten inherited contracts |
| IntMinHeapTest / IntArrayCapacityTest | **11 / 2** passed |
| TreeTraversalsTest / TreeAnalysisTest | **6 / 5** passed |
| HeapSortTest / KSmallestTest | **4 / 4** passed |
| Trees total | **59** actual JUnit invocations |
| Benchmark profile package | BUILD SUCCESS; executable benchmarks.jar generated |
| Module discovery | All **12** methods listed |
| bstHit parameters | size={100,1000,10000}; insertionOrder={SHUFFLED,SORTED} |
| bottomUpBuild parameters | size={100,1000,10000}; distribution={RANDOM,REVERSE_SORTED} |

Generated Surefire XML confirms the totals. Eleven test sources declare 49
@Test methods; inheriting the ten shared contracts twice yields 59 invocations.
SmallTrees/SmallInputs are helpers, not additional test classes. Existing Java
models run 5000 mixed steps per set representation and 5000 heap steps against
TreeSet/PriorityQueue. The shared set test enumerates 576 insertion/removal-order
pairs over four distinct keys. Specific tests cover successor-child/root repair,
four AVL insertion cases, deletion with zero-balance heavy child, 10000-key AVL
growth/drain, duplicate/no-op returns and integer extremes.

The Java tests enumerate 626 proper binary-tree shapes through seven nodes,
1093 small arrays and 7108 array/k combinations. Traversal oracles and all-pairs
undirected-distance diameter checks are independent of the production explicit
algorithms. 100000-node spines exercise iterative traversals and analysis.
These inner cases are not thousands of separately declared JUnit methods.
Finite execution does not prove every bound or resource-failure path.

After the successful normal suite, packaging deliberately skips further test
execution. It compiles the benchmark profile and additional fixtures but does
not claim execution of those profile-only fixtures. Discovery verifies method
registration/parameters, not timings. No new coverage percentage, JMH score,
smoke run, source-counter experiment, profiler result or heap measurement is
produced by this documentation pass.

## Final audit and preserved history

The final audit checks **299 Markdown files / 1241 relative file
targets**, with zero missing destinations, balanced code fences and a passing
`git diff --check`. No new local Markdown heading-fragment links are introduced;
external URLs and all inherited fragments are not exhaustively validated.
The nineteen changed paths are Markdown in module docs, its package README
and the project traceability review.

Repository inventory remains **100 production Java files / 115 JMH methods**.
Trees contains **11 production Java files, 11 test sources, 49 declared @Test
methods, two JMH classes and 12 JMH methods**. Source/test/fixture/build/CI and
historical-result paths are outside the changed set.

The original **524-test** build remains dated 2026-10-19 evidence. The
[lookup subset](../17-benchmarking/results/trees/README.md) retains its 24 cells,
source `c576cb79aa9747aced1cd041e97f64fc99e60010` identified by the author's
reflog, Oracle JDK 21.0.9 and all errors/iterations. The separate
[heap subset](../17-benchmarking/results/trees/heap-first-run.md) keeps 24 cells,
author-supplied source `ce621c63fabc2fe04c180d71fd1f9e165cc8a447` and its own
errors/iterations. Each has 240 measurement and 144 warmup iteration averages;
samples are not pooled or reclassified as current verification-host data.

Four SHUFFLED AVL errors exceed 25% of their means; five heap errors reach that
threshold, including the high-error JDK peek and its literal CI marker `? 0`.
The 48 recorded cells do not cover all 72 defaults or size 10000. Source path/
parent-move arguments do not fit timing exponents or isolate CPU/cache/allocation
causes. No new JSON/log/CSV artifact is versioned; generated target files remain
build output.

## Next ordered review

Continue with **10 - Graphs**, preserving its implementation/test/research chain.
Later modules are not declared re-reviewed by this Trees documentation pass.
