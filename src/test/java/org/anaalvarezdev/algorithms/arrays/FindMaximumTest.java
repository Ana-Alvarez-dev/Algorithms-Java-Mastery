package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FindMaximumTest {
    @Test
    void shouldReturnMaximumValue() {
        int[] values = {10, 25, 7, 40, 15};

        int result = FindMaximum.find(values);

        assertThat(result).isEqualTo(40);
    }

    @Test
    void shouldReturnMaximumWhenItIsFirstElement() {
        int[] values = {50, 10, 20, 30};

        int result = FindMaximum.find(values);

        assertThat(result).isEqualTo(50);
    }

    @Test
    void shouldReturnMaximumWhenItIsLastElement() {
        int[] values = {10, 20, 30, 50};

        int result = FindMaximum.find(values);

        assertThat(result).isEqualTo(50);
    }

    @Test
    void shouldReturnOnlyElement() {
        int[] values = {42};

        int result = FindMaximum.find(values);

        assertThat(result).isEqualTo(42);
    }

    @Test
    void shouldFindMaximumAmongNegativeValues() {
        int[] values = {-20, -5, -30, -10};

        int result = FindMaximum.find(values);

        assertThat(result).isEqualTo(-5);
    }

    @Test
    void shouldFindMaximumWhenMaximumIsRepeated() {
        int[] values = {10, 40, 20, 40, 30};

        int result = FindMaximum.find(values);

        assertThat(result).isEqualTo(40);
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 25, 7, 40};

        FindMaximum.find(values);

        assertThat(values)
                .containsExactly(10, 25, 7, 40);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsEmpty() {
        int[] values = {};

        assertThatThrownBy(() -> FindMaximum.find(values))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() -> FindMaximum.find(null))
                .isInstanceOf(NullPointerException.class);
    }
}
