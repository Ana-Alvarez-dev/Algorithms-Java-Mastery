package org.anaalvarezdev.algorithms.linkedlists;

import org.junit.jupiter.api.Test;

import java.util.List;

class CircularLinkedListTest extends LinkedSequenceContractTest {

    @Override
    protected <E> LinkedSequence<E> create() {
        return new CircularLinkedList<>();
    }

    @Override
    protected boolean validStructure(LinkedSequence<?> sequence) {
        return ((CircularLinkedList<?>) sequence).hasValidStructure();
    }

    @Test
    void shouldMaintainSingletonAndTwoNodeRingsAcrossEndpointChanges() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.addFirst(1);
        assertState(list, List.of(1));
        list.addFirst(0);
        assertState(list, List.of(0, 1));
        list.reverse();
        assertState(list, List.of(1, 0));
        list.removeLast();
        assertState(list, List.of(1));
        list.addLast(2);
        list.removeFirst();
        assertState(list, List.of(2));
        list.removeFirst();
        assertState(list, List.of());
    }
}
