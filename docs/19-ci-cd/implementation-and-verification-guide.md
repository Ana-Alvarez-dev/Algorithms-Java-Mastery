# CI/CD: Applied Workflow and Verification Guide

Reviewed on **2026-10-27**, from main
`a0e174db482164027b086a0ddb986bf136c38b4a` (PR #96, Linux Environment).
The executable definition is [.github/workflows/maven-ci.yml](../../.github/workflows/maven-ci.yml).
This module implements automated verification and package preparation. It does
not invent a deployment target for this academic algorithms repository.

## Contract and traceability

Input: a configured GitHub event and its checked-out source. Preconditions:
GitHub Actions enabled/permitted, an available hosted ubuntu-24.04 runner,
network access for the selected actions/JDK/Maven dependencies, and the tracked
Linux launchers. The workflow selects Eclipse Temurin Java 21, then uses the
existing [Linux preflight](../18-linux-environment/implementation-and-verification-guide.md).

Successful output: tests/coverage and package jobs complete, expected coverage
files exist, the benchmark jar is nonempty, selected discovery succeeds, and
requested downloadable outputs are uploaded. Failure in prerequisite/build/test
or discovery prevents a successful dependent package. Diagnostic upload after a
failed verification does not change the job's failure.

The evidence chain remains contracts/proofs, Java, tests, selected environment,
build, artifact identity and observed automation. A workflow file is implemented
source; a successful hosted run is a distinct observed outcome. The
[verification report](verification-report.md) keeps that distinction explicit.
No production Java, JUnit test, JMH method or POM change is introduced.

## Actual workflow design

| Element | Implemented behaviour |
|---|---|
| Name / file | Maven CI / maven-ci.yml |
| Triggers | push to main; pull_request targeting main; workflow_dispatch |
| Branch pushes | Feature-branch push alone is not a configured trigger |
| Permissions | contents: read; checkout persist-credentials: false |
| Concurrency | Workflow + PR number/ref; cancel an older run for that group |
| Runner / runtime | ubuntu-24.04; Temurin Java 21 |
| Shell / Maven | Bash; batch mode and no transfer progress; wrapper 3.9.11 |
| Job timeout | 15 minutes each |
| Job dependency | benchmark-package needs verify |
| Dependency cache | Maven via setup-java; key inputs pom.xml and wrapper properties |
| Artifact retention | 7 days requested, subject to repository policy |
| Deployment | No release, Maven deploy, environment or service deployment |

No path filters skip documentation-only PRs. No continue-on-error masks checks.
There is no pull_request_target trigger, direct shell interpolation of PR titles,
secret-based deployment or cache of target as accepted build output. This is the
scoped workflow design rather than a general security attestation.

### Job: Java 21 tests and coverage

Checkout and setup-java precede Linux preflight, twenty offline shell contract
cases and wrapper version. Maven runs:

```bash
./mvnw -DfailIfNoTests=true -Pcoverage clean verify
test -s target/site/jacoco/index.html
test -s target/site/jacoco/jacoco.xml
```

This currently executes **710 JUnit invocations**, with JaCoCo 0.8.13 generating
HTML/XML. Surefire's failIfNoTests property rejects an empty suite. There is no
arbitrary coverage-percentage gate. The two nonempty-file checks catch missing
expected reports. Compilation, tests and coverage do not prove complete
behavioural correctness.

The report-upload step runs if the job is not cancelled, including after a
failure, retaining any JUnit TEST-*.xml and target/site/jacoco output. Missing
partial files produce a warning; prior failure remains a failure. A failed
verify job blocks the dependent package job through needs.

### Job: Java 21 benchmark package

A separate job checks out source and selects Java again, then runs preflight
and wrapper version. It uses:

```bash
./mvnw -DfailIfNoTests=true -Pbenchmark clean package
```

This executes **723 JUnit invocations**, the existing suite plus thirteen
benchmark-fixture cases. Coverage is not active in this job. Clean output and
a new Maven process separate the build from coverage; dependency-cache reuse
is not compiled-output reuse. The twenty shell cases are separate from both
JUnit counts, and repeated suites are not new distinct repository tests.

After success, the workflow writes ignored target/ci-provenance.md with run/
attempt URL, event name/SHA, the preflight's actual checkout SHA/dirty state,
UTC time, visible environment, Java/Javac selection and jar SHA-256, plus wrapper
version. It prints the record to the job log. The script does not execute the
jar while collecting identity. Hashing alone does not prove source linkage;
the adjacent successful package and unchanged source context supply that link.

Discovery runs `java -jar target/benchmarks.jar -l`, then the anchored aggregation
selector with `-lp`. The step requires the exact aggregation method and a size
parameter in output. The current inventory is **115 methods / 40 classes**;
that global count is recorded rather than hard-coded as a growth-blocking gate.
There is no timed JMH research or performance regression threshold in CI.

Only after these steps succeed does upload retain benchmarks.jar,
ci-provenance.md and package-job JUnit XML. Upload errors if no selected files
exist; earlier checks separately require the jar and generated provenance.
The archive's upload digest and the contained jar's SHA-256 are different
identities. Upload is package retention, not publishing a release or deployment.

## Pseudocode, trace and reasoning

```text
ON configured event:
    VERIFY: checkout; select JDK; check environment and shell contracts
            clean coverage verify; require HTML/XML
            retain any available diagnostic reports unless cancelled
    IF VERIFY succeeded:
        PACKAGE: new checkout/JDK; check environment
                 clean benchmark package with tests
                 capture source/environment/artifact identity
                 require selected benchmark discovery
                 retain successful package and provenance
```

Success trace: a main-target PR checks out its candidate merge, runs twenty
shell cases and 710 coverage-enabled tests, then packages with 723 tests and
retains discovery/provenance artifacts. Failure trace: a failing assertion makes
verify fail; diagnostic output can still be retained and the dependent job is
skipped. Cancellation supersedes an old run rather than relabelling it passed.

The dependency edge and default failure propagation are the workflow's ordering
argument. It does not execute a deployment before verification or certify an
unobserved hosted run. Each job's identity record must refer to its actual
checkout. On pull_request, GitHub's default checkout normally uses the synthetic
merge commit; that is not silently replaced with the PR branch head. Always
inspect the actual run/attempt and source before documenting its conclusion.

CI wall-clock time includes setup, network/cache, compilation, tests and uploads.
It is not an algorithmic complexity bound or a JMH score. Hashing processes B
artifact bytes, while repository/build costs depend on their inputs. The pinned
action commits do not freeze the hosted image, dependency service or Java patch:
java-version 21 selects that major version, and the actual version is recorded.

## Reviewed action identities

| Official action | Reviewed tag | Full pinned commit |
|---|---|---|
| actions/checkout | v7.0.1 | 3d3c42e5aac5ba805825da76410c181273ba90b1 |
| actions/setup-java | v6.1.0 | b843ebf9b913146b38da2eaa7aeddf76074ae1ad |
| actions/upload-artifact | v7.0.2 | cf430e030ddbb5b0abf93d22962f4752f3646cd9 |

Official tag refs and action.yml metadata were checked. These actions declare
Node 24 and target a compatible hosted runner. Each used input was checked
against the respective metadata. Updates need a reviewed replacement SHA and
metadata validation rather than silently moving to an unrelated tag.

## Local checks versus hosted execution

Actionlint **1.7.12** accepts the YAML. Its downloaded binary was checked against
the official release checksum. ShellCheck/Pyflakes integration was disabled
locally because those tools were unavailable; all six run blocks were separately
Bash syntax-checked and executed with failure/pipe handling. The local harness
extracts those blocks from YAML; it does not claim to run the JavaScript actions,
GitHub cache/upload services or hosted runner.

Local execution used OpenJDK 21 on Linux, not the hosted Temurin setup. Temporary
Maven transport settings and a local perf-data workaround were outside the
workflow. Local-only run-context placeholders test Markdown formatting without
inventing a GitHub run ID. The subsequent [initial hosted record](first-hosted-run.md) now documents real
PR/main success; these local checks remain a different evidence layer.

## Author PR and review procedure

Create/merge the PR through the usual workflow. On its GitHub Checks/Actions
view, inspect **Java 21 tests and coverage** and **Java 21 benchmark package**.
Retain the run URL, run attempt, actual checked source and conclusions. Download
verification-reports to inspect JaCoCo/JUnit; download benchmark-package for the
jar and its Markdown source/artifact record. Uploaded artifacts are temporary,
while important research evidence stays in versioned Markdown.

If a job fails, inspect its first failing step and available diagnostics before
rebuilding or changing source. The package job may be skipped because verify
failed. A cancelled or skipped job is not a passing job. A PR introducing this
workflow should be reviewed through its observed checks; no successful run is
claimed from simply publishing the branch.

After merging, the author can still use Windows PowerShell:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd -DfailIfNoTests=true clean test
```

Actions runs on GitHub's Linux runner; local WSL installation is not a prerequisite
for creating the PR. A manual run is available through Actions once the workflow
is available on the default branch, subject to repository settings/permissions.
No branch-protection or required-check setting is changed by this contribution.
If enforcement is configured later, use the two stable job names above and
validate the repository's current rules separately.

## Primary references and follow-up

- [GitHub workflow syntax](https://docs.github.com/en/actions/reference/workflows-and-actions/workflow-syntax): events, jobs/needs, permissions, shell, conditions and concurrency.
- Official pinned action metadata: [checkout](https://github.com/actions/checkout/blob/3d3c42e5aac5ba805825da76410c181273ba90b1/action.yml), [setup-java](https://github.com/actions/setup-java/blob/b843ebf9b913146b38da2eaa7aeddf76074ae1ad/action.yml), [upload-artifact](https://github.com/actions/upload-artifact/blob/cf430e030ddbb5b0abf93d22962f4752f3646cd9/action.yml).
- [Maven Surefire test goal](https://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html): failIfNoTests; the local project's 3.2.5 plugin descriptor also confirms this property.
- [Actionlint official release](https://github.com/rhysd/actionlint/releases/tag/v1.7.12): local workflow validation tool.

The source-level CI workflow gap is addressed. The [hosted follow-up](first-hosted-run.md)
now records successful PR/main jobs, uploads and independently inspected main
artifacts. Cache hit/speedup is not inferred from setup success, and repository
enforcement settings remain separate. Historical
benchmark observations and unavailable original hashes remain unchanged. There
is no new timed benchmark or JSON/log/CSV evidence file in Git. This completes
the existing numbered implementation sequence through 19; the next step is the
[closing traceability review](../00-project/12-closing-traceability-review.md),
with observed CI and scoped documentation corrections rather than an invented
production deployment or additional numbered module.
