package org.anaalvarezdev.algorithms.hashtables;

/** Finds the first index whose integer value occurs exactly once. */
public final class FirstUniqueElement {
    private FirstUniqueElement() { }
    /**
     * Counts then scans in input order, preserving input. Empty gives -1.
     * Expected O(n) time/O(n) space; worst O(n²) with concentrated collisions.
     * @param values non-null array
     * @return first unique value's index, or -1
     * @throws NullPointerException for null input
     */
    public static int firstIndex(int[] values) {
        HashTable<Integer, Integer> counts = FrequencyCounter.table(values);
        for (int index = 0; index < values.length; index++) {
            if (counts.get(values[index]) == 1) { return index; }
        }
        return -1;
    }
}
