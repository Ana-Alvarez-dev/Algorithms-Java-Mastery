package org.anaalvarezdev.algorithms.sorting;

class SelectionSortTest extends SortingContractTest {

    @Override
    protected void sort(int[] values) {
        SelectionSort.sort(values);
    }
}
