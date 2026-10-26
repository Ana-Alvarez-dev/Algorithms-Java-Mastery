package org.anaalvarezdev.algorithms.greedy;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.*;

class ActivitySelectionTest {
    private static final Comparator<Interval> ORDER = Comparator.comparingInt(Interval::finish).thenComparingInt(Interval::start);

    @Test void rejectsNullListsAndNullEntriesInBothVariants() {
        assertThatNullPointerException().isThrownBy(() -> ActivitySelection.select(null));
        assertThatNullPointerException().isThrownBy(() -> ActivitySelection.selectSorted(null));
        List<Interval> values = Arrays.asList(new Interval(0,1), null);
        assertThatNullPointerException().isThrownBy(() -> ActivitySelection.select(values));
        assertThatNullPointerException().isThrownBy(() -> ActivitySelection.selectSorted(values));
    }

    @Test void emptyAndReturnedPlansAreImmutable() {
        assertThat(ActivitySelection.select(List.of())).isEmpty();
        assertThat(ActivitySelection.selectSorted(List.of())).isEmpty();
        List<Interval> result = ActivitySelection.select(List.of(new Interval(0,1)));
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> result.clear());
    }

    @Test void canonicalPlansHandleTouchingTiesDuplicatesAndSignedTimes() {
        List<Interval> values = List.of(new Interval(3,5),new Interval(1,4),new Interval(0,4),new Interval(4,7),new Interval(0,4));
        assertThat(ActivitySelection.select(values)).containsExactly(new Interval(0,4),new Interval(4,7));
        assertThat(ActivitySelection.select(List.of(new Interval(-2,0),new Interval(0,1),new Interval(1,2)))).hasSize(3);
        assertThat(ActivitySelection.select(List.of(new Interval(Integer.MIN_VALUE,-1),new Interval(-1,Integer.MAX_VALUE)))).hasSize(2);
        check(values);
    }

    @Test void presortedVariantRejectsFullComparatorInversions() {
        assertThatIllegalArgumentException().isThrownBy(() -> ActivitySelection.selectSorted(List.of(new Interval(1,3),new Interval(0,2))));
        assertThatIllegalArgumentException().isThrownBy(() -> ActivitySelection.selectSorted(List.of(new Interval(1,3),new Interval(0,3))));
    }

    @Test void allSmallIntervalSetsMatchExhaustiveCompatibleSubsets() {
        SmallSchedules.forEach(ActivitySelectionTest::check);
    }

    @Test void seededUnsortedAndLinkedInputsMatchExhaustiveOracle() {
        SplittableRandom random = new SplittableRandom(20261026);
        for (int sample = 0; sample < 200; sample++) {
            List<Interval> values = new LinkedList<>();
            int size = random.nextInt(13);
            for (int i = 0; i < size; i++) {
                int from = random.nextInt(-10,11);
                values.add(new Interval(from,from + random.nextInt(1,9)));
            }
            check(values);
        }
    }

    private static void check(List<Interval> values) {
        List<Interval> before = List.copyOf(values);
        List<Interval> result = ActivitySelection.select(values);
        assertThat(result).hasSize(optimum(values));
        for (int i = 0; i < result.size(); i++) {
            assertThat(values).contains(result.get(i));
            if (i > 0) { assertThat(result.get(i).start()).isGreaterThanOrEqualTo(result.get(i - 1).finish()); }
        }
        List<Interval> ordered = new ArrayList<>(values); ordered.sort(ORDER);
        assertThat(ActivitySelection.selectSorted(ordered)).isEqualTo(result);
        assertThat(ActivitySelection.select(values)).isEqualTo(result);
        assertThat(values).isEqualTo(before);
    }

    private static int optimum(List<Interval> values) {
        int best = 0;
        for (int mask = 0; mask < 1 << values.size(); mask++) {
            boolean compatible = true;
            for (int i = 0; i < values.size() && compatible; i++) {
                if ((mask & (1 << i)) == 0) { continue; }
                for (int j = i + 1; j < values.size(); j++) {
                    if ((mask & (1 << j)) != 0 && overlaps(values.get(i),values.get(j))) { compatible = false; break; }
                }
            }
            if (compatible) { best = Math.max(best,Integer.bitCount(mask)); }
        }
        return best;
    }

    private static boolean overlaps(Interval a, Interval b) {
        return a.start() < b.finish() && b.start() < a.finish();
    }
}
