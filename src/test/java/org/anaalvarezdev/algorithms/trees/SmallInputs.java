package org.anaalvarezdev.algorithms.trees;

import java.util.ArrayList;
import java.util.List;

final class SmallInputs {
    private SmallInputs() { }
    static List<int[]> arrays() {
        List<int[]> output = new ArrayList<>();
        for (int length = 0; length <= 6; length++) { fill(new int[length], 0, output); }
        return output;
    }
    private static void fill(int[] values, int index, List<int[]> output) {
        if (index == values.length) { output.add(values.clone()); return; }
        for (int value = -1; value <= 1; value++) { values[index] = value; fill(values, index + 1, output); }
    }
}
