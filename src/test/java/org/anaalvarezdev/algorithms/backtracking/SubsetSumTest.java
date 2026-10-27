package org.anaalvarezdev.algorithms.backtracking;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.*;

class SubsetSumTest {
    @Test void rejectsNullAndWorkCapBeforeExploration() {
        assertThatNullPointerException().isThrownBy(() -> SubsetSum.exists(null,0));
        assertThatNullPointerException().isThrownBy(() -> SubsetSum.existsWithoutBounds(null,0));
        assertThatIllegalArgumentException().isThrownBy(() -> SubsetSum.exists(new int[25],0));
        assertThatIllegalArgumentException().isThrownBy(() -> SubsetSum.existsWithoutBounds(new int[25],0));
        check(new int[24],0,true);
    }

    @Test void emptySubsetZerosAndFullLongTargetsHaveDefinedResults() {
        check(new int[0],0,true);
        check(new int[0],1,false);
        check(new int[]{0,0,0},0,true);
        check(new int[]{0,0,0},1,false);
        check(new int[]{-1,0,1},Long.MIN_VALUE,false);
        check(new int[]{-1,0,1},Long.MAX_VALUE,false);
    }

    @Test void signedBoundsDoNotPruneOvershootsThatCanBeRepaired() {
        check(new int[]{8,-9},-1,true);
        check(new int[]{-3,2,4},3,true);
        check(new int[]{4,4},8,true);
        check(new int[]{2,2,2},5,false);
        check(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},3L * Integer.MAX_VALUE,true);
        check(new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE},2L * Integer.MIN_VALUE,true);
    }

    @Test void allSmallSignedArraysMatchIterativeReachableSumSets() {
        for (int length = 0; length <= 6; length++) { enumerate(new int[length],0); }
    }

    @Test void seededFullIntArraysMatchIndependentReachableSets() {
        SplittableRandom random = new SplittableRandom(20261026);
        for (int sample = 0; sample < 100; sample++) {
            int[] values = random.ints(random.nextInt(17)).toArray();
            Set<Long> sums = reachable(values);
            long chosen = 0;
            for (int value : values) { if (random.nextBoolean()) { chosen += value; } }
            check(values,chosen,true);
            check(values,chosen + 1,sums.contains(chosen + 1));
            check(values,Long.MAX_VALUE,false);
        }
    }

    @Test void reversingAndNegatingTheSmallDomainPreservesFeasibility() {
        int[] values = {-3,1,2,4};
        int[] reversed = {4,2,1,-3};
        int[] negative = {3,-1,-2,-4};
        for (long target = -8; target <= 8; target++) {
            boolean expected = reachable(values).contains(target);
            check(values,target,expected);
            check(reversed,target,expected);
            check(negative,-target,expected);
        }
    }

    @Test void earlySuccessAndFailureDoNotMutateOrShareSearchState() {
        int[] values = {7,-4,2}; int[] before = values.clone();
        check(values,3,true);
        check(values,99,false);
        check(new int[]{1},1,true);
        check(values,3,true);
        assertThat(values).containsExactly(before);
    }

    private static void enumerate(int[] values, int index) {
        if (index == values.length) {
            Set<Long> expected = reachable(values);
            for (long target = -8; target <= 8; target++) { check(values,target,expected.contains(target)); }
            return;
        }
        for (int value = -1; value <= 1; value++) { values[index] = value; enumerate(values,index + 1); }
    }

    private static Set<Long> reachable(int[] values) {
        Set<Long> sums = new HashSet<>(); sums.add(0L);
        for (int value : values) {
            Set<Long> next = new HashSet<>(sums);
            for (long sum : sums) { next.add(sum + value); }
            sums = next;
        }
        return sums;
    }

    private static void check(int[] values, long target, boolean expected) {
        int[] before = values.clone();
        assertThat(SubsetSum.exists(values,target)).isEqualTo(expected);
        assertThat(SubsetSum.existsWithoutBounds(values,target)).isEqualTo(expected);
        assertThat(values).containsExactly(before);
    }
}
