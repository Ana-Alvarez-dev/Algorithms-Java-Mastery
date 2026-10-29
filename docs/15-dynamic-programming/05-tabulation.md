# Tabulation

## Versioned row invariants

The [applied proof](implementation-contracts.md) names each read version.
Descending knapsack capacity leaves row[c-weight] on the previous item prefix
because weights are positive. LCS instead needs current left, previous above
and saved previous diagonal while moving left-to-right. Shorter-column
transposition is used only by the length API; reconstruction retains the
original first/second orientation and its tie rule.

Tabulation evaluates states in an order that satisfies their dependencies.
The selected loops are iterative, so they avoid recursive search frames;
their arrays and retained outputs still require explicit resource bounds.

| Recurrence | Required predecessors | Legal fill order |
|---|---|---|
| Fibonacci | i-1, i-2 | Increasing i |
| Knapsack | Previous item row, same/reduced capacity | Increasing item prefix |
| LCS | Previous row, current left cell, previous diagonal | Row-major |

Zero boundaries represent the empty subset/prefix. An arbitrary table iteration
order does not preserve the recurrence. Induction follows the fill order: each
cell's dependencies are already exact, so the recurrence makes it exact too.

## Rolling Storage

Fibonacci needs only two previous values, already implemented in Recursion.
Knapsack updates a one-dimensional row in **descending capacity**; ascending
order can reuse the current item. LCS updates left to right while keeping the
old diagonal and current cell before overwriting, and uses the shorter String
as columns. These are different versioning arguments, not one universal loop rule.

Rolling value/length methods omit witness reconstruction. `Knapsack01.select`
and `LongestCommonSubsequence.sequence` instead retain the full table to trace
one deterministic optimum. Further compression/recomputation algorithms would
need separate proofs and contracts. Smaller storage does not guarantee lower
runtime on all inputs; full preparation and public outputs must match a comparison.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) supplies traces, invariants and costs. Next:
[state definition](06-state-definition.md).
