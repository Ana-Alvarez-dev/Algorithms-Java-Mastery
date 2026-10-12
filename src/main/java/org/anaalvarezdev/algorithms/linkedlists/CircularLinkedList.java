package org.anaalvarezdev.algorithms.linkedlists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Circular singly linked sequence with explicit head, tail and cached size.
 *
 * <p>Invariant: empty endpoints are null; otherwise tail.next is head and exactly
 * size distinct nodes form the ring. A singleton points to itself. Head changes
 * reconnect the tail; splitting creates two independent rings. Reversal saves
 * each successor and changes exactly size links, then swaps the endpoints.
 *
 * <p>Head changes, tail append/access and size are Θ(1). Indexed operations and
 * tail removal are O(n). Iteration is bounded by the captured node count and
 * terminates after one lap. Reversal is Θ(n) with Θ(1) auxiliary state; node
 * storage is Θ(n). This is a ring representation, not an infinite iterator.
 *
 * @param <E> the element type
 */
public final class CircularLinkedList<E> implements LinkedSequence<E> {

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
    public CircularLinkedList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E value) {
        Node<E> node = new Node<>(value, head);
        if (size == 0) {
            tail = node;
        }
        head = node;
        tail.next = head;
        size++;
        modifications++;
    }

    @Override
    public void addLast(E value) {
        Node<E> node = new Node<>(value, head);
        if (size == 0) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        tail.next = head;
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
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }
        removed.next = null;
        size--;
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
        Node<E> previous = tail;
        Node<E> current = head;
        for (int count = 0; count < size; count++) {
            Node<E> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        Node<E> oldHead = head;
        head = tail;
        tail = oldHead;
        modifications++;
    }

    @Override
    public CircularLinkedList<E> splitOff(int index) {
        checkPosition(index);
        CircularLinkedList<E> suffix = new CircularLinkedList<>();
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
            tail = previous;
            tail.next = head;
            suffix.tail.next = suffix.head;
        }
        size = index;
        modifications++;
        return suffix;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;
            private int remaining = size;
            private final int expected = modifications;

            @Override
            public boolean hasNext() {
                if (expected != modifications) {
                    throw new ConcurrentModificationException();
                }
                return remaining > 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.value;
                current = current.next;
                remaining--;
                return value;
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

    boolean hasValidStructure() {
        if (size == 0) {
            return head == null && tail == null;
        }
        if (size < 0 || head == null || tail == null || tail.next != head) {
            return false;
        }
        Node<E> current = head;
        Node<E> last = null;
        for (int count = 0; count < size; count++) {
            if (current == null || (count > 0 && current == head)) {
                return false;
            }
            last = current;
            current = current.next;
        }
        return current == head && last == tail;
    }
}
