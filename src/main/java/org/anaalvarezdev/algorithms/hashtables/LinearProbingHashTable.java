package org.anaalvarezdev.algorithms.hashtables;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Linear probes with explicit EMPTY/OCCUPIED/DELETED states and cached hashes.
 * Live load stays at most one half; used-slot load at most three quarters.
 * Replacement searches beyond tombstones; cleanup is separate from live growth.
 * Expected constant costs require hashing assumptions; a rebuild can be quadratic.
 * @param <K> stable key type
 * @param <V> non-null value type
 */
public final class LinearProbingHashTable<K, V> implements HashTable<K, V> {
    private static final byte EMPTY = 0;
    private static final byte OCCUPIED = 1;
    private static final byte DELETED = 2;
    private Object[] keys;
    private Object[] values;
    private int[] hashes;
    private byte[] states;
    private int size;
    private int used;

    /** Creates an empty table with sixteen slots. */
    public LinearProbingHashTable() { this(16); }
    /** Rounds a positive requested capacity up to a supported power of two. */
    public LinearProbingHashTable(int initialCapacity) {
        int capacity = Hashing.capacity(initialCapacity);
        keys = new Object[capacity]; values = new Object[capacity];
        hashes = new int[capacity]; states = new byte[capacity];
    }

    @Override
    public V put(K key, V value) {
        int hash = Hashing.hash(key);
        Objects.requireNonNull(value, "value must not be null");
        int found = find(key, hash);
        if (found >= 0) { V old = valueAt(found); values[found] = value; return old; }
        if (size + 1 > capacity() / 2) {
            rebuild(Hashing.grow(capacity()));
        } else if (used >= capacity() - capacity() / 4) {
            rebuild(capacity());
        }
        int slot = reusable(hash, states);
        if (states[slot] == EMPTY) { used++; }
        keys[slot] = key; values[slot] = value; hashes[slot] = hash;
        states[slot] = OCCUPIED; size++;
        return null;
    }

    @Override
    public V get(K key) {
        int found = find(key, Hashing.hash(key));
        return found < 0 ? null : valueAt(found);
    }

    @Override
    public V remove(K key) {
        int found = find(key, Hashing.hash(key));
        if (found < 0) { return null; }
        V old = valueAt(found);
        keys[found] = null; values[found] = null; hashes[found] = 0;
        states[found] = DELETED; size--;
        return old;
    }

    @Override public int size() { return size; }
    @Override public int capacity() { return states.length; }
    @Override
    public void clear() {
        Arrays.fill(keys, null); Arrays.fill(values, null);
        Arrays.fill(hashes, 0); Arrays.fill(states, EMPTY); size = 0; used = 0;
    }

    @Override
    public Map<K, V> toMap() {
        Map<K, V> snapshot = HashMap.newHashMap(size);
        for (int slot = 0; slot < capacity(); slot++) {
            if (states[slot] == OCCUPIED) { snapshot.put(keyAt(slot), valueAt(slot)); }
        }
        return snapshot;
    }

    private int find(K key, int hash) {
        if (size == 0) { return -1; }
        int slot = hash & (capacity() - 1);
        for (int probes = 0; probes < capacity(); probes++) {
            if (states[slot] == EMPTY) { return -1; }
            if (states[slot] == OCCUPIED && hashes[slot] == hash && key.equals(keys[slot])) { return slot; }
            slot = (slot + 1) & (capacity() - 1);
        }
        return -1;
    }

    private static int reusable(int hash, byte[] states) {
        int slot = hash & (states.length - 1);
        for (int probes = 0; probes < states.length; probes++) {
            if (states[slot] != OCCUPIED) { return slot; }
            slot = (slot + 1) & (states.length - 1);
        }
        throw new IllegalStateException("no free slot");
    }

    private void rebuild(int capacity) {
        Object[] newKeys = new Object[capacity]; Object[] newValues = new Object[capacity];
        int[] newHashes = new int[capacity]; byte[] newStates = new byte[capacity];
        for (int old = 0; old < states.length; old++) {
            if (states[old] == OCCUPIED) {
                int slot = reusable(hashes[old], newStates);
                newKeys[slot] = keys[old]; newValues[slot] = values[old];
                newHashes[slot] = hashes[old]; newStates[slot] = OCCUPIED;
            }
        }
        keys = newKeys; values = newValues; hashes = newHashes; states = newStates; used = size;
    }

    @SuppressWarnings("unchecked") private K keyAt(int slot) { return (K) keys[slot]; }
    @SuppressWarnings("unchecked") private V valueAt(int slot) { return (V) values[slot]; }

    int deletedSlots() { return used - size; }

    boolean hasValidStructure() {
        int live = 0; int nonempty = 0;
        for (int slot = 0; slot < capacity(); slot++) {
            if (states[slot] == OCCUPIED) {
                live++; nonempty++;
                if (keys[slot] == null || values[slot] == null || Hashing.hash(keys[slot]) != hashes[slot]
                        || find(keyAt(slot), hashes[slot]) != slot) { return false; }
            } else {
                if (states[slot] == DELETED) { nonempty++; } else if (states[slot] != EMPTY) { return false; }
                if (keys[slot] != null || values[slot] != null || hashes[slot] != 0) { return false; }
            }
        }
        return live == size && nonempty == used && size >= 0 && size <= capacity() / 2
                && used <= capacity() - capacity() / 4;
    }
}
