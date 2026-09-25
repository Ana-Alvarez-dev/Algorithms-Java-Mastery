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
 * 100                  33.419
 * 1,000                311.847
 * 10,000               3,033.378
 * 100,000              30,893.113
 * 1,000,000            337,446.045
 *
 * Interpretation:
 *
 * The experimental results are consistent with the theoretical
 * time complexity T(n) = Θ(n).
 *
 * ArrayReverse uses two pointers that move from opposite ends
 * of the array toward the center.
 *
 * The algorithm performs approximately:
 *
 * floor(n / 2)
 *
 * swaps.
 *
 * Each swap requires a constant amount of work, including
 * indexed reads, indexed writes, pointer updates, and the
 * temporary variable assignment.
 *
 * Therefore:
 *
 * T(n) ≈ c * (n / 2)
 *
 * and asymptotically:
 *
 * Θ(n / 2) = Θ(n)
 *
 * The benchmark shows that increasing the array size by
 * approximately one order of magnitude results in an execution
 * time increase close to the same order of magnitude.
 *
 * The measured execution time per element also remains relatively
 * stable across the tested input sizes, which is consistent with
 * linear growth.
 *
 * Small deviations from perfectly proportional growth are expected
 * because real execution is influenced by JVM/JIT optimization,
 * CPU cache behavior, memory hierarchy, memory bandwidth,
 * and measurement variability.
 *
 * The empirical results support the theoretical Θ(n) analysis
 * but do not constitute a mathematical proof of asymptotic
 * complexity.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(
        iterations = 5,
        time = 1,
        timeUnit = TimeUnit.SECONDS
)
@Measurement(
        iterations = 10,
        time = 1,
        timeUnit = TimeUnit.SECONDS
)
@Fork(2)
public class ArrayReverseBenchmark {
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
    public void reverse(Blackhole blackhole) {

        ArrayReverse.reverse(array);

        blackhole.consume(array);
    }
}
