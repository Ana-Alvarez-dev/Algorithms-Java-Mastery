# Testing

Module 16 applies contract-driven verification to the repository's real code.
It follows [Dynamic Programming](../15-dynamic-programming/README.md) and precedes
[Benchmarking](../17-benchmarking/README.md).

## Original Executable Contribution — 2026-10-27

- Correct lowest-index duplicate ties in ClosestValueSearch by reusing lower bounds.
- Add twelve named parameterised cases and four oracle/property/isolation tests.
- Use a small test-only copying builder, existing Jupiter 5.12.2 and AssertJ 3.27.3.
- Add optional JaCoCo 0.8.13 HTML/XML coverage under ignored target.
- Correct future ArrayAggregation benchmark data to stay within its int-sum contract.

The [implementation and verification guide](implementation-and-verification-guide.md)
defines contracts, partitions, pseudocode/trace, proof, costs, fixtures, sources
and Windows commands. The [verification report](verification-report.md) records
the baseline/red/green chain and actual coverage counters. The old search had
100% instruction/branch/line coverage but failed six of twelve new named cases.
The normal and coverage-only suites have **710 tests**, including **16 Searching
regression invocations**; these are counted once in the algorithm package, not
as a second production Testing package. The benchmark profile adds **13 fixture
invocations**, for **723**. Coverage percentages are not correctness proofs.

The **2026-10-29 ordered documentation review** adds an
[applied evidence map](test-contracts.md) and a [review record](documentation-review.md).
They connect TE01-TE10 to the actual Java sources, oracle argument, finite
domains, fixture ownership, profile counts and fresh coverage. This follow-up
changes only Markdown.

## Study Sequence

1. [Fundamentals](01-testing-fundamentals.md)
2. [Test design](02-test-design.md)
3. [JUnit Jupiter](03-junit-jupiter.md)
4. [AssertJ](04-assertj.md)
5. [Parameterised tests](05-parameterized-tests.md)
6. [Exception testing](06-exception-testing.md)
7. [Edge cases](07-edge-case-testing.md)
8. [Property thinking](08-property-based-thinking.md)
9. [Test-data builders](09-test-data-builders.md)
10. [Coverage](10-test-coverage.md)
11. [Common mistakes](11-common-mistakes.md)

## Local Reproduction

```powershell
.\mvnw.cmd clean test
.\mvnw.cmd -Pcoverage clean verify
Start-Process .\target\site\jacoco\index.html
```

Maven must run on Java 21. Full commands and source/runtime capture are in the
guide. Coverage instrumentation and JMH measurements remain separate. The
[corrected aggregation run](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
is now recorded with its own provenance limits; historical Arrays means stay
unchanged. The [CI workflow](../../.github/workflows/maven-ci.yml) and
[dated hosted record](../19-ci-cd/first-hosted-run.md) are later contributions,
separate from this review's local checks. No new benchmark family is added merely
to time tests. Evidence is Markdown; generated HTML/XML remain unversioned,
without new JSON/log/CSV exports. The next ordered review is **17 — Benchmarking**.
