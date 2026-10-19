package org.anaalvarezdev.algorithms.trees;

/** Bounded capacity arithmetic; physical allocation can fail before this limit. */
final class IntArrayCapacity {
    static final int MAXIMUM = 1 << 30;
    private IntArrayCapacity() { }
    static int initial(int requested) {
        if (requested < 0 || requested > MAXIMUM) { throw new IllegalArgumentException("capacity outside [0, 2^30]"); }
        return Math.max(1, requested);
    }
    static int grow(int capacity) {
        if (capacity < 1 || capacity > MAXIMUM) { throw new IllegalArgumentException("invalid current capacity"); }
        if (capacity == MAXIMUM) { throw new IllegalStateException("maximum capacity reached"); }
        return capacity > MAXIMUM / 2 ? MAXIMUM : capacity * 2;
    }
}
