package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PrefixSumTest {
    @Test
    void shouldBuildPrefixSumArray() {
        int[] values = {10, 20, 30, 40};

        long[] prefix = PrefixSum.build(values);

        assertThat(prefix)
                .containsExactly(
                        0L,
                        10L,
                        30L,
                        60L,
                        100L
                );
    }

    @Test
    void shouldReturnSumOfCompleteRange() {
        int[] values = {10, 20, 30, 40};
        long[] prefix = PrefixSum.build(values);

        long result =
                PrefixSum.rangeSum(prefix, 0, 3);

        assertThat(result).isEqualTo(100L);
    }

    @Test
    void shouldReturnSumOfMiddleRange() {
        int[] values = {10, 20, 30, 40};
        long[] prefix = PrefixSum.build(values);

        long result =
                PrefixSum.rangeSum(prefix, 1, 2);

        assertThat(result).isEqualTo(50L);
    }

    @Test
    void shouldReturnSingleElementRange() {
        int[] values = {10, 20, 30};

        long[] prefix = PrefixSum.build(values);

        long result =
                PrefixSum.rangeSum(prefix, 1, 1);

        assertThat(result).isEqualTo(20L);
    }

    @Test
    void shouldHandleNegativeValues() {
        int[] values = {-10, 20, -5, 30};

        long[] prefix = PrefixSum.build(values);

        assertThat(prefix)
                .containsExactly(
                        0L,
                        -10L,
                        10L,
                        5L,
                        35L
                );

        assertThat(
                PrefixSum.rangeSum(prefix, 1, 3)
        ).isEqualTo(45L);
    }

    @Test
    void shouldBuildPrefixForEmptyArray() {
        int[] values = {};

        long[] prefix = PrefixSum.build(values);

        assertThat(prefix)
                .containsExactly(0L);
    }

    @Test
    void shouldNotModifyInputArray() {
        int[] values = {10, 20, 30};

        PrefixSum.build(values);

        assertThat(values)
                .containsExactly(10, 20, 30);
    }

    @Test
    void shouldThrowExceptionWhenInputArrayIsNull() {
        assertThatThrownBy(() ->
                PrefixSum.build(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldThrowExceptionWhenLeftIndexIsNegative() {
        long[] prefix =
                PrefixSum.build(
                        new int[]{10, 20, 30}
                );

        assertThatThrownBy(() ->
                PrefixSum.rangeSum(prefix, -1, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenRightIndexExceedsArray() {
        long[] prefix =
                PrefixSum.build(
                        new int[]{10, 20, 30}
                );

        assertThatThrownBy(() ->
                PrefixSum.rangeSum(prefix, 0, 3))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void shouldThrowExceptionWhenLeftIsGreaterThanRight() {
        long[] prefix =
                PrefixSum.build(
                        new int[]{10, 20, 30}
                );

        assertThatThrownBy(() ->
                PrefixSum.rangeSum(prefix, 2, 1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
}
