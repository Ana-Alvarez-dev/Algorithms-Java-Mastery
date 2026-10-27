# Benchmarking: Implementation and Local Verification

Verified on **2026-10-27** from main
`18a8ef53d4a1c4f1cd58c9caf22c72051be6b891` (PR #92, Testing).
This identifies the starting source; the contribution changes the files described
below. A future author-run SHA must identify the later merged checkout explicitly.

## Scope and traceability

The [applied guide](implementation-and-benchmark-guide.md) defines the question,
contract, bounded data, preparation pseudocode, manual trace, invariants, cost
model, tests, execution and interpretation. The ten numbered notes were revised
from generic surveys into applied explanations with explicit workload boundaries
and references to existing experiments. The module index, project README and
traceability audit link the concrete evidence.

No production algorithm or new JMH method is added. The existing aggregation
benchmark changes its fork default from **one to two**. Its production call,
Blackhole consumption and modulo-100 trial preparation are unchanged. After
removing comments and normalising whitespace, its executable source differs
from the starting snapshot only at `@Fork(2)`.

The benchmark Maven profile adds `src/jmh-test/java` at generate-test-sources.
The normal source roots and coverage profile remain unchanged. Test classes
are not included in `benchmarks.jar`. There are still **40 JMH classes / 115
methods**. The separate aggregation record preserves author output without replacing historical Arrays data.
No historical numerical table or CSV is replaced. No JSON/log/new CSV is versioned.

## Verification environment

| Component | Local verification environment |
|---|---|
| Runtime | OpenJDK 21, build 21+35-2513, 64-bit Server VM |
| Build | Maven Wrapper 3.9.11 |
| Test runner | Maven Surefire 3.2.5 |
| Test libraries | JUnit Jupiter 5.12.2, AssertJ 3.27.3 |
| Harness | JMH 1.37 |
| Platform | Linux amd64 container |

This environment is distinct from the author's Windows 11 / Oracle 21.0.9
machine. Local timing output is deliberately not used as research evidence.
Dependency transport used temporary workspace settings, without adding repository
network configuration. The local verification JVM disabled its perf-data file
to avoid PID-namespace collisions; author commands do not silently inherit that
local workaround. Preserve actual effective options for every real run.

## Functional and packaging checks

| Check | Observed outcome |
|---|---|
| Normal clean test suite | 710 tests, zero failures/errors/skips |
| Benchmark-profile clean package | 723 tests, zero failures/errors/skips; executable jar created |
| New actual-fixture suite | 13 invocations pass |
| Full jar discovery | 115 methods, unchanged inventory |
| Anchored aggregation discovery / parameters | One method, five sizes |
| Benchmark jar content | Fixture test class excluded |
| Aggregation smoke | All five sizes complete; one warmup and one measurement of 50 ms, one fork/thread |
| GC-profiler compatibility | Listed as available; one size-100 smoke completes with secondary output |
| Markdown / Maven / change scope | Relative links, fences, whitespace, profile isolation and allowed paths checked |

The thirteen added invocations comprise five sizes obtained from the real
`@Param` annotation, four valid non-default sizes (0, 1, 99, 103), three invalid
sizes (-1, 1,000,001, Integer.MAX_VALUE) and one trial-isolation check.
Long-stream sums must equal the independent cycle formula. Values must stay in
0..99, totals fit int, and three calls per valid case must return the expected
sum without changing input. Preservation uses a linear `Arrays.equals` check
on large arrays. Independent/reinitialised trials must have distinct arrays.
Reflection accesses the actual private fixture; no production accessor is added.
Assertions, cloning and oracles are untimed.

Normal and benchmark-profile counts are intentionally different: **710 + 13 =
723**. These are executed JUnit invocations, not assertions, operations per
iteration or additional production classes. Discovery/smoke checks exercise
packaging and invocation; they do not establish statistical stability.

## Commands checked locally

Builds used Java 21 and the wrapper, with the ordinary or benchmark profile
respectively. Discovery and smoke used the newly packaged jar with the exact
fully qualified selector in the [guide](implementation-and-benchmark-guide.md).
JMH accepted `-lp`, the explicit average-time/nanosecond options, size overrides,
failure handling and `-prof gc`. The five-cell smoke preserves one full scan per
invocation and completes without benchmark errors. The ordinary controlled
3/5 x 500 ms, two-fork command was prepared for the author; the subsequent
reported execution is recorded separately below.

All short-run timings and profiler values stay outside versioned evidence.
No author measurement is inferred from those compatibility checks. At that
local review the author-run record was pending; the follow-up below records
subsequently supplied observations while retaining source-attribution limits.

## Limits and next step

Tests check fixture correctness; they do not validate every existing benchmark.
In particular, this contribution does not silently close Searching S02–S08,
repair historical Arrays overflow measurements or supply missing original
runtime/errors. It does not add passing GitHub Actions: the workflow gap remains.
Coverage is not timing, and source counts are not CPU or allocation attribution.

After merging, the author clean-builds on Java 21, captures source/dirty state,
jar SHA-256, timestamp and environment, then runs the guide's controlled command.
The author subsequently supplied complete headers, forks/iterations, result
blocks, completion and final table, now retained in the
[aggregation record](results/arrays/aggregation-corrected-run.md) and the JMH
Result/Interpretation fields. The later supplied reflog supports inferred
source attribution; the original artifact hash is unavailable. A profiled execution is separately attributed. Next numbered subject is
**18 — Linux Environment**.

## Author-output follow-up: 2026-10-27

The five supplied rows retain 50 measured and 30 warmup averages; headers confirm
JDK 21.0.9/JMH 1.37, 3/5 x 500 ms, two forks, one thread, compiler Blackholes and
no VM options. Completion is 00:00:44. Independent numerical checks reconcile
means, extrema, sample stdev and 99.9% CI values within 0.0012 ns/op of the
printed values. No supplied observation is discarded. No relative error reaches
25%; the maximum is 10.917%. Mean growth is compatible with linear source work
without establishing an isolated hardware cause or old-versus-new improvement.

The subsequently supplied eight-entry reflog shows `5eee0dc` immediately before
results merge `12f2973`. Combined with the conversation sequence, it supports an
inferred execution source of `5eee0dc`, without proving the build, exact jar or
execution-time dirty state. Expected sums refer to that reviewed modulo-100
fixture; JMH did not print actual sums/payloads. The author's subsequent hash
attempt failed because target/benchmarks.jar is absent; the original SHA-256 is
unavailable. Exact command/start time and fresh machine metadata remain unknown.
The full supplied reflog and inference limits are retained in the result record.
The follow-up changes only Markdown and the benchmark's research comment;
executable Java, tests and POM are unchanged, so the 710/723 outcomes above
remain prior verification rather than a newly claimed test execution.
