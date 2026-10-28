# CI/CD: Implementation and Verification Record

Reviewed on **2026-10-27**, from main
`a0e174db482164027b086a0ddb986bf136c38b4a` (PR #96, Linux Environment).
The contribution adds [.github/workflows/maven-ci.yml](../../.github/workflows/maven-ci.yml)
and applies the nine CI/CD notes. Production Java, tests, POM, Linux scripts and
all historical research datasets remain unchanged.

**Hosted follow-up now observed:** PR run 40606662517 and merged-main run
40606676206 both succeeded. The [hosted record](first-hosted-run.md) retains
actual checkouts, per-job runtimes, downloaded artifacts and integrity checks.
The local results below remain the original implementation-time verification;
branch-protection enforcement and cache hit/speedup are not inferred.

## Implemented checks

The [guide](implementation-and-verification-guide.md) specifies events, job
contracts, ordering, failure traces, action pins, retention, metadata and review.
Maven CI handles main pushes, main-target PRs and manual dispatch. It has two
Ubuntu 24.04 / Temurin-21 jobs, fifteen-minute timeouts, read-only contents
permissions, disabled checkout credential persistence and PR/ref cancellation.

Verify runs Linux preflight, twenty shell cases, wrapper version and coverage
verify with failIfNoTests, requiring nonempty HTML/XML. The dependent package
job clean-builds the benchmark profile without coverage, captures source/dirty
state/environment and jar identity in generated Markdown, and checks discovery.
Diagnostics can upload after verify fails without masking that failure. The
success-only package upload retains the jar, provenance and JUnit XML. Requested
retention is seven days. No timed JMH research, release or deployment is added.

## Local verification boundaries

The six YAML run blocks were extracted and executed with Bash --noprofile
--norc -e -o pipefail. The local harness used the current worktree at the stated
base with workflow changes; the captured preflight correctly reported **dirty**.
It ran sequentially in one Linux worktree, preserving a coverage snapshot outside
the checkout before the package profile. This is command validation, not a claim
that GitHub's two hosted jobs or JavaScript actions ran locally.

| Local component | Observed value |
|---|---|
| OS/shell | Ubuntu 24.04.3 LTS container, Linux x86_64, Bash 5.2.21 |
| JDK | OpenJDK 21, 21+35-2513, javac 21 (not hosted Temurin installation) |
| Maven | Wrapper-selected 3.9.11 |
| Test runner / libraries | Surefire 3.2.5 / Jupiter 5.12.2 / AssertJ 3.27.3 |
| Coverage / harness | JaCoCo 0.8.13 / JMH 1.37 |
| Workflow linter | actionlint 1.7.12 |

Temporary Maven proxy/trust configuration and -XX:-UsePerfData were local
transport/namespace workarounds, not repository or workflow settings. The harness
used explicit **local-only** GitHub-context placeholders (local://verification,
local-only run ID, local-command-check event) solely to exercise provenance
formatting. They are not a real Actions run. Official setup/cache/upload services
were not exercised by that harness. Their later observed hosted execution is
recorded separately in the linked follow-up, including runtime/image differences.

| Check | Observed local outcome |
|---|---|
| YAML parse + actionlint | Pass |
| Bash syntax for all six run blocks | Pass |
| Linux prerequisite and wrapper-version blocks | Pass, selected Java 21 |
| Shell suite | **20 cases pass**, separately from JUnit |
| Coverage verify | **710 JUnit invocations**, zero failures/errors/skips, BUILD SUCCESS |
| Coverage output | Nonempty HTML/XML generated; 135 production classes analysed |
| Benchmark package | **723 JUnit invocations**, zero failures/errors/skips, BUILD SUCCESS |
| Package contents | Executable jar excludes fixture test classes and JaCoCo classes |
| Provenance | Generated Markdown contains actual checkout/dirty state and matching jar SHA-256 |
| Discovery | **115 methods**, unchanged inventory; exact aggregation method and size parameter found |
| Official action references | Tag refs match full pinned SHAs; pinned metadata matches tag metadata |
| Action runtime and inputs | All three declare Node 24; every configured input exists in pinned metadata |
| Surefire property | Local 3.2.5 plugin descriptor confirms failIfNoTests |

Actionlint was downloaded from its official v1.7.12 release and checked against
its published checksum. Archive SHA-256:

`8aca8db96f1b94770f1b0d72b6dddcb1ebb8123cb3712530b08cc387b349a3d8`

ShellCheck and Pyflakes integration were disabled because the tools were
unavailable locally. The separate Bash syntax/execution checks above were run;
this is not an unstated full static-analysis or hosted-workflow certification.

The last local coverage Maven invocation took **16.161 s**; package
invocation took **18.267 s**. These are build durations, not algorithm
latencies, and are not entered into any JMH result table. Counts are per
invocation; repeated suites do not increase distinct repository tests.

The newly produced **local-only verification jar** has SHA-256:

`ffe603fa6f70bb35358174e8121af8dd62d1f018eac8b2b33eedd1933bd67cf5`

This is distinct from the absent original Windows aggregation artifact.
Its actual checkout SHA and dirty state are printed in generated provenance;
it is not a universal rebuild hash or proof of an unobserved hosted package.
No historical unavailable artifact hash is replaced.

## First hosted execution record

| Field | Current status |
|---|---|
| Run URL / run attempt | [Main 40606676206](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40606676206), attempt 1; PR run also recorded in follow-up |
| Event / actual checkout SHA | main push: 0d0ade1f67ffd79db26c06be48a0bf13fdda724a; PR checked synthetic merge 659cdb7 |
| Verify conclusion | success; 710 JUnit invocations and 20 shell cases |
| Benchmark-package conclusion | success; 723 JUnit invocations, capture and discovery |
| Hosted runtime / image details | main verify: Temurin 21.0.12.1/image 20261012.334.1; package: Temurin 21.0.13/image 20261025.353.1 |
| Retained artifact IDs / jar SHA-256 | reports 12781895048; package 12781725315; independently verified jar digest and archive digests in hosted record |
| Branch-protection enforcement | Not changed by this contribution |

After the PR is opened, inspect both named jobs and retain their actual source,
run URL/attempt, conclusions and artifacts. Cancellation, skipped jobs and local
success are not substituted for a successful hosted run. If GitHub settings,
network, actions, permissions or uploads fail, record the observed failing step
and fix it through a reviewed change.

The prior missing-workflow finding is addressed at source level, and the
initial hosted jobs are now observed passing. Required-check enforcement and
any subsequent commit/run remain separate claims. No deployment, percentage/performance gate or new JSON/log/CSV evidence
file is added to Git. The [closing traceability review](../00-project/12-closing-traceability-review.md)
records the existing numbered modules through 19, scoped Searching documentation
corrections and the historical/provenance limitations that remain.
