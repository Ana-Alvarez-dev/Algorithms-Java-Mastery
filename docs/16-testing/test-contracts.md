# Testing: Applied Contracts and Evidence Map

Reviewed against main `cb6855da09f963097953ae958f62e1827ae861b8`
(PR #113), on **2026-10-29**, America/Buenos_Aires. The
[documentation review](documentation-review.md) records the findings and fresh
Java verification. The [original report](verification-report.md) retains the
2026-10-27 baseline/red/green experiment; it is not a new execution here.

## 1. Contract, Oracle and Failure Meaning

For a non-null nondecreasing `int[] a`, nearest-value search returns -1 when
empty; otherwise it minimises `(abs((long) a[i] - target), i)`, preserving the
array. Null has an explicit exception/message. Sortedness is a caller
precondition: neither this suite nor this map promises rejection of an unsorted
array. The [Searching map](../04-searching/implementation-contracts.md) gives
the current API and lower-bound proof.

The regression oracle is an independent left-to-right scan, not another binary
search. After processing a prefix, its incumbent is that prefix's minimum
distance and first index attaining it. Strictly smaller distance replaces the
incumbent; equal distance keeps the earlier index. An empty prefix has index -1
and distance Long.MAX_VALUE. Induction over positions gives the complete
argmin; a finite scan terminates. Widening before subtraction bounds absolute
distance by 4,294,967,295, safely below the long limit. Similar casts after an
int subtraction would not supply this guarantee.

| Obligation | Source evidence | What a weaker check would miss |
|---|---|---|
| Distance and first-index tie | Exact expected index in named cases and linear oracle | Any valid index, or correct distance with a later duplicate |
| Empty and full int domain | Named cases, MIN/MAX queries, long oracle | Sentinel errors and overflow |
| Preservation | Copy before search, compare after call | Correct answer obtained by mutating caller data |
| Null rejection | Existing ClosestValueSearchTest checks type and message | A passing valid-input test says nothing about null |
| Sorted precondition | Generators construct nondecreasing input | Unsorted behaviour remains unspecified |

The historical six failures are retained counterexamples to the old source.
This review reruns the corrected source; it does not reconstruct or rerun the
old red baseline. A green test establishes its asserted observations for its
executed inputs, subject also to the adequacy of the oracle and fixture.

## 2. Finite Domains and Reported Invocations

The actual sources are
[ClosestValueSearchRegressionTest](../../src/test/java/org/anaalvarezdev/algorithms/searching/ClosestValueSearchRegressionTest.java),
[ClosestSearchCaseBuilder](../../src/test/java/org/anaalvarezdev/algorithms/searching/ClosestSearchCaseBuilder.java)
and [the existing closest-value tests](../../src/test/java/org/anaalvarezdev/algorithms/searching/ClosestValueSearchTest.java).

| Executable check | Declared domain | JUnit invocations |
|---|---|---:|
| Named MethodSource cases | Twelve rows, including duplicate ties, extremes and empty | 12 |
| Exhaustive oracle | 792 sorted multisets, length 0..7, values -2..2; 11 targets each | 1 |
| Seeded oracle | 300 arrays, length 0..129, seed 20261027 | 1 |
| Translation/scaling | 45 targets -120..100, step 5, on one fixed six-element array | 1 |
| Fixture isolation | Caller, accessor copy and reused builder | 1 |
| Existing closest-value tests | Twelve pre-existing ordinary tests, including null | 12 |

There are five annotated test methods in the regression class: one expands to
twelve invocations and four remain single tests, giving **16**, not 5 or 8,712.
The existing class supplies another 12; together they are 28 of Searching's
101 invocations. They are already included in the repository's 710 normal
tests; module 16 does not create a second production package to count again.

For length n, a sorted multiset over five symbols is described by five
nonnegative multiplicities summing to n, giving C(n+4,4). Summing for n=0..7
gives C(12,5)=792, hence 792*11=8,712 oracle queries. The recursive generator
chooses each next value at least as large as its predecessor, enumerating each
such array exactly once. This is exhaustive over that finite domain only.

The seeded generator first makes an eight-entry full-int pool and replaces two
entries by MIN/MAX. Each array samples from that pool and is sorted, producing
duplicates; it queries random, MIN/MAX and, for nonempty arrays, an exact value.
It is not uniform sampling of all sorted int arrays and does not enumerate all
extreme combinations. A seed identifies a reproducible generator sequence with
the same code/runtime; retain the actual failing input when investigating one.

For the metamorphic test, adding 1000 preserves all distances and multiplying
by positive 3 multiplies every distance by 3. Both preserve order and the set
of minimising indices on the bounded data. There are 135 production calls
(base/translated/scaled for each target) within one invocation. These fixed
relations are not an installed property-testing/shrinking framework. A negative
factor, reordered indices or overflow would require a different argument.

## 3. Fixture Ownership and Assertions

The builder copies on values assignment; SearchCase copies on construction and
on every input accessor. Mutating the original caller array, an exposed copy
or the reused builder cannot change an already built case's retained input.
The isolation test makes these three routes observable. Ordinary per-method
Jupiter lifecycle alone does not prevent aliasing through externally shared
objects; fresh data is a separate responsibility.

The builder is mutable and test-only. It neither sorts input nor calculates
expected results nor validates every case's semantics. Its default expected
value -1 suits the empty case; setting an explicit expectation for a nonempty
case is a caller convention, not an enforced constructor invariant. Copying an
array in a record also does not make the generated record equality a deep
array-content comparison. Assertions compare content explicitly.

AssertJ descriptions belong before the terminal assertion, so a failure can
include them. `containsExactly` checks order and multiplicity. For deliberate
exception contracts, isolate the throwing operation and check the stable
type/message; the explicit null message is independent of VM-generated text.
The benchmark fixture tests use reflection intentionally to inspect their
private fixture, not to introduce a reflection-based public algorithm API.

## 4. Profile Scope Is Part of the Result

| Command from repository root | Tests included at reviewed source | Output / limit |
|---|---:|---|
| .\mvnw.cmd -DfailIfNoTests=true clean test | 710 | Functional suite; no JaCoCo report requested |
| .\mvnw.cmd -DfailIfNoTests=true -Pcoverage clean verify | 710 | Same test scope, with agent and HTML/XML coverage |
| .\mvnw.cmd -DfailIfNoTests=true -Pbenchmark clean package | 723 | Adds 13 fixture invocations, then packages JMH |
| .\mvnw.cmd -Pbenchmark -DskipTests package | Not executed | Compiling tests/package success is not a test pass |

The [POM](../../pom.xml) activates `src/jmh-test/java` only through the benchmark
profile. Its
[ArrayAggregationFixtureTest](../../src/jmh-test/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregationFixtureTest.java)
has three parameterised methods (5+4+3 rows) and one ordinary test: **13**
invocations from four annotated methods. They do not belong in the 710-test
coverage-only denominator. Repeated builds do not create new distinct tests.

The five default sizes are read from the real @Param annotation; four additional
valid sizes are 0,1,99,103; three rejected sizes are -1,1,000,001,MAX_INT. Valid
cases check size, value range, long sum versus an independent cycle formula,
three production calls and preservation. With n=100q+r, the formula is
`4950q+r(r-1)/2`. Defaults yield 4,950..49,500,000. All entries are nonnegative,
so every prefix is bounded by the safe total. Empty and partial cycles are
valid; aggregation itself retains its int-sum precondition.

The test checks range and aggregate sum, not each position against i%100.
A permutation preserving both would satisfy those checks. The actual setup
loop supplies the pointwise pattern, and source inspection shows the guard
before allocation. Exception assertions alone do not measure allocation order
or bytes. The final ordinary test additionally checks independent arrays across
successive setups and fixture instances. These are untimed fixture checks, not
proof of every JMH fixture, payload attestation for an old run or timing data.

## 5. Coverage Counters and Gates

The coverage profile binds prepare-agent to its default initialise phase and
report to verify, requesting only HTML/XML. Its agent supplies Surefire's
argLine; the observed build confirms execution data is consumed. The POM has
no JaCoCo check goal, threshold rule or package exclusion. Producing a report
does not itself enforce a minimum percentage. `-DfailIfNoTests=true` rejects an
empty discovery result; it does not verify that exactly 710 tests were found.
Counts and skipped outcomes therefore remain part of the recorded evidence.

JaCoCo works with bytecode and debug mappings. An instruction is an execution
unit, a line is covered when at least one mapped instruction ran, and its
branch metric excludes exception handling. Constructors/initialisers and
filtering mean source files, source methods and analysed classes have different
counts: **100 production Java files** yield **135 analysed classes** here.
Covered complexity is not a mutation score or an inventory of all input paths.
Delegating lower bounds also changes the nearest method's counter denominator;
its 100% is not coverage of every delegated obligation in that method alone.

The historical old nearest method was 100% covered yet six new named duplicate
cases failed. Current method counters are 67/67 instructions, 8/8 branches and
12/12 lines. Passing assertions, finite partitions, proof and execution coverage
answer different questions. The [fresh review](documentation-review.md) records
the full repository counters without claiming unexecuted paths correct.

Use separate clean builds for coverage and JMH. A later clean removes the
earlier target reports/jar: capture needed report evidence and hash the actual
existing jar before cleanup. A regenerated XML's session metadata can change
its hash while counters stay equal; the historical XML hash must not be
replaced. A newly built jar cannot recover an earlier missing benchmark hash.

## 6. Current Evidence and Continuation

The [corrected aggregation run](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
already records five supplied means/errors and all 50 measurement/30 warmup
averages. Its source remains reflog-inferred and its original jar hash absent;
this review supplies neither a replacement timing run nor retrospective
attestation. The old ascending fixture's overflowing-input means stay separate.

The repository also has a [Maven CI workflow](../../.github/workflows/maven-ci.yml),
with separate coverage and benchmark-package jobs, and a
[dated hosted-run record](../19-ci-cd/first-hosted-run.md). This closes the old
workflow-definition gap. Reading YAML and passing local tests do not establish
the status of this review's future PR, branch protection or deployment.

Next in the ordered documentation review is **17 — Benchmarking**. Framework
behaviour is checked against the primary references listed in the review;
the supplied CLRS algorithm text is not an authority for Jupiter or JaCoCo APIs.
