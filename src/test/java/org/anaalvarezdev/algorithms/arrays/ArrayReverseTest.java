package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayReverseTest {
    @Test
    void shouldReverseEvenLengthArray() {
        int[] values = {10, 20, 30, 40};

        ArrayReverse.reverse(values);

        assertThat(values)
                .containsExactly(40, 30, 20, 10);
    }

    @Test
    void shouldReverseOddLengthArray() {
        int[] values = {10, 20, 30, 40, 50};

        ArrayReverse.reverse(values);

        assertThat(values)
                .containsExactly(50, 40, 30, 20, 10);
    }

    @Test
    void shouldKeepSingleElementArrayUnchanged() {
        int[] values = {42};

        ArrayReverse.reverse(values);

        assertThat(values)
                .containsExactly(42);
    }

    @Test
    void shouldHandleEmptyArray() {
        int[] values = {};

        ArrayReverse.reverse(values);

        assertThat(values).isEmpty();
    }

    @Test
    void shouldReverseNegativeValues() {
        int[] values = {-10, -20, -30, -40};

        ArrayReverse.reverse(values);

        assertThat(values)
                .containsExactly(-40, -30, -20, -10);
    }

    @Test
    void shouldHandleRepeatedValues() {
        int[] values = {1, 2, 2, 3};

        ArrayReverse.reverse(values);

        assertThat(values)
                .containsExactly(3, 2, 2, 1);
    }

    @Test
    void shouldRestoreOriginalArrayWhenReversedTwice() {
        int[] values = {10, 20, 30, 40};

        ArrayReverse.reverse(values);
        ArrayReverse.reverse(values);

        assertThat(values)
                .containsExactly(10, 20, 30, 40);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() ->
                ArrayReverse.reverse(null))
                .isInstanceOf(NullPointerException.class);
    }
}
