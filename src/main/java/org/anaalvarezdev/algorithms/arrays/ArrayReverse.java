package org.anaalvarezdev.algorithms.arrays;
/**
 * Problem:
 * Given an integer array, reverse the order of its elements
 * without creating another array.
 *
 * Input:
 * - An integer array.
 *
 * Output:
 * - No value is returned.
 * - The input array is reversed in place.
 *
 * Precondition:
 * - array != null
 *
 * Postcondition:
 * - For every valid index i:
 *
 *   arrayAfter[i] == arrayBefore[array.length - 1 - i]
 *
 * - The same input array is modified.
 * - No auxiliary array is created.
 *
 * Algorithmic Strategy:
 * Two-pointer in-place reversal.
 *
 * One pointer starts at the beginning of the array and another
 * at the end. Their elements are swapped, and both pointers move
 * toward the center until they meet or cross.
 *
 * Time Complexity:
 * - Θ(n)
 * - Approximately floor(n / 2) swaps are performed.
 *
 * Space Complexity:
 * - Θ(1)
 * - Only a constant number of auxiliary variables is required.
 *
 * Benchmarking:
 * - Required.
 * - Benchmarking is used to observe how execution time changes
 *   as the input size increases.
 * - The expected behavior is linear because the number of swaps
 *   grows proportionally with n.
 */
public class ArrayReverse {
    public ArrayReverse() {
    }
    /**
     * Reverses the given array in place.
     *
     * @param array the integer array to reverse
     */
    public static void reverse(int[] array) {

        int left = 0;
        int right = array.length - 1;

        while (left < right) {

            int temporary = array[left];

            array[left] = array[right];
            array[right] = temporary;

            left++;
            right--;
        }
    }
}
