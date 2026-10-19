package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CircularArrayQueueTest extends QueueContractTest {
    @Override protected <E> FifoQueue<E> create() { return new CircularArrayQueue<>(1); }
    @Override protected boolean valid(FifoQueue<?> queue) { return ((CircularArrayQueue<?>) queue).hasValidStructure(); }

    @Test
    void shouldGrowWrappedContentsInLogicalOrder() {
        CircularArrayQueue<String> queue = new CircularArrayQueue<>(3);
        queue.enqueue("a"); queue.enqueue("b"); queue.enqueue("c");
        assertThat(queue.dequeue()).isEqualTo("a");
        assertThat(queue.dequeue()).isEqualTo("b");
        queue.enqueue("d"); queue.enqueue("e");
        assertThat(queue.capacity()).isEqualTo(3);
        assertThat(queue.hasValidStructure()).isTrue();
        queue.enqueue("f");
        assertThat(queue.toList()).containsExactly("c", "d", "e", "f");
        assertThat(queue.capacity()).isEqualTo(6);
        assertThat(queue.hasValidStructure()).isTrue();
        queue.clear();
        assertThat(queue.capacity()).isEqualTo(6);
        assertThat(queue.hasValidStructure()).isTrue();
    }

    @Test
    void shouldRejectInvalidCapacities() {
        for (int capacity : new int[]{-1, 0, Integer.MAX_VALUE}) {
            assertThatThrownBy(() -> new CircularArrayQueue<>(capacity)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void shouldRejectNullBeforeGrowingFullStorage() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(1);
        queue.enqueue(1);
        assertThatThrownBy(() -> queue.enqueue(null)).isInstanceOf(NullPointerException.class);
        assertThat(queue.capacity()).isEqualTo(1);
        assertThat(queue.toList()).containsExactly(1);
        assertThat(queue.hasValidStructure()).isTrue();
    }
}
