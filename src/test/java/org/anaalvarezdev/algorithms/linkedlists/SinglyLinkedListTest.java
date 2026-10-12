package org.anaalvarezdev.algorithms.linkedlists;

class SinglyLinkedListTest extends LinkedSequenceContractTest {

    @Override
    protected <E> LinkedSequence<E> create() {
        return new SinglyLinkedList<>();
    }

    @Override
    protected boolean validStructure(LinkedSequence<?> sequence) {
        return ((SinglyLinkedList<?>) sequence).hasValidStructure();
    }
}
