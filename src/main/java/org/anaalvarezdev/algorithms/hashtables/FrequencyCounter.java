package org.anaalvarezdev.algorithms.hashtables;

import java.util.Map;
import java.util.Objects;

/** Integer frequencies using the manual chained table. */
public final class FrequencyCounter {
    private FrequencyCounter() { }
    /**
     * Preserves input; returns an independent mutable frequency map.
     * Expected O(n) time, O(n) space; adversarial hashing may give O(n²).
     * @param values non-null array; empty is valid
     * @return frequency of each distinct value, with no ordering guarantee
     * @throws NullPointerException for null input
     */
    public static Map<Integer, Integer> count(int[] values) {
        return table(values).toMap();
    }

    static HashTable<Integer, Integer> table(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        HashTable<Integer, Integer> counts = new SeparateChainingHashTable<>();
        for (int value : values) {
            Integer old = counts.get(value);
            counts.put(value, old == null ? 1 : old + 1);
        }
        return counts;
    }
}
