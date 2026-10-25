package org.anaalvarezdev.algorithms.recursion;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.assertThat;

class GreatestCommonDivisorTest {
    @Test void shouldDefineZerosSignsAndMinimumIntegerMagnitude() {
        assertThat(GreatestCommonDivisor.recursive(0,0)).isZero();
        assertThat(GreatestCommonDivisor.recursive(-54,24)).isEqualTo(6);
        assertThat(GreatestCommonDivisor.recursive(Integer.MIN_VALUE,0)).isEqualTo(2147483648L);
        assertThat(GreatestCommonDivisor.iterative(Integer.MIN_VALUE,Integer.MIN_VALUE)).isEqualTo(2147483648L);
        assertThat(GreatestCommonDivisor.recursive(Integer.MIN_VALUE,Integer.MAX_VALUE)).isEqualTo(1);
    }
    @Test void shouldMatchBigIntegerGcdForEverySmallSignedPair() {
        for (int a = -40; a <= 40; a++) for (int b = -40; b <= 40; b++) { check(a,b); }
    }
    @Test void shouldMatchSeededFullIntPairs() {
        var random = new SplittableRandom(20261025);
        for (int trial = 0; trial < 5000; trial++) { check(random.nextInt(),random.nextInt()); }
    }
    private static void check(int a, int b) {
        long expected = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).longValueExact();
        assertThat(GreatestCommonDivisor.recursive(a,b)).isEqualTo(expected);
        assertThat(GreatestCommonDivisor.iterative(a,b)).isEqualTo(expected);
    }
}
