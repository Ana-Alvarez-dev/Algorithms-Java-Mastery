package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayUpdateTest {

    @Test
    void updateArrayTest() {
        int[] array = {1, 2, 3, 4, 5};
        int index = 2;
        int newValue = 10;

        ArrayUpdate.update(array, index, newValue);

        assertThat(array[index]).isEqualTo(newValue);
    }
    @Test
    void shouldUpdateElementAtGivenIndex() {
        int[] values = {10, 20, 30, 40};

        ArrayUpdate.update(values, 2, 99);

        assertThat(values)
                .containsExactly(10, 20, 99, 40);
    }

    @Test
    void shouldThrowExceptionWhenIndexIsNegative() {

        int[] array = {10,15,20,25,30,35};
        int index = -1;
        int value = 24;
        assertThatThrownBy(() -> ArrayUpdate.update(array, index, value))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenIndexEqualsArrayLength() {
        int[] array = {10, 20, 30};
        int index = array.length;
        int value = 99;

        assertThatThrownBy(() ->
                ArrayUpdate.update(array, index, 99))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }
    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() ->
                ArrayUpdate.update(null, 0, 99))
                .isInstanceOf(NullPointerException.class);
    }


}
