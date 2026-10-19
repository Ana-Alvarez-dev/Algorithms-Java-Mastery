package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.Arrays;
import java.util.Objects;

/** Nearest strictly greater right-hand indices using a monotonic pending stack. */
public final class NextGreaterElement {
    private NextGreaterElement() { }

    /**
     * Preserves input and returns -1 where no greater element exists.
     * Each index enters/leaves once: Theta(n) time, O(n) auxiliary space.
     * @param values non-null integer array
     * @return independent array of right-hand indices; empty for empty input
     * @throws NullPointerException for null input
     */
    public static int[] indices(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        int[] result = new int[values.length];
        Arrays.fill(result, -1);
        Stack<Integer> pending = new ArrayStack<>();
        for (int index = 0; index < values.length; index++) {
            while (!pending.isEmpty() && values[pending.peek()] < values[index]) {
                result[pending.pop()] = index;
            }
            pending.push(index);
        }
        return result;
    }
}
