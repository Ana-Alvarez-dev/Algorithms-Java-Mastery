package org.anaalvarezdev.algorithms.arrays;

public class AccessArrays {

    public AccessArrays() {

    }

    /**
     * Problem:
     * Given an integer array and a valid index,
     * return the element stored at that position.
     *
     * Input:
     * - An integer array.
     * - A valid index.
     *
     * Output:
     * - The value stored at the specified index.
     *
     * Precondition:
     * - 0 <= index < array.length
     *
     * Postcondition:
     * - The returned value is equal to array[index].
     *
     * Algorithmic Strategy:
     * Direct indexed access.
     *
     * Time Complexity:
     * - Θ(1)
     *
     * Space Complexity:
     * - Θ(1)
     *
     * Benchmarking:
     * - Not required
     * - Direct indexed access is a constant-time primitive operation,
     *   so empirical benchmarking would provide little algorithmic insight.
     */
    public static int get(int[] array, int index) {
        /**
         * This method accesses an array and returns the value stored at the specified index.
         *
         * @param array The integer array to access.
         * @param index The valid index of the element to retrieve.
         * @return The value stored at the specified index.
         */
            return array[index];
    }
}
