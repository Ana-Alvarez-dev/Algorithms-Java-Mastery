package org.anaalvarezdev.algorithms.stacksandqueues;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do manual array/linked stacks and JDK Deque implementations compare for
 * a push/pop pair on prepopulated stacks with reserved array capacity?
 *
 * Theoretical Expectation:
 * Constant endpoint work for every method; array resizing is excluded here.
 * Allocation sites for linked nodes are timed, subject to JIT optimisation.
 *
 * Measured Operation:
 * Push cached Integer -1 then pop and return it. Every invocation restores
 * size, order and values. Trial population/initial boxing are excluded.
 * Each score measures one pair, not one isolated push or pop. The fixed value
 * and repeatedly warm state restrict generalisation; inspect generated code
 * and use separate profiling before making allocation or causal claims.
 *
 * Result:
 * First subset supplied on 2026-10-19: JMH 1.37, JDK 21.0.9, one thread,
 * 2 forks x 5 measurements of 500 ms after 3 x 500 ms warmup; average ns/op.
 * Author-reported source: 804f69f32b0da715ed41f390034523890c24bca5.
 * This group has eight configurations, Cnt = 10 each. Means +/- errors:
 * Method                 n = 100              n = 1000
 * arrayPushPop           3.234 +/- 1.066       3.271 +/- 1.154
 * javaArrayDequePushPop  4.746 +/- 1.495       4.811 +/- 1.543
 * javaLinkedListPushPop  17.527 +/- 5.992      17.104 +/- 4.945
 * linkedPushPop          9.410 +/- 2.827       9.609 +/- 3.309
 * All reported means/errors, intervals, 200 measurement and 120 warmup values
 * across both groups are preserved in Markdown. Smoke scores are excluded.
 *
 * Interpretation:
 * The manual array stack has the lowest mean at both sizes, followed by JDK
 * ArrayDeque, manual linked stack and JDK LinkedList. This describes these
 * means, not a universal ranking. Every stack error exceeds 25% of its mean;
 * array and JDK ArrayDeque confidence intervals overlap. Precise speedups
 * and formal significance are not established by these comparisons.
 * Similar means across two sizes do not prove asymptotic or amortised bounds.
 * Reserved capacity excludes growth; allocation and hardware causes were not
 * profiled. Interpretation is preliminary; paired scores are not isolated ops.
 * Evidence: docs/17-benchmarking/results/stacks-and-queues/README.md.
 * Protocol: docs/07-stacks-and-queues/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(2)
public class StackOperationsBenchmark {
    @Param({"100", "1000", "10000", "100000"})
    private int size;

    private ArrayStack<Integer> array;
    private LinkedStack<Integer> linked;
    private Deque<Integer> javaArrayDeque;
    private Deque<Integer> javaLinkedList;

    @Setup(Level.Trial)
    public void setup() {
        array = new ArrayStack<>(size + 1);
        linked = new LinkedStack<>();
        javaArrayDeque = new ArrayDeque<>(size + 1);
        javaLinkedList = new LinkedList<>();
        for (int value = 0; value < size; value++) {
            Integer boxed = value;
            array.push(boxed);
            linked.push(boxed);
            javaArrayDeque.push(boxed);
            javaLinkedList.push(boxed);
        }
    }

    @Benchmark
    public Integer arrayPushPop() { array.push(-1); return array.pop(); }
    @Benchmark
    public Integer linkedPushPop() { linked.push(-1); return linked.pop(); }
    @Benchmark
    public Integer javaArrayDequePushPop() { javaArrayDeque.push(-1); return javaArrayDeque.pop(); }
    @Benchmark
    public Integer javaLinkedListPushPop() { javaLinkedList.push(-1); return javaLinkedList.pop(); }
}
