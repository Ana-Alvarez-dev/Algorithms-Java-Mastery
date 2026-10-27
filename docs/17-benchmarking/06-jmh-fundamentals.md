# JMH Fundamentals

The project pins JMH **1.37** under Maven profile `benchmark`. That profile registers `src/jmh/java`, runs annotation processing and creates the executable `target/benchmarks.jar`. It now also registers `src/jmh-test/java` as **test sources**; fixture tests are not packaged into the benchmark jar.

`@Benchmark` marks `sum`. `@State(Scope.Thread)` provides state for each worker. `@Param` supplies the five sizes. `@Setup(Level.Trial)` prepares the bounded array. `@BenchmarkMode(Mode.AverageTime)` and nanosecond output define the score. `@Warmup`, `@Measurement` and `@Fork` provide defaults, which command-line options can override.

For the corrected aggregation experiment, module 17 changes the fork default from one to two. Historical means retain their original provenance. The scan and modulo-100 fixture introduced by Testing are unchanged.

Run `-l` or `-lp` on the newly built jar with the guide's anchored, fully qualified pattern. Exactly one method should match; `-lp` also shows size values. An empty match means the jar/pattern/checkout needs investigation before timing. Do not keep running stale artifacts after a failed build.

Normal `clean test` runs 710 production-contract invocations. The benchmark-profile build adds 13 fixture invocations, for 723. Fixture checks and JMH invocations are different counts.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from the [first supplied aggregation run](results/arrays/aggregation-corrected-run.md), whose execution source attribution remains open.
