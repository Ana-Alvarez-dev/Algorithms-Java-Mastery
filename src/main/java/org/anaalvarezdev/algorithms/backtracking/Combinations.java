package org.anaalvarezdev.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Size-k subsets of the distinct integers 0..n-1, ascending within each row
 * and lexicographic across rows. Domain {@code 0 <= k <= n <= 18} bounds search and
 * materialised output; invalid arguments throw IllegalArgumentException.
 * k=0 has one empty combination, including n=0. Rows and outer lists are immutable.
 */
public final class Combinations {
    private Combinations() { }

    public static List<List<Integer>> choose(int n, int k) {
        validate(n,k);
        List<List<Integer>> output = new ArrayList<>();
        enumerate(n,0,k,new int[k],0,output);
        return List.copyOf(output);
    }

    public static long countWithPruning(int n, int k) {
        validate(n,k);
        return count(n,0,k,true);
    }

    /** Same count, retaining branches that cannot supply enough remaining items. */
    public static long countWithoutPruning(int n, int k) {
        validate(n,k);
        return count(n,0,k,false);
    }

    private static void enumerate(int n, int from, int remaining, int[] path, int depth, List<List<Integer>> output) {
        if (remaining == 0) {
            List<Integer> row = new ArrayList<>(path.length);
            for (int value : path) { row.add(value); }
            output.add(List.copyOf(row));
            return;
        }
        for (int candidate = from; candidate <= n - remaining; candidate++) {
            path[depth] = candidate;
            enumerate(n,candidate + 1,remaining - 1,path,depth + 1,output);
        }
    }

    private static long count(int n, int from, int remaining, boolean prune) {
        if (remaining == 0) { return 1; }
        long total = 0;
        int lastCandidate = prune ? n - remaining : n - 1;
        for (int candidate = from; candidate <= lastCandidate; candidate++) {
            total += count(n,candidate + 1,remaining - 1,prune);
        }
        return total;
    }

    private static void validate(int n, int k) {
        if (n < 0 || n > 18 || k < 0 || k > n) { throw new IllegalArgumentException("requires 0 <= k <= n <= 18"); }
    }
}
