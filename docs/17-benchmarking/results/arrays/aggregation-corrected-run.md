# Corrected Array Aggregation: Pending Author Run

**Status: protocol prepared; no controlled author measurements received.**
Prepared on **2026-10-27**, following main
`18a8ef53d4a1c4f1cd58c9caf22c72051be6b891` (Testing merge, PR #92).
That SHA identifies the starting source, not a future execution.

## Question and source distinction

Observe full-scan average time across five valid bounded inputs. The
[guide](../../implementation-and-benchmark-guide.md) defines contracts,
proof/trace, fixture tests, exact commands and timed work. Trial preparation
uses value[i]=i%100; the scan and Blackhole are timed. Input reuse is explicit.
Module 17 uses two forks; the previous class default was one.

The [historical Arrays report](README.md) retains five old ascending-payload
means and the two int-sum domain violations. Those means are not transplanted
here. Expected sums below are mathematical oracles, not timing observations.

## Execution provenance

| Field | Current evidence |
|---|---|
| Execution source SHA and clean/dirty state | Pending author run |
| Jar SHA-256 | Pending author run |
| Start timestamp / complete command | Pending author run |
| Actual JMH version / VM version / invoker | Pending author run |
| VM options / Blackhole mode | Pending author run |
| Actual OS / CPU / architecture / locale / encoding | Pending author run |
| Power / background-load conditions | Pending author run |
| Effective warmup / measurement / forks / threads / units | Pending author run |
| Completion marker / elapsed time | Pending author run |

Planned settings: JMH 1.37, Java 21, AverageTime ns/op, 3 warmup and 5 measured
iterations of 500 ms per fork, 2 forks, 1 thread. Effective run headers must
confirm settings; previously supplied Windows/Oracle metadata is not silently
reused as fresh metadata for this run.

## Result

| n | Expected sum (not measured timing) | Score (ns/op) | Error (ns/op) | Cnt |
|---:|---:|---|---|---|
| 100 | 4,950 | Pending | Pending | Pending |
| 1,000 | 49,500 | Pending | Pending | Pending |
| 10,000 | 495,000 | Pending | Pending | Pending |
| 100,000 | 4,950,000 | Pending | Pending | Pending |
| 1,000,000 | 49,500,000 | Pending | Pending | Pending |

Full evidence must retain all supplied result blocks and both forks' iteration
averages: nominally 50 measured plus 30 warmup averages. Until supplied, none
is inferred from a table, from local smoke, or from historical means. Missing
errors remain unknown, never zero. Preserve exact nonnumeric CI markers.

## Interpretation

Pending observations. Source reasoning predicts Θ(n) scan work and Θ(1)
auxiliary state, independent of whether finite timings scale proportionally.
The five-point series will describe this deterministic reused-array workload,
not arbitrary distributions, cold caches, allocation cost or multi-threading.
Any GC profile belongs to a separately identified execution with its own score,
metrics, units and provenance; none is currently recorded here.

Local tests, discovery and smoke are in the
[verification report](../../verification-report.md). They establish no author
research score. Versioned evidence stays Markdown; no new JSON/log/CSV exports.
