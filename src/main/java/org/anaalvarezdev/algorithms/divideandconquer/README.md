# Divide and Conquer Implementations

This Java 21 package implements the selected scope of
[module 12](../../../../../../../docs/12-divide-and-conquer/README.md).
The [implementation guide](../../../../../../../docs/12-divide-and-conquer/implementation-and-benchmark-guide.md)
connects specifications, decomposition, pseudocode, traces, proofs, cost models,
tests and JMH. Existing Searching/Sorting case studies are reused unchanged.

| Type | Observable contract | Verification |
|---|---|---|
| MaximumSubarray | Divide/linear variants return Optional of canonical nonempty half-open range and exact long sum; empty gives no result; input preserved | Six tests: exhaustive interval oracle, 500 seeded cases, ties/extremes and large exact sum |
| InversionCount | Merge/direct-pair variants count strict inversions in long; equal values excluded; input preserved | Six tests: exhaustive pair oracle, 500 seeded cases, duplicate reversal identities and count beyond int |
| QuickSelect | In-place zero-based sorted rank; duplicate multiplicities preserved; rank positioned with earlier/later inequalities | Six tests: 21,324 exhaustive array/rank combinations, sorted-copy/permutation checks, full-int/structured/large inputs and rejection before mutation |

Verification reported 2026-10-25 (UTC), using OpenJDK 21, passed **618 repository
tests**, including **18 module tests**, normal tests and benchmark-profile clean
package. All six JMH methods are discovered; 48 short smoke cells complete.
An independent probe checks every default combination three calls each against
separate oracles, with repeatability and unchanged trial inputs. JavaDoc passes
doclint excluding missing tags. Local checks are distinct from CI status.

Balanced crossing scans and merge counts have Theta(n log n) work for n>=2;
linear maximum subarray has linear work. Logical depth, total source allocations,
scratch arrays and returned output are distinct. QuickSelect is iterative with
O(1) state and an O(n^2) worst bound; its deterministic median-of-three pivot
does not promise expected-linear work on arbitrary input. All-equal input takes
one linear partition. All possible int-array sums/counts fit long.

MaximumSubarray tie order is highest sum, earliest start, then earliest end;
all-negative inputs do not choose an implicit empty zero sum. Selection
validates ranks before writing and otherwise permutes the input. Its benchmark
clones inside both timed workflows to preserve repeated trial data; the clone's
O(n) space is separate from the production selector's O(1) auxiliary state.

Three templates retain Research Question, Theoretical Expectation, Measured
Work, Result and Interpretation. The
[first research report](../../../../../../../docs/17-benchmarking/results/divide-and-conquer/README.md)
now completes all three with 48 means/errors, 480 measured and 288 warmup
averages, in **microseconds/op**, on JDK 21.0.9/JMH 1.37. Author-reported source
is `507b2537d3dedd3beb57900d3ee72d7858571c89` (PR #84). Six errors reach 25%
of their means. All observations and workload limits stay intact in Markdown
without JSON/log artifacts; smoke remains separate. Parallel algorithms,
Strassen, randomised/worst-linear selection and later paradigms remain extensions.

The [ordered documentation review](../../../../../../../docs/12-divide-and-conquer/documentation-review.md)
records fresh Java 21 verification: **710 repository tests, including 18 here**.
The [applied map](../../../../../../../docs/12-divide-and-conquer/implementation-contracts.md)
connects APIs, canonical ties, cross-pair/partition invariants, work ledgers
and research limits. This pass changes documentation only.
