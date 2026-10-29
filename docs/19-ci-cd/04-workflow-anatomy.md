# Workflow Anatomy

The Maven CI workflow declares events, read permissions, concurrency, Bash defaults and Maven noninteractive arguments. Each job selects source and Java explicitly. Both have a fifteen-minute timeout.

The verify job runs Linux preflight, twenty offline shell cases, wrapper version and coverage-enabled Maven verify. It requires generated HTML/XML coverage files. The benchmark-package job needs verify and starts from another checkout/job environment. It performs an ordinary benchmark-profile build, records package identity and discovers benchmarks without timed research.

Default step failure propagation remains enabled: there is no continue-on-error for verification. The diagnostic upload runs when the job is not cancelled, even after a failure, and warns if no partial report exists. This preserves available diagnostics without changing the failed test conclusion. The package upload is success-only and errors if no selected files match; it does not independently require every listed path when some files match.

The six shell run blocks were syntax-checked and executed locally using Bash's failure/pipe handling. Official actions, hosted runner behaviour, cache service and uploads were not simulated as completed Actions. The guide links the actual YAML instead of maintaining a conflicting second workflow example.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the [observed initial hosted executions](first-hosted-run.md).

## Ordered Review Clarification

The entire verify job, including diagnostic upload, must succeed before the
package job is eligible. A failed upload can therefore block packaging even
when Maven passed. The !cancelled() status function allows diagnostic upload
after earlier failure; it does not guarantee files or service availability.
Failure/cancellation traces are source-semantic reasoning, separate from this
review's observed successful run and local command checks.

The [workflow and evidence map](workflow-contracts.md) gives the applied
boundaries. The [documentation review](documentation-review.md) records CI01-CI12
and fresh local/hosted verification.
