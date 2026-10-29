# Test Coverage

Coverage measures executed code, not whether assertions express every required
behaviour. This module provides an actual counterexample: the old nearest-value
method had **85/85 instructions, 14/14 branches and 21/21 lines covered** under
694 passing tests, yet six new named cases failed. Data partitions can be absent
even when all recorded control-flow branches have executed.

The optional JaCoCo **0.8.13** profile attaches its agent to tests and generates
HTML/XML at verify. A clean, coverage-only build gives a consistent production
denominator; do not combine JMH/generated classes or performance measurement
with instrumentation. No arbitrary percentage gate or scope exclusion is added.

```powershell
.\mvnw.cmd -Pcoverage clean verify
Start-Process .\target\site\jacoco\index.html
```

The [verification report](verification-report.md) records before/after counters,
test/source scope, tool versions and hashes of the generated XML snapshots.
Both production code and suite change, so a percentage difference cannot isolate
test quality. Counts reflect JaCoCo's bytecode filtering, not source-method or
assertion counts; line execution is not all paths. Exception handling is outside
its branch definition. No mutation/path-coverage score is claimed.

Generated target files stay local and ignored. Markdown records the evidence;
no JSON/log/new CSV is versioned. Use missed areas to ask meaningful contract
questions, not to add assertions that merely mirror the implementation. The
[implementation and verification guide](implementation-and-verification-guide.md) provides protocol and limits. Next: [common mistakes](11-common-mistakes.md).

## Ordered Review Clarification

Report generation and a percentage gate are separate mechanisms. This POM
binds prepare-agent/report, with no JaCoCo check goal or threshold. A covered
line can contain unexecuted instructions; constructors/filtering also make
135 analysed classes different from 100 production source files. The current
coverage-only run excludes benchmark sources and fixture tests. Its actual
counters and XML hash are recorded in the dated documentation review, without
replacing the original baseline snapshots.

The [applied evidence map](test-contracts.md) connects this clarification to
the actual source, domains and profile scope. The [documentation review](documentation-review.md)
records TE01-TE10 and the fresh Java 21 outcomes.
