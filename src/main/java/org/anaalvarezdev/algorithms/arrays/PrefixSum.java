package org.anaalvarezdev.algorithms.arrays;

/**
 * Problem:
 * Given an integer array, preprocess its elements into a prefix-sum
 * structure that allows range-sum queries to be answered in
 * constant time.
 *
 * Input:
 * - An integer array.
 *
 * Output:
 * - A prefix-sum array of length array.length + 1.
 * - prefix[0] == 0.
 * - prefix[i + 1] contains the sum of array[0..i].
 *
 * Precondition:
 * - array != null
 *
 * Postcondition:
 * - prefix.length == array.length + 1
 * - prefix[0] == 0
 * - For every valid index i:
 *
 *   prefix[i + 1] == prefix[i] + array[i]
 *
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Sequential preprocessing with accumulated state.
 *
 * Range Query:
 * For an inclusive range [left, right]:
 *
 * rangeSum = prefix[right + 1] - prefix[left]
 *
 * Time Complexity:
 * - Prefix construction: Θ(n)
 * - Range query: Θ(1)
 *
 * Space Complexity:
 * - Θ(n)
 * - An auxiliary prefix array of size n + 1 is created.
 *
 * Benchmarking:
 * - Required.
 * - Benchmarking compares direct range summation with
 *   prefix-sum range queries.
 */
public class PrefixSum {
    private PrefixSum() {
    }

    /**
     * Builds the prefix-sum representation of the given array.
     *
     * @param array the integer array to preprocess
     * @return a prefix-sum array of length array.length + 1
     */
    public static long[] build(int[] array) {

        long[] prefix = new long[array.length + 1];

        for (int index = 0; index < array.length; index++) {
            prefix[index + 1] =
                    prefix[index] + array[index];
        }

        return prefix;
    }

    /**
     * Returns the sum of the inclusive range [left, right].
     *
     * @param prefix the prefix-sum array produced by build
     * @param left the inclusive left boundary
     * @param right the inclusive right boundary
     * @return the sum of the requested range
     */
    public static long rangeSum(
            long[] prefix,
            int left,
            int right
    ) {

        if (prefix == null) {
            throw new NullPointerException(
                    "Prefix array must not be null"
            );
        }

        int originalLength = prefix.length - 1;

        if (left < 0
                || right < left
                || right >= originalLength) {

            throw new IndexOutOfBoundsException(
                    "Invalid range"
            );
        }

        return prefix[right + 1] - prefix[left];
    }
}
