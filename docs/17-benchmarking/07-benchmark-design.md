# Benchmark Design

Define the question, valid domain, timed boundary and comparison before running. The selected aggregation design varies n across five values and holds the bounded value rule, algorithm, worker count, VM options and protocol fixed.

The actual fixture is checked by benchmark-profile JUnit tests: long-stream oracle against a closed-form cycle sum, bounded values, supported sizes, fresh trial arrays, repeated calls and unchanged input. Unsupported sizes fail before allocation. These tests exercise the real setup through reflection, without exposing fields as a production API or timing assertions.

The scan includes the call, loop and Blackhole consumption. Trial construction, validation oracles and JUnit are excluded. A separate GC run diagnoses allocation; it does not replace ordinary timing. No comparison algorithm is added solely to generate another score.

Existing modules already illustrate controls: Sorting has copy-only work; DP has DISJOINT/TOO_HEAVY fixtures. Controls answer narrower questions. Subtracting a copy-only mean from another mean does not automatically isolate sorting cost or yield a valid interval, because code generation and runtime conditions can differ.

For a future factorial comparison, predeclare implementation × size × distribution and ensure each cell solves the same contract. Keep source/settings fixed, retain all cells and budget the matrix first. The present experiment has one implementation, five sizes and one deterministic value rule; it makes no distribution-wide claim.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from pending author measurements.
