package org.anaalvarezdev.algorithms.divideandconquer;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How does counting cross inversions during merging compare with direct pairs
 * at bounded sizes and matching input distributions?
 * Theoretical Expectation:
 * Merge Theta(n log n) word work, O(n) arrays, logarithmic logical depth;
 * direct pairs Theta(n^2) work and O(1) local state. Equal keys never count.
 * Measured Work:
 * Full long result with validation. Merge includes its private clone, auxiliary
 * allocation and all copy/merge work; direct pairs reads the trial input. Setup
 * uses seed 20261025 outside timing. Defaults bound quadratic work to n <= 1000.
 * Result:
 * Author run reported 2026-10-26; source 507b2537d3dedd3beb57900d3ee72d7858571c89
 * supplied separately. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/
 * measurement, two forks (Cnt=10), combined run 7 min 4 sec. All 48 cells/errors,
 * 480 measured and 288 warmup averages: docs/17-benchmarking/results/divide-and-conquer/README.md.
 * All 16 inversion cells retained. SORTED n=100 merge/direct: 1.962 +/- 0.092 /
 * 1.140 +/- 0.054; n=1000: 26.179 +/- 2.158 / 87.771 +/- 0.685 us/op.
 * Interpretation:
 * Direct means are lower for small sorted/reverse fixtures; merge means are
 * lower across all n=1000 fixtures. This is not a general crossover threshold.
 * Two direct errors exceed 25%: RANDOM/100 74.5%, REVERSE_SORTED/1000 36.8%.
 * Pair visits are 4950/499500 versus merge item writes 672/9976 at n=100/1000,
 * plus clone/scratch/copy work. Source counts do not isolate allocation cost,
 * CPU/cache causes, significance or an exponent fitted from two sizes.
 * Protocol: docs/12-divide-and-conquer/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class InversionCountBenchmark {
    @Param({"100","1000"}) public int size;
    @Param({"RANDOM","SORTED","REVERSE_SORTED","DUPLICATE_HEAVY"}) public String distribution;
    private int[] values;
    @Setup(Level.Trial) public void setup() { values = TrialInputs.ordered(size, distribution); }
    @Benchmark public long mergeBased() { return InversionCount.mergeBased(values); }
    @Benchmark public long quadratic() { return InversionCount.quadratic(values); }
}
