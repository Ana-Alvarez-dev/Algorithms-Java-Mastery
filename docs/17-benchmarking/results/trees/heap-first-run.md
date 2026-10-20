# Heaps: First Reported Measurement Subset

The author supplied this complete **HeapOperationsBenchmark** run on
**2026-10-19 (America/Buenos_Aires)**, after reporting source checkout
**`ce621c63fabc2fe04c180d71fd1f9e165cc8a447`** before launch. It contains
**24 configurations**, **240 measurement-iteration averages** and **144 warmup
iteration averages**, completed in **3 minutes 36 seconds**. The heap template's
Result and Interpretation now use this evidence.

Together with the separate [lookup run](README.md), the initial Trees evidence
now covers **48 reported configurations**: 24 lookup and 24 heap cells. This
completes the requested two-size subsets, not all 72 default configurations.
The two executions retain separate source identities and statistics; their
iteration samples are not pooled. Every supplied heap observation is preserved
below in Markdown, without JSON, log or copied console-text artifacts.

## Provenance and Runtime

| Property | Supplied evidence or explicit limitation |
|---|---|
| Source | Author-pasted console attachment `Texto pegado(4).txt` |
| Reporting date | 2026-10-19, author's timezone; no benchmark execution timestamp printed |
| Source checkout reported before launch | `ce621c63fabc2fe04c180d71fd1f9e165cc8a447`, merge of PR #77 |
| Reviewed documentation base | `958af3c64c5bc1123820dc199ddeda2dafd6b57a`, merge of PR #78; later than the reported execution checkout |
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
| Coverage | 6 heap methods x RANDOM/REVERSE_SORTED x sizes 100/1,000 |
| Measurement count | `Cnt = 10` iteration averages per cell, not ten individual invocations |
| Confidence output | 99.9% intervals, with JMH's printed normal-distribution assumption |
| Full command line | First pasted line is truncated; headers and coverage match the instructed command |
| CPU, power settings and profiler metrics | Not supplied |
| Operating system | Windows executable path; Windows 11 amd64 reported separately during setup |

The source SHA was supplied in the conversation **before** this heap run, not
inferred from the later results merge. It is an author-reported checkout identity,
not a source stamp embedded by JMH or a supplied jar hash. The earlier lookup
run used `c576cb7`, identified separately from the author's reflog. Trees
production/JMH executable source at `ce621c6` is equivalent to that earlier
revision after excluding block comments; the comparison does not replace each
run's own provenance or establish byte-for-byte jar identity.

The instructed heap command was:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*trees.HeapOperationsBenchmark.*" -p size=100,1000 -foe true
```

This is not presented as an intact captured command line. Decimal commas become
dots, preserving three printed decimals. Iteration averages are rounded console
observations, not full-precision raw JMH arrays or individual-operation samples.
No value is removed as an outlier. One supplied CI lower marker is `? 0` rather
than a numeric bound; it is retained literally and discussed below.

## Timed Work and Analytical Model

The [implementation guide](../../../09-trees/implementation-and-benchmark-guide.md)
connects contracts, traces, invariants, costs and tests. The
[heap benchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/trees/HeapOperationsBenchmark.java)
separates three operation families:

| Family | One timed invocation | Included/excluded work |
|---|---|---|
| Manual/JDK peek | Read minimum and return its int value | Manual OptionalInt access versus JDK unboxing is included; initial construction excluded |
| Manual/JDK poll/restore | Remove current minimum, reinsert it, return its int value | Pair cost; count/multiset restored; capacity reserved; physical arrangement may change |
| Bottom-up/repeated-offer build | Allocate a new manual heap from the same trial int array and return it | Container/array allocation and population included; growth excluded; no copy baseline subtracted |

Sizes are 100 and 1,000. RANDOM uses unrestricted int values from SplittableRandom
seed **20261019**; REVERSE_SORTED uses n..1. Trial setup initially fills both
representations through repeated offers with reserved n capacity and preboxes
JDK values. Setup and initial boxing are outside peek/pair timing. JDK pairs
reinsert the exact removed boxed object; manual primitive storage and OptionalInt
mechanics differ. No allocation-rate or memory-footprint equivalence is implied.

A pair always restores the current minimum, not a random replacement priority.
It is a deliberately warm repeated path. In the descending distinct fixture,
manual restoration offers a value smaller than every remaining key, moving it
from the final slot to the root. Those conditions differ from ordinary arbitrary
priority churn. Scores cannot be relabelled isolated poll latency.

Both build methods allocate n-slot backing arrays. Bottom-up copies input and
sifts parents from n/2-1 downward. Repeated-offer reserves n then inserts every
input value. Returning the constructed object preserves the intended result;
JIT/compiler-blackhole behaviour remains part of the experimental context.
Neither method times dynamic array growth or mutates the source input.

## Complete Reported Means and Errors

Every cell is **mean +/- JMH-reported error in ns/op**, `avgt`, `Cnt = 10`.
The distribution names describe input construction, not a new random query for
each timed invocation. Keep errors with means when comparing values.

| Method | Distribution | Size 100 | Size 1,000 |
|---|---|---:|---:|
| `bottomUpBuild` | RANDOM | 246.716 +/- 42.055 | 2371.967 +/- 229.000 |
| `bottomUpBuild` | REVERSE_SORTED | 240.554 +/- 34.466 | 2354.977 +/- 304.174 |
| `javaPeek` | RANDOM | 5.012 +/- 11.922 | 1.444 +/- 0.264 |
| `javaPeek` | REVERSE_SORTED | 1.465 +/- 0.365 | 1.549 +/- 0.574 |
| `javaPollRestore` | RANDOM | 40.920 +/- 9.989 | 54.143 +/- 5.163 |
| `javaPollRestore` | REVERSE_SORTED | 42.449 +/- 4.511 | 59.339 +/- 8.445 |
| `manualPeek` | RANDOM | 1.173 +/- 0.117 | 1.173 +/- 0.133 |
| `manualPeek` | REVERSE_SORTED | 1.203 +/- 0.178 | 1.182 +/- 0.120 |
| `manualPollRestore` | RANDOM | 22.645 +/- 13.338 | 25.243 +/- 2.258 |
| `manualPollRestore` | REVERSE_SORTED | 19.979 +/- 12.327 | 23.472 +/- 2.604 |
| `repeatedOfferBuild` | RANDOM | 252.946 +/- 26.689 | 2919.485 +/- 247.188 |
| `repeatedOfferBuild` | REVERSE_SORTED | 774.179 +/- 379.514 | 13996.646 +/- 2803.147 |

## Interpretation

### Construction: Bottom-up Versus Repeated Offer

Bottom-up means are approximately 240–247 ns/op at 100 and 2,355–2,372 ns/op at
1,000 across the two inputs. Repeated-offer RANDOM means are **252.946 +/-
26.689** and **2,919.485 +/- 247.188 ns/op**. At 100, the bottom-up and repeated-
offer RANDOM intervals overlap; these data do not establish a precise winner
there. Under descending input, repeated-offer reports **774.179 +/- 379.514**
and **13,996.646 +/- 2,803.147 ns/op**, versus bottom-up **240.554 +/- 34.466**
and **2,354.977 +/- 304.174 ns/op**.

Ratios of repeated-offer mean to bottom-up mean are approximately **1.03/1.23**
for RANDOM at sizes 100/1,000 and **3.22/5.94** for descending input. These are
point ratios, without separately estimated ratio confidence intervals. The
large descending size-100 error and the other reported errors must remain
visible. No formal significance test or generalised ranking is claimed.

The source explains the adverse repeated-offer workload. In descending distinct
input, each new value is smaller than all previous values. Inserting the ith
value moves every ancestor on its path, exactly floor(log2(i)) parent moves.
Summing over a complete build gives:

| Size | Descending repeated-offer parent moves | Derivation |
|---|---:|---|
| 100 | 480 | Sum of floor(log2(i)) for i=1..100 |
| 1,000 | 7,987 | Sum of floor(log2(i)) for i=1..1,000 |

These are source-level work counts, not measured CPU instructions. The count
is checked by summing the integer bit-length formula, not by a timed profiler.
Bottom-up build instead benefits from many low-height subtrees; the sum of
height-weighted sift bounds is O(n), with input copying giving Theta(n) total
construction work. The measured contrast is consistent with these separate
analytical arguments. Two sizes, one RANDOM seed, allocation and runtime effects
do not establish a fitted exponent or the exact cause of nanosecond ratios.
The counts also do not imply that RANDOM repeated insertion has worst-case
sifting on every element.

### Peek and Restored Priority Pairs

Manual peek means are **1.173–1.203 ns/op** across these cells, compatible with
a constant-time source read. Similar means at two sizes are not an asymptotic
proof. JDK peek is about **1.444–1.549 ns/op** in three cells, while RANDOM at
100 is **5.012 +/- 11.922 ns/op**. That cell's error is about **237.9%** of its
mean, so a precise manual/JDK peek ranking or ratio would be misleading.

For that JDK cell, the printed measurement-average range is **1.460–27.167
ns/op**, and derived fork means are **8.396** and **1.629 ns/op**. The supplied
CI is **[`? 0`, 16.934]**; the lower marker is retained as text rather than
replaced with zero or a reconstructed negative number. No individual-operation
latency or numerical CI lower bound is recovered from that marker.

Manual pair means range **19.979–25.243 ns/op**, and JDK pair means
**40.920–59.339 ns/op**. This describes the paired workload; it does not isolate
poll, restoration, allocation or boxing costs. Both size-100 manual pair cells
have errors above 50% of their means. Primitive versus boxed storage, OptionalInt
access, comparator/library code and repeated restoration of one minimum prevent
an algorithm-only or universal production-performance conclusion. No profiler
was supplied to attribute differences to cache, GC, allocation or generated code.

## Supplied Statistics and Derived Fork Means

All numeric values below are **ns/op**. Min/max describe measurement-iteration
averages, not individual operation latencies. Standard deviations are distinct
from JMH errors. Exact printed bounds are preserved even when rounding differs
by 0.001 from mean/error arithmetic. The one nonnumeric marker stays `? 0`.
Fork means are derived from five rounded iteration averages, rounded to three
decimals; they are not additional independent experimental runs.

| Method | Distribution | Size | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|
| `bottomUpBuild` | RANDOM | 100 | 204.998 | 290.982 | 27.817 | 204.661 | 288.771 | 266.466 | 226.967 |
| `bottomUpBuild` | RANDOM | 1000 | 2119.834 | 2542.919 | 151.469 | 2142.967 | 2600.966 | 2423.133 | 2320.800 |
| `bottomUpBuild` | REVERSE_SORTED | 100 | 211.076 | 282.098 | 22.797 | 206.089 | 275.020 | 229.441 | 251.667 |
| `bottomUpBuild` | REVERSE_SORTED | 1000 | 2061.789 | 2733.714 | 201.192 | 2050.803 | 2659.150 | 2284.117 | 2425.836 |
| `javaPeek` | RANDOM | 100 | 1.460 | 27.167 | 7.885 | `? 0` | 16.934 | 8.396 | 1.629 |
| `javaPeek` | RANDOM | 1000 | 1.279 | 1.822 | 0.175 | 1.180 | 1.709 | 1.415 | 1.473 |
| `javaPeek` | REVERSE_SORTED | 100 | 1.281 | 2.107 | 0.242 | 1.100 | 1.830 | 1.405 | 1.526 |
| `javaPeek` | REVERSE_SORTED | 1000 | 1.276 | 2.528 | 0.380 | 0.975 | 2.123 | 1.377 | 1.721 |
| `javaPollRestore` | RANDOM | 100 | 35.174 | 58.193 | 6.607 | 30.931 | 50.910 | 39.640 | 42.200 |
| `javaPollRestore` | RANDOM | 1000 | 50.053 | 57.429 | 3.415 | 48.980 | 59.307 | 54.126 | 54.160 |
| `javaPollRestore` | REVERSE_SORTED | 100 | 38.968 | 46.152 | 2.984 | 37.938 | 46.961 | 42.505 | 42.393 |
| `javaPollRestore` | REVERSE_SORTED | 1000 | 52.537 | 70.600 | 5.586 | 50.894 | 67.784 | 58.883 | 59.795 |
| `manualPeek` | RANDOM | 100 | 1.070 | 1.252 | 0.077 | 1.056 | 1.290 | 1.173 | 1.172 |
| `manualPeek` | RANDOM | 1000 | 1.070 | 1.265 | 0.088 | 1.040 | 1.306 | 1.176 | 1.170 |
| `manualPeek` | REVERSE_SORTED | 100 | 1.076 | 1.423 | 0.118 | 1.025 | 1.381 | 1.174 | 1.232 |
| `manualPeek` | REVERSE_SORTED | 1000 | 1.079 | 1.280 | 0.080 | 1.062 | 1.303 | 1.176 | 1.189 |
| `manualPollRestore` | RANDOM | 100 | 15.491 | 37.882 | 8.823 | 9.306 | 35.983 | 17.064 | 28.226 |
| `manualPollRestore` | RANDOM | 1000 | 22.493 | 26.884 | 1.494 | 22.985 | 27.501 | 26.142 | 24.345 |
| `manualPollRestore` | REVERSE_SORTED | 100 | 15.137 | 42.767 | 8.153 | 7.653 | 32.306 | 23.550 | 16.409 |
| `manualPollRestore` | REVERSE_SORTED | 1000 | 21.489 | 25.746 | 1.722 | 20.869 | 26.076 | 23.463 | 23.481 |
| `repeatedOfferBuild` | RANDOM | 100 | 232.118 | 277.942 | 17.653 | 226.257 | 279.636 | 255.509 | 250.383 |
| `repeatedOfferBuild` | RANDOM | 1000 | 2697.977 | 3083.638 | 163.500 | 2672.296 | 3166.673 | 2940.424 | 2898.545 |
| `repeatedOfferBuild` | REVERSE_SORTED | 100 | 594.132 | 1460.611 | 251.025 | 394.665 | 1153.692 | 668.441 | 879.916 |
| `repeatedOfferBuild` | REVERSE_SORTED | 1000 | 11176.840 | 16254.232 | 1854.108 | 11193.498 | 16799.793 | 14351.482 | 13641.811 |

Every pooled mean matches the ten rounded measurement averages within 0.001
ns/op. All minima/maxima match their printed observations; every final table
row agrees with its result block and complete configuration header. Numerical
confidence bounds agree with rounded mean/error arithmetic within 0.001, except
that no numeric comparison is imposed on the literal `? 0` marker.

## Complete Measurement-Iteration Evidence

These **240 values** are measured iteration averages in **ns/op**. Each timed
iteration contains many invocations; they are not operation-latency percentiles.
No observations were removed or replaced.

| Method | Distribution | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---|---:|---:|---:|---:|---:|---:|---:|
| `bottomUpBuild` | RANDOM | 100 | 1 | 232.627 | 290.982 | 277.121 | 272.555 | 259.044 |
| `bottomUpBuild` | RANDOM | 100 | 2 | 204.998 | 214.727 | 245.915 | 235.986 | 233.209 |
| `bottomUpBuild` | RANDOM | 1000 | 1 | 2255.108 | 2268.958 | 2542.919 | 2530.177 | 2518.503 |
| `bottomUpBuild` | RANDOM | 1000 | 2 | 2119.834 | 2196.837 | 2391.803 | 2461.291 | 2434.235 |
| `bottomUpBuild` | REVERSE_SORTED | 100 | 1 | 211.076 | 212.670 | 241.981 | 241.311 | 240.169 |
| `bottomUpBuild` | REVERSE_SORTED | 100 | 2 | 215.876 | 243.927 | 282.098 | 263.595 | 252.840 |
| `bottomUpBuild` | REVERSE_SORTED | 1000 | 1 | 2511.874 | 2061.789 | 2176.578 | 2366.325 | 2304.018 |
| `bottomUpBuild` | REVERSE_SORTED | 1000 | 2 | 2224.481 | 2206.934 | 2434.559 | 2529.493 | 2733.714 |
| `javaPeek` | RANDOM | 100 | 1 | 3.386 | 4.514 | 4.813 | 2.102 | 27.167 |
| `javaPeek` | RANDOM | 100 | 2 | 1.658 | 1.826 | 1.734 | 1.465 | 1.460 |
| `javaPeek` | RANDOM | 1000 | 1 | 1.326 | 1.282 | 1.402 | 1.484 | 1.579 |
| `javaPeek` | RANDOM | 1000 | 2 | 1.279 | 1.282 | 1.413 | 1.822 | 1.571 |
| `javaPeek` | REVERSE_SORTED | 100 | 1 | 1.305 | 1.315 | 1.429 | 1.499 | 1.476 |
| `javaPeek` | REVERSE_SORTED | 100 | 2 | 1.281 | 1.298 | 1.454 | 1.489 | 2.107 |
| `javaPeek` | REVERSE_SORTED | 1000 | 1 | 1.276 | 1.283 | 1.397 | 1.478 | 1.452 |
| `javaPeek` | REVERSE_SORTED | 1000 | 2 | 1.290 | 1.364 | 1.679 | 2.528 | 1.745 |
| `javaPollRestore` | RANDOM | 100 | 1 | 40.398 | 35.629 | 40.725 | 40.307 | 41.142 |
| `javaPollRestore` | RANDOM | 100 | 2 | 35.174 | 35.252 | 40.672 | 58.193 | 41.708 |
| `javaPollRestore` | RANDOM | 1000 | 1 | 50.053 | 50.149 | 55.767 | 57.429 | 57.234 |
| `javaPollRestore` | RANDOM | 1000 | 2 | 50.569 | 50.278 | 55.303 | 57.344 | 57.306 |
| `javaPollRestore` | REVERSE_SORTED | 100 | 1 | 39.541 | 39.125 | 43.168 | 45.499 | 45.194 |
| `javaPollRestore` | REVERSE_SORTED | 100 | 2 | 39.007 | 38.968 | 43.119 | 46.152 | 44.718 |
| `javaPollRestore` | REVERSE_SORTED | 1000 | 1 | 54.939 | 54.113 | 60.542 | 62.821 | 62.002 |
| `javaPollRestore` | REVERSE_SORTED | 1000 | 2 | 53.954 | 52.537 | 59.172 | 62.713 | 70.600 |
| `manualPeek` | RANDOM | 100 | 1 | 1.087 | 1.086 | 1.213 | 1.245 | 1.233 |
| `manualPeek` | RANDOM | 100 | 2 | 1.070 | 1.092 | 1.221 | 1.252 | 1.227 |
| `manualPeek` | RANDOM | 1000 | 1 | 1.080 | 1.071 | 1.211 | 1.257 | 1.261 |
| `manualPeek` | RANDOM | 1000 | 2 | 1.076 | 1.070 | 1.193 | 1.246 | 1.265 |
| `manualPeek` | REVERSE_SORTED | 100 | 1 | 1.082 | 1.083 | 1.200 | 1.236 | 1.268 |
| `manualPeek` | REVERSE_SORTED | 100 | 2 | 1.076 | 1.095 | 1.423 | 1.315 | 1.250 |
| `manualPeek` | REVERSE_SORTED | 1000 | 1 | 1.082 | 1.094 | 1.225 | 1.266 | 1.212 |
| `manualPeek` | REVERSE_SORTED | 1000 | 2 | 1.079 | 1.123 | 1.209 | 1.254 | 1.280 |
| `manualPollRestore` | RANDOM | 100 | 1 | 17.122 | 15.491 | 16.674 | 18.518 | 17.515 |
| `manualPollRestore` | RANDOM | 100 | 2 | 20.925 | 15.865 | 29.061 | 37.397 | 37.882 |
| `manualPollRestore` | RANDOM | 1000 | 1 | 24.627 | 26.454 | 26.192 | 26.552 | 26.884 |
| `manualPollRestore` | RANDOM | 1000 | 2 | 23.123 | 22.493 | 24.633 | 25.685 | 25.789 |
| `manualPollRestore` | REVERSE_SORTED | 100 | 1 | 42.767 | 19.910 | 19.035 | 18.677 | 17.360 |
| `manualPollRestore` | REVERSE_SORTED | 100 | 2 | 15.152 | 15.137 | 16.691 | 17.492 | 17.572 |
| `manualPollRestore` | REVERSE_SORTED | 1000 | 1 | 21.489 | 21.546 | 23.712 | 24.824 | 25.746 |
| `manualPollRestore` | REVERSE_SORTED | 1000 | 2 | 21.677 | 21.636 | 23.928 | 25.129 | 25.036 |
| `repeatedOfferBuild` | RANDOM | 100 | 1 | 233.448 | 232.118 | 277.942 | 267.546 | 266.489 |
| `repeatedOfferBuild` | RANDOM | 100 | 2 | 234.970 | 232.710 | 257.608 | 263.325 | 263.304 |
| `repeatedOfferBuild` | RANDOM | 1000 | 1 | 2773.992 | 2742.875 | 3058.782 | 3042.835 | 3083.638 |
| `repeatedOfferBuild` | RANDOM | 1000 | 2 | 2719.883 | 2697.977 | 2970.875 | 3052.352 | 3051.639 |
| `repeatedOfferBuild` | REVERSE_SORTED | 100 | 1 | 764.202 | 704.710 | 594.132 | 652.463 | 626.700 |
| `repeatedOfferBuild` | REVERSE_SORTED | 100 | 2 | 706.774 | 704.709 | 842.605 | 1460.611 | 684.882 |
| `repeatedOfferBuild` | REVERSE_SORTED | 1000 | 1 | 15035.716 | 14415.207 | 14714.511 | 11337.742 | 16254.232 |
| `repeatedOfferBuild` | REVERSE_SORTED | 1000 | 2 | 15791.010 | 15678.107 | 12621.040 | 12942.056 | 11176.840 |

## Complete Warmup Evidence

These **144 values**, in **ns/op**, remain separate and are excluded from the
reported measurement means and derived fork means.

| Method | Distribution | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---|---:|---:|---:|---:|---:|
| `bottomUpBuild` | RANDOM | 100 | 1 | 288.645 | 886.147 | 255.797 |
| `bottomUpBuild` | RANDOM | 100 | 2 | 243.484 | 270.368 | 207.784 |
| `bottomUpBuild` | RANDOM | 1000 | 1 | 2350.712 | 2268.227 | 2253.374 |
| `bottomUpBuild` | RANDOM | 1000 | 2 | 2336.471 | 2235.635 | 2111.023 |
| `bottomUpBuild` | REVERSE_SORTED | 100 | 1 | 204.103 | 194.877 | 215.129 |
| `bottomUpBuild` | REVERSE_SORTED | 100 | 2 | 219.687 | 193.160 | 215.115 |
| `bottomUpBuild` | REVERSE_SORTED | 1000 | 1 | 2217.793 | 2072.534 | 2076.445 |
| `bottomUpBuild` | REVERSE_SORTED | 1000 | 2 | 2670.066 | 2080.665 | 2196.453 |
| `javaPeek` | RANDOM | 100 | 1 | 1.680 | 2.862 | 2.989 |
| `javaPeek` | RANDOM | 100 | 2 | 2.487 | 1.960 | 1.754 |
| `javaPeek` | RANDOM | 1000 | 1 | 1.296 | 1.313 | 1.335 |
| `javaPeek` | RANDOM | 1000 | 2 | 1.305 | 1.306 | 1.292 |
| `javaPeek` | REVERSE_SORTED | 100 | 1 | 1.293 | 1.301 | 1.284 |
| `javaPeek` | REVERSE_SORTED | 100 | 2 | 1.287 | 1.308 | 1.279 |
| `javaPeek` | REVERSE_SORTED | 1000 | 1 | 1.290 | 1.293 | 1.274 |
| `javaPeek` | REVERSE_SORTED | 1000 | 2 | 1.298 | 1.393 | 1.388 |
| `javaPollRestore` | RANDOM | 100 | 1 | 30.772 | 30.170 | 38.554 |
| `javaPollRestore` | RANDOM | 100 | 2 | 30.803 | 30.649 | 35.304 |
| `javaPollRestore` | RANDOM | 1000 | 1 | 47.377 | 45.368 | 50.827 |
| `javaPollRestore` | RANDOM | 1000 | 2 | 47.752 | 46.410 | 50.195 |
| `javaPollRestore` | REVERSE_SORTED | 100 | 1 | 36.043 | 34.059 | 39.381 |
| `javaPollRestore` | REVERSE_SORTED | 100 | 2 | 35.648 | 34.452 | 39.352 |
| `javaPollRestore` | REVERSE_SORTED | 1000 | 1 | 48.161 | 51.345 | 55.094 |
| `javaPollRestore` | REVERSE_SORTED | 1000 | 2 | 51.032 | 50.559 | 52.635 |
| `manualPeek` | RANDOM | 100 | 1 | 1.166 | 1.107 | 1.090 |
| `manualPeek` | RANDOM | 100 | 2 | 1.106 | 1.109 | 1.082 |
| `manualPeek` | RANDOM | 1000 | 1 | 1.106 | 1.111 | 1.091 |
| `manualPeek` | RANDOM | 1000 | 2 | 1.103 | 1.105 | 1.081 |
| `manualPeek` | REVERSE_SORTED | 100 | 1 | 1.110 | 1.099 | 1.095 |
| `manualPeek` | REVERSE_SORTED | 100 | 2 | 1.116 | 1.109 | 1.075 |
| `manualPeek` | REVERSE_SORTED | 1000 | 1 | 1.110 | 1.102 | 1.080 |
| `manualPeek` | REVERSE_SORTED | 1000 | 2 | 1.108 | 1.107 | 1.089 |
| `manualPollRestore` | RANDOM | 100 | 1 | 15.990 | 22.761 | 15.789 |
| `manualPollRestore` | RANDOM | 100 | 2 | 15.448 | 16.173 | 19.826 |
| `manualPollRestore` | RANDOM | 1000 | 1 | 50.354 | 28.731 | 26.299 |
| `manualPollRestore` | RANDOM | 1000 | 2 | 23.598 | 23.830 | 31.804 |
| `manualPollRestore` | REVERSE_SORTED | 100 | 1 | 15.666 | 17.360 | 15.173 |
| `manualPollRestore` | REVERSE_SORTED | 100 | 2 | 15.350 | 16.322 | 15.747 |
| `manualPollRestore` | REVERSE_SORTED | 1000 | 1 | 22.936 | 22.729 | 21.676 |
| `manualPollRestore` | REVERSE_SORTED | 1000 | 2 | 22.975 | 22.912 | 21.615 |
| `repeatedOfferBuild` | RANDOM | 100 | 1 | 271.810 | 281.394 | 234.376 |
| `repeatedOfferBuild` | RANDOM | 100 | 2 | 268.055 | 281.450 | 237.106 |
| `repeatedOfferBuild` | RANDOM | 1000 | 1 | 3865.431 | 3031.566 | 2747.421 |
| `repeatedOfferBuild` | RANDOM | 1000 | 2 | 3100.883 | 2962.038 | 2753.184 |
| `repeatedOfferBuild` | REVERSE_SORTED | 100 | 1 | 579.386 | 566.729 | 564.442 |
| `repeatedOfferBuild` | REVERSE_SORTED | 100 | 2 | 833.586 | 781.500 | 704.039 |
| `repeatedOfferBuild` | REVERSE_SORTED | 1000 | 1 | 9881.698 | 9911.556 | 11422.523 |
| `repeatedOfferBuild` | REVERSE_SORTED | 1000 | 2 | 13746.310 | 13208.547 | 16765.297 |

## Research Limits and Module Handoff

- **5 of 24 errors reach at least 25% of their means**, with one greater than
  its mean. The largest is about 237.9%; one printed CI lower bound is a
  nonnumeric `? 0` marker. No outlier removal, significance/equivalence test or
  precision beyond the supplied display is claimed.
- Coverage is **24 of 36 heap defaults**. Combined with the separate 24-cell
  lookup run, the initial evidence covers 48 of 72 module defaults. Size 10,000
  remains unmeasured in both families. Module timing samples are not pooled.
- The author supplied checkout SHA before launch. No exact execution timestamp,
  jar hash, CPU model, power configuration or profiler metrics were provided;
  the initial pasted command line is truncated. The reported source is kept
  distinct from subsequent documentation commits.
- Two sizes and one fixed seed do not establish scaling exponents or a broad
  distributional result. REVERSE_SORTED is a deliberate adverse insertion
  sequence. Restoring the same minimum is a restricted mutation workload.
- Build means include allocation/copy/population; pairs and peek exclude trial
  setup and growth. Pair means are not isolated removal latency. No source
  optimisation, allocation rate, memory footprint, cache cause, GC cause or
  thread-safety claim follows from these scores.
- Correctness remains the separate **524-test verification**, including **59
  Trees tests**, invariants and analytical arguments. This documentation update
  changes no executable Java and executes no new performance run. Smoke
  timings are not substituted for author measurements.

Both JMH templates now contain preliminary Result/Interpretation supported by
their respective complete initial subsets. Documentation and the project audit
link both reports, sources, actual JVM settings, uncertainties and preserved
iteration evidence. This completes the initial traceability step; it does not
claim exhaustive functional coverage or performance validation of every input.

Separate future experiments could investigate growth, varied priority churn,
more random seeds, additional sizes or justified profiler questions. Keep each
run's own source/runtime, full settings, means/errors and completion evidence;
do not infer missing larger-size scores from these results. No repeat of the
lookup run or new measurement is required merely to record this supplied output.

## Source Integrity

SHA-256 of the original supplied attachment bytes, including line endings:

`c70eacd69634c19e2cca109cc77f3a9a4dbb0261f1061064b3188286bfabec5c`

This identifies the received source. Decimal normalisation and Markdown layout
retain every printed observation and the literal CI marker. The text attachment
itself is not copied into the repository.
