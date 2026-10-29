# Dynamic Programming Documentation Review

Reviewed on **2026-10-29 (America/Buenos_Aires)** from main
`bbab550e82a7768bdce789e646dd16621fa8d539`, the PR #112 merge of the
[Backtracking review](../14-backtracking/documentation-review.md).

## Scope and conclusion

This ordered pass reads all twelve numbered notes, module README, guide and
package README; all three production files, three test sources and four JMH
sources (three benchmark classes plus DynamicProgrammingInputs). It reads the
reused Recursion Fibonacci implementation and checks the historical report's
provenance/workflow boundary. The original module documentation has 872 lines
and already states rigorous bounded contracts and appropriate research limits.

Ten findings clarify and consolidate the chain in an
[applied contract/proof map](implementation-contracts.md). They derive cache
requests versus expansions, sufficient state/context, both directions of the
LCS recurrence, versioned rolling reads, score-preserving reconstruction and
knapsack's conceptual numeric-mask tie. They also date the original 694-test
build against the fresh 710-test suite and remove stale planned-evidence wording.
This is a source/proof/evidence review, not a machine-checked verification or
a claim that Java resources are unbounded.

Only **eighteen Markdown files** change: twelve notes, module README, guide,
package README, project traceability review and new report/map. Executable Java,
tests, JMH bodies/templates, POM, CI, scripts and historical timing files remain
unchanged. Actual Java 21 Maven/JUnit/AssertJ execution supplies Java evidence;
no translated or Python algorithm supplies a Java test result.

## Primary sources inspected

The supplied local PDF is **CLRS, Introduction to Algorithms, Third Edition
(2009)**. Fourth Edition (2022) remains the preferred bibliography but was not
directly inspected; pagination is not equated across editions.

| Selected passages | Printed pages | One-based PDF pages | Review use |
|---|---|---|---|
| Chapter 15 introduction | 359-360 | 380-381 | Distinguish optimum value from constructed solution and explicit design stages |
| Section 15.4 Theorem 15.1 / prefix recurrence | 392-393 | 413-414 | Matching-trailing-unit upper/lower reasoning, mismatch predecessors and dependency order |
| Section 16.2 knapsack contrast / exercise 16.2-2 | 426-427 | 447-448 | Density-greedy counterexample versus exact 0/1 choices; exercise asks for numeric-capacity DP |

No complete knapsack worked algorithm is attributed to Chapter 15. The
repository's long range, shared validation caps, original-index ties, UTF-16
interpretation, dense caches, one-row versions and fixed benchmark grids are
engineering choices/derivations from source. Historical figure/doclint checks
in the guide retain their dates; this pass does not claim to rerun them.

The official [Java SE 21 String documentation](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html)
was checked for immutable Strings, code-unit indexing/length and charAt's
surrogate behaviour. This supports the stated unit model, not a code-point/
grapheme objective or any measured layout/byte count.

## Findings and applied clarifications

| ID | Gap / source observation | Applied documentation |
|---|---|---|
| DP01 | Correct cache-key/sentinel claims needed a complete request/expansion/context ledger. | Overlap/Memo/State/map distinguish fixed input, known zero, nonbase expansions R, at most 1+2R requests and full dense grid preparation, including empty axes. |
| DP02 | Reused Fibonacci methods and equal addition counts needed one applied source chain. | Fibonacci/map link existing APIs, exact 0..92/long bounds, max(n-1,0) additions and memo requests without implying equal machine work or cross-call retained tables. |
| DP03 | Shared knapsack caps and result ownership needed explicit validation/arithmetic boundaries. | State/Knapsack/map retain rolling's shared grid policy, long grid product/value sum, pre-allocation validation, selected-weight bound and factory versus public-record semantic guarantees. |
| DP04 | Rolling methods used different dependency versions despite sharing a one-row description. | Tabulation/map state descending-capacity old item reads versus current-left/old-above/diagonal LCS reads; transposition belongs only to the LCS length API. |
| DP05 | Smallest numeric subset mask could be mistaken for a primitive bitmask limit or another tie objective. | Knapsack/Recurrence/map prove highest-index omission minimises conceptual sum(2^index), with n<=256 and no stored mask; reconstruct an optimal residual, not minimum weight/count/list order. |
| DP06 | LCS matched-suffix prose needed an explicit upper bound as well as an extension construction. | Substructure/map prove an optimum can end in the matching trailing units and reduce to shorter prefixes; mismatch covers at least one omitted trailing position. |
| DP07 | Length symmetry, witness orientation and raw-unit output needed a unified trace invariant. | LCS/map preserve original-input tie direction, next=L(i,j), fully filled char output and legality; swapped length columns do not alter sequence policy, and surrogate units remain explicit. |
| DP08 | Dense/rolling source ledgers and zero-axis work were scattered. | Analysis/map separate G, R, table/rolling updates, row-object storage and witness output; heavy/diagonal traversal does not avoid full memo fill, and numeric C remains pseudopolynomial. |
| DP09 | Mixed units and value-only workflow boundaries needed one benchmark map. | Map lists all 57 defaults, nine ns/op Fibonacci cells versus 48 us/op cells, per-call setup, trial-only generation, omitted witnesses and preserved uncertainty; no new timing. |
| DP10 | Current status still used the original 694-test total and some wording called recorded evidence planned/future. | Date original build/smoke/probe/doclint, link fresh 710/22 execution and registration, update protocol expectation and extend package/project traceability while preserving author data. |

DP identifiers are local. Most findings add derivation or precision to already-
correct contracts; this is not a claim of ten new executable defects. No solver,
optimisation, broader input domain or new performance experiment is introduced.

## Fresh native Java 21 verification

JAVA_HOME selects temporary **Amazon Corretto 21.0.13.0.11**, OpenJDK
**21.0.13+11-LTS**, vendor Amazon.com Inc.; Maven Wrapper is **Maven 3.9.11**,
with compiler release 21. The author's JDK 21.0.9/Windows measurement remains
a distinct environment/provenance statement.

The normal suite and profile package used cached dependencies in offline mode.
The suite finished **2026-10-29 00:49:37 -03:00**, taking 14.439 s; packaging
finished **00:51:09 -03:00**, taking 6.937 s. JAVA_HOME and the environment Java
trust-store option were supplied outside the repository. Offline mode reflects
the available cache, not a requirement for a first local build.

Core commands, with environment selection supplied separately:

```bash
bash mvnw -o --batch-mode --no-transfer-progress -DfailIfNoTests=true test
bash mvnw -o --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.dynamicprogramming\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.dynamicprogramming\..*'
```

Both discovery commands use the explicit Java 21 executable.

| Check | Observed result |
|---|---|
| Normal Maven/JUnit/AssertJ suite | **710 tests**, zero failures/errors/skips; BUILD SUCCESS |
| FibonacciTabulationTest | **5** passed |
| Knapsack01Test | **9** passed |
| LongestCommonSubsequenceTest | **8** passed |
| Dynamic Programming module | **22** actual JUnit invocations |
| Benchmark profile package | BUILD SUCCESS; executable benchmarks.jar generated |
| JMH method discovery | All **9** methods listed |
| Parameter discovery | **57** defaults: 9 Fibonacci, 24 knapsack, 24 LCS; their ns/op/us/op annotations remain distinct |

Surefire XML confirms totals. Three test sources declare 22 @Test methods;
looped cases are not separate invocations. Existing tests cover all 93 Fibonacci
values using BigInteger, Cassini products, 66429 small knapsack item/capacity
cases using exhaustive subsets/ties, 3969 binary String pairs using independent
subsequence enumeration, seeded cases, exact cell/item/512-unit boundaries,
long values, UTF-16/surrogates, recurrence identities and ownership. The
[map](implementation-contracts.md) links each actual oracle and proof boundary.

Packaging skips another execution after the successful normal suite. Profile-
only fixtures compile without being claimed as newly executed. Discovery checks
registration/parameters, not a fresh 57-cell timed experiment, smoke/probe,
doclint, profiler or allocation measurement. Original checks retain their dates.
No fresh coverage percentage or CI status is asserted. The shade manifest-
overlap warning did not prevent BUILD SUCCESS.

## Final audit and preserved research

The closing audit checks **311 Markdown files / 1491 relative file targets**,
with zero missing destinations, balanced code fences and passing
`git diff --check`. No new local heading-fragment links are introduced.
External URLs and inherited heading fragments are not exhaustively validated.
All eighteen changed paths are Markdown within the declared scope.

Inventory remains **100 production Java files / 115 JMH methods** repository-wide.
This package has **3 production files, 3 test sources, 22 declared/executed tests,
4 JMH sources and 9 methods**. Reused Fibonacci stays in Recursion. Generated
Maven output remains ignored; no new JSON, CSV, log or binary file is included.

The [first author report](../17-benchmarking/results/dynamic-programming/README.md)
retains all 57 means/errors, 570 measured and 342 warmup iteration averages,
reported 2026-10-26, source author-attributed
`d572651484f3ec6cffe03ad589bddd5a4a8fb8a3` (PR #90), JDK 21.0.9/JMH 1.37,
8 min 30 sec completion. All eleven high-error cells, every printed iteration,
the literal `? 0` marker and per-row units remain intact. Missing jar hash/full
command/timestamp remain unknown. No observation is removed, exact runtime
cause assigned, significance claimed or exponent fit added. Value/length scores
do not measure the separate plan/sequence APIs.

The original 694-test build, fresh 710-test suite and author's 57-cell experiment
are separate evidence. This closes only the scoped **15 — Dynamic Programming
documentation review**. Next is **16 — Testing**, after integration.
