# Files and Directories

Start with inspection: `pwd`, `ls -l`, `git status --short` and a focused `git diff`. Review a path before modifying it, and separate generated output from authored source. A successful build creates artifacts; it does not make them versioned evidence automatically.

The environment verifier reads the checkout and optional jar; it does not create result files, move source, build Maven or remove artifacts. The offline shell tests deliberately create isolated temporary fixture repositories with `mktemp -d` and remove only their generated fixture directory on exit. Their fake Java launchers are subprocess test doubles, not real Java execution evidence.

For this project, preserve numerical results and provenance in Markdown. No new JSON/log/CSV exports are required. Historical CSV transcriptions stay historical. A screenshot can show coverage totals, but cannot recover missing benchmark iterations or an absent jar identity.

Two files with the same name may contain different bytes. `sha256sum target/benchmarks.jar` identifies the bytes currently available at that path; Git SHA identifies a source commit. Capture both with dirty state after a successful build and before measurement. They do not substitute for each other.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.

## Ordered Review Clarification

The preflight performs metadata subprocesses and repository/artifact inspection;
its read-only check compares Git status and jar digest, rather than auditing
every external-program filesystem effect. The shell suite has a different
footprint: it creates its own temporary Git tree and fake launchers, with EXIT
cleanup. The artifact fixture deliberately contains arbitrary bytes to test
identity reporting without implying JAR format validity.

The [script and evidence map](environment-contracts.md) gives the applied
boundaries. The [documentation review](documentation-review.md) records LI01-LI12
and fresh shell/Java verification.
