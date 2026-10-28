# Searching Documentation Analysis

## Review Scope and Conclusion

Reviewed on **2026-10-09**, against repository snapshot
`0d44defbb57ed7e1743da4cc2b6b269a81b5d349`.

The module has a coherent learning progression, eight dedicated implementations,
eight corresponding test classes, and recorded benchmark evidence. Its core
linear-search and binary-search invariant arguments are sound under their stated
input assumptions. However, academic completion requires resolving a confirmed
closest-value contract mismatch and several inconsistencies in specifications,
complexity explanations, and implementation traceability.

This review covers the module README and all eleven numbered documents. It
compares their claims with production code, tests, three JMH benchmark classes,
and the [first Searching benchmark record](../17-benchmarking/results/searching/README.md).
The original findings describe that reviewed snapshot and originally proposed
follow-up work. Dated sections below distinguish later applied corrections
from the historical defects, execution results and measurements.

## Module 16 Follow-up (2026-10-27)

**S01 is now corrected.** The implementation reuses lower bounds to return the
first copy of the selected nearest value. The [Testing report](../16-testing/verification-report.md)
records a 694-test green baseline with 100% instruction/branch/line coverage in
the old method, then six failures from twelve new named cases, followed by 710
passing tests after the fix. Sixteen new Searching invocations include exhaustive
linear-oracle, seeded, metamorphic and fixture-isolation checks. Searching now
has 101 executed tests; the additional helper is test-only.

The [applied-problems note](08-common-search-problems.md) and class proof/cost
model now match empty/minimum fast paths plus at most two logarithmic lower
bounds. The lower-bound helper's JavaDoc comparison markup is repaired; its
executable behaviour is unchanged. S02-S08 and the original review/results are
not reclassified as resolved by this focused correction. The sections below
retain the original pre-fix counterexamples and historical verification.

## Closing Documentation Follow-up (2026-10-27)

The following scoped corrections are now applied against source
`0d0ade1f67ffd79db26c06be48a0bf13fdda724a`. Production Java and tests are unchanged.
The original findings below remain a review of the 2026-10-09 snapshot; they
must be read with these resolution follow-ups rather than as current defects.

| ID | Applied correction | Verification |
|---|---|---|
| S02 | Concrete eight-operation contract table; sequence semantics; null/order/mutation policies | Read all eight current public APIs and source preconditions; hosted XML retains 101 Searching invocations |
| S03 | Ten halvings distinguished from eleven probes at n=1024; safe midpoint pseudocode | Explicit last-target midpoint trace and floor(log2(n))+1 bound |
| S04 | Uniform-success probability models and linear mixture formula; recursive stack bound qualified as worst case | Direct expected-count derivation and initial-midpoint countermodel |
| S05 | Single-transition predicate and boundary invariant; generic API remains an extension | Ordered-value partitions separated from alternating status examples |
| S06 | O(log n+k) enumeration and Θ(k) materialised output distinguished from fixed endpoints | Current SearchRange source returns a two-int array; interval/equality semantics separated |
| S07 | Canonical Searching production/test/JMH paths; first occurrence, -1, null and non-mutation explicit | Referenced paths exist and match the source contract |
| S08 | Implemented versus extension status; checked versus assumed preconditions; workload-aware selection and non-identical timings | README, recursive comparison and common-mistakes text aligned |

These close the specific S02-S08 documentation actions from this review. S01's
code fix and its regression evidence remain the separate module 16 change.
The [closing traceability review](../00-project/12-closing-traceability-review.md)
records hosted evidence, source inventory and continuing research limitations.
No new performance run, Java implementation change or claim of universal
academic completion is introduced by these edits.

## Ordered Documentation Review Follow-up (2026-10-28)

Reviewed from main **af63005fa6ac23c68de9c20d062443a67821db47**, the PR #101
merge of the [Arrays review](../03-arrays/documentation-review.md). This pass
reads the eleven numbered Searching notes, both module/package READMEs, all
eight production classes, nine test classes plus their fixture helper, three
JMH classes and the existing research record. It examines technical contracts,
selected proofs, probability models, finite-width arithmetic and source
correspondence. It is not an exhaustive editorial or formal proof audit.

S01's code/regression correction and the scoped S02-S08 resolutions above are
retained. Additional findings S09-S16 clarify remaining statements and add
applied proofs; their identifiers do not reopen those historical defects.
The new [contract map](implementation-contracts.md) connects all eight current
operations to actual results, failures, mutation, tests and cost models.
Only Markdown changes. Production Java, tests, benchmark bodies/defaults,
POM, scripts, CI and historical measurements/provenance remain unchanged.

### Sources inspected for this follow-up

The supplied **CLRS Third Edition (2009)** was consulted directly:

| Passage | Printed page | One-based PDF page | Use |
|---|---:|---:|---|
| Exercise 2.1-3 | 22 | 43 | Sequence search, index-or-NIL specification and requested invariant proof |
| Exercise 2.3-5 | 39 | 60 | Ordered midpoint elimination and requested logarithmic worst-case argument |

These are exercises, not supplied solutions for this repository's zero-based
contracts or proof variants. First/last/lower-bound invariants, closest-value
tie rules and exact probe/frame counts are repository derivations. The preferred
Fourth Edition (2022) bibliography is distinct from the consulted third edition;
fourth-edition pagination is not claimed.

Versioned official sources checked are the
[Java SE 21 Arrays API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html)
for absent-key/duplicate policies,
[JLS section 15.18.2](https://docs.oracle.com/javase/specs/jls/se21/html/jls-15.html#jls-15.18.2)
for numeric promotion/finite-width addition and subtraction, and
[Math.abs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Math.html#abs(long))
for the minimum-value absolute-value caveat. The midpoint/domain and int-input
distance bounds are explicit mathematical arguments, not timing observations.

### Findings and applied corrections

| ID | Evidence / gap | Applied correction |
|---|---|---|
| S09 | Linear Search's own Average Case still said only “common models”; the growth table called binary midpoint probes comparisons. | The linear note derives the unique-uniform-success and absence-mixture counts. Complexity distinguishes equality comparisons, midpoint probes and guards, with 2p-1 / 2p core value-comparison counts. |
| S10 | Boundary reasoning recorded a valid match but did not fully establish its extremal index; the lower bound lacked a central two-partition proof. | The contract map states candidate-plus-active-match invariants for first/last, both lower-bound excluded partitions, variants and empty-exit postconditions. Patterns distinguish the monotone threshold predicate from equality. |
| S11 | Midpoint advice promised overflow avoidance without its endpoint domain; wider distances lacked an explicit range argument. | Binary Search and Mistakes state the non-negative valid-array bounds. The map proves int-input distances fit long, shows why casting after overflow is too late, and guards the MAX_VALUE+1 upper-bound extension. No implementation changes. |
| S12 | Recursive explanation treated an empty base case as a termination guarantee on its own; the recurrence omitted base/rounding and the step lacked an explicit induction hypothesis. | Recursive Binary Search adds strict-size progress, induction on smaller intervals, W(0) and the attained floor(m/2) worst continuation. It separates non-empty probes, the empty helper call, wrapper and logical stack from JVM bytes/availability. |
| S13 | Some summaries still implied one universal search result; insertion sentinel n, library absence encoding and fixed endpoint results needed applied examples. | Fundamentals, Problem Definition, Problems and Interview Notes distinguish the postconditions and link the API map. SearchInsertPosition returns a position without insertion; SearchRange skips the last search when absent and returns only two indices. |
| S14 | Workload advice omitted a concrete total-cost model and the fact that sorting changes index meaning and can violate non-mutation. | Complexity compares q worst-case scans with an explicitly assumed comparison sort plus q searches. The original-index example identifies separate indexing/storage and duplicate-policy obligations; no timing crossover is inferred. |
| S15 | A single Linear Search appeared as a duplicate-detection strategy without the complete task; one guide sentence claimed tests confirm the specification universally. | Problems separates repeated scans, sort-plus-adjacency and expected hash assumptions. Guide distinguishes executed cases from a proof, links existing closest-value regressions and adds applied review questions. |
| S16 | Ordered review navigation and historical/current evidence needed a new dated boundary. | Both READMEs and the project audit link this follow-up and its contract map. S01-S08 history and the 65 recorded means/errors remain intact. Current fixed-target JMH source is not evidence for unmeasured variants or recovered execution metadata. |

### Independent bounded verification

A temporary Python model checks mathematical translations of the eight
operations against direct linear/index oracles, including preserved invariants
and decreasing intervals. It is independent of the project's Java execution;
no model script, JSON, log or CSV is added to the repository.

| Check | Bounded domain | Outcome |
|---|---|---|
| Eight operation results, interval invariants and input preservation in the models | 792 sorted arrays, lengths 0..7, values {-2,-1,0,1,2}; 8712 target cases including int extremes | Pass; first/last, lower bound, equality endpoints, nearest distance/index, any-match and recursive midpoint sequences agree with oracles |
| Unordered first-match scan | 364 arrays, lengths 0..5, values {-1,0,1}; 1820 target cases | Pass; exact r+1 / n comparison counts |
| Core maximum, boundary min/max probes and uniform-success count controls | 257 sizes 0..256; 66306 target/gap cases | Pass; core maximum is n's bit length, boundary counts satisfy the stated bounds, linear expectation sum and binary shallow-level capacity agree |
| Large endpoint arithmetic | Nine limits, including 2^30, 2^30+1 and MAX_VALUE; no giant allocation | Pass; rightmost probe count, safe update ranges, wrapped old midpoint and MAX_VALUE+1 counterexamples |
| Numeric / contract controls | Full-width int distance, int abs minimum, sentinel n, sorting/original index and unsorted invariant | Pass; incorrect numeric/order assumptions are exposed; the unsorted-input invariant rejection is expected |
| Current default JMH source correspondence | Five sizes, four methods and scenario counts totalling 65 cells | Pass; binary MIDDLE is one probe and differs from linear MIDDLE at each even default size; binary LAST attains the core maximum |

These finite checks complement the stated invariants and derivations; they
are not universal proofs inferred from samples or tests of Java exception,
allocation or concurrency behaviour. No Java compilation, JUnit/Maven suite,
coverage or timed JMH execution was performed in this pass. The available host
runtime is JDK 17; the earlier Java 21 hosted evidence remains separately dated.
The existing 101 Searching invocations are not reported as newly re-executed.

### Final audit and empirical boundary

The final path audit checks **289 Markdown files / 1028 relative file targets**
with **zero missing destinations**. All **eight newly introduced local heading
targets** resolve. Fenced-code balance and `git diff --check` pass. This does
not claim validation of every inherited external URL or heading fragment.
The change boundary is **sixteen Markdown files only**; the repository inventory
remains **100 production Java files / 115 JMH methods**. Test, benchmark-result,
build and CI paths are outside the changed file set. These checks do not
constitute a Java 21 execution.

The [first 65-row measurement record](../17-benchmarking/results/searching/README.md)
retains all reported means/errors and missing run-header, iteration, effective
settings and exact execution-source limitations. Current source has three JMH
classes/four methods; their fixed trial targets, MIDDLE difference, below-minimum
ABSENT target and excluded setup are inspected source properties. They do not
measure boundary/closest-value variants, stack occupancy or a sorting crossover.
No new artifact hash or performance ranking is supplied by this review.

Next in the ordered documentation pass is **05 - Sorting**, preserving its
existing implementation, tests and research history. Later modules are not
declared re-reviewed by this change. The following original method, findings
and verification sections remain the historical 2026-10-09 record, read with
the dated resolutions above.

## Method and Sources

The review used four distinct forms of evidence:

1. **Specification:** explicit inputs, outputs, duplicate policies, assumptions,
   mutation guarantees, and invalid-input behavior.
2. **Reasoning:** invariants, recursive contracts, decreasing interval sizes,
   operation counts, and probability assumptions.
3. **Execution:** the existing JUnit suite and small counterexamples executed
   against the actual `ClosestValueSearch` source.
4. **Measurement:** the 65 transcribed JMH configurations, including every
   reported mean and error, without treating timing as a proof.

The supplied copy of Cormen, Leiserson, Rivest, and Stein, *Introduction to
Algorithms*, **Third Edition, 2009**, was consulted directly:

| Source location | Concept checked | Repository adaptation |
|---|---|---|
| Section 2.1; Exercise 2.1-3, printed p. 22 | Sequence-based search specification and linear-search loop invariant | Zero-based array indices and `-1` rather than the textbook's `NIL` |
| Section 2.2 | Input size, operation model, and distinctions between best, average, and worst cases | Constant-time integer comparisons and indexed array access |
| Exercise 2.3-5, printed p. 39 | Sorted-input binary search and logarithmic worst-case time | Iterative and recursive Java implementations |

The module bibliography names the **Fourth Edition, 2022**. The page numbers
above refer exclusively to the supplied third edition. This review does not
claim to have verified fourth-edition pagination. Boundary variants and the
closest-value tie policy are repository contracts, not requirements attributed
to those textbook exercises.

## What Already Works

- The progression from problem definition to algorithms, patterns, applied
  problems, and review is appropriate for the module's current scope.
- `03-linear-search.md` contains initialization, maintenance, and termination
  reasoning for the examined-prefix invariant.
- `04-binary-search.md` identifies the active-interval invariant and explains
  why sorted order permits safe elimination and why interval reduction terminates.
- `05-recursive-binary-search.md` distinguishes the recursive contract, base
  cases, recurrence, and auxiliary stack space.
- The core binary searches permit any matching duplicate; first, last, and
  lower-bound variants have distinct contracts and implementations.
- `10-common-mistakes.md` correctly explains why checking sortedness inside every
  query would change the operation from logarithmic to linear time.
- `09-problem-solving-guide.md` and `11-interview-notes.md` explicitly separate
  tests from proof and benchmark measurements from asymptotic analysis.
- Benchmark records preserve uncertainty and explain fixed targets, excluded
  setup costs, and missing execution metadata.

## Findings and Priorities

**High** means a documented output guarantee is contradicted by execution.
**Medium** means a learner could derive an incorrect contract, count, or
algorithm-selection rule. **Low** means traceability or editorial precision
needs improvement. These are qualitative review priorities, not numeric scores.

| ID | Priority | Finding | Primary evidence |
|---|---|---|---|
| S01 | High; corrected in module 16 | Historical closest-value tie defect with duplicate values | `08-common-search-problems.md`, `ClosestValueSearch.java`, reproduced outputs |
| S02 | Medium | The formal problem-definition document does not specify the implemented contracts | `02-search-problem-definition.md`; README Document Overview |
| S03 | Medium | Binary-search example understates the maximum number of midpoint probes | `04-binary-search.md`, Search Space Reduction |
| S04 | Medium | Average-case claims omit their probability models in several sections | `04-binary-search.md`, `05-recursive-binary-search.md`, `06-search-complexity.md` |
| S05 | Medium | Boundary-search examples need a monotonicity condition | `07-search-patterns.md`, `08-common-search-problems.md` |
| S06 | Medium | Range endpoints and enumerating all results need distinct cost models | `07-search-patterns.md`, `08-common-search-problems.md` |
| S07 | Low | Linear-search traceability still points to the former Arrays package | `03-linear-search.md`, Repository Traceability |
| S08 | Low | README status and teaching examples need alignment with current scope | README; `05-recursive-binary-search.md`; `10-common-mistakes.md` |

### S01 — Closest-Value Tie Policy and Duplicate Values

Both the applied-problems document and class Javadoc promise the lower index
when values are equally close. Duplicates are allowed by the nondecreasing-input
precondition. The implementation returns immediately on an exact match and,
after an unsuccessful search, considers the adjacent boundary indices. This
finds a closest **value**, but does not always find the smallest index among
all equally close elements.

The following calls were executed against the unchanged production source:

| Sorted input | Target | Expected under the documented policy | Actual index |
|---|---:|---:|---:|
| `[10, 20, 20, 20, 30]` | 20 | 1 | 2 |
| `[10, 20, 20, 40]` | 25 | 1 | 2 |
| `[10, 20, 20]` | 100 | 1 | 2 |

In the first case, the exact midpoint match is not the first duplicate. In the
other cases, the lower boundary or final index belongs to a repeated value.
Earlier copies have the same distance and should win under the stated policy.

`ClosestValueSearchTest` has 12 tests, including a tie between distinct values
and an integer-overflow case, but no duplicate-value case. Passing those tests
therefore does not establish the full tie guarantee.

**Recommended correction:** preserve the documented smallest-index policy.
Determine a closest candidate value, then use a lower-bound search to return
its first occurrence. Two logarithmic searches remain `O(log n)` overall.
Add regression coverage for exact duplicates, repeated lower candidates,
repeated maxima, and ties between repeated neighboring values. Revise the
correctness argument to justify both minimal distance and minimal index.

Until that correction is made, the documentation should identify this gap
explicitly rather than claiming the complete tie contract has been verified.

### S02 — Make the Formal Specification Concrete

`02-search-problem-definition.md` describes a finite "set", then offers several
possible result types and absence representations. An indexed array with
duplicates is an ordered **sequence**. The document does not define the
repository's null, mutation, duplicate, or empty-array policies, although its
README overview says it does.

Its claim that every searching algorithm solves the same existence/location
problem also needs qualification: insertion-position and nearest-value search
can succeed without an exact match.

**Recommended correction:** retain the general introduction and add a concrete
contract table for the implemented `int[]` operations:

| Operation | Required order | Result | Empty-array result | Duplicate policy |
|---|---|---|---|---|
| `LinearSearch` | None | Matching index or `-1` | `-1` | First occurrence |
| `BinarySearch` | Nondecreasing | Matching index or `-1` | `-1` | Any matching index |
| `RecursiveBinarySearch` | Nondecreasing | Matching index or `-1` | `-1` | Any matching index |
| `FirstOccurrence` | Nondecreasing | First index or `-1` | `-1` | Smallest matching index |
| `LastOccurrence` | Nondecreasing | Last index or `-1` | `-1` | Largest matching index |
| `SearchInsertPosition` | Nondecreasing | Lower bound in `[0, n]` | `0` | Before existing equal values |
| `SearchRange` | Nondecreasing | Inclusive `[first, last]` indices | `{-1, -1}` | Complete target endpoints |
| `ClosestValueSearch` | Nondecreasing | Closest-value index | `-1` | Smallest equally close index; S01 corrected by module 16 |

All eight methods reject null input with `NullPointerException` and leave the
input array unchanged. Sortedness is a caller obligation, not an implemented
validation step. No particular result is promised for unsorted input to an
ordered-search method. These distinctions should be stated alongside the table.

### S03 — Count Midpoint Probes Precisely

`04-binary-search.md` shows the reductions from 1,024 elements to one and says
only ten iterations are required. Ten halvings do not account for examining
the remaining singleton. With the repository's inclusive interval and floor
midpoint, searching the last element of a 1,024-element distinct array takes
**eleven midpoint probes**.

For nonempty arrays, the maximum number of midpoint probes for this binary
search is `floor(log2(n)) + 1`. This agrees with the four, seven, ten, and twenty
probe examples in `06-search-complexity.md` for 10, 100, 1,000, and 1,000,000
elements, respectively.

**Recommended correction:** distinguish halvings from iterations and label the
growth table as midpoint probes. One probe is not necessarily one Java primitive
comparison: an unsuccessful probe evaluates equality and an ordering condition.
The distinction changes exact operation counts, not the logarithmic bound.
Use `low + (high - low) / 2` consistently in pseudocode as well as Java examples.

### S04 — State Average-Case Models and Space Cases

The assumptions section and summary table in `06-search-complexity.md` correctly
require probability models. Its Average Case section nevertheless says linear
search examines approximately half the collection without specifying successful
searches or uniform target position. The individual binary-search documents
also state average `Θ(log n)` without defining their model.

**Recommended correction:** for a successful linear search with a unique target
uniformly distributed over the `n` positions, derive `(n + 1) / 2` inspected
elements. If success has probability `p` and successful positions are uniform,
the expected count is `p * (n + 1) / 2 + (1 - p) * n` for `n >= 1`.
For binary search, state a model such as distinct sorted values with uniformly
chosen successful target positions before claiming average logarithmic time.
A workload that always targets the initial midpoint has constant time instead.

Qualify recursive `Θ(log n)` stack space as a **worst-case** bound; an initial
midpoint match uses constant-depth recursion. In the recursive comparison table,
replace iterative "Stack Usage: None" with "No recursive stack growth" and label
running time as worst case. Ordinary method execution still has a call frame.

The existing use of `O(n)` where a tight `Θ(n)` bound is available is not
mathematically false. Harmonizing notation would improve consistency with the
module's stated preference for tight bounds.

### S05 — Require Monotonic Predicates for Boundary Binary Search

Examples such as the first failed transaction or first available appointment
do not inherently guarantee that a binary-search decision safely eliminates
half of the candidates. A status sequence may alternate between true and false.
Sorting by an unrelated field does not make that predicate monotonic.

**Recommended correction:** explain the required single-transition pattern,
such as `false ... false, true ... true`, and connect it with a preserved
boundary invariant. Without this property or a suitable index, scanning may
be necessary. Clarify that `FirstOccurrence`, `LastOccurrence`, and
`SearchInsertPosition` implement ordered-value boundaries; they are not generic
predicate-search implementations.

### S06 — Distinguish Endpoints from Materialized Results

`SearchRange` returns two indices, not every matching element. Its logarithmic
bound is correct for those endpoints. The surrounding general range and
multiple-match discussions also describe retrieving every match without
explaining the output cost.

**Recommended correction:** for `k` results in a sorted array, finding boundaries
and enumerating the matches costs `O(log n + k)` time. Returning only endpoints
has no `k`-element output. Materializing a result collection also needs `O(k)`
output space. Distinguish an equality range from a general value interval.
Keep multidimensional nearest-neighbor examples outside the one-dimensional
integer implementation's guarantee, as the existing Implementation Boundary
section already intends.

### S07 — Update Linear-Search Traceability

`03-linear-search.md` points to Arrays production, test, and benchmark paths
and discusses the dedicated Searching implementation as future work. Those
three former paths do not exist in the reviewed snapshot. The README and
problem-solving guide already identify the canonical Searching package.

**Recommended correction:** replace the old paths with the existing Searching
paths and retain the Arrays origin as a brief historical note. Specify first
occurrence, `-1`, null rejection, and non-mutation directly in the algorithm
document rather than deferring the absence representation to an unspecified
implementation contract.

### S08 — Align Status and Engineering Advice

The README lists existing algorithms as potential implementation topics, says
to verify precondition enforcement without distinguishing checked null input
from assumed sortedness, and describes examples absent from the applied-problems
document, such as rotated-array search. These are scope ambiguities rather than
missing mandatory implementations.

`10-common-mistakes.md` calls linear search on sorted input a poor algorithm
selection categorically. This conflicts with the module's own explanations of
small inputs, preprocessing costs, and workload-dependent choices.

**Recommended correction:** separate implemented coverage from possible later
extensions, distinguish validation from caller obligations, and qualify
algorithm-selection advice with workload assumptions. In the recursive document,
describe equal comparison sequences and equal asymptotic growth rather than
claiming identical running time. Timing differences are precisely what the
comparative benchmark investigates.

## Document-by-Document Assessment

| Document | Assessment | Main follow-up |
|---|---|---|
| [Module README](README.md) | Strong navigation and traceability; some planning text is stale | S02, S08; add edition-specific source locators |
| [01 — Fundamentals](01-search-fundamentals.md) | Useful terminology and motivation | Limit universal existence-search and performance claims to the intended scope |
| [02 — Problem definition](02-search-problem-definition.md) | Conceptual introduction rather than the promised concrete specification | S02 |
| [03 — Linear search](03-linear-search.md) | Correct prefix-invariant reasoning; stale executable paths | S07; make the first-occurrence contract explicit |
| [04 — Binary search](04-binary-search.md) | Sound interval and termination reasoning | S03, S04 |
| [05 — Recursive binary search](05-recursive-binary-search.md) | Sound recursive contract and recurrence | S04, S08 |
| [06 — Complexity](06-search-complexity.md) | Correct assumptions and summary; examples need consistent qualifications | S03, S04; distinguish analytical cases from measured scenarios |
| [07 — Patterns](07-search-patterns.md) | Correct links to implemented ordered boundary variants | S05, S06 |
| [08 — Applied problems](08-common-search-problems.md) | Correct range composition; closest-value claim exceeds behavior | S01, S05, S06 |
| [09 — Problem-solving guide](09-problem-solving-guide.md) | Good methodology and implementation matrix | Carry the concrete contracts and tie regression cases into the testing guidance |
| [10 — Common mistakes](10-common-mistakes.md) | Strong midpoint, termination, and sortedness explanations | S08; add duplicate nearest-value ties to contract examples |
| [11 — Interview notes](11-interview-notes.md) | Good reasoning questions and evidence boundaries | S04, S05; distinguish nearest value from first nearest index |

## Relationship with Recorded Benchmark Results

The [complete 65-row transcription](../17-benchmarking/results/searching/2026-10-09-first-run-transcribed.csv)
already supplies the empirical layer. The documentation should reuse that
record instead of introducing a second dataset or treating selected means as
general performance guarantees.

| Recorded observation | Defensible interpretation | Limit |
|---|---|---|
| Binary `MIDDLE`: means 1.041–1.067 ns/op | Consistent with an initial-midpoint match | Best-case target selection; no average-case model |
| Binary `LAST`: 9.539 +/- 0.823 at `n = 100`; 36.672 +/- 4.698 at `n = 1,000,000` | Slow measured growth consistent with interval halving | Timing does not prove logarithmic complexity |
| Linear `LAST`, `n = 1,000,000`: 239,040.976 +/- 192,867.402 ns/op | A full-array traversal with a large reported mean | Wide uncertainty limits precise comparisons |
| Variants `LAST`, `n = 1,000,000`: iterative 36.159 +/- 0.871; recursive 43.996 +/- 3.979 ns/op | Recursive reported mean is about 21.7% higher in this case | Descriptive ratio; no causal attribution or universal ranking |
| Recursive `ABSENT`, `n = 1,000,000`: 66.355 +/- 116.612 ns/op | A reported measurement with very wide uncertainty | Does not support a precise ranking |

The name `MIDDLE` selects different targets: linear search uses `n / 2`, while
binary search uses `(n - 1) / 2`. At every even size in this experiment, those
are different indices. For linear search the case traverses about half the
array; for binary search it is the immediate-success best case. Cross-algorithm
comparisons must explain this distinction. `ABSENT` always selects `-1`, below
the minimum, and is not an average over absent targets.

The run header, iteration data, and exact source commit used by the author's
execution were not supplied. The source annotations describe one fork and
five measurement iterations but do not establish the effective command-line
configuration. The current record is adequate for a preliminary research
discussion, with its existing limitations retained. It does not measure stack
space, sorting costs, changing targets, or correctness of the boundary variants.

## Validation Performed

The unchanged repository was compiled and tested using **OpenJDK 21**:

```bash
bash mvnw -o -B -ntp test
```

Result: **188 tests passed**, with zero failures, errors, or skipped tests.
Of these, **85 tests** belong to the eight Searching test classes. This result
applies to the reviewed snapshot and does not resolve S01: the three duplicate
counterexamples were separate executable probes, absent from the committed suite.

No new performance experiment was executed for this review. The numerical
observations above come from the previously recorded author-supplied summary.

## Historical Correction Order and Current Acceptance Status

1. **S01 completed by module 16:** preserve the nearest-index tie policy, add
   regressions/oracles and update the proof/costs; see the follow-up above.
2. **Specify contracts and repair traceability:** address S02 and S07; every path
   and result representation should agree across documentation, source, and tests.
3. **Correct the teaching analysis:** address S03–S06 with precise probe counts,
   explicit probability models, monotonicity, and output-size costs.
4. **Reconcile status and references:** address S08 and add source locations tied
   to the edition actually consulted, without inventing pagination for another edition.
5. **Continue empirical research:** connect `06-search-complexity.md` to the
   existing evidence, retain all uncertainty, and store any future run separately.

The original correction order is retained as review history. S01's code fix
was completed by module 16, and the closing follow-up above applies the specific
S02-S08 documentation corrections. Continued empirical research still retains
its original uncertainty/provenance limits. Passing tests alone did not close
the documentation findings; source/contract and analytical corrections did.
