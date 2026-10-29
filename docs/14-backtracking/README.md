# Backtracking

Module 14 studies complete combinatorial search and exact feasibility through
partial constructions, constraint checks, sound pruning and state restoration.
It follows [Greedy Algorithms](../13-greedy-algorithms/README.md) and precedes
[Dynamic Programming](../15-dynamic-programming/README.md).

## Implemented Scope

Package `org.anaalvarezdev.algorithms.backtracking` contains four Java classes:

| Class | Contract | Work/output cap |
|---|---|---|
| Permutations | Immutable unique value permutations and two exact counters | Enumeration length <=9, counts <=10 |
| Combinations | Immutable size-k subsets of 0..n-1 and two exact counters | 0<=k<=n<=18 |
| NQueens | Ordinary column-per-row placements; flags/mask counts | 0<=n<=12 |
| SubsetSum | Signed int-position subsets, any long target; bounded/unbounded decision | Length <=24 |

Array inputs are preserved; materialised outer lists and rows are immutable.
Empty permutation/combination/queen constructions each count as one solution.
Pruning preserves complete output only with a sound rejection rule. A boolean
decision can stop at its first witness; full counters cannot.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md)
is the executable specification: input/error/duplicate policies, pseudocode,
traces, correctness arguments, exact state ledgers, complexity, independent
tests, primary sources and Windows execution commands.

## Study Sequence

1. [Fundamentals](01-backtracking-fundamentals.md): contracts and correctness obligations.
2. [State-space trees](02-state-space-trees.md): total work versus active state.
3. [Candidate generation](03-candidate-generation.md): completeness and canonical choices.
4. [Constraint validation](04-constraint-validation.md): invariants and sound rejection.
5. [Pruning](05-pruning.md): signed bounds and matching controls.
6. [Permutations and combinations](06-permutations-and-combinations.md): duplicate policy and output cost.
7. [N-Queens](07-n-queens.md): conflict flags and fixed-width masks.
8. [Complexity analysis](08-complexity-analysis.md): scans, calls, depth and output.
9. [Common mistakes](09-common-mistakes.md): restoration, arithmetic and evidence limits.

## Verification and Measurement Status

The original 2026-10-26 verification passed **672 repository tests**,
including **27 module tests**, on OpenJDK 21. The
[ordered review](documentation-review.md) passes **710 repository tests**,
including the same 27 here, and supplies an
[applied contract/proof map](implementation-contracts.md). Four JMH templates
contain
eight methods and **46 default cells** (12 permutation, 12 combination,
6 queen, 16 subset). The original verification completed short execution checks
and an independent probe of every default three times; its JavaDoc check
passed doclint excluding missing tags. Fresh discovery is recorded separately.

The [first research report](../17-benchmarking/results/backtracking/README.md)
now completes all four Result/Interpretation fields with **46 means/errors**,
**460 measured** and **276 warmup averages**, completion **6 min 42 sec**.
Author-reported source is `b62df1b84810dfe5dea023d5d00bac2eb05c0cd0` (PR #88);
headers confirm JDK 21.0.9/JMH 1.37 and the controlled defaults. The author also
supplied Oracle/Windows 11 amd64/es_AR/UTF-8 metadata. Two errors reach 25%:
unpruned combinations 12/HALF and flags queens n=8. Every observation remains
in Markdown without JSON/log exports. Complete command, execution timestamp,
CPU/power, jar hash and profiler data remain unavailable; source attribution is
not embedded by JMH. Earlier module evidence and smoke stay separate.

Sudoku, graph colouring, mazes, branch-and-bound optimisation, symmetry-reduced
queens, streaming and production memoisation/tabulation are future extensions.
Local verification does not imply a configured GitHub Actions workflow.
