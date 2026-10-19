package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TwoStackQueueTest extends QueueContractTest {
    @Override protected <E> FifoQueue<E> create() { return new TwoStackQueue<>(1); }
    @Override protected boolean valid(FifoQueue<?> queue) { return ((TwoStackQueue<?>) queue).hasValidStructure(); }

    @Test
    void shouldTransferOnlyAfterOlderOutgoingPrefixIsConsumed() {
        TwoStackQueue<Integer> queue = new TwoStackQueue<>(1);
        queue.enqueue(1); queue.enqueue(2); queue.enqueue(3);
        assertThat(queue.peek()).isEqualTo(1);
        assertThat(queue.peek()).isEqualTo(1);
        assertThat(queue.dequeue()).isEqualTo(1);
        queue.enqueue(4); queue.enqueue(5);
        assertThat(queue.toList()).containsExactly(2, 3, 4, 5);
        for (int value = 2; value <= 5; value++) { assertThat(queue.dequeue()).isEqualTo(value); }
        assertThat(queue.hasValidStructure()).isTrue();
        assertThat(queue.peek()).isNull();
    }

    @Test
    void shouldClearBothStacksAndReuseTheirStorage() {
        TwoStackQueue<Integer> queue = new TwoStackQueue<>(1);
        queue.enqueue(1); queue.enqueue(2); queue.dequeue(); queue.enqueue(3);
        queue.clear();
        assertThat(queue.hasValidStructure()).isTrue();
        assertThat(queue.toList()).isEmpty();
        queue.enqueue(7); assertThat(queue.dequeue()).isEqualTo(7);
    }

    @Test
    void shouldValidateInitialCapacityForBothStacks() {
        assertThatThrownBy(() -> new TwoStackQueue<>(0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new TwoStackQueue<>(Integer.MAX_VALUE)).isInstanceOf(IllegalArgumentException.class);
    }
}
