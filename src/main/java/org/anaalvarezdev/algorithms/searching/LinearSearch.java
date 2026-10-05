package org.anaalvarezdev.algorithms.searching;
/**
     * Searches for the target value in the given array using linear search.
     *
     * If the target occurs more than once, this method returns the index
     * of its first occurrence.
     *
     * @param array the integer array to search
     * @param target the value to locate
     * @return the index of the first occurrence of the target,
     *         or -1 if the target is not present
     * @throws NullPointerException if the array is null
     */
    public static int search(int[] array, int target) {

        for (int index = 0; index < array.length; index++) {

            if (array[index] == target) {
                return index;
            }
        }

        return -1;
    }
}
