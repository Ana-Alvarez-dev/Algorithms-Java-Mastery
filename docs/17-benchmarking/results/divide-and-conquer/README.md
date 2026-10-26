# Divide and Conquer: First Reported Results

The author supplied one complete execution covering **all 48 current default
configurations**, split into 16 InversionCount, 16 MaximumSubarray and 16
QuickSelect cells. This report retains **48 means/errors**, **480 measurement-
iteration averages** and **288 warmup averages**. Completion time is **7 minutes
4 seconds**. All three templates now use this evidence in Result/Interpretation.

Initial selected-grid evidence is complete. It is not performance coverage of
all input sizes, ranks, seeds or accepted values. Existing Searching, Sorting
and Recursion results remain separate, with their own source/runtime provenance.
Short harness-smoke scores are not substituted for these author measurements.

## Provenance and Runtime

The author separately supplied source SHA
**`507b2537d3dedd3beb57900d3ee72d7858571c89`**, the merge of PR #84. It matches
the reviewed Divide and Conquer source. This is author-reported checkout
attribution supplied with the results, not a revision embedded by JMH or a
supplied jar hash. The output does not establish when that SHA was captured.
Later documentation commits are distinct from the attributed measurement source.

| Property | Supplied evidence |
|---|---|
| Reporting date | 2026-10-26, America/Buenos_Aires; execution timestamp not supplied |
| JMH | 1.37 in every configuration header |
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
| Confidence output | 99.9% intervals with printed normal-distribution assumption |
| Full command | Not captured; attachment starts with the first JMH header |
| CPU, power settings, profiler metrics, jar hash | Not supplied |
| Operating system | Windows executable path; Windows 11 amd64 reported separately during setup |

The [guide](../../../12-divide-and-conquer/implementation-and-benchmark-guide.md)
instructed this combined invocation:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*divideandconquer.*" -foe true
```

That is the documented protocol, not a recovered command line. Headers,
parameters, forks and completion match all its defaults. Inversion sizes are
100/1,000; MaximumSubarray and QuickSelect sizes are 100/10,000. Their different
size grids must not be conflated or pooled as a single scalability experiment.

Decimal commas become dots, retaining three printed decimals. These are rounded
console iteration averages, not full-precision JMH arrays or single-operation
latencies. No fork/observation is removed as an outlier. Warmup stays separate
from measured means. Printed CI endpoints are preserved, including 0.001
rounding differences from arithmetic on printed mean/error. At very small
scores, three-decimal rounding also limits precision in relative comparisons.

## Contracts and Timed Work

The guide connects definitions, decomposition, manual traces, invariants,
recurrences and independent tests. Trial setup uses seed **20261025** outside
timing. Each family compares the same answer and matching fixtures, with
different work/state costs kept explicit.

| Template | Timed public workflow | Fixture / ownership distinction |
|---|---|---|
| [InversionCountBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/divideandconquer/InversionCountBenchmark.java) | Full validated strict-inversion long count | Merge includes a private clone and scratch array; direct pairs has no matching allocation. Both preserve trial input. |
| [MaximumSubarrayBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/divideandconquer/MaximumSubarrayBenchmark.java) | Full validated canonical nonempty maximum with Optional/Subarray result | Read-only input, no timed clone. Recursion constructs crossing candidates; linear method constructs only the returned range. |
| [QuickSelectBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/divideandconquer/QuickSelectBenchmark.java) | Clone INSIDE timing, then select rank=size/2 or sort fully and read that rank | Fresh copies for both methods preserve repeated trial input. This is upper-middle selection at even sizes, not all ranks or an isolated partition cost. |

RANDOM for inversions/selection uses full-range ints. SORTED and REVERSE_SORTED
reorder the same seeded population; DUPLICATE_HEAVY instead uses values -4..4.
It changes the value population, not just order. MaximumSubarray MIXED uses
-1,000..1,000, ALL_NEGATIVE -1,000..-1, ALL_POSITIVE 1..1,000, and ALL_ZERO zeros.
Sign/zero cases also change tie and branch/update work, rather than isolating
one hardware factor. Only one seed is represented.

All mathematical int-array sums and inversion pair counts fit long. Equal keys
are not inversions. Maximum-subarray ties prefer earliest start, then earliest
exclusive end; all-negative input has a negative nonempty answer. Selection
preserves multiplicities and returns the sorted-rank value without fully sorting
the remaining array. These contract differences remain in the source analysis.

## Complete Means and Errors

Each cell is **mean +/- JMH-reported error in us/op**, `avgt`, `Cnt = 10`.

### InversionCountBenchmark

| Method | Distribution | Size 100 | Size 1,000 |
|---|---|---:|---:|
| `mergeBased` | RANDOM | 2.460 +/- 0.218 | 32.263 +/- 1.939 |
| `mergeBased` | SORTED | 1.962 +/- 0.092 | 26.179 +/- 2.158 |
| `mergeBased` | REVERSE_SORTED | 2.109 +/- 0.097 | 27.509 +/- 0.870 |
| `mergeBased` | DUPLICATE_HEAVY | 2.313 +/- 0.330 | 28.129 +/- 0.674 |
| `quadratic` | RANDOM | 3.122 +/- 2.325 | 250.959 +/- 3.312 |
| `quadratic` | SORTED | 1.140 +/- 0.054 | 87.771 +/- 0.685 |
| `quadratic` | REVERSE_SORTED | 1.132 +/- 0.041 | 97.024 +/- 35.673 |
| `quadratic` | DUPLICATE_HEAVY | 2.485 +/- 0.024 | 251.469 +/- 3.426 |

### MaximumSubarrayBenchmark

| Method | Distribution | Size 100 | Size 10,000 |
|---|---|---:|---:|
| `divideAndConquer` | MIXED | 1.307 +/- 0.012 | 224.857 +/- 4.264 |
| `divideAndConquer` | ALL_NEGATIVE | 1.342 +/- 0.097 | 177.289 +/- 3.953 |
| `divideAndConquer` | ALL_POSITIVE | 1.180 +/- 0.025 | 165.153 +/- 9.217 |
| `divideAndConquer` | ALL_ZERO | 1.203 +/- 0.017 | 164.955 +/- 7.182 |
| `linear` | MIXED | 0.066 +/- 0.001 | 4.978 +/- 0.049 |
| `linear` | ALL_NEGATIVE | 0.056 +/- 0.001 | 4.870 +/- 0.606 |
| `linear` | ALL_POSITIVE | 0.053 +/- 0.008 | 3.448 +/- 0.347 |
| `linear` | ALL_ZERO | 0.062 +/- 0.010 | 5.138 +/- 0.724 |

### QuickSelectBenchmark

| Method | Distribution | Size 100 | Size 10,000 |
|---|---|---:|---:|
| `selectCopy` | RANDOM | 0.325 +/- 0.084 | 102.795 +/- 7.247 |
| `selectCopy` | SORTED | 0.190 +/- 0.025 | 18.015 +/- 3.109 |
| `selectCopy` | REVERSE_SORTED | 0.294 +/- 0.044 | 27.546 +/- 7.438 |
| `selectCopy` | DUPLICATE_HEAVY | 0.248 +/- 0.025 | 92.606 +/- 11.744 |
| `sortCopy` | RANDOM | 1.255 +/- 0.505 | 455.682 +/- 60.337 |
| `sortCopy` | SORTED | 0.080 +/- 0.012 | 9.164 +/- 2.476 |
| `sortCopy` | REVERSE_SORTED | 0.090 +/- 0.007 | 11.224 +/- 0.202 |
| `sortCopy` | DUPLICATE_HEAVY | 0.698 +/- 0.031 | 113.075 +/- 19.241 |

## Interpretation: Inversion Counting and Bounded Crossovers

At size 100, direct pairs has lower means on SORTED (**1.140 +/- 0.054**) and
REVERSE_SORTED (**1.132 +/- 0.041 us/op**) than merge counting (**1.962 +/-
0.092** and **2.109 +/- 0.097 us/op**). A lower asymptotic bound does not imply
a smaller elapsed mean at every small size. RANDOM size 100 has wide direct-pair
uncertainty (**3.122 +/- 2.325** versus merge **2.460 +/- 0.218 us/op**); the
intervals overlap. DUPLICATE_HEAVY size 100 also has overlapping method intervals.

At size 1,000, merge has lower means in all four selected distributions.
For RANDOM it reports **32.263 +/- 1.939** versus direct **250.959 +/- 3.312**;
for SORTED **26.179 +/- 2.158** versus **87.771 +/- 0.685 us/op**. These points
locate behaviour in the measured grid, not a general crossover threshold or a
formal significance test. Reverse direct counting at 1,000 remains noisy:
**97.024 +/- 35.673 us/op**, error about **36.8%** of its mean.

The source explains different work, without assigning its share of time. Direct
counting visits every unordered index pair regardless of how many qualify.
Merge counts qualifying cross pairs in batches but allocates two arrays and
copies/merges every non-leaf interval, including already sorted data.

| Source quantity | n=100 | n=1,000 |
|---|---:|---:|
| Direct pair visits, n(n-1)/2 | 4,950 | 499,500 |
| Recursive calls, 2n-1 | 199 | 1,999 |
| Total merge destination items | 672 | 9,976 |
| Total items copied by per-merge arraycopy calls | 672 | 9,976 |
| Length of each private clone / scratch array | 100 | 1,000 |

Merge item counts sum interval lengths at every internal node; clone creation
and scratch initialisation are additional work. They are not comparator counts,
CPU instructions, bytes, cache misses or profiler events. Direct pair counts
grow about 100.91 times across these sizes, while merge-item counts grow about
14.85 times. Timings need not follow these ratios exactly.

Direct counting has the same pair-visit count in RANDOM, SORTED and REVERSE_SORTED
at a fixed size, yet reported means differ. Qualification/branch patterns and
other runtime work differ; no exact branch-prediction, vectorisation, JIT or
cache cause was measured. No subtraction isolates allocation or merge cost.
The largest relative error here is RANDOM direct size 100, about **74.5%**;
its derived fork means **2.657/3.587 us/op** remain fully represented.

## Interpretation: Maximum Subarray, Total Work and Result Construction

Linear means are lower for every matching fixture in this subset. On MIXED
size 10,000, divide-and-conquer reports **224.857 +/- 4.264** versus linear
**4.978 +/- 0.049 us/op**. On ALL_ZERO size 10,000 the means are **164.955 +/-
7.182** versus **5.138 +/- 0.724 us/op**. All 16 cells have reported error
below 25% of their means; that is not protection against systematic effects
or a formal significance claim.

Both methods return the same canonical range/sum. Division recursively solves
both halves and performs full crossing scans, giving Theta(n log n) word work;
the reference uses a linear ending-range invariant. The following exact source
ledger is independent of sign distribution:

| Source quantity | n=100 | n=10,000 |
|---|---:|---:|
| Recursive calls | 199 | 19,999 |
| Subarray constructor expressions evaluated in recursion | 199 | 19,999 |
| Elements visited by all crossing suffix/prefix scans | 672 | 133,616 |
| Elements initially processed by the linear method | 100 | 10,000 |
| Linear Subarray constructor expressions evaluated | 1 | 1 |

For n>0, crossing scans satisfy S(1)=0 and
S(n)=S(floor(n/2))+S(ceil(n/2))+n. If h=ceil(log2(n)), the sum is
**n*(h+1)-2^h**. Singleton recursive leaves add n separate value reads, not
included in the crossing-scan row. The full binary tree has n leaves and n-1
internal crossing candidates. Both public methods also return an Optional.

These are source-level counts, not actual surviving allocations after JIT,
measured allocation rates or heap bytes. Recursive live auxiliary state has
logarithmic logical depth, while total candidate constructions are linear;
these are different quantities. The experiment does not isolate their timing
shares. Different sign/tie fixtures change scans' update behaviour without
changing their lengths. No GC/cache/CPU cause or asymptotic exponent is inferred
from the two measured sizes. The 100-fold length change is not a claim that
either method's elapsed mean must increase by exactly 100-fold.

## Interpretation: Copy-Plus-Selection Versus Copy-Plus-Sort

Selection has lower means on RANDOM at both sizes: **0.325 +/- 0.084** versus
**1.255 +/- 0.505 us/op** at 100, and **102.795 +/- 7.247** versus
**455.682 +/- 60.337 us/op** at 10,000. Both size-100 RANDOM cells have wide
relative errors (about **25.8%** and **40.2%**), limiting precise ratios.

The ordered distributions reverse the point ranking. On SORTED size 10,000,
selection reports **18.015 +/- 3.109** versus sort **9.164 +/- 2.476 us/op**;
on REVERSE_SORTED, **27.546 +/- 7.438** versus **11.224 +/- 0.202 us/op**.
Sort also has lower means in both ordered size-100 fixtures. These full API
workflows do not imply identical internal sorting work across distributions.
The library's exact executed path was not inspected or profiled here; no claim
that it necessarily performs n log n work on these ordered fixtures is made.

There is a concrete selection-source fact that does not require a timing
inference: on SORTED input, the first/middle/last median value is the value at
rank n/2. The equal band therefore contains the target, including duplicates,
so selection returns after **one partition with n unknown-region iterations**.
It still performs the timed clone, pivot checks, comparisons and swaps; a
single linear pass does not establish it will beat the library call.
On an even-length, strictly descending array, the initial middle value has
ascending rank n/2-1, so upper-middle selection can need more partitions.
That last statement is conditional on distinctness; no unprinted fixture
uniqueness or exact reverse partition count is invented.

DUPLICATE_HEAVY at 10,000 reports selection **92.606 +/- 11.744** and sort
**113.075 +/- 19.241 us/op**. Their intervals overlap, so a precise ranking is
not established by those point means. This fixture has nine possible key values;
it is not all-equal data, and the all-equal one-pass proof does not imply every
duplicate-heavy call takes one partition. At 100, selection mean is lower.

The selector is deterministic median-of-three, not randomised selection or
median-of-medians. Its O(n^2) worst-case bound remains; no measured fixture
establishes arbitrary-input expected-linear time. Production selection uses
O(1) auxiliary state, but this nonmutating benchmark workflow includes an O(n)
clone, as does sorting. No copy-time subtraction, isolated partition overhead,
retained-sorted-array multi-query result or universal speedup is inferred.

## Uncertainty Review

Six of 48 cells have reported error at least 25% of their means: two inversion
and four selection/sort workflow cells; none is MaximumSubarray. The threshold
is descriptive, not a pass/fail rule or significance test. Fork means below
use five rounded measured averages, adding summaries rather than observations.

| Template / method | Distribution | Size | Error / mean | Fork 1 mean (us/op) | Fork 2 mean (us/op) |
|---|---|---:|---:|---:|---:|
| `InversionCountBenchmark.quadratic` | RANDOM | 100 | 74.5% | 2.657 | 3.587 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 1000 | 36.8% | 83.899 | 110.149 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 100 | 25.8% | 0.304 | 0.346 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 10000 | 27.0% | 30.845 | 24.246 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 100 | 40.2% | 1.110 | 1.400 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 10000 | 27.0% | 9.815 | 8.514 |

## Statistics and Derived Fork Means

All entries are **us/op**. Min/max are iteration averages, not invocation
latency extremes. Standard deviation differs from JMH error. Derived means
are rounded to three decimals from the five supplied values in each fork.
CI endpoints preserve the supplied 99.9% model output and its assumptions.

| Template / method | Distribution | Size | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| `InversionCountBenchmark.mergeBased` | RANDOM | 100 | 2.312 | 2.798 | 0.144 | 2.242 | 2.678 | 2.421 | 2.499 |
| `InversionCountBenchmark.mergeBased` | RANDOM | 1000 | 30.518 | 34.226 | 1.282 | 30.324 | 34.202 | 32.070 | 32.456 |
| `InversionCountBenchmark.mergeBased` | SORTED | 100 | 1.892 | 2.087 | 0.061 | 1.870 | 2.053 | 1.956 | 1.967 |
| `InversionCountBenchmark.mergeBased` | SORTED | 1000 | 24.770 | 29.447 | 1.427 | 24.021 | 28.337 | 26.327 | 26.031 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 100 | 2.030 | 2.215 | 0.064 | 2.012 | 2.206 | 2.063 | 2.156 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 1000 | 26.797 | 28.503 | 0.575 | 26.639 | 28.379 | 27.331 | 27.687 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 100 | 2.042 | 2.744 | 0.218 | 1.983 | 2.643 | 2.281 | 2.345 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 1000 | 27.705 | 29.169 | 0.446 | 27.455 | 28.803 | 28.030 | 28.228 |
| `InversionCountBenchmark.quadratic` | RANDOM | 100 | 2.551 | 7.489 | 1.538 | 0.797 | 5.447 | 2.657 | 3.587 |
| `InversionCountBenchmark.quadratic` | RANDOM | 1000 | 248.084 | 254.101 | 2.191 | 247.648 | 254.271 | 249.986 | 251.933 |
| `InversionCountBenchmark.quadratic` | SORTED | 100 | 1.113 | 1.216 | 0.035 | 1.087 | 1.194 | 1.154 | 1.127 |
| `InversionCountBenchmark.quadratic` | SORTED | 1000 | 87.184 | 88.720 | 0.453 | 87.086 | 88.456 | 87.832 | 87.710 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 100 | 1.110 | 1.203 | 0.027 | 1.091 | 1.172 | 1.140 | 1.124 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 1000 | 82.685 | 144.623 | 23.596 | 61.351 | 132.697 | 83.899 | 110.149 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 100 | 2.464 | 2.524 | 0.016 | 2.461 | 2.508 | 2.489 | 2.480 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 1000 | 248.391 | 255.749 | 2.266 | 248.043 | 254.895 | 251.061 | 251.877 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 100 | 1.293 | 1.319 | 0.008 | 1.295 | 1.319 | 1.310 | 1.305 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 10000 | 220.222 | 228.935 | 2.820 | 220.593 | 229.122 | 223.219 | 226.496 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 100 | 1.280 | 1.496 | 0.064 | 1.246 | 1.439 | 1.299 | 1.385 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 10000 | 174.707 | 184.107 | 2.614 | 173.337 | 181.242 | 176.017 | 178.561 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 100 | 1.159 | 1.210 | 0.016 | 1.155 | 1.205 | 1.182 | 1.177 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 10000 | 160.124 | 181.081 | 6.097 | 155.936 | 174.370 | 168.844 | 161.462 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 100 | 1.186 | 1.224 | 0.011 | 1.186 | 1.220 | 1.209 | 1.198 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 10000 | 159.961 | 172.544 | 4.750 | 157.774 | 172.137 | 169.138 | 160.773 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 100 | 0.066 | 0.067 | 0.001 | 0.066 | 0.067 | 0.067 | 0.066 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 10000 | 4.923 | 5.019 | 0.032 | 4.929 | 5.027 | 4.966 | 4.991 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 100 | 0.056 | 0.057 | 0.001 | 0.056 | 0.057 | 0.056 | 0.056 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 10000 | 4.638 | 5.877 | 0.401 | 4.263 | 5.476 | 4.671 | 5.069 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 100 | 0.046 | 0.065 | 0.006 | 0.044 | 0.061 | 0.052 | 0.054 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 10000 | 3.167 | 3.985 | 0.230 | 3.101 | 3.795 | 3.602 | 3.294 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 100 | 0.057 | 0.079 | 0.006 | 0.052 | 0.071 | 0.064 | 0.059 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 10000 | 4.684 | 6.359 | 0.479 | 4.414 | 5.862 | 5.100 | 5.175 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 100 | 0.280 | 0.475 | 0.056 | 0.241 | 0.410 | 0.304 | 0.346 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 10000 | 96.322 | 113.364 | 4.793 | 95.549 | 110.042 | 100.687 | 104.904 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 100 | 0.169 | 0.218 | 0.017 | 0.165 | 0.215 | 0.181 | 0.199 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 10000 | 14.314 | 21.379 | 2.056 | 14.906 | 21.123 | 19.436 | 16.593 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 100 | 0.271 | 0.368 | 0.029 | 0.250 | 0.339 | 0.309 | 0.280 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 10000 | 23.377 | 39.101 | 4.919 | 20.108 | 34.983 | 30.845 | 24.246 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 100 | 0.227 | 0.271 | 0.017 | 0.223 | 0.274 | 0.244 | 0.253 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 10000 | 85.974 | 107.257 | 7.768 | 80.863 | 104.350 | 90.315 | 94.897 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 100 | 0.973 | 1.968 | 0.334 | 0.750 | 1.760 | 1.110 | 1.400 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 10000 | 417.747 | 545.671 | 39.909 | 395.345 | 516.019 | 467.801 | 443.562 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 100 | 0.070 | 0.091 | 0.008 | 0.068 | 0.091 | 0.075 | 0.084 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 10000 | 7.393 | 12.817 | 1.638 | 6.688 | 11.640 | 9.815 | 8.514 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 100 | 0.085 | 0.098 | 0.005 | 0.083 | 0.097 | 0.086 | 0.093 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 10000 | 11.081 | 11.474 | 0.133 | 11.023 | 11.426 | 11.205 | 11.244 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 100 | 0.687 | 0.756 | 0.021 | 0.667 | 0.730 | 0.705 | 0.692 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 10000 | 100.373 | 135.468 | 12.726 | 93.835 | 132.316 | 107.214 | 118.937 |

## Complete Measurement-Iteration Evidence

These **480 values**, in **us/op**, are rounded measurement-iteration averages.
Each timed iteration contains invocations; `Cnt=10` is ten iteration means
across two forks, not ten operations or a request-latency distribution.

| Template / method | Distribution | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---|---:|---:|---:|---:|---:|---:|---:|
| `InversionCountBenchmark.mergeBased` | RANDOM | 100 | 1 | 2.480 | 2.516 | 2.398 | 2.363 | 2.349 |
| `InversionCountBenchmark.mergeBased` | RANDOM | 100 | 2 | 2.344 | 2.312 | 2.493 | 2.548 | 2.798 |
| `InversionCountBenchmark.mergeBased` | RANDOM | 1000 | 1 | 31.531 | 30.518 | 34.226 | 32.950 | 31.126 |
| `InversionCountBenchmark.mergeBased` | RANDOM | 1000 | 2 | 31.653 | 33.257 | 30.924 | 33.770 | 32.675 |
| `InversionCountBenchmark.mergeBased` | SORTED | 100 | 1 | 1.940 | 1.959 | 2.048 | 1.942 | 1.892 |
| `InversionCountBenchmark.mergeBased` | SORTED | 100 | 2 | 1.937 | 1.918 | 1.971 | 1.923 | 2.087 |
| `InversionCountBenchmark.mergeBased` | SORTED | 1000 | 1 | 24.770 | 25.361 | 26.779 | 25.276 | 29.447 |
| `InversionCountBenchmark.mergeBased` | SORTED | 1000 | 2 | 25.306 | 26.353 | 27.508 | 25.896 | 25.090 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 100 | 1 | 2.088 | 2.037 | 2.087 | 2.073 | 2.030 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 100 | 2 | 2.215 | 2.207 | 2.156 | 2.096 | 2.104 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 1000 | 1 | 27.814 | 27.415 | 27.309 | 27.322 | 26.797 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 1000 | 2 | 27.096 | 26.908 | 28.389 | 28.503 | 27.539 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 100 | 1 | 2.410 | 2.276 | 2.227 | 2.298 | 2.196 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 100 | 2 | 2.744 | 2.605 | 2.099 | 2.042 | 2.233 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 1000 | 1 | 28.239 | 27.718 | 27.969 | 27.943 | 28.280 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 1000 | 2 | 29.169 | 27.705 | 28.493 | 27.813 | 27.960 |
| `InversionCountBenchmark.quadratic` | RANDOM | 100 | 1 | 2.551 | 2.905 | 2.618 | 2.655 | 2.557 |
| `InversionCountBenchmark.quadratic` | RANDOM | 100 | 2 | 2.573 | 2.633 | 2.597 | 2.641 | 7.489 |
| `InversionCountBenchmark.quadratic` | RANDOM | 1000 | 1 | 249.640 | 248.084 | 248.896 | 250.580 | 252.730 |
| `InversionCountBenchmark.quadratic` | RANDOM | 1000 | 2 | 254.016 | 252.359 | 254.101 | 250.205 | 248.983 |
| `InversionCountBenchmark.quadratic` | SORTED | 100 | 1 | 1.216 | 1.191 | 1.116 | 1.114 | 1.131 |
| `InversionCountBenchmark.quadratic` | SORTED | 100 | 2 | 1.146 | 1.135 | 1.126 | 1.116 | 1.113 |
| `InversionCountBenchmark.quadratic` | SORTED | 1000 | 1 | 87.587 | 88.062 | 88.271 | 87.633 | 87.609 |
| `InversionCountBenchmark.quadratic` | SORTED | 1000 | 2 | 87.184 | 88.720 | 87.432 | 87.508 | 87.706 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 100 | 1 | 1.203 | 1.117 | 1.143 | 1.126 | 1.110 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 100 | 2 | 1.129 | 1.123 | 1.126 | 1.130 | 1.111 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 1000 | 1 | 82.685 | 83.338 | 82.874 | 83.849 | 86.748 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 1000 | 2 | 144.623 | 135.520 | 103.136 | 84.080 | 83.386 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 100 | 1 | 2.489 | 2.464 | 2.482 | 2.484 | 2.524 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 100 | 2 | 2.479 | 2.485 | 2.486 | 2.474 | 2.478 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 1000 | 1 | 248.391 | 251.476 | 252.398 | 249.153 | 253.886 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 1000 | 2 | 249.890 | 250.251 | 250.694 | 252.803 | 255.749 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 100 | 1 | 1.313 | 1.319 | 1.316 | 1.302 | 1.298 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 100 | 2 | 1.308 | 1.304 | 1.311 | 1.293 | 1.307 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 10000 | 1 | 222.831 | 227.069 | 224.716 | 220.222 | 221.258 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 10000 | 2 | 226.163 | 228.935 | 225.814 | 227.678 | 223.889 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 100 | 1 | 1.320 | 1.291 | 1.312 | 1.280 | 1.292 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 100 | 2 | 1.335 | 1.345 | 1.358 | 1.392 | 1.496 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 10000 | 1 | 175.851 | 177.559 | 174.707 | 176.369 | 175.601 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 10000 | 2 | 184.107 | 177.745 | 176.310 | 178.144 | 176.498 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 100 | 1 | 1.169 | 1.210 | 1.191 | 1.176 | 1.166 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 100 | 2 | 1.159 | 1.161 | 1.183 | 1.186 | 1.196 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 10000 | 1 | 166.368 | 164.306 | 167.141 | 181.081 | 165.323 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 10000 | 2 | 160.124 | 163.258 | 161.064 | 160.607 | 162.258 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 100 | 1 | 1.200 | 1.202 | 1.208 | 1.210 | 1.224 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 100 | 2 | 1.196 | 1.204 | 1.186 | 1.213 | 1.189 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 10000 | 1 | 167.679 | 168.372 | 170.991 | 166.103 | 172.544 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 10000 | 2 | 160.604 | 161.044 | 161.428 | 159.961 | 160.828 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 100 | 1 | 0.066 | 0.067 | 0.066 | 0.067 | 0.067 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 100 | 2 | 0.066 | 0.066 | 0.067 | 0.066 | 0.066 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 10000 | 1 | 4.970 | 4.947 | 4.923 | 4.977 | 5.013 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 10000 | 2 | 5.019 | 4.945 | 4.987 | 5.012 | 4.990 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 100 | 1 | 0.056 | 0.056 | 0.057 | 0.057 | 0.056 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 100 | 2 | 0.057 | 0.057 | 0.056 | 0.056 | 0.056 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 10000 | 1 | 4.689 | 4.726 | 4.652 | 4.650 | 4.638 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 10000 | 2 | 4.798 | 4.721 | 4.673 | 5.275 | 5.877 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 100 | 1 | 0.053 | 0.050 | 0.049 | 0.053 | 0.056 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 100 | 2 | 0.065 | 0.058 | 0.046 | 0.049 | 0.050 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 10000 | 1 | 3.985 | 3.573 | 3.402 | 3.572 | 3.479 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 10000 | 2 | 3.406 | 3.337 | 3.241 | 3.167 | 3.319 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 100 | 1 | 0.062 | 0.079 | 0.064 | 0.058 | 0.058 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 100 | 2 | 0.062 | 0.059 | 0.057 | 0.060 | 0.058 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 10000 | 1 | 5.277 | 4.684 | 5.331 | 5.053 | 5.157 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 10000 | 2 | 4.796 | 4.821 | 4.868 | 5.033 | 6.359 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 100 | 1 | 0.280 | 0.307 | 0.314 | 0.305 | 0.316 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 100 | 2 | 0.280 | 0.319 | 0.314 | 0.475 | 0.344 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 10000 | 1 | 104.739 | 102.648 | 100.372 | 99.355 | 96.322 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 10000 | 2 | 98.308 | 103.866 | 106.016 | 113.364 | 102.964 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 100 | 1 | 0.186 | 0.183 | 0.187 | 0.178 | 0.169 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 100 | 2 | 0.173 | 0.188 | 0.217 | 0.218 | 0.200 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 10000 | 1 | 17.367 | 18.136 | 19.864 | 21.379 | 20.436 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 10000 | 2 | 14.314 | 16.905 | 16.784 | 17.484 | 17.478 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 100 | 1 | 0.275 | 0.298 | 0.291 | 0.315 | 0.368 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 100 | 2 | 0.298 | 0.276 | 0.273 | 0.271 | 0.281 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 10000 | 1 | 39.101 | 30.170 | 28.131 | 31.361 | 25.463 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 10000 | 2 | 23.543 | 23.736 | 23.377 | 25.446 | 25.130 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 100 | 1 | 0.260 | 0.247 | 0.239 | 0.231 | 0.242 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 100 | 2 | 0.271 | 0.264 | 0.227 | 0.233 | 0.271 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 10000 | 1 | 86.227 | 99.658 | 90.116 | 87.006 | 88.570 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 10000 | 2 | 103.267 | 107.257 | 85.974 | 89.425 | 88.562 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 100 | 1 | 0.977 | 1.009 | 0.988 | 1.439 | 1.138 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 100 | 2 | 1.367 | 1.089 | 1.968 | 1.604 | 0.973 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 10000 | 1 | 439.920 | 430.712 | 474.588 | 448.116 | 545.671 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 10000 | 2 | 419.204 | 417.747 | 422.800 | 481.128 | 476.931 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 100 | 1 | 0.078 | 0.071 | 0.073 | 0.074 | 0.079 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 100 | 2 | 0.089 | 0.084 | 0.087 | 0.091 | 0.070 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 10000 | 1 | 10.013 | 8.023 | 8.945 | 12.817 | 9.275 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 10000 | 2 | 10.676 | 8.023 | 7.393 | 7.868 | 8.608 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 100 | 1 | 0.086 | 0.088 | 0.086 | 0.087 | 0.085 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 100 | 2 | 0.090 | 0.097 | 0.091 | 0.098 | 0.090 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 10000 | 1 | 11.211 | 11.474 | 11.081 | 11.158 | 11.101 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 10000 | 2 | 11.134 | 11.279 | 11.111 | 11.311 | 11.385 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 100 | 1 | 0.695 | 0.756 | 0.687 | 0.693 | 0.692 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 100 | 2 | 0.700 | 0.691 | 0.688 | 0.693 | 0.688 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 10000 | 1 | 104.950 | 115.212 | 110.652 | 103.873 | 101.381 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 10000 | 2 | 128.300 | 126.577 | 135.468 | 103.969 | 100.373 |

## Complete Warmup Evidence

These **288 values**, in **us/op**, are excluded from measured means and
derived fork means. Their order and fork membership are retained.

| Template / method | Distribution | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---|---:|---:|---:|---:|---:|
| `InversionCountBenchmark.mergeBased` | RANDOM | 100 | 1 | 2.877 | 2.539 | 2.484 |
| `InversionCountBenchmark.mergeBased` | RANDOM | 100 | 2 | 2.658 | 2.396 | 2.495 |
| `InversionCountBenchmark.mergeBased` | RANDOM | 1000 | 1 | 33.139 | 33.248 | 31.831 |
| `InversionCountBenchmark.mergeBased` | RANDOM | 1000 | 2 | 32.858 | 35.352 | 32.954 |
| `InversionCountBenchmark.mergeBased` | SORTED | 100 | 1 | 2.192 | 2.075 | 1.996 |
| `InversionCountBenchmark.mergeBased` | SORTED | 100 | 2 | 1.991 | 2.059 | 1.973 |
| `InversionCountBenchmark.mergeBased` | SORTED | 1000 | 1 | 26.032 | 26.212 | 24.840 |
| `InversionCountBenchmark.mergeBased` | SORTED | 1000 | 2 | 25.572 | 24.786 | 24.955 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 100 | 1 | 2.203 | 2.160 | 2.361 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 100 | 2 | 2.189 | 2.117 | 2.163 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 1000 | 1 | 28.601 | 27.001 | 26.883 |
| `InversionCountBenchmark.mergeBased` | REVERSE_SORTED | 1000 | 2 | 28.082 | 26.530 | 27.415 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 100 | 1 | 2.344 | 2.252 | 2.166 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 100 | 2 | 2.149 | 2.159 | 2.557 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 1000 | 1 | 29.489 | 27.674 | 27.276 |
| `InversionCountBenchmark.mergeBased` | DUPLICATE_HEAVY | 1000 | 2 | 29.337 | 27.499 | 28.201 |
| `InversionCountBenchmark.quadratic` | RANDOM | 100 | 1 | 2.729 | 2.679 | 2.567 |
| `InversionCountBenchmark.quadratic` | RANDOM | 100 | 2 | 2.945 | 2.964 | 2.577 |
| `InversionCountBenchmark.quadratic` | RANDOM | 1000 | 1 | 272.080 | 261.212 | 250.585 |
| `InversionCountBenchmark.quadratic` | RANDOM | 1000 | 2 | 253.715 | 250.857 | 252.134 |
| `InversionCountBenchmark.quadratic` | SORTED | 100 | 1 | 1.163 | 1.115 | 1.119 |
| `InversionCountBenchmark.quadratic` | SORTED | 100 | 2 | 1.178 | 1.106 | 1.151 |
| `InversionCountBenchmark.quadratic` | SORTED | 1000 | 1 | 84.701 | 95.294 | 95.681 |
| `InversionCountBenchmark.quadratic` | SORTED | 1000 | 2 | 84.818 | 87.543 | 87.901 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 100 | 1 | 1.147 | 1.411 | 1.183 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 100 | 2 | 1.158 | 1.106 | 1.120 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 1000 | 1 | 85.545 | 82.931 | 85.035 |
| `InversionCountBenchmark.quadratic` | REVERSE_SORTED | 1000 | 2 | 86.547 | 83.908 | 86.302 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 100 | 1 | 2.561 | 2.564 | 2.491 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 100 | 2 | 2.585 | 2.501 | 2.499 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 1000 | 1 | 254.200 | 249.804 | 250.565 |
| `InversionCountBenchmark.quadratic` | DUPLICATE_HEAVY | 1000 | 2 | 252.323 | 249.884 | 248.648 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 100 | 1 | 1.612 | 1.341 | 1.310 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 100 | 2 | 1.782 | 1.334 | 1.309 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 10000 | 1 | 250.551 | 228.557 | 224.128 |
| `MaximumSubarrayBenchmark.divideAndConquer` | MIXED | 10000 | 2 | 270.098 | 240.806 | 230.256 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 100 | 1 | 1.552 | 1.550 | 1.294 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 100 | 2 | 1.530 | 1.345 | 1.348 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 10000 | 1 | 200.174 | 178.986 | 184.674 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_NEGATIVE | 10000 | 2 | 197.368 | 182.131 | 185.167 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 100 | 1 | 1.365 | 1.225 | 1.210 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 100 | 2 | 1.386 | 1.233 | 1.220 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 10000 | 1 | 186.399 | 166.268 | 166.452 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_POSITIVE | 10000 | 2 | 217.661 | 163.260 | 166.271 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 100 | 1 | 1.369 | 1.252 | 1.209 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 100 | 2 | 1.357 | 1.203 | 1.217 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 10000 | 1 | 177.618 | 161.876 | 160.410 |
| `MaximumSubarrayBenchmark.divideAndConquer` | ALL_ZERO | 10000 | 2 | 178.186 | 162.999 | 162.211 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 100 | 1 | 0.082 | 0.080 | 0.067 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 100 | 2 | 0.083 | 0.080 | 0.066 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 10000 | 1 | 5.270 | 5.379 | 4.883 |
| `MaximumSubarrayBenchmark.linear` | MIXED | 10000 | 2 | 5.277 | 5.362 | 5.041 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 100 | 1 | 0.060 | 0.057 | 0.056 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 100 | 2 | 0.060 | 0.058 | 0.057 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 10000 | 1 | 5.156 | 4.668 | 4.742 |
| `MaximumSubarrayBenchmark.linear` | ALL_NEGATIVE | 10000 | 2 | 5.732 | 4.780 | 5.083 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 100 | 1 | 0.073 | 0.053 | 0.054 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 100 | 2 | 0.095 | 0.072 | 0.076 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 10000 | 1 | 4.338 | 4.020 | 3.833 |
| `MaximumSubarrayBenchmark.linear` | ALL_POSITIVE | 10000 | 2 | 4.266 | 4.092 | 3.634 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 100 | 1 | 0.063 | 0.061 | 0.061 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 100 | 2 | 0.070 | 0.071 | 0.063 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 10000 | 1 | 5.531 | 5.746 | 6.378 |
| `MaximumSubarrayBenchmark.linear` | ALL_ZERO | 10000 | 2 | 5.596 | 5.131 | 4.918 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 100 | 1 | 0.349 | 0.348 | 0.283 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 100 | 2 | 0.326 | 0.277 | 0.276 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 10000 | 1 | 98.423 | 103.516 | 103.801 |
| `QuickSelectBenchmark.selectCopy` | RANDOM | 10000 | 2 | 100.212 | 93.372 | 110.464 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 100 | 1 | 0.157 | 0.148 | 0.186 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 100 | 2 | 0.171 | 0.150 | 0.179 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 10000 | 1 | 16.717 | 16.420 | 15.175 |
| `QuickSelectBenchmark.selectCopy` | SORTED | 10000 | 2 | 17.843 | 17.014 | 16.838 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 100 | 1 | 0.279 | 0.290 | 0.297 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 100 | 2 | 0.335 | 0.248 | 0.310 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 10000 | 1 | 26.746 | 26.905 | 28.558 |
| `QuickSelectBenchmark.selectCopy` | REVERSE_SORTED | 10000 | 2 | 27.575 | 27.456 | 26.052 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 100 | 1 | 0.246 | 0.241 | 0.261 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 100 | 2 | 0.256 | 0.400 | 0.309 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 10000 | 1 | 78.160 | 92.518 | 86.889 |
| `QuickSelectBenchmark.selectCopy` | DUPLICATE_HEAVY | 10000 | 2 | 81.873 | 97.350 | 118.510 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 100 | 1 | 1.045 | 0.955 | 0.915 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 100 | 2 | 1.264 | 1.350 | 1.225 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 10000 | 1 | 504.726 | 444.368 | 456.269 |
| `QuickSelectBenchmark.sortCopy` | RANDOM | 10000 | 2 | 478.856 | 439.695 | 424.905 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 100 | 1 | 0.104 | 0.087 | 0.091 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 100 | 2 | 0.079 | 0.076 | 0.078 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 10000 | 1 | 7.790 | 8.133 | 13.525 |
| `QuickSelectBenchmark.sortCopy` | SORTED | 10000 | 2 | 10.695 | 8.569 | 9.176 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 100 | 1 | 0.100 | 0.112 | 0.096 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 100 | 2 | 0.101 | 0.094 | 0.091 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 10000 | 1 | 12.123 | 10.226 | 10.427 |
| `QuickSelectBenchmark.sortCopy` | REVERSE_SORTED | 10000 | 2 | 12.042 | 10.246 | 10.928 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 100 | 1 | 0.798 | 0.753 | 0.705 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 100 | 2 | 0.790 | 0.699 | 0.689 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 10000 | 1 | 103.636 | 100.504 | 101.647 |
| `QuickSelectBenchmark.sortCopy` | DUPLICATE_HEAVY | 10000 | 2 | 113.817 | 152.367 | 145.575 |

Every final-table mean/error agrees with its result block and parameters.
Every pooled mean agrees with its ten rounded observations within 0.001 us/op;
min/max and sample standard deviations agree to printed precision. No sample
was removed, replaced or pooled with a separate experiment.

## Research Limits and Module Handoff

- Coverage is **48 selected defaults**, one seed, one runtime/environment and
  only middle-rank selection. Other sizes, ranks, seeds, adversarial inputs,
  repeated queries, parallel/generic variants and stronger selection guarantees
  remain outside this experiment.
- Six wide-error cells remain intact. JMH model confidence intervals are not
  invocation latency ranges or protection against systematic experimental
  effects. No formal significance, equivalence or precise universal ratio is
  claimed; overlapping intervals do not establish equivalence either.
- The SHA is author-reported, not embedded in the output or verified by a jar
  hash. Command line, execution timestamp, CPU/power details, generated-code
  inspection and profiler data are not supplied. Source work counts are
  analytical, not observations of machine events or measured allocations.
- Full ownership/output/work differences are timed. There is no subtraction
  for a pure call, clone, merge, crossing-scan or partition cost. Two-size data
  does not prove a complexity exponent, and favourable cases do not remove
  deterministic selection's worst-case bound.
- Correctness remains the earlier **618-test verification**, including **18
  module tests**, independent probes and invariants. This results update changes
  no executable Java and introduces no new timed run or CI status. Open earlier
  Arrays/Searching findings remain unchanged.

The three templates, READMEs, case-study notes and traceability review now link
the complete initial evidence. No repeat is needed merely to record the run.
Future controls can vary ranks/seeds/sizes or investigate library paths and
allocation with matching source/runtime/profiler evidence; later runs retain
their own provenance and every error rather than overwriting this record.
Module 13 (Greedy), Backtracking and Dynamic Programming remain separate work.

## Source Integrity

Original attachment: `Texto pegado(7).txt`. SHA-256 of the received bytes,
including original line endings:

`84d9b1c3a5b80b1a67aad2ec030798b6d06470939d1e7bbe27b05466d1dd7a11`

The attachment is not copied into the repository. Markdown tables and decimal
normalisation retain every printed numerical observation. No JSON/log or new
CSV artifact is created. This hash identifies the received text, not a jar.
