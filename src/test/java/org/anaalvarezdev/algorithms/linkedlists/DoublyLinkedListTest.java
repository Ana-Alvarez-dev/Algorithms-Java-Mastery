package org.anaalvarezdev.algorithms.linkedlists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DoublyLinkedListTest extends LinkedSequenceContractTest {

    @Override
    protected <E> LinkedSequence<E> create() {
        return new DoublyLinkedList<>();
    }

    @Override
    protected boolean validStructure(LinkedSequence<?> sequence) {
        return ((DoublyLinkedList<?>) sequence).hasValidStructure();
    }

    @Test
    void shouldTraverseBackwardAfterInsertionRemovalReverseAndSplit() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (int value : new int[]{1, 2, 3, 4}) {
            list.addLast(value);
        }
        assertThat(backward(list)).containsExactly(4, 3, 2, 1);
        list.remove(1);
        list.add(1, 9);
        list.reverse();
        assertThat(backward(list)).containsExactly(1, 9, 3, 4);
        DoublyLinkedList<Integer> suffix = list.splitOff(2);
        assertThat(backward(list)).containsExactly(3, 4);
        assertThat(backward(suffix)).containsExactly(1, 9);
    }

    @Test
    void shouldRejectExhaustedOrInvalidatedBackwardIterator() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertThatThrownBy(list.descendingIterator()::next).isInstanceOf(NoSuchElementException.class);
        list.addLast(1);
        Iterator<Integer> iterator = list.descendingIterator();
        list.addFirst(2);
        assertThatThrownBy(iterator::next).isInstanceOf(ConcurrentModificationException.class);
    }

    private List<Integer> backward(DoublyLinkedList<Integer> list) {
        List<Integer> values = new ArrayList<>();
        list.descendingIterator().forEachRemaining(values::add);
        return values;
    }
}
