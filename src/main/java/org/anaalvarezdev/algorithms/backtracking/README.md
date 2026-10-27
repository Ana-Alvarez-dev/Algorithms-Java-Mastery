# Backtracking Implementations

Package `org.anaalvarezdev.algorithms.backtracking` implements module 14.
The [authoritative guide](../../../../../../../docs/14-backtracking/implementation-and-benchmark-guide.md)
connects contracts, pseudocode/traces, correctness, costs, tests and measurement.

| Type | Selected API |
|---|---|
| Permutations | `unique`, `countUnique`, `countUsingFrequencies`; equal values indistinguishable |
| Combinations | `choose`, `countWithPruning`, `countWithoutPruning`; distinct universe 0..n-1 |
| NQueens | `solutions`, `countWithFlags`, `countWithBitMasks`; ordinary placements |
| SubsetSum | `exists`, `existsWithoutBounds`; signed position subsets and long target |

Inputs are preserved. Enumeration returns immutable rows/outer lists in
lexicographic order. Empty combinatorial constructions have one empty solution;
empty subset is allowed. Explicit work/output caps and exception policies are
specified in JavaDoc and the guide; these are educational bounded searches.
Count variants do not materialise rows, and decision returns at the first witness.

Tests in the matching test package execute 27 cases using independent exhaustive
oracles, identities, published counts, boundaries and ownership checks. Four JMH
classes in the matching JMH package define eight methods/46 defaults. Full
public workflow is timed; no memoisation/formula replaces the search. Author
measurement is pending; the guide has Windows Java 21 commands and the required
source/runtime/console evidence. No JSON/log output is needed.
