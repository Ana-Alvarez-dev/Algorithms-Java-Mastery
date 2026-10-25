package org.anaalvarezdev.algorithms.divideandconquer;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.SplittableRandom;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;

class QuickSelectTest {
    @Test void rejectsNullBeforeRankValidation() {
        assertThatNullPointerException().isThrownBy(() -> QuickSelect.select(null, -1));
    }

    @Test void rejectsInvalidRanksBeforeMutation() {
        for (int[] values : new int[][]{new int[0], {3,1,2}}) {
            int[] before = values.clone();
            for (int rank : new int[]{Integer.MIN_VALUE,-1,values.length,Integer.MAX_VALUE}) {
                assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> QuickSelect.select(values,rank));
                assertThat(values).containsExactly(before);
            }
        }
    }

    @Test void allSmallArraysAndRanksMatchSortedCopiesAndPartitionContract() {
        SmallArrays.forEach(QuickSelectTest::checkEveryRank);
    }

    @Test void seededFullIntArraysAndAllRanksMatchSortedCopies() {
        SplittableRandom random = new SplittableRandom(20261025);
        for (int sample = 0; sample < 200; sample++) {
            checkEveryRank(random.ints(random.nextInt(1,65)).toArray());
        }
    }

    @Test void extremeValuesAndStructuredShapesRetainPermutation() {
        checkEveryRank(new int[]{Integer.MAX_VALUE,0,Integer.MIN_VALUE,Integer.MAX_VALUE,-1});
        int n = 10_001;
        int[][] shapes = {
            IntStream.range(0,n).toArray(),
            IntStream.range(0,n).map(i -> n - i).toArray(),
            IntStream.range(0,n).map(i -> Math.min(i,n - 1 - i)).toArray(),
            IntStream.range(0,n).map(i -> i % 7).toArray()
        };
        for (int[] shape : shapes) {
            int[] sorted = shape.clone();
            Arrays.sort(sorted);
            for (int rank : new int[]{0,n / 2,n - 1}) { check(shape,sorted,rank); }
        }
    }

    @Test void largeEqualArrayAcceptsFirstMiddleAndLastRank() {
        int[] values = new int[200_000];
        Arrays.fill(values, -42);
        for (int rank : new int[]{0,values.length / 2,values.length - 1}) {
            assertThat(QuickSelect.select(values,rank)).isEqualTo(-42);
        }
        assertThat(values).containsOnly(-42);
    }

    private static void checkEveryRank(int[] original) {
        int[] sorted = original.clone();
        Arrays.sort(sorted);
        for (int rank = 0; rank < original.length; rank++) { check(original,sorted,rank); }
    }

    private static void check(int[] original, int[] sorted, int rank) {
        int[] values = original.clone();
        assertThat(QuickSelect.select(values,rank)).isEqualTo(sorted[rank]);
        assertThat(values[rank]).isEqualTo(sorted[rank]);
        for (int i = 0; i < rank; i++) { assertThat(values[i]).isLessThanOrEqualTo(values[rank]); }
        for (int i = rank + 1; i < values.length; i++) { assertThat(values[i]).isGreaterThanOrEqualTo(values[rank]); }
        Arrays.sort(values);
        assertThat(values).containsExactly(sorted);
    }
}
