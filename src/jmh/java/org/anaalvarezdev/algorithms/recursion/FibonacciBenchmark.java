package org.anaalvarezdev.algorithms.recursion;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How does repeated branching compare with per-call memoisation and iteration
 * for computing the same exact Fibonacci value?
 * Theoretical Expectation:
 * Naive calls satisfy C(n)=2F(n+1)-1, exponential work and linear logical depth.
 * Memoisation has O(n) word work/array/depth; iteration O(n) work, O(1) local state.
 * Measured Work:
 * A complete validated calculation from trial-state n is returned. Memoised
 * includes allocation/initialisation of a NEW memo array every invocation; no
 * cross-call warm cache is reused. Allocation and JIT effects are not isolated.
 * Result:
 * First author run; source fe370a1953c089a55f8851399acc42a59e3d37b7 supplied
 * separately. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/measurement,
 * two forks (Cnt=10), complete combined run 3 min 35 sec. Full means/errors,
 * 240 measured and 144 warmup averages: docs/17-benchmarking/results/recursion/README.md.
 * All nine Fibonacci cells retained. At n=30: naive 3429961.531 +/- 699197.741,
 * memoised 143.788 +/- 108.596, iterative 6.713 +/- 1.182 ns/op.
 * Interpretation:
 * Naive total helper calls are 177/21891/2692537 for n=10/20/30, not exponential
 * live depth. Memoised computes states with a fresh array/fill per call; its
 * n=30 error reaches 75.5%, limiting precise ratios. Loop n=20/30 intervals
 * overlap, not evidence of constant work. Source recurrence, rather than a fit
 * to three means, justifies work counts. No isolated allocation/call cost,
 * cache cause, exact stack usage or universal ranking is established.
 * Protocol: docs/11-recursion/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class FibonacciBenchmark {
    @Param({"10","20","30"}) private int n;
    @Benchmark public long naive() { return Fibonacci.naive(n); }
    @Benchmark public long memoised() { return Fibonacci.memoised(n); }
    @Benchmark public long iterative() { return Fibonacci.iterative(n); }
}
