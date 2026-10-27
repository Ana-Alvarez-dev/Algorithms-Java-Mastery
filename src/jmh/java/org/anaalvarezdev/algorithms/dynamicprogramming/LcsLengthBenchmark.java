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
 * Pending author measurements; smoke is not controlled research evidence.
 * Interpretation:
 * Pending source/runtime/settings and all errors. Output length alone does not
 * determine work; dense setup and branching remain included. No reconstruction
 * throughput, measured heap/stack bytes, cache cause, universal winner or fitted
 * exponent follows. Two sizes and one seed do not cover arbitrary String pairs.
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
