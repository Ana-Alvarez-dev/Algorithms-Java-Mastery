# Pruning

Pruning discards a partial branch using a justified impossibility condition.
For complete enumeration/counting, it must retain every solution required by
the contract. For optimisation, discarding a feasible branch instead requires
a bound proving that it cannot improve the incumbent; no such optimiser is
implemented here.

## Selected Rules and Controls

| Rule | Soundness | Control with the same result |
|---|---|---|
| Combination capacity | A next index above n-remaining leaves insufficient elements | Count without remaining-capacity restriction |
| Queen conflicts | Later placements cannot remove an existing attack | Flags and masks enumerate the same legal prefixes |
| Signed subset interval | Every continuation sum lies between suffix negative/positive bounds | Include-first decision without bounds |
| Permutation equal-copy rule | Equivalent value prefixes have one canonical index path | Independent index permutations then value deduplication in tests |

Bounds cost work too. SubsetSum prepares two O(n) suffix arrays inside the
timed operation; a quick witness may make that preparation visible. Root
rejection still includes preparation and is not a constant-time public call.

## Analytical Controls

For combinations, unpruned calls are sum(C(n,d), d=0..k); capacity-pruned calls
are C(n+1,k). At k=n they are 2^n versus n+1, while both return one. At n=18,
k=2 the difference is only 172 versus 171 calls. A large gain in one family
does not imply the same gain for every k.

For n all-two values, an absent target 2n+1 is rejected at the bounded root;
the unbounded method visits 2^(n+1)-1 nodes. The in-range odd target 2n-1 takes
2n+1 bounded calls but is still impossible. Target n, for even n, succeeds
after n/2+1 include-first calls in both methods. The signed alternating fixture
has no parity shortcut in production. Exact ledgers are in the [implementation and benchmark guide](implementation-and-benchmark-guide.md).

## Limits

Pruning benefit depends on the input family, stopping rule and predicate cost.
Exponential worst cases remain for general signed subset search. Particular
families can have much smaller search trees; there is no rule that pruning
always preserves an identical worst bound or always improves elapsed time.
All-even parity is an analytical fact about a fixture, not an implemented test.

First-witness early return is valid for boolean feasibility, but it would omit
solutions from a full enumerator. Candidate ordering can affect that first hit;
it does not alone reduce an unchanged complete counter's set of legal prefixes.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) provides correctness proofs and separates source node counts from
future JMH timings. Next: [permutations and combinations](06-permutations-and-combinations.md).

## First Measured Controls

The [first report](../17-benchmarking/results/backtracking/README.md) retains
all 46 cells and printed iterations from author-reported source `b62df1b`,
JDK 21.0.9/JMH 1.37. HALF/ALL combinations have lower pruned means, but TWO
has lower unpruned means at both n; only one call is avoided there. Subset
miss means favour bounds, while the early PRESENT witness favours the reference
without preparation. These controls reject a universal pruning-speedup claim.
Two wide-error cells stay visible; logical calls do not isolate predicate or
allocation cost. Proof of soundness remains separate from elapsed-time evidence.
