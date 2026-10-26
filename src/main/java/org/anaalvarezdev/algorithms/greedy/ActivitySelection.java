package org.anaalvarezdev.algorithms.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Maximum-cardinality compatible subset of nonempty half-open intervals.
 * Touching intervals are compatible. All results are immutable; input is preserved.
 * Ties use finish then start, with equal records indistinguishable by value.
 * Weighted scheduling is a different problem and is not solved by this rule.
 */
public final class ActivitySelection {
    private ActivitySelection() { }
    private static final Comparator<Interval> FINISH_ORDER =
            Comparator.comparingInt(Interval::finish).thenComparingInt(Interval::start);

    /** Sorts a private snapshot, then selects: O(n log n) worst work, O(n) state. */
    public static List<Interval> select(List<Interval> intervals) {
        List<Interval> ordered = new ArrayList<>(Objects.requireNonNull(intervals, "intervals must not be null"));
        ordered.forEach(value -> Objects.requireNonNull(value, "interval must not be null"));
        ordered.sort(FINISH_ORDER);
        return scan(ordered);
    }

    /**
     * Validates finish-then-start order and selects in O(n+1) work.
     * A defensive snapshot/output still uses O(n) space, not constant total space.
     * Throws IllegalArgumentException on an ordering inversion; nulls fail first.
     */
    public static List<Interval> selectSorted(List<Interval> intervals) {
        List<Interval> ordered = List.copyOf(intervals);
        Interval previous = null;
        for (Interval interval : ordered) {
            if (previous != null && FINISH_ORDER.compare(previous, interval) > 0) {
                throw new IllegalArgumentException("intervals must be ordered by finish, then start");
            }
            previous = interval;
        }
        return scan(ordered);
    }

    private static List<Interval> scan(List<Interval> ordered) {
        List<Interval> selected = new ArrayList<>();
        int lastFinish = 0;
        for (Interval interval : ordered) {
            if (selected.isEmpty() || interval.start() >= lastFinish) {
                selected.add(interval);
                lastFinish = interval.finish();
            }
        }
        return List.copyOf(selected);
    }
}
