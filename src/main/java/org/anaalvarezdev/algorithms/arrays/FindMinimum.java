package org.anaalvarezdev.algorithms.arrays;
/**
 * Problem:
 * Given a non-empty integer array, find and return
 * the smallest element stored in the array.
 *
 * Input:
 * - A non-empty integer array.
 *
 * Output:
 * - The minimum value contained in the array.
 *
 * Precondition:
 * - array != null
 * - array.length > 0
 *
 * Postcondition:
 * - The returned value belongs to the input array.
 * - The returned value is less than or equal to every
 *   element in the array.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Sequential traversal while maintaining the smallest
 * value seen so far.
 *
 * Time Complexity:
 * - Θ(n)
 * - The algorithm performs exactly n - 1 comparisons.
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Optional.
 * - The algorithm performs a complete linear traversal.
 * - Benchmarking may be used to observe execution-time
 *   growth across increasing input sizes.
 */
public class FindMinimum {
    public FindMinimum() {
    }
    /**
     * Returns the minimum value contained in the given array.
     *
     * @param array the non-empty integer array to inspect
     * @return the minimum value contained in the array
     */
    public static int find(int[] array) {

        int minimum = array[0];

        for (int index = 1; index < array.length; index++) {
            if (array[index] < minimum) {
                minimum = array[index];
            }
        }

        return minimum;
    }



}
