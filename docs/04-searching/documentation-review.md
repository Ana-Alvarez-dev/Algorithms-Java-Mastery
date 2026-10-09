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
The findings below describe the reviewed snapshot; they are proposed follow-up
work, not corrections already applied by this report.

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
| S01 | High | Closest-value tie policy fails with duplicate values | `08-common-search-problems.md`, `ClosestValueSearch.java`, reproduced outputs |
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
| `ClosestValueSearch` | Nondecreasing | Closest-value index | `-1` | Smallest equally close index intended; S01 remains open |

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

## Recommended Correction Order and Acceptance Criteria

1. **Resolve S01:** preserve and implement the intended nearest-index tie policy,
   add meaningful duplicate regression tests, and update the correctness argument.
2. **Specify contracts and repair traceability:** address S02 and S07; every path
   and result representation should agree across documentation, source, and tests.
3. **Correct the teaching analysis:** address S03–S06 with precise probe counts,
   explicit probability models, monotonicity, and output-size costs.
4. **Reconcile status and references:** address S08 and add source locations tied
   to the edition actually consulted, without inventing pagination for another edition.
5. **Continue empirical research:** connect `06-search-complexity.md` to the
   existing evidence, retain all uncertainty, and store any future run separately.

The module can continue into research discussion now, provided these open
findings remain visible. Full contract alignment should wait for S01 and the
specification corrections; a successful test run alone is not evidence that
those documentation guarantees have been satisfied.
