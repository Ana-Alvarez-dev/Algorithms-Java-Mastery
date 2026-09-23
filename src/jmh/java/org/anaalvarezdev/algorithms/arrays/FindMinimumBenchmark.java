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
 * 100                  22.303
 * 1,000                167.196
 * 10,000               1,648.084
 * 100,000              15,208.461
 * 1,000,000            169,009.064
 *
 * Execution time increases as the size of the input array grows.
 *
 * For larger input sizes, increasing n by approximately one
 * order of magnitude results in an execution-time increase
 * close to the same order of magnitude.
 *
 * Interpretation:
 *
 * The experimental results are consistent with the theoretical
 * time complexity T(n) = Θ(n).
 *
 * FindMinimum initializes the minimum candidate with the first
 * array element and then performs exactly n - 1 comparisons.
 *
 * The benchmark uses a descending array, causing the current
 * minimum candidate to be updated during every iteration.
 *
 * Despite these repeated updates, the amount of work performed
 * per element remains constant, so the total running time grows
 * linearly with the input size.
 *
 * The measured execution time per element remains relatively
 * stable for larger input sizes, which is consistent with:
 *
 * T(n) ≈ c * n
 *
 * Deviations from perfectly proportional growth are expected
 * because execution time is influenced by JVM/JIT optimizations,
 * CPU cache behavior, branch prediction, memory hierarchy,
 * and measurement variability.
 *
 * The benchmark provides empirical evidence consistent with
 * the theoretical Θ(n) analysis, but it does not constitute
 * a mathematical proof of asymptotic complexity.
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
public class FindMinimumBenchmark {
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
            array[index] = size - index;
        }
    }

    @Benchmark
    public void findMinimum(Blackhole blackhole) {
        int result = FindMinimum.find(array);

        blackhole.consume(result);
    }
}
