package org.anaalvarezdev.algorithms.greedy;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.*;

class IntervalPartitioningTest {
    @Test void rejectsNullListsAndNullItems() {
        assertThatNullPointerException().isThrownBy(() -> IntervalPartitioning.assign(null));
        assertThatNullPointerException().isThrownBy(() -> IntervalPartitioning.assign(Arrays.asList(new Interval(0,1),null)));
    }

    @Test void emptyAndAssignmentOwnershipAreExplicit() {
        assertThat(IntervalPartitioning.assign(List.of())).isEqualTo(new IntervalPartitioning.Assignment(List.of(),0));
        List<Integer> original = new ArrayList<>(List.of(0));
        var assignment = new IntervalPartitioning.Assignment(original,1);
        original.set(0,99);
        assertThat(assignment.roomByInputIndex()).containsExactly(0);
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> assignment.roomByInputIndex().clear());
    }

    @Test void touchingDuplicatesAndExtremeTimesUseTheMinimumRooms() {
        check(List.of(new Interval(-2,0),new Interval(0,2),new Interval(2,3)));
        List<Interval> repeated = List.of(new Interval(0,2),new Interval(0,2),new Interval(0,2));
        assertThat(IntervalPartitioning.assign(repeated).roomCount()).isEqualTo(3);
        check(repeated);
        check(List.of(new Interval(Integer.MIN_VALUE,0),new Interval(0,Integer.MAX_VALUE)));
    }

    @Test void everySmallIntervalSetMatchesIndependentPeakOverlap() {
        SmallSchedules.forEach(IntervalPartitioningTest::check);
    }

    @Test void seededSchedulesGiveLegalOptimalAssignments() {
        SplittableRandom random = new SplittableRandom(20261026);
        for (int sample = 0; sample < 500; sample++) {
            List<Interval> values = new ArrayList<>();
            int size = random.nextInt(101);
            for (int i = 0; i < size; i++) {
                int from = random.nextInt(-100,101);
                values.add(new Interval(from,from + random.nextInt(1,41)));
            }
            check(values);
        }
    }

    @Test void originalPositionsAndDeterministicRoomTiesArePreserved() {
        List<Interval> values = new ArrayList<>(List.of(new Interval(5,7),new Interval(0,4),new Interval(1,4),new Interval(4,5)));
        assertThat(IntervalPartitioning.assign(values).roomByInputIndex()).containsExactly(1,0,1,0);
        check(values);
    }

    private static void check(List<Interval> values) {
        List<Interval> before = List.copyOf(values);
        var result = IntervalPartitioning.assign(values);
        assertThat(result.roomCount()).isEqualTo(peak(values));
        assertThat(result.roomByInputIndex()).hasSize(values.size());
        for (int i = 0; i < values.size(); i++) {
            int room = result.roomByInputIndex().get(i);
            assertThat(room).isBetween(0,result.roomCount() - 1);
            for (int j = i + 1; j < values.size(); j++) {
                if (room == result.roomByInputIndex().get(j)) {
                    Interval a = values.get(i), b = values.get(j);
                    assertThat(a.finish() <= b.start() || b.finish() <= a.start()).isTrue();
                }
            }
        }
        assertThat(values).isEqualTo(before);
        assertThat(IntervalPartitioning.assign(values)).isEqualTo(result);
    }

    private static int peak(List<Interval> values) {
        int best = 0;
        for (Interval candidate : values) {
            int active = 0;
            for (Interval interval : values) {
                if (interval.start() <= candidate.start() && candidate.start() < interval.finish()) { active++; }
            }
            best = Math.max(best,active);
        }
        return best;
    }
}
