package org.anaalvarezdev.algorithms.recursion;

import java.util.Objects;

/** Exact long sum of an int array or half-open slice, without input mutation. */
public final class ArraySum {
    private ArraySum() { }
    public static long recursive(int[] values) { Objects.requireNonNull(values,"values must not be null"); return recursive(values,0,values.length); }
    public static long recursive(int[] values, int from, int to) { validate(values,from,to); return divide(values,from,to); }
    public static long iterative(int[] values) { Objects.requireNonNull(values,"values must not be null"); return iterative(values,0,values.length); }
    public static long iterative(int[] values, int from, int to) {
        validate(values,from,to); long total = 0;
        for (int index = from; index < to; index++) { total += values[index]; }
        return total;
    }
    private static long divide(int[] values, int from, int to) {
        if (from == to) { return 0; }
        if (to-from == 1) { return values[from]; }
        int middle = from + (to-from)/2;
        return divide(values,from,middle) + divide(values,middle,to);
    }
    private static void validate(int[] values, int from, int to) {
        Objects.requireNonNull(values,"values must not be null"); Objects.checkFromToIndex(from,to,values.length);
    }
}
