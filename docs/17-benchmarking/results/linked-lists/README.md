# Linked Lists: First Reported Measurement Subset

On **2026-10-19**, the repository author supplied a completed JMH console
summary containing **30 configurations**, each with **10 measurement-iteration
averages**. This record transcribes every reported mean and error into Markdown
and completes the benchmark template's `Result` and `Interpretation` sections.
No JSON or log file is versioned for this experiment.

The run completed in **4 minutes 29 seconds**. The interpretation remains
preliminary: only two sizes were measured and some errors are wide. Follow-up
evidence supplied on the same date confirms the runtime settings, provides
iteration values for five configurations, and identifies the author's executed
source checkout as commit `a16a431e5d7562557a06e795e332e1c8dfbac5ac`.

## Evidence and Provenance

| Item | Evidence and status |
|---|---|
| Evidence format | Author-pasted final table and completion footer, followed by headers/iterations for five ArrayList configurations and detailed result blocks for five configurations in total |
| Reporting date | 2026-10-19; the console excerpt has no execution timestamp |
| Mode and unit | Average time (`avgt`), `ns/op` |
| Coverage | 15 methods x sizes 100 and 1,000 = 30 configurations |
| Measurement count | `Cnt = 10` in every row; these are iteration averages, not ten individual list operations |
| Total represented iteration averages | 300; 50 rounded measurement-iteration values were supplied for five configurations; the other 250 remain unavailable |
| Blackhole mode | Compiler blackholes, explicitly identified in the supplied footer |
| Runtime confirmed in supplied headers | JDK 21.0.9, Java HotSpot(TM) 64-Bit Server VM, 21.0.9+7-LTS-338; separately identified as Oracle JDK by the author's version check |
| VM invoker | `C:\Program Files\Java\jdk-21\bin\java.exe` |
| JVM options | `<none>` in the supplied headers |
| Build environment reported separately | Maven 3.9.11 using Oracle JDK 21.0.9, Windows 11 amd64, OS version 10.0 |
| CPU and profiler output | Not supplied |
| Execution source commit reported by the author | `a16a431e5d7562557a06e795e332e1c8dfbac5ac`, supplied in response to the `git rev-parse HEAD` request |
| JMH version | 1.37 |
| Threads | 1; synchronised iterations |
| Warmup | 3 iterations per fork, 500 ms each |
| Measurement | 5 iterations per fork, 500 ms each |
| Forks | 2; both forks are present for each of the five supplied configurations |
| Timeout | 10 minutes per iteration |

The author-reported execution source matches the reviewed main
[commit a16a431](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/commit/a16a431e5d7562557a06e795e332e1c8dfbac5ac),
which merged Linked Lists PR #69. Its implementation commit is
`727616130fd05070ffbc212ee4b45328ad64dae8`. The source identifier is supplied by
the author; JMH's console does not embed it. Later commits documenting the
results are separate from the source revision used for this execution.

The follow-up includes matching headers for `arrayListMiddleGet` and
`arrayListPrependRemove` at sizes 100/1,000, and `arrayListTraverse` at 100.
They confirm JMH **1.37**, JDK **21.0.9**, one thread, three 500 ms warmup
iterations and five 500 ms measurement iterations. Both forks are present in
each configuration. These settings match the reviewed source defaults.
Headers for the remaining 25 configurations are not reproduced in the supplied
excerpt; their final rows all report `Cnt = 10`.

The instructed command was:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*linkedlists.*" -p size=100,1000 -foe true
```

This command is the protocol given to the author, not a command recovered from
the excerpt. Runtime selection matters: compiling with `release=21` alone does
not select the Java executable used to launch JMH. The earlier Sorting run used
JDK 25.0.1 and remains a separate experiment.

Decimal commas in the supplied console output are transcribed as decimal dots.
Every mean and error retains its three reported decimal places. No observation
has been replaced or removed. The earlier size-100 smoke run is not pooled with
these results.

## What Was Measured

The [implementation and benchmark guide](../../../06-linked-lists/implementation-and-benchmark-guide.md)
defines contracts, invariants, tests and the complete experiment. The
[benchmark source](../../../../src/jmh/java/org/anaalvarezdev/algorithms/linkedlists/LinkedListOperationsBenchmark.java)
compares three manual containers with `ArrayList<Integer>` and the executing
JDK's `LinkedList<Integer>`.

| Workload | Timed work | Analytical expectation |
|---|---|---|
| `*MiddleGet` | Return `get(size / 2)` on a populated container | ArrayList Θ(1); these linked representations Θ(n) |
| `*PrependRemove` | Add -1 at the head, then remove and return it; restore size and contents | ArrayList Θ(n) shifts; linked structures Θ(1) endpoint work |
| `*Traverse` | Enhanced-for traversal, unboxing and returned long checksum | Θ(n) for all five representations |

Trial construction and initial boxing of `0..n-1` are excluded. ArrayList has
capacity `n+1`, excluding capacity growth from the mutation pair. Node-allocation
sites belong to the timed linked mutation methods, subject to JVM optimisation;
no allocation rate was measured. The pair is neither isolated insertion nor
isolated removal. Traversal includes iterator creation, checks and dispatch.
The long checksum avoids integer overflow at the larger default sizes.

## Complete Reported Results

Each cell is **mean +/- JMH-reported error in ns/op**. `Cnt = 10` and mode
`avgt` apply to every cell. Smaller means represent less measured time for the
same workload and size; errors must remain visible when making comparisons.

### Fixed Middle Access

| Method | Size 100 | Size 1,000 |
|---|---:|---:|
| `arrayListMiddleGet` | 1.372 +/- 0.103 | 1.668 +/- 0.744 |
| `circularMiddleGet` | 36.933 +/- 5.906 | 671.151 +/- 114.886 |
| `doublyMiddleGet` | 32.421 +/- 6.455 | 1368.056 +/- 2343.650 |
| `javaLinkedListMiddleGet` | 93.933 +/- 121.224 | 694.916 +/- 77.854 |
| `singlyMiddleGet` | 36.618 +/- 7.630 | 677.253 +/- 89.571 |

ArrayList has the lowest reported mean at both sizes, with values close to
1–2 ns/op. This is consistent with direct indexing for a fixed, repeatedly
accessed target. The linked methods have larger means at size 1,000 than at
100, consistent with traversing links to reach the middle. Doubly linked
nearest-end traversal still needs about half the nodes for this target.

Two cells are particularly uncertain: `doublyMiddleGet` at 1,000 and
`javaLinkedListMiddleGet` at 100 have errors larger than their means. They do
not support a precise ranking among linked topologies. The measurements do not
identify why those cells vary or establish a universal speedup for ArrayList.

### Restored Prepend/Remove Pair

| Method | Size 100 | Size 1,000 |
|---|---:|---:|
| `arrayListPrependRemove` | 59.856 +/- 7.032 | 104.285 +/- 1.096 |
| `circularPrependRemove` | 27.276 +/- 11.496 | 20.840 +/- 3.835 |
| `doublyPrependRemove` | 20.281 +/- 4.194 | 16.247 +/- 3.378 |
| `javaLinkedListPrependRemove` | 16.040 +/- 2.029 | 17.384 +/- 3.274 |
| `singlyPrependRemove` | 9.425 +/- 1.470 | 11.242 +/- 7.069 |

ArrayList has the largest reported pair mean at both sizes and increases from
59.856 to 104.285 ns/op. Its two head mutations shift the existing references.
Linked methods update endpoints without traversing the existing sequence;
their means move in both directions between the two measured sizes.

These observations are consistent with the distinct movement requirements.
They neither prove constant-time behaviour nor imply that larger linked lists
are inherently faster. The ArrayList mean increases by less than the tenfold
size change; small-range timing need not scale like an asymptotic bound.
The singly linked pair has the lowest mean at both sizes, but its 1,000-element
error is wide. No general ranking among linked containers follows.

### Complete Traversal

| Method | Size 100 | Size 1,000 |
|---|---:|---:|
| `arrayListTraverse` | 53.972 +/- 4.858 | 971.031 +/- 382.618 |
| `circularTraverse` | 149.061 +/- 32.715 | 2488.897 +/- 513.506 |
| `doublyTraverse` | 187.847 +/- 23.380 | 4063.288 +/- 1464.355 |
| `javaLinkedListTraverse` | 163.251 +/- 37.224 | 2421.615 +/- 467.094 |
| `singlyTraverse` | 203.884 +/- 114.643 | 3644.669 +/- 566.782 |

Every traversal mean increases with size. ArrayList has the lowest reported
mean at each size, although all representations perform Θ(n) analytical work.
The experiment measures complete workloads with different iteration and storage
representations. It does not isolate the contributions of cache locality,
pointer access, checks, allocation, JIT decisions or background activity.

Traversal means increase by roughly 15–22 times for a tenfold size change.
Two sizes with the observed uncertainty cannot determine an exponent or
establish superlinear complexity. The source traversal remains a finite pass
over n elements.

## Additional Supplied Statistics

The follow-up supplied four complete ArrayList result blocks. Every number
below is in **ns/op**. Confidence intervals are the printed **99.9%** intervals
under the stated normal-distribution assumption.

| Method | Size | Mean +/- error | Min | Max | Standard deviation | Confidence interval |
|---|---:|---:|---:|---:|---:|---|
| `arrayListMiddleGet` | 100 | 1.372 +/- 0.103 | 1.313 | 1.533 | 0.068 | [1.269, 1.475] |
| `arrayListMiddleGet` | 1,000 | 1.668 +/- 0.744 | 1.320 | 2.681 | 0.492 | [0.924, 2.412] |
| `arrayListPrependRemove` | 100 | 59.856 +/- 7.032 | 56.081 | 70.439 | 4.651 | [52.824, 66.888] |
| `arrayListPrependRemove` | 1,000 | 104.285 +/- 1.096 | 103.201 | 105.788 | 0.725 | [103.189, 105.381] |

The earlier excerpt also supplied the detailed result block for
`singlyTraverse` at size 1,000:

| Statistic | Reported value (ns/op) |
|---|---:|
| Mean +/- error | 3644.669 +/- 566.782 |
| Minimum iteration average | 3175.079 |
| Maximum iteration average | 4096.028 |
| Standard deviation | 374.891 |
| 99.9% confidence interval | [3077.887, 4211.450] |

The block explicitly states that its interval assumes a normal distribution.
Its endpoints agree with the rounded mean plus/minus error. The standard
deviation is a different statistic from the reported error. Min/max describe
measurement-iteration averages, not fastest/slowest individual operations or
request-latency percentiles. Detailed blocks for the other 25 cells were not
supplied, so no equivalent statistics are reconstructed for them.

## Partial Iteration Evidence

The follow-up contains **50 measurement-iteration averages** and **30 warmup
iteration averages** for five configurations. Each value below preserves its
three printed decimal places in **ns/op**. Warmup is excluded from all reported
measurement means. Each iteration itself aggregates many operation invocations.

### Measurement Iterations

| Method | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---:|---:|---:|---:|---:|---:|---:|
| `arrayListMiddleGet` | 100 | 1 | 1.396 | 1.533 | 1.345 | 1.370 | 1.351 |
| `arrayListMiddleGet` | 100 | 2 | 1.320 | 1.435 | 1.313 | 1.316 | 1.344 |
| `arrayListMiddleGet` | 1,000 | 1 | 1.337 | 1.320 | 1.323 | 2.456 | 1.725 |
| `arrayListMiddleGet` | 1,000 | 2 | 1.450 | 1.409 | 1.478 | 1.501 | 2.681 |
| `arrayListPrependRemove` | 100 | 1 | 59.037 | 59.588 | 61.143 | 70.439 | 65.157 |
| `arrayListPrependRemove` | 100 | 2 | 56.673 | 56.881 | 56.901 | 56.658 | 56.081 |
| `arrayListPrependRemove` | 1,000 | 1 | 103.839 | 104.247 | 103.582 | 103.201 | 103.918 |
| `arrayListPrependRemove` | 1,000 | 2 | 104.522 | 104.665 | 105.788 | 104.803 | 104.283 |
| `arrayListTraverse` | 100 | 1 | 52.767 | 53.553 | 52.990 | 52.482 | 53.203 |
| `arrayListTraverse` | 100 | 2 | 52.913 | 53.047 | 52.737 | 52.952 | 63.081 |

### Warmup Iterations

| Method | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---:|---:|---:|---:|---:|
| `arrayListMiddleGet` | 100 | 1 | 1.339 | 1.385 | 1.379 |
| `arrayListMiddleGet` | 100 | 2 | 1.336 | 1.351 | 1.319 |
| `arrayListMiddleGet` | 1,000 | 1 | 1.341 | 1.356 | 1.327 |
| `arrayListMiddleGet` | 1,000 | 2 | 1.446 | 1.458 | 1.363 |
| `arrayListPrependRemove` | 100 | 1 | 60.616 | 62.645 | 58.925 |
| `arrayListPrependRemove` | 100 | 2 | 57.233 | 56.589 | 57.168 |
| `arrayListPrependRemove` | 1,000 | 1 | 110.775 | 106.738 | 108.266 |
| `arrayListPrependRemove` | 1,000 | 2 | 106.706 | 112.590 | 105.291 |
| `arrayListTraverse` | 100 | 1 | 54.192 | 53.347 | 53.021 |
| `arrayListTraverse` | 100 | 2 | 54.393 | 53.963 | 53.064 |

The ten measurement values per configuration reproduce the previously reported
means within **0.001 ns/op**, allowing for console rounding. They are rounded
iteration averages, so they cannot reproduce full-precision confidence estimates
exactly. The supplied `arrayListTraverse` excerpt ends after its final iteration;
its aggregate result remains supported by the earlier final table.

`arrayListMiddleGet` at 1,000 includes iteration averages of 2.456 and
2.681 ns/op, while its other eight range from 1.320 to 1.725 ns/op.
For `arrayListTraverse` at 100, nine iteration averages range from 52.482 to
53.553 ns/op; the last is 63.081 ns/op. These observations help locate variation
inside the supplied measurements. They do not identify a JIT, GC, cache or
operating-system cause. Every value is retained.

## Uncertainty and Limits

- **2 of 30** cells have an error larger than their mean. A statistical lower
  bound below zero is not evidence of physical negative execution time.
- The table preserves JMH-reported errors. No formal significance test or
  equivalence claim is made by comparing or overlapping intervals.
- Per-fork iteration values are available for only five configurations. The
  remaining 250 measurement averages cannot be reconstructed from the final
  table. No iteration evidence was supplied for the two highest-error cells,
  so their variability cannot be localised or explained here.
- Runtime settings are confirmed by the supplied headers and the source
  revision is identified by the author. CPU details remain unavailable. The
  exact shell command is the instructed protocol, not a captured command line.
- The supplied subset covers 30 of the 60 default configurations. Sizes
  10,000 and 100,000 remain unmeasured in this record.
- Fixed values, a fixed middle target, repeated warm access and the trial's
  interleaved construction of five representations restrict generalisation.
  No workload distribution, cold-memory study, concurrency test, heap footprint,
  allocation rate or GC profile is established here.
- The earlier Java 21 verification passed 338 repository tests, including 81
  Linked Lists tests. That is separate correctness evidence, not a claim that
  a fresh test run occurred on the author's machine during this benchmark.

## Research Interpretation and Handoff

The measured means illustrate a workload tradeoff: ArrayList has the lowest
middle-access and traversal means in this subset; linked structures have lower
prepend/remove pair means. Analytical operation counts explain why these are
reasonable hypotheses. The timings do not prove those counts or establish a
single best container for every application.

This closes the initial result-and-interpretation stage with preliminary
evidence. Follow-up evidence records the runtime settings and author-reported
execution source commit. Future runs should preserve the complete header, command,
commit, Java version, CPU/OS, completion marker and full result table as text
or legible captures, then record their provenance in Markdown. Keep separate
runs distinct and retain all reported errors. JSON and log files are not part
of the current repository evidence policy.

For deeper causal research, use additional sizes and separate profiler runs
with justified controls. If raw iteration values are unavailable, keep that
limitation visible instead of inferring a cause from a pooled mean.
