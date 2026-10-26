package org.anaalvarezdev.algorithms.greedy;

import org.openjdk.jmh.annotations.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do complete selection calls compare when input is unsorted versus supplied
 * in the required order, across size and compatibility patterns?
 * Theoretical Expectation:
 * Sort-plus-scan has O(n log n) worst work; validated presorted scan is linear.
 * Both retain O(n) snapshot/output state. The returned canonical plans agree.
 * Measured Work:
 * Full validation, snapshots and immutable selected plan. Trial setup creates
 * seed-20261026 shuffled intervals and a finish/start-ordered list; setup sorting
 * is excluded for scanPresorted. No caller mutation, no pure sorting subtraction.
 * Result:
 * Author run reported 2026-10-26; source 674ab35b7d82f1a9ab7fbab63fa1a9e80253965c
 * supplied separately. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/
 * measurement, two forks (Cnt=10), combined run 5 min 51 sec. All 40 cells/errors,
 * 400 measured and 240 warmup averages: docs/17-benchmarking/results/greedy/README.md.
 * All 16 activity cells retained. DISJOINT n=10000 presorted/full:
 * 94.215 +/- 1.244 / 1610.244 +/- 431.882 us/op; RANDOM:
 * 56.152 +/- 1.875 / 2587.016 +/- 1158.367 us/op.
 * Interpretation:
 * Presorted means are lower for all matching fixtures, but its input order was
 * prepared outside timing. Both validate/snapshot/materialise the same plan;
 * subtraction does not isolate sorting. Seven full-sort errors reach 25%,
 * largest 48.6% at TOUCHING/100. Known selected output sizes are n for disjoint/
 * touching and one for overlapping; output count alone does not explain time.
 * No universal ratio, significance, allocation-rate or cache/CPU cause is claimed.
 * Protocol: docs/13-greedy-algorithms/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class ActivitySelectionBenchmark {
    @Param({"100","10000"}) public int size;
    @Param({"DISJOINT","TOUCHING","OVERLAPPING","RANDOM"}) public String shape;
    private List<Interval> values;
    private List<Interval> ordered;
    @Setup(Level.Trial) public void setup() {
        values = GreedyInputs.intervals(size,shape);
        ordered = new ArrayList<>(values);
        ordered.sort(Comparator.comparingInt(Interval::finish).thenComparingInt(Interval::start));
    }
    @Benchmark public List<Interval> sortAndSelect() { return ActivitySelection.select(values); }
    @Benchmark public List<Interval> scanPresorted() { return ActivitySelection.selectSorted(ordered); }
}
