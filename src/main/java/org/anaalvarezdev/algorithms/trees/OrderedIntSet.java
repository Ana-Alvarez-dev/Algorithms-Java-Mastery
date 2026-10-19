package org.anaalvarezdev.algorithms.trees;

import java.util.OptionalInt;

/**
 * Selected ordered-set contract for all int keys, not java.util.Set.
 * Keys are unique; duplicate add and absent remove return false without mutation.
 * Empty extrema and absent inclusive neighbours use OptionalInt.empty().
 * Height counts edges: empty -1, leaf 0. Snapshots are independent sorted arrays.
 * Implementations are mutable and not thread-safe; no iterator is exposed.
 */
public interface OrderedIntSet {
    boolean add(int value);
    boolean remove(int value);
    boolean contains(int value);
    OptionalInt first();
    OptionalInt last();
    OptionalInt floor(int value);
    OptionalInt ceiling(int value);
    int size();
    int height();
    int[] toArray();
    void clear();
    default boolean isEmpty() { return size() == 0; }
}
