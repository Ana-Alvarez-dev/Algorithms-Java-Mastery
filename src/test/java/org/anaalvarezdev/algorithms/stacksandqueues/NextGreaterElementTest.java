package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NextGreaterElementTest {
    @Test
    void shouldReturnNearestStrictlyGreaterIndicesRatherThanValues() {
        assertThat(NextGreaterElement.indices(new int[]{2, 2, 3, 1})).containsExactly(2, 2, -1, -1);
        assertThat(NextGreaterElement.indices(new int[]{1, 3, 2, 4})).containsExactly(1, 3, 3, -1);
        assertThat(NextGreaterElement.indices(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE})).containsExactly(1, -1);
    }

    @Test
    void shouldHandleEmptySingletonEqualAndDecreasingArrays() {
        assertThat(NextGreaterElement.indices(new int[0])).isEmpty();
        assertThat(NextGreaterElement.indices(new int[]{1})).containsExactly(-1);
        assertThat(NextGreaterElement.indices(new int[]{3, 3, 3})).containsExactly(-1, -1, -1);
        assertThat(NextGreaterElement.indices(new int[]{3, 2, 1})).containsExactly(-1, -1, -1);
    }

    @Test
    void shouldMatchIndependentQuadraticOracleForEverySmallArrayWithoutMutation() {
        for (int length = 0; length <= 6; length++) {
            int combinations = (int) Math.pow(3, length);
            for (int encoding = 0; encoding < combinations; encoding++) {
                int[] values = new int[length]; int remaining = encoding;
                for (int index = 0; index < length; index++) { values[index] = remaining % 3 - 1; remaining /= 3; }
                int[] original = values.clone(); int[] expected = new int[length];
                for (int index = 0; index < length; index++) {
                    expected[index] = -1;
                    for (int right = index + 1; right < length; right++) {
                        if (values[right] > values[index]) { expected[index] = right; break; }
                    }
                }
                assertThat(NextGreaterElement.indices(values)).containsExactly(expected);
                assertThat(values).containsExactly(original);
            }
        }
    }

    @Test
    void shouldRejectNull() {
        assertThatThrownBy(() -> NextGreaterElement.indices(null)).isInstanceOf(NullPointerException.class);
    }
}
