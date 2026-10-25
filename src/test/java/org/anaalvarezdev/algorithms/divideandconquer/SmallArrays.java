package org.anaalvarezdev.algorithms.divideandconquer;

import java.util.function.Consumer;

/** All 3,280 arrays of lengths 0..7 over {-1,0,1}. */
final class SmallArrays {
    private SmallArrays() { }

    static void forEach(Consumer<int[]> check) {
        for (int length = 0; length <= 7; length++) {
            enumerate(new int[length], 0, check);
        }
    }

    private static void enumerate(int[] values, int index, Consumer<int[]> check) {
        if (index == values.length) {
            check.accept(values.clone());
            return;
        }
        for (int value = -1; value <= 1; value++) {
            values[index] = value;
            enumerate(values, index + 1, check);
        }
    }
}
