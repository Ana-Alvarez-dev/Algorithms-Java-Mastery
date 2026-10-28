# GitHub Actions

GitHub Actions executes the versioned .github/workflows/maven-ci.yml on the configured events. The workflow has two jobs on hosted ubuntu-24.04 with Eclipse Temurin Java 21. The image and Java patch can evolve; their names alone are not a frozen machine/runtime identity.

Three official actions are pinned to reviewed full commit SHAs: checkout v7.0.1, setup-java v6.1.0 and upload-artifact v7.0.2. The guide records those identities and checked metadata. This pins action source; it does not pin every dependency download, runner image or JVM patch.

The workflow token requests contents:read. Checkout disables persisted credentials. It uses pull_request, not a privileged pull_request_target execution, and references no deployment secrets. Runtime context values are printed through quoted environment expansions, not interpolated PR titles or branch text as shell code.

Setup-java caches Maven dependencies using pom.xml and wrapper properties as key inputs. The workflow never caches target as verified build output. Newer runs for the same PR/ref can cancel older runs; a cancelled run remains cancelled, not passing. The first actual hosted execution must be reviewed separately from local validation.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the [observed initial hosted executions](first-hosted-run.md).
