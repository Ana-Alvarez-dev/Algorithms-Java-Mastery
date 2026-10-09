# Searching: First Reported Benchmark Run

## Algorithms Java Mastery

The first Searching benchmark summary was supplied by the repository author on
2026-10-09. This record completes the `Result` and `Interpretation` sections of
the three JMH benchmark templates with observed evidence.

**Status: first execution recorded; interpretation is preliminary.** The data
support studying growth trends. Several measurements have wide reported error
margins, and the run header and per-iteration output were not supplied.

## Evidence and Provenance

- [Complete transcribed summary](2026-10-09-first-run-transcribed.csv): 65 rows,
  covering four benchmark methods and five input sizes.
- The CSV was transcribed from the author's pasted console table. It preserves
  every displayed mean and error, converting decimal commas to decimal points.
  It is not the original JMH CSV or JSON export and contains no iteration data.
- Production code and benchmark definitions were inspected at main commit
  `709c539337bb0755661969c24ebc2351d86b9416`, which includes the LinearSearch fix.
  The exact commit used by the author's benchmark run was not captured in its
  supplied output.
- The author previously verified Oracle Java/Javac/Maven runtime 21.0.9 on
  Windows 11. The supplied benchmark table alone does not verify its JVM,
  CPU, JVM options, thread count, command, or runtime conditions.

| Property | Evidence |
|---|---|
| Mode and units | `avgt`, `ns/op`, in the supplied table |
| Measurement count | 5 per row; this is not five algorithm calls |
| Sizes | 100, 1,000, 10,000, 100,000, 1,000,000 |
| Benchmark annotations | 3 warmup iterations and 5 measurement iterations, each 500 ms; 1 fork |
| Effective warmup, duration and forks | Not confirmed without the run header; command-line options can override annotations |
| Input | Ascending array with `array[index] = index`, built in trial setup |
| Target | Fixed within each trial; `ABSENT` is -1, below every array value |
| Timed operation | Search method plus result consumption; setup and sorting excluded |

## Linear and Binary Search

The following selected rows show the `LAST` workload. Every value is a reported
mean plus/minus the reported error, in **ns/op**; the full dataset is linked above.

| Input size | LinearSearchBenchmark.search | BinarySearchBenchmark.search |
|---:|---:|---:|
| 100 | 21.865 +/- 16.310 | 9.539 +/- 0.823 |
| 1,000 | 240.334 +/- 158.998 | 16.033 +/- 0.396 |
| 10,000 | 1,951.861 +/- 931.841 | 25.087 +/- 4.264 |
| 100,000 | 19,051.110 +/- 12,729.013 | 30.169 +/- 1.930 |
| 1,000,000 | 239,040.976 +/- 192,867.402 | 36.672 +/- 4.698 |

Linear `MIDDLE`, `LAST` and `ABSENT` means grow in a way compatible with linear
traversal. Binary `LAST` and `ABSENT` means grow slowly, consistently with interval
halving. These observations do not prove asymptotic complexity.

Binary `MIDDLE` selects the first midpoint exactly. Its reported means remain
between 1.041 and 1.067 ns/op, consistent with a one-comparison workload. This
best-case selection is not a measurement of average successful-search cost.

Linear `FIRST` also requires one comparison, but its wide error margins prevent
a precise constant-cost estimate from this run.

## Iterative and Recursive Binary Search

Compare methods within `BinarySearchVariantsBenchmark`, which provides the same
input construction and target selection for both implementations.

| Scenario and size | Iterative, ns/op | Recursive, ns/op |
|---|---:|---:|
| LAST, 1,000,000 | 36.159 +/- 0.871 | 43.996 +/- 3.979 |
| MIDDLE, 10,000 | 1.033 +/- 0.070 | 4.108 +/- 14.074 |
| ABSENT, 1,000,000 | 23.642 +/- 2.798 | 66.355 +/- 116.612 |

Iterative `LAST` has a lower reported mean at each size. At one million elements,
the recursive mean is approximately 21.7% higher. This is a descriptive ratio
for that workload and run, not a universal speed difference.

The wide errors in recursive `MIDDLE` and `ABSENT` prevent a reliable detailed
ranking in those cases. The table does not establish the cause of variability
or attribute timing differences to method calls, the JIT, or stack management.
It also does not measure memory use or prove the auxiliary-space bounds.

## Limits of the Evidence

- Preserve the reported errors when quoting means. `Error` is statistical
  uncertainty, not a count of algorithm failures or the standard deviation.
- Some errors exceed their means. This limits precision; it does not imply
  negative execution times or an incorrect implementation.
- Each trial repeatedly searches a fixed array for a fixed target. These results
  should not be generalised to varying targets or cold-memory workloads.
- Binary search starts with sorted data. This experiment excludes the cost of
  sorting and does not answer whether sorting first is worthwhile for one query.
- Correctness is verified separately through contracts and automated tests.

## Documentation Handoff

The benchmark execution and its preliminary interpretation are now recorded.
The research documentation can use them as an empirical example, retaining the
distinction between mathematical analysis and measured execution time.

A more stable run and complete environment metadata remain follow-up work;
they do not prevent continuing the documentation. Retain the first run and
store later runs separately rather than replacing this evidence.

The follow-up command proposed after this run is:

```powershell
java -jar target\benchmarks.jar ".*searching.*" -wi 5 -w 1s -i 10 -r 1s -f 3 -rf json -rff searching-results-v2.json -o searching-run-v2.log
```

That command is a proposal, not an execution recorded here. Alongside a future
run, capture its complete header, source commit, CPU, operating system, Java
version and exact command. More measurements may improve precision but do not
guarantee that every source of variability is removed.
