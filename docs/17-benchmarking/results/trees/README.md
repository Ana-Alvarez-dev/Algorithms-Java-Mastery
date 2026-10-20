# Trees: First Reported Lookup Measurement Subset

The author supplied this completed **TreeOperationsBenchmark** execution on
**2026-10-19 (America/Buenos_Aires)**. It contains **24 configurations**,
**240 measurement-iteration averages** and **144 warmup iteration averages**.
The run completed in **3 minutes 35 seconds**. The tree lookup template now
records Result and Interpretation from this evidence.

The attachment also lists all twelve tree/heap benchmark methods. That discovery
listing contains **no heap measurement**. HeapOperationsBenchmark Result and
Interpretation therefore remain pending; this report covers lookup only.
All supplied printed results and iterations are retained in Markdown. No JSON,
log or copied console-text artifact is versioned.

## Provenance and Runtime

| Property | Supplied evidence or explicit limitation |
|---|---|
| Source | Author-pasted console attachment `Texto pegado(3).txt` |
| Reporting date | 2026-10-19, author's timezone; no execution timestamp in JMH output |
| Execution source attributed from author reflog | `c576cb79aa9747aced1cd041e97f64fc99e60010` (PR #76 merge); history supplied on 2026-10-19 |
| Reviewed source | `c576cb79aa9747aced1cd041e97f64fc99e60010`, merge of PR #76; implementation commit `e79f2d999dc0f4d2e36b8a03180f5c05491f8265` |
| JMH | 1.37, confirmed in all 24 headers |
| Runtime | JDK 21.0.9, Java HotSpot(TM) 64-Bit Server VM, 21.0.9+7-LTS-338 |
| Java invoker | `C:\Program Files\Java\jdk-21\bin\java.exe` |
| VM options | `<none>` |
| Blackhole mode | Compiler, auto-detected |
| Threads | 1, synchronised iterations |
| Warmup | 3 iterations per fork, 500 ms each |
| Measurement | 5 iterations per fork, 500 ms each |
| Forks | 2 per configuration |
| Mode and units | Average time (`avgt`), ns/op |
| Timeout | 10 minutes per iteration |
| Coverage | 6 lookup methods x 2 insertion orders x 2 sizes (100 and 1,000) |
| Measurement count | `Cnt = 10` iteration averages per cell, not ten individual invocations |
| Confidence output | 99.9% intervals, with JMH's printed normal-distribution assumption |
| Command line | Complete lookup invocation captured before the first header |
| Discovery listing | 6 tree lookup + 6 heap methods; listing alone is not a measured result |
| CPU, power settings and profiler metrics | Not supplied |
| Operating system | Windows PowerShell/executable path; Windows 11 amd64 reported separately during setup |

The captured lookup command, omitting only the local prompt, is:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*trees.TreeOperationsBenchmark.*" -p size=100,1000 -foe true
```

### Source checkout recovered from the author's reflog

After the output was supplied, the author initially reported current HEAD
`ce621c63fabc2fe04c180d71fd1f9e165cc8a447`. That revision merges PR #77, which
records these already supplied results, so it is later than the lookup run.
The author then supplied local reflog history on 2026-10-19:

| Local checkout | Reflog time (2026-10-19, UTC-03:00) | Meaning |
|---|---|---|
| `3f1e5dfb6a0dc5144fa426585a0a00ba5eeeec60` | 13:29:49 | Checkout before Trees; the earlier "No matching benchmarks" error belongs here |
| `c576cb79aa9747aced1cd041e97f64fc99e60010` | 14:01:52 | Fast-forward pull of PR #76's Trees implementation |
| `ce621c63fabc2fe04c180d71fd1f9e165cc8a447` | 22:02:31 | Fast-forward pull of PR #77's results documentation |

The successful output was supplied between the last two pulls. The author
history therefore identifies **`c576cb79aa9747aced1cd041e97f64fc99e60010`** as
the execution checkout, matching the reviewed source. Reflog timestamps identify
checkout changes, not the exact benchmark start/end time. This is provenance
supplied by the author, not an embedded JMH source stamp or a recovered jar hash.

A source comparison also confirms that all **13 Trees production/JMH Java
files** have the same executable text between `c576cb7` and `ce621c6` after
excluding block comments; only the lookup template's research comment changed.
That equivalence check does not replace the checkout timeline. The older
`3f1e5df` still lacks Trees and is not relabelled as the successful run's source.

Decimal commas are transcribed as dots, preserving three printed decimal places.
These are rounded console iteration averages, not full-precision JMH raw arrays
or individual-operation latency samples. Warmups remain separate from measured
means. No data is removed or replaced as an outlier.

## Timed Work and Analytical Model

The [implementation guide](../../../09-trees/implementation-and-benchmark-guide.md)
connects contracts, pseudocode, traces, correctness, costs and tests.
[TreeOperationsBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/trees/TreeOperationsBenchmark.java)
compares the manual unbalanced BST, manual AVL set and JDK TreeSet.

| Workload | One timed invocation | Restriction |
|---|---|---|
| Hit | Select next key from 0..n-1, advance wrapping cursor, test membership | Query selection is timed; every requested value is present |
| Miss | Test fixed absent key n, above every stored key | No cursor selection; this is one particular unsuccessful path |

Trial setup inserts the same keys 0..n-1. SORTED inserts them in increasing order;
SHUFFLED uses Fisher-Yates with SplittableRandom seed **20261019**. Each size has
one deterministic shuffled order, not a sample of all permutations. Construction,
initial boxing and updates/rotations are excluded from timed lookup. No source
invariant diagnostic is called by JMH.

Queries are preboxed once, and JDK TreeSet stores those same boxed references.
Manual APIs unbox to primitive int. Integer caching, comparator/library mechanics,
node representation and JIT decisions remain part of the comparison. A timing
difference does not isolate one balancing algorithm from these other costs.
The trees stay unchanged during lookup, with repeated warm state and one thread.
Hit and miss have different query-selection work and path distributions; their
difference is not an isolated hit-versus-miss outcome penalty.

## Complete Reported Means and Errors

Every cell is **mean +/- JMH-reported error in ns/op**, mode `avgt`, `Cnt = 10`.
SHUFFLED and SORTED refer to insertion order, not the order of measured queries.

| Method | Insertion order | Size 100 | Size 1,000 |
|---|---|---:|---:|
| `avlHit` | SHUFFLED | 6.419 +/- 2.434 | 15.752 +/- 8.681 |
| `avlHit` | SORTED | 5.934 +/- 1.042 | 9.965 +/- 0.482 |
| `avlMiss` | SHUFFLED | 6.585 +/- 2.400 | 12.149 +/- 3.858 |
| `avlMiss` | SORTED | 6.642 +/- 0.759 | 9.471 +/- 1.119 |
| `bstHit` | SHUFFLED | 8.676 +/- 1.044 | 16.224 +/- 2.283 |
| `bstHit` | SORTED | 85.424 +/- 12.292 | 1304.430 +/- 7.248 |
| `bstMiss` | SHUFFLED | 5.065 +/- 0.097 | 5.761 +/- 1.124 |
| `bstMiss` | SORTED | 170.056 +/- 15.668 | 3411.725 +/- 209.696 |
| `javaSetHit` | SHUFFLED | 11.164 +/- 1.773 | 36.011 +/- 2.270 |
| `javaSetHit` | SORTED | 9.526 +/- 0.474 | 36.183 +/- 0.965 |
| `javaSetMiss` | SHUFFLED | 6.452 +/- 0.129 | 14.048 +/- 0.325 |
| `javaSetMiss` | SORTED | 13.997 +/- 0.399 | 23.383 +/- 0.728 |

## Interpretation: Insertion Order and Lookup Paths

The largest measured changes occur in the **unbalanced BST with SORTED
insertion**. Successful lookup rises from **85.424 +/- 12.292** to
**1,304.430 +/- 7.248 ns/op**; the fixed upper miss rises from
**170.056 +/- 15.668** to **3,411.725 +/- 209.696 ns/op**. The corresponding
ratios of observed means are approximately **15.27** and **20.06** for a tenfold
population increase. They are descriptive ratios, not fitted exponents or a
claim that a linear source path must yield exactly tenfold elapsed time.

The source explains the structural contrast. Sorted insertion into this
unbalanced BST creates root 0 and a right chain 1..n-1. A successful query for
key k visits k+1 nodes; the absent key n visits all n nodes. Over one complete
uniform cursor cycle, a hit averages (n+1)/2 source-level node visits. These
are counts derived from the reviewed control flow, not measured CPU instruction
counts, branch counts or profiler observations.

| Population | Sorted BST edge-height | Mean hit node visits over a complete key cycle | Upper-miss node visits |
|---|---:|---:|---:|
| 100 | 99 | 50.5 | 100 |
| 1,000 | 999 | 500.5 | 1,000 |

Actual timed iterations are not asserted to end on a complete cursor cycle.
The derivation establishes linear source-path work for this shape. It does not
isolate the causes of the exact nanoseconds or their non-tenfold ratios: no
profiler or hardware/background-load experiment was supplied.

With SHUFFLED insertion, BST hit means are **8.676 +/- 1.044** and
**16.224 +/- 2.283 ns/op**, and fixed-miss means **5.065 +/- 0.097** and
**5.761 +/- 1.124 ns/op**. The upper miss follows one rightmost path in each
seed-specific tree. A nearly unchanged miss mean does not establish constant
worst-case search. These two shuffled fixtures do not prove an expected
random-BST bound; the probability model and many insertion samples would be
needed for such an empirical question.

AVL with SORTED insertion reports hit means **5.934 +/- 1.042** and
**9.965 +/- 0.482 ns/op**, and miss means **6.642 +/- 0.759** and
**9.471 +/- 1.119 ns/op**. The severe sorted-spine penalty is absent from these
reported AVL means, consistent with the separate logarithmic-height argument.
This is not a measurement of rotation/update cost, since construction is excluded.

For SHUFFLED AVL, all four reported errors exceed 25% of their means. In
particular, `avlHit` at 1,000 is **15.752 +/- 8.681 ns/op** (about **55.1%**
relative error). Its interval overlaps the SHUFFLED BST hit interval; no precise
winner or equality is inferred. JDK TreeSet's means also depend on size and
insertion order; they are a boxed standard-library reference, not an isolated
comparison of otherwise identical node algorithms. No formal significance,
equivalence or universal implementation-ranking claim is made.

## Supplied Statistics and Derived Fork Means

All supplied min/max, standard deviations and 99.9% confidence bounds below are
in **ns/op**. Min/max describe iteration averages, not individual operation
latencies. The exact printed bounds are retained even when rounding differs by
0.001 from arithmetic on the printed mean/error. Fork means are derived from
five rounded measured averages and rounded to three decimals.

| Method | Order | Size | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|
| `avlHit` | SHUFFLED | 100 | 5.500 | 10.637 | 1.610 | 3.984 | 8.853 | 5.564 | 7.273 |
| `avlHit` | SHUFFLED | 1000 | 9.957 | 26.198 | 5.742 | 7.071 | 24.432 | 19.736 | 11.767 |
| `avlHit` | SORTED | 100 | 5.432 | 7.373 | 0.690 | 4.892 | 6.977 | 6.280 | 5.589 |
| `avlHit` | SORTED | 1000 | 9.755 | 10.853 | 0.319 | 9.482 | 10.447 | 10.051 | 9.878 |
| `avlMiss` | SHUFFLED | 100 | 5.787 | 10.210 | 1.587 | 4.185 | 8.984 | 5.811 | 7.358 |
| `avlMiss` | SHUFFLED | 1000 | 9.072 | 17.361 | 2.552 | 8.291 | 16.006 | 10.635 | 13.662 |
| `avlMiss` | SORTED | 100 | 5.893 | 7.360 | 0.502 | 5.882 | 7.401 | 6.791 | 6.493 |
| `avlMiss` | SORTED | 1000 | 8.488 | 10.270 | 0.740 | 8.352 | 10.590 | 9.437 | 9.505 |
| `bstHit` | SHUFFLED | 100 | 7.761 | 9.401 | 0.691 | 7.632 | 9.720 | 8.650 | 8.702 |
| `bstHit` | SHUFFLED | 1000 | 14.476 | 18.001 | 1.510 | 13.941 | 18.507 | 16.199 | 16.249 |
| `bstHit` | SORTED | 100 | 78.996 | 104.738 | 8.130 | 73.133 | 97.716 | 85.837 | 85.011 |
| `bstHit` | SORTED | 1000 | 1297.930 | 1312.831 | 4.794 | 1297.182 | 1311.678 | 1304.549 | 1304.312 |
| `bstMiss` | SHUFFLED | 100 | 5.002 | 5.238 | 0.064 | 4.968 | 5.162 | 5.080 | 5.050 |
| `bstMiss` | SHUFFLED | 1000 | 5.102 | 7.334 | 0.744 | 4.636 | 6.885 | 5.910 | 5.611 |
| `bstMiss` | SORTED | 100 | 159.321 | 187.933 | 10.363 | 154.388 | 185.724 | 174.817 | 165.295 |
| `bstMiss` | SORTED | 1000 | 3200.349 | 3650.528 | 138.701 | 3202.029 | 3621.421 | 3385.738 | 3437.712 |
| `javaSetHit` | SHUFFLED | 100 | 10.339 | 14.012 | 1.173 | 9.391 | 12.936 | 10.514 | 11.813 |
| `javaSetHit` | SHUFFLED | 1000 | 34.732 | 39.823 | 1.502 | 33.741 | 38.281 | 36.690 | 35.331 |
| `javaSetHit` | SORTED | 100 | 9.165 | 10.016 | 0.314 | 9.051 | 10.000 | 9.493 | 9.558 |
| `javaSetHit` | SORTED | 1000 | 35.082 | 37.273 | 0.638 | 35.217 | 37.148 | 36.142 | 36.223 |
| `javaSetMiss` | SHUFFLED | 100 | 6.321 | 6.566 | 0.085 | 6.324 | 6.581 | 6.466 | 6.439 |
| `javaSetMiss` | SHUFFLED | 1000 | 13.697 | 14.313 | 0.215 | 13.723 | 14.373 | 14.211 | 13.887 |
| `javaSetMiss` | SORTED | 100 | 13.596 | 14.306 | 0.264 | 13.598 | 14.396 | 14.023 | 13.972 |
| `javaSetMiss` | SORTED | 1000 | 22.750 | 24.379 | 0.481 | 22.655 | 24.111 | 23.500 | 23.266 |

Every pooled mean matches the ten rounded iteration averages within 0.001
ns/op. All minima/maxima match the printed observations, and every final-table
row agrees with its result block and headers.

SHUFFLED AVL hit at 1,000 has derived fork means **19.736** and **11.767 ns/op**;
at 100 they are **5.564** and **7.273 ns/op**. This locates between-fork variation
without establishing its cause. No outliers were deleted and no fork is treated
as a separate experimental machine or individual invocation sample.

## Complete Measurement-Iteration Evidence

The following **240 values** are measurement-iteration averages in **ns/op**.
Each iteration contains many invocations; these are not operation percentiles.

| Method | Order | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---|---:|---:|---:|---:|---:|---:|---:|
| `avlHit` | SHUFFLED | 100 | 1 | 5.538 | 5.528 | 5.728 | 5.500 | 5.527 |
| `avlHit` | SHUFFLED | 100 | 2 | 6.461 | 5.851 | 5.881 | 10.637 | 7.534 |
| `avlHit` | SHUFFLED | 1000 | 1 | 11.478 | 22.942 | 17.360 | 20.700 | 26.198 |
| `avlHit` | SHUFFLED | 1000 | 2 | 9.957 | 9.969 | 12.681 | 13.434 | 12.794 |
| `avlHit` | SORTED | 100 | 1 | 5.432 | 5.510 | 6.166 | 7.373 | 6.918 |
| `avlHit` | SORTED | 100 | 2 | 5.469 | 5.527 | 5.554 | 5.455 | 5.941 |
| `avlHit` | SORTED | 1000 | 1 | 9.829 | 9.820 | 9.998 | 9.755 | 10.853 |
| `avlHit` | SORTED | 1000 | 2 | 9.864 | 9.880 | 9.827 | 9.903 | 9.917 |
| `avlMiss` | SHUFFLED | 100 | 1 | 5.837 | 5.839 | 5.796 | 5.796 | 5.789 |
| `avlMiss` | SHUFFLED | 100 | 2 | 5.787 | 5.946 | 5.994 | 8.853 | 10.210 |
| `avlMiss` | SHUFFLED | 1000 | 1 | 13.931 | 9.072 | 9.214 | 10.514 | 10.443 |
| `avlMiss` | SHUFFLED | 1000 | 2 | 12.803 | 14.167 | 12.142 | 11.838 | 17.361 |
| `avlMiss` | SORTED | 100 | 1 | 6.398 | 6.106 | 7.205 | 6.884 | 7.360 |
| `avlMiss` | SORTED | 100 | 2 | 6.065 | 5.893 | 6.710 | 6.861 | 6.937 |
| `avlMiss` | SORTED | 1000 | 1 | 8.619 | 8.488 | 9.902 | 10.119 | 10.056 |
| `avlMiss` | SORTED | 1000 | 2 | 9.015 | 8.541 | 9.468 | 10.270 | 10.231 |
| `bstHit` | SHUFFLED | 100 | 1 | 7.853 | 7.761 | 8.879 | 9.401 | 9.358 |
| `bstHit` | SHUFFLED | 100 | 2 | 7.832 | 8.182 | 8.985 | 9.145 | 9.364 |
| `bstHit` | SHUFFLED | 1000 | 1 | 14.549 | 14.478 | 17.120 | 17.266 | 17.583 |
| `bstHit` | SHUFFLED | 1000 | 2 | 14.513 | 14.476 | 16.850 | 17.407 | 18.001 |
| `bstHit` | SORTED | 100 | 1 | 80.609 | 80.818 | 86.456 | 89.354 | 91.949 |
| `bstHit` | SORTED | 100 | 2 | 104.738 | 79.510 | 78.996 | 81.702 | 80.110 |
| `bstHit` | SORTED | 1000 | 1 | 1307.157 | 1304.967 | 1299.868 | 1300.386 | 1310.366 |
| `bstHit` | SORTED | 1000 | 2 | 1297.930 | 1304.672 | 1300.816 | 1305.312 | 1312.831 |
| `bstMiss` | SHUFFLED | 100 | 1 | 5.238 | 5.002 | 5.076 | 5.057 | 5.028 |
| `bstMiss` | SHUFFLED | 100 | 2 | 5.043 | 5.051 | 5.070 | 5.039 | 5.049 |
| `bstMiss` | SHUFFLED | 1000 | 1 | 5.329 | 5.175 | 5.142 | 7.334 | 6.572 |
| `bstMiss` | SHUFFLED | 1000 | 2 | 5.148 | 5.102 | 5.812 | 6.069 | 5.923 |
| `bstMiss` | SORTED | 100 | 1 | 165.344 | 163.451 | 187.933 | 179.500 | 177.855 |
| `bstMiss` | SORTED | 100 | 2 | 159.321 | 161.445 | 180.776 | 164.882 | 160.053 |
| `bstMiss` | SORTED | 1000 | 1 | 3200.349 | 3650.528 | 3375.651 | 3326.016 | 3376.146 |
| `bstMiss` | SORTED | 1000 | 2 | 3592.620 | 3470.860 | 3311.238 | 3493.979 | 3319.864 |
| `javaSetHit` | SHUFFLED | 100 | 1 | 10.347 | 10.444 | 10.339 | 10.818 | 10.624 |
| `javaSetHit` | SHUFFLED | 100 | 2 | 11.315 | 10.902 | 10.477 | 12.357 | 14.012 |
| `javaSetHit` | SHUFFLED | 1000 | 1 | 39.823 | 35.579 | 35.703 | 36.777 | 35.570 |
| `javaSetHit` | SHUFFLED | 1000 | 2 | 34.912 | 34.732 | 34.865 | 35.554 | 36.592 |
| `javaSetHit` | SORTED | 100 | 1 | 9.184 | 9.222 | 9.584 | 10.016 | 9.460 |
| `javaSetHit` | SORTED | 100 | 2 | 9.165 | 9.289 | 9.596 | 9.842 | 9.898 |
| `javaSetHit` | SORTED | 1000 | 1 | 36.998 | 35.082 | 36.230 | 35.934 | 36.467 |
| `javaSetHit` | SORTED | 1000 | 2 | 35.900 | 35.603 | 37.273 | 36.317 | 36.022 |
| `javaSetMiss` | SHUFFLED | 100 | 1 | 6.321 | 6.436 | 6.446 | 6.566 | 6.562 |
| `javaSetMiss` | SHUFFLED | 100 | 2 | 6.365 | 6.390 | 6.404 | 6.505 | 6.529 |
| `javaSetMiss` | SHUFFLED | 1000 | 1 | 14.018 | 14.252 | 14.223 | 14.247 | 14.313 |
| `javaSetMiss` | SHUFFLED | 1000 | 2 | 13.697 | 13.839 | 14.121 | 13.819 | 13.957 |
| `javaSetMiss` | SORTED | 100 | 1 | 13.596 | 13.901 | 14.227 | 14.132 | 14.257 |
| `javaSetMiss` | SORTED | 100 | 2 | 13.706 | 13.701 | 13.936 | 14.209 | 14.306 |
| `javaSetMiss` | SORTED | 1000 | 1 | 24.379 | 22.866 | 23.206 | 23.780 | 23.269 |
| `javaSetMiss` | SORTED | 1000 | 2 | 23.001 | 22.750 | 23.468 | 23.631 | 23.479 |

## Complete Warmup Evidence

These **144 values**, in **ns/op**, are excluded from all measurement means.

| Method | Order | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---|---:|---:|---:|---:|---:|
| `avlHit` | SHUFFLED | 100 | 1 | 5.669 | 5.619 | 5.471 |
| `avlHit` | SHUFFLED | 100 | 2 | 7.006 | 5.726 | 5.563 |
| `avlHit` | SHUFFLED | 1000 | 1 | 12.281 | 15.231 | 64.781 |
| `avlHit` | SHUFFLED | 1000 | 2 | 10.769 | 10.288 | 10.327 |
| `avlHit` | SORTED | 100 | 1 | 5.590 | 5.553 | 5.403 |
| `avlHit` | SORTED | 100 | 2 | 6.618 | 5.923 | 5.589 |
| `avlHit` | SORTED | 1000 | 1 | 10.274 | 10.686 | 9.880 |
| `avlHit` | SORTED | 1000 | 2 | 10.094 | 9.905 | 10.027 |
| `avlMiss` | SHUFFLED | 100 | 1 | 5.833 | 5.834 | 5.793 |
| `avlMiss` | SHUFFLED | 100 | 2 | 5.935 | 5.825 | 5.877 |
| `avlMiss` | SHUFFLED | 1000 | 1 | 8.744 | 9.937 | 9.525 |
| `avlMiss` | SHUFFLED | 1000 | 2 | 10.053 | 8.924 | 9.975 |
| `avlMiss` | SORTED | 100 | 1 | 7.496 | 6.932 | 5.905 |
| `avlMiss` | SORTED | 100 | 2 | 6.175 | 7.657 | 5.938 |
| `avlMiss` | SORTED | 1000 | 1 | 8.654 | 8.733 | 8.535 |
| `avlMiss` | SORTED | 1000 | 2 | 8.896 | 8.601 | 8.512 |
| `bstHit` | SHUFFLED | 100 | 1 | 7.940 | 8.296 | 7.772 |
| `bstHit` | SHUFFLED | 100 | 2 | 8.017 | 8.324 | 8.019 |
| `bstHit` | SHUFFLED | 1000 | 1 | 15.487 | 14.384 | 14.438 |
| `bstHit` | SHUFFLED | 1000 | 2 | 15.836 | 14.260 | 14.818 |
| `bstHit` | SORTED | 100 | 1 | 79.646 | 84.734 | 80.776 |
| `bstHit` | SORTED | 100 | 2 | 80.186 | 87.970 | 80.350 |
| `bstHit` | SORTED | 1000 | 1 | 1334.040 | 1308.568 | 1317.005 |
| `bstHit` | SORTED | 1000 | 2 | 1337.279 | 1305.281 | 1329.121 |
| `bstMiss` | SHUFFLED | 100 | 1 | 5.671 | 6.248 | 6.065 |
| `bstMiss` | SHUFFLED | 100 | 2 | 5.114 | 5.099 | 5.105 |
| `bstMiss` | SHUFFLED | 1000 | 1 | 5.091 | 5.100 | 5.201 |
| `bstMiss` | SHUFFLED | 1000 | 2 | 5.735 | 7.367 | 5.260 |
| `bstMiss` | SORTED | 100 | 1 | 166.983 | 161.153 | 730.855 |
| `bstMiss` | SORTED | 100 | 2 | 161.122 | 158.783 | 158.301 |
| `bstMiss` | SORTED | 1000 | 1 | 3242.503 | 3206.785 | 3179.927 |
| `bstMiss` | SORTED | 1000 | 2 | 3278.288 | 3204.446 | 3311.489 |
| `javaSetHit` | SHUFFLED | 100 | 1 | 12.973 | 10.611 | 10.298 |
| `javaSetHit` | SHUFFLED | 100 | 2 | 11.771 | 10.315 | 10.340 |
| `javaSetHit` | SHUFFLED | 1000 | 1 | 436.022 | 44.405 | 41.473 |
| `javaSetHit` | SHUFFLED | 1000 | 2 | 30.726 | 36.531 | 34.796 |
| `javaSetHit` | SORTED | 100 | 1 | 9.410 | 11.335 | 9.195 |
| `javaSetHit` | SORTED | 100 | 2 | 9.373 | 10.939 | 9.155 |
| `javaSetHit` | SORTED | 1000 | 1 | 35.706 | 39.016 | 35.701 |
| `javaSetHit` | SORTED | 1000 | 2 | 35.149 | 37.444 | 35.574 |
| `javaSetMiss` | SHUFFLED | 100 | 1 | 7.151 | 6.464 | 6.765 |
| `javaSetMiss` | SHUFFLED | 100 | 2 | 6.502 | 6.466 | 6.327 |
| `javaSetMiss` | SHUFFLED | 1000 | 1 | 14.615 | 14.083 | 14.177 |
| `javaSetMiss` | SHUFFLED | 1000 | 2 | 14.531 | 14.027 | 13.975 |
| `javaSetMiss` | SORTED | 100 | 1 | 14.005 | 13.888 | 13.777 |
| `javaSetMiss` | SORTED | 100 | 2 | 14.029 | 13.868 | 13.887 |
| `javaSetMiss` | SORTED | 1000 | 1 | 23.476 | 24.566 | 22.725 |
| `javaSetMiss` | SORTED | 1000 | 2 | 23.355 | 23.865 | 22.858 |

## Research Limits and Handoff

- Execution source is attributed to PR #76 merge `c576cb7` from the author's
  checkout history and the reporting timeline, not from an embedded jar/source
  identifier. Exact benchmark timestamps, CPU, power settings and profiling
  evidence remain unknown.
- This measures **24 of 36 default lookup configurations**, with size 10,000
  unmeasured. No heap configuration is measured: the attachment's listing is
  discovery only. Full Trees defaults comprise 36 lookup plus 36 heap cells.
- **4 of 24 errors reach at least 25% of their means**, all SHUFFLED AVL cells;
  the largest is about 55.1%. The 99.9% interval is JMH's model output, not a
  guarantee about independent individual operations or real-world latency.
- Two populations and one shuffle per size cannot establish an asymptotic
  exponent or average over insertion permutations. The fixed upper miss is not
  a representative distribution of all absent queries.
- Lookup excludes updates, balancing, initial allocation and boxing. Repeated
  warm queries, one thread, compiler blackholes and primitive versus boxed API
  mechanics restrict applicability. No allocation-rate, heap footprint,
  cold-start, cache-cause or rotation-latency conclusion follows.
- Correctness remains the separate **524-test verification**, including **59
  Trees tests**, diagnostics and analysis. No new performance run or executable
  Java change is made by this documentation update. Timings do not replace
  invariants, proof arguments or functional tests.

The lookup source-to-run link now includes the author's checkout history.
Keep its `c576cb7` revision distinct from later documentation commits. The heap
subset is the next outstanding supplied evidence and needs its own source SHA:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*trees.HeapOperationsBenchmark.*" -p size=100,1000 -foe true
```

Preserve its own headers, final 24-cell table, errors, completion marker and any
available iterations. If it has already been run, supply that separate output;
this report does not assume the run failed or require lookup to be repeated.
Later experiments could sample several shuffle seeds or varied absent-key
positions at matching size/runtime, and use justified profilers to investigate
causal questions. Those experiments have not been executed here.

The lookup template now uses this complete initial subset as preliminary
empirical evidence, with source provenance identified from the author's reflog.
Heap Result/Interpretation remain pending their separate measured output.

## Source Integrity

SHA-256 of the original supplied attachment bytes, including line endings:

`1c5d67f1ec29aef8ab7945c5cc00e50b88594bcef6c95915840b27a8cfeb6a5b`

This identifies the received evidence. Decimal normalisation and Markdown
layout preserve every printed numerical observation. The raw attachment itself
is not copied into the repository. The separate discovery list and successful
lookup execution are distinguished from any earlier failed pattern match.
