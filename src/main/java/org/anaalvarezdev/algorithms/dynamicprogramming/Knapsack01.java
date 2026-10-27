package org.anaalvarezdev.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Exact 0/1 knapsack over original item positions; empty subset is allowed.
 * Non-null equal-length arrays require positive weights and nonnegative values.
 * All variants preserve inputs and share educational caps: at most 256 items,
 * capacity 0..10000, and at most 1000000 cells including boundary row/column.
 * Null fails with NullPointerException; other invalid input with
 * IllegalArgumentException before search/table allocation. Exact sums use long.
 */
public final class Knapsack01 {
    private Knapsack01() { }

    /** Solver results have increasing original indices; the list is snapshotted. */
    public record Selection(long value, int weight, List<Integer> indices) {
        public Selection { indices = List.copyOf(indices); }
    }

    /** Fresh dense -1-filled cache; lazy expansion still pays full grid initialisation. */
    public static long valueMemoised(int[] weights, int[] values, int capacity) {
        validate(weights, values, capacity);
        long[][] memo = new long[weights.length + 1][capacity + 1];
        for (long[] row : memo) { Arrays.fill(row, -1); }
        return cached(weights, values, weights.length, capacity, memo);
    }

    public static long valueTabulated(int[] weights, int[] values, int capacity) {
        validate(weights, values, capacity);
        return table(weights, values, capacity)[weights.length][capacity];
    }

    /** Descending capacities ensure an item cannot be used more than once. */
    public static long valueRolling(int[] weights, int[] values, int capacity) {
        validate(weights, values, capacity);
        long[] row = new long[capacity + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int c = capacity; c >= weights[i]; c--) {
                row[c] = Math.max(row[c], values[i] + row[c - weights[i]]);
            }
        }
        return row[capacity];
    }

    /**
     * Reconstructs one optimum from a full table. Ties omit the highest remaining
     * index, choosing the smallest numeric subset mask, not minimum total weight.
     */
    public static Selection select(int[] weights, int[] values, int capacity) {
        validate(weights, values, capacity);
        long[][] table = table(weights, values, capacity);
        List<Integer> indices = new ArrayList<>();
        int remaining = capacity;
        for (int i = weights.length; i > 0; i--) {
            if (table[i][remaining] > table[i - 1][remaining]) {
                indices.add(i - 1);
                remaining -= weights[i - 1];
            }
        }
        Collections.reverse(indices);
        return new Selection(table[weights.length][capacity], capacity - remaining, indices);
    }

    private static long cached(int[] weights, int[] values, int items, int capacity, long[][] memo) {
        if (items == 0) { return 0; }
        if (memo[items][capacity] >= 0) { return memo[items][capacity]; }
        long best = cached(weights, values, items - 1, capacity, memo);
        if (weights[items - 1] <= capacity) {
            best = Math.max(best, values[items - 1] + cached(
                    weights, values, items - 1, capacity - weights[items - 1], memo));
        }
        memo[items][capacity] = best;
        return best;
    }

    private static long[][] table(int[] weights, int[] values, int capacity) {
        long[][] table = new long[weights.length + 1][capacity + 1];
        for (int i = 1; i <= weights.length; i++) {
            for (int c = 0; c <= capacity; c++) {
                table[i][c] = table[i - 1][c];
                if (weights[i - 1] <= c) {
                    table[i][c] = Math.max(table[i][c], values[i - 1] + table[i - 1][c - weights[i - 1]]);
                }
            }
        }
        return table;
    }

    private static void validate(int[] weights, int[] values, int capacity) {
        Objects.requireNonNull(weights, "weights");
        Objects.requireNonNull(values, "values");
        if (weights.length != values.length || weights.length > 256 || capacity < 0 || capacity > 10_000
                || (weights.length + 1L) * (capacity + 1L) > 1_000_000) {
            throw new IllegalArgumentException("invalid dimensions or selected work cap exceeded");
        }
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] <= 0 || values[i] < 0) {
                throw new IllegalArgumentException("weights must be positive and values nonnegative");
            }
        }
    }
}
