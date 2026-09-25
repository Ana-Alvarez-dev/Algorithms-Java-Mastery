package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeleteAtIndexTest {
    @Test
    void shouldDeleteElementFromMiddle() {
        int[] values = {10, 20, 30, 40, 50, 0};

        int newSize =
                DeleteAtIndex.delete(values, 5, 2);

        assertThat(newSize).isEqualTo(4);

        assertThat(values)
                .containsExactly(10, 20, 40, 50, 0, 0);
    }

    @Test
    void shouldDeleteFirstElement() {
        int[] values = {10, 20, 30, 40};

        int newSize =
                DeleteAtIndex.delete(values, 4, 0);

        assertThat(newSize).isEqualTo(3);

        assertThat(values)
                .containsExactly(20, 30, 40, 0);
    }

    @Test
    void shouldDeleteLastElement() {
        int[] values = {10, 20, 30, 40};

        int newSize =
                DeleteAtIndex.delete(values, 4, 3);

        assertThat(newSize).isEqualTo(3);

        assertThat(values)
                .containsExactly(10, 20, 30, 0);
    }

    @Test
    void shouldDeleteOnlyElement() {
        int[] values = {42};

        int newSize =
                DeleteAtIndex.delete(values, 1, 0);

        assertThat(newSize).isZero();

        assertThat(values)
                .containsExactly(0);
    }

    @Test
    void shouldPreserveElementsBeforeDeletedIndex() {
        int[] values = {10, 20, 30, 40};

        DeleteAtIndex.delete(values, 4, 2);

        assertThat(values[0]).isEqualTo(10);
        assertThat(values[1]).isEqualTo(20);
    }

    @Test
    void shouldThrowExceptionWhenIndexIsNegative() {
        int[] values = {10, 20, 30};

        assertThatThrownBy(() ->
                DeleteAtIndex.delete(values, 3, -1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenIndexEqualsLogicalSize() {
        int[] values = {10, 20, 30};

        assertThatThrownBy(() ->
                DeleteAtIndex.delete(values, 3, 3))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenLogicalSizeIsZero() {
        int[] values = {0, 0, 0};

        assertThatThrownBy(() ->
                DeleteAtIndex.delete(values, 0, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowExceptionWhenLogicalSizeExceedsCapacity() {
        int[] values = {10, 20, 30};

        assertThatThrownBy(() ->
                DeleteAtIndex.delete(values, 4, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() ->
                DeleteAtIndex.delete(null, 1, 0))
                .isInstanceOf(NullPointerException.class);
    }
}
