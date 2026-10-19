package org.anaalvarezdev.algorithms.trees;

import org.openjdk.jmh.annotations.*;
import java.util.SplittableRandom;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How does insertion order affect successful/absent lookup in an unbalanced BST,
 * an AVL tree and JDK TreeSet, with the same int key set and query schedule?
 *
 * Theoretical Expectation:
 * BST lookup is O(h+1); sorted insertion creates h=n-1. AVL maintains logarithmic
 * height for either order. A seeded shuffled order is one sample, not a proof of
 * the expected random-BST bound. TreeSet is a standard-library reference.
 *
 * Measured Work:
 * Hit cycles 0..n-1, including cursor selection; miss repeats fixed absent n.
 * Equal integer values use preboxed query objects, unboxed by the primitive
 * manual API. Trial construction/boxing and all updates are excluded. Trees are
 * not rebuilt per invocation, so insertion-order effects are retained.
 * Primitive versus boxed APIs and library mechanics limit algorithm-only claims.
 *
 * Result:
 * Pending author measurements. Smoke execution only verifies the harness.
 *
 * Interpretation:
 * Pending. Retain actual source SHA, JDK/JMH/settings and every mean/error.
 * Compare matching size/order/query kind. Do not infer memory, allocation rates,
 * cache causes, update latency or asymptotic proofs from lookup means.
 * Protocol: docs/09-trees/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(2)
public class TreeOperationsBenchmark {
    @Param({"100", "1000", "10000"}) private int size;
    @Param({"SHUFFLED", "SORTED"}) private String insertionOrder;
    private IntBinarySearchTree bst;
    private IntAvlTree avl;
    private TreeSet<Integer> javaSet;
    private Integer[] queries;
    private Integer absent;
    private int cursor;

    @Setup(Level.Trial) public void setup() {
        int[] order = new int[size]; queries = new Integer[size];
        for (int index = 0; index < size; index++) { order[index] = index; queries[index] = index; }
        if (insertionOrder.equals("SHUFFLED")) {
            var random = new SplittableRandom(20261019);
            for (int index = size - 1; index > 0; index--) {
                int next = random.nextInt(index + 1); int temp = order[index]; order[index] = order[next]; order[next] = temp;
            }
        }
        bst = new IntBinarySearchTree(); avl = new IntAvlTree(); javaSet = new TreeSet<>();
        for (int value : order) { bst.add(value); avl.add(value); javaSet.add(queries[value]); }
        absent = size; cursor = 0;
    }
    private Integer nextQuery() {
        Integer value = queries[cursor]; cursor = cursor == size - 1 ? 0 : cursor + 1; return value;
    }
    @Benchmark public boolean bstHit() { return bst.contains(nextQuery()); }
    @Benchmark public boolean avlHit() { return avl.contains(nextQuery()); }
    @Benchmark public boolean javaSetHit() { return javaSet.contains(nextQuery()); }
    @Benchmark public boolean bstMiss() { return bst.contains(absent); }
    @Benchmark public boolean avlMiss() { return avl.contains(absent); }
    @Benchmark public boolean javaSetMiss() { return javaSet.contains(absent); }
}
