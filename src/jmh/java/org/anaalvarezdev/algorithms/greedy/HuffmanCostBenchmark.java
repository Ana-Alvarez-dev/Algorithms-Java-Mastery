package org.anaalvarezdev.algorithms.greedy;

import org.openjdk.jmh.annotations.*;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How does exact optimal Huffman COST computation compare using heap merges
 * versus sorting leaves then merging through two monotone queues?
 * Theoretical Expectation:
 * Both have O(n log n) worst comparison preprocessing/heap work; two-queue
 * merging is linear after sorting. Both do n-1 merges and BigInteger cost sums,
 * use O(n) state and preserve input. Codewords/bitstreams are not produced.
 * Measured Work:
 * Full validation, heap boxing or private clone/sort/arrays, merging and exact
 * result. Trial setup generates positive frequencies with seed 20261026 and
 * is excluded. SKEWED has one MAX_INT frequency plus ones; no overflow in long
 * subtree weights, and weighted cost uses BigInteger.
 * Result:
 * Author run reported 2026-10-26; source 674ab35b7d82f1a9ab7fbab63fa1a9e80253965c
 * supplied separately. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/
 * measurement, two forks (Cnt=10), combined run 5 min 51 sec. All 40 cells/errors,
 * 400 measured and 240 warmup averages: docs/17-benchmarking/results/greedy/README.md.
 * All 16 cost cells retained. RANDOM n=1000 heap/two-queue:
 * 149.045 +/- 1.551 / 32.205 +/- 1.556 us/op; SKEWED:
 * 68.620 +/- 0.846 / 14.491 +/- 0.312 us/op.
 * Interpretation:
 * Two-queue means are lower in this subset; UNIFORM/100 intervals overlap.
 * Three UNIFORM errors reach 25% (35.3%/43.4% heap, 44.7% two-queue at 100).
 * Both do n-1 merges/exact cost additions; heap offers total 2n-1, while the
 * other method includes clone/sort/arrays and monotone cursors. Source counts
 * are not profiler attribution or measured allocation. Cost-only results do
 * not time codewords/compression. No isolated heap/boxing cost or universal ratio.
 * Protocol: docs/13-greedy-algorithms/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class HuffmanCostBenchmark {
    @Param({"100","1000"}) public int size;
    @Param({"UNIFORM","RANDOM","SKEWED","TWO_LEVEL"}) public String distribution;
    private int[] frequencies;
    @Setup(Level.Trial) public void setup() { frequencies = GreedyInputs.frequencies(size,distribution); }
    @Benchmark public BigInteger heapCost() { return HuffmanCoding.costWithHeap(frequencies); }
    @Benchmark public BigInteger twoQueueCost() { return HuffmanCoding.costWithTwoQueues(frequencies); }
}
