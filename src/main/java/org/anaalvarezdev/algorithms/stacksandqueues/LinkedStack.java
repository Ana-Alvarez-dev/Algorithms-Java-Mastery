package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Private singly linked nodes with the top at head. Endpoint operations use
 * constant pointer work; storage is Theta(n). Clear drops this owner's chain.
 * @param <E> element type
 */
public final class LinkedStack<E> implements Stack<E> {
    private Node<E> head;
    private int size;

    /** Creates an empty stack. */
    public LinkedStack() { }

    @Override
    public void push(E value) {
        head = new Node<>(Objects.requireNonNull(value, "value must not be null"), head);
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) { throw new NoSuchElementException("stack is empty"); }
        E value = head.value;
        head = head.next;
        size--;
        return value;
    }

    @Override
    public E peek() { return head == null ? null : head.value; }
    @Override
    public int size() { return size; }
    @Override
    public void clear() { head = null; size = 0; }

    @Override
    public List<E> toList() {
        List<E> values = new ArrayList<>(size);
        for (Node<E> node = head; node != null; node = node.next) { values.add(node.value); }
        return values;
    }

    boolean hasValidStructure() {
        if (size < 0) { return false; }
        Node<E> node = head;
        for (int count = 0; count < size; count++) {
            if (node == null || node.value == null) { return false; }
            node = node.next;
        }
        return node == null;
    }

    private static final class Node<E> {
        private final E value;
        private final Node<E> next;
        private Node(E value, Node<E> next) { this.value = value; this.next = next; }
    }
}
