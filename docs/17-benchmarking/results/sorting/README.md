# Sorting: First Reported Controlled Subset

The repository author supplied a complete JMH JSON export and log on
**2026-10-12**. They record **48 configurations**, with **480 measurement-iteration
averages** across two forks. Both benchmark templates now contain `Result` and
`Interpretation` sections based on this evidence.

**The execution is complete; the interpretation remains preliminary.** The log
ends with a completed run lasting 7 minutes 31 seconds. Several results are
highly variable, including the copy-only controls. Preserve that uncertainty
when discussing the measurements.

## Evidence and Provenance

- [JMH JSON export](2026-10-12-first-reported-run.json): every original score,
  error, confidence interval, percentile, metadata field, and per-fork raw array.
- [JMH log](2026-10-12-first-reported-run.log): warmup and measurement output,
  headers, warnings, completion marker, and the 48-row summary.
- The author supplied both exports inside Markdown code fences. Only the outer
  fences and surrounding whitespace were removed to make the export files usable.
  A local Windows user/repository path in the log's JMH jar warning was replaced
  with `file:<local-repository>/target/benchmarks.jar`. Timing values, headers,
  warnings, and execution settings were preserved. The JSON fields were not edited.
- All 48 rounded console means/errors agree with their full-precision JSON
  values. Every configuration has two arrays of five raw iteration averages.
  Their arithmetic means agree with the recorded primary scores.
- The source inspected for this report is main commit
  `fe13e3bf77d94226cbf97684a4debf98e44630a1`, which includes PR #67's Sorting
  implementations. The exports do not record the exact source commit or complete
  command used for the author's execution. This snapshot is a source-review
  reference, not a claim that the execution's commit was captured.
- Filenames identify the date the evidence was supplied. No execution timestamp
  was present in the supplied exports.

| Property | Confirmed evidence |
|---|---|
| JMH | 1.37 |
| Runtime JDK | 25.0.1 |
| VM | Java HotSpot(TM) 64-Bit Server VM, 25.0.1+8-LTS-27 |
| VM arguments | None recorded |
| Blackhole mode | Compiler, auto-detected in the log |
| Mode and unit | Average time, `us/op` |
| Threads | 1 |
| Forks | 2 per configuration |
| Warmup | 3 iterations per fork, 500 ms each |
| Measurement | 5 iterations per fork, 500 ms each |
| Measurement count | 10 iteration averages per configuration; not ten sorting calls |
| Sizes | 100 and 1,000 |
| Distributions | RANDOM, SORTED, REVERSE_SORTED |
| Coverage | 8 methods x 2 sizes x 3 distributions = 48 configurations |
| CPU and exact OS version | Not supplied; VM invoker and warning paths use Windows syntax |
| Profiler metrics | None supplied; secondary metrics are empty |

The project compiles for Java 21, and its original implementation verification
used Java 21. This benchmark used **JDK 25.0.1**. The library result therefore
measures `Arrays.sort(int[])` from the executing JDK 25 runtime. Do not label
this export as Java 21 runtime evidence or combine it with another JDK's scores
without identifying that change.

The log includes JMH initialization warnings about the terminally deprecated
`sun.misc.Unsafe::objectFieldOffset` method. The warnings occur across the 96
forks, and the run completes without a recorded benchmark failure. They are
retained as execution context; this report does not attribute timing variability
to them. Compiler blackhole mode should also remain part of future provenance.

## What Was Measured

The inspected benchmark source generates one deterministic input per
size/distribution pair in trial setup, using seed `20261009`. Each benchmark
invocation clones that preserved source. Sorting methods order the clone and
return it for JMH consumption. Generation is excluded from the timed operation.

**Sorting scores include array allocation/copy plus sorting.** Merge Sort also
allocates its auxiliary workspace inside the timed call. The two `copyOnly`
methods measure cloning without sorting and serve as experimental controls.
Their costs should not be mechanically subtracted to claim isolated sorting time.

According to the inspected definitions, RANDOM draws values over the integer
domain, SORTED contains `0..n-1`, and REVERSE_SORTED contains `n-1..0`. Each pair
repeatedly uses the same dataset. These are concrete workloads, not averages
over many random seeds or a complete worst-case investigation.

## Complete Reported Summary

Every table entry is **mean +/- JMH-reported error in us/op**, rounded to three
decimal places for readability. The JSON preserves full precision and all raw
iteration averages. The log identifies its intervals as 99.9% confidence
intervals under JMH's statistical assumptions.

### Efficient Group

| Distribution | Size | Copy only | JDK 25 Arrays.sort | Merge Sort | Quick Sort |
|---|---:|---:|---:|---:|---:|
| RANDOM | 100 | 0.078 +/- 0.012 | 1.098 +/- 0.142 | 3.105 +/- 1.109 | 2.328 +/- 1.506 |
| RANDOM | 1,000 | 1.022 +/- 0.338 | 14.921 +/- 1.309 | 40.193 +/- 16.990 | 87.551 +/- 158.007 |
| SORTED | 100 | 0.137 +/- 0.138 | 0.260 +/- 0.411 | 2.537 +/- 0.492 | 0.670 +/- 0.088 |
| SORTED | 1,000 | 1.107 +/- 0.793 | 1.298 +/- 0.315 | 53.682 +/- 66.156 | 11.806 +/- 1.879 |
| REVERSE_SORTED | 100 | 0.089 +/- 0.017 | 0.240 +/- 0.121 | 2.449 +/- 0.382 | 1.095 +/- 0.554 |
| REVERSE_SORTED | 1,000 | 0.988 +/- 0.553 | 2.367 +/- 2.075 | 30.058 +/- 2.503 | 12.740 +/- 4.516 |

`Arrays.sort` has the lowest reported sorting mean in this group in all six
size/distribution pairs. This is a description of the observed means, not a
universal ranking or a formal statistical test. For example, the sorted
100-element library measurement has an error larger than its mean.

Quick Sort has lower reported means than Merge Sort on the sorted and reverse
inputs at each measured size. The RANDOM case at 1,000 elements is too variable
for a precise comparison: Quick Sort's mean is 87.551 +/- 158.007 us/op. Its
separate fork means are approximately **149.892** and **25.211** us/op. Pooling
those values does not explain why the forks differ.

Merge Sort on sorted 1,000-element input is also highly variable, at
53.682 +/- 66.156 us/op. The experiment does not isolate JIT, allocation, GC,
hardware, or background-system effects, so it cannot establish their individual
contribution to the observed differences.

### Elementary Group

| Distribution | Size | Copy only | Selection Sort | Bubble Sort | Insertion Sort |
|---|---:|---:|---:|---:|---:|
| RANDOM | 100 | 0.308 +/- 0.591 | 9.902 +/- 29.847 | 3.898 +/- 1.697 | 1.484 +/- 0.542 |
| RANDOM | 1,000 | 1.584 +/- 2.241 | 320.550 +/- 98.867 | 923.241 +/- 175.611 | 75.763 +/- 10.892 |
| SORTED | 100 | 0.159 +/- 0.289 | 1.535 +/- 0.575 | 0.094 +/- 0.015 | 0.169 +/- 0.129 |
| SORTED | 1,000 | 8.836 +/- 31.165 | 103.916 +/- 17.889 | 2.736 +/- 5.653 | 1.781 +/- 2.007 |
| REVERSE_SORTED | 100 | 0.129 +/- 0.112 | 10.471 +/- 6.955 | 11.426 +/- 36.790 | 1.986 +/- 0.315 |
| REVERSE_SORTED | 1,000 | 1.128 +/- 0.581 | 2,339.062 +/- 6,286.460 | 391.304 +/- 233.725 | 331.021 +/- 508.882 |

Bubble and Insertion Sort have lower reported means on already sorted input
than on random or reverse input at both sizes. This is consistent with early
exit and fewer shifts, respectively. Wide uncertainty in the sorted 1,000-element
cases limits exact cost estimates and comparisons.

Selection Sort has larger means at 1,000 elements than at 100 for each measured
distribution. Its inspected implementation performs `n(n-1)/2` key comparisons
regardless of initial order. That count does not require equal measured time
across distributions: data movement, branch behavior, and execution variability
are distinct from the comparison count. This experiment does not isolate their
causal contributions.

## Variability and Copy Controls

For **14 of 48** configurations, the reported error is at least as large as
the score. For **25 of 48**, it is at least half the score. These descriptive
counts flag limited precision; they are not an algorithm-failure count or a
replacement for reviewing individual runs.

Two examples are especially important:

- Elementary `copyOnly`, SORTED, size 1,000: 8.836 +/- 31.165 us/op, with
  fork means approximately 0.931 and 16.741. One measurement iteration averages
  66.661 us/op.
- Selection Sort, REVERSE_SORTED, size 1,000: 2,339.062 +/- 6,286.460 us/op.
  One iteration averages **14,160.787 us/op**; the other nine range from
  approximately 848.909 to 1,436.183 us/op.

These are raw **iteration averages**, not individual operation latencies or
request-latency percentiles. All values, including these spikes, remain in
the export. No observations were deleted, winsorized, or replaced.

The elementary sorted copy-only mean exceeds the sorted Bubble and Insertion
means. The independently measured operations have substantial uncertainty;
this does not establish a negative sorting cost. Baseline subtraction would
obscure the instability rather than resolve it. The two groups' copy controls
also describe separate measurements, even when the source input pair matches.

## Scope and Limits

- This is the complete **48-configuration subset**, not the full default
  160-configuration matrix. No 10,000–1,000,000-element results or
  NEARLY_SORTED/DUPLICATE_HEAVY results were supplied.
- Two input sizes cannot identify an asymptotic exponent. Complexity statements
  still come from the algorithm analysis and its assumptions.
- The recorded errors are JMH's uncertainty estimates, not standard deviations
  or correctness failures. A confidence bound below zero does not represent
  physical negative execution time; retain the exported bounds as statistical
  output rather than silently changing them.
- Runtime scores include cloning and allocation. Space complexity, allocation
  rate, GC behavior, and object stability were not measured by a profiler here.
- Correctness evidence remains the separate JUnit suite and algorithm reasoning.
  No new performance run was executed to produce this report.

## Research Handoff

The templates and report can use this execution as a preliminary empirical
example. A future run should record its exact source commit, command, CPU and
OS version, retain all raw data, and investigate variation across forks and
iterations before asserting precise rankings.

If the research target is Java 21, select that runtime explicitly and store
the resulting run separately. The present JDK 25 run remains valid evidence of
what was measured on that runtime. Additional warmup, longer measurements,
more forks, and a separate GC-profiled experiment can help investigate the
variation, but none guarantees that every instability disappears.

Keep the first run and its uncertainty. Broader input sizes or additional
distributions should become separate evidence records, not overwrite this subset.

## Integrity Identifiers

SHA-256 identifiers allow the extraction and saved artifacts to be checked:

| Item | SHA-256 |
|---|---|
| Supplied JSON Markdown attachment | `c9a5f0105950ea327b58c8f8f6466612a2e846f48af26209156e5c311c2f07a0` |
| Supplied log Markdown attachment | `1354cd61ce6cb50d2dcb1aeabcf7aa6e0bf1c5e87980a96a37ea6e92d4a382cc` |
| Saved JSON after fence removal | `e73693361835cbb8417a3c33ffdc96fd1930e7b7d834b7dcf76c9eae977d7571` |
| Extracted log before local-path replacement | `2d78d7ba32b23f1d23317713d83133cb7df2fec7f4aab37df8c5dae6835af05b` |
| Saved log after local-path replacement | `dd0afe2f34eb126916353e75074158c6a42729457a36dd89a33f79b6e31c5844` |
