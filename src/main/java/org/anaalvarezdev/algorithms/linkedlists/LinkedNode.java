package org.anaalvarezdev.algorithms.linkedlists;

/**
 * Explicit mutable link for standalone algorithm exercises. A null next link
 * terminates a chain; callers can also construct cycles deliberately. These
 * nodes are independent of the private nodes owned by LinkedSequence containers.
 *
 * @param <E> the stored value type
 */
public final class LinkedNode<E> {

    private final E value;
    private LinkedNode<E> next;

    /**
     * Creates an isolated node.
     * @param value the stored value, possibly null
     */
    public LinkedNode(E value) {
        this.value = value;
    }

    /**
     * Reads this node's value.
     * @return the stored value
     */
    public E getValue() {
        return value;
    }

    /**
     * Reads the successor link.
     * @return the next node, or null; cycles are permitted
     */
    public LinkedNode<E> getNext() {
        return next;
    }

    /**
     * Replaces the successor link.
     * @param next the next node, possibly null or this node itself
     */
    public void setNext(LinkedNode<E> next) {
        this.next = next;
    }
}
