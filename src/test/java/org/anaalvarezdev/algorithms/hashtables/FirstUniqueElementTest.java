package org.anaalvarezdev.algorithms.hashtables;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FirstUniqueElementTest {
    @Test void shouldMatchScanningOracleForAllSmallArraysWithoutMutation() {
        SmallArrays.forEach(values->{
            int[] original=values.clone();int expected=-1;
            for(int index=0;index<values.length;index++) {
                int count=0;for(int value:values) { if(value==values[index]) { count++; } }
                if(count==1) { expected=index;break; }
            }
            assertThat(FirstUniqueElement.firstIndex(values)).isEqualTo(expected);assertThat(values).containsExactly(original);
        });
    }
    @Test void shouldChooseOriginalOrderWithExtremeValues() {
        assertThat(FirstUniqueElement.firstIndex(new int[]{Integer.MIN_VALUE,0,Integer.MIN_VALUE,Integer.MAX_VALUE})).isEqualTo(1);
    }
    @Test void shouldRejectNull() {
        assertThatThrownBy(()->FirstUniqueElement.firstIndex(null)).isInstanceOf(NullPointerException.class);
    }
}
