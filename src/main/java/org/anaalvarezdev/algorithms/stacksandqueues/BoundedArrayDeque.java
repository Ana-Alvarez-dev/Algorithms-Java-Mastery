package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Fixed-capacity ring with a cached size, using all slots. End operations are
 * constant-time; overflow rejection preserves state. Unused slots are null.
 * @param <E> element type
 */
public final class BoundedArrayDeque<E> implements DoubleEndedQueue<E> {
    private final Object[] elements;
    private int head;
    private int size;

    /** Creates an empty deque; capacity must be positive and supported. */
    public BoundedArrayDeque(int capacity) {
        elements = new Object[ArrayCapacity.requirePositive(capacity)];
    }

    @Override
    public boolean offerFirst(E value) {
        Objects.requireNonNull(value, "value must not be null");
        if (isFull()) { return false; }
        head = head == 0 ? elements.length - 1 : head - 1;
        elements[head] = value;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E value) {
        Objects.requireNonNull(value, "value must not be null");
        if (isFull()) { return false; }
        elements[index(size)] = value;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) { return null; }
        E value = valueAt(head);
        elements[head] = null;
        head = head == elements.length - 1 ? 0 : head + 1;
        if (--size == 0) { head = 0; }
        return value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) { return null; }
        int last = index(size - 1);
        E value = valueAt(last);
        elements[last] = null;
        if (--size == 0) { head = 0; }
        return value;
    }

    @Override
    public E peekFirst() { return isEmpty() ? null : valueAt(head); }
    @Override
    public E peekLast() { return isEmpty() ? null : valueAt(index(size - 1)); }
    @Override
    public int size() { return size; }
    @Override
    public int capacity() { return elements.length; }
    @Override
    public void clear() { while (!isEmpty()) { pollFirst(); } }

    @Override
    public List<E> toList() {
        List<E> values = new ArrayList<>(size);
        for (int offset = 0; offset < size; offset++) { values.add(valueAt(index(offset))); }
        return values;
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
