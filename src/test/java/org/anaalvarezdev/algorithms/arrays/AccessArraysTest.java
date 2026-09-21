package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AccessArraysTest {

    @Test
    void accessArraysTest(){
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        int index = 5;
        int result = AccessArrays.get(array, index);
        assertThat(result).isEqualTo(6);
    }


}
