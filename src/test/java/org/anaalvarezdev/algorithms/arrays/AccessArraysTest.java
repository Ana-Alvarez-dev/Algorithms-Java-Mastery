package org.anaalvarezdev.algorithms.arrays;

import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class AccessArraysTest {

    @Test
    void accessArraysTest(){
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        int index = 5;
        int result = AccessArrays.get(array, index);
        assertThat(result).isEqualTo(6);
    }
    @Test
    void shouldReturnLastElement() {
        int[] values = {10, 20, 30, 40};

        int result = AccessArrays.get(values, values.length - 1);

        assertThat(result).isEqualTo(40);
    }
    @Test
    void shouldReturnFirstElement() {
        int[] values = {10, 20, 30, 40};

        int result = AccessArrays.get(values, 0);

        assertThat(result).isEqualTo(10);
    }
    @Test
    void shouldThrowExceptionWhenIndexIsNegative() {
        int[] values = {10, 20, 30};

        assertThatThrownBy(() -> AccessArrays.get(values, -1))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }
    @Test
    void shouldThrowExceptionWhenIndexEqualsArrayLength() {
        int[] values = {10, 20, 30};

        assertThatThrownBy(() ->
                AccessArrays.get(values, values.length))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }
    @Test
    void shouldThrowExceptionWhenArrayIsNull() {
        assertThatThrownBy(() -> AccessArrays.get(null, 0))
                .isInstanceOf(NullPointerException.class);
    }


}
