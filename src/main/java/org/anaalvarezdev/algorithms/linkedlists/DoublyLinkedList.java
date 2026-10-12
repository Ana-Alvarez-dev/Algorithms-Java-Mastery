package org.anaalvarezdev.algorithms.linkedlists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Doubly linked sequence with private reciprocal links and cached endpoints.
 *
 * <p>Invariant: head.previous and tail.next are null; adjacent next/previous
 * links agree; exactly size nodes connect the endpoints. Linking updates both
 * neighbors; unlinking reconnects both neighbors before detaching a node.
 * Reversal swaps each node's links and the endpoints, preserving reciprocity.
 *
 * <p>Endpoint access/changes and size are Θ(1). Indexed operations traverse from
 * the nearer endpoint and are O(n) worst case. Reversal is Θ(n) with Θ(1)
 * auxiliary state; node storage is Θ(n).
 *
 * @param <E> the element type
 */
public final class DoublyLinkedList<E> implements LinkedSequence<E> {

    private static final class Node<E> {
        private E value;
        private Node<E> previous;
        private Node<E> next;

        private Node(E value, Node<E> previous, Node<E> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int modifications;

    /** Creates an empty sequence. */
    public DoublyLinkedList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E value) {
        linkBefore(value, head);
    }

    @Override
    public void addLast(E value) {
        linkBefore(value, null);
    }

    @Override
    public void add(int index, E value) {
        checkPosition(index);
        linkBefore(value, index == size ? null : nodeAt(index));
    }

    private void linkBefore(E value, Node<E> next) {
        Node<E> previous = next == null ? tail : next.previous;
        Node<E> node = new Node<>(value, previous, next);
        if (previous == null) {
            head = node;
        } else {
            previous.next = node;
        }
        if (next == null) {
            tail = node;
        } else {
            next.previous = node;
        }
        size++;
        modifications++;
    }

    @Override
    public E getFirst() {
        requireNonEmpty();
        return head.value;
    }

    @Override
    public E getLast() {
        requireNonEmpty();
        return tail.value;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return nodeAt(index).value;
    }

    @Override
    public E set(int index, E value) {
        Objects.checkIndex(index, size);
        Node<E> node = nodeAt(index);
        E old = node.value;
        node.value = value;
        return old;
    }

    @Override
    public E removeFirst() {
        requireNonEmpty();
        return unlink(head);
    }

    @Override
    public E removeLast() {
        requireNonEmpty();
        return unlink(tail);
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        return unlink(nodeAt(index));
    }

    private E unlink(Node<E> node) {
        if (node.previous == null) {
            head = node.next;
        } else {
            node.previous.next = node.next;
        }
        if (node.next == null) {
            tail = node.previous;
        } else {
            node.next.previous = node.previous;
        }
        node.previous = null;
        node.next = null;
        size--;
        modifications++;
        return node.value;
    }

    @Override
    public void clear() {
        if (size > 0) {
            head = null;
            tail = null;
            size = 0;
            modifications++;
        }
    }

    @Override
    public void reverse() {
        if (size < 2) {
            return;
        }
        Node<E> current = head;
        while (current != null) {
            Node<E> next = current.next;
            current.next = current.previous;
            current.previous = next;
            current = next;
        }
        Node<E> oldHead = head;
        head = tail;
        tail = oldHead;
        modifications++;
    }

    @Override
    public DoublyLinkedList<E> splitOff(int index) {
        checkPosition(index);
        DoublyLinkedList<E> suffix = new DoublyLinkedList<>();
        if (index == size) {
            return suffix;
        }
        suffix.tail = tail;
        suffix.size = size - index;
        suffix.head = nodeAt(index);
        tail = suffix.head.previous;
        suffix.head.previous = null;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size = index;
        modifications++;
        return suffix;
    }

    @Override
    public Iterator<E> iterator() {
        return iterator(false);
    }

    /**
     * Iterates in reverse logical order without modifying links.
     * @return an iterator from tail to head, invalidated by structural changes
     */
    public Iterator<E> descendingIterator() {
        return iterator(true);
    }

    private Iterator<E> iterator(boolean descending) {
        return new Iterator<>() {
            private Node<E> current = descending ? tail : head;
            private final int expected = modifications;

            @Override
            public boolean hasNext() {
                if (expected != modifications) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.value;
                current = descending ? current.previous : current.next;
                return value;
            }
        };
    }

    private Node<E> nodeAt(int index) {
        if (index < size / 2) {
            Node<E> current = head;
            for (int position = 0; position < index; position++) {
                current = current.next;
            }
            return current;
        }
        Node<E> current = tail;
        for (int position = size - 1; position > index; position--) {
            current = current.previous;
        }
        return current;
    }

    private void checkPosition(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    private void requireNonEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("list is empty");
        }
    }

    boolean hasValidStructure() {
        if (size == 0) {
            return head == null && tail == null;
        }
        if (size < 0 || head == null || tail == null || head.previous != null) {
            return false;
        }
        Node<E> current = head;
        Node<E> previous = null;
        for (int count = 0; count < size; count++) {
            if (current == null || current.previous != previous) {
                return false;
            }
            previous = current;
            current = current.next;
        }
        return current == null && previous == tail;
    }
}
