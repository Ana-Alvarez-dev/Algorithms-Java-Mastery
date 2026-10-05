package org.anaalvarezdev.algorithms.searching;

import java.util.Objects;

/**
 * Problem:
 * Given a sorted integer array and a target value,
 * return the index of the first occurrence of the target.
 *
 * Input:
 * - A sorted integer array.
 * - An integer target value.
 *
 * Output:
 * - The index of the first occurrence of the target.
 * - -1 if the target is not present.
 *
 * Preconditions:
 * - array != null
 * - The array is sorted in nondecreasing order.
 *
 * Postconditions:
 * - If the returned index is >= 0, array[index] == target.
 * - If the returned index is >= 0, no smaller valid index contains the target.
 * - If the returned value is -1, the target does not occur in the array.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Boundary binary search.
 *
 * Correctness:
 * - When a match is found, it is preserved as a valid candidate.
 * - The search continues only to the left, so any better candidate must
 *   have a smaller index.
 * - When the interval is exhausted, the preserved candidate is the first
 *   occurrence.
 *
 * Termination:
 * - Every iteration strictly reduces the active search interval.
 * - The loop terminates when low > high.
 *
 * Time Complexity:
 * - Θ(log n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Not required.
 * - This is a boundary-search variant of Binary Search and adds no separate
 *   empirical question beyond the existing binary-search benchmarks.
 */
public final class FirstOccurrence {

    private FirstOccurrence() {
    }

    /**
     * Returns the index of the first occurrence of the target in a sorted array.
     *
     * @param array the sorted integer array to search
     * @param target the value to locate
     * @return the first index containing the target,
     *         or -1 if the target is not present
     * @throws NullPointerException if the array is null
     */
    public static int search(int[] array, int target) {
        Objects.requireNonNull(array, "array must not be null");

        int low = 0;
        int high = array.length - 1;
        int result = -1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            int value = array[middle];

            if (value == target) {
                result = middle;
                high = middle - 1;
            } else if (target < value) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

        return result;
    }
}
