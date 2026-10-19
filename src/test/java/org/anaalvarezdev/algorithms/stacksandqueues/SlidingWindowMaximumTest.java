package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SlidingWindowMaximumTest {
    @Test
    void shouldHandleRepeatedMaximaAndExpiringCandidates() {
        assertThat(SlidingWindowMaximum.maxima(new int[]{1, 3, 3, -1, 5}, 3)).containsExactly(3, 3, 5);
        assertThat(SlidingWindowMaximum.maxima(new int[]{9, 1, 2, 3}, 2)).containsExactly(9, 2, 3);
    }

    @Test
    void shouldHandleSingletonAndFullLengthWindowsAndIntegerExtremes() {
        int[] values = {Integer.MIN_VALUE, -1, Integer.MAX_VALUE};
        assertThat(SlidingWindowMaximum.maxima(values, 1)).containsExactly(values);
        assertThat(SlidingWindowMaximum.maxima(values, 3)).containsExactly(Integer.MAX_VALUE);
        assertThat(SlidingWindowMaximum.maxima(new int[]{-7}, 1)).containsExactly(-7);
    }

    @Test
    void shouldMatchScanningOracleForEverySmallArrayAndWindowWithoutMutation() {
        for (int length = 1; length <= 6; length++) {
            for (int encoding = 0; encoding < (int) Math.pow(3, length); encoding++) {
                int[] values = new int[length]; int remaining = encoding;
                for (int index = 0; index < length; index++) { values[index] = remaining % 3 - 1; remaining /= 3; }
                int[] original = values.clone();
                for (int window = 1; window <= length; window++) {
                    int[] expected = new int[length - window + 1];
                    for (int start = 0; start < expected.length; start++) {
                        int maximum = Integer.MIN_VALUE;
                        for (int offset = 0; offset < window; offset++) { maximum = Math.max(maximum, values[start + offset]); }
                        expected[start] = maximum;
                    }
                    assertThat(SlidingWindowMaximum.maxima(values, window)).containsExactly(expected);
                    assertThat(values).containsExactly(original);
                }
            }
        }
    }

    @Test
    void shouldRejectInvalidWindowsIncludingEmptyArray() {
        for (int window : new int[]{-1, 0, 4}) {
            assertThatThrownBy(() -> SlidingWindowMaximum.maxima(new int[]{1, 2, 3}, window))
                    .isInstanceOf(IllegalArgumentException.class);
        }
        assertThatThrownBy(() -> SlidingWindowMaximum.maxima(new int[0], 1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> SlidingWindowMaximum.maxima(null, 1)).isInstanceOf(NullPointerException.class);
    }
}
