package org.anaalvarezdev.algorithms.backtracking;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * When do safe signed suffix min/max bounds change an include-first exact
 * subset decision, across root rejection, in-range misses and early success?
 * Theoretical Expectation:
 * Both retain O(2^n) worst search work and O(n) depth; bounded version adds O(n)
 * suffix preparation/storage. Miss cases cannot short-circuit on a solution;
 * PRESENT has an include-prefix witness. Bounds are safe for negative values.
 * Measured Work:
 * Complete validation/decision, including suffix arrays for bounded and no
 * matching arrays for unbounded. Trial-only fixture construction is excluded;
 * input order stays unchanged, and no witness/subset output or DP is timed.
 * Result:
 * Pending author measurements; smoke is not measured research evidence.
 * Interpretation:
 * Pending source/runtime/settings and every error. Targeted root/near-upper
 * misses do not make arbitrary subset sum easy; PRESENT can expose preparation
 * overhead, SIGNED_MISS does not receive a parity shortcut. Source node counts
 * are not elapsed-cost attribution, profiler events or asymptotic proof.
 * Protocol: docs/14-backtracking/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class SubsetSumBenchmark {
    @Param({"12","18"}) public int n;
    @Param({"RANGE_MISS","PARITY_MISS","PRESENT","SIGNED_MISS"}) public String scenario;
    private int[] values;
    private long target;
    @Setup(Level.Trial) public void setup() {
        values = BacktrackingInputs.subset(n,scenario);
        target = BacktrackingInputs.target(n,scenario);
    }
    @Benchmark public boolean bounded() { return SubsetSum.exists(values,target); }
    @Benchmark public boolean withoutBounds() { return SubsetSum.existsWithoutBounds(values,target); }
}
