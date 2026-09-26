package org.anaalvarezdev.algorithms.arrays;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/*
 * Result:
 *
 * Scenario      Input Size (n)       Average Time (ns/op)
 *
 * UNIQUE        100                  43.957
 * UNIQUE        1,000                236.314
 * UNIQUE        10,000               2,243.352
 * UNIQUE        100,000              21,083.326
 * UNIQUE        1,000,000            541,469.762
 *
 * PAIRS         100                  95.512
 * PAIRS         1,000                977.375
 * PAIRS         10,000               7,117.656
 * PAIRS         100,000              68,918.998
 * PAIRS         1,000,000            924,959.431
 *
 * ALL_EQUAL     100                  75.119
 * ALL_EQUAL     1,000                303.528
 * ALL_EQUAL     10,000               2,614.715
 * ALL_EQUAL     100,000              23,933.981
 * ALL_EQUAL     1,000,000            637,098.637
 *
 * Interpretation:
 *
 * All three input distributions exhibit behavior consistent
 * with linear time complexity:
 *
 * T(n) = Θ(n)
 *
 * The read pointer examines every array element regardless
 * of the number of duplicate values.
 *
 * Therefore, the algorithm performs approximately n - 1
 * comparisons in all scenarios.
 *
 * The scenarios differ in the amount of additional work:
 *
 * UNIQUE:
 * Nearly every comparison identifies a new value, causing
 * approximately n - 1 writes.
 *
 * PAIRS:
 * Approximately half of the inspected elements produce
 * writes to the logical unique region.
 *
 * ALL_EQUAL:
 * After the first element, no additional unique values need
 * to be written.
 *
 * Despite performing fewer writes than the UNIQUE scenario,
 * the PAIRS distribution produced the highest measured
 * execution time in this experiment.
 *
 * This demonstrates that practical execution time is not
 * determined solely by the number of assignments. Branch
 * behavior, JVM/JIT optimization, CPU execution, cache effects,
 * memory hierarchy, and other runtime characteristics may also
 * influence performance.
 *
 * The benchmark alone does not identify which hardware-level
 * factor causes these differences; profiling would be required
 * for that conclusion.
 *
 * For the largest arrays, execution time increases more sharply,
 * which may reflect memory hierarchy and bandwidth effects.
 *
 * The empirical measurements therefore complement the theoretical
 * analysis:
 *
 * UNIQUE      -> Θ(n)
 * PAIRS       -> Θ(n)
 * ALL_EQUAL   -> Θ(n)
 *
 * Different duplicate distributions change constant factors
 * and runtime behavior but do not change the asymptotic
 * complexity of the algorithm.
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
public class RemoveDuplicatesSortedBenchmark {
    @Param({
            "100",
            "1000",
            "10000",
            "100000",
            "1000000"
    })
    private int size;

    @Param({
            "UNIQUE",
            "PAIRS",
            "ALL_EQUAL"
    })
    private String scenario;

    private int[] source;
    private int[] working;

    @Setup(Level.Trial)
    public void setupTrial() {

        source = new int[size];
        working = new int[size];

        switch (scenario) {

            case "UNIQUE" -> {
                for (int index = 0; index < size; index++) {
                    source[index] = index;
                }
            }

            case "PAIRS" -> {
                for (int index = 0; index < size; index++) {
                    source[index] = index / 2;
                }
            }

            case "ALL_EQUAL" -> {
                for (int index = 0; index < size; index++) {
                    source[index] = 1;
                }
            }

            default -> throw new IllegalStateException(
                    "Unexpected scenario: " + scenario
            );
        }
    }

    @Setup(Level.Invocation)
    public void setupInvocation() {

        System.arraycopy(
                source,
                0,
                working,
                0,
                size
        );
    }

    @Benchmark
    public void removeDuplicates(Blackhole blackhole) {

        int newSize =
                RemoveDuplicatesSorted.remove(working);

        blackhole.consume(newSize);
        blackhole.consume(working);
    }

}
