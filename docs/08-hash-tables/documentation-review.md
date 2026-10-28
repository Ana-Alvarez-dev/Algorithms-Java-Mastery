# Hash Tables Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`60ec9de663589f37bfa81c6d3b1fcd82176bcdaf`, the PR #105 merge of the
[Stacks and Queues review](../07-stacks-and-queues/documentation-review.md).

## Scope and conclusion

This technical pass reads the ten numbered notes, module/package READMEs,
implementation guide, all eight production Java files, nine test sources
(including the shared contract and enumeration helper) and the JMH class body.
It checks current contracts, equality/hash assumptions, collision paths,
live/used load, rebuild costs, application invariants and experimental limits.
It is not an exhaustive review of every backend application recommendation or
a new implementation of the conceptual extensions.

Ten scoped findings are corrected below. Concrete contradictions included the
Complexity note saying Result/Interpretation were still pending, the hash-function
note requiring consistency across executions, and a resizing takeaway saying
all rebuilds take O(n) despite the qualified probing analysis above it.
The [contract/proof map](implementation-contracts.md) connects these corrections
to the actual Java classes, tests and historical research.

Only **sixteen Markdown files** change. Executable Java, tests, JMH fixtures,
POM, scripts, CI and historical result paths remain unchanged. Fresh verification
runs actual repository classes on Java 21 through Maven/JUnit/AssertJ; no
translated algorithm is presented as Java execution evidence.

## Primary sources inspected

The supplied PDF is **CLRS, Introduction to Algorithms, Third Edition (2009)**.
The preferred bibliography remains Fourth Edition (2022), which was not directly
inspected; these page numbers are not attributed to that edition.

| Material read | Printed pages | One-based PDF pages | Applied use |
|---|---|---|---|
| Section 11.2 | 256-261 | 277-282 | Collisions/chaining, known-node operations versus key search, simple uniform hashing and expected chain length |
| Section 11.3 introduction, division/multiplication | 262-264 | 283-285 | Key distribution assumptions and capacity-dependent index choice |
| Section 11.4 | 269-276 | 290-297 | Bounded probe sequences, deletion markers, clustering, step coverage and ideal uniform-hashing assumptions |

The textbook's constant insertion of a known-absent node and constant doubly
linked deletion given a node are not the manual API's replacement-aware put
and singly linked remove-by-key. Ideal arbitrary-permutation probe bounds are
not assigned to deterministic linear probing. Cached spreading, explicit
live/used thresholds, snapshots, application tie rules and the following
cleanup accounting are repository-specific derivations.

Versioned official Java references checked:

- [Java SE 21 Object](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Object.html#hashCode()): equality/hash consistency within an execution, without a cross-execution integer guarantee.
- [Java SE 21 HashMap](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html): conditional basic-operation costs, capacity-plus-size iteration, null/order/thread policies and possible use of comparison order.

These API contracts do not establish benchmark causes or portable heap byte
counts. The selected manual tables are not full Map implementations.

## Findings and applied corrections

| ID | Evidence / gap | Applied correction |
|---|---|---|
| HT01 | Fundamentals implied that computing an index eliminates sequential traversal. | Fundamentals/map distinguish candidate home, chain/probe search, equality and conditional expected lookup. Current non-null API, snapshot and no-iterator scope are explicit. |
| HT02 | Hash Functions said every execution must return the same result; blanket key immutability was stronger than the requirement. | Hash Functions/Mistakes/map require stable equality/hash-relevant state while stored and distinguish within-execution consistency, equal-key implication, complete hash and masked index. |
| HT03 | “No collisions in practice” explanations obscured collision-free particular sets and the current distinct-home fixture. | Hash Functions/Collisions/map separate universe-wide pigeonhole reasoning, fixed-set alternatives, identical complete hashes, shared homes and contiguous runs of distinct homes. |
| HT04 | Chaining's insertion sketch omitted replacement search; “never full” and constant local operations could overstate public guarantees. | Chaining/map state search-before-insert/growth, original key identity, private nodes, keyed predecessor deletion, capacity/resource limits and saved-successor rehash preservation. |
| HT05 | Open addressing described clearing versus markers imprecisely and needed the actual search-before-reuse invariant. | Open Addressing/map specify cleared references with retained DELETED state, continue-past-deletion replacement, bounded step-one paths and separate live/used counts. Locality/space claims are qualified by actual reference-array layout. |
| HT06 | Resizing's final O(n)/average-O(1) takeaway contradicted its detailed collision-heavy probing bound. | Load/Complexity/map qualify all rebuild claims, cached hashes/new masks, unchanged possible indices, equal-key early return, growth versus same-capacity cleanup and no shrinking. Geometric growth plus the quarter-capacity cleanup gap are derived separately from expected hashing assumptions. |
| HT07 | Simplified Java HashMap tables could be mistaken for per-call guarantees or the manual API. | Java note/map distinguish amortised put, capacity-sensitive iteration, nullable lookup ambiguity and the limited common workload; no universal JDK logarithmic collision bound is asserted. |
| HT08 | Applications needed a central executable output/tie/mutation map rather than only general use cases. | Algorithms/map derive prefix counts/seen sets, earliest-completing TwoSum with earliest compatible left, long complement range checks and original-order unique-index scan. Distinct-count storage and early-return limits are explicit. |
| HT09 | Complexity said measurement was still pending despite the completed historical report. | Complexity links the 18-cell report and identifies its contiguous-home probing workload. Map derives N-i+1 search visits and (N+3)/2 per-cycle mean, separates fixed miss from cursor-based hit/pairs and makes no exact-time causal claim. |
| HT10 | Ordered review needed dated source/test/research navigation. | READMEs, guide and project audit link the report/map. Actual Java 21 tests, packaging and discovery below remain separate from the earlier 465-test build and author timing source. |

HT identifiers are local to this documentation review. No probing optimisation,
new hash family, tree bin, iterator or additional application is introduced.

## Fresh verification on the Java project

The host default was Java 17. A temporary official **Amazon Corretto
21.0.13.0.11** installation was selected via JAVA_HOME; observed runtime was
OpenJDK **21.0.13+11-LTS**, vendor Amazon.com Inc. Maven Wrapper selected
**Maven 3.9.11**, compiling with release 21. The author's local Oracle JDK
21.0.9 and the project's POM were not changed.

The first offline attempt stopped before tests because a required Maven plugin
was absent from the session cache. Successful normal/profile builds used a
temporary settings file for the session network proxy and the environment's
Java trust store. These files and runtime options stayed outside the repository.

Core commands executed, with temporary JAVA_HOME/settings/trust options supplied
separately:

```bash
bash mvnw --batch-mode --no-transfer-progress -DfailIfNoTests=true test
bash mvnw --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.hashtables\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.hashtables\.HashTableOperationsBenchmark\.probingRemoveRestore$'
```

The explicit Java 21 executable also ran both discovery commands.

| Execution/check | Observed result |
|---|---|
| Normal Maven/JUnit suite | **710 tests**, zero failures/errors/skips; BUILD SUCCESS |
| SeparateChainingHashTableTest | **14** passed, including 12 inherited contracts |
| LinearProbingHashTableTest | **18** passed, including the same 12 inherited contracts |
| HashingTest | **2** passed |
| FrequencyCounterTest / DuplicateDetectorTest / FirstUniqueElementTest | **3 / 3 / 3** passed |
| TwoSumTest | **4** passed |
| Hash Tables total | **47** actual JUnit invocations |
| Benchmark profile package | BUILD SUCCESS; produced executable benchmarks.jar |
| Module benchmark discovery | All **9** methods listed |
| probingRemoveRestore parameter discovery | size={100,1000,10000}; hashMode={DISTINCT_HASH,CONSTANT_HASH} |

Generated Surefire XML confirms these totals. The module has 35 declared @Test
methods; inheriting the twelve shared contracts twice accounts for 47 invocations.
The seeded Java test performs 2500 operations per representation/hash mode
against HashMap with invariant/state checks. Additional cases cover equal-key
identity, 100 colliding keys, growth, wrap, tombstone update/reuse, cleanup and
integer hash/capacity extremes.

SmallArrays enumerates 1093 arrays of lengths 0..6 over {-1,0,1}. The four
application tests run their Java scanning/pairwise oracles; TwoSum includes
seven targets per array, or 7651 array/target combinations, plus overflow/tie
cases. Those inner cases are not counted as thousands of separate JUnit methods.
They supply finite correctness evidence, not proof for every key or allocation
failure or a timing experiment.

Packaging intentionally skips further test execution after the successful
normal suite. It compiles the benchmark profile and checks registration;
profile-only fixtures are not claimed as new executed tests. Discovery is not
a timed JMH run. No fresh coverage percentage, score or profiler result is
produced, and a functional run on Corretto is not a controlled comparison with
the author's historical Oracle runtime.

## Final audit and preserved evidence

The final path audit checks **297 Markdown files / 1197 relative
file targets**, with zero missing destinations and balanced code fences.
`git diff --check` passes. No new local Markdown heading-fragment links are
introduced; this is not an exhaustive external-URL or inherited-anchor audit.
All sixteen changed paths are Markdown in module docs, its package README
and the project traceability review.

The repository inventory remains **100 production Java files / 115 JMH methods**.
Hash Tables has **8 production Java files, 9 test sources, 35 declared @Test
methods and one JMH class with 9 methods**. No source/test/benchmark/build/CI or
historical-result file is changed.

The earlier **465-test** build on 2026-10-19 remains historical evidence.
The [reported experiment](../17-benchmarking/results/hash-tables/README.md)
keeps the author-confirmed execution source
`03e6ed5e5649b4018b47f054319e0495676b7fcf`, Oracle JDK 21.0.9, JMH 1.37,
18 DISTINCT_HASH cells, every mean/error, 180 measurement and 108 warmup
iteration averages. The earlier instrumented source-counter check remains
separate; this review derives the path from source without claiming it reran
that instrumented copy.

Current defaults have 54 possible method/size/mode configurations; CONSTANT_HASH
and size 10000 are not promoted to measured cells. Hit/miss/pair scores include
different query-selection work. Remove/restore retains logical mappings and
references, while physical chain/JDK bin placement can change. Growth, accumulated
cleanup, heap footprint, allocation rate and hardware/cache causes are outside
that timing subset. No new JSON/log/CSV artifact is versioned; generated target
files remain build output.

## Next ordered review

Continue with **09 - Trees**, preserving its implementation/test/research chain.
Later modules are not declared re-reviewed by this Hash Tables pass.
