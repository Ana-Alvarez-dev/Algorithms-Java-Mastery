# Permutations and Combinations

## Counts, output rows and accepted empty cases

The [applied map](implementation-contracts.md) proves both the prefix-tree
ledger and the retained-row bound, including the one empty row at n=0/k=0.
Counts are primitive results; materialisation copies outer/inner lists and
each chosen value. The n<=9 enumeration and n<=10 permutation-count caps
are independent of numeric overflow and are checked before preparing a clone.
Frequency counting still explores prefixes; it does not replace search with
a multinomial formula or DP cache.

Permutations distinguish value order; combinations ignore the order in which
distinct members were selected. The Java contracts choose canonical output
orders so repeated calls are deterministic.

## Unique Value Permutations

`Permutations.unique(int[])` preserves input and returns deeply immutable
lexicographic rows. Equal values are indistinguishable: [2,1,1] yields exactly
[1,1,2], [1,2,1], [2,1,1]. Empty input yields one empty row. Enumeration accepts
length 0..9; two exact-count variants accept 0..10. Null and larger inputs fail
explicitly. The caps bound work/output; they are not mathematical limits.

Sort a private clone, then scan positions at each depth:

```text
skip if used[i]
skip if i>0 and a[i]==a[i-1] and not used[i-1]
mark used[i]; write path[depth]
recurse to depth+1
unmark used[i]
```

Used-index tracking alone does not deduplicate repeated values. Requiring the
first unused equal copy gives a unique canonical path for every value prefix.
Every unique arrangement still has that path, proving completeness. The
alternative count loops over remaining value multiplicities and restores the
chosen count after recursion. Neither count uses factorial shortcuts or memo.

If multiplicities are m1..mu, unique rows P=n!/product(mi!), rather than always
n!. Position loops inspect n entries per nonterminal prefix; frequency loops
inspect u. Both also clone/sort/allocate. Enumeration additionally writes and
retains O((n+1)P) row/container output, separate from O(n) live search state.

## Combinations of 0..n-1

`Combinations.choose(n,k)` accepts 0<=k<=n<=18. Rows increase strictly and are
lexicographic; k=0 yields one empty row, including n=0. Invalid domains fail
before search. Both counters return exact C(n,k) without materialising rows.

```text
choose(from, remaining):
    if remaining==0: copy row / count one
    for candidate=from through n-remaining:
        write next candidate
        choose(candidate+1, remaining-1)
```

Increasing indices give every subset exactly one path. The upper bound is
sound because enough greater indices must remain. For choose(4,2), output is
[0,1], [0,2], [0,3], [1,2], [1,3], [2,3]. The unpruned counter instead loops
through n-1 and retains dead prefixes; both counts agree.

Output copying costs Omega((k+1)C(n,k)); result count alone does not describe
search work. At k=n, pruned calls are n+1, unpruned 2^n, with one output.
Largest accepted output is C(18,9)=48,620 rows; working depth/path is O(k+1).

## Evidence

Seven permutation tests compare small multisets against all index permutations
then value deduplication and BigInteger multinomials. Six combination tests use
independent bitmask subsets, BigInteger binomials, Pascal identity and boundary
materialisation. Ownership, invalid domains, extremes and restoration are tested.
The two count benchmarks each define 12 default cells; enumeration is not timed.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) contains complete proofs, cost ledgers and the measurement protocol.
Next: [N-Queens](07-n-queens.md).

## First Count Measurements

The [first report](../17-benchmarking/results/backtracking/README.md) records
12 permutation and 12 combination cells, with every error/printed iteration
from author-reported source `b62df1b`, JDK 21.0.9/JMH 1.37. Frequency means
are lower on the six matching permutation fixtures. DISTINCT has u=n and equal
candidate-check counts, so loop-width reduction alone does not explain all gaps.
Combination pruning helps HALF/ALL by point mean; TWO reverses that direction.
Unpruned 12/HALF error reaches 38.3% and its interval overlaps the pruned one.
These are complete primitive counts,
including private preparation; enumeration/copying throughput is not measured.
