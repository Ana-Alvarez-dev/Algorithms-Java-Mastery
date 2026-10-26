# Interval Scheduling and Partitioning

## Algorithms Java Mastery

The [implementation guide](implementation-and-benchmark-guide.md) distinguishes
two objectives that are often confused. Classical unweighted interval scheduling
is the **same mathematical selection problem** as activity selection; it is
not a second independent algorithm copied into this package.

| Objective | What may be discarded? | Selected implementation |
|---|---|---|
| Maximise compatible intervals on one resource | Overlapping candidates may be omitted | ActivitySelection, earliest finish |
| Assign every interval using minimum rooms | Nothing may be omitted | IntervalPartitioning, start order plus earliest available room |

Both use the shared immutable, nonempty half-open Interval. Endpoints touching
at a common time do not overlap. Empty/reversed intervals are rejected; negative
times are allowed. Null collections/items fail, input order is preserved and
returned plans/assignments are immutable.

## Selection Alias

Earliest-finish selection uses the exchange proof in the previous document and
guide. Its public API snapshots input and materialises a result: O(n log n)
worst sort-plus-scan, O(n+1) validated presorted work, and O(n) worst space.
Only its scalar scan state, excluding snapshots/sort/output, is constant.
Profit-weighted selection is not solved by this unweighted rule.

## Minimum-Room Assignment

IntervalPartitioning sorts by start, finish and original index. Its heap keeps
one latest (finish,id) for **every allocated room**, including idle rooms.
If the minimum finish<=nextStart, reuse that room; otherwise open the next id.
After assignment, insert the room's new finish. There is no need to remove every
expired room: only one next interval needs a room, and unused idle rooms remain
available. Finish ties use room id; this does not promise the smallest free id
across different finish times.

The immutable Assignment stores one zero-based room id at every original input
position, plus roomCount. Duplicate intervals must all be assigned and can force
separate rooms. A legal per-room plan orders intervals with previousFinish<=start.

## Optimality by Overlap

At a new allocation, every existing room has an interval ending after the current
start. Their starts were already processed, so all overlap the new interval at
that time. Any valid assignment needs the new room count. The algorithm opens
rooms only at this forced lower bound, proving minimality; otherwise it legally
reuses a room. The guide traces the original-index assignment [1,0,1,0] for
[(5,7),(0,4),(1,4),(4,5)].

For n intervals and r allocated rooms, ordering is O(n log n) worst work,
heap maintenance O(n log(r+1)), and complete state/output O(n), with O(r) heap
entries. A heap of room availability is not an event-sweep heap containing every
active interval; its invariant and operation counts are different.

## Evidence and Limits

All 1,024 small interval sets and 500 seeded schedules are checked against an
independent maximum-overlap oracle, with per-room legality, original indices,
determinism and ownership checks. The benchmark times complete assignment
across size and overlap shapes, not activity-selection cardinality or isolated
heap costs. The [first report](../17-benchmarking/results/greedy/README.md)
now supplies all eight assignment cells/errors and printed iterations on JDK
21.0.9/JMH 1.37, with author-reported source `674ab35`. At 10,000, disjoint/
touching means are 1901.335 +/- 19.514 / 1894.487 +/- 45.547 us/op and their
confidence intervals overlap. Known room/heap operation counts are analytical, not timing
attribution; output objectives, input ordering and allocations stay distinct.
Multi-room weighted priorities, resource capacities and real-time/preemptive
scheduling have different contracts and are not claimed by these examples.
