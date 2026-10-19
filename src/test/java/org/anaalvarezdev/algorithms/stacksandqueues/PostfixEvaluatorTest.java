package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostfixEvaluatorTest {
    @Test
    void shouldEvaluateNestedSubexpressionsAndSignedIntegers() {
        assertThat(PostfixEvaluator.evaluate("3 4 + 2 *")).isEqualTo(14);
        assertThat(PostfixEvaluator.evaluate("5 1 2 + 4 * + 3 -")).isEqualTo(14);
        assertThat(PostfixEvaluator.evaluate("-7 +2 *")).isEqualTo(-14);
        assertThat(PostfixEvaluator.evaluate("-2147483648")).isEqualTo(Integer.MIN_VALUE);
    }

    @Test
    void shouldPreserveLeftRightOperandOrderAndTruncateDivisionTowardsZero() {
        assertThat(PostfixEvaluator.evaluate("3 10 -")).isEqualTo(-7);
        assertThat(PostfixEvaluator.evaluate("20 4 /")).isEqualTo(5);
        assertThat(PostfixEvaluator.evaluate("-7 2 /")).isEqualTo(-3);
        assertThat(PostfixEvaluator.evaluate("7 -2 /")).isEqualTo(-3);
    }

    @Test
    void shouldAcceptJavaWhitespaceSeparators() {
        assertThat(PostfixEvaluator.evaluate(" \t3\n4\u2003+\r ")).isEqualTo(7);
    }

    @Test
    void shouldRejectMalformedExpressionsAndOutOfRangeOperands() {
        for (String text : new String[]{"", " \t", "+", "1 +", "1 2", "1 2 + +", "1 x +", "2147483648", "2 3 ^"}) {
            assertThatThrownBy(() -> PostfixEvaluator.evaluate(text)).as(text)
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void shouldRejectAllArithmeticOverflowDirections() {
        for (String text : new String[]{"2147483647 1 +", "-2147483648 1 -", "2147483647 2 *", "-2147483648 -1 /"}) {
            assertThatThrownBy(() -> PostfixEvaluator.evaluate(text)).as(text)
                    .isInstanceOf(ArithmeticException.class);
        }
    }

    @Test
    void shouldRejectDivisionByZeroAndNull() {
        assertThatThrownBy(() -> PostfixEvaluator.evaluate("3 0 /")).isInstanceOf(ArithmeticException.class);
        assertThatThrownBy(() -> PostfixEvaluator.evaluate(null)).isInstanceOf(NullPointerException.class);
    }
}
