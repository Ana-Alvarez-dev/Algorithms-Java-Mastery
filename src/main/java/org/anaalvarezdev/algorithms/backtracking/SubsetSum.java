package org.anaalvarezdev.algorithms.backtracking;

import java.util.Objects;

/**
 * Exact decision: some subset of positions sums to a long target. Empty subset
 * is allowed; duplicate values remain different selectable positions. Signed
 * ints and zeros are accepted, input is preserved. Length 0..24 is a work cap;
 * null throws NullPointerException, exceeding the cap throws IllegalArgumentException.
 */
public final class SubsetSum {
    private SubsetSum() { }

    /** Signed suffix min/max bounds safely reject unreachable residual targets. */
    public static boolean exists(int[] values, long target) {
        validate(values);
        long[] minimum = new long[values.length + 1];
        long[] maximum = new long[values.length + 1];
        for (int i = values.length - 1; i >= 0; i--) {
            minimum[i] = minimum[i + 1] + Math.min(values[i],0);
            maximum[i] = maximum[i + 1] + Math.max(values[i],0);
        }
        return search(values,target,0,0,minimum,maximum);
    }

    /** Include-first reference without suffix-bound pruning; same boolean contract. */
    public static boolean existsWithoutBounds(int[] values, long target) {
        validate(values);
        return unbounded(values,target,0,0);
    }

    private static boolean search(int[] values, long target, int index, long sum, long[] minimum, long[] maximum) {
        if (sum == target) { return true; }
        if (target < sum + minimum[index] || target > sum + maximum[index]) { return false; }
        if (index == values.length) { return false; }
        return search(values,target,index + 1,sum + values[index],minimum,maximum)
                || search(values,target,index + 1,sum,minimum,maximum);
    }

    private static boolean unbounded(int[] values, long target, int index, long sum) {
        if (sum == target) { return true; }
        if (index == values.length) { return false; }
        return unbounded(values,target,index + 1,sum + values[index]) || unbounded(values,target,index + 1,sum);
    }

    private static void validate(int[] values) {
        Objects.requireNonNull(values,"values must not be null");
        if (values.length > 24) { throw new IllegalArgumentException("subset-sum search cap is 24 items"); }
    }
}
