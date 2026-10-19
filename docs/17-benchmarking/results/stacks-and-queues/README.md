# Stacks and Queues: First Reported Measurement Subset

The author supplied this completed execution on **2026-10-19**, together with
source commit **`804f69f32b0da715ed41f390034523890c24bca5`**. It contains
**20 configurations**, **200 measurement-iteration averages** and **120 warmup
iteration averages**. Both benchmark templates now record Result and
Interpretation based on this evidence. The run completed in **2 minutes 57 seconds**.

This report preserves the supplied numerical evidence in Markdown. No JSON,
log or copied console-text artifact is versioned. Interpretation is preliminary:
the execution covers two sizes, several errors are substantial, and no profiler
or CPU information was supplied.

## Provenance and Runtime

| Property | Supplied evidence |
|---|---|
| Source | Author-pasted console output, supplied as `Texto pegado(1).txt` |
| Date | Reporting date 2026-10-19; no execution timestamp appears in the output |
| Source SHA reported by author | `804f69f32b0da715ed41f390034523890c24bca5` |
| JMH | 1.37, confirmed in every configuration header |
| Runtime | JDK 21.0.9, Java HotSpot(TM) 64-Bit Server VM, 21.0.9+7-LTS-338 |
| Java invoker | `C:Program FilesJavajdk-21injava.exe` |
| VM options | `<none>` |
| Blackhole mode | Compiler, auto-detected |
| Threads | 1, synchronised iterations |
| Warmup | 3 iterations per fork, 500 ms each |
| Measurement | 5 iterations per fork, 500 ms each |
| Forks | 2 per configuration |
| Mode and units | Average time (`avgt`), ns/op |
| Timeout | 10 minutes per iteration |
| Coverage | 6 queue methods + 4 stack methods, each at sizes 100 and 1,000 |
| Measurement count | 10 iteration averages per cell; not ten individual operations |
| Confidence output | 99.9% intervals, with the printed normal-distribution assumption |
| CPU and profiler metrics | Not supplied |
| Operating system | Windows executable path; Windows 11 amd64 was reported separately during environment setup |
| Full command line | First pasted line is truncated; exact invocation is not recoverable from it |

The reported SHA is the merge of PR #71, which introduced this module. It matches
the source reviewed here. JMH does not embed this source identity; it was supplied
separately by the author. Later result-documentation commits are distinct from
the revision used for measurement.

The instructed invocation was:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*stacksandqueues.*" -p size=100,1000 -foe true
```

The headers and coverage match that protocol; the command above is not presented
as an intact captured command line. The earlier 50 ms, one-fork smoke run is a
separate harness check and is excluded from these results.

Decimal commas are transcribed as dots. Every original printed value retains
three decimal places. The console supplies rounded iteration averages, not
full-precision JMH raw arrays or individual-operation latency samples. Warmup
values are retained separately and excluded from measurement calculations.

## Timed Work and Analytical Model

The [implementation guide](../../../07-stacks-and-queues/implementation-and-benchmark-guide.md)
connects specifications, pseudocode, traces, invariants, complexity and tests.
The measured definitions are in
[StackOperationsBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/stacksandqueues/StackOperationsBenchmark.java)
and [QueueOperationsBenchmark](../../../../src/jmh/java/org/anaalvarezdev/algorithms/stacksandqueues/QueueOperationsBenchmark.java).

| Group | One timed invocation | Analytical expectation |
|---|---|---|
| Stack | Push -1, pop and return it; restore contents and size | Constant endpoint work; reserved array capacity excludes growth |
| Queue | Remove the front, append that same boxed object and return it | Constant ordinary endpoint work; two-stack queue includes occasional linear transfers with constant amortised cost |

Trial construction and initial boxing of `0..n-1` are excluded. Array-backed
structures are pre-sized; both internal stacks of the two-stack queue reserve n
slots. The bounded deque starts full and removes before insertion, so no overflow
failure is timed. Queue order rotates while size and the value multiset remain
fixed. Stack contents are restored every call.

Linked-node allocation sites remain in the timed methods, subject to JIT
optimisation. No allocation rate or heap footprint is inferred. Setup populates
several representations together, and repeated warm state is deliberate. All
scores are **per pair**, not per isolated insertion, removal or transfer.

## Complete Reported Means and Errors

Every cell is **mean +/- JMH-reported error in ns/op**, with `Cnt = 10` and
mode `avgt`. Retain errors when comparing means.

### FIFO Rotation

| Method | Size 100 | Size 1,000 |
|---|---:|---:|
| `boundedDequeRotate` | 3.776 +/- 0.541 | 3.593 +/- 0.634 |
| `circularRotate` | 3.893 +/- 0.549 | 3.496 +/- 0.234 |
| `javaArrayDequeRotate` | 3.943 +/- 0.278 | 3.600 +/- 0.241 |
| `javaLinkedListRotate` | 15.107 +/- 0.709 | 14.982 +/- 0.602 |
| `linkedRotate` | 13.553 +/- 1.098 | 14.982 +/- 4.263 |
| `twoStacksRotate` | 9.017 +/- 2.828 | 8.463 +/- 2.339 |

The bounded ring, growing circular queue and JDK ArrayDeque have rotation means
around 3.5–3.9 ns/op in this subset. Their errors overlap in several comparisons;
these values do not support a precise winner among the three representations.
Two-stack rotation has means 9.017 and 8.463 ns/op, including required transfers.
Linked rotation means are about 13.6–15.1 ns/op. Manual and JDK linked queues
both round to 14.982 ns/op at 1,000, with different uncertainty.

These are observations about the measured workload. They do not establish that
node allocation or cache locality caused the differences. The experiment did
not isolate these factors. Similar costs at the two sizes are compatible with
endpoint/amortised reasoning, but are not a proof of constant complexity.

### Restored LIFO Push/Pop Pair

| Method | Size 100 | Size 1,000 |
|---|---:|---:|
| `arrayPushPop` | 3.234 +/- 1.066 | 3.271 +/- 1.154 |
| `javaArrayDequePushPop` | 4.746 +/- 1.495 | 4.811 +/- 1.543 |
| `javaLinkedListPushPop` | 17.527 +/- 5.992 | 17.104 +/- 4.945 |
| `linkedPushPop` | 9.410 +/- 2.827 | 9.609 +/- 3.309 |

The manual array stack has the lowest reported mean in this group at both sizes,
followed by JDK ArrayDeque, the manual linked stack and JDK LinkedList. This is
a description of means, not a universal ranking or a formal significance test.
For example, the manual array and JDK ArrayDeque uncertainty intervals overlap.
Every stack score's reported error exceeds 25% of its mean, limiting exact ratios.

Changing population from 100 to 1,000 leaves each method's mean in a similar
range. No array growth was timed, so these scores cannot describe growth latency
or prove the dynamic stack's amortised bound. Fixed values, returned results,
compiler blackholes and JVM optimisation remain part of the experimental context.

## Supplied Statistics and Derived Fork Means

All min/max, standard deviations and confidence bounds below are transcribed
from complete result blocks, in **ns/op**. Min/max concern measurement-iteration
averages, not individual operation latencies. Standard deviation is distinct from
JMH's reported error. Printed bounds may differ by 0.001 from arithmetic on the
rounded mean/error; the supplied bounds are preserved unchanged.

| Method | Size | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| `boundedDequeRotate` | 100 | 3.369 | 4.502 | 0.358 | 3.235 | 4.316 | 3.889 | 3.662 |
| `boundedDequeRotate` | 1000 | 3.113 | 4.253 | 0.420 | 2.958 | 4.227 | 3.406 | 3.779 |
| `circularRotate` | 100 | 3.438 | 4.353 | 0.363 | 3.345 | 4.442 | 3.577 | 4.210 |
| `circularRotate` | 1000 | 3.268 | 3.693 | 0.155 | 3.261 | 3.730 | 3.424 | 3.568 |
| `javaArrayDequeRotate` | 100 | 3.710 | 4.131 | 0.184 | 3.665 | 4.221 | 3.908 | 3.977 |
| `javaArrayDequeRotate` | 1000 | 3.411 | 3.778 | 0.159 | 3.359 | 3.841 | 3.619 | 3.581 |
| `javaLinkedListRotate` | 100 | 14.424 | 15.807 | 0.469 | 14.398 | 15.815 | 14.992 | 15.221 |
| `javaLinkedListRotate` | 1000 | 14.504 | 15.405 | 0.398 | 14.381 | 15.584 | 14.959 | 15.006 |
| `linkedRotate` | 100 | 12.644 | 14.823 | 0.727 | 12.454 | 14.651 | 13.778 | 13.328 |
| `linkedRotate` | 1000 | 12.680 | 21.254 | 2.820 | 10.719 | 19.245 | 13.593 | 16.371 |
| `twoStacksRotate` | 100 | 7.100 | 12.363 | 1.871 | 6.188 | 11.845 | 9.586 | 8.447 |
| `twoStacksRotate` | 1000 | 6.633 | 10.721 | 1.547 | 6.124 | 10.802 | 8.540 | 8.386 |
| `arrayPushPop` | 100 | 2.492 | 4.322 | 0.705 | 2.168 | 4.300 | 3.238 | 3.231 |
| `arrayPushPop` | 1000 | 2.511 | 4.380 | 0.763 | 2.117 | 4.425 | 3.173 | 3.369 |
| `javaArrayDequePushPop` | 100 | 3.589 | 6.354 | 0.989 | 3.251 | 6.241 | 4.776 | 4.716 |
| `javaArrayDequePushPop` | 1000 | 3.662 | 6.828 | 1.021 | 3.268 | 6.354 | 4.302 | 5.319 |
| `javaLinkedListPushPop` | 100 | 13.243 | 23.958 | 3.963 | 11.535 | 23.519 | 17.606 | 17.448 |
| `javaLinkedListPushPop` | 1000 | 13.284 | 23.408 | 3.271 | 12.159 | 22.049 | 16.388 | 17.820 |
| `linkedPushPop` | 100 | 7.371 | 11.950 | 1.870 | 6.583 | 12.237 | 9.672 | 9.148 |
| `linkedPushPop` | 1000 | 7.411 | 12.906 | 2.189 | 6.300 | 12.918 | 10.021 | 9.197 |

Fork means are derived from the five rounded measurement averages in each fork,
then rounded to three decimals. All 20 pooled means agree with the supplied
summary within 0.001 ns/op. Every configuration's min/max matches its measurement
values. Complete headers, result blocks and final-summary rows agree.

For manual linked rotation at 1,000, the derived fork means are about 13.593
and 16.371 ns/op. JDK ArrayDeque push/pop at 1,000 has fork means about 4.302
and 5.319 ns/op. Such differences locate observed variation without establishing
a cause. The manual array stack at 1,000 has a reported range of 2.511–4.380
ns/op and error 1.154 ns/op on a mean of 3.271 ns/op. No values were removed,
winsorised or replaced as outliers.

## Complete Measurement-Iteration Evidence

The following **200 values** are iteration averages in **ns/op**. Each timed
iteration contains many benchmark invocations. These are not request-latency
percentiles or individual operation samples.

| Method | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---:|---:|---:|---:|---:|---:|---:|
| `boundedDequeRotate` | 100 | 1 | 3.799 | 3.512 | 3.404 | 4.502 | 4.227 |
| `boundedDequeRotate` | 100 | 2 | 3.578 | 3.369 | 3.819 | 3.830 | 3.716 |
| `boundedDequeRotate` | 1000 | 1 | 3.772 | 3.113 | 3.154 | 3.632 | 3.361 |
| `boundedDequeRotate` | 1000 | 2 | 3.876 | 3.304 | 3.262 | 4.198 | 4.253 |
| `circularRotate` | 100 | 1 | 3.438 | 3.478 | 3.622 | 3.671 | 3.675 |
| `circularRotate` | 100 | 2 | 4.026 | 3.999 | 4.353 | 4.343 | 4.331 |
| `circularRotate` | 1000 | 1 | 3.281 | 3.268 | 3.441 | 3.584 | 3.544 |
| `circularRotate` | 1000 | 2 | 3.401 | 3.430 | 3.664 | 3.693 | 3.652 |
| `javaArrayDequeRotate` | 100 | 1 | 3.710 | 3.716 | 3.974 | 4.009 | 4.131 |
| `javaArrayDequeRotate` | 100 | 2 | 3.762 | 3.765 | 4.115 | 4.122 | 4.122 |
| `javaArrayDequeRotate` | 1000 | 1 | 3.415 | 3.429 | 3.718 | 3.778 | 3.754 |
| `javaArrayDequeRotate` | 1000 | 2 | 3.420 | 3.411 | 3.646 | 3.713 | 3.715 |
| `javaLinkedListRotate` | 100 | 1 | 14.505 | 14.522 | 15.201 | 15.363 | 15.368 |
| `javaLinkedListRotate` | 100 | 2 | 14.424 | 15.445 | 15.080 | 15.807 | 15.351 |
| `javaLinkedListRotate` | 1000 | 1 | 14.546 | 14.504 | 15.064 | 15.397 | 15.282 |
| `javaLinkedListRotate` | 1000 | 2 | 14.588 | 14.513 | 15.146 | 15.405 | 15.380 |
| `linkedRotate` | 100 | 1 | 12.953 | 13.104 | 13.965 | 14.823 | 14.047 |
| `linkedRotate` | 100 | 2 | 13.362 | 14.332 | 12.720 | 12.644 | 13.580 |
| `linkedRotate` | 1000 | 1 | 15.049 | 13.551 | 13.400 | 12.978 | 12.987 |
| `linkedRotate` | 1000 | 2 | 13.268 | 12.680 | 16.863 | 17.792 | 21.254 |
| `twoStacksRotate` | 100 | 1 | 7.100 | 7.109 | 10.599 | 10.760 | 12.363 |
| `twoStacksRotate` | 100 | 2 | 7.268 | 7.186 | 8.577 | 9.316 | 9.888 |
| `twoStacksRotate` | 1000 | 1 | 7.090 | 6.633 | 9.486 | 9.569 | 9.924 |
| `twoStacksRotate` | 1000 | 2 | 6.689 | 6.692 | 9.157 | 10.721 | 8.672 |
| `arrayPushPop` | 100 | 1 | 2.492 | 2.544 | 3.444 | 3.386 | 4.322 |
| `arrayPushPop` | 100 | 2 | 2.513 | 2.532 | 3.128 | 3.863 | 4.118 |
| `arrayPushPop` | 1000 | 1 | 2.557 | 2.511 | 3.166 | 4.380 | 3.253 |
| `arrayPushPop` | 1000 | 2 | 2.521 | 2.519 | 3.409 | 4.353 | 4.042 |
| `javaArrayDequePushPop` | 100 | 1 | 4.088 | 3.589 | 4.847 | 5.538 | 5.818 |
| `javaArrayDequePushPop` | 100 | 2 | 3.618 | 3.648 | 4.790 | 5.170 | 6.354 |
| `javaArrayDequePushPop` | 1000 | 1 | 3.662 | 3.679 | 4.426 | 4.943 | 4.801 |
| `javaArrayDequePushPop` | 1000 | 2 | 4.063 | 4.370 | 5.267 | 6.828 | 6.069 |
| `javaLinkedListPushPop` | 100 | 1 | 13.243 | 13.334 | 18.437 | 19.058 | 23.958 |
| `javaLinkedListPushPop` | 100 | 2 | 13.481 | 13.380 | 19.272 | 18.826 | 22.280 |
| `javaLinkedListPushPop` | 1000 | 1 | 13.531 | 13.284 | 17.195 | 20.039 | 17.890 |
| `javaLinkedListPushPop` | 1000 | 2 | 13.913 | 14.744 | 18.216 | 18.820 | 23.408 |
| `linkedPushPop` | 100 | 1 | 7.450 | 7.476 | 9.782 | 11.700 | 11.950 |
| `linkedPushPop` | 100 | 2 | 7.371 | 7.513 | 9.159 | 10.632 | 11.065 |
| `linkedPushPop` | 1000 | 1 | 7.551 | 7.411 | 10.047 | 12.189 | 12.906 |
| `linkedPushPop` | 1000 | 2 | 7.411 | 7.479 | 8.603 | 10.699 | 11.793 |

## Complete Warmup Evidence

These **120 values**, in **ns/op**, are kept separate and excluded from the
reported measurement means and fork means.

| Method | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---:|---:|---:|---:|---:|
| `boundedDequeRotate` | 100 | 1 | 4.612 | 3.808 | 4.412 |
| `boundedDequeRotate` | 100 | 2 | 8.959 | 9.091 | 3.861 |
| `boundedDequeRotate` | 1000 | 1 | 5.364 | 5.153 | 3.814 |
| `boundedDequeRotate` | 1000 | 2 | 3.989 | 5.068 | 3.634 |
| `circularRotate` | 100 | 1 | 4.880 | 5.998 | 3.620 |
| `circularRotate` | 100 | 2 | 4.740 | 4.467 | 4.114 |
| `circularRotate` | 1000 | 1 | 3.878 | 3.809 | 3.225 |
| `circularRotate` | 1000 | 2 | 3.887 | 3.856 | 3.350 |
| `javaArrayDequeRotate` | 100 | 1 | 4.577 | 4.584 | 3.709 |
| `javaArrayDequeRotate` | 100 | 2 | 4.581 | 4.544 | 3.746 |
| `javaArrayDequeRotate` | 1000 | 1 | 4.743 | 4.714 | 3.857 |
| `javaArrayDequeRotate` | 1000 | 2 | 4.720 | 4.716 | 3.457 |
| `javaLinkedListRotate` | 100 | 1 | 16.283 | 16.007 | 14.555 |
| `javaLinkedListRotate` | 100 | 2 | 16.137 | 15.964 | 14.693 |
| `javaLinkedListRotate` | 1000 | 1 | 16.324 | 15.789 | 14.567 |
| `javaLinkedListRotate` | 1000 | 2 | 15.858 | 16.058 | 14.529 |
| `linkedRotate` | 100 | 1 | 15.755 | 15.518 | 12.924 |
| `linkedRotate` | 100 | 2 | 16.429 | 17.980 | 13.632 |
| `linkedRotate` | 1000 | 1 | 20.203 | 17.717 | 13.212 |
| `linkedRotate` | 1000 | 2 | 21.432 | 18.067 | 14.265 |
| `twoStacksRotate` | 100 | 1 | 7.258 | 10.152 | 9.879 |
| `twoStacksRotate` | 100 | 2 | 7.283 | 7.423 | 7.187 |
| `twoStacksRotate` | 1000 | 1 | 7.173 | 7.075 | 7.412 |
| `twoStacksRotate` | 1000 | 2 | 7.165 | 7.032 | 6.713 |
| `arrayPushPop` | 100 | 1 | 3.103 | 2.880 | 2.546 |
| `arrayPushPop` | 100 | 2 | 3.068 | 2.921 | 2.530 |
| `arrayPushPop` | 1000 | 1 | 3.128 | 2.944 | 2.510 |
| `arrayPushPop` | 1000 | 2 | 3.211 | 2.901 | 2.518 |
| `javaArrayDequePushPop` | 100 | 1 | 5.511 | 5.520 | 3.683 |
| `javaArrayDequePushPop` | 100 | 2 | 5.617 | 5.480 | 3.662 |
| `javaArrayDequePushPop` | 1000 | 1 | 5.466 | 5.492 | 3.716 |
| `javaArrayDequePushPop` | 1000 | 2 | 5.569 | 5.558 | 3.660 |
| `javaLinkedListPushPop` | 100 | 1 | 14.882 | 13.916 | 13.334 |
| `javaLinkedListPushPop` | 100 | 2 | 15.271 | 14.039 | 13.284 |
| `javaLinkedListPushPop` | 1000 | 1 | 15.147 | 14.134 | 13.394 |
| `javaLinkedListPushPop` | 1000 | 2 | 17.911 | 17.381 | 14.187 |
| `linkedPushPop` | 100 | 1 | 9.554 | 9.457 | 7.762 |
| `linkedPushPop` | 100 | 2 | 9.489 | 9.340 | 7.402 |
| `linkedPushPop` | 1000 | 1 | 9.755 | 8.845 | 7.555 |
| `linkedPushPop` | 1000 | 2 | 9.123 | 9.311 | 7.447 |

## Research Limits and Handoff

- **11 of 20** scores have reported error at least 25% of their mean; the largest
  relative error is about 35.3%. No formal significance or equivalence test is
  claimed. Confidence bounds express JMH's model, not a guarantee of real-world
  latency or independence of every individual operation.
- Only 20 of the 40 default configurations are measured here. Sizes 10,000 and
  100,000 remain unmeasured. Two sizes cannot establish an asymptotic exponent.
- Two-stack rotation averages periodic transfers. It does not measure the
  worst transfer latency separately. Reserved capacity excludes growth in all
  array-backed benchmark workloads.
- JIT optimisation, allocation, GC, physical layout and background activity
  were not isolated by a profiler or controlled factorial experiment. Do not
  infer their individual causal contribution from these means.
- Fixed values, repeated warm state, one thread and paired operations restrict
  applicability. No thread-safety, memory-footprint, allocation-rate or isolated
  operation-latency claim follows.
- Console values are rounded to three decimals. Full-precision export fields,
  operation-level timestamps, CPU details and an intact shell command were not
  supplied. The source SHA and JMH headers are recorded independently.
- Correctness evidence remains the separate 418-test verification, including
  80 module tests, plus invariants and analysis. This report executes no new
  performance run and does not treat elapsed time as proof of correctness.

The templates can now use this complete initial subset as preliminary empirical
evidence. Additional sizes, separate growth/transfer workloads and justified
profiler runs would address different questions. Keep later runs separate and
record their own source/runtime/command metadata. Preserve every reported error
and identify whether evidence is a console transcription or a full-precision export.

## Source Integrity

The supplied text attachment is identified by SHA-256:

`e8969eacf29d8ff35ebd5c1c82e75818e97f81eae4c73426e5a71266a78732c9`

This identifies the original supplied bytes, including line endings. Decimal
normalisation and Markdown layout do not change the retained printed values.
The attachment itself is not copied into the repository.
