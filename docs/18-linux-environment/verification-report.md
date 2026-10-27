# Linux Environment: Implementation and Local Verification

Verified on **2026-10-27**, from main
`5efd7f7b7cf5ddfa34a7380b01b6a7435299da8f` (PR #95, aggregation provenance),
with this contribution present as working-tree changes. The preflight correctly
reported that local tree as **dirty**; the base SHA alone is not represented as
the full modified source. A later published contribution commit identifies it.

## Applied scope

The [guide](implementation-and-verification-guide.md) connects all ten notes to
an inspectable contract, pseudocode, trace, reasoning, failure cases, build and
artifact identity. The executable contribution consists of the read-only
[preflight](../../scripts/linux/check-environment.sh) and
[subprocess tests](../../scripts/linux/test-check-environment.sh).
Production Java, JMH code, JUnit tests, POM and Windows mvnw.cmd are unchanged.
There is no new benchmark question or timing dataset.

The mvnw content is byte-identical to the base; its tracked mode changes from
100644 to **100755**. Both shell scripts are executable. Scoped attributes
request LF for mvnw and scripts/linux/*.sh; no broad normalisation occurs.

## Real local verification

| Component | Observed local environment |
|---|---|
| Distribution | Ubuntu 24.04.3 LTS, container |
| Kernel / architecture | Linux 6.18.55 / x86_64 |
| Shell | GNU Bash 5.2.21 |
| Git | 2.51.1 |
| Selected JDK | OpenJDK 21, runtime 21+35-2513; javac 21 |
| Runtime vendor property | Oracle Corporation |
| Wrapper-selected Maven | 3.9.11, reporting Java 21 |
| Test libraries | JUnit Jupiter 5.12.2 / AssertJ 3.27.3 |
| Harness | JMH 1.37 |

The container's visible CPU/kernel metadata is not a physical-host inventory,
CPU reservation or power-control claim. This is distinct from the author's
Windows 11 / Oracle JDK 21.0.9 environment. No author WSL/native-Linux validation
is claimed. Dependency transport used temporary workspace settings; no proxy,
credentials or network configuration is added to the repository. Local Maven
and JMH metadata commands disabled the JVM perf-data file to avoid PID-namespace
collisions; those verification options are not silently imposed on author runs.

| Verification | Outcome |
|---|---|
| Bash syntax, both scripts | Pass |
| Offline shell subprocess suite | **20 cases pass**, separately from JUnit |
| Real selected JDK 21, no jar before packaging | Environment-only preflight passes; optional absence reported |
| Host default Java 17 runtime environment | Rejected; its selected installation lacked javac |
| Wrapper `./mvnw -version` | Maven 3.9.11 / Java 21 |
| `./mvnw clean test` | **710** tests, zero failures/errors/skips; BUILD SUCCESS |
| `./mvnw -Pbenchmark clean package` | **723** tests, zero failures/errors/skips; BUILD SUCCESS, jar created |
| Required-jar preflight after packaging | Pass; digest agrees with independent SHA-256 calculation |
| Read-only check | Artifact bytes and Git status unchanged before/after preflight |
| Full JMH discovery | **115 methods in 40 classes**, unchanged inventory |
| Anchored aggregation `-lp` | One method, all five sizes |
| Git attributes / file contents | LF requested for all three launchers; no wrapper content change |

Normal build completed in 35.870 seconds and profile build in 46.403 seconds
on this verification environment. These are Maven build durations, **not
algorithm timing measurements**. No JMH timed operation or controlled Linux
research run was executed; only discovery was requested.

The newly built **local verification artifact** has SHA-256:

`06c41596dc0497d54be76b6e9236720a6bcdf2c368e10db34cfc87cd423b0195`

It belongs only to this local Linux package with the stated working-tree changes.
It is not the missing original Windows artifact, a universal reproducible jar
hash or evidence of the author's old run. The preflight explicitly says that
hashing alone does not establish artifact freshness/source linkage.

## Twenty offline shell cases

The suite uses a temporary Git repository whose path contains spaces, fake
Java/Javac launchers and a fake uname. These are controlled subprocess fixtures,
not actual JVM/OS observations. The real checks above are separate.

1. Clean repository with spaced paths succeeds.
2. An optional missing jar is reported without failure.
3. A required missing jar fails.
4. A Java 17 feature value is rejected.
5. A Javac 17 version is rejected.
6. Empty JAVA_HOME is rejected.
7. Mismatched PATH/JAVA_HOME executables are rejected.
8. Malformed Java properties are rejected.
9. Launcher failure with private diagnostics is reported without echoing values.
10. A non-Linux uname is rejected.
11. A missing required tool is rejected.
12. An unsupported argument returns usage status 64.
13. Help works without a successful environment check.
14. Injected option-variable names are reported with values withheld.
15. Required-jar capture rejects injected option variables.
16. The script-relative root works from another current directory.
17. Dirty state is reported without discarding changes.
18. An empty required jar is rejected.
19. Present artifact bytes are hashed without execution or modification.
20. Hash output explicitly does not assert artifact freshness.

The suite creates only its own temporary fixtures and cleans that directory on
exit. The artifact fixture is deliberately arbitrary bytes: format/discovery is
a later JMH check, while this contract tests identity reporting. The checks are
shell cases, not an increase from 723 to 743 JUnit invocations.

## Provenance, limitations and next subject

The historical aggregation report retains its five observations, source
inferred from the author's reflog and unavailable original jar hash. Every
historical numerical table/CSV is unchanged. Linux verification does not close
other algorithm findings, repair missing author metadata or certify absence of
background interference. The preflight is scoped to a Linux JDK environment,
not arbitrary shells/operating systems or a complete security/provenance audit.

Commands and links are reviewed against primary Bash, Git, Maven, Java 21 and
Microsoft WSL references in the guide. No installer, system configuration edit,
new JSON/log/CSV evidence file, coverage gate or GitHub Actions workflow is added.
Local passing tests are not CI. The author may use the guide in an existing
Linux/WSL environment when available; Windows support remains. The next numbered
module is **19 — CI/CD**.
