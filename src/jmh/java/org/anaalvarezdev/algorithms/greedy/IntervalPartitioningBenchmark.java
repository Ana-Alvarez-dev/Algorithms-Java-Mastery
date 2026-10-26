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
 * Pending author measurements; smoke is functional/harness evidence only.
 * Interpretation:
 * Pending actual source/runtime/settings and every mean/error. This retains
 * every interval, unlike activity selection. Do not rank their unlike outputs
 * as alternative algorithms. No isolated heap, memory-byte, cache or CPU claim
 * follows from size/shape means or from an asymptotic upper bound.
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
