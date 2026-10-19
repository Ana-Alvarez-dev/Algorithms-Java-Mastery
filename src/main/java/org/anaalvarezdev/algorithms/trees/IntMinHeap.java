package org.anaalvarezdev.algorithms.trees;

import java.util.Arrays;
import java.util.Objects;
import java.util.OptionalInt;

/**
 * Array min-heap, allowing duplicates and every int value. Empty peek/poll return
 * OptionalInt.empty(); snapshots are independent heap-order arrays, not sorted.
 * Offer sifts in O(log(n+1)), but a growing offer copies O(n) slots. Growth
 * contributes O(1) amortised copying per offer. Poll is O(log(n+1)); peek O(1).
 * Mutable, not thread-safe; no iterator or arbitrary-key removal is exposed.
 */
public final class IntMinHeap {
    private int[] values;
    private int size;
    public IntMinHeap() { this(16); }
    public IntMinHeap(int capacity) { values = new int[IntArrayCapacity.initial(capacity)]; }
    /** Copies the non-null input and builds bottom-up in Theta(n) time. */
    public IntMinHeap(int[] input) {
        Objects.requireNonNull(input, "input must not be null");
        values = Arrays.copyOf(input, IntArrayCapacity.initial(input.length)); size = input.length;
        for (int parent = size / 2 - 1; parent >= 0; parent--) { siftDown(parent); }
    }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public int capacity() { return values.length; }
    public OptionalInt peek() { return size == 0 ? OptionalInt.empty() : OptionalInt.of(values[0]); }
    public int[] toArray() { return Arrays.copyOf(values, size); }
    /** Retains capacity; unused primitive slots carry no observable values. */
    public void clear() { size = 0; }

    public void offer(int value) {
        if (size == values.length) { values = Arrays.copyOf(values, IntArrayCapacity.grow(values.length)); }
        int slot = size++;
        while (slot > 0) {
            int parent = (slot - 1) / 2;
            if (values[parent] <= value) { break; }
            values[slot] = values[parent]; slot = parent;
        }
        values[slot] = value;
    }

    public OptionalInt poll() {
        if (size == 0) { return OptionalInt.empty(); }
        int result = values[0]; int last = values[--size];
        if (size > 0) { values[0] = last; siftDown(0); }
        return OptionalInt.of(result);
    }

    private void siftDown(int slot) {
        int value = values[slot];
        while (slot < size / 2) {
            int child = slot * 2 + 1;
            if (child + 1 < size && values[child + 1] < values[child]) { child++; }
            if (value <= values[child]) { break; }
            values[slot] = values[child]; slot = child;
        }
        values[slot] = value;
    }

    boolean hasValidStructure() {
        if (size < 0 || size > values.length || values.length < 1) { return false; }
        for (int child = 1; child < size; child++) {
            if (values[(child - 1) / 2] > values[child]) { return false; }
        }
        return true;
    }
}
