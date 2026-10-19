package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Size-counted growing ring, using every slot and never shifting on removal.
 * Enqueue is O(1) amortised, O(n) on growth; dequeue/peek are constant-time.
 * Growth preserves logical order even when the original data wrap around.
 * @param <E> element type
 */
public final class CircularArrayQueue<E> implements FifoQueue<E> {
    private Object[] elements;
    private int head;
    private int size;

    /** Creates an empty queue with capacity eight. */
    public CircularArrayQueue() { this(8); }
    /** Creates an empty queue; rejects nonpositive or unsupported capacity. */
    public CircularArrayQueue(int initialCapacity) {
        elements = new Object[ArrayCapacity.requirePositive(initialCapacity)];
    }

    @Override
    public void enqueue(E value) {
        Objects.requireNonNull(value, "value must not be null");
        if (size == elements.length) { grow(); }
        elements[index(size)] = value;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) { throw new NoSuchElementException("queue is empty"); }
        E value = valueAt(head);
        elements[head] = null;
        head = head == elements.length - 1 ? 0 : head + 1;
        if (--size == 0) { head = 0; }
        return value;
    }

    @Override
    public E peek() { return isEmpty() ? null : valueAt(head); }
    @Override
    public int size() { return size; }
    /** Returns retained capacity, not a fixed maximum. */
    public int capacity() { return elements.length; }
    @Override
    public void clear() { while (!isEmpty()) { dequeue(); } }

    @Override
    public List<E> toList() {
        List<E> values = new ArrayList<>(size);
        for (int offset = 0; offset < size; offset++) { values.add(valueAt(index(offset))); }
        return values;
    }

    private void grow() {
        Object[] expanded = new Object[ArrayCapacity.grow(elements.length)];
        for (int offset = 0; offset < size; offset++) { expanded[offset] = elements[index(offset)]; }
        elements = expanded;
        head = 0;
    }

    private int index(int offset) {
        int untilWrap = elements.length - head;
        return offset < untilWrap ? head + offset : offset - untilWrap;
    }

    @SuppressWarnings("unchecked")
    private E valueAt(int index) { return (E) elements[index]; }

    boolean hasValidStructure() {
        if (size < 0 || size > elements.length || head < 0 || head >= elements.length
                || (size == 0 && head != 0)) { return false; }
        for (int slot = 0; slot < elements.length; slot++) {
            int distance = slot >= head ? slot - head : elements.length - head + slot;
            if ((elements[slot] != null) != (distance < size)) { return false; }
        }
        return true;
    }
}
