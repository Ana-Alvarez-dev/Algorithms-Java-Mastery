package org.anaalvarezdev.algorithms.recursion;

import java.util.Objects;

/** Symmetry of int values; balanced recursion over mirrored pairs, not text processing. */
public final class Palindrome {
    private Palindrome() { }
    public static boolean recursive(int[] values) {
        Objects.requireNonNull(values,"values must not be null"); return pairs(values,0,values.length/2);
    }
    public static boolean iterative(int[] values) {
        Objects.requireNonNull(values,"values must not be null");
        for (int left = 0; left < values.length/2; left++) { if (values[left] != values[values.length-1-left]) { return false; } }
        return true;
    }
    private static boolean pairs(int[] values, int from, int to) {
        if (from == to) { return true; }
        if (to-from == 1) { return values[from] == values[values.length-1-from]; }
        int middle = from + (to-from)/2;
        return pairs(values,from,middle) && pairs(values,middle,to);
    }
}
