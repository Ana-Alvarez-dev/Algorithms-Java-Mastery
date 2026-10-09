package org.anaalvarezdev.algorithms.searching;

/**
 * Problem:
 * Given an integer array and a target value,
 * return the index of the first occurrence of the target.
 *
 * Input:
 * - An integer array, which does not need to be sorted.
 * - An integer target value.
 *
 * Output:
 * - The index of the first occurrence of the target.
 * - -1 if the target is not present.
 *
 * Preconditions:
 * - array != null
 *
 * Postconditions:
 * - If the returned index is >= 0, array[index] == target.
 * - No earlier index contains the target.
 * - If the returned value is -1, the target does not occur in the array.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Sequential linear search.
 *
 * Correctness:
 * - Loop invariant: before each iteration, no index less than index
 *   contains the target.
 * - A match is therefore the first occurrence; exhausting the array
 *   establishes that the target is absent.
 *
 * Termination:
 * - Each unsuccessful iteration advances index toward array.length.
 *
 * Time Complexity:
 * - Best case: Θ(1)
 * - Worst case: Θ(n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Required.
 */
public final class LinearSearch {

    private LinearSearch() {
    }

    /**
     * Searches for the target value in the given array using linear search.
     *
     * If the target occurs more than once, this method returns the index
     * of its first occurrence.
     *
     * @param array the integer array to search
     * @param target the value to locate
     * @return the index of the first occurrence of the target,
     *         or -1 if the target is not present
     * @throws NullPointerException if the array is null
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
