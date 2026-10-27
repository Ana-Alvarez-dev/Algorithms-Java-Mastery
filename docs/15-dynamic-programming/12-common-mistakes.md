# Common Mistakes

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

Keep Result/Interpretation pending until author measurements include source,
actual runtime/settings, complete errors/fork observations and completion. Three
templates use different units: ns/op for Fibonacci, us/op for the other two.
Do not pool unlike outputs, erase uncertain cells or infer CPU causes from means.
Evidence stays Markdown without JSON/log exports. The [implementation and benchmark guide](implementation-and-benchmark-guide.md) contains the chain
from state specification through implementation, tests and experiment protocol.

The next numbered module is **16 - Testing**, building on the repository's actual
JUnit/AssertJ evidence and remaining coverage gaps. It is distinct from a new
algorithm family or an already configured CI workflow.
