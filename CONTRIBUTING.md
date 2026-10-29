# Contributing to Algorithms Java Mastery

This academic Java 21 repository connects specifications, correctness reasoning,
complexity analysis, implementation, tests and selected experiments. Contributions
should improve that traceability. Start with the [project overview](README.md)
([Español](README.es.md)), [engineering standards](docs/00-project/08-engineering-standards.md)
and [development workflow](docs/00-project/09-development-workflow.md).

## Choose a focused contribution

For a defect, describe the input, expected contract, observed result, source
commit and reproduction steps. For a new algorithm or substantial redesign,
explain the problem and proposed scope in an issue or the PR before expanding
the implementation. Small documentation corrections can go directly to a PR.

English is primary for code, identifiers, technical documentation, commit
messages and proposed PR text. Keep both root READMEs equivalent when shared
scope, counters, commands or navigation change; Spanish project navigation is
provided in README.es.md. Module documentation is not automatically translated.

## Prepare the environment and branch

Install a full **JDK 21**, align JAVA_HOME and PATH, and confirm that both java
and javac select it. The POM's release 21 target does not select the runtime.
Use the committed Maven Wrapper; a global Maven installation is unnecessary.
First use can download Maven and dependencies.

Work on a focused branch from current main. If you have write access, use a
repository branch; otherwise, use a branch in your fork and target this
repository's main in the PR. Example branch names follow existing conventions:
`fix/closest-value-duplicates`, `test/array-boundaries`,
`docs/search-contracts` or `benchmark/sorting-distributions`.

Start commands at the repository root, inspect existing local changes before
switching branches or updating main, and preserve unrelated work. Execute build
commands as checkpoints: stop and resolve a failure before continuing.

## Maintain the algorithm's evidence chain

1. Define input/output, preconditions, postconditions, rejection policies and
   mutation/ownership rules before implementation.
2. Explain the strategy and language-independent pseudocode, trace representative
   cases, and justify correctness and termination using invariants or induction.
3. Analyse time and auxiliary space with explicit assumptions, including output
   costs, recursion depth and numeric limits where relevant.
4. Implement in the existing `org.anaalvarezdev.algorithms` topic package under
   `src/main/java`; keep test helpers out of production code.
5. Add JUnit Jupiter/AssertJ cases under `src/test/java`. For a bug, reproduce
   the failure and add a regression. Cover relevant boundaries, duplicates,
   overflow and ownership, with deterministic independent oracles where useful.
6. Update the affected `docs` module, contract/evidence map and navigation.
   Preserve dated historical records and add a qualified follow-up when facts
   change. Cite sources with the edition/version actually inspected.

Apply the depth appropriate to the change. A documentation-only correction
needs inspection and link checks; it does not require a new algorithm, test or
timing run. A behavioural change needs native Java tests. Finite test domains
and coverage are evidence with limits, not universal correctness proofs.

## Validate the relevant scope

For Java changes, run the normal build with the selected JDK 21:

```powershell
.\mvnw.cmd -version
.\mvnw.cmd -DfailIfNoTests=true clean verify
```

```bash
./mvnw -version
./mvnw -DfailIfNoTests=true clean verify
```

Maven must report Java 21. For coverage-related changes, run the optional profile
separately and inspect `target/site/jacoco/index.html` and `jacoco.xml`:

```powershell
.\mvnw.cmd -DfailIfNoTests=true -Pcoverage clean verify
```

```bash
./mvnw -DfailIfNoTests=true -Pcoverage clean verify
```

For benchmark, fixture or benchmark-profile changes, build and test that profile
separately. Fixture tests in `src/jmh-test/java` are active only in this profile:

```powershell
.\mvnw.cmd -DfailIfNoTests=true -Pbenchmark clean package
& "$env:JAVA_HOME\bin\java.exe" -jar .\target\benchmarks.jar -l
```

```bash
./mvnw -DfailIfNoTests=true -Pbenchmark clean package
"$JAVA_HOME/bin/java" -jar target/benchmarks.jar -l
```

The `-l` command lists registered methods; it does not measure performance.
Use an anchored method selector with `-lp` to inspect parameters before a timed
run, following the [benchmark guide](docs/17-benchmarking/implementation-and-benchmark-guide.md).
Each `clean` deletes earlier target output, so retain needed evidence first.
Never combine coverage instrumentation with performance measurement.

For Linux script changes, also run the real environment check and shell contract
suite. Check each script's syntax separately:

```bash
bash -n scripts/linux/check-environment.sh
bash -n scripts/linux/test-check-environment.sh
bash scripts/linux/check-environment.sh
bash scripts/linux/test-check-environment.sh
```

These are Bash/Linux checks; Windows contributors can use the PowerShell build
commands above. Local WSL installation is not required for ordinary Java work.
See the [Linux guide](docs/18-linux-environment/implementation-and-verification-guide.md).

## Add experiments only for a defined question

Keep JMH under `src/jmh/java`, separate from correctness tests. Define fixtures,
input distributions, setup boundaries, result consumption and comparison
baselines. Retain actual JMH settings, units, sample count and uncertainty.
Record the source commit and dirty state, the exact jar SHA-256, JDK/JVM,
effective options, OS/hardware and the command used for the run. Separate
profiler diagnostics from the primary timing results.

Use the existing Markdown research records for transcribed evidence; screenshots
may supplement readable tables and execution metadata. Do not add new generated
JSON, logs, CSV, jars or coverage output to the repository. Existing historical
CSV records remain preserved. Generated `target` output stays ignored.
Mark unavailable metadata explicitly: a later build cannot recover the identity
of a missing original jar. Do not combine incompatible JVM/settings or infer a
universal speed ranking, CPU cause or asymptotic proof from a finite timing series.
See the [experimental contracts](docs/17-benchmarking/experiment-contracts.md).

## Prepare the commit and PR

Review `git status --short`, `git diff` and `git diff --check`; stage only the
intended paths. Keep commits coherent and describe the change in English, using
existing prefixes such as `docs:`, `fix:`, `test:`, `feat:`, `benchmark:`,
`build:` or `ci:`. Do not include credentials, personal paths or unrelated output.

In the PR description, state the problem and resulting behaviour, affected
contracts/modules, relevant issue, checks actually executed and their results,
and remaining limits. Identify the source/environment for test or benchmark
evidence. Clearly distinguish inspection, JMH discovery, Java tests and timed
experiments; do not claim checks that were not run.

Add a concise entry to [CHANGELOG.md](CHANGELOG.md) under Unreleased for meaningful
behavioural, documentation, testing or workflow changes. Link an existing issue,
PR or evidence record when available; do not invent a PR number or release.

The [CI workflow](.github/workflows/maven-ci.yml) runs tests/coverage followed by
a separate benchmark package and discovery job. Review the actual PR checks and
address failures. Observed test counts are snapshots, not configured exact-count
gates; no coverage percentage or benchmark-speed threshold is enforced. Passing
CI does not establish branch protection, deployment or permission to merge.
See the [workflow contracts](docs/19-ci-cd/workflow-contracts.md).

## Academic integrity and AI assistance

Use original explanations and responsible attribution; do not copy textbook
content into the repository. AI may assist explanation, alternatives, review and
writing, but accepted reasoning/code require human understanding and validation.
Disclose material AI assistance in the PR and describe how it was checked.
An AI response is not execution evidence. Follow any applicable course rules.
