# Testing Fundamentals

Tests compare observed behaviour with a specified expectation. They can reveal
counterexamples and prevent regressions; a passing finite suite is not a proof
over every input. Correctness arguments, coverage and timings provide different
evidence and must not substitute for one another.

This module applies testing to existing production code. `ClosestValueSearch`
promises the smallest original index at minimum distance. Before the correction,
694 tests passed and its JaCoCo instruction/branch/line counters were 100%, yet
duplicates violated that policy. Twelve new named cases produced six failures
on the old source. Fixing the contract and retaining those cases creates useful
regression evidence; increasing a percentage alone would not.

Verification asks whether the implementation meets its contract; validation
also asks whether that contract solves the intended problem. A distance-only
assertion would miss a wrong duplicate index. The test's oracle must include
the secondary index objective, empty result, input preservation and null policy.

Use focused unit tests for pure algorithms. External-service integration,
mocking, end-to-end tests and a test pyramid are contextual techniques, not
implemented layers claimed by this repository. The [implementation and verification guide](implementation-and-verification-guide.md) and
[verification report](verification-report.md) connect local evidence to source.
Next: [test design](02-test-design.md).

## Ordered Review Clarification

The evidence chain is contract -> input partition -> independent expected result
-> executable assertion -> observed outcome. A green test can still contain a
weak oracle or omit a valid partition. The old red/green experiment is dated
2026-10-27; this ordered review checks the current corrected source separately.
Module 16 applies these techniques across existing algorithm packages and has
no separate production Testing package.

The [applied evidence map](test-contracts.md) connects this clarification to
the actual source, domains and profile scope. The [documentation review](documentation-review.md)
records TE01-TE10 and the fresh Java 21 outcomes.
