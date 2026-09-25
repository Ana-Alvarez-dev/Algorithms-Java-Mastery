package org.anaalvarezdev.algorithms.arrays;

import org.openjdk.jmh.annotations.*;
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
public class ArrayCopyBenchmark {
    @Param({
            "100",
            "1000",
            "10000",
            "100000",
            "1000000"
    })
    private int size;

    private int[] source;

    @Setup(Level.Trial)
    public void setup() {

        source = new int[size];

        for (int index = 0; index < size; index++) {
            source[index] = index;
        }
    }

    @Benchmark
    public void manualCopy(Blackhole blackhole) {

        int[] result = ArrayCopy.copy(source);

        blackhole.consume(result);
    }

    @Benchmark
    public void systemArrayCopy(Blackhole blackhole) {

        int[] result = new int[source.length];

        System.arraycopy(
                source,
                0,
                result,
                0,
                source.length
        );

        blackhole.consume(result);
    }

}
