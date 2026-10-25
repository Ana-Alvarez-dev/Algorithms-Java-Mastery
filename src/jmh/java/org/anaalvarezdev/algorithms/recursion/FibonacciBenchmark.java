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
 * Pending author measurements; smoke scores are not research evidence.
 * Interpretation:
 * Pending source/runtime/settings and every error. Different work counts are
 * justified by source recurrences, not inferred from ratios across three inputs.
 * No isolated call overhead, allocation rate or stack-byte claim is made.
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
