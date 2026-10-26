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
 * Pending author measurements; smoke values are not empirical conclusions.
 * Interpretation:
 * Pending source/runtime/settings and every error. Cost-only workflows have the
 * same optimum, not a full encoder's cost. No isolated sort/heap/boxing cost,
 * allocation rate, cache cause, significance or two-size exponent is inferred.
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
