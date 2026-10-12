package org.anaalvarezdev.algorithms.linkedlists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LinkedListAlgorithmsTest {

    @Test
    void shouldDetectEmptyAndAcyclicChainsWithEqualValues() {
        assertThat(LinkedListAlgorithms.hasCycle(null)).isFalse();
        assertThat(LinkedListAlgorithms.hasCycle(chain(1))).isFalse();
        assertThat(LinkedListAlgorithms.hasCycle(chain(1, 1, 1))).isFalse();
    }

    @Test
    void shouldDetectSelfAndInteriorCycles() {
        LinkedNode<Integer> singleton = chain(1);
        singleton.setNext(singleton);
        assertThat(LinkedListAlgorithms.hasCycle(singleton)).isTrue();
        LinkedNode<Integer> head = chain(1, 2, 3, 4);
        head.getNext().getNext().getNext().setNext(head.getNext());
        assertThat(LinkedListAlgorithms.hasCycle(head)).isTrue();
    }

    @Test
    void shouldReverseUsingExactlyTheOriginalNodes() {
        LinkedNode<Integer> head = chain(1, 2, 3);
        List<LinkedNode<Integer>> originals = nodes(head);
        LinkedNode<Integer> reversed = LinkedListAlgorithms.reverse(head);
        assertThat(nodes(reversed)).containsExactly(originals.get(2), originals.get(1), originals.get(0));
        assertThat(values(reversed)).containsExactly(3, 2, 1);
        assertThat(head.getNext()).isNull();
        assertThat(LinkedListAlgorithms.reverse(reversed)).isSameAs(head);
        assertThat(values(head)).containsExactly(1, 2, 3);
    }

    @Test
    void shouldReverseEmptyAndSingletonChains() {
        assertThat(LinkedListAlgorithms.reverse(null)).isNull();
        LinkedNode<Integer> node = chain(1);
        assertThat(LinkedListAlgorithms.reverse(node)).isSameAs(node);
    }

    @Test
    void shouldReturnSecondMiddleForEvenLengthsWithoutChangingLinks() {
        assertThat(LinkedListAlgorithms.middle(null)).isNull();
        for (int length = 1; length <= 8; length++) {
            LinkedNode<Integer> head = range(length);
            List<LinkedNode<Integer>> original = nodes(head);
            assertThat(LinkedListAlgorithms.middle(head)).isSameAs(original.get(length / 2));
            assertThat(nodes(head)).containsExactlyElementsOf(original);
        }
    }

    @Test
    void shouldRejectCyclesInAllAlgorithmsThatRequireFiniteChains() {
        LinkedNode<Integer> cyclic = chain(1, 2);
        cyclic.getNext().setNext(cyclic);
        assertThatThrownBy(() -> LinkedListAlgorithms.reverse(cyclic)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LinkedListAlgorithms.middle(cyclic)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LinkedListAlgorithms.mergeSorted(cyclic, null, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LinkedListAlgorithms.removeDuplicatesSorted(cyclic, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(cyclic.getNext().getNext()).isSameAs(cyclic);
    }

    @Test
    void shouldMergeSortedChainsAndReuseEveryElementNode() {
        LinkedNode<Integer> left = chain(Integer.MIN_VALUE, 2, 4);
        LinkedNode<Integer> right = chain(1, 2, Integer.MAX_VALUE);
        List<LinkedNode<Integer>> expectedNodes = new ArrayList<>(nodes(left));
        expectedNodes.addAll(nodes(right));
        LinkedNode<Integer> merged = LinkedListAlgorithms.mergeSorted(left, right, Comparator.naturalOrder());
        assertThat(values(merged)).containsExactly(Integer.MIN_VALUE, 1, 2, 2, 4, Integer.MAX_VALUE);
        assertThat(nodes(merged)).containsExactlyInAnyOrderElementsOf(expectedNodes);
        assertThat(LinkedListAlgorithms.hasCycle(merged)).isFalse();
    }

    @Test
    void shouldPreserveStableOrderAmongComparatorEqualValues() {
        record Item(String label, int key) { }
        LinkedNode<Item> left = chain(new Item("L1", 1), new Item("L2", 1), new Item("L3", 2));
        LinkedNode<Item> right = chain(new Item("R1", 1), new Item("R2", 2));
        LinkedNode<Item> merged = LinkedListAlgorithms.mergeSorted(left, right, Comparator.comparingInt(Item::key));
        assertThat(values(merged)).extracting(Item::label).containsExactly("L1", "L2", "R1", "L3", "R2");
    }

    @Test
    void shouldMergeEmptyInputsAndSupportComparatorOrderedNullValues() {
        assertThat(LinkedListAlgorithms.<Integer>mergeSorted(null, null, Comparator.naturalOrder())).isNull();
        LinkedNode<Integer> single = chain(1);
        assertThat(LinkedListAlgorithms.mergeSorted(null, single, Comparator.naturalOrder())).isSameAs(single);
        LinkedNode<Integer> merged = LinkedListAlgorithms.mergeSorted(chain(null, 2), chain(null, 3),
                Comparator.nullsFirst(Comparator.naturalOrder()));
        assertThat(values(merged)).containsExactly(null, null, 2, 3);
    }

    @Test
    void shouldRejectSharedNodesBeforeMerging() {
        LinkedNode<Integer> shared = chain(3, 4);
        LinkedNode<Integer> left = chain(1);
        LinkedNode<Integer> right = chain(2);
        left.setNext(shared);
        right.setNext(shared);
        assertThatThrownBy(() -> LinkedListAlgorithms.mergeSorted(left, right, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("input chains must not share nodes");
        assertThat(left.getNext()).isSameAs(shared);
        assertThat(right.getNext()).isSameAs(shared);
        assertThat(values(shared)).containsExactly(3, 4);
    }

    @Test
    void shouldRejectUnsortedInputsBeforeMutation() {
        LinkedNode<Integer> unsorted = chain(2, 1);
        LinkedNode<Integer> valid = chain(0, 3);
        assertThatThrownBy(() -> LinkedListAlgorithms.mergeSorted(valid, unsorted, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LinkedListAlgorithms.removeDuplicatesSorted(unsorted, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(values(unsorted)).containsExactly(2, 1);
        assertThat(values(valid)).containsExactly(0, 3);
    }

    @Test
    void shouldRemoveDuplicatesKeepingFirstNodeOfEachRun() {
        LinkedNode<Integer> head = chain(1, 1, 2, 2, 2, 3, 3);
        List<LinkedNode<Integer>> original = nodes(head);
        LinkedNode<Integer> result = LinkedListAlgorithms.removeDuplicatesSorted(head, Comparator.naturalOrder());
        assertThat(result).isSameAs(head);
        assertThat(nodes(result)).containsExactly(original.get(0), original.get(2), original.get(5));
        assertThat(values(result)).containsExactly(1, 2, 3);
        assertThat(LinkedListAlgorithms.removeDuplicatesSorted(result, Comparator.naturalOrder())).isSameAs(result);
        assertThat(values(result)).containsExactly(1, 2, 3);
    }

    @Test
    void shouldHandleEmptyEqualAndNullDuplicateRuns() {
        assertThat(LinkedListAlgorithms.<Integer>removeDuplicatesSorted(null, Comparator.naturalOrder())).isNull();
        LinkedNode<Integer> allEqual = chain(1, 1, 1);
        assertThat(LinkedListAlgorithms.removeDuplicatesSorted(allEqual, Comparator.naturalOrder())).isSameAs(allEqual);
        assertThat(allEqual.getNext()).isNull();
        LinkedNode<Integer> nullable = chain(null, null, 1, 1);
        assertThat(values(LinkedListAlgorithms.removeDuplicatesSorted(nullable,
                Comparator.nullsFirst(Comparator.naturalOrder())))).containsExactly(null, 1);
    }

    @Test
    void shouldRejectNullComparatorEvenForEmptyInputs() {
        assertThatThrownBy(() -> LinkedListAlgorithms.mergeSorted(null, null, null))
                .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> LinkedListAlgorithms.removeDuplicatesSorted(null, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldProcessLongChainsIteratively() {
        LinkedNode<Integer> head = range(100_000);
        assertThat(LinkedListAlgorithms.middle(head).getValue()).isEqualTo(50_000);
        LinkedNode<Integer> result = LinkedListAlgorithms.reverse(head);
        assertThat(result.getValue()).isEqualTo(99_999);
        assertThat(nodes(result)).hasSize(100_000);
        assertThat(LinkedListAlgorithms.hasCycle(result)).isFalse();
    }

    @SafeVarargs
    private static <E> LinkedNode<E> chain(E... values) {
        LinkedNode<E> head = null;
        for (int index = values.length - 1; index >= 0; index--) {
            LinkedNode<E> node = new LinkedNode<>(values[index]);
            node.setNext(head);
            head = node;
        }
        return head;
    }

    private static LinkedNode<Integer> range(int length) {
        LinkedNode<Integer> head = null;
        for (int value = length - 1; value >= 0; value--) {
            LinkedNode<Integer> node = new LinkedNode<>(value);
            node.setNext(head);
            head = node;
        }
        return head;
    }

    private static <E> List<LinkedNode<E>> nodes(LinkedNode<E> head) {
        assertThat(LinkedListAlgorithms.hasCycle(head)).isFalse();
        List<LinkedNode<E>> result = new ArrayList<>();
        for (LinkedNode<E> current = head; current != null; current = current.getNext()) {
            result.add(current);
        }
        return result;
    }

    private static <E> List<E> values(LinkedNode<E> head) {
        List<E> result = new ArrayList<>();
        for (LinkedNode<E> node : nodes(head)) {
            result.add(node.getValue());
        }
        return result;
    }
}
