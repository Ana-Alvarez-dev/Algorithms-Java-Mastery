package org.anaalvarezdev.algorithms.recursion;

/** Euclidean variants for every int pair; nonnegative long result, gcd(0,0)=0. */
public final class GreatestCommonDivisor {
    private GreatestCommonDivisor() { }
    public static long recursive(int left, int right) { return euclid(Math.abs((long)left),Math.abs((long)right)); }
    public static long iterative(int left, int right) {
        long a = Math.abs((long)left); long b = Math.abs((long)right);
        while (b != 0) { long remainder = a % b; a = b; b = remainder; }
        return a;
    }
    private static long euclid(long a, long b) { return b == 0 ? a : euclid(b,a%b); }
}
