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
is timed; controlled author measurements remain pending. The guide defines
Windows Java 21 commands and Markdown source/runtime/console evidence without
JSON/log exports. Dense initialisation and pseudopolynomial capacity costs
remain explicit; local smoke is not measured research or passing CI.
