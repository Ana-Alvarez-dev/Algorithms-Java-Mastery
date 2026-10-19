package org.anaalvarezdev.algorithms.hashtables;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DuplicateDetectorTest {
    @Test void shouldMatchPairwiseOracleForAllSmallArraysWithoutMutation() {
        SmallArrays.forEach(values->{
            int[] original=values.clone();boolean repeated=false;
            for(int i=0;i<values.length;i++) { for(int j=i+1;j<values.length;j++) { repeated|=values[i]==values[j]; } }
            assertThat(DuplicateDetector.hasDuplicates(values)).isEqualTo(repeated);assertThat(values).containsExactly(original);
        });
    }
    @Test void shouldHandleExtremeValues() {
        assertThat(DuplicateDetector.hasDuplicates(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE})).isFalse();
        assertThat(DuplicateDetector.hasDuplicates(new int[]{Integer.MIN_VALUE,0,Integer.MIN_VALUE})).isTrue();
    }
    @Test void shouldRejectNull() {
        assertThatThrownBy(()->DuplicateDetector.hasDuplicates(null)).isInstanceOf(NullPointerException.class);
    }
}
