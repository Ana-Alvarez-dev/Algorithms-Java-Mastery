package org.anaalvarezdev.algorithms.searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FirstOccurrenceTest {

    @Test
    void shouldReturnFirstOccurrenceWhenTargetIsRepeated() {
        int[] values = {10, 20, 20, 20, 30};
        assertThat(FirstOccurrence.search(values, 20)).isEqualTo(1);
    }

    @Test
    void shouldReturnIndexWhenTargetOccursOnce() {
        int[] values = {10, 20, 30, 40};
        assertThat(FirstOccurrence.search(values, 30)).isEqualTo(2);
    }

    @Test
    void shouldReturnZeroWhenFirstOccurrenceIsAtBeginning() {
        int[] values = {10, 10, 20, 30};
        assertThat(FirstOccurrence.search(values, 10)).isZero();
    }

    @Test
    void shouldReturnLastIndexWhenOnlyOccurrenceIsAtEnd() {
        int[] values = {10, 20, 30, 40};
        assertThat(FirstOccurrence.search(values, 40)).isEqualTo(values.length - 1);
    }

    @Test
    void shouldReturnMinusOneWhenTargetIsAbsent() {
        int[] values = {10, 20, 30, 40};
        assertThat(FirstOccurrence.search(values, 25)).isEqualTo(-1);
    }

    @Test
    void shouldReturnMinusOneForEmptyArray() {
        assertThat(FirstOccurrence.search(new int[]{}, 10)).isEqualTo(-1);
    }

    @Test
    void shouldHandleSingleElementArray() {
        assertThat(FirstOccurrence.search(new int[]{42}, 42)).isZero();
        assertThat(FirstOccurrence.search(new int[]{42}, 7)).isEqualTo(-1);
    }

    @Test
    void shouldHandleAllElementsEqualToTarget() {
        int[] values = {5, 5, 5, 5, 5};
        assertThat(FirstOccurrence.search(values, 5)).isZero();
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 20, 20, 30};
        int[] original = values.clone();
        FirstOccurrence.search(values, 20);
        assertThat(values).containsExactly(original);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() -> FirstOccurrence.search(null, 10))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("array must not be null");
    }
}
