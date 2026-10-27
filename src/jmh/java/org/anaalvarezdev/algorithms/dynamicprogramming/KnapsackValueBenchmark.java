package org.anaalvarezdev.algorithms.dynamicprogramming;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do dense top-down, dense bottom-up and one-row knapsack values compare
 * when unit-weight choices overlap versus every item exceeding capacity?
 * Theoretical Expectation:
 * Same exact long value. Heavy top-down expands only n states but still fills
 * a full dense cache; full table visits n*(C+1) cells. Rolling skips unavailable
 * updates and keeps O(C) state. O(nC) is pseudopolynomial in numeric capacity.
 * Measured Work:
 * Full validation, private allocation/initialisation and exact value. Seeded
 * trial construction/shuffling is excluded. Inputs stay unchanged, no plan,
 * memo reuse, fractional choices or repeated-item semantics are timed.
 * Result:
 * Author run reported 2026-10-26; source d572651484f3ec6cffe03ad589bddd5a4a8fb8a3
 * supplied separately (PR #90). JMH 1.37/JDK 21.0.9, one thread,
 * 3/5 x 500 ms warmup/measurement, two forks (Cnt=10), combined 8 min 30 sec.
 * All 57 means/errors, 570 measured and 342 warmup averages:
 * docs/17-benchmarking/results/dynamic-programming/README.md. Units stay per row.
 * All 24 knapsack cells in us/op. n=64,C=128 LIGHT rolling/memoised/table:
 * 4.597 +/- 0.777 / 23.005 +/- 2.881 / 21.544 +/- 2.661; TOO_HEAVY:
 * 0.196 +/- 0.008 / 13.668 +/- 2.160 / 17.207 +/- 1.518.
 * Interpretation:
 * Rolling has lower point means on all eight fixtures. No error reaches 25%,
 * but five memo/table interval pairs overlap; memo is not uniformly quicker.
 * Heavy memo expands n states yet fills the whole grid. At n=16, changing C
 * from 32 to 128 leaves R=16 heavy/R=136 light while dense cells rise 561/2193.
 * Source ledgers are not profiler events, allocated bytes or isolated setup
 * shares. Plans are not timed; numeric O(nC) stays pseudopolynomial. No
 * universal ratio, cache/CPU cause, significance or fitted exponent follows.
 * Protocol: docs/15-dynamic-programming/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class KnapsackValueBenchmark {
    @Param({"16","64"}) public int n;
    @Param({"32","128"}) public int capacity;
    @Param({"LIGHT","TOO_HEAVY"}) public String shape;
    private int[] weights;
    private int[] values;

    @Setup(Level.Trial) public void setup() {
        var items = DynamicProgrammingInputs.items(n, capacity, shape);
        weights = items.weights();
        values = items.values();
    }
    @Benchmark public long memoised() { return Knapsack01.valueMemoised(weights, values, capacity); }
    @Benchmark public long table() { return Knapsack01.valueTabulated(weights, values, capacity); }
    @Benchmark public long rolling() { return Knapsack01.valueRolling(weights, values, capacity); }
}
