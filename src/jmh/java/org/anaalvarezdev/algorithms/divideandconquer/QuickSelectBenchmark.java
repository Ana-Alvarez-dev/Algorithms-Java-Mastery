package org.anaalvarezdev.algorithms.divideandconquer;

import org.openjdk.jmh.annotations.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * What is the complete cost of obtaining one middle order statistic while
 * preserving the trial input, using selection versus sorting a fresh copy?
 * Theoretical Expectation:
 * Deterministic three-way selection retains one side, O(n^2) worst-case work,
 * O(1) algorithm state; all equal Theta(n). No expected-linear guarantee is
 * borrowed from randomised selection. Sorting is a library reference workflow.
 * Measured Work:
 * Both methods clone INSIDE timing and return ascending zero-based rank size/2
 * (upper middle at even sizes). Selection partitions that copy; reference sorts
 * it fully. Trial setup uses seed 20261025 and is excluded. No reused sorted data.
 * Result:
 * Author run reported 2026-10-26; source 507b2537d3dedd3beb57900d3ee72d7858571c89
 * supplied separately. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/
 * measurement, two forks (Cnt=10), combined run 7 min 4 sec. All 48 cells/errors,
 * 480 measured and 288 warmup averages: docs/17-benchmarking/results/divide-and-conquer/README.md.
 * All 16 selection/sort cells retained. SORTED n=10000 select/sort:
 * 18.015 +/- 3.109 / 9.164 +/- 2.476 us/op; RANDOM: 102.795 +/- 7.247 /
 * 455.682 +/- 60.337 us/op. Both workflows include a timed clone.
 * Interpretation:
 * Selection means are lower for RANDOM; sort means are lower for ordered data.
 * DUPLICATE_HEAVY n=10000 intervals overlap (92.606 +/- 11.744 versus
 * 113.075 +/- 19.241 us/op). Four workflow errors reach 25%, largest 40.2%.
 * Sorted selection returns after one partition of n items: the first pivot's
 * equal band contains rank n/2. A linear pass still need not beat a library
 * call. Its exact library path/CPU causes were not profiled. Deterministic
 * worst-case bounds remain; no pure partition cost, universal ratio or formal
 * significance/equivalence follows from these complete copy-plus-operation means.
 * Protocol: docs/12-divide-and-conquer/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class QuickSelectBenchmark {
    @Param({"100","10000"}) public int size;
    @Param({"RANDOM","SORTED","REVERSE_SORTED","DUPLICATE_HEAVY"}) public String distribution;
    private int[] values;
    @Setup(Level.Trial) public void setup() { values = TrialInputs.ordered(size, distribution); }
    @Benchmark public int selectCopy() { return QuickSelect.select(values.clone(), size / 2); }
    @Benchmark public int sortCopy() {
        int[] copy = values.clone();
        Arrays.sort(copy);
        return copy[size / 2];
    }
}
