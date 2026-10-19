package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Non-null FIFO values, with rear insertion and front removal.
 * Snapshots are independent and front-first. Implementations are not thread-safe.
 * @param <E> element type
 */
public interface FifoQueue<E> {
    /** Appends a value; rejects null before mutation. */
    void enqueue(E value);
    /** Removes the oldest value; throws NoSuchElementException when empty. */
    E dequeue();
    /** Inspects the front, or returns null when empty. */
    E peek();
    /** Returns the cached element count. */
    int size();
    /** Empties the container and permits reuse. */
    void clear();
    /** Returns a mutable shallow snapshot in removal order. */
    List<E> toList();

    /** Returns whether the container is empty. */
    default boolean isEmpty() { return size() == 0; }
    /** Removes the front, or returns null when empty. */
    default E poll() { return isEmpty() ? null : dequeue(); }
    /** Inspects the front; throws NoSuchElementException when empty. */
    default E element() {
        E value = peek();
        if (value == null) { throw new NoSuchElementException("queue is empty"); }
        return value;
    }
}
