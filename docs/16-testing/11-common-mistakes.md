# Common Testing Mistakes

| Mistake | Concrete consequence | Selected correction |
|---|---|---|
| Equating coverage with correctness | 100%-covered search still chooses later duplicates | Contract partitions, linear argmin and failing regressions |
| Checking only valid index/distance | Misses lowest-index tie policy | Assert the exact oracle index |
| Deriving expected values with the SUT | Shared wrong logic passes | Explicit cases and independent linear scan |
| Ignoring valid duplicates/extremes | Boundary policy remains untested | Named cases, exhaustive multisets and long arithmetic |
| Treating loop iterations as test counts | Inflates evidence | Report 16 new invocations separately from queries |
| Reusing mutable fixtures | Order-dependent failures | Copying builder/case and isolation assertion |
| Asserting unspecified invalid-input behaviour | Invents API guarantees | Distinguish null rejection from sortedness precondition |
| Using overflowing property transformations | Tests the wrong relation | Safe bounded translation/positive scaling |
| Treating benchmark data as automatically valid | Measures out-of-contract arithmetic | Long/formula fixture checks and separate future dataset |
| Measuring timings under coverage | Changes the performance workflow | Separate clean coverage and JMH runs |

Mocking is unnecessary for these pure functions. Property/mutation libraries
and CI workflows need their own justified contribution; this module does not
claim them installed. Existing framework versions remain pinned, and JaCoCo is
an opt-in verification tool. The previous suite's 694 green tests, twelve-case
red run with six failures, and final corrected suite are recorded distinctly.

The [implementation and verification guide](implementation-and-verification-guide.md) and [verification report](verification-report.md) connect contracts,
traces, tests, local counters and limitations. The next numbered module is
**17 - Benchmarking**; correctness of its fixture comes before interpreting
its timings. Earlier recorded means retain their own source and uncertainty.

## Ordered Review Clarification

Two earlier pending items now have later evidence: the corrected aggregation
run is recorded under Benchmarking, and module 19 supplies a real CI workflow
and dated hosted executions. They do not recover the missing original jar
hash or establish this review's future PR status. Likewise, a successful
-DskipTests package is not a test execution, an empty-suite guard is not an
exact-count gate, and reporting coverage alone is not enforcing a threshold.

The [applied evidence map](test-contracts.md) connects this clarification to
the actual source, domains and profile scope. The [documentation review](documentation-review.md)
records TE01-TE10 and the fresh Java 21 outcomes.
