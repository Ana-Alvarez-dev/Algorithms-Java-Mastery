# Backtracking: First Reported Results

The author supplied a complete run of **all 46 current default configurations**:
12 combination counts, six ordinary N-Queens counts, 12 unique permutation
counts and 16 signed subset decisions. This report retains **46 means/errors**,
**460 measurement-iteration averages** and **276 warmup averages**. Completion
time is **6 minutes 42 seconds**. All four templates now use this evidence in
their Result/Interpretation fields.

Coverage is the selected initial grid, not every valid input or API. No
permutation/combination/queen rows or subset witnesses are materialised by
these timed methods. Enumeration, symmetry reduction, general-purpose solvers
and production Dynamic Programming are separate work. Earlier module samples
retain their own source/runtime and uncertainty; smoke scores stay excluded.

## Provenance and Runtime

The author separately supplied checkout SHA
**`b62df1b84810dfe5dea023d5d00bac2eb05c0cd0`**, the merge of feature PR #88.
Its Git tree matches the reviewed Backtracking contribution. This identifies
the author-reported source, not a revision embedded by JMH or a supplied jar
hash. The text does not establish whether SHA capture preceded measurement.
Later results-documentation commits are distinct from that attribution.

| Property | Supplied evidence |
|---|---|
| Reporting date | 2026-10-26, America/Buenos_Aires; exact execution timestamp not supplied |
| JMH | 1.37 in every configuration header |
| Runtime | JDK 21.0.9, Java HotSpot(TM) 64-Bit Server VM, 21.0.9+7-LTS-338 |
| Vendor | Oracle Corporation, separately supplied with runtime information |
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
| Confidence output | Printed 99.9% intervals and normal-distribution assumption |
| Operating system | Windows 11, version 10.0, amd64; separately supplied by the author |
| Locale and encoding | es_AR, UTF-8, separately supplied |
| Full command | Not captured; attachment begins with the first JMH header |
| CPU, power settings, profiler metrics, generated-code inspection, jar hash | Not supplied |

The [module guide](../../../14-backtracking/implementation-and-benchmark-guide.md)
instructed this combined invocation:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*backtracking.*" -foe true
```

This is the instructed protocol, not a recovered command line. Every header,
fork, parameter and completion marker matches the default grid/settings. The
combined table has `distribution`, `n`, `scenario` and `selection` columns.
`N/A` means inapplicable to that class, not zero or an omitted tested parameter.
Queen rows have only n. Class-specific grids and mathematical outputs must not
be pooled into a cross-problem ranking.

Decimal commas are normalised to dots, retaining all three printed decimals.
These are rounded console iteration averages, not full-precision JMH arrays or
individual invocation-latency distributions. Warmup stays separate; no outlier
is removed. CI endpoints preserve JMH output, including 0.001 rounding
differences from calculations on printed mean/error. Tiny values can print
identical iterations while JMH still prints a nonzero error/standard deviation
from its underlying precision; the text is retained without inventing digits.

## Contracts, Fixtures and Timed Work

The guide links input/error/empty/duplicate policies, pseudocode, traces,
completeness/uniqueness and sound-pruning arguments, exact state/output costs
and independent tests. Educational caps bound the selected APIs; they are not
maximal mathematical domains or fixed resource promises. Every method validates
its input, preserves caller arrays and returns a primitive consumed by JMH.

| Template | Fixtures | Complete timed workflow |
|---|---|---|
| [CombinationCountBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/CombinationCountBenchmark.java) | n=12/18; TWO means k=2, HALF means n/2, ALL means n | Full exact count of size-k subsets of 0..n-1, with/without remaining-capacity pruning; no rows/cache/formula |
| [NQueensCountBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/NQueensCountBenchmark.java) | n=8/10/12 | All ordinary placements; flags include private column/diagonal/path arrays and writes, masks pass primitive state; no boards or symmetry quotient |
| [PermutationCountBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/PermutationCountBenchmark.java) | n=6/8; DISTINCT=0..n-1, DUPLICATE_HEAVY=i%3, ALL_EQUAL=ones | Full unique-value count including private clone/sort/state; sorted-position versus multiplicity candidate loops, no result rows |
| [SubsetSumBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/SubsetSumBenchmark.java) | n=12/18; all twos except SIGNED_MISS alternating -2,+2 | Full include-first boolean decision; bounded includes two O(n) suffix arrays/preparation, reference does not; no witness/count/memo table |

Trial-only generation is excluded. Permutation shuffle uses seed **20261026**;
other fixtures are deterministic. Subset targets are RANGE_MISS=2n+1,
PARITY_MISS=2n-1, PRESENT=n, SIGNED_MISS=1; only PRESENT is true at these
defaults. These are designed controls, not random population samples.
Input order matters to first-witness work. All counters exhaust their required
searches; the boolean methods can short-circuit at an exact prefix sum.

## Complete Means and Errors

Every entry is **mean +/- JMH-reported error in us/op**, `avgt`, `Cnt=10`.

### CombinationCountBenchmark

| Method | Selection | n=12 | n=18 |
|---|---|---:|---:|
| `countPruned` | TWO | 0.274 +/- 0.003 | 0.636 +/- 0.014 |
| `countPruned` | HALF | 3.885 +/- 0.047 | 137.222 +/- 0.659 |
| `countPruned` | ALL | 0.029 +/- 0.001 | 0.044 +/- 0.001 |
| `countUnpruned` | TWO | 0.245 +/- 0.002 | 0.562 +/- 0.004 |
| `countUnpruned` | HALF | 6.363 +/- 2.438 | 268.161 +/- 40.492 |
| `countUnpruned` | ALL | 6.867 +/- 0.091 | 455.584 +/- 19.181 |

### NQueensCountBenchmark

| Method | Other parameter | n=8 | n=10 | n=12 |
|---|---|---:|---:|---:|
| `bitMasks` | N/A | 4.291 +/- 0.057 | 197.377 +/- 3.523 | 5663.154 +/- 329.313 |
| `flags` | N/A | 29.829 +/- 8.793 | 1816.214 +/- 88.718 | 47867.076 +/- 448.464 |

### PermutationCountBenchmark

| Method | Distribution | n=6 | n=8 |
|---|---|---:|---:|
| `frequencyCounts` | DISTINCT | 6.867 +/- 0.080 | 457.918 +/- 5.051 |
| `frequencyCounts` | DUPLICATE_HEAVY | 0.778 +/- 0.010 | 4.999 +/- 0.052 |
| `frequencyCounts` | ALL_EQUAL | 0.039 +/- 0.001 | 0.049 +/- 0.001 |
| `sortedPositions` | DISTINCT | 8.857 +/- 0.100 | 603.265 +/- 37.037 |
| `sortedPositions` | DUPLICATE_HEAVY | 1.768 +/- 0.143 | 13.494 +/- 2.840 |
| `sortedPositions` | ALL_EQUAL | 0.065 +/- 0.001 | 0.098 +/- 0.003 |

### SubsetSumBenchmark

| Method | Scenario | n=12 | n=18 |
|---|---|---:|---:|
| `bounded` | RANGE_MISS | 0.040 +/- 0.001 | 0.055 +/- 0.001 |
| `bounded` | PARITY_MISS | 0.079 +/- 0.001 | 0.114 +/- 0.001 |
| `bounded` | PRESENT | 0.045 +/- 0.001 | 0.062 +/- 0.001 |
| `bounded` | SIGNED_MISS | 6.919 +/- 0.441 | 360.426 +/- 6.790 |
| `withoutBounds` | RANGE_MISS | 12.081 +/- 0.091 | 774.377 +/- 11.168 |
| `withoutBounds` | PARITY_MISS | 12.130 +/- 0.174 | 774.188 +/- 2.837 |
| `withoutBounds` | PRESENT | 0.008 +/- 0.001 | 0.010 +/- 0.001 |
| `withoutBounds` | SIGNED_MISS | 12.269 +/- 0.490 | 778.161 +/- 20.444 |

## Interpretation: Capacity Pruning Depends on Cardinality

For HALF and ALL, countPruned has lower reported means at both n values.
At n=18/HALF the comparison is **137.222 +/- 0.659** versus unpruned
**268.161 +/- 40.492 us/op**. ALL is **0.044 +/- 0.001** versus
**455.584 +/- 19.181 us/op**, although both count exactly one combination.

For TWO the direction reverses: at n=12, pruned **0.274 +/- 0.003** versus
unpruned **0.245 +/- 0.002**; at n=18, **0.636 +/- 0.014** versus
**0.562 +/- 0.004 us/op**. Pruning fewer nodes does not guarantee less elapsed
work for every family. The control removes only one call in each TWO case.

The source independently gives pruned calls C(n+1,k) and unpruned calls
sum(C(n,d),d=0..k), including root/leaves:

| n / selection / k | Mathematical result | Pruned calls | Unpruned calls |
|---|---:|---:|---:|
| 12 / TWO / 2 | 66 | 78 | 79 |
| 12 / HALF / 6 | 924 | 1,716 | 2,510 |
| 12 / ALL / 12 | 1 | 13 | 4,096 |
| 18 / TWO / 2 | 153 | 171 | 172 |
| 18 / HALF / 9 | 48,620 | 92,378 | 155,382 |
| 18 / ALL / 18 | 1 | 19 | 262,144 |

These are mathematical/source helper counts, not printed JMH return values,
machine instructions or elapsed-cost shares. Different local bounds, loops
and JVM optimisation remain in the public workflows; the run does not isolate
a predicate cost or establish a CPU cause. At k=n an increasing feasible path
has n+1 pruned calls, whereas unpruned explores every increasing prefix, 2^n.
Both use O(k+1) logical depth and return one primitive, not a stored plan.

n=12/HALF unpruned has **38.3%** relative error, largest in this run. Its printed
CI **[3.925,8.800]** overlaps the pruned **[3.838,3.932] us/op** interval;
point means alone do not establish a precise ordering or equivalence. Derived
fork means are **4.834** and **7.892 us/op**; all ten observations remain visible.
The differing fork means suggest examining run variation, without identifying
its cause. No exact speedup, formal significance/equivalence or exponent fit
is claimed from these two sizes.

## Interpretation: Ordinary Queens, Flags Versus Available Bits

bitMasks has lower reported means at n=8,10,12. At n=12 it reports
**5663.154 +/- 329.313**, versus flags **47867.076 +/- 448.464 us/op**.
Both return the same ordinary count, **14,200**, without boards or symmetry
reduction. The smaller defaults count 92 and 724, respectively.

Both searches visit the same legal-prefix tree. Flags inspect n column
candidates at each nonterminal node; masks iterate only available children.
The source-level ledger is:

| n | Visited calls V | Complete solutions Q | Flag candidate checks n(V-Q) | Mask child-loop iterations V-1 |
|---|---:|---:|---:|---:|
| 8 | 2,057 | 92 | 15,720 | 2,056 |
| 10 | 35,539 | 724 | 348,150 | 35,538 |
| 12 | 856,189 | 14,200 | 10,103,868 | 856,188 |

These counts come from the reviewed recurrence and an untimed counter, not
from JMH profiler events. They distinguish rejected-candidate checks from
accepted child recursion; they do not equate a flag check with a bit operation.
Flags also allocate/write private arrays/path even for counting. The timing
therefore does not isolate one instruction or allocation and cannot assign
the exact gap to candidate checks, GC, branch prediction or cache locality.

Flags at n=8 has **29.5%** relative error, with derived fork means
**31.562/28.097 us/op**. Keeping that uncertainty is essential despite the lower
mask point mean. Both variants retain O(n) logical depth: primitive masks have
constant-size state per frame, not constant total recursive space. The cap
n<=12 protects the shifts/work domain; no arbitrary-n bit-mask guarantee,
measured stack-byte amount, factorial fit or first-solution result follows.

## Interpretation: Duplicate-Aware Permutation Counts

frequencyCounts has lower means for all six matching fixtures. At n=8/DISTINCT,
it reports **457.918 +/- 5.051** versus sortedPositions
**603.265 +/- 37.037 us/op**. At n=8/DUPLICATE_HEAVY, the comparison is
**4.999 +/- 0.052** versus **13.494 +/- 2.840**; at ALL_EQUAL,
**0.049 +/- 0.001** versus **0.098 +/- 0.003 us/op**.

Both clone/sort, preserve input and count unique value arrangements. Repeated
values are indistinguishable. Position tracking also requires canonical equal-
copy skipping; used indices alone would duplicate rows. Frequency search loops
over u distinct-value entries with positive remaining counts. Neither uses a
closed-form multinomial nor memoisation. Derived prefix work is:

| n | Distribution | Nonterminal prefixes I | Unique leaves P | Position candidate checks nI | Frequency candidate checks uI |
|---|---|---:|---:|---:|---:|
| 6 | DISTINCT | 1,237 | 720 | 7,422 | 7,422 |
| 6 | DUPLICATE_HEAVY | 181 | 90 | 1,086 | 543 |
| 6 | ALL_EQUAL | 6 | 1 | 36 | 6 |
| 8 | DISTINCT | 69,281 | 40,320 | 554,248 | 554,248 |
| 8 | DUPLICATE_HEAVY | 1,189 | 560 | 9,512 | 3,567 |
| 8 | ALL_EQUAL | 8 | 1 | 64 | 8 |

These are analytical counts, not measured events or return values printed by
JMH. All-equal input has one leaf yet the position loop still makes n^2 checks,
versus n frequency checks. Duplication changes both output population and loop
width; comparing unlike distributions does not isolate the cost of one check.

For DISTINCT, u=n and both candidate-check counts are identical, yet their
means differ. Reducing loop width alone therefore cannot explain the entire
observed grid. Position used/path writes and duplicate tests differ from
multiplicity bookkeeping; clone/sort/allocation and runtime effects are also
included. A profiler or controlled follow-up would be needed to isolate shares.

No permutation error reaches the descriptive 25% threshold, but n=8 duplicate-
heavy positions still has about **21.0%** error. Lower relative error does not
exclude systematic effects or make precise universal ratios valid. These are
count-only comparisons, not enumeration throughput, row-allocation rates or
proof that arbitrary permutation workloads are cheap. Materialising P rows
adds their width/output-storage cost; the 10! count cap differs from enumeration.

## Interpretation: Signed Subset Bounds and Early Witnesses

For RANGE_MISS, PARITY_MISS and SIGNED_MISS, bounded has lower means at both
sizes. At n=18, RANGE_MISS is **0.055 +/- 0.001** versus withoutBounds
**774.377 +/- 11.168**; PARITY_MISS is **0.114 +/- 0.001** versus
**774.188 +/- 2.837**; SIGNED_MISS is **360.426 +/- 6.790** versus
**778.161 +/- 20.444 us/op**. Bounds do not make every absent target equally
easy, as the signed in-range fixture demonstrates.

For PRESENT, withoutBounds instead has lower means: at n=12,
**0.008 +/- 0.001** versus bounded **0.045 +/- 0.001**; at n=18,
**0.010 +/- 0.001** versus **0.062 +/- 0.001 us/op**. Include-first reaches a
witness using the first n/2 twos. Both use the same successful recursion chain;
bounded still prepares two suffix arrays and checks bounds at visited prefixes.
Thus a preprocessing/pruning workflow need not win when the witness is early.
The observed difference does not isolate preparation or allocation costs.

The source ledgers, including root/terminal calls but excluding suffix setup,
are:

| n | Scenario / target | Decision | Bounded calls | Without-bound calls |
|---|---|---|---:|---:|
| 12 | RANGE_MISS / 25 | false | 1 | 8,191 |
| 12 | PARITY_MISS / 23 | false | 25 | 8,191 |
| 12 | PRESENT / 12 | true | 7 | 7 |
| 12 | SIGNED_MISS / 1 | false | 3,431 | 8,191 |
| 18 | RANGE_MISS / 37 | false | 1 | 524,287 |
| 18 | PARITY_MISS / 35 | false | 37 | 524,287 |
| 18 | PRESENT / 18 | true | 10 | 10 |
| 18 | SIGNED_MISS / 1 | false | 184,755 | 524,287 |

All miss controls exhaust the unbounded binary tree. RANGE_MISS is outside the
root's achievable interval. For all-twos PARITY_MISS=2n-1, excluding any item
makes the upper bound too small; only the include chain and its rejected
siblings remain. SIGNED_MISS sums are even, but production has no parity test;
its generic intervals leave many prefixes. These are source-derived logical
counts, including signed counter enumeration, not observations embedded by JMH.
The root-rejected public bounded call is still O(n) due to suffix preparation.

Signed bounds sum negative and positive suffix values separately. They safely
enclose every possible continuation; inside the interval is not sufficient for
reachability. A generic sum>target rejection would lose valid signed solutions
such as [8,-9] -> -1. Long sums are exact in the length<=24 int domain, and
direct bound comparisons avoid overflowing target-sum for extreme long targets.

No subset error reaches 25%, but tiny PRESENT values have limited decimal
precision; visible identical iterations do not justify replacing a printed
nonzero error with zero. Input order and first-hit behaviour are part of the
decision contract. This is neither subset enumeration/counting nor a general
polynomial-time subset-sum result; worst-case exponential search and O(n)
logical depth remain. No cache/GC cause or exact predicate cost is established.

## Uncertainty Review

**Two of 46 cells** have error at least 25% of the mean. The threshold describes
the supplied output; it is not a significance/pass-fail rule. Low error does not
rule out systematic effects. Fork summaries derive from rounded iteration
averages and add no new observations. No outlier/fork is discarded.

| Template / method | Parameter | n | Error / mean | Fork 1 mean (us/op) | Fork 2 mean (us/op) |
|---|---|---:|---:|---:|---:|
| `CombinationCountBenchmark.countUnpruned` | HALF | 12 | 38.3% | 4.834 | 7.892 |
| `NQueensCountBenchmark.flags` | N/A | 8 | 29.5% | 31.562 | 28.097 |

## Statistics and Derived Fork Means

All values are **us/op**. Min/max describe measurement-iteration averages,
not per-invocation latency extremes. Standard deviation differs from JMH error.
Each derived fork mean uses its five rounded measured averages and is rounded
to three decimals. CI endpoints retain the printed 99.9% model assumption.

| Template / method | Parameter | n | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| `CombinationCountBenchmark.countPruned` | TWO | 12 | 0.271 | 0.277 | 0.002 | 0.270 | 0.277 | 0.272 | 0.275 |
| `CombinationCountBenchmark.countPruned` | HALF | 12 | 3.839 | 3.940 | 0.031 | 3.838 | 3.932 | 3.868 | 3.902 |
| `CombinationCountBenchmark.countPruned` | ALL | 12 | 0.029 | 0.029 | 0.001 | 0.028 | 0.029 | 0.029 | 0.029 |
| `CombinationCountBenchmark.countPruned` | TWO | 18 | 0.629 | 0.658 | 0.009 | 0.623 | 0.650 | 0.641 | 0.632 |
| `CombinationCountBenchmark.countPruned` | HALF | 18 | 136.519 | 138.118 | 0.436 | 136.563 | 137.881 | 137.186 | 137.257 |
| `CombinationCountBenchmark.countPruned` | ALL | 18 | 0.043 | 0.045 | 0.001 | 0.043 | 0.045 | 0.045 | 0.043 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 12 | 0.242 | 0.247 | 0.002 | 0.242 | 0.247 | 0.244 | 0.246 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 12 | 4.793 | 7.954 | 1.612 | 3.925 | 8.800 | 4.834 | 7.892 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 12 | 6.801 | 6.967 | 0.060 | 6.776 | 6.958 | 6.916 | 6.818 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 18 | 0.557 | 0.567 | 0.003 | 0.557 | 0.566 | 0.561 | 0.562 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 18 | 253.885 | 340.718 | 26.783 | 227.669 | 308.653 | 255.142 | 281.180 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 18 | 443.500 | 473.998 | 12.687 | 436.403 | 474.765 | 462.282 | 448.886 |
| `NQueensCountBenchmark.bitMasks` | N/A | 8 | 4.226 | 4.357 | 0.038 | 4.235 | 4.348 | 4.268 | 4.315 |
| `NQueensCountBenchmark.bitMasks` | N/A | 10 | 194.701 | 202.286 | 2.330 | 193.855 | 200.900 | 197.958 | 196.797 |
| `NQueensCountBenchmark.bitMasks` | N/A | 12 | 5553.135 | 6275.988 | 217.820 | 5333.840 | 5992.467 | 5595.907 | 5730.400 |
| `NQueensCountBenchmark.flags` | N/A | 8 | 26.809 | 46.157 | 5.816 | 21.037 | 38.622 | 31.562 | 28.097 |
| `NQueensCountBenchmark.flags` | N/A | 10 | 1787.748 | 1982.076 | 58.681 | 1727.497 | 1904.932 | 1793.301 | 1839.127 |
| `NQueensCountBenchmark.flags` | N/A | 12 | 47409.936 | 48338.673 | 296.631 | 47418.613 | 48315.540 | 47906.871 | 47827.282 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 6 | 6.783 | 6.981 | 0.053 | 6.787 | 6.947 | 6.856 | 6.877 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 8 | 454.016 | 465.451 | 3.341 | 452.867 | 462.969 | 459.112 | 456.723 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 6 | 0.772 | 0.795 | 0.006 | 0.769 | 0.788 | 0.781 | 0.776 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 8 | 4.951 | 5.059 | 0.034 | 4.947 | 5.050 | 5.023 | 4.975 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 6 | 0.039 | 0.040 | 0.001 | 0.039 | 0.040 | 0.039 | 0.039 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 8 | 0.049 | 0.050 | 0.001 | 0.049 | 0.050 | 0.049 | 0.049 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 6 | 8.773 | 8.960 | 0.066 | 8.757 | 8.957 | 8.839 | 8.874 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 8 | 583.863 | 656.146 | 24.498 | 566.227 | 640.302 | 589.515 | 617.014 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 6 | 1.683 | 1.986 | 0.095 | 1.624 | 1.911 | 1.732 | 1.804 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 8 | 12.300 | 17.910 | 1.878 | 10.654 | 16.333 | 12.401 | 14.587 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 6 | 0.064 | 0.068 | 0.001 | 0.064 | 0.067 | 0.065 | 0.066 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 8 | 0.096 | 0.102 | 0.002 | 0.095 | 0.101 | 0.098 | 0.097 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 12 | 0.039 | 0.042 | 0.001 | 0.038 | 0.041 | 0.040 | 0.039 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 12 | 0.078 | 0.081 | 0.001 | 0.078 | 0.080 | 0.079 | 0.079 |
| `SubsetSumBenchmark.bounded` | PRESENT | 12 | 0.044 | 0.046 | 0.001 | 0.044 | 0.046 | 0.044 | 0.045 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 12 | 6.625 | 7.526 | 0.291 | 6.478 | 7.359 | 6.917 | 6.921 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 18 | 0.054 | 0.056 | 0.001 | 0.054 | 0.056 | 0.055 | 0.055 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 18 | 0.113 | 0.117 | 0.001 | 0.113 | 0.116 | 0.115 | 0.114 |
| `SubsetSumBenchmark.bounded` | PRESENT | 18 | 0.061 | 0.062 | 0.001 | 0.061 | 0.062 | 0.062 | 0.062 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 18 | 356.286 | 370.567 | 4.491 | 353.636 | 367.216 | 358.012 | 362.841 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 12 | 11.985 | 12.196 | 0.060 | 11.990 | 12.172 | 12.064 | 12.098 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 12 | 11.982 | 12.334 | 0.115 | 11.956 | 12.304 | 12.140 | 12.120 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 12 | 0.008 | 0.008 | 0.001 | 0.008 | 0.008 | 0.008 | 0.008 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 12 | 11.937 | 12.723 | 0.324 | 11.779 | 12.759 | 11.967 | 12.571 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 18 | 767.491 | 793.240 | 7.387 | 763.208 | 785.545 | 771.527 | 777.226 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 18 | 771.402 | 777.320 | 1.876 | 771.351 | 777.025 | 774.420 | 773.956 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 18 | 0.010 | 0.010 | 0.001 | 0.010 | 0.010 | 0.010 | 0.010 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 18 | 766.916 | 806.729 | 13.523 | 757.717 | 798.606 | 768.663 | 787.659 |

## Complete Measurement-Iteration Evidence

These **460 values**, in **us/op**, are rounded iteration averages. Each timed
iteration contains invocations; Cnt=10 is ten measured iteration means across
two forks, not ten invocations or a latency percentile distribution.

| Template / method | Parameter | n | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---|---:|---:|---:|---:|---:|---:|---:|
| `CombinationCountBenchmark.countPruned` | TWO | 12 | 1 | 0.271 | 0.272 | 0.274 | 0.272 | 0.272 |
| `CombinationCountBenchmark.countPruned` | TWO | 12 | 2 | 0.277 | 0.277 | 0.274 | 0.273 | 0.274 |
| `CombinationCountBenchmark.countPruned` | HALF | 12 | 1 | 3.868 | 3.839 | 3.894 | 3.894 | 3.843 |
| `CombinationCountBenchmark.countPruned` | HALF | 12 | 2 | 3.863 | 3.902 | 3.940 | 3.904 | 3.902 |
| `CombinationCountBenchmark.countPruned` | ALL | 12 | 1 | 0.029 | 0.029 | 0.029 | 0.029 | 0.029 |
| `CombinationCountBenchmark.countPruned` | ALL | 12 | 2 | 0.029 | 0.029 | 0.029 | 0.029 | 0.029 |
| `CombinationCountBenchmark.countPruned` | TWO | 18 | 1 | 0.634 | 0.638 | 0.658 | 0.642 | 0.631 |
| `CombinationCountBenchmark.countPruned` | TWO | 18 | 2 | 0.640 | 0.633 | 0.630 | 0.629 | 0.629 |
| `CombinationCountBenchmark.countPruned` | HALF | 18 | 1 | 137.106 | 137.265 | 137.323 | 137.430 | 136.807 |
| `CombinationCountBenchmark.countPruned` | HALF | 18 | 2 | 137.246 | 138.118 | 136.519 | 136.909 | 137.492 |
| `CombinationCountBenchmark.countPruned` | ALL | 18 | 1 | 0.045 | 0.045 | 0.045 | 0.045 | 0.045 |
| `CombinationCountBenchmark.countPruned` | ALL | 18 | 2 | 0.043 | 0.043 | 0.043 | 0.043 | 0.043 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 12 | 1 | 0.243 | 0.246 | 0.242 | 0.243 | 0.244 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 12 | 2 | 0.246 | 0.247 | 0.246 | 0.245 | 0.244 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 12 | 1 | 4.834 | 4.806 | 4.839 | 4.897 | 4.793 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 12 | 2 | 7.934 | 7.832 | 7.954 | 7.897 | 7.841 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 12 | 1 | 6.882 | 6.967 | 6.916 | 6.872 | 6.941 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 12 | 2 | 6.804 | 6.801 | 6.804 | 6.859 | 6.823 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 18 | 1 | 0.566 | 0.560 | 0.562 | 0.560 | 0.559 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 18 | 2 | 0.567 | 0.563 | 0.559 | 0.557 | 0.564 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 18 | 1 | 253.885 | 255.807 | 254.683 | 255.602 | 255.735 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 18 | 2 | 340.718 | 279.550 | 269.737 | 257.581 | 258.315 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 18 | 1 | 443.586 | 452.238 | 470.858 | 470.729 | 473.998 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 18 | 2 | 443.500 | 463.383 | 446.412 | 445.335 | 445.800 |
| `NQueensCountBenchmark.bitMasks` | N/A | 8 | 1 | 4.264 | 4.289 | 4.226 | 4.248 | 4.312 |
| `NQueensCountBenchmark.bitMasks` | N/A | 8 | 2 | 4.286 | 4.311 | 4.314 | 4.357 | 4.307 |
| `NQueensCountBenchmark.bitMasks` | N/A | 10 | 1 | 199.391 | 198.059 | 197.116 | 198.437 | 196.786 |
| `NQueensCountBenchmark.bitMasks` | N/A | 10 | 2 | 197.019 | 194.948 | 202.286 | 194.701 | 195.031 |
| `NQueensCountBenchmark.bitMasks` | N/A | 12 | 1 | 5637.092 | 5553.135 | 5651.331 | 5572.489 | 5565.490 |
| `NQueensCountBenchmark.bitMasks` | N/A | 12 | 2 | 5564.195 | 5598.585 | 5620.925 | 5592.305 | 6275.988 |
| `NQueensCountBenchmark.flags` | N/A | 8 | 1 | 28.036 | 27.075 | 29.242 | 27.302 | 46.157 |
| `NQueensCountBenchmark.flags` | N/A | 8 | 2 | 28.672 | 27.873 | 29.747 | 26.809 | 27.383 |
| `NQueensCountBenchmark.flags` | N/A | 10 | 1 | 1795.553 | 1798.603 | 1796.759 | 1787.843 | 1787.748 |
| `NQueensCountBenchmark.flags` | N/A | 10 | 2 | 1982.076 | 1802.181 | 1796.309 | 1809.335 | 1805.734 |
| `NQueensCountBenchmark.flags` | N/A | 12 | 1 | 47915.364 | 47903.318 | 47841.191 | 47784.736 | 48089.745 |
| `NQueensCountBenchmark.flags` | N/A | 12 | 2 | 47532.445 | 47627.236 | 48228.118 | 47409.936 | 48338.673 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 6 | 1 | 6.854 | 6.890 | 6.869 | 6.814 | 6.854 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 6 | 2 | 6.903 | 6.981 | 6.864 | 6.783 | 6.855 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 8 | 1 | 458.401 | 459.696 | 465.451 | 456.854 | 455.157 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 8 | 2 | 454.895 | 460.268 | 457.809 | 456.628 | 454.016 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 6 | 1 | 0.795 | 0.777 | 0.776 | 0.779 | 0.777 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 6 | 2 | 0.779 | 0.772 | 0.777 | 0.773 | 0.780 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 8 | 1 | 5.020 | 5.045 | 4.991 | 4.998 | 5.059 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 8 | 2 | 4.976 | 4.951 | 4.964 | 4.983 | 4.999 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 6 | 1 | 0.039 | 0.039 | 0.040 | 0.040 | 0.039 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 6 | 2 | 0.039 | 0.040 | 0.039 | 0.039 | 0.039 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 8 | 1 | 0.049 | 0.049 | 0.049 | 0.049 | 0.049 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 8 | 2 | 0.049 | 0.050 | 0.049 | 0.049 | 0.049 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 6 | 1 | 8.789 | 8.813 | 8.833 | 8.800 | 8.960 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 6 | 2 | 8.924 | 8.773 | 8.864 | 8.937 | 8.874 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 8 | 1 | 594.909 | 589.496 | 589.356 | 589.952 | 583.863 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 8 | 2 | 602.842 | 639.717 | 598.931 | 656.146 | 587.434 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 6 | 1 | 1.803 | 1.690 | 1.683 | 1.796 | 1.686 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 6 | 2 | 1.835 | 1.986 | 1.712 | 1.777 | 1.708 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 8 | 1 | 12.433 | 12.300 | 12.585 | 12.300 | 12.386 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 8 | 2 | 15.509 | 17.910 | 14.261 | 12.816 | 12.437 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 6 | 1 | 0.064 | 0.068 | 0.065 | 0.066 | 0.064 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 6 | 2 | 0.066 | 0.066 | 0.065 | 0.066 | 0.065 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 8 | 1 | 0.097 | 0.097 | 0.099 | 0.098 | 0.098 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 8 | 2 | 0.096 | 0.096 | 0.102 | 0.096 | 0.096 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 12 | 1 | 0.039 | 0.039 | 0.042 | 0.040 | 0.039 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 12 | 2 | 0.039 | 0.040 | 0.040 | 0.039 | 0.039 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 12 | 1 | 0.079 | 0.079 | 0.080 | 0.079 | 0.079 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 12 | 2 | 0.078 | 0.081 | 0.079 | 0.079 | 0.080 |
| `SubsetSumBenchmark.bounded` | PRESENT | 12 | 1 | 0.045 | 0.044 | 0.044 | 0.044 | 0.045 |
| `SubsetSumBenchmark.bounded` | PRESENT | 12 | 2 | 0.044 | 0.046 | 0.044 | 0.045 | 0.044 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 12 | 1 | 7.526 | 7.089 | 6.691 | 6.625 | 6.652 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 12 | 2 | 7.228 | 6.993 | 6.844 | 6.715 | 6.823 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 18 | 1 | 0.054 | 0.055 | 0.055 | 0.056 | 0.055 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 18 | 2 | 0.055 | 0.055 | 0.055 | 0.054 | 0.056 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 18 | 1 | 0.114 | 0.114 | 0.115 | 0.114 | 0.117 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 18 | 2 | 0.114 | 0.114 | 0.114 | 0.115 | 0.113 |
| `SubsetSumBenchmark.bounded` | PRESENT | 18 | 1 | 0.062 | 0.061 | 0.062 | 0.061 | 0.062 |
| `SubsetSumBenchmark.bounded` | PRESENT | 18 | 2 | 0.062 | 0.062 | 0.062 | 0.062 | 0.062 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 18 | 1 | 361.158 | 356.539 | 356.286 | 359.710 | 356.365 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 18 | 2 | 370.567 | 364.540 | 359.031 | 357.830 | 362.236 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 12 | 1 | 12.100 | 12.066 | 11.993 | 12.086 | 12.073 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 12 | 2 | 12.106 | 11.985 | 12.196 | 12.099 | 12.105 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 12 | 1 | 12.083 | 12.083 | 12.194 | 12.311 | 12.027 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 12 | 2 | 12.078 | 12.092 | 12.115 | 11.982 | 12.334 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 12 | 1 | 0.008 | 0.008 | 0.008 | 0.008 | 0.008 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 12 | 2 | 0.008 | 0.008 | 0.008 | 0.008 | 0.008 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 12 | 1 | 11.937 | 12.006 | 11.950 | 11.970 | 11.973 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 12 | 2 | 12.553 | 12.723 | 12.538 | 12.502 | 12.541 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 18 | 1 | 774.543 | 773.244 | 773.075 | 769.284 | 767.491 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 18 | 2 | 771.644 | 793.240 | 779.145 | 772.866 | 769.234 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 18 | 1 | 773.426 | 772.136 | 775.559 | 773.657 | 777.320 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 18 | 2 | 771.402 | 775.826 | 773.820 | 775.829 | 772.903 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 18 | 1 | 0.010 | 0.010 | 0.010 | 0.010 | 0.010 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 18 | 2 | 0.010 | 0.010 | 0.010 | 0.010 | 0.010 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 18 | 1 | 768.444 | 768.593 | 767.192 | 772.170 | 766.916 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 18 | 2 | 777.419 | 785.302 | 773.674 | 795.172 | 806.729 |

## Complete Warmup Evidence

These **276 values**, in **us/op**, remain excluded from pooled/derived measured
means. Their sequence and fork membership are preserved.

| Template / method | Parameter | n | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---|---:|---:|---:|---:|---:|
| `CombinationCountBenchmark.countPruned` | TWO | 12 | 1 | 0.168 | 0.160 | 0.273 |
| `CombinationCountBenchmark.countPruned` | TWO | 12 | 2 | 0.168 | 0.156 | 0.273 |
| `CombinationCountBenchmark.countPruned` | HALF | 12 | 1 | 3.881 | 3.836 | 3.860 |
| `CombinationCountBenchmark.countPruned` | HALF | 12 | 2 | 3.900 | 3.838 | 3.879 |
| `CombinationCountBenchmark.countPruned` | ALL | 12 | 1 | 0.030 | 0.031 | 0.029 |
| `CombinationCountBenchmark.countPruned` | ALL | 12 | 2 | 0.030 | 0.029 | 0.029 |
| `CombinationCountBenchmark.countPruned` | TWO | 18 | 1 | 0.359 | 0.323 | 0.631 |
| `CombinationCountBenchmark.countPruned` | TWO | 18 | 2 | 0.366 | 0.325 | 0.632 |
| `CombinationCountBenchmark.countPruned` | HALF | 18 | 1 | 138.015 | 136.892 | 137.808 |
| `CombinationCountBenchmark.countPruned` | HALF | 18 | 2 | 138.760 | 139.611 | 137.598 |
| `CombinationCountBenchmark.countPruned` | ALL | 18 | 1 | 0.047 | 0.046 | 0.045 |
| `CombinationCountBenchmark.countPruned` | ALL | 18 | 2 | 0.045 | 0.046 | 0.043 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 12 | 1 | 0.182 | 0.177 | 0.244 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 12 | 2 | 0.184 | 0.177 | 0.247 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 12 | 1 | 4.874 | 4.802 | 4.827 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 12 | 2 | 7.896 | 7.800 | 7.941 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 12 | 1 | 6.955 | 6.837 | 6.853 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 12 | 2 | 6.994 | 6.841 | 6.879 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 18 | 1 | 0.410 | 0.392 | 0.563 |
| `CombinationCountBenchmark.countUnpruned` | TWO | 18 | 2 | 0.392 | 0.394 | 0.592 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 18 | 1 | 256.222 | 256.428 | 258.693 |
| `CombinationCountBenchmark.countUnpruned` | HALF | 18 | 2 | 258.247 | 258.449 | 266.387 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 18 | 1 | 447.248 | 448.475 | 450.804 |
| `CombinationCountBenchmark.countUnpruned` | ALL | 18 | 2 | 450.862 | 447.250 | 448.000 |
| `NQueensCountBenchmark.bitMasks` | N/A | 8 | 1 | 4.313 | 4.309 | 4.282 |
| `NQueensCountBenchmark.bitMasks` | N/A | 8 | 2 | 4.404 | 4.418 | 4.316 |
| `NQueensCountBenchmark.bitMasks` | N/A | 10 | 1 | 196.754 | 198.709 | 200.276 |
| `NQueensCountBenchmark.bitMasks` | N/A | 10 | 2 | 198.619 | 198.652 | 196.707 |
| `NQueensCountBenchmark.bitMasks` | N/A | 12 | 1 | 5609.992 | 5609.647 | 5622.075 |
| `NQueensCountBenchmark.bitMasks` | N/A | 12 | 2 | 5621.847 | 5631.215 | 5579.043 |
| `NQueensCountBenchmark.flags` | N/A | 8 | 1 | 27.334 | 29.377 | 27.320 |
| `NQueensCountBenchmark.flags` | N/A | 8 | 2 | 34.084 | 33.219 | 31.911 |
| `NQueensCountBenchmark.flags` | N/A | 10 | 1 | 1827.524 | 1791.410 | 1785.822 |
| `NQueensCountBenchmark.flags` | N/A | 10 | 2 | 1841.169 | 2006.071 | 1785.699 |
| `NQueensCountBenchmark.flags` | N/A | 12 | 1 | 49625.991 | 47614.618 | 48241.409 |
| `NQueensCountBenchmark.flags` | N/A | 12 | 2 | 49097.891 | 49045.991 | 48244.300 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 6 | 1 | 7.127 | 7.124 | 6.873 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 6 | 2 | 7.182 | 6.978 | 6.882 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 8 | 1 | 463.526 | 468.091 | 457.771 |
| `PermutationCountBenchmark.frequencyCounts` | DISTINCT | 8 | 2 | 460.239 | 460.190 | 461.247 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 6 | 1 | 0.797 | 0.796 | 0.772 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 6 | 2 | 0.813 | 0.796 | 0.783 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 8 | 1 | 5.191 | 5.031 | 5.019 |
| `PermutationCountBenchmark.frequencyCounts` | DUPLICATE_HEAVY | 8 | 2 | 5.176 | 4.974 | 5.037 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 6 | 1 | 0.045 | 0.040 | 0.039 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 6 | 2 | 0.044 | 0.041 | 0.040 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 8 | 1 | 0.053 | 0.051 | 0.049 |
| `PermutationCountBenchmark.frequencyCounts` | ALL_EQUAL | 8 | 2 | 0.052 | 0.051 | 0.050 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 6 | 1 | 9.212 | 8.976 | 9.028 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 6 | 2 | 9.391 | 9.051 | 8.943 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 8 | 1 | 596.063 | 596.602 | 596.979 |
| `PermutationCountBenchmark.sortedPositions` | DISTINCT | 8 | 2 | 604.403 | 596.169 | 639.535 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 6 | 1 | 2.560 | 1.966 | 1.814 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 6 | 2 | 1.873 | 1.992 | 1.806 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 8 | 1 | 12.816 | 12.850 | 12.653 |
| `PermutationCountBenchmark.sortedPositions` | DUPLICATE_HEAVY | 8 | 2 | 13.275 | 13.218 | 14.835 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 6 | 1 | 0.098 | 0.072 | 0.066 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 6 | 2 | 0.075 | 0.073 | 0.066 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 8 | 1 | 0.120 | 0.105 | 0.098 |
| `PermutationCountBenchmark.sortedPositions` | ALL_EQUAL | 8 | 2 | 0.112 | 0.105 | 0.096 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 12 | 1 | 0.043 | 0.040 | 0.039 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 12 | 2 | 0.043 | 0.040 | 0.039 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 12 | 1 | 0.085 | 0.082 | 0.079 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 12 | 2 | 0.086 | 0.083 | 0.079 |
| `SubsetSumBenchmark.bounded` | PRESENT | 12 | 1 | 0.049 | 0.045 | 0.044 |
| `SubsetSumBenchmark.bounded` | PRESENT | 12 | 2 | 0.048 | 0.045 | 0.045 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 12 | 1 | 7.723 | 7.082 | 6.574 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 12 | 2 | 6.975 | 6.947 | 6.758 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 18 | 1 | 0.060 | 0.055 | 0.055 |
| `SubsetSumBenchmark.bounded` | RANGE_MISS | 18 | 2 | 0.059 | 0.056 | 0.055 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 18 | 1 | 0.123 | 0.115 | 0.117 |
| `SubsetSumBenchmark.bounded` | PARITY_MISS | 18 | 2 | 0.123 | 0.117 | 0.123 |
| `SubsetSumBenchmark.bounded` | PRESENT | 18 | 1 | 0.067 | 0.063 | 0.061 |
| `SubsetSumBenchmark.bounded` | PRESENT | 18 | 2 | 0.070 | 0.062 | 0.062 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 18 | 1 | 385.334 | 359.055 | 363.704 |
| `SubsetSumBenchmark.bounded` | SIGNED_MISS | 18 | 2 | 384.594 | 360.488 | 361.146 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 12 | 1 | 12.167 | 12.151 | 12.245 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 12 | 2 | 12.380 | 12.132 | 12.136 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 12 | 1 | 12.204 | 12.136 | 12.258 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 12 | 2 | 12.147 | 12.083 | 12.092 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 12 | 1 | 0.008 | 0.008 | 0.008 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 12 | 2 | 0.008 | 0.008 | 0.008 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 12 | 1 | 12.095 | 12.138 | 12.378 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 12 | 2 | 12.238 | 12.126 | 12.307 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 18 | 1 | 782.016 | 776.200 | 773.085 |
| `SubsetSumBenchmark.withoutBounds` | RANGE_MISS | 18 | 2 | 773.487 | 792.784 | 773.611 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 18 | 1 | 775.156 | 795.674 | 777.600 |
| `SubsetSumBenchmark.withoutBounds` | PARITY_MISS | 18 | 2 | 779.051 | 773.534 | 775.931 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 18 | 1 | 0.011 | 0.011 | 0.010 |
| `SubsetSumBenchmark.withoutBounds` | PRESENT | 18 | 2 | 0.011 | 0.011 | 0.010 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 18 | 1 | 771.832 | 784.128 | 776.603 |
| `SubsetSumBenchmark.withoutBounds` | SIGNED_MISS | 18 | 2 | 772.584 | 767.550 | 772.915 |

All final-table means/errors agree with their result blocks and parameters.
Rounded pooled means, extrema and sample standard deviations match the supplied
observations within printed precision. Nonzero uncertainty is preserved even
when three-decimal iterations coincide. No value is deleted, replaced or pooled
with another source/runtime or a smoke run.

## Research Limits and Module Handoff

- Coverage is **46 selected defaults** on one runtime/environment, not all
  accepted inputs, seeds, candidate orders, queen sizes, subset targets or
  materialisation APIs. Ordinary counts, unique counts and boolean decisions
  solve different problems and cannot form one performance ranking.
- Every mean/error, printed measured/warmup iteration and 99.9% CI is retained.
  Two wide-error cells remain visible. The normal-model output is not a latency
  distribution or immunity to systematic effects. No significance/equivalence
  test, universal speedup or fitted exponential/factorial exponent is claimed.
- Source attribution and OS/vendor/locale are author supplied separately from
  JMH. Full command, execution timestamp, CPU/power, generated-code inspection,
  profiler data and jar hash are unavailable. The text hash below identifies
  the received bytes, not the benchmark executable.
- Source helper/candidate counts are derived mathematical evidence, not observed
  machine events, actual allocations, stack bytes or exact elapsed-cost shares.
  Full validation/private state, clone/sort and suffix preparation remain timed.
  Constant-size masks per frame do not imply constant recursive space.
- Correctness remains the earlier **672-test verification**, including **27
  Backtracking tests**, independent references and proof arguments. This results
  update changes documentation/comments only, with no executable Java change,
  new timed experiment or CI status. Prior Arrays/Searching findings stay tracked.

The four templates, study notes, READMEs and project audit now link this complete
initial evidence. No repeat is required merely to transcribe the supplied run.
Further controlled experiments can vary seeds/order/cardinality/targets, isolate
preparation/allocation or profile candidate work under an explicit question,
retaining their own source/runtime and every error. Module **15 - Dynamic
Programming** is the next separate implementation contribution; no production
DP module is claimed by test-only reachable-set references here.

## Source Integrity

Original attachment: `Texto pegado(9).txt`. SHA-256 of the received bytes,
including original line endings:

`fad99438f0ef374ac26b25076df0bab0b249aedce406290e7e7de779793b51cd`

The raw attachment is not copied into the repository. Markdown tables retain
all printed numerical observations with decimal normalisation. No JSON/log or
new CSV artifact is created. This hash identifies the text, not a jar.
