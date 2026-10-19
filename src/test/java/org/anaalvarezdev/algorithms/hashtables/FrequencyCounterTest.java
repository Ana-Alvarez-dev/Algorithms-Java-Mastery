package org.anaalvarezdev.algorithms.hashtables;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrequencyCounterTest {
    @Test void shouldMatchIndependentScanningCountsForAllSmallArrays() {
        SmallArrays.forEach(values->{
            int[] original=values.clone();Map<Integer,Integer> expected=new HashMap<>();
            for(int value:values) { int count=0;for(int other:values) { if(value==other) { count++; } }expected.put(value,count); }
            assertThat(FrequencyCounter.count(values)).isEqualTo(expected);assertThat(values).containsExactly(original);
        });
    }
    @Test void shouldSupportExtremesAndIndependentMutableResults() {
        Map<Integer,Integer> counts=FrequencyCounter.count(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE});
        assertThat(counts).isEqualTo(Map.of(Integer.MIN_VALUE,2,Integer.MAX_VALUE,1));
        counts.clear();assertThat(FrequencyCounter.count(new int[]{1})).isEqualTo(Map.of(1,1));
    }
    @Test void shouldRejectNull() {
        assertThatThrownBy(()->FrequencyCounter.count(null)).isInstanceOf(NullPointerException.class);
    }
}
