package org.anaalvarezdev.algorithms.trees;

import java.util.Objects;

/** Selected heap application; not a bounded-memory top-k algorithm. */
public final class KSmallest {
    private KSmallest() { }
    /**
     * Returns k smallest values in ascending order with multiplicity, preserving
     * the non-null input. Requires {@code 0 <= k <= length}. For {@code k > 0}, Theta(n) heap build
     * plus O(k log(n+1)) extraction time and O(n+k) storage; k=0 returns immediately.
     */
    public static int[] select(int[] values, int k) {
        Objects.requireNonNull(values, "values must not be null");
        if (k < 0 || k > values.length) { throw new IllegalArgumentException("k outside [0, length]"); }
        int[] result = new int[k];
        if (k == 0) { return result; }
        IntMinHeap heap = new IntMinHeap(values);
        for (int index = 0; index < k; index++) { result[index] = heap.poll().orElseThrow(); }
        return result;
    }
}
