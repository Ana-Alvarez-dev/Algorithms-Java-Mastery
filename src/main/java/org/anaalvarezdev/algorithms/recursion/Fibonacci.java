package org.anaalvarezdev.algorithms.recursion;

import java.util.Arrays;

/** F(0)=0, F(1)=1. Naive work is deliberately bounded; no shared memo cache. */
public final class Fibonacci {
    private Fibonacci() { }
    /** Educational exponential implementation, accepting only 0..30. */
    public static long naive(int n) { validate(n,30); return branch(n); }
    /** Exact long result for 0..92, including a fresh memo array per call. */
    public static long memoised(int n) {
        validate(n,92); long[] memo = new long[n+1]; Arrays.fill(memo,-1); return cached(n,memo);
    }
    public static long iterative(int n) {
        validate(n,92);
        if (n == 0) { return 0; }
        long previous = 0; long current = 1;
        for (int index = 2; index <= n; index++) { long next = previous + current; previous = current; current = next; }
        return current;
    }
    private static long branch(int n) { return n < 2 ? n : branch(n-1) + branch(n-2); }
    private static long cached(int n, long[] memo) {
        if (memo[n] >= 0) { return memo[n]; }
        memo[n] = n < 2 ? n : cached(n-1,memo) + cached(n-2,memo); return memo[n];
    }
    private static void validate(int n, int maximum) {
        if (n < 0 || n > maximum) { throw new IllegalArgumentException("Fibonacci input outside selected domain"); }
    }
}
