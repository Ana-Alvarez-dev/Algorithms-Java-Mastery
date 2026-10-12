package org.anaalvarezdev.algorithms.linkedlists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Singly linked sequence with private nodes, head, tail and cached size.
 *
 * <p>Invariant: an empty sequence has null endpoints; otherwise exactly size
 * nodes are reachable from head, the last is tail, and tail.next is null.
 * Insertion saves the successor before linking a new node; removal reconnects
 * the predecessor before detaching the removed node. Endpoint and size updates
 * preserve the invariant. Indexed traversal advances over a finite prefix.
 *
 * <p>Head changes, tail append/access and size are Θ(1). Indexed operations and
 * tail removal are O(n). Reversal is Θ(n) with Θ(1) auxiliary state. Node storage
 * is Θ(n). Equality-based search assumes constant-time value comparison.
 *
 * @param <E> the element type
 */
public final class SinglyLinkedList<E> implements LinkedSequence<E> {

    private static final class Node<E> {
        private E value;
        private Node<E> next;

        private Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int modifications;

    /** Creates an empty sequence. */
    public SinglyLinkedList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E value) {
        head = new Node<>(value, head);
        if (size == 0) {
            tail = head;
        }
        size++;
        modifications++;
    }

    @Override
    public void addLast(E value) {
        Node<E> node = new Node<>(value, null);
        if (size == 0) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
        modifications++;
    }

    @Override
    public void add(int index, E value) {
        checkPosition(index);
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node<E> previous = nodeAt(index - 1);
            previous.next = new Node<>(value, previous.next);
            size++;
            modifications++;
        }
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
        Node<E> removed = head;
        head = removed.next;
        removed.next = null;
        if (--size == 0) {
            tail = null;
        }
        modifications++;
        return removed.value;
    }

    @Override
    public E removeLast() {
        requireNonEmpty();
        return remove(size - 1);
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return removeFirst();
        }
        Node<E> previous = nodeAt(index - 1);
        Node<E> removed = previous.next;
        previous.next = removed.next;
        if (removed == tail) {
            tail = previous;
        }
        removed.next = null;
        size--;
        modifications++;
        return removed.value;
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
        tail = head;
        Node<E> previous = null;
        Node<E> current = head;
        while (current != null) {
            Node<E> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
        modifications++;
    }

    @Override
    public SinglyLinkedList<E> splitOff(int index) {
        checkPosition(index);
        SinglyLinkedList<E> suffix = new SinglyLinkedList<>();
        if (index == size) {
            return suffix;
        }
        suffix.tail = tail;
        suffix.size = size - index;
        if (index == 0) {
            suffix.head = head;
            head = null;
            tail = null;
        } else {
            Node<E> previous = nodeAt(index - 1);
            suffix.head = previous.next;
            previous.next = null;
            tail = previous;
        }
        size = index;
        modifications++;
        return suffix;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;
            private final int expected = modifications;

            @Override
            public boolean hasNext() {
                checkModification();
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.value;
                current = current.next;
                return value;
            }

            private void checkModification() {
                if (expected != modifications) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private Node<E> nodeAt(int index) {
        Node<E> current = head;
        for (int position = 0; position < index; position++) {
            current = current.next;
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

    // Explicit diagnostic for package tests; never scanned during a mutation.
    boolean hasValidStructure() {
        if (size == 0) {
            return head == null && tail == null;
        }
        if (size < 0 || head == null || tail == null) {
            return false;
        }
        Node<E> current = head;
        Node<E> last = null;
        for (int count = 0; count < size; count++) {
            if (current == null) {
                return false;
            }
            last = current;
            current = current.next;
        }
        return current == null && last == tail;
    }
}
