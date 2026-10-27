# Testing: Implementation and Verification Guide

## Selected Scope and Source

This module starts from main `8bacc90f00e4ad7dc998743d06be65ff7f5c04aa`,
PR #91 integrating [Dynamic Programming research](../17-benchmarking/results/dynamic-programming/README.md).
It applies testing practice to existing contracts instead of inventing a new
production testing algorithm. JUnit Jupiter 5.12.2 and AssertJ 3.27.3 remain;
an optional JaCoCo **0.8.13** profile adds a reproducible coverage baseline.
That pinned release supports the selected Java 21 class files; this is not an
upgrade to whichever framework version is newest.

| Notes | Concrete responsibility |
|---|---|
| 01 fundamentals / 02 design | Contract partitions and a failing regression before the fix |
| 03 Jupiter / 04 AssertJ | Discovered test invocations, focused expected/actual and ownership assertions |
| 05 parameterisation / 06 exceptions | Named MethodSource cases and the existing null-contract check |
| 07 edges / 08 properties | Duplicates, long distances, exhaustive oracle, seeded and metamorphic checks |
| 09 builders | Small test-only fixture builder with fresh copies, no production abstraction |
| 10 coverage / 11 mistakes | Real before/after counters, scope/denominator, no correctness inference |

The selected work closes the current nearest-value duplicate defect and fixes
the future aggregation benchmark fixture domain. Historical benchmark scores
retain their original limits. Coverage instrumentation is for tests, not JMH
measurements. No new JMH family is justified merely to time a unit-test framework.
The next numbered module is **17 - Benchmarking**.

## Contract and Regression Design

`ClosestValueSearch.search` accepts a non-null nondecreasing int array and an int
target, returns -1 for empty input, otherwise minimises the lexicographic pair
**(absolute long distance, original index)**, preserving input. Sortedness is
a caller precondition, not checked by an O(n) validation pass. Null throws the
existing NullPointerException with its documented message. Unsorted behaviour
is unspecified; a test must not invent an exception for it.

The previous implementation found a closest value but could choose a later
equal copy. Three recorded counterexamples are:

| Input / target | Old index | Contract index |
|---|---:|---:|
| [10,20,20,20,30] / 20 | 2 | 1 |
| [10,20,20,40] / 25 | 2 | 1 |
| [10,20,20] / 100 | 2 | 1 |

Named parameterised cases also cover repeated upper neighbours, tied repeated
groups, all equal, negatives, extremes, singleton and empty. Each invocation
gets fresh input. A linear oracle scans all positions using long subtraction
and replaces its incumbent only on strictly smaller distance, independently of
binary-search control flow. It checks complete index policy, not distance alone.

Exhaustive nondecreasing arrays of lengths 0..7 over {-2,-1,0,1,2} number
sum(C(n+4,4),n=0..7)=792. Targets -4..4 plus MIN_INT/MAX_INT give **8,712
queries**. Seeded full-int/duplicate arrays extend the finite domain. Translation
and positive-scaling properties are applied only where arithmetic stays in int;
they should preserve the selected index. Finite cases complement the proof and
are not a proof over every sorted int array.

## Corrected Strategy, Trace and Proof

Reuse `SearchInsertPosition.search` for lower bounds rather than copying it:

```text
null -> throw; empty -> -1
target <= first value -> 0
upper = first position whose value >= target
upper == n -> lower_bound(last value)
compare long distances to upper-1 and upper
lower wins or ties -> lower_bound(value at upper-1)
otherwise -> upper
```

The existing half-open lower-bound invariant is: every index before low is
below the searched value, every index at/after high is at least it, and the
candidate boundary stays in [low,high]. Each step shrinks that interval; at
equality it returns the first eligible position.

For an interior target, sortedness makes every value farther than either
adjacent boundary no better than those candidates. The upper boundary is already
the first copy of its value. When the lower value wins, including equal-distance
ties, a second lower bound returns its first copy, strictly before any upper
candidate. If target is above all values, the maximum value wins but still needs
its first occurrence. At/below the minimum, index zero wins immediately. Thus
both minimum distance and minimum index hold. Long absolute differences fit:
the largest is 4,294,967,295, and cast precedes subtraction.

For [10,20,20,40]/25, lower_bound(25)=3; distances to 20/40 are 5/15;
lower_bound(20)=1 fixes the duplicate index. For an exact repeated 20, its first
lower bound already identifies index 1. Each binary loop terminates by shrinking
a finite interval. Empty/minimum fast paths have Theta(1) cost; worst nontrivial
work is Theta(log(n+1)) across at most two lower bounds, with O(1) auxiliary state.
An exact midpoint is no longer the old early-return complexity argument.

## Benchmark Inputs Also Require Correctness

`ArrayAggregation.sum` keeps its existing contract: mathematical result fits
int. The old trial fixture used values 0..n-1 and violated that domain at
100,000 and 1,000,000. Its [historical means](../17-benchmarking/results/arrays/README.md)
are not corrected or relabelled by changing a fixture now.

The future fixture uses **value[i]=i%100**, with an explicit size cap 0..1,000,000
before allocation. Default sizes are multiples of 100, so expected sums are:

| Size | Exact sum |
|---:|---:|
| 100 | 4,950 |
| 1,000 | 49,500 |
| 10,000 | 495,000 |
| 100,000 | 4,950,000 |
| 1,000,000 | 49,500,000 |

Every prefix is nonnegative and <=49,500,000, so int accumulation is safe.
Trial generation remains outside timing. Validate every default against an
independent long sum and formula before using its timings. Corrected Result/
Interpretation require a **separate future author run**; smoke is only a harness
check. Five old means and their missing errors/runtime/source stay historical.

## Coverage Profile and Evidence Boundaries

`-Pcoverage` binds JaCoCo prepare-agent before tests and report to verify. It
generates only HTML/XML under ignored target/site/jacoco, with execution data
under target. There is no arbitrary percentage gate and no excluded production
package to improve the denominator. A clean run prevents stale execution data.
Do not combine coverage instrumentation with benchmark performance measurements.

Record the baseline suite/coverage before adding regressions; then observe the
new cases fail against old code, fix the implementation, and run the full suite
with coverage again. Preserve executed test invocations separately from source
method counts or loop/assertion counts. Parameterised invocations are reported
separately by JUnit; exhaustive loops remain one test invocation each.

JaCoCo reports bytecode instruction/branch and source-line counters. Branch
coverage does not mean all data combinations or paths were tested; exception
handling is not counted as a branch in its definition. A covered line requires
only some mapped instruction to execute. A high percentage can coexist with an
incorrect assertion or missing duplicate partition. The nearest-value regression
provides a concrete example, to be quantified by actual baseline counters.
No metric is a correctness proof, mutation score or path-coverage claim.

Coverage, regression diagnostics and fixture checks will be recorded in the
[verification report](verification-report.md), as local evidence. No GitHub
Actions workflow is added or passing CI inferred. JMH scores, coverage and unit
test pass counts answer different questions.

## Windows Reproduction After PR Integration

From the repository root with Maven running on Java 21:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd clean test
.\mvnw.cmd -Pcoverage clean verify
Start-Process .\target\site\jacoco\index.html
```

The XML is target/site/jacoco/jacoco.xml. HTML/XML are local generated reports;
commit the concise Markdown record, not target, JSON/log or a new CSV export.
Capture source SHA when sharing local verification. Compare reports only when
the compiled source, suite, compiler, tool version and scope are identified.

To measure the corrected aggregation fixture later, use a separate clean build:

```powershell
.\mvnw.cmd -Pbenchmark clean package
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*arrays.ArrayAggregationBenchmark.*" -foe true
```

Expect five default cells in ns/op. Preserve the complete headers, forks,
iteration observations, mean/error and completion in Markdown. This instruction
does not create a new measured dataset. Earlier Arrays/Searching research keeps
its source/runtime and limitations.

## Primary References and Review Decisions

- [JUnit 5.12.2 User Guide](https://docs.junit.org/5.12.2/user-guide/index.html), parameterised MethodSource/lifecycle and exception assertions, consulted 2026-10-27.
- [AssertJ official guide](https://assertj.github.io/doc/), fluent array/exception assertions and failure descriptions, consulted 2026-10-27; installed core stays 3.27.3.
- [JaCoCo release history](https://www.jacoco.org/jacoco/trunk/doc/changes.html), Java 21 support and pinned 0.8.13 release; [Maven integration](https://www.jacoco.org/jacoco/trunk/doc/maven.html), [report formats](https://www.jacoco.org/jacoco/trunk/doc/report-mojo.html), [counter semantics](https://www.jacoco.org/jacoco/trunk/doc/counters.html), consulted 2026-10-27. Current trunk documentation can describe a newer development version; the POM pins 0.8.13.

CLRS remains the algorithmic reference used by earlier modules, not the authority
for Jupiter/AssertJ/JaCoCo APIs. Sortedness, null behaviour, lowest-index tie,
fixture-copy policy, coverage scope and bounded benchmark data are explicit
repository choices. The module demonstrates contract-driven regression and
data validation; it does not add mocking/property-generator/mutation libraries
without a separate need or make every existing test complete.
