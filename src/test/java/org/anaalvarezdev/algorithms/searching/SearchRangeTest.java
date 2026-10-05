package org.anaalvarezdev.algorithms.searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SearchRangeTest {

    @Test
    void shouldReturnFirstAndLastOccurrenceForRepeatedTarget() {
        int[] values = {10, 20, 20, 20, 30};

        assertThat(SearchRange.search(values, 20))
                .containsExactly(1, 3);
    }

    @Test
    void shouldReturnSameIndexWhenTargetOccursOnce() {
        int[] values = {10, 20, 30, 40};

        assertThat(SearchRange.search(values, 30))
                .containsExactly(2, 2);
    }

    @Test
    void shouldReturnMinusOneRangeWhenTargetIsAbsent() {
        int[] values = {10, 20, 30, 40};

        assertThat(SearchRange.search(values, 25))
                .containsExactly(-1, -1);
    }

    @Test
    void shouldReturnMinusOneRangeForEmptyArray() {
        assertThat(SearchRange.search(new int[]{}, 10))
                .containsExactly(-1, -1);
    }

    @Test
    void shouldHandleAllElementsEqualToTarget() {
        int[] values = {5, 5, 5, 5};

        assertThat(SearchRange.search(values, 5))
                .containsExactly(0, values.length - 1);
    }

    @Test
    void shouldHandleTargetAtArrayBoundaries() {
        int[] values = {10, 10, 20, 30, 40, 40};

        assertThat(SearchRange.search(values, 10))
                .containsExactly(0, 1);

        assertThat(SearchRange.search(values, 40))
                .containsExactly(4, 5);
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 20, 20, 30};
        int[] original = values.clone();

        SearchRange.search(values, 20);

        assertThat(values).containsExactly(original);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() -> SearchRange.search(null, 10))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("array must not be null");
    }
}
