# Project Traceability Review

Reviewed on **2026-10-19**, starting from main commit
`494c243488b69c2703357de6bf1bbaa581fe9042`.

This review checks the evidence chain for the executable modules and the
progression through **11 — Recursion**. It is a source/evidence inventory,
contract spot-check and build verification, not a claim that every paragraph
of every future module has undergone a formal proof review.

An empirical follow-up supplied on the same date records the author's Stacks
and Queues execution from commit `804f69f32b0da715ed41f390034523890c24bca5`
(PR #71's merge). That source identity is distinct from the review's initial
base above and from the later commits documenting its results.

The Hash Tables contribution extends the review from main commit
`f26a7763413793df701fcfb68b53547a992a2222`, which merged the Stacks and Queues
results through PR #72. Its functional/harness verification is recorded separately
below. A subsequent author-supplied Hash Tables run records 18 DISTINCT_HASH
configurations on JDK 21.0.9. The author subsequently confirmed
execution source `03e6ed5e5649b4018b47f054319e0495676b7fcf`, matching the
reviewed PR #73 merge. This confirmation was supplied separately from JMH.

The Trees contribution extends the review from main commit
`3f1e5dfb6a0dc5144fa426585a0a00ba5eeeec60`, which merged the Hash Tables
source confirmation through PR #75. Its scope and functional/harness evidence
are recorded below. A later supplied lookup run records 24 configurations on
JDK 21.0.9. The author's subsequently supplied reflog identifies execution
source `c576cb79aa9747aced1cd041e97f64fc99e60010` (PR #76 merge), matching review.
The later heap run supplies 24 additional cells from source `ce621c6`, which
the author reported before that launch. Initial lookup and heap evidence now
cover 48 cells with separate source identities; results merge `ce621c6` is
later than lookup but is the author-reported checkout for the heap run.

The Graphs contribution extends this review from main commit
`aca933f9c15db0e977f64a6ea48ce5bb9b537b90`, which integrated Trees heap results
through PR #79. Its selected scope and local verification are recorded below;
A subsequent empirical follow-up supplied on **2026-10-24** records all 32
current default Graphs cells on JDK 21.0.9/JMH 1.37, with author-reported source
`00fc9de90757eab77fbf6f824132d9bbea3f37e2` (PR #80 merge).

The Recursion contribution extends review from main commit
`7ab257e7041d9e70207a54f876178c256ebe34d8`, which integrated Graphs results
through PR #81. Its selected scope and local verification are recorded below;
Recursion author research measurements remain pending.

## Required Chain

The [study methodology](03-study-methodology.md) and
[engineering standards](08-engineering-standards.md) require a connection from
problem and specification through strategy, pseudocode/traces, correctness,
complexity, Java, tests and justified measurement. Benchmark absence is not
itself a defect: a distinct empirical question must justify a benchmark.
Tests, timing, proof arguments and source provenance provide different evidence.

## Module Evidence Matrix

Counts below are **executed JUnit tests**, including inherited contract tests,
not source-file or assertion counts. Java source counts include interfaces and
helpers. The matrix includes the Recursion contribution in this branch.

| Module | Java files | Executed tests | JMH methods | Evidence and current status |
|---|---:|---:|---:|---|
| 00–02: project, foundations, complexity | Conceptual | Not a standalone executable package | Not applicable | Methodology and analysis prerequisites; not falsely marked incomplete for lacking benchmarks |
| Arrays | 13 | 103 | 15 | [105 recorded means and research](../17-benchmarking/results/arrays/README.md); original errors/runtime/source identity unknown; aggregation input violation remains |
| Searching | 8 | 85 | 4 | [65 means/errors](../17-benchmarking/results/searching/README.md) and [documentation review](../04-searching/documentation-review.md); closest-value duplicate tie mismatch remains |
| Sorting | 5 | 69 | 8 | [Implementation guide](../05-sorting/implementation-and-benchmark-guide.md), [48 reported configurations](../17-benchmarking/results/sorting/README.md); JDK 25.0.1, substantial uncertainty, execution SHA unknown |
| Linked Lists | 6 | 81 | 15 | [Implementation guide](../06-linked-lists/implementation-and-benchmark-guide.md), [30 reported configurations](../17-benchmarking/results/linked-lists/README.md); JDK 21.0.9, author-reported source `a16a431`, partial iterations and wide-error limitations recorded |
| Stacks and Queues | 15 | 80 | 10 | [Selected scope](../07-stacks-and-queues/implementation-and-benchmark-guide.md) and [20 reported configurations](../17-benchmarking/results/stacks-and-queues/README.md); JDK 21.0.9/JMH 1.37, author-reported source `804f69f`, all printed iteration values preserved; preliminary interpretation |
| Hash Tables | 8 | 47 | 9 | [Scope and contracts](../08-hash-tables/implementation-and-benchmark-guide.md), [18 reported configurations](../17-benchmarking/results/hash-tables/README.md); JDK 21.0.9/JMH 1.37, all printed iterations retained; author-reported source `03e6ed5` matches review; probing reinsertion source trace recorded |
| Trees | 11 | 59 | 12 | [Selected scope](../09-trees/implementation-and-benchmark-guide.md), [24 lookup results](../17-benchmarking/results/trees/README.md); JDK 21.0.9, all printed iterations retained; lookup source `c576cb7` from author reflog; [24 heap results](../17-benchmarking/results/trees/heap-first-run.md) from author-reported `ce621c6`; both initial subsets recorded with limits |
| Graphs | 12 | 48 | 6 | [Selected contracts](../10-graphs/implementation-and-benchmark-guide.md), [24 traversal results](../17-benchmarking/results/graphs/README.md), [8 weighted results](../17-benchmarking/results/graphs/weighted-first-run.md); JDK 21.0.9, author-reported source `00fc9de`, all errors/printed iterations retained with preliminary limits |
| Recursion | 7 | 28 | 8 | [Selected contracts, induction, recurrences and protocol](../11-recursion/implementation-and-benchmark-guide.md); seven examples and reference variants verified; measured author results pending |
| 12–19: later subjects | No production packages yet | No corresponding tests yet | None yet | Documentation represents a learning plan, not completed executable evidence |

There are now **600 passing repository tests**, compared with **572** before
Recursion, **524** before Graphs, **465** before Trees, **418** before Hash Tables
and **338** before Stacks and Queues. Counts do not demonstrate complete behavioural coverage
or resolve a known counterexample outside the selected implementation scope.

## Rechecked Gaps

### Searching: duplicate tie contract

`ClosestValueSearch` promises the lowest index among equally close values.
The following counterexamples were re-executed against this branch's unchanged
Searching production source:

| Input | Target | Actual index | Contract-required index |
|---|---:|---:|---:|
| `[10,20,20,20,30]` | 20 | 2 | 1 |
| `[10,20,20,40]` | 25 | 2 | 1 |
| `[10,20,20]` | 100 | 2 | 1 |

The existing 85 Searching tests pass but do not establish this missing duplicate
policy. A focused future fix should first add regression tests, then reconcile
the implementation and documented best/worst costs. This contribution does
not silently change Searching behaviour. It does not depend on that method.

### Arrays: invalid aggregation benchmark inputs

`ArrayAggregation.sum` documents that the mathematical sum must fit in an int.
Its benchmark generates `0..n-1`, violating that precondition at its two largest
sizes. Rechecking the original inputs produced:

| Size | Mathematical sum | Actual Java int result |
|---|---:|---:|
| 100,000 | 4,999,950,000 | 704,982,704 |
| 1,000,000 | 499,999,500,000 | 1,783,293,664 |

This is a benchmark-domain violation, not evidence that the implementation is
wrong for inputs within its documented domain. Correct the future benchmark
dataset or introduce a separately specified wider accumulator and remeasure.
Historical means must retain their limitation; do not relabel them valid sums.

### Build and CI evidence

The POM targets Java 21. Maven's runtime and the Java executable launching JMH
must be recorded separately. The historical Sorting run used JDK 25; Linked
Lists used JDK 21. Those environments are kept distinct.

No `.github/workflows` files are versioned in the reviewed base tree. Therefore
local successful builds are reported as local verification, not as passing CI.
The project's CI documentation describes intended engineering practice; adding
an actual workflow remains future work. No CI result was inferred from tests.

## Stacks and Queues Review Decisions

- The module README's illustrative `org.anaalvarez` paths were corrected to the
  actual `org.anaalvarezdev.algorithms.stacksandqueues` package.
- Every numbered module document links to a single guide defining executable
  scope, contracts, pseudocode, traces, invariants, proof arguments and costs.
- Dynamic stack/queue insertion costs explicitly distinguish an ordinary
  operation, a growth operation and amortised cost. Two-stack transfers have
  the same distinction.
- The Java Deque discussion distinguishes the interface from its implementations
  and documents strict versus sentinel operations and null policies.
- Non-null containers and absence sentinels are explicit choices, distinct from
  the preceding module's null-permitting linked sequences.
- Fixed-array stacks, other deque variants and advanced parsing/traversal remain
  explicit extensions. They are not counted as implemented.
- Benchmark templates contain questions, expectations, timed work and limitations.
  The empirical follow-up completes their Result/Interpretation fields with
  20 configurations and their errors; smoke times remain excluded.

## Stacks and Queues Verification

On 2026-10-19, using **OpenJDK 21+35-2513** on the verification host:

| Check | Observed evidence |
|---|---|
| Base build before new source | 338 tests passed; benchmark jar built |
| Normal Maven test lifecycle | 418 tests, zero failures/errors/skips |
| Benchmark profile clean package | 418 tests passed; executable JMH jar built |
| JMH discovery | All 10 new methods listed |
| Forked harness smoke | All 10 methods completed at size 100; one 50 ms warmup and measurement, one fork |
| Independent harness probe | All 40 method/size pairs checked; stack restoration, FIFO rotation, reference identity, counts and invariants verified; queue methods called 2n+3 times to cross transfer boundaries |
| JavaDoc | New package passes doclint with missing-tag checks excluded |
| Markdown references | Repository-relative Markdown link targets checked; no missing file/directory target in the final scan |

The 80 new tests include shared contracts across two stacks and three queues,
3,000 seeded steps per representation, and 3,000 steps for each bounded-deque
capacity 1/2/3/8. Next-greater indices are checked on all **1,093** arrays of
length 0–6 over `{-1,0,1}` against a quadratic oracle. Sliding maxima are checked
on all **6,015** nonempty array/window combinations in that domain against a
scanning oracle. Expression tests include malformed arity, operand order,
overflow, division by zero and Java whitespace; delimiter tests include deep
iterative input. These are functional checks, not statistical experiments.

The implementation verification produced no research timing results. The
subsequent author-supplied run records 20 configurations, 200 measurement and
120 warmup averages. Its JDK 21.0.9 machine is a separate environment from the
OpenJDK verification host above; no fresh local test run is inferred from it.

## Hash Tables Verification and Review

On 2026-10-19, OpenJDK 21 verification passed the 418-test baseline, then
**465 tests** after this contribution, including **47 Hash Tables tests**.
Normal tests and the benchmark-profile clean package passed. Nine new methods
were discovered; all 18 short smoke cases at size 100 across two hash modes
completed. A separate probe checked all **54 method/size/hash-mode combinations**
for query wrap, hit/miss behaviour, restored original references and mappings,
counts and structural invariants. JavaDoc passed doclint excluding missing tags.

The two tables inherit 12 shared tests and add representation-specific cases.
They are checked against HashMap with 2,500 seeded steps per representation/hash
mode. Four application algorithms use independent small-input oracles; TwoSum
also checks overflow, distinct positions and its exact tie policy.

Documentation corrections make expected versus amortised analysis explicit,
retain capacity c in sparse/tombstone bounds, and preserve the quadratic
worst-case probing rebuild. HashMap null/order policies and the preconditions
for quadratic/double probe coverage are distinguished from the selected API.
All ten numbered documents link to the authoritative implementation guide.
Future hash variants are not counted as implemented, and no smoke timing is
labelled a research result. Earlier Arrays/Searching findings remain unchanged.

## Trees Verification and Review

On 2026-10-19, using verification-host **OpenJDK 21+35-2513**, the 465-test
baseline built, then normal Maven tests and benchmark-profile clean package
passed **524 tests**, zero failures/errors/skips. The **59 Trees tests** include
10 shared ordered-set contract tests inherited by BST/AVL, concrete rotation/
delete cases, heap/model tests, traversals, metrics and selected heap applications.

| Check | Observed evidence |
|---|---|
| Ordered sets | 5,000 seeded TreeSet-model steps per representation; all 576 insertion/removal-order pairs over four distinct keys; inclusive neighbours, extremes, size, ordering, identity uniqueness and snapshots |
| Unbalanced BST | Sorted spine height, iterative queries/updates, successor repair including its right child |
| AVL | Four insertion rotations, deletion with zero-balance heavy child, cached height/local balance, 10,000-key sorted growth/drain and alternating extrema |
| Min-heap | 5,000 seeded PriorityQueue-model steps; growth/capacity boundaries, duplicate/extreme keys, child selection and independent snapshots |
| Small arrays | All 1,093 arrays length 0–6 over {-1,0,1}; build/drain/sort oracles; all 7,108 array/k combinations for KSmallest |
| Standalone trees | All 626 shapes through seven nodes; independent small traversal and all-pairs-distance metric oracles; 100,000-node explicit-stack cases |
| JMH discovery | Twelve methods, two classes, 72 default method/size/fixture configurations |
| Harness smoke | All 24 size-100 cases across insertion orders/distributions; one 50 ms warmup/measurement, one fork |
| Independent harness probe | All 72 combinations, nine calls each; cursor wrap, shape/height, query outcomes, minima, restored multiset/capacity, independent builds and invariants |
| JavaDoc | Package passes doclint excluding missing tags |

The guide connects selected contracts to pseudocode, concrete traces, invariants,
termination and complexity. AVL logarithmic height uses the minimum-node
recurrence; deletion repair is distinguished from insertion-only rotation counts.
The docs correct an inaccurate implication that balanced full traversal becomes
logarithmic, separate output from frontier space, account for a growing offer's
linear copy, and distinguish inclusive floor/ceiling from strict successor/
predecessor and root poll from arbitrary-key deletion. Proper-tree identity and
edge-height conventions are explicit. The README's misnumbered contents are
repaired; all thirteen conceptual documents link to the authoritative guide.

Red-black/B-trees, tries, LCA, graph/scheduling/expression applications and a full
generic collections API remain extensions. Earlier executable packages and POM
are unchanged; the recorded Arrays/Searching gaps remain tracked. No fresh CI
result is inferred. Local smoke and functional probes do not supply research
timings. A subsequent author lookup
run completes the tree lookup template with 24 cells and their errors. A later
heap run completes the other template with 24 cells, retaining its own source,
JVM metadata, errors and printed iterations. The earlier discovery list stays
distinct from that actual heap measurement.

## Graphs Verification and Review

On **2026-10-20 UTC**, verification-host **OpenJDK 21+35-2513** passed normal
Maven tests and benchmark-profile clean package: **572 tests**, zero failures,
errors/skips, including **48 Graphs tests** (the prior Trees base recorded 524).
The guide maps all twelve conceptual documents to selected APIs, pseudocode,
traces, invariants, termination, cost models, tests and justified experiments.

| Check | Evidence |
|---|---|
| Immutable representations | Eight shared tests per list/matrix; 400 seeded graph input cases; loops, direction, duplicates, invalid IDs/rows, independent snapshots and iterator exhaustion |
| Source traversal | All 512 directed three-vertex graphs with loops at every source in both representations; BFS all-pairs oracle and small recursive DFS order oracle |
| Components / DAG | All 64 undirected four-vertex graphs; directed transitive-closure cycle oracle and edge precedence; empty DAG distinguished from cycle |
| Deep path | 100,000 vertices, explicit DFS frames and BFS distances |
| Dijkstra | 400 seeded six-vertex graphs / 2,400 source cases against repeated relaxation; zero cycles, stale queue entries, negative edges unreachable from source and long costs |
| Kruskal / disjoint sets | 192 weighted small graph cases against exhaustive subset enumeration, disconnected/negative/loop/tie/long-total cases; 1,000 seeded unions against naive labels |
| JMH | Six discovered methods, 16 short smoke cells and all 32 default combinations checked three calls each against functional/reference oracles |
| JavaDoc | New package passes doclint excluding missing tags |

The selected graph APIs are immutable and fixed to int vertices. Unweighted
input duplicates collapse, weighted pairs are rejected, and each public result
has independent arrays or immutable lists. DFS iterators avoid copying active
rows; Kahn uses local indegrees/FIFO, Dijkstra has lazy duplicate queue entries,
and Kruskal returns a forest on disconnected input. Long sums, global negative
validation and source/empty-result policies are explicit.

The guide accounts for M constructor records, V initialisation, reachable row
scans, output allocation and conservative union-by-size height bounds. Indexed-
heap queue space is not assigned to lazy Dijkstra. Generic examples, alternative
algorithms/SCC and graph-domain services remain extensions, not production
completion. Earlier source/tests/POM and tracked Arrays/Searching findings stay
unchanged. These are local checks; no new CI result is inferred. Smoke and
reference probes are not research timing.

## Recursion Verification and Review

Verification reported **2026-10-24**, using host **OpenJDK 21+35-2513**, passed
normal Maven tests and benchmark-profile clean package: **600 tests**, zero
failures/errors/skips, including **28 Recursion tests** (prior Graphs base 572).
The guide maps ten conceptual documents to contracts, pseudocode, traces,
induction/accumulator invariants, decreasing variants, cost ledgers and evidence.

| Check | Evidence |
|---|---|
| Factorial | Exact BigInteger products for all 21 accepted inputs; ordinary/tail/loop boundaries and rejection |
| Fibonacci | Exact BigInteger sequence through 92; all 31 naive inputs; work cap distinct from numeric range |
| Power | 1,105 small base/exponent cases against BigInteger.pow; large exact values and MAX exponent with bounded-result bases |
| Array sum | 1,093 arrays / 27,064 slices; full-int extremes, 128 seeded slices, 200,000-value exact long sum and nonmutation |
| Palindrome | Independent reversed-copy oracle, all small arrays, million-value symmetry and first/last pair mismatches |
| GCD | BigInteger.gcd for 6,561 small signed pairs plus 5,000 seeded int pairs; zero and MIN_VALUE magnitude |
| Hanoi | Legal peg simulation for 0..12 and 16 disks, optimal move count/final state, immutable output and rejection |
| JMH | Eight discovered methods, all 24 short smoke cells and 24 default combinations checked three calls each against independent references |
| JavaDoc | Package passes doclint excluding missing tags |

The guide separates primitive numeric limits from work/output caps; no shared
memo cache or mutated inputs are exposed. ArraySum returns long and does not
silently fix or relabel the earlier Arrays aggregation benchmark. It corrects
recurrence tables that assigned n log n to any two half-sized calls, accounts
for output and BigInteger bit costs, and distinguishes depth from total calls.
Conservative Java tail analysis makes no constant-stack promise; iteration's
space depends on explicit data structures, not merely its syntax. No stack-depth
limit is inferred by deliberately overflowing the runtime stack.

Conceptual mutual recursion/frameworks and full later paradigms remain extensions.
Prior code/tests/POM, measured results and open Arrays/Searching findings remain
unchanged. Local verification is not a new CI status, and no smoke timing fills
the pending author Result/Interpretation fields.

## Evidence Policy and Next Action

The repository retains results in Markdown, with existing historical CSV
transcriptions where already present. JSON/log exports were removed by PR #70
at the author's request; Git history is not rewritten. A final table can retain
means/errors but cannot reconstruct unavailable per-fork observations.

The [20-configuration initial run](../17-benchmarking/results/stacks-and-queues/README.md)
now records source SHA, headers, final table, completion marker and all printed
warmup/measurement iterations in Markdown. Eleven errors reach at least 25%
of their means; CPU details and an intact command line remain unavailable.
Future runs should follow the
[evidence protocol](../07-stacks-and-queues/implementation-and-benchmark-guide.md#windows-execution-and-evidence)
and remain distinct from this initial subset. Larger sizes, growth and isolated
transfer latency remain separate research questions.

The [Hash Tables initial run](../17-benchmarking/results/hash-tables/README.md)
now retains all 18 configurations, 180 measurement and 108 warmup averages,
complete headers/result blocks and the 2 minute 39 second completion marker.
All final means/errors and min/max agree with the printed iterations. The
author subsequently confirmed execution source
`03e6ed5e5649b4018b47f054319e0495676b7fcf` (PR #73's merge), matching the
reviewed source. This author-reported identity is separate from console metadata.

Probing remove/restore increases from 53.530 +/- 1.553 to 360.300 +/- 4.195
ns/op. A source trace and temporary counter probe confirm that the duplicate
search after removal averages 51.5/501.5 slot visits in this contiguous fixture.
This is a work-count explanation, not a profiler-based attribution of exact
nanoseconds. Executable source is unchanged; tombstone correctness remains required.
CONSTANT_HASH, size 10,000, allocation and growth remain separate questions.
The template, guide and report carry these findings and provenance limits.
The source-to-run link now includes the author's SHA confirmation. No earlier
module's open findings or historical results are silently reclassified.

The [Trees lookup execution](../17-benchmarking/results/trees/README.md) now
retains all 24 initial cells, 240 measurement and 144 warmup averages, complete
headers/result blocks, captured lookup command and the 3 minute 35 second
completion marker. All printed means/extrema and final-table rows agree.
Four SHUFFLED AVL errors exceed 25% of their means; the largest is about 55.1%.
The sorted-BST source trace gives average successful visits (n+1)/2 per full
cursor cycle and n visits for the fixed upper miss. It explains path work,
not exact nanosecond ratios, CPU/cache causes or a fitted asymptotic exponent.

The author supplied local reflog evidence on 2026-10-19. It records the pull to
`c576cb79aa9747aced1cd041e97f64fc99e60010` at **14:01:52 -0300** and the later
pull to results merge `ce621c63fabc2fe04c180d71fd1f9e165cc8a447` at **22:02:31**.
The successful output was supplied between those entries. This identifies
`c576cb7` as the execution checkout, matching the reviewed PR #76 merge.
The older `3f1e5df` lacked Trees and belonged to the failed pattern match.
The report retains the checkout timeline; it is author-provided provenance,
not a source SHA or jar hash embedded by JMH. The 13 Trees production/JMH Java
files have identical executable source between `c576cb7` and `ce621c6`, with
only the lookup benchmark's research block comment changed. Numerical evidence
and executable behaviour remain unchanged by this provenance update.
The [heap initial run](../17-benchmarking/results/trees/heap-first-run.md) now
records all 24 cells, 240 measurement and 144 warmup averages, complete headers/
result blocks and its 3 minute 36 second completion marker. Source
`ce621c63fabc2fe04c180d71fd1f9e165cc8a447` was reported by the author **before**
launch; it is not inferred from a later results merge. Actual runtime is JDK
21.0.9/JMH 1.37 with the controlled 3/5 x 500 ms, two-fork, one-thread settings.
The first command line is truncated and CPU/profiler data remain unavailable.

Five heap errors reach at least 25% of their means; RANDOM size-100 JDK peek
has mean 5.012 and error 11.922 ns/op (237.9%). Its supplied CI lower marker
`? 0` is preserved as text. Descending construction means favour bottom-up;
source repeated-offer parent moves total 480/7987 at n=100/1000, while bottom-up
has a separate linear build proof. No precise ranking, exact CPU cause, formal
significance or asymptotic exponent is inferred from timings.

Both initial subsets now complete the two templates: **48 of 72 defaults**, with
480 measurement and 288 warmup averages preserved across distinct reports.
Size 10,000 remains unmeasured in both families. Source checkout attribution,
JVMs, all errors and the uncertainty limits remain inspectable; samples are
not pooled. Earlier Arrays/Searching findings remain unchanged. Keep later
runs separate in Markdown without JSON/log exports.

The author supplied both Graphs runs on **2026-10-24**, reporting source
`00fc9de90757eab77fbf6f824132d9bbea3f37e2` (PR #80 merge). The source matches
reviewed executable code; its identity is supplied separately, not embedded by
JMH. The [traversal report](../17-benchmarking/results/graphs/README.md) retains
24 cells, 240 measurement and 144 warmup averages, completion 3 min 36 sec.
The [weighted report](../17-benchmarking/results/graphs/weighted-first-run.md)
retains eight cells, 80 measurement and 48 warmup averages, completion 1 min
14 sec. Every mean/error agrees with the final table/result block; rounded
means, extrema and standard deviations match the printed observations.

All 32 current default cells now support both templates' Result/Interpretation.
Actual runtime is JDK 21.0.9/JMH 1.37, one thread, 3/5 x 500 ms and two forks;
scores are microseconds/op. Both initial command lines are truncated; CPU,
exact execution timestamps, jar hash and profiler data are not supplied.
Five cells have errors at least 25% of their means: three traversal cells and
two sparse Dijkstra cells. Those uncertainties remain in full, without deleting
outliers or claiming formal significance.

Source work counts distinguish list adjacency from full matrix rows; DENSE
also makes list edges quadratic in V. Current Kruskal sorts/scans all canonical
edges, even after spanning a connected graph: 4,950/499,500 attempts at V=100/
1,000, selecting 99/999. Its dense size-1,000 mean is about 164 ms relative to
a 500 ms measurement target; per-iteration invocation counts remain unknown.
Those facts are not profiler attribution or an asymptotic fit. Dijkstra and
Kruskal solve different problems and are not ranked as substitutes.

This completes initial default-grid evidence, not performance coverage of all
graphs or proof of correctness. Both run samples remain separate. The earlier
572-test verification and prior module findings remain unchanged; no executable
Java change or new timed run is introduced by recording these results. Future
experiments must retain their own source, settings, errors and complete evidence
in Markdown without JSON/log exports.

Recursion is ready for its
[initial 24-cell measurement](../11-recursion/implementation-and-benchmark-guide.md#jmh-design-and-windows-evidence-protocol)
after integration, using nanoseconds/op. Capture SHA before launch and before
pulling later results, complete JMH headers, every mean/error, available fork
iterations and completion marker. Retain its own source/runtime rather than
reusing Graphs' source or microsecond unit. Initial empirical fields stay pending.

The source-to-test-to-experiment chain is inspectable for the selected modules.
The rechecked gaps remain tracked work, and conceptual future modules remain
distinct from executable completion.
