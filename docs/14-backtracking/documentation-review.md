# Backtracking Documentation Review

Reviewed on **2026-10-29 (America/Buenos_Aires)** from main
`df79799f8d8412df840c6e9f5cd1588a35f0fe5c`, the PR #111 merge of the
[Greedy documentation review](../13-greedy-algorithms/documentation-review.md).

## Scope and conclusion

This ordered pass reads all nine numbered notes, module README, implementation
guide and package README; all four production Java files, four test sources
and five JMH sources (four benchmark classes plus BacktrackingInputs). It checks
the first research report's provenance/workflow boundaries. The original module
documentation has 1004 lines and already states strong bounded contracts and
appropriate evidence limits. This review checks those technical claims and
adds their derivations; it is not a machine-checked proof or a claim of
unbounded-resource safety.

Ten findings clarify and consolidate the chain in an
[applied contract/proof map](implementation-contracts.md). In particular, they
make helper normal-return state, early terminal witnesses, canonical equal-copy
paths, exact call/candidate formulas, current-row masks and materialisation costs
explicit. The concrete current-status correction dates the original 672-test
build and separates it from fresh 710-test verification. Existing correct
algorithms, signed pruning and research caveats remain intact.

Only **fifteen Markdown files** change: nine notes, module README, guide,
package README, project audit and the new report/map. Executable Java, tests,
JMH bodies/templates, POM, CI, scripts and historical timing files are unchanged.
Actual Java 21 Maven/JUnit/AssertJ execution supplies Java evidence. No translated
or Python algorithm supplies a Java test result.

## Primary sources inspected

The supplied local PDF is **CLRS, Introduction to Algorithms, Third Edition
(2009)**. Fourth Edition (2022) remains the preferred bibliography but was not
directly inspected; pagination is not equated across editions.

| Selected passages | Printed pages | One-based PDF pages | Use in this review |
|---|---|---|---|
| Section 22.3 DFS procedure/exploration and return | 604-605 | 625-626 | Depth-first active-path reasoning; not a claim that global graph colours implement these reversible search constraints |
| Section 35.5 positive-input subset problem / EXACT-SUBSET-SUM | 1128-1129 | 1149-1150 | Positive-input overshoot discard versus signed API; distinct list-of-sums optimisation procedure and recurrence |

The book's positive optimisation problem, graph DFS and stateful timestamping
are not presented as copies of this code. The selected value-permutation,
combination, queen, signed-decision contracts, exact ledgers and caps are
repository choices/derivations. No dedicated CLRS N-Queens or permutation-
generator chapter is claimed. General complexity-class context is not a
performance proof for the fixed-cap APIs.

Additional primary/technical references checked on 2026-10-29:

- [NIST DADS, backtracking](https://xlinux.nist.gov/dads/HTML/backtrack.html): alternatives and conceptual depth-first tree of partial choices, without requiring a stored tree.
- [OEIS A000170](https://oeis.org/A000170): ordinary nonattacking-queen counts, offset zero, matching the existing expected sequence through n=12. This is a numerical cross-check, not a proof or timing sample.
- [Java SE 21 JLS 15.19](https://docs.oracle.com/javase/specs/jls/se21/html/jls-15.html#jls-15.19): fixed-width shifts and masked distances; the cap is validated before shifts.
- [Java SE 21 JLS 15.24](https://docs.oracle.com/javase/specs/jls/se21/html/jls-15.html#jls-15.24): left-first conditional OR, omitting the right operand after true; this supplies the include-first decision execution boundary.

Helper counts and arithmetic/state proofs below come from source reasoning;
these sources supply no project-specific profiler events or JVM-frame bytes.

## Findings and applied clarifications

| ID | Gap / source observation | Applied documentation |
|---|---|---|
| BT01 | The generic completion sketch combined report/count/return, while a subset witness can terminate before the final index. | Fundamentals/Tree/map explicitly return from bases and distinguish conceptual full assignments from the visited early-terminal decision tree. |
| BT02 | Restoration was correct but needed a precise normal-return helper obligation. | Constraints/map distinguish restored used/multiplicity/queen flags, unchanged live prefix, overwritten dead path slots and scalar child state; immutable rows are copied before reuse. |
| BT03 | Canonical duplicate handling needed one complete cross-contract argument. | Candidates/Permutation map prove initial-segment equal-copy usage and the frequency-prefix correspondence; combinations use distinct indices, subsets retain equal-valued positions, queens retain reflections. |
| BT04 | Combination call formulas were stated without depth-wise derivation and output was mainly a lower-bound term. | Map derives C(n,d) versus C(n-k+d,d), the hockey-stick total C(n+1,k), node/edge distinction and matching Theta((k+1)C(n,k)) materialisation bound. |
| BT05 | Queen mask prose needed an explicit row-state/decreasing-measure bridge. | Queens/map state shifted current-row attacks, available-bit equivalence, popcount progress, empty full-mask base and cap-before-shift; boolean flag undo cannot erase ancestors because accepted entries were false. |
| BT06 | Safe suffix bounds needed direct linkage to helper semantics, extreme targets and public preparation. | Pruning/map derive disjoint prefix/suffix long bounds, necessary-not-sufficient intervals, implicit suffix exclusion at early success and target-zero/root-miss preparation costs. |
| BT07 | V/I/Q labels and candidate-check ledgers needed consistent terminal/root conventions. | Analysis/map derive n*I versus u*I permutation checks, n*(V-Q) flag checks and V-1 mask iterations, keeping calls, local work and live state separate. |
| BT08 | Empty results, different materialisation/count caps and retained rows needed a unified API boundary. | Contract map connects one empty solution, zero queen boards at n=2/3, immutable lexicographic output, pre-search validation, exact counter bounds and ordinary-placement semantics. |
| BT09 | Count/decision means could be mistaken for enumeration or an isolated pruning/mask cost. | Map ties all four default grids to complete public preparation/work, first-hit stopping, no rows/cache and the preserved source/runtime/uncertainty limits. Existing sound caveats remain. |
| BT10 | Module README/Windows instructions used the original 672-test total without a current follow-up; two notes referred to future timings already recorded. | Date original build/smoke/probe/doclint, link fresh 710/27 execution and discovery, replace stale future-timing wording and extend package/project traceability. |

BT identifiers are local. Most findings add derivations or precision to already-
correct contracts; this is not a claim of ten new executable bugs. No solver,
optimisation, wider domain or new measurement is introduced.

## Fresh native Java 21 verification

JAVA_HOME selects temporary **Amazon Corretto 21.0.13.0.11**, OpenJDK
**21.0.13+11-LTS**, vendor Amazon.com Inc.; Maven Wrapper uses **Maven 3.9.11**,
compiling with release 21. The author's Oracle JDK 21.0.9 Windows experiment
remains a distinct environment/provenance statement.

The normal suite and profile package used cached Maven dependencies in offline
mode. The suite finished **2026-10-29 00:35:19 -03:00**, taking 15.348 s;
packaging finished **00:36:23 -03:00**, taking 7.327 s. JAVA_HOME and the environment
Java trust-store option were supplied outside the repository. Offline mode
reflects that cache, not a requirement for a first local build.

Core commands, with environment selection supplied separately:

```bash
bash mvnw -o --batch-mode --no-transfer-progress -DfailIfNoTests=true test
bash mvnw -o --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.backtracking\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.backtracking\..*'
```

Both discovery commands use the explicit Java 21 executable.

| Check | Observed result |
|---|---|
| Normal Maven/JUnit/AssertJ suite | **710 tests**, zero failures/errors/skips; BUILD SUCCESS |
| PermutationsTest / CombinationsTest | **7 / 6** passed |
| NQueensTest / SubsetSumTest | **7 / 7** passed |
| Backtracking module | **27** actual JUnit invocations |
| Benchmark profile package | BUILD SUCCESS; executable benchmarks.jar generated |
| JMH method discovery | All **8** methods listed |
| Parameter discovery | **46** default cells: 12 permutation, 12 combination, 6 queens, 16 subset |

Surefire XML confirms totals. Four test sources declare 27 @Test methods; looped
cases do not inflate this number. Existing tests use independent index-
permutation/dedup, bitmask-subset, complete-column/leaf-diagonal and reachable-
sum-set references, plus BigInteger formulas, Pascal identity, published ordinary
counts, boundaries, ownership and repeated-call checks. Small domains include
120 multisets, all n<=12/k combinations, every queen board through n=9 and
1093 signed arrays/targets -8..8. Larger boundary/seeded checks complement them.
The [map](implementation-contracts.md) links each actual test and proof boundary.

Packaging skips another execution after the successful normal suite. Profile-
only fixtures compile without being claimed as newly executed. Discovery checks
registration/parameters, not a fresh 46-cell timing run, smoke/probe, profiler,
doclint or allocation measurement. Historical smoke/probe/doclint retain their
dates. No fresh coverage percentage or CI status is asserted. The shade
manifest-overlap warning did not prevent BUILD SUCCESS.

## Final audit and preserved research

The closing audit checks **309 Markdown files / 1451 relative file targets**,
with zero missing destinations, balanced code fences and passing
`git diff --check`. No new local heading-fragment links are introduced.
External URLs and inherited heading fragments are not exhaustively validated.
All fifteen changed paths are Markdown within the declared scope.

Inventory remains **100 production Java files / 115 JMH methods** repository-wide.
Backtracking has **4 production files, 4 test sources, 27 declared/executed tests,
5 JMH sources and 8 methods**. Generated Maven output remains ignored; no new
JSON, CSV, log or binary artifact is included.

The [first author report](../17-benchmarking/results/backtracking/README.md)
retains all 46 means/errors, 460 measured and 276 warmup iteration averages,
reported 2026-10-26, source author-attributed
`b62df1b84810dfe5dea023d5d00bac2eb05c0cd0` (PR #88), Oracle JDK 21.0.9/JMH 1.37,
6 min 42 sec completion. Both errors reaching 25% and all tiny nonzero printed
uncertainties remain. Missing jar hash/full command/timestamp remain unknown.
No observation is removed, cause assigned, significance claimed or exponent fit
added. Counts and booleans do not measure enumeration, retained-output throughput
or an isolated allocation/predicate/bit instruction cost.

The original 672-test build, fresh 710-test suite and author's 46-cell experiment
are separate evidence statements. This closes only the scoped **14 — Backtracking
documentation review**. Next is **15 — Dynamic Programming**, after integration.
