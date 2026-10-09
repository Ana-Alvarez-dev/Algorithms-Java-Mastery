package org.anaalvarezdev.algorithms.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.SplittableRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Common observable contract, inherited by each concrete algorithm test. */
abstract class SortingContractTest {

    protected abstract void sort(int[] values);

    @Test
    void shouldAcceptEmptyArray() {
        int[] values = {};
        sort(values);
        assertThat(values).isEmpty();
    }

    @Test
    void shouldPreserveSingleElement() {
        int[] values = {42};
        sort(values);
        assertThat(values).containsExactly(42);
    }

    @Test
    void shouldSortTwoElementsInBothOrders() {
        assertMatchesReference(new int[]{2, 1});
        assertMatchesReference(new int[]{1, 2});
        assertMatchesReference(new int[]{2, 2});
    }

    @Test
    void shouldSortUnorderedInputInTheSuppliedArray() {
        int[] values = {29, 10, 14, 37, 13};
        sort(values);
        assertThat(values).containsExactly(10, 13, 14, 29, 37);
    }

    @Test
    void shouldPreserveDuplicateMultiplicities() {
        int[] values = {4, 2, 4, 1, 2, 4};
        sort(values);
        assertThat(values).containsExactly(1, 2, 2, 4, 4, 4);
    }

    @Test
    void shouldHandleNegativeAndExtremeIntegersWithoutOverflow() {
        int[] values = {Integer.MAX_VALUE, -1, 0, Integer.MIN_VALUE,
                Integer.MAX_VALUE, Integer.MIN_VALUE};
        sort(values);
        assertThat(values).containsExactly(Integer.MIN_VALUE, Integer.MIN_VALUE,
                -1, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Test
    void shouldPreserveAlreadySortedInput() {
        assertMatchesReference(new int[]{-10, -3, 0, 2, 2, 8});
    }

    @Test
    void shouldSortReverseInput() {
        assertMatchesReference(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0});
    }

    @Test
    void shouldHandleAllEqualValues() {
        int[] values = new int[257];
        Arrays.fill(values, 7);
        assertMatchesReference(values);
    }

    @Test
    void shouldBeIdempotent() {
        int[] values = {5, 0, -3, 5, 1, Integer.MIN_VALUE};
        sort(values);
        int[] onceSorted = values.clone();
        sort(values);
        assertThat(values).containsExactly(onceSorted);
    }

    @Test
    void shouldRejectNullInput() {
        assertThatThrownBy(() -> sort(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("values must not be null");
    }

    @Test
    void shouldMatchReferenceAcrossSeededInputsAndBoundaryLengths() {
        SplittableRandom random = new SplittableRandom(20261009L);
        int[] lengths = {0, 1, 2, 3, 7, 8, 9, 15, 16, 17, 31, 32, 33,
                63, 64, 65, 127, 128, 129, 255, 256, 257};
        for (int length : lengths) {
            for (int trial = 0; trial < 5; trial++) {
                int[] values = new int[length];
                for (int index = 0; index < length; index++) {
                    values[index] = trial % 2 == 0
                            ? random.nextInt() : random.nextInt(-8, 9);
                }
                assertMatchesReference(values);
            }
        }
    }

    @Test
    void shouldSortEverySmallArrayOverThreeKeys() {
        // Exhaustive duplicate/boundary coverage: 1,093 sequences, lengths 0..6.
        int combinations = 1;
        for (int length = 0; length <= 6; length++) {
            for (int encoding = 0; encoding < combinations; encoding++) {
                int[] values = new int[length];
                int remaining = encoding;
                for (int index = 0; index < length; index++) {
                    values[index] = remaining % 3 - 1;
                    remaining /= 3;
                }
                assertMatchesReference(values);
            }
            combinations *= 3;
        }
    }

    protected final void assertMatchesReference(int[] values) {
        int[] expected = values.clone();
        Arrays.sort(expected);
        sort(values);
        assertThat(values).isSorted().containsExactly(expected);
    }
}
