package org.anaalvarezdev.algorithms.searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ClosestValueSearchTest {

    @Test
    void shouldReturnExactMatchWhenTargetExists() {
        int[] values = {10, 20, 30, 40};

        assertThat(ClosestValueSearch.search(values, 30)).isEqualTo(2);
    }

    @Test
    void shouldReturnLowerNeighbourWhenItIsCloser() {
        int[] values = {10, 20, 40, 50};

        assertThat(ClosestValueSearch.search(values, 27)).isEqualTo(1);
    }

    @Test
    void shouldReturnUpperNeighbourWhenItIsCloser() {
        int[] values = {10, 20, 40, 50};

        assertThat(ClosestValueSearch.search(values, 34)).isEqualTo(2);
    }

    @Test
    void shouldReturnLowerIndexWhenCandidatesAreEquallyClose() {
        int[] values = {10, 20, 30, 40};

        assertThat(ClosestValueSearch.search(values, 25)).isEqualTo(1);
    }

    @Test
    void shouldReturnFirstIndexWhenTargetIsBelowMinimum() {
        int[] values = {10, 20, 30};

        assertThat(ClosestValueSearch.search(values, 1)).isZero();
    }

    @Test
    void shouldReturnLastIndexWhenTargetIsAboveMaximum() {
        int[] values = {10, 20, 30};

        assertThat(ClosestValueSearch.search(values, 100))
                .isEqualTo(values.length - 1);
    }

    @Test
    void shouldReturnMinusOneForEmptyArray() {
        assertThat(ClosestValueSearch.search(new int[]{}, 10)).isEqualTo(-1);
    }

    @Test
    void shouldHandleSingleElementArray() {
        assertThat(ClosestValueSearch.search(new int[]{42}, 100)).isZero();
    }

    @Test
    void shouldHandleNegativeValues() {
        int[] values = {-20, -10, 10, 30};

        assertThat(ClosestValueSearch.search(values, -6)).isEqualTo(1);
    }

    @Test
    void shouldAvoidIntegerOverflowWhenComparingDistances() {
        int[] values = {Integer.MIN_VALUE, Integer.MAX_VALUE};

        assertThat(ClosestValueSearch.search(values, 0)).isEqualTo(1);
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 20, 40};
        int[] original = values.clone();

        ClosestValueSearch.search(values, 31);

        assertThat(values).containsExactly(original);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() -> ClosestValueSearch.search(null, 10))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("array must not be null");
    }
}
