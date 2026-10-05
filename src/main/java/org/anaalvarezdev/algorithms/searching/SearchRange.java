package org.anaalvarezdev.algorithms.searching;

import java.util.Objects;

/**
 * Problem:
 * Given a sorted integer array and a target value,
 * return the inclusive range occupied by the target.
 *
 * Input:
 * - A sorted integer array.
 * - An integer target value.
 *
 * Output:
 * - A two-element array {firstIndex, lastIndex}.
 * - {-1, -1} if the target is not present.
 *
 * Preconditions:
 * - array != null
 * - The array is sorted in nondecreasing order.
 *
 * Postconditions:
 * - If the target exists, result[0] is its first occurrence.
 * - If the target exists, result[1] is its last occurrence.
 * - If the target is absent, result equals {-1, -1}.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Compose two boundary binary searches:
 * FirstOccurrence and LastOccurrence.
 *
 * Correctness:
 * - FirstOccurrence returns the minimum valid target index.
 * - LastOccurrence returns the maximum valid target index.
 * - Combining both results therefore returns the complete contiguous target
 *   range in a sorted array.
 *
 * Termination:
 * - Both delegated boundary searches terminate because their active intervals
 *   strictly decrease on every iteration.
 *
 * Time Complexity:
 * - Θ(log n)
 *
 * Space Complexity:
 * - Θ(1) auxiliary space, excluding the fixed-size returned array.
 *
 * Benchmarking:
 * - Not required.
 * - The method composes two already analysed Θ(log n) boundary searches and
 *   introduces no distinct empirical question.
 */
public final class SearchRange {

    private SearchRange() {
    }

    /**
     * Returns the inclusive range occupied by the target in a sorted array.
     *
     * @param array the sorted integer array to search
     * @param target the value whose range is required
     * @return a two-element array containing the first and last matching
     *         indices, or {-1, -1} if the target is absent
     * @throws NullPointerException if the array is null
     */
    public static int[] search(int[] array, int target) {
        Objects.requireNonNull(array, "array must not be null");

        int first = FirstOccurrence.search(array, target);

        if (first == -1) {
            return new int[]{-1, -1};
        }

        int last = LastOccurrence.search(array, target);

        return new int[]{first, last};
    }
}
