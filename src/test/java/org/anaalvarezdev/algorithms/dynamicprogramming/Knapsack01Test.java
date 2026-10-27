package org.anaalvarezdev.algorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;

class Knapsack01Test {
    @Test void rejectsNullInvalidDimensionsAndWorkDomainForEveryVariant() {
        int[] tooManyItems = new int[257];
        int[] excessiveGrid = new int[100];
        Arrays.fill(tooManyItems, 1);
        Arrays.fill(excessiveGrid, 1);
        for (int variant = 0; variant < 4; variant++) {
            int v = variant;
            assertThatNullPointerException().isThrownBy(() -> run(v, null, new int[0], 0));
            assertThatNullPointerException().isThrownBy(() -> run(v, new int[0], null, 0));
            for (int capacity : new int[]{-1, 10_001, Integer.MAX_VALUE}) {
                assertThatIllegalArgumentException().isThrownBy(() -> run(v, new int[0], new int[0], capacity));
            }
            assertThatIllegalArgumentException().isThrownBy(() -> run(v, new int[]{1}, new int[0], 0));
            assertThatIllegalArgumentException().isThrownBy(() -> run(v, tooManyItems, new int[257], 0));
            assertThatIllegalArgumentException().isThrownBy(() -> run(v, excessiveGrid, new int[100], 10_000));
            for (int weight : new int[]{-1, 0}) {
                assertThatIllegalArgumentException().isThrownBy(() -> run(v, new int[]{weight}, new int[]{1}, 1));
            }
            assertThatIllegalArgumentException().isThrownBy(() -> run(v, new int[]{1}, new int[]{-1}, 1));
        }
    }

    @Test void emptyZeroCapacityAndUnavailableItemsHaveEmptyOptimum() {
        check(new int[0], new int[0], 0);
        check(new int[0], new int[0], 10);
        check(new int[]{1, 2}, new int[]{9, 5}, 0);
        check(new int[]{3, Integer.MAX_VALUE}, new int[]{5, 9}, 2);
        check(new int[]{1, 1}, new int[]{0, 0}, 2);
    }

    @Test void densityGreedyCounterexampleReturnsTheExactPlan() {
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        check(weights, values, 50);
        assertThat(Knapsack01.select(weights, values, 50)).isEqualTo(new Knapsack01.Selection(220, 50, List.of(1, 2)));
    }

    @Test void tiesOmitHighestIndexAndRollingDoesNotReuseAnItem() {
        assertThat(Knapsack01.select(new int[]{2, 1}, new int[]{3, 3}, 2))
                .isEqualTo(new Knapsack01.Selection(3, 2, List.of(0))); // not minimum weight
        assertThat(Knapsack01.select(new int[]{1, 1, 1}, new int[]{4, 4, 4}, 2).indices()).isEqualTo(List.of(0, 1));
        check(new int[]{2}, new int[]{3}, 4);
        assertThat(Knapsack01.valueRolling(new int[]{2}, new int[]{3}, 4)).isEqualTo(3);
    }

    @Test void allSmallItemArraysAndCapacitiesMatchExhaustiveSubsets() {
        for (int n = 0; n <= 4; n++) { enumerate(new int[n], new int[n], 0); }
    }

    @Test void seededItemsMatchAllSubsetsAndPreserveInputs() {
        SplittableRandom random = new SplittableRandom(20261026);
        for (int sample = 0; sample < 100; sample++) {
            int n = random.nextInt(13);
            check(random.ints(n, 1, 11).toArray(), random.ints(n, 0, 101).toArray(), random.nextInt(41));
        }
    }

    @Test void acceptedGridAndItemBoundariesKeepLongValuesExact() {
        int[] weights = new int[256];
        int[] values = new int[256];
        Arrays.fill(weights, 1);
        Arrays.fill(values, Integer.MAX_VALUE);
        long expected = 256L * Integer.MAX_VALUE;
        for (int v = 0; v < 4; v++) { assertThat(run(v, weights, values, 256)).isEqualTo(expected); }
        assertThat(Knapsack01.select(weights, values, 256).indices()).isEqualTo(IntStream.range(0, 256).boxed().toList());
        weights = new int[99];
        values = new int[99];
        Arrays.fill(weights, 10_000); // exactly 100*10000=1000000 grid cells
        Arrays.fill(values, 1);
        for (int v = 0; v < 4; v++) { assertThat(run(v, weights, values, 9999)).isZero(); }
        assertThat(Knapsack01.valueRolling(new int[0], new int[0], 10_000)).isZero();
    }

    @Test void moreCapacityAndAnUnavailableAddedItemRespectMetamorphicRelations() {
        int[] weights = {2, 3, 4};
        int[] values = {3, 5, 6};
        long previous = 0;
        for (int c = 0; c <= 12; c++) {
            long current = Knapsack01.valueRolling(weights, values, c);
            assertThat(current).isGreaterThanOrEqualTo(previous);
            assertThat(Knapsack01.valueMemoised(new int[]{2, 3, 4, 100}, new int[]{3, 5, 6, 999}, c)).isEqualTo(current);
            previous = current;
        }
    }

    @Test void selectedIndicesAreImmutableAndRepeatedCallsDoNotShareState() {
        int[] weights = {2, 3};
        int[] values = {4, 6};
        var before = Knapsack01.select(weights, values, 5);
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> before.indices().clear());
        Knapsack01.select(new int[]{1}, new int[]{99}, 1);
        assertThat(Knapsack01.select(weights, values, 5)).isEqualTo(before);
        values[0] = 999;
        assertThat(before.value()).isEqualTo(10);
        assertThat(before.indices()).isEqualTo(List.of(0, 1));
    }

    private static void enumerate(int[] weights, int[] values, int index) {
        if (index == weights.length) {
            for (int c = 0; c <= 8; c++) { check(weights, values, c); }
            return;
        }
        for (int weight = 1; weight <= 3; weight++) {
            for (int value = 0; value <= 2; value++) {
                weights[index] = weight;
                values[index] = value;
                enumerate(weights, values, index + 1);
            }
        }
    }

    private static void check(int[] weights, int[] values, int capacity) {
        int[] oldWeights = weights.clone();
        int[] oldValues = values.clone();
        long best = 0;
        int bestMask = 0;
        int bestWeight = 0;
        for (int mask = 0; mask < (1 << weights.length); mask++) {
            long value = 0;
            long weight = 0;
            for (int i = 0; i < weights.length; i++) {
                if ((mask & (1 << i)) != 0) { value += values[i]; weight += weights[i]; }
            }
            if (weight <= capacity && value > best) {
                best = value; bestMask = mask; bestWeight = (int) weight;
            }
        }
        for (int variant = 0; variant < 4; variant++) {
            assertThat(run(variant, weights, values, capacity)).isEqualTo(best);
        }
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) { if ((bestMask & (1 << i)) != 0) { indices.add(i); } }
        assertThat(Knapsack01.select(weights, values, capacity)).isEqualTo(new Knapsack01.Selection(best, bestWeight, indices));
        assertThat(weights).containsExactly(oldWeights);
        assertThat(values).containsExactly(oldValues);
    }

    private static long run(int variant, int[] weights, int[] values, int capacity) {
        return switch (variant) {
            case 0 -> Knapsack01.valueMemoised(weights, values, capacity);
            case 1 -> Knapsack01.valueTabulated(weights, values, capacity);
            case 2 -> Knapsack01.valueRolling(weights, values, capacity);
            default -> Knapsack01.select(weights, values, capacity).value();
        };
    }
}
