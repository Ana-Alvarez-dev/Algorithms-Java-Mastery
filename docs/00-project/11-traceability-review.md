# Project Traceability Review

Reviewed on **2026-10-19**, starting from main commit
`494c243488b69c2703357de6bf1bbaa581fe9042`.

A later **2026-10-28 Foundations documentation review**, based on main
`d70d8d1c61da6e22394edadaca84d50d4255cc84` (PR #98 merge), is recorded
[separately](../01-foundations/documentation-review.md). It reads the eight
Foundations notes and README, applies eight scoped corrections and preserves
the historical build, coverage and benchmark evidence below. Complexity and
later modules are not declared re-reviewed by that follow-up.

A **2026-10-28 Complexity documentation review**, based on main
`dfb60afc3b8e60231ec421a8211073fee7677385` (PR #99 merge), follows
[separately](../02-complexity/documentation-review.md). It reads the ten notes
and README, corrects the scoped models/examples and records independent checks.
It changes no executable source or historical benchmark evidence; later modules
are not declared re-reviewed by this follow-up.

A **2026-10-28 Arrays documentation review**, based on main
`ee2383bbe83dd9539171454eeb182f81c150ff83` (PR #100 merge), follows
[separately](../03-arrays/documentation-review.md). It checks the module's
technical claims against all thirteen Arrays classes, their tests and JMH
bodies, and adds an applied contract map. One source Javadoc is clarified;
executable Java, tests, fixtures and historical research data remain unchanged.

A **2026-10-28 ordered Searching documentation review**, based on main
`af63005fa6ac23c68de9c20d062443a67821db47` (PR #101 merge), follows
[separately](../04-searching/documentation-review.md#ordered-documentation-review-follow-up-2026-10-28).
It adds scoped S09-S16 clarifications and an applied contract/proof map while
preserving S01-S08 history. Only Markdown changes; source, tests, JMH fixtures
and the historical 65-row measurement record are unchanged.

A **2026-10-28 ordered Sorting documentation review**, based on main
`8d8a5beec76c97583d721c36ab3903aa1fd267b7` (PR #102 merge), follows
[separately](../05-sorting/documentation-review.md). It adds scoped SO01-SO12
corrections and a current contract/proof map. Only Markdown changes; Java,
tests, JMH fixtures and the historical 48-configuration JDK 25 record are
unchanged. Later modules are not declared re-reviewed by this follow-up.

A **2026-10-28 ordered Linked Lists documentation review**, based on main
`20e7f2823fc0e9e620029177fceb111961e80811` (PR #103 merge), follows
[separately](../06-linked-lists/documentation-review.md). It adds scoped
LL01-LL10 corrections and a current contract/proof map, records actual Java 21
verification and clarifies one source Javadoc. Executable Java/tests and the
historical 30-configuration timing record are unchanged; later modules are not
reclassified as re-reviewed by this follow-up.

A **2026-10-28 ordered Stacks and Queues documentation review**, based on main
`6db7b24a9660ac9fd44ce42f941f8307bc2c2d22` (PR #104 merge), follows
[separately](../07-stacks-and-queues/documentation-review.md). Scoped SQ01-SQ10
corrections and the contract/proof map clarify endpoint policies, ring capacity,
two-stack amortisation and current algorithm invariants. Actual Java 21 tests
pass (710 overall, 80 module); only Markdown changes. Historical measurements
retain their source/runtime. Later modules are not declared re-reviewed.

A **2026-10-28 ordered Hash Tables documentation review**, based on main
`60ec9de663589f37bfa81c6d3b1fcd82176bcdaf` (PR #105 merge), follows
[separately](../08-hash-tables/documentation-review.md). Scoped HT01-HT10
corrections and a contract/proof map distinguish equality, cached spreading,
probe paths, live/used load, conditional expected costs and amortised rebuilding.
Actual Java 21 tests pass (710 overall, 47 module). Only Markdown changes;
historical timings retain their source/runtime, and later modules are not
reclassified as re-reviewed.

A **2026-10-28 ordered Trees documentation review**, based on main
`7182a9990e06dbd4e7d9dd82302ca32932b52d5a` (PR #106 merge), follows
[separately](../09-trees/documentation-review.md). Scoped TR01-TR12 corrections
and a contract/proof map distinguish proper standalone trees, keyed set updates,
AVL height, traversal/metric storage and heap growth/build workloads. Actual
Java 21 verification passes 710 tests overall, including 59 module invocations.
Only Markdown changes; both historical timing subsets retain their source
identities. Later modules are not declared re-reviewed.

A **2026-10-28 ordered Graphs documentation review**, based on main
`809deace4c37ba91bf45621265446644dd3392bf` (PR #107 merge), follows
[separately](../10-graphs/documentation-review.md). Scoped GR01-GR12 corrections
and a contract/proof map clarify immutable graph policies, source reachability,
FIFO Kahn, lazy Dijkstra, long totals and disconnected Kruskal forests. Actual
Java 21 verification passes 710 tests overall, including 48 module invocations.
Only Markdown changes; the two author timing records retain their source/runtime.
Later modules are not declared re-reviewed.

A **2026-10-28 ordered Recursion documentation review**, based on main
`6e8f87c1dcaae00d0d545d5ebb4ec539c48b33f7` (PR #108 merge), follows
[separately](../11-recursion/documentation-review.md). Scoped RE01-RE12
corrections and a contract/proof map clarify helper specifications, zero bases,
shared references, work versus depth, BigInteger costs and materialised output.
Actual Java 21 verification passes 710 tests overall, including 28 module tests.
Only Markdown changes; the historical 24-cell run retains its source/runtime.
Later modules are not declared re-reviewed.

A **2026-10-29 ordered Divide and Conquer documentation review**, based on main
`5fd81c44cd5df511bb6c553dcdad45b17c38a18d` (PR #109 merge), follows
[separately](../12-divide-and-conquer/documentation-review.md). Scoped DC01-DC10
corrections and a contract/proof map connect canonical maximum ranges, strict
inversions and three-way selection with reused Searching/Sorting cases.
Actual Java 21 verification passes 710 tests overall, including 18 module tests.
Only Markdown changes; the 48-cell author run retains its source/runtime.
Later modules are not declared re-reviewed.

A **2026-10-29 ordered Greedy documentation review**, based on main
`0bc276ff49a5cd6ae265f2c4968d2c507ad3e1d7` (PR #110 merge), follows
[separately](../13-greedy-algorithms/documentation-review.md). Scoped GE01-GE12
corrections and a contract/proof map distinguish safe choices and optimal
residuals, room overlap bounds, internal/public Huffman bases and change
heuristic limits. Actual Java 21 verification passes 710 tests overall,
including 27 module tests. Only Markdown changes; the 40-cell author run
retains its source/runtime. Later modules are not declared re-reviewed.

A **2026-10-29 ordered Backtracking documentation review**, based on main
`df79799f8d8412df840c6e9f5cd1588a35f0fe5c` (PR #111 merge), follows
[separately](../14-backtracking/documentation-review.md). Scoped BT01-BT10
clarifications and a contract/proof map derive restoration, canonical choices,
combination calls, queen masks, signed bounds and output costs. Actual Java 21
verification passes 710 tests overall, including 27 module tests. Only Markdown
changes; the 46-cell author run retains its source/runtime. Later modules are
not declared re-reviewed.

A **2026-10-29 ordered Dynamic Programming documentation review**, based on main
`bbab550e82a7768bdce789e646dd16621fa8d539` (PR #112 merge), follows
[separately](../15-dynamic-programming/documentation-review.md). Scoped DP01-DP10
clarifications and a contract/proof map derive sufficient states, cache costs,
row versions, knapsack mask ties and UTF-16 LCS witnesses. Actual Java 21
verification passes 710 tests overall, including 22 module tests. Only Markdown
changes; the 57-cell author run retains its source/runtime and per-row units.
Later modules are not declared re-reviewed.

A **2026-10-29 ordered Testing documentation review**, based on main
`cb6855da09f963097953ae958f62e1827ae861b8` (PR #113 merge), follows
[separately](../16-testing/documentation-review.md). Scoped TE01-TE10 corrections
and an applied evidence map distinguish oracle domains, ownership, invocation
counts and coverage gates; current aggregation and CI evidence replace stale
pending status. Actual Java 21 coverage verification passes 710 tests; the
separate benchmark profile passes 723 including 13 fixture invocations. Only
Markdown changes; the original red/green experiment and all timed results stay
dated and unchanged. Later modules are not declared re-reviewed.

A **2026-10-29 ordered Benchmarking documentation review**, based on main
`93fb7ce12d1f24ef2a930dcc2b4d2bf187b654f8` (PR #114 merge), follows
[separately](../17-benchmarking/documentation-review.md). Scoped BE01-BE12
corrections and an experiment/evidence map distinguish timed operations,
parameter cells, iteration statistics, fixture tests, diagnostics and provenance.
Actual Java 21 packaging passes 723 tests; discovery finds 40 classes, 115 methods
and 803 default parameter cells. Retained aggregation observations are reconciled
in Java without a new timing run. Only Markdown changes; historical evidence
retains its source/runtime and limits. Later modules are not declared re-reviewed.

A **2026-10-29 ordered Linux Environment documentation review**, based on main
`07539e468523e3f1a96a84949386907b1abeb67c` (PR #115 merge), follows
[separately](../18-linux-environment/documentation-review.md). Scoped LI01-LI12
corrections and a script/evidence map distinguish interpreter/root checks,
path/feature versions, failure statuses, permissions, option guards and artifact
identity. Actual verification passes 20 shell cases and 723 Java 21 tests, with
real preflight, unchanged status/digest and JMH discovery. Only Markdown changes;
original runtime/build/hash and author research stay dated and unchanged.
Module 19's existing workflow is recognised; its ordered review remains next.

This review checks the evidence chain for the executable modules and the
progression through **19 — CI/CD**. It is a source/evidence inventory,
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
through PR #81. Its selected scope and local verification are recorded below.
The first author-reported Recursion run now records all 24 current default cells
on JDK 21.0.9/JMH 1.37, with separately supplied source
`fe370a1953c089a55f8851399acc42a59e3d37b7` (PR #82 merge).

The Divide and Conquer contribution extends review from main
`be8c6cdf4cbbab3f760cbc22cbc5136151674b1f`, which integrated Recursion results
through PR #83. Local verification reported 2026-10-25 (UTC) is recorded below.
The first author-reported run, supplied on 2026-10-26, now records all 48 default
cells on JDK 21.0.9/JMH 1.37, with separately supplied source
`507b2537d3dedd3beb57900d3ee72d7858571c89` (PR #84 merge).

The Greedy contribution extends review from main
`9f65e53437bb7200f658a65f6fb795c20266f85c`, which integrated Divide and Conquer
research through PR #85. Its local verification reported 2026-10-26 is recorded
below. The first author-reported Greedy run now records all 40 default cells
on JDK 21.0.9/JMH 1.37, with separately supplied source
`674ab35b7d82f1a9ab7fbab63fa1a9e80253965c` (PR #86 merge).

The Backtracking contribution extends review from main
`605c299f11944e0234c1651c21d4191f07b3432f`, which integrated Greedy research
through PR #87. Its selected contracts and local verification reported
2026-10-26 are recorded below. The first author-supplied run now records all
46 default cells on JDK 21.0.9/JMH 1.37, with separately supplied source
`b62df1b84810dfe5dea023d5d00bac2eb05c0cd0` (PR #88 merge).

The Dynamic Programming contribution extends review from main
`da3db882a70e3cc59ee05491ee76d8d8c11b97ae`, which integrated Backtracking
research through PR #89. Selected scope and local verification reported
2026-10-26 are recorded below. The first author-reported run now records all
57 defaults on JDK 21.0.9/JMH 1.37, with separately supplied source
`d572651484f3ec6cffe03ad589bddd5a4a8fb8a3` (PR #90 merge).

The Testing contribution extends review from main
`8bacc90f00e4ad7dc998743d06be65ff7f5c04aa`, which integrated DP research through
PR #91. Verification reported 2026-10-27 records a coverage baseline, failing
duplicate regressions, a contract correction, valid future aggregation inputs
and the corrected full suite. Historical measurements retain their limitations.

The Benchmarking contribution extends review from main
`18a8ef53d4a1c4f1cd58c9caf22c72051be6b891`, which integrated Testing through
PR #92. The ten notes now use an applied protocol, with benchmark-profile
fixture tests, exact commands, discovery and local smoke. Corrected aggregation
author output now supplies all five means/errors and 80 printed iteration
averages on JDK 21.0.9/JMH 1.37, with 44-second completion. Execution source
attribution is now inferred from the author's supplied reflog (`5eee0dc` before
results merge `12f2973`); original jar hash is unavailable. Earlier numerical
evidence is not rewritten.

The Linux Environment contribution extends review from main
`5efd7f7b7cf5ddfa34a7380b01b6a7435299da8f` (PR #95, aggregation provenance).
Its applied preflight, twenty offline subprocess cases, wrapper portability
and local Java 21 build checks are recorded separately below. No old numerical
evidence or missing artifact identity is replaced.

The CI/CD contribution extends review from main
`a0e174db482164027b086a0ddb986bf136c38b4a` (PR #96, Linux Environment).
It implements the two-job Maven CI workflow and validates its local commands
and syntax. The previous absence of workflow source is addressed; the first
hosted run and repository enforcement remain separate evidence/configuration.

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
helpers. The matrix includes selected algorithms through module 15 and cross-cutting Testing/Benchmarking in modules 16–17. Profile-only fixture counts are explicit.

| Module | Java files | Executed tests | JMH methods | Evidence and current status |
|---|---:|---:|---:|---|
| 00–02: project, foundations, complexity | Conceptual | Not a standalone executable package | Not applicable | Methodology and analysis prerequisites; not falsely marked incomplete for lacking benchmarks |
| Arrays | 13 | 103 | 15 | [105 recorded means and research](../17-benchmarking/results/arrays/README.md); original errors/runtime/source identity unknown; historical aggregation violation retained, future fixture corrected by module 16 |
| Searching | 8 | 101 | 4 | [65 means/errors](../17-benchmarking/results/searching/README.md) and [documentation review](../04-searching/documentation-review.md); S01 duplicate tie corrected by module 16; sixteen new invocations included here |
| Sorting | 5 | 69 | 8 | [Implementation guide](../05-sorting/implementation-and-benchmark-guide.md), [48 reported configurations](../17-benchmarking/results/sorting/README.md); JDK 25.0.1, substantial uncertainty, execution SHA unknown |
| Linked Lists | 6 | 81 | 15 | [Implementation guide](../06-linked-lists/implementation-and-benchmark-guide.md), [30 reported configurations](../17-benchmarking/results/linked-lists/README.md); JDK 21.0.9, author-reported source `a16a431`, partial iterations and wide-error limitations recorded |
| Stacks and Queues | 15 | 80 | 10 | [Selected scope](../07-stacks-and-queues/implementation-and-benchmark-guide.md) and [20 reported configurations](../17-benchmarking/results/stacks-and-queues/README.md); JDK 21.0.9/JMH 1.37, author-reported source `804f69f`, all printed iteration values preserved; preliminary interpretation |
| Hash Tables | 8 | 47 | 9 | [Scope and contracts](../08-hash-tables/implementation-and-benchmark-guide.md), [18 reported configurations](../17-benchmarking/results/hash-tables/README.md); JDK 21.0.9/JMH 1.37, all printed iterations retained; author-reported source `03e6ed5` matches review; probing reinsertion source trace recorded |
| Trees | 11 | 59 | 12 | [Selected scope](../09-trees/implementation-and-benchmark-guide.md), [24 lookup results](../17-benchmarking/results/trees/README.md); JDK 21.0.9, all printed iterations retained; lookup source `c576cb7` from author reflog; [24 heap results](../17-benchmarking/results/trees/heap-first-run.md) from author-reported `ce621c6`; both initial subsets recorded with limits |
| Graphs | 12 | 48 | 6 | [Selected contracts](../10-graphs/implementation-and-benchmark-guide.md), [24 traversal results](../17-benchmarking/results/graphs/README.md), [8 weighted results](../17-benchmarking/results/graphs/weighted-first-run.md); JDK 21.0.9, author-reported source `00fc9de`, all errors/printed iterations retained with preliminary limits |
| Recursion | 7 | 28 | 8 | [Selected contracts](../11-recursion/implementation-and-benchmark-guide.md), [24 results and research](../17-benchmarking/results/recursion/README.md); JDK 21.0.9/JMH 1.37, author-reported source `fe370a1`, all errors/iterations retained; five wide-error cells and preliminary limits |
| Divide and Conquer | 3 | 18 | 6 | [Selected contracts](../12-divide-and-conquer/implementation-and-benchmark-guide.md), [48 results and research](../17-benchmarking/results/divide-and-conquer/README.md); JDK 21.0.9/JMH 1.37, author-reported source `507b253`, all errors/printed iterations retained; six wide-error cells and preliminary limits |
| Greedy | 5 | 27 | 5 | [Selected contracts](../13-greedy-algorithms/implementation-and-benchmark-guide.md), [40 results and research](../17-benchmarking/results/greedy/README.md); JDK 21.0.9/JMH 1.37, author-reported source `674ab35`, all errors/printed iterations retained; ten wide-error cells and preliminary limits |
| Backtracking | 4 | 27 | 8 | [Selected contracts](../14-backtracking/implementation-and-benchmark-guide.md), [46 results and research](../17-benchmarking/results/backtracking/README.md); JDK 21.0.9/JMH 1.37, author-reported source `b62df1b`, every error/printed iteration retained; two wide-error cells and preliminary limits |
| Dynamic Programming | 3 | 22 | 9 | [State/recurrence contracts](../15-dynamic-programming/implementation-and-benchmark-guide.md), [57 results and research](../17-benchmarking/results/dynamic-programming/README.md); JDK 21.0.9/JMH 1.37, author-reported `d572651`; mixed units, every error/printed iteration and eleven wide-error cells retained; reused Fibonacci is not a new file |
| Testing | 0 new production files | 16 added in Searching, not additive | 0 new | [Regression/coverage guide](../16-testing/implementation-and-verification-guide.md), [baseline/red/green evidence](../16-testing/verification-report.md); coverage profile and fixture validity, no separate production package |
| Benchmarking | 0 new production files | 13 additional under -Pbenchmark only | 0 new; 115 existing methods in 40 classes | [Applied guide](../17-benchmarking/implementation-and-benchmark-guide.md), [local verification](../17-benchmarking/verification-report.md); five-cell author output retained, source inferred from supplied reflog; original jar absent, historical data unchanged |
| Linux Environment | 0 new Java files; 2 Bash scripts | 20 shell subprocess cases, separate from JUnit | 0 new; environment/artifact preflight | [Applied guide](../18-linux-environment/implementation-and-verification-guide.md), [local checks](../18-linux-environment/verification-report.md); wrapper executable/LF, real Linux JDK 21 builds; author WSL availability unclaimed |
| CI/CD | 0 new Java files; 1 workflow | 20 shell cases, 710 coverage / 723 package JUnit invocations locally; not additive | Discovery only, no timing gate | [Applied guide](../19-ci-cd/implementation-and-verification-guide.md), [validation and hosted status](../19-ci-cd/verification-report.md); two jobs; PR/main hosted success, inspected artifacts and source identities |

There are **710 passing normal-suite tests** and **723 under -Pbenchmark**
(the same 710 plus thirteen fixture checks). The normal count compares with **694** before
Testing, **672** before Dynamic Programming, **645** before Backtracking, **618** before Greedy, **600** before Divide and Conquer, **572** before Recursion, **524** before Graphs, **465** before Trees, **418** before Hash Tables
and **338** before Stacks and Queues. Counts do not demonstrate complete behavioural coverage
or establish complete behaviour outside the selected scope. The Testing row
is cross-cutting; its sixteen invocations are already counted in Searching.

## Rechecked Gaps

### Searching: duplicate tie contract

`ClosestValueSearch` promises the lowest index among equally close values.
The following historical counterexamples were re-executed against the pre-fix
source; module 16 now returns the required indices in every row:

| Input | Target | Actual index | Contract-required index |
|---|---:|---:|---:|
| `[10,20,20,20,30]` | 20 | 2 | 1 |
| `[10,20,20,40]` | 25 | 2 | 1 |
| `[10,20,20]` | 100 | 2 | 1 |

**Corrected in module 16.** The original 85 Searching tests did not establish
this duplicate policy. Twelve new named cases exposed six pre-fix failures;
four additional tests use a linear oracle, exhaustive/seeded inputs, safe
metamorphic relations and fixture isolation. The corrected 101 Searching
invocations pass. Reused lower bounds enforce both distance and index, with
empty/minimum fast paths and logarithmic worst work. The
[Testing report](../16-testing/verification-report.md) preserves before/after
coverage: the old method was already 100% covered by recorded counters.
The original mismatch remains historical evidence rather than an open code bug.

### Arrays: invalid aggregation benchmark inputs

`ArrayAggregation.sum` documents that the mathematical sum must fit in an int.
The historical benchmark generated `0..n-1`, violating that precondition at
its two largest sizes. Rechecking those original inputs produced:

| Size | Mathematical sum | Actual Java int result |
|---|---:|---:|
| 100,000 | 4,999,950,000 | 704,982,704 |
| 1,000,000 | 499,999,500,000 | 1,783,293,664 |

This is a benchmark-domain violation, not evidence that the implementation is
wrong for inputs within its documented domain. **The future fixture is now
corrected by module 16** to value[i]=i%100 with a validated size cap and exact
default sums through 49,500,000. An independent long/formula probe and smoke
verify the domain. Historical means retain their limitation. Module 17 now
records five corrected-protocol observations and all printed iterations, with
source `5eee0dc` inferred from the supplied reflog and original jar hash
unavailable; no old value is relabelled.

### Build and CI evidence

The POM targets Java 21. Maven's runtime and the Java executable launching JMH
must be recorded separately. The historical Sorting run used JDK 25; Linked
Lists used JDK 21. Those environments are kept distinct.

No `.github/workflows` files were versioned in the reviewed base trees through
module 18. Module 19 now adds the actual two-job Maven CI definition, addressing
that source-level gap. The subsequent [hosted record](../19-ci-cd/first-hosted-run.md)
now confirms successful PR/main runs and inspected artifacts. Local checks
remain local evidence; branch-protection enforcement is not inferred from CI.

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
unchanged. Local verification is not a new CI status. The subsequent author
run completes all three Result/Interpretation fields using real measured output;
no smoke timing is substituted for it.

## Divide and Conquer Verification and Review

Verification reported **2026-10-25 (UTC)**, using **OpenJDK 21+35-2513**, passed
normal Maven tests and benchmark-profile clean package: **618 tests**, zero
failures/errors/skips, including **18 new module tests** (prior Recursion 600).
The guide links all nine numbered documents to contracts, decomposition,
pseudocode, manual traces, invariants/induction, decreasing measures and costs.

| Check | Evidence |
|---|---|
| Canonical maximum subarray | Quadratic interval oracle for all 3,280 arrays of length 0..7 over {-1,0,1}; 500 full-int seeded cases; negative/zero/crossing/tie examples; 200,000-value exact long sum and nonmutation |
| Strict inversions | Independent direct-pair oracle on all small arrays and 500 seeded cases; 200 duplicate-aware reversal identities; descending 100,000 count 4,999,950,000 and nonmutation |
| Order statistic | Sorted-copy ranks, full permutation and global partition inequalities for all 21,324 small array/rank cases; every rank in 200 seeded arrays; extreme/structured/large equal inputs; invalid ranks rejected before mutation |
| JMH | Six discovered methods, all 48 short smoke configurations completed; independent prefix-minimum/pair/sorted-copy probe verifies all defaults three calls each and unchanged trial inputs |
| JavaDoc | Package passes doclint excluding missing tags |

Three production files add selected examples without cloning existing binary
search or sorts. Earlier production/tests/POM and measured reports remain
unchanged. The conceptual review supplies polynomial gaps and case-three
regularity for the Master theorem, distinguishes tight local work from upper
bounds, and separates conditional balance from average-case Quicksort analysis.
The actual Hoare source returns a boundary, not a final pivot rank, and recurses
only on the smaller side: O(log n) worst logical depth despite quadratic time.
Three-way selection keeps one side with O(1) state, but its deterministic
median-of-three pivot has no arbitrary-input expected-linear guarantee.

Maximum-subarray nonempty/tie/Optional policy, duplicate-strict inversion counts,
long arithmetic and selection mutation are explicit. Work, depth, total source
record constructions, scratch arrays and live state are not interchangeable.
Benchmarks include full differing contract work, including both selection
clones; no score or profiler/cause claim is invented. Parallel/Strassen,
randomised/median-of-medians selection and future paradigms remain extensions.
The next numbered module is **13 - Greedy Algorithms**, then Backtracking and
Dynamic Programming. Local checks do not establish CI status or fix the open
earlier Arrays/Searching findings. Smoke is separate from measured research.

## Greedy Verification and Review

Verification reported **2026-10-26**, using **OpenJDK 21+35-2513**, passed normal
Maven tests and benchmark-profile clean package: **645 tests**, zero failures/
errors/skips, including **27 new module tests** (prior Divide and Conquer 618).
The guide connects all nine conceptual documents to precise objectives,
contracts, pseudocode/traces, exchanges, lower bounds, invariants and evidence.

| Check | Evidence |
|---|---|
| Interval domain | Two tests: nonempty half-open intervals, signed/extreme times, rejection without subtraction |
| Activity selection | Six tests: all 1,024 sets from ten intervals on endpoints 0..4 against exhaustive compatible subsets, 200 seeded lists, touching/ties/duplicates, ordered-domain and ownership checks |
| Room assignment | Six tests: all small sets plus 500 seeded schedules against maximum overlap; all original positions assigned legally, deterministic room ties, immutable output |
| Huffman | Seven tests: all 1,093 positive weight arrays length 0..6 against all full-binary-tree root partitions; prefix freedom/recovered cost, 100 seeded alphabets, singleton/empty, uniform/skew/extreme frequencies and deterministic immutable maps |
| Greedy change | Six tests: suboptimal {1,3,4}/6 and incomplete {3,4}/6 counterexamples; binary coins match exact test-only DP for amounts 0..4,096; invalid/empty/duplicates and compressed MAX_VALUE quantity |
| JMH | Five discovered methods, all 40 short smoke cells complete; reference DP/sweep/sorted-multiset probe checks every default three calls each and trial nonmutation/repeatability |
| JavaDoc | Package passes doclint excluding missing tags |

Five production files add selected examples without duplicating Graphs' Dijkstra/
Kruskal or copying activity selection under another name. Interval scheduling
and activity selection share a cardinality objective; room partitioning retains
every interval and minimises rooms. The proof matches a simultaneous-overlap
lower bound, and the heap retains one latest finish per allocated room, including
idle ones. Scalar greedy state is not the complete public space bound when
snapshots, sorting workspace and immutable output are materialised.

Huffman has positive int frequencies, opaque int symbols, deterministic ties,
empty/one-bit-singleton policies, exact BigInteger weighted length and explicit-
stack code generation. Materialised code strings add O(L) state/work to tree/
queue bounds. Cost-only heap/two-queue workflows omit codewords and are not full
compression; heap leaves are inserted individually rather than linear heapify.
CoinChange exposes payment/remainder validity without claiming arbitrary-system
optimality or completeness. Tests include actual counterexamples rather than
promoting that heuristic's success on canonical examples to a general theorem.

The document review scopes safe choices to the specified rule/objective and
corrects output-space, queue-construction and next-module claims. The next
numbered module is **14 - Backtracking**, then **15 - Dynamic Programming**.
Weighted scheduling, knapsack, matroids, full compression and automatic coin
classification remain extensions. Earlier source/tests/POM, measured reports
and open findings are unchanged. Local checks do not establish CI status;
smoke is separate from controlled author research.

## Backtracking Verification and Review

Verification reported **2026-10-26**, using **OpenJDK 21+35-2513**, passes normal
Maven tests and benchmark-profile clean package: **672 tests**, zero failures/
errors/skips, including **27 new tests** (prior Greedy 645). All nine numbered
notes now connect to the guide's contracts, pseudocode, traces, soundness,
completeness/uniqueness, termination, restoration and complexity arguments.

| Check | Evidence |
|---|---|
| Permutations | Seven tests: all 120 small multisets through length 7 against complete index permutations then value deduplication; BigInteger multinomials, 100 seeded arrays, 10!, immutable rows, caps and input preservation |
| Combinations | Six tests: all n<=12/k against bitmask subsets; BigInteger counts at n=18, 48,620-row boundary, Pascal identity, ordering, ownership and invalid domains |
| N-Queens | Seven tests: full column-permutation/leaf-only diagonal oracle through n=9; published ordinary counts through 12, 14,200-row boundary, reflection inclusion, caps and restoration |
| Signed subset sum | Seven tests: 1,093 signed arrays length <=6 and all targets -8..8 against independent reachable sets; 100 seeded full-int arrays, extreme long targets, signed overshoot and long sums, reversal/negation and nonmutation |
| JMH | Eight discovered methods; all 46 default smoke cells complete at one 50 ms warmup/measurement and one fork; separate independent probe checks every default three calls each |
| JavaDoc | Four production classes pass doclint excluding missing tags |

The review corrects duplicate-value generation, signed pruning, first-hit versus
full search, fixed-width masks and candidate-order claims. It distinguishes
visited states, candidate scans, output copying/retention and active depth.
Counts do not time enumeration; masks have constant state per frame but O(n)
depth. Educational caps are explicit API limits, not fixed resource guarantees.
CLRS Third Edition sections/pages are scoped to DFS and positive subset-sum
context, not claimed as a copied N-Queens/permutation algorithm; NIST and OEIS
provide separately identified primary references.

Four templates contain questions, expectations, timed work and limitations.
The [first Backtracking report](../17-benchmarking/results/backtracking/README.md)
now completes Result/Interpretation for **all 46 defaults**, split 12 permutation /
12 combination / 6 queen / 16 subset cells. It retains **460 measured** and
**276 warmup averages**, completion **6 min 42 sec**, in **microseconds/op**.
Separately author-reported source is `b62df1b84810dfe5dea023d5d00bac2eb05c0cd0`,
PR #88's merge; its tree matches reviewed source. JMH headers confirm 1.37,
JDK 21.0.9, 3/5 x 500 ms, two forks and one thread. Oracle/Windows 11 amd64,
es_AR/UTF-8 metadata was separately supplied. The full command, execution
timestamp, CPU/power, jar hash and profiler data are unavailable; source SHA
is not embedded by JMH and its capture time is not established by the text.

All means/errors match their result blocks; rounded statistics match printed
iterations within precision, and every 99.9% CI remains intact. Two errors
reach 25%: n=12/HALF unpruned combinations (38.3%, fork means 4.834/7.892 us/op)
and n=8 queen flags (29.5%). The n=12/HALF combination intervals overlap, so
the point means do not establish a precise ordering or equivalence. No outlier
is discarded. Tiny identical printed
iterations retain nonzero reported uncertainty instead of inventing precision.

Pruned combination means are lower at HALF/ALL, but TWO favours unpruned; one
avoided call need not reduce elapsed work. Masks and frequency counters have
lower means in matching subsets, but DISTINCT permutation candidate-check
counts agree, so reduced loop width alone cannot explain all gaps. Subset
bounds have lower miss means, while the early PRESENT witness favours the
reference without suffix preparation. Source prefix/candidate ledgers are
analytical, not machine events, measured allocations or exact timing shares.
Counts omit enumeration; no universal gain, significance/equivalence, CPU cause
or exponential/factorial fit is claimed.

This results update changes documentation/comments only, with no executable Java
change, new timed run or CI status. Earlier 672-test verification and tracked
Arrays/Searching findings remain distinct. Evidence stays Markdown without
JSON/log exports; smoke is excluded. Future runs retain their own source/settings
and all errors. The next module at that review was **15 - Dynamic Programming**;
its implementation contribution is recorded below.

## Dynamic Programming Verification and Review

Verification reported **2026-10-26**, using **OpenJDK 21+35-2513**, passes normal
tests and benchmark-profile clean package: **694 tests**, zero failures/errors/
skips, including **22 new tests** (prior Backtracking 672). All twelve study
notes link to the guide's contracts, state meanings, recurrences, pseudocode,
traces, induction, reconstruction ties and full work/space models.

| Check | Evidence |
|---|---|
| Fibonacci | Five tests: every exact long value 0..92 against BigInteger, reused memoised/iterative agreement, exact Cassini identity, invalid limits and interleaved boundaries |
| Knapsack | Nine tests: all 7,381 arrays through four items (weights 1..3, values 0..2) at nine capacities, against complete subsets and numeric-mask tie oracle; 100 seeded inputs, greedy/ascending-loop counterexamples, long sums, grid/item caps, ownership and metamorphic checks |
| LCS | Eight tests: all 3,969 pairs of binary Strings through length five against independent subsequence enumeration; 100 seeded pairs, witness legality/ties, UTF-16/surrogate policy, 512-unit boundaries, symmetry/common-suffix identities and repeatability |
| JMH | Nine discovered methods, all 57 short smoke cells; independent probe checks every default three times against BigInteger, unit-weight optimum and matching-position LIS references with unchanged trial inputs |
| JavaDoc | Three production classes pass doclint excluding missing tags |

Three new production files add full-table Fibonacci, exact 0/1 knapsack values/
plans and LCS lengths/sequences. Memoised/rolling Fibonacci is reused from
Recursion; its source and earlier measurements are unchanged. Knapsack uses
positive weights, nonnegative values, long sums and shared explicit grid caps.
LCS uses Java UTF-16 units; deterministic witness ties and lone-surrogate
semantics are explicit. Rolling values/lengths do not retain a free witness.

The review corrects pseudopolynomial capacity bounds, dense memo preparation,
state sufficiency, zero sentinels and dependency versioning. Fibonacci is value
computation, LCS is optimisation, and DP also supports counting/feasibility.
CLRS Third Edition Chapter 15 and §16.2/exercise 16.2-2 are separately identified;
knapsack is not misattributed to a complete Chapter 15 worked algorithm.

The [first DP report](../17-benchmarking/results/dynamic-programming/README.md)
completes all three templates with **57 means/errors**, **570 measured** and
**342 warmup averages**, completion **8 min 30 sec**. Source
`d572651484f3ec6cffe03ad589bddd5a4a8fb8a3` is separately author-reported checkout
attribution (PR #90 merge), whose tree matches reviewed implementation. Headers
confirm JDK 21.0.9/JMH 1.37, one thread, 3/5 x 500 ms and two forks. Fibonacci
ns/op stays separate from knapsack/LCS us/op; no observation is silently converted.
The full command, execution timestamp, CPU/power, jar hash and profiler data are
unavailable. Earlier OS/vendor/locale setup is contextual rather than newly
supplied metadata; the SHA is not embedded by JMH or tied to a capture timestamp.

All means/errors agree with result blocks and parameters; rounded means, extrema
and sample deviations match printed observations. Eleven errors reach 25%:
four Fibonacci and seven LCS, none knapsack. Table Fibonacci n=20 has 163.7%
error, a 347.118 ns/op measured iteration, fork means 127.539/45.845 and literal
CI lower marker `? 0`. All remain. Twelve fully numeric matching-method CI pairs
overlap; two n=20 pairs instead have a nonnumeric boundary. No precise table
ranking or significance/equivalence follows from those point means.

Rolling point means are lower for all eight knapsack fixtures, while memo/table
order is mixed and five pairs overlap. LCS memo/rolling depends on shape:
IDENTICAL/SHIFTED favour memo means, large DISJOINT/RANDOM favour rolling.
Source ledgers distinguish Fibonacci additions/calls, knapsack dense cells/
reachable states/updates and LCS diagonal/full-grid dependencies. Dense cache
preparation persists for a sparse search; source counts are not profiler events,
actual allocations/bytes or isolated elapsed-cost shares. Witnesses are not
timed. No universal ratio, CPU/cache cause or fitted exponent is claimed;
knapsack's numeric capacity bound stays pseudopolynomial.

This results update changes documentation/comments only, with no executable Java
change, new timed run or CI status. Earlier 694-test verification and tracked
Arrays/Searching findings remain distinct. All evidence stays Markdown without
JSON/log; smoke and prior reports remain separate. Further experiments retain
their own source/settings and every error. The next module is **16 - Testing**.

## Testing Verification and Review

Verification reported **2026-10-27**, with OpenJDK 21+35-2513/Maven Wrapper 3.9.11,
records the baseline/red/green chain in the
[Testing report](../16-testing/verification-report.md). Production/tests match
the base for the 694-test baseline, with only optional JaCoCo configuration
added. ClosestValueSearch had 85/85 instructions, 14/14 branches and 21/21 lines
covered, yet six of twelve new named cases failed before the correction.

The corrected suite passes **710 tests** in normal, benchmark-package and
coverage verification, zero failures/errors/skips. The sixteen new Searching
invocations include 8,712 exhaustive queries, 300 seeded arrays, safe translation/
scaling and a copying-builder isolation check. All eleven Testing notes link
contracts, partitions, oracle independence, parameterisation, exceptions,
ownership, fixture validation and coverage limits to actual code/evidence.

JaCoCo 0.8.13 is an optional coverage-only profile, generating HTML/XML under
ignored target with no percentage gate or excluded production scope. Actual
135-class counters and source changes are identified; no path/mutation score
or correctness proof is inferred. The lower-bound helper's JavaDoc markup is
repaired without changing its executable behaviour. Searching JavaDoc passes
doclint excluding missing tags. Historical docs/results remain identified.

The aggregation benchmark uses contract-valid modulo-100 inputs; all five
default fixtures match long/formula sums and preserve inputs across three calls,
and five short smoke cells complete. Existing means remain historical and a
separate corrected author run was needed at that review. The module 17
follow-up below now records supplied observations with source attribution open.
No new JMH family is added to time tests.
Coverage instrumentation and performance measurement remain separate. The CI
workflow gap is still open; local verification is not CI. Evidence stays Markdown
without versioned JSON/log/new CSV. This supplied the prerequisite for
**17 - Benchmarking**, reviewed next.

## Benchmarking Verification and Review

Reviewed on **2026-10-27**, starting from Testing merge `18a8ef5` (PR #92).
The [applied guide](../17-benchmarking/implementation-and-benchmark-guide.md)
and [verification report](../17-benchmarking/verification-report.md) distinguish
normal tests (710), benchmark-profile tests (723), discovery, smoke and research.
The thirteen new checks use the actual aggregation setup and independent
long/formula sums, bounded values, repeated-call preservation, size rejection
and fresh trial arrays. They are test sources, not benchmark jar payload.

All ten notes now connect theory to this protocol and prior module examples.
There are still 40 JMH classes and 115 methods. The corrected five-size
aggregation changes its default from one to two forks; measured scan and
modulo-100 payload are unchanged. Explicit commands preserve Java 21, 3/5 x
500 ms, one thread, AverageTime ns/op, source/dirty state and jar SHA-256.
Profiler diagnostics have a separate run identity; local compatibility checks
supply no performance conclusions. The
[first supplied aggregation record](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
now retains five means/errors, 50 measured and 30 warmup iteration averages,
JDK 21.0.9/JMH 1.37 and 00:00:44 completion. All printed means/extrema/stdev/CI
reconcile within display rounding. Mean growth for tenfold n is 11.215, 10.033,
10.138 and 11.249; none of the errors reaches 25% (maximum 10.917%). These
observations are compatible with source linear work, not a complexity proof
or cache/GC/JIT diagnosis. The author's subsequently supplied reflog shows
`5eee0dc` immediately before results merge `12f2973`. The conversation places
the measurement before the results PR, supporting `5eee0dc` as an inferred
execution checkout. The reflog has no run/build timestamps or dirty state;
this is not an execution-time attestation. Executable aggregation source and
production/tests/POM are identical between those merges. The original jar
is absent according to the author's failed hash attempt, so its SHA-256 is
unavailable. Exact command/time and fresh machine metadata remain unknown.

Historical means/errors, censored intervals and source limitations remain
unchanged. The Arrays historical overflow limitation remains historical; future
fixture validity does not repair old data. Coverage is separate from JMH, and
no CI workflow/status or new JSON/log/CSV is introduced. Next numbered module
is **18 - Linux Environment**; source attribution is inferred and the original
artifact hash remains unavailable. These limits do not require inventing data
or rebuilding the old artifact to continue the curriculum.

## Linux Environment Verification and Review

Reviewed on **2026-10-27** from `5efd7f7` (PR #95). The
[guide](../18-linux-environment/implementation-and-verification-guide.md) applies
all ten notes to a concrete Linux/JDK preflight. The
[report](../18-linux-environment/verification-report.md) distinguishes twenty
offline shell cases using fake launchers from real Linux/JDK 21 verification.
The verifier reports source/dirty state and environment, resolves PATH versus
JAVA_HOME Java/Javac 21, optionally hashes a present jar, and rejects absent
required artifacts or injected option variables for ordinary timing capture.
It does not install, build, run a jar or change local configuration. Exit codes,
spaced paths and reporting boundaries are explicit.

The wrapper gains tracked executable mode without a content change; scoped LF
attributes cover mvnw/Linux scripts. Production Java, tests, POM and historical
results are unchanged. Real wrapper builds pass **710 normal / 723 profile
JUnit tests**; **20 shell cases are separate and non-additive to JUnit**.
Discovery retains 115 methods in 40 classes. A local metadata/hash check is not
a new research dataset, author Linux/WSL execution or passing GitHub Actions.
The author's existing Windows workflow remains available. Prior aggregation
source stays inferred from the supplied reflog and its old jar hash stays
unavailable; a fresh Linux artifact cannot replace that identity. Next numbered
module: **19 - CI/CD**; the workflow gap remains open.

## CI/CD Implementation and Verification

Reviewed on **2026-10-27** from Linux merge `a0e174d` (PR #96). The actual
[Maven CI workflow](../../.github/workflows/maven-ci.yml) addresses the earlier
source-level workflow gap. The [guide](../19-ci-cd/implementation-and-verification-guide.md)
and [report](../19-ci-cd/verification-report.md) separate implemented automation,
local command validation and the subsequently observed hosted executions.

Main pushes, main-target pull requests and manual dispatch select two Linux/
Temurin-21 jobs. Verify runs preflight, twenty shell cases and coverage-enabled
Maven verify, with no-tests failure and required HTML/XML reports. The dependent
package job checks out source separately, builds the benchmark profile without
coverage, captures run/source/dirty-state/environment/jar identity in generated
Markdown and checks discovery. Artifacts request seven-day retention; diagnostic
upload never masks verification failure. SHA-pinned official actions, read-only
permissions, timeouts and PR/ref cancellation are explicit. No deployment,
performance threshold or branch-protection setting is added.

Actionlint 1.7.12 and Bash syntax checks pass locally; all six workflow run blocks
execute successfully using local OpenJDK 21 and temporary transport settings.
Observed counts are **20 shell cases**, **710 coverage-enabled JUnit invocations**
and **723 benchmark-profile invocations**, with reports, provenance and discovery
checks passing. Production Java, tests, POM, prior scripts and historical data
are unchanged; repeated builds do not increase unique test counts. Hosted
checkout/setup/cache/uploads were not run by that local harness. The subsequent
[initial hosted record](../19-ci-cd/first-hosted-run.md) now confirms PR #97 and
main success. Main checkout 0d0ade1 and PR synthetic checkout 659cdb7 share tree
6334460b94457ed63c914cf55b61a6d65d0dff4d. Downloaded XML confirms 710/723 passing
JUnit invocations; the package jar digest matches its provenance and job log.
The two main jobs selected different Java 21 patches/images, explicitly retained.
The [closing review](12-closing-traceability-review.md) reconciles selected scope
and applies Searching S02-S08 documentation corrections. The unavailable
original aggregation jar hash remains unavailable; repository enforcement and
unmeasured research cells remain separate limits.

## Closing Follow-up

The [closing traceability review](12-closing-traceability-review.md) records the
100-source-file / 710-normal-test / 115-method inventory against hosted artifacts,
with thirteen additional package-profile cases and twenty separate shell cases.
Both hosted job conclusions are observed for merged main, with a clean actual
checkout and independently matched archive/jar identities. Scoped Searching
S02-S08 documentation actions are now applied and linked to current source and
analytical models; S01 retains its separate module 16 code/regression history.
No Java, test, POM, workflow or historical numerical record is changed by this
follow-up. The selected milestone is closed with explicit research, coverage,
source-attribution and enforcement limits, rather than claiming universal proof.

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

The [first Recursion report](../17-benchmarking/results/recursion/README.md)
now preserves all **24 current default cells**, **240 measurement** and **144
warmup averages**, with completion **3 min 35 sec**. Source
`fe370a1953c089a55f8851399acc42a59e3d37b7` is the author's separately supplied
checkout attribution (PR #82); it is not embedded in JMH or a jar hash. Headers
confirm JDK 21.0.9/JMH 1.37, one thread, 3/5 x 500 ms and two forks, in
**nanoseconds/op**, distinct from Graphs' microseconds. The full command,
execution timestamp, CPU/power, profiler data and jar hash are unavailable.

All three templates contain preliminary Result/Interpretation, supported by
every final mean/error, measured/warmup iteration, extrema, sample standard
deviation and printed 99.9% CI. Five errors reach 25% of their means: two
ArraySum, two iterative Factorial and one memoised Fibonacci cell, maximum
about 76.3%. No observation is discarded and no significance/equivalence or
precise universal speedup is claimed.

Source counts distinguish factorial products/helper calls, naive Fibonacci's
exponential total calls versus linear active depth, per-call memo allocation,
and balanced sum's linear total work versus logarithmic depth. They are not
measured machine events, exact frames or evidence of tail-call elimination.
The nearly flat loop Fibonacci means do not prove constant complexity. No
cache/GC/vectorisation cause or allocation rate is inferred from timings.

The selected initial grid is complete; performance for all valid inputs or the
other four Recursion classes is not claimed. Earlier 600-test verification,
open findings and future modules remain distinct. That Recursion results
update changed only documentation/comments, with no new timed run or CI status. All evidence stays
Markdown without JSON/log exports; future experiments need their own source,
settings, complete errors and controls.

The [first Divide and Conquer report](../17-benchmarking/results/divide-and-conquer/README.md)
now completes all three templates with **48 means/errors**, **480 measured**
and **288 warmup averages**, completion **7 min 4 sec**. Source
`507b2537d3dedd3beb57900d3ee72d7858571c89` is separately author-reported checkout
attribution, matching the PR #84 merge. It is not embedded in JMH or a jar hash.
Headers confirm JDK 21.0.9/JMH 1.37, one thread, 3/5 x 500 ms, two forks and
**microseconds/op**, distinct from Recursion's nanoseconds. The attachment
contains no command line or execution timestamp; CPU/power and profiler data
are unavailable.

All final means/errors agree with their result blocks. Rounded observation
means, extrema and sample standard deviations agree to printed precision;
printed 99.9% CIs and every warmup/measurement value remain intact. Six errors
reach 25% of means: two direct inversion and four selection/sort cells, maximum
about 74.5%. No outlier is discarded, and no significance/equivalence test is
claimed. Duplicate-heavy size-10,000 selection/sort intervals overlap.

Linear maximum-subarray means are lower for all matching fixtures; small
ordered direct counts and ordered copy-plus-sort have lower means in selected
comparisons. These are not general crossover thresholds or universal rankings.
Source ledgers distinguish pair visits, full merge/crossing scans, source
record constructions and sorted selection's single partition. They are not
measured CPU events, actual allocation rates or elapsed-time attribution.
Both selection workflow clones remain timed; no pure partition cost or
arbitrary-input expected-linear guarantee follows. Library paths were not
inspected or profiled, and two sizes do not prove a complexity exponent.

Initial default-grid evidence is complete, not all accepted inputs/ranks/seeds.
That Divide and Conquer results update changed documentation/comments only,
with no executable Java change, new timed run or CI status. Its earlier 618-test
verification and open findings stay
distinct. All evidence is Markdown without JSON/log exports. Further experiments
retain their own source/settings, complete errors and matched controls. Greedy
implementation/measurement, Backtracking and Dynamic Programming are separate
contributions; Divide and Conquer data is not reused as their performance data.

The [first Greedy report](../17-benchmarking/results/greedy/README.md) now
completes all three templates with **40 means/errors**, **400 measured** and
**240 warmup averages**, completed in **5 min 51 sec**. Source
`674ab35b7d82f1a9ab7fbab63fa1a9e80253965c` is separately author-reported checkout
attribution, matching the PR #86 merge; it is not embedded in JMH or a jar hash.
Headers confirm JDK 21.0.9/JMH 1.37, one thread, 3/5 x 500 ms, two forks and
microseconds/op. The output contains no command or execution timestamp;
CPU/power, generated-code inspection and profiler data remain unavailable.

All final means/errors match their blocks and parameters. Rounded observation
means, extrema and sample standard deviations agree to printed precision;
printed 99.9% CIs and every warmup/measurement value are retained. Ten errors
reach 25% of their means: seven activity sortAndSelect and three UNIFORM Huffman
cells, maximum about 48.6%. No outlier is removed and no significance/equivalence
test or universal speedup is claimed. UNIFORM/100 Huffman intervals overlap.

Presorted activity and two-queue Huffman means are lower in matching fixtures,
but order preparation is excluded for the former, and the latter omits codes/
compression. Room assignment places every interval and is not ranked against
subset selection; disjoint/touching timing confidence intervals overlap.
Source output/heap/merge ledgers distinguish n peeks/offers, n-r room reuses, n-1 Huffman merges
and 2n-1 heap offers. Those counts and derived expected costs are mathematical
evidence, not profiler events, actual allocations or elapsed-cost attribution.

The initial default grid is complete, not all Greedy inputs/APIs. Codebook
generation and CoinChange are not timed. That Greedy results update changed documentation/
comments only, with no executable Java change, new timed run or CI status.
Earlier 645-test verification, open findings and prior source provenance remain
distinct. All evidence stays Markdown without JSON/log. A safe-choice takeaway
now consistently names the objective/rule/residual state, matching its existing
introduction. Further controlled experiments retain their own source/settings
and all errors; their measurements are not reused for Backtracking or Dynamic Programming.

The source-to-test-to-experiment chain is inspectable for the selected modules.
The rechecked gaps remain tracked work, and conceptual future modules remain
distinct from executable completion.
