package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SplittableRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

abstract class StackContractTest {
    protected abstract <E> Stack<E> create();
    protected abstract boolean valid(Stack<?> stack);

    @Test
    void shouldDistinguishEmptySentinelsFromStrictOperations() {
        Stack<Integer> stack = create();
        assertState(stack, List.of());
        assertThat(stack.peek()).isNull();
        assertThat(stack.poll()).isNull();
        assertThatThrownBy(stack::pop).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(stack::element).isInstanceOf(NoSuchElementException.class);
        assertState(stack, List.of());
    }

    @Test
    void shouldRemoveInLifoOrderIncludingDuplicates() {
        Stack<Integer> stack = create();
        for (int value : new int[]{1, 2, 2, 3}) { stack.push(value); }
        for (int value : new int[]{3, 2, 2, 1}) {
            int count = stack.size();
            assertThat(stack.peek()).isEqualTo(value);
            assertThat(stack.element()).isEqualTo(value);
            assertThat(stack.size()).isEqualTo(count);
            assertThat(stack.pop()).isEqualTo(value);
            assertThat(valid(stack)).isTrue();
        }
        assertState(stack, List.of());
    }

    @Test
    void shouldRejectNullWithoutMutation() {
        Stack<Integer> stack = create();
        assertThatThrownBy(() -> stack.push(null)).isInstanceOf(NullPointerException.class);
        stack.push(7);
        assertThatThrownBy(() -> stack.push(null)).isInstanceOf(NullPointerException.class);
        assertState(stack, List.of(7));
    }

    @Test
    void shouldExposeIndependentMutableShallowSnapshot() {
        Stack<StringBuilder> stack = create();
        StringBuilder value = new StringBuilder("a");
        stack.push(value);
        List<StringBuilder> snapshot = stack.toList();
        assertThat(snapshot.getFirst()).isSameAs(value);
        snapshot.clear();
        assertThat(stack.peek()).isSameAs(value);
        List<StringBuilder> retained = stack.toList();
        stack.pop();
        assertThat(retained).containsExactly(value);
        assertThat(valid(stack)).isTrue();
    }

    @Test
    void shouldClearAndReuseWithoutRetainingLogicalValues() {
        Stack<Integer> stack = create();
        stack.clear();
        for (int index = 0; index < 50; index++) { stack.push(index); }
        stack.clear();
        assertState(stack, List.of());
        stack.push(9);
        assertThat(stack.poll()).isEqualTo(9);
        assertState(stack, List.of());
    }

    @Test
    void shouldRestoreContentsAfterPushPopPair() {
        Stack<String> stack = create();
        stack.push("old");
        List<String> before = stack.toList();
        stack.push("new");
        assertThat(stack.pop()).isEqualTo("new");
        assertState(stack, before);
    }

    @Test
    void shouldKeepInstancesIndependent() {
        Stack<String> first = create();
        Stack<String> second = create();
        first.push("a");
        second.push("b");
        first.clear();
        assertState(first, List.of());
        assertState(second, List.of("b"));
    }

    @Test
    void shouldMatchArrayDequeThroughSeededOperationSequences() {
        Stack<Integer> stack = create();
        ArrayDeque<Integer> model = new ArrayDeque<>();
        SplittableRandom random = new SplittableRandom(20261019);
        for (int step = 0; step < 3000; step++) {
            int operation = random.nextInt(10);
            if (operation < 5) {
                int value = random.nextInt(-3, 4);
                stack.push(value); model.push(value);
            } else if (operation < 8) {
                assertThat(stack.poll()).isEqualTo(model.pollFirst());
            } else if (operation == 8) {
                assertThat(stack.peek()).isEqualTo(model.peekFirst());
            } else {
                stack.clear(); model.clear();
            }
            assertState(stack, new ArrayList<>(model));
        }
    }

    private <E> void assertState(Stack<E> stack, List<E> expected) {
        assertThat(valid(stack)).isTrue();
        assertThat(stack.size()).isEqualTo(expected.size());
        assertThat(stack.isEmpty()).isEqualTo(expected.isEmpty());
        assertThat(stack.toList()).containsExactlyElementsOf(expected);
    }
}
