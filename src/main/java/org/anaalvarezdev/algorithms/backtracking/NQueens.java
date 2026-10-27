package org.anaalvarezdev.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * All ordinary n-queens placements, without quotienting rotations/reflections.
 * A solution lists its zero-based column per row. Domain 0..12 is an explicit
 * bounded-search/output cap, validated before shifts/allocations. n=0 has one
 * empty board; n=2/3 have none. Results are deeply immutable and lexicographic.
 */
public final class NQueens {
    private NQueens() { }

    public static List<List<Integer>> solutions(int n) {
        validate(n);
        List<List<Integer>> output = new ArrayList<>();
        search(n,0,new boolean[n],new boolean[Math.max(0,2 * n - 1)],
                new boolean[Math.max(0,2 * n - 1)],new int[n],output);
        return List.copyOf(output);
    }

    public static long countWithFlags(int n) {
        validate(n);
        return search(n,0,new boolean[n],new boolean[Math.max(0,2 * n - 1)],
                new boolean[Math.max(0,2 * n - 1)],new int[n],null);
    }

    public static long countWithBitMasks(int n) {
        validate(n);
        long full = (1L << n) - 1;
        return count(full,0,0,0);
    }

    private static long search(int n, int row, boolean[] columns, boolean[] descending,
                               boolean[] ascending, int[] path, List<List<Integer>> output) {
        if (row == n) {
            if (output != null) {
                List<Integer> solution = new ArrayList<>(n);
                for (int column : path) { solution.add(column); }
                output.add(List.copyOf(solution));
            }
            return 1;
        }
        long total = 0;
        for (int column = 0; column < n; column++) {
            int down = row - column + n - 1;
            int up = row + column;
            if (columns[column] || descending[down] || ascending[up]) { continue; }
            columns[column] = descending[down] = ascending[up] = true;
            path[row] = column;
            total += search(n,row + 1,columns,descending,ascending,path,output);
            columns[column] = descending[down] = ascending[up] = false;
        }
        return total;
    }

    private static long count(long full, long columns, long left, long right) {
        if (columns == full) { return 1; }
        long available = full & ~(columns | left | right);
        long total = 0;
        while (available != 0) {
            long bit = available & -available;
            available -= bit;
            total += count(full,columns | bit,((left | bit) << 1) & full,(right | bit) >>> 1);
        }
        return total;
    }

    private static void validate(int n) {
        if (n < 0 || n > 12) { throw new IllegalArgumentException("queens search requires 0 <= n <= 12"); }
    }
}
