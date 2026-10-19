package org.anaalvarezdev.algorithms.hashtables;

import java.util.Map;

/**
 * Selected non-null associative API, with stable equals/hashCode keys.
 * No iteration order or thread safety is promised; this is not java.util.Map.
 * @param <K> key type
 * @param <V> value type
 */
public interface HashTable<K, V> {
    /** Inserts/replaces, returning the old value or null for absence; rejects nulls. */
    V put(K key, V value);
    /** Returns the value or null for absence; rejects a null key. */
    V get(K key);
    /** Removes a mapping, returning its value or null; rejects a null key. */
    V remove(K key);
    /** Returns the cached live mapping count. */
    int size();
    /** Returns retained bucket/slot capacity. */
    int capacity();
    /** Empties the table and resets markers, retaining capacity. */
    void clear();
    /** Returns an independent mutable shallow snapshot, with no ordering promise. */
    Map<K, V> toMap();
    /** Returns whether a non-null key is present. */
    default boolean containsKey(K key) { return get(key) != null; }
    /** Returns whether no live mappings exist. */
    default boolean isEmpty() { return size() == 0; }
}
