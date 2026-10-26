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
 * Pending author measurements; short smoke checks harness execution only.
 * Interpretation:
 * Pending source/runtime/settings, all means/errors and parameters. Presorted
 * preparation is a different caller precondition, not free end-to-end sorting.
 * Compare matching shapes; source work/output differences do not establish
 * allocation rates, cache/CPU causes, significance or a fitted exponent.
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
