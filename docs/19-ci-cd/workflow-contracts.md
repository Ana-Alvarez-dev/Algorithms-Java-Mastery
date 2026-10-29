# CI/CD: Applied Workflow and Evidence Map

Reviewed on **2026-10-29**, against main
`ca756c1b3f50ed2ab476b68b12615eca4899784b` (PR #116). The
[documentation review](documentation-review.md) records CI01-CI12, fresh local
checks and the observed main run. The [workflow](../../.github/workflows/maven-ci.yml)
is the executable definition; this map explains its existing guarantees without
adding a deployment, gate or repository rule.

## 1. Events and Source Identity

| Configured event | Selected source / scope | Boundary |
|---|---|---|
| push on main | Main event commit | A feature-branch push alone does not trigger this workflow |
| pull_request targeting main | Default PR merge checkout | Default activity types are opened, synchronize and reopened; the branch filter refers to the base |
| workflow_dispatch | Selected dispatch ref, subject to availability/permissions | Availability on the default branch is separate from a feature push |

There are no path filters, so documentation-only changes are not excluded by
path. The PR API head, event SHA/ref and actual Git checkout are distinct
identities. A PR default merge checkout tests the candidate integration; it
must not be relabelled as the feature-head commit. Main push identity can agree
with the actual checkout, as confirmed by the latest observed main logs.
Record run ID, attempt, event and actual checked SHA before attributing a result.

The concurrency group uses workflow name plus PR number, falling back to ref.
Runs in different PR groups are independent; matching groups can supersede old
pending/running work because cancel-in-progress is true. Main push and manual
dispatch on the same ref can share that group. Cancellation is not a successful
verification and this policy does not promise that every queued run completes.

## 2. Jobs and Failure Propagation

| Job | Ordered work | Required predecessor |
|---|---|---|
| verify | Checkout, Java setup, Linux preflight, 20 shell cases, Maven version, clean coverage verify, nonempty reports, diagnostic upload | Configured event |
| benchmark-package | New checkout/Java setup, preflight/version, clean benchmark package, provenance capture, discovery, package upload | The entire verify job succeeds |

Each job has its own hosted environment and fifteen-minute timeout. The two
jobs do not share target as an implicit workspace. Dependency cache reuse is
different from reusing compiled output. Job timeouts are not a fifteen-minute
whole-workflow or queue-time guarantee. Java 21 and ubuntu-24.04 select families;
record exact patch/image for each job. The initial hosted record had different
patches/images across its jobs; the newly observed main run uses matching ones.

| Condition | Diagnostic upload in verify | Dependent package / final package upload |
|---|---|---|
| All verify steps succeed | Eligible | Package eligible; its final upload requires prior package steps to succeed |
| Tests, report checks or another verify step fail | Eligible if not cancelled, even after that failure | verify remains failed; dependent package skipped |
| Diagnostic upload itself fails after tests pass | Its failure contributes to job failure | Dependency still requires the complete verify job |
| Workflow cancelled | !cancelled() is false | Cancelled/skipped outcomes are not passing checks |
| Package build, capture or discovery fails | Separate verify outcome remains recorded | Default success condition prevents the final package upload |

The explicit diagnostic status function permits an upload attempt after earlier
failure. It neither guarantees diagnostic availability nor repairs a failed
job. The success-only upload has no such override. These failure traces follow
the YAML and documented Actions semantics; this review does not inject failing
or cancelled hosted runs to test every path.

The explicit Bash default uses --noprofile --norc with -e and pipefail. The six
run blocks are separate step processes; source/workspace files persist within
a job, but a shell export in one step is not automatically exported to another.
Workflow-level MAVEN_ARGS applies to the Maven commands. The local command
harness checks those blocks sequentially, without simulating GitHub scheduling,
JavaScript actions, cache service or upload service.

## 3. Implemented Gates and Recorded Observations

| Check | What is enforced | What is recorded separately |
|---|---|---|
| Maven commands and default failure propagation | Nonzero build/test status blocks normal progression | Actual test outcomes and first failing step |
| failIfNoTests=true | Empty Surefire discovery fails | Exact 710/723 counts and zero skipped cases are observations, not exact-count gates |
| JaCoCo report plus test -s | Both HTML index and XML are present and nonempty | XML counters, assertion adequacy and percentages; no JaCoCo check/threshold |
| Required benchmark preflight | Selected environment and nonempty artifact with a digest | Build/source linkage needs adjacent context; hash alone proves neither freshness nor execution |
| Exact discovery grep checks | Aggregation method line and a size parameter label exist | Five values and 115 methods/40 classes are current inventory, not hard-coded count/value-set gates |
| Diagnostic upload with warn | No matching files produces a warning | Partial availability; no promise of a complete report after early failure |
| Package upload with error | No matching files produces an error | Does not independently require every listed path when some files match |

The current artifact contains jar, provenance and JUnit XML, and the generating
steps supply their current evidence. The upload option itself is not an
all-requested-files validator. A successful upload of a partial matching set
must not be described as a separate check of every listed path. Coverage's two
nonempty-file guards and the jar preflight are concrete earlier checks.

The normal suite is 710 invocations. Benchmark source activation adds thirteen
aggregation fixture cases for 723. Their repeated execution across jobs does
not make 1,433 distinct tests; the twenty shell cases are another category.
The [Testing map](../16-testing/test-contracts.md) and
[Linux map](../18-linux-environment/environment-contracts.md) define those
domains. Discovery and package duration are not timed JMH research. No numeric
algorithm-performance threshold is implemented.

## 4. Action Source, Permissions and Runtime

The workflow uses checkout, setup-java and upload-artifact twice each: six uses,
three distinct full SHA pins. Fresh verification checks every configured input
against metadata at that pinned SHA, current reviewed tag correspondence and
the declared Node 24 action runtime. Node runs the GitHub actions; project
compilation/tests still run on Java 21. Pinning action source does not freeze
all downloads, the runner image, the dependency cache or the selected JDK patch.

The workflow requests contents:read and disables persisted checkout credentials.
It declares no deployment environment or release publication. Those scoped
settings are independent of branch protection/required checks: a YAML workflow
and passing jobs do not themselves require a user to wait for checks before
merging. This review does not inspect or change repository enforcement settings.

setup-java caches Maven dependencies with the POM and wrapper properties as
configured key inputs. No target cache is declared. Cache hit/miss and speedup
must be observed separately; action/setup success alone does not establish a
hit or explain build duration. The wrapper selects Maven 3.9.11; its bootstrap
version is separate. The POM controls tests, profiles and dependencies.

## 5. Artifact Identity and Retention

The verify artifact selects JUnit TEST-*.xml plus target/site/jacoco. The package
artifact selects benchmarks.jar, ci-provenance.md and that job's JUnit XML.
Names include event SHA and run attempt; actual checked Git SHA is also captured
inside provenance. The capture includes event/run context, timestamp, dirty
state, Java/environment metadata and the jar SHA-256, followed by Maven version.
It is adjacent to a successful package and before discovery/upload.

| Identity | Bytes / object identified | Remaining limit |
|---|---|---|
| Git commit | Source history node, with separate working-tree status | Not artifact bytes |
| Run + attempt + job | Particular execution record | A later attempt or commit has another outcome |
| GitHub artifact ID | Retained upload object | Availability can end through expiry/deletion |
| Upload/archive SHA-256 | Uploaded ZIP bytes in this archive mode | Different from its contained jar hash |
| Jar SHA-256 | The contained executable jar bytes | Not the earlier missing Windows artifact or proof of past execution by itself |

Seven-day retention is requested, subject to service/repository policy; actual
API expiry and expired status belong to the inspected artifact record. Archive
download and digest verification permit inspection while available. Versioned
Markdown preserves important observed identities and limits; raw logs/ZIP/XML
need not be committed. The original Windows aggregation hash stays unavailable.
No release or deployment follows from retaining a CI package.

## 6. Evidence Layers and Continuation

| Layer | Current review evidence | Scope |
|---|---|---|
| Static workflow | actionlint 1.7.12; six Bash syntax checks; pinned action metadata | No claim that every static-analysis integration ran |
| Local commands | Twenty shell cases; 710 coverage / 723 package tests; capture and discovery | Corretto/Linux command validation with explicit local-only run placeholders |
| Hosted main execution | Run 40877685400, attempt 1; both jobs success; actual checkout ca756c1 | Observed Temurin/Actions environment at that exact main |
| Downloaded outputs | API archive digests, JUnit XML, coverage, provenance and inner jar verified | Independent inspection of those retained bytes |
| Historical evidence | First hosted record and earlier research retained | Their original source/runtime/hash limits stay dated |

The [fresh review](documentation-review.md) records run/job IDs, timestamps,
counter values and artifact hashes. Current hosted success does not attest the
future PR carrying this documentation. Syntax checking, successful commands,
complete finite tests and coverage supply different evidence; none is a universal
algorithm-correctness proof or enforcement setting.

This is the final existing numbered subject, **19 — CI/CD**. After integration,
continue with the [closing traceability review](../00-project/12-closing-traceability-review.md)
to reconcile the ordered reviews and remaining scoped limitations. A production
deployment or a new numbered module is not introduced by this review.
