package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Non-null LIFO values. Snapshots are independent and top-first.
 * Implementations are mutable and not thread-safe; no iterator API is promised.
 * @param <E> element type
 */
public interface Stack<E> {
    /** Inserts at the top; rejects null before mutation. */
    void push(E value);
    /** Removes the top; throws NoSuchElementException when empty. */
    E pop();
    /** Returns the top without removal, or null when empty. */
    E peek();
    /** Returns the cached element count. */
    int size();
    /** Empties the container and permits reuse. */
    void clear();
    /** Returns a mutable shallow snapshot in removal order. */
    List<E> toList();

    /** Returns whether the container is empty. */
    default boolean isEmpty() { return size() == 0; }
    /** Removes the top, or returns null when empty. */
    default E poll() { return isEmpty() ? null : pop(); }
    /** Inspects the top; throws NoSuchElementException when empty. */
    default E element() {
        E value = peek();
        if (value == null) { throw new NoSuchElementException("stack is empty"); }
        return value;
    }
}
