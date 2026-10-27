package org.anaalvarezdev.algorithms.backtracking;

import java.util.Arrays;
import java.util.SplittableRandom;

/** Trial-only fixtures; generation is outside timed operations. */
final class BacktrackingInputs {
    private BacktrackingInputs() { }
    static int[] permutations(int n, String distribution) {
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = switch (distribution) {
                case "DISTINCT" -> i;
                case "DUPLICATE_HEAVY" -> i % 3;
                case "ALL_EQUAL" -> 1;
                default -> throw new IllegalArgumentException("unknown distribution");
            };
        }
        SplittableRandom random = new SplittableRandom(20261026);
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1); int value = values[i]; values[i] = values[j]; values[j] = value;
        }
        return values;
    }
    static int cardinality(int n, String selection) {
        return switch (selection) {
            case "TWO" -> 2;
            case "HALF" -> n / 2;
            case "ALL" -> n;
            default -> throw new IllegalArgumentException("unknown selection");
        };
    }
    static int[] subset(int n, String scenario) {
        int[] values = new int[n]; Arrays.fill(values,2);
        if (scenario.equals("SIGNED_MISS")) {
            for (int i = 0; i < n; i += 2) { values[i] = -2; }
        } else if (!scenario.equals("RANGE_MISS") && !scenario.equals("PARITY_MISS") && !scenario.equals("PRESENT")) {
            throw new IllegalArgumentException("unknown scenario");
        }
        return values;
    }
    static long target(int n, String scenario) {
        return switch (scenario) {
            case "RANGE_MISS" -> 2L * n + 1;
            case "PARITY_MISS" -> 2L * n - 1;
            case "PRESENT" -> n;
            case "SIGNED_MISS" -> 1;
            default -> throw new IllegalArgumentException("unknown scenario");
        };
    }
}
