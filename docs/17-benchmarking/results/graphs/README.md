# Graphs: First Reported Traversal Results

The author supplied this complete execution on **2026-10-24**: **24 traversal
configurations**, **240 measurement-iteration averages** and **144 warmup
averages**, completed in **3 minutes 36 seconds**. The traversal template's
Result and Interpretation now use this evidence.

The separate [weighted run](weighted-first-run.md) records another eight cells.
Together the reports cover **all 32 current default configurations**, with
**320 measured and 192 warmup averages** preserved across separate executions.
They complete the requested initial evidence, not performance validation over
all possible graphs. Samples from the two runs are not pooled. Scores use
**microseconds**, distinct from the previous Trees module's nanoseconds.

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

The instructed traversal invocation was:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*graphs.GraphTraversalBenchmark.*" -foe true
```

It is not presented as an intact captured command line. Source text is
`Texto pegado(5).txt`; headers cover PATH/SPARSE/DENSE, sizes 100 and 1,000,
and list/matrix BFS/DFS, with the completion marker preserved in transcription.

## Timed Work and Fixture Model

The [implementation guide](../../../10-graphs/implementation-and-benchmark-guide.md)
connects specifications, traces, invariants, complexity and tests. The
[traversal benchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/graphs/GraphTraversalBenchmark.java)
constructs immutable list/matrix representations from identical canonical edges
outside timing. Seed **20261020** controls the generator.

All graphs are undirected and connected through a path backbone. PATH retains
only that backbone; SPARSE adds up to 3V pair attempts, discarding loops and
duplicates; DENSE includes every unordered distinct pair. Source 0 reaches all
vertices. Sparse exact edge counts are not printed by the author output and are
not invented here. They are bounded above by 4V-1, not asserted equal to 4V-1.

Timed calls include new local algorithm/frontier/output allocations and neighbour
scans, returning fresh results. BFS retains distances/parents and order; DFS
returns recursive-order visit order through explicit frames. Construction and
public defensive neighbour snapshots are excluded. BFS-versus-DFS scores do not
isolate queue-versus-stack cost, since their output/state responsibilities differ.

## Complete Means and Errors

Every cell is **mean +/- JMH-reported error in us/op**, `avgt`, `Cnt = 10`.

| Method | Shape | Size 100 | Size 1,000 |
|---|---|---:|---:|
| `listBfs` | PATH | 1.046 +/- 0.081 | 15.263 +/- 0.236 |
| `listBfs` | SPARSE | 2.717 +/- 0.130 | 34.781 +/- 0.360 |
| `listBfs` | DENSE | 26.143 +/- 3.430 | 2519.647 +/- 80.292 |
| `listDfs` | PATH | 1.956 +/- 0.433 | 19.648 +/- 0.582 |
| `listDfs` | SPARSE | 4.367 +/- 0.140 | 40.952 +/- 0.774 |
| `listDfs` | DENSE | 43.241 +/- 1.082 | 4185.886 +/- 170.704 |
| `matrixBfs` | PATH | 3.184 +/- 0.077 | 387.084 +/- 161.352 |
| `matrixBfs` | SPARSE | 5.641 +/- 0.074 | 624.284 +/- 12.492 |
| `matrixBfs` | DENSE | 26.245 +/- 2.275 | 3205.651 +/- 239.113 |
| `matrixDfs` | PATH | 4.944 +/- 0.187 | 379.654 +/- 108.557 |
| `matrixDfs` | SPARSE | 11.437 +/- 3.825 | 404.327 +/- 17.054 |
| `matrixDfs` | DENSE | 57.318 +/- 0.626 | 5506.411 +/- 138.519 |

## Interpretation: Representation and Graph Shape

For PATH and SPARSE in this subset, list traversal has lower reported means than
the corresponding matrix traversal. At SPARSE size 1,000, BFS reports
**34.781 +/- 0.360** versus **624.284 +/- 12.492 us/op**; DFS reports
**40.952 +/- 0.774** versus **404.327 +/- 17.054 us/op**. Those point comparisons
are specific to the reviewed fixtures, code and runtime, not a universal rule
for every graph application or a formal significance test.

The source provides an operation-count explanation: list iterators consume
stored adjacency entries, while every matrix iterator scans a complete row when
all vertices are reached. The following counts describe the fixture/scan domain,
not CPU reads, instructions, cache misses or measured profiler events:

| Shape / quantity | V=100 | V=1,000 |
|---|---:|---:|
| PATH logical edges | 99 | 999 |
| PATH stored adjacency entries | 198 | 1,998 |
| DENSE logical edges V(V-1)/2 | 4,950 | 499,500 |
| DENSE stored adjacency entries | 9,900 | 999,000 |
| Full matrix row positions across traversal | 10,000 | 1,000,000 |

Repeated iterator checks may inspect a present position more than once; the
matrix row-position count is not a count of exact Java reads or machine loads.
Both traversal forms also allocate/initialise O(V) state. Sparse list scanning
and dense matrix scanning therefore have distinct source work, without proving
that a particular hardware factor caused the observed wall times.

On DENSE input, E itself is quadratic in V, so O(V+E) list traversal does not
mean linear work in V. Dense BFS means are **26.143 +/- 3.430 / 2,519.647 +/-
80.292 us/op** for lists at 100/1,000 and **26.245 +/- 2.275 / 3,205.651 +/-
239.113 us/op** for matrices. The size-100 intervals overlap substantially;
there is no precise winner established there. Dense DFS grows from
**43.241 +/- 1.082** to **4,185.886 +/- 170.704** for lists and
**57.318 +/- 0.626** to **5,506.411 +/- 138.519 us/op** for matrices.
The graph's dense edge count grows about 100.91 times between sizes. These
observed increases are compatible with the analytical scan model; two sizes
do not fit an asymptotic exponent or identify exact runtime causes.

Three traversal cells have reported error at least 25% of the mean:
`matrixBfs/PATH/1000` (about **41.7%**), `matrixDfs/PATH/1000` (**28.6%**) and
`matrixDfs/SPARSE/100` (**33.4%**). PATH matrix BFS at 1,000 has derived fork
means **332.860** and **441.308 us/op**, locating observed variation without
attributing it to JIT, GC, cache or background activity. No outlier is discarded.


## Statistics and Derived Fork Means

All entries are **us/op**. Min/max are measured iteration averages, not single
operation latencies. Standard deviation differs from JMH error. Fork means are
calculated from five rounded measured averages, then rounded to three decimals;
they add summaries, not new observations.

| Method | Shape | Size | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|
| `listBfs` | PATH | 100 | 1.015 | 1.195 | 0.054 | 0.965 | 1.127 | 1.029 | 1.063 |
| `listBfs` | PATH | 1000 | 15.119 | 15.519 | 0.156 | 15.027 | 15.498 | 15.380 | 15.146 |
| `listBfs` | SPARSE | 100 | 2.656 | 2.949 | 0.086 | 2.587 | 2.847 | 2.754 | 2.680 |
| `listBfs` | SPARSE | 1000 | 34.525 | 35.200 | 0.238 | 34.421 | 35.141 | 34.829 | 34.734 |
| `listBfs` | DENSE | 100 | 24.736 | 31.311 | 2.269 | 22.713 | 29.573 | 24.818 | 27.468 |
| `listBfs` | DENSE | 1000 | 2475.131 | 2644.695 | 53.108 | 2439.355 | 2599.939 | 2486.651 | 2552.644 |
| `listDfs` | PATH | 100 | 1.749 | 2.565 | 0.287 | 1.523 | 2.389 | 1.769 | 2.143 |
| `listDfs` | PATH | 1000 | 19.095 | 20.180 | 0.385 | 19.066 | 20.229 | 19.570 | 19.725 |
| `listDfs` | SPARSE | 100 | 4.297 | 4.618 | 0.093 | 4.226 | 4.507 | 4.344 | 4.390 |
| `listDfs` | SPARSE | 1000 | 40.120 | 41.660 | 0.512 | 40.179 | 41.726 | 41.108 | 40.797 |
| `listDfs` | DENSE | 100 | 42.508 | 44.409 | 0.716 | 42.159 | 44.323 | 43.379 | 43.104 |
| `listDfs` | DENSE | 1000 | 4056.523 | 4400.782 | 112.910 | 4015.182 | 4356.590 | 4221.854 | 4149.917 |
| `matrixBfs` | PATH | 100 | 3.114 | 3.279 | 0.051 | 3.106 | 3.261 | 3.165 | 3.202 |
| `matrixBfs` | PATH | 1000 | 316.514 | 614.797 | 106.724 | 225.732 | 548.436 | 332.860 | 441.308 |
| `matrixBfs` | SPARSE | 100 | 5.572 | 5.732 | 0.049 | 5.566 | 5.715 | 5.612 | 5.670 |
| `matrixBfs` | SPARSE | 1000 | 612.661 | 634.240 | 8.263 | 611.792 | 636.776 | 631.508 | 617.060 |
| `matrixBfs` | DENSE | 100 | 24.746 | 28.293 | 1.505 | 23.970 | 28.520 | 24.841 | 27.648 |
| `matrixBfs` | DENSE | 1000 | 3071.386 | 3521.628 | 158.158 | 2966.538 | 3444.764 | 3267.207 | 3144.095 |
| `matrixDfs` | PATH | 100 | 4.812 | 5.155 | 0.124 | 4.756 | 5.131 | 4.896 | 4.991 |
| `matrixDfs` | PATH | 1000 | 262.269 | 499.925 | 71.804 | 271.097 | 488.211 | 386.889 | 372.418 |
| `matrixDfs` | SPARSE | 100 | 8.979 | 15.911 | 2.530 | 7.612 | 15.262 | 12.111 | 10.763 |
| `matrixDfs` | SPARSE | 1000 | 395.845 | 434.541 | 11.280 | 387.272 | 421.381 | 409.905 | 398.748 |
| `matrixDfs` | DENSE | 100 | 56.710 | 58.046 | 0.414 | 56.692 | 57.944 | 57.248 | 57.388 |
| `matrixDfs` | DENSE | 1000 | 5351.260 | 5695.697 | 91.622 | 5367.893 | 5644.930 | 5494.782 | 5518.041 |

## Complete Measurement-Iteration Evidence

The following **240 values**, in **us/op**, are iteration averages.
Each timed iteration contains benchmark invocations; these are not request
percentiles or individual invocation samples.

| Method | Shape | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---|---:|---:|---:|---:|---:|---:|---:|
| `listBfs` | PATH | 100 | 1 | 1.015 | 1.043 | 1.045 | 1.025 | 1.018 |
| `listBfs` | PATH | 100 | 2 | 1.021 | 1.048 | 1.030 | 1.021 | 1.195 |
| `listBfs` | PATH | 1000 | 1 | 15.519 | 15.437 | 15.275 | 15.473 | 15.194 |
| `listBfs` | PATH | 1000 | 2 | 15.119 | 15.209 | 15.125 | 15.138 | 15.139 |
| `listBfs` | SPARSE | 100 | 1 | 2.684 | 2.672 | 2.949 | 2.756 | 2.708 |
| `listBfs` | SPARSE | 100 | 2 | 2.677 | 2.704 | 2.656 | 2.674 | 2.689 |
| `listBfs` | SPARSE | 1000 | 1 | 34.729 | 35.093 | 35.200 | 34.542 | 34.579 |
| `listBfs` | SPARSE | 1000 | 2 | 34.883 | 34.952 | 34.525 | 34.642 | 34.667 |
| `listBfs` | DENSE | 100 | 1 | 24.771 | 24.916 | 24.923 | 24.744 | 24.736 |
| `listBfs` | DENSE | 100 | 2 | 31.311 | 26.345 | 29.150 | 25.576 | 24.960 |
| `listBfs` | DENSE | 1000 | 1 | 2488.739 | 2475.131 | 2481.908 | 2495.257 | 2492.218 |
| `listBfs` | DENSE | 1000 | 2 | 2644.695 | 2559.630 | 2481.710 | 2550.494 | 2526.691 |
| `listDfs` | PATH | 100 | 1 | 1.778 | 1.754 | 1.749 | 1.798 | 1.766 |
| `listDfs` | PATH | 100 | 2 | 2.304 | 2.152 | 2.565 | 1.914 | 1.781 |
| `listDfs` | PATH | 1000 | 1 | 19.133 | 19.644 | 19.750 | 19.533 | 19.790 |
| `listDfs` | PATH | 1000 | 2 | 19.095 | 19.301 | 20.180 | 19.901 | 20.148 |
| `listDfs` | SPARSE | 100 | 1 | 4.334 | 4.383 | 4.318 | 4.332 | 4.353 |
| `listDfs` | SPARSE | 100 | 2 | 4.312 | 4.618 | 4.297 | 4.384 | 4.337 |
| `listDfs` | SPARSE | 1000 | 1 | 41.596 | 41.660 | 41.169 | 40.355 | 40.758 |
| `listDfs` | SPARSE | 1000 | 2 | 40.797 | 40.737 | 40.897 | 41.433 | 40.120 |
| `listDfs` | DENSE | 100 | 1 | 42.741 | 42.508 | 44.276 | 44.409 | 42.959 |
| `listDfs` | DENSE | 100 | 2 | 42.691 | 43.008 | 42.679 | 43.116 | 44.028 |
| `listDfs` | DENSE | 1000 | 1 | 4400.782 | 4089.602 | 4222.856 | 4124.109 | 4271.922 |
| `listDfs` | DENSE | 1000 | 2 | 4298.217 | 4056.523 | 4063.944 | 4194.121 | 4136.781 |
| `matrixBfs` | PATH | 100 | 1 | 3.185 | 3.133 | 3.114 | 3.176 | 3.218 |
| `matrixBfs` | PATH | 100 | 2 | 3.279 | 3.155 | 3.231 | 3.208 | 3.138 |
| `matrixBfs` | PATH | 1000 | 1 | 390.356 | 317.439 | 320.499 | 319.490 | 316.514 |
| `matrixBfs` | PATH | 1000 | 2 | 549.220 | 614.797 | 348.118 | 367.530 | 326.877 |
| `matrixBfs` | SPARSE | 100 | 1 | 5.627 | 5.659 | 5.597 | 5.572 | 5.605 |
| `matrixBfs` | SPARSE | 100 | 2 | 5.618 | 5.654 | 5.707 | 5.637 | 5.732 |
| `matrixBfs` | SPARSE | 1000 | 1 | 634.240 | 625.895 | 632.836 | 632.782 | 631.786 |
| `matrixBfs` | SPARSE | 1000 | 2 | 617.456 | 612.661 | 617.187 | 615.596 | 622.398 |
| `matrixBfs` | DENSE | 100 | 1 | 24.857 | 24.803 | 24.746 | 24.879 | 24.920 |
| `matrixBfs` | DENSE | 100 | 2 | 27.710 | 28.293 | 27.243 | 27.622 | 27.373 |
| `matrixBfs` | DENSE | 1000 | 1 | 3521.628 | 3451.201 | 3122.624 | 3092.592 | 3147.991 |
| `matrixBfs` | DENSE | 1000 | 2 | 3247.065 | 3096.840 | 3201.468 | 3071.386 | 3103.718 |
| `matrixDfs` | PATH | 100 | 1 | 4.928 | 4.812 | 4.812 | 5.117 | 4.812 |
| `matrixDfs` | PATH | 100 | 2 | 5.031 | 5.155 | 4.907 | 4.899 | 4.964 |
| `matrixDfs` | PATH | 1000 | 1 | 499.925 | 296.079 | 338.140 | 392.720 | 407.582 |
| `matrixDfs` | PATH | 1000 | 2 | 465.636 | 262.269 | 383.717 | 398.150 | 352.317 |
| `matrixDfs` | SPARSE | 100 | 1 | 9.052 | 9.041 | 11.537 | 15.015 | 15.911 |
| `matrixDfs` | SPARSE | 100 | 2 | 8.979 | 9.232 | 11.117 | 12.968 | 11.517 |
| `matrixDfs` | SPARSE | 1000 | 1 | 434.541 | 407.516 | 399.372 | 403.005 | 405.093 |
| `matrixDfs` | SPARSE | 1000 | 2 | 403.648 | 397.341 | 395.845 | 396.862 | 400.045 |
| `matrixDfs` | DENSE | 100 | 1 | 57.364 | 56.710 | 57.579 | 56.862 | 57.724 |
| `matrixDfs` | DENSE | 100 | 2 | 58.046 | 57.147 | 57.186 | 57.006 | 57.554 |
| `matrixDfs` | DENSE | 1000 | 1 | 5468.374 | 5484.540 | 5695.697 | 5351.260 | 5474.037 |
| `matrixDfs` | DENSE | 1000 | 2 | 5564.641 | 5581.248 | 5502.041 | 5495.404 | 5446.871 |

## Complete Warmup Evidence

These **144 values**, in **us/op**, are excluded from measurement
means and derived fork means.

| Method | Shape | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---|---:|---:|---:|---:|---:|
| `listBfs` | PATH | 100 | 1 | 1.166 | 1.104 | 1.026 |
| `listBfs` | PATH | 100 | 2 | 1.140 | 1.032 | 1.028 |
| `listBfs` | PATH | 1000 | 1 | 17.881 | 15.414 | 15.374 |
| `listBfs` | PATH | 1000 | 2 | 16.673 | 15.316 | 15.099 |
| `listBfs` | SPARSE | 100 | 1 | 2.979 | 2.750 | 2.682 |
| `listBfs` | SPARSE | 100 | 2 | 3.005 | 3.112 | 2.970 |
| `listBfs` | SPARSE | 1000 | 1 | 38.642 | 34.809 | 34.859 |
| `listBfs` | SPARSE | 1000 | 2 | 39.105 | 34.794 | 36.514 |
| `listBfs` | DENSE | 100 | 1 | 27.447 | 25.563 | 25.219 |
| `listBfs` | DENSE | 100 | 2 | 27.399 | 26.120 | 28.344 |
| `listBfs` | DENSE | 1000 | 1 | 2603.793 | 2499.148 | 2694.812 |
| `listBfs` | DENSE | 1000 | 2 | 2583.470 | 2461.203 | 2495.386 |
| `listDfs` | PATH | 100 | 1 | 1.950 | 1.764 | 1.884 |
| `listDfs` | PATH | 100 | 2 | 2.390 | 2.224 | 2.345 |
| `listDfs` | PATH | 1000 | 1 | 21.326 | 19.670 | 19.432 |
| `listDfs` | PATH | 1000 | 2 | 21.997 | 19.247 | 19.267 |
| `listDfs` | SPARSE | 100 | 1 | 4.760 | 4.395 | 4.367 |
| `listDfs` | SPARSE | 100 | 2 | 4.791 | 4.314 | 4.381 |
| `listDfs` | SPARSE | 1000 | 1 | 45.834 | 41.947 | 41.032 |
| `listDfs` | SPARSE | 1000 | 2 | 45.610 | 42.383 | 40.943 |
| `listDfs` | DENSE | 100 | 1 | 46.454 | 42.775 | 42.105 |
| `listDfs` | DENSE | 100 | 2 | 46.119 | 41.963 | 42.584 |
| `listDfs` | DENSE | 1000 | 1 | 4301.007 | 4099.861 | 4200.866 |
| `listDfs` | DENSE | 1000 | 2 | 4251.270 | 4102.715 | 4124.764 |
| `matrixBfs` | PATH | 100 | 1 | 3.648 | 3.134 | 3.138 |
| `matrixBfs` | PATH | 100 | 2 | 3.796 | 3.233 | 3.198 |
| `matrixBfs` | PATH | 1000 | 1 | 343.336 | 321.701 | 322.261 |
| `matrixBfs` | PATH | 1000 | 2 | 334.953 | 321.316 | 321.907 |
| `matrixBfs` | SPARSE | 100 | 1 | 6.406 | 5.646 | 5.563 |
| `matrixBfs` | SPARSE | 100 | 2 | 6.598 | 5.702 | 5.672 |
| `matrixBfs` | SPARSE | 1000 | 1 | 650.954 | 635.454 | 635.609 |
| `matrixBfs` | SPARSE | 1000 | 2 | 641.604 | 628.343 | 623.521 |
| `matrixBfs` | DENSE | 100 | 1 | 27.946 | 25.715 | 24.896 |
| `matrixBfs` | DENSE | 100 | 2 | 31.312 | 27.857 | 27.759 |
| `matrixBfs` | DENSE | 1000 | 1 | 3544.116 | 3274.150 | 3123.586 |
| `matrixBfs` | DENSE | 1000 | 2 | 3353.161 | 3232.952 | 3054.532 |
| `matrixDfs` | PATH | 100 | 1 | 5.863 | 5.175 | 4.831 |
| `matrixDfs` | PATH | 100 | 2 | 5.495 | 5.091 | 5.087 |
| `matrixDfs` | PATH | 1000 | 1 | 295.213 | 326.627 | 671.053 |
| `matrixDfs` | PATH | 1000 | 2 | 290.896 | 265.935 | 340.984 |
| `matrixDfs` | SPARSE | 100 | 1 | 9.915 | 9.073 | 9.209 |
| `matrixDfs` | SPARSE | 100 | 2 | 9.855 | 8.894 | 8.758 |
| `matrixDfs` | SPARSE | 1000 | 1 | 465.034 | 405.926 | 399.745 |
| `matrixDfs` | SPARSE | 1000 | 2 | 441.412 | 407.585 | 400.660 |
| `matrixDfs` | DENSE | 100 | 1 | 63.090 | 57.604 | 57.203 |
| `matrixDfs` | DENSE | 100 | 2 | 64.090 | 56.593 | 59.029 |
| `matrixDfs` | DENSE | 1000 | 1 | 6021.541 | 5481.633 | 5453.659 |
| `matrixDfs` | DENSE | 1000 | 2 | 5901.830 | 5666.971 | 5503.620 |

Every pooled mean agrees with its ten rounded observations within 0.001 us/op.
All min/max and sample standard deviations agree with those observations to
printed precision. Every final-table mean/error matches its result block and
complete header. No data was removed or substituted.

## Research Limits and Module Handoff

- All **24 current traversal defaults** are measured, but only two populations,
  one fixed seed and connected undirected fixtures. Directed/disconnected,
  other source positions, larger sizes and repeated random seeds remain
  distinct experiments, not covered by the default grid.
- **3 of 24** reported errors reach 25% of their means, maximum about 41.7%.
  JMH's 99.9% model intervals are not individual-operation latency ranges or
  guarantees against systematic error. No formal significance/equivalence test
  is claimed and no precise ratio ranking is promoted to a general rule.
- Source identity was supplied by the author; the pasted command is truncated.
  Exact execution time, jar hash, CPU, power configuration and profiler data
  are not supplied. Reported date is a reporting date, not a captured timestamp.
- Work/output allocation is timed, graph construction is not. BFS and DFS have
  different result contracts. No allocation-rate, heap-byte, cache-cause,
  thread-safety or queue-versus-stack-only claim follows.
- Correctness remains the separate **572-test verification**, including **48
  Graphs tests**, proof arguments and source checks. This update changes no
  executable Java and runs no new performance experiment. Smoke times are not
  substituted for author measurements.

Both templates now retain preliminary Result/Interpretation supported by their
own runs. The [weighted report](weighted-first-run.md) preserves eight more
configurations with separate errors, iterations and a different completion time.
The project review and READMEs link this evidence without pooling samples.
Further experiments should record source/runtime/fixtures, complete errors and
available profiler/control information. No repeat is needed merely to record
the supplied results, and no unmeasured graph case is marked complete.

## Source Integrity

Original attachment: `Texto pegado(5).txt`. SHA-256 of the supplied bytes, including
line endings:

`a6881e06b746a20737125cd80e5ef8fe0c1097e80231e432135eb7d37f670a13`

The attachment is not copied into the repository. Decimal normalisation and
Markdown layout retain every printed numerical observation. No JSON/log export
is created. This source hash identifies the original received text, not a jar.
