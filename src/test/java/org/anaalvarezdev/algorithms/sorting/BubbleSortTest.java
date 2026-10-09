package org.anaalvarezdev.algorithms.sorting;

class BubbleSortTest extends SortingContractTest {

    @Override
    protected void sort(int[] values) {
        BubbleSort.sort(values);
    }
}
