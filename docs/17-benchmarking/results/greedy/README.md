# Greedy Algorithms: First Reported Results

The author supplied one complete execution of **all 40 current default
configurations**: 16 activity-selection, 16 Huffman-cost and eight minimum-room
assignment cells. This report preserves **40 means/errors**, **400 measurement-
iteration averages** and **240 warmup averages**. Completion time is **5 minutes
51 seconds**. All three templates now use this output in Result/Interpretation.

This completes the initial selected grid, not all valid inputs or every Greedy
API. Huffman codeword construction, bitstream compression and CoinChange are
not measured by these templates. Prior modules retain their separate source,
runtime, observations and limitations. Short smoke scores remain excluded.

## Provenance and Runtime

The author separately supplied source SHA
**`674ab35b7d82f1a9ab7fbab63fa1a9e80253965c`**, the merge of Greedy PR #86.
It matches the reviewed executable source. This is author-reported checkout
attribution supplied with the output, not a revision embedded by JMH or a
supplied jar hash. The text does not establish when the SHA was captured.
Later result-documentation commits are distinct from this attribution.

| Property | Supplied evidence |
|---|---|
| Reporting date | 2026-10-26, America/Buenos_Aires; execution timestamp not supplied |
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
| Confidence output | 99.9% intervals with the printed normal-distribution assumption |
| Full command | Not captured; attachment begins with the first JMH header |
| CPU, power settings, profiler metrics, jar hash | Not supplied |
| Operating system | Windows executable path; Windows 11 amd64 reported separately during setup |

The [guide](../../../13-greedy-algorithms/implementation-and-benchmark-guide.md)
instructed this combined invocation:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*greedy.*" -foe true
```

This is the instructed protocol, not a recovered command line. Headers, forks,
parameters and completion match the full default grid. Activity/room sizes are
100/10,000; Huffman sizes are 100/1,000. The mixed final table has both `shape`
and `distribution`: `N/A` means the column is inapplicable to that class, not
zero or a missing tested parameter. Unlike objectives and size grids are not
pooled into one comparison or scalability model.

Decimal commas become dots, retaining all three printed decimals. These are
rounded console iteration averages, not full-precision JMH arrays or individual
invocation latency distributions. Warmup remains separate. No fork/observation
is removed as an outlier. Printed CI endpoints are preserved, including 0.001
rounding differences from arithmetic on printed mean/error. Rounding also
limits relative precision for small microsecond scores.

## Contracts, Fixtures and Timed Work

The guide links specifications, pseudocode, traces, safe-choice/lower-bound
proofs, work/state models and independent tests. All fixtures use trial seed
**20261026** outside timing. Returned values are consumed by JMH; no generated-
code inspection is claimed merely because the harness ran.

| Template | Timed workflow | Important boundary |
|---|---|---|
| [ActivitySelectionBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/ActivitySelectionBenchmark.java) | Full sortAndSelect on shuffled input versus validated scanPresorted on an ordered list; snapshots and immutable selected plans included | Order preparation for scanPresorted is outside timing; it is a different caller precondition, not free end-to-end sorting |
| [HuffmanCostBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/HuffmanCostBenchmark.java) | Full validation and exact BigInteger cost; heap boxing/offers/removals versus private clone/sort/two-queue arrays | Same optimum cost, no symbol codewords, encoding/decoding or compressed-file measurement |
| [IntervalPartitioningBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/IntervalPartitioningBenchmark.java) | Full snapshot, ordering, room heap/records and immutable original-index assignment | Assigns every interval and minimises rooms; activity selection may discard intervals and solves another objective |

Intervals are nonempty, half-open and may touch. DISJOINT uses [3i,3i+1),
TOUCHING [i,i+1); OVERLAPPING has starts 0..99 and finishes 101..200, sharing
time 100. RANDOM has starts 0..4n-1 and durations 1..100. All lists are shuffled
in trial setup. Huffman UNIFORM has all weights 10, RANDOM weights 1..1,000,
SKEWED one MAX_INT and remaining ones, and TWO_LEVEL alternates 1/1,000 before
shuffling. These change values, overlap/ties and outputs, not one isolated
hardware factor. Only one seed is represented.

All operations preserve trial input. The selector's plan is deterministic by
finish then start; the presorted API validates that full order. Room ids retain
original input positions and reuse earliest finish, then room id. Huffman
subtree weights fit long under the positive-int/count domain; weighted cost is
kept exact with BigInteger. These contracts remain distinct from timing claims.

## Complete Means and Errors

Every cell is **mean +/- JMH-reported error in us/op**, `avgt`, `Cnt = 10`.

### ActivitySelectionBenchmark

| Method | Shape | Size 100 | Size 10,000 |
|---|---|---:|---:|
| `scanPresorted` | DISJOINT | 0.740 +/- 0.041 | 94.215 +/- 1.244 |
| `scanPresorted` | TOUCHING | 0.731 +/- 0.016 | 94.323 +/- 1.445 |
| `scanPresorted` | OVERLAPPING | 0.385 +/- 0.029 | 67.833 +/- 3.232 |
| `scanPresorted` | RANDOM | 0.432 +/- 0.007 | 56.152 +/- 1.875 |
| `sortAndSelect` | DISJOINT | 4.138 +/- 0.147 | 1610.244 +/- 431.882 |
| `sortAndSelect` | TOUCHING | 5.744 +/- 2.790 | 1830.551 +/- 595.098 |
| `sortAndSelect` | OVERLAPPING | 7.668 +/- 2.546 | 2683.156 +/- 812.107 |
| `sortAndSelect` | RANDOM | 7.998 +/- 2.358 | 2587.016 +/- 1158.367 |

### HuffmanCostBenchmark

| Method | Distribution | Size 100 | Size 1,000 |
|---|---|---:|---:|
| `heapCost` | UNIFORM | 5.598 +/- 1.974 | 103.031 +/- 44.727 |
| `heapCost` | RANDOM | 5.807 +/- 0.266 | 149.045 +/- 1.551 |
| `heapCost` | SKEWED | 4.185 +/- 0.150 | 68.620 +/- 0.846 |
| `heapCost` | TWO_LEVEL | 4.658 +/- 0.050 | 106.332 +/- 1.777 |
| `twoQueueCost` | UNIFORM | 2.609 +/- 1.165 | 22.671 +/- 0.483 |
| `twoQueueCost` | RANDOM | 2.917 +/- 0.338 | 32.205 +/- 1.556 |
| `twoQueueCost` | SKEWED | 1.429 +/- 0.023 | 14.491 +/- 0.312 |
| `twoQueueCost` | TWO_LEVEL | 1.826 +/- 0.111 | 19.972 +/- 4.140 |

### IntervalPartitioningBenchmark

| Method | Shape | Size 100 | Size 10,000 |
|---|---|---:|---:|
| `assignRooms` | DISJOINT | 6.662 +/- 0.208 | 1901.335 +/- 19.514 |
| `assignRooms` | TOUCHING | 6.481 +/- 0.103 | 1894.487 +/- 45.547 |
| `assignRooms` | OVERLAPPING | 10.383 +/- 1.029 | 2823.808 +/- 39.895 |
| `assignRooms` | RANDOM | 12.932 +/- 0.677 | 3069.430 +/- 43.918 |

## Interpretation: Selection With Different Preparation Preconditions

scanPresorted has lower reported means in every matching size/shape cell. For
DISJOINT size 10,000, it reports **94.215 +/- 1.244** versus sortAndSelect
**1610.244 +/- 431.882 us/op**. For RANDOM size 10,000, means are
**56.152 +/- 1.875** versus **2587.016 +/- 1158.367 us/op**.

The source explains a material difference: the presorted method receives order
prepared in trial setup, whereas the other call sorts its private snapshot
inside timing. Both still validate/snapshot and construct an immutable plan.
These are complete APIs with different caller preconditions. Their difference
is not an isolated sorting time; subtracting means would conflate allocation,
validation, order, output and runtime effects. A caller starting with unsorted
input must account for preparing and maintaining order elsewhere.

The selected-output populations are analytically known for three shapes:

| Shape | Chosen intervals k | Minimum rooms r if all intervals must be assigned |
|---|---:|---:|
| DISJOINT | n | 1 |
| TOUCHING | n | 1 |
| OVERLAPPING | 1 | n |
| RANDOM | Not printed by this run | Not printed by this run |

RANDOM's optimal counts were functionally reference-checked before publication,
but the author timing output does not contain them; no exact values are
invented here. All methods visit the full input; changing output size and tie/
branch behaviour can change work even at fixed n. Output count alone is not a
timing predictor: RANDOM presorted size 10,000 has a lower mean than OVERLAPPING
(**67.833 +/- 3.232 us/op**), which selects only one interval. The run does not
isolate the cause or establish cache, branch-prediction, allocation or GC effects.

Seven of eight sortAndSelect cells have error at least 25% of the mean; none
of the eight scanPresorted cells does. The widest relative error is TOUCHING
size 100 (**5.744 +/- 2.790 us/op**, about **48.6%**). TOUCHING size 10,000 has
derived fork means **1822.968/1838.134 us/op**, yet pooled error is **595.098**:
similar fork means do not imply low variation within their measured iterations.
All those observations stay intact. No formal significance/equivalence test,
general speedup ratio or fitted complexity exponent is claimed.

## Interpretation: Exact Huffman Cost, Not Full Compression

twoQueueCost has lower means on all eight matching fixtures. On RANDOM at
1,000 frequencies, it reports **32.205 +/- 1.556** versus heapCost
**149.045 +/- 1.551 us/op**; on SKEWED, **14.491 +/- 0.312** versus
**68.620 +/- 0.846 us/op**. These comparisons concern exact cost-only public
calls, including their differing preparation/state, not codebook generation
or compression throughput.

Both variants carry out n-1 merges and exact BigInteger cost accumulation.
The heap variant inserts leaves individually and boxes queue weights; the
other clones/sorts leaves and uses monotone queue cursors. The latter has
linear merging after its included sorting. The source ledger is:

| Source quantity, n>=2 | n=100 | n=1,000 |
|---|---:|---:|
| Merges / cost-addition expressions | 99 | 999 |
| Heap offers including leaves, 2n-1 | 199 | 1,999 |
| Heap removals / two-queue front selections, 2(n-1) | 198 | 1,998 |
| Private leaf-array length in twoQueueCost | 100 | 1,000 |
| Internal merge-array length in twoQueueCost | 99 | 999 |

These counts are analytical source work, not queue comparator counts, actual
heap allocations after optimisation, bytes, GC events or profiler measurements.
No subtraction isolates heap, sorting, boxing or BigInteger costs. Equal big-O
upper bounds do not require equal elapsed means, and adaptive sorting need not
execute identical work on every value distribution.

For UNIFORM size 100, heap **5.598 +/- 1.974** and two-queue **2.609 +/- 1.165
us/op** intervals overlap; those point means do not establish a precise winner.
Three Huffman errors reach 25%, all UNIFORM: heap at both sizes (about **35.3%**
and **43.4%**) and two-queue at 100 (**44.7%**). This limits exact ratio claims
even though the two-queue mean is lower throughout the selected grid.

As an additional analytical check, expected optimum cost can be derived without
using elapsed times. For m unit weights let h=ceil(log2(m)); their full-tree
weighted path cost is U(m)=m*(h+1)-2^h for m>=2, with U(1)=0. Then:

| Fixture | Expected mathematical cost at n=100 | At n=1,000 |
|---|---:|---:|
| UNIFORM, weight 10 | 6,720 | 99,760 |
| SKEWED, one MAX_INT and n-1 ones | 2,147,484,410 | 2,147,494,611 |

The SKEWED value follows because the dominant symbol stays separate until the
final merge: MAX_INT+(n-1)+U(n-1). These are **derived expected result values**,
not output fields supplied by JMH and not new performance measurements.
RANDOM/TWO_LEVEL exact costs are not reconstructed from time. Full codeword
construction, output-character length L, singleton/empty policy and greedy
coin-change behaviour remain mathematical/functional evidence outside this grid.

## Interpretation: Assigning Every Interval to Minimum Rooms

At size 10,000, DISJOINT and TOUCHING full assignments report
**1901.335 +/- 19.514** and **1894.487 +/- 45.547 us/op**, respectively.
Their intervals overlap; the data does not demonstrate equivalence or a precise
ordering. OVERLAPPING reports **2823.808 +/- 39.895**, and RANDOM
**3069.430 +/- 43.918 us/op**. Every room-assignment error is below 25% of its
mean; no systematic-error or cause-isolation guarantee follows.

The heap maintains one latest finish per allocated room, including idle rooms.
For n intervals with optimum r rooms there are **n peeks**, **n offers**,
**n-r removals for reuse**, and r new-room allocations. It does not remove every
expired interval as an active-event heap would. The known source cases are:

| Shape / n | Rooms r | Offers | Reuse removals |
|---|---:|---:|---:|
| DISJOINT or TOUCHING / 100 | 1 | 100 | 99 |
| DISJOINT or TOUCHING / 10,000 | 1 | 10,000 | 9,999 |
| OVERLAPPING / 100 | 100 | 100 | 0 |
| OVERLAPPING / 10,000 | 10,000 | 10,000 | 0 |

Fewer removals do not imply lower elapsed cost: maximum-overlap fixtures retain
a larger heap and different ordering/tie patterns. RANDOM has a greater mean
than OVERLAPPING at these sizes, so peak room count alone does not explain time.
Sorting, snapshot/entry/room/result creation, heap maintenance and input patterns
are all included. The run does not isolate their shares or identify a CPU/cache
cause. A comparator event count cannot be inferred from the peek/offer ledger.

This objective places every input interval and returns original-index room ids.
Activity selection may omit overlaps and returns a compatible subset. Their
means must not rank them as alternatives for the same output. No alternative
room algorithm, scalar minimum-count-only baseline or parallel scheduler was
timed here. Two sizes do not establish a scalability exponent or universal bound
from measurement; the separate lower-bound proof establishes optimal room count.

## Uncertainty Review

**Ten of 40 cells** have error at least 25% of their means: seven activity
sortAndSelect and three UNIFORM Huffman cells. The threshold is descriptive,
not a significance/pass-fail rule. Low reported error does not exclude systematic
effects. Fork summaries use rounded iteration means and add no new observations.

| Template / method | Pattern | Size | Error / mean | Fork 1 mean (us/op) | Fork 2 mean (us/op) |
|---|---|---:|---:|---:|---:|
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 10000 | 26.8% | 1405.430 | 1815.059 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 100 | 48.6% | 6.247 | 5.240 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 10000 | 32.5% | 1822.968 | 1838.134 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 100 | 33.2% | 7.458 | 7.877 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 10000 | 30.3% | 2614.895 | 2751.417 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 100 | 29.5% | 8.024 | 7.972 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 10000 | 44.8% | 2720.951 | 2453.081 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 100 | 35.3% | 5.743 | 5.453 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 1000 | 43.4% | 105.583 | 100.480 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 100 | 44.7% | 2.263 | 2.955 |

## Statistics and Derived Fork Means

All entries are **us/op**. Min/max are measurement-iteration averages, not
invocation-latency extremes. Standard deviation is distinct from JMH error.
Each derived fork mean uses five rounded measured averages, rounded to three
decimals. CI endpoints preserve JMH's printed 99.9% model output/assumption.

| Template / method | Pattern | Size | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 100 | 0.724 | 0.813 | 0.027 | 0.699 | 0.781 | 0.744 | 0.735 |
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 10000 | 93.247 | 96.167 | 0.823 | 92.971 | 95.459 | 94.646 | 93.784 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 100 | 0.723 | 0.757 | 0.010 | 0.715 | 0.746 | 0.732 | 0.729 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 10000 | 93.179 | 95.734 | 0.956 | 92.878 | 95.769 | 94.831 | 93.816 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 100 | 0.364 | 0.413 | 0.019 | 0.356 | 0.414 | 0.367 | 0.402 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 10000 | 65.302 | 72.715 | 2.138 | 64.601 | 71.065 | 66.268 | 69.398 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 100 | 0.425 | 0.440 | 0.005 | 0.425 | 0.439 | 0.435 | 0.429 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 10000 | 52.991 | 57.609 | 1.240 | 54.278 | 58.027 | 56.761 | 55.544 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 100 | 4.070 | 4.405 | 0.097 | 3.991 | 4.284 | 4.164 | 4.111 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 10000 | 1393.600 | 2099.357 | 285.663 | 1178.362 | 2042.126 | 1405.430 | 1815.059 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 100 | 4.064 | 9.998 | 1.846 | 2.953 | 8.534 | 6.247 | 5.240 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 10000 | 1391.935 | 2429.827 | 393.621 | 1235.452 | 2425.649 | 1822.968 | 1838.134 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 100 | 5.850 | 9.833 | 1.684 | 5.121 | 10.214 | 7.458 | 7.877 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 10000 | 2070.614 | 3518.521 | 537.158 | 1871.049 | 3495.263 | 2614.895 | 2751.417 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 100 | 6.014 | 9.978 | 1.560 | 5.640 | 10.356 | 8.024 | 7.972 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 10000 | 1769.543 | 3700.757 | 766.188 | 1428.649 | 3745.382 | 2720.951 | 2453.081 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 100 | 4.144 | 7.667 | 1.306 | 3.624 | 7.572 | 5.743 | 5.453 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 1000 | 75.155 | 152.008 | 29.584 | 58.304 | 147.758 | 105.583 | 100.480 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 100 | 5.642 | 6.247 | 0.176 | 5.541 | 6.072 | 5.791 | 5.823 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 1000 | 147.424 | 150.865 | 1.026 | 147.495 | 150.596 | 148.838 | 149.253 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 100 | 4.109 | 4.450 | 0.099 | 4.035 | 4.335 | 4.144 | 4.226 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 1000 | 67.949 | 69.984 | 0.560 | 67.774 | 69.466 | 68.415 | 68.825 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 100 | 4.615 | 4.707 | 0.033 | 4.608 | 4.708 | 4.644 | 4.672 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 1000 | 104.913 | 108.986 | 1.175 | 104.555 | 108.109 | 106.896 | 105.768 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 100 | 2.211 | 4.668 | 0.771 | 1.444 | 3.775 | 2.263 | 2.955 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 1000 | 22.401 | 23.479 | 0.320 | 22.188 | 23.155 | 22.790 | 22.553 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 100 | 2.759 | 3.466 | 0.224 | 2.578 | 3.255 | 2.776 | 3.057 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 1000 | 31.552 | 35.040 | 1.029 | 30.649 | 33.761 | 31.674 | 32.736 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 100 | 1.409 | 1.469 | 0.015 | 1.406 | 1.452 | 1.435 | 1.424 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 1000 | 14.216 | 14.864 | 0.206 | 14.179 | 14.803 | 14.373 | 14.609 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 100 | 1.784 | 2.030 | 0.074 | 1.715 | 1.937 | 1.853 | 1.800 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 1000 | 18.361 | 26.644 | 2.738 | 15.832 | 24.112 | 21.156 | 18.789 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 100 | 6.450 | 6.839 | 0.137 | 6.454 | 6.869 | 6.543 | 6.780 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 10000 | 1874.777 | 1925.495 | 12.907 | 1881.821 | 1920.848 | 1902.155 | 1900.514 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 100 | 6.398 | 6.583 | 0.068 | 6.378 | 6.584 | 6.476 | 6.486 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 10000 | 1854.556 | 1956.703 | 30.127 | 1848.940 | 1940.035 | 1871.892 | 1917.083 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 100 | 10.090 | 12.313 | 0.681 | 9.354 | 11.412 | 10.604 | 10.163 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 10000 | 2795.316 | 2885.530 | 26.388 | 2783.913 | 2863.703 | 2823.904 | 2823.712 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 100 | 12.524 | 14.112 | 0.448 | 12.255 | 13.608 | 12.769 | 13.095 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 10000 | 3030.787 | 3136.883 | 29.049 | 3025.512 | 3113.348 | 3064.875 | 3073.985 |

## Complete Measurement-Iteration Evidence

These **400 values**, in **us/op**, are rounded iteration averages. Each timed
iteration contains invocations: `Cnt=10` is ten measured iteration means across
two forks, not ten invocations or a latency percentile distribution.

| Template / method | Pattern | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---|---:|---:|---:|---:|---:|---:|---:|
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 100 | 1 | 0.813 | 0.724 | 0.727 | 0.730 | 0.728 |
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 100 | 2 | 0.735 | 0.727 | 0.735 | 0.727 | 0.753 |
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 10000 | 1 | 94.284 | 94.133 | 96.167 | 94.791 | 93.854 |
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 10000 | 2 | 93.973 | 93.247 | 94.084 | 93.326 | 94.289 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 100 | 1 | 0.757 | 0.726 | 0.723 | 0.731 | 0.725 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 100 | 2 | 0.724 | 0.730 | 0.727 | 0.739 | 0.723 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 10000 | 1 | 95.734 | 95.591 | 95.441 | 93.529 | 93.859 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 10000 | 2 | 94.204 | 93.578 | 93.551 | 93.179 | 94.567 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 100 | 1 | 0.365 | 0.364 | 0.368 | 0.368 | 0.371 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 100 | 2 | 0.413 | 0.401 | 0.398 | 0.392 | 0.406 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 10000 | 1 | 66.589 | 66.590 | 66.557 | 66.302 | 65.302 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 10000 | 2 | 69.513 | 67.751 | 68.274 | 68.735 | 72.715 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 100 | 1 | 0.434 | 0.437 | 0.440 | 0.435 | 0.430 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 100 | 2 | 0.431 | 0.432 | 0.427 | 0.429 | 0.425 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 10000 | 1 | 57.156 | 56.254 | 57.609 | 56.668 | 56.116 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 10000 | 2 | 56.573 | 56.348 | 55.685 | 56.123 | 52.991 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 100 | 1 | 4.133 | 4.134 | 4.077 | 4.070 | 4.405 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 100 | 2 | 4.136 | 4.131 | 4.089 | 4.100 | 4.101 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 10000 | 1 | 1394.941 | 1429.886 | 1413.887 | 1393.600 | 1394.834 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 10000 | 2 | 1457.710 | 1596.738 | 2099.357 | 2048.130 | 1873.359 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 100 | 1 | 4.238 | 4.455 | 5.514 | 9.998 | 7.029 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 100 | 2 | 4.064 | 4.073 | 5.326 | 5.946 | 6.792 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 10000 | 1 | 1391.935 | 1408.382 | 1977.638 | 2429.827 | 1907.056 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 10000 | 2 | 1429.608 | 1419.214 | 1904.733 | 2200.487 | 2236.626 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 100 | 1 | 5.899 | 5.850 | 7.850 | 7.860 | 9.833 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 100 | 2 | 5.936 | 5.857 | 8.363 | 9.584 | 9.643 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 10000 | 1 | 2134.778 | 2105.965 | 2751.354 | 2938.539 | 3143.839 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 10000 | 2 | 2070.614 | 2121.620 | 3198.246 | 3518.521 | 2848.084 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 100 | 1 | 6.043 | 6.556 | 7.983 | 9.978 | 9.561 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 100 | 2 | 6.664 | 6.014 | 8.470 | 9.345 | 9.366 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 10000 | 1 | 1921.665 | 1780.254 | 2532.551 | 3669.527 | 3700.757 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 10000 | 2 | 1785.549 | 1769.543 | 2664.240 | 3220.294 | 2825.777 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 100 | 1 | 4.190 | 4.144 | 6.028 | 6.688 | 7.667 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 100 | 2 | 4.433 | 4.252 | 5.255 | 6.798 | 6.526 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 1000 | 1 | 75.155 | 75.425 | 101.496 | 139.298 | 136.540 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 1000 | 2 | 75.978 | 78.448 | 105.445 | 152.008 | 90.519 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 100 | 1 | 5.818 | 5.811 | 5.946 | 5.642 | 5.738 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 100 | 2 | 5.717 | 5.729 | 5.710 | 5.713 | 6.247 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 1000 | 1 | 149.447 | 149.478 | 149.956 | 147.885 | 147.424 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 1000 | 2 | 148.341 | 149.581 | 148.867 | 150.865 | 148.612 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 100 | 1 | 4.149 | 4.153 | 4.136 | 4.137 | 4.146 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 100 | 2 | 4.188 | 4.109 | 4.450 | 4.149 | 4.236 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 1000 | 1 | 68.565 | 68.372 | 67.949 | 68.619 | 68.568 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 1000 | 2 | 69.984 | 68.832 | 67.995 | 68.627 | 68.689 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 100 | 1 | 4.616 | 4.688 | 4.615 | 4.657 | 4.644 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 100 | 2 | 4.652 | 4.707 | 4.707 | 4.652 | 4.642 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 1000 | 1 | 106.809 | 106.101 | 108.986 | 105.790 | 106.792 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 1000 | 2 | 105.191 | 106.780 | 106.621 | 105.333 | 104.913 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 100 | 1 | 2.211 | 2.425 | 2.213 | 2.254 | 2.214 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 100 | 2 | 4.668 | 3.045 | 2.598 | 2.236 | 2.229 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 1000 | 1 | 22.628 | 23.479 | 22.401 | 22.525 | 22.917 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 1000 | 2 | 22.446 | 22.559 | 22.518 | 22.524 | 22.716 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 100 | 1 | 2.782 | 2.759 | 2.776 | 2.761 | 2.802 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 100 | 2 | 3.047 | 3.466 | 2.968 | 3.021 | 2.783 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 1000 | 1 | 31.696 | 31.656 | 31.619 | 31.552 | 31.849 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 1000 | 2 | 32.116 | 35.040 | 32.177 | 32.291 | 32.056 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 100 | 1 | 1.431 | 1.469 | 1.423 | 1.425 | 1.427 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 100 | 2 | 1.431 | 1.425 | 1.425 | 1.409 | 1.430 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 1000 | 1 | 14.401 | 14.343 | 14.655 | 14.252 | 14.216 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 1000 | 2 | 14.485 | 14.716 | 14.532 | 14.864 | 14.449 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 100 | 1 | 2.030 | 1.805 | 1.814 | 1.802 | 1.812 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 100 | 2 | 1.794 | 1.793 | 1.784 | 1.840 | 1.787 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 1000 | 1 | 26.644 | 23.050 | 19.284 | 18.441 | 18.361 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 1000 | 2 | 18.526 | 18.605 | 18.643 | 18.456 | 19.713 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 100 | 1 | 6.545 | 6.646 | 6.522 | 6.553 | 6.450 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 100 | 2 | 6.839 | 6.729 | 6.814 | 6.789 | 6.729 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 10000 | 1 | 1899.874 | 1899.651 | 1902.973 | 1910.914 | 1897.365 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 10000 | 2 | 1907.703 | 1893.985 | 1900.611 | 1925.495 | 1874.777 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 100 | 1 | 6.453 | 6.583 | 6.486 | 6.420 | 6.438 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 100 | 2 | 6.554 | 6.434 | 6.398 | 6.583 | 6.461 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 10000 | 1 | 1866.685 | 1854.556 | 1896.885 | 1866.407 | 1874.925 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 10000 | 2 | 1899.847 | 1905.884 | 1908.610 | 1956.703 | 1914.369 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 100 | 1 | 10.158 | 12.313 | 10.090 | 10.237 | 10.222 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 100 | 2 | 10.107 | 10.201 | 10.264 | 10.128 | 10.115 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 10000 | 1 | 2795.316 | 2885.530 | 2829.851 | 2802.311 | 2806.514 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 10000 | 2 | 2833.315 | 2816.664 | 2844.437 | 2813.705 | 2810.437 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 100 | 1 | 13.057 | 12.524 | 12.685 | 12.683 | 12.894 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 100 | 2 | 12.711 | 12.714 | 12.926 | 14.112 | 13.011 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 10000 | 1 | 3088.463 | 3056.525 | 3053.439 | 3045.084 | 3080.862 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 10000 | 2 | 3071.927 | 3064.504 | 3065.822 | 3030.787 | 3136.883 |

## Complete Warmup Evidence

These **240 values**, in **us/op**, are excluded from measured/derived fork
means. Their order and fork membership are retained.

| Template / method | Pattern | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---|---:|---:|---:|---:|---:|
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 100 | 1 | 0.897 | 0.788 | 0.749 |
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 100 | 2 | 0.890 | 0.745 | 0.735 |
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 10000 | 1 | 109.205 | 96.265 | 94.185 |
| `ActivitySelectionBenchmark.scanPresorted` | DISJOINT | 10000 | 2 | 109.774 | 95.652 | 102.979 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 100 | 1 | 0.884 | 0.740 | 0.728 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 100 | 2 | 0.983 | 0.785 | 0.742 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 10000 | 1 | 111.573 | 95.229 | 93.670 |
| `ActivitySelectionBenchmark.scanPresorted` | TOUCHING | 10000 | 2 | 111.949 | 96.051 | 94.097 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 100 | 1 | 0.440 | 0.371 | 0.372 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 100 | 2 | 0.465 | 0.404 | 0.400 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 10000 | 1 | 73.838 | 65.731 | 66.253 |
| `ActivitySelectionBenchmark.scanPresorted` | OVERLAPPING | 10000 | 2 | 77.086 | 69.321 | 68.622 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 100 | 1 | 0.543 | 0.432 | 0.453 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 100 | 2 | 0.517 | 0.437 | 0.437 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 10000 | 1 | 68.317 | 67.650 | 59.361 |
| `ActivitySelectionBenchmark.scanPresorted` | RANDOM | 10000 | 2 | 61.891 | 56.643 | 56.785 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 100 | 1 | 5.145 | 4.154 | 4.093 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 100 | 2 | 5.436 | 4.289 | 4.158 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 10000 | 1 | 1786.929 | 1399.452 | 1399.831 |
| `ActivitySelectionBenchmark.sortAndSelect` | DISJOINT | 10000 | 2 | 1766.710 | 1407.008 | 1402.859 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 100 | 1 | 6.288 | 4.602 | 4.222 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 100 | 2 | 4.981 | 4.105 | 4.120 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 10000 | 1 | 1836.486 | 1419.491 | 2196.974 |
| `ActivitySelectionBenchmark.sortAndSelect` | TOUCHING | 10000 | 2 | 1778.060 | 1429.345 | 1408.793 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 100 | 1 | 7.008 | 6.127 | 5.909 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 100 | 2 | 6.803 | 6.051 | 5.893 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 10000 | 1 | 2451.060 | 2163.686 | 2106.461 |
| `ActivitySelectionBenchmark.sortAndSelect` | OVERLAPPING | 10000 | 2 | 2607.381 | 2096.562 | 2106.202 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 100 | 1 | 6.978 | 6.129 | 6.118 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 100 | 2 | 6.910 | 6.613 | 6.027 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 10000 | 1 | 2408.741 | 1945.434 | 1798.282 |
| `ActivitySelectionBenchmark.sortAndSelect` | RANDOM | 10000 | 2 | 2086.755 | 1847.767 | 1764.853 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 100 | 1 | 5.255 | 4.240 | 4.308 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 100 | 2 | 5.464 | 4.363 | 4.595 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 1000 | 1 | 85.709 | 76.427 | 75.452 |
| `HuffmanCostBenchmark.heapCost` | UNIFORM | 1000 | 2 | 93.926 | 89.106 | 77.453 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 100 | 1 | 6.885 | 6.168 | 5.711 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 100 | 2 | 6.776 | 5.914 | 5.841 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 1000 | 1 | 167.628 | 150.710 | 149.029 |
| `HuffmanCostBenchmark.heapCost` | RANDOM | 1000 | 2 | 167.342 | 149.210 | 153.053 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 100 | 1 | 5.503 | 4.253 | 4.211 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 100 | 2 | 5.113 | 4.146 | 4.138 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 1000 | 1 | 82.629 | 106.716 | 84.867 |
| `HuffmanCostBenchmark.heapCost` | SKEWED | 1000 | 2 | 85.930 | 68.945 | 68.301 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 100 | 1 | 5.929 | 4.805 | 4.687 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 100 | 2 | 6.059 | 4.757 | 4.741 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 1000 | 1 | 119.110 | 105.944 | 106.242 |
| `HuffmanCostBenchmark.heapCost` | TWO_LEVEL | 1000 | 2 | 124.076 | 107.336 | 105.491 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 100 | 1 | 2.499 | 2.245 | 2.290 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 100 | 2 | 2.791 | 2.364 | 2.986 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 1000 | 1 | 25.201 | 22.648 | 22.673 |
| `HuffmanCostBenchmark.twoQueueCost` | UNIFORM | 1000 | 2 | 25.192 | 22.762 | 22.558 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 100 | 1 | 3.273 | 2.836 | 2.759 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 100 | 2 | 3.170 | 2.787 | 2.758 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 1000 | 1 | 39.402 | 32.610 | 31.928 |
| `HuffmanCostBenchmark.twoQueueCost` | RANDOM | 1000 | 2 | 38.558 | 32.791 | 31.972 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 100 | 1 | 1.858 | 1.439 | 1.433 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 100 | 2 | 1.618 | 1.485 | 1.418 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 1000 | 1 | 16.839 | 14.437 | 14.647 |
| `HuffmanCostBenchmark.twoQueueCost` | SKEWED | 1000 | 2 | 17.535 | 15.119 | 14.542 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 100 | 1 | 2.046 | 1.842 | 1.797 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 100 | 2 | 2.066 | 1.976 | 1.811 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 1000 | 1 | 21.717 | 18.658 | 18.555 |
| `HuffmanCostBenchmark.twoQueueCost` | TWO_LEVEL | 1000 | 2 | 20.984 | 19.100 | 18.610 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 100 | 1 | 10.062 | 6.439 | 6.519 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 100 | 2 | 10.362 | 6.851 | 6.843 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 10000 | 1 | 2456.534 | 1947.313 | 1921.406 |
| `IntervalPartitioningBenchmark.assignRooms` | DISJOINT | 10000 | 2 | 2533.895 | 1897.619 | 1891.057 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 100 | 1 | 10.568 | 6.461 | 6.543 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 100 | 2 | 10.975 | 6.439 | 6.547 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 10000 | 1 | 2559.642 | 1858.572 | 1856.819 |
| `IntervalPartitioningBenchmark.assignRooms` | TOUCHING | 10000 | 2 | 2475.602 | 1890.156 | 1892.212 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 100 | 1 | 16.327 | 11.067 | 11.632 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 100 | 2 | 14.141 | 10.886 | 10.441 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 10000 | 1 | 3738.617 | 2868.062 | 2841.908 |
| `IntervalPartitioningBenchmark.assignRooms` | OVERLAPPING | 10000 | 2 | 3669.807 | 2860.583 | 2810.036 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 100 | 1 | 19.156 | 12.756 | 12.867 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 100 | 2 | 18.518 | 13.154 | 13.380 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 10000 | 1 | 4410.778 | 3152.495 | 3096.547 |
| `IntervalPartitioningBenchmark.assignRooms` | RANDOM | 10000 | 2 | 4236.243 | 3108.430 | 3046.982 |

Every final-table mean/error agrees with its result block and class parameters.
Every pooled mean matches its ten rounded observations within 0.001 us/op;
min/max and sample standard deviations agree to printed precision. No value
was deleted, replaced or silently pooled with another run.

## Research Limits and Module Handoff

- Coverage is the **40 selected defaults**, one seed and one runtime/environment.
  Other sizes, interval domains, priorities/weights, frequency distributions,
  symbol/code generation, codecs, coin systems and parallel variants are not
  empirically completed by this run.
- All ten wide-error cells remain visible. Confidence intervals are model-based,
  not invocation latency ranges or a guarantee against systematic error. No
  significance/equivalence test or universal speedup is claimed; interval
  overlap does not establish equality either.
- Source identity is author-reported, not embedded by JMH or established by a
  jar hash. Full command, execution timestamp, CPU/power details, generated-code
  inspection and profiler data are not supplied. Source work ledgers and
  expected mathematical costs are not observed machine events or new timings.
- Different preprocessing/ownership/output is timed. Presorted preparation is
  excluded for that API; Huffman costs omit codes; room assignment retains all
  intervals. No subtraction isolates sorting, heap, boxing or allocation costs,
  and two-size data does not prove an exponent.
- Correctness remains the earlier **645-test verification**, including **27
  module tests**, independent references and proof arguments. This results
  update changes no executable Java and runs no new timed experiment or CI.
  Existing Arrays/Searching findings and previous module provenance stay intact.

The three templates, READMEs, case studies and project review now link this
complete initial evidence. No repeat is needed merely to record the supplied
run. Further controlled work can vary seeds/shapes/sizes or isolate preparation/
allocation under a stated design, retaining its own source/runtime and errors.
Module 14 (Backtracking) and 15 (Dynamic Programming) remain separate work.

## Source Integrity

Original attachment: `Texto pegado(8).txt`. SHA-256 of the received bytes,
including original line endings:

`073841c736cac100dba41086310c738401dcfe486cdb38ed899751e7dc6927a5`

The attachment is not copied into the repository. Markdown tables and decimal
normalisation retain every printed numerical observation. No JSON/log or new
CSV artifact is created. This hash identifies the received text, not a jar.
