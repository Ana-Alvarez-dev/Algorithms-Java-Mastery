# Benchmark Parameters

The corrected aggregation protocol is explicit:

| Setting | Controlled run | Smoke only |
|---|---|---|
| Mode / unit | AverageTime / ns/op | Same |
| Sizes | 100, 1000, 10000, 100000, 1000000 | All five in local verification |
| Warmup | 3 × 500 ms per fork | 1 × 50 ms |
| Measurement | 5 × 500 ms per fork | 1 × 50 ms |
| Forks / threads | 2 / 1 | 1 / 1 |
| Failure handling | Stop on benchmark error | Same |

The full timing phase has a nominal 5 × 2 × (3 + 5) × 0.5 = **40 seconds**, excluding VM startup, setup and harness overhead. It supplies ten measured iteration averages per size, normally shown as Cnt=10, not ten method calls. Do not promise an exact wall-clock duration.

The class defines these controlled defaults; the guide also passes explicit arguments so the copied command is reviewable. The effective JMH header is authoritative when overrides or environment options change execution.

Record actual Java executable/version, options, Blackhole mode, source SHA, dirty state and jar SHA-256. JAVA_HOME used by Maven and the executable selected by PowerShell can differ. The guide uses the explicit Java 21 path for execution; compiler release=21 alone does not select a runtime.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from pending author measurements.
