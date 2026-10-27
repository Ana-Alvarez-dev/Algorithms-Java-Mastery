package org.anaalvarezdev.algorithms.searching;

import java.util.Objects;

/**
 * Problem:
 * Given a sorted integer array and a target value,
 * return the index of the array value closest to the target.
 *
 * Input:
 * - A sorted integer array.
 * - An integer target value.
 *
 * Output:
 * - The index of the closest value.
 * - -1 if the array is empty.
 *
 * Preconditions:
 * - array != null
 * - The array is sorted in nondecreasing order.
 *
 * Postconditions:
 * - If the array is non-empty, the returned index is valid.
 * - The absolute distance between array[result] and target is minimal.
 * - If two candidates are equally close, the lower index is returned.
 * - If the array is empty, the returned value is -1.
 * - The input array remains unchanged.
 *
 * Algorithmic Strategy:
 * Lower-bound search followed by comparison of the two boundary values.
 * If the lower value wins, a second lower bound finds its first occurrence.
 *
 * Correctness:
 * - At or below the minimum, index zero minimises distance and index.
 * - The first lower bound identifies the first value at least the target.
 *   Its predecessor, if present, is the largest value below the target.
 * - Sortedness makes one of these boundary values closest; ties favour lower.
 * - The upper index is already its value's first occurrence. A second lower
 *   bound selects the first copy when the lower value or maximum wins.
 * - Thus the method minimises both distance and index, including duplicates.
 *
 * Termination:
 * - Each delegated lower-bound iteration shrinks a finite half-open interval.
 *
 * Time Complexity:
 * - Best case: Θ(1), for empty input or target at/below the minimum.
 * - Worst case: Θ(log(n+1)), at most two lower bounds.
 *
 * Space Complexity:
 * - Θ(1)
 *
 * Benchmarking:
 * - Not required.
 * - The method reuses logarithmic binary-search reduction and does not yet
 *   introduce a separate empirical question.
 */
public final class ClosestValueSearch {

    private ClosestValueSearch() {
    }

    /**
     * Returns the index whose value is closest to the target.
     *
     * If two values are equally close, the lower index is returned.
     *
     * @param array the sorted integer array to search
     * @param target the value to approximate
     * @return the index of the closest value,
     *         or -1 if the array is empty
     * @throws NullPointerException if the array is null
     */
    public static int search(int[] array, int target) {
        Objects.requireNonNull(array, "array must not be null");

        if (array.length == 0) {
            return -1;
        }

        if (target <= array[0]) {
            return 0;
        }

        int upper = SearchInsertPosition.search(array, target);
        if (upper == array.length) {
            return SearchInsertPosition.search(array, array[array.length - 1]);
        }

        long lowerDistance = Math.abs((long) target - array[upper - 1]);
        long upperDistance = Math.abs((long) array[upper] - target);

        return lowerDistance <= upperDistance
                ? SearchInsertPosition.search(array, array[upper - 1]) : upper;
    }
}
