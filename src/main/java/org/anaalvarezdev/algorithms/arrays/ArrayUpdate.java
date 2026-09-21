package org.anaalvarezdev.algorithms.arrays;
/**
 * Problem:
 * Given an integer array, a valid index, and a new value,
 * replace the element stored at that position with the specified value.
 *
 * Input:
 * - An integer array.
 * - A valid index.
 * - A new integer value.
 *
 * Output:
 * - No value is returned.
 * - The input array is modified in place.
 *
 * Precondition:
 * - array != null
 * - 0 <= index < array.length
 *
 * Postcondition:
 * - array[index] is equal to the specified value.
 * - All elements at positions different from index remain unchanged.
 *
 * Algorithmic Strategy:
 * Direct indexed update.
 *
 * Time Complexity:
 * - Θ(1)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Not required.
 * - Direct indexed update is a constant-time primitive operation,
 *   so empirical benchmarking would provide little algorithmic insight.
    */

public class ArrayUpdate {

    public ArrayUpdate() {

    }

    /**
     *This method updates an array by replacing the element at the specified index with a new value.
     *
     * @param array The integer array to update.
     * @param index The valid index of the element to replace.
     * @param value The new integer value to store at the specified index.
     *
     */
    public static void update(int[] array,int index ,int value){

        array[index] = value;
    }
}
