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
 * Input Size (n)       Average Time (ns/op)
 *
 * 100                  15.348
 * 1,000                136.340
 * 10,000               1,309.764
 * 100,000              14,253.154
 * 1,000,000            173,251.665
 *
 * The measured execution time increases as the input size grows.
 * Increasing the array size by approximately one order of magnitude
 * produces a roughly proportional increase in traversal time.
 *
 * Interpretation:
 *
 * The experimental results are consistent with the theoretical
 * time complexity T(n) = Θ(n).
 *
 * ArrayTraversal visits every element exactly once, and the measured
 * cost per element remains approximately stable across most input
 * sizes. This behavior is consistent with a linear relationship
 * between input size and execution time.
 *
 * Deviations from perfectly proportional growth are expected in
 * real execution environments and may be influenced by JVM/JIT
 * optimizations, CPU cache behavior, memory hierarchy, memory
 * bandwidth, and measurement variability.
 *
 * Therefore, the benchmark supports the theoretical expectation
 * of linear growth, but it does not constitute a mathematical proof
 * of Θ(n); that classification follows from algorithmic analysis.
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

    @Param({"100", "1000", "10000", "100000", "1000000"})
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
        ArrayTraversal.traverse(array, blackhole::consume);
    }
}
