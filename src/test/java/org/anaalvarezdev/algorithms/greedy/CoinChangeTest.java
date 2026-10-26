package org.anaalvarezdev.algorithms.greedy;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.*;

class CoinChangeTest {
    @Test void rejectsNullAndInvalidDomainsEvenAtZeroAmount() {
        assertThatNullPointerException().isThrownBy(() -> CoinChange.greedy(null,0));
        assertThatIllegalArgumentException().isThrownBy(() -> CoinChange.greedy(new int[]{1},-1));
        for (int invalid : new int[]{0,-1,Integer.MIN_VALUE}) {
            assertThatIllegalArgumentException().isThrownBy(() -> CoinChange.greedy(new int[]{1,invalid},0));
        }
    }

    @Test void emptySetsAndZeroHaveExplicitRemainderAndOwnership() {
        assertThat(CoinChange.greedy(new int[0],0).complete()).isTrue();
        var missing = CoinChange.greedy(new int[0],7);
        assertThat(missing.remaining()).isEqualTo(7);
        assertThat(missing.coinCount()).isZero();
        assertThat(missing.complete()).isFalse();
        var zero = CoinChange.greedy(new int[]{2,1},0);
        assertThat(zero.uses()).isEmpty();
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> zero.uses().add(new CoinChange.CoinUse(1,1)));
    }

    @Test void counterexamplesDistinguishSuboptimalCountFromMissedRepresentation() {
        var suboptimal = CoinChange.greedy(new int[]{1,3,4},6);
        assertThat(suboptimal.complete()).isTrue();
        assertThat(suboptimal.coinCount()).isEqualTo(3);
        assertThat(optimalCounts(new int[]{1,3,4},6)[6]).isEqualTo(2);
        var missed = CoinChange.greedy(new int[]{3,4},6);
        assertThat(missed.complete()).isFalse();
        assertThat(missed.remaining()).isEqualTo(2);
        assertThat(optimalCounts(new int[]{3,4},6)[6]).isEqualTo(2);
    }

    @Test void binaryDenominationsMatchExactDynamicOracleThrough4096() {
        int[] denominations = {1,2,4,8,16,32};
        int[] optimum = optimalCounts(denominations,4096);
        for (int amount = 0; amount <= 4096; amount++) {
            var result = CoinChange.greedy(denominations,amount);
            assertThat(result.complete()).isTrue();
            assertThat(result.coinCount()).isEqualTo(optimum[amount]);
            long recovered = result.uses().stream().mapToLong(u -> (long) u.denomination() * u.quantity()).sum();
            assertThat(recovered).isEqualTo(amount);
        }
    }

    @Test void duplicateOrderAndInputMutationDoNotChangeThePlan() {
        int[] denominations = {4,1,3,4,1}; int[] before = denominations.clone();
        var result = CoinChange.greedy(denominations,13);
        assertThat(result).isEqualTo(CoinChange.greedy(new int[]{1,3,4},13));
        assertThat(result.uses()).containsExactly(new CoinChange.CoinUse(4,3),new CoinChange.CoinUse(1,1));
        assertThat(denominations).containsExactly(before);
    }

    @Test void maximumAmountUsesCompressedQuantitiesRatherThanBillionsOfEntries() {
        var result = CoinChange.greedy(new int[]{1},Integer.MAX_VALUE);
        assertThat(result.complete()).isTrue();
        assertThat(result.coinCount()).isEqualTo(Integer.MAX_VALUE);
        assertThat(result.uses()).containsExactly(new CoinChange.CoinUse(1,Integer.MAX_VALUE));
    }

    private static int[] optimalCounts(int[] denominations, int bound) {
        int[] best = new int[bound + 1]; Arrays.fill(best,bound + 1); best[0] = 0;
        for (int amount = 1; amount <= bound; amount++) {
            for (int coin : denominations) {
                if (coin <= amount) { best[amount] = Math.min(best[amount],best[amount - coin] + 1); }
            }
        }
        return best;
    }
}
