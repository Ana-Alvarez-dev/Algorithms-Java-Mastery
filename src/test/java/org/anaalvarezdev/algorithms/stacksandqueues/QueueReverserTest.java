package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SplittableRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueueReverserTest {
    @Test
    void shouldReverseAllQueueRepresentationsAndRestoreByReversingTwice() {
        for (FifoQueue<Integer> queue : List.<FifoQueue<Integer>>of(
                new CircularArrayQueue<>(1), new LinkedQueue<>(), new TwoStackQueue<>(1))) {
            SplittableRandom random = new SplittableRandom(20261019);
            for (int length = 0; length < 50; length++) {
                queue.clear(); List<Integer> original = new ArrayList<>();
                for (int index = 0; index < length; index++) {
                    int value = random.nextInt(-3, 4); original.add(value); queue.enqueue(value);
                }
                List<Integer> reversed = new ArrayList<>(original); Collections.reverse(reversed);
                QueueReverser.reverse(queue); assertThat(queue.toList()).containsExactlyElementsOf(reversed);
                QueueReverser.reverse(queue); assertThat(queue.toList()).containsExactlyElementsOf(original);
            }
        }
    }

    @Test
    void shouldPreserveElementIdentity() {
        FifoQueue<Object> queue = new LinkedQueue<>();
        Object first = new Object(); Object second = new Object();
        queue.enqueue(first); queue.enqueue(second); QueueReverser.reverse(queue);
        assertThat(queue.dequeue()).isSameAs(second); assertThat(queue.dequeue()).isSameAs(first);
    }

    @Test
    void shouldRejectNullQueue() {
        assertThatThrownBy(() -> QueueReverser.reverse(null)).isInstanceOf(NullPointerException.class);
    }
}
