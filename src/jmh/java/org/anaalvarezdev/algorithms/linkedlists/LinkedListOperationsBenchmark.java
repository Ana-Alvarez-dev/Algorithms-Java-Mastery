package org.anaalvarezdev.algorithms.linkedlists;

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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/*
 * Research Questions:
 * How do three manual topologies compare with ArrayList and java.util.LinkedList
 * for sequential traversal, fixed middle access and a prepend/remove pair?
 *
 * Theoretical Expectation:
 * Traversal: Θ(n) for all representations; sums use long to avoid int overflow.
 * Middle get: Θ(1) for ArrayList, Θ(n) for these linked representations.
 * Prepend/remove pair: Θ(n) ArrayList shifts; Θ(1) endpoint changes for the lists.
 *
 * Measured Operations:
 * Traversal returns a checksum, access returns a value, mutations return the
 * removed value. Every mutation pair restores its original contents and size.
 * Node allocation in addFirst is timed. Trial construction and boxing are not.
 * ArrayList is pre-sized to n + 1, so the pair measures shifting without resizing.
 *
 * Result:
 * First measured subset supplied on 2026-10-19: 30 console rows, sizes 100/1000,
 * average time in ns/op, Cnt = 10 each. Follow-up headers confirm JMH 1.37,
 * JDK 21.0.9, no VM options, compiler blackholes, one thread, 3 x 500 ms warmup,
 * and 2 forks x 5 measurements of 500 ms for five configurations. Their
 * 50 measurement and 30 warmup averages are retained in Markdown. The exact
 * execution commit remains pending; remaining iteration values are unavailable.
 * At n = 1000, reported means +/- errors in ns/op:
 * Representation      Middle get           Prepend/remove      Traverse
 * ArrayList           1.668 +/- 0.744       104.285 +/- 1.096   971.031 +/- 382.618
 * Circular            671.151 +/- 114.886   20.840 +/- 3.835    2488.897 +/- 513.506
 * Doubly              1368.056 +/- 2343.650 16.247 +/- 3.378    4063.288 +/- 1464.355
 * JDK LinkedList      694.916 +/- 77.854    17.384 +/- 3.274    2421.615 +/- 467.094
 * Singly              677.253 +/- 89.571    11.242 +/- 7.069    3644.669 +/- 566.782
 * All 30 means/errors and supplied detailed statistics are recorded in Markdown:
 * docs/17-benchmarking/results/linked-lists/README.md. Smoke scores are excluded.
 *
 * Interpretation:
 * ArrayList has the lowest middle-access and traversal means at both sizes;
 * linked structures have lower prepend/remove pair means in this subset.
 * This is consistent with indexing, link traversal and head-shifting costs.
 * Doubly middle get at 1000 and JDK LinkedList middle get at 100 have errors
 * larger than their means. Do not assert precise rankings among topologies.
 * Two sizes do not establish asymptotic growth or isolate cache/JIT/GC causes.
 * Iterator allocation/checks and dispatch belong to the traversal workloads;
 * the mutation score measures a restored pair, not one insertion or removal.
 * Partial iteration evidence locates variability in five ArrayList cases;
 * it does not explain the two highest-error linked-access results.
 *
 * Limitations:
 * Fixed values and middle target, warm repeated structures, one thread. Runtime,
 * GC, allocation, JIT and cache effects affect timing. This experiment does not
 * establish memory footprints, thread safety or asymptotic complexity.
 * Protocol: docs/06-linked-lists/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(2)
public class LinkedListOperationsBenchmark {

    @Param({"100", "1000", "10000", "100000"})
    private int size;

    private SinglyLinkedList<Integer> singly;
    private DoublyLinkedList<Integer> doubly;
    private CircularLinkedList<Integer> circular;
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> javaLinkedList;

    @Setup(Level.Trial)
    public void setup() {
        singly = new SinglyLinkedList<>();
        doubly = new DoublyLinkedList<>();
        circular = new CircularLinkedList<>();
        arrayList = new ArrayList<>(size + 1);
        javaLinkedList = new LinkedList<>();
        for (int value = 0; value < size; value++) {
            Integer boxed = value;
            singly.addLast(boxed);
            doubly.addLast(boxed);
            circular.addLast(boxed);
            arrayList.add(boxed);
            javaLinkedList.addLast(boxed);
        }
    }

    @Benchmark
    public long singlyTraverse() {
        long sum = 0;
        for (int value : singly) {
            sum += value;
        }
        return sum;
    }

    @Benchmark
    public long doublyTraverse() {
        long sum = 0;
        for (int value : doubly) {
            sum += value;
        }
        return sum;
    }

    @Benchmark
    public long circularTraverse() {
        long sum = 0;
        for (int value : circular) {
            sum += value;
        }
        return sum;
    }

    @Benchmark
    public long arrayListTraverse() {
        long sum = 0;
        for (int value : arrayList) {
            sum += value;
        }
        return sum;
    }

    @Benchmark
    public long javaLinkedListTraverse() {
        long sum = 0;
        for (int value : javaLinkedList) {
            sum += value;
        }
        return sum;
    }

    @Benchmark
    public Integer singlyMiddleGet() {
        return singly.get(size / 2);
    }

    @Benchmark
    public Integer doublyMiddleGet() {
        return doubly.get(size / 2);
    }

    @Benchmark
    public Integer circularMiddleGet() {
        return circular.get(size / 2);
    }

    @Benchmark
    public Integer arrayListMiddleGet() {
        return arrayList.get(size / 2);
    }

    @Benchmark
    public Integer javaLinkedListMiddleGet() {
        return javaLinkedList.get(size / 2);
    }

    @Benchmark
    public Integer singlyPrependRemove() {
        singly.addFirst(-1);
        return singly.removeFirst();
    }

    @Benchmark
    public Integer doublyPrependRemove() {
        doubly.addFirst(-1);
        return doubly.removeFirst();
    }

    @Benchmark
    public Integer circularPrependRemove() {
        circular.addFirst(-1);
        return circular.removeFirst();
    }

    @Benchmark
    public Integer arrayListPrependRemove() {
        arrayList.addFirst(-1);
        return arrayList.removeFirst();
    }

    @Benchmark
    public Integer javaLinkedListPrependRemove() {
        javaLinkedList.addFirst(-1);
        return javaLinkedList.removeFirst();
    }
}
