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

Normal tests and benchmark-profile clean package pass **672 repository tests**,
including **27 new module tests**, on OpenJDK 21. Four JMH templates contain
eight methods and **46 default cells** (12 permutation, 12 combination,
6 queen, 16 subset). All complete short execution checks; an independent probe
checks every default three times. JavaDoc passes doclint excluding missing tags.

`Result` and `Interpretation` remain pending author measurements on the merged
source. Smoke scores are not research. The protocol captures source SHA, actual
runtime, complete headers/iterations/errors and completion in Markdown, without
new JSON/log exports. Earlier module evidence retains its own provenance.

Sudoku, graph colouring, mazes, branch-and-bound optimisation, symmetry-reduced
queens, streaming and production memoisation/tabulation are future extensions.
Local verification does not imply a configured GitHub Actions workflow.
