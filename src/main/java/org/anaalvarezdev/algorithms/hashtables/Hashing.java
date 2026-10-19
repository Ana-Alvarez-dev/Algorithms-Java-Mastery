package org.anaalvarezdev.algorithms.hashtables;

import java.util.Objects;

/** Capacity arithmetic and deterministic spreading; not universal hashing. */
final class Hashing {
    static final int MAXIMUM_CAPACITY = 1 << 30;
    private Hashing() { }

    static int capacity(int requested) {
        if (requested < 1 || requested > MAXIMUM_CAPACITY) {
            throw new IllegalArgumentException("capacity must be in [1, 2^30]");
        }
        int capacity = 4;
        while (capacity < requested) { capacity <<= 1; }
        return capacity;
    }

    static int grow(int capacity) {
        if (capacity < 4 || capacity > MAXIMUM_CAPACITY || (capacity & (capacity - 1)) != 0) {
            throw new IllegalArgumentException("capacity must be a supported power of two");
        }
        if (capacity == MAXIMUM_CAPACITY) { throw new IllegalStateException("maximum capacity reached"); }
        return capacity << 1;
    }

    static int hash(Object key) {
        int hash = Objects.requireNonNull(key, "key must not be null").hashCode();
        return hash ^ (hash >>> 16);
    }
}
