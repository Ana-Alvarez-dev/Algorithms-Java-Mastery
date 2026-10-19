package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArrayStackTest extends StackContractTest {
    @Override protected <E> Stack<E> create() { return new ArrayStack<>(1); }
    @Override protected boolean valid(Stack<?> stack) { return ((ArrayStack<?>) stack).hasValidStructure(); }

    @Test
    void shouldGrowRepeatedlyThenClearReferencesWithoutShrinking() {
        ArrayStack<Integer> stack = new ArrayStack<>(1);
        for (int value = 0; value < 5000; value++) { stack.push(value); }
        int retained = stack.capacity();
        assertThat(retained).isGreaterThanOrEqualTo(5000);
        for (int value = 4999; value >= 0; value--) { assertThat(stack.pop()).isEqualTo(value); }
        assertThat(stack.capacity()).isEqualTo(retained);
        assertThat(stack.hasValidStructure()).isTrue();
        stack.push(1); stack.clear();
        assertThat(stack.hasValidStructure()).isTrue();
        assertThat(stack.capacity()).isEqualTo(retained);
    }

    @Test
    void shouldRejectInvalidInitialCapacities() {
        for (int capacity : new int[]{-1, 0, Integer.MAX_VALUE}) {
            assertThatThrownBy(() -> new ArrayStack<>(capacity)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void shouldGuardCapacityOverflowWithoutAllocatingHugeArrays() {
        assertThat(ArrayCapacity.grow(1)).isEqualTo(2);
        assertThat(ArrayCapacity.grow(ArrayCapacity.MAXIMUM / 2 + 1)).isEqualTo(ArrayCapacity.MAXIMUM);
        assertThatThrownBy(() -> ArrayCapacity.grow(ArrayCapacity.MAXIMUM)).isInstanceOf(OutOfMemoryError.class);
        assertThatThrownBy(() -> ArrayCapacity.grow(0)).isInstanceOf(IllegalArgumentException.class);
    }
}
