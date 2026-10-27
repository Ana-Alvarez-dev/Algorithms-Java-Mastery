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
 * Author run reported 2026-10-26; source d572651484f3ec6cffe03ad589bddd5a4a8fb8a3
 * supplied separately (PR #90). JMH 1.37/JDK 21.0.9, one thread,
 * 3/5 x 500 ms warmup/measurement, two forks (Cnt=10), combined 8 min 30 sec.
 * All 57 means/errors, 570 measured and 342 warmup averages:
 * docs/17-benchmarking/results/dynamic-programming/README.md. Units stay per row.
 * All nine Fibonacci cells in ns/op. At n=90 rolling/table/memoised:
 * 15.455 +/- 1.323 / 147.322 +/- 10.103 / 536.951 +/- 30.365.
 * Interpretation:
 * Rolling point means are lower at all n, but four errors reach 25%. Table
 * n=20 has 86.692 +/- 141.911 ns/op (163.7%); literal CI lower marker ? 0
 * is preserved. Fork means 127.539/45.845 retain the 347.118 iteration.
 * No precise n=20 table ordering or significance/equivalence is claimed.
 * Same n-1 recurrence additions do not explain all differences: fresh memo/
 * table setup and state differ. No isolated call/allocation cost, TCO, CPU
 * cause, arbitrary-precision bound or reuse of earlier Recursion data follows.
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
