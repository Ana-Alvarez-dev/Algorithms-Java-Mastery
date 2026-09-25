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

    @Setup(Level.Invocation)
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
