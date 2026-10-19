package org.anaalvarezdev.algorithms.stacksandqueues;

/** Shared capacity arithmetic; allocation may still fail below this limit. */
final class ArrayCapacity {
    static final int MAXIMUM = Integer.MAX_VALUE - 8;

    private ArrayCapacity() { }

    static int requirePositive(int capacity) {
        if (capacity < 1 || capacity > MAXIMUM) {
            throw new IllegalArgumentException("capacity must be between 1 and " + MAXIMUM);
        }
        return capacity;
    }

    static int grow(int capacity) {
        requirePositive(capacity);
        if (capacity == MAXIMUM) {
            throw new OutOfMemoryError("maximum array capacity reached");
        }
        return (int) Math.min((long) capacity * 2, MAXIMUM);
    }
}
