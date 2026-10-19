package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Geometrically growing stack: live prefix, top at size-1, null unused slots.
 * Push is O(1) amortised, O(n) on growth; pop/peek are constant-time.
 * Storage is proportional to retained capacity; removal does not shrink it.
 * @param <E> element type
 */
public final class ArrayStack<E> implements Stack<E> {
    private Object[] elements;
    private int size;

    /** Creates an empty stack with capacity eight. */
    public ArrayStack() { this(8); }
    /** Creates an empty stack; rejects nonpositive or unsupported capacity. */
    public ArrayStack(int initialCapacity) {
        elements = new Object[ArrayCapacity.requirePositive(initialCapacity)];
    }

    @Override
    public void push(E value) {
        Objects.requireNonNull(value, "value must not be null");
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, ArrayCapacity.grow(elements.length));
        }
        elements[size++] = value;
    }

    @Override
    public E pop() {
        if (isEmpty()) { throw new NoSuchElementException("stack is empty"); }
        E value = valueAt(--size);
        elements[size] = null;
        return value;
    }

    @Override
    public E peek() { return isEmpty() ? null : valueAt(size - 1); }
    @Override
    public int size() { return size; }
    /** Returns current retained capacity, not a fixed maximum. */
    public int capacity() { return elements.length; }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public List<E> toList() {
        List<E> values = new ArrayList<>(size);
        for (int index = size - 1; index >= 0; index--) { values.add(valueAt(index)); }
        return values;
    }

    @SuppressWarnings("unchecked")
    private E valueAt(int index) { return (E) elements[index]; }

    boolean hasValidStructure() {
        if (size < 0 || size > elements.length) { return false; }
        for (int index = 0; index < elements.length; index++) {
            if ((elements[index] != null) != (index < size)) { return false; }
        }
        return true;
    }
}
