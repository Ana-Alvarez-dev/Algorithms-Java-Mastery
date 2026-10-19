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
 * How do circular, linked, two-stack, bounded-deque and JDK representations
 * compare for repeated FIFO rotation at a fixed population?
 *
 * Theoretical Expectation:
 * Constant pointer/index work for ordinary endpoints; two-stack queue has
 * O(1) amortised cost but occasional O(n) transfers. Arrays are pre-sized.
 *
 * Measured Operation:
 * Remove the front, append that same boxed value and return it to JMH.
 * Size and value multiset are preserved; logical order rotates each call.
 * Trial construction/boxing are excluded. The bounded deque starts full and
 * removes before insertion. Node allocations and required stack transfers
 * are timed. Every score measures one pair, not one isolated queue operation.
 * Repeated warm data, JIT, allocator and GC constrain generalisation; no
 * isolated transfer latency, allocation rate or resizing experiment is claimed.
 *
 * Result:
 * First subset supplied on 2026-10-19: JMH 1.37, JDK 21.0.9, one thread,
 * 2 forks x 5 measurements of 500 ms after 3 x 500 ms warmup; average ns/op.
 * Author-reported source: 804f69f32b0da715ed41f390034523890c24bca5.
 * This group has twelve configurations, Cnt = 10 each. Means +/- errors:
 * Method                  n = 100              n = 1000
 * boundedDequeRotate      3.776 +/- 0.541       3.593 +/- 0.634
 * circularRotate          3.893 +/- 0.549       3.496 +/- 0.234
 * javaArrayDequeRotate    3.943 +/- 0.278       3.600 +/- 0.241
 * javaLinkedListRotate    15.107 +/- 0.709      14.982 +/- 0.602
 * linkedRotate            13.553 +/- 1.098      14.982 +/- 4.263
 * twoStacksRotate         9.017 +/- 2.828       8.463 +/- 2.339
 * All reported means/errors and per-fork iteration averages are retained in
 * docs/17-benchmarking/results/stacks-and-queues/README.md. Smoke is excluded.
 *
 * Interpretation:
 * The array-backed rings have similar means with overlapping uncertainty in
 * several comparisons. Two-stack means are about 8.5-9.0 ns/op, including
 * transfers, and linked means about 13.6-15.1 ns/op. These observations do not
 * isolate allocation or cache causes. Manual and JDK linked queues both round
 * to 14.982 ns/op at 1000 with different errors; no precise winner is claimed.
 * Size-stable rotation averages periodic transfers rather than measuring their
 * worst latency. Two sizes cannot prove amortised bounds. Interpretation is
 * preliminary and remains limited to the matched workload/runtime/settings.
 * Protocol: docs/07-stacks-and-queues/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(2)
public class QueueOperationsBenchmark {
    @Param({"100", "1000", "10000", "100000"})
    private int size;

    private CircularArrayQueue<Integer> circular;
    private LinkedQueue<Integer> linked;
    private TwoStackQueue<Integer> twoStacks;
    private BoundedArrayDeque<Integer> bounded;
    private Deque<Integer> javaArrayDeque;
    private Deque<Integer> javaLinkedList;

    @Setup(Level.Trial)
    public void setup() {
        circular = new CircularArrayQueue<>(size);
        linked = new LinkedQueue<>();
        twoStacks = new TwoStackQueue<>(size);
        bounded = new BoundedArrayDeque<>(size);
        javaArrayDeque = new ArrayDeque<>(size);
        javaLinkedList = new LinkedList<>();
        for (int value = 0; value < size; value++) {
            Integer boxed = value;
            circular.enqueue(boxed);
            linked.enqueue(boxed);
            twoStacks.enqueue(boxed);
            bounded.addLast(boxed);
            javaArrayDeque.addLast(boxed);
            javaLinkedList.addLast(boxed);
        }
    }

    @Benchmark
    public Integer circularRotate() {
        Integer value = circular.dequeue(); circular.enqueue(value); return value;
    }
    @Benchmark
    public Integer linkedRotate() {
        Integer value = linked.dequeue(); linked.enqueue(value); return value;
    }
    @Benchmark
    public Integer twoStacksRotate() {
        Integer value = twoStacks.dequeue(); twoStacks.enqueue(value); return value;
    }
    @Benchmark
    public Integer boundedDequeRotate() {
        Integer value = bounded.removeFirst(); bounded.addLast(value); return value;
    }
    @Benchmark
    public Integer javaArrayDequeRotate() {
        Integer value = javaArrayDeque.removeFirst(); javaArrayDeque.addLast(value); return value;
    }
    @Benchmark
    public Integer javaLinkedListRotate() {
        Integer value = javaLinkedList.removeFirst(); javaLinkedList.addLast(value); return value;
    }
}
