package org.anaalvarezdev.algorithms.sorting;

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
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do size and initial order affect copy-plus-sort cost for elementary sorts?
 *
 * Theoretical Expectation:
 * Selection: Θ(n²) for every distribution.
 * Bubble with early exit and insertion: Θ(n) on sorted input, Θ(n²) worst case.
 * Copy-only: Θ(n). Small-input results include allocation/copy overhead.
 *
 * Measured Operation:
 * Clone the immutable trial source, sort the clone, and return it to JMH.
 * Input generation is excluded; copying/allocation is INCLUDED in every method.
 * Size is capped at 10,000 because these algorithms can perform quadratic work.
 *
 * Result:
 * Pending a recorded controlled run; smoke checks are not research results.
 *
 * Interpretation:
 * Pending. Compare identical size/distribution pairs and retain JMH errors.
 * copyOnly is a control, not a baseline to subtract mechanically.
 *
 * Limitations:
 * One deterministic dataset per pair; repeated cloned inputs; GC, JVM, hardware
 * and cache effects. Reported time is not isolated sorting time. No stability,
 * memory-space bound, or asymptotic proof follows from these measurements.
 * Protocol: docs/05-sorting/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(2)
public class ElementarySortingBenchmark {

    @Param({"100", "1000", "10000"})
    private int size;

    @Param({"RANDOM", "SORTED", "REVERSE_SORTED", "NEARLY_SORTED", "DUPLICATE_HEAVY"})
    private String distribution;

    private int[] source;

    @Setup(Level.Trial)
    public void setup() {
        source = SortingBenchmarkInputs.create(size, distribution);
    }

    @Benchmark
    public int[] selectionSort() {
        int[] values = source.clone();
        SelectionSort.sort(values);
        return values;
    }

    @Benchmark
    public int[] bubbleSort() {
        int[] values = source.clone();
        BubbleSort.sort(values);
        return values;
    }

    @Benchmark
    public int[] insertionSort() {
        int[] values = source.clone();
        InsertionSort.sort(values);
        return values;
    }

    @Benchmark
    public int[] copyOnly() {
        return source.clone();
    }
}
