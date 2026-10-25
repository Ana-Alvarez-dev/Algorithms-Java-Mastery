package org.anaalvarezdev.algorithms.divideandconquer;

import java.util.Objects;
import java.util.Optional;

/**
 * Maximum-sum nonempty contiguous subarray, with an exact long sum.
 * Empty input returns Optional.empty(); null input throws NullPointerException.
 * Neither variant modifies the input. Ties prefer the smallest start, then end.
 *
 * <p>Every int-array sum fits long. Balanced recursion compares the best left,
 * right and crossing ranges: Theta(n log n) work for n >= 2, O(log(n+1))
 * logical depth/live auxiliary state, without slice copies. The loop maintains
 * the canonical best range ending at each index: Theta(n+1) work, O(1) state.
 * Source result-object creation is not a measured allocation rate.
 */
public final class MaximumSubarray {
    private MaximumSubarray() { }

    /** Immutable result with half-open bounds into the original input. */
    public record Subarray(int fromInclusive, int toExclusive, long sum) { }

    /** Returns the canonical maximum using balanced division and crossing scans. */
    public static Optional<Subarray> divideAndConquer(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        return values.length == 0 ? Optional.empty() : Optional.of(divide(values, 0, values.length));
    }

    /** Returns the same canonical maximum using a linear ending-range invariant. */
    public static Optional<Subarray> linear(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        if (values.length == 0) {
            return Optional.empty();
        }
        long endingSum = values[0];
        int endingStart = 0;
        long bestSum = endingSum;
        int bestStart = 0;
        int bestEnd = 1;
        for (int end = 1; end < values.length; end++) {
            // On equal sums, extending retains the earlier starting index.
            if (endingSum < 0) {
                endingSum = values[end];
                endingStart = end;
            } else {
                endingSum += values[end];
            }
            if (better(endingSum, endingStart, end + 1, bestSum, bestStart, bestEnd)) {
                bestSum = endingSum;
                bestStart = endingStart;
                bestEnd = end + 1;
            }
        }
        return Optional.of(new Subarray(bestStart, bestEnd, bestSum));
    }

    private static Subarray divide(int[] values, int from, int to) {
        if (to - from == 1) {
            return new Subarray(from, to, values[from]);
        }
        int middle = from + (to - from) / 2;
        Subarray left = divide(values, from, middle);
        Subarray right = divide(values, middle, to);
        long suffixSum = 0;
        long bestSuffix = Long.MIN_VALUE;
        int crossingStart = middle - 1;
        for (int i = middle - 1; i >= from; i--) {
            suffixSum += values[i];
            if (suffixSum >= bestSuffix) {
                bestSuffix = suffixSum;
                crossingStart = i;
            }
        }
        long prefixSum = 0;
        long bestPrefix = Long.MIN_VALUE;
        int crossingEnd = middle + 1;
        for (int i = middle; i < to; i++) {
            prefixSum += values[i];
            if (prefixSum > bestPrefix) {
                bestPrefix = prefixSum;
                crossingEnd = i + 1;
            }
        }
        Subarray crossing = new Subarray(crossingStart, crossingEnd, bestSuffix + bestPrefix);
        return best(best(left, right), crossing);
    }

    private static Subarray best(Subarray a, Subarray b) {
        return better(b.sum(), b.fromInclusive(), b.toExclusive(), a.sum(), a.fromInclusive(), a.toExclusive()) ? b : a;
    }

    private static boolean better(long sum, int from, int to, long otherSum, int otherFrom, int otherTo) {
        return sum > otherSum || (sum == otherSum && (from < otherFrom || (from == otherFrom && to < otherTo)));
    }
}
