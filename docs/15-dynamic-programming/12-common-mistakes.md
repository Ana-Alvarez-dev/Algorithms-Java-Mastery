# Common Mistakes

## Dated verification and proof boundary

The [ordered review](documentation-review.md) records fresh Java 21 tests
and discovery separately from the original build, smoke/probe/doclint and
author experiment. The [contract/proof map](implementation-contracts.md)
connects sufficient keys, nonnegative sentinels, row versions, arithmetic,
validation and witness ties to actual source/tests. Preserve the literal
uncertainty markers and per-row units; a source state count is not a profile.

| Mistake | Consequence | Selected correction |
|---|---|---|
| Caching only capacity or one LCS prefix | Different residual problems share a key | Include both relevant state coordinates |
| Using zero as unknown | Valid empty/disjoint optima repeatedly recompute | -1 sentinel under nonnegative-answer contract |
| Reusing cache across different inputs | Stale answers | Fresh private state per public call |
| Assuming memo memory equals reached states | Hides dense allocation/fill | Account for the full initialised grid |
| Ascending one-row knapsack capacity | Reuses an item | Descending capacities and 0/1 counterexample |
| Losing LCS old diagonal | Mixes row versions | Save old cell and diagonal before overwriting |
| Treating LCS as substring | Forbids valid gaps | Prefix recurrence and subsequence oracle |
| Expecting a unique/lexicographically least witness | Unspecified tie behaviour | Explicit deterministic reconstruction ties |
| Expecting minimum weight among knapsack value ties | Changes the contract | Highest-index omission, smallest numeric mask |
| Calling O(nC) polynomial in bit-length | Hides numeric capacity growth | Pseudopolynomial qualification |
| Treating Fibonacci as optimal-choice reasoning | Confuses value recurrence and optimisation | Recurrence induction and exact long cap |
| Treating UTF-16 units as user-perceived characters | Splits surrogate/grapheme semantics | Explicit unit policy and Unicode tests |
| Claiming compressed length/value gives a free witness | Omits reconstruction state/work | Separate full-table plan/sequence APIs |
| Timing warmed cache and comparing with fresh work | Changes the question | Per-invocation private state |

## Verification and Evidence

Twenty-two module tests use BigInteger references, exhaustive item subsets,
independent subsequence enumeration, identities and boundary/tie/ownership checks.
Variant agreement complements these references and the proof; it is not itself
a proof. The seeded grid probe additionally checks every benchmark return value
and repeatability without turning its execution times into research scores.

The [first report](../17-benchmarking/results/dynamic-programming/README.md)
now completes all three templates with 57 cells, every error/fork observation,
570 measured and 342 warmup averages. Author-reported source is `d572651`;
headers confirm JDK 21.0.9/JMH 1.37 and the controlled defaults. Eleven wide-error
cells remain, including 163.7% at table Fibonacci n=20 and its literal `? 0` CI
marker. Do not delete the 347.118 ns/op iteration or infer a precise ranking.
Twelve fully numeric interval pairs overlap; a diagonal/heavy memo search still
pays dense setup. Per-row ns/op/us/op, source counts versus runtime causes,
omitted witnesses and prior provenance remain distinct. Evidence stays Markdown
without JSON/log. The [implementation and benchmark guide](implementation-and-benchmark-guide.md) contains the chain
from state specification through implementation, tests and experiment protocol.

The next numbered module is **16 - Testing**, building on the repository's actual
JUnit/AssertJ evidence and remaining coverage gaps. It is distinct from a new
algorithm family or an already configured CI workflow.
