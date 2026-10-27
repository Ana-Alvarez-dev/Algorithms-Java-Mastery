# Testing

Module 16 applies contract-driven verification to the repository's real code.
It follows [Dynamic Programming](../15-dynamic-programming/README.md) and precedes
[Benchmarking](../17-benchmarking/README.md).

## Executable Contribution

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
The full corrected suite has **710 tests**, including **16 new Searching
invocations**; these are counted once in the algorithm package, not as a second
production Testing package. Coverage percentages are not correctness proofs.

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
guide. Coverage instrumentation and JMH measurements remain separate. The five
corrected aggregation defaults need a new author run before Result/Interpretation
can be completed; historical Arrays means stay unchanged. No new benchmark
family is added merely to time tests, and no CI status is inferred. Evidence
is Markdown; generated HTML/XML stay local without versioned JSON/log/new CSV.
