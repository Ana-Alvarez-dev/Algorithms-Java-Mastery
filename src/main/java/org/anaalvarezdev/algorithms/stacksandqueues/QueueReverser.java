package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.Objects;

/** Reverses a FIFO in place using an explicit auxiliary stack. */
public final class QueueReverser {
    private QueueReverser() { }

    /**
     * Reverses the supplied queue's logical order; empty queues are valid.
     * Uses Theta(n) endpoint calls and Theta(n) auxiliary references; time is
     * Theta(n) for this module's amortised-constant-time queue implementations.
     * The operation is destructive, not transactional on allocation failure.
     * @param queue non-null mutable queue
     * @param <E> element type
     * @throws NullPointerException for a null queue
     */
    public static <E> void reverse(FifoQueue<E> queue) {
        Objects.requireNonNull(queue, "queue must not be null");
        Stack<E> values = new ArrayStack<>();
        while (!queue.isEmpty()) { values.push(queue.dequeue()); }
        while (!values.isEmpty()) { queue.enqueue(values.pop()); }
    }
}
