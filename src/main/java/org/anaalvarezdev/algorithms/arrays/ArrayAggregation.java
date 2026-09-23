package org.anaalvarezdev.algorithms.arrays;

/**
 * Problem:
 * Given an integer array, compute the sum of all its elements.
 *
 * Input:
 * - An integer array.
 *
 * Output:
 * - The sum of all elements stored in the array.
 *
 * Precondition:
 * - array != null
 * - The mathematical sum fits within the Java int range.
 *
 * Postcondition:
 * - The returned value is equal to the sum of all array elements.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Sequential traversal with an accumulator.
 *
 * Time Complexity:
 * - Θ(n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Optional.
 * - The algorithm performs a complete linear traversal.
 * - Benchmarking may be used to observe how execution time
 *   changes as the input size increases.
 */

public class ArrayAggregation {
    public ArrayAggregation() {
    }

    /**
     * Computes the sum of all elements in the given array.
     *
     * @param array the integer array to aggregate
     * @return the sum of all array elements
     */
    public static int sum(int[] array) {

        int sum = 0;

        for (int index = 0; index < array.length; index++) {
            sum = sum + array[index];
        }

        return sum;
    }
}
