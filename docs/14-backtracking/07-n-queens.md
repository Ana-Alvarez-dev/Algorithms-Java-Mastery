# N-Queens

Place n queens on an n-by-n board with no shared row, column or diagonal.
The selected contract enumerates or counts **ordinary placements**: rotations
and reflections remain separate, rather than counting only fundamental classes.

## Contract and Representation

`NQueens.solutions(n)` returns immutable lexicographic rows of zero-based column
indices, one per board row. `countWithFlags` and `countWithBitMasks` return the
same exact long count without materialised boards. All accept n=0..12, with
the educational work/output cap checked before allocation or shifts.

n=0 has one empty board, n=1 one placement, n=2/3 none. For n=4 the two rows are
[1,3,0,2] and [2,0,3,1]. Published ordinary counts for n=0..12 are:

```text
1, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200
```

The external count reference is [OEIS A000170](https://oeis.org/A000170),
accessed 2026-10-26. Count agreement supplements the independent board oracle
and correctness argument; it is not a performance measurement.

## Flags and Restoration

At row r, flags record occupied columns and diagonals indexed by
`r-column+n-1` and `r+column`. A candidate with any occupied flag conflicts with
a previous queen. Otherwise mark all three, recurse to the next row, then
unmark before the next column. Later rows cannot repair an existing attack.
Every valid board has a safe next column, so no solution is lost. Fixed row
order gives each placement one path. The length-n column path is copied only
for enumeration; stale slots outside its current prefix are irrelevant.

## Available-Column Masks

With `full=(1L<<n)-1`, available columns are
`full & ~(columns | left | right)`. Extract `bit=available & -available`,
remove it, and recurse with the chosen column and shifted diagonal masks.
The cap validates n before shifts. This is a fixed-width representation for
the specified domain, not a solution for arbitrary n.

Flags scan n columns at each nonterminal visited prefix; masks enumerate safe
children directly. For V visited states, loose work bounds are O(nV) and O(V)
respectively (factorial upper bounds O(n*n!) and O(n!) when word masks fit).
Both have O(n) logical depth; constant-size mask state per frame does not imply
constant total stack. Enumeration also retains O((n+1)Q) output for Q boards.

## Verification and Experiment

Seven tests compare all solutions through n=9 against every complete column
permutation followed by diagonal checking only at the leaf. They also check
the published counts through 12, the 14,200-row boundary, reflection inclusion,
immutability, invalid caps and restoration across calls. The benchmark has six
defaults at n=8/10/12. It times full counts, including the flag arrays/path;
it does not isolate bit operations, allocation rates or enumerate boards.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) contains pseudocode, invariants, traces and source limits.
Next: [complexity analysis](08-complexity-analysis.md).
