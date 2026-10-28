# Workflow Anatomy

The Maven CI workflow declares events, read permissions, concurrency, Bash defaults and Maven noninteractive arguments. Each job selects source and Java explicitly. Both have a fifteen-minute timeout.

The verify job runs Linux preflight, twenty offline shell cases, wrapper version and coverage-enabled Maven verify. It requires generated HTML/XML coverage files. The benchmark-package job needs verify and starts from another checkout/job environment. It performs an ordinary benchmark-profile build, records package identity and discovers benchmarks without timed research.

Default step failure propagation remains enabled: there is no continue-on-error for verification. The diagnostic upload runs when the job is not cancelled, even after a failure, and warns if no partial report exists. This preserves available diagnostics without changing the failed test conclusion. The package upload is success-only and errors if its requested output set is absent.

The six shell run blocks were syntax-checked and executed locally using Bash's failure/pipe handling. Official actions, hosted runner behaviour, cache service and uploads were not simulated as completed Actions. The guide links the actual YAML instead of maintaining a conflicting second workflow example.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the [observed initial hosted executions](first-hosted-run.md).
