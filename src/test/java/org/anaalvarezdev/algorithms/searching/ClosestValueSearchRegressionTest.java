package org.anaalvarezdev.algorithms.searching;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.SplittableRandom;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;

class ClosestValueSearchRegressionTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("closestCases")
    void returnsLowestIndexAcrossContractPartitions(String name, int[] values, int target, int expected) {
        int[] original = values.clone();
        assertThat(ClosestValueSearch.search(values, target)).as(name).isEqualTo(expected);
        assertThat(values).as("input preserved: %s", name).containsExactly(original);
    }

    static Stream<Arguments> closestCases() {
        return Stream.of(
                scenario("exact duplicates").values(10, 20, 20, 20, 30).target(20).expected(1).build(),
                scenario("repeated lower neighbour").values(10, 20, 20, 40).target(25).expected(1).build(),
                scenario("repeated maximum").values(10, 20, 20).target(100).expected(1).build(),
                scenario("tied repeated groups").values(0, 0, 10, 10).target(5).expected(0).build(),
                scenario("repeated upper neighbour").values(0, 10, 10, 20).target(9).expected(1).build(),
                scenario("all equal").values(7, 7, 7, 7).target(8).expected(0).build(),
                scenario("minimum exact duplicates").values(Integer.MIN_VALUE, Integer.MIN_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE).target(Integer.MIN_VALUE).expected(0).build(),
                scenario("maximum exact duplicates").values(Integer.MIN_VALUE, Integer.MIN_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE).target(Integer.MAX_VALUE).expected(3).build(),
                scenario("full-width distances").values(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE).target(0).expected(2).build(),
                scenario("negative repeated tie").values(-10, -10, -4, -4).target(-7).expected(0).build(),
                scenario("singleton extremes").values(Integer.MAX_VALUE).target(Integer.MIN_VALUE).expected(0).build(),
                scenario("empty input").target(0).build()
        ).map(c -> Arguments.of(c.name(), c.input(), c.target(), c.expected()));
    }

    @Test void allSmallSortedMultisetsMatchIndependentLinearArgmin() {
        for (int n = 0; n <= 7; n++) { enumerate(new int[n], 0, -2); }
    }

    @Test void seededFullIntAndDuplicateArraysMatchOracleWithoutMutation() {
        SplittableRandom random = new SplittableRandom(20261027);
        for (int sample = 0; sample < 300; sample++) {
            int[] pool = random.ints(8).toArray();
            pool[0] = Integer.MIN_VALUE;
            pool[1] = Integer.MAX_VALUE;
            int[] values = new int[random.nextInt(130)];
            for (int i = 0; i < values.length; i++) { values[i] = pool[random.nextInt(pool.length)]; }
            Arrays.sort(values);
            check(values, random.nextInt());
            check(values, Integer.MIN_VALUE);
            check(values, Integer.MAX_VALUE);
            if (values.length > 0) { check(values, values[values.length / 2]); }
        }
    }

    @Test void safeTranslationAndPositiveScalingPreserveTheChosenIndex() {
        int[] values = {-100, -100, 0, 20, 20, 80};
        int[] translated = Arrays.stream(values).map(v -> v + 1000).toArray();
        int[] scaled = Arrays.stream(values).map(v -> v * 3).toArray();
        for (int target = -120; target <= 100; target += 5) {
            int expected = oracle(values, target);
            assertThat(ClosestValueSearch.search(values, target)).isEqualTo(expected);
            assertThat(ClosestValueSearch.search(translated, target + 1000)).isEqualTo(expected);
            assertThat(ClosestValueSearch.search(scaled, target * 3)).isEqualTo(expected);
        }
    }

    @Test void fixtureBuilderAndInputsStayIsolatedAcrossInvocations() {
        int[] caller = {1, 1, 9};
        var builder = scenario("isolated fixture").values(caller).target(2).expected(0);
        var first = builder.build();
        caller[0] = 999;
        int[] input = first.input();
        input[0] = -99;
        builder.values(3, 4).target(4).expected(1);
        assertThat(first.input()).containsExactly(1, 1, 9);
        assertThat(first.target()).isEqualTo(2);
        assertThat(ClosestValueSearch.search(first.input(), first.target())).isEqualTo(first.expected());
        var second = builder.build();
        assertThat(ClosestValueSearch.search(second.input(), second.target())).isEqualTo(second.expected());
        assertThat(first.input()).containsExactly(1, 1, 9);
    }

    private static ClosestSearchCaseBuilder scenario(String name) { return new ClosestSearchCaseBuilder(name); }

    private static void enumerate(int[] values, int index, int minimum) {
        if (index == values.length) {
            for (int target = -4; target <= 4; target++) { check(values, target); }
            check(values, Integer.MIN_VALUE);
            check(values, Integer.MAX_VALUE);
            return;
        }
        for (int value = minimum; value <= 2; value++) {
            values[index] = value;
            enumerate(values, index + 1, value);
        }
    }

    private static void check(int[] values, int target) {
        int[] before = values.clone();
        assertThat(ClosestValueSearch.search(values, target))
                .as("linear argmin: %s, target=%d", Arrays.toString(values), target)
                .isEqualTo(oracle(values, target));
        assertThat(values).containsExactly(before);
    }

    private static int oracle(int[] values, int target) {
        int best = -1;
        long distance = Long.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            long candidate = Math.abs((long) values[i] - target);
            if (candidate < distance) { best = i; distance = candidate; }
        }
        return best;
    }
}
