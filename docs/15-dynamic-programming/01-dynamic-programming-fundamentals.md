# Dynamic Programming Fundamentals

Dynamic Programming evaluates a recurrence over reusable states. Memoisation
discovers states top down; tabulation evaluates them in a dependency-respecting
order. Correctness starts with the state meaning and recurrence, not the cache.

## Design Sequence

1. Specify inputs, outputs, domain, empty case and ownership.
2. Define sufficient state and prove the recurrence/base cases.
3. Establish a well-founded dependency order.
4. Choose memoisation or tabulation and count transitions plus preparation.
5. Reconstruct a witness only when the public contract requires one.
6. Verify against independent references and measure a stated question.

The selected examples are full-table Fibonacci, 0/1 knapsack and longest common
subsequence. Fibonacci computes a value; knapsack and LCS optimise an objective.
DP also supports counting/feasibility recurrences. A maximisation objective is
not a universal prerequisite, and storing results alone does not prove a recurrence.

## Reuse and Limits

Fibonacci memoised/rolling methods are reused from Recursion. New tabulation
shares their exact long domain 0..92. Knapsack uses positive weights,
nonnegative values and original positions once each; LCS uses UTF-16 char units.
Input caps and deterministic witness policies are in the [implementation and benchmark guide](implementation-and-benchmark-guide.md).

Overlapping calls motivate avoiding recomputation, but total states can still
be huge. Knapsack's capacity-indexed O(nC) bound is pseudopolynomial in numeric
C. Dense caches allocate/initialise their full grids even when few states are
expanded. Rolling storage reduces retained state but does not preserve a witness
for free. There is no universal performance ranking among implementation styles.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) connects all twelve notes to Java, induction/invariants, traces,
independent tests and three pending measurement templates. Next:
[overlapping subproblems](02-overlapping-subproblems.md).
