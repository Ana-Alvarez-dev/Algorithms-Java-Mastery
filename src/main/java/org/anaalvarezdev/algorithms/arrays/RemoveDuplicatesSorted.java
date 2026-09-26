package org.anaalvarezdev.algorithms.arrays;
/**
 * Problem:
 * Given a sorted integer array, remove duplicate values in place
 * so that each distinct value appears exactly once in the logical
 * portion of the array.
 *
 * Input:
 * - A sorted integer array in non-decreasing order.
 *
 * Output:
 * - The new logical size containing only unique values.
 * - The input array is modified in place.
 *
 * Precondition:
 * - array != null
 * - The array is sorted in non-decreasing order.
 *
 * Postcondition:
 * - The first returnedSize positions contain all distinct values
 *   from the original array.
 * - Each distinct value appears exactly once in the logical range.
 * - The relative sorted order is preserved.
 * - Elements beyond the returned logical size are unspecified.
 *
 * Algorithmic Strategy:
 * Two-pointer read/write traversal.
 *
 * The read pointer scans every element.
 * The write pointer identifies the position where the next
 * unique value must be stored.
 *
 * Time Complexity:
 * - Θ(n)
 * - Every element is inspected once.
 *
 * Space Complexity:
 * - Θ(1)
 * - The transformation is performed in place.
 *
 * Benchmarking:
 * - Required.
 * - Benchmarking is used to observe linear growth and how
 *   different duplicate distributions affect practical execution.
 */
public class RemoveDuplicatesSorted {

    private RemoveDuplicatesSorted() {
    }

    /**
     * Removes duplicate values from a sorted array in place.
     *
     * @param array the sorted integer array
     * @return the logical number of unique elements
     */
    public static int remove(int[] array) {

        if (array.length == 0) {
            return 0;
        }

        int write = 1;

        for (int read = 1; read < array.length; read++) {

            if (array[read] != array[write - 1]) {
                array[write] = array[read];
                write++;
            }
        }

        return write;
    }

}
