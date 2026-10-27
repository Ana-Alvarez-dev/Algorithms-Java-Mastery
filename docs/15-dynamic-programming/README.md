# Dynamic Programming

Module 15 evaluates reusable states with memoisation/tabulation and proves the
recurrences and dependency order before choosing storage. It follows
[Backtracking](../14-backtracking/README.md) and precedes
[Testing](../16-testing/README.md).

## Selected Implementation

| Type | Responsibility |
|---|---|
| FibonacciTabulation | New full-table exact long computation, 0..92; memoised/rolling variants reused from Recursion |
| Knapsack01 | Exact 0/1 value with dense memo/table/one-row storage; deterministic immutable original-index plan |
| LongestCommonSubsequence | UTF-16 length with dense memo/table/one-row storage; one deterministic maximum subsequence |

The [implementation and benchmark guide](implementation-and-benchmark-guide.md)
defines domains, errors, empty cases, ties, ownership, pseudocode/traces,
induction, state/output costs, independent tests, academic sources and commands.
Knapsack's numeric-capacity bound is pseudopolynomial. Dense memo preparation
counts even when only a few states expand. Compressed values/lengths do not
retain a free witness. UTF-16 units differ from code points/graphemes.

## Study Sequence

1. [Fundamentals](01-dynamic-programming-fundamentals.md)
2. [Overlapping subproblems](02-overlapping-subproblems.md)
3. [Optimal substructure](03-optimal-substructure.md)
4. [Memoisation](04-memoization.md)
5. [Tabulation](05-tabulation.md)
6. [State definition](06-state-definition.md)
7. [Recurrence design](07-recurrence-design.md)
8. [Fibonacci](08-fibonacci-case-study.md)
9. [0/1 Knapsack](09-knapsack-case-study.md)
10. [Longest common subsequence](10-longest-common-subsequence.md)
11. [Complexity](11-complexity-analysis.md)
12. [Common mistakes](12-common-mistakes.md)

## Verification and Measurement Status

Normal/profile builds pass **694 repository tests**, including **22 new tests**
(5 Fibonacci, 9 knapsack, 8 LCS). Independent exhaustive oracles, identities,
boundaries, ties, exact sums and ownership complement the proof arguments.
Three JMH templates contain **nine methods/57 defaults**: Fibonacci 9 in ns/op,
knapsack 24 and LCS 24 in us/op. Each row's units must be retained. Full public
value/length workflows are timed; plan/sequence generation is tested separately.

Local discovery, complete short smoke/default reference probes and JavaDoc are
harness/functional evidence. The
[first research report](../17-benchmarking/results/dynamic-programming/README.md)
now completes all three Result/Interpretation fields with **57 means/errors**,
**570 measured** and **342 warmup averages**, completion **8 min 30 sec**.
Author-reported source is `d572651484f3ec6cffe03ad589bddd5a4a8fb8a3` (PR #90);
headers confirm JDK 21.0.9/JMH 1.37 and the controlled defaults. Eleven errors
reach 25%: four Fibonacci/seven LCS, maximum 163.7% for table Fibonacci n=20;
its printed `? 0` CI marker remains literal. Twelve fully numeric method-pair
intervals overlap. Every observation retains its unit in Markdown without
JSON/log; source ledgers, smoke and earlier module provenance stay distinct.
Full command, exact execution timestamp, CPU/power, jar hash and profiler data
are unavailable; SHA attribution was supplied separately from JMH.

Sparse caches, arbitrary-precision Fibonacci, fractional/unbounded knapsack,
rod cutting, matrix-chain order, edit distance, Hirschberg reconstruction and
code-point/grapheme text comparison remain extensions. Local checks are not CI.
