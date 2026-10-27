# Linux Environment: Applied Implementation and Verification

Reviewed on **2026-10-27**, from main
`5efd7f7b7cf5ddfa34a7380b01b6a7435299da8f` (PR #95, aggregation provenance).
This cross-cutting module connects the existing Java repository to an inspectable
Linux build environment. The next numbered module is **19 — CI/CD**.

## Scope and evidence chain

The ten notes cover Linux, filesystems, Bash, permissions, processes, variables,
Java, Maven and Git through the actual repository workflow. The executable
contribution is [check-environment.sh](../../scripts/linux/check-environment.sh)
with [offline subprocess tests](../../scripts/linux/test-check-environment.sh).
No production Java algorithm, new JUnit invocation or JMH family is added.

The chain is: source/contract and tests, then selected Linux/JDK environment,
wrapper build, present artifact identity, discovery, and separately attributed
measurement when justified. Environment checks support repeatable execution;
they do not establish correctness, warmup stability or algorithmic complexity.
Existing results keep their original environment, source and uncertainty.

The aggregation record retains source `5eee0dc` **inferred** from the author's
reflog and conversation sequence; its original jar hash is unavailable. A new
Linux build has a new artifact identity and cannot fill that old missing field.
The [Benchmarking guide](../17-benchmarking/implementation-and-benchmark-guide.md)
and [aggregation record](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
remain the measurement authorities.

## Verifier contract

Invoke `bash scripts/linux/check-environment.sh` from any current directory,
using the correct path to the script. Its repository root comes from the script's
location and must agree with Git. Paths containing spaces are supported.
Prerequisites are Linux Bash, Git, Java/Javac, readlink, sha256sum, date and uname,
with a usable `JAVA_HOME` and PATH selecting the same JDK 21 executables.

| Mode | Jar policy | Injected Java-option variables |
|---|---|---|
| No arguments | Report/hash nonempty target/benchmarks.jar if present; absence is acceptable | Report nonempty variable names; values withheld |
| `--require-benchmark` | Require nonempty regular jar and print its SHA-256 | Reject nonempty JAVA_TOOL_OPTIONS/JDK_JAVA_OPTIONS/_JAVA_OPTIONS |
| `--help` | No environment checks | Usage only |

Explicit contract exits: **0** success/help, **2** failed precondition/check,
**64** invalid argument usage. Unexpected external-tool failures can still
propagate a different nonzero exit; do not interpret that as success.

The verifier resolves PATH and JAVA_HOME Java/Javac paths, reads the runtime's
specification version and compiler version, and reports UTC capture time, source
SHA, dirty state, visible OS/kernel/CPU/architecture, Java selection, encoding,
locale variables and optional artifact hash. It does not print arbitrary VM-option
values or execute /etc/os-release as code. Launcher diagnostics are captured;
on failure inspect them locally, especially if options contain private values.

The preflight never installs software, edits configuration, downloads Maven,
builds a project, executes a jar, discards local changes or writes evidence files.
It launches Java/Javac metadata queries and reads the checkout/artifact. It does
not require a clean working tree: it reports changes so they stay visible.
`--require-benchmark` is an ordinary uninstrumented-protocol guard, not a detector
for every possible agent or runtime modification. Intentional profiling remains
separate. Maven's version/runtime is checked explicitly after preflight.

## Strategy, trace and reasoning

```text
CHECK(arguments): validate supported mode and required tools
                 require Linux and a script-relative Git root
                 require JAVA_HOME executables to match canonical PATH executables
                 require Java specification 21 and compiler 21
                 report source/environment and visible dirty state
                 if jar exists: hash its bytes and report freshness limitation
                 else if required: fail
                 else: report optional absence
```

A matching JDK 21 with no jar succeeds in environment-only mode, allowing tests
before packaging. The same state fails in required-jar mode. After a successful
benchmark package, required-jar mode emits the current SHA-256, while explicitly
leaving source-to-artifact freshness unproven by the hash alone. A Java 21
runtime paired with Javac 17 fails before reporting PASS.

Each prerequisite must hold before the final success message; the script neither
repairs a failed assumption nor substitutes a stale/missing artifact. The
property parser retains only selected metadata while iterating over captured
launcher output. Hashing is read-only; the offline tests compare artifact bytes
before/after. These are script-contract arguments, not formal proofs of the OS,
JVM or an arbitrary build's authenticity.

The verifier issues a bounded sequence of subprocess calls, but total work is
not constant in repository/artifact size. Hashing B artifact bytes takes Θ(B)
byte processing; Git status depends on the working tree and Git configuration.
Do not describe this preflight as an algorithm performance measurement. Tests,
metadata, hashing and setup stay outside JMH timed work.

## Filesystem portability changes

The wrapper content is unchanged; its tracked mode becomes **100755** from
100644. Both new scripts are executable. `.gitattributes` requests LF for
`/mvnw` and `/scripts/linux/*.sh` only. Java source, historical CSV and Windows
mvnw.cmd are unchanged. No broad renormalisation is performed.

Inspect with `git ls-files -s mvnw` and
`git check-attr text eol -- mvnw scripts/linux/check-environment.sh`.
Filesystems/mounts can impose further permission constraints. Explicit
`bash scripts/linux/check-environment.sh` avoids relying on the script's execute
bit; it does not fix an incompatible filesystem or replace Linux prerequisites.

## Windows and optional WSL path

The author currently uses Windows 11 and Oracle JDK 21.0.9. That Windows workflow
continues with `.\mvnw.cmd`; no Linux/WSL installation or author-side Linux
validation is claimed. To inspect an existing WSL installation from **PowerShell**:

```powershell
wsl --status
wsl --list --verbose
```

If a distribution already exists, open its Linux terminal. If WSL is absent,
consult Microsoft's installation documentation before choosing that environment;
this contribution runs no installer or machine configuration command. Native
Linux or an existing Linux VM are also suitable environments.

In WSL, use a separate Linux-filesystem checkout (for example under ~/projects)
and a Linux JDK. Microsoft's filesystem guidance recommends keeping work in the
filesystem of the tools being used. Sharing a Windows target directory or
invoking Windows java.exe from Linux introduces another environment boundary.
Keep OS/runtime/virtualisation identities separate in reports; WSL/container
measurements are not silently equivalent to bare-metal Linux or old Windows data.

## Applied Linux commands

Run from the root of a Linux checkout after merging/pulling this contribution.
First select an installed Linux JDK 21 using the environment's existing tooling.
The following derives JAVA_HOME from the selected **javac**, then the preflight
checks whether that selection is really 21. It installs nothing and affects only
the current shell's variables:

```bash
command -v javac
javac -version
export JAVA_HOME="$(dirname "$(dirname "$(readlink -f "$(command -v javac)")")")"
export PATH="$JAVA_HOME/bin:$PATH"
bash scripts/linux/check-environment.sh
```

Stop and correct the selection locally if the check fails. Once it passes:

```bash
bash scripts/linux/test-check-environment.sh
./mvnw -version
./mvnw clean test
```

Maven must report Java 21. Wrapper 3.9.11 may download its distribution or
missing dependencies, unlike the preflight. Stop after any failed command;
review proxy/network/cache setup without publishing credentials. The shell
suite uses local fake launchers to test failure handling; the real JVM/build
checks are separately recorded in the [verification report](verification-report.md).

For benchmark preparation, after successful tests:

```bash
./mvnw -Pbenchmark clean package
bash scripts/linux/check-environment.sh --require-benchmark
"$JAVA_HOME/bin/java" -jar target/benchmarks.jar '^org\.anaalvarezdev\.algorithms\.arrays\.ArrayAggregationBenchmark\.sum$' -lp
```

The profile runs 723 JUnit invocations, including 13 fixture checks; normal tests
run 710. The shell suite's 20 cases are separate. Discovery should show exactly
one aggregation method and five sizes. This is preparation, not a new timing run.
Do not edit source, pull, rebuild or run another clean between artifact capture
and the measurement being attributed. Record the actual full command and retain
the complete console; direct text is sufficient for Markdown evidence.

A controlled Linux run is optional and separate from this module's checks:

```bash
"$JAVA_HOME/bin/java" -jar target/benchmarks.jar '^org\.anaalvarezdev\.algorithms\.arrays\.ArrayAggregationBenchmark\.sum$' -p size=100,1000,10000,100000,1000000 -bm avgt -tu ns -wi 3 -i 5 -w 500ms -r 500ms -f 2 -t 1 -foe true
```

Capture fresh source/dirty state, artifact hash, environment, options, power/load
context, headers, both forks, iterations, result blocks and completion. Identify
native/VM/container/WSL explicitly. Do not pool a new run with the earlier
Windows observations. A separate `./mvnw -Pcoverage clean verify` produces ignored
coverage output and may remove the benchmark jar; it cannot recover that jar's
old hash. No Linux author timing is fabricated here.

## Primary references and limits

- [GNU Bash manual: quoting](https://www.gnu.org/software/bash/manual/html_node/Double-Quotes.html) and [set](https://www.gnu.org/software/bash/manual/html_node/The-Set-Builtin.html): argument handling and conditional exit semantics.
- [Git attributes](https://git-scm.com/docs/gitattributes) and [executable mode](https://git-scm.com/docs/git-update-index): scoped LF and tracked permissions.
- [Apache Maven Wrapper](https://maven.apache.org/tools/wrapper/): platform launchers and distribution bootstrap.
- [Java 21 launcher](https://docs.oracle.com/en/java/javase/21/docs/specs/man/java.html): version/properties and launcher options.
- [Microsoft WSL commands](https://learn.microsoft.com/en-us/windows/wsl/basic-commands) and [filesystems](https://learn.microsoft.com/en-us/windows/wsl/filesystems): existing-environment inspection and filesystem boundaries.

These engineering references describe the environment; CLRS remains the
algorithmic reasoning reference and is not used as authority for Bash/WSL APIs.
The verifier is a focused project prerequisite check, not a general machine
inventory, security audit, workload stabiliser or provenance attestation.
No GitHub Actions workflow is added; implementation belongs to module 19.
