# Linux Environment

This module applies Linux filesystems, Bash, permissions, processes, Java, Maven
and Git to the existing Algorithms Java Mastery repository. It follows
Benchmarking and prepares **19 — CI/CD**. Code, documentation and evidence remain
in English. The author can continue using the existing Windows workflow;
Linux/WSL availability on the author's machine is not assumed.

## Applied contribution

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
| [08 — Java development environment](08-java-development-environment.md) | Are both selected tools from Linux JDK 21? |
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

Stop after a failed step. The preflight does not install tools or download Maven;
the separate wrapper invocation may bootstrap its pinned distribution. For
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
CSV exports are introduced. No GitHub Actions workflow is implemented yet.
