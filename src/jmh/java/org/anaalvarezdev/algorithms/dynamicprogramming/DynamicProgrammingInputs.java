package org.anaalvarezdev.algorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.SplittableRandom;

/** Trial-only fixtures; generation/shuffling is excluded from timed public calls. */
final class DynamicProgrammingInputs {
    private DynamicProgrammingInputs() { }

    record Items(int[] weights, int[] values) { }
    record Texts(String first, String second) { }

    static Items items(int n, int capacity, String shape) {
        int weight = switch (shape) {
            case "LIGHT" -> 1;
            case "TOO_HEAVY" -> capacity + 1;
            default -> throw new IllegalArgumentException("unknown shape");
        };
        int[] weights = new int[n];
        int[] values = new int[n];
        Arrays.fill(weights, weight);
        for (int i = 0; i < n; i++) { values[i] = 1 + i % 7; }
        SplittableRandom random = new SplittableRandom(20261026);
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temporary = values[i]; values[i] = values[j]; values[j] = temporary;
        }
        return new Items(weights, values);
    }

    static Texts texts(int n, String shape) {
        return switch (shape) {
            case "IDENTICAL" -> new Texts("A".repeat(n), "A".repeat(n));
            case "DISJOINT" -> new Texts("A".repeat(n), "B".repeat(n));
            case "SHIFTED" -> new Texts("AB".repeat(n / 2), "BA".repeat(n / 2));
            case "RANDOM" -> {
                SplittableRandom random = new SplittableRandom(20261026);
                yield new Texts(randomText(n, random), randomText(n, random));
            }
            default -> throw new IllegalArgumentException("unknown shape");
        };
    }

    private static String randomText(int n, SplittableRandom random) {
        StringBuilder text = new StringBuilder(n);
        for (int i = 0; i < n; i++) { text.append((char) ('A' + random.nextInt(4))); }
        return text.toString();
    }
}
