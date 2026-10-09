package org.anaalvarezdev.algorithms.sorting;

import java.util.Objects;

/**
 * Sorts an integer array by repeatedly selecting the smallest remaining value.
 *
 * <p>Contract: non-null input; ascending order in the same array; length and
 * element multiplicities preserved. Empty and singleton arrays are accepted.
 * The exchange-based algorithm is unstable and uses constant auxiliary space.
 *
 * <p>Invariant: before position i is filled, the prefix contains the i smallest
 * original elements in order. Selecting the suffix minimum extends that prefix.
 * Swaps preserve the permutation, and both bounded indices advance to termination.
 *
 * <p>Time: Θ(n²) in all cases, with n(n - 1)/2 key comparisons and at most
 * n - 1 swaps for n >= 1. Space: Θ(1).
 *
 * <p>Academic analysis: docs/05-sorting/04-selection-sort.md.
 */
public final class SelectionSort {

    private SelectionSort() {
    }

    /**
     * Sorts {@code values} in ascending order, modifying the supplied array.
     *
     * @param values the array to sort
     * @throws NullPointerException if values is null
     */
    public static void sort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");

        for (int index = 0; index < values.length - 1; index++) {
            int minimumIndex = index;
            for (int scan = index + 1; scan < values.length; scan++) {
                if (values[scan] < values[minimumIndex]) {
                    minimumIndex = scan;
                }
            }
            if (minimumIndex != index) {
                int temporary = values[index];
                values[index] = values[minimumIndex];
                values[minimumIndex] = temporary;
            }
        }
    }
}
