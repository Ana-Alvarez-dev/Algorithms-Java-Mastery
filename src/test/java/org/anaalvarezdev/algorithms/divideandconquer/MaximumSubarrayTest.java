package org.anaalvarezdev.algorithms.divideandconquer;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Optional;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.*;
import static org.anaalvarezdev.algorithms.divideandconquer.MaximumSubarray.Subarray;

class MaximumSubarrayTest {
    @Test void rejectsNullInBothVariants() {
        assertThatNullPointerException().isThrownBy(() -> MaximumSubarray.divideAndConquer(null));
        assertThatNullPointerException().isThrownBy(() -> MaximumSubarray.linear(null));
    }

    @Test void emptyInputHasNoNonemptyAnswer() {
        check(new int[0], Optional.empty());
    }

    @Test void returnsCanonicalRangesForCrossingNegativeAndTieCases() {
        check(new int[]{-2,1,-3,4,-1,2,1,-5,4}, Optional.of(new Subarray(3,7,6)));
        check(new int[]{-5,-2,-2}, Optional.of(new Subarray(1,2,-2)));
        check(new int[]{0,0,0}, Optional.of(new Subarray(0,1,0)));
        check(new int[]{0,1,0,-1,1}, Optional.of(new Subarray(0,2,1)));
        check(new int[]{1,-1,1}, Optional.of(new Subarray(0,1,1)));
        check(new int[]{Integer.MIN_VALUE}, Optional.of(new Subarray(0,1,Integer.MIN_VALUE)));
    }

    @Test void allSmallArraysMatchIndependentIntervalEnumeration() {
        SmallArrays.forEach(values -> check(values, oracle(values)));
    }

    @Test void seededFullIntArraysMatchIntervalEnumeration() {
        SplittableRandom random = new SplittableRandom(20261025);
        for (int sample = 0; sample < 500; sample++) {
            int[] values = random.ints(random.nextInt(81)).toArray();
            check(values, oracle(values));
        }
    }

    @Test void exactLongSumOnLargeInputPreservesTheArray() {
        int[] values = new int[200_000];
        Arrays.fill(values, Integer.MAX_VALUE);
        check(values, Optional.of(new Subarray(0, values.length, (long) Integer.MAX_VALUE * values.length)));
        check(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE},
                oracle(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE}));
    }

    private static void check(int[] values, Optional<Subarray> expected) {
        int[] before = values.clone();
        assertThat(MaximumSubarray.divideAndConquer(values)).isEqualTo(expected);
        assertThat(MaximumSubarray.linear(values)).isEqualTo(expected);
        assertThat(values).containsExactly(before);
    }

    // Enumerate bounds in tie order; only a strictly better sum replaces them.
    private static Optional<Subarray> oracle(int[] values) {
        Subarray answer = null;
        for (int from = 0; from < values.length; from++) {
            long sum = 0;
            for (int to = from + 1; to <= values.length; to++) {
                sum += values[to - 1];
                if (answer == null || sum > answer.sum()) {
                    answer = new Subarray(from, to, sum);
                }
            }
        }
        return Optional.ofNullable(answer);
    }
}
