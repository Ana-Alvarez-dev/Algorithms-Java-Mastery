# Common Benchmarking Mistakes

| Mistake | Repository-specific correction |
|---|---|
| Treating a Maven build as a measurement | Run the executable jar after a successful profile build |
| Reusing a stale jar after changing branches | Clean-build, discover the exact method and record jar SHA-256 |
| Timing invalid inputs | Keep modulo-100 aggregation; old large ascending sums violated int range |
| Treating a smoke score as research | Label smoke separately; controlled Result remains Pending |
| Assuming release=21 selects Java | Check Maven and use the explicit Java 21 executable |
| Timing tests or coverage | Run coverage separately; build benchmarks without that profile |
| Ignoring setup and reuse | State that construction is trial-only and repeated scans reuse data |
| Assuming Blackhole prevents all optimisation issues | Preserve its mode and investigate generated code when warranted |
| Reporting Cnt as method calls | Count measured iteration averages separately from repeated invocations |
| Removing noisy observations | Retain every supplied value and describe uncertainty |
| Calling a lower mean universally faster | Match contracts, workloads, units, settings and uncertainty |
| Inferring a CPU/GC cause from timings | Use a separately identified diagnostic experiment |
| Overwriting historical evidence | Add a new source-attributed record and retain old limitations |

If discovery says “No matching benchmarks”, stop and inspect checkout, successful packaging, `-l` and `-lp`. The guide uses a fully qualified anchored selector to avoid ambiguous patterns.

No new JSON/log/CSV files are required. Copy the complete console output into the conversation; the versioned evidence belongs in Markdown.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from pending author measurements.
