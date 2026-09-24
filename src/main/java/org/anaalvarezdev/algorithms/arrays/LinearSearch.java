package org.anaalvarezdev.algorithms.arrays;
/**
 * Problem:
 * Given an integer array and a target value,
 * find the first index at which the target occurs.
 *
 * Input:
 * - An integer array.
 * - An integer target value.
 *
 * Output:
 * - The index of the first occurrence of the target.
 * - -1 if the target is not present in the array.
 *
 * Precondition:
 * - array != null
 *
 * Postcondition:
 * - If the returned index is >= 0, array[index] == target.
 * - The returned index corresponds to the first occurrence
 *   of the target.
 * - If the returned value is -1, the target does not occur
 *   in the array.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Sequential search with early termination.
 *
 * Time Complexity:
 * - Best case: Θ(1)
 * - Average case: Θ(n)
 * - Worst case: Θ(n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Required.
 * - Benchmarking is used to observe how target position
 *   affects execution time.
 * - Best, middle, last, and absent-target scenarios
 *   should be evaluated separately.
 */
public class LinearSearch {
    private LinearSearch() {
    }

    /**
     * Searches for the first occurrence of the target value.
     *
     * @param array the integer array to search
     * @param target the value to find
     * @return the index of the first occurrence of the target,
     *         or -1 if the target is not present
     */
    public static int search(int[] array, int target) {

        for (int index = 0; index < array.length; index++) {

            if (array[index] == target) {
                return index;
            }
        }

        return -1;
    }
}
