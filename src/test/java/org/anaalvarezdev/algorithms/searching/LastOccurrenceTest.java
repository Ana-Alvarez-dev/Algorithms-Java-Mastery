package org.anaalvarezdev.algorithms.searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LastOccurrenceTest {

    @Test
    void shouldReturnLastOccurrenceWhenTargetIsRepeated() {
        int[] values = {10, 20, 20, 20, 30};
        assertThat(LastOccurrence.search(values, 20)).isEqualTo(3);
    }

    @Test
    void shouldReturnIndexWhenTargetOccursOnce() {
        int[] values = {10, 20, 30, 40};
        assertThat(LastOccurrence.search(values, 30)).isEqualTo(2);
    }

    @Test
    void shouldReturnZeroWhenOnlyOccurrenceIsAtBeginning() {
        int[] values = {10, 20, 30, 40};
        assertThat(LastOccurrence.search(values, 10)).isZero();
    }

    @Test
    void shouldReturnLastIndexWhenLastOccurrenceIsAtEnd() {
        int[] values = {10, 20, 30, 40, 40};
        assertThat(LastOccurrence.search(values, 40)).isEqualTo(values.length - 1);
    }

    @Test
    void shouldReturnMinusOneWhenTargetIsAbsent() {
        int[] values = {10, 20, 30, 40};
        assertThat(LastOccurrence.search(values, 25)).isEqualTo(-1);
    }

    @Test
    void shouldReturnMinusOneForEmptyArray() {
        assertThat(LastOccurrence.search(new int[]{}, 10)).isEqualTo(-1);
    }

    @Test
    void shouldHandleSingleElementArray() {
        assertThat(LastOccurrence.search(new int[]{42}, 42)).isZero();
        assertThat(LastOccurrence.search(new int[]{42}, 7)).isEqualTo(-1);
    }

    @Test
    void shouldHandleAllElementsEqualToTarget() {
        int[] values = {5, 5, 5, 5, 5};
        assertThat(LastOccurrence.search(values, 5)).isEqualTo(values.length - 1);
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 20, 20, 30};
        int[] original = values.clone();
        LastOccurrence.search(values, 20);
        assertThat(values).containsExactly(original);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() -> LastOccurrence.search(null, 10))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("array must not be null");
    }
}
