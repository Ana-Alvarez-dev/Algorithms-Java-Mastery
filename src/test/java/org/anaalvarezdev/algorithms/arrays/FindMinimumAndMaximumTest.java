package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FindMinimumAndMaximumTest {
    @Test
    void shouldReturnMinimumAndMaximumForEvenLengthArray() {
        int[] values = {8, 3, 10, 2, 7, 15};

        FindMinimumAndMaximum.MinMax result =
                FindMinimumAndMaximum.find(values);

        assertThat(result)
                .isEqualTo(
                        new FindMinimumAndMaximum.MinMax(2, 15)
                );
    }

    @Test
    void shouldReturnMinimumAndMaximumForOddLengthArray() {
        int[] values = {8, 3, 10, 2, 7};

        FindMinimumAndMaximum.MinMax result =
                FindMinimumAndMaximum.find(values);

        assertThat(result)
                .isEqualTo(
                        new FindMinimumAndMaximum.MinMax(2, 10)
                );
    }

    @Test
    void shouldReturnSameValueForSingleElement() {
        int[] values = {42};

        FindMinimumAndMaximum.MinMax result =
                FindMinimumAndMaximum.find(values);

        assertThat(result)
                .isEqualTo(
                        new FindMinimumAndMaximum.MinMax(42, 42)
                );
    }

    @Test
    void shouldHandleTwoElementsInAscendingOrder() {
        int[] values = {10, 20};

        FindMinimumAndMaximum.MinMax result =
                FindMinimumAndMaximum.find(values);

        assertThat(result)
                .isEqualTo(
                        new FindMinimumAndMaximum.MinMax(10, 20)
                );
    }

    @Test
    void shouldHandleTwoElementsInDescendingOrder() {
        int[] values = {20, 10};

        FindMinimumAndMaximum.MinMax result =
                FindMinimumAndMaximum.find(values);

        assertThat(result)
                .isEqualTo(
                        new FindMinimumAndMaximum.MinMax(10, 20)
                );
    }

    @Test
    void shouldFindMinimumAndMaximumAmongNegativeValues() {
        int[] values = {-20, -5, -30, -10};

        FindMinimumAndMaximum.MinMax result =
                FindMinimumAndMaximum.find(values);

        assertThat(result)
                .isEqualTo(
                        new FindMinimumAndMaximum.MinMax(-30, -5)
                );
    }

    @Test
    void shouldHandleRepeatedMinimumAndMaximumValues() {
        int[] values = {1, 10, 1, 5, 10, 7};

        FindMinimumAndMaximum.MinMax result =
                FindMinimumAndMaximum.find(values);

        assertThat(result)
                .isEqualTo(
                        new FindMinimumAndMaximum.MinMax(1, 10)
                );
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {8, 3, 10, 2};

        FindMinimumAndMaximum.find(values);

        assertThat(values)
                .containsExactly(8, 3, 10, 2);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsEmpty() {
        int[] values = {};

        assertThatThrownBy(() ->
                FindMinimumAndMaximum.find(values))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() ->
                FindMinimumAndMaximum.find(null))
                .isInstanceOf(NullPointerException.class);
    }
}
