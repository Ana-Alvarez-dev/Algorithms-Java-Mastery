package org.anaalvarezdev.algorithms.trees;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.SplittableRandom;
import java.util.TreeSet;
import static org.assertj.core.api.Assertions.assertThat;

abstract class OrderedIntSetContractTest {
    protected abstract AbstractIntSearchTree create();

    @Test void shouldRepresentEveryEmptyQueryWithoutSentinels() {
        var tree = create();
        assertThat(tree.isEmpty()).isTrue(); assertThat(tree.size()).isZero();
        assertThat(tree.height()).isEqualTo(-1); assertThat(tree.contains(0)).isFalse();
        assertThat(tree.remove(0)).isFalse(); assertThat(tree.first()).isEmpty();
        assertThat(tree.last()).isEmpty(); assertThat(tree.floor(0)).isEmpty();
        assertThat(tree.ceiling(0)).isEmpty(); assertThat(tree.toArray()).isEmpty();
        assertThat(tree.hasValidStructure()).isTrue();
    }
    @Test void shouldAddUniqueKeysAndReturnWhetherTheSetChanged() {
        var tree = create();
        assertThat(tree.add(4)).isTrue(); assertThat(tree.add(4)).isFalse();
        assertThat(tree.height()).isZero(); assertThat(tree.contains(4)).isTrue();
        assertThat(tree.remove(4)).isTrue(); assertThat(tree.remove(4)).isFalse();
        assertState(tree, new TreeSet<>());
    }
    @Test void shouldAcceptExtremesWithoutSubtractionOverflow() {
        var tree = create(); var model = new TreeSet<Integer>();
        for (int value : new int[]{0, Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 1}) { tree.add(value); model.add(value); }
        assertState(tree, model);
        assertThat(tree.floor(Integer.MIN_VALUE)).hasValue(Integer.MIN_VALUE);
        assertThat(tree.ceiling(Integer.MAX_VALUE)).hasValue(Integer.MAX_VALUE);
        for (int value : model) { assertThat(tree.remove(value)).isTrue(); }
        assertState(tree, new TreeSet<>());
    }
    @Test void shouldReturnInclusiveNeighboursAndAbsenceAtTheBoundaries() {
        var tree = create(); for (int value : new int[]{20,10,30}) { tree.add(value); }
        assertThat(tree.first()).hasValue(10); assertThat(tree.last()).hasValue(30);
        assertThat(tree.floor(20)).hasValue(20); assertThat(tree.ceiling(20)).hasValue(20);
        assertThat(tree.floor(25)).hasValue(20); assertThat(tree.ceiling(25)).hasValue(30);
        assertThat(tree.floor(9)).isEmpty(); assertThat(tree.ceiling(31)).isEmpty();
        assertThat(tree.floor(31)).hasValue(30); assertThat(tree.ceiling(9)).hasValue(10);
    }
    @Test void shouldReturnAnIndependentSortedSnapshot() {
        var tree = create(); for (int value : new int[]{4,1,9,2}) { tree.add(value); }
        int[] snapshot = tree.toArray(); assertThat(snapshot).containsExactly(1,2,4,9);
        snapshot[0] = 100; assertThat(tree.first()).hasValue(1);
        tree.clear(); assertThat(snapshot).containsExactly(100,2,4,9);
    }
    @Test void shouldPreserveStateOnDuplicateAndMissingUpdates() {
        var tree = create(); for (int value : new int[]{7,2,9,1,5,8,10}) { tree.add(value); }
        int height = tree.height(); int[] before = tree.toArray();
        assertThat(tree.add(5)).isFalse(); assertThat(tree.remove(100)).isFalse();
        assertThat(tree.toArray()).containsExactly(before); assertThat(tree.height()).isEqualTo(height);
        assertThat(tree.hasValidStructure()).isTrue();
    }
    @Test void shouldRemoveLeafAndNodesWithOneOrTwoChildren() {
        var tree = create(); var model = new TreeSet<Integer>();
        for (int value : new int[]{40,20,60,10,30,50,70,55,65}) { tree.add(value); model.add(value); }
        for (int value : new int[]{10,50,60,40,20,30,55,65,70}) {
            assertThat(tree.remove(value)).isEqualTo(model.remove(value)); assertState(tree, model);
        }
    }
    @Test void shouldClearRepeatedlyAndAllowIndependentReuse() {
        var tree = create(); var other = create(); tree.add(1); other.add(8);
        tree.clear(); tree.clear(); assertState(tree, new TreeSet<>());
        assertThat(tree.height()).isEqualTo(-1); tree.add(9);
        assertThat(other.toArray()).containsExactly(8); assertThat(tree.toArray()).containsExactly(9);
    }
    @Test void shouldMatchTreeSetThroughSeededMixedUpdatesAndQueries() {
        var tree = create(); var model = new TreeSet<Integer>();
        var random = new SplittableRandom(20261019);
        for (int step = 0; step < 5000; step++) {
            int key = random.nextInt(-100,101);
            switch (random.nextInt(50)) {
                case 0 -> { tree.clear(); model.clear(); }
                case 1,2,3,4,5,6,7,8,9 -> assertThat(tree.remove(key)).isEqualTo(model.remove(key));
                case 10,11,12,13,14 -> assertThat(tree.contains(key)).isEqualTo(model.contains(key));
                default -> assertThat(tree.add(key)).isEqualTo(model.add(key));
            }
            assertThat(tree.floor(key)).isEqualTo(optional(model.floor(key)));
            assertThat(tree.ceiling(key)).isEqualTo(optional(model.ceiling(key)));
            assertState(tree, model);
        }
    }
    @Test void shouldHandleEverySmallInsertionAndRemovalOrder() {
        int[][] orders = permutations(new int[]{-1,0,1,2});
        for (int[] insertion : orders) for (int[] removal : orders) {
            var tree = create(); var model = new TreeSet<Integer>();
            for (int value : insertion) { tree.add(value); model.add(value); assertState(tree, model); }
            for (int value : removal) { assertThat(tree.remove(value)).isTrue(); model.remove(value); assertState(tree, model); }
        }
    }
    private static OptionalInt optional(Integer value) { return value == null ? OptionalInt.empty() : OptionalInt.of(value); }
    private static void assertState(AbstractIntSearchTree tree, TreeSet<Integer> model) {
        assertThat(tree.hasValidStructure()).isTrue(); assertThat(tree.size()).isEqualTo(model.size());
        assertThat(tree.isEmpty()).isEqualTo(model.isEmpty());
        assertThat(tree.toArray()).containsExactly(model.stream().mapToInt(Integer::intValue).toArray());
        assertThat(tree.first()).isEqualTo(model.isEmpty() ? OptionalInt.empty() : OptionalInt.of(model.first()));
        assertThat(tree.last()).isEqualTo(model.isEmpty() ? OptionalInt.empty() : OptionalInt.of(model.last()));
    }
    private static int[][] permutations(int[] values) {
        java.util.List<int[]> output = new java.util.ArrayList<>(); permute(values, 0, output);
        return output.toArray(int[][]::new);
    }
    private static void permute(int[] values, int index, java.util.List<int[]> output) {
        if (index == values.length) { output.add(Arrays.copyOf(values, values.length)); return; }
        for (int next = index; next < values.length; next++) {
            int temp = values[index]; values[index] = values[next]; values[next] = temp;
            permute(values, index + 1, output);
            temp = values[index]; values[index] = values[next]; values[next] = temp;
        }
    }
}
