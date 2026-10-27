# File System

The filesystem root `/` is different from the Git repository root. The project contains source (`src/`), tracked documentation (`docs/`), wrapper configuration (`.mvn/`) and generated build output (`target/`). The applied script resolves its project root from its own location, then checks agreement with Git. It can run from another current directory and from paths containing spaces.

Linux paths use `/` and names are commonly case-sensitive. `JAVA_HOME` names an installed JDK directory, not its `bin` subdirectory. `/etc/os-release` contains distribution metadata; `/proc/cpuinfo` exposes the CPU view available to the current environment. The script reads selected values without executing those files as shell code.

`target/` is reproducible build output, ignored by Git. Maven `clean` removes generated output. Capture a benchmark jar's SHA-256 before a later clean/build; source history cannot recover a missing binary's previous hash. A later build is a new artifact even if source is unchanged.

For WSL-based development, the guide explains a separate Linux-filesystem checkout. Mounted Windows paths add filesystem and permission considerations; they are not silently interchangeable with the native checkout or previous timing conditions.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.
