package org.anaalvarez.algorithms.arrays;

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
 * ArrayTraversal Benchmark
 *
 * Research Question:
 * How does sequential traversal execution time change
 * as the array size increases?
 *
 * Theoretical Expectation:
 * The algorithm performs one visit per element.
 *
 * T(n) = Θ(n)
 *
 * Independent Variable:
 * Array size (n).
 *
 * Input Sizes:
 * - 100
 * - 1,000
 * - 10,000
 * - 100,000
 * - 1,000,000
 *
 * Benchmark Tool:
 * JMH
 *
 * Measured Operation:
 * ArrayTraversal.traverse(...)
 *
 * Expected Trend:
 * Execution time should increase approximately linearly
 * as n increases.
 *
 * Result:
 * To be completed after benchmark execution.
 *
 * Interpretation:
 * To be completed after analyzing experimental results.
 *
 * Limitations:
 * Results depend on JVM, JIT compilation, CPU architecture,
 * cache behavior, and runtime environment.
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
public class ArrayTraversalBenchmark {

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
    public void traverse(Blackhole blackhole) {

        ArrayTraversal.traverse(
                array,
                blackhole::consume
        );
    }
}