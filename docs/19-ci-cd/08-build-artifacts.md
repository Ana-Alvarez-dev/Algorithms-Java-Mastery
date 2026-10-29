# Build Artifacts and Provenance

The verify job retains JUnit TEST-*.xml and target/site/jacoco. The successful package job retains benchmarks.jar, ci-provenance.md and its JUnit XML. Both artifact names include event SHA and run attempt, with seven-day retention requested. These outputs remain in generated storage/Actions artifacts, outside versioned research tables.

The Markdown provenance record includes run/attempt URL, event SHA, actual git checkout SHA, dirty state, UTC capture time, environment, selected Java/Javac, wrapper-selected Maven version and jar SHA-256. On a PR, actual checkout can be a synthetic merge; the branch head is not substituted for it.

A SHA-256 identifies current jar bytes; it does not alone prove that source built them. A successful fresh package, adjacent capture, discovery and run context strengthen the link. A GitHub upload-archive digest is also distinct from the SHA-256 of the jar inside it.

The old Windows aggregation jar was absent when its hash was requested. This workflow cannot recover that identity and does not overwrite its unavailable field with a new CI hash. Archive expiration likewise does not erase the versioned Markdown research; retain important observed run identities in documentation after verification.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the [observed initial hosted executions](first-hosted-run.md).

## Ordered Review Clarification

Archive SHA-256, artifact ID and inner jar SHA-256 identify different objects.
The fresh main archives match API digests; XML and provenance agree with logs,
and the contained jar hash agrees independently. Availability/expiry is recorded
at inspection. The local harness uses explicit local-only run placeholders,
which must not be presented as a real GitHub run or overwrite older identities.

The [workflow and evidence map](workflow-contracts.md) gives the applied
boundaries. The [documentation review](documentation-review.md) records CI01-CI12
and fresh local/hosted verification.
