# Testing: Baseline, Regression and Verification Report

**Historical experiment:** the baseline/red/green observations below were
recorded on 2026-10-27. The [2026-10-29 ordered review](documentation-review.md)
records fresh 710/723 Java executions and coverage separately. Subsequent
[aggregation results](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
and [hosted CI evidence](../19-ci-cd/first-hosted-run.md) supersede the pending
items in this dated snapshot; the original counters, hashes and failures remain
unchanged.

Verification date: **2026-10-27**, America/Buenos_Aires. Base main is
**`8bacc90f00e4ad7dc998743d06be65ff7f5c04aa`**, PR #91 integrating DP research.
This is local functional/coverage evidence, not a user performance run or CI.
The [guide](implementation-and-verification-guide.md) connects all eleven notes
to contracts, partitions, pseudocode/traces, proof, costs, fixtures and commands.

## Source, Runtime and Scope

| Property | Recorded verification |
|---|---|
| Compiler/runtime | OpenJDK 21+35-2513, OpenJDK 64-Bit Server VM |
| Build | Maven Wrapper 3.9.11; Surefire 3.2.5 |
| Test/assertion libraries | Existing Jupiter 5.12.2 and AssertJ 3.27.3 |
| Coverage | Optional JaCoCo Maven plugin 0.8.13, prepare-agent and verify report |
| Environment | Linux amd64 in the verification workspace; not the author's Windows benchmark environment |
| Baseline production/tests | Identical to base commit; POM coverage configuration added before the run |
| Corrected production | ClosestValueSearch logic; SearchInsertPosition JavaDoc markup only |
| New test sources | ClosestValueSearchRegressionTest and test-only ClosestSearchCaseBuilder |
| Benchmark change | ArrayAggregationBenchmark trial payload and input guard; no new JMH method |
| Coverage denominator | 135 production classes analysed in both clean coverage-only builds; no package exclusion or percentage gate |
| Generated reports | HTML/XML and execution data under ignored target; concise evidence versioned here |

No JMH-generated classes are included in these clean coverage-only scopes.
Code, tests and configuration are identified separately; both production source
and the test suite change between snapshots. XML hashes below identify local
generated artifacts, not a commit, jar or universal coverage baseline.

## 1. Green Baseline Before New Regressions

With the optional profile added and original production/tests unchanged:

```powershell
.\mvnw.cmd -Pcoverage clean verify
```

The baseline passes **694 tests**, zero failures/errors/skips. Searching has
85 invocations, including twelve existing ClosestValueSearch tests. JaCoCo
reports **100% instruction, branch and line coverage** for the old nearest-value
method, despite its known duplicate-index defect. The counter table makes that
claim inspectable rather than assuming high coverage from a green build.

## 2. Red Regression Against the Old Implementation

Add the new test class/builder, leaving production unchanged, then run:

```powershell
.\mvnw.cmd '-Dtest=ClosestValueSearchRegressionTest#returnsLowestIndexAcrossContractPartitions' test
```

This runs **12 parameterised invocations: six failures, zero errors/skips**.
It is an intentional observed regression failure, not a test left failing in
the published contribution. Each row below corresponds to an actual diagnostic:

| Case | Input / target | Expected index | Old index |
|---|---|---:|---:|
| Exact duplicates | [10,20,20,20,30] / 20 | 1 | 2 |
| Repeated lower neighbour | [10,20,20,40] / 25 | 1 | 2 |
| Repeated maximum | [10,20,20] / 100 | 1 | 2 |
| Tied repeated groups | [0,0,10,10] / 5 | 0 | 1 |
| All equal | [7,7,7,7] / 8 | 0 | 3 |
| Negative repeated tie | [-10,-10,-4,-4] / -7 | 0 | 1 |

The other six named cases already pass. A regression suite can distinguish the
faulty partitions rather than asserting that every new test must fail initially.
The exact index matters; a distance-only assertion would accept these wrong
later copies and fail to enforce the documented tie policy.

## 3. Corrected Implementation and Full Green Suite

ClosestValueSearch reuses the first-insertion-position lower bound. It returns
zero for target at/below the minimum; otherwise it compares adjacent boundary
values in long and resolves a winning lower/max value to its first occurrence.
Empty input stays -1 and null keeps its explicit exception/message. Sortedness
remains a caller precondition, not a new linear validation pass. The proof and
complexity are in the guide: minimum (distance,index), constant fast paths,
worst Theta(log(n+1)), O(1) auxiliary state, unchanged input.

| Verification | Tests | Failures / errors / skipped | Outcome |
|---|---:|---|---|
| Normal clean test | 710 | 0 / 0 / 0 | BUILD SUCCESS |
| Benchmark-profile clean package | 710 | 0 / 0 / 0 | BUILD SUCCESS, executable benchmark jar |
| Coverage-profile clean verify | 710 | 0 / 0 / 0 | BUILD SUCCESS, HTML/XML report |

Searching now contributes **101 tests**, its original 85 plus **16 new
invocations**. The new class contains one parameterised source method with
twelve invocations and four ordinary tests. It is not sixteen new source
methods or a second Testing package that should be added again to the total.

| New evidence | Domain / independence |
|---|---|
| Named cases | Twelve visible partitions including duplicates, extremes, singleton, empty and tied groups |
| Exhaustive oracle | 792 nondecreasing multisets, lengths 0..7 over {-2,-1,0,1,2}; nine targets -4..4 plus MIN/MAX = 8,712 queries; linear long-distance argmin with strict incumbent replacement |
| Seeded oracle | 300 arrays, lengths 0..129, eight-value full-int pool including MIN/MAX, duplicates; seed 20261027; random/extreme/exact targets, preservation |
| Metamorphic | 45 bounded targets with +1000 translation and positive x3 scaling; index preserved without int overflow |
| Isolation | Caller array, exposed copy and reused builder changes do not mutate an already built case |

The existing focused null test remains rather than being copied for style.
Neither oracle nor builder calls production to construct an expected answer.
Exhaustive loops have declared finite domains and are not a proof for all int
arrays. No automatic shrinking/property-generator/mutation library is claimed.

## 4. Actual Coverage Counters

Entries show **covered / total (percentage)**. Counter meanings and filtering
are JaCoCo's, not source statement/assertion counts. Each XML snapshot analyses
135 production classes. There is no excluded package to improve the result.

### Repository Scope

| Counter | Baseline | Corrected |
|---|---:|---:|
| INSTRUCTION | 12,649 / 12,764 (99.10%) | 12,631 / 12,746 (99.10%) |
| BRANCH | 1,517 / 1,624 (93.41%) | 1,511 / 1,618 (93.39%) |
| LINE | 2,172 / 2,202 (98.64%) | 2,163 / 2,193 (98.63%) |
| COMPLEXITY | 1,184 / 1,301 (91.01%) | 1,181 / 1,298 (90.99%) |
| METHOD | 478 / 488 (97.95%) | 478 / 488 (97.95%) |
| CLASS | 135 / 135 (100.00%) | 135 / 135 (100.00%) |

### ClosestValueSearch.search

| Counter | Old implementation | Corrected implementation |
|---|---:|---:|
| INSTRUCTION | 85 / 85 (100.00%) | 67 / 67 (100.00%) |
| BRANCH | 14 / 14 (100.00%) | 8 / 8 (100.00%) |
| LINE | 21 / 21 (100.00%) | 12 / 12 (100.00%) |

The old method was already fully covered by these metrics while missing valid
duplicate partitions. New regressions and the independent oracle make the
behavioural distinction. The implementation now delegates lower bounds, so
its bytecode/control-flow denominator changes. A lower or higher repository
percentage does not isolate test improvement or a performance change.

Coverage indicates execution, not all data combinations, paths, assertion
adequacy or correctness. JaCoCo's branch definition does not include exception
handling; exception contracts still need explicit tests. No mutation score,
MC/DC, full path coverage or universal quality threshold is inferred. The
remaining missed counters stay visible rather than adding tautological tests
solely to force a percentage target.

## 5. Corrected Aggregation Fixture, Separate From Historical Timing

The old ascending 0..n-1 fixture violates ArrayAggregation's int-sum contract at
100,000 and 1,000,000. The production sum method is unchanged. Future trial data
is i%100 with an explicit size range 0..1,000,000. At defaults:

| Size | Long reference / exact formula / returned int |
|---:|---:|
| 100 | 4,950 |
| 1,000 | 49,500 |
| 10,000 | 495,000 |
| 100,000 | 4,950,000 |
| 1,000,000 | 49,500,000 |

An independent probe reads each trial array, checks the pattern/size, computes
a long stream sum and formula 4950*floor(n/100)+r(r-1)/2, then checks three
algorithm calls and input preservation. Invalid sizes -1,1,000,001,MAX_INT are
rejected before allocation. All fifteen default calls match and preserve input.
Every prefix is within the int domain.

Five short JMH cells complete with one 50 ms warmup/measurement, one fork and
one thread, in ns/op, on OpenJDK 21/JMH 1.37. Their scores are deliberately not
transcribed as research. No new benchmark family or closest-value performance
question is introduced. The [historical Arrays report](../17-benchmarking/results/arrays/README.md)
retains its original five aggregation means, missing metadata and invalid
two largest inputs. Corrected Result/Interpretation await a separate controlled
author run; changing the fixture does not retroactively repair that dataset.

Coverage-only and benchmark runs are separate clean builds. Instrumented test
duration is not algorithm performance evidence. No cache/GC/JIT cause, speedup
or fitted exponent is claimed by these functional checks.

## 6. Documentation, Provenance and Limits

All eleven study notes link the executable guide/report. Searching S01 is now
marked corrected in current status while its historical counterexamples remain.
Other Searching review items are not silently marked resolved. The old Arrays
measurement limitation remains, though future fixture-domain construction is
corrected. The existing CI workflow gap remains open; local checks are not CI.
Searching JavaDoc passes doclint excluding missing tags, including repaired
comparison markup in the reused lower-bound helper without logic changes.

No user-controlled timed run is created by this contribution. Fresh runs need
their own source/runtime/settings and all errors. Compiler/tool versions,
source/suite scope and counter definitions constrain coverage comparisons.
The next module is **17 - Benchmarking**, with these fixture and evidence
distinctions available for its review.

## Local XML Integrity

SHA-256 identifies the actual generated report bytes including run information:

| Snapshot | SHA-256 |
|---|---|
| Baseline | `80c80db52cdd770a3466b81c0f47a09a804d51ad9677d68fc63ab14acbc26bb7` |
| Corrected | `bcee401f80320ef5ce8b9ea39e2e104b7f6319afda5368e9c6902453110b3ccb` |

These are artifact hashes, not Git source or jar hashes. Generated XML/HTML/
execution data stay under ignored target; temporary red diagnostics stay local.
No raw JSON/log/new CSV is versioned. The guide contains the Windows commands
to reproduce test and coverage verification after feature PR integration.

## Ordered Documentation Review — 2026-10-29

The [follow-up review](documentation-review.md), based on PR #113's merged main,
checks all eleven notes and this original evidence chain against current source.
The [applied map](test-contracts.md) separates the 16 regression invocations,
8,712 finite oracle queries, 13 benchmark-only fixture invocations and coverage
counters. Fresh coverage-only verification runs 710 tests; a separate clean
benchmark package runs 723. The old red run is retained, not rerun here.
Current coverage counters agree with the corrected snapshot above, with a new
session-specific XML hash. No historical hash or missing original jar identity
is replaced, and no new research timing or hosted outcome is inferred.
