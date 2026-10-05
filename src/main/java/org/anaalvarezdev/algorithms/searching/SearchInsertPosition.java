package org.anaalvarezdev.algorithms.searching;

import java.util.Objects;

/**
 * Problem:
 * Given a sorted integer array and a target value,
 * determine the first index at which the target can be inserted
 * while preserving nondecreasing order.
 *
 * Input:
 * - A sorted integer array.
 * - An integer target value.
 *
 * Output:
 * - The first valid insertion index in the range [0, array.length].
 *
 * Preconditions:
 * - array != null
 * - The array is sorted in nondecreasing order.
 *
 * Postconditions:
 * - 0 <= result <= array.length.
 * - Every valid index smaller than result contains a value < target.
 * - If result < array.length, array[result] >= target.
 * - Inserting target at result preserves nondecreasing order.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Lower-bound binary search.
 *
 * Correctness:
 * - Every index smaller than low contains a value < target.
 * - high always identifies a valid candidate boundary.
 * - Each comparison preserves these boundary properties.
 * - When low == high, low is the first valid insertion position.
 *
 * Termination:
 * - Every iteration strictly decreases the size of [low, high).
 * - The loop terminates when low == high.
 *
 * Time Complexity:
 * - Θ(log n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Not required.
 * - This lower-bound variant does not introduce a distinct empirical question
 *   beyond the existing binary-search benchmarks.
 */
public final class SearchInsertPosition {

    private SearchInsertPosition() {
    }

    /**
     * Returns the first index at which the target can be inserted
     * while preserving nondecreasing order.
     *
     * If the target already exists, the method returns its first occurrence.
     *
     * @param array the sorted integer array to inspect
     * @param target the value whose insertion position is required
     * @return the first valid insertion index in the range
     *         from 0 through array.length
     * @throws NullPointerException if the array is null
     */
    public static int search(int[] array, int target) {
        Objects.requireNonNull(array, "array must not be null");

        int low = 0;
        int high = array.length;

        while (low < high) {
            int middle = low + (high - low) / 2;

            if (array[middle] < target) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }

        return low;
    }
}
