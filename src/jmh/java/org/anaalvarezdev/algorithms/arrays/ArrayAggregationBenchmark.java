package org.anaalvarezdev.algorithms.arrays;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/*
 * Result:
 *
 * Input Size (n)       Average Time (ns/op)
 *
 * 100                  19.046
 * 1,000                249.818
 * 10,000               2,541.590
 * 100,000              25,949.628
 * 1,000,000            277,151.221
 *
 * The measured execution time increases as the size of the
 * input array grows.
 *
 * From 1,000 elements onward, increasing the input size by
 * approximately one order of magnitude results in an execution
 * time increase close to the same order of magnitude.
 *
 * Interpretation:
 *
 * The experimental results are consistent with the theoretical
 * time complexity T(n) = Θ(n).
 *
 * ArrayAggregation performs one complete traversal of the array.
 * During each iteration, it accesses one element, performs an
 * addition, and updates the accumulator.
 *
 * The measured execution time per element remains relatively
 * stable for larger input sizes, which is consistent with a
 * linear relationship of the form:
 *
 * T(n) ≈ c * n
 *
 * Small deviations from perfectly proportional growth are
 * expected because real execution is influenced by factors such
 * as JVM and JIT optimizations, CPU cache behavior, memory
 * hierarchy, memory bandwidth, and measurement variability.
 *
 * The benchmark therefore provides empirical evidence consistent
 * with the theoretical Θ(n) analysis. It does not constitute a
 * mathematical proof of asymptotic complexity.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(
        iterations = 3,
        time = 500,
        timeUnit = TimeUnit.MILLISECONDS
)
@Measurement(
        iterations = 5,
        time = 500,
        timeUnit = TimeUnit.MILLISECONDS
)
@Fork(1)
public class ArrayAggregationBenchmark {
    @Param({
            "100",
            "1000",
            "10000",
            "100000",
            "1000000"
    })
    private int size;

    private int[] array;

    @Setup(Level.Trial)
    public void setup() {
        array = new int[size];

        for (int index = 0; index < size; index++) {
            array[index] = index;
        }
    }

    @Benchmark
    public void sum(Blackhole blackhole) {
        int result = ArrayAggregation.sum(array);

        blackhole.consume(result);
    }

}
