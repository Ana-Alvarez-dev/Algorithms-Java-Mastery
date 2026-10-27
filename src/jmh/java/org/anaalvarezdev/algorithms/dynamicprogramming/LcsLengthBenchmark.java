package org.anaalvarezdev.algorithms.dynamicprogramming;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do dense memo, full table and one-row LCS lengths compare for identical,
 * disjoint, shifted and seeded random equal-length UTF-16 inputs?
 * Theoretical Expectation:
 * Same optimum length. Identical top-down follows a diagonal while disjoint
 * expands the full nonbase grid; dense memo still allocates/fills all cells.
 * Rolling retains O(min(lengths)) storage; table and rolling visit the grid.
 * Measured Work:
 * Full validated length call with fresh cache/table/row. Trial text generation
 * is excluded. No sequence reconstruction, normalisation, code-point conversion
 * or shared warmed cache. ASCII fixtures fall within the UTF-16 contract.
 * Result:
 * Author run reported 2026-10-26; source d572651484f3ec6cffe03ad589bddd5a4a8fb8a3
 * supplied separately (PR #90). JMH 1.37/JDK 21.0.9, one thread,
 * 3/5 x 500 ms warmup/measurement, two forks (Cnt=10), combined 8 min 30 sec.
 * All 57 means/errors, 570 measured and 342 warmup averages:
 * docs/17-benchmarking/results/dynamic-programming/README.md. Units stay per row.
 * All 24 LCS cells in us/op. n=128 IDENTICAL memoised/rolling/table:
 * 13.947 +/- 0.702 / 49.353 +/- 14.854 / 111.161 +/- 21.021; RANDOM:
 * 121.560 +/- 16.427 / 42.091 +/- 6.680 / 121.045 +/- 25.484.
 * Interpretation:
 * Memo means are lower than rolling on IDENTICAL/SHIFTED at both n; large
 * DISJOINT/RANDOM favour rolling means. Seven errors reach 25%; multiple
 * pairs overlap (including n=32 RANDOM memo/rolling and large RANDOM memo/table).
 * Identical memo expands n diagonal states but still fills (n+1)^2 cells;
 * table/rolling visit n^2 positions. Output length/storage alone do not explain
 * elapsed time. These are length-only ASCII fixtures; no witness throughput,
 * Unicode conversion, allocation/stack bytes, CPU cause or universal winner.
 * Protocol: docs/15-dynamic-programming/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class LcsLengthBenchmark {
    @Param({"32","128"}) public int n;
    @Param({"IDENTICAL","DISJOINT","SHIFTED","RANDOM"}) public String shape;
    private String first;
    private String second;

    @Setup(Level.Trial) public void setup() {
        var texts = DynamicProgrammingInputs.texts(n, shape);
        first = texts.first();
        second = texts.second();
    }
    @Benchmark public int memoised() { return LongestCommonSubsequence.lengthMemoised(first, second); }
    @Benchmark public int table() { return LongestCommonSubsequence.lengthTabulated(first, second); }
    @Benchmark public int rolling() { return LongestCommonSubsequence.lengthRolling(first, second); }
}
