package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.List;

/**
 * FIFO from incoming/outgoing LIFO stacks. Transfer only when outgoing is empty.
 * A dequeue/peek can be linear, but a sequence uses O(1) amortised work per call.
 * @param <E> element type
 */
public final class TwoStackQueue<E> implements FifoQueue<E> {
    private final ArrayStack<E> incoming;
    private final ArrayStack<E> outgoing;

    /** Creates an empty queue with initial capacity eight in each stack. */
    public TwoStackQueue() { this(8); }
    /** Reserves initial capacity in each stack; both can grow independently. */
    public TwoStackQueue(int initialCapacity) {
        incoming = new ArrayStack<>(initialCapacity);
        outgoing = new ArrayStack<>(initialCapacity);
    }

    @Override
    public void enqueue(E value) { incoming.push(value); }
    @Override
    public E dequeue() { transferIfNeeded(); return outgoing.pop(); }
    @Override
    public E peek() { transferIfNeeded(); return outgoing.peek(); }
    @Override
    public int size() { return incoming.size() + outgoing.size(); }
    @Override
    public void clear() { incoming.clear(); outgoing.clear(); }

    @Override
    public List<E> toList() {
        List<E> values = outgoing.toList();
        List<E> newer = incoming.toList();
        for (int index = newer.size() - 1; index >= 0; index--) { values.add(newer.get(index)); }
        return values;
    }

    private void transferIfNeeded() {
        if (outgoing.isEmpty()) {
            while (!incoming.isEmpty()) { outgoing.push(incoming.pop()); }
        }
    }

    boolean hasValidStructure() {
        return incoming.hasValidStructure() && outgoing.hasValidStructure();
    }
}
