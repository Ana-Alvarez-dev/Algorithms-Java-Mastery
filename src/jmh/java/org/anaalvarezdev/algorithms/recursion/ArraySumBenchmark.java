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
 * First author run; source fe370a1953c089a55f8851399acc42a59e3d37b7 supplied
 * separately. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/measurement,
 * two forks (Cnt=10), complete combined run 3 min 35 sec. Full means/errors,
 * 240 measured and 144 warmup averages: docs/17-benchmarking/results/recursion/README.md.
 * All six ArraySum cells retained. Size 1000000 loop: 272188.344 +/- 207549.689;
 * recursive: 3473757.020 +/- 643696.308 ns/op.
 * Interpretation:
 * Loop means are lower on these fixtures, but largest loop error is 76.3%;
 * recursive size-100 error is 35.9%. Both perform linear total work. Nonempty
 * recursive helper calls are 2*size-1, with logarithmic depth and no slice copy.
 * Source counts are not profiler attribution. No precise ratio, stack bytes,
 * cache/vectorisation cause, significance or asymptotic fit is established.
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
