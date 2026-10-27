package org.anaalvarezdev.algorithms.backtracking;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How does distinct-value multiplicity generation compare with scanning sorted
 * input positions and skipping used/equivalent copies, for the same exact count?
 * Theoretical Expectation:
 * Same unique value prefixes/leaves. Candidate loops inspect n positions versus
 * u distinct-value entries; both restore private state. No memoisation/formula.
 * Measured Work:
 * Full validation, private clone/sort/state allocation and complete count.
 * Seed-20261026 trial shuffling is excluded; input stays unchanged. No output
 * permutations are materialised, so enumeration/output throughput is not timed.
 * Result:
 * Pending author measurements; smoke is harness evidence only.
 * Interpretation:
 * Pending actual source/runtime/settings and every mean/error. Duplication
 * changes both result population and candidate checks; compare matching inputs.
 * No isolated call/candidate cost, allocation rate, CPU/cache cause or exponent
 * is inferred from these two sizes or a short execution check.
 * Protocol: docs/14-backtracking/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class PermutationCountBenchmark {
    @Param({"6","8"}) public int n;
    @Param({"DISTINCT","DUPLICATE_HEAVY","ALL_EQUAL"}) public String distribution;
    private int[] values;
    @Setup(Level.Trial) public void setup() { values = BacktrackingInputs.permutations(n,distribution); }
    @Benchmark public long sortedPositions() { return Permutations.countUnique(values); }
    @Benchmark public long frequencyCounts() { return Permutations.countUsingFrequencies(values); }
}
