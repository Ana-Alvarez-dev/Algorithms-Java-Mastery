# Continuous Integration

Continuous Integration checks proposed changes against a defined build/test contract. Here the concrete contract is the versioned Maven CI workflow: Java 21, Linux prerequisite checks, twenty shell cases, JUnit/coverage, then a separate benchmark package and discovery check.

The workflow responds to pull requests targeting main, pushes to main and manual dispatch. A feature-branch push alone is not a configured trigger. The author creates the PR; the contribution does not open or merge it automatically. On a PR, the default checkout normally tests the synthetic merge commit. The retained actual checkout SHA is therefore distinct from merely naming a branch head.

A failed verification job prevents the dependent package job. Passing jobs support the checked source and stated environment; they do not prove every algorithm correct or turn current benchmark means into universal facts. CI durations include setup/network/cache/build work and are not algorithm latency.

The source-level workflow gap is addressed, and the [initial PR and main executions](first-hosted-run.md) now provide actual hosted success with run/checkout identities. Local checks remain a separate record; source and hosted evidence are not conflated.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the [observed initial hosted executions](first-hosted-run.md).

## Ordered Review Clarification

With no explicit PR types, the configured PR activity defaults are opened,
synchronize and reopened; the main filter applies to the target branch. No path
filter excludes documentation. Each run/attempt needs its own checked source:
the newly observed main run is identified separately from the initial PR's
synthetic merge and from this review's future PR.

The [workflow and evidence map](workflow-contracts.md) gives the applied
boundaries. The [documentation review](documentation-review.md) records CI01-CI12
and fresh local/hosted verification.
