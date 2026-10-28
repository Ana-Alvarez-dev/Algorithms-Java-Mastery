# Stacks and Queues Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`6db7b24a9660ac9fd44ce42f941f8307bc2c2d22`, the PR #104 merge of the
[Linked Lists review](../06-linked-lists/documentation-review.md).

## Scope and conclusion

This is a scoped technical pass over the ten numbered notes, module/package
READMEs, implementation guide, fifteen production source files, thirteen test
sources and both JMH class bodies. The long fundamentals/implementation notes
were checked through their contract, boundary, representation, complexity and
engineering sections; this is not an exhaustive paragraph-by-paragraph editorial
review of all examples or application advice.

Ten findings below clarify the current APIs, preservation arguments and cost
models. Two concrete contradictions were a queue table's unqualified constant
enqueue beside dynamic growth, and Java Deque's claim that no timing comparison
had been recorded despite the linked historical experiment. The new
[contract/proof map](implementation-contracts.md) makes current source, proofs,
tests and measurement limits navigable together.

Only sixteen Markdown files change. Java source, tests, JMH fixtures, POM,
scripts, CI and historical result files remain unchanged. Verification executes
the actual repository Java classes through Maven/JUnit on Java 21; it does not
use a translated implementation as Java-test evidence.

## Consulted primary sources

The supplied PDF is **CLRS, Introduction to Algorithms, Third Edition (2009)**.
The preferred bibliography remains Fourth Edition (2022), which was not directly
inspected; page numbers are not presented as interchangeable across editions.

| Material inspected | Printed pages | One-based PDF pages | Applied boundary |
|---|---|---|---|
| Section 10.1, stacks and queues | 232-235 | 253-256 | LIFO/FIFO, array stack and circular queue, underflow/overflow |
| Exercises 10.1-5 through 10.1-7 | 236 | 257 | Deque, queue from stacks, stack from queues as distinct constructions |
| Section 17.1, introduction and stack aggregate argument | 451-454 | 472-475 | Worst-case total over a sequence, not random-input expectation |
| Section 17.2, accounting and stack credit | 456-457 | 477-478 | Credit must pay actual work without becoming negative |

The textbook's one-based circular queue reserves a slot and uses a next-insertion
tail. Current Java rings instead use zero-based head plus explicit size and
all capacity slots. Array growth, null rejection, snapshot APIs, monotonic
algorithms and checked postfix arithmetic are repository choices. The two-stack
and growth arguments in the map are applied derivations, not claims that these
pages contain the complete Java implementation or its tests.

Official versioned references checked:

- [Java SE 21 Queue](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Queue.html): ordering is implementation-specific; Queue does not itself define blocking methods.
- [Java SE 21 Deque](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Deque.html): endpoint method pairs, FIFO/LIFO use and broader interface scope.
- [Java SE 21 ArrayDeque](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayDeque.html): null/thread policy and amortised endpoints versus linear searches/bulk work.

These references specify Java APIs. They do not make the educational interfaces
JCF collections or establish this experiment's allocation rate or heap footprint.

## Findings and applied corrections

| ID | Gap in the reviewed material | Applied correction |
|---|---|---|
| SQ01 | Conceptual isFull/nullable alternatives could be mistaken for current Stack/FifoQueue methods; Java Queue was treated too broadly as FIFO. | Fundamentals/map name the actual interfaces, null/duplicate policies, strict versus sentinel access and shallow removal-order snapshots. FIFO here is explicit; java.util.Queue ordering depends on implementation. |
| SQ02 | Deque overflow prose lacked the current null-before-full precedence and all-slot capacity invariant. | Deque/map specify false versus IllegalStateException, NullPointerException even when full, explicit size, canonical empty head and capacity-one behaviour. |
| SQ03 | Top/front/rear conventions and textbook circular formulas lacked a direct mapping to current Java fields. | Queue implementations/map distinguish the spare-slot textbook model from head/size Java rings, derive the safe offset helper and prove logical-order copying during wrapped growth. |
| SQ04 | Shrinking and node integration alternatives could suggest behaviour absent from the selected implementations. | Stack implementations/map label shrinking and fixed-stack alternatives, retained capacity, private nodes, live-slot clear and owner reset versus eventual GC. Construction work is explicit. |
| SQ05 | Dynamic circular enqueue was O(1) without qualification; “expected” tables blurred ordinary, worst and amortised costs. | Queue/Complexity and fundamentals qualify table models, array growth, initial capacity and geometric aggregate work. Capacity after drain is not bounded by current live size alone. |
| SQ06 | Two-stack amortisation needed to include peek-triggered transfer and real resizable backing stacks. | Map proves outgoing-prefix/reversed-incoming order, per-item transfer accounting and a qualified abstract potential, then separately pays geometric array growth. A preloaded state needs initial credit/work. |
| SQ07 | Algorithm patterns lacked a central tie/output/mutation specification and applied linear-work reasoning. | Algorithms/map specify indices versus values, strict greater versus equal replacement, expiry/candidate invariants, parser limits, postfix operand order/checked overflow and destructive FIFO reversal. Output and auxiliary storage are separated. |
| SQ08 | Java Deque said no timing comparison existed; representation preferences could sound like universal empirical rankings. | Java note links the existing 20-cell subset, removes the stale statement and distinguishes reference layout hypotheses from measured allocation/cache/heap claims. No new timing result is invented. |
| SQ09 | Problem guide discouraged a deque used at one end, despite Java stack guidance; scheduling and empty-state advice overgeneralised. | Problem guide recognises Deque as a valid stack abstraction, qualifies FIFO scheduling/concurrency, and Mistakes distinguishes linked null endpoints from numeric ring indices. |
| SQ10 | Ordered review needed dated source-to-test-to-research navigation. | READMEs, guide and project audit link this report/map. Fresh Java execution and JMH registration checks below remain separate from historical builds, scores and source identities. |

Identifiers are local to this review and do not renumber earlier findings.
These documentation corrections do not add new container features or declare
all later modules re-reviewed.

## Fresh Java 21 verification

The temporary runtime was **Amazon Corretto 21.0.13.0.11**, OpenJDK
21.0.13+11-LTS, vendor Amazon.com Inc. JAVA_HOME selected it for Maven Wrapper
**3.9.11**, compiling with release 21. The author's Oracle JDK 21.0.9 experiment
is a separate runtime; no vendor/patch comparison is inferred.

Commands executed from this clean worktree, with temporary JAVA_HOME supplied:

```bash
bash mvnw --offline --batch-mode --no-transfer-progress -DfailIfNoTests=true test
bash mvnw --offline --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.stacksandqueues\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.stacksandqueues\.QueueOperationsBenchmark\.twoStacksRotate$'
```

The explicit Java 21 binary also ran both discovery commands. Cached dependencies
allowed offline builds; no repository environment or build configuration changed.

| Execution/check | Observed result |
|---|---|
| Normal Maven/JUnit test suite | **710 tests**, zero failures/errors/skips; BUILD SUCCESS |
| ArrayStackTest / LinkedStackTest | **11 / 8** passed |
| CircularArrayQueueTest / LinkedQueueTest / TwoStackQueueTest | **11 / 9 / 11** passed |
| BoundedArrayDequeTest | **8** passed |
| DelimiterValidatorTest / PostfixEvaluatorTest | **5 / 6** passed |
| NextGreaterElementTest / SlidingWindowMaximumTest / QueueReverserTest | **4 / 4 / 3** passed |
| Module total | **80** actual JUnit invocations, including inherited contracts |
| Benchmark profile package | BUILD SUCCESS; generated executable benchmarks.jar |
| Module method discovery | All **10** registered methods listed |
| twoStacksRotate parameter discovery | size={100,1000,10000,100000} |

Surefire XML confirms the test totals. Shared stack contracts have eight tests
inherited twice; shared queue contracts have eight inherited three times.
Module test sources declare 56 @Test methods, expanded to 80 invocations by
inheritance. The seeded Java models execute 3000 operations per stack/queue
implementation, and bounded-deque checks use capacities 1,2,3,8 with 3000 steps
each. They check order, size, sentinels and structural invariants.

The existing Java next-greater oracle checks all 1093 arrays of lengths 0..6
over {-1,0,1}; the sliding-window oracle checks 6015 array/window combinations.
Other Java tests cover wrapped growth, transfer ordering, snapshot identity,
integer overflow/operand order and deep iterative delimiters. These are finite
execution checks, not proofs of every asymptotic bound or resource-failure path.

After the successful normal test suite, packaging deliberately skips further
test execution. Discovery verifies registration and parameters; it is not a
timed benchmark. No new performance score, profiler result, coverage percentage
or claim of testing profile-only fixtures is made in this pass.

## Final audit and preserved history

The final repository audit checks **295 Markdown files / 1155 relative
file targets**, with zero missing destinations and balanced code fences.
`git diff --check` passes. No new local heading-fragment links are introduced;
external URLs and all inherited fragments are not exhaustively validated.
All sixteen changed files are Markdown within this module's documentation,
its package README and the project traceability review.

Inventory remains **100 production Java files / 115 JMH methods** repository-wide.
This module has **15 production Java files, 13 test source files, 56 declared
@Test methods, 2 JMH classes and 10 JMH methods**. Source/test/benchmark/build/CI
and historical-result paths are outside the changed file set.

The 2026-10-19 **418-test** implementation build remains historical evidence.
The [author's timing subset](../17-benchmarking/results/stacks-and-queues/README.md)
retains source `804f69f32b0da715ed41f390034523890c24bca5`, Oracle JDK 21.0.9,
JMH 1.37, 20 cells, 200 measurement and 120 warmup iteration averages. Eleven
errors reach at least 25% of their means. Current four-size defaults describe
40 possible configurations, not additional measured cells.

Stack pairs restore contents; queue pairs rotate them while preserving size
and identities. Array growth is excluded, two-stack transfers are averaged,
and linked allocation sites remain subject to JIT optimisation. The historical
run does not isolate those causes, transfer tails, memory use or concurrency.
No new JSON/log/CSV artifact is versioned; generated target output stays build
output. New functional verification does not change the original timing source.

## Next ordered review

Continue with **08 - Hash Tables**, keeping its implementation, test and research
provenance. This review completes the scoped Stacks and Queues documentation
pass; it does not add a new numbered implementation module.
