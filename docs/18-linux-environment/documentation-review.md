# Linux Environment: Ordered Documentation Review

Reviewed on **2026-10-29**, America/Buenos_Aires, against main
**`07539e468523e3f1a96a84949386907b1abeb67c`**, PR #115 merging the Benchmarking
documentation review. This closes the scoped **18 — Linux Environment
documentation review**; next is **19 — CI/CD**, after integration.

## Scope and Decision

Read all ten numbered notes, README, implementation/verification guide and
original report. Check their claims against both Linux shell scripts, Maven
launcher/configuration, Git attributes/modes, actual selected JDK, build and
artifact output. Read the later CI workflow and hosted record to reconcile the
old absent-workflow status. The [script and evidence map](environment-contracts.md)
connects arguments, root/tool selection, failure paths, metadata, permissions,
file identity and the subsequent Java workflow.

Sixteen Markdown paths change: thirteen existing module files, two new map/
review files and the project traceability review. Scripts, modes, attributes,
Java/JMH/test sources, POM and workflow are unchanged. All historical result
files and the original Linux verification hash remain intact. The findings
are scoped documentation corrections/clarifications, not twelve new script
defects or an author-side Linux migration.

## Findings and Applied Corrections

| ID | Finding / boundary | Applied clarification |
|---|---|---|
| LI01 | README still says no GitHub Actions workflow exists | Link module 19's existing workflow and dated hosted record; retain local checks and future PR status separately |
| LI02 | Linux, caller directory and script location can be conflated | Explain physical script-relative root agreement with Git under the expected layout; visible metadata is not automatic virtualisation/host identification |
| LI03 | JDK alignment can imply stronger identity than the implementation checks | State canonical Java/Javac paths and feature 21; exact vendor/patch equality and the VM's os.name are not enforced |
| LI04 | 0/2/64 wording can imply exhaustive status normalisation | Identify explicit success/check/usage branches and other propagated nonzero tool errors; keep Bash features separate from mvnw's sh interpreter |
| LI05 | A failed script can be assumed to stop subsequent pasted commands | Distinguish child-shell options from the interactive caller and treat each guide line as a checked checkpoint |
| LI06 | Read-only can imply no subprocesses or an audit of all filesystem effects | Describe actual metadata launches and the observed Git-status/artifact-digest comparison; distinguish the temporary-fixture suite's writes |
| LI07 | Clean status and a jar digest can imply build freshness or JAR validity | Explain ignored target files, permitted dirty state, hashing of arbitrary fixture bytes, later format/discovery check and source/build/run linkage |
| LI08 | Execute mode and LF policy can be treated as one portable guarantee | Inspect tracked 100755, checkout execute bits, text/LF attributes and actual no-CR bytes separately; mount/interpreter requirements remain |
| LI09 | The option guard and metadata can imply a complete runtime inventory | Bound the three checked option variables, withheld diagnostics and selected literal metadata; effective Maven/JMH options remain separate inputs |
| LI10 | Fake launcher cases can be counted as real JDK or additional JUnit evidence | Map 20 existing subprocess cases to shell branches, separate the real JDK check, and retain 723=710+13 profile scope |
| LI11 | Wrapper versions and optional WSL guidance can imply installation or environment equivalence | Separate wrapper 3.3.4 from Maven 3.9.11, cached versus first-use downloads, existing WSL inspection and the explicit Linux-JDK/filesystem protocol |
| LI12 | A fresh Linux build/hash can replace older evidence | Record new runtime/build/identity checks separately, preserve the old Linux artifact hash and absent original author Windows jar identity, and run discovery without new timing |

The ten notes receive applied clarifications and links. The README and guide
now describe current CI status and the precise preflight guarantees. The
original report is explicitly historical with a separate current follow-up.
No new prerequisite, installer, test case, benchmark, configuration policy or
system change is introduced by this documentation review.

## Primary Sources and Reasoning

Consulted **2026-10-29**, using official sources:

- [GNU Bash set](https://www.gnu.org/s/bash/manual/html_node/The-Set-Builtin.html) and [builtins](https://www.gnu.org/s/bash/manual/html_node/Bash-Builtins.html): syntax checking, conditional errexit behaviour and external path lookup. The observed Bash here is 5.2.21; current manual behaviour is checked against these selected script uses.
- [GNU readlink](https://www.gnu.org/software/coreutils/manual/html_node/readlink-invocation.html): canonical path resolution and its failure boundary.
- [Git attributes](https://git-scm.com/docs/gitattributes), [update-index](https://git-scm.com/docs/git-update-index) and [status](https://git-scm.com/docs/git-status): LF conversion, executable mode and ignored-file visibility; actual repository modes/attributes are inspected separately.
- [Java 21 launcher](https://docs.oracle.com/en/java/javase/21/docs/specs/man/java.html) and [javac](https://docs.oracle.com/en/java/javase/21/docs/specs/man/javac.html): tool roles, version/properties and environment options. The script itself determines which fields/variables it actually checks.
- [Apache Maven Wrapper](https://maven.apache.org/tools/wrapper/): platform launchers, distribution selection/bootstrap. The project's properties identify wrapperVersion 3.3.4 and Maven distribution 3.9.11.
- [os-release specification](https://www.freedesktop.org/software/systemd/man/latest/os-release.html): distribution identification and literal shell-compatible assignments; this script reads only selected lines without sourcing the file.
- [Microsoft WSL commands](https://learn.microsoft.com/en-us/windows/wsl/basic-commands) and [filesystem guidance](https://learn.microsoft.com/en-us/windows/wsl/filesystems): status/list inspection and storing files with the tools' operating system. No WSL operation is executed in this review.

The branch/argument contract, selected metadata, bounded option guard and
temporary-fixture behaviour are derived from the actual scripts. The artifact
identity/freshness distinction is checked against their explicit output and
test fixture. The input-size cost discussion describes hashing/tree inspection,
not measured algorithm latency. CLRS remains an algorithmic reference from
earlier modules; no fresh PDF passage or figure inspection is needed or claimed
as authority for Bash, Git or WSL APIs.

## Fresh Native Verification

Verification runs in an isolated worktree with the base's executable sources
unchanged. Initial preflight and artifact capture occur before documentation
edits and report clean Git status at the base SHA. Subsequent edits are only
the declared Markdown paths. The environment is a **Linux container**, with
**Ubuntu 24.04.3 LTS**, kernel **6.18.55**, **x86_64**, **Bash 5.2.21** and
**Git 2.51.1**. Visible CPU is AMD EPYC 9V74 80-Core Processor; no reserved-core,
load, power or physical-host assertion follows.

JAVA_HOME and PATH explicitly select **Amazon Corretto 21.0.13.0.11**, runtime
**21.0.13+11-LTS**, compiler **javac 21.0.13**. The launcher reports UTF-8;
LANG/LC_ALL are C.UTF-8. Wrapper invocation reports **Maven 3.9.11** and that
same runtime. The profile uses compiler plugin **3.14.0** with release 21,
Surefire **3.2.5**, Jupiter **5.12.2**, AssertJ **3.27.3**, JMH **1.37**.
This differs from the old local OpenJDK build and the author's Oracle Windows VM.

| Actual command / check | Observed outcome |
|---|---|
| bash -n on both Linux scripts | Syntax passes; no claim of executing those commands from syntax alone |
| bash scripts/linux/test-check-environment.sh | All 20 existing subprocess cases pass; temporary fixtures cleaned on exit |
| Real bash scripts/linux/check-environment.sh before packaging | PASS; clean base; selected real JDK 21; optional missing jar reported |
| ./mvnw -o --batch-mode --no-transfer-progress -Pbenchmark -DfailIfNoTests=true clean package | BUILD SUCCESS; 723 tests, zero failures/errors/skips; executable jar |
| Real preflight with --require-benchmark after packaging | PASS; current jar hash reported; selected Java/Javac paths agree |
| Git porcelain status and independent sha256sum before/after that preflight | Both unchanged; no broader filesystem audit inferred |
| Explicit Java 21 jar -l | 115 registered methods in 40 benchmark classes |
| Exact aggregation selector with -lp | One method and five default sizes, without timing |
| ./mvnw -version | Maven 3.9.11 and selected Corretto 21.0.13 |
| Git modes / attributes / checkout bytes | mvnw and both scripts 100755, executable locally, text/LF requested, zero CR bytes |

The initial real preflight capture is **12:38:08Z**; packaging finishes
**09:39:15 -03:00**, Maven duration **19.550 s**; required-jar capture is
**12:39:54Z** (**09:39:54 -03:00**). These are build/metadata observations,
not benchmark latencies. Maven uses the populated offline cache and the
standard Java trust store selected through MAVEN_OPTS. That option is recorded
as a Maven verification setting, not an ordinary performance-run setting.
The shade manifest-overlap warning did not prevent packaging success.

Surefire XML independently confirms **723** invocations with **13** in
ArrayAggregationFixtureTest. The normal 710-test scope is included in that
profile build; a new separate normal/coverage build is not claimed. Twenty
shell cases are not additional JUnit invocations, and repeats across reviews
do not create new distinct tests. Fake Java/Javac/uname outputs in that suite
test the shell interface; the real launcher/build observations above are separate.

The new local artifact SHA-256, agreed by the preflight and independent digest:

`9f23c369be44638fd02d86a94533c24e770bf2da78e5bc0a9395525b016ff0e9`

It identifies this verification jar only. The original Linux artifact hash
and the author's missing original Windows jar identity are not replaced.
No new JMH timed run, profiler, coverage percentage, WSL installation, author
Linux execution or hosted CI status is inferred. Discovery is only metadata.

## Final Audit and Evidence Boundaries

The closing audit checks **317 Markdown files / 1643 relative file targets**,
with zero missing destinations, balanced fences and passing `git diff --check`.
All sixteen changed paths are Markdown within the declared scope. No new local
heading-fragment links are introduced; external URLs and inherited fragments
are not exhaustively validated. Inventory remains **100 production Java files /
115 JMH methods**. Scripts, modes, attributes and all historical result files
remain unchanged. Generated jar/reports and temporary diagnostics remain outside
versioned evidence; no new JSON, CSV, log or binary file is included.

The [original verification](verification-report.md) retains its 2026-10-27
runtime, dirty contribution state, 710/723 builds, twenty shell cases and jar
hash. The [aggregation record](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
retains all author observations and its reflog-inferred source, absent original
jar hash and missing execution metadata. A new Linux package supplies none of
that missing historical provenance or a Windows-versus-Linux performance result.

The [existing CI workflow](../../.github/workflows/maven-ci.yml) and
[initial hosted evidence](../19-ci-cd/first-hosted-run.md) supersede absent-CI
language, with their own dated executions. This closes only the ordered Linux
documentation review, ready for the existing **19 — CI/CD** module next.
