# Fibonacci Case Study

F(0)=0, F(1)=1 and F(n)=F(n-1)+F(n-2). This is exact value computation, not
an optimisation objective. It demonstrates repeated subproblems and storage
trade-offs without introducing an unnecessary choice rule.

## Contract and Reuse

`FibonacciTabulation.value(n)` accepts 0..92 and returns an exact long. Reject
other arguments before allocating; F(93) exceeds long. It creates a fresh table,
sets the base values and fills entries in increasing order. At n=5 the table
is [0,1,1,2,3,5]. Induction proves each predecessor and then the next entry.

Memoised and two-value iterative variants are reused from module 11's
`recursion.Fibonacci`, not copied into new production classes. Its naive variant
remains deliberately capped at 30 and is not timed by the new template. Existing
Recursion measurements preserve their own source, settings and smaller fixtures.

| Variant | Word work in the accepted domain | Auxiliary storage |
|---|---|---|
| Naive recursion | Exponential search; exact calls 2F(n+1)-1 | Linear logical depth |
| Reused memoised | O(n+1), including fresh allocation/fill | O(n+1) array and depth |
| New full table | O(n+1) | O(n+1) table |
| Reused iterative | O(n+1) | O(1) retained scalar state |

These are primitive-word bounds within 0..92. Arbitrary-precision Fibonacci
needs bit-size/arithmetic analysis; a long overflow is not an acceptable result.
Five new tests check every supported value against BigInteger, cross-variant
agreement, Cassini's identity, invalid limits and interleaved boundary calls.

## Benchmark Question

Three methods at n=20/40/90 define nine cells in **ns/op**. Complete public calls
include private memo/table setup; no warm cache persists. The other new templates
use us/op, so retain row units. `Result`/`Interpretation` await author measurements;
smoke and previous Recursion results cannot supply them. No isolated recursion,
allocation or tail-call-elimination claim follows from a timing difference.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) gives the proof, sources and protocol. Next:
[0/1 knapsack](09-knapsack-case-study.md).
