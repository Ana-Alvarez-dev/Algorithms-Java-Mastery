package org.anaalvarezdev.algorithms.sorting;

import java.util.Objects;

/**
 * Sorts an integer array by inserting each key into the ordered prefix.
 *
 * <p>Contract: non-null input; ascending order in the same array; length and
 * element multiplicities preserved. Empty and singleton arrays are accepted.
 * Only strictly greater values are shifted, preserving equal-element order.
 *
 * <p>Invariant: before index i is processed, the prefix [0, i) is an ordered
 * permutation of its original elements. Saving the key, shifting greater
 * elements, and filling the resulting gap establishes the invariant for i + 1.
 * The outer index advances and the inner index decreases within finite bounds.
 *
 * <p>Time: Θ(n) best case; Θ(n²) worst case and expected for a uniformly random
 * permutation of distinct keys. Space: Θ(1).
 */
public final class InsertionSort {

    private InsertionSort() {
    }

    /**
     * Sorts {@code values} in ascending order, modifying the supplied array.
     *
     * @param values the array to sort
     * @throws NullPointerException if values is null
     */
    public static void sort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
    }
}
