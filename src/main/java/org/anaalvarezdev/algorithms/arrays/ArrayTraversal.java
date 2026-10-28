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
 * - On normal completion, action is called once per index, in index order,
 *   with the value read from that slot at that time.
 * - This traversal does not write the array itself. The action can mutate
 *   aliased input or external state; no snapshot or rollback is provided.
 * - Input contents remain unchanged only if the action and other actors
 *   do not modify them. An action exception prevents later visits.
 *
 * Algorithmic Strategy:
 * Sequential indexed traversal.
 *
 * Time Complexity:
 * - On normal completion, Θ(1 + n) own work plus the action's work.
 * - Θ(n) for a full traversal with constant-cost, normally completing actions.
 *
 * Space Complexity:
 * - Θ(1) traversal workspace, excluding action allocations and retained output.
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
     * @param action the operation to execute for each current element;
     *               its mutation, allocation and failure effects are not isolated
     */
    public static void traverse(int[] array, IntConsumer action) {

        for (int index = 0; index < array.length; index++) {
            //action.accept is a int consumer.It takes an int
            //but doesn't return a value
            action.accept(array[index]);
        }
    }

}
