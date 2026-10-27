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
 * Author run reported 2026-10-26; source b62df1b84810dfe5dea023d5d00bac2eb05c0cd0
 * supplied separately (PR #88). JMH 1.37/JDK 21.0.9, Windows 11 amd64,
 * one thread, 3/5 x 500 ms warmup/measurement, two forks (Cnt=10).
 * Combined run 6 min 42 sec; all 46 cells/errors, 460 measured and 276 warmup
 * averages: docs/17-benchmarking/results/backtracking/README.md.
 * All 12 combination cells retained. n=18/HALF pruned/unpruned:
 * 137.222 +/- 0.659 / 268.161 +/- 40.492; ALL:
 * 0.044 +/- 0.001 / 455.584 +/- 19.181 us/op.
 * Interpretation:
 * Pruned means are lower at HALF/ALL, but unpruned means are lower at TWO
 * for both n. TWO removes only one logical call (78/79 or 171/172); ALL
 * changes n+1 versus 2^n calls with one result. Source call counts are not
 * CPU events or isolated predicate costs. n=12/HALF unpruned error is 38.3%,
 * fork means 4.834/7.892 us/op; its interval overlaps pruned. Keep all
 * observations; no universal pruning
 * ratio, significance/equivalence, cause attribution or exponent fit follows.
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
