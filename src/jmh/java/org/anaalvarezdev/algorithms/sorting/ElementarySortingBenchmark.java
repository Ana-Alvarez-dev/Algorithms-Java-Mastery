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
 * First complete subset supplied on 2026-10-12: JMH 1.37, JDK 25.0.1,
 * 2 forks x 5 measurements, average time in us/op. This group has 24 results:
 * sizes 100 and 1,000; RANDOM, SORTED and REVERSE_SORTED.
 * At n = 1,000, reported means +/- errors:
 * - RANDOM: insertion 75.763 +/- 10.892; selection 320.550 +/- 98.867;
 *   bubble 923.241 +/- 175.611 us/op.
 * - SORTED: insertion 1.781 +/- 2.007; bubble 2.736 +/- 5.653;
 *   selection 103.916 +/- 17.889 us/op.
 * - REVERSE_SORTED: selection 2339.062 +/- 6286.460 us/op.
 * The full JSON retains every result and all per-fork iteration averages:
 * docs/17-benchmarking/results/sorting/2026-10-12-first-reported-run.json.
 *
 * Interpretation:
 * - Bubble and insertion have lower reported means on sorted input than on
 *   random/reverse input, consistent with their adaptive movement rules.
 * - Selection still scans every suffix; different timings do not change its
 *   distribution-independent comparison count.
 * - Wide errors, fork differences and iteration spikes limit precise rankings.
 *   All original values are retained; none are removed as outliers.
 * - Sorted copyOnly at n = 1,000 is 8.836 +/- 31.165 us/op. This unstable
 *   control must not be subtracted to manufacture an isolated sorting cost.
 * - Only two sizes and three distributions were measured. Larger sizes and
 *   NEARLY_SORTED/DUPLICATE_HEAVY are not results of this run.
 * Interpretation is preliminary; provenance and limits are recorded in
 * docs/17-benchmarking/results/sorting/README.md.
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
