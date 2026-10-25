package org.anaalvarezdev.algorithms.recursion;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArraySumTest {
    @Test void shouldHandleEmptySingletonExtremesAndBeyondIntSums() {
        for (int[] values : new int[][]{{},{7},{Integer.MIN_VALUE},{Integer.MAX_VALUE,Integer.MAX_VALUE},{Integer.MIN_VALUE,Integer.MIN_VALUE},{Integer.MIN_VALUE,Integer.MAX_VALUE}}) {
            long expected = Arrays.stream(values).asLongStream().sum();
            assertThat(ArraySum.recursive(values)).isEqualTo(expected); assertThat(ArraySum.iterative(values)).isEqualTo(expected);
        }
    }
    @Test void shouldMatchEverySmallArraySliceAndPreserveInput() {
        var inputs = SmallArrays.all(); assertThat(inputs).hasSize(1093);
        for (int[] values : inputs) {
            int[] before = values.clone();
            for (int from = 0; from <= values.length; from++) for (int to = from; to <= values.length; to++) {
                long expected = Arrays.stream(values,from,to).asLongStream().sum();
                assertThat(ArraySum.recursive(values,from,to)).isEqualTo(expected);
                assertThat(ArraySum.iterative(values,from,to)).isEqualTo(expected);
            }
            assertThat(values).containsExactly(before);
        }
    }
    @Test void shouldValidateNullAndHalfOpenBounds() {
        assertThatThrownBy(() -> ArraySum.recursive(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> ArraySum.iterative(null,0,0)).isInstanceOf(NullPointerException.class);
        int[] values = {1,2,3};
        for (int[] range : new int[][]{{-1,0},{0,4},{2,1},{4,4}}) {
            assertThatThrownBy(() -> ArraySum.recursive(values,range[0],range[1])).isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> ArraySum.iterative(values,range[0],range[1])).isInstanceOf(IndexOutOfBoundsException.class);
        }
        assertThat(ArraySum.recursive(values,3,3)).isZero(); assertThat(values).containsExactly(1,2,3);
    }
    @Test void shouldProcessLargeInputsWithoutLinearCallDepth() {
        int[] values = new int[200000]; Arrays.fill(values,Integer.MAX_VALUE);
        long expected = (long)values.length*Integer.MAX_VALUE;
        assertThat(ArraySum.recursive(values)).isEqualTo(expected); assertThat(ArraySum.iterative(values)).isEqualTo(expected);
    }
    @Test void shouldMatchSeededFullIntSlices() {
        var random = new SplittableRandom(20261025);
        for (int trial = 0; trial < 128; trial++) {
            int[] values = random.ints(1000).toArray(); int from = random.nextInt(1001); int to = random.nextInt(from,1001);
            long expected = Arrays.stream(values,from,to).asLongStream().sum();
            assertThat(ArraySum.recursive(values,from,to)).isEqualTo(expected); assertThat(ArraySum.iterative(values,from,to)).isEqualTo(expected);
        }
    }
}
