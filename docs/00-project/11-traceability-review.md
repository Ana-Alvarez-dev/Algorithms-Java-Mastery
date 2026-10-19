# Project Traceability Review

Reviewed on **2026-10-19**, starting from main commit
`494c243488b69c2703357de6bf1bbaa581fe9042`.

This review checks the evidence chain for the executable modules and the
transition to **07 — Stacks and Queues**. It is a source/evidence inventory,
contract spot-check and build verification, not a claim that every paragraph
of every future module has undergone a formal proof review.

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
helpers. The matrix includes the Stacks and Queues contribution in this branch.

| Module | Java files | Executed tests | JMH methods | Evidence and current status |
|---|---:|---:|---:|---|
| 00–02: project, foundations, complexity | Conceptual | Not a standalone executable package | Not applicable | Methodology and analysis prerequisites; not falsely marked incomplete for lacking benchmarks |
| Arrays | 13 | 103 | 15 | [105 recorded means and research](../17-benchmarking/results/arrays/README.md); original errors/runtime/source identity unknown; aggregation input violation remains |
| Searching | 8 | 85 | 4 | [65 means/errors](../17-benchmarking/results/searching/README.md) and [documentation review](../04-searching/documentation-review.md); closest-value duplicate tie mismatch remains |
| Sorting | 5 | 69 | 8 | [Implementation guide](../05-sorting/implementation-and-benchmark-guide.md), [48 reported configurations](../17-benchmarking/results/sorting/README.md); JDK 25.0.1, substantial uncertainty, execution SHA unknown |
| Linked Lists | 6 | 81 | 15 | [Implementation guide](../06-linked-lists/implementation-and-benchmark-guide.md), [30 reported configurations](../17-benchmarking/results/linked-lists/README.md); JDK 21.0.9, author-reported source `a16a431`, partial iterations and wide-error limitations recorded |
| Stacks and Queues | 15 | 80 | 10 | [Selected scope and traceability](../07-stacks-and-queues/implementation-and-benchmark-guide.md); implementation/tests/harness verified; research results pending a measured run |
| 08–19: later subjects | No production packages yet | No corresponding tests yet | None yet | Documentation represents a learning plan, not completed executable evidence |

There are **418 passing repository tests** after this contribution, compared
with **338** before it. Counts do not demonstrate complete behavioural coverage
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
- Benchmark templates contain questions, expectations, timed work and limitations;
  their Result/Interpretation fields remain pending. Smoke times are excluded.

## Verification Performed

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

No new research timing results are claimed. The author's JDK 21.0.9 machine
is a separate environment from this verification host.

## Evidence Policy and Next Action

The repository retains results in Markdown, with existing historical CSV
transcriptions where already present. JSON/log exports were removed by PR #70
at the author's request; Git history is not rewritten. A final table can retain
means/errors but cannot reconstruct unavailable per-fork observations.

After reviewing/merging this contribution, build on local main and follow the
[20-configuration initial run](../07-stacks-and-queues/implementation-and-benchmark-guide.md#windows-execution-and-evidence).
Supply the source SHA, actual JVM/JMH header, full final table and completion
marker as text or legible captures. A subsequent results contribution can then
complete the empirical stage without fabricating evidence.

The source-to-test-to-experiment chain is inspectable for the selected modules.
The rechecked gaps remain tracked work, and conceptual future modules remain
distinct from executable completion.
