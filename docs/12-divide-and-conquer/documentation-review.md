# Divide and Conquer Documentation Review

Reviewed on **2026-10-29 (America/Buenos_Aires)** from main
`5fd81c44cd5df511bb6c553dcdad45b17c38a18d`, the PR #109 merge of the
[Recursion documentation review](../11-recursion/documentation-review.md).

## Scope and conclusion

This ordered pass reads the nine numbered notes, module README, guide and
package README; all three production classes, four test sources and four JMH
sources (three benchmark classes and TrialInputs); and the reused binary-search,
MergeSort and QuickSort classes with their relevant concrete/shared tests.
The original module documentation has 3593 lines. The review checks technical
contracts, proofs, traces, recurrence assumptions, ownership and evidence
boundaries; it is not a formal verification or a fact-check of every generic
application mentioned in the conceptual notes.

Ten findings apply corrections and consolidate source guarantees in an
[applied contract/proof map](implementation-contracts.md). Concrete corrections
include confusing smaller input with lower total work, attributing problem-tree
height to the actual QuickSort stack, treating binary-search reduction as a
sufficient correctness proof, broadening the concrete merge sort to descending
output, and omitting trace/correctness from a proposed design sequence.
Already-correct tie, inversion and selection reasoning is connected to one
source/test/work map rather than presented as newly implemented behaviour.

Only **fifteen Markdown paths** change: nine notes, module README, guide,
package README, project traceability review and the new review/map. Executable
Java, tests, JMH bodies/templates, POM, CI, scripts and historical measurements
remain unchanged. The actual Java 21 Maven/JUnit/AssertJ run supplies execution
evidence. No translated or Python algorithm substitutes for Java verification.

## Primary sources inspected

The supplied local PDF is **CLRS, Introduction to Algorithms, Third Edition
(2009)**. Fourth Edition (2022) remains the preferred bibliography but was not
directly inspected; third-edition pagination is not relabelled as fourth.

| Selected passages | Printed pages | One-based PDF pages | Review use |
|---|---|---|---|
| Problem 2-4, inversions | 41-42 | 62-63 | Strict pairs, modified merge counting; textbook distinct-key domain versus repository duplicates |
| Section 4.1, decomposition/crossing scans/procedure | 70-73 | 91-94 | Left/right/crossing categories and linear combination; repository half-open/canonical ties remain explicit choices |
| Theorem 4.1 and case qualifications | 94-95 | 115-116 | Basic Master cases, polynomial gaps, regularity and non-exhaustiveness |
| Section 9.2, RANDOMIZED-SELECT procedure/explanation | 216 | 237 | One surviving side and local one-based rank; separate from fixed global zero-based three-way Java selection |

The source problem uses distinct inversion keys; the Java predicate explicitly
extends to duplicate inputs, with equal values not counted. The textbook
maximum-subarray procedure need not return this repository's earliest-start,
then earliest-end answer. Those tie rules, Optional-empty policy, exact long
bounds and ownership follow from the actual Java source and tests. No
randomised expected-linear proof is transferred to deterministic median-of-three.

## Findings and applied corrections

| ID | Gap / source observation | Applied correction |
|---|---|---|
| DC01 | Fundamentals equated every smaller child with reduced complexity/efficient execution. | Distinguish child-size progress from total child/local work, postcondition proofs and measured performance; compare the balanced and linear maximum-subarray strategies. |
| DC02 | Process note said imbalance increases recursion depth without mapping actual control, and implied global descent then ascent. | Separate full problem-tree height from smaller-side QuickSort calls/iterative QuickSelect; describe interleaved sibling execution and actual shared/copy storage. |
| DC03 | Binary Search claimed bases guarantee termination and correctness follows directly from reduction. | Add initialisation/preservation/result/termination reasoning for all possible target positions, exclude middle on misses and state exact reused sorted-array/absence/duplicate contract. Qualify practical comparisons. |
| DC04 | Merge Sort problem allowed ascending or descending output while Java only sorts ascending; source merge proof was abbreviated. | Label adaptations separately, state the sorted-prefix/multiset invariant, scratch purpose, exhaustion and stable-left-on-equality reasoning without inventing identity tests. |
| DC05 | Tight helper recurrences needed one complete public-cost/source ledger. | Recurrences/Master/Analysis/map include public clone/scratch work, L(n) scan/write recurrence, exact construction counts, constant bases and theorem-versus-correctness boundary. Existing Master hypotheses remain intact. |
| DC06 | Hoare, three-way equal bands, local textbook rank and global Java rank needed one applied convention map. | Quicksort/map trace selection and establish discarded-region bounds, unchanged global rank, nonempty equal band and strict active-range progress. No stronger pivot guarantee is introduced. |
| DC07 | Canonical maximum-subarray guarantees and logarithmic live state were scattered across guide/source. | Map derives suffix >= versus prefix > ties, zero-ending extension, nonempty negative answers, three-category proof and total constructions versus logical live state. |
| DC08 | Inversion pair categories, ownership and numeric bounds needed one source/test chain. | Map connects combined count/sort helper postcondition, strict right-head charges, duplicate-aware reversal identity, private arrays and long bounds. Existing correct implementation is preserved. |
| DC09 | Proposed development sequence omitted pseudocode/manual trace/correctness; average-case prompt lacked its input model and pivot-error wording assumed a final pivot position. | README/Mistakes restore the methodological sequence, specify the existing distinct-key random-permutation model and distinguish boundary forms; add contract-specific test guidance. |
| DC10 | Current entry points/Windows instructions still used the original 618-test total. | Date original verification and smoke/probe claims, link fresh 710/18 native execution and discovery; package/project audit extend the ordered chain without relabelling the author experiment. |

DC identifiers are local to this review. Some entries consolidate already-sound
source/guide claims; they are not ten newly discovered executable defects.

## Fresh Java 21 verification

JAVA_HOME selects temporary **Amazon Corretto 21.0.13.0.11**, OpenJDK
**21.0.13+11-LTS**, vendor Amazon.com Inc. Maven Wrapper is **Maven 3.9.11**,
compiling with release 21. The author's Oracle JDK 21.0.9 Windows measurement
remains a separate environment/provenance statement.

The normal suite and profile package used cached Maven dependencies in offline
mode. The normal build finished **2026-10-29 00:02:28 -03:00**, taking 15.766 s;
profile packaging finished **00:03:12 -03:00**, taking 7.172 s. JAVA_HOME and
the environment Java trust-store option were supplied outside the repository.

Core commands with those environment selections separately:

```bash
bash mvnw -o --batch-mode --no-transfer-progress -DfailIfNoTests=true test
bash mvnw -o --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.divideandconquer\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.divideandconquer\..*'
```

Both discovery commands use the explicit Java 21 executable. Offline mode here
reflects available dependencies, not a requirement for a fresh local checkout.

| Check | Observed result |
|---|---|
| Normal Maven/JUnit/AssertJ suite | **710 tests**, zero failures/errors/skips; BUILD SUCCESS |
| MaximumSubarrayTest / InversionCountTest / QuickSelectTest | **6 / 6 / 6** passed; **18** module invocations |
| Reused BinarySearchTest / RecursiveBinarySearchTest | **13 / 13** passed |
| Reused MergeSortTest / QuickSortTest | **14 / 16** passed, including inherited sorting contracts |
| Benchmark-profile package | BUILD SUCCESS; executable benchmarks.jar generated |
| JMH method discovery | All **6** methods listed |
| Parameter discovery | Each method's two sizes/four distributions listed: **48** default cells, 16 per class |

Surefire XML confirms totals. The three module classes declare 18 @Test methods;
SmallArrays is a helper, not another test class. Its 3280 arrays and 21324 valid
rank combinations are inner cases. Existing tests also check 500 seeded maximum
arrays, 500 inversion arrays, 200 duplicate-aware reversal identities, every
rank in 200 seeded selection arrays, extreme ints and large inputs. The
[map](implementation-contracts.md) links the actual independent interval/pair/
sorted-copy oracles and result/ownership checks.

Packaging deliberately skips another test execution after the successful normal
suite. Profile-only fixtures compile without being claimed as newly executed.
Discovery verifies registration/parameters, **not** a fresh 48-cell timing run,
smoke/probe, profiler or allocation measurement. Historical doclint/smoke/probe
claims retain their dates; no new coverage percentage or CI status is asserted.
The shade manifest-overlap warning did not prevent BUILD SUCCESS.

## Final audit and preserved evidence

The closing audit checks **305 Markdown files / 1372 relative file targets**,
with zero missing destinations, balanced fences and passing `git diff --check`.
No new local heading-fragment links are introduced. External URLs and inherited
heading fragments are not exhaustively validated. All fifteen changed paths
are Markdown within the declared scope.

Inventory remains **100 production Java files / 115 JMH methods** repository-wide.
The module has **3 production classes, 4 test sources, 18 declared/executed
module tests, 4 JMH sources and 6 benchmark methods**. Generated Maven output
remains ignored. No new JSON, CSV, log or binary artifact is included.

The [author research record](../17-benchmarking/results/divide-and-conquer/README.md)
keeps all 48 means/errors, 480 measured and 288 warmup iteration averages,
reported 2026-10-26, with author-attributed source
`507b2537d3dedd3beb57900d3ee72d7858571c89` (PR #84), JDK 21.0.9/JMH 1.37,
7 min 4 sec duration and six errors reaching 25% of means. Timed clones,
differing grids, single seed and unavailable jar hash/full command/timestamp
remain explicit. No observation is removed, score replaced, library path
inferred, machine cause attributed, significance conclusion or exponent fit added.

The original 618-test build, current 710-test suite and historical 48-cell
experiment are separate evidence. This closes only the scoped **12 — Divide
and Conquer documentation review**. Next is **13 — Greedy**, after integration.
