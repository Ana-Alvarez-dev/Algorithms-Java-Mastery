package org.anaalvarezdev.algorithms.trees;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.PriorityQueue;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IntMinHeapTest {
    @Test void shouldStartEmptyWithUnambiguousAbsence() {
        var heap = new IntMinHeap(0);
        assertThat(heap.capacity()).isEqualTo(1); assertThat(heap.size()).isZero();
        assertThat(heap.isEmpty()).isTrue(); assertThat(heap.peek()).isEmpty();
        assertThat(heap.poll()).isEmpty(); assertThat(heap.toArray()).isEmpty();
        assertThat(heap.hasValidStructure()).isTrue();
    }
    @Test void shouldRejectInvalidCapacityAndNullInput() {
        assertThatThrownBy(() -> new IntMinHeap(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new IntMinHeap(IntArrayCapacity.MAXIMUM + 1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new IntMinHeap((int[]) null)).isInstanceOf(NullPointerException.class);
    }
    @Test void shouldGrowAndDrainInPriorityOrderWithMultiplicity() {
        var heap = new IntMinHeap(1);
        for (int value : new int[]{5,1,1,9,-2,0,8}) { heap.offer(value); assertThat(heap.hasValidStructure()).isTrue(); }
        assertThat(heap.capacity()).isGreaterThanOrEqualTo(7);
        assertThat(drain(heap)).containsExactly(-2,0,1,1,5,8,9); assertThat(heap.poll()).isEmpty();
    }
    @Test void shouldAcceptIntegerExtremesWithoutComparatorOverflow() {
        var heap = new IntMinHeap(new int[]{Integer.MAX_VALUE,0,Integer.MIN_VALUE,-1,1});
        assertThat(drain(heap)).containsExactly(Integer.MIN_VALUE,-1,0,1,Integer.MAX_VALUE);
    }
    @Test void shouldCopyInputAndExposeIndependentHeapOrderSnapshots() {
        int[] values = {2,9,3,10}; var heap = new IntMinHeap(values); values[0] = -100;
        assertThat(heap.peek()).hasValue(2); int[] copy = heap.toArray();
        assertThat(copy).containsExactly(2,9,3,10); copy[0] = 100;
        assertThat(heap.peek()).hasValue(2); heap.offer(1); assertThat(copy).hasSize(4);
    }
    @Test void shouldClearRetainCapacityAndPermitReuse() {
        var heap = new IntMinHeap(new int[]{3,2,1}); int capacity = heap.capacity();
        heap.clear(); heap.clear(); assertThat(heap.capacity()).isEqualTo(capacity);
        assertThat(heap.peek()).isEmpty(); assertThat(heap.toArray()).isEmpty();
        heap.offer(9); assertThat(heap.poll()).hasValue(9); assertThat(heap.hasValidStructure()).isTrue();
    }
    @Test void shouldKeepIndependentInstancesAndHandleSingletonReuse() {
        var a = new IntMinHeap(); var b = new IntMinHeap(); a.offer(1); b.offer(2);
        for (int iteration = 0; iteration < 100; iteration++) { assertThat(a.poll()).hasValue(1); a.offer(1); }
        assertThat(b.peek()).hasValue(2); assertThat(a.size()).isEqualTo(1);
    }
    @Test void shouldUseTheSmallerChildIncludingOddSizedHeaps() {
        var heap = new IntMinHeap(new int[]{1,6,2,9,8,3,4});
        assertThat(drain(heap)).containsExactly(1,2,3,4,6,8,9);
        var even = new IntMinHeap(new int[]{4,3,2,1}); assertThat(drain(even)).containsExactly(1,2,3,4);
    }
    @Test void shouldMatchPriorityQueueThroughSeededMixedOperations() {
        var heap = new IntMinHeap(0); var model = new PriorityQueue<Integer>();
        var random = new SplittableRandom(20261019);
        for (int step = 0; step < 5000; step++) {
            int action = random.nextInt(40);
            if (action == 0) { heap.clear(); model.clear();
            } else if (action < 15) { assertThat(heap.poll()).isEqualTo(optional(model.poll()));
            } else { int value = random.nextInt(-100,101); heap.offer(value); model.offer(value); }
            assertThat(heap.hasValidStructure()).isTrue(); assertThat(heap.size()).isEqualTo(model.size());
            assertThat(heap.peek()).isEqualTo(optional(model.peek()));
            int[] snapshot = heap.toArray(); Arrays.sort(snapshot);
            assertThat(snapshot).containsExactly(model.stream().mapToInt(Integer::intValue).sorted().toArray());
        }
    }
    @Test void shouldBuildAndDrainEverySmallArrayWithoutMutatingIt() {
        var inputs = SmallInputs.arrays(); assertThat(inputs).hasSize(1093);
        for (int[] input : inputs) {
            int[] before = input.clone(); int[] expected = input.clone(); Arrays.sort(expected);
            var heap = new IntMinHeap(input); assertThat(heap.hasValidStructure()).isTrue();
            assertThat(drain(heap)).containsExactly(expected); assertThat(input).containsExactly(before);
        }
    }
    @Test void shouldBuildDescendingAndEqualInputsAcrossSeveralGrowthBoundaries() {
        for (boolean equal : new boolean[]{false,true}) {
            int[] input = new int[1000]; for (int i = 0; i < input.length; i++) { input[i] = equal ? 7 : 1000 - i; }
            var heap = new IntMinHeap(0); for (int value : input) { heap.offer(value); }
            Arrays.sort(input); assertThat(drain(heap)).containsExactly(input);
        }
    }
    private static OptionalInt optional(Integer value) { return value == null ? OptionalInt.empty() : OptionalInt.of(value); }
    private static int[] drain(IntMinHeap heap) {
        int[] output = new int[heap.size()];
        for (int index = 0; index < output.length; index++) { output[index] = heap.poll().orElseThrow(); assertThat(heap.hasValidStructure()).isTrue(); }
        return output;
    }
}
