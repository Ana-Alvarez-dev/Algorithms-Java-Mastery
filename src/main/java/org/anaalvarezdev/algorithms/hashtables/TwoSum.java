package org.anaalvarezdev.algorithms.hashtables;

import java.util.Objects;

/** Two distinct indices whose mathematical integer sum equals the target. */
public final class TwoSum {
    private TwoSum() { }
    /**
     * Returns the earliest completing right index and earliest matching left,
     * as [left,right], or an empty array. Input is preserved and overflow avoided.
     * Expected O(n) time/O(n) space; adversarial hashing may give O(n²) time.
     * @param values non-null array
     * @param target required mathematical sum
     * @return two valid ordered indices, or an empty array
     * @throws NullPointerException for null input
     */
    public static int[] findIndices(int[] values, int target) {
        Objects.requireNonNull(values, "values must not be null");
        HashTable<Integer, Integer> earliest = new SeparateChainingHashTable<>();
        for (int right = 0; right < values.length; right++) {
            long complement = (long) target - values[right];
            if (complement >= Integer.MIN_VALUE && complement <= Integer.MAX_VALUE) {
                Integer left = earliest.get((int) complement);
                if (left != null) { return new int[]{left, right}; }
            }
            if (!earliest.containsKey(values[right])) { earliest.put(values[right], right); }
        }
        return new int[0];
    }
}
