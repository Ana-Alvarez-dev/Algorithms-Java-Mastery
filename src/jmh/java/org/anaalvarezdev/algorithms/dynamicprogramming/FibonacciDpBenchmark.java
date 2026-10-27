package org.anaalvarezdev.algorithms.dynamicprogramming;

import org.anaalvarezdev.algorithms.recursion.Fibonacci;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do fresh memoisation, a retained full table and two-value rolling state
 * compare for the same exact long Fibonacci value at n=20/40/90?
 * Theoretical Expectation:
 * Linear word work, with O(n) memo/table storage versus O(1) rolling state.
 * Existing recursion.Fibonacci APIs are reused; no duplicated algorithm/cache.
 * Measured Work:
 * Full validated value calls, including per-call memo fill/table allocation.
 * No naive exponential search, retained warm cache or sequence output.
 * Result:
 * Pending author measurements; short smoke is functional/harness evidence.
 * Interpretation:
 * Pending source/runtime/settings and all errors. This is a public-workflow
 * comparison, not isolated recursion/allocation cost. ns/op here differs from
 * the other templates' us/op; do not pool rows or reuse earlier Recursion data.
 * No TCO, allocation rate, CPU/cache cause or arbitrary-precision bound follows.
 * Protocol: docs/15-dynamic-programming/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class FibonacciDpBenchmark {
    @Param({"20","40","90"}) public int n;
    @Benchmark public long memoised() { return Fibonacci.memoised(n); }
    @Benchmark public long table() { return FibonacciTabulation.value(n); }
    @Benchmark public long rolling() { return Fibonacci.iterative(n); }
}
