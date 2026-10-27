# Common Mistakes

Backtracking correctness depends on the contract and rejection rules, not just
on using recursion. Review each of the following against a concrete trace.

| Mistake | Consequence | Selected correction |
|---|---|---|
| Treating used indices as value deduplication | Duplicate rows for repeated values | Canonical equal-copy order or frequency choices |
| Omitting sibling restoration | Constraints leak across branches | Mark/recurse/unmark or decrement/recurse/increment |
| Clearing irrelevant state instead of defining the live prefix | Obscures the invariant | Only slots below depth are logically live; copy completed rows |
| Saving a mutable path reference | Later recursion changes earlier output | Immutable snapshots of every finished row and outer list |
| Confusing partial failure with impossibility | Loses valid completions | Prove an extension cannot repair a rejected condition |
| Pruning `sum>target` for signed values | Rejects [8,-9] -> -1 | Signed suffix minimum/maximum bounds |
| Assuming an in-range target is feasible | Accepts parity/gap misses | Continue exact include/exclude decision |
| Using first-hit return in a full counter | Omits required solutions | Exhaust all branches for counting/enumeration |
| Extending shifts to arbitrary n | Java shift semantics break representation | Validate the explicit 0..12 queen domain first |
| Counting only distinct queen symmetries | Changes the ordinary-placement contract | Keep rotations/reflections separate |
| Reporting only output count as runtime/space | Hides scans, copying and retained rows | Separate visited states, local work, depth and output |
| Assuming candidate order always reduces full-search work | Misattributes a timing difference | Distinguish first-witness decisions from complete counts |

## Input and Arithmetic Policies

Validate nulls and caps before search/materialisation. Caps are explicit work/
output decisions, not maximal mathematical domains or fixed resource promises.
Empty constructions have specified meanings: one empty permutation/combination/
queen board; empty subset feasible at zero. Do not accidentally convert these
to no solutions. Signed subset sums use long; bounds compare directly rather
than overflowing `target-sum` at extreme targets.

## Verification Mistakes

Two implementations can share a bug. The 27 module tests use independent
index-permutation/dedup, bitmask, leaf-only queen and reachable-set references,
as well as identities, boundaries, restoration and nonmutation. Small exhaustive
checks complement the proof; they do not prove every accepted input.

Benchmark the specified public workflow: preparation inside the method remains
timed; trial generation stays outside. Counts do not measure enumeration, and
smoke does not supply research scores. Retain source SHA, actual Java runtime,
settings, all errors and complete console observations in Markdown. Do not
discard wide intervals or infer CPU causes from means alone.

## Next Module

Module 15 is Dynamic Programming. Identifying equivalent residual states and
their recurrence may allow memoisation/tabulation; improvement depends on
overlap and the cost of storing states. It is not an automatic replacement for
output-proportional enumeration. No production DP module is claimed here.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) joins these review decisions to code, tests, source references and
the four measurement templates and their recorded first-run evidence.

## Evidence From the First Run

The [first research report](../17-benchmarking/results/backtracking/README.md)
completes all four templates with 46 cells and every printed iteration/error.
Author-reported source is `b62df1b` (PR #88); headers confirm JDK 21.0.9/JMH 1.37.
Two wide-error cells remain intact. TWO combinations and early PRESENT subset
witnesses have lower unpruned means, illustrating why fewer calls or an added
bound cannot guarantee lower elapsed time. DISTINCT permutation variants have
equal candidate-check counts but different means; source ledgers alone do not
identify runtime causes. Preserve nonzero printed uncertainty even when tiny
rounded iterations coincide; do not turn smoke or a point mean into a proof.
