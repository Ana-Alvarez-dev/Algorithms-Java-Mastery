package org.anaalvarezdev.algorithms.arrays;
/**
 * Problem:
 * Given an integer array, create and return a new array
 * containing the same elements in the same order.
 *
 * Input:
 * - An integer array.
 *
 * Output:
 * - A new integer array containing the same elements
 *   as the input array.
 *
 * Precondition:
 * - array != null
 *
 * Postcondition:
 * - result != array
 * - result.length == array.length
 * - For every valid index i:
 *
 *   result[i] == array[i]
 *
 * - Modifying the returned array does not modify
 *   the original array.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Allocate a new array with the same length and copy
 * each element using sequential indexed traversal.
 *
 * Time Complexity:
 * - Θ(n)
 * - Every element is copied exactly once.
 *
 * Space Complexity:
 * - Θ(n)
 * - A new array of n elements is allocated.
 *
 * Benchmarking:
 * - Required.
 * - Benchmarking compares the manual copying strategy
 */

public class ArrayCopy {

    private ArrayCopy() {
    }

    /**
     * Creates an independent copy of the given array.
     *
     * @param array the integer array to copy
     * @return a new array containing the same elements
     */
    public static int[] copy(int[] array) {

        int[] copy = new int[array.length];

        for (int index = 0; index < array.length; index++) {
            copy[index] = array[index];
        }

        return copy;
    }

}
