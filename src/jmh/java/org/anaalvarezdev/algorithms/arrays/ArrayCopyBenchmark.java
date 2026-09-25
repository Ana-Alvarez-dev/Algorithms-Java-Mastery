package org.anaalvarezdev.algorithms.arrays;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
/*
 * Result:
 *
 * Input Size (n)       Manual Copy (ns/op)       System.arraycopy (ns/op)
 *
 * 100                  88.565                    83.355
 * 1,000                1,007.289                 786.623
 * 10,000               8,622.099                 7,866.158
 * 100,000              83,562.646                77,332.065
 * 1,000,000            1,994,178.554             1,210,007.886
 *
 * Interpretation:
 *
 * Both implementations exhibit behavior consistent with
 * linear time complexity:
 *
 * T(n) = Θ(n)
 *
 * Both strategies must process n elements and allocate
 * a destination array of size n.
 *
 * The manual strategy performs one explicit indexed read
 * and one indexed write for each element.
 *
 * System.arraycopy also copies n elements, but it can take
 * advantage of JVM/runtime-level optimized copying mechanisms.
 *
 * Across the measured input sizes, System.arraycopy generally
 * achieved lower execution times than the manual implementation.
 *
 * For n = 1,000,000, the manual implementation required
 * approximately 1.65 times the execution time of System.arraycopy
 * in this benchmark run.
 *
 * This experiment demonstrates that:
 *
 * same asymptotic complexity != same practical performance.
 *
 * Both implementations remain Θ(n), but their constant factors
 * differ.
 *
 * The benchmark also includes allocation of the destination
 * array in both strategies, because creating an independent copy
 * is part of the operation being studied.
 *
 * For larger arrays, memory hierarchy, cache capacity, memory
 * bandwidth, allocation cost, garbage collection, JVM/JIT
 * optimization, and runtime conditions may influence the
 * measured execution time.
 *
 * Therefore, the empirical results are consistent with the
 * theoretical Θ(n) analysis while also showing the practical
 * advantage of the optimized platform implementation.
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
