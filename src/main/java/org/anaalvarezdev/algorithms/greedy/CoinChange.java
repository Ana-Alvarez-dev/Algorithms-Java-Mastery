package org.anaalvarezdev.algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Largest-denomination-first change, an explicit heuristic for arbitrary coins.
 * It need not minimise coin count or find an existing representation. The
 * remaining amount reports that limitation; no optimality is claimed generally.
 */
public final class CoinChange {
    private CoinChange() { }
    public record CoinUse(int denomination, int quantity) { }
    public record Change(List<CoinUse> uses, int remaining, long coinCount) {
        public Change { uses = List.copyOf(uses); }
        public boolean complete() { return remaining == 0; }
    }

    /**
     * Accepts nonnegative amount and positive denominations, collapsing duplicates.
     * Empty denomination sets are accepted. Null throws NullPointerException;
     * invalid amount/denominations throw IllegalArgumentException before solving,
     * even for amount zero. Input is preserved; output quantities are compressed.
     * O(d log d) worst preprocessing, O(d) scan/storage; no loop over amount.
     */
    public static Change greedy(int[] denominations, int amount) {
        Objects.requireNonNull(denominations, "denominations must not be null");
        if (amount < 0) { throw new IllegalArgumentException("amount must be nonnegative"); }
        int[] ordered = denominations.clone();
        for (int value : ordered) {
            if (value <= 0) { throw new IllegalArgumentException("denominations must be positive"); }
        }
        Arrays.sort(ordered);
        List<CoinUse> uses = new ArrayList<>();
        int remaining = amount;
        long count = 0;
        for (int i = ordered.length - 1; i >= 0; i--) {
            int coin = ordered[i];
            if (i + 1 < ordered.length && coin == ordered[i + 1]) { continue; }
            int quantity = remaining / coin;
            if (quantity > 0) {
                uses.add(new CoinUse(coin, quantity));
                remaining %= coin;
                count += quantity;
            }
        }
        return new Change(uses, remaining, count);
    }
}
