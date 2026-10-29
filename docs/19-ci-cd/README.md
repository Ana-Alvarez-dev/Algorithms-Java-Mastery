# CI/CD: Applied Maven Verification

This module connects the existing contracts, Java 21 tests, Linux checks,
coverage and benchmark packaging to an actual GitHub Actions workflow. It
follows module 18 and completes the repository's existing numbered sequence
through 19. The [initial hosted executions](first-hosted-run.md) and
[closing traceability review](../00-project/12-closing-traceability-review.md)
now record observed CI and the selected-scope milestone.

## Implemented workflow

- [Maven CI YAML](../../.github/workflows/maven-ci.yml): two jobs, configured events, permissions, pinned actions and artifacts.
- [Implementation and verification guide](implementation-and-verification-guide.md): contract, pseudocode/trace, failure handling, action identities, local checks and author procedure.
- [Verification report](verification-report.md): local outcomes and subsequent hosted follow-up.

`verify` runs Linux preflight, twenty shell cases and coverage-enabled tests,
requiring HTML/XML coverage output. `benchmark-package` needs verify, starts a
separate job and builds the uninstrumented benchmark profile with tests, jar
identity capture and discovery. It retains the package and Markdown provenance.
There is no timed performance threshold, release publication or service deployment.

The workflow handles main pushes, PRs targeting main and manual dispatch.
Feature-branch push alone is not a trigger. It requests contents:read, disables
checkout credential persistence, uses fifteen-minute job timeouts, cancels older
runs for a PR/ref, caches Maven dependencies and requests seven-day artifacts.
Three official actions are pinned to reviewed full SHAs. Hosted image/JDK patch
are still recorded execution context, not immutable machine identities.

## Local verification versus hosted status

Actionlint **1.7.12** accepts the workflow. All six run blocks were separately
syntax-checked and executed locally: **20 shell cases**, **710 coverage-enabled
JUnit invocations** and **723 package-profile JUnit invocations**, with coverage
files, jar/provenance and discovery checks passing. There are still **115 JMH
methods / 40 classes**. Production Java, tests, POM and historical results are
unchanged; counts across repeated builds are not new distinct tests.

**Initial hosted execution observed:** PR run 40606662517 and merged-main run
40606676206 both succeeded. The [hosted record](first-hosted-run.md) retains
actual checked source (synthetic merge versus final merge), clean states,
710/723 JUnit outcomes, artifact IDs/digests and the independently downloaded
main jar hash. The two main jobs used different Temurin-21 patches/images;
this allowed variability is recorded rather than hidden. The earlier local
harness did not run those services and remains separate. Branch-protection
settings were not changed or verified. Future PRs have their own observed checks.
Windows remains supported; installing WSL locally is not required for GitHub's
hosted Linux jobs.

## Ordered documentation review — 2026-10-29

The [review record](documentation-review.md), based on main
`ca756c1b3f50ed2ab476b68b12615eca4899784b` (PR #116), and
[workflow/evidence map](workflow-contracts.md) connect CI01-CI12 to events,
checkout identity, job conditions, actual gates, action metadata and artifact
integrity. Fresh local validation passes actionlint, all six Bash run blocks,
20 shell cases, 710 coverage tests and 723 benchmark-profile tests.

**Current main observed:** [run 40877685400, attempt 1](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40877685400)
completed with both jobs successful. Downloaded archives match API digests;
JUnit XML confirms 710/723 zero-failure outcomes, and the contained jar matches
its logged/provenance SHA-256. These are observations for that exact checkout;
this documentation PR has its own later checks. The initial hosted record and
research remain unchanged. This follow-up changes only Markdown. After
integration, the [closing traceability review](../00-project/12-closing-traceability-review.md)
reconciles the ordered reviews and continuing limits.

## Study notes

| Note | Applied question |
|---|---|
| [01 — Continuous integration](01-continuous-integration.md) | Which changes/events are verified and what does passing establish? |
| [02 — Continuous delivery](02-continuous-delivery.md) | What package is prepared, and where does deployment remain out of scope? |
| [03 — GitHub Actions](03-github-actions.md) | Which actions, runtime, permissions and cache are selected? |
| [04 — Workflow anatomy](04-workflow-anatomy.md) | How do needs, step failures, cancellation and diagnostics interact? |
| [05 — Maven pipeline](05-maven-ci-pipeline.md) | How are the coverage and benchmark profiles separated? |
| [06 — Automated testing](06-automated-testing.md) | Which tests run and which behaviours remain unproved? |
| [07 — Quality checks](07-code-quality-checks.md) | Which gates exist and which analyses were not configured? |
| [08 — Build artifacts](08-build-artifacts.md) | How are source, run attempt, jar bytes and retained reports connected? |
| [09 — Common mistakes](09-common-mistakes.md) | Which failure/provenance shortcuts should be avoided? |

## Evidence boundaries

CI artifacts live in generated output/Actions retention; important observations
remain in versioned Markdown. No JSON/log/new CSV evidence file is added to Git.
The earlier aggregation record keeps its reflog-inferred source and unavailable
original jar hash. A newly captured CI jar does not fill that old missing value.
Coverage, tests, build duration and JMH measurements remain separate claims.
The workflow-definition gap and initial hosted-verification evidence are now
addressed for the recorded runs. Repository enforcement settings remain separate.
