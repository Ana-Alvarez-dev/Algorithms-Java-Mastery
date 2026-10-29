# Greedy Documentation Review

Reviewed on **2026-10-29 (America/Buenos_Aires)** from main
`0bc276ff49a5cd6ae265f2c4968d2c507ad3e1d7`, the PR #110 merge of the
[Divide and Conquer review](../12-divide-and-conquer/documentation-review.md).

## Scope and conclusion

This ordered pass checks selected technical passages across all nine numbered
notes and the module/guide/package entry points. It reads all five production
Java files, six test sources and four JMH sources (three benchmark classes plus
GreedyInputs), and checks the historical report's provenance/workload boundary.
The original module documentation has 2608 lines. This is a scoped technical
review of specifications, residual problems, proof bases, ownership, costs and
evidence, not a formal verification or exhaustive editorial pass over every
application mentioned in the notes.

Twelve findings apply corrections and consolidate current guarantees in an
[applied contract/proof map](implementation-contracts.md). Concrete corrections
include implying future decisions are independent, treating optimal residuals
as sufficient after an arbitrary first choice, calling Merge Sort an optimisation
substructure example without an objective, and transferring a public singleton
Huffman convention into an internal contraction proof. Categorical memory/DP
comparisons and historical/current test counts are also qualified.

Only **fifteen Markdown paths** change: nine notes, module README, guide,
package README, project traceability review and the new report/map. Algorithms,
tests, JMH bodies/templates, POM, CI, scripts and historical timing files remain
unchanged. Actual Java 21 Maven/JUnit/AssertJ execution supplies test evidence;
no translated/Python algorithm supplies a Java verification result.

## Primary sources inspected

The supplied local PDF is **CLRS, Introduction to Algorithms, Third Edition
(2009)**. Fourth Edition (2022) remains the preferred bibliography but was not
directly inspected; pagination is not relabelled across editions.

| Selected passages | Printed pages | One-based PDF pages | Review use |
|---|---|---|---|
| Theorem 16.1 and activity-selector explanation | 418-419 | 439-440 | Some optimum contains earliest finish; residual compatibility and textbook sentinel versus signed-time source |
| Section 16.2 safe choice / optimal substructure | 423-425 | 444-446 | Rule before residual solutions, replacement under fixed choice, distinction from DP |
| Lemma 16.2, exchanges | 433-434 | 454-455 | Minimum weights in deepest sibling leaves without increasing cost |
| Lemma 16.3, contraction / Theorem 16.4 | 435 | 456 | Internal tree weighted-depth identity and induction |

The source text supplies the classical mathematical arguments. Half-open signed
int intervals, snapshots, deterministic finish/start and room-id ties, nonempty
public singleton codes, BigInteger results and compressed coins are repository
choices. The room-overlap and two-queue invariants are derived from the inspected
code; they are not attributed to an unverified textbook subsection. A textbook
zero-depth contracted singleton is distinguished from the public one-bit case.

Official Java SE 21 references checked:

- [PriorityQueue](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/PriorityQueue.html): minimum-head extraction/peek, comparator ties and operation-cost boundary; iteration is not sorted traversal.
- [List](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html): unmodifiable/null-rejecting copy factories and value/identity semantics; a snapshot need not allocate a distinct collection.

Source ledgers count API operations. Neither documentation page supplies a
profile, physical allocation count or heap-byte measurement for this project.

## Findings and applied corrections

| ID | Gap / source observation | Applied correction |
|---|---|---|
| GE01 | Strategy comparisons imposed sequential/recursive and lower/higher-memory categories on whole paradigms. | Strategy/map separate safe rules, control syntax, state/output and preprocessing; Huffman output and DP state compression need their own bounds. |
| GE02 | Safe choice was described as independent of future choices, whose quality could not invalidate it. | Choice note/map use the existential optimum-preserving claim, residual feasibility, correct continuation and induction; Huffman action is two current minima. |
| GE03 | Optimal parts implied an optimal whole without fixing a safe first choice/compatible boundary; Merge Sort appeared as a generic optimisation example. | Substructure/map add the {1,3,4}/6 counterexample, residual replacement conditions and distinction between a sorting postcondition and optimisation objective. Remove two trailing empty bullets. |
| GE04 | Internal Huffman contraction could be read as invoking the public nonempty singleton cost. | Correctness/Huffman/guide/map explicitly use zero-depth internal roots; n=2 expands from zero to x+y, while a separate top-level singleton emits "0". No executable change. |
| GE05 | Activity residual wording said “after” and public order/ownership details needed one applied specification. | Activity/map use start>=finish, full finish/start validation, negative first-item guard, duplicate-value policy and snapshot identity limits; no weighted or lexicographic optimum is invented. |
| GE06 | Correct room availability/overlap reasoning was dispersed across source/guide. | Room/map connect legal original-position assignments, one entry per allocated room including idle rooms, earliest-finish/id reuse, matching overlap bound and n-r removal ledger. |
| GE07 | Huffman deterministic ties, monotone queues and materialised prefix bounds needed a complete source-to-proof map. | Map proves the next-merge monotonicity, unique minimum-symbol ties and full-tree prefix-length ledger; cost-only work stays separate from code strings/codec claims. |
| GE08 | The guide used an array-length argument for all Huffman inputs although build accepts a symbol map. | Guide/map separately bound arrays by length and maps by the finite unique-int alphabet, keeping long subtree sums distinct from BigInteger weighted depth and feasible resources. |
| GE09 | Correct coin payment/counterexample guarantees needed explicit linkage to exact result semantics and finite-domain arithmetic. | Map relates quotient/remainder, descending duplicate collapse, compressed MAX amount, complete() versus optimality/representability and the bounded binary-family proof/test oracle. |
| GE10 | Mistakes inferred DP necessity from overlap and verified a choice property before choosing the rule; trace/progress steps were missing. | Mistakes/README restore objective -> candidate rule/state -> pseudocode/trace -> safe choice/residual/invariant/termination proof -> costs/tests; overlap alone does not refute a safe rule. |
| GE11 | Complete benchmark workflows needed an applied link to all three objectives/fixtures. | Map records prepared presorted input, immutable plans, every-room assignment, cost-only Huffman, differing grids and one seed; no subtraction/profile attribution or new score. |
| GE12 | Current instructions expected the original 645 tests and smoke/probe prose could appear current. | Guide/README date original verification and connect fresh 710/27 execution; package/project audit extend the ordered chain while author timing provenance stays intact. |

Identifiers are local to this review. Some entries consolidate already-correct
source/guide reasoning; this is not a report of twelve new executable defects.

## Fresh Java 21 verification

JAVA_HOME selects temporary **Amazon Corretto 21.0.13.0.11**, OpenJDK
**21.0.13+11-LTS**, vendor Amazon.com Inc.; Maven Wrapper is **Maven 3.9.11**,
with compiler release 21. The author's Oracle JDK 21.0.9 Windows experiment
remains a separate provenance/environment statement.

The normal suite and profile package used cached dependencies in offline mode.
The suite finished **2026-10-29 00:22:07 -03:00**, taking 15.696 s; packaging
finished **00:22:38 -03:00**, taking 7.252 s. JAVA_HOME and the environment trust
store option were supplied outside the repo. Offline mode reflects this cache,
not a requirement for a first local build.

Core commands with environment selection supplied separately:

```bash
bash mvnw -o --batch-mode --no-transfer-progress -DfailIfNoTests=true test
bash mvnw -o --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.greedy\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.greedy\..*'
```

Both discovery commands use the explicit Java 21 executable.

| Check | Observed result |
|---|---|
| Normal Maven/JUnit/AssertJ suite | **710 tests**, zero failures/errors/skips; BUILD SUCCESS |
| IntervalTest | **2** passed |
| ActivitySelectionTest / IntervalPartitioningTest | **6 / 6** passed |
| HuffmanCodingTest / CoinChangeTest | **7 / 6** passed |
| Greedy module | **27** actual JUnit invocations |
| Benchmark profile package | BUILD SUCCESS; executable benchmarks.jar generated |
| JMH method discovery | All **5** methods listed |
| Parameter discovery | Two sizes/four patterns for each applicable method: **40** default cells, 16 activity / 8 rooms / 16 Huffman |

Surefire XML confirms totals. Six test sources contain five concrete test classes
and SmallSchedules, with 27 @Test methods. Enumerations/seeded cases are checks
inside those invocations: 1024 small interval sets; 200 seeded selection lists;
500 seeded room schedules; 1093 small weight arrays using an independent full-
binary-root-partition oracle; 100 larger seeded Huffman alphabets; and every
amount 0..4096 for selected binary denominations. Tests also check legality,
prefix freedom, recovered costs, deterministic ties, extremes and ownership.
The [map](implementation-contracts.md) distinguishes independent small optimum
oracles from larger cross-checks and links the actual test classes.

Packaging skips another execution after the successful normal suite; profile-
only fixtures compile without being claimed as newly executed. Discovery checks
registration/parameters, not a fresh 40-cell timed experiment, smoke/probe,
doclint run, profiler or memory measurement. Original smoke/probe/doclint claims
keep their dates. No fresh coverage percentage or CI status is asserted.
The shade manifest-overlap warning did not prevent BUILD SUCCESS.

## Final audit and preserved research

The closing audit checks **307 Markdown files / 1412 relative file targets**,
with zero missing destinations, balanced code fences and passing
`git diff --check`. No new local heading-fragment links are introduced.
External URLs and inherited heading fragments are not exhaustively validated.
All fifteen changed paths are Markdown within the declared scope.

Inventory remains **100 production Java files / 115 JMH methods** repository-wide.
Greedy has **5 production files, 6 test sources, 27 declared/executed tests,
4 JMH sources and 5 methods**. Generated Maven output remains ignored; no new
JSON, CSV, log or binary artifact is included.

The [historical author report](../17-benchmarking/results/greedy/README.md)
retains 40 means/errors, 400 measured and 240 warmup iteration averages,
reported 2026-10-26, source author-attributed
`674ab35b7d82f1a9ab7fbab63fa1a9e80253965c` (PR #86), JDK 21.0.9/JMH 1.37 and
5 min 51 sec duration. All ten errors reaching 25% remain visible; no data is
replaced or dropped. Missing jar hash/full command/timestamp remain unknown.
Complete-workflow means do not prove optimality, isolated sorting/heap/boxing
cost, CPU/cache causes, significance, a universal ratio or an exponent fit.

The original 645-test build, fresh 710-test suite and author 40-cell experiment
are distinct evidence. This closes only the scoped **13 — Greedy documentation
review**. Next is **14 — Backtracking**, after integration of this review.
