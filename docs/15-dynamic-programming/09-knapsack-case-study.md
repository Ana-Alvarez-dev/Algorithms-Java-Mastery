# 0/1 Knapsack Case Study

## Safe arithmetic and deterministic selection

The [contract/proof map](implementation-contracts.md) shows that total value
is at most 256*MAX_INT, safely long, while selected weight stays within the
validated capacity. A strict row improvement can only occur when taking
that item is feasible. Omitting the largest remaining index on a tie
minimises the conceptual numeric mask by induction; it does not minimise
weight, item count or lexicographic index-list order. The shared grid bound
is checked before every variant, including empty/rolling cases.

Choose a subset of original item positions, using each at most once, with total
weight <=C and maximum value. Empty subset is allowed; capacity need not be
filled. This differs from fractional, unbounded and exact-weight knapsack.

## Selected Contract

`Knapsack01` accepts non-null equal-length arrays, positive int weights and
nonnegative int values. It preserves input, sums values in long and caps n<=256,
C<=10000 and (n+1)(C+1)<=1000000. All variants share that educational domain,
even rolling storage. Validate dimensions/product and elements before allocating.

K(i,c) is the optimum over the first i items. It either omits item i-1 or takes
it plus an optimum from earlier items at reduced capacity. Replacement of a worse
residual proves optimal substructure; this partition proves completeness. Base
K(0,c)=0 and decreasing item count establish the induction/termination argument.

For weights [10,20,30], values [60,100,120], C=50, the optimum is 220 using
indices [1,2]. Taking highest value/weight first is not an exact 0/1 algorithm.
The supplied CLRS §16.2 gives this contrast; exercise 16.2-2 asks for the DP.

## Implementations and Plan

- Dense memo: per-call -1-filled matrix, lazy nonbase expansion, O(n) depth.
- Full table: previous-item row already complete, capacities 0..C.
- One row: capacities descend so c-weight retains previous-item state.
- `select`: full table plus deterministic backward trace and immutable indices.

On equal optimum values, reconstruction omits the highest remaining index.
It therefore chooses the smallest numeric subset mask. It does not promise
minimum weight: weights [2,1], values [3,3], C=2 selects index 0. Unit-weight
duplicates remain distinct positions. An ascending one-row loop could take
one (2,3) item twice at C=4; the correct answer is 3, not 6.

## Cost and Verification

O(nC) is **pseudopolynomial** in numeric C, not polynomial in binary input length.
Dense memo still reserves/fills the whole grid, even when every item is too heavy
and only n nonbase states expand. Rolling retains O(C+1); plan reconstruction
uses the full grid plus O(n) output. Nine tests include all 7,381 small arrays
at nine capacities (66,429 cases) against independent subset enumeration and
numeric-mask tie resolution; seeded arrays, exact long sums, caps and ownership.

The three value-only methods define 24 JMH defaults over n=16/64, C=32/128 and
LIGHT/TOO_HEAVY. Full setup/validation stays timed; trial generation is excluded.
The [first report](../17-benchmarking/results/dynamic-programming/README.md)
now records all 24 cells/errors and printed iterations, source `d572651`
separately author reported, JDK 21.0.9/JMH 1.37 in headers. Rolling point means
are lower throughout; memo/table order is mixed and five interval pairs overlap.
No error reaches 25%, which does not isolate systematic/runtime effects.
The source ledger distinguishes dense cells, memo expansions and rolling
updates; heavy inputs still initialise the dense cache. Plan output is not timed
and no precise setup share, CPU cause or bit-polynomial bound is inferred.
The [implementation and benchmark guide](implementation-and-benchmark-guide.md) connects recurrence, proofs, traces, tests and protocol. Next:
[longest common subsequence](10-longest-common-subsequence.md).
