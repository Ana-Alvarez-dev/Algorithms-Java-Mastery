package org.anaalvarezdev.algorithms.sorting;

class InsertionSortTest extends SortingContractTest {

    @Override
    protected void sort(int[] values) {
        InsertionSort.sort(values);
    }
}
