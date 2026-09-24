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

import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
 * Result:
 *
 * Input Size (n)       Pairwise (ns/op)       Separate Passes (ns/op)
 *
 * 100                  35.124                 45.098
 * 1,000                407.223                389.591
 * 10,000               4,236.359              3,554.124
 * 100,000              181,296.459            34,087.864
 * 1,000,000            1,915,624.640          493,124.143
 *
 * Interpretation:
 *
 * Both strategies exhibit linear asymptotic complexity:
 *
 * T(n) = Θ(n)
 *
 * From a comparison-count perspective, the pairwise strategy
 * is theoretically more efficient:
 *
 * Separate passes:
 * 2n - 2 comparisons
 *
 * Pairwise strategy:
 * approximately 3n / 2 comparisons
 *
 * However, the empirical benchmark shows that fewer comparisons
 * do not necessarily result in lower execution time.
 *
 * For small inputs, the pairwise strategy performs competitively
 * and is faster for n = 100 in this experiment.
 *
 * For larger inputs, the two independent passes are considerably
 * faster in the measured environment.
 *
 * One possible explanation is that the pairwise implementation
 * introduces more complex branching. The comparison between two
 * pseudo-random elements may be difficult for the CPU branch
 * predictor, while minimum and maximum updates during independent
 * passes become increasingly infrequent and potentially more
 * predictable.
 *
 * Other factors may include JVM/JIT optimization, instruction
 * scheduling, cache behavior, memory access patterns, and CPU
 * microarchitecture.
 *
 * Therefore, the experiment demonstrates an important distinction:
 *
 * fewer theoretical comparisons != lower measured execution time.
 *
 * Both approaches remain Θ(n), but their constant factors and
 * interaction with real hardware differ significantly.
 *
 * The benchmark does not invalidate the theoretical comparison
 * analysis. Instead, it complements it by showing that the RAM
 * model intentionally abstracts hardware-level execution effects.
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
public class FindMinimumAndMaximumBenchmark {
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

        Random random = new Random(42);

        for (int index = 0; index < size; index++) {
            array[index] = random.nextInt();
        }
    }

    @Benchmark
    public void pairwiseStrategy(Blackhole blackhole) {

        FindMinimumAndMaximum.MinMax result =
                FindMinimumAndMaximum.find(array);

        blackhole.consume(result);
    }

    @Benchmark
    public void separatePasses(Blackhole blackhole) {

        int minimum = FindMinimum.find(array);
        int maximum = FindMaximum.find(array);

        FindMinimumAndMaximum.MinMax result =
                new FindMinimumAndMaximum.MinMax(
                        minimum,
                        maximum
                );

        blackhole.consume(result);
    }
}
