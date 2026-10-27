# Result Interpretation

For each configuration preserve the printed score, error, unit, Cnt, result block and every available fork/iteration average. For this AverageTime experiment lower scores mean less elapsed time per complete scan. A missing error stays unavailable; it is never replaced by zero.

JMH 1.37 prints a mean confidence-interval half-width at its stated confidence level (normally 99.9%). This error is not the standard deviation and does not mean every future operation lies within score ± error. Iteration averages within forked JVMs are structured observations; inspect fork differences as well as the pooled summary.

Use 100 × error/score, for positive finite scores, as a descriptive relative-width check. The repository's 25% flag is a review heuristic, not a significance threshold or a rule for deleting data. DP's Fibonacci table n=20 had a 163.7% error: that wide uncertainty remains recorded. Preserve literal censored CI markers such as `? 0`; do not invent a numeric lower bound.

Overlapping intervals do not establish equality; non-overlap alone is not a complete comparison study. Timing differences do not identify cache, GC or compilation causes without additional evidence. Keep hypotheses and observations distinct.

The corrected aggregation template currently says Pending. It contains expected mathematical sums, not invented elapsed times. Fill Result and Interpretation only after reconciling source identity, effective headers, configuration coverage and completion.

## Applied protocol and sources

See the [implementation and benchmark guide](implementation-and-benchmark-guide.md) for contracts, proof/trace, executable commands and primary references. The [verification report](verification-report.md) separates local checks from pending author measurements.
