package org.anaalvarezdev.algorithms.sorting;

import java.util.Objects;

/**
 * Quick sort using Hoare partitioning and the middle element's value as pivot.
 *
 * <p>Contract: non-null input; ascending order in the same array; length and
 * element multiplicities preserved. Empty and singleton arrays are accepted.
 * The exchange-based algorithm does not guarantee stability.
 *
 * <p>Partition invariant: values passed by the left scan are {@code <= pivot}
 * and those passed by the right scan are {@code >= pivot}. Exchanging misplaced values preserves
 * the permutation. Both scans advance, including when values equal the pivot.
 * The returned boundary splits [left, boundary] from [boundary + 1, right];
 * it is not a final pivot index. Sorting those smaller regions orders the whole.
 *
 * <p>Only the smaller partition is processed recursively; the larger is handled
 * by the loop. Recursive problem sizes at least halve, bounding stack usage by
 * O(log n) even when partitions are unbalanced. This does not prevent Θ(n²)
 * worst-case time. Balanced partitions take Θ(n log n); expected Θ(n log n)
 * assumes a uniformly random permutation of distinct keys. The pivot policy
 * is deterministic, not randomised. Partition variables use Θ(1) space.
 */
public final class QuickSort {

    private QuickSort() {
    }

    /**
     * Sorts {@code values} in ascending order, modifying the supplied array.
     *
     * @param values the array to sort
     * @throws NullPointerException if values is null
     */
    public static void sort(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        sort(values, 0, values.length - 1);
    }

    private static void sort(int[] values, int left, int right) {
        while (left < right) {
            int boundary = partition(values, left, right);
            int leftSize = boundary - left + 1;
            int rightSize = right - boundary;
            if (leftSize < rightSize) {
                sort(values, left, boundary);
                left = boundary + 1;
            } else {
                sort(values, boundary + 1, right);
                right = boundary;
            }
        }
    }

    private static int partition(int[] values, int left, int right) {
        int pivot = values[left + (right - left) / 2];
        int first = left - 1;
        int second = right + 1;

        while (true) {
            do {
                first++;
            } while (values[first] < pivot);
            do {
                second--;
            } while (values[second] > pivot);
            if (first >= second) {
                return second;
            }
            int temporary = values[first];
            values[first] = values[second];
            values[second] = temporary;
        }
    }
}
