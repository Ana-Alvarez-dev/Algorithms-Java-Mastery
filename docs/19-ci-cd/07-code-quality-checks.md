# Code Quality Checks

This workflow's implemented checks are compilation, existing tests, twenty shell contract cases, coverage generation, nonempty coverage reports, packaging, source/artifact capture and benchmark discovery. It adds no arbitrary JaCoCo percentage gate, Sonar service, deployment scanner or unconfigured quality badge.

JaCoCo reports which bytecode/branches the tests reached; it does not judge assertion strength. CI retains the HTML/XML so a reviewer can inspect uncovered paths. Missing expected coverage output fails the generating step even if Maven otherwise exits successfully.

The YAML was checked with actionlint 1.7.12. Local ShellCheck/Pyflakes integration was disabled because those tools were unavailable; all six run blocks were additionally syntax-checked and actually executed with Bash. This is an explicit scope limit, not a claim that every static-analysis tool ran in CI.

Full SHA pins, read permissions, fresh checkouts and quoted runtime context constrain the workflow's behaviour, with initial hosted executions now observed separately. A workflow definition and passing local commands do not by themselves enforce branch protection. Required-check settings are repository administration and were not changed.

## Applied workflow and evidence

See the [implementation and verification guide](implementation-and-verification-guide.md), [workflow](../../.github/workflows/maven-ci.yml) and [verification report](verification-report.md). They distinguish implemented checks, local command validation and the [observed initial hosted executions](first-hosted-run.md).
