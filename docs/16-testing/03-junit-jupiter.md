# JUnit Jupiter

The repository pins JUnit Jupiter **5.12.2**. Jupiter supplies the test model
and engine, running on the JUnit Platform; AssertJ supplies assertion expressions.
The existing aggregate Jupiter dependency includes parameterised-test support.
No migration to a newer major version is implied by this module.

The new `ClosestValueSearchRegressionTest` has one `@ParameterizedTest` backed
by a static `@MethodSource`, plus four ordinary `@Test` methods. Twelve argument
sets yield twelve separately reported invocations, so this class contributes
**16 executed tests**, not five. The exhaustive loop inside one test remains
one reported invocation, irrespective of its thousands of queries.

Prefer independent fixtures and default per-method lifecycle. Use lifecycle
callbacks when shared arrangement clarifies a real fixture, rather than adding
empty setup/teardown methods. Expected failures must be observed through the
runner and assertions, not swallowed. This module records the six pre-fix
failures, then a clean full-suite result with the fix.

Maven Surefire reports under ignored target/surefire-reports are local evidence.
An IDE success, local pass and GitHub Actions status are distinct; this module
adds no CI workflow. The [implementation and verification guide](implementation-and-verification-guide.md) has exact commands and source attribution.
Next: [AssertJ](04-assertj.md).
