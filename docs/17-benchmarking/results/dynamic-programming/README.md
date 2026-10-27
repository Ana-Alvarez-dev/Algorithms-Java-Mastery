# Dynamic Programming: First Reported Results

The author supplied a complete execution of **all 57 current defaults**: nine
Fibonacci values, 24 knapsack values and 24 LCS lengths. This report preserves
**57 means/errors**, **570 measurement-iteration averages** and **342 warmup
averages**, with completion **8 minutes 30 seconds**. All three templates now
use this evidence in Result/Interpretation.

Fibonacci is measured in **ns/op**; knapsack and LCS in **us/op**. Every evidence
row retains its unit. No cross-class pooling, silent conversion or ranking of
different mathematical outputs is performed. This completes the initial grid,
not all valid inputs, witnesses, numeric domains or DP techniques. Prior module
reports keep their own provenance; short smoke scores stay excluded.

## Provenance and Runtime

The author separately supplied checkout SHA
**`d572651484f3ec6cffe03ad589bddd5a4a8fb8a3`**, the merge of feature PR #90.
Its Git tree matches the reviewed Dynamic Programming implementation. Source
identity is author-reported, not a revision embedded by JMH or a supplied jar
hash. The text does not establish when the SHA was captured relative to launch.
Later results-documentation commits are distinct from this attribution.

| Property | Evidence / limit |
|---|---|
| Reporting date | 2026-10-26, America/Buenos_Aires; exact execution timestamp not supplied |
| JMH | 1.37 in all 57 headers |
| Runtime | JDK 21.0.9, Java HotSpot(TM) 64-Bit Server VM, 21.0.9+7-LTS-338 |
| Java invoker | `C:\Program Files\Java\jdk-21\bin\java.exe` |
| VM options | `<none>` |
| Blackhole mode | Compiler, auto-detected |
| Threads | 1, synchronised iterations |
| Warmup | 3 iterations per fork, 500 ms each |
| Measurement | 5 iterations per fork, 500 ms each |
| Forks | 2 per configuration |
| Mode | Average time (`avgt`) |
| Units | Fibonacci ns/op; knapsack and LCS us/op |
| Count | 10 measured iteration averages per cell, not ten invocations |
| Timeout | 10 minutes per iteration |
| Confidence output | Printed 99.9% intervals, with normal-distribution assumption |
| Non-numeric CI boundary | Fibonacci table n=20 prints the literal lower marker `? 0` |
| Full command | Not captured; attachment begins at the first JMH header |
| OS/vendor/locale context | Oracle/Windows 11 amd64/es_AR/UTF-8 reported in prior setup; not newly supplied as run metadata here |
| CPU, power, profiler metrics, generated-code inspection, jar hash | Not supplied |

The [module guide](../../../15-dynamic-programming/implementation-and-benchmark-guide.md)
instructed this combined invocation:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*dynamicprogramming.*" -foe true
```

This is the instructed protocol, not a recovered command line. Settings,
parameters, forks and completion agree with that full default grid. The mixed
final table has `capacity`, `n` and `shape`. N/A means inapplicable, not zero or
an untested capacity/shape. Class-specific sizes and units stay separate.

Decimal commas are normalised to dots, retaining all three printed decimals.
These are rounded console iteration averages, not full-precision arrays or
per-invocation latency distributions. Warmup remains separate. No observation
or fork is removed. Printed CIs preserve endpoint rounding, including 0.001
differences from arithmetic using rounded mean/error. The literal `? 0` is
retained as text: it is not replaced by zero, a fabricated negative endpoint
or an inferred full-precision boundary.

## Contracts, Fixtures and Full Timed Work

The guide links states, recurrences, traces, induction, reconstruction ties,
work/output models, explicit caps and independent tests. Per-call caches are
private; no warmed result persists between invocations. JMH consumes returned
primitives, without that fact establishing generated-code correctness by itself.

| Template | Fixtures | Full timed workflow |
|---|---|---|
| [FibonacciDpBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/dynamicprogramming/FibonacciDpBenchmark.java) | n=20/40/90, exact long values | Existing Recursion memoised/iterative versus new full table, including validation and private memo/table setup; no naive recursion or sequence output |
| [KnapsackValueBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/dynamicprogramming/KnapsackValueBenchmark.java) | n=16/64, capacity=32/128, LIGHT/TOO_HEAVY | Exact 0/1 optimum value with dense memo, full table or one row; validation/private setup included, no selected-index plan |
| [LcsLengthBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/dynamicprogramming/LcsLengthBenchmark.java) | n=32/128, IDENTICAL/DISJOINT/SHIFTED/RANDOM | UTF-16 optimum length with dense memo, full table or one row; no witness, normalisation or code-point conversion |

LIGHT knapsack uses unit weights and positive values cycling 1..7. TOO_HEAVY
uses weights capacity+1 and the same values. Seed **20261026** shuffles trial
values outside timing. LCS pairs are all A/all A, all A/all B, alternating
AB/BA at the even default lengths, or independent A..D Strings from the seeded
trial generator. These are controlled families, not population samples.

Knapsack positions may be selected once, empty subset is allowed, weights are
positive and values nonnegative; accumulators are long. The shared grid cap
also applies to rolling calls. LCS compares Java char units, not graphemes;
ASCII fixtures exercise that domain without measuring Unicode transformations.
Fibonacci exactness is bounded to 0..92. Every timed method returns a value/
length, not the corresponding materialised plan or subsequence.

## Complete Means and Errors

Each entry is **mean +/- JMH error**, `avgt`, `Cnt=10`, in its section's units.

### FibonacciDpBenchmark (ns/op)

| Method | Capacity | Shape | n=20 | n=40 | n=90 |
|---|---:|---|---:|---:|---:|
| `memoised` | N/A | N/A | 71.790 +/- 9.931 | 191.962 +/- 58.642 | 536.951 +/- 30.365 |
| `rolling` | N/A | N/A | 5.454 +/- 1.647 | 9.391 +/- 3.816 | 15.455 +/- 1.323 |
| `table` | N/A | N/A | 86.692 +/- 141.911 | 69.990 +/- 5.239 | 147.322 +/- 10.103 |

### KnapsackValueBenchmark (us/op)

| Method | Capacity | Shape | n=16 | n=64 |
|---|---:|---|---:|---:|
| `memoised` | 32 | LIGHT | 1.475 +/- 0.243 | 11.402 +/- 1.489 |
| `memoised` | 32 | TOO_HEAVY | 1.028 +/- 0.079 | 3.903 +/- 0.526 |
| `memoised` | 128 | LIGHT | 3.691 +/- 0.200 | 23.005 +/- 2.881 |
| `memoised` | 128 | TOO_HEAVY | 3.387 +/- 0.203 | 13.668 +/- 2.160 |
| `rolling` | 32 | LIGHT | 0.352 +/- 0.068 | 1.329 +/- 0.226 |
| `rolling` | 32 | TOO_HEAVY | 0.056 +/- 0.008 | 0.076 +/- 0.010 |
| `rolling` | 128 | LIGHT | 1.143 +/- 0.155 | 4.597 +/- 0.777 |
| `rolling` | 128 | TOO_HEAVY | 0.194 +/- 0.009 | 0.196 +/- 0.008 |
| `table` | 32 | LIGHT | 1.351 +/- 0.155 | 5.523 +/- 0.586 |
| `table` | 32 | TOO_HEAVY | 1.008 +/- 0.076 | 3.805 +/- 0.346 |
| `table` | 128 | LIGHT | 4.992 +/- 0.748 | 21.544 +/- 2.661 |
| `table` | 128 | TOO_HEAVY | 4.344 +/- 0.382 | 17.207 +/- 1.518 |

### LcsLengthBenchmark (us/op)

| Method | Capacity | Shape | n=32 | n=128 |
|---|---:|---|---:|---:|
| `memoised` | N/A | IDENTICAL | 1.446 +/- 0.250 | 13.947 +/- 0.702 |
| `memoised` | N/A | DISJOINT | 7.720 +/- 1.311 | 116.516 +/- 17.514 |
| `memoised` | N/A | SHIFTED | 1.777 +/- 0.359 | 20.058 +/- 7.054 |
| `memoised` | N/A | RANDOM | 5.402 +/- 0.930 | 121.560 +/- 16.427 |
| `rolling` | N/A | IDENTICAL | 2.954 +/- 0.748 | 49.353 +/- 14.854 |
| `rolling` | N/A | DISJOINT | 5.899 +/- 1.769 | 49.328 +/- 13.924 |
| `rolling` | N/A | SHIFTED | 5.643 +/- 2.161 | 43.288 +/- 8.510 |
| `rolling` | N/A | RANDOM | 5.585 +/- 1.541 | 42.091 +/- 6.680 |
| `table` | N/A | IDENTICAL | 7.943 +/- 1.675 | 111.161 +/- 21.021 |
| `table` | N/A | DISJOINT | 9.706 +/- 2.019 | 132.297 +/- 26.196 |
| `table` | N/A | SHIFTED | 9.613 +/- 2.335 | 113.249 +/- 23.055 |
| `table` | N/A | RANDOM | 8.882 +/- 1.903 | 121.045 +/- 25.484 |

## Interpretation: Fibonacci Storage and Strong n=20 Table Uncertainty

rolling has lower point means at every n: **5.454 +/- 1.647**, **9.391 +/-
3.816**, **15.455 +/- 1.323 ns/op** at 20/40/90. At n=90 the table reports
**147.322 +/- 10.103**, and memoised **536.951 +/- 30.365 ns/op**. These
compare full exact-value public calls, including differing private setup.

At n=20, table reports **86.692 +/- 141.911 ns/op**, with **163.7%** relative
error. Its CI is printed **[`? 0`,228.603]**. Derived fork means are
**127.539/45.845 ns/op**; one measured iteration is **347.118 ns/op**, while
the minimum iteration average is **36.671**. Every observation stays. This is
insufficient for a precise ordering of table against the other n=20 workflows.
The lower n=40 table mean, **69.990 +/- 5.239**, does not show that more
arithmetic became cheaper or that complexity decreases with n.

Four of nine Fibonacci errors reach 25%: memoised n=40, rolling n=20/40 and
table n=20. Rolling n=40 fork means **9.393/9.388 ns/op** are close despite
the pooled **40.6%** error; fork means alone do not describe within-run variation.
The threshold is descriptive, not a significance test or permission to discard data.

The source separates work/storage without using times:

| n | Memo helper calls, 2n-1 | Memo/table array cells n+1 | Additions per variant n-1 | Derived exact result |
|---|---:|---:|---:|---:|
| 20 | 39 | 21 | 19 | 6,765 |
| 40 | 79 | 41 | 39 | 102,334,155 |
| 90 | 179 | 91 | 89 | 2,880,067,194,370,816,120 |

Memo also fills the array with -1, caches reached values and has O(n) logical
depth. Table retains n+1 entries; rolling retains two scalar values. All perform
the same number of recurrence additions at these n, so addition count alone
does not explain their elapsed gaps. These are mathematical/source counts and
expected values, not JMH return values, machine events or actual allocation
rates. No subtraction isolates call/array cost; no tail-call elimination,
CPU/cache cause, universal ratio or arbitrary-precision bound is established.
Prior Recursion measurements use different source/fixtures and remain separate.

## Interpretation: Knapsack Reachability Versus Dense Preparation

rolling has the lowest point mean on every matching knapsack fixture. At
n=64,C=128,LIGHT it reports **4.597 +/- 0.777**, versus memoised
**23.005 +/- 2.881** and table **21.544 +/- 2.661 us/op**. On TOO_HEAVY,
rolling is **0.196 +/- 0.008**, memoised **13.668 +/- 2.160**, table
**17.207 +/- 1.518 us/op**. Those last memo/table CIs overlap, so their means
do not establish a precise ordering or equivalence.

Dense memo is not uniformly better than full table. At C=32,n=64,LIGHT,
memo/table are **11.402 +/- 1.489 / 5.523 +/- 0.586 us/op**; at
C=128,n=16,LIGHT, they are **3.691 +/- 0.200 / 4.992 +/- 0.748**. Five
of eight memo/table matching pairs have overlapping printed intervals. No
knapsack cell reaches 25% relative error, which does not exclude systematic
effects or isolate an implementation cost.

The reviewed source yields the following ledger. Memo calls include base cases
and hits; R counts first-time nonbase expansions. Grid cells include boundaries;
table iterations and rolling updates are source-level loop counts.

| n | C | Shape | Dense cells | Memo R | Memo helper calls | Table cell iterations | Rolling updates |
|---|---:|---|---:|---:|---:|---:|---:|
| 16 | 32 | LIGHT | 561 | 136 | 273 | 528 | 512 |
| 16 | 32 | TOO_HEAVY | 561 | 16 | 17 | 528 | 0 |
| 16 | 128 | LIGHT | 2,193 | 136 | 273 | 2,064 | 2,048 |
| 16 | 128 | TOO_HEAVY | 2,193 | 16 | 17 | 2,064 | 0 |
| 64 | 32 | LIGHT | 2,145 | 1,584 | 3,137 | 2,112 | 2,048 |
| 64 | 32 | TOO_HEAVY | 2,145 | 64 | 65 | 2,112 | 0 |
| 64 | 128 | LIGHT | 8,385 | 2,080 | 4,161 | 8,256 | 8,192 |
| 64 | 128 | TOO_HEAVY | 8,385 | 64 | 65 | 8,256 | 0 |

TOO_HEAVY expands only n nonbase states, but memo still allocates and fills
the entire dense matrix. For fixed n=16, changing C=32 to 128 leaves those
16 states unchanged while increasing allocated/filled cells from 561 to 2,193;
memo means change from **1.028 +/- 0.079** to **3.387 +/- 0.203 us/op**.
LIGHT likewise keeps 136 expanded states at n=16 in both capacities, while
preparation changes. These observations are consistent with included dense
setup; they do not quantify its isolated share or establish a hardware cause.

Full table visits n(C+1) cells even when no item fits, though transition bodies
differ. Rolling keeps C+1 cells and has zero feasible-item updates on heavy
fixtures, but still validates n items and allocates/initialises its row. Its
public call is not zero work, nor is it universal constant time. LIGHT's one-row
capacity loop descends, preserving the previous-item version and 0/1 semantics.

Expected optimum values are 59 at n=16 in both capacities, and 182/253 at
n=64,C=32/128 for LIGHT; all heavy results are zero. These derive from choosing
the highest-valued feasible unit-weight positions or no available item, not
from a value printed by JMH. Index-plan reconstruction/ties, weights up to the
API limit and arbitrary distributions are not timed. O(nC) is pseudopolynomial
in numeric capacity, not a polynomial bit-length guarantee. Dense-fill counts
are not measured memory writes, bytes, GC events or a fitted complexity exponent.

## Interpretation: LCS Has No Universal Memo/Rolling Winner

Memoised has lower means than rolling on IDENTICAL and SHIFTED at both n.
At n=128, IDENTICAL is **13.947 +/- 0.702** versus rolling
**49.353 +/- 14.854** and table **111.161 +/- 21.021 us/op**. SHIFTED is
**20.058 +/- 7.054**, **43.288 +/- 8.510**, **113.249 +/- 23.055**,
respectively. Sparse recursive dependencies can avoid many recurrence expansions,
while their dense-cache setup remains included.

At n=128/DISJOINT, rolling instead reports **49.328 +/- 13.924** versus
memo **116.516 +/- 17.514** and table **132.297 +/- 26.196 us/op**.
For RANDOM, rolling is **42.091 +/- 6.680**, memo **121.560 +/- 16.427**,
table **121.045 +/- 25.484**. Memo/table intervals overlap for both disjoint
and random size-128 fixtures. Near-equal RANDOM point means do not show
equivalence. At n=32/RANDOM, memo/rolling **5.402 +/- 0.930 / 5.585 +/-
1.541** intervals overlap too; this small mean difference does not resolve them.

Seven LCS cells reach 25% error: memoised n=128/SHIFTED and six rolling cells
(all four shapes at 32, IDENTICAL/DISJOINT at 128). They remain in full. The
largest LCS relative error is about **38.3%**, rolling n=32/SHIFTED.

The source dependency ledger distinguishes shape from output length:

| n | Shape | Dense cells | Memo R | Memo helper calls | Table/rolling recurrence iterations |
|---|---|---:|---:|---:|---:|
| 32 | IDENTICAL | 1,089 | 32 | 33 | 1,024 |
| 32 | DISJOINT | 1,089 | 1,024 | 2,049 | 1,024 |
| 32 | SHIFTED | 1,089 | 63 | 65 | 1,024 |
| 32 | RANDOM | 1,089 | 637 | 1,104 | 1,024 |
| 128 | IDENTICAL | 16,641 | 128 | 129 | 16,384 |
| 128 | DISJOINT | 16,641 | 16,384 | 32,769 | 16,384 |
| 128 | SHIFTED | 16,641 | 255 | 257 | 16,384 |
| 128 | RANDOM | 16,641 | 11,566 | 20,128 | 16,384 |

These are derived from the reviewed recurrence and an untimed state counter,
not JMH profiler measurements. IDENTICAL follows one diagonal (R=n, calls=n+1);
DISJOINT expands all n^2 nonbase states (2n^2+1 calls). SHIFTED has a mismatching
root and two matching off-diagonals (R=2n-1, calls=2n+1). RANDOM depends on
the generated pair, not merely its length/alphabet size. Dense memo still
initialises (n+1)^2 cells in all four cases. Table/rolling visit n^2 recurrence
positions; their per-position work, layout and branching differ.

IDENTICAL gives length n, DISJOINT zero, SHIFTED n-1 at these even defaults;
RANDOM lengths were independently checked by the matching-position LIS probe
before publication. The author's JMH text contains elapsed scores, not returned
lengths or source-state counts. A diagonal search does not make this dense
public memo call asymptotically linear: setup remains quadratic. Lower storage
does not guarantee lower elapsed time on every shape, nor does output length
alone predict cost.

No subsequence reconstruction, deterministic tie processing, code-point/grapheme
conversion or Unicode normalisation is timed. Both Strings are equal-length
ASCII fixtures; unequal lengths and the full UTF-16 contract have functional
evidence outside this grid. No exact cache, branch-prediction, allocation or
stack-byte attribution follows from timings or these logical ledgers.

## Uncertainty and Matching-Interval Review

**Eleven of 57 cells** have error at least 25% of the mean: four Fibonacci,
seven LCS, none knapsack. This is a descriptive threshold, not a pass/fail or
significance rule. Every fork/iteration stays. Derived fork means use rounded
measurement averages and add no observations.

| Method | n | Capacity | Shape | Unit | Error / mean | Fork 1 mean | Fork 2 mean |
|---|---:|---:|---|---|---:|---:|---:|
| `FibonacciDpBenchmark.memoised` | 40 | N/A | N/A | ns/op | 30.5% | 176.521 | 207.403 |
| `FibonacciDpBenchmark.rolling` | 20 | N/A | N/A | ns/op | 30.2% | 5.583 | 5.325 |
| `FibonacciDpBenchmark.rolling` | 40 | N/A | N/A | ns/op | 40.6% | 9.393 | 9.388 |
| `FibonacciDpBenchmark.table` | 20 | N/A | N/A | ns/op | 163.7% | 127.539 | 45.845 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | SHIFTED | us/op | 35.2% | 21.692 | 18.424 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | IDENTICAL | us/op | 25.3% | 2.642 | 3.267 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | DISJOINT | us/op | 30.0% | 4.999 | 6.799 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | SHIFTED | us/op | 38.3% | 6.304 | 4.982 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | RANDOM | us/op | 27.6% | 5.012 | 6.159 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | IDENTICAL | us/op | 30.1% | 48.805 | 49.902 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | DISJOINT | us/op | 28.2% | 50.102 | 48.554 |

Among the 57 within-fixture method pairs, **12 pairs have overlapping fully
numeric printed CIs**, listed below. The two n=20 Fibonacci pairs involving
table instead have its nonnumeric lower marker and are not assigned numeric
endpoints. Interval comparisons are descriptive: overlap does not establish
equality, and non-overlap is not used as a formal multiple-comparison test.

| Template | n | Capacity | Shape | Unit | Methods | First CI | Second CI |
|---|---:|---:|---|---|---|---|---|
| KnapsackValueBenchmark | 16 | 32 | LIGHT | us/op | memoised / table | [1.232,1.718] | [1.195,1.506] |
| KnapsackValueBenchmark | 16 | 32 | TOO_HEAVY | us/op | memoised / table | [0.949,1.107] | [0.932,1.084] |
| KnapsackValueBenchmark | 64 | 32 | TOO_HEAVY | us/op | memoised / table | [3.377,4.428] | [3.459,4.150] |
| KnapsackValueBenchmark | 64 | 128 | LIGHT | us/op | memoised / table | [20.123,25.886] | [18.883,24.205] |
| KnapsackValueBenchmark | 64 | 128 | TOO_HEAVY | us/op | memoised / table | [11.509,15.828] | [15.689,18.725] |
| LcsLengthBenchmark | 32 | N/A | DISJOINT | us/op | memoised / rolling | [6.410,9.031] | [4.130,7.668] |
| LcsLengthBenchmark | 32 | N/A | DISJOINT | us/op | memoised / table | [6.410,9.031] | [7.687,11.726] |
| LcsLengthBenchmark | 32 | N/A | SHIFTED | us/op | rolling / table | [3.482,7.804] | [7.278,11.948] |
| LcsLengthBenchmark | 32 | N/A | RANDOM | us/op | memoised / rolling | [4.472,6.331] | [4.045,7.126] |
| LcsLengthBenchmark | 32 | N/A | RANDOM | us/op | rolling / table | [4.045,7.126] | [6.978,10.785] |
| LcsLengthBenchmark | 128 | N/A | DISJOINT | us/op | memoised / table | [99.002,134.030] | [106.101,158.493] |
| LcsLengthBenchmark | 128 | N/A | RANDOM | us/op | memoised / table | [105.133,137.987] | [95.561,146.529] |

## Statistics and Derived Fork Means

Min/max are measurement-iteration averages, not invocation-latency extremes.
Standard deviation is distinct from JMH error. CI endpoints retain the printed
99.9% model/normal-distribution assumption; `? 0` stays literal text. Each fork
mean derives from its five rounded measured averages, rounded to three decimals.

| Method | n | Capacity | Shape | Unit | Min | Max | Standard deviation | Printed CI | Fork 1 mean | Fork 2 mean |
|---|---:|---:|---|---|---:|---:|---:|---|---:|---:|
| `FibonacciDpBenchmark.memoised` | 20 | N/A | N/A | ns/op | 64.147 | 81.782 | 6.569 | [61.859,81.722] | 69.730 | 73.850 |
| `FibonacciDpBenchmark.memoised` | 40 | N/A | N/A | ns/op | 148.847 | 251.648 | 38.788 | [133.319,250.604] | 176.521 | 207.403 |
| `FibonacciDpBenchmark.memoised` | 90 | N/A | N/A | ns/op | 516.755 | 576.357 | 20.084 | [506.586,567.316] | 537.843 | 536.059 |
| `FibonacciDpBenchmark.rolling` | 20 | N/A | N/A | ns/op | 4.329 | 7.627 | 1.089 | [3.807,7.100] | 5.583 | 5.325 |
| `FibonacciDpBenchmark.rolling` | 40 | N/A | N/A | ns/op | 7.489 | 14.567 | 2.524 | [5.574,13.207] | 9.393 | 9.388 |
| `FibonacciDpBenchmark.rolling` | 90 | N/A | N/A | ns/op | 14.630 | 17.016 | 0.875 | [14.132,16.777] | 14.949 | 15.960 |
| `FibonacciDpBenchmark.table` | 20 | N/A | N/A | ns/op | 36.671 | 347.118 | 93.865 | [`? 0`,228.603] | 127.539 | 45.845 |
| `FibonacciDpBenchmark.table` | 40 | N/A | N/A | ns/op | 64.645 | 76.615 | 3.465 | [64.751,75.229] | 71.686 | 68.294 |
| `FibonacciDpBenchmark.table` | 90 | N/A | N/A | ns/op | 137.427 | 157.097 | 6.683 | [137.218,157.425] | 147.889 | 146.754 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | LIGHT | us/op | 1.299 | 1.780 | 0.161 | [1.232,1.718] | 1.462 | 1.488 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | TOO_HEAVY | us/op | 0.939 | 1.102 | 0.052 | [0.949,1.107] | 1.043 | 1.013 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | LIGHT | us/op | 10.029 | 12.778 | 0.985 | [9.913,12.891] | 11.404 | 11.400 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | TOO_HEAVY | us/op | 3.559 | 4.700 | 0.348 | [3.377,4.428] | 3.854 | 3.951 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | LIGHT | us/op | 3.435 | 3.812 | 0.133 | [3.490,3.891] | 3.720 | 3.661 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | TOO_HEAVY | us/op | 3.166 | 3.610 | 0.134 | [3.185,3.590] | 3.405 | 3.370 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | LIGHT | us/op | 21.027 | 26.860 | 1.906 | [20.123,25.886] | 23.311 | 22.698 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | TOO_HEAVY | us/op | 12.027 | 16.736 | 1.428 | [11.509,15.828] | 14.555 | 12.782 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | LIGHT | us/op | 0.283 | 0.431 | 0.045 | [0.283,0.420] | 0.377 | 0.327 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | TOO_HEAVY | us/op | 0.051 | 0.067 | 0.005 | [0.048,0.063] | 0.053 | 0.058 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | LIGHT | us/op | 1.150 | 1.503 | 0.149 | [1.103,1.554] | 1.353 | 1.305 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | TOO_HEAVY | us/op | 0.068 | 0.085 | 0.006 | [0.067,0.086] | 0.077 | 0.076 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | LIGHT | us/op | 1.019 | 1.251 | 0.103 | [0.988,1.298] | 1.144 | 1.142 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | TOO_HEAVY | us/op | 0.187 | 0.203 | 0.006 | [0.184,0.203] | 0.199 | 0.189 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | LIGHT | us/op | 3.872 | 5.360 | 0.514 | [3.820,5.374] | 4.555 | 4.639 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | TOO_HEAVY | us/op | 0.188 | 0.203 | 0.005 | [0.188,0.204] | 0.197 | 0.195 |
| `KnapsackValueBenchmark.table` | 16 | 32 | LIGHT | us/op | 1.212 | 1.472 | 0.103 | [1.195,1.506] | 1.321 | 1.381 |
| `KnapsackValueBenchmark.table` | 16 | 32 | TOO_HEAVY | us/op | 0.926 | 1.063 | 0.050 | [0.932,1.084] | 0.981 | 1.035 |
| `KnapsackValueBenchmark.table` | 64 | 32 | LIGHT | us/op | 5.061 | 5.970 | 0.387 | [4.937,6.109] | 5.514 | 5.532 |
| `KnapsackValueBenchmark.table` | 64 | 32 | TOO_HEAVY | us/op | 3.521 | 4.253 | 0.229 | [3.459,4.150] | 3.855 | 3.754 |
| `KnapsackValueBenchmark.table` | 16 | 128 | LIGHT | us/op | 4.524 | 6.028 | 0.495 | [4.243,5.740] | 4.911 | 5.072 |
| `KnapsackValueBenchmark.table` | 16 | 128 | TOO_HEAVY | us/op | 3.989 | 4.671 | 0.253 | [3.962,4.726] | 4.369 | 4.319 |
| `KnapsackValueBenchmark.table` | 64 | 128 | LIGHT | us/op | 19.271 | 25.098 | 1.760 | [18.883,24.205] | 22.607 | 20.481 |
| `KnapsackValueBenchmark.table` | 64 | 128 | TOO_HEAVY | us/op | 15.637 | 18.242 | 1.004 | [15.689,18.725] | 17.354 | 17.061 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | IDENTICAL | us/op | 1.262 | 1.745 | 0.166 | [1.195,1.696] | 1.414 | 1.477 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | DISJOINT | us/op | 6.613 | 9.185 | 0.867 | [6.410,9.031] | 7.741 | 7.699 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | SHIFTED | us/op | 1.447 | 2.246 | 0.237 | [1.418,2.136] | 1.641 | 1.913 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | RANDOM | us/op | 4.592 | 6.199 | 0.615 | [4.472,6.331] | 5.307 | 5.496 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | IDENTICAL | us/op | 13.150 | 14.572 | 0.465 | [13.245,14.650] | 14.007 | 13.888 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | DISJOINT | us/op | 103.014 | 129.617 | 11.584 | [99.002,134.030] | 117.793 | 115.239 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | SHIFTED | us/op | 16.542 | 32.601 | 4.666 | [13.004,27.113] | 21.692 | 18.424 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | RANDOM | us/op | 107.921 | 134.678 | 10.865 | [105.133,137.987] | 122.215 | 120.905 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | IDENTICAL | us/op | 2.407 | 3.919 | 0.494 | [2.207,3.702] | 2.642 | 3.267 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | DISJOINT | us/op | 4.971 | 8.570 | 1.170 | [4.130,7.668] | 4.999 | 6.799 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | SHIFTED | us/op | 4.136 | 9.291 | 1.429 | [3.482,7.804] | 6.304 | 4.982 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | RANDOM | us/op | 3.892 | 6.884 | 1.019 | [4.045,7.126] | 5.012 | 6.159 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | IDENTICAL | us/op | 40.353 | 74.633 | 9.825 | [34.499,64.207] | 48.805 | 49.902 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | DISJOINT | us/op | 38.496 | 69.817 | 9.210 | [35.404,63.253] | 50.102 | 48.554 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | SHIFTED | us/op | 35.079 | 55.700 | 5.629 | [34.778,51.798] | 44.296 | 42.280 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | RANDOM | us/op | 36.570 | 47.615 | 4.418 | [35.411,48.770] | 41.814 | 42.368 |
| `LcsLengthBenchmark.table` | 32 | N/A | IDENTICAL | us/op | 6.016 | 9.265 | 1.108 | [6.268,9.618] | 7.130 | 8.756 |
| `LcsLengthBenchmark.table` | 32 | N/A | DISJOINT | us/op | 7.789 | 11.736 | 1.336 | [7.687,11.726] | 9.665 | 9.748 |
| `LcsLengthBenchmark.table` | 32 | N/A | SHIFTED | us/op | 7.480 | 12.451 | 1.545 | [7.278,11.948] | 9.629 | 9.596 |
| `LcsLengthBenchmark.table` | 32 | N/A | RANDOM | us/op | 6.696 | 10.600 | 1.259 | [6.978,10.785] | 8.969 | 8.794 |
| `LcsLengthBenchmark.table` | 128 | N/A | IDENTICAL | us/op | 94.376 | 128.211 | 13.904 | [90.140,132.182] | 111.342 | 110.980 |
| `LcsLengthBenchmark.table` | 128 | N/A | DISJOINT | us/op | 97.322 | 156.068 | 17.327 | [106.101,158.493] | 129.547 | 135.046 |
| `LcsLengthBenchmark.table` | 128 | N/A | SHIFTED | us/op | 89.585 | 136.864 | 15.250 | [90.193,136.304] | 114.022 | 112.475 |
| `LcsLengthBenchmark.table` | 128 | N/A | RANDOM | us/op | 95.671 | 149.651 | 16.856 | [95.561,146.529] | 124.917 | 117.173 |

## Complete Measurement-Iteration Evidence

These **570 values** retain their units and fork order. Cnt=10 means ten measured
iteration averages across two forks, not ten invocations or latency percentiles.

| Method | n | Capacity | Shape | Unit | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---:|---:|---|---|---:|---:|---:|---:|---:|---:|
| `FibonacciDpBenchmark.memoised` | 20 | N/A | N/A | ns/op | 1 | 66.733 | 73.339 | 64.275 | 64.147 | 80.157 |
| `FibonacciDpBenchmark.memoised` | 20 | N/A | N/A | ns/op | 2 | 64.349 | 74.169 | 74.159 | 74.793 | 81.782 |
| `FibonacciDpBenchmark.memoised` | 40 | N/A | N/A | ns/op | 1 | 194.100 | 227.641 | 160.157 | 148.847 | 151.858 |
| `FibonacciDpBenchmark.memoised` | 40 | N/A | N/A | ns/op | 2 | 251.648 | 229.655 | 225.441 | 154.158 | 176.112 |
| `FibonacciDpBenchmark.memoised` | 90 | N/A | N/A | ns/op | 1 | 576.357 | 540.384 | 519.261 | 523.097 | 530.117 |
| `FibonacciDpBenchmark.memoised` | 90 | N/A | N/A | ns/op | 2 | 565.612 | 536.728 | 540.702 | 516.755 | 520.496 |
| `FibonacciDpBenchmark.rolling` | 20 | N/A | N/A | ns/op | 1 | 4.329 | 4.474 | 5.465 | 7.627 | 6.019 |
| `FibonacciDpBenchmark.rolling` | 20 | N/A | N/A | ns/op | 2 | 4.391 | 4.467 | 5.461 | 5.746 | 6.559 |
| `FibonacciDpBenchmark.rolling` | 40 | N/A | N/A | ns/op | 1 | 7.500 | 7.489 | 8.883 | 14.567 | 8.526 |
| `FibonacciDpBenchmark.rolling` | 40 | N/A | N/A | ns/op | 2 | 7.493 | 7.555 | 7.991 | 11.548 | 12.352 |
| `FibonacciDpBenchmark.rolling` | 90 | N/A | N/A | ns/op | 1 | 14.928 | 14.957 | 15.005 | 14.630 | 15.225 |
| `FibonacciDpBenchmark.rolling` | 90 | N/A | N/A | ns/op | 2 | 15.086 | 16.976 | 15.872 | 17.016 | 14.850 |
| `FibonacciDpBenchmark.table` | 20 | N/A | N/A | ns/op | 1 | 72.447 | 347.118 | 111.184 | 55.513 | 51.431 |
| `FibonacciDpBenchmark.table` | 20 | N/A | N/A | ns/op | 2 | 46.723 | 46.843 | 48.577 | 50.409 | 36.671 |
| `FibonacciDpBenchmark.table` | 40 | N/A | N/A | ns/op | 1 | 72.847 | 76.615 | 71.360 | 68.992 | 68.618 |
| `FibonacciDpBenchmark.table` | 40 | N/A | N/A | ns/op | 2 | 64.645 | 65.720 | 69.068 | 71.885 | 70.152 |
| `FibonacciDpBenchmark.table` | 90 | N/A | N/A | ns/op | 1 | 139.723 | 137.427 | 156.456 | 157.097 | 148.744 |
| `FibonacciDpBenchmark.table` | 90 | N/A | N/A | ns/op | 2 | 145.162 | 141.005 | 150.945 | 146.666 | 149.994 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | LIGHT | us/op | 1 | 1.299 | 1.335 | 1.472 | 1.622 | 1.583 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | LIGHT | us/op | 2 | 1.327 | 1.305 | 1.476 | 1.552 | 1.780 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | TOO_HEAVY | us/op | 1 | 1.041 | 1.044 | 1.008 | 1.102 | 1.022 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | TOO_HEAVY | us/op | 2 | 0.943 | 0.939 | 1.065 | 1.056 | 1.062 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | LIGHT | us/op | 1 | 10.029 | 10.402 | 11.760 | 12.052 | 12.778 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | LIGHT | us/op | 2 | 10.719 | 10.331 | 11.390 | 12.635 | 11.924 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | TOO_HEAVY | us/op | 1 | 3.728 | 3.585 | 3.775 | 4.231 | 3.952 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | TOO_HEAVY | us/op | 2 | 3.559 | 3.633 | 4.700 | 3.896 | 3.967 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | LIGHT | us/op | 1 | 3.642 | 3.645 | 3.710 | 3.795 | 3.809 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | LIGHT | us/op | 2 | 3.435 | 3.507 | 3.788 | 3.763 | 3.812 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | TOO_HEAVY | us/op | 1 | 3.277 | 3.166 | 3.492 | 3.480 | 3.610 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | TOO_HEAVY | us/op | 2 | 3.345 | 3.263 | 3.326 | 3.443 | 3.472 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | LIGHT | us/op | 1 | 21.165 | 21.161 | 23.370 | 26.860 | 23.999 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | LIGHT | us/op | 2 | 21.027 | 21.170 | 23.131 | 23.693 | 24.469 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | TOO_HEAVY | us/op | 1 | 16.736 | 15.376 | 13.294 | 14.218 | 13.150 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | TOO_HEAVY | us/op | 2 | 12.294 | 12.027 | 13.392 | 13.007 | 13.189 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | LIGHT | us/op | 1 | 0.355 | 0.366 | 0.336 | 0.431 | 0.397 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | LIGHT | us/op | 2 | 0.286 | 0.283 | 0.335 | 0.366 | 0.363 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | TOO_HEAVY | us/op | 1 | 0.052 | 0.052 | 0.053 | 0.055 | 0.053 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | TOO_HEAVY | us/op | 2 | 0.053 | 0.051 | 0.060 | 0.060 | 0.067 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | LIGHT | us/op | 1 | 1.161 | 1.164 | 1.444 | 1.491 | 1.503 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | LIGHT | us/op | 2 | 1.170 | 1.150 | 1.370 | 1.432 | 1.402 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | TOO_HEAVY | us/op | 1 | 0.069 | 0.068 | 0.079 | 0.085 | 0.082 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | TOO_HEAVY | us/op | 2 | 0.069 | 0.071 | 0.076 | 0.082 | 0.080 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | LIGHT | us/op | 1 | 1.029 | 1.019 | 1.188 | 1.251 | 1.234 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | LIGHT | us/op | 2 | 1.028 | 1.031 | 1.180 | 1.236 | 1.234 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | TOO_HEAVY | us/op | 1 | 0.203 | 0.192 | 0.203 | 0.197 | 0.199 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | TOO_HEAVY | us/op | 2 | 0.188 | 0.189 | 0.188 | 0.192 | 0.187 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | LIGHT | us/op | 1 | 3.995 | 3.872 | 4.476 | 5.072 | 5.360 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | LIGHT | us/op | 2 | 3.959 | 4.618 | 4.737 | 4.984 | 4.898 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | TOO_HEAVY | us/op | 1 | 0.192 | 0.194 | 0.195 | 0.201 | 0.202 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | TOO_HEAVY | us/op | 2 | 0.188 | 0.189 | 0.203 | 0.196 | 0.200 |
| `KnapsackValueBenchmark.table` | 16 | 32 | LIGHT | us/op | 1 | 1.212 | 1.217 | 1.322 | 1.465 | 1.387 |
| `KnapsackValueBenchmark.table` | 16 | 32 | LIGHT | us/op | 2 | 1.377 | 1.226 | 1.373 | 1.472 | 1.457 |
| `KnapsackValueBenchmark.table` | 16 | 32 | TOO_HEAVY | us/op | 1 | 0.940 | 0.926 | 0.961 | 1.028 | 1.049 |
| `KnapsackValueBenchmark.table` | 16 | 32 | TOO_HEAVY | us/op | 2 | 1.063 | 1.061 | 1.035 | 0.998 | 1.016 |
| `KnapsackValueBenchmark.table` | 64 | 32 | LIGHT | us/op | 1 | 5.094 | 5.061 | 5.763 | 5.892 | 5.761 |
| `KnapsackValueBenchmark.table` | 64 | 32 | LIGHT | us/op | 2 | 5.080 | 5.112 | 5.611 | 5.970 | 5.885 |
| `KnapsackValueBenchmark.table` | 64 | 32 | TOO_HEAVY | us/op | 1 | 3.629 | 3.558 | 3.975 | 3.860 | 4.253 |
| `KnapsackValueBenchmark.table` | 64 | 32 | TOO_HEAVY | us/op | 2 | 3.521 | 3.592 | 3.863 | 3.900 | 3.894 |
| `KnapsackValueBenchmark.table` | 16 | 128 | LIGHT | us/op | 1 | 4.550 | 4.524 | 5.035 | 5.060 | 5.386 |
| `KnapsackValueBenchmark.table` | 16 | 128 | LIGHT | us/op | 2 | 4.546 | 4.544 | 4.874 | 6.028 | 5.368 |
| `KnapsackValueBenchmark.table` | 16 | 128 | TOO_HEAVY | us/op | 1 | 4.160 | 4.077 | 4.482 | 4.454 | 4.671 |
| `KnapsackValueBenchmark.table` | 16 | 128 | TOO_HEAVY | us/op | 2 | 3.989 | 4.060 | 4.411 | 4.667 | 4.468 |
| `KnapsackValueBenchmark.table` | 64 | 128 | LIGHT | us/op | 1 | 21.443 | 22.124 | 20.690 | 23.681 | 25.098 |
| `KnapsackValueBenchmark.table` | 64 | 128 | LIGHT | us/op | 2 | 19.759 | 19.271 | 20.432 | 21.474 | 21.470 |
| `KnapsackValueBenchmark.table` | 64 | 128 | TOO_HEAVY | us/op | 1 | 16.108 | 16.302 | 18.168 | 17.950 | 18.242 |
| `KnapsackValueBenchmark.table` | 64 | 128 | TOO_HEAVY | us/op | 2 | 15.637 | 16.379 | 17.245 | 18.126 | 17.916 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | IDENTICAL | us/op | 1 | 1.271 | 1.262 | 1.438 | 1.602 | 1.498 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | IDENTICAL | us/op | 2 | 1.316 | 1.267 | 1.488 | 1.745 | 1.570 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | DISJOINT | us/op | 1 | 6.875 | 6.865 | 8.135 | 8.408 | 8.421 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | DISJOINT | us/op | 2 | 6.868 | 6.613 | 7.872 | 7.959 | 9.185 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | SHIFTED | us/op | 1 | 1.447 | 1.481 | 1.669 | 1.769 | 1.841 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | SHIFTED | us/op | 2 | 1.779 | 1.735 | 1.758 | 2.045 | 2.246 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | RANDOM | us/op | 1 | 4.592 | 4.716 | 5.437 | 5.687 | 6.102 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | RANDOM | us/op | 2 | 4.759 | 4.870 | 5.746 | 5.907 | 6.199 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | IDENTICAL | us/op | 1 | 13.693 | 13.150 | 14.572 | 14.088 | 14.530 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | IDENTICAL | us/op | 2 | 13.397 | 13.873 | 14.023 | 14.345 | 13.803 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | DISJOINT | us/op | 1 | 104.152 | 103.037 | 125.314 | 129.617 | 126.844 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | DISJOINT | us/op | 2 | 103.014 | 103.446 | 119.322 | 123.554 | 126.861 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | SHIFTED | us/op | 1 | 17.578 | 17.268 | 20.733 | 20.282 | 32.601 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | SHIFTED | us/op | 2 | 16.542 | 16.970 | 18.548 | 19.925 | 20.133 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | RANDOM | us/op | 1 | 108.807 | 107.921 | 127.510 | 132.158 | 134.678 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | RANDOM | us/op | 2 | 109.737 | 110.801 | 125.195 | 130.506 | 128.286 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | IDENTICAL | us/op | 1 | 2.457 | 2.407 | 2.700 | 2.798 | 2.846 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | IDENTICAL | us/op | 2 | 2.949 | 2.586 | 3.919 | 3.359 | 3.522 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | DISJOINT | us/op | 1 | 4.998 | 4.971 | 5.011 | 4.980 | 5.036 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | DISJOINT | us/op | 2 | 6.740 | 8.570 | 6.465 | 6.220 | 6.000 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | SHIFTED | us/op | 1 | 9.291 | 4.769 | 5.678 | 5.407 | 6.376 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | SHIFTED | us/op | 2 | 4.515 | 4.136 | 5.269 | 5.492 | 5.498 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | RANDOM | us/op | 1 | 3.892 | 3.949 | 5.585 | 5.326 | 6.306 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | RANDOM | us/op | 2 | 5.413 | 6.884 | 5.604 | 6.375 | 6.520 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | IDENTICAL | us/op | 1 | 49.159 | 48.271 | 45.095 | 50.554 | 50.944 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | IDENTICAL | us/op | 2 | 51.291 | 42.582 | 40.353 | 74.633 | 40.650 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | DISJOINT | us/op | 1 | 45.194 | 58.138 | 49.110 | 48.691 | 49.377 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | DISJOINT | us/op | 2 | 39.399 | 38.496 | 43.920 | 51.140 | 69.817 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | SHIFTED | us/op | 1 | 47.447 | 43.824 | 43.181 | 41.080 | 45.948 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | SHIFTED | us/op | 2 | 55.700 | 35.079 | 42.083 | 38.827 | 39.710 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | RANDOM | us/op | 1 | 37.540 | 36.570 | 42.512 | 47.332 | 45.116 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | RANDOM | us/op | 2 | 37.879 | 37.417 | 42.683 | 47.615 | 46.244 |
| `LcsLengthBenchmark.table` | 32 | N/A | IDENTICAL | us/op | 1 | 6.322 | 6.016 | 7.267 | 8.089 | 7.958 |
| `LcsLengthBenchmark.table` | 32 | N/A | IDENTICAL | us/op | 2 | 9.031 | 8.385 | 8.121 | 9.265 | 8.976 |
| `LcsLengthBenchmark.table` | 32 | N/A | DISJOINT | us/op | 1 | 7.789 | 8.954 | 11.406 | 10.357 | 9.818 |
| `LcsLengthBenchmark.table` | 32 | N/A | DISJOINT | us/op | 2 | 8.431 | 8.312 | 9.661 | 11.736 | 10.600 |
| `LcsLengthBenchmark.table` | 32 | N/A | SHIFTED | us/op | 1 | 7.735 | 8.238 | 10.095 | 9.626 | 12.451 |
| `LcsLengthBenchmark.table` | 32 | N/A | SHIFTED | us/op | 2 | 7.480 | 9.010 | 9.971 | 10.974 | 10.547 |
| `LcsLengthBenchmark.table` | 32 | N/A | RANDOM | us/op | 1 | 7.943 | 7.894 | 9.903 | 8.606 | 10.500 |
| `LcsLengthBenchmark.table` | 32 | N/A | RANDOM | us/op | 2 | 6.696 | 8.099 | 9.287 | 9.288 | 10.600 |
| `LcsLengthBenchmark.table` | 128 | N/A | IDENTICAL | us/op | 1 | 100.107 | 97.813 | 107.886 | 127.493 | 123.409 |
| `LcsLengthBenchmark.table` | 128 | N/A | IDENTICAL | us/op | 2 | 94.376 | 98.859 | 106.089 | 128.211 | 127.367 |
| `LcsLengthBenchmark.table` | 128 | N/A | DISJOINT | us/op | 1 | 126.793 | 97.322 | 143.787 | 143.438 | 136.395 |
| `LcsLengthBenchmark.table` | 128 | N/A | DISJOINT | us/op | 2 | 123.171 | 115.132 | 133.350 | 156.068 | 147.510 |
| `LcsLengthBenchmark.table` | 128 | N/A | SHIFTED | us/op | 1 | 96.835 | 101.574 | 122.359 | 112.479 | 136.864 |
| `LcsLengthBenchmark.table` | 128 | N/A | SHIFTED | us/op | 2 | 89.585 | 101.521 | 123.193 | 121.139 | 126.937 |
| `LcsLengthBenchmark.table` | 128 | N/A | RANDOM | us/op | 1 | 108.290 | 105.581 | 149.651 | 132.539 | 128.525 |
| `LcsLengthBenchmark.table` | 128 | N/A | RANDOM | us/op | 2 | 95.671 | 109.330 | 129.961 | 114.280 | 136.622 |

## Complete Warmup Evidence

These **342 values** keep their units, sequence and fork membership and remain
excluded from pooled/derived measured means.

| Method | n | Capacity | Shape | Unit | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---:|---:|---|---|---:|---:|---:|---:|
| `FibonacciDpBenchmark.memoised` | 20 | N/A | N/A | ns/op | 1 | 80.508 | 66.403 | 65.084 |
| `FibonacciDpBenchmark.memoised` | 20 | N/A | N/A | ns/op | 2 | 86.060 | 68.330 | 68.450 |
| `FibonacciDpBenchmark.memoised` | 40 | N/A | N/A | ns/op | 1 | 253.096 | 213.059 | 307.482 |
| `FibonacciDpBenchmark.memoised` | 40 | N/A | N/A | ns/op | 2 | 165.666 | 261.121 | 420.134 |
| `FibonacciDpBenchmark.memoised` | 90 | N/A | N/A | ns/op | 1 | 559.626 | 646.693 | 589.489 |
| `FibonacciDpBenchmark.memoised` | 90 | N/A | N/A | ns/op | 2 | 634.481 | 645.536 | 571.609 |
| `FibonacciDpBenchmark.rolling` | 20 | N/A | N/A | ns/op | 1 | 4.861 | 4.689 | 4.447 |
| `FibonacciDpBenchmark.rolling` | 20 | N/A | N/A | ns/op | 2 | 4.723 | 4.695 | 4.431 |
| `FibonacciDpBenchmark.rolling` | 40 | N/A | N/A | ns/op | 1 | 8.416 | 8.227 | 7.472 |
| `FibonacciDpBenchmark.rolling` | 40 | N/A | N/A | ns/op | 2 | 8.378 | 8.127 | 7.461 |
| `FibonacciDpBenchmark.rolling` | 90 | N/A | N/A | ns/op | 1 | 18.357 | 25.826 | 17.683 |
| `FibonacciDpBenchmark.rolling` | 90 | N/A | N/A | ns/op | 2 | 22.728 | 18.571 | 15.722 |
| `FibonacciDpBenchmark.table` | 20 | N/A | N/A | ns/op | 1 | 42.607 | 51.688 | 59.815 |
| `FibonacciDpBenchmark.table` | 20 | N/A | N/A | ns/op | 2 | 60.611 | 152.779 | 44.742 |
| `FibonacciDpBenchmark.table` | 40 | N/A | N/A | ns/op | 1 | 85.886 | 72.072 | 79.087 |
| `FibonacciDpBenchmark.table` | 40 | N/A | N/A | ns/op | 2 | 68.626 | 76.368 | 65.030 |
| `FibonacciDpBenchmark.table` | 90 | N/A | N/A | ns/op | 1 | 150.429 | 147.779 | 174.149 |
| `FibonacciDpBenchmark.table` | 90 | N/A | N/A | ns/op | 2 | 148.006 | 140.939 | 140.013 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | LIGHT | us/op | 1 | 1.588 | 1.337 | 1.333 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | LIGHT | us/op | 2 | 1.408 | 1.356 | 1.360 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | TOO_HEAVY | us/op | 1 | 1.334 | 1.396 | 1.366 |
| `KnapsackValueBenchmark.memoised` | 16 | 32 | TOO_HEAVY | us/op | 2 | 1.042 | 1.003 | 0.931 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | LIGHT | us/op | 1 | 11.010 | 10.236 | 10.346 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | LIGHT | us/op | 2 | 11.447 | 10.300 | 13.376 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | TOO_HEAVY | us/op | 1 | 4.333 | 3.700 | 3.668 |
| `KnapsackValueBenchmark.memoised` | 64 | 32 | TOO_HEAVY | us/op | 2 | 4.306 | 3.663 | 3.649 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | LIGHT | us/op | 1 | 4.567 | 3.988 | 3.692 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | LIGHT | us/op | 2 | 4.121 | 3.511 | 3.502 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | TOO_HEAVY | us/op | 1 | 3.380 | 3.366 | 3.215 |
| `KnapsackValueBenchmark.memoised` | 16 | 128 | TOO_HEAVY | us/op | 2 | 3.813 | 3.388 | 3.292 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | LIGHT | us/op | 1 | 22.357 | 21.767 | 21.819 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | LIGHT | us/op | 2 | 24.967 | 21.423 | 21.019 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | TOO_HEAVY | us/op | 1 | 13.731 | 13.065 | 14.093 |
| `KnapsackValueBenchmark.memoised` | 64 | 128 | TOO_HEAVY | us/op | 2 | 13.133 | 12.347 | 14.135 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | LIGHT | us/op | 1 | 0.367 | 0.359 | 0.376 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | LIGHT | us/op | 2 | 0.301 | 0.320 | 0.278 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | TOO_HEAVY | us/op | 1 | 0.056 | 0.062 | 0.053 |
| `KnapsackValueBenchmark.rolling` | 16 | 32 | TOO_HEAVY | us/op | 2 | 0.063 | 0.053 | 0.052 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | LIGHT | us/op | 1 | 1.683 | 1.393 | 1.197 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | LIGHT | us/op | 2 | 1.289 | 1.219 | 1.315 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | TOO_HEAVY | us/op | 1 | 0.077 | 0.077 | 0.069 |
| `KnapsackValueBenchmark.rolling` | 64 | 32 | TOO_HEAVY | us/op | 2 | 0.087 | 0.074 | 0.068 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | LIGHT | us/op | 1 | 1.123 | 1.080 | 1.025 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | LIGHT | us/op | 2 | 1.151 | 1.091 | 1.050 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | TOO_HEAVY | us/op | 1 | 0.213 | 0.205 | 0.195 |
| `KnapsackValueBenchmark.rolling` | 16 | 128 | TOO_HEAVY | us/op | 2 | 0.242 | 0.191 | 0.192 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | LIGHT | us/op | 1 | 4.113 | 4.045 | 3.826 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | LIGHT | us/op | 2 | 4.331 | 3.961 | 3.955 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | TOO_HEAVY | us/op | 1 | 0.210 | 0.210 | 0.217 |
| `KnapsackValueBenchmark.rolling` | 64 | 128 | TOO_HEAVY | us/op | 2 | 0.229 | 0.203 | 0.185 |
| `KnapsackValueBenchmark.table` | 16 | 32 | LIGHT | us/op | 1 | 1.372 | 1.214 | 1.210 |
| `KnapsackValueBenchmark.table` | 16 | 32 | LIGHT | us/op | 2 | 1.369 | 1.271 | 1.201 |
| `KnapsackValueBenchmark.table` | 16 | 32 | TOO_HEAVY | us/op | 1 | 1.014 | 0.929 | 1.006 |
| `KnapsackValueBenchmark.table` | 16 | 32 | TOO_HEAVY | us/op | 2 | 1.078 | 1.327 | 1.138 |
| `KnapsackValueBenchmark.table` | 64 | 32 | LIGHT | us/op | 1 | 7.071 | 5.329 | 5.124 |
| `KnapsackValueBenchmark.table` | 64 | 32 | LIGHT | us/op | 2 | 5.900 | 5.369 | 5.131 |
| `KnapsackValueBenchmark.table` | 64 | 32 | TOO_HEAVY | us/op | 1 | 4.134 | 3.573 | 3.511 |
| `KnapsackValueBenchmark.table` | 64 | 32 | TOO_HEAVY | us/op | 2 | 3.953 | 3.623 | 3.725 |
| `KnapsackValueBenchmark.table` | 16 | 128 | LIGHT | us/op | 1 | 5.242 | 4.786 | 4.783 |
| `KnapsackValueBenchmark.table` | 16 | 128 | LIGHT | us/op | 2 | 5.299 | 4.692 | 4.587 |
| `KnapsackValueBenchmark.table` | 16 | 128 | TOO_HEAVY | us/op | 1 | 3.883 | 3.830 | 3.977 |
| `KnapsackValueBenchmark.table` | 16 | 128 | TOO_HEAVY | us/op | 2 | 3.835 | 3.770 | 4.062 |
| `KnapsackValueBenchmark.table` | 64 | 128 | LIGHT | us/op | 1 | 20.783 | 19.338 | 21.763 |
| `KnapsackValueBenchmark.table` | 64 | 128 | LIGHT | us/op | 2 | 21.085 | 19.579 | 19.897 |
| `KnapsackValueBenchmark.table` | 64 | 128 | TOO_HEAVY | us/op | 1 | 16.331 | 15.879 | 12.185 |
| `KnapsackValueBenchmark.table` | 64 | 128 | TOO_HEAVY | us/op | 2 | 17.212 | 15.975 | 12.220 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | IDENTICAL | us/op | 1 | 1.365 | 1.304 | 1.552 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | IDENTICAL | us/op | 2 | 1.380 | 1.337 | 1.282 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | DISJOINT | us/op | 1 | 8.136 | 8.375 | 8.058 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | DISJOINT | us/op | 2 | 7.123 | 6.839 | 6.608 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | SHIFTED | us/op | 1 | 1.750 | 1.478 | 1.492 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | SHIFTED | us/op | 2 | 1.910 | 1.468 | 1.626 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | RANDOM | us/op | 1 | 6.003 | 4.976 | 4.807 |
| `LcsLengthBenchmark.memoised` | 32 | N/A | RANDOM | us/op | 2 | 4.985 | 4.699 | 4.809 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | IDENTICAL | us/op | 1 | 14.698 | 13.650 | 13.316 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | IDENTICAL | us/op | 2 | 16.331 | 13.712 | 13.348 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | DISJOINT | us/op | 1 | 108.743 | 107.256 | 103.328 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | DISJOINT | us/op | 2 | 110.151 | 103.369 | 103.239 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | SHIFTED | us/op | 1 | 17.830 | 17.334 | 17.614 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | SHIFTED | us/op | 2 | 23.963 | 29.035 | 20.073 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | RANDOM | us/op | 1 | 113.963 | 109.053 | 109.260 |
| `LcsLengthBenchmark.memoised` | 128 | N/A | RANDOM | us/op | 2 | 117.175 | 108.830 | 107.719 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | IDENTICAL | us/op | 1 | 3.428 | 4.014 | 2.451 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | IDENTICAL | us/op | 2 | 3.358 | 4.221 | 2.620 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | DISJOINT | us/op | 1 | 4.916 | 6.815 | 4.963 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | DISJOINT | us/op | 2 | 3.739 | 4.407 | 9.307 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | SHIFTED | us/op | 1 | 3.375 | 4.150 | 4.877 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | SHIFTED | us/op | 2 | 3.237 | 4.019 | 4.344 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | RANDOM | us/op | 1 | 3.411 | 3.768 | 3.928 |
| `LcsLengthBenchmark.rolling` | 32 | N/A | RANDOM | us/op | 2 | 3.305 | 3.788 | 4.128 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | IDENTICAL | us/op | 1 | 50.647 | 45.988 | 41.209 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | IDENTICAL | us/op | 2 | 40.910 | 49.927 | 38.771 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | DISJOINT | us/op | 1 | 60.173 | 37.818 | 38.822 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | DISJOINT | us/op | 2 | 39.914 | 44.116 | 38.852 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | SHIFTED | us/op | 1 | 45.563 | 38.686 | 48.742 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | SHIFTED | us/op | 2 | 37.675 | 52.390 | 47.547 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | RANDOM | us/op | 1 | 37.791 | 38.095 | 36.933 |
| `LcsLengthBenchmark.rolling` | 128 | N/A | RANDOM | us/op | 2 | 38.386 | 36.788 | 36.377 |
| `LcsLengthBenchmark.table` | 32 | N/A | IDENTICAL | us/op | 1 | 4.911 | 6.056 | 6.099 |
| `LcsLengthBenchmark.table` | 32 | N/A | IDENTICAL | us/op | 2 | 5.786 | 6.572 | 11.658 |
| `LcsLengthBenchmark.table` | 32 | N/A | DISJOINT | us/op | 1 | 8.677 | 7.036 | 6.250 |
| `LcsLengthBenchmark.table` | 32 | N/A | DISJOINT | us/op | 2 | 6.975 | 6.305 | 8.514 |
| `LcsLengthBenchmark.table` | 32 | N/A | SHIFTED | us/op | 1 | 6.381 | 7.067 | 7.770 |
| `LcsLengthBenchmark.table` | 32 | N/A | SHIFTED | us/op | 2 | 5.469 | 7.226 | 8.524 |
| `LcsLengthBenchmark.table` | 32 | N/A | RANDOM | us/op | 1 | 7.422 | 6.396 | 7.078 |
| `LcsLengthBenchmark.table` | 32 | N/A | RANDOM | us/op | 2 | 5.880 | 6.897 | 7.808 |
| `LcsLengthBenchmark.table` | 128 | N/A | IDENTICAL | us/op | 1 | 72.905 | 88.715 | 96.845 |
| `LcsLengthBenchmark.table` | 128 | N/A | IDENTICAL | us/op | 2 | 75.230 | 97.415 | 84.482 |
| `LcsLengthBenchmark.table` | 128 | N/A | DISJOINT | us/op | 1 | 81.981 | 116.731 | 115.125 |
| `LcsLengthBenchmark.table` | 128 | N/A | DISJOINT | us/op | 2 | 84.786 | 137.888 | 135.004 |
| `LcsLengthBenchmark.table` | 128 | N/A | SHIFTED | us/op | 1 | 78.832 | 110.372 | 94.420 |
| `LcsLengthBenchmark.table` | 128 | N/A | SHIFTED | us/op | 2 | 89.829 | 132.126 | 100.356 |
| `LcsLengthBenchmark.table` | 128 | N/A | RANDOM | us/op | 1 | 77.975 | 95.642 | 108.586 |
| `LcsLengthBenchmark.table` | 128 | N/A | RANDOM | us/op | 2 | 78.106 | 109.457 | 106.845 |

Every final-table mean/error matches its result block and parameters. Rounded
means, extrema and sample standard deviations match the observations within
printed precision. Numeric CI arithmetic agrees within endpoint rounding;
the exceptional lower marker is preserved without imputation. No observation
is removed, replaced, converted between units or pooled with another run.

## Research Limits and Next Module

- Coverage is **57 defaults**, selected capacities/sizes, one seed and one runtime
  environment. Other inputs, numeric domains, unequal-length Strings, sparse
  caches, arbitrary-precision values, witness APIs and alternative algorithms
  are not empirically completed. Knapsack O(nC) stays pseudopolynomial.
- All 11 wide-error cells and the literal CI marker remain visible. Confidence
  output is model-based, not a latency distribution or immunity to systematic
  effects. Twelve numeric interval overlaps and two nonnumeric-bound pairs do
  not support a formal significance/equivalence test or universal speedup.
- Source is author-reported; JMH contains no source SHA or jar hash. The full
  command, capture/execution timestamps, CPU/power, generated-code inspection
  and profiler data are unavailable. Prior setup metadata is contextual, not a
  new machine inspection for this run. The received text hash is not a jar hash.
- Full validation/dense initialisation, recursion or rolling state remain timed.
  Logical recurrence/helper counts do not measure instructions, allocation
  rates, bytes or exact timing shares. No subtraction isolates pure recursion,
  cache lookup, GC, CPU or memory effects; sizes alone do not fit an exponent.
- Correctness remains the earlier **694-test verification**, including **22
  module tests**, independent oracles and induction/reconstruction arguments.
  This update changes documentation/comments only, with no executable Java
  change, new timed run or CI status. Earlier Arrays/Searching findings remain.

The three templates, study notes, READMEs and audit now link the initial evidence.
No repeat is needed merely to record this run. A further controlled experiment
can investigate the uncertain Fibonacci table case, vary seeds/shapes/capacity
or isolate setup under a stated design, retaining its own source/runtime and
every error. The next numbered module is **16 - Testing**, grounded in the
repository's actual tests, reference strategies and remaining coverage gaps.

## Source Integrity

Original attachment: `Texto pegado(10).txt`. SHA-256 of the received bytes,
including original line endings:

`ab641c988d2a78dc86bc86177a5040eb4d0910f1c648306d2f18ba8edb023de7`

The raw attachment is not copied into the repository. Markdown retains the
printed benchmark observations with decimal normalisation and explicit units.
No JSON/log or new CSV artifact is created. This hash identifies text, not a jar.
