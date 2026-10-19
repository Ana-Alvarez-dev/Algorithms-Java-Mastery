package org.anaalvarezdev.algorithms.trees;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class IntAvlTreeTest extends OrderedIntSetContractTest {
    @Override protected IntAvlTree create() { return new IntAvlTree(); }
    @Test void shouldRestoreBalanceForAllFourInsertionRotations() {
        for (int[] order : new int[][]{{3,2,1},{1,2,3},{3,1,2},{1,3,2}}) {
            var tree = create(); for (int value : order) { tree.add(value); }
            assertThat(tree.height()).isEqualTo(1); assertThat(tree.toArray()).containsExactly(1,2,3);
            assertThat(tree.hasValidStructure()).isTrue();
        }
    }
    @Test void shouldHandleDeletionWithZeroBalanceInTheHeavyChild() {
        for (int[] order : new int[][]{{4,2,5,1,3},{2,1,4,3,5}}) {
            var tree = create(); for (int value : order) { tree.add(value); }
            tree.remove(order[2] == 5 ? 5 : 1);
            assertThat(tree.size()).isEqualTo(4); assertThat(tree.height()).isEqualTo(2);
            assertThat(tree.hasValidStructure()).isTrue();
        }
    }
    @Test void shouldRetainLogarithmicHeightAcrossSortedGrowthAndDeletion() {
        var tree = create();
        for (int value = 0; value < 10000; value++) { tree.add(value); }
        assertThat(tree.height()).isLessThanOrEqualTo(18); assertThat(tree.hasValidStructure()).isTrue();
        for (int value = 0; value < 10000; value++) {
            assertThat(tree.remove(value)).isTrue();
            if (value % 137 == 0) { assertThat(tree.hasValidStructure()).isTrue(); }
        }
        assertThat(tree.height()).isEqualTo(-1); assertThat(tree.hasValidStructure()).isTrue();
    }
    @Test void shouldRebalanceAfterAnAlternatingExtremaDrain() {
        var tree = create(); for (int value = 255; value >= 0; value--) { tree.add(value); }
        for (int low = 0; low < 128; low++) {
            tree.remove(low); tree.remove(255 - low); assertThat(tree.hasValidStructure()).isTrue();
        }
        assertThat(tree.isEmpty()).isTrue();
    }
}
