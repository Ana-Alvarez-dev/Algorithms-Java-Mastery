package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * FIFO with private nodes and cached head, tail and size. Both endpoints are
 * maintained in constant pointer work. Empty means both references are null.
 * @param <E> element type
 */
public final class LinkedQueue<E> implements FifoQueue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /** Creates an empty queue. */
    public LinkedQueue() { }

    @Override
    public void enqueue(E value) {
        Node<E> node = new Node<>(Objects.requireNonNull(value, "value must not be null"));
        if (tail == null) { head = node; } else { tail.next = node; }
        tail = node;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) { throw new NoSuchElementException("queue is empty"); }
        E value = head.value;
        head = head.next;
        if (--size == 0) { tail = null; }
        return value;
    }

    @Override
    public E peek() { return head == null ? null : head.value; }
    @Override
    public int size() { return size; }
    @Override
    public void clear() { head = null; tail = null; size = 0; }

    @Override
    public List<E> toList() {
        List<E> values = new ArrayList<>(size);
        for (Node<E> node = head; node != null; node = node.next) { values.add(node.value); }
        return values;
    }

    boolean hasValidStructure() {
        if (size < 0 || (size == 0 && (head != null || tail != null))) { return false; }
        Node<E> node = head;
        Node<E> last = null;
        for (int count = 0; count < size; count++) {
            if (node == null || node.value == null) { return false; }
            last = node;
            node = node.next;
        }
        return node == null && last == tail;
    }

    private static final class Node<E> {
        private final E value;
        private Node<E> next;
        private Node(E value) { this.value = value; }
    }
}
