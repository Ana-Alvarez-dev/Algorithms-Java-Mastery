# Continuous Delivery and Deployment Boundaries

This academic repository produces checked code, tests, reports and an executable benchmark jar. The applied workflow prepares a downloadable verified package with source/artifact context. It has no application server, production environment, release registry or deployment target.

Delivery readiness and actual deployment are separate decisions. The workflow contains no Maven deploy goal, release publication or environment deployment. A retained CI artifact is a temporary build output, not a production release or a substitute for versioned research evidence.

The package job runs only after tests/coverage pass and performs its own benchmark-profile tests, artifact capture and discovery. Its archive contains benchmarks.jar, a Markdown provenance record and JUnit XML. Artifacts expire after seven days under the requested retention, subject to repository policy. A later rebuild has its own identity; downloading an archive does not restore an old missing artifact's hash.

A future deployment pipeline would need an actual delivery contract and target. This module implements the repository's CI and package-preparation scope without inventing a service deployment. The author continues to review and merge PRs through the existing workflow.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the pending first hosted execution.
