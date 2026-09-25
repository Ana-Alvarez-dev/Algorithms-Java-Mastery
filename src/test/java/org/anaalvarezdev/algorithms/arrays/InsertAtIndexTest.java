package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InsertAtIndexTest {
    @Test
    void shouldInsertValueInMiddle() {
        int[] values = {10, 20, 30, 40, 0, 0};
        int size = 4;

        int newSize =
                InsertAtIndex.insert(values, size, 2, 99);

        assertThat(newSize).isEqualTo(5);

        assertThat(values)
                .containsExactly(10, 20, 99, 30, 40, 0);
    }

    @Test
    void shouldInsertValueAtBeginning() {
        int[] values = {10, 20, 30, 0};
        int size = 3;

        int newSize =
                InsertAtIndex.insert(values, size, 0, 99);

        assertThat(newSize).isEqualTo(4);

        assertThat(values)
                .containsExactly(99, 10, 20, 30);
    }

    @Test
    void shouldInsertValueAtEnd() {
        int[] values = {10, 20, 30, 0};
        int size = 3;

        int newSize =
                InsertAtIndex.insert(values, size, size, 99);

        assertThat(newSize).isEqualTo(4);

        assertThat(values)
                .containsExactly(10, 20, 30, 99);
    }

    @Test
    void shouldInsertIntoLogicallyEmptyArray() {
        int[] values = {0, 0, 0};

        int newSize =
                InsertAtIndex.insert(values, 0, 0, 42);

        assertThat(newSize).isEqualTo(1);

        assertThat(values)
                .containsExactly(42, 0, 0);
    }

    @Test
    void shouldPreserveElementsBeforeInsertionIndex() {
        int[] values = {10, 20, 30, 40, 0};

        InsertAtIndex.insert(values, 4, 2, 99);

        assertThat(values[0]).isEqualTo(10);
        assertThat(values[1]).isEqualTo(20);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsFull() {
        int[] values = {10, 20, 30};

        assertThatThrownBy(() ->
                InsertAtIndex.insert(values, 3, 1, 99))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowExceptionWhenIndexIsNegative() {
        int[] values = {10, 20, 0};

        assertThatThrownBy(() ->
                InsertAtIndex.insert(values, 2, -1, 99))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenIndexExceedsLogicalSize() {
        int[] values = {10, 20, 0, 0};

        assertThatThrownBy(() ->
                InsertAtIndex.insert(values, 2, 3, 99))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() ->
                InsertAtIndex.insert(null, 0, 0, 99))
                .isInstanceOf(NullPointerException.class);
    }
}
