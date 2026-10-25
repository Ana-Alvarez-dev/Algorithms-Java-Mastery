package org.anaalvarezdev.algorithms.recursion;

import java.math.BigInteger;
import java.util.Objects;

/** Exponentiation by squaring with exact integers; 0^0 is defined as 1. */
public final class Power {
    private Power() { }
    public static BigInteger recursive(BigInteger base, int exponent) { validate(base,exponent); return square(base,exponent); }
    public static BigInteger iterative(BigInteger base, int exponent) {
        validate(base,exponent); BigInteger result = BigInteger.ONE; BigInteger factor = base;
        int remaining = exponent;
        while (remaining > 0) {
            if ((remaining & 1) != 0) { result = result.multiply(factor); }
            remaining >>>= 1;
            if (remaining > 0) { factor = factor.multiply(factor); }
        }
        return result;
    }
    private static BigInteger square(BigInteger base, int exponent) {
        if (exponent == 0) { return BigInteger.ONE; }
        if (exponent == 1) { return base; }
        BigInteger half = square(base,exponent/2); BigInteger result = half.multiply(half);
        return (exponent & 1) == 0 ? result : result.multiply(base);
    }
    private static void validate(BigInteger base, int exponent) {
        Objects.requireNonNull(base,"base must not be null");
        if (exponent < 0) { throw new IllegalArgumentException("negative exponent"); }
    }
}
