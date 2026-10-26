package org.anaalvarezdev.algorithms.greedy;

import org.openjdk.jmh.annotations.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How does complete minimum-room assignment change with interval population
 * and overlap, retaining an assignment for every original input position?
 * Theoretical Expectation:
 * Sort O(n log n), heap maintenance O(n log(r+1)) for r allocated rooms,
 * O(n) snapshot/entry/result state. Heap retains one latest finish per room.
 * Measured Work:
 * Validation, snapshot, sorting, heap/record allocation and immutable assignment.
 * Trial-only seed-20261026 construction is excluded; input stays unchanged.
 * DISJOINT/TOUCHING need one room; OVERLAPPING needs size rooms.
 * Result:
 * Author run reported 2026-10-26; source 674ab35b7d82f1a9ab7fbab63fa1a9e80253965c
 * supplied separately. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/
 * measurement, two forks (Cnt=10), combined run 5 min 51 sec. All 40 cells/errors,
 * 400 measured and 240 warmup averages: docs/17-benchmarking/results/greedy/README.md.
 * All eight room cells retained. At n=10000 DISJOINT/TOUCHING:
 * 1901.335 +/- 19.514 / 1894.487 +/- 45.547; OVERLAPPING/RANDOM:
 * 2823.808 +/- 39.895 / 3069.430 +/- 43.918 us/op.
 * Interpretation:
 * No room error reaches 25%; disjoint/touching timing confidence intervals
 * overlap, without demonstrating equivalence. Source has n peeks/offers and n-r reuse
 * removals for r rooms: one room for disjoint/touching, n for overlapping.
 * Fewer removals or peak room count alone do not explain elapsed means; sort,
 * heap/record/result work remains included. Counts are not CPU/profile events.
 * Assigning every interval differs from selection; no cross-objective ranking,
 * isolated heap cost, memory-byte claim or fitted exponent follows.
 * Protocol: docs/13-greedy-algorithms/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class IntervalPartitioningBenchmark {
    @Param({"100","10000"}) public int size;
    @Param({"DISJOINT","TOUCHING","OVERLAPPING","RANDOM"}) public String shape;
    private List<Interval> values;
    @Setup(Level.Trial) public void setup() { values = GreedyInputs.intervals(size,shape); }
    @Benchmark public IntervalPartitioning.Assignment assignRooms() { return IntervalPartitioning.assign(values); }
}
