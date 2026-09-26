package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RemoveDuplicatesSortedTest {
    @Test
    void shouldRemoveDuplicatesFromSortedArray() {
        int[] values = {1, 1, 2, 2, 3, 4, 4};

        int newSize = RemoveDuplicatesSorted.remove(values);

        assertThat(newSize).isEqualTo(4);

        assertThat(values)
                .startsWith(1, 2, 3, 4);
    }

    @Test
    void shouldKeepArrayWithOnlyUniqueValues() {
        int[] values = {1, 2, 3, 4, 5};

        int newSize = RemoveDuplicatesSorted.remove(values);

        assertThat(newSize).isEqualTo(5);

        assertThat(values)
                .startsWith(1, 2, 3, 4, 5);
    }

    @Test
    void shouldReduceAllEqualValuesToOneLogicalElement() {
        int[] values = {7, 7, 7, 7};

        int newSize = RemoveDuplicatesSorted.remove(values);

        assertThat(newSize).isEqualTo(1);
        assertThat(values[0]).isEqualTo(7);
    }

    @Test
    void shouldHandleSingleElementArray() {
        int[] values = {42};

        int newSize = RemoveDuplicatesSorted.remove(values);

        assertThat(newSize).isEqualTo(1);
        assertThat(values[0]).isEqualTo(42);
    }

    @Test
    void shouldHandleEmptyArray() {
        int[] values = {};

        int newSize = RemoveDuplicatesSorted.remove(values);

        assertThat(newSize).isZero();
        assertThat(values).isEmpty();
    }

    @Test
    void shouldHandleNegativeValues() {
        int[] values = {-5, -5, -3, -3, -1, 0, 0};

        int newSize = RemoveDuplicatesSorted.remove(values);

        assertThat(newSize).isEqualTo(4);

        assertThat(values)
                .startsWith(-5, -3, -1, 0);
    }

    @Test
    void shouldPreserveSortedOrderOfUniqueValues() {
        int[] values = {1, 1, 1, 2, 3, 3, 4, 5, 5};

        int newSize = RemoveDuplicatesSorted.remove(values);

        assertThat(newSize).isEqualTo(5);

        assertThat(values)
                .startsWith(1, 2, 3, 4, 5);
    }

    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() ->
                RemoveDuplicatesSorted.remove(null))
                .isInstanceOf(NullPointerException.class);
    }
}
