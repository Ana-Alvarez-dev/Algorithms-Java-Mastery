# Linked Lists Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`20e7f2823fc0e9e620029177fceb111961e80811`, the PR #103 merge of the
[Sorting review](../05-sorting/documentation-review.md).

## Scope and conclusion

This technical pass checks the eleven numbered notes, module/package READMEs,
implementation guide, all six production source files, five test source files
and the LinkedListOperationsBenchmark body. It examines current contracts,
ownership, structural preservation, iterator policies, locating/validation costs
and the boundary between implemented and proposed measurements. It is not an
exhaustive editorial review of every application recommendation or future API.

Ten scoped findings are corrected below. A concrete contradiction was the
unconditional “source unchanged” appendAll wording alongside self-append, where
source and receiver are the same changed object. Another was a claim that JMH
already compares standalone node algorithms, while the current benchmark only
compares container operations. Other changes make representation, boundary and
proof assumptions explicit. The new [contract/proof map](implementation-contracts.md)
connects these claims to the actual Java classes and tests.

The change contains seventeen Markdown files and one Java Javadoc-only edit.
Executable Java, tests, benchmark fixtures, POM, scripts, CI and historical
measurements remain unchanged. Unlike an independent model check, the execution
evidence in this pass comes from **the actual repository classes on Java 21**.
No Python translation is used as algorithm-execution evidence.

## Consulted sources

The supplied PDF is **CLRS, Introduction to Algorithms, Third Edition (2009)**.
The preferred bibliography remains **Fourth Edition (2022)**, which was not
directly inspected; no equivalence of page numbers is claimed.

| Source checked | Printed pages | One-based PDF pages | Review use |
|---|---|---|---|
| Section 10.2: representations | 236-237 | 257-258 | Logical link order; singly/doubly, sorted/unsorted and circular variants |
| Section 10.2: search, insertion and deletion | 237-238 | 258-259 | First-match scan; constant local splice versus preceding search cost |
| Section 10.2: sentinels | 238-240 | 259-261 | Explicit representation boundary: textbook circular doubly linked sentinel versus the repository's singly linked data-node ring |

CLRS's dynamic-set/known-node operations are not the same public API as this
repository's indexed, private-node containers. Cached tail/size, copying append,
self-append, transferring split, fail-fast policy and standalone-node validation
are repository choices. The applied reversal, Floyd, overlap, merge and dedup
arguments are not attributed to those pages as though all appear there.

Versioned official primary references were also inspected:

- [Java SE 21 LinkedList](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/LinkedList.html): List/Deque scope, nulls, nearer-end traversal and fail-fast limits.
- [Java SE 21 Iterator](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Iterator.html): exhaustion and optional/default unsupported removal.
- [Java SE 21 JVMS, section 2.7](https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-2.html#jvms-2.7): object layout is not prescribed by the specification.

These distinguish public Java contracts from cost models and implementation
choices. They do not supply a measured node byte size, cache explanation or
comparison of JVM vendors/patch releases.

## Findings and applied corrections

| ID | Evidence / gap | Applied correction |
|---|---|---|
| LL01 | General known-node discussions could be mistaken for the actual indexed private-node API; logical order and physical layout were conflated. | Fundamentals and the map state LinkedSequence/Iterable scope, private nodes, nullable values, bounds/failures and model-qualified layout. Public LinkedNode chains are a separate type. |
| LL02 | Ring properties and generic structural advice lacked explicit empty/singleton/count and split/reverse postconditions. | Circular and the map specify exact counted topology, finite iteration, saved-successor reversal, endpoint repair and two disjoint rings after interior split. The intentional ring is distinct from an unsupported cycle in a standalone chain. |
| LL03 | Uniform O(n) tables hid positional/end cases and cached metadata; constant local splices could hide preceding lookup/validation. | Singly/Doubly/Complexity give Theta(1+i) and nearer-end costs, getLast versus get(n-1), endpoint split cases, cached size, and total public merge validation work. Application equality/comparator work is separate. |
| LL04 | Guide/package/source Javadoc said append's source is unchanged without excluding self-aliasing. | Operations, guide, package README and LinkedSequence Javadoc distinguish distinct-source preservation from self-append. The map proves snapshot-first finite copying and separates fresh nodes, shared values and transferring split ownership. Source executable tokens are unchanged. |
| LL05 | Iteration could be read as a value snapshot; no-op and reclamation boundaries needed precision. | Java/Mistakes/map describe current-value reads, nonstructural set, singleton reverse/empty clear/split-at-size, finite ring counters and unsupported iterator remove. Ownership reset does not imply immediate GC or thread safety. |
| LL06 | Middle's “single traversal” omitted validation; merge used one n without naming both lengths; destructive results needed ownership qualification. | Algorithms names the validation-plus-middle phases, second-middle policy, independent lengths n/m, constant sentinel state and consumed input-head interpretation. The map includes cycle/order validation in all stated public costs. |
| LL07 | Identity/order reasoning needed a central applied proof rather than a technique name alone. | The map derives Floyd's modular meeting condition over distinct reachable nodes, shared-tail overlap equivalence for acyclic chains, logical merge-prefix/left-tie preservation and first-node deduplication. Comparator side effects do not acquire rollback promises. |
| LL08 | Java guidance overgeneralised interchangeability and “frequent changes” suitability; an extra reference was treated as a portable memory increase. | Java/Properties descriptions distinguish List/Deque from the educational interface, indexed versus positioned work, throwing endpoints versus peek/poll nulls, logical reference fields and JVM-specific bytes. No universal performance ranking is inferred. |
| LL09 | Common Algorithms said proposed standalone comparisons were already performed with JMH. | That section now marks them as future questions. Current scope is 15 container workloads over four default sizes; the historical 30 measured cells remain separate from 60 defaults and new discovery. |
| LL10 | A new ordered review needed dated execution and source/evidence navigation. | Both READMEs, guide and project audit link this report and the map. Fresh Maven/JUnit and packaging/discovery results below are explicit, while the earlier 338-test build and author timing record retain their provenance. |

LL identifiers are local to this review and do not replace earlier module
findings. These are scoped corrections, not new list operations or a claim
that all possible callbacks, invalid objects, schedules or resource failures
have been tested.

## Fresh verification on the Java project

The host initially selected JDK 17. A temporary **Amazon Corretto 21.0.13.0.11**
JDK was obtained from its official distribution and selected through JAVA_HOME.
Observed runtime was OpenJDK 21.0.13+11-LTS, vendor Amazon.com Inc.; Maven Wrapper
selected **Maven 3.9.11**. The project continued compiling with release 21.
The runtime was temporary; the POM and the author's local installation were
not changed. This differs from the historical Oracle 21.0.9 timing runtime.

Initial dependency-resolution attempts failed before tests because of the
session's network routing. A temporary Maven settings file configured the
session proxy, with the environment's Java trust store; dependency resolution
then succeeded. Those environment files are outside the repository.

Core project commands used, with the temporary JAVA_HOME/network options
supplied separately:

```bash
bash mvnw --batch-mode --no-transfer-progress -U -DfailIfNoTests=true test
bash mvnw --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.linkedlists\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.linkedlists\.LinkedListOperationsBenchmark\.singlyTraverse$'
```

The successful test invocation compiled the unmodified executable source from
the reviewed base. The later Java edit changes only appendAll's Javadoc; the
benchmark package compiled that reviewed working tree. Comment/whitespace
stripping confirms that executable Java tokens still match the base.

| Execution/check | Observed result |
|---|---|
| Maven test, actual Java 21 classes | **710 tests**, zero failures, errors or skipped tests; BUILD SUCCESS |
| SinglyLinkedListTest | **21** passed |
| DoublyLinkedListTest | **23** passed |
| CircularLinkedListTest | **22** passed |
| LinkedListAlgorithmsTest | **15** passed |
| Linked Lists total | **81** actual JUnit invocations, including inherited shared contracts |
| Benchmark profile package | BUILD SUCCESS; annotation-processed/compiled the benchmark sources and created the executable jar |
| Linked Lists discovery | All **15** methods listed |
| singlyTraverse parameter discovery | size={100,1000,10000,100000} |

The Maven totals were cross-checked against generated Surefire XML. The shared
source has 21 inherited test methods per topology; doubly adds two and circular
one, plus 15 standalone-algorithm tests. This accounts for 81 executions, not
81 separately declared methods. The shared seeded test performs 2000 Java
operations per topology against java.util.LinkedList with structure checks.
Standalone tests exercise real node identities, stable record ties, null-aware
comparators, cycles, intersecting/unsorted inputs and a 100,000-node chain.

The package command deliberately skips test execution after the successful
normal suite; it compiles the profile's additional test sources but does not
claim new invocations of those extra fixtures. Discovery is not a timed JMH
run. No fresh coverage report, profiler experiment or performance score is
produced. Passing tests supplies finite execution evidence, not a universal
proof or a measurement of every complexity/storage claim.

The reviewed main revision also has a successful
[hosted Maven CI run](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40772570599).
That hosted baseline is separate from the local Corretto execution recorded here.

## Final audit and historical evidence

The final path audit checks **293 Markdown files / 1108 relative file targets**
with **zero missing destinations**. All **four newly introduced local heading
targets** resolve. Fenced-code balance and `git diff --check` pass. This does
not claim validation of every inherited external URL or heading fragment.

The change boundary is **seventeen Markdown files plus one Java comment-only
change**. Executable-token comparison against the reviewed base passes. The
repository inventory remains **100 production Java files / 115 JMH methods**;
the module has six production files, five test sources (39 declared @Test
methods including the inherited base) and one JMH class with fifteen methods.
Test sources, historical-result paths, build/CI configuration and benchmark
fixtures are outside the changed file set.

The [first timing subset](../17-benchmarking/results/linked-lists/README.md)
keeps its 30 means/errors, source a16a431 reported by the author, Oracle JDK
21.0.9 and five configurations with supplied detailed headers/iterations.
The other iteration values and absent metadata are not reconstructed. New
functional verification on Corretto 21.0.13 does not relabel that experiment,
replace its source identity or create a controlled vendor/patch comparison.

The benchmark source times restored prepend/remove pairs, current middle access
and finite traversal/checksum, including iterator/unboxing/allocation sites as
documented. It does not measure standalone merge/reversal/deduplication,
deep copying, concurrency safety or retained heap footprint. No new JSON/log/CSV
artifact is versioned, and generated target output remains build output.

## Next ordered review

Continue with **07 - Stacks and Queues**, retaining its implementation, tests
and historical measurements. This pass does not declare later modules
re-reviewed or add new container/chain features. Future API extensions need
their own contracts, Java tests and justified benchmark questions.
