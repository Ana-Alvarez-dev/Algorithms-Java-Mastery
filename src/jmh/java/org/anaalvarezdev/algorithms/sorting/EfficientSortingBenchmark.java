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

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do merge sort, middle-pivot Hoare quick sort and Arrays.sort compare
 * across sizes and distributions when every invocation receives a fresh copy?
 *
 * Theoretical Expectation:
 * Merge: Θ(n log n), with Θ(n) auxiliary storage allocated inside the sort call.
 * Quick: balanced/expected Θ(n log n), Θ(n²) worst case, O(log n) stack here.
 * The expectation assumes an input model; structured cases are specific data.
 * Arrays.sort is the executing JDK's library reference, not a handwritten variant.
 *
 * Measured Operation:
 * Clone the immutable trial source, sort the clone, and return it to JMH.
 * Input generation is excluded; copying/allocation is INCLUDED in every method.
 * MergeSort's own workspace allocation is also measured.
 *
 * Result:
 * First complete subset supplied on 2026-10-12: JMH 1.37, JDK 25.0.1,
 * 2 forks x 5 measurements, average time in us/op. This group has 24 results:
 * sizes 100 and 1,000; RANDOM, SORTED and REVERSE_SORTED.
 * At n = 1,000, reported means +/- errors:
 * - RANDOM: Arrays.sort 14.921 +/- 1.309; merge 40.193 +/- 16.990;
 *   quick 87.551 +/- 158.007 us/op.
 * - SORTED: Arrays.sort 1.298 +/- 0.315; merge 53.682 +/- 66.156;
 *   quick 11.806 +/- 1.879 us/op.
 * - REVERSE_SORTED: Arrays.sort 2.367 +/- 2.075; merge 30.058 +/- 2.503;
 *   quick 12.740 +/- 4.516 us/op.
 * The complete mean/error tables and earlier per-fork findings are retained in
 * docs/17-benchmarking/results/sorting/README.md. JSON/log exports were removed
 * from the current tree on 2026-10-19 at the author's request.
 *
 * Interpretation:
 * - Arrays.sort has the lowest sorting mean within this group in each measured
 *   size/distribution pair. This is a descriptive observation, not a universal
 *   ranking; some errors are wide.
 * - Quick RANDOM at n = 1,000 has fork means 149.892 and 25.211 us/op;
 *   its pooled result does not support a precise comparison with merge.
 * - Merge SORTED at n = 1,000 is also highly uncertain. The data do not establish
 *   which runtime or hardware effect caused the variability.
 * - The project targets Java 21 bytecode, but this experiment used JDK 25.0.1:
 *   the library reference is that JDK's Arrays.sort, not a Java 21 measurement.
 * - copyOnly is an allocation/copy control, not a quantity to subtract.
 * - Two sizes and three distributions cannot establish asymptotic growth.
 * Interpretation is preliminary; provenance and limits are recorded in
 * docs/17-benchmarking/results/sorting/README.md.
 *
 * Limitations:
 * One deterministic dataset per pair; repeated cloned inputs; GC, JVM, hardware
 * and cache effects. QuickSort has no worst-case O(n log n) guarantee.
 * Timing does not establish space usage, stability, or asymptotic complexity.
 * Protocol: docs/05-sorting/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(2)
public class EfficientSortingBenchmark {

    @Param({"100", "1000", "10000", "100000", "1000000"})
    private int size;

    @Param({"RANDOM", "SORTED", "REVERSE_SORTED", "NEARLY_SORTED", "DUPLICATE_HEAVY"})
    private String distribution;

    private int[] source;

    @Setup(Level.Trial)
    public void setup() {
        source = SortingBenchmarkInputs.create(size, distribution);
    }

    @Benchmark
    public int[] mergeSort() {
        int[] values = source.clone();
        MergeSort.sort(values);
        return values;
    }

    @Benchmark
    public int[] quickSort() {
        int[] values = source.clone();
        QuickSort.sort(values);
        return values;
    }

    @Benchmark
    public int[] javaArraysSort() {
        int[] values = source.clone();
        Arrays.sort(values);
        return values;
    }

    @Benchmark
    public int[] copyOnly() {
        return source.clone();
    }
}
