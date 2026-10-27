# Terminal and Shell

A terminal provides an interface; Bash is the command interpreter used by this module's scripts. The verifier uses Bash arrays and indirection, so invoke it with Bash rather than `sh`. The Maven wrapper itself has its own shell requirements.

Double-quote paths and expansions: `"$JAVA_HOME/bin/java"` remains one argument when the directory contains spaces. A JMH regular expression is single-quoted so its metacharacters reach JMH unchanged. Avoid copying PowerShell syntax such as `$env:JAVA_HOME` into Bash, or Bash `export` into PowerShell.

An external command's exit status communicates success or failure. The preflight returns 0 on success/help, 2 on an environmental check failure, and 64 on unsupported arguments. `set -euo pipefail` assists error handling but has conditional/pipeline exceptions; critical checks also use explicit tests and error messages.

Use `command -v java`, `java -version` and `./mvnw -version` to investigate selection. The verifier additionally resolves executable paths and compares `PATH` with `JAVA_HOME`. Changing only the compiler release in pom.xml does not select the runtime. The guide contains the exact command sequence and expected stop points.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.
