package org.anaalvarezdev.algorithms.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.SplittableRandom;
import java.util.stream.IntStream;

class QuickSortTest extends SortingContractTest {

    @Override
    protected void sort(int[] values) {
        QuickSort.sort(values);
    }

    @Test
    void shouldHandleLargeSortedAndReverseInputs() {
        assertMatchesReference(IntStream.range(0, 100_000).toArray());
        assertMatchesReference(IntStream.range(0, 100_000)
                .map(index -> 99_999 - index).toArray());
    }

    @Test
    void shouldHandleLargeEqualInputWithoutStalling() {
        int[] values = new int[100_000];
        Arrays.fill(values, 7);
        assertMatchesReference(values);
    }

    @Test
    void shouldHandleLargeRandomAndRepeatedKeys() {
        SplittableRandom random = new SplittableRandom(20261009L);
        assertMatchesReference(random.ints(100_000).toArray());
        assertMatchesReference(random.ints(100_000, -8, 9).toArray());
    }
}
