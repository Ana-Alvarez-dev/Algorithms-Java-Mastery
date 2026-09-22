package org.anaalvarezdev.algorithms.arrays;

import java.util.function.IntConsumer;

/**
 * Problem:
 * Given an integer array, visit every element exactly once
 * from the first position to the last position.
 *
 * Input:
 * - An integer array.
 * - An action to execute for each element.
 *
 * Output:
 * - No value is returned.
 * - The specified action is applied to every array element.
 *
 * Precondition:
 * - array != null
 * - action != null
 *
 * Postcondition:
 * - Every element in the array has been visited exactly once.
 * - Elements are visited from index 0 to array.length - 1.
 * - The array remains unchanged.
 *
 * Algorithmic Strategy:
 * Sequential indexed traversal.
 *
 * Time Complexity:
 * - Θ(n)
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Required.
 * - Benchmarking is used to experimentally observe how traversal
 *   time changes as the input size increases.
 * - The expected behavior is approximately linear growth because
 *   every array element must be visited exactly once.
 * - JMH should be used to reduce JVM warm-up, JIT compilation,
 *   and measurement-noise problems associated with manual timing.
 */

public class ArrayTraversal {

    public ArrayTraversal() {
    }
    /**
     * Visits every element of the array from left to right
     * and applies the specified action.
     *
     * @param array the integer array to traverse
     * @param action the operation to execute for each element
     */
    public static void traverse(int[] array, IntConsumer action) {

        for (int index = 0; index < array.length; index++) {
            action.accept(array[index]);
        }
    }

}
