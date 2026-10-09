package org.anaalvarezdev.algorithms.sorting;

import java.util.Objects;

/**
 * Top-down merge sort with one reusable auxiliary array per public call.
 *
 * <p>Contract: non-null input; ascending order in the supplied array; length and
 * element multiplicities preserved. Empty and singleton arrays are accepted.
 * The algorithm writes back into the input, but is not a constant-space sort.
 * Choosing the left value on equality preserves equal-element order.
 *
 * <p>Correctness: singleton intervals are sorted. Recursively ordered halves
 * are merged by repeatedly selecting the smallest remaining head. The written
 * prefix is ordered and contains exactly the consumed values. Each merge
 * exhausts finite halves, and each recursive interval is strictly smaller.
 *
 * <p>Time: Θ(n log n) best and worst case for n >= 2; no ordered-halves shortcut
 * is used. Space: Θ(n) auxiliary array plus O(log n) recursive stack.
 */
public final class MergeSort {

    private MergeSort() {
    }

    /**
     * Sorts {@code values} in ascending order, modifying the supplied array.
     *
     * @param values the array to sort
     * @throws NullPointerException if values is null
     */
    public static void sort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        if (values.length < 2) {
            return;
        }
        sort(values, new int[values.length], 0, values.length - 1);
    }

    private static void sort(int[] values, int[] auxiliary, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + (right - left) / 2;
        sort(values, auxiliary, left, middle);
        sort(values, auxiliary, middle + 1, right);
        merge(values, auxiliary, left, middle, right);
    }

    private static void merge(int[] values, int[] auxiliary,
                              int left, int middle, int right) {
        System.arraycopy(values, left, auxiliary, left, right - left + 1);
        int first = left;
        int second = middle + 1;

        for (int destination = left; destination <= right; destination++) {
            if (first > middle) {
                values[destination] = auxiliary[second++];
            } else if (second > right || auxiliary[first] <= auxiliary[second]) {
                values[destination] = auxiliary[first++];
            } else {
                values[destination] = auxiliary[second++];
            }
        }
    }
}
