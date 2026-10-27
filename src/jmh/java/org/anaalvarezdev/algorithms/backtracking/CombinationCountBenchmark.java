package org.anaalvarezdev.algorithms.backtracking;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How does remaining-cardinality pruning affect complete combination counting
 * when k is two, half of n, or all of n?
 * Theoretical Expectation:
 * Same C(n,k) result; pruned candidates stop at n-remaining. For k=n there are
 * n+1 pruned logical calls versus 2^n unpruned calls, but only one result.
 * Measured Work:
 * Full validated primitive count, no materialised rows or memo cache. Trial
 * setup selects k; both methods recurse over the same distinct universe 0..n-1.
 * Result:
 * Pending author measurements; short smoke does not supply research scores.
 * Interpretation:
 * Pending source/runtime/settings, parameters and every error. A targeted ALL
 * case does not imply equal gains at TWO/HALF. Source call counts are not CPU
 * events, isolated predicate costs or measured stack bytes. No exponent fit,
 * formal significance or universal pruning ratio follows from two sizes.
 * Protocol: docs/14-backtracking/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class CombinationCountBenchmark {
    @Param({"12","18"}) public int n;
    @Param({"TWO","HALF","ALL"}) public String selection;
    private int k;
    @Setup(Level.Trial) public void setup() { k = BacktrackingInputs.cardinality(n,selection); }
    @Benchmark public long countPruned() { return Combinations.countWithPruning(n,k); }
    @Benchmark public long countUnpruned() { return Combinations.countWithoutPruning(n,k); }
}
