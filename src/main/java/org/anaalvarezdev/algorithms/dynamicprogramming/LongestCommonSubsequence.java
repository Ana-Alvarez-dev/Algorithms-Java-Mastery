package org.anaalvarezdev.algorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.Objects;

/**
 * LCS over Java UTF-16 char units, without normalisation/code-point conversion.
 * Each non-null String may contain at most 512 units, an explicit work/depth cap.
 * Empty input gives length zero/empty output. Null throws NullPointerException;
 * larger inputs throw IllegalArgumentException before table allocation.
 * All caches are per-call. A unit subsequence may contain a lone surrogate.
 */
public final class LongestCommonSubsequence {
    private LongestCommonSubsequence() { }

    /** Nonbase states are expanded once; the fresh dense cache is fully initialised. */
    public static int lengthMemoised(String first, String second) {
        validate(first, second);
        int[][] memo = new int[first.length() + 1][second.length() + 1];
        for (int[] row : memo) { Arrays.fill(row, -1); }
        return cached(first, second, first.length(), second.length(), memo);
    }

    public static int lengthTabulated(String first, String second) {
        validate(first, second);
        return table(first, second)[first.length()][second.length()];
    }

    /** One row of min(lengths)+1 cells plus the previous diagonal scalar. */
    public static int lengthRolling(String first, String second) {
        validate(first, second);
        String rows = first.length() >= second.length() ? first : second;
        String columns = first.length() >= second.length() ? second : first;
        int[] row = new int[columns.length() + 1];
        for (int i = 1; i <= rows.length(); i++) {
            int diagonal = 0;
            for (int j = 1; j <= columns.length(); j++) {
                int above = row[j];
                row[j] = rows.charAt(i - 1) == columns.charAt(j - 1)
                        ? diagonal + 1 : Math.max(above, row[j - 1]);
                diagonal = above;
            }
        }
        return row[columns.length()];
    }

    /**
     * One maximum subsequence. On unequal trailing units and equal predecessor
     * scores, discard from the first prefix; no lexicographic-minimum promise.
     */
    public static String sequence(String first, String second) {
        validate(first, second);
        int[][] table = table(first, second);
        int i = first.length();
        int j = second.length();
        char[] output = new char[table[i][j]];
        int next = output.length;
        while (i > 0 && j > 0) {
            if (first.charAt(i - 1) == second.charAt(j - 1)) {
                output[--next] = first.charAt(i - 1);
                i--;
                j--;
            } else if (table[i - 1][j] >= table[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return new String(output);
    }

    private static int cached(String first, String second, int i, int j, int[][] memo) {
        if (i == 0 || j == 0) { return 0; }
        if (memo[i][j] >= 0) { return memo[i][j]; }
        memo[i][j] = first.charAt(i - 1) == second.charAt(j - 1)
                ? 1 + cached(first, second, i - 1, j - 1, memo)
                : Math.max(cached(first, second, i - 1, j, memo), cached(first, second, i, j - 1, memo));
        return memo[i][j];
    }

    private static int[][] table(String first, String second) {
        int[][] table = new int[first.length() + 1][second.length() + 1];
        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                table[i][j] = first.charAt(i - 1) == second.charAt(j - 1)
                        ? 1 + table[i - 1][j - 1] : Math.max(table[i - 1][j], table[i][j - 1]);
            }
        }
        return table;
    }

    private static void validate(String first, String second) {
        Objects.requireNonNull(first, "first");
        Objects.requireNonNull(second, "second");
        if (first.length() > 512 || second.length() > 512) {
            throw new IllegalArgumentException("LCS requires at most 512 UTF-16 units per String");
        }
    }
}
