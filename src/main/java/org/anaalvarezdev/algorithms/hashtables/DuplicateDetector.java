package org.anaalvarezdev.algorithms.hashtables;

import java.util.Objects;

/** Detects repeated integers with the manual probing table. */
public final class DuplicateDetector {
    private DuplicateDetector() { }
    /**
     * Preserves input; empty/singleton arrays have no duplicates.
     * Expected O(n) time, O(n) auxiliary space; worst O(n²) under collisions.
     * @param values non-null input
     * @return whether any value repeats
     * @throws NullPointerException for null input
     */
    public static boolean hasDuplicates(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        HashTable<Integer, Boolean> seen = new LinearProbingHashTable<>();
        for (int value : values) {
            if (seen.containsKey(value)) { return true; }
            seen.put(value, true);
        }
        return false;
    }
}
