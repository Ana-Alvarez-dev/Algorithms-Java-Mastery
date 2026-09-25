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
 * Scenario     Input Size (n)       Average Time (ns/op)
 *
 * BEGINNING    100                  25.393
 * BEGINNING    1,000                221.594
 * BEGINNING    10,000               2,883.733
 * BEGINNING    100,000              24,383.244
 * BEGINNING    1,000,000            321,372.055
 *
 * MIDDLE       100                  14.225
 * MIDDLE       1,000                113.906
 * MIDDLE       10,000               1,116.847
 * MIDDLE       100,000              12,042.948
 * MIDDLE       1,000,000            134,187.915
 *
 * END          100                  258.638
 * END          1,000                2.684
 * END          10,000               1.742
 * END          100,000              1.583
 * END          1,000,000            1.838
 *
 * Interpretation:
 *
 * The experimental results demonstrate that insertion cost
 * depends strongly on the insertion position.
 *
 * BEGINNING:
 * Approximately n elements must be shifted one position
 * to the right.
 *
 * T(n) = Θ(n)
 *
 * MIDDLE:
 * Approximately n / 2 elements must be shifted.
 *
 * Although fewer elements are moved than for insertion at
 * the beginning:
 *
 * Θ(n / 2) = Θ(n)
 *
 * END:
 * No existing elements need to be shifted. The algorithm
 * performs only the indexed write and constant-size bookkeeping.
 *
 * T(n) = Θ(1)
 *
 * Except for the noisy measurement at n = 100, the END
 * scenario remains approximately constant as the input size
 * grows from 1,000 to 1,000,000 elements.
 *
 * The benchmark therefore provides empirical behavior consistent
 * with the more precise cost model:
 *
 * T(n, i) = Θ(n - i + 1)
 *
 * where i is the insertion index.
 *
 * The experiment also demonstrates that benchmark setup design
 * matters. Preparing the array once per trial avoids repeatedly
 * introducing O(n) setup work around an operation whose END case
 * is theoretically Θ(1).
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
public class InsertAtIndexBenchmark {

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
    private int insertionIndex;

    @Setup(Level.Trial)
    public void setup() {

        array = new int[size + 1];

        for (int index = 0; index < size; index++) {
            array[index] = index;
        }

        insertionIndex = switch (scenario) {

            case "BEGINNING" -> 0;

            case "MIDDLE" -> size / 2;

            case "END" -> size;

            default -> throw new IllegalStateException(
                    "Unexpected scenario: " + scenario
            );
        };
    }

    @Benchmark
    public void insert(Blackhole blackhole) {

        int newSize = InsertAtIndex.insert(
                array,
                size,
                insertionIndex,
                -1
        );

        blackhole.consume(newSize);
        blackhole.consume(array);
    }
}