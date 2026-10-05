package org.anaalvarezdev.algorithms.searching;

import java.util.Objects;

/**
 * Problem:
 * Given a sorted integer array and a target value,
 * determine whether the target exists and return a matching index.
 *
 * Input:
 * - A sorted integer array.
 * - An integer target value.
 *
 * Output:
 * - An index at which the target occurs.
 * - -1 if the target is not present.
 *
 * Preconditions:
 * - array != null
 * - The array is sorted in nondecreasing order.
 *
 * Postconditions:
 * - If the returned index is >= 0, array[index] == target.
 * - If the returned value is -1, the target does not occur in the array.
 * - If duplicate target values exist, any valid matching index may be returned.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Iterative binary search.
 *
 * The algorithm compares the target with the middle element of the active
 * search interval and discards the half that cannot contain the target.
 *
 * Correctness:
 * - Loop invariant:
 *   If the target exists in the array, it remains within the active
 *   search interval [low, high].
 * - Every interval update preserves all valid target positions.
 *
 * Termination:
 * - Each unsuccessful iteration strictly reduces the active search interval.
 * - The algorithm terminates when the target is found or low > high.
 *
 * Time Complexity:
 * - Best case: Θ(1)
 * - Average case: Θ(log n) under an explicitly defined search model
 * - Worst case: Θ(log n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Required.
 * - The benchmark observes the effect of input size on binary-search execution
 *   time and provides empirical evidence consistent with logarithmic growth.
 */
public final class BinarySearch {

    private BinarySearch() {
    }

    /**
     * Searches for the target value in the given sorted array
     * using iterative binary search.
     *
     * The array must be sorted in nondecreasing order.
     *
     * @param array the sorted integer array to search
     * @param target the value to locate
     * @return an index containing the target,
     *         or -1 if the target is not present
     * @throws NullPointerException if the array is null
     */
    public static int search(int[] array, int target) {
        Objects.requireNonNull(array, "array must not be null");

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            int value = array[middle];

            if (value == target) {
                return middle;
            }

            if (target < value) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

        return -1;
    }
}
