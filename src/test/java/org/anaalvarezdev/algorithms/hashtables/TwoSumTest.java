package org.anaalvarezdev.algorithms.hashtables;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TwoSumTest {
    @Test void shouldMatchOrderedQuadraticOracleForAllSmallArraysAndTargets() {
        SmallArrays.forEach(values->{
            int[] original=values.clone();
            for(int target=-3;target<=3;target++) {
                int[] expected=new int[0];
                outer:for(int right=0;right<values.length;right++) {
                    for(int left=0;left<right;left++) {
                        if((long)values[left]+values[right]==target) { expected=new int[]{left,right};break outer; }
                    }
                }
                assertThat(TwoSum.findIndices(values,target)).containsExactly(expected);
                assertThat(values).containsExactly(original);
            }
        });
    }
    @Test void shouldRespectEarliestCompletionEarliestLeftAndDistinctIndices() {
        assertThat(TwoSum.findIndices(new int[]{1,1,2},3)).containsExactly(0,2);
        assertThat(TwoSum.findIndices(new int[]{3,3},6)).containsExactly(0,1);
        assertThat(TwoSum.findIndices(new int[]{3},6)).isEmpty();
    }
    @Test void shouldUseMathematicalSumsRatherThanOverflowingIntArithmetic() {
        assertThat(TwoSum.findIndices(new int[]{Integer.MAX_VALUE,1},Integer.MIN_VALUE)).isEmpty();
        assertThat(TwoSum.findIndices(new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE},0)).isEmpty();
        assertThat(TwoSum.findIndices(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE},-2)).isEmpty();
        assertThat(TwoSum.findIndices(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE},-1)).containsExactly(0,1);
    }
    @Test void shouldRejectNull() {
        assertThatThrownBy(()->TwoSum.findIndices(null,0)).isInstanceOf(NullPointerException.class);
    }
}
