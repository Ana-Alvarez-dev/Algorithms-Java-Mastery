# Testing: Ordered Documentation Review

Reviewed on **2026-10-29**, America/Buenos_Aires, against main
**`cb6855da09f963097953ae958f62e1827ae861b8`**, PR #113 merging the Dynamic
Programming documentation review. This closes the scoped **16 — Testing**
documentation review; next is **17 — Benchmarking**, after integration.

## Scope and Decision

Read all eleven numbered notes, README, implementation/verification guide and
original report. Check their claims against the current nearest-value and
lower-bound implementations, regression/original tests, copying builder, actual
aggregation JMH setup, benchmark-profile fixture tests, POM, workflow and later
aggregation/hosted evidence. The [applied evidence map](test-contracts.md)
connects contracts, oracle argument, finite domains, assertions, counts and
profile boundaries. Module 16 has no separate production algorithm package.

This contribution changes **seventeen Markdown paths**: those fourteen existing
module files, two new map/review documents and the project traceability review.
Executable Java, tests, POM, workflow and historical research stay unchanged.
The findings below include status corrections and explanatory limits; they are
not ten newly discovered executable defects or a fresh mutation-testing study.

## Findings and Applied Corrections

| ID | Finding at the reviewed source | Applied clarification / evidence |
|---|---|---|
| TE01 | The green/red/green experiment is historical, while notes sometimes describe its additions as new | Date the original contribution; separate its six observed failures from today's corrected-source checks and link the existing Searching proof |
| TE02 | An independent oracle still needs a stated correctness argument | Derive the linear prefix argmin, strict tie update, empty sentinel and long-distance bound; distinguish index equality from distance-only agreement |
| TE03 | Annotated methods, invocations and generated queries have different counts | Map 5 regression methods to 12+4=16 invocations, Searching 101, normal/coverage 710; derive 792 multisets and 8,712 queries without inflating JUnit totals |
| TE04 | Fixture tests introduced later are absent from the original 710-test scope | Map benchmark-profile source activation and 5+4+3+1=13 fixture invocations, producing 723; a skipped-test package is not an executed suite |
| TE05 | Lifecycle and copying do not supply all fixture guarantees | Explain three copy boundaries, default expectation as caller convention, mutable builder and lack of automatic deep array equality |
| TE06 | Broad generated/property labels can imply more than the actual finite tests | Describe the eight-entry seeded pool and 45 bounded metamorphic targets/135 calls; prove safe translation/scaling without claiming uniform sampling or shrinking |
| TE07 | Fixture sum/range checks do not assert every payload position or allocation byte | Map valid/invalid rows, formula and preservation; distinguish actual setup/guard source from the test's aggregate assertions |
| TE08 | Coverage generation can be mistaken for a threshold, complete paths or exact-count gate | Read prepare-agent/report and absence of check rules; separate bytecode counters, source counts, assertions and failIfNoTests semantics |
| TE09 | README/guide still say a corrected aggregation author run is pending | Link the already recorded five-cell supplied run, preserving its inferred source, absent original jar hash and separate old ascending payload |
| TE10 | Original report says the CI workflow gap remains open | Preserve that dated snapshot, add current workflow/hosted-record links, and distinguish their recorded runs from this local review and a future PR |

Each numbered note receives its relevant clarification and links to the map
and review. The README and guide provide the current actionable status; the
original report keeps its baseline counters, six failures and two XML hashes.
No new benchmark family, dependency, percentage policy or production fix is
needed to apply these scoped documentation corrections.

## Primary References and Source Decisions

Consulted **2026-10-29**, using official documentation:

- [JUnit 5.12.2 User Guide](https://docs.junit.org/5.12.2/user-guide/index.html): sections 2.17.3 MethodSource and 2.17.8 lifecycle; static source in the selected class and separately reported parameterised invocations.
- [AssertJ official guide](https://assertj.github.io/doc/): assertion completion, descriptions before the terminal assertion, array and exception assertions. The installed dependency remains 3.27.3; the online guide is not a claim of a dependency upgrade.
- [JaCoCo counters](https://www.jacoco.org/jacoco/trunk/doc/counters.html): bytecode, line/branch/method/class semantics and exception-handling limitation; [prepare-agent](https://www.jacoco.org/jacoco/trunk/doc/prepare-agent-mojo.html), [report](https://www.jacoco.org/jacoco/trunk/doc/report-mojo.html) and [check](https://www.jacoco.org/jacoco/trunk/doc/check-mojo.html): distinct goals and lifecycle/configuration responsibilities.
- [JaCoCo release history](https://www.jacoco.org/jacoco/trunk/doc/changes.html): pinned 0.8.13 and Java 21 support; trunk goal documentation currently describes a newer development version. The POM and actual build determine the installed version.
- [Maven Surefire test goal](https://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html): failIfNoTests and skipTests. Current online documentation can describe a newer plugin; the observed runner here is 3.2.5 and no Failsafe integration suite is introduced.

The independent oracle argument, combinatorial counts and metamorphic relations
are derivations from this repository's code/domain. The
[POM](../../pom.xml),
[fixture tests](../../src/jmh-test/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregationFixtureTest.java)
and [workflow](../../.github/workflows/maven-ci.yml) determine local scope and
configured gates. CLRS remains the earlier algorithmic reference; no new CLRS
passage or visual inspection is claimed for this framework-focused review.

## Fresh Java 21 Verification

The executable source remains identical to the base main above. Commands run
in an isolated worktree, with documentation edits only. Verification uses
**Amazon Corretto 21.0.13.0.11**, runtime **21.0.13+11-LTS**, Maven Wrapper
**3.9.11**, Linux amd64, UTF-8; not the author's Oracle 21.0.9 Windows runtime.
Jupiter remains 5.12.2, AssertJ 3.27.3 and JaCoCo 0.8.13.

| Actual command / check | Observed outcome |
|---|---|
| bash mvnw --settings TEMP --batch-mode --no-transfer-progress -Pcoverage -DfailIfNoTests=true clean verify | BUILD SUCCESS, 710 tests; zero failures/errors/skips; HTML/XML; 135 analysed classes |
| Separate bash mvnw --settings TEMP --batch-mode --no-transfer-progress -Pbenchmark -DfailIfNoTests=true clean package | BUILD SUCCESS, 723 tests; zero failures/errors/skips; executable benchmarks.jar |
| Java 21 benchmarks.jar -l | 115 registered JMH methods |
| Java 21 exact ArrayAggregationBenchmark.sum filter with -lp | One method; five sizes 100,1000,10000,100000,1000000 |

JAVA_HOME explicitly selects that JDK. TEMP denotes a temporary Maven settings
file for dependency access, outside the repository; the standard Java trust
store is selected through MAVEN_OPTS. Initial offline attempts stopped at
uncached plugin resolution before tests, then the online commands above passed.
No application/test failure is hidden by that dependency retry. Compiler plugin
versions observed are 3.13.0 for coverage and the benchmark profile's 3.14.0;
both compile release 21. Surefire is 3.2.5. The shade manifest-overlap warning
did not prevent packaging success.

Coverage verification finished **08:58:23 -03:00**, Maven duration **38.932 s**;
the separate package finished **08:59:58 -03:00**, duration **38.341 s**.
These are build observations, not algorithm latency measurements. Surefire XML
confirms Searching **101**, including regression **16** and original closest
**12**; benchmark-profile XML separately confirms fixture **13**. The normal
scope is executed under coverage rather than claimed as an additional fresh
uninstrumented test build. Repeated invocations across builds are not new tests.

### Current Coverage-Only Counters

| Counter | Covered | Missed | Total |
|---|---:|---:|---:|
| INSTRUCTION | 12,631 | 115 | 12,746 |
| BRANCH | 1,511 | 107 | 1,618 |
| LINE | 2,163 | 30 | 2,193 |
| COMPLEXITY | 1,181 | 117 | 1,298 |
| METHOD | 478 | 10 | 488 |
| CLASS | 135 | 0 | 135 |

ClosestValueSearch.search has **67/67 instructions, 8/8 branches and 12/12
lines**, all covered. These agree with the original corrected counters; they
do not prove all data partitions or paths correct. The 135 analysed classes
come from 100 production Java files, without JMH/generated benchmark classes.
Coverage-only and benchmark-only profiles are not combined.

The fresh XML was captured before the next clean removed target reports.
Its SHA-256 is
`e00e445693cbb278cd0cec0ecb2507ea9d2604ac88670dcbd0c551a1e34992a5`.
The newly built local benchmark jar SHA-256 is
`28ab51346c97a642b283a05ae75f6d8121a7ad03dc05fc19072a92139aceb1cd`.
These identify these generated bytes only; neither replaces an original
2026-10-27 XML hash or the absent author-run jar hash. Discovery is not a timed
run, smoke, independent all-fixture probe, profiler or doclint check. No new
hosted CI status is inferred.

## Final Audit and Evidence Boundaries

The closing audit checks **313 Markdown files / 1550 relative file targets**,
with zero missing destinations, balanced fences and passing `git diff --check`.
All seventeen changed paths are Markdown within the declared scope. No new
local heading-fragment links are introduced; external URLs and inherited
heading fragments are not exhaustively validated. Inventory remains
**100 production Java files / 115 JMH methods**. Generated reports, jar and
temporary diagnostics stay outside versioned evidence; no new JSON, CSV, log
or binary file is included.

The [original report](verification-report.md) retains the old 694-test green
baseline, six failures among twelve added rows, corrected 710-test outcomes and
original coverage snapshots. This review does not rerun the old red stage or
present its recorded counters as fresh measurements.

The [corrected aggregation record](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
already retains all five means/errors and 50 measured/30 warmup averages. Its
reflog-inferred source and missing original jar identity remain qualified;
the old ascending-input record retains its domain violations and uncertainty
limits. The [initial hosted record](../19-ci-cd/first-hosted-run.md) remains a
dated record of those actual runs, not proof about this review's future checks.
No historical measurement, failure, hash or limitation is overwritten.
