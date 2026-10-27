# Benchmarking: Applied Protocol and Evidence Guide

Reviewed on **2026-10-27**, from main
`18a8ef53d4a1c4f1cd58c9caf22c72051be6b891` (PR #92, Testing).
This is cross-cutting module **17**. It applies a controlled protocol to an
existing algorithm; it does not invent another production algorithm or replace
previous modules' measurements. Next numbered subject: **18 — Linux**.

## Question, contract and workload

How does a complete, read-only integer aggregation scale over five bounded
input sizes on one stated Java 21 environment? The production contract is in
[ArrayAggregation](../../src/main/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregation.java):
non-null int array, mathematical sum fits int, exact sum returned, input unchanged.

The [actual JMH fixture](../../src/jmh/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregationBenchmark.java)
accepts sizes 0..1,000,000 and constructs `value[i] = i % 100`. Timing defaults
use only the five rows below. Invalid size overrides fail before allocation.
These bounds describe this fixture, not a new restriction on the production API.

| n | Expected mathematical sum | Status of corrected timing |
|---:|---:|---|
| 100 | 4,950 | Pending author run |
| 1,000 | 49,500 | Pending author run |
| 10,000 | 495,000 | Pending author run |
| 100,000 | 4,950,000 | Pending author run |
| 1,000,000 | 49,500,000 | Pending author run |

The original ascending fixture and its five means remain in the
[historical Arrays report](results/arrays/README.md). At the two largest sizes
its mathematical sums exceed int. The corrected payload was introduced by
Testing; this module changes its fork default **from one to two**. Historical
means are not measurements of this payload/protocol. The
[new record](results/arrays/aggregation-corrected-run.md) remains pending.

## Strategy, pseudocode, trace and correctness

Preparation and measurement are distinct stages:

```text
PREPARE(n): reject n outside [0,1000000]; allocate A[n]
            for i in [0,n): A[i] = i mod 100
ONE_MEASURED_OPERATION(A): result = ArrayAggregation.sum(A)
                           consume result with JMH Blackhole
```

For n=101, one full 0..99 cycle totals 4,950 and the final value is 0, so the
sum is 4,950. For n=103 the remainder 0,1,2 raises the total to 4,953.
In general, with q=floor(n/100), r=n mod 100:

`expected(n) = 4950*q + r*(r-1)/2` (evaluate in long for verification).

Preparation invariant: before writing i, every index j<i contains j mod 100.
The loop writes the required next value and increments i; n-i decreases to
zero. This establishes the complete fixture. Aggregation invariant: before
iteration i, the accumulator equals the sum of A[0..i). Adding A[i] preserves
it and termination at n yields the full sum. Values are nonnegative, so every
prefix is bounded by the final total, at most 49,500,000 within this domain;
no intermediate int overflow occurs. The scan writes no array element.

Timed source work is Θ(n) time and Θ(1) auxiliary state. Trial preparation is
Θ(n) time and storage, excluded from the score. The resident input itself is
Θ(n). A finite timing series can be consistent with this argument; it cannot
prove it or identify a specific CPU mechanism.

## Tests and timed boundary

[ArrayAggregationFixtureTest](../../src/jmh-test/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregationFixtureTest.java)
is active only under `-Pbenchmark`. Five cases read sizes from the actual
`@Param`; four cases cover 0/1/99/103, three reject unsupported sizes, and one
checks fresh independent trial arrays: **13 invocations**. Reflection checks
real private fixture fields without adding production accessors. Long-stream
and closed-form sums must agree; three production calls must agree and preserve
the input. These are functional checks, with no elapsed-time assertions.

Normal tests remain **710**; the profile runs **723**, including these thirteen.
The profile registers `src/jmh-test/java` at generate-test-sources, so its tests
are compiled as tests and excluded from the executable jar. The
[verification report](verification-report.md) records observed outcomes.

Each JMH invocation includes the production scan and Blackhole consumption.
Trial setup, oracles, assertions, git commands and hashing are outside timing.
One worker reuses its prepared array across invocations; no cold-cache, fresh
request, concurrent or distribution-wide conclusion follows. Keep JaCoCo and
other agents out of the ordinary timing run. Run coverage in a separate clean
build when needed; never combine `-Pcoverage,benchmark` for this protocol.

## Windows execution and provenance

Run from the repository root after merging the PR and pulling main. Commands
print to the console; they request no JSON, log or CSV exports. Stop on any failed
build or benchmark before interpreting a leftover artifact.

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
& 'C:\Program Files\Java\jdk-21\bin\java.exe' -XshowSettings:properties -version
.\mvnw.cmd -Pbenchmark clean package
if ($LASTEXITCODE -ne 0) { throw 'Benchmark build failed' }
```

Maven and the explicitly selected executable must both report Java 21. Compiler
release=21 does not select the runtime. Existing environment variables may add
VM agents/options; inspect and retain their actual values privately as needed
before sharing, along with the effective JMH header.

Capture provenance **after the successful build and immediately before running**:

```powershell
Get-Date -Format o
git rev-parse HEAD
git status --short
Get-FileHash .\target\benchmarks.jar -Algorithm SHA256
$env:JAVA_HOME
$env:JAVA_TOOL_OPTIONS
$env:JDK_JAVA_OPTIONS
$env:_JAVA_OPTIONS
[System.Runtime.InteropServices.RuntimeInformation]::OSDescription
[System.Runtime.InteropServices.RuntimeInformation]::OSArchitecture
(Get-CimInstance Win32_Processor).Name
Get-Culture
powercfg /getactivescheme
```

An empty `git status --short` denotes a clean checkout; retain that fact explicitly
when transcribing. A SHA labels source, while jar SHA-256 identifies artifact
bytes; neither proves the other was actually executed without the run record.
Do not edit code or rebuild the jar between capture and execution. Record power,
background load and thermal conditions; do not claim they were controlled when
unknown. The timestamp is captured from the author's machine, not inferred from
when the evidence is received.

Discover the **one exact method** and its parameters:

```powershell
& 'C:\Program Files\Java\jdk-21\bin\java.exe' -jar .\target\benchmarks.jar '^org\.anaalvarezdev\.algorithms\.arrays\.ArrayAggregationBenchmark\.sum$' -lp
```

Expected output includes `org.anaalvarezdev.algorithms.arrays.ArrayAggregationBenchmark.sum`
and all five size values. If no method matches, inspect `-l` without a filter,
the checkout and the successful build. Building a jar is not a JMH execution.

Optional smoke (all five sizes, **not research**):

```powershell
& 'C:\Program Files\Java\jdk-21\bin\java.exe' -jar .\target\benchmarks.jar '^org\.anaalvarezdev\.algorithms\.arrays\.ArrayAggregationBenchmark\.sum$' -wi 1 -i 1 -w 50ms -r 50ms -f 1 -t 1 -foe true
```

Controlled author measurement, with all defaults made explicit:

```powershell
& 'C:\Program Files\Java\jdk-21\bin\java.exe' -jar .\target\benchmarks.jar '^org\.anaalvarezdev\.algorithms\.arrays\.ArrayAggregationBenchmark\.sum$' -p size=100,1000,10000,100000,1000000 -bm avgt -tu ns -wi 3 -i 5 -w 500ms -r 500ms -f 2 -t 1 -foe true
```

This is five configurations, fifty measured and thirty warmup iteration
averages. The nominal timed phases total forty seconds, plus setup/startup.
Each iteration invokes the algorithm many times; Cnt=10 per row is not ten
individual algorithm calls. Retain the entire console: JMH/VM/options/Blackhole
headers, parameters, both forks, warmup and measurement iterations, result
blocks, run-complete marker/duration and final table. Copy text into the
conversation; a cropped final-table screenshot cannot recover omitted values.

On Linux, use `./mvnw -Pbenchmark clean package` and the explicit path to a Java 21
executable with the same jar selector/options. Local verification used
`bash mvnw` because the tracked wrapper lacks executable permission. OS changes
require a separately attributed run; Windows and Linux data are not pooled.

## Separate allocation diagnostic

First list available profilers:

```powershell
& 'C:\Program Files\Java\jdk-21\bin\java.exe' -jar .\target\benchmarks.jar -lprof
```

If `gc` is supported, run the same controlled matrix with `-prof gc` appended.
Keep it separate from ordinary timing, retain primary/secondary metrics and
units, and record unsupported metrics explicitly. B/op is normalised allocation,
not retained memory or an asymptotic-space proof. No profiler result is yet
recorded as author research. Local GC smoke only checks compatibility.

## Recording and interpretation rules

Use the pending record for the **new** run; update this benchmark's Result and
Interpretation only from reconciled evidence. Preserve all observed values and
literal unavailable/censored markers. Retain ns/op; expected sums are correctness
oracles, not observed return values printed by JMH. Describe drift, fork variation
and wide errors before ranking or explaining growth.

The printed ± value is the mean CI half-width at the stated level, not stdev or
a per-call bound. A relative-width flag of 25% is only a review heuristic.
Overlapping intervals do not prove equal performance. Cache/JIT/GC explanations
remain hypotheses without suitable diagnostics. Source operation counts,
functional coverage, elapsed time and profiler observations are separate claims.

Prior reports retain their exact source, runtime and limitations. See the
[module index](README.md) for all existing datasets and each algorithm module's
own timed-work contract. Sorting's copy baseline is not automatically subtractable;
DP memoisation includes dense state setup even when few states are reached.
No new JSON/log/CSV files, arbitrary coverage gates or CI claims are introduced.

## Primary references

- Supplied CLRS, *Introduction to Algorithms*, third edition (2009), Chapters 2–3:
  loop reasoning and growth models. It is not an authority for JMH APIs.
- [OpenJDK JMH](https://openjdk.org/projects/code-tools/jmh/), pinned **1.37** in this project.
- [JMH 1.37 command-line options](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/runner/options/CommandLineOptions.java): selectors, parameters, forks, profilers and overrides.
- [JMH 1.37 statistics](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/util/AbstractStatistics.java) and [Result](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/results/Result.java): mean errors and confidence level.
- [JMH 1.37 GC profiler](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/profile/GCProfiler.java): secondary metrics and normalisation.
- Official 1.37 samples: [dead code](https://github.com/openjdk/jmh/blob/1.37/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_08_DeadCode.java), [constant folding](https://github.com/openjdk/jmh/blob/1.37/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_10_ConstantFold.java), [loops](https://github.com/openjdk/jmh/blob/1.37/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_11_Loops.java), [invocation setup](https://github.com/openjdk/jmh/blob/1.37/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_38_PerInvokeSetup.java).

Sources were checked against the official 1.37 source tag, commit
`2effa2c8310e1d3ad03c8ee02024edca9252b46a`. Newer documentation need not describe
this pinned implementation exactly.
