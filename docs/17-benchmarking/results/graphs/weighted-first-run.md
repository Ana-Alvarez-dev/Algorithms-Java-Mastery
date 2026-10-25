# Graphs: First Reported Weighted Results

The author supplied this completed execution on **2026-10-24**: **8 weighted
configurations**, **80 measurement-iteration averages** and **48 warmup averages**,
completed in **1 minute 14 seconds**. The weighted template's Result and
Interpretation now use this evidence. The separate [traversal report](README.md)
contains 24 more cells; together they cover all 32 current default configurations.
The two executions keep their own statistics and are not pooled.

## Provenance and Runtime

Both runs use the same source SHA supplied by the author with the outputs:
**`00fc9de90757eab77fbf6f824132d9bbea3f37e2`**, the merge of PR #80. It matches
the source reviewed here. JMH does not embed that identity; this is separately
reported checkout provenance, not a supplied jar hash. Later documentation
commits are distinct from the revision attributed to measurement.

| Property | Supplied evidence |
|---|---|
| Reporting date | 2026-10-24, America/Buenos_Aires; no execution timestamp in output |
| JMH | 1.37, present in every configuration header |
| Runtime | JDK 21.0.9, Java HotSpot(TM) 64-Bit Server VM, 21.0.9+7-LTS-338 |
| Java invoker | `C:\Program Files\Java\jdk-21\bin\java.exe` |
| VM options | `<none>` |
| Blackhole mode | Compiler, auto-detected |
| Threads | 1, synchronised iterations |
| Warmup | 3 iterations per fork, 500 ms each |
| Measurement | 5 iterations per fork, 500 ms each |
| Forks | 2 per configuration |
| Mode and units | Average time (`avgt`), **microseconds/op (`us/op`)** |
| Count | 10 measurement-iteration averages per cell, not ten invocations |
| Timeout | 10 minutes per iteration |
| Confidence output | 99.9% intervals, printed normal-distribution assumption |
| Full command | Initial pasted line is truncated; headers/coverage match the instructed protocol |
| CPU, power configuration, profiling | Not supplied |
| Operating system | Windows executable path; Windows 11 amd64 reported separately during setup |

Decimal commas become dots, retaining three printed decimals. These are rounded
console iteration averages, not full-precision raw JMH arrays or individual
operation latency distributions. Warmups stay separate. No observation or fork
is removed or replaced as an outlier. Printed CI endpoints are preserved,
including 0.001 rounding differences from arithmetic on printed mean/error.

Source text is `Texto pegado (2).txt`. The instructed command was:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*graphs.WeightedGraphBenchmark.*" -foe true
```

The initial pasted line is truncated, so this is the instructed protocol, not a
recovered complete command. Every header identifies Dijkstra or Kruskal,
SPARSE/DENSE and size 100/1,000, matching that protocol and the reviewed source.

## Timed Work and Analytical Model

The [implementation guide](../../../10-graphs/implementation-and-benchmark-guide.md)
and [weighted benchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/graphs/WeightedGraphBenchmark.java)
define connected undirected fixtures, positive int weights 1..1,000 and seed
**20261020**. SPARSE starts with a path backbone plus up to 3V pair attempts,
with loops/duplicates discarded. DENSE has every unordered distinct pair.
WeightedGraph construction is outside timing.

| Method | Timed work and output | Source model |
|---|---|---|
| dijkstra | Global nonnegative validation, long distances/parents, lazy queue entries, relaxation from source 0, result | O(V+E+E log(E+1)); stale entries contribute queue/state work |
| kruskalForest | Copy/sort canonical edges, initialise disjoint sets, scan candidates, select forest, create immutable result | Sorting and union/find plus O(V) initialisation; disconnected inputs remain supported |

Dijkstra computes source distances/paths; Kruskal computes a minimum spanning
forest. They solve different problems. Their means are not a ranking of
interchangeable algorithms, and the sparse/dense comparison is within each
method. Allocation, validation and result work are included, not isolated by
an allocation or sorting baseline.

## Complete Means and Errors

Every cell is **mean +/- JMH-reported error in us/op**, `avgt`, `Cnt = 10`.

| Method | Shape | Size 100 | Size 1,000 |
|---|---|---:|---:|
| `dijkstra` | SPARSE | 11.925 +/- 4.120 | 329.684 +/- 93.355 |
| `dijkstra` | DENSE | 47.341 +/- 1.909 | 5395.066 +/- 479.629 |
| `kruskalForest` | SPARSE | 27.518 +/- 0.332 | 668.781 +/- 79.978 |
| `kruskalForest` | DENSE | 782.274 +/- 10.156 | 163948.138 +/- 19262.711 |

## Interpretation: Density, Queue Work and Edge Sorting

Dijkstra SPARSE means are **11.925 +/- 4.120** and **329.684 +/- 93.355 us/op**;
DENSE means are **47.341 +/- 1.909** and **5,395.066 +/- 479.629 us/op** at
100/1,000 vertices. Both sparse errors exceed 25% of their means, limiting precise
ratios. This implementation validates all canonical edges and scans outgoing
adjacency, with a new queue entry on each strict improvement and stale-entry
skipping. Exact queue insertions or maximum live queue size are not printed by
the run and are not reconstructed from its timings.

Kruskal SPARSE means are **27.518 +/- 0.332** and **668.781 +/- 79.978 us/op**;
DENSE means are **782.274 +/- 10.156** and **163,948.138 +/- 19,262.711 us/op**.
For dense input the logical edge population rises from 4,950 to 499,500 (about
100.91 times). The dense Kruskal mean rises about 209.58 times. This is a
point ratio across two sizes, not a fitted exponent or an estimate of a single
cause. Sorting, comparisons, allocation, disjoint sets and runtime effects
were not isolated in separate experiments.

### What the reviewed Kruskal source actually scans

The source sorts a copy of **all canonical edges**, then loops over that full
list. It does not stop when a connected fixture has already selected V-1 edges.
For these connected loop-free fixtures, exactly V-1 unions succeed and the rest
are rejected as already connected. The following counts are derived from source
and complete-graph topology, not CPU/profile measurements:

| DENSE fixture | Canonical edges sorted / union attempts | Selected edges | Rejected attempts |
|---|---:|---:|---:|
| V=100 | 4,950 | 99 | 4,851 |
| V=1,000 | 499,500 | 999 | 498,501 |

These counts identify concrete work retained by this implementation, but do not
prove its share of elapsed time. A future controlled early-stop variant after
one component remains could be compared separately while retaining correctness
on disconnected forests; no such executable change is made by this report.
The temporary reference Prim algorithm used during earlier verification is not
a production alternative benchmarked in this run.

The largest cell, dense Kruskal at 1,000, averages **163.948 ms/op** after
converting the reported 163,948.138 microseconds. Relative to a 500 ms measurement
target, that suggests few invocations may fit in an iteration; the actual
invocation count was not supplied. This is an inference from the mean and target
duration, not a recovered count or a declaration that the run is invalid.
Measured iteration averages range **151,278.000–193,637.900 us/op**. Longer
measurement durations would be a distinct future control, not a reason to
invent observations or discard this supplied result.

Two weighted cells have errors above 25%, both SPARSE Dijkstra: about **34.5%**
at 100 and **28.3%** at 1,000. Their derived fork means are **11.113/12.736** and
**354.975/304.393 us/op**. These describe variation without assigning causes.
No significance, equivalence, universal ranking, memory-footprint or cache/GC
claim is made. Positive connected fixtures do not measure negative-edge
rejection, unreachable components or arbitrary weight distributions.


## Statistics and Derived Fork Means

All entries are **us/op**. Min/max are measured iteration averages, not single
operation latencies. Standard deviation differs from JMH error. Fork means are
calculated from five rounded measured averages, then rounded to three decimals;
they add summaries, not new observations.

| Method | Shape | Size | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|
| `dijkstra` | SPARSE | 100 | 10.491 | 19.453 | 2.725 | 7.805 | 16.045 | 11.113 | 12.736 |
| `dijkstra` | SPARSE | 1000 | 285.751 | 451.584 | 61.749 | 236.329 | 423.039 | 354.975 | 304.393 |
| `dijkstra` | DENSE | 100 | 45.142 | 48.856 | 1.263 | 45.432 | 49.250 | 47.993 | 46.688 |
| `dijkstra` | DENSE | 1000 | 5161.058 | 6140.648 | 317.245 | 4915.437 | 5874.695 | 5293.734 | 5496.399 |
| `kruskalForest` | SPARSE | 100 | 27.169 | 27.944 | 0.219 | 27.187 | 27.850 | 27.492 | 27.545 |
| `kruskalForest` | SPARSE | 1000 | 610.757 | 724.866 | 52.900 | 588.803 | 748.759 | 718.759 | 618.803 |
| `kruskalForest` | DENSE | 100 | 770.532 | 790.115 | 6.717 | 772.118 | 792.430 | 779.765 | 784.783 |
| `kruskalForest` | DENSE | 1000 | 151278.000 | 193637.900 | 12741.090 | 144685.427 | 183210.848 | 155823.760 | 172072.515 |

## Complete Measurement-Iteration Evidence

The following **80 values**, in **us/op**, are iteration averages.
Each timed iteration contains benchmark invocations; these are not request
percentiles or individual invocation samples.

| Method | Shape | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---|---:|---:|---:|---:|---:|---:|---:|
| `dijkstra` | SPARSE | 100 | 1 | 10.631 | 11.283 | 11.141 | 10.742 | 11.770 |
| `dijkstra` | SPARSE | 100 | 2 | 10.491 | 10.679 | 10.502 | 12.557 | 19.453 |
| `dijkstra` | SPARSE | 1000 | 1 | 294.376 | 290.874 | 436.204 | 451.584 | 301.838 |
| `dijkstra` | SPARSE | 1000 | 2 | 331.861 | 285.751 | 312.820 | 290.212 | 301.322 |
| `dijkstra` | DENSE | 100 | 1 | 48.078 | 48.031 | 47.920 | 47.720 | 48.216 |
| `dijkstra` | DENSE | 100 | 2 | 45.672 | 45.950 | 47.822 | 48.856 | 45.142 |
| `dijkstra` | DENSE | 1000 | 1 | 5309.096 | 5292.339 | 5392.822 | 5249.056 | 5225.358 |
| `dijkstra` | DENSE | 1000 | 2 | 5161.058 | 6140.648 | 5194.510 | 5782.472 | 5203.305 |
| `kruskalForest` | SPARSE | 100 | 1 | 27.441 | 27.671 | 27.537 | 27.388 | 27.424 |
| `kruskalForest` | SPARSE | 100 | 2 | 27.415 | 27.169 | 27.438 | 27.944 | 27.758 |
| `kruskalForest` | SPARSE | 1000 | 1 | 715.931 | 715.363 | 715.235 | 724.866 | 722.399 |
| `kruskalForest` | SPARSE | 1000 | 2 | 620.678 | 619.323 | 617.097 | 610.757 | 626.158 |
| `kruskalForest` | DENSE | 100 | 1 | 788.090 | 784.956 | 782.000 | 770.532 | 773.247 |
| `kruskalForest` | DENSE | 100 | 2 | 777.186 | 790.115 | 788.271 | 781.423 | 786.921 |
| `kruskalForest` | DENSE | 1000 | 1 | 158772.700 | 155136.000 | 156141.750 | 157790.350 | 151278.000 |
| `kruskalForest` | DENSE | 1000 | 2 | 164470.550 | 193637.900 | 160355.800 | 163720.725 | 178177.600 |

## Complete Warmup Evidence

These **48 values**, in **us/op**, are excluded from measurement
means and derived fork means.

| Method | Shape | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---|---:|---:|---:|---:|---:|
| `dijkstra` | SPARSE | 100 | 1 | 14.496 | 11.531 | 11.603 |
| `dijkstra` | SPARSE | 100 | 2 | 13.609 | 10.494 | 10.508 |
| `dijkstra` | SPARSE | 1000 | 1 | 356.263 | 352.618 | 302.827 |
| `dijkstra` | SPARSE | 1000 | 2 | 348.678 | 316.777 | 296.667 |
| `dijkstra` | DENSE | 100 | 1 | 62.505 | 54.102 | 49.439 |
| `dijkstra` | DENSE | 100 | 2 | 59.366 | 45.385 | 45.574 |
| `dijkstra` | DENSE | 1000 | 1 | 7414.309 | 5678.208 | 5712.840 |
| `dijkstra` | DENSE | 1000 | 2 | 6861.688 | 5388.671 | 6218.915 |
| `kruskalForest` | SPARSE | 100 | 1 | 40.994 | 28.209 | 27.216 |
| `kruskalForest` | SPARSE | 100 | 2 | 36.653 | 27.472 | 28.007 |
| `kruskalForest` | SPARSE | 1000 | 1 | 875.321 | 724.967 | 715.942 |
| `kruskalForest` | SPARSE | 1000 | 2 | 800.653 | 627.760 | 617.604 |
| `kruskalForest` | DENSE | 100 | 1 | 1015.330 | 790.974 | 794.440 |
| `kruskalForest` | DENSE | 100 | 2 | 1025.352 | 792.704 | 781.130 |
| `kruskalForest` | DENSE | 1000 | 1 | 265761.000 | 154831.350 | 157109.525 |
| `kruskalForest` | DENSE | 1000 | 2 | 262430.600 | 166560.800 | 165525.975 |

Every pooled mean agrees with its ten rounded observations within 0.001 us/op.
All min/max and sample standard deviations agree with those observations to
printed precision. Every final-table mean/error matches its result block and
complete header. No data was removed or substituted.

## Research Limits and Module Handoff

- All **8 current weighted defaults** are measured, but coverage is two sizes,
  two connected undirected shapes, one seed and positive weights. General
  directed/disconnected/zero/negative cases remain functional-test evidence,
  not measured performance evidence here.
- **2 of 8** errors reach 25% of their means, largest about 34.5%. Confidence
  intervals concern JMH's model, not latency percentiles or a guarantee of
  isolated/systematic-error-free data. No formal significance test is claimed.
- The SHA is separately author-reported. Exact execution timestamps, jar hash,
  CPU/power settings, actual per-iteration invocation counts and profiler metrics
  are not supplied; the pasted command is truncated.
- Algorithms have distinct output problems and allocation/validation/sort work.
  No production optimisation, isolated operation cost, cache cause, allocation
  rate or asymptotic exponent follows from these two-size means alone.
- Correctness remains the separate **572-test verification** and mathematical
  arguments; no new executable code or performance run is introduced here.
  The [traversal report](README.md) preserves its 24 cells separately. Overall,
  32 means/errors, 320 measured and 192 warmup iteration averages are recorded.

Both benchmark templates and the project review now point to the complete
initial default-grid evidence and its limits. Future seed/size/density,
queue/sort or early-stop experiments need their own source/runtime/settings and
complete uncertainty. Current observations are kept intact in Markdown without
JSON/log exports; later runs must not silently replace them.

## Source Integrity

Original attachment: `Texto pegado (2).txt`. SHA-256 of the supplied bytes, including
line endings:

`3948b7b323d008b18ef79fdc6928d8fa7a889e9fb6e0a2046289f42a281fd7ef`

The attachment is not copied into the repository. Decimal normalisation and
Markdown layout retain every printed numerical observation. No JSON/log export
is created. This source hash identifies the original received text, not a jar.
