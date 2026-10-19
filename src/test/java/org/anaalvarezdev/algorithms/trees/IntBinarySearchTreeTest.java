package org.anaalvarezdev.algorithms.trees;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class IntBinarySearchTreeTest extends OrderedIntSetContractTest {
    @Override protected IntBinarySearchTree create() { return new IntBinarySearchTree(); }
    @Test void shouldExposeInsertionOrderThroughHeight() {
        var sorted = create(); for (int value = 0; value < 1000; value++) { sorted.add(value); }
        assertThat(sorted.height()).isEqualTo(999); assertThat(sorted.hasValidStructure()).isTrue();
        assertThat(sorted.contains(999)).isTrue(); assertThat(sorted.contains(1000)).isFalse();
        assertThat(sorted.toArray()).hasSize(1000);
        for (int value = 999; value >= 0; value--) { assertThat(sorted.remove(value)).isTrue(); }
        assertThat(sorted.height()).isEqualTo(-1);
    }
    @Test void shouldRepairTheSuccessorsRightChild() {
        var tree = create(); for (int value : new int[]{40,20,80,60,90,50,70,55}) { tree.add(value); }
        tree.remove(40); assertThat(tree.toArray()).containsExactly(20,50,55,60,70,80,90);
        assertThat(tree.hasValidStructure()).isTrue();
    }
    @Test void shouldHandleImmediateSuccessorAndRootWithOneChild() {
        var tree = create(); for (int value : new int[]{2,1,3,4}) { tree.add(value); }
        tree.remove(2); tree.remove(1); tree.remove(3);
        assertThat(tree.toArray()).containsExactly(4); assertThat(tree.height()).isZero();
        assertThat(tree.hasValidStructure()).isTrue();
    }
}
