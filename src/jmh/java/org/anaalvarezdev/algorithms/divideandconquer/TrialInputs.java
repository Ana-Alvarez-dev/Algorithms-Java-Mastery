package org.anaalvarezdev.algorithms.divideandconquer;

import java.util.Arrays;
import java.util.SplittableRandom;

/** Deterministic trial-only input construction; never part of timed methods. */
final class TrialInputs {
    private TrialInputs() { }

    static int[] sum(int size, String distribution) {
        SplittableRandom random = new SplittableRandom(20261025);
        return switch (distribution) {
            case "MIXED" -> random.ints(size, -1000, 1001).toArray();
            case "ALL_NEGATIVE" -> random.ints(size, -1000, 0).toArray();
            case "ALL_POSITIVE" -> random.ints(size, 1, 1001).toArray();
            case "ALL_ZERO" -> new int[size];
            default -> throw new IllegalArgumentException("unknown sum distribution");
        };
    }

    static int[] ordered(int size, String distribution) {
        SplittableRandom random = new SplittableRandom(20261025);
        if (distribution.equals("DUPLICATE_HEAVY")) {
            return random.ints(size, -4, 5).toArray();
        }
        int[] values = random.ints(size).toArray();
        switch (distribution) {
            case "RANDOM": return values;
            case "SORTED": Arrays.sort(values); return values;
            case "REVERSE_SORTED":
                Arrays.sort(values);
                for (int i = 0; i < size / 2; i++) {
                    int temporary = values[i];
                    values[i] = values[size - 1 - i];
                    values[size - 1 - i] = temporary;
                }
                return values;
            default: throw new IllegalArgumentException("unknown order distribution");
        }
    }
}
