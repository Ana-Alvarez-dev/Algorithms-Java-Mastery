package org.anaalvarezdev.algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Distinct value permutations in ascending lexicographic order; equal input
 * values are indistinguishable. Input is preserved. Empty input has one empty
 * permutation. Enumeration accepts length 0..9, counting 0..10: explicit work/
 * output caps, not numeric limits. Null fails; larger inputs throw IllegalArgumentException.
 */
public final class Permutations {
    private Permutations() { }

    /** Materialises deeply immutable rows, with O((n+1)*P) retained output for P rows. */
    public static List<List<Integer>> unique(int[] values) {
        int[] sorted = prepare(values,9);
        List<List<Integer>> output = new ArrayList<>();
        visit(sorted,new boolean[sorted.length],new int[sorted.length],0,output);
        return List.copyOf(output);
    }

    /** Counts the same value permutations using sorted positions and duplicate skipping. */
    public static long countUnique(int[] values) {
        int[] sorted = prepare(values,10);
        return visit(sorted,new boolean[sorted.length],new int[sorted.length],0,null);
    }

    /** Counts via remaining multiplicities, iterating over distinct values at each depth. */
    public static long countUsingFrequencies(int[] values) {
        int[] sorted = prepare(values,10);
        int[] counts = new int[sorted.length];
        int distinct = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) { distinct++; }
            counts[distinct - 1]++;
        }
        return count(counts,distinct,sorted.length);
    }

    private static long visit(int[] sorted, boolean[] used, int[] path, int depth, List<List<Integer>> output) {
        if (depth == sorted.length) {
            if (output != null) {
                List<Integer> row = new ArrayList<>(path.length);
                for (int value : path) { row.add(value); }
                output.add(List.copyOf(row));
            }
            return 1;
        }
        long total = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (used[i] || (i > 0 && sorted[i] == sorted[i - 1] && !used[i - 1])) { continue; }
            used[i] = true;
            path[depth] = sorted[i];
            total += visit(sorted,used,path,depth + 1,output);
            used[i] = false;
        }
        return total;
    }

    private static long count(int[] counts, int distinct, int remaining) {
        if (remaining == 0) { return 1; }
        long total = 0;
        for (int i = 0; i < distinct; i++) {
            if (counts[i] == 0) { continue; }
            counts[i]--;
            total += count(counts,distinct,remaining - 1);
            counts[i]++;
        }
        return total;
    }

    private static int[] prepare(int[] values, int maximum) {
        Objects.requireNonNull(values,"values must not be null");
        if (values.length > maximum) { throw new IllegalArgumentException("permutation work/output cap exceeded"); }
        int[] sorted = values.clone();
        Arrays.sort(sorted);
        return sorted;
    }
}
