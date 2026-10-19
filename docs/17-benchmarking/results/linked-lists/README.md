# Linked Lists: First Reported Measurement Subset

On **2026-10-19**, the repository author supplied a completed JMH console
summary containing **30 configurations**, each with **10 measurement-iteration
averages**. This record transcribes every reported mean and error into Markdown
and completes the benchmark template's `Result` and `Interpretation` sections.
No JSON or log file is versioned for this experiment.

The run completed in **4 minutes 29 seconds**. The interpretation remains
preliminary: only two sizes were measured, some errors are wide, and the exact
execution commit and complete JMH header were not supplied.

## Evidence and Provenance

| Item | Evidence and status |
|---|---|
| Evidence format | Author-pasted final console table, completion footer and final `singlyTraverse` result block |
| Reporting date | 2026-10-19; the console excerpt has no execution timestamp |
| Mode and unit | Average time (`avgt`), `ns/op` |
| Coverage | 15 methods x sizes 100 and 1,000 = 30 configurations |
| Measurement count | `Cnt = 10` in every row; these are iteration averages, not ten individual list operations |
| Total represented iteration averages | 300; their individual values were not supplied |
| Blackhole mode | Compiler blackholes, explicitly identified in the supplied footer |
| JDK selected by the author | Oracle JDK 21.0.9, runtime 21.0.9+7-LTS-338, Java HotSpot(TM) 64-Bit Server VM |
| Runtime evidence boundary | The author verified the explicit JDK 21 executable before this run; the benchmark's JVM header is absent, so the runtime is author-reported rather than independently confirmed from this excerpt |
| Build environment reported separately | Maven 3.9.11 using Oracle JDK 21.0.9, Windows 11 amd64, OS version 10.0 |
| CPU, JVM arguments and profiler output | Not supplied |
| Exact execution commit | Not supplied; pending `git rev-parse HEAD` from the executed source checkout |
| Effective JMH version, threads, warmup, measurement duration and fork count | Header not supplied; source defaults are listed below as expectations, not confirmed runtime metadata |

The reviewed source is main commit
`a16a431e5d7562557a06e795e332e1c8dfbac5ac`, which merged Linked Lists PR #69.
Its implementation commit is `727616130fd05070ffbc212ee4b45328ad64dae8`.
These identify the definitions inspected for this report; neither is asserted
to be the author's execution commit.

The POM declares JMH **1.37**. The reviewed benchmark defaults are one thread,
three 500 ms warmup iterations, five 500 ms measurement iterations and two
forks. The reported `Cnt = 10` is consistent with two forks of five iterations,
but does not uniquely establish that configuration. The instructed command was:

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

The author also supplied the detailed result block for `singlyTraverse` at
size 1,000:

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
request-latency percentiles. Detailed blocks for the other 29 cells were not
supplied, so no equivalent statistics are reconstructed for them.

## Uncertainty and Limits

- **2 of 30** cells have an error larger than their mean. A statistical lower
  bound below zero is not evidence of physical negative execution time.
- The table preserves JMH-reported errors. No formal significance test or
  equivalence claim is made by comparing or overlapping intervals.
- Without per-fork and per-iteration values, we cannot inspect fork differences,
  diagnose outliers, reanalyse the distribution or explain timing variation.
- Header fields and the exact source commit remain explicitly pending. The
  source defaults and separately verified JDK must not be presented as a
  complete captured execution environment.
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
evidence. Provenance remains incomplete until the execution commit and header
are supplied. Future runs should preserve the complete header, command,
commit, Java version, CPU/OS, completion marker and full result table as text
or legible captures, then record their provenance in Markdown. Keep separate
runs distinct and retain all reported errors. JSON and log files are not part
of the current repository evidence policy.

For deeper causal research, use additional sizes and separate profiler runs
with justified controls. If raw iteration values are unavailable, keep that
limitation visible instead of inferring a cause from a pooled mean.
