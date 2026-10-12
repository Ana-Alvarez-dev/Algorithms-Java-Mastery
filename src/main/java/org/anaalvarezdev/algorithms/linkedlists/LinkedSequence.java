package org.anaalvarezdev.algorithms.linkedlists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Shared observable contract for the three educational node-based sequences.
 *
 * <p>Indices are zero-based. Values may be null or repeated. Element operations
 * require indices in [0, size); insertion and splitting allow [0, size]. Invalid
 * indices throw IndexOutOfBoundsException without modifying the sequence. Empty
 * endpoint access/removal throws java.util.NoSuchElementException.
 *
 * <p>Iteration visits every value once in logical order, including circular
 * sequences. Iterators reject structural changes with
 * java.util.ConcurrentModificationException and do not support remove(). A set
 * operation replaces a value without changing topology. These mutable
 * structures are not synchronized and do not implement java.util.List.
 *
 * @param <E> the element type
 */
public interface LinkedSequence<E> extends Iterable<E> {

    /**
     * Reads the cached element count in constant time.
     * @return the number of elements
     */
    int size();

    /**
     * Tests whether this sequence is empty.
     * @return whether there are no elements
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts a value at the head.
     * @param value the value to prepend, possibly null
     */
    void addFirst(E value);

    /**
     * Inserts a value at the tail.
     * @param value the value to append, possibly null
     */
    void addLast(E value);

    /**
     * Inserts a value before the element at index, or appends at size.
     * @param index insertion position
     * @param value the value to insert
     * @throws IndexOutOfBoundsException if index is outside [0, size]
     */
    void add(int index, E value);

    /**
     * Reads the head value without removing it.
     * @return the first value
     * @throws java.util.NoSuchElementException if empty
     */
    E getFirst();

    /**
     * Reads the tail value without removing it.
     * @return the last value
     * @throws java.util.NoSuchElementException if empty
     */
    E getLast();

    /**
     * Reads a value by zero-based position.
     * @param index element position
     * @return the value at that position
     * @throws IndexOutOfBoundsException if index is outside [0, size)
     */
    E get(int index);

    /**
     * Replaces a value without structurally modifying the sequence.
     * @param index element position
     * @param value replacement
     * @return the old value
     * @throws IndexOutOfBoundsException if index is outside [0, size)
     */
    E set(int index, E value);

    /**
     * Removes the head node.
     * @return the removed first value
     * @throws java.util.NoSuchElementException if empty
     */
    E removeFirst();

    /**
     * Removes the tail node.
     * @return the removed last value
     * @throws java.util.NoSuchElementException if empty
     */
    E removeLast();

    /**
     * Removes an element by position.
     * @param index element position
     * @return the removed value
     * @throws IndexOutOfBoundsException if index is outside [0, size)
     */
    E remove(int index);

    /** Drops this sequence's node ownership and resets its cached size. */
    void clear();

    /** Reverses this sequence in place, preserving all values and node count. */
    void reverse();

    /**
     * Transfers the suffix starting at index into a new independent sequence of
     * the same implementation. Existing element nodes are reused, not shared.
     *
     * @param index split position, from zero through size
     * @return the transferred suffix; this sequence retains the prefix
     * @throws IndexOutOfBoundsException if index is outside [0, size]
     */
    LinkedSequence<E> splitOff(int index);

    /**
     * Searches from the head using value equality.
     * @param value the search value, possibly null
     * @return the first matching index, or -1; uses Objects.equals
     */
    default int indexOf(Object value) {
        int index = 0;
        for (E current : this) {
            if (Objects.equals(current, value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Tests for a matching value.
     * @param value the search value
     * @return whether a matching value exists
     */
    default boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    /**
     * Copies the values without exposing internal node ownership.
     * @return an independent mutable snapshot, with shallow element references
     */
    default List<E> toList() {
        List<E> result = new ArrayList<>(size());
        for (E value : this) {
            result.add(value);
        }
        return result;
    }

    /**
     * Appends a snapshot of another sequence, allocating new destination nodes.
     * The source is unchanged; self-append duplicates the original values once.
     *
     * @param other the sequence to copy; must not be null
     */
    default void appendAll(LinkedSequence<? extends E> other) {
        Objects.requireNonNull(other, "other must not be null");
        for (E value : other.toList()) {
            addLast(value);
        }
    }
}
