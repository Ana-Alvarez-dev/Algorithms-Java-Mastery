# Automated Testing in CI

CI repeats existing contract checks in a selected environment. JUnit/AssertJ verify behaviour; the shell suite verifies Linux preflight boundaries using controlled fake launchers. Real JDK/build checks and those fake-tool cases are separate evidence.

The coverage job currently executes 710 JUnit invocations. The package job repeats those and adds thirteen aggregation fixture cases, for 723. A repository count is not the sum of repeated builds. Tests failing, throwing errors or finding no suite prevent successful packaging; diagnostic uploads can still retain available XML/coverage output.

No test is added merely to assert a constant workflow string or reach an arbitrary percentage. Workflow syntax, action metadata, dependencies between jobs and concrete run commands are checked directly. Local execution cannot validate hosted permissions, runner startup, action runtime or artifact service availability; record actual run conclusions later.

A green workflow supports the tests it ran, not every undocumented behaviour. Historical Searching defects demonstrated that complete coverage could coexist with a missing duplicate contract case. Keep formal reasoning, independent oracles, coverage and CI status distinct.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the pending first hosted execution.
