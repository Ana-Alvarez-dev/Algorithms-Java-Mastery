package org.anaalvarezdev.algorithms.divideandconquer;

import org.junit.jupiter.api.Test;
import java.util.SplittableRandom;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;

class InversionCountTest {
    @Test void rejectsNullInBothVariants() {
        assertThatNullPointerException().isThrownBy(() -> InversionCount.mergeBased(null));
        assertThatNullPointerException().isThrownBy(() -> InversionCount.quadratic(null));
    }

    @Test void handlesBaseCasesDuplicatesAndExtremeValues() {
        check(new int[0], 0);
        check(new int[]{42}, 0);
        check(new int[]{2,2,1}, 2);
        check(new int[]{1,1,1}, 0);
        check(new int[]{2,3,8,6,1}, 5);
        check(new int[]{Integer.MAX_VALUE,0,Integer.MIN_VALUE}, 3);
    }

    @Test void allSmallArraysMatchDirectPairEnumeration() {
        SmallArrays.forEach(values -> check(values, pairOracle(values)));
    }

    @Test void seededFullIntArraysMatchDirectPairEnumeration() {
        SplittableRandom random = new SplittableRandom(20261025);
        for (int sample = 0; sample < 500; sample++) {
            int[] values = random.ints(random.nextInt(101)).toArray();
            check(values, pairOracle(values));
        }
    }

    @Test void reversingExchangesStrictInversionsWithNoninversions() {
        SplittableRandom random = new SplittableRandom(42);
        for (int sample = 0; sample < 200; sample++) {
            int[] values = random.ints(100, -8, 9).toArray();
            int[] reversed = IntStream.range(0, values.length).map(i -> values[values.length - 1 - i]).toArray();
            long unequalPairs = 0;
            for (int i = 0; i < values.length; i++) {
                for (int j = i + 1; j < values.length; j++) {
                    if (values[i] != values[j]) { unequalPairs++; }
                }
            }
            assertThat(InversionCount.mergeBased(values) + InversionCount.mergeBased(reversed)).isEqualTo(unequalPairs);
        }
    }

    @Test void descendingCountExceedsIntWithoutChangingTheInput() {
        int n = 100_000;
        int[] values = IntStream.range(0,n).map(i -> n - i).toArray();
        int[] before = values.clone();
        assertThat(InversionCount.mergeBased(values)).isEqualTo((long) n * (n - 1) / 2);
        assertThat(values).containsExactly(before);
    }

    private static void check(int[] values, long expected) {
        int[] before = values.clone();
        assertThat(InversionCount.mergeBased(values)).isEqualTo(expected);
        assertThat(InversionCount.quadratic(values)).isEqualTo(expected);
        assertThat(values).containsExactly(before);
    }

    private static long pairOracle(int[] values) {
        // Sum independent per-position stream counts, without merging/sorting.
        return IntStream.range(0,values.length).mapToLong(i ->
                IntStream.range(i + 1,values.length).filter(j -> values[i] > values[j]).count()).sum();
    }
}
