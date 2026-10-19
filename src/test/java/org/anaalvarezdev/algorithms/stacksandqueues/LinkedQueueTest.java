package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LinkedQueueTest extends QueueContractTest {
    @Override protected <E> FifoQueue<E> create() { return new LinkedQueue<>(); }
    @Override protected boolean valid(FifoQueue<?> queue) { return ((LinkedQueue<?>) queue).hasValidStructure(); }

    @Test
    void shouldResetBothEndpointsAfterEverySingletonRemoval() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for (int value = 0; value < 100; value++) {
            queue.enqueue(value);
            assertThat(queue.dequeue()).isEqualTo(value);
            assertThat(queue.hasValidStructure()).isTrue();
        }
        queue.enqueue(1); queue.enqueue(2);
        assertThat(queue.toList()).containsExactly(1, 2);
    }
}
