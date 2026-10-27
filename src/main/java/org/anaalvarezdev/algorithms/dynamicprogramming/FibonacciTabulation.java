package org.anaalvarezdev.algorithms.dynamicprogramming;

/**
 * Full-table Fibonacci, F(0)=0 and F(1)=1. Accepts 0..92, the exact nonnegative
 * long domain. Invalid arguments fail before allocation. Per-call table state
 * is private; memoised and rolling variants are reused from recursion.Fibonacci.
 */
public final class FibonacciTabulation {
    private FibonacciTabulation() { }

    public static long value(int n) {
        if (n < 0 || n > 92) {
            throw new IllegalArgumentException("requires 0 <= n <= 92");
        }
        long[] table = new long[n + 1];
        if (n > 0) { table[1] = 1; }
        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }
        return table[n];
    }
}
