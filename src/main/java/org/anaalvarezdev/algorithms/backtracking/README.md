# Backtracking Implementations

Package `org.anaalvarezdev.algorithms.backtracking` implements module 14.
The [authoritative guide](../../../../../../../docs/14-backtracking/implementation-and-benchmark-guide.md)
connects contracts, pseudocode/traces, correctness, costs, tests and measurement.

| Type | Selected API |
|---|---|
| Permutations | `unique`, `countUnique`, `countUsingFrequencies`; equal values indistinguishable |
| Combinations | `choose`, `countWithPruning`, `countWithoutPruning`; distinct universe 0..n-1 |
| NQueens | `solutions`, `countWithFlags`, `countWithBitMasks`; ordinary placements |
| SubsetSum | `exists`, `existsWithoutBounds`; signed position subsets and long target |

Inputs are preserved. Enumeration returns immutable rows/outer lists in
lexicographic order. Empty combinatorial constructions have one empty solution;
empty subset is allowed. Explicit work/output caps and exception policies are
specified in JavaDoc and the guide; these are educational bounded searches.
Count variants do not materialise rows, and decision returns at the first witness.

Tests in the matching test package execute 27 cases using independent exhaustive
oracles, identities, published counts, boundaries and ownership checks. Four JMH
classes in the matching JMH package define eight methods/46 defaults. Full
public workflow is timed; no memoisation/formula replaces the search. The
[first research report](../../../../../../../docs/17-benchmarking/results/backtracking/README.md)
now completes all four templates with 46 means/errors, 460 measured and 276
warmup averages on JDK 21.0.9/JMH 1.37; author-reported source is `b62df1b`
(PR #88). Two wide-error cells stay visible. Pruning benefits depend on the
family and stopping rule; count timings omit enumeration and source counts
do not isolate CPU/GC/allocation causes. The guide retains Java 21 commands.
Evidence remains Markdown without JSON/log; smoke stays separate.

The [ordered documentation review](../../../../../../../docs/14-backtracking/documentation-review.md)
records fresh Java 21 execution: **710 repository tests, including 27 here**.
The [contract/proof map](../../../../../../../docs/14-backtracking/implementation-contracts.md)
derives helper restoration, canonical paths, exact call/candidate ledgers and
output costs. This pass changes Markdown only.
