# Optimal Substructure

## Complete LCS cases and separate witness proofs

The [proof map](implementation-contracts.md) supplies both directions for the
matching-trailing-unit LCS recurrence: appending a match constructs a solution,
and an optimum can be chosen to use those final units. On mismatch at least
one trailing position can be omitted. Knapsack take/skip likewise covers
every feasible subset. Deterministic witness ties are additional claims;
optimal length/value alone does not establish them.

For an optimisation recurrence, optimal substructure means the chosen residual
solutions can be optimal for their correctly defined subproblems. A replacement
argument shows why a worse residual could not belong to a global optimum.

## Selected Arguments

In 0/1 knapsack, an optimum either omits the last item or takes it once. After
taking it, a better subset of earlier items at the remaining capacity would
improve the original. Both branches must be considered unless infeasible;
this is not a proof that the largest value/weight ratio is a safe choice.

In LCS, matching trailing units can finish an optimum using the shorter-prefix
optimum. If trailing units differ, at least one is omitted. Taking the maximum
of those two prefix optima covers all cases. LCS **is an optimisation problem**:
it maximises common subsequence length, with possibly multiple optimal witnesses.

Fibonacci is a value recurrence, not an optimisation example. Its correctness
uses recurrence induction rather than an optimal-choice argument. DP also
evaluates valid counting/feasibility recurrences, where optimality terminology
must be replaced by the relevant combination rule.

## Sufficient State and Safe Combination

The recurrence must preserve resource/ordering constraints. Knapsack decreases
item count on both branches; LCS decreases prefix indices. A state containing
capacity alone would forget which knapsack items remain. A greedy rule needs
its own safe-choice proof; optimal substructure alone does not justify it.
Overlapping residuals explain reuse, while state count, transition cost and
numeric encoding determine whether the resulting computation is practical.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) contains full proofs and counterexamples. Next:
[memoisation](04-memoization.md).
