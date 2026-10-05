package org.anaalvarezdev.algorithms.searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BinarySearchTest {

    @Test
    void shouldReturnIndexWhenTargetIsInMiddle() {
        int[] values = {10, 20, 30, 40, 50};

        int result = BinarySearch.search(values, 30);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldReturnIndexWhenTargetIsFirstElement() {
        int[] values = {10, 20, 30, 40, 50};

        int result = BinarySearch.search(values, 10);

        assertThat(result).isZero();
    }

    @Test
    void shouldReturnIndexWhenTargetIsLastElement() {
        int[] values = {10, 20, 30, 40, 50};

        int result = BinarySearch.search(values, 50);

        assertThat(result).isEqualTo(values.length - 1);
    }

    @Test
    void shouldReturnMinusOneWhenTargetIsAbsent() {
        int[] values = {10, 20, 30, 40, 50};

        int result = BinarySearch.search(values, 35);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void shouldReturnMinusOneForEmptyArray() {
        int[] values = {};

        int result = BinarySearch.search(values, 10);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void shouldReturnZeroForSingleElementArrayWhenTargetExists() {
        int[] values = {42};

        int result = BinarySearch.search(values, 42);

        assertThat(result).isZero();
    }

    @Test
    void shouldReturnMinusOneForSingleElementArrayWhenTargetIsAbsent() {
        int[] values = {42};

        int result = BinarySearch.search(values, 7);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void shouldSearchTwoElementArray() {
        int[] values = {10, 20};

        assertThat(BinarySearch.search(values, 10)).isZero();
        assertThat(BinarySearch.search(values, 20)).isEqualTo(1);
    }

    @Test
    void shouldReturnAValidMatchingIndexWhenDuplicatesExist() {
        int[] values = {10, 20, 20, 20, 30};

        int result = BinarySearch.search(values, 20);

        assertThat(result).isBetween(1, 3);
        assertThat(values[result]).isEqualTo(20);
    }

    @Test
    void shouldReturnMinusOneWhenTargetIsBelowMinimum() {
        int[] values = {10, 20, 30, 40};

        int result = BinarySearch.search(values, 5);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void shouldReturnMinusOneWhenTargetIsAboveMaximum() {
        int[] values = {10, 20, 30, 40};

        int result = BinarySearch.search(values, 50);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 20, 30, 40};
        int[] original = values.clone();

        BinarySearch.search(values, 30);

        assertThat(values).containsExactly(original);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() -> BinarySearch.search(null, 10))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("array must not be null");
    }
}
