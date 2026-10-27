# Complexity Analysis

DP cost includes state preparation, expanded states, transitions, lookups,
arithmetic and output. A state-count formula alone is not the complete public
workflow. Dense memo storage must be counted even for unreachable states.

| Operation | Full asymptotic model | Auxiliary state |
|---|---|---|
| Primitive Fibonacci memo/table | O(n+1) | O(n+1); memo also linear depth |
| Primitive Fibonacci iterative | O(n+1) | O(1) |
| Knapsack dense memo/table | O((n+1)(C+1)) | Full grid; memo adds O(n) depth |
| Knapsack one row | O(n+C+1+sum(max(0,C-w[i]+1))) | O(C+1) |
| Knapsack select | Full table plus O(n) trace | Full grid plus O(n) retained indices |
| LCS dense memo/table | O((m+1)(k+1)) | Full grid; memo adds O(m+k) depth |
| LCS one row | O(mk+m+k+1) loose full-call bound | O(min(m,k)+1) |
| LCS sequence | Full table plus O(m+k) trace and O(L) output | Full grid plus O(L) output |

Empty axes and array-of-array row allocation are included by the +1 terms.
These are word-cost models inside the APIs' validated domains. Knapsack's C
is numeric, so the usual O(nC) bound is pseudopolynomial in bit-length. Long
Fibonacci has a finite exact domain; arbitrary-precision arithmetic is another
model. General DP need not have polynomially many states/transitions.

## Reachability and Layout

TOO_HEAVY knapsack expands n memo states but fills the whole dense cache. Unit
weights create overlapping capacities; rolling skips c=0 and scans feasible
capacities downwards. IDENTICAL LCS memo follows a diagonal but initialises the
whole grid; DISJOINT expands all nonbase states. Dense table/rolling lengths
visit both loop dimensions. Output length alone does not predict elapsed time.

Three templates define nine methods/57 defaults. Fibonacci uses ns/op; knapsack
and LCS use us/op. Preserve units and mathematical output when comparing rows.
No smoke score, two-size grid, lower mean or source state count proves an exponent,
allocation rate, exact stack size, cache/CPU cause or statistical significance.
Fresh preparation and plan omissions must remain explicit. The [implementation and benchmark guide](implementation-and-benchmark-guide.md) provides
the full ledgers and protocol. Next: [common mistakes](12-common-mistakes.md).
