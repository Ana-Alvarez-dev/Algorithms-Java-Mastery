package org.anaalvarezdev.algorithms.arrays;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/*
 * Result:
 *
 * Scenario     Input Size (n)       Average Time (ns/op)
 *
 * BEGINNING    100                  43.498
 * BEGINNING    1,000                305.321
 * BEGINNING    10,000               2,761.476
 * BEGINNING    100,000              33,740.729
 * BEGINNING    1,000,000            694,656.039
 *
 * MIDDLE       100                  19.933
 * MIDDLE       1,000                165.240
 * MIDDLE       10,000               1,284.753
 * MIDDLE       100,000              16,469.113
 * MIDDLE       1,000,000            189,389.996
 *
 * END          100                  1.642
 * END          1,000                1.412
 * END          10,000               1.589
 * END          100,000              1.616
 * END          1,000,000            1.391
 *
 * Interpretation:
 *
 * The experimental results demonstrate that deletion cost
 * depends directly on the position of the removed element.
 *
 * BEGINNING:
 * Approximately n - 1 elements must be shifted one position
 * to the left.
 *
 * T(n) = Θ(n)
 *
 * MIDDLE:
 * Approximately n / 2 elements must be shifted.
 *
 * Although fewer elements are moved:
 *
 * Θ(n / 2) = Θ(n)
 *
 * END:
 * No elements need to be shifted.
 *
 * The algorithm only clears the final logical position
 * and updates the logical size.
 *
 * T(n) = Θ(1)
 *
 * The END measurements remain approximately constant across
 * all tested input sizes, providing empirical behavior
 * consistent with constant-time deletion at the final
 * logical position.
 *
 * A more precise cost model for deletion is:
 *
 * T(n, i) = Θ(n - i)
 *
 * where n is the logical size and i is the deletion index.
 *
 * The benchmark therefore illustrates how the same operation
 * can exhibit constant or linear behavior depending on the
 * position at which it is performed.
 *
 * Measurement differences and variability may be influenced
 * by JVM/JIT optimization, CPU cache behavior, memory hierarchy,
 * memory bandwidth, and runtime conditions.
 *
 * The benchmark provides empirical evidence consistent with
 * the theoretical analysis but does not replace the formal
 * asymptotic reasoning.
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
public class DeleteAtIndexBenchmark {
    @Param({
            "100",
            "1000",
            "10000",
            "100000",
            "1000000"
    })
    private int size;

    @Param({
            "BEGINNING",
            "MIDDLE",
            "END"
    })
    private String scenario;

    private int[] array;
    private int deletionIndex;

    @Setup(Level.Trial)
    public void setup() {

        array = new int[size];

        for (int index = 0; index < size; index++) {
            array[index] = index + 1;
        }

        deletionIndex = switch (scenario) {

            case "BEGINNING" -> 0;

            case "MIDDLE" -> size / 2;

            case "END" -> size - 1;

            default -> throw new IllegalStateException(
                    "Unexpected scenario: " + scenario
            );
        };
    }

    @Benchmark
    public void delete(Blackhole blackhole) {

        int newSize = DeleteAtIndex.delete(
                array,
                size,
                deletionIndex
        );

        blackhole.consume(newSize);
        blackhole.consume(array);
    }
}
