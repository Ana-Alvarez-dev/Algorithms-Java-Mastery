package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FindMinimumTest {
    @Test
    void shouldReturnMinimumValue() {
        int[] values = {10, 7, 25, 3, 15};

        int result = FindMinimum.find(values);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void shouldReturnMinimumWhenItIsFirstElement() {
        int[] values = {1, 10, 20, 30};

        int result = FindMinimum.find(values);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldReturnMinimumWhenItIsLastElement() {
        int[] values = {10, 20, 30, 1};

        int result = FindMinimum.find(values);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldReturnOnlyElement() {
        int[] values = {42};

        int result = FindMinimum.find(values);

        assertThat(result).isEqualTo(42);
    }

    @Test
    void shouldFindMinimumAmongNegativeValues() {
        int[] values = {-5, -20, -3, -15};

        int result = FindMinimum.find(values);

        assertThat(result).isEqualTo(-20);
    }

    @Test
    void shouldFindMinimumWhenMinimumIsRepeated() {
        int[] values = {10, 2, 30, 2, 40};

        int result = FindMinimum.find(values);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 7, 25, 3};

        FindMinimum.find(values);

        assertThat(values)
                .containsExactly(10, 7, 25, 3);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsEmpty() {
        int[] values = {};

        assertThatThrownBy(() -> FindMinimum.find(values))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() -> FindMinimum.find(null))
                .isInstanceOf(NullPointerException.class);
    }
}
