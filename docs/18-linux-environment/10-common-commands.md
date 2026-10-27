# Common Commands

| Goal | Linux Bash command | What it establishes |
|---|---|---|
| Current location | `pwd` | Current shell directory |
| Inspect permissions | `ls -l mvnw` | Current filesystem mode |
| Current source | `git rev-parse HEAD` | Current commit, not an earlier execution |
| Local changes | `git status --short` | Current dirty state |
| Runtime/compiler | `java -version` / `javac -version` | Executables selected by PATH |
| Applied checks | `bash scripts/linux/check-environment.sh` | Linux/JDK alignment and optional identity |
| Offline shell tests | `bash scripts/linux/test-check-environment.sh` | Twenty subprocess contract cases |
| Maven selection | `./mvnw -version` | Wrapper Maven and build runtime |
| Normal tests | `./mvnw clean test` | JUnit correctness checks |
| Benchmark package | `./mvnw -Pbenchmark clean package` | Tests plus executable artifact |
| Required jar capture | `bash scripts/linux/check-environment.sh --require-benchmark` | Present nonempty artifact hash plus environment |
| Independent digest | `sha256sum target/benchmarks.jar` | Current artifact bytes |

Use the guide's anchored JMH selector for discovery after a successful package. A missing jar is reported explicitly; rebuilding later creates another artifact identity. Commands in this table do not establish old execution metadata.

The preflight returns 2 on a failed check and 64 on an unsupported argument. Keep its output with the next command/run's context. Console text can be shared directly and consolidated into Markdown without creating JSON/log/CSV evidence files.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.
