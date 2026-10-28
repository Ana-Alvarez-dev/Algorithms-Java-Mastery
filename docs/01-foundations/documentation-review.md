# Foundations Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** against main
`d70d8d1c61da6e22394edadaca84d50d4255cc84`, the PR #98 merge.
This is the first module-specific documentation pass after the
[engineering traceability closure](../00-project/12-closing-traceability-review.md).
It does not revise the identity or outcome of the earlier CI runs.

## Scope and conclusion

All eight numbered Foundations notes and the module README were read. The pass
checks definitions, specification strength, worked correctness arguments,
consistency between the notes, source attribution and concrete downstream
examples. Eight grouped findings below have documentation corrections applied
in this branch. Some correct statements were retained and made more concrete;
not every addition denotes a production defect.

The main substantive gaps were incomplete maximum postconditions in shortened
examples, an insertion-sort invariant stating only ordering, and wording that
could dismiss implementation proofs or make optional study stages mandatory.
The current maximum and insertion-sort production methods already express the
intended algorithmic behaviour. No production correction is required by these
findings. Java, tests, JMH, POM, scripts, workflow and historical measurements are
unchanged. No new Maven test, coverage or timed benchmark run is claimed here.

## Consulted academic source

The supplied PDF identifies itself as **CLRS, Introduction to Algorithms,
Third Edition, MIT Press, 2009**. Printed pages and one-based PDF page positions
are recorded separately:

| Source | Printed pages | PDF pages | Concepts actually checked |
|---|---|---|---|
| Section 1.1, Algorithms | 5-6 | 26-27 | Input/output specification, instances, sorting as an ordered permutation, correctness including halting, incorrect candidates, representations of algorithms |
| Section 2.1, Insertion sort | 18-20 | 39-41 | Original-element prefix invariant; initialisation, maintenance and exit argument; pseudocode loop-counter conventions |
| Section 2.3.1, The divide-and-conquer approach | 30-31 | 51-52 | Smaller recursive instances, divide/conquer/combine and merge's sorted-input precondition |

The repository's preferred bibliography remains the **Fourth Edition (2022)**.
It was not supplied or checked in this review. The numbered notes now distinguish
that bibliography from the third-edition evidence; page equivalence across
editions is not assumed.

The explicit entry snapshot/frame condition, well-founded progress explanation,
Java exception observations, valid-edge-case naming convention and sixteen-stage
engineering workflow are the repository's explanations or source-derived
contracts. They are not attributed to an identical CLRS checklist or a formal
contract calculus verified in the cited passages. Dijkstra and Gries remain
complementary references, not texts newly inspected for this pass.

## Findings and applied corrections

| ID | Classification | Evidence / problem before correction | Applied correction and location |
|---|---|---|---|
| F01 | Definition clarification | Algorithm definition built successful termination/output into the term; deterministic behaviour was characterised only by the result. CLRS separately discusses incorrect candidates and permits program representations. | [Definition](01-algorithm-definition.md) separates precise procedure from correctness obligations, scopes finiteness to these tasks, distinguishes deterministic choices from equal outputs, and gives the maximum snippet's preconditions. |
| F02 | Incomplete teaching contract | The shortened maximum examples in notes 02/03 stated only that the return value dominates the input. Returning 3 for [1,2] passes that condition. The fuller note 03 already included membership, but lacked a concrete entry-state/non-mutation contract. | [Specification](02-problem-specification.md) and [Contracts](03-preconditions-and-postconditions.md) require membership and dominance over the original input, plus non-mutation for this API. A0 is proof notation, not a required copy. Representative examples are distinguished from an exhaustive finite-domain specification. |
| F03 | Worked-proof clarification | The informal maximum invariant did not name its index bounds, entry snapshot or progress measure. The notes correctly separated partial and total correctness, but offered no complete indexed example. | [Correctness](05-correctness.md) introduces a well-founded variant. [Invariants](06-invariants.md#an-indexed-proof-and-a-termination-variant) gives the loop-header property, n-i decrease, exit inference and singleton case. |
| F04 | Invariant too weak for sorting | The insertion-sort example required only a sorted prefix. Replacing [2,1] with [0,0] yields sorted output without sorting the original values. | [Invariants](06-invariants.md#example--insertion-sort) requires a sorted permutation of the original prefix and an unchanged suffix, explains multiplicity and the saved-key state. [Decomposition](07-problem-decomposition.md#decomposition-and-correctness) makes merge-sort contract composition concrete. |
| F05 | Contract-boundary clarification | Invalid mathematical inputs, public exception behaviour and runtime validation could be read as disjoint from behavioural correctness. The edge-case naming convention was presented without qualification. | [Contracts](03-preconditions-and-postconditions.md#contract-validation-vs-algorithm-testing) records actual FindMaximum null/empty outcomes and the absence of explicit validation. [Edge Cases](04-edge-cases.md) scopes its naming convention and keeps defined rejection outcomes testable. |
| F06 | Misleading correctness guidance | The heading “Proving the Implementation Instead of the Algorithmic Idea” classified a legitimate proof activity as a mistake. An abstract proof alone does not establish Java behaviour. | [Correctness](05-correctness.md#assuming-an-abstract-proof-automatically-proves-the-implementation) requires translation assumptions about bounds, arithmetic, aliases and mutation, with an int-overflow counterexample to model transfer. |
| F07 | Inconsistent methodology | Note 08 mandated every stage and a test for every edge case, while notes 04/07 explicitly select useful cases/decomposition. It called manual tracing validation of reasoning and implied every algorithm needs a benchmark/pattern. | [Method](08-problem-solving-method.md) makes depth proportional, requires a reason for optional stages, distinguishes selected traces from general proof and states the computational cost model. |
| F08 | Navigation / attribution | The README tree and overview used unnumbered filenames that do not exist. Notes cited the fourth edition without distinguishing the supplied third edition. | [README](README.md) uses actual numbered files, links all eight overviews and this report. Notes 01-07 qualify the edition; note 08 identifies the workflow as repository synthesis. |

These IDs belong to this review only; they do not replace Searching S01-S08 or
renumber findings in any earlier report. “Applied” means the scoped documentation
changes above are present, not that all possible formalisation work is complete.

## Concrete source and test correspondence

- [`FindMaximum.find`](../../src/main/java/org/anaalvarezdev/algorithms/arrays/FindMaximum.java)
  starts from array[0], compares the remaining values and does not write the
  input. It does not explicitly validate null/emptiness. The current source
  entails NullPointerException for null and ArrayIndexOutOfBoundsException for
  empty input. Its [nine existing tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/FindMaximumTest.java)
  include those two outcomes, non-mutation, singleton, negative and repeated
  maximum cases. This is source inspection, not a fresh test execution.
- [`InsertionSort.sort`](../../src/main/java/org/anaalvarezdev/algorithms/sorting/InsertionSort.java)
  already specifies an ordered permutation, saves a key, shifts only greater
  values and fills the gap. The Foundations example is brought into agreement
  with that source contract. No new claim of having formally verified every
  inner-loop state is made.
- The maximum result contract refers to the original non-empty sequence. Public
  exception obligations remain separate from that mathematical result. The
  review does not change any exception policy or add runtime sortedness scans.

## Validation and its limits

An independent, temporary Python translation checked the documented maximum
loop and insertion-sort outer-loop properties on a bounded domain:

| Check | Domain / count | Outcome |
|---|---|---|
| Maximum header invariant, n-i variant, final result and non-mutation | Lengths 1-4, values in {-2,-1,0,1,2}: 780 arrays | Pass |
| Insertion-sort outer prefix/suffix properties, ordered result and multiplicities | Lengths 0-4, same values: 781 arrays | Pass |
| Negative control: upper bound without membership | Return 3 for [1,2] | Weak condition accepts; corrected contract rejects |
| Negative control: sortedness without permutation | Replace [2,1] with [0,0] | Weak condition accepts; corrected contract rejects |
| Negative control: sets without multiplicities | [1,1,2] versus [1,2,2] | Equal sets; different multiplicities |

The first two checks exhaust only the declared small domain. They exercise
independent translations, not the Java binary, and do not prove correctness over
all Java arrays. The symbolic arguments in the notes and the source/test
inspection are distinct evidence. No temporary script or generated data is added
to the repository.

The final documentation checks verify existing relative file targets, local
heading links introduced by this pass, fenced-code balance and Git whitespace.
Their results are recorded below after the edits. External URLs and all inherited
anchors are not comprehensively validated.

Final audit: **285 Markdown files / 848 relative file targets / zero missing
destinations**. All **six newly introduced local heading targets** resolve.
Fenced-code balance and `git diff --check` pass. The change boundary is
**eleven Markdown files only**; the inventory remains 100 production Java files
and 115 JMH methods. These are documentation/source checks, not new executions
of the 710/723 JUnit suites.

## What remains for the next review

The next planned documentation pass is **02 - Complexity**: cost models, growth,
quantifiers in asymptotic notation, best/average/worst cases, auxiliary versus
total space, amortised analysis and worked examples. It has not been re-reviewed
by this change. Later modules need their own bounded passes before an exhaustive
repository-wide review can be claimed.

This pass also does not replace a formal proof of every Java implementation,
perform benchmark reruns, fill historical provenance gaps, introduce a coverage
gate or configure repository protection. Those remain separate work items.
