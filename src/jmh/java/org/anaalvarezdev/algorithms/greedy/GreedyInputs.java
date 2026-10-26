package org.anaalvarezdev.algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SplittableRandom;

/** Trial-only fixed-seed fixtures; their construction is excluded from timing. */
final class GreedyInputs {
    private GreedyInputs() { }
    static List<Interval> intervals(int size, String shape) {
        SplittableRandom random = new SplittableRandom(20261026);
        List<Interval> values = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            switch (shape) {
                case "DISJOINT" -> values.add(new Interval(3 * i,3 * i + 1));
                case "TOUCHING" -> values.add(new Interval(i,i + 1));
                case "OVERLAPPING" -> values.add(new Interval(random.nextInt(100),random.nextInt(101,201)));
                case "RANDOM" -> {
                    int start = random.nextInt(4 * size);
                    values.add(new Interval(start,start + random.nextInt(1,101)));
                }
                default -> throw new IllegalArgumentException("unknown interval shape");
            }
        }
        for (int i = values.size() - 1; i > 0; i--) { Collections.swap(values,i,random.nextInt(i + 1)); }
        return values;
    }

    static int[] frequencies(int size, String distribution) {
        SplittableRandom random = new SplittableRandom(20261026);
        int[] values = new int[size];
        switch (distribution) {
            case "UNIFORM" -> Arrays.fill(values,10);
            case "RANDOM" -> { for (int i = 0; i < size; i++) { values[i] = random.nextInt(1,1001); } }
            case "SKEWED" -> { Arrays.fill(values,1); values[0] = Integer.MAX_VALUE; }
            case "TWO_LEVEL" -> { for (int i = 0; i < size; i++) { values[i] = i % 2 == 0 ? 1 : 1000; } }
            default -> throw new IllegalArgumentException("unknown frequency distribution");
        }
        for (int i = values.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1); int temporary = values[i]; values[i] = values[j]; values[j] = temporary;
        }
        return values;
    }
}
