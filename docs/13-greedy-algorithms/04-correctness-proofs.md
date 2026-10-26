# Correctness Proofs

## Algorithms Java Mastery

The [implementation guide](implementation-and-benchmark-guide.md) gives the
selected contracts, pseudocode, traces, invariants and test mapping. A proof
applies to a specified objective, input domain and algorithmic model; passing
examples and elapsed timings supply different evidence. The chosen rule matters:
optimal substructure alone does not make every greedy choice safe.

## State What Is Being Proved

Define feasibility, the objective, empty/degenerate inputs, tie policies and
the residual problem after a choice. A typical safe-choice claim is existential:
**some optimum contains the chosen action**, not every optimum uses the same
action. Show how to exchange/transform an optimum without worsening it, then
show that an optimum of the reduced problem completes an optimum of the original.
Induct over a strictly decreasing measure and separately establish termination.

## Activity Exchange

For nonempty half-open intervals and an unweighted maximum-cardinality objective,
let g finish earliest. Replace the first interval o of an optimal compatible
plan by g. Since finish(g)<=finish(o), all later intervals remain compatible.
Cardinality is unchanged. Only intervals starting at or after finish(g) remain;
if the suffix were suboptimal, replacing it would improve the original plan.
Induction proves optimality. Finish/start ties only select a deterministic
representative, and touching intervals remain compatible.

The Java scan preserves compatibility and a prefix admitting an optimal
completion. Full input snapshots and order validation are separate implementation
responsibilities. A proof for cardinality does not extend to profit-weighted
scheduling: two short intervals of profit 1 can lose to one long interval of
profit 100 despite having greater cardinality.

## Room Assignment: Match a Lower Bound

This objective retains **every** interval and minimises room count. Process by
start time, maintaining each allocated room's last finish in a heap. Reuse a
room only when its finish<=start. If none is reusable, all allocated rooms have
an interval covering the current start. The new interval raises simultaneous
overlap to the new room count. Every legal assignment needs at least that many
rooms. The algorithm opens a room only when forced by this lower bound, so its
legal assignment is minimal. This is not a proof of activity selection under
a different name: the feasible outputs and objective are different.

## Huffman: Exchange and Contract

For at least two symbols with positive weights, an optimum can be a full binary
prefix tree. Place the two smallest weights into maximum-depth sibling leaves
by exchanges that cannot increase the weighted path sum. Contract those leaves
to weight x+y; expansion of an optimum for the contracted alphabet adds exactly
x+y. A better contracted solution would improve the original tree, contradicting
optimality. Induction justifies repeated two-minimum merging and the sum-of-merge-
weights identity. The guide develops the two-queue invariant and deterministic
tie rule used by the code.

Empty alphabets cost zero. A singleton uses the explicit nonempty codeword "0"
and costs its frequency; its special convention is not a zero-merge proof case.
Prefix-code payload optimality does not imply minimal total compressed-file
size after metadata or superiority to every other compression model.

## A Heuristic Can Be Valid Without Being Optimal

CoinChange.greedy preserves
amount=sum(denomination*quantity)+remaining with remaining>=0, and terminates
over a finite sorted denomination list. This proves its reported payment and
remainder are valid. It does not prove minimum coin count or completeness:
{1,3,4}, amount 6 gives three coins instead of two, and {3,4}, amount 6 leaves
remainder 2 despite a valid 3+3 representation. Carrying proves the selected
binary-denomination family optimal, not arbitrary coin systems.

## Relate Proof, Java and Tests

The proof assumptions appear as validation and ownership policies: positive
frequencies/coins, nonempty intervals, exact arithmetic, safe comparisons and
immutable results. Tests use exhaustive small alternatives and independent
oracles, not only agreement between two greedy implementations. Benchmark
templates ask bounded empirical questions; their pending Result/Interpretation
fields are not filled by correctness proofs or smoke timings. Later measurement
must retain source/runtime and all uncertainty.
