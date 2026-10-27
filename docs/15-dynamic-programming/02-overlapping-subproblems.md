# Overlapping Subproblems

Two calls overlap when they ask the same residual question under the same
fixed input. A state key must include every value that changes that answer.
Reusing a result from a different residual problem is a correctness error.

| Problem | State | Fixed context |
|---|---|---|
| Fibonacci | n | Recurrence and exact long domain |
| Knapsack | Item-prefix length i and remaining capacity c | Weight/value arrays for this call |
| LCS | Both prefix lengths i,j | The two Strings for this call |

F(5) depends on F(4) and F(3); F(4) asks for F(3) again. Knapsack take/skip
paths can reach the same (i,c). LCS mismatches can reach the same shorter-prefix
pair through different paths. Memoisation expands each cached nonbase state
once; later requests still pay lookup/call work.

## Reachability Is Not Allocation

These knapsack/LCS memo implementations use fresh dense matrices filled with
-1. Only reached states execute transitions, but all grid cells are allocated
and initialised. TOO_HEAVY knapsack follows one item chain, while IDENTICAL LCS
follows one diagonal; neither case makes the dense public call use only that
chain's memory or preparation time. No cache survives across calls.

Divide-and-conquer recurrences often generate disjoint work; DP reuse concerns
identical residual questions. This does not mean all optimisation subproblems
share consumable resources. The two senses of independence/overlap differ.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) gives concrete state graphs, full costs and controls. Next:
[optimal substructure](03-optimal-substructure.md).
