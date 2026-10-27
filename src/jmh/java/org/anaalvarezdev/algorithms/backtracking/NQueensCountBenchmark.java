package org.anaalvarezdev.algorithms.backtracking;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How does full ordinary N-Queens counting compare using column/diagonal arrays
 * versus masks that directly enumerate available columns?
 * Theoretical Expectation:
 * Same valid-prefix tree/result, no symmetry reduction. Flags loop over n
 * columns at each nonterminal node; masks visit available children directly.
 * Both retain O(n) logical depth. Mask width is protected by the n<=12 cap.
 * Measured Work:
 * Full validated count. Flags include private arrays/path allocation and writes;
 * mask recursion uses primitive state. No boards are materialised or cached.
 * Result:
 * Author run reported 2026-10-26; source b62df1b84810dfe5dea023d5d00bac2eb05c0cd0
 * supplied separately (PR #88). JMH 1.37/JDK 21.0.9, Windows 11 amd64,
 * one thread, 3/5 x 500 ms warmup/measurement, two forks (Cnt=10).
 * Combined run 6 min 42 sec; all 46 cells/errors, 460 measured and 276 warmup
 * averages: docs/17-benchmarking/results/backtracking/README.md.
 * All six ordinary-count cells retained. n=12 masks/flags:
 * 5663.154 +/- 329.313 / 47867.076 +/- 448.464 us/op; result count 14200
 * is derived from the contract/reference, not printed by JMH.
 * Interpretation:
 * Mask means are lower at n=8/10/12. Both visit the same valid-prefix tree;
 * at n=12, source counts give 10103868 flag candidate checks versus 856188
 * available-child iterations. Flags also include arrays/path writes. This
 * does not isolate a bit instruction, actual allocation rate or CPU cause.
 * n=8 flags error is 29.5%; all observations remain. O(n) depth is distinct
 * from constant-size mask state per frame; no arbitrary-n or stack-byte claim.
 * Protocol: docs/14-backtracking/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class NQueensCountBenchmark {
    @Param({"8","10","12"}) public int n;
    @Benchmark public long flags() { return NQueens.countWithFlags(n); }
    @Benchmark public long bitMasks() { return NQueens.countWithBitMasks(n); }
}
