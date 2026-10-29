# Activity Selection

## Algorithms Java Mastery

## Endpoint, tie and ownership details

See the [applied map](implementation-contracts.md) for the exact scan invariant
and validation order. The residual condition is start **>=** chosen.finish,
including touching endpoints. Equal records have no exposed identity tie.
selectSorted requires finish-then-start order and rejects all null elements
before its order scan; the algorithm is not silently sorting that input.
Snapshot semantics do not promise a newly allocated collection identity.

The [implementation guide](implementation-and-benchmark-guide.md) connects this
problem to ActivitySelection, Interval, exhaustive tests and JMH. The objective
is the **maximum number** of compatible nonempty intervals on one resource.
Intervals are half-open [start,finish), so finish==nextStart is compatible.
Signed int times are accepted; start>=finish is rejected by the domain record.
Weights, profits and priorities are not part of this objective.

## Strategy and Correctness

Sort by finish, then start; choose the first available interval, and subsequently
accept each interval whose start is at least the last chosen finish. The empty
plan admits a first negative-time interval without a zero-time sentinel.
Duplicate ranges are allowed in input but overlap, so both cannot be selected.
The immutable output contains original Interval values in selection order.

Replacing the first interval of an optimum by an earliest-finishing one cannot
invalidate later intervals. An optimum with that first choice therefore exists.
The suffix is an identical problem over intervals starting at or after its finish; induction
proves the cardinality result. See the guide for the scan invariant and manual
tie/endpoint trace. Earliest start, shortest duration or greatest local profit
are different rules and do not inherit this proof.

## Two Public Preconditions

| Operation | Caller input | Included work |
|---|---|---|
| select | Any valid interval order | Validate/snapshot, private sort, scan and immutable result |
| selectSorted | Nondecreasing finish **then start** | Full null/order validation, snapshot, scan and immutable result |

selectSorted rejects an ordering inversion, including a start tie-break inversion
at equal finish. Both variants reject null collections/items, accept empty lists,
preserve input and return the same value plan for matching ordered populations.
Input/output snapshots are part of the public contract, even when a caller
already has reusable order.

## Work and Space

Full selection has O(n log n) worst comparison sorting plus O(n) validation/scan.
The validated presorted operation has O(n+1) work. Its scalar scan state alone
is O(1), but its snapshot and materialised plan make the actual public-operation
space bound **O(n)**. Sorting workspace and output do not disappear merely
because the greedy decision uses only a last-finish variable.

The ActivitySelection benchmark compares complete calls on a shuffled trial
list versus a list ordered outside timing. That is an explicit difference in
caller preconditions, not free end-to-end sorting or an isolated sorting-cost
measurement. Both return immutable plans; size and compatibility shape also
change output sizes. The
[first report](../17-benchmarking/results/greedy/README.md) now supplies all
16 activity cells/errors and their printed iterations. Presorted means are
lower for the matching fixtures, but preparation stays outside timing. Seven
full-sort cells have error at least 25%, maximum about 48.6%; no isolated sort
cost, general ratio or formal significance/equivalence is claimed.

## Verification and Boundaries

The tests compare all 1,024 subsets of ten intervals on endpoints 0..4 with
exhaustive compatible-subset enumeration, plus 200 seeded lists, signed/extreme
times, ties, duplicates, immutability and rejection policies. These finite
checks complement the proof rather than replacing it. The next document
distinguishes this interval-selection problem from assigning every interval to
minimum rooms; weighted scheduling and general resource planning remain outside
this API.
