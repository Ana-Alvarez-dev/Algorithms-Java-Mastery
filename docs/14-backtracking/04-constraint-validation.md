# Constraint Validation

A constraint check describes which partial constructions remain legal. A
pruning predicate additionally needs a reason why a rejected state cannot
extend to a required solution. Failure to satisfy the final goal yet is not
enough to reject a partial state.

## Invariants Used in Java

| Search | Invariant before recursion | State update and restoration |
|---|---|---|
| Position permutations | Used positions exactly describe the live prefix; equal copies follow canonical order | Mark/unmark used flag; overwrite next path slot |
| Frequency permutations | Remaining multiplicities plus prefix multiplicities equal the input | Decrement/recurse/increment |
| Combinations | Prefix strictly increases; all next candidates are at least `from` | Pass candidate+1 and remaining-1 by value |
| Queen flags | Previous rows have distinct columns and nonconflicting diagonals | Set/clear column, row-column+n-1 and row+column flags |
| Subset sum | Sum equals the selected prefix positions; each remaining position is undecided | Pass new index/sum by value |

No live prefix slot is read before being written. Stale path slots beyond the
current depth are irrelevant; each complete row is copied before further search.
The result lists and their rows are immutable snapshots.

## Sound Rejection

Queen column/diagonal conflicts cannot be repaired by adding later queens, so
those candidates are rejected. A combination with too few available indices
cannot reach its required cardinality. Duplicate permutation suppression is a
canonical-representation argument, not a claim that equal values are invalid.

For signed subset sum, `sum > target` is unsafe: [8,-9] reaches -1 after an
overshoot. Let minimum[i] sum the negative suffix values and maximum[i] the
positive suffix values. Every continuation lies in
`[sum+minimum[i], sum+maximum[i]]`. Only an outside target is impossible by this
bound. Inside the interval is necessary, not sufficient: all-even values cannot
make an odd target.

## Proof and Tests

Establish the invariant at the root, preserve it on every accepted edge, prove
the terminal condition, and show restoration before the next sibling. Separately
show that every required solution survives the rejection rules. Finite tests
then compare against independent full permutations, bitmask subsets, leaf-only
queen checks and reachable-sum sets; cross-variant agreement alone is weaker.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) gives those arguments, exact arithmetic bounds and test links.
Next: [pruning](05-pruning.md).
