package org.anaalvarezdev.algorithms.linkedlists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SplittableRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Same observable contract and independent model for all three topologies. */
abstract class LinkedSequenceContractTest {

    protected abstract <E> LinkedSequence<E> create();

    protected abstract boolean validStructure(LinkedSequence<?> sequence);

    @Test
    void shouldStartEmpty() {
        assertState(create(), List.of());
    }

    @Test
    void shouldAddAtBothEndsAndMaintainSize() {
        LinkedSequence<String> list = create();
        list.addFirst("b");
        list.addFirst("a");
        list.addLast("c");
        assertState(list, List.of("a", "b", "c"));
        assertThat(list.getFirst()).isEqualTo("a");
        assertThat(list.getLast()).isEqualTo("c");
    }

    @Test
    void shouldInsertAtEveryPositionIncludingEmpty() {
        LinkedSequence<Integer> list = create();
        list.add(0, 2);
        list.add(0, 0);
        list.add(1, 1);
        list.add(3, 3);
        assertState(list, List.of(0, 1, 2, 3));
    }

    @Test
    void shouldGetAndReplaceEveryPositionWithoutChangingSize() {
        LinkedSequence<Integer> list = filled(0, 1, 2, 3, 4);
        for (int index = 0; index < list.size(); index++) {
            assertThat(list.get(index)).isEqualTo(index);
            assertThat(list.set(index, index + 10)).isEqualTo(index);
        }
        assertState(list, List.of(10, 11, 12, 13, 14));
    }

    @Test
    void shouldAcceptNullAndRepeatedValues() {
        LinkedSequence<String> list = filled(null, "a", null, "a");
        assertState(list, Arrays.asList(null, "a", null, "a"));
        assertThat(list.indexOf(null)).isZero();
        assertThat(list.indexOf(new String("a"))).isEqualTo(1);
        assertThat(list.contains(null)).isTrue();
        assertThat(list.contains("missing")).isFalse();
        assertThat(list.indexOf(123)).isEqualTo(-1);
    }

    @Test
    void shouldRemoveHeadMiddleAndTail() {
        LinkedSequence<Integer> list = filled(0, 1, 2, 3, 4);
        assertThat(list.remove(2)).isEqualTo(2);
        assertState(list, List.of(0, 1, 3, 4));
        assertThat(list.removeFirst()).isZero();
        assertThat(list.removeLast()).isEqualTo(4);
        assertState(list, List.of(1, 3));
        assertThat(list.remove(0)).isEqualTo(1);
        assertThat(list.remove(0)).isEqualTo(3);
        assertState(list, List.of());
    }

    @Test
    void shouldSupportSingletonRemovalAndReuse() {
        LinkedSequence<Integer> list = create();
        list.addLast(1);
        assertThat(list.removeFirst()).isEqualTo(1);
        assertState(list, List.of());
        list.addFirst(2);
        assertThat(list.removeLast()).isEqualTo(2);
        assertState(list, List.of());
        list.addLast(3);
        assertState(list, List.of(3));
    }

    @Test
    void shouldRejectEmptyEndpointOperations() {
        LinkedSequence<Integer> list = create();
        assertThatThrownBy(list::getFirst).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(list::getLast).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(list::removeFirst).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(list::removeLast).isInstanceOf(NoSuchElementException.class);
        assertState(list, List.of());
    }

    @Test
    void shouldRejectInvalidIndicesWithoutMutation() {
        for (int count : new int[]{0, 1, 3}) {
            LinkedSequence<Integer> list = create();
            for (int index = 0; index < count; index++) {
                list.addLast(index);
            }
            List<Integer> expected = list.toList();
            for (int index : new int[]{-1, count, count + 1}) {
                assertThatThrownBy(() -> list.get(index)).isInstanceOf(IndexOutOfBoundsException.class);
                assertThatThrownBy(() -> list.set(index, 9)).isInstanceOf(IndexOutOfBoundsException.class);
                assertThatThrownBy(() -> list.remove(index)).isInstanceOf(IndexOutOfBoundsException.class);
            }
            for (int index : new int[]{-1, count + 1}) {
                assertThatThrownBy(() -> list.add(index, 9)).isInstanceOf(IndexOutOfBoundsException.class);
                assertThatThrownBy(() -> list.splitOff(index)).isInstanceOf(IndexOutOfBoundsException.class);
            }
            assertState(list, expected);
        }
    }

    @Test
    void shouldReverseAllBoundaryLengthsAndRestoreByReversingTwice() {
        for (int count = 0; count <= 7; count++) {
            LinkedSequence<Integer> list = create();
            List<Integer> expected = new ArrayList<>();
            for (int index = 0; index < count; index++) {
                list.addLast(index);
                expected.add(index);
            }
            list.reverse();
            Collections.reverse(expected);
            assertState(list, expected);
            list.reverse();
            Collections.reverse(expected);
            assertState(list, expected);
            list.addFirst(-1);
            list.addLast(99);
            expected.addFirst(-1);
            expected.addLast(99);
            assertState(list, expected);
        }
    }

    @Test
    void shouldSplitAtEveryPositionIntoIndependentSequences() {
        for (int count = 0; count <= 6; count++) {
            for (int cut = 0; cut <= count; cut++) {
                LinkedSequence<Integer> list = create();
                List<Integer> values = new ArrayList<>();
                for (int index = 0; index < count; index++) {
                    list.addLast(index);
                    values.add(index);
                }
                LinkedSequence<Integer> suffix = list.splitOff(cut);
                assertThat(suffix).isInstanceOf(list.getClass());
                List<Integer> prefixValues = new ArrayList<>(values.subList(0, cut));
                List<Integer> suffixValues = new ArrayList<>(values.subList(cut, count));
                assertState(list, prefixValues);
                assertState(suffix, suffixValues);
                list.addLast(-1);
                prefixValues.add(-1);
                suffix.addFirst(-2);
                suffixValues.addFirst(-2);
                list.reverse();
                Collections.reverse(prefixValues);
                suffix.reverse();
                Collections.reverse(suffixValues);
                assertState(list, prefixValues);
                assertState(suffix, suffixValues);
            }
        }
    }

    @Test
    void shouldAppendCopiesWithoutSharingSourceNodes() {
        LinkedSequence<Integer> source = filled(2, 3);
        LinkedSequence<Integer> destination = filled(1);
        destination.appendAll(source);
        assertState(destination, List.of(1, 2, 3));
        source.set(0, 20);
        source.removeLast();
        source.addFirst(0);
        assertState(source, List.of(0, 20));
        assertState(destination, List.of(1, 2, 3));
    }

    @Test
    void shouldAppendSelfExactlyOnceAndRejectNullSource() {
        LinkedSequence<Integer> list = filled(1, 2);
        list.appendAll(list);
        assertState(list, List.of(1, 2, 1, 2));
        assertThatThrownBy(() -> list.appendAll(null)).isInstanceOf(NullPointerException.class);
        assertState(list, List.of(1, 2, 1, 2));
    }

    @Test
    void shouldAppendAcrossGenericTypesAndImplementations() {
        LinkedSequence<Number> numbers = create();
        DoublyLinkedList<Integer> integers = new DoublyLinkedList<>();
        integers.addLast(42);
        numbers.addLast(1.5);
        numbers.appendAll(integers);
        assertState(numbers, List.of(1.5, 42));
    }

    @Test
    void shouldReturnIndependentSnapshotsWithShallowValues() {
        StringBuilder value = new StringBuilder("a");
        LinkedSequence<StringBuilder> list = filled(value);
        List<StringBuilder> snapshot = list.toList();
        assertThat(snapshot.getFirst()).isSameAs(value);
        snapshot.clear();
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.getFirst()).isSameAs(value);
        assertThat(validStructure(list)).isTrue();
    }

    @Test
    void shouldClearAndReuse() {
        LinkedSequence<Integer> list = filled(1, 2, 3);
        list.clear();
        list.clear();
        assertState(list, List.of());
        list.addFirst(4);
        list.addLast(5);
        assertState(list, List.of(4, 5));
    }

    @Test
    void shouldIterateOneFinitePassAndRejectExhaustedNext() {
        LinkedSequence<Integer> list = filled(1, 2, 3);
        Iterator<Integer> iterator = list.iterator();
        for (int value : new int[]{1, 2, 3}) {
            assertThat(iterator.hasNext()).isTrue();
            assertThat(iterator.next()).isEqualTo(value);
        }
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(create().iterator()::next).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void shouldRejectIteratorRemoval() {
        Iterator<Integer> iterator = this.<Integer>filled(1).iterator();
        iterator.next();
        assertThatThrownBy(iterator::remove).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldInvalidateIteratorsOnStructuralChanges() {
        for (int operation = 0; operation < 5; operation++) {
            LinkedSequence<Integer> list = filled(1, 2, 3);
            Iterator<Integer> iterator = list.iterator();
            switch (operation) {
                case 0 -> list.addLast(4);
                case 1 -> list.removeFirst();
                case 2 -> list.reverse();
                case 3 -> list.splitOff(1);
                case 4 -> list.clear();
                default -> throw new AssertionError();
            }
            assertThatThrownBy(iterator::hasNext).isInstanceOf(ConcurrentModificationException.class);
            assertThatThrownBy(iterator::next).isInstanceOf(ConcurrentModificationException.class);
            assertThat(validStructure(list)).isTrue();
        }
    }

    @Test
    void shouldKeepIteratorsValidAfterValueReplacementAndNoOpChanges() {
        LinkedSequence<Integer> list = filled(1);
        Iterator<Integer> iterator = list.iterator();
        list.set(0, 2);
        list.reverse();
        list.splitOff(list.size());
        assertThat(iterator.next()).isEqualTo(2);
        LinkedSequence<Integer> empty = create();
        Iterator<Integer> emptyIterator = empty.iterator();
        empty.clear();
        empty.reverse();
        empty.splitOff(0);
        assertThat(emptyIterator.hasNext()).isFalse();
    }

    @Test
    void shouldMatchJavaLinkedListAcrossSeededOperationSequences() {
        LinkedSequence<Integer> actual = create();
        LinkedList<Integer> expected = new LinkedList<>();
        SplittableRandom random = new SplittableRandom(20261012L);
        for (int step = 0; step < 2_000; step++) {
            Integer value = random.nextInt(10) == 0 ? null : random.nextInt(-20, 21);
            switch (random.nextInt(12)) {
                case 0 -> { actual.addFirst(value); expected.addFirst(value); }
                case 1 -> { actual.addLast(value); expected.addLast(value); }
                case 2 -> {
                    int index = random.nextInt(expected.size() + 1);
                    actual.add(index, value); expected.add(index, value);
                }
                case 3 -> {
                    if (!expected.isEmpty()) {
                        int index = random.nextInt(expected.size());
                        assertThat(actual.remove(index)).isEqualTo(expected.remove(index));
                    }
                }
                case 4 -> {
                    if (!expected.isEmpty()) {
                        int index = random.nextInt(expected.size());
                        assertThat(actual.set(index, value)).isEqualTo(expected.set(index, value));
                    }
                }
                case 5 -> {
                    if (!expected.isEmpty()) {
                        int index = random.nextInt(expected.size());
                        assertThat(actual.get(index)).isEqualTo(expected.get(index));
                    }
                }
                case 6 -> assertThat(actual.indexOf(value)).isEqualTo(expected.indexOf(value));
                case 7 -> { actual.reverse(); Collections.reverse(expected); }
                case 8 -> {
                    if (!expected.isEmpty()) {
                        assertThat(actual.removeFirst()).isEqualTo(expected.removeFirst());
                    }
                }
                case 9 -> {
                    if (!expected.isEmpty()) {
                        assertThat(actual.removeLast()).isEqualTo(expected.removeLast());
                    }
                }
                case 10 -> {
                    int cut = random.nextInt(expected.size() + 1);
                    LinkedSequence<Integer> suffix = actual.splitOff(cut);
                    assertState(actual, expected.subList(0, cut));
                    assertState(suffix, expected.subList(cut, expected.size()));
                    actual.appendAll(suffix);
                }
                case 11 -> {
                    if (random.nextInt(20) == 0) {
                        actual.clear(); expected.clear();
                    }
                }
                default -> throw new AssertionError();
            }
            assertState(actual, expected);
            if (!expected.isEmpty()) {
                assertThat(actual.getFirst()).isEqualTo(expected.getFirst());
                assertThat(actual.getLast()).isEqualTo(expected.getLast());
            }
        }
    }

    @SafeVarargs
    protected final <E> LinkedSequence<E> filled(E... values) {
        LinkedSequence<E> result = create();
        for (E value : values) {
            result.addLast(value);
        }
        return result;
    }

    protected final <E> void assertState(LinkedSequence<E> actual, List<? extends E> expected) {
        assertThat(validStructure(actual)).as("structural invariant").isTrue();
        assertThat(actual.size()).isEqualTo(expected.size());
        assertThat(actual.isEmpty()).isEqualTo(expected.isEmpty());
        assertThat(actual.toList()).containsExactlyElementsOf(expected);
    }
}
