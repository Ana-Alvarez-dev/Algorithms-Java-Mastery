# Dynamic Programming Implementations

Package `org.anaalvarezdev.algorithms.dynamicprogramming` implements module 15.
The [authoritative guide](../../../../../../../docs/15-dynamic-programming/implementation-and-benchmark-guide.md)
connects contracts, recurrence/state design, traces, proofs, costs and evidence.

| Type | APIs |
|---|---|
| FibonacciTabulation | `value(n)`; exact long full table for 0..92 |
| Knapsack01 | `valueMemoised`, `valueTabulated`, `valueRolling`, `select` |
| LongestCommonSubsequence | `lengthMemoised`, `lengthTabulated`, `lengthRolling`, `sequence` |

Fibonacci memoised/iterative APIs are reused from the Recursion package. Arrays
are preserved, caches are private per call and selected indices are immutable
snapshots. Knapsack allows the empty subset and preserves original positions;
weights are positive, values nonnegative, optima long. LCS uses UTF-16 units,
including explicit surrogate/tie semantics. Shared work/depth caps and errors
are documented before allocation. Witnesses are separate from values/lengths.

Twenty-two tests use independent BigInteger/subset/subsequence references,
identities, boundaries and ownership. Nine JMH methods in three classes define
57 defaults: Fibonacci 9 ns/op, knapsack/LCS 24 us/op each. Full private setup
is timed. The [first research report](../../../../../../../docs/17-benchmarking/results/dynamic-programming/README.md)
completes all three templates with 57 means/errors, 570 measured and 342 warmup
averages on JDK 21.0.9/JMH 1.37, author-reported source `d572651` (PR #90).
Eleven wide-error cells and the n=20 table Fibonacci CI marker `? 0` remain.
Knapsack rolling point means are lower throughout; LCS depends on shape, and
overlapping intervals limit exact rankings. Units stay per row in Markdown
without JSON/log. Dense setup, pseudopolynomial capacity and omitted witnesses
remain explicit; state ledgers are not timing attribution and smoke is separate.
