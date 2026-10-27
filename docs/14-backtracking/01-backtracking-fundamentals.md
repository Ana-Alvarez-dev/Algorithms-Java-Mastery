# Backtracking Fundamentals

Backtracking builds a partial solution, explores a legal choice, and returns to
the parent state before trying a sibling. Its search is commonly depth first.
It can enumerate solutions, count them, or decide whether any solution exists;
these are different output contracts.

## Search Contract

Specify the input domain, meaning of a solution, duplicate policy, output order,
empty case and stopping rule before writing recursion. In this module:

| Problem | Complete search | Decision |
|---|---|---|
| Permutations | All unique value arrangements or their exact count | Not selected |
| Combinations | All size-k subsets of 0..n-1 or their count | Not selected |
| N-Queens | All ordinary placements or their count | Not selected |
| Signed subset sum | Not materialised | Whether some subset of positions reaches the target |

An enumerator/counter must exhaust all relevant choices. A boolean decision can
return at its first witness. An empty permutation, combination or queen board
is one solution; an empty subset witnesses target zero.

## Recursive Scheme

```text
search(state):
    if complete(state): report/count/return witness
    for candidate in complete_candidates(state):
        if safe_to_extend(state, candidate):
            apply(candidate)
            explore(child)
            restore(parent)
```

SubsetSum instead passes index and sum by value; it has no mutable path to undo.
Restoration concerns relevant logical state, not clearing every array cell.

## Correctness Obligations

- Soundness: each reported result satisfies the contract.
- Completeness: each required solution has a generated path that is not pruned.
- Uniqueness: equivalent value arrangements have one canonical path.
- Restoration: siblings begin with their parent's constraint state.
- Termination: remaining positions/rows decrease within a bounded domain.

Pruning preserves completeness only when its rejection condition is sound.
It need not reduce work on every input, and a successful example is not a proof.

## Relationship to Other Paradigms

Greedy commits to a choice justified by the objective and its safe-choice proof.
Backtracking explores alternatives. Divide and Conquer decomposes a problem;
this module explores competing partial constructions. Dynamic Programming can
reuse equivalent residual states when their recurrence and overlap justify it;
memoisation is not used by these production searches.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) provides concrete contracts, pseudocode, traces, invariants,
complexity and Java/test/JMH links. Next: [state-space trees](02-state-space-trees.md).
