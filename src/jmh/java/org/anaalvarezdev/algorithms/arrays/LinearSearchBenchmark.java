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
 * LinearSearch Benchmark
 *
 * Research Question:
 * How do input size and target position affect
 * linear-search execution time?
 *
 * Theoretical Expectation:
 *
 * First position:
 * T(n) = Θ(1)
 *
 * Middle position:
 * approximately n / 2 comparisons
 * T(n) = Θ(n)
 *
 * Last position:
 * n comparisons
 * T(n) = Θ(n)
 *
 * Absent target:
 * n comparisons
 * T(n) = Θ(n)
 *
 * Independent Variables:
 * - Array size (n).
 * - Target position.
 *
 * Input Sizes:
 * - 100
 * - 1,000
 * - 10,000
 * - 100,000
 * - 1,000,000
 *
 * Scenarios:
 * - FIRST
 * - MIDDLE
 * - LAST
 * - ABSENT
 *
 * Benchmark Tool:
 * JMH
 *
 * Measured Operation:
 * LinearSearch.search(...)
 *
 * Expected Trend:
 * - FIRST should remain approximately constant as n grows.
 * - MIDDLE should grow approximately linearly.
 * - LAST should grow approximately linearly.
 * - ABSENT should grow approximately linearly.
 *
 * Result:
 * To be completed after benchmark execution.
 *
 * Interpretation:
 * To be completed after analyzing experimental results.
 *
 * Limitations:
 * Results depend on JVM/JIT optimization, CPU architecture,
 * cache behavior, branch prediction, memory hierarchy,
 * and runtime environment.
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
public class LinearSearchBenchmark {
    @Param({
            "100",
            "1000",
            "10000",
            "100000",
            "1000000"
    })
    private int size;

    @Param({
            "FIRST",
            "MIDDLE",
            "LAST",
            "ABSENT"
    })
    private String scenario;

    private int[] array;
    private int target;

    @Setup(Level.Trial)
    public void setup() {

        array = new int[size];

        for (int index = 0; index < size; index++) {
            array[index] = index;
        }

        target = switch (scenario) {

            case "FIRST" ->
                    array[0];

            case "MIDDLE" ->
                    array[size / 2];

            case "LAST" ->
                    array[size - 1];

            case "ABSENT" ->
                    -1;

            default ->
                    throw new IllegalStateException(
                            "Unexpected scenario: " + scenario
                    );
        };
    }

    @Benchmark
    public void search(Blackhole blackhole) {

        int result = LinearSearch.search(array, target);

        blackhole.consume(result);
    }

}
