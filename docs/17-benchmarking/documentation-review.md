# Benchmarking: Ordered Documentation Review

Reviewed on **2026-10-29**, America/Buenos_Aires, against main
**`93fb7ce12d1f24ef2a930dcc2b4d2bf187b654f8`**, PR #114 merging the Testing
documentation review. This closes the scoped **17 — Benchmarking documentation
review**; next is **18 — Linux Environment**, after integration.

## Scope and Conclusion

Read all ten numbered notes, the full module README, execution guide and
original verification report. Check the selected aggregation experiment against
its production method, actual JMH setup, profile-only fixture tests, Maven
configuration, existing corrected aggregation record and primary JMH 1.37
implementation. Representative Sorting clone controls and knapsack timed
boundaries are checked against their current benchmark bodies and the preceding
module reviews. Current workflow/hosted records supply later status, not a new
hosted result. Other datasets remain linked historical evidence; this review
does not re-audit every numerical table or certify every registered fixture.

The [experiment and evidence map](experiment-contracts.md) connects the valid
domain, preparation/scan invariants, source costs, actual operation, stages,
registry, statistics, controls, diagnostics and provenance. Sixteen Markdown
paths change: thirteen existing module files, the new map/review and project
traceability review. Java, tests, JMH sources, POM, workflow and all files under
results are unchanged. BE01-BE12 include corrections and explanatory limits,
not twelve new executable defects or a fresh controlled performance experiment.

## Findings and Applied Corrections

| ID | Finding / ambiguity | Applied correction and evidence |
|---|---|---|
| BE01 | Generic README wording promises reliable statistics or complete quality evaluation from the tools | Qualify representativeness and JMH's role, separate finite tests/proofs/timings, and make the reading progression follow experiment design before measurement |
| BE02 | Source visits/additions can be confused with measured CPU work | Keep the RAM-style scan ledger separate from machine instructions, elapsed-time causes and profiler events; retain setup/resident-input costs |
| BE03 | A full-scan operation can be mistaken for a single addition's latency | Define default operation normalisation, score/n as descriptive amortisation, and the effect of OperationsPerInvocation/manual repetitions; AverageTime supplies no per-call p95/p99 |
| BE04 | A 115-method registry can be read as 115 configurations or already validated measurements | Count 40 classes and 803 parameter cells from fresh -lp metadata, with a package inventory and explicit selected-subset budget |
| BE05 | Fixed warmup/forks and 40 nominal seconds can sound like stability or exact duration guarantees | Explain trial reuse, two process forks per size, requested minimum iteration time, ten fork trials and remaining host state |
| BE06 | Cnt, samples and mean error need a pinned implementation model | Trace AverageTimeResult, RunResult and AVG statistics: ten measured iteration scores, 99.9% Student-t half-width, df=9, warmup excluded |
| BE07 | A calculated interval can imply independence, per-call bounds or recovered missing errors | Retain fork grouping, temporal/host effects and unavailable small-sample errors; preserve rounding, literal markers and the descriptive 25% flag |
| BE08 | Fixture tests can imply a position-by-position payload or allocation proof | Separate aggregate range/formula assertions from setup source's i%100 pattern and guard-before-allocation order; retain 710/723 and 13 fixture scope |
| BE09 | Untimed setup and baselines can imply isolated component measurements | State resident-state effects, timed Sorting clones, non-subtractable controls and dense knapsack setup; require matching contracts and a predeclared factorial design |
| BE10 | GC rates, normalised allocation and profiler availability can be conflated | Check 1.37's byte/time/operation denominators and literal units; missing metrics remain unavailable, listing is not profiling and B/op is not retained heap |
| BE11 | Original verification still says the workflow gap remains open | Preserve that dated snapshot and link the existing workflow/hosted record; distinguish local package checks, author timings and future PR status |
| BE12 | A new build, hash or reconciliation could overwrite earlier provenance | Record fresh Java checks separately, preserve the author's inferred source/missing original jar identity, and reconcile rounded iterations without replacing printed observations |

Each note receives its relevant applied clarification and map/review links.
The README's general claims and progression are corrected; the guide clarifies
operation, fixture, discovery, budget, statistics and artifact capture. The
original report is explicitly dated and gains a separate follow-up. No new
benchmark method, dependency, fixture payload or timing threshold is introduced.

## Primary Sources and Derivations

Inspected **2026-10-29**, from the official OpenJDK repository's **1.37** tag,
resolved to **`2effa2c8310e1d3ad03c8ee02024edca9252b46a`**:

- [CommandLineOptions](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/runner/options/CommandLineOptions.java): selection, parameter/normalisation overrides, minimum iteration time and discovery options.
- [AverageTimeResult](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/results/AverageTimeResult.java), [BenchmarkResult](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/results/BenchmarkResult.java) and [RunResult](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/results/RunResult.java): duration/operations, iteration averaging and merging the forks' iterations.
- [AbstractStatistics](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/util/AbstractStatistics.java) and [Result](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/results/Result.java): sample deviation, Student-t interval, unavailable error for at most two samples, and AVG's 0.999 level.
- [GCProfiler](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/profile/GCProfiler.java): observed allocation deltas, time/operation normalisation, 1024-squared rate conversion and optional secondary metrics.
- [OperationsPerInvocation](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/annotations/OperationsPerInvocation.java), [Warmup](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/annotations/Warmup.java) and [Setup](https://github.com/openjdk/jmh/blob/1.37/jmh-core/src/main/java/org/openjdk/jmh/annotations/Setup.java): normalisation, overridable defaults and state fixtures.
- Official samples [08 — DeadCode](https://github.com/openjdk/jmh/blob/1.37/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_08_DeadCode.java) and [11 — Loops](https://github.com/openjdk/jmh/blob/1.37/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_11_Loops.java): observability and optimisation changes under manual repetition.

The supplied CLRS **third edition (2009)** was checked in selected text passages:
printed pp.18-19 (PDF pp.39-40), loop reasoning; pp.25-26 (PDF pp.46-47), input
size/constant-cost model; pp.43-45 (PDF pp.64-66), asymptotic growth/notation.
These support algorithmic reasoning, not JMH APIs, a CPU prediction or a timing
proof. No fresh visual figure inspection or fourth-edition pagination is claimed.
The fixture formula, prefix bound, phase budget and parameter products are
derivations from the reviewed repository and discovered metadata.

## Fresh Java 21 Verification

An isolated worktree uses unchanged executable sources from the base above.
Runtime/compiler is **Amazon Corretto 21.0.13.0.11**, build **21.0.13+11-LTS**,
Linux amd64, UTF-8; Maven Wrapper **3.9.11**, compiler plugin **3.14.0** with
release 21, Surefire **3.2.5**, Jupiter **5.12.2**, AssertJ **3.27.3**, JMH
**1.37**. This is distinct from the author's Oracle 21.0.9 Windows run.

| Actual verification | Outcome |
|---|---|
| bash mvnw -o --batch-mode --no-transfer-progress -Pbenchmark -DfailIfNoTests=true clean package | BUILD SUCCESS; 723 tests; zero failures/errors/skips; executable jar |
| Surefire XML scope | 13 actual-fixture invocations included in 723; normal source scope remains 710 |
| Jar -l and full -lp | 115 methods, 40 benchmark classes, 803 default parameter cells |
| Anchored aggregation -lp | Exactly one method; five default sizes |
| Full controlled command with -lp appended | All explicit options accepted; same five sizes; no timed run |
| Jar -h and -lprof | Pinned CLI help inspected; gc listed supported in this local environment |
| Jar contents | Benchmark registry present; ArrayAggregationFixtureTest.class excluded |
| Java rounded-record reconciliation | Five rows/50 measured samples; summaries and fork means agree within printed precision |

JAVA_HOME explicitly selects the stated JDK; MAVEN_OPTS selects the standard
Java trust store. Packaging finished **09:18:27 -03:00**, duration **21.092 s**.
This is build duration, not algorithm latency. The shade manifest-overlap warning
did not prevent success. No additional normal/coverage build is claimed; the
723 invocation profile executes the normal tests plus its thirteen fixture
invocations. Those outcomes are not new distinct tests across repeated reviews.

The registry inventory and arithmetic reconciliation run in a temporary Java
21 source program, outside versioned files. It reads full -lp output, multiplies
each method's parameter counts and checks class/method totals. For the existing
aggregation Markdown it reads the two forks' five measurements per size and
uses the jar's pinned JMH ListStatistics. All printed mean/error, min/max,
stdev and CI endpoints reconcile within **0.000590595 ns/op** of the rounded
inputs (less than 0.000591). This checks consistency through the same statistics
implementation, not an independent statistical model or a new measurement.
All ten fork means agree with the recorded four-decimal values. The 30 warmup
observations stay outside score statistics and remain unchanged in the record.

The freshly built local jar SHA-256 is
`92ff7da529c3162b3530ff9470fc95a48b997a5f1ccb7fbec0c9a84824c05fd7`.
It identifies this verification artifact only. It does not recover the absent
original author artifact or prove a new timed experiment. Discovery/CLI parsing,
Java reconciliation and profiler availability are not a fresh smoke, profiled
run, assembly inspection, all-fixture probe, coverage percentage or hosted CI
status. The old verification report's smokes and GC compatibility keep their dates.

## Final Audit and Preserved Evidence

The closing audit checks **315 Markdown files / 1596 relative file targets**,
with zero missing destinations, balanced fences and passing `git diff --check`.
All sixteen changed paths are Markdown within the declared scope. No new local
heading-fragment link is introduced; external URLs and inherited fragments are
not exhaustively validated. Inventory remains **100 production Java files /
115 JMH methods**. All results files remain byte-for-byte unchanged. Generated
jar/reports and temporary Java checks stay outside versioned evidence; no new
JSON, CSV, log or binary file is included.

The [corrected aggregation record](results/arrays/aggregation-corrected-run.md)
retains five means/errors, all 50 measured and 30 warmup averages, JDK 21.0.9 /
JMH 1.37 headers and 44-second completion. Source remains reflog-inferred as
5eee0dc, while original jar hash, execution-time dirty state, exact command/time
and fresh machine metadata remain unavailable. No old score, marker, error,
outlier or limitation is removed. Original ascending-array measurements keep
their two large-input int-sum violations and unknown original errors.

Other module reports retain their source, VM, units, subsets and uncertainty;
in particular Sorting's JDK 25 evidence is not pooled with Java 21. The
[initial hosted record](../19-ci-cd/first-hosted-run.md) supersedes the old
workflow-definition gap without attesting this review's future PR. This closes
only the ordered Benchmarking documentation scope, ready for Linux next.
