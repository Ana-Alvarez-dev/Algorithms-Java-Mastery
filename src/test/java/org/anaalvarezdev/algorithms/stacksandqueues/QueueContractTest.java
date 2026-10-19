package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SplittableRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

abstract class QueueContractTest {
    protected abstract <E> FifoQueue<E> create();
    protected abstract boolean valid(FifoQueue<?> queue);

    @Test
    void shouldDistinguishEmptySentinelsAndStrictOperations() {
        FifoQueue<Integer> queue = create();
        assertThat(queue.peek()).isNull();
        assertThat(queue.poll()).isNull();
        assertThatThrownBy(queue::dequeue).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(queue::element).isInstanceOf(NoSuchElementException.class);
        assertState(queue, List.of());
    }

    @Test
    void shouldPreserveFifoOrderAndDuplicatesAcrossInterleaving() {
        FifoQueue<Integer> queue = create();
        queue.enqueue(1); queue.enqueue(2); queue.enqueue(2);
        assertThat(queue.dequeue()).isEqualTo(1);
        queue.enqueue(3);
        assertThat(queue.element()).isEqualTo(2);
        assertThat(queue.peek()).isEqualTo(2);
        assertState(queue, List.of(2, 2, 3));
        assertThat(queue.dequeue()).isEqualTo(2);
        assertThat(queue.dequeue()).isEqualTo(2);
        assertThat(queue.dequeue()).isEqualTo(3);
        assertState(queue, List.of());
    }

    @Test
    void shouldRejectNullWithoutChangingOrder() {
        FifoQueue<Integer> queue = create();
        assertThatThrownBy(() -> queue.enqueue(null)).isInstanceOf(NullPointerException.class);
        queue.enqueue(7);
        assertThatThrownBy(() -> queue.enqueue(null)).isInstanceOf(NullPointerException.class);
        assertState(queue, List.of(7));
    }

    @Test
    void shouldReturnIndependentMutableShallowSnapshots() {
        FifoQueue<StringBuilder> queue = create();
        StringBuilder first = new StringBuilder("a");
        queue.enqueue(first);
        List<StringBuilder> snapshot = queue.toList();
        assertThat(snapshot.getFirst()).isSameAs(first);
        snapshot.clear();
        assertThat(queue.peek()).isSameAs(first);
        List<StringBuilder> retained = queue.toList();
        queue.dequeue();
        assertThat(retained).containsExactly(first);
        assertThat(valid(queue)).isTrue();
    }

    @Test
    void shouldClearAndReuseAcrossEmptySingletonAndManyItems() {
        FifoQueue<Integer> queue = create();
        queue.clear();
        for (int value = 0; value < 50; value++) { queue.enqueue(value); }
        queue.dequeue(); queue.enqueue(99); queue.clear();
        assertState(queue, List.of());
        queue.enqueue(8);
        assertThat(queue.poll()).isEqualTo(8);
        assertState(queue, List.of());
    }

    @Test
    void shouldRotateWithoutLosingSizeOrContents() {
        FifoQueue<Integer> queue = create();
        for (int value = 0; value < 17; value++) { queue.enqueue(value); }
        List<Integer> before = queue.toList();
        for (int step = 0; step < 17 * 3; step++) {
            Integer value = queue.dequeue();
            assertThat(value).isEqualTo(step % 17);
            queue.enqueue(value);
            assertThat(valid(queue)).isTrue();
        }
        assertState(queue, before);
    }

    @Test
    void shouldKeepInstancesIndependent() {
        FifoQueue<String> first = create(); FifoQueue<String> second = create();
        first.enqueue("a"); second.enqueue("b"); first.clear();
        assertState(first, List.of()); assertState(second, List.of("b"));
    }

    @Test
    void shouldMatchArrayDequeAcrossSeededOperations() {
        FifoQueue<Integer> queue = create();
        ArrayDeque<Integer> model = new ArrayDeque<>();
        SplittableRandom random = new SplittableRandom(20261019);
        for (int step = 0; step < 3000; step++) {
            int operation = random.nextInt(10);
            if (operation < 5) {
                int value = random.nextInt(-3, 4);
                queue.enqueue(value); model.addLast(value);
            } else if (operation < 8) {
                assertThat(queue.poll()).isEqualTo(model.pollFirst());
            } else if (operation == 8) {
                assertThat(queue.peek()).isEqualTo(model.peekFirst());
            } else {
                queue.clear(); model.clear();
            }
            assertState(queue, new ArrayList<>(model));
        }
    }

    private <E> void assertState(FifoQueue<E> queue, List<E> expected) {
        assertThat(valid(queue)).isTrue();
        assertThat(queue.size()).isEqualTo(expected.size());
        assertThat(queue.isEmpty()).isEqualTo(expected.isEmpty());
        assertThat(queue.toList()).containsExactlyElementsOf(expected);
    }
}
