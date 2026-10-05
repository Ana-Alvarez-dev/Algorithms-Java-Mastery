package org.anaalvarezdev.algorithms.searching;

import java.util.Objects;

/**
 * Problem:
 * Given a sorted integer array and a target value,
 * determine whether the target exists and return a matching index
 * using recursive binary search.
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
 * Recursive binary search.
 *
 * The algorithm compares the target with the middle element of the active
 * interval and recursively searches only the half that can still contain
 * the target.
 *
 * Correctness:
 * - Recursive contract:
 *   For every call over [low, high], return a matching index if the target
 *   occurs in that interval; otherwise return -1.
 * - Sorted order allows one half of the interval to be discarded safely.
 * - The recursive call preserves every valid target position.
 *
 * Termination:
 * - Each recursive call receives a strictly smaller search interval.
 * - The recursion terminates when the target is found or low > high.
 *
 * Time Complexity:
 * - Best case: Θ(1)
 * - Average case: Θ(log n) under an explicitly defined search model
 * - Worst case: Θ(log n)
 *
 * Space Complexity:
 * - Θ(log n) auxiliary stack space in the worst case.
 *
 * Benchmarking:
 * - Required as a comparative experiment.
 * - The benchmark compares iterative and recursive binary search under
 *   equivalent inputs and scenarios.
 */
public final class RecursiveBinarySearch {

    private RecursiveBinarySearch() {
    }

    /**
     * Searches for the target value in the given sorted array
     * using recursive binary search.
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

        return search(array, target, 0, array.length - 1);
    }

    /**
     * Searches recursively within the inclusive interval [low, high].
     *
     * @param array the sorted integer array to search
     * @param target the value to locate
     * @param low the first index of the active search interval
     * @param high the last index of the active search interval
     * @return an index containing the target,
     *         or -1 if the target is not present in the interval
     */
    private static int search(
            int[] array,
            int target,
            int low,
            int high
    ) {
        if (low > high) {
            return -1;
        }

        int middle = low + (high - low) / 2;
        int value = array[middle];

        if (value == target) {
            return middle;
        }

        if (target < value) {
            return search(array, target, low, middle - 1);
        }

        return search(array, target, middle + 1, high);
    }
}
