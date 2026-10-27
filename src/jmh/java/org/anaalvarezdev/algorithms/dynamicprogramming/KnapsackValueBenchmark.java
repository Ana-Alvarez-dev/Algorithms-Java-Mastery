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
 * Pending author measurements; smoke scores do not supply research results.
 * Interpretation:
 * Pending source/runtime/settings and all errors. Reachable memo states do not
 * describe dense allocation cost. Do not isolate cache lookup, recursion or
 * memory traffic by subtraction. These controls are not random knapsack inputs;
 * no universal ratio, allocation rate, CPU cause or polynomial bit bound follows.
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
