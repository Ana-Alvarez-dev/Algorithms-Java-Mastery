package org.anaalvarezdev.algorithms.arrays;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/*
 * Result:
 *
 * Input Size (n)       Average Time (ns/op)
 *
 * 100                  21.098
 * 1,000                263.930
 * 10,000               3,664.318
 * 100,000              28,632.177
 * 1,000,000            267,564.949
 *
 * Interpretation:
 *
 * The measured execution time increases as the input size grows,
 * which is consistent with the theoretical linear complexity
 * T(n) = Θ(n).
 *
 * FindMaximum initializes the maximum candidate with the first
 * element and performs exactly n - 1 comparisons.
 *
 * The benchmark uses an ascending array, causing the maximum
 * candidate to be updated during every iteration.
 *
 * The current benchmark execution shows considerable measurement
 * variability for several input sizes. Therefore, the absolute
 * execution times should be interpreted cautiously.
 *
 * Additional warm-up iterations, measurement iterations, and JVM
 * forks may be used to obtain more stable empirical measurements.
 *
 * Despite this variability, the theoretical Θ(n) classification
 * remains unchanged because the algorithm performs one sequential
 * pass over the input array.
 *
 * Benchmarking provides empirical observations and does not replace
 * the mathematical analysis of asymptotic complexity.
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
public class FindMaximumBenchmark {
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
    public void findMaximum(Blackhole blackhole) {
        int result = FindMaximum.find(array);

        blackhole.consume(result);
    }

}
