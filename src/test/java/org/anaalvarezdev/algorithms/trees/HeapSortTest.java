package org.anaalvarezdev.algorithms.trees;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HeapSortTest {
    @Test void shouldRejectNull() { assertThatThrownBy(() -> HeapSort.sort(null)).isInstanceOf(NullPointerException.class); }
    @Test void shouldMatchArraysSortForEverySmallArray() {
        for (int[] input : SmallInputs.arrays()) {
            int[] expected = input.clone(); Arrays.sort(expected); HeapSort.sort(input);
            assertThat(input).containsExactly(expected);
        }
    }
    @Test void shouldHandleExtremesSortedReverseAndAllEqualValues() {
        for (int[] values : new int[][]{{Integer.MAX_VALUE,0,Integer.MIN_VALUE,-1},{1,2,3,4},{4,3,2,1},{2,2,2,2}}) {
            int[] expected = values.clone(); Arrays.sort(expected); HeapSort.sort(values);
            assertThat(values).containsExactly(expected);
        }
    }
    @Test void shouldMatchArraysSortForSeededLargerInputs() {
        var random = new SplittableRandom(20261019);
        for (int length : new int[]{7,8,31,32,1000,10000}) {
            int[] values = random.ints(length).toArray(); int[] expected = values.clone();
            Arrays.sort(expected); HeapSort.sort(values); assertThat(values).containsExactly(expected);
        }
    }
}
