package org.anaalvarezdev.algorithms.searching;

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
 * Benchmark:
 * BinarySearch
 *
 * Research Question:
 * How does input size affect iterative binary-search execution time
 * when searching an already sorted array?
 *
 * Theoretical Expectation:
 *
 * Middle target:
 * T(n) = Θ(1)
 *
 * Present target requiring interval reduction:
 * T(n) = Θ(log n)
 *
 * Absent target:
 * T(n) = Θ(log n)
 *
 * Independent Variables:
 * - Input size (n).
 * - Search scenario.
 *
 * Input Sizes:
 * - 100
 * - 1,000
 * - 10,000
 * - 100,000
 * - 1,000,000
 *
 * Scenarios:
 * - MIDDLE
 * - LAST
 * - ABSENT
 *
 * Measured Operation:
 * BinarySearch.search(...)
 *
 * Expected Trend:
 * - MIDDLE should remain approximately constant because the first midpoint
 *   comparison succeeds.
 * - LAST should increase slowly as n grows, consistently with logarithmic
 *   interval reduction.
 * - ABSENT should increase slowly as n grows, consistently with logarithmic
 *   interval reduction.
 *
 * Result:
 * First reported run, 2026-10-09; average time, ns/op, 5 measurements per case.
 * - MIDDLE: reported means range from 1.041 to 1.067 ns/op across all sizes.
 * - LAST: 9.539 +/- 0.823 at n = 100;
 *   36.672 +/- 4.698 at n = 1,000,000.
 * - ABSENT: 6.854 +/- 5.328 at n = 100;
 *   25.181 +/- 14.063 at n = 1,000,000.
 * Full means and reported errors are preserved in:
 * docs/17-benchmarking/results/searching/2026-10-09-first-run-transcribed.csv
 *
 * Interpretation:
 * - MIDDLE succeeds at the first midpoint and shows near-constant timing.
 * - LAST and ABSENT show slow growth consistent with interval halving.
 * - The input is already sorted; array construction and sorting are not timed.
 * - ABSENT uses -1, below every array value; it is one unsuccessful-search
 *   workload, not an average over all absent targets.
 * - Large reported errors in some ABSENT cases limit timing precision.
 * These are preliminary observations, not a proof of asymptotic complexity
 * or a universal performance guarantee. Run provenance and limitations are
 * documented in docs/17-benchmarking/results/searching/README.md.
 *
 * Limitations:
 * Results depend on the JVM, JIT compiler, hardware, cache behaviour,
 * branch prediction, operating system, benchmark configuration,
 * and runtime environment.
 *
 * The benchmark can provide empirical evidence consistent with Θ(log n)
 * behaviour. It does not constitute a mathematical proof of asymptotic
 * complexity.
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
public class BinarySearchBenchmark {

    @Param({
            "100",
            "1000",
            "10000",
            "100000",
            "1000000"
    })
    private int size;

    @Param({
            "MIDDLE",
            "LAST",
            "ABSENT"
    })
    private String scenario;

    private int[] array;
    private int target;

    @Setup(Level.Trial)
    public void setup() {
        array = new int[size];

        for (int index = 0; index < size; index++) {
            array[index] = index;
        }

        target = switch (scenario) {
            case "MIDDLE" -> array[(array.length - 1) / 2];
            case "LAST" -> array[array.length - 1];
            case "ABSENT" -> -1;
            default -> throw new IllegalStateException(
                    "Unexpected scenario: " + scenario
            );
        };
    }

    @Benchmark
    public void search(Blackhole blackhole) {
        int result = BinarySearch.search(array, target);
        blackhole.consume(result);
    }
}
