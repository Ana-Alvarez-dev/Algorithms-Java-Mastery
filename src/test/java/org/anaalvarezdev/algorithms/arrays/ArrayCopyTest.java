package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayCopyTest {
    @Test
    void shouldCopyAllElements() {
        int[] values = {10, 20, 30, 40};

        int[] result = ArrayCopy.copy(values);

        assertThat(result)
                .containsExactly(10, 20, 30, 40);
    }

    @Test
    void shouldCreateDifferentArrayInstance() {
        int[] values = {10, 20, 30};

        int[] result = ArrayCopy.copy(values);

        assertThat(result)
                .isNotSameAs(values);
    }

    @Test
    void shouldCreateIndependentCopy() {
        int[] values = {10, 20, 30};

        int[] result = ArrayCopy.copy(values);

        result[0] = 99;

        assertThat(values)
                .containsExactly(10, 20, 30);

        assertThat(result)
                .containsExactly(99, 20, 30);
    }

    @Test
    void shouldCopySingleElementArray() {
        int[] values = {42};

        int[] result = ArrayCopy.copy(values);

        assertThat(result)
                .containsExactly(42);
    }

    @Test
    void shouldCopyEmptyArray() {
        int[] values = {};

        int[] result = ArrayCopy.copy(values);

        assertThat(result).isEmpty();
        assertThat(result).isNotSameAs(values);
    }

    @Test
    void shouldCopyNegativeAndRepeatedValues() {
        int[] values = {-10, 20, -10, 20};

        int[] result = ArrayCopy.copy(values);

        assertThat(result)
                .containsExactly(-10, 20, -10, 20);
    }

    @Test
    void shouldNotModifyOriginalArray() {
        int[] values = {10, 20, 30};

        ArrayCopy.copy(values);

        assertThat(values)
                .containsExactly(10, 20, 30);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() ->
                ArrayCopy.copy(null))
                .isInstanceOf(NullPointerException.class);
    }
}
