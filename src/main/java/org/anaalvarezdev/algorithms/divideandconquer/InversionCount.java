package org.anaalvarezdev.algorithms.divideandconquer;

import java.util.Objects;

/**
 * Counts pairs {@code i < j} with {@code values[i] > values[j]}, without modifying the input.
 * Equal values are not inversions. Empty/singleton inputs give zero; null fails.
 * The result is long: n(n-1)/2 fits long for every possible int-array length.
 */
public final class InversionCount {
    private InversionCount() { }

    /** Theta(n log n) work for n >= 2, O(n) arrays and O(log(n+1)) logical depth. */
    public static long mergeBased(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        if (values.length < 2) {
            return 0;
        }
        return count(values.clone(), new int[values.length], 0, values.length);
    }

    /** Independent direct-pair baseline: Theta(n^2) work for n >= 2, O(1) state. */
    public static long quadratic(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        long inversions = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] > values[j]) {
                    inversions++;
                }
            }
        }
        return inversions;
    }

    private static long count(int[] values, int[] auxiliary, int from, int to) {
        if (to - from < 2) {
            return 0;
        }
        int middle = from + (to - from) / 2;
        long inversions = count(values, auxiliary, from, middle) + count(values, auxiliary, middle, to);
        System.arraycopy(values, from, auxiliary, from, to - from);
        int left = from;
        int right = middle;
        for (int destination = from; destination < to; destination++) {
            if (left == middle) {
                values[destination] = auxiliary[right++];
            } else if (right == to || auxiliary[left] <= auxiliary[right]) {
                values[destination] = auxiliary[left++];
            } else {
                values[destination] = auxiliary[right++];
                // Every remaining left item strictly exceeds this right item.
                inversions += middle - left;
            }
        }
        return inversions;
    }
}
