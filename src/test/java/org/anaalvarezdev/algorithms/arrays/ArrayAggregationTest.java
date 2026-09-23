package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayAggregationTest {

    @Test
    void shouldReturnSumOfAllElements() {
        int[] values = {10, 20, 30, 40};

        int result = ArrayAggregation.sum(values);

        assertThat(result).isEqualTo(100);
    }

    @Test
    void shouldReturnOnlyElementWhenArrayHasOneElement() {
        int[] values = {42};

        int result = ArrayAggregation.sum(values);

        assertThat(result).isEqualTo(42);
    }

    @Test
    void shouldReturnZeroWhenArrayIsEmpty() {
        int[] values = {};

        int result = ArrayAggregation.sum(values);

        assertThat(result).isZero();
    }

    @Test
    void shouldSumNegativeValues() {
        int[] values = {-10, -20, -30};

        int result = ArrayAggregation.sum(values);

        assertThat(result).isEqualTo(-60);
    }

    @Test
    void shouldSumPositiveAndNegativeValues() {
        int[] values = {10, -20, 30, -40};

        int result = ArrayAggregation.sum(values);

        assertThat(result).isEqualTo(-20);
    }
    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() ->
                ArrayAggregation.sum(null))
                .isInstanceOf(NullPointerException.class);
    }

}
