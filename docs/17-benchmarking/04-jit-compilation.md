# JIT Compilation

The JVM can inline, compile and optimise frequently executed code. JMH organises repeated invocation but cannot make every benchmark design valid automatically.

`ArrayAggregationBenchmark.sum` passes a mutable trial-state array to the production method and consumes the returned int with a JMH `Blackhole`. This makes the result observable to the harness. It does not itself prove that generated machine code matches an intended performance model. A literal input/result in a benchmark can instead invite constant folding; unused output can invite dead-code elimination.

Do not add a manual repetition loop solely to make this scan last longer, or divide its elapsed time by an invented operation count. JMH already repeats the method. Keep one full aggregation per invocation and preserve the Blackhole mode printed in each run.

The author previously used compiler Blackholes with JMH 1.37. Record the actual new header rather than assuming that mode. Comparing VMs or Blackhole modes requires a new, explicitly controlled experiment.

If a surprising result warrants compilation/assembly diagnostics, first list available profilers. Such diagnostics require platform-specific support and can change execution; they belong to a separate run with its own settings. Timing alone does not establish vectorisation or an inlining decision.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from the [first supplied aggregation run](results/arrays/aggregation-corrected-run.md), with execution source inferred from the supplied reflog and original jar SHA-256 unavailable.
