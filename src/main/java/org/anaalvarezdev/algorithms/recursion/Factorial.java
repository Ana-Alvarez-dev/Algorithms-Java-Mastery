package org.anaalvarezdev.algorithms.recursion;

/** Exact long factorial variants, restricted to 0..20; 0! = 1. */
public final class Factorial {
    private Factorial() { }
    public static long recursive(int n) { validate(n); return product(n); }
    public static long tailRecursive(int n) { validate(n); return accumulate(n,1); }
    public static long iterative(int n) {
        validate(n); long result = 1;
        for (int factor = 2; factor <= n; factor++) { result *= factor; }
        return result;
    }
    private static long product(int n) { return n == 0 ? 1 : n * product(n-1); }
    private static long accumulate(int n, long result) { return n == 0 ? result : accumulate(n-1,result*n); }
    private static void validate(int n) {
        if (n < 0 || n > 20) { throw new IllegalArgumentException("factorial requires 0 <= n <= 20"); }
    }
}
