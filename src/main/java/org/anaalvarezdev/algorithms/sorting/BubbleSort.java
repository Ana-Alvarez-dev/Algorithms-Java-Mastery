package org.anaalvarezdev.algorithms.sorting;

import java.util.Objects;

/**
 * Sorts an integer array using adjacent exchanges and an early-exit check.
 *
 * <p>Contract: non-null input; ascending order in the same array; length and
 * element multiplicities preserved. Empty and singleton arrays are accepted.
 * Strict greater-than exchanges preserve equal-element order.
 *
 * <p>Invariant: after each pass, the greatest remaining element reaches the end
 * of the active prefix; the completed suffix is in its final order. A pass with
 * no exchange establishes that the remaining prefix is sorted. Swaps preserve
 * the permutation, and the active end decreases until termination.
 *
 * <p>Time: Θ(n) best case; Θ(n²) worst case and expected for a uniformly random
 * permutation of distinct keys. Space: Θ(1).
 */
public final class BubbleSort {

    private BubbleSort() {
    }

    /**
     * Sorts {@code values} in ascending order, modifying the supplied array.
     *
     * @param values the array to sort
     * @throws NullPointerException if values is null
     */
    public static void sort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");

        for (int end = values.length - 1; end > 0; end--) {
            boolean swapped = false;
            for (int index = 0; index < end; index++) {
                if (values[index] > values[index + 1]) {
                    int temporary = values[index];
                    values[index] = values[index + 1];
                    values[index + 1] = temporary;
                    swapped = true;
                }
            }
            if (!swapped) {
                return;
            }
        }
    }
}
