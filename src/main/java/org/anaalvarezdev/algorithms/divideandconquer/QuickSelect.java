package org.anaalvarezdev.algorithms.divideandconquer;

import java.util.Objects;

/**
 * In-place zero-based order statistic using deterministic median-of-three
 * pivot values and a three-way partition. Duplicates count as separate ranks.
 * The result equals the element at rank in an ascending sorted copy.
 *
 * <p>Input is permuted: on return values[rank] is the result, items before it
 * are {@code <= result} and items after it are {@code >= result}. The array is not fully sorted.
 * Null fails first; an out-of-range rank throws IndexOutOfBoundsException before
 * mutation, including all ranks of an empty array.
 *
 * <p>Only one strict side is retained: this is decrease-and-conquer, implemented
 * iteratively with O(1) auxiliary state. Each partition is linear in its active
 * length; worst-case total work is O(n^2). Deterministic pivots do not promise
 * expected linear time on arbitrary input. All-equal input takes Theta(n) work.
 */
public final class QuickSelect {
    private QuickSelect() { }

    /** Selects an ascending rank and partitions the supplied array in place. */
    public static int select(int[] values, int rank) {
        Objects.requireNonNull(values, "values must not be null");
        Objects.checkIndex(rank, values.length);
        int from = 0;
        int to = values.length;
        while (to - from > 1) {
            int pivot = median(values[from], values[from + (to - from) / 2], values[to - 1]);
            int less = from;
            int scan = from;
            int greater = to;
            // [from,less) < pivot; [less,scan) == pivot; [greater,to) > pivot.
            while (scan < greater) {
                if (values[scan] < pivot) {
                    swap(values, less++, scan++);
                } else if (values[scan] > pivot) {
                    swap(values, scan, --greater);
                } else {
                    scan++;
                }
            }
            if (rank < less) {
                to = less;
            } else if (rank >= greater) {
                from = greater;
            } else {
                return values[rank];
            }
        }
        return values[rank];
    }

    private static int median(int a, int b, int c) {
        if (a < b) {
            return b < c ? b : Math.max(a, c);
        }
        return a < c ? a : Math.max(b, c);
    }

    private static void swap(int[] values, int first, int second) {
        int temporary = values[first];
        values[first] = values[second];
        values[second] = temporary;
    }
}
