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
 * Author run reported 2026-10-26; source b62df1b84810dfe5dea023d5d00bac2eb05c0cd0
 * supplied separately (PR #88). JMH 1.37/JDK 21.0.9, Windows 11 amd64,
 * one thread, 3/5 x 500 ms warmup/measurement, two forks (Cnt=10).
 * Combined run 6 min 42 sec; all 46 cells/errors, 460 measured and 276 warmup
 * averages: docs/17-benchmarking/results/backtracking/README.md.
 * All 16 decisions retained. n=18 bounded/withoutBounds RANGE_MISS:
 * 0.055 +/- 0.001 / 774.377 +/- 11.168; SIGNED_MISS:
 * 360.426 +/- 6.790 / 778.161 +/- 20.444 us/op.
 * Interpretation:
 * Bounded means are lower for all miss controls, while withoutBounds is lower
 * for PRESENT at both n (n=18: 0.010 +/- 0.001 versus bounded 0.062 +/- 0.001).
 * The witness uses the same n/2+1 include-prefix calls; bounded still prepares
 * suffix arrays/checks bounds. Root rejection still has O(n) preparation.
 * SIGNED_MISS has no parity shortcut and retains many prefixes. No error
 * reaches 25%; tiny rounded scores retain printed nonzero uncertainty. No
 * universal pruning gain, isolated allocation cost or polynomial bound follows.
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
