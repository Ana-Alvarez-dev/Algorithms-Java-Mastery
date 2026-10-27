# Corrected Array Aggregation: First Reported Execution

**Evidence received: 2026-10-27.** The author supplied complete JMH console
headers, both forks for all five sizes, result blocks and the final table.
All five means/errors, **50 measured** and **30 warmup iteration averages**
are retained below. Completion: **00:00:44**. A subsequent author-supplied
reflog supports `5eee0dc` as the preceding checkout; execution-source attribution
is inferred from that sequence and the conversation. The original jar is no
longer available for hashing; these limits do not erase the observations.

## Question, reviewed source and attribution

Observe full-scan average time across five bounded inputs. The
[guide](../../implementation-and-benchmark-guide.md) defines contracts,
pseudocode, trace, proof, fixture tests, timed work and the requested command.
The reviewed protocol was merged through PR #93 into
`5eee0dc47547a4713adb7a1a16d4316745bd8d99`; its contribution commit is
`7caf9f1c1a8735952199438aa6dc5c3e96539fc4`.
The subsequent reflog shows `5eee0dc` immediately before the pull to the
results merge `12f2973`. Together with the conversation sequence (measurement,
results PR, then pull), this supports an **inferred execution checkout** of
`5eee0dc`, rather than the later results commit. The console does not embed a
Git SHA or jar hash. Reflog entries alone do not timestamp a benchmark or
record the jar build and working-tree state; this is a qualified attribution.

In that reviewed source, trial setup uses `value[i]=i%100`, outside timing;
the production scan and Blackhole consumption are timed. Each thread reuses
its array. Expected sums below describe this reviewed fixture. JMH did not
print return values or payload contents; linking these observations to that
exact fixture is supported by the reflog-based source inference, while the
precise built artifact and execution-time dirty state remain unverified.

The [historical Arrays report](README.md) retains the old ascending payload's
five means and the two int-sum domain violations. Those old values and missing
metadata remain historical. Different payload/settings and incomplete old
provenance prevent treating the new/old means as a controlled improvement.

## Execution provenance and effective headers

| Field | Supplied evidence |
|---|---|
| Execution source SHA / clean or dirty state | `5eee0dc47547a4713adb7a1a16d4316745bd8d99`, inferred from supplied reflog and conversation sequence; execution-time dirty state unknown |
| Jar SHA-256 | Unavailable: subsequent Get-FileHash attempt reports that target/benchmarks.jar does not exist; no replacement artifact is substituted |
| Execution start timestamp | Not supplied; receipt date is not run time |
| Complete invoked command | Not supplied; the preceding requested command is not proof of invocation |
| JMH version | 1.37 in all five headers |
| VM version | JDK 21.0.9; Java HotSpot(TM) 64-Bit Server VM; 21.0.9+7-LTS-338 |
| VM invoker | `C:\Program Files\Java\jdk-21\bin\java.exe` |
| VM options | `<none>` |
| Blackhole mode | compiler, auto-detected |
| Warmup | 3 iterations x 500 ms per fork |
| Measurement | 5 iterations x 500 ms per fork |
| Forks / threads | 2 / 1, synchronised iterations |
| Timeout | 10 minutes per iteration |
| Mode / output unit | Average time, time/op / ns/op |
| Benchmark | `org.anaalvarezdev.algorithms.arrays.ArrayAggregationBenchmark.sum` |
| Parameters | size=100,1000,10000,100000,1000000, separately |
| Fresh OS / CPU / architecture / locale / encoding / power metadata | Not supplied in this message |
| Completion | Run complete; total time 00:00:44 |
| Profiler observations | None supplied; this is ordinary timing |

Earlier Windows 11 / Oracle / amd64 / es_AR / UTF-8 setup is contextual rather
than newly collected run metadata. The Windows-style invoker path is retained
literally. All five effective headers agree with the planned timing protocol;
this does not certify power, background load, payload or source identity.
The compiler-Blackhole advisory and general JMH warning accompanied the output;
no assembly, hardware counters or GC diagnosis was supplied.

## Source-attribution follow-up: author-supplied reflog

The author supplied the following output after the current `git rev-parse HEAD`
returned the results merge, and repeated a failed `Get-FileHash` attempt. No
reflog timestamps were included:

```text
12f2973 (HEAD -> main, origin/main, origin/HEAD) HEAD@{0}: pull --ff-only origin main: Fast-forward
5eee0dc HEAD@{1}: pull --ff-only origin main: Fast-forward
18a8ef5 HEAD@{2}: pull --ff-only origin main: Fast-forward
8bacc90 HEAD@{3}: pull --ff-only origin main: Fast-forward
d572651 HEAD@{4}: pull --ff-only origin main: Fast-forward
da3db88 HEAD@{5}: pull --ff-only origin main: Fast-forward
b62df1b HEAD@{6}: pull --ff-only origin main: Fast-forward
605c299 HEAD@{7}: pull --ff-only origin main: Fast-forward
```

`5eee0dc` expands to `5eee0dc47547a4713adb7a1a16d4316745bd8d99` (PR #93,
Benchmarking protocol); `12f2973` expands to
`12f2973034b4a0ed1f9a3a03e3e687af903a9c24` (PR #94, reported results).
The first is the most recent recorded checkout before the results pull.
The conversation places the reported measurement before creation/merge of
that results PR. This supports the preceding checkout as the likely execution
source; it is not an execution-time attestation or proof of which jar was used.

A source comparison confirms that the aggregation benchmark's executable Java
is identical between those two merges after removing comments/whitespace.
Production code, tests, fixture tests and POM also have no changes between them.
This establishes compatibility of the tracked source, not artifact identity.

The subsequent hash attempt failed at
`C:\Users\rousl\Documents\Algorithms-Java-Mastery\target\benchmarks.jar`:
Resolve-Path reported that the file did not exist. The cause of its absence was
not established. Therefore the original jar SHA-256 is **unavailable**, not
zero, and a newly built jar must not be presented as the old artifact.
Execution-time dirty state, build timestamp and exact command remain unknown.
No measurement is rerun or altered for this provenance follow-up.

## Result

Decimal commas in the pasted console are normalised to dots for these English
tables; three decimal places are preserved. Every row has mode **avgt**, unit
**ns/op**, **Cnt=10** measured iteration averages (five per fork), not ten
individual algorithm calls. Expected sums are untimed source-derived oracles.

| n | Expected sum in reviewed fixture | Mean (ns/op) | Error (ns/op, 99.9%) | Cnt |
|---:|---:|---:|---:|---:|
| 100 | 4950 | 22.231 | 2.427 | 10 |
| 1000 | 49500 | 249.323 | 13.697 | 10 |
| 10000 | 495000 | 2501.538 | 18.532 | 10 |
| 100000 | 4950000 | 25359.597 | 235.917 | 10 |
| 1000000 | 49500000 | 285257.728 | 7591.839 | 10 |

The following result-block statistics are preserved as printed, including
rounding that can make a displayed mean +/- displayed error differ by 0.001
from a separately rounded CI endpoint. No endpoint is replaced or recomputed
in the evidence table.

| n | Min (ns/op) | Max (ns/op) | Stdev (ns/op) | CI lower (ns/op, 99.9%) | CI upper (ns/op, 99.9%) |
|---:|---:|---:|---:|---:|---:|
| 100 | 19.174 | 24.169 | 1.605 | 19.804 | 24.658 |
| 1000 | 243.252 | 270.352 | 9.059 | 235.627 | 263.020 |
| 10000 | 2488.664 | 2521.675 | 12.258 | 2483.006 | 2520.070 |
| 100000 | 25186.460 | 25658.406 | 156.044 | 25123.680 | 25595.514 |
| 1000000 | 278731.459 | 292959.510 | 5021.532 | 277665.888 | 292849.567 |

## Retained fork and iteration observations

All entries below are **ns/op**. W denotes a warmup iteration average; M
denotes a measured iteration average. Fork order and within-fork order are
unchanged. No observation is deleted, pooled with an earlier dataset or
invented from a final mean.

### size=100

| Fork | W1 | W2 | W3 | M1 | M2 | M3 | M4 | M5 |
|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| 1 | 19.340 | 22.954 | 40.417 | 22.371 | 20.083 | 21.195 | 22.346 | 23.650 |
| 2 | 19.322 | 18.977 | 24.107 | 24.169 | 19.174 | 23.129 | 22.930 | 23.263 |

Fork starts: 0% / ETA 00:00:40, then 10% / ETA 00:00:40.

### size=1000

| Fork | W1 | W2 | W3 | M1 | M2 | M3 | M4 | M5 |
|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| 1 | 249.130 | 247.532 | 251.946 | 248.088 | 270.352 | 259.173 | 252.887 | 243.286 |
| 2 | 251.304 | 247.581 | 243.994 | 243.252 | 244.018 | 243.956 | 243.558 | 244.662 |

Fork starts: 20% / ETA 00:00:35, then 30% / ETA 00:00:31.

### size=10000

| Fork | W1 | W2 | W3 | M1 | M2 | M3 | M4 | M5 |
|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| 1 | 2559.148 | 2495.614 | 2485.083 | 2521.675 | 2513.712 | 2506.078 | 2493.935 | 2492.061 |
| 2 | 2544.874 | 2506.124 | 2478.058 | 2516.186 | 2489.080 | 2502.168 | 2488.664 | 2491.823 |

Fork starts: 40% / ETA 00:00:26, then 50% / ETA 00:00:22.

### size=100000

| Fork | W1 | W2 | W3 | M1 | M2 | M3 | M4 | M5 |
|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| 1 | 25807.677 | 25201.397 | 25379.743 | 25186.460 | 25518.167 | 25208.077 | 25496.778 | 25315.467 |
| 2 | 25685.899 | 25249.482 | 25437.681 | 25206.693 | 25308.153 | 25658.406 | 25300.093 | 25397.673 |

Fork starts: 60% / ETA 00:00:17, then 70% / ETA 00:00:13.

### size=1000000

| Fork | W1 | W2 | W3 | M1 | M2 | M3 | M4 | M5 |
|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| 1 | 295293.284 | 288248.485 | 288260.124 | 286802.910 | 289906.534 | 290096.763 | 292959.510 | 287610.376 |
| 2 | 283262.590 | 277267.836 | 279968.675 | 285384.013 | 280667.418 | 280306.601 | 278731.459 | 280111.696 |

Fork starts: 80% / ETA 00:00:08, then 90% / ETA 00:00:04.

## Numerical reconciliation and descriptive analysis

Independent recalculation from the fifty displayed measurement averages
matches all printed means, minima, maxima, sample standard deviations and
99.9% mean-CI half-widths/endpoints within **0.0012 ns/op**. The check uses a
two-sided Student-t critical value at df=9, the JMH 1.37 formula documented in
the guide; displayed iteration values themselves have limited precision.
This consistency check is not a new statistical comparison or a correction
to the supplied evidence. Warmup values are retained but excluded from scores.

| n | Error / mean (%) | Mean / n (ns/element) | Mean ratio to preceding size | Fork 1 mean (ns/op) | Fork 2 mean (ns/op) |
|---:|---:|---:|---:|---:|---:|
| 100 | 10.917 | 0.222310 | N/A | 21.9290 | 22.5330 |
| 1000 | 5.494 | 0.249323 | 11.215 | 254.7572 | 243.8892 |
| 10000 | 0.741 | 0.250154 | 10.033 | 2505.4922 | 2497.5842 |
| 100000 | 0.930 | 0.253596 | 10.138 | 25344.9898 | 25374.2036 |
| 1000000 | 2.661 | 0.285258 | 11.249 | 289475.2186 | 281040.2374 |

All five relative error widths are below the repository's 25% review flag;
the largest is **10.917%** at n=100. That heuristic does not establish
stationarity or certify the experiment. The n=100 first-fork warmup value
40.417 is retained. At n=1,000,000 the second fork has a lower mean
(281040.2374 versus 289475.2186 ns/op); the data do not identify its cause.

## Interpretation and limits

A tenfold increase in n corresponds to mean-time ratios **11.215, 10.033,
10.138 and 11.249**. The middle sizes have approximately 0.249..0.254
ns/element, rising to about 0.285 at one million. These finite observations
are compatible with a complete linear scan. They do not prove asymptotic
complexity, establish a fitted exponent, isolate cache/memory bandwidth or
attribute an exact runtime share to compilation or GC. No such profiler data
was supplied. There is one implementation here, so no algorithm ranking is made.

For the reviewed modulo-100 fixture, source reasoning gives n additions and
n element visits, Θ(n) timed work and Θ(1) auxiliary accumulator state. Trial
allocation/fill is excluded and the resident input is Θ(n). With nonnegative
values, every prefix stays below the final total, at most 49,500,000; that
supports the int-sum contract for the reviewed defaults. Those are source/test
claims, conditional on execution attribution, not values observed from timing.

The source checkout is now inferred from the supplied reflog and conversation
sequence. Execution-time dirty state, original artifact hash, actual command/start
time and fresh machine/power metadata remain unknown. The absent jar prevents
hashing that original artifact now; a rebuilt jar would have a new artifact
identity. Record subsequent runs separately and retain these limits. The five
observations are unchanged. A GC run would have a distinct identity and units. No raw
JSON/log/new CSV file is required; evidence is retained in Markdown.

The [local verification report](../../verification-report.md) retains the
710 normal / 723 profile test outcomes and separate smoke/compatibility work.
This author timing record does not imply a fresh test run or passing CI.
