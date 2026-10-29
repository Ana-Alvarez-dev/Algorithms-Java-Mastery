# Maven CI Pipeline

The project wrapper configuration selects Maven 3.9.11; its bootstrap version is separate. Maven CI supplies batch mode and suppresses transfer progress through MAVEN_ARGS. Verification uses `./mvnw -DfailIfNoTests=true -Pcoverage clean verify`; packaging uses `./mvnw -DfailIfNoTests=true -Pbenchmark clean package`. Neither skips tests. The no-tests property rejects an empty Surefire suite.

Coverage executes the existing 710 normal-suite JUnit invocations under the JaCoCo agent and emits HTML/XML. The benchmark profile executes 723, including thirteen real-fixture checks. These are per-build counts, not additional distinct tests introduced by CI. The separate twenty shell cases retain their own count.

The jobs do not combine coverage and benchmark profiles. Each clean build recreates target; the coverage reports are uploaded before another job packages a different artifact set. Maven dependency cache reuse does not certify compiled output, so clean remains explicit.

A network/dependency/plugin failure is a build failure requiring investigation, not a reason to bypass checks or use an old jar. The local run-block verification used a temporary transport configuration and OpenJDK 21; the hosted record now separately confirms setup and passing jobs, including two different Java 21 patches on main.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the [observed initial hosted executions](first-hosted-run.md).

## Ordered Review Clarification

The exact 710/723 totals are recorded outcomes. failIfNoTests rejects an empty
suite without enforcing those exact counts or zero skipped cases. The two
nonempty report guards do not validate assertion adequacy or a coverage
percentage. Separate jobs recreate output; the local six-block harness runs
sequentially and preserves coverage externally before its later clean package.

The [workflow and evidence map](workflow-contracts.md) gives the applied
boundaries. The [documentation review](documentation-review.md) records CI01-CI12
and fresh local/hosted verification.
