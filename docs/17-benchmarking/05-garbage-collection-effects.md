# Garbage Collection Effects

The aggregation array is allocated in `@Setup(Level.Trial)`, outside the timed scan. The production scan creates no explicit per-call Java object. This source fact does not establish a measured zero allocation rate: the VM and harness still execute, and runtime behaviour needs observation.

The guide provides a separate `-prof gc` run after the ordinary timing run. Retain its primary score and secondary metrics, including their units, in a separately labelled record. In JMH's GC profiler, `gc.alloc.rate.norm` is allocated bytes normalised per benchmark operation (B/op); it is not the retained heap size or auxiliary-space complexity.

An absent GC event does not prove that no allocation occurred. Allocation rate per second can vary when throughput changes even if bytes per operation stay similar. Profiler availability and supported metrics depend on the VM/platform; unsupported output remains an explicit limitation.

Do not insert `System.gc()` into the benchmark, infer GC as the cause of a slow iteration without diagnostic evidence, or combine ordinary and profiled means into one table. Do not run JaCoCo instrumentation while measuring JMH.

The contract tests intentionally allocate clones and compute long oracles. They run before JMH and their allocations are outside the performance workload.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from pending author measurements.
