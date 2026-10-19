package org.anaalvarezdev.algorithms.trees;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KSmallestTest {
    @Test void shouldRejectNullAndInvalidKWithoutMutation() {
        assertThatThrownBy(() -> KSmallest.select(null,0)).isInstanceOf(NullPointerException.class);
        int[] values = {3,1};
        assertThatThrownBy(() -> KSmallest.select(values,-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> KSmallest.select(values,3)).isInstanceOf(IllegalArgumentException.class);
        assertThat(values).containsExactly(3,1);
    }
    @Test void shouldHandleEmptyZeroKDuplicatesAndExtremes() {
        assertThat(KSmallest.select(new int[0],0)).isEmpty();
        assertThat(KSmallest.select(new int[]{3,2},0)).isEmpty();
        assertThat(KSmallest.select(new int[]{3,1,1,2},3)).containsExactly(1,1,2);
        assertThat(KSmallest.select(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,0},2)).containsExactly(Integer.MIN_VALUE,0);
    }
    @Test void shouldMatchSortedPrefixesForEverySmallArrayAndK() {
        for (int[] values : SmallInputs.arrays()) {
            int[] before = values.clone(); int[] sorted = values.clone(); Arrays.sort(sorted);
            for (int k = 0; k <= values.length; k++) {
                assertThat(KSmallest.select(values,k)).containsExactly(Arrays.copyOf(sorted,k));
                assertThat(values).containsExactly(before);
            }
        }
    }
    @Test void shouldReturnIndependentResults() {
        int[] input = {9,2,1}; int[] result = KSmallest.select(input,3); result[0] = 100;
        assertThat(input).containsExactly(9,2,1); assertThat(KSmallest.select(input,1)).containsExactly(1);
    }
}
