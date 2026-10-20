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
 * Author lookup run reported 2026-10-19: JMH 1.37, Oracle JDK 21.0.9, one
 * thread, 3 x 500 ms warmup, 5 x 500 ms measurement, two forks (Cnt=10).
 * All 24 cells, errors, 240 measured and 144 warmup averages are preserved in
 * docs/17-benchmarking/results/trees/README.md; completion 3 min 35 sec.
 * SORTED BST hit n=100/1000: 85.424 +/- 12.292 / 1304.430 +/- 7.248 ns/op;
 * SORTED BST miss: 170.056 +/- 15.668 / 3411.725 +/- 209.696 ns/op.
 * Author-supplied reflog identifies execution source:
 * c576cb79aa9747aced1cd041e97f64fc99e60010 (PR #76 merge), matching review.
 * It records checkout at 14:01:52 -0300 and a later pull to results merge
 * ce621c6 at 22:02:31 on 2026-10-19. JMH does not embed this source identity.
 * Older 3f1e5df lacks Trees; ce621c6 documents already supplied results.
 *
 * Interpretation:
 * SORTED BST is a right chain: source hit visits average (n+1)/2 over a full
 * cursor cycle (50.5/500.5), while fixed upper miss visits n (100/1000).
 * This explains path work, not exact nanosecond ratios (about 15.27/20.06).
 * AVL's logarithmic-height argument remains separate from these timing means.
 * All four SHUFFLED AVL errors exceed 25% of their means (largest 55.1%);
 * no precise ranking or formal significance claim follows. One shuffle/size
 * and a fixed upper miss do not sample all insertion/query distributions.
 * Heap timings are not supplied. Preserve every error/runtime/provenance limit;
 * no memory, allocation, cache-cause, update-cost or asymptotic-proof claim.
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
