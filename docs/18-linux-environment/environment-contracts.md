# Linux Environment: Applied Script and Evidence Map

Reviewed on **2026-10-29**, against main
`07539e468523e3f1a96a84949386907b1abeb67c` (PR #115). The
[documentation review](documentation-review.md) records LI01-LI12 and actual
verification. The [execution guide](implementation-and-verification-guide.md)
provides the Linux sequence and optional WSL context. This map describes the
existing scripts; no installer, configuration migration or new algorithm is
introduced by the ordered review.

## 1. Interpreter, Root and Argument Contract

The [preflight](../../scripts/linux/check-environment.sh) is a Bash program,
using arrays, [[ tests, here-strings and indirect variable expansion. Run it
as a child with bash and the correct script path. Its shell options and exit
calls belong to that process; sourcing it is a different execution model.
The [Maven launcher](../../mvnw) has a /bin/sh shebang and its own requirements.
The two scripts must not be assumed to need the same interpreter.

The preflight derives root from BASH_SOURCE's script directory, changes to its
physical parent root and requires pom.xml/mvnw plus agreement with Git's top
level. It does not infer repository identity from the caller's current
directory. The tests cover another working directory and a path containing
spaces. This guarantee assumes the documented repository/script layout; it is
not arbitrary relocation or an authenticity check on a directory's contents.

| Invocation | Environment / jar policy | Explicit outcome |
|---|---|---|
| No arguments | Check environment; hash a present nonempty regular file at target/benchmarks.jar; absence allowed | 0 after checks pass |
| --require-benchmark | Same checks, require that file and empty three guarded Java-option variables | 0 after checks pass |
| --help alone | Print usage before tool/JDK/root checks | 0 |
| Unsupported argument, empty argument or additional arguments | Print usage; perform no normal preflight | 64 |
| Deliberate failed prerequisite/check | Print ERROR message | 2 |

Unexpected external-command failures can propagate another nonzero status.
The 0/2/64 interface describes explicit branches, not every possible shell/tool
failure. The final PASS line is reached only after its required checks succeed.
It does not mean that later Maven or JMH commands ran.

set -e assists failure handling, set -u catches unguarded unset expansions, and
pipefail changes pipeline status. Their contexts matter: tests, conditionals
and some command lists are exceptions to errexit. Explicit guards implement
the important script branches. A failed child command in an interactive shell
also does not prevent the next pasted line from running. Treat each guide line
as a checkpoint; automation needs its own checked control flow.

## 2. Java Selection and Version Boundary

The preflight requires externally located uname, Git, java, javac, readlink,
sha256sum and date. uname -s must return Linux. JAVA_HOME must be nonempty and
contain executable bin/java and bin/javac. For Java and Javac separately, it
resolves executable paths and compares them with the corresponding canonical
JAVA_HOME path. Quoting keeps paths containing spaces as individual arguments.

Runtime properties must report java.specification.version=21; the compiler
version line must identify javac 21 followed by a dot, whitespace or line end.
Folder names alone do not establish this.
These are **feature-version and path checks**, not a vendor/patch equality
policy. Runtime/vendor metadata is printed; exact Java and compiler patch
identity is not compared by the script. The protocol calls for a Linux JDK;
the program checks Linux uname and selected metadata, without separately
verifying the VM's os.name or every wrapper/symlink implementation.

The current real check resolves both tools to the selected Corretto JDK 21
and observes javac 21.0.13. The fake-launcher tests cover rejection of runtime
17, compiler 17, a missing JAVA_HOME, path disagreement and malformed properties.
Those injected outputs test the shell contract; they do not validate a real
Java installation. Trust in the selected tools remains an execution assumption.

The guide's JAVA_HOME derivation follows an already selected javac; it neither
installs a JDK nor selects version 21 automatically. Confirm that compiler
first, export variables in the current shell, then run the preflight. Its
environment changes are not applied to shell startup files. Run ./mvnw -version
separately to observe Maven's own runtime. Compiler release=21 describes the
compilation target, not launcher selection.

## 3. Options, Metadata and Visibility

The guarded variables are JAVA_TOOL_OPTIONS, JDK_JAVA_OPTIONS and _JAVA_OPTIONS.
Their nonempty names are reported without their values. Environment-only mode
may continue when those options still allow the metadata queries to succeed;
required-jar mode rejects them before those launcher queries. Nothing silently
clears them. Captured launcher diagnostics are withheld on failure, with a
message directing local inspection. There is no full environment dump.

That finite check does not inspect every Maven/JMH configuration or explicit
VM argument. For example, Maven runtime options and the eventual JMH command
are separate inputs. Retain effective runtime headers and actual commands for
measurements. A PASS is not a universal absence-of-instrumentation claim.

The script prints UTC capture time, source SHA, visible Git state, kernel/
architecture, selected OS/CPU fields, Java tools/runtime/encoding and LANG/
LC_ALL. os-release is read as selected key/value lines, without sourcing it;
displayed quotes remain literal. This is not a general parser for every field
or an expansion of shell expressions. OS/CPU files are optional observations.
LANG/LC_ALL/file.encoding are recorded context, not a complete locale inventory.

uname and /proc expose the current environment's view. A container's visible
CPU name does not establish reserved cores, host load, governor or thermal
conditions. The script does not classify native Linux versus WSL/VM/container
automatically. Record that context separately. Process visibility and JVM forks
are distinct from the number of benchmark method calls.

## 4. Source, File Bytes and Build Sequence

| Observation | Established fact | Remaining boundary |
|---|---|---|
| Git HEAD | Current commit | Uncommitted work needs its own status/diff |
| Porcelain status | Reported tracked/untracked changes; dirty is allowed | Ignored target output is not listed; clean does not mean no generated artifacts |
| Present nonempty file and SHA-256 | Identity of bytes at the conventional artifact path | No ZIP/manifest validity, discovery, build freshness or earlier execution proof |
| Successful clean profile build | Observed tests and packaging at captured source/state | Keep that build record; later changes/cleanup need another attribution |
| Anchored JMH -lp | Selected method and parameter registration | No algorithm timing or validity of every fixture |

The preflight never builds or executes the artifact. Its shell fixture
deliberately places arbitrary bytes at the jar path and still expects a digest;
this demonstrates the identity-only contract. Real JMH discovery is the later
format/registration check. Hashing a stale valid jar can also succeed; do not
interpret presence or a digest as freshness.

The documented sequence is environment check, shell cases, Maven/runtime and
functional build, clean benchmark package, required-file capture, discovery,
then separately attributed timing if selected. The wrapper version is 3.3.4;
its distribution configuration selects **Maven 3.9.11**. Maven and its
dependencies may need downloads on first use. The preflight does no Maven
bootstrap or installation. Current verification uses the populated offline
cache, not a claim that a fresh machine can build offline.

A later clean removes target, including jar and reports. Capture evidence and
the actual jar hash before cleanup; a later rebuild cannot restore a missing
earlier hash. Equal filenames or a shared source commit do not identify equal
artifact bytes. The author aggregation record's original jar remains absent.

## 5. Permissions, Endings and Side Effects

Git tracks mvnw and both Linux scripts as 100755. In the reviewed checkout all
three have an executable filesystem mode and no CR bytes. The
[attributes](../../.gitattributes) request text/LF only for these launchers;
they do not set executable mode, change Windows mvnw.cmd or normalise all source.
Tracked mode, checkout bytes and mount execution policy are separate facts.
Invoking bash script-path reads a script without needing its execute bit; the
interpreter and any programs it launches still need a usable environment.

The preflight's intended effect is repository/artifact inspection and console
output, including short Java/Javac metadata subprocesses. It does not edit
source, discard changes, install tools or write evidence files. The fresh
read-only check compares reported Git status and artifact digest before/after;
it is not an audit of every filesystem effect of external programs.

By contrast, the [shell suite](../../scripts/linux/test-check-environment.sh)
creates a unique temporary fixture tree, fake launchers and a local test Git
commit. Its EXIT trap removes its own generated directory. It relies on real
Bash/Git/core utilities while substituting Java/Javac/uname observations. These
twenty cases are controlled subprocess checks, distinct from real environment
validation and the 723 Java tests. Their count is not 743 JUnit invocations.

The preflight has a fixed control-flow structure but its work is not constant
in input bytes. Hashing B artifact bytes processes Theta(B) data; Git status
cost depends on tree/configuration. Process startup, tool execution and file
access add work. None of this is the algorithm's JMH latency.

## 6. Windows, WSL and CI Status

Windows remains supported with .\mvnw.cmd. PowerShell wsl --status and
wsl --list --verbose inspect an existing WSL installation; they do not install
one. A Linux terminal, Linux JDK and Linux-filesystem checkout give the guide's
selected WSL workflow. Microsoft recommends storing files with the OS tools
used to work on them; mounted Windows files remain another filesystem context.
No local WSL availability, migration or author Linux benchmark is assumed here.

The [workflow](../../.github/workflows/maven-ci.yml) already invokes these Linux
checks, with [dated hosted evidence](../19-ci-cd/first-hosted-run.md). The old
README's absent-workflow status is superseded. This review's local environment,
test and package checks remain separately attributed; no fresh hosted outcome
or Windows-versus-Linux performance effect is inferred. The next ordered
documentation review is **19 — CI/CD**.
