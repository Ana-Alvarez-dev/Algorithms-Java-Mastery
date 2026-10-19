package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SplittableRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoundedArrayDequeTest {
    @Test
    void shouldDistinguishStrictAndSentinelEmptyMethods() {
        DoubleEndedQueue<Integer> deque = new BoundedArrayDeque<>(2);
        assertThat(deque.peekFirst()).isNull(); assertThat(deque.peekLast()).isNull();
        assertThat(deque.pollFirst()).isNull(); assertThat(deque.pollLast()).isNull();
        assertThatThrownBy(deque::getFirst).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(deque::getLast).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(deque::removeFirst).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(deque::removeLast).isInstanceOf(NoSuchElementException.class);
        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    void shouldSupportBothEndsAndCapacityOneTransitions() {
        BoundedArrayDeque<Integer> deque = new BoundedArrayDeque<>(1);
        for (int index = 0; index < 50; index++) {
            deque.addFirst(index); assertThat(deque.isFull()).isTrue();
            assertThat(deque.getFirst()).isEqualTo(index); assertThat(deque.getLast()).isEqualTo(index);
            assertThat(deque.removeLast()).isEqualTo(index);
            deque.addLast(index); assertThat(deque.removeFirst()).isEqualTo(index);
            assertThat(deque.hasValidStructure()).isTrue();
        }
    }

    @Test
    void shouldUseEverySlotAndRejectOverflowWithoutMutation() {
        BoundedArrayDeque<Integer> deque = new BoundedArrayDeque<>(3);
        deque.addLast(2); deque.addFirst(1); deque.addLast(3);
        assertThat(deque.isFull()).isTrue();
        assertThat(deque.offerFirst(9)).isFalse(); assertThat(deque.offerLast(9)).isFalse();
        assertThatThrownBy(() -> deque.addFirst(9)).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> deque.addLast(9)).isInstanceOf(IllegalStateException.class);
        assertThat(deque.toList()).containsExactly(1, 2, 3);
        assertThat(deque.hasValidStructure()).isTrue();
    }

    @Test
    void shouldRejectNullEvenWhenFullWithoutMutatingState() {
        BoundedArrayDeque<Integer> deque = new BoundedArrayDeque<>(1);
        for (boolean full : new boolean[]{false, true}) {
            if (full) { deque.addLast(1); }
            assertThatThrownBy(() -> deque.offerFirst(null)).isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> deque.offerLast(null)).isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> deque.addFirst(null)).isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> deque.addLast(null)).isInstanceOf(NullPointerException.class);
            assertThat(deque.size()).isEqualTo(full ? 1 : 0);
            assertThat(deque.hasValidStructure()).isTrue();
        }
    }

    @Test
    void shouldValidateCapacity() {
        for (int capacity : new int[]{-1, 0, Integer.MAX_VALUE}) {
            assertThatThrownBy(() -> new BoundedArrayDeque<>(capacity)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void shouldClearWrappedReferencesAndRetainCapacity() {
        BoundedArrayDeque<Integer> deque = new BoundedArrayDeque<>(3);
        deque.addLast(1); deque.addLast(2); deque.addLast(3); deque.removeFirst(); deque.addLast(4);
        deque.clear(); deque.clear();
        assertThat(deque.hasValidStructure()).isTrue();
        assertThat(deque.capacity()).isEqualTo(3);
        deque.addFirst(7); assertThat(deque.removeLast()).isEqualTo(7);
    }

    @Test
    void shouldSnapshotReferencesIndependentlyAndPermitDuplicates() {
        BoundedArrayDeque<StringBuilder> deque = new BoundedArrayDeque<>(3);
        StringBuilder value = new StringBuilder("x");
        deque.addFirst(value); deque.addLast(value);
        List<StringBuilder> copy = deque.toList();
        assertThat(copy.getFirst()).isSameAs(value);
        copy.clear(); assertThat(deque.size()).isEqualTo(2);
        assertThat(deque.removeFirst()).isSameAs(value);
        assertThat(deque.removeLast()).isSameAs(value);
        assertThat(deque.hasValidStructure()).isTrue();
    }

    @Test
    void shouldMatchBoundedReferenceModelAcrossWrapsAtBothEnds() {
        for (int capacity : new int[]{1, 2, 3, 8}) {
            BoundedArrayDeque<Integer> deque = new BoundedArrayDeque<>(capacity);
            ArrayDeque<Integer> model = new ArrayDeque<>();
            SplittableRandom random = new SplittableRandom(20261019 + capacity);
            for (int step = 0; step < 3000; step++) {
                int operation = random.nextInt(8);
                int value = random.nextInt(-3, 4);
                if (operation < 2) {
                    boolean space = model.size() < capacity;
                    boolean inserted = operation == 0 ? deque.offerFirst(value) : deque.offerLast(value);
                    assertThat(inserted).isEqualTo(space);
                    if (space) { if (operation == 0) { model.addFirst(value); } else { model.addLast(value); } }
                } else if (operation == 2) { assertThat(deque.pollFirst()).isEqualTo(model.pollFirst());
                } else if (operation == 3) { assertThat(deque.pollLast()).isEqualTo(model.pollLast());
                } else if (operation == 4) { assertThat(deque.peekFirst()).isEqualTo(model.peekFirst());
                } else if (operation == 5) { assertThat(deque.peekLast()).isEqualTo(model.peekLast());
                } else if (operation == 6) { deque.clear(); model.clear(); }
                assertThat(deque.hasValidStructure()).isTrue();
                assertThat(deque.toList()).containsExactlyElementsOf(new ArrayList<>(model));
                assertThat(deque.size()).isEqualTo(model.size());
                assertThat(deque.isFull()).isEqualTo(model.size() == capacity);
            }
        }
    }
}
