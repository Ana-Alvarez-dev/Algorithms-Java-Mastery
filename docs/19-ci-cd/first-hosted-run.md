# Initial Hosted Maven CI Executions

Observed on **2026-10-27, America/Buenos_Aires**. GitHub timestamps below also
retain UTC, where these runs fall on 2026-10-28. This follow-up records actual
hosted results, separate from the original local command checks.

## Run and source identities

| Event | Run / attempt | GitHub API head SHA | Actual checkout from job logs | Conclusion |
|---|---|---|---|---|
| PR #97 | [40606662517](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40606662517), attempt 1 | c8a030b8b85ea15dddddc1467d1777d930eb6f98 | 659cdb7a9c4f89a74c9648c88d9514d1ed76d020 | success |
| Push to merged main | [40606676206](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40606676206), attempt 1 | 0d0ade1f67ffd79db26c06be48a0bf13fdda724a | 0d0ade1f67ffd79db26c06be48a0bf13fdda724a | success |

PR #97 merged the workflow contribution. The PR API head identifies the feature
commit; the logs show the tested synthetic merge. GitHub commit metadata confirms
that the synthetic merge and merged main share tree
`6334460b94457ed63c914cf55b61a6d65d0dff4d`, with parents a0e174d and c8a030b.
Different commit identities are retained rather than replacing one with another.
Both jobs report a **clean working tree** for their actual checkouts.

PR run: created/started 2026-10-28T00:07:00Z, completed by 00:08:25Z.
Main run: created/started 2026-10-28T00:07:06Z, completed by 00:08:41Z
(**2026-10-27 21:07:06 to 21:08:41 -03:00**). These are automation timestamps,
not the dates of earlier author benchmark measurements.

## Main job results and environments

| Field | Java 21 tests and coverage | Java 21 benchmark package |
|---|---|---|
| Job | [122547783285](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40606676206/job/122547783285) | [122548103486](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40606676206/job/122548103486) |
| Conclusion | success | success |
| Job start/end UTC | 00:07:09 / 00:07:59 | 00:08:01 / 00:08:40 |
| Runner version | 2.338.0 | 2.338.0 |
| OS / label | Ubuntu 24.04.5 / ubuntu-24.04 | Ubuntu 24.04.5 / ubuntu-24.04 |
| Runner image version | 20261012.334.1 | 20261025.353.1 |
| Java runtime | Temurin 21.0.12.1+1-LTS | Temurin 21.0.13+9-LTS |
| Compiler | javac 21.0.12.1 | javac 21.0.13 |
| Vendor / architecture | Eclipse Adoptium / amd64 | Eclipse Adoptium / amd64 |
| Maven | 3.9.11 | 3.9.11 |
| JUnit invocations | 710 | 723 |
| Failures / errors / skipped | 0 / 0 / 0 | 0 / 0 / 0 |
| Maven build duration | 32.518 s | 27.081 s |
| Additional outcome | 20 shell cases pass; JaCoCo analyses 135 classes | Source/artifact capture and benchmark discovery pass |

The two jobs selected **different Java 21 patches and runner images**. This is
allowed by java-version:21 and the ubuntu-24.04 label. Those selectors constrain
a major version/image family, not immutable environments. The differences are
recorded explicitly; no identical-runtime or cross-job performance comparison is
claimed. The downloaded JUnit XML independently confirms both runtime versions.
A future requirement for a fixed patch/image would be a separately reviewed
configuration change, not an interpretation silently imposed on these runs.

PR jobs 122547736253 and 122548040802 also succeeded. Their logs show clean
checkout 659cdb7, Temurin 21.0.12.1+1-LTS in both jobs and 710/723 passing JUnit
invocations respectively. The PR package log records jar SHA-256
`0c671447dff594eaae5f285504fd181acbfa051a9a7104625ce9844ed567e71a`.
That PR hash is log-reported; the binary archive independently downloaded for
this review is the later main artifact described below.

## Downloaded main artifacts and integrity

GitHub's artifact API reported these artifacts available (expired=false) at
inspection. Their requested retention is seven days; availability is temporary.
Archives were downloaded through the connector and their SHA-256 values were
independently checked against the API digests before inspection.

| Artifact | ID | Archive bytes | API expiry UTC |
|---|---:|---:|---|
| verification-reports-0d0ade1f67ffd79db26c06be48a0bf13fdda724a-1 | 12781895048 | 644116 | 2026-11-04T00:07:52Z |
| benchmark-package-0d0ade1f67ffd79db26c06be48a0bf13fdda724a-1 | 12781725315 | 3585389 | 2026-11-04T00:08:36Z |

Verification archive SHA-256:
`ac4236f0ea7dfcd94cc2ba355e22f9b5e13584128e6d38edface2d785d6d8796`

Package archive SHA-256:
`abff8c071c666eeb55fcbbecb8d4f698822b23efa1feaeacdfbc984dabe6a61b`

The **contained benchmarks.jar** has independently calculated SHA-256:

`df14a75e6e3e8f4ca1fb5041a1c5e8801dea81724e824636e3b7027f5ecfe03d`

This matches both ci-provenance.md inside the archive and the package job log.
That record also contains the real run/attempt URL, actual checkout 0d0ade1,
clean state, timestamp and selected Java 21.0.13 environment. The archive digest
is not the contained jar digest. Neither value recovers the unavailable jar hash
of the earlier Windows aggregation timing run.

The verification archive contains JUnit XML and HTML/XML coverage. Its 89 JUnit
suites total 710 invocations; the package archive's 90 suites total 723. All
failure/error/skip totals are zero. The thirteen extra cases belong to
ArrayAggregationFixtureTest; they do not add production algorithms. The jar's
BenchmarkList retains 115 methods, and the successful discovery output includes
the exact aggregation method and five sizes. No timed JMH experiment ran in CI.

## Coverage counters from downloaded XML

| Counter | Missed | Covered | Total |
|---|---:|---:|---:|
| INSTRUCTION | 115 | 12631 | 12746 |
| BRANCH | 107 | 1511 | 1618 |
| LINE | 30 | 2163 | 2193 |
| COMPLEXITY | 117 | 1181 | 1298 |
| METHOD | 10 | 478 | 488 |
| CLASS | 0 | 135 | 135 |

These are coverage counters, not proof of complete behaviour. The normal suite's
135 covered bytecode classes include nested/generated class structures; the
source inventory contains 100 production Java files. The inherited/parameterised
JUnit counts are executed invocations, not source-file or assertion counts.
No new coverage threshold is introduced.

## Scope and continuing limits

Observed successful checkout, Java setup, tests, report uploads, package capture
and discovery now supply real hosted evidence for the workflow. Cache hit/miss
or speedup is not inferred merely from a successful setup step. Repository branch
protection/required-check settings were not changed or verified by this review.
A passing workflow alone does not establish merge enforcement.

This evidence concerns the exact runs and source/tree above. The documentation
follow-up branch will have a different commit and its own subsequent CI check.
No production Java, test, POM or workflow modification is needed to record these
observations. Historical benchmark means, errors, units and provenance limits
remain unchanged. Raw job logs and ZIP/XML artifacts are not copied into Git;
this Markdown preserves key identities, observed outcomes and integrity checks.
See the [closing traceability review](../00-project/12-closing-traceability-review.md)
for the selected module inventory, scoped Searching documentation resolutions
and research limitations that remain after this milestone.
