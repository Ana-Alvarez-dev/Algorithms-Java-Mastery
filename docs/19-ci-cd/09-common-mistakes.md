# Common CI/CD Mistakes

| Mistake | Applied correction |
|---|---|
| Claiming hosted CI passed after local tests | Keep hosted run evidence pending until the run is observed |
| Treating branch push as a PR run | Events are main pushes, main-target PRs and manual dispatch |
| Confusing PR head with tested merge | Retain actual checkout and event SHA |
| Using another Java through PATH | Select Temurin 21 and run the Linux alignment preflight |
| Passing an empty suite | Enable failIfNoTests and retain XML |
| Mixing coverage with performance | Separate jobs/profiles; no timed JMH research in this pipeline |
| Reusing target from cache | Cache Maven dependencies; clean-build target |
| Hiding test failure behind uploads | Keep failure propagation; diagnostics do not alter conclusions |
| Losing artifact identity | Capture adjacent source/dirty state and jar SHA-256 in Markdown |
| Calling an uploaded jar a release/deployment | Keep package preparation scoped to this academic repository |
| Assuming CI enables branch protection | Required-check settings remain a separate configuration |

No JSON/log/new CSV evidence files are added to Git. GitHub's own job logs and downloadable reports are operational records, while research remains in the existing Markdown reports. After the author opens the PR, inspect both named checks and their source/attempt before reporting a successful hosted run.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the pending first hosted execution.
