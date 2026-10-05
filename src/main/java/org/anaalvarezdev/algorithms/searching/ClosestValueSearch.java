package org.anaalvarezdev.algorithms.searching;

import java.util.Objects;

/**
 * Problem:
 * Given a sorted integer array and a target value,
 * return the index of the array value closest to the target.
 *
 * Input:
 * - A sorted integer array.
 * - An integer target value.
 *
 * Output:
 * - The index of the closest value.
 * - -1 if the array is empty.
 *
 * Preconditions:
 * - array != null
 * - The array is sorted in nondecreasing order.
 *
 * Postconditions:
 * - If the array is non-empty, the returned index is valid.
 * - The absolute distance between array[result] and target is minimal.
 * - If two candidates are equally close, the lower index is returned.
 * - If the array is empty, the returned value is -1.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Binary search followed by comparison of the two boundary candidates
 * that surround the target.
 *
 * Correctness:
 * - If the target is found exactly, its distance is zero and is therefore
 *   minimal.
 * - Otherwise, after binary search terminates, high identifies the greatest
 *   value smaller than the target and low identifies the smallest value
 *   greater than the target, when those positions exist.
 * - Any other array element is at least as far from the target as one of
 *   those two boundary candidates.
 * - Comparing those candidates therefore yields a closest value.
 *
 * Termination:
 * - Every unsuccessful binary-search iteration strictly reduces the active
 *   interval.
 * - The loop terminates when an exact match is found or low > high.
 *
 * Time Complexity:
 * - Best case: Θ(1)
 * - Worst case: Θ(log n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Not required.
 * - The method reuses logarithmic binary-search reduction and does not yet
 *   introduce a separate empirical question.
 */
public final class ClosestValueSearch {

    private ClosestValueSearch() {
    }

    /**
     * Returns the index whose value is closest to the target.
     *
     * If two values are equally close, the lower index is returned.
     *
     * @param array the sorted integer array to search
     * @param target the value to approximate
     * @return the index of the closest value,
     *         or -1 if the array is empty
     * @throws NullPointerException if the array is null
     */
    public static int search(int[] array, int target) {
        Objects.requireNonNull(array, "array must not be null");

        if (array.length == 0) {
            return -1;
        }

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            int value = array[middle];

            if (value == target) {
                return middle;
            }

            if (value < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        if (high < 0) {
            return low;
        }

        if (low >= array.length) {
            return high;
        }

        long lowerDistance = Math.abs((long) target - array[high]);
        long upperDistance = Math.abs((long) array[low] - target);

        return lowerDistance <= upperDistance ? high : low;
    }
}
