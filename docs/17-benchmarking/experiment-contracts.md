# Benchmarking: Applied Experiment and Evidence Map

Reviewed on **2026-10-29**, against main
`93fb7ce12d1f24ef2a930dcc2b4d2bf187b654f8` (PR #114). The
[documentation review](documentation-review.md) records BE01-BE12, primary
sources and fresh Java checks. The [execution guide](implementation-and-benchmark-guide.md)
remains the Windows/Linux command reference. This map makes the selected
aggregation experiment explicit; registration of other benchmarks does not
certify every fixture or turn every default into a measured result.

## 1. Question, Contract and Operation

The question is average elapsed time for a full read-only aggregation as size
varies over five deterministic, reusable arrays on a recorded JVM. The
[production method](../../src/main/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregation.java)
requires a non-null array whose mathematical sum fits int, returns that sum and
preserves input. The fixture's size cap is a preparation policy, separate from
the production domain. Neither the API nor this experiment models a service
request, cold-cache access or per-element latency distribution.

| Stage | Actual work | Evidence / boundary |
|---|---|---|
| Trial preparation | Check size 0..1,000,000, allocate array, fill i%100 | Setup source; excluded from timed scan, but resident state can affect execution |
| Functional fixture checks | Range, long sum/formula, repeated results, preservation, invalid sizes, independence | 13 profile-only JUnit invocations; no time assertions |
| One benchmark invocation | Production full scan and Blackhole consumption | One operation under the selected default normalisation |
| Warmup | Repeated scans before measured iterations | Retained for trends; excluded from result statistics |
| Measured iteration | Harness repeats the benchmark and obtains an average | One ns/op observation, containing many calls |
| Summary | Aggregate the ten measured iteration averages per size | Mean and mean-interval error, not ten calls or per-call quantiles |

The [fixture](../../src/jmh/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregationBenchmark.java)
has thread-scoped state and trial setup. The selected command uses one worker
and two fresh forked JVMs per configuration. Each worker reuses its prepared
array within its trial. Fresh forks isolate JVM process state; they do not
reset the host's temperature, background load or all hardware state.

One operation is the complete scan. No OperationsPerInvocation override is
selected. Dividing the reported score by n gives a descriptive ns/element
normalisation, including amortised call/harness effects; it is not a measured
latency for an isolated addition. Changing -opi would change normalisation and
the reported operation's interpretation. Adding manual repetition can also
change optimisation; it is not a neutral way to remove overhead.

## 2. Preparation Proof and Tests

For n=100q+r, 0<=r<100, the exact sum is
`4950*q+r*(r-1)/2`. Preparation maintains that every written prefix element j
equals j%100; n-i decreases to zero. The aggregation accumulator represents
the sum of the processed prefix. These invariants establish the resulting
array and sum. Nonnegative entries bound every prefix by the full total,
at most 49,500,000, so the selected fixture avoids intermediate overflow.
For n=101 the sum is 4,950; n=103 gives 4,953. The default sums are
4,950 / 49,500 / 495,000 / 4,950,000 / 49,500,000.

This gives n source-level visits/additions, Theta(n) scan work and constant
accumulator state. Setup uses Theta(n) time/storage separately, and the input
remains resident. These are source cost-model statements; JIT transformations
mean they are not measured CPU instruction counts or an allocation profile.

The [fixture tests](../../src/jmh-test/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregationFixtureTest.java)
read five defaults from @Param, check four further valid sizes 0/1/99/103,
reject three unsupported sizes and check trial independence: 5+4+3+1=13.
Normal test scope is 710; the benchmark-profile package executes 723.
The [Testing map](../16-testing/test-contracts.md) defines the invocation and
ownership boundaries. Range plus aggregate sum does not check each position
against i%100: a permutation can preserve both. The setup loop supplies that
pointwise guarantee. Likewise, the source shows the guard before allocation;
the exception test alone does not measure allocation order or bytes.

## 3. Discovery, Parameters and Budget

The newly packaged JMH 1.37 jar exposes **40 classes / 115 methods**. Taking the
product of parameter value counts for each registered method gives **803
default parameter cells**:

| Package | JMH classes | Methods | Default cells |
|---|---:|---:|---:|
| arrays | 11 | 15 | 105 |
| backtracking | 4 | 8 | 46 |
| divideandconquer | 3 | 6 | 48 |
| dynamicprogramming | 3 | 9 | 57 |
| graphs | 2 | 6 | 32 |
| greedy | 3 | 5 | 40 |
| hashtables | 1 | 9 | 54 |
| linkedlists | 1 | 15 | 60 |
| recursion | 3 | 8 | 24 |
| searching | 3 | 4 | 65 |
| sorting | 2 | 8 | 160 |
| stacksandqueues | 2 | 10 | 40 |
| trees | 2 | 12 | 72 |
| **Total** | **40** | **115** | **803** |

This is registry/parameter inventory, not 803 executed or independently
validated experiments. The Sorting report's 48 historical cells and selected
aggregation's five cells are different scopes. Discovery without a filter is
safe to inspect; timing without an include selector can select the entire
registry. Budget and choose the intended subset before starting a run.

Use the anchored fully qualified aggregation selector from the guide. -l lists
methods; -lp also lists parameters; neither measures the algorithm. Building a
jar also supplies no timing. CLI options can override annotations; inspect the
effective header and retain the actual command. A no-match result requires
checking selector, checkout and successful packaging before interpretation.

For this selected five-cell protocol, each fork uses three warmup and five
measurement iterations of 500 ms: 5*2*(3+5)*0.5=40 seconds of nominal phases,
plus setup, startup and harness work. There are ten fork trials, 30 warmup
averages and 50 measured averages. JMH iteration duration is a requested minimum;
40 seconds is not a wall-clock promise or an operation count. The supplied
44-second completion remains an observation of that earlier run.

## 4. Mean, Error and Statistical Unit

For one worker in AverageTime, JMH forms duration/operation-count observations,
then averages measured iteration scores. Its RunResult aggregation includes
iterations from the forks. Here k=10 observations per size; warmup does not
enter the score. The 1.37 AVG-result error uses the 99.9% mean interval:
`error = t(0.9995, k-1) * sample_stdev / sqrt(k)`.
For k=10 the degrees of freedom are nine. That is an account of the harness
calculation, not proof that the statistical assumptions hold for this workload.

Fork grouping, temporal drift and shared machine conditions matter. Ten pooled
iteration scores must not be described as ten independent machines, ten method
calls or ten independent process replications. The design has two JVM forks
per size; retain each fork's sequence and mean when examining variability.
More iterations do not automatically remove shared bias or between-fork shifts.

The printed interval concerns the mean under its model, not the range of all
future calls or a p95/p99 request latency. An AverageTime summary also does not
supply a measured throughput distribution by taking the reciprocal of its
mean. Missing error remains unavailable; 1.37 cannot form this mean-error
estimate with two or fewer samples. A smoke score therefore does not fill the
research uncertainty field. Keep literal censored markers and printed rounding.

Relative error 100*error/mean is descriptive for positive finite means. The
repository's 25% flag is a prompt to investigate, not a deletion rule, passing
threshold or significance test. Overlap does not prove equivalence. A fitted
complexity or exact runtime cause does not follow from these few finite sizes.

## 5. Controls, Diagnostics and Comparability

Setup outside timing still determines the data layout and resident workload.
Repeated aggregation uses one deterministic value pattern; it does not sample
all arrays of each size. In
[efficient sorting](../../src/jmh/java/org/anaalvarezdev/algorithms/sorting/EfficientSortingBenchmark.java),
each measured method clones, and copyOnly measures that full clone control.
Subtracting separate means does not automatically isolate a sorting component
or propagate a valid interval. In
[knapsack](../../src/jmh/java/org/anaalvarezdev/algorithms/dynamicprogramming/KnapsackValueBenchmark.java),
TOO_HEAVY changes reachable/updated states while dense memo allocation/fill
remains timed. These controls answer their selected questions and contracts.

A future implementation-by-size-by-distribution comparison should predeclare
the complete selected matrix, matching outputs and timed boundaries, input
seeds, settings and repetition/order plan. Analyse all retained cells and
identify the changed factor. The present aggregation run has one implementation
and one deterministic preparation rule; it cannot identify a factorial effect
or an old-versus-new improvement across different payloads/VMs.

Blackhole consumption makes output observable; it is not a certificate that
the generated code matches every intended mechanism. Preserve the reported
mode and keep assembly/JIT investigations separate when they are warranted.
Warmup can trigger compilation but a fixed count does not establish stability.

The 1.37 GC profiler derives allocation rate from its observed bytes and time
window, and normalised allocation from bytes divided by operations. Preserve
its literal MB/sec and B/op labels; the rate implementation divides bytes by
1024 squared. Neither is retained heap or auxiliary-space complexity. A missing
secondary metric, unsupported counter or no GC event must not become an
invented zero. Profiler listing establishes availability only; it is not a
profiled measurement. Keep a diagnostic run's settings and scores separate
from ordinary timing, and avoid coverage instrumentation in the timing run.

## 6. Provenance and Evidence Status

| Evidence | What it supports | Remaining boundary |
|---|---|---|
| Passing fixture tests | Asserted functional properties at checked sizes | Not historical payload attestation or every fixture's correctness |
| Jar registry and -lp | Packaged benchmark/parameter discovery | No elapsed-time result |
| Complete console record | Printed effective settings, iterations, scores and completion | Payload, source and artifact need their own provenance |
| Git SHA and clean/dirty capture | Recorded source checkout/state | Not proof of the jar's bytes or invocation |
| SHA-256 of actual jar | Identity of those existing artifact bytes | Not a missing older jar or its execution by itself |
| Reflog sequence | Evidence supporting a checkout inference | Not build/run time, dirty state or artifact identity |
| CI package/discovery | Recorded hosted functional/package outcome | Not ordinary author performance or future PR status |

The [corrected aggregation record](results/arrays/aggregation-corrected-run.md)
retains all five scores/errors, both forks and the 80 printed iteration
averages. Its source is inferred as 5eee0dc from supplied reflog/context; the
original jar hash, execution-time dirty state, exact command/time and fresh
machine metadata remain unavailable. A freshly built jar cannot fill those
gaps. The old ascending-payload means keep their separate domain limitations.

Capture source/state and hash after a successful build, immediately before
execution. A subsequent clean deletes target, including jar and reports;
record needed evidence first. No new JSON/log/CSV export is necessary. The
[existing workflow](../../.github/workflows/maven-ci.yml) and
[dated hosted record](../19-ci-cd/first-hosted-run.md) supersede the original
workflow-gap status. This review's Java checks remain local; next is the
ordered review of **18 — Linux Environment**.
