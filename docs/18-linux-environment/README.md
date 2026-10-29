# Linux Environment

This module applies Linux filesystems, Bash, permissions, processes, Java, Maven
and Git to the existing Algorithms Java Mastery repository. It follows
Benchmarking and prepares **19 — CI/CD**. Code, documentation and evidence remain
in English. The author can continue using the existing Windows workflow;
Linux/WSL availability on the author's machine is not assumed.

## Original applied contribution — 2026-10-27

- [Implementation and verification guide](implementation-and-verification-guide.md): scope, script contract, pseudocode, trace, reasoning, exact Linux commands and primary references.
- [Verification report](verification-report.md): real local environment/build checks, twenty offline shell cases and explicit limits.
- [Environment verifier](../../scripts/linux/check-environment.sh): Linux/JDK selection, source/dirty state, environment and optional jar identity.
- [Offline subprocess tests](../../scripts/linux/test-check-environment.sh): controlled fake launchers and failure cases, with no network or project build.

The wrapper now has tracked executable mode 100755, and targeted Git attributes
retain LF in mvnw/Linux scripts. Production Java, JMH methods, JUnit tests,
POM and Windows mvnw.cmd are unchanged. The shell suite adds **20 separate
cases**, not extra JUnit invocations. Real Linux/JDK 21 verification passes
**710 normal tests** and **723 with -Pbenchmark**; discovery retains **115
methods in 40 classes**. These are local checks, not an author WSL run or CI.

## Ordered documentation review — 2026-10-29

The [review record](documentation-review.md), based on main
`07539e468523e3f1a96a84949386907b1abeb67c` (PR #115), and
[script/evidence map](environment-contracts.md) connect LI01-LI12 to the actual
root, path/version, exit, permission, option and artifact contracts. Fresh
verification passes **20 shell cases** and **723 Java 21 tests**, checks the
real preflight before/after packaging and confirms unchanged jar bytes/status.
The earlier 710-test normal build above retains its original date; this review
executes the normal test scope within the benchmark-profile build.

The [Maven CI workflow](../../.github/workflows/maven-ci.yml) and
[dated hosted record](../19-ci-cd/first-hosted-run.md) already exist. They are
separate from this local review and supersede the earlier absent-workflow
status. This follow-up changes only Markdown; next is the ordered review of
**19 — CI/CD**.

## Study notes

| Note | Applied question |
|---|---|
| [01 — Linux fundamentals](01-linux-fundamentals.md) | Which environment layer is running the JVM? |
| [02 — File system](02-file-system.md) | Which files are source, metadata and replaceable build output? |
| [03 — Terminal and shell](03-terminal-and-shell.md) | Which interpreter and argument/exit semantics apply? |
| [04 — Files and directories](04-files-and-directories.md) | What can be inspected without changing artifacts? |
| [05 — Permissions](05-permissions.md) | Are execute mode and line endings valid for Linux launchers? |
| [06 — Processes](06-processes.md) | Which build/test/fork process is being observed? |
| [07 — Environment variables](07-environment-variables.md) | Do PATH, JAVA_HOME and injected options agree with the protocol? |
| [08 — Java development environment](08-java-development-environment.md) | Which tool paths and feature versions are checked? |
| [09 — Maven and Git](09-maven-and-git.md) | How are source, successful build and artifact identity linked? |
| [10 — Common commands](10-common-commands.md) | Which command provides each specific piece of evidence? |

## Linux quick check

After selecting an installed Linux JDK 21 and pulling this module:

```bash
bash scripts/linux/check-environment.sh
bash scripts/linux/test-check-environment.sh
./mvnw -version
./mvnw clean test
```

Run each line as a checkpoint and stop after a failed step; the interactive
shell does not automatically prevent the next pasted line. The preflight does
not install tools or download Maven; the separate wrapper invocation may
bootstrap its pinned distribution. For
benchmark preparation, the guide adds clean packaging, required-jar capture and
exact discovery. Hashing a present jar does not prove which source built it.

Windows users can inspect an existing WSL setup with `wsl --status` and
`wsl --list --verbose` in PowerShell, as explained in the guide. No installation,
partitioning, configuration change or migration of the user's repository is
performed by this contribution. Bash commands belong in a Linux terminal;
Windows Maven commands remain `.\mvnw.cmd`.

## Evidence boundaries

The [aggregation record](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
retains the earlier Windows observations, reflog-inferred source and unavailable
original jar hash. This module does not replace those fields with a newly built
Linux artifact. There is no new author Linux timing dataset or newly measured
OS comparison. Metadata, correctness tests and performance evidence remain
separate. Console observations can be consolidated in Markdown; no JSON/log/new
CSV exports are introduced. The later module 19 workflow now reuses these
Linux checks; its actual hosted evidence remains separately dated.
