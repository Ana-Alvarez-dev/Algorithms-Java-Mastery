package org.anaalvarezdev.algorithms.recursion;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FibonacciTest {
    @Test void shouldDefineBasesAndLargestLongFibonacci() {
        assertThat(Fibonacci.naive(0)).isZero(); assertThat(Fibonacci.naive(1)).isEqualTo(1);
        assertThat(Fibonacci.memoised(92)).isEqualTo(7540113804746346429L);
        assertThat(Fibonacci.iterative(92)).isEqualTo(7540113804746346429L);
    }
    @Test void shouldMatchBigIntegerOracleForEveryAcceptedLongInput() {
        BigInteger previous = BigInteger.ZERO; BigInteger current = BigInteger.ONE;
        for (int n = 0; n <= 92; n++) {
            assertThat(Fibonacci.memoised(n)).isEqualTo(previous.longValueExact());
            assertThat(Fibonacci.iterative(n)).isEqualTo(previous.longValueExact());
            BigInteger next = previous.add(current); previous = current; current = next;
        }
    }
    @Test void shouldMatchNaiveValuesAcrossItsEntireBoundedDomain() {
        BigInteger previous = BigInteger.ZERO; BigInteger current = BigInteger.ONE;
        for (int n = 0; n <= 30; n++) {
            assertThat(Fibonacci.naive(n)).isEqualTo(previous.longValueExact());
            BigInteger next = previous.add(current); previous = current; current = next;
        }
    }
    @Test void shouldDistinguishNaiveWorkLimitFromLongRangeAndRejectInvalidInputs() {
        assertThat(Fibonacci.iterative(31)).isEqualTo(1346269L);
        assertThatThrownBy(() -> Fibonacci.naive(31)).isInstanceOf(IllegalArgumentException.class);
        for (int n : new int[]{Integer.MIN_VALUE,-1,93,Integer.MAX_VALUE}) {
            assertThatThrownBy(() -> Fibonacci.memoised(n)).isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> Fibonacci.iterative(n)).isInstanceOf(IllegalArgumentException.class);
        }
        assertThatThrownBy(() -> Fibonacci.naive(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Fibonacci.naive(Integer.MAX_VALUE)).isInstanceOf(IllegalArgumentException.class);
    }
}
