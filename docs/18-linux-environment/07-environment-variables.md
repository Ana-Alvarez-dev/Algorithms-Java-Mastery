# Environment Variables

For this protocol, export `JAVA_HOME` to an existing Linux JDK 21 and prepend its `bin` directory to `PATH`. Replace the guide's example with the real installed path; do not overwrite shell startup files automatically. The preflight reports selected canonical Java/Javac paths and rejects disagreement with `JAVA_HOME`.

`JAVA_TOOL_OPTIONS`, `JDK_JAVA_OPTIONS` and `_JAVA_OPTIONS` can influence Java launchers. The verifier reports only the names of nonempty variables, withholding values. In `--require-benchmark` mode it rejects such injected options for the ordinary uninstrumented protocol; it does not silently clear or alter them. Intentional profiling uses a separately documented execution.

The environment-only mode may proceed with such variables if the launcher still reports Java 21; that success does not certify absence of instrumentation. Launcher failure messages direct inspection locally without echoing potentially sensitive option values. The subprocess tests check this boundary.

Locale and encoding are context: the script records LANG, LC_ALL and Java file.encoding. A decimal comma in console timing text is a representation choice, not a change in units. Preserve the original precision when normalising evidence to English tables. Do not publish a full environment dump containing unrelated credentials.

## Applied verification

The [implementation and verification guide](implementation-and-verification-guide.md) connects these concepts to script contracts, commands and primary sources. The [verification report](verification-report.md) distinguishes real Linux/JDK checks from injected shell fixtures, historical research and pending author execution.

## Ordered Review Clarification

The three guarded option variables are a finite protocol check. They do not
cover every Maven/JMH configuration or explicit VM argument. Required-jar mode
rejects nonempty values before its Java metadata queries; environment-only mode
may continue if those queries work. LANG/LC_ALL/file.encoding are context, not
a complete locale inventory. The verifier neither silently clears options nor
edits the caller's persistent environment.

The [script and evidence map](environment-contracts.md) gives the applied
boundaries. The [documentation review](documentation-review.md) records LI01-LI12
and fresh shell/Java verification.
