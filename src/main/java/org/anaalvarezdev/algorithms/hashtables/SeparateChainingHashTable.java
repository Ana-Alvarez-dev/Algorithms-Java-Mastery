package org.anaalvarezdev.algorithms.hashtables;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Private singly linked buckets, cached hashes and maximum live load 0.75.
 * Expected amortised constant basic operations require well-distributed hashes;
 * concentrated collisions give linear searches. Rehash relinks each node once.
 * @param <K> stable key type
 * @param <V> non-null value type
 */
public final class SeparateChainingHashTable<K, V> implements HashTable<K, V> {
    private Node<K, V>[] buckets;
    private int size;

    /** Creates an empty table with sixteen buckets. */
    public SeparateChainingHashTable() { this(16); }
    /** Rounds a positive requested capacity up to a supported power of two. */
    public SeparateChainingHashTable(int initialCapacity) {
        buckets = newBuckets(Hashing.capacity(initialCapacity));
    }

    @Override
    public V put(K key, V value) {
        int hash = Hashing.hash(key);
        Objects.requireNonNull(value, "value must not be null");
        for (Node<K, V> node = buckets[index(hash)]; node != null; node = node.next) {
            if (node.hash == hash && key.equals(node.key)) {
                V old = node.value; node.value = value; return old;
            }
        }
        if (size + 1 > buckets.length - buckets.length / 4) { resize(); }
        int index = index(hash);
        buckets[index] = new Node<>(key, value, hash, buckets[index]);
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        int hash = Hashing.hash(key);
        for (Node<K, V> node = buckets[index(hash)]; node != null; node = node.next) {
            if (node.hash == hash && key.equals(node.key)) { return node.value; }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int hash = Hashing.hash(key);
        int index = index(hash);
        Node<K, V> previous = null;
        for (Node<K, V> node = buckets[index]; node != null; node = node.next) {
            if (node.hash == hash && key.equals(node.key)) {
                if (previous == null) { buckets[index] = node.next; } else { previous.next = node.next; }
                size--;
                return node.value;
            }
            previous = node;
        }
        return null;
    }

    @Override public int size() { return size; }
    @Override public int capacity() { return buckets.length; }
    @Override public void clear() { Arrays.fill(buckets, null); size = 0; }

    @Override
    public Map<K, V> toMap() {
        Map<K, V> snapshot = HashMap.newHashMap(size);
        for (Node<K, V> first : buckets) {
            for (Node<K, V> node = first; node != null; node = node.next) { snapshot.put(node.key, node.value); }
        }
        return snapshot;
    }

    private int index(int hash) { return hash & (buckets.length - 1); }

    private void resize() {
        Node<K, V>[] expanded = newBuckets(Hashing.grow(buckets.length));
        for (Node<K, V> first : buckets) {
            Node<K, V> node = first;
            while (node != null) {
                Node<K, V> next = node.next;
                int index = node.hash & (expanded.length - 1);
                node.next = expanded[index]; expanded[index] = node; node = next;
            }
        }
        buckets = expanded;
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] newBuckets(int capacity) {
        return (Node<K, V>[]) new Node<?, ?>[capacity];
    }

    boolean hasValidStructure() {
        Set<K> seen = new HashSet<>();
        int count = 0;
        for (int bucket = 0; bucket < buckets.length; bucket++) {
            for (Node<K, V> node = buckets[bucket]; node != null; node = node.next) {
                if (++count > size || node.key == null || node.value == null || !seen.add(node.key)
                        || index(node.hash) != bucket || Hashing.hash(node.key) != node.hash) { return false; }
            }
        }
        return count == size && size >= 0 && size <= buckets.length - buckets.length / 4;
    }

    private static final class Node<K, V> {
        private final K key;
        private V value;
        private final int hash;
        private Node<K, V> next;
        private Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key; this.value = value; this.hash = hash; this.next = next;
        }
    }
}
