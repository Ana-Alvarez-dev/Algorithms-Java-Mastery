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
 * Author run reported 2026-10-19: source ce621c63fabc2fe04c180d71fd1f9e165cc8a447
 * supplied before launch; JMH 1.37, Oracle JDK 21.0.9, one thread, two forks,
 * 3 x 500 ms warmup and 5 x 500 ms measurement (Cnt=10), 3 min 36 sec complete.
 * All 24 cells/errors, 240 measured and 144 warmup averages are preserved in
 * docs/17-benchmarking/results/trees/heap-first-run.md.
 * Descending n=1000: bottom-up 2354.977 +/- 304.174 versus repeated offer
 * 13996.646 +/- 2803.147 ns/op. RANDOM n=100 JDK peek 5.012 +/- 11.922 ns/op
 * is highly uncertain; its supplied CI lower marker is literal "? 0".
 *
 * Interpretation:
 * Descending insertion moves every ancestor for each new minimum: sum floor
 * log2(i) parent moves is 480/7987 for n=100/1000; bottom-up has linear build
 * work including copy. These are source arguments, not timing-exponent proofs.
 * Five errors reach 25% of means; largest 237.9%. RANDOM small build intervals
 * overlap. No outliers removed, formal significance or universal ranking claimed.
 * Pair/build/peek answer different questions; primitive/boxed mechanics differ.
 * No growth, isolated poll, allocation-rate, memory or cache-cause claim follows.
 * This run and the lookup report complete separate initial subsets, 48 of 72
 * default module cells; size 10000 remains unmeasured.
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
