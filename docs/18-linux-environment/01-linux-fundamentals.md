# Linux Fundamentals

Linux is an execution environment for this Java repository. A native installation, virtual machine, container and WSL can expose a Linux interface while providing different scheduling, storage and resource conditions. Record the actual environment rather than inferring a physical machine from `uname`.

The operating system hosts processes and file access; Bash interprets commands; the JVM executes Java bytecode. Those layers have distinct responsibilities. Moving the same aggregation source between Windows and Linux does not change its Θ(n) source operation count. Concrete elapsed times can change and require a new attributed experiment.

The applied verifier requires Linux and an aligned JDK 21. It reports visible kernel, architecture, OS, CPU and source state, without installing software or changing a machine. Its success is a prerequisite check, not a stability certificate, correctness proof or passing CI.

From an existing Linux checkout run `bash scripts/linux/check-environment.sh`. Windows users first consult the guide's optional WSL path; PowerShell remains supported for the existing Windows workflow. No WSL installation or author Linux execution is assumed.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.
