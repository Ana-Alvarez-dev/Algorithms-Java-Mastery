package org.anaalvarezdev.algorithms.searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SearchInsertPositionTest {

    @Test
    void shouldReturnExistingIndexWhenTargetExists() {
        int[] values = {10, 20, 30, 40};
        assertThat(SearchInsertPosition.search(values, 30)).isEqualTo(2);
    }

    @Test
    void shouldReturnFirstOccurrenceWhenDuplicatesExist() {
        int[] values = {10, 20, 20, 20, 30};
        assertThat(SearchInsertPosition.search(values, 20)).isEqualTo(1);
    }

    @Test
    void shouldReturnBeginningWhenTargetIsSmallerThanAllElements() {
        int[] values = {10, 20, 30};
        assertThat(SearchInsertPosition.search(values, 5)).isZero();
    }

    @Test
    void shouldReturnArrayLengthWhenTargetIsGreaterThanAllElements() {
        int[] values = {10, 20, 30};
        assertThat(SearchInsertPosition.search(values, 40)).isEqualTo(values.length);
    }

    @Test
    void shouldReturnPositionBetweenExistingElements() {
        int[] values = {10, 20, 40, 50};
        assertThat(SearchInsertPosition.search(values, 30)).isEqualTo(2);
    }

    @Test
    void shouldReturnZeroForEmptyArray() {
        assertThat(SearchInsertPosition.search(new int[]{}, 10)).isZero();
    }

    @Test
    void shouldHandleSingleElementArray() {
        int[] values = {20};
        assertThat(SearchInsertPosition.search(values, 10)).isZero();
        assertThat(SearchInsertPosition.search(values, 20)).isZero();
        assertThat(SearchInsertPosition.search(values, 30)).isEqualTo(1);
    }

    @Test
    void shouldHandleNegativeValues() {
        int[] values = {-20, -10, 0, 10};
        assertThat(SearchInsertPosition.search(values, -15)).isEqualTo(1);
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 20, 40};
        int[] original = values.clone();
        SearchInsertPosition.search(values, 30);
        assertThat(values).containsExactly(original);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() -> SearchInsertPosition.search(null, 10))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("array must not be null");
    }
}
