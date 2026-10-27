# Candidate Generation

Candidate generation defines which children can follow a partial construction.
It must cover every required solution and avoid unwanted equivalent paths.
Constraint checks alone cannot repair a missing candidate.

## Canonical Choices

| Problem | Candidates | Why this covers the contract |
|---|---|---|
| Unique permutations | Unused sorted positions, with equal-copy skipping; alternatively remaining value multiplicities | Each value prefix has one canonical representation |
| Combinations | Indices from `from` through `n-remaining` | Increasing order prevents reordering duplicates; upper bound leaves enough values |
| N-Queens | Columns not occupied or diagonally attacked | Every valid placement has one safe column at its next row |
| Subset sum | Include, then exclude the next position | Every subset of positions has a unique binary choice sequence |

For [2,1,1], sorting a private clone gives [1,1,2]. Merely tracking used indices
still permits duplicate value rows. The additional rule skips an equal copy
while its preceding equal copy remains unused. The frequency variant chooses
among distinct values with positive remaining multiplicity instead.

For choose(4,2), choosing 1 next permits 2 or 3, never 0. An unordered subset
therefore appears only in its increasing order. This API chooses from distinct
integers 0..n-1; it does not define combinations of an arbitrary multiset.

## Order and Stopping Rule

Ascending candidates give lexicographic enumeration. Reordering candidates may
change time to the first witness in a decision search. With unchanged variable
order and constraints, reordering siblings alone does not remove valid prefixes
from a complete count. Different constraints or an incumbent-dependent
optimisation bound would be a different experiment.

Full enumerators/counters explore all necessary candidates; SubsetSum can stop
at its first witness. Include-first is a documented policy, not a proof that
this order is optimal for every input.

## Engineering Scope

Sudoku, graph colouring and maze choices are useful conceptual extensions.
They are not implemented or counted in this module. The selected Java APIs
validate bounded work/output domains before recursion and preserve caller input.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) connects these rules to completeness/uniqueness proofs and independent
oracles. Next: [constraint validation](04-constraint-validation.md).
