package org.anaalvarezdev.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/** All 1,024 subsets of the ten nonempty intervals with endpoints in 0..4. */
final class SmallSchedules {
    private SmallSchedules() { }
    static void forEach(Consumer<List<Interval>> check) {
        List<Interval> universe = new ArrayList<>();
        for (int from = 0; from < 4; from++) {
            for (int to = from + 1; to <= 4; to++) { universe.add(new Interval(from,to)); }
        }
        for (int mask = 0; mask < 1 << universe.size(); mask++) {
            List<Interval> values = new ArrayList<>();
            for (int i = 0; i < universe.size(); i++) {
                if ((mask & (1 << i)) != 0) { values.add(universe.get(i)); }
            }
            check.accept(values);
        }
    }
}
