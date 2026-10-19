package org.anaalvarezdev.algorithms.trees;

import java.util.Objects;

/** Ascending in-place max-heap sort: O(n log(n+1)) worst time, O(1) extra space, unstable. */
public final class HeapSort {
    private HeapSort() { }
    /** Rejects null before mutation; supports duplicates and every int value. */
    public static void sort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        for (int parent = values.length / 2 - 1; parent >= 0; parent--) { siftDown(values, parent, values.length); }
        for (int end = values.length - 1; end > 0; end--) {
            int maximum = values[0]; values[0] = values[end]; values[end] = maximum;
            siftDown(values, 0, end);
        }
    }
    private static void siftDown(int[] values, int slot, int end) {
        int value = values[slot];
        while (slot < end / 2) {
            int child = slot * 2 + 1;
            if (child + 1 < end && values[child + 1] > values[child]) { child++; }
            if (value >= values[child]) { break; }
            values[slot] = values[child]; slot = child;
        }
        values[slot] = value;
    }
}
