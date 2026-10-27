# JVM Warmup

Warmup lets the executing VM encounter the workload before measurements. It may trigger class loading and compilation; a fixed warmup duration does not prove that all runtime behaviour has stabilised.

The selected protocol uses three warmup iterations of 500 ms **per fork**, then five measured iterations of 500 ms, in two independent forked JVMs. Preserve all printed warmup values and the iteration order. With five sizes this gives 30 warmup averages and 50 measured iteration averages, not 80 individual algorithm calls.

Check trends within a fork and shifts between forks before interpreting a summary mean. Large drift can motivate a separately identified rerun with longer warmup, longer measurements or more forks. Retain the first run and document the changed settings; do not remove an inconvenient iteration.

A one-fork, short smoke run deliberately uses different settings to check that the jar starts and discovers the benchmark. Its score does not fill the controlled-result template.

Trial setup creates the array once per trial. The measured method repeatedly scans it, so warmup and measurement reuse that prepared data. The result is a steady-workload question, not startup latency.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from the [first supplied aggregation run](results/arrays/aggregation-corrected-run.md), with execution source inferred from the supplied reflog and original jar SHA-256 unavailable.
