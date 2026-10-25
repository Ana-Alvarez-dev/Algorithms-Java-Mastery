package org.anaalvarezdev.algorithms.recursion;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PowerTest {
    @Test void shouldMatchBigIntegerPowForSignsParityZeroAndSmallExponents() {
        for (int value = -8; value <= 8; value++) for (int exponent = 0; exponent <= 64; exponent++) {
            BigInteger base = BigInteger.valueOf(value); BigInteger expected = base.pow(exponent);
            assertThat(Power.recursive(base,exponent)).isEqualTo(expected); assertThat(Power.iterative(base,exponent)).isEqualTo(expected);
        }
    }
    @Test void shouldHandleLargeExponentWithBoundedResultAndLogarithmicDepth() {
        for (int value : new int[]{-1,0,1}) {
            BigInteger base = BigInteger.valueOf(value); BigInteger expected = BigInteger.valueOf(value);
            assertThat(Power.recursive(base,Integer.MAX_VALUE)).isEqualTo(expected);
            assertThat(Power.iterative(base,Integer.MAX_VALUE)).isEqualTo(expected);
        }
    }
    @Test void shouldRetainExactLargeResults() {
        for (BigInteger base : new BigInteger[]{BigInteger.TWO,BigInteger.valueOf(-17),BigInteger.ONE.shiftLeft(128).add(BigInteger.ONE)}) {
            int exponent = base.equals(BigInteger.TWO) ? 10000 : 127;
            assertThat(Power.recursive(base,exponent)).isEqualTo(base.pow(exponent));
            assertThat(Power.iterative(base,exponent)).isEqualTo(base.pow(exponent));
        }
    }
    @Test void shouldRejectNullAndNegativeExponent() {
        assertThatThrownBy(() -> Power.recursive(null,0)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Power.iterative(null,0)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Power.recursive(BigInteger.TWO,-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Power.iterative(BigInteger.TWO,Integer.MIN_VALUE)).isInstanceOf(IllegalArgumentException.class);
    }
}
