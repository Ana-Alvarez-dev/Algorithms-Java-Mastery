package org.anaalvarezdev.algorithms.sorting;

import java.util.SplittableRandom;

/** Identical deterministic source data for both sorting experiment groups. */
final class SortingBenchmarkInputs {

    private SortingBenchmarkInputs() {
    }

    static int[] create(int size, String distribution) {
        int[] values = new int[size];
        SplittableRandom random = new SplittableRandom(20261009L);
        for (int index = 0; index < size; index++) {
            values[index] = switch (distribution) {
                case "RANDOM" -> random.nextInt();
                case "SORTED", "NEARLY_SORTED" -> index;
                case "REVERSE_SORTED" -> size - 1 - index;
                case "DUPLICATE_HEAVY" -> random.nextInt(16) - 8;
                default -> throw new IllegalArgumentException(
                        "Unknown distribution: " + distribution);
            };
        }
        if (distribution.equals("NEARLY_SORTED") && size > 1) {
            // Local disorder: max(1, floor(n/100)) seeded adjacent exchanges.
            for (int exchange = 0; exchange < Math.max(1, size / 100); exchange++) {
                int index = random.nextInt(size - 1);
                int temporary = values[index];
                values[index] = values[index + 1];
                values[index + 1] = temporary;
            }
        }
        return values;
    }
}
