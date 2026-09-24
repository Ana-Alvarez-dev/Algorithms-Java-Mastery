package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LinearSearchTest {
    @Test
    void shouldReturnIndexWhenTargetIsInMiddle() {
        int[] values = {10, 20, 30, 40, 50};

        int result = LinearSearch.search(values, 30);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldReturnZeroWhenTargetIsFirstElement() {
        int[] values = {10, 20, 30, 40};

        int result = LinearSearch.search(values, 10);

        assertThat(result).isZero();
    }

    @Test
    void shouldReturnLastIndexWhenTargetIsLastElement() {
        int[] values = {10, 20, 30, 40};

        int result = LinearSearch.search(values, 40);

        assertThat(result).isEqualTo(values.length - 1);
    }

    @Test
    void shouldReturnMinusOneWhenTargetIsAbsent() {
        int[] values = {10, 20, 30, 40};

        int result = LinearSearch.search(values, 99);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void shouldReturnFirstOccurrenceWhenTargetIsRepeated() {
        int[] values = {10, 20, 30, 20, 40};

        int result = LinearSearch.search(values, 20);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldReturnZeroForSingleElementArrayWhenTargetExists() {
        int[] values = {42};

        int result = LinearSearch.search(values, 42);

        assertThat(result).isZero();
    }

    @Test
    void shouldReturnMinusOneForEmptyArray() {
        int[] values = {};

        int result = LinearSearch.search(values, 42);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 20, 30};

        LinearSearch.search(values, 20);

        assertThat(values)
                .containsExactly(10, 20, 30);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() ->
                LinearSearch.search(null, 10))
                .isInstanceOf(NullPointerException.class);
    }
}
