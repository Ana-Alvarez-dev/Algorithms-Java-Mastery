# Benchmarking Fundamentals

A microbenchmark asks a bounded question about an operation in a stated environment. Here the selected question is: how does `ArrayAggregation.sum` scale across five contract-valid array sizes on one executing Java 21 VM?

The output is average **ns/op**, where one operation is one full scan plus JMH result consumption. Trial array construction is excluded. The array is reused; this experiment does not model a cold, freshly allocated request payload.

A successful test supports behaviour for checked inputs. A proof establishes a property under stated assumptions. A smoke run checks discovery/execution. A controlled run supplies performance observations. Each has a separate role in the evidence chain.

The original ascending aggregation payload violated the int-sum contract at 100,000 and 1,000,000 elements. Its historical means remain visible, but cannot answer the corrected workload's question. The [first supplied two-fork run](results/arrays/aggregation-corrected-run.md) now retains five means/errors and all printed iterations; source attribution is now inferred from the supplied reflog; the original artifact hash and execution-time dirty state remain unavailable.

Use the guide's discovery command before timing. Building `benchmarks.jar` does not execute a JMH experiment. Do not treat Maven test duration as algorithm latency.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from the [first supplied aggregation run](results/arrays/aggregation-corrected-run.md), with execution source inferred from the supplied reflog and original jar SHA-256 unavailable.
