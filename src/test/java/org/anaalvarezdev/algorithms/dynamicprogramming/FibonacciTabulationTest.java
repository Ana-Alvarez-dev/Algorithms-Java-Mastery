package org.anaalvarezdev.algorithms.dynamicprogramming;

import org.anaalvarezdev.algorithms.recursion.Fibonacci;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.Assertions.*;

class FibonacciTabulationTest {
    @Test void rejectsOutsideExactLongDomainBeforeAllocation() {
        for (int n : new int[]{-1, 93, Integer.MAX_VALUE}) {
            assertThatIllegalArgumentException().isThrownBy(() -> FibonacciTabulation.value(n));
        }
    }

    @Test void everySupportedValueMatchesIndependentBigIntegerSequence() {
        BigInteger previous = BigInteger.ZERO;
        BigInteger current = BigInteger.ONE;
        for (int n = 0; n <= 92; n++) {
            assertThat(FibonacciTabulation.value(n)).isEqualTo(previous.longValueExact());
            BigInteger next = previous.add(current);
            previous = current;
            current = next;
        }
        assertThat(FibonacciTabulation.value(92)).isEqualTo(7_540_113_804_746_346_429L);
        assertThat(previous).isGreaterThan(BigInteger.valueOf(Long.MAX_VALUE)); // F(93)
    }

    @Test void agreesWithReusedMemoisedAndRollingContractsWithoutCopyingThem() {
        for (int n = 0; n <= 92; n++) {
            assertThat(FibonacciTabulation.value(n)).isEqualTo(Fibonacci.memoised(n));
            assertThat(FibonacciTabulation.value(n)).isEqualTo(Fibonacci.iterative(n));
        }
    }

    @Test void cassiniIdentityHoldsUsingExactProducts() {
        for (int n = 1; n < 92; n++) {
            BigInteger a = BigInteger.valueOf(FibonacciTabulation.value(n - 1));
            BigInteger b = BigInteger.valueOf(FibonacciTabulation.value(n));
            BigInteger c = BigInteger.valueOf(FibonacciTabulation.value(n + 1));
            assertThat(a.multiply(c).subtract(b.multiply(b))).isEqualTo(BigInteger.valueOf(n % 2 == 0 ? 1 : -1));
        }
    }

    @Test void interleavedCallsKeepEmptyAndBoundaryValuesIndependent() {
        assertThat(FibonacciTabulation.value(0)).isZero();
        assertThat(FibonacciTabulation.value(92)).isPositive();
        assertThat(FibonacciTabulation.value(1)).isEqualTo(1);
        assertThat(FibonacciTabulation.value(5)).isEqualTo(5);
        assertThat(FibonacciTabulation.value(0)).isZero();
    }
}
