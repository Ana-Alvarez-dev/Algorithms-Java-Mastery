package org.anaalvarezdev.algorithms.searching;

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
 * Benchmark:
 * BinarySearchVariants
 *
 * Research Question:
 * What empirical differences appear between iterative and recursive
 * binary search when both process the same sorted input and target?
 *
 * Theoretical Expectation:
 *
 * Iterative Binary Search:
 * - Best case: Θ(1)
 * - Worst case: Θ(log n)
 * - Auxiliary space: Θ(1)
 *
 * Recursive Binary Search:
 * - Best case: Θ(1)
 * - Worst case: Θ(log n)
 * - Auxiliary stack space: Θ(log n)
 *
 * Independent Variables:
 * - Input size (n).
 * - Search scenario.
 * - Implementation strategy.
 *
 * Input Sizes:
 * - 100
 * - 1,000
 * - 10,000
 * - 100,000
 * - 1,000,000
 *
 * Scenarios:
 * - MIDDLE
 * - LAST
 * - ABSENT
 *
 * Measured Operations:
 * - BinarySearch.search(...)
 * - RecursiveBinarySearch.search(...)
 *
 * Expected Trend:
 * - Both variants should exhibit the same asymptotic search-time growth.
 * - MIDDLE should remain approximately constant.
 * - LAST and ABSENT should grow slowly as n increases.
 * - Concrete timing may differ because recursion introduces method-call
 *   and stack-management effects.
 *
 * Result:
 * To be completed after benchmark execution.
 *
 * Interpretation:
 * To be completed after analysing the experimental results.
 *
 * Limitations:
 * Results depend on the JVM, JIT compiler, hardware, cache behaviour,
 * branch prediction, operating system, benchmark configuration,
 * and runtime environment.
 *
 * This benchmark compares concrete implementations.
 * It does not prove asymptotic complexity.
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
public class BinarySearchVariantsBenchmark {

    @Param({
            "100",
            "1000",
            "10000",
            "100000",
            "1000000"
    })
    private int size;

    @Param({
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
            case "MIDDLE" -> array[(array.length - 1) / 2];
            case "LAST" -> array[array.length - 1];
            case "ABSENT" -> -1;
            default -> throw new IllegalStateException(
                    "Unexpected scenario: " + scenario
            );
        };
    }

    @Benchmark
    public void iterativeSearch(Blackhole blackhole) {
        int result = BinarySearch.search(array, target);
        blackhole.consume(result);
    }

    @Benchmark
    public void recursiveSearch(Blackhole blackhole) {
        int result = RecursiveBinarySearch.search(array, target);
        blackhole.consume(result);
    }
}
