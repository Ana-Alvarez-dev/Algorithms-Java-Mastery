# CI/CD: Ordered Documentation Review

Reviewed on **2026-10-29**, America/Buenos_Aires, against main
**`ca756c1b3f50ed2ab476b68b12615eca4899784b`**, PR #116 merging the Linux
documentation review. This closes the scoped **19 — CI/CD documentation
review**. After integration, continue with the existing
[closing traceability review](../00-project/12-closing-traceability-review.md).

## Scope and Decision

Read all nine numbered notes, README, implementation/verification guide,
original report and initial hosted record. Check their claims against the
actual YAML, POM/profile behaviour, Linux scripts, pinned action metadata and
current main run/jobs/logs/artifacts. The [workflow and evidence map](workflow-contracts.md)
connects event/source selection, job dependencies, failure conditions, exact
gate strength, runtime identities and retained outputs.

Fifteen Markdown paths change: twelve existing module files, two new map/review
documents and the project traceability review. The initial hosted record stays
byte-for-byte unchanged. No workflow, script, action pin, Java/JMH/test source,
POM, branch rule or historical result is modified. CI01-CI12 are scoped
documentation corrections/clarifications, not twelve new executable defects.

## Findings and Applied Corrections

| ID | Finding / ambiguity | Applied correction / evidence |
|---|---|---|
| CI01 | Event labels can imply every branch push or every PR activity runs | Map main push, main-target PR default types and manual dispatch; no path filter excludes Markdown |
| CI02 | PR head, event SHA and checked integration can be conflated | Preserve the original PR synthetic-merge identity; freshly confirm main checkout ca756c1 from logs/provenance and bind it to run/attempt |
| CI03 | Package dependency can be read as depending only on passing tests | Derive needs from the whole verify job, including diagnostic-upload outcome; preserve skipped/cancelled/failure distinctions |
| CI04 | Upload conditions can imply guaranteed diagnostics or complete path validation | Explain !cancelled() versus default success and correct no-files-found semantics: no matches differs from missing one listed path |
| CI05 | Observed counts and reports can be described as stronger gates | Separate failIfNoTests, nonempty HTML/XML and method/size-label grep from exact 710/723 counts, percentage thresholds and complete parameter inventories |
| CI06 | Local sequential blocks can be mistaken for two hosted jobs | Execute all six extracted run blocks with Bash failure handling, while attributing Actions services and separate runner jobs only to observed hosted evidence |
| CI07 | Concurrency, timeouts and selectors can imply immutable/fair/completing runs | State matching PR/ref supersession, per-job fifteen-minute timeouts and explicit per-job image/JDK patch observations |
| CI08 | Workflow permissions/passing checks can imply merge enforcement or deployment | Keep read permissions/credential settings separate from uninspected required-check rules and the absence of a deployment target |
| CI09 | Full SHA pins or Node metadata can be confused with freezing all tools or changing project language | Check three tags/pins and six uses/inputs; Node 24 runs actions, Java 21 runs the project; wrapper-selected Maven is separate |
| CI10 | Artifact IDs, ZIP digests, jar hashes and retention can be conflated | Verify both downloaded archive digests, inner jar/log/provenance identity, report XML and API expiry; preserve each object's role |
| CI11 | Initial hosted success can stand in for current/future commits | Add the exact current main run and fresh local evidence separately; the future documentation PR needs its own checks |
| CI12 | A checked package can imply research timing, a release or complete project correctness | Keep discovery/build durations, JUnit/coverage, finite proofs and historical JMH observations distinct; proceed to closing reconciliation without a fabricated deployment |

Each numbered note receives its applied clarification and map/review links.
The README and guide now distinguish what is gated from what is merely
observed. The original implementation verification gains a separately dated
follow-up; original hashes and the initial hosted record are preserved.

## Primary References Checked

Consulted **2026-10-29**, using official sources:

- [GitHub workflow syntax](https://docs.github.com/en/actions/reference/workflows-and-actions/workflow-syntax), [status functions](https://docs.github.com/en/actions/reference/workflows-and-actions/expressions), [events](https://docs.github.com/en/actions/reference/workflows-and-actions/events-that-trigger-workflows) and [concurrency](https://docs.github.com/en/actions/how-tos/write-workflows/choose-when-workflows-run/control-workflow-concurrency): selected event/default-shell, dependency, condition and supersession semantics.
- [GitHub contexts](https://docs.github.com/en/actions/reference/workflows-and-actions/contexts) and [protected branches](https://docs.github.com/en/repositories/configuring-branches-and-merges-in-your-repository/managing-protected-branches/about-protected-branches): source/run identity and the distinct enforcement mechanism. No repository administration setting is read or changed here.
- Pinned official action metadata: [checkout](https://github.com/actions/checkout/blob/3d3c42e5aac5ba805825da76410c181273ba90b1/action.yml), [setup-java](https://github.com/actions/setup-java/blob/b843ebf9b913146b38da2eaa7aeddf76074ae1ad/action.yml), [upload-artifact](https://github.com/actions/upload-artifact/blob/cf430e030ddbb5b0abf93d22962f4752f3646cd9/action.yml). Fresh tag resolution agrees with the pinned SHAs for v7.0.1/v6.1.0/v7.0.2 respectively; every configured input is present and all three declare Node 24.
- [Actionlint v1.7.12 release](https://github.com/rhysd/actionlint/releases/tag/v1.7.12): the downloaded Linux-amd64 archive is checked against its published SHA-256 before execution.
- Actual repository workflow/POM/scripts and GitHub run/job/log/artifact APIs provide the concrete gate and execution evidence below. These observed records are separate from general documentation semantics.

The source-defined failure traces are reasoning about this workflow, not fresh
injected failure/cancellation experiments on the Actions service. CLRS remains
the earlier algorithmic reference; no new PDF passage or figure inspection is
claimed as authority for Actions APIs.

## Fresh Local Command Verification

An isolated worktree executes unchanged workflow commands at the base source
above before the documentation edits. Environment: Ubuntu 24.04.3 LTS Linux
amd64 container, Bash 5.2.21, Git 2.51.1, **Amazon Corretto 21.0.13.0.11**,
runtime **21.0.13+11-LTS**, javac 21.0.13, UTF-8; wrapper-selected Maven
**3.9.11**, Surefire **3.2.5**, Jupiter **5.12.2**, AssertJ **3.27.3**,
JaCoCo **0.8.13**, JMH **1.37**. This is not the hosted Temurin environment.

| Actual local check | Outcome |
|---|---|
| actionlint 1.7.12, ShellCheck/Pyflakes integrations disabled | Pass; neither unavailable integration is claimed |
| All six YAML run blocks individually extracted and bash -n checked | Pass |
| Blocks executed in order with bash --noprofile --norc -e -o pipefail | All six pass |
| Verify prerequisite block | Real JDK preflight/version and 20 shell cases pass |
| Coverage block | BUILD SUCCESS; 710 tests, zero failures/errors/skips; nonempty HTML/XML; 135 classes |
| Benchmark package block | BUILD SUCCESS; 723 tests, zero failures/errors/skips, including 13 fixture invocations |
| Capture/discovery blocks | Generated local-only provenance, matching jar digest, exact aggregation method and size label |
| Action pin/metadata check | Three official tag identities match pins; six uses/configured inputs valid; Node 24 |

The six command blocks run sequentially in one local worktree, with a coverage
snapshot retained outside target before the package clean. No JavaScript
checkout/setup/cache/upload action or hosted scheduler is simulated locally.
Provenance formatting uses explicit local://command-review / local-only run
placeholders and local-command-review event; these are not actual GitHub IDs.
JAVA_HOME/PATH select the stated JDK, workflow MAVEN_ARGS are retained and
MAVEN_OPTS selects the standard Java trust store for the local build.

Local block execution spans **13:01:55Z to 13:02:37Z** (10:01:55 to 10:02:37
-03:00). Coverage Maven duration is **18.227 s**, finishing 10:02:15 -03:00;
package duration is **18.913 s**, finishing 10:02:36 -03:00. These are build
durations. The shade manifest-overlap warning did not prevent success.

Actionlint archive SHA-256:
`8aca8db96f1b94770f1b0d72b6dddcb1ebb8123cb3712530b08cc387b349a3d8`.
Fresh local jar SHA-256:
`5518bbecc7c52e597f5da3cf1aa02cbb2a79c018bf09acb36c704f4778f97d69`.
Fresh local coverage XML SHA-256:
`9d42e25d9db3c8a741c937a079f50340c2022c7f68a17126d469893cb1b78722`.
These identify local bytes only and replace no old or hosted identity.

## Fresh Observed Hosted Main Run

[Maven CI run 40877685400](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40877685400),
**attempt 1**, is a push to main at
`ca756c1b3f50ed2ab476b68b12615eca4899784b`. API status is completed/success;
logs and package provenance independently show that actual checkout and clean
Git state. It starts **2026-10-29T12:58:01Z**, with run metadata updated through
**12:59:11Z** (09:58:01 to 09:59:11 -03:00). Both job conclusions are success.

| Field | Tests and coverage | Benchmark package |
|---|---|---|
| Job ID | 123460633813 | 123460949450 |
| Job start/end UTC | 12:58:04 / 12:58:41 | 12:58:43 / 12:59:10 |
| Runner image | ubuntu-24.04 / 20261025.353.1 | ubuntu-24.04 / 20261025.353.1 |
| Java runtime | Temurin 21.0.13+9-LTS | Temurin 21.0.13+9-LTS |
| Compiler / vendor | javac 21.0.13 / Eclipse Adoptium | javac 21.0.13 / Eclipse Adoptium |
| Maven | 3.9.11 | 3.9.11 |
| JUnit suites / invocations | 89 / 710 | 90 / 723 |
| Failures / errors / skipped | 0 / 0 / 0 | 0 / 0 / 0 |
| Maven duration | 23.301 s | 15.925 s |
| Additional observed result | 20 shell cases, coverage generated/uploaded | Capture/discovery and package upload pass |

The matching patches/images are observations for these two jobs; the initial
hosted run's different patches/images remain recorded. No cache speedup,
physical-host equivalence or JMH latency is inferred from job/build duration.

### Downloaded Artifact Integrity

Both archives are downloaded via the connector and their SHA-256 independently
matches the API artifact digest. API expired=false at inspection:

| Artifact | ID | Archive bytes | API expiry UTC |
|---|---:|---:|---|
| verification-reports-ca756c1b3f50ed2ab476b68b12615eca4899784b-1 | 12898816886 | 644076 | 2026-11-05T12:58:37Z |
| benchmark-package-ca756c1b3f50ed2ab476b68b12615eca4899784b-1 | 12898742073 | 3585388 | 2026-11-05T12:59:07Z |

Verification archive SHA-256:
`6656a2bb0bcfc63bbaee154d6d9a92e95173ebfb486c43cb7c77930420bd8d7b`.
Package archive SHA-256:
`7b16faf18690a9ef76138f6379ca39eb6d0e0a18055fd32b05d042e40b8cc42d`.
Contained hosted jar SHA-256:
`a3a72b85f812ae4204d4a7a06383f8d85c65820e11429e2f85f23802c9114daa`.
Hosted coverage XML SHA-256:
`c3273ff329fdb22a67065954d75770d1e1198a9f45b8e6a66a004dfc8d757d4f`.

The inner jar digest agrees with ci-provenance.md and the job log. Its registry
has 115 nonempty benchmark entries; fixture-test and org/jacoco classes are
absent. The archive also contains package JUnit XML and the provenance record
for the exact real run/attempt, clean source and 12:59:06Z capture. The separate
verification archive supplies JUnit XML plus coverage. Downloaded XML confirms
the 710/723 totals, zero failures/errors/skips and Temurin runtime independently
of the log summaries. Thirteen additional package invocations belong to the
aggregation fixture class; repeated suites are not new distinct tests.

Current downloaded coverage counters, matching the fresh local snapshot:

| Counter | Covered | Missed | Total |
|---|---:|---:|---:|
| INSTRUCTION | 12631 | 115 | 12746 |
| BRANCH | 1511 | 107 | 1618 |
| LINE | 2163 | 30 | 2193 |
| COMPLEXITY | 1181 | 117 | 1298 |
| METHOD | 478 | 10 | 488 |
| CLASS | 135 | 0 | 135 |

These are observed counters, not a configured percentage gate or proof of
assertion adequacy/all input paths. The current archive's complete payload is
an inspected fact, not stronger semantics of if-no-files-found. Availability
and expiry belong to this inspection; versioned research stays in Markdown.

## Final Audit and Continuing Scope

The closing audit checks **319 Markdown files / 1680 relative file targets**,
with zero missing destinations, balanced fences and passing `git diff --check`.
All fifteen changed paths are Markdown within the declared scope; the initial
hosted record and all historical result files remain unchanged. No new local
heading-fragment link is introduced; external URLs and inherited fragments are
not exhaustively validated. Inventory remains **100 production Java files /
115 JMH methods**. Downloaded ZIP/XML, native build output and local diagnostics
stay outside versioned evidence; no new JSON, CSV, log or binary file is included.

The [initial hosted record](first-hosted-run.md), original local verification,
all earlier source/runtime/hash identities and every research result remain
unchanged. The earlier missing Windows jar hash is not filled by this new local
or hosted package. No timed benchmark or deployment is performed.

Repository protection/required-check settings are not inspected or changed.
Observed success concerns the main source/run above, not the future PR carrying
these Markdown changes. This closes only the ordered CI/CD documentation scope;
closing reconciliation can now review the full ordered chain after integration.
