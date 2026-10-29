# Empirical Analysis

For aggregation, the source performs n additions and n element visits, so time is Θ(n), with Θ(1) auxiliary accumulator space. Trial preparation costs Θ(n) space/time separately. The benchmark keeps n fixed within each configuration and observes finite-size elapsed time.

For each observed row, retain n, score, error, unit and actual settings. Derive ns/element as score/n only after obtaining a valid score. A tenfold increase in n need not give exactly tenfold elapsed time: fixed overhead, compilation and the memory system can matter. Timing alone does not identify which cause acted.

The matrix has five sizes: 100, 1,000, 10,000, 100,000 and 1,000,000. Every element is in 0..99 and total sums fit int. Size is the varied factor; values follow the same bounded construction rule. No claim about random, cold-cache or multi-threaded data follows.

Earlier Sorting used JDK 25.0.1; later runs used Java 21.0.9. Keep those records separate. Do not pool ns/op with us/op, compare different timed work, fit an asymptotic exponent from these few points, or turn a finite range into a complexity proof.

CLRS, third edition, Chapters 2–3 supplies the algorithmic cost model; JMH supplies the experimental harness.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from the [first supplied aggregation run](results/arrays/aggregation-corrected-run.md), with execution source inferred from the supplied reflog and original jar SHA-256 unavailable.

## Ordered Review Clarification

The n visits/additions are a source cost ledger, not a CPU instruction count.
Score/n is descriptive normalisation over a complete scan, including amortised
call/harness effects; a sub-nanosecond value does not measure an isolated
addition's latency. A fixed deterministic payload at each size supports that
selected workload, rather than a distribution-wide conclusion.

The [experiment and evidence map](experiment-contracts.md) gives the applied
boundaries. The [documentation review](documentation-review.md) records BE01-BE12
and fresh Java 21 verification.
