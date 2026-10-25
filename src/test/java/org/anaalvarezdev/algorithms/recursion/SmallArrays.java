package org.anaalvarezdev.algorithms.recursion;

import java.util.ArrayList;
import java.util.List;

final class SmallArrays {
    private SmallArrays() { }
    static List<int[]> all() {
        List<int[]> result = new ArrayList<>();
        for (int length = 0; length <= 6; length++) { fill(new int[length],0,result); }
        return result;
    }
    private static void fill(int[] values, int index, List<int[]> result) {
        if (index == values.length) { result.add(values.clone()); return; }
        for (int value = -1; value <= 1; value++) { values[index] = value; fill(values,index+1,result); }
    }
}
