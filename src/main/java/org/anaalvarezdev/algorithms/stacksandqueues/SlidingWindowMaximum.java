package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.Objects;

/** Monotonic deque of candidate indices, expiring old and dominated values. */
public final class SlidingWindowMaximum {
    private SlidingWindowMaximum() { }

    /**
     * Returns maxima of every complete window without modifying input.
     * Each index enters/leaves once: Theta(n) time, O(k) auxiliary space.
     * @param values non-null array with at least k elements
     * @param windowSize k, from 1 to array length
     * @return n-k+1 window maxima
     * @throws NullPointerException for null input
     * @throws IllegalArgumentException when k is not in [1,n]
     */
    public static int[] maxima(int[] values, int windowSize) {
        Objects.requireNonNull(values, "values must not be null");
        if (windowSize < 1 || windowSize > values.length) {
            throw new IllegalArgumentException("window size must be in [1, array length]");
        }
        int[] maxima = new int[values.length - windowSize + 1];
        DoubleEndedQueue<Integer> candidates = new BoundedArrayDeque<>(windowSize);
        for (int index = 0; index < values.length; index++) {
            if (!candidates.isEmpty() && candidates.getFirst() <= index - windowSize) {
                candidates.removeFirst();
            }
            while (!candidates.isEmpty() && values[candidates.getLast()] <= values[index]) {
                candidates.removeLast();
            }
            candidates.addLast(index);
            if (index >= windowSize - 1) {
                maxima[index - windowSize + 1] = values[candidates.getFirst()];
            }
        }
        return maxima;
    }
}
