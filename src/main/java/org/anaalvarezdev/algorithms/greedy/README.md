# Greedy Implementations

This Java 21 package implements the selected scope of
[module 13](../../../../../../../docs/13-greedy-algorithms/README.md).
The [implementation guide](../../../../../../../docs/13-greedy-algorithms/implementation-and-benchmark-guide.md)
connects contracts, pseudocode, traces, exchange/lower-bound proofs, invariants,
cost ledgers, independent tests and JMH.

| Type | Selected contract | Verification |
|---|---|---|
| Interval | Immutable nonempty half-open int interval; signed times accepted | Two tests for reversed/empty rejection and extreme endpoints |
| ActivitySelection | Maximum-cardinality compatible immutable plan; full sort or validated finish/start order; input preserved | Six tests, all 1,024 small interval sets against exhaustive subsets plus 200 seeded lists, ties/ownership/order policies |
| IntervalPartitioning | Assign every interval to minimum rooms; immutable original-index room ids | Six tests, all small sets plus 500 seeded schedules against independent maximum overlap and legal assignments |
| HuffmanCoding | Deterministic positive-frequency prefix codes and exact BigInteger cost; heap/two-queue cost-only variants | Seven tests, 1,093 weight arrays against all binary root partitions, prefix freedom, recovered cost, seed/extreme/skew/tie/ownership checks |
| CoinChange | Compressed largest-coin-first heuristic with explicit remainder; no arbitrary-system optimum/completeness claim | Six tests, two counterexamples, binary denominations against exact DP through 4,096, domains and compressed MAX_VALUE amount |

Verification reported 2026-10-26 using OpenJDK 21 passed **645 repository tests**,
including **27 module tests**, normal tests and benchmark-profile clean package.
Five JMH methods were discovered; all 40 short smoke cells completed. A separate
probe checked every default combination three calls each using references and
nonmutation/repeatability checks. JavaDoc passes doclint excluding missing tags.
Local evidence does not establish CI status or close earlier module findings.

Touching intervals are compatible. Unweighted interval scheduling is the same
selection problem; minimum-room assignment retains all intervals and has a
different objective. Snapshots and returned plans/assignments count in space,
not just scalar greedy state. All supplied collections/arrays are preserved,
and returned collection values are defensively immutable.

Huffman accepts opaque int symbols and positive int frequencies. Subtree sums
fit long; weighted path length uses BigInteger. Empty input costs zero; a
singleton has code "0" and costs its frequency. Full code generation uses an
explicit stack and O(n+L) state including L codeword characters. Cost-only
variants omit codewords; neither is a full encoder/decoder or compression format.
Heap leaves are offered individually; two-queue sorting is part of the operation.

CoinChange.complete() means remainder zero, not optimal coin count. Largest-coin-
first may return extra coins or miss an existing representation; the guide
distinguishes its payment invariant from the safe-choice proofs of other APIs.
No production exact-change solver or automatic coin-system classifier is added.

Three templates retain Research Question, Theoretical Expectation, Measured
Work, Result and Interpretation. Author measurements remain pending; smoke is
harness evidence only. The guide defines **40 default cells (16/8/16)** in
**microseconds/op**, preserving source, actual runtime and all errors later in
Markdown without JSON/log artifacts. Matroids, weighted scheduling, knapsack,
full compression codecs and later paradigms remain extensions.
