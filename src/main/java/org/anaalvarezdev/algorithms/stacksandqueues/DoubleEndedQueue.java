package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Selected bounded deque API, rejecting null even when full.
 * Failed offers and empty polls leave contents unchanged. Not thread-safe.
 * @param <E> element type
 */
public interface DoubleEndedQueue<E> {
    /** Inserts at the front, or returns false when full. */
    boolean offerFirst(E value);
    /** Inserts at the rear, or returns false when full. */
    boolean offerLast(E value);
    /** Removes the front, or returns null when empty. */
    E pollFirst();
    /** Removes the rear, or returns null when empty. */
    E pollLast();
    /** Inspects the front, or returns null when empty. */
    E peekFirst();
    /** Inspects the rear, or returns null when empty. */
    E peekLast();
    /** Returns the cached element count. */
    int size();
    /** Returns the fixed maximum number of elements. */
    int capacity();
    /** Clears occupied references and allows reuse. */
    void clear();
    /** Returns an independent mutable front-to-rear snapshot. */
    List<E> toList();

    /** Returns whether no elements are stored. */
    default boolean isEmpty() { return size() == 0; }
    /** Returns whether capacity has been reached. */
    default boolean isFull() { return size() == capacity(); }
    /** Inserts at front; throws IllegalStateException when full. */
    default void addFirst(E value) {
        if (!offerFirst(value)) { throw new IllegalStateException("deque is full"); }
    }
    /** Inserts at rear; throws IllegalStateException when full. */
    default void addLast(E value) {
        if (!offerLast(value)) { throw new IllegalStateException("deque is full"); }
    }
    /** Removes front; throws NoSuchElementException when empty. */
    default E removeFirst() {
        E value = pollFirst();
        if (value == null) { throw new NoSuchElementException("deque is empty"); }
        return value;
    }
    /** Removes rear; throws NoSuchElementException when empty. */
    default E removeLast() {
        E value = pollLast();
        if (value == null) { throw new NoSuchElementException("deque is empty"); }
        return value;
    }
    /** Inspects front; throws NoSuchElementException when empty. */
    default E getFirst() {
        E value = peekFirst();
        if (value == null) { throw new NoSuchElementException("deque is empty"); }
        return value;
    }
    /** Inspects rear; throws NoSuchElementException when empty. */
    default E getLast() {
        E value = peekLast();
        if (value == null) { throw new NoSuchElementException("deque is empty"); }
        return value;
    }
}
