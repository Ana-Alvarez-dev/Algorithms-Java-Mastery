package org.anaalvarezdev.algorithms.arrays;
/**
 * Problem:
 * Given a non-empty integer array, find both the minimum
 * and maximum values using an optimized pairwise strategy.
 *
 * Input:
 * - A non-empty integer array.
 *
 * Output:
 * - The minimum value contained in the array.
 * - The maximum value contained in the array.
 *
 * Precondition:
 * - array != null
 * - array.length > 0
 *
 * Postcondition:
 * - The returned minimum belongs to the input array.
 * - The returned maximum belongs to the input array.
 * - minimum <= array[i] for every valid index i.
 * - maximum >= array[i] for every valid index i.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Pairwise comparison.
 *
 * Elements are processed in pairs:
 * - Compare both elements with each other.
 * - Compare the smaller element with the current minimum.
 * - Compare the larger element with the current maximum.
 *
 * Time Complexity:
 * - Θ(n)
 *
 * Comparison Complexity:
 * - Odd n:  3(n - 1) / 2 comparisons.
 * - Even n: 3n / 2 - 2 comparisons.
 * - This improves over the 2n - 2 comparisons required
 *   when minimum and maximum are found independently.
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Required.
 * - Benchmarking compares the pairwise strategy against
 *   two independent linear passes.
 */
public class FindMinimumAndMaximum {
    public FindMinimumAndMaximum() {
    }
    /**
     * Represents the minimum and maximum values found.
     *
     * @param minimum the minimum value
     * @param maximum the maximum value
     */
    public record MinMax(int minimum, int maximum) {
    }

    /**
     * Finds both the minimum and maximum values using
     * pairwise comparisons.
     *
     * @param array the non-empty integer array to inspect
     * @return a MinMax result containing the minimum and maximum values
     */
    public static MinMax find(int[] array) {

        int minimum;
        int maximum;
        int index;

        if (array.length % 2 == 0) {

            if (array[0] < array[1]) {
                minimum = array[0];
                maximum = array[1];
            } else {
                minimum = array[1];
                maximum = array[0];
            }

            index = 2;

        } else {

            minimum = array[0];
            maximum = array[0];
            index = 1;
        }

        while (index < array.length - 1) {

            int first = array[index];
            int second = array[index + 1];

            if (first < second) {

                if (first < minimum) {
                    minimum = first;
                }

                if (second > maximum) {
                    maximum = second;
                }

            } else {

                if (second < minimum) {
                    minimum = second;
                }

                if (first > maximum) {
                    maximum = first;
                }
            }

            index += 2;
        }

        return new MinMax(minimum, maximum);
    }
}
