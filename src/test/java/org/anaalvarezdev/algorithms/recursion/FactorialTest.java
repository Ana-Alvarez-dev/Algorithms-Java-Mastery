package org.anaalvarezdev.algorithms.recursion;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FactorialTest {
    @Test void shouldHandleZeroOneAndTheExactLongBoundary() {
        assertThat(Factorial.recursive(0)).isEqualTo(1); assertThat(Factorial.tailRecursive(1)).isEqualTo(1);
        assertThat(Factorial.iterative(20)).isEqualTo(2432902008176640000L);
    }
    @Test void shouldMatchBigIntegerOracleThroughoutTheAcceptedDomain() {
        BigInteger expected = BigInteger.ONE;
        for (int n = 0; n <= 20; n++) {
            if (n > 0) { expected = expected.multiply(BigInteger.valueOf(n)); }
            long result = expected.longValueExact();
            assertThat(Factorial.recursive(n)).isEqualTo(result); assertThat(Factorial.tailRecursive(n)).isEqualTo(result);
            assertThat(Factorial.iterative(n)).isEqualTo(result);
        }
    }
    @Test void shouldRejectNegativeAndOverflowingInputsForAllVariants() {
        for (int n : new int[]{Integer.MIN_VALUE,-1,21,Integer.MAX_VALUE}) {
            assertThatThrownBy(() -> Factorial.recursive(n)).isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> Factorial.tailRecursive(n)).isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> Factorial.iterative(n)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
