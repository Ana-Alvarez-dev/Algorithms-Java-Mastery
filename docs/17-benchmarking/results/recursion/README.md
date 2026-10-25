# Recursion: First Reported Results

The author supplied a complete run covering **all 24 current default
configurations**: six ArraySum, nine Factorial and nine Fibonacci cells.
This report retains **24 means/errors**, **240 measurement-iteration averages**
and **144 warmup averages**. Completion time is **3 minutes 35 seconds**.
All three benchmark templates now use this evidence in Result and Interpretation.

This completes the initial selected experiment grid, not performance coverage
of every recursive algorithm or every accepted input. Power, Palindrome, GCD
and Hanoi retain their separate mathematical and functional-test evidence;
they were not benchmarked in this run. Short harness-smoke scores are excluded.

## Provenance and Runtime

The author separately supplied source SHA
**`fe370a1953c089a55f8851399acc42a59e3d37b7`**, the merge of Recursion PR #82.
It matches the reviewed executable source. The SHA is not embedded in the JMH
output; this is author-reported checkout provenance, not a supplied jar hash
or evidence that the revision was captured before launch. Later result-document
commits remain distinct from this measurement attribution.

| Property | Supplied evidence |
|---|---|
| Evidence review date | 2026-10-25 (UTC); execution timestamp not supplied |
| JMH | 1.37, present in every configuration header |
| Runtime | JDK 21.0.9, Java HotSpot(TM) 64-Bit Server VM, 21.0.9+7-LTS-338 |
| Java invoker | `C:\Program Files\Java\jdk-21\bin\java.exe` |
| VM options | `<none>` |
| Blackhole mode | Compiler, auto-detected |
| Threads | 1, synchronised iterations |
| Warmup | 3 iterations per fork, 500 ms each |
| Measurement | 5 iterations per fork, 500 ms each |
| Forks | 2 per configuration |
| Mode and units | Average time (`avgt`), **nanoseconds/op (`ns/op`)** |
| Count | 10 measurement-iteration averages per cell, not ten invocations |
| Timeout | 10 minutes per iteration |
| Confidence output | 99.9% intervals, printed normal-distribution assumption |
| Full command | Not captured; attachment begins with the first JMH header |
| CPU, power configuration, profiling, jar hash | Not supplied |
| Operating system | Windows executable path; Windows 11 amd64 reported separately during setup |

The guide's instructed invocation was:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*recursion.*" -foe true
```

That is the documented protocol, not a recovered command line. Headers, forks,
parameters and completion match its default grid. The mixed final table has
both `(n)` and `(size)` columns: `N/A` means that parameter does not apply to
that class. It is neither zero nor an additional tested input.

Decimal commas become dots with all three printed decimals retained. These are
rounded console iteration averages, not full-precision JMH arrays or individual
operation latencies. Warmup is separate from measurement. No observation or
fork is removed as an outlier. Printed CI endpoints remain intact, including
0.001 rounding differences from arithmetic on printed mean/error.

## Timed Work and Fixture Model

The [implementation guide](../../../11-recursion/implementation-and-benchmark-guide.md)
connects specifications, induction, traces, work/depth recurrences and tests.
The selected algorithms return the same mathematical answer within each family,
but execute different work and retain different state.

| Template | Parameter | Work inside each invocation | Excluded work / important distinction |
|---|---|---|---|
| [ArraySumBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/recursion/ArraySumBenchmark.java) | size=100/10,000/1,000,000 | Validation and full exact long sum, balanced recursion or loop | Trial setup creates full-range int values with SplittableRandom seed 20261025; read-only input, no timed copy |
| [FactorialBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/recursion/FactorialBenchmark.java) | n=5/10/20 | Validation and complete exact long factorial, ordinary/tail recursion or loop | No precomputed result; small bounded domain, source work counts differ |
| [FibonacciBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/recursion/FibonacciBenchmark.java) | n=10/20/30 | Validation and complete exact Fibonacci, naive/memoised/loop | Memoised allocates and fills a NEW array each invocation; no retained cross-call cache |

All possible int-array mathematical sums fit long, so the ArraySum fixtures
respect their numeric contract. This does not repair or relabel the old Arrays
module's int-aggregation benchmark finding. Factorial is restricted to 0..20;
naive Fibonacci to 0..30 as a work cap, memoised/iterative to 0..92 for long
range. The selected grid does not measure all those accepted inputs.

## Complete Means and Errors

Every cell is **mean +/- JMH-reported error in ns/op**, `avgt`, `Cnt = 10`.
Three decimals are retained even for multi-millisecond operations.

### ArraySumBenchmark

| Method | size=100 | size=10,000 | size=1,000,000 |
|---|---:|---:|---:|
| `iterative` | 14.250 +/- 2.643 | 578.707 +/- 90.192 | 272188.344 +/- 207549.689 |
| `recursive` | 294.126 +/- 105.551 | 31505.682 +/- 7102.023 | 3473757.020 +/- 643696.308 |

### FactorialBenchmark

| Method | n=5 | n=10 | n=20 |
|---|---:|---:|---:|
| `iterative` | 3.068 +/- 1.344 | 4.789 +/- 1.280 | 7.134 +/- 1.620 |
| `recursive` | 6.108 +/- 1.160 | 13.665 +/- 2.269 | 24.741 +/- 4.252 |
| `tailRecursive` | 6.020 +/- 0.953 | 14.075 +/- 2.221 | 25.186 +/- 5.213 |

### FibonacciBenchmark

| Method | n=10 | n=20 | n=30 |
|---|---:|---:|---:|
| `iterative` | 3.675 +/- 0.760 | 6.411 +/- 1.306 | 6.713 +/- 1.182 |
| `memoised` | 40.945 +/- 9.426 | 85.664 +/- 21.124 | 143.788 +/- 108.596 |
| `naive` | 215.480 +/- 40.688 | 27779.727 +/- 6045.567 | 3429961.531 +/- 699197.741 |

## Interpretation: Factorial and Tail Position

The loop has lower reported means at each selected n. At n=20 it reports
**7.134 +/- 1.620 ns/op**, ordinary recursion **24.741 +/- 4.252** and tail
recursion **25.186 +/- 5.213 ns/op**. Ordinary and tail intervals overlap at
all three n values. There is no demonstrated performance advantage from tail
position in this selected experiment, and no formal equivalence or
significance test is claimed.

This is not an isolated measurement of call overhead. Both recursive helpers
perform n multiplications (including multiplication by one) and n+1 logical
helper invocations, excluding their validation wrapper. The loop performs
max(n-1,0) multiplications. At n=5/10/20 the recursive multiplication counts
are 5/10/20 versus loop counts 4/9/19; the helper invocation counts are 6/11/21.
These are source counts, not recovered instructions, actual JVM frames or
profiling events. JIT specialisation/inlining may change executed machine work;
the run does not identify those transformations.

Two loop cells have error at least 25% of their means: n=5 (**43.8%**) and
n=10 (**26.7%**). Small bounded nanosecond workloads limit precise ratios and
generalisation. Similar ordinary/tail timings cannot establish tail-call
elimination, stack bytes or constant stack usage. The conservative source
model remains linear logical depth for both recursive forms and constant
local state for the selected loop.

## Interpretation: Fibonacci Work and Per-Call Memoisation

At n=30, naive reports **3429961.531 +/- 699197.741 ns/op** (about 3.430 ms),
memoised **143.788 +/- 108.596 ns/op** and iterative **6.713 +/- 1.182 ns/op**.
Naive means increase sharply across 10/20/30; memoised and iterative means are
lower on these fixtures. Those measurements agree with a source model in which
naive repeatedly solves overlapping subproblems, rather than demonstrating
that recursion syntax alone has a fixed per-call penalty.

| n | Exact F(n) | Naive logical helper calls, 2F(n+1)-1 | Maximum distinct memo slots |
|---|---:|---:|---:|
| 10 | 55 | 177 | 11 |
| 20 | 6,765 | 21,891 | 21 |
| 30 | 832,040 | 2,692,537 | 31 |

The recurrence C(0)=C(1)=1, C(n)=C(n-1)+C(n-2)+1 derives these calls; they are
not measured CPU events. Exponential total calls coexist with linear active
depth. Memoisation computes each needed state once, with repeated lookups and
O(n) array initialisation/storage plus recursive depth. Iteration uses a bounded
pair of long values and O(n) word work. These algorithmic arguments, rather than
a fitted exponent from three means, justify the work/storage distinctions.

Memoised timing includes a fresh array allocation/fill every invocation, so it
is a full calculation, not a warm O(1) cache lookup. Its n=30 error is about
**75.5%** of the mean; derived fork means are **180.606/106.970 ns/op**. This
wide interval limits a precise speedup ratio. Iterative n=20 and n=30 intervals
overlap: the nearly flat selected means do not establish constant complexity.
No allocation rate, GC cause, stack-byte size or JIT explanation is recovered
from these timings. Naive's bound at n=30 deliberately caps work; results do
not measure the other forms' entire long-range domain.

## Interpretation: Balanced Array Sum

At size 1,000,000, the loop reports **272188.344 +/- 207549.689 ns/op** (about
0.272 ms) and balanced recursion **3473757.020 +/- 643696.308 ns/op** (about
3.474 ms). The loop has lower means at all three selected sizes. Both perform
linear total word work; the balanced variant's logarithmic depth does not mean
logarithmic total scanning work.

| Nonempty length k | Singleton leaves | Internal combination nodes | Logical recursive helper calls, 2k-1 |
|---|---:|---:|---:|
| 100 | 100 | 99 | 199 |
| 10,000 | 10,000 | 9,999 | 19,999 |
| 1,000,000 | 1,000,000 | 999,999 | 1,999,999 |

Disjoint halves give a full binary tree with k leaves and k-1 internal nodes.
These source counts exclude public wrappers/validation. Neither variant copies
slices in the timed call. The loop scans the same read-only values; balanced
recursion retains O(log(k+1)) logical depth versus O(1) loop local state.
Extra recursive/control work is concrete in the source, but its share of
elapsed time is not isolated by this experiment.

The size-1,000,000 loop has about **76.3%** reported error and fork means
**356493.638/187883.050 ns/op**. Recursive size 100 also has wide error,
about **35.9%**. All observations are retained. No exact ratio or cache,
vectorisation, GC or CPU cause is established; one seed and three sizes are
not a fitted asymptotic proof or a measurement of all array workloads.

## Uncertainty Review

Five of 24 cells have reported error at least 25% of the mean. This threshold
is a descriptive flag, not a pass/fail or significance rule. Lower errors in
other cells do not rule out systematic experimental effects.

| Template / method | Parameter | Error / mean | Derived fork 1 mean (ns/op) | Derived fork 2 mean (ns/op) |
|---|---|---:|---:|---:|
| `ArraySumBenchmark.iterative` | size=1000000 | 76.3% | 356493.638 | 187883.050 |
| `ArraySumBenchmark.recursive` | size=100 | 35.9% | 270.978 | 317.274 |
| `FactorialBenchmark.iterative` | n=5 | 43.8% | 2.707 | 3.429 |
| `FactorialBenchmark.iterative` | n=10 | 26.7% | 4.515 | 5.064 |
| `FibonacciBenchmark.memoised` | n=30 | 75.5% | 180.606 | 106.970 |

## Statistics and Derived Fork Means

All entries are **ns/op**. Min/max are measurement-iteration averages, not
single-operation latency extremes. Standard deviation differs from JMH error.
Each fork mean is calculated from five rounded measured averages and rounded
to three decimals; this summary adds no new observation. CI endpoints preserve
the supplied 99.9% output and its printed normal-distribution assumption.

| Template / method | Parameter | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|
| `ArraySumBenchmark.iterative` | size=100 | 11.455 | 16.409 | 1.748 | 11.607 | 16.892 | 14.494 | 14.006 |
| `ArraySumBenchmark.iterative` | size=10000 | 529.869 | 691.471 | 59.657 | 488.515 | 668.899 | 531.404 | 626.010 |
| `ArraySumBenchmark.iterative` | size=1000000 | 130128.817 | 613311.446 | 137281.269 | 64638.655 | 479738.033 | 356493.638 | 187883.050 |
| `ArraySumBenchmark.recursive` | size=100 | 224.146 | 464.481 | 69.815 | 188.575 | 399.677 | 270.978 | 317.274 |
| `ArraySumBenchmark.recursive` | size=10000 | 23999.243 | 37890.989 | 4697.548 | 24403.660 | 38607.705 | 29758.614 | 33252.750 |
| `ArraySumBenchmark.recursive` | size=1000000 | 2869948.045 | 4047284.252 | 425765.253 | 2830060.711 | 4117453.328 | 3363125.228 | 3584388.811 |
| `FactorialBenchmark.iterative` | n=5 | 2.200 | 5.299 | 0.889 | 1.724 | 4.412 | 2.707 | 3.429 |
| `FactorialBenchmark.iterative` | n=10 | 3.505 | 6.125 | 0.847 | 3.509 | 6.069 | 4.515 | 5.064 |
| `FactorialBenchmark.iterative` | n=20 | 5.909 | 9.573 | 1.072 | 5.514 | 8.754 | 7.420 | 6.848 |
| `FactorialBenchmark.recursive` | n=5 | 5.108 | 6.942 | 0.767 | 4.948 | 7.267 | 6.169 | 6.047 |
| `FactorialBenchmark.recursive` | n=10 | 11.613 | 16.241 | 1.501 | 11.397 | 15.934 | 13.648 | 13.683 |
| `FactorialBenchmark.recursive` | n=20 | 21.283 | 28.193 | 2.813 | 20.488 | 28.993 | 24.949 | 24.533 |
| `FactorialBenchmark.tailRecursive` | n=5 | 5.015 | 7.034 | 0.630 | 5.067 | 6.973 | 6.015 | 6.025 |
| `FactorialBenchmark.tailRecursive` | n=10 | 11.721 | 15.608 | 1.469 | 11.854 | 16.296 | 13.585 | 14.565 |
| `FactorialBenchmark.tailRecursive` | n=20 | 22.118 | 33.628 | 3.448 | 19.973 | 30.400 | 23.468 | 26.905 |
| `FibonacciBenchmark.iterative` | n=10 | 3.067 | 4.317 | 0.503 | 2.915 | 4.435 | 3.639 | 3.711 |
| `FibonacciBenchmark.iterative` | n=20 | 4.681 | 7.682 | 0.864 | 5.105 | 7.717 | 6.311 | 6.511 |
| `FibonacciBenchmark.iterative` | n=30 | 5.741 | 7.848 | 0.782 | 5.532 | 7.895 | 6.704 | 6.723 |
| `FibonacciBenchmark.memoised` | n=10 | 31.574 | 50.931 | 6.235 | 31.519 | 50.372 | 38.886 | 43.005 |
| `FibonacciBenchmark.memoised` | n=20 | 68.798 | 113.321 | 13.972 | 64.540 | 106.788 | 93.350 | 77.978 |
| `FibonacciBenchmark.memoised` | n=30 | 89.473 | 334.167 | 71.830 | 35.192 | 252.385 | 180.606 | 106.970 |
| `FibonacciBenchmark.naive` | n=10 | 184.629 | 249.690 | 26.913 | 174.792 | 256.168 | 215.835 | 215.125 |
| `FibonacciBenchmark.naive` | n=20 | 23175.976 | 32881.258 | 3998.768 | 21734.160 | 33825.294 | 27556.571 | 28002.882 |
| `FibonacciBenchmark.naive` | n=30 | 2859767.778 | 4062967.969 | 462476.014 | 2730763.791 | 4129159.272 | 3399230.339 | 3460692.723 |

## Complete Measurement-Iteration Evidence

These **240 values**, in **ns/op**, are rounded iteration averages. Each timed
iteration contains benchmark invocations; `Cnt=10` refers to ten measured
iteration averages across two forks, not ten invocations or latency percentiles.

| Template / method | Parameter | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---|---:|---:|---:|---:|---:|---:|
| `ArraySumBenchmark.iterative` | size=100 | 1 | 16.409 | 12.721 | 14.081 | 13.764 | 15.496 |
| `ArraySumBenchmark.iterative` | size=100 | 2 | 14.908 | 16.202 | 15.476 | 11.988 | 11.455 |
| `ArraySumBenchmark.iterative` | size=10000 | 1 | 530.286 | 533.010 | 529.869 | 533.324 | 530.532 |
| `ArraySumBenchmark.iterative` | size=10000 | 2 | 601.056 | 576.815 | 596.606 | 664.103 | 691.471 |
| `ArraySumBenchmark.iterative` | size=1000000 | 1 | 613311.446 | 315974.268 | 341320.394 | 244435.844 | 267426.237 |
| `ArraySumBenchmark.iterative` | size=1000000 | 2 | 130128.817 | 196892.849 | 254124.632 | 212797.218 | 145471.735 |
| `ArraySumBenchmark.recursive` | size=100 | 1 | 231.098 | 224.146 | 285.756 | 287.438 | 326.454 |
| `ArraySumBenchmark.recursive` | size=100 | 2 | 227.994 | 464.481 | 287.935 | 291.831 | 314.131 |
| `ArraySumBenchmark.recursive` | size=10000 | 1 | 23999.243 | 24064.675 | 30232.230 | 32858.858 | 37638.065 |
| `ArraySumBenchmark.recursive` | size=10000 | 2 | 33043.964 | 32958.366 | 37890.989 | 30752.555 | 31617.878 |
| `ArraySumBenchmark.recursive` | size=1000000 | 1 | 2869948.045 | 2905513.636 | 3519791.096 | 3872924.812 | 3647448.551 |
| `ArraySumBenchmark.recursive` | size=1000000 | 2 | 3098185.276 | 3230526.250 | 4047284.252 | 3624371.942 | 3921576.336 |
| `FactorialBenchmark.iterative` | n=5 | 1 | 2.467 | 2.200 | 3.469 | 2.679 | 2.720 |
| `FactorialBenchmark.iterative` | n=5 | 2 | 2.713 | 2.971 | 3.555 | 5.299 | 2.605 |
| `FactorialBenchmark.iterative` | n=10 | 1 | 3.641 | 3.505 | 4.852 | 5.730 | 4.845 |
| `FactorialBenchmark.iterative` | n=10 | 2 | 4.821 | 4.082 | 5.310 | 4.981 | 6.125 |
| `FactorialBenchmark.iterative` | n=20 | 1 | 5.909 | 6.556 | 7.133 | 9.573 | 7.929 |
| `FactorialBenchmark.iterative` | n=20 | 2 | 6.278 | 6.093 | 7.170 | 7.414 | 7.285 |
| `FactorialBenchmark.recursive` | n=5 | 1 | 5.204 | 5.589 | 6.198 | 6.942 | 6.912 |
| `FactorialBenchmark.recursive` | n=5 | 2 | 5.155 | 5.108 | 6.810 | 6.660 | 6.500 |
| `FactorialBenchmark.recursive` | n=10 | 1 | 11.966 | 11.613 | 13.377 | 15.042 | 16.241 |
| `FactorialBenchmark.recursive` | n=10 | 2 | 12.507 | 12.617 | 14.022 | 14.403 | 14.866 |
| `FactorialBenchmark.recursive` | n=20 | 1 | 22.475 | 21.433 | 27.512 | 25.929 | 27.394 |
| `FactorialBenchmark.recursive` | n=20 | 2 | 21.311 | 21.283 | 25.451 | 28.193 | 26.427 |
| `FactorialBenchmark.tailRecursive` | n=5 | 1 | 5.624 | 5.133 | 6.104 | 6.182 | 7.034 |
| `FactorialBenchmark.tailRecursive` | n=5 | 2 | 5.015 | 6.002 | 6.008 | 6.584 | 6.514 |
| `FactorialBenchmark.tailRecursive` | n=10 | 1 | 11.721 | 11.790 | 13.433 | 15.608 | 15.373 |
| `FactorialBenchmark.tailRecursive` | n=10 | 2 | 13.391 | 14.494 | 14.140 | 15.246 | 15.556 |
| `FactorialBenchmark.tailRecursive` | n=20 | 1 | 22.252 | 22.215 | 22.118 | 25.113 | 25.641 |
| `FactorialBenchmark.tailRecursive` | n=20 | 2 | 25.479 | 23.111 | 25.052 | 27.256 | 33.628 |
| `FibonacciBenchmark.iterative` | n=10 | 1 | 3.067 | 3.091 | 3.797 | 4.183 | 4.059 |
| `FibonacciBenchmark.iterative` | n=10 | 2 | 3.234 | 3.070 | 3.939 | 4.317 | 3.995 |
| `FibonacciBenchmark.iterative` | n=20 | 1 | 4.681 | 5.662 | 6.254 | 7.278 | 7.682 |
| `FibonacciBenchmark.iterative` | n=20 | 2 | 5.985 | 6.767 | 6.862 | 6.827 | 6.112 |
| `FibonacciBenchmark.iterative` | n=30 | 1 | 5.741 | 6.058 | 6.755 | 7.117 | 7.848 |
| `FibonacciBenchmark.iterative` | n=30 | 2 | 5.980 | 5.776 | 6.908 | 7.317 | 7.635 |
| `FibonacciBenchmark.memoised` | n=10 | 1 | 32.342 | 31.574 | 38.621 | 50.931 | 40.962 |
| `FibonacciBenchmark.memoised` | n=10 | 2 | 46.102 | 48.214 | 39.367 | 41.322 | 40.021 |
| `FibonacciBenchmark.memoised` | n=20 | 1 | 73.301 | 95.501 | 113.321 | 99.409 | 85.220 |
| `FibonacciBenchmark.memoised` | n=20 | 2 | 68.798 | 71.387 | 83.748 | 87.822 | 78.134 |
| `FibonacciBenchmark.memoised` | n=30 | 1 | 173.102 | 334.167 | 157.269 | 116.856 | 121.635 |
| `FibonacciBenchmark.memoised` | n=30 | 2 | 89.473 | 89.993 | 128.801 | 111.557 | 115.027 |
| `FibonacciBenchmark.naive` | n=10 | 1 | 187.300 | 184.629 | 221.024 | 241.265 | 244.959 |
| `FibonacciBenchmark.naive` | n=10 | 2 | 185.444 | 187.670 | 218.561 | 249.690 | 234.262 |
| `FibonacciBenchmark.naive` | n=20 | 1 | 23214.717 | 23317.863 | 28708.032 | 29660.985 | 32881.258 |
| `FibonacciBenchmark.naive` | n=20 | 2 | 23175.976 | 23555.030 | 30553.320 | 31747.729 | 30982.356 |
| `FibonacciBenchmark.naive` | n=30 | 1 | 2859767.778 | 2869514.045 | 3426073.333 | 3877153.030 | 3963643.511 |
| `FibonacciBenchmark.naive` | n=30 | 2 | 3146946.341 | 2962442.197 | 3385577.181 | 4062967.969 | 3745529.927 |

## Complete Warmup Evidence

These **144 values**, in **ns/op**, are excluded from measurement and derived
fork means. Their order and fork membership are retained.

| Template / method | Parameter | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---|---:|---:|---:|---:|
| `ArraySumBenchmark.iterative` | size=100 | 1 | 12.562 | 11.705 | 15.352 |
| `ArraySumBenchmark.iterative` | size=100 | 2 | 9.181 | 9.122 | 8.493 |
| `ArraySumBenchmark.iterative` | size=10000 | 1 | 627.427 | 564.392 | 530.989 |
| `ArraySumBenchmark.iterative` | size=10000 | 2 | 541.311 | 562.335 | 754.210 |
| `ArraySumBenchmark.iterative` | size=1000000 | 1 | 79062.375 | 75717.025 | 96756.775 |
| `ArraySumBenchmark.iterative` | size=1000000 | 2 | 91248.646 | 112657.891 | 154181.419 |
| `ArraySumBenchmark.recursive` | size=100 | 1 | 228.301 | 230.055 | 260.989 |
| `ArraySumBenchmark.recursive` | size=100 | 2 | 225.245 | 226.022 | 219.493 |
| `ArraySumBenchmark.recursive` | size=10000 | 1 | 24568.822 | 24829.435 | 25293.214 |
| `ArraySumBenchmark.recursive` | size=10000 | 2 | 40344.130 | 198069.148 | 163214.902 |
| `ArraySumBenchmark.recursive` | size=1000000 | 1 | 2886092.655 | 2898111.429 | 2861416.201 |
| `ArraySumBenchmark.recursive` | size=1000000 | 2 | 2929486.207 | 2925802.299 | 2899054.494 |
| `FactorialBenchmark.iterative` | n=5 | 1 | 2.197 | 2.751 | 2.113 |
| `FactorialBenchmark.iterative` | n=5 | 2 | 2.515 | 2.356 | 2.333 |
| `FactorialBenchmark.iterative` | n=10 | 1 | 4.739 | 3.851 | 3.648 |
| `FactorialBenchmark.iterative` | n=10 | 2 | 3.832 | 4.631 | 3.621 |
| `FactorialBenchmark.iterative` | n=20 | 1 | 6.150 | 6.686 | 9.028 |
| `FactorialBenchmark.iterative` | n=20 | 2 | 6.286 | 6.579 | 5.837 |
| `FactorialBenchmark.recursive` | n=5 | 1 | 4.622 | 4.664 | 5.797 |
| `FactorialBenchmark.recursive` | n=5 | 2 | 4.477 | 4.348 | 5.160 |
| `FactorialBenchmark.recursive` | n=10 | 1 | 11.222 | 11.557 | 11.607 |
| `FactorialBenchmark.recursive` | n=10 | 2 | 12.279 | 12.582 | 12.512 |
| `FactorialBenchmark.recursive` | n=20 | 1 | 25.526 | 23.531 | 23.521 |
| `FactorialBenchmark.recursive` | n=20 | 2 | 21.221 | 21.284 | 21.398 |
| `FactorialBenchmark.tailRecursive` | n=5 | 1 | 4.233 | 5.005 | 5.017 |
| `FactorialBenchmark.tailRecursive` | n=5 | 2 | 4.184 | 5.026 | 5.026 |
| `FactorialBenchmark.tailRecursive` | n=10 | 1 | 11.620 | 11.763 | 11.705 |
| `FactorialBenchmark.tailRecursive` | n=10 | 2 | 11.491 | 11.893 | 11.769 |
| `FactorialBenchmark.tailRecursive` | n=20 | 1 | 22.205 | 22.822 | 24.607 |
| `FactorialBenchmark.tailRecursive` | n=20 | 2 | 21.962 | 23.789 | 22.422 |
| `FibonacciBenchmark.iterative` | n=10 | 1 | 3.546 | 3.434 | 3.471 |
| `FibonacciBenchmark.iterative` | n=10 | 2 | 3.385 | 3.432 | 3.100 |
| `FibonacciBenchmark.iterative` | n=20 | 1 | 4.544 | 4.580 | 4.321 |
| `FibonacciBenchmark.iterative` | n=20 | 2 | 6.317 | 5.132 | 4.910 |
| `FibonacciBenchmark.iterative` | n=30 | 1 | 6.126 | 6.575 | 5.787 |
| `FibonacciBenchmark.iterative` | n=30 | 2 | 6.424 | 6.458 | 5.750 |
| `FibonacciBenchmark.memoised` | n=10 | 1 | 39.279 | 36.764 | 33.083 |
| `FibonacciBenchmark.memoised` | n=10 | 2 | 44.661 | 50.382 | 57.963 |
| `FibonacciBenchmark.memoised` | n=20 | 1 | 99.389 | 69.926 | 82.109 |
| `FibonacciBenchmark.memoised` | n=20 | 2 | 94.652 | 75.770 | 76.826 |
| `FibonacciBenchmark.memoised` | n=30 | 1 | 240.762 | 333.167 | 2380.126 |
| `FibonacciBenchmark.memoised` | n=30 | 2 | 103.939 | 92.471 | 89.481 |
| `FibonacciBenchmark.naive` | n=10 | 1 | 186.102 | 188.611 | 184.999 |
| `FibonacciBenchmark.naive` | n=10 | 2 | 187.937 | 187.659 | 188.669 |
| `FibonacciBenchmark.naive` | n=20 | 1 | 23461.562 | 23302.256 | 23338.589 |
| `FibonacciBenchmark.naive` | n=20 | 2 | 23413.304 | 23519.331 | 23504.909 |
| `FibonacciBenchmark.naive` | n=30 | 1 | 2870911.364 | 2850512.994 | 2882825.281 |
| `FibonacciBenchmark.naive` | n=30 | 2 | 3042675.449 | 2855231.638 | 2876154.749 |

Every pooled mean agrees with its ten rounded observations within 0.001 ns/op.
Min/max and sample standard deviations agree to printed precision. Every
final-table mean/error matches its result block and complete parameter/header.
No observation is discarded, replaced or silently pooled with another run.

## Research Limits and Module Handoff

- Initial coverage is the **24 selected defaults**, one array seed, three input
  values per family and one runtime/environment. Other valid inputs, seeds,
  slices and four unbenchmarked classes are not marked empirically complete.
- All five wide-error cells remain visible. JMH model confidence intervals
  are not invocation latency ranges or protection against systematic error.
  No formal significance/equivalence test or universal ranking is claimed.
- Source identity is author-reported. The command line, execution timestamp,
  jar hash, CPU/power details and profiler data were not captured in the output.
  No machine-code inspection or allocation/GC/cache attribution is claimed.
- Timing includes validated full calculations and their differing work/state.
  No subtraction estimates an isolated recursive-call cost. No measured ratio
  proves an asymptotic class, tail-call elimination or exact stack usage.
- Correctness remains the earlier **600-test verification**, including **28
  Recursion tests**, independent probes and proof arguments. This documentation
  update changes no executable Java and introduces no new timed run or CI status.

The three templates, READMEs and project traceability review now link this
complete initial evidence. No repeat is needed merely to record the supplied
run. Future controlled experiments can lengthen measurement, vary inputs/seeds
and isolate memo allocation or source work with matched controls; any profiler
or generated-code findings need their own recorded source/runtime and results.
Later Divide and Conquer, Backtracking and Dynamic Programming modules remain
separate work, not completed by these examples.

## Source Integrity

Original attachment: `Texto pegado(6).txt`. SHA-256 of the supplied bytes,
including original line endings:

`ecd1a47e837eb7b54195d8ac1083af35ebbd82509e74febd3bb5febb6eac1530`

The source text is not copied into the repository. Decimal normalisation and
Markdown tables retain every printed numerical observation. No JSON, log or
new CSV artifact is created. This hash identifies the received text, not a jar.
