package org.anaalvarezdev.algorithms.recursion;

import org.openjdk.jmh.annotations.*;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How does a balanced recursive long sum compare with a loop over the same int array?
 * Theoretical Expectation:
 * Both use Theta(n) word work. Balanced recursion has O(log(n+1)) logical depth;
 * loop O(1) local state. Every possible int[] mathematical sum fits long.
 * Measured Work:
 * Trial setup creates full-range int values with seed 20261025; construction is
 * excluded. Each timed call validates, scans and returns the full long sum.
 * The array is shared read-only within the trial; there is no timed clone.
 * Result:
 * Pending author measurements; smoke is harness evidence only.
 * Interpretation:
 * Pending actual source/runtime/settings, sizes and every mean/error. Source
 * work/depth, JIT optimisation and elapsed means are distinct. No cache cause,
 * exact stack-byte usage or asymptotic proof follows from the measured scores.
 * Protocol: docs/11-recursion/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class ArraySumBenchmark {
    @Param({"100","10000","1000000"}) private int size;
    private int[] values;
    @Setup(Level.Trial) public void setup() { values = new SplittableRandom(20261025).ints(size).toArray(); }
    @Benchmark public long recursive() { return ArraySum.recursive(values); }
    @Benchmark public long iterative() { return ArraySum.iterative(values); }
}
