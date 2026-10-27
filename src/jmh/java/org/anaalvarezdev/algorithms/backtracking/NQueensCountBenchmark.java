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
 * Pending author measurements; smoke checks execution and return consumption.
 * Interpretation:
 * Pending actual source/runtime/settings and all means/errors. This is not an
 * isolated bit-instruction or allocation comparison. No measured stack bytes,
 * arbitrary-n mask guarantee, cache cause or factorial fit is inferred.
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
