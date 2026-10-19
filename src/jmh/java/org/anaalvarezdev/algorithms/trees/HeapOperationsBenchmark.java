package org.anaalvarezdev.algorithms.trees;

import org.openjdk.jmh.annotations.*;
import java.util.PriorityQueue;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do manual and JDK priority operations compare, and how does bottom-up
 * manual construction compare with repeated offer on random/descending inputs?
 *
 * Theoretical Expectation:
 * Peek is O(1); non-growing poll/offer pair O(log(n+1)). Bottom-up build is Theta(n).
 * Repeated offers have O(n log(n+1)) worst time, attained by descending distinct
 * keys in a min-heap, while other distributions can require less sift work.
 *
 * Measured Work:
 * Trial setup builds both representations and preboxes JDK values. Peek/pairs
 * use reserved capacity, exclude initial construction and preserve size/multiset.
 * Poll/offer repeatedly restores the current minimum, not random priority churn;
 * physical arrangement may change. Manual OptionalInt and primitive arrays differ
 * from JDK boxed storage. Returning/unboxing and OptionalInt access are timed.
 * Build methods include new heap/array allocation and population from the same
 * trial int[]; one copies/heapifies, the other reserves n and offers each value.
 * No input copy baseline is subtracted. No growth is timed by these methods.
 *
 * Result:
 * Pending author measurements. Smoke scores are not research evidence.
 *
 * Interpretation:
 * Pending. Preserve source/runtime/settings, distributions, means and errors.
 * Pair costs are not isolated poll latency; build costs are not operation-only
 * sift timings. No memory-footprint, allocation-rate, cache-cause, generalised
 * primitive/JDK ranking or complexity proof follows from these means alone.
 * Protocol: docs/09-trees/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(2)
public class HeapOperationsBenchmark {
    @Param({"100", "1000", "10000"}) private int size;
    @Param({"RANDOM", "REVERSE_SORTED"}) private String distribution;
    private int[] input;
    private IntMinHeap manual;
    private PriorityQueue<Integer> javaQueue;

    @Setup(Level.Trial) public void setup() {
        input = new int[size]; var random = new SplittableRandom(20261019);
        manual = new IntMinHeap(size); javaQueue = new PriorityQueue<>(size);
        for (int index = 0; index < size; index++) {
            int value = distribution.equals("REVERSE_SORTED") ? size - index : random.nextInt();
            input[index] = value; manual.offer(value); javaQueue.offer(value);
        }
    }
    @Benchmark public int manualPeek() { return manual.peek().orElseThrow(); }
    @Benchmark public int javaPeek() { return javaQueue.peek(); }
    @Benchmark public int manualPollRestore() {
        int value = manual.poll().orElseThrow(); manual.offer(value); return value;
    }
    @Benchmark public int javaPollRestore() {
        Integer value = javaQueue.poll(); javaQueue.offer(value); return value;
    }
    @Benchmark public IntMinHeap bottomUpBuild() { return new IntMinHeap(input); }
    @Benchmark public IntMinHeap repeatedOfferBuild() {
        IntMinHeap heap = new IntMinHeap(size);
        for (int value : input) { heap.offer(value); }
        return heap;
    }
}
