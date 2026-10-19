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
 * Pending an author-supplied measurement run. Harness smoke scores are excluded.
 *
 * Interpretation:
 * Pending. Retain means/errors, source SHA, actual JDK/JMH and complete header.
 * Measured time does not prove amortised bounds or explain hardware causes.
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
