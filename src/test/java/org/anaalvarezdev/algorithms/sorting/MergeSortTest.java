package org.anaalvarezdev.algorithms.sorting;

import org.junit.jupiter.api.Test;

import java.util.SplittableRandom;

class MergeSortTest extends SortingContractTest {

    @Override
    protected void sort(int[] values) {
        MergeSort.sort(values);
    }

    @Test
    void shouldMergeLargeOddLengthInput() {
        int[] values = new SplittableRandom(20261009L).ints(100_001).toArray();
        assertMatchesReference(values);
    }
}
